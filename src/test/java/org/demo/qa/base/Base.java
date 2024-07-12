package org.demo.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.demo.qa.util.Utilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Base {
	WebDriver driver;
	public Properties prop;
	public Properties dataProp;
	public Base() {
		prop = new Properties();
		
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\demo\\qa\\config\\config.properties");
		try {
			FileInputStream fis=new FileInputStream(propFile);
			prop.load(fis);
					}
		catch(Throwable e) {
			e.printStackTrace();
		}
		dataProp=new Properties();
		File dataPropFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\demo\\qa\\testData\\testData.properties");
		try {
			FileInputStream dataFis=new FileInputStream(dataPropFile);
			prop.load(dataFis);
					}
		catch(Throwable e) {
			e.printStackTrace();
		}
	}
//	public void loadPropertiesFile() {
//		prop = new Properties();
//		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\org\\demo\\qa\\config\\config.propertys");
//		try {
//			FileInputStream fis=new FileInputStream(propFile);
//			prop.load(fis);
//					}
//		catch(Throwable e) {
//			e.printStackTrace();
//		}
//	}
	public WebDriver initializeBrowser(String browserName) {
	
		if(browserName.equalsIgnoreCase("Chrome")) {
			driver=new ChromeDriver();
		}
		else if (browserName.equalsIgnoreCase("Edge")) {
			driver=new EdgeDriver();
		}
		else if (browserName.equalsIgnoreCase("Safari")) {
			driver=new SafariDriver();
		}
		return driver;
	}
	public void openApp() throws InterruptedException {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		//driver.get("https://tutorialsninja.com/demo/");
		driver.get(prop.getProperty("URL"));
		Thread.sleep(5000);
		
	}
}
