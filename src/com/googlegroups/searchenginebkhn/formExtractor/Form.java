package com.googlegroups.searchenginebkhn.formExtractor;

import java.util.LinkedList;



public class Form 
{
	private LinkedList<LabelValueSet> labelValueSetList;
	private String action;
	private String method;
	/**
	 * Hien thi ket qua de test
	 */
	public void printResult()
	{
		System.out.println("action: " + action);
		System.out.println("method: " + method);
		System.out.println("lvs List: " );
		{
			for (LabelValueSet lvs : labelValueSetList)
			{
				System.out.println("   label:" + lvs.getLabelName());
				LinkedList<String> labelValue = lvs.getSetOfLabelValue();
				for (String value : labelValue)
				{
					System.out.print(value + "   ;");
				}
				System.out.println();
			}
		}
	}
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
