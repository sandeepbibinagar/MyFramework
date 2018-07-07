package com.ics.fred.testScenarios;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadSQLFileUtil;
import com.ics.seleniumCoreUtilis.Component;

public class Validate13MF01LoadedColumnNames extends ICSProductDBConnection{

	public static void verify13MF01ColumnPresentInDatabase(String serverName,String dbName,String strImageDataExtractionFromDB,
			String templateCreditFileType,String templateCreditFileName,String templateFilePath,String strX9FileCreatedQueryValidationFromDatabase,
			String xsdFilesPath,String xsdFileName,String ConfigBusinessDate) throws Exception{
  	
		String dbSQLFileInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+templateFilePath + templateCreditFileName + "_Actual.sql ";		
	 
	 ResultSet resultSetConfigDateValue = getICSDBServerConnection(serverName, dbName, ConfigBusinessDate);
		String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(resultSetConfigDateValue);
		System.out.println("BusinessDate Config Table BusinessDate is"+actualConfigDateValue);
		String extractID =  ICSDBUtilis.generateExtractId(templateCreditFileType);
		System.out.println("ExtractID has been generated: "+extractID);
				
		ICSDBUtilis.createFileFromTemplate(templateFilePath, templateCreditFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
		ICSDBUtilis.createFileFromTemplate(templateFilePath, templateCreditFileName+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", actualConfigDateValue);
		
		
		
	String strFetchMF01BatchesAllCoulmnNameFromDatabase ="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS where TABLE_NAME='MF01_Batches'";
	ResultSet resultSetBatchColumns = getICSDBServerConnection(serverName, dbName, strFetchMF01BatchesAllCoulmnNameFromDatabase);
	String actualBatchMF01ColumnName = GenericMethods.getICSRetrieveColumnValues(resultSetBatchColumns);
	System.out.println("Display All column name from Batches table"+actualBatchMF01ColumnName);
	
	String strFetchMF01ItemsAllCoulmnNameFromDatabase ="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS where TABLE_NAME='MF01_Items'";
	ResultSet resultSetItemsColumn = getICSDBServerConnection(serverName, dbName, strFetchMF01ItemsAllCoulmnNameFromDatabase);
	String actualItemsMF01ColumnName = GenericMethods.getICSRetrieveColumnValues(resultSetItemsColumn);
	System.out.println("Display All column name from Items table"+actualItemsMF01ColumnName);
		
	//Compare Batch table column name with File columnName
		ICSDBUtilis.sqlCommandExecution(dbSQLFileInjectCommand);
		 String fileName=templateCreditFileName+"_Actual.sql";
	
	HashMap<String, String> expectedCoreTag = ValidateE031ICNCoreSubTags.getE031ICNCoreTagSubTagsValidation(xsdFilesPath,xsdFileName);
	System.out.println("Expected CoreTag :"+expectedCoreTag);
	
	String expectedE022ValueFromFile,expectedBusinessDateFrmFile,expectedExtractIDFrmFile,expectedProcessPIDFrmFile;
	String expectedExtMsgTypeFrmFile,expectedIntMsgTypeFrmFile,expectedMessageSrcFrmFile,expectedMessageDestFrmFile,expectedRecordCntsFromFile;
	
	String startBusinessDateTag = "<BusinessDate>";
		String endBusinessDateTag = "</BusinessDate>";
		expectedBusinessDateFrmFile= ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileName, startBusinessDateTag, endBusinessDateTag);
		//Component.assertEquals(actualBatchMF01ColumnName, expectedCoreTag, "Actual MF01_Batches and expected 13MF01 CoreTag Names are same");
		
		String startExtractIdTag = "<ExtractId>";
		String endExtractIdTag = "</ExtractId>";
		expectedExtractIDFrmFile= ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileName, startExtractIdTag, endExtractIdTag);
		System.out.println("ExtractID Tag Value from Database is :"+expectedExtractIDFrmFile);
		//Component.assertEquals(actualBatchMF01ColumnName, expectedCoreTag, "Actual MF01_Batches and expected 13MF01 CoreTag Names are same");		
		
		String startProcessingParticipantIDTag = "<ProcessingParticipantId>";
		String endProcessingParticipantIDTag = "</ProcessingParticipantId>";
		expectedProcessPIDFrmFile= ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileName, startProcessingParticipantIDTag, endProcessingParticipantIDTag);
		//Component.assertEquals(actualBatchMF01ColumnName, expectedCoreTag, "Actual MF01_Batches and expected 13MF01 CoreTag Names are same");
		
		String startExtMessageTypeTag = "<ExtMessageType>";
		String endExtMessageTypeTag = "</ExtMessageType>";
		expectedExtMsgTypeFrmFile= ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileName, startExtMessageTypeTag, endExtMessageTypeTag);
		//Component.assertEquals(actualBatchMF01ColumnName, expectedCoreTag, "Actual MF01_Batches and expected 13MF01 CoreTag Names are same");
		
		String startIntMessageTypeTag = "<IntMessageType>";
		String endIntMessageTypeTag = "</IntMessageType>";
		expectedIntMsgTypeFrmFile= ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileName, startIntMessageTypeTag, endIntMessageTypeTag);
		//Component.assertEquals(actualBatchMF01ColumnName, expectedCoreTag, "Actual MF01_Batches and expected 13MF01 CoreTag Names are same");
		
		String startMessageSourceTag = "<MessageSource>";
		String endMessageSourceTag = "</MessageSource>";
		expectedMessageSrcFrmFile= ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileName, startMessageSourceTag, endMessageSourceTag);
		//Component.assertEquals(actualBatchMF01ColumnName, expectedCoreTag, "Actual MF01_Batches and expected 13MF01 CoreTag Names are same");
		
		String startMessageDestinationTag = "<MessageDestination>";
		String endMessageDestinationTag = "</MessageDestination>";
		expectedMessageDestFrmFile= ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileName, startMessageDestinationTag, endMessageDestinationTag);
		//Component.assertEquals(actualBatchMF01ColumnName, expectedCoreTag, "Actual MF01_Batches and expected 13MF01 CoreTag Names are same");
		
		String startRecordCountsTag = "<RecordCounts>";
		String endRecordCountsTag = "</RecordCounts> ";
		expectedRecordCntsFromFile= ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileName, startRecordCountsTag, endRecordCountsTag);
		
		expectedE022ValueFromFile = expectedBusinessDateFrmFile+expectedExtractIDFrmFile+expectedProcessPIDFrmFile+expectedExtMsgTypeFrmFile+expectedIntMsgTypeFrmFile+expectedMessageSrcFrmFile+expectedMessageDestFrmFile+expectedRecordCntsFromFile;
		System.out.println("Expected data from File:"+expectedE022ValueFromFile);
		 actualBatchMF01ColumnName = "A"+"B"+"C"+"D";
		Component.assertEquals(actualBatchMF01ColumnName, expectedE022ValueFromFile, "Actual MF01_Batches and expected 13MF01 CoreTag Names are same");

		
	//	Component.assertEquals(actualItemsMF01ColumnName, expectedItemsTag, "Actual MF01_Items and expected 13MF01 ItemsTag Names are same");
	}
	
	/*public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		Validate13MF01LoadedColumnNames colValidation = new Validate13MF01LoadedColumnNames();
		String strImageDataExtractionSQL ="select MessageItemID,Image from Base.MF01_Items MT Join Base.MF01_Batches MB on MT.BatchDetailsID = MB.BatchDetailsID";
		colValidation.verify13MF01ColumnPresentInDatabase("GBIBC-DT30-33-V\\SQL001","Fred_DB_Auto",strImageDataExtractionSQL,strtemplateFilePath,strtemplateFileName);
	//	ReadImageValueFromSQLFile.getTagValue(strtemplateFilePath,strtemplateFileName);
	}*/
}
