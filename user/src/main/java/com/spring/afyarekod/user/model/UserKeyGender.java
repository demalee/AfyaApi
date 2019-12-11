package com.spring.afyarekod.user.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class UserKeyGender implements Serializable {

	private static final long serialVersionUID = 1811810502888850789L;

	@PrimaryKeyColumn(name = "gender", type = PARTITIONED)
	private String gender;

	@PrimaryKeyColumn(name = "userid", ordinal = 0)
	private String userid;

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUserId() {
		return userid;
	}

	public void setUserId(String userid) {
		this.userid = userid;
	}

}