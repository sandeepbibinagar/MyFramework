package com.ics.dew.pages.testScenarios;

import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewSearchItemsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;
import com.relevantcodes.extentreports.ExtentTest;

public class SearchResultColumnValidations implements Scenario<DecisionEngineWorkflowPage, DewSearchItemsPage> 
{
	
	
	@Override
	public DewSearchItemsPage run(DecisionEngineWorkflowPage entry) throws Exception {		
	return entry
					//.dewProductLoginAlertAutentication(ICSPropertiesConfig.getDewUsername(), ICSPropertiesConfig.getDewPassword())
					.clickSearchItemsMenu()
					.selectFromDate()
					.selectToDate()
					.selectWorkgroup()
					.selectWorkstream()
					.clickSearchButton()
					.validateItemCountOnSearchPageResult()
					.isExpAllColPresentInHeader()
					.isSortCodeColPresentInHeader()
					.isAccNumberColPresentInHeader()
					.isSerialNumberColPresentInHeader()
					.isAmountColPresentInHeader()
					.isWorkGroupColPresentInHeader()
					.isWorkStreamColPresentInHeader()
					.isProcessColPresentInHeader()
					.isOwnerColPresentInHeader()
					.isFinalColPresentInHeader()
					.isDefaultAppliedColPresentInHeader()
					.isTimestampColPresentInHeader()
					
					;			
					
	}
}
