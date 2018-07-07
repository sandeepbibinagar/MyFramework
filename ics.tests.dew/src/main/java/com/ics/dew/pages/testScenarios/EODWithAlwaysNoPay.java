package com.ics.dew.pages.testScenarios;

import java.io.IOException;

import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewSearchItemsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;

public class EODWithAlwaysNoPay implements Scenario<DecisionEngineWorkflowPage, DewSearchItemsPage> 
{
	@Override
	public DewSearchItemsPage run(DecisionEngineWorkflowPage entry) throws IOException {		
	return entry
					.dewProductLoginAlertAutentication(ICSPropertiesConfig.getDewUsername(), ICSPropertiesConfig.getDewPassword())
					.clickSearchItemsMenu()
					.selectWorkgroup()
					.selectWorkstream()
					.clickSearchButton()
					//.executeEODSSISPackage(" ")
					.selectWorkgroup()
					.selectWorkstream()
					.clickSearchButton()
					.validateTotalNoOfItems()
					.validateFinalDecisionOfItem();
	}
}
