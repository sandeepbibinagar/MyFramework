package com.ics.mo.xmlFiles.isoContent;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import com.ics.mo.xmlFiles.icnContent.Msg06MA03;

//<copyright  file="Msg06MD01.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
* Module Name -  Msg06MD01.java
************************************************************************ 
* Date -  26/04/2017
************************************************************************ 
* Created By -  MuluguUm
************************************************************************ 
* Description - Required Generic Functionalities to check Msg06MD01
***********************************************************************/
@SuppressWarnings("unused")
public class Msg06MD01 extends Msg06 {
	protected static HashMap<String, HashMap<String, String>>  isoDebitDetailsAfterFREDFlow ;
	protected static HashMap<String, String> debitIdBankDetailsAfterFREDFlow ;
	protected static final Logger msg06MD01Log = Logger.getLogger(Msg06MD01.class.getSimpleName()) ;

	/*
	 * 1. Validate CodeLine Details 
	 */
	public static void validateCodeLineDetails(String workFlow, String sourceContent)
	{
		getISOContentOf07MS01(sourceContent);
		boolean finalFlag = true;
		for(Entry<String, HashMap<String, String>> eachDebitDetail : mapofItemIdAndItsBankDetails.entrySet())
		{
			try {
				HashMap<String, String> expectedCodeLineDetails = eachDebitDetail.getValue();
				String expectedItemId = eachDebitDetail.getKey();
				HashMap<String, String> actualCodeLineDetails = isoDebitDetailsAfterFREDFlow.get(eachDebitDetail.getKey());
				for(Entry<String, String> eachCodeLineDetails : expectedCodeLineDetails.entrySet())
				{
					String expectedBankDetailsKey = eachCodeLineDetails.getKey();
					String expectedBankDetailsValue = eachCodeLineDetails.getValue();

					String actualBankDetailsValue = actualCodeLineDetails.get(expectedBankDetailsKey);

					boolean flag = expectedBankDetailsValue.equals(actualBankDetailsValue);

					if(!(expectedBankDetailsKey.equals("TranCode")))
					{
						if(flag)
						{
							msg06MD01Log.info(expectedBankDetailsKey + " details " + expectedBankDetailsValue + " for ItemId "+ expectedItemId + " matches with "+ expectedBankDetailsKey + " details of FRED file"); 	
						    validationStepInformation(expectedBankDetailsKey + " details " + expectedBankDetailsValue + " for ItemId "+ expectedItemId + " matches with "+ expectedBankDetailsKey + " details of FRED file");
						}else {
							finalFlag = false;
							msg06MD01Log.error(expectedBankDetailsKey + " details " + expectedBankDetailsValue + " for ItemId "+ expectedItemId + " not matches with "+ expectedBankDetailsKey + " details of FRED file"); 	
						    validationErrorInformation(expectedBankDetailsKey + " details " + expectedBankDetailsValue + " for ItemId "+ expectedItemId + " not matches with "+ expectedBankDetailsKey + " details of FRED file");
						}
					}
					
				}
			}catch(Exception e)
			{
				
			}
			
		}
		if(!finalFlag)
		{
			msg06MD01Log.error("Codeline details of "+ workFlow + " not matches with FRED file");	
		}else {
			msg06MD01Log.info("Codeline details of "+ workFlow + " matches with FRED file");
		}
	}

	public static void getISOContentOf07MS01(String sourceContent)
	{		
		debitIdBankDetailsAfterFREDFlow = new LinkedHashMap<String, String>();
		isoDebitDetailsAfterFREDFlow = new LinkedHashMap<String, HashMap<String, String>>() ;

		String msg07MS01Content = sourceContent;
		int count = getStringOccurence(msg07MS01Content, "<DbtItm>");
		int i = 0;
		while(count > 0)
		{
			int dbtItmIndexStartTag = msg07MS01Content.indexOf("<DbtItm>");
			int dbtItmIndexEndTag = msg07MS01Content.indexOf("</DbtItm>");
			String dbtItmDetails =  msg07MS01Content.substring(dbtItmIndexStartTag, dbtItmIndexEndTag);

			String debitItemId = "";
			try{
				debitItemId = dbtItmDetails.substring(dbtItmDetails.indexOf("<DbtItmId>"), dbtItmDetails.indexOf("</DbtItmId>"));
				int debitItemIdIndex = debitItemId.indexOf(">");
				debitItemId = debitItemId.substring(++debitItemIdIndex);
			}catch(Exception exception){
				tagExceptionErrorLogInformation("DbtItemId" , exception);
			}	

			try{
				String srlNb = dbtItmDetails.substring(dbtItmDetails.indexOf("<SrlNb>"), dbtItmDetails.indexOf("</SrlNb>"));
				int srlNbIndex = srlNb.indexOf(">");
				srlNb = srlNb.substring(++srlNbIndex);
				debitIdBankDetailsAfterFREDFlow.put(serialNo, srlNb);
				/*isoSrlNbs07MS01.put(serialNo, srlNb);*/
			}catch(Exception exception){
				tagExceptionErrorLogInformation(serialNo , exception);
			}

			try{
				String bkCd = dbtItmDetails.substring(dbtItmDetails.indexOf("<BkCd>"), dbtItmDetails.indexOf("</BkCd>"));
				int bkCdIndex = bkCd.indexOf(">");
				bkCd = bkCd.substring(++bkCdIndex);
				debitIdBankDetailsAfterFREDFlow.put(sortCode, bkCd);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(sortCode , exception);
			}

			try{
				String acctNb = dbtItmDetails.substring(dbtItmDetails.indexOf("<AcctNb>"), dbtItmDetails.indexOf("</AcctNb>"));
				int acctNbIndex = acctNb.indexOf(">");
				acctNb = acctNb.substring(++acctNbIndex);
				debitIdBankDetailsAfterFREDFlow.put(accountNo, acctNb);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(accountNo , exception);
			}
			try{
				String amt = dbtItmDetails.substring(dbtItmDetails.indexOf("<Amt Ccy=\"GBP\">"), dbtItmDetails.indexOf("</Amt>"));
				int amtIndex = amt.indexOf(">");
				amt = amt.substring(++amtIndex);
				debitIdBankDetailsAfterFREDFlow.put(amount, amt);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(amount , exception);
			}
			isoDebitDetailsAfterFREDFlow.put(debitItemId, debitIdBankDetailsAfterFREDFlow);
			msg07MS01Content = msg07MS01Content.substring(msg07MS01Content.indexOf("<DbtItm>")+"<DbtItm>".length());
			count--;
			if(count >0)
			{
				msg07MS01Content = msg07MS01Content.substring(msg07MS01Content.indexOf("<DbtItm>"));		
				debitIdBankDetailsAfterFREDFlow = new LinkedHashMap<String, String>();
			}
			i++;
		}
	}
}
