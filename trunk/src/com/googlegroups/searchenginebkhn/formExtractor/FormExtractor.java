package com.googlegroups.searchenginebkhn.formExtractor;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

public class FormExtractor 
{
	public List<HtmlForm> getForm(WebClient wc, URL url)
	{
		List<HtmlForm> node_list;
		try{
		HtmlPage page = (HtmlPage) wc.getPage(url);
		node_list = page.getForms();
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return node_list;
	}
	
	public Form analyzeForm(HtmlForm form)
	{
		Form result = new Form();
		LinkedList<LabelValueSet> labelValueSetList = new LinkedList<LabelValueSet>();
		HtmlInput input;
		HtmlSelect select;
		HtmlOption option;
		
		DomNodeList<HtmlElement> inputNodeList = form.getElementsByTagName("input");
		
		for(int i = 0; i < inputNodeList.size(); i ++)
		{
			input = (HtmlInput)inputNodeList.get(i);
			if(input.getTypeAttribute().compareTo("text") == 0)
			{
				labelValueSetList.get(i).setLabelName(input.getNameAttribute());
			}
			else continue;
			
		}
		
		DomNodeList<HtmlElement> selectNodeList = form.getElementsByTagName("select");
		
		for(int i = 0; i < selectNodeList.size(); i ++)
		{
			select = (HtmlSelect)selectNodeList.get(i);
			labelValueSetList.get(i).setLabelName(select.getNameAttribute());
			for(int j = 0; j < select.getOptionSize(); j ++)
			{
				option = select.getOption(j);
				labelValueSetList.get(i).addValue(option.getValueAttribute());
			}
		}
		
		result.setList(labelValueSetList);
		result.setMethod(form.getMethodAttribute());
		result.setAction(form.getActionAttribute());
		
		return result;
	}
	
	public void toFile(List<HtmlForm> node_list)
	{
		String FILE_NAME = "results.txt";
		PrintWriter printWriter = null;
		java.util.Iterator<HtmlForm> iterator;
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
