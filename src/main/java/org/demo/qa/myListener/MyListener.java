package org.demo.qa.myListener;

import org.testng.ITestListener;

public class MyListener implements ITestListener {

	public void onStart(ITestListener context) {
		System.out.println("Test execution is started....!!!");
	}

	public void onTestStart(ITestListener result) {
		System.out.println("Test started....!!!");
	}

	public void onTestSuccess(ITestListener result) {
		System.out.println("Test Passed....!!!");
	}

	public void onTestFailure(ITestListener result) {
		System.out.println("Test Failed....!!!");
	}

	public void onTestSkipped(ITestListener result) {
		System.out.println("Test Skipped....!!!");
	}

	public void onTestFinished(ITestListener result) {
		System.out.println("Test Finished....!!!");
	}
}
