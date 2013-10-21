package com.thread;

import java.util.concurrent.*;

public class CountDownLatchDemo implements Runnable {
	private CountDownLatch cd;
	private int id;

	public CountDownLatchDemo(CountDownLatch cd, int id) {
		this.cd = cd;
		this.id = id;
	}

	public void run() {
		try {
			TimeUnit.MILLISECONDS.sleep(id * 1000);
			cd.countDown();
			System.out.println("countDown " + id);
			cd.await();

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("line " + id + " Dead");
	}

	public static void main(String[] args) {
		final CountDownLatch cd = new CountDownLatch(10);
		final ExecutorService es = Executors.newCachedThreadPool();

		for (int i = 0; i < 9; i++) {
			es.execute(new CountDownLatchDemo(cd, i));
		}
		es.execute(new Runnable() {
			@Override
			public void run() {
				try {
					TimeUnit.MILLISECONDS.sleep(9 * 1000);
					cd.countDown();
					System.out.println("countDown " + 9);
					cd.await();
					for (int i = 10; i < 20; i++) {// 这里id为10-20区别于前10个进程，但是等待的时间稍有点长
						es.execute(new CountDownLatchDemo(cd, i));
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		try {
			TimeUnit.SECONDS.sleep(15);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		es.shutdown();
		System.out.println("main Dead");
	}
}
