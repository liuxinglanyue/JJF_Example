package com.decode;

import org.apache.commons.codec.binary.Base64;

/**
 * Base64 例子
 * @author Jiao JianFeng
 *
 */
public class Base64Test {
	public static void main(String[] args) {
		String imageInfo = "20371700672e09c1039972bd0f2db701";
		System.out.println(imageInfo = encodeStr(imageInfo));
		System.out.println(decodeStr(imageInfo));
	}
	
	/**
	 * base64 编码
	 * @param text
	 * @return
	 */
	public static String encodeStr(String text) {
		if(null == text) {
			return null;
		}
		byte[] b = text.getBytes();
		Base64 base64 = new Base64();
		b = base64.encode(b);
		return new String(b);
	}
	
	/**
	 * base64解码
	 * @param text
	 * @return
	 */
	public static String decodeStr(String text) {
		if(null == text) {
			return null;
		}
		byte[] b = text.getBytes();
		Base64 base64 = new Base64();
		b = base64.decode(b);
		return new String(b);
	}
}
