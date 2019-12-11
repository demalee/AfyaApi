package com.spring.afyarekod.user.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.spring.afyarekod.user.model.UserByDob;
import com.spring.afyarekod.user.model.UserKeyDob;

public interface UserDobRepository extends CassandraRepository<UserByDob, UserKeyDob> {
	
}