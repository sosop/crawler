package com.sosop.crawler.webCrawler.pipe.entity;

import java.nio.ByteBuffer;

import com.sosop.crawler.webCrawler.pipe.EntityPipe;
import com.sosop.crawler.webCrawler.util.StringReg;

public class FileEntityPipe extends EntityPipe {

	private ByteBuffer buffer;

	public FileEntityPipe(String savePath) {
		this.savePath = savePath;
	}

	@Override
	public void execute(String html, String... url) {
		String domain = StringReg.getDomain(url[0]);
		buffer = ByteBuffer.wrap(html.getBytes());
		this.writeToFile(domain, StringReg.replaceURL(url[0]), buffer);
	}
}