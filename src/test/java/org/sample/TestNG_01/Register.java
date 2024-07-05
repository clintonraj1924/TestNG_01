package org.sample.TestNG_01;

import java.time.Duration;

import org.demo.qa.Utilities;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Register {
	WebDriver driver;
	@Test
	public void veryfyRegisteringAnAccountWithMandateryFields() throws InterruptedException {
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
		driver.findElement(By.name("//input[@class='btn btn-primary']"));
		driver.findElement(By.xpath("//input[@class='btn btn-primary']")).click();
	}

}
