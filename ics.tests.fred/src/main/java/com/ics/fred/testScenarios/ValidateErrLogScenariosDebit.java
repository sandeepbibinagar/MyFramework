package com.ics.fred.testScenarios;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateErrLogScenariosDebit extends ICSDBUtilis{

	@Test
	public void validateDebitErrLog() throws Exception{
		
		String filepath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\";
		String destFilePath="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\MF01InputFolder\\";
		String fileName ="TC_MSG05_CR_Invalid";
		String fileTobeCreated =fileName+"_Actual";
		String fileExtension=".sql";
		String fileTobeCopied =fileName+"_Actual.sql";
		String dbSQLFileInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-32-V\\SQL001 -d dbName -i "+filepath + fileName + "_Actual.sql ";		
		String extractID =  generateExtractId("CRED");		
		 
		createFileFromTemplate(filepath, fileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
		createFileFromTemplate(filepath, fileTobeCreated,".sql", "BUSINESSDATETOBEREPLACE", "2016-09-22");
		sqlCommandExecution(dbSQLFileInjectCommand);
		System.out.println("Invalid SQL File has been loaded into database successfully");		
	//	copyFileFromOneLocationToAnother(filepath,destFilePath,fileTobeCopied);
	//	createFileFromTemplateSplit(destFilePath,fileTobeCreated,fileExtension);	
	}
	//Call ExtractID validation class file
	//Call BusinessDateValidation file
	//Call AIXMLduplicatefilename
	//Call errorlog
	
	public static void verifyErrorLogMF01SendQueue(String dbServerName,String fredDatabaseName,String dbConfigBusinessDateSQL,
			String fredExtractIDValidationWithBatchMF01,String fredBuisnessDateDataType,String fredBusinessDateValidationWithDBInErrLog,String sqlCreditFileType,
			String sqlCreditTemplateFileName,String templateFilePath,String sqlTemplateExtractIDToBeReplaced,String sqlTemplateBusinessDateToBeReplaced,String sqlCreditExtractIDSameValueTemplateFileName,String strX9FileCreatedQueryValidationFromDatabase,String mf01SendQueueQuery,
			String sqlCreditExtractIDLenIncorrectValTemplateFileName,String sqlCreditExtractIDLenIncorrectValTemplateFileName1,String fredBusinessDateValidationWithBatchMF01,
			String sqlBusinessDateSecondCreditTemplateFileName,String sqlBusinessDateCreditTemplateFileName,String aixmlTempFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey,String strICNFilePath,String strICNXMLFileName) throws Exception{
	
	FREDCIX9ExtractIDValidation.validAndInvalidCIExtractIDValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
			fredExtractIDValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlCreditFileType,
			sqlCreditTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
			sqlTemplateBusinessDateToBeReplaced,sqlCreditExtractIDSameValueTemplateFileName,strX9FileCreatedQueryValidationFromDatabase,mf01SendQueueQuery,
			sqlCreditExtractIDLenIncorrectValTemplateFileName,sqlCreditExtractIDLenIncorrectValTemplateFileName1);
		
	
	FREDCIMF01BusinessDateValidation.validAndInvalidCIBusinessDateValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
			fredBusinessDateValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlCreditFileType,
			sqlBusinessDateCreditTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
			sqlTemplateBusinessDateToBeReplaced,sqlBusinessDateSecondCreditTemplateFileName);
	
	
	
	ValidateAIXMLExtractFileLoadedIntoDatabase.verifyErrorLog06MF01(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
}
	
	public static void validateAIXMLInvalidFile(String dbServerName,String fredDatabaseName,String templateFilePath,String aixmlTempFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey,String strICNFilePath,String strICNXMLFileName) throws Exception{
	
		ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLValuesWithDB(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
	}
	
	
	public void createFileFromTemplateSplit(String destFilePath, String fileName, String fileExtension) throws Exception
	{		
		
		String content = readDataOfFileStream(getFileStreamWithExtension(destFilePath, fileName, fileExtension));
		String[] splitSQLContent = content.split("'");
		for(int i=0;i<splitSQLContent.length;i++){
			System.out.println("Splitted SQL data:"+i+" "+splitSQLContent[i]);
		}
		
		writeDataOfFileStream(getFileStreamWithExtension(destFilePath, fileName, ".xml"), splitSQLContent[1], false);
		
	}
	
	public void copyFileFromOneLocationToAnother(String srcFilePath,String destFilePath,String fileName) throws Exception{
	File srcFile = new File(srcFilePath+fileName);
	File destFile = new File (destFilePath+fileName);
	FileUtils.copyFile(srcFile, destFile);
	}
	
	public static void getErrorLogMsg(String filePath,String fileName,String dbServerName,String fredDBName) throws Exception
	{	
		//2017-06-12 16:43:53.7308275
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss.sssssss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		System.out.println("strDate " +strDate);
				
		System.out.println("Verify ErrorLog Table for any error occurs");
		System.out.println("Check if processed "+fileName+" MF01 file is present in ErrorLog Table");
		//LocalDate date = LocalDate.now();
		String errorLogQuery="SELECT ErrorMessage FROM Base.ErrorLog where OccuredDateTime='"+strDate+"'";
	//	String errorLogQuery="SELECT ErrorMessage FROM Base.ErrorLog";
		System.out.println(errorLogQuery);
		ResultSet rsErrLog = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, errorLogQuery);
		ArrayList<String> actualErrLog = GenericMethods.getICSRetrieveErrColumnValues(rsErrLog,fileName);
		
		System.out.println("Error Log found for "+fileName+" in ErrorLog Table :"+actualErrLog);
		
		//Search Error in Errortable
		//if()
		//Search err in error queue
		//search error in MF01 send queue
		
		
		
		
		
	//	ICSGenericUtils.setPITOutputTestData(fredOutputFilePath,sheetName,sheetName,"fredAIXMLErrLog",actualErrLog);
		if(!(actualErrLog.size()==0))
		{
			boolean returnExpectedReslt = Component.verifyEquals(actualErrLog.get(0), actualErrLog.get(1), "ErrorLog is not present in database.");
			publishResults(returnExpectedReslt, actualErrLog.get(0), actualErrLog.get(1), actualErrLog+" ErrorLog is present for "+fileName+" aixml file loaded into database");
		}
		else 
			System.out.println("No error found in ErrorLog table");	
		
		
	}
		
	
	
	public static File getFileStreamWithExtension(String filePath, String fileName, String extension)
	{
		return new File(filePath + fileName + extension);
	}
	
	public static void writeDataOfFileStream(File fileStream, String data, boolean overWrite) throws IOException
	{
		FileUtils.writeStringToFile(fileStream, data, overWrite);
	}
	
	public static String readDataOfFileStream(File fileStream) throws IOException
	{
		return FileUtils.readFileToString(fileStream);
	}

	
	/*public static void main(String[] args){
		// TODO Auto-generated method stub

		String filepath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\";
		String destFilePath="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\MF01InputFolder\\";
		String fileName ="TC_MSG05_CR";
		String fileTobeCreated =fileName+"_Actual";
		String fileExtension=".sql";
		String fileTobeCopied =fileName+"_Actual.sql";
		String dbSQLFileInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-32-V\\SQL001 -d dbName -i "+filepath + fileName + "_Actual.sql ";		
		String extractID =  ICSDBUtilis.generateExtractId("CRED");		
		 
	try {
			ICSDBUtilis.createFileFromTemplate(filepath, fileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
		
		ICSDBUtilis.createFileFromTemplate(filepath, fileTobeCreated,".sql", "BUSINESSDATETOBEREPLACE", "2016-09-22");
		ICSDBUtilis.sqlCommandExecution(dbSQLFileInjectCommand);
		System.out.println("SQL File has been loaded into database successfully ");
		
		ValidateErrLogScenariosDebit valErrLogObj = new ValidateErrLogScenariosDebit();
		
		valErrLogObj.copyFileFromOneLocationToAnother(filepath,destFilePath,fileTobeCopied);
		valErrLogObj.createFileFromTemplateSplit(destFilePath,fileTobeCreated,fileExtension);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
*/	
	
	
	public static ArrayList<String> getICSRetrieveErrColumnValues(ResultSet resultSet,String filename) throws Exception
	{
	
		String resultSetValue=null;
		String businessDateFaileMsg=filename+":"+" "+filename+" :Businessdate validation failed.";
		String blockNumberAlreadyPrcsdErr=filename+" : This block number in the file is already processed.";
		String errorMessageBusinessdateValidationFailedFileName =filename+" :Businessdate validation failed.";
		String errorMessageDateTimeCnvFailed ="Conversion failed when converting date and/or time from character string.";
		String errorMessageDateTimeCnvFailedWithFileName =filename+": "+errorMessageDateTimeCnvFailed;
		String  strCnvFailed="Conversion failed when converting date and/or time from character string.";
		 
		ArrayList<String> returnVal= new ArrayList<>();
	
		while(resultSet.next())
		{	
			resultSetValue  = resultSet.getString(1);
			if(resultSetValue.contains(businessDateFaileMsg))
			{
				returnVal.add(resultSetValue);
				returnVal.add(businessDateFaileMsg);
			}
			else if(resultSetValue.contains(strCnvFailed))
			{ 
				returnVal.add(resultSetValue);
				returnVal.add(strCnvFailed);
			}
			else if(resultSetValue.contains(errorMessageBusinessdateValidationFailedFileName))
			{ 
				returnVal.add(resultSetValue);
				returnVal.add(errorMessageBusinessdateValidationFailedFileName);
			}
			else if(resultSetValue.contains(errorMessageDateTimeCnvFailed))
			{ 
				returnVal.add(resultSetValue);			
				returnVal.add(errorMessageDateTimeCnvFailed);
				
			}
			else if(resultSetValue.contains(errorMessageDateTimeCnvFailedWithFileName))
			{ 
				returnVal.add(resultSetValue);
				returnVal.add(errorMessageDateTimeCnvFailedWithFileName);
				
			}
			else if(resultSetValue.contains(blockNumberAlreadyPrcsdErr))
				 { 
					returnVal.add(resultSetValue);
					returnVal.add(blockNumberAlreadyPrcsdErr);
				 }
				else 
					{
					System.out.println("No Error found for "+filename+" in Batch Error Log Table");
					}
		
		}

		return returnVal;
	}

	
}


