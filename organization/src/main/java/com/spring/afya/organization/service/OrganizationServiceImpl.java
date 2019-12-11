package com.spring.afya.organization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.afya.organization.model.CategoryByName;
import com.spring.afya.organization.model.OrganizationById;
import com.spring.afya.organization.repository.CategoryRepository;
import com.spring.afya.organization.repository.OrganizationRepository;

/**
 * Service for Database operations
*/

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	OrganizationRepository organizationRepository;
	
	
	@Override
	public void addOrganization(OrganizationById organizationById) {
		organizationRepository.save(organizationById);
	}

	@Override
	public OrganizationById findByKeyId(String id) {
		return organizationRepository.findByKeyId(id);
	}

	@Override
	public List<OrganizationById> findAll() {
		return organizationRepository.findAll();
	}

	@Override
	public List<CategoryByName> findCategoryAll() {
		return categoryRepository.findAll();
	}

	@Override
	public void addCategory(CategoryByName categoryByName) {
		categoryRepository.save(categoryByName);		
	}

	@Override
	public CategoryByName findByName(String name) {
		return categoryRepository.findByName(name);
	}

}
