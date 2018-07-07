package com.ics.mo.xmlFiles.icnContent;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.ics.mo.xmlFiles.isoContent.Msg06MD01;

//<copyright  file="Msg06DM01.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
* Module Name -  Msg06DM01.java
************************************************************************ 
* Date -  26/04/2017
************************************************************************ 
* Created By -  MuluguUm
************************************************************************ 
* Description - Required Generic Functionalities to check Msg06DM01
***********************************************************************/
@SuppressWarnings("unused")
public class Msg06DM01 extends Msg06FM_Msg05FM {
	protected static final Logger msg06DM01Log = Logger.getLogger(Msg06DM01.class.getSimpleName()) ;
	/*
	 * 1. Check 06DM01 file record In Receive Staging Table
	 */
	public static void checkICNFile06DM01InsertInReceiveStaging(String inputFilePath, String inputFileName, String outputFilePath, String workFlow) throws Exception
	{
		checkSourceIdInsertInReceiveStagingTable(workFlow);
		if(actualRowIDMsgFM01.equals(""))
		{
			msg06DM01Log.error("WorkFlow "+workFlow + " not loaded in ReceiveStaging table " );
			publishResults(false, "WorkFlow "+ workFlow + " ICN content not loaded in ReceiveStaging table " ,
					"Should see workFlow "+ workFlow + " ICN content to load in ReceiveStaging table",
					"Check workFlow "+ workFlow + " in ReceiveStaging table");	
		}else {
			checkICNFile06DM01InsertedDataInReceiveStaging(inputFilePath, inputFileName, outputFilePath, workFlow);
			msg06DM01Log.info("WorkFlow "+workFlow + " loaded in ReceiveStaging table with RowId/Msg_Id "+ actualRowIDMsgFM01 );
			publishResults(true, "WorkFlow "+ workFlow + " ICN content loaded in ReceiveStaging table with RowId/Msg_Id "+ actualRowIDMsgFM01 ,
					"Should see workFlow "+ workFlow + " ICN content to load in ReceiveStaging table",
					"Check workFlow "+ workFlow + " in ReceiveStaging table");	
		}
	}

	/*
	 * 2. Check ICN File DEW Data Insert in Receive Staging 
	 */
	protected static void checkICNFile06DM01InsertedDataInReceiveStaging(String inputFilePath, String inputFileName, String outputFilePath, String workFlow) throws IOException, InterruptedException
	{
		msg06DM01Log.info("Validating ICN data inserted in ReceiveStaging Table for workflow "+  workFlow + "with RowId "+ actualRowIDMsgFM01);	
		String actualContentPath = outputFilePath + "ActualICNContent_" + workFlow + ".txt";
		writeToFile(actualContentPath, actualICNContentMsgFM01InReceiveStaging);
		String xmlExtensionActualData = getRenamedFileExtenstion(actualContentPath, ".txt", ".xml");
		String strExpectedICN06FM01Data = FileUtils.readFileToString(new File(inputFilePath + inputFileName));
		String strActualICN06FM01Data = FileUtils.readFileToString(new File(xmlExtensionActualData));

		validationStepInformation("Detailed comparison report for workflow " + workFlow + " with Msgs_Id " + actualRowIDMsgFM01 + " in ReceiveStaging table as below:");
		//Compare Core Section
		validationStepInformation("Check on Core section");	
		msg06DM01Log.info("Check on Core section");
		checkActualICNCoreDatawithExpected(strActualICN06FM01Data, workFlow, strExpectedICN06FM01Data);

		initialStateFromFred = new LinkedHashMap<String, String>();
		//Compare Entities Section
		validationStepInformation("Check on Entities section");
		msg06DM01Log.info("Check on Entities section");
		checkActualICNEntitiesSectionWithExpected(strActualICN06FM01Data, workFlow, strExpectedICN06FM01Data);
	}
}
