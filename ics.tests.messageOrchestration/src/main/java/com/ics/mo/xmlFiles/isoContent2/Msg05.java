package com.ics.mo.xmlFiles.isoContent2;

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
import com.ics.mo.common.MoGenericMethods2;
import com.ics.seleniumCoreUtilis.Component;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * <h1>Msg05</h1>
 * This class file contains the methods related to MSG05 switch File 
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-04-15
 */
@SuppressWarnings("unused")
public class Msg05 extends MoGenericMethods2
{
	public Msg05(ExtentReports extent, ExtentTest extentLog) 
	{
		super(extent,extentLog );
		// TODO Auto-generated constructor stub
	}

	protected String actualSwitchContentOfSourceDB = new String(), actualSourceIDOfMsg05 = new String(), actualSourceStateDivision = new String();
	protected ArrayList<HashMap<String, String>> groupHdrDetails;
	protected HashMap<String, String> isoMsgId, isoCreDtTm, isoNoOfTxs, isoRcrId, isoTstInd, isoSgntr; 
	protected static final String msgId = "MsgId", creDtTm = "CreDtTm", noOfTxs = "NoOfTxs", rcrId = "RcrId", tstInd = "TstInd", sgntr = "Sgntr" ;
	protected boolean grpHdrFlag = true, msgFlowFlag = true, txSetCreditDetailsFlag=true, expectedGrpHdrFlag, actualGrpHdrFlag, expectedTxSetFlag, actualTxSetFlag ;
	protected HashMap<String ,HashMap<String, List<HashMap<String, String>>>> isoTransactionCreditDetails;
	protected HashMap<String, List<HashMap<String, String>>> isoCreditItemIds;
	protected HashMap<String, String> isoBkCds, isoAcctNbs, isoSrlNbs, isoCrdtItmTxCds, isoAmts;
	protected ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> txSetMapCreditDetails;
	protected static HashMap<String, HashMap<String, String>> mapofItemIdAndItsBankDetails = new LinkedHashMap<String, HashMap<String, String>>();
	protected Set<String> setDetails = new HashSet<String>(), duplicateTSetIds = new HashSet<String>();	
	protected HashMap<String, String> getAllBankDetails = new LinkedHashMap<String, String>();
	protected final Logger msg05Log = Logger.getLogger(Msg05.class.getSimpleName()) ;

	/**
	 * This method is to check ISOContent of MSG05 loaded in Source Table
	 * @param inputFilePath
	 * @param inputFileName
	 * @param msg05
	 * @throws Exception
	 */
	public void checkMSg05ISOContentOfSourceTable(String inputFilePath, String inputFileName, List<String> itemIds) throws Exception
	{
		msg05Log.info(isoContentValidation + msg05);
		String query = "Select " + isoContentXMLColumn + "," + sourceIDColumn + "," + sourceSKIDColumn + "," + sourceStateRevisionColumn + " From " 
				+ repositorySchema + sourceTable + " Where " + messageTypeColumn + "='" + msg05 + "'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query);
		while(resultSet.next())
		{
			actualSwitchContentOfSourceDB = new String();
			actualSourceIDOfMsg05 = new String();
			actualSwitchContentOfSourceDB = resultSet.getString(isoContent);
			actualSourceStateDivision = resultSet.getString(sourceStateRevisionColumn);
	        boolean tempFlag = false;
			for(String eachCreditItemId : itemIds)
			{
				if((actualSwitchContentOfSourceDB.indexOf(eachCreditItemId)>0))
				{
					tempFlag = true;
				}else{
					tempFlag = false;
				}
			}
			if(tempFlag)
			{
				actualSourceIDOfMsg05 = resultSet.getString(sourceIDColumn);
				String strExpectedISOData = FileUtils.readFileToString(new File(inputFilePath + inputFileName));
				String strActualISOData = actualSwitchContentOfSourceDB;
				validationStepInformation(String.format(detailComparison, msg05));
				ArrayList<HashMap<String, String>> expectedGrpHdrDetails = getGrpHdrDetails(strExpectedISOData);
				expectedGrpHdrFlag = grpHdrFlag;
				grpHdrFlag = true;
				ArrayList<HashMap<String, String>> actualGrpHdrDetails = getGrpHdrDetails(strActualISOData);
				actualGrpHdrFlag = grpHdrFlag;
				validateGrpHdrDetails(expectedGrpHdrDetails, actualGrpHdrDetails, msg05);

				ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> expectedTxSetAndItsCrdtItmIdDetails = getTxSetAndItsCrdtItmIdDetails(strExpectedISOData);
				expectedTxSetFlag = txSetCreditDetailsFlag;
				txSetCreditDetailsFlag = true;
				ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> actualTxSetAndItsCrdtItmIdDetails = getTxSetAndItsCrdtItmIdDetails(strActualISOData);
				actualTxSetFlag = txSetCreditDetailsFlag;	
				validateTxSetDetails(expectedTxSetAndItsCrdtItmIdDetails, actualTxSetAndItsCrdtItmIdDetails, msg05, credit);
				if(strExpectedISOData.indexOf("<DbtStopdItm>") > 0)
				{
					validationStepInformation(dbtStopItemCheckHeader+ msg05);
					if(strActualISOData.indexOf("<DbtStopdItm>") > 0)
					{
						publishResults(true, dbtStopItemLogActual + msg05, String.format(dbtStopItemLogExpected, msg05), dbtStopItemLogCheck);
					}else {
						publishResults(false, dbtStopItemLogError + msg05, String.format(dbtStopItemLogExpected, msg05), dbtStopItemLogCheck);
					}
				}				
			}
		}
	}

	/**
	 * This method is to validate GrpHdr Details
	 * @param expectedGrpHdrDetails
	 * @param actualGrpHdrDetails
	 * @param msg05
	 */
	public void validateGrpHdrDetails(ArrayList<HashMap<String, String>> expectedGrpHdrDetails, 
			ArrayList<HashMap<String, String>> actualGrpHdrDetails, String msg05)
	{
		validationStepInformation(grpHdrCheckHeader);
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
											"Check " + expectedGroupHdrDetailName +" tag and its value in Source table for ISOContent of msg05 "+ msg05 );								
								}
							}	
						}
						if(!checkFlag)
						{
							validationErrorInformation(expectedGroupHdrDetailName + ":" + isoTagDetailsNotLoaded);
						}
					} 
				}
			}
		}
	}

	/**
	 * This method is to Validate TxSet details
	 * @param expectedTxSet
	 * @param actualTxSet
	 * @param msg05
	 * @param itemType
	 */
	public void validateTxSetDetails(ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> expectedTxSet,
			ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> actualTxSet, String msg05, String itemType)
	{
		if(expectedTxSetFlag && actualTxSetFlag)
		{
			if(expectedTxSet.size() == actualTxSet.size())
			{
				for(int eachExpectedTxSet = 0 ; eachExpectedTxSet < expectedTxSet.size() ; eachExpectedTxSet++)
				{

					validationStepInformation(String.format(detailComparisonTsets, itemType, eachExpectedTxSet, itemType));
					Iterator<Entry<String, HashMap<String, List<HashMap<String, String>>>>> iterator1 = expectedTxSet.get(eachExpectedTxSet).entrySet().iterator();
					while(iterator1.hasNext())
					{
						Entry<String, HashMap<String, List<HashMap<String, String>>>> e1 = iterator1.next();
						String expectedTxSetId = e1.getKey();
						checkDuplicateTsetTag(expectedTxSetId, msg05);
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
									publishResults(checkTxSetIdFlag, "TxSetId tag value " + actualTxSetId + " loaded/available in Source table for msg05 " + msg05, 
											"Should see TxSetId tag value " + actualTxSetId + " to load in Source table for msg05 " + msg05 , 
											"Check on TxSetId tag load "+ " in Source table for msg05 "+ msg05); 
									HashMap<String, List<HashMap<String, String>>> actualTxSetIdDetail = e2.getValue();
									Iterator<Entry<String, List<HashMap<String, String>>>> iterator3 = expectedTxSetIdDetail.entrySet().iterator();
									while(iterator3.hasNext())
									{
										Entry<String, List<HashMap<String, String>>> expectedItmIdDetail = iterator3.next();
										String expectedItmId = expectedItmIdDetail.getKey();
										List<HashMap<String, String>> expectedItemBankDetails = expectedItmIdDetail.getValue();
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
												publishResults(checkItemIdFlag, itemType +"Item tag value " + actualItmId + " of TxSetId " + expectedTxSetId + " available in Source table for msg05 " + msg05, 
														"Should see " + itemType + "Item tag value " + expectedItmId + " of TxSetId " + expectedTxSetId + " to be load/available in Source table for msg05 "+ msg05,
														"Check on " + itemType + "Item tag load for TxSetId "+ expectedTxSetId + " in Source table for msg05 "+ msg05 );
												for(int eachExpectedDbtBankDetails = 0 ; eachExpectedDbtBankDetails < expectedItemBankDetails.size() ; eachExpectedDbtBankDetails++ )
												{
													Iterator<Entry<String, String>> iterator5 = expectedItemBankDetails.get(eachExpectedDbtBankDetails).entrySet().iterator();
													boolean checkItemBankDetailFlag = false;
													while(iterator5.hasNext())
													{
														Entry<String, String> eachExpectedItemBankDetail = iterator5.next();
														String eachExpectedItemBankDetailName = eachExpectedItemBankDetail.getKey();
														List<HashMap<String, String>> actualItemBankDetails = actualItmIdDetail.getValue();
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
																	if(itemType.equals(credit))
																	{
																		switch(eachExpectedItemBankDetailName)
																		{
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
												mapofItemIdAndItsBankDetails.put(expectedItmId, getAllBankDetails) ; 
											}
										}	
										if(!checkItemIdFlag)
										{
											validationErrorInformation(expectedItmId + ":" + isoTagDetailsNotLoaded + " for " + expectedTxSetId);
										}
									}
								}
							}
						}
						if(!checkTxSetIdFlag)
						{
							validationErrorInformation(expectedTxSetId + ":" + isoTagDetailsNotLoaded);
						}
					}					
				}
			}
		}
	}

	/**
	 * Get TxSet And Its CrdtItmId details
	 * @param msg05Data
	 * @return
	 */
	public ArrayList<HashMap<String, HashMap<String, List<HashMap<String, String>>>>> getTxSetAndItsCrdtItmIdDetails(String msg05Data)
	{
		int count = getStringOccurence(msg05Data, "<TxSet>");
		txSetMapCreditDetails = new ArrayList<HashMap<String ,HashMap<String, List<HashMap<String, String>>>>>();
		int index = 0;
		while(count > 0)
		{
			int txSetIndexStartTag = msg05Data.indexOf("<TxSet>");
			int txSetIndexEndTag = msg05Data.indexOf("</TxSet>");			
			String txSet = msg05Data.substring(txSetIndexStartTag, txSetIndexEndTag);
			msg05Data = msg05Data.substring(txSetIndexStartTag + "<TxSet>".length());
			txSetMapCreditDetails.add(getCrdtItmDetailsForTsetId(txSet));
			--count;
			if(count >0)
			{
				msg05Data = msg05Data.substring(msg05Data.indexOf("<TxSet>"));
			}	
			index++;
		}
		return txSetMapCreditDetails;
	}

	/**
	 * This method is to get CrdtItm Details for each TsetId of given ISOContent
	 * @param isoContent
	 * @return
	 */
	public HashMap<String ,HashMap<String, List<HashMap<String, String>>>> getCrdtItmDetailsForTsetId(String isoContent)
	{
		String txSetId = isoContent.substring(isoContent.indexOf("<TxSetId>")+"<TxSetId>".length() , isoContent.indexOf("</TxSetId>"));
		isoTransactionCreditDetails = new HashMap<String ,HashMap<String, List<HashMap<String, String>>>>();
		isoCreditItemIds = new HashMap<String, List<HashMap<String, String>>>();
		isoSrlNbs = new HashMap<String, String>();
		isoBkCds = new HashMap<String, String>();
		isoAcctNbs = new HashMap<String, String>();
		isoCrdtItmTxCds = new HashMap<String, String>();
		isoAmts = new HashMap<String, String>();
		List<HashMap<String, String>> CreditIdBankDetails = new ArrayList<HashMap<String, String>>();

		int count = getStringOccurence(isoContent, "<CrdtItm>");
		int i = 0;
		while(count > 0)
		{
			int CrdtItmIndexStartTag = isoContent.indexOf("<CrdtItm>");
			int CrdtItmIndexEndTag = isoContent.indexOf("</CrdtItm>");
			String CrdtItmDetails = isoContent.substring(CrdtItmIndexStartTag, CrdtItmIndexEndTag);


			String CreditItemId = new String();
			try{
				CreditItemId = CrdtItmDetails.substring(CrdtItmDetails.indexOf("<CrdtItmId>"), CrdtItmDetails.indexOf("</CrdtItmId>"));
				int CreditItemIdIndex = CreditItemId.indexOf(">");
				CreditItemId = CreditItemId.substring(++CreditItemIdIndex);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
//				tagExceptionErrorLogInformation("CrdtItmId" , exception);
			}

			try{
				String srlNb = CrdtItmDetails.substring(CrdtItmDetails.indexOf("<SrlNb>"), CrdtItmDetails.indexOf("</SrlNb>"));
				int srlNbIndex = srlNb.indexOf(">");
				srlNb = srlNb.substring(++srlNbIndex);
				isoSrlNbs.put(serialNo, srlNb);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
//				tagExceptionErrorLogInformation(serialNo , exception);
			}

			try{
				String bkCd = CrdtItmDetails.substring(CrdtItmDetails.indexOf("<BkCd>"), CrdtItmDetails.indexOf("</BkCd>"));
				int bkCdIndex = bkCd.indexOf(">");
				bkCd = bkCd.substring(++bkCdIndex);
				isoBkCds.put(sortCode, bkCd);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
//				tagExceptionErrorLogInformation(sortCode , exception);
			}

			try{
				String acctNb = CrdtItmDetails.substring(CrdtItmDetails.indexOf("<AcctNb>"), CrdtItmDetails.indexOf("</AcctNb>"));
				int acctNbIndex = acctNb.indexOf(">");
				acctNb = acctNb.substring(++acctNbIndex);
				isoAcctNbs.put(accountNo, acctNb);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
//				tagExceptionErrorLogInformation(accountNo , exception);
			}

			try{
				String CrdtItmTxCd = CrdtItmDetails.substring(CrdtItmDetails.indexOf("<CrdtItmTxCd>"), CrdtItmDetails.indexOf("</CrdtItmTxCd>"));
				int CrdtItmTxCdIndex = CrdtItmTxCd.indexOf(">");
				CrdtItmTxCd = CrdtItmTxCd.substring(++CrdtItmTxCdIndex);
				isoCrdtItmTxCds.put(transactionCode, CrdtItmTxCd);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
//				tagExceptionErrorLogInformation(transactionCode , exception);
			}

			try{
				String amt = CrdtItmDetails.substring(CrdtItmDetails.indexOf("<Amt Ccy=\"GBP\">"), CrdtItmDetails.indexOf("</Amt>"));
				int amtIndex = amt.indexOf(">");
				amt = amt.substring(++amtIndex);
				isoAmts.put(amount, amt);
			}catch(Exception exception){
				txSetCreditDetailsFlag = false;
//				tagExceptionErrorLogInformation(amount , exception);
			}

			CreditIdBankDetails.add(isoSrlNbs);
			CreditIdBankDetails.add(isoBkCds);
			CreditIdBankDetails.add(isoAcctNbs);
			CreditIdBankDetails.add(isoCrdtItmTxCds);
			CreditIdBankDetails.add(isoAmts);
			isoCreditItemIds.put(CreditItemId, CreditIdBankDetails);
			isoContent = isoContent.substring(isoContent.indexOf("<CrdtItm>")+"<CrdtItm>".length());
			count--;
			if(count >0)
			{
				isoContent = isoContent.substring(isoContent.indexOf("<CrdtItm>"));
				isoSrlNbs = new HashMap<String, String>();
				isoBkCds = new HashMap<String, String>();
				isoAcctNbs = new HashMap<String, String>();
				isoCrdtItmTxCds = new HashMap<String, String>();
				isoAmts = new HashMap<String, String>();
				CreditIdBankDetails = new ArrayList<HashMap<String, String>>();
			}	
			i++;					
		}		
		isoTransactionCreditDetails.put(txSetId, isoCreditItemIds);
		return isoTransactionCreditDetails;
	}	

	/**
	 * This method is to get the GrpHdr Details of given Switch Content 
	 * @param isoContent
	 * @return
	 */
	private ArrayList<HashMap<String, String>> getGrpHdrDetails(String isoContent)
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

	/**
	 * This method is to display TagException Error details for missed ISOContent tag
	 * @param tagname
	 * @param exception
	 */
	public void tagExceptionErrorLogInformation(String tagname, Exception exception)
	{
//		validationErrorInformation("Error in " + tagname + " tag " + "<br/> <br/>" + exceptionMessage + exception.getMessage() + "<br/> <br/>" +
//				exceptionTrace + exception.getMessage());
	}	
	
	/**
	 * This method is to check the Duplicate TSetId 
	 * @param tSetTagInformation
	 * @param msg05
	 */
	public void checkDuplicateTsetTag(String tSetTagInformation, String msg05)
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
			validationStepInformation("<br/>" + "Duplicate TSetID " + tSetTagInformation + " is available in " + msg05);
			duplicateTSetIds.add(tSetTagInformation);
		}else{
			setDetails.add(tSetTagInformation);
		}
	}
	
	/**
	 * This method is to get the SwitchContent of msg05 from Source Table
	 * @return
	 */
	public String getISOContentOfmsg05SourceTable()
	{
		return actualSwitchContentOfSourceDB;
	}

	/**
	 * This method is to get the SourceId of msg05 file loaded in Source Table
	 * @return
	 */
	public String getSourceID()
	{
		return actualSourceIDOfMsg05;
	}
	
	public synchronized static HashMap<String, HashMap<String, String>> getMapofItemIdAndItsBankDetails()
	{
		return mapofItemIdAndItsBankDetails;
	}
	
	/**
	 * This method is to get the Map of ItemIds and its EntityState
	 * @return
	 */
	public HashMap<String,String> getItemIdAndItState(List<String> itemIds)
	{
		HashMap<String, String> itemIdAndItsState = new LinkedHashMap<String,String>();
		for(String eachItemId : itemIds)
		{
			itemIdAndItsState.put(eachItemId, beforeClearing);
		}
		return itemIdAndItsState;
	}
	
	/**
	 * This method is to get the Map of TsetIds and its EntityState
	 */
	public HashMap<String,String> getTseIdAndItState(List<String> tsetIds)
	{
		HashMap<String, String> itemIdAndItsState = new LinkedHashMap<String,String>();
		for(String eachItemId : tsetIds)
		{
			itemIdAndItsState.put(eachItemId, beforeClearing);
		}
		return itemIdAndItsState;
	}
}
