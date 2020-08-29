package com.standardchartered.main.controller;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;


//https://extentreports.com/docs/versions/3/java/#automatic-screenshot-management
public class Report {
	public static void reportInstance(String status,String MethodName,WebDriver driver) throws Exception {
		String pathOfScreenShot="";
		if(status.equalsIgnoreCase("Passed")) {
//			Constants.extentTest.log(Status.PASS, MethodName);
			// To take the Screen Shot we have to pass the driver to the screen shot method
			 pathOfScreenShot = ActionMethods.takeScreenShot(driver);
			//pathOfScreenShot = ActionMethods.takeScreenShotASHOT(driver);
			 
			 Constants.extentTest.log(Status.PASS, MethodName, MediaEntityBuilder.createScreenCaptureFromPath(pathOfScreenShot).build());
			//Constants.extentTest.pass(MethodName, MediaEntityBuilder.createScreenCaptureFromPath(pathOfScreenShot).build());
		}if(status.equalsIgnoreCase("Failed")) {
			Constants.extentTest.log(Status.FAIL, MethodName);
			Constants.extentTest.addScreenCaptureFromPath("C:\\Users\\Public\\POMFrameWork\\ScreenShots\\gettyimages-496261146-2048x2048");
		}if(status.equalsIgnoreCase("Skipped")) {
			Constants.extentTest.log(Status.SKIP, MethodName);
		}
	}
}
