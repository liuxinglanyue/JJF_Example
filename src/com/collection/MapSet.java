package com.collection;

import java.util.HashMap;
import java.util.Map;

public class MapSet {
	public static void main(String[] args) {
		Map<String, String> maps = new HashMap<String, String>();
		maps.put("123", "3ht");
		maps.put("1233", "3");
		maps.put("123", "3fsd");
		for(String valueString : maps.values()) {
			System.out.println(valueString);
		}
	}
}
