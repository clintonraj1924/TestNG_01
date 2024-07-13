package org.demo.qa.myListener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

//import javax.swing.text.Utilities;
import org.demo.qa.util.Utilities;
import org.openqa.selenium.WebDriver;
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

	@Override
	public void onStart(ITestContext context) {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/myReport.html");
		sparkReporter.config().setDocumentTitle("Automation Report"); // Title of report
		sparkReporter.config().setReportName("Futional Testing"); // name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setTimeStampFormat("dd/MM/yyy hh:mm:ss");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Computer Name", "local Host");
		extent.setSystemInfo("ENV", "QA");
		extent.setSystemInfo("Tester Name", "Clinton Raj");
		extent.setSystemInfo("OS", "W10");
		extent.setSystemInfo("Browser Name", "Chrome");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // create a new entry in the report
		test.log(Status.PASS, "Test Case Passed is:" + result.getName()); // update stsus p/f/s

	}

	@Override
	public void onTestStart(ITestResult result) {

		test.log(Status.INFO, result.getName() + "Started Execution");
	}

	@Override
	public void onTestFailure(ITestResult result) {

		// test = extent.createTest(result.getName()); // create a new entry in the
		// report
		WebDriver driver = null; // for ScreenShort we need driver. so this line take a driver
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver")
					.get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		String destinationOfScreenShortPath = Utilities.captureScreenShort(driver, result.getName());
		test.addScreenCaptureFromPath(destinationOfScreenShortPath);
		test.log(Status.INFO, result.getThrowable());
		test.log(Status.FAIL, result.getName() + "Got Faild");

//		test.log(Status.FAIL, "Test Case Faild is:" + result.getName());
//		test.log(Status.FAIL, "Test Case Faild cause is:" + result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// test = extent.createTest(result.getName()); // create a new entry in the
		// report
		test.log(Status.INFO, result.getThrowable());
		test.log(Status.SKIP, "Test Case skiped is:" + result.getName()); // update stsus p/f/s

	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
		String pathOfExtendReport = System.getProperty("user.dir") + "\\reports\\myReport.html";
		File extendReport = new File(pathOfExtendReport);
		try {
			Desktop.getDesktop().browse(extendReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
