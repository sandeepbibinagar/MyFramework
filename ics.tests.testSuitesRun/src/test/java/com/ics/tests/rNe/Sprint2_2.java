package com.ics.tests.rNe;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.fred.testScenarios.SingleFileInsertionCreditItem;
import com.ics.rNe.testScenarios.CoreNodeValidationScenarios;
import com.ics.rNe.testScenarios.PostingResponseHeaderValidationScenarios;
import com.ics.rNe.xmlFilesDataFetch.CoreNodeFile;
import com.ics.rNe.xmlFilesDataFetch.XSDFile;
import com.ics.seleniumCoreUtilis.Component;

/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class Sprint2_2 {
	/*
	 * Test Case ID: 65813
	 * Test Description: Validate the Response Header Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	
	private static String templateFilePath = ICSPropertiesConfig.getrNePRRM01FilePath();
	private static String dbServerName = ICSPropertiesConfig.getrNeDBServerName();
	private static String rNeDatabaseName = ICSPropertiesConfig.getrNeDBName();
	
	private static String sqlPRRM01TemplateFileName="PRRM01";
	
	 private static String rNePrrm01SqlExecution = "cmd /c sqlcmd -S +dbServerName+ -d +rNeDatabaseName+ -i "+
				templateFilePath + sqlPRRM01TemplateFileName + "_Actual.sql ";
	
	@Test(priority = 1)
	public void test_case_65813() throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> CoreNodeActualList = new HashMap<String, String>();
		CoreNodeActualList = CoreNodeFile.getCoreFileTagwiseDataForCoreNode();
		boolean assertFlagCheck;
		
		assertFlagCheck = CoreNodeValidationScenarios.validateBusinessDate(CoreNodeActualList.get(CoreNodeFile.businessDateTagName));
	    Component.assertTrue(assertFlagCheck, "BusinessDate Tag not as expected");
		
		assertFlagCheck = CoreNodeValidationScenarios.validateExtractId(CoreNodeActualList.get(CoreNodeFile.extractIdTagName));
	    Component.assertTrue(assertFlagCheck, "ExtractId Tag not as expected");
		
		assertFlagCheck = CoreNodeValidationScenarios.validateIntMessageType(CoreNodeActualList.get(CoreNodeFile.intMessageTypeTagName));
	    Component.assertTrue(assertFlagCheck, "IntMessageType Tag not as expected");
	    
	   // assertFlagCheck = CoreNodeValidationScenarios.validateSequence(CoreNodeActualList.get(CoreNodeFile.sequenceTagName));
	  //  Component.assertTrue(assertFlagCheck, "Sequence Tag not as expected");
	    
	    assertFlagCheck = CoreNodeValidationScenarios.validatePostingExtractId(CoreNodeActualList.get(CoreNodeFile.postingExtractIdTagName));
	    Component.assertTrue(assertFlagCheck, "PostingExtractId Tag not as expected");
	    
	    assertFlagCheck = CoreNodeValidationScenarios.validateProcessingParticipantId(CoreNodeActualList.get(CoreNodeFile.processingParticipantIdTagName));
	    Component.assertTrue(assertFlagCheck, "ProcessingParticipantId Tag not as expected");
	    
	    assertFlagCheck = CoreNodeValidationScenarios.validateExtMessageType(CoreNodeActualList.get(CoreNodeFile.extMessageTypeTagName));
	    Component.assertTrue(assertFlagCheck, "ExtMessageType Tag not as expected");
	    
	    assertFlagCheck = CoreNodeValidationScenarios.validateMessageSource(CoreNodeActualList.get(CoreNodeFile.messageSourceTagName));
	    Component.assertTrue(assertFlagCheck, "MessageSource Tag not as expected");    
	    
	    assertFlagCheck = CoreNodeValidationScenarios.validateRecordCounts(CoreNodeActualList.get(CoreNodeFile.recordCountsTagName));
	    Component.assertTrue(assertFlagCheck, "RecordCounts Tag not as expected"); 
	    
	    assertFlagCheck = CoreNodeValidationScenarios.validateMessageDestination(CoreNodeActualList.get(CoreNodeFile.messageDestinationTagName));
	    Component.assertTrue(assertFlagCheck, "MessageDestination Tag not as expected"); 
	    
	   ICSDBUtilis.sqlCommandExecution(rNePrrm01SqlExecution);
	    
	   
	}

}
