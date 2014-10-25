package com.sosop.crawler.webCrawler.pipe.extra;

import java.nio.ByteBuffer;
import java.util.List;

import com.sosop.crawler.webCrawler.parse.HtmlParse;
import com.sosop.crawler.webCrawler.pipe.EntityPipe;
import com.sosop.crawler.webCrawler.util.Constant;
import com.sosop.crawler.webCrawler.util.StringReg;
import com.sosop.crawler.webCrawler.util.UUIDUtil;

public abstract class FileExtraPipe extends EntityPipe {

	private ByteBuffer buffer;

	public FileExtraPipe(String savePath) {
		this.savePath = savePath;
	}

	@Override
	public void execute(String html, String... url) {
		StringBuilder sb = new StringBuilder();
		HtmlParse parse = new HtmlParse(html);
		String domain = StringReg.getDomain(url[0]);

		List<String> lines = extraDOM(parse);
		if (lines != null) {
			for (String line : lines) {
				sb.append(line).append("\n");
			}
		}
		buffer = ByteBuffer.wrap(sb.toString().getBytes());
		this.writeToFile(
				new StringBuilder(domain).append("/")
						.append(Constant.DIR.EXTRA).toString(),
				UUIDUtil.genUUId(), buffer);
	}

	public abstract List<String> extraDOM(HtmlParse parse);
}