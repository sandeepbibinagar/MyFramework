package com.ics.rNe.xmlFilesDataFetch;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class CoreNodeRecordFile extends GenericMethodUtilis {
	
	public static String businessDateTagName = "BusinessDate";
	public static String extractIdTagName = "ExtractId";
	public static String intMessageTypeTagName = "IntMessageType";
	public static String processingParticipantIdTagName = "ProcessingParticipantId";
	public static String extMessageTypeTagName = "ExtMessageType";
	public static String messageSourceTagName = "MessageSource";
	public static String messageDestinationTagName = "MessageDestination";
	public static String recordCountsTagName = "RecordCounts";
	
	public static String entityTypeTagName = "EntityType";
	public static String entityStateTagName = "EntityState";
	public static String entityIdTagName = "EntityId";
	public static String sourceDateTimeTagName = "SourceDateTime";
	
	public static String postingExtractIdTagName = "PostingExtractId";
	public static String extractSequenceTagName = "ExtractSequence";
	public static String extractRevisionTagName = "ExtractRevision";
	public static String fileIdTagName = "FileId";
	public static String weekdayTagName = "Weekday";
	public static String fileTypeTagName = "FileType";
	public static String currencyTagName = "Currency";
	public static String environmentTagName = "Environment";
	public static String extractStartDateTimeTagName = "ExtractStartDateTime";
	public static String extractEndDateTimeTagName = "ExtractEndDateTime";
	public static String extractItemCountTagName = "ExtractItemCount";
	
	public static String itemIdTagName = "ItemId";
	public static String postingAttemptTagName = "PostingAttempt";
	public static String postingSequenceTagName = "PostingSequence";
	public static String postingTypeTagName = "PostingType";
	public static String postingSubTypeTagName = "PostingSubType";
	public static String channelTagName = "Channel";
	public static String postingDrCrEntryTagName = "PostingDrCrEntry";
	public static String postingSourceTagName = "PostingSource";
	public static String responseIdSourceTagName = "ResponseIdSource";
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
	public static String aggregratedTagName = "Aggregrated";
	
	public static String itemIdDrTagName = "ItemId";
	public static String sortCodeDrTagName = "SortCode";
	public static String accountDrTagName = "Account";
	public static String serialDrTagName = "Serial";
	public static String tranCodeDrTagName = "TranCode";
	public static String switchedSortCodeTagName = "SwitchedSortCode";
	public static String switchedAccountTagName = "SwitchedAccount";
	public static String debitFullAmountTagName = "DebitFullAmount";
	
	
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
	
	public static String CoreNodeFilesPath = "D:/RnE_Data/TestXML/";
	public static String CoreNodeFileName = "sampleXY.xml";
	
	public static String CoreNodeHeaderTagName = "CoreNode";
	public static String EntitiesHeaderTagName = "Entities";
	public static String PostingUpdateHeaderTagName = "PostingUpdate";
	public static String PostingItemEntryHeaderTagName = "PostingItemEntry";
	public static String CreditRecordHeaderTagName = "CreditRecord";
	public static String DebitRecordHeaderTagName = "DebitRecord";
	
	public static HashMap<String, String> getCoreNodeFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(CoreNodeFilesPath, CoreNodeFileName, CoreNodeHeaderTagName);
		HashMap<String, String> coreNodeTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				coreNodeTagWiseData.put(businessDateTagName, eachElement.getElementsByTagName(businessDateTagName).item(0).getTextContent());	
				coreNodeTagWiseData.put(extractIdTagName, eachElement.getElementsByTagName(extractIdTagName).item(0).getTextContent());	
				coreNodeTagWiseData.put(intMessageTypeTagName, eachElement.getElementsByTagName(intMessageTypeTagName).item(0).getTextContent());						
				coreNodeTagWiseData.put(postingExtractIdTagName, eachElement.getElementsByTagName(postingExtractIdTagName).item(0).getTextContent());	
				coreNodeTagWiseData.put(processingParticipantIdTagName, eachElement.getElementsByTagName(processingParticipantIdTagName).item(0).getTextContent());	
				coreNodeTagWiseData.put(extMessageTypeTagName, eachElement.getElementsByTagName(extMessageTypeTagName).item(0).getTextContent());	
				coreNodeTagWiseData.put(messageSourceTagName, eachElement.getElementsByTagName(messageSourceTagName).item(0).getTextContent());	
				coreNodeTagWiseData.put(recordCountsTagName, eachElement.getElementsByTagName(recordCountsTagName).item(0).getTextContent());	
				coreNodeTagWiseData.put(messageDestinationTagName, eachElement.getElementsByTagName(messageDestinationTagName).item(0).getTextContent());						
				
			}
		}
		return coreNodeTagWiseData;		
	}
	
	public static HashMap<String, String> getEntitiesFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(CoreNodeFilesPath, CoreNodeFileName, EntitiesHeaderTagName);
		HashMap<String, String> entitySetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				entitySetTagWiseData.put(entityTypeTagName, eachElement.getElementsByTagName(entityTypeTagName).item(0).getTextContent());	
				entitySetTagWiseData.put(entityStateTagName, eachElement.getElementsByTagName(entityStateTagName).item(0).getTextContent());	
				entitySetTagWiseData.put(entityIdTagName, eachElement.getElementsByTagName(entityIdTagName).item(0).getTextContent());					
				entitySetTagWiseData.put(sourceDateTimeTagName, eachElement.getElementsByTagName(sourceDateTimeTagName).item(0).getTextContent());	
				
			}
		}
		return entitySetTagWiseData;		
	}
	
	public static HashMap<String, String> getPostingUpdateFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(CoreNodeFilesPath, CoreNodeFileName, PostingUpdateHeaderTagName);
		HashMap<String, String> postingUpdateTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				postingUpdateTagWiseData.put(postingExtractIdTagName, eachElement.getElementsByTagName(postingExtractIdTagName).item(0).getTextContent());	
				postingUpdateTagWiseData.put(extractSequenceTagName, eachElement.getElementsByTagName(extractSequenceTagName).item(0).getTextContent());	
				postingUpdateTagWiseData.put(extractRevisionTagName, eachElement.getElementsByTagName(extractRevisionTagName).item(0).getTextContent());					
				postingUpdateTagWiseData.put(fileIdTagName, eachElement.getElementsByTagName(fileIdTagName).item(0).getTextContent());	
				postingUpdateTagWiseData.put(weekdayTagName, eachElement.getElementsByTagName(weekdayTagName).item(0).getTextContent());	
				postingUpdateTagWiseData.put(fileTypeTagName, eachElement.getElementsByTagName(fileTypeTagName).item(0).getTextContent());	
				postingUpdateTagWiseData.put(currencyTagName, eachElement.getElementsByTagName(currencyTagName).item(0).getTextContent());					
				postingUpdateTagWiseData.put(environmentTagName, eachElement.getElementsByTagName(environmentTagName).item(0).getTextContent());
				postingUpdateTagWiseData.put(extractStartDateTimeTagName, eachElement.getElementsByTagName(extractStartDateTimeTagName).item(0).getTextContent());	
				postingUpdateTagWiseData.put(extractEndDateTimeTagName, eachElement.getElementsByTagName(extractEndDateTimeTagName).item(0).getTextContent());					
				postingUpdateTagWiseData.put(extractItemCountTagName, eachElement.getElementsByTagName(extractItemCountTagName).item(0).getTextContent());
			}
		}
		return postingUpdateTagWiseData;	
	}
	
	public static HashMap<String, String> getPostingItemEntryFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(CoreNodeFilesPath, CoreNodeFileName, PostingItemEntryHeaderTagName);
		HashMap<String, String> postingItemEntryTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				postingItemEntryTagWiseData.put(itemIdTagName, eachElement.getElementsByTagName(itemIdTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(postingAttemptTagName, eachElement.getElementsByTagName(postingAttemptTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(postingSequenceTagName, eachElement.getElementsByTagName(postingSequenceTagName).item(0).getTextContent());					
				postingItemEntryTagWiseData.put(postingSubTypeTagName, eachElement.getElementsByTagName(postingSubTypeTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(postingTypeTagName, eachElement.getElementsByTagName(postingTypeTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(channelTagName, eachElement.getElementsByTagName(channelTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(postingDrCrEntryTagName, eachElement.getElementsByTagName(postingDrCrEntryTagName).item(0).getTextContent());					
				postingItemEntryTagWiseData.put(postingSourceTagName, eachElement.getElementsByTagName(postingSourceTagName).item(0).getTextContent());
				postingItemEntryTagWiseData.put(responseIdSourceTagName, eachElement.getElementsByTagName(responseIdSourceTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(postingDayTagName, eachElement.getElementsByTagName(postingDayTagName).item(0).getTextContent());					
				postingItemEntryTagWiseData.put(settlementDateTagName, eachElement.getElementsByTagName(settlementDateTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(postedAmmountTagName, eachElement.getElementsByTagName(postedAmmountTagName).item(0).getTextContent());					
				postingItemEntryTagWiseData.put(postingDecisionReasonCodeTagName, eachElement.getElementsByTagName(postingDecisionReasonCodeTagName).item(0).getTextContent());				
				postingItemEntryTagWiseData.put(postingOverrideFlagTagName, eachElement.getElementsByTagName(postingOverrideFlagTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(nPASortCodeTagName, eachElement.getElementsByTagName(nPASortCodeTagName).item(0).getTextContent());					
				postingItemEntryTagWiseData.put(nPAAccountTagName, eachElement.getElementsByTagName(nPAAccountTagName).item(0).getTextContent());
				postingItemEntryTagWiseData.put(supportingInfoTagName, eachElement.getElementsByTagName(supportingInfoTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(chequeCountTagName, eachElement.getElementsByTagName(chequeCountTagName).item(0).getTextContent());					
				postingItemEntryTagWiseData.put(collectingParticipantIDTagName, eachElement.getElementsByTagName(collectingParticipantIDTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(collectingLocationTagName, eachElement.getElementsByTagName(collectingLocationTagName).item(0).getTextContent());					
				postingItemEntryTagWiseData.put(aggregratedTagName, eachElement.getElementsByTagName(aggregratedTagName).item(0).getTextContent());
				
				
			}
		}
		return postingItemEntryTagWiseData;	
	}
	
	public static HashMap<String, String> getDebitRecordFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(CoreNodeFilesPath, CoreNodeFileName, DebitRecordHeaderTagName);
		HashMap<String, String> debitRecordTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				debitRecordTagWiseData.put(itemIdDrTagName, eachElement.getElementsByTagName(itemIdDrTagName).item(0).getTextContent());	
				debitRecordTagWiseData.put(sortCodeDrTagName, eachElement.getElementsByTagName(sortCodeDrTagName).item(0).getTextContent());	
				debitRecordTagWiseData.put(accountDrTagName, eachElement.getElementsByTagName(accountDrTagName).item(0).getTextContent());					
				debitRecordTagWiseData.put(serialDrTagName, eachElement.getElementsByTagName(serialDrTagName).item(0).getTextContent());	
				debitRecordTagWiseData.put(tranCodeDrTagName, eachElement.getElementsByTagName(tranCodeDrTagName).item(0).getTextContent());	
				debitRecordTagWiseData.put(switchedSortCodeTagName, eachElement.getElementsByTagName(switchedSortCodeTagName).item(0).getTextContent());	
				debitRecordTagWiseData.put(switchedAccountTagName, eachElement.getElementsByTagName(switchedAccountTagName).item(0).getTextContent());					
				debitRecordTagWiseData.put(debitFullAmountTagName, eachElement.getElementsByTagName(debitFullAmountTagName).item(0).getTextContent());
				}
		}
		return debitRecordTagWiseData;	
	}
	
	public static HashMap<String, String> getCreditRecordFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(CoreNodeFilesPath, CoreNodeFileName, CreditRecordHeaderTagName);
		HashMap<String, String> creditRecordTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				creditRecordTagWiseData.put(itemIdCrTagName, eachElement.getElementsByTagName(itemIdCrTagName).item(0).getTextContent());	
				creditRecordTagWiseData.put(sortCodeCrTagName, eachElement.getElementsByTagName(sortCodeCrTagName).item(0).getTextContent());	
				creditRecordTagWiseData.put(accountCrTagName, eachElement.getElementsByTagName(accountCrTagName).item(0).getTextContent());					
				creditRecordTagWiseData.put(referenceCrTagName, eachElement.getElementsByTagName(referenceCrTagName).item(0).getTextContent());	
				creditRecordTagWiseData.put(tranCodeCrTagName, eachElement.getElementsByTagName(tranCodeCrTagName).item(0).getTextContent());	
				creditRecordTagWiseData.put(originalAmountTagName, eachElement.getElementsByTagName(originalAmountTagName).item(0).getTextContent());	
				creditRecordTagWiseData.put(originalSortCodeTagName, eachElement.getElementsByTagName(originalSortCodeTagName).item(0).getTextContent());					
				creditRecordTagWiseData.put(originalAccountTagName, eachElement.getElementsByTagName(originalAccountTagName).item(0).getTextContent());
				creditRecordTagWiseData.put(creditExceptionCodeTagName, eachElement.getElementsByTagName(creditExceptionCodeTagName).item(0).getTextContent());					
				creditRecordTagWiseData.put(beneficiaryNameTagName, eachElement.getElementsByTagName(beneficiaryNameTagName).item(0).getTextContent());
     		
     		}
		}
		return creditRecordTagWiseData;	
	}
	
	
	private static NodeList getNodeSetList(String CoreNodeFilesPath, String CoreNodeFileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(CoreNodeFilesPath, CoreNodeFileName)).getElementsByTagName(elementsSetWithTagName);		
	}

}
