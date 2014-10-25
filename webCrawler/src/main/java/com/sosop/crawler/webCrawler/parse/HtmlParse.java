package com.sosop.crawler.webCrawler.parse;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sosop.crawler.webCrawler.exception.DOMNotFoundException;
import com.sosop.crawler.webCrawler.exception.IDLocationException;
import com.sosop.crawler.webCrawler.exception.IDNotFoundException;
import com.sosop.crawler.webCrawler.util.Constant;

public class HtmlParse {
	private Document doc;
	private Element startE;
	private boolean startFlag = false;
	private Elements last = new Elements();
	private Elements lastTmp = new Elements();
	private List<Elements> collect = new ArrayList<>();

	public HtmlParse(String html) {
		doc = Jsoup.parse(html);
		last.add(doc);
	}

	public HtmlParse(File file) {
		try {
			doc = Jsoup.parse(file, Constant.ENCODING.DEFAULT);
			last.add(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HtmlParse(URL url, int time) {
		try {
			doc = Jsoup.parse(url, time);
			last.add(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HtmlParse(InputStream in, String baseUri) {
		System.out.println(in);
		try {
			doc = Jsoup.parse(in, Constant.ENCODING.DEFAULT, baseUri);
			last.add(doc);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public HtmlParse getFragmentById(String id) {
		if (startFlag) {
			try {
				throw new IDLocationException();
			} catch (IDLocationException e) {
				e.printStackTrace();
			}
		}

		try {
			startE = doc.getElementById(id);
		} catch (Exception e) {
			try {
				throw new IDNotFoundException();
			} catch (IDNotFoundException e1) {
				e1.printStackTrace();
			}
		}

		last.clear();
		last.add(startE);
		startFlag = true;
		return this;
	}

	private void closable(Method method, int type, Object... params) {
		try {
			Elements tmps = null;
			Element tmp = null;
			for (Element el : last) {
				if (type == Constant.TYPE.ELEMENTS) {
					tmps = (Elements) method.invoke(el, params);
					lastTmp.addAll(tmps);

				} else if (type == Constant.TYPE.ELEMENT) {
					tmp = (Element) method.invoke(el, params);
					if (tmp != null) {
						lastTmp.add(tmp);
					}
				}
			}
			last.clear();
			last.addAll(lastTmp);
			lastTmp.clear();
		} catch (Exception e) {
			try {
				throw new DOMNotFoundException();
			} catch (DOMNotFoundException e1) {
				e1.printStackTrace();
			}
		}
	}

	public HtmlParse getFragmentByClass(String className) {
		try {
			Method method = Element.class.getMethod("getElementByClass",
					String.class);
			closable(method, Constant.TYPE.ELEMENTS, className);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HtmlParse getFragmentByAttr(String attr) {
		try {
			Method method = Element.class.getMethod("getElementsByAttribute",
					String.class);
			closable(method, Constant.TYPE.ELEMENTS, attr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HtmlParse getFragmentByAttrValue(String attr, String value) {
		try {
			Method method = Element.class.getMethod(
					"getElementsByAttributeValue", String.class, String.class);
			closable(method, Constant.TYPE.ELEMENTS, attr, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HtmlParse children() {
		try {
			Method method = Element.class.getMethod("children", null);
			closable(method, Constant.TYPE.ELEMENTS, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HtmlParse child(int index) {
		try {
			Method method = Element.class.getMethod("child", int.class);
			closable(method, Constant.TYPE.ELEMENT, index);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	public HtmlParse getFragmentByTag(String tagName) {
		try {
			Method method = Element.class.getMethod("getElementsByTag",
					String.class);
			closable(method, Constant.TYPE.ELEMENTS, tagName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}


	public List<String> html() {
		return fetch("html");
	}

	public List<String> text() {
		return fetch("text");
	}

	private List<String> fetch(String methodName) {
		List<String> lines = new ArrayList<String>();
		for (Element e : last) {
			if (e != null) {
				lines.add(reflectMethod(e, methodName));
			}
		}
		for (Elements es : collect) {
			lines.add(reflectMethod(es, methodName));
			lines.add("\n");
		}
		startFlag = false;
		return lines;
	}

	private String reflectMethod(Object obj, String methodName) {
		String result = "";
		try {
			Method m = obj.getClass().getMethod(methodName, null);
			result = (String) m.invoke(obj, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}