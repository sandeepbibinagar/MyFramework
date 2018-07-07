package com.ics.testNgSeleniumSetup;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class ICSAutomationDriverSetup extends ICSAutomationCommonSetup {
	@BeforeClass(groups={"sanity"})
	public static void driverSetup(){
		DRIVER = startDriver();
	}	
	
	@AfterClass()
	public void quitDriver(){
		DRIVER.quit();
	}
}
