package com.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class SplitDate {
	public static void main(String[] args) throws ParseException {
		String contentName = "市场分析室_20100727";
		String splitTitleWithContentName = "";
		try{
			String[] names = StringUtils.split(contentName, "_");
			if(null != names && names.length == 2) {
				SimpleDateFormat sdfIn = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat sdfOut = new SimpleDateFormat("MM月dd日");
				splitTitleWithContentName += (names[0] + "(" + sdfOut.format(sdfIn.parse(names[1])) + ")");
			}
		} catch (Exception e) {
			
		}
		System.out.println(splitTitleWithContentName);
		
		String string = "1970-01-01 12:12:12";
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(format.parse(string));
		
		String oriFileName = "盛夏晚晴飞138x181.jpg";
		int figureNum = getMediumNameNumFromOriFileName(oriFileName);
		String mediumName = oriFileName.substring(0, figureNum);
		String resolution = oriFileName.substring(figureNum, oriFileName.lastIndexOf("."));
		print(mediumName);
		print(resolution);
		
		Set<Long> ids = new HashSet<Long>();
		System.out.println(ids.add(13l));
		System.out.println(ids.add(14l));
		System.out.println(ids.add(15l));
		System.out.println(ids.add(14l));
		
	}
	
	/**
	 * 根据原始文件名，获取介质名称的位置
	 * @param oriFileName 原始文件名
	 * @return
	 */
	public static int getMediumNameNumFromOriFileName(String oriFileName) {
		int figureNum = 0;
		String mediumNameAndResolutionWidth = oriFileName.substring(0, oriFileName.lastIndexOf("x"));
		char[] mediumNameAndResolutionWidthChar = mediumNameAndResolutionWidth.toCharArray();
		for(int i = mediumNameAndResolutionWidthChar.length - 1; i >= 0; i--) {
			if(mediumNameAndResolutionWidthChar[i] >= '0' && mediumNameAndResolutionWidthChar[i] <= '9') {
				figureNum++;
			} else {
				return mediumNameAndResolutionWidth.length() - figureNum;
			}
		}
		return 0;
	}
	
	public static void print(String string) {
		System.out.println(string);
	}
	
	public static void printc(char string) {
		System.out.println(string);
	}
}
