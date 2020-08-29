package com.standardchartered.main.controller;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.google.common.io.Files;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class ActionMethods {
	Logger log = Logger.getLogger(ActionMethods.class);
	public WebDriver driver = FlowMethods.driver;
	//To Open the Browser
	public WebDriver openBrowser(String BrowserType) {
		// Selecting the browser based on the Driver that you have passed in the Excel
		try {
			switch(BrowserType) {
			case "Chrome":
				System.setProperty("webdriver.chrome.driver", Constants.ChromeDriverPath);
				driver = new ChromeDriver();
				break;
			case "IE":
				driver = new InternetExplorerDriver();
				break;
			case "Safari":
				driver = new SafariDriver();
				break;
			}
			FlowMethods.driver=driver;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return driver;
	}
	//To launch URL
	public void launchURL() throws Exception {
		try {
			driver.get(Constants.URL);
			driver.manage().window().maximize();
			log.info("Launched URL:: "+Constants.URL);
			Report.reportInstance("Passed", Thread.currentThread().getStackTrace()[1].getMethodName(),driver);
		}
		catch(Exception e) {
			Report.reportInstance("Failed", Thread.currentThread().getStackTrace()[1].getMethodName(),driver);
			e.printStackTrace();
		}
	}
	
	//to take screen shots 
	
	public static String takeScreenShot(WebDriver driver) {
		File ss = null;
		String filePathName="";
		String destinationPath = null;
		try {
			ss = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			//String fileName =  ss.getName()+System.currentTimeMillis()+".jpeg";
			destinationPath=Constants.ScreenShotsPath+System.currentTimeMillis()+".jpeg";
			FileUtils.copyFile(ss, new File(destinationPath));
			//Files.copy(ss, new File(Cons
			//to get the name of the screen shot
			// filePathName = ss.toString().split("\\")[ss.toString().split("\\").length-1];
			
		}catch(Exception e) {
			e.printStackTrace();
		} return destinationPath;
	}
	
	public static String takeScreenShotASHOT(WebDriver driver) {
		String screenShotName="";
		try {
				Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
				 screenShotName = new SimpleDateFormat("dd-MM-YYYY_hh_mm_ssaa").toString()+".png";
				ImageIO.write(screenshot.getImage(), "PNG", new File(screenShotName));
				screenShotName=Constants.ScreenShotsPath+"\\"+screenShotName;
				}catch(Exception e) {
					e.printStackTrace();
		}
		
		return screenShotName;
	}
	
}
