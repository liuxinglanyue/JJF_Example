package com.String;

import java.util.ArrayList;
import java.util.List;

public class List2String {
	public static void main(String[] args) {
		List<Long> ids = new ArrayList<Long>();
		ids.add(23l);
		ids.add(455l);
		
		System.out.println(ids.toArray());
	}
}
