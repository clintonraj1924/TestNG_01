package org.demo.qa.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	public static final int IMPLICIT_WAIT_TIME=10;
	public static final int PAGE_LOAD_TIME=5;
	
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String TimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "test_testng" + TimeStamp + "@yopmail.com";
	}
	public static String  captureScreenShort(WebDriver driver, String testName) {
		File srcScreenShort = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String destinationOfScreenShortPath = System.getProperty(("user.dir") + "\\ScreenShort\\" + testName + "png");
		try {
			FileHandler.copy(srcScreenShort, new File(destinationOfScreenShortPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return destinationOfScreenShortPath;
	}
}
