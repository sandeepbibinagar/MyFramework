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
import com.ics.rNe.testScenarios.DEBValidationScenarios;
import com.ics.rNe.testScenarios.DebitDetailValidationScenarios;
import com.ics.rNe.testScenarios.PostingExtractFileValidationScenarios;
import com.ics.rNe.testScenarios.PostingHeaderValidationsScenarios;
import com.ics.rNe.testScenarios.PostingRecordValidationScenarios;
import com.ics.rNe.testScenarios.PostingResponseHeaderValidationScenarios;
import com.ics.rNe.testScenarios.RecordBodyValidationScenarios;
import com.ics.rNe.testScenarios.ValidationScenarios70656;
import com.ics.rNe.xmlFilesDataFetch.CoreNodeRecordFile;
import com.ics.rNe.xmlFilesDataFetch.CreditRecordFile;
import com.ics.rNe.xmlFilesDataFetch.DEBFile;
import com.ics.rNe.xmlFilesDataFetch.DIOFile;
import com.ics.rNe.xmlFilesDataFetch.DNPFile;
import com.ics.rNe.xmlFilesDataFetch.DebitDetailFile;
import com.ics.rNe.xmlFilesDataFetch.PERM01File;
import com.ics.rNe.xmlFilesDataFetch.DEBFile;
import com.ics.rNe.xmlFilesDataFetch.PostingExtractFile;
import com.ics.rNe.xmlFilesDataFetch.PostingExtractFile70656;
import com.ics.rNe.xmlFilesDataFetch.PostingHeaderFile;
import com.ics.rNe.xmlFilesDataFetch.PostingRecordFile;
import com.ics.rNe.xmlFilesDataFetch.RecordBodyFile;
import com.ics.rNe.xmlFilesDataFetch.XSDFile;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */

public class DEBExecution extends ICSAutomationCommonSetup{
	
	
	
	/*
	 * Test Case ID: DEB ES 520 Flow
	 * Test Description: E2E RnE Scenario Validation  
	 * Author: Himanshu Malhotra
	 * Date: 12th-MAY-2017
	 * Comments:
	 */
	
	
	@Test(priority = 1)
	public void test_case_DEBES520() throws SAXException, IOException, ParserConfigurationException
	{
		
		try{
		boolean flag;
		/*
		//Job Run
		DEBFile.initialSize = DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.firstMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
	
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.secondMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.thirdMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.fourthMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.fifthMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.sixthMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.seventhMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
		DEBFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		
		DEBFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		DEBFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		
		//PTMR01 Status "C" check
		DEBFile.validatePTMR01Status();
		
		//PERM01 Validations
		DEBFile.getPERM01XML();
		//DEBFile.getEntitySetsFromPERM01();
		
		//Extract FileName Copy to Local and FileNameValidation
		DEBFile.copyExtractFileFromSharedToLocalFolder();
		DEBFile.getAndValidateFileNameFromRootDirectory();		
			*/   
			  //DEBFile check
			    
		        DEBFile.validateRnEMOQueueDetailsTableFileID();
		        DEBFile.getTagwiseDataForPostingExtract();			
		        DEBFile.getRecordsFromExtractXML();
		        ResultSet rs=DEBFile.validatePostingResponseHeaderXMLAttributes();
	            
	            while(rs.next())
	    		{	
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderSchema"), DEBFile.responseHeaderSetTagWiseData.get("Schema"), "SchemaTagName record matches");			
	                publishResults(flag, rs.getString("HeaderSchema"), DEBFile.responseHeaderSetTagWiseData.get("Schema"), "Schema Tag Comparison");
	    			            
	    			flag= Component.verifyEquals(rs.getString("HeaderProcDate"), DEBFile.responseHeaderSetTagWiseData.get("ProcDate"), "ProcDateTagName record matches");			
	    			publishResults(flag, rs.getString("HeaderProcDate"), DEBFile.responseHeaderSetTagWiseData.get("ProcDate"), "ProcDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderParticipant"), DEBFile.responseHeaderSetTagWiseData.get("Participant"), "Participant record matches");
	    			publishResults(flag, rs.getString("HeaderParticipant"), DEBFile.responseHeaderSetTagWiseData.get("Participant"), "Participant Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderSequence"), DEBFile.responseHeaderSetTagWiseData.get("Sequence"), "SequenceTagName record matches");
	    			publishResults(flag, rs.getString("HeaderSequence"), DEBFile.responseHeaderSetTagWiseData.get("Sequence"), " Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderVersion"), DEBFile.responseHeaderSetTagWiseData.get("Version"), "VersionTagName record matches");
	    			publishResults(flag, rs.getString("HeaderVersion"), DEBFile.responseHeaderSetTagWiseData.get("Version"), "Version Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderFileDate"), DEBFile.responseHeaderSetTagWiseData.get("FileDate"), "FileDateTagName record matches");
	    			publishResults(flag, rs.getString("HeaderFileDate"), DEBFile.responseHeaderSetTagWiseData.get("FileDate"), "FileDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderEnvironment"), DEBFile.responseHeaderSetTagWiseData.get("Environment"), "EnvironmentTagName record matches");
	    			publishResults(flag, rs.getString("HeaderEnvironment"), DEBFile.responseHeaderSetTagWiseData.get("Environment"), "Environment Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderWeekday"), DEBFile.responseHeaderSetTagWiseData.get("Weekday"), "SourceTagName record matches");
	    			publishResults(flag, rs.getString("HeaderWeekday"), DEBFile.responseHeaderSetTagWiseData.get("Weekday"), "Weekday Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderCurrency"), DEBFile.responseHeaderSetTagWiseData.get("Currency"), "EnvironmentTagName record matches");
	    			publishResults(flag, rs.getString("HeaderCurrency"), DEBFile.responseHeaderSetTagWiseData.get("Currency"), "Currency Tag Comparison");
	    			
	    			System.out.println("successfull for Header Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), DEBFile.getRecordsFromExtractXML().get(0), "SequenceR record matches");
	    			flag = DEBValidationScenarios.validateSequence(DEBFile.getRecordsFromExtractXML().get(0));
	    			flag = DEBValidationScenarios.validateSequence(DEBFile.getRecordsFromExtractXML().get(1));
	    			publishResults(flag, rs.getString("RecordSequence"), DEBFile.getRecordsFromExtractXML().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), DEBFile.getRecordsFromExtractXML().get(2), "PostType record matches");	
	    			flag = DEBValidationScenarios.validatePostType(DEBFile.getRecordsFromExtractXML().get(2));
	    			flag = DEBValidationScenarios.validatePostType(DEBFile.getRecordsFromExtractXML().get(3));
	    			publishResults(flag, rs.getString("RecordPostType"), DEBFile.getRecordsFromExtractXML().get(2), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), DEBFile.getRecordsFromExtractXML().get(4), "SubType record matches");
	    			flag = DEBValidationScenarios.validateSubType(DEBFile.getRecordsFromExtractXML().get(4));
	    			flag = DEBValidationScenarios.validateSubType(DEBFile.getRecordsFromExtractXML().get(5));
	    			publishResults(flag, rs.getString("RecordSubType"), DEBFile.getRecordsFromExtractXML().get(4), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), DEBFile.getRecordsFromExtractXML().get(6), "SourceMsg record matches");
	    			flag = DEBValidationScenarios.validateSourceMsg(DEBFile.getRecordsFromExtractXML().get(6));
	    			flag = DEBValidationScenarios.validateSourceMsg(DEBFile.getRecordsFromExtractXML().get(7));
	    			publishResults(flag, rs.getString("RecordSourceMsg"), DEBFile.getRecordsFromExtractXML().get(6), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), DEBFile.getRecordsFromExtractXML().get(8), "Channel record matches");
	    			flag = DEBValidationScenarios.validateChannel(DEBFile.getRecordsFromExtractXML().get(8));
	    			flag = DEBValidationScenarios.validateChannel(DEBFile.getRecordsFromExtractXML().get(9));
	    			publishResults(flag, rs.getString("RecordChannel"), DEBFile.getRecordsFromExtractXML().get(8), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), DEBFile.getRecordsFromExtractXML().get(10), "EntryType record matches");
	    			flag = DEBValidationScenarios.validateEntryType(DEBFile.getRecordsFromExtractXML().get(10));
	    			flag = DEBValidationScenarios.validateEntryType(DEBFile.getRecordsFromExtractXML().get(11));
	    			publishResults(flag, rs.getString("RecordEntryType"), DEBFile.getRecordsFromExtractXML().get(10), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), DEBFile.getRecordsFromExtractXML().get(12), "PostSource record matches");	
	    			flag = DEBValidationScenarios.validatePostingSource(DEBFile.getRecordsFromExtractXML().get(12));
	    			flag = DEBValidationScenarios.validatePostingSource(DEBFile.getRecordsFromExtractXML().get(13));
	    			publishResults(flag, rs.getString("RecordPostSource"), DEBFile.getRecordsFromExtractXML().get(12), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), DEBFile.getRecordsFromExtractXML().get(14), "RespIDSource record matches");
	    			flag = DEBValidationScenarios.validateResponseIDSource(DEBFile.getRecordsFromExtractXML().get(14));
	    			flag = DEBValidationScenarios.validateResponseIDSource(DEBFile.getRecordsFromExtractXML().get(15));
	    			publishResults(flag, rs.getString("RecordRespSource"), DEBFile.getRecordsFromExtractXML().get(14), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), DEBFile.getRecordsFromExtractXML().get(16), "PostDay record matches");
	    			flag = DEBValidationScenarios.validatePostDay(DEBFile.getRecordsFromExtractXML().get(16));
	    			flag = DEBValidationScenarios.validatePostDay(DEBFile.getRecordsFromExtractXML().get(17));
	    			publishResults(flag, rs.getString("RecordPostDay"), DEBFile.getRecordsFromExtractXML().get(16), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), DEBFile.getRecordsFromExtractXML().get(18), "ClearDate record matches");
	    			flag = DEBValidationScenarios.validateClearDate(DEBFile.getRecordsFromExtractXML().get(18));
	    			flag = DEBValidationScenarios.validateClearDate(DEBFile.getRecordsFromExtractXML().get(19));
	    			publishResults(flag, rs.getString("RecordClearDate"), DEBFile.getRecordsFromExtractXML().get(18), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), DEBFile.getRecordsFromExtractXML().get(20), "SettDate record matches");
	    			flag = DEBValidationScenarios.validateSettDate(DEBFile.getRecordsFromExtractXML().get(20));
	    			flag = DEBValidationScenarios.validateSettDate(DEBFile.getRecordsFromExtractXML().get(21));
	    			publishResults(flag, rs.getString("RecordSettDate"), DEBFile.getRecordsFromExtractXML().get(20), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), DEBFile.getRecordsFromExtractXML().get(22), "CaptDate record matches");
	    			flag = DEBValidationScenarios.validateCaptDate(DEBFile.getRecordsFromExtractXML().get(22));
	    			flag = DEBValidationScenarios.validateCaptDate(DEBFile.getRecordsFromExtractXML().get(23));
	    			publishResults(flag, rs.getString("RecordCaptDate"), DEBFile.getRecordsFromExtractXML().get(22), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), DEBFile.getRecordsFromExtractXML().get(24), "Amount record matches");
	    			flag = DEBValidationScenarios.validateAmount(DEBFile.getRecordsFromExtractXML().get(24));
	    			flag = DEBValidationScenarios.validateAmount(DEBFile.getRecordsFromExtractXML().get(25));
	    			publishResults(flag, rs.getString("RecordAmount"), DEBFile.getRecordsFromExtractXML().get(24), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), DEBFile.getRecordsFromExtractXML().get(26), "TranSetID record matches");
	    			flag = DEBValidationScenarios.validateTranSetID(DEBFile.getRecordsFromExtractXML().get(26));
	    			flag = DEBValidationScenarios.validateTranSetID(DEBFile.getRecordsFromExtractXML().get(27));
	    			publishResults(flag, rs.getString("RecordTranSetID"), DEBFile.getRecordsFromExtractXML().get(26), "TranSetID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), DEBFile.getRecordsFromExtractXML().get(28), "Override record matches");
	    			flag = DEBValidationScenarios.validateOverride(DEBFile.getRecordsFromExtractXML().get(28));
	    			flag = DEBValidationScenarios.validateOverride(DEBFile.getRecordsFromExtractXML().get(29));
	    			publishResults(flag, rs.getString("RecordOverride"), DEBFile.getRecordsFromExtractXML().get(28), "Override Tag Comparison");
	    			
//	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), DEBFile.getRecordsFromExtractXML().get(30), "CollPart record matches");
//	    			flag = DEBValidationScenarios.validateNPASort(DEBFile.getRecordsFromExtractXML().get(30));
//	    			flag = DEBValidationScenarios.validateNPASort(DEBFile.getRecordsFromExtractXML().get(31));
//	    			publishResults(flag, rs.getString("RecordCollPart"), DEBFile.getRecordsFromExtractXML().get(30), "CollPart Tag Comparison");
//	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), DEBFile.getRecordsFromExtractXML().get(32), "CollLocn record matches");	
	    			flag = DEBValidationScenarios.validateCollLocn(DEBFile.getRecordsFromExtractXML().get(32));
	    			flag = DEBValidationScenarios.validateCollLocn(DEBFile.getRecordsFromExtractXML().get(33));
	    			publishResults(flag, rs.getString("RecordCollLoc"), DEBFile.getRecordsFromExtractXML().get(32), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), DEBFile.getRecordsFromExtractXML().get(34), "DebitTransId record matches");
	    			flag = DEBValidationScenarios.validateDebitTransactionID(DEBFile.getRecordsFromExtractXML().get(34));
	    			flag = DEBValidationScenarios.validateDebitTransactionID(DEBFile.getRecordsFromExtractXML().get(35));
	    			publishResults(flag, rs.getString("DebitTransID"), DEBFile.getRecordsFromExtractXML().get(34), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), DEBFile.getRecordsFromExtractXML().get(38), "SortCode record matches");
	    			flag = DEBValidationScenarios.validateSortCodeDR(DEBFile.getRecordsFromExtractXML().get(38));
	    			flag = DEBValidationScenarios.validateSortCodeDR(DEBFile.getRecordsFromExtractXML().get(39));
	    			publishResults(flag, rs.getString("DebitSortCode"), DEBFile.getRecordsFromExtractXML().get(38), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), DEBFile.getRecordsFromExtractXML().get(42), "AccNum record matches");	
	    			flag = DEBValidationScenarios.validateAccNumDR(DEBFile.getRecordsFromExtractXML().get(42));
	    			flag = DEBValidationScenarios.validateAccNumDR(DEBFile.getRecordsFromExtractXML().get(43));
	    			publishResults(flag, rs.getString("DebitAccNum"), DEBFile.getRecordsFromExtractXML().get(42), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), DEBFile.getRecordsFromExtractXML().get(46), "SerNum record matches");	
	    			flag = DEBValidationScenarios.validateSerNumDR(DEBFile.getRecordsFromExtractXML().get(46));
	    			flag = DEBValidationScenarios.validateSerNumDR(DEBFile.getRecordsFromExtractXML().get(47));
	    			publishResults(flag, rs.getString("DebitSerNum"), DEBFile.getRecordsFromExtractXML().get(46), "SerNum Tag Comparison");

//	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), DEBFile.getRecordsFromExtractXML().get(48), "TranCode record matches");
//	    			flag = DEBValidationScenarios.validateTranCodeDR(DEBFile.getRecordsFromExtractXML().get(48));
//	    			flag = DEBValidationScenarios.validateTranCodeDR(DEBFile.getRecordsFromExtractXML().get(49));
//	    			publishResults(flag, rs.getString("DebitTranCode"), DEBFile.getRecordsFromExtractXML().get(48), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), DEBFile.getRecordsFromExtractXML().get(50), "SwitchSort record matches");	
	    			flag = DEBValidationScenarios.validateSwitchSort(DEBFile.getRecordsFromExtractXML().get(50));
	    			flag = DEBValidationScenarios.validateSwitchSort(DEBFile.getRecordsFromExtractXML().get(51));
	    			publishResults(flag, rs.getString("DebitSwitchSort"), DEBFile.getRecordsFromExtractXML().get(50), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), DEBFile.getRecordsFromExtractXML().get(52), "SwitchAcc record matches");	
	    			flag = DEBValidationScenarios.validateSwitchAcc(DEBFile.getRecordsFromExtractXML().get(52));
	    			flag = DEBValidationScenarios.validateSwitchAcc(DEBFile.getRecordsFromExtractXML().get(53));
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), DEBFile.getRecordsFromExtractXML().get(52), "SwitchAcc Tag Comparison");
	    		
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), DEBFile.getRecordsFromExtractXML().get(36), "TransID record matches");	
	    			flag = DEBValidationScenarios.validateCreditIdCR(DEBFile.getRecordsFromExtractXML().get(36));
	    			flag = DEBValidationScenarios.validateCreditIdCR(DEBFile.getRecordsFromExtractXML().get(37));
	    			publishResults(flag, rs.getString("CreditTransID"), DEBFile.getRecordsFromExtractXML().get(36), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), DEBFile.getRecordsFromExtractXML().get(40), "SortCode record matches");
	    			flag = DEBValidationScenarios.validateSortCodeCR(DEBFile.getRecordsFromExtractXML().get(40));
	    			flag = DEBValidationScenarios.validateSortCodeCR(DEBFile.getRecordsFromExtractXML().get(41));
	    			publishResults(flag, rs.getString("CreditSortCode"), DEBFile.getRecordsFromExtractXML().get(40), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), DEBFile.getRecordsFromExtractXML().get(44), "AccNnum record matches");
	    			flag = DEBValidationScenarios.validateAccNumCR(DEBFile.getRecordsFromExtractXML().get(44));
	    			flag = DEBValidationScenarios.validateAccNumCR(DEBFile.getRecordsFromExtractXML().get(45));
	    			publishResults(flag, rs.getString("CreditAccNum"), DEBFile.getRecordsFromExtractXML().get(44), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), DEBFile.getRecordsFromExtractXML().get(54), "Ref record matches");	
	    			flag = DEBValidationScenarios.validateRefCR(DEBFile.getRecordsFromExtractXML().get(54));
	    			flag = DEBValidationScenarios.validateRefCR(DEBFile.getRecordsFromExtractXML().get(55));
	    			publishResults(flag, rs.getString("CreditRef"), DEBFile.getRecordsFromExtractXML().get(54), "Ref Tag Comparison");
	    				    				    			
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), DEBFile.getRecordsFromExtractXML().get(56), "Beneficiary record matches");
	    			flag = DEBValidationScenarios.validateBeneficiary(DEBFile.getRecordsFromExtractXML().get(56));
	    			flag = DEBValidationScenarios.validateBeneficiary(DEBFile.getRecordsFromExtractXML().get(57));
	    			publishResults(flag, rs.getString("CreditBeneficiary"), DEBFile.getRecordsFromExtractXML().get(56), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    		    			
	    		}	
	            
	            //Get PERM01 Tags
		
	            PERM01File.getPERM01Details();
	            DEBFile.getRecordsFromExtractXML();
	           
	            //Validate PERM01 tags
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(0),PERM01File.getPERM01Details().get(8),"Sequence Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(0), PERM01File.getPERM01Details().get(8), "Sequence Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(2),PERM01File.getPERM01Details().get(10),"Post Type Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(2), PERM01File.getPERM01Details().get(10), "PostType Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(4),PERM01File.getPERM01Details().get(12),"PostSubType Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(4), PERM01File.getPERM01Details().get(12), "PostSubType Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(8),PERM01File.getPERM01Details().get(14),"channel Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(8), PERM01File.getPERM01Details().get(14), "channel Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(12),PERM01File.getPERM01Details().get(18),"PostingSource Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(12), PERM01File.getPERM01Details().get(18), "PostingSource Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(14),PERM01File.getPERM01Details().get(20),"RespIdSource Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(14), PERM01File.getPERM01Details().get(20), "RespIdSource Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(16),PERM01File.getPERM01Details().get(22),"PostingDay Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(16), PERM01File.getPERM01Details().get(22), "PostingDay Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(18),PERM01File.getPERM01Details().get(24),"ClearDate Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(18), PERM01File.getPERM01Details().get(24), "ClearDate Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(20),PERM01File.getPERM01Details().get(26),"SettlementDate Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(20), PERM01File.getPERM01Details().get(26), "SettlementDate Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(24),PERM01File.getPERM01Details().get(28),"PostedAmount Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(24), PERM01File.getPERM01Details().get(28), "PostedAmount Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(28),PERM01File.getPERM01Details().get(30),"Override Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(28), PERM01File.getPERM01Details().get(30), "Override Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(30),PERM01File.getPERM01Details().get(32),"CollPart Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(30), PERM01File.getPERM01Details().get(32), "CollPart Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(32),PERM01File.getPERM01Details().get(38),"Coll Location Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(32), PERM01File.getPERM01Details().get(38), "Coll Location Tag Comparison");
	            
	            //Debit Elements
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(34),PERM01File.getPERM01Details().get(1),"Debit Id Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(34), PERM01File.getPERM01Details().get(1), "Debit Id Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(38),PERM01File.getPERM01Details().get(42),"Debit SortCode Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(38), PERM01File.getPERM01Details().get(42), "Debit SortCode Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(42),PERM01File.getPERM01Details().get(46),"Account Number Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(42), PERM01File.getPERM01Details().get(46), "Account Number Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(46),PERM01File.getPERM01Details().get(50),"Serial Number Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(46), PERM01File.getPERM01Details().get(50), "Serial Number Tag Comparison");
	            
//	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(48),PERM01File.getPERM01Details().get(52),"Tran Code Validation");
//	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(48), PERM01File.getPERM01Details().get(52), "Tran Code Tag Comparison");
//	            
	            //Credit Elements
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(36),PERM01File.getPERM01Details().get(2),"Credit Id Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(36), PERM01File.getPERM01Details().get(2), "Credit Id Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(40),PERM01File.getPERM01Details().get(43),"Sort Code Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(40), PERM01File.getPERM01Details().get(43), "Sort Code Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(44),PERM01File.getPERM01Details().get(47),"Acc Num Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(44), PERM01File.getPERM01Details().get(47), "Acc Num Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(54),PERM01File.getPERM01Details().get(60),"Ref Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(54), PERM01File.getPERM01Details().get(60), "Ref Tag Comparison");
	                 
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML().get(56),PERM01File.getPERM01Details().get(68),"Ref Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML().get(56), PERM01File.getPERM01Details().get(68), "Ref Tag Comparison");
	                
	            
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	/*
	@Test(priority = 1)
	public void test_case_DEBES5201CR5DR() throws SAXException, IOException, ParserConfigurationException
	{
		
		try{
		boolean flag;
		/*
		//Job Run
		DEBFile.initialSize = DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.firstMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
	
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.secondMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.thirdMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
//		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
//		ICSDBUtilis.sqlCommandExecution(DEBFile.fourthMessageExecution);
//		Thread.sleep(3000);
//		DEBFile.compareRowCount1();
		
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.fifthMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.sixthMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
		DEBFile.initialSize =DEBFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DEBFile.seventhMessageExecution);
		Thread.sleep(3000);
		DEBFile.compareRowCount1();
		
		DEBFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		
		DEBFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		DEBFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		
		//PTMR01 Status "C" check
		DEBFile.validatePTMR01Status();
		
		//PERM01 Validations
		DEBFile.getPERM01XML();
		//DEBFile.getEntitySetsFromPERM01();
		
		//Extract FileName Copy to Local and FileNameValidation
		DEBFile.copyExtractFileFromSharedToLocalFolder();
		DEBFile.getAndValidateFileNameFromRootDirectory();		
			 
			  //DEBFile check
			    
		        DEBFile.validateRnEMOQueueDetailsTableFileID();
		        DEBFile.getTagwiseDataForPostingExtract();			
		        DEBFile.getRecordsFromExtractXML1TR5DR1CR();
		        ResultSet rs=DEBFile.validatePostingResponseHeaderXMLAttributes();
	            
	            while(rs.next())
	    		{	
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderSchema"), DEBFile.responseHeaderSetTagWiseData.get("Schema"), "SchemaTagName record matches");			
	                publishResults(flag, rs.getString("HeaderSchema"), DEBFile.responseHeaderSetTagWiseData.get("Schema"), "Schema Tag Comparison");
	    			            
	    			flag= Component.verifyEquals(rs.getString("HeaderProcDate"), DEBFile.responseHeaderSetTagWiseData.get("ProcDate"), "ProcDateTagName record matches");			
	    			publishResults(flag, rs.getString("HeaderProcDate"), DEBFile.responseHeaderSetTagWiseData.get("ProcDate"), "ProcDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderParticipant"), DEBFile.responseHeaderSetTagWiseData.get("Participant"), "Participant record matches");
	    			publishResults(flag, rs.getString("HeaderParticipant"), DEBFile.responseHeaderSetTagWiseData.get("Participant"), "Participant Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderSequence"), DEBFile.responseHeaderSetTagWiseData.get("Sequence"), "SequenceTagName record matches");
	    			publishResults(flag, rs.getString("HeaderSequence"), DEBFile.responseHeaderSetTagWiseData.get("Sequence"), " Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderVersion"), DEBFile.responseHeaderSetTagWiseData.get("Version"), "VersionTagName record matches");
	    			publishResults(flag, rs.getString("HeaderVersion"), DEBFile.responseHeaderSetTagWiseData.get("Version"), "Version Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderFileDate"), DEBFile.responseHeaderSetTagWiseData.get("FileDate"), "FileDateTagName record matches");
	    			publishResults(flag, rs.getString("HeaderFileDate"), DEBFile.responseHeaderSetTagWiseData.get("FileDate"), "FileDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderEnvironment"), DEBFile.responseHeaderSetTagWiseData.get("Environment"), "EnvironmentTagName record matches");
	    			publishResults(flag, rs.getString("HeaderEnvironment"), DEBFile.responseHeaderSetTagWiseData.get("Environment"), "Environment Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderWeekday"), DEBFile.responseHeaderSetTagWiseData.get("Weekday"), "SourceTagName record matches");
	    			publishResults(flag, rs.getString("HeaderWeekday"), DEBFile.responseHeaderSetTagWiseData.get("Weekday"), "Weekday Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderCurrency"), DEBFile.responseHeaderSetTagWiseData.get("Currency"), "EnvironmentTagName record matches");
	    			publishResults(flag, rs.getString("HeaderCurrency"), DEBFile.responseHeaderSetTagWiseData.get("Currency"), "Currency Tag Comparison");
	    			
	    			System.out.println("successfull for Header Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(0), "SequenceR record matches");
	    			flag = DEBValidationScenarios.validateSequence(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(0));
	    			flag = DEBValidationScenarios.validateSequence(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(1));
	    			publishResults(flag, rs.getString("RecordSequence"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(2), "PostType record matches");	
	    			flag = DEBValidationScenarios.validatePostType(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(2));	    			
	    			publishResults(flag, rs.getString("RecordPostType"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(2), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(3), "SubType record matches");
	    			flag = DEBValidationScenarios.validateSubType(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(3));	    		
	    			publishResults(flag, rs.getString("RecordSubType"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(3), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(4), "SourceMsg record matches");
	    			flag = DEBValidationScenarios.validateSourceMsg(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(4));	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(4), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(5), "Channel record matches");
	    			flag = DEBValidationScenarios.validateChannel(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(5));	    			
	    			publishResults(flag, rs.getString("RecordChannel"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(5), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(6), "EntryType record matches");
	    			flag = DEBValidationScenarios.validateEntryType(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(6));	    		
	    			publishResults(flag, rs.getString("RecordEntryType"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(6), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(7), "PostSource record matches");	
	    			flag = DEBValidationScenarios.validatePostingSource(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(7));	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(7), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(8), "RespIDSource record matches");
	    			flag = DEBValidationScenarios.validateResponseIDSource(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(8));
	    			flag = DEBValidationScenarios.validateResponseIDSource(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(15));
	    			publishResults(flag, rs.getString("RecordRespSource"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(8), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(9), "PostDay record matches");
	    			flag = DEBValidationScenarios.validatePostDay(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(9));
	    			flag = DEBValidationScenarios.validatePostDay(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(17));
	    			publishResults(flag, rs.getString("RecordPostDay"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(9), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(10), "ClearDate record matches");
	    			flag = DEBValidationScenarios.validateClearDate(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(10));
	    			flag = DEBValidationScenarios.validateClearDate(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(19));
	    			publishResults(flag, rs.getString("RecordClearDate"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(10), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(11), "SettDate record matches");
	    			flag = DEBValidationScenarios.validateSettDate(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(11));
	    			flag = DEBValidationScenarios.validateSettDate(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(21));
	    			publishResults(flag, rs.getString("RecordSettDate"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(11), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(12), "CaptDate record matches");
	    			flag = DEBValidationScenarios.validateCaptDate(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(12));
	    			flag = DEBValidationScenarios.validateCaptDate(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(23));
	    			publishResults(flag, rs.getString("RecordCaptDate"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(12), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(13), "Amount record matches");
	    			flag = DEBValidationScenarios.validateAmount(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(13));
	    			flag = DEBValidationScenarios.validateAmount(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(25));
	    			publishResults(flag, rs.getString("RecordAmount"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(13), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(14), "TranSetID record matches");
	    			flag = DEBValidationScenarios.validateTranSetID(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(14));
	    			flag = DEBValidationScenarios.validateTranSetID(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(27));
	    			publishResults(flag, rs.getString("RecordTranSetID"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(14), "TranSetID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(15), "Override record matches");
	    			flag = DEBValidationScenarios.validateOverride(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(15));
	    			flag = DEBValidationScenarios.validateOverride(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(29));
	    			publishResults(flag, rs.getString("RecordOverride"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(15), "Override Tag Comparison");
	    			
//	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(30), "CollPart record matches");
//	    			flag = DEBValidationScenarios.validateNPASort(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(30));
//	    			flag = DEBValidationScenarios.validateNPASort(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(31));
//	    			publishResults(flag, rs.getString("RecordCollPart"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(30), "CollPart Tag Comparison");
//	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(17), "CollLocn record matches");	
	    			flag = DEBValidationScenarios.validateCollLocn(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(17));
	    			flag = DEBValidationScenarios.validateCollLocn(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(33));
	    			publishResults(flag, rs.getString("RecordCollLoc"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(17), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(18), "DebitTransId record matches");
	    			flag = DEBValidationScenarios.validateDebitTransactionID(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(18));
	    			flag = DEBValidationScenarios.validateDebitTransactionID(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(35));
	    			publishResults(flag, rs.getString("DebitTransID"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(18), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(24), "SortCode record matches");
	    			flag = DEBValidationScenarios.validateSortCodeDR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(24));
	    			flag = DEBValidationScenarios.validateSortCodeDR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(39));
	    			publishResults(flag, rs.getString("DebitSortCode"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(24), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(30), "AccNum record matches");	
	    			flag = DEBValidationScenarios.validateAccNumDR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(30));
	    			flag = DEBValidationScenarios.validateAccNumDR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(43));
	    			publishResults(flag, rs.getString("DebitAccNum"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(30), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(36), "SerNum record matches");	
	    			flag = DEBValidationScenarios.validateSerNumDR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(36));
	    			flag = DEBValidationScenarios.validateSerNumDR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(47));
	    			publishResults(flag, rs.getString("DebitSerNum"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(36), "SerNum Tag Comparison");

//	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(48), "TranCode record matches");
//	    			flag = DEBValidationScenarios.validateTranCodeDR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(48));
//	    			flag = DEBValidationScenarios.validateTranCodeDR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(49));
//	    			publishResults(flag, rs.getString("DebitTranCode"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(48), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(43), "SwitchSort record matches");	
	    			flag = DEBValidationScenarios.validateSwitchSort(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(43));
	    			flag = DEBValidationScenarios.validateSwitchSort(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(51));
	    			publishResults(flag, rs.getString("DebitSwitchSort"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(43), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(48), "SwitchAcc record matches");	
	    			flag = DEBValidationScenarios.validateSwitchAcc(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(48));
	    			flag = DEBValidationScenarios.validateSwitchAcc(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(53));
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(48), "SwitchAcc Tag Comparison");
	    		
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(19), "TransID record matches");	
	    			flag = DEBValidationScenarios.validateCreditIdCR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(19));
	    			flag = DEBValidationScenarios.validateCreditIdCR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(37));
	    			publishResults(flag, rs.getString("CreditTransID"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(19), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(25), "SortCode record matches");
	    			flag = DEBValidationScenarios.validateSortCodeCR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(25));
	    			flag = DEBValidationScenarios.validateSortCodeCR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(41));
	    			publishResults(flag, rs.getString("CreditSortCode"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(25), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(31), "AccNnum record matches");
	    			flag = DEBValidationScenarios.validateAccNumCR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(31));
	    			flag = DEBValidationScenarios.validateAccNumCR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(45));
	    			publishResults(flag, rs.getString("CreditAccNum"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(31), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(53), "Ref record matches");	
	    			flag = DEBValidationScenarios.validateRefCR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(53));
	    			flag = DEBValidationScenarios.validateRefCR(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(55));
	    			publishResults(flag, rs.getString("CreditRef"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(53), "Ref Tag Comparison");
	    				    				    			
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(54), "Beneficiary record matches");
	    			flag = DEBValidationScenarios.validateBeneficiary(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(54));
	    			flag = DEBValidationScenarios.validateBeneficiary(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(57));
	    			publishResults(flag, rs.getString("CreditBeneficiary"), DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(54), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    		    			
	    		}	
	            
	            //Get PERM01 Tags
		
	            PERM01File.getPERM01Details();
	            DEBFile.getRecordsFromExtractXML1TR5DR1CR();
	           
	            //Validate PERM01 tags
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(1),PERM01File.getPERM01Details().get(8),"Sequence Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(1), PERM01File.getPERM01Details().get(8), "Sequence Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(2),PERM01File.getPERM01Details().get(10),"Post Type Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(2), PERM01File.getPERM01Details().get(10), "PostType Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(3),PERM01File.getPERM01Details().get(12),"PostSubType Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(3), PERM01File.getPERM01Details().get(12), "PostSubType Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(5),PERM01File.getPERM01Details().get(14),"channel Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(5), PERM01File.getPERM01Details().get(14), "channel Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(7),PERM01File.getPERM01Details().get(18),"PostingSource Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(7), PERM01File.getPERM01Details().get(18), "PostingSource Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(8),PERM01File.getPERM01Details().get(20),"RespIdSource Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(8), PERM01File.getPERM01Details().get(20), "RespIdSource Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(9),PERM01File.getPERM01Details().get(22),"PostingDay Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(9), PERM01File.getPERM01Details().get(22), "PostingDay Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(10),PERM01File.getPERM01Details().get(24),"ClearDate Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(10), PERM01File.getPERM01Details().get(24), "ClearDate Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(11),PERM01File.getPERM01Details().get(26),"SettlementDate Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(11), PERM01File.getPERM01Details().get(26), "SettlementDate Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(13),PERM01File.getPERM01Details().get(28),"PostedAmount Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(13), PERM01File.getPERM01Details().get(28), "PostedAmount Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(15),PERM01File.getPERM01Details().get(30),"Override Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(15), PERM01File.getPERM01Details().get(30), "Override Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(16),PERM01File.getPERM01Details().get(32),"CollPart Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(16), PERM01File.getPERM01Details().get(32), "CollPart Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(17),PERM01File.getPERM01Details().get(38),"Coll Location Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(17), PERM01File.getPERM01Details().get(38), "Coll Location Tag Comparison");
	            
	            //Debit Elements
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(18),PERM01File.getPERM01Details().get(1),"Debit Id Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(18), PERM01File.getPERM01Details().get(1), "Debit Id Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(24),PERM01File.getPERM01Details().get(42),"Debit SortCode Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(24), PERM01File.getPERM01Details().get(42), "Debit SortCode Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(30),PERM01File.getPERM01Details().get(46),"Account Number Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(30), PERM01File.getPERM01Details().get(46), "Account Number Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(36),PERM01File.getPERM01Details().get(50),"Serial Number Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(36), PERM01File.getPERM01Details().get(50), "Serial Number Tag Comparison");
	            
//	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(48),PERM01File.getPERM01Details().get(52),"Tran Code Validation");
//	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(48), PERM01File.getPERM01Details().get(52), "Tran Code Tag Comparison");
//	            
	            //Credit Elements
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(19),PERM01File.getPERM01Details().get(2),"Credit Id Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(19), PERM01File.getPERM01Details().get(2), "Credit Id Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(25),PERM01File.getPERM01Details().get(43),"Sort Code Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(25), PERM01File.getPERM01Details().get(43), "Sort Code Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(31),PERM01File.getPERM01Details().get(47),"Acc Num Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(31), PERM01File.getPERM01Details().get(47), "Acc Num Tag Comparison");
	            
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(53),PERM01File.getPERM01Details().get(60),"Ref Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(53), PERM01File.getPERM01Details().get(60), "Ref Tag Comparison");
	                 
	            flag=Component.verifyEquals(DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(54),PERM01File.getPERM01Details().get(68),"Beneficiary Validation");
	            publishResults(flag, DEBFile.getRecordsFromExtractXML1TR5DR1CR().get(54), PERM01File.getPERM01Details().get(68), "Beneficiary Tag Comparison");
	                
	            
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	/*
	
	
	@Test(priority = 2)
	public void test_case_DIO() throws SAXException, IOException, ParserConfigurationException
	{
		
		try{
		boolean flag;
		
		//Job Run
		DIOFile.initialSize = DIOFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DIOFile.firstMessageExecution);
		Thread.sleep(3000);
		DIOFile.compareRowCount1();
	
		DIOFile.initialSize =DIOFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DIOFile.secondMessageExecution);
		Thread.sleep(3000);
		DIOFile.compareRowCount1();
		
		DIOFile.initialSize =DIOFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DIOFile.thirdMessageExecution);
		Thread.sleep(3000);
		DIOFile.compareRowCount1();
		
//		DIOFile.initialSize =DIOFile.getRowCountFromBaseCoreTable1();
//		ICSDBUtilis.sqlCommandExecution(DIOFile.fourthMessageExecution);
//		Thread.sleep(3000);
//		DIOFile.compareRowCount1();
		
		DIOFile.initialSize =DIOFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DIOFile.fifthMessageExecution);
		Thread.sleep(3000);
		DIOFile.compareRowCount1();
		
		DIOFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		
		DIOFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		DIOFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		
		//PTMR01 Status "C" check
		DIOFile.validatePTMR01Status();
		
		//PERM01 Validations
		DIOFile.getPERM01XML();
		//DIOFile.getEntitySetsFromPERM01();
		
		//Extract FileName Copy to Local and FileNameValidation
		DIOFile.copyExtractFileFromSharedToLocalFolder();
		DIOFile.getAndValidateFileNameFromRootDirectory();
			    
			  //DIOFile check
			    
		        DIOFile.validateRnEMOQueueDetailsTableFileID();
		        DIOFile.getTagwiseDataForPostingExtract();			
		        DIOFile.getRecordsFromExtractXML();
		        ResultSet rs=DIOFile.validatePostingResponseHeaderXMLAttributes();
	            
	            while(rs.next())
	    		{	
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderSchema"), DIOFile.responseHeaderSetTagWiseData.get("Schema"), "SchemaTagName record matches");			
	                publishResults(flag, rs.getString("HeaderSchema"), DIOFile.responseHeaderSetTagWiseData.get("Schema"), "Schema Tag Comparison");
	    			            
	    			flag= Component.verifyEquals(rs.getString("HeaderProcDate"), DIOFile.responseHeaderSetTagWiseData.get("ProcDate"), "ProcDateTagName record matches");			
	    			publishResults(flag, rs.getString("HeaderProcDate"), DIOFile.responseHeaderSetTagWiseData.get("ProcDate"), "ProcDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderParticipant"), DIOFile.responseHeaderSetTagWiseData.get("Participant"), "Participant record matches");
	    			publishResults(flag, rs.getString("HeaderParticipant"), DIOFile.responseHeaderSetTagWiseData.get("Participant"), "Participant Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderSequence"), DIOFile.responseHeaderSetTagWiseData.get("Sequence"), "SequenceTagName record matches");
	    			publishResults(flag, rs.getString("HeaderSequence"), DIOFile.responseHeaderSetTagWiseData.get("Sequence"), " Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderVersion"), DIOFile.responseHeaderSetTagWiseData.get("Version"), "VersionTagName record matches");
	    			publishResults(flag, rs.getString("HeaderVersion"), DIOFile.responseHeaderSetTagWiseData.get("Version"), "Version Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderFileDate"), DIOFile.responseHeaderSetTagWiseData.get("FileDate"), "FileDateTagName record matches");
	    			publishResults(flag, rs.getString("HeaderFileDate"), DIOFile.responseHeaderSetTagWiseData.get("FileDate"), "FileDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderEnvironment"), DIOFile.responseHeaderSetTagWiseData.get("Environment"), "EnvironmentTagName record matches");
	    			publishResults(flag, rs.getString("HeaderEnvironment"), DIOFile.responseHeaderSetTagWiseData.get("Environment"), "Environment Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderWeekday"), DIOFile.responseHeaderSetTagWiseData.get("Weekday"), "SourceTagName record matches");
	    			publishResults(flag, rs.getString("HeaderWeekday"), DIOFile.responseHeaderSetTagWiseData.get("Weekday"), "Weekday Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderCurrency"), DIOFile.responseHeaderSetTagWiseData.get("Currency"), "EnvironmentTagName record matches");
	    			publishResults(flag, rs.getString("HeaderCurrency"), DIOFile.responseHeaderSetTagWiseData.get("Currency"), "Currency Tag Comparison");
	    			
	    			System.out.println("successfull for Header Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), DIOFile.getRecordsFromExtractXML().get(0), "SequenceR record matches");
	    			flag = DEBValidationScenarios.validateSequence(DIOFile.getRecordsFromExtractXML().get(0));
	    			flag = DEBValidationScenarios.validateSequence(DIOFile.getRecordsFromExtractXML().get(1));
	    			publishResults(flag, rs.getString("RecordSequence"), DIOFile.getRecordsFromExtractXML().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), DIOFile.getRecordsFromExtractXML().get(2), "PostType record matches");	
	    			flag = DEBValidationScenarios.validatePostType(DIOFile.getRecordsFromExtractXML().get(2));
	    			flag = DEBValidationScenarios.validatePostType(DIOFile.getRecordsFromExtractXML().get(3));
	    			publishResults(flag, rs.getString("RecordPostType"), DIOFile.getRecordsFromExtractXML().get(2), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), DIOFile.getRecordsFromExtractXML().get(4), "SubType record matches");
	    			flag = DEBValidationScenarios.validateSubType(DIOFile.getRecordsFromExtractXML().get(4));
	    			flag = DEBValidationScenarios.validateSubType(DIOFile.getRecordsFromExtractXML().get(5));
	    			publishResults(flag, rs.getString("RecordSubType"), DIOFile.getRecordsFromExtractXML().get(4), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), DIOFile.getRecordsFromExtractXML().get(6), "SourceMsg record matches");
	    			flag = DEBValidationScenarios.validateSourceMsg(DIOFile.getRecordsFromExtractXML().get(6));
	    			flag = DEBValidationScenarios.validateSourceMsg(DIOFile.getRecordsFromExtractXML().get(7));
	    			publishResults(flag, rs.getString("RecordSourceMsg"), DIOFile.getRecordsFromExtractXML().get(6), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), DIOFile.getRecordsFromExtractXML().get(8), "Channel record matches");
	    			flag = DEBValidationScenarios.validateChannel(DIOFile.getRecordsFromExtractXML().get(8));
	    			flag = DEBValidationScenarios.validateChannel(DIOFile.getRecordsFromExtractXML().get(9));
	    			publishResults(flag, rs.getString("RecordChannel"), DIOFile.getRecordsFromExtractXML().get(8), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), DIOFile.getRecordsFromExtractXML().get(10), "EntryType record matches");
	    			flag = DEBValidationScenarios.validateEntryType(DIOFile.getRecordsFromExtractXML().get(10));
	    			flag = DEBValidationScenarios.validateEntryType(DIOFile.getRecordsFromExtractXML().get(11));
	    			publishResults(flag, rs.getString("RecordEntryType"), DIOFile.getRecordsFromExtractXML().get(10), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), DIOFile.getRecordsFromExtractXML().get(12), "PostSource record matches");	
	    			flag = DEBValidationScenarios.validatePostingSource(DIOFile.getRecordsFromExtractXML().get(12));
	    			flag = DEBValidationScenarios.validatePostingSource(DIOFile.getRecordsFromExtractXML().get(13));
	    			publishResults(flag, rs.getString("RecordPostSource"), DIOFile.getRecordsFromExtractXML().get(12), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), DIOFile.getRecordsFromExtractXML().get(14), "RespIDSource record matches");
	    			flag = DEBValidationScenarios.validateResponseIDSource(DIOFile.getRecordsFromExtractXML().get(14));
	    			flag = DEBValidationScenarios.validateResponseIDSource(DIOFile.getRecordsFromExtractXML().get(15));
	    			publishResults(flag, rs.getString("RecordRespSource"), DIOFile.getRecordsFromExtractXML().get(14), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), DIOFile.getRecordsFromExtractXML().get(16), "PostDay record matches");
	    			flag = DEBValidationScenarios.validatePostDay(DIOFile.getRecordsFromExtractXML().get(16));
	    			flag = DEBValidationScenarios.validatePostDay(DIOFile.getRecordsFromExtractXML().get(17));
	    			publishResults(flag, rs.getString("RecordPostDay"), DIOFile.getRecordsFromExtractXML().get(16), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), DIOFile.getRecordsFromExtractXML().get(18), "ClearDate record matches");
	    			flag = DEBValidationScenarios.validateClearDate(DIOFile.getRecordsFromExtractXML().get(18));
	    			flag = DEBValidationScenarios.validateClearDate(DIOFile.getRecordsFromExtractXML().get(19));
	    			publishResults(flag, rs.getString("RecordClearDate"), DIOFile.getRecordsFromExtractXML().get(18), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), DIOFile.getRecordsFromExtractXML().get(20), "SettDate record matches");
	    			flag = DEBValidationScenarios.validateSettDate(DIOFile.getRecordsFromExtractXML().get(20));
	    			flag = DEBValidationScenarios.validateSettDate(DIOFile.getRecordsFromExtractXML().get(21));
	    			publishResults(flag, rs.getString("RecordSettDate"), DIOFile.getRecordsFromExtractXML().get(20), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), DIOFile.getRecordsFromExtractXML().get(22), "CaptDate record matches");
	    			flag = DEBValidationScenarios.validateCaptDate(DIOFile.getRecordsFromExtractXML().get(22));
	    			flag = DEBValidationScenarios.validateCaptDate(DIOFile.getRecordsFromExtractXML().get(23));
	    			publishResults(flag, rs.getString("RecordCaptDate"), DIOFile.getRecordsFromExtractXML().get(22), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), DIOFile.getRecordsFromExtractXML().get(24), "Amount record matches");
	    			flag = DEBValidationScenarios.validateAmount(DIOFile.getRecordsFromExtractXML().get(24));
	    			flag = DEBValidationScenarios.validateAmount(DIOFile.getRecordsFromExtractXML().get(25));
	    			publishResults(flag, rs.getString("RecordAmount"), DIOFile.getRecordsFromExtractXML().get(24), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), DIOFile.getRecordsFromExtractXML().get(26), "TranSetID record matches");
	    			flag = DEBValidationScenarios.validateTranSetID(DIOFile.getRecordsFromExtractXML().get(26));
	    			flag = DEBValidationScenarios.validateTranSetID(DIOFile.getRecordsFromExtractXML().get(27));
	    			publishResults(flag, rs.getString("RecordTranSetID"), DIOFile.getRecordsFromExtractXML().get(26), "TranSetID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), DIOFile.getRecordsFromExtractXML().get(28), "Override record matches");
	    			flag = DEBValidationScenarios.validateOverride(DIOFile.getRecordsFromExtractXML().get(28));
	    			flag = DEBValidationScenarios.validateOverride(DIOFile.getRecordsFromExtractXML().get(29));
	    			publishResults(flag, rs.getString("RecordOverride"), DIOFile.getRecordsFromExtractXML().get(28), "Override Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), DIOFile.getRecordsFromExtractXML().get(30), "CollPart record matches");
	    			flag = DEBValidationScenarios.validateNPASort(DIOFile.getRecordsFromExtractXML().get(30));
	    			flag = DEBValidationScenarios.validateNPASort(DIOFile.getRecordsFromExtractXML().get(31));
	    			publishResults(flag, rs.getString("RecordCollPart"), DIOFile.getRecordsFromExtractXML().get(30), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), DIOFile.getRecordsFromExtractXML().get(32), "CollLocn record matches");	
	    			flag = DEBValidationScenarios.validateCollLocn(DIOFile.getRecordsFromExtractXML().get(32));
	    			flag = DEBValidationScenarios.validateCollLocn(DIOFile.getRecordsFromExtractXML().get(33));
	    			publishResults(flag, rs.getString("RecordCollLoc"), DIOFile.getRecordsFromExtractXML().get(32), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), DIOFile.getRecordsFromExtractXML().get(34), "DebitTransId record matches");
	    			flag = DEBValidationScenarios.validateDebitTransactionID(DIOFile.getRecordsFromExtractXML().get(34));
	    			flag = DEBValidationScenarios.validateDebitTransactionID(DIOFile.getRecordsFromExtractXML().get(35));
	    			publishResults(flag, rs.getString("DebitTransID"), DIOFile.getRecordsFromExtractXML().get(34), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), DIOFile.getRecordsFromExtractXML().get(38), "SortCode record matches");
	    			flag = DEBValidationScenarios.validateSortCodeDR(DIOFile.getRecordsFromExtractXML().get(38));
	    			flag = DEBValidationScenarios.validateSortCodeDR(DIOFile.getRecordsFromExtractXML().get(39));
	    			publishResults(flag, rs.getString("DebitSortCode"), DIOFile.getRecordsFromExtractXML().get(38), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), DIOFile.getRecordsFromExtractXML().get(42), "AccNum record matches");	
	    			flag = DEBValidationScenarios.validateAccNumDR(DIOFile.getRecordsFromExtractXML().get(42));
	    			flag = DEBValidationScenarios.validateAccNumDR(DIOFile.getRecordsFromExtractXML().get(43));
	    			publishResults(flag, rs.getString("DebitAccNum"), DIOFile.getRecordsFromExtractXML().get(42), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), DIOFile.getRecordsFromExtractXML().get(46), "SerNum record matches");	
	    			flag = DEBValidationScenarios.validateSerNumDR(DIOFile.getRecordsFromExtractXML().get(46));
	    			flag = DEBValidationScenarios.validateSerNumDR(DIOFile.getRecordsFromExtractXML().get(47));
	    			publishResults(flag, rs.getString("DebitSerNum"), DIOFile.getRecordsFromExtractXML().get(46), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), DIOFile.getRecordsFromExtractXML().get(48), "TranCode record matches");
	    			flag = DEBValidationScenarios.validateTranCodeDR(DIOFile.getRecordsFromExtractXML().get(48));
	    			flag = DEBValidationScenarios.validateTranCodeDR(DIOFile.getRecordsFromExtractXML().get(49));
	    			publishResults(flag, rs.getString("DebitTranCode"), DIOFile.getRecordsFromExtractXML().get(48), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), DIOFile.getRecordsFromExtractXML().get(50), "SwitchSort record matches");	
	    			flag = DEBValidationScenarios.validateSwitchSort(DIOFile.getRecordsFromExtractXML().get(50));
	    			flag = DEBValidationScenarios.validateSwitchSort(DIOFile.getRecordsFromExtractXML().get(51));
	    			publishResults(flag, rs.getString("DebitSwitchSort"), DIOFile.getRecordsFromExtractXML().get(50), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), DIOFile.getRecordsFromExtractXML().get(52), "SwitchAcc record matches");	
	    			flag = DEBValidationScenarios.validateSwitchAcc(DIOFile.getRecordsFromExtractXML().get(52));
	    			flag = DEBValidationScenarios.validateSwitchAcc(DIOFile.getRecordsFromExtractXML().get(53));
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), DIOFile.getRecordsFromExtractXML().get(52), "SwitchAcc Tag Comparison");
	    		
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), DIOFile.getRecordsFromExtractXML().get(36), "TransID record matches");	
	    			flag = DEBValidationScenarios.validateCreditIdCR(DIOFile.getRecordsFromExtractXML().get(36));
	    			flag = DEBValidationScenarios.validateCreditIdCR(DIOFile.getRecordsFromExtractXML().get(37));
	    			publishResults(flag, rs.getString("CreditTransID"), DIOFile.getRecordsFromExtractXML().get(36), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), DIOFile.getRecordsFromExtractXML().get(40), "SortCode record matches");
	    			flag = DEBValidationScenarios.validateSortCodeCR(DIOFile.getRecordsFromExtractXML().get(40));
	    			flag = DEBValidationScenarios.validateSortCodeCR(DIOFile.getRecordsFromExtractXML().get(41));
	    			publishResults(flag, rs.getString("CreditSortCode"), DIOFile.getRecordsFromExtractXML().get(40), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), DIOFile.getRecordsFromExtractXML().get(44), "AccNnum record matches");
	    			flag = DEBValidationScenarios.validateAccNumCR(DIOFile.getRecordsFromExtractXML().get(44));
	    			flag = DEBValidationScenarios.validateAccNumCR(DIOFile.getRecordsFromExtractXML().get(45));
	    			publishResults(flag, rs.getString("CreditAccNum"), DIOFile.getRecordsFromExtractXML().get(44), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), DIOFile.getRecordsFromExtractXML().get(54), "Ref record matches");	
	    			flag = DEBValidationScenarios.validateRefCR(DIOFile.getRecordsFromExtractXML().get(54));
	    			flag = DEBValidationScenarios.validateRefCR(DIOFile.getRecordsFromExtractXML().get(55));
	    			publishResults(flag, rs.getString("CreditRef"), DIOFile.getRecordsFromExtractXML().get(54), "Ref Tag Comparison");
	    				    				    			
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), DIOFile.getRecordsFromExtractXML().get(56), "Beneficiary record matches");
	    			flag = DEBValidationScenarios.validateBeneficiary(DIOFile.getRecordsFromExtractXML().get(56));
	    			flag = DEBValidationScenarios.validateBeneficiary(DIOFile.getRecordsFromExtractXML().get(57));
	    			publishResults(flag, rs.getString("CreditBeneficiary"), DIOFile.getRecordsFromExtractXML().get(56), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    		    			
	    		}	
	            
	            //Get PERM01 Tags
		
	            PERM01File.getPERM01Details();
	            DIOFile.getRecordsFromExtractXML();
	           
	            //Validate PERM01 tags
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(0),PERM01File.getPERM01Details().get(8),"Sequence Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(0), PERM01File.getPERM01Details().get(8), "Sequence Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(2),PERM01File.getPERM01Details().get(10),"Post Type Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(2), PERM01File.getPERM01Details().get(10), "PostType Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(4),PERM01File.getPERM01Details().get(12),"PostSubType Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(4), PERM01File.getPERM01Details().get(12), "PostSubType Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(8),PERM01File.getPERM01Details().get(14),"channel Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(8), PERM01File.getPERM01Details().get(14), "channel Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(12),PERM01File.getPERM01Details().get(18),"PostingSource Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(12), PERM01File.getPERM01Details().get(18), "PostingSource Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(14),PERM01File.getPERM01Details().get(20),"RespIdSource Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(14), PERM01File.getPERM01Details().get(20), "RespIdSource Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(16),PERM01File.getPERM01Details().get(22),"PostingDay Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(16), PERM01File.getPERM01Details().get(22), "PostingDay Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(18),PERM01File.getPERM01Details().get(24),"ClearDate Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(18), PERM01File.getPERM01Details().get(24), "ClearDate Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(20),PERM01File.getPERM01Details().get(26),"SettlementDate Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(20), PERM01File.getPERM01Details().get(26), "SettlementDate Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(24),PERM01File.getPERM01Details().get(28),"PostedAmount Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(24), PERM01File.getPERM01Details().get(28), "PostedAmount Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(28),PERM01File.getPERM01Details().get(30),"Override Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(28), PERM01File.getPERM01Details().get(30), "Override Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(30),PERM01File.getPERM01Details().get(32),"NPA Sort Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(30), PERM01File.getPERM01Details().get(32), "NPA Sort Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(32),PERM01File.getPERM01Details().get(38),"Coll Location Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(32), PERM01File.getPERM01Details().get(38), "Coll Location Tag Comparison");
	            
	            //Debit Elements
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(34),PERM01File.getPERM01Details().get(1),"Debit Id Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(34), PERM01File.getPERM01Details().get(1), "Debit Id Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(38),PERM01File.getPERM01Details().get(42),"Debit SortCode Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(38), PERM01File.getPERM01Details().get(42), "Debit SortCode Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(42),PERM01File.getPERM01Details().get(46),"Account Number Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(42), PERM01File.getPERM01Details().get(46), "Account Number Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(46),PERM01File.getPERM01Details().get(49),"Serial Number Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(46), PERM01File.getPERM01Details().get(49), "Serial Number Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(48),PERM01File.getPERM01Details().get(51),"Tran Code Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(48), PERM01File.getPERM01Details().get(51), "Tran Code Tag Comparison");
	            
	            //Credit Elements
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(36),PERM01File.getPERM01Details().get(2),"Credit Id Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(36), PERM01File.getPERM01Details().get(2), "Credit Id Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(40),PERM01File.getPERM01Details().get(43),"Sort Code Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(40), PERM01File.getPERM01Details().get(43), "Sort Code Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(44),PERM01File.getPERM01Details().get(47),"Acc Num Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(44), PERM01File.getPERM01Details().get(47), "Acc Num Tag Comparison");
	            
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(54),PERM01File.getPERM01Details().get(60),"Ref Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(54), PERM01File.getPERM01Details().get(60), "Ref Tag Comparison");
	                 
	            flag=Component.verifyEquals(DIOFile.getRecordsFromExtractXML().get(56),PERM01File.getPERM01Details().get(68),"Ref Validation");
	            publishResults(flag, DIOFile.getRecordsFromExtractXML().get(56), PERM01File.getPERM01Details().get(68), "Ref Tag Comparison");
	                
	            
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	/*
	
	@Test(priority = 3)
	public void test_case_DNP() throws SAXException, IOException, ParserConfigurationException
	{
		
		try{
		boolean flag;
		
		//Job Run
		DNPFile.initialSize = DNPFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DNPFile.firstMessageExecution);
		Thread.sleep(3000);
		DNPFile.compareRowCount1();
	
		DNPFile.initialSize =DNPFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DNPFile.secondMessageExecution);
		Thread.sleep(3000);
		DNPFile.compareRowCount1();
		
		DNPFile.initialSize =DNPFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DNPFile.thirdMessageExecution);
		Thread.sleep(3000);
		DNPFile.compareRowCount1();
		
		DNPFile.initialSize =DNPFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DNPFile.fourthMessageExecution);
		Thread.sleep(3000);
		DNPFile.compareRowCount1();
		
		DNPFile.initialSize =DNPFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DNPFile.fifthMessageExecution);
		Thread.sleep(3000);
		DNPFile.compareRowCount1();
		
		DNPFile.initialSize =DNPFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DNPFile.sixthMessageExecution);
		Thread.sleep(3000);
		DNPFile.compareRowCount1();
		
		DNPFile.initialSize =DNPFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(DNPFile.seventhMessageExecution);
		Thread.sleep(3000);
		DNPFile.compareRowCount1();
		
		DNPFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		
		DNPFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		DNPFile.validatePTMR01Refresh();
		Thread.sleep(3000);
		
		//PTMR01 Status "C" check
		DNPFile.validatePTMR01Status();
		
		//PERM01 Validations
		DNPFile.getPERM01XML();
		//DNPFile.getEntitySetsFromPERM01();
		
		//Extract FileName Copy to Local and FileNameValidation
		DNPFile.copyExtractFileFromSharedToLocalFolder();
		DNPFile.getAndValidateFileNameFromRootDirectory();
		
		
			   
			  //DNPFile check
			    
		        DNPFile.validateRnEMOQueueDetailsTableFileID();
		        DNPFile.getTagwiseDataForPostingExtract();			
		        DNPFile.getRecordsFromExtractXML();
		        ResultSet rs=DNPFile.validatePostingResponseHeaderXMLAttributes();
	            
	            while(rs.next())
	    		{	
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderSchema"), DNPFile.responseHeaderSetTagWiseData.get("Schema"), "SchemaTagName record matches");			
	                publishResults(flag, rs.getString("HeaderSchema"), DNPFile.responseHeaderSetTagWiseData.get("Schema"), "Schema Tag Comparison");
	    			            
	    			flag= Component.verifyEquals(rs.getString("HeaderProcDate"), DNPFile.responseHeaderSetTagWiseData.get("ProcDate"), "ProcDateTagName record matches");			
	    			publishResults(flag, rs.getString("HeaderProcDate"), DNPFile.responseHeaderSetTagWiseData.get("ProcDate"), "ProcDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderParticipant"), DNPFile.responseHeaderSetTagWiseData.get("Participant"), "Participant record matches");
	    			publishResults(flag, rs.getString("HeaderParticipant"), DNPFile.responseHeaderSetTagWiseData.get("Participant"), "Participant Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderSequence"), DNPFile.responseHeaderSetTagWiseData.get("Sequence"), "SequenceTagName record matches");
	    			publishResults(flag, rs.getString("HeaderSequence"), DNPFile.responseHeaderSetTagWiseData.get("Sequence"), " Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderVersion"), DNPFile.responseHeaderSetTagWiseData.get("Version"), "VersionTagName record matches");
	    			publishResults(flag, rs.getString("HeaderVersion"), DNPFile.responseHeaderSetTagWiseData.get("Version"), "Version Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderFileDate"), DNPFile.responseHeaderSetTagWiseData.get("FileDate"), "FileDateTagName record matches");
	    			publishResults(flag, rs.getString("HeaderFileDate"), DNPFile.responseHeaderSetTagWiseData.get("FileDate"), "FileDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderEnvironment"), DNPFile.responseHeaderSetTagWiseData.get("Environment"), "EnvironmentTagName record matches");
	    			publishResults(flag, rs.getString("HeaderEnvironment"), DNPFile.responseHeaderSetTagWiseData.get("Environment"), "Environment Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderWeekday"), DNPFile.responseHeaderSetTagWiseData.get("Weekday"), "SourceTagName record matches");
	    			publishResults(flag, rs.getString("HeaderWeekday"), DNPFile.responseHeaderSetTagWiseData.get("Weekday"), "Weekday Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderCurrency"), DNPFile.responseHeaderSetTagWiseData.get("Currency"), "EnvironmentTagName record matches");
	    			publishResults(flag, rs.getString("HeaderCurrency"), DNPFile.responseHeaderSetTagWiseData.get("Currency"), "Currency Tag Comparison");
	    			
	    			System.out.println("successfull for Header Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), DNPFile.getRecordsFromExtractXML().get(0), "SequenceR record matches");
	    			flag = DEBValidationScenarios.validateSequence(DNPFile.getRecordsFromExtractXML().get(0));
	    			flag = DEBValidationScenarios.validateSequence(DNPFile.getRecordsFromExtractXML().get(1));
	    			publishResults(flag, rs.getString("RecordSequence"), DNPFile.getRecordsFromExtractXML().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), DNPFile.getRecordsFromExtractXML().get(2), "PostType record matches");	
	    			flag = DEBValidationScenarios.validatePostType(DNPFile.getRecordsFromExtractXML().get(2));
	    			flag = DEBValidationScenarios.validatePostType(DNPFile.getRecordsFromExtractXML().get(3));
	    			publishResults(flag, rs.getString("RecordPostType"), DNPFile.getRecordsFromExtractXML().get(2), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), DNPFile.getRecordsFromExtractXML().get(4), "SubType record matches");
	    			flag = DEBValidationScenarios.validateSubType(DNPFile.getRecordsFromExtractXML().get(4));
	    			flag = DEBValidationScenarios.validateSubType(DNPFile.getRecordsFromExtractXML().get(5));
	    			publishResults(flag, rs.getString("RecordSubType"), DNPFile.getRecordsFromExtractXML().get(4), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), DNPFile.getRecordsFromExtractXML().get(6), "SourceMsg record matches");
	    			flag = DEBValidationScenarios.validateSourceMsg(DNPFile.getRecordsFromExtractXML().get(6));
	    			flag = DEBValidationScenarios.validateSourceMsg(DNPFile.getRecordsFromExtractXML().get(7));
	    			publishResults(flag, rs.getString("RecordSourceMsg"), DNPFile.getRecordsFromExtractXML().get(6), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), DNPFile.getRecordsFromExtractXML().get(8), "Channel record matches");
	    			flag = DEBValidationScenarios.validateChannel(DNPFile.getRecordsFromExtractXML().get(8));
	    			flag = DEBValidationScenarios.validateChannel(DNPFile.getRecordsFromExtractXML().get(9));
	    			publishResults(flag, rs.getString("RecordChannel"), DNPFile.getRecordsFromExtractXML().get(8), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), DNPFile.getRecordsFromExtractXML().get(10), "EntryType record matches");
	    			flag = DEBValidationScenarios.validateEntryType(DNPFile.getRecordsFromExtractXML().get(10));
	    			flag = DEBValidationScenarios.validateEntryType(DNPFile.getRecordsFromExtractXML().get(11));
	    			publishResults(flag, rs.getString("RecordEntryType"), DNPFile.getRecordsFromExtractXML().get(10), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), DNPFile.getRecordsFromExtractXML().get(12), "PostSource record matches");	
	    			flag = DEBValidationScenarios.validatePostingSource(DNPFile.getRecordsFromExtractXML().get(12));
	    			flag = DEBValidationScenarios.validatePostingSource(DNPFile.getRecordsFromExtractXML().get(13));
	    			publishResults(flag, rs.getString("RecordPostSource"), DNPFile.getRecordsFromExtractXML().get(12), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), DNPFile.getRecordsFromExtractXML().get(14), "RespIDSource record matches");
	    			flag = DEBValidationScenarios.validateResponseIDSource(DNPFile.getRecordsFromExtractXML().get(14));
	    			flag = DEBValidationScenarios.validateResponseIDSource(DNPFile.getRecordsFromExtractXML().get(15));
	    			publishResults(flag, rs.getString("RecordRespSource"), DNPFile.getRecordsFromExtractXML().get(14), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), DNPFile.getRecordsFromExtractXML().get(16), "PostDay record matches");
	    			flag = DEBValidationScenarios.validatePostDay(DNPFile.getRecordsFromExtractXML().get(16));
	    			flag = DEBValidationScenarios.validatePostDay(DNPFile.getRecordsFromExtractXML().get(17));
	    			publishResults(flag, rs.getString("RecordPostDay"), DNPFile.getRecordsFromExtractXML().get(16), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), DNPFile.getRecordsFromExtractXML().get(18), "ClearDate record matches");
	    			flag = DEBValidationScenarios.validateClearDate(DNPFile.getRecordsFromExtractXML().get(18));
	    			flag = DEBValidationScenarios.validateClearDate(DNPFile.getRecordsFromExtractXML().get(19));
	    			publishResults(flag, rs.getString("RecordClearDate"), DNPFile.getRecordsFromExtractXML().get(18), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), DNPFile.getRecordsFromExtractXML().get(20), "SettDate record matches");
	    			flag = DEBValidationScenarios.validateSettDate(DNPFile.getRecordsFromExtractXML().get(20));
	    			flag = DEBValidationScenarios.validateSettDate(DNPFile.getRecordsFromExtractXML().get(21));
	    			publishResults(flag, rs.getString("RecordSettDate"), DNPFile.getRecordsFromExtractXML().get(20), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), DNPFile.getRecordsFromExtractXML().get(22), "CaptDate record matches");
	    			flag = DEBValidationScenarios.validateCaptDate(DNPFile.getRecordsFromExtractXML().get(22));
	    			flag = DEBValidationScenarios.validateCaptDate(DNPFile.getRecordsFromExtractXML().get(23));
	    			publishResults(flag, rs.getString("RecordCaptDate"), DNPFile.getRecordsFromExtractXML().get(22), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), DNPFile.getRecordsFromExtractXML().get(24), "Amount record matches");
	    			flag = DEBValidationScenarios.validateAmount(DNPFile.getRecordsFromExtractXML().get(24));
	    			flag = DEBValidationScenarios.validateAmount(DNPFile.getRecordsFromExtractXML().get(25));
	    			publishResults(flag, rs.getString("RecordAmount"), DNPFile.getRecordsFromExtractXML().get(24), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), DNPFile.getRecordsFromExtractXML().get(26), "TranSetID record matches");
	    			flag = DEBValidationScenarios.validateTranSetID(DNPFile.getRecordsFromExtractXML().get(26));
	    			flag = DEBValidationScenarios.validateTranSetID(DNPFile.getRecordsFromExtractXML().get(27));
	    			publishResults(flag, rs.getString("RecordTranSetID"), DNPFile.getRecordsFromExtractXML().get(26), "TranSetID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), DNPFile.getRecordsFromExtractXML().get(28), "Override record matches");
	    			flag = DEBValidationScenarios.validateOverride(DNPFile.getRecordsFromExtractXML().get(28));
	    			flag = DEBValidationScenarios.validateOverride(DNPFile.getRecordsFromExtractXML().get(29));
	    			publishResults(flag, rs.getString("RecordOverride"), DNPFile.getRecordsFromExtractXML().get(28), "Override Tag Comparison");
	    			
//	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), DNPFile.getRecordsFromExtractXML().get(30), "NPASort record matches");
//	    			flag = DEBValidationScenarios.validateNPASort(DNPFile.getRecordsFromExtractXML().get(30));
//	    			flag = DEBValidationScenarios.validateNPASort(DNPFile.getRecordsFromExtractXML().get(31));
//	    			publishResults(flag, rs.getString("RecordCollPart"), DNPFile.getRecordsFromExtractXML().get(30), "NPASort Tag Comparison");
//	    			
//	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), DNPFile.getRecordsFromExtractXML().get(32), "CollLocn record matches");	
//	    			flag = DEBValidationScenarios.validateCollLocn(DNPFile.getRecordsFromExtractXML().get(32));
//	    			flag = DEBValidationScenarios.validateCollLocn(DNPFile.getRecordsFromExtractXML().get(33));
//	    			publishResults(flag, rs.getString("RecordCollLoc"), DNPFile.getRecordsFromExtractXML().get(32), "CollLocn Tag Comparison");
//	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), DNPFile.getRecordsFromExtractXML().get(34), "DebitTransId record matches");
	    			flag = DEBValidationScenarios.validateDebitTransactionID(DNPFile.getRecordsFromExtractXML().get(34));
	    			flag = DEBValidationScenarios.validateDebitTransactionID(DNPFile.getRecordsFromExtractXML().get(35));
	    			publishResults(flag, rs.getString("DebitTransID"), DNPFile.getRecordsFromExtractXML().get(34), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), DNPFile.getRecordsFromExtractXML().get(38), "SortCode record matches");
	    			flag = DEBValidationScenarios.validateSortCodeDR(DNPFile.getRecordsFromExtractXML().get(38));
	    			flag = DEBValidationScenarios.validateSortCodeDR(DNPFile.getRecordsFromExtractXML().get(39));
	    			publishResults(flag, rs.getString("DebitSortCode"), DNPFile.getRecordsFromExtractXML().get(38), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), DNPFile.getRecordsFromExtractXML().get(42), "AccNum record matches");	
	    			flag = DEBValidationScenarios.validateAccNumDR(DNPFile.getRecordsFromExtractXML().get(42));
	    			flag = DEBValidationScenarios.validateAccNumDR(DNPFile.getRecordsFromExtractXML().get(43));
	    			publishResults(flag, rs.getString("DebitAccNum"), DNPFile.getRecordsFromExtractXML().get(42), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), DNPFile.getRecordsFromExtractXML().get(46), "SerNum record matches");	
	    			flag = DEBValidationScenarios.validateSerNumDR(DNPFile.getRecordsFromExtractXML().get(46));
	    			flag = DEBValidationScenarios.validateSerNumDR(DNPFile.getRecordsFromExtractXML().get(47));
	    			publishResults(flag, rs.getString("DebitSerNum"), DNPFile.getRecordsFromExtractXML().get(46), "SerNum Tag Comparison");

//	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), DNPFile.getRecordsFromExtractXML().get(48), "TranCode record matches");
//	    			flag = DEBValidationScenarios.validateTranCodeDR(DNPFile.getRecordsFromExtractXML().get(48));
//	    			flag = DEBValidationScenarios.validateTranCodeDR(DNPFile.getRecordsFromExtractXML().get(49));
//	    			publishResults(flag, rs.getString("DebitTranCode"), DNPFile.getRecordsFromExtractXML().get(48), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), DNPFile.getRecordsFromExtractXML().get(50), "SwitchSort record matches");	
	    			flag = DEBValidationScenarios.validateSwitchSort(DNPFile.getRecordsFromExtractXML().get(50));
	    			flag = DEBValidationScenarios.validateSwitchSort(DNPFile.getRecordsFromExtractXML().get(51));
	    			publishResults(flag, rs.getString("DebitSwitchSort"), DNPFile.getRecordsFromExtractXML().get(50), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), DNPFile.getRecordsFromExtractXML().get(52), "SwitchAcc record matches");	
	    			flag = DEBValidationScenarios.validateSwitchAcc(DNPFile.getRecordsFromExtractXML().get(52));
	    			flag = DEBValidationScenarios.validateSwitchAcc(DNPFile.getRecordsFromExtractXML().get(53));
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), DNPFile.getRecordsFromExtractXML().get(52), "SwitchAcc Tag Comparison");
	    		
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), DNPFile.getRecordsFromExtractXML().get(36), "TransID record matches");	
	    			flag = DEBValidationScenarios.validateCreditIdCR(DNPFile.getRecordsFromExtractXML().get(36));
	    			flag = DEBValidationScenarios.validateCreditIdCR(DNPFile.getRecordsFromExtractXML().get(37));
	    			publishResults(flag, rs.getString("CreditTransID"), DNPFile.getRecordsFromExtractXML().get(36), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), DNPFile.getRecordsFromExtractXML().get(40), "SortCode record matches");
	    			flag = DEBValidationScenarios.validateSortCodeCR(DNPFile.getRecordsFromExtractXML().get(40));
	    			flag = DEBValidationScenarios.validateSortCodeCR(DNPFile.getRecordsFromExtractXML().get(41));
	    			publishResults(flag, rs.getString("CreditSortCode"), DNPFile.getRecordsFromExtractXML().get(40), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), DNPFile.getRecordsFromExtractXML().get(44), "AccNnum record matches");
	    			flag = DEBValidationScenarios.validateAccNumCR(DNPFile.getRecordsFromExtractXML().get(44));
	    			flag = DEBValidationScenarios.validateAccNumCR(DNPFile.getRecordsFromExtractXML().get(45));
	    			publishResults(flag, rs.getString("CreditAccNum"), DNPFile.getRecordsFromExtractXML().get(44), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), DNPFile.getRecordsFromExtractXML().get(54), "Ref record matches");	
	    			flag = DEBValidationScenarios.validateRefCR(DNPFile.getRecordsFromExtractXML().get(54));
	    			flag = DEBValidationScenarios.validateRefCR(DNPFile.getRecordsFromExtractXML().get(55));
	    			publishResults(flag, rs.getString("CreditRef"), DNPFile.getRecordsFromExtractXML().get(54), "Ref Tag Comparison");
	    				    				    			
//	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), DNPFile.getRecordsFromExtractXML().get(56), "Beneficiary record matches");
//	    			flag = DEBValidationScenarios.validateBeneficiary(DNPFile.getRecordsFromExtractXML().get(56));
//	    			flag = DEBValidationScenarios.validateBeneficiary(DNPFile.getRecordsFromExtractXML().get(57));
//	    			publishResults(flag, rs.getString("CreditBeneficiary"), DNPFile.getRecordsFromExtractXML().get(56), "Beneficiary Tag Comparison");
//	    			
	    			System.out.println("Validation End");
	    		    			
	    		}	
	            
	            //Get PERM01 Tags
		
	            PERM01File.getPERM01Details();
	            DNPFile.getRecordsFromExtractXML();
	           
	            //Validate PERM01 tags
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(0),PERM01File.getPERM01Details().get(8),"Sequence Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(0), PERM01File.getPERM01Details().get(8), "Sequence Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(2),PERM01File.getPERM01Details().get(10),"Post Type Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(2), PERM01File.getPERM01Details().get(10), "PostType Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(4),PERM01File.getPERM01Details().get(12),"PostSubType Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(4), PERM01File.getPERM01Details().get(12), "PostSubType Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(8),PERM01File.getPERM01Details().get(14),"channel Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(8), PERM01File.getPERM01Details().get(14), "channel Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(12),PERM01File.getPERM01Details().get(18),"PostingSource Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(12), PERM01File.getPERM01Details().get(18), "PostingSource Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(14),PERM01File.getPERM01Details().get(20),"RespIdSource Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(14), PERM01File.getPERM01Details().get(20), "RespIdSource Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(16),PERM01File.getPERM01Details().get(22),"PostingDay Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(16), PERM01File.getPERM01Details().get(22), "PostingDay Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(18),PERM01File.getPERM01Details().get(24),"ClearDate Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(18), PERM01File.getPERM01Details().get(24), "ClearDate Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(20),PERM01File.getPERM01Details().get(26),"SettlementDate Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(20), PERM01File.getPERM01Details().get(26), "SettlementDate Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(24),PERM01File.getPERM01Details().get(28),"PostedAmount Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(24), PERM01File.getPERM01Details().get(28), "PostedAmount Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(28),PERM01File.getPERM01Details().get(30),"Override Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(28), PERM01File.getPERM01Details().get(30), "Override Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(30),PERM01File.getPERM01Details().get(32),"NPA Sort Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(30), PERM01File.getPERM01Details().get(32), "NPA Sort Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(32),PERM01File.getPERM01Details().get(38),"Coll Location Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(32), PERM01File.getPERM01Details().get(38), "Coll Location Tag Comparison");
	            
	            //Debit Elements
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(34),PERM01File.getPERM01Details().get(1),"Debit Id Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(34), PERM01File.getPERM01Details().get(1), "Debit Id Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(38),PERM01File.getPERM01Details().get(42),"Debit SortCode Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(38), PERM01File.getPERM01Details().get(42), "Debit SortCode Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(42),PERM01File.getPERM01Details().get(46),"Account Number Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(42), PERM01File.getPERM01Details().get(46), "Account Number Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(46),PERM01File.getPERM01Details().get(49),"Serial Number Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(46), PERM01File.getPERM01Details().get(49), "Serial Number Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(48),PERM01File.getPERM01Details().get(51),"Tran Code Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(48), PERM01File.getPERM01Details().get(51), "Tran Code Tag Comparison");
	            
	            //Credit Elements
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(36),PERM01File.getPERM01Details().get(2),"Credit Id Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(36), PERM01File.getPERM01Details().get(2), "Credit Id Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(40),PERM01File.getPERM01Details().get(43),"Sort Code Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(40), PERM01File.getPERM01Details().get(43), "Sort Code Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(44),PERM01File.getPERM01Details().get(47),"Acc Num Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(44), PERM01File.getPERM01Details().get(47), "Acc Num Tag Comparison");
	            
	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(54),PERM01File.getPERM01Details().get(60),"Ref Validation");
	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(54), PERM01File.getPERM01Details().get(60), "Ref Tag Comparison");
	                 
//	            flag=Component.verifyEquals(DNPFile.getRecordsFromExtractXML().get(56),PERM01File.getPERM01Details().get(68),"Ref Validation");
//	            publishResults(flag, DNPFile.getRecordsFromExtractXML().get(56), PERM01File.getPERM01Details().get(68), "Ref Tag Comparison");
                
	            
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	*/

}
