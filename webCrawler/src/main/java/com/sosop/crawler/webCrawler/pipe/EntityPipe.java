package com.sosop.crawler.webCrawler.pipe;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public abstract class EntityPipe {
	protected String savePath;

	public abstract void execute(String html, String... url);

	protected void writeToFile(String domain, String filename, ByteBuffer buffer) {
		Path dir  = Paths.get(savePath, domain);
		Path file = Paths.get(savePath, domain, filename);
		FileChannel channel = null;
		try {
			// 创建文件
			if (Files.notExists(dir, LinkOption.NOFOLLOW_LINKS)) {
				Files.createDirectories(dir);
			}
			// 创建文件
			if (Files.notExists(file, LinkOption.NOFOLLOW_LINKS)) {
				Files.createFile(file);
			}
			// 写文件
			channel = FileChannel.open(file, StandardOpenOption.APPEND);
			channel.write(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				// 关闭channel
				if (channel != null && channel.isOpen()) {
					channel.close();
				}
				// 清空缓冲区
				if (buffer != null) {
					buffer.clear();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
