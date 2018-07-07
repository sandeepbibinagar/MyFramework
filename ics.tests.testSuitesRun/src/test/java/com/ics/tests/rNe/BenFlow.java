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
import com.ics.rNe.xmlFilesDataFetch.DebitDetailFile;
import com.ics.rNe.xmlFilesDataFetch.ExtractXML;
import com.ics.rNe.xmlFilesDataFetch.PERM01File;
import com.ics.rNe.xmlFilesDataFetch.PostingExtractFile;
import com.ics.rNe.xmlFilesDataFetch.PostingExtractFile70656;
import com.ics.rNe.xmlFilesDataFetch.PostingHeaderFile;
import com.ics.rNe.xmlFilesDataFetch.PostingRecordFile;
import com.ics.rNe.xmlFilesDataFetch.RecordBodyFile;
import com.ics.rNe.xmlFilesDataFetch.XSDFile;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;
/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class BenFlow extends ICSAutomationCommonSetup{
	/*
	@Test(priority = 1)
	public void TC_3DEB_1CR_ES280() throws SAXException, IOException, ParserConfigurationException{
		try{
			boolean flag;
			
			PostingExtractFile.readDataFrom05MA01();
			PostingExtractFile.readDataFrom13MA02();
			
			//Job Run
			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
			System.out.println(ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution));
			
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
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
			Thread.sleep(3000);
			
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);
			
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);			
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//Extract FileName Copy to Local and FileNameValidation
			PostingExtractFile.copyExtractFileFromSharedToLocalFolder();
			PostingExtractFile.getAndValidateFileNameFromRootDirectory();
			
			//PERM01 Validations
			PostingExtractFile.getPERM01XML();
			
			    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
		        PostingExtractFile.getTagwiseDataForPostingExtract();			
		        PostingExtractFile.getRecordsFromExtractXML();
		        ResultSet rs=PostingExtractFile.validatePostingResponseHeaderXMLAttributes();
		        
		        while(rs.next()){
		        	flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML().get(0), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML().get(3), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML().get(3), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML().get(5), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML().get(5), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML().get(8), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML().get(8), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML().get(11), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML().get(11), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML().get(14), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML().get(14), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML().get(17), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML().get(17), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML().get(20), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML().get(20), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML().get(23), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML().get(23), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML().get(26), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML().get(26), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML().get(29), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML().get(29), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML().get(32), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML().get(32), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML().get(35), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML().get(35), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML().get(38), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML().get(38), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML().get(41), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML().get(41), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML().get(44), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML().get(44), "Override Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML().get(47), "numCheques record matches");
	    			
	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML().get(47), "numCheques Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML().get(50), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML().get(50), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML().get(53), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML().get(53), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML().get(56), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML().get(56), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(62), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(62), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(68), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(68), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML().get(74), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML().get(74), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML().get(77), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML().get(77), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML().get(83), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML().get(83), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML().get(86), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML().get(86), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML().get(89), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML().get(89), "SwitchAcc Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML().get(57), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML().get(57), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(63), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(63), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(69), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(69), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML().get(92), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML().get(92), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML().get(78), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML().get(78), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML().get(95), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML().get(95), "OrigAmt Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML().get(98), "Exception record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML().get(98), "Exception Comparison");
	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML().get(101), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML().get(101), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			
	    			
	    			//Set2
	    			/*
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML().get(1), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML().get(1), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML().get(4), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML().get(4), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML().get(6), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML().get(6), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML().get(9), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML().get(9), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML().get(12), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML().get(12), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML().get(15), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML().get(15), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML().get(18), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML().get(18), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML().get(21), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML().get(21), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML().get(24), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML().get(24), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML().get(27), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML().get(27), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML().get(30), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML().get(30), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML().get(33), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML().get(33), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML().get(36), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML().get(36), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML().get(39), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML().get(39), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML().get(42), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML().get(42), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML().get(45), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML().get(45), "Override Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML().get(48), "numCheques record matches");
	    			
	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML().get(48), "numCheques Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML().get(51), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML().get(51), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML().get(54), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML().get(54), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML().get(58), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML().get(58), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(64), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(64), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(70), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(70), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML().get(75), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML().get(75), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML().get(79), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML().get(79), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML().get(84), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML().get(84), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML().get(87), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML().get(87), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML().get(90), "FullAmount record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML().get(90), "FullAmount Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML().get(59), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML().get(59), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(65), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(65), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(71), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(71), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML().get(93), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML().get(93), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML().get(80), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML().get(80), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML().get(96), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML().get(96), "OrigAmt Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML().get(99), "Exception record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML().get(99), "Exception Comparison");
	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML().get(102), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML().get(102), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			
	    			
	    			
	    			
	    			//set 3
	    			

	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML().get(2), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML().get(2), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML().get(104), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML().get(104), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML().get(7), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML().get(7), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML().get(10), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML().get(10), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML().get(13), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML().get(13), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML().get(16), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML().get(16), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML().get(19), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML().get(19), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML().get(22), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML().get(22), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML().get(25), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML().get(25), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML().get(28), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML().get(28), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML().get(31), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML().get(31), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML().get(34), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML().get(34), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML().get(37), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML().get(37), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML().get(40), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML().get(40), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML().get(43), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML().get(43), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML().get(46), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML().get(46), "Override Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML().get(49), "numCheques record matches");
	    			
	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML().get(49), "numCheques Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML().get(52), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML().get(52), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML().get(55), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML().get(55), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML().get(60), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML().get(60), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(66), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(66), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(72), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(72), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML().get(76), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML().get(76), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML().get(81), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML().get(81), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML().get(85), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML().get(85), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML().get(88), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML().get(88), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML().get(91), "FullAmount record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML().get(91), "FullAmount Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML().get(61), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML().get(61), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(67), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML().get(67), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(73), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML().get(73), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML().get(94), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML().get(94), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML().get(82), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML().get(82), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML().get(97), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML().get(97), "OrigAmt Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML().get(100), "Exception record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML().get(100), "Exception Comparison");
	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML().get(103), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML().get(103), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
		        }
	       

			
			
		}
		catch(Exception e){
			
		}
	}
	*/
	
	@Test(priority = 1)
	public void TC_1DEB_1CR_CRE_ES280() throws SAXException, IOException, ParserConfigurationException{
		try{
			boolean flag;
			/*
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
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
			Thread.sleep(3000);
			
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);
			
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);			
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//Extract FileName Copy to Local and FileNameValidation
			PostingExtractFile.copyExtractFileFromSharedToLocalFolder();
			PostingExtractFile.getAndValidateFileNameFromRootDirectory();
			
			//PERM01 Validations
			PostingExtractFile.getPERM01XML();
			
			*/
			
			    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
		        //PostingExtractFile.getTagwiseDataForPostingExtract();			
		        PostingExtractFile.getRecordsFromExtractXML1DR1CR();
		        ResultSet rs=PostingExtractFile.validatePostingResponseHeaderXMLAttributes();
		        
		        while(rs.next()){
		        	flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(3), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(3), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(5), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(5), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(11), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(11), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(13), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(13), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(14), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(14), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15), "Override Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(16), "numCheques record matches");
	    			
	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(16), "numCheques Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(28), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(28), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(29), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(29), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(30), "Full Amt record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(30), "Full Amt Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(24), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(24), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(27), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(27), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(32), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(32), "OrigAmt Comparison");
//	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(98), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(98), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			 //Get PERM01 Tags
	    			
		            PERM01File.getPERM01Details();
		            PostingExtractFile.getRecordsFromExtractXML();
		           
		            //Validate PERM01 tags
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0),PERM01File.getPERM01Details().get(0),"Sequence Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0), PERM01File.getPERM01Details().get(0), "Sequence Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1),PERM01File.getPERM01Details().get(1),"Post Type Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1), PERM01File.getPERM01Details().get(1), "PostType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2),PERM01File.getPERM01Details().get(2),"PostSubType Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2), PERM01File.getPERM01Details().get(2), "PostSubType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4),PERM01File.getPERM01Details().get(3),"channel Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4), PERM01File.getPERM01Details().get(3), "channel Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6),PERM01File.getPERM01Details().get(6),"PostingSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6), PERM01File.getPERM01Details().get(6), "PostingSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7),PERM01File.getPERM01Details().get(7),"RespIdSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7), PERM01File.getPERM01Details().get(7), "RespIdSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8),PERM01File.getPERM01Details().get(8),"PostingDay Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8), PERM01File.getPERM01Details().get(8), "PostingDay Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9),PERM01File.getPERM01Details().get(9),"ClearDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9), PERM01File.getPERM01Details().get(9), "ClearDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10),PERM01File.getPERM01Details().get(10),"SettlementDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10), PERM01File.getPERM01Details().get(10), "SettlementDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12),PERM01File.getPERM01Details().get(12),"PostedAmount Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12), PERM01File.getPERM01Details().get(12), "PostedAmount Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15),PERM01File.getPERM01Details().get(15),"Override Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15), PERM01File.getPERM01Details().get(15), "Override Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17),PERM01File.getPERM01Details().get(17),"CollPart Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17), PERM01File.getPERM01Details().get(17), "CollPart Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18),PERM01File.getPERM01Details().get(18),"Coll Location Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18), PERM01File.getPERM01Details().get(18), "Coll Location Tag Comparison");
		            
		            //Debit Elements
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19),PERM01File.getPERM01Details().get(19),"Debit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19), PERM01File.getPERM01Details().get(19), "Debit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21),PERM01File.getPERM01Details().get(21),"Debit SortCode Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21), PERM01File.getPERM01Details().get(21), "Debit SortCode Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23),PERM01File.getPERM01Details().get(23),"Account Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), PERM01File.getPERM01Details().get(23), "Account Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25),PERM01File.getPERM01Details().get(25),"Serial Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25), PERM01File.getPERM01Details().get(25), "Serial Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26),PERM01File.getPERM01Details().get(26),"Tran Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26), PERM01File.getPERM01Details().get(26), "Tran Code Tag Comparison");
		            
		            //Credit Elements
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20),PERM01File.getPERM01Details().get(20),"Credit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20), PERM01File.getPERM01Details().get(20), "Credit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22),PERM01File.getPERM01Details().get(22),"Sort Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22), PERM01File.getPERM01Details().get(22), "Sort Code Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23),PERM01File.getPERM01Details().get(24),"Acc Num Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), PERM01File.getPERM01Details().get(24), "Acc Num Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31),PERM01File.getPERM01Details().get(31),"Ref Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31), PERM01File.getPERM01Details().get(31), "Ref Tag Comparison");
		                 
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33),PERM01File.getPERM01Details().get(33),"beneficiary Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33), PERM01File.getPERM01Details().get(33), "beneficiary Tag Comparison");
		                
	    			
		        }}
	
	catch(Exception e){
		
	}
		}
	/*
	@Test(priority = 2)
	public void TC_1DEB_1CR_ES280_CD_Y_LDC() throws SAXException, IOException, ParserConfigurationException{
		try{
			boolean flag;
			/*
			PostingExtractFile.readDataFrom05MA01();
			PostingExtractFile.readDataFrom13MA02();
			
			//Job Run
			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
			System.out.println(ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution));
			
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
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
			Thread.sleep(3000);
			PostingExtractFile.compareRowCount1();
			
			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
			Thread.sleep(3000);
			
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);
			
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);			
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//Extract FileName Copy to Local and FileNameValidation
			PostingExtractFile.copyExtractFileFromSharedToLocalFolder();
			PostingExtractFile.getAndValidateFileNameFromRootDirectory();
			
			//PERM01 Validations
			PostingExtractFile.getPERM01XML();
			
			    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
		        //PostingExtractFile.getTagwiseDataForPostingExtract();			
		        PostingExtractFile.getRecordsFromExtractXML1DR1CR();
		        ResultSet rs=PostingExtractFile.validatePostingResponseHeaderXMLAttributes();
		        
		        while(rs.next()){
		        	flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(3), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(3), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(5), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(5), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(11), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(11), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(13), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(13), "TranSetID Tag Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(14), "Reason record matches");
//	    			
//	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(14), "Reason Tag Comparison");
//	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15), "Override Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(16), "numCheques record matches");
	    			
	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(16), "numCheques Tag Comparison");
	    			
	    			
//	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17), "CollPart record matches");
//	    			
//	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17), "CollPart Tag Comparison");
//	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(28), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(28), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(29), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(29), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(29), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(29), "SwitchAcc Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(24), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(24), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(27), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(27), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(32), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(32), "OrigAmt Comparison");
//	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(98), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(98), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
 //Get PERM01 Tags
	    			
		            PERM01File.getPERM01Details();
		            PostingExtractFile.getRecordsFromExtractXML();
		           
		            //Validate PERM01 tags
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0),PERM01File.getPERM01Details().get(0),"Sequence Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0), PERM01File.getPERM01Details().get(0), "Sequence Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1),PERM01File.getPERM01Details().get(1),"Post Type Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1), PERM01File.getPERM01Details().get(1), "PostType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2),PERM01File.getPERM01Details().get(2),"PostSubType Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2), PERM01File.getPERM01Details().get(2), "PostSubType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4),PERM01File.getPERM01Details().get(3),"channel Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4), PERM01File.getPERM01Details().get(3), "channel Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6),PERM01File.getPERM01Details().get(6),"PostingSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6), PERM01File.getPERM01Details().get(6), "PostingSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7),PERM01File.getPERM01Details().get(7),"RespIdSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7), PERM01File.getPERM01Details().get(7), "RespIdSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8),PERM01File.getPERM01Details().get(8),"PostingDay Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8), PERM01File.getPERM01Details().get(8), "PostingDay Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9),PERM01File.getPERM01Details().get(9),"ClearDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9), PERM01File.getPERM01Details().get(9), "ClearDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10),PERM01File.getPERM01Details().get(10),"SettlementDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10), PERM01File.getPERM01Details().get(10), "SettlementDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12),PERM01File.getPERM01Details().get(12),"PostedAmount Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12), PERM01File.getPERM01Details().get(12), "PostedAmount Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15),PERM01File.getPERM01Details().get(15),"Override Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15), PERM01File.getPERM01Details().get(15), "Override Tag Comparison");
		            
//		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17),PERM01File.getPERM01Details().get(17),"CollPart Validation");
//		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17), PERM01File.getPERM01Details().get(17), "CollPart Tag Comparison");
//		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18),PERM01File.getPERM01Details().get(18),"Coll Location Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18), PERM01File.getPERM01Details().get(18), "Coll Location Tag Comparison");
		            
		            //Debit Elements
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19),PERM01File.getPERM01Details().get(19),"Debit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19), PERM01File.getPERM01Details().get(19), "Debit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21),PERM01File.getPERM01Details().get(21),"Debit SortCode Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21), PERM01File.getPERM01Details().get(21), "Debit SortCode Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23),PERM01File.getPERM01Details().get(23),"Account Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), PERM01File.getPERM01Details().get(23), "Account Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25),PERM01File.getPERM01Details().get(25),"Serial Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25), PERM01File.getPERM01Details().get(25), "Serial Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26),PERM01File.getPERM01Details().get(26),"Tran Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26), PERM01File.getPERM01Details().get(26), "Tran Code Tag Comparison");
		            
		            //Credit Elements
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20),PERM01File.getPERM01Details().get(20),"Credit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20), PERM01File.getPERM01Details().get(20), "Credit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22),PERM01File.getPERM01Details().get(22),"Sort Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22), PERM01File.getPERM01Details().get(22), "Sort Code Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23),PERM01File.getPERM01Details().get(24),"Acc Num Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), PERM01File.getPERM01Details().get(24), "Acc Num Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31),PERM01File.getPERM01Details().get(31),"Ref Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31), PERM01File.getPERM01Details().get(31), "Ref Tag Comparison");
		                 
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33),PERM01File.getPERM01Details().get(33),"beneficiary Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33), PERM01File.getPERM01Details().get(33), "beneficiary Tag Comparison");
		                
		        }}
	
	catch(Exception e){
		
	}
		}
	
	
	@Test(priority = 2)
	public void TC_1DEB_1CR_ES280_CD_N_CNPandCRE() throws SAXException, IOException, ParserConfigurationException{
		try{
			boolean flag;
			
//			PostingExtractFile.readDataFrom05MA01();
//			PostingExtractFile.readDataFrom13MA02();
//			
//			//Job Run
//			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
//			System.out.println(ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution));
//			
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
//			Thread.sleep(3000);
//			
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);
//			
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);			
//			
//			//PTMR01 Status "C" check
//			PostingExtractFile.validatePTMR01Status();
//			
//			//Extract FileName Copy to Local and FileNameValidation
//			PostingExtractFile.copyExtractFileFromSharedToLocalFolder();
//			PostingExtractFile.getAndValidateFileNameFromRootDirectory();
//			
//			//PERM01 Validations
//			PostingExtractFile.getPERM01XML();
//			
//			    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
//		        PostingExtractFile.getTagwiseDataForPostingExtract();			
		        PostingExtractFile.getRecordsFromExtractXML();
		        ResultSet rs=PostingExtractFile.validatePostingResponseHeaderXMLAttributes();
		        
		        while(rs.next()){
		        	flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(14), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(14), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(24), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(24), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(28), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(28), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(30), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(30), "Override Tag Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "numCheques record matches");
//	    			
//	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "numCheques Tag Comparison");
//	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(34), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(34), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(36), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(36), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(38), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(38), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(42), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(42), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(46), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(46), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(50), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(50), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(52), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(52), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(56), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(56), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(58), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(58), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(60), "FullAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(60), "FullAmt Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(39), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(39), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(43), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(43), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(47), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(47), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(62), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(62), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(53), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(53), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(64), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(64), "OrigAmt Comparison");
//	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(98), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(98), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(66), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(66), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			
	    			//Set 2
	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(3), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(3), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(5), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(5), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(11), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(11), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(27), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(27), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(29), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(29), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), "Override Tag Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "numCheques record matches");
//	    			
//	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "numCheques Tag Comparison");
//	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(35), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(35), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(37), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(37), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(40), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(40), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(44), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(44), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(51), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(51), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(54), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(54), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(57), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(57), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(59), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(59), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(61), "FullAmount record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(61), "FullAmount Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(41), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(41), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(45), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(45), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(49), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(49), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(63), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(63), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(55), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(55), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(65), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(65), "OrigAmt Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(99), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(99), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(67), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(67), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			
	    			
                    //Get PERM01 Tags
	    			
		            PERM01File.getPERM01Details();
		            PostingExtractFile.getRecordsFromExtractXML();
		           
		            //Validate PERM01 tags
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0),PERM01File.getPERM01Details().get(0),"Sequence Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), PERM01File.getPERM01Details().get(0), "Sequence Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1),PERM01File.getPERM01Details().get(1),"Post Type Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), PERM01File.getPERM01Details().get(1), "PostType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2),PERM01File.getPERM01Details().get(2),"PostSubType Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), PERM01File.getPERM01Details().get(2), "PostSubType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4),PERM01File.getPERM01Details().get(3),"channel Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), PERM01File.getPERM01Details().get(3), "channel Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6),PERM01File.getPERM01Details().get(6),"PostingSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), PERM01File.getPERM01Details().get(6), "PostingSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7),PERM01File.getPERM01Details().get(7),"RespIdSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), PERM01File.getPERM01Details().get(7), "RespIdSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8),PERM01File.getPERM01Details().get(8),"PostingDay Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), PERM01File.getPERM01Details().get(8), "PostingDay Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9),PERM01File.getPERM01Details().get(9),"ClearDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), PERM01File.getPERM01Details().get(9), "ClearDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10),PERM01File.getPERM01Details().get(10),"SettlementDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), PERM01File.getPERM01Details().get(10), "SettlementDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12),PERM01File.getPERM01Details().get(12),"PostedAmount Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), PERM01File.getPERM01Details().get(12), "PostedAmount Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15),PERM01File.getPERM01Details().get(15),"Override Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), PERM01File.getPERM01Details().get(15), "Override Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17),PERM01File.getPERM01Details().get(17),"CollPart Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), PERM01File.getPERM01Details().get(17), "CollPart Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18),PERM01File.getPERM01Details().get(18),"Coll Location Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), PERM01File.getPERM01Details().get(18), "Coll Location Tag Comparison");
		            
		            //Debit Elements
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19),PERM01File.getPERM01Details().get(19),"Debit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), PERM01File.getPERM01Details().get(19), "Debit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21),PERM01File.getPERM01Details().get(21),"Debit SortCode Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), PERM01File.getPERM01Details().get(21), "Debit SortCode Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23),PERM01File.getPERM01Details().get(23),"Account Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), PERM01File.getPERM01Details().get(23), "Account Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25),PERM01File.getPERM01Details().get(25),"Serial Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), PERM01File.getPERM01Details().get(25), "Serial Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26),PERM01File.getPERM01Details().get(26),"Tran Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), PERM01File.getPERM01Details().get(26), "Tran Code Tag Comparison");
		            
		            //Credit Elements
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20),PERM01File.getPERM01Details().get(20),"Credit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), PERM01File.getPERM01Details().get(20), "Credit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22),PERM01File.getPERM01Details().get(22),"Sort Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), PERM01File.getPERM01Details().get(22), "Sort Code Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23),PERM01File.getPERM01Details().get(24),"Acc Num Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), PERM01File.getPERM01Details().get(24), "Acc Num Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31),PERM01File.getPERM01Details().get(31),"Ref Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), PERM01File.getPERM01Details().get(31), "Ref Tag Comparison");
		                 
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(33),PERM01File.getPERM01Details().get(33),"beneficiary Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(33), PERM01File.getPERM01Details().get(33), "beneficiary Tag Comparison");
		                
		            
	    			
		        }}
	
	catch(Exception e){
		
	}
		}
	
	@Test(priority = 2)
	public void TC_1DEB_MultiCR_ES280_CO_Y_CRE() throws SAXException, IOException, ParserConfigurationException{
		try{
			boolean flag;
			
//			PostingExtractFile.readDataFrom05MA01();
//			PostingExtractFile.readDataFrom13MA02();
//			
//			//Job Run
//			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
//			System.out.println(ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution));
//			
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
//			Thread.sleep(3000);
//			
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);
//			
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);			
//			
//			//PTMR01 Status "C" check
//			PostingExtractFile.validatePTMR01Status();
//			
//			//Extract FileName Copy to Local and FileNameValidation
//			PostingExtractFile.copyExtractFileFromSharedToLocalFolder();
//			PostingExtractFile.getAndValidateFileNameFromRootDirectory();
//			
//			//PERM01 Validations
//			PostingExtractFile.getPERM01XML();
//			
//			    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
//		        PostingExtractFile.getTagwiseDataForPostingExtract();			
		        PostingExtractFile.getRecordsFromExtractXML();
		        ResultSet rs=PostingExtractFile.validatePostingResponseHeaderXMLAttributes();
		        
		        while(rs.next()){
		        	flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(14), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(14), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(24), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(24), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(28), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(28), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(30), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(30), "Override Tag Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "numCheques record matches");
//	    			
//	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "numCheques Tag Comparison");
//	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(34), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(34), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(36), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(36), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(38), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(38), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(42), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(42), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(46), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(46), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(50), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(50), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(52), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(52), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(56), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(56), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(58), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(58), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(60), "FullAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(60), "FullAmt Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(39), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(39), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(43), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(43), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(47), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(47), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(62), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(62), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(53), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(53), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(64), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(64), "OrigAmt Comparison");
//	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(98), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(98), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(66), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(66), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			
	    			//Set 2
	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(3), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(3), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(5), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(5), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(11), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(11), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(27), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(27), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(29), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(29), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), "Override Tag Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "numCheques record matches");
//	    			
//	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "numCheques Tag Comparison");
//	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(35), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(35), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(37), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(37), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(40), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(40), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(44), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(44), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(51), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(51), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(54), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(54), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(57), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(57), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(59), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(59), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(61), "FullAmount record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(61), "FullAmount Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(41), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(41), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(45), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(45), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(49), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(49), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(63), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(63), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(55), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(55), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(65), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(65), "OrigAmt Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(99), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(99), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(67), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(67), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			
	    			
                    //Get PERM01 Tags
	    			
		            PERM01File.getPERM01Details();
		            PostingExtractFile.getRecordsFromExtractXML();
		           
		            //Validate PERM01 tags
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0),PERM01File.getPERM01Details().get(0),"Sequence Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), PERM01File.getPERM01Details().get(0), "Sequence Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1),PERM01File.getPERM01Details().get(1),"Post Type Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), PERM01File.getPERM01Details().get(1), "PostType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2),PERM01File.getPERM01Details().get(2),"PostSubType Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), PERM01File.getPERM01Details().get(2), "PostSubType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4),PERM01File.getPERM01Details().get(3),"channel Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), PERM01File.getPERM01Details().get(3), "channel Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6),PERM01File.getPERM01Details().get(6),"PostingSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), PERM01File.getPERM01Details().get(6), "PostingSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7),PERM01File.getPERM01Details().get(7),"RespIdSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), PERM01File.getPERM01Details().get(7), "RespIdSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8),PERM01File.getPERM01Details().get(8),"PostingDay Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), PERM01File.getPERM01Details().get(8), "PostingDay Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9),PERM01File.getPERM01Details().get(9),"ClearDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), PERM01File.getPERM01Details().get(9), "ClearDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10),PERM01File.getPERM01Details().get(10),"SettlementDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), PERM01File.getPERM01Details().get(10), "SettlementDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12),PERM01File.getPERM01Details().get(12),"PostedAmount Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), PERM01File.getPERM01Details().get(12), "PostedAmount Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15),PERM01File.getPERM01Details().get(15),"Override Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), PERM01File.getPERM01Details().get(15), "Override Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17),PERM01File.getPERM01Details().get(17),"CollPart Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), PERM01File.getPERM01Details().get(17), "CollPart Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18),PERM01File.getPERM01Details().get(18),"Coll Location Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), PERM01File.getPERM01Details().get(18), "Coll Location Tag Comparison");
		            
		            //Debit Elements
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19),PERM01File.getPERM01Details().get(19),"Debit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), PERM01File.getPERM01Details().get(19), "Debit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21),PERM01File.getPERM01Details().get(21),"Debit SortCode Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), PERM01File.getPERM01Details().get(21), "Debit SortCode Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23),PERM01File.getPERM01Details().get(23),"Account Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), PERM01File.getPERM01Details().get(23), "Account Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25),PERM01File.getPERM01Details().get(25),"Serial Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), PERM01File.getPERM01Details().get(25), "Serial Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26),PERM01File.getPERM01Details().get(26),"Tran Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), PERM01File.getPERM01Details().get(26), "Tran Code Tag Comparison");
		            
		            //Credit Elements
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20),PERM01File.getPERM01Details().get(20),"Credit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), PERM01File.getPERM01Details().get(20), "Credit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22),PERM01File.getPERM01Details().get(22),"Sort Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), PERM01File.getPERM01Details().get(22), "Sort Code Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23),PERM01File.getPERM01Details().get(24),"Acc Num Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), PERM01File.getPERM01Details().get(24), "Acc Num Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31),PERM01File.getPERM01Details().get(31),"Ref Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), PERM01File.getPERM01Details().get(31), "Ref Tag Comparison");
		                 
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(33),PERM01File.getPERM01Details().get(33),"beneficiary Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(33), PERM01File.getPERM01Details().get(33), "beneficiary Tag Comparison");
		                
		            
	    			
		        }}
	
	catch(Exception e){
		
	}
		}
	
	
	
	@Test(priority = 2)
	public void TC_1DEB_MultiCR_ES280_CO_N_LDC() throws SAXException, IOException, ParserConfigurationException{
		try{
			boolean flag;
			
//			PostingExtractFile.readDataFrom05MA01();
//			PostingExtractFile.readDataFrom13MA02();
//			
//			//Job Run
//			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
//			System.out.println(ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution));
//			
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
//			Thread.sleep(3000);
//			
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);
//			
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);			
//			
//			//PTMR01 Status "C" check
//			PostingExtractFile.validatePTMR01Status();
//			
//			//Extract FileName Copy to Local and FileNameValidation
//			PostingExtractFile.copyExtractFileFromSharedToLocalFolder();
//			PostingExtractFile.getAndValidateFileNameFromRootDirectory();
//			
//			//PERM01 Validations
//			PostingExtractFile.getPERM01XML();
//			
//			    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
//		        PostingExtractFile.getTagwiseDataForPostingExtract();			
		        PostingExtractFile.getRecordsFromExtractXML();
		        ResultSet rs=PostingExtractFile.validatePostingResponseHeaderXMLAttributes();
		        
		        while(rs.next()){
		        	flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(14), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(14), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(24), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(24), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(28), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(28), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(30), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(30), "Override Tag Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "numCheques record matches");
//	    			
//	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "numCheques Tag Comparison");
//	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(34), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(34), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(36), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(36), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(38), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(38), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(42), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(42), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(46), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(46), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(50), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(50), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(52), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(52), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(56), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(56), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(58), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(58), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(60), "FullAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(60), "FullAmt Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(39), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(39), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(43), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(43), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(47), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(47), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(62), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(62), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(53), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(53), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(64), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(64), "OrigAmt Comparison");
//	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(98), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(98), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(66), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(66), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			
	    			//Set 2
	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(3), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(3), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(5), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(5), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(11), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(11), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(27), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(27), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(29), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(29), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), "Override Tag Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "numCheques record matches");
//	    			
//	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "numCheques Tag Comparison");
//	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(35), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(35), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(37), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(37), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(40), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(40), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(44), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(44), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(51), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(51), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(54), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(54), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(57), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(57), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(59), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(59), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(61), "FullAmount record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(61), "FullAmount Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(41), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(41), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(45), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(45), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(49), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(49), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(63), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(63), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(55), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(55), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(65), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(65), "OrigAmt Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(99), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(99), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(67), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(67), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			
	    			
                    //Get PERM01 Tags
	    			
		            PERM01File.getPERM01Details();
		            PostingExtractFile.getRecordsFromExtractXML();
		           
		            //Validate PERM01 tags
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0),PERM01File.getPERM01Details().get(0),"Sequence Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), PERM01File.getPERM01Details().get(0), "Sequence Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1),PERM01File.getPERM01Details().get(1),"Post Type Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), PERM01File.getPERM01Details().get(1), "PostType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2),PERM01File.getPERM01Details().get(2),"PostSubType Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), PERM01File.getPERM01Details().get(2), "PostSubType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4),PERM01File.getPERM01Details().get(3),"channel Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), PERM01File.getPERM01Details().get(3), "channel Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6),PERM01File.getPERM01Details().get(6),"PostingSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), PERM01File.getPERM01Details().get(6), "PostingSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7),PERM01File.getPERM01Details().get(7),"RespIdSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), PERM01File.getPERM01Details().get(7), "RespIdSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8),PERM01File.getPERM01Details().get(8),"PostingDay Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), PERM01File.getPERM01Details().get(8), "PostingDay Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9),PERM01File.getPERM01Details().get(9),"ClearDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), PERM01File.getPERM01Details().get(9), "ClearDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10),PERM01File.getPERM01Details().get(10),"SettlementDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), PERM01File.getPERM01Details().get(10), "SettlementDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12),PERM01File.getPERM01Details().get(12),"PostedAmount Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), PERM01File.getPERM01Details().get(12), "PostedAmount Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15),PERM01File.getPERM01Details().get(15),"Override Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), PERM01File.getPERM01Details().get(15), "Override Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17),PERM01File.getPERM01Details().get(17),"CollPart Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), PERM01File.getPERM01Details().get(17), "CollPart Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18),PERM01File.getPERM01Details().get(18),"Coll Location Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), PERM01File.getPERM01Details().get(18), "Coll Location Tag Comparison");
		            
		            //Debit Elements
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19),PERM01File.getPERM01Details().get(19),"Debit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), PERM01File.getPERM01Details().get(19), "Debit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21),PERM01File.getPERM01Details().get(21),"Debit SortCode Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), PERM01File.getPERM01Details().get(21), "Debit SortCode Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23),PERM01File.getPERM01Details().get(23),"Account Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), PERM01File.getPERM01Details().get(23), "Account Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25),PERM01File.getPERM01Details().get(25),"Serial Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), PERM01File.getPERM01Details().get(25), "Serial Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26),PERM01File.getPERM01Details().get(26),"Tran Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), PERM01File.getPERM01Details().get(26), "Tran Code Tag Comparison");
		            
		            //Credit Elements
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20),PERM01File.getPERM01Details().get(20),"Credit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), PERM01File.getPERM01Details().get(20), "Credit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22),PERM01File.getPERM01Details().get(22),"Sort Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), PERM01File.getPERM01Details().get(22), "Sort Code Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23),PERM01File.getPERM01Details().get(24),"Acc Num Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), PERM01File.getPERM01Details().get(24), "Acc Num Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31),PERM01File.getPERM01Details().get(31),"Ref Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), PERM01File.getPERM01Details().get(31), "Ref Tag Comparison");
		                 
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(33),PERM01File.getPERM01Details().get(33),"beneficiary Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(33), PERM01File.getPERM01Details().get(33), "beneficiary Tag Comparison");
		                
		            
	    			
		        }}
	
	catch(Exception e){
		
	}
		}

	@Test(priority = 2)
	public void TC_1DEB_MultiCR_ES280_CO_N_AltBen_Y_CNPandCRE() throws SAXException, IOException, ParserConfigurationException{
		try{
			boolean flag;
			
//			PostingExtractFile.readDataFrom05MA01();
//			PostingExtractFile.readDataFrom13MA02();
//			
//			//Job Run
//			PostingExtractFile.initialSize = PostingExtractFile.getRowCountFromBaseCoreTable1();
//			System.out.println(ICSDBUtilis.sqlCommandExecution(PostingExtractFile.firstMessageExecution));
//			
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.secondMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.thirdMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fourthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.fifthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
//			Thread.sleep(3000);
//			
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);
//			
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);
//			PostingExtractFile.validatePTMR01Refresh();
//			Thread.sleep(3000);			
//			
//			//PTMR01 Status "C" check
//			PostingExtractFile.validatePTMR01Status();
//			
//			//Extract FileName Copy to Local and FileNameValidation
//			PostingExtractFile.copyExtractFileFromSharedToLocalFolder();
//			PostingExtractFile.getAndValidateFileNameFromRootDirectory();
//			
//			//PERM01 Validations
//			PostingExtractFile.getPERM01XML();
//			
//			    PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
//		        PostingExtractFile.getTagwiseDataForPostingExtract();			
		        PostingExtractFile.getRecordsFromExtractXML();
		        ResultSet rs=PostingExtractFile.validatePostingResponseHeaderXMLAttributes();
		        
		        while(rs.next()){
		        	flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(14), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(14), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(24), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(24), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(28), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(28), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(30), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(30), "Override Tag Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "numCheques record matches");
//	    			
//	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(16), "numCheques Tag Comparison");
//	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(34), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(34), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(36), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(36), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(38), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(38), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(42), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(42), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(46), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(46), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(50), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(50), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(52), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(52), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(56), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(56), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(58), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(58), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(60), "FullAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(60), "FullAmt Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(39), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(39), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(43), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(43), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(47), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(47), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(62), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(62), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(53), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(53), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(64), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(64), "OrigAmt Comparison");
//	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(98), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(98), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(66), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(66), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			
	    			//Set 2
	    			flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(3), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(3), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(5), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(5), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(11), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(11), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(27), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(27), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(29), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(29), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), "Override Tag Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "numCheques record matches");
//	    			
//	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "numCheques Tag Comparison");
//	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(35), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(35), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(37), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(37), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(40), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(40), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(44), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(44), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(48), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(51), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(51), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(54), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(54), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(57), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(57), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(59), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(59), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(61), "FullAmount record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(61), "FullAmount Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(41), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(41), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(45), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(45), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(49), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(49), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(63), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(63), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(55), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(55), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(65), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(65), "OrigAmt Comparison");
	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(99), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(99), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(67), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(67), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			
	    			
                    //Get PERM01 Tags
	    			
		            PERM01File.getPERM01Details();
		            PostingExtractFile.getRecordsFromExtractXML();
		           
		            //Validate PERM01 tags
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0),PERM01File.getPERM01Details().get(0),"Sequence Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(0), PERM01File.getPERM01Details().get(0), "Sequence Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1),PERM01File.getPERM01Details().get(1),"Post Type Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(1), PERM01File.getPERM01Details().get(1), "PostType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2),PERM01File.getPERM01Details().get(2),"PostSubType Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(2), PERM01File.getPERM01Details().get(2), "PostSubType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4),PERM01File.getPERM01Details().get(3),"channel Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(4), PERM01File.getPERM01Details().get(3), "channel Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6),PERM01File.getPERM01Details().get(6),"PostingSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(6), PERM01File.getPERM01Details().get(6), "PostingSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7),PERM01File.getPERM01Details().get(7),"RespIdSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(7), PERM01File.getPERM01Details().get(7), "RespIdSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8),PERM01File.getPERM01Details().get(8),"PostingDay Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(8), PERM01File.getPERM01Details().get(8), "PostingDay Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9),PERM01File.getPERM01Details().get(9),"ClearDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(9), PERM01File.getPERM01Details().get(9), "ClearDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10),PERM01File.getPERM01Details().get(10),"SettlementDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(10), PERM01File.getPERM01Details().get(10), "SettlementDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12),PERM01File.getPERM01Details().get(12),"PostedAmount Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(12), PERM01File.getPERM01Details().get(12), "PostedAmount Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15),PERM01File.getPERM01Details().get(15),"Override Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(15), PERM01File.getPERM01Details().get(15), "Override Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17),PERM01File.getPERM01Details().get(17),"CollPart Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(17), PERM01File.getPERM01Details().get(17), "CollPart Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18),PERM01File.getPERM01Details().get(18),"Coll Location Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(18), PERM01File.getPERM01Details().get(18), "Coll Location Tag Comparison");
		            
		            //Debit Elements
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19),PERM01File.getPERM01Details().get(19),"Debit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(19), PERM01File.getPERM01Details().get(19), "Debit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21),PERM01File.getPERM01Details().get(21),"Debit SortCode Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(21), PERM01File.getPERM01Details().get(21), "Debit SortCode Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23),PERM01File.getPERM01Details().get(23),"Account Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), PERM01File.getPERM01Details().get(23), "Account Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25),PERM01File.getPERM01Details().get(25),"Serial Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(25), PERM01File.getPERM01Details().get(25), "Serial Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26),PERM01File.getPERM01Details().get(26),"Tran Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(26), PERM01File.getPERM01Details().get(26), "Tran Code Tag Comparison");
		            
		            //Credit Elements
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20),PERM01File.getPERM01Details().get(20),"Credit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(20), PERM01File.getPERM01Details().get(20), "Credit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22),PERM01File.getPERM01Details().get(22),"Sort Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(22), PERM01File.getPERM01Details().get(22), "Sort Code Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23),PERM01File.getPERM01Details().get(24),"Acc Num Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(23), PERM01File.getPERM01Details().get(24), "Acc Num Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31),PERM01File.getPERM01Details().get(31),"Ref Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(31), PERM01File.getPERM01Details().get(31), "Ref Tag Comparison");
		                 
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(33),PERM01File.getPERM01Details().get(33),"beneficiary Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML2Records1DR1CR().get(33), PERM01File.getPERM01Details().get(33), "beneficiary Tag Comparison");
		                
		            
	    			
		        }}
	
	catch(Exception e){
		
	}
		}

*/
	
	@Test(priority = 1)
	public void TC_1DEB_1CR_CNP_ES260() throws SAXException, IOException, ParserConfigurationException{
		try{
			boolean flag;
			
			String a = GenericMethodUtilis.getRnEValueFromDataSheet("05ma01Path");
			System.out.println(a);
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
			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.sixthMessageExecution);
//			Thread.sleep(3000);
//			PostingExtractFile.compareRowCount1();
//			
//			PostingExtractFile.initialSize =PostingExtractFile.getRowCountFromBaseCoreTable1();
//			ICSDBUtilis.sqlCommandExecution(PostingExtractFile.seventhMessageExecution);
//			Thread.sleep(3000);
			
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);
			
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);
			PostingExtractFile.validatePTMR01Refresh();
			Thread.sleep(3000);			
			
			//PTMR01 Status "C" check
			PostingExtractFile.validatePTMR01Status();
			
			//Extract FileName Copy to Local and FileNameValidation
			PostingExtractFile.copyExtractFileFromSharedToLocalFolder();
			PostingExtractFile.getAndValidateFileNameFromRootDirectory();
			
			//PERM01 Validations
			PostingExtractFile.getPERM01XML();			
					
			   // PostingExtractFile.validateRnEMOQueueDetailsTableFileID();
		        //PostingExtractFile.getTagwiseDataForPostingExtract();			
		        PostingExtractFile.getRecordsFromExtractXML1DR1CR();
		        ResultSet rs=PostingExtractFile.validatePostingResponseHeaderXMLAttributes();
		        
		        while(rs.next()){
		        	flag= Component.verifyEquals(rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0), "SequenceR record matches");	    			
	    			publishResults(flag, rs.getString("RecordSequence"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0), "Sequence Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1), "PostType record matches");	    			
	    			publishResults(flag, rs.getString("RecordPostType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1), "PostType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2), "SubType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSubType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2), "SubType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(3), "SourceMsg record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSourceMsg"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(3), "SourceMsg Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4), "Channel record matches");
	    			
	    			publishResults(flag, rs.getString("RecordChannel"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4), "Channel Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(5), "EntryType record matches");
	    			
	    			publishResults(flag, rs.getString("RecordEntryType"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(5), "EntryType Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6), "PostSource record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordPostSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6), "PostingSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7), "RespIDSource record matches");
	    			
	    			publishResults(flag, rs.getString("RecordRespSource"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7), "RespIDSource Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8), "PostDay record matches");
	    			
	    			publishResults(flag, rs.getString("RecordPostDay"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8), "PostDay Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9), "ClearDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordClearDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9), "ClearDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10), "SettDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordSettDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10), "SettDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(11), "CaptDate record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCaptDate"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(11), "CaptDate Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12), "Amount record matches");
	    			
	    			publishResults(flag, rs.getString("RecordAmount"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12), "Amount Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(13), "TranSetID record matches");
	    			
	    			publishResults(flag, rs.getString("RecordTranSetID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(13), "TranSetID Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(14), "Reason record matches");
	    			
	    			publishResults(flag, rs.getString("RecordReason"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(14), "Reason Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15), "Override record matches");
	    			
	    			publishResults(flag, rs.getString("RecordOverride"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15), "Override Tag Comparison");
	    			
                    flag= Component.verifyEquals(rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(16), "numCheques record matches");
	    			
	    			publishResults(flag, rs.getString("RecordNumCheques"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(16), "numCheques Tag Comparison");
	    			
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17), "CollPart record matches");
	    			
	    			publishResults(flag, rs.getString("RecordCollPart"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17), "CollPart Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18), "CollLocn record matches");	
	    			
	    			publishResults(flag, rs.getString("RecordCollLoc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18), "CollLocn Tag Comparison");
	    			
	    			System.out.println("Successful for Record Attributes");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19), "DebitTransId record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19), "DebitTransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), "AccNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), "AccNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25), "SerNum record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSerNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25), "SerNum Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26), "TranCode record matches");
	    			
	    			publishResults(flag, rs.getString("DebitTranCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26), "TranCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(28), "SwitchSort record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchSort"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(28), "SwitchSort Tag Comparison");
	    		
	    			flag= Component.verifyEquals(rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(29), "SwitchAcc record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitSwitchAcc"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(29), "SwitchAcc Tag Comparison");
	    		
                    flag= Component.verifyEquals(rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(30), "Full Amt record matches");	
	    			
	    			publishResults(flag, rs.getString("DebitFullAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(30), "Full Amt Tag Comparison");
	    		
	    			
	    			System.out.println("Successful for Debit");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20), "TransID record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransID"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20), "TransID Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22), "SortCode record matches");
	    			
	    			publishResults(flag, rs.getString("CreditSortCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22), "SortCode Tag Comparison");

	    			flag= Component.verifyEquals(rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(24), "AccNnum record matches");
	    			
	    			publishResults(flag, rs.getString("CreditAccNum"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(24), "AccNnum Tag Comparison");
	    			
	    			flag= Component.verifyEquals(rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31), "Ref record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditRef"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31), "Ref Tag Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(27), "TranCode record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditTransCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(27), "TranCode Comparison");
	    				
                    flag= Component.verifyEquals(rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(32), "OrigAmt record matches");	
	    			
	    			publishResults(flag, rs.getString("CreditOrigAmt"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(32), "OrigAmt Comparison");
//	    			
//                    flag= Component.verifyEquals(rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(98), "Exception record matches");	
//	    			
//	    			publishResults(flag, rs.getString("CreditExcepCode"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(98), "Exception Comparison");
//	    					    				
	    			flag= Component.verifyEquals(rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33), "Beneficiary record matches");
	    			
	    			publishResults(flag, rs.getString("CreditBeneficiary"), PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33), "Beneficiary Tag Comparison");
	    			
	    			System.out.println("Validation End");
	    			
	    			 //Get PERM01 Tags
	    			
		            PERM01File.getPERM01Details();
		            PostingExtractFile.getRecordsFromExtractXML();
		           
		            //Validate PERM01 tags
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0),PERM01File.getPERM01Details().get(0),"Sequence Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(0), PERM01File.getPERM01Details().get(0), "Sequence Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1),PERM01File.getPERM01Details().get(1),"Post Type Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(1), PERM01File.getPERM01Details().get(1), "PostType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2),PERM01File.getPERM01Details().get(2),"PostSubType Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(2), PERM01File.getPERM01Details().get(2), "PostSubType Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4),PERM01File.getPERM01Details().get(3),"channel Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(4), PERM01File.getPERM01Details().get(3), "channel Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6),PERM01File.getPERM01Details().get(6),"PostingSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(6), PERM01File.getPERM01Details().get(6), "PostingSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7),PERM01File.getPERM01Details().get(7),"RespIdSource Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(7), PERM01File.getPERM01Details().get(7), "RespIdSource Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8),PERM01File.getPERM01Details().get(8),"PostingDay Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(8), PERM01File.getPERM01Details().get(8), "PostingDay Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9),PERM01File.getPERM01Details().get(9),"ClearDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(9), PERM01File.getPERM01Details().get(9), "ClearDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10),PERM01File.getPERM01Details().get(10),"SettlementDate Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(10), PERM01File.getPERM01Details().get(10), "SettlementDate Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12),PERM01File.getPERM01Details().get(12),"PostedAmount Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(12), PERM01File.getPERM01Details().get(12), "PostedAmount Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15),PERM01File.getPERM01Details().get(15),"Override Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(15), PERM01File.getPERM01Details().get(15), "Override Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17),PERM01File.getPERM01Details().get(17),"CollPart Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(17), PERM01File.getPERM01Details().get(17), "CollPart Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18),PERM01File.getPERM01Details().get(18),"Coll Location Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(18), PERM01File.getPERM01Details().get(18), "Coll Location Tag Comparison");
		            
		            //Debit Elements
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19),PERM01File.getPERM01Details().get(19),"Debit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(19), PERM01File.getPERM01Details().get(19), "Debit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21),PERM01File.getPERM01Details().get(21),"Debit SortCode Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(21), PERM01File.getPERM01Details().get(21), "Debit SortCode Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23),PERM01File.getPERM01Details().get(23),"Account Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), PERM01File.getPERM01Details().get(23), "Account Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25),PERM01File.getPERM01Details().get(25),"Serial Number Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(25), PERM01File.getPERM01Details().get(25), "Serial Number Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26),PERM01File.getPERM01Details().get(26),"Tran Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(26), PERM01File.getPERM01Details().get(26), "Tran Code Tag Comparison");
		            
		            //Credit Elements
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20),PERM01File.getPERM01Details().get(20),"Credit Id Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(20), PERM01File.getPERM01Details().get(20), "Credit Id Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22),PERM01File.getPERM01Details().get(22),"Sort Code Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(22), PERM01File.getPERM01Details().get(22), "Sort Code Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23),PERM01File.getPERM01Details().get(24),"Acc Num Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(23), PERM01File.getPERM01Details().get(24), "Acc Num Tag Comparison");
		            
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31),PERM01File.getPERM01Details().get(31),"Ref Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(31), PERM01File.getPERM01Details().get(31), "Ref Tag Comparison");
		                 
		            flag=Component.verifyEquals(PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33),PERM01File.getPERM01Details().get(33),"beneficiary Validation");
		            publishResults(flag, PostingExtractFile.getRecordsFromExtractXML1DR1CR().get(33), PERM01File.getPERM01Details().get(33), "beneficiary Tag Comparison");
		                
	    			
		        }}
	
	catch(Exception e){
		
	}
	}
}
