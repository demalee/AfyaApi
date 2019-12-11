package com.spring.afyarekod.user.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.spring.afyarekod.user.model.UserByGender;
import com.spring.afyarekod.user.model.UserKeyGender;


public interface UserGenderRepository extends CassandraRepository<UserByGender, UserKeyGender> {
	public List<UserByGender> findByKeyGender(final String gender);
}