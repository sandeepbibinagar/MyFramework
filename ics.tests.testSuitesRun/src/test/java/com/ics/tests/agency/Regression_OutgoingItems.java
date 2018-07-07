//<copyright  file="Regression_IncomingCredits.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

package com.ics.tests.agency;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.ics.agency.entities.OpenAgencyPageScenario;
import com.ics.agency.pages.AgencyIdentityProviderPage;
import com.ics.agency.pages.BankLandingPage;
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.agency.pages.OutgoingItemsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

/******************MODULE HEADER*****************************************
 * Module Name -  Regression_OutgoingItems.java
 ************************************************************************ 
 * Date -  26/07/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of Outgoing Items screen
 ***********************************************************************/
 
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_OutgoingItems extends ICSAutomationCommonSetup{
	
	public static String server =getAgencyValueFromDataSheet("serverNameIA");
	public static String db = getAgencyValueFromDataSheet("imageArchiveDatabase");
	public static String path_HSBC=getAgencyDataFromPath("incomingCredits")+"\\HSBC\\";
	public static String path_LBG=getAgencyDataFromPath("incomingCredits")+"\\LBG\\";
	public static String collectedByMeMA01_fileName=getAgencyValueFromDataSheet("collectedByMeMA01");
	
	public static String deleteIaTables=getAgencyValueFromDataSheet("deleteIATables");
	public static String path_delete_iadata=getAgencyDataFromPath("dashboard")+"\\";
	
	protected static WebDriver DRIVER;
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	public void quitDriver(){
		DRIVER.quit();
	}
	
	/* Test Case ID: 
	 * Author:Deepa Ramu
	 * Date created:15/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Load Data for HSBC
	 * Expected: 
	 *   1. Incoming Credits data should be loaded for HSBC*/

@Test(priority = 1)
	public void dataLoad_HSBC() throws Exception
	{
	runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
	runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
	runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
	runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
	runStoredProcedureCall(server,db,path_HSBC,collectedByMeMA01_fileName);
	}

	/* Test Case ID: 
	 * Author:Deepa Ramu
	 * Date created:15/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Load Data for LBG
	 * Expected: 
	 *   1. Incoming Credits data should be loaded for LBG*/
	
	@Test(priority = 1)
	public void dataLoad_LBG() throws Exception
	{
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_LBG,collectedByMeMA01_fileName);
	}
	
	/* Test Case ID: loginIncomingCreditsScreen
	 * Author:Deepa Ramu
	 * Date created:16/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Login to portal and navigate to incoming credits screen
	 * Expected: 
	 *   1. search screen should be displayed*/

	@Test(priority = 2)
	public void loginPortal() throws Exception
	{
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame();
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
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnOutgoingItems()
		.clickOnCompletedItems();
		new OutgoingItemsPage(DRIVER)
		.validatePaginationCompletedItems();
	}	

	/* Test Case ID: LogoutPortal
	 * Author:Deepa Ramu
	 * Date created:16/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Logout of the portal
	 * Expected: 
	 *   1. portal should be logged out successfully*/

	@Test(priority = 50)
	public void logoutPortal() throws Exception
	{
		new BankLandingPage(DRIVER)
		.verifyLogout();
		quitDriver();
	}
}



