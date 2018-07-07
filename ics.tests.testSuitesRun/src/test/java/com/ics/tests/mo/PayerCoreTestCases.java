package com.ics.tests.mo;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ics.mo.common.MoGenericMethods;
import com.ics.mo.xmlFiles.icnContent.Msg06DM01;
import com.ics.mo.xmlFiles.icnContent.Msg06FM_Msg05FM;
import com.ics.mo.xmlFiles.icnContent.Msg06KM_Msg05KM;
import com.ics.mo.xmlFiles.icnContent.Msg06MA01;
import com.ics.mo.xmlFiles.icnContent.MsgMA02;
import com.ics.mo.xmlFiles.isoContent.Msg06;
import com.ics.mo.xmlFiles.isoContent.Msg06MD01;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

//<copyright  file="PayerCoreTestCases.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
 * Module Name -  PayerCoreTestCases.java
 ************************************************************************ 
 * Date -  26/04/2017
 ************************************************************************ 
 * Created By -  MuluguUm
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of Payer Message flows 
 ***********************************************************************/

/******************AMENDMENT HISTORY*********************************** 
 * Modified By - Devisetv   Date - 13/08/2017
 ***********************************************************************
 * Description - Updated method to check the created ScheduleId
 ***********************************************************************/

public class PayerCoreTestCases extends ICSAutomationCommonSetup {
	private static String regressionCycle = getRequiredRegressionCycleName(new Object(){});	
	private static String inputSheetPathNameForMsg06;
	private static String testCaseName ;
	private static String msg06SM01InputFilePath;
	private static String msg06SM01InputFileName;
	private static String msg06FM01InputFileName;
	private static String msg06FM01InputFilePath;
	private static String msg06KM01InputFilePath;
	private static String msg06KM01InputFileName;
	private static String msg06DM01InputFileName;
	private static String msg06DM01InputFilePath;
	private static String msg06SM01OutputFilePath;
	private static String msg06MA01OutputFilePath;
	private static String msg06FM01OutputFilePath;
	private static String msg06KM01OutputFilePath;
	private static String msg06DM01OutputFilePath;
	private static String msg06FM01SourceContent;
	private static String msg08SM01InputFileName;
	private static String msg08SM01InputFilePath;
	private static String msg11SM01InputFilePath;
	private static String msg11SM01InputFileName;
	private static String msg12SM01InputFilePath;
	private static String msg12SM01InputFileName;
	private static String msg06 = "MSG06";
	private static String msg06KM01 = "MSF08";
	private static String switchActualContentMODB = "ActualISOContent_MSG06.xml";
	private static String xsdSqlLocation;
	private static String sourceIDFor07MS01;
	private static final Logger payerCoreTestCasesLogger = Logger.getLogger(PayerCoreTestCases.class.getSimpleName());

	PayerCoreTestCases(String testCaseName, Logger logger)
	{
		PayerCoreTestCases.testCaseName = testCaseName;
		logger.info(testCaseName + " suite run started");
	}

	@BeforeClass
	public void deleteArchiveAndSetupTestData() throws Exception {
		payerCoreTestCasesLogger.info("Running precondition test-setup script");
		inputSheetPathNameForMsg06 = getInputSheetOfMoRegression(regressionCycle);

		//Msg06SM01 Test data file
		msg06SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow06SM01);
		System.out.println(msg06SM01InputFilePath);
		msg06SM01InputFileName = getTestDataFileName(msg06SM01InputFilePath);
		payerCoreTestCasesLogger.info("MSG06 file considered : "+ msg06SM01InputFileName + " from filepath "+ msg06SM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg06SM01InputFileName);
		Msg06.getInputXmlDataOfSwitchFile(testCaseName , workFlow06SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), regressionCycle);
		MoGenericMethods.setMsg06SM01FileName(msg06SM01InputFileName);

		//Msg06FM01 Test data file
		msg06FM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow06FM01);		
		msg06FM01InputFileName = getTestDataFileName(msg06FM01InputFilePath);
		payerCoreTestCasesLogger.info("MSG06FM01 file considered "+ msg06FM01InputFileName + " from filepath "+ msg06FM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06FM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg06FM01InputFileName);			
		MoGenericMethods.getInputXmlDataOfMoToFredFile(testCaseName , workFlow06FM01, MoGenericMethods.getColumnKeyAsXmlFileName(), regressionCycle);

		//Msg06KM01 Test data file
		msg06KM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow06KM01);
		msg06KM01InputFileName = getTestDataFileName(msg06KM01InputFilePath);
		payerCoreTestCasesLogger.info("MSG06KM01 file considered "+ msg06KM01InputFileName + " from filepath "+ msg06KM01InputFileName);
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06KM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg06KM01InputFileName);	
		MoGenericMethods.setMsg06KM01FileName(msg06KM01InputFileName);

		//Msg06DM01 Test data file
		msg06DM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow06DM01);		
		msg06DM01InputFileName = getTestDataFileName(msg06DM01InputFilePath);
		payerCoreTestCasesLogger.info("MSG06DM01 file considered "+ msg06DM01InputFileName + " from filepath "+ msg06DM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow06DM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg06DM01InputFileName);			

		//Msg12SM01 Test data file
		msg12SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow12SM01);		
		msg12SM01InputFileName = getTestDataFileName(msg12SM01InputFilePath);
		payerCoreTestCasesLogger.info("MSG12SM01 file considered "+ msg12SM01InputFileName + " from filepath "+ msg12SM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow12SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg12SM01InputFileName);			
		MoGenericMethods.setMsg12SM01FileName(msg12SM01InputFileName);

		//Msg11SM01 Test data file
		msg11SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow11SM01);		
		msg11SM01InputFileName = getTestDataFileName(msg11SM01InputFilePath);
		payerCoreTestCasesLogger.info("MSG11SM01 file considered "+ msg11SM01InputFileName + " from filepath "+ msg11SM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg06, testCaseName, workFlow11SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg11SM01InputFileName);			
		MoGenericMethods.setMsg11SM01FileName(msg11SM01InputFileName);

		//Msg06 Output Files to save Actual data from Source table
		msg06SM01OutputFilePath = getOutputXmlBasePath(regressionCycle, testCaseName, workFlow06SM01);
		msg06MA01OutputFilePath = getOutputXmlBasePath(regressionCycle, testCaseName, workFlow06MA01);
		msg06FM01OutputFilePath = getOutputXmlBasePath(regressionCycle, testCaseName, workFlow06FM01);
		msg06KM01OutputFilePath = getOutputXmlBasePath(regressionCycle, testCaseName, workFlow06KM01);
		msg06DM01OutputFilePath = getOutputXmlBasePath(regressionCycle, testCaseName, workFlow06DM01);

		msg08SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow08SM01);
		msg08SM01InputFileName = getTestDataFileName(msg08SM01InputFilePath);
		MoGenericMethods.setMsg08SM01FileName(msg08SM01InputFileName);

		xsdSqlLocation = getXsdTestDataLocationPath() + regressionCycle + directoryPathSeperationSymbol +
				testCaseName.replaceAll("Test", "test") + directoryPathSeperationSymbol ;

		deleteArchievedData();

		scriptStartLog(payerCoreTestCasesLogger, "Running script to create JobTemplates And IntradaySchedule Ids");
		MoGenericMethods.setupJobParamIds();
		MoGenericMethods.createJobTemplateForWorkFlows();	
		MoGenericMethods.packageRunForSODScheduleIds();

		Thread.sleep(15000);
	}	

	/*
	 * Test Case: TC:06SM01
	 * Description: Core test case to check 06SM01 file from switch loaded in MO with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 1)
	public void testCase_06SM01() throws Exception
	{	
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06SM01);
		validationStepInformation(testCaseName + "validation for " + workFlow06SM01);
		fileCopy(msg06SM01InputFilePath + msg06SM01InputFileName, MoGenericMethods.getEAVLocation(workFlow06SM01), msg06SM01InputFileName);
		payerCoreTestCasesLogger.info(msg06SM01InputFileName + " file copied to EAV location "+ MoGenericMethods.getEAVLocation(workFlow06SM01));
		MoGenericMethods.getCreatedScheduleId(workFlow06SM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow06SM01, MoGenericMethods.getScheduleId(), workFlow06MA01, expectedDebitItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow06SM01, expectedDebitItemId);
		MoGenericMethods.dbCheckSourceIdFromSourceTable(expectedSourceId.get(0), workFlow06SM01);
		MoGenericMethods.dbCheckTxSetIdInTransactionSetTable(workFlow06SM01);
		MoGenericMethods.dbCheckTxSetIdFromTransactionSetStateTable();
		Msg06.checkISOContentOfSourceTable(msg06SM01InputFilePath , msg06SM01InputFileName , msg06SM01OutputFilePath, msg06);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, msg06, expectedSourceId.get(0), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, msg06, expectedSourceId.get(0), dayTwoResponseWindow.get(0));
		MoGenericMethods.dbCheckActivityIdColumn();
		workFlowLoadFinalResultStatement(workFlow06SM01);	
	}

	/*
	 * Test Case: TC:06MA01
	 * Description: Core test case to check 06MA01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 2, dependsOnMethods={"testCase_06SM01"})
	public void testCase_06MA01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06MA01 );
		validationStepInformation(testCaseName + " validation for " + workFlow06MA01);
//		MoGenericMethods.test3();
		MoGenericMethods.getCreatedScheduleId(workFlow06MA01);
		MoGenericMethods.checkScheduleIdStatus(workFlow06MA01, MoGenericMethods.getScheduleId(), workFlow06MF01 , expectedDebitItemId );
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow06MA01, expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfISOContentWithDebitItem(workFlow06MA01);
		MoGenericMethods.dbCheckTxSetIdInTransactionSetTable(workFlow06MA01);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow06MA01, MoGenericMethods.getISOSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow06MA01, MoGenericMethods.getISOSourceID(), dayTwoResponseWindow.get(0));
		MoGenericMethods.checkXmlInIaTargetQueue(workFlow06MA01, MoGenericMethods.getISOSourceID(),
				"Check PayLoad-file to be availabile in MO-IATargetQueue DB");
		Msg06MA01.checkISOContentOfSourceTable(msg06SM01OutputFilePath, switchActualContentMODB, msg06MA01OutputFilePath, workFlow06MA01);
		Msg06MA01.checkICNInMA01ISOFileInSourceDB(Msg06.getISOContentOfMsg06FromSourceTable(), workFlow06MA01, "500" , Msg06.getSourceID());
		MoGenericMethods.xsdValidationForRequiredWorkFlow(workFlow06MA01, xsdSqlLocation + workFlow06MA01 + directoryPathSeperationSymbol, Msg06MA01.getSwitchSourceISOContent());
		workFlowLoadFinalResultStatement(workFlow06MA01);	
	}

	/*
	 * Test Case: TC:06MF01
	 * Description: Core test case to check 06MF01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 3, dependsOnMethods={"testCase_06MA01"})
	public void testCase_06MF01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06MF01);
		validationStepInformation(testCaseName + " validation for "  + workFlow06MF01);
//		MoGenericMethods.test3();
		MoGenericMethods.getCreatedScheduleId(workFlow06MF01);
		MoGenericMethods.checkScheduleIdStatus(workFlow06MF01, MoGenericMethods.getScheduleId(), workFlow06FM01 , expectedDebitItemId );
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow06MF01, expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlow06MF01);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow06MF01, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow06MF01, MoGenericMethods.getICNSourceID(), dayTwoResponseWindow.get(0));
		MoGenericMethods.checkXmlInFredTargetQueue(workFlow06MF01, MoGenericMethods.getICNSourceID(), 
				"Check PayLoad-file to be availabile in MO-FREDTargetQueue DB");
		MoGenericMethods.xsdValidationForRequiredWorkFlow(workFlow06MF01, xsdSqlLocation + workFlow06MF01 + directoryPathSeperationSymbol, MoGenericMethods.getICNSourceContent());
		workFlowLoadFinalResultStatement(workFlow06MF01);	
	}

	/*
	 * Test Case: TC:06FM01
	 * Description: Core test case to check 06FM01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 4, dependsOnMethods={"testCase_06MF01"})
	public void testCase_06FM01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06FM01 );
		validationStepInformation(testCaseName + " validation for " + workFlow06FM01);	
		executionStoredProcedureCall(MoGenericMethods.getMoDbServerDetails(), MoGenericMethods.getMoDbNameDetails(), getSqlsTestDataLocationPath()
				+ regressionCycle + directoryPathSeperationSymbol +testCaseName.replaceAll("Test", "test") + directoryPathSeperationSymbol + workFlow06FM01 + directoryPathSeperationSymbol , sql06FM01);	
		Msg06FM_Msg05FM.checkICNFileFM01InsertInReceiveStaging(msg06FM01InputFilePath , msg06FM01InputFileName , msg06FM01OutputFilePath, workFlow06FM01, expectedDebitItemId);
		MoGenericMethods.getCreatedScheduleId(workFlow06FM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow06FM01, MoGenericMethods.getScheduleId(), workFlow06MA02 , expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlow06FM01);
		msg06FM01SourceContent = MoGenericMethods.getICNSourceContent();
		MoGenericMethods.xsdValidationForRequiredWorkFlow(workFlow06FM01, xsdSqlLocation + workFlow06FM01 + directoryPathSeperationSymbol, Msg06FM_Msg05FM.getReceiveStagingContent());
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow06FM01, expectedDebitItemId);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow06FM01, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow06FM01, MoGenericMethods.getICNSourceID(), dayTwoResponseWindow.get(0));
		workFlowLoadFinalResultStatement(workFlow06FM01);			
	}

	/*
	 * Test Case: TC:06MA02
	 * Description: Core test case to check 06MA02 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 5, dependsOnMethods={"testCase_06FM01"})
	public void testCase_06MA02() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06MA02 );
		validationStepInformation(testCaseName + " validation for " + workFlow06MA02);
		MoGenericMethods.test3();
		MoGenericMethods.getCreatedScheduleId(workFlow06MA02);
		MoGenericMethods.checkScheduleIdStatus(workFlow06MA02, MoGenericMethods.getScheduleId(), workFlow06MK01 , expectedDebitItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow06MA02, expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlow06MA02);
		MsgMA02.validateICNContentInSourceForMsgMA02(msg06FM01SourceContent, MoGenericMethods.getICNSourceContent(), workFlow06MA02, workFlow06FM01);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow06MA02, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow06MA02, MoGenericMethods.getICNSourceID(), dayTwoResponseWindow.get(0));
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow06MA02);
	}		

	/*
	 * Test Case: TC:06MK01
	 * Description: Core test case to check 06MK01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 6 , dependsOnMethods={"testCase_06MA02"})
	public void testCase_06MK01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06MK01 );
		validationStepInformation(testCaseName +  " validation for " + workFlow06MK01);
		MoGenericMethods.test3();
		MoGenericMethods.getCreatedScheduleId(workFlow06MK01);
		MoGenericMethods.checkScheduleIdStatus(workFlow06MK01, MoGenericMethods.getScheduleId(), workFlow06KM01 , expectedDebitItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow06MK01, expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfISOContentWithDebitItem(workFlow06MK01);
		MoGenericMethods.dbCheckTxSetIdInTransactionSetTable(workFlow06MK01);
		MoGenericMethods.checkSavedFileInEAVLocation(workFlow06MK01, expectedDebitItemId, "Check in the 06MK01 folder of EAV location to ensure  the 06MK01 File generated");
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow06MK01, MoGenericMethods.getISOSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow06MK01, MoGenericMethods.getISOSourceID(), dayTwoResponseWindow.get(0));
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow06MK01);
	}

	/*
	 * Test Case: TC:06KM01
	 * Description: Core test case to check 06KM01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 7, dependsOnMethods={"testCase_06MK01"})
	public void testCase_06KM01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06KM01 );
		validationStepInformation(testCaseName + " validation for " + workFlow06KM01);
		String fileName = getXMLFilePathOfRegressionCyle1(inputSheetPathNameForMsg06, testCaseName, workFlow06KM01, MoGenericMethods.keyColumnDataAsXmlFileName) ;
		fileCopy(getInputXmlBasePath(regressionCycle, testCaseName, workFlow06KM01 + directoryPathSeperationSymbol + fileName), MoGenericMethods.getEAVLocation(workFlow06KM01), fileName);
		MoGenericMethods.getCreatedScheduleId(workFlow06KM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow06KM01, MoGenericMethods.getScheduleId(), workFlow06MA03 , expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfISOContentWithDebitItem(msg06KM01);
		Msg06KM_Msg05KM.checkICNFileKM01InsertInSourceTable(msg06KM01InputFilePath , msg06KM01InputFileName , msg06KM01OutputFilePath, msg06KM01);
		Msg06KM_Msg05KM.checkISOFile06KM01InsertInSourceTable(msg06KM01, expectedDebitItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow06KM01, expectedDebitItemId);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow06KM01, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow06KM01, MoGenericMethods.getICNSourceID(), dayTwoResponseWindow.get(0));
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow06KM01);
	}

	/*
	 * Test Case: TC:06MA03
	 * Description: Core test case to check 06MA03 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 8, dependsOnMethods={"testCase_06KM01"})
	public void testCase_06MA03() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06MA03 );
		validationStepInformation(testCaseName + " validation for " + workFlow06MA03);
		MoGenericMethods.test3();
		MoGenericMethods.getCreatedScheduleId(workFlow06MA03);
		MoGenericMethods.checkScheduleIdStatus(workFlow06MA03, MoGenericMethods.getScheduleId(), "" , expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlow06MA03);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow06MA03, expectedDebitItemId);
		MoGenericMethods.checkXmlInIaTargetQueue(workFlow06MA03, MoGenericMethods.getICNSourceID(),
				"Check PayLoad-file to be availabile in MO-IATargetQueue DB");
		MoGenericMethods.xsdValidationForRequiredWorkFlow(workFlow06MA03, xsdSqlLocation + workFlow06MA03 + directoryPathSeperationSymbol, MoGenericMethods.getICNSourceContent());
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow06MA03, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow06MA03, MoGenericMethods.getICNSourceID(), dayTwoResponseWindow.get(0));		
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow06MA03);
	}

	/*
	 * Test Case: TC:06MD01
	 * Description: Core test case to check 06MD01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 9, dependsOnMethods={"testCase_06MA03"})
	public void testCase_06MD01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06MD01 );
		validationStepInformation(testCaseName + " validation for " + workFlow06MD01);
		MoGenericMethods.test3();
		MoGenericMethods.getCreatedScheduleId(workFlow06MD01);
		MoGenericMethods.checkScheduleIdStatus(workFlow06MD01, MoGenericMethods.getScheduleId(), workFlow06DM01 , expectedDebitItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow06MD01, expectedDebitItemId);
		MoGenericMethods.dbCheckTxSetIdInTransactionSetTable(workFlow06MD01);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlow06MD01);
		Msg06MD01.validateCodeLineDetails(workFlow06MD01, MoGenericMethods.getICNSourceContent());
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow06MD01, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow06MD01, MoGenericMethods.getICNSourceID(), dayTwoResponseWindow.get(0));
		MoGenericMethods.checkXmlInDewTargetQueue(workFlow06MD01, MoGenericMethods.getICNSourceID(),
				"Check PayLoad-file to be availabile in MO-DEWTargetQueue DB");
		MoGenericMethods.xsdValidationForRequiredWorkFlow(workFlow06MD01, xsdSqlLocation + workFlow06MD01 + directoryPathSeperationSymbol, MoGenericMethods.getICNSourceContent());
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow06MD01);
	}

	/* Test Case: TC:12SM01
	 * Description: Core test case to check 06MD01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 10, dependsOnMethods={"testCase_06MD01"})
	public void testCase_12SM01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow12SM01 );
		validationStepInformation(testCaseName + " validation for " + workFlow12SM01);
		fileCopy(msg12SM01InputFilePath + msg12SM01InputFileName, MoGenericMethods.getEAVLocation(workFlow12SM01), msg12SM01InputFileName);
		MoGenericMethods.updateWindows2DateToDay1Date(expectedDebitItemId);
		payerCoreTestCasesLogger.info(msg12SM01InputFileName + " file copied to EAV location "+ MoGenericMethods.getEAVLocation(workFlow12SM01));
		MoGenericMethods.getCreatedScheduleId(workFlow12SM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow12SM01, MoGenericMethods.getScheduleId(), workFlow12MA01 , expectedDebitItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable("12SM01M", expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfISOContentWithDebitItem("MSG12");
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow12SM01);
	}

	/* Test Case: TC:12MA01
	 * Description: Core test case to check 06MD01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 11, dependsOnMethods={"testCase_12SM01"})
	public void testCase_12MA01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow12MA01 );
		validationStepInformation(testCaseName + " validation for " + workFlow12MA01);
		MoGenericMethods.getCreatedScheduleId(workFlow12MA01);
		MoGenericMethods.checkScheduleIdStatus(workFlow12MA01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlow12MA01);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow12MA01, expectedDebitItemId);
		MoGenericMethods.updateWindows2DateToDayTwoDate(expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow12MA01);

	}

	/* Test Case: TC:06MD01
	 * Description: Core test case to check 06MD01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 12, dependsOnMethods={"testCase_06MD01"})
	public void testCase_PTMA01_payer() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlowPTMA01 );
		validationStepInformation(testCaseName + " validation for " + workFlowPTMA01);
		MoGenericMethods.getItemSKID(expectedDebitItemId, workFlow06KM01);
		MoGenericMethods.getCreatedScheduleId(workFlowPTMA01);
		MoGenericMethods.checkScheduleIdStatus(workFlowPTMA01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId );
		MoGenericMethods.checkPostingStateForPTMA01(MoGenericMethods.derivedState520ItemIds, workFlowPTMA01, "945");
		MoGenericMethods.checkSourceIDOfICNContentRequiredDebit(workFlowPTMA01, MoGenericMethods.derivedState520ItemIds);
		MoGenericMethods.checkXmlInIaTargetQueue(workFlowPTMA01, MoGenericMethods.getICNSourceID(),
				"Check PayLoad-file to be availabile in MO-IATargetQueue DB");
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlowPTMA01);
	}	

	/* Test Case: TC:PTMA01
	 * Description: Core test case to check PTMA01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 13, dependsOnMethods={"testCase_PTMA01_payer"})
	public void testCase_PTMR01_payer() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlowPTMR01 );
		validationStepInformation(testCaseName + " validation for " + workFlowPTMR01);		
		MoGenericMethods.postingSourceID = MoGenericMethods.getSourceIDForRequiredWorkFlow(workFlowPTMA01, MoGenericMethods.derivedState520ItemIds.get(0));
		MoGenericMethods.getCreatedScheduleId(workFlowPTMR01);
		MoGenericMethods.checkScheduleIdStatus(workFlowPTMR01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlowPTMR01, MoGenericMethods.derivedState520ItemIds);
		MoGenericMethods.checkSourceIDOfICNContentOfPTRM01(workFlowPTMR01,MoGenericMethods.postingSourceID);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlowPTMR01);
	}

	/* Test Case: TC:PERM01
	 * Description: Core test case to check PERM01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 14, dependsOnMethods={"testCase_PTMR01_payer"})
	public void testCase_PERM01_payer() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlowPERM01 );
		validationStepInformation(testCaseName + " validation for " + workFlowPERM01);
		String sqlBaseLocation = getSqlsTestDataLocationPath()+ regressionCycle + directoryPathSeperationSymbol + testCaseName.replaceAll("Test", "test") + directoryPathSeperationSymbol + workFlowPERM01 + directoryPathSeperationSymbol;
		MoGenericMethods.updatePERMwithPTMRid(sqlBaseLocation, txtPERM01, MoGenericMethods.postingSourceID);
		Thread.sleep(30000);
		executionStoredProcedureCall(MoGenericMethods.getMoDbServerDetails(), MoGenericMethods.getMoDbNameDetails(), sqlBaseLocation , sqlPERM01);	
		Thread.sleep(3000);
		MoGenericMethods.checkSourceIdInsertInReceiveStagingTable(workFlowPERM01);
		MoGenericMethods.getCreatedScheduleId(workFlowPERM01);
		MoGenericMethods.checkScheduleIdStatus(workFlowPERM01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.checkPostingStateForPERM01(workFlowPERM01, MoGenericMethods.permInputFileXmlContent);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlowPERM01);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlowPERM01);
	}

	/* Test Case: TC:PEMA01
	 * Description: Core test case to check PEMA01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 15, dependsOnMethods={"testCase_PERM01_payer"})
	public void testCase_PEMA01_payer() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlowPEMA01 );
		validationStepInformation(testCaseName + " validation for " + workFlowPEMA01);
		MoGenericMethods.postingSourceID = MoGenericMethods.getSourceIDForRequiredWorkFlow(workFlowPERM01, expectedDebitItemId.get(0));
		MoGenericMethods.getCreatedScheduleId(workFlowPEMA01);
		MoGenericMethods.checkScheduleIdStatus(workFlowPEMA01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.checkPostingStateForPEMA01(MoGenericMethods.permFileEntityStates, workFlowPEMA01);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlowPEMA01);
		MoGenericMethods.checkXmlInIaTargetQueue(workFlowPEMA01, MoGenericMethods.getICNSourceID(),
				"Check PayLoad-file to be availabile in MO-IATargetQueue DB");
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlowPEMA01);
	}

	/* Test Case: TC:PRRM01
	 * Description: Core test case to check PRRM01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 16, dependsOnMethods={"testCase_PEMA01_payer"})
	public void testCase_PRRM01_payer() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlowPRRM01 );
		validationStepInformation(testCaseName + " validation for " + workFlowPRRM01);
		String sqlBaseLocation = getSqlsTestDataLocationPath()+ regressionCycle + directoryPathSeperationSymbol + testCaseName.replaceAll("Test", "test") + directoryPathSeperationSymbol + workFlowPRRM01 + directoryPathSeperationSymbol;
		executionStoredProcedureCall(MoGenericMethods.getMoDbServerDetails(), MoGenericMethods.getMoDbNameDetails(), getSqlsTestDataLocationPath() 
				+ regressionCycle + directoryPathSeperationSymbol + testCaseName.replaceAll("Test", "test") + directoryPathSeperationSymbol + workFlowPRRM01 + directoryPathSeperationSymbol, sqlPRRM01);	
		MoGenericMethods.getPRRMICNContent(sqlBaseLocation, sqlPRRM01);
		MoGenericMethods.checkSourceIdInsertInReceiveStagingTable(workFlowPRRM01 );
		MoGenericMethods.getCreatedScheduleId(workFlowPRRM01);
		MoGenericMethods.checkScheduleIdStatus(workFlowPRRM01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.checkPostingStateForPRRM01(workFlowPRRM01, MoGenericMethods.prrmInputFileXmlContent);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlowPRRM01);
		MoGenericMethods.postingSourceID = MoGenericMethods.getSourceIDForRequiredWorkFlow(workFlowPRRM01, expectedDebitItemId.get(0));
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlowPRRM01);
	}	

	/* Test Case: TC:PRMA01
	 * Description: Core test case to check PRMA01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 17, dependsOnMethods={"testCase_PRRM01_payer"})
	public void testCase_PRMA01_payer() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlowPRMA01 );
		validationStepInformation(testCaseName + " validation for " + workFlowPRMA01);
		MoGenericMethods.getCreatedScheduleId(workFlowPRMA01);
		MoGenericMethods.checkScheduleIdStatus(workFlowPRMA01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.checkPostingStateForPEMA01(MoGenericMethods.prrmFileEntityStates, workFlowPRMA01);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlowPRMA01);
		MoGenericMethods.checkXmlInIaTargetQueue(workFlowPRMA01, MoGenericMethods.getICNSourceID(),
				"Check PayLoad-file to be availabile in MO-IATargetQueue DB");
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlowPRMA01);
	}

	/* Test Case: TC:PRMA01
	 * Description: Core test case to check PRMD01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */

	@Test(priority = 18, dependsOnMethods={"testCase_PRMA01_payer"})
	public void testCase_PRMD01_payer() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlowPRMD01 );
		validationStepInformation(testCaseName + " validation for " + workFlowPRMD01);
		MoGenericMethods.getCreatedScheduleId(workFlowPRMD01);
		MoGenericMethods.checkScheduleIdStatus(workFlowPRMD01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.checkPostingStateForPEMA01(MoGenericMethods.prrmFileEntityStates, workFlowPRMD01);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlowPRMD01);
		MoGenericMethods.checkXmlInDewTargetQueue(workFlowPRMD01, MoGenericMethods.getICNSourceID(),
				"Check PayLoad-file to be availabile in MO-DEWTargetQueue DB");
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlowPRMD01);
	}	

	/*
	 * Test Case: TC:06DM01
	 * Description: Core test case to check 06DM01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 19/*, dependsOnMethods={"testCase_PRMD01_payer"}*/, dependsOnMethods={"testCase_06MD01"})
	public void testCase_06DM01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06DM01 );
		validationStepInformation(testCaseName + " validation for " + workFlow06DM01);
		MoGenericMethods.updatePayerItemToDerivedStates();
		String sqlBaseLocation = getSqlsTestDataLocationPath() 
				+ regressionCycle + directoryPathSeperationSymbol + testCaseName.replaceAll("Test", "test") + directoryPathSeperationSymbol + workFlow06DM01 + directoryPathSeperationSymbol;
		executionStoredProcedureCall(MoGenericMethods.getMoDbServerDetails(), MoGenericMethods.getMoDbNameDetails(), sqlBaseLocation, sql06DM01);	
		MoGenericMethods.getPRRMICNContent(sqlBaseLocation, sql06DM01);
		Msg06DM01.checkICNFile06DM01InsertInReceiveStaging(msg06DM01InputFilePath , msg06DM01InputFileName , msg06DM01OutputFilePath, workFlow06DM01);
		MoGenericMethods.getCreatedScheduleId(workFlow06DM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow06DM01, MoGenericMethods.getScheduleId(), workFlow06MA04 , expectedDebitItemId);
		MoGenericMethods.checkPostingStateFor06DM01(workFlow06DM01, MoGenericMethods.prrmInputFileXmlContent);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlow06DM01);
		//		MoGenericMethods.xsdValidationForRequiredWorkFlow(workFlow06DM01, xsdSqlLocation + workFlow06DM01 + directoryPathSeperationSymbol, Msg06DM01.getReceiveStagingContent());
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow06DM01, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow06DM01, MoGenericMethods.getICNSourceID(), dayTwoResponseWindow.get(0));
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow06DM01);
	}	

	/*
	 * Test Case: TC:06MA04
	 * Description: Core test case to check 06MA04 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 20,dependsOnMethods={"testCase_06DM01"} )
	public void testCase_06MA04() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow06MA04 );
		validationStepInformation(testCaseName + " validation for " + workFlow06MA04);
		MoGenericMethods.getCreatedScheduleId(workFlow06MA04);
		MoGenericMethods.checkScheduleIdStatus(workFlow06MA04, MoGenericMethods.getScheduleId(), workFlow07MS01 , expectedDebitItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow06MA04, expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlow06MA04);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow06MA04, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow06MA04, MoGenericMethods.getICNSourceID(), dayTwoResponseWindow.get(0));
		MoGenericMethods.checkXmlInIaTargetQueue(workFlow06MA04, MoGenericMethods.getICNSourceID(),
				"Check PayLoad-file to be availabile in MO-IATargetQueue DB");
		MoGenericMethods.xsdValidationForRequiredWorkFlow(workFlow06MA04, xsdSqlLocation + workFlow06MA04 + directoryPathSeperationSymbol, MoGenericMethods.getICNSourceContent());
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow06MA04);
	}	

	/*
	 * Test Case: TC:07MS01
	 * Description: Core test case to check 07MS01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 21,dependsOnMethods={"testCase_06MA04"})
	public void testCase_07MS01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow07MS01 );
		validationStepInformation(testCaseName + " validation for " + workFlow07MS01);
		MoGenericMethods.getCreatedScheduleId(workFlow07MS01);
		MoGenericMethods.checkScheduleIdStatus(workFlow07MS01, MoGenericMethods.getScheduleId(), workFlow07MA01 , expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfISOContentWithDebitItem(workFlow07MS01);
		MoGenericMethods.checkSavedFileInEAVLocation(workFlow07MS01, expectedDebitItemId, "Check in the 07MS01 folder of EAV location to ensure  the 07MS01 File generated");
		sourceIDFor07MS01 = MoGenericMethods.getISOSourceID();
		Msg06MD01.validateCodeLineDetails(workFlow07MS01, MoGenericMethods.getISOSourceContent());
		MoGenericMethods.checkSourceStateFor07MS01(sourceIDFor07MS01, workFlow07MS01);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow07MS01, expectedDebitItemId);
		MoGenericMethods.updateSourceStateTo570(sourceIDFor07MS01, workFlow07MS01);
//		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow07MS01, sourceIDFor07MS01, dayOneResponseWindow.get(0));
//		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow07MS01, sourceIDFor07MS01, dayOneResponseWindow.get(0));
	}

	/*
	 * Test Case: TC:07MA01
	 * Description: Core test case to check 07MA01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 22 , dependsOnMethods={"testCase_07MS01"})
	public void testCase_07MA01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow07MA01 );
		validationStepInformation(testCaseName + " validation for " + workFlow07MA01);
		MoGenericMethods.updateWindows2DateToDay1Date(expectedDebitItemId);
		MoGenericMethods.getCreatedScheduleId(workFlow07MA01);
		MoGenericMethods.checkScheduleIdStatus(workFlow07MA01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId );
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow07MA01, expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlow07MA01);
		MoGenericMethods.checkXmlInIaTargetQueue(workFlow07MA01, MoGenericMethods.getICNSourceID(),
				"Check PayLoad-file to be availabile in MO-IATargetQueue DB");
		MoGenericMethods.xsdValidationForRequiredWorkFlow(workFlow07MA01, xsdSqlLocation + workFlow07MA01 + directoryPathSeperationSymbol, MoGenericMethods.getICNSourceContent());
////		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow07MA01, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
////		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow07MA01, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow07MA01);
	}	

	/*
	 * Test Case: TC:11SM01
	 * Description: Core test case to check 11SM01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 23,dependsOnMethods={"testCase_07MA01"})
	public void testCase_11SM01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow11SM01 );
		validationStepInformation(testCaseName + " validation for " + workFlow11SM01);
		fileCopy(msg11SM01InputFilePath + msg11SM01InputFileName, MoGenericMethods.getEAVLocation(workFlow11SM01), msg11SM01InputFileName);
		payerCoreTestCasesLogger.info(msg11SM01InputFileName + " file copied to EAV location "+ MoGenericMethods.getEAVLocation(workFlow11SM01));
		MoGenericMethods.getCreatedScheduleId(workFlow11SM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow11SM01, MoGenericMethods.getScheduleId(), workFlow11MA01 , expectedDebitItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable("11SM01M", expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfISOContentWithDebitItem("MSG11");
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow11SM01, MoGenericMethods.getISOSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow11SM01, MoGenericMethods.getISOSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow11SM01);
	}

	/*
	 * Test Case: TC:11MA01
	 * Description: Core test case to check 11MA01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 24, dependsOnMethods={"testCase_11SM01"})
	public void testCase_11MA01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow11MA01 );
		validationStepInformation(testCaseName + " validation for " + workFlow11MA01);
		MoGenericMethods.getCreatedScheduleId(workFlow11MA01);
		MoGenericMethods.checkScheduleIdStatus(workFlow11MA01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithDebitItem(workFlow11MA01);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow11MA01, expectedDebitItemId);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedDebitItemId, workFlow11MA01, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedDebitItemId, workFlow11MA01, MoGenericMethods.getICNSourceID(), dayOneResponseWindow.get(0));
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow11MA01);
	}

	/*
	 * Test Case: TC:08SM01
	 * Description: Core test case to check 06MD01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 25, dependsOnMethods={"testCase_07MA01"})
	public void testCase_08SM01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow08SM01 );
		validationStepInformation(testCaseName + " validation for " + workFlow08SM01);		
		MoGenericMethods.updatePayerItemStateAsSubmitToCI(expectedDebitItemId.get(0));
		MoGenericMethods.update08SM01with07MA01(msg08SM01InputFilePath, msg08SM01InputFileName, sourceIDFor07MS01);
		Thread.sleep(5000);
		msg08SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow08SM01);
		msg08SM01InputFileName = getTestDataFileName(msg08SM01InputFilePath);
		MoGenericMethods.setMsg08SM01FileName(msg08SM01InputFileName);
		fileCopy(msg08SM01InputFilePath + msg08SM01InputFileName, MoGenericMethods.getEAVLocation(workFlow08SM01), msg08SM01InputFileName);
		payerCoreTestCasesLogger.info(msg08SM01InputFileName + " file copied to EAV location "+ MoGenericMethods.getEAVLocation(workFlow08SM01));
		MoGenericMethods.getCreatedScheduleId(workFlow08SM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow08SM01, MoGenericMethods.getScheduleId(), workFlow08MA01, expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfMsg08SM01(workFlow08SM01,sourceIDFor07MS01);
		MoGenericMethods.dbCheckSourceBatchedFlagFromSourceTable(inputSheetPathNameForMsg06, testCaseName , workFlow08SM01, MoGenericMethods.getISOSourceID());		
		MoGenericMethods.dbCheckSourceDirtyFlagFromSourceTable(inputSheetPathNameForMsg06, testCaseName , workFlow08SM01, MoGenericMethods.getISOSourceID());
		MoGenericMethods.dbCheckSourceStateColumnFromSourceTable(workFlow08SM01, sourceIDFor07MS01);		
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow08SM01);	
	}

	/*
	 * Test Case: TC:08MA01
	 * Description: Core test case to check 06MD01 file loaded with status and flag updated
	 * Expected:  All required status and flags column should be updated in the respective Tables of MO DB
	 * Author: MuluguUm
	 * Date: 13/05/2017 
	 */
	@Test(priority = 26, dependsOnMethods={"testCase_08SM01"})
	public void testCase_08MA01() throws Exception
	{
		scriptStartLog(payerCoreTestCasesLogger, validationHeader + workFlow08MA01 );
		validationStepInformation(testCaseName + " validation for " + workFlow08MA01);
		MoGenericMethods.getCreatedScheduleId(workFlow08MA01);
		MoGenericMethods.checkScheduleIdStatus(workFlow08MA01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.checkSourceIDOfMsg08MA01(workFlow08MA01, sourceIDFor07MS01);
		MoGenericMethods.dbCheckSourceStateColumnFromSourceTable(workFlow08MA01, sourceIDFor07MS01);
		MoGenericMethods.checkXmlInIaTargetQueue(workFlow08MA01, MoGenericMethods.getICNSourceID(),
				"Check PayLoad-file to be availabile in MO-IATargetQueue DB");
		MoGenericMethods.xsdValidationForRequiredWorkFlow(workFlow08MA01, xsdSqlLocation + workFlow08MA01 + directoryPathSeperationSymbol, MoGenericMethods.getICNSourceContent());
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow08MA01);
	}	

	//26---Http codes
	private static void deleteArchievedData() throws Exception
	{
		MoGenericMethods.setMoDetails(getInputSheetOfMoRegression(regressionCycle));
		/*MoGenericMethods.deleteArchiveDataOfJobExecutionlogTable();
		MoGenericMethods.deleteArchiveDataOfIntraDayScheduleTable();
		MoGenericMethods.deleteArchiveDataOfJobTemplateIdTable();
		MoGenericMethods.deleteArchiveDataOfJobScheduleParams();
		MoGenericMethods.deleteArchiveDataOfItemTable(expectedDebitItemId);
		MoGenericMethods.deleteArchiveDataOfItemTable(expectedCreditItemId);
		MoGenericMethods.deleteArchiveDataOfItemStateTable(expectedDebitItemId);
		MoGenericMethods.deleteArchiveDataOfItemStateTable(expectedCreditItemId);
		MoGenericMethods.deleteArchiveDataOfTransactionSet(expectedTsetId);
		MoGenericMethods.deleteArchiveDataOfTransactionSetStateAndReceiveStaging(expectedTsetId);
		MoGenericMethods.deleteArchiveDataOfSourceIDs(expectedDebitItemId);*/
	}
}
