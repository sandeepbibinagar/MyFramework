package com.ics.tests.rNe;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.rNe.testScenarios.CoreNodeRecordValidationScenarios;
import com.ics.rNe.testScenarios.CoreNodeValidationScenarios;
import com.ics.rNe.testScenarios.CreditRecordValidationScenarios;
import com.ics.rNe.testScenarios.DebitDetailValidationScenarios;
import com.ics.rNe.testScenarios.PostingExtractFileValidationScenarios;
import com.ics.rNe.testScenarios.PostingHeaderValidationsScenarios;
import com.ics.rNe.testScenarios.PostingRecordValidationScenarios;
import com.ics.rNe.testScenarios.PostingResponseHeaderValidationScenarios;
import com.ics.rNe.testScenarios.RecordBodyValidationScenarios;
import com.ics.rNe.testScenarios.ValidationScenarios70656;
import com.ics.rNe.xmlFilesDataFetch.CoreNodeRecordFile;
import com.ics.rNe.xmlFilesDataFetch.CreditRecordFile;
import com.ics.rNe.xmlFilesDataFetch.CteTestFile;
import com.ics.rNe.xmlFilesDataFetch.DebitDetailFile;
import com.ics.rNe.xmlFilesDataFetch.ExtractXML;
import com.ics.rNe.xmlFilesDataFetch.PostingExtractFile;
import com.ics.rNe.xmlFilesDataFetch.PostingExtractFile70656;
import com.ics.rNe.xmlFilesDataFetch.PostingHeaderFile;
import com.ics.rNe.xmlFilesDataFetch.PostingRecordFile;
import com.ics.rNe.xmlFilesDataFetch.RecordBodyFile;
import com.ics.rNe.xmlFilesDataFetch.XSDFile;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

public class CTEExecution extends ICSAutomationCommonSetup{
	
	/*
	 * Test Case ID: CTE Flow Execution
	 * Test Description: E2E RnE Scenario Validation  
	 * Author: Himanshu Malhotra
	 * Date: 5th-June-2017
	 * Comments:
	 */
	@Test(priority = 1)
	public void test_case_dataLoadToDB() throws SAXException, IOException, ParserConfigurationException
	{
		
		try{
		
		//Job Runs
		PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.firstMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.secondMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.thirdMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.fourthMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.fifthMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.sixthMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.seventhMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.eighthMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.ninthMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.tenthMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.eleventhMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.twelvethMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(CteTestFile.thirteenthMessageExecution);
		Thread.sleep(1000);
		PostingExtractFile.compareRowCount1();
		
		}
		
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	}

}
