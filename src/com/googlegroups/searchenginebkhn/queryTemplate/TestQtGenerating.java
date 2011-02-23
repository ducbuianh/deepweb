package com.googlegroups.searchenginebkhn.queryTemplate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.LinkedList;

import javax.swing.text.html.HTML;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class TestQtGenerating {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 */
	public static void main(String[] args) throws FailingHttpStatusCodeException, MalformedURLException, IOException 
	{
		// TODO Auto-generated method stub
		
		/*
		LabelValueSet nameSet = new LabelValueSet("field-isbn");
		nameSet.addValue("9780140285000");
		nameSet.addValue("9780743273565");
		nameSet.addValue("9780061120060");
		*/
		
		LabelValueSet keyword = new LabelValueSet("field-keywords");
		keyword.addValue("java");
		keyword.addValue("linux");
		keyword.addValue("computer");
		
		LabelValueSet publisher = new LabelValueSet("field-publisher");
		publisher.addValue("Prentice Hall");
		publisher.addValue("addison wesley");

		/*
		LabelValueSet locationSet = new LabelValueSet("Location");
		locationSet.addValue("Hung Yen");
		locationSet.addValue("Thai Binh");
		locationSet.addValue("Thanh Hoa");
		
		LabelValueSet ageSet = new LabelValueSet("Age");
		ageSet.addValue("22");
		ageSet.addValue("54");
		ageSet.addValue("96");
		*/
		QueryTemplate qt = new QueryTemplate("http://www.amazon.com/gp/search/ref=sr_adv_b/");
		qt.addInputBinding(publisher);
		qt.addInputBinding(keyword);
		
		/*
		qt.addInputBinding(nameSet);
		qt.addInputBinding(locationSet);
		qt.addInputBinding(ageSet);
		*/

		System.out.println(qt.toString());
		LinkedList<String> querySet = qt.getAllTemplate();
		
		File file = new File("result");
		file.mkdir();
		for (int i = 0; i < querySet.size(); i++)
		{
			
			System.out.println(querySet.get(i));
			WebClient webClient = new WebClient(BrowserVersion.FIREFOX_3_6);
			HtmlPage page = webClient.getPage(querySet.get(i));
			System.out.println("**************" + i + "****************");
			
			String path = "result/page" + i + ".html";
			try
			{
			    // Create file 
			    FileWriter fstream = new FileWriter(path);
			        BufferedWriter out = new BufferedWriter(fstream);
			    out.write(page.asXml());
			    //Close the output stream
			    out.close();
		    }
			catch (Exception e)
			{
				//Catch exception if any
				System.err.println("Error: " + e.getMessage());
		    }
		}
	}

}
