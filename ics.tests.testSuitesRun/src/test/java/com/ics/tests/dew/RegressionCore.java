package com.ics.tests.dew;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ics.dew.pages.DecisionEngineWorkflowPage;
import com.ics.dew.pages.DewPendingPaymentsPage;
import com.ics.dew.pages.testScenarios.FinalPaymentDecisionNotPaidBySystemScenario;
import com.ics.dew.pages.testScenarios.FinalPaymentDecisionPaidByPostingFileScenario;
import com.ics.dew.pages.testScenarios.ItemDecisionScreen_ButtonActivation;
import com.ics.dew.pages.testScenarios.ItemDecisionScreen_InfoSection;
import com.ics.dew.pages.testScenarios.ReleaseOwnershipFunctionalityWithDecisionClickNext;
import com.ics.dew.pages.testScenarios.ReleaseOwnershipFunctionalityWithPreliminaryDecision;
import com.ics.dew.pages.testScenarios.ReleaseOwnershipFunctionalityWithoutTakingAnyDecision;
import com.ics.dew.pages.testScenarios.SDCandKappaCrossException;
import com.ics.dew.pages.testScenarios.SearchItemScreenItemSearchScenario1;
import com.ics.dew.pages.testScenarios.SearchItemScreenItemSearchScenario2;
import com.ics.dew.pages.testScenarios.SearchItemScreenItemSearchScenario3;
import com.ics.dew.pages.testScenarios.SearchResultColumnValidations;
import com.ics.dew.pages.testScenarios.SearchResultsViewButton;
import com.ics.dew.pages.testScenarios.TakeOwnershipFunctionalityScenario;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

/********************************************************************************************************************/
/* @author= Prateek.Arora@ipsl.co.uk  
/* 			keerthiga.Pandian@ipsl.co.uk   																		*/
/********************************************************************************************************************/

public class RegressionCore extends ICSAutomationCommonSetup{
	
	@BeforeClass(groups={"sanity"})
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	@AfterClass()
	public void quitDriver(){
		DRIVER.quit();
	}
	
	/*
	 * Test Case: 54623
	 * Description: Validating item Information at Item Decision Screen
	 * Expected: All details coming in information section for an Item at Item decision screen should have correct values 
	 *   
	*/
	@Test//(priority=1)
	public void testcase54623()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new ItemDecisionScreen_InfoSection());
		
	}

	/*
	 * Test Case: 54664
	 * Description: Validating item Information at Item Decision Screen
	 * Expected: All details coming in information section for an Item at Item decision screen should have correct values 
	 *   
	*/
	@Test(priority=1)
	public void testcase54664()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new SearchResultColumnValidations());
		
	}

	
	
	/*
	 * Test Case: 54980
	 * Description: Validating item Information at Item Decision Screen
	 * Expected: All details coming in information section for an Item at Item decision screen should have correct values 
	 *   
	*/
	@Test(priority=1)
	public void testcase54980()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new ReleaseOwnershipFunctionalityWithoutTakingAnyDecision());
		
	}
	
	/*
	 * Test Case: 54981
	 * Description: Validating item Information at Item Decision Screen
	 * Expected: All details coming in information section for an Item at Item decision screen should have correct values 
	 *   
	*/
	@Test(priority=1)
	public void testcase54981()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new ReleaseOwnershipFunctionalityWithPreliminaryDecision());
		
	}

	
	/*
	 * Test Case: 54981
	 * Description: Validating item Information at Item Decision Screen
	 * Expected: All details coming in information section for an Item at Item decision screen should have correct values 
	 *   
	*/
	@Test(priority=1)
	public void testcase54983()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new ReleaseOwnershipFunctionalityWithDecisionClickNext());
		
	}

	
	
	
	
	/*
	 * Test Case: 54946
	 * Description: Validating item Information at Item Decision Screen
	 * Expected: All details coming in information section for an Item at Item decision screen should have correct values 
	 *   
	*/
	@Test(priority=1)
	public void testcase54946()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new TakeOwnershipFunctionalityScenario());
		
	}
	
	/*
	 * Test Case: 54622
	 * Description: Validating item Information at Item Decision Screen
	 * Expected: All details coming in information section for an Item at Item decision screen should have correct values 
	 *   
	*/
	@Test(priority=1)
	public void testcase54622()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new SearchResultsViewButton());
		
	}

	
	
	
	
	
/*	 * Test Case: 54594_1
	 * Description: 
	 * Expected: 
	 *   
*/	
	
	@Test(priority=2)
	public void testcase54594Scenario1()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new SearchItemScreenItemSearchScenario1());
		
	}

	/*
	 * Test Case: 54594_2
	 * Description: 
	 * Expected: 
	 *   
	*/

	@Test(priority=2)
	public void testcase54594Scenario2()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new SearchItemScreenItemSearchScenario2());
	}

	/*
	 * Test Case: 54594_3
	 * Description: 
	 * Expected: 
	 *   
	*/
	
	@Test(priority=3)
	public void testcase54594Scenario3()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new SearchItemScreenItemSearchScenario3());
	}

	
	
	/*
	 * Test Case: 55157
	 * Description: 
	 * Expected: 
	 *   
	*/

	@Test(priority=4)
	public void testcase55157() throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new FinalPaymentDecisionPaidByPostingFileScenario());
	}	
	
	/*
	 * Test Case: 62019
	 * Description: 
	 * Expected: 
	 *  
	 */
	
	@Test(priority=5)
		public void testcase62019() throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new FinalPaymentDecisionPaidByPostingFileScenario());
	}
	
	
	
	/*
	 * Test Case: 54621
	 * Description: Validating item Information at Item Decision Screen
	 * Expected: All details coming in information section for an Item at Item decision screen should have correct values 
	 *   
	*/
	@Test(priority=1)
	public void testcase54621()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new ItemDecisionScreen_ButtonActivation());
		
	}


	/*
	 * Test Case: 84460
	 * Description: Validating item Information at Item Decision Screen
	 * Expected: All details coming in information section for an Item at Item decision screen should have correct values 
	 *   
	*/
	@Test(priority=1)
	public void testcase84460()  throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER, ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new SDCandKappaCrossException());
		
	}
	
	/*
	
	 * Test Case: 55191
	 * Description: 
	 * Expected: 
	 *
	 */
	
	@Test(priority=6)
	public void testcase55191() throws Exception
	{
		new DecisionEngineWorkflowPage(DRIVER,ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new FinalPaymentDecisionNotPaidBySystemScenario());
	}
	
	
	/*
	 * Test Case: 54951
	 * Description: 
	 * Expected: 
	 *   
	*/
	
	/*@Test(priority=7)
	public void testcase54951() throws IOException, InterruptedException
	{
		new DecisionEngineWorkflowPage(DRIVER,ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new EODWithAlwaysNoPay());
	}*/
	
	/*
	 * Test Case: 54952
	 * Description: 
	 * Expected: 
	 *   
	*/
	/*
	@Test(priority=8)
	public void testcase54952() throws IOException, InterruptedException
	{
		new DecisionEngineWorkflowPage(DRIVER,ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new EODWithItemsInDecisionEngine());
	}*/
	
	/*
	 * Test Case: 54953
	 * Description: 
	 * Expected: 
	 *   
	*/
	
	/*@Test(priority=9)
	public void testcase54953() throws IOException, InterruptedException
	{
		new DecisionEngineWorkflowPage(DRIVER,ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new VerifyPendingPaymentsPageAfterEOD());
	}*/
	

	/*
	 * Test Case: 54954
	 * Description: 
	 * Expected: 
	 *   
	*/
	
	/*@Test(priority=10)
	public void testcase54954() throws IOException, InterruptedException
	{
		new DecisionEngineWorkflowPage(DRIVER,ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new PendingPaymentSingleWorkFLowItems());
	}*/
	
	/*
	 * Test Case: 51680
	 * Description: 
	 * Expected: 
	 *   
	*/
	
	/*@Test(priority=11)
	public void testcase_51680() throws IOException, InterruptedException
	{
		new DecisionEngineWorkflowPage(DRIVER,ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new EodSearchMessageScenario());
	}
*/	
	/*
	 * Test Case: 51679
	 * Description: 
	 * Expected: 
	 *   
	*/
/*	@Test(priority=12)
	public void testcase_51679() throws IOException, InterruptedException
	{
		new DecisionEngineWorkflowPage(DRIVER,ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new PendingPaymentSingleWorkFLowItems());
	}
*/	
	/*
	 * Test Case: 53722
	 * Description: 
	 * Expected: 
	 *   
	*/

	/*@Test(priority=13)
	public void testcase_53722() throws IOException, InterruptedException
	{
		new DecisionEngineWorkflowPage(DRIVER,ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new EODActivationScenario());
	}
	*/
	/*
	 * Test Case: 
	 * Description: 
	 * Expected: 
	 *   
	*/
	/*@Test(priority=14)
	public void testcase_() throws IOException, InterruptedException
	{
		new DecisionEngineWorkflowPage(DRIVER,ICSPropertiesConfig.getDewEnvironmentURL())
		.run(new EODPendingPaymentAndDecisionScreenScenario());
	}
	
*/	
}
