package com.annotation;

public class AnnotationTest {

	public static void main(String[] args) throws Exception {
		// 生成代理类class文件的代码一定在放在main方法，用JUnit生成不了
		AnnotationUtils.saveProxyClassInHardDisk();
		AnnotationUtils.printAnnotationInfo();
	}
}