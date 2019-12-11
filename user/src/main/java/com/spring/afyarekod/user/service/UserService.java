package com.spring.afyarekod.user.service;

import java.util.List;

import com.spring.afyarekod.user.model.UserByGender;
import com.spring.afyarekod.user.model.UserById;
import com.spring.afyarekod.user.model.UserByLocation;
import com.spring.afyarekod.user.model.UserByLogin;
import com.spring.afyarekod.user.model.UserByOrganization;

public interface UserService {
	
	public void createUser(UserByLogin userByLogin);
	public void createUser(UserById userById);
	public void createUser(UserByGender userByGender);
	public void createUser(UserByOrganization userByOrganization);
	public void createUser(UserByLocation userByLocation);
	
	public UserByLogin findByKeyLogin(final String name);
	public UserById findByKeyId(final String userid);
	public List<UserByLocation> findByUsersByLocationName(String name);
	public List<UserByOrganization> findByUsersByOrganization(String code);
}
