package com.googlegroups.searchenginebkhn.formExtractor;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;

public class TestFormExtractor {

	/**
	 * Kiem tra tinh dung dan cua viec trich form
	 * @param args
	 * @throws MalformedURLException 
	 */
	public static void main(String[] args) throws MalformedURLException 
	{
		// TODO Auto-generated method stub
		FormExtractor formExtractor = new FormExtractor();
		WebClient wc = new WebClient();
		URL url = new URL("http://www.amazon.com/Advanced-Search-Books/b?ie=UTF8&node=241582011");
		List<HtmlForm> htmlFormList = formExtractor.getForm(wc, url);
		LinkedList<Form> formList = new LinkedList<Form>();
		for (HtmlForm htmlForm : htmlFormList)
			formList.add(formExtractor.analyzeForm(htmlForm));

		for (Form form : formList)
			form.printResult();
	}
	
}
