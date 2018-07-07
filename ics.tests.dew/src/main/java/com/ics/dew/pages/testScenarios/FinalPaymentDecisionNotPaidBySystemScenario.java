package com.ics.dew.pages.testScenarios;

import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewSearchItemsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;

public class FinalPaymentDecisionNotPaidBySystemScenario implements Scenario<DecisionEngineWorkflowPage, DewSearchItemsPage> 
{
	@Override
	public DewSearchItemsPage run(DecisionEngineWorkflowPage entry) throws InterruptedException {		
	return entry
			.clickSearchItemsMenu()
			.selectFromDate()
			.selectWorkgroup()
			.valuetextUniqueIdentifier()
			.clickSearchButton()
			.validateFinalCheckbox()
			.clickEXPLink()	
			.validateFinalPaymentStatusNotPaidByPosting()
			;
	}
}

