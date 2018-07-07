package com.ics.dew.pages.testScenarios;
import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewPendingPaymentsPage;
import com.ics.dew.pages.DewSearchItemsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;
import com.microsoft.windowsazure.management.configuration.PublishSettingsLoader;
import com.relevantcodes.extentreports.ExtentReports;

public class ReleaseOwnershipFunctionalityWithDecisionClickNext implements Scenario<DecisionEngineWorkflowPage, DewPendingPaymentsPage> 
{
	@Override
	public DewPendingPaymentsPage run(DecisionEngineWorkflowPage entry) throws InterruptedException  {		
	return entry
			
				//	.dewProductLoginAlertAutentication(ICSPropertiesConfig.getDewUsername(), ICSPropertiesConfig.getDewPassword())
					.clickPendingPaymentMenu()
					.selectWorkgroup()
					.selectWorkStream()
					.selectSubWorkStream()
					.selectProcess()
					.valuetextTeamName()
					.valuetextEmail()
					.valuetextFaxNumber()
					.valuetextPhoneNumber()
					.clickConfirmContact()
					.clickStartProcessing()
					.validateItemOwnershipAfterDecisionClickNext()
					
					
					;
						
	}
}
