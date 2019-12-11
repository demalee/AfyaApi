package com.spring.afya.location.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.afya.location.model.LocationType;

public interface LocationTypeRepository extends MongoRepository<LocationType, String>{
	
	List<LocationType> findByName(String name);
	List<LocationType> findAll();
}
