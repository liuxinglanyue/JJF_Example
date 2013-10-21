package com.cache.fqueue;

import com.google.code.fqueue.FQueue;

public class FQueueDemo {
	public static void main(String[] args) throws Exception {
		FQueue fQueue = new FQueue("db");
        StringBuilder sb = new StringBuilder();
        int length = 10; //Integer.parseInt(args[0]);
        for (int i = 0; i < length; i++) {
            sb.append("a");
        }
        byte[] data = sb.toString().getBytes();
        fQueue.add(data);// 预热一下
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            fQueue.add(data);
        }
        System.out.println(100000000.0 / ((System.currentTimeMillis() - start) / 1000) + "qps");
        fQueue.close();
	}
}
