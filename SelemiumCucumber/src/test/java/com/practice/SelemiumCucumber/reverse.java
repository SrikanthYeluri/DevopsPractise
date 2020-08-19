package com.practice.SelemiumCucumber;

public class reverse {

	public static String orginalString="madam";
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] stringToChar = orginalString.toCharArray();
		String temp="";
		int j=0;
		for(int i=stringToChar.length-1;i>=j;i--) {
			
			if(temp.equalsIgnoreCase(""))
				temp=String.valueOf(stringToChar[i]);
			else
				temp=temp+String.valueOf(stringToChar[i]);
		}
		System.out.println(temp);
		
		//to check hte stting is palendrome
		if(orginalString.equals(temp)) {
			System.out.println("Palindrome");
		}else {
			System.out.println("Not Palindrome");
		}

	}

}
