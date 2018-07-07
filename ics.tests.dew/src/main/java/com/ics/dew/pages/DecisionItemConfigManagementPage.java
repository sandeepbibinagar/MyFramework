package com.ics.dew.pages;

import org.openqa.selenium.WebDriver;

import com.ics.seleniumCoreUtilis.ICSPageUtilis;

public class DecisionItemConfigManagementPage extends ICSPageUtilis {
	
	public DecisionItemConfigManagementPage(WebDriver driver) {
		super(driver);
	}
	
	public DecisionItemConfigManagementPage(WebDriver driver, String url) {
		super(driver, url);
	}
	
	public DecisionItemConfigManagementPage PaymentDecisionPaidByOperator() {	
		
		return new DecisionItemConfigManagementPage(driver);		
	}
	
	public DecisionItemConfigManagementPage PaymentDecisionNotPaidByOperator() {	
		
		return new DecisionItemConfigManagementPage(driver);		
	}
}
