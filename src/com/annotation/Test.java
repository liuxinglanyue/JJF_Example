package com.annotation;

import java.lang.reflect.InvocationTargetException;

public class Test {

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException,
			NoSuchMethodException, InstantiationException {

		AnnotationParseUtil parse = new AnnotationParseUtil();

		Object[] params = new Object[10];

		params[0] = "123@qq.com";

		UseCase useCase = new UseCase();

		parse.parseMethod(useCase, UseCase.class.getDeclaredMethods()[0],
				params);

	}

}
