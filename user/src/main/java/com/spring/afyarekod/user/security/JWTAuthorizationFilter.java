package com.spring.afyarekod.user.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.spring.afyarekod.user.Constants;
import com.spring.afyarekod.user.model.UserByLogin;
import com.spring.afyarekod.user.service.UserService;

import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
	
	@Autowired
	UserService userService;
	
	public JWTAuthorizationFilter(AuthenticationManager authManager,ApplicationContext ctx) {
		super(authManager);	
		this.userService = ctx.getBean(UserService.class);		
	}

	@Override

	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader(Constants.HEADER_STRING);

		if (header == null || !header.startsWith(Constants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);

	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

		String token = request.getHeader(Constants.HEADER_STRING);
		String tokensub = token.replace(Constants.TOKEN_PREFIX, "");
		byte[] decodedBytes = Base64.decodeBase64(tokensub);
		String decodedString = new String(decodedBytes);
		decodedString = decodedString.replaceAll("[^a-zA-Z0-9{}:,]", "");
		int index = decodedString.indexOf("sub:");
		int next = decodedString.indexOf(",roles");
		String sub = decodedString.substring(index+4,next);
		UserByLogin userByLogin = userService.findByKeyLogin(sub);
		String secret = userByLogin.getSecret();
		
		if (token != null) {
			String user = Jwts.parser().setSigningKey(secret)
					.parseClaimsJws(token.replace(Constants.TOKEN_PREFIX, "")).getBody().getSubject();

			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
			}

			return null;

		}

		return null;

	}

}
