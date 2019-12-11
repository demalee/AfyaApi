package com.spring.afyarekod.user.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.spring.afyarekod.user.model.UserByCountry;
import com.spring.afyarekod.user.model.UserKeyCountry;


public interface UserCountryRepository extends CassandraRepository<UserByCountry, UserKeyCountry> {
	public List<UserByCountry> findByKeyCode(final String code);
	public List<UserByCountry> findByKeyCodeAndKeyRegiom(final String code);
	public UserByCountry findByKeyCodeAndKeyUserid(final String country,final String userid);
}