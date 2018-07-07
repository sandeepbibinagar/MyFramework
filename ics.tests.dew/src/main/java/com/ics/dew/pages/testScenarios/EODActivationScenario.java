package com.ics.dew.pages.testScenarios;

import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewPendingPaymentsPage;
import com.ics.seleniumCoreUtilis.Scenario;

public class EODActivationScenario implements Scenario<DecisionEngineWorkflowPage, DewPendingPaymentsPage> 
{
	@Override
	public DewPendingPaymentsPage run(DecisionEngineWorkflowPage entry) {		
	return entry
			.clickPendingPaymentMenu()
			.selectWorkgroup()
			//Add code
			.selectWorkgroup()
			.selectWorkStream()
			.clickStartProcessing();
			//add code
			
	}
}
