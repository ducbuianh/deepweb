package com.googlegroups.searchenginebkhn.formExtractor;

import java.util.LinkedList;



public class Form 
{
	private LinkedList<LabelValueSet> labelValueSetList;
	private String action;
	private String method;
	
	public void setList(LinkedList<LabelValueSet> list)
	{
		this.labelValueSetList = list;
	}
	
	public void setAction(String action)
	{
		this.action = action;
	}
	
	public void setMethod(String method)
	{
		this.method = method;
	}
}
