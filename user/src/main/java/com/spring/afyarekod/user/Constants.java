package com.spring.afyarekod.user;

public class Constants {
	public static final String SECRET = "SecretKeyToGenJWTs";
	public static final long EXPIRATION_TIME = 864000000; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String CREATE_USER_URL = "/adduser";
	public static final String ACTIVATE_USER_URL = "/activate";
}
