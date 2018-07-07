package com.ics.seleniumCoreUtilis;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

/********************************************************************************************************************/
/* Created By: 	Umamahesh.Mulugu@ipsl.co.uk																			*/
/* Purpose:	 Initialize of InternetExplorerDriver                                                                   */
/********************************************************************************************************************/
public class InternetExplorerInitializer {
	private static DesiredCapabilities capabilities;
/*	private String internetExplorerDriverLocation = this.getClass().getClassLoader().getResource("ieDriver/IEDriverServer.exe").getPath().toString();*/

	public WebDriver initialize(){
		capabilities= DesiredCapabilities.internetExplorer();
		capabilities.setCapability("ignoreZoomSetting", true);
		capabilities.setCapability("EnableNativeEvents", false);		
		System.setProperty("webdriver.ie.driver", "D:\\TFS_WD\\PRATEEK_TFS_WD\\TestAutomation\\ST\\ics-tests-automation\\ics.tests.testSuitesRun\\src\\test\\resources\\ie\\IEDriverServer.exe");
		return new InternetExplorerDriver(capabilities);
	}
	
}
