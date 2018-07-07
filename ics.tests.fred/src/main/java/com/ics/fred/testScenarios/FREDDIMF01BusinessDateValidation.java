package com.ics.fred.testScenarios;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.seleniumCoreUtilis.Component;


public class FREDDIMF01BusinessDateValidation extends ICSDBUtilis {
	
	public static void validAndInvalidDIBusinessDateValidation(String serverName,String dbName,String sqlConfigBusinessDate,
			String dbBusinessDateValidationWithBatchMF01Table,String dbBuisnessDateDataTypeQuery,String dbBusinessDateValidationWithDBInErrLog,
			String templateFileType,String templateDebitFileName,
			String templateFilePath,String templateExtractIDToBeReplaced,String templateBusinessDateToBeReplaced,
			String strBusinessDateSecondDebitTemplateFileName) throws Exception{

    	//*************** SQL Insertion Command ********************// 
		
		
		//**********TestStep 1: To Verify BusinessDate in Config Table value  *************//
		 
		 System.out.println("******** TestCase 77830 : BusinessDate validation ***********");
		 validationStepInformation("******** TestCase 77830 : BusinessDate validation ***********");
		
		ResultSet resultSetConfigDateValue = getICSDBServerConnection(serverName, dbName, sqlConfigBusinessDate);
		String actualConfigDateVal = GenericMethods.getICSRetrieveColumnValues(resultSetConfigDateValue);
		String actualConfigDateValue = GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy-MM-dd",actualConfigDateVal);
		System.out.println("BusinessDate in Config Table: "+actualConfigDateValue);
		String extractID =  ICSDBUtilis.generateExtractId(templateFileType);
		String extractIDWithouHypen =  ICSDBUtilis.generateExtractId(templateFileType);
		System.out.println("ExtractID has been generated: "+extractID);
		
		String expectedMessageInErrLog ="Businessdate validation failed for the Extract id :"+extractIDWithouHypen;
		String dummyVal ="11May2016";
		createFileFromTemplate(templateFilePath, templateDebitFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);		
		createFileFromTemplate(templateFilePath, templateDebitFileName+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", actualConfigDateValue);
		
		createFileFromTemplate(templateFilePath, "BuisnessDateValidation1",".sql", "STRINGTOBEREPLACEDININPUTFILE", extractIDWithouHypen);			
		createFileFromTemplate(templateFilePath, "BuisnessDateValidation1_Actual",".sql", "BUSINESSDATETOBEREPLACE", dummyVal);
		//createFileFromTemplate(templateFilePath, "BuisnessDateValidation1_Actual",".sql", "BUSINESSDATETOBEREPLACE", actualConfigDateVal);
		//sqlCommandExecution(sqlBusinessDateFileMultipleInjectCommand);
		
		createFileFromTemplate(templateFilePath, strBusinessDateSecondDebitTemplateFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractIDWithouHypen); 
		LocalDate date = LocalDate.now();
	    date = date.plusDays(1);
	   // String nextDayDate = GenericMethods.ConvertDateInToRequiredFormat("yyyy-MM-dd","yyyyMMdd",date.toString());
	    String nextDayDate = GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy-MM-dd",date.toString());
	               
		createFileFromTemplate(templateFilePath, strBusinessDateSecondDebitTemplateFileName+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", nextDayDate);
		
		String dateIncorrectFormatVal = GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy/MM/dd",actualConfigDateVal);
		System.out.println("BusinessDate with Incorrect format: "+dateIncorrectFormatVal);
		
		createFileFromTemplate(templateFilePath, "BuisnessDateValidationFormatIncorrect",".sql", "STRINGTOBEREPLACEDININPUTFILE", extractIDWithouHypen);		
		createFileFromTemplate(templateFilePath, "BuisnessDateValidationFormatIncorrect_Actual",".sql", "BUSINESSDATETOBEREPLACE", dateIncorrectFormatVal);
		
		 String sqlBusinessDateFileMultipleInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+
				 templateFilePath + templateDebitFileName + "_Actual.sql "+
				 templateFilePath + "BuisnessDateValidation1_Actual" + ".sql "+
				 templateFilePath + "BuisnessDateValidationFormatIncorrect_Actual" + ".sql "+
				 templateFilePath + strBusinessDateSecondDebitTemplateFileName + "_Actual.sql";	
		
		
		Thread.sleep(1000);
		sqlCommandExecution(sqlBusinessDateFileMultipleInjectCommand);
		Thread.sleep(10000);
 		
		
	//	 validationStepInformation("BusinessDate Fetched from Database is "+actualConfigDateValue+" and updated businessdate in .SQL File");
		// validationStepInformation("Unique ExtractId "+extractID+" has been generated and updated in .SQL file.");
		//***** TestStep 2: To Verify correct BusinessDate is ingested in database for the file having correct business date as per TestStep1****//
		 validationStepInformation("Verify correct businessDate is ingested in database");
    	
		 String dbBusinessDateValidationWithDB = dbBusinessDateValidationWithBatchMF01Table+" "+"where ExtractID ="+"'"+extractID+"'";
    	 String sqlBusinessDateFileDebitInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+templateFilePath + templateDebitFileName + "_Actual.sql ";
 		// sqlCommandExecution(sqlBusinessDateFileDebitInjectCommand);
 		 
 		 validationStepInformation(templateDebitFileName+"_Actual.sql has been processed into database.");
		
		
		//*********** Step to validate that incorrect BusinessDate has been loaded into database*********//
		
		//***** TestStep 3: To Verify Incorrect BusinessDate error log is populating in database error log for the file having incorrect business date****//
		
    	


ArrayList<String> bdateArray =new ArrayList<>();
bdateArray.add(nextDayDate);
bdateArray.add(dateIncorrectFormatVal);
bdateArray.add(dummyVal);

	String dateIncorrectFormat = bdateArray.get(0); //queue
	String dateFormatwithoutHyphen = bdateArray.get(1); //queueu
	String nextDayDateVal=bdateArray.get(2); //errorlog
	
for(int i=1;i<=bdateArray.size();i++){
	switch(i){
	
	case 1:
		//check error log
		
		validationStepInformation("BusinessDate Unexpected Date with same format validation---------------------------------------------");
		String query ="SELECT ErrorMessage from Base.ErrorLog where ErrorMessage='"+expectedMessageInErrLog+"'";
		//dateIncorrectFormatVal
		//Duplicate ExtractID validation
		//Error should be logged into error log table
		System.out.println("Expected BusinessDate ErrorLog validation error message should be : "+expectedMessageInErrLog);
		ResultSet resultSetExtractIDValueInErrLog =getICSDBServerConnection(serverName, dbName, query);
		String actualExtractIDIncorrectValue = GenericMethods.getICSRetrieveMutipleColumnValues(resultSetExtractIDValueInErrLog,expectedMessageInErrLog);
		System.out.println("Actual BusinessDate Error message from ErrorLog Table is: "+actualExtractIDIncorrectValue);
		validationStepInformation("Actual ErrorLog failed message for Unexpected value validation "+actualExtractIDIncorrectValue);
		boolean flagExtractID = Component.verifyEquals(actualExtractIDIncorrectValue, expectedMessageInErrLog, "Incorrect ExtractId has been captured in ErrorLog table");
		System.out.println("Verifying Error log table for Unexpected validation :"+flagExtractID);
		publishResults(flagExtractID,actualExtractIDIncorrectValue, expectedMessageInErrLog, "Checking BusinessDate value in ErrorLog Table if incorrect businessdate format value is present in database.");
		//boolean flagB =Component.verifyNotEquals(actualConfigDateVal, actualConfigDateValue, "Actual and Expected Business date found (06MF01 message)");
		//publishResults(flagB,actualConfigDateVal, actualConfigDateValue, ".SQL file having businessdate format "+actualConfigDateVal+" loaded into database");
	
		break;
	case 2:
		//mf01sendqueue
		validationStepInformation("BusinessDate Incorrect Format validation--------------------------------------------");
		//select TOP 1 message_sequence_number from [Base].[//FRED/MF01SendQueue] order by message_enqueue_time desc
		String mf01SendQueueCastQuery ="SELECT TOP 3 CAST(message_body AS NVARCHAR(MAX)) FROM [FREDPIT].[Base].[//FRED/MF01SendQueue] where validation='X' order by message_enqueue_time desc";
		ResultSet rsDateValueInErrLogCast =getICSDBServerConnection(serverName, dbName, mf01SendQueueCastQuery);
		//String actualExtractIDIncorrectValue1 = GenericMethods.getICSRetrieveColumnValues(resultSetExtractIDValueInErrLogCast);
		
		String actualExtractIDIncorrectValue1 = GenericMethods.getICSRetrieveErrLog(rsDateValueInErrLogCast, dateIncorrectFormatVal);
		if(actualExtractIDIncorrectValue1.contains(dateIncorrectFormatVal))
			System.out.println("expectedExtractIDFromTmpLenIncorrect1 Incorrect format :"+dateIncorrectFormatVal);
		else
			System.out.println("No err found"+dateIncorrectFormatVal);
		validationStepInformation("Actual XSD vailation failed message for BusinessDate incorrect format is "+actualExtractIDIncorrectValue1);
		System.out.println("Actual XSD vailation failed message for BusinessDate incorrect format is "+actualExtractIDIncorrectValue1);
		        boolean f = Component.verifyTrue(actualExtractIDIncorrectValue1!=null,"ExtractId length XSD vaildation failed.");
		        publishResults(actualExtractIDIncorrectValue1!=null,(actualExtractIDIncorrectValue1!=null)?"BusinessDate Incorrect format "+actualExtractIDIncorrectValue1+" is unexpected and XSD validation fail occured.":
		        	"No XSD fail occured","BusinessDate Incorrect format "+actualExtractIDIncorrectValue1+" is unexpected and XSD validation fail occured.","BusinessDate Incorrect validation performed.");
	//}
	
		break;
	case 3:
		String expecetdResult ="XML Validation: Invalid simple type value: &apos;12345678";
		validationStepInformation("BusinessDate without any format validation--------------------------------------------");
		//mfo1sendqueue
		//File should move into mf01 send Queue if len unexpected
		//String q="select TOP 1 validation from [Base].[//FRED/MF01SendQueue] order by message_enqueue_time desc"
		String queryVal ="SELECT TOP 3 CAST(message_body AS NVARCHAR(MAX)) FROM [FREDPIT].[Base].[//FRED/MF01SendQueue] where validation='X' order by message_enqueue_time desc";
		ResultSet rsErrLog =getICSDBServerConnection(serverName, dbName, queryVal);
		String invalidDate = GenericMethods.getICSRetrieveErrLog(rsErrLog, dummyVal);
		System.out.println("Actual EtractID Validation fail message from ErrorLog Table is: "+dummyVal);
		if(invalidDate.contains(dummyVal))
		System.out.println("actualConfigDateVal "+dummyVal);
		else
		System.out.println("No err found"+dummyVal);
	
		validationStepInformation("Actual XSD vailation failed message for businessDate incorrect format is "+invalidDate);
	//Component.assertEquals(actualExtractIDIncorrectValue, "-1", "Actual and Expected ExtractID error message found");
		boolean flagIncorrect = Component.verifyTrue(invalidDate!=null,"Actual and Expected ExtractID error message found");
		System.out.println(flagIncorrect);
		publishResults(flagIncorrect,(invalidDate!=null)?"BusinessDate validation failed. "+invalidDate+" and error occured in MF01SendQueue":"NO XSD validation failed","BusinessDate validation failed. "+invalidDate+" and error occured in MF01SendQueue","BusinessDate validation failed. and error occured in MF01SendQueue");
		System.out.println("XSD Validation failed for BusinessDate containing value "+dummyVal+" "+flagIncorrect);		
		validationStepInformation("XSD Validation failed for BusinessDate containing value "+dummyVal+" "+flagIncorrect);
		break; 
	}
}


}


	}