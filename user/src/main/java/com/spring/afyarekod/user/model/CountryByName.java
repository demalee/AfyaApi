package com.spring.afyarekod.user.model;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("country_by_name")
public class CountryByName implements Serializable {

	private static final long serialVersionUID = -8677645533301302615L;

	@PrimaryKey
	private CountryKeyCode key;

	@Column("name")
	private String name;
	
	@Column("regions")
	private List<String> regions;

	public CountryKeyCode getKey() {
		return key;
	}

	public void setKey(CountryKeyCode key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getRegions() {
		return regions;
	}

	public void setRegions(List<String> regions) {
		this.regions = regions;
	}	

}
