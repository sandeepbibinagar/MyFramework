package com.ics.mo.xmlFiles.isoContent2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.ics.mo.common.MoGenericMethods2;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * <h1>MsgKM01</h1>
 * This class file contains the methods related to KAPPA to MO File 
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-04-15
 */
public class MsgKM01 extends MoGenericMethods2
{
	private HashMap<String, String> debitItemAndItsFraudResult = new LinkedHashMap<String, String>();
	private HashMap<String, String> creditItemAndItsFraudResult = new LinkedHashMap<String, String>();
	private HashMap<String, String> itemAndItsKAPPAresponse = new LinkedHashMap<String, String>();
	private HashMap<String, String> tempItemDetails = new LinkedHashMap<String, String>();
	private final Logger msgKM01Log = Logger.getLogger(MsgKM01.class.getSimpleName()) ;
	
	public MsgKM01(ExtentReports extent, ExtentTest extentLog) {
		super(extent, extentLog);
		// TODO Auto-generated constructor stub
	}	

	/**
	 * This method is to get KAPPA response states W.R.T FRED/DEW states
	 * @param inputFilePath
	 * @param inputFileName
	 * @param itemIdAndItsStates
	 * @param workFlow
	 * @return
	 */
	public HashMap<String,String> getKappaResponseForKappaFile(String inputFilePath, String inputFileName,
			HashMap<String,String> itemIdAndItsStates, String workFlow)  throws Exception
	{
		getKM01ContentItemAndItsFraudDetails(inputFilePath, inputFileName, workFlow);
		for(Entry<String, String> eachItemIdAndItsState : itemIdAndItsStates.entrySet())
		{
			String itemId = eachItemIdAndItsState.getKey();
			String state = eachItemIdAndItsState.getValue();
			if(workFlow.equals(workFlow06KM01))
			{
				try{
					 debitItemAndItsFraudResult.get(itemId);
					if(state.equals(payerValidState))
					{
						itemAndItsKAPPAresponse.put(itemId, payableAfterFraud);
						
					}else if(state.equals(payerInvalidState))
					{
						itemAndItsKAPPAresponse.put(itemId, notPayableAfterFraud);
					}
				}catch(Exception e)
				{
					validationStepInformation("KAPPA file has no response for ItemId "+ itemId);
					msgKM01Log.info("KAPPA file has no response for ItemId "+ itemId);					
				}				
			}else if(workFlow.equals(workFlow05KM01))
			{
				String fraudResult = creditItemAndItsFraudResult.get(itemId);
				if(state.equals(validClearing))
				{
			       if(fraudResult.equals(kappaOk) || fraudResult.equals(kappaNotProcessed))
			       {
			    	   itemAndItsKAPPAresponse.put(itemId, validFraud);
			       }else if(fraudResult.equals(kappaSuspect) || fraudResult.equals(kappaFraudulent))
			       {
			    	   itemAndItsKAPPAresponse.put(itemId, invalidFraud);
			       }
				}else if(state.equals(invalidClearing))	
				{
					  itemAndItsKAPPAresponse.put(itemId, invalidFraud);
				}
			}else if(workFlow.equals(workFlow01KM01))
			{
				String fraudResult = tempItemDetails.get(itemId);
				if(state.equals(validClearing))
				{
					if(fraudResult.equals(kappaOk) || fraudResult.equals(kappaNotProcessed))
					{
						 itemAndItsKAPPAresponse.put(itemId, validFraud);
					}else if(fraudResult.equals(kappaSuspect) || fraudResult.equals(kappaFraudulent))
					{
						 itemAndItsKAPPAresponse.put(itemId, invalidFraud);
					}
				}else if(state.equals(amendedAfterClearing))
				{
					itemAndItsKAPPAresponse.put(itemId, invalidFraud);
				}
			}
		}	
		msgKM01Log.info("ItemId and Its KAPPA response details :" + itemAndItsKAPPAresponse);
		validationStepInformation("ItemId and Its KAPPA response details :" + itemAndItsKAPPAresponse);
		return itemAndItsKAPPAresponse;
	}
	
	/**
	 * This method is to get Map details of Item and its KAPPA response
	 * @return
	 */
	public HashMap<String,String> getItemAndKappaDetails()
	{
		return itemAndItsKAPPAresponse;
	}
	
	/**
	 * This method is to get List of KAPPA items
	 * @return
	 */
	public List<String> getKAPPAitems()
	{
		List<String> kappaItems = new ArrayList<String>();
		for(Entry<String, String> eachItemAndItsKAPPAresponse : itemAndItsKAPPAresponse.entrySet())
		{
			kappaItems.add(eachItemAndItsKAPPAresponse.getKey());
		}
		return kappaItems;
	}
	
	/**
	 * This method is to get (KAPPA to MO) FraudResult for required ItemIds 
	 * @param inputFilePath
	 * @param inputFileName
	 * @param workFlow
	 * @return
	 * @throws IOException
	 */
	private MsgKM01 getKM01ContentItemAndItsFraudDetails(String inputFilePath, String inputFileName, String workFlow) throws Exception
	{
		tempItemDetails = new LinkedHashMap<String, String>() ;
		String strKM01Data = FileUtils.readFileToString(new File(inputFilePath + inputFileName));
		if(workFlow.equals(workFlow06KM01))
		{
			debitItemAndItsFraudResult = new LinkedHashMap<String,String>();
			getFraudResultForDebitItems(strKM01Data);
		}else if(workFlow.equals(workFlow05KM01))
		{
			creditItemAndItsFraudResult = new LinkedHashMap<String,String>();
			getFraudResulrForCreditItems(strKM01Data);
		}else if(workFlow.equals(workFlow01KM01))
		{
			debitItemAndItsFraudResult = new LinkedHashMap<String,String>();
			creditItemAndItsFraudResult = new LinkedHashMap<String,String>();
			getFraudResultForDebitItems(strKM01Data);
			getFraudResulrForCreditItems(strKM01Data);
		}
		return this;
	}

	/**
	 * This method is to get (KAPPA to MO) FraudResult for required Debit Items
	 * @param strKM01Data
	 * @return
	 */
	private MsgKM01 getFraudResultForDebitItems(String strKM01Data)
	{
		int count = getStringOccurence(strKM01Data, "<DbtItmId>");
		String tempStrKM01Data = strKM01Data;
		while(count > 0)
		{
			tempStrKM01Data = tempStrKM01Data.substring(tempStrKM01Data.indexOf("<DbtItmId>"));
			String dbtItmId = tempStrKM01Data.substring(tempStrKM01Data.indexOf("<DbtItmId>") + "<DbtItmId>".length(),
					tempStrKM01Data.indexOf("</DbtItmId>"));
			String frdDtcnRslt = tempStrKM01Data.substring(tempStrKM01Data.indexOf("<FrdDtcnRslt>") + "<FrdDtcnRslt>".length(),
					tempStrKM01Data.indexOf("</FrdDtcnRslt>"));
			debitItemAndItsFraudResult.put(dbtItmId, frdDtcnRslt);
			tempItemDetails.put(dbtItmId, frdDtcnRslt);
			--count;
		}
		return this;
	}

	/**
	 * This method is to get (KAPPA to MO) FraudResult for required Credit Items
	 * @param strKM01Data
	 * @return
	 */
	private MsgKM01 getFraudResulrForCreditItems(String strKM01Data)
	{
		int count = getStringOccurence(strKM01Data, "<CdtItmId>");
		String tempStrKM01Data = strKM01Data;
		while(count > 0)
		{
			tempStrKM01Data = tempStrKM01Data.substring(tempStrKM01Data.indexOf("<CdtItmId>"));
			String cdtItmId = tempStrKM01Data.substring(tempStrKM01Data.indexOf("<CdtItmId>") + "<CdtItmId>".length(),
					tempStrKM01Data.indexOf("</CdtItmId>"));
			String frdDtcnRslt = tempStrKM01Data.substring(tempStrKM01Data.indexOf("<FrdDtcnRslt>") + "<FrdDtcnRslt>".length(),
					tempStrKM01Data.indexOf("</FrdDtcnRslt>"));
			creditItemAndItsFraudResult.put(cdtItmId, frdDtcnRslt);
			tempItemDetails.put(cdtItmId, frdDtcnRslt);
			--count;
		}
		return this;
	}
}
