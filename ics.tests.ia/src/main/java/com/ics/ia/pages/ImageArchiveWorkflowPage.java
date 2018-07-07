/*  
<Copyright file="ImageArchiveWorkflowPage.java" company="iPSL">
Copyright © iPSL 2017 All rights are reserved.
Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
is prohibited without the prior written permission of the copyright owner.
</copyright> 
*/
 
/* ************************* Module Header ************************************************
 * Module Name : This  module handles the login/logout  scenario for the application
 * Date : 10/05/2017
 * Created By : Sandeep Bibinagar
 * Description : This class contains Login Scenario
 * 
 ******************** AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
 Description : Updating class as per java coding standards
 ****************************************************************************************** */


package com.ics.ia.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

	public class ImageArchiveWorkflowPage extends ICSPageUtilis {	
	
	protected static boolean flag=false;
	public static boolean tempResult;
	
	public ImageArchiveWorkflowPage(WebDriver driver) 
	{
		super(driver);
	}
	
	public ImageArchiveWorkflowPage(WebDriver driver, String url) 
	{
		super(driver, url);
	}
	
	@FindBy(xpath = "//div[@id='nav-title']")
	private static WebElement clickOpertionalUser;
	@FindBy(xpath = "//span[@class='site-menu-title']")
	private static WebElement clickHomeMenu;
	@FindBy(xpath = "input[@id='ProcessingDate']")
	private static WebElement DatePicker;
	@FindBy(xpath = "a[@data-slug='Access Queries']")
	private static WebElement clickAccessQueries;
	@FindBy(xpath = "a[@data-slug='Item Search Query]")
	private static WebElement clickItemSearchQuery;
	@FindBy(xpath  = "//ul[@class='dropdown-menu']/li/a")
	private static WebElement LogoutIcon;
	@FindBy(xpath  = "//span[@id='spanUserName']")
	private static WebElement UserNameLink;
	
	// Description : This method validates presence of window based login alert pop up and handles the login scenario for the application
	public SearchItemPage iaLoginAlertAutentication(String userName, String password)
	{
		alertAuthentication1(userName,password);
		pause(1000);
		MaximizeBrowser();
		pause(3000);
		return new SearchItemPage(driver);
	}	
	
	// Description : This method handles the Logout scenario 	
	public ImageArchiveWorkflowPage validateLogout() throws AWTException 
	{
		pause(3000);
		clickElementEnter(UserNameLink);
		pause(2000);
		clickElementEnter(LogoutIcon);
		pause(2000);
		tempResult=	verifyTrue(LogoutIcon.isDisplayed(), "user should be able to log out");
		publishResults(tempResult,"User able to log out : "+LogoutIcon.isDisplayed()+"","User should be able to logout", "Validaiton for logout");
		for(String currentWindow : driver.getWindowHandles()){
		driver.switchTo().window(currentWindow);
	}
		pause(3000);
		System.out.println("The title is pop up : "+driver.getTitle());
		Robot r=new Robot();
		r.keyPress(KeyEvent.VK_ENTER);
		r.delay(1000);
		driver.quit();
		return this;
	}
		
}