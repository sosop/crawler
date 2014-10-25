package com.sosop.crawler.webCrawler.site;

import java.util.Collection;

import com.sosop.crawler.webCrawler.download.Download;
import com.sosop.crawler.webCrawler.pipe.EntityPipe;
import com.sosop.crawler.webCrawler.queue.TaskQueue;

public class Site {
	private Download download;
	private EntityPipe page;
	private EntityPipe extra;
	
	public static Site me() {
		return new Site();
	}
	
	public Site addURL(Collection<String> urls) {
		TaskQueue.enterAll(urls);
		return this;
	}
	
	public Site addURL(String url) {
		TaskQueue.enter(url);
		return this;
	}
	
	public Site addPagePipe(EntityPipe page) {
		this.page = page;
		return this;
	}
	
	public Site addExtraPipe(EntityPipe extra) {
		this.extra = extra;
		return this;
	}
	
	public void run() {
		this.download = new Download();
		download.downloadPage(page, extra);
	}
}