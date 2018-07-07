package com.ics.agency.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

public class RolesSelectionAgencyPortalPage extends ICSPageUtilis{
	
	public RolesSelectionAgencyPortalPage(WebDriver driver) {
		super(driver);
	}
	
	public RolesSelectionAgencyPortalPage(WebDriver driver, String url) {
		super(driver,url);
	}
	
	@FindBy(linkText = "SUP")
	private static WebElement lnkSUPRole;
	
	public BankLandingPage clickSUPRole()
	{
		clickElement(lnkSUPRole);
		return new BankLandingPage(driver);
	}

}
