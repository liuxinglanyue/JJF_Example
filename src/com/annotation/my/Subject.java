package com.annotation.my;

public class Subject {
	@Auto(autoT = 2, name = "jjf")
	private void request(int auto, String name) {
		System.out.println("autoT= " + auto + "  name=" + name);
	}
}
