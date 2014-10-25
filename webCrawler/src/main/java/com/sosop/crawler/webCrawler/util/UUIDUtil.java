package com.sosop.crawler.webCrawler.util;

import java.util.UUID;

public class UUIDUtil {
	public static String genUUId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
