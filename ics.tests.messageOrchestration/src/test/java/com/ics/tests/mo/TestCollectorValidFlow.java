package com.ics.tests.mo;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.ics.mo.common.MoDbAndEntityNames;
import com.ics.mo.common.MoGenericMethods2;
import com.ics.mo.xmlFiles.isoContent2.Msg01FM01;
import com.ics.mo.xmlFiles.isoContent2.MsgKM01;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

/**
 * <h1>TestCollectorFlow</h1>
 * This class file contains TestScripts to check the Functionality of Collector Message flows.
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-04-15
 */
public class TestCollectorValidFlow extends ICSAutomationCommonSetup
{
	private String inputSheetPathNameForMsg01;
	private String msg01FM01InputFilePath;
	private String msg01FM01InputFileName;
	private String msg01KM01FileName;
	private String msg01KM01FilePath;
	private MoGenericMethods2  moGenericMethods;
	private Msg01FM01 msg01FM01;
	private MsgKM01 msg01KM01;
	private String regressionCycle = getRequiredRegressionCycleName(new Object(){});
	private static final String testCaseName = "TestCollectorFlow";
	private static final Logger testCollectorFlow = Logger.getLogger(TestCollectorValidFlow.class.getSimpleName());	
	
	/**
	 * This script is to get the Required Test-Data for CollectorFlow
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void getTestData() throws Exception 
	{
		testCollectorFlow.info("Running precondition test-setup script");	
		inputSheetPathNameForMsg01 = getInputSheetOfMoRegression(regressionCycle);
		instanceCreation();
		//Msg01FM01 Test data file
		msg01FM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow01FM01);		
		msg01FM01InputFileName = getTestDataFileName(msg01FM01InputFilePath);
		testCollectorFlow.info("MSG01FM01 file considered "+ msg01FM01InputFileName + " from filepath "+ msg01FM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg01, testCaseName, workFlow01FM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), msg01FM01InputFileName);			
		moGenericMethods.getInputXmlDataOfMoToFredFile(testCaseName , workFlow01FM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), regressionCycle);
		msg01KM01FilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow01KM01);
		msg01KM01FileName = getTestDataFileName(msg01KM01FilePath);
		testCollectorFlow.info("MSG01KM01 file considered "+ msg01KM01FileName + " from filepath "+ msg01KM01FilePath);
		setStOutputTestData(inputSheetPathNameForMsg01, testCaseName, workFlow01KM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), msg01KM01FileName);	
		moGenericMethods.setMsg01KM01FileName(msg01KM01FileName);
	}
	
	/**
	 * This testScript method is to test 01FM01 Message Flow and required table validations
	 * @throws Exception
	 */
	@Test(priority = 2, dependsOnMethods={"getTestData"})
	public void test01FM01() throws Exception
	{
		scriptStartLog(testCollectorFlow, validationHeader + workFlow01FM01);
		validationStepInformation(testCaseName + " validation for " + workFlow01FM01);	
		executionStoredProcedureCall(MoDbAndEntityNames.moServerDetails, MoDbAndEntityNames.repositorySchema, getSqlsTestDataLocationPath()
			+ regressionCycle + directoryPathSeperationSymbol + testCaseName.replaceAll("Test", "test") 
				+ directoryPathSeperationSymbol + workFlow01FM01 + directoryPathSeperationSymbol , sql01FM01);
		msg01FM01.check01FM01FileInsertInReceiveStaging(workFlow01FM01,  moGenericMethods.getExtractId());
		moGenericMethods.scheduleIdWithRowId(workFlow01FM01, msg01FM01.getRowId())
		.checkScheduleIdStatus(workFlow01FM01, moGenericMethods.getScheduleId(), workFlow01MA01, expectedDebitItemId)
		.checkSourceIDOfICNcontent(workFlow01FM01, moGenericMethods.getTsetIds());
		msg01FM01.check01FM01ContentInSourceTable(workFlow01FM01,moGenericMethods.getTsetIds(),moGenericMethods.getICNSourceID());
		moGenericMethods.checkStateFromItemAndItemStateTable(workFlow01FM01, msg01FM01.getItemIdAndItsStates(), MoDbAndEntityNames.collector)
		.checkStateTxSetAndTxSetStateTable(workFlow01FM01, msg01FM01.getTsetIdAndItsStates(), MoDbAndEntityNames.collector);	
		workFlowLoadFinalResultStatement(workFlow01FM01);	
	}
	
	/**
	 * This testScript method is to test 01MA01 Message flow and required table validations
	 * @throws Exception
	 */
	@Test(priority = 3, dependsOnMethods={"test01FM01"})
	public void test01MA01() throws Exception
	{
		scriptStartLog(testCollectorFlow, validationHeader + workFlow01MA01);
		validationStepInformation(testCaseName + " validation for " + workFlow01MA01);
		moGenericMethods.scheduleIdWithIdsList(workFlow01MA01, moGenericMethods.getTsetIds())
		.checkScheduleIdStatus(workFlow01MA01, moGenericMethods.getScheduleId(), workFlow01MK01, moGenericMethods.getDebitItems())
		.checkSourceIDOfISOcontent(workFlow01MA01, moGenericMethods.getDebitItems())
		.checkStateFromItemAndItemStateTable(workFlow01MA01, msg01FM01.getItemIdAndItsStates(), MoDbAndEntityNames.collector)
		.checkStateTxSetAndTxSetStateTable(workFlow01MA01, msg01FM01.getTsetIdAndItsStates(), MoDbAndEntityNames.collector);
		workFlowLoadFinalResultStatement(workFlow01MA01);	
	}
	
	/**
	 * This testScript method is to test 01MK01 Message flow next after 01FM01 flow
	 * and required table validations
	 * @throws Exception
	 */
	@Test(priority = 4, dependsOnMethods={"test01MA01"})
	public void test01MK01() throws Exception
	{
		scriptStartLog(testCollectorFlow, validationHeader + workFlow01MK01);
		validationStepInformation(testCaseName + " validation for " + workFlow01MK01);
		moGenericMethods.scheduleIdWithIdsList(workFlow01MK01, moGenericMethods.getTsetIds())
		.checkScheduleIdStatus(workFlow01MK01, moGenericMethods.getScheduleId(), workFlow01KM01, msg01FM01.getAllValidItems())
		.checkSourceIDOfISOcontent(workFlow01MK01, moGenericMethods.getDebitItems())
		.checkStateFromItemAndItemStateTable(workFlow01MK01, msg01FM01.getAllValidItemIds(), MoDbAndEntityNames.collector)
		.checkStateTxSetAndTxSetStateTable(workFlow01MK01, msg01FM01.getAllValidTsetIds(), MoDbAndEntityNames.collector)
		.checkSavedFileInEAVLocation(workFlow01MK01, moGenericMethods.getActivityId());
		workFlowLoadFinalResultStatement(workFlow01MK01);	
	}
	
    /**
   	 * This testScript method is to test 01KM01 Message flow next after 01FM01 flow
	 * and required table validations
     * @throws Exception
     */
	@Test(priority = 5, dependsOnMethods={"test01MK01"})
	public void test01KM01() throws Exception
	{
		scriptStartLog(testCollectorFlow, validationHeader + workFlow01KM01);
		validationStepInformation(testCaseName + " validation for " + workFlow01KM01);
		String fileName = getXMLFilePathOfRegressionCyle1(inputSheetPathNameForMsg01, testCaseName, workFlow01KM01, MoGenericMethods2.getColumnKeyAsXmlFileName()) ;
		fileCopy(getInputXmlBasePath(regressionCycle, testCaseName, workFlow01KM01 + directoryPathSeperationSymbol + fileName), moGenericMethods.getEAVLocation(workFlow01KM01), fileName);
		moGenericMethods.scheduleIdWithFileName(workFlow01KM01, moGenericMethods.getMsg01KM01FileName())
		.checkScheduleIdStatus(workFlow01KM01, moGenericMethods.getScheduleId(), workFlow01KM01, msg01FM01.getAllValidItems())
		.checkSourceIDOfISOcontent(workFlow01KM01, msg01FM01.getAllValidItems())
		.checkStateFromItemAndItemStateTable(workFlow01KM01, msg01KM01.getKappaResponseForKappaFile(msg01KM01FilePath, msg01KM01FileName, msg01FM01.getAllValidItemIds(), workFlow01KM01),
				MoDbAndEntityNames.collector);
//		.checkStateTxSetAndTxSetStateTable(workFlow01KM01, msg01FM01.getTsetIdAndItsStates(), MoDbAndEntityNames.collector);
		workFlowLoadFinalResultStatement(workFlow01KM01);	
	}
	
	/**
	 * This testScript method is to test 01MA03 Message flow next after 01FM01 flow
	 * @throws Exception
	 */
	@Test(priority = 6, dependsOnMethods={"test01KM01"})
	public void test01MA03() throws Exception
	{
		scriptStartLog(testCollectorFlow, validationHeader + workFlow01MA03);
		validationStepInformation(testCaseName + " validation for " + workFlow01MA03);
		moGenericMethods.scheduleIdWithIdsList(workFlow01MA03,  msg01FM01.getAllValidItems())
		.checkScheduleIdStatus(workFlow01MA03, moGenericMethods.getScheduleId(), workFlow01MM01, msg01FM01.getAllValidItems())
		.checkSourceIDOfISOcontent(workFlow01MA03, msg01FM01.getAllValidItems())
		.checkStateFromItemAndItemStateTable(workFlow01MA03, msg01KM01.getItemAndKappaDetails(), MoDbAndEntityNames.collector);
//		.checkStateTxSetAndTxSetStateTable(workFlow01KM01, msg01FM01.getTsetIdAndItsStates(), MoDbAndEntityNames.collector);
		workFlowLoadFinalResultStatement(workFlow01MA03);	
	}
	
	/**
	 * This testScript method is to test 01MM01 Message flow next after 01FM01 flow
	 * @throws Exception
	 */
	@Test(priority = 7, dependsOnMethods={"test01MA03"})
	public void test01MM01() throws Exception
	{
		scriptStartLog(testCollectorFlow, validationHeader + workFlow01MM01);
		validationStepInformation(testCaseName + " validation for " + workFlow01MM01);
		moGenericMethods.scheduleIdWithIdsList(workFlow01MM01,  msg01FM01.getAllValidItems())
		.checkScheduleIdStatus(workFlow01MM01, moGenericMethods.getScheduleId(), workFlow01MA05, msg01FM01.getAllValidItems())
		.checkSourceIDOfICNcontent(workFlow01MM01, msg01FM01.getAllValidItems())
		.checkStateFromItemAndItemStateTable(workFlow01MM01, msg01KM01.getItemAndKappaDetails(), MoDbAndEntityNames.collector);
//		.checkStateTxSetAndTxSetStateTable(workFlow01KM01, msg01FM01.getTsetIdAndItsStates(), MoDbAndEntityNames.collector);
		workFlowLoadFinalResultStatement(workFlow01MM01);			
	}
	
	/**
	 * This method is to create instance for MO class files to access
	 */
	private void instanceCreation()
	{
		//Instance Creation
		moGenericMethods = new MoGenericMethods2(EXTENT, EXTENTLOG);
		msg01FM01 = new Msg01FM01(EXTENT, EXTENTLOG);
		msg01KM01 = new MsgKM01(EXTENT, EXTENTLOG);		
	}
}
