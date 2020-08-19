package com.practice.selenium.pages;

import com.practice.selenium.controls.Controls;

public class Login extends Controls{

	
	public void launchURL() {
		try {
			
		driver.get("https://www.facebook.com");
		System.out.println("The Driver Instance in Login Page"+driver);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
