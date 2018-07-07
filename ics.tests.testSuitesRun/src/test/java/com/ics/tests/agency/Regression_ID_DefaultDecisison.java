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
 * Module Name -  Regression_ID_Defaultdecision.java
 ************************************************************************ 
 * Date -  11/09/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the default decisioning of items in Incoming Debits screen
 ***********************************************************************/
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_ID_DefaultDecisison extends ICSAutomationCommonSetup{

	@BeforeMethod
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	@AfterMethod()
	public void quitDriver(){
		DRIVER.quit();
	}
	
	public static String server_dew = getAgencyValueFromDataSheet("serverNameDEW");
	public static String dew_db = getAgencyValueFromDataSheet("dewDatabase");
	public static String path_HSBC_IncomingDebits=getAgencyDataFromPath("incomingDebits")+"\\HSBC\\";
	public static String path_LBG_IncomingDebits= getAgencyDataFromPath("incomingDebits")+"\\LBG\\";
	public static String incomingDebitMsg06_fileName=getAgencyValueFromDataSheet("incomingDebitMsg06");
	public static String incomingDebitMsg06_threshold_fileName=getAgencyValueFromDataSheet("incomingDebitMsg06Threshold");
	public static String delete_dewmsg06_fileName=getAgencyValueFromDataSheet("deleteDewTables");
	public static String eod_packageRun=getAgencyValueFromDataSheet("eod_packageRun");
	
	/* Test Case: 
	 * Author:Deepa Ramu
	 * Date created:24/04/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Load Data for HSBC
	 * Expected: 
	 *   1. Incoming Debits data should be loaded for HSBC*/

	@Test(priority = 1)
	public void dataLoad_HSBC() throws Exception
	{
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,delete_dewmsg06_fileName);	
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,delete_dewmsg06_fileName);
		runStoredProcedureCall(server_dew,dew_db,path_HSBC_IncomingDebits,incomingDebitMsg06_fileName);
	}
	
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
	}

	/* Test Case: default decision business rules_SDC and kappa Items
	 * Author:Deepa Ramu
	 *  Date created:11/09/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Debits Workflow: Regression: Pay Decision - business rules for all default decision scenarios_Approvals on
	 * Expected: 
	 *   1. Incoming Debits Screen should be available
	 *   2. Decision should change to 'completed' status
	 */
	@Test(priority = 2)
	public void test_case_SDC_KappaItems_defaultDecision() throws Exception
	{ 
		String decisioner = getAgencyValueFromDataSheet("userName");
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus();
		String accno1 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno1)
		.applyFiltersBasedOnAccountNumber(accno1);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);	
		validationStepInformation("SDC Item 1- Decisioner- Pay: "+accno1);
		
		new BankLandingPage(DRIVER)	
		.setPaymentDecisionStatusClearFields();
		String accno2 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno2 ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno2);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("SDC Item 2- Decisioner- No pay: "+accno2);
		
		new BankLandingPage(DRIVER)
		.setPaymentDecisionStatusClearFields();
		String accno3 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno3)
		.applyFiltersBasedOnAccountNumber(accno3);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("SDC Item 3- Decisioner- Pay: "+accno3);
		
		new BankLandingPage(DRIVER)
		.setPaymentDecisionStatusClearFields();
		String accno4 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno4 ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno4);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("SDC Item 4- Decisioner- No Pay: "+accno4);
		
		new BankLandingPage(DRIVER)
		.setPaymentDecisionStatusClearFields();
		String accno5 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno5)
		.applyFiltersBasedOnAccountNumber(accno5);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("SDC Item 5- Decisioner- Pay: "+accno5);
		
		new BankLandingPage(DRIVER)
		.setPaymentDecisionStatusClearFields();
		String accno6 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno6 ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno6);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("SDC Item 6- Decisioner- No Pay: "+accno6);
		
		new BankLandingPage(DRIVER)
		.setPaymentDecisionStatusClearFields();
		String accno7 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		String defaultDecisionPay =BankLandingPage.getDefaultdecisionValueFromTable();
		validationStepInformation("SDC Item 7- No action taken "+accno7);
		
		new BankLandingPage(DRIVER)
		.setKappaDecisionStatusClearFields();
		String accno8 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno8)
		.applyFiltersBasedOnAccountNumber(accno8);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("Kappa Item 1- Decisioner- Pay: "+accno8);
		
		new BankLandingPage(DRIVER)	
		.setKappaDecisionStatusClearFields();
		String accno9 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno9 ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno9);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("Kappa Item 2- Decisioner- No Pay: "+accno9);
		
		new BankLandingPage(DRIVER)
		.setKappaDecisionStatusClearFields();
		String accno10 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno10)
		.applyFiltersBasedOnAccountNumber(accno10);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("Kappa Item 3- Decisioner- Pay: "+accno10);
		
		new BankLandingPage(DRIVER)
		.setKappaDecisionStatusClearFields();
		String accno11 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno11 ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno11);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("Kappa Item 4- Decisioner- No Pay: "+accno11);
		
		new BankLandingPage(DRIVER)
		.setKappaDecisionStatusClearFields();
		String accno12 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno12)
		.applyFiltersBasedOnAccountNumber(accno12);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("Kappa Item 5- Decisioner- Pay: "+accno12);
		
		new BankLandingPage(DRIVER)
		.setKappaDecisionStatusClearFields();
		String accno13 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno13 ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno13);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("Kappa Item 6- Decisioner- No Pay: "+accno13);
		
		new BankLandingPage(DRIVER)
		.setKappaDecisionStatusClearFields();
		String accno14 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		String defaultDecisionKappa =BankLandingPage.getDefaultdecisionValueFromTable();
		validationStepInformation("Kappa Item 7- No action taken "+accno14);
				
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.approveDecisionBasedOnAccountFilter(accno1)
		.applyFiltersBasedOnAccountNumber(accno1);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","Pay");
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("SDC Item 1- Approver approves- "+accno1);
		
		new BankLandingPage(DRIVER)
		.approveDecisionBasedOnAccountFilter(accno2)
		.applyFiltersBasedOnAccountNumber(accno2);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));	
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("SDC Item 2- Approver approves- "+accno2);
		
		new BankLandingPage(DRIVER)		
		.clickDenyByApprover(accno3)
		.applyFiltersBasedOnAccountNumber(accno3);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getPayDecisionStatus());
		validationStepInformation("SDC Item 3- Approver Deny- "+accno3);
		
		new BankLandingPage(DRIVER)
		.clickDenyByApprover(accno4)
		.applyFiltersBasedOnAccountNumber(accno4);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getPayDecisionStatus());
		validationStepInformation("SDC Item 4- Approver Deny- "+accno4);
		
		new BankLandingPage(DRIVER)
		.approveDecisionBasedOnAccountFilter(accno8)
		.applyFiltersBasedOnAccountNumber(accno8);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","Pay");
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("Kappa Item 1- Approver approve- "+accno8);
		
		new BankLandingPage(DRIVER)
		.approveDecisionBasedOnAccountFilter(accno9)
		.applyFiltersBasedOnAccountNumber(accno9);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));	
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("Kappa Item 2- Approver approve- "+accno9);
		
		new BankLandingPage(DRIVER)		
		.clickDenyByApprover(accno10)
		.applyFiltersBasedOnAccountNumber(accno10);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getKappaDecisionStatus());
		validationStepInformation("Kappa Item 3- Approver deny- "+accno10);
		
		new BankLandingPage(DRIVER)
		.clickDenyByApprover(accno11)
		.applyFiltersBasedOnAccountNumber(accno11);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getKappaDecisionStatus());
		validationStepInformation("Kappa Item 4- Approver deny- "+accno11);
		
		sqlCommandExecution(eod_packageRun);
		validationStepInformation("*******Default Decision package run successfully*********");
		
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		
		.applyFiltersBasedOnAccountNumber(accno1);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
		BankLandingPage.validateColumnsInWebPortal("Decisioner",decisioner);
		validationStepInformation("SDC Item 1-"+accno1);
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno2);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));	
		BankLandingPage.validateColumnsInWebPortal("Decisioner",decisioner);
		validationStepInformation("SDC Item 2-"+accno2);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno3);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		validationStepInformation("SDC Item 3-"+accno3);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno4);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		validationStepInformation("SDC Item 4-"+accno4);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno5);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		validationStepInformation("SDC Item 5-"+accno5);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno6);		
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		validationStepInformation("SDC Item 6-"+accno6);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno7);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions",defaultDecisionPay);	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		validationStepInformation("SDC Item 7-(No action taken)-"+accno7 +defaultDecisionPay);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno8);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
		BankLandingPage.validateColumnsInWebPortal("Decisioner",decisioner);
		validationStepInformation("Kappa Item 1-"+accno8);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno9);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));	
		BankLandingPage.validateColumnsInWebPortal("Decisioner",decisioner);
		validationStepInformation("Kappa Item 2-"+accno9);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno10);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		validationStepInformation("Kappa Item 3-"+accno10);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno11);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		validationStepInformation("Kappa Item 4-"+accno11);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno12);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		validationStepInformation("Kappa Item 5-"+accno12);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno13);		
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions","Pay");	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		validationStepInformation("Kappa Item 6-"+accno13);
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno14);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions",defaultDecisionKappa);	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		validationStepInformation("Kappa Item 7 (No action taken)-"+accno14 +defaultDecisionKappa);
				
	}
	
	
	@Test(priority = 2)
	public void test_case_SDC_KappaItems_defaultDecision_NoApprovals() throws Exception
	{ 
		validationStepInformation("Agency - BosUnisys (Auto Approval)");
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus();
		String accno1 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno1);
		String defaultDecisionPay =BankLandingPage.getDefaultdecisionValueFromTable();
		validationStepInformation("SDC Item - Default value is: "+defaultDecisionPay+ "Account Number: "+accno1);
				
		new BankLandingPage(DRIVER)
		.setKappaDecisionStatusClearFields();
		String accno2 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno2);
		String defaultDecisionPay1 =BankLandingPage.getDefaultdecisionValueFromTable();
		validationStepInformation("Kappa Item - Default value is: "+defaultDecisionPay1+ "Account Number: "+accno2);
		
		sqlCommandExecution(eod_packageRun);
		validationStepInformation("*******Default Decision package run successfully*********");
		
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.applyFiltersBasedOnAccountNumber(accno1);
		validationStepInformation("SDC Item-(No action taken)-account number: "+accno2+" status: "+defaultDecisionPay);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions",defaultDecisionPay);	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
	
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno2);
		validationStepInformation("Kappa Item-(No action taken)-account number: "+accno2+" status: "+defaultDecisionPay1);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions",defaultDecisionPay);	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		
	
	}
	
	@Test(priority = 2)
	public void test_case_SDC_KappaItems_defaultDecision_approvalsOn() throws Exception
	{ 
		validationStepInformation("Agency - BosUnisys (Auto Approval)");
		String decisioner = getAgencyValueFromDataSheet("userName");
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus();
		String accno1 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno1)
		.applyFiltersBasedOnAccountNumber(accno1);
		String defaultDecisionPay =BankLandingPage.getDefaultdecisionValueFromTable();
		validationStepInformation("SDC Item 1 - Default value is: "+defaultDecisionPay+ "Account Number: "+accno1);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);	
		
		new BankLandingPage(DRIVER)	
		.setPaymentDecisionStatusClearFields();
		String accno2 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno2 ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno2);
		validationStepInformation("SDC Item 2 - Default value is: "+defaultDecisionPay+ "Account Number: "+accno1);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		
		new BankLandingPage(DRIVER)
		.setKappaDecisionStatusClearFields();
		String accno3 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno3)
		.applyFiltersBasedOnAccountNumber(accno3);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","Pay");			
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("Kappa Item 1 - Default value is: "+defaultDecisionPay+ "Account Number: "+accno3);
		
		new BankLandingPage(DRIVER)	
		.setKappaDecisionStatusClearFields();
		String accno4 = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno4 ,getAgencyValueFromDataSheet("noPayReason"))
		.applyFiltersBasedOnAccountNumber(accno4);
		BankLandingPage.validateColumnsInWebPortalDefault("Status",ICSPropertiesConfig.getAwaitingApprovalStatus());
		BankLandingPage.validateColumnsInWebPortalDefault("Decisions","No Pay - "+getAgencyValueFromDataSheet("noPayReason"));
		BankLandingPage.validateColumnsInWebPortalDefault("Decisioner",decisioner);
		validationStepInformation("Kappa Item 2 - Default value is: "+defaultDecisionPay+ "Account Number: "+accno4);
		
		sqlCommandExecution(eod_packageRun);
		validationStepInformation("*******Default Decision package run successfully*********");
		
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.applyFiltersBasedOnAccountNumber(accno1);
		validationStepInformation("SDC Item-(Pay Decision taken)-account number: "+accno1+" status: "+defaultDecisionPay);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions",defaultDecisionPay);	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
	
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno2);
		validationStepInformation("Kappa Item-(No Pay decision taken)-account number: "+accno2+" status: "+defaultDecisionPay);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions",defaultDecisionPay);	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno3);
		validationStepInformation("Kappa Item-(Pay Decision taken)-account number: "+accno3+" status: "+defaultDecisionPay);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions",defaultDecisionPay);	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		
		new BankLandingPage(DRIVER)
		.applyFiltersBasedOnAccountNumber(accno4);
		validationStepInformation("Kappa Item-(No Pay decision taken)-account number: "+accno4+" status: "+defaultDecisionPay);
		BankLandingPage.validateColumnsInWebPortal("Status",ICSPropertiesConfig.getCompletedStatus());
		BankLandingPage.validateColumnsInWebPortal("Decisions",defaultDecisionPay);	
		BankLandingPage.validateColumnsInWebPortal("Decisioner","AGY-DEFAULT");
		
	
	}
	
}



