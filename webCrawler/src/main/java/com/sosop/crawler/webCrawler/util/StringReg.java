package com.sosop.crawler.webCrawler.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringReg {
	public static String getDomain(String url) {
		String domain = "unknown";
		String reg = "^http[s]?://.*?\\.(.*?)/.*$";
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(url);
		if(matcher.matches()) {
			domain = matcher.group(1);
		}
		return domain;
	}
	
	public static String replaceURL(String url) {
		return url.replaceAll("http://", "")
				.replaceAll("https://", "")
				.replaceAll("/", "-");
	}
}
