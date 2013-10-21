package com.annotation;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;

public class AnnotationParseUtil {

	/*public void parseMethod(Class clazz) throws IllegalArgumentException,
			IllegalAccessException,

			InvocationTargetException, SecurityException,
			NoSuchMethodException, InstantiationException {

		Object obj = clazz.getConstructor(new Class[] {}).newInstance(
				new Object[] {});

		for (Method method : clazz.getDeclaredMethods()) {

			method.getAnnotation(DateFormat.class);
			DateFormat df = method.getAnnotation(DateFormat.class);

			String name = "";

			if (df != null) {

				name = df.dateType().value;

				method.invoke(obj, name);

			}

		}

	}*/


	public void parseMethod(Object proxy, Method method, Object[] args)
			throws IllegalArgumentException,

			IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException,

			InstantiationException {


		Regex df = method.getAnnotation(Regex.class);

		String name = "";

		if (df != null) {

			name = df.regexRule().value;

			method.invoke(proxy, args[0], name);

		}

	}

}
