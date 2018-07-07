package com.ics.mo.xmlFiles.isoContent;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.ics.mo.common.MoGenericMethods;
import com.ics.seleniumCoreUtilis.Component;

//<copyright  file="Msg06.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
* Module Name -  Msg06.java
************************************************************************ 
* Date -  26/04/2017
************************************************************************ 
* Created By -  MuluguUm
************************************************************************ 
* Description - Required Generic Functionalities to check Msg06
***********************************************************************/
public class Msg06 extends MoGenericMethods {
	protected static String actualSwitchContentOfSourceDB;
	protected static String actualSourceIDOfMsg06;
	protected static ArrayList<HashMap<String, String>> groupHdrDetails ;
	protected static HashMap<String, String> isoMsgId, isoCreDtTm, isoNoOfTxs, isoRcrId, isoTstInd, isoSgntr; 
	protected static String msgId = "MsgId", creDtTm = "CreDtTm", noOfTxs = "NoOfTxs", rcrId = "RcrId", tstInd = "TstInd", sgntr = "Sgntr" ; 
	protected static boolean grpHdrFlag = true , txSetDebitDetailsFlag = true , txSetCreditDetailsFlag = true, expectedGrpHdrFlag , actualGrpHdrFlag,
			expectedTxSetFlag, actualTxSetFlag;
	protected static Set<String> setDetails = new HashSet<String>();
	protected static Set<String> duplicateTSetIds = new HashSet<String>();
	protected static ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> txSetMapDebitDetails, txSetMapCreditDetails;
	protected static HashMap<String ,HashMap<String, List<HashMap<String, String>>>> isoTransactionDebitDetails, isoTransactionCreditDetails;
	protected static HashMap<String, List<HashMap<String, String>>> isoDebitItemIds , isoCdtItmIds;
	protected static HashMap<String, String> getAllBankDetails ;
	public static HashMap<String, HashMap<String, String>>  mapofItemIdAndItsBankDetails  = new LinkedHashMap<String, HashMap<String, String>>();
	protected static HashMap<String, String> isoBkCds, isoAcctNbs, isoSrlNbs, isoDbtItmTxCds, isoAmts, isoImgs, isoDayOneRspnWndwStartDtTm, 
	isoDayOneRspnWndwEndDtTm, isoDayTwoRspnWndwStartDtTm, isoDayTwoRspnWndwEndDtTm, isoCdtItmTp, isoCdtAmts, isoCdtBkCds,
	isoCdtAcctNbs, isoCdtRefNbs,isoDebitItmFradData, isoDebitDplctItm ;
	protected static String image = "Image";
	protected static String day1resposneWindowStartDtTime = "Day1resposneWindowStartDtTime";
	protected static String day2resposneWindowStartDtTime = "Day2resposneWindowStartDtTime";
	protected static String day1resposneWindowEndDtTime = "Day1resposneWindowEndDtTime";
	protected static String day2resposneWindowEndDtTime = "Day2resposneWindowEndDtTime";
	protected static String debitItmFradData = "DbtItmFrdData";
	protected static String debitDplctItm = "DbtDplctItm";
	protected static final Logger msg06Log = Logger.getLogger(Msg06.class.getSimpleName()) ;

	/*
	 * 1. Method to Check ISO Content inserted in Source Table
	 */
	public static void checkISOContentOfSourceTable(String inputFilePath, String inputFileName, String outputFilePath, String workFlow) throws Exception
	{
		msg06Log.info("Validating ISO insert in Source Table for workflow "+  workFlow);
		String query = "Select ISOContent = CAST(ISOContent AS XML), SourceID, SourceSKID, SourceStateRevision from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "where  MessageType = '" + workFlow + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			actualSwitchContentOfSourceDB = "";
			actualSourceIDOfMsg06 = "";
			actualSwitchContentOfSourceDB = resultSet.getString(columnISOContent);
			if(workFlow.equals("MSG06"))
			{
				actualSourceStateDivision = resultSet.getString(columnStateDivision);
			}			
			boolean tempFlag = false;
			for(String eachDebitItemId : expectedDebitItemId)
			{
				if((actualSwitchContentOfSourceDB.indexOf(eachDebitItemId) > 0) &&
						(actualSwitchContentOfSourceDB.indexOf(expectedSourceId.get(0)) > 0))
				{
					tempFlag = true;

				}else {
					tempFlag = false;
				}
			}
			if(tempFlag)
			{
				actualSourceIDOfMsg06 = resultSet.getString(columnSourceID);
				String actualContentPath = outputFilePath + "ActualISOContent_" + workFlow + ".txt";
				writeToFile(actualContentPath, actualSwitchContentOfSourceDB);	   
				String xmlExtensionActualData = getRenamedFileExtenstion(actualContentPath, ".txt", ".xml");
				String strExpectedISOData = FileUtils.readFileToString(new File(inputFilePath + inputFileName));
				String strActualISOData = FileUtils.readFileToString(new File(xmlExtensionActualData));
				@SuppressWarnings("unused")
				String compareExpected = strExpectedISOData.substring(strExpectedISOData.indexOf("<ReqToPay>"), strExpectedISOData.indexOf("</ReqToPay>"))
						+ "</ReqToPay>";
				@SuppressWarnings("unused")
				String compareActual = strActualISOData.substring(strActualISOData.indexOf("<ReqToPay>"),strActualISOData.indexOf("</ReqToPay>") ) + "</ReqToPay>";
				//XMLUnit difference Code
				validationStepInformation("Detailed comparison report for workflow " + workFlow + " in Source table as below:");

				ArrayList<HashMap<String, String>> expectedGrpHdrDetails =  getGrpHdrDetails(strExpectedISOData);
				expectedGrpHdrFlag = grpHdrFlag;
				grpHdrFlag = true;
				ArrayList<HashMap<String, String>> actualGrpHdrDetails = getGrpHdrDetails(strActualISOData);
				actualGrpHdrFlag = grpHdrFlag;
				validateGrpHdrDetails(expectedGrpHdrDetails, actualGrpHdrDetails, workFlow);

				ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> expectedTxSetAndItsDbtItmIdDetails = getTxSetAndItsDbtItmIdDetails(strExpectedISOData);
				expectedTxSetFlag =  txSetDebitDetailsFlag;
				txSetDebitDetailsFlag = true;
				ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> actualTxSetAndItsDbtItmIdDetails = getTxSetAndItsDbtItmIdDetails(strActualISOData);
				actualTxSetFlag = txSetDebitDetailsFlag;	
				validateTxSetDetails(expectedTxSetAndItsDbtItmIdDetails, actualTxSetAndItsDbtItmIdDetails, workFlow, "Debit");

				if(strExpectedISOData.indexOf("<DbtStopdItm>") > 0)
				{
					validationStepInformation("DbtStopdItm tag details available in input file of workflow "+ workFlow);
					if(strActualISOData.indexOf("<DbtStopdItm>") > 0)
					{
						publishResults(true, "DbtStopdItm tag details loaded in source table fow workflow " + workFlow ,
								"Should see DbtStopdItm tag details of input file of workFlow "+ workFlow + " to load in source table",
								"Check validation on DbtStopdItm tag details");
					}else {
						publishResults(false, "DbtStopdItm tag details not loaded in source table fow workflow " + workFlow ,
								"Should see DbtStopdItm tag details of input file of workFlow "+ workFlow + " to load in source table",
								"Check validation on DbtStopdItm tag details");
					}
				}				
			}
		}
		if(workFlow.equals("MSG06"))
		{
			validationStepInformation("SourceStateDivision for workflow "+ workFlow + " is : " + actualSourceStateDivision);
			msg06Log.info("SourceStateDivision for workflow "+ workFlow + " is : " + actualSourceStateDivision);
		}

		for(Entry<String, HashMap<String, String>> eachItemIdDetails : mapofItemIdAndItsBankDetails.entrySet())
		{
			msg06Log.info("Bank details of " + workFlow + " for itemId "+ eachItemIdDetails.getKey() + ":"+ eachItemIdDetails.getValue() );
		}

	}

	/*
	 * 2. Validate TxSetDetails
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
						if(workFlow.equals("MSG06"))
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
												//												tempDebitIsoDetails2
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
																	if(itemType.equals("Debit"))
																	{
																		switch(eachExpectedItemBankDetailName)
																		{
																		case "SerialNo" :
																			getAllBankDetails.put(serialNo, eachActualItemBankDetailValue);
																			publishResults(eachExpectedItemBankDetailValue.equals(eachActualItemBankDetailValue), eachActualItemBankDetailName + " tag value is : "+ eachActualItemBankDetailValue ,
																					"Should see "+ eachExpectedItemBankDetailName + " tag value is : "+ eachExpectedItemBankDetailValue, 
																					"Check validation of tag name " + eachExpectedItemBankDetailName + " for " + itemType + "ItmId " + expectedItmId 
																					+ " of TxSetId " + expectedTxSetId );
																			break;
																		case "SortCode" :
																			getAllBankDetails.put(sortCode, eachActualItemBankDetailValue);
																			publishResults(eachExpectedItemBankDetailValue.equals(eachActualItemBankDetailValue), eachActualItemBankDetailName + " tag value is : "+ eachActualItemBankDetailValue ,
																					"Should see "+ eachExpectedItemBankDetailName + " tag value is : "+ eachExpectedItemBankDetailValue, 
																					"Check validation of tag name " + eachExpectedItemBankDetailName + " for " + itemType + "ItmId " + expectedItmId 
																					+ " of TxSetId " + expectedTxSetId );
																			break;
																		case "Account" :
																			getAllBankDetails.put(accountNo, eachActualItemBankDetailValue);
																			publishResults(eachExpectedItemBankDetailValue.equals(eachActualItemBankDetailValue), eachActualItemBankDetailName + " tag value is : "+ eachActualItemBankDetailValue ,
																					"Should see "+ eachExpectedItemBankDetailName + " tag value is : "+ eachExpectedItemBankDetailValue, 
																					"Check validation of tag name " + eachExpectedItemBankDetailName + " for " + itemType + "ItmId " + expectedItmId 
																					+ " of TxSetId " + expectedTxSetId );
																			break;	
																		case "TranCode"  :
																			getAllBankDetails.put(transactionCode, eachActualItemBankDetailValue);
																			publishResults(eachExpectedItemBankDetailValue.equals(eachActualItemBankDetailValue), eachActualItemBankDetailName + " tag value is : "+ eachActualItemBankDetailValue ,
																					"Should see "+ eachExpectedItemBankDetailName + " tag value is : "+ eachExpectedItemBankDetailValue, 
																					"Check validation of tag name " + eachExpectedItemBankDetailName + " for " + itemType + "ItmId " + expectedItmId 
																					+ " of TxSetId " + expectedTxSetId );
																			break;
																		case "Amount" :
																			getAllBankDetails.put(amount, eachActualItemBankDetailValue);
																			boolean flag = eachExpectedItemBankDetailValue.equals(eachActualItemBankDetailValue);
																			publishResults(eachExpectedItemBankDetailValue.equals(eachActualItemBankDetailValue), eachActualItemBankDetailName + " tag value is : "+ eachActualItemBankDetailValue ,
																					"Should see "+ eachExpectedItemBankDetailName + " tag value is : "+ eachExpectedItemBankDetailValue, 
																					"Check validation of tag name " + eachExpectedItemBankDetailName + " for " + itemType + "ItmId " + expectedItmId 
																					+ " of TxSetId " + expectedTxSetId );
																			float expectedAmountValue = Float.parseFloat(eachExpectedItemBankDetailValue)  ;
																			@SuppressWarnings("unused")
																			float actualAmountValue = Float.parseFloat(eachActualItemBankDetailValue);
																			if(expectedAmountValue > 3000)
																			{
																				if(flag)
																				{
																					validationStepInformation("Standard Debit Clearing-Amt>3K " + "processed/loaded for workflow "+ workFlow);

																				}else {
																					validationErrorInformation("Standard Debit Clearing-Amt>3K " + "not processed/loaded for workflow "+ workFlow);
																					msgFlowFlag = false;
																					Component.assertTrue(msgFlowFlag , "Standard Debit Clearing-Amt>3K " + "not processed/loaded for workflow "+ workFlow);
																				}
																			}
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

	/*
	 * 3. Validate DbtItmFrdData Tag Details
	 */
	public static void validateDbtItmFrdData(String expected, String actual , String debitItemId, String txsetId)
	{
		String expectedChqAtRskInd = "";
		String actualChqAtRskInd = "";
		try{
			expectedChqAtRskInd = expected.substring(expected.indexOf("<ChqAtRskInd>"), expected.indexOf("</ChqAtRskInd>"));
			int expectedChqAtRskIndIndex = expectedChqAtRskInd.indexOf(">");
			expectedChqAtRskInd = expectedChqAtRskInd.substring(++expectedChqAtRskIndIndex);
		}catch(Exception exception){
			validationErrorInformation("No <ChqAtRskInd>  tag details of <DbtItmFrdData> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualChqAtRskInd =  actual.substring(expected.indexOf("<ChqAtRskInd>"), actual.indexOf("</ChqAtRskInd>"));
			int actualChqAtRskIndIndex = actualChqAtRskInd.indexOf(">");
			actualChqAtRskInd = actualChqAtRskInd.substring(++actualChqAtRskIndIndex);
		}catch(Exception exception){
			validationErrorInformation("<ChqAtRskInd>" + " tag details of DbtItmFrdData not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedChqAtRskInd.equals(""))) && (!(actualChqAtRskInd.equals(""))))
		{
			publishResults(expectedChqAtRskInd.equals(actualChqAtRskInd), "ChqAtRskInd tag value loaded as : " + actualChqAtRskInd  , 
					"Should see loaded ChqAtRskInd tag value as " + expectedChqAtRskInd , 
					"Check validation of ChqAtRskInd tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "ChqAtRskInd tag value is : " + actualChqAtRskInd  , 
					"Should see valid ChqAtRskInd tag value loaded " , 
					"Check validation of ChqAtRskInd tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}

		String expectedDtOfFirstChq = "";
		String actualDtOfFirstChq = "";
		try{
			expectedDtOfFirstChq = expected.substring(expected.indexOf("<DtOfFrstChq>"), expected.indexOf("</DtOfFrstChq>"));
			int expectedDtOfFirstChqIndex = expectedDtOfFirstChq.indexOf(">");
			expectedDtOfFirstChq = expectedDtOfFirstChq.substring(++expectedDtOfFirstChqIndex);
		}catch(Exception exception){
			validationErrorInformation("No <DtOfFrstChq>  tag details of <DbtItmFrdData> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualDtOfFirstChq =  actual.substring(expected.indexOf("<DtOfFrstChq>"), actual.indexOf("</DtOfFrstChq>"));
			int actualDtOfFirstChqIndex = actualDtOfFirstChq.indexOf(">");
			actualDtOfFirstChq = actualDtOfFirstChq.substring(++actualDtOfFirstChqIndex);
		}catch(Exception exception){
			validationErrorInformation("<DtOfFrstChq> tag details of DbtItmFrdData not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		String expectedDtOfLastChq = "";
		String actualDtOfLastChq = "";
		try{
			expectedDtOfLastChq = expected.substring(expected.indexOf("<DtOfLstChq>"), expected.indexOf("</DtOfLstChq>"));
			int expectedDtOfLastChqIndex = expectedDtOfLastChq.indexOf(">");
			expectedDtOfLastChq = expectedDtOfLastChq.substring(++expectedDtOfLastChqIndex);
		}catch(Exception exception){
			validationErrorInformation("No <DtOfLstChq>  tag details of <DbtItmFrdData> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualDtOfLastChq =  actual.substring(actual.indexOf("<DtOfLstChq>"), actual.indexOf("</DtOfLstChq>"));
			int actualDtOfLastChqIndex = actualDtOfLastChq.indexOf(">");
			actualDtOfLastChq = actualDtOfLastChq.substring(++actualDtOfLastChqIndex);
		}catch(Exception exception){
			validationErrorInformation("<DtOfLstChq> tag details of DbtItmFrdData not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedDtOfLastChq.equals(""))) && (!(actualDtOfLastChq.equals(""))))
		{
			publishResults(expectedDtOfLastChq.equals(actualDtOfLastChq), "DtOfLstChq tag value loaded as : " + actualDtOfLastChq  , 
					"Should see loaded DtOfLstChq tag value as " + expectedDtOfLastChq , 
					"Check validation of DtOfLstChq tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "DtOfLstChq tag value is : " + actualDtOfLastChq  , 
					"Should see valid DtOfLstChq tag value loads in Source table " , 
					"Check validation of DtOfLstChq tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}

		String expectedNbOfCtrPtys = "";
		String actualNbOfCtrPtys = "";
		try{
			expectedNbOfCtrPtys = expected.substring(expected.indexOf("<NbOfCtrPtys>"), expected.indexOf("</NbOfCtrPtys>"));
			int expectedNbOfCtrPtysIndex = expectedNbOfCtrPtys.indexOf(">");
			expectedNbOfCtrPtys = expectedNbOfCtrPtys.substring(++expectedNbOfCtrPtysIndex);
		}catch(Exception exception){
			validationErrorInformation("No <NbOfCtrPtys>  tag details of <DbtItmFrdData> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualNbOfCtrPtys =  actual.substring(actual.indexOf("<NbOfCtrPtys>"), actual.indexOf("</NbOfCtrPtys>"));
			int actualNbOfCtrPtysIndex = actualNbOfCtrPtys.indexOf(">");
			actualNbOfCtrPtys = actualNbOfCtrPtys.substring(++actualNbOfCtrPtysIndex);
		}catch(Exception exception){
			validationErrorInformation("<NbOfCtrPtys> tag details of DbtItmFrdData not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedNbOfCtrPtys.equals(""))) && (!(actualNbOfCtrPtys.equals(""))))
		{
			publishResults(expectedNbOfCtrPtys.equals(actualNbOfCtrPtys), "NbOfCtrPtys tag value loaded as : " + actualNbOfCtrPtys  , 
					"Should see loaded NbOfCtrPtys tag value as " + expectedNbOfCtrPtys , 
					"Check validation of NbOfCtrPtys tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "NbOfCtrPtys tag value is : " + actualNbOfCtrPtys  , 
					"Should see valid NbOfCtrPtys tag value loads in Source table " , 
					"Check validation of NbOfCtrPtys tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}

		String expectedNbOfVldChqs = "";
		String actualNbOfVldChqs = "";
		try{
			expectedNbOfVldChqs = expected.substring(expected.indexOf("<NbOfVldChqs>"), expected.indexOf("</NbOfVldChqs>"));
			int expectedNbOfVldChqsIndex = expectedNbOfVldChqs.indexOf(">");
			expectedNbOfVldChqs = expectedNbOfVldChqs.substring(++expectedNbOfVldChqsIndex);
		}catch(Exception exception){
			validationErrorInformation("No <NbOfVldChqs>  tag details of <DbtItmFrdData> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualNbOfVldChqs =  actual.substring(actual.indexOf("<NbOfVldChqs>"), actual.indexOf("</NbOfVldChqs>"));
			int actualNbOfVldChqsIndex = actualNbOfVldChqs.indexOf(">");
			actualNbOfVldChqs = actualNbOfVldChqs.substring(++actualNbOfVldChqsIndex);
		}catch(Exception exception){
			validationErrorInformation("<NbOfVldChqs> tag details of DbtItmFrdData not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedNbOfVldChqs.equals(""))) && (!(actualNbOfVldChqs.equals(""))))
		{
			publishResults(expectedNbOfVldChqs.equals(actualNbOfVldChqs), "NbOfVldChqs tag value loaded as : " + actualNbOfVldChqs  , 
					"Should see loaded NbOfVldChqs tag value as " + expectedNbOfVldChqs , 
					"Check validation of NbOfVldChqs tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "NbOfVldChqs tag value is : " + actualNbOfVldChqs  , 
					"Should see valid NbOfVldChqs tag value loads in Source table " , 
					"Check validation of NbOfVldChqs tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}
		String expectedNbOfFrdChqs = "";
		String actualNbOfFrdChqs = "";
		try{
			expectedNbOfFrdChqs = expected.substring(expected.indexOf("<NbOfFrdChqs>"), expected.indexOf("</NbOfFrdChqs>"));
			int expectedNbOfFrdChqsIndex = expectedNbOfFrdChqs.indexOf(">");
			expectedNbOfFrdChqs = expectedNbOfFrdChqs.substring(++expectedNbOfFrdChqsIndex);
		}catch(Exception exception){
			validationErrorInformation("No <NbOfFrdChqs>  tag details of <DbtItmFrdData> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualNbOfFrdChqs =  actual.substring(actual.indexOf("<NbOfFrdChqs>"), actual.indexOf("</NbOfFrdChqs>"));
			int actualNbOfFrdChqsIndex = actualNbOfFrdChqs.indexOf(">");
			actualNbOfFrdChqs = actualNbOfFrdChqs.substring(++actualNbOfFrdChqsIndex);
		}catch(Exception exception){
			validationErrorInformation("<NbOfFrdChqs> tag details of DbtItmFrdData not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedNbOfFrdChqs.equals(""))) && (!(actualNbOfFrdChqs.equals(""))))
		{
			publishResults(expectedNbOfFrdChqs.equals(actualNbOfFrdChqs), "NbOfFrdChqs tag value loaded as : " + actualNbOfFrdChqs  , 
					"Should see loaded NbOfFrdChqs tag value as " + expectedNbOfFrdChqs , 
					"Check validation of NbOfFrdChqs tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "NbOfFrdChqs tag value is : " + actualNbOfFrdChqs , 
					"Should see valid NbOfFrdChqs tag value loads in Source table " , 
					"Check validation of NbOfFrdChqs tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}
	}

	/*
	 * 4. Validate DbtDplctItm tag Details
	 */
	public static void validateDbtDplctItm(String expected, String actual, String debitItemId, String txsetId)
	{
		String expectedDplctItmId = "";
		String actualDplctItmId = "";
		try{
			expectedDplctItmId = expected.substring(expected.indexOf("<DplctItmId>"), expected.indexOf("</DplctItmId>"));
			int expectedDplctItmIdIndex = expectedDplctItmId.indexOf(">");
			expectedDplctItmId = expectedDplctItmId.substring(++expectedDplctItmIdIndex);
		}catch(Exception exception){
			validationErrorInformation("No <DplctItmId>  tag details of <DbtDplctItm> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualDplctItmId =  actual.substring(actual.indexOf("<DplctItmId>"), actual.indexOf("</DplctItmId>"));
			int actualDplctItmIdIndex = actualDplctItmId.indexOf(">");
			actualDplctItmId = actualDplctItmId.substring(++actualDplctItmIdIndex);
		}catch(Exception exception){
			validationErrorInformation("<DplctItmId> tag details of <DbtDplctItm> not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedDplctItmId.equals(""))) && (!(actualDplctItmId.equals(""))))
		{
			publishResults(expectedDplctItmId.equals(actualDplctItmId), "DplctItmId tag value loaded as : " + actualDplctItmId  , 
					"Should see loaded DplctItmId tag value as " + expectedDplctItmId , 
					"Check validation of DplctItmId tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "DplctItmId tag value is : " + actualDplctItmId  , 
					"Should see valid DplctItmId tag value loaded " , 
					"Check validation of DplctItmId tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}
		String expectedDbtDplctSts = "";
		String actualDbtDplctSts = "";
		try{
			expectedDbtDplctSts = expected.substring(expected.indexOf("<DbtDplctSts>"), expected.indexOf("</DbtDplctSts>"));
			int expectedDbtDplctStsIndex = expectedDbtDplctSts.indexOf(">");
			expectedDbtDplctSts = expectedDbtDplctSts.substring(++expectedDbtDplctStsIndex);
		}catch(Exception exception){
			validationErrorInformation("No <DbtDplctSts>  tag details of <DbtDplctItm> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualDbtDplctSts =  actual.substring(actual.indexOf("<DbtDplctSts>"), actual.indexOf("</DbtDplctSts>"));
			int actualDbtDplctStsIndex = actualDbtDplctSts.indexOf(">");
			actualDbtDplctSts = actualDbtDplctSts.substring(++actualDbtDplctStsIndex);
		}catch(Exception exception){
			validationErrorInformation("<DbtDplctSts> tag details of <DbtDplctItm> not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedDbtDplctSts.equals(""))) && (!(actualDbtDplctSts.equals(""))))
		{
			publishResults(expectedDbtDplctSts.equals(actualDbtDplctSts), "DbtDplctSts tag value loaded as : " + actualDbtDplctSts  , 
					"Should see loaded DbtDplctSts tag value as " + expectedDbtDplctSts , 
					"Check validation of DbtDplctSts tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "DbtDplctSts tag value is : " + actualDbtDplctSts  , 
					"Should see valid DbtDplctSts tag value loaded " , 
					"Check validation of DbtDplctSts tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}
		String expectedDtFirstPresntd = "";
		String actualDtFirstPresntd = "";
		try{
			expectedDtFirstPresntd = expected.substring(expected.indexOf("<DtFirstPresntd>"), expected.indexOf("</DtFirstPresntd>"));
			int expectedDtFirstPresntdIndex = expectedDtFirstPresntd.indexOf(">");
			expectedDtFirstPresntd = expectedDtFirstPresntd.substring(++expectedDtFirstPresntdIndex);
		}catch(Exception exception){
			validationErrorInformation("No <DtFirstPresntd>  tag details of <DbtDplctItm> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualDtFirstPresntd =  actual.substring(actual.indexOf("<DtFirstPresntd>"), actual.indexOf("</DtFirstPresntd>"));
			int actualDtFirstPresntdIndex = actualDtFirstPresntd.indexOf(">");
			actualDtFirstPresntd = actualDtFirstPresntd.substring(++actualDtFirstPresntdIndex);
		}catch(Exception exception){
			validationErrorInformation("<DtFirstPresntd> tag details of <DbtDplctItm> not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedDtFirstPresntd.equals(""))) && (!(actualDtFirstPresntd.equals(""))))
		{
			publishResults(expectedDtFirstPresntd.equals(actualDtFirstPresntd), "DtFirstPresntd tag value loaded as : " + actualDtFirstPresntd  , 
					"Should see loaded DtFirstPresntd tag value as " + expectedDtFirstPresntd , 
					"Check validation of DbtDtFirstPresntd tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "DtFirstPresntd tag value is : " + actualDtFirstPresntd  , 
					"Should see valid DtFirstPresntd tag value loaded " , 
					"Check validation of DtFirstPresntd tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}
		String expectedMmbId = "";
		String actualMmbId = "";
		try{
			expectedMmbId = expected.substring(expected.indexOf("<MmbId>"), expected.indexOf("</MmbId>"));
			int expectedMmbIdIndex = expectedMmbId.indexOf(">");
			expectedMmbId = expectedMmbId.substring(++expectedMmbIdIndex);
		}catch(Exception exception){
			validationErrorInformation("No <MmbId>  tag details of <DbtDplctItm> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualMmbId =  actual.substring(actual.indexOf("<MmbId>"), actual.indexOf("</MmbId>"));
			int actualMmbIdIndex = actualMmbId.indexOf(">");
			actualMmbId = actualMmbId.substring(++actualMmbIdIndex);
		}catch(Exception exception){
			validationErrorInformation("<MmbId> tag details of <DbtDplctItm> not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedMmbId.equals(""))) && (!(actualMmbId.equals(""))))
		{
			publishResults(expectedMmbId.equals(actualMmbId), "MmbId tag value loaded as : " + actualMmbId  , 
					"Should see loaded MmbId tag value as " + expectedMmbId , 
					"Check validation of MmbId tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "MmbId tag value is : " + actualMmbId  , 
					"Should see valid MmbId tag value loaded " , 
					"Check validation of MmbId tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}
		String expectedOrgnlCaptrDt = "";
		String actualOrgnlCaptrDt = "";
		try{
			expectedOrgnlCaptrDt = expected.substring(expected.indexOf("<OrgnlCaptrDt>"), expected.indexOf("</OrgnlCaptrDt>"));
			int expectedOrgnlCaptrDtIndex = expectedOrgnlCaptrDt.indexOf(">");
			expectedOrgnlCaptrDt = expectedOrgnlCaptrDt.substring(++expectedOrgnlCaptrDtIndex);
		}catch(Exception exception){
			validationErrorInformation("No <OrgnlCaptrDt>  tag details of <DbtDplctItm> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualOrgnlCaptrDt =  actual.substring(actual.indexOf("<OrgnlCaptrDt>"), actual.indexOf("</OrgnlCaptrDt>"));
			int actualOrgnlCaptrDtIndex = actualOrgnlCaptrDt.indexOf(">");
			actualOrgnlCaptrDt = actualOrgnlCaptrDt.substring(++actualOrgnlCaptrDtIndex);
		}catch(Exception exception){
			validationErrorInformation("<OrgnlCaptrDt> tag details of <DbtDplctItm> not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedOrgnlCaptrDt.equals(""))) && (!(actualOrgnlCaptrDt.equals(""))))
		{
			publishResults(expectedOrgnlCaptrDt.equals(actualOrgnlCaptrDt), "OrgnlCaptrDt tag value loaded as : " + actualOrgnlCaptrDt  , 
					"Should see loaded OrgnlCaptrDt tag value as " + expectedOrgnlCaptrDt , 
					"Check validation of OrgnlCaptrDt tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "OrgnlCaptrDt tag value is : " + actualOrgnlCaptrDt  , 
					"Should see valid OrgnlCaptrDt tag value loaded " , 
					"Check validation of OrgnlCaptrDt tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}
		String expectedOrgnlSrc = "";
		String actualOrgnlSrc = "";
		try{
			expectedOrgnlSrc = expected.substring(expected.indexOf("<OrgnlSrc>"), expected.indexOf("</OrgnlSrc>"));
			int expectedOrgnlSrcIndex = expectedOrgnlSrc.indexOf(">");
			expectedOrgnlSrc = expectedOrgnlSrc.substring(++expectedOrgnlSrcIndex);
		}catch(Exception exception){
			validationErrorInformation("No <OrgnlSrc>  tag details of <DbtDplctItm> available in expected input file for DebitItemID"+ debitItemId);
		}
		try {
			actualOrgnlSrc =  actual.substring(actual.indexOf("<OrgnlSrc>"), actual.indexOf("</OrgnlSrc>"));
			int actualOrgnlSrcIndex = actualOrgnlSrc.indexOf(">");
			actualOrgnlSrc = actualOrgnlSrc.substring(++actualOrgnlSrcIndex);
		}catch(Exception exception){
			validationErrorInformation("<OrgnlSrc> tag details of <DbtDplctItm> not loaded/available in ISO Content for DebitItemID"+ debitItemId);
		}
		if((!(expectedOrgnlSrc.equals(""))) && (!(actualOrgnlSrc.equals(""))))
		{
			publishResults(expectedOrgnlSrc.equals(actualOrgnlSrc), "OrgnlSrc tag value loaded as : " + actualOrgnlSrc  , 
					"Should see loaded OrgnlSrc tag value as " + expectedOrgnlSrc , 
					"Check validation of OrgnlSrc tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);			
		}else {
			publishResults(false, "OrgnlSrc tag value is : " + actualOrgnlSrc  , 
					"Should see valid OrgnlSrc tag value loaded " , 
					"Check validation of OrgnlSrc tag of DbtItmFrdData tag for DbtItmId "+ debitItemId + " of TxsetId " + txsetId);	
		}
	}

	/*
	 * 5. Get TxSet and Its DbtItemId Details
	 */
	public static ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> getTxSetAndItsDbtItmIdDetails(String msg06Data)
	{
		int count = getStringOccurence(msg06Data, "<TxSet>");
		txSetMapDebitDetails = new ArrayList<HashMap<String ,HashMap<String, List<HashMap<String, String>>>>>();
		@SuppressWarnings("unused")
		int index = 0;
		while(count > 0)
		{
			int txSetIndexStartTag = msg06Data.indexOf("<TxSet>");
			int txSetIndexEndTag = msg06Data.indexOf("</TxSet>");			
			String txSet =  msg06Data.substring(txSetIndexStartTag, txSetIndexEndTag);
			msg06Data = msg06Data.substring(txSetIndexStartTag + "<TxSet>".length());
			txSetMapDebitDetails.add(getDbtItmDetailsForTsetId(txSet));
			--count;
			if(count >0)
			{
				msg06Data = msg06Data.substring(msg06Data.indexOf("<TxSet>"));
			}	
			index++;
		}
		return txSetMapDebitDetails;
	}

	/*
	 * 5.1 Get DbtItemDetails for given TxSetId
	 */
	public static HashMap<String ,HashMap<String, List<HashMap<String, String>>>> getDbtItmDetailsForTsetId(String isoContent)
	{
		String txSetId = isoContent.substring(isoContent.indexOf("<TxSetId>")+"<TxSetId>".length() , isoContent.indexOf("</TxSetId>"));
		isoTransactionDebitDetails = new HashMap<String ,HashMap<String, List<HashMap<String, String>>>>();
		isoDebitItemIds = new HashMap<String, List<HashMap<String, String>>>();
		isoSrlNbs = new HashMap<String, String>();
		isoBkCds  = new HashMap<String, String>();
		isoAcctNbs = new HashMap<String, String>();
		isoDbtItmTxCds = new HashMap<String, String>();
		isoAmts = new HashMap<String, String>();
		List<HashMap<String, String>> debitIdBankDetails = new ArrayList<HashMap<String, String>>();

		int count = getStringOccurence(isoContent, "<DbtItm>");
		@SuppressWarnings("unused")
		int i = 0;
		while(count > 0)
		{
			int dbtItmIndexStartTag = isoContent.indexOf("<DbtItm>");
			int dbtItmIndexEndTag = isoContent.indexOf("</DbtItm>");
			String dbtItmDetails =  isoContent.substring(dbtItmIndexStartTag, dbtItmIndexEndTag);


			String debitItemId = "";
			try{
				debitItemId = dbtItmDetails.substring(dbtItmDetails.indexOf("<DbtItmId>"), dbtItmDetails.indexOf("</DbtItmId>"));
				int debitItemIdIndex = debitItemId.indexOf(">");
				debitItemId = debitItemId.substring(++debitItemIdIndex);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation("DbtItemId" , exception);
			}

			try{
				String srlNb = dbtItmDetails.substring(dbtItmDetails.indexOf("<SrlNb>"), dbtItmDetails.indexOf("</SrlNb>"));
				int srlNbIndex = srlNb.indexOf(">");
				srlNb = srlNb.substring(++srlNbIndex);
				isoSrlNbs.put(serialNo, srlNb);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(serialNo , exception);
			}

			try{
				String bkCd = dbtItmDetails.substring(dbtItmDetails.indexOf("<BkCd>"), dbtItmDetails.indexOf("</BkCd>"));
				int bkCdIndex = bkCd.indexOf(">");
				bkCd = bkCd.substring(++bkCdIndex);
				isoBkCds.put(sortCode, bkCd);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(sortCode , exception);
			}

			try{
				String acctNb = dbtItmDetails.substring(dbtItmDetails.indexOf("<AcctNb>"), dbtItmDetails.indexOf("</AcctNb>"));
				int acctNbIndex = acctNb.indexOf(">");
				acctNb = acctNb.substring(++acctNbIndex);
				isoAcctNbs.put(accountNo, acctNb);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(accountNo , exception);
			}

			try{
				String dbtItmTxCd = dbtItmDetails.substring(dbtItmDetails.indexOf("<DbtItmTxCd>"), dbtItmDetails.indexOf("</DbtItmTxCd>"));
				int dbtItmTxCdIndex = dbtItmTxCd.indexOf(">");
				dbtItmTxCd = dbtItmTxCd.substring(++dbtItmTxCdIndex);
				isoDbtItmTxCds.put(transactionCode, dbtItmTxCd);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(transactionCode , exception);
			}

			try{
				String amt = dbtItmDetails.substring(dbtItmDetails.indexOf("<Amt Ccy=\"GBP\">"), dbtItmDetails.indexOf("</Amt>"));
				int amtIndex = amt.indexOf(">");
				amt = amt.substring(++amtIndex);
				isoAmts.put(amount, amt);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(amount , exception);
			}

		/*	try{
				String img = dbtItmDetails.substring(dbtItmDetails.indexOf("<Img>"), dbtItmDetails.indexOf("</Img>"));
				int imgIndex = img.indexOf(">");
				img = img.substring(++imgIndex);
				isoImgs.put(image, img+  "<br/>");
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(image , exception);
			}

			try{	
				String day1RespStartTime = dbtItmDetails.substring(dbtItmDetails.indexOf("<DayOneRspnWndwStartDtTm>"), dbtItmDetails.indexOf("</DayOneRspnWndwStartDtTm>"));
				int day1RespStartTimeIndex = day1RespStartTime.indexOf(">");
				day1RespStartTime = day1RespStartTime.substring(++day1RespStartTimeIndex);
				isoDayOneRspnWndwStartDtTm.put(day1resposneWindowStartDtTime, day1RespStartTime);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(day1resposneWindowStartDtTime , exception);
			}

			try{
				String day1RespEndTime = dbtItmDetails.substring(dbtItmDetails.indexOf("<DayOneRspnWndwEndDtTm>"), dbtItmDetails.indexOf("</DayOneRspnWndwEndDtTm>"));
				int day1RespEndTimeIndex = day1RespEndTime.indexOf(">");
				day1RespEndTime = day1RespEndTime.substring(++day1RespEndTimeIndex);
				isoDayOneRspnWndwEndDtTm.put(day1resposneWindowEndDtTime, day1RespEndTime);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(day1resposneWindowEndDtTime , exception);
			}

			try{
				String day2RespStartTime = dbtItmDetails.substring(dbtItmDetails.indexOf("<DayTwoRspnWndwStartDtTm>"), dbtItmDetails.indexOf("</DayTwoRspnWndwStartDtTm>"));
				int day2RespStartTimeIndex = day2RespStartTime.indexOf(">");
				day2RespStartTime = day2RespStartTime.substring(++day2RespStartTimeIndex);
				isoDayTwoRspnWndwStartDtTm.put(day2resposneWindowStartDtTime, day2RespStartTime);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(day2resposneWindowStartDtTime , exception);
			}

			try{
				String day2RespEndTime = dbtItmDetails.substring(dbtItmDetails.indexOf("<DayTwoRspnWndwEndDtTm>"), dbtItmDetails.indexOf("</DayTwoRspnWndwEndDtTm>"));
				int day2RespEndTimeIndex = day2RespEndTime.indexOf(">");
				day2RespEndTime = day2RespEndTime.substring(++day2RespEndTimeIndex);
				isoDayTwoRspnWndwEndDtTm.put(day2resposneWindowEndDtTime, day2RespEndTime);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(day2resposneWindowEndDtTime , exception);
			}

			try{
				String dbtItmFrdData = dbtItmDetails.substring(dbtItmDetails.indexOf("<DbtItmFrdData>"), dbtItmDetails.indexOf("</DbtItmFrdData>"));
				int dbtItmFrdDataIndex = dbtItmFrdData.indexOf(">");
				dbtItmFrdData = dbtItmFrdData.substring(++dbtItmFrdDataIndex);
				isoDebitItmFradData.put(debitItmFradData, dbtItmFrdData);
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(debitItmFradData , exception);
			}

			try{
				String dbtDplctItm = dbtItmDetails.substring(dbtItmDetails.indexOf("<DbtDplctItm>"), dbtItmDetails.indexOf("</DbtDplctItm>"));
				int dbtDplctItmIndex = dbtDplctItm.indexOf(">");
				dbtDplctItm = dbtDplctItm.substring(++dbtDplctItmIndex);
				isoDebitDplctItm.put(debitDplctItm, dbtDplctItm);	
			}catch(Exception exception){
				txSetDebitDetailsFlag = false;
				tagExceptionErrorLogInformation(debitDplctItm , exception);
			}
*/
			debitIdBankDetails.add(isoSrlNbs);
			debitIdBankDetails.add(isoBkCds);
			debitIdBankDetails.add(isoAcctNbs);
			debitIdBankDetails.add(isoDbtItmTxCds);
			debitIdBankDetails.add(isoAmts);
/*			debitIdBankDetails.add(isoImgs );
			debitIdBankDetails.add(isoDayOneRspnWndwStartDtTm);
			debitIdBankDetails.add(isoDayOneRspnWndwEndDtTm);
			debitIdBankDetails.add(isoDayTwoRspnWndwStartDtTm);
			debitIdBankDetails.add(isoDayTwoRspnWndwEndDtTm);
			debitIdBankDetails.add(isoDebitItmFradData);
			debitIdBankDetails.add(isoDebitDplctItm);*/
			isoDebitItemIds.put(debitItemId, debitIdBankDetails);
			isoContent = isoContent.substring(isoContent.indexOf("<DbtItm>")+"<DbtItm>".length());
			count--;
			if(count >0)
			{
				isoContent = isoContent.substring(isoContent.indexOf("<DbtItm>"));
				isoSrlNbs = new HashMap<String, String>();
				isoBkCds  = new HashMap<String, String>();
				isoAcctNbs = new HashMap<String, String>();
				isoDbtItmTxCds = new HashMap<String, String>();
				isoAmts = new HashMap<String, String>();
				isoImgs = new HashMap<String, String>();
				/*isoDayOneRspnWndwStartDtTm = new HashMap<String, String>();
				isoDayOneRspnWndwEndDtTm = new HashMap<String, String>();
				isoDayOneRspnWndwStartDtTm = new HashMap<String, String>();
				isoDayOneRspnWndwEndDtTm =  new HashMap<String, String>();
				isoDebitItmFradData = new HashMap<String, String>();
				isoDebitDplctItm = new HashMap<String, String>();*/
				debitIdBankDetails = new ArrayList<HashMap<String, String>>();
			}	
			i++;					
		}		
		isoTransactionDebitDetails.put(txSetId, isoDebitItemIds);
		return isoTransactionDebitDetails;
	}

	/*
	 * 7. Method to Validate GrpHdr Details
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
	 * 7.1 Get GrpHdrDetails 
	 */
	public static ArrayList<HashMap<String, String>> getGrpHdrDetails(String isoContent)
	{
		groupHdrDetails = new ArrayList<HashMap<String, String>>();
		isoMsgId = new HashMap<String, String>();
		isoCreDtTm = new HashMap<String, String>();
		isoNoOfTxs = new HashMap<String, String>();
		isoRcrId = new HashMap<String, String>();
		isoTstInd = new HashMap<String, String>();
	/*	isoSgntr = new HashMap<String, String>();*/

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
		/*try{
			isoSgntr.put(sgntr, isoContent.substring(isoContent.indexOf("<Sgntr>") + "<TstInd>".length(), isoContent.indexOf("</Sgntr>")) + "<br/>");
		}catch(Exception exception) {
			grpHdrFlag = false;
			tagExceptionErrorLogInformation(sgntr, exception);
		}	*/	
		groupHdrDetails.add(isoMsgId);
		groupHdrDetails.add(isoCreDtTm);
		groupHdrDetails.add(isoNoOfTxs);
		groupHdrDetails.add(isoRcrId);
		groupHdrDetails.add(isoTstInd);
	/*	groupHdrDetails.add(isoSgntr);*/
		return groupHdrDetails;
	}	



	public static String getISOContentOfMsg06FromSourceTable()
	{
		return actualSwitchContentOfSourceDB;
	}

	public static String getSourceID()
	{
		return actualSourceIDOfMsg06;
	}

	public static void tagExceptionErrorLogInformation(String tagname, Exception exception)
	{
		validationErrorInformation("Error in " + tagname + " tag " + "<br/> <br/>" + exceptionMessage + exception.getMessage() + "<br/> <br/>" +
				exceptionTrace + exception.getMessage());
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

	public static Set<String> getDuplicateTSetIds()
	{
		return duplicateTSetIds;
	}

	public static HashMap<String, List<HashMap<String, String>>> getExpectedISODbtItemDetails()
	{
		return isoDebitItemIds;
	}

	public static String getISOSerialNoKeyName()
	{
		return serialNo;
	}

	public static String getISOSortCodeKeyName()
	{
		return sortCode;
	}

	public static String getISOAccoutNoKeyName()
	{
		return accountNo;
	}

	public static String getISOTransactionCode()
	{
		return transactionCode;
	}

	public static String getISOAmount()
	{
		return amount;
	}

	public static String getISOimage()
	{
		return image;
	}

	public static String getSwitchSourceISOContent()
	{
		return actualSwitchContentOfSourceDB;
	}
}
