package com.spring.afyarekod.user.security;

import static java.util.Collections.emptyList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.spring.afyarekod.user.model.AppUser;
import com.spring.afyarekod.user.model.UserByLogin;
import com.spring.afyarekod.user.repository.UserLoginRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserLoginRepository userloginRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {

		UserByLogin userByLogin = userloginRepository.findByKeyLogin(username);
		AppUser appUser = new AppUser(userByLogin.getKey().getLogin(), userByLogin.getPassword(),
				userByLogin.getRoles());
		return new User(appUser.getUsername(), appUser.getPassword(), emptyList());
	}

}
