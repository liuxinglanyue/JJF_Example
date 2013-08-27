package com.util;

import com.util.entity.Content;


public class BaseTest {
	public static void main(String[] args) throws Exception {
		Content srcContent = new Content();
		srcContent.setApple("apple");
		srcContent.pig = "kk";
		Content destContent = new Content();
		//BeanUtils.copyProperties(destContent, srcContent);
		BaseUtil.copyProperties(destContent, srcContent, true);
		System.out.println(destContent.pig + "  " + destContent.getApple());
	}
}
