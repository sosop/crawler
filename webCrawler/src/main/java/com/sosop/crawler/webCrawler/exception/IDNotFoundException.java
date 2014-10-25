package com.sosop.crawler.webCrawler.exception;

public class IDNotFoundException extends Exception {

	private static final long serialVersionUID = -7233121385184981711L;
	
	public IDNotFoundException() {
		super("解析id错误，查找不到id");
	}
}
