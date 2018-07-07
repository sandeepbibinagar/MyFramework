package com.ics.fred.testScenarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.fred.common.GenericMethods;
import com.ics.seleniumCoreUtilis.Component;


public class E022MessageLoadedIntoFREDDatabase extends ICSDBUtilis {
	
	
	
	public static void validateE022MessageLoadedIntoFredDB(String serverName,String dbName,String dbDataLoadedIntoDB,String templateFileType,
			String templateFilePath,String templateFileName) throws Exception{
		
		//Verify E022 Message Loaded Into Database successfully
		
		 String dbSQLFileInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+templateFilePath + templateFileName + "_Actual.sql ";		
		 String extractID =  ICSDBUtilis.generateExtractId(templateFileType);		
		 
		 ICSDBUtilis.createFileFromTemplate(templateFilePath, templateFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
		 ICSDBUtilis.sqlCommandExecution(dbSQLFileInjectCommand);
		 System.out.println("SQL File has been loaded into database successfully ");
		 String dbDataLoadedIntoDBQuery = dbDataLoadedIntoDB+" where ExtractID ='"+extractID+"'";
		 ResultSet resultSetColumnValue = getICSDBServerConnection(serverName, dbName, dbDataLoadedIntoDBQuery);
		 String actualValue = GenericMethods.getICSRetrieveAllColumnValues(resultSetColumnValue);
		 System.out.println("All Column Values of E022 Message is :"+actualValue);
		 
		 //Read all column values from SQL File
		 
		 String fileName=templateFileName+"_Actual.sql";
	     String expectedE022ValueFromDatabase= getSQLFileTagValues(templateFilePath, fileName);
		 if(expectedE022ValueFromDatabase!=null)
		 { 
			 System.out.println("Expected Value of loaded E022 message should be :"+expectedE022ValueFromDatabase);
			 //Component.assertEquals(actualValue, expectedE022ValueFromDatabase, "Actual and Expected values are same.");
			 boolean returnExpectedReslt=Component.verifyEquals(actualValue, expectedE022ValueFromDatabase, "E022 Message Loaded properly into database");
			 publishResults(returnExpectedReslt, actualValue, expectedE022ValueFromDatabase, "E022 Message Loaded into database");
			
		 }
		 else{
			 System.out.println("No records found in database");
		 }
		 
	}   
	
	public static String getSQLFileTagValues(String templateFilePath,String fileName){
			
			String s=new String();
			StringBuffer sbf = new StringBuffer();
			String BusinessDate=null;
			String ExtractId=null;	
			String ProcessingParticipantIDTag=null;
			String ExtMessageTypeTag=null;
			String IntMessageTypeTag=null;
			String MessageSourceTag=null;
			String MessageDestinationTag=null;
			//String RecordCountsTag=null;
		//	String ImageTag=null;
			String requiredTag=null;
			String filePath = templateFilePath+fileName;
			
			String startBusinessDateTag = "<BusinessDate>";
			String endBusinessDateTag = "</BusinessDate>";
			
			String startExtractIdTag = "<ExtractId>";
			String endExtractIdTag = "</ExtractId>";
	
			String startProcessingParticipantIDTag = "<ProcessingParticipantId>";
			String endProcessingParticipantIDTag = "</ProcessingParticipantId>";
	
			String startExtMessageTypeTag = "<ExtMessageType>";
			String endExtMessageTypeTag = "</ExtMessageType>";
	
			String startIntMessageTypeTag = "<IntMessageType>";
			String endIntMessageTypeTag = "</IntMessageType>";
	
			String startMessageSourceTag = "<MessageSource>";
			String endMessageSourceTag = "</MessageSource>";
	
			String startMessageDestinationTag = "<MessageDestination>";
			String endMessageDestinationTag = "</MessageDestination>";
			
			String startXMLString = "@XMLString = '";
			String endXMLString = "'";
						
	
		/*	String startRecordCountsTag = "<RecordCounts>";
			String endRecordCountsTag = "</RecordCounts> ";
			*/
	
					
			try{
				FileReader fr=new FileReader(new File(filePath));
				BufferedReader br = new BufferedReader(fr);
				
				while((s = br.readLine())!= null){
					sbf.append(s);
					}
				br.close();
				String content =sbf.toString();
			
				int indexStartBusinessDate=content.indexOf(startBusinessDateTag);
				int lengthBusinessDate = startBusinessDateTag.length();
				int indexEndBusinessDate=content.indexOf(endBusinessDateTag);
				BusinessDate=content.substring(indexStartBusinessDate+lengthBusinessDate, indexEndBusinessDate);
				String BusinessDateFormatUpdate = GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy-MM-dd",BusinessDate);
							
				int indexStartExtractId=content.indexOf(startExtractIdTag);
				int lengthExtractId = startExtractIdTag.length();
				int indexEndExtractId=content.indexOf(endExtractIdTag);
				ExtractId=content.substring(indexStartExtractId+lengthExtractId, indexEndExtractId);
				
				int indexStartProcessingParticipantIDTag=content.indexOf(startProcessingParticipantIDTag);
				int lengthProcessingParticipantIDTag = startProcessingParticipantIDTag.length();
				int indexEndProcessingParticipantIDTag=content.indexOf(endProcessingParticipantIDTag);
				ProcessingParticipantIDTag=content.substring(indexStartProcessingParticipantIDTag+lengthProcessingParticipantIDTag, indexEndProcessingParticipantIDTag);
				
				int indexStartExtMessageTypeTag=content.indexOf(startExtMessageTypeTag);
				int lengthExtMessageTypeTag = startExtMessageTypeTag.length();
				int indexEndExtMessageTypeTag=content.indexOf(endExtMessageTypeTag);
				ExtMessageTypeTag=content.substring(indexStartExtMessageTypeTag+lengthExtMessageTypeTag, indexEndExtMessageTypeTag);
				
				int indexStartIntMessageTypeTag=content.indexOf(startIntMessageTypeTag);
				int lengthIntMessageTypeTag = startIntMessageTypeTag.length();
				int indexEndIntMessageTypeTag=content.indexOf(endIntMessageTypeTag);
				IntMessageTypeTag=content.substring(indexStartIntMessageTypeTag+lengthIntMessageTypeTag, indexEndIntMessageTypeTag);
				
				int indexStartMessageSourceTag=content.indexOf(startMessageSourceTag);
				int lengthMessageSourceTag = startMessageSourceTag.length();
				int indexEndMessageSourceTag=content.indexOf(endMessageSourceTag);
				MessageSourceTag=content.substring(indexStartMessageSourceTag+lengthMessageSourceTag, indexEndMessageSourceTag);
				
				int indexStartMessageDestinationTag=content.indexOf(startMessageDestinationTag);
				int lengthMessageDestinationTag = startMessageDestinationTag.length();
				int indexEndMessageDestinationTag=content.indexOf(endMessageDestinationTag);
				MessageDestinationTag=content.substring(indexStartMessageDestinationTag+lengthMessageDestinationTag, indexEndMessageDestinationTag);
				
				/*int indexStartRecordCountsTag=content.indexOf(startRecordCountsTag);
				int lengthRecordCountsTag = startRecordCountsTag.length();
				int indexEndRecordCountsTag=content.indexOf(endRecordCountsTag);
				RecordCountsTag=content.substring(indexStartRecordCountsTag+lengthRecordCountsTag, indexEndRecordCountsTag);
				*/
				//requiredSQLTagValues= sbf.append(ExtractId).append(ProcessingParticipantIDTag).append(ExtMessageTypeTag).append(IntMessageTypeTag).append(MessageSourceTag).append(MessageDestinationTag).append(RecordCountsTag).toString();
						
				 requiredTag = ExtractId+BusinessDateFormatUpdate+ProcessingParticipantIDTag+ExtMessageTypeTag+IntMessageTypeTag+MessageSourceTag+MessageDestinationTag;
				 System.out.println("All column values as :"+requiredTag);
			}
			catch(Exception e){
			
		}
		return requiredTag;
		}
	
		
}
