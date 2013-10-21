package com.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListRemove {
	public static void main(String[] args) {
		String[] idLongs = new String[]{"fd","32"};
		List<String> privilegeIds = Arrays.asList(idLongs);
		/*privilegeIds.add("fd");
		privilegeIds.add("32");*/
		((ArrayList)privilegeIds).remove("fd");
		/*Long[] idLongs = new Long[]{32l,45l};
		List<Long> ids = Arrays.asList();
		for(Long id : ids ) {
			System.out.println(id);
		}
		ids.add(34l);
		ids.add(32l);
		ids.remove(32l);*/
	}
}
