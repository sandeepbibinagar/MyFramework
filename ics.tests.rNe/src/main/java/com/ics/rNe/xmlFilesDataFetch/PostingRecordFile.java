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
public class PostingRecordFile extends GenericMethodUtilis{
	
	public static String entityTypeTagName = "EntityType";
	public static String entityStateTagName = "EntityState";
	public static String entityIdTagName = "EntityId";
	public static String sourceDateTimeTagName = "SourceDateTime";
	
	
	public static String postingExtractIdTagName = "PostingExtractId";
	public static String extractSequenceTagName = "ExtractSequence";
	public static String extractRevisionTagName = "ExtractRevision";
	public static String fieldTagName = "Field";
	public static String weekdayTagName = "Weekday";
	public static String fileTypeTagName = "FileType";
	public static String currencyTagName = "Currency";
	public static String environmentTagName = "Environment";
	public static String extractStartDateTimeTagName = "ExtractStartDateTime";
	public static String extractEndDateTimeTagName = "ExtractEndDateTime";
	public static String extractItemTagName = "ExtractItem";
	
	
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
	
	
	public static String itemIdDrTagName = "ItemId";
	public static String sortCodeDrTagName = "SortCode";
	public static String accountDrTagName = "Account";
	public static String referenceDrTagName = "Reference";
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
	
	public static String postingRecordFilesPath = "D:/RnE_Data/TestXML/";
	public static String postingRecordFileName = "sampleX.xml";
	
	public static String EntityHeaderTagName = "EntitySet";
	public static String PostingUpdateHeaderTagName = "PostingUpdate";
	public static String PostingItemEntryHeaderTagName = "PostingItemEntry";
	public static String DebitRecordHeaderTagName = "DebitRecord";
	public static String CreditRecordHeaderTagName = "CreditRecord";
	
	public static HashMap<String, String> getEntitySetFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(postingRecordFilesPath, postingRecordFileName, EntityHeaderTagName);
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
		NodeList nodeSetList = getNodeSetList(postingRecordFilesPath, postingRecordFileName, PostingUpdateHeaderTagName);
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
				postingUpdateTagWiseData.put(fieldTagName, eachElement.getElementsByTagName(fieldTagName).item(0).getTextContent());	
				postingUpdateTagWiseData.put(weekdayTagName, eachElement.getElementsByTagName(weekdayTagName).item(0).getTextContent());	
				postingUpdateTagWiseData.put(fileTypeTagName, eachElement.getElementsByTagName(fileTypeTagName).item(0).getTextContent());	
				postingUpdateTagWiseData.put(currencyTagName, eachElement.getElementsByTagName(currencyTagName).item(0).getTextContent());					
				postingUpdateTagWiseData.put(environmentTagName, eachElement.getElementsByTagName(environmentTagName).item(0).getTextContent());
				postingUpdateTagWiseData.put(extractStartDateTimeTagName, eachElement.getElementsByTagName(extractStartDateTimeTagName).item(0).getTextContent());	
				postingUpdateTagWiseData.put(extractEndDateTimeTagName, eachElement.getElementsByTagName(extractEndDateTimeTagName).item(0).getTextContent());					
				postingUpdateTagWiseData.put(extractItemTagName, eachElement.getElementsByTagName(extractItemTagName).item(0).getTextContent());
			}
		}
		return postingUpdateTagWiseData;	
	}
	
	
	public static HashMap<String, String> getPositemEntryFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(postingRecordFilesPath, postingRecordFileName, PostingUpdateHeaderTagName);
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
				postingItemEntryTagWiseData.put(clearingDateTagName, eachElement.getElementsByTagName(clearingDateTagName).item(0).getTextContent());
				postingItemEntryTagWiseData.put(settlementDateTagName, eachElement.getElementsByTagName(settlementDateTagName).item(0).getTextContent());	
				postingItemEntryTagWiseData.put(postedAmmountTagName, eachElement.getElementsByTagName(postedAmmountTagName).item(0).getTextContent());					
				postingItemEntryTagWiseData.put(postingDecisionReasonCodeTagName, eachElement.getElementsByTagName(postingDecisionReasonCodeTagName).item(0).getTextContent());
			}
		}
		return postingItemEntryTagWiseData;	
	}
	
	public static HashMap<String, String> getDebitRecordFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(postingRecordFilesPath, postingRecordFileName, DebitRecordHeaderTagName);
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
				debitRecordTagWiseData.put(referenceDrTagName, eachElement.getElementsByTagName(referenceDrTagName).item(0).getTextContent());	
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
		NodeList nodeSetList = getNodeSetList(postingRecordFilesPath, postingRecordFileName, CreditRecordHeaderTagName);
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
	
	private static NodeList getNodeSetList(String postingRecordFilesPath, String postingRecordFileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(postingRecordFilesPath, postingRecordFileName)).getElementsByTagName(elementsSetWithTagName);		
	}


}
