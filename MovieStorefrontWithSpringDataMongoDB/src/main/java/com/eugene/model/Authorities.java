package com.eugene.model;

import org.springframework.security.core.GrantedAuthority;

public class Authorities implements GrantedAuthority{

	private Integer authoritiesId;
	
	private User users;

	private String authority;

	/** default constructor */
	public Authorities() {
	}

	public User getUsers() {
		return this.users;
	}

	public void setUsers(User users) {
		this.users = users;
	}

	public String getAuthority() {
		return this.authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public Integer getAuthoritiesId() {
		return authoritiesId;
	}

	public void setAuthoritiesId(Integer authoritiesId) {
		this.authoritiesId = authoritiesId;
	}
}
