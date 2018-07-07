package com.ics.tests.mo;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.ics.mo.common.MoDbAndEntityNames;
import com.ics.mo.common.MoGenericMethods2;
import com.ics.mo.xmlFiles.isoContent2.Msg01FM01;
import com.ics.mo.xmlFiles.isoContent2.Msg04;
import com.ics.mo.xmlFiles.isoContent2.Msg05;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

/**
 * <h1>TestBankHoliday</h1>
 * This class file contains TestScripts to check the Functionality of Bank Holiday Message flows.
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-04-15
 */
public class TestBankHolidayFlow extends ICSAutomationCommonSetup
{
	private String regressionCycle = getRequiredRegressionCycleName(new Object(){});
	private String inputSheetPathNameForMsg04;
	private String msg01FM01InputFilePath;
	private String msg01FM01InputFileName;
	private String msg05SM01InputFilePath;
	private String msg05SM01InputFileName;
	private String msg04SM01InputFilePath;
	private String msg04SM01InputFileName;
	private String msg05Flow = "MSG05";
	private String msg04Flow = "MSG04";
	private List<String> msg01TsetIds = new ArrayList<String>();
	private List<String> msg05TsetIds = new ArrayList<String>();
	private List<String> msg01DebitItems = new ArrayList<String>();
	private Msg01FM01 msg01FM01;
	private Msg05 msg05;
	private Msg04 msg04;
	private MoGenericMethods2  moGenericMethods;
	private static final String testCaseName = "TestBankHolidayFlow";
	private static final Logger testBankHoliday = Logger.getLogger(TestBankHolidayFlow.class.getSimpleName());
	
	/**
	 * This script is to get the Required Test-Data for BankHoliday Flow
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void getTestData() throws Exception
	{
		testBankHoliday.info("Running precondition test-setup script");
		inputSheetPathNameForMsg04 = getInputSheetOfMoRegression(regressionCycle);	
		instanceCreation();
		
		//Msg01FM01 Test data file
		msg01FM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow01FM01);
		msg01FM01InputFileName = getTestDataFileName(msg01FM01InputFilePath);
		moGenericMethods.getInputXmlDataOfMoToFredFile(testCaseName , workFlow01FM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), regressionCycle);
		testBankHoliday.info("MSG01FM01 file considered "+ msg01FM01InputFileName + " from filepath "+ msg01FM01InputFilePath);
		
		//Msg05SM01 Test data file
		msg05SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow05SM01);
		msg05SM01InputFileName = getTestDataFileName(msg05SM01InputFilePath);	
		setStOutputTestData(inputSheetPathNameForMsg04, testCaseName, workFlow05SM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), msg05SM01InputFileName);
		testBankHoliday.info("MSG05 file considered : "+ msg05SM01InputFileName + " from filepath "+ msg05SM01InputFilePath);
		
		//Msg04SM01 Test data file
		msg04SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow04SM01);
		msg04SM01InputFileName = getTestDataFileName(msg04SM01InputFilePath);	
		setStOutputTestData(inputSheetPathNameForMsg04, testCaseName, workFlow04SM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), msg04SM01InputFileName);
		testBankHoliday.info("MSG04 file considered : "+ msg04SM01InputFileName + " from filepath "+ msg04SM01InputFilePath);
	}
	
	/**
	 * This testScript method is to test 01FM01 Message Flow
	 * @throws Exception
	 */
	@Test(priority=2, dependsOnMethods={"getTestData"})
	public void test01FM01() throws Exception
	{
		scriptStartLog(testBankHoliday, validationHeader + workFlow01FM01);
		validationStepInformation(testCaseName + " validation for " + workFlow01FM01);	
		executionStoredProcedureCall(MoDbAndEntityNames.moServerDetails, MoDbAndEntityNames.repositorySchema, getSqlsTestDataLocationPath()
			+ regressionCycle + directoryPathSeperationSymbol + testCaseName.replaceAll("Test", "test") 
				+ directoryPathSeperationSymbol + workFlow01FM01 + directoryPathSeperationSymbol , sql01FM01);
		msg01FM01.check01FM01FileInsertInReceiveStaging(workFlow01FM01,  moGenericMethods.getExtractId());
		msg01TsetIds = moGenericMethods.getTsetIds();
		msg01DebitItems = moGenericMethods.getDebitItems();
		moGenericMethods.scheduleIdWithRowId(workFlow01FM01, msg01FM01.getRowId())
		.checkScheduleIdStatus(workFlow01FM01, moGenericMethods.getScheduleId(), workFlow01MA01, expectedDebitItemId)
		.checkSourceIDOfICNcontent(workFlow01FM01, moGenericMethods.getTsetIds());
		msg01FM01.check01FM01ContentInSourceTable(workFlow01FM01,moGenericMethods.getTsetIds(),moGenericMethods.getICNSourceID());
		moGenericMethods.checkStateFromItemAndItemStateTable(workFlow01FM01, msg01FM01.getItemIdAndItsStates(), MoDbAndEntityNames.collector)
		.checkStateTxSetAndTxSetStateTable(workFlow01FM01, msg01FM01.getTsetIdAndItsStates(), MoDbAndEntityNames.collector);	
		workFlowLoadFinalResultStatement(workFlow01FM01);	
	}
	
	/**
	 * This testScript method is to test 05SM01 Message Flow
	 * @throws Exception
	 */
	@Test(priority=3, dependsOnMethods={"test01FM01"})
	public void test05SM01() throws Exception
	{
		scriptStartLog(testBankHoliday, validationHeader + workFlow05SM01);
		validationStepInformation(testCaseName + " validation for " + workFlow05SM01);	
		moGenericMethods.getInputXmlDataOfSwitchFile(testCaseName , workFlow05SM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), regressionCycle);
		fileCopy(msg05SM01InputFilePath + msg05SM01InputFileName, moGenericMethods.getEAVLocation(workFlow05SM01), msg05SM01InputFileName);
		testBankHoliday.info(msg05SM01InputFileName + " file copied to EAV location "+ moGenericMethods.getEAVLocation(workFlow05SM01));	
		msg05TsetIds = moGenericMethods.getTsetIds();
		moGenericMethods.setMsg05SM01FileName(msg05SM01InputFileName)
		.scheduleIdWithFileName(workFlow05SM01, moGenericMethods.getMsg05SM01FileName())
		.checkScheduleIdStatus(workFlow05SM01, moGenericMethods.getScheduleId(), workFlow05MA01, moGenericMethods.getDebitItems())
		.checkSourceIDOfISOcontent(msg05Flow, moGenericMethods.getDebitItems())
		.checkStateFromItemAndItemStateTable(workFlow05SM01, msg05.getItemIdAndItState(moGenericMethods.getDebitItems()), MoDbAndEntityNames.beneficiary)
		.checkStateTxSetAndTxSetStateTable(workFlow05SM01, msg05.getTseIdAndItState(moGenericMethods.getTsetIds()), MoDbAndEntityNames.beneficiary);	
		msg05.checkMSg05ISOContentOfSourceTable(msg05SM01InputFilePath , msg05SM01InputFileName, moGenericMethods.getDebitItems());
		workFlowLoadFinalResultStatement(workFlow05SM01);			
	}
	
	/**
	 * This testScript method is to test 04SM01 Message Flow
	 * @throws Exception
	 */
	@Test(priority=4, dependsOnMethods={"test05SM01"})
	public void test04SM01() throws Exception
	{
		scriptStartLog(testBankHoliday, validationHeader + workFlow04SM01);
		validationStepInformation(testCaseName + " validation for " + workFlow04SM01);	
		moGenericMethods.getInputXmlDataOfSwitchFile(testCaseName , workFlow04SM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), regressionCycle);
		fileCopy(msg04SM01InputFilePath + msg04SM01InputFileName, moGenericMethods.getEAVLocation(workFlow04SM01), msg04SM01InputFileName);
		moGenericMethods.setMsg04SM01FileName(msg04SM01InputFileName)
		.scheduleIdWithFileName(workFlow04SM01, moGenericMethods.getMsg04SM01FileName())
		.checkScheduleIdStatus(workFlow04SM01, moGenericMethods.getScheduleId(), "", moGenericMethods.getDebitItems())
		.checkSourceIDOfISOcontent(msg04Flow, moGenericMethods.getDebitItems());
		msg04.getTsetIdsOfMsg04(moGenericMethods.getISOSourceID())
		.getExpectedTsetStateOfReceiveStagingTset(msg01TsetIds, msg05TsetIds);		
		workFlowLoadFinalResultStatement(workFlow04SM01);	
	}
	
	/**
	 * This testScript method is to test test04MM01 Message Flow
	 * @throws Exception
	 */
	@Test(priority=5, dependsOnMethods={"test04SM01"})
	public void test04MM01() throws Exception
	{
//		scriptStartLog(testBankHoliday, validationHeader + workFlow04MM01);
//		validationStepInformation(testCaseName + " validation for " + workFlow04MM01);	
//		moGenericMethods.scheduleIdWithRowId(workFlow04MM01, msg04.getRowIdOfMsg04SM01())
//		.checkScheduleIdStatus(workFlow04MM01, moGenericMethods.getScheduleId(), "", moGenericMethods.getDebitItems())
////		.checkSourceIDOfISOcontent(workFlow04MM01, moGenericMethods.getDebitItems())
////		.checkStateInReceiveStagingTset(msg04.getMSG04TsetIds(), workFlow04MM01)
////		.checkItemStateForMsg01AndMsg05(msg01TsetIds, msg05TsetIds, workFlow04MM01);
//		workFlowLoadFinalResultStatement(workFlow04MM01);	
	}
	
	/**
	 * This testScript method is to test test04MA01 Message Flow
	 * @throws Exception
	 */
	@Test(priority=6, dependsOnMethods={"test04MM01"})
	public void test04MA01() throws Exception
	{
//		scriptStartLog(testBankHoliday, validationHeader + workFlow04MA01);
//		validationStepInformation(testCaseName + " validation for " + workFlow04MA01);	
//		moGenericMethods.scheduleIdWithIdsList(workFlow04MA01,msg01DebitItems)
//		.checkScheduleIdStatus(workFlow04MA01, moGenericMethods.getScheduleId(), "", msg01DebitItems)
//		.checkSourceIDOfICNcontent(workFlow04MA01, msg01DebitItems)
//		.checkItemStateForMsg01AndMsg05(msg01TsetIds, msg05TsetIds, workFlow04MA01);
//		workFlowLoadFinalResultStatement(workFlow04MA01);	
	}
	
	/**
	 * This method is to create instance for MO class files to access
	 */
	private void instanceCreation()
	{
		moGenericMethods = new MoGenericMethods2(EXTENT, EXTENTLOG);
		msg01FM01 = new Msg01FM01(EXTENT, EXTENTLOG);
		msg05 = new Msg05(EXTENT, EXTENTLOG);
		msg04 = new Msg04(EXTENT, EXTENTLOG);
	}	
}
