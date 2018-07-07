package com.ics.tests.fred;

import java.util.ArrayList;
import org.testng.annotations.Test;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.fred.testScenarios.ValidateAIXMLExtractFileLoadedIntoDatabase;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateAIXMLTags  extends GenericMethodUtilis{
				
			private static String templateFilePath = ICSPropertiesConfig.getsqlTemplateFilePath();
			private static String dbServerName = ICSPropertiesConfig.getFREDDBServerName();
			private static String fredDatabaseName = ICSPropertiesConfig.getFREDDatabaseName();
			private static String strICNFilePath =ICSPropertiesConfig.getICNFilePathKey();
			private static String strAIXMLArchiveFldrPathKey =ICSPropertiesConfig.getAIXMLArchiveFldrPathKey();
			private static String strAIXMLFilePath =ICSPropertiesConfig.getaixmlFilePthKey();
			private static String aixmlTempFileName;
			
			private static String strICNXMLFileName="ICNOutput";
			ArrayList<String> returnActExpctdVal=new ArrayList<>();
			boolean returnExpectedReslt=false;
		
		
			@Test
			public void validateAIXMLTagsVal() throws Exception
			{
			
			//ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileMovedToArchieveFldr(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileLoadedIntoDabatase(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			//ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLJobBusinessDateValWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLJobWorkTypeDetailsWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
		
			ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLBlockNumberDetailsWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLBlockImageTypeDetailWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			returnActExpctdVal=ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLBatchDetailsWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			returnExpectedReslt = Component.verifyEquals(returnActExpctdVal.get(0), returnActExpctdVal.get(1), "BatchDetails of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, returnActExpctdVal.get(0), returnActExpctdVal.get(1), "BatchDetails of the aixml file is loaded into database.");

			ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLItemDetailsWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			returnActExpctdVal=ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLCustomFieldsDetailsWithDB(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			returnExpectedReslt = Component.verifyEquals(returnActExpctdVal.get(0), returnActExpctdVal.get(1), "ItemID fields of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, returnActExpctdVal.get(0), returnActExpctdVal.get(1), "ItemID of the aixml file is loaded into database.");

			String returnExpctdVal=null;
			returnExpctdVal=ValidateAIXMLExtractFileLoadedIntoDatabase.getICNOutputFileFromDatabase(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			publishResults(Integer.valueOf(returnExpctdVal).equals(1), (Integer.valueOf(returnExpctdVal).equals(1))?"One ICNOutput File is Present":"More Than one file is present", "ICNOutput File Got Generated in FRED database", "ICNOutput File Count Verifying");

			String icnEntityState =ValidateAIXMLExtractFileLoadedIntoDatabase.getEntityStateICNOutputFileFromDatabase(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			publishResults(icnEntityState!=null,(icnEntityState!=null)?"Entity state value is present":"Entity state is null","Entity state value is present","Verifying entity state");
			
			ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLItemHistoryDetailsWithDB(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			ValidateAIXMLExtractFileLoadedIntoDatabase.validateRepairedAIXMLItemAmountWithDBVal(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			ValidateAIXMLExtractFileLoadedIntoDatabase.verifyErrorLog06MF01(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			/*if(!(returnActExpctdVal.size()==0))
			{
				returnExpectedReslt = Component.verifyEquals(returnActExpctdVal.get(0), returnActExpctdVal.get(1), "ErrorLog is not present in database.");
				publishResults(returnExpectedReslt, returnActExpctdVal.get(0), returnActExpctdVal.get(1), "ErrorLog is present for aixml file loaded into database");
			}
			else 
				System.out.println("No error found in ErrorLog table");		
			 		*/
	
	}
}