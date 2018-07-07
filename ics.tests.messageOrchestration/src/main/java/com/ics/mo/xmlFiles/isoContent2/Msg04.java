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
 * <h1>Msg04</h1>
 * This class file contains the methods related to MSG04 switch File 
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-04-15
 */
public class Msg04 extends MoGenericMethods2 
{
	public Msg04(ExtentReports extent, ExtentTest extentLog) {
		super(extent, extentLog);
		// TODO Auto-generated constructor stub
	}

	protected List<String> tsetIds = new ArrayList<String>();
	protected HashMap<String,String> tsetIdAndOrphanState = new LinkedHashMap<String,String>();
	protected HashMap<String,String> msg04TsetIdCollectorMatchedAndState = new LinkedHashMap<String,String>();
	protected HashMap<String,String> msg05TsetIdBeneficiaryMatched = new LinkedHashMap<String,String>();
	protected List<String> tsetIdCollectorMatched = new ArrayList<String>();
	protected List<String> tsetIdBeneficiaryMatched = new ArrayList<String>();
	protected List<String> tsetIdBenInCollector = new ArrayList<String>();
	protected List<String> tsetIdBenNotInCollector = new ArrayList<String>();
	protected List<String> tsetIdCollectorNotInBen = new ArrayList<String>();
	protected final Logger msg04Log = Logger.getLogger(Msg04.class.getSimpleName());

	/**
	 * This method is to get TsetIds of MSG04 switch File
	 * @param sourceId
	 * @return
	 * @throws Exception
	 */
	public Msg04 getTsetIdsOfMsg04(String sourceId) throws Exception
	{
		String query = "Select " + isoContentXMLColumn + "," + sourceIDColumn + "," + sourceSKIDColumn + "," + sourceStateRevisionColumn + " From " 
				+ repositorySchema + sourceTable + " Where " + sourceIDColumn + "='" + sourceId + "'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query);
		if(resultSet.next())
		{
			String actualSwitchContent = resultSet.getString(isoContent);
			ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> txSetDetails = new Msg05(extent, extentLog).getTxSetAndItsCrdtItmIdDetails(actualSwitchContent);
			for(HashMap<String, HashMap<String, List<HashMap<String, String>>>> eachTsetDetails : txSetDetails)
			{
				for(Entry<String, HashMap<String, List<HashMap<String, String>>>> eachTset :eachTsetDetails.entrySet())
				{
					tsetIdAndOrphanState.put(eachTset.getKey(), amendedAfterClearing);
					tsetIds.add(eachTset.getKey());
				}
			}
		}	
		msg04Log.info("TsetIds of MSG04 : " + tsetIds);
		validationStepInformation("TsetIds of MSG04 : " + tsetIds);
		return this;
	}
	
	public List<String> getMSG04TsetIds()
	{
		return tsetIds;
	}

	/**
	 * This method is to get Expected Tset State after MSG04 loaded in ReceiveStagingTset
	 * @param msg01Tsets
	 * @param msg05Tsets
	 */
	public Msg04 getExpectedTsetStateOfReceiveStagingTset(List<String> msg01Tsets, List<String> msg05Tsets)
	{
		for(String eachMsg04TsetId : tsetIds)
		{
			for(String eachMsg01TsetId : msg01Tsets)
			{
				if(eachMsg01TsetId.equals(eachMsg04TsetId))
				{
					msg04TsetIdCollectorMatchedAndState.put(eachMsg01TsetId, validClearing);
					tsetIdAndOrphanState.remove(eachMsg04TsetId);
					tsetIdCollectorMatched.add(eachMsg04TsetId);
				}
			}			
		}	
		for(String eachMsg04TsetId : tsetIds)
		{
			for(String eachMsg05TsetId : msg05Tsets)	
			{
				if(eachMsg05TsetId.equals(eachMsg04TsetId))
				{
					boolean flag = false;	
					for(String eachCollectorTsetId : tsetIdCollectorMatched)
					{
						if(eachCollectorTsetId.equals(eachMsg05TsetId))
						{
							flag = true;
						}
					}
					if(flag)
					{
						tsetIdBenInCollector.add(eachMsg05TsetId);
						msg04TsetIdCollectorMatchedAndState.put(eachMsg05TsetId, validClearing);
					}else {
						tsetIdBenNotInCollector.add(eachMsg05TsetId);
						msg05TsetIdBeneficiaryMatched.put(eachMsg05TsetId, amendedAfterClearing);
					}

					tsetIdAndOrphanState.remove(eachMsg04TsetId);
					tsetIdBeneficiaryMatched.add(eachMsg04TsetId);
				}
			}
		}
		msg04Log.info("Matched TsetIds of MSG01FM01 and MSG05 are : " + tsetIdBenInCollector);
		validationStepInformation("Matched TsetIds of MSG01FM01 and MSG05 are : " + tsetIdBenInCollector);
		
		msg04Log.info("TsetIds of MSG05 not in MSG01FM01 are : " + tsetIdBenNotInCollector);	
		validationStepInformation("Matched TsetIds of MSG01FM01 and MSG05 are : " + tsetIdBenInCollector);
		for(String eachTsetId : msg01Tsets)
		{
			boolean flag = false;
			for(String eachMatchedItem : tsetIdBenInCollector)
			{
				if(eachTsetId.equals(eachMatchedItem))  
				{
                    flag = true;
				}
			}
			if(!flag)
			{
				tsetIdCollectorNotInBen.add(eachTsetId);
			}
		}
		msg04Log.info("TsetIds of MSG01FM01 not in MSG05 are : " + tsetIdCollectorNotInBen);
		validationStepInformation("TsetIds of MSG01FM01 not in MSG05 are : " + tsetIdCollectorNotInBen);
		
		msg04Log.info("TsetIds " + tsetIdCollectorMatched + " of MSG04 matches with 01FM01");
		validationStepInformation("TsetIds " + tsetIdCollectorMatched + " of MSG04 matches with 01FM01");

		msg04Log.info("TsetIds " + tsetIdBeneficiaryMatched + " of MSG04 matches with MSG05");
		validationStepInformation("TsetIds " + tsetIdBeneficiaryMatched + " of MSG04 matches with MSG05");

		List<String> unmatchedMSG04TsetIds = new ArrayList<String>();
		for(Entry<String, String> eachUnmatchedMSG04 : tsetIdAndOrphanState.entrySet())
		{
			unmatchedMSG04TsetIds.add(eachUnmatchedMSG04.getKey());
		}
		msg04Log.info("UnMatched TsetIds of MSG04 are : "+ unmatchedMSG04TsetIds);
		return this;
	}
}
