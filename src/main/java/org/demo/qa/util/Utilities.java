package org.demo.qa.util;

import java.util.Date;

public class Utilities {
	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String TimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "test_testng" + TimeStamp + "@yopmail.com";
	}
}
