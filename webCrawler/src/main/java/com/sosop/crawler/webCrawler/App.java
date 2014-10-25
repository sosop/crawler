package com.sosop.crawler.webCrawler;

import java.util.Arrays;
import java.util.List;

import com.sosop.crawler.webCrawler.parse.HtmlParse;
import com.sosop.crawler.webCrawler.pipe.entity.FileEntityPipe;
import com.sosop.crawler.webCrawler.pipe.extra.ConsoleExtraPipe;
import com.sosop.crawler.webCrawler.pipe.extra.FileExtraPipe;
import com.sosop.crawler.webCrawler.site.Site;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	String[] urls = {
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2004-01-01",
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2005-01-01",
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2006-01-01",
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2007-01-01",
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2008-01-01",
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2009-01-01",
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2010-01-01",
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2011-01-01",
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2012-01-01",
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2013-01-01",
    		"http://baidu.lecai.com/lottery/draw/list/3?d=2014-01-01"
    	};
    	long start = System.currentTimeMillis();
    	Site.me().addURL(Arrays.asList(urls))
    		.addPagePipe(new FileEntityPipe("/home/sosop/crawler"))
    		//.addPagePipe(new ConsoleEntityPipe())
    		.addExtraPipe(new MyFileExtraPipe("/home/sosop/crawler"))
    		.run();
    	long end = System.currentTimeMillis();
    	System.out.println(end - start);
    }
}

class MyConsoleExtraPipe extends ConsoleExtraPipe {

	@Override
	public List<String> extraDOM(HtmlParse parse) {
		List<String> lines = parse.getFragmentById("draw_list")
				.getFragmentByTag("tbody")
				//.child(0, true)
				//.child(1, true)
				.children()
				//.getFragmentByTag("tr")
				//.child(1, false)
				.text();
		return lines;
	}
	
}

class MyFileExtraPipe extends FileExtraPipe {

	public MyFileExtraPipe(String savePath) {
		super(savePath);
	}

	@Override
	public List<String> extraDOM(HtmlParse parse) {
		List<String> lines = parse.getFragmentById("draw_list")
				.getFragmentByTag("tbody")
				//.child(0, true)
				//.child(1, true)
				.children()
				//.getFragmentByTag("tr")
				//.child(1, false)
				.text();
		return lines;
	}
	
}
