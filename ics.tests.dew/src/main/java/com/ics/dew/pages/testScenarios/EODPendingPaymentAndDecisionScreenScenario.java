package com.ics.dew.pages.testScenarios;

import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewPendingPaymentsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;


public class EODPendingPaymentAndDecisionScreenScenario implements Scenario<DecisionEngineWorkflowPage, DewPendingPaymentsPage> 
{
	@Override
	public DewPendingPaymentsPage run(DecisionEngineWorkflowPage entry) {		
	return entry
			.dewProductLoginAlertAutentication(ICSPropertiesConfig.getDewUsername(), ICSPropertiesConfig.getDewPassword())
			.clickPendingPaymentMenu()
			.passEodUrl(" ")
			.validateEodAssertMessage()
			.acceptEodAlert()
			.clickStartProcessing()
			.passEodUrl(" ")
			.validateEodAssertMessage()
			.acceptEodAlert()
			.clickStartProcessing()
			.passEodUrl(" ")
			.validateEodAssertMessage()
			.acceptEodAlert()
			.clickStartProcessing()
			.passEodUrl(" ")
			.validateEodAssertMessage()
			.acceptEodAlert()
			.clickStartProcessing()
			.passEodUrl(" ")
			.validateEodAssertMessage()
			.acceptEodAlert()
			.clickStartProcessing();
	}
}

