package com.ics.tests.mo;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.ics.mo.common.MoGenericMethods;
import com.ics.mo.xmlFiles.isoContent.Msg01FM01;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

@SuppressWarnings("unused")
public class CollectorTestCases extends ICSAutomationCommonSetup
{
	private static String testCaseName ;	
	private static String inputSheetPathNameForMsg01;
	private static String msg01FM01InputFilePath;
	private static String msg01FM01InputFileName;
	private static String regressionCycle = getRequiredRegressionCycleName(new Object(){});	
	private static final Logger collectorCoreTestCasesLogger = Logger.getLogger(CollectorTestCases.class.getSimpleName());

	CollectorTestCases(String testCaseName, Logger logger)
	{
		CollectorTestCases.testCaseName = testCaseName;
		logger.info(testCaseName + " suite run started");
	}

	@BeforeClass
	public void setupTestDataForCollector() throws Exception
	{
	
	}

	@Test(priority = 1)
	public void test01FM01() throws Exception
	{
		scriptStartLog(collectorCoreTestCasesLogger, validationHeader + workFlow01FM01);	
		validationStepInformation(testCaseName+" validation for "+ workFlow01FM01);	
		executionStoredProcedureCall(MoGenericMethods.getMoDbServerDetails(), MoGenericMethods.getMoDbNameDetails(), getSqlsTestDataLocationPath()
				+regressionCycle+directoryPathSeperationSymbol+testCaseName.replaceAll("Test", "test")+directoryPathSeperationSymbol+workFlow01FM01+directoryPathSeperationSymbol , sql01FM01);	
		Msg01FM01.check01FM01FileInsertInReceiveStaging(workFlow01FM01);
		MoGenericMethods.getCreatedScheduleId(workFlow01FM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow01FM01, MoGenericMethods.getScheduleId(), "" , expectedDebitItemId);
		Msg01FM01.check01FM01ContentInserted(workFlow01FM01);
		Msg01FM01.checkTsetStates(workFlow01FM01);
		Msg01FM01.checkItemIdStates(workFlow01FM01);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow01FM01);
	}

	@Test(priority = 2, dependsOnMethods={"testCase_01FM01"})
	public void test01MA01() throws Exception
	{
		scriptStartLog(collectorCoreTestCasesLogger, validationHeader + workFlow01MA01);	
		validationStepInformation(testCaseName+" validation for "+ workFlow01MA01);	
		MoGenericMethods.getCreatedScheduleId(workFlow01MA01);
		MoGenericMethods.checkScheduleIdStatus(workFlow01MA01, MoGenericMethods.getScheduleId(), "" , expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow01MA01);
	}

	@Test(priority = 3, dependsOnMethods={"testCase_01MA01"})
	public void test01MK01_after_01FM01() throws Exception
	{
		scriptStartLog(collectorCoreTestCasesLogger, validationHeader + workFlow01MK01);	
		validationStepInformation(testCaseName+" validation for "+ workFlow01MK01);	
		MoGenericMethods.getCreatedScheduleId(workFlow01MK01);
		MoGenericMethods.checkScheduleIdStatus(workFlow01MK01, MoGenericMethods.getScheduleId(), "" , expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow01MK01);
	}

	@Test(priority = 4, dependsOnMethods={"test01MK01_after_01FM01"})
	public void test01KM01_after_01FM01() throws Exception
	{
		scriptStartLog(collectorCoreTestCasesLogger, validationHeader + workFlow01KM01);	
		validationStepInformation(testCaseName+" validation for "+ workFlow01KM01);
		String fileName = getXMLFilePathOfRegressionCyle1(inputSheetPathNameForMsg01, testCaseName, workFlow01KM01, MoGenericMethods.keyColumnDataAsXmlFileName) ;
		fileCopy(getInputXmlBasePath(regressionCycle, testCaseName, workFlow01KM01 + directoryPathSeperationSymbol + fileName), MoGenericMethods.getEAVLocation(workFlow01KM01), fileName);
		MoGenericMethods.getCreatedScheduleId(workFlow01KM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow01KM01, MoGenericMethods.getScheduleId(), "" , expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow01KM01);
	}

	@Test(priority = 5, dependsOnMethods={"test01KM01_after_01FM01"})
	public void test01MA03_after_01FM01() throws Exception
	{
		scriptStartLog(collectorCoreTestCasesLogger, validationHeader + workFlow01MA03);	
		validationStepInformation(testCaseName+" validation for "+ workFlow01MA03);	
		MoGenericMethods.getCreatedScheduleId(workFlow01MA03);
		MoGenericMethods.checkScheduleIdStatus(workFlow01MA03, MoGenericMethods.getScheduleId(), "" , expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow01MA03);
	}
	
	@Test(priority = 6, dependsOnMethods={"test01MA03_after_01FM01"})
	public void test01MM01_after_01FM01() throws Exception
	{
		scriptStartLog(collectorCoreTestCasesLogger, validationHeader + workFlow01MM01);	
		validationStepInformation(testCaseName+" validation for "+ workFlow01MM01);	
		MoGenericMethods.getCreatedScheduleId(workFlow01MM01);
		MoGenericMethods.checkScheduleIdStatus(workFlow01MM01, MoGenericMethods.getScheduleId(), "" , expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow01MM01);
	}
	
	@Test(priority = 7, dependsOnMethods={"test01MM01_after_01FM01"})
	public void test01MA05_after_01FM01() throws Exception
	{
		scriptStartLog(collectorCoreTestCasesLogger, validationHeader + workFlow01MA05);	
		validationStepInformation(testCaseName+" validation for "+ workFlow01MA05);	
		MoGenericMethods.getCreatedScheduleId(workFlow01MA05);
		MoGenericMethods.checkScheduleIdStatus(workFlow01MA05, MoGenericMethods.getScheduleId(), "" , expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow01MM01);
	}
	
	@Test(priority = 8, dependsOnMethods={"test01MA05_after_01FM01"})
	public void test01MS01_after_01FM01()throws Exception
	{
		scriptStartLog(collectorCoreTestCasesLogger, validationHeader + workFlow01MS01);	
		validationStepInformation(testCaseName+" validation for "+ workFlow01MS01);	
		MoGenericMethods.getCreatedScheduleId(workFlow01MS01);
		MoGenericMethods.checkScheduleIdStatus(workFlow01MS01, MoGenericMethods.getScheduleId(), "" , expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow01MS01);
	}
	
	@Test(priority = 9, dependsOnMethods={"test01MS01_after_01FM01"})
	public void test01MA06_after_01FM01()throws Exception
	{
		scriptStartLog(collectorCoreTestCasesLogger, validationHeader + workFlow01MA06);	
		validationStepInformation(testCaseName+" validation for "+ workFlow01MA06);	
		MoGenericMethods.getCreatedScheduleId(workFlow01MA06);
		MoGenericMethods.checkScheduleIdStatus(workFlow01MA06, MoGenericMethods.getScheduleId(), "" , expectedDebitItemId);
		MoGenericMethods.workFlowLoadFinalResultStatement(workFlow01MA06);
	}
	
	@Test(priority = 10, dependsOnMethods={"test01MA06_after_01FM01"})
	public void test02SM01_after_01MA06()
	{
		
	}
}
