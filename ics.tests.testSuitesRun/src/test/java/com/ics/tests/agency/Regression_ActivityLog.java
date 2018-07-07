//<copyright  file="Regression_SearchArchive.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>
package com.ics.tests.agency;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.ics.agency.entities.OpenAgencyPageScenario;
import com.ics.agency.pages.ActivityLogPage;
import com.ics.agency.pages.AgencyIdentityProviderPage;
import com.ics.agency.pages.BankLandingPage;
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;
 
/******************MODULE HEADER*****************************************
 * Module Name -  Regression_ActivityLog.java
 ************************************************************************ 
 * Date -  26/09/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of Activity Log screen
 ***********************************************************************/
 
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_ActivityLog extends ICSAutomationCommonSetup{
		
	@BeforeMethod
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	@AfterMethod()
	public void quitDriver(){
		DRIVER.quit();
	}
	public static String server =getAgencyValueFromDataSheet("serverNameIA");
	public static String db = getAgencyValueFromDataSheet("imageArchiveDatabase");
		
	
	/* Test Case: 72345
	 * Author:Deepa Ramu
	 *  Date created:05/10/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Activity logging: Regression: user successfully login activity
	 * Expected: 
	 *   1. user login activity should be logged successfully in activity log screen
	 */
	@Test(priority = 1)
	public void test_case_72345_ativityLog_login() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnActivityLog();
		new ActivityLogPage(DRIVER)
		.assertLoginLogoutActivityLog("login");
	}

	/* Test Case: 140438
	 * Author:Deepa Ramu
	 *  Date created:06/10/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Activity logging: Regression: user successfully logout activity
	 * Expected: 
	 *   1. user logout activity should be logged successfully in activity log screen
	 */
	@Test(priority = 2)
	public void test_case_140438_activityLog_logout() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.verifyLogout();
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnActivityLog();
		new ActivityLogPage(DRIVER)
		.assertLoginLogoutActivityLog("logout");
	}
	
	/* Test Case: 72350 ,80821
	 * Author:Deepa Ramu
	 *  Date created:06/10/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Activity logging: Regression: item paid and approved action activity
	 * use agency which has approvals on (arbuthnot)
	 * Expected: 
	 *   1. item paid and approved activity should be logged successfully in activity log screen
     */
	@Test(priority = 3)
	public void test_case_72350_activityLog_ItemPaid_Approved() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		String serial = BankLandingPage.getFirstSerialFromTableBasedOnFilter();
		String sortCode = BankLandingPage.getFirstSortCodeFromTableBasedOnFilter();
		String amount = BankLandingPage.getFirstAmountFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno)
		.clickOnActivityLog();
		new ActivityLogPage(DRIVER)
		.assertItemActivityLog("itemPaid",accno,serial,sortCode,amount);
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillSuperUserApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.approveDecisionBasedOnAccountFilter(accno)
		.clickOnActivityLog();
		new ActivityLogPage(DRIVER)
		.assertItemActivityLog("itemDecisionApproved",accno,serial,sortCode,amount);
	}
	
	/* Test Case: 72350
	 * Author:Deepa Ramu
	 *  Date created:06/10/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Activity logging: Regression: item approval denied  action activity
	 * Expected: 
	 *   1. item approval denied activity should be logged successfully in activity log screen
     */
	@Test(priority = 4)
	public void test_case_72350_activityLog_ItemDenied() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		String serial = BankLandingPage.getFirstSerialFromTableBasedOnFilter();
		String sortCode = BankLandingPage.getFirstSortCodeFromTableBasedOnFilter();
		String amount = BankLandingPage.getFirstAmountFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.payDecisionBasedOnAccountFilter(accno)
		.clickOnActivityLog();
		new ActivityLogPage(DRIVER)
		.assertItemActivityLog("itemPaid",accno,serial,sortCode,amount);
		quitDriver();
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.fillSuperUserApproverNameID();
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.clickDenyByApprover(accno)
		.clickOnActivityLog();
		new ActivityLogPage(DRIVER)
		.assertItemActivityLog("itemDecisionDenied",accno,serial,sortCode,amount);
	}
	
	/* Test Case: 72355
	 * Author:Deepa Ramu
	 *  Date created:06/10/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Activity logging: Regression: item not paid action activity
	 * Expected: 
	 *   1. item not paid activity should be logged successfully in activity log screen
     */
	@Test(priority = 5)
	public void test_case_72355_activityLog_ItemNotPaid() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits();
		String accno = BankLandingPage.getFirstAccountNumberFromTableBasedOnFilter();
		String serial = BankLandingPage.getFirstSerialFromTableBasedOnFilter();
		String sortCode = BankLandingPage.getFirstSortCodeFromTableBasedOnFilter();
		String amount = BankLandingPage.getFirstAmountFromTableBasedOnFilter();
		new BankLandingPage(DRIVER)
		.approveNoPayDecisionBasedOnAccountFilter(accno ,getAgencyValueFromDataSheet("noPayReason"))
		.clickOnActivityLog();
		new ActivityLogPage(DRIVER)
		.assertItemActivityLog("itemNotPaid",accno,serial,sortCode,amount);
	}
	
	/*  Test Case: 88286
	 * Author:Deepa Ramu
	 *  Date created:11/10/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Activity logging: Regression: Sorting
	 * Expected: 
	 *   1. Sorting details displayed as expected*/
	 	
	@Test(priority = 19)
	public void test_case_88286_sorting() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));		
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnActivityLog();
		new ActivityLogPage(DRIVER)
		.validateSorting();	
	}
	
	
	
	

}