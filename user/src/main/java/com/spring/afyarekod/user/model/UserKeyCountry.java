package com.spring.afyarekod.user.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class UserKeyCountry implements Serializable {

	private static final long serialVersionUID = -9192692826738739328L;

	@PrimaryKeyColumn(name = "code", type = PARTITIONED)
	private String code;
	
	@PrimaryKeyColumn(name = "region", type = PARTITIONED)
	private String region;

	@PrimaryKeyColumn(name = "userid", ordinal = 0)
	private String userid;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getUserId() {
		return userid;
	}

	public void setUserId(String userid) {
		this.userid = userid;
	}

}