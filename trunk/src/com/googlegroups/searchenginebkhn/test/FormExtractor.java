package com.googlegroups.searchenginebkhn.test;

import java.net.URL;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class FormExtractor {
	public DomNodeList<HtmlElement> extract(URL url)
	{
		DomNodeList<HtmlElement> node_list;
		WebClient wc = new WebClient();
		try{
		HtmlPage page = (HtmlPage) wc.getPage(url);
		node_list = page.getElementsByTagName("form");
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return node_list;
	}
	
	public void toFile(DomNodeList<HtmlElement> node_list)
	{
		String FILE_NAME = "results.txt";
		PrintWriter printWriter = null;
		java.util.Iterator<HtmlElement> iterator;
		HtmlElement form;
		
		try {
			printWriter = new PrintWriter(new FileWriter(FILE_NAME));
			
			iterator = node_list.iterator();
			while(iterator.hasNext())
			{
				form = iterator.next();
				printWriter.println(form.asXml());
				printWriter.println("--------------------------------");
			}
			
		}catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			if (printWriter != null) {
			printWriter.close();
			}
		}
	}
}
