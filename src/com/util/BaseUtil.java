package com.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;

public class BaseUtil {
	private final static String GETER_START = "get";
	private final static String SET_START = "set";
	
	/**
	 * 制对象的属性  boo==true源对象的属性值为0或者null的时候不复制
	 * @param dest
	 * @param orig
	 * @param boo
	 * @throws Exception
	 */
	public static void copyProperties(Object dest, Object orig, boolean boo) throws Exception{
		Class<?> clazzOrig = orig.getClass();
		Class<?> clazzDest = dest.getClass();
		Field[] fList = clazzOrig.getFields(); // 此方法可以取public参数包括父类
		//if (fList == null || fList.length == 0) {
			fList = clazzOrig.getDeclaredFields(); // 此方法可取所有参数，但不能取父类
		//}
		Method method = null;
		Object value = null;
		for(Field field : fList){
			String proName = field.getName();
			Class<?> type = field.getType();
			try {
				method = clazzOrig.getMethod(GETER_START + StringUtils.capitalize(proName));
			} catch (Exception e) {
				continue;
			}
			value = method.invoke(orig);
			if(boo){
				if(value==null||"0".equals(value.toString())){
					continue;
				}
			}
			try {
				method = clazzDest.getMethod(SET_START + StringUtils.capitalize(proName),type);
				method.invoke(dest,value);
			} catch (Exception e) {
				continue;
			}
		}
	}
}