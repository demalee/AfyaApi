package com.spring.afyarekod.user.security;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.datastax.driver.core.exceptions.AuthenticationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.afyarekod.user.Constants;
import com.spring.afyarekod.user.model.AppUser;
import com.spring.afyarekod.user.model.UserByLogin;
import com.spring.afyarekod.user.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	UserService userService;
	
	private AuthenticationManager authenticationManager;
	private AppUser appUser;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,ApplicationContext ctx) {
		this.authenticationManager = authenticationManager;
		this.userService = ctx.getBean(UserService.class);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			appUser = new ObjectMapper().readValue(req.getInputStream(), AppUser.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getUsername(),
					appUser.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			System.out.println("JWTAuthenticationFilter Error:" + e.getMessage());
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((User) auth.getPrincipal()).getUsername();
		Claims claims = Jwts.claims().setSubject(((User) auth.getPrincipal()).getUsername());
		String userid = "";
		UserByLogin user = userService.findByKeyLogin(username);
		String secret = user.getSecret();
		userid = user.getUserId();		
		claims.put("roles", user.getRoles());
		long currentmillis = System.currentTimeMillis();
		String token = Jwts.builder().setClaims(claims)
				.setExpiration(new Date(currentmillis + Constants.EXPIRATION_TIME))
				.setIssuedAt(new Date(currentmillis))
				.signWith(SignatureAlgorithm.HS512, secret).compact();		
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		HashMap<String, Object> usermap = new HashMap<String, Object>();
		HashMap<String, Object> statusmap = new HashMap<String, Object>();
		statusmap.put("status_id", "1");
		statusmap.put("name", "Active");
		usermap.put("id", userid);
		usermap.put("firstname", user.getFirstname());
		usermap.put("lastname", user.getLastname());
		usermap.put("email", user.getEmail());
		usermap.put("phone", user.getPhone());
		usermap.put("status", statusmap);
		usermap.put("roles", user.getRoles());
		usermap.put("country", user.getLocation());
		map.put("user", usermap);
		map.put("token", token);
		map.put("timestamp", ""+System.currentTimeMillis());
		JSONObject jSONObject = new JSONObject(map);
		res.getWriter().write(jSONObject.toString());
	}

}
