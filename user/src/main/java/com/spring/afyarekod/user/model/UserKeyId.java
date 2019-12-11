package com.spring.afyarekod.user.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class UserKeyId implements Serializable {

	private static final long serialVersionUID = 5970982458798013393L;
	
	@PrimaryKeyColumn(name = "userid", type = PARTITIONED)
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
}