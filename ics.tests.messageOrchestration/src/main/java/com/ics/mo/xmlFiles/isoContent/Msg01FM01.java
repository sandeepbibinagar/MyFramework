package com.ics.mo.xmlFiles.isoContent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.ics.mo.common.MoGenericMethods;

public class Msg01FM01 extends MoGenericMethods
{
	protected static String actual01FM01ISOContent, actual01FM01ICNContent;
	protected static String actualSourceIDOfMsg01;
	protected static ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> txSetAndItsCdtItmIdDetails;
	protected static ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> txSetAndItsDbtItmIdDetails;
	protected static HashMap<String,String> itemAndIts01FM01State = new LinkedHashMap<String,String>();
	protected static HashMap<String,String> tsetAndIts01FM01State = new LinkedHashMap<String,String>();
	protected static ArrayList<String> tsetInvalid = new ArrayList<String>();
	protected static ArrayList<String> tsetValid = new ArrayList<String>();
	protected static ArrayList<String> itemInvalid = new ArrayList<String>();
	protected static ArrayList<String> itemValid = new ArrayList<String>();
	protected static final Logger msg01FM01Log = Logger.getLogger(Msg01FM01.class.getSimpleName()) ;

	public static void check01FM01FileInsertInReceiveStaging(String inputFilePath, String inputFileName, String outputFilePath,
			String workFlow, List<String> itemIds) throws Exception	
	{
		checkSourceIdInsertInReceiveStagingTable(workFlow);
		if(actualRowIDMsgFM01.equals(""))
		{
			msg01FM01Log.error("WorkFlow "+ workFlow + " not loaded in ReceiveStaging table ");
			publishResults(false, "WorkFlow "+ workFlow + " ICN content not loaded in ReceiveStaging table ",
					"Should see workFlow "+ workFlow + " ICN content to load in ReceiveStaging table",
					"Check workFlow "+ workFlow + " in ReceiveStaging table");	
		}else {
			msg01FM01Log.info("WorkFlow "+ workFlow + " loaded in ReceiveStaging table with RowId/Msg_Id "+ actualRowIDMsgFM01);
			publishResults(true, "WorkFlow "+ workFlow + " ICN content loaded in ReceiveStaging table with RowId/Msg_Id "+ actualRowIDMsgFM01,
					"Should see workFlow "+ workFlow + " ICN content to load in ReceiveStaging table",
					"Check workFlow "+ workFlow + " in ReceiveStaging table");	
		}			
	}

	public static void check01FM01ContentInserted(String workFlow) throws Exception
	{
		msg01FM01Log.info("Validating FRED content insert in Source Table for workflow "+  workFlow);
		String query = "Select ISOContent = CAST(ISOContent AS XML), ICNContent = CAST(ICNContent AS XML), SourceID, SourceSKID from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "where  MessageType = '" + workFlow + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			actual01FM01ISOContent = "";
			actualSourceIDOfMsg01 = "";
			actual01FM01ISOContent = resultSet.getString(columnISOContent);
			actual01FM01ICNContent = resultSet.getString(columnICNContent);
			boolean tempFlag = false;
			for(String eachItemId : expectedCreditItemId)
			{
				if((actual01FM01ISOContent.indexOf(eachItemId) > 0) &&
						(actual01FM01ISOContent.indexOf(expectedSourceId.get(0)) > 0))
				{
					tempFlag = true;

				}else {
					tempFlag = false;
				}
			}
			if(tempFlag)
			{
				actualSourceIDOfMsg01 = resultSet.getString(columnSourceID);
				String str01FM01ISOData = FileUtils.readFileToString(new File(actual01FM01ISOContent));
				String str01FM01ICNData = FileUtils.readFileToString(new File(actual01FM01ICNContent));
				txSetAndItsCdtItmIdDetails = Msg05.getTxSetAndItsCdtItmIdDetails(str01FM01ISOData);
				txSetAndItsDbtItmIdDetails = Msg06.getTxSetAndItsDbtItmIdDetails(str01FM01ISOData);
				get01FM01ICNcontentInserted(str01FM01ICNData);
			}
		}
	}	

	public static void get01FM01ICNcontentInserted(String str01FM01ICNData)
	{
		int itemIdOccurenceCount = getStringOccurence(str01FM01ICNData, "<EntityType>I</EntityType>");
		int tsetOccurenceCount = getStringOccurence(str01FM01ICNData, "<EntityType>T</EntityType>");
		String tempEntityWithIdata = str01FM01ICNData;
		String tempEntityWithEdata = str01FM01ICNData;
		while(itemIdOccurenceCount>0)
		{
			itemIdOccurenceCount--;
			tempEntityWithIdata = tempEntityWithIdata.substring(tempEntityWithIdata.indexOf("<EntityType>I</EntityType>") 
					+ "<EntityType>I</EntityType>".length());
			String itemId = tempEntityWithIdata.substring(tempEntityWithIdata.indexOf("<EntityId>")+"<EntityId>".length(), tempEntityWithIdata.indexOf("</EntityId>"));
			String entityState = tempEntityWithIdata.substring(tempEntityWithIdata.indexOf("<EntityState>")+"<EntityState>".length(), tempEntityWithIdata.indexOf("</EntityState>"));
			itemAndIts01FM01State.put(itemId, entityState);
		}				
		while(tsetOccurenceCount>0)
		{
			tsetOccurenceCount--;
			tempEntityWithEdata = tempEntityWithEdata.substring(tempEntityWithEdata.indexOf("<EntityType>T</EntityType>")
					+ "<EntityType>T</EntityType>".length());
			String tsetId = tempEntityWithEdata.substring(tempEntityWithEdata.indexOf("<EntityId>")+"<EntityId>".length(), tempEntityWithEdata.indexOf("</EntityId>"));
			String entityState = tempEntityWithEdata.substring(tempEntityWithEdata.indexOf("<EntityState>")+"<EntityState>".length(), tempEntityWithEdata.indexOf("</EntityState>"));
			tsetAndIts01FM01State.put(tsetId, entityState);
		}
	}
	
	public static void checkTsetStates(String workFlow) throws Exception
	{
		msg01FM01Log.info("Validating TsetIds EntityStates");
		String query  = new String();
		for(Entry<String, String> eachTsetDetails : tsetAndIts01FM01State.entrySet())
		{
			String tsetId = eachTsetDetails.getKey();
			String entityState = eachTsetDetails.getValue();
			if(entityState.equals("20"))
			{				
				tsetValid.add(tsetId);
			}else if(entityState.equals("21"))
			{
				msg01FM01Log.info("TsetId : tsetId  EntityState of input file is "+ entityState+ " - Invalid Tset");
				tsetInvalid.add(tsetId);
			}		
			query = "Select CollectorTsetState from " + getMoRepositoryTable() +  ".[Base].[TransactionSetState] where TsetID like '" + tsetId +"%'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				String collectorTsetState = resultSet.getString("CollectorTsetState");		
				if(collectorTsetState.equals(entityState))
				{
					publishResults(true, collectorTsetState, entityState, "Check CollectorTsetState for TsetId " + tsetId + " with workflow "+ workFlow);
				}else {
					publishResults(false, collectorTsetState, entityState, "Check CollectorTsetState for TsetId " + tsetId + " with workflow "+ workFlow);
				}				
			}
		}	
		msg01FM01Log.info("Valid TsetIds : "+ tsetValid);
		msg01FM01Log.info("Invalid TsetIds : "+ tsetInvalid);
	}
	
	public static void checkItemIdStates(String workFlow) throws Exception
	{
		msg01FM01Log.info("Validating ItemIds EntityStates");
		String query = new String();
		for(Entry<String, String> eachItemIdDetails : itemAndIts01FM01State.entrySet())
		{
			String itemId = eachItemIdDetails.getKey();
			String entityState = eachItemIdDetails.getValue();
			if(entityState.equals("20"))
			{
				itemInvalid.add(itemId);
			}else if(entityState.equals("21"))
			{
			    itemValid.add(itemId);
			}	
			query = "Select CollectorItemState from " + getMoRepositoryTable() +  ".[Base].[ItemState] where ItemID = '" + itemId +"'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				String collectorItemState = resultSet.getString("CollectorItemState");
				if(collectorItemState.equals(entityState))
				{
					publishResults(true, collectorItemState, entityState, "Check CollectorItemState for ItemID " + itemId + " with workflow "+ workFlow);
					msg01FM01Log.info("CollectorItemState is "+ collectorItemState + " matched with expected for workflow "+ workFlow);
				}else {
					publishResults(false, collectorItemState, entityState, "Check CollectorItemState for TsetId " + itemId + " with workflow "+ workFlow);
					msg01FM01Log.error("CollectorItemState is "+ collectorItemState + " not matched with expected for workflow "+ workFlow);
				}
			}
		}
		msg01FM01Log.info("Valid ItemIds : "+ itemValid);
		msg01FM01Log.info("Invalid ItemIds : "+ itemInvalid);
	}
	
	public static boolean checkValidTsetAvailable()
	{
		boolean flag = false;
		if(tsetValid.size() > 0)
		{
			flag = true;
		}else {
			flag = false;
		}
		msg01FM01Log.info("Valid TsetIds are " + tsetValid);
		return flag;

	}
	
	public static boolean checkInvalidTsetAvailable()
	{
		boolean flag = false;
		if(tsetInvalid.size() > 0)
		{
			flag = true;
		}else {
			flag = false;
		}
		msg01FM01Log.info("Invalid TsetIds are " + tsetInvalid);
		return flag;
	}
}
