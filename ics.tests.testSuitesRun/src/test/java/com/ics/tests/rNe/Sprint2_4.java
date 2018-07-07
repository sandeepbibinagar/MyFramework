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

/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */

public class Sprint2_4 extends ICSAutomationCommonSetup{
	
	
	/*
	 * Test Case ID: 71824
	 * Test Description: E2E RnE Scenario Validation  
	 * Author: Himanshu Malhotra
	 * Date: 12th-MAY-2017
	 * Comments:
	 */
	@Test(priority = 1)
	public void test_case_71824() throws SAXException, IOException, ParserConfigurationException
	{
		
		try{
		boolean flag;			
		
		//Src Tag Check
		PostingExtractFile.readDataFrom05MA01();
		PostingExtractFile.readDataFrom13MA02();
		
		//Job Run
		PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution);
		Thread.sleep(3000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
		Thread.sleep(3000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
		Thread.sleep(3000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
		Thread.sleep(3000);
		PostingExtractFile.compareRowCount1();
		
		PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
		ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
		Thread.sleep(3000);
		PostingExtractFile.compareRowCount1();
		
		ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
		Thread.sleep(3000);
		ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
		
		//PTMR01 Status "C" check
		PostingExtractFile.validatePTMR01Status();
		
		//Extract FileName Copy to Local and FileNameValidation
		PostingExtractFile.copyExtractFileFromSharedToLocalFolder();
		PostingExtractFile.getAndValidateFileNameFromRootDirectory();
		
		//PERM01 Validations
		PostingExtractFile.getPERM01XML();
		PostingExtractFile.getEntitySetsFromPERM01();
		PostingExtractFile.getPERM01TagwiseData();
		
		//PERM01 Data Validation
		
				//Core Attributes
				flag = PostingExtractFileValidationScenarios.validateBusinessDate(PostingExtractFile.coreNodeSetTagWiseData.get("BusinessDate"));
			    Component.verifyTrue(flag, "BusinessDate Tag not as expected");
				
			    flag = PostingExtractFileValidationScenarios.validateExtractId(PostingExtractFile.coreNodeSetTagWiseData.get("ExtractId"));
			    Component.verifyTrue(flag, "ExtractId Tag not as expected");

			    flag = PostingExtractFileValidationScenarios.validateIntMessageType(PostingExtractFile.coreNodeSetTagWiseData.get("IntMessageType"));
			    Component.verifyTrue(flag, "IntMessageType Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateProcessingParticipantId(PostingExtractFile.coreNodeSetTagWiseData.get("ProcessingParticipantId"));
			    Component.verifyTrue(flag, "ProcessingParticipantId Tag not as expected");
			    
			 //   flag = PostingExtractFileValidationScenarios.validateSequence(PostingExtractFile.coreNodeSetTagWiseData.get("Sequence"));
			   // Component.verifyTrue(flag, "Sequence Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateExtMessageType(PostingExtractFile.coreNodeSetTagWiseData.get("ExtMessageType"));
			    Component.verifyTrue(flag, "ExtMessageType Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateMessageSource(PostingExtractFile.coreNodeSetTagWiseData.get("MessageSource"));
			    Component.verifyTrue(flag, "MessageSource Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateMessageDestination(PostingExtractFile.coreNodeSetTagWiseData.get("MessageDestination"));
			    Component.verifyTrue(flag, "MessageDestination Tag not as expected");
			    
			    //Entity Attributes
			    flag = PostingExtractFileValidationScenarios.validateEntityType(PostingExtractFile.getEntitySetsFromPERM01().get(0));
			    Component.verifyTrue(flag, "EntityType Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateEntityType(PostingExtractFile.getEntitySetsFromPERM01().get(3));
			    Component.verifyTrue(flag, "EntityType Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateEntityId(PostingExtractFile.getEntitySetsFromPERM01().get(1));
			    Component.verifyTrue(flag, "EntityId Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateEntityId(PostingExtractFile.getEntitySetsFromPERM01().get(4));
			    Component.verifyTrue(flag, "EntityId Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateEntityState(PostingExtractFile.getEntitySetsFromPERM01().get(2));
			    Component.verifyTrue(flag, "EntityState Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateEntityState(PostingExtractFile.getEntitySetsFromPERM01().get(5));
			    Component.verifyTrue(flag, "EntityState Tag not as expected");
			    
			    //Posting Update Attributes
			    
			    flag = PostingExtractFileValidationScenarios.validateExtractSequence(PostingExtractFile.postingUpdateSetTagWiseData.get("ExtractSequence"));
			    Component.verifyTrue(flag, "ExtractSequence Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateExtractRevision(PostingExtractFile.postingUpdateSetTagWiseData.get("ExtractRevision"));
			    Component.verifyTrue(flag, "ExtractRevision Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateFileId(PostingExtractFile.postingUpdateSetTagWiseData.get("FileId"));
			    Component.verifyTrue(flag, "FileId Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateWeekday(PostingExtractFile.postingUpdateSetTagWiseData.get("WeekDay"));
			    Component.verifyTrue(flag, "Weekday Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateFileType(PostingExtractFile.postingUpdateSetTagWiseData.get("FileType"));
			    Component.verifyTrue(flag, "FileType Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateCurrency(PostingExtractFile.postingUpdateSetTagWiseData.get("Currency"));
			    Component.verifyTrue(flag, "Currency Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateEnvironment(PostingExtractFile.postingUpdateSetTagWiseData.get("Environment"));
			    Component.verifyTrue(flag, "Environment Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateExtractStartDateTime(PostingExtractFile.postingUpdateSetTagWiseData.get("ExtractStartDateTime"));
			    Component.verifyTrue(flag, "ExtractStartDateTime Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateExtractEndDateTime(PostingExtractFile.postingUpdateSetTagWiseData.get("ExtractEndDateTime"));
			    Component.verifyTrue(flag, "ExtractEndDateTime Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateExtractItemCount(PostingExtractFile.postingUpdateSetTagWiseData.get("ExtractItemCount"));
			    Component.verifyTrue(flag, "ExtractItemCount Tag not as expected");
			    
			    //PostingItemEntry Attributes
			    
			    flag = PostingExtractFileValidationScenarios.validateItemId(PostingExtractFile.postingItemEntrySetTagWiseData.get("ItemId"));
			    Component.verifyTrue(flag, "ItemId Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validatePostingAttempt(PostingExtractFile.postingItemEntrySetTagWiseData.get("PostingAttempt"));
			    Component.verifyTrue(flag, "PostingAttempt Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validatePostingSequence(PostingExtractFile.postingItemEntrySetTagWiseData.get("PostingSequence"));
			    Component.verifyTrue(flag, "PostingSequence Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validatePostingType(PostingExtractFile.postingItemEntrySetTagWiseData.get("PostingType"));
			    Component.verifyTrue(flag, "PostingType Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validatePostingSubType(PostingExtractFile.postingItemEntrySetTagWiseData.get("PostingSubType"));
			    Component.verifyTrue(flag, "PostingSubType Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateChannel(PostingExtractFile.postingItemEntrySetTagWiseData.get("Channel"));
			    Component.verifyTrue(flag, "Channel Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validatePostingDrCrEntry(PostingExtractFile.postingItemEntrySetTagWiseData.get("PostingDrCrEntry"));
			    Component.verifyTrue(flag, "PostingDrCrEntry Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validatePostingSource(PostingExtractFile.postingItemEntrySetTagWiseData.get("PostingSource"));
			    Component.verifyTrue(flag, "PostingSource Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateResponseIdSource(PostingExtractFile.postingItemEntrySetTagWiseData.get("ResponseIDSource"));
			    Component.verifyTrue(flag, "ResponseIDSource Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validatePostingDay(PostingExtractFile.postingItemEntrySetTagWiseData.get("PostingDay"));
			    Component.verifyTrue(flag, "PostingDay Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateClearingDate(PostingExtractFile.postingItemEntrySetTagWiseData.get("ClearingDate"));
			    Component.verifyTrue(flag, "ClearingDate Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateSettlementDate(PostingExtractFile.postingItemEntrySetTagWiseData.get("SettlementDate"));
			    Component.verifyTrue(flag, "SettlementDate Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validatePostedAmount(PostingExtractFile.postingItemEntrySetTagWiseData.get("PostedAmount"));
			    Component.verifyTrue(flag, "PostedAmount Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validatePostingOverrideFlag(PostingExtractFile.postingItemEntrySetTagWiseData.get("PostingOverrideFlag"));
			    Component.verifyTrue(flag, "PostingOverrideFlag Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateNPASortCode(PostingExtractFile.postingItemEntrySetTagWiseData.get("NPASortCode"));
			    Component.verifyTrue(flag, "NPASortCode Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateNPAAccount(PostingExtractFile.postingItemEntrySetTagWiseData.get("NPAAccount"));
			    Component.verifyTrue(flag, "NPAAccount Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateSupportingInfo(PostingExtractFile.postingItemEntrySetTagWiseData.get("SupportingInfo"));
			    Component.verifyTrue(flag, "SupportingInfo Tag not as expected");
			    
			    //flag = PostingExtractFileValidationScenarios.validateChequeCount(PostingExtractFile.postingItemEntrySetTagWiseData.get("ChequeCount"));
			    //Component.verifyTrue(flag, "ChequeCount Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateCollectingParticipantId(PostingExtractFile.postingItemEntrySetTagWiseData.get("CollectingParticipantID"));
			    Component.verifyTrue(flag, "CollectingParticipantID Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateCollectingLocation(PostingExtractFile.postingItemEntrySetTagWiseData.get("CollectingLocation"));
			    Component.verifyTrue(flag, "CollectingLocation Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateAggregrated(PostingExtractFile.postingItemEntrySetTagWiseData.get("Aggregated"));
			    Component.verifyTrue(flag, "Aggregated Tag not as expected");
			    
			    //Debit Record Attributes
			    
			    flag = PostingExtractFileValidationScenarios.validateItemIdDR(PostingExtractFile.debitRecordSetTagWiseData.get("ItemId"));
			    Component.verifyTrue(flag, "ItemId Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateSortCodeDR(PostingExtractFile.debitRecordSetTagWiseData.get("SortCode"));
			    Component.verifyTrue(flag, "SortCode Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateAccountDR(PostingExtractFile.debitRecordSetTagWiseData.get("Account"));
			    Component.verifyTrue(flag, "Account Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateSerialDR(PostingExtractFile.debitRecordSetTagWiseData.get("Serial"));
			    Component.verifyTrue(flag, "Serial Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateTranCodeDR(PostingExtractFile.debitRecordSetTagWiseData.get("TranCode"));
			    Component.verifyTrue(flag, "TranCode Tag not as expected");
			    
			//   /* flag = PostingExtractFileValidationScenarios.validateSwitchedSortCode(PostingExtractFile.debitRecordSetTagWiseData.get("SwitchedSortCode"));
			  //  Component.verifyTrue(flag, "SwitchedSortCode Tag not as expected");
			    
			   // flag = PostingExtractFileValidationScenarios.validateSwitchedAccount(PostingExtractFile.debitRecordSetTagWiseData.get("SwitchedAccount"));
			   // Component.verifyTrue(flag, "SwitchedAccount Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateDebitFullAmount(PostingExtractFile.debitRecordSetTagWiseData.get("DebitFullAmount"));
			    Component.verifyTrue(flag, "DebitFullAmount Tag not as expected");
			    
			    //Credit Record Attributes
			    
			    flag = PostingExtractFileValidationScenarios.validateItemIdCR(PostingExtractFile.creditRecordSetTagWiseData.get("ItemId"));
			    Component.verifyTrue(flag, "ItemId Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateSortCodeCR(PostingExtractFile.creditRecordSetTagWiseData.get("SortCode"));
			    Component.verifyTrue(flag, "SortCode Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateAccountCR(PostingExtractFile.creditRecordSetTagWiseData.get("Account"));
			    Component.verifyTrue(flag, "Account Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateReferenceCR(PostingExtractFile.creditRecordSetTagWiseData.get("Reference"));
			    Component.verifyTrue(flag, "Reference Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateTranCodeCR(PostingExtractFile.creditRecordSetTagWiseData.get("TranCode"));
			    Component.verifyTrue(flag, "TranCode Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateOriginalAmount(PostingExtractFile.creditRecordSetTagWiseData.get("OriginalAmount"));
			    Component.verifyTrue(flag, "OriginalAmount Tag not as expected");
			    
//			   /* flag = PostingExtractFileValidationScenarios.validateOriginalSortCode(PostingExtractFile.creditRecordSetTagWiseData.get("OriginalSortCode"));
//			    Component.verifyTrue(flag, "OriginalSortCode Tag not as expected");
//			    
//			    flag = PostingExtractFileValidationScenarios.validateOriginalAccount(PostingExtractFile.creditRecordSetTagWiseData.get("OriginalAccount"));
//			    Component.verifyTrue(flag, "OriginalAccount Tag not as expected");
//			    
//			    flag = PostingExtractFileValidationScenarios.validateOriginalAccount(PostingExtractFile.creditRecordSetTagWiseData.get("OriginalAccount"));
//			    Component.verifyTrue(flag, "OriginalAccount Tag not as expected");
//			    
//			    flag = PostingExtractFileValidationScenarios.validateCreditExceptionCode(PostingExtractFile.creditRecordSetTagWiseData.get("CreditExceptionCode"));
//			    Component.verifyTrue(flag, "CreditExceptionCode Tag not as expected");
			    
			    flag = PostingExtractFileValidationScenarios.validateBeneficiaryName(PostingExtractFile.creditRecordSetTagWiseData.get("BeneficiaryName"));
			    Component.verifyTrue(flag, "BeneficiaryName Tag not as expected");
			    
			    System.out.println("End of validation");
			    
			  //ExtractXMLFile check
			    
			    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
				ExtractXML.getTagwiseDataForPostingExtract();			
		        ExtractXML.getRecordsFromExtractXML();
		        ResultSet rs=ExtractXML.validatePostingResponseHeaderXMLAttributes();
	            
	            while(rs.next())
	    		{	
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderSchema"), ExtractXML.responseHeaderSetTagWiseData.get("Schema"), "SchemaTagName record matches");			
	                publishResults(flag, rs.getString("HeaderSchema"), ExtractXML.responseHeaderSetTagWiseData.get("Schema"), "Schema Tag Comparison");
	    			            
	    			flag= Component.verifyEquals(rs.getString("HeaderProcDate"), ExtractXML.responseHeaderSetTagWiseData.get("ProcDate"), "ProcDateTagName record matches");			
	    			publishResults(flag, rs.getString("HeaderProcDate"), ExtractXML.responseHeaderSetTagWiseData.get("ProcDate"), "ProcDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderParticipant"), ExtractXML.responseHeaderSetTagWiseData.get("Participant"), "Participant record matches");
	    			publishResults(flag, rs.getString("HeaderParticipant"), ExtractXML.responseHeaderSetTagWiseData.get("Participant"), "Participant Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderSequence"), ExtractXML.responseHeaderSetTagWiseData.get("Sequence"), "SequenceTagName record matches");
	    			publishResults(flag, rs.getString("HeaderSequence"), ExtractXML.responseHeaderSetTagWiseData.get("Sequence"), " Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderVersion"), ExtractXML.responseHeaderSetTagWiseData.get("Version"), "VersionTagName record matches");
	    			publishResults(flag, rs.getString("HeaderVersion"), ExtractXML.responseHeaderSetTagWiseData.get("Version"), "Version Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderFileDate"), ExtractXML.responseHeaderSetTagWiseData.get("FileDate"), "FileDateTagName record matches");
	    			publishResults(flag, rs.getString("HeaderFileDate"), ExtractXML.responseHeaderSetTagWiseData.get("FileDate"), "FileDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderEnvironment"), ExtractXML.responseHeaderSetTagWiseData.get("Environment"), "EnvironmentTagName record matches");
	    			publishResults(flag, rs.getString("HeaderEnvironment"), ExtractXML.responseHeaderSetTagWiseData.get("Environment"), "Environment Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderWeekday"), ExtractXML.responseHeaderSetTagWiseData.get("Weekday"), "SourceTagName record matches");
	    			publishResults(flag, rs.getString("HeaderWeekday"), ExtractXML.responseHeaderSetTagWiseData.get("Weekday"), "Weekday Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("HeaderCurrency"), ExtractXML.responseHeaderSetTagWiseData.get("Currency"), "EnvironmentTagName record matches");
	    			publishResults(flag, rs.getString("HeaderCurrency"), ExtractXML.responseHeaderSetTagWiseData.get("Currency"), "Currency Tag Comparison");
	    			
	    			System.out.println("successfull for Header Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), ExtractXML.getRecordsFromExtractXML().get(0), "SequenceR record matches");			
	    			publishResults(flag, rs.getString("RecordSequence"), ExtractXML.getRecordsFromExtractXML().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), ExtractXML.getRecordsFromExtractXML().get(1), "PostType record matches");			
	    			publishResults(flag, rs.getString("RecordPostType"), ExtractXML.getRecordsFromExtractXML().get(1), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), ExtractXML.getRecordsFromExtractXML().get(2), "SubType record matches");			
	    			publishResults(flag, rs.getString("RecordSubType"), ExtractXML.getRecordsFromExtractXML().get(2), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), ExtractXML.getRecordsFromExtractXML().get(3), "SourceMsg record matches");			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), ExtractXML.getRecordsFromExtractXML().get(3), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), ExtractXML.getRecordsFromExtractXML().get(4), "Channel record matches");			
	    			publishResults(flag, rs.getString("RecordChannel"), ExtractXML.getRecordsFromExtractXML().get(4), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), ExtractXML.getRecordsFromExtractXML().get(5), "EntryType record matches");			
	    			publishResults(flag, rs.getString("RecordEntryType"), ExtractXML.getRecordsFromExtractXML().get(5), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), ExtractXML.getRecordsFromExtractXML().get(6), "PostSource record matches");			
	    			publishResults(flag, rs.getString("RecordPostSource"), ExtractXML.getRecordsFromExtractXML().get(6), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), ExtractXML.getRecordsFromExtractXML().get(7), "RespIDSource record matches");			
	    			publishResults(flag, rs.getString("RecordRespSource"), ExtractXML.getRecordsFromExtractXML().get(7), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), ExtractXML.getRecordsFromExtractXML().get(8), "PostDay record matches");			
	    			publishResults(flag, rs.getString("RecordPostDay"), ExtractXML.getRecordsFromExtractXML().get(8), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), ExtractXML.getRecordsFromExtractXML().get(9), "ClearDate record matches");			
	    			publishResults(flag, rs.getString("RecordClearDate"), ExtractXML.getRecordsFromExtractXML().get(9), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), ExtractXML.getRecordsFromExtractXML().get(10), "SettDate record matches");			
	    			publishResults(flag, rs.getString("RecordSettDate"), ExtractXML.getRecordsFromExtractXML().get(10), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), ExtractXML.getRecordsFromExtractXML().get(11), "CaptDate record matches");			
	    			publishResults(flag, rs.getString("RecordCaptDate"), ExtractXML.getRecordsFromExtractXML().get(11), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), ExtractXML.getRecordsFromExtractXML().get(12), "Amount record matches");			
	    			publishResults(flag, rs.getString("RecordAmount"), ExtractXML.getRecordsFromExtractXML().get(12), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), ExtractXML.getRecordsFromExtractXML().get(13), "TranSetID record matches");			
	    			publishResults(flag, rs.getString("RecordTranSetID"), ExtractXML.getRecordsFromExtractXML().get(13), "TranSetID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordReason"), ExtractXML.getRecordsFromExtractXML().get(14), "Reason record matches");			
	    			publishResults(flag, rs.getString("RecordReason"),ExtractXML.getRecordsFromExtractXML().get(14), "Reason Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), ExtractXML.getRecordsFromExtractXML().get(15), "Override record matches");			
	    			publishResults(flag, rs.getString("RecordOverride"), ExtractXML.getRecordsFromExtractXML().get(15), "Override Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordNPASort"), ExtractXML.getRecordsFromExtractXML().get(16), "NPASort record matches");			
	    			publishResults(flag, rs.getString("RecordNPASort"), ExtractXML.getRecordsFromExtractXML().get(16), "NPASort Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordNumCheques"), ExtractXML.getRecordsFromExtractXML().get(17), "NumCheques record matches");			
	    			publishResults(flag, rs.getString("RecordNumCheques"), ExtractXML.getRecordsFromExtractXML().get(17), "NumCheques Tag Comparison");
	        		
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), ExtractXML.getRecordsFromExtractXML().get(18), "CollPart record matches");			
	    			publishResults(flag, rs.getString("RecordCollPart"), ExtractXML.getRecordsFromExtractXML().get(18), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), ExtractXML.getRecordsFromExtractXML().get(19), "CollLocn record matches");			
	    			publishResults(flag, rs.getString("RecordCollLoc"), ExtractXML.getRecordsFromExtractXML().get(19), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), ExtractXML.getRecordsFromExtractXML().get(20), "DebitTransId record matches");			
	    			publishResults(flag, rs.getString("DebitTransID"), ExtractXML.getRecordsFromExtractXML().get(20), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), ExtractXML.getRecordsFromExtractXML().get(21), "SortCode record matches");			
	    			publishResults(flag, rs.getString("DebitSortCode"), ExtractXML.getRecordsFromExtractXML().get(21), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), ExtractXML.getRecordsFromExtractXML().get(22), "AccNum record matches");			
	    			publishResults(flag, rs.getString("DebitAccNum"), ExtractXML.getRecordsFromExtractXML().get(22), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), ExtractXML.getRecordsFromExtractXML().get(23), "SerNum record matches");			
	    			publishResults(flag, rs.getString("DebitSerNum"), ExtractXML.getRecordsFromExtractXML().get(23), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), ExtractXML.getRecordsFromExtractXML().get(24), "TranCode record matches");			
	    			publishResults(flag, rs.getString("DebitTranCode"), ExtractXML.getRecordsFromExtractXML().get(24), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), ExtractXML.getRecordsFromExtractXML().get(25), "SwitchAcc record matches");			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), ExtractXML.getRecordsFromExtractXML().get(25), "SwitchAcc Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), ExtractXML.getRecordsFromExtractXML().get(26), "SwitchSort record matches");			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), ExtractXML.getRecordsFromExtractXML().get(26), "SwitchSort Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitFullAmt"), ExtractXML.getRecordsFromExtractXML().get(27), "FullAmt record matches");			
	    			publishResults(flag, rs.getString("DebitFullAmt"), ExtractXML.getRecordsFromExtractXML().get(27), "FullAmt Tag Comparison");
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), ExtractXML.getRecordsFromExtractXML().get(28), "TransID record matches");			
	    			publishResults(flag, rs.getString("CreditTransID"), ExtractXML.getRecordsFromExtractXML().get(28), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), ExtractXML.getRecordsFromExtractXML().get(29), "SortCode record matches");			
	    			publishResults(flag, rs.getString("CreditSortCode"), ExtractXML.getRecordsFromExtractXML().get(29), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), ExtractXML.getRecordsFromExtractXML().get(30), "AccNnum record matches");			
	    			publishResults(flag, rs.getString("CreditAccNum"), ExtractXML.getRecordsFromExtractXML().get(30), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), ExtractXML.getRecordsFromExtractXML().get(31), "Ref record matches");			
	    			publishResults(flag, rs.getString("CreditRef"), ExtractXML.getRecordsFromExtractXML().get(31), "Ref Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransCode"), ExtractXML.getRecordsFromExtractXML().get(32), "TransCode record matches");			
	    			publishResults(flag, rs.getString("CreditTransCode"), ExtractXML.getRecordsFromExtractXML().get(32), "TransCode Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), ExtractXML.getRecordsFromExtractXML().get(33), "OrigAmt record matches");			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), ExtractXML.getRecordsFromExtractXML().get(33), "OrigAmt Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditExcepCode"), ExtractXML.getRecordsFromExtractXML().get(34), "ExcepCode record matches");			
	    			publishResults(flag, rs.getString("CreditExcepCode"), ExtractXML.getRecordsFromExtractXML().get(34), "ExcepCode Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), ExtractXML.getRecordsFromExtractXML().get(35), "Beneficiary record matches");			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), ExtractXML.getRecordsFromExtractXML().get(35), "Beneficiary Tag Comparison");
	    		    			
	    		}
		
		
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	
	/*
	 * Test Case ID: 71827
	 * Test Description: E2E RnE Scenario Validation  
	 * Author: Himanshu Malhotra
	 * Date: 12th-May-2017
	 * Comments:
	 */
	@Test(priority = 2)
	public void test_case_71827() throws SAXException, IOException, ParserConfigurationException
	{
		try{
			boolean flag;			
			
			//Src Tag Check
			PostingExtractFile.readDataFrom05MA01();
			PostingExtractFile.readDataFrom13MA02();
			
			//Job Run
			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
			Thread.sleep(3000);
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//ExtractXMLFile check
		    
		    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
			ExtractXML.getTagwiseDataForPostingExtract();			
	        ExtractXML.getRecordsFromExtractXML();
	        ResultSet rs=ExtractXML.validatePostingResponseHeaderXMLAttributes();
		
		
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	/*
	 * Test Case ID: 71830
	 * Test Description: E2E RnE Scenario Validation  
	 * Author: Himanshu Malhotra
	 * Date: 12th-May-2017
	 * Comments:
	 */
	@Test(priority = 3)
	public void test_case_71830() throws SAXException, IOException, ParserConfigurationException
	{
		try{
			boolean flag;			
			
			//Src Tag Check
			PostingExtractFile.readDataFrom05MA01();
			PostingExtractFile.readDataFrom13MA02();
			
			//Job Run
			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
			Thread.sleep(3000);
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//ExtractXMLFile check
		    
		    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
			ExtractXML.getTagwiseDataForPostingExtract();			
	        ExtractXML.getRecordsFromExtractXML();
	        ResultSet rs=ExtractXML.validatePostingResponseHeaderXMLAttributes();
		
		
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	/*
	 * Test Case ID: 70656
	 * Test Description: E2E RnE Scenario Validation  
	 * Author: Himanshu Malhotra
	 * Date: 12th-Apr-2017
	 * Comments:
	 */
	@Test(priority = 4)
	public void test_case_70656() throws SAXException, IOException, ParserConfigurationException
	{
		try{
			boolean flag;			
			
			//Src Tag Check
			PostingExtractFile.readDataFrom05MA01();
			PostingExtractFile.readDataFrom13MA02();
			
			//Job Run
			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
			Thread.sleep(3000);
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//ExtractXMLFile check
		    
		    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
			ExtractXML.getTagwiseDataForPostingExtract();			
	        ExtractXML.getRecordsFromExtractXML();
	        ResultSet rs=ExtractXML.validatePostingResponseHeaderXMLAttributes();
		
		
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	/*
	 * Test Case ID: 69895
	 * Test Description: E2E RnE Scenario Validation  
	 * Author: Himanshu Malhotra
	 * Date: 12th-May-2017
	 * Comments:
	 */
	@Test(priority = 5)
	public void test_case_69895() throws SAXException, IOException, ParserConfigurationException
	{
		try{
			boolean flag;			
			
			//Src Tag Check
			PostingExtractFile.readDataFrom05MA01();
			PostingExtractFile.readDataFrom13MA02();
			
			//Job Run
			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
			Thread.sleep(3000);
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//ExtractXMLFile check
		    
		    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
			ExtractXML.getTagwiseDataForPostingExtract();			
	        ExtractXML.getRecordsFromExtractXML();
	        ResultSet rs=ExtractXML.validatePostingResponseHeaderXMLAttributes();
		
		
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	/*
	 * Test Case ID: 69982
	 * Test Description: E2E RnE Scenario Validation  
	 * Author: Himanshu Malhotra
	 * Date: 12th-May-2017
	 * Comments:
	 */
	@Test(priority = 6)
	public void test_case_69982() throws SAXException, IOException, ParserConfigurationException
	{
		try{
			boolean flag;			
			
			//Src Tag Check
			PostingExtractFile.readDataFrom05MA01();
			PostingExtractFile.readDataFrom13MA02();
			
			//Job Run
			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
			Thread.sleep(3000);
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//ExtractXMLFile check
		    
		    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
			ExtractXML.getTagwiseDataForPostingExtract();			
	        ExtractXML.getRecordsFromExtractXML();
	        ResultSet rs=ExtractXML.validatePostingResponseHeaderXMLAttributes();
		
		
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	/*
	 * Test Case ID: 70703
	 * Test Description: E2E RnE Scenario Validation  
	 * Author: Himanshu Malhotra
	 * Date: 12th-May-2017
	 * Comments:
	 */
	@Test(priority = 7)
	public void test_case_70703() throws SAXException, IOException, ParserConfigurationException
	{
		try{
			boolean flag;			
			
			//Src Tag Check
			PostingExtractFile.readDataFrom05MA01();
			PostingExtractFile.readDataFrom13MA02();
			
			//Job Run
			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
			Thread.sleep(3000);
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//ExtractXMLFile check
		    
		    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
			ExtractXML.getTagwiseDataForPostingExtract();			
	        ExtractXML.getRecordsFromExtractXML();
	        ResultSet rs=ExtractXML.validatePostingResponseHeaderXMLAttributes();
		
		
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	
	/*
	 * Test Case ID: 68055
	 * Test Description: E2E RnE Scenario Validation  
	 * Author: Himanshu Malhotra
	 * Date: 12th-May-2017
	 * Comments:
	 */
	@Test(priority = 8)
	public void test_case_68055() throws SAXException, IOException, ParserConfigurationException
	{
		try{
			boolean flag;			
			
			//Src Tag Check
			PostingExtractFile.readDataFrom05MA01();
			PostingExtractFile.readDataFrom13MA02();
			
			//Job Run
			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
			Thread.sleep(3000);
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//ExtractXMLFile check
		    
		    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
			ExtractXML.getTagwiseDataForPostingExtract();			
	        ExtractXML.getRecordsFromExtractXML();
	        ResultSet rs=ExtractXML.validatePostingResponseHeaderXMLAttributes();
		
		
	
		}
		catch(Exception e)
		{
			//publish result with null pointer exception
		}
		
	
	    
	}
	

}
