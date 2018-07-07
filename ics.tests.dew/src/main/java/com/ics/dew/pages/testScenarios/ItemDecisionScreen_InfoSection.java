package com.ics.dew.pages.testScenarios;
import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewPendingPaymentsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Scenario;
import com.microsoft.windowsazure.management.configuration.PublishSettingsLoader;
import com.relevantcodes.extentreports.ExtentReports;

public class ItemDecisionScreen_InfoSection implements Scenario<DecisionEngineWorkflowPage, DewPendingPaymentsPage> 
{
	@Override
	public DewPendingPaymentsPage run(DecisionEngineWorkflowPage entry)  {		
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
					.validateItemDecisionInfo();
					
	
	
	}
}
