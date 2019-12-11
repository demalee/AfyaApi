package com.spring.afyarekod.user.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user_by_gender")
public class UserByGender implements Serializable {

	private static final long serialVersionUID = 1655657592377156932L;

	@PrimaryKey
	private UserKeyGender key;

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

	@Column("password")
	private String password;
	
	@Column("secret")
	private String secret;

	@Column("created")
	private String created;

	@Column("location")
	private Map<String, String> location;
	
	@Column("organization")
	private Map<String, String> organization;

	@Column("rolelist")
	private List<String> rolelist;

	@Column("profileimage")
	private String profileimage;
		
	@Column("fcmtoken")
	private String fcmtoken;
	
	public UserKeyGender getKey() {
		return key;
	}

	public void setKey(UserKeyGender key) {
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

	public List<String> getRoles() {
		return rolelist;
	}

	public void setRoles(List<String> role) {
		this.rolelist = role;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
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

	public String getProfileimage() {
		return profileimage;
	}

	public void setProfileimage(String profileimage) {
		this.profileimage = profileimage;
	}

	public String getFcmtoken() {
		return fcmtoken;
	}

	public void setFcmtoken(String fcmtoken) {
		this.fcmtoken = fcmtoken;
	}
}
