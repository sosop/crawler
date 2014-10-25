package com.sosop.crawler.webCrawler.pipe.entity;


import com.sosop.crawler.webCrawler.pipe.EntityPipe;


public class ConsoleEntityPipe extends EntityPipe {

	@Override
	public void execute(String html, String...url) {
		System.out.println(html);
	}
}