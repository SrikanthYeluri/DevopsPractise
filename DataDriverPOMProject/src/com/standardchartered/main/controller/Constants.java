package com.standardchartered.main.controller;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class Constants {
	 public static String Environment= "";
	 public static String URL= "";
	 public static String GlobalMaxTime= "";
	 public static String WaitTime= "";
	 public static String ChromeDriverPath="";
	 public static String ReportsPath="";
	 public static String ScreenShotsPath="";
	 public static String BrowserType="";
	 public static ExtentHtmlReporter htmlExtentReport=null;
	 public static ExtentReports extentReports=null;
	 public static ExtentTest extentTest=null;
	 public static String Status = "Passed";
	 
}
