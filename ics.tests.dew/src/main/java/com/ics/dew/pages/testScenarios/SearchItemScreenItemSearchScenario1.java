package com.ics.dew.pages.testScenarios;

import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewSearchItemsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;
import com.relevantcodes.extentreports.ExtentTest;

public class SearchItemScreenItemSearchScenario1 implements Scenario<DecisionEngineWorkflowPage, DewSearchItemsPage> 
{
	
	
	@Override
	public DewSearchItemsPage run(DecisionEngineWorkflowPage entry) throws InterruptedException {		
	return entry
					//.dewProductLoginAlertAutentication(ICSPropertiesConfig.getDewUsername(), ICSPropertiesConfig.getDewPassword())
					.clickSearchItemsMenu()
					.pageTitleValidation()
					
					.searchItemdefaultValueValidation()
					.fromDateDefaultValueValidation()
					.toDateDefaultValueValidation()
					.selectFromDate()
					.selectToDate()
					.clickSearchButton()
					.searchCriteriamessageValidation()
					.clickClearButton()
					.selectFromDate()
					.selectWorkgroup()
					.selectWorkstream()
					.clickSearchButton()
					.validateMessagesOnClickSearch()
					
					.isWorkstreamDisabled()
					.isSubWorkstreamDisabled()
					.isProcessEnabled()

					.selectFromDate()
					.selectWorkgroup()
					.selectWorkstream()
					.clickSearchButton()
					.validateMessagesOnClickSearch()
	
					.selectFromDate()
					.selectWorkgroup()
					.processValidation()
					.selectProcess()
					.clickSearchButton()
					.validateMessagesOnClickSearch()
					
					.selectFromDate()	
					.selectWorkgroup()
					.selectFinalPaymentDecision()
					.clickSearchButton()
					.validateMessagesOnClickSearch()

					
					.selectFromDate()
					.selectWorkgroup()
					.valuetextAccountNumber()
					.clickSearchButton()
					.validateMessagesOnClickSearch()
					
					
					.selectFromDate()
					.selectWorkgroup()
					.valuetextAccountSortCode()
					.clickSearchButton()
					.validateMessagesOnClickSearch()

					
					.selectFromDate()
					.selectWorkgroup()
					.valuetextSerial()
					.clickSearchButton()
					.validateMessagesOnClickSearch()

					
					.selectFromDate()
					.selectWorkgroup()
					.valuetextAmount()
					.clickSearchButton()
					.validateMessagesOnClickSearch()

					
					.selectFromDate()
					.selectWorkgroup()
					.valuetextUserName()
					.clickSearchButton()
					.validateMessagesOnClickSearch()

					
					.selectFromDate()
					.selectWorkgroup()
					.valuetextUniqueIdentifier()
					.clickSearchButton()
					.validateMessagesOnClickSearch()
					
					.selectFromDate()
					.selectWorkgroup()
					.selectWorkstream()
					.selectFinalPaymentDecision()
					.clickSearchButton()
					.validateMessagesOnClickSearch()
					;			
					
	}
}
