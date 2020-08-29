package com.standardchartered.main.controller;

import org.apache.log4j.Logger;

import com.standardchartered.main.ExcelOperations.ExcelOperations;

public class Runner {
	static Logger log  = Logger.getLogger(Runner.class);
	public static void main(String args[]) throws Exception {
		ExcelOperations excelOperation = new ExcelOperations();
		excelOperation.readExecutionVariables();
		excelOperation.readTestSuiteVariables();
		log.info("Complated reading suite variables and exected the test case");
		Runner.exitMethod();
		log.info("Complated Generating the extent report");
		
	}
	public static void exitMethod() {
		Constants.extentReports.flush();
		
	}
}
