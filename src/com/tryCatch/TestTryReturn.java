package com.tryCatch;

public class TestTryReturn {

	public static void main(String[] args) {

		TestTryReturn ttr = new TestTryReturn();
		System.out.println(ttr.launch());
	}

	public int launch() {
		try {
			System.out.println("run in try region");
			return 0;

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("run in catch region");
			return -1;
		} finally {
			System.out.println("run in finally region");

		}
	}
}