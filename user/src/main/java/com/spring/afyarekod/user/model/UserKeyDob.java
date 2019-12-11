package com.spring.afyarekod.user.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class UserKeyDob implements Serializable {

	private static final long serialVersionUID = 8149683037350550751L;

	@PrimaryKeyColumn(name = "dob", type = PARTITIONED)
	private String dob;

	@PrimaryKeyColumn(name = "userid", ordinal = 0)
	private String userid;


	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	
}