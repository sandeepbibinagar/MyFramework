package com.ics.tests.rNe;

import java.io.IOException;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import com.ics.rNe.testScenarios.PostingResponseHeaderValidationScenarios;
import com.ics.rNe.xmlFilesDataFetch.XSDFile;
import com.ics.seleniumCoreUtilis.Component;

/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class Sprint2_1 {
	
	
	/*
	 * Test Case ID: 58821
	 * Test Description: Validate the Response Header Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	
	@Test(priority = 1)
	public void test_case_58821() throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> postingResponseHeaderActualList = new HashMap<String, String>();
		postingResponseHeaderActualList = XSDFile.getXSDFileTagwiseDataForResponseHeader();
		boolean assertFlagCheck;
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateFileDate(postingResponseHeaderActualList.get(XSDFile.fileDateTagName));
	    Component.assertTrue(assertFlagCheck, "FileDate Tag not as expected");
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateProcDate(postingResponseHeaderActualList.get(XSDFile.procDateTagName));
	    Component.assertTrue(assertFlagCheck, "ProcDate Tag not as expected");
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateParticipant(postingResponseHeaderActualList.get(XSDFile.participantTagName));
	    Component.assertTrue(assertFlagCheck, "Participant-Id Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateVersion(postingResponseHeaderActualList.get(XSDFile.versionTagName));
	    Component.assertTrue(assertFlagCheck, "Version Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateSchema(postingResponseHeaderActualList.get(XSDFile.schemaTagName));
	    Component.assertTrue(assertFlagCheck, "Schema Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateSequence(postingResponseHeaderActualList.get(XSDFile.sequenceTagName));
	    Component.assertTrue(assertFlagCheck, "Sequence Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateEnvironment(postingResponseHeaderActualList.get(XSDFile.environmentTagName));
	    Component.assertTrue(assertFlagCheck, "Environment Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateSource(postingResponseHeaderActualList.get(XSDFile.sourceTagName));
	    Component.assertTrue(assertFlagCheck, "Source Tag not as expected");    
	}
	
	/*
	 * Test Case ID: 58822
	 * Test Description: Validate the Posting Response Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	
	@Test(priority = 2)
	public void test_case_58822()throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> postingResponseActualList = new HashMap<String, String>();
		postingResponseActualList = XSDFile.getXSDFileTagwiseDataForResponse();
		boolean assertFlagCheck;
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateSequenceR(postingResponseActualList.get(XSDFile.sequenceRTagName));
	    Component.assertTrue(assertFlagCheck, "SequenceR Tag not as expected");
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateTranID(postingResponseActualList.get(XSDFile.tranIDTagName));
	    Component.assertTrue(assertFlagCheck, "TranID Tag not as expected");
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validatePostType(postingResponseActualList.get(XSDFile.postTypeTagName));
	    Component.assertTrue(assertFlagCheck, "Post Type Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateAccNum(postingResponseActualList.get(XSDFile.accNumTagName));
	    Component.assertTrue(assertFlagCheck, "Acc num Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateSortCode(postingResponseActualList.get(XSDFile.sortCodeTagName));
	    Component.assertTrue(assertFlagCheck, "Sort Code Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateNPAAcc(postingResponseActualList.get(XSDFile.nPAAccTagName));
	    Component.assertTrue(assertFlagCheck, "NPAAcc Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateNPASort(postingResponseActualList.get(XSDFile.nPASortTagName));
	    Component.assertTrue(assertFlagCheck, "NPASort Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateAmount(postingResponseActualList.get(XSDFile.amountTagName));
	    Component.assertTrue(assertFlagCheck, "Amount Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateAccSystem(postingResponseActualList.get(XSDFile.accSystemTagName));
	    Component.assertTrue(assertFlagCheck, "AccSystem Tag not as expected");	    
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateStatusCnt(postingResponseActualList.get(XSDFile.statusCntTagName));
	    Component.assertTrue(assertFlagCheck, "StatusCnt Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateRedirInd(postingResponseActualList.get(XSDFile.redirIndTagName));
	    Component.assertTrue(assertFlagCheck, "RedirInd Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateRespStatus(postingResponseActualList.get(XSDFile.respStatusTagName));
	    Component.assertTrue(assertFlagCheck, "RespStatus Tag not as expected");
	    
	    assertFlagCheck = PostingResponseHeaderValidationScenarios.validateRespSubType(postingResponseActualList.get(XSDFile.respSubTypeTagName));
	    Component.assertTrue(assertFlagCheck, "RespSubType Tag not as expected");
	}
	
	/*
	 * Test Case ID: 58826
	 * Test Description: Validate the Status Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	
	@Test(priority = 3)
	public void test_case_58826()throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> postingStatusActualList = new HashMap<String, String>();
		postingStatusActualList = XSDFile.getXSDFileTagwiseDataForStatus();
		boolean assertFlagCheck;
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateStatusSeq(postingStatusActualList.get(XSDFile.statusSeqtTagName));
	    Component.assertTrue(assertFlagCheck, "StatusSeq Tag not as expected");
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateReasonCode(postingStatusActualList.get(XSDFile.reasonCodeTagName));
	    Component.assertTrue(assertFlagCheck, "ReasonCode Tag not as expected");
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateReasonText(postingStatusActualList.get(XSDFile.reasonTextTagName));
	    Component.assertTrue(assertFlagCheck, "ReasonText Tag not as expected");
	    		
	}
	
	/*
	 * Test Case ID: 60854
	 * Test Description: Validate the RespDetail Attributes data types.  
	 * Author: Himanshu Malhotra
	 * Date: 20-Feb-2017
	 * Comments:
	 */
	
	@Test(priority = 4)
	public void test_case_60854()throws SAXException, IOException, ParserConfigurationException
	{
		HashMap<String, String> postingRespDetailActualList = new HashMap<String, String>();
		postingRespDetailActualList = XSDFile.getXSDFileTagwiseDataForRespDetail();
		boolean assertFlagCheck;
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateRicherData(postingRespDetailActualList.get(XSDFile.richerDataTagName));
	    Component.assertTrue(assertFlagCheck, "RicherData Tag not as expected");
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateFraudStatus(postingRespDetailActualList.get(XSDFile.fraudStatusTagName));
	    Component.assertTrue(assertFlagCheck, "FraudStatus Tag not as expected");
		
		assertFlagCheck = PostingResponseHeaderValidationScenarios.validateFraudReason(postingRespDetailActualList.get(XSDFile.fraudReasonTagName));
	    Component.assertTrue(assertFlagCheck, "FraudReason Tag not as expected");
	    
	    HashMap<String, String> postingTrailerActualList = new HashMap<String, String>();
	    postingTrailerActualList = XSDFile.getXSDFileTagwiseDataForTrailer();
		boolean assertFlagCheck1;
		
		assertFlagCheck1 = PostingResponseHeaderValidationScenarios.validateTranCount(postingTrailerActualList.get(XSDFile.tranCountTagName));
	    Component.assertTrue(assertFlagCheck, "Tran Count Tag not as expected");
	    
	    		
	}
	
	
	
	

}
