package com.movies.moviedatabaseapi.controllers;

import java.util.regex.Pattern;

public final class HelperController {
	private static Pattern regex = Pattern.compile("[$&+,:;=\\\\?@#|/'<>.^*()%!-]");
	public static final String BASE_URI_V1 = "/moviedb/v1/";
	
	
	public static final Boolean passwordContainsSpecialCharacters (String text) {
		if (regex.matcher(text).find()) {
			return true;
		} else {
			return false;
		}
	}
}
