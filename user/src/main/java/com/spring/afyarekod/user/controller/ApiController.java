/**
REST Controller endpoints
 * 
 */
package com.spring.afyarekod.user.controller;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.spring.afyarekod.user.model.UserByGender;
import com.spring.afyarekod.user.model.UserById;
import com.spring.afyarekod.user.model.UserByLocation;
import com.spring.afyarekod.user.model.UserByLogin;
import com.spring.afyarekod.user.model.UserKeyGender;
import com.spring.afyarekod.user.model.UserKeyId;
import com.spring.afyarekod.user.model.UserKeyLocation;
import com.spring.afyarekod.user.model.UserKeyLogin;
import com.spring.afyarekod.user.service.UserService;
import com.spring.afyarekod.user.util.StringUtil;

/**
 * @author gichohi karuga
 *
 */
@RestController
@RequestMapping(value = { "/" })
public class ApiController {
	
	private static final Logger LOGGER = Logger.getLogger(ApiController.class.getName());

	
	
	public static final String DEFAULT_IMAGE = "avatar.jpeg";
	
	@Autowired
	UserService userService;

	RestTemplate restTemplate = new RestTemplate();
	
	private BCryptPasswordEncoder bCryptPasswordEncoder;


	@GetMapping(value = "/")
	public ResponseEntity<String> index() {
		String message = "User API Service";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@GetMapping(value = "/test")
	public ResponseEntity<String> test() {
		String message = "User API Service";
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
	
	@PostMapping(value = "/adduser", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> createUser(@RequestBody String json) {

		String result;
		String message = "";
		String uname = "";
		int code = -1;
		try {
			result = java.net.URLDecoder.decode(json, "UTF-8");
			uname = saveUser(result);
			message = "User: " + uname + " successfully created.";
			code = 200;
		} catch (UnsupportedEncodingException e) {
			message = e.getMessage();
			LOGGER.severe("Error: " + e.getMessage());
			code = 301;
		} catch (JSONException e) {
			message = e.getMessage();
			LOGGER.severe("Error: " + e.getMessage());
			code = 302;
		}

		HashMap<String, Object> usermap = new HashMap<String, Object>();
		usermap.put("code", code);
		usermap.put("username", uname);
		usermap.put("message", message);

		return new ResponseEntity<HashMap<String, Object>>(usermap, HttpStatus.OK);
	}
	
	@PostMapping(value = "/activate", headers = "Accept=application/json", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HashMap<String, Object>> activateBasic(@RequestBody String json) {

		String result;
		String message = "";
		String secret = "";
		String username = "";
		int code = -1;
		List<String> roles = null;
		
		try {
			result = java.net.URLDecoder.decode(json, "UTF-8");
			JSONObject jSONObject = new JSONObject(result);
			username = jSONObject.getString("username");
			UserByLogin userByLogin = userService.findByKeyLogin(username);	
			secret = StringUtil.randomString(32);
			String userid = userByLogin.getUserId();
			roles = userByLogin.getRoles();
			UserById userById = userService.findByKeyId(userid);
			userByLogin.setSecret(secret);
			userById.setSecret(secret);
			userService.createUser(userById);
			userService.createUser(userByLogin);
			LOGGER.info(message);
			code = 200;
		} catch (Exception e) {
			message = e.getMessage();
			LOGGER.severe("Error: " + e.getMessage());
			code = 301;
		} 
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("secret", secret);
		map.put("roles", roles);
				
		return new ResponseEntity<HashMap<String, Object>>(map, HttpStatus.OK);
	}
	
	private String saveUser(String json) {

		LOGGER.info("User Create Request");

		String message = "";
		try {
			JSONObject jSONObject = new JSONObject(json);
			UUID uuid = UUID.randomUUID();
			String id = uuid.toString();
			String uname = "";
			String fname = jSONObject.getString("firstname");
			String lname = jSONObject.getString("lastname");
			String phone = null;
			String email = null;
			String gender = null;
			String secret = null;
			String pin = null;
			String password = null;
			if (jSONObject.has("password")) {
				secret = jSONObject.getString("password");
			}
			if (jSONObject.has("email")) {
				email = jSONObject.getString("email");
				bCryptPasswordEncoder = new BCryptPasswordEncoder();
				password = bCryptPasswordEncoder.encode(secret);
			}
			if (jSONObject.has("phone")) {
				phone = jSONObject.getString("phone");
				pin = StringUtil.randomNumber(4);
				bCryptPasswordEncoder = new BCryptPasswordEncoder();
				password = bCryptPasswordEncoder.encode(pin);
			}
			if (jSONObject.has("gender")) {
				gender = jSONObject.getString("gender").toUpperCase();
			}
			String country = jSONObject.getJSONObject("location").getString("country");
			String region = jSONObject.getJSONObject("location").getString("region");
			String territory = jSONObject.getJSONObject("location").getString("territory");
			String zone = jSONObject.getJSONObject("location").getString("zone");
			JSONArray rolesArray = jSONObject.getJSONArray("roles");
			List<String> rolelist = new ArrayList<String>();
			for (int i = 0; i < rolesArray.length(); i++) {
				rolelist.add(rolesArray.getString(i));
			}

			String contactmethod = jSONObject.getString("contactmethod");
			if ("SMS".equalsIgnoreCase(contactmethod)) {
				uname = phone;
			} else {
				uname = email;
			}
			Map<String, String> locmap = new HashMap<String, String>();
			locmap.put("country", country);
			locmap.put("region", region);
			locmap.put("territory", territory);
			locmap.put("zone", zone);
			DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
			Date date = new Date();
			String curdate = dateFormat.format(date);


			UserKeyLogin userKeyLogin = new UserKeyLogin();
			userKeyLogin.setLogin(uname);
			UserByLogin userByLogin = new UserByLogin();
			userByLogin.setKey(userKeyLogin);
			userByLogin.setUserId(id);
			userByLogin.setFirstname(fname);
			userByLogin.setLastname(lname);
			userByLogin.setEmail(email);
			userByLogin.setPhone(phone);
			userByLogin.setPassword(password);
			userByLogin.setCreated(curdate);
			userByLogin.setRoles(rolelist);
			userByLogin.setGender(gender);
			userByLogin.setProfileimage(DEFAULT_IMAGE);
			userByLogin.setLocation(locmap);

			UserKeyId userKeyId = new UserKeyId();
			userKeyId.setId(id);
			UserById userById = new UserById();
			userById.setKey(userKeyId);
			userById.setUsername(uname);
			userById.setFirstname(fname);
			userById.setLastname(lname);
			userById.setEmail(email);
			userById.setPhone(phone);
			userById.setPassword(password);
			userById.setCreated(curdate);
			userById.setRoles(rolelist);
			userById.setGender(gender);
			userById.setProfileimage(DEFAULT_IMAGE);
			userById.setLocation(locmap);

			UserKeyGender userKeyGender = new UserKeyGender();
			userKeyGender.setUserId(id);
			userKeyGender.setGender(gender);
			UserByGender userByGender = new UserByGender();
			userByGender.setKey(userKeyGender);
			userByGender.setUsername(uname);
			userByGender.setFirstname(fname);
			userByGender.setLastname(lname);
			userByGender.setEmail(email);
			userByGender.setPhone(phone);
			userByGender.setPassword(password);
			userByGender.setCreated(curdate);
			userByGender.setRoles(rolelist);
			userByGender.setProfileimage(DEFAULT_IMAGE);
			userByGender.setLocation(locmap);

			UserKeyLocation userKeyCountry = new UserKeyLocation();
			userKeyCountry.setUserid(id);			
			userKeyCountry.setName(country);
			UserByLocation userByCountry = new UserByLocation();
			userByCountry.setKey(userKeyCountry);
			userByCountry.setUsername(uname);
			userByCountry.setFirstname(fname);
			userByCountry.setLastname(lname);
			userByCountry.setEmail(email);
			userByCountry.setPhone(phone);
			userByCountry.setPassword(password);
			userByCountry.setCreated(curdate);
			userByCountry.setRoles(rolelist);
			userByCountry.setProfileimage(DEFAULT_IMAGE);
			userByCountry.setLocation(locmap);
			
			UserKeyLocation userKeyRegion = new UserKeyLocation();
			userKeyRegion.setUserid(id);			
			userKeyRegion.setName(region);
			UserByLocation userByRegion = new UserByLocation();
			userByRegion.setKey(userKeyRegion);
			userByRegion.setUsername(uname);
			userByRegion.setFirstname(fname);
			userByRegion.setLastname(lname);
			userByRegion.setEmail(email);
			userByRegion.setPhone(phone);
			userByRegion.setPassword(password);
			userByRegion.setCreated(curdate);
			userByRegion.setRoles(rolelist);
			userByRegion.setProfileimage(DEFAULT_IMAGE);
			userByRegion.setLocation(locmap);
			
			UserKeyLocation userKeyTerritory = new UserKeyLocation();
			userKeyTerritory.setUserid(id);			
			userKeyTerritory.setName(territory);
			UserByLocation userByTerritory = new UserByLocation();
			userByTerritory.setKey(userKeyTerritory);
			userByTerritory.setUsername(uname);
			userByTerritory.setFirstname(fname);
			userByTerritory.setLastname(lname);
			userByTerritory.setEmail(email);
			userByTerritory.setPhone(phone);
			userByTerritory.setPassword(password);
			userByTerritory.setCreated(curdate);
			userByTerritory.setRoles(rolelist);
			userByTerritory.setProfileimage(DEFAULT_IMAGE);
			userByTerritory.setLocation(locmap);
			
			UserKeyLocation userKeyZone = new UserKeyLocation();
			userKeyZone.setUserid(id);			
			userKeyZone.setName(zone);
			UserByLocation userByZone = new UserByLocation();
			userByZone.setKey(userKeyZone);
			userByZone.setUsername(uname);
			userByZone.setFirstname(fname);
			userByZone.setLastname(lname);
			userByZone.setEmail(email);
			userByZone.setPhone(phone);
			userByZone.setPassword(password);
			userByZone.setCreated(curdate);
			userByZone.setRoles(rolelist);
			userByZone.setProfileimage(DEFAULT_IMAGE);
			userByZone.setLocation(locmap);

			
			userService.createUser(userById);
			userService.createUser(userByLogin);
			userService.createUser(userByGender);
			userService.createUser(userByCountry);
			userService.createUser(userByRegion);
			userService.createUser(userByTerritory);
			userService.createUser(userByZone);
			
			
			message = id;
			LOGGER.info(message);

		} catch (JSONException e) {
			LOGGER.severe("AGENT_CREATE_REQUEST_ERROR " + e.getMessage());
			message = e.getMessage();
		} catch (NullPointerException e) {
			message = e.getMessage();
		} catch (Exception e) {
			message = e.getMessage();
		}

		return message;
	}

	
}
