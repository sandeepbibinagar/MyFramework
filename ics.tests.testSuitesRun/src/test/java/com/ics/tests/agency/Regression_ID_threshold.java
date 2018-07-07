//<copyright  file="Regression_IncomingDebits.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>
package com.ics.tests.agency;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ics.agency.entities.OpenAgencyPageScenario;
import com.ics.agency.pages.AgencyIdentityProviderPage;
import com.ics.agency.pages.BankLandingPage;
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;
 
/******************MODULE HEADER*****************************************
 * Module Name -  Regression_ID_threshold.java
 ************************************************************************ 
 * Date -  11/09/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the decisioning of items in Incoming Debits screen with above and below threshold values
 ***********************************************************************/
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_ID_threshold extends ICSAutomationCommonSetup{

	@BeforeMethod
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	@AfterMethod()
	public void quitDriver(){
		DRIVER.quit();
	}
	
	public static String server = getAgencyValueFromDataSheet("serverNameIA");
	public static String server_dew = getAgencyValueFromDataSheet("serverNameDEW");
	public static String db = getAgencyValueFromDataSheet("agencyDatabase");
	public static String dew_db = getAgencyValueFromDataSheet("dewDatabase");
	public static String IA_db = getAgencyValueFromDataSheet("imageArchiveDatabase");
	public static String path_LBG_IncomingDebits= getAgencyDataFromPath("incomingDebits")+"\\LBG\\";	
	public static String incomingDebitMsg06_fileName=getAgencyValueFromDataSheet("incomingDebitMsg06");
	public static String incomingDebitMsg06_threshold_fileName=getAgencyValueFromDataSheet("incomingDebitMsg06Threshold");
	public static String delete_dewmsg06_fileName=getAgencyValueFromDataSheet("deleteDewTables");
	
	
	/* Test Case: 
	 * Author:Deepa Ramu
	 * Date created:24/04/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Load Data for threshold scenarios for LBG with approvals off
	 * - use intelligentfinancestjamesplace agency (approvals are off ,threshold set to 250,no pay decision)
	 * Expected: 
	 *   1. Incoming Debits data should be loaded for LBG*/

	@Test(priority = 1)
	public void dataLoad_Threshold_LBG() throws Exception
	{	
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,incomingDebitMsg06_threshold_fileName);
	} 

	/* Test Case: 84628
	 * Author:Deepa Ramu
	 *  Date created:31/08/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Pay Decision - below threshold (Approvals off scenarios only applicable to LBG)
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'Pay' status 
	 *   - use intelligentfinancestjamesplace agency (approvals are off ,threshold set to 250)
	 */
	@Test(priority = 2)
	public void test_case_84628_payDecision_approvalOff_belowThreshold() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus()
		.setBelowThresholdAmount();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));	
	}
	
	/* Test Case: 85067
	 * Author:Deepa Ramu
	 *  Date created:31/08/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Pay Decision - above threshold (Approvals off scenarios only applicable to LBG)
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'Pay' status 
	 *   - use intelligentfinancestjamesplace agency (approvals are off ,threshold set to 250)
	 */
	@Test(priority = 3)
	public void test_case_84628_payDecision_approvalOff_aboveThreshold() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus()
		.setAboveThresholdAmount();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));	
	}
	
	/* Test Case: 84629
	 * Author:Deepa Ramu
	 *  Date created:01/09/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Pay Decision - below threshold -all no pay reason(Approvals off scenarios only applicable to LBG)
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'Pay' status 
	 *   - use intelligentfinancestjamesplace agency (approvals are off ,threshold set to 250,no pay decision)
	 */
	@Test(priority = 4)
	public void test_case_84629_payDecision_nopay_approvalOff_belowThreshold() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus()
		.setBelowThresholdAmount();
	//	new BankLandingPage(DRIVER)
		//.verifyAllNoPayReason();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
	}
	
	/* Test Case: 85557
	 * Author:Deepa Ramu
	 *  Date created:01/09/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Pay Decision - above threshold -all no pay reason(Approvals off scenarios only applicable to LBG)
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'completed' status 
	 *   - use intelligentfinancestjamesplace agency (approvals are off ,threshold set to 250,no pay decision)
	 */
	@Test(priority = 5)
	public void test_case_85557_payDecision_nopay_approvalOff_aboveThreshold() throws Exception
	{

		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus()
		.setAboveThresholdAmount();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		//new BankLandingPage(DRIVER)
		//.verifyAllNoPayReason();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
	}
	
	/* Test Case: 84630
	 * Author:Deepa Ramu
	 *  Date created:31/08/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: kappa Decision_pay(Approvals off scenarios only applicable to LBG)
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'Pay' status 
	 */
	@Test(priority = 6)
	public void test_case_84630_kappaDecision_pay_approvalOff() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingFraudDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setKappaDecisionStatus();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));	
	}
	
	/* Test Case: 84631
	 * Author:Deepa Ramu
	 *  Date created:01/09/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: kappa Decision - no pay approvals off(Approvals off scenarios only applicable to LBG)
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'Pay' status 
	 *   - use intelligentfinancestjamesplace agency (approvals are off ,threshold set to 250,no pay decision)
	 */
	@Test(priority = 7)
	public void test_case_84631_kappaDecision_nopay_approvalOff() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingFraudDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setKappaDecisionStatus();
		//new BankLandingPage(DRIVER)
		//.verifyAllNoPayReason();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
	}
	
	/* Test Case: 109726
	 * Author:Deepa Ramu
	 *  Date created:02/10/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: duplicate Image - verify duplicate image is populated when clicked on view button
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   - use intelligentfinancestjamesplace agency (duplicate data loaded only for intelligentfinancestjamesplace)
	 */
	@Test(priority = 7)
	public void test_case_109726_duplicateImage() throws Exception
	{
		runStoredProcedureCall(server,IA_db,path_LBG_IncomingDebits,getAgencyValueFromDataSheet("duplicateImage1"));
		runStoredProcedureCall(server,IA_db,path_LBG_IncomingDebits,getAgencyValueFromDataSheet("duplicateImage2"));
		runStoredProcedureCall(server,IA_db,path_LBG_IncomingDebits,getAgencyValueFromDataSheet("duplicateImage3"));
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingFraudDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.applyFiltersBasedOnAccountNumber(getAgencyValueFromDataSheet("duplicatedataAccount"))
		.validateDuplicateImageData();

	}
	
	
}



