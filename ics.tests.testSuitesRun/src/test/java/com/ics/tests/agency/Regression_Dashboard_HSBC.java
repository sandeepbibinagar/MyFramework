//<copyright  file="Regression_Dashboard.java" company="iPSL">
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
import com.ics.agency.pages.DashboardPage;
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

/******************MODULE HEADER*****************************************
 * Module Name -  Regression_Dashboard.java
 ************************************************************************ 
 * Date -  26/07/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of Dashboard screen for HSBC
 ***********************************************************************/
 
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_Dashboard_HSBC extends ICSAutomationCommonSetup{

	@BeforeMethod
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	@AfterMethod()
	public void quitDriver(){
		DRIVER.quit();
	}
	
	public static String server = getAgencyValueFromDataSheet("serverNameIA");
	public static String db = getAgencyValueFromDataSheet("agencyDatabase");
	public static String IA_db = getAgencyValueFromDataSheet("imageArchiveDatabase");
	public static String serverAgency = getAgencyValueFromDataSheet("serverNameAgency");
	
	public static String path_HSBC_IncomingDebits_completed=getAgencyDataFromPath("dashboard")+getAgencyResourceFile().getString("debitsCompletedHsbc");
	public static String path_HSBC_IncomingDebits_kappadecision=getAgencyDataFromPath("dashboard")+getAgencyResourceFile().getString("debitsKappaHsbc");
	public static String path_HSBC_IncomingDebits_paymentdecision=getAgencyDataFromPath("dashboard")+getAgencyResourceFile().getString("debitsPayHsbc");
	
	public static String path_HSBC_IncomingCredits_CollectedElsewhere=getAgencyDataFromPath("dashboard")+getAgencyResourceFile().getString("creditsCollectedElsewhereHsbc");
	public static String path_HSBC_IncomingCredits_CollectedByMe=getAgencyDataFromPath("dashboard")+getAgencyResourceFile().getString("creditsCollectedByMeHsbc");

	public static String dashboard_06MA01=getAgencyValueFromDataSheet("dashboard06MA01");
	public static String dashboard_06MA02=getAgencyValueFromDataSheet("dashboard06MA02");
	public static String dashboard_06MA03=getAgencyValueFromDataSheet("dashboard06MA03");
	public static String dashboard_06MA04=getAgencyValueFromDataSheet("dashboard06MA04");
	public static String dashboard_05MA01=getAgencyValueFromDataSheet("dashboard05MA01");
	public static String dashboard_05MA03=getAgencyValueFromDataSheet("dashboard05MA03");
	public static String dashboard_13MA01=getAgencyValueFromDataSheet("dashboard13MA01");
	public static String dashboard_13MA04=getAgencyValueFromDataSheet("dashboard13MA04");

	
	/* Test Case: 105346, 105347
	 * Author:Deepa Ramu
	 * Description: Dashboard(Incoming Debits): Regression: Integration - Verify value of all debit completed items is displayed against complete 
	 * Expected: 
	 *   1. Field should be populated with value equal to sum of paid and not paid items 
	 */
	@Test(priority = 1)
	public void test_case_hsbc_incomingDebits_completedItems() throws Exception
	{
		new DashboardPage(DRIVER)
		.dataLoad_IA_deleteScript();
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_completed,dashboard_06MA01);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_completed,dashboard_06MA02);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_completed,dashboard_06MA03);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_completed,dashboard_06MA04);
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame();
		new DashboardPage(DRIVER)
		.verifyLandingPageDashboardCompleted();
	}	
	/* Test Case: 105201, 105332 
	 * Author:Deepa Ramu
	 * Description: Dashboard(Incoming Debits): Regression: Integration - Verify value of all debit pay decision items is displayed against payment decisions
	 * Expected: 
	 *   1. Field should be populated with value equal to sum of payment decision items
	 */
	@Test(priority = 2)
	public void test_case_hsbc_incomingDebits_payDecisionItems() throws Exception
	{
		new DashboardPage(DRIVER)
		.dataLoad_IA_deleteScript();
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_paymentdecision,dashboard_06MA01);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_paymentdecision,dashboard_06MA02);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_paymentdecision,dashboard_06MA03);
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame();
		new DashboardPage(DRIVER)
		.verifyLandingPageDashboardPayDecision();
	}
	
	/* Test Case: 105337, 105336
	 * Author:Deepa Ramu
	 * Description: Dashboard(Incoming Debits): Regression: Integration - Verify value of all debit pay decision items is displayed against payment decisions
	 * Expected: 
	 *   1. Field should be populated with value equal to sum of payment decision items
	 */
	@Test(priority = 3)
	public void test_case_hsbc_incomingDebits_kappaDecisionItems() throws Exception
	{
		new DashboardPage(DRIVER)
		.dataLoad_IA_deleteScript();
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_kappadecision,dashboard_06MA01);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_kappadecision,dashboard_06MA02);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_kappadecision,dashboard_06MA03);
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame();
		new DashboardPage(DRIVER)
		.verifyLandingPageDashboardKappaDecision();
	}
	
	/* Test Case: 104804, 104814
	 * Author:Deepa Ramu
	 * Description: Dashboard(Incoming Credits): Regression: Integration - Verify value of all credit items is displayed on dashboard(collecetdElsewhere)
	 * Expected: 
	 *   1. collected elsewhere items should be displayed
	 */
	@Test(priority = 5)
	public void test_case_hsbc_incomingCredits_collectedElsewhere() throws Exception
	{
		new DashboardPage(DRIVER)
		.dataLoad_IA_deleteScript();
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingCredits_CollectedElsewhere,dashboard_05MA01);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingCredits_CollectedElsewhere,dashboard_05MA03);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingCredits_CollectedElsewhere,dashboard_13MA01);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingCredits_CollectedElsewhere,dashboard_13MA04);
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame();
		new DashboardPage(DRIVER)
		.verifyDashboardCollectedElsewhere();
	}
		
	/* Test Case: 127809
	 * Author:Deepa Ramu
	 * Description: Dashboard(Incoming Credits): Regression:Verify dashboard credit information is updated based on configuration time
	 * Expected: 
	 *   1. data should be updated based on the config time when clicked on refresh
	 */
	@Test(priority = 8)
	public void test_case_hsbc_incomingCredits_refresh() throws Exception
	{
		String query = getAgencyValueFromDataSheet("configTimeInterval1");
		ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, query);
		new DashboardPage(DRIVER)
		.dataLoad_IA_deleteScript();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame();
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingCredits_CollectedElsewhere,dashboard_05MA01);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingCredits_CollectedElsewhere,dashboard_05MA03);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingCredits_CollectedElsewhere,dashboard_13MA01);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingCredits_CollectedElsewhere,dashboard_13MA04);
		new DashboardPage(DRIVER)
		.verifyIncomingCreditsConfigTime();
		query = getAgencyValueFromDataSheet("configTimeInterval2");
		ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, query);
	}
	
	/* Test Case: 130241
	 * Author:Deepa Ramu
	 * Description: Dashboard(Incoming Debits): Regression:Verify dashboard credit information is updated based on configuration time
	 * Expected: 
	 *   1. data should be updated based on the config time when clicked on refresh
	 */
	@Test(priority = 9)
	public void test_case_hsbc_incomingDebits_refresh() throws Exception
	{
		String query = getAgencyValueFromDataSheet("configTimeInterval1");
		ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, query);
		new DashboardPage(DRIVER)
		.dataLoad_IA_deleteScript();
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getInclearingDescisioner()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame();
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_paymentdecision,dashboard_06MA01);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_paymentdecision,dashboard_06MA02);
		runStoredProcedureCall(server,IA_db,path_HSBC_IncomingDebits_paymentdecision,dashboard_06MA03);
		new DashboardPage(DRIVER)
		.verifyIncomingDebitsConfigTime();
		query = getAgencyValueFromDataSheet("configTimeInterval2");
		ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, query);
	}
}



