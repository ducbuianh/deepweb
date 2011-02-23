package com.googlegroups.searchenginebkhn.queryTemplate;

import java.util.LinkedList;

import com.gargoylesoftware.htmlunit.util.NameValuePair;

public class QueryTemplate 
{
	private LinkedList<LabelValueSet> lvsList;
	private String queryTemplateHeader;
	public QueryTemplate(String queryTemplateHeader)
	{
		this.lvsList = new LinkedList<LabelValueSet>();
		this.queryTemplateHeader = queryTemplateHeader;
	}
	/**
	 * Hiển thị dạng String của Query Template
	 * @return Dạng của query Temlate dang xet
	 */
	public String toString()
	{
		String queryTemplateContent = getQueryTemplateContent();
		String queryTemplateString = queryTemplateHeader + "?" + queryTemplateContent;
		return queryTemplateString;
		
	}
	public void addInputBinding(LabelValueSet lvs)
	{
		lvsList.add(lvs);
	}
	private String getQueryTemplateContent()
	{
		String queryTemplateContent = "";
		int i = 0;
		for (LabelValueSet lvs : lvsList)
		{
			queryTemplateContent = queryTemplateContent + lvs.getLabelName() + "=" 
				+ "\"value" + (i++) + "\"";
		}
		return queryTemplateContent;
	}
	public LinkedList<String> getAllTemplate()
	{
		int numOfLabel = lvsList.size();
		// khoi tao
		LinkedList<String> temporaryList = new LinkedList<String>();
		LinkedList<String> resultList = lvsList.get(0).getSetOfLabelValue();
		if (lvsList.size() == 1) 
			return resultList;
		
		// duyet
		for (int i = 1; i < numOfLabel; i ++)
		{
			temporaryList.clear();
			for (int rlI = 0; rlI < resultList.size(); rlI++)
			{
				String s = resultList.get(rlI);
				temporaryList.add(s);
			}
			
			LinkedList<String> currentValueSet = lvsList.get(i).getSetOfLabelValue();
			resultList.clear();
			for (int tlI = 0; tlI < temporaryList.size(); tlI ++)
				for (int cvsI = 0; cvsI < currentValueSet.size(); cvsI ++)
				{
					String query = temporaryList.get(tlI) + "&" + currentValueSet.get(cvsI);
					resultList.add(query);
				}
		}
		
		return resultList;
	}
	
}
