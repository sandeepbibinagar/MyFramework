package com.ics.fred.testScenarios;

	import java.io.IOException;
	import java.sql.ResultSet;
	import java.sql.SQLException;
import java.util.ArrayList;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
	import com.ics.externalFactoryUtilis.ICSProductDBConnection;
	import com.ics.fred.common.GenericMethods;
	import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
import com.ics.fred.common.ReadSQLFileUtil;


	public class FREDCIX9ExtractIDValidation  extends ICSDBUtilis {
	
		public static void validAndInvalidCIExtractIDValidation(String serverName,String dbName,
				String sqlConfigBusinessDate,String dbExtractIDValidationWithBatchMF01Table,
				String dbBuisnessDateDataTypeQuery,String dbBusinessDateValidationWithDBInErrLog,
				String templateFileType,String templateFileName,String templateFilePath,
				String templateExtractIDToBeReplaced,String templateBusinessDateToBeReplaced,
				String str05MF01ExtractIDSameValueTemplateFileName,
				String X9FileCreatedQueryValidationFromDatabase,String mf01SendQueueQuery,String str05MF01ExtractIDIncorrectTmp,String str05MF01ExtractIDIncorrectTmp1) throws Exception{
			
			
			//**********TestStep 1: To Verify BusinessDate in Config Table value  *************//
			 
			ResultSet resultSetConfigDateValue = getICSDBServerConnection(serverName, dbName, sqlConfigBusinessDate);
			String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(resultSetConfigDateValue);
			String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
			System.out.println("BusinessDate Config Table BusinessDate is: "+configDateValue);
			
			String extractID =  ICSDBUtilis.generateExtractId(templateFileType);
			System.out.println("ExtractID has been generated: "+extractID);
			
			//String extractIDReducedLength = extractID.substring(5);
			String extractIDReducedLength = "12";
			extractIDReducedLength = extractIDReducedLength.concat("87439EXTRACTID123");
			System.out.println("ExtractID Reduced lenght : "+extractIDReducedLength);
			
			String extractIDLenght =  ICSDBUtilis.generateExtractId(templateFileType);
			extractIDLenght = extractID.concat("2345");
			System.out.println("ExtractID Inceased lenght than expected 4: "+extractIDLenght);
			System.out.println("ExtractID : "+extractID);
			//Need to ingest 3 different files to cover this testcases
					
			ICSDBUtilis.createFileFromTemplate(templateFilePath, templateFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
			ICSDBUtilis.createFileFromTemplate(templateFilePath, str05MF01ExtractIDSameValueTemplateFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
			ICSDBUtilis.createFileFromTemplate(templateFilePath, templateFileName+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", configDateValue);
			ICSDBUtilis.createFileFromTemplate(templateFilePath, str05MF01ExtractIDSameValueTemplateFileName+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", configDateValue);
			ICSDBUtilis.createFileFromTemplate(templateFilePath, str05MF01ExtractIDIncorrectTmp,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractIDReducedLength);
			ICSDBUtilis.createFileFromTemplate(templateFilePath, str05MF01ExtractIDIncorrectTmp+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", configDateValue);
			ICSDBUtilis.createFileFromTemplate(templateFilePath, str05MF01ExtractIDIncorrectTmp1,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractIDLenght);
			ICSDBUtilis.createFileFromTemplate(templateFilePath, str05MF01ExtractIDIncorrectTmp1+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", configDateValue);
			
			String sqlExtractIDFileMultipleInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+
						templateFilePath + templateFileName + "_Actual.sql "+templateFilePath + str05MF01ExtractIDSameValueTemplateFileName + "_Actual.sql "+
						templateFilePath + str05MF01ExtractIDIncorrectTmp + "_Actual.sql "+templateFilePath + str05MF01ExtractIDIncorrectTmp1 + "_Actual.sql";
			System.out.println(sqlExtractIDFileMultipleInjectCommand);
			Thread.sleep(1000);
			//***** TestStep 2: To Verify correct ExtractID and BusinessDate are ingested in database for the file having valid business date as per TestStep1****//
			validationStepInformation("Validation for correct ExtractID and BusinessDate are ingested in database for the file having valid business date as per");
	    	sqlCommandExecution(sqlExtractIDFileMultipleInjectCommand);
			Thread.sleep(10000);
			//Read ExtractID from file 
			String startExtractIDTag ="<ExtractId>";
			String endExtractIDTag ="</ExtractId>";
			String fileName =templateFileName+"_Actual.sql";
			String fileNameSameExtractID =str05MF01ExtractIDSameValueTemplateFileName+"_Actual.sql";
			String fileNameExtractIDLenIncorrect =str05MF01ExtractIDIncorrectTmp+"_Actual.sql";
			String fileNameExtractIDLenIncorrect1 =str05MF01ExtractIDIncorrectTmp1+"_Actual.sql";
			
			System.out.println("Duplicate extractId FileName :"+fileNameSameExtractID);
			System.out.println("ExtractId Incorrect length (less than expected length) FileName :"+fileNameExtractIDLenIncorrect);
			System.out.println("ExtractId Incorrect lenth (more than expected length)  FileName :"+fileNameExtractIDLenIncorrect1);
			
			String expectedExtractIDFromTmp = ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileName, startExtractIDTag, endExtractIDTag);
			String expectedExtractIDFromSameTmp = ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileNameSameExtractID, startExtractIDTag, endExtractIDTag);
			String expectedExtractIDFromTmpLenIncorrect = ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileNameExtractIDLenIncorrect, startExtractIDTag, endExtractIDTag);
			String expectedExtractIDFromTmpLenIncorrect1 = ReadSQLFileUtil.getSQLFileTagValues(templateFilePath, fileNameExtractIDLenIncorrect1, startExtractIDTag, endExtractIDTag);
			
			System.out.println("Expected ExtractID loaded from file is: "+expectedExtractIDFromTmp+" ExtractId loaded from file having same extractID is :"+expectedExtractIDFromSameTmp+
					" ExtractID length is less than expected :"+expectedExtractIDFromTmpLenIncorrect+
					" ExtractID length is more than expected :"+expectedExtractIDFromTmpLenIncorrect1);
			
			String dbExtractIDValidationWithDB = dbExtractIDValidationWithBatchMF01Table+" "+"where ExtractID ="+"'"+extractID+"'";
			ResultSet resultSetExtractIDValueInMF01 =getICSDBServerConnection(serverName, dbName, dbExtractIDValidationWithDB);
			String actualExtractIDInDatabase =GenericMethods.getICSRetrieveColumnValues(resultSetExtractIDValueInMF01);
			System.out.println("Actual ExtractID loaded from message in MF01 Batch Table is: "+actualExtractIDInDatabase);			
		//	Component.assertEquals(actualExtractIDInDatabase, expectedExtractID, "Actual and Expected ExtractID found");
			boolean flag = Component.verifyEquals(actualExtractIDInDatabase, extractID, "Actual and Expected ExtractID found");
			publishResults(flag,actualExtractIDInDatabase,extractID,"Actual and Expected ExtractID validation");
			System.out.println("Validation is done for actual and expected BusinessDate loaded properly with MF01 table");
			
			//***** TestStep 3: To Verify Incorrect ExtractID error log is populating in database error log for the file having incorrect business date****//
			String expectedExtractIDErrMsgInErrLogQueue = extractID+" : This Extract Id is already processed.";
			String expectedExtractIDErrMsgInErrLog = expectedExtractIDFromSameTmp+" : This Extract Id is already processed.";
			
			int expFileExtractValLen =expectedExtractIDFromTmp.length();
			int expFileExtractValLenIncorrect = expectedExtractIDFromSameTmp.length();
			
			ArrayList<String> extractIdArray =new ArrayList<>();
			extractIdArray.add(expectedExtractIDFromTmp);
			extractIdArray.add(extractIDLenght);
			extractIdArray.add(extractIDReducedLength);
		/*	String duplicate = extractIdArray.get(0);
			String greaterLengthValidation = extractIdArray.get(1);
			String lesserLenghtValidation=extractIdArray.get(2);
		*/	
			for(int i=1;i<=extractIdArray.size();i++){
		 	switch(i){
		 	
		 	case 1:
		 		//check error log
		 		if(expectedExtractIDFromTmp.length()==26 )// || expFileExtractValLenIncorrect!=26)
				{	
					validationStepInformation("Duplicate extractID validation--------------------------------------------");
					String query ="SELECT ErrorMessage from Base.ErrorLog where ErrorMessage='"+expectedExtractIDErrMsgInErrLog+"'";
					//Duplicate ExtractID validation
					//Error should be logged into error log table
					System.out.println("Expected ExtarctID ErrorLog validation error message should be : "+expectedExtractIDErrMsgInErrLog);
					ResultSet resultSetExtractIDValueInErrLog =getICSDBServerConnection(serverName, dbName, query);
					String actualExtractIDIncorrectValue = GenericMethods.getICSRetrieveMutipleColumnValues(resultSetExtractIDValueInErrLog,expectedExtractIDErrMsgInErrLog);
					System.out.println("Actual BusinessDate Error message from ErrorLog Table is: "+actualExtractIDIncorrectValue);
					validationStepInformation("Actual ErrorLog failed message for Duplicate ExtractId "+actualExtractIDIncorrectValue);
					boolean flagExtractID = Component.verifyEquals(actualExtractIDIncorrectValue, expectedExtractIDErrMsgInErrLog, "Duplicate ExtractId has been captured in ErrorLog table");
					System.out.println("Verifying Error log table for extractID validation :"+flagExtractID);
					publishResults(flagExtractID,actualExtractIDIncorrectValue, expectedExtractIDErrMsgInErrLog, "Checking ExtractID value in ErrorLog Table if same extractID is present in database.");
					
				}
		 		else
		 			System.out.println("Check MF01SendQueue.................... ");

		 		break;
		 	case 2:
		 		//mf01sendqueue
		 		/*if (extractIDLenght.length()>26)
				{*/
		 		//select TOP 1 message_sequence_number from [Base].[//FRED/MF01SendQueue] order by message_enqueue_time desc
					String mf01SendQueueCastQuery ="SELECT TOP 2 CAST(message_body AS NVARCHAR(MAX)) FROM ["+dbName+"].[Base].[//FRED/MF01SendQueue] where validation='X' order by message_enqueue_time desc";
					ResultSet resultSetExtractIDValueInErrLogCast =getICSDBServerConnection(serverName, dbName, mf01SendQueueCastQuery);
					//String actualExtractIDIncorrectValue1 = GenericMethods.getICSRetrieveColumnValues(resultSetExtractIDValueInErrLogCast);
					
					String actualExtractIDIncorrectValue1 = GenericMethods.getICSRetrieveErrLog(resultSetExtractIDValueInErrLogCast, expectedExtractIDFromTmpLenIncorrect1);
					if(actualExtractIDIncorrectValue1.contains(expectedExtractIDFromTmpLenIncorrect1))
						System.out.println("expectedExtractIDFromTmpLenIncorrect1 >26"+expectedExtractIDFromTmpLenIncorrect1);
					else
						System.out.println("No err found"+expectedExtractIDFromTmpLenIncorrect1);
					validationStepInformation("Actual XSD vailation failed message for ExtractId length greater than expected is "+actualExtractIDIncorrectValue1);
					
					System.out.println("Actual XSD vailation failed message for ExtractId length greater than expected is "+actualExtractIDIncorrectValue1);
					
					String tagValue1 =errActualVal(actualExtractIDIncorrectValue1);
					
			        boolean f = Component.verifyTrue(actualExtractIDIncorrectValue1!=null,"ExtractId length XSD vaildation failed.");
			        publishResults(actualExtractIDIncorrectValue1!=null,(actualExtractIDIncorrectValue1!=null)?"ErrorMessage "+tagValue1+" in MF01SendQueue with ExtractID length "+extractIDLenght+" is greater than expected and XSD validation fail occured.":
			        	"No XSD fail occured","ErrorMessage "+tagValue1+" in MF01SendQueue with ExtractID length "+extractIDLenght+" is greater than expected and XSD validation fail occured.","ExtractID length validation performed.");
		//}
				
		 		break;
		 	case 3:
		 		//mfo1sendqueue
		 		//File should move into mf01 send Queue if len unexpected
		 		//String q="select TOP 1 validation from [Base].[//FRED/MF01SendQueue] order by message_enqueue_time desc"
		 		String queryVal ="SELECT TOP 2 CAST(message_body AS NVARCHAR(MAX)) FROM ["+dbName+"].[Base].[//FRED/MF01SendQueue] where validation='X' order by message_enqueue_time desc";
		 		//String queryVal="select TOP 1 message_sequence_number from [Base].[//FRED/MF01SendQueue] where validation='X' order by message_enqueue_time desc";
				System.out.println("ExtractID Lenght failed.");
				//String extractIdValidationQuery =mf01SendQueueQuery;		
		    //	ResultSet resultSetExtractIDValueInErrLog =getICSDBServerConnection(serverName, dbName, mf01SendQueueQuery);
				ResultSet rs =getICSDBServerConnection(serverName, dbName, queryVal);
				String actualExtractIDIncorrectValue = GenericMethods.getICSRetrieveErrLog(rs, expectedExtractIDFromTmpLenIncorrect);
				System.out.println("Actual EtractID Validation fail message from ErrorLog Table is: "+actualExtractIDIncorrectValue);
				/*if(actualExtractIDIncorrectValue.get(1).contains(expectedExtractIDFromTmpLenIncorrect))
					System.out.println("expectedExtractIDFromTmpLenIncorrect <26"+expectedExtractIDFromTmpLenIncorrect);
				else
					System.out.println("No err found"+expectedExtractIDFromTmpLenIncorrect);
				*/
				validationStepInformation("Actual XSD vailation failed message for ExtractId length less than expected is "+actualExtractIDIncorrectValue);
				//Component.assertEquals(actualExtractIDIncorrectValue, "-1", "Actual and Expected ExtractID error message found");
				String tagVal =errActualVal(actualExtractIDIncorrectValue);
				boolean flagIncorrect = Component.verifyTrue(actualExtractIDIncorrectValue!=null,"Actual and Expected ExtractID error message found");
				publishResults(flagIncorrect,(actualExtractIDIncorrectValue!=null)?"ExtractID lenght validation failed. "+actualExtractIDIncorrectValue+" and XSD vailation failed message for "+tagVal+" error occured in MF01SendQueue.":"NO XSD validation failed","ExtractID lenght validation failed. "+actualExtractIDIncorrectValue+" and XSD vailation failed message for "+tagVal+" error occured in MF01SendQueue.","ExtractID lenght validation failed. and error occured in MF01SendQueue");
				System.out.println("ExtractID XSD Validation fail");		

		 		break; 
		 	}
			}
		}
		
		public static String errActualVal(String actualExtractIDIncorrectValue){
			String startTag ="<Description>";
			String endTag ="</Description>";
				
			int indexStartTag=actualExtractIDIncorrectValue.indexOf(startTag);
			int length = startTag.length();
			int indexEndTag=actualExtractIDIncorrectValue.indexOf(endTag);
			String tagValue = actualExtractIDIncorrectValue.substring(indexStartTag+length, indexEndTag);
			System.out.println("Expected Substring Value is :"+tagValue);
			
			int indexStart=tagValue.indexOf("This");
			String tagValue1 = tagValue.substring(0, indexStart);
			System.out.println("Expected err Substring Value is :"+tagValue1);
			return tagValue1;
		}
	}
			/*
			if(expectedExtractIDFromTmp.length()==26 )// || expFileExtractValLenIncorrect!=26)
			{	
				validationStepInformation("Duplicate extractID validation--------------------------------------------");
				//Duplicate ExtractID validation
				//Error should be logged into error log table
				System.out.println("Expected ExtarctID ErrorLog validation error message should be : "+expectedExtractIDErrMsgInErrLog);
				ResultSet resultSetExtractIDValueInErrLog =getICSDBServerConnection(serverName, dbName, dbBusinessDateValidationWithDBInErrLog);
				String actualExtractIDIncorrectValue = GenericMethods.getICSRetrieveMutipleColumnValues(resultSetExtractIDValueInErrLog,expectedExtractIDErrMsgInErrLog);
				System.out.println("Actual BusinessDate Error message from ErrorLog Table is: "+actualExtractIDIncorrectValue);
				boolean flagExtractID = Component.verifyEquals(actualExtractIDIncorrectValue, expectedExtractIDErrMsgInErrLog, "Dupliacate ExtractId has been captured in ErrorLog table");
				System.out.println("Verifying Error log table for extractID validation :"+flagExtractID);
				publishResults(flagExtractID,actualExtractIDIncorrectValue, expectedExtractIDErrMsgInErrLog, "Checking ExtractID value in ErrorLog Table if same extractID is present in database.");
				
			}
		 	
						else if (extractIDLenght.length()>26)
			{
				String mf01SendQueueCastQuery ="SELECT TOP 1 CAST(message_body AS NVARCHAR(MAX)) FROM [FREDPIT].[Base].[//FRED/MF01SendQueue] order by message_enqueue_time desc";
				ResultSet resultSetExtractIDValueInErrLogCast =getICSDBServerConnection(serverName, dbName, mf01SendQueueCastQuery);
				String actualExtractIDIncorrectValue1 = GenericMethods.getICSRetrieveColumnValues(resultSetExtractIDValueInErrLogCast);
				        boolean f = Component.verifyTrue(actualExtractIDIncorrectValue1!=null,"ExtractId length XSD vaildation failed.");
				        publishResults(actualExtractIDIncorrectValue1!=null,(actualExtractIDIncorrectValue1!=null)?"ExtractID length "+extractIDLenght+" is greater than expected and XSD validation fail occured.":
				        	"No XSD fail occured","ExtractID length "+extractIDLenght+" is greater than expected and XSD validation fail occured.","ExtractID length validation performed.");
			}
			else if(extractIDReducedLength.length() < 26)
			{
				//File should move into mf01 send Queue if len unexpected
				System.out.println("ExtractID Lenght failed.");
				//String extractIdValidationQuery =mf01SendQueueQuery;		
		    	ResultSet resultSetExtractIDValueInErrLog =getICSDBServerConnection(serverName, dbName, mf01SendQueueQuery);
				String actualExtractIDIncorrectValue = GenericMethods.getICSRetrieveColumnValues(resultSetExtractIDValueInErrLog);
				System.out.println("Actual EtractID Validation fail message from ErrorLog Table is: "+actualExtractIDIncorrectValue);
				//Component.assertEquals(actualExtractIDIncorrectValue, "-1", "Actual and Expected ExtractID error message found");
				boolean flagIncorrect = Component.verifyTrue(actualExtractIDIncorrectValue.equals("-1"),"Actual and Expected ExtractID error message found");
				publishResults(flagIncorrect,actualExtractIDIncorrectValue,"-1","ExtractID lenght validation failed. and error occured in MF01SendQueue");
				System.out.println("ExtractID XSD Validation fail");		
			}		
			
		
		}

		*/
		
