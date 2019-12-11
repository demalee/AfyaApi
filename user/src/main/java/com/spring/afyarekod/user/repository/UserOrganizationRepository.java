package com.spring.afyarekod.user.repository;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;

import com.spring.afyarekod.user.model.UserByOrganization;
import com.spring.afyarekod.user.model.UserKeyOrganization;


public interface UserOrganizationRepository extends CassandraRepository<UserByOrganization, UserKeyOrganization> {
	public List<UserByOrganization> findByKeyId(final String code);
}