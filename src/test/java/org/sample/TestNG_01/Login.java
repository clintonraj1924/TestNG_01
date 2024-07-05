package org.sample.TestNG_01;

import java.time.Duration;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Login {
	WebDriver driver;
	public void tearDown() {
		driver.quit();
	}
	@Test(priority = 1)
	public void veryfyLoginWithValidCredentials() throws InterruptedException {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("test_testng@yopmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test123$");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),"TestNg-->Edit your account information");
		
	}
	
	@Test(priority  =2)
	public void veryfyLoginWithCredentials() throws InterruptedException {
	    driver=new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("test_testng"+genrateTimeStamp()+"@yopmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test123$1234");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualWarningMess= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMess= "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMess.contains(expectedWarningMess),"Expected warning mess dosint come");

	}
	@Test(priority  =3)
	public void veryfyLoginWithInValidEmailAndValidPossword() throws InterruptedException {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("test_testng"+genrateTimeStamp()+"@yopmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test123$");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualWarningMess= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMess= "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMess.contains(expectedWarningMess),"Expected warning mess dosint come");

	}
	@Test(priority  =4)
	public void veryfyLoginWithValidEmailAndInValidPossword() throws InterruptedException {
	    driver=new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("test_testng@yopmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test123$123");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualWarningMess= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMess= "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMess.contains(expectedWarningMess),"Expected warning mess dosint come");

	}
	@Test(priority  =5)
	public void veryfyLoginWithOutCredentials() throws InterruptedException {
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[text()=\"My Account\"]")).click();
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.id("input-email")).sendKeys("test_testng"+genrateTimeStamp()+"@yopmail.com");
		driver.findElement(By.id("input-password")).sendKeys("test123$");
		driver.findElement(By.xpath("//input[@value=\"Login\"]")).click();
		String actualWarningMess= driver.findElement(By.xpath("//div[contains(@class,'alert-dismissible')]")).getText();
		String expectedWarningMess= "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMess.contains(expectedWarningMess),"Expected warning mess dosint come");

	}
	
	public String genrateTimeStamp() {
		Date date = new Date();
		return (date.toString().replace(" ", "_").replace(":", "_"));
		
	}

}
