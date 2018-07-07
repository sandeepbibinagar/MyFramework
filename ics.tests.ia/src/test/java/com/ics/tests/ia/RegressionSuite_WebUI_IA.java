/*  
<Copyright file="RegressionSuite_WebUI_IA.java" company="iPSL">
Copyright © iPSL 2017 All rights are reserved.
Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
is prohibited without the prior written permission of the copyright owner.
</copyright> 
*/
 
/*
/************************** Module Header ******************************
 * Module Name : WebUI Regression Suite 
 * Date : 19/06/2017
 * Created By : Sandeep Bibinagar
 * Description : This file contains all the test scripts/work flows w.r.t Web UI for Image Archive
 * 
****************** AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 30/07/2017		
*********************************************************************** 
Description : Updating file as more work flows are added to the regression suite
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 26/08/2017		
*********************************************************************** 
Description : Updating file as more work flows are added to the regression suite
***********************************************************************
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 30/08/2017		
**********************************************************************************************************
Description : Adding data_Load method to load data into the Database before executing the regression suite
***********************************************************************************************************
******************** AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
**********************************************************************************************************
Description : added functionality to fetch test data from excel sheet
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
 Description : Updating class as per java coding standards
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 15/09/2017		
 Description : Updating class as per new test cases around represent items 
***********************************************************************************************************/


package com.ics.tests.ia;

import java.awt.List;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;
import org.seleniumhq.jetty7.util.log.Log;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.ia.db.testScenarios.FetchXMLNodeValues;
import com.ics.ia.pages.ImageArchiveWorkflowPage;
import com.ics.ia.pages.RepresentQueryPage;
import com.ics.ia.pages.SearchItemPage;
import com.ics.ia.pages.SearchResultsPage;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;
import com.ics.testscenarios.ValidateLoginScenario;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/* *******************************************************************************************************************/
/* @author= Sandeep Bibinagar
2 * All pages related object repository/elements/functions are placed in Specific page related classes in the project																		
/********************************************************************************************************************/

public class RegressionSuite_WebUI_IA extends ICSAutomationCommonSetup{
	
	public static String server =getIAValueFromDataSheet("server");
	public static String db = getIAValueFromDataSheet("db_web");
	public static String templateFilePath = getIADataFromPathXml();
	
	@BeforeClass
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	@AfterClass()
	public void quitDriver(){
		DRIVER.quit();
	}
	
	/*
	 * Test Case: Loading Data
	 * Description: Loading data for the WebUI before executing the regression suite
	 * Expected: Data should be loaded
	 * Author: Sandeep Bibinagar
	 * Date: 28/08/2017
	 */
	/*@Test(priority=1)
	public void loadDataToArchive1() throws Exception
	{
		PostingExtractFile.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("webuiload_1sql"));
		PostingExtractFile.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("webuiload_2sql"));
		PostingExtractFile.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("webuiload_3sql"));
		PostingExtractFile.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("webuiload_4sql"));
		PostingExtractFile.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("webuiload_5sql"));
	}*/
	
	/*
	 * Test Case: 54981
	 * Description: Validating Login Scenario
	 * Expected: Login should be successful
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=2)
	public void testcasee54981()  throws Exception
	{
		new ImageArchiveWorkflowPage(DRIVER, getIAValueFromDataSheet("urlR1"))
		.run(new ValidateLoginScenario());
		new SearchItemPage(DRIVER)
		.validateLoginSuccessful();
		Log.info("testcasee54981");
	}
	
		
	/*
	 * Test Case: 60964
	 * Description: Validating presence of cursor in 'Processing Date' field
	 * Expected: Cursor should be placed on the 'Processing Date' field. 
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=3)
	public void testcasee60964() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateProcDateFocus();
		Log.info("testcase60964");
}
		
	
	/* Test Case: 53480
	 * Description: Validating input fields for Item query of 'Shared' section
	 * Expected: All input fields should be visible for 'Shared' section.
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=4)
	public void testcasee53480()  throws Exception{
		new SearchItemPage(DRIVER)
		.validateSharedInputsFields();
		Log.info("testcase53480");
	}
	
		 
	/* Test Case: 53482
	 * Description: Validating input fields for Item query of 'Image Clearing' section
	 * Expected: All input fields should be visible for 'Image Clearing' section. 
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=5)
	public void testcasee53482()  throws Exception
	{
		 new SearchItemPage(DRIVER)
		 .clickImageClearingOnly()
		 .validateImageClearingInputsFields();
		 Log.info("testcase53482");
	}
		
	 
	
	 /* Test Case: 53483
	  * Description: Validating input fields for Item query of 'Paper Clearing' section
	  * Expected: All input fields should be visible for 'Paper Clearing' section. 
	  * Author: Sandeep Bibinagar
	  * Date: 05/05/2017s
	  */	
	@Test(priority=6)
	public void testcasee53483()  throws Exception
	{
		  new SearchItemPage(DRIVER)
		 .clearProcDateField()
		 .clickPaperClearingOnly()
		 .validatePaperClearingInputsFields();
		  Log.info("testcase53483");
	}
	
		
	
	/* Test Case: 54539
	 * Description: Validating errors presented to users must have concise description
	 * Expected: All errors presented to users should display a concise description.
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */	
	@Test(priority=7)
	public void testcasee54539()  throws Exception
	{
		/*new ImageArchiveWorkflowPage(DRIVER, getIAValueFromDataSheet("urlR2"))
		.run(new ValidateLoginScenario());*/
		 new SearchItemPage(DRIVER)
		 .clickPaperClearingOnly()
		 .validateClearingDinErrorField()
		 .clickShared()
		 .validateProcDateErrorField()
		 .validateProcDateEndErrorField()
		 .validatePostingDateErrorField()
		 .validatePostingDateEndErrorField()
		 .validateSorCodeErrorField()
		 .validateAccountErrorField()
		 .validateTranCodeErrorField()
		 .validateAmountAndAmountEndErrorField()
		 .clickImageClearingOnly()
		 .validateUIIErrorField()
		 .validateUTSIErrorField();
		 Log.info("testcase54539");
	}
	
	
	
	/* Test Case: 54754
	 * Description: Validating invalid date format error messages
	 * Expected: Error messages should be returned if an invalid date format is entered
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */  
	@Test(priority=8)
	public void testcasee54754() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateDateFormatErrorMessage();	
		Log.info("testcase54754");
	}
		
	
	
	/* Test Case: 54755
	 * Description: Validating invalid date format error messages for future dates
	 * Expected: Error messages should be returned if a future date is entered
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */   
	@Test(priority=9)
	public void testcasee54755() throws Exception
	{
		new SearchItemPage(DRIVER)	
		.validateFutureDateFormatErrorMessage();
		Log.info("testcase54755");
	}
	
	
	
	
	/* Test Case: 59567
	 * Description: Validating invalid date format error messages for dates prior to 'Date Start Date'
	 * Expected: Error messages should be returned if a date prior to 'Date Start Date' date is entered
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */ 
	@Test(priority=10)
	public void testcasee59567() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateStartDateErrorMessage();  
		Log.info("testcase59567");
	}
	
	
	
	/* Test Case: 53498
	 * Description: Validating Search screen for minimum search criteria
	 * Expected: Minimum search criteria should be satisfied based on the inputs provided
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=11)
	public void testcasee53498() throws Exception
	{
		new SearchItemPage(DRIVER)
	//	.validateMinimumSearchCriteria()
		.clickShared()
		.validateMinimumSearchCriteriaForOnlyDate()
		.validateMinimumSearchCriteriaWithNoCriteria();	
		Log.info("testcase53498");
	}
	
	
	
	/* Test Case: 61028
	 * Description: Validating Error messages for 'From Value' data fields When only 'To' value is entered
	 * Expected: Should display appropriate error message to indicate 'From Value' field should be entered when 'To Value' is provided and searched 
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */    
	@Test(priority=12)
	public void testcasee61028() throws Exception
	{		
		new SearchItemPage(DRIVER)
		.validateToOrEndValueFields();
		Log.info("testcase61028");
	}
	
	
	
	/* Test Case: 77818
	 * Description: Validating Search with Wild Card Input parameters for DateRange & SorCode Combination
	 * Expected: Search should be successful based on the inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=13)
	public void testcasee77818() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateSearchSortCodeWildCardParameters();
		Log.info("testcase77818");
	}
	
	 
	/* Test Case: 77820
	 * Description: Validating Search with Wild Card Input parameters for DateRange & Account Number Combination
	 * Expected: Search should be successful based on the inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */    
	@Test(priority=14)
	public void testcasee77820() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateSearchAccountNumberWildCardParameters();
		Log.info("testcase77820");
	}

	
	/* Test Case: 77834
	 * Description: Validating Search with Wild Card Input parameters for DateRange & Serial Combination
	 * Expected: Search should be successful based on the inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=15)
	public void testcasee77834() throws Exception
	{		
		new SearchItemPage(DRIVER)
		.validateSearchSerialWildCardParameters();
		Log.info("testcase77834");
	}
	
	
	/* Test Case: 77836
	 * Description: Validating Search with Wild Card Input parameters for DateRange & Credit Reference Combination
	 * Expected: Search should be successful based on the inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=16)
	public void testcasee77836() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateSearchCreditReferenceWildCardParameters();
		Log.info("testcase77836");
	}
	
	
	
	/* Test Case: 52053
	 * Description: Validating Single page display in Results screen..
	 * Expected: Only one page must be displayed at a time in results screen.
	 * Author: Sandeep Bibinagar
	 * Date: 28/06/2017
	 */
	@Test(priority=17)
	public void testcasee52053() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateSinglePageDisplay();
		Log.info("testcase52053");
	}
	
	
	
	/* Test Case: 52054
	  * Description: Validating Next page button functionality
	  * Expected: User must be displayed next page with search results
	  * Author: Sandeep Bibinagar
	  * Date: 09/07/2017
	  */
	@Test(priority=18)
	public void testcasee52054() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateNextPageButton();
		Log.info("testcase52054");
	}
	
	
	
	/* Test Case: 52060
	 * Description: Validating Previous page button functionality
	 * Expected: User must be displayed previous page with search results
	 * Author: Sandeep Bibinagar
	 * Date: 11/07/2017
	 */
	@Test(priority=19)
	public void testcasee52060() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validatePreviousPageButton();
		Log.info("testcase52060");
	}

	
	
	/* Test Case: 52061
	 * Description: Validating Test Page number functionality
	 * Expected: Page number functionality should work
	 * Author: Sandeep Bibinagar
	 * Date: 10/07/2017
	 */
	@Test(priority=20)
	public void testcasee52061() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validatePageNavigation();
		Log.info("testcase52061");
	}
	
	
	/* Test Case: 134127
	 * Description: Validating Entity state column
	 * Expected: User should be able to see entity state column on search results page
	 * Author: Sandeep Bibinagar
	 * Date: 11/07/2017
	 */
	@Test(priority=21)
	public void testcasee134127() throws Exception
	{
		new SearchResultsPage(DRIVER)
		.validateEntityStateColumnOnSearchResultsPage();
		Log.info("testcase134127");
	}
	
	
	
	/* Test Case: 52068
	 * Description: Validating selection of Sub queries
	 * Expected: User should be able to select a sub query
	 * Author: Sandeep Bibinagar
	 * Date: 11/07/2017
	 */
	@Test(priority=22)
	public void testcasee52068() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateSelectRowAddToOutbox();
		Log.info("testcase52068");
	}
		
	
	
	/* Test Case: 52070
	 * Description: Validating sub queries
	 * Expected: Relevant sub queries should be displayed
	 * Author: Sandeep Bibinagar
	 * Date: 11/07/2017
	 */
	@Test(priority=23)
	public void testcasee52070() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateSubQueries();
		Log.info("testcase52070");
	}
		
	
	
	/* Test Case: 52088
	 * Description: Validating Horizontal scroll bar functionality
	 * Expected: User should be able to Horizontally scroll through results grid
	 * Author: Sandeep Bibinagar
	 * Date: 12/07/2017
	 */
	@Test(priority=24)
	public void testcasee52088() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateHorizontalScroll();
		Log.info("testcase52088");
		}
	
	
	
	/* Test Case: 52089
	 * Description: Validating Vertical scroll bar functionality
	 * Expected: User should be able to vertically scroll through results grid
	 * Author: Sandeep Bibinagar
	 * Date: 12/07/2017
	 */
	@Test(priority=25)
	public void testcasee52089() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateVerticalScroll();
		Log.info("testcase52089");
		}
	
	
	
	/* Test Case: 52094
	 * Description: Validating Single click functionality
	 * Expected: Particular record should be displayed.
	 * Author: Sandeep Bibinagar
	 * Date: 14/07/2017
	 */
	@Test(priority=26)
	public void testcasee52094() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateSingleClick();
		Log.info("testcase52094");
		}
	
	
	
	/* Test Case: 53660
	 * Description: Validating Tool tips for the columns in search results screen
	 * Expected: Columns tool tips should be displayed.
	 * Author: Sandeep Bibinagar
	 * Date: 15/07/2017
	 */
	@Test(priority=27)
	public void testcasee53660() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateToolTip();
		Log.info("testcase53660");
	}
	
	
	
	/* Test Case: 54353
	 * Description: Validating Add to Out box functionality
	 * Expected: 'Add to Out box' should be displayed.
	 * Author: Sandeep Bibinagar
	 * Date: 15/07/2017
	 */
	@Test(priority=28)
	public void testcasee54353() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateIsOutBoxPresent();
		Log.info("testcasee54353");
	}
	
	
	
	/* Test Case: 54350
	 * Description: Validating of results screen view
	 * Expected: Front and rear images should be displayed for selected item.
	 * Author: Sandeep Bibinagar
	 * Date: 16/07/2017
	 */
	@Test(priority=29)
	public void testcasee54350() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateFrontAndRearImage();
		Log.info("testcase54350");
	}
	
	
	
	/* Test Case: 54333
	 * Description: Validating Search screen functionality - No image
	 * Expected: 'No Image available' message should be displayed
	 * Author: Sandeep Bibinagar
	 * Date: 17/07/2017
	 */
	@Test(priority=30)
	public void testcasee54333() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateNoImage();
		Log.info("testcase54333");
	}
	
	
	
	/* Test Case: 54177
	 * Description: Validating of wild card character in Search screen
	 * Expected: wild card character validation should be successful
	 * Author: Sandeep Bibinagar
	 * Date: 18/07/2017
	 */
	@Test(priority=31)
	public void testcasee54177() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateSearchAccountNumberWildCardParameters()
		.validateSearchSortCodeWildCardParameters()
		.validateSearchSerialWildCardParameters()
		.validateSearchCreditReferenceWildCardParameters();
		Log.info("testcase54177");
	}
	
	
	
	/* Test Case: 54149
	 * Description: Validating Beneficiary PID field
	 * Expected: Beneficiary PID field level validation should be successful
	 * Author: Sandeep Bibinagar
	 * Date: 19/07/2017
	 */
	@Test(priority=32)
	public void testcasee54149() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateBenPID();
		Log.info("testcase54149");
	}
	
	
	
	/* Test Case: 54148
	 * Description: Validating Paying PID field
	 * Expected: Paying PID field level validation should be successful
	 * Author: Sandeep Bibinagar
	 * Date: 21/07/2017
	 */
	@Test(priority=33)
	public void testcasee54148() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validatePayPID();
		Log.info("testcase54148");
	}
	
	
	
	/* Test Case: 54145
	 * Description: Validating Collecting PID field
	 * Expected: Collecting PID field level validation should be successful
	 * Author: Sandeep Bibinagar
	 * Date: 26/07/2017
	 */
	@Test(priority=34)
	public void testcasee54145() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateColPID();
		Log.info("testcase54145");
	}
	
	
	
	/* Test Case: 54138
	 * Description: Validating UII field
	 * Expected: UII field level validation should be successful
	 * Author: Sandeep Bibinagar
	 * Date: 28/07/2017
	 */
	@Test(priority=35)
	public void testcasee54138() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateUII();
		Log.info("testcase54138");
	}
	
	
	
	/* Test Case: 54140
	 * Description: Validating RTP field
	 * Expected: RTP field level validation should be successful
	 * Author: Sandeep Bibinagar
	 * Date: 24/07/2017
	 */
	@Test(priority=36)
	public void testcasee54140() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateRTPPaidIndicator();
		Log.info("testcase54140");
	}
	
	
	
	
	/* Test Case: 54139
	 * Description: Validating UTSI field
	 * Expected: UTSI field level validation should be successful
	 * Author: Sandeep Bibinagar
	 * Date: 20/07/2017
	 */
	@Test(priority=37)
	public void testcasee54139() throws Exception
	{
		/*new ImageArchiveWorkflowPage(DRIVER, ICSPropertiesConfig.getIAEnvironmentURL())
		.run(new ValidateLoginScenario());*/
		new SearchItemPage(DRIVER)
		.validateUTSI();
		Log.info("testcase54139");
	}
	
	
	/* Test Case: 54141
	 * Description: Validating Search screen for Branch Identifier
	 * Expected: Branch Identifiers level validation should be successful
	 * Author: Sandeep Bibinagar
	 * Date: 27/10/2017
	 */
	@Test(priority=38)
	public void testcasee54141() throws Exception
	{
		/*new ImageArchiveWorkflowPage(DRIVER, ICSPropertiesConfig.getIAEnvironmentURL())
		.run(new ValidateLoginScenario());*/
		new SearchItemPage(DRIVER)
		.validateTextBoxBranchOrIBDEISN();
		Log.info("testcase54141");
	}
	
	
	/* Test Case: 54154
	 * Description: Validating Search screen for Branch/IBDE ISN
	 * Expected: Branch Identifiers level validation should be successful
	 * Author: Sandeep Bibinagar
	 * Date: 31/10/2017
	 */
	@Test(priority=39)
	public void testcasee54154() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateTextBoxIBDEISN();
		Log.info("testcase54154");
	}
	
	
	/* Test Case: 60120
	 * Description: Validating Wide Search Error handling
	 * Expected: Error should be displayed depending upon date range input paramaeters
	 * Author: Sandeep Bibinagar
	 * Date: 31/10/2017
	 */
	@Test(priority=40)
	public void testcasee60120() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateWideSearch();
		Log.info("testcase60120");
	}
	
	
	/* Test Case: 59566
	 * Description: Validating date input functionality
	 * Expected: User shoould be able to input date in date format
	 * Author: Sandeep Bibinagar
	 * Date: 31/10/2017
	 */
	@Test(priority=41)
	public void testcasee59566() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateDateInput();
		Log.info("testcase60120");
	}
	
	
	
	
	
	
	/* Test Case: 52078
	 * Description: Validating Logout Functionality
	 * Expected: User should be able to logout successfully.
	 * Author: Sandeep Bibinagar
	 * Date: 18/08/2017
	 */
	@Test(priority=42)
	public void testcasee52078() throws Exception
	{		
		new SearchItemPage(DRIVER)
		.validateLogout();
		Log.info("testcase52078");
	}
	
	
	/* Test Case: 127467
	 * Description: Validating presence of new Menu 'Previous Queries' which should be disabled
	 * Expected: User should be able to see 'Previous Queries' menu option disabled
	 * Author: Sandeep Bibinagar
	 * Date: 29/08/2017
	 */
	//@Test(priority=43)
	public void testcasee127467() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validatePreviousQueryMenuDisabled();
		Log.info("testcase127467");
	}
	
	
	/* Test Case: 54557
	 * Description: Validating whether Input fields are intact when Back button is clicked from search results page
	 * Author: Sandeep Bibinagar
	 * Date: 17/08/2017
	 */
//	@Test(priority=44)
	public void testcasee54557() throws Exception
	{		
		new SearchItemPage(DRIVER)
		.validateHomeBackNavigation();
		Log.info("testcase54557");
	}
	
	
	
	
	/* Test Case: 54559
	 * Description: Validating whether Input fields are empty when Home button is clicked from search results page
 	 * Expected: Input fields are empty.
 	 * Author: Sandeep Bibinagar
	 * Date: 16/08/2017
	 */
	//@Test(priority=45)
	public void testcasee54559() throws Exception
	{
		new SearchItemPage(DRIVER)
		.validateHomeNavigation();
		Log.info("testcase54559");
	}
	
	
	/* Test Case: 127466
	 * Description: Validating presence of new Menu 'Previous Queries'
	 * Expected: User should be able to see 'Previous Queries' menu option
	 * Author: Sandeep Bibinagar
	 * Date: 29/08/2017
	 */
	//	@Test(priority=46)
	public void testcasee127466() throws Exception
	{	
		/*new ImageArchiveWorkflowPage(DRIVER, ICSPropertiesConfig.getIAEnvironmentURL())
		.run(new ValidateLoginScenario());*/
		new SearchItemPage(DRIVER)
		.validatePreviousQueryMenu();
		Log.info("testcase127466");
	}
	
	
	/* Test Case: 127469
	 * Description: Validating 'Previous Queries' by performing search
	 * Expected: After valid search, 'previous queries' should be enabled
	 * Author: Sandeep Bibinagar
	 * Date: 29/08/2017
	 */
//	@Test(priority=47)
	public void testcasee127469() throws Exception
	{	
		/*new ImageArchiveWorkflowPage(DRIVER, ICSPropertiesConfig.getIAEnvironmentURL())
		.run(new ValidateLoginScenario());*/
		new SearchItemPage(DRIVER)
		.validateHomeBackNavigation()
		.clickPreviousQueryMenu()
		.validatePreviousQueryMenuEnabled();
		Log.info("testcase127469");
	}
	
	
	/* Test Case: 127470
	 * Description: Validating 'Previous Queries' by performing search using saved query
	 * Expected: User should be able to perform search with saved previous query
	 * Author: Sandeep Bibinagar
	 * Date: 29/08/2017
	 */
//	@Test(priority=48)
	public void testcasee127470() throws Exception
	{	
		/*new ImageArchiveWorkflowPage(DRIVER, ICSPropertiesConfig.getIAEnvironmentURL())
		.run(new ValidateLoginScenario());*/
		new SearchItemPage(DRIVER)
		/*.validateHomeBackNavigation()
		.clickPreviousQueryMenu()*/
		.clickPreviousQueryMenuFirstItem();
		Log.info("testcase127470");
	}
	
	/* Test Case: 120425
	 * Description: Validating 3 sections on the Item search page are visible
	 * Expected: User should be able to perform search with saved previous query
	 * Author: Sandeep Bibinagar
	 * Date: 31/08/2017
	 */
//	@Test(priority=49)
	public void testcasee120425() throws Exception
	{	
		/*new ImageArchiveWorkflowPage(DRIVER, ICSPropertiesConfig.getIAEnvironmentURL())
		.run(new ValidateLoginScenario());*/
		new SearchItemPage(DRIVER)
		.validateHomeScreenSections();
		Log.info("testcase120425");
		
	}
	
	/* Test Case: 120495
	 * Description: Validating 3 menus on the Item search page are displayed
	 * Expected: 3 menus should be displayed on the Main search page
	 * Author: Sandeep Bibinagar
	 * Date: 01/09/2017
	 */
//	@Test(priority=50)
	public void testcasee120495() throws Exception
	{	
		/*new ImageArchiveWorkflowPage(DRIVER, ICSPropertiesConfig.getIAEnvironmentURL())
		.run(new ValidateLoginScenario());*/
		new SearchItemPage(DRIVER)
		.validateHomeScreenMenus();
		Log.info("testcase120495");
	}
	
	/* Test Case: 131482
	 * Description: Validating Out box menu is displayed and disabled
	 * Expected: Out box menu should be displayed and disabled
	 * Author: Sandeep Bibinagar
	 * Date: 01/09/2017
	 */
//	@Test(priority=51)
	public void testcasee131482() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateOutboxMenu();
		Log.info("testcase131482");
	}
	
	/* Test Case: 85479
	 * Description: Validating Represent Query Fields
	 * Expected: User should be able to view mandatory fields on represent query page 
	 * Author: Sandeep Bibinagar
	 * Date: 18/09/2017
	 */
//	@Test(priority=52)
	public void testcasee85479() throws Exception
	{	
		
		new RepresentQueryPage(DRIVER)
		.validateRepresentQueryFields();
		Log.info("testcase85479");
	}
	
	/* Test Case: 85480
	 * Description: Validating Default value for Proc date on represent Query page
	 * Expected: User should be able to view default value in Proc date field
	 * Author: Sandeep Bibinagar
	 * Date: 18/09/2017
	 */
//	@Test(priority=53)
	public void testcase85480() throws Exception
	{	
		
		new RepresentQueryPage(DRIVER)
		.validateDefaultValueProcDate();
		Log.info("testcase85480");
	}
	
	
	/* Test Case: 85481
	 * Description: Validating Minimum search criteria for represent query
	 * Expected: User should provide valid minimum search criteria on represent query page.
	 * Author: Sandeep Bibinagar
	 * Date: 18/09/2017
	 */
//	@Test(priority=54)
	public void testcase85481() throws Exception
	{	
		new RepresentQueryPage(DRIVER)
		.validateMinimumSearchCriteriaRepresentQuery();
		Log.info("testcase85481");
	}
	
	/* Test Case: 85483
	 * Description: Validating search criteria for max days on represent query page
	 * Expected: User should not be able to use max date range on item represent page.
	 * Author: Sandeep Bibinagar
	 * Date: 18/09/2017
	 */
//	@Test(priority=55)
	public void testcase85483() throws Exception
	{	
		
		new RepresentQueryPage(DRIVER)
		.validateSearchCriteriaMaximumDaysRepresentQuery();
		Log.info("testcase85483");
	}
	
	/* Test Case: 85485
	 * Description: Validating wildcard functionality on item represent page
	 * Expected: User should not be able to use wild card on item represent page
	 * Author: Sandeep Bibinagar
	 * Date: 18/09/2017
	 */
//	@Test(priority=56)
	public void testcase85485() throws Exception
	{	
		new RepresentQueryPage(DRIVER)
		.validateWildCardOnRepresentQuery();
		Log.info("testcase85485");
	}
	
	
	/* Test Case: 130109
	 * Description: Validating Transaction content sub query on search results page
	 * Expected: User should be able to view Transaction content sub query 
	 * Author: Sandeep Bibinagar
	 * Date: 24/09/2017
	 */
//	@Test(priority=57)
	public void testcase130109() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateTransactionContentSubQuery();
		Log.info("testcase130109");
	}
	
	/* Test Case: 101141
	 * Description: Validating Fraud item details sub query on search results page
	 * Expected: User should be able to view Fraud item details sub query 
	 * Author: Sandeep Bibinagar
	 * Date: 24/09/2017
	 */
//	@Test(priority=58)
	public void testcase101141() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateDebitFraudItemDetailsSubQuery();
		Log.info("testcase101141");
	}
	
	
	/* Test Case: 87990
	 * Description: Validating Item State History sub query on search results page
	 * Expected: User should be able to view Transaction content sub query 
	 * Author: Sandeep Bibinagar
	 * Date: 26/09/2017
	 */
//	@Test(priority=59)
	public void testcase87990() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateItemStateHistorySubQuery();
		Log.info("testcase87990");
	}
	
	
	
	/* Test Case: 94040
	 * Description: Validating No output results for UII/UTSI which does not exist in DB
	 * Expected: User should be see empty results on search results page
	 * Author: Sandeep Bibinagar
	 * Date: 26/09/2017
	 */
//	@Test(priority=60)
	public void testcase94040() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateMainSearchOutputResults();
		Log.info("testcase94040");
	}
	
	
	/* Test Case: 101142
	 * Description: Validating No output results for UII & Business date which does not exist in DB
	 * Expected: User should be see empty results on search results page
	 * Author: Sandeep Bibinagar
	 * Date: 26/09/2017
	 */
//	@Test(priority=61)
	public void testcase101142() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateSearchOutputResultsForUII();
		Log.info("testcase101142");
	}
	
	
	/* Test Case: 110010
	 * Description: Validating No output results for UTSI & Business date which does not exist in DB
	 * Expected: User should be see empty results on search results page
	 * Author: Sandeep Bibinagar
	 * Date: 26/09/2017
	 */
//	@Test(priority=62)
	public void testcase110010() throws Exception
	{	
	
		new SearchItemPage(DRIVER)
		.validateSearchValidationForUTSI();
		Log.info("testcase110010");
	}
	
	
	/* Test Case: 131488
	 * Description: Validating Outbox menu displays number of items held in outbox on all pages
	 * Expected: Outbox should display number of items on all web pages
	 * Author: Sandeep Bibinagar
	 * Date: 26/09/2017
	 */
//	@Test(priority=63)
	public void testcase131488() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateOutboxMenuOnAllPages();
		Log.info("testcase131488");
	}
	
	
	/* Test Case: 131485
	 * Description: Validating if outbox items decrements when items are removed
	 * Expected: Outbox items should be decremented when items are removed
	 * Author: Sandeep Bibinagar
	 * Date: 26/09/2017
	 */
//	@Test(priority=64)
	public void testcase131485() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateOutboxMenuItemsDecrement();
		Log.info("testcase131485");
	}
	
	/* Test Case: 131427
	 * Description: Validating Entity State description on main search results page
	 * Expected: Entity state description should be from ReferenceData.RTPPaidEntityStates
	 * Author: Sandeep Bibinagar
	 * Date: 26/09/2017
	 */
//	@Test(priority=65)
	public void testcase131427() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateEntityStateDescription();
		Log.info("testcase131427");
	}
	
	
	/* Test Case: 111856
	 * Description: Validating Invalid Credit fraud item details
	 * Expected: It should return an empty result set
	 * Author: Sandeep Bibinagar
	 * Date: 2/09/2017
	 */
//	@Test(priority=66)
	public void testcase111856() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateInvalidCreditFraudItemDetails();
		Log.info("testcase111856");
	}
	
	
	/* Test Case: 111857
	 * Description: Validating credit fraud item details sub query 
	 * Expected: Credit fraud item details should be displayed in the credit fraud item detais results page
	 * Author: Sandeep Bibinagar
	 * Date: 27/09/2017
	 */
//	@Test(priority=67)
	public void testcase111857() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateCreditFraudItemDetailsSubQuery();
		Log.info("testcase111857");
	}
	
	
	/* Test Case: 140650
	 * Description: Validating item capture history sub query 
	 * Expected: Item capture history should be displayed in the sub query
	 * Author: Sandeep Bibinagar
	 * Date: 22/10/2017
	 */
//	@Test(priority=68)
	public void testcase140650() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateItemCaptureHistorySubQuery();
		Log.info("testcase111857");
	}
	
	

	/* Test Case: 87989
	 * Description: Validating Item State History sub query on search results page
	 * Expected: User should be able to view Item State History sub query 
	 * Author: Sandeep Bibinagar
	 * Date: 06/11/2017
	 */
//	@Test(priority=69)
	public void testcase87989() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateUII()
		.validateItemStateHistorySubQuery();
		Log.info("testcase87989");
	}
	
	
	/* Test Case: 101136
	 * Description: Validating Debit fraud item details sub query on search results page
	 * Expected: User should be able to view tem State History sub query 
	 * Author: Sandeep Bibinagar
	 * Date: 06/11/2017
	 */
//	@Test(priority=70)
	public void testcase101136() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateDebitFraudItemDetailsSubQuery();
		Log.info("testcase101136");
	}
	

	/* Test Case: 101148
	 * Description: Validating No OutputUII
	 * Expected: the search should return an empty result set
	 * Author: Sandeep Bibinagar
	 * Date: 06/11/2017
	 */
//	@Test(priority=71)
	public void testcase101148() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateNoOutputUII();
		Log.info("testcase101148");
	}
	
		
	/* Test Case: 101147
	 * Description: Validating Debit fraud item details sub query on search results page
	 * Expected: User should be able to view tem State History sub query 
	 * Author: Sandeep Bibinagar
	 * Date: 18/11/2017
	 */
//	@Test(priority=72)
	public void testcase101147() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateDebitFraudItemDetailsSubQuery();
		Log.info("testcase101147");
	}
	
	
	/* Test Case: 104831
	 * Description: Validating Debit fraud item details sub query on search results page
	 * Expected: User should be able to view tem State History sub query 
	 * Author: Sandeep Bibinagar
	 * Date: 10/11/2017
	 */
//	@Test(priority=73)
	public void testcase104831() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateCreditFraudItemDetailsSubQuery();
		Log.info("testcase104831");
	}
	
	
	/* Test Case: 104844
	 * Description: Validating Debit fraud item details sub query on search results page
	 * Expected: User should be able to view tem State History sub query 
	 * Author: Sandeep Bibinagar
	 * Date: 18/11/2017
	 */
//	@Test(priority=74)
	public void testcase104844() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateTransactionContentSubQuery();
		Log.info("testcase101147");
	}
	
	/* Test Case: 111855
	 * Description: Validating Debit fraud item details sub query on search results page
	 * Expected: User should be able to view tem State History sub query 
	 * Author: Sandeep Bibinagar
	 * Date: 11/11/2017
	 */
	//@Test(priority=75)
	public void testcase111855() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateCreditFraudItemDetailsSubQuery();
		Log.info("testcase111855");
	}
	
	
	
	/* Test Case: 111859
	 * Description: Validating Credit fraud item details sub query on search results page
	 * Expected: User should be able to view credit fraud item details sub query 
	 * Author: Sandeep Bibinagar
	 * Date: 13/11/2017
	 */
	//@Test(priority=76)
	public void testcase111859() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateCreditFraudItemDetailsSubQuery();
		Log.info("testcase111859");
	}
	
	
	/* Test Case: 120427
	 * Description: Validating web ui tab name on all pages
	 * Expected: User should be able to view bank name on every tab on all pages 
	 * Author: Sandeep Bibinagar
	 * Date: 18/11/2017
	 */
	//@Test(priority=77)
	public void testcase120427() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateWebUiTab();
		Log.info("testcase120427");
	}
	
	
	/* Test Case: 132011
	 * Description: Validating search results for UTSI
	 * Expected: User should be able to view results for UTSI
	 * Author: Sandeep Bibinagar
	 * Date: 18/11/2017
	 */
	//@Test(priority=80)
	public void testcase132011() throws Exception
	{	
		new SearchItemPage(DRIVER)
		.validateUTSI()
		.validateSearchValidationForUTSI();
		Log.info("testcase132011");
	}
	
	/* Test Case: 138801
	 * Description: Validating search results for UII and posting date
	 * Expected: User should be able to view results for UII and posting date
	 * Author: Sandeep Bibinagar
	 * Date: 18/11/2017
	 */
	/*@Test(priority=81)
	public void testcase138801() throws Exception
	{	
		new SearchItemPage(DRIVER)
		
		Log.info("testcase138801");
	}*/
	
	
	/* Test Case: Search Performance Test - WEB UI - NFR 
	 * Description: Validating Search with Wild Card Input parameters for DateRange & Credit Reference Combination
	 * Expected: Search should be successful based on the inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 15/07/2017
	 */
	/*@Test(priority=82)
	public void testcasee_SearchPerformanceWebUI()  throws Exception
	{
		new ImageArchiveWorkflowPage(DRIVER, ICSPropertiesConfig.getIAEnvironmentURL())
		.run(new ValidateLoginScenario());
		new SearchItemPage(DRIVER)
		.validateLoginSuccessful()
		.validateCSS();		
	}*/
}