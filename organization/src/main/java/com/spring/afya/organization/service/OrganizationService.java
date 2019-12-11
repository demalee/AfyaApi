package com.spring.afya.organization.service;

import java.util.List;

import com.spring.afya.organization.model.CategoryByName;
import com.spring.afya.organization.model.OrganizationById;

public interface OrganizationService {

	public void addCategory(CategoryByName categoryByName);
	public CategoryByName findByName(String name);
	public List<CategoryByName> findCategoryAll();
	
	public void addOrganization(OrganizationById organizationById);
	public OrganizationById findByKeyId(String id); 		
	public List<OrganizationById> findAll();
}
