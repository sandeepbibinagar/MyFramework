package com.ics.dew.pages.testScenarios;

import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewSearchItemsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;
import com.relevantcodes.extentreports.ExtentTest;

public class SearchItemScreenItemSearchScenario2 implements Scenario<DecisionEngineWorkflowPage, DewSearchItemsPage> 
{

	@Override
	public DewSearchItemsPage run(DecisionEngineWorkflowPage entry) throws InterruptedException {		
	return entry
//			.dewProductLoginAlertAutentication(ICSPropertiesConfig.getDewUsername(), ICSPropertiesConfig.getDewPassword())
			.clickSearchItemsMenu()
		
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.valuetextAccountNumber()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.valuetextAccountSortCode()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.valuetextSerial()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.valuetextAmount()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.valuetextAccountNumber()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.valuetextUserName()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.valuetextUniqueIdentifier()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.selectSubWorkstream()
			.selectProcess()
			.selectFinalPaymentDecision()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.selectSubWorkstream()
			.selectProcess()
			.valuetextAccountNumber()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.selectSubWorkstream()
			.selectProcess()
			.valuetextAccountSortCode()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.selectSubWorkstream()
			.selectProcess()
			.valuetextSerial()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.selectSubWorkstream()
			.selectProcess()
			.valuetextAmount()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.selectSubWorkstream()
			.selectProcess()
			.valuetextUserName()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.selectWorkgroup()
			.selectWorkstream()
			.selectSubWorkstream()
			.selectProcess()
			.valuetextUniqueIdentifier()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			
			.selectFromDate()
			.valuetextAccountSortCode()
			.selectFinalPaymentDecision()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.valuetextAccountSortCode()
			.valuetextAccountNumber()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.valuetextAccountSortCode()
			.selectFinalPaymentDecision()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			.selectFromDate()
			.valuetextAccountSortCode()
			.valuetextUniqueIdentifier()
			.clickSearchButton()
			.validateMessagesOnClickSearch()

			.selectFromDate()
			.valuetextAccountSortCode()
			.valuetextSerial()
			.clickSearchButton()
			.validateMessagesOnClickSearch()

			.selectFromDate()
			.valuetextAccountSortCode()
			.valuetextAmount()
			.clickSearchButton()
			.validateMessagesOnClickSearch()

			.selectFromDate()
			.valuetextAccountSortCode()
			.valuetextUserName()
			.clickSearchButton()
			.validateMessagesOnClickSearch()

			.selectFromDate()
			.valuetextUniqueIdentifier()
			.selectFinalPaymentDecision()
			.clickSearchButton()
			.validateMessagesOnClickSearch()
			
			;
			
  }
}
