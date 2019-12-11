package com.spring.afyarekod.user.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class UserKeyOrganization implements Serializable {


	private static final long serialVersionUID = -7002759697417744197L;

	@PrimaryKeyColumn(name = "organizationid", type = PARTITIONED)
	private String id;

	@PrimaryKeyColumn(name = "userid", ordinal = 0)
	private String userid;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}
	
}