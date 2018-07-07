package com.ics.mo.xmlFiles.icnContent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import com.ics.mo.xmlFiles.isoContent.Msg01FM01;

public class Msg01DM extends Msg01FM01
{  
	protected static HashMap<String,String> itemAndIts01DM01State = new LinkedHashMap<String,String>();
	protected static HashMap<String,String> tsetAndIts01DM01State = new LinkedHashMap<String,String>();
	protected static ArrayList<String> tsetWithDrawn = new ArrayList<String>();
	protected static ArrayList<String> tsetAmended = new ArrayList<String>();
	protected static ArrayList<String> itemWithDrawn = new ArrayList<String>();
	protected static ArrayList<String> itemAmended = new ArrayList<String>();
	protected static final Logger msg01DM = Logger.getLogger(Msg01DM.class.getSimpleName()) ;

	public static void check01DM01ICNcontentInserted(String workFlow, String icnContent) throws Exception
	{
		int itemIdOccurenceCount = getStringOccurence(icnContent, "<EntityType>I</EntityType>");
		int tsetOccurenceCount = getStringOccurence(icnContent, "<EntityType>T</EntityType>");
		String tempEntityWithIdata = icnContent;
		String tempEntityWithEdata = icnContent;
		while(itemIdOccurenceCount>0)
		{
			itemIdOccurenceCount--;
			tempEntityWithIdata = tempEntityWithIdata.substring(tempEntityWithIdata.indexOf("<EntityType>I</EntityType>") 
					+ "<EntityType>I</EntityType>".length());
			String itemId = tempEntityWithIdata.substring(tempEntityWithIdata.indexOf("<EntityId>")+"<EntityId>".length(), tempEntityWithIdata.indexOf("</EntityId>"));
			String entityState = tempEntityWithIdata.substring(tempEntityWithIdata.indexOf("<EntityState>")+"<EntityState>".length(), tempEntityWithIdata.indexOf("</EntityState>"));
			itemAndIts01DM01State.put(itemId, entityState);
		}				
		while(tsetOccurenceCount>0)
		{
			tsetOccurenceCount--;
			tempEntityWithEdata = tempEntityWithEdata.substring(tempEntityWithEdata.indexOf("<EntityType>T</EntityType>")
					+ "<EntityType>T</EntityType>".length());
			String tsetId = tempEntityWithEdata.substring(tempEntityWithEdata.indexOf("<EntityId>")+"<EntityId>".length(), tempEntityWithEdata.indexOf("</EntityId>"));
			String entityState = tempEntityWithEdata.substring(tempEntityWithEdata.indexOf("<EntityState>")+"<EntityState>".length(), tempEntityWithEdata.indexOf("</EntityState>"));
			tsetAndIts01DM01State.put(tsetId, entityState);
		}
	}	

	public static void check01DM01EntityStatesWithT(String workFlow) throws Exception
	{
		tsetWithDrawn = new ArrayList<String>();
		tsetAmended = new ArrayList<String>();
		String query  = new String();
		if(tsetAndIts01DM01State.size()>0)
		{
			for(Entry<String, String> eachTsetDetails : tsetAndIts01DM01State.entrySet())
			{
				String tsetId = eachTsetDetails.getKey();
				String entityState = eachTsetDetails.getValue();
				if(entityState.equals("30"))
				{
					tsetWithDrawn.add(tsetId);
				}else if(entityState.equals("40"))
				{
					tsetAmended.add(tsetId);
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
		}		
		msg01DM.info("TsetIds Withdrawn states : "+ tsetWithDrawn + " for workflow "+ workFlow);
		msg01DM.info("TsetIds Amended states : "+ tsetAmended + " for workflow "+ workFlow);
	}
	
	public static void check01DM01EntityStatesWithI(String workFlow) throws Exception
	{
		itemWithDrawn = new ArrayList<String>();
		itemAmended = new ArrayList<String>();
		String query  = new String();
		if(itemAndIts01DM01State.size()>0)
		{
			for(Entry<String, String> eachItemIdDetails : itemAndIts01DM01State.entrySet())
			{
				String itemId = eachItemIdDetails.getKey();
				String entityState = eachItemIdDetails.getValue();
				if(entityState.equals("30"))
				{
					itemWithDrawn.add(itemId);
				}else if(entityState.equals("40"))
				{
					itemAmended.add(itemId);
				}
				query = "Select CollectorTsetState from " + getMoRepositoryTable() +  ".[Base].[ItemState] where ItemID = '" + itemId +"'";
				resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
				while(resultSet.next())
				{
					String collectorItemState = resultSet.getString("CollectorItemState");
					if(collectorItemState.equals(entityState))
					{
						publishResults(true, collectorItemState, entityState, "Check CollectorItemState for ItemID " + itemId + " with workflow "+ workFlow);
						msg01DM.info("CollectorItemState is "+ collectorItemState + " matched with expected for workflow "+ workFlow);
					}else {
						publishResults(false, collectorItemState, entityState, "Check CollectorItemState for TsetId " + itemId + " with workflow "+ workFlow);
						msg01DM.error("CollectorItemState is "+ collectorItemState + " not matched with expected for workflow "+ workFlow);
					}
				}
			}
		}
		msg01DM.info("ItemIds Withdrawn states : "+ itemWithDrawn + " for workflow "+ workFlow);
		msg01DM.info("ItemIds Amended states : "+ itemAmended + " for workflow "+ workFlow);
	}
}
