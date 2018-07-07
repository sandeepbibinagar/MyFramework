package com.ics.agency.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

public class OpenAgencyPortalPage extends ICSPageUtilis{
	
	
	private String url;
	@FindBy(id = "Submit")
	private static WebElement submit;	
	
	@FindBy(id = "FrmAgency")
	private static WebElement agencyFrame;
	
	@FindBy(id = "overridelink")
	private static WebElement securityOverrideLink;
	
	@FindBy(id = "submit")
	private static WebElement openAgencyPortal;
	
	public OpenAgencyPortalPage(WebDriver driver) {
		super(driver);		
	}

	public OpenAgencyPortalPage(WebDriver driver, String url) {
		super(driver, url);		
	}	
	
	public static WebDriver launchDriver(WebDriver driver)
	{		
		startDriverWithUrl(driver);
		return driver;
	}
	
	public BankLandingPage clickOpenAgencyPortalButton()
	{	
		clickElement(submit);
		return new BankLandingPage(driver);
	}
	
	public OpenAgencyPortalPage alertAuthentication()
	{
		alertAuthentication("Pramania", "#July@2016");
		return this;
	}
	
	public OpenAgencyPortalPage getUrl()
	{		
		url=driver.getCurrentUrl();
		System.out.println(url);
		return this;
	}

	public BankLandingPage navigateToErrorPage()
	{		
		driver.navigate().to(ICSPropertiesConfig.getBrandingIdMoreCharsUrl());
		return new BankLandingPage(driver);
	}
	public OpenAgencyPortalPage handleDrivers()
	{		
		driver.close();
		return this;
	}

	public void ClickOnsecuritylink() {
		clickElement(securityOverrideLink);			
	}

}
