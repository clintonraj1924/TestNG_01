package org.sample.TestNG_01;

import org.demo.qa.base.Base;
import org.demo.qa.util.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Login extends Base {
	public Login() {
		super();
	}
	WebDriver driver;

	@BeforeMethod
	public void setup() throws InterruptedException {
		//loadPropertiesFile(); //instead of using Base constructor -->login
		driver = initializeBrowser(prop.getProperty("browserName"));
		openApp();
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1)
	public void veryfyLoginWithValidCredentials() {

		driver.findElement(By.id("input-email")).sendKeys("test_testng@yopmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test123$");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"TestNg-->Edit your account information");

	}

	@Test(priority = 2)
	public void veryfyLoginWithCredentials() {

		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("test123$1234");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualWarningMess = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String expectedWarningMess = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMess.contains(expectedWarningMess), "Expected warning mess dosint come");

	}

	@Test(priority = 3)
	public void veryfyLoginWithInValidEmailAndValidPossword() {

		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys("test123$");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualWarningMess = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String expectedWarningMess = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMess.contains(expectedWarningMess), "Expected warning mess dosint come");

	}

	@Test(priority = 4)
	public void veryfyLoginWithValidEmailAndInValidPossword() {

		driver.findElement(By.id("input-email")).sendKeys("test_testng@yopmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test123$123");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualWarningMess = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String expectedWarningMess = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMess.contains(expectedWarningMess), "Expected warning mess dosint come");

	}

	@Test(priority = 5)
	public void veryfyLoginWithOutCredentials() {

		driver.findElement(By.id("input-email")).sendKeys("");
		driver.findElement(By.id("input-password")).sendKeys("");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualWarningMess = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String expectedWarningMess = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMess.contains(expectedWarningMess), "Expected warning mess dosint come");

	}
}
