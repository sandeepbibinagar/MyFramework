package com.ics.fred.testScenarios;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateAIXMLImageICNOutput extends ICSDBUtilis{


	
	public static void aixmlCorruptImageValidation(String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempFileName,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
		//Validate AIXML service is up and running
		//Process corruted Image AIXML file
		//Check exception log for corrutpted image
		//validate image tag is present in icn file
		
		String queryVal ="SELECT TOP (1000) [ServiceStatus] FROM [FREDPIT].[Base].[WindowsServiceStatus]";
		ResultSet resultset =getICSDBServerConnection(dbServerName, fredDatabaseName, queryVal);
		String actualAIXMLSeriveVal = GenericMethods.getICSRetrieveColumnValues(resultset);
		System.out.println("actualAIXMLSeriveVal "+actualAIXMLSeriveVal);
		
		if (actualAIXMLSeriveVal.equals("0")){
			
			System.out.println("AIXMLService is not runnning "+actualAIXMLSeriveVal);
			//Starts the service
			//sqlFileInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-32-V\\SQL001 -d FREDPIT -i "+templateFilePath + sqlCreditTemplateFileName + "_Actual.sql ";
			String strCommand ="SC \\\\129.227.33.34 start iPSL.iCE.Fred.AIXMLLoad";
			//strCommand ="net START iPSL.iCE.Fred.AIXMLService";
			sqlCommandExecutionTest(strCommand);
			
			System.out.println("AIXMLService is not running........."+actualAIXMLSeriveVal);
			publishResults(actualAIXMLSeriveVal.equals("0"), "AIXMLService is not running...."+actualAIXMLSeriveVal, "AIXMLService is not running...."+actualAIXMLSeriveVal, "AIXMLService Status validation performed.");
			//ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
		}
		else
		{
			//
			System.out.println("AIXMLService is up and running...."+actualAIXMLSeriveVal);
			//ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			publishResults(!(actualAIXMLSeriveVal.equals("0")), "AIXMLService is up and running...."+actualAIXMLSeriveVal, "AIXMLService is up and running...."+actualAIXMLSeriveVal, "AIXMLService Status validation performed.");
			
		}
		
		
	
	
		boolean flag =ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileMovedToArchieveFldr(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
		if(flag){
			try{
					ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileLoadedIntoDabatase(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
					verifyExceptionLogAIXML(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
					String icnImageFile = ValidateAIXMLExtractFileLoadedIntoDatabase.getICNOutputFileFromDatabase(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
					
					System.out.println("icnImageFile "+icnImageFile);
					ValidateAIXMLExtractFileLoadedIntoDatabase.verifyErrorLog06MF01(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
					/*itemId = ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLCustomFieldsDetailsWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
					System.out.println("itemid "+itemId.get(0));
					System.out.println("itemid "+itemId.get(0));
					System.out.println("itemid "+itemId.get(0));
					System.out.println("itemid "+itemId.get(0));*/
			}
			catch(Exception e){
					System.out.println(e.getMessage());
					//validationErrorInformation(e.getMessage());
				}
			}
		else 
		{
			System.out.println("File not moved into archive");
			//check errror log and rejected folder
			ValidateAIXMLExtractFileLoadedIntoDatabase.verifyErrorLog06MF01(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
		}
	
	}
	
	
	public static void verifyExceptionLogAIXML(String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempFileName,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
		ArrayList<String> itemId = new ArrayList<>();
		itemId = ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLCustomFieldsDetailsWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
		
		System.out.println("Verify ErrorLog Table for any error occurs");
		System.out.println("Check if processed processed AIXML is present in ExceptionLog Table");
	//	LocalDate date = LocalDate.now();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sssssss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		System.out.println("strDate " +strDate);
				
		String frontImgExceptionLogQuery="select Message from base.ExceptionLog where ApplicationErrorCode='-20009'";
		String rearImgExceptionLogQuery="select Message from base.ExceptionLog where ApplicationErrorCode='-20010'";
		
		System.out.println("FrontImage Exception Log Query "+frontImgExceptionLogQuery);
		System.out.println("RearImage Exception Log Query "+rearImgExceptionLogQuery);
		
		ResultSet rsErrLog = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDatabaseName, frontImgExceptionLogQuery);
		System.out.println("AIXML item id size "+itemId.size());
		for(int i=0;i<itemId.size();i++){		
			
			System.out.println("AIXML item id size "+itemId.get(i));
		//String actualErrLog = GenericMethods.getICSRetrieveErrLog(rsErrLog,itemId.get(i));	
		String actualErrLog = GenericMethods.getICSRetrieveColumnValues(rsErrLog);
		System.out.println("Error Log found for  in ExceptionLog Table :"+actualErrLog);
		boolean returnExpectedReslt = Component.verifyTrue(!(actualErrLog.length()==0), "ErrorLog is not present in database.");
		if(!(actualErrLog.length()==0))
		{
		
			publishResults(returnExpectedReslt, actualErrLog, actualErrLog, actualErrLog+" ErrorLog is present for  aixml file loaded into database");
		}
		else 
			{
			
			publishResults(actualErrLog.length()==0, (actualErrLog.length()==0)?"ErrorLog is not present in database.":"Issue Found!!","ErrorLog is not present in database.","ErrorLog is not present in database.");
		}
		}
		
	/*	ArrayList<String> actualErrLog = GenericMethods.getICSRetrieveErrColumnValues(rsErrLog,itemid);	
		System.out.println("Error Log found for "+aixmlFileNameCreated+" in ErrorLog Table :"+actualErrLog);

		boolean returnExpectedReslt = false;
		if(!(actualErrLog.size()==0))
		{
			returnExpectedReslt = Component.verifyEquals(actualErrLog.get(0), actualErrLog.get(1), "ErrorLog is present in database.");
			publishResults(returnExpectedReslt, actualErrLog.get(0), actualErrLog.get(1), actualErrLog+" ErrorLog is present for "+aixmlFileNameCreated+" aixml file loaded into database");
		}
		else 
			{
			returnExpectedReslt = Component.verifyTrue(actualErrLog.size()==0, "ErrorLog is not present in database.");
			publishResults(returnExpectedReslt, (actualErrLog.size()==0)?"ErrorLog is not present in database.":"Issue Found!!","ErrorLog is not present in database.","ErrorLog is not present in database.");
		}*/
		
		
	}

	
	public static void x9CorruptImageValidation(String dbServerName,String fredDBName,String templateFileType,String filepath,String destFilePath,String fileName,String sqlTemplateExtractIDToBeReplaced,String sqlTemplateBusinessDateToBeReplaced,String dbItemImageDataTypeQuery,String dbImageFetchQuery2,String sqlConfigBusinessDate,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempFileName,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
			
			String fileTobeCreated =fileName+"_Actual";
			
			String dbSQLFileInjectCommand = "cmd /c sqlcmd -S "+dbServerName+" -d "+fredDBName+" -i "+filepath + fileName + "_Actual.sql ";		
			String extractID =  generateExtractId(templateFileType);	
			 System.out.println(dbSQLFileInjectCommand);
			createFileFromTemplate(filepath, fileName,".sql", sqlTemplateExtractIDToBeReplaced, extractID);
			String actualConfigDate =GenericMethods.getActualConfigDate(dbServerName,fredDBName,sqlConfigBusinessDate);
			createFileFromTemplate(filepath, fileTobeCreated,".sql", sqlTemplateBusinessDateToBeReplaced, actualConfigDate);
			sqlCommandExecution(dbSQLFileInjectCommand);
			System.out.println("SQL File has been loaded into database ");
			validationStepInformation(" SQL File has been loaded into database ");
			ValidateMF01DataLoadedIntoDatabase.verifyExceptionLogX9CorruptImage( dbServerName, fredDBName, templateFileType, filepath, destFilePath, fileName, sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced, dbItemImageDataTypeQuery, dbImageFetchQuery2, sqlConfigBusinessDate, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempFileName, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
		//	ValidateMF01DataLoadedIntoDatabase.verifyExceptionLogX9SplitImage( dbServerName, fredDBName, templateFileType, filepath, destFilePath, fileName, sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced, dbItemImageDataTypeQuery, dbImageFetchQuery2, sqlConfigBusinessDate, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempFileName, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
			//verifyErrorQueue(dbServerName,fredDBName,extractID);
	}
	
	public static void x9SplitImageValidation(String dbServerName,String fredDBName,String templateFileType,String filepath,String destFilePath,String fileName,String sqlTemplateExtractIDToBeReplaced,String sqlTemplateBusinessDateToBeReplaced,String dbItemImageDataTypeQuery,String dbImageFetchQuery2,String sqlConfigBusinessDate,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempFileName,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
		
		String fileTobeCreated =fileName+"_Actual";
		
		String dbSQLFileInjectCommand = "cmd /c sqlcmd -S "+dbServerName+" -d "+fredDBName+" -i "+filepath + fileName + "_Actual.sql ";		
		String extractID =  generateExtractId(templateFileType);	
		 System.out.println(dbSQLFileInjectCommand);
		createFileFromTemplate(filepath, fileName,".sql", sqlTemplateExtractIDToBeReplaced, extractID);
		String actualConfigDate =GenericMethods.getActualConfigDate(dbServerName,fredDBName,sqlConfigBusinessDate);
		createFileFromTemplate(filepath, fileTobeCreated,".sql", sqlTemplateBusinessDateToBeReplaced, actualConfigDate);
		sqlCommandExecution(dbSQLFileInjectCommand);
		System.out.println("SQL File has been loaded into database ");
		validationStepInformation(" SQL File has been loaded into database ");
	//	ValidateMF01DataLoadedIntoDatabase.verifyExceptionLogX9CorruptImage( dbServerName, fredDBName, templateFileType, filepath, destFilePath, fileName, sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced, dbItemImageDataTypeQuery, dbImageFetchQuery2, sqlConfigBusinessDate, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempFileName, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
		ValidateMF01DataLoadedIntoDatabase.verifyExceptionLogX9SplitImage( dbServerName, fredDBName, templateFileType, filepath, destFilePath, fileName, sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced, dbItemImageDataTypeQuery, dbImageFetchQuery2, sqlConfigBusinessDate, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempFileName, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
		//verifyErrorQueue(dbServerName,fredDBName,extractID);
}
	
	public static void verifyErrorQueue(String dbServerName,String fredDBName,String extractId) throws Exception{
		String query = "SELECT TOP 2 CAST(message_body AS NVARCHAR(MAX)) FROM "+fredDBName+".[Base].[//FRED/ErrorQueue] where validation='X' order by message_enqueue_time desc";
		ResultSet rsErrQ = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, query);
		String errQueueMsg = GenericMethods.getICSRetrieveErrLog(rsErrQ,extractId);
		
		publishResults(errQueueMsg!=null,(errQueueMsg!=null)?"Error log occured in ErrorQueue":"Error detail is not present","Error log occured in ErrorQueue","Corrupted Image validation performed.");
	}
	
	

}
