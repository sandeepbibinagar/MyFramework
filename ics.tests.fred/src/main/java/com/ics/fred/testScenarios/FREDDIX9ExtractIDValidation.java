package com.ics.fred.testScenarios;


	import java.io.IOException;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import com.ics.externalFactoryUtilis.ICSDBUtilis;
	import com.ics.externalFactoryUtilis.ICSProductDBConnection;
	import com.ics.fred.common.GenericMethods;
	import com.ics.seleniumCoreUtilis.Component;
	import com.ics.fred.common.ReadSQLFileUtil;


	public class FREDDIX9ExtractIDValidation  extends ICSProductDBConnection {
	
		public static void validAndInvalidDIExtractIDValidation(String serverName,String dbName,
				String sqlConfigBusinessDate,String dbExtractIDValidationWithBatchMF01Table,
				String dbBuisnessDateDataTypeQuery,String dbBusinessDateValidationWithDBInErrLog,
				String templateFileType,String templateFileName,String templateFilePath,
				String templateExtractIDToBeReplaced,String templateBusinessDateToBeReplaced,
				String strExtractIDSameValueDebitTemplateFileName,
				String X9FileCreatedQueryValidationFromDatabase) throws Exception{
			
			 String sqlExtractIDFileMultipleInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-33-V\\SQL001 -d Fred_DB_Auto -i "+
					templateFilePath + templateFileName + "_Actual.sql "+
					templateFilePath + strExtractIDSameValueDebitTemplateFileName + "_Actual.sql";
			
			/**********TestStep 1: To Verify BusinessDate in Config Table value  *************/
			
			ResultSet resultSetConfigDateValue = getICSDBServerConnection(serverName, dbName, sqlConfigBusinessDate);
			String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(resultSetConfigDateValue);
			System.out.println("BusinessDate Config Table BusinessDate is"+actualConfigDateValue);
			String extractID =  ICSDBUtilis.generateExtractId(templateFileType);
			System.out.println("ExtractID has been generated: "+extractID);
					
			ICSDBUtilis.createFileFromTemplate(templateFilePath, templateFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
			ICSDBUtilis.createFileFromTemplate(templateFilePath, strExtractIDSameValueDebitTemplateFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
			ICSDBUtilis.createFileFromTemplate(templateFilePath, templateFileName+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", actualConfigDateValue);
			ICSDBUtilis.createFileFromTemplate(templateFilePath, strExtractIDSameValueDebitTemplateFileName+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", actualConfigDateValue);
			
			String expectedBusinessDate =actualConfigDateValue;
			Component.assertEquals(actualConfigDateValue, expectedBusinessDate, "Actual and Expected BusinessDate found in Config Table");
			System.out.println("Validation is done for Config table business date value successfully");

			/***** TestStep 2: To Verify correct ExtractID and BusinessDate are ingested in database for the file having valid business date as per TestStep1****/
			
			//
		//	ICSDBUtilis.createFileFromTemplate(templateFilePath, templateFileName+"_Actual",".sql", extractID, extractID);
	    	
	    	ICSDBUtilis.sqlCommandExecution(sqlExtractIDFileMultipleInjectCommand);
			Thread.sleep(10000);
			//Read ExtractID from file
			String startExtractIDTag ="<ExtractId>";
			String endExtractIDTag ="</ExtractId>";
			String fileName =templateFileName+"_Actual.sql";
			String expectedExtractID = ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileName, startExtractIDTag, endExtractIDTag);
			System.out.println("Expected ExtractID loaded from file is: "+expectedExtractID);
			String dbExtractIDValidationWithDB = dbExtractIDValidationWithBatchMF01Table+" "+"where ExtractID ="+"'"+extractID+"'";
			ResultSet resultSetExtractIDValueInMF01 =getICSDBServerConnection(serverName, dbName, dbExtractIDValidationWithDB);
			String actualExtractIDInDatabase =GenericMethods.getICSRetrieveColumnValues(resultSetExtractIDValueInMF01);
			System.out.println("Actual ExtractID loaded from message in MF01 Batch Table is: "+actualExtractIDInDatabase);			
			Component.assertEquals(actualExtractIDInDatabase, expectedExtractID, "Actual and Expected ExtractID found");
			System.out.println("Validation is done for actual and expected BusinessDate loaded prperly in MF01 table");

			/***** TestStep 3: To Verify Incorrect BusinessDate error log is populating in database error log for the file having incorrect business date****/
			String expectedExtractIDErrMsgInErrLog =extractID+" : This Extract Id is already processed.";
	    	System.out.println("Expected ExtarctID ErrorLog validation error message should be : "+expectedExtractIDErrMsgInErrLog);
		 	ResultSet resultSetExtractIDValueInErrLog =getICSDBServerConnection(serverName, dbName, dbBusinessDateValidationWithDBInErrLog);
			String actualExtractIDIncorrectValue = GenericMethods.getICSRetrieveMutipleColumnValues(resultSetExtractIDValueInErrLog,expectedExtractIDErrMsgInErrLog);
			System.out.println("Actual BusinessDate Error message from ErrorLog Table is: "+actualExtractIDIncorrectValue);
			Component.assertEquals(actualExtractIDIncorrectValue, expectedExtractIDErrMsgInErrLog, "Actual and Expected Business date error message found");
			System.out.println("Validation is done for actual and expected ExtractID ErrorMessage successfully");			
			
			String dbX9FileCreatedQuery = X9FileCreatedQueryValidationFromDatabase+" where ExtractID="+"'"+extractID+"'";
			ResultSet resultSetIsX9FileCreated =getICSDBServerConnection(serverName, dbName, dbX9FileCreatedQuery);
			String actualX9FileCreatedValue = GenericMethods.getICSRetrieveColumnValues(resultSetIsX9FileCreated);
			//If (actualX9FileCreatedValue!=null)
			Component.assertEquals(actualX9FileCreatedValue,"1", "Actual and Expected Business date error message found");
		
		}

		
		
		}
		

