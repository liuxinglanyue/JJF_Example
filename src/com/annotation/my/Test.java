package com.annotation.my;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
	public static void main(String[] args) throws ClassNotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException, SecurityException, NoSuchMethodException, InstantiationException {
		//System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
		for(Method method : Subject.class.getDeclaredMethods()) {
			//System.out.println(method.getName());
			Auto auto = method.getAnnotation(Auto.class);
			System.out.println("autoT= " + auto.autoT() + "  name=" + auto.name());
		}
		Class<?> class1 = Class.forName("com.annotation.my.Subject");
		Method method = class1.getDeclaredMethod("request", new Class[]{int.class, String.class});
		method.setAccessible(true);
		method.invoke(class1.newInstance(), new Object[]{4, "jjftto"});
	}
}
