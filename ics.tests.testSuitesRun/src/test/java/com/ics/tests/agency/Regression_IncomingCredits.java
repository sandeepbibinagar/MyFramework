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
import com.ics.agency.pages.IncomingCreditsPage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

/******************MODULE HEADER*****************************************
 * Module Name -  Regression_IncomingCredits.java
 ************************************************************************ 
 * Date -  26/07/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of Incoming Credits screen
 ***********************************************************************/
 
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_IncomingCredits extends ICSAutomationCommonSetup{
	
	public static String server =getAgencyValueFromDataSheet("serverNameIA");
	public static String db = getAgencyValueFromDataSheet("imageArchiveDatabase");
	public static String path_HSBC=getAgencyDataFromPath("incomingCredits")+"\\HSBC\\";
	public static String path_LBG=getAgencyDataFromPath("incomingCredits")+"\\LBG\\";
	public static String collectedByMeMA01_fileName=getAgencyValueFromDataSheet("collectedByMeMA01");
	public static String collectedByMeMA02_fileName=getAgencyValueFromDataSheet("collectedByMeMA02");
	public static String collectedByMeMA03_fileName=getAgencyValueFromDataSheet("collectedByMeMA03");
	public static String collectedByMe13MA01_fileName=getAgencyValueFromDataSheet("collectedByMe13MA01");
	public static String collectedByMe13MA04_fileName=getAgencyValueFromDataSheet("collectedByMe13MA04");
	public static String collectedElsewhereMA01_fileName=getAgencyValueFromDataSheet("collectedElsewhereMA01");
	public static String collectedElsewhereMA02_fileName=getAgencyValueFromDataSheet("collectedElsewhereMA02");
	public static String collectedElsewhereMA03_fileName=getAgencyValueFromDataSheet("collectedElsewhereMA03");
	public static String collectedElsewhere13MA01_fileName=getAgencyValueFromDataSheet("collectedElsewhere13MA01");
	public static String collectedElsewhere13MA04_fileName=getAgencyValueFromDataSheet("collectedElsewhere13MA04");
	
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
	runStoredProcedureCall(server,db,path_HSBC,collectedByMeMA02_fileName);
	runStoredProcedureCall(server,db,path_HSBC,collectedByMeMA03_fileName);
	runStoredProcedureCall(server,db,path_HSBC,collectedByMe13MA01_fileName);
	runStoredProcedureCall(server,db,path_HSBC,collectedByMe13MA04_fileName);
	runStoredProcedureCall(server,db,path_HSBC,collectedElsewhereMA01_fileName);
	runStoredProcedureCall(server,db,path_HSBC,collectedElsewhereMA02_fileName);
	runStoredProcedureCall(server,db,path_HSBC,collectedElsewhereMA03_fileName);
	runStoredProcedureCall(server,db,path_HSBC,collectedElsewhere13MA01_fileName);
	runStoredProcedureCall(server,db,path_HSBC,collectedElsewhere13MA04_fileName);
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
		runStoredProcedureCall(server,db,path_LBG,collectedByMeMA02_fileName);
		runStoredProcedureCall(server,db,path_LBG,collectedByMeMA03_fileName);
		runStoredProcedureCall(server,db,path_LBG,collectedByMe13MA01_fileName);
		runStoredProcedureCall(server,db,path_LBG,collectedByMe13MA04_fileName);
		runStoredProcedureCall(server,db,path_LBG,collectedElsewhereMA01_fileName);
		runStoredProcedureCall(server,db,path_LBG,collectedElsewhereMA02_fileName);
		runStoredProcedureCall(server,db,path_LBG,collectedElsewhereMA03_fileName);
		runStoredProcedureCall(server,db,path_LBG,collectedElsewhere13MA01_fileName);
		runStoredProcedureCall(server,db,path_LBG,collectedElsewhere13MA04_fileName);
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
	
	/*	
	 * Test Case ID: test_case_84328_allCollected_statusFilter
	 * Author:Deepa Ramu
	 * Date created:16/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(All Collected Items): Regression(filter): Filtering by status
	 * Expected: 
	 *   1. Status filters set as expected
	 */

	@Test(priority = 3)
	public void test_case_84328_allCollected_statusFilter() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedItemsTab();
		new IncomingCreditsPage(DRIVER)
		.assertselectStatusFieldsinCreditsTab(ICSPropertiesConfig.getCollectedItemsTab());
	}
	
	/*	
	 * Test Case ID: 84330
	 * Author:Deepa Ramu
	 * Date created:17/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(All Collected Items): Regression(filter): Clear Filter button
	 * Pre-requisite:Load 05MA01 and 05MA02 data
	 * Expected: 
	 *   1. All filters should be removed*/
	 

	@Test(priority = 4)
	public void test_case_84330_allCollected_clearFilter() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedItemsTab();
		new IncomingCreditsPage(DRIVER)
		.fillDetailsInCollectedItemsTab()
		.assertClearFieldsinAllCollItemsTab();
	}
	
		
	/* * Test Case ID: 84331
	 * Author:Deepa Ramu
	 * Date created:18/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(All Collected Items): Regression(filter): Current Filter refresh and clear
	 * Pre-requisite:Load 05MA01 and 05MA02 data
	 * Expected: 
	 *   1. current filter must be refreshed and then cleared*/
	 

	@Test(priority = 5)
	public void test_case_84331_allCollected_refresh() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedItemsTab();
		new IncomingCreditsPage(DRIVER)
		.fillDetailsInCollectedItemsTab()
		.refreshDetailsAndVerify(ICSPropertiesConfig.getCollectedItemsTab())
		.assertClearFieldsinAllCollItemsTab();
	}
	
		
	/* * Test Case ID: 84329
	 * Author:Deepa Ramu
	 * Date created:19/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(All Collected Items): Regression(filter): Filtering by details
	 * Pre-requisite:Load 05MA01 and 05MA02 data
	 * Expected: 
	 *   1. Details filters set as expected*/
	 

	@Test(priority = 6)
	public void test_case_84329_allCollected_detailsFilter() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedItemsTab();
		new IncomingCreditsPage(DRIVER)
		.assertDetailsFieldsinCollectedByMeTab();
	}
	
	/*	Test Case ID: 84354
	 * Author:Deepa Ramu
	 * Date created:22/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(All Collected Items): Regression: Pagination
	 * Expected: 
	 *   1. Pagination details displayed as expected*/
	 	
	@Test(priority = 7)
	public void test_case_81691_allCollected_pagination() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedItemsTab();
		new IncomingCreditsPage(DRIVER)
		.validatePagination(ICSPropertiesConfig.getCollectedItemsTab());
	}	
	
	
	/* * Test Case ID: 81562
	 * Author:Deepa Ramu
	 * Date created:23/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(All Collected Items): Regression: Verify T set Entry
	 * Expected: 
	 *   1. Incoming Credits Screen and T set should be available*/
	 
	@Test(priority = 8)
	public void test_case_81562_allCollected_tSet() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedItemsTab();
		new IncomingCreditsPage(DRIVER)
		.validateTSetEntryAllItems();	
	}
	
	
	/* * Test Case ID: 102718
	 * Author:Deepa Ramu
	 * Date created:25/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(All Collected Items): Regression: Other information for Collected by me items
	 * Expected: 
	 *   1. Other information should be present for the collected by me items*/
	 
	@Test(priority = 9)
	public void test_case_102718_allCollected_Other() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedItemsTab();
		new IncomingCreditsPage(DRIVER)
		.validateOtherInformation(ICSPropertiesConfig.getCollectedItemsTab());	
	}
	
	/*	Test Case ID: 117058
	 * Author:Deepa Ramu
	 * Date created:16/08/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow: Regression: Multiselect download - select all
	 * Expected: 
	 *   1. Multiselect expander should appear*/
	
	@Test(priority = 10)
	public void test_case_117058_allCollected_assertMultiselectExpander() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedItemsTab()
		.checkSelectAllCheckboxIncomingCredits();
		new IncomingCreditsPage(DRIVER)
		.assertMultiSelectTabs();		
	}
	
	 /*Test Case ID: 112346
	 * Author:Deepa Ramu
	 * Date created:16/08/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: IC collected By Me-Regression: clientAccessibility
	 * Expected: 
	 *   1.  Client accessibility text should be displayed when hovered over the required links */

	@Test(priority = 11)
	public void test_case_112346_collectedByMe_tooltip() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedItemsTab();
		new IncomingCreditsPage(DRIVER)
		.assertClientAccessibilityTextCollectedItems();
	}
	
	/*	
	 * Test Case ID: 84343
	 * Author:Deepa Ramu
	 * Date created:16/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Collected Elsewhere): Regression(filter): Filtering by status
	 * Expected: 
	 *   1. Status filters set as expected
	 */

	@Test(priority = 12)
	public void test_case_84343_collectedElsewhere_statusFilter() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedElseWhereTab();
		new IncomingCreditsPage(DRIVER)
		.assertselectStatusFieldsinCreditsTab(ICSPropertiesConfig.getCollectedElsewhereTab());
	}
	
	/*	
	 * Test Case ID: 84345
	 * Author:Deepa Ramu
	 * Date created:17/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Collected Elsewhere): Regression(filter): Clear Filter button
	 * Pre-requisite:Load 05MA01 and 05MA02 data
	 * Expected: 
	 *   1. All filters should be removed
	 */

	@Test(priority = 13)
	public void test_case_84345_collectedElsewhere_clearFilter() throws Exception
	{	
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedElseWhereTab();
		new IncomingCreditsPage(DRIVER)
		.fillDetailsInCollectedElsewhereTab()
		.assertClearFieldsinCollectedElseWhereTab();
	}
	
	/*	
	 * Test Case ID: 84346
	 * Author:Deepa Ramu
	 * Date created:18/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Collected Elsewhere): Regression(filter): Current Filter refresh and clear
	 * Pre-requisite:Load 05MA01 and 05MA02 data
	 * Expected: 
	 *   1. All filters should refreshed and then cleared
	 */

	@Test(priority = 14)
	public void test_case_84346_collectedElsewhere_refresh() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedElseWhereTab();
		new IncomingCreditsPage(DRIVER)
		.fillDetailsInCollectedElsewhereTab()
		.refreshDetailsAndVerify(ICSPropertiesConfig.getCollectedElsewhereTab())
		.assertClearFieldsinCollectedElseWhereTab();
	}
	
	/*	
	 * Test Case ID: 84344
	 * Author:Deepa Ramu
	 * Date created:19/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Collected Elsewhere Items): Regression(filter): Filtering by details
	 * Pre-requisite:Load 05MA01 and 05MA02 data
	 * Expected: 
	 *   1. Details filters set as expected
	 */

	@Test(priority = 15)
	public void test_case_84344_collectedElsewhere_detailsFilter() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedElseWhereTab();
		new IncomingCreditsPage(DRIVER)
		.assertDetailsFieldsinCollectedElsewhereTab();
	}
	
	/*	Test Case ID: 84355
	 * Author:Deepa Ramu
	 * Date created:22/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Collected Elsewhere): Regression: Pagination
	 * Expected: 
	 *   1. Pagination details displayed as expected*/
	 
	@Test(priority = 16)
	public void test_case_84355_collectedElsewhere_pagination() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedElseWhereTab();
		new IncomingCreditsPage(DRIVER)
		.validatePagination(ICSPropertiesConfig.getCollectedElsewhereTab());
	}	
	
	/*
	 * Test Case ID: 81562
	 * Author:Deepa Ramu
	 * Date created:24/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(All Collected Items): Regression: Verify T set Entry
	 * Expected: 
	 *   1. Incoming Credits Screen and T set should be available
	 */
	@Test(priority = 17)
	public void test_case_81615_collectedElsewhere_tSet() throws Exception
	{

		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedElseWhereTab();
		new IncomingCreditsPage(DRIVER)
		.validateTSetEntryCollectedElsewhere();	
	}
	
	/*
	 * Test Case ID: 102719
	 * Author:Deepa Ramu
	 * Date created:25/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Collected Elsewhere): Regression: Other information for Collected Elsewhere items
	 * Expected: 
	 *   1. Other information should be present for the Collected Elsewhere items
	 */
	@Test(priority = 18)
	public void test_case_102719_collectedElsewhere_other() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedElseWhereTab();
		new IncomingCreditsPage(DRIVER)
		.validateOtherInformation(ICSPropertiesConfig.getCollectedElsewhereTab());	
	}
	
	/*	Test Case ID: 115198
	 * Author:Deepa Ramu
	 * Date created:16/08/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow: Regression: Multiselect download - select all
	 * Expected: 
	 *   1. Multiselect expander should appear*/
	
	@Test(priority = 19)
	public void test_case_115198_collectedElsewhere_assertMultiselectExpander() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedElseWhereTab()
		.checkSelectAllCheckboxIncomingCredits();
		new IncomingCreditsPage(DRIVER)
		.assertMultiSelectTabs();		
	}
	
	/* Test Case ID: 112350
	 * Author:Deepa Ramu
	 * Date created:16/08/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: IC collectedElsewhere-Regression: clientAccessibility
	 * Expected: 
	 *   1.  Client accessibility text should be displayed when hovered over the required links */

	@Test(priority = 20)
	public void test_case_112350_collectedElsewhere_tooltip() throws Exception
	{	
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedElseWhereTab();
		new IncomingCreditsPage(DRIVER)
		.assertTooltipTextCollectedElsewhere();
	}
	
	/*	
	 * Test Case ID: 84349
	 * Author:Deepa Ramu
	 * Date created:17/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(exception): Regression(filter): Clear Filter button
	 * Pre-requisite:Load 05MA01 and 05MA02 data
	 * Expected: 
	 *   1. All filters should be removed
	 */

	@Test(priority = 21)
	public void test_case_84349_exception_clearFilter() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnExceptionsTab();
		new IncomingCreditsPage(DRIVER)
		.fillDetailsInExceptionTab()
		.assertClearFieldsinExceptionTab();
	}
	
	/*	
	 * Test Case ID: 84350
	 * Author:Deepa Ramu
	 * Date created:18/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Exceptions): Regression(filter): Current Filter refresh and clear
	 * Pre-requisite:Load 05MA01 and 05MA02 data
	 * Expected: 
	 *   1. All filters should refreshed and then cleared
	 */

	@Test(priority = 22)
	public void test_case_84350_exception_refresh() throws Exception
	{	
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnExceptionsTab();
		new IncomingCreditsPage(DRIVER)
		.fillDetailsInExceptionTab()
		.refreshDetailsAndVerify(ICSPropertiesConfig.getExceptionsTab())
		.assertClearFieldsinExceptionTab();
	}
	
	
	/*	
	 * Test Case ID: 84348
	 * Author:Deepa Ramu
	 * Date created:19/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Exceptions): Regression(filter): Filtering by details
	 * Pre-requisite:Load 05MA01 and 05MA02 data
	 * Expected: 
	 *   1. Details filters set as expected
	 */

	@Test(priority = 23)
	public void test_case_84348_exception_detailsFilter() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnExceptionsTab();
		new IncomingCreditsPage(DRIVER)
		.assertDetailsFieldsinExceptionTab();
	}
	
	/*	Test Case ID: 84356
	 * Author:Deepa Ramu
	 * Date created:23/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Exception Items): Regression: Pagination
	 * Expected: 
	 *   1. Pagination details displayed as expected*/
	 	
	/*@Test(priority = 24)
	public void test_case_84356_exception_pagination() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnExceptionsTab();
		new IncomingCreditsPage(DRIVER)
		.validatePagination(ICSPropertiesConfig.getExceptionsTab());
	}	*/
	
	/*
	 * Test Case ID: 81618
	 * Author:Deepa Ramu
	 * Date created:24/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Exception Items): Regression: Verify T set Entry
	 * Expected: 
	 *   1. Incoming Credits Screen and T set should be available
	 */
	@Test(priority = 25)
	public void test_case_81618_exception_tSet() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnExceptionsTab();
		new IncomingCreditsPage(DRIVER)
		.validateTSetEntryExceptions();	
	}
	
	/*
	 * Test Case ID: 102720
	 * Author:Deepa Ramu
	 * Date created:26/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Exception): Regression: Other information for Exception items
	 * Expected: 
	 *   1. Other information should be present for Exception items
	 */
	@Test(priority = 26)
	public void test_case_102720_exception_Other() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnExceptionsTab();
		new IncomingCreditsPage(DRIVER)
		.validateOtherInformation(ICSPropertiesConfig.getExceptionsTab());	
	}
	
	/* Test Case ID: 84337
	 * Author:Deepa Ramu
	 * Date created:26/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(All collected Items): Regression: Help Icon
	 * Expected: 
	 *   1. Help Icon should be clickable and pop up should open*/

	@Test(priority = 27)
	public void test_case_84337_allCollected_Help() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedItemsTab()
		.clickOnHelpIcon();
	}

	/* Test Case ID: 84347
	 * Author:Deepa Ramu
	 * Date created:26/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Collected Elsewhere): Regression: Help Icon
	 * Expected: 
	 *   1. Help Icon should be clickable and pop up should open*/

	@Test(priority = 28)
	public void test_case_84347_collectedElsewhere_help() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnCollectedElseWhereTab()
		.clickOnHelpIcon();
	}

	/* Test Case ID: 84351
	 * Author:Deepa Ramu
	 * Date created:26/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Incoming Credits Workflow(Exception Items): Regression: Help Icon
	 * Expected: 
	 *   1. Help Icon should be clickable and pop up should open*/

	@Test(priority = 29)
	public void test_case_84351_exception_help() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnIncomingCredits()
		.clickOnExceptionsTab()
		.clickOnHelpIcon();
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



