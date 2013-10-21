package com.annotation;

public enum RegexRule {

	EMAIL("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"),


	NUMBER("^[0-9]*$");

	public String value;

	RegexRule(String value) {

		this.value = value;

	}

}
