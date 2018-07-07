package com.ics.fred.testScenarios;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.fred.common.GenericMethods;
import com.ics.seleniumCoreUtilis.Component;

public class XSDICNValidationFM01 extends ICSDBUtilis{
//Append ICN message into XSD Query and execute and check error log or mfo1 sendqueue for xsd failure message if any.
// XSD query -declare @Message XML([Base].[01FM01]) set @Message = '
	

	public static void validateFM01XSDWithICN(String serverName,String dbName,String templateFilePath,String strICNFilePath,String strICNXMLFile,String strICNXMLFileNameTemp,String tempCreditFileType,String tempDebitFileType,String sqlConfigBusinessDate) throws Exception{
	//String sqlFileInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+templateFilePath + strICNXMLFileNameTemp + ".sql ";
	String strICNXMLFileNameTempIncorrectDate="ICNXSDValidationMsgIncorrectDate";
	//sqlCommandExecution(sqlFileInjectCommand);
	
	//System.out.println("sqlCommandExecution(sqlFileInjectCommand) execeution result :"+sqlCommandExecution(sqlFileInjectCommand));
	//check erro log
		
	//extractid
	String extractID =  generateExtractId(tempCreditFileType);
	String extractIDWithouHypen =  generateExtractId(tempCreditFileType);
	System.out.println("ExtractID has been generated: "+extractID);
	
	//date
	LocalDate date = LocalDate.now();
    date = date.plusDays(1);
   //String nextDayDate = GenericMethods.ConvertDateInToRequiredFormat("yyyy-MM-dd","yyyyMMdd",date.toString());
    String nextDayDate = GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy-MM-dd",date.toString());
               
    ResultSet resultSetConfigDateValue = getICSDBServerConnection(serverName, dbName, sqlConfigBusinessDate);
	String actualConfigDateVal = GenericMethods.getICSRetrieveColumnValues(resultSetConfigDateValue);
	String actualConfigDateValue = GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy-MM-dd",actualConfigDateVal);
	System.out.println("BusinessDate in Config Table: "+actualConfigDateValue);
	
	createFileFromTemplate(templateFilePath, strICNXMLFileNameTemp,".sql", "STRINGTOBEREPLACEDININPUTFILE", "STRINGTOBEREPLACEDININPUTFILE"); 
	createFileFromTemplate(templateFilePath, strICNXMLFileNameTemp+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", actualConfigDateValue);
	
	String dateIncorrectFormatVal = GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy/MM/dd",actualConfigDateVal);
	System.out.println("BusinessDate with Incorrect format: "+dateIncorrectFormatVal);
	
	createFileFromTemplate(templateFilePath, strICNXMLFileNameTempIncorrectDate,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractIDWithouHypen);		
	createFileFromTemplate(templateFilePath, strICNXMLFileNameTempIncorrectDate+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", dateIncorrectFormatVal);
	
	 String sqlBusinessDateFileMultipleInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+
			 templateFilePath + strICNXMLFileNameTempIncorrectDate + "_Actual.sql "+
			 templateFilePath + strICNXMLFileNameTemp + "_Actual.sql";	
	
	/*
	Thread.sleep(1000);
	sqlCommandExecution(sqlBusinessDateFileMultipleInjectCommand);
	Thread.sleep(10000);
	*/	
	 
	 Thread.sleep(100);
		String ouPutCmd = sqlCommandExecution(sqlBusinessDateFileMultipleInjectCommand);
		Thread.sleep(1000);
			
			publishResults(ouPutCmd!=null,(ouPutCmd!=null)?"SQL fime Exceution Message displayed as :"+ouPutCmd:"Issue Found!!","SQL fime Exceution Message displayed as :"+ouPutCmd,"SQL command execution performed.");

	}
	
	
	public static void validateMF01CreditDebitXSDWithICN(String serverName,String dbName,String templateFilePath,String strICNFilePath,String strICNXMLFile,String strICNXMLFileNameTemp,String tempCreditFileType,String tempDebitFileType,String sqlConfigBusinessDate) throws Exception{
		//String sqlFileInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+templateFilePath + strICNXMLFileNameTemp + ".sql ";
		String strICNXMLFileNameTempIncorrectDate="ICNXSDValidationMsgClearingMF";
		//sqlCommandExecution(sqlFileInjectCommand);
		
		//System.out.println("sqlCommandExecution(sqlFileInjectCommand) execeution result :"+sqlCommandExecution(sqlFileInjectCommand));
		//check erro log
			
		//extractid
		String extractID =  generateExtractId(tempCreditFileType);
		String extractIDWithouHypen =  generateExtractId(tempCreditFileType);
		System.out.println("ExtractID has been generated: "+extractID);
		
		//date
		LocalDate date = LocalDate.now();
	    date = date.plusDays(1);
	   //String nextDayDate = GenericMethods.ConvertDateInToRequiredFormat("yyyy-MM-dd","yyyyMMdd",date.toString());
	    String nextDayDate = GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy-MM-dd",date.toString());
	               
	    ResultSet resultSetConfigDateValue = getICSDBServerConnection(serverName, dbName, sqlConfigBusinessDate);
		String actualConfigDateVal = GenericMethods.getICSRetrieveColumnValues(resultSetConfigDateValue);
		String actualConfigDateValue = GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy-MM-dd",actualConfigDateVal);
		System.out.println("BusinessDate in Config Table: "+actualConfigDateValue);
		
		createFileFromTemplate(templateFilePath, strICNXMLFileNameTemp,".sql", "STRINGTOBEREPLACEDININPUTFILE", "STRINGTOBEREPLACEDININPUTFILE"); 
		createFileFromTemplate(templateFilePath, strICNXMLFileNameTemp+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", actualConfigDateValue);
		
		String dateIncorrectFormatVal = GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy/MM/dd",actualConfigDateVal);
		System.out.println("BusinessDate with Incorrect format: "+dateIncorrectFormatVal);
		
		createFileFromTemplate(templateFilePath, strICNXMLFileNameTempIncorrectDate,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractIDWithouHypen);		
		createFileFromTemplate(templateFilePath, strICNXMLFileNameTempIncorrectDate+"_Actual",".sql", "BUSINESSDATETOBEREPLACE", dateIncorrectFormatVal);
		
		 String sqlBusinessDateFileMultipleInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+
				 templateFilePath + strICNXMLFileNameTempIncorrectDate + "_Actual.sql "+
				 templateFilePath + strICNXMLFileNameTemp + "_Actual.sql";	
		
		
		Thread.sleep(1000);
		String ouPutCmd = sqlCommandExecution(sqlBusinessDateFileMultipleInjectCommand);
		Thread.sleep(10000);
			
		
			System.out.println("Error Occured:"+ouPutCmd);
			publishResults(ouPutCmd!=null,(ouPutCmd!=null)?"SQL file Exceution Message displayed as :"+ouPutCmd:"Issue Found!!","SQL fime Exceution Message displayed as :"+ouPutCmd,"SQL command execution performed.");
	}
	
	public static String sqlCommandExecutionTest(String strCommand){
		StringBuffer stringBufferStream= new StringBuffer();
		
		try {
			final Process strSqlCommand = Runtime.getRuntime().exec(strCommand);
			InputStreamReader inputS = new InputStreamReader(strSqlCommand.getInputStream());
			OutputStreamWriter oSw = new OutputStreamWriter(strSqlCommand.getOutputStream());
			BufferedReader stdErr = new BufferedReader(new InputStreamReader(strSqlCommand.getErrorStream()));
			final InputStream in = strSqlCommand.getInputStream();			
			int eachCharacterIndex;
			String s=null;
			
			while ((eachCharacterIndex=in.read())!=-1){
				stringBufferStream.append((char)eachCharacterIndex);
				
			}
			System.out.println(stringBufferStream.append((char)eachCharacterIndex));
			
			//oSw.write(eachCharacterIndex);
			//System.out.println("read msg"+oSw.append((char)eachCharacterIndex));
						
			while(stdErr.readLine()!=null){
				System.out.println("Incorrect Commandline execution performed "+stdErr.readLine());
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		return stringBufferStream.toString();	
	}	

}
