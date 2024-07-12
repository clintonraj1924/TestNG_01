package org.demo.qa.myListener;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
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
		sparkReporter.config().setTimeStampFormat("dd/MM/yyy hh:mm:ss");

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
		WebDriver driver = null; //for ScreenShort we need driver. so this line take a driver  
		try {
		driver=(WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance()); 
		}
		catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		File srcScreenShort=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String destinationOfScreenShortPath = System.getProperty(("user.dir")+"\\ScreenShort\\"+test+"png");
		try {
		FileHandler.copy(srcScreenShort,new File(destinationOfScreenShortPath));
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(destinationOfScreenShortPath);
		test.log(Status.INFO, result.getThrowable());
		test.log(Status.FAIL, test+ "Got Faild");
		
		
		
//		test.log(Status.FAIL, "Test Case Faild is:" + result.getName());
//		test.log(Status.FAIL, "Test Case Faild cause is:" + result.getThrowable());
	}

	public void onTestSkiped(ITestResult result) {
		test = extent.createTest(result.getName()); // create a new entry in the report
		test.log(Status.SKIP, "Test Case skiped is:" + result.getName()); // update stsus p/f/s

	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
