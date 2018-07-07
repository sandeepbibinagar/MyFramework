package com.ics.mo.xmlFiles.isoContent2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import com.ics.mo.common.MoGenericMethods2;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * <h1>Msg01FM01</h1>
 * This class file contains the methods related to Msg01FM01
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-04-15
 */
public class Msg01FM01 extends MoGenericMethods2
{
	public Msg01FM01(ExtentReports extent, ExtentTest extentLog) 
	{
		super(extent,extentLog );
		// TODO Auto-generated constructor stub
	}	
	protected String actual01FM01ISOContent, actual01FM01ICNContent;
	protected ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> txSetAndItsCdtItmIdDetails;
	protected ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> txSetAndItsDbtItmIdDetails;
	protected HashMap<String,String> itemAndIts01FM01State = new LinkedHashMap<String,String>();
	protected HashMap<String,String> tsetAndIts01FM01State = new LinkedHashMap<String,String>();
	protected HashMap<String,String> tsetInvalid = new LinkedHashMap<String,String>();
	protected HashMap<String,String> tsetValid = new LinkedHashMap<String,String>();
	protected HashMap<String,String> itemInvalid = new LinkedHashMap<String,String>();
	protected HashMap<String,String> itemValid = new LinkedHashMap<String,String>();
	protected HashMap<String,String> allValidIdsAndItsState = new LinkedHashMap<String,String>();
	protected HashMap<String,String> allInValidIdsAndItsState = new LinkedHashMap<String,String>();
	protected HashMap<String, String> creditItemAndItsTset = new LinkedHashMap<String,String>();
	protected HashMap<String, String> debitItemAndItsTset = new LinkedHashMap<String,String>();
	protected List<String> allValidItems = new ArrayList<String>();
	protected List<String> allInvalidItems = new ArrayList<String>();
	protected final Logger msg01FM01Log = Logger.getLogger(Msg01FM01.class.getSimpleName()) ;

	/**
	 * This method is to check the 01FM01 file insert in Receive Staging table
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */	
	public Msg01FM01 check01FM01FileInsertInReceiveStaging(String workFlow, ArrayList<String> extractId) throws Exception	
	{
		checkMessageFlowInsertInReceiveStaging(workFlow, extractId);
		if(rowId.equals(""))
		{
			msg01FM01Log.error(String.format(receiveStagingTableLogError, workFlow));
			validationErrorInformation(String.format(receiveStagingTableLogError, workFlow));
		}else {			
			msg01FM01Log.info(String.format(receiveStagingTableLogActual, workFlow)+ rowId);
			validationStepInformation(String.format(receiveStagingTableLogActual, workFlow)+ rowId);
		}	
		return this;
	}

	/**
	 * This method is to check the 01FM01 content insert in Source Table
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */
	public Msg01FM01 check01FM01ContentInSourceTable(String workFlow, List<String> tsetIds, String sourceId) throws Exception
	{
		String query = "Select " + isoContentXMLColumn + "," + icnContentXMLColumn + "," + sourceIDColumn + "," + sourceSKIDColumn + "," + sourceStateRevisionColumn + " From " 
				+ repositorySchema + sourceTable + " Where " + sourceIDColumn + "='" + sourceId + "'" ;
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query);
		if(resultSet.next())
		{
			actual01FM01ISOContent = resultSet.getString(isoContent);
			actual01FM01ICNContent = resultSet.getString(icnContent);
			txSetAndItsCdtItmIdDetails = new Msg05(extent, extentLog).getTxSetAndItsCrdtItmIdDetails(actual01FM01ISOContent);
			txSetAndItsDbtItmIdDetails = new Msg06(extent, extentLog).getTxSetAndItsDbtItmIdDetails(actual01FM01ISOContent);
    		get01FM01ICNcontentInserted(actual01FM01ICNContent);
		}		
		getValidAndInvalidTsets(workFlow);
		getValidAndInvalidItems(workFlow);
		return this;
	}	

	/**
	 * This method is to Get the List of Items and TsetIds of 01FM01
	 * @param str01FM01ICNData
	 * @return
	 */
	public Msg01FM01 get01FM01ICNcontentInserted(String str01FM01ICNData)
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
		msg01FM01Log.info("TsetIDAndItsStates : " + tsetAndIts01FM01State);
		validationStepInformation("TsetIDAndItsStates : " + tsetAndIts01FM01State);
		msg01FM01Log.info("ItemAndIts01FM01State : " + itemAndIts01FM01State);
		validationStepInformation("ItemAndIts01FM01State : " + itemAndIts01FM01State);
		return this;
	}
	
	/**
	 * This method is to get the Valid And InValid TsetIds from 01FM01
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */
	public Msg01FM01 getValidAndInvalidTsets(String workFlow) throws Exception
	{
		for(Entry<String, String> eachTsetDetails : tsetAndIts01FM01State.entrySet())
		{
			String tsetId = eachTsetDetails.getKey();
			String entityState = eachTsetDetails.getValue();
			if(entityState.equals(validClearing))
			{				
				tsetValid.put(tsetId, validClearing);
				allValidIdsAndItsState.put(tsetId, entityState);
			}else if(entityState.equals(invalidClearing))
			{
				tsetInvalid.put(tsetId, invalidClearing);
				allInValidIdsAndItsState.put(tsetId, entityState);
			}		
		}	
		msg01FM01Log.info(validTsetsLog + tsetValid);
		msg01FM01Log.info(inValidTsetsLog + tsetInvalid);
		return this;
	}
	
	/**
	 * This method is to get the Valid and Invalid Items from 01FM01
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */
	public Msg01FM01 getValidAndInvalidItems(String workFlow) throws Exception
	{
		for(Entry<String, String> eachItemIdDetails : itemAndIts01FM01State.entrySet())
		{
			String itemId = eachItemIdDetails.getKey();
			String entityState = eachItemIdDetails.getValue();
			if(entityState.equals(validClearing))
			{
				itemValid.put(itemId, validClearing);
				allInValidIdsAndItsState.put(itemId, entityState);
				allInvalidItems.add(itemId);
			}else if(entityState.equals(invalidClearing))
			{
				itemInvalid.put(itemId, invalidClearing);
			  allValidIdsAndItsState.put(itemId, entityState);
			  allValidItems.add(itemId);
			}				
		}
		msg01FM01Log.info(validItemsLog + itemValid);
		msg01FM01Log.info(inValidItemsLog + itemInvalid);
		return this;
	}
	
	/**
	 * This method is to get flag update of Valid TsetIds available or not
	 * @return
	 */
	public boolean checkValidTsetAvailable()
	{
		boolean flag = false;
		if(tsetValid.size() > 0)
		{
			flag = true;
		}else {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * This method is to get flag update of InValid TsetIds available or not
	 * @return
	 */
	public boolean checkInvalidTsetAvailable()
	{
		boolean flag = false;
		if(tsetInvalid.size() > 0)
		{
			flag = true;
		}else {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * This method is to get Map of TsetIds and Its states
	 * @return
	 */
	public HashMap<String, String> getTsetIdAndItsStates()
	{
		return tsetAndIts01FM01State;
	}
	
	/**
	 * This method is to get Map of ItemIds and Its States
	 * @return
	 */
	public HashMap<String, String> getItemIdAndItsStates()
	{
		return itemAndIts01FM01State;
	}
	
	/**
	 * This method is to get Map Of Valid Items and TSets
	 * @return
	 */
	public HashMap<String,String> getAllValidIdsAndItsStates()
	{
		return allValidIdsAndItsState;
	}
	
	/**
	 * This method is to get Map of Invalid Items and TSets
	 * @return
	 */
	public HashMap<String,String> getAllInvalidIdsAndItsStates()
	{
		return allInValidIdsAndItsState;
	}
	
	/**
	 * This method is to get Map of Valid ItemIds
	 * @return
	 */
	public HashMap<String,String> getAllValidItemIds()
	{
		return itemValid;
	}
	
	/**
	 * This method is to get Map of Invalid ItemIds
	 * @return
	 */
	public HashMap<String,String> getAllInvalidItemIds()
	{
		return itemInvalid;
	}
	
	/**
	 * This method is to get Map Of Valid TsetIds
	 * @return
	 */
	public HashMap<String,String> getAllValidTsetIds()
	{
		return tsetValid;
	}
	
	/**
	 * This method is to get Map of Invalid TsetIds
	 * @return
	 */
	public HashMap<String,String> getAllInvalidTsetIds()
	{
		return tsetInvalid;
	}
	
	/**
	 * This method is to get List of Valid Items
	 * @return
	 */
	public List<String> getAllValidItems()
	{
		return allValidItems;
	}
	
	/**
	 * This method is to get List of Invalid Items
	 * @return
	 */
    public List<String> getAllInvalidItems()
    {
    	return allInvalidItems;
    }
    
    /**
     * This method is to Map CreditItem and its TSet
     */
    public HashMap<String,String> getMapOfCreditItemsAndItsTset()
    {
    	for(HashMap<String, HashMap<String, List<HashMap<String, String>>>> eachTxsetDetails : txSetAndItsCdtItmIdDetails)
    	{
    		for(Entry<String, HashMap<String, List<HashMap<String, String>>>> tsetAndItsCreditDetails : eachTxsetDetails.entrySet())
    		{
    			String tsetId = tsetAndItsCreditDetails.getKey();
    			for(Entry<String, List<HashMap<String, String>>> eachCreditItem : tsetAndItsCreditDetails.getValue().entrySet())
    			{
    				creditItemAndItsTset.put(eachCreditItem.getKey(), tsetId);
    			}
    		}
    	}
    	return creditItemAndItsTset;
    }
    
    /**
     * This method is to Map DebitItem And its TSet
     */
    public HashMap<String,String> getMapOfDebitItemsAndItsTset()
    {
    	for(HashMap<String, HashMap<String, List<HashMap<String, String>>>> eachTxsetDetails : txSetAndItsDbtItmIdDetails)
    	{
    		for(Entry<String, HashMap<String, List<HashMap<String, String>>>> tsetAndItsDebitDetails : eachTxsetDetails.entrySet())
    		{
    			String tsetId = tsetAndItsDebitDetails.getKey();
    			for(Entry<String, List<HashMap<String, String>>> eachDebitItem : tsetAndItsDebitDetails.getValue().entrySet())
    			{
    				debitItemAndItsTset.put(eachDebitItem.getKey(), tsetId);
    			}
    		}
    	}
    	return debitItemAndItsTset;
    }
}
