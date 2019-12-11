package com.spring.afya.organization.repository;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.spring.afya.organization.model.OrganizationById;
import com.spring.afya.organization.model.OrganizationKeyId;


public interface OrganizationRepository extends MongoRepository<OrganizationById, OrganizationKeyId>  {

	OrganizationById findByKeyId(String id);
}
