package com.spring.afya.organization.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.afya.organization.model.CategoryByName;

public interface CategoryRepository extends MongoRepository<CategoryByName, String> {
	
	CategoryByName findByName(String name);
}
