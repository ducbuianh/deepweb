package com.googlegroups.searchenginebkhn.queryTemplate;

import java.util.LinkedList;

public class TestQtGenerating {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		LabelValueSet nameSet = new LabelValueSet("Name");
		nameSet.addValue("Do");
		nameSet.addValue("Duc");
		nameSet.addValue("Hoa");
		
		
		LabelValueSet locationSet = new LabelValueSet("Location");
		locationSet.addValue("Hung Yen");
		locationSet.addValue("Thai Binh");
		locationSet.addValue("Thanh Hoa");
		
		LabelValueSet ageSet = new LabelValueSet("Age");
		ageSet.addValue("22");
		ageSet.addValue("54");
		ageSet.addValue("96");
		
		QueryTemplate qt = new QueryTemplate("http://dtdh.hut.edu.vn");
		
		qt.addInputBinding(nameSet);
		qt.addInputBinding(locationSet);
		qt.addInputBinding(ageSet);
		

		System.out.println(qt.toString());
		LinkedList<String> querySet = qt.getAllTemplate();
		for (String q : querySet)
			System.out.println(q);
	}

}
