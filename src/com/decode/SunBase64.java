package com.decode;

import org.apache.commons.codec.digest.DigestUtils;

import sun.misc.BASE64Encoder;

public class SunBase64 {
	public static void main(String[] args) {
		System.out.println(new BASE64Encoder().encode(DigestUtils.md5("FD151D8B-1")));
	}
}
