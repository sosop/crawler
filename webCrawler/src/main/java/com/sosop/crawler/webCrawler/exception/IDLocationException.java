package com.sosop.crawler.webCrawler.exception;

public class IDLocationException extends Exception {

	private static final long serialVersionUID = -7233121385184981711L;
	
	public IDLocationException() {
		super("根据id定位元素必须放在所有方法之前调用且只能调用一次");
	}
}
