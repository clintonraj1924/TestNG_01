package org.demo.qa.myListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class MyListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		System.out.println("Test execution is started....!!!");
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName + "Test started....!!!");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName + "Test Passed....!!!");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName + "Test Failed....!!!");
		System.out.println(result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testName = result.getName();
		System.out.println(testName + "Test Skipped....!!!");
		System.out.println(result.getThrowable());
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println("Test Finished....!!!");
	}

//	public void onStart(ITestListener context) {
//		System.out.println("Test execution is started....!!!");
//	}
//
//	public void onTestStart(ITestListener result) {
//		System.out.println("Test started....!!!");
//	}
//
//	public void onTestSuccess(ITestListener result) {
//		System.out.println("Test Passed....!!!");
//	}
//
//	public void onTestFailure(ITestListener result) {
//		System.out.println("Test Failed....!!!");
//	}
//
//	public void onTestSkipped(ITestListener result) {
//		System.out.println("Test Skipped....!!!");
//	}
//
//	public void onTestFinished(ITestListener result) {
//		System.out.println("Test Finished....!!!");
//	}

}
