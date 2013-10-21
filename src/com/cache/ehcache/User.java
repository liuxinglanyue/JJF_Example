package com.cache.ehcache;

import java.io.Serializable;

public class User implements Serializable {
	private static final long serialVersionUID = -6227376640818226890L;
	
	private String name;
	private String code;

	public User(String name, String code) {
		super();
		this.name = name;
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}