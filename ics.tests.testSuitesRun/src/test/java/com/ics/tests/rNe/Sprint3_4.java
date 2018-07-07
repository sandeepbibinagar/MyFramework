package com.ics.tests.rNe;

import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ics.rNe.testScenarios.CoreNodeRecordValidationScenarios;
import com.ics.rNe.testScenarios.CoreNodeValidationScenarios;
import com.ics.rNe.testScenarios.CreditRecordValidationScenarios;
import com.ics.rNe.testScenarios.DebitDetailValidationScenarios;
import com.ics.rNe.testScenarios.PostingHeaderValidationsScenarios;
import com.ics.rNe.testScenarios.PostingRecordValidationScenarios;
import com.ics.rNe.testScenarios.PostingResponseHeaderValidationScenarios;
import com.ics.rNe.testScenarios.RecordBodyValidationScenarios;
import com.ics.rNe.xmlFilesDataFetch.CoreNodeRecordFile;
import com.ics.rNe.xmlFilesDataFetch.CreditRecordFile;
import com.ics.rNe.xmlFilesDataFetch.DebitDetailFile;
import com.ics.rNe.xmlFilesDataFetch.PostingHeaderFile;
import com.ics.rNe.xmlFilesDataFetch.PostingRecordFile;
import com.ics.rNe.xmlFilesDataFetch.RecordBodyFile;
import com.ics.rNe.xmlFilesDataFetch.XSDFile;
import com.ics.seleniumCoreUtilis.Component;

/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */

public class Sprint3_4 {
	
	/*
	 * Test Case ID: 58754
	 * Test Description: Validate the Response Header Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	@Test(priority = 1)
	public void test_case_58754() throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> postingHeaderActualList = new HashMap<String, String>();
		postingHeaderActualList = PostingHeaderFile.getPostingHeaderFileTagwiseData();
		boolean assertFlagCheck;
		
		assertFlagCheck = PostingHeaderValidationsScenarios.validateSequence(postingHeaderActualList.get(PostingHeaderFile.sequenceTagName));
	    Component.assertTrue(assertFlagCheck, "Sequence Tag not as expected");
		
		assertFlagCheck = PostingHeaderValidationsScenarios.validatePostType(postingHeaderActualList.get(PostingHeaderFile.postTypeTagName));
	    Component.assertTrue(assertFlagCheck, "PostType Tag not as expected");
		
		assertFlagCheck = PostingHeaderValidationsScenarios.validateSubType(postingHeaderActualList.get(PostingHeaderFile.subTypeTagName));
	    Component.assertTrue(assertFlagCheck, "SubType Tag not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateSourceMsg(postingHeaderActualList.get(PostingHeaderFile.sourceMsgTagName));
	    Component.assertTrue(assertFlagCheck, "SourceMsg Tag not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateChannel(postingHeaderActualList.get(PostingHeaderFile.channelTagName));
	    Component.assertTrue(assertFlagCheck, "Channel not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateEntryType(postingHeaderActualList.get(PostingHeaderFile.entryTypeTagName));
	    Component.assertTrue(assertFlagCheck, "EntryType not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validatePostingSource(postingHeaderActualList.get(PostingHeaderFile.postingSourceTagName));
	    Component.assertTrue(assertFlagCheck, "PostingSource Tag not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateRespIDSource(postingHeaderActualList.get(PostingHeaderFile.respIDSourceTagName));
	    Component.assertTrue(assertFlagCheck, "RespIDSource not as expected");    
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validatePostDay(postingHeaderActualList.get(PostingHeaderFile.postDayTagName));
	    Component.assertTrue(assertFlagCheck, "PostDay not as expected"); 
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateClearDate(postingHeaderActualList.get(PostingHeaderFile.clearDateTagName));
	    Component.assertTrue(assertFlagCheck, "ClearDate not as expected"); 
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateSettDateType(postingHeaderActualList.get(PostingHeaderFile.settDateTypeTagName));
	    Component.assertTrue(assertFlagCheck, "SettDateType not as expected"); 
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateCaptDateType(postingHeaderActualList.get(PostingHeaderFile.captDateTypeTagName));
	    Component.assertTrue(assertFlagCheck, "CaptDateType not as expected"); 
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateAmount(postingHeaderActualList.get(PostingHeaderFile.amountTagName));
	    Component.assertTrue(assertFlagCheck, "Amount not as expected"); 
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateTranSetIDType(postingHeaderActualList.get(PostingHeaderFile.tranSetIDTypeTagName));
	    Component.assertTrue(assertFlagCheck, "TranSetIDType not as expected"); 
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateReason(postingHeaderActualList.get(PostingHeaderFile.reasonTagName));
	    Component.assertTrue(assertFlagCheck, "Reason not as expected"); 
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateOverride(postingHeaderActualList.get(PostingHeaderFile.overrideTagName));
	    Component.assertTrue(assertFlagCheck, "Override not as expected"); 
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateCollPart(postingHeaderActualList.get(PostingHeaderFile.collPartTagName));
	    Component.assertTrue(assertFlagCheck, "CollPart not as expected"); 
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateCollLocn(postingHeaderActualList.get(PostingHeaderFile.collLocnTagName));
	    Component.assertTrue(assertFlagCheck, "CollLocn not as expected"); 
	    
	    
	}
	
	
	/*
	 * Test Case ID: 58755
	 * Test Description: Validate the Response Header Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	@Test(priority = 2)
	public void test_case_58755() throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> postingHeaderActualList = new HashMap<String, String>();
		postingHeaderActualList = PostingHeaderFile.getDebitHeaderFileTagwiseData();
		boolean assertFlagCheck;
		
		assertFlagCheck = PostingHeaderValidationsScenarios.validateDebitId(postingHeaderActualList.get(PostingHeaderFile.debitIdTagName));
	    Component.assertTrue(assertFlagCheck, "DebitId Tag not as expected");
		
		assertFlagCheck = PostingHeaderValidationsScenarios.validateSortCode(postingHeaderActualList.get(PostingHeaderFile.sortCodeTagName));
	    Component.assertTrue(assertFlagCheck, "SortCode Tag not as expected");
		
		assertFlagCheck = PostingHeaderValidationsScenarios.validateAccNum(postingHeaderActualList.get(PostingHeaderFile.accNumTagName));
	    Component.assertTrue(assertFlagCheck, "AccNum Tag not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateSerNum(postingHeaderActualList.get(PostingHeaderFile.serNumTagName));
	    Component.assertTrue(assertFlagCheck, "SerNum Tag not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateTranCode(postingHeaderActualList.get(PostingHeaderFile.tranCodeTagName));
	    Component.assertTrue(assertFlagCheck, "Tran Code not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateSwitchSort(postingHeaderActualList.get(PostingHeaderFile.switchSortTagName));
	    Component.assertTrue(assertFlagCheck, "SwitchSort Tag not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateSwitchAcc(postingHeaderActualList.get(PostingHeaderFile.switchAccTagName));
	    Component.assertTrue(assertFlagCheck, "SwitchAcc not as expected");
	    	    
	}
	
	/*
	 * Test Case ID: 58757
	 * Test Description: Validate the Response Header Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	@Test(priority = 3)
	public void test_case_58757() throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> debitDetailActualList = new HashMap<String, String>();
		debitDetailActualList = DebitDetailFile.getDebitDetailFileTagwiseData();
		boolean assertFlagCheck;
		
		assertFlagCheck = DebitDetailValidationScenarios.validateDefSort(debitDetailActualList.get(DebitDetailFile.defSortTagName));
	    Component.assertTrue(assertFlagCheck, "Def Sort not as expected");
		
		assertFlagCheck = DebitDetailValidationScenarios.validateDefAcc(debitDetailActualList.get(DebitDetailFile.defAccTagName));
	    Component.assertTrue(assertFlagCheck, "DefAcc Tag not as expected");
		
		assertFlagCheck = DebitDetailValidationScenarios.validateRepresent(debitDetailActualList.get(DebitDetailFile.representTagName));
	    Component.assertTrue(assertFlagCheck, "Represent Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateFrontQual(debitDetailActualList.get(DebitDetailFile.frontQualTagName));
	    Component.assertTrue(assertFlagCheck, "FrontQual Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateFraudCode(debitDetailActualList.get(DebitDetailFile.fraudCodeTagName));
	    Component.assertTrue(assertFlagCheck, "FraudCode not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateFraudReason(debitDetailActualList.get(DebitDetailFile.fraudReasonTagName));
	    Component.assertTrue(assertFlagCheck, "FraudReason Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateFraudName(debitDetailActualList.get(DebitDetailFile.fraudNameTagName));
	    Component.assertTrue(assertFlagCheck, "FraudName not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateDuplicate(debitDetailActualList.get(DebitDetailFile.duplicateTagName));
	    Component.assertTrue(assertFlagCheck, "Duplicate not as expected");
		
		assertFlagCheck = DebitDetailValidationScenarios.validateDupId(debitDetailActualList.get(DebitDetailFile.dupIdTagName));
	    Component.assertTrue(assertFlagCheck, "DupId Tag not as expected");
		
		assertFlagCheck = DebitDetailValidationScenarios.validateDupStatus(debitDetailActualList.get(DebitDetailFile.dupStatusTagName));
	    Component.assertTrue(assertFlagCheck, "DupStatus Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateDupSeen(debitDetailActualList.get(DebitDetailFile.dupSeenTagName));
	    Component.assertTrue(assertFlagCheck, "DupSeen Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateDupCollect(debitDetailActualList.get(DebitDetailFile.dupCollectTagName));
	    Component.assertTrue(assertFlagCheck, "DupCollect not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateDupCapture(debitDetailActualList.get(DebitDetailFile.dupCaptureTagName));
	    Component.assertTrue(assertFlagCheck, "DupCapture Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateDupSource(debitDetailActualList.get(DebitDetailFile.dupSourceTagName));
	    Component.assertTrue(assertFlagCheck, "DupSource not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateStopped(debitDetailActualList.get(DebitDetailFile.stoppedTagName));
	    Component.assertTrue(assertFlagCheck, "Stopped not as expected");
		
		assertFlagCheck = DebitDetailValidationScenarios.validateStopDate(debitDetailActualList.get(DebitDetailFile.stopDateTagName));
	    Component.assertTrue(assertFlagCheck, "StopDate not as expected");
		
		assertFlagCheck = DebitDetailValidationScenarios.validateStopStatus(debitDetailActualList.get(DebitDetailFile.stopStatusTagName));
	    Component.assertTrue(assertFlagCheck, "StopStatus Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateStopAmt(debitDetailActualList.get(DebitDetailFile.stopAmtTagName));
	    Component.assertTrue(assertFlagCheck, "StopAmt Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateStopName(debitDetailActualList.get(DebitDetailFile.stopNameTagName));
	    Component.assertTrue(assertFlagCheck, "StopName not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateStopStart(debitDetailActualList.get(DebitDetailFile.stopStartTagName));
	    Component.assertTrue(assertFlagCheck, "StopStart Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateStopEnd(debitDetailActualList.get(DebitDetailFile.stopEndTagName));
	    Component.assertTrue(assertFlagCheck, "StopEnd not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateRepSort(debitDetailActualList.get(DebitDetailFile.repSortTagName));
	    Component.assertTrue(assertFlagCheck, "RepSort Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateRepAcc(debitDetailActualList.get(DebitDetailFile.repAccTagName));
	    Component.assertTrue(assertFlagCheck, "RepAcc Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateRepAmt(debitDetailActualList.get(DebitDetailFile.repAmtTagName));
	    Component.assertTrue(assertFlagCheck, "RepAmt not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateRepSer(debitDetailActualList.get(DebitDetailFile.repSerTagName));
	    Component.assertTrue(assertFlagCheck, "RepSer Tag not as expected");
	    
	    assertFlagCheck = DebitDetailValidationScenarios.validateException(debitDetailActualList.get(DebitDetailFile.exceptionTagName));
	    Component.assertTrue(assertFlagCheck, "Exception not as expected");
	    	    
	}
	

	/*
	 * Test Case ID: 61743
	 * Test Description: Validate the Response Header Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	@Test(priority = 4)
	public void test_case_61743() throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> debitDetailActualList = new HashMap<String, String>();
		debitDetailActualList = PostingHeaderFile.getPostingTrailerFileTagwiseData();
		boolean assertFlagCheck;
		
		assertFlagCheck = PostingHeaderValidationsScenarios.validateTranCount(debitDetailActualList.get(PostingHeaderFile.tranCountTagName));
	    Component.assertTrue(assertFlagCheck, "Def Sort not as expected");
		
	    	    
	}
	
	/*
	 * Test Case ID: 72132
	 * Test Description: Validate the Response Header Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	@Test(priority = 5)
	public void test_case_72132() throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> postingHeaderActualList = new HashMap<String, String>();
		postingHeaderActualList = RecordBodyFile.getPostingHeaderFileTagwiseData();
		boolean assertFlagCheck;
		
		assertFlagCheck = RecordBodyValidationScenarios.validateSequence(postingHeaderActualList.get(RecordBodyFile.sequenceTagName));
	    Component.assertTrue(assertFlagCheck, "Sequence Tag not as expected");
		
		assertFlagCheck = RecordBodyValidationScenarios.validatePostType(postingHeaderActualList.get(RecordBodyFile.postTypeTagName));
	    Component.assertTrue(assertFlagCheck, "PostType Tag not as expected");
		
		assertFlagCheck = RecordBodyValidationScenarios.validateSubType(postingHeaderActualList.get(RecordBodyFile.subTypeTagName));
	    Component.assertTrue(assertFlagCheck, "SubType Tag not as expected");
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validateSourceMsg(postingHeaderActualList.get(RecordBodyFile.sourceMsgTagName));
	    Component.assertTrue(assertFlagCheck, "SourceMsg Tag not as expected");
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validateChannel(postingHeaderActualList.get(RecordBodyFile.channelTagName));
	    Component.assertTrue(assertFlagCheck, "Channel not as expected");
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validateEntryType(postingHeaderActualList.get(RecordBodyFile.entryTypeTagName));
	    Component.assertTrue(assertFlagCheck, "EntryType not as expected");
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validatePostingSource(postingHeaderActualList.get(RecordBodyFile.postingSourceTagName));
	    Component.assertTrue(assertFlagCheck, "PostingSource Tag not as expected");
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validateRespIDSource(postingHeaderActualList.get(RecordBodyFile.respIDSourceTagName));
	    Component.assertTrue(assertFlagCheck, "RespIDSource not as expected");    
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validatePostDay(postingHeaderActualList.get(RecordBodyFile.postDayTagName));
	    Component.assertTrue(assertFlagCheck, "PostDay not as expected"); 
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validateClearDate(postingHeaderActualList.get(RecordBodyFile.clearDateTagName));
	    Component.assertTrue(assertFlagCheck, "ClearDate not as expected"); 
	    
//	    assertFlagCheck = RecordBodyValidationScenarios.validateSettDateType(postingHeaderActualList.get(RecordBodyFile.settDateTypeTagName));
//	    Component.assertTrue(assertFlagCheck, "SettDateType not as expected"); 
	    
//	    assertFlagCheck = RecordBodyValidationScenarios.validateCaptDateType(postingHeaderActualList.get(RecordBodyFile.captDateTypeTagName));
//	    Component.assertTrue(assertFlagCheck, "CaptDateType not as expected"); 
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validateAmount(postingHeaderActualList.get(RecordBodyFile.amountTagName));
	    Component.assertTrue(assertFlagCheck, "Amount not as expected"); 
	    
//	    assertFlagCheck = RecordBodyValidationScenarios.validateTranSetIDType(postingHeaderActualList.get(RecordBodyFile.tranSetIDTypeTagName));
//	    Component.assertTrue(assertFlagCheck, "TranSetIDType not as expected"); 
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validateReason(postingHeaderActualList.get(RecordBodyFile.reasonTagName));
	    Component.assertTrue(assertFlagCheck, "Reason not as expected"); 
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validateOverride(postingHeaderActualList.get(RecordBodyFile.overrideTagName));
	    Component.assertTrue(assertFlagCheck, "Override not as expected"); 
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validateCollPart(postingHeaderActualList.get(RecordBodyFile.collPartTagName));
	    Component.assertTrue(assertFlagCheck, "CollPart not as expected"); 
	    
	    assertFlagCheck = RecordBodyValidationScenarios.validateCollLocn(postingHeaderActualList.get(RecordBodyFile.collLocnTagName));
	    Component.assertTrue(assertFlagCheck, "CollLocn not as expected"); 
	    
	    
	}
	
	/*
	 * Test Case ID: 72141
	 * Test Description: Validate the Response Header Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	
	@Test(priority = 6)
	public void test_case_72141() throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> postingHeaderActualList = new HashMap<String, String>();
		postingHeaderActualList = PostingHeaderFile.getDebitHeaderFileTagwiseData();
		boolean assertFlagCheck;
		
		assertFlagCheck = PostingHeaderValidationsScenarios.validateDebitId(postingHeaderActualList.get(PostingHeaderFile.debitIdTagName));
	    Component.assertTrue(assertFlagCheck, "DebitId Tag not as expected");
		
		assertFlagCheck = PostingHeaderValidationsScenarios.validateSortCode(postingHeaderActualList.get(PostingHeaderFile.sortCodeTagName));
	    Component.assertTrue(assertFlagCheck, "SortCode Tag not as expected");
		
		assertFlagCheck = PostingHeaderValidationsScenarios.validateAccNum(postingHeaderActualList.get(PostingHeaderFile.accNumTagName));
	    Component.assertTrue(assertFlagCheck, "AccNum Tag not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateSerNum(postingHeaderActualList.get(PostingHeaderFile.serNumTagName));
	    Component.assertTrue(assertFlagCheck, "SerNum Tag not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateTranCode(postingHeaderActualList.get(PostingHeaderFile.tranCodeTagName));
	    Component.assertTrue(assertFlagCheck, "Tran Code not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateSwitchSort(postingHeaderActualList.get(PostingHeaderFile.switchSortTagName));
	    Component.assertTrue(assertFlagCheck, "SwitchSort Tag not as expected");
	    
	    assertFlagCheck = PostingHeaderValidationsScenarios.validateSwitchAcc(postingHeaderActualList.get(PostingHeaderFile.switchAccTagName));
	    Component.assertTrue(assertFlagCheck, "SwitchAcc not as expected");
	    	    
	}
	
	/*
	 * Test Case ID: 72143
	 * Test Description: Validate the Response Header Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	
	@Test(priority = 6)
	public void test_case_72143() throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> creditRecordActualList = new HashMap<String, String>();
		creditRecordActualList = CreditRecordFile.getCreditRecordFileTagwiseData();
		boolean assertFlagCheck;
		
		assertFlagCheck = CreditRecordValidationScenarios.validateCreditId(creditRecordActualList.get(CreditRecordFile.creditIdTagName));
	    Component.assertTrue(assertFlagCheck, "CreditId Tag not as expected");
		
		assertFlagCheck = CreditRecordValidationScenarios.validateSortCode(creditRecordActualList.get(CreditRecordFile.sortCodeTagName));
	    Component.assertTrue(assertFlagCheck, "SortCode Tag not as expected");
		
		assertFlagCheck = CreditRecordValidationScenarios.validateAccNum(creditRecordActualList.get(CreditRecordFile.accNumTagName));
	    Component.assertTrue(assertFlagCheck, "AccNum Tag not as expected");
	    
	    assertFlagCheck = CreditRecordValidationScenarios.validateRef(creditRecordActualList.get(CreditRecordFile.refTagName));
	    Component.assertTrue(assertFlagCheck, "Ref Tag not as expected");
	    
	    assertFlagCheck = CreditRecordValidationScenarios.validateTranCode(creditRecordActualList.get(CreditRecordFile.tranCodeTagName));
	    Component.assertTrue(assertFlagCheck, "Tran Code not as expected");
	    
	    assertFlagCheck = CreditRecordValidationScenarios.validateBeneficiary(creditRecordActualList.get(CreditRecordFile.beneficiaryTagName));
	    Component.assertTrue(assertFlagCheck, "Beneficiary Tag not as expected");
	    	    
	}
	
	/*
	 * Test Case ID: 61896
	 * Test Description: Validate the Response Header Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	@Test(priority = 7)
	public void test_case_61896() throws SAXException, IOException, ParserConfigurationException
	{
		boolean assertFlagCheck;
		
		//ENTITY SET
		HashMap<String, String> postingRecordEntitySet = new HashMap<String, String>();
		postingRecordEntitySet = PostingRecordFile.getEntitySetFileTagwiseData();
				
		assertFlagCheck = PostingRecordValidationScenarios.validateEntityType(postingRecordEntitySet.get(PostingRecordFile.entityTypeTagName));
	    Component.assertTrue(assertFlagCheck, "Entity Type Tag not as expected");
		
	    assertFlagCheck = PostingRecordValidationScenarios.validateEntityState(postingRecordEntitySet.get(PostingRecordFile.entityStateTagName));
	    Component.assertTrue(assertFlagCheck, "Entity State Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateEntityId(postingRecordEntitySet.get(PostingRecordFile.entityIdTagName));
	    Component.assertTrue(assertFlagCheck, "Entity Id Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateSourceDateTime(postingRecordEntitySet.get(PostingRecordFile.sourceDateTimeTagName));
	    Component.assertTrue(assertFlagCheck, "SourceDateTime Tag not as expected");
		
	    
	    //POSTING UPDATE TAG
	    HashMap<String, String> postingRecordPostingUpdateSet = new HashMap<String, String>();
	    postingRecordPostingUpdateSet = PostingRecordFile.getPostingUpdateFileTagwiseData();
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostingExtractId(postingRecordPostingUpdateSet.get(PostingRecordFile.postingExtractIdTagName));
	    Component.assertTrue(assertFlagCheck, "PostingExtractId Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateExtractSequence(postingRecordPostingUpdateSet.get(PostingRecordFile.extractSequenceTagName));
	    Component.assertTrue(assertFlagCheck, "ExtractSequence Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateExtractRevision(postingRecordPostingUpdateSet.get(PostingRecordFile.extractRevisionTagName));
	    Component.assertTrue(assertFlagCheck, "ExtractRevision Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateField(postingRecordPostingUpdateSet.get(PostingRecordFile.fieldTagName));
	    Component.assertTrue(assertFlagCheck, "Field Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostingExtractId(postingRecordPostingUpdateSet.get(PostingRecordFile.postingExtractIdTagName));
	    Component.assertTrue(assertFlagCheck, "PostingExtractId Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateWeekday(postingRecordPostingUpdateSet.get(PostingRecordFile.weekdayTagName));
	    Component.assertTrue(assertFlagCheck, "Weekday Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateFileType(postingRecordPostingUpdateSet.get(PostingRecordFile.fileTypeTagName));
	    Component.assertTrue(assertFlagCheck, "Filetype Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateCurrency(postingRecordPostingUpdateSet.get(PostingRecordFile.currencyTagName));
	    Component.assertTrue(assertFlagCheck, "Currency Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateEnvironment(postingRecordPostingUpdateSet.get(PostingRecordFile.environmentTagName));
	    Component.assertTrue(assertFlagCheck, "Environment Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateExtractStartDateTime(postingRecordPostingUpdateSet.get(PostingRecordFile.extractStartDateTimeTagName));
	    Component.assertTrue(assertFlagCheck, "ExtractStartDateTime Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateExtractEndDateTime(postingRecordPostingUpdateSet.get(PostingRecordFile.extractEndDateTimeTagName));
	    Component.assertTrue(assertFlagCheck, "ExtractEndDateTime Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateExtractItem(postingRecordPostingUpdateSet.get(PostingRecordFile.extractItemTagName));
	    Component.assertTrue(assertFlagCheck, "ExtractItem Tag not as expected");
	    
	    
	    
	    
	    //POSTING ITEM ENTRY TAG
	    HashMap<String, String> postingRecordPostingItemEntrySet = new HashMap<String, String>();
	    postingRecordPostingItemEntrySet = PostingRecordFile.getPositemEntryFileTagwiseData();
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateItemId(postingRecordPostingItemEntrySet.get(PostingRecordFile.itemIdTagName));
	    Component.assertTrue(assertFlagCheck, "ItemId Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostingAttempt(postingRecordPostingItemEntrySet.get(PostingRecordFile.postingAttemptTagName));
	    Component.assertTrue(assertFlagCheck, "PostingAttempt Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostingSequence(postingRecordPostingItemEntrySet.get(PostingRecordFile.postingSequenceTagName));
	    Component.assertTrue(assertFlagCheck, "PostingSequence Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostingType(postingRecordPostingItemEntrySet.get(PostingRecordFile.postingTypeTagName));
	    Component.assertTrue(assertFlagCheck, "PostingType Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostingSubType(postingRecordPostingItemEntrySet.get(PostingRecordFile.postingSubTypeTagName));
	    Component.assertTrue(assertFlagCheck, "PostingSubType Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateChannel(postingRecordPostingItemEntrySet.get(PostingRecordFile.channelTagName));
	    Component.assertTrue(assertFlagCheck, "Channel Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostingDrCrEntry(postingRecordPostingItemEntrySet.get(PostingRecordFile.postingDrCrEntryTagName));
	    Component.assertTrue(assertFlagCheck, "PostingDrCrEntry Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostingSource(postingRecordPostingItemEntrySet.get(PostingRecordFile.postingSourceTagName));
	    Component.assertTrue(assertFlagCheck, "PostingSource Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateResponseIdSource(postingRecordPostingItemEntrySet.get(PostingRecordFile.responseIdSourceTagName));
	    Component.assertTrue(assertFlagCheck, "ResponseIdSource Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostingDay(postingRecordPostingItemEntrySet.get(PostingRecordFile.postingDayTagName));
	    Component.assertTrue(assertFlagCheck, "PostingDay Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateClearingDate(postingRecordPostingItemEntrySet.get(PostingRecordFile.clearingDateTagName));
	    Component.assertTrue(assertFlagCheck, "ClearingDate Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateSettlementDate(postingRecordPostingItemEntrySet.get(PostingRecordFile.settlementDateTagName));
	    Component.assertTrue(assertFlagCheck, "SettlementDate Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostedAmount(postingRecordPostingItemEntrySet.get(PostingRecordFile.postedAmmountTagName));
	    Component.assertTrue(assertFlagCheck, "PostedAmount Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validatePostingDecisionReasonCode(postingRecordPostingItemEntrySet.get(PostingRecordFile.postingDecisionReasonCodeTagName));
	    Component.assertTrue(assertFlagCheck, "PostingDecisionReasonCode Tag not as expected");
	    
	    
	    //DEBIT RECORD TAG
	    HashMap<String, String> postingRecordDebitRecordSet = new HashMap<String, String>();
	    postingRecordDebitRecordSet = PostingRecordFile.getDebitRecordFileTagwiseData();
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateItemIdDR(postingRecordDebitRecordSet.get(PostingRecordFile.itemIdDrTagName));
	    Component.assertTrue(assertFlagCheck, "ItemIdDr Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateSortCodeDR(postingRecordDebitRecordSet.get(PostingRecordFile.sortCodeDrTagName));
	    Component.assertTrue(assertFlagCheck, "SortCodeDR Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateAccountDR(postingRecordDebitRecordSet.get(PostingRecordFile.accountDrTagName));
	    Component.assertTrue(assertFlagCheck, "AccountDR Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateReferenceDR(postingRecordDebitRecordSet.get(PostingRecordFile.referenceDrTagName));
	    Component.assertTrue(assertFlagCheck, "ReferenceDR Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateTranCodeDR(postingRecordDebitRecordSet.get(PostingRecordFile.tranCodeDrTagName));
	    Component.assertTrue(assertFlagCheck, "TranCodeDR Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateSwitchedSortCode(postingRecordDebitRecordSet.get(PostingRecordFile.switchedSortCodeTagName));
	    Component.assertTrue(assertFlagCheck, "SwitchedSortCode Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateSwitchedAccount(postingRecordDebitRecordSet.get(PostingRecordFile.switchedAccountTagName));
	    Component.assertTrue(assertFlagCheck, "SwitchedAccount Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateDebitFullAmount(postingRecordDebitRecordSet.get(PostingRecordFile.debitFullAmountTagName));
	    Component.assertTrue(assertFlagCheck, "DebitFullAmount Tag not as expected");
	    
	    
	    //CREDIT RECORD TAG
	    HashMap<String, String> postingRecordCreditRecordSet = new HashMap<String, String>();
	    postingRecordCreditRecordSet = PostingRecordFile.getCreditRecordFileTagwiseData();
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateItemIdCR(postingRecordCreditRecordSet.get(PostingRecordFile.itemIdCrTagName));
	    Component.assertTrue(assertFlagCheck, "ItemIdCR Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateSortCodeCR(postingRecordCreditRecordSet.get(PostingRecordFile.sortCodeCrTagName));
	    Component.assertTrue(assertFlagCheck, "SortCodeCR Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateAccountCR(postingRecordCreditRecordSet.get(PostingRecordFile.accountCrTagName));
	    Component.assertTrue(assertFlagCheck, "AccountCR Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateReferenceCR(postingRecordCreditRecordSet.get(PostingRecordFile.referenceCrTagName));
	    Component.assertTrue(assertFlagCheck, "ReferenceCR Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateTranCodeCR(postingRecordCreditRecordSet.get(PostingRecordFile.tranCodeCrTagName));
	    Component.assertTrue(assertFlagCheck, "TranCodeCR Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateOriginalAmount(postingRecordCreditRecordSet.get(PostingRecordFile.originalAmountTagName));
	    Component.assertTrue(assertFlagCheck, "OriginalAmount Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateOriginalSortCode(postingRecordCreditRecordSet.get(PostingRecordFile.originalSortCodeTagName));
	    Component.assertTrue(assertFlagCheck, "OriginalSortCode Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateOriginalAccount(postingRecordCreditRecordSet.get(PostingRecordFile.originalAccountTagName));
	    Component.assertTrue(assertFlagCheck, "OriginalAccount Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateCreditExceptionCode(postingRecordCreditRecordSet.get(PostingRecordFile.creditExceptionCodeTagName));
	    Component.assertTrue(assertFlagCheck, "CreditExceptionCode Tag not as expected");
	    
	    assertFlagCheck = PostingRecordValidationScenarios.validateBeneficiaryName(postingRecordCreditRecordSet.get(PostingRecordFile.beneficiaryNameTagName));
	    Component.assertTrue(assertFlagCheck, "BeneficiaryName Tag not as expected");
	    
	    
	}
	
	@Test(priority = 8)
	public void test_case_77267() throws SAXException, IOException, ParserConfigurationException
	{
		boolean assertFlagCheck;
		
		//CORE NODE SET
		HashMap<String, String> coreNodeRecordSet = new HashMap<String, String>();
		coreNodeRecordSet = CoreNodeRecordFile.getCoreNodeFileTagwiseData();
		
		assertFlagCheck = CoreNodeRecordValidationScenarios.validateBusinessDate(coreNodeRecordSet.get(CoreNodeRecordFile.businessDateTagName));
	    Component.assertTrue(assertFlagCheck, "BusinessDate Tag not as expected");
	    
	    assertFlagCheck = CoreNodeRecordValidationScenarios.validateExtractId(coreNodeRecordSet.get(CoreNodeRecordFile.extractIdTagName));
	    Component.assertTrue(assertFlagCheck, "ExtractId Tag not as expected");
	    
	    assertFlagCheck = CoreNodeRecordValidationScenarios.validateIntMessageType(coreNodeRecordSet.get(CoreNodeRecordFile.intMessageTypeTagName));
	    Component.assertTrue(assertFlagCheck, "IntMessageType Tag not as expected");
	    
	    assertFlagCheck = CoreNodeRecordValidationScenarios.validateProcessingParticipantId(coreNodeRecordSet.get(CoreNodeRecordFile.processingParticipantIdTagName));
	    Component.assertTrue(assertFlagCheck, "ProcessingParticipantId Tag not as expected");
	    
	    assertFlagCheck = CoreNodeRecordValidationScenarios.validateExtMessageType(coreNodeRecordSet.get(CoreNodeRecordFile.extMessageTypeTagName));
	    Component.assertTrue(assertFlagCheck, "ExtMessageType Tag not as expected");
	    
	    assertFlagCheck = CoreNodeRecordValidationScenarios.validateMessageSource(coreNodeRecordSet.get(CoreNodeRecordFile.messageSourceTagName));
	    Component.assertTrue(assertFlagCheck, "MessageSource Tag not as expected");
	    
	    assertFlagCheck = CoreNodeRecordValidationScenarios.validateMessageDestination(coreNodeRecordSet.get(CoreNodeRecordFile.messageDestinationTagName));
	    Component.assertTrue(assertFlagCheck, "MessageDestination Tag not as expected");
	    
	    assertFlagCheck = CoreNodeRecordValidationScenarios.validateRecordCounts(coreNodeRecordSet.get(CoreNodeRecordFile.recordCountsTagName));
	    Component.assertTrue(assertFlagCheck, "RecordCounts Tag not as expected");
	    
	    
	    
	    //ENTITIES SET
	  	HashMap<String, String> entitiesRecordSet = new HashMap<String, String>();
	  	entitiesRecordSet = CoreNodeRecordFile.getEntitiesFileTagwiseData();
	  		
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateEntityType(entitiesRecordSet.get(CoreNodeRecordFile.entityTypeTagName));
	  	Component.assertTrue(assertFlagCheck, "EntityType Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateEntityId(entitiesRecordSet.get(CoreNodeRecordFile.entityIdTagName));
	  	Component.assertTrue(assertFlagCheck, "EntityID Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateEntityType(entitiesRecordSet.get(CoreNodeRecordFile.entityTypeTagName));
	  	Component.assertTrue(assertFlagCheck, "EntityType Tag not as expected");
	  	    
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateEntityState(entitiesRecordSet.get(CoreNodeRecordFile.entityStateTagName));
	  	Component.assertTrue(assertFlagCheck, "EntityState Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateSourceDateTime(entitiesRecordSet.get(CoreNodeRecordFile.sourceDateTimeTagName));
	  	Component.assertTrue(assertFlagCheck, "SourceDateTime Tag not as expected");
	  	
	  	
	  	//POSTING UPDATE SET
	  	HashMap<String, String> postingUpdateSet = new HashMap<String, String>();
	  	postingUpdateSet = CoreNodeRecordFile.getPostingUpdateFileTagwiseData();
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateExtractSequence(postingUpdateSet.get(CoreNodeRecordFile.extractSequenceTagName));
	  	Component.assertTrue(assertFlagCheck, "ExtractSequence Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateExtractRevision(postingUpdateSet.get(CoreNodeRecordFile.extractRevisionTagName));
	  	Component.assertTrue(assertFlagCheck, "ExtractRevision Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateFileId(postingUpdateSet.get(CoreNodeRecordFile.fileIdTagName));
	  	Component.assertTrue(assertFlagCheck, "FileID Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateWeekday(postingUpdateSet.get(CoreNodeRecordFile.weekdayTagName));
	  	Component.assertTrue(assertFlagCheck, "Weekday Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateFileType(postingUpdateSet.get(CoreNodeRecordFile.fileTypeTagName));
	  	Component.assertTrue(assertFlagCheck, "FileType Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateCurrency(postingUpdateSet.get(CoreNodeRecordFile.currencyTagName));
	  	Component.assertTrue(assertFlagCheck, "Currency Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateEnvironment(postingUpdateSet.get(CoreNodeRecordFile.environmentTagName));
	  	Component.assertTrue(assertFlagCheck, "Environment Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateExtractStartDateTime(postingUpdateSet.get(CoreNodeRecordFile.extractStartDateTimeTagName));
	  	Component.assertTrue(assertFlagCheck, "ExtractStartDateTime Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateExtractEndDateTime(postingUpdateSet.get(CoreNodeRecordFile.extractEndDateTimeTagName));
	  	Component.assertTrue(assertFlagCheck, "ExtractEndDateTime Tag not as expected");
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateExtractItemCount(postingUpdateSet.get(CoreNodeRecordFile.extractItemCountTagName));
	  	Component.assertTrue(assertFlagCheck, "ExtractItemCount Tag not as expected");
	  	
	  	
	  	//Posting Item Entry SET
	  	HashMap<String, String> postingItemEntrySet = new HashMap<String, String>();
	  	postingItemEntrySet = CoreNodeRecordFile.getPostingUpdateFileTagwiseData();
	  	
	  	assertFlagCheck = CoreNodeRecordValidationScenarios.validateItemId(postingItemEntrySet.get(CoreNodeRecordFile.itemIdTagName));
	  	Component.assertTrue(assertFlagCheck, "ItemID Tag not as expected");
	  	
	  	 assertFlagCheck = CoreNodeRecordValidationScenarios.validatePostingAttempt(postingItemEntrySet.get(CoreNodeRecordFile.postingAttemptTagName));
		    Component.assertTrue(assertFlagCheck, "PostingAttempt Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validatePostingSequence(postingItemEntrySet.get(CoreNodeRecordFile.postingSequenceTagName));
		    Component.assertTrue(assertFlagCheck, "PostingSequence Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validatePostingType(postingItemEntrySet.get(CoreNodeRecordFile.postingTypeTagName));
		    Component.assertTrue(assertFlagCheck, "PostingType Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validatePostingSubType(postingItemEntrySet.get(CoreNodeRecordFile.postingSubTypeTagName));
		    Component.assertTrue(assertFlagCheck, "PostingSubType Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validateChannel(postingItemEntrySet.get(CoreNodeRecordFile.channelTagName));
		    Component.assertTrue(assertFlagCheck, "Channel Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validatePostingDrCrEntry(postingItemEntrySet.get(CoreNodeRecordFile.postingDrCrEntryTagName));
		    Component.assertTrue(assertFlagCheck, "PostingDrCrEntry Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validatePostingSource(postingItemEntrySet.get(CoreNodeRecordFile.postingSourceTagName));
		    Component.assertTrue(assertFlagCheck, "PostingSource Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validateResponseIdSource(postingItemEntrySet.get(CoreNodeRecordFile.responseIdSourceTagName));
		    Component.assertTrue(assertFlagCheck, "ResponseIdSource Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validatePostingDay(postingItemEntrySet.get(CoreNodeRecordFile.postingDayTagName));
		    Component.assertTrue(assertFlagCheck, "PostingDay Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validateSettlementDate(postingItemEntrySet.get(CoreNodeRecordFile.settlementDateTagName));
		    Component.assertTrue(assertFlagCheck, "SettlementDate Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validatePostedAmount(postingItemEntrySet.get(CoreNodeRecordFile.postedAmmountTagName));
		    Component.assertTrue(assertFlagCheck, "PostedAmount Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validatePostingDecisionReasonCode(postingItemEntrySet.get(CoreNodeRecordFile.postingDecisionReasonCodeTagName));
		    Component.assertTrue(assertFlagCheck, "PostingDecisionReasonCode Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validatePostingOverrideFlag(postingItemEntrySet.get(CoreNodeRecordFile.postingOverrideFlagTagName));
		    Component.assertTrue(assertFlagCheck, "PostingOverride Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validateNPASortCode(postingItemEntrySet.get(CoreNodeRecordFile.nPASortCodeTagName));
		    Component.assertTrue(assertFlagCheck, "NPASortCode Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validateNPAAccount(postingItemEntrySet.get(CoreNodeRecordFile.nPAAccountTagName));
		    Component.assertTrue(assertFlagCheck, "NPAAccount Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validateSupportingInfo(postingItemEntrySet.get(CoreNodeRecordFile.supportingInfoTagName));
		    Component.assertTrue(assertFlagCheck, "SupportingInfo Info Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validateChequeCount(postingItemEntrySet.get(CoreNodeRecordFile.chequeCountTagName));
		    Component.assertTrue(assertFlagCheck, "ChequeCount Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validateCollectingParticipantId(postingItemEntrySet.get(CoreNodeRecordFile.collectingParticipantIDTagName));
		    Component.assertTrue(assertFlagCheck, "CollectingParticipantId Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validateCollectingLocation(postingItemEntrySet.get(CoreNodeRecordFile.collectingLocationTagName));
		    Component.assertTrue(assertFlagCheck, "CollectingLocation Tag not as expected");
		    
		    assertFlagCheck = CoreNodeRecordValidationScenarios.validateAggregrated(postingItemEntrySet.get(CoreNodeRecordFile.aggregratedTagName));
		    Component.assertTrue(assertFlagCheck, "Aggregrated Tag not as expected");
		    
		    //Credit Record
		    
		    //Debit Record
	  	
	  	
	  	
	  	
	}




}
