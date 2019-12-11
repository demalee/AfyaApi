package com.spring.afyarekod.user.model;

import static org.springframework.data.cassandra.core.cql.PrimaryKeyType.PARTITIONED;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;

@PrimaryKeyClass
public class CountryKeyCode implements Serializable {

	private static final long serialVersionUID = 2913074294425409901L;

	@PrimaryKeyColumn(name = "code", type = PARTITIONED)
	private String code;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}