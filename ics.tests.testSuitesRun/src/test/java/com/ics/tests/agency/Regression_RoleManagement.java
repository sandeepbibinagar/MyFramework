//<copyright  file="Regression_RoleManagement.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>
package com.ics.tests.agency;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ics.agency.entities.OpenAgencyPageScenario;
import com.ics.agency.pages.AgencyIdentityProviderPage;
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

/******************MODULE HEADER*****************************************
 * Module Name -  Regression_RoleManagement.java
 ************************************************************************ 
 * Date -  26/07/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of Role MAnagement
 ***********************************************************************/
 
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_RoleManagement extends ICSAutomationCommonSetup{

	protected static WebDriver DRIVER;
	@BeforeMethod
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	@AfterMethod()
	public void quitDriver(){
		DRIVER.quit();
	}
	
	/*
	 * Test Case: 84343
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Incoming Decisioner (IPD)
	 * Expected: 
	 *   1. Incoming Credits and Debits Screen should be available
	 *   2. All collected items, Collected Else where and Exceptions Tab should be displayed
	 */
	@Test(priority = 1 )
	public void test_case_84343_IPD() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Dashboard")
		.clickOnIncomingDebits()
		.setKappaDecisionStatus()
		.assertPayNoPayNotVisisble()
		.setPaymentDecisionStatus()
		.assertPayNoPayEnabled()
		.clickOnIncomingCredits()
		.assertRolesTabAvailable()
		.clickOnCollectedItemsTab()
		.clickOnExceptionsTab()
		.clickOnCollectedElseWhereTab()
		.clickOnSearchArchive()
		.assertActivityLogTabNotAvailable();
	}
	/*
	 * Test Case: 84370
	 * Author:Deepa Ramu
	 * Description: Role Management: Regression :Verify permissions for Incoming Approver (IPA)
	 * Expected: 
	 *   1. Incoming Credits and Debits Screen should be available
	 *   2. All collected items, Collected Else where and Exceptions Tab should be displayed
	 */
	@Test(priority = 2)
	public void test_case_84370_IPA() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingApprover()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Dashboard")
		.clickOnIncomingDebits()
		.setKappaDecisionStatus()
		.assertPayNoPayNotVisisble()
		.setPaymentDecisionStatus()
		.assertPayNoPayNotVisisble()	
		.clickOnIncomingCredits()
		.assertRolesTabAvailable()
		.clickOnCollectedItemsTab()
		.clickOnCollectedElseWhereTab()
		.clickOnExceptionsTab()
		.clickOnSearchArchive()
		.assertActivityLogTabNotAvailable();
	}
	
	/*
	 * Test Case: 84374
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Inclearing Fraud decisioner (IKD)
	 * Expected: 
	 *   1. Incoming Credits and Debits Screen should be available
	 *   2. All collected items, Collected Else where and Exceptions Tab should be displayed
	 */
	@Test(priority = 3)
	public void test_case_84374_IKD() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingFraudDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Dashboard")
		.clickOnIncomingDebits()
		.setKappaDecisionStatus()
		.assertPayNoPayEnabled()
		.setPaymentDecisionStatus()
		.assertPayNoPayNotVisisble()	
		.clickOnIncomingCredits()
		.assertRolesTabAvailable()
		.clickOnCollectedItemsTab()
		.clickOnCollectedElseWhereTab()
		.clickOnExceptionsTab()
		.clickOnSearchArchive()
		.assertActivityLogTabNotAvailable();
	}
	
	/*
	 * Test Case: 84378
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Outclearing Fraud decisioner (OKD)
	 * Expected: 
	 *   1. Incoming Credits and Debits Screen should be available
	 *   2. All collected items, Collected Else where and Exceptions Tab should be displayed
	 */
	@Test(priority = 4)
	public void test_case_84378_OKD() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getOutclearingFraudDecisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Dashboard")
		.clickOnIncomingDebits()
		.setKappaDecisionStatus()
		.assertPayNoPayNotVisisble()
		.setPaymentDecisionStatus()
		.assertPayNoPayNotVisisble()	
		.clickOnIncomingCredits()
		.assertRolesTabAvailable()
		.clickOnCollectedItemsTab()
		.clickOnCollectedElseWhereTab()
		.clickOnExceptionsTab()
		.clickOnSearchArchive()
		.assertActivityLogTabNotAvailable();
	}
	
	/*
	 * Test Case: 84384
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Outclearing Fraud Approver (OKA)
	 * Expected: 
	 *   1. Incoming Credits and Debits Screen should be available
	 *   2. All collected items, Collected Else where and Exceptions Tab should be displayed
	 */
	@Test(priority = 5)
	public void test_case_84384_OKA() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getOutclearingFraudApprover()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Dashboard")
		.clickOnIncomingDebits()
		.setPaymentDecisionStatus()
		.assertPayNoPayNotVisisble()
		.clickOnIncomingCredits()
		.assertRolesTabAvailable()
		.clickOnCollectedItemsTab()
		.clickOnCollectedElseWhereTab()
		.clickOnExceptionsTab()
		.clickOnSearchArchive()
		.assertActivityLogTabNotAvailable();
	}
	
	/*
	 * Test Case: 84388
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Super User ()
	 * Expected: 
	 *   1. Incoming Credits tab should not be available
	 *   2. Incoming debits screen should be available with only items in completed status
	 */
	@Test(priority = 6)
	public void test_case_84388_SAV() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSpecialistAuditViewer()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Search")
		.clickOnIncomingDebits()
		.verifyValueNotPresent("Awaiting Approval")
		.verifyValueNotPresent("Kappa Decision")
		.verifyValueNotPresent("Pay Decision")
		.assertOnlyCompleteStatusInDebitsTab()
		.assertIncomingCreditTabNotAvailable()
		.assertDashboardTabNotAvailable()
		.clickOnSearchArchive()
		.clickOnActivityLog();	
	}
	
	/*
	 * Test Case: 84388
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Enquire User (ENQ)
	 * Expected: 
	 *   1. Incoming Credits tab should not be available
	 *   2. Incoming debits screen should be available with only items in completed status
	 */
	@Test(priority = 7)
	public void test_case_84388_ENQ() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getEnquireOperator()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Search")
		.clickOnIncomingDebits()
		.assertOnlyCompleteStatusInDebitsTab()
		.assertDashboardTabNotAvailable()
		.verifyValueNotPresent("Awaiting Approval")
		.verifyValueNotPresent("Kappa Decision")
		.verifyValueNotPresent("Pay Decision")
		.assertIncomingCreditTabNotAvailable()
		.clickOnSearchArchive()
		.clickOnActivityLog();	
	}
	
	/*
	 * Test Case: 84408
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Systems/data operator (SYS)
	 * Expected: 
	 *   1. Incoming Credits tab should not be available
	 *   2. Incoming debits tab should not be available
	 */
	@Test(priority = 8)
	public void test_case_84408_SYS() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSystemDataOperator()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("UploadDownload")
		.assertIncomingCreditTabNotAvailable()
		.assertIncomingDebitTabNotAvailable()
		.assertSearchTabNotAvailable()
		.assertDashboardTabNotAvailable()
		.assertActivityLogTabNotAvailable();
	}
	
	/*
	 * Test Case: 84409
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Specialist Fraud decisioner (SFD)
	 * Expected: 
	 *   1. Incoming Credits tab should be available
	 *   2. Incoming debits tab should not be available , pay no pay icon enabled for kappa decision
	 */
	@Test(priority = 9)
	public void test_case_84409_SFD() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSpecialistFraudDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Dashboard")
		.clickOnIncomingDebits()
		.setKappaDecisionStatus()
		.assertPayNoPayEnabled()
		.setPaymentDecisionStatus()
		.assertPayNoPayNotVisisble()	
		.clickOnIncomingCredits()
		.assertRolesTabAvailable()
		.clickOnCollectedItemsTab()
		.clickOnCollectedElseWhereTab()
		.clickOnExceptionsTab()
		.clickOnSearchArchive()
		.assertActivityLogTabNotAvailable();
		
	}
	
	/*
	 * Test Case: 84411
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Combined Processing Role (COM)
	 * Expected: 
	 *   1. Incoming Credits tab should not be available
	 *   2. Incoming debits tab tab should not be available with pay , no pay icons
	 */
	@Test(priority = 10)
	public void test_case_84411_COM() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getCombinedProcessingRole()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Dashboard")
		.clickOnIncomingDebits()
		.setKappaDecisionStatus()
		.assertPayNoPayEnabled()
		.setPaymentDecisionStatus()
		.assertPayNoPayEnabled()	
		.clickOnIncomingCredits()
		.assertRolesTabAvailable()
		.clickOnCollectedItemsTab()
		.clickOnCollectedElseWhereTab()
		.clickOnExceptionsTab()
		.clickOnSearchArchive()
		.assertActivityLogTabNotAvailable();
	}
	
	/*
	 * Test Case: 84411
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Prepeare Role (PRP)
	 * Expected: 
	 *   1. Incoming Credits tab should be available
	 *   2. Incoming debits tab tab should not be available with pay , no pay icons
	 */
	@Test(priority = 11)
	public void test_case_84411_PRP() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getPrepareRole()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Dashboard")
		.clickOnIncomingDebits()
		.setKappaDecisionStatus()
		.assertPayNoPayEnabled()
		.setPaymentDecisionStatus()
		.assertPayNoPayEnabled()	
		.verifyValueNotPresent("Awaiting Approval")
		.clickOnIncomingCredits()
		.assertRolesTabAvailable()
		.clickOnCollectedItemsTab()
		.clickOnCollectedElseWhereTab()
		.clickOnExceptionsTab()
		.clickOnSearchArchive()
		.assertActivityLogTabNotAvailable();
	}
	
	/*
	 * Test Case: 84416
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Back Office Opereator (BOF)
	 * Expected: 
	 *  *   1. Incoming Credits tab should not be available
	 *   2. Incoming debits tab should not be available
	 */
	@Test(priority = 12)
	public void test_case_84416_BOF() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getBackOfficeOperator()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Search")
		.clickOnIncomingDebits()
		.assertOnlyCompleteStatusInDebitsTab()
		.verifyValueNotPresent("Awaiting Approval")
		.verifyValueNotPresent("Kappa Decision")
		.verifyValueNotPresent("Pay Decision")
		.assertIncomingCreditTabNotAvailable()
		.assertDashboardTabNotAvailable()
		.clickOnSearchArchive()
		.clickOnActivityLog();
		
	}
	
	/*
	 * Test Case: 84418
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Super User (SUP)
	 * Expected: 
	 *   1. Incoming Credits and Debits Screen should be available
	 *   2. All collected items, Collected Else where and Exceptions Tab should be displayed
	 */
	@Test(priority = 13)
	public void test_case_84418_SUP() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("Dashboard")
		.clickOnIncomingDebits()
		.setKappaDecisionStatus()
		.assertPayNoPayEnabled()
		.setPaymentDecisionStatus()
		.assertPayNoPayEnabled()
		.clickOnIncomingCredits()
		.assertRolesTabAvailable()
		.clickOnCollectedItemsTab()
		.clickOnCollectedElseWhereTab()
		.clickOnExceptionsTab()
		.clickOnSearchArchive()
		.clickOnActivityLog();
	}
	
	/*
	 * Test Case: 84408
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify permissions for Settlement Cap MAangement (SLS)
	 * Expected: 
	 *   1. Incoming Credits tab should not be available
	 *   2. Incoming debits tab should not be available
	 */
	@Test(priority = 14)
	public void test_case_84408_SLS() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSettlementCapManagement()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.assertLandingPage("ActivityLog")
		.assertIncomingCreditTabNotAvailable()
		.assertIncomingDebitTabNotAvailable()
		.assertSearchTabNotAvailable()
		.assertDashboardTabNotAvailable()
		.clickOnSettlementLimits()
		.clickOnAgencySummary();
	}
	
	/*
	 * Test Case: 113154
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify user is not able to access the screens with the url directly
	 * Expected: 
	 *   1. User should not be able to login with the url directly
	 */
	/*@Test(priority = 12)
	public void test_case_113154_urlCheck() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,getAgencyValueFromDataSheet("dashboardUrl"))
		.ClickOnsecuritylink();
		new BankLandingPage(DRIVER).urlValidation("Dashboard");
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER,getAgencyValueFromDataSheet("incomingDebitUrl"))
		.ClickOnsecuritylink();
		new BankLandingPage(DRIVER).urlValidation("Incoming Debits");
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER,getAgencyValueFromDataSheet("incomingCreditAllUrl"))
		.ClickOnsecuritylink();
		new BankLandingPage(DRIVER).urlValidation("Incoming Credits - All items");
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER,getAgencyValueFromDataSheet("incomingCreditElsewhereUrl"))
		.ClickOnsecuritylink();
		new BankLandingPage(DRIVER).urlValidation("Incoming Credits - collected elsewhere");
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER,getAgencyValueFromDataSheet("incomingCreditExceptionUrl"))
		.ClickOnsecuritylink();
		new BankLandingPage(DRIVER).urlValidation("Incoming Credits - Exception items");
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER,getAgencyValueFromDataSheet("searchArchiveUrl"))
		.ClickOnsecuritylink();
		new BankLandingPage(DRIVER).urlValidation("Search Archive");
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER,getAgencyValueFromDataSheet("activityLogUrl"))
		.ClickOnsecuritylink();
		new BankLandingPage(DRIVER).urlValidation("Activit Log");
		quitDriver();
		driverSetup();
	}
*/
	/*
	 * Test Case: 113162
	 * Author:DeepaRamu
	 * Description: Role Management: Regression :Verify user is not able to access the screens with the url directly after logging in
	 * Expected: 
	 *   1. User should not be able to login with the url directly after logging in
	 */
	/*	@Test(priority = 11)
	public void test_case_113162_urlCheckAfterLogin() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.urlValidationNewTab("Dashboard");
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.urlValidationNewTab("Incoming Debits");
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.urlValidationNewTab("Incoming Credits - All items");
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.urlValidationNewTab("Incoming Credits - collected elsewhere");
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.urlValidationNewTab("Incoming Credits - Exception items");
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.urlValidationNewTab("Search Archive");
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.urlValidationNewTab("Activity Log");
	}
		*/
}