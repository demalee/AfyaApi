package com.spring.afyarekod.user.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.spring.afyarekod.user.model.UserByLocation;
import com.spring.afyarekod.user.model.UserKeyLocation;


public interface UserLocationRepository extends CassandraRepository<UserByLocation, UserKeyLocation> {
	public List<UserByLocation> findByKeyName(final String name);
}