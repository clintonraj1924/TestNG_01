package org.demo.qa.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base {
	WebDriver driver;
	public WebDriver initializeBrowser(String browserName) {
	
		if(browserName.equals("Chrome")) {
			driver=new ChromeDriver();
		}
		else if (browserName.equals("Edge")) {
			driver=new EdgeDriver();
		}
		else if (browserName.equals("Safari")) {
			driver=new SafariDriver();
		}
		return driver;
	}
	public void openApp() throws InterruptedException {
		driver.manage().window().maximize();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.get("https://tutorialsninja.com/demo/");
		Thread.sleep(5000);
		
	}
}
