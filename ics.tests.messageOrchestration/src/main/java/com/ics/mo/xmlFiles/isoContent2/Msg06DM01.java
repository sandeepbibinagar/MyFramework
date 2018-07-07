package com.ics.mo.xmlFiles.isoContent2;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.ics.mo.common.MoGenericMethods2;
import com.ics.mo.xmlFiles.icnContent2.MsgFM01InclearingFlow;
import com.ics.seleniumCoreUtilis.Component;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * <h1>MsgDM01</h1>
 * This class file contains the methods related to 06DM01 DEW Response
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-04-15
 */
public class Msg06DM01 extends MoGenericMethods2
{

	public Msg06DM01(ExtentReports extent, ExtentTest extentLog) {
		super(extent, extentLog);
		// TODO Auto-generated constructor stub
	}
	
	private HashMap<String, String> dewResponse = new LinkedHashMap<String, String>();
	private HashMap<String, String> msg07MS01ItemsAndState = new LinkedHashMap<String, String>();
	private final Logger msg06DM01Log = Logger.getLogger(MsgFM01InclearingFlow.class.getSimpleName());
	
	/**
	 * This method is to check the 06DM01 file insert in Receive Staging table
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */	
	public Msg06DM01 getMsgIdOfDM01InReceiveStaging(String workFlow, ArrayList<String> extractId) throws Exception	
	{
		checkMessageFlowInsertInReceiveStaging(workFlow, extractId);
		if(rowId.equals(""))
		{
			msg06DM01Log.error(String.format(receiveStagingTableLogError, workFlow));
			validationErrorInformation(String.format(receiveStagingTableLogError, workFlow));
			Component.assertTrue(false, String.format(receiveStagingTableLogError, workFlow));
		}else {			
			msg06DM01Log.info(String.format(receiveStagingTableLogActual, workFlow)+ rowId);
			validationStepInformation(String.format(receiveStagingTableLogActual, workFlow)+ rowId);
		}
		return this;
	}
	
	/**
	 * This method is to check ICN File FRED Data Of InClearing flows Insert in Receive Staging
	 * @param inputFile
	 * @param workFlow
	 * @param itemIds
	 * @return
	 */
	public Msg06DM01 validateDM01ICNdataInReceiveStage(String inputFile, String workFlow) throws Exception
	{
		msg06DM01Log.info(String.format(icnDataInReceiveStageValidateLog, workFlow) + rowId);	
		String strExpectedICN06DM01Data = FileUtils.readFileToString(new File(inputFile));
		String strActualICN06DM01Data = actualContentInReceiveStaging;
		validationStepInformation(String.format(detailComparisonReceiveStageLog, workFlow, rowId));
		
		//Compare Entities Section
		validationStepInformation(checkOnEntitiesSectionLog);
		msg06DM01Log.info(checkOnEntitiesSectionLog);
		checkICNEntitiesSection(strActualICN06DM01Data, workFlow, strExpectedICN06DM01Data);
		msg06DM01Log.info("DEW response details are : "+ dewResponse);
		validationStepInformation("DEW response details are : "+ dewResponse);
		for(Entry<String, String> eachDewResponse : dewResponse.entrySet())	
		{
			String itemId = eachDewResponse.getKey();
			String itemState = eachDewResponse.getValue();
			if(!(itemState.equals("541")))
			{
				msg07MS01ItemsAndState.put(itemId, "570");
			}
		}
		msg06DM01Log.info("DEW items moved to Switch 07MS01 : " + msg07MS01ItemsAndState);
		validationStepInformation("DEW items moved to Switch 07MS01 : " + msg07MS01ItemsAndState);
	    return this;
	}
	
	/**
	 * This method is to check ICN file Entities section
	 * @param tempICNActualData
	 * @param workFlow
	 * @param tempICNExpectedData
	 * @return
	 */
	private Msg06DM01 checkICNEntitiesSection(String tempICNActualData, String workFlow, String tempICNExpectedData)
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
			String entityId = new String();
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
					msg06DM01Log.error("EntityId  "+ entityId + " details not loaded in ReceiveStaging table for workFlow "+ workFlow);
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
					dewResponse.put(entityId, expectedEntityState);
					
				}else {
					validationErrorInformation("Actual EntityState " + actualEntityState + " of EntityId  "+ entityId + " not matches with Expected EntityState " + expectedEntityState);
					msg06DM01Log.error("Actual EntityState " + actualEntityState + " of EntityId  "+ entityId + " not matches with Expected EntityState " + expectedEntityState);
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
	    return this;
	}
	
	/**
	 * This method is to get DEW Response details from FRED
	 * @return
	 */
	public HashMap<String,String> getDEWresponse()
	{
		return dewResponse;
	}
	
	/**
	 * This method is to get Items in 06DM01 response file
	 * @return
	 */
	public List<String> getItemsFromDEW()
	{
		List<String> dewItems = new ArrayList<String>();
		for(Entry<String, String> eachInitialState : dewResponse.entrySet())
		{
			dewItems.add(eachInitialState.getKey());
		}
		return dewItems;
	}
	
	/**
	 * This method is to get the RowId in ReceiveStaging table of DM01
	 * @return
	 */
	public String getRowIdOfDM01()
	{
		return rowId;
	}
	
	/**
	 * This method is to get Item And Its State details of 07MS01
	 * @return
	 */
	public HashMap<String, String> get07MS01ItemsAndItsState()
	{
		return msg07MS01ItemsAndState;
	}
	
	/**
	 * This method is to get Items of 07MS01
	 * @return
	 */
	public List<String> getItemsOf07MS01()
	{
		List<String> msg07MS01Items = new ArrayList<String>();
		for(Entry<String,String> each07MS01Item : msg07MS01ItemsAndState.entrySet())
		{
			msg07MS01Items.add(each07MS01Item.getKey());
		}
		return msg07MS01Items;
	}
}