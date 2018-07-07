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
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.agency.pages.SettlementLimits;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;
 
/******************MODULE HEADER*****************************************
 * Module Name -  Regression_SettlementLimits.java
 ************************************************************************ 
 * Date -  26/07/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of Settlement Limits screen
 ***********************************************************************/
 
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_SettlementLimits extends ICSAutomationCommonSetup{

	@BeforeMethod
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	@AfterMethod()
	public void quitDriver(){
		DRIVER.quit();
	}
		
	/* Test Case: 142327
	 * Author:Deepa Ramu
	 *  Date created:31/10/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Settlement cap limit: Regression: verify error message
	 * Expected: 
	 *   1. Verify error message is displayed as expected
	 */
	@Test(priority = 2)
	public void test_case_142327_verifyErrorMessage() throws Exception
	{
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSettlementCapManagement()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnSettlementLimits();
		new SettlementLimits(DRIVER)
		.verifyErrorMesssage();	
	}	
	
	/* Test Case: 140050 , 
	 * Author:Deepa Ramu
	 *  Date created:01/11/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Settlement cap limit: Regression: Agency names an limits should be taken from ref data after SOD is run
	 * Expected: 
	 *   1. data should be populated as expected
	 */
	@Test(priority = 2)
	public void test_case_142327_verifySettlementCap() throws Exception
	{		
		new SettlementLimits(DRIVER)
		.validateSettlementCapTables();	
	}	
	
	/* Test Case: 144177 , 144176 , 144173 , 144174
	 * Author:Deepa Ramu
	 *  Date created:02/11/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Settlement cap limit: Regression: Agency names an limits should be updated for the next working day based on the business date
	 * Expected: 
	 *   1. Next working date settlement limit should be updated
	 */
	@Test(priority = 3)
	public void test_case_142327_verifySettlementCapDate() throws Exception
	{		
		new SettlementLimits(DRIVER)
		.validateNextDateSettletmentCap();	
	}	
	
	/* Test Case: 142336
	 * Author:Deepa Ramu
	 *  Date created:03/11/2017
	 * Modified by and date:
	 * Reviewed by : venkatasainath devisetty
	 * Description: Settlement cap limit: Regression: Check the extended settlement cap limit  past the cut off  time cannot be updated
	 * Expected: 
	 *   1. error message should be displayed when the cut off time is missed
	 */
	@Test(priority = 3)
	public void test_case_142336_extendedSettltmentCapLimit_cutOffTime() throws Exception
	{		
		new AgencyIdentityProviderPage(DRIVER, getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSettlementCapManagement()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnSettlementLimits();
		new SettlementLimits(DRIVER)
		.validateCuttOffTimeError();	
		
	}
	
}


