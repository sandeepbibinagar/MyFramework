package com.ics.dew.pages.testScenarios;

import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewSearchItemsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;

public class EodSearchMessageScenario implements Scenario<DecisionEngineWorkflowPage, DewSearchItemsPage> 
{
	@Override
	public DewSearchItemsPage run(DecisionEngineWorkflowPage entry) {		
	return entry
			.dewProductLoginAlertAutentication(ICSPropertiesConfig.getDewUsername(), ICSPropertiesConfig.getDewPassword())
			.clickSearchItemsMenu()
			.passEodUrl(" ")
			.validateEodAssertMessage()
			.acceptEodAlert()
			.passEodUrl(" ")
			.validateEodAssertMessage()
			.acceptEodAlert();
	}
}