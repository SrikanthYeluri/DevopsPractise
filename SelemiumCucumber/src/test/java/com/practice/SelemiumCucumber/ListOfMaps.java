package com.practice.SelemiumCucumber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListOfMaps {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Map<String,String> hmap1 = new HashMap<String,String>();
		hmap1.put("Key1", "Value1");
		Map<String,String> hmap2 = new HashMap<String,String>();
		hmap2.put("Key2", "Value2");
		Map<String,String> hmap3 = new HashMap<String,String>();
		hmap3.put("Key3", "Value3");
		
		List<Map<String,String>> lMap = new ArrayList<Map<String,String>>();
		lMap.add(hmap1);
		lMap.add(hmap2);
		lMap.add(hmap3);
		// to Iterate list of maps
		
		for(Map<String,String> cMap :lMap) {
			Set<String> set = cMap.keySet();
			Iterator i = set.iterator();
			while(i.hasNext()) {
				System.out.println(i.next());
			}
			System.out.println(cMap.get("Key1"));
			System.out.println(cMap.get("Key2"));
			System.out.println(cMap.get("Key3"));
		}

	}

}
