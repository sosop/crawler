package com.sosop.crawler.webCrawler.pipe.extra;

import java.util.List;

import com.sosop.crawler.webCrawler.parse.HtmlParse;
import com.sosop.crawler.webCrawler.pipe.EntityPipe;

public abstract class ConsoleExtraPipe extends EntityPipe {

	@Override
	public void execute(String html, String... url) {
		HtmlParse parse = new HtmlParse(html);
		
		List<String> lines = extraDOM(parse);
		
		if (lines != null) {
			for (String line : lines) {
				System.out.println(line);
			}
		}
	}
	
	public abstract List<String> extraDOM(HtmlParse parse);
}
