package com.acm;

import java.util.Scanner;

public class Sample {

	public static int f(int x, int y) {
		return x * 10;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			System.out.println(f(x, y));
		}
	}

}
