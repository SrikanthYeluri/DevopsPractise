package com.practice.SelemiumCucumber;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class repetativeCharacters {
	
	public static String str="lalitha";

	public static void main(String[] args) {
		 char[] strchar = str.toCharArray();
		 Map<Character,Integer> hmap = new HashMap<Character,Integer>();
		 for(int i=0;i<strchar.length;i++) {
			 
			 if(hmap.containsKey(strchar[i])) {
				 hmap.put(strchar[i], hmap.get(strchar[i])+1);
			 }else {
				 hmap.put(strchar[i], 1);
			 }
				 
		 }
		 
		 Set<Character> keys = hmap.keySet();
		 for(Character k:keys) {
			 System.out.println("Number of times each:: "+k.UPPERCASE_LETTER+" key repeated"+hmap.get(k));
			 // tocheck the duplicate key
			 if(hmap.get(k)>1) {
				 System.out.println("duplicate key --- "+k.UPPERCASE_LETTER+"  Number of times repeated" +hmap.get(k));
			 }
		 }
		 
		 // other way to iterate the keys and values.
		 hmap.keySet();
		 Set<Map.Entry<Character, Integer>>  mEntry= hmap.entrySet();
		 
		 Iterator i = mEntry.iterator();
		 while(i.hasNext()) {
			 System.out.println("Other way to iterate::   "+i.next());
			 
		 }
		
	}

}
