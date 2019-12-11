package com.spring.afyarekod.user.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.spring.afyarekod.user.model.UserByLogin;
import com.spring.afyarekod.user.model.UserKeyLogin;

public interface UserLoginRepository extends CassandraRepository<UserByLogin, UserKeyLogin> {
	public UserByLogin findByKeyLogin(final String name);

	public List<UserByLogin> findAll();
}