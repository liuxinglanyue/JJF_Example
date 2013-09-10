package com.decode;

import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5Test {
	public static void main(String[] args) {
		String text = "FD151D8B-1";
		System.out.println(md5Hex(text));
		System.out.println(shaHex(text));
		System.out.println(md5ToBinary(text));
	}
	
	public static String md5Hex(String text) {
		if(null == text) {
			return null;
		}
		return DigestUtils.md5Hex(text);
	}
	
	public static String shaHex(String text) {
		if(null == text) {
			return null;
		}
		return DigestUtils.shaHex(text);
	}
	
	public static String md5ToBinary(String text) {
		if(null == text) {
			return null;
		}
		return new String(BinaryCodec.toAsciiBytes(DigestUtils.md5(text)));
	}
}
