package com.ics.fred.testScenarios;

import java.sql.ResultSet;
import java.util.Map;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadImageValueFromSQLFile;
import com.ics.seleniumCoreUtilis.Component;

public class Validate05MF01LoadedColumnNames extends ICSProductDBConnection{

	//verify file data is loaded successfully into db
	
	//verify image data is correctly populated at item level
	
	public static void verify05MF01ColumnPresentInDatabase(String serverName,String dbName,String dbImageFetchQuery2,
			String templateCreditFileType,String templateCreditFileName,String templateFilePath,
			String strX9FileCreatedQueryValidationFromDatabase,String dbConfigBusinessDateSQL) throws Exception{
	 
	 String dbSQLFileInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+templateFilePath + templateCreditFileName + "_Actual.sql ";		
	 String extractID =  ICSDBUtilis.generateExtractId(templateCreditFileType);		
				 
	 ICSDBUtilis.createFileFromTemplate(templateFilePath, templateCreditFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
	 ICSDBUtilis.sqlCommandExecution(dbSQLFileInjectCommand);
	 System.out.println("(05MF01) SQL File has been loaded into database successfully");
	 String fileName=templateCreditFileName+"_Actual.sql";
	 
     // Retrieve data from dabatabase
	 
	 String strFetchMF01ItemsAllCoulmnNameFromDatabase ="SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS where TABLE_NAME='MF01_Items'";
		ResultSet resultSetItemsColumn = getICSDBServerConnection(serverName, dbName, strFetchMF01ItemsAllCoulmnNameFromDatabase);
		String actualItemsMF01ColumnName = GenericMethods.getICSRetrieveColumnValues(resultSetItemsColumn);
		System.out.println("Display All column name from Items table"+actualItemsMF01ColumnName);
			
	 
	 // Read all image value from database at item level
	 
	 ReadImageValueFromSQLFile readSQLFile = new ReadImageValueFromSQLFile();
	 Map<String,String> mapTagValuesFromFile =readSQLFile.getTagValue(templateFilePath, fileName);
	 GenericMethods genericObj = new GenericMethods();
	 			 
	 String dbDataLoadedIntoDBQuery = dbImageFetchQuery2+" where ExtractID ='"+extractID+"'";
	 System.out.println("Item Image Column Values of 05MF01 Message is :"+dbDataLoadedIntoDBQuery);
	 ResultSet resultSetColumnValue = getICSDBServerConnection(serverName, dbName, dbDataLoadedIntoDBQuery);

	 Map<String,String> mapTagValuesFromResultSet =genericObj.getMessageItemIdAndImageValue(resultSetColumnValue);
	 
	 for(Map.Entry<String,String> entry:mapTagValuesFromFile.entrySet()){
		 String fileItemId = entry.getKey();
		 String fileImageValue =entry.getValue();
		 String getImageValueFromResultSetByFileItemId = mapTagValuesFromResultSet.get(fileItemId);				 
		 Component.assertEquals(fileImageValue, getImageValueFromResultSetByFileItemId, "Actual and Expected Loaded Image values (05MF01 or 06MF01)of the messages are same.");
	 }
	
	
	}
}