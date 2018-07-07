package com.ics.mo.xmlFiles.icnContent;

import com.ics.mo.xmlFiles.isoContent.Msg06;

//<copyright  file="Msg06MA01.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
* Module Name -  Msg06MA01.java
************************************************************************ 
* Date -  26/04/2017
************************************************************************ 
* Created By -  MuluguUm
************************************************************************ 
* Description - Required Generic Functionalities to check Msg06MA01
***********************************************************************/
public class Msg06MA01 extends Msg06{
	protected static String actualSourceTableDataOf06MA01;

	/*
	 * 1. Check ICN Content in MA01 record in Source Table
	 */
	public static void checkICNInMA01ISOFileInSourceDB(String isoContentData, String workFlow, String entityStatus, String sourceID) throws Exception
	{
		String query = "Select ISOContent = CAST(ISOContent AS XML) from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "where  SourceID = '" + sourceID + "' and MessageType = '" + workFlow + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 		
		actualSourceTableDataOf06MA01 = getICSRetrieveColumnValue(resultSet);
		String tempICNData = "";
		tempICNData = actualSourceTableDataOf06MA01.substring(actualSourceTableDataOf06MA01.indexOf("<ICN>")+"<ICN>".length(), actualSourceTableDataOf06MA01.indexOf("</ICN>"));
		String validationHeader = "Check on ICN content append in the ISO content file for workflow " + workFlow;
		String comparisonReport = "Please find below the ICN details appended in ISO file as mentioned in above step:";
		if(!(tempICNData.equals("")))
		{
			publishResults(true, "ICN content appended to the ISO content of workflow "+ workFlow + " with SourceID "+ sourceID,
					"Should see ICN content appends to the ISO content of workflow "+ workFlow,
					validationHeader);
		}else {
			publishResults(false, "ICN content not appended to the ISO content of workflow "+ workFlow + " with SourceID "+ sourceID,
					"Should see ICN content appends to the ISO content of workflow "+ workFlow,
					validationHeader);
		}
		validationStepInformation(comparisonReport);
	}

	/*
	 * 2. Method to get SourceID of MA01 Loaded in Source Table
	 */
	public static String getActualMA01DataLoadedInSourceTable()
	{
		return actualSourceTableDataOf06MA01;
	}
}
