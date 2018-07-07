//<copyright  file="Regression_IncomingDebits.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>
package com.ics.tests.agency;

import java.sql.ResultSet;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ics.agency.entities.OpenAgencyPageScenario;
import com.ics.agency.pages.AgencyIdentityProviderPage;
import com.ics.agency.pages.BankLandingPage;
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;
 
/******************MODULE HEADER*****************************************
 * Module Name -  Regression_IncomingDebits.java
 ************************************************************************ 
 * Date -  26/07/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of Incoming Debits screen with approvals enabled
 ***********************************************************************/
 
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_IncomingDebits extends ICSAutomationCommonSetup{

	protected static WebDriver DRIVER;
	@BeforeMethod
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	@AfterMethod()
	public void quitDriver(){
		DRIVER.quit();
	}
	
	public static String server = getAgencyValueFromDataSheet("serverNameIA");
	public static String serverAgency = getAgencyValueFromDataSheet("serverNameAgency");
	public static String server_dew = getAgencyValueFromDataSheet("serverNameDEW");
	public static String db = getAgencyValueFromDataSheet("agencyDatabase");
	public static String IA_db = getAgencyValueFromDataSheet("imageArchiveDatabase");
	public static String dew_db = getAgencyValueFromDataSheet("dewDatabase");
	public static String path_HSBC_IncomingDebits=getAgencyDataFromPath("incomingDebits")+"\\HSBC\\";
	public static String path_LBG_IncomingDebits= getAgencyDataFromPath("incomingDebits")+"\\LBG\\";
	public static String otherDataMA01_fileName=getAgencyValueFromDataSheet("otherDataMA01");
	public static String otherDataMA02_fileName=getAgencyValueFromDataSheet("otherDataMA02");
	public static String incomingDebitMsg06_fileName=getAgencyValueFromDataSheet("incomingDebitMsg06");
	public static String incomingDebitMsg06_threshold_fileName=getAgencyValueFromDataSheet("incomingDebitMsg06Threshold");
	public static String delete_dewmsg06_fileName=getAgencyValueFromDataSheet("deleteDewTables");
	
	/* Test Case: 
	 * Author:Deepa Ramu
	 * Date created:24/04/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Load Data for HSBC
	 * Expected: 
	 *   1. Incoming Debits data should be loaded for HSBC*/

	/*@Test(priority = 1)
	public void dataLoad_HSBC() throws Exception
	{
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,delete_dewmsg06_fileName);	
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,incomingDebitMsg06_fileName);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits,otherDataMA01_fileName);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits,otherDataMA02_fileName);
	}*/
	
	/* Test Case: 
	 * Author:Deepa Ramu
	 * Date created:24/04/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Load Data for LBG
	 * Expected: 
	 *   1. Incoming Debits data should be loaded for LBG*/
	@Test(priority = 1)
	public void dataLoad_LBG() throws Exception
	{
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,delete_dewmsg06_fileName);	
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_LBG_IncomingDebits,incomingDebitMsg06_fileName);
		runStoredProcedureCall(server,IA_db,path_LBG_IncomingDebits,otherDataMA01_fileName);
		runStoredProcedureCall(server,IA_db,path_LBG_IncomingDebits,otherDataMA02_fileName);
	}
	

	/* Test Case: test_case_82442_systemLockout
	 * Author:Deepa Ramu
	 *  Date created:13/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: System Lockout
	 * Prerequisite: Set the system lockout time in DB before running this scenario
	 * TableName : config.BankConfiguration ,ColumnName: End of Day Processing Cut Off
	 * Expected: 
	 *   1. Button should be disabled and user will not be able to take any action*/

	@Test(priority = 21)
	public void test_case_82442_systemLockout() throws Exception
	{
		String query = getAgencyValueFromDataSheet("bankConfigStartTime");
		ResultSet rsCore = ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, query);
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.assertPayNoPayDisabled()
		.setAwaitingApprovalStatus()
		.assertApproveDenyApprovalDisabled();
		query = getAgencyValueFromDataSheet("bankConfigEndTime");
		rsCore = ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, query);
	} 	
	
	/* Test Case: 81487
	 * Author:Deepa Ramu
	 *  Date created:25/04/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Pay Decision - decisioner approves and approver approves
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'Pay' status 
	 */
	@Test(priority = 2)
	public void test_case_81487_payDecisionIcon_decisonerApproves_approverApproves() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));	
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.approveDecisionBasedOnAccountFilter(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
	}
	
	/* Test Case: 81489
	 * Author:Deepa Ramu
	 *  Date created:26/04/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Pay decision - decisioner approves and approver rejects
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'Pay Decision'
	 */
	@Test(priority = 3)
	public void test_case_81489_payDecisionIcon_decisonerApproves_approverDenyApproval() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));	
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.clickDenyByApprover(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getPayDecisionStatus());
	}
	
	/* Test Case: 81500
	 * Author:Deepa Ramu
	 *  Date created:26/04/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: No Pay Decision - decisioner rejects and approver approves
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'No Pay' status
	 */
	@Test(priority = 4)
	public void test_case_81500_payDecisionIcon_decisonerDenyApproval_approverApproves() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.approveDecisionBasedOnAccountFilter(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));	
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
	}

	/* Test Case: 81501
	 * Author:Deepa Ramu
	 *  Date created:27/04/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: No Pay Decision - decisioner rejects and approver rejects
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'No pay' status
	 */
	@Test(priority = 5)
	public void test_case_81501_payDecisionIcon_decisonerDenyApproval_approverDenyApproval() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno ,getAgencyValueFromDataSheet("noPayReason"))		
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.clickDenyByApprover(accno)
		.applyFiltersBasedOnAccountNumber(accno);
			BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getPayDecisionStatus());
	}
	
	/* Test Case: 113259
	 * Author:Deepa Ramu
	 *  Date created:01/07/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Kappa Decision - decisioner approves and approver approves
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'Pay' status 
	 */
	@Test(priority = 6)
	public void test_case_113259_kappaDecisionIcon_decisonerApproves_approverApproves() throws Exception
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
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));	
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.approveDecisionBasedOnAccountFilter(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
	}
	
	/* Test Case: 113260
	 * Author:Deepa Ramu
	 *  Date created:04/07/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Kappa decision - decisioner approves and approver rejects
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'Kappa Decision'
	 */
	@Test(priority = 7)
	public void test_case_113260_kappaDecisionIcon_decisonerApproves_approverDenyApproval() throws Exception
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
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));	
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.clickDenyByApprover(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getKappaDecisionStatus());
	}
	
	/* Test Case: 113261
	 * Author:Deepa Ramu
	 *  Date created:04/07/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression:Kappa Decision - decisioner rejects and approver approves
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'No Pay' status
	 */
	@Test(priority = 8)
	public void test_case_113261_kappaDecisionIcon_decisonerDenyApproval_approverApproves() throws Exception
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
		.approveNoPayDecisionBasedOnAccountFilter(accno ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.approveDecisionBasedOnAccountFilter(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));	
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
	}

	/* Test Case: 113263
	 * Author:Deepa Ramu
	 *  Date created:04/07/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Kappa Decision - decisioner rejects and approver rejects
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Status should change to 'Kappa Decision' status
	 */
	@Test(priority = 9)
	public void test_case_81501_kappaDecisionIcon_decisonerDenyApproval_approverDenyApproval() throws Exception
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
		.approveNoPayDecisionBasedOnAccountFilter(accno ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortal("Decisioner",getAgencyValueFromDataSheet("userName"));
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.clickDenyByApprover(accno)
		.applyFiltersBasedOnAccountNumber(accno);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getKappaDecisionStatus());
	}
	/*
	 * Test Case: 81458
	 * Author:Deepa Ramu
	 *  Date created:05/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Verify Debit with all optional icons available
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. All icons should be displayed as expected
	 */
	@Test(priority = 22)
	public void test_case_81458_verifyIncomingDebit() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.validateIncomingDebitsTab();	
	}
	
	/* Test Case: 81628
	 * Author:Deepa Ramu
	 *  Date created:05/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression(filter): Filtering by status
	 * Expected: 
	 *   1. Status filters set as expected
	 */
	
	@Test(priority = 10)
	public void test_case_81628_filterByStatus() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.assertselectStatusFieldsinDebitsTab();	
	}

	
	/* Test Case: 81638
	 * Author:Deepa Ramu
	 *  Date created:06/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression(filter): Filtering by Details
	 * Expected: 
	 *   1. Details filters set as expected
	 */
	
	@Test(priority = 11)
	public void test_case_81638_filterByDetails() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.assertDetailsFieldsinDebitsTab();	
	}
	
	
	/* Test Case: 81658
	 * Author:Deepa Ramu
	 *  Date created:07/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression(filter): Clear filters button
	 * Expected: 
	 *   1. Clear filters set as expected
	 */
	@Test(priority = 12)
	public void test_case_81658_clearFilters() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits();
		BankLandingPage.fillFieldsWithValueFromTable();
		BankLandingPage.assertClearFieldsinDebititemsTab();	
	}
	
	/*	Test Case: 81667
	 * Author:Deepa Ramu
	 *  Date created:07/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression(filter): Current Filter refresh and Clear Filter button
	 * Expected: 
	 *   1. Refresh and clear filters set as expected
	 */
	@Test(priority = 13)
	public void test_case_81667_refreshFilter() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits();
		String accNum = BankLandingPage.fillFieldsWithValueFromTable();
		BankLandingPage.fillNewAccountDetailsAndVerify(accNum);
		BankLandingPage.assertClearFieldsinDebititemsTab();
	}
	
	/* Test Case: 81687
	 * Author:Deepa Ramu
	 *  Date created:07/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Help Icon
	 * Expected: 
	 *   1. Help Icon should be clickable and pop up should open*/
	 
	
	@Test(priority = 14)
	public void test_case_81687_helpIcon() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.clickOnHelpIcon();
	}
	/*	Test Case: 81687
	 * Author:Deepa Ramu
	 *  Date created:08/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Pagination
	 * Expected: 
	 *   1. Pagination details displayed as expected*/
	 	
	@Test(priority = 15)
	public void test_case_81691_pagination() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.validatePagination();
	}	
			
	/*	Test Case: 82082
	 * Author:Deepa Ramu
	 *  Date created:08/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Multiselect download - select all
	 * Expected: 
	 *   1. Multiselect expander should appear*/
	
	@Test(priority = 16)
	public void test_case_82082_selectAll() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.checkSelectAllCheckbox()
		.assertMultiSelectTabs()
		.checkSelectAllCheckbox()
		.assertMultiSelectTabsNotPresent();		
	}
	
	/* Test Case: 82219
	 * Author:Deepa Ramu
	 *  Date created:09/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Multiselect download - pay multiple items
	 * Expected: 
	 *   1. Multiselect expander should appear
	 *   2.Should be able to select multiple items and click on pay */
	 	
	@Test(priority = 17)
	public void test_case_82219_payMultipleItems() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus()
		.filterwithAmountandPay("Pay");
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
	}
	
	/* Test Case: 82220
	 * Author:Deepa Ramu
	 *  Date created:09/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Multiselect download - No pay multiple items
	 * Expected: 
	 *   1. Multiselect expander should appear
	 *   2.Should be able to select multiple items and click on No pay */
	 	
	@Test(priority = 18)
	public void test_case_82220_noPayMultipleItems() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus()
		.filterwithAmountandPay("NoPay");
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));	
	}
	
	/*  Test Case: 82222
	 * Author:Deepa Ramu
	 *  Date created:12/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Sorting
	 * Expected: 
	 *   1. Sorting details displayed as expected*/
	 	
	@Test(priority = 19)
	public void test_case_82222_sorting() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));		
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.validateSorting();	
	}

	/*
	 * Test Case: 102689
	 * Author:Deepa Ramu
	 *  Date created:12/06/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Other information for Kappa and SDC
	 * Expected: 
	 *   1. Other information should be present for Incoming debits
	 */
	
	@Test(priority = 20)
	public void test_case_102689_otherInfo() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.validateOtherInformation();	
	}
}


