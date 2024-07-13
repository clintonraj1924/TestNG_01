package org.sample.TestNG_01;

import java.time.Duration;

import org.demo.qa.base.Base;
import org.demo.qa.util.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(org.demo.qa.myListener.MyListener.class)
public class RegisterTest extends Base {
	WebDriver driver;
	@Test(priority = 1)
	public void veryfyRegisteringAnAccountWithMandateryFields() throws InterruptedException {
		driver = initializeBrowser("Edge");
		openApp();
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Clinton"); //sendKeys(dataProp.getProperty("firstName"));
		driver.findElement(By.id("input-lastname")).sendKeys("Raj");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9843461696");
		driver.findElement(By.id("input-password")).sendKeys("test123$");
		driver.findElement(By.id("input-confirm")).sendKeys("test123$");
		Thread.sleep(5000);
		driver.findElement(By.name("//input[@class='btn btn-primary']"));
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		String actualSucessHeding = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSucessHeding, "Your Account Has Been Created!", "Account Sucess page is not displayed");
		
		driver.quit();
	}
	
	@Test(priority = 2)
	public void veryfyRegisteringAnAccountWithAllFields() throws InterruptedException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Clinton");
		driver.findElement(By.id("input-lastname")).sendKeys("Raj");
		driver.findElement(By.id("input-email")).sendKeys(Utilities.generateEmailWithTimeStamp());
		driver.findElement(By.id("input-telephone")).sendKeys("9843461696");
		driver.findElement(By.id("input-password")).sendKeys("test123$");
		driver.findElement(By.id("input-confirm")).sendKeys("test123$");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("//input[@class='btn btn-primary']"));
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		String actualSucessHeding = driver.findElement(By.xpath("//div[@id='content']/h1")).getText();
		Assert.assertEquals(actualSucessHeding, "Your Account Has Been Created!", "Account Sucess page is not displayed");
		
		driver.quit();
	}
	@Test(priority = 3)
	public void veryfyRegisteringAnExistingAccount() throws InterruptedException {
		driver = initializeBrowser("Edge");
		openApp();
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.id("input-firstname")).sendKeys("Clinton");
		driver.findElement(By.id("input-lastname")).sendKeys("Raj");
		driver.findElement(By.id("input-email")).sendKeys("test_testng@yopmail.com");
		driver.findElement(By.id("input-telephone")).sendKeys("9843461696");
		driver.findElement(By.id("input-password")).sendKeys("test123$");
		driver.findElement(By.id("input-confirm")).sendKeys("test123$");
		driver.findElement(By.xpath("//input[@name='newsletter'][@value='1']")).click();
		driver.findElement(By.name("//input[@class='btn btn-primary']"));
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		String actualWarningMess = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String expectedWarningMess = "Warning: E-Mail Address is already registered!";
		Assert.assertTrue(actualWarningMess.contains(expectedWarningMess), "Expected warning mess dosint come");
		
		driver.quit();
	}
	
	@Test(priority = 4)
	public void veryfyRegisteringEnterAnyOfFields() throws InterruptedException {
		driver = initializeBrowser("Edge");
		openApp();
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Register")).click();
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
		
		
		String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]"))
				.getText();
		String expectedPrivacyPolicyWarning="Warning: You must agree to the Privacy Policy!";
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(expectedPrivacyPolicyWarning), "Expected Privacy Policy warning mess dosint come");
		
		String actualFirstnameWARNING= driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		String expectedFirstnameWarningMess= "First Name must be between 1 and 32 characters!";
		Assert.assertEquals(actualFirstnameWARNING, expectedFirstnameWarningMess,"NotAppier: FirstName Warning Message");
		
		String actualLasttnameWARNING= driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		String expectedLastnameWarningMess= "Last Name must be between 1 and 32 characters!";
		Assert.assertEquals(actualLasttnameWARNING, expectedLastnameWarningMess,"NotAppier: LastName Warning Message");
		
		String actualEmaileWARNING= driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		String expectedEmailWarningMess= "E-Mail Address does not appear to be valid!";
		Assert.assertEquals(actualEmaileWARNING, expectedEmailWarningMess,"NotAppier: Email Warning Message");
		
		String actualTelephoneWARNING= driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		String expectedTelephoneWarningMess= "Telephone must be between 3 and 32 characters!";
		Assert.assertEquals(actualTelephoneWARNING, expectedTelephoneWarningMess,"NotAppier: Telephone Warning Message");
		
		String actualPasswordWARNING= driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		String expectedPasswordWarningMess= "Password must be between 4 and 20 characters!";
		Assert.assertEquals(actualPasswordWARNING, expectedPasswordWarningMess,"NotAppier: Password Warning Message");
		
		
		driver.quit();
	}


}
