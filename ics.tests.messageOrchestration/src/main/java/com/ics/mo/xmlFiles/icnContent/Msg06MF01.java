package com.ics.mo.xmlFiles.icnContent;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import com.ics.mo.xmlFiles.isoContent.Msg06;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

//<copyright  file="Msg06MF01.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
* Module Name -  Msg06MF01.java
************************************************************************ 
* Date -  26/04/2017
************************************************************************ 
* Created By -  MuluguUm
************************************************************************ 
* Description - Required Generic Functionalities to check Msg06MF01
***********************************************************************/
public class Msg06MF01 extends Msg06MA01 {
	private static String actualICNContent06MF01 ;

	/*
	 * 1. Check ICN File record of 06MF01 loaded in Source Table
	 */
	public static void checkICNFile06MF01InSourceDB(String isoContentData, String workFlow) throws Exception
	{
		String query = "Select ICNContent = CAST(ICNContent AS XML), SourceID, SourceSKID from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "where  MessageType = '" + workFlow + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		actualICNContent06MF01 = "";
		String debitItemId = "";		
		boolean debitItemIDCheckFlag = false;
		debitItemId = expectedDebitItemId.get(0);
		while(resultSet.next())
		{
			actualICNContent06MF01 = resultSet.getString(columnICNContent);
			if(actualICNContent06MF01.indexOf(debitItemId) > 0)
			{
				debitItemIDCheckFlag = true;
				sourceID = resultSet.getString(columnSourceID);
				publishResults(debitItemIDCheckFlag, workFlow + " ICN file details for enitityId "+debitItemId + " loaded in source table with SourceID : "+ sourceID, 
						"Should see ICN file details of workflow " + workFlow + " to load in source table",
						"Check ICN file details of workflow " + workFlow + " to load in source table");
				checkICNCoreDataValidationWithISOData(actualICNContent06MF01, workFlow, "APG", isoContentData);
				checkItemsSectionOfICNFile(actualICNContent06MF01, Msg06.getExpectedISODbtItemDetails(), workFlow);
			}	
		}
		if(!debitItemIDCheckFlag)
		{
			publishResults(debitItemIDCheckFlag, workFlow + " ICN file details for enitityId "+debitItemId + " not loaded in source table ", 
					"Should see ICN file details of workflow " +workFlow + " to load in source table",
					"Check ICN file details of workflow " +workFlow + " to load in source table");
		}
	}

	/*
	 * 1.1 Check ICN Core Data Validation With ISO Data 
	 */
	public static void  checkICNCoreDataValidationWithISOData(String tempICNData, String workFlow, String expectedDestination, String expectedData)
	{
		switch("ExtractId")
		{
		case "ExtractId" :
			String extractId = tempICNData.substring(tempICNData.indexOf("<ExtractId>"), tempICNData.indexOf("</ExtractId>"));
			int tempExtracIdIndex = extractId.indexOf(">");
			extractId = extractId.substring(++tempExtracIdIndex);
			publishResults(extractId.equals(sourceID), "ExtractId tag in ICN is "+ extractId,
					"Should see ExtractId tag in ICN content with value "+ sourceID, "Check the ExtractId tag in ICN content" );
		case "BusinessDate" :
			String businessDate =  tempICNData.substring(tempICNData.indexOf("<BusinessDate>"), tempICNData.indexOf("</BusinessDate>"));
			int tempBusinessDateIndex = businessDate.indexOf(">");
			businessDate = businessDate.substring(++tempBusinessDateIndex);
			publishResults(businessDate.equals(new GenericMethodUtilis().dateFormat_3.format(new Date())), "BusinessDate tag in ICN is "+ businessDate,
					"Should see BusinessDate tag in ICN content with value "+ new GenericMethodUtilis().dateFormat_3, "Check the BusinessDate tag in ICN content" );
		case "ProcessingParticipantId" :
			String receiverId = expectedData.substring(expectedData.indexOf("<RcvrId>"), expectedData.indexOf("</RcvrId>"));
			int receiverIdIndex = receiverId.indexOf(">");
			receiverId = receiverId.substring(++receiverIdIndex);
			String processingParticipantId = tempICNData.substring(tempICNData.indexOf("<ProcessingParticipantId>"), tempICNData.indexOf("</ProcessingParticipantId>"));
			int processingIdIndex = processingParticipantId.indexOf(">");
			processingParticipantId = processingParticipantId.substring(++processingIdIndex);
			publishResults(processingParticipantId.equals(receiverId), "ProcessingParticipantId tag in ICN is "+ processingParticipantId,
					"Should see ProcessingParticipantId tag in ICN content with value "+ receiverId, "Check the ProcessingParticipantId tag in ICN content" );					
		case "ExtMessageType" :
			String extMessageType = tempICNData.substring(tempICNData.indexOf("<ExtMessageType>"), tempICNData.indexOf("</ExtMessageType>"));
			int extMessageTypeIndex = extMessageType.indexOf(">");
			extMessageType = extMessageType.substring(++extMessageTypeIndex);
			publishResults(extMessageType.equals(workFlow), "ExtMessageType tag in ICN is "+ extMessageType,
					"Should see ExtMessageType tag in ICN content with value "+ workFlow, "Check the ExtMessageType tag in ICN content" );					
		case "IntMessageType" :
			String intMessageType = tempICNData.substring(tempICNData.indexOf("<IntMessageType>"), tempICNData.indexOf("</IntMessageType>"));
			int intMessageTypeIndex = intMessageType.indexOf(">");
			intMessageType = intMessageType.substring(++intMessageTypeIndex);
			publishResults(intMessageType.equals(workFlow), "IntMessageType tag in ICN is "+ intMessageType,
					"Should see IntMessageType tag in ICN content with value "+ workFlow, "Check the IntMessageType tag in ICN content" );					
		case "MessageSource"	:
			String messageSource = tempICNData.substring(tempICNData.indexOf("<MessageSource>"), tempICNData.indexOf("</MessageSource>"));
			int messageSourceIndex = messageSource.indexOf(">");
			messageSource = messageSource.substring(++messageSourceIndex);
			publishResults(messageSource.equals("MO"), "MessageSource tag in ICN is "+ messageSource,
					"Should see MessageSource tag in ICN content with value "+ "MO", "Check the MessageSource tag in ICN content" );					
		case "MessageDestination" :
			String messageDestination = tempICNData.substring(tempICNData.indexOf("<MessageDestination>"), tempICNData.indexOf("</MessageDestination>"));
			int messageDestinationIndex = messageDestination.indexOf(">");
			messageDestination = messageDestination.substring(++messageDestinationIndex);
			publishResults(messageDestination.equals(expectedDestination), "MessageDestination tag in ICN is "+ messageDestination,
					"Should see MessageDestination tag in ICN content with value "+ expectedDestination, "Check the MessageDestination tag in ICN content" );					
		case "RecordCounts" :
			String recordCounts = tempICNData.substring(tempICNData.indexOf("<RecordCounts>"), tempICNData.indexOf("</RecordCounts>"));
			int recordCountsIndex = recordCounts.indexOf(">");
			recordCounts = recordCounts.substring(++recordCountsIndex);
			publishResults(recordCounts.equals(expectedDebitItemId.size()+ ""), "RecordCounts tag value in ICN is "+ recordCounts,
					"Should see RecordCounts tag in ICN content with value "+ expectedDebitItemId.size()+ "", "Check the RecordCounts tag in ICN content" );					
		}
	}

	/*
	 * 1.2 Check Items Section of ICN File
	 */
	public static void checkItemsSectionOfICNFile(String actualICNContent, HashMap<String, List<HashMap<String, String>>> expectedICNContent, String workFlow)
	{
		String tempActualData = actualICNContent;
		actualICNContent = actualICNContent.substring(actualICNContent.indexOf("<Item>") + "<Item>".length() ,
				actualICNContent.indexOf("</Item>"));
		tempActualData = tempActualData.substring(actualICNContent.indexOf("</Item>") + "</Item>".length());
		do
		{
			String actualItemId  = actualICNContent.substring(actualICNContent.indexOf("<ItemId>") + "<ItemId>".length() ,
					actualICNContent.indexOf("</ItemId>"));
			for(HashMap<String, String> expectedDebitItemDetails : expectedICNContent.get(actualItemId))
			{
				for(Entry<String, String> eachDebitItemDetail :expectedDebitItemDetails.entrySet())
				{
					if((eachDebitItemDetail.getKey()).equals(Msg06.getISOSerialNoKeyName()))
					{
						String actualSerialNo = actualICNContent.substring(actualICNContent.indexOf("<Serial>")+ "<Serial>".length(), actualICNContent.indexOf("</Serial>"));
						if(actualSerialNo.equals(eachDebitItemDetail.getValue()))
						{
							publishResults(true, Msg06.getISOSerialNoKeyName() + " tag value is " + actualSerialNo,
									Msg06.getISOSerialNoKeyName() + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + Msg06.getISOSerialNoKeyName() + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
						else{
							publishResults(false, Msg06.getISOSerialNoKeyName() + " tag value is " + actualSerialNo,
									Msg06.getISOSerialNoKeyName() + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + Msg06.getISOSerialNoKeyName() + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}				    
					}else if((eachDebitItemDetail.getKey()).equals(Msg06.getISOSortCodeKeyName()))
					{
						String actualSortCode = actualICNContent.substring(actualICNContent.indexOf("<SortCode>")+ "<SortCode>".length(), actualICNContent.indexOf("</SortCode>"));
						if(actualSortCode.equals(eachDebitItemDetail.getValue()))
						{
							publishResults(true, Msg06.getISOSortCodeKeyName() + " tag value is " + actualSortCode,
									Msg06.getISOSortCodeKeyName() + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + Msg06.getISOSortCodeKeyName() + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
						else{
							publishResults(false, Msg06.getISOSortCodeKeyName() + " tag value is " + actualSortCode,
									Msg06.getISOSortCodeKeyName() + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + Msg06.getISOSortCodeKeyName() + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
					}else if((eachDebitItemDetail.getKey()).equals(Msg06.getISOAccoutNoKeyName()))
					{
						String actualAccount = actualICNContent.substring(actualICNContent.indexOf("<Account>")+ "<Account>".length(), actualICNContent.indexOf("</Account>"));
						if(actualAccount.equals(eachDebitItemDetail.getValue()))
						{
							publishResults(true, Msg06.getISOAccoutNoKeyName() + " tag value is " + actualAccount,
									Msg06.getISOAccoutNoKeyName() + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + Msg06.getISOAccoutNoKeyName() + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
						else{
							publishResults(false, Msg06.getISOAccoutNoKeyName() + " tag value is " + actualAccount,
									Msg06.getISOAccoutNoKeyName() + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + Msg06.getISOAccoutNoKeyName() + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
					}else if((eachDebitItemDetail.getKey()).equals(Msg06.getISOTransactionCode()))
					{
						String actualTxCode = actualICNContent.substring(actualICNContent.indexOf("<TranCode>")+ "<TranCode>".length(), actualICNContent.indexOf("</TranCode>"));
						if(actualTxCode.equals(eachDebitItemDetail.getValue()))
						{
							publishResults(true, "TranCode" + " tag value is " + actualTxCode,
									"TranCode"  + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + "TranCode" + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
						else{
							publishResults(false, "TranCode" + " tag value is " + actualTxCode,
									"TranCode"  + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + "TranCode" + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
					}else if((eachDebitItemDetail.getKey()).equals(Msg06.getISOAmount()))
					{
						String actualTxCode = actualICNContent.substring(actualICNContent.indexOf("<Amount>")+ "<Amount>".length(), actualICNContent.indexOf("</Amount>"));
						if(actualTxCode.equals(eachDebitItemDetail.getValue()))
						{
							publishResults(true, Msg06.getISOAmount() + " tag value is " + actualTxCode,
									Msg06.getISOAmount() + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + Msg06.getISOAmount() + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
						else{
							publishResults(false, Msg06.getISOAmount() + " tag value is " + actualTxCode,
									Msg06.getISOAmount()  + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + Msg06.getISOAmount() + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
					}else if((eachDebitItemDetail.getKey()).equals(Msg06.getISOimage()))
					{
						String actualImage = actualICNContent.substring(actualICNContent.indexOf("<Image>")+ "<Image>".length(), actualICNContent.indexOf("</Image>"));
						if(actualImage.equals(eachDebitItemDetail.getValue()))
						{
							publishResults(true, Msg06.getISOimage() + " tag value is " + actualImage,
									Msg06.getISOimage() + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + Msg06.getISOimage() + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
						else{
							publishResults(false, Msg06.getISOimage() + " tag value is " + actualImage,
									Msg06.getISOimage() + " tag value is " + eachDebitItemDetail.getValue(),
									"Check ICN validation for " + Msg06.getISOimage() + " tag value for workflow " + workFlow + " for ItemId  "+ actualItemId );
						}
					}
				}
			}

		}while(tempActualData.indexOf("<Item>") > 0);
	}

}
