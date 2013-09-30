package com.String;

import java.util.ArrayList;
import java.util.List;

public class StringTest {
	/*public static void main(String[] args) {
		String tt = "我们是粤语321084x768.jpg";
		String suf = "1084x768.";
		int number = -1;
		if(-1 != (number = tt.indexOf(suf))) {
			System.out.println(number);
			System.out.println(tt.substring(0, number));
			System.out.println(tt.substring(number));
		}
	}*/
	public static void main(String[] args) {  
        List<String> list=new ArrayList<String>();  
        list.add("王利虎");  
        list.add("张三");  
        list.add("李四");  
        int size=list.size();  
        String[] array = (String[])list.toArray(new String[size]);  
        for(int i=0;i<array.length;i++){  
            System.out.println(array[i]);  
        }  
    }  
}