package com.String;

public class StringTest {
	public static void main(String[] args) {
		String tt = "我们是粤语321084x768.jpg";
		String suf = "1084x768.";
		int number = -1;
		if(-1 != (number = tt.indexOf(suf))) {
			System.out.println(number);
			System.out.println(tt.substring(0, number));
			System.out.println(tt.substring(number));
		}
	}
}