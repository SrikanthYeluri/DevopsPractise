package com.standardchartered.main.ExcelOperations;


import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.standardchartered.main.controller.Constants;
import com.standardchartered.main.controller.FlowMethods;
import com.standardchartered.main.testdata.DemoModuleTestDataObject;

public class ExcelOperations {
	Logger  log = Logger.getLogger(ExcelOperations.class);
	Fillo fillo = new Fillo();
	Properties prop = new Properties();
	FileInputStream fis=null;
	String testDataSheet=null;
	Connection con=null;
	Recordset readExecutionVariablesRecordSet = null;
	String readExecutionVariables = null;
	List<String> JourneyList = new ArrayList<String>();
	String journeyModule;
	String journeyFlowMethods;
	String journeyTestCaseID;
	//TO read the Setting sheet in the WOrkBook
	public void readExecutionVariables() throws Exception {
		try {
			fis = new FileInputStream(new File("./Config.Properties"));
			prop.load(fis);
			testDataSheet = prop.getProperty("TestDataLocation");
			con = fillo.getConnection(testDataSheet);
			//Reading the execution variables in the excel sheet.;
			readExecutionVariables = "Select * from Settings" ;
			readExecutionVariablesRecordSet = con.executeQuery(readExecutionVariables);
			log.info("Started Reading Settings sheet variables ");
			while(readExecutionVariablesRecordSet.next()) {
				Constants.Environment = readExecutionVariablesRecordSet.getField("Environment");
				Constants.URL = readExecutionVariablesRecordSet.getField("URL");
				Constants.GlobalMaxTime = readExecutionVariablesRecordSet.getField("GlobalMaxTime");
				Constants.ChromeDriverPath=readExecutionVariablesRecordSet.getField("ChromeDriverPath");
				Constants.ReportsPath = readExecutionVariablesRecordSet.getField("ReportsPath");
				Constants.ScreenShotsPath = readExecutionVariablesRecordSet.getField("ScreenShotsPath");
				Constants.BrowserType= readExecutionVariablesRecordSet.getField("BrowserType");	
				log.info("Completed Reading Settings sheet variables");
				System.out.println("**********************SuccessFully Loaded Constant WebElements*********************");
			}
			
			//To initiate extent report class 
			log.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@----------Report Initialization Started-----------------@@@@@");
			Constants.htmlExtentReport = new ExtentHtmlReporter(Constants.ReportsPath+"Report_"+System.currentTimeMillis()+"testing.html");
			Constants.extentReports = new ExtentReports();
			Constants.extentReports.attachReporter(Constants.htmlExtentReport);
			Constants.extentReports.setSystemInfo("Key1", "Value1");
			Constants.extentReports.setSystemInfo("Key2", "Value2");
			Constants.extentReports.setSystemInfo("Key3", "Value3");
			Constants.extentReports.setSystemInfo("Key4", "Value4");
			Constants.extentReports.setSystemInfo("Key5", "Value5");
		//	Constants.htmlExtentReport.config().se

			Constants.htmlExtentReport.config().setDocumentTitle("Extent Report Demo");
			Constants.htmlExtentReport.config().setReportName("Test Report");
			Constants.htmlExtentReport.config().setTestViewChartLocation(ChartLocation.TOP);
			Constants.htmlExtentReport.config().setTheme(Theme.STANDARD);
			Constants.htmlExtentReport.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
		}
		catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			// To close the data base opened connections
			readExecutionVariablesRecordSet.close();
			con.close();
		}
	}
	// To read the TestSuite Sheet in the WorkBook.
	public void readTestSuiteVariables() {
		Recordset testSuiteRecordSet = null;
		try {
	
			con = fillo.getConnection(testDataSheet);
			String query= "Select * from TestSuite where ExecutionStatus = 'Yes'";;
			testSuiteRecordSet = con.executeQuery(query);
			log.info("Started Reading Test Suite Variables");
			while(testSuiteRecordSet.next()) {
				String S_No = testSuiteRecordSet.getField("S_No");
				String TestCaseID = testSuiteRecordSet.getField("TestCaseID");
				String Description = testSuiteRecordSet.getField("Description");
				String FlowMethods = testSuiteRecordSet.getField("FlowMethods");
				String ExecutionStatus = testSuiteRecordSet.getField("ExecutionStatus");
				String Module = testSuiteRecordSet.getField("Module");
				log.info("Completed Reading Test Suite Variables,Adding into ArrayList with the ~ Seperation");
				JourneyList.add(TestCaseID+"~"+FlowMethods+"~"+Module);
			}

		}catch(Exception e) {
			e.printStackTrace();

		}finally {
			testSuiteRecordSet.close();
			con.close();

		}
		//To iterate all the JourneyList 
		Iterator iterator= 		JourneyList.iterator();
		while(iterator.hasNext()) {
			String journeyListElements = (String) iterator.next();
			String journeyListEleArr[]	= journeyListElements.split("~");
			journeyTestCaseID=			journeyListEleArr[0];
			journeyFlowMethods			 = journeyListEleArr[1];
			journeyModule			 = journeyListEleArr[2];

			log.info("Started Execution the Moudle wise Testcases "+journeyListElements);

			//getting the module name and reading the variables as per that.

			if(journeyModule.equalsIgnoreCase("DemoModule")) {
				log.info("Started reading the module level variables");
				populateTestDataDemoModule(journeyModule,journeyTestCaseID);
			}
			// This is to execute the flowmethods for a journey.
			Constants.extentTest=Constants.extentReports.createTest(journeyTestCaseID, "Sample test Description needs to be added");
			
			Constants.extentTest.assignCategory(journeyModule);
			executeFlowMethods(journeyFlowMethods,journeyModule);
		}
		

		log.info("@@@@@@@@@@@@@@@@Test case execution:"+journeyTestCaseID+" Execution Completed@@@@@@@@@@@@@@");
	}
	private void populateTestDataDemoModule(String journeyModule, String journeyTestCaseID) {
		try {
			String query = "select * from "+journeyModule+" where TestCaseID="+"'"+journeyTestCaseID+"'";
			log.info("Query:: "+query);
			Connection con = fillo.getConnection(testDataSheet);
			Recordset populateTDDemoModuleRS=con.executeQuery(query);
			while(populateTDDemoModuleRS.next()) {
				DemoModuleTestDataObject.TestCaseID=populateTDDemoModuleRS.getField("TestCaseID");
				DemoModuleTestDataObject.Description=populateTDDemoModuleRS.getField("Description");
				DemoModuleTestDataObject.LinkRef=populateTDDemoModuleRS.getField("LinkRef");
				DemoModuleTestDataObject.LoginCredentials=populateTDDemoModuleRS.getField("LoginCredentials");
			}

		}catch(Exception e) {
			e.printStackTrace();

		}finally {
			con.close();
		}
		// TODO Auto-generated method stub

	}
	//For FlowMethods to iterate and execute each one
	public void	executeFlowMethods(String journeyFlowMethods,String journeyModule){
			FlowMethods flowMethods = new FlowMethods();
		try {
			//Loop for Multiple FLowmethods.
			for(int currentFM = 0;currentFM<journeyFlowMethods.split(",").length;currentFM++) {
				//to invoke the flowmethod inside the class.
			flowMethods.getClass().getMethod(journeyFlowMethods.trim(), String.class).invoke(flowMethods, "");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
