package com.spring.afya.location.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import com.spring.afya.location.model.LocationByName;

public interface LocationRepository extends MongoRepository<LocationByName, String>{
	
	LocationByName findByName(String name);
	List<LocationByName> findByParent(String parent);

}
