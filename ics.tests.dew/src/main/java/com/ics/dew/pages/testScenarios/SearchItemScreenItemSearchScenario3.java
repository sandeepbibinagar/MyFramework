package com.ics.dew.pages.testScenarios;

import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewSearchItemsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;
import com.relevantcodes.extentreports.ExtentTest;

public class SearchItemScreenItemSearchScenario3 implements Scenario<DecisionEngineWorkflowPage, DewSearchItemsPage> 
{
	
	
	@Override
	public DewSearchItemsPage run(DecisionEngineWorkflowPage entry) throws InterruptedException {		
		return entry
				//.dewProductLoginAlertAutentication(ICSPropertiesConfig.getDewUsername(), ICSPropertiesConfig.getDewPassword())
				.clickSearchItemsMenu()

				.selectFromDate()
				.valuetextUniqueIdentifier()
				.valuetextAccountNumber()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				
				.selectFromDate()
				.valuetextUniqueIdentifier()
				.valuetextSerial()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextUniqueIdentifier()
				.valuetextAmount()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextUniqueIdentifier()
				.valuetextUserName()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextAccountNumber()
				.selectFinalPaymentDecision()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextAccountNumber()
				.valuetextSerial()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextAccountNumber()
				.valuetextAmount()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextAccountNumber()
				.valuetextUserName()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextSerial()
				.selectFinalPaymentDecision()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextSerial()
				.valuetextUserName()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextAccountNumber()
				.valuetextAmount()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextAmount()
				.selectFinalPaymentDecision()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextAmount()
				.valuetextUserName()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				.selectFromDate()
				.valuetextUserName()
				.selectFinalPaymentDecision()
				.clickSearchButton()
				.validateMessagesOnClickSearch()
				
				
				;

	}
}
