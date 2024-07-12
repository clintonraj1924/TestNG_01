package org.demo.qa.myListener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkReporter; // ui of the report
	public ExtentReports extent; // populate common info on the report
	public ExtentTest test; // creating tc entriesin the report and update statusof the methos

	public void onStart(ITestContext context) {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myReport.html");
		sparkReporter.config().setDocumentTitle("Automation Report"); // Title of report
		sparkReporter.config().setReportName("Futional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Computer Name", "local Host");
		extent.setSystemInfo("ENV", "QA");
		extent.setSystemInfo("Tester Name", "Clinton Raj");
		extent.setSystemInfo("OS", "W10");
		extent.setSystemInfo("Browser Name", "Chrome");

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // create a new entry in the report
		test.log(Status.PASS, "Test Case Passed is:" + result.getName()); // update stsus p/f/s

	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); // create a new entry in the report
		test.log(Status.FAIL, "Test Case Faild is:" + result.getName());
		test.log(Status.FAIL, "Test Case Faild cause is:" + result.getThrowable());
	}

	public void onTestSkiped(ITestResult result) {
		test = extent.createTest(result.getName()); // create a new entry in the report
		test.log(Status.SKIP, "Test Case skiped is:" + result.getName()); // update stsus p/f/s

	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
