package com.sosop.crawler.webCrawler.download;


import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.sosop.crawler.webCrawler.pipe.EntityPipe;
import com.sosop.crawler.webCrawler.queue.TaskQueue;
import com.sosop.crawler.webCrawler.util.ObjectToString;



public class Download {
	
	public void downloadPage(EntityPipe page, EntityPipe extra) {
		CloseableHttpClient client = HttpClients.createDefault();
		String url = null;
		while((url = TaskQueue.out()) != null) {
			HttpGet get = new HttpGet(url);
			try {
				CloseableHttpResponse response = client.execute(get);
				if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					HttpEntity entity = response.getEntity();
					String html = 
							ObjectToString.streamToString(entity.getContent());
					// InputStream in = entity.getContent();
					if(page != null) {
						page.execute(html, url);
					}
					if(extra != null) {
						extra.execute(html, url);
					}
				}
				
			} catch (Exception e) {
				System.out.println("爬取 [" + url + "] 出错");
				e.printStackTrace();
			}
		}
	}
}
