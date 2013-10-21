package com.annotation;

import java.util.regex.Pattern;

public class UseCase {

	@Regex(regexRule = RegexRule.EMAIL)
	public void regexEmail(String unCheckedString, String regexRule) {

		boolean rs = Pattern.compile(regexRule).matcher(unCheckedString).find();

		System.out.println(rs);

	}

}
