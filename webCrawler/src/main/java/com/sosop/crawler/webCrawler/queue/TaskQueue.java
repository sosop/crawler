package com.sosop.crawler.webCrawler.queue;

import java.util.Collection;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TaskQueue {
	private static ConcurrentLinkedQueue<String> queue = 
			new ConcurrentLinkedQueue<String>();
	
	private TaskQueue() {}
	
	public static boolean enter(String url) {
		return queue.offer(url);
	}
	
	public static boolean enterAll(Collection<String> urls) {
		return queue.addAll(urls);
	}
	
	public static String out() {
		return queue.poll();
	}
}
