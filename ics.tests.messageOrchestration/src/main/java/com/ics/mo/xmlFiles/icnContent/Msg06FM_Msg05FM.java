package com.ics.mo.xmlFiles.icnContent;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.ics.mo.common.MoGenericMethods;
import com.ics.mo.xmlFiles.isoContent.Msg05;
import com.ics.mo.xmlFiles.isoContent.Msg06;
import com.ics.seleniumCoreUtilis.Component;

//<copyright  file="MsgFM01.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
 * Module Name -  MsgFM01.java
 ************************************************************************ 
 * Date -  26/04/2017
 ************************************************************************ 
 * Created By -  MuluguUm
 ************************************************************************ 
 * Description - Required Generic Functionalities to check MsgFM01
 ***********************************************************************/
public class Msg06FM_Msg05FM extends MoGenericMethods{
	protected static final Logger msg05FM_06FM_Log = Logger.getLogger(Msg06FM_Msg05FM.class.getSimpleName()) ;
	protected static String actualICNContentMsg13DM01InReceiveStaging;
	protected static String actualSourceIDMsg06FM01;	
	protected static String actualRowIDMsg13DM01;
	protected static HashMap<String, String> fredCodelineChangeSorstCds, fredCodelineChangeAcctNbs ;
	protected static HashMap<String, String> fredCodelineChangeSrlNbs , fredCodelineChangeTransCode;
	protected static HashMap<String, String> fredCodelineChangeDetails;
	protected static HashMap<String, HashMap<String, String>>  mapOfItemIdsWithChangedCodeline  = new LinkedHashMap<String, HashMap<String, String>>();

	/*
	 * 1. Check 06FM01 file record In Receive Staging Table
	 */
	public static void checkICNFileFM01InsertInReceiveStaging(String inputFilePath, String inputFileName, String outputFilePath, String workFlow, List<String> itemIds) throws Exception
	{
		checkSourceIdInsertInReceiveStagingTable(workFlow);
		if(actualRowIDMsgFM01.equals(""))
		{
			msg05FM_06FM_Log.error("WorkFlow "+workFlow + " not loaded in ReceiveStaging table " );
			publishResults(false, "WorkFlow "+ workFlow + " ICN content not loaded in ReceiveStaging table " ,
					"Should see workFlow "+ workFlow + " ICN content to load in ReceiveStaging table",
					"Check workFlow "+ workFlow + " in ReceiveStaging table");	
		}else {
			checkICNFile06FM01InsertedDataInReceiveStaging(inputFilePath, inputFileName, outputFilePath, workFlow, itemIds);
			msg05FM_06FM_Log.info("WorkFlow "+workFlow + " loaded in ReceiveStaging table with RowId/Msg_Id "+ actualRowIDMsgFM01 );
			publishResults(true, "WorkFlow "+ workFlow + " ICN content loaded in ReceiveStaging table with RowId/Msg_Id "+ actualRowIDMsgFM01 ,
					"Should see workFlow "+ workFlow + " ICN content to load in ReceiveStaging table",
					"Check workFlow "+ workFlow + " in ReceiveStaging table");	
		}
	}

	/*
	 * 2. Check ICN File FRED Data Insert in Receive Staging with FRED input file
	 */
	protected static void checkICNFile06FM01InsertedDataInReceiveStaging(String inputFilePath, String inputFileName, String outputFilePath, String workFlow, List<String> itemIds) throws IOException, InterruptedException
	{
		msg05FM_06FM_Log.info("Validating ICN data inserted in ReceiveStaging Table for workflow "+  workFlow + "with RowId "+ actualRowIDMsgFM01);	
		String actualContentPath = outputFilePath + "ActualICNContent_" + workFlow + ".txt";
		writeToFile(actualContentPath, actualICNContentMsgFM01InReceiveStaging);
		String xmlExtensionActualData = getRenamedFileExtenstion(actualContentPath, ".txt", ".xml");
		String strExpectedICN06FM01Data = FileUtils.readFileToString(new File(inputFilePath + inputFileName));
		String strActualICN06FM01Data = FileUtils.readFileToString(new File(xmlExtensionActualData));
		validationStepInformation("Detailed comparison report for workflow " + workFlow + " with Msgs_Id " + actualRowIDMsgFM01 + " in ReceiveStaging table as below:");
		//Compare Core Section
		validationStepInformation("Check on Core section");	
		msg05FM_06FM_Log.info("Check on Core section");
		checkActualICNCoreDatawithExpected(strActualICN06FM01Data, workFlow, strExpectedICN06FM01Data);

		//Compare Entities Section
		validationStepInformation("Check on Entities section");
		msg05FM_06FM_Log.info("Check on Entities section");
		checkActualICNEntitiesSectionWithExpected(strActualICN06FM01Data, workFlow, strExpectedICN06FM01Data);

		//Compare Item Section
		validationStepInformation("Check on Items section");
		msg05FM_06FM_Log.info("Check on Items section");
		checkActualICNItemsSectionWithExpected(strActualICN06FM01Data, workFlow, strExpectedICN06FM01Data);

		//Compare CaptureInfo Section	
		validationStepInformation("Check on CaptureInfo section tags");
		msg05FM_06FM_Log.info("Check on CaptureInfo section tags");
		checkActualICNCaptureItemWithExpected(strActualICN06FM01Data, workFlow, strExpectedICN06FM01Data);

		if(workFlow.equals("06FM01"))
		{
			getCodeLineSectionFlagStatusOfFredFile(expectedDebitItemId);
		}else if(workFlow.equals("05FM01")){
			getCodeLineSectionFlagStatusOfFredFile(expectedCreditItemId);
		}
		validateCodeLineBasedOnIsCorrectedTagForDebits(itemIds, workFlow);		
		if(mapOfItemIdsWithChangedCodeline.size() > 0)
		{
			for(Entry<String, HashMap<String, String>> eachItemDetails : mapOfItemIdsWithChangedCodeline.entrySet())
			{
				String item = eachItemDetails.getKey();	
				msg05FM_06FM_Log.info("Code Line Changed for ItemId "+ item + " with details as : "+ eachItemDetails.getValue());
				validationStepInformation("Code Line Changed for ItemId "+ item + " with details as : "+ eachItemDetails.getValue());
				if(workFlow.equals("06FM01"))
				{
					Msg06.mapofItemIdAndItsBankDetails.put(item, eachItemDetails.getValue());
				}else  if(workFlow.equals("05FM01")){
					Msg05.mapofItemIdAndItsBankDetails.put(item, eachItemDetails.getValue());   
				}

			}
		}	
		if(workFlow.equals("06FM01"))
		{
			for(Entry<String, HashMap<String, String>> eachItemIdDetails :Msg06.mapofItemIdAndItsBankDetails.entrySet())
			{
				msg05FM_06FM_Log.info("CodeLine details of " + workFlow + " message for itemId is "+ eachItemIdDetails.getKey() + " is "+ eachItemIdDetails.getValue() );
			}
		}else  if(workFlow.equals("05FM01")){
			for(Entry<String, HashMap<String, String>> eachItemIdDetails :Msg05.mapofItemIdAndItsBankDetails.entrySet())
			{
				msg05FM_06FM_Log.info("CodeLine details of " + workFlow + " message for itemId is "+ eachItemIdDetails.getKey() + " is "+ eachItemIdDetails.getValue() );
			}
		}
		
	}

	/*
	 * 2.1 Validate ICN FRED File Data- Core Section loaded in Receive Staging table with FRED input file
	 */
	protected static void checkActualICNCoreDatawithExpected(String tempICNActualData, String workFlow, String tempICNExpectedData)
	{
		switch("ExtractId")
		{
		case "ExtractId" :
			String actualExtractId = tempICNActualData.substring(tempICNActualData.indexOf("<ExtractId>"), tempICNActualData.indexOf("</ExtractId>"));
			int tempActualExtractIdIndex = actualExtractId.indexOf(">");
			actualExtractId = actualExtractId.substring(++tempActualExtractIdIndex);

			String expectedExtractId = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<ExtractId>"), tempICNExpectedData.indexOf("</ExtractId>"));
			int tempExpectedExtractIdIndex = expectedExtractId.indexOf(">");
			expectedExtractId = expectedExtractId.substring(++tempExpectedExtractIdIndex);			

			publishResults(actualExtractId.equals(expectedExtractId), "ExtractId tag in ICN is "+ actualExtractId,
					"Should see ExtractId tag in ICN content with value "+ expectedExtractId, "Check the ExtractId tag of ICN content" );
		case "BusinessDate" :
			String actualBusinessDate =  tempICNActualData.substring(tempICNActualData.indexOf("<BusinessDate>"), tempICNActualData.indexOf("</BusinessDate>"));
			int tempActualBusinessDateIndex = actualBusinessDate.indexOf(">");
			actualBusinessDate = actualBusinessDate.substring(++tempActualBusinessDateIndex);

			String expectedBusinessDate =  tempICNExpectedData.substring(tempICNExpectedData.indexOf("<BusinessDate>"), tempICNExpectedData.indexOf("</BusinessDate>"));
			int tempExpectedBusinessDateIndex = expectedBusinessDate.indexOf(">");
			expectedBusinessDate = expectedBusinessDate.substring(++tempExpectedBusinessDateIndex);			

			publishResults(actualBusinessDate.equals(expectedBusinessDate), "BusinessDate tag in ICN is "+ actualBusinessDate,
					"Should see BusinessDate tag in ICN content with value "+ expectedBusinessDate, "Check the BusinessDate tag in ICN content" );
		case "ProcessingParticipantId" :
			String actualProcessingParticipantId = tempICNActualData.substring(tempICNActualData.indexOf("<ProcessingParticipantId>"), tempICNActualData.indexOf("</ProcessingParticipantId>"));
			int actualProcessingParticipantIdIndex = actualProcessingParticipantId.indexOf(">");
			actualProcessingParticipantId = actualProcessingParticipantId.substring(++actualProcessingParticipantIdIndex);

			String expectedProcessingParticipantId = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<ProcessingParticipantId>"), tempICNExpectedData.indexOf("</ProcessingParticipantId>"));
			int expectedProcessingParticipantIdIndex = expectedProcessingParticipantId.indexOf(">");
			expectedProcessingParticipantId = expectedProcessingParticipantId.substring(++expectedProcessingParticipantIdIndex);

			publishResults(actualProcessingParticipantId.equals(expectedProcessingParticipantId), "ProcessingParticipantId tag in ICN is "+ actualProcessingParticipantId,
					"Should see ProcessingParticipantId tag in ICN content with value "+ expectedProcessingParticipantId, "Check the ProcessingParticipantId tag in ICN content" );					
		case "ExtMessageType" :
			String actualExtMessageType = tempICNActualData.substring(tempICNActualData.indexOf("<ExtMessageType>"), tempICNActualData.indexOf("</ExtMessageType>"));
			int actualExtMessageTypeIndex = actualExtMessageType.indexOf(">");
			actualExtMessageType = actualExtMessageType.substring(++actualExtMessageTypeIndex);

			String expectedExtMessageType = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<ExtMessageType>"), tempICNExpectedData.indexOf("</ExtMessageType>"));
			int expectedExtMessageTypeIndex = expectedExtMessageType.indexOf(">");
			expectedExtMessageType = expectedExtMessageType.substring(++expectedExtMessageTypeIndex);
			publishResults(actualExtMessageType.equals(expectedExtMessageType), "ExtMessageType tag in ICN is "+ actualExtMessageType,
					"Should see ExtMessageType tag in ICN content with value "+ expectedExtMessageType, "Check the ExtMessageType tag in ICN content" );					
		case "IntMessageType" :
			String actualIntMessageType = tempICNActualData.substring(tempICNActualData.indexOf("<IntMessageType>"), tempICNActualData.indexOf("</IntMessageType>"));
			int actualIntMessageTypeIndex = actualIntMessageType.indexOf(">");
			actualIntMessageType = actualIntMessageType.substring(++actualIntMessageTypeIndex);

			String expectedIntMessageType = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<IntMessageType>"), tempICNExpectedData.indexOf("</IntMessageType>"));
			int expectedIntMessageTypeIndex = expectedIntMessageType.indexOf(">");
			expectedIntMessageType = expectedIntMessageType.substring(++expectedIntMessageTypeIndex);
			publishResults(actualIntMessageType.equals(expectedIntMessageType), "IntMessageType tag in ICN is "+ actualIntMessageType,
					"Should see IntMessageType tag in ICN content with value "+ expectedIntMessageType, "Check the IntMessageType tag in ICN content" );					
		case "MessageSource"	:
			String actualMessageSource = tempICNActualData.substring(tempICNActualData.indexOf("<MessageSource>"), tempICNActualData.indexOf("</MessageSource>"));
			int actualMessageSourceIndex = actualMessageSource.indexOf(">");
			actualMessageSource = actualMessageSource.substring(++actualMessageSourceIndex);

			String expectedMessageSource = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<MessageSource>"), tempICNExpectedData.indexOf("</MessageSource>"));
			int expectedMessageSourceIndex = expectedMessageSource.indexOf(">");
			expectedMessageSource = expectedMessageSource.substring(++expectedMessageSourceIndex);
			publishResults(actualMessageSource.equals(expectedMessageSource), "MessageSource tag in ICN is "+ actualMessageSource,
					"Should see MessageSource tag in ICN content with value "+ expectedMessageSource, "Check the MessageSource tag in ICN content" );					
		case "MessageDestination" :
			String actualMessageDestination = tempICNActualData.substring(tempICNActualData.indexOf("<MessageDestination>"), tempICNActualData.indexOf("</MessageDestination>"));
			int actualMessageDestinationIndex = actualMessageDestination.indexOf(">");
			actualMessageDestination = actualMessageDestination.substring(++actualMessageDestinationIndex);

			String expectedMessageDestination = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<MessageDestination>"), tempICNExpectedData.indexOf("</MessageDestination>"));
			int expectedMessageDestinationIndex = expectedMessageDestination.indexOf(">");
			expectedMessageDestination = expectedMessageDestination.substring(++expectedMessageDestinationIndex);
			publishResults(actualMessageDestination.equals(expectedMessageDestination), "MessageDestination tag in ICN is "+ actualMessageDestination,
					"Should see MessageDestination tag in ICN content with value "+ expectedMessageDestination, "Check the MessageDestination tag in ICN content" );					
		case "RecordCounts" :
			String actualRecordCounts = tempICNActualData.substring(tempICNActualData.indexOf("<RecordCounts>"), tempICNActualData.indexOf("</RecordCounts>"));
			int actualRecordCountsIndex = actualRecordCounts.indexOf(">");
			actualRecordCounts = actualRecordCounts.substring(++actualRecordCountsIndex);

			String expectedRecordCounts = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<RecordCounts>"), tempICNExpectedData.indexOf("</RecordCounts>"));
			int expectedRecordCountsIndex = expectedRecordCounts.indexOf(">");
			expectedRecordCounts = expectedRecordCounts.substring(++expectedRecordCountsIndex);
			publishResults(actualRecordCounts.equals(expectedRecordCounts), "RecordCounts tag value in ICN is "+ actualRecordCounts,
					"Should see RecordCounts tag in ICN content with value "+ expectedRecordCounts, "Check the RecordCounts tag in ICN content" );					
		}
	}

	/*
	 * 2.2 Validate ICN  FRED File Data - Entities Section loaded in Receive Staging table with FRED input file
	 */
	protected static void checkActualICNEntitiesSectionWithExpected(String tempICNActualData, String workFlow, String tempICNExpectedData)
	{
		tempICNActualData = tempICNActualData.substring(tempICNActualData.indexOf("<Entities>")+ "<Entities>".length(), tempICNActualData.indexOf("</Entities>"));
		tempICNExpectedData = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<Entities>")+ "<Entities>".length(), tempICNExpectedData.indexOf("</Entities>"));
		String actualData = tempICNActualData;
		String expectedData = tempICNExpectedData;

		do
		{
			tempICNActualData = actualData.substring(actualData.indexOf("<Entity>")+ "<Entity>".length(), actualData.indexOf("</Entity>"));
			tempICNExpectedData = expectedData.substring(expectedData.indexOf("<Entity>")+ "<Entity>".length(), expectedData.indexOf("</Entity>"));
			actualData = actualData.substring(actualData.indexOf("</Entity>") + "</Entity>".length());
			expectedData =  expectedData.substring(expectedData.indexOf("</Entity>") + "</Entity>".length());
			String entityId = "";
			switch("EntityType")
			{
			case "EntityType" :
				String actualEntityType = tempICNActualData.substring(tempICNActualData.indexOf("<EntityType>"), tempICNActualData.indexOf("</EntityType>"));
				int actualEntityTypeIndex = actualEntityType.indexOf(">");
				actualEntityType = actualEntityType.substring(++actualEntityTypeIndex);

				String expectedEntityType = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<EntityType>"), tempICNExpectedData.indexOf("</EntityType>"));
				int expectedEntityTypeIndex = expectedEntityType.indexOf(">");
				expectedEntityType = expectedEntityType.substring(++expectedEntityTypeIndex);
				publishResults(actualEntityType.equals(expectedEntityType), "EntityType tag in ICN is "+ actualEntityType,
						"Should see EntityType tag in ICN content with value "+ expectedEntityType, "Check the EntityType tag in ICN content" );	
			case "EntityId" :
				String actualEntityId = tempICNActualData.substring(tempICNActualData.indexOf("<EntityId>"), tempICNActualData.indexOf("</EntityId>"));
				int actualEntityIdIndex = actualEntityId.indexOf(">");
				actualEntityId = actualEntityId.substring(++actualEntityIdIndex);

				String expectedEntityId = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<EntityId>"), tempICNExpectedData.indexOf("</EntityId>"));
				int expectedEntityIdIndex = expectedEntityId.indexOf(">");
				expectedEntityId = expectedEntityId.substring(++expectedEntityIdIndex);
				boolean entityIdFlag = actualEntityId.equals(expectedEntityId);
				publishResults(entityIdFlag, "EntityId tag in ICN is "+ actualEntityId,
						"Should see EntityId tag in ICN content with value "+ expectedEntityId, "Check the EntityId tag in ICN content" );
				if(entityIdFlag)
				{
					entityId = 	expectedEntityId;

				}else {
					validationErrorInformation("EntityId  "+ expectedEntityId + " details not loaded in ReceiveStaging table for workFlow "+ workFlow);
					msg05FM_06FM_Log.error("EntityId  "+ entityId + " details not loaded in ReceiveStaging table for workFlow "+ workFlow);
					Component.assertTrue(entityIdFlag, "EntityId  "+ expectedEntityId + " details not loaded in ReceiveStaging table for workFlow "+ workFlow);
				}
			case "EntityState" :
				String actualEntityState = tempICNActualData.substring(tempICNActualData.indexOf("<EntityState>"), tempICNActualData.indexOf("</EntityState>"));
				int actualEntityStateIndex = actualEntityState.indexOf(">");
				actualEntityState = actualEntityState.substring(++actualEntityStateIndex);

				String expectedEntityState = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<EntityState>"), tempICNExpectedData.indexOf("</EntityState>"));
				int expectedEntityStateIndex = expectedEntityState.indexOf(">");
				expectedEntityState = expectedEntityState.substring(++expectedEntityStateIndex);
				boolean entityStateFlag = actualEntityState.equals(expectedEntityState);
				publishResults(actualEntityState.equals(expectedEntityState), "EntityState tag in ICN is "+ actualEntityState,
						"Should see EntityState tag in ICN content with value "+ expectedEntityState, "Check the EntityState tag in ICN content" );
				if(entityStateFlag)	
				{
					initialStateFromFred.put(entityId, expectedEntityState);
					if(expectedEntityState.equals("550") || expectedEntityState.equals("551") || expectedEntityState.equals("560")
							|| expectedEntityState.equals("561") )
					{
						msg07MS01ValuesForItems.put(entityId, "570");
					}
				}else {
					validationErrorInformation("Actual EntityState " + actualEntityState + " of EntityId  "+ entityId + " not matches with Expected EntityState " + expectedEntityState);
					msg05FM_06FM_Log.error("Actual EntityState " + actualEntityState + " of EntityId  "+ entityId + " not matches with Expected EntityState " + expectedEntityState);
					Component.assertTrue(entityStateFlag, "Actual EntityState " + actualEntityState + " of EntityId  "+ entityId + " not matches with Expected EntityState " + expectedEntityState);
				}
			case "SourceDateTime" :
				String actualSourceDateTime = tempICNActualData.substring(tempICNActualData.indexOf("<SourceDateTime>"), tempICNActualData.indexOf("</SourceDateTime>"));
				int actualSourceDateTimeIndex = actualSourceDateTime.indexOf(">");
				actualSourceDateTime = actualSourceDateTime.substring(++actualSourceDateTimeIndex);

				String expectedSourceDateTime = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<SourceDateTime>"), tempICNExpectedData.indexOf("</SourceDateTime>"));
				int expectedSourceDateTimeIndex = expectedSourceDateTime.indexOf(">");
				expectedSourceDateTime = expectedSourceDateTime.substring(++expectedSourceDateTimeIndex);
				publishResults(actualSourceDateTime.equals(expectedSourceDateTime), "SourceDateTime tag in ICN is "+ actualSourceDateTime,
						"Should see SourceDateTime tag in ICN content with value "+ expectedSourceDateTime, "Check the SourceDateTime tag in ICN content" );
			}
		}while(expectedData.indexOf("<Entity>") > 0);
	}

	/*
	 * 2.3 Check ICN  FRED File Data - Items Section loaded in Receive Staging table with FRED input file
	 */
	protected static void checkActualICNItemsSectionWithExpected(String tempICNActualData, String workFlow, String tempICNExpectedData)
	{
		tempICNActualData = tempICNActualData.substring(tempICNActualData.indexOf("<Items>")+ "<Items>".length(), tempICNActualData.indexOf("</Items>"));
		tempICNExpectedData = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<Items>")+ "<Items>".length(), tempICNExpectedData.indexOf("</Items>"));
		String actualData = tempICNActualData;
		String expectedData = tempICNExpectedData;
		do
		{
			tempICNActualData = actualData.substring(actualData.indexOf("<Item>")+ "<Item>".length(), actualData.indexOf("</Item>"));
			tempICNExpectedData = expectedData.substring(expectedData.indexOf("<Item>")+ "<Item>".length(), expectedData.indexOf("</Item>"));

			String tempActualCodelineData = actualData.substring(actualData.indexOf("<Codeline>")+ "<Codeline>".length(), actualData.indexOf("</Codeline>"));
			String tempExpectedCodelineData = expectedData.substring(expectedData.indexOf("<Codeline>")+ "<Codeline>".length(), expectedData.indexOf("</Codeline>"));

			actualData = actualData.substring(actualData.indexOf("</Item>") + "</Item>".length());
			expectedData =  expectedData.substring(expectedData.indexOf("</Item>") + "</Item>".length());
			String itemId = "";
			switch("ItemId")
			{
			case "ItemId":
				String actualItemId = tempICNActualData.substring(tempICNActualData.indexOf("<ItemId>"), tempICNActualData.indexOf("</ItemId>"));
				int actualItemIdIndex = actualItemId.indexOf(">");
				actualItemId = actualItemId.substring(++actualItemIdIndex);

				String expectedItemId = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<ItemId>"), tempICNExpectedData.indexOf("</ItemId>"));
				int expectedItemIdIndex = expectedItemId.indexOf(">");
				expectedItemId = expectedItemId.substring(++expectedItemIdIndex);
				boolean itemIdFlag = actualItemId.equals(expectedItemId);
				publishResults(actualItemId.equals(expectedItemId), "ItemId tag in ICN is "+ actualItemId,
						"Should see ItemId tag in ICN content with value "+ expectedItemId, "Check the ItemId tag in ICN content" );
				if(itemIdFlag)
				{
					itemId = expectedItemId;					
				}else {
					validationErrorInformation("ItemId  "+ expectedItemId + " details not loaded in ReceiveStaging table for workFlow "+ workFlow);
					msg05FM_06FM_Log.error("ItemId  "+ expectedItemId + " details not loaded in ReceiveStaging table for workFlow "+ workFlow);
					Component.assertTrue(itemIdFlag, "ItemId  "+ expectedItemId + " details not loaded in ReceiveStaging table for workFlow "+ workFlow);
				}
			case "ProcessId":	
				String actualProcessId = tempICNActualData.substring(tempICNActualData.indexOf("<ProcessId>"), tempICNActualData.indexOf("</ProcessId>"));
				int actualProcessIdIndex = actualProcessId.indexOf(">");
				actualProcessId = actualProcessId.substring(++actualProcessIdIndex);

				String expectedProcessId = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<ProcessId>"), tempICNExpectedData.indexOf("</ProcessId>"));
				int expectedProcessIdIndex = expectedProcessId.indexOf(">");
				expectedProcessId = expectedProcessId.substring(++expectedProcessIdIndex);
				publishResults(actualProcessId.equals(expectedProcessId), "ProcessId tag in ICN is "+ actualProcessId,
						"Should see ProcessId tag in ICN content with value "+ expectedProcessId, "Check the ProcessId tag in ICN content" );
			case "OperatorId":
				String actualOperatorId = tempICNActualData.substring(tempICNActualData.indexOf("<OperatorId>"), tempICNActualData.indexOf("</OperatorId>"));
				int actualOperatorIdIndex = actualOperatorId.indexOf(">");
				actualOperatorId = actualOperatorId.substring(++actualOperatorIdIndex);

				String expectedOperatorId = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<OperatorId>"), tempICNExpectedData.indexOf("</OperatorId>"));
				int expectedOperatorIdIndex = expectedOperatorId.indexOf(">");
				expectedOperatorId = expectedOperatorId.substring(++expectedOperatorIdIndex);
				publishResults(actualOperatorId.equals(expectedOperatorId), "OperatorId tag in ICN is "+ actualOperatorId,
						"Should see OperatorId tag in ICN content with value "+ expectedOperatorId, "Check the OperatorId tag in ICN content" );
			case "UpdateDateTime":
				String actualUpdateDateTime = tempICNActualData.substring(tempICNActualData.indexOf("<UpdateDateTime>"), tempICNActualData.indexOf("</UpdateDateTime>"));
				int actualUpdateDateTimeIndex = actualUpdateDateTime.indexOf(">");
				actualUpdateDateTime = actualUpdateDateTime.substring(++actualUpdateDateTimeIndex);

				String expectedUpdateDateTime = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<UpdateDateTime>"), tempICNExpectedData.indexOf("</UpdateDateTime>"));
				int expectedUpdateDateTimeIndex = expectedUpdateDateTime.indexOf(">");
				expectedUpdateDateTime = expectedUpdateDateTime.substring(++expectedUpdateDateTimeIndex);
				publishResults(actualUpdateDateTime.equals(expectedUpdateDateTime), "UpdateDateTime tag in ICN is "+ actualUpdateDateTime,
						"Should see UpdateDateTime tag in ICN content with value "+ expectedUpdateDateTime, "Check the UpdateDateTime tag in ICN content" );
			case "AdjustmentReason":
				String actualAdjustmentReason = tempICNActualData.substring(tempICNActualData.indexOf("<AdjustmentReason>"), tempICNActualData.indexOf("</AdjustmentReason>"));
				int actualAdjustmentReasonIndex = actualAdjustmentReason.indexOf(">");
				actualAdjustmentReason = actualAdjustmentReason.substring(++actualAdjustmentReasonIndex);

				String expectedAdjustmentReason = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<AdjustmentReason>"), tempICNExpectedData.indexOf("</AdjustmentReason>"));
				int expectedAdjustmentReasonIndex = expectedAdjustmentReason.indexOf(">");
				expectedAdjustmentReason = expectedAdjustmentReason.substring(++expectedAdjustmentReasonIndex);
				publishResults(actualAdjustmentReason.equals(expectedAdjustmentReason), "AdjustmentReason tag in ICN is "+ actualAdjustmentReason,
						"Should see AdjustmentReason tag in ICN content with value "+ expectedAdjustmentReason, "Check the AdjustmentReason tag in ICN content" );
			case "NoPayReason":
				String actualNoPayReason = tempICNActualData.substring(tempICNActualData.indexOf("<NoPayReason>"), tempICNActualData.indexOf("</NoPayReason>"));
				int actualNoPayReasonIndex = actualNoPayReason.indexOf(">");
				actualNoPayReason = actualNoPayReason.substring(++actualNoPayReasonIndex);

				String expectedNoPayReason = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<NoPayReason>"), tempICNExpectedData.indexOf("</NoPayReason>"));
				int expectedNoPayReasonIndex = expectedNoPayReason.indexOf(">");
				expectedNoPayReason = expectedNoPayReason.substring(++expectedNoPayReasonIndex);
				publishResults(actualNoPayReason.equals(expectedNoPayReason), "NoPayReason tag in ICN is "+ actualNoPayReason,
						"Should see NoPayReason tag in ICN content with value "+ expectedNoPayReason, "Check the NoPayReason tag in ICN content" );
			}

			checkActualICNItemsSectionCodeLineWithExpected(tempActualCodelineData, workFlow, tempExpectedCodelineData, itemId);	
		}while(expectedData.indexOf("<Item>") > 0);		
	}

	/*
	 * 2.3.1 Check ICN  FRED File Data - Items Section- CodeLine Information loaded in Receive Staging table with FRED input file
	 */
	protected static void checkActualICNItemsSectionCodeLineWithExpected(String tempICNActualData, String workFlow, String tempICNExpectedData, String itemId)
	{
		switch("Serial")
		{
		case "Serial":
			try{
				String actualSerial = tempICNActualData.substring(tempICNActualData.indexOf("<Serial>"), tempICNActualData.indexOf("</Serial>"));
				int actualSerialIndex = actualSerial.indexOf(">");
				actualSerial = actualSerial.substring(++actualSerialIndex);

				String expectedSerial = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<Serial>"), tempICNExpectedData.indexOf("</Serial>"));
				int expectedSerialIndex = expectedSerial.indexOf(">");
				expectedSerial = expectedSerial.substring(++expectedSerialIndex);
				publishResults(actualSerial.equals(expectedSerial), "Serial tag in ICN is "+ actualSerial,
						"Should see Serial tag in ICN content with value "+ expectedSerial, "Check the Serial tag in ICN content");			
				fredSortCodeDetails.put(serialNo, actualSerial);
			}catch(Exception e)
			{
				String actualSerial = tempICNActualData.substring(tempICNActualData.indexOf("<Reference>"), tempICNActualData.indexOf("</Reference>"));
				int actualSerialIndex = actualSerial.indexOf(">");
				actualSerial = actualSerial.substring(++actualSerialIndex);

				String expectedSerial = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<Reference>"), tempICNExpectedData.indexOf("</Reference>"));
				int expectedSerialIndex = expectedSerial.indexOf(">");
				expectedSerial = expectedSerial.substring(++expectedSerialIndex);
				publishResults(actualSerial.equals(expectedSerial), "Serial tag in ICN is "+ actualSerial,
						"Should see Serial tag in ICN content with value "+ expectedSerial, "Check the Serial tag in ICN content");			
				fredSortCodeDetails.put(serialNo, actualSerial);
			}

		case "SortCode":
			String actualSortCode = tempICNActualData.substring(tempICNActualData.indexOf("<SortCode>"), tempICNActualData.indexOf("</SortCode>"));
			int actualSortCodeIndex = actualSortCode.indexOf(">");
			actualSortCode = actualSortCode.substring(++actualSortCodeIndex);

			String expectedSortCode = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<SortCode>"), tempICNExpectedData.indexOf("</SortCode>"));
			int expectedSortCodeIndex = expectedSortCode.indexOf(">");
			expectedSortCode = expectedSortCode.substring(++expectedSortCodeIndex);
			publishResults(actualSortCode.equals(expectedSortCode), "SortCode tag in ICN is "+ actualSortCode,
					"Should see SortCode tag in ICN content with value "+ expectedSortCode, "Check the SortCode tag in ICN content");	
			fredSortCodeDetails.put(sortCode, actualSortCode);
		case "Account":
			String actualAccount = tempICNActualData.substring(tempICNActualData.indexOf("<Account>"), tempICNActualData.indexOf("</Account>"));
			int actualAccountIndex = actualAccount.indexOf(">");
			actualAccount = actualAccount.substring(++actualAccountIndex);

			String expectedAccount = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<Account>"), tempICNExpectedData.indexOf("</Account>"));
			int expectedAccountIndex = expectedAccount.indexOf(">");
			expectedAccount = expectedAccount.substring(++expectedAccountIndex);
			publishResults(actualAccount.equals(expectedAccount), "Account tag in ICN is "+ actualAccount,
					"Should see Account tag in ICN content with value "+ expectedAccount, "Check the Account tag in ICN content");		
			fredSortCodeDetails.put(accountNo, actualAccount);
		case "TranCode":	
			String actualTranCode = tempICNActualData.substring(tempICNActualData.indexOf("<TranCode>"), tempICNActualData.indexOf("</TranCode>"));
			int actualTranCodeIndex = actualTranCode.indexOf(">");
			actualTranCode = actualTranCode.substring(++actualTranCodeIndex);

			String expectedTranCode = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<TranCode>"), tempICNExpectedData.indexOf("</TranCode>"));
			int expectedTranCodeIndex = expectedTranCode.indexOf(">");
			expectedTranCode = expectedTranCode.substring(++expectedTranCodeIndex);
			publishResults(actualTranCode.equals(expectedTranCode), "TranCode tag in ICN is "+ actualTranCode,
					"Should see TranCode tag in ICN content with value "+ expectedTranCode, "Check the TranCode tag in ICN content");	
			fredSortCodeDetails.put(transactionCode, actualTranCode);
		case "Amount":	
			String actualAmount = tempICNActualData.substring(tempICNActualData.indexOf("<Amount>"), tempICNActualData.indexOf("</Amount>"));
			int actualAmountIndex = actualAmount.indexOf(">");
			actualAmount = actualAmount.substring(++actualAmountIndex);

			String expectedAmount = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<Amount>"), tempICNExpectedData.indexOf("</Amount>"));
			int expectedAmountIndex = expectedAmount.indexOf(">");
			expectedAmount = expectedAmount.substring(++expectedAmountIndex);
			publishResults(actualAmount.equals(expectedAmount), "Amount tag in ICN is "+ actualAmount,
					"Should see Amount tag in ICN content with value "+ expectedAmount, "Check the Amount tag in ICN content");	
			fredSortCodeDetails.put(amount, actualAmount)	;
		case "Narrative":	
			String actualNarrative = tempICNActualData.substring(tempICNActualData.indexOf("<Narrative>"), tempICNActualData.indexOf("</Narrative>"));
			int actualNarrativeIndex = actualNarrative.indexOf(">");
			actualNarrative = actualNarrative.substring(++actualNarrativeIndex);

			String expectedNarrative = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<Narrative>"), tempICNExpectedData.indexOf("</Narrative>"));
			int expectedNarrativeIndex = expectedNarrative.indexOf(">");
			expectedNarrative = expectedNarrative.substring(++expectedNarrativeIndex);
			publishResults(actualNarrative.equals(expectedNarrative), "Narrative tag in ICN is " + actualNarrative,
					"Should see Narrative tag in ICN content with value "+ expectedNarrative, "Check the Narrative tag in ICN content");		
			fredSortCodeDetails.put("Narrative", actualNarrative) ;
		}
		itemAndFREDCodeLineDetails.put(itemId, fredSortCodeDetails);
		fredSortCodeDetails = new LinkedHashMap<String, String>();
	}

	/*
	 * 2.4 Check ICN FRED File Data - Capture Information loaded in Receive Staging table with FRED input file
	 */
	protected static void checkActualICNCaptureItemWithExpected(String tempICNActualData, String workFlow, String tempICNExpectedData)
	{
		tempICNActualData = tempICNActualData.substring(tempICNActualData.indexOf("<CaptureInfo>")+ "<CaptureInfo>".length(), tempICNActualData.indexOf("</CaptureInfo>"));
		tempICNExpectedData = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<CaptureInfo>")+ "<CaptureInfo>".length(), tempICNExpectedData.indexOf("</CaptureInfo>"));
		String actualData = tempICNActualData;
		String expectedData = tempICNExpectedData;
		do
		{
			tempICNActualData = actualData.substring(actualData.indexOf("<CaptureItem>")+ "<CaptureItem>".length(), actualData.indexOf("</CaptureItem>"));
			tempICNExpectedData = expectedData.substring(expectedData.indexOf("<CaptureItem>")+ "<CaptureItem>".length(), expectedData.indexOf("</CaptureItem>"));
			actualData = actualData.substring(actualData.indexOf("</CaptureItem>") + "</CaptureItem>".length());
			expectedData =  expectedData.substring(expectedData.indexOf("</CaptureItem>") + "</CaptureItem>".length());
			String itemId = "";
			switch("ItemId")
			{
			case "ItemId":
				try{
					String actualItemId = tempICNActualData.substring(tempICNActualData.indexOf("<ItemId>"), tempICNActualData.indexOf("</ItemId>"));
					int actualItemIdIndex = actualItemId.indexOf(">");
					actualItemId = actualItemId.substring(++actualItemIdIndex);

					String expectedItemId = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<ItemId>"), tempICNExpectedData.indexOf("</ItemId>"));
					int expectedItemIdIndex = expectedItemId.indexOf(">");
					expectedItemId = expectedItemId.substring(++expectedItemIdIndex);

					boolean itemIdCheckFlag = actualItemId.equals(expectedItemId);
					publishResults(itemIdCheckFlag, "ItemId tag in ICN is " + actualItemId,
							"Should see ItemId tag in ICN content with value "+ expectedItemId, "Check the ItemId tag in ICN content");	
					if(itemIdCheckFlag)
					{
						itemId = expectedItemId;
					}else {
						validationErrorInformation("Validation Failed for ItemId tag in CaptureInfo of FRED file");
						Component.assertTrue(itemIdCheckFlag, "Validation Failed for ItemId tag in CaptureInfo of FRED file");
						msg05FM_06FM_Log.error("Validation Failed for ItemId tag in CaptureInfo of FRED file");
					}
				}catch(Exception e)
				{
					validationErrorInformation("Exception in ItemId Tag not available in CaptureInfo of loaded file 0f WorkFlow"+ workFlow);	
				}
			case "IsCorrected":
				try {
					String actualIsCorrected = tempICNActualData.substring(tempICNActualData.indexOf("<IsCorrected>"), tempICNActualData.indexOf("</IsCorrected>"));
					int actualIsCorrectedIndex = actualIsCorrected.indexOf(">");
					actualIsCorrected = actualIsCorrected.substring(++actualIsCorrectedIndex);

					String expectedIsCorrected = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<IsCorrected>"), tempICNExpectedData.indexOf("</IsCorrected>"));
					int expectedIsCorrectedIndex = expectedIsCorrected.indexOf(">");
					expectedIsCorrected = expectedIsCorrected.substring(++expectedIsCorrectedIndex);
					boolean isCorrectedCheckFlag = actualIsCorrected.equals(expectedIsCorrected);
					publishResults(isCorrectedCheckFlag, "IsCorrected tag in ICN is " + actualIsCorrected,
							"Should see IsCorrected tag in ICN content with value "+ expectedIsCorrected, "Check the IsCorrected tag in ICN content");	
					if(isCorrectedCheckFlag)
					{
						fredCaptureInfoDetails.put("IsCorrected", actualIsCorrected);
					}else {
						validationErrorInformation("Validation Failed for IsCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
						Component.assertTrue(isCorrectedCheckFlag, "Validation Failed for IsCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");	
						msg05FM_06FM_Log.error("Validation Failed for IsCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
					}
				}catch(Exception e)
				{
					validationErrorInformation("Exception in IsCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file 0f WorkFlow"+ workFlow);	
					msg05FM_06FM_Log.error("Exception in IsCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file 0f WorkFlow"+ workFlow);
					Component.assertTrue(false, "Exception in IsCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file 0f WorkFlow"+ workFlow);	
				}
			case "IsTCCorrected":
				try{
					String actualIsTCCorrected = tempICNActualData.substring(tempICNActualData.indexOf("<IsTCCorrected>"), tempICNActualData.indexOf("</IsTCCorrected>"));
					int actualIsTCCorrectedIndex = actualIsTCCorrected.indexOf(">");
					actualIsTCCorrected = actualIsTCCorrected.substring(++actualIsTCCorrectedIndex);

					String expectedIsTCCorrected = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<IsTCCorrected>"), tempICNExpectedData.indexOf("</IsTCCorrected>"));
					int expectedIsTCCorrectedIndex = expectedIsTCCorrected.indexOf(">");
					expectedIsTCCorrected = expectedIsTCCorrected.substring(++expectedIsTCCorrectedIndex);
					boolean isTCCorrectedCheckFlag = actualIsTCCorrected.equals(expectedIsTCCorrected);
					publishResults(isTCCorrectedCheckFlag, "IsTCCorrected tag in ICN is " + actualIsTCCorrected,
							"Should see IsTCCorrected tag in ICN content with value "+ expectedIsTCCorrected, "Check the IsTCCorrected tag in ICN content");	
					if(isTCCorrectedCheckFlag)
					{
						fredCaptureInfoDetails.put("IsTCCorrected", actualIsTCCorrected);
					}else {
						validationErrorInformation("Validation Failed for IsTCCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
						msg05FM_06FM_Log.error("Validation Failed for IsTCCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
						Component.assertTrue(isTCCorrectedCheckFlag, "Validation Failed for IsTCCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");	
					}
				}catch(Exception e)
				{
					validationErrorInformation("Exception in IsTCCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);	
					msg05FM_06FM_Log.error("Exception in IsTCCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);
					Component.assertTrue(false, "Exception in IsTCCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);	
				}		
			case "IsANCorrected":
				try{
					String actualIsANCorrected = tempICNActualData.substring(tempICNActualData.indexOf("<IsANCorrected>"), tempICNActualData.indexOf("</IsANCorrected>"));
					int actualIsANCorrectedIndex = actualIsANCorrected.indexOf(">");
					actualIsANCorrected = actualIsANCorrected.substring(++actualIsANCorrectedIndex);

					String expectedIsANCorrected = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<IsANCorrected>"), tempICNExpectedData.indexOf("</IsANCorrected>"));
					int expectedIsANCorrectedIndex = expectedIsANCorrected.indexOf(">");
					expectedIsANCorrected = expectedIsANCorrected.substring(++expectedIsANCorrectedIndex);
					boolean isANCorrectedCheckFlag = actualIsANCorrected.equals(expectedIsANCorrected);
					publishResults(isANCorrectedCheckFlag, "IsANCorrected tag in ICN is " + actualIsANCorrected,
							"Should see IsANCorrected tag in ICN content with value "+ expectedIsANCorrected, "Check the IsANCorrected tag in ICN content");	
					if(isANCorrectedCheckFlag)
					{
						fredCaptureInfoDetails.put("IsANCorrected", actualIsANCorrected);
					}else {
						validationErrorInformation("Validation Failed for IsANCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
						msg05FM_06FM_Log.error("Validation Failed for IsANCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
						Component.assertTrue(isANCorrectedCheckFlag, "Validation Failed for IsANCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
					}	
				}catch(Exception e)
				{
					validationErrorInformation("Exception in IsANCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);	
					msg05FM_06FM_Log.error("Exception in IsANCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);
					Component.assertTrue(false, "Exception in IsANCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);	
				}					
			case "IsSortCodeCorrected":
				try{
					String actualIsSortCodeCorrected = tempICNActualData.substring(tempICNActualData.indexOf("<IsSortCodeCorrected>"), tempICNActualData.indexOf("</IsSortCodeCorrected>"));
					int actualIsSortCodeCorrectedIndex = actualIsSortCodeCorrected.indexOf(">");
					actualIsSortCodeCorrected = actualIsSortCodeCorrected.substring(++actualIsSortCodeCorrectedIndex);

					String expectedIsSortCodeCorrected = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<IsSortCodeCorrected>"), tempICNExpectedData.indexOf("</IsSortCodeCorrected>"));
					int expectedIsSortCodeCorrectedIndex = expectedIsSortCodeCorrected.indexOf(">");
					expectedIsSortCodeCorrected = expectedIsSortCodeCorrected.substring(++expectedIsSortCodeCorrectedIndex);
					boolean isSortCodeCorrectedCheckFlag = actualIsSortCodeCorrected.equals(expectedIsSortCodeCorrected);
					publishResults(isSortCodeCorrectedCheckFlag, "IsSortCodeCorrected tag in ICN is " + actualIsSortCodeCorrected,
							"Should see IsSortCodeCorrected tag in ICN content with value "+ expectedIsSortCodeCorrected, "Check the IsSortCodeCorrected tag in ICN content");	
					if(isSortCodeCorrectedCheckFlag)
					{
						fredCaptureInfoDetails.put("IsSortCodeCorrected", actualIsSortCodeCorrected);
					}else {
						validationErrorInformation("Validation Failed for IsSortCodeCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
						msg05FM_06FM_Log.error("Validation Failed for IsSortCodeCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
						Component.assertTrue(isSortCodeCorrectedCheckFlag, "Validation Failed for IsSortCodeCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
					}
				}catch(Exception e)
				{
					validationErrorInformation("Exception in IsSortCodeCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);	
					msg05FM_06FM_Log.error("Exception in IsSortCodeCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);
					Component.assertTrue(false, "Exception in IsSortCodeCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);	
				}	
			case "IsSerialCorrected":
				try{
					String actualIsSerialCorrected = tempICNActualData.substring(tempICNActualData.indexOf("<IsSerialCorrected>"), tempICNActualData.indexOf("</IsSerialCorrected>"));
					int actualIsSerialCorrectedIndex = actualIsSerialCorrected.indexOf(">");
					actualIsSerialCorrected = actualIsSerialCorrected.substring(++actualIsSerialCorrectedIndex);

					String expectedIsSerialCorrected = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<IsSerialCorrected>"), tempICNExpectedData.indexOf("</IsSerialCorrected>"));
					int expectedIsSerialCorrectedIndex = expectedIsSerialCorrected.indexOf(">");
					expectedIsSerialCorrected = expectedIsSerialCorrected.substring(++expectedIsSerialCorrectedIndex);
					boolean isSerialCorrectedCheckFlag = actualIsSerialCorrected.equals(expectedIsSerialCorrected);
					publishResults(isSerialCorrectedCheckFlag, "IsSerialCorrected tag in ICN is " + actualIsSerialCorrected,
							"Should see IsSerialCorrected tag in ICN content with value "+ expectedIsSerialCorrected, "Check the IsSerialCorrected tag in ICN content");	
					if(isSerialCorrectedCheckFlag)
					{
						fredCaptureInfoDetails.put("IsSerialCorrected", actualIsSerialCorrected);
					}else {
						validationErrorInformation("Validation Failed for IsSerialCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
						msg05FM_06FM_Log.error("Validation Failed for IsSerialCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
						Component.assertTrue(isSerialCorrectedCheckFlag, "Validation Failed for IsSerialCorrected tag in CaptureInfo for EntityId " +itemId + " of FRED file");
					}	
				}catch(Exception e)
				{
					validationErrorInformation("Exception in IsSerialCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);	
					msg05FM_06FM_Log.error("Exception in IsSerialCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);
					Component.assertTrue(false, "Exception in IsSerialCorrected Tag not available for EntityId " +itemId + " in CaptureInfo of loaded file of WorkFlow"+ workFlow);	
				}					
			}
			itemAndFREDCaptureInfoDetails.put(itemId, fredCaptureInfoDetails);
			fredCaptureInfoDetails = new LinkedHashMap<String, String>();	
		}while(expectedData.indexOf("<CaptureItem>") > 0);	
	}

	/*
	 * 3. Get Code-line Changes Flag based on IsCorrected tag information.
	 */
	public static void getCodeLineSectionFlagStatusOfFredFile(List<String> itemIds)
	{
		for(String eachItemId : itemIds)
		{
			String isCorrectedFlag = itemAndFREDCaptureInfoDetails.get(eachItemId).get("IsCorrected");
			String isTCCorrected = itemAndFREDCaptureInfoDetails.get(eachItemId).get("IsTCCorrected");
			String isSortCodeCorrected = itemAndFREDCaptureInfoDetails.get(eachItemId).get("IsSortCodeCorrected");
			String isSerialCorrected = itemAndFREDCaptureInfoDetails.get(eachItemId).get("IsSerialCorrected");
			String isANCorrected = itemAndFREDCaptureInfoDetails.get(eachItemId).get("IsANCorrected");
			if(isCorrectedFlag.equals("true")||isCorrectedFlag.equals("1"))
			{
				msg05FM_06FM_Log.info("IsCorrected flag is true for ItemId "+ eachItemId);
				codeLineCorrectedChangeFlag.put(eachItemId, true);	
				if(isTCCorrected.equals("true")|| isTCCorrected.equals("1"))
				{
					msg05FM_06FM_Log.info("IsTCCorrected flag is true for ItemId "+ eachItemId);
					codeLineTxCodeChangeFlag.put(eachItemId, true);
				}else  if(isTCCorrected.equals("false")|| isTCCorrected.equals("0")){
					msg05FM_06FM_Log.info("IsTCCorrected flag is false for ItemId "+ eachItemId);
					codeLineTxCodeChangeFlag.put(eachItemId, false);
				}
				if(isSortCodeCorrected.equals("true")|| isSortCodeCorrected.equals("1"))
				{
					msg05FM_06FM_Log.info("IsSortCodeCorrected flag is true for ItemId "+ eachItemId);
					codeLineSortCodeChangeFlag.put(eachItemId, true);	
				}else  if(isSortCodeCorrected.equals("false")|| isSortCodeCorrected.equals("0")){
					msg05FM_06FM_Log.info("IsSortCodeCorrected flag is false for ItemId "+ eachItemId);
					codeLineSortCodeChangeFlag.put(eachItemId, false);	
				}
				if(isSerialCorrected.equals("true")|| isSerialCorrected.equals("1"))
				{
					msg05FM_06FM_Log.info("IsSerialCorrected flag is true for ItemId "+ eachItemId);
					codeLineSerialChangeFlag.put(eachItemId, true);	
				}else  if(isSerialCorrected.equals("false")|| isSerialCorrected.equals("0")){
					msg05FM_06FM_Log.info("IsSerialCorrected flag is false for ItemId "+ eachItemId);
					codeLineSerialChangeFlag.put(eachItemId, false);	
				}
				if(isANCorrected.equals("true")|| isANCorrected.equals("1"))
				{
					msg05FM_06FM_Log.info("IsANCorrected flag is true for ItemId "+ eachItemId);
					codeLineAccountNoChangeFlag.put(eachItemId, true);
				}else if(isANCorrected.equals("false")|| isANCorrected.equals("0")){
					msg05FM_06FM_Log.info("IsANCorrected flag is false for ItemId "+ eachItemId);
					codeLineAccountNoChangeFlag.put(eachItemId, false);
				}
			}else if(isCorrectedFlag.equals("false")||isCorrectedFlag.equals("0")){
				msg05FM_06FM_Log.info("IsCorrected flag is false for ItemId "+ eachItemId);
				codeLineCorrectedChangeFlag.put(eachItemId, false);
				codeLineTxCodeChangeFlag.put(eachItemId, false);
				codeLineSortCodeChangeFlag.put(eachItemId, false);
				codeLineSerialChangeFlag.put(eachItemId, false);
				codeLineAccountNoChangeFlag.put(eachItemId, false);
			}				
		}
	}	

	/*
	 * 3.1 Validate Updated Code line Changes from FRED file loaded based on - IsCorrected tag information
	 */
	public static void validateCodeLineBasedOnIsCorrectedTagForDebits(List<String> itemIds, String workFlow)
	{
		String item = "";
		fredCodelineChangeDetails = new LinkedHashMap<String, String>();	
		HashMap<String, HashMap<String, String>>  newMapofItemIdAndItsBankDetails  = new LinkedHashMap<String, HashMap<String, String>>();
		if(workFlow.equals("06FM01"))
		{
			newMapofItemIdAndItsBankDetails = Msg06.mapofItemIdAndItsBankDetails;
		}else  if(workFlow.equals("05FM01")){
			newMapofItemIdAndItsBankDetails = Msg05.mapofItemIdAndItsBankDetails;
		}
		for(String eachItemId : itemIds)
		{
			HashMap<String, String> fredSCDetails = itemAndFREDCodeLineDetails.get(eachItemId);
			String fredSerialNo = fredSCDetails.get(serialNo);
			String fredSortCode = fredSCDetails.get(sortCode);
			String fredAccountNo = fredSCDetails.get(accountNo);
			String fredTransactionCode = fredSCDetails.get(transactionCode);
			String fredAmount = fredSCDetails.get(amount);
			boolean codeLineIsCorrectedFlag = codeLineCorrectedChangeFlag.get(eachItemId);
			boolean codeLineIsTCCorrectedFlag = codeLineTxCodeChangeFlag.get(eachItemId);
			boolean codeLineIsSortCodeCorrectedFlag = codeLineSortCodeChangeFlag.get(eachItemId);
			boolean codeLineIsSerialCorrectedFlag = codeLineSerialChangeFlag.get(eachItemId);
			boolean codeLineIsANCorrectedFlag = codeLineAccountNoChangeFlag.get(eachItemId);
			if(codeLineIsCorrectedFlag)
			{
				item = eachItemId;
				for(Entry<String, HashMap<String, String>> eachDebitItemDetails : newMapofItemIdAndItsBankDetails.entrySet())
				{
					String itemId  = eachDebitItemDetails.getKey();
					if(itemId.equals(eachItemId))
					{
						HashMap<String, String> switchFileDetails = eachDebitItemDetails.getValue();

						String switchAmountDetails = switchFileDetails.get(amount);
						if((switchAmountDetails.equals(fredAmount)))
						{
							fredCodelineChangeDetails.put(amount, fredAmount);
							thresholdValueForItems.put(item, Float.parseFloat(fredAmount));
						}else {
							msg05FM_06FM_Log.error("FRED file Amount details not matches with switch file during Codeline Change condition for itemId "+ eachItemId );
							validationErrorInformation("FRED file Amount details not matches with switch file during Codeline Change condition for itemId "+ eachItemId);
							Component.assertEquals(fredAmount, switchAmountDetails, "FRED file Amount details not matches with switch file during Codeline Change condition for itemId "+ eachItemId );
						}

						String switchSortCode = switchFileDetails.get(sortCode);
						if(codeLineIsSortCodeCorrectedFlag)
						{
							if(!(switchSortCode.equals(fredSortCode)))
							{
								fredCodelineChangeDetails.put(sortCode, fredSortCode);
							}else {
								msg05FM_06FM_Log.error("FRED file SortCode details not changed for itemId "+ eachItemId + " for IsSortCodeCorrectflag as true" );
								validationErrorInformation("FRED file SortCode details not changed for itemId "+ eachItemId + " for IsSortCodeCorrectflag as true");
								Component.assertEquals(fredSortCode, switchSortCode, "FRED file SortCode details not changed for itemId "+ eachItemId + " for IsSortCodeCorrectflag as true");
							}
						}else {
							fredCodelineChangeDetails.put(sortCode, switchSortCode);
						}

						String switchSerialCode = switchFileDetails.get(serialNo);
						if(codeLineIsSerialCorrectedFlag)
						{
							if(!(switchSerialCode.equals(fredSerialNo)))
							{
								fredCodelineChangeDetails.put(serialNo, fredSerialNo);
							}else {
								msg05FM_06FM_Log.error("FRED file SerialNo details not changed for itemId "+ eachItemId + " for IsSerialNoCorrectflag as true" );
								validationErrorInformation("FRED file SerialNo details not changed for itemId "+ eachItemId + " for IsSerialNoCorrectflag as true");
								Component.assertFalse(true, "FRED file SerialNo details not changed for itemId "+ eachItemId + " for IsSerialNoCorrectflag as true");
							}
						}else {
							fredCodelineChangeDetails.put(serialNo, switchSerialCode);
						}

						String switchAccountNo = switchFileDetails.get(accountNo);
						if(codeLineIsANCorrectedFlag)
						{
							if(!(switchAccountNo.equals(fredAccountNo)))
							{
								fredCodelineChangeDetails.put(accountNo, fredAccountNo);
							}else {
								msg05FM_06FM_Log.error("FRED file AccountNo details not changed for itemId "+ eachItemId + " for IsANCorrectedFlag as true" );
								validationErrorInformation("FRED file AccountNo details not changed for itemId "+ eachItemId + " for IsANCorrectedFlag as true" );
								Component.assertFalse(true, "FRED file AccountNo details not changed for itemId "+ eachItemId + " for IsANCorrectedFlag as true");
							}   
						}else {
							fredCodelineChangeDetails.put(accountNo, switchAccountNo);
						}

						String switchTransactionCode = switchFileDetails.get(transactionCode);
						try{
							if(codeLineIsTCCorrectedFlag)
							{
								if(!(switchTransactionCode.equals(fredTransactionCode)))
								{
									fredCodelineChangeDetails.put(transactionCode, fredTransactionCode);
								}else {
									msg05FM_06FM_Log.error("FRED file TransCode details not changed for itemId "+ eachItemId + " for IsTCCorrected as true" );
									validationErrorInformation("FRED file TransCode details not changed for itemId "+ eachItemId + " for IsTCCorrected as true" );
									Component.assertFalse(true, "FRED file TransCode details not changed for itemId "+ eachItemId + " for IsTCCorrected as true");
								}   
							}else {
								fredCodelineChangeDetails.put(transactionCode, switchTransactionCode);
							}
						}catch(Exception e)
						{
							
						}
						
					}					
				}
			}else {
				boolean tempFlag = true;
				msg05FM_06FM_Log.info("IsCorrectedFlag is false and Codeline Change not required for itemId "+ eachItemId);
				for(Entry<String, HashMap<String, String>> eachDebitItemDetails : newMapofItemIdAndItsBankDetails.entrySet())
				{
					String itemId  = eachDebitItemDetails.getKey();
					if(itemId.equals(eachItemId))
					{
						HashMap<String, String> switchFileDetails = eachDebitItemDetails.getValue();
						String switchSerialNo = switchFileDetails.get(serialNo);
						if(!(switchSerialNo.equals(fredSerialNo)))
						{
							msg05FM_06FM_Log.error("FRED Codeline SerialNo tag not matches with Switch File details for itemId "+ eachItemId );
							validationErrorInformation("FRED Codeline SerialNo tag not matches with Switch File details for itemId "+ eachItemId );
							tempFlag = false;
						}

						String switchSortCode = switchFileDetails.get(sortCode);
						if(!(switchSortCode.equals(fredSortCode)))
						{
							msg05FM_06FM_Log.error("FRED Codeline SortCode tag not matches with Switch File details for itemId "+ eachItemId );
							validationErrorInformation("FRED Codeline SortCode tag not matches with Switch File details for itemId "+ eachItemId  );
							tempFlag = false;
						}

						String switchAccountNo = switchFileDetails.get(accountNo);
						if(!(switchAccountNo.equals(fredAccountNo)))
						{
							msg05FM_06FM_Log.error("FRED Codeline AccountNo tag not matches with Switch File details for itemId "+ eachItemId );
							validationErrorInformation("FRED Codeline AccountNo tag not matches with Switch File details for itemId "+ eachItemId  );
							tempFlag = false;
						}

						String switchAmount = switchFileDetails.get(amount);
						thresholdValueForItems.put(itemId, Float.parseFloat(switchAmount));
						if(!(switchAmount.equals(fredAmount)))
						{
							msg05FM_06FM_Log.error("FRED Codeline Amount tag not matches with Switch File details for itemId "+ eachItemId );
							validationErrorInformation("FRED Codeline AccountNo tag not matches with Switch File details for itemId "+ eachItemId );
							tempFlag = false;
						}
					}
				}
				if(!tempFlag)
				{
					Component.assertTrue(tempFlag, "FRED Codeline details not matches with Switch File details");
				}
			}
		}
		mapOfItemIdsWithChangedCodeline.put(item, fredCodelineChangeDetails);
	}

	/*
	 * 3.2 Check 13DM01 Insert in Receive Staging Table
	 */
	public static void checkICNFile13DM01InsertInReceiveStaging(String inputFilePath, String inputFileName, String outputFilePath, String workFlow, List<String> itemIds) throws Exception
	{
		String query = "SELECT Msg = CAST(Msg AS XML), Msg_Id FROM " + getMoRepositoryTable() + ".[Staging].[ReceiveStaging] "
				+ "where MessageType='" + workFlow + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		actualICNContentMsg13DM01InReceiveStaging = "";
		actualRowIDMsg13DM01 = "";
		while(resultSet.next())
		{
			actualICNContentMsg13DM01InReceiveStaging = resultSet.getString("Msg");
			if(workFlow.equals("06FM01"))
			{
				if(actualICNContentMsg13DM01InReceiveStaging.indexOf(expectedDebitItemId.get(0)) > 0)
				{
					actualRowIDMsg13DM01 = resultSet.getString(columnMsgId);
				}
			}else if(workFlow.equals("05FM01"))
			{
				if(actualICNContentMsg13DM01InReceiveStaging.indexOf(expectedCreditItemId.get(0)) > 0)
				{
					actualRowIDMsg13DM01 = resultSet.getString(columnMsgId);
				}
			}

		}
		if(actualRowIDMsg13DM01.equals(""))
		{
			msg05FM_06FM_Log.error("WorkFlow "+workFlow + " not loaded in ReceiveStaging table " );
			publishResults(false, "WorkFlow "+ workFlow + " ICN content not loaded in ReceiveStaging table " ,
					"Should see workFlow "+ workFlow + " ICN content to load in ReceiveStaging table",
					"Check workFlow "+ workFlow + " in ReceiveStaging table");	
		}else {
			checkICNFile06FM01InsertedDataInReceiveStaging(inputFilePath, inputFileName, outputFilePath, workFlow, itemIds);
			msg05FM_06FM_Log.info("WorkFlow "+workFlow + " loaded in ReceiveStaging table with RowId/Msg_Id "+ actualRowIDMsg13DM01 );
			publishResults(true, "WorkFlow "+ workFlow + " ICN content loaded in ReceiveStaging table with RowId/Msg_Id "+ actualRowIDMsg13DM01 ,
					"Should see workFlow "+ workFlow + " ICN content to load in ReceiveStaging table",
					"Check workFlow "+ workFlow + " in ReceiveStaging table");	
		}
	}

	public static String getSourceID()
	{
		return actualSourceIDMsg06FM01;
	}

	public static String getReceiveStagingContent()
	{
		return actualICNContentMsgFM01InReceiveStaging;
	}
}
