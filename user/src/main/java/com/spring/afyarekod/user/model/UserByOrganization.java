package com.spring.afyarekod.user.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user_by_organizaion")
public class UserByOrganization implements Serializable {

	private static final long serialVersionUID = 5760464253675802076L;

	@PrimaryKey
	private UserKeyOrganization key;
	
	@Column("username")
	private String username;

	@Column("phone")
	private String phone;

	@Column("email")
	private String email;

	@Column("firstname")
	private String firstname;

	@Column("lastname")
	private String lastname;

	@Column("gender")
	private String gender;

	@Column("created")
	private String created;

	@Column("location")
	private Map<String, String> location;
	
	@Column("organization")
	private Map<String, String> organization;

	@Column("role")
	private List<String> role;

	@Column("contactmethod")
	private String contactmethod;

	@Column("profileimage")
	private String profileimage;

	public UserKeyOrganization getKey() {
		return key;
	}

	public void setKey(UserKeyOrganization key) {
		this.key = key;
	}	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}
	
	public Map<String, String> getOrganization() {
		return organization;
	}

	public void setOrganization(Map<String, String> organization) {
		this.organization = organization;
	}

	public Map<String, String> getLocation() {
		return location;
	}

	public void setLocation(Map<String, String> location) {
		this.location = location;
	}

	public String getContactmethod() {
		return contactmethod;
	}

	public void setContactmethod(String contactmethod) {
		this.contactmethod = contactmethod;
	}

	public String getProfileimage() {
		return profileimage;
	}

	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

}
