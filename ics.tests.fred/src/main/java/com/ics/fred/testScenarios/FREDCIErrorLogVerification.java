//************* TC-65877*********************//

package com.ics.fred.testScenarios;

import java.sql.ResultSet;
import java.util.ArrayList;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;

public class FREDCIErrorLogVerification {

	
	
	public static void verifyErrorLog06MF01(String serverName,String dbName,String templateFilePath,String credittemplateFileName,
			String debitTemplateFileName,String creditTemplateFileType,String debitTemplateFileType,String strBusinessDateValidationWithDBInErrLog) throws Exception{
		
		
		//DI_ MO1_510.xml: Error converting data type nvarchar to numeric. : in usp_Shred_AIXML_History
		
		
		//Execute Invalid data in 05MF01 File
		
		//ErrQueue Query
		String errQueueMsg = "SELECT casted_message_body = CASE message_type_name WHEN 'X' THEN CAST(message_body AS NVARCHAR(MAX)) "+
				"ELSE message_body END FROM [FRED_DB_Auto].[Base].[//FRED/MF01SendQueue] WITH(NOLOCK)";
		
		//SQL command execution
		 String dbSQLFileInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+templateFilePath + debitTemplateFileName + "_Actual.sql ";		
		 String extractID =  ICSDBUtilis.generateExtractId(debitTemplateFileType);		
		
		 //Execute Invalid Credit file
		 ICSDBUtilis.createFileFromTemplate(templateFilePath, debitTemplateFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
		// ICSDBUtilis.createFileFromTemplate(templateFilePath, templateFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
		 ICSDBUtilis.sqlCommandExecution(dbSQLFileInjectCommand);
		 
		 System.out.println("(05MF01) SQL File has been loaded into database successfully");
		 String fileName=debitTemplateFileName+"_Actual.sql";
		 System.out.println("Inserted FileName is"+fileName);
		
		ResultSet rsErrLog = ICSProductDBConnection.getICSDBServerConnection(serverName, dbName, strBusinessDateValidationWithDBInErrLog);
		ArrayList<String> actualErrLog = GenericMethods.getICSRetrieveErrColumnValues(rsErrLog,fileName);
		//String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
		System.out.println("Error Log found in Batch"+actualErrLog);
		
		ResultSet rsErrLoginErrQueue = ICSProductDBConnection.getICSDBServerConnection(serverName, dbName, errQueueMsg);
		ArrayList<String> actualErrLoginErrQueue = GenericMethods.getICSRetrieveErrColumnValues(rsErrLoginErrQueue,fileName);		
		System.out.println("Error Log found in ErrorQueue and XSD validation Failed"+actualErrLoginErrQueue);
		
		//Expected Error Messages in Error Log and ErrQueue
				String strExpected05MF01Msg ="05FM01Message";
				String strExpected06MF01Msg ="06MF01Message";
				String strExpected13MF01Msg ="13FM01Message";
				//String filename="outclearing_New-Image Data.xml";
				String BusinessDateFaileMsg=fileName+":"+" "+fileName+" :Businessdate validation failed.";
				String errQuery ="SELECT ErrorMessage FROM Base.ErrorLog where ErrorOccuredDate='2017-03-03'";
				String ErrorMessageBusinessdateValidationFailedFileName =fileName+" :Businessdate validation failed.";
				String ErrorMessageDateTimeCnvFailed ="Conversion failed when converting date and/or time from character string.";
				String ErrorMessageDateTimeCnvFailedWithFileName =fileName+": "+ErrorMessageDateTimeCnvFailed;
				String ErrorMessage05MF01 =fileName+"The message type '05FM01Message' is not part of the service contract.";
				String  strCnvFailed="Conversion failed when converting date and/or time from character string.";
		}
	
				
	
	
	
}
