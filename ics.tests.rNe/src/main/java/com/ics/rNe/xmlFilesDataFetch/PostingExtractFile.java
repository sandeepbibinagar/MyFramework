
package com.ics.rNe.xmlFilesDataFetch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.jetty.html.Input;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.gargoylesoftware.htmlunit.javascript.host.file.FileReader;
import com.gargoylesoftware.htmlunit.util.StringUtils;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;

/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class PostingExtractFile extends GenericMethodUtilis {

	//ArrayList For Row Count
//	static ArrayList<Integer> rc=new ArrayList<Integer>();
	public static int initialSize = 0;
	static int finalSize = 0;
	static String fileNameFromDB=null;

	static ArrayList<String> recordList=new ArrayList<>();

	//Posting Header Attributes
	public static String schemaTagName = "Schema";
	public static String procDateTagName = "ProcDate";
	public static String participantTagName = "Participant";
	public static String sequenceTagName = "Sequence";
	public static String versionTagName = "Version";
	public static String fileDateTagName = "FileDate";
	public static String environmentTagName = "Environment";
	//public static String currencyTagName = "Currency";
	//public static String weekdayTagName = "Weekday";

	//Record Attributes
	public static String sequenceRTagName = "Sequence";
	public static String postTypeTagName = "PostType";
	public static String subTypeTagName = "SubType";
	public static String sourceMsgTagName = "SourceMsg";
	public static String channelTagName = "Channel";
	public static String entryTypeTagName = "EntryType";
	public static String postingSourceTagName = "PostingSource";
	public static String respIDSourceTagName = "RespIDSource";
	public static String postDayTagName = "PostDay";
	public static String clearDateTagName = "ClearDate";
	public static String settDateTagName = "SettDate";
	public static String captDateTagName = "CaptDate";
	public static String amountTagName = "Amount";
	public static String tranSetIDTagName = "TranSetID";
	public static String reasonTagName = "Reason";
	public static String overrideTagName = "Override";
	public static String nPASortTagName = "NPASort";
	public static String numChequesTagName = "NumCheques";
	public static String collPartTagName = "CollPart";
	public static String collLocnTagName = "CollLocn";

	//Debit Attributes
	public static String transIdDRTagName = "TransID";
	public static String sortCodeDRTagName = "SortCode";
	public static String accNumDRTagName = "AccNum";
	public static String serNumDRTagName = "SerNum";
	public static String tranCodeDRTagName = "TranCode";
	public static String debitFullAmountDRTagName = "DebitFullAmt";
	public static String switchSortDRTagName = "SwitchSort";
	public static String switchAccDRTagName = "SwitchAcc";

	//Credit Attributes
	public static String transIdCRTagName = "TransID";
	public static String sortCodeCRTagName = "SortCode";
	public static String accNumCRTagName = "AccNum";
	public static String refCRTagName = "Ref";
	public static String tranCodeCRTagName = "TranCode";
	public static String origAmtCRTagName = "OrigAmt";
	public static String beneficiaryCRTagName = "Beneficiary";

	//Debit Detail Attributes
	public static String defSortTagName = "DefSort";
	public static String defAccTagName = "DefAcc";
	public static String representTagName = "Represent";
	public static String frontQualTagName = "FrontQual";
	public static String rearQualTagName = "RearQual";
	public static String chanRiskTagName = "ChanRisk";
	public static String fraudCodeTagName = "FraudCode";
	public static String fraudReasonTagName = "FraudReason";
	public static String fraudNameTagName = "FraudName";
	public static String duplicateTagName = "Duplicate";
	public static String dupIdTagName = "DupId";
	public static String dupStatusTagName = "DupStatus";
	public static String dupSeenTagName = "DupSeen";
	public static String dupCollectTagName = "DupCollect";
	public static String dupCaptureTagName = "DupCapture";
	public static String dupSourceTagName = "DupSource";
	public static String stoppedTagName = "Stopped";
	public static String stopDateTagName = "StopDate";
	public static String stopStatusTagName = "StopStatus";	
	public static String stopAmtTagName = "StopAmt";
	public static String stopNameTagName = "StopName";
	public static String stopStartTagName = "StopStart";
	public static String stopEndTagName = "StopEnd";
	public static String repSortTagName = "RepSort";
	public static String repAccTagName = "RepAcc";
	public static String repAmtTagName = "RepAmt";
	public static String repSerTagName = "RepSer";
	public static String exceptionTagName = "Exception";

	//PTMR01 Tags
	public static String MSGIDTAGNAME = "MsgId";

	//Core Node Attributes
	public static String businessDateTagName = "BusinessDate";
	public static String extractIdTagName = "ExtractId";
	public static String intMessageTypeTagName = "IntMessageType";
	public static String processingParticipantIdTagName = "ProcessingParticipantId";
	public static String sequenceCoreNodeTagName = "Sequence";
	public static String extMessageTypeTagName = "ExtMessageType";
	public static String messageSourceTagName = "MessageSource";
	public static String messageDestinationTagName = "MessageDestination";
	public static String recordCountsTagName = "RecordCounts";

	//Entities Attributes in PERM01 
	public static String entityTypeTagName = "EntityType";
	public static String entityStateTagName = "EntityState";
	public static String entityIdTagName = "EntityId";
	public static String sourceDateTimeTagName = "SourceDateTime";

	//Posting Update Attributes
	public static String postingExtractIdTagName = "PostingExtractId";
	public static String extractSequenceTagName = "ExtractSequence";
	public static String extractRevisionTagName = "ExtractRevision";
	public static String fileIdTagName = "FileId";
	public static String weekdayTagName = "WeekDay";
	public static String fileTypeTagName = "FileType";
	public static String currencyTagName = "Currency";
	public static String environmentPostingUpdateTagName = "Environment";
	public static String extractStartDateTimeTagName = "ExtractStartDateTime";
	public static String extractEndDateTimeTagName = "ExtractEndDateTime";
	public static String extractItemCountTagName = "ExtractItemCount";

	//Posting Item Entry Attributes
	public static String itemIdTagName = "ItemId";
	public static String postingAttemptTagName = "PostingAttempt";
	public static String postingSequenceTagName = "PostingSequence";
	public static String postingTypeTagName = "PostingType";
	public static String postingSubTypeTagName = "PostingSubType";
	public static String channelPostingItemEntryTagName = "Channel";
	public static String postingDrCrEntryTagName = "PostingDrCrEntry";
	public static String postingSourcePostingItemEntryTagName = "PostingSource";
	public static String responseIdSourceTagName = "ResponseIDSource";
	public static String postingDayTagName = "PostingDay";
	public static String clearingDateTagName = "ClearingDate";
	public static String settlementDateTagName = "SettlementDate";
	public static String postedAmmountTagName = "PostedAmount";
	public static String postingDecisionReasonCodeTagName = "PostingDecisionReasonCode";
	public static String postingOverrideFlagTagName = "PostingOverrideFlag";
	public static String nPASortCodeTagName = "NPASortCode";
	public static String nPAAccountTagName = "NPAAccount";
	public static String supportingInfoTagName = "SupportingInfo";
	public static String chequeCountTagName = "ChequeCount";
	public static String collectingParticipantIDTagName = "CollectingParticipantID";
	public static String collectingLocationTagName = "CollectingLocation";
	public static String aggregratedTagName = "Aggregated";

	//Debit Record Attributes
	public static String itemIdDrTagName = "ItemId";
	public static String sortCodeDrTagName = "SortCode";
	public static String accountDrTagName = "Account";
	public static String serialDrTagName = "Serial";
	public static String tranCodeDrTagName = "TranCode";
	public static String switchedSortCodeTagName = "SwitchedSortCode";
	public static String switchedAccountTagName = "SwitchedAccount";
	public static String debitFullAmountTagName = "DebitFullAmount";

	//Credit Record Attributes
	public static String itemIdCrTagName = "ItemId";
	public static String sortCodeCrTagName = "SortCode";
	public static String accountCrTagName = "Account";
	public static String referenceCrTagName = "Reference";
	public static String tranCodeCrTagName = "TranCode";
	public static String originalAmountTagName = "OriginalAmount";
	public static String originalSortCodeTagName = "OriginalSortCode";
	public static String originalAccountTagName = "OriginalAccount";
	public static String creditExceptionCodeTagName = "CreditExceptionCode";
	public static String beneficiaryNameTagName = "BeneficiaryName";

	//Headers
	public static String PostingResponseHeader = "Header";
	public static String PostingResponseRecord ="Record";
	public static String PostingResponseDebit = "Debit";
	public static String PostingResponseCredit = "Credit";
	public static String PostingResponseDebDetail = "DebDetail";
	public static String CoreNodeHeaderTagName = "Core";
	public static String EntitiesHeaderTagName = "Entity";
	public static String PostingUpdateHeaderTagName = "PostingExtractUpdate";
	public static String PostingItemEntryHeaderTagName = "PostingItemEntry";
	public static String CreditRecordHeaderTagName = "CreditRecord";
	public static String DebitRecordHeaderTagName = "DebitRecord";

	//File Path and Name for different .sql and XML file types
	public static String filePath = GenericMethodUtilis.getrNeResourceFile().getString("filePath");
	public static String fileName = GenericMethodUtilis.getrNeResourceFile().getString("fileName");

	public static String filePTMR01Path = GenericMethodUtilis.getrNeResourceFile().getString("filePTMR01Path");
	public static String filePTMR01Name = GenericMethodUtilis.getrNeResourceFile().getString("filePTMR01Name");

	private static String filePath1 = GenericMethodUtilis.getrNeResourceFile().getString("filePath1");
	private static String fileName1=  GenericMethodUtilis.getrNeResourceFile().getString("fileName1");

	private static String filePath2 = GenericMethodUtilis.getrNeResourceFile().getString("filePath2");
	private static String fileName2=  GenericMethodUtilis.getrNeResourceFile().getString("fileName2");

	private static String filePath3 = GenericMethodUtilis.getrNeResourceFile().getString("filePath3");
	private static String fileName3=  GenericMethodUtilis.getrNeResourceFile().getString("fileName3");

	private static String filePath4 = GenericMethodUtilis.getrNeResourceFile().getString("filePath4");
	private static String fileName4=  GenericMethodUtilis.getrNeResourceFile().getString("fileName4");

	private static String filePath5 = GenericMethodUtilis.getrNeResourceFile().getString("filePath5");
	private static String fileName5=  GenericMethodUtilis.getrNeResourceFile().getString("fileName5");

	private static String filePath6 = GenericMethodUtilis.getrNeResourceFile().getString("filePath6");
	private static String fileName6=  GenericMethodUtilis.getrNeResourceFile().getString("fileName6");

	private static String filePath7 = GenericMethodUtilis.getrNeResourceFile().getString("filePath7");
	private static String fileName7=  GenericMethodUtilis.getrNeResourceFile().getString("fileName7");

	private static String dbArchiveServerName = GenericMethodUtilis.getrNeResourceFile().getString("dbArchiveServerName");
	private static String rNeArchiveDatabaseName = GenericMethodUtilis.getrNeResourceFile().getString("rNeArchiveDatabaseName");

	public static String firstMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
			filePath1 + fileName1 + "_Actual.sql ";

	public static String secondMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
			filePath2 + fileName2 + "_Actual.sql ";

	public static String thirdMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
			filePath3 + fileName3 + "_Actual.sql ";

	public static String fourthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
			filePath4 + fileName4 + "_Actual.sql ";

	public static String fifthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
			filePath5 + fileName5 + "_Actual.sql ";

	public static String sixthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
			filePath6 + fileName6 + "_Actual.sql ";

	public static String seventhMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
			filePath7 + fileName7 + "_Actual.sql ";



	public static String xsdFileResponseHeaderTagName = "ResponseHeader";

	public static String readFromPTMR01= "BnfcryTxSetEarlyNtfctn";

	public static String xsdFileResponseTagName = "Response";

	public static String xsdFileStatusTagName = "Status";

	public static String xsdFileRespDetailTagName = "RespDetail";

	public static String xsdFileTrailerTagName = "Trailer";

	public static HashMap<String, String> responseHeaderSetTagWiseData;
	public static HashMap<String, String> responseRecordSetTagWiseData;
	public static HashMap<String, String> responseDebitSetTagWiseData;
	public static HashMap<String, String> responseCreditSetTagWiseData;
	public static HashMap<String, String> responseDebitDetailSetTagWiseData;
	public static HashMap<String, String> coreNodeSetTagWiseData;
	public static HashMap<String, String> entitiesSetTagWiseData;
	public static HashMap<String, String> postingUpdateSetTagWiseData;
	public static HashMap<String, String> postingItemEntrySetTagWiseData;
	public static HashMap<String, String> debitRecordSetTagWiseData;
	public static HashMap<String, String> creditRecordSetTagWiseData;

	public static String responseStatusTagName = "RespStatus";

	/* 
	  Method Name: getTagwiseDataForResponseHeader
	  Author: Himanshu Malhotra
	  Created Date: 13/04/2017
	  Description: This method gets XML attributes in a result set for validation.
	  Last Modified By: Himanshu Malhotra
	  Last Modified Date: 13/04/2017
	  Last Modified By: 
	  Modified Date:
	  Change Details:
	 */
	/*******************************************************************************************************************************************************************************************************************************/
	public static void getTagwiseDataForPostingExtract() throws SAXException, IOException, ParserConfigurationException
	{	

		NodeList nodeSetList = getNodeSetList(filePath,fileName,PostingResponseHeader);

		responseHeaderSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
		{

			Node eachNode = nodeSetList.item(eachNodeSetEntry);

			if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				System.out.println("in if");
				Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));

				responseHeaderSetTagWiseData.put(schemaTagName, eachElement.getElementsByTagName(schemaTagName).item(0).getTextContent());	
			
				responseHeaderSetTagWiseData.put(procDateTagName, eachElement.getElementsByTagName(procDateTagName).item(0).getTextContent());	
				
				responseHeaderSetTagWiseData.put(participantTagName, eachElement.getElementsByTagName(participantTagName).item(0).getTextContent());					
				
				responseHeaderSetTagWiseData.put(sequenceTagName, eachElement.getElementsByTagName(sequenceTagName).item(0).getTextContent());	
				
				responseHeaderSetTagWiseData.put(versionTagName, eachElement.getElementsByTagName(versionTagName).item(0).getTextContent());		
				
				responseHeaderSetTagWiseData.put(fileDateTagName, eachElement.getElementsByTagName(fileDateTagName).item(0).getTextContent());	
				
				responseHeaderSetTagWiseData.put(environmentTagName, eachElement.getElementsByTagName(environmentTagName).item(0).getTextContent());
				
				responseHeaderSetTagWiseData.put(currencyTagName, eachElement.getElementsByTagName(currencyTagName).item(0).getTextContent());	
				
				responseHeaderSetTagWiseData.put(weekdayTagName, eachElement.getElementsByTagName(weekdayTagName).item(0).getTextContent());									
				

			}
		}

		NodeList nodeSetList1 = getNodeSetList(filePath,fileName,PostingResponseRecord);
		responseRecordSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList1.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList1.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList1.item(eachNodeSetEntry);
				responseRecordSetTagWiseData.put(sequenceRTagName, eachElement.getElementsByTagName(sequenceRTagName).item(0).getTextContent());
				
				responseRecordSetTagWiseData.put(postTypeTagName, eachElement.getElementsByTagName(postTypeTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(subTypeTagName, eachElement.getElementsByTagName(subTypeTagName).item(0).getTextContent());					
				responseRecordSetTagWiseData.put(sourceMsgTagName, eachElement.getElementsByTagName(sourceMsgTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(channelTagName, eachElement.getElementsByTagName(channelTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(entryTypeTagName, eachElement.getElementsByTagName(entryTypeTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(postingSourceTagName, eachElement.getElementsByTagName(postingSourceTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(respIDSourceTagName, eachElement.getElementsByTagName(respIDSourceTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(postDayTagName, eachElement.getElementsByTagName(postDayTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(clearDateTagName, eachElement.getElementsByTagName(clearDateTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(settDateTagName, eachElement.getElementsByTagName(settDateTagName).item(0).getTextContent());					
				responseRecordSetTagWiseData.put(captDateTagName, eachElement.getElementsByTagName(captDateTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(amountTagName, eachElement.getElementsByTagName(amountTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(tranSetIDTagName, eachElement.getElementsByTagName(tranSetIDTagName).item(0).getTextContent());	
				//responseRecordSetTagWiseData.put(reasonTagName, eachElement.getElementsByTagName(reasonTagName).item(0).getTextContent());	
				//responseRecordSetTagWiseData.put(overrideTagName, eachElement.getElementsByTagName(overrideTagName).item(0).getTextContent());	
				//responseRecordSetTagWiseData.put(nPASortTagName, eachElement.getElementsByTagName(nPASortTagName).item(0).getTextContent());	
				//responseRecordSetTagWiseData.put(numChequesTagName, eachElement.getElementsByTagName(numChequesTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(collPartTagName, eachElement.getElementsByTagName(collPartTagName).item(0).getTextContent());	
				responseRecordSetTagWiseData.put(collLocnTagName, eachElement.getElementsByTagName(collLocnTagName).item(0).getTextContent());	
				
			}
		}	

		NodeList nodeSetList2 = getNodeSetList(filePath,fileName, PostingResponseDebit);
		responseDebitSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList2.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList2.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList2.item(eachNodeSetEntry);
				responseDebitSetTagWiseData.put(transIdDRTagName, eachElement.getElementsByTagName(transIdDRTagName).item(0).getTextContent());	
				
				responseDebitSetTagWiseData.put(sortCodeDRTagName, eachElement.getElementsByTagName(sortCodeDRTagName).item(0).getTextContent());	
				responseDebitSetTagWiseData.put(accNumDRTagName, eachElement.getElementsByTagName(accNumDRTagName).item(0).getTextContent());					
				responseDebitSetTagWiseData.put(serNumDRTagName, eachElement.getElementsByTagName(serNumDRTagName).item(0).getTextContent());	
				responseDebitSetTagWiseData.put(tranCodeDRTagName, eachElement.getElementsByTagName(tranCodeDRTagName).item(0).getTextContent());
				responseDebitSetTagWiseData.put(switchSortDRTagName, eachElement.getElementsByTagName(switchSortDRTagName).item(0).getTextContent());
				responseDebitSetTagWiseData.put(switchAccDRTagName, eachElement.getElementsByTagName(switchAccDRTagName).item(0).getTextContent());
				//responseDebitSetTagWiseData.put(debitFullAmountDRTagName, eachElement.getElementsByTagName(debitFullAmountDRTagName).item(0).getTextContent());	
				
			}
		}

		NodeList nodeSetList3 = getNodeSetList(filePath,fileName, PostingResponseCredit);
		responseCreditSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList3.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList3.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList3.item(eachNodeSetEntry);
				responseCreditSetTagWiseData.put(transIdCRTagName, eachElement.getElementsByTagName(transIdCRTagName).item(0).getTextContent());	
				responseCreditSetTagWiseData.put(sortCodeCRTagName, eachElement.getElementsByTagName(sortCodeCRTagName).item(0).getTextContent());	
				responseCreditSetTagWiseData.put(accNumCRTagName, eachElement.getElementsByTagName(accNumCRTagName).item(0).getTextContent());					
				responseCreditSetTagWiseData.put(refCRTagName, eachElement.getElementsByTagName(refCRTagName).item(0).getTextContent());	
				//responseCreditSetTagWiseData.put(tranCodeCRTagName, eachElement.getElementsByTagName(tranCodeCRTagName).item(0).getTextContent());	
				//responseCreditSetTagWiseData.put(origAmtCRTagName, eachElement.getElementsByTagName(origAmtCRTagName).item(0).getTextContent());
				//responseCreditSetTagWiseData.put(beneficiaryCRTagName, eachElement.getElementsByTagName(beneficiaryCRTagName).item(0).getTextContent());
				
			}
		}
		/*
		NodeList nodeSetList4 = getNodeSetList(filePath,fileName, PostingResponseDebDetail);
		responseDebitDetailSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList4.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList4.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList4.item(eachNodeSetEntry);
				responseDebitDetailSetTagWiseData.put(defSortTagName, eachElement.getElementsByTagName(defSortTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(defAccTagName, eachElement.getElementsByTagName(defAccTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(representTagName, eachElement.getElementsByTagName(representTagName).item(0).getTextContent());					
				responseDebitDetailSetTagWiseData.put(frontQualTagName, eachElement.getElementsByTagName(frontQualTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(rearQualTagName, eachElement.getElementsByTagName(rearQualTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(chanRiskTagName, eachElement.getElementsByTagName(chanRiskTagName).item(0).getTextContent());
				responseDebitDetailSetTagWiseData.put(fraudCodeTagName, eachElement.getElementsByTagName(fraudCodeTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(fraudReasonTagName, eachElement.getElementsByTagName(fraudReasonTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(fraudNameTagName, eachElement.getElementsByTagName(fraudNameTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(duplicateTagName, eachElement.getElementsByTagName(duplicateTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(dupIdTagName, eachElement.getElementsByTagName(dupIdTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(dupStatusTagName, eachElement.getElementsByTagName(dupStatusTagName).item(0).getTextContent());					
				//responseDebitDetailSetTagWiseData.put(dupSeenTagName, eachElement.getElementsByTagName(dupSeenTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(dupCollectTagName, eachElement.getElementsByTagName(dupCollectTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(dupCaptureTagName, eachElement.getElementsByTagName(dupCaptureTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(dupSourceTagName, eachElement.getElementsByTagName(dupSourceTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(stoppedTagName, eachElement.getElementsByTagName(stoppedTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(stopDateTagName, eachElement.getElementsByTagName(stopDateTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(stopStatusTagName, eachElement.getElementsByTagName(stopStatusTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(stopAmtTagName, eachElement.getElementsByTagName(stopAmtTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(stopNameTagName, eachElement.getElementsByTagName(stopNameTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(stopStartTagName, eachElement.getElementsByTagName(stopStartTagName).item(0).getTextContent());	
				//responseDebitDetailSetTagWiseData.put(stopEndTagName, eachElement.getElementsByTagName(stopEndTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(repSortTagName, eachElement.getElementsByTagName(repSortTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(repAccTagName, eachElement.getElementsByTagName(repAccTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(repAmtTagName, eachElement.getElementsByTagName(repAmtTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(repSerTagName, eachElement.getElementsByTagName(repSerTagName).item(0).getTextContent());	
				responseDebitDetailSetTagWiseData.put(exceptionTagName, eachElement.getElementsByTagName(exceptionTagName).item(0).getTextContent());		

			}
		}*/
		//return responseHeaderSetTagWiseData;
	}

	public static void getPERM01TagwiseData() throws SAXException, IOException, ParserConfigurationException, Exception
	{
		
		coreNodeSetTagWiseData = new LinkedHashMap<String, String>();
		NodeList nodeSetList = getNodeSetList(filePath,fileName,CoreNodeHeaderTagName);

		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				coreNodeSetTagWiseData.put(businessDateTagName, eachElement.getElementsByTagName(businessDateTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(extractIdTagName, eachElement.getElementsByTagName(extractIdTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(intMessageTypeTagName, eachElement.getElementsByTagName(intMessageTypeTagName).item(0).getTextContent());
				//coreNodeSetTagWiseData.put(sequenceCoreNodeTagName, eachElement.getElementsByTagName(sequenceCoreNodeTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(processingParticipantIdTagName, eachElement.getElementsByTagName(processingParticipantIdTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(extMessageTypeTagName, eachElement.getElementsByTagName(extMessageTypeTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(messageSourceTagName, eachElement.getElementsByTagName(messageSourceTagName).item(0).getTextContent());	
				//coreNodeSetTagWiseData.put(recordCountsTagName, eachElement.getElementsByTagName(recordCountsTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(messageDestinationTagName, eachElement.getElementsByTagName(messageDestinationTagName).item(0).getTextContent());						
				
			}
		}

		NodeList nodeSetList2 = getNodeSetList(filePath, fileName, PostingUpdateHeaderTagName);
		postingUpdateSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList2.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList2.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList2.item(eachNodeSetEntry);
				//postingUpdateSetTagWiseData.put(postingExtractIdTagName, eachElement.getElementsByTagName(postingExtractIdTagName).item(0).getTextContent());	
				postingUpdateSetTagWiseData.put(extractSequenceTagName, eachElement.getElementsByTagName(extractSequenceTagName).item(0).getTextContent());	
				postingUpdateSetTagWiseData.put(extractRevisionTagName, eachElement.getElementsByTagName(extractRevisionTagName).item(0).getTextContent());					
				postingUpdateSetTagWiseData.put(fileIdTagName, eachElement.getElementsByTagName(fileIdTagName).item(0).getTextContent());	
				postingUpdateSetTagWiseData.put(weekdayTagName, eachElement.getElementsByTagName(weekdayTagName).item(0).getTextContent());	
				postingUpdateSetTagWiseData.put(fileTypeTagName, eachElement.getElementsByTagName(fileTypeTagName).item(0).getTextContent());	
				postingUpdateSetTagWiseData.put(currencyTagName, eachElement.getElementsByTagName(currencyTagName).item(0).getTextContent());					
				postingUpdateSetTagWiseData.put(environmentTagName, eachElement.getElementsByTagName(environmentTagName).item(0).getTextContent());
				postingUpdateSetTagWiseData.put(extractStartDateTimeTagName, eachElement.getElementsByTagName(extractStartDateTimeTagName).item(0).getTextContent());	
				postingUpdateSetTagWiseData.put(extractEndDateTimeTagName, eachElement.getElementsByTagName(extractEndDateTimeTagName).item(0).getTextContent());					
				postingUpdateSetTagWiseData.put(extractItemCountTagName, eachElement.getElementsByTagName(extractItemCountTagName).item(0).getTextContent());
				

			}
		}

		NodeList nodeSetList3 = getNodeSetList(filePath, fileName, PostingItemEntryHeaderTagName);
		postingItemEntrySetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList3.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList3.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList3.item(eachNodeSetEntry);
				postingItemEntrySetTagWiseData.put(itemIdTagName, eachElement.getElementsByTagName(itemIdTagName).item(0).getTextContent());	
				postingItemEntrySetTagWiseData.put(postingAttemptTagName, eachElement.getElementsByTagName(postingAttemptTagName).item(0).getTextContent());	
				postingItemEntrySetTagWiseData.put(postingSequenceTagName, eachElement.getElementsByTagName(postingSequenceTagName).item(0).getTextContent());					
				postingItemEntrySetTagWiseData.put(postingSubTypeTagName, eachElement.getElementsByTagName(postingSubTypeTagName).item(0).getTextContent());	
				postingItemEntrySetTagWiseData.put(postingTypeTagName, eachElement.getElementsByTagName(postingTypeTagName).item(0).getTextContent());	
				postingItemEntrySetTagWiseData.put(channelPostingItemEntryTagName, eachElement.getElementsByTagName(channelPostingItemEntryTagName).item(0).getTextContent());	
				postingItemEntrySetTagWiseData.put(postingDrCrEntryTagName, eachElement.getElementsByTagName(postingDrCrEntryTagName).item(0).getTextContent());					
				postingItemEntrySetTagWiseData.put(postingSourcePostingItemEntryTagName, eachElement.getElementsByTagName(postingSourcePostingItemEntryTagName).item(0).getTextContent());
				postingItemEntrySetTagWiseData.put(responseIdSourceTagName, eachElement.getElementsByTagName(responseIdSourceTagName).item(0).getTextContent());	
				postingItemEntrySetTagWiseData.put(postingDayTagName, eachElement.getElementsByTagName(postingDayTagName).item(0).getTextContent());		
				postingItemEntrySetTagWiseData.put(clearingDateTagName, eachElement.getElementsByTagName(clearingDateTagName).item(0).getTextContent());
				postingItemEntrySetTagWiseData.put(settlementDateTagName, eachElement.getElementsByTagName(settlementDateTagName).item(0).getTextContent());	
				postingItemEntrySetTagWiseData.put(postedAmmountTagName, eachElement.getElementsByTagName(postedAmmountTagName).item(0).getTextContent());					
				//postingItemEntrySetTagWiseData.put(postingDecisionReasonCodeTagName, eachElement.getElementsByTagName(postingDecisionReasonCodeTagName).item(0).getTextContent());				
				postingItemEntrySetTagWiseData.put(postingOverrideFlagTagName, eachElement.getElementsByTagName(postingOverrideFlagTagName).item(0).getTextContent());	
				//postingItemEntrySetTagWiseData.put(nPASortCodeTagName, eachElement.getElementsByTagName(nPASortCodeTagName).item(0).getTextContent());					
				//postingItemEntrySetTagWiseData.put(nPAAccountTagName, eachElement.getElementsByTagName(nPAAccountTagName).item(0).getTextContent());
				//postingItemEntrySetTagWiseData.put(supportingInfoTagName, eachElement.getElementsByTagName(supportingInfoTagName).item(0).getTextContent());	
				//postingItemEntrySetTagWiseData.put(chequeCountTagName, eachElement.getElementsByTagName(chequeCountTagName).item(0).getTextContent());					
				postingItemEntrySetTagWiseData.put(collectingParticipantIDTagName, eachElement.getElementsByTagName(collectingParticipantIDTagName).item(0).getTextContent());	
				postingItemEntrySetTagWiseData.put(collectingLocationTagName, eachElement.getElementsByTagName(collectingLocationTagName).item(0).getTextContent());					
				postingItemEntrySetTagWiseData.put(aggregratedTagName, eachElement.getElementsByTagName(aggregratedTagName).item(0).getTextContent());
				

			}
		}

		NodeList nodeSetList4 = getNodeSetList(filePath, fileName, DebitRecordHeaderTagName);
		debitRecordSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList4.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList4.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList4.item(eachNodeSetEntry);
				debitRecordSetTagWiseData.put(itemIdDrTagName, eachElement.getElementsByTagName(itemIdDrTagName).item(0).getTextContent());	
				debitRecordSetTagWiseData.put(sortCodeDrTagName, eachElement.getElementsByTagName(sortCodeDrTagName).item(0).getTextContent());	
				debitRecordSetTagWiseData.put(accountDrTagName, eachElement.getElementsByTagName(accountDrTagName).item(0).getTextContent());					
				debitRecordSetTagWiseData.put(serialDrTagName, eachElement.getElementsByTagName(serialDrTagName).item(0).getTextContent());	
				debitRecordSetTagWiseData.put(tranCodeDrTagName, eachElement.getElementsByTagName(tranCodeDrTagName).item(0).getTextContent());	
				//debitRecordSetTagWiseData.put(switchedSortCodeTagName, eachElement.getElementsByTagName(switchedSortCodeTagName).item(0).getTextContent());	
				//debitRecordSetTagWiseData.put(switchedAccountTagName, eachElement.getElementsByTagName(switchedAccountTagName).item(0).getTextContent());					
				debitRecordSetTagWiseData.put(debitFullAmountTagName, eachElement.getElementsByTagName(debitFullAmountTagName).item(0).getTextContent());
				

			}
		}

		NodeList nodeSetList5 = getNodeSetList(filePath, fileName, CreditRecordHeaderTagName);
		creditRecordSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList5.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList5.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList5.item(eachNodeSetEntry);
				creditRecordSetTagWiseData.put(itemIdCrTagName, eachElement.getElementsByTagName(itemIdCrTagName).item(0).getTextContent());	
				creditRecordSetTagWiseData.put(sortCodeCrTagName, eachElement.getElementsByTagName(sortCodeCrTagName).item(0).getTextContent());	
				creditRecordSetTagWiseData.put(accountCrTagName, eachElement.getElementsByTagName(accountCrTagName).item(0).getTextContent());					
				creditRecordSetTagWiseData.put(referenceCrTagName, eachElement.getElementsByTagName(referenceCrTagName).item(0).getTextContent());	
				creditRecordSetTagWiseData.put(tranCodeCrTagName, eachElement.getElementsByTagName(tranCodeCrTagName).item(0).getTextContent());	
				creditRecordSetTagWiseData.put(originalAmountTagName, eachElement.getElementsByTagName(originalAmountTagName).item(0).getTextContent());	
				//creditRecordSetTagWiseData.put(originalSortCodeTagName, eachElement.getElementsByTagName(originalSortCodeTagName).item(0).getTextContent());					
				//creditRecordSetTagWiseData.put(originalAccountTagName, eachElement.getElementsByTagName(originalAccountTagName).item(0).getTextContent());
				//creditRecordSetTagWiseData.put(creditExceptionCodeTagName, eachElement.getElementsByTagName(creditExceptionCodeTagName).item(0).getTextContent());					
				creditRecordSetTagWiseData.put(beneficiaryNameTagName, eachElement.getElementsByTagName(beneficiaryNameTagName).item(0).getTextContent());
				
			}
		}


		//return coreNodeTagWiseData;		
	}

	public static ArrayList<String> getEntitySetsFromPERM01() throws SAXException, IOException, ParserConfigurationException, Exception
	{
		ArrayList<String> ar=new ArrayList<String>();

		String entityType1=null;
		String entityId1=null;
		String entityState1=null;

		String entityType2=null;
		String entityId2=null;
		String entityState2=null;

		String entityType3=null;
		String entityId3=null;
		String entityState3=null;

		String entityType4=null;
		String entityId4=null;
		String entityState4=null;

		ArrayList<String> entitySet1= new ArrayList<String>();		
		entitySet1= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//EntityType"),"EntityType");
		
		entityType1=entitySet1.get(0);
		entityType2=entitySet1.get(1);
		//entityType3=entitySet1.get(2);
		//entityType4=entitySet1.get(3);

		ArrayList<String> entitySet2= new ArrayList<String>();		
		entitySet2= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//EntityId"),"EntityId");
		
		entityId1=entitySet2.get(0);
		entityId2=entitySet2.get(1);
		//entityId3=entitySet2.get(2);
		//entityId4=entitySet2.get(3);

		ArrayList<String> entitySet3= new ArrayList<String>();		
		entitySet3= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//EntityState"),"EntityState");
	
		entityState1=entitySet3.get(0);
		entityState2=entitySet3.get(1);
		//entityState3=entitySet3.get(2);
		//entityState4=entitySet3.get(3);

		ar.add(entityType1);
		ar.add(entityId1);
		ar.add(entityState1);

		ar.add(entityType2);
		ar.add(entityId2);
		ar.add(entityState2);

		/*ar.add(entityType3);
				ar.add(entityId3);
				ar.add(entityState3);

				ar.add(entityType4);
				ar.add(entityId4);
				ar.add(entityState4);*/

		return ar;

	}
	/******************************************************************************************************************************************************************************************************************************/
	/* 
	 * Method Name: validatePostingResponseHeaderXMLAttributes
	 * Author: Himanshu Malhotra
	 * Created Date: 14/03/2017
	 * Description: This method compares RnE table values with XML values.
	 * Last Modified By: Himanshu Malhotra
	 * Last Modified Date: 14/03/2017
	 */	
	/******************************************************************************************************************************************************************************************************************************/
	public static ResultSet validatePostingResponseHeaderXMLAttributes() throws Exception
	{
		String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");			
		String query = " select top 1 * from Posting.RNEPostingExtract "
				+ "where InternalId in ('" + GenericMethodUtilis.getrNeResourceFile().getString("internalId")+"')"
				+ " order by 1 desc";

		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(server, database, query);		
		return rs;
	}


	private static NodeList getNodeSetList(String FilePath, String FileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(FilePath, FileName)).getElementsByTagName(elementsSetWithTagName);		
	}	

	public static ArrayList<String> readDataFromPTMR01() throws SAXException, IOException, ParserConfigurationException
	{	
		ArrayList<String> ar=new ArrayList<String>();
		String extractIdValue=null;
		String postingExtractIdvalue=null;
		FileInputStream inputStream= new FileInputStream(GenericMethodUtilis.getrNeResourceFile().getString("ptmr01Path"));
		try {
			String all=IOUtils.toString(inputStream);

			int i=all.indexOf("<ExtractId>");
			int j=all.indexOf("<PostingExtractId>");
			int k=all.indexOf("</ExtractId>");
			int m=all.indexOf("</PostingExtractId>");
			String extract1=(String) all.subSequence(i+11,k);
			String extract2=(String) all.subSequence(j+18,m);
			extractIdValue=extract1;
			postingExtractIdvalue=extract2;			
			String extractId=(String) all.subSequence(i+11, i+25+12);
			String postingExtractId=(String) all.subSequence(j+18,j+25+12+7);			
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputSource src= new InputSource();
			src.setCharacterStream(new StringReader(all));
			ar.add(extractIdValue);
			ar.add(postingExtractIdvalue);

			//Document doc =builder.parse(src);

		}
		finally{
			inputStream.close();
		}

		return ar;

	}

	public static ArrayList<String> readDataFrom05MA01() throws SAXException, IOException, ParserConfigurationException
	{	
		ArrayList<String> ar=new ArrayList<String>();
		String srcValue=null;
		boolean flag=false;

		FileInputStream inputStream= new FileInputStream(GenericMethodUtilis.getrNeResourceFile().getString("05ma01Path"));
		try {
			String all=IOUtils.toString(inputStream);

			int i=all.indexOf("<Src>");

			int k=all.indexOf("</Src>");

			String extract1=(String) all.subSequence(i+5,k);

			if(extract1!= null){
				flag=true;
				srcValue=extract1;
				System.out.println(srcValue);
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource src= new InputSource();
				src.setCharacterStream(new StringReader(all));
				ar.add(srcValue);
				publishResults(flag,srcValue,"Src Tag Should not be null","Src Tag Validation for 05ma01");

			}
			else{
				flag=false;
				publishResults(flag,srcValue,"Src Tag Should not be null","Src Tag Validation for 05ma01");
			}

			//Document doc =builder.parse(src);

		}
		finally{
			inputStream.close();
		}

		return ar;

	}

	public static ArrayList<String> readDataFrom13MA02() throws SAXException, IOException, ParserConfigurationException
	{	
		ArrayList<String> ar=new ArrayList<String>();
		String srcValue=null;
		boolean flag=false;

		FileInputStream inputStream= new FileInputStream(GenericMethodUtilis.getrNeResourceFile().getString("13ma02Path"));
		try {
			String all=IOUtils.toString(inputStream);

			int i=all.indexOf("<Src>");

			int k=all.indexOf("</Src>");

			String extract1=(String) all.subSequence(i+5,k);

			if(extract1!=null){
				flag=true;
				srcValue=extract1;	 
				System.out.println(srcValue);
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource src= new InputSource();
				src.setCharacterStream(new StringReader(all));
				ar.add(srcValue);
				publishResults(flag,srcValue,"Src Tag Should not be null","Src Tag Validation for 13ma02");				
			}
			else{
				flag=false;
				publishResults(flag,srcValue,"Src Tag Should not be null","Src Tag Validation for 13ma02");
			}



			//Document doc =builder.parse(src);

		}
		finally{
			inputStream.close();
		}

		return ar;

	}

	public static void getPERM01XML() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, SAXException, IOException, ParserConfigurationException
	{
		String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");
		String query = " select top 1 * from Posting.RNEMOQueueDetails "
				+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
				+ " order by 1 desc";
		String data = "";


		ResultSet rs =  ICSProductDBConnection.getICSDBServerConnection(server, database, query);

		while(rs.next())
		{
			data = rs.getString(12);
		}


		try(PrintWriter pw = new PrintWriter(new File(GenericMethodUtilis.getrNeResourceFile().getString("perm01FileSave"))))
		{
			pw.write(data);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void validateRnEMOQueueDetailsTableColumnsNotNull() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, SAXException, IOException, ParserConfigurationException
	{
		boolean flag=false;
		String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");
		String query = " select top 1 * from Posting.RNEMOQueueDetails "
				+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
				+ " and PostingId in ('" + readDataFromPTMR01().get(1)+"')"
				+ " order by 1 desc";

		

		ResultSet rs =  ICSProductDBConnection.getICSDBServerConnection(server, database, query);

		while(rs.next()){

			if (rs.getString(15)==null){
				publishResults(flag,"Empty Sets","Should not be null", "ExtractStartDate,ExtractEndDate and FileID validation");
			}		
			else{
				flag=true;
				publishResults(flag,"NOT Null","Should not be null", "ExtractStartDate,ExtractEndDate and FileID validation");
			}
		}

	}

	public static String validateRnEMOQueueDetailsTableFileID() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, SAXException, IOException, ParserConfigurationException
	{
		boolean flag=false;
	
		String pattern="(ICSPST[LT][0-9]{8}[0-9]{4}[0-9]{2})";
		String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");
		String query = " select top 1 * from Posting.RNEMOQueueDetails "
				+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
				+ " and PostingId in ('" + readDataFromPTMR01().get(1)+"')"
				+ " order by 1 desc";		

		ResultSet rs =  ICSProductDBConnection.getICSDBServerConnection(server, database, query);
		

		while(rs.next()){

			if (rs.getString(20)==null){
				publishResults(flag,"Empty Sets","Should not be null", "FileID validation from Table");
			}		
			else if(rs.getString(20).matches(pattern)){
				flag=true;
				fileNameFromDB=rs.getString(20);
				publishResults(flag,rs.getString(20),"FileName is as expected", "FileID Validation from Table");
				
			}
		}
		
		return fileNameFromDB;

	}

	public static void getAndValidateFileNameFromRootDirectory() throws Exception
	{
		boolean flag=false;
		String extractFileName=null;
		String pattern="(ICSPST[LT][0-9]{8}[0-9]{4}[0-9]{2}.xml)";
		try{

			File folder=new File(GenericMethodUtilis.getrNeResourceFile().getString("localPathForExtractFile"));
			File[] listOfFiles=folder.listFiles();
			for (int i=0;i<listOfFiles.length;i++){
				if(listOfFiles[i].isFile()){
					
					extractFileName=listOfFiles[i].getName();

					if(extractFileName.matches(pattern)){

						flag=true;
						publishResults(flag,extractFileName,"FileName is Correct","Extract FileName Validation");
					}
					else{
						publishResults(flag,extractFileName,"FileName Should be as per Pattern","Extract FileName Validation");
					}


				}
				else if(listOfFiles[i].isDirectory()){
					
				}
			}


		}

		catch(Exception e){
			System.out.println("Unhandled Exception");
		}
	}
	
	public static boolean validatePTMR01Refresh() throws Exception
	{
		boolean flag = false;
		String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");		

		String query = " select * from Posting.RNEMOQueueDetails "
				+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
				+ " and PostingId in ('" + readDataFromPTMR01().get(1)+"')"
				+ " order by 1 desc";
		
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(server, database, query);
		
		return flag;
	}


	public static boolean validatePTMR01Status() throws Exception
	{
		boolean flag = false;
		String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");		

		String query = " select top 1 status from Posting.RNEMOQueueDetails "
				+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
				+ " and PostingId in ('" + readDataFromPTMR01().get(1)+"')"
				+ " order by 1 desc";
		
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(server, database, query);

		int count = 0;
		while(rs.next())
		{
			count++;
		}
		

		if(count == 0)
		{
			
			publishResults(flag," 0 records Found","Record Should be present","Record Count Validation for PTMR01 status ");
		}
		else{
			rs.beforeFirst();

			while(rs.next())
			{
				if(rs.getString(1).equalsIgnoreCase("C")){
					flag= true;
					
					publishResults(flag,rs.getString(1),"Status Should be C"," PTMR01 Status Validation");
				}
				else{
					flag=false;
					
					publishResults(flag,rs.getString(1),"Status Should be C"," PTMR01 Status Validation");
				}
			}
		}
		return flag;
	}

	public static boolean validateExtractFileStatus71824() throws Exception
	{
		boolean flag = false;
		String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");

		String query = " select top 1 * from Posting.RNEPostingExtract "
				+ "where HeaderSequence in ('" + PostingExtractFile.responseHeaderSetTagWiseData.get("Sequence")+"')"
				+ " and HeaderVersion in ('" + PostingExtractFile.responseHeaderSetTagWiseData.get("Version")+"')"
				+ " order by 1 desc";
		
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(server, database, query);

		int count = 0;
		while(rs.next())
		{
			count++;
		}
		

		if(count == 0)
		{
			
		}
		else{
			rs.beforeFirst();

			while(rs.next())
			{
				if(rs.getString(19).equalsIgnoreCase("CNP")){
					flag= true;
					
				}
				else{
					flag=false;
					
				}
			}
		}
		return flag;
	}

	public static void copyExtractFileFromSharedToLocalFolder() throws Exception
	{
		String[] children =  new File(GenericMethodUtilis.getrNeResourceFile().getString("configPathForExtractFile")).list();
		String fileIDFromTable=null;
		boolean flag=false;
		
		String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");
		String query = " select top 1 * from Posting.RNEMOQueueDetails "
				+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
				+ " and PostingId in ('" + readDataFromPTMR01().get(1)+"')"
				+ " order by 1 desc";

		

		ResultSet rs =  ICSProductDBConnection.getICSDBServerConnection(server, database, query);

		while(rs.next()){

			fileIDFromTable=rs.getString(20)+".xml";
			for(int i = 0 ; i <children.length; i++)
			{
				String fileName = GenericMethodUtilis.getrNeResourceFile().getString("configPathForExtractFile") + children[i];
				if(children[i].equalsIgnoreCase(fileIDFromTable))
				{
					flag=true;
					FileUtils.copyFileToDirectory(new File(fileName), new File(GenericMethodUtilis.getrNeResourceFile().getString("localPathForExtractFile")));
					publishResults(flag,"File COPIED to local","ExtractFile found and copied to local","Extract File fetch and copy");

				}
				else{
					flag=false;
					publishResults(flag,"File NOT Copied to local","ExtractFile found and copied to local","Extract File fetch and copy");
				}
				
			}		

		
		}
	}

	public static boolean validateExtractFileStatus71827() throws Exception
	{
		boolean flag = false;
		String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");

		String query = " select top 1 * from Posting.RNEPostingExtract "
				+ "where HeaderSequence in ('" + PostingExtractFile.responseHeaderSetTagWiseData.get("Sequence")+"')"
				+ " and HeaderVersion in ('" + PostingExtractFile.responseHeaderSetTagWiseData.get("Version")+"')"
				+ " order by 1 desc";
		
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(server, database, query);

		int count = 0;
		while(rs.next())
		{
			count++;
		}
		System.out.println("count=" + count);

		if(count == 0)
		{
			System.out.println("No records found");
		}
		else{
			rs.beforeFirst();

			while(rs.next())
			{
				if(rs.getString(19).equalsIgnoreCase("LDC") || rs.getString(19).equalsIgnoreCase("SID") ){
					flag= true;
					
				}
				else{
					flag=false;
					
				}
			}
		}
		return flag;
	}


	public static boolean validatePostingExtractTableRecordCount() throws Exception
	{
		boolean flag = false;
		String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");

		String query = " select top 1 * from Posting.RNEPostingExtract "
				+ "where HeaderSequence in ('" + PostingExtractFile.responseHeaderSetTagWiseData.get("Sequence")+"')"
				+ " and HeaderVersion in ('" + PostingExtractFile.responseHeaderSetTagWiseData.get("Version")+"')"
				+ " and RecordPostType in ('" + GenericMethodUtilis.getrNeResourceFile().getString("PostingType1")+"')"
				+ " order by 1 desc";

		String query1 = " select top 1 * from Posting.RNEPostingExtract "
				+ "where HeaderSequence in ('" + PostingExtractFile.responseHeaderSetTagWiseData.get("Sequence")+"')"
				+ " and HeaderVersion in ('" + PostingExtractFile.responseHeaderSetTagWiseData.get("Version")+"')"
				+ " and RecordPostType in ('" + GenericMethodUtilis.getrNeResourceFile().getString("PostingType1")+"')"
				+ " order by 1 desc";

		System.out.println(query + " -------------------------------query");
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(server, database, query);
		ResultSet rs1 = ICSProductDBConnection.getICSDBServerConnection(server, database, query1);

		int count = 0;
		int count1 = 0;
		while(rs.next() && rs1.next())
		{
			count++;
			count1++;
		}		

		if(count == 0 && count1==0)
		{
			
		}

		else{
			rs.beforeFirst();
			rs1.beforeFirst();

			while(rs.next())
			{
				if(rs.getString(19).equalsIgnoreCase("SID") ){
					flag= true;
					
					publishResults(flag, rs.getString(19), "SID", "SID PostType Validation");
				}
				else{
					flag=false;				
					publishResults(flag, rs.getString(19), "SID", "SID PostType Validation");
				}
			}

			while(rs1.next())
			{
				if(rs1.getString(19).equalsIgnoreCase("LDC") ){
					flag= true;
					
					publishResults(flag, rs1.getString(19), "LDC", "LDC PostType Validation");
				}
				else{
					flag=false;				
					publishResults(flag, rs1.getString(19), "LDC", "LDC PostType Validation");
				}
			}
		}
		return flag;
	}

	/*public static ArrayList<Integer> getRowCountFromBaseCoreTable() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{

		String server = GenericMethodUtilis.getrNeResourceFile().getString("dbArchiveServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeArchiveDatabaseName");
		String query = " select * from Base.Core";


		ResultSet rs =  ICSProductDBConnection.getICSDBServerConnection(server, database, query);

		int initialSize;
		try{

			rs.last();
			initialSize=rs.getRow();
			rs.beforeFirst();

			rc.add(initialSize);
			System.out.println("Method1 initial size"+rc.get(0));
		}

		catch(Exception e)
		{
			System.out.println("Error in getting row cout");
		}

		return rc;		
	}

	public static boolean compareRowCount() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		boolean flag=false;
		int initialSize = rc.get(0);
		int finalSize=getRowCountFromBaseCoreTable().get(0);	

		try{

			int expectedSize=initialSize +1;

			if(finalSize>initialSize){

				flag=true;
				System.out.println("Data Loaded SuccessFully");

				publishResults(flag,Integer.toString(finalSize),Integer.toString(expectedSize), "Row Count Comparison");
			}
			else{
				System.out.println("Incorrect Data");
				publishResults(flag,Integer.toString(finalSize),Integer.toString(expectedSize), "Row Count Comparison");
			}

		}

		catch(Exception e)
		{
			System.out.println("Error in getting row cout");

		}

		return flag;		
	}*/

	

	public static int getRowCountFromBaseCoreTable1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		int size = 0;
		String server = GenericMethodUtilis.getrNeResourceFile().getString("dbArchiveServerName");
		String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeArchiveDatabaseName");
		String query = " select count(*) from Base.Core";


		ResultSet rs =  ICSProductDBConnection.getICSDBServerConnection(server, database, query);

		try{

			while(rs.next())
			{
				size = rs.getInt(1);
			}

			
		}

		catch(Exception e)
		{
			
		}

		return size;		
	}

	public static boolean compareRowCount1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		boolean flag=false;
		finalSize=getRowCountFromBaseCoreTable1();	

		try{

			int expectedSize=initialSize +1;

			if(finalSize>initialSize){

				flag=true;
				

				publishResults(flag,Integer.toString(finalSize),Integer.toString(expectedSize), "Row Count Comparison");
			}
			else{
				
				publishResults(flag,Integer.toString(finalSize),Integer.toString(expectedSize), "Row Count Comparison");
			}

		}

		catch(Exception e)
		{
			

		}

		return flag;		
	}
	
	public static ArrayList<String> getRecordsFromExtractXML() throws SAXException, IOException, ParserConfigurationException, Exception
	{
		ArrayList<String> ar=new ArrayList<String>();
		
		String sequence1=null;

		String sequence2=null;
		String postType1=null;
		String subType1=null;
		String sourceMsg1=null;
		String channel1=null;
		String entryType1=null;
		String postingSource1=null;
		String respIDSource1=null;
		String postDay1=null;
		String clearDate1=null;
		String settDate1=null;
		String captDate1=null;
		String amount1=null;
		String tranSetID1=null;
		String reason1=null;
		String override1=null;
		String numCheques1=null;
		String collLocn1=null;
		String collPart1=null;

		String transIDDR1=null;
		String sortCodeDR1=null;
		String accNumDR1=null;
		String serNumDR1=null;
		String tranCodeDR1=null;
		String switchSortDR1=null;
		String switchAccDR1=null;
		String fullAmountDR1=null;

		String transIDCR1=null;
		String sortCodeCR1=null;
		String accNumCR1=null;
		String refCR1=null;
		String tranCodeCR1=null;
		String origAmtCR1=null;
		String exceptionCR1=null;
		String beneficiaryCR1=null;

		String sequence3=null;
		String postType2=null;
		String subType2=null;
		String sourceMsg2=null;
		String channel2=null;
		String entryType2=null;
		String postingSource2=null;
		String respIDSource2=null;
		String postDay2=null;
		String clearDate2=null;
		String settDate2=null;
		String captDate2=null;
		String amount2=null;
		String tranSetID2=null;
		String reason2=null;
		String override2=null;
		String numCheques2=null;
		String collLocn2=null;
		String collPart2=null;

		String transIDDR2=null;
		String sortCodeDR2=null;
		String accNumDR2=null;
		String serNumDR2=null;
		String tranCodeDR2=null;
		String switchSortDR2=null;
		String switchAccDR2=null;
		String fullAmountDR2=null;

		String transIDCR2=null;
		String sortCodeCR2=null;
		String accNumCR2=null;
		String refCR2=null;
		String tranCodeCR2=null;
		String origAmtCR2=null;
		String exceptionCR2=null;
		String beneficiaryCR2=null;
		
		String sequence4=null;
		String postType3=null;
		String subType3=null;
		String sourceMsg3=null;
		String channel3=null;
		String entryType3=null;
		String postingSource3=null;
		String respIDSource3=null;
		String postDay3=null;
		String clearDate3=null;
		String settDate3=null;
		String captDate3=null;
		String amount3=null;
		String tranSetID3=null;
		String reason3=null;
		String override3=null;
		String numCheques3=null;
		String collLocn3=null;
		String collPart3=null;

		String transIDDR3=null;
		String sortCodeDR3=null;
		String accNumDR3=null;
		String serNumDR3=null;
		String tranCodeDR3=null;
		String switchSortDR3=null;
		String switchAccDR3=null;
		String fullAmountDR3=null;

		String transIDCR3=null;
		String sortCodeCR3=null;
		String accNumCR3=null;
		String refCR3=null;
		String tranCodeCR3=null;
		String origAmtCR3=null;
		String exceptionCR3=null;
		String beneficiaryCR3=null;

		ArrayList<String> recordSet1= new ArrayList<String>();		
		recordSet1= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Sequence"),"Sequence");
		sequence2=recordSet1.get(1);
		sequence3=recordSet1.get(2);
		sequence4=recordSet1.get(3);

		ArrayList<String> recordSet2= new ArrayList<String>();		
		recordSet2= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostType"),"PostType");
		postType1=recordSet2.get(0);
		postType2=recordSet2.get(1);
		postType3=recordSet2.get(2);

		ArrayList<String> recordSet3= new ArrayList<String>();		
		recordSet3= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SubType"),"SubType");
		subType1=recordSet3.get(0);
		subType2=recordSet3.get(1);
		subType3=recordSet3.get(2);

		ArrayList<String> recordSet4= new ArrayList<String>();		
		recordSet4= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SourceMsg"),"SourceMsg");
		sourceMsg1=recordSet4.get(0);
		sourceMsg2=recordSet4.get(1);
		sourceMsg3=recordSet4.get(2);

		ArrayList<String> recordSet5= new ArrayList<String>();		
		recordSet5= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Channel"),"Channel");
		channel1=recordSet5.get(0);
		channel2=recordSet5.get(1);
		channel3=recordSet5.get(2);

		ArrayList<String> recordSet6= new ArrayList<String>();		
		recordSet6= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//EntryType"),"EntryType");
		entryType1=recordSet6.get(0);
		entryType2=recordSet6.get(1);
		entryType3=recordSet6.get(2);

		ArrayList<String> recordSet7= new ArrayList<String>();		
		recordSet7= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostingSource"),"PostingSource");
		postingSource1=recordSet7.get(0);
		postingSource2=recordSet7.get(1);
		postingSource3=recordSet7.get(2);

		ArrayList<String> recordSet8= new ArrayList<String>();		
		recordSet8= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//RespIDSource"),"RespIDSource");
		respIDSource1=recordSet8.get(0);
		respIDSource2=recordSet8.get(1);
		respIDSource3=recordSet8.get(2);

		ArrayList<String> recordSet9= new ArrayList<String>();		
		recordSet9= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//ClearDate"),"ClearDate");
		clearDate1=recordSet9.get(0);
		clearDate2=recordSet9.get(1);
		clearDate3=recordSet9.get(2);

		ArrayList<String> recordSet10= new ArrayList<String>();		
		recordSet10= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SettDate"),"SettDate");
		settDate1=recordSet10.get(0);
		settDate2=recordSet10.get(1);
		settDate3=recordSet10.get(2);

		ArrayList<String> recordSet11= new ArrayList<String>();		
		recordSet11= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CaptDate"),"CaptDate");
		captDate1=recordSet11.get(0);
		captDate2=recordSet11.get(1);
		captDate3=recordSet11.get(2);

		ArrayList<String> recordSet12= new ArrayList<String>();		
		recordSet12= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Amount"),"Amount");
		amount1=recordSet12.get(0);
		amount2=recordSet12.get(1);
		amount3=recordSet12.get(2);

		ArrayList<String> recordSet13= new ArrayList<String>();		
		recordSet13= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TranSetID"),"TranSetID");
		tranSetID1=recordSet13.get(0);
		tranSetID2=recordSet13.get(1);
		tranSetID3=recordSet13.get(2);
		
		ArrayList<String> recordSet28= new ArrayList<String>();		
		recordSet28= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Reason"),"Reason");
		reason1=recordSet28.get(0);
		reason2=recordSet28.get(1);
		reason3=recordSet28.get(2);		
		
		ArrayList<String> recordSet14= new ArrayList<String>();		
		recordSet14= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostDay"),"PostDay");
		postDay1=recordSet14.get(0);
		postDay2=recordSet14.get(1);
		postDay3=recordSet14.get(2);

		ArrayList<String> recordSet15= new ArrayList<String>();		
		recordSet15= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Override"),"Override");
		override1=recordSet15.get(0);
		override2=recordSet15.get(1);
		override3=recordSet15.get(2);

		ArrayList<String> recordSet16= new ArrayList<String>();		
		recordSet16= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CollPart"),"CollPart");
		collPart1=recordSet16.get(0);
		collPart2=recordSet16.get(1);
		collPart3=recordSet16.get(2);

		ArrayList<String> recordSet17= new ArrayList<String>();		
		recordSet17= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CollLocn"),"CollLocn");
		collLocn1=recordSet17.get(0);
		collLocn2=recordSet17.get(1);
		collLocn3=recordSet17.get(2);
		
		ArrayList<String> recordSet29= new ArrayList<String>();		
		recordSet29= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//NumCheques"),"NumCheques");
		numCheques1=recordSet29.get(0);
		numCheques2=recordSet29.get(1);
		numCheques3=recordSet29.get(2);

		ArrayList<String> recordSet18= new ArrayList<String>();		
		recordSet18= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TransID"),"TransID");
		transIDDR1=recordSet18.get(0);
		transIDCR1=recordSet18.get(1);
		transIDDR2=recordSet18.get(2);
		transIDCR2=recordSet18.get(3);
		transIDDR3=recordSet18.get(4);
		transIDCR3=recordSet18.get(5);

		ArrayList<String> recordSet19= new ArrayList<String>();		
		recordSet19= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SortCode"),"SortCode");
		sortCodeDR1=recordSet19.get(0);
		sortCodeCR1=recordSet19.get(1);
		sortCodeDR2=recordSet19.get(2);
		sortCodeCR2=recordSet19.get(3);
		sortCodeDR3=recordSet19.get(4);
		sortCodeCR3=recordSet19.get(5);

		ArrayList<String> recordSet20= new ArrayList<String>();		
		recordSet20= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//AccNum"),"AccNum");
		accNumDR1=recordSet20.get(0);
		accNumCR1=recordSet20.get(1);
		accNumDR2=recordSet20.get(2);
		accNumCR2=recordSet20.get(3);
		accNumDR3=recordSet20.get(4);
		accNumCR3=recordSet20.get(5);

		ArrayList<String> recordSet21= new ArrayList<String>();		
		recordSet21= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SerNum"),"SerNum");
		serNumDR1=recordSet21.get(0);
		serNumDR2=recordSet21.get(1);
		serNumDR3=recordSet21.get(2);

		ArrayList<String> recordSet22= new ArrayList<String>();		
		recordSet22= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TranCode"),"TranCode");
		tranCodeDR1=recordSet22.get(0);
		tranCodeCR1=recordSet22.get(1);
		tranCodeDR2=recordSet22.get(2);
		tranCodeCR2=recordSet22.get(3);
		tranCodeDR3=recordSet22.get(4);
		tranCodeCR3=recordSet22.get(5);

		ArrayList<String> recordSet23= new ArrayList<String>();		
		recordSet23= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SwitchSort"),"SwitchSort");
		switchSortDR1=recordSet23.get(0);
		switchSortDR2=recordSet23.get(1);
		switchSortDR3=recordSet23.get(2);

		ArrayList<String> recordSet24= new ArrayList<String>();		
		recordSet24= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SwitchAcc"),"SwitchAcc");
		switchAccDR1=recordSet24.get(0);
		switchAccDR2=recordSet24.get(1);
		switchAccDR3=recordSet24.get(2);
		
		ArrayList<String> recordSet30= new ArrayList<String>();		
		recordSet30= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//FullAmt"),"FullAmt");
		fullAmountDR1=recordSet30.get(0);
		fullAmountDR2=recordSet30.get(1);
		fullAmountDR3=recordSet30.get(2);

		ArrayList<String> recordSet25= new ArrayList<String>();		
		recordSet25= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Ref"),"Ref");
		refCR1=recordSet25.get(0);
		refCR2=recordSet25.get(1);
		refCR3=recordSet25.get(2);
		
		ArrayList<String> recordSet32= new ArrayList<String>();		
		recordSet32= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//OrigAmt"),"OrigAmt");
		origAmtCR1=recordSet32.get(0);
		origAmtCR2=recordSet32.get(1);
		origAmtCR3=recordSet32.get(2);
		
		ArrayList<String> recordSet33= new ArrayList<String>();		
		recordSet33= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Exception"),"Exception");
		exceptionCR1=recordSet33.get(0);
		exceptionCR2=recordSet33.get(1);
		exceptionCR3=recordSet33.get(2);

		ArrayList<String> recordSet27= new ArrayList<String>();		
		recordSet27= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Beneficiary"),"Beneficiary");
		beneficiaryCR1=recordSet27.get(0);
		beneficiaryCR1=recordSet27.get(1);	
		
			
//Record Sets Addition to ArrayList
		ar.add(sequence2);//0
		ar.add(sequence3);//1
		ar.add(sequence4);//2
		ar.add(postType1);//3
		ar.add(postType2);//4
		ar.add(subType1);//5
		ar.add(subType2);//6
		ar.add(subType3);//7
		ar.add(sourceMsg1);//8
		ar.add(sourceMsg2);//9
		ar.add(sourceMsg3);//10
		ar.add(channel1);//11
		ar.add(channel2);//12
		ar.add(channel3);//13
		ar.add(entryType1);//14
		ar.add(entryType2);//15
		ar.add(entryType3);//16
		ar.add(postingSource1);//17
		ar.add(postingSource2);//18
		ar.add(postingSource3);//19
		ar.add(respIDSource1);//20
		ar.add(respIDSource2);//21
		ar.add(respIDSource3);//22
		ar.add(postDay1);//23
		ar.add(postDay2);//24
		ar.add(postDay3);//25
		ar.add(clearDate1);//26
		ar.add(clearDate2);//27
		ar.add(clearDate3);//28
		ar.add(settDate1);//29
		ar.add(settDate2);//30
		ar.add(settDate3);//31
		ar.add(captDate1);//32
		ar.add(captDate2);//33
		ar.add(captDate3);//34
		ar.add(amount1);//35
		ar.add(amount2);//36
		ar.add(amount3);//37
		ar.add(tranSetID1);//38
		ar.add(tranSetID2);//39
		ar.add(tranSetID3);//40
		ar.add(reason1);//41
		ar.add(reason2);//42
		ar.add(reason3);//43
		ar.add(override1);//44
		ar.add(override2);//45
		ar.add(override3);//46
		ar.add(numCheques1);//47
		ar.add(numCheques2);//48
		ar.add(numCheques3);//49
		ar.add(collPart1);//50
		ar.add(collPart2);//51
		ar.add(collPart3);//52
		ar.add(collLocn1);//53
		ar.add(collLocn2);//54
		ar.add(collLocn3);//55
	
		//Debit Sets
		ar.add(transIDDR1);//56
		ar.add(transIDCR1);//57
		ar.add(transIDDR2);//58
		ar.add(transIDCR2);//59
		ar.add(transIDDR3);//60
		ar.add(transIDCR3);//61
		
		ar.add(sortCodeDR1);//62
		ar.add(sortCodeCR1);//63
		ar.add(sortCodeDR2);//64	
		ar.add(sortCodeCR2);//65
		ar.add(sortCodeDR3);//66	
		ar.add(sortCodeCR3);//67
		
		ar.add(accNumDR1);//68
		ar.add(accNumCR1);//69
		ar.add(accNumDR2);//70
		ar.add(accNumCR2);//71
		ar.add(accNumDR3);//72
		ar.add(accNumCR3);//73
		
		ar.add(serNumDR1);//74
		ar.add(serNumDR2);//75
		ar.add(serNumDR3);//76
		
		ar.add(tranCodeDR1);//77
		ar.add(tranCodeCR1);//78
		ar.add(tranCodeDR2);//79
		ar.add(tranCodeCR2);//80
		ar.add(tranCodeDR3);//81
		ar.add(tranCodeCR3);//82
		
		ar.add(switchSortDR1);//83
		ar.add(switchSortDR2);//84
		ar.add(switchSortDR3);//85
		
		ar.add(switchAccDR1);//86
		ar.add(switchAccDR2);//87
		ar.add(switchAccDR3);//88
		
		ar.add(fullAmountDR1);//89
		ar.add(fullAmountDR2);//90
		ar.add(fullAmountDR3);//91
				
		ar.add(refCR1);//92
		ar.add(refCR2);//93
		ar.add(refCR3);//94
		
		ar.add(origAmtCR1);//95
		ar.add(origAmtCR2);//96
		ar.add(origAmtCR3);//97		
		ar.add(exceptionCR1);//98
		ar.add(exceptionCR2);//99
		ar.add(exceptionCR3);//100
				
		ar.add(beneficiaryCR1);//101
		ar.add(beneficiaryCR2);//102
		ar.add(beneficiaryCR3);//103
		
		ar.add(postType3);//104

		System.out.println(ar);

		return ar;

	}
	
	
	public static ArrayList<String> getRecordsFromExtractXML1DR1CR() throws SAXException, IOException, ParserConfigurationException, Exception
	{
		ArrayList<String> ar=new ArrayList<String>();
		
		String sequence1=null;

		String sequence2=null;
		String postType1=null;
		String subType1=null;
		String sourceMsg1=null;
		String channel1=null;
		String entryType1=null;
		String postingSource1=null;
		String respIDSource1=null;
		String postDay1=null;
		String clearDate1=null;
		String settDate1=null;
		String captDate1=null;
		String amount1=null;
		String tranSetID1=null;
		String reason1=null;
		String override1=null;
		String numCheques1=null;
		String collLocn1=null;
		String collPart1=null;

		String transIDDR1=null;
		String sortCodeDR1=null;
		String accNumDR1=null;
		String serNumDR1=null;
		String tranCodeDR1=null;
		String switchSortDR1=null;
		String switchAccDR1=null;
		String fullAmountDR1=null;

		String transIDCR1=null;
		String sortCodeCR1=null;
		String accNumCR1=null;
		String refCR1=null;
		String tranCodeCR1=null;
		String origAmtCR1=null;
		String exceptionCR1=null;
		String beneficiaryCR1=null;		

		ArrayList<String> recordSet1= new ArrayList<String>();		
		recordSet1= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Sequence"),"Sequence");
		sequence2=recordSet1.get(1);
		

		ArrayList<String> recordSet2= new ArrayList<String>();		
		recordSet2= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostType"),"PostType");
		postType1=recordSet2.get(0);
		

		ArrayList<String> recordSet3= new ArrayList<String>();		
		recordSet3= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SubType"),"SubType");
		subType1=recordSet3.get(0);
		

		ArrayList<String> recordSet4= new ArrayList<String>();		
		recordSet4= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SourceMsg"),"SourceMsg");
		sourceMsg1=recordSet4.get(0);
		

		ArrayList<String> recordSet5= new ArrayList<String>();		
		recordSet5= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Channel"),"Channel");
		channel1=recordSet5.get(0);
	

		ArrayList<String> recordSet6= new ArrayList<String>();		
		recordSet6= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//EntryType"),"EntryType");
		entryType1=recordSet6.get(0);
		

		ArrayList<String> recordSet7= new ArrayList<String>();		
		recordSet7= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostingSource"),"PostingSource");
		postingSource1=recordSet7.get(0);
	

		ArrayList<String> recordSet8= new ArrayList<String>();		
		recordSet8= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//RespIDSource"),"RespIDSource");
		respIDSource1=recordSet8.get(0);
		

		ArrayList<String> recordSet9= new ArrayList<String>();		
		recordSet9= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//ClearDate"),"ClearDate");
		clearDate1=recordSet9.get(0);
		

		ArrayList<String> recordSet10= new ArrayList<String>();		
		recordSet10= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SettDate"),"SettDate");
		settDate1=recordSet10.get(0);
		

		ArrayList<String> recordSet11= new ArrayList<String>();		
		recordSet11= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CaptDate"),"CaptDate");
		captDate1=recordSet11.get(0);
		

		ArrayList<String> recordSet12= new ArrayList<String>();		
		recordSet12= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Amount"),"Amount");
		amount1=recordSet12.get(0);
		

		ArrayList<String> recordSet13= new ArrayList<String>();		
		recordSet13= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TranSetID"),"TranSetID");
		tranSetID1=recordSet13.get(0);
		
		
		ArrayList<String> recordSet28= new ArrayList<String>();		
		recordSet28= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Reason"),"Reason");
		reason1=recordSet28.get(0);
		
		
		ArrayList<String> recordSet14= new ArrayList<String>();		
		recordSet14= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostDay"),"PostDay");
		postDay1=recordSet14.get(0);
		

		ArrayList<String> recordSet15= new ArrayList<String>();		
		recordSet15= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Override"),"Override");
		override1=recordSet15.get(0);
		

		ArrayList<String> recordSet16= new ArrayList<String>();		
		recordSet16= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CollPart"),"CollPart");
		collPart1=recordSet16.get(0);
		

		ArrayList<String> recordSet17= new ArrayList<String>();		
		recordSet17= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CollLocn"),"CollLocn");
		collLocn1=recordSet17.get(0);
		
		
		ArrayList<String> recordSet29= new ArrayList<String>();		
		recordSet29= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//NumCheques"),"NumCheques");
		numCheques1=recordSet29.get(0);
		

		ArrayList<String> recordSet18= new ArrayList<String>();		
		recordSet18= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TransID"),"TransID");
		transIDDR1=recordSet18.get(0);
		transIDCR1=recordSet18.get(1);
		

		ArrayList<String> recordSet19= new ArrayList<String>();		
		recordSet19= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SortCode"),"SortCode");
		sortCodeDR1=recordSet19.get(0);
		sortCodeCR1=recordSet19.get(1);
		

		ArrayList<String> recordSet20= new ArrayList<String>();		
		recordSet20= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//AccNum"),"AccNum");
		accNumDR1=recordSet20.get(0);
		accNumCR1=recordSet20.get(1);
		

		ArrayList<String> recordSet21= new ArrayList<String>();		
		recordSet21= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SerNum"),"SerNum");
		serNumDR1=recordSet21.get(0);
		
		ArrayList<String> recordSet22= new ArrayList<String>();		
		recordSet22= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TranCode"),"TranCode");
		tranCodeDR1=recordSet22.get(0);
		tranCodeCR1=recordSet22.get(1);
		

		ArrayList<String> recordSet23= new ArrayList<String>();		
		recordSet23= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SwitchSort"),"SwitchSort");
		switchSortDR1=recordSet23.get(0);
		

		ArrayList<String> recordSet24= new ArrayList<String>();		
		recordSet24= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SwitchAcc"),"SwitchAcc");
		switchAccDR1=recordSet24.get(0);
		
		
		ArrayList<String> recordSet30= new ArrayList<String>();		
		recordSet30= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//FullAmt"),"FullAmt");
		fullAmountDR1=recordSet30.get(0);
		

		ArrayList<String> recordSet25= new ArrayList<String>();		
		recordSet25= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Ref"),"Ref");
		refCR1=recordSet25.get(0);
		
		
		ArrayList<String> recordSet32= new ArrayList<String>();		
		recordSet32= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//OrigAmt"),"OrigAmt");
		origAmtCR1=recordSet32.get(0);
		
		
		ArrayList<String> recordSet33= new ArrayList<String>();		
		recordSet33= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Exception"),"Exception");
		exceptionCR1=recordSet33.get(0);
		

		ArrayList<String> recordSet27= new ArrayList<String>();		
		recordSet27= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Beneficiary"),"Beneficiary");
		beneficiaryCR1=recordSet27.get(0);
		
		
			
//Record Sets Addition to ArrayList
		ar.add(sequence2);//0
		
		ar.add(postType1);//1
		
		ar.add(subType1);//2
		
		ar.add(sourceMsg1);//3
		
		ar.add(channel1);//4
		
		ar.add(entryType1);//5
		
		ar.add(postingSource1);//6
		
		ar.add(respIDSource1);//7
		
		ar.add(postDay1);//8
		
		ar.add(clearDate1);//9
		
		ar.add(settDate1);//10
		
		ar.add(captDate1);//11
		
		ar.add(amount1);//12
		
		ar.add(tranSetID1);//13
		
		ar.add(reason1);//14
		
		ar.add(override1);//15
		
		ar.add(numCheques1);//16
		
		ar.add(collPart1);//17
		
		ar.add(collLocn1);//18
		
	
		//Debit Sets
		ar.add(transIDDR1);//19
		ar.add(transIDCR1);//20	
		
		ar.add(sortCodeDR1);//21
		ar.add(sortCodeCR1);//22	
		
		ar.add(accNumDR1);//23
		ar.add(accNumCR1);//24
		
		ar.add(serNumDR1);//25	
		
		ar.add(tranCodeDR1);//26
		ar.add(tranCodeCR1);//27		
		
		ar.add(switchSortDR1);//28		
		
		ar.add(switchAccDR1);//29		
		
		ar.add(fullAmountDR1);//30		
				
		ar.add(refCR1);//31		
		
		ar.add(origAmtCR1);//32		
				
		ar.add(beneficiaryCR1);//33	

		

		return ar;

	}
	
	
	public static ArrayList<String> getRecordsFromExtractXML2Records1DR1CR() throws SAXException, IOException, ParserConfigurationException, Exception
	{
		ArrayList<String> ar=new ArrayList<String>();
		
		String sequence1=null;

		String sequence2=null;
		String postType1=null;
		String subType1=null;
		String sourceMsg1=null;
		String channel1=null;
		String entryType1=null;
		String postingSource1=null;
		String respIDSource1=null;
		String postDay1=null;
		String clearDate1=null;
		String settDate1=null;
		String captDate1=null;
		String amount1=null;
		String tranSetID1=null;
		String reason1=null;
		String override1=null;
		String numCheques1=null;
		String collLocn1=null;
		String collPart1=null;

		String transIDDR1=null;
		String sortCodeDR1=null;
		String accNumDR1=null;
		String serNumDR1=null;
		String tranCodeDR1=null;
		String switchSortDR1=null;
		String switchAccDR1=null;
		String fullAmountDR1=null;

		String transIDCR1=null;
		String sortCodeCR1=null;
		String accNumCR1=null;
		String refCR1=null;
		String tranCodeCR1=null;
		String origAmtCR1=null;
		String exceptionCR1=null;
		String beneficiaryCR1=null;

		String sequence3=null;
		String postType2=null;
		String subType2=null;
		String sourceMsg2=null;
		String channel2=null;
		String entryType2=null;
		String postingSource2=null;
		String respIDSource2=null;
		String postDay2=null;
		String clearDate2=null;
		String settDate2=null;
		String captDate2=null;
		String amount2=null;
		String tranSetID2=null;
		String reason2=null;
		String override2=null;
		String numCheques2=null;
		String collLocn2=null;
		String collPart2=null;

		String transIDDR2=null;
		String sortCodeDR2=null;
		String accNumDR2=null;
		String serNumDR2=null;
		String tranCodeDR2=null;
		String switchSortDR2=null;
		String switchAccDR2=null;
		String fullAmountDR2=null;

		String transIDCR2=null;
		String sortCodeCR2=null;
		String accNumCR2=null;
		String refCR2=null;
		String tranCodeCR2=null;
		String origAmtCR2=null;
		String exceptionCR2=null;
		String beneficiaryCR2=null;		
		
		ArrayList<String> recordSet1= new ArrayList<String>();		
		recordSet1= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Sequence"),"Sequence");
		sequence2=recordSet1.get(1);
		sequence3=recordSet1.get(2);		

		ArrayList<String> recordSet2= new ArrayList<String>();		
		recordSet2= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostType"),"PostType");
		postType1=recordSet2.get(0);
		postType2=recordSet2.get(1);		

		ArrayList<String> recordSet3= new ArrayList<String>();		
		recordSet3= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SubType"),"SubType");
		subType1=recordSet3.get(0);
		subType2=recordSet3.get(1);		

		ArrayList<String> recordSet4= new ArrayList<String>();		
		recordSet4= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SourceMsg"),"SourceMsg");
		sourceMsg1=recordSet4.get(0);
		sourceMsg2=recordSet4.get(1);		

		ArrayList<String> recordSet5= new ArrayList<String>();		
		recordSet5= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Channel"),"Channel");
		channel1=recordSet5.get(0);
		channel2=recordSet5.get(1);		

		ArrayList<String> recordSet6= new ArrayList<String>();		
		recordSet6= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//EntryType"),"EntryType");
		entryType1=recordSet6.get(0);
		entryType2=recordSet6.get(1);		

		ArrayList<String> recordSet7= new ArrayList<String>();		
		recordSet7= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostingSource"),"PostingSource");
		postingSource1=recordSet7.get(0);
		postingSource2=recordSet7.get(1);		

		ArrayList<String> recordSet8= new ArrayList<String>();		
		recordSet8= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//RespIDSource"),"RespIDSource");
		respIDSource1=recordSet8.get(0);
		respIDSource2=recordSet8.get(1);		

		ArrayList<String> recordSet9= new ArrayList<String>();		
		recordSet9= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//ClearDate"),"ClearDate");
		clearDate1=recordSet9.get(0);
		clearDate2=recordSet9.get(1);		

		ArrayList<String> recordSet10= new ArrayList<String>();		
		recordSet10= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SettDate"),"SettDate");
		settDate1=recordSet10.get(0);
		settDate2=recordSet10.get(1);		

		ArrayList<String> recordSet11= new ArrayList<String>();		
		recordSet11= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CaptDate"),"CaptDate");
		captDate1=recordSet11.get(0);
		captDate2=recordSet11.get(1);		

		ArrayList<String> recordSet12= new ArrayList<String>();		
		recordSet12= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Amount"),"Amount");
		amount1=recordSet12.get(0);
		amount2=recordSet12.get(1);		

		ArrayList<String> recordSet13= new ArrayList<String>();		
		recordSet13= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TranSetID"),"TranSetID");
		tranSetID1=recordSet13.get(0);
		tranSetID2=recordSet13.get(1);		
		
		ArrayList<String> recordSet28= new ArrayList<String>();		
		recordSet28= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Reason"),"Reason");
		reason1=recordSet28.get(0);
		reason2=recordSet28.get(1);			
		
		ArrayList<String> recordSet14= new ArrayList<String>();		
		recordSet14= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostDay"),"PostDay");
		postDay1=recordSet14.get(0);
		postDay2=recordSet14.get(1);		

		ArrayList<String> recordSet15= new ArrayList<String>();		
		recordSet15= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Override"),"Override");
		override1=recordSet15.get(0);
		override2=recordSet15.get(1);		

		ArrayList<String> recordSet16= new ArrayList<String>();		
		recordSet16= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CollPart"),"CollPart");
		collPart1=recordSet16.get(0);
		collPart2=recordSet16.get(1);		

		ArrayList<String> recordSet17= new ArrayList<String>();		
		recordSet17= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CollLocn"),"CollLocn");
		collLocn1=recordSet17.get(0);
		collLocn2=recordSet17.get(1);		
		
//		ArrayList<String> recordSet29= new ArrayList<String>();		
//		recordSet29= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//NumCheques"),"NumCheques");
//		numCheques1=recordSet29.get(0);
//		numCheques2=recordSet29.get(1);		

		ArrayList<String> recordSet18= new ArrayList<String>();		
		recordSet18= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TransID"),"TransID");
		transIDDR1=recordSet18.get(0);
		transIDCR1=recordSet18.get(1);
		transIDDR2=recordSet18.get(2);
		transIDCR2=recordSet18.get(3);		

		ArrayList<String> recordSet19= new ArrayList<String>();		
		recordSet19= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SortCode"),"SortCode");
		sortCodeDR1=recordSet19.get(0);
		sortCodeCR1=recordSet19.get(1);
		sortCodeDR2=recordSet19.get(2);
		sortCodeCR2=recordSet19.get(3);		

		ArrayList<String> recordSet20= new ArrayList<String>();		
		recordSet20= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//AccNum"),"AccNum");
		accNumDR1=recordSet20.get(0);
		accNumCR1=recordSet20.get(1);
		accNumDR2=recordSet20.get(2);
		accNumCR2=recordSet20.get(3);		

		ArrayList<String> recordSet21= new ArrayList<String>();		
		recordSet21= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SerNum"),"SerNum");
		serNumDR1=recordSet21.get(0);
		serNumDR2=recordSet21.get(1);		

		ArrayList<String> recordSet22= new ArrayList<String>();		
		recordSet22= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TranCode"),"TranCode");
		tranCodeDR1=recordSet22.get(0);
		tranCodeCR1=recordSet22.get(1);
		tranCodeDR2=recordSet22.get(2);
		tranCodeCR2=recordSet22.get(3);		

		ArrayList<String> recordSet23= new ArrayList<String>();		
		recordSet23= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SwitchSort"),"SwitchSort");
		switchSortDR1=recordSet23.get(0);
		switchSortDR2=recordSet23.get(1);		

		ArrayList<String> recordSet24= new ArrayList<String>();		
		recordSet24= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SwitchAcc"),"SwitchAcc");
		switchAccDR1=recordSet24.get(0);
		switchAccDR2=recordSet24.get(1);		
		
		ArrayList<String> recordSet30= new ArrayList<String>();		
		recordSet30= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//FullAmt"),"FullAmt");
		fullAmountDR1=recordSet30.get(0);
		fullAmountDR2=recordSet30.get(1);		

		ArrayList<String> recordSet25= new ArrayList<String>();		
		recordSet25= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Ref"),"Ref");
		refCR1=recordSet25.get(0);
		refCR2=recordSet25.get(1);		
		
		ArrayList<String> recordSet32= new ArrayList<String>();		
		recordSet32= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//OrigAmt"),"OrigAmt");
		origAmtCR1=recordSet32.get(0);
		origAmtCR2=recordSet32.get(1);	
		
//		ArrayList<String> recordSet33= new ArrayList<String>();		
//		recordSet33= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Exception"),"Exception");
//		exceptionCR1=recordSet33.get(0);
//		exceptionCR2=recordSet33.get(1);		

		ArrayList<String> recordSet27= new ArrayList<String>();		
		recordSet27= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Beneficiary"),"Beneficiary");
		beneficiaryCR1=recordSet27.get(0);
		beneficiaryCR1=recordSet27.get(1);	
		
			
//Record Sets Addition to ArrayList
		ar.add(sequence2);//0
		ar.add(sequence3);//1
		
		ar.add(postType1);//2
		ar.add(postType2);//3
		
		ar.add(subType1);//4
		ar.add(subType2);//5
		
		ar.add(sourceMsg1);//6
		ar.add(sourceMsg2);//7
		
		ar.add(channel1);//8
		ar.add(channel2);//9
	
		ar.add(entryType1);//10
		ar.add(entryType2);//11
		
		ar.add(postingSource1);//12
		ar.add(postingSource2);//13

		ar.add(respIDSource1);//14
		ar.add(respIDSource2);//15
		
		ar.add(postDay1);//16
		ar.add(postDay2);//17
		
		ar.add(clearDate1);//18
		ar.add(clearDate2);//19
		
		ar.add(settDate1);//20
		ar.add(settDate2);//21
		
		ar.add(captDate1);//22
		ar.add(captDate2);//23
		
		ar.add(amount1);//24
		ar.add(amount2);//25
		
		ar.add(tranSetID1);//26
		ar.add(tranSetID2);//27
	
		ar.add(reason1);//28
		ar.add(reason2);//29
		
		ar.add(override1);//30
		ar.add(override2);//31
		
		ar.add(override1);//32
		ar.add(override2);//33
		
		ar.add(collPart1);//34
		ar.add(collPart2);//35
		
		ar.add(collLocn1);//36
		ar.add(collLocn2);//37		
	
		//Debit Sets
		ar.add(transIDDR1);//38
		ar.add(transIDCR1);//39
		ar.add(transIDDR2);//40
		ar.add(transIDCR2);//41		
		
		ar.add(sortCodeDR1);//42
		ar.add(sortCodeCR1);//43
		ar.add(sortCodeDR2);//44	
		ar.add(sortCodeCR2);//45		
		
		ar.add(accNumDR1);//46
		ar.add(accNumCR1);//47
		ar.add(accNumDR2);//48
		ar.add(accNumCR2);//49		
		
		ar.add(serNumDR1);//50
		ar.add(serNumDR2);//51	
		
		ar.add(tranCodeDR1);//52
		ar.add(tranCodeCR1);//53
		ar.add(tranCodeDR2);//54
		ar.add(tranCodeCR2);//55		
		
		ar.add(switchSortDR1);//56
		ar.add(switchSortDR2);//57		
		
		ar.add(switchAccDR1);//58
		ar.add(switchAccDR2);//59		
		
		ar.add(fullAmountDR1);//60
		ar.add(fullAmountDR2);//61		
				
		ar.add(refCR1);//62
		ar.add(refCR2);//63		
		
		ar.add(origAmtCR1);//64
		ar.add(origAmtCR2);//65
		
		ar.add(beneficiaryCR1);//66
		ar.add(beneficiaryCR2);//67	

		return ar;

	}
	
	
	public static ArrayList<String> getRecordsFromExtractXML4Records1DRMultiCR() throws SAXException, IOException, ParserConfigurationException, Exception
	{
		ArrayList<String> ar=new ArrayList<String>();
		
		String sequence1=null;

		String sequence2=null;
		String postType1=null;
		String subType1=null;
		String sourceMsg1=null;
		String channel1=null;
		String entryType1=null;
		String postingSource1=null;
		String respIDSource1=null;
		String postDay1=null;
		String clearDate1=null;
		String settDate1=null;
		String captDate1=null;
		String amount1=null;
		String tranSetID1=null;
		String reason1=null;
		String override1=null;
		String numCheques1=null;
		String collLocn1=null;
		String collPart1=null;

		String transIDDR1=null;
		String sortCodeDR1=null;
		String accNumDR1=null;
		String serNumDR1=null;
		String tranCodeDR1=null;
		String switchSortDR1=null;
		String switchAccDR1=null;
		String fullAmountDR1=null;

		String transIDCR1=null;
		String sortCodeCR1=null;
		String accNumCR1=null;
		String refCR1=null;
		String tranCodeCR1=null;
		String origAmtCR1=null;
		String exceptionCR1=null;
		String beneficiaryCR1=null;

		String sequence3=null;
		String postType2=null;
		String subType2=null;
		String sourceMsg2=null;
		String channel2=null;
		String entryType2=null;
		String postingSource2=null;
		String respIDSource2=null;
		String postDay2=null;
		String clearDate2=null;
		String settDate2=null;
		String captDate2=null;
		String amount2=null;
		String tranSetID2=null;
		String reason2=null;
		String override2=null;
		String numCheques2=null;
		String collLocn2=null;
		String collPart2=null;

		String transIDDR2=null;
		String sortCodeDR2=null;
		String accNumDR2=null;
		String serNumDR2=null;
		String tranCodeDR2=null;
		String switchSortDR2=null;
		String switchAccDR2=null;
		String fullAmountDR2=null;

		String transIDCR2=null;
		String sortCodeCR2=null;
		String accNumCR2=null;
		String refCR2=null;
		String tranCodeCR2=null;
		String origAmtCR2=null;
		String exceptionCR2=null;
		String beneficiaryCR2=null;		
		
		String sequence4=null;
		String postType3=null;
		String subType3=null;
		String sourceMsg3=null;
		String channel3=null;
		String entryType3=null;
		String postingSource3=null;
		String respIDSource3=null;
		String postDay3=null;
		String clearDate3=null;
		String settDate3=null;
		String captDate3=null;
		String amount3=null;
		String tranSetID3=null;
		String reason3=null;
		String override3=null;
		String numCheques3=null;
		String collLocn3=null;
		String collPart3=null;

		String transIDDR3=null;
		String sortCodeDR3=null;
		String accNumDR3=null;
		String serNumDR3=null;
		String tranCodeDR3=null;
		String switchSortDR3=null;
		String switchAccDR3=null;
		String fullAmountDR3=null;

		String transIDCR3=null;
		String sortCodeCR3=null;
		String accNumCR3=null;
		String refCR3=null;
		String tranCodeCR3=null;
		String origAmtCR3=null;
		String exceptionCR3=null;
		String beneficiaryCR3=null;		
		
		String sequence5=null;
		String postType4=null;
		String subType4=null;
		String sourceMsg4=null;
		String channel4=null;
		String entryType4=null;
		String postingSource4=null;
		String respIDSource4=null;
		String postDay4=null;
		String clearDate4=null;
		String settDate4=null;
		String captDate4=null;
		String amount4=null;
		String tranSetID4=null;
		String reason4=null;
		String override4=null;
		String numCheques4=null;
		String collLocn4=null;
		String collPart4=null;

		String transIDDR4=null;
		String sortCodeDR4=null;
		String accNumDR4=null;
		String serNumDR4=null;
		String tranCodeDR4=null;
		String switchSortDR4=null;
		String switchAccDR4=null;
		String fullAmountDR4=null;

		String transIDCR4=null;
		String sortCodeCR4=null;
		String accNumCR4=null;
		String refCR4=null;
		String tranCodeCR4=null;
		String origAmtCR4=null;
		String exceptionCR4=null;
		String beneficiaryCR4=null;		
		
		ArrayList<String> recordSet1= new ArrayList<String>();		
		recordSet1= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Sequence"),"Sequence");
		sequence2=recordSet1.get(1);
		sequence3=recordSet1.get(2);
		sequence4=recordSet1.get(3);
		sequence5=recordSet1.get(4);

		ArrayList<String> recordSet2= new ArrayList<String>();		
		recordSet2= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostType"),"PostType");
		postType1=recordSet2.get(0);
		postType2=recordSet2.get(1);
		postType3=recordSet2.get(2);
		postType4=recordSet2.get(3);

		ArrayList<String> recordSet3= new ArrayList<String>();		
		recordSet3= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SubType"),"SubType");
		subType1=recordSet3.get(0);
		subType2=recordSet3.get(1);	
		subType3=recordSet3.get(2);
		subType4=recordSet3.get(3);

		ArrayList<String> recordSet4= new ArrayList<String>();		
		recordSet4= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SourceMsg"),"SourceMsg");
		sourceMsg1=recordSet4.get(0);
		sourceMsg2=recordSet4.get(1);
		sourceMsg3=recordSet4.get(2);
		sourceMsg4=recordSet4.get(3);

		ArrayList<String> recordSet5= new ArrayList<String>();		
		recordSet5= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Channel"),"Channel");
		channel1=recordSet5.get(0);
		channel2=recordSet5.get(1);	
		channel3=recordSet5.get(2);
		channel4=recordSet5.get(3);

		ArrayList<String> recordSet6= new ArrayList<String>();		
		recordSet6= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//EntryType"),"EntryType");
		entryType1=recordSet6.get(0);
		entryType2=recordSet6.get(1);
		entryType3=recordSet6.get(2);
		entryType4=recordSet6.get(3);

		ArrayList<String> recordSet7= new ArrayList<String>();		
		recordSet7= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostingSource"),"PostingSource");
		postingSource1=recordSet7.get(0);
		postingSource2=recordSet7.get(1);
		postingSource3=recordSet7.get(2);
		postingSource4=recordSet7.get(3);

		ArrayList<String> recordSet8= new ArrayList<String>();		
		recordSet8= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//RespIDSource"),"RespIDSource");
		respIDSource1=recordSet8.get(0);
		respIDSource2=recordSet8.get(1);
		respIDSource3=recordSet8.get(2);
		respIDSource4=recordSet8.get(3);

		ArrayList<String> recordSet9= new ArrayList<String>();		
		recordSet9= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//ClearDate"),"ClearDate");
		clearDate1=recordSet9.get(0);
		clearDate2=recordSet9.get(1);
		clearDate3=recordSet9.get(2);
		clearDate4=recordSet9.get(3);

		ArrayList<String> recordSet10= new ArrayList<String>();		
		recordSet10= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SettDate"),"SettDate");
		settDate1=recordSet10.get(0);
		settDate2=recordSet10.get(1);
		settDate3=recordSet10.get(2);
		settDate4=recordSet10.get(3);

		ArrayList<String> recordSet11= new ArrayList<String>();		
		recordSet11= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CaptDate"),"CaptDate");
		captDate1=recordSet11.get(0);
		captDate2=recordSet11.get(1);
		captDate3=recordSet11.get(2);
		captDate4=recordSet11.get(3);

		ArrayList<String> recordSet12= new ArrayList<String>();		
		recordSet12= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Amount"),"Amount");
		amount1=recordSet12.get(0);
		amount2=recordSet12.get(1);
		amount3=recordSet12.get(2);
		amount4=recordSet12.get(3);

		ArrayList<String> recordSet13= new ArrayList<String>();		
		recordSet13= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TranSetID"),"TranSetID");
		tranSetID1=recordSet13.get(0);
		tranSetID2=recordSet13.get(1);
		tranSetID3=recordSet13.get(2);
		tranSetID4=recordSet13.get(3);
		
		ArrayList<String> recordSet28= new ArrayList<String>();		
		recordSet28= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Reason"),"Reason");
		reason1=recordSet28.get(0);
		reason2=recordSet28.get(1);
		reason3=recordSet28.get(2);
		reason4=recordSet28.get(3);
		
		ArrayList<String> recordSet14= new ArrayList<String>();		
		recordSet14= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//PostDay"),"PostDay");
		postDay1=recordSet14.get(0);
		postDay2=recordSet14.get(1);
		postDay3=recordSet14.get(2);
		postDay4=recordSet14.get(3);

		ArrayList<String> recordSet15= new ArrayList<String>();		
		recordSet15= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Override"),"Override");
		override1=recordSet15.get(0);
		override2=recordSet15.get(1);
		override3=recordSet15.get(2);
		override4=recordSet15.get(3);

		ArrayList<String> recordSet16= new ArrayList<String>();		
		recordSet16= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CollPart"),"CollPart");
		collPart1=recordSet16.get(0);
		collPart2=recordSet16.get(1);
		collPart3=recordSet16.get(2);
		collPart4=recordSet16.get(3);

		ArrayList<String> recordSet17= new ArrayList<String>();		
		recordSet17= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//CollLocn"),"CollLocn");
		collLocn1=recordSet17.get(0);
		collLocn2=recordSet17.get(1);
		collLocn3=recordSet17.get(2);
		collLocn4=recordSet17.get(3);
		
//		ArrayList<String> recordSet29= new ArrayList<String>();		
//		recordSet29= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//NumCheques"),"NumCheques");
//		numCheques1=recordSet29.get(0);
//		numCheques2=recordSet29.get(1);		

		ArrayList<String> recordSet18= new ArrayList<String>();		
		recordSet18= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TransID"),"TransID");
		transIDDR1=recordSet18.get(0);
		transIDCR1=recordSet18.get(1);
		transIDDR2=recordSet18.get(2);
		transIDCR2=recordSet18.get(3);	
		transIDDR3=recordSet18.get(4);
		transIDCR3=recordSet18.get(5);	
		transIDDR4=recordSet18.get(6);
		transIDCR4=recordSet18.get(7);	

		ArrayList<String> recordSet19= new ArrayList<String>();		
		recordSet19= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SortCode"),"SortCode");
		sortCodeDR1=recordSet19.get(0);
		sortCodeCR1=recordSet19.get(1);
		sortCodeDR2=recordSet19.get(2);
		sortCodeCR2=recordSet19.get(3);
		sortCodeDR3=recordSet19.get(4);
		sortCodeCR3=recordSet19.get(5);
		sortCodeDR4=recordSet19.get(6);
		sortCodeCR4=recordSet19.get(7);

		ArrayList<String> recordSet20= new ArrayList<String>();		
		recordSet20= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//AccNum"),"AccNum");
		accNumDR1=recordSet20.get(0);
		accNumCR1=recordSet20.get(1);
		accNumDR2=recordSet20.get(2);
		accNumCR2=recordSet20.get(3);
		accNumDR3=recordSet20.get(4);
		accNumCR3=recordSet20.get(5);	
		accNumDR4=recordSet20.get(6);
		accNumCR4=recordSet20.get(7);	

		ArrayList<String> recordSet21= new ArrayList<String>();		
		recordSet21= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SerNum"),"SerNum");
		serNumDR1=recordSet21.get(0);
		serNumDR2=recordSet21.get(1);
		serNumDR3=recordSet21.get(2);	
		serNumDR4=recordSet21.get(3);	

		ArrayList<String> recordSet22= new ArrayList<String>();		
		recordSet22= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//TranCode"),"TranCode");
		tranCodeDR1=recordSet22.get(0);
		tranCodeCR1=recordSet22.get(1);
		tranCodeDR2=recordSet22.get(2);
		tranCodeCR2=recordSet22.get(3);	
		tranCodeDR3=recordSet22.get(4);
		tranCodeCR3=recordSet22.get(5);	
		tranCodeDR4=recordSet22.get(6);
		tranCodeCR4=recordSet22.get(7);	

		ArrayList<String> recordSet23= new ArrayList<String>();		
		recordSet23= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SwitchSort"),"SwitchSort");
		switchSortDR1=recordSet23.get(0);
		switchSortDR2=recordSet23.get(1);	
		switchSortDR3=recordSet23.get(2);
		switchSortDR4=recordSet23.get(3);

		ArrayList<String> recordSet24= new ArrayList<String>();		
		recordSet24= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//SwitchAcc"),"SwitchAcc");
		switchAccDR1=recordSet24.get(0);
		switchAccDR2=recordSet24.get(1);	
		switchAccDR3=recordSet24.get(2);
		switchAccDR4=recordSet24.get(3);
		
		ArrayList<String> recordSet30= new ArrayList<String>();		
		recordSet30= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//FullAmt"),"FullAmt");
		fullAmountDR1=recordSet30.get(0);
		fullAmountDR2=recordSet30.get(1);
		fullAmountDR3=recordSet30.get(2);
		fullAmountDR4=recordSet30.get(3);

		ArrayList<String> recordSet25= new ArrayList<String>();		
		recordSet25= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Ref"),"Ref");
		refCR1=recordSet25.get(0);
		refCR2=recordSet25.get(1);	
		refCR3=recordSet25.get(2);
		refCR4=recordSet25.get(3);
		
		ArrayList<String> recordSet32= new ArrayList<String>();		
		recordSet32= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//OrigAmt"),"OrigAmt");
		origAmtCR1=recordSet32.get(0);
		origAmtCR2=recordSet32.get(1);
		origAmtCR3=recordSet32.get(2);
		origAmtCR4=recordSet32.get(3);
		
//		ArrayList<String> recordSet33= new ArrayList<String>();		
//		recordSet33= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Exception"),"Exception");
//		exceptionCR1=recordSet33.get(0);
//		exceptionCR2=recordSet33.get(1);		

		ArrayList<String> recordSet27= new ArrayList<String>();		
		recordSet27= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName+".xml","//Beneficiary"),"Beneficiary");
		beneficiaryCR1=recordSet27.get(0);
		beneficiaryCR1=recordSet27.get(1);
		beneficiaryCR3=recordSet27.get(2);
		beneficiaryCR4=recordSet27.get(3);
		
			
//Record Sets Addition to ArrayList
		ar.add(sequence2);//0
		ar.add(sequence3);//1
		ar.add(sequence4);
		ar.add(sequence5);
		ar.add(postType1);//2
		ar.add(postType2);//3
		ar.add(postType3);
		ar.add(postType4);
		ar.add(subType1);//4
		ar.add(subType2);//5
		ar.add(subType3);
		ar.add(subType4);
		ar.add(sourceMsg1);//6
		ar.add(sourceMsg2);//7
		ar.add(sourceMsg3);
		ar.add(sourceMsg4);
		ar.add(channel1);//8
		ar.add(channel2);//9
		ar.add(channel3);
		ar.add(channel4);
		ar.add(entryType1);//10
		ar.add(entryType2);//11
		ar.add(entryType3);
		ar.add(entryType4);
		ar.add(postingSource1);//12
		ar.add(postingSource2);//13
		ar.add(postingSource3);
		ar.add(postingSource4);
		ar.add(respIDSource1);//14
		ar.add(respIDSource2);//15
		ar.add(respIDSource3);
		ar.add(respIDSource4);
		ar.add(postDay1);//16
		ar.add(postDay2);//17
		ar.add(postDay3);
		ar.add(postDay4);
		ar.add(clearDate1);//18
		ar.add(clearDate2);//19
		ar.add(clearDate3);
		ar.add(clearDate4);
		ar.add(settDate1);//20
		ar.add(settDate2);//21
		ar.add(settDate3);
		ar.add(settDate4);
		ar.add(captDate1);//22
		ar.add(captDate2);//23
		ar.add(captDate3);
		ar.add(captDate4);
		ar.add(amount1);//24
		ar.add(amount2);//25
		ar.add(amount3);
		ar.add(amount4);
		ar.add(tranSetID1);//26
		ar.add(tranSetID2);//27
		ar.add(tranSetID3);
		ar.add(tranSetID4);
		ar.add(reason1);//28
		ar.add(reason2);//29
		ar.add(reason3);
		ar.add(reason4);
		ar.add(override1);//30
		ar.add(override2);//31
		ar.add(override3);
		ar.add(override4);
		ar.add(override1);//32
		ar.add(override2);//33
		ar.add(override3);
		ar.add(override4);
		ar.add(collPart1);//34
		ar.add(collPart2);//35
		ar.add(collPart3);
		ar.add(collPart4);
		ar.add(collLocn1);//36
		ar.add(collLocn2);//37		
		ar.add(collLocn3);
		ar.add(collLocn4);
		//Debit Sets
		ar.add(transIDDR1);//38
		ar.add(transIDCR1);//39
		ar.add(transIDDR2);//40
		ar.add(transIDCR2);//41		
		ar.add(transIDDR3);
		ar.add(transIDCR3);
		ar.add(transIDDR4);
		ar.add(transIDCR4);
		ar.add(sortCodeDR1);//42
		ar.add(sortCodeCR1);//43
		ar.add(sortCodeDR2);//44	
		ar.add(sortCodeCR2);//45		
		ar.add(sortCodeDR3);
		ar.add(sortCodeCR3);
		ar.add(sortCodeDR4);
		ar.add(sortCodeCR4);
		ar.add(accNumDR1);//46
		ar.add(accNumCR1);//47
		ar.add(accNumDR2);//48
		ar.add(accNumCR2);//49		
		ar.add(accNumDR3);
		ar.add(accNumCR3);
		ar.add(accNumDR4);
		ar.add(accNumCR4);
		ar.add(serNumDR1);//50
		ar.add(serNumDR2);//51	
		ar.add(serNumDR3);
		ar.add(serNumDR4);
		ar.add(tranCodeDR1);//52
		ar.add(tranCodeCR1);//53
		ar.add(tranCodeDR2);//54
		ar.add(tranCodeCR2);//55		
		ar.add(tranCodeDR3);
		ar.add(tranCodeCR3);
		ar.add(tranCodeDR4);
		ar.add(tranCodeCR4);
		ar.add(switchSortDR1);//56
		ar.add(switchSortDR2);//57		
		ar.add(switchSortDR3);
		ar.add(switchSortDR4);
		ar.add(switchAccDR1);//58
		ar.add(switchAccDR2);//59		
		ar.add(switchAccDR3);
		ar.add(switchAccDR4);
		ar.add(fullAmountDR1);//60
		ar.add(fullAmountDR2);//61		
		ar.add(fullAmountDR3);
		ar.add(fullAmountDR4);
		ar.add(refCR1);//62
		ar.add(refCR2);//63		
		ar.add(refCR3);
		ar.add(refCR4);
		ar.add(origAmtCR1);//64
		ar.add(origAmtCR2);//65
		ar.add(origAmtCR3);
		ar.add(origAmtCR4);
		ar.add(beneficiaryCR1);//66
		ar.add(beneficiaryCR2);//67
		ar.add(beneficiaryCR3);
		ar.add(beneficiaryCR4);
		

		return ar;

	}
	


}
