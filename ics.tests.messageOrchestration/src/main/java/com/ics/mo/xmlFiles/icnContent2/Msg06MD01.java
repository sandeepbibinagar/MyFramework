package com.ics.mo.xmlFiles.icnContent2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import org.apache.log4j.Logger;
import com.ics.mo.common.MoGenericMethods2;
import com.ics.mo.xmlFiles.isoContent2.Msg06;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * <h1>Msg06MD01</h1>
 * This class file contains the methods related to 06MD01 flow and CodeLine Details check of FRED file
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-04-15
 */
@SuppressWarnings("unused")
public class Msg06MD01 extends MoGenericMethods2
{

	public Msg06MD01(ExtentReports extent, ExtentTest extentLog) {
		super(extent, extentLog);
		// TODO Auto-generated constructor stub
	}

	private HashMap<String, HashMap<String, String>>  isoDebitDetailsAfterFREDFlow ;
	private HashMap<String, String> debitIdBankDetailsAfterFREDFlow ;
	private boolean txSetDebitDetailsFlag = true ;
	private final Logger msg06MD01Log = Logger.getLogger(Msg06MD01.class.getSimpleName()) ;

	/**
	 * This method is to Validate CodeLine Details of 06MD01
	 * @param workFlow
	 * @param sourceContent
	 * @param mapofItemIdAndItsBankDetails
	 */
	public void validateCodeLineDetails(String workFlow, String sourceContent, HashMap<String, HashMap<String, String>> mapofItemIdAndItsBankDetails)
	{
		getISOContent(sourceContent);
		boolean finalFlag = true;
		for(Entry<String, HashMap<String, String>> eachDebitDetail : mapofItemIdAndItsBankDetails.entrySet())
		{
			HashMap<String, String> expectedCodeLineDetails = eachDebitDetail.getValue();
			String expectedItemId = eachDebitDetail.getKey();
			HashMap<String, String> actualCodeLineDetails  = new LinkedHashMap<String,String>();
			try {
				actualCodeLineDetails = isoDebitDetailsAfterFREDFlow.get(eachDebitDetail.getKey());
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
				validationStepInformation("No details for ItemId : " + expectedItemId + " in WorkFlow Content of "+ workFlow);
				continue;
			}
		}
		if(!finalFlag)
		{
			msg06MD01Log.error("Codeline details of "+ workFlow + " not matches with FRED file");	
		}else {
			msg06MD01Log.info("Codeline details of "+ workFlow + " matches with FRED file");
		}
	}

	/**
	 * This method is to get Bank Details after FRED flow  
	 * @param sourceContent
	 */
	private void getISOContent(String sourceContent)
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
				new Msg06(extent, extentLog).tagExceptionErrorLogInformation("DbtItemId" , exception);
			}	

			try{
				String srlNb = dbtItmDetails.substring(dbtItmDetails.indexOf("<SrlNb>"), dbtItmDetails.indexOf("</SrlNb>"));
				int srlNbIndex = srlNb.indexOf(">");
				srlNb = srlNb.substring(++srlNbIndex);
				debitIdBankDetailsAfterFREDFlow.put(serialNo, srlNb);
				/*isoSrlNbs07MS01.put(serialNo, srlNb);*/
			}catch(Exception exception){
				new Msg06(extent, extentLog).tagExceptionErrorLogInformation(serialNo , exception);
			}

			try{
				String bkCd = dbtItmDetails.substring(dbtItmDetails.indexOf("<BkCd>"), dbtItmDetails.indexOf("</BkCd>"));
				int bkCdIndex = bkCd.indexOf(">");
				bkCd = bkCd.substring(++bkCdIndex);
				debitIdBankDetailsAfterFREDFlow.put(sortCode, bkCd);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				new Msg06(extent, extentLog).tagExceptionErrorLogInformation(sortCode , exception);
			}

			try{
				String acctNb = dbtItmDetails.substring(dbtItmDetails.indexOf("<AcctNb>"), dbtItmDetails.indexOf("</AcctNb>"));
				int acctNbIndex = acctNb.indexOf(">");
				acctNb = acctNb.substring(++acctNbIndex);
				debitIdBankDetailsAfterFREDFlow.put(accountNo, acctNb);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				new Msg06(extent, extentLog).tagExceptionErrorLogInformation(accountNo , exception);
			}
			try{
				String amt = dbtItmDetails.substring(dbtItmDetails.indexOf("<Amt Ccy=\"GBP\">"), dbtItmDetails.indexOf("</Amt>"));
				int amtIndex = amt.indexOf(">");
				amt = amt.substring(++amtIndex);
				debitIdBankDetailsAfterFREDFlow.put(amount, amt);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				new Msg06(extent, extentLog).tagExceptionErrorLogInformation(amount , exception);
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
