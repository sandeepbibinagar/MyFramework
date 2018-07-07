package com.ics.mo.xmlFiles.isoContent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.ics.mo.xmlFiles.icnContent.Msg06DM01;

//<copyright  file="Msg05.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
* Module Name -  Msg05.java
************************************************************************ 
* Date -  26/04/2017
************************************************************************ 
* Created By -  MuluguUm
************************************************************************ 
* Description - Required Generic Functionalities to check Msg05
***********************************************************************/
public class Msg05 extends Msg06DM01{
	protected static String actualSwitchContentOfSourceDB;
	protected static String actualSourceIDOfMsg05;
	protected static boolean expectedGrpHdrFlag, grpHdrFlag = true, actualGrpHdrFlag, expectedTxSetFlag, actualTxSetFlag, txSetDebitDetailsFlag = true , txSetCreditDetailsFlag = true;
	protected static ArrayList<HashMap<String, String>> groupHdrDetails ;
	protected static HashMap<String, List<HashMap<String, String>>> isoCdtItmIds;
	protected static HashMap<String, String> isoMsgId, isoCreDtTm, isoNoOfTxs, isoRcrId, isoTstInd, isoSgntr, isoSrlNbs; 
	protected static String msgId = "MsgId", creDtTm = "CreDtTm", noOfTxs = "NoOfTxs", rcrId = "RcrId", tstInd = "TstInd", sgntr = "Sgntr" ; 
	protected static ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> txSetMapDebitDetails, txSetMapCreditDetails;
	protected static HashMap<String ,HashMap<String, List<HashMap<String, String>>>>  isoTransactionCreditDetails;
	protected static HashMap<String, String> isoBkCds, isoAcctNbs,  isoAmts, isoImgs, isoDayOneRspnWndwStartDtTm, 
	isoDayOneRspnWndwEndDtTm, isoDayTwoRspnWndwStartDtTm, isoDayTwoRspnWndwEndDtTm, isoCdtItmTp, isoCdtAmts, isoCdtBkCds, isoCdtAcctNbs, isoCdtRefNbs;
	protected static String serialNo = "SerialNo", sortCode = "SortCode", accountNo = "Account" ,
			amount = "Amount", image = "Image", day1resposneWindowStartDtTime = "Day1resposneWindowStartDtTime",
			day2resposneWindowStartDtTime = "Day2resposneWindowStartDtTime", day1resposneWindowEndDtTime = "Day1resposneWindowEndDtTime",
			day2resposneWindowEndDtTime = "Day2resposneWindowEndDtTime"/*, creditTimeTp = "CreditTimeTp" , amount = "Amount", 
			sortCode = "SortCode" , accountNo = "Account", serialNo = "SerialNo"*/ ;
	protected static Set<String> setDetails = new HashSet<String>();
	protected static Set<String> duplicateTSetIds = new HashSet<String>();
	public static HashMap<String, HashMap<String, String>>  mapofItemIdAndItsBankDetails  = new LinkedHashMap<String, HashMap<String, String>>();
	protected static HashMap<String, String> getAllBankDetails ;
	protected static final Logger msg05Log = Logger.getLogger(Msg05.class.getSimpleName()) ;
	
	/*
	 * 1. Method to Check ISO Content loaded in Source Table for Msg05
	 */
	public static void checkISOContentOfSourceTable(String inputFilePath, String inputFileName, String outputFilePath, String workFlow) throws Exception
	{
		msg05Log.info("Validating ISO insert in Source Table for workflow "+  workFlow);
		String query = "Select ISOContent = CAST(ISOContent AS XML), SourceID, SourceSKID from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "where  MessageType = '" + workFlow + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			actualSwitchContentOfSourceDB = "";
			actualSourceIDOfMsg05 = "";
			actualSwitchContentOfSourceDB = resultSet.getString(columnISOContent);
			boolean tempFlag = false;
			for(String eachItemId : expectedCreditItemId)
			{
				if((actualSwitchContentOfSourceDB.indexOf(eachItemId) > 0) &&
						(actualSwitchContentOfSourceDB.indexOf(expectedSourceId.get(0)) > 0))
				{
					tempFlag = true;

				}else {
					tempFlag = false;
				}
			}
			if(tempFlag)
			{
				actualSourceIDOfMsg05 = resultSet.getString(columnSourceID);
				String actualContentPath = outputFilePath + "ActualISOContent_" + workFlow + ".txt";
				writeToFile(actualContentPath, actualSwitchContentOfSourceDB);	   
				String xmlExtensionActualData = getRenamedFileExtenstion(actualContentPath, ".txt", ".xml");
				String strExpectedISOData = FileUtils.readFileToString(new File(inputFilePath + inputFileName));
				String strActualISOData = FileUtils.readFileToString(new File(xmlExtensionActualData));
				validationStepInformation("Detailed comparison report for workflow " + workFlow + " in Source table as below:");
				ArrayList<HashMap<String, String>> expectedGrpHdrDetails =  getGrpHdrDetails(strExpectedISOData);
				expectedGrpHdrFlag = grpHdrFlag;
				grpHdrFlag = true;
				ArrayList<HashMap<String, String>> actualGrpHdrDetails = getGrpHdrDetails(strActualISOData);
				actualGrpHdrFlag = grpHdrFlag;
				validateGrpHdrDetails(expectedGrpHdrDetails, actualGrpHdrDetails, workFlow);
				
				ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> expectedTxSetAndItsCdtItmIdDetails = getTxSetAndItsCdtItmIdDetails(strExpectedISOData);
				expectedTxSetFlag =  txSetCreditDetailsFlag;
				txSetDebitDetailsFlag = true;
				ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> actualTxSetAndItsCdtItmIdDetails = getTxSetAndItsCdtItmIdDetails(strActualISOData);
				actualTxSetFlag = txSetCreditDetailsFlag;	
				validateTxSetDetails(expectedTxSetAndItsCdtItmIdDetails, actualTxSetAndItsCdtItmIdDetails, workFlow, "Credit");	
				
			}
		}
	}
		
	/*
	 *2. Get GrpHdr Details of ISO Content of MSg05 
	 */
	public static ArrayList<HashMap<String, String>> getGrpHdrDetails(String isoContent)
	{
		groupHdrDetails = new ArrayList<HashMap<String, String>>();
		isoMsgId = new HashMap<String, String>();
		isoCreDtTm = new HashMap<String, String>();
		isoNoOfTxs = new HashMap<String, String>();
		isoRcrId = new HashMap<String, String>();
		isoTstInd = new HashMap<String, String>();
		try{
			isoMsgId.put(msgId, isoContent.substring(isoContent.indexOf("<MsgId>") + "<MsgId>".length(), isoContent.indexOf("</MsgId>"))) ;
		}catch(Exception exception) {
			grpHdrFlag = false;
			tagExceptionErrorLogInformation(msgId, exception);
		}		
		try{
			isoCreDtTm.put(creDtTm, isoContent.substring(isoContent.indexOf("<CreDtTm>") + "<CreDtTm>".length(), isoContent.indexOf("</CreDtTm>")));
		}catch(Exception exception) {
			grpHdrFlag = false;
			tagExceptionErrorLogInformation(creDtTm, exception);
		}		
		try{
			isoNoOfTxs.put(noOfTxs, isoContent.substring(isoContent.indexOf("<NbOfTxs>") + "<NbOfTxs>".length(), isoContent.indexOf("</NbOfTxs>")));
		}catch(Exception exception) {
			grpHdrFlag = false;
			tagExceptionErrorLogInformation(noOfTxs, exception);
		}
		try{
			isoRcrId.put(rcrId, isoContent.substring(isoContent.indexOf("<RcvrId>") + "<RcvrId>".length(), isoContent.indexOf("</RcvrId>")));	
		}catch(Exception exception) {
			grpHdrFlag = false;
			tagExceptionErrorLogInformation(rcrId, exception);
		}
		try{
			isoTstInd.put(tstInd, isoContent.substring(isoContent.indexOf("<TstInd>") + "<TstInd>".length(), isoContent.indexOf("</TstInd>")));
		}catch(Exception exception) {
			grpHdrFlag = false;
			tagExceptionErrorLogInformation(tstInd, exception);
		}
		groupHdrDetails.add(isoMsgId);
		groupHdrDetails.add(isoCreDtTm);
		groupHdrDetails.add(isoNoOfTxs);
		groupHdrDetails.add(isoRcrId);
		groupHdrDetails.add(isoTstInd);
		return groupHdrDetails;
	}	
	
	/*
	 * 2.1 Validate GrpHdr Details of Msg05
	 */
	public static void validateGrpHdrDetails(ArrayList<HashMap<String, String>> expectedGrpHdrDetails, 
			ArrayList<HashMap<String, String>> actualGrpHdrDetails, String workFlow)
	{
		validationStepInformation("Detail validation report on GrpHdr tag load");
		if(expectedGrpHdrFlag && actualGrpHdrFlag)
		{
			if(expectedGrpHdrDetails.size() == actualGrpHdrDetails.size())
			{
				for(int eachExpectedGrpHdrDetail = 0 ; eachExpectedGrpHdrDetail < expectedGrpHdrDetails.size() ; eachExpectedGrpHdrDetail++)
				{

					Iterator<Entry<String, String>> iterator1 = expectedGrpHdrDetails.get(eachExpectedGrpHdrDetail).entrySet().iterator();
					while(iterator1.hasNext())
					{
						Entry<String, String> e1 = iterator1.next();
						String expectedGroupHdrDetailName = e1.getKey();
						String expectedGroupHdrDetailValue = e1.getValue();	
						boolean checkFlag = false;
						for(int eachActualGrpHdrDetail = 0 ; eachActualGrpHdrDetail < actualGrpHdrDetails.size() ; eachActualGrpHdrDetail++)
						{
							Iterator<Entry<String, String>> iterator2 = actualGrpHdrDetails.get(eachActualGrpHdrDetail).entrySet().iterator();
							while(iterator2.hasNext())
							{
								Entry<String, String> e2 = iterator2.next();
								String actualGroupHdrDetailName = e2.getKey();
								if(expectedGroupHdrDetailName.equals(actualGroupHdrDetailName))
								{
									checkFlag = true;
									String actualGroupHdrDetailValue = e2.getValue();
									publishResults(expectedGroupHdrDetailValue.equals(actualGroupHdrDetailValue),
											actualGroupHdrDetailName + " value is " + actualGroupHdrDetailValue , 
											expectedGroupHdrDetailName + " value is " + expectedGroupHdrDetailValue ,
											"Check " + expectedGroupHdrDetailName +" tag and its value in Source table for ISOContent of workflow "+ workFlow );								
								}
							}	
						}
						if(!checkFlag)
						{
							validationErrorInformation(expectedGroupHdrDetailName + " tag not loaded/available in ISO Content");
						}
					} 
				}
			}
		}
	}
	
	/*
	 * 2.2 Method for TagException Error Log information
	 */
	public static void tagExceptionErrorLogInformation(String tagname, Exception exception)
	{
		validationErrorInformation("Error in " + tagname + " tag " + "<br/> <br/>" + exceptionMessage + exception.getMessage() + "<br/> <br/>" +
				exceptionTrace + exception.getMessage());
	}
	
	/*
	 * 3.  Method to get TxsetAnd Its CredtItemId Details
	 */
	public static ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> getTxSetAndItsCdtItmIdDetails(String Msg05Data)
	{
		int count = getStringOccurence(Msg05Data, "<TxSet>");
		txSetMapCreditDetails = new ArrayList<HashMap<String ,HashMap<String, List<HashMap<String, String>>>>>();
		@SuppressWarnings("unused")
		int index = 0;
		while(count > 0)
		{
			int txSetIndexStartTag = Msg05Data.indexOf("<TxSet>");
			int txSetIndexEndTag = Msg05Data.indexOf("</TxSet>");			
			String txSet =  Msg05Data.substring(txSetIndexStartTag, txSetIndexEndTag);
			txSetMapCreditDetails.add(getCrdtItmDetailsForTsetId(txSet));
			Msg05Data = Msg05Data.substring(txSetIndexStartTag + "<TxSet>".length());
			count--;
			if(count >0)
			{
				Msg05Data = Msg05Data.substring(Msg05Data.indexOf("<TxSet>"));
			}	
			index++;
		}
		return txSetMapCreditDetails;
	}
	
	/*
	 * 3.1 Method to get CrdtItmDetailsFor given Tset
	 */
	public static HashMap<String, HashMap<String, List<HashMap<String, String>>>> getCrdtItmDetailsForTsetId(String isoContent)
	{
		String txSetId = isoContent.substring(isoContent.indexOf("<TxSetId>")+"<TxSetId>".length() , isoContent.indexOf("</TxSetId>"));
		isoTransactionCreditDetails = new HashMap<String ,HashMap<String, List<HashMap<String, String>>>>();
		isoCdtItmIds = new HashMap<String, List<HashMap<String, String>>>();
		isoCdtAmts  = new HashMap<String, String>();
		isoCdtBkCds = new HashMap<String, String>();
		isoCdtAcctNbs = new HashMap<String, String>();
		isoCdtRefNbs = new HashMap<String, String>();
		List<HashMap<String, String>> creditIdBankDetails = new ArrayList<HashMap<String, String>>();

		int count = getStringOccurence(isoContent, "<CrdtItm>");
		@SuppressWarnings("unused")
		int i = 0;
		while(count > 0)
		{
			int crdtItmIndexStartTag = isoContent.indexOf("<CrdtItm>");
			int crdtItmIndexEndTag = isoContent.indexOf("</CrdtItm>");
			String crdtItmDetails =  isoContent.substring(crdtItmIndexStartTag, crdtItmIndexEndTag);	

			String crdtItemId = "";

			try{
				crdtItemId = crdtItmDetails.substring(crdtItmDetails.indexOf("<CdtItmId>"), crdtItmDetails.indexOf("</CdtItmId>"));
				int creditItemIdIndex = crdtItmDetails.indexOf(">");
				creditItemIdIndex = creditItemIdIndex+1;
				crdtItemId = crdtItemId.substring(++creditItemIdIndex);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
				tagExceptionErrorLogInformation("CdtItmId" , exception);
			}

			try{
				String cdtAmount = crdtItmDetails.substring(crdtItmDetails.indexOf("<Amt Ccy=\"GBP\">"), crdtItmDetails.indexOf("</Amt>"));
				int cdtAmountIndex = cdtAmount.indexOf(">");
				cdtAmount = cdtAmount.substring(++cdtAmountIndex);
				isoCdtAmts.put(amount, cdtAmount);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
				tagExceptionErrorLogInformation(amount , exception);
			}

			try{
				String cdtBkCd = crdtItmDetails.substring(crdtItmDetails.indexOf("<BkCd>"), crdtItmDetails.indexOf("</BkCd>"));
				int cdtBkCdIndex = cdtBkCd.indexOf(">");
				cdtBkCd = cdtBkCd.substring(++cdtBkCdIndex);
				isoCdtBkCds.put(sortCode, cdtBkCd);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
				tagExceptionErrorLogInformation(sortCode , exception);
			}

			try{
				String cdtAccountNo = crdtItmDetails.substring(crdtItmDetails.indexOf("<AcctNb>"), crdtItmDetails.indexOf("</AcctNb>"));
				int cdtAccountNoIndex = cdtAccountNo.indexOf(">");
				cdtAccountNo = cdtAccountNo.substring(++cdtAccountNoIndex);
				isoCdtAcctNbs.put(accountNo, cdtAccountNo);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
				tagExceptionErrorLogInformation(accountNo , exception);
			}	

			try{
				String cdtRefNb = crdtItmDetails.substring(crdtItmDetails.indexOf("<RefNb>"), crdtItmDetails.indexOf("</RefNb>"));
				int cdtRefNbIndex = cdtRefNb.indexOf(">");
				cdtRefNb = cdtRefNb.substring(++cdtRefNbIndex);
				isoCdtRefNbs.put(serialNo, cdtRefNb);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
				tagExceptionErrorLogInformation(serialNo , exception);
			}
			creditIdBankDetails.add(isoCdtAmts);
			creditIdBankDetails.add(isoCdtBkCds);
			creditIdBankDetails.add(isoCdtAcctNbs);
			creditIdBankDetails.add(isoCdtRefNbs);
			isoCdtItmIds.put(crdtItemId, creditIdBankDetails);
			isoContent = isoContent.substring(isoContent.indexOf("<CrdtItm>")+"<DbtItm>".length());
			count--;
			if(count >0)
			{
				isoContent = isoContent.substring(isoContent.indexOf("<CrdtItm>"));
				isoCdtAmts  = new HashMap<String, String>();
				isoCdtBkCds = new HashMap<String, String>();
				isoCdtAcctNbs = new HashMap<String, String>();
				isoCdtRefNbs = new HashMap<String, String>();
				creditIdBankDetails = new ArrayList<HashMap<String, String>>();
			}	
			i++;					
		}
		isoTransactionCreditDetails.put(txSetId, isoCdtItmIds);
		return isoTransactionCreditDetails;
	}
	
	/*
	 * 3.2 Method to Validate TxSet Details
	 */
	public static void validateTxSetDetails(ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> expectedTxSet,
			ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> actualTxSet, String workFlow, String itemType)
	{
		if(expectedTxSetFlag && actualTxSetFlag)
		{
			if(expectedTxSet.size() == actualTxSet.size())
			{
				for(int eachExpectedTxSet = 0 ; eachExpectedTxSet < expectedTxSet.size() ; eachExpectedTxSet++)
				{

					validationStepInformation("Detailed validation report of " + itemType + "Ids of TxSet["+ eachExpectedTxSet + "] of " + itemType + " items");
					Iterator<Entry<String, HashMap<String, List<HashMap<String, String>>>>> iterator1 = expectedTxSet.get(eachExpectedTxSet).entrySet().iterator();
					while(iterator1.hasNext())
					{
						Entry<String, HashMap<String, List<HashMap<String, String>>>> e1 = iterator1.next();
						String expectedTxSetId = e1.getKey();
						if(workFlow.equals("Msg05"))
						{
							checkDuplicateTsetTag(expectedTxSetId, workFlow);
						}
						HashMap<String, List<HashMap<String, String>>> expectedTxSetIdDetail = e1.getValue();
						boolean checkTxSetIdFlag = false;
						for(int eachActualTxSet = 0 ; eachActualTxSet < actualTxSet.size() ; eachActualTxSet++)
						{
							Iterator<Entry<String, HashMap<String, List<HashMap<String, String>>>>> iterator2 = actualTxSet.get(eachActualTxSet).entrySet().iterator();
							while(iterator2.hasNext())
							{
								Entry<String, HashMap<String, List<HashMap<String, String>>>> e2 = iterator2.next();
								String actualTxSetId = e2.getKey();
								if(expectedTxSetId.equals(actualTxSetId))
								{
									checkTxSetIdFlag = true;
									publishResults(checkTxSetIdFlag, "TxSetId tag value " + actualTxSetId + " loaded/available in Source table for workflow " + workFlow, 
											"Should see TxSetId tag value " + actualTxSetId + " to load in Source table for workflow " + workFlow , 
											"Check on TxSetId tag load "+ " in Source table for workflow "+ workFlow); 
									HashMap<String, List<HashMap<String, String>>> actualTxSetIdDetail = e2.getValue();
									Iterator<Entry<String, List<HashMap<String, String>>>> iterator3 = expectedTxSetIdDetail.entrySet().iterator();
									while(iterator3.hasNext())
									{
										Entry<String, List<HashMap<String, String>>> expectedItmIdDetail = iterator3.next();
										String expectedItmId = expectedItmIdDetail.getKey();
										List<HashMap<String, String>> expectedItemBankDetails =  expectedItmIdDetail.getValue();
										Iterator<Entry<String, List<HashMap<String, String>>>> iterator4 = actualTxSetIdDetail.entrySet().iterator();
										boolean checkItemIdFlag = false;
										while(iterator4.hasNext())
										{
											Entry<String, List<HashMap<String, String>>> actualItmIdDetail = iterator4.next();
											String actualItmId = actualItmIdDetail.getKey();
											if(expectedItmId.equals(actualItmId))
											{
												getAllBankDetails = new LinkedHashMap<String, String>();
												checkItemIdFlag = true;
												publishResults(checkItemIdFlag, itemType +"Item tag value " + actualItmId + "  of TxSetId " + expectedTxSetId + " available in Source table for workflow " + workFlow, 
														"Should see " + itemType + "Item tag value " + expectedItmId + "  of TxSetId " + expectedTxSetId + " to be load/available in Source table for workflow "+ workFlow,
														"Check on " + itemType + "Item tag load for TxSetId "+  expectedTxSetId + " in Source table for workflow "+ workFlow );
												for(int eachExpectedDbtBankDetails = 0 ; eachExpectedDbtBankDetails < expectedItemBankDetails.size() ; eachExpectedDbtBankDetails++ )
												{
													Iterator<Entry<String, String>> iterator5 = expectedItemBankDetails.get(eachExpectedDbtBankDetails).entrySet().iterator();
													boolean checkItemBankDetailFlag = false;
													while(iterator5.hasNext())
													{
														Entry<String, String> eachExpectedItemBankDetail = iterator5.next();
														String eachExpectedItemBankDetailName = eachExpectedItemBankDetail.getKey();
														List<HashMap<String, String>> actualItemBankDetails =  actualItmIdDetail.getValue();
														for(int eachActualItemBankDetails = 0 ; eachActualItemBankDetails < actualItemBankDetails.size() ; eachActualItemBankDetails++)
														{
															Iterator<Entry<String, String>> iterator6 = actualItemBankDetails.get(eachActualItemBankDetails).entrySet().iterator();
															while(iterator6.hasNext())
															{
																Entry<String, String> eachActualItemBankDetail = iterator6.next();
																String eachActualItemBankDetailName = eachActualItemBankDetail.getKey();
																if(eachExpectedItemBankDetailName.equals(eachActualItemBankDetailName))
																{
																	checkItemBankDetailFlag = true;
																	String eachExpectedItemBankDetailValue = eachExpectedItemBankDetail.getValue();
																	String eachActualItemBankDetailValue = eachActualItemBankDetail.getValue();
																	if(itemType.equals("Credit"))
																	{
																		switch(eachExpectedItemBankDetailName)
																		{
//																		case "CdtItmTxCd" :
//																			publishResults(checkItemBankDetailFlag, eachActualItemBankDetailName + " tag value is : "+ eachActualItemBankDetailValue ,
//																					"Should see "+ eachExpectedItemBankDetailName + " tag value is : "+ eachExpectedItemBankDetailValue, 
//																					"Check validation of tag name " + eachExpectedItemBankDetailName + " for " + itemType + "ItmId " + expectedItmId 
//																					+ " of TxSetId " + expectedTxSetId );
//																			break;
																		case "Amount" :
																			getAllBankDetails.put(amount, eachActualItemBankDetailValue);
																			publishResults(checkItemBankDetailFlag, eachActualItemBankDetailName + " tag value is : "+ eachActualItemBankDetailValue ,
																					"Should see "+ eachExpectedItemBankDetailName + " tag value is : "+ eachExpectedItemBankDetailValue, 
																					"Check validation of tag name " + eachExpectedItemBankDetailName + " for " + itemType + "ItmId " + expectedItmId 
																					+ " of TxSetId " + expectedTxSetId );
																			break;
																		case "SortCode" :	
																			getAllBankDetails.put(sortCode, eachActualItemBankDetailValue);
																			publishResults(checkItemBankDetailFlag, eachActualItemBankDetailName + " tag value is : "+ eachActualItemBankDetailValue ,
																					"Should see "+ eachExpectedItemBankDetailName + " tag value is : "+ eachExpectedItemBankDetailValue, 
																					"Check validation of tag name " + eachExpectedItemBankDetailName + " for " + itemType + "ItmId " + expectedItmId 
																					+ " of TxSetId " + expectedTxSetId );

																			break;
																		case "Account" :
																			getAllBankDetails.put(accountNo, eachActualItemBankDetailValue);
																			publishResults(checkItemBankDetailFlag, eachActualItemBankDetailName + " tag value is : "+ eachActualItemBankDetailValue ,
																					"Should see "+ eachExpectedItemBankDetailName + " tag value is : "+ eachExpectedItemBankDetailValue, 
																					"Check validation of tag name " + eachExpectedItemBankDetailName + " for " + itemType + "ItmId " + expectedItmId 
																					+ " of TxSetId " + expectedTxSetId );
																			break;
																		case "SerialNo" :	
																			getAllBankDetails.put(serialNo, eachActualItemBankDetailValue);
																			publishResults(checkItemBankDetailFlag, eachActualItemBankDetailName + " tag value is : "+ eachActualItemBankDetailValue ,
																					"Should see "+ eachExpectedItemBankDetailName + " tag value is : "+ eachExpectedItemBankDetailValue, 
																					"Check validation of tag name " + eachExpectedItemBankDetailName + " for " + itemType + "ItmId " + expectedItmId 
																					+ " of TxSetId " + expectedTxSetId );
																			break;
																		}
																	}
																}
															}
														}
														if(!checkItemBankDetailFlag)
														{
															validationErrorInformation(eachExpectedItemBankDetailName + " tag details not loaded/available in ISO Content for "+ expectedItmId);
														}
													}
												}
												mapofItemIdAndItsBankDetails.put(expectedItmId, getAllBankDetails)  ; 
											}
										}	
										if(!checkItemIdFlag)
										{
											validationErrorInformation(expectedItmId + " tag details not loaded/available in ISO Content for " + expectedTxSetId);
										}
									}
								}
							}
						}
						if(!checkTxSetIdFlag)
						{
							validationErrorInformation(expectedTxSetId + " tag details not loaded/available in ISO Content");
						}
					}					
				}
			}
		}
	}
	
	public static void checkDuplicateTsetTag(String tSetTagInformation, String workFlow)
	{
		boolean flag = true;
		for(String eachSetDetail : setDetails)
		{
			if(eachSetDetail.equals(tSetTagInformation))
			{
				flag = false;  
			}
		}
		if(!flag)
		{			
			validationStepInformation("<br/>" + "Duplicate TSetID " + tSetTagInformation + " is available in " + workFlow);
			duplicateTSetIds.add(tSetTagInformation);
		}else{
			setDetails.add(tSetTagInformation);
		}
	}
	
	public static String getMsg05SourceID()
	{
		return actualSourceIDOfMsg05;
	}
	
	public static String getSwitchSourceISOContent()
	{
		return actualSwitchContentOfSourceDB;
	}
}
