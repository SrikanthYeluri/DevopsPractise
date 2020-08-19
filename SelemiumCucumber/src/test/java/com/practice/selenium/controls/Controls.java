package com.practice.selenium.controls;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

/*
 *  The controls class is used to initialize the following by using Static keyword
 *   to Initialize property file in the static block
 */
public class Controls {
	
	public static WebDriver driver;
	public static Properties prop;
	
	static {
		
		try {
			FileInputStream fis = new FileInputStream(new File("./config.properties"));
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static WebDriver openBrowser(String browserType) {

		if(browserType.toUpperCase().equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", "C:/Users/lalitha/eclipse-workspace/SelemiumCucumber/src/test/resources/chromedriver.exe");
			driver = new ChromeDriver();
		}

		if(browserType.toUpperCase().equalsIgnoreCase("IE")) {
			driver = new InternetExplorerDriver();
		}
		return driver;
	}
}
