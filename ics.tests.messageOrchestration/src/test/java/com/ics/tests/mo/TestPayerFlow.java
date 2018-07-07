package com.ics.tests.mo;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.ics.mo.common.MoDbAndEntityNames;
import com.ics.mo.common.MoGenericMethods2;
import com.ics.mo.xmlFiles.icnContent2.Msg06MD01;
import com.ics.mo.xmlFiles.icnContent2.MsgFM01InclearingFlow;
import com.ics.mo.xmlFiles.icnContent2.MsgMA02InclearingFlow;
import com.ics.mo.xmlFiles.isoContent2.Msg06;
import com.ics.mo.xmlFiles.isoContent2.Msg06DM01;
import com.ics.mo.xmlFiles.isoContent2.MsgKM01;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

/**
 * <h1>TestPayerFlow</h1>
 * This class file contains TestScripts to check the Functionality of Payer Message flows.
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-04-15
 */
public class TestPayerFlow extends ICSAutomationCommonSetup 
{
	private String regressionCycle = getRequiredRegressionCycleName(new Object(){});	
	private String inputSheetPathNameForMsg06;
	private String msg06SM01InputFilePath;
	private String msg06SM01InputFileName;
	private String msg06FM01InputFilePath;
	private String msg06FM01InputFileName;
	private String msg06KM01InputFilePath;
	private String msg06KM01InputFileName;
	private String msg06DM01InputFilePath;
	private String msg06DM01InputFileName;
	private MoGenericMethods2  moGenericMethods;
	private Msg06 msg06;
	private MsgKM01 msgKM01;
	private Msg06MD01 msg06MD01;
	private Msg06DM01 msg06DM01;
	private MsgFM01InclearingFlow msgFM01InclearingFlow;
	private MsgMA02InclearingFlow msgMA02InclearingFlow;
	private String msg06Flow = "MSG06";
	private static final String testCaseName = "TestPayerFlow";
	private static final Logger testPayerFlow = Logger.getLogger(TestPayerFlow.class.getSimpleName());

	/**
	 * This script is to get the Required Test-Data for PayerFlow
	 * @throws Exception
	 */
	@Test(priority = 1)
	public void getTestData() throws Exception 
	{
		testPayerFlow.info("Running precondition test-setup script");
		inputSheetPathNameForMsg06 = getInputSheetOfMoRegression(regressionCycle);	
		instanceCreation();

		//Msg06SM01 Test data file
		msg06SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow06SM01);
		msg06SM01InputFileName = getTestDataFileName(msg06SM01InputFilePath);	
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06SM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), msg06SM01InputFileName);
		String tempItemSubData = MoGenericMethods2.getXMLFileName(regressionCycle, testCaseName, workFlow06SM01, MoGenericMethods2.getColumnkeyAsTsetData());
		String tempSubMsgId = MoGenericMethods2.getXMLFileName(regressionCycle, testCaseName, workFlow06SM01, MoGenericMethods2.getColumnkeyAsMsgIdValue());
		moGenericMethods.createTestDataForSwitch(msg06SM01InputFilePath , msg06SM01InputFileName, tempItemSubData, tempSubMsgId);		
		msg06SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow06SM01);
		msg06SM01InputFileName = getTestDataFileName(msg06SM01InputFilePath);	
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06SM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), msg06SM01InputFileName);
		testPayerFlow.info("MSG06 file considered : "+ msg06SM01InputFileName + " from filepath "+ msg06SM01InputFilePath);
		moGenericMethods.getInputXmlDataOfSwitchFile(testCaseName , workFlow06SM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), regressionCycle)
		.setMsg06SM01FileName(msg06SM01InputFileName)
		.setupJobParamIds()
		.createJobTemplateForWorkFlows()	
		.packageRunForSODScheduleIds();

		//Msg06FM01 Test data file
		msg06FM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow06FM01);		
		msg06FM01InputFileName = getTestDataFileName(msg06FM01InputFilePath);
		testPayerFlow.info("MSG06FM01 file considered "+ msg06FM01InputFileName + " from filepath "+ msg06FM01InputFilePath);
		String tempFMextractId = MoGenericMethods2.getXMLFileName(regressionCycle, testCaseName, workFlow06FM01, MoGenericMethods2.getColumnkeyAsFMextractId());
		String msg06FM01SqlPath = getSqlsTestDataLocationPath()+ regressionCycle + directoryPathSeperationSymbol + testCaseName.replaceAll("Test", "test") 
		+ directoryPathSeperationSymbol + workFlow06FM01 + directoryPathSeperationSymbol;
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06FM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), msg06FM01InputFileName);			
		moGenericMethods.createdTestDataForFM(msg06FM01InputFilePath + msg06FM01InputFileName, msg06FM01SqlPath, tempFMextractId, tempItemSubData, workFlow06FM01)
        .getInputXmlDataOfMoToFredFile(testCaseName , workFlow06FM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), regressionCycle);

		//Msg06KM01 Test data file
		msg06KM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow06KM01);
		msg06KM01InputFileName = getTestDataFileName(msg06KM01InputFilePath);	
		testPayerFlow.info("MSF08 file considered : "+ msg06KM01InputFileName + " from filepath "+ msg06KM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06KM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), msg06KM01InputFileName);	
		moGenericMethods.setMsg06KM01FileName(msg06KM01InputFileName);

		//Msg06DM01 Test data file
		msg06DM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow06DM01);		
		msg06DM01InputFileName = getTestDataFileName(msg06DM01InputFilePath);
		String tempDMextractId = MoGenericMethods2.getXMLFileName(regressionCycle, testCaseName, workFlow06DM01, MoGenericMethods2.getColumnKeyAsDMextractId());
		String msg06DM01SqlPath = getSqlsTestDataLocationPath()+ regressionCycle + directoryPathSeperationSymbol + testCaseName.replaceAll("Test", "test") 
		+ directoryPathSeperationSymbol + workFlow06DM01 + directoryPathSeperationSymbol;
		testPayerFlow.info("MSG06DM01 file considered "+ msg06DM01InputFileName + " from filepath "+ msg06DM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06DM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), msg06DM01InputFileName);			
		moGenericMethods.createdTestDataForDM(msg06DM01InputFilePath + msg06DM01InputFileName, msg06DM01SqlPath, tempDMextractId, tempItemSubData, workFlow06DM01);
//		 .getInputXmlDataOfMoToFredFile(testCaseName , workFlow06DM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), regressionCycle);
		
		//Update the .Xls sheet Data for required WorkFlow
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06SM01, MoGenericMethods2.getColumnkeyAsMsgIdValue(), moGenericMethods.getNewReplacedMsgIdData());			
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06SM01, MoGenericMethods2.getColumnkeyAsTsetData(), moGenericMethods.getNewReplacedTestData());			
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06FM01, MoGenericMethods2.getColumnkeyAsTsetData(), moGenericMethods.getNewExtractId06FM01());			
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06DM01, MoGenericMethods2.getColumnkeyAsTsetData(), moGenericMethods.getNewExtractId06DM01());	
	}	

	/**
	 * This testScript method is to test 06SM01 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 2, dependsOnMethods={"getTestData"})
	public void test06SM01() throws Exception
	{	
		scriptStartLog(testPayerFlow, validationHeader + workFlow06SM01);
		validationStepInformation(testCaseName + "validation for " + workFlow06SM01);
		fileCopy(msg06SM01InputFilePath + msg06SM01InputFileName, moGenericMethods.getEAVLocation(workFlow06SM01), msg06SM01InputFileName);
		testPayerFlow.info(msg06SM01InputFileName + " file copied to EAV location "+ moGenericMethods.getEAVLocation(workFlow06SM01));	
		moGenericMethods.scheduleIdWithFileName(workFlow06SM01, moGenericMethods.getMsg06SM01FileName())
		.checkScheduleIdStatus(workFlow06SM01, moGenericMethods.getScheduleId(), workFlow06MA01, moGenericMethods.getDebitItems())
		.checkSourceIDOfISOcontent(msg06Flow, moGenericMethods.getDebitItems())
		.checkStateFromItemAndItemStateTable(workFlow06SM01, msg06.getItemIdAndItState(moGenericMethods.getDebitItems()), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06SM01, msg06.getTseIdAndItState(moGenericMethods.getTsetIds()), MoDbAndEntityNames.payer);	
		msg06.checkMSg06ISOContentOfSourceTable(msg06SM01InputFilePath , msg06SM01InputFileName, moGenericMethods.getDebitItems());
		workFlowLoadFinalResultStatement(workFlow06SM01);	
	}

	/**
	 * This testScript method is to test 06MA01 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 3, dependsOnMethods={"test06SM01"})
	public void test06MA01() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow06MA01);
		validationStepInformation(testCaseName + " validation for " + workFlow06MA01);
		moGenericMethods.scheduleIdWithIdsList(workFlow06MA01, moGenericMethods.getDebitItems())
		.checkScheduleIdStatus(workFlow06MA01, moGenericMethods.getScheduleId(), workFlow06MF01, moGenericMethods.getDebitItems())
		.checkSourceIDOfISOcontent(workFlow06MA01, moGenericMethods.getDebitItems())
		.checkStateFromItemAndItemStateTable(workFlow06MA01, msg06.getItemIdAndItState(moGenericMethods.getDebitItems()), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06MA01, msg06.getItemIdAndItState(moGenericMethods.getDebitItems()), MoDbAndEntityNames.payer);
		workFlowLoadFinalResultStatement(workFlow06MA01);	
	}

	/**
	 * This testScript method is to test 06MF01 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 4, dependsOnMethods={"test06MA01"})
	public void test06MF01() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow06MF01);
		validationStepInformation(testCaseName + " validation for " + workFlow06MF01);
		moGenericMethods.scheduleIdWithIdsList(workFlow06MF01, moGenericMethods.getDebitItems())
		.checkScheduleIdStatus(workFlow06MF01, moGenericMethods.getScheduleId(), "", moGenericMethods.getDebitItems())
		.checkSourceIDOfICNcontent(workFlow06MF01, moGenericMethods.getDebitItems())
		.checkStateFromItemAndItemStateTable(workFlow06MF01, msg06.getItemIdAndItState(moGenericMethods.getDebitItems()), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06MF01, msg06.getItemIdAndItState(moGenericMethods.getDebitItems()), MoDbAndEntityNames.payer);
		workFlowLoadFinalResultStatement(workFlow06MF01);	
	}	

	/**
	 * This testScript method is to test 06FM01 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 5, dependsOnMethods={"test06MF01"})
	public void test06FM01() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow06FM01);
		validationStepInformation(testCaseName + " validation for " + workFlow06FM01);
		executionStoredProcedureCall(MoDbAndEntityNames.moServerDetails, MoDbAndEntityNames.repositorySchema, getSqlsTestDataLocationPath()
				+ regressionCycle + directoryPathSeperationSymbol + testCaseName.replaceAll("Test", "test") 
				+ directoryPathSeperationSymbol + workFlow06FM01 + directoryPathSeperationSymbol , sql06FM01);
		msgFM01InclearingFlow.getMsgIdOfFM01InReceiveStaging(workFlow06FM01, moGenericMethods.getExtractId())
		.validateFM01ICNdataInReceiveStage(msg06FM01InputFilePath + msg06FM01InputFileName, workFlow06FM01, moGenericMethods.getDebitItems(), msg06.getMapofItemIdAndItsBankDetails());
		moGenericMethods.scheduleIdWithRowId(workFlow06FM01, msgFM01InclearingFlow.getRowIdOfFM01Inclearing())
		.checkScheduleIdStatus(workFlow06FM01, moGenericMethods.getScheduleId(), workFlow06MA02, msgFM01InclearingFlow.getItemsFromFRED())
		.checkSourceIDOfICNcontent(workFlow06FM01, msgFM01InclearingFlow.getItemsFromFRED())
		.checkStateFromItemAndItemStateTable(workFlow06FM01, msgFM01InclearingFlow.getInitalStateFromFRED(), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06FM01, msgFM01InclearingFlow.getInitalStateFromFRED(), MoDbAndEntityNames.payer);
		workFlowLoadFinalResultStatement(workFlow06FM01);	
	}	

	/**
	 * This testScript method is to test 06MA02 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 6, dependsOnMethods={"test06FM01"})
	public void test06MA02() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow06MA02);
		validationStepInformation(testCaseName + " validation for " + workFlow06MA02);
		moGenericMethods.scheduleIdWithIdsList(workFlow06MA02, msgFM01InclearingFlow.getItemsFromFRED())
		.checkScheduleIdStatus(workFlow06MA02, moGenericMethods.getScheduleId(), workFlow06MK01, msgFM01InclearingFlow.getItemsFromFRED())
		.checkSourceIDOfICNcontent(workFlow06MA02, msgFM01InclearingFlow.getItemsFromFRED())
		.checkStateFromItemAndItemStateTable(workFlow06MA02, msgFM01InclearingFlow.getInitalStateFromFRED(), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06MA02, msgFM01InclearingFlow.getInitalStateFromFRED(), MoDbAndEntityNames.payer);
		msgMA02InclearingFlow.validateICNContentInSourceForMsgMA02(msgFM01InclearingFlow.getFM01InclearingICNcontent(),
				moGenericMethods.getICNContent(), workFlow06MA02, workFlow06FM01);
		workFlowLoadFinalResultStatement(workFlow06MA02);	
	}

	/**
	 * This testScript method is to test 06MK01 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 7, dependsOnMethods={"test06MA02"})
	public void test06MK01() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow06MK01);
		validationStepInformation(testCaseName + " validation for " + workFlow06MK01);
		moGenericMethods.scheduleIdWithIdsList(workFlow06MK01, msgFM01InclearingFlow.getItemsFromFRED())	
		.checkScheduleIdStatus(workFlow06MK01, moGenericMethods.getScheduleId(), "", msgFM01InclearingFlow.getItemsFromFRED())
		.checkSourceIDOfISOcontent(workFlow06MK01, msgFM01InclearingFlow.getItemsFromFRED())
		.checkStateFromItemAndItemStateTable(workFlow06MK01, msgFM01InclearingFlow.getInitalStateFromFRED(), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06MK01, msgFM01InclearingFlow.getInitalStateFromFRED(), MoDbAndEntityNames.payer)
		.checkSavedFileInEAVLocation(workFlow06MK01, moGenericMethods.getActivityId());
		workFlowLoadFinalResultStatement(workFlow06MK01);
	}

	/**
	 * This testScript method is to test 06KM01 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 8, dependsOnMethods={"test06MK01"})
	public void test06KM01() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow06KM01);
		validationStepInformation(testCaseName + " validation for " + workFlow06KM01);
		fileCopy(msg06KM01InputFilePath + msg06KM01InputFileName, moGenericMethods.getEAVLocation(workFlow06KM01), msg06KM01InputFileName);
		testPayerFlow.info(msg06KM01InputFileName + " file copied to EAV location "+ moGenericMethods.getEAVLocation(workFlow06KM01));	
		msgKM01.getKappaResponseForKappaFile(msg06KM01InputFilePath, msg06KM01InputFileName, msgFM01InclearingFlow.getInitalStateFromFRED(), workFlow06KM01);
		moGenericMethods.scheduleIdWithFileName(workFlow06KM01, moGenericMethods.getMsg06KM01FileName())
		.checkScheduleIdStatus(workFlow06KM01, moGenericMethods.getScheduleId(), workFlow06MA03, msgKM01.getKAPPAitems())	
		.checkSourceIDOfISOcontent("MSF08", msgKM01.getKAPPAitems())
		.checkStateFromItemAndItemStateTable(workFlow06KM01, msgKM01.getItemAndKappaDetails(), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06KM01, msgKM01.getItemAndKappaDetails(), MoDbAndEntityNames.payer);
		workFlowLoadFinalResultStatement(workFlow06KM01);
	}

	/**
	 * This testScript method is to test 06MA03 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 9, dependsOnMethods={"test06KM01"})
	public void test06MA03() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow06MA03);
		validationStepInformation(testCaseName + " validation for " + workFlow06MA03);
		moGenericMethods.scheduleIdWithIdsList(workFlow06MA03, msgKM01.getKAPPAitems())	
		.checkScheduleIdStatus(workFlow06MA03, moGenericMethods.getScheduleId(), workFlow06MD01, msgKM01.getKAPPAitems())
		.checkSourceIDOfICNcontent(workFlow06MA03, msgKM01.getKAPPAitems())
		.checkStateFromItemAndItemStateTable(workFlow06MA03,  msgKM01.getItemAndKappaDetails(), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06MA03,  msgKM01.getItemAndKappaDetails(), MoDbAndEntityNames.payer);
		workFlowLoadFinalResultStatement(workFlow06MA03);
	}

	/**
	 * This testScript method is to test 06MD01 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 10, dependsOnMethods={"test06MA03"})
	public void test06MD01() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow06MD01);
		validationStepInformation(testCaseName + " validation for " + workFlow06MD01);
		moGenericMethods.scheduleIdWithIdsList(workFlow06MD01, msgKM01.getKAPPAitems())	
		.checkScheduleIdStatus(workFlow06MD01, moGenericMethods.getScheduleId(), workFlow06MD01, msgKM01.getKAPPAitems())
		.checkSourceIDOfICNcontent(workFlow06MD01, msgKM01.getKAPPAitems())
		.checkStateFromItemAndItemStateTable(workFlow06MD01,  msgKM01.getItemAndKappaDetails(), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06MD01,  msgKM01.getItemAndKappaDetails(), MoDbAndEntityNames.payer);
		msg06MD01.validateCodeLineDetails(workFlow06MD01, moGenericMethods.getICNContent(), msg06.getMapofItemIdAndItsBankDetails());
		workFlowLoadFinalResultStatement(workFlow06MD01);
	}

	/**
	 * This testScript method is to test 06DM01 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 11, dependsOnMethods={"test06MD01"})
	public void test06DM01() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow06DM01);
		validationStepInformation(testCaseName + " validation for " + workFlow06DM01);
		executionStoredProcedureCall(MoDbAndEntityNames.moServerDetails, MoDbAndEntityNames.repositorySchema, getSqlsTestDataLocationPath()
				+ regressionCycle + directoryPathSeperationSymbol + testCaseName.replaceAll("Test", "test") 
				+ directoryPathSeperationSymbol + workFlow06DM01 + directoryPathSeperationSymbol , sql06DM01);
		moGenericMethods.getInputXmlDataOfMoToFredFile(testCaseName , workFlow06DM01, MoGenericMethods2.getColumnKeyAsXmlFileName(), regressionCycle);
		msg06DM01.getMsgIdOfDM01InReceiveStaging(workFlow06DM01, moGenericMethods.getExtractId())
		.validateDM01ICNdataInReceiveStage(msg06DM01InputFilePath + msg06DM01InputFileName, workFlow06DM01);
		moGenericMethods.scheduleIdWithRowId(workFlow06DM01, msg06DM01.getRowIdOfDM01())
		.checkScheduleIdStatus(workFlow06DM01, moGenericMethods.getScheduleId(), workFlow06MA04, msg06DM01.getItemsFromDEW())
		.checkSourceIDOfICNcontent(workFlow06DM01, msg06DM01.getItemsFromDEW())
		.checkStateFromItemAndItemStateTable(workFlow06DM01, msg06DM01.getDEWresponse(), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06DM01, msg06DM01.getDEWresponse(), MoDbAndEntityNames.payer);
		workFlowLoadFinalResultStatement(workFlow06DM01);	
	}

	/**
	 * This testScript method is to test 06MA04 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 12, dependsOnMethods={"test06DM01"})
	public void test06MA04() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow06MA04);
		validationStepInformation(testCaseName + " validation for " + workFlow06MA04);
		moGenericMethods.scheduleIdWithIdsList(workFlow06MA04, msg06DM01.getItemsFromDEW())	
		.checkScheduleIdStatus(workFlow06MA04, moGenericMethods.getScheduleId(), workFlow07MS01, msg06DM01.getItemsFromDEW())
		.checkSourceIDOfICNcontent(workFlow06MA04, msg06DM01.getItemsFromDEW())
		.checkStateFromItemAndItemStateTable(workFlow06MA04, msg06DM01.getDEWresponse(), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow06MA04, msg06DM01.getDEWresponse(), MoDbAndEntityNames.payer);
		workFlowLoadFinalResultStatement(workFlow06MA04);
	}

	/**
	 * This testScript method is to test 07MS01 Message Flow
	 * @throws Exception
	 */
	@Test(priority = 13, dependsOnMethods={"test06MA04"})
	public void test07MS01() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow07MS01);
		validationStepInformation(testCaseName + " validation for " + workFlow07MS01);
		moGenericMethods.scheduleIdWithIdsList(workFlow07MS01, msg06DM01.getItemsOf07MS01())	
		.checkScheduleIdStatus(workFlow07MS01, moGenericMethods.getScheduleId(), workFlow07MA01, msg06DM01.getItemsOf07MS01())
		.checkSourceIDOfISOcontent(workFlow07MS01, msg06DM01.getItemsOf07MS01())
		.checkStateFromItemAndItemStateTable(workFlow07MS01, msg06DM01.get07MS01ItemsAndItsState(), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow07MS01, msg06DM01.get07MS01ItemsAndItsState(), MoDbAndEntityNames.payer);
		msg06MD01.validateCodeLineDetails(workFlow07MS01, moGenericMethods.getICNContent(), msg06.getMapofItemIdAndItsBankDetails());
		workFlowLoadFinalResultStatement(workFlow07MS01);
	}

	/**
	 * This testScript method is to test 07MA01 Message Flow
	 */
	@Test(priority = 14, dependsOnMethods={"test07MS01"})
	public void test07MA01() throws Exception
	{
		scriptStartLog(testPayerFlow, validationHeader + workFlow07MA01);
		validationStepInformation(testCaseName + " validation for " + workFlow07MA01);
		moGenericMethods.scheduleIdWithIdsList(workFlow07MA01, msg06DM01.getItemsOf07MS01())	
		.checkScheduleIdStatus(workFlow07MA01, moGenericMethods.getScheduleId(), "", msg06DM01.getItemsOf07MS01())
		.checkSourceIDOfICNcontent(workFlow07MA01, msg06DM01.getItemsOf07MS01())
		.checkStateFromItemAndItemStateTable(workFlow07MA01, msg06DM01.get07MS01ItemsAndItsState(), MoDbAndEntityNames.payer)
		.checkStateTxSetAndTxSetStateTable(workFlow07MA01, msg06DM01.get07MS01ItemsAndItsState(), MoDbAndEntityNames.payer);
	}	

	/**
	 * This method is to create instance for MO class files to access
	 */
	private void instanceCreation()
	{
		//Instance Creation
		moGenericMethods = new MoGenericMethods2(EXTENT, EXTENTLOG);
		msg06 = new Msg06(EXTENT, EXTENTLOG);
		msgFM01InclearingFlow = new MsgFM01InclearingFlow(EXTENT, EXTENTLOG);
		msgMA02InclearingFlow = new MsgMA02InclearingFlow(EXTENT, EXTENTLOG);
		msgKM01 = new MsgKM01(EXTENT, EXTENTLOG);
		msg06MD01 = new Msg06MD01(EXTENT, EXTENTLOG);
		msg06DM01 = new Msg06DM01(EXTENT, EXTENTLOG);
	}


	private void createUniqueTestData()
	{

	}
}