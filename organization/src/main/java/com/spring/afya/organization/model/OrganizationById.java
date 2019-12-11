package com.spring.afya.organization.model;

import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "organization_by_id")
public class OrganizationById {

	@Id
	private OrganizationKeyId key;

	@Indexed(unique = false)
	private String category;

	@Indexed(unique = false)
	private String name;

	@Indexed(unique = false)
	private String address;

	@Indexed(unique = false)
	private String phone;

	@Indexed(unique = false)
	private String email;
	
	private Map<String,String> gps;
	
	private Map<String,String> location;

	private List<String> attributes;
	
	private List<String> services;

	public OrganizationKeyId getKey() {
		return key;
	}

	public void setKey(OrganizationKeyId key) {
		this.key = key;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<String> attributes) {
		this.attributes = attributes;
	}

	public List<String> getServices() {
		return services;
	}

	public void setServices(List<String> services) {
		this.services = services;
	}
	

	public Map<String, String> getGps() {
		return gps;
	}

	public void setGps(Map<String, String> gps) {
		this.gps = gps;
	}

	public Map<String, String> getLocation() {
		return location;
	}

	public void setLocation(Map<String, String> location) {
		this.location = location;
	}
	
}
