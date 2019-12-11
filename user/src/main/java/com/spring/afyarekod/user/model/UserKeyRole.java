package com.spring.afyarekod.user.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class UserKeyRole implements Serializable {

	private static final long serialVersionUID = -9192692826738739328L;

	@PrimaryKeyColumn(name = "role", type = PARTITIONED)
	private String role;

	@PrimaryKeyColumn(name = "userid", ordinal = 0)
	private String userid;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}	

}