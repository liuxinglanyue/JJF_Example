package com.annotation;

public class User {

	@RequestMapping("name")
	public String add(String name) {
		return "add";
	}
}