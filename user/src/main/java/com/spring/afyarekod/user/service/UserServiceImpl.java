package com.spring.afyarekod.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.afyarekod.user.model.UserByGender;
import com.spring.afyarekod.user.model.UserById;
import com.spring.afyarekod.user.model.UserByLocation;
import com.spring.afyarekod.user.model.UserByLogin;
import com.spring.afyarekod.user.model.UserByOrganization;
import com.spring.afyarekod.user.repository.UserGenderRepository;
import com.spring.afyarekod.user.repository.UserIdRepository;
import com.spring.afyarekod.user.repository.UserLocationRepository;
import com.spring.afyarekod.user.repository.UserLoginRepository;
import com.spring.afyarekod.user.repository.UserOrganizationRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserLoginRepository userloginRepository;
	
	@Autowired
	UserIdRepository userIdRepository;
	
	@Autowired
	UserGenderRepository userGenderRepository;
	
	@Autowired
	UserOrganizationRepository userOrganizationRepository;
	
	@Autowired
	UserLocationRepository userLocationRepository;


	@Override
	public UserByLogin findByKeyLogin(String name) {
		return userloginRepository.findByKeyLogin(name);
	}

	@Override
	public void createUser(UserByLogin user) {
		userloginRepository.save(user);
	}

	@Override
	public void createUser(UserById userById) {
		userIdRepository.save(userById);		
	}

	@Override
	public void createUser(UserByGender userByGender) {
		userGenderRepository.save(userByGender);		
	}

	@Override
	public void createUser(UserByOrganization userByOrganization) {
		userOrganizationRepository.save(userByOrganization);		
	}

	@Override
	public UserById findByKeyId(String userid) {
		return userIdRepository.findByKeyId(userid);
	}

	@Override
	public void createUser(UserByLocation userByLocation) {
		userLocationRepository.save(userByLocation);		
	}

	@Override
	public List<UserByLocation> findByUsersByLocationName(String name) {
		return userLocationRepository.findByKeyName(name);
	}

	@Override
	public List<UserByOrganization> findByUsersByOrganization(String code) {
		return userOrganizationRepository.findByKeyId(code);
	}	

}
