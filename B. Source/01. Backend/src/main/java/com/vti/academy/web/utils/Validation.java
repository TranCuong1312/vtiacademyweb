package com.vti.academy.web.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
	public static final String REGEX_SPECIAL_CUSTOMIZE = "[\"'&<>%_]";
	
	/** validate parameter input 
	 * if input contain [\"'&<>%] then return false else return true
	 * if input is null or empty then return true
	 * @param input
	 * @return boolean
	 */
	public static boolean passValidateSpecialCharacter(String input) {
		Pattern pattern = Pattern.compile(REGEX_SPECIAL_CUSTOMIZE);
		Matcher matcher = pattern.matcher(input);
		return !matcher.find();
	}
}
