package com.api;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeSupport {

	private static Unsafe unsafe;

	static {
		Field field;
		try {
			// 由反编译Unsafe类获得的信息
			field = Unsafe.class.getDeclaredField("theUnsafe");
			field.setAccessible(true);
			// 获取静态属性,Unsafe在启动JVM时随rt.jar装载
			unsafe = (Unsafe) field.get(null);
		} catch (Exception e) {
		}
	}

	/**
	 * 获取{@link Unsafe }
	 */
	public static Unsafe getInstance() {
		return unsafe;
	}

	public static void main(String[] args) {

	}
}