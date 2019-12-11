package com.spring.afyarekod.user.model;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("role_by_id")
public class RoleById implements Serializable {

	private static final long serialVersionUID = 6438472863794496280L;

	@PrimaryKey
	private RoleKeyId key;
	
	@Column("name")
	private String name;

	@Column("created")
	private String created;

	public RoleKeyId getKey() {
		return key;
	}

	public void setKey(RoleKeyId key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}	
	
	
}
