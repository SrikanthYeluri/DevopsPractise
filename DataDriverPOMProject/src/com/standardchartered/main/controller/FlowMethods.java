package com.standardchartered.main.controller;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class FlowMethods {
	ActionMethods actionMethods= new ActionMethods();
	Logger log = Logger.getLogger(FlowMethods.class);
	public static WebDriver driver;
	
	//Login Method
	public void Login(String ModuleName) throws Exception {
		log.info("Inside the Flowmethod::" +Thread.currentThread().getStackTrace()[1].getMethodName());
		driver = actionMethods.openBrowser(Constants.BrowserType);
		
		actionMethods.launchURL();
		
	}
}
