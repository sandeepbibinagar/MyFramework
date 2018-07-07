package com.ics.dew.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

public class DecisionEngineWorkflowPage extends ICSPageUtilis {
	@FindBy(xpath = "//span[@expanded-text='Search Items']")
	private static WebElement linkSearchItemsMenu;
	
	@FindBy(xpath = "//span[@expanded-text='Pending Payments']")
	private static WebElement link_PendingPaymentMenu;

	public DecisionEngineWorkflowPage(WebDriver driver) {
		super(driver);
	}
	
	public DecisionEngineWorkflowPage(WebDriver driver, String url) {
		super(driver, url);
	}
	
	public DewSearchItemsPage clickSearchItemsMenu() {	
		actionMouseClickEvent(linkSearchItemsMenu);		
		pause(20000);	
		return new DewSearchItemsPage(driver);		
	}	
	
	
	public DecisionEngineWorkflowPage dewProductLoginAlertAutentication(String userName, String password) 
	{
		
		alertAuthentication(userName, password);
		pause(10000);
		
		
		return this;
	}	
	
	public DewPendingPaymentsPage clickPendingPaymentMenu() {	
		actionMouseClickEvent(link_PendingPaymentMenu);		
		pause(10000);	
		return new DewPendingPaymentsPage(driver);		
	}
}
