package com.ics.dew.pages.testScenarios;

import java.io.IOException;

import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewSearchItemsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;

public class PendingPaymentSingleWorkFLowItems implements Scenario<DecisionEngineWorkflowPage, DewSearchItemsPage> 
{
	@Override
	public DewSearchItemsPage run(DecisionEngineWorkflowPage entry) throws IOException {		
	return entry
			
			
			/*made some changes to remove errors in .selectWorkstream()/ and some others just */
					.dewProductLoginAlertAutentication(ICSPropertiesConfig.getDewUsername(), ICSPropertiesConfig.getDewPassword())
					.clickPendingPaymentMenu()
					//code to be included which returns driver for DewSearchItemsPage
					.clickSearchItemsMenu()
					.selectWorkgroup()
					.selectWorkstream()
					.clickSearchButton()
				//	.executeEODSSISPackage(null)
					.selectWorkgroup()
					.selectWorkstream()
					.clickSearchButton()
					.validateTotalNoOfItems()
					.validateFinalDecisionOfItem()
					.validatePaymentPendingButtons();
	}
}
