//<copyright  file="Regression_SearchArchive.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>
package com.ics.tests.agency;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import com.ics.agency.entities.OpenAgencyPageScenario;
import com.ics.agency.pages.ActivityLogPage;
import com.ics.agency.pages.AgencyIdentityProviderPage;
import com.ics.agency.pages.BankLandingPage;
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.agency.pages.SearchArchivePage;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

/******************MODULE HEADER*****************************************
 * Module Name -  Regression_SearchArchive_RefinedUI.java
 ************************************************************************ 
 * Date -  26/07/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of Search screen
 ***********************************************************************/
 
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_SearchArchive_RefinedUI extends ICSAutomationCommonSetup{
		
	public static String server =getAgencyValueFromDataSheet("serverNameIA");
	public static String db = getAgencyValueFromDataSheet("imageArchiveDatabase");
	public static String path_HSBC_search=getAgencyDataFromPath("searchArchive")+"//HSBC//";
	public static String path_HSBC_IC=getAgencyDataFromPath("incomingCredits")+"//HSBC//";
	public static String path_LBG_search=getAgencyDataFromPath("searchArchive")+"//LBG//";
	public static String path_LBG_IC=getAgencyDataFromPath("incomingCredits")+"//LBG//";
	public static String path_delete_iadata=getAgencyDataFromPath("dashboard")+"\\";
	public static String deleteIaTables=getAgencyValueFromDataSheet("deleteIATables");
	
	public static String search06MA01_fileName=getAgencyValueFromDataSheet("search06MA01");
	public static String search06MA02_fileName=getAgencyValueFromDataSheet("search06MA02");
	public static String search06MA03_fileName=getAgencyValueFromDataSheet("search06MA03");
	public static String search06MA04_fileName=getAgencyValueFromDataSheet("search06MA04");
	public static String search07MA01_fileName=getAgencyValueFromDataSheet("search07MA01");

	public static String collectedElsewhereMA01_fileName=getAgencyValueFromDataSheet("collectedElsewhereMA01");
	public static String collectedElsewhereMA02_fileName=getAgencyValueFromDataSheet("collectedElsewhereMA02");
	public static String collectedElsewhereMA03_fileName=getAgencyValueFromDataSheet("collectedElsewhereMA03");
	public static String collectedElsewhere13MA01_fileName=getAgencyValueFromDataSheet("collectedElsewhere13MA01");
	public static String collectedElsewhere13MA04_fileName=getAgencyValueFromDataSheet("collectedElsewhere13MA04");
	
	protected static WebDriver DRIVER;
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	public void quitDriver(){
		DRIVER.quit();
	}
	
	/* Test Case: 84337
	 * Author:Deepa Ramu
	 * Description: Load Data for HSBC
	 * Expected: 
	 *   1. Should load data successfully*/

	@Test(priority = 1)
	public void dataLoad_HSBC() throws Exception
	{
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_HSBC_IC,collectedElsewhereMA01_fileName);
		runStoredProcedureCall(server,db,path_HSBC_IC,collectedElsewhereMA02_fileName);
		runStoredProcedureCall(server,db,path_HSBC_IC,collectedElsewhereMA03_fileName);
		runStoredProcedureCall(server,db,path_HSBC_IC,collectedElsewhere13MA01_fileName);
		runStoredProcedureCall(server,db,path_HSBC_IC,collectedElsewhere13MA04_fileName);
		runStoredProcedureCall(server,db,path_HSBC_search,search06MA01_fileName);
		runStoredProcedureCall(server,db,path_HSBC_search,search06MA02_fileName);
		runStoredProcedureCall(server,db,path_HSBC_search,search06MA03_fileName);
		runStoredProcedureCall(server,db,path_HSBC_search,search06MA04_fileName);
		runStoredProcedureCall(server,db,path_HSBC_search,search07MA01_fileName);
	}
	@Test(priority = 1)
	public void dataLoad_LBG() throws Exception
	{
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,db,path_LBG_IC,collectedElsewhereMA01_fileName);
		runStoredProcedureCall(server,db,path_LBG_IC,collectedElsewhereMA02_fileName);
    	runStoredProcedureCall(server,db,path_LBG_IC,collectedElsewhereMA03_fileName);
		runStoredProcedureCall(server,db,path_LBG_IC,collectedElsewhere13MA01_fileName);
		runStoredProcedureCall(server,db,path_LBG_IC,collectedElsewhere13MA04_fileName);
		runStoredProcedureCall(server,db,path_LBG_search,search06MA01_fileName);
		runStoredProcedureCall(server,db,path_LBG_search,search06MA02_fileName);
		runStoredProcedureCall(server,db,path_LBG_search,search06MA03_fileName);
		runStoredProcedureCall(server,db,path_LBG_search,search06MA04_fileName);
		runStoredProcedureCall(server,db,path_LBG_search,search07MA01_fileName);
	}
	
	/* Test Case: loginSearchScreen
	 * Author:Deepa Ramu
	 * Description: Login to portal and navigate to search screen
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

	/* Test Case: 127139, 127198
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify results are displayed on transactionIdentifier search and debit/credit section is disabled
	 * Expected: 
	 *   1. Search results displayed as expected*/

	@Test(priority = 3)
	public void test_case_127139_transactionIdentifier_current() throws Exception
	{
		validationStepInformation("Check transaction identifier Search filter for current date");
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)		
		.assertTsetFieldSearchFilter("Debit InClearing","Current")
		.assertTsetFieldSearchFilter("Credit InClearing","Current")
		.assertDebitCreditDisabled();
		new BankLandingPage(DRIVER)
		.clickOnActivityLog();
		new ActivityLogPage(DRIVER)
		.assertItemActivityLogSearch("searchCurrent","Credit InClearing");;
	}
	
	/* Test Case: 127348, 127352
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify results are displayed on transactionIdentifier search and debit/credit section is disabled
	 * Expected: 
	 *   1. Search results displayed as expected*/

	@Test(priority = 4)
	public void test_case_127348_transactionIdentifier_historic() throws Exception
	{	
		validationStepInformation("Check transaction identifier Search filter for historic date");
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.selectHistoricDate()
		.assertTsetFieldSearchFilter("Credit InClearing","Historic")
		.assertTsetFieldSearchFilter("Debit InClearing","Historic")
		.assertDebitCreditDisabled();
	}
	
	/* Test Case: 127142, 127197
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify results are displayed on transactionIdentifier search and debit/credit section is disabled
	 * Expected: 
	 *   1. Search results displayed as expected*/

	@Test(priority = 5)
	public void test_case_127142_itemIdentifier_current() throws Exception
	{	
		validationStepInformation("Check Item identifier Search filter for current date");
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.assertItemIdentifierFieldSearchFilter("Debit InClearing")
		.assertItemIdentifierFieldSearchFilter("Credit InClearing")
		.assertDebitCreditDisabled();
	}
	
	/* Test Case: 127360, 127361
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify results are displayed on itemIdentifier search for historic date
	 * Expected: 
	 *   1. Search results displayed as expected*/

	@Test(priority = 6)
	public void test_case_127360_itemIdentifier_historic() throws Exception
	{	validationStepInformation("Check Item identifier Search filter for historic date");
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.selectHistoricDate()
		.assertItemIdentifierFieldSearchFilter("Debit InClearing")
		.assertItemIdentifierFieldSearchFilter("Credit InClearing")
		.assertDebitCreditDisabled();
	}

	/* Test Case: 126809,126806,126818
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify submission channel is displayed only when outclearing is selected
	 * Expected: 
	 *   1. submission channel should be displayed only when outclearing is selected and All channel should be selected by default*/

	@Test(priority = 7)
	public void test_case_126809_WorkStream_SubmissionChannel() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.assertWorkflowStatusSubmissionChannel("Credit InClearing")
		.assertWorkflowStatusSubmissionChannel("Debit InClearing")
		.assertWorkflowStatusSubmissionChannel("OutClearing");
	}
	
	/* Test Case: 127097
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify results when historic and credit with two or more codeline values are given
	 * Expected: 
	 *   1. Search results displayed as expected*/

	@Test(priority = 8)
	public void test_case_127097_sortCodeAccount_current() throws Exception
	{	
		validationStepInformation("Check Sort code Search filter for current date");
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.assertSortCodeAccountFieldSearchFilter("Credit InClearing","current")
		.assertSortCodeAccountFieldSearchFilter("Debit InClearing","current");
	}
	
	/* Test Case: 127097
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify results when historic and credit with two or more codeline values are given
	 * Expected: 
	 *   1. Search results displayed as expected*/

	@Test(priority = 9)
	public void test_case_127097_sortCodeAccount_historic() throws Exception
	{	
		driverSetup();
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame();
		validationStepInformation("Check Sort code Search filter for historic date");
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.assertSortCodeAccountFieldSearchFilter("Credit InClearing","historic")
		.assertSortCodeAccountFieldSearchFilter("Debit InClearing","historic");
		new BankLandingPage(DRIVER)
		.clickOnActivityLog();
		new ActivityLogPage(DRIVER)
		.assertItemActivityLogSearch("searchHistoric","Debit InClearing");
	}
	
	/* Test Case: 127097
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify results when historic and credit with two or more codeline values are given
	 * Expected: 
	 *   1. Search results displayed as expected*/

	@Test(priority = 10)
	public void test_case_127097_serialAmount_current() throws Exception
	{	
		validationStepInformation("Check Sort code Search filter for current date");
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.assertSerialAmountFieldSearchFilter("Credit InClearing","current")
		.assertSerialAmountFieldSearchFilter("Debit InClearing","current");
	}
	
	/* Test Case: 127097
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify results when historic and credit with two or more codeline values are given
	 * Expected: 
	 *   1. Search results displayed as expected*/

	@Test(priority = 11)
	public void test_case_127097_serialAmount_historic() throws Exception
	{	
		validationStepInformation("Check Sort code Search filter for historic date");
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.assertSerialAmountFieldSearchFilter("Credit InClearing","historic")
		.assertSerialAmountFieldSearchFilter("Debit InClearing","historic");
	}
	
	/*Test Case: 98243
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify error is displayed when date range is greater than 365 days 
	 * Expected: 
	 *   1. error displayed as expected*/

	@Test(priority = 12)
	public void test_case_98243_dateRange_error() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.fillDateError();
	}
	

	/*Test Case: 127097
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify error is displayed when only one codeline value is displayed for historic date 
	 * Expected: 
	 *   1. error displayed as expected*/

	@Test(priority = 13)
	public void test_case_127097_codeLine_error() throws Exception
	{
		validationStepInformation("Check code line historic date");
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.codeLineFilterErrorHistoric("Credit InClearing")
		.codeLineFilterErrorHistoric("Debit InClearing");
	}
	
	
	
	/* Test Case: 102160
	 * Author:Deepa Ramu
	 * Description: Search-Regression: verify clear search functionality
	 * Expected: 
	 *   1. Columns values must be cleared*/

	@Test(priority = 14)
	public void test_case_102160_clearSearch() throws Exception
	{	
		validationStepInformation("Validate clear search functionality");
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.assertClearFieldsinSearchScreen();
	}
	
	/* Test Case: 102160
	 * Author:Deepa Ramu
	 * Description: Search-Regression: verify clear search functionality
	 * Expected: 
	 *   1. Columns values must be cleared*/

	@Test(priority = 15)
	public void test_case_102160_payDecisionStatus() throws Exception
	{	
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.assertpayDecisionStatusFieldSearchFilter();
	}
	
	/*Test Case: 104662
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: verify Pagination
	 * Expected: 
	 *   1. Pagination details displayed as expected*/
	 	
	@Test(priority = 16)
	public void test_case_104662_pagination() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.validatePaginationSearch();
	}	
	
	 /* Test Case: 104496
	 * Author:Deepa Ramu
	 * Description: Search-Regression: Sorting
	 * Expected: 
	 *   1. Sorting details displayed as expected
	 */
	
	@Test(priority = 17)
	public void test_case_104496_sorting() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame();
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.validateSorting();	
	}	
	
	/* Test Case: 108745
	 * Author:Deepa Ramu
	 * Description: Search-Regression: Help Icon
	 * Expected: 
	 *   1. Help Icon should be clickable and pop up should open*/

	@Test(priority = 20)
	public void test_case_108745_helpIcon() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive()
		.clickOnHelpIcon();
	}
	
	/* Test Case: 101648
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify Image , Kappa and Others icons are enabled
	 * Expected: 
	 *   1. Image , Kappa and Others icons are enabled*/

	@Test(priority = 18)
	public void test_case_101648_iconsEnabled() throws Exception
	{	
		driverSetup();
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.validateSearchResultsIcon("Enabled");
	}
	
	/* Test Case: 101651
	 * Author:Deepa Ramu
	 * Description: Search- Get service -Regression: Verify Image , Kappa and Others icons are disabled
	 * Expected: 
	 *   1. Image , Kappa and Others icons are disabled*/

//	@Test(priority = 19)
//	public void test_case_101651_iconsDisabled() throws Exception
//	{
//		new BankLandingPage(DRIVER)
//		.clickOnSearchArchive();
//		new SearchArchivePage(DRIVER)
//		.validateSearchResultsIcon("Disabled");
//	}
		
	/* Test Case: 112354
	 * Author:Deepa Ramu
	 * Description: Search-Regression: clientAccessibility
	 * Expected: 
	 *   1. Client accessibility text should be displayed when hovered over the required links */

	@Test(priority = 20)
	public void test_case_112354_clientAccessibility() throws Exception
	{
		new BankLandingPage(DRIVER)
		.clickOnSearchArchive();
		new SearchArchivePage(DRIVER)
		.assertClientAccessibilityText();
	}
	/* Test Case ID: LogoutPortal
	 * Author:Deepa Ramu
	 * Date created:16/05/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Logout of the portal
	 * Expected: 
	 *   1. portal should be logged out successfully*/

	@Test(priority = 25)
	public void logoutPortal() throws Exception
	{
		new BankLandingPage(DRIVER)
		.verifyLogout();
		quitDriver();
	}
}