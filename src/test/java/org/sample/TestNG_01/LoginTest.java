package org.sample.TestNG_01;

import org.demo.qa.base.Base;

import org.demo.qa.pages.HomePage;
import org.demo.qa.pages.LoginPage;
import org.demo.qa.util.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(org.demo.qa.myListener.MyListener.class)
public class LoginTest extends Base {
	public LoginTest() {
		super();
	}
	public WebDriver driver;

	@BeforeMethod
	public void setup() throws InterruptedException {
		//loadPropertiesFile(); //instead of using Base constructor -->login
		driver = initializeBrowser(prop.getProperty("browserName"));
		openApp();
		HomePage homePage = new HomePage(driver);
		homePage.clickMyAccount();
		homePage.selectLoginOption();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

	@Test(priority = 1, dataProvider = "supplyTestData")
	public void veryfyLoginWithValidCredentials(String email, String password) {
		
        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterEmailAdress(email);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"TestNg-->Edit your account information not came");

	}
	@DataProvider
	public Object[][] supplyTestData() {
		Object[][] data= {{"test_testng@yopmail.com","test123$"},
				{"test_testng@yopmail.com","test123$"},
				{"test_testng@yopmail.com","test123$"}};
		return data;
	}

	@Test(priority = 2, dependsOnMethods = {"veryfyLoginWithValidCredentials"})
	public void veryfyLoginWithCredentials() throws InterruptedException {

		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-password")).sendKeys(dataProp.getProperty("invalidPassword"));
		driver.findElement(By.xpath("//input[@value='Login']")).click();
		Thread.sleep(5000);
		String actualWarningMess = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		//String expectedWarningMess = dataProp.getProperty("emailPasswordNoMachWarning");
		String expectedWarningMess ="Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMess.contains(expectedWarningMess), "Expected warning mess dosint come");

	}

	@Test(priority = 3)
	public void veryfyLoginWithInValidEmailAndValidPossword() {

		LoginPage loginPage=new LoginPage(driver);
		loginPage.enterEmailAdress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword("test123$");
        loginPage.clickLoginButton();
		String actualWarningMess = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		//String actualWarningMess = loginPage.retrieveEmailPasswordNotMachingWarnig();
		String expectedWarningMess = "Warning: No match for E-Mail Address and/or Password.";
		//String expectedWarningMess = dataProp.getProperty("emailPasswordNoMachWarning");
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
