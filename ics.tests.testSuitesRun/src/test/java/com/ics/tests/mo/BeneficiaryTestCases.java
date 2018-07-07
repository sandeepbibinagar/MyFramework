package com.ics.tests.mo;

import java.util.Date;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ics.mo.common.MoGenericMethods;
import com.ics.mo.xmlFiles.icnContent.Msg06FM_Msg05FM;
import com.ics.mo.xmlFiles.icnContent.Msg06KM_Msg05KM;
import com.ics.mo.xmlFiles.isoContent.Msg05;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

//<copyright  file="BeneficiaryTestCases.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
 * Module Name -  BeneficiaryTestCases.java
 ************************************************************************ 
 * Date -  26/04/2017
 ************************************************************************ 
 * Created By -  MuluguUm
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of Beneficiary Message flows 
 ***********************************************************************/

/******************AMENDMENT HISTORY*********************************** 
 * Modified By - Devisetv   Date - 13/08/2017
 ***********************************************************************
 * Description - Updated method to check the created ScheduleId
 ***********************************************************************/
@SuppressWarnings("unused")
public class BeneficiaryTestCases extends ICSAutomationCommonSetup 
{
	private static String testCaseName ;
	private static String inputSheetPathNameForMsg05;
	private static String msg05SM01InputFilePath;
	private static String msg05SM01InputFileName;
	private static String msg05FM01InputFileName;
	private static String msg05FM01InputFilePath;
	private static String msg05KM01InputFilePath;
	private static String msg05KM01InputFileName;
	private static String msg05SM01OutputFilePath;
	private static String msg05MA01OutputFilePath;
	private static String msg05MF01OutputFilePath;
	private static String msg05FM01OutputFilePath;
	private static String msg05KM01OutputFilePath;
	private static String msg05FM01SourceContent;
	private static String msg13SM01InputFilePath;
	private static String msg13SM01InputFileName;
	private static String msg05 = "Msg05";
	private static String msg05KM01 = "MSF06";
	private static String regressionCycle = getRequiredRegressionCycleName(new Object(){});	
	private static final Logger beneficiaryCoreTestCasesLogger = Logger.getLogger(BeneficiaryTestCases.class.getSimpleName());

	BeneficiaryTestCases(String testCaseName, Logger logger)
	{
		BeneficiaryTestCases.testCaseName = testCaseName;
		logger.info(testCaseName + " suite run started");
	}

	@BeforeClass
	public void setUpTestDataForBeneficiary() throws Exception
	{
		beneficiaryCoreTestCasesLogger.info("Running precondition test-setup script");
		inputSheetPathNameForMsg05 = getInputSheetOfMoRegression(regressionCycle);	

		//Msg05SM01 Test data file
		msg05SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow05SM01);
		msg05SM01InputFileName = getTestDataFileName(msg05SM01InputFilePath);
		beneficiaryCoreTestCasesLogger.info("MSG05 file considered : "+ msg05SM01InputFileName + " from filepath "+ msg05SM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg05, testCaseName, workFlow05SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg05SM01InputFileName);
		Msg05.getInputXmlDataOfSwitchFile(testCaseName , workFlow05SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), regressionCycle);
		MoGenericMethods.setMsg05SM01FileName(msg05SM01InputFileName);

		//Msg05FM01 Test data file
		msg05FM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow05FM01);		
		msg05FM01InputFileName = getTestDataFileName(msg05FM01InputFilePath);
		beneficiaryCoreTestCasesLogger.info("MSG05FM01 file considered "+ msg05FM01InputFileName + " from filepath "+ msg05FM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg05, testCaseName, workFlow05FM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg05FM01InputFileName);			
		MoGenericMethods.getInputXmlDataOfMoToFredFile(testCaseName , workFlow05FM01, MoGenericMethods.getColumnKeyAsXmlFileName(), regressionCycle);

		//Msg05KM01 Test data file
		msg05KM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow05KM01);
		msg05KM01InputFileName = getTestDataFileName(msg05KM01InputFilePath);
		beneficiaryCoreTestCasesLogger.info("MSG05KM01 file considered "+ msg05KM01InputFileName + " from filepath "+ msg05KM01InputFileName);
		setStOutputTestData(inputSheetPathNameForMsg05, testCaseName, workFlow05KM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg05KM01InputFileName);	
		MoGenericMethods.setMsg05KM01FileName(msg05KM01InputFileName);

		//Msg05 Output Files to save Actual data from Source table
		msg05SM01OutputFilePath = getOutputXmlBasePath(regressionCycle, testCaseName, workFlow05SM01);
		msg05MA01OutputFilePath = getOutputXmlBasePath(regressionCycle, testCaseName, workFlow05MA01);
		msg05FM01OutputFilePath = getOutputXmlBasePath(regressionCycle, testCaseName, workFlow05FM01);
		msg05KM01OutputFilePath = getOutputXmlBasePath(regressionCycle, testCaseName, workFlow05KM01);

		deleteArchievedData();
	}

	@Test(priority = 1)
	public void testCase_05SM01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow05SM01);
		validationStepInformation(testCaseName + "validation for " + workFlow05SM01);
		fileCopy(msg05SM01InputFilePath + msg05SM01InputFileName, MoGenericMethods.getEAVLocation(workFlow05SM01), msg05SM01InputFileName);
		beneficiaryCoreTestCasesLogger.info(msg05SM01InputFileName + " file copied to EAV location "+ MoGenericMethods.getEAVLocation(workFlow05SM01));
		MoGenericMethods.getCreatedScheduleId(workFlow05SM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow05SM01, MoGenericMethods.getScheduleId(), workFlow05MA01, expectedCreditItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow05SM01, expectedCreditItemId);
		MoGenericMethods.dbCheckSourceIdFromSourceTable(expectedSourceId.get(0), workFlow05SM01);
		MoGenericMethods.dbCheckTxSetIdInTransactionSetTable(workFlow05SM01);
		MoGenericMethods.dbCheckTxSetIdFromTransactionSetStateTable();
		Msg05.checkISOContentOfSourceTable(msg05SM01InputFilePath , msg05SM01InputFileName , msg05SM01OutputFilePath, msg05);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedCreditItemId, msg05, expectedSourceId.get(0), new GenericMethodUtilis().dateFormat_3.format(new Date()));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedCreditItemId, msg05, expectedSourceId.get(0), null);
		MoGenericMethods.dbCheckActivityIdColumn();
		workFlowLoadFinalResultStatement(workFlow05SM01);	
	}

	@Test(priority = 2, dependsOnMethods={"testCase_05SM01"})
	public void testCase_05MA01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow05MA01 );
		validationStepInformation(testCaseName + " validation for " + workFlow05MA01);
		MoGenericMethods.getCreatedScheduleId(workFlow05MA01);
		MoGenericMethods.checkScheduleIdStatus(workFlow05MA01, MoGenericMethods.getScheduleId(), workFlow05MF01 , expectedCreditItemId );
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow05MA01, expectedCreditItemId);
		MoGenericMethods.checkSourceIDOfISOContentWithCreditItem(workFlow05MA01);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedCreditItemId, workFlow05MA01, MoGenericMethods.getISOSourceID(), new GenericMethodUtilis().dateFormat_3.format(new Date()));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedCreditItemId, workFlow05MA01, MoGenericMethods.getISOSourceID(), null);
		workFlowLoadFinalResultStatement(workFlow05MA01); 
	}

	@Test(priority = 3,  dependsOnMethods={"testCase_05MA01"})
	public void testCase_05MF01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow05MF01);
		validationStepInformation(testCaseName + " validation for "  + workFlow05MF01);
		MoGenericMethods.getCreatedScheduleId(workFlow05MF01);
		MoGenericMethods.checkScheduleIdStatus(workFlow05MF01, MoGenericMethods.getScheduleId(), workFlow05FM01 , expectedCreditItemId );
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow05MF01, expectedCreditItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithCreditItem(workFlow05MF01);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedCreditItemId, workFlow05MF01, MoGenericMethods.getICNSourceID(), new GenericMethodUtilis().dateFormat_3.format(new Date()));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedCreditItemId, workFlow05MF01, MoGenericMethods.getICNSourceID(), null);
		workFlowLoadFinalResultStatement(workFlow05MF01);
	}

	@Test(priority = 4, dependsOnMethods={"testCase_05MF01"})
	public void testCase_05FM01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow05FM01 );
		validationStepInformation(testCaseName + " validation for " + workFlow05FM01);	
		executionStoredProcedureCall(MoGenericMethods.getMoDbServerDetails(), MoGenericMethods.getMoDbNameDetails(), getSqlsTestDataLocationPath()
				+ regressionCycle + directoryPathSeperationSymbol +testCaseName.replaceAll("Test", "test") + directoryPathSeperationSymbol + workFlow05FM01 + directoryPathSeperationSymbol , sql05FM01);	
		Msg06FM_Msg05FM.checkICNFileFM01InsertInReceiveStaging(msg05FM01InputFilePath , msg05FM01InputFileName , msg05FM01OutputFilePath, workFlow05FM01, expectedCreditItemId);
		MoGenericMethods.getCreatedScheduleId(workFlow05FM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow05FM01, MoGenericMethods.getScheduleId(), workFlow05MA02 , expectedCreditItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithCreditItem(workFlow05FM01);
		msg05FM01SourceContent = MoGenericMethods.getICNSourceContent();
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow05FM01, expectedCreditItemId);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedCreditItemId, workFlow05FM01, MoGenericMethods.getICNSourceID(), new GenericMethodUtilis().dateFormat_3.format(new Date()));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedCreditItemId, workFlow05FM01, MoGenericMethods.getICNSourceID(), null);
		workFlowLoadFinalResultStatement(workFlow05FM01);	
	}

	@Test(priority = 5, dependsOnMethods={"testCase_05FM01"})
	public void testCase_05MA02() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow05MA02 );
		validationStepInformation(testCaseName + " validation for " + workFlow05MA02);
		MoGenericMethods.getCreatedScheduleId(workFlow05MA02);
		MoGenericMethods.checkScheduleIdStatus(workFlow05MA02, MoGenericMethods.getScheduleId(), workFlow05MK01 , expectedCreditItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow05MA02, expectedCreditItemId);
		MoGenericMethods.checkSourceIDOfICNContentWithCreditItem(workFlow05MA02);
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedCreditItemId, workFlow05MA02, MoGenericMethods.getICNSourceID(), new GenericMethodUtilis().dateFormat_3.format(new Date()));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedCreditItemId, workFlow05MA02, MoGenericMethods.getICNSourceID(), null);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow05MA02);
	}

	@Test(priority = 6, dependsOnMethods={"testCase_05MA02"})
	public void testCase_05MK01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow05MK01 );
		validationStepInformation(testCaseName +  " validation for " + workFlow05MK01);
		MoGenericMethods.getCreatedScheduleId(workFlow05MK01);
		MoGenericMethods.checkScheduleIdStatus(workFlow05MK01, MoGenericMethods.getScheduleId(), workFlow05KM01 , expectedCreditItemId);
		MoGenericMethods.dbCheckStateColumnFromItemTable(workFlow05MK01, expectedCreditItemId);
		MoGenericMethods.checkSourceIDOfISOContentWithCreditItem(workFlow05MK01);
		MoGenericMethods.checkSavedFileInEAVLocation(workFlow05MK01, expectedCreditItemId, "Check in the 05MK01 folder of EAV location to ensure  the 05MK01 File generated");
		MoGenericMethods.dbCheckWindow1ColumnFromItemStateTable(expectedCreditItemId, workFlow05MK01, MoGenericMethods.getISOSourceID(), new GenericMethodUtilis().dateFormat_3.format(new Date()));
		MoGenericMethods.dbCheckWindow2ColumnFromItemStateTable(expectedCreditItemId, workFlow05MK01, MoGenericMethods.getISOSourceID(), null);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow05MK01);
	}

	@Test(priority = 7, dependsOnMethods={"testCase_05MK01"})
	public void testCase_05KM01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow05KM01 );
		validationStepInformation(testCaseName + " validation for " + workFlow05KM01);
		String fileName = getXMLFilePathOfRegressionCyle1(inputSheetPathNameForMsg05, testCaseName, workFlow05KM01, MoGenericMethods.keyColumnDataAsXmlFileName) ;
		fileCopy(getInputXmlBasePath(regressionCycle, testCaseName, workFlow05KM01 + directoryPathSeperationSymbol + fileName), MoGenericMethods.getEAVLocation(workFlow05KM01), fileName);
		MoGenericMethods.getCreatedScheduleId(workFlow05KM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow05KM01, MoGenericMethods.getScheduleId(), workFlow05MA03 , expectedCreditItemId);
	}

	@Test(priority = 8, dependsOnMethods={"testCase_05KM01"})
	public void testCase_05MA03() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow05MA03);
		validationStepInformation(testCaseName + " validation for " + workFlow05MA03);
		MoGenericMethods.getCreatedScheduleId(workFlow05MA03);
		MoGenericMethods.checkScheduleIdStatus(workFlow05MA03, MoGenericMethods.getScheduleId(), "" , expectedCreditItemId);
	}

	@Test(priority = 9, dependsOnMethods={"testCase_05MA03"})
	public void setUpTestDataForMsg13() throws Exception
	{	
		//Msg13SM01 Test data file
		beneficiaryCoreTestCasesLogger.info("Get Required Test-Data for MSG13");
		msg13SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow13SM01);
		msg13SM01InputFileName = getTestDataFileName(msg13SM01InputFilePath);
		beneficiaryCoreTestCasesLogger.info("MSG13 file considered : "+ msg13SM01InputFileName + " from filepath "+ msg13SM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg05, testCaseName, workFlow13SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg13SM01InputFileName);
		Msg05.getInputXmlDataOfSwitchFile(testCaseName , workFlow13SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), regressionCycle);
		MoGenericMethods.setMsg13SM01FileName(msg13SM01InputFileName);
		MoGenericMethods.updateWindow2BusinessDate(expectedCreditItemId, new GenericMethodUtilis().dateFormat_3.format(new Date()));
		MoGenericMethods.updateWindow1BusinessDate(expectedDebitItemId, new GenericMethodUtilis().dateFormat_3.format(new Date()));
	}

	@Test(priority = 10, dependsOnMethods={"setUpTestDataForMsg13"})
	public void testCase_13SM01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow13SM01);
		validationStepInformation(testCaseName + "validation for " + workFlow13SM01);
		fileCopy(msg13SM01InputFilePath + msg13SM01InputFileName, MoGenericMethods.getEAVLocation(workFlow13SM01), msg13SM01InputFileName);
		beneficiaryCoreTestCasesLogger.info(msg13SM01InputFileName + " file copied to EAV location "+ MoGenericMethods.getEAVLocation(workFlow13SM01));
		MoGenericMethods.getCreatedScheduleId(workFlow13SM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow13SM01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow13SM01);
	}

	@Test(priority = 11, dependsOnMethods={"testCase_13SM01"})
	public void testCase_13MA01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow13MA01);
		validationStepInformation(testCaseName + " validation for " + workFlow13MA01);
		MoGenericMethods.getCreatedScheduleId(workFlow13MA01);
		MoGenericMethods.checkScheduleIdStatus(workFlow13MA01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow13MA01);
	}

	@Test(priority = 12, dependsOnMethods={"testCase_13MA01"})
	public void testCase_13MM01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow13MM01);
		validationStepInformation(testCaseName + " validation for " + workFlow13MM01);
		MoGenericMethods.getCreatedScheduleId(workFlow13MM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow13MM01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow13MM01);
	}

	@Test(priority = 13, dependsOnMethods={"testCase_13MM01"})
	public void testCase_13MA04() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow13MA04);
		validationStepInformation(testCaseName + " validation for " + workFlow13MA04);
		MoGenericMethods.getCreatedScheduleId(workFlow13MA04);
		MoGenericMethods.checkScheduleIdStatus(workFlow13MA04, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow13MA04);
	}

	@Test(priority = 14, dependsOnMethods={"testCase_13MA04"})
	public void testCase_13MD01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow13MD01);
		validationStepInformation(testCaseName + " validation for " + workFlow13MD01);
		MoGenericMethods.getCreatedScheduleId(workFlow13MD01);
		MoGenericMethods.checkScheduleIdStatus(workFlow13MD01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow13MD01);
	}

	@Test(priority = 15, dependsOnMethods={"testCase_13MD01"})
	public void testCase_13DM01() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow13DM01 );
		validationStepInformation(testCaseName + " validation for " + workFlow13DM01);	
		executionStoredProcedureCall(MoGenericMethods.getMoDbServerDetails(), MoGenericMethods.getMoDbNameDetails(), getSqlsTestDataLocationPath()
				+ regressionCycle + directoryPathSeperationSymbol +testCaseName.replaceAll("Test", "test") + directoryPathSeperationSymbol + workFlow13DM01 + directoryPathSeperationSymbol , sql13DM01);	
		MoGenericMethods.getCreatedScheduleId(workFlow13DM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow13DM01, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow13DM01);
	}

	@Test(priority = 16, dependsOnMethods={"testCase_13DM01"})
	public void testCase_13MD02() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow13MD02);
		validationStepInformation(testCaseName + " validation for " + workFlow13MD02);
		MoGenericMethods.getCreatedScheduleId(workFlow13MD02);
		MoGenericMethods.checkScheduleIdStatus(workFlow13MD02, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow13MD02);
	}

	@Test(priority = 17, dependsOnMethods={"testCase_13MD02"})
	public void testCase_13DM02() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow13DM02 );
		validationStepInformation(testCaseName + " validation for " + workFlow13DM02);	
		executionStoredProcedureCall(MoGenericMethods.getMoDbServerDetails(), MoGenericMethods.getMoDbNameDetails(), getSqlsTestDataLocationPath()
				+ regressionCycle + directoryPathSeperationSymbol +testCaseName.replaceAll("Test", "test") + directoryPathSeperationSymbol + workFlow13DM02 + directoryPathSeperationSymbol , sql13DM02);	
		MoGenericMethods.getCreatedScheduleId(workFlow13DM02);
		MoGenericMethods.checkScheduleIdStatus(workFlow13DM02, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow13DM02);
	}	

	@Test(priority = 18, dependsOnMethods={"testCase_13DM02"})
	public void testCase_13MA02() throws Exception
	{
		scriptStartLog(beneficiaryCoreTestCasesLogger, validationHeader + workFlow13MA02);
		validationStepInformation(testCaseName + " validation for " + workFlow13MA02);
		MoGenericMethods.getCreatedScheduleId(workFlow13MA02);
		MoGenericMethods.checkScheduleIdStatus(workFlow13MA02, MoGenericMethods.getScheduleId(), "", expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow13MA02);
	}

	@Test(priority = 19, dependsOnMethods={"testCase_13MA02"})
	public void testMsg13Flow_singleCredit_multiDebit() throws Exception
	{
		//Msg13SM01 Test data file
		int total13SM01 = 2;
		beneficiaryCoreTestCasesLogger.info("Get Required Test-Data for MSG13 for SingleCredit-MultiDebit");
		for(int eachFile = 1 ; eachFile <=total13SM01; eachFile++)
		{
			msg13SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow13SM01+"_"+eachFile);
			msg13SM01InputFileName = getTestDataFileName(msg13SM01InputFilePath);
			beneficiaryCoreTestCasesLogger.info("MSG13 file considered : "+ msg13SM01InputFileName + " from filepath "+ msg13SM01InputFilePath);
			setStOutputTestData(inputSheetPathNameForMsg05, testCaseName, workFlow13SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg13SM01InputFileName);
			Msg05.getInputXmlDataOfSwitchFile(testCaseName , workFlow13SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), regressionCycle);
			MoGenericMethods.setMsg13SM01FileName(msg13SM01InputFileName);
			MoGenericMethods.updateWindow2BusinessDate(expectedCreditItemId, new GenericMethodUtilis().dateFormat_3.format(new Date()));
			MoGenericMethods.updateWindow1BusinessDate(expectedDebitItemId, new GenericMethodUtilis().dateFormat_3.format(new Date()));
			testCase_13SM01();
			testCase_13MA01();
			testCase_13MM01();
			testCase_13MA04();
		}		
	}

	@Test(priority = 20, dependsOnMethods={"testMsg13Flow_singleCredit_multiDebit"})
	public void testMgs13Flow_orphan() throws Exception
	{
		//Msg13SM01 Test data file
		beneficiaryCoreTestCasesLogger.info("Get Required Test-Data for MSG13 for Collector Orphan");
		msg13SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow13SM01+"_"+"collectorOrphan");
		msg13SM01InputFileName = getTestDataFileName(msg13SM01InputFilePath);
		beneficiaryCoreTestCasesLogger.info("MSG13 file considered : "+ msg13SM01InputFileName + " from filepath "+ msg13SM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg05, testCaseName, workFlow13SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg13SM01InputFileName);
		Msg05.getInputXmlDataOfSwitchFile(testCaseName , workFlow13SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), regressionCycle);
		MoGenericMethods.setMsg13SM01FileName(msg13SM01InputFileName);
		MoGenericMethods.updateWindow2BusinessDate(expectedCreditItemId, new GenericMethodUtilis().dateFormat_3.format(new Date()));
		MoGenericMethods.updateWindow1BusinessDate(expectedDebitItemId, new GenericMethodUtilis().dateFormat_3.format(new Date()));
		testCase_13SM01();
		testCase_13MA01();
		testCase_13MD01();
	}

	@Test(priority = 21, dependsOnMethods={"testMgs13Flow_orphan"})
	public void testMsg13Flow_collectedOrphan() throws Exception
	{
		//Msg13SM01 Test data file
		beneficiaryCoreTestCasesLogger.info("Get Required Test-Data for MSG13 for Collected Orphan");
		msg13SM01InputFilePath = getInputXmlBasePath(regressionCycle, testCaseName, workFlow13SM01+"_"+"orphan");
		msg13SM01InputFileName = getTestDataFileName(msg13SM01InputFilePath);
		beneficiaryCoreTestCasesLogger.info("MSG13 file considered : "+ msg13SM01InputFileName + " from filepath "+ msg13SM01InputFilePath);
		setStOutputTestData(inputSheetPathNameForMsg05, testCaseName, workFlow13SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), msg13SM01InputFileName);
		Msg05.getInputXmlDataOfSwitchFile(testCaseName , workFlow13SM01, MoGenericMethods.getColumnKeyAsXmlFileName(), regressionCycle);
		MoGenericMethods.setMsg13SM01FileName(msg13SM01InputFileName);
		MoGenericMethods.updateWindow2BusinessDate(expectedCreditItemId, new GenericMethodUtilis().dateFormat_3.format(new Date()));
		MoGenericMethods.updateWindow1BusinessDate(expectedDebitItemId, new GenericMethodUtilis().dateFormat_3.format(new Date()));
		testCase_13SM01();
		testCase_13MA01();
		testCase_13MD01();
	}
	
	private static void deleteArchievedData() throws Exception
	{
		MoGenericMethods.setMoDetails(getInputSheetOfMoRegression(regressionCycle));
	}
}

