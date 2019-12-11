package com.spring.afyarekod.user.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.spring.afyarekod.user.model.UserById;
import com.spring.afyarekod.user.model.UserKeyId;

public interface UserIdRepository extends CassandraRepository<UserById, UserKeyId> {
	public UserById findByKeyId(final String id);
	public List<UserById> findAll();
}