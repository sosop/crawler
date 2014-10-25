package com.sosop.crawler.webCrawler.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ObjectToString {
	public static String streamToString(InputStream in) {

		StringBuilder html = new StringBuilder();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				in));) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				html.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return html.toString();
	}
}
