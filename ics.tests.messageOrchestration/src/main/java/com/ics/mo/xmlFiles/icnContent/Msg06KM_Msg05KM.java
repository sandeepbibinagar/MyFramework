package com.ics.mo.xmlFiles.icnContent;

import java.io.File;
import java.sql.SQLException;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

//<copyright  file="MsgKM01.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
 * Module Name -  MsgKM01.java
 ************************************************************************ 
 * Date -  26/04/2017
 ************************************************************************ 
 * Created By -  MuluguUm
 ************************************************************************ 
 * Description - Required Generic Functionalities to check MsgKM01
 ***********************************************************************/
public class Msg06KM_Msg05KM extends MsgMA02 {
	private static String fraudResponseContentOfKappaFile;
	protected static final Logger msg06KM_05KM_Log = Logger.getLogger(Msg06KM_Msg05KM.class.getSimpleName()) ;

	/*
	 * 1. Check 06KM01 file record In Receive Staging Table
	 */
	public static void checkICNFileKM01InsertInSourceTable(String inputFilePath, String inputFileName, String outputFilePath, String workFlow) throws Exception
	{
		String strExpectedICNKM01Data = FileUtils.readFileToString(new File(inputFilePath + inputFileName));
		String actualContentPath = outputFilePath + "ActualISOContent_" + workFlow + ".txt";
		String strActualICN06KM01Data = "";
		if(workFlow.equals("MSF06"))
		{
			strActualICN06KM01Data = getISOSourceContent();
		}else if(workFlow.equals("MSF08"))
		{
			strActualICN06KM01Data = getISOSourceContent();
		}		
		writeToFile(actualContentPath, strActualICN06KM01Data);
		String expectedData = strExpectedICNKM01Data;
		String actualData = strActualICN06KM01Data;
		do
		{
			if(workFlow.equals("MSF08"))
			{
				strActualICN06KM01Data = actualData.substring(actualData.indexOf("<MSF08RtpFrdChkRsp>")+ "<MSF08RtpFrdChkRsp>".length(), actualData.indexOf("</MSF08RtpFrdChkRsp>"));
				strExpectedICNKM01Data = expectedData.substring(expectedData.indexOf("<MSF08RtpFrdChkRsp>")+ "<MSF08RtpFrdChkRsp>".length(), expectedData.indexOf("</MSF08RtpFrdChkRsp>"));

				actualData = actualData.substring(actualData.indexOf("</MSF08RtpFrdChkRsp>") + "</MSF08RtpFrdChkRsp>".length());
				expectedData =  expectedData.substring(expectedData.indexOf("</MSF08RtpFrdChkRsp>") + "</MSF08RtpFrdChkRsp>".length());
			}else if(workFlow.equals("MSF06"))
			{
				strActualICN06KM01Data = actualData.substring(actualData.indexOf("<MSF06TsetFrdChkRsp>")+ "<MSF06TsetFrdChkRsp>".length(), actualData.indexOf("</MSF06TsetFrdChkRsp>"));
				strExpectedICNKM01Data = expectedData.substring(expectedData.indexOf("<MSF06TsetFrdChkRsp>")+ "<MSF06TsetFrdChkRsp>".length(), expectedData.indexOf("</MSF06TsetFrdChkRsp>"));

				actualData = actualData.substring(actualData.indexOf("</MSF06TsetFrdChkRsp>") + "</MSF06TsetFrdChkRsp>".length());
				expectedData =  expectedData.substring(expectedData.indexOf("</MSF06TsetFrdChkRsp>") + "</MSF06TsetFrdChkRsp>".length());
			}

			String actualDbtItmId="";
			int actualDbtItmIdIndex = 0;
			String expectedDbtItmId = "";
			int expectedDbtItmIdIndex = 0;
			if(workFlow.equals("MSF08"))
			{
				actualDbtItmId = strActualICN06KM01Data.substring(strActualICN06KM01Data.indexOf("<DbtItmId>"), strActualICN06KM01Data.indexOf("</DbtItmId>"));
				actualDbtItmIdIndex = actualDbtItmId.indexOf(">");
				actualDbtItmId = actualDbtItmId.substring(++actualDbtItmIdIndex);

				expectedDbtItmId = strExpectedICNKM01Data.substring(strExpectedICNKM01Data.indexOf("<DbtItmId>"), strExpectedICNKM01Data.indexOf("</DbtItmId>"));
				expectedDbtItmIdIndex = expectedDbtItmId.indexOf(">");
				expectedDbtItmId = expectedDbtItmId.substring(++expectedDbtItmIdIndex);
			}else if(workFlow.equals("MSF06"))
			{
				actualDbtItmId = strActualICN06KM01Data.substring(strActualICN06KM01Data.indexOf("<CdtItmId>"), strActualICN06KM01Data.indexOf("</CdtItmId>"));
				actualDbtItmIdIndex = actualDbtItmId.indexOf(">");
				actualDbtItmId = actualDbtItmId.substring(++actualDbtItmIdIndex);

				expectedDbtItmId = strExpectedICNKM01Data.substring(strExpectedICNKM01Data.indexOf("<CdtItmId>"), strExpectedICNKM01Data.indexOf("</CdtItmId>"));
				expectedDbtItmIdIndex = expectedDbtItmId.indexOf(">");
				expectedDbtItmId = expectedDbtItmId.substring(++expectedDbtItmIdIndex);	
			}

			String actualDbtItmFrdStatus = strActualICN06KM01Data.substring(strActualICN06KM01Data.indexOf("<FrdDtcnRslt>"), strActualICN06KM01Data.indexOf("</FrdDtcnRslt>"));
			int actualDbtItmFrdStatusIndex = actualDbtItmFrdStatus.indexOf(">");
			actualDbtItmFrdStatus = actualDbtItmFrdStatus.substring(++actualDbtItmFrdStatusIndex);

			String expectedDbtItmFrdStatus = strExpectedICNKM01Data.substring(strExpectedICNKM01Data.indexOf("<FrdDtcnRslt>"), strExpectedICNKM01Data.indexOf("</FrdDtcnRslt>"));
			int expectedDbtItmFrdStatusIndex = expectedDbtItmFrdStatus.indexOf(">");
			expectedDbtItmFrdStatus = expectedDbtItmFrdStatus.substring(++expectedDbtItmFrdStatusIndex);

			boolean itemIdFlag = actualDbtItmId.equals(expectedDbtItmId);
			boolean frdStatusFlag = actualDbtItmFrdStatus.equals(expectedDbtItmFrdStatus);

			if(itemIdFlag && frdStatusFlag)
			{
				publishResults(itemIdFlag, workFlow + " file with ItemId "+ actualDbtItmId + " and Fraud status  "+actualDbtItmFrdStatus + " loaded correctly in Source table", 
						"Should see "+ workFlow + " file to load with ItemId "+ expectedDbtItmId + " and Fraud status as "+actualDbtItmFrdStatus ,
						"Check the "+ workFlow + " file load in Source table");
				msg06KM_05KM_Log.info(workFlow + " file with ItemId "+ actualDbtItmId + " and Fraud status  "+actualDbtItmFrdStatus + " loaded correctly in Source table");
				fraudResultFromKappa.put(actualDbtItmId, actualDbtItmFrdStatus);
			}else {
				publishResults(itemIdFlag, workFlow + " file with ItemId "+ actualDbtItmId + " and Fraud status  "+actualDbtItmFrdStatus + " not loaded/matches with expected", 
						"Should see "+ workFlow + " file to load with ItemId "+ expectedDbtItmId + " and Fraud status as "+actualDbtItmFrdStatus ,
						"Check the "+ workFlow + " file load in Source table");	
				msg06KM_05KM_Log.error(workFlow + " file with ItemId "+ actualDbtItmId + " and Fraud status  "+actualDbtItmFrdStatus + " not loaded/matches with expected");
			}
		}while(expectedData.indexOf("<MSF08RtpFrdChkRsp>") > 0 || expectedData.indexOf("<MSF06TsetFrdChkRsp>") > 0 );
		msg06KM_05KM_Log.info("fraudResultFromKappa : "+ fraudResultFromKappa );	
	}

	/*
	 * 2. Method to check ISO File Insert of 06KM01 in Source Table
	 */
	public static void checkISOFile06KM01InsertInSourceTable(String workFlow, List<String> itemIds) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		boolean flag = true;
		String query = "SELECT ICNContent = CAST(ICNContent AS XML) FROM " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "where SourceID = '" +getICNSourceID() + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			fraudResponseContentOfKappaFile = resultSet.getString("ICNContent");
		}	

		for(String eachItemId : itemIds)
		{				
			if(!(fraudResponseContentOfKappaFile.indexOf(eachItemId) > 0))
			{
				flag = false;
				validationErrorInformation("Item Id " +eachItemId + " not present in Fraud Response ICN Content in Source table for workflow "+ workFlow);
				msg06KM_05KM_Log.error("Item Id " +eachItemId + " not present in Fraud Response ICN Content in Source table for workflow "+ workFlow);
			}
		}

		if(!(flag))
		{
			validationErrorInformation("Fraud Response ICN Content in Source table for workflow "+ workFlow + " not matches ");
			msg06KM_05KM_Log.error("Fraud Response ICN Content in Source table for workflow "+ workFlow + " not matches ");
		}else {
			validationStepInformation("Fraud Response ICN Content in Source table for workflow "+ workFlow + " is proper with itemId "+ itemIds);
			msg06KM_05KM_Log.info("Fraud Response ICN Content in Source table for workflow "+ workFlow + " is proper with itemId "+ itemIds);
		}
	}
}
