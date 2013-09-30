package com.jvm;

import java.lang.ref.SoftReference;

public class SoftReferenceTest {
	private final static ThreadLocal<SoftReference<String>> bufLocal = new ThreadLocal<SoftReference<String>>();
	
	public static void main(String[] args) {
		SoftReference<String> strSoftReference = new SoftReference<String>("jkfds");
		bufLocal.set(strSoftReference);
		SoftReference<String> softReference = bufLocal.get();
		System.out.println(softReference.get());
	}
}
