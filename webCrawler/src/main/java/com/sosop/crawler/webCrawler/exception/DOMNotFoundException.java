package com.sosop.crawler.webCrawler.exception;

public class DOMNotFoundException extends Exception {

	private static final long serialVersionUID = -7233121385184981711L;
	
	public DOMNotFoundException() {
		super("找不到元素");
	}
}
