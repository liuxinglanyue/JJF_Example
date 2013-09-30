package com.tryCatch;

public class TestTryThrowReturn {
	private static String order;

	public static void main(String[] args) {

		TestTryThrowReturn ttr = new TestTryThrowReturn();
		order = " 1 ";
		System.out.println(ttr.launch());
		order = order + " 7 ";
		System.out.println(order);// 1  2  3  4  5  6  7 
	}

	public int launch() {
		try {
			order = order + " 2 ";
			System.out.println("run in try region");
			order = order + " 3 ";
			throw new Exception();

		} catch (Exception e) {
			order = order + " 4 ";
			e.printStackTrace();
			order = order + " 5 ";
			System.out.println("run in catch region");
			return -1;
		} finally {
			order = order + " 6 ";
			System.out.println("run in finally region");

		}
	}
}