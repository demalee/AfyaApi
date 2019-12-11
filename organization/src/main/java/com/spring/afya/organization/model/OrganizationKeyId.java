package com.spring.afya.organization.model;

import org.springframework.data.mongodb.core.index.Indexed;

public class OrganizationKeyId {

	@Indexed
	private String id;
	
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
}
