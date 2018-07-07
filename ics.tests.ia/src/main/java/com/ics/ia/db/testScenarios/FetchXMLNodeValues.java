/*  
<Copyright file="PostingExtractFile.java" company="iPSL">
Copyright © iPSL 2017 All rights are reserved.
Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
is prohibited without the prior written permission of the copyright owner.
</copyright> 
*/
 

/* ************************* Module Header ******************************
 * Module Name : Common methods for loading, fetching XML values
 * Date : 18/04/2017
 * Created By : Sandeep Bibinagar
 * Description : This class contains common methods w.r.t Image Archive for fetching XML node values, loading messages to DB and validating node values against DB.
 
 ******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
 Description : Updating class as per java coding standards
 ********************************************************************** */


package com.ics.ia.db.testScenarios;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Random;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.junit.Assert;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.ia.testScenario.DBValidations;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;



/******************************************************************************************************* */
/* Purpose:	 This class contains common methods w.r.t Image Archive for fetching XML node values, loading messages to DB and validating node values against DB.  
 * @Author Sandeep Bibinagar 
/******************************************************************************************************* */

public class FetchXMLNodeValues extends GenericMethodUtilis {
	
	public static String templateFilePath = getIADataFromPathXml();
	public static String businessDateTagName= "BusinessDate";
	public static String extractIdTagNameTagName = "ExtractId";
	public static String processingParticipantIdTagName = "ProcessingParticipantId";
	public static String extMessageTypeTagName = "ExtMessageType";
	public static String intMessageTypeTagName = "IntMessageType";
	public static String messageSourceTagName = "MessageSource";
	public static String messageDestinationTagName = "MessageDestination";
	public static String recordCountsTagName = "RecordCounts";
	public static String entityTypeTagName = "EntityType";
	public static String entityIdTagNameTagName = "EntityId";
	public static String stateRevisionTagName = "StateRevision";
	public static String entityStateTagName="EntityState";
	public static String sourceDateTimeTagName="SourceDateTime";
	//01MA02
	public static String itemIdTagName="ItemId";
	public static String processIdTagName="ProcessId";
	public static String operatorIdTagName="OperatorId";
	public static String updateDateTimeTagName="UpdateDateTime";
	public static String auditRevisionTagName="AuditRevision";
	public static String genderTagName="Gender";
	public static String payDecisionTagName="PayDecision";
	public static String payDecisionReasonCodeTagName="ReasonCode";
	public static String tranCodeTagName="TranCode";
	public static String referenceTagName="Reference";
	public static String serialTagName="Serial";
	public static String sortcodeTagName="SortCode";
	public static String accountTagName="Account";
	public static String amountTagName="Amount";
	//CreditItem
	public static String creditItemIdTagName = "CdtItmId";
	public static String CdtItmTpTagNameTagName = "CdtItmTp";
	public static String creditAmtTagName = "Amt";
	public static String creditBkCdTagName="BkCd";
	public static String creditAcctNbTagName="AcctNb";
	public static String creditRefNbTimeTagName="RefNb";
	//DebitItem
	public static String debitItemIdTagName = "DbtItmId";
	public static String debitItemtpTagNameTagName = "DbtItmTp";
	public static String dbtItmTxTagName = "DbtItmTxCd";
	public static String debitrpresntdItmIndTagName="RpresntdItmInd";
	public static String debitamtTagName="Amt";
	public static String debitBkCdTagName="BkCd";
	//04MA01
	public static String responseWindowStartDateTagName="Day2ResponseWindowStartDateTime";
	public static String responseWindowEndDateTagName="Day2ResponseWindowEndDateTime";
	//03MA01 //09MA01
	public static String errorCodeTagName="ErrorCode";
	public static String errorDescriptionTagName="ErrorDescription";
	public static String errorLocationTagName="ErrorLocation";
	//01MA02 //06MA03
	public static String fraudrReasonTagName="FraudCheckReason";
	public static String fraudResultTagName="FraudCheckResult";
	public static String transactionsetIdTagName="TransactionSetId";
	public static String responseTypeTagName="FraudResponseType";
	public static String creationDateTagName="CreationDateTime";
	public static String numberOfentriesTagName="NumberofEntries";
	//07MA01 // 76054(AuditEntryFieldReference)
	public static String documentIdTagName="DocumentId";
	public static String creationDateTimeIdTagName="CreationDateTime";
	public static String numberOfEntriesIdTagName="NumberofEntries";
	public static String senderIdTagName="SenderId";
	public static String chargingParticipantTagName="ChargingParticipant";
	public static String testDocumentTagName="TestDocument";
	public static String transactionSetIdTagName="TransactionSetId";
	//PRMA01
	public static String FileIdTagName="FileId";
	public static String FileSequenceNumberTagName="FileSequenceNumber";
	public static String SourceTagName="Source";
	public static String FileDateTimeTagName="FileDateTime";
	public static String ExtractItemCountTagName="ExtractItemCount";
	public static String ResponseSequenceTagName="ResponseSequence";
	public static String PostingTypeTagName="PostingType";
	public static String AccountTagName="Account";
	public static String SortCodeTagName="SortCode";
	public static String NPAAccountTagName="NPAAccount";
	public static String NPASortCodeTagName="NPASortCode";
	public static String RedirectionIndTagName="RedirectionInd";
	public static String AccountingSystemTagName="AccountingSystem";
	public static String ResponseStatusTagName="ResponseStatus";
	public static String ResponseSubTypeTagName="ResponseSubType";
	public static String ResponseStatusCountTagName="ResponseStatusCount";
	public static String AggregationCountTagName="AggregationCount";
	public static String RicherDataRefName="RicherDataRef";
	public static String FraudStatusCodeTagName="FraudStatusCode";
	public static String FraudReasonCodeTagName="FraudReasonCode";
	public static String CreditReferenceTagName="CreditReference";
	public static String SerialTagName="Serial";
	public static String StatusSequenceTagName="StatusSequence";
	public static String ReasonCodeTagName="ReasonCode";
	public static String ReasonTextTagName="ReasonText";
	//CMMA01 // CMMA02
	public static String caseIdTagName="CaseId";
	public static String caseTypeTagName="CaseType";
	public static String EntityIdTagName="EntityId";
	public static String EntityTypeTagName="EntityType";
	//public static String filePath = "D:\\Sandeep\\TestData\\";
	public static String filePath = getIADataFromPathXmlForNode();
	public static String xsdFileCoreTagName = "Core";
	public static String xsdFileEntityTagName = "Entity";
	public static String xsdFileItemTagName = "Item";
	public static String xsdFileFraudResultsTagName = "FraudItemResults";
	public static String xsdFileCodeLineTagName = "Codeline";
	public static String xsdFileEntityErrorTagName = "EntityError";
	public static String xsdFileDocumentTagName = "DocumentTransactionItems";
	public static String xsdFileOpenCaseTagName = "OpenCase";
	public static String xsdFileCloseCaseTagName = "CloseCase";
	public static String xsdFilePostingResponseTagName = "PostingResponse";
	public static String xsdFileCreditItemTagName = "CrdtItm";
	public static String xsdFileDebitItemTagName = "DbtItm";
	public static String xsdFilePostingUpdateTagName = "PostingUpdate";
	//public static String server ="GBIBC-DT03-01-V\\SQL002";
	//public static String db = "Short_Term_Archive_R2";
	public static String server =getIAValueFromDataSheet("server");
	public static String db = getIAValueFromDataSheet("db_r2");
	
	public static String query="";
	private static String sqlFileInjectCommand ;
	public static NodeList nodeSetList;
	public static boolean flag;
	public static HashMap<String, String> coreHeaderSetTagWiseData;
	public static HashMap<String, String> entityHeaderSetTagWiseData;
	public static HashMap<String, String> itemHeaderSetTagWiseData;
	public static HashMap<String, String> codelineHeaderSetTagWiseData;
	public static HashMap<String, String> responsewindowHeaderSetTagWiseData;
	public static HashMap<String, String> fraudresultsHeaderSetTagWiseData;
	public static HashMap<String, String> entityErrorHeaderSetTagWiseData;
	public static HashMap<String, String> documentHeaderSetTagWiseData;
	public static HashMap<String, String> opencaseHeaderSetTagWiseData;
	public static HashMap<String, String> closedcaseHeaderSetTagWiseData;
	public static HashMap<String, String> postingresponseHeaderSetTagWiseData;
	public static HashMap<String, String> responseHeaderSetTagWiseData;
	public static HashMap<String, String> creditItemHeaderSetTagWiseData;
	public static HashMap<String, String> debitItemHeaderSetTagWiseData;
	public static HashMap<String, String> postingUpdateHeaderSetTagWiseData;
	public static Map<String, String> itemID,fraudCheckResult,fraudCheckReason = new LinkedHashMap<String, String>();
	public static List<String> itemIDFromXML, fraudCheckResultFromXML, fraudCheckReasonFromXML;
	public static String[] businessDate_NV, ExtractId_NV, ProcessingParticipantId_NV, ExtMessageType_NV, IntMessageType_NV, MessageSource_NV, MessageDestination_NV, RecordCounts_NV;
	public static String[] EntityType_NV,EntityId_NV, StateRevision_NV, EntityState_NV, SourceDateTime_NV;
	public static String[] CreationDateTime_NV, NumberofEntries_NV, FraudResponseType_NV, TransactionSetId_NV, ItemId_NV, FraudCheckResult_NV, FraudCheckReason_NV;
	public static String[] FirstChequeDate_NV, LastChequeDate_NV, CounterpartiesCount_NV,FraudChequesCount_NV, GoodChequesCount_NV, LargestAmount_NV, RiskIndicator_NV ;
	public static String[] ProcessId_NV, OperatorId_NV, UpdateDateTime_NV, AuditRevision_NV, Gender_NV,BeneficiaryParticipantId_NV;
	public static String[] ErrorCode_NV, ErrorLocation_NV, ErrorDescription_NV;
	public static String[] SortCode_NV, Account_NV, Amount_NV, SwitchedSortCode_NV,SwitchedAccount_NV;
	public static String[] Reference_NV, TranCode_NV;
	public static String[] DocumentID_NV;
	public static String[] Day2ResponseWindowStartDateTime_NV, Day2ResponseWindowEndDateTime_NV;
	public static String[] CaseId_NV, CaseType_NV;
	public static String[] PayDecision_NV, PayDecisionReasonCode_NV;
	public static String[] APGEndTime_NV,APGStartTime_NV, WorkTypeNbr_NV,BusinessDate_NV, InstallationId_NV, CaptureSystemId_NV, StartTime_NV,EndTime_NV,WorkTypeNbr_NVR, SortFamily_NV,SourceType_NV,SourceName_NV,SourceID_NV,FinancialInstitutionID_NV,CollectionStartTime_NV,CollectionEndTime_NV, FinancialInstitutionId_NV;
	public static String[] BlkNbr_NV, ImageType_NV,BatchCreditCount_NV,BatchDebitCount_NV,BatchCreditAmount_NV,BatchDebitAmount_NV;
	public static String[] TriggeringState_NV;
	public static String[] ResponseWindowStart_NV, ResponseWindowEnd_NV;
	public static String[] FileID_NV, FileSequenceNumber_NV,Source_NV, FileDateTime_NV,ExtractItemCount_NV;
	public static String[] Sequence_NV, PostingType_NV,NPAAccount_NV, NPASortCode_NV,RedirectInd_NV,AccountingSystem_NV,ResponseStatus_NV,ResponseSubType_NV,StatusCount_NV,AggregationCount_NV;
	public static String[] StatusSequence_NV, ReasonCode_NV,ReasonText_NV;
	public static String[] RicherDataRef_NV, FraudStatusCode_NV,CreditReference_NV,Serial_NV,FraudReasonCode_NV;
	public static String[] APGDIN_NV,APGBusinessDate_NV,TransactionNumber_NV,IsElectronic_NV,IsOnUs_NV,IsDeleted_NV,IsCorrected_NV,IsAmountCorrected_NV,OriginalAmount_NV,IsTCCorrected_NV,IsANCorrected_NV,IsSortCodeCorrected_NV,IsSerialCorrected_NV,IsReject_NV,RejectReason_NV,SpSelector_NV,Currency_NV,AdjustmentReason_NV,Narrative_NV,OriginalIsn_NV,AeStatus_NV,IcStatus_NV,IqvStatus_NV,CarSetId_NV,CarResult_NV,IaStatus_NV,IaResult_NV,PNVReviewStatus_NV,DuplicateStatus_NV,ReturnReason_NV,ChequeIssuedDate_NV;
	public static String[] ExtractSequence_NV, ExtractRevision_NV,FileId_NV,FileType_NV,Environment_NV,Weekday_NV,ExtractStartDateTime_NV,ExtractEndDateTime_NV;
	public static String[] PostingAttempt_NV,PostingSequence_NV,PostingSubType_NV,Channel_NV,PostingDrCrEntry_NV,PostingSource_NV,ResponseIdSource_NV,PostingDay_NV,ClearingDate_NV, SettlementDate_NV,PostedAmount_NV,PostingOverrideFlag_NV,ChequeCount_NV,CollectingParticipantId_NV,CollectingLocation_NV,SupportingInfo_NV;
	public static String[] OriginalSortCode_NV,OriginalAccount_NV,CreditExceptionCode_NV,BeneficiaryName_NV;
	public static DateFormat formatter, dateFormat;
	public static Date date,tempDate;
	public static int rowCount, rowCountAfterLoad;
	
	// Description: This is a generic method to validate XSD 
	public static void validateXSD(String serverName,String dbName,String templateFilePath,String templateFileName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException{
		runStoredProcedureCallTest(serverName, dbName, templateFilePath, templateFileName);
	}
	
	// Description: This is a generic method created to run stored procedure through command prompt
	public static void runStoredProcedureCallTest(String sqlServerName, String sqlDbName, String procedureFilePath, String procedureFileName) throws IOException
		{
			sqlFileInjectCommand = "cmd /c sqlcmd -S " + sqlServerName + " -d " + sqlDbName + " -i " + procedureFilePath+"\\"+ procedureFileName  + "_Actual.sql" ;
			System.out.println("SQL-Command : "+ sqlFileInjectCommand);
			sqlCommandExecutionTest(sqlFileInjectCommand);
		}
		
		
	// Description: This is a generic method to run stored procedure through command prompt
	public static String sqlCommandExecutionTest(String strCommand) throws IOException{
		StringBuffer stringBufferStream= new StringBuffer();
		final Process strSqlCommand = Runtime.getRuntime().exec(strCommand);
		//InputStreamReader inputS = new InputStreamReader(strSqlCommand.getInputStream());
		//OutputStreamWriter oSw = new OutputStreamWriter(strSqlCommand.getOutputStream());
		BufferedReader stdErr = new BufferedReader(new InputStreamReader(strSqlCommand.getErrorStream()));
		final InputStream in = strSqlCommand.getInputStream();			
		int eachCharacterIndex;
		//String s=null;
		while ((eachCharacterIndex=in.read())!=-1){
			stringBufferStream.append((char)eachCharacterIndex);
		}
		System.out.println(stringBufferStream.append((char)eachCharacterIndex));
		System.out.println("XSD Validation passed ");
		String xsdCmdOutput= stringBufferStream.append((char)eachCharacterIndex).toString();
		if(xsdCmdOutput.contains("Invalid")){
		System.exit(0);
		}
		while(stdErr.readLine()!=null){
			System.out.println("Incorrect Commandline execution performed "+stdErr.readLine());
		}
		return stringBufferStream.toString();	
	}
	
	
	// Description: This method generates a random number 
	public static int randomInt(int min, int max){
		Random random=new Random();
		int randomNum=random.nextInt((max-min)+1)+min;
		return randomNum;
	}
	
	
	public static void errorQueue(String serverName,String dbName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		String query = "SELECT Count(*) "
				+ "FROM ["+db+"].[dbo].[//ImageArchive/ErrorQueue]";
		resultSet = getICSDBServerConnection(serverName, dbName, query);
		while(resultSet.next())
		{		
		String resultSetCount=resultSet.getString(1);		
		rowCount=Integer.parseInt(resultSetCount);
		System.out.println("Row Count in ErrorQueue : "+rowCount);
		}
	}
	
	
	public static void validateErrorQueueAfterLoad(String serverName,String dbName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		String query = "SELECT Count(*) "
				+ "FROM ["+db+"].[dbo].[//ImageArchive/ErrorQueue]";
		resultSet = getICSDBServerConnection(serverName, dbName, query);
		while(resultSet.next())
		{		
		String resultSetCount=resultSet.getString(1);	
		rowCountAfterLoad=Integer.parseInt(resultSetCount);
		if(rowCountAfterLoad==rowCount){
			System.out.println("Message loaded in the IA Database successfully");
		}else{
			System.out.println("Message Failed to Load in DB");
			System.exit(0);
			}
			}
		}
	
	
	
	
	// Description: This method checks Error Send Queue after loading data into Archive DB
	public static void checkErrorSendQueue(String serverName,String dbName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException{
		String query = "SELECT  top 1 *, casted_message_body = "
				+ "CASE message_type_name WHEN 'X'" 
				+ " THEN CAST(message_body AS NVARCHAR(MAX)) "
				+ " ELSE message_body "
				+ " END " 
				+ " FROM [Short_Term_Archive_2].[dbo].[//ImageArchive/SendQueue] Order By message_enqueue_time desc ";
		System.out.println(query);
		resultSet = getICSDBServerConnection(serverName, dbName, query);
		while(resultSet.next())
		{		
		String resultSetValueCastedMsg=resultSet.getString("casted_message_body");
		String resultSetValueValidation=resultSet.getString("validation");
		String startTag ="<Description>";
		String endTag ="</Description>";
		if(resultSetValueCastedMsg!=null){
		if(resultSetValueCastedMsg.contains("Error")||resultSetValueCastedMsg.contains("Invalid")||resultSetValueCastedMsg.contains("failed")){
			int indexStartTag=resultSetValueCastedMsg.indexOf(startTag);
			int length = startTag.length();
			int indexEndTag=resultSetValueCastedMsg.indexOf(endTag);
			String tagValue = resultSetValueCastedMsg.substring(indexStartTag+length, indexEndTag);
			int indexStart=tagValue.indexOf("This");
			String errMsg = tagValue.substring(0, indexStart);
			System.out.println("Message Failed to Load in DB :  " +errMsg);
			
		}
		}else{
			
		if(resultSetValueValidation.equals("E")){
			System.out.println("Message Loaded in the Database");			
		}}}	
	}
	
	
	// Description: This method loads Data into Archive DB
	public static void validateMessageLoadIntoIADB(String serverName,String dbName,String templateFilePath,String templateFileName) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, IOException{
		ResultSet rsLoad = ICSProductDBConnection.getICSDBServerConnection(serverName, dbName, generateAndUpdateUniqueIDInMessage(templateFilePath +"\\"+ templateFileName + "_Actual.sql"));
		}
	
	
	// Description: This method generates a unique ID for Messages
	public static String generateAndUpdateUniqueIDInMessage(String filePath) throws IOException
	{
		String extractID=RandomStringUtils.randomNumeric(16).toString()+RandomStringUtils.randomAlphabetic(4).toUpperCase()+RandomStringUtils.randomNumeric(6).toString();
		String closedcaseId=randomInt(1,1)+RandomStringUtils.randomNumeric(9).toString();
		String opencaseId=randomInt(1,1)+RandomStringUtils.randomNumeric(9).toString();
		String blocknumber=randomInt(1,9)+RandomStringUtils.randomNumeric(3).toString();
		String installationid=RandomStringUtils.randomAlphabetic(2).toUpperCase()+RandomStringUtils.randomAlphabetic(13).toString();
		String transactionnumber=randomInt(1,9)+RandomStringUtils.randomNumeric(8).toString(); 
		String documentId=randomInt(1,9)+RandomStringUtils.randomNumeric(11).toString()+":"+randomInt(1,9)+RandomStringUtils.randomNumeric(9).toString();
		String fileId=RandomStringUtils.randomNumeric(30).toString()+RandomStringUtils.randomAlphabetic(3).toString();
		String ReasonCode=RandomStringUtils.randomAlphabetic(6).toString();
		String paydecisionreasoncode=RandomStringUtils.randomAlphabetic(2).toUpperCase()+RandomStringUtils.randomNumeric(2).toString();
		String tranCode=randomInt(1,9)+RandomStringUtils.randomNumeric(1).toString();
		String switchedAccount=randomInt(1,9)+RandomStringUtils.randomNumeric(7).toString();
		//String postingsequnce=randomInt(1,9)+RandomStringUtils.randomNumeric(5).toString();
		String crid=randomInt(1,9)+RandomStringUtils.randomNumeric(3).toString();
		String dbid=randomInt(1,9)+RandomStringUtils.randomNumeric(3).toString();
		String fraudReasoncode= RandomStringUtils.randomAlphabetic(4).toString();
		String extractsequence= randomInt(1,9)+RandomStringUtils.randomNumeric(3).toString();
		
		String entityId="";
		if(filePath.split("\\\\")[filePath.split("\\\\").length-1].equals("04MA01_Actual.sql") || filePath.split("\\\\")[filePath.split("\\\\").length-1].equals("CMMA01_Actual.sql"))
		{
		entityId=RandomStringUtils.randomNumeric(16).toString()+RandomStringUtils.randomAlphabetic(5).toUpperCase()+RandomStringUtils.randomNumeric(4).toString();
		}
		if(filePath.split("\\\\")[filePath.split("\\\\").length-1].equals("03MA01_Actual.sql")){
		entityId=RandomStringUtils.randomNumeric(16).toString()+RandomStringUtils.randomAlphabetic(4).toUpperCase()+RandomStringUtils.randomNumeric(4).toString();
		}
		if(!(filePath.split("\\\\")[filePath.split("\\\\").length-1].equals("03MA01_Actual.sql")) && !(filePath.split("\\\\")[filePath.split("\\\\").length-1].equals("CMMA01_Actual.sql")) && !(filePath.split("\\\\")[filePath.split("\\\\").length-1].equals("04MA01_Actual.sql")))
		{
			entityId=RandomStringUtils.randomNumeric(16).toString()+RandomStringUtils.randomAlphabetic(5).toUpperCase()+RandomStringUtils.randomNumeric(2).toString();
		}
		File file=new File(filePath);
		String filevalue=FileUtils.readFileToString(file);
		if (filevalue.contains("ExID"))
			filevalue=filevalue.replaceAll("ExID", extractID);
		if(filevalue.contains("EntID"))
			filevalue=filevalue.replaceAll("EntID", entityId);
		if(filevalue.contains("ClosedCaseID"))
			filevalue=filevalue.replaceAll("ClosedCaseID", closedcaseId);
		if(filevalue.contains("DocID"))
			filevalue=filevalue.replaceAll("DocID", documentId);
		if(filevalue.contains("FId"))
			filevalue=filevalue.replaceAll("FId", fileId);
		if(filevalue.contains("RsCode"))
			filevalue=filevalue.replaceAll("RsCode", ReasonCode);
		if(filevalue.contains("OpenCaseID"))
			filevalue=filevalue.replaceAll("OpenCaseID", opencaseId);
		if(filevalue.contains("BlockNum"))
			filevalue=filevalue.replaceAll("BlockNum", blocknumber);
		if(filevalue.contains("InstID"))
			filevalue=filevalue.replaceAll("InstID", installationid);
		if(filevalue.contains("TrNum"))
			filevalue=filevalue.replaceAll("TrNum", transactionnumber);
		if(filevalue.contains("pdrc"))
			filevalue=filevalue.replaceAll("pdrc", paydecisionreasoncode);
		if(filevalue.contains("tranCd"))
			filevalue=filevalue.replaceAll("tranCd", tranCode);
		if(filevalue.contains("SwAcct"))
			filevalue=filevalue.replaceAll("SwAcct", switchedAccount);
		if(filevalue.contains("CxID"))
			filevalue=filevalue.replaceAll("CxID", crid);
		if(filevalue.contains("DxID"))
			filevalue=filevalue.replaceAll("DxID", dbid);
		if(filevalue.contains("ExSeq"))
			filevalue=filevalue.replaceAll("ExSeq", extractsequence);
		filePath=filePath.replaceAll("_Actual.sql", ".xml");
		File file1=new File(filePath);
		String filevalue1=FileUtils.readFileToString(file1);
		if (filevalue1.contains("<ExtractId>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<ExtractId>"), filevalue1.indexOf("</ExtractId>")), "<ExtractId>"+extractID);
		if (filevalue1.contains("<EntityId>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<EntityId>"), filevalue1.indexOf("</EntityId>")), "<EntityId>"+entityId);
		if (filevalue1.contains("<CloseCase>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<CaseId>"), filevalue1.indexOf("</CaseId>")), "<CaseId>"+closedcaseId);
		if (filevalue1.contains("<OpenCase>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<CaseId>"), filevalue1.indexOf("</CaseId>")), "<CaseId>"+opencaseId);
		if (filevalue1.contains("<DocumentId>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<DocumentId>"), filevalue1.indexOf("</DocumentId>")), "<DocumentId>"+documentId);
		if (filevalue1.contains("<ReasonCode>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<ReasonCode>"), filevalue1.indexOf("</ReasonCode>")), "<ReasonCode>"+ReasonCode);
		if (filevalue1.contains("<FileId>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<FileId>"), filevalue1.indexOf("</FileId>")), "<FileId>"+fileId);
		if (filevalue1.contains("<InstallationId>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<InstallationId>"), filevalue1.indexOf("</InstallationId>")), "<InstallationId>"+installationid);
		if (filevalue1.contains("<BlkNbr>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<BlkNbr>"), filevalue1.indexOf("</BlkNbr>")), "<BlkNbr>"+blocknumber);
		if (filevalue1.contains("<TransactionNumber>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<TransactionNumber>"), filevalue1.indexOf("</TransactionNumber>")), "<TransactionNumber>"+transactionnumber);
		if (filevalue1.contains("<PayDecisionReasonCode>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<PayDecisionReasonCode>"), filevalue1.indexOf("</PayDecisionReasonCode>")), "<PayDecisionReasonCode>"+paydecisionreasoncode);
		if (filevalue1.contains("<TranCode>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<TranCode>"), filevalue1.indexOf("</TranCode>")), "<TranCode>"+tranCode);
		if (filevalue1.contains("<SwitchedAccount>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<SwitchedAccount>"), filevalue1.indexOf("</SwitchedAccount>")), "<SwitchedAccount>"+switchedAccount);
		if (filevalue1.contains("CxID"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("CRE70016250"), filevalue1.indexOf("CD")), "CRE70016250"+crid);
		if (filevalue1.contains("DxID"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("DEBT5016250"), filevalue1.indexOf("DB")), "DEBT5016250"+dbid);
		if (filevalue1.contains("<ExtractSequence>"))
			filevalue1=filevalue1.replaceAll(filevalue1.substring(filevalue1.indexOf("<ExtractSequence>"), filevalue1.indexOf("</ExtractSequence>")), "<ExtractSequence>"+extractsequence);
		if(filevalue.contains("ITMID"))
			filevalue=updateItemdata(filevalue,filevalue1,file1);
		else
		FileUtils.writeStringToFile(file1, filevalue1, false);
		//System.out.println(filevalue);
		return filevalue;
	}
	
	
	// Description: This method updates Item data if there are multiple items in the message
	public static String updateItemdata(String sqlfileValue,String xmlfileValue, File filepath) throws IOException
	{
		String itemid="",finalxmlValue="";
		String listofSqlfile[], listofxmlFile[];
		listofSqlfile=sqlfileValue.split("ITMID");
		listofxmlFile=xmlfileValue.split("<ItemId>");
		if(listofSqlfile.length<=2&&listofxmlFile.length<=2)
		{
			itemid=RandomStringUtils.randomNumeric(16).toString()+RandomStringUtils.randomAlphabetic(5).toUpperCase()+RandomStringUtils.randomNumeric(4).toString();
			sqlfileValue=sqlfileValue.replaceAll("ITMID", itemid);
			finalxmlValue=xmlfileValue.replaceAll(xmlfileValue.substring(xmlfileValue.indexOf("<ItemId>"), xmlfileValue.indexOf("</ItemId>")), "<ItemId>"+itemid);
		}
		else
		{
			for(int i=1;i<=listofSqlfile.length-1;i++)
			{
				itemid=RandomStringUtils.randomNumeric(16).toString()+RandomStringUtils.randomAlphabetic(5).toUpperCase()+RandomStringUtils.randomNumeric(4).toString();
				sqlfileValue=sqlfileValue.replaceAll(("ITMID"+i).toString(), itemid);
				if(listofxmlFile[i-1].contains("</ItemId>"))
					finalxmlValue=finalxmlValue+listofxmlFile[i-1].substring(listofxmlFile[i-1].indexOf("</ItemId>"), listofxmlFile[i-1].length())+"<ItemId>"+itemid;
				else
					finalxmlValue=finalxmlValue+listofxmlFile[i-1]+"<ItemId>"+itemid;
			}
			finalxmlValue=finalxmlValue+listofxmlFile[listofxmlFile.length-1].substring(listofxmlFile[listofxmlFile.length-1].indexOf("</ItemId>"), listofxmlFile[listofxmlFile.length-1].length());
		}
		FileUtils.writeStringToFile(filepath, finalxmlValue, false);
	//	System.out.println(sqlfileValue);
		return sqlfileValue;
	}
	
	
	
	// Description: This method gets the tag wise data in the XML/Message for Core Node
	public static void getTagwiseDataForCoreHeader(String fileName) throws Exception
	{	
		nodeSetList = getNodeSetList(filePath,fileName, xsdFileCoreTagName);
		coreHeaderSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
		{
		Node eachNode = nodeSetList.item(eachNodeSetEntry);
		if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
		{	
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
			coreHeaderSetTagWiseData.put(businessDateTagName, eachElement.getElementsByTagName(businessDateTagName).item(0).getTextContent());	
			coreHeaderSetTagWiseData.put(extractIdTagNameTagName, eachElement.getElementsByTagName(extractIdTagNameTagName).item(0).getTextContent());	
			coreHeaderSetTagWiseData.put(processingParticipantIdTagName, eachElement.getElementsByTagName(processingParticipantIdTagName).item(0).getTextContent());					
			coreHeaderSetTagWiseData.put(extMessageTypeTagName, eachElement.getElementsByTagName(extMessageTypeTagName).item(0).getTextContent());	
			coreHeaderSetTagWiseData.put(intMessageTypeTagName, eachElement.getElementsByTagName(intMessageTypeTagName).item(0).getTextContent());	
			coreHeaderSetTagWiseData.put(messageSourceTagName, eachElement.getElementsByTagName(messageSourceTagName).item(0).getTextContent());	
			coreHeaderSetTagWiseData.put(messageDestinationTagName, eachElement.getElementsByTagName(messageDestinationTagName).item(0).getTextContent());	
			coreHeaderSetTagWiseData.put(recordCountsTagName, eachElement.getElementsByTagName(recordCountsTagName).item(0).getTextContent());									
		  }
		}
	}
	
		
	// Description: This method gets the tag wise data in the XML/Message for Entity Node
	public static void getTagwiseDataForEntityHeader(String fileName) throws Exception
	{
		nodeSetList = getNodeSetList(filePath,fileName, xsdFileEntityTagName);
		entityHeaderSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
		
			entityHeaderSetTagWiseData.put(entityTypeTagName, eachElement.getElementsByTagName(entityTypeTagName).item(0).getTextContent());	
			entityHeaderSetTagWiseData.put(entityIdTagNameTagName, eachElement.getElementsByTagName(entityIdTagNameTagName).item(0).getTextContent());	
			entityHeaderSetTagWiseData.put(stateRevisionTagName, eachElement.getElementsByTagName(stateRevisionTagName).item(0).getTextContent());					
			entityHeaderSetTagWiseData.put(entityStateTagName, eachElement.getElementsByTagName(entityStateTagName).item(0).getTextContent());	
			entityHeaderSetTagWiseData.put(sourceDateTimeTagName, eachElement.getElementsByTagName(sourceDateTimeTagName).item(0).getTextContent());	
			}
		}
		}	
	
	
	
	// Description: This method gets the tag wise data in the XML/Message for Item Node
	public static void getTagwiseDataForItemHeader(String fileName) throws Exception
	{
	nodeSetList = getNodeSetList(filePath,fileName, xsdFileItemTagName);
	itemHeaderSetTagWiseData = new LinkedHashMap<String, String>();
	for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
	{
		Node eachNode = nodeSetList.item(eachNodeSetEntry);
		if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
		{			
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
			itemHeaderSetTagWiseData.put(itemIdTagName, eachElement.getElementsByTagName(itemIdTagName).item(0).getTextContent());	
			itemHeaderSetTagWiseData.put(auditRevisionTagName, eachElement.getElementsByTagName(auditRevisionTagName).item(0).getTextContent());	
			itemHeaderSetTagWiseData.put(genderTagName, eachElement.getElementsByTagName(genderTagName).item(0).getTextContent());
		}
	}
	}
	
	
	
	// Description: This method gets the tag wise data in the XML/Message for CodeLine Node
	public static void getTagwiseDataForCodeLinemHeader(String fileName) throws Exception
	{
	nodeSetList = getNodeSetList(filePath,fileName, xsdFileCodeLineTagName);
	codelineHeaderSetTagWiseData = new LinkedHashMap<String, String>();
	for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
	{
		Node eachNode = nodeSetList.item(eachNodeSetEntry);
		if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
		{			
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
			codelineHeaderSetTagWiseData.put(sortcodeTagName, eachElement.getElementsByTagName(sortcodeTagName).item(0).getTextContent());
			codelineHeaderSetTagWiseData.put(accountTagName, eachElement.getElementsByTagName(accountTagName).item(0).getTextContent());
			codelineHeaderSetTagWiseData.put(amountTagName, eachElement.getElementsByTagName(amountTagName).item(0).getTextContent());
		}
	}
	}
	
	// Description: This method gets the tag wise data in the XML/Message for PostingUpdate Node
	public static void getTagwiseDataForPostingUpdateHeader(String fileName) throws Exception
	{
	nodeSetList = getNodeSetList(filePath,fileName, xsdFilePostingUpdateTagName);
	postingUpdateHeaderSetTagWiseData = new LinkedHashMap<String, String>();
	for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
	{
		Node eachNode = nodeSetList.item(eachNodeSetEntry);
		if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
		{			
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
			postingUpdateHeaderSetTagWiseData.put(sortcodeTagName, eachElement.getElementsByTagName(sortcodeTagName).item(0).getTextContent());
			postingUpdateHeaderSetTagWiseData.put(accountTagName, eachElement.getElementsByTagName(accountTagName).item(0).getTextContent());
			postingUpdateHeaderSetTagWiseData.put(amountTagName, eachElement.getElementsByTagName(amountTagName).item(0).getTextContent());
		}
	}
	}
	
	
	
	// Description: This method gets the tag wise data in the XML/Message for Response Window Node
	public static void getTagwiseDataForResponseWindowHeader(String fileName) throws Exception
	{
	nodeSetList = getNodeSetList(filePath,fileName, xsdFileCodeLineTagName);
	responsewindowHeaderSetTagWiseData = new LinkedHashMap<String, String>();
	for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
	{
		Node eachNode = nodeSetList.item(eachNodeSetEntry);
		if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
		{			
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
			responsewindowHeaderSetTagWiseData.put(responseWindowStartDateTagName, eachElement.getElementsByTagName(responseWindowStartDateTagName).item(0).getTextContent());
			responsewindowHeaderSetTagWiseData.put(responseWindowEndDateTagName, eachElement.getElementsByTagName(responseWindowEndDateTagName).item(0).getTextContent());
		}
	}
	}
	
	
	
	// Description: This method gets the tag wise data in the XML/Message for Document Node
	public static void getTagwiseDataForDocumentHeader(String fileName) throws Exception
	{
	nodeSetList = getNodeSetList(filePath,fileName, xsdFileDocumentTagName);
	documentHeaderSetTagWiseData = new LinkedHashMap<String, String>();
	for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
	{
		Node eachNode = nodeSetList.item(eachNodeSetEntry);
		if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
		{			
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
			documentHeaderSetTagWiseData.put(documentIdTagName, eachElement.getElementsByTagName(documentIdTagName).item(0).getTextContent());	
			documentHeaderSetTagWiseData.put(creationDateTimeIdTagName, eachElement.getElementsByTagName(creationDateTimeIdTagName).item(0).getTextContent());	
			documentHeaderSetTagWiseData.put(numberOfEntriesIdTagName, eachElement.getElementsByTagName(numberOfEntriesIdTagName).item(0).getTextContent());					
			documentHeaderSetTagWiseData.put(senderIdTagName, eachElement.getElementsByTagName(senderIdTagName).item(0).getTextContent());	
			documentHeaderSetTagWiseData.put(chargingParticipantTagName, eachElement.getElementsByTagName(chargingParticipantTagName).item(0).getTextContent());	
		}
		}		
	}
	

	// Description: This method gets the tag wise data in the XML/Message for OpenCase Node
	public static void getTagwiseDataForOpenCaseHeader(String fileName) throws Exception
	{
	nodeSetList = getNodeSetList(filePath,fileName, xsdFileOpenCaseTagName);
	opencaseHeaderSetTagWiseData = new LinkedHashMap<String, String>();
	for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
	{
		Node eachNode = nodeSetList.item(eachNodeSetEntry);
		if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
		{			
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
			opencaseHeaderSetTagWiseData.put(caseIdTagName, eachElement.getElementsByTagName(caseIdTagName).item(0).getTextContent());	
			opencaseHeaderSetTagWiseData.put(caseTypeTagName, eachElement.getElementsByTagName(caseTypeTagName).item(0).getTextContent());	
			opencaseHeaderSetTagWiseData.put(EntityTypeTagName, eachElement.getElementsByTagName(EntityTypeTagName).item(0).getTextContent());	
			opencaseHeaderSetTagWiseData.put(EntityIdTagName, eachElement.getElementsByTagName(EntityIdTagName).item(0).getTextContent());	
		}}
	}
	
	
	
	// Description: This method gets the tag wise data in the XML/Message for ClosedCase Node
	public static void getTagwiseDataForClosedCaseHeader(String fileName) throws Exception
	{
	nodeSetList = getNodeSetList(filePath,fileName, xsdFileCloseCaseTagName);
	closedcaseHeaderSetTagWiseData = new LinkedHashMap<String, String>();
	for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
	{
		Node eachNode = nodeSetList.item(eachNodeSetEntry);
		if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
		{			
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
			closedcaseHeaderSetTagWiseData.put(caseIdTagName, eachElement.getElementsByTagName(caseIdTagName).item(0).getTextContent());	
		}}
	}
	

	
	// Description: This method gets the tag wise data in the XML/Message for EntityError Node
	public static void getTagwiseDataForEntityErrorHeader(String fileName) throws Exception
	{
	nodeSetList = getNodeSetList(filePath,fileName, xsdFileEntityErrorTagName);
	entityErrorHeaderSetTagWiseData = new LinkedHashMap<String, String>();
	for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
	{
		Node eachNode = nodeSetList.item(eachNodeSetEntry);
		if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
		{			
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
			entityErrorHeaderSetTagWiseData.put(errorCodeTagName, eachElement.getElementsByTagName(errorCodeTagName).item(0).getTextContent());
			entityErrorHeaderSetTagWiseData.put(errorDescriptionTagName, eachElement.getElementsByTagName(errorDescriptionTagName).item(0).getTextContent());
		}
	}}
	
	
	
	// Description: This method gets the tag wise data in the XML/Message for Posting Response Node
	public static void getTagwiseDataForPostingReponseHeader(String fileName) throws Exception
	{
		nodeSetList = getNodeSetList(filePath,fileName, xsdFilePostingResponseTagName);
		postingresponseHeaderSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{			
				Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
				postingresponseHeaderSetTagWiseData.put(FileIdTagName, eachElement.getElementsByTagName(FileIdTagName).item(0).getTextContent());	
				postingresponseHeaderSetTagWiseData.put(FileSequenceNumberTagName, eachElement.getElementsByTagName(FileSequenceNumberTagName).item(0).getTextContent());	
				postingresponseHeaderSetTagWiseData.put(SourceTagName, eachElement.getElementsByTagName(SourceTagName).item(0).getTextContent());
				postingresponseHeaderSetTagWiseData.put(FileDateTimeTagName, eachElement.getElementsByTagName(FileDateTimeTagName).item(0).getTextContent());	
				postingresponseHeaderSetTagWiseData.put(ExtractItemCountTagName, eachElement.getElementsByTagName(ExtractItemCountTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(ExtractItemCountTagName, eachElement.getElementsByTagName(ExtractItemCountTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(ResponseSequenceTagName, eachElement.getElementsByTagName(ResponseSequenceTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(PostingTypeTagName, eachElement.getElementsByTagName(PostingTypeTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(AccountTagName, eachElement.getElementsByTagName(AccountTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(SortCodeTagName, eachElement.getElementsByTagName(SortCodeTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(NPAAccountTagName, eachElement.getElementsByTagName(NPAAccountTagName).item(0).getTextContent());	
				postingresponseHeaderSetTagWiseData.put(NPASortCodeTagName, eachElement.getElementsByTagName(NPASortCodeTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(RedirectionIndTagName, eachElement.getElementsByTagName(RedirectionIndTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(AccountingSystemTagName, eachElement.getElementsByTagName(AccountingSystemTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(ResponseStatusTagName, eachElement.getElementsByTagName(ResponseStatusTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(ResponseSubTypeTagName, eachElement.getElementsByTagName(ResponseSubTypeTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(ResponseStatusCountTagName, eachElement.getElementsByTagName(ResponseStatusCountTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(AggregationCountTagName, eachElement.getElementsByTagName(AggregationCountTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(RicherDataRefName, eachElement.getElementsByTagName(RicherDataRefName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(FraudStatusCodeTagName, eachElement.getElementsByTagName(FraudStatusCodeTagName).item(0).getTextContent());	
				postingresponseHeaderSetTagWiseData.put(CreditReferenceTagName, eachElement.getElementsByTagName(CreditReferenceTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(SerialTagName, eachElement.getElementsByTagName(SerialTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(StatusSequenceTagName, eachElement.getElementsByTagName(StatusSequenceTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(ReasonCodeTagName, eachElement.getElementsByTagName(ReasonCodeTagName).item(0).getTextContent());		
				postingresponseHeaderSetTagWiseData.put(ReasonTextTagName, eachElement.getElementsByTagName(ReasonTextTagName).item(0).getTextContent());		
		}
	}}
	
	
	
	// Description: This method gets the tag wise data in the XML/Message for Fraud Results Node
	public static void getTagwiseDataForFraudResultsHeader(String fileName) throws Exception
	{
	nodeSetList = getNodeSetList(filePath,fileName, xsdFileFraudResultsTagName);
	fraudresultsHeaderSetTagWiseData = new LinkedHashMap<String, String>();
	for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
	{
		Node eachNode = nodeSetList.item(eachNodeSetEntry);
		if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
		{			
			Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
			fraudresultsHeaderSetTagWiseData.put(itemIdTagName, eachElement.getElementsByTagName(itemIdTagName).item(0).getTextContent());	
			fraudresultsHeaderSetTagWiseData.put(transactionsetIdTagName, eachElement.getElementsByTagName(transactionsetIdTagName).item(0).getTextContent());	
			fraudresultsHeaderSetTagWiseData.put(fraudrReasonTagName, eachElement.getElementsByTagName(fraudrReasonTagName).item(0).getTextContent());					
			fraudresultsHeaderSetTagWiseData.put(fraudResultTagName, eachElement.getElementsByTagName(fraudResultTagName).item(0).getTextContent());	
			fraudresultsHeaderSetTagWiseData.put(responseTypeTagName, eachElement.getElementsByTagName(responseTypeTagName).item(0).getTextContent());	
			fraudresultsHeaderSetTagWiseData.put(creationDateTagName, eachElement.getElementsByTagName(creationDateTagName).item(0).getTextContent());
			fraudresultsHeaderSetTagWiseData.put(numberOfentriesTagName, eachElement.getElementsByTagName(numberOfentriesTagName).item(0).getTextContent());
		}
	}
	}
	
	
	
	// Description: This method gets the tag wise data in the XML/Message for Debit Item Entity Node
	public static void getTagwiseDataForDebitItemEntityHeader(String fileName) throws Exception
	{
		nodeSetList = getNodeSetList(filePath,fileName, xsdFileDebitItemTagName);
		debitItemHeaderSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
				debitItemHeaderSetTagWiseData.put(debitItemIdTagName, eachElement.getElementsByTagName(debitItemIdTagName).item(0).getTextContent());	
				debitItemHeaderSetTagWiseData.put(debitItemtpTagNameTagName, eachElement.getElementsByTagName(debitItemtpTagNameTagName).item(0).getTextContent());	
				debitItemHeaderSetTagWiseData.put(dbtItmTxTagName, eachElement.getElementsByTagName(dbtItmTxTagName).item(0).getTextContent());					
				debitItemHeaderSetTagWiseData.put(debitrpresntdItmIndTagName, eachElement.getElementsByTagName(debitrpresntdItmIndTagName).item(0).getTextContent());	
				debitItemHeaderSetTagWiseData.put(debitamtTagName, eachElement.getElementsByTagName(debitamtTagName).item(0).getTextContent());	
				debitItemHeaderSetTagWiseData.put(debitBkCdTagName, eachElement.getElementsByTagName(debitBkCdTagName).item(0).getTextContent());	
			}
		}
		}

	
	
	// Description: This method gets the tag wise data in the XML/Message for Credit Item Node
	public static void getTagwiseDataForCreditItemHeader(String fileName) throws Exception
	{
		nodeSetList = getNodeSetList(filePath,fileName, xsdFileCreditItemTagName);
		creditItemHeaderSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));
				creditItemHeaderSetTagWiseData.put(creditItemIdTagName, eachElement.getElementsByTagName(creditItemIdTagName).item(0).getTextContent());	
				creditItemHeaderSetTagWiseData.put(CdtItmTpTagNameTagName, eachElement.getElementsByTagName(CdtItmTpTagNameTagName).item(0).getTextContent());	
				creditItemHeaderSetTagWiseData.put(creditAmtTagName, eachElement.getElementsByTagName(creditAmtTagName).item(0).getTextContent());					
				creditItemHeaderSetTagWiseData.put(creditBkCdTagName, eachElement.getElementsByTagName(creditBkCdTagName).item(0).getTextContent());	
				creditItemHeaderSetTagWiseData.put(creditAcctNbTagName, eachElement.getElementsByTagName(creditAcctNbTagName).item(0).getTextContent());	
				creditItemHeaderSetTagWiseData.put(creditRefNbTimeTagName, eachElement.getElementsByTagName(creditRefNbTimeTagName).item(0).getTextContent());	
			}
		}
		}
	
	
	
	// Description: This method gets the data in the DB for Core 
	public static ResultSet getCoreHeaderAttributeValues(String fileName) throws Exception
	{
		String[] ExtractIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ExtractId", "ExtractId");

		query = "select CoreId as BusinessDate, ExtractId,ParticipantId as ProcessingParticipantId, MessageType as ExtMessageType,IntMessageType, Source as MessageSource, Destination as MessageDestination, RecordCount as RecordCounts FROM Base.Core"
			//	+  " where Extractid='"+PostingExtractFile.coreHeaderSetTagWiseData.get("ExtractId")+"'";
				+ " where Extractid='"+ExtractIDFromXML[0]+"'";
		System.out.println(query);
		ResultSet rsCore = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsCore.next())
		{
			String businessdate_db = rsCore.getString("BusinessDate").replaceAll("[A-Za-z .]","");
			String businessdate_xml =  FetchXMLNodeValues.coreHeaderSetTagWiseData.get("BusinessDate").replaceAll("[A-Za-z+ -.]","");
			flag= Component.verifyEquals(businessdate_db.substring(0, 8), businessdate_xml.substring(0, 8), "BusinessDate Validation");
			publishResults(flag,businessdate_db.substring(0, 8), businessdate_xml.substring(0, 8), "Business Date Value Validation ");
			flag= Component.verifyEquals(rsCore.getString("ExtractId"), ExtractIDFromXML[0], "ExtractId validation");
			publishResults(flag, rsCore.getString("ExtractId"), ExtractIDFromXML[0], "ExtractId Value Validation");
			flag= Component.verifyEquals(rsCore.getString("ProcessingParticipantId"),FetchXMLNodeValues.coreHeaderSetTagWiseData.get("ProcessingParticipantId"), "ProcessingParticipantId record Validation");
			publishResults(flag, rsCore.getString("ProcessingParticipantId"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("ProcessingParticipantId"), "ProcessingParticipantId Value Validation");
			flag= Component.verifyEquals(rsCore.getString("IntMessageType"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("IntMessageType"), "IntMessageType record Validation");
			publishResults(flag, rsCore.getString("IntMessageType"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("IntMessageType"), "IntMessageType Value Validation ");
			flag= Component.verifyEquals(rsCore.getString("MessageSource"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("MessageSource"), "MessageSource record Validation");
			publishResults(flag, rsCore.getString("MessageSource"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("MessageSource"), "Source Value Validation ");
			flag= Component.verifyEquals(rsCore.getString("MessageDestination"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("MessageDestination"), "MessageDestination record Validation");
			publishResults(flag, rsCore.getString("MessageDestination"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("MessageDestination"), "Destination Value Validation ");
			flag= Component.verifyEquals(rsCore.getString("RecordCounts"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("RecordCounts"), "RecordCounts record Validation");
			publishResults(flag, rsCore.getString("RecordCounts"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("RecordCounts"), "RecordCounts Value Validation ");
			DBValidations.validateBusinessDate(FetchXMLNodeValues.coreHeaderSetTagWiseData.get("BusinessDate"));
			DBValidations.validateExtractId(rsCore.getString(2));
			DBValidations.validateProcessingParticipantId(rsCore.getString(3));
			DBValidations.validateIntMessageType(rsCore.getString(5));
			DBValidations.validateMessageSource(rsCore.getString(6));
			DBValidations.validateMessageDestination(rsCore.getString(7));
			DBValidations.validateRecordCounts(rsCore.getString(8));
		}
		return rsCore;
	}
	
	
	
	// Description: This method gets the data in the DB for Open Case Table 
	public static ResultSet getOpenCaseHeaderAttributeValues(String fileName) throws Exception
	{
		CaseType_NV=getTagValueByXPATH(templateFilePath, fileName, "//CaseType", "CaseType");
		CaseId_NV=getTagValueByXPATH(templateFilePath, fileName, "//CaseId", "CaseId");
		EntityId_NV=getTagValueByXPATH(templateFilePath, fileName, "//EntityId", "EntityId");
		query ="SELECT CaseID,CaseType, EntityType, EntityId FROM CaseMgmt.OpenCases" 
		+" where CaseID ='"+CaseId_NV[0]+"'";
		System.out.println(query );
		ResultSet rsCaseOpen = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsCaseOpen.next())
		{
			flag= Component.verifyEquals(rsCaseOpen.getString("CaseID"), CaseId_NV[0], "CaseId record validation");
			publishResults(flag, rsCaseOpen.getString("CaseID"), CaseId_NV[0], "Validation of CaseId in Case Management Table");
			flag= Component.verifyEquals(rsCaseOpen.getString("CaseType"), CaseType_NV[0].substring(0, 5), "CaseType record Validation");
			publishResults(flag, rsCaseOpen.getString("CaseType"), CaseType_NV[0].substring(0, 5), "Validation of Case Type in Case Management Table");
			flag= Component.verifyEquals(rsCaseOpen.getString("EntityType"), FetchXMLNodeValues.opencaseHeaderSetTagWiseData.get("EntityType"), "Entity Type record Validation");
			publishResults(flag, rsCaseOpen.getString("EntityType"), FetchXMLNodeValues.opencaseHeaderSetTagWiseData.get("EntityType"), "Validation of Entity Type in Case Management Table");
			flag= Component.verifyEquals(rsCaseOpen.getString("EntityId"), EntityId_NV[1], "EntityId record Validation");
			publishResults(flag, rsCaseOpen.getString("EntityId"), EntityId_NV[1], "Validation of EntityId in Case Management Table");
			DBValidations.validateOpenCaseId(rsCaseOpen.getString(1));
			DBValidations.validateCaseType(rsCaseOpen.getString(2));
			DBValidations.validateEntityType(rsCaseOpen.getString(3));
			DBValidations.validateEntityId(rsCaseOpen.getString(4));
		}
		return rsCaseOpen;
	}	
	
	
	
	// Description: This method gets the data in the DB for Debit Table 
	public static ResultSet getResponseWindowHeaderAttributeValues(String fileName) throws Exception
	{
		String[] itemIDFromXMLFile=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		ResponseWindowStart_NV=getTagValueByXPATH(templateFilePath, fileName, "//Day2ResponseWindowStartDateTime", "Day2ResponseWindowStartDateTime");
		ResponseWindowEnd_NV=getTagValueByXPATH(templateFilePath, fileName, "//Day2ResponseWindowEndDateTime", "Day2ResponseWindowEndDateTime");
		query = "select DebitId,Day2ResponseStartDateTime,Day2ResponseEndDateTime from Base.Debit where DebitId='"+itemIDFromXMLFile[0]+"'";
		System.out.println(query); 
		ResultSet rsResWindow = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsResWindow.next())
		{
		String responseWindowStart_db= rsResWindow.getString("Day2ResponseStartDateTime").replaceAll("[A-Za-z +]","");
		String responseWindowStart_xml = ResponseWindowStart_NV[0].replaceAll("[A-Za-z+]","");
		String responseWindowEnd_db= rsResWindow.getString("Day2ResponseEndDateTime").replaceAll("[A-Za-z +]","");
		String responseWindowEnd_xml = ResponseWindowEnd_NV[0].replaceAll("[A-Za-z+]","");
		flag= Component.verifyEquals(rsResWindow.getString("DebitId"), itemIDFromXMLFile[0],"ItemId record validation");
		publishResults(flag, rsResWindow.getString("DebitId"),itemIDFromXMLFile[0], "ItemId value validation");
		flag= Component.verifyEquals(responseWindowStart_db.substring(0, responseWindowStart_db.length()-4),responseWindowStart_xml.substring(0, responseWindowStart_xml.length()-5), "Day2ResponseStartDateTime record validation");
		publishResults(flag, responseWindowStart_db.substring(0, responseWindowStart_db.length()-4),responseWindowStart_xml.substring(0, responseWindowStart_xml.length()-5), "Day2ResponseStartDateTime Value validation");
		flag= Component.verifyEquals(responseWindowEnd_db.substring(0, responseWindowEnd_db.length()-4),responseWindowEnd_xml.substring(0, responseWindowEnd_xml.length()-5), "Day2ResponseEndDateTime record validation");
		publishResults(flag, responseWindowEnd_db.substring(0, responseWindowEnd_db.length()-4),responseWindowEnd_xml.substring(0, responseWindowEnd_xml.length()-5), "Day2ResponseEndDateTime Value validation");
		DBValidations.validateResponseWindowStartDateTime(rsResWindow.getString("Day2ResponseStartDateTime"));
		DBValidations.validateResponseWindowEndDateTime(rsResWindow.getString("Day2ResponseEndDateTime"));
		}
		return rsResWindow;
	}	
	
	
	
	// Description: This method gets the data in the DB for Closed Case Table 
	public static ResultSet getClosedCaseHeaderAttributeValues(String fileName) throws Exception
	{
		String[] Case=getTagValueByXPATH(templateFilePath, fileName, "//CaseId", "CaseId");
		query ="select CaseID from CaseMgmt.ClosedCases" 
				+" where CaseID ='"+Case[1]+"'";
		System.out.println(query );
		ResultSet rsCaseClosed = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsCaseClosed.next())
		{
		flag= Component.verifyEquals(rsCaseClosed.getString("CaseId"), FetchXMLNodeValues.closedcaseHeaderSetTagWiseData.get("CaseId"), "CaseId record Validation");
		publishResults(flag, rsCaseClosed.getString("CaseId"), FetchXMLNodeValues.closedcaseHeaderSetTagWiseData.get("CaseId"), "Validation of CaseId in Case Management Table");
		DBValidations.validateClosedCaseID(rsCaseClosed.getString(1));
	}
		return rsCaseClosed;
	}	
	
	
	
	// Description: This method gets the data in the DB for Entity Error Table 
	public static ResultSet getEntityErrorAttributeValue(String fileName) throws Exception
	{
		EntityId_NV=getTagValueByXPATH(templateFilePath, fileName, "//EntityId", "EntityId");
		query = "select ErrorCode, ErrorDescription FROM Base.EntityError ee JOIN" + 
				" Base.Entity e on e.EntityId = ee.EntityId where e.EntityIdentifier='"+EntityId_NV[0]+"'";
		System.out.println(query); 
		ResultSet rsError = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsError.next())
		{
		flag= Component.verifyEquals(rsError.getString("ErrorCode"), FetchXMLNodeValues.entityErrorHeaderSetTagWiseData.get("ErrorCode"), "ErrorCode record validation");
		publishResults(flag, rsError.getString("ErrorCode"), FetchXMLNodeValues.entityErrorHeaderSetTagWiseData.get("ErrorCode"), "ErrorCode Value validation");
		flag= Component.verifyEquals(rsError.getString("ErrorDescription"), FetchXMLNodeValues.entityErrorHeaderSetTagWiseData.get("ErrorDescription"), "ExtMessageType record validation");
		publishResults(flag, rsError.getString("ErrorDescription"), FetchXMLNodeValues.entityErrorHeaderSetTagWiseData.get("ErrorDescription"), "ErrorDescription Value validation");
		DBValidations.validateErrorCode(rsError.getString("ErrorCode"));	
		DBValidations.validateEntityErroDesc(rsError.getString("ErrorDescription"));	
		}
		return rsError;
	}	
	
	// Description: This method gets the data in the DB for Response Header Table 
	public static ResultSet getResponseHeaderAttributeValues(String fileName) throws Exception
	{
		query = "select Source, CreatedDate, ItemCount FROM Post.Response" 
				+ " where ItemCount ='"+FetchXMLNodeValues.responseHeaderSetTagWiseData.get("ExtractItemCount")+"'";
		System.out.println(query);
		ResultSet rsreponseHeader = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsreponseHeader.next())
		{
			flag= Component.verifyEquals(rsreponseHeader.getString("Source"), FetchXMLNodeValues.responseHeaderSetTagWiseData.get("Source"), "Source record Validation");
			publishResults(flag, rsreponseHeader.getString("Source"), FetchXMLNodeValues.responseHeaderSetTagWiseData.get("Source"), "Source value Validation");
			flag= Component.verifyEquals(rsreponseHeader.getString("CreatedDate"), FetchXMLNodeValues.responseHeaderSetTagWiseData.get("FileDateTime"), "FileDateTime record Validation");
			publishResults(flag, rsreponseHeader.getString("CreatedDate"), FetchXMLNodeValues.responseHeaderSetTagWiseData.get("FileDateTime"), "FileDateTime value Validation");
			flag= Component.verifyEquals(rsreponseHeader.getString("ItemCount"), FetchXMLNodeValues.responseHeaderSetTagWiseData.get("ExtractItemCount"), "ExtractItemCount record Validation");
			publishResults(flag, rsreponseHeader.getString("ItemCount"), FetchXMLNodeValues.responseHeaderSetTagWiseData.get("ExtractItemCount"), "ExtractItemCount value Validation");
			DBValidations.validateSource(rsreponseHeader.getString(1));
			DBValidations.validateCreatedDate(rsreponseHeader.getString(2));
			DBValidations.validateItemCount(rsreponseHeader.getString(3));
		}
		return rsreponseHeader;
	}		
	
	// Description: This method gets the data in the DB for Posting Batch Table
	public static ResultSet getPostingBatchAttributeValues(String fileName) throws Exception
	{
		ItemId_NV=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		TriggeringState_NV=getTagValueByXPATH(templateFilePath, fileName, "//TriggeringState", "TriggeringState");
		query = "select ItemId, TriggeringState FROM Post.TriggerBatchItems"
				+ " where ItemId ='"+ItemId_NV[0]+"'";
		System.out.println(query); 
		ResultSet rsPostBatch = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsPostBatch.next()){
			flag= Component.verifyEquals(rsPostBatch.getString("ItemId"), ItemId_NV[0], "ItemId validation");
			publishResults(flag, rsPostBatch.getString("ItemId"), ItemId_NV[0], "ItemId validation ");
			flag= Component.verifyEquals(rsPostBatch.getString("TriggeringState"), TriggeringState_NV[0], "TriggeringState validation ");
			publishResults(flag, rsPostBatch.getString("TriggeringState"), TriggeringState_NV[0], "TriggeringState validation  ");
			DBValidations.validateItemId(rsPostBatch.getString(1));
			DBValidations.validateTriggeringState(rsPostBatch.getString(2));
		}
		return rsPostBatch;
	}
	
	// Description: This method gets the data in the DB for PostingUpdate Posting Response Table 
	public static ResultSet getPostingUpdate_PostingResponseAttributeValues(String fileName) throws Exception
	{
		FileID_NV=getTagValueByXPATH(templateFilePath, fileName, "//FileId", "FileId");
		FileSequenceNumber_NV=getTagValueByXPATH(templateFilePath, fileName, "//FileSequenceNumber", "FileSequenceNumber");
		Source_NV=getTagValueByXPATH(templateFilePath, fileName, "//Source", "Source");
		FileDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//FileDateTime", "FileDateTime");
		ExtractItemCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractItemCount", "ExtractItemCount");
		query = "select FileId,Sequence,Source,CreatedDate,ItemCount FROM Post.Response"
				+ " where FileId ='"+FileID_NV[0]+"'";
		System.out.println(query); 
		ResultSet rsPostUpdate = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsPostUpdate.next()){
			flag= Component.verifyEquals(rsPostUpdate.getString("FileId"), FileID_NV[0], "FileId validation");
			publishResults(flag, rsPostUpdate.getString("FileId"), FileID_NV[0], "FileId validation ");
			flag= Component.verifyEquals(rsPostUpdate.getString("Sequence"), FileSequenceNumber_NV[0], "Sequence validation ");
			publishResults(flag, rsPostUpdate.getString("Sequence"), FileSequenceNumber_NV[0], "Sequence validation  ");
			flag= Component.verifyEquals(rsPostUpdate.getString("Source"), Source_NV[0], "Source validation");
			publishResults(flag, rsPostUpdate.getString("Source"), Source_NV[0], "Source validation ");
			flag= Component.verifyEquals(rsPostUpdate.getString("CreatedDate"), FileDateTime_NV[0], "FileDateTime_NV validation ");
			publishResults(flag, rsPostUpdate.getString("CreatedDate"),FileDateTime_NV[0], "FileDateTime_NV validation  ");
			flag= Component.verifyEquals(rsPostUpdate.getString("ItemCount").trim(),ExtractItemCount_NV[0].trim(), "ItemCount validation");
			publishResults(flag, rsPostUpdate.getString("ItemCount"),ExtractItemCount_NV[0], "ItemCount validation  ");
			DBValidations.validateItemId(rsPostUpdate.getString(1));
			DBValidations.validateTriggeringState(rsPostUpdate.getString(2));			
		}
		return rsPostUpdate;
	}
	
	
	
	// Description: This method gets the data in the DB for Posting Update Posting response Table 
	public static ResultSet getPostingUpdate_PostingResponseRecordAttributeValues(String fileName) throws Exception
	{
		Sequence_NV=getTagValueByXPATH(templateFilePath, fileName, "//ResponseSequence", "ResponseSequence");
		ItemId_NV=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		PostingType_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingType", "PostingType");
		Account_NV=getTagValueByXPATH(templateFilePath, fileName, "//Account", "Account");
		SortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//SortCode", "SortCode");
		NPAAccount_NV=getTagValueByXPATH(templateFilePath, fileName, "//NPAAccount", "NPAAccount");
		NPASortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//NPASortCode", "NPASortCode");
		Amount_NV=getTagValueByXPATH(templateFilePath, fileName, "//Amount", "Amount");
		RedirectInd_NV=getTagValueByXPATH(templateFilePath, fileName, "//RedirectionInd", "RedirectionInd");
		AccountingSystem_NV=getTagValueByXPATH(templateFilePath, fileName, "//AccountingSystem", "AccountingSystem");
		ResponseStatus_NV=getTagValueByXPATH(templateFilePath, fileName, "//ResponseStatus", "ResponseStatus");
		ResponseSubType_NV=getTagValueByXPATH(templateFilePath, fileName, "//ResponseSubType", "ResponseSubType");
		StatusCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//ResponseStatusCount", "ResponseStatusCount");
		AggregationCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//AggregationCount", "AggregationCount");
		RicherDataRef_NV=getTagValueByXPATH(templateFilePath, fileName, "//RicherDataRef", "RicherDataRef");
		FraudStatusCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudStatusCode", "FraudStatusCode");
		FraudReasonCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudReasonCode", "FraudReasonCode");
		CreditReference_NV=getTagValueByXPATH(templateFilePath, fileName, "//CreditReference", "CreditReference");
		Serial_NV=getTagValueByXPATH(templateFilePath, fileName, "//Serial", "Serial");
		query = "select Sequence,ItemId,PostingType,Account,SortCode,NPAAccount,NPASortCode,Amount,RedirectionInd, AccountingSystem, ResponseStatus, ResponseSubType, StatusCount, AggregationCount, RicherDataRef, FraudStatusCode, FraudReasonCode, CreditReference, Serial FROM Post.ResponseDetail"
				+ " where ItemId ='"+ItemId_NV[0]+"'";
		System.out.println(query); 
		ResultSet rsPostUpdate_rr = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsPostUpdate_rr.next()){
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("Sequence"), Sequence_NV[0], "Sequence validation");
			publishResults(flag, rsPostUpdate_rr.getString("Sequence"), Sequence_NV[0], "Sequence validation ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("ItemId"), ItemId_NV[0], "ItemId validation ");
			publishResults(flag, rsPostUpdate_rr.getString("ItemId"), ItemId_NV[0], "ItemId validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("PostingType"), PostingType_NV[0], "PostingType validation");
			publishResults(flag, rsPostUpdate_rr.getString("PostingType"), PostingType_NV[0], "PostingType validation ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("Account"), Account_NV[0], "Account validation ");
			publishResults(flag, rsPostUpdate_rr.getString("Account"),Account_NV[0], "Account validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("SortCode").trim(),SortCode_NV[0].trim(), "SortCode validation");
			publishResults(flag, rsPostUpdate_rr.getString("SortCode"),SortCode_NV[0], "SortCode validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("NPAAccount"), NPAAccount_NV[0], "FileId validation");
			publishResults(flag, rsPostUpdate_rr.getString("NPAAccount"), NPAAccount_NV[0], "FileId validation ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("NPASortCode").trim(), NPASortCode_NV[0].trim(), "Sequence validation ");
			publishResults(flag, rsPostUpdate_rr.getString("NPASortCode"), NPASortCode_NV[0], "Sequence validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("Amount"), Amount_NV[0], "Amount validation");
			publishResults(flag, rsPostUpdate_rr.getString("Amount"), Amount_NV[0], "Amount validation ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("RedirectionInd"), RedirectInd_NV[0], "RedirectionInd validation ");
			publishResults(flag, rsPostUpdate_rr.getString("RedirectionInd"),RedirectInd_NV[0], "RedirectionInd validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("AccountingSystem").trim(),AccountingSystem_NV[0].trim(), "AccountingSystem validation");
			publishResults(flag, rsPostUpdate_rr.getString("AccountingSystem"),AccountingSystem_NV[0], "AccountingSystem validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("ResponseStatus"),ResponseStatus_NV[0], "ResponseStatus validation");
			publishResults(flag, rsPostUpdate_rr.getString("ResponseStatus"),ResponseStatus_NV[0], "ResponseStatus validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("ResponseSubType").trim(),ResponseSubType_NV[0].trim(), "ResponseSubType validation");
			publishResults(flag, rsPostUpdate_rr.getString("ResponseSubType"),ResponseSubType_NV[0], "ResponseSubType validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("StatusCount").trim(),StatusCount_NV[0].trim(), "StatusCount validation");
			publishResults(flag, rsPostUpdate_rr.getString("StatusCount"),StatusCount_NV[0], "StatusCount validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("AggregationCount"),AggregationCount_NV[0], "AggregationCount validation");
			publishResults(flag, rsPostUpdate_rr.getString("AggregationCount"),AggregationCount_NV[0], "AggregationCount validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("RicherDataRef"), RicherDataRef_NV[0], "RicherDataRef validation");
			publishResults(flag, rsPostUpdate_rr.getString("RicherDataRef"), RicherDataRef_NV[0], "RicherDataRef validation ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("FraudStatusCode"), FraudStatusCode_NV[0], "FraudStatusCode validation ");
			publishResults(flag, rsPostUpdate_rr.getString("FraudStatusCode"), FraudStatusCode_NV[0], "FraudStatusCode validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("FraudReasonCode"), FraudReasonCode_NV[0], "FraudReasonCode validation");
			publishResults(flag, rsPostUpdate_rr.getString("FraudReasonCode"), FraudReasonCode_NV[0], "FraudReasonCode validation ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("CreditReference"), CreditReference_NV[0], "CreditReference validation ");
			publishResults(flag, rsPostUpdate_rr.getString("CreditReference"),CreditReference_NV[0], "CreditReference validation  ");
			flag= Component.verifyEquals(rsPostUpdate_rr.getString("Serial").trim(),Serial_NV[0].trim(), "Serial validation");
			publishResults(flag, rsPostUpdate_rr.getString("Serial"),Serial_NV[0], "Serial validation  ");
		}
		return rsPostUpdate_rr;
	}
	
	
	// Description: This method gets the data in the DB for Posting Update response detail Table 
	public static ResultSet getPostingUpdate_ResponseDetailAttributeValues(String fileName) throws Exception
	{
		StatusSequence_NV=getTagValueByXPATH(templateFilePath, fileName, "//StatusSequence", "StatusSequence");
		ReasonCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//ReasonCode", "ReasonCode");
		ReasonText_NV=getTagValueByXPATH(templateFilePath, fileName, "//ReasonText", "ReasonText");
		query = "select Sequence, ReasonCode,ReasonText FROM Post.ResponseStatus"
				+ " where ReasonCode ='"+ReasonCode_NV[0]+"'";
		System.out.println(query); 
		ResultSet rsPostUpdate = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsPostUpdate.next()){
			flag= Component.verifyEquals(rsPostUpdate.getString("Sequence"), StatusSequence_NV[0], "Sequence validation");
			publishResults(flag, rsPostUpdate.getString("Sequence"), StatusSequence_NV[0], "Sequence validation ");
			flag= Component.verifyEquals(rsPostUpdate.getString("ReasonCode"), ReasonCode_NV[0], "ReasonCode validation ");
			publishResults(flag, rsPostUpdate.getString("ReasonCode"), ReasonCode_NV[0], "ReasonCode validation  ");
			flag= Component.verifyEquals(rsPostUpdate.getString("ReasonText"), ReasonText_NV[0], "ReasonText validation");
			publishResults(flag, rsPostUpdate.getString("ReasonText"), ReasonText_NV[0], "ReasonText validation ");
			DBValidations.validateStateSequence(rsPostUpdate.getString(1));
			DBValidations.validateReasonCode(rsPostUpdate.getString(2));
			DBValidations.validateReasonText(rsPostUpdate.getString(3));
		}
		return rsPostUpdate;
	}
	
	
	// Description: This method gets the data in the DB for Posting Update response detail Table 
		public static ResultSet getPostingUpdate_StatusRecords(String fileName) throws Exception
		{
			RicherDataRef_NV=getTagValueByXPATH(templateFilePath, fileName, "//RicherDataRef", "RicherDataRef");
			FraudStatusCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudStatusCode", "FraudStatusCode");
			FraudReasonCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudReasonCode", "FraudReasonCode");
			CreditReference_NV=getTagValueByXPATH(templateFilePath, fileName, "//CreditReference", "CreditReference");
			Serial_NV=getTagValueByXPATH(templateFilePath, fileName, "//Serial", "Serial");
			query = "select RicherDataRef, FraudStatusCode,FraudReasonCode,CreditReference,Serial FROM Post.ResponseDetail"
					+ " where FraudReasonCode ='"+FraudReasonCode_NV[0]+"'";
			System.out.println(query); 
			ResultSet rsPostUpdate = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
			while(rsPostUpdate.next()){
				flag= Component.verifyEquals(rsPostUpdate.getString("RicherDataRef"), RicherDataRef_NV[0], "RicherDataRef validation");
				publishResults(flag, rsPostUpdate.getString("RicherDataRef"), RicherDataRef_NV[0], "RicherDataRef validation ");
				flag= Component.verifyEquals(rsPostUpdate.getString("FraudStatusCode"), FraudStatusCode_NV[0], "FraudStatusCode validation ");
				publishResults(flag, rsPostUpdate.getString("FraudStatusCode"), FraudStatusCode_NV[0], "FraudStatusCode validation  ");
				flag= Component.verifyEquals(rsPostUpdate.getString("FraudReasonCode"), FraudReasonCode_NV[0], "FraudReasonCode validation");
				publishResults(flag, rsPostUpdate.getString("FraudReasonCode"), FraudReasonCode_NV[0], "FraudReasonCode validation ");
				flag= Component.verifyEquals(rsPostUpdate.getString("CreditReference"), CreditReference_NV[0], "CreditReference validation");
				publishResults(flag, rsPostUpdate.getString("CreditReference"), CreditReference_NV[0], "CreditReference validation ");
				flag= Component.verifyEquals(rsPostUpdate.getString("Serial"), Serial_NV[0], "Serial validation ");
				publishResults(flag, rsPostUpdate.getString("Serial"), Serial_NV[0], "Serial validation  ");
				DBValidations.validateRicherDataRef(rsPostUpdate.getString(1));
				DBValidations.validateFraudReasonCode(rsPostUpdate.getString(3));
				DBValidations.validateFraudStatusCode(rsPostUpdate.getString(2));
				DBValidations.validateCreditReference(rsPostUpdate.getString(4));
				DBValidations.validateSerial(rsPostUpdate.getString(5));
			}
			return rsPostUpdate;
		}
	
		
		// Description: This method gets the data in the DB for Posting update Posting Extract Table 
		public static ResultSet getPostingExtractAttributeValues(String fileName) throws Exception
		{
			ExtractSequence_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractSequence", "ExtractSequence");
			ExtractRevision_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractRevision", "ExtractRevision");
			FileId_NV=getTagValueByXPATH(templateFilePath, fileName, "//FileId", "FileId");
			Weekday_NV=getTagValueByXPATH(templateFilePath, fileName, "//Weekday", "Weekday");
			FileType_NV=getTagValueByXPATH(templateFilePath, fileName, "//FileType", "FileType");
			Currency_NV=getTagValueByXPATH(templateFilePath, fileName, "//Currency", "Currency");
			Environment_NV=getTagValueByXPATH(templateFilePath, fileName, "//Environment", "Environment");
			ExtractStartDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractStartDateTime", "ExtractStartDateTime");
			ExtractEndDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractEndDateTime", "ExtractEndDateTime");
			ExtractItemCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractItemCount", "ExtractItemCount");
			query = "select ExtractSequence, ExtractRevision,FileId,Weekday,FileType,Currency,Environment,StartDate,EndDate,ItemCount FROM Post.PostingExtract"
					+ " where FileId ='"+FileId_NV[0]+"'";
			System.out.println(query); 
			ResultSet rsPostExtract = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
			while(rsPostExtract.next()){
				String startdatet_db= rsPostExtract.getString("StartDate").replaceAll("[A-Za-z +]","");
				String startdate_xml = ExtractStartDateTime_NV[0].replaceAll("[A-Za-z+]","");
				String enddate_db= rsPostExtract.getString("EndDate").replaceAll("[A-Za-z +]","");
				String enddate_xml = ExtractEndDateTime_NV[0].replaceAll("[A-Za-z+]","");
				flag= Component.verifyEquals(rsPostExtract.getString("ExtractSequence"), ExtractSequence_NV[0], "ExtractSequence validation");
				publishResults(flag, rsPostExtract.getString("ExtractSequence"), ExtractSequence_NV[0], "ExtractSequence validation ");
				flag= Component.verifyEquals(rsPostExtract.getString("ExtractRevision"), ExtractRevision_NV[0], "ExtractRevision validation ");
				publishResults(flag, rsPostExtract.getString("ExtractRevision"), ExtractRevision_NV[0], "ExtractRevision validation  ");
				flag= Component.verifyEquals(rsPostExtract.getString("Weekday"), Weekday_NV[0], "Weekday validation");
				publishResults(flag, rsPostExtract.getString("Weekday"), Weekday_NV[0], "Weekday validation ");
				flag= Component.verifyEquals(rsPostExtract.getString("FileType"), FileType_NV[0], "FileType validation ");
				publishResults(flag, rsPostExtract.getString("FileType"), FileType_NV[0], "FileType validation  ");
				flag= Component.verifyEquals(rsPostExtract.getString("Currency"),Currency_NV[0], "Currency validation");
				publishResults(flag, rsPostExtract.getString("Currency"), Currency_NV[0], "Currency validation ");
				flag= Component.verifyEquals(rsPostExtract.getString("Environment"), Environment_NV[0], "Environment validation ");
				publishResults(flag, rsPostExtract.getString("Environment"), Environment_NV[0], "Environment validation  ");
				flag= Component.verifyEquals(rsPostExtract.getString("FileId"), FileId_NV[0], "FileId validation");
				publishResults(flag, rsPostExtract.getString("FileId"), FileId_NV[0], "FileId validation ");
				
				/*flag= Component.verifyEquals(rsPostExtract.getString("StartDate"), ExtractStartDateTime_NV[0], "StartDate validation");
				publishResults(flag, rsPostExtract.getString("StartDate"), ExtractStartDateTime_NV[0], "StartDate validation ");
				flag= Component.verifyEquals(rsPostExtract.getString("EndDate"), ExtractEndDateTime_NV[0], "EndDate validation");
				publishResults(flag, rsPostExtract.getString("EndDate"), ExtractEndDateTime_NV[0], "EndDate validation ");
				*/
				
				flag= Component.verifyEquals(startdatet_db.substring(0, startdatet_db.length()-4),startdate_xml.substring(0, startdate_xml.length()-5), "StartDate record Validation");
				publishResults(flag, startdatet_db.substring(0, startdatet_db.length()-4),startdate_xml.substring(0, startdate_xml.length()-5), "StartDate Value Validation");
			

				flag= Component.verifyEquals(enddate_db.substring(0, enddate_db.length()-4),enddate_xml.substring(0, enddate_xml.length()-5), "End date record Validation");
				publishResults(flag, enddate_db.substring(0, enddate_db.length()-4),enddate_xml.substring(0, enddate_xml.length()-5), "End date Value Validation");
			
				
				flag= Component.verifyEquals(rsPostExtract.getString("ItemCount"), ExtractItemCount_NV[0], "Extract Item Count validation ");
				publishResults(flag, rsPostExtract.getString("ItemCount"), ExtractItemCount_NV[0], "Extract Item Count validation  ");
			}
			return rsPostExtract;
		}
	
		
		// Description: This method gets the data in the DB for Posting update Posting Extract Table 
				public static ResultSet getPostingExtractWithoutFileIdAttributeValues(String fileName) throws Exception
				{
					ExtractSequence_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractSequence", "ExtractSequence");
					ExtractRevision_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractRevision", "ExtractRevision");
					Weekday_NV=getTagValueByXPATH(templateFilePath, fileName, "//Weekday", "Weekday");
					FileType_NV=getTagValueByXPATH(templateFilePath, fileName, "//FileType", "FileType");
					Currency_NV=getTagValueByXPATH(templateFilePath, fileName, "//Currency", "Currency");
					Environment_NV=getTagValueByXPATH(templateFilePath, fileName, "//Environment", "Environment");
					ExtractStartDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractStartDateTime", "ExtractStartDateTime");
					ExtractEndDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractEndDateTime", "ExtractEndDateTime");
					ExtractItemCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//ExtractItemCount", "ExtractItemCount");
					query = "select ExtractSequence, ExtractRevision,FileId,Weekday,FileType,Currency,Environment,StartDate,EndDate,ItemCount FROM Post.PostingExtract"
							+ " where ExtractSequence ='"+ExtractSequence_NV[0]+"'";
					System.out.println(query); 
					ResultSet rsPostExtract = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
					while(rsPostExtract.next()){
						String startdatet_db= rsPostExtract.getString("StartDate").replaceAll("[A-Za-z +]","");
						String startdate_xml = ExtractStartDateTime_NV[0].replaceAll("[A-Za-z+]","");
						String enddate_db= rsPostExtract.getString("EndDate").replaceAll("[A-Za-z +]","");
						String enddate_xml = ExtractEndDateTime_NV[0].replaceAll("[A-Za-z+]","");
						flag= Component.verifyEquals(rsPostExtract.getString("ExtractSequence"), ExtractSequence_NV[0], "ExtractSequence validation");
						publishResults(flag, rsPostExtract.getString("ExtractSequence"), ExtractSequence_NV[0], "ExtractSequence validation ");
						flag= Component.verifyEquals(rsPostExtract.getString("ExtractRevision"), ExtractRevision_NV[0], "ExtractRevision validation ");
						publishResults(flag, rsPostExtract.getString("ExtractRevision"), ExtractRevision_NV[0], "ExtractRevision validation  ");
						flag= Component.verifyEquals(rsPostExtract.getString("Weekday"), Weekday_NV[0], "Weekday validation");
						publishResults(flag, rsPostExtract.getString("Weekday"), Weekday_NV[0], "Weekday validation ");
						flag= Component.verifyEquals(rsPostExtract.getString("FileType"), FileType_NV[0], "FileType validation ");
						publishResults(flag, rsPostExtract.getString("FileType"), FileType_NV[0], "FileType validation  ");
						flag= Component.verifyEquals(rsPostExtract.getString("Currency"),Currency_NV[0], "Currency validation");
						publishResults(flag, rsPostExtract.getString("Currency"), Currency_NV[0], "Currency validation ");
						flag= Component.verifyEquals(rsPostExtract.getString("Environment"), Environment_NV[0], "Environment validation ");
						publishResults(flag, rsPostExtract.getString("Environment"), Environment_NV[0], "Environment validation  ");
						/*flag= Component.verifyEquals(rsPostExtract.getString("FileId"), FileId_NV[0], "FileId validation");
						publishResults(flag, rsPostExtract.getString("FileId"), FileId_NV[0], "FileId validation ");
						*/
						/*flag= Component.verifyEquals(rsPostExtract.getString("StartDate"), ExtractStartDateTime_NV[0], "StartDate validation");
						publishResults(flag, rsPostExtract.getString("StartDate"), ExtractStartDateTime_NV[0], "StartDate validation ");
						flag= Component.verifyEquals(rsPostExtract.getString("EndDate"), ExtractEndDateTime_NV[0], "EndDate validation");
						publishResults(flag, rsPostExtract.getString("EndDate"), ExtractEndDateTime_NV[0], "EndDate validation ");
						*/
						
						flag= Component.verifyEquals(startdatet_db.substring(0, startdatet_db.length()-4),startdate_xml.substring(0, startdate_xml.length()-5), "StartDate record Validation");
						publishResults(flag, startdatet_db.substring(0, startdatet_db.length()-4),startdate_xml.substring(0, startdate_xml.length()-5), "StartDate Value Validation");
					

						flag= Component.verifyEquals(enddate_db.substring(0, enddate_db.length()-4),enddate_xml.substring(0, enddate_xml.length()-5), "End date record Validation");
						publishResults(flag, enddate_db.substring(0, enddate_db.length()-4),enddate_xml.substring(0, enddate_xml.length()-5), "End date Value Validation");
					
						
						flag= Component.verifyEquals(rsPostExtract.getString("ItemCount"), ExtractItemCount_NV[0], "Extract Item Count validation ");
						publishResults(flag, rsPostExtract.getString("ItemCount"), ExtractItemCount_NV[0], "Extract Item Count validation  ");
					}
					return rsPostExtract;
				}
				
		
		// Description: This method gets the data in the DB for Posting update Posting Extract Table 
				public static ResultSet getPostingItemEntriesAttributeValues(String fileName) throws Exception
				{
					ItemId_NV=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
					PostingAttempt_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingAttempt", "PostingAttempt");
					PostingSequence_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingSequence", "PostingSequence");
					PostingType_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingType", "PostingType");
					PostingSubType_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingSubType", "PostingSubType");
					Channel_NV=getTagValueByXPATH(templateFilePath, fileName, "//Channel", "Channel");
					PostingDrCrEntry_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingDrCrEntry", "PostingDrCrEntry");
					PostingSource_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingSource", "PostingSource");
					ResponseIdSource_NV=getTagValueByXPATH(templateFilePath, fileName, "//ResponseIdSource", "ResponseIdSource");
					PostingDay_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingDay", "PostingDay");
					ClearingDate_NV=getTagValueByXPATH(templateFilePath, fileName, "//ClearingDate", "ClearingDate");
					SettlementDate_NV=getTagValueByXPATH(templateFilePath, fileName, "//SettlementDate", "SettlementDate");
					PostedAmount_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostedAmount", "PostedAmount");
					PostingOverrideFlag_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingOverrideFlag", "PostingOverrideFlag");
					NPASortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//NPASortCode", "NPASortCode");
					NPAAccount_NV=getTagValueByXPATH(templateFilePath, fileName, "//NPAAccount", "NPAAccount");
					SupportingInfo_NV=getTagValueByXPATH(templateFilePath, fileName, "//SupportingInfo", "SupportingInfo");
					ChequeCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//ChequeCount", "ChequeCount");
					CollectingParticipantId_NV=getTagValueByXPATH(templateFilePath, fileName, "//CollectingParticipantId", "CollectingParticipantId");
					CollectingLocation_NV=getTagValueByXPATH(templateFilePath, fileName, "//CollectingLocation", "CollectingLocation");
					query = "select ItemId, Attempt,Sequence,Type,SubType,Channel,PostingDrCrEntry,PostingSource,ResponseIdSource,PostingDay,ClearingDate,SettlementDate,PostedAmount,OverrideFlag,NPASortCode, NPAAccount,SupportingInfo,ChequeCount,CollectingParticipantId, CollectingLocation FROM Post.PostingItem"
							+ " where ItemId ='"+ItemId_NV[0]+"'";
					System.out.println(query); 
					ResultSet rsPostItem = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
					while(rsPostItem.next()){
						flag= Component.verifyEquals(rsPostItem.getString("ItemId"), ItemId_NV[0], "ItemId validation");
						publishResults(flag, rsPostItem.getString("ItemId"), ItemId_NV[0], "ItemId validation ");
						flag= Component.verifyEquals(rsPostItem.getString("Type"), PostingType_NV[0], "Posting Type validation");
						publishResults(flag, rsPostItem.getString("Type"), PostingType_NV[0], "Posting Type validation ");
						flag= Component.verifyEquals(rsPostItem.getString("SubType"), PostingSubType_NV[0], "SubType validation");
						publishResults(flag, rsPostItem.getString("SubType"), PostingSubType_NV[0], "SubType validation ");
						flag= Component.verifyEquals(rsPostItem.getString("Channel"), Channel_NV[0], "Channel validation");
						publishResults(flag, rsPostItem.getString("Channel"), Channel_NV[0], "Channel validation ");
						flag= Component.verifyEquals(rsPostItem.getString("PostingDrCrEntry"), PostingDrCrEntry_NV[0], "PostingDrCrEntry validation ");
						publishResults(flag, rsPostItem.getString("PostingDrCrEntry"), PostingDrCrEntry_NV[0], "PostingDrCrEntry validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("PostingSource"),PostingSource_NV[0], "PostingSource validation");
						publishResults(flag, rsPostItem.getString("PostingSource"), PostingSource_NV[0], "PostingSource validation ");
						flag= Component.verifyEquals(rsPostItem.getString("ResponseIdSource"), ResponseIdSource_NV[0], "ResponseIdSource validation ");
						publishResults(flag, rsPostItem.getString("ResponseIdSource"), ResponseIdSource_NV[0], "ResponseIdSource validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("PostingDay"), PostingDay_NV[0], "PostingDay validation");
						publishResults(flag, rsPostItem.getString("PostingDay"), PostingDay_NV[0], "PostingDay validation ");
						flag= Component.verifyEquals(rsPostItem.getString("ClearingDate"), ClearingDate_NV[0], "ClearingDate validation ");
						publishResults(flag, rsPostItem.getString("ClearingDate"), ClearingDate_NV[0], "ClearingDate validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("SettlementDate"), SettlementDate_NV[0], "SettlementDate validation ");
						publishResults(flag, rsPostItem.getString("SettlementDate"), SettlementDate_NV[0], "SettlementDate validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("PostedAmount"), PostedAmount_NV[0], "PostedAmount validation ");
						publishResults(flag, rsPostItem.getString("PostedAmount"), PostedAmount_NV[0], "PostedAmount validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("OverrideFlag"), PostingOverrideFlag_NV[0], "Posting Override Flag validation ");
						publishResults(flag, rsPostItem.getString("OverrideFlag"), PostingOverrideFlag_NV[0], "Posting Override Flag validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("NPASortCode"), NPASortCode_NV[0], "NPASortCode validation ");
						publishResults(flag, rsPostItem.getString("NPASortCode"), NPASortCode_NV[0], "NPASortCode validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("NPAAccount"), NPAAccount_NV[0], "NPAAccount validation ");
						publishResults(flag, rsPostItem.getString("NPAAccount"), NPAAccount_NV[0], "NPAAccount validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("SupportingInfo"), SupportingInfo_NV[0], "SupportingInfo validation ");
						publishResults(flag, rsPostItem.getString("SupportingInfo"), SupportingInfo_NV[0], "SupportingInfo validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("ChequeCount"), ChequeCount_NV[0], "ChequeCount validation ");
						publishResults(flag, rsPostItem.getString("ChequeCount"), ChequeCount_NV[0], "ChequeCount validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("CollectingParticipantId"), CollectingParticipantId_NV[0], "CollectingParticipantId validation ");
						publishResults(flag, rsPostItem.getString("CollectingParticipantId"), CollectingParticipantId_NV[0], "CollectingParticipantId validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("CollectingLocation"), CollectingLocation_NV[0], "CollectingLocation validation ");
						publishResults(flag, rsPostItem.getString("CollectingLocation"), CollectingLocation_NV[0], "CollectingLocation validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("Attempt"), PostingAttempt_NV[0], "PostingAttempt validation ");
						publishResults(flag, rsPostItem.getString("Attempt"), PostingAttempt_NV[0], "PostingAttempt validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("Sequence"), PostingSequence_NV[0], "PostingSequence validation");
						publishResults(flag, rsPostItem.getString("Sequence"), PostingSequence_NV[0], "PostingSequence validation ");
					
					}
					return rsPostItem;
				}
							
				// Description: This method gets the data in the DB for Posting update Posting Extract Table 
				public static ResultSet getPostingItemEntriesWithoutNPAAttributeValues(String fileName) throws Exception
				{
					ItemId_NV=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
					PostingAttempt_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingAttempt", "PostingAttempt");
					PostingSequence_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingSequence", "PostingSequence");
					PostingType_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingType", "PostingType");
					PostingSubType_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingSubType", "PostingSubType");
					Channel_NV=getTagValueByXPATH(templateFilePath, fileName, "//Channel", "Channel");
					PostingDrCrEntry_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingDrCrEntry", "PostingDrCrEntry");
					PostingSource_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingSource", "PostingSource");
					ResponseIdSource_NV=getTagValueByXPATH(templateFilePath, fileName, "//ResponseIdSource", "ResponseIdSource");
					PostingDay_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingDay", "PostingDay");
					ClearingDate_NV=getTagValueByXPATH(templateFilePath, fileName, "//ClearingDate", "ClearingDate");
					SettlementDate_NV=getTagValueByXPATH(templateFilePath, fileName, "//SettlementDate", "SettlementDate");
					PostedAmount_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostedAmount", "PostedAmount");
					PostingOverrideFlag_NV=getTagValueByXPATH(templateFilePath, fileName, "//PostingOverrideFlag", "PostingOverrideFlag");
					/*NPASortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//NPASortCode", "NPASortCode");
					NPAAccount_NV=getTagValueByXPATH(templateFilePath, fileName, "//NPAAccount", "NPAAccount");
					*/
					SupportingInfo_NV=getTagValueByXPATH(templateFilePath, fileName, "//SupportingInfo", "SupportingInfo");
					ChequeCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//ChequeCount", "ChequeCount");
					CollectingParticipantId_NV=getTagValueByXPATH(templateFilePath, fileName, "//CollectingParticipantId", "CollectingParticipantId");
					CollectingLocation_NV=getTagValueByXPATH(templateFilePath, fileName, "//CollectingLocation", "CollectingLocation");
					query = "select ItemId,Type,SubType,Channel,PostingDrCrEntry,PostingSource,ResponseIdSource,PostingDay,ClearingDate,SettlementDate,PostedAmount,OverrideFlag,SupportingInfo,CollectingParticipantId, CollectingLocation FROM Post.PostingItem"
							+ " where ItemId ='"+ItemId_NV[0]+"'";
					System.out.println(query); 
					ResultSet rsPostItem = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
					while(rsPostItem.next()){
						flag= Component.verifyEquals(rsPostItem.getString("ItemId"), ItemId_NV[0], "ItemId validation");
						publishResults(flag, rsPostItem.getString("ItemId"), ItemId_NV[0], "ItemId validation ");
						flag= Component.verifyEquals(rsPostItem.getString("Type"), PostingType_NV[0], "Posting Type validation");
						publishResults(flag, rsPostItem.getString("Type"), PostingType_NV[0], "Posting Type validation ");
						flag= Component.verifyEquals(rsPostItem.getString("SubType"), PostingSubType_NV[0], "SubType validation");
						publishResults(flag, rsPostItem.getString("SubType"), PostingSubType_NV[0], "SubType validation ");
						flag= Component.verifyEquals(rsPostItem.getString("Channel"), Channel_NV[0], "Channel validation");
						publishResults(flag, rsPostItem.getString("Channel"), Channel_NV[0], "Channel validation ");
						flag= Component.verifyEquals(rsPostItem.getString("PostingDrCrEntry"), PostingDrCrEntry_NV[0], "PostingDrCrEntry validation ");
						publishResults(flag, rsPostItem.getString("PostingDrCrEntry"), PostingDrCrEntry_NV[0], "PostingDrCrEntry validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("PostingSource"),PostingSource_NV[0], "PostingSource validation");
						publishResults(flag, rsPostItem.getString("PostingSource"), PostingSource_NV[0], "PostingSource validation ");
						flag= Component.verifyEquals(rsPostItem.getString("ResponseIdSource"), ResponseIdSource_NV[0], "ResponseIdSource validation ");
						publishResults(flag, rsPostItem.getString("ResponseIdSource"), ResponseIdSource_NV[0], "ResponseIdSource validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("PostingDay"), PostingDay_NV[0], "PostingDay validation");
						publishResults(flag, rsPostItem.getString("PostingDay"), PostingDay_NV[0], "PostingDay validation ");
						flag= Component.verifyEquals(rsPostItem.getString("ClearingDate"), ClearingDate_NV[0], "ClearingDate validation ");
						publishResults(flag, rsPostItem.getString("ClearingDate"), ClearingDate_NV[0], "ClearingDate validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("SettlementDate"), SettlementDate_NV[0], "SettlementDate validation ");
						publishResults(flag, rsPostItem.getString("SettlementDate"), SettlementDate_NV[0], "SettlementDate validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("PostedAmount"), PostedAmount_NV[0], "PostedAmount validation ");
						publishResults(flag, rsPostItem.getString("PostedAmount"), PostedAmount_NV[0], "PostedAmount validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("OverrideFlag"), PostingOverrideFlag_NV[0], "Posting Override Flag validation ");
						publishResults(flag, rsPostItem.getString("OverrideFlag"), PostingOverrideFlag_NV[0], "Posting Override Flag validation  ");
						/*flag= Component.verifyEquals(rsPostItem.getString("NPASortCode"), NPASortCode_NV[0], "NPASortCode validation ");
						publishResults(flag, rsPostItem.getString("NPASortCode"), NPASortCode_NV[0], "NPASortCode validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("NPAAccount"), NPAAccount_NV[0], "NPAAccount validation ");
						publishResults(flag, rsPostItem.getString("NPAAccount"), NPAAccount_NV[0], "NPAAccount validation  ");
						*/flag= Component.verifyEquals(rsPostItem.getString("SupportingInfo"), SupportingInfo_NV[0], "SupportingInfo validation ");
						publishResults(flag, rsPostItem.getString("SupportingInfo"), SupportingInfo_NV[0], "SupportingInfo validation  ");
						/*flag= Component.verifyEquals(rsPostItem.getString("ChequeCount"), ChequeCount_NV[0], "ChequeCount validation ");
						publishResults(flag, rsPostItem.getString("ChequeCount"), ChequeCount_NV[0], "ChequeCount validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("CollectingParticipantId"), CollectingParticipantId_NV[0], "CollectingParticipantId validation ");
						publishResults(flag, rsPostItem.getString("CollectingParticipantId"), CollectingParticipantId_NV[0], "CollectingParticipantId validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("CollectingLocation"), CollectingLocation_NV[0], "CollectingLocation validation ");
						publishResults(flag, rsPostItem.getString("CollectingLocation"), CollectingLocation_NV[0], "CollectingLocation validation  ");
						/*flag= Component.verifyEquals(rsPostItem.getString("Attempt"), PostingAttempt_NV[0], "PostingAttempt validation ");
						publishResults(flag, rsPostItem.getString("Attempt"), PostingAttempt_NV[0], "PostingAttempt validation  ");
						flag= Component.verifyEquals(rsPostItem.getString("Sequence"), PostingSequence_NV[0], "PostingSequence validation");
						publishResults(flag, rsPostItem.getString("Sequence"), PostingSequence_NV[0], "PostingSequence validation ");
					*/
					}
					return rsPostItem;
				}
			
				// Description: This method gets the data in the DB for Posting update Posting debit Table 
				public static ResultSet getPostingDebitAttributeValues(String fileName) throws Exception
				{
					ItemId_NV=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
					SortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//SortCode", "SortCode");
					Account_NV=getTagValueByXPATH(templateFilePath, fileName, "//Account", "Account");
					Serial_NV=getTagValueByXPATH(templateFilePath, fileName, "//Serial", "Serial");
					TranCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//TranCode", "TranCode");
					SwitchedSortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//SwitchedSortCode", "SwitchedSortCode");
					SwitchedAccount_NV=getTagValueByXPATH(templateFilePath, fileName, "//SwitchedAccount", "SwitchedAccount");
					query = "select ItemId, SortCode,Account,Serial,TranCode,SwitchedSortCode,SwitchedAccount FROM Post.PostingDebit"
							+ " where ItemId ='"+ItemId_NV[0]+"'";
					System.out.println(query); 
					ResultSet rsPostdebit = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
					while(rsPostdebit.next()){
						flag= Component.verifyEquals(rsPostdebit.getString("ItemId"), ItemId_NV[0], "ItemId validation");
						publishResults(flag, rsPostdebit.getString("ItemId"), ItemId_NV[0], "ItemId validation ");
						flag= Component.verifyEquals(rsPostdebit.getString("SortCode"), SortCode_NV[0], "SortCode validation ");
						publishResults(flag, rsPostdebit.getString("SortCode"), SortCode_NV[0], "SortCode validation  ");
						flag= Component.verifyEquals(rsPostdebit.getString("Account"), Account_NV[0], "Account validation");
						publishResults(flag, rsPostdebit.getString("Account"), Account_NV[0], "Account validation ");
						flag= Component.verifyEquals(rsPostdebit.getString("Serial"), Serial_NV[0], "Serial validation");
						publishResults(flag, rsPostdebit.getString("Serial"), Serial_NV[0], "Serial validation ");
						flag= Component.verifyEquals(rsPostdebit.getString("TranCode"), TranCode_NV[0].trim(), "TranCode validation ");
						publishResults(flag, rsPostdebit.getString("TranCode"), TranCode_NV[0].trim(), "TranCode validation  ");
						flag= Component.verifyEquals(rsPostdebit.getString("SwitchedSortCode"),SwitchedSortCode_NV[0], "Switched Sort Code validation");
						publishResults(flag, rsPostdebit.getString("SwitchedSortCode"), SwitchedSortCode_NV[0], "Switched Sort Code validation ");
						flag= Component.verifyEquals(rsPostdebit.getString("SwitchedAccount"), SwitchedAccount_NV[0], "Switched Account validation ");
						publishResults(flag, rsPostdebit.getString("SwitchedAccount"), SwitchedAccount_NV[0], "Switched Account validation");
					}
					return rsPostdebit;
				}
			
				// Description: This method gets the data in the DB for Posting update Posting Credit Table 
				public static ResultSet getPostingCreditAttributeValues(String fileName) throws Exception
				{
					ItemId_NV=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
					SortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//SortCode", "SortCode");
					Account_NV=getTagValueByXPATH(templateFilePath, fileName, "//Account", "Account");
					Reference_NV=getTagValueByXPATH(templateFilePath, fileName, "//Reference", "Reference");
					TranCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//TranCode", "TranCode");
					OriginalAmount_NV=getTagValueByXPATH(templateFilePath, fileName, "//OriginalAmount", "OriginalAmount");
					OriginalSortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//OriginalSortCode", "OriginalSortCode");
					OriginalAccount_NV=getTagValueByXPATH(templateFilePath, fileName, "//OriginalAccount", "OriginalAccount");
					CreditExceptionCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//CreditExceptionCode", "CreditExceptionCode");
					BeneficiaryName_NV=getTagValueByXPATH(templateFilePath, fileName, "//BeneficiaryName", "BeneficiaryName");
					query = "select ItemId, SortCode,Account,Reference,TranCode,OriginalAmount,OriginalSortCode,OriginalAccount,CreditExceptionCode,BeneficiaryName FROM Post.PostingCredit"
							+ " where ItemId ='"+ItemId_NV[0]+"'";
					System.out.println(query); 
					ResultSet rsPostCredit = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
					while(rsPostCredit.next()){
						flag= Component.verifyEquals(rsPostCredit.getString("ItemId"), ItemId_NV[0], "ItemId validation");
						publishResults(flag, rsPostCredit.getString("ItemId"), ItemId_NV[0], "ItemId validation ");
						flag= Component.verifyEquals(rsPostCredit.getString("SortCode"), SortCode_NV[0], "SortCode validation ");
						publishResults(flag, rsPostCredit.getString("SortCode"), SortCode_NV[0], "SortCode validation  ");
						flag= Component.verifyEquals(rsPostCredit.getString("Account"), Account_NV[0], "Account validation");
						publishResults(flag, rsPostCredit.getString("Account"), Account_NV[0], "Account validation ");
						flag= Component.verifyEquals(rsPostCredit.getString("Reference"), Reference_NV[0], "Reference validation");
						publishResults(flag, rsPostCredit.getString("Reference"), Reference_NV[0], "Reference validation ");
						flag= Component.verifyEquals(rsPostCredit.getString("TranCode"), TranCode_NV[0], "TranCode validation ");
						publishResults(flag, rsPostCredit.getString("TranCode"), TranCode_NV[0], "TranCode validation  ");
						flag= Component.verifyEquals(rsPostCredit.getString("OriginalAmount"),OriginalAmount_NV[0], "OriginalAmount validation");
						publishResults(flag, rsPostCredit.getString("OriginalAmount"), SwitchedSortCode_NV[0], "OriginalAmount validation ");
						flag= Component.verifyEquals(rsPostCredit.getString("OriginalSortCode"), OriginalSortCode_NV[0], "Original Sort Code validation ");
						publishResults(flag, rsPostCredit.getString("OriginalSortCode"), OriginalSortCode_NV[0], "Original Sort Code validation");
						flag= Component.verifyEquals(rsPostCredit.getString("OriginalAmount"),OriginalAmount_NV[0], "OriginalAmount validation");
						publishResults(flag, rsPostCredit.getString("OriginalAmount"), OriginalAmount_NV[0], "OriginalAmount validation ");
						flag= Component.verifyEquals(rsPostCredit.getString("CreditExceptionCode"),CreditExceptionCode_NV[0], "CreditExceptionCode validation");
						publishResults(flag, rsPostCredit.getString("CreditExceptionCode"), CreditExceptionCode_NV[0], "CreditExceptionCode validation ");
						flag= Component.verifyEquals(rsPostCredit.getString("BeneficiaryName"),BeneficiaryName_NV[0], "BeneficiaryName validation");
						publishResults(flag, rsPostCredit.getString("BeneficiaryName"), BeneficiaryName_NV[0], "BeneficiaryName validation ");
					}
					return rsPostCredit;
				}
				
	// Description: This method gets the data in the DB for Entity Table 
	public static ResultSet getEntityHeaderAttributeValues(String fileName) throws Exception
	{
		String[] EntityIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//EntityId", "EntityId");
		query = "select EntityType, EntityIdentifier, Revision as StateRevision, EntityState, SourceDateTime FROM Base.Entity"
		//		+ " where EntityIdentifier ='"+PostingExtractFile.entityHeaderSetTagWiseData.get("EntityId")+"'";
				+ " where EntityIdentifier='"+EntityIDFromXML[0]+"'";
		System.out.println(query); 
		ResultSet rsEntity = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsEntity.next()){
			String sourcedatetime_db= rsEntity.getString("SourceDateTime").replaceAll("[A-Za-z +]","");
			String sourcedatetime_xml = FetchXMLNodeValues.entityHeaderSetTagWiseData.get("SourceDateTime").replaceAll("[A-Za-z+]","");
			flag= Component.verifyEquals(rsEntity.getString("EntityType"), FetchXMLNodeValues.entityHeaderSetTagWiseData.get("EntityType"), "EntityType record Validation");
			publishResults(flag, rsEntity.getString("EntityType"), FetchXMLNodeValues.entityHeaderSetTagWiseData.get("EntityType"), "EntityType Value Validation");
			flag= Component.verifyEquals(rsEntity.getString("EntityIdentifier"), EntityIDFromXML[0], "EntityIdentifier record Validation");
			publishResults(flag, rsEntity.getString("EntityIdentifier"), EntityIDFromXML[0], "EntityIdentifier Value Validation ");
			flag= Component.verifyEquals(rsEntity.getString("StateRevision"), FetchXMLNodeValues.entityHeaderSetTagWiseData.get("StateRevision"), "StateRevision record Validation");
			publishResults(flag, rsEntity.getString("StateRevision"), FetchXMLNodeValues.entityHeaderSetTagWiseData.get("StateRevision"), "Revision Value Validation");
			flag= Component.verifyEquals(rsEntity.getString("EntityState"), FetchXMLNodeValues.entityHeaderSetTagWiseData.get("EntityState"), "EntityState record Validation");
			publishResults(flag, rsEntity.getString("EntityState"), FetchXMLNodeValues.entityHeaderSetTagWiseData.get("EntityState"), "EntityState Value Validation");
			flag= Component.verifyEquals(sourcedatetime_db.substring(0, sourcedatetime_db.length()-4),sourcedatetime_xml.substring(0, sourcedatetime_xml.length()-5), "SourceDateTime record Validation");
			publishResults(flag, sourcedatetime_db.substring(0, sourcedatetime_db.length()-4),sourcedatetime_xml.substring(0, sourcedatetime_xml.length()-5), "SourceDateTime Value Validation");
			DBValidations.validateEntityType(rsEntity.getString(1));
			DBValidations.validateEntityId(rsEntity.getString(2));
			DBValidations.validateStateRevision(rsEntity.getString(3));
			DBValidations.validateEntityState(rsEntity.getString(4));
			DBValidations.validateSourceDateTime(rsEntity.getString(5));
		}
		return rsEntity;
	}	
	

	// Description: This method gets the data in the DB for Item Table 
	public static ResultSet getItemHeaderAttributeValues(String fileName) throws Exception
	{
		String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		ProcessId_NV=getTagValueByXPATH(templateFilePath, fileName, "//ProcessId", "ProcessId");
		OperatorId_NV=getTagValueByXPATH(templateFilePath, fileName, "//OperatorId", "OperatorId");
		UpdateDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//UpdateDateTime", "UpdateDateTime");
		AuditRevision_NV=getTagValueByXPATH(templateFilePath, fileName, "//AuditRevision", "AuditRevision");
		Gender_NV=getTagValueByXPATH(templateFilePath, fileName, "//Gender", "Gender");
		SortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//SortCode", "SortCode");
		Account_NV=getTagValueByXPATH(templateFilePath, fileName, "//Account", "Account");
		Amount_NV=getTagValueByXPATH(templateFilePath, fileName, "//Amount", "Amount");
		query ="select ItemId, ProcessId, OperatorId, UpdateDateTime, Revision as AuditRevision, Gender FROM Base.ItemUpdate "
				+ " where ItemId='"+itemIDFromXML[0]+"'";	
		System.out.println(query );
		ResultSet rsItem = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsItem.next())
		{
			flag= Component.verifyEquals(rsItem.getString("ItemId"), itemIDFromXML[0],"ItemId record validation");
			publishResults(flag, rsItem.getString("ItemId"),itemIDFromXML[0], "ItemId value validation");
			flag= Component.verifyEquals(rsItem.getString("AuditRevision"), AuditRevision_NV[0], "AuditRevision record validation");
			publishResults(flag, rsItem.getString("AuditRevision"), AuditRevision_NV[0], "Revision value validation");
			flag= Component.verifyEquals(rsItem.getString("Gender").trim(), Gender_NV[0].trim(), "Gender record validation");
			publishResults(flag, rsItem.getString("Gender"), Gender_NV[0], "Gender value validation");
			flag= Component.verifyEquals(rsItem.getString("ProcessId"), ProcessId_NV[0], "ProcessId record validation");
			publishResults(flag, rsItem.getString("ProcessId"), ProcessId_NV[0], "ProcessId value validation");
			flag= Component.verifyEquals(rsItem.getString("OperatorId"), OperatorId_NV[0], "operatorID record validation");
			publishResults(flag, rsItem.getString("OperatorId"), OperatorId_NV[0], "OperatorId value validation");
			String updatedatetime_db = rsItem.getString("UpdateDateTime").replaceAll("[A-Za-z .]","");
			String updatedatetime_xml =  UpdateDateTime_NV[0].replaceAll("[A-Za-z+ .]","");
			flag= Component.verifyEquals(updatedatetime_db.substring(0, updatedatetime_db.length()-2), updatedatetime_xml.substring(0, updatedatetime_xml.length()-5), "UpdateDateTime record Validation");
			publishResults(flag, updatedatetime_db.substring(0, updatedatetime_db.length()-2), updatedatetime_xml.substring(0, updatedatetime_xml.length()-5), "UpdateDateTime value Validation");
			DBValidations.validateItemId(rsItem.getString(1));
			DBValidations.validateProcessIdDataType(rsItem.getString(2));
			DBValidations.validateOperatorIdDataType(rsItem.getString(3));
			DBValidations.validateUpdateDateTime(rsItem.getString(4));
			DBValidations.validateAuditRevision(rsItem.getString(5));
			DBValidations.validateGender(rsItem.getString(6));
		}
		return rsItem;
	}	
	
	
	// Description: This method gets the data in the DB for Item Table without gender 
		public static ResultSet getItemHeaderWithoutGenderAttributeValues(String fileName) throws Exception
		{
			String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
			ProcessId_NV=getTagValueByXPATH(templateFilePath, fileName, "//ProcessId", "ProcessId");
			OperatorId_NV=getTagValueByXPATH(templateFilePath, fileName, "//OperatorId", "OperatorId");
			UpdateDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//UpdateDateTime", "UpdateDateTime");
			AuditRevision_NV=getTagValueByXPATH(templateFilePath, fileName, "//AuditRevision", "AuditRevision");
			SortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//SortCode", "SortCode");
			Account_NV=getTagValueByXPATH(templateFilePath, fileName, "//Account", "Account");
			Amount_NV=getTagValueByXPATH(templateFilePath, fileName, "//Amount", "Amount");
			query ="select ItemId, ProcessId, OperatorId, UpdateDateTime, Revision as AuditRevision FROM Base.ItemUpdate "
					+ " where ItemId='"+itemIDFromXML[0]+"'";	
			System.out.println(query );
			ResultSet rsItem = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
			while(rsItem.next())
			{
				flag= Component.verifyEquals(rsItem.getString("ItemId"), itemIDFromXML[0],"ItemId record validation");
				publishResults(flag, rsItem.getString("ItemId"),itemIDFromXML[0], "ItemId value validation");
				flag= Component.verifyEquals(rsItem.getString("AuditRevision"), AuditRevision_NV[0], "AuditRevision record validation");
				publishResults(flag, rsItem.getString("AuditRevision"), AuditRevision_NV[0], "Revision value validation");
				flag= Component.verifyEquals(rsItem.getString("ProcessId"), ProcessId_NV[0], "ProcessId record validation");
				publishResults(flag, rsItem.getString("ProcessId"), ProcessId_NV[0], "ProcessId value validation");
				flag= Component.verifyEquals(rsItem.getString("OperatorId"), OperatorId_NV[0], "operatorID record validation");
				publishResults(flag, rsItem.getString("OperatorId"), OperatorId_NV[0], "OperatorId value validation");
				String updatedatetime_db = rsItem.getString("UpdateDateTime").replaceAll("[A-Za-z .]","");
				String updatedatetime_xml =  UpdateDateTime_NV[0].replaceAll("[A-Za-z+ .]","");
				flag= Component.verifyEquals(updatedatetime_db.substring(0, updatedatetime_db.length()-2), updatedatetime_xml.substring(0, updatedatetime_xml.length()-5), "UpdateDateTime record Validation");
				publishResults(flag, updatedatetime_db.substring(0, updatedatetime_db.length()-2), updatedatetime_xml.substring(0, updatedatetime_xml.length()-5), "UpdateDateTime value Validation");
				DBValidations.validateItemId(rsItem.getString(1));
				DBValidations.validateProcessIdDataType(rsItem.getString(2));
				DBValidations.validateOperatorIdDataType(rsItem.getString(3));
				DBValidations.validateUpdateDateTime(rsItem.getString(4));
				DBValidations.validateAuditRevision(rsItem.getString(5));
			}
			return rsItem;
		}	
		
		
	//Description : This method validates data for Item header without process ID
	public static ResultSet getItemHeaderWithoutProcessIDAttributeValues(String fileName) throws Exception
	{
		String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		AuditRevision_NV=getTagValueByXPATH(templateFilePath, fileName, "//AuditRevision", "AuditRevision");
		Gender_NV=getTagValueByXPATH(templateFilePath, fileName, "//Gender", "Gender");
		query ="select ItemId, Revision as AuditRevision, Gender FROM Base.ItemUpdate "
				+ " where ItemId='"+itemIDFromXML[0]+"'";	
		System.out.println(query );
		ResultSet rsItem = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsItem.next())
		{
			flag= Component.verifyEquals(rsItem.getString("ItemId"), itemIDFromXML[0],"ItemId record validation");
			publishResults(flag, rsItem.getString("ItemId"),itemIDFromXML[0], "ItemId value validation");
			flag= Component.verifyEquals(rsItem.getString("AuditRevision"), AuditRevision_NV[0], "AuditRevision record validation");
			publishResults(flag, rsItem.getString("AuditRevision"), AuditRevision_NV[0], "Revision value validation");
			flag= Component.verifyEquals(rsItem.getString("Gender").trim(), Gender_NV[0].trim(), "Gender record validation");
			publishResults(flag, rsItem.getString("Gender"), Gender_NV[0], "Gender value validation");
			DBValidations.validateItemId(rsItem.getString(1));
			DBValidations.validateAuditRevision(rsItem.getString(2));
			DBValidations.validateGender(rsItem.getString(3));
		}
		return rsItem;
	}
	
	
	//Description : This methods validates Pay decision data
	public static ResultSet getPayDecisionAttributeValues(String fileName) throws Exception
	{
		String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		String[] PayDecisionFromXML=getTagValueByXPATH(templateFilePath, fileName, "//PayDecision", "PayDecision");
		String[] PayReasonFromXML=getTagValueByXPATH(templateFilePath, fileName, "//PayDecisionReasonCode", "PayDecisionReasonCode");
		query ="select PayDecision, PayReason from  Base.Debit"
				+ " where PayReason='"+PayReasonFromXML[0]+"'";	
		System.out.println(query );
		ResultSet rsPay = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		String revisedPayDecision="1";
		String updatedPayDecision="0";
		if(PayDecisionFromXML[0].contains("true")){
			PayDecisionFromXML[0]=revisedPayDecision;
		}
		else if(PayDecisionFromXML[0].contains("false")){
			PayDecisionFromXML[0]=updatedPayDecision;
			}
		while(rsPay.next())
		{
			flag= Component.verifyEquals(rsPay.getString("PayDecision"), PayDecisionFromXML[0],"PayDecision record validation");
			publishResults(flag, rsPay.getString("PayDecision"),PayDecisionFromXML[0], "PayDecision value validation");
			flag= Component.verifyEquals(rsPay.getString("PayReason"), PayReasonFromXML[0],"PayReason record validation");
			publishResults(flag, rsPay.getString("PayReason"),PayReasonFromXML[0], "PayReason value validation");
		}
		return rsPay;
	}
	
	
	
	// Description: This method gets the data in the DB for Multiple Item Tables
	public static ResultSet getItemHeaderMultipleAttributeValues(String fileName) throws Exception
	{
		String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		ProcessId_NV=getTagValueByXPATH(templateFilePath, fileName, "//ProcessId", "ProcessId");
		OperatorId_NV=getTagValueByXPATH(templateFilePath, fileName, "//OperatorId", "OperatorId");
		ItemId_NV=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		System.out.println(ItemId_NV[1]);
		query ="select ItemId, ProcessId, OperatorId FROM Base.ItemUpdate "
				+ " where ItemId='"+itemIDFromXML[1]+"'";	
		System.out.println(query );
		ResultSet rsItem = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsItem.next())
		{
			String updatedatetime_db = rsItem.getString("UpdateDateTime").replaceAll("[A-Za-z .]","");
			String updatedatetime_xml =  UpdateDateTime_NV[0].replaceAll("[A-Za-z+ .]","");
			flag= Component.verifyEquals(rsItem.getString("ItemId"), itemIDFromXML[1],"ItemId record validation");
			publishResults(flag, rsItem.getString("ItemId"),itemIDFromXML[1], "ItemId value validation");
			flag= Component.verifyEquals(rsItem.getString("ProcessId"), ProcessId_NV[0], "ProcessId record validation");
			publishResults(flag, rsItem.getString("ProcessId"), ProcessId_NV[0], "ProcessId value validation");
			flag= Component.verifyEquals(rsItem.getString("OperatorId"), OperatorId_NV[0], "operatorID record validation");
			publishResults(flag, rsItem.getString("OperatorId"), OperatorId_NV[0], "OperatorId value validation");
			DBValidations.validateItemId(rsItem.getString(1));
			DBValidations.validateProcessIdDataType(rsItem.getString(2));
			DBValidations.validateOperatorIdDataType(rsItem.getString(3));
			}
		return rsItem;
	}
	
	// Description: This method gets the data in the DB for Item Table with BenPID
	public static ResultSet getItemHeaderWithBenParticipantIDAttributeValues(String fileName) throws Exception
	{
		String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		AuditRevision_NV=getTagValueByXPATH(templateFilePath, fileName, "//AuditRevision", "AuditRevision");
		Gender_NV=getTagValueByXPATH(templateFilePath, fileName, "//Gender", "Gender");
		BeneficiaryParticipantId_NV=getTagValueByXPATH(templateFilePath, fileName, "//BeneficiaryParticipantId", "BeneficiaryParticipantId");
		query ="select ItemId, Revision as AuditRevision, Gender, BeneficiaryParticipantId FROM Base.ItemUpdate "
				+ " where ItemId='"+itemIDFromXML[0]+"'";	
		System.out.println(query );
		ResultSet rsItem = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsItem.next())
		{
			flag= Component.verifyEquals(rsItem.getString("ItemId"), itemIDFromXML[0],"ItemId record validation");
			publishResults(flag, rsItem.getString("ItemId"),itemIDFromXML[0], "ItemId value validation");
			flag= Component.verifyEquals(rsItem.getString("BeneficiaryParticipantId"), BeneficiaryParticipantId_NV[0], "BeneficiaryParticipantId record validation");
			publishResults(flag, rsItem.getString("BeneficiaryParticipantId"), BeneficiaryParticipantId_NV[0], "BeneficiaryParticipantId value validation");
			flag= Component.verifyEquals(rsItem.getString("AuditRevision"), AuditRevision_NV[0], "AuditRevision record validation");
			publishResults(flag, rsItem.getString("AuditRevision"), AuditRevision_NV[0], "Revision value validation");
			flag= Component.verifyEquals(rsItem.getString("Gender").trim(), Gender_NV[0].trim(), "Gender record validation");
			publishResults(flag, rsItem.getString("Gender"), Gender_NV[0], "Gender value validation");
			DBValidations.validateItemId(rsItem.getString(1));
			DBValidations.validateAuditRevision(rsItem.getString(2));
			DBValidations.validateGender(rsItem.getString(3));
			DBValidations.validateBeneficiaryParticipantId(rsItem.getString(4));
		}
		return rsItem;
	}
	
	// Description: This method gets the data in the DB for Job Header Table 
	public static ResultSet getJobHeaderAttributeValues(String fileName) throws Exception
	{
		BusinessDate_NV=getTagValueByXPATH(templateFilePath, fileName, "//BusinessDate", "BusinessDate");
		InstallationId_NV=getTagValueByXPATH(templateFilePath, fileName, "//InstallationId", "InstallationId");
		CaptureSystemId_NV=getTagValueByXPATH(templateFilePath, fileName, "//CaptureSystemId", "CaptureSystemId");
		APGStartTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//StartTime", "StartTime");
		APGEndTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//EndTime", "EndTime");
		WorkTypeNbr_NV=getTagValueByXPATH(templateFilePath, fileName, "//WorkTypeNbr", "WorkTypeNbr");
		SortFamily_NV=getTagValueByXPATH(templateFilePath, fileName, "//SortFamily", "SortFamily");
		SourceType_NV=getTagValueByXPATH(templateFilePath, fileName, "//SourceType", "SourceType");
		SourceID_NV=getTagValueByXPATH(templateFilePath, fileName, "//SourceID", "SourceID");
		SourceName_NV=getTagValueByXPATH(templateFilePath, fileName, "//SourceName", "SourceName");
		FinancialInstitutionId_NV=getTagValueByXPATH(templateFilePath, fileName, "//FinancialInstitutionID", "FinancialInstitutionID");
		CollectionStartTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//CollectionStartTime", "CollectionStartTime");
		CollectionEndTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//CollectionEndTime", "CollectionEndTime");
		query ="select BusinessDate, InstallationId, CaptureSystemId, SortFamily, SourceType, FinancialInstitutionID,SourceID,CollectionStartTime,CollectionEndTime,SourceName,APGStartTime, WorkTypeNbr, APGEndTime FROM Base.Job "
				+ " where InstallationId='"+InstallationId_NV[0]+"'";	
		System.out.println(query );
		ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsJob.next())
		{
			String APGStartTime_db = rsJob.getString("APGStartTime").replaceAll("[A-Za-z .]","");
			String APGStartTime_xml =  APGStartTime_NV[0].replaceAll("[A-Za-z+ .]","");
			String APGEndTime_db = rsJob.getString("APGEndTime").replaceAll("[A-Za-z .]","");
			String APGEndTime_xml = APGEndTime_NV[0].replaceAll("[A-Za-z+ .]","");
			String CollectionStartTime_db = rsJob.getString("CollectionStartTime").replaceAll("[A-Za-z .]","");
			String CollectionStartTime_xml =  CollectionStartTime_NV[0].replaceAll("[A-Za-z+ .]","");
			String CollectionEndTime_db = rsJob.getString("CollectionEndTime").replaceAll("[A-Za-z .]","");
			String CollectionEndTime_xml =  CollectionEndTime_NV[0].replaceAll("[A-Za-z+ .]","");
			String businessdate_db = rsJob.getString("BusinessDate").replaceAll("[A-Za-z+-.]","");
			String businessdate_xml =  FetchXMLNodeValues.coreHeaderSetTagWiseData.get("BusinessDate").replaceAll("[A-Za-z+ -.]","");
			flag= Component.verifyEquals(businessdate_db.substring(0, 8), businessdate_xml.substring(0, 8), "BusinessDate Validation");
			publishResults(flag,businessdate_db.substring(0, 8), businessdate_xml.substring(0, 8), "Business Date Value Validation ");
			flag= Component.verifyEquals(rsJob.getString("InstallationId"), InstallationId_NV[0], "ProcessId record validation");
			publishResults(flag, rsJob.getString("InstallationId"), InstallationId_NV[0], "InstallationId value validation");
			flag= Component.verifyEquals(rsJob.getString("CaptureSystemId"), CaptureSystemId_NV[0], "CaptureSystemId record validation");
			publishResults(flag, rsJob.getString("CaptureSystemId"), CaptureSystemId_NV[0], "CaptureSystemId value validation");
			flag= Component.verifyEquals(APGStartTime_db, APGStartTime_xml.substring(0, APGStartTime_xml.length()-3), "APGStartTime record validation");
			publishResults(flag, APGStartTime_db, APGStartTime_xml.substring(0, APGStartTime_xml.length()-3), "APGStartTime value validation");
			flag= Component.verifyEquals(APGEndTime_db, APGEndTime_xml.substring(0, APGEndTime_xml.length()-3), "APGEndTime record validation");
			publishResults(flag, APGEndTime_db, APGEndTime_xml.substring(0, APGEndTime_xml.length()-3), "APGEndTime value validation");
			flag= Component.verifyEquals(rsJob.getString("WorkTypeNbr"), WorkTypeNbr_NV[0],"WorkTypeNbr record validation");
			publishResults(flag, rsJob.getString("WorkTypeNbr"),WorkTypeNbr_NV[0], "WorkTypeNbr value validation");
			flag= Component.verifyEquals(rsJob.getString("SortFamily"), SortFamily_NV[0], "SortFamily record validation");
			publishResults(flag, rsJob.getString("SortFamily"), SortFamily_NV[0], "SortFamily value validation");
			flag= Component.verifyEquals(rsJob.getString("SourceType"), SourceType_NV[0], "SourceType record validation");
			publishResults(flag, rsJob.getString("SourceType"), SourceType_NV[0], "SourceType value validation");
			flag= Component.verifyEquals(rsJob.getString("SourceName"), SourceName_NV[0], "SourceName record validation");
			publishResults(flag, rsJob.getString("SourceName"), SourceName_NV[0], "SourceName value validation");
			flag= Component.verifyEquals(rsJob.getString("SourceID"), SourceID_NV[0], "SourceID record validation");
			publishResults(flag, rsJob.getString("SourceID"), SourceID_NV[0], "SourceID value validation");
			flag= Component.verifyEquals(rsJob.getString("FinancialInstitutionId"), FinancialInstitutionId_NV[0],"FinancialInstitutionId record validation");
			publishResults(flag, rsJob.getString("FinancialInstitutionId"),FinancialInstitutionId_NV[0], "FinancialInstitutionId value validation");
			flag= Component.verifyEquals(CollectionStartTime_db, CollectionStartTime_xml.substring(0, CollectionStartTime_xml.length()-3), "CollectionStartTime record validation");
			publishResults(flag, CollectionStartTime_db, CollectionStartTime_xml.substring(0, CollectionStartTime_xml.length()-3), "CollectionStartTime value validation");
			flag= Component.verifyEquals(CollectionEndTime_db, CollectionEndTime_xml.substring(0, CollectionEndTime_xml.length()-3), "CollectionEndTime record validation");
			publishResults(flag, CollectionEndTime_db, CollectionEndTime_xml.substring(0, CollectionEndTime_xml.length()-3), "CollectionEndTime value validation");
			DBValidations.validateBusinessDate(rsJob.getString(1));
			DBValidations.validateInstallationId(rsJob.getString(2));
			DBValidations.validateCaptureSystemId(rsJob.getString(3));
			DBValidations.validateSortFamily(rsJob.getString(4));
			DBValidations.validateSourceType(rsJob.getString(5));
			DBValidations.validateFinancialInstitutionID(rsJob.getString(6));
			DBValidations.validateSourceID(rsJob.getString(7));
			DBValidations.validateCollectionStartTime(rsJob.getString(8));
			DBValidations.validateCollectionEndTime(rsJob.getString(9));
			DBValidations.validateSourceName(rsJob.getString(10));
			DBValidations.validateAPGStartTime(rsJob.getString(11));
			DBValidations.validateWorkTypeNbr(rsJob.getString(12));
			DBValidations.validateAPGEndTime(rsJob.getString(13));
		}
		return rsJob;
	}	
	
	// Description: This method gets the data in the DB for Batch Header Table 
	public static ResultSet getBatchHeaderAttributeValues(String fileName) throws Exception
	{
		BlkNbr_NV=getTagValueByXPATH(templateFilePath, fileName, "//BlkNbr", "BlkNbr");
		ImageType_NV=getTagValueByXPATH(templateFilePath, fileName, "//ImageType", "ImageType");
		BatchCreditCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//BatchCreditCount", "BatchCreditCount");
		BatchDebitCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//BatchDebitCount", "BatchDebitCount");
		BatchCreditAmount_NV=getTagValueByXPATH(templateFilePath, fileName, "//BatchCreditAmount", "BatchCreditAmount");
		BatchDebitAmount_NV=getTagValueByXPATH(templateFilePath, fileName, "//BatchDebitAmount", "BatchDebitAmount");
		query ="select BlockNumber, ImageType, CreditCount, DebitCount, CreditAmount, DebitAmount from Base.Batch "
				+ " where BlockNumber='"+BlkNbr_NV[0]+"'";	
		System.out.println(query );
		ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsJob.next())
		{
			flag= Component.verifyEquals(rsJob.getString("BlockNumber"), BlkNbr_NV[0],"BlockNumber record Validation");
			publishResults(flag, rsJob.getString("BlockNumber"),BlkNbr_NV[0], "BlockNumber value Validation");
			flag= Component.verifyEquals(rsJob.getString("ImageType"), ImageType_NV[0], "ImageType record Validation");
			publishResults(flag, rsJob.getString("ImageType"), ImageType_NV[0], "ImageType value Validation");
			flag= Component.verifyEquals(rsJob.getString("CreditCount"), BatchCreditCount_NV[0], "CreditCount record Validation");
			publishResults(flag, rsJob.getString("CreditCount"), BatchCreditCount_NV[0], "CreditCount value Validation");
			flag= Component.verifyEquals(rsJob.getString("DebitCount"), BatchDebitCount_NV[0], "DebitCount record Validation");
			publishResults(flag, rsJob.getString("DebitCount"), BatchDebitCount_NV[0], "DebitCount value Validation");
			flag= Component.verifyEquals(rsJob.getString("CreditAmount").substring(0, 8), BatchCreditAmount_NV[0].substring(0, 8), "CreditAmount record Validation");
			publishResults(flag, rsJob.getString("CreditAmount").substring(0, 8), BatchCreditAmount_NV[0].substring(0, 8), "CreditAmount value Validation");
			flag= Component.verifyEquals(rsJob.getString("DebitAmount").substring(0, 8), BatchDebitAmount_NV[0].substring(0, 8),"DebitAmount record Validation");
			publishResults(flag, rsJob.getString("DebitAmount").substring(0, 8),BatchDebitAmount_NV[0].substring(0, 8), "DebitAmount value Validation");
			DBValidations.validateBlockNumber(rsJob.getString(1));
			DBValidations.validateImageType(rsJob.getString(2));
			DBValidations.validateCreditCount(rsJob.getString(3));
			DBValidations.validateDebitCount(rsJob.getString(4));
			DBValidations.validateCreditAmount(rsJob.getString(5));
			DBValidations.validateDebitAmount(rsJob.getString(6));
		}
		return rsJob;
	}
	
	// Description: This method gets the data in the DB for Capture Item Header Table 
	public static ResultSet getCaptureItemHeaderAttributeValues(String fileName) throws Exception
	{
		APGDIN_NV=getTagValueByXPATH(templateFilePath, fileName, "//APGDIN", "APGDIN");
		APGBusinessDate_NV=getTagValueByXPATH(templateFilePath, fileName, "//APGBusinessDate", "APGBusinessDate");
		TransactionNumber_NV=getTagValueByXPATH(templateFilePath, fileName, "//TransactionNumber", "TransactionNumber");
		IsElectronic_NV=getTagValueByXPATH(templateFilePath, fileName, "//IsElectronic", "IsElectronic");
		IsOnUs_NV=getTagValueByXPATH(templateFilePath, fileName, "//IsOnUs", "IsOnUs");
		IsDeleted_NV=getTagValueByXPATH(templateFilePath, fileName, "//IsDeleted", "IsDeleted");
		IsCorrected_NV=getTagValueByXPATH(templateFilePath, fileName, "//IsCorrected", "IsCorrected");
		IsAmountCorrected_NV=getTagValueByXPATH(templateFilePath, fileName, "//IsAmountCorrected", "IsAmountCorrected");
		OriginalAmount_NV=getTagValueByXPATH(templateFilePath, fileName, "//OriginalAmount", "OriginalAmount");
		IsTCCorrected_NV=getTagValueByXPATH(templateFilePath, fileName, "//IsTCCorrected", "IsTCCorrected");
		IsANCorrected_NV=getTagValueByXPATH(templateFilePath, fileName, "//IsANCorrected", "IsANCorrected");
		IsSortCodeCorrected_NV=getTagValueByXPATH(templateFilePath, fileName, "//IsSortCodeCorrected", "IsSortCodeCorrected");
		IsSerialCorrected_NV=getTagValueByXPATH(templateFilePath, fileName, "//IsSerialCorrected", "IsSerialCorrected");
		IsReject_NV=getTagValueByXPATH(templateFilePath, fileName, "//IsReject", "IsReject");
		RejectReason_NV=getTagValueByXPATH(templateFilePath, fileName, "//RejectReason", "RejectReason");
		SpSelector_NV=getTagValueByXPATH(templateFilePath, fileName, "//SpSelector", "SpSelector");
		Currency_NV=getTagValueByXPATH(templateFilePath, fileName, "//Currency", "Currency");
		AdjustmentReason_NV=getTagValueByXPATH(templateFilePath, fileName, "//AdjustmentReason", "AdjustmentReason");
		Narrative_NV=getTagValueByXPATH(templateFilePath, fileName, "//Narrative", "Narrative");
		OriginalIsn_NV=getTagValueByXPATH(templateFilePath, fileName, "//OriginalIsn", "OriginalIsn");
		AeStatus_NV=getTagValueByXPATH(templateFilePath, fileName, "//AeStatus", "AeStatus");
		IcStatus_NV=getTagValueByXPATH(templateFilePath, fileName, "//IcStatus", "IcStatus");
		IqvStatus_NV=getTagValueByXPATH(templateFilePath, fileName, "//IqvStatus", "IqvStatus");
		CarSetId_NV=getTagValueByXPATH(templateFilePath, fileName, "//CarSetId", "CarSetId");
		CarResult_NV=getTagValueByXPATH(templateFilePath, fileName, "//CarResult", "CarResult");
		IaStatus_NV=getTagValueByXPATH(templateFilePath, fileName, "//IaStatus", "IaStatus");
		IaResult_NV=getTagValueByXPATH(templateFilePath, fileName, "//IaResult", "IaResult");
		PNVReviewStatus_NV=getTagValueByXPATH(templateFilePath, fileName, "//PNVReviewStatus", "PNVReviewStatus");
		DuplicateStatus_NV=getTagValueByXPATH(templateFilePath, fileName, "//DuplicateStatus", "DuplicateStatus");
		ReturnReason_NV=getTagValueByXPATH(templateFilePath, fileName, "//ReturnReason", "ReturnReason");
		ChequeIssuedDate_NV=getTagValueByXPATH(templateFilePath, fileName, "//ChequeIssuedDate", "ChequeIssuedDate");
		
		query ="SELECT [APGDIN],[APGBusinessDate],[Gender],[TransactionNumber],[IsElectronic] ,[IsOnUs],[IsDeleted],[IsCorrected],[IsAmountCorrected],[OriginalAmount],[IsTCCorrected] ,[IsANCorrected] ,[IsSortCodeCorrected]  ,[IsSerialCorrected] ,[IsReject] ,[RejectReason],[SpSelector],[Currency],[AdjustmentReason],[OriginalISN] ,[AeStatus],[IcStatus] ,[IqvStatus] ,[CarSetId] ,[CarResults] ,[IaStatus] ,[IaResult],[PNVReviewStatus]  ,[DuplicateStatus] ,[ReturnReason],[ChequeIssuedDate] from Base.Item "
				+ " where TransactionNumber='"+TransactionNumber_NV[0]+"'";	
		System.out.println(query );
		ResultSet rsCaptureItem = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsCaptureItem.next())
		{
			String apgbusinessdate_db = rsCaptureItem.getString("APGBusinessDate").replaceAll("[A-Za-z+-. ]","");
			String apgbusinessdate_xml =  APGBusinessDate_NV[0].replaceAll("[A-Za-z+-. ]","");
			String chequeIssueDate_db= rsCaptureItem.getString("ChequeIssuedDate").replaceAll("[A-Za-z +]","");
			String chequeIssueDate_xml = ChequeIssuedDate_NV[0].replaceAll("[A-Za-z +]","");
			flag= Component.verifyEquals(rsCaptureItem.getString("APGDIN"), APGDIN_NV[0],"APGDIN record Validation");
			publishResults(flag, rsCaptureItem.getString("APGDIN"),APGDIN_NV[0], "APGDIN value Validation");
			flag= Component.verifyEquals(apgbusinessdate_db.substring(0, 8), apgbusinessdate_xml.substring(0, 8), "APGBusinessDate Validation");
			publishResults(flag,apgbusinessdate_db.substring(0, 8), apgbusinessdate_xml.substring(0, 8), "APGBusinessDate Date Value Validation ");
			flag= Component.verifyEquals(rsCaptureItem.getString("TransactionNumber"), TransactionNumber_NV[0], "TransactionNumber record Validation");
			publishResults(flag, rsCaptureItem.getString("TransactionNumber"), TransactionNumber_NV[0], "TransactionNumber value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("AeStatus"), AeStatus_NV[0], "AeStatus record Validation");
			publishResults(flag, rsCaptureItem.getString("AeStatus"), AeStatus_NV[0], "AeStatus value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IaResult"), IaResult_NV[0], "IaResult record Validation");
			publishResults(flag, rsCaptureItem.getString("IaResult"), IaResult_NV[0], "IaResult value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("ReturnReason"), ReturnReason_NV[0],"ReturnReason record Validation");
			publishResults(flag, rsCaptureItem.getString("ReturnReason"),ReturnReason_NV[0], "ReturnReason value Validation");
			flag= Component.verifyEquals(chequeIssueDate_db.substring(0, chequeIssueDate_db.length()-11),chequeIssueDate_xml.substring(0, chequeIssueDate_xml.length()-13), "Cheque Issue Date record Validation");
			publishResults(flag, chequeIssueDate_db.substring(0, chequeIssueDate_db.length()-11),chequeIssueDate_xml.substring(0, chequeIssueDate_xml.length()-13), "Cheque Issue Date Value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("DuplicateStatus"),DuplicateStatus_NV[0], "DuplicateStatus record Validation");
			publishResults(flag, rsCaptureItem.getString("DuplicateStatus"), DuplicateStatus_NV[0], "DuplicateStatus value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("PNVReviewStatus"), PNVReviewStatus_NV[0], "PNVReviewStatus record Validation");
			publishResults(flag, rsCaptureItem.getString("PNVReviewStatus"), PNVReviewStatus_NV[0], "PNVReviewStatus value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IaStatus"),IaStatus_NV[0], "IaStatus record Validation");
			publishResults(flag, rsCaptureItem.getString("IaStatus"), IaStatus_NV[0], "IaStatus value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("CarResults"), CarResult_NV[0], "CarResults record Validation");
			publishResults(flag, rsCaptureItem.getString("CarResults"), CarResult_NV[0], "CarResults value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("CarSetId"), CarSetId_NV[0],"CarSetId record Validation");
			publishResults(flag, rsCaptureItem.getString("CarSetId"),CarSetId_NV[0], "CarSetId value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IqvStatus"), IqvStatus_NV[0],"IqvStatus record Validation");
			publishResults(flag, rsCaptureItem.getString("IqvStatus"),IqvStatus_NV[0], "IqvStatus value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IcStatus"), IcStatus_NV[0], "IcStatus record Validation");
			publishResults(flag, rsCaptureItem.getString("IcStatus"), IcStatus_NV[0], "IcStatus value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("OriginalIsn"), OriginalIsn_NV[0], "OriginalIsn record Validation");
			publishResults(flag, rsCaptureItem.getString("OriginalIsn"), OriginalIsn_NV[0], "OriginalIsn value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("AdjustmentReason"), AdjustmentReason_NV[0], "AdjustmentReason record Validation");
			publishResults(flag, rsCaptureItem.getString("AdjustmentReason"), AdjustmentReason_NV[0], "AdjustmentReason value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("SpSelector"), SpSelector_NV[0],"SpSelector record Validation");
			publishResults(flag, rsCaptureItem.getString("SpSelector"),SpSelector_NV[0], "SpSelector value Validation");
			/*flag= Component.verifyEquals(rsCaptureItem.getString("Currency"), Currency_NV[0], "Currency record Validation");
			publishResults(flag, rsCaptureItem.getString("Currency"),Currency_NV[0], "Currency value Validation");
			*/flag= Component.verifyEquals(rsCaptureItem.getString("RejectReason"), RejectReason_NV[0], "RejectReason record Validation");
			publishResults(flag, rsCaptureItem.getString("RejectReason"), RejectReason_NV[0], "RejectReason value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IsReject"), IsReject_NV[0], "IsReject record Validation");
			publishResults(flag, rsCaptureItem.getString("IsReject"), IsReject_NV[0], "IsReject value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IsSerialCorrected"), IsSerialCorrected_NV[0], "IsSerialCorrected record Validation");
			publishResults(flag, rsCaptureItem.getString("IsSerialCorrected"), IsSerialCorrected_NV[0], "IsSerialCorrected value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IsANCorrected"),IsANCorrected_NV[0], "IsANCorrected record Validation");
			publishResults(flag, rsCaptureItem.getString("IsANCorrected"),IsANCorrected_NV[0], "IsANCorrected value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IsTCCorrected"), IsTCCorrected_NV[0], "IsTCCorrected record Validation");
			publishResults(flag, rsCaptureItem.getString("IsTCCorrected"), IsTCCorrected_NV[0], "IsTCCorrected value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("OriginalAmount"), OriginalAmount_NV[0], "OriginalAmount record Validation");
			publishResults(flag, rsCaptureItem.getString("OriginalAmount"), OriginalAmount_NV[0], "OriginalAmount value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IsElectronic"), IsElectronic_NV[0],"IsElectronic record Validation");
			publishResults(flag, rsCaptureItem.getString("IsElectronic"),IsElectronic_NV[0], "IsElectronic value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IsOnUs"), IsOnUs_NV[0], "IsOnUs record Validation");
			publishResults(flag, rsCaptureItem.getString("IsOnUs"),IsOnUs_NV[0], "IsOnUs value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IsDeleted"), IsDeleted_NV[0], "IsDeleted record Validation");
			publishResults(flag, rsCaptureItem.getString("IsDeleted"),IsDeleted_NV[0], "IsDeleted value Validation");
			flag= Component.verifyEquals(rsCaptureItem.getString("IsAmountCorrected"), IsAmountCorrected_NV[0], "IsAmountCorrected record Validation");
			publishResults(flag, rsCaptureItem.getString("IsAmountCorrected"), IsAmountCorrected_NV[0], "IsAmountCorrected value Validation");
			DBValidations.validateAPGDIN(rsCaptureItem.getString(1));
			DBValidations.validateAPGBusinessDate(rsCaptureItem.getString(2));
			DBValidations.validateGender(rsCaptureItem.getString(3));
			DBValidations.validateTransactionNumber(rsCaptureItem.getString(4));
			DBValidations.validateRejectReason(rsCaptureItem.getString(16));
			DBValidations.validateSpSelector(rsCaptureItem.getString(17));
			DBValidations.validateCurrency(rsCaptureItem.getString(18));
			DBValidations.validateAdjustmentReason(rsCaptureItem.getString(19));
			DBValidations.validateOriginalIsn(rsCaptureItem.getString(20));
			DBValidations.validateAeStatus(rsCaptureItem.getString(21));
			DBValidations.validateIcStatus(rsCaptureItem.getString(22));
			DBValidations.validateIqvStatus(rsCaptureItem.getString(23));
			DBValidations.validateCarSetId(rsCaptureItem.getString(24));
			DBValidations.validateCarResults(rsCaptureItem.getString(25));
			DBValidations.validateIaStatus(rsCaptureItem.getString(26));
			DBValidations.validateIaResult(rsCaptureItem.getString(27));
			DBValidations.validatePNVReviewStatus(rsCaptureItem.getString(28));
			DBValidations.validateDuplicateStatus(rsCaptureItem.getString(29));
			DBValidations.validateReturnReason(rsCaptureItem.getString(30));
			DBValidations.validateChequeIssuedDate(rsCaptureItem.getString(31));
		}
		return rsCaptureItem;
	}
	
	// Description: This method gets the data in the DB for Code Line data 
	public static ResultSet getCodeLineAttributeValues(String fileName) throws Exception
	{
		SortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//SortCode", "SortCode");
		Account_NV=getTagValueByXPATH(templateFilePath, fileName, "//Account", "Account");
		Amount_NV=getTagValueByXPATH(templateFilePath, fileName, "//Amount", "Amount");
		Reference_NV=getTagValueByXPATH(templateFilePath, fileName, "//Reference", "Reference");
		TranCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//TranCode", "TranCode");
		query ="select Sortcode, AccountNumber, Amount, Reference, TranCode FROM Base.Credit "
				+ " where TranCode='"+TranCode_NV[0]+"'";	
		System.out.println(query);
		ResultSet rsCodeline = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsCodeline.next())
		{
			flag= Component.verifyEquals(rsCodeline.getString("Sortcode"), SortCode_NV[0], "SortCode record validation");
			publishResults(flag, rsCodeline.getString("Sortcode"), SortCode_NV[0], "SortCode value validation");
			flag= Component.verifyEquals(rsCodeline.getString("AccountNumber"), Account_NV[0], "Account record validation");
			publishResults(flag, rsCodeline.getString("AccountNumber"), Account_NV[0], "Account value validation");
			flag= Component.verifyEquals(rsCodeline.getString("Amount"), Amount_NV[0], "Amount record validation");
			publishResults(flag, rsCodeline.getString("Amount"), Amount_NV[0], "Amount value validation");
			flag= Component.verifyEquals(rsCodeline.getString("Reference"), Reference_NV[0], "Reference record validation");
			publishResults(flag, rsCodeline.getString("Reference"), Reference_NV[0], "Reference value validation");
			flag= Component.verifyEquals(rsCodeline.getString("TranCode"), TranCode_NV[0], "TranCode record validation");
			publishResults(flag, rsCodeline.getString("TranCode"), TranCode_NV[0], "TranCode value validation");
			DBValidations.validateOriginalSortCode(rsCodeline.getString(1));
			DBValidations.validateAccount(rsCodeline.getString(2));
			DBValidations.validateAmount(rsCodeline.getString(3));
			DBValidations.validateAccount(AccountingSystemTagName);
			DBValidations.validateTransactionNumber(rsCodeline.getString(5));
			
		}
		return rsCodeline;
	}
	
	
	
	// Description: This method gets the data in the DB for Code Line & Switched code line data 
		public static ResultSet getCodeLineAndSwitchedCodeLineAttributeValues(String fileName) throws Exception
		{
			SortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//SortCode", "SortCode");
			Account_NV=getTagValueByXPATH(templateFilePath, fileName, "//Account", "Account");
			SwitchedSortCode_NV=getTagValueByXPATH(templateFilePath, fileName, "//SwitchedSortCode", "SwitchedSortCode");
			SwitchedAccount_NV=getTagValueByXPATH(templateFilePath, fileName, "//SwitchedAccount", "SwitchedAccount");
			query ="select Sortcode, AccountNumber, SwitchedSortCode, SwitchedAccount FROM Base.Debit "
					+ " where SwitchedAccount='"+SwitchedAccount_NV[0]+"'";	
			System.out.println(query);
			ResultSet rsCodeSwline = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
			while(rsCodeSwline.next())
			{
				flag= Component.verifyEquals(rsCodeSwline.getString("Sortcode"), SortCode_NV[0], "SortCode record validation");
				publishResults(flag, rsCodeSwline.getString("Sortcode"), SortCode_NV[0], "SortCode value validation");
				flag= Component.verifyEquals(rsCodeSwline.getString("AccountNumber"), Account_NV[0], "Account record validation");
				publishResults(flag, rsCodeSwline.getString("AccountNumber"), Account_NV[0], "Account value validation");
				flag= Component.verifyEquals(rsCodeSwline.getString("SwitchedSortCode"), SwitchedSortCode_NV[0], "SwitchedSortCode record validation");
				publishResults(flag, rsCodeSwline.getString("SwitchedSortCode"), SwitchedSortCode_NV[0], "SwitchedSortCode value validation");
				flag= Component.verifyEquals(rsCodeSwline.getString("SwitchedAccount"), SwitchedAccount_NV[0], "SwitchedAccount record validation");
				publishResults(flag, rsCodeSwline.getString("SwitchedAccount"), SwitchedAccount_NV[0], "SwitchedAccount value validation");
				DBValidations.validateOriginalSortCode(rsCodeSwline.getString(1));
				DBValidations.validateAccount(rsCodeSwline.getString(2));
			}
			return rsCodeSwline;
		}
		
	// Description: This method gets the data in the DB for Fraud Status Table 
	public static ResultSet getFraudStatusAttributeValues(String fileName) throws Exception
	{	
		String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		TransactionSetId_NV=getTagValueByXPATH(templateFilePath,fileName, "//TransactionSetId", "TransactionSetId");
		CreationDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//CreationDateTime", "CreationDateTime");
		query ="select fsr.ItemId, fsr.TransactionSetId,fsr.FraudReason, fsr.FraudResult,fs.CreationDate,fs.ResponseType, fs.NumberOfEntries from Base.FraudStatusResults fsr join"
		  + " Base.FraudStatus fs  on fs.FraudStatusId = fsr.FraudStatusId where ItemId='"+itemIDFromXML[0]+"'";	
		System.out.println(query);
		ResultSet rsFraudStatus = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		FraudCheckReason_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudCheckReason", "FraudCheckReason");
		FraudCheckResult_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudCheckResult", "FraudCheckResult");
		FraudResponseType_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudResponseType", "FraudResponseType");
		NumberofEntries_NV=getTagValueByXPATH(templateFilePath, fileName, "//NumberofEntries", "NumberofEntries");
		while(rsFraudStatus.next())
		{
		String creationdate_db = rsFraudStatus.getString("CreationDate").replaceAll("[A-Za-z.]","");
		String creationdate_xml =CreationDateTime_NV[0].replaceAll("[A-Za-z.]"," ");
		flag= Component.verifyEquals(rsFraudStatus.getString("ItemId"), itemIDFromXML[0], "ItemId record validation");
		publishResults(flag, rsFraudStatus.getString("ItemId"), itemIDFromXML[0], "ItemId value validation");
		flag= Component.verifyEquals(rsFraudStatus.getString("TransactionSetId"), TransactionSetId_NV[0], "TransactionSetId record validation");
		publishResults(flag, rsFraudStatus.getString("TransactionSetId"), TransactionSetId_NV[0], "TransactionSetId validation");
		flag= Component.verifyEquals(rsFraudStatus.getString("FraudReason"), FraudCheckReason_NV[0], "FraudReason record Validation");
		publishResults(flag, rsFraudStatus.getString("FraudReason"), FraudCheckReason_NV[0], "FraudReason validation");
		flag= Component.verifyEquals(rsFraudStatus.getString("FraudResult"), FraudCheckResult_NV[0], "FraudResult record validation");
		publishResults(flag, rsFraudStatus.getString("FraudResult"), FraudCheckResult_NV[0], "FraudResult validation");
		flag= Component.verifyEquals(creationdate_db.substring(0, creationdate_db.length()-2), creationdate_xml.substring(0, creationdate_xml.length()-6), "CreationDate record Validation");
		publishResults(flag, creationdate_db.substring(0, creationdate_db.length()-2),  creationdate_xml.substring(0, creationdate_xml.length()-6), " CreationDate Validation");
		flag= Component.verifyEquals(rsFraudStatus.getString("ResponseType"), FraudResponseType_NV[0], "ResponseType record validation");
		publishResults(flag, rsFraudStatus.getString("ResponseType"),FraudResponseType_NV[0], "ResponseType validation");
		flag= Component.verifyEquals(rsFraudStatus.getString("NumberOfEntries"), NumberofEntries_NV[0], "NumberOfEntries record validation");
		publishResults(flag, rsFraudStatus.getString("NumberOfEntries"),NumberofEntries_NV[0], "NumberOfEntries validation");
		DBValidations.validateResponseType(rsFraudStatus.getString(6));
		DBValidations.validateItemId(rsFraudStatus.getString(1));
		DBValidations.validateFraudCheckResult(rsFraudStatus.getString(4));
		DBValidations.validateFraudCheckReaosn(rsFraudStatus.getString(3));
		DBValidations.validateTransactionSetId(rsFraudStatus.getString(2));
		DBValidations.validateCreatedDate(rsFraudStatus.getString(5));
		DBValidations.validateNumberOfEntries(rsFraudStatus.getString(7));
		}
		return rsFraudStatus;
	}	
	
	// Description: This method gets the data in the DB for Fraud Results Table 
	public static ResultSet getFraudResultsAttributeValues(String fileName) throws Exception
	{
		String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		query ="select fsr.ItemId, fsr.TransactionSetId,fsr.FraudReason, fsr.FraudResult,fs.CreationDate,fs.ResponseType, fs.NumberOfEntries from Base.FraudStatusResults fsr join"
				  + " Base.FraudStatus fs  on fs.FraudStatusId = fsr.FraudStatusId where ItemId='"+itemIDFromXML[1]+"'";		
		System.out.println(query);
		FraudCheckReason_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudCheckReason", "FraudCheckReason");
		FraudCheckResult_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudCheckResult", "FraudCheckResult");
		FraudResponseType_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudResponseType", "FraudResponseType");
		TransactionSetId_NV=getTagValueByXPATH(templateFilePath, fileName, "//TransactionSetId", "TransactionSetId");
		CreationDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//CreationDateTime", "CreationDateTime");
		NumberofEntries_NV=getTagValueByXPATH(templateFilePath, fileName, "//NumberofEntries", "NumberofEntries");
		ResultSet rsFraudResults = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsFraudResults.next())
		{
			String creationdate_db = rsFraudResults.getString("CreationDate").replaceAll("[A-Za-z.]","");
			String creationdate_xml =CreationDateTime_NV[0].replaceAll("[A-Za-z.]"," ");
		flag= Component.verifyEquals(rsFraudResults.getString("ItemId"), itemIDFromXML[1], "ItemId record validation");
		publishResults(flag, rsFraudResults.getString("ItemId"), itemIDFromXML[1], "ItemId value validation");
		flag= Component.verifyEquals(rsFraudResults.getString("TransactionSetId"), TransactionSetId_NV[0], "TransactionSetId record validation");
		publishResults(flag, rsFraudResults.getString("TransactionSetId"), TransactionSetId_NV[0], "TransactionSetId validation");
		flag= Component.verifyEquals(rsFraudResults.getString("FraudReason"), FraudCheckReason_NV[1], "FraudReason record validation");
		publishResults(flag, rsFraudResults.getString("FraudReason"), FraudCheckReason_NV[1], "FraudReason validation");
		flag= Component.verifyEquals(rsFraudResults.getString("FraudResult"), FraudCheckResult_NV[1], "FraudResult record validation");
		publishResults(flag, rsFraudResults.getString("FraudResult"), FraudCheckResult_NV[1], "FraudResult validation");
		flag= Component.verifyEquals(creationdate_db.substring(0, creationdate_db.length()-2), creationdate_xml.substring(0, creationdate_xml.length()-6), "CreationDate record Validation");
		publishResults(flag, creationdate_db.substring(0, creationdate_db.length()-2),  creationdate_xml.substring(0, creationdate_xml.length()-6), " CreationDate Validation");
		flag= Component.verifyEquals(rsFraudResults.getString("ResponseType"), FraudResponseType_NV[0], "ResponseType record validation");
		publishResults(flag, rsFraudResults.getString("ResponseType"), FraudResponseType_NV[0], "ResponseType validation");
		flag= Component.verifyEquals(rsFraudResults.getString("NumberOfEntries"), NumberofEntries_NV[0], "NumberOfEntries record validation");
		publishResults(flag, rsFraudResults.getString("NumberOfEntries"), NumberofEntries_NV[0], "NumberOfEntries validation");
		DBValidations.validateResponseType(rsFraudResults.getString(6));
		DBValidations.validateItemId(rsFraudResults.getString(1));
		DBValidations.validateFraudCheckResult(rsFraudResults.getString(4));
		DBValidations.validateFraudCheckReaosn(rsFraudResults.getString(3));
		DBValidations.validateTransactionSetId(rsFraudResults.getString(2));
		DBValidations.validateCreatedDate(rsFraudResults.getString(5));
		DBValidations.validateNumberOfEntries(rsFraudResults.getString(7));
		}
		return rsFraudResults;
	}	
	
	
	// Description: This method gets the data in the DB for Fraud Results Table // 16MA01
		public static ResultSet getFraudAttributeValues(String fileName) throws Exception
		{
			String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
			TransactionSetId_NV=getTagValueByXPATH(templateFilePath,fileName, "//TransactionSetId", "TransactionSetId");
			CreationDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//CreationDateTime", "CreationDateTime");

			query ="select fsr.ItemId, fsr.TransactionSetId,fsr.FraudReason, fsr.FraudResult,fs.CreationDate, fs.NumberOfEntries from Base.FraudStatusResults fsr join"
			  + " Base.FraudStatus fs  on fs.FraudStatusId = fsr.FraudStatusId where ItemId='"+itemIDFromXML[0]+"'";	
			System.out.println(query);
			ResultSet rsFraudStatus = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
			FraudCheckResult_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudCheckResult", "FraudCheckResult");
			FraudCheckReason_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudCheckReason", "FraudCheckReason");
			NumberofEntries_NV=getTagValueByXPATH(templateFilePath, fileName, "//NumberofEntries", "NumberofEntries");
			while(rsFraudStatus.next())
			{
			String creationdate_db = rsFraudStatus.getString("CreationDate").replaceAll("[A-Za-z.]","");
			String creationdate_xml =CreationDateTime_NV[0].replaceAll("[A-Za-z.]"," ");
			flag= Component.verifyEquals(rsFraudStatus.getString("ItemId"), itemIDFromXML[0], "ItemId record validation");
			publishResults(flag, rsFraudStatus.getString("ItemId"), itemIDFromXML[0], "ItemId value validation");
			flag= Component.verifyEquals(rsFraudStatus.getString("TransactionSetId"), TransactionSetId_NV[0], "TransactionSetId record validation");
			publishResults(flag, rsFraudStatus.getString("TransactionSetId"), TransactionSetId_NV[0], "TransactionSetId validation");
			flag= Component.verifyEquals(rsFraudStatus.getString("FraudResult"), FraudCheckResult_NV[0], "FraudResult record validation");
			publishResults(flag, rsFraudStatus.getString("FraudResult"), FraudCheckResult_NV[0], "FraudResult validation");
			flag= Component.verifyEquals(creationdate_db.substring(0, creationdate_db.length()-2), creationdate_xml.substring(0, creationdate_xml.length()-6), "CreationDate record Validation");
			publishResults(flag, creationdate_db.substring(0, creationdate_db.length()-2),  creationdate_xml.substring(0, creationdate_xml.length()-6), " CreationDate Validation");
			flag= Component.verifyEquals(rsFraudStatus.getString("FraudReason"),FraudCheckReason_NV[0], "Fraud Check Reason record validation");
			publishResults(flag, rsFraudStatus.getString("FraudReason"),FraudCheckReason_NV[0], "Fraud Check Reason validation");
			flag= Component.verifyEquals(rsFraudStatus.getString("NumberOfEntries"), NumberofEntries_NV[0], "NumberOfEntries record validation");
			publishResults(flag, rsFraudStatus.getString("NumberOfEntries"),NumberofEntries_NV[0], "NumberOfEntries validation");
			DBValidations.validateItemId(rsFraudStatus.getString(1));
			DBValidations.validateFraudCheckReaosn(rsFraudStatus.getString(2));
			DBValidations.validateFraudCheckResult(rsFraudStatus.getString(4));
			DBValidations.validateTransactionSetId(rsFraudStatus.getString(2));
			DBValidations.validateCreatedDate(rsFraudStatus.getString(5));
			DBValidations.validateNumberOfEntries(rsFraudStatus.getString(6));
			}
			return rsFraudStatus;
		}
		
		
	// Description: This method gets the data in the DB for Fraud Additional Info Table 
	public static ResultSet getFraudAdditionalInfoAttributeValues(String fileName) throws Exception
	{
		String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		query ="select fsr.ItemId, fsr.FirstChequeDate,fsr.LastChequeDate, fsr.CounterpartiesCount,fsr.GoodChequesCount,fsr.FraudChequesCount, fsr.LargestAmount, fsr.RiskIndicator from Base.FraudStatusResults fsr join"
				  + " Base.FraudStatus fs  on fs.FraudStatusId = fsr.FraudStatusId where ItemId='"+itemIDFromXML[1]+"'";		
		System.out.println(query);
		FirstChequeDate_NV=getTagValueByXPATH(templateFilePath, fileName, "//FirstChequeDate", "FirstChequeDate");
		LastChequeDate_NV=getTagValueByXPATH(templateFilePath, fileName, "//LastChequeDate", "LastChequeDate");
		CounterpartiesCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//CounterpartiesCount", "CounterpartiesCount");
		GoodChequesCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//GoodChequesCount", "GoodChequesCount");
		FraudChequesCount_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudChequesCount", "FraudChequesCount");
		LargestAmount_NV=getTagValueByXPATH(templateFilePath, fileName, "//LargestAmount", "LargestAmount");
		RiskIndicator_NV=getTagValueByXPATH(templateFilePath, fileName, "//RiskIndicator", "RiskIndicator");
		ResultSet rsFraudAdditionalInfo = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsFraudAdditionalInfo.next())
		{
		flag= Component.verifyEquals(rsFraudAdditionalInfo.getString("ItemId"), itemIDFromXML[1], "ItemId record validation");
		publishResults(flag, rsFraudAdditionalInfo.getString("ItemId"), itemIDFromXML[1], "ItemId value validation");
		flag= Component.verifyEquals(rsFraudAdditionalInfo.getString("FirstChequeDate"), FirstChequeDate_NV[0], "FirstChequeDate record validation");
		publishResults(flag, rsFraudAdditionalInfo.getString("FirstChequeDate"), FirstChequeDate_NV[0], "FirstChequeDate validation");
		flag= Component.verifyEquals(rsFraudAdditionalInfo.getString("LastChequeDate"), LastChequeDate_NV[0], "LastChequeDate record validation");
		publishResults(flag, rsFraudAdditionalInfo.getString("LastChequeDate"), LastChequeDate_NV[0], "LastChequeDate validation");
		flag= Component.verifyEquals(rsFraudAdditionalInfo.getString("CounterpartiesCount"), CounterpartiesCount_NV[0], "CounterpartiesCount record validation");
		publishResults(flag, rsFraudAdditionalInfo.getString("CounterpartiesCount"), CounterpartiesCount_NV[0], "CounterpartiesCount validation");
		flag= Component.verifyEquals(rsFraudAdditionalInfo.getString("GoodChequesCount"), GoodChequesCount_NV[0], "GoodChequesCount record validation");
		publishResults(flag, rsFraudAdditionalInfo.getString("GoodChequesCount"),GoodChequesCount_NV[0], "GoodChequesCount validation");
		flag= Component.verifyEquals(rsFraudAdditionalInfo.getString("FraudChequesCount"), FraudChequesCount_NV[0], "FraudChequesCount record validation");
		publishResults(flag, rsFraudAdditionalInfo.getString("FraudChequesCount"), FraudChequesCount_NV[0], "FraudChequesCount validation");
		DBValidations.validateResponseType(rsFraudAdditionalInfo.getString(6));
		DBValidations.validateItemId(rsFraudAdditionalInfo.getString(1));
		DBValidations.validateFraudCheckResult(rsFraudAdditionalInfo.getString(4));
		DBValidations.validateFraudCheckReaosn(rsFraudAdditionalInfo.getString(3));
		DBValidations.validateTransactionSetId(rsFraudAdditionalInfo.getString(2));
		DBValidations.validateCreatedDate(rsFraudAdditionalInfo.getString(5));
		DBValidations.validateNumberOfEntries(rsFraudAdditionalInfo.getString(7));
	}
		return rsFraudAdditionalInfo;
	}	
	
	
	// Description: This method gets the data in the DB for Fraud Status Table 
	public static ResultSet getFraudStatusAttributeValuesWithNoFraudReason(String fileName) throws Exception
	{	
		String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		TransactionSetId_NV=getTagValueByXPATH(templateFilePath,fileName, "//TransactionSetId", "TransactionSetId");
		CreationDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//CreationDateTime", "CreationDateTime");

		query ="select fsr.ItemId, fsr.TransactionSetId,fsr.FraudReason, fsr.FraudResult,fs.CreationDate,fs.ResponseType, fs.NumberOfEntries from Base.FraudStatusResults fsr join"
		  + " Base.FraudStatus fs  on fs.FraudStatusId = fsr.FraudStatusId where ItemId='"+itemIDFromXML[0]+"'";	
		System.out.println(query);
		ResultSet rsFraudStatus = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		FraudCheckResult_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudCheckResult", "FraudCheckResult");
		FraudResponseType_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudResponseType", "FraudResponseType");
		NumberofEntries_NV=getTagValueByXPATH(templateFilePath, fileName, "//NumberofEntries", "NumberofEntries");
		while(rsFraudStatus.next())
		{
		String creationdate_db = rsFraudStatus.getString("CreationDate").replaceAll("[A-Za-z.]","");
		String creationdate_xml =CreationDateTime_NV[0].replaceAll("[A-Za-z.]"," ");
		flag= Component.verifyEquals(rsFraudStatus.getString("ItemId"), itemIDFromXML[0], "ItemId record validation");
		publishResults(flag, rsFraudStatus.getString("ItemId"), itemIDFromXML[0], "ItemId value validation");
		flag= Component.verifyEquals(rsFraudStatus.getString("TransactionSetId"), TransactionSetId_NV[0], "TransactionSetId record validation");
		publishResults(flag, rsFraudStatus.getString("TransactionSetId"), TransactionSetId_NV[0], "TransactionSetId validation");
		flag= Component.verifyEquals(rsFraudStatus.getString("FraudResult"), FraudCheckResult_NV[0], "FraudResult record validation");
		publishResults(flag, rsFraudStatus.getString("FraudResult"), FraudCheckResult_NV[0], "FraudResult validation");
		flag= Component.verifyEquals(creationdate_db.substring(0, creationdate_db.length()-2), creationdate_xml.substring(0, creationdate_xml.length()-6), "CreationDate record Validation");
		publishResults(flag, creationdate_db.substring(0, creationdate_db.length()-2),  creationdate_xml.substring(0, creationdate_xml.length()-6), " CreationDate Validation");
		flag= Component.verifyEquals(rsFraudStatus.getString("ResponseType"), FraudResponseType_NV[0], "ResponseType record validation");
		publishResults(flag, rsFraudStatus.getString("ResponseType"),FraudResponseType_NV[0], "ResponseType validation");
		flag= Component.verifyEquals(rsFraudStatus.getString("NumberOfEntries"), NumberofEntries_NV[0], "NumberOfEntries record validation");
		publishResults(flag, rsFraudStatus.getString("NumberOfEntries"),NumberofEntries_NV[0], "NumberOfEntries validation");
		DBValidations.validateResponseType(rsFraudStatus.getString(6));
		DBValidations.validateItemId(rsFraudStatus.getString(1));
		DBValidations.validateFraudCheckResult(rsFraudStatus.getString(4));
		DBValidations.validateTransactionSetId(rsFraudStatus.getString(2));
		DBValidations.validateCreatedDate(rsFraudStatus.getString(5));
		DBValidations.validateNumberOfEntries(rsFraudStatus.getString(7));
		}
		return rsFraudStatus;
	}	
	
	// Description: This method gets the data in the DB for Fraud Results Table 
	public static ResultSet getFraudResultsAttributeValuesWithNoFraudReason(String fileName) throws Exception
	{
		String[] itemIDFromXML=getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
		query ="select fsr.ItemId, fsr.TransactionSetId,fsr.FraudReason, fsr.FraudResult,fs.CreationDate,fs.ResponseType, fs.NumberOfEntries from Base.FraudStatusResults fsr join"
				  + " Base.FraudStatus fs  on fs.FraudStatusId = fsr.FraudStatusId where ItemId='"+itemIDFromXML[1]+"'";		
		System.out.println(query);
		FraudCheckResult_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudCheckResult", "FraudCheckResult");
		FraudResponseType_NV=getTagValueByXPATH(templateFilePath, fileName, "//FraudResponseType", "FraudResponseType");
		TransactionSetId_NV=getTagValueByXPATH(templateFilePath, fileName, "//TransactionSetId", "TransactionSetId");
		CreationDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//CreationDateTime", "CreationDateTime");
		NumberofEntries_NV=getTagValueByXPATH(templateFilePath, fileName, "//NumberofEntries", "NumberofEntries");
		ResultSet rsFraudResults = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsFraudResults.next())
		{
			String creationdate_db = rsFraudResults.getString("CreationDate").replaceAll("[A-Za-z.]","");
			String creationdate_xml =CreationDateTime_NV[0].replaceAll("[A-Za-z.]"," ");
		flag= Component.verifyEquals(rsFraudResults.getString("ItemId"), itemIDFromXML[1], "ItemId record validation");
		publishResults(flag, rsFraudResults.getString("ItemId"), itemIDFromXML[1], "ItemId value validation");
		flag= Component.verifyEquals(rsFraudResults.getString("TransactionSetId"), TransactionSetId_NV[0], "TransactionSetId record validation");
		publishResults(flag, rsFraudResults.getString("TransactionSetId"), TransactionSetId_NV[0], "TransactionSetId validation");
		//flag= Component.verifyEquals(rsFraudResults.getString("FraudReason"), FraudCheckReason_NV[1], "FraudReason record validation");
		//publishResults(flag, rsFraudResults.getString("FraudReason"), FraudCheckReason_NV[1], "FraudReason validation");
		flag= Component.verifyEquals(rsFraudResults.getString("FraudResult"), FraudCheckResult_NV[1], "FraudResult record validation");
		publishResults(flag, rsFraudResults.getString("FraudResult"), FraudCheckResult_NV[1], "FraudResult validation");
		flag= Component.verifyEquals(creationdate_db.substring(0, creationdate_db.length()-2), creationdate_xml.substring(0, creationdate_xml.length()-6), "CreationDate record Validation");
		publishResults(flag, creationdate_db.substring(0, creationdate_db.length()-2),  creationdate_xml.substring(0, creationdate_xml.length()-6), " CreationDate Validation");
		flag= Component.verifyEquals(rsFraudResults.getString("ResponseType"), FraudResponseType_NV[0], "ResponseType record validation");
		publishResults(flag, rsFraudResults.getString("ResponseType"), FraudResponseType_NV[0], "ResponseType validation");
		flag= Component.verifyEquals(rsFraudResults.getString("NumberOfEntries"), NumberofEntries_NV[0], "NumberOfEntries record validation");
		publishResults(flag, rsFraudResults.getString("NumberOfEntries"), NumberofEntries_NV[0], "NumberOfEntries validation");
		DBValidations.validateResponseType(rsFraudResults.getString(6));
		DBValidations.validateItemId(rsFraudResults.getString(1));
		DBValidations.validateFraudCheckResult(rsFraudResults.getString(4));
		DBValidations.validateTransactionSetId(rsFraudResults.getString(2));
		DBValidations.validateCreatedDate(rsFraudResults.getString(5));
		DBValidations.validateNumberOfEntries(rsFraudResults.getString(7));
		}
		return rsFraudResults;
	}	
	
	// Description: This method gets the data in the DB for External Message Type/look up Table 
	public static ResultSet getExtMessageTypeAttributeValue(String MessageId) throws Exception
	{
		query = "select * from Lookup.MessageType where MessageId="+MessageId;
		System.out.println(query); 
		ResultSet rsExtMsgType = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsExtMsgType.next())
		{
		flag= Component.verifyEquals(rsExtMsgType.getString("MessageType"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("ExtMessageType"), "ExtMessageType record validation");
		publishResults(flag, rsExtMsgType.getString("MessageType"), FetchXMLNodeValues.coreHeaderSetTagWiseData.get("ExtMessageType"), "ExtMessageType Value validation");
		DBValidations.validateExtMessageType(rsExtMsgType.getString("MessageType"));	
		}
		return rsExtMsgType;
	}	
	
	// Description: This method gets the data in the DB for Document Table 
	public static ResultSet getDocumentHeaderAttributeValues(String fileName) throws Exception
	{
		CreationDateTime_NV=getTagValueByXPATH(templateFilePath, fileName, "//CreationDateTime", "CreationDateTime");
		DocumentID_NV=getTagValueByXPATH(templateFilePath, fileName, "//DocumentId", "DocumentId");
		String DocumentID= DocumentID_NV[0];
		System.out.println(DocumentID);
		String submissionCounter=DocumentID.substring(DocumentID.lastIndexOf(":")+1).trim();
		System.out.println(submissionCounter);
		query ="select SubmissionCounter,CreatedDate, NumberOfEntries, SenderParticipantId, ChargedParticipantId from Base.Document "
				+" where SubmissionCounter ='"+submissionCounter+"'";
		System.out.println(query );
		ResultSet rsDocument = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsDocument.next())
		{
		String creationdate_db = rsDocument.getString("CreatedDate").replaceAll("[A-Za-z.]","");
		String creationdate_xml =CreationDateTime_NV[0].replaceAll("[A-Za-z.+]"," ");
		flag= Component.verifyEquals(creationdate_db.substring(0, creationdate_db.length()-3), creationdate_xml.substring(0, creationdate_xml.length()-6), "CreationDate record Validation");
		publishResults(flag, creationdate_db.substring(0, creationdate_db.length()-3),  creationdate_xml.substring(0, creationdate_xml.length()-6), " CreationDate Validation");
		flag= Component.verifyEquals(rsDocument.getString("SubmissionCounter"), submissionCounter, "DocumentId record validation");
		publishResults(flag, rsDocument.getString("SubmissionCounter"),submissionCounter, "Validation of DocumentId in Document Table");
		flag= Component.verifyEquals(rsDocument.getString("NumberOfEntries"), FetchXMLNodeValues.documentHeaderSetTagWiseData.get("NumberofEntries"), "NumberofEntries record validation");
		publishResults(flag, rsDocument.getString("NumberOfEntries"),FetchXMLNodeValues.documentHeaderSetTagWiseData.get("NumberofEntries"), "Validation of NumberOfEntries in Document Table");
		flag= Component.verifyEquals(rsDocument.getString("SenderParticipantId"), FetchXMLNodeValues.documentHeaderSetTagWiseData.get("SenderId"), "SenderParticipantId record validation");
		publishResults(flag, rsDocument.getString("SenderParticipantId"), FetchXMLNodeValues.documentHeaderSetTagWiseData.get("SenderId"), "Validation of SenderParticipantId in Document Table");
		flag= Component.verifyEquals(rsDocument.getString("ChargedParticipantId"), FetchXMLNodeValues.documentHeaderSetTagWiseData.get("ChargingParticipant"), "ChargedParticipantId record validation");
		publishResults(flag, rsDocument.getString("ChargedParticipantId"), FetchXMLNodeValues.documentHeaderSetTagWiseData.get("ChargingParticipant"), "Validation of ChargedParticipantId in Document Table");
		DBValidations.validateDocumentId(rsDocument.getString(1));
		DBValidations.validateCreatedDate(rsDocument.getString(2));
		DBValidations.validateNumberOfEntries(rsDocument.getString(3));
		DBValidations.validateSenderId(rsDocument.getString(4));
		DBValidations.validateChargingParticipantId(rsDocument.getString(5));
		}
		return rsDocument;
	}	
	
	// Description: This method gets the data in the DB for Debit Header Table 
	public static ResultSet getDebitHeaderAttributeValues() throws Exception
	{
		query = "select CoreId as BusinessDate, ExtractId,ParticipantId as ProcessingParticipantId, MessageType as ExtMessageType,IntMessageType, Source as MessageSource, Destination as MessageDestination, RecordCount as RecordCounts FROM Base.Core"
				+  " where Extractid='"+FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("ExtractId")+"'";
		System.out.println(query);
		ResultSet rsDebit = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsDebit.next())
		{
			flag= Component.verifyEquals((rsDebit.getString("BusinessDate").substring(0,8)), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("BusinessDate").replaceAll("-", ""), "BusinessDate Validation");
			publishResults(flag, rsDebit.getString("BusinessDate").substring(0,8), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("BusinessDate").replaceAll("-", ""), "Business Date Value Validation ");
			flag= Component.verifyEquals(rsDebit.getString("ExtractId"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("ExtractId"), "ExtractId validation");
			publishResults(flag, rsDebit.getString("ExtractId"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("ExtractId"), "ExtractId Value validation");
			flag= Component.verifyEquals(rsDebit.getString("ProcessingParticipantId"),FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("ProcessingParticipantId"), "ProcessingParticipantId record validation");
			publishResults(flag, rsDebit.getString("ProcessingParticipantId"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("ProcessingParticipantId"), "ProcessingParticipantId Value validation");
			flag= Component.verifyEquals(rsDebit.getString("IntMessageType"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("IntMessageType"), "IntMessageType record validation");
			publishResults(flag, rsDebit.getString("IntMessageType"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("IntMessageType"), "IntMessageType Value validation ");
			flag= Component.verifyEquals(rsDebit.getString("MessageSource"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("MessageSource"), "MessageSource record validation");
			publishResults(flag, rsDebit.getString("MessageSource"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("MessageSource"), "Source Value validation ");
			flag= Component.verifyEquals(rsDebit.getString("MessageDestination"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("MessageDestination"), "MessageDestination record validation");
			publishResults(flag, rsDebit.getString("MessageDestination"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("MessageDestination"), "Destination Value validation ");
			flag= Component.verifyEquals(rsDebit.getString("RecordCounts"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("RecordCounts"), "RecordCounts record validation");
			publishResults(flag, rsDebit.getString("RecordCounts"), FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("RecordCounts"), "RecordCounts Value validation ");
			DBValidations.validateBusinessDate(FetchXMLNodeValues.debitItemHeaderSetTagWiseData.get("BusinessDate"));
			DBValidations.validateExtractId(rsDebit.getString(2));
			DBValidations.validateProcessingParticipantId(rsDebit.getString(3));
			DBValidations.validateIntMessageType(rsDebit.getString(5));
			DBValidations.validateMessageSource(rsDebit.getString(6));
			DBValidations.validateMessageDestination(rsDebit.getString(7));
			DBValidations.validateRecordCounts(rsDebit.getString(8));	
		}
		return rsDebit;
	}
	
	// Description: This method gets the data in the DB for Credit Header Table 
	public static ResultSet getCreditHeaderAttributeValues() throws Exception
	{
		query = "select CoreId as BusinessDate, ExtractId,ParticipantId as ProcessingParticipantId, MessageType as ExtMessageType,IntMessageType, Source as MessageSource, Destination as MessageDestination, RecordCount as RecordCounts FROM Base.Core"
				+  " where Extractid='"+FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("ExtractId")+"'";
		System.out.println(query);
		ResultSet rsCredit = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
		while(rsCredit.next())
		{
			flag= Component.verifyEquals((rsCredit.getString("BusinessDate").substring(0,8)), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("BusinessDate").replaceAll("-", ""), "BusinessDate validation");
			publishResults(flag, rsCredit.getString("BusinessDate").substring(0,8), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("BusinessDate").replaceAll("-", ""), "Business Date Value validation ");
			flag= Component.verifyEquals(rsCredit.getString("ExtractId"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("ExtractId"), "ExtractId validation");
			publishResults(flag, rsCredit.getString("ExtractId"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("ExtractId"), "ExtractId Value validation");
			flag= Component.verifyEquals(rsCredit.getString("ProcessingParticipantId"),FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("ProcessingParticipantId"), "ProcessingParticipantId record validation");
			publishResults(flag, rsCredit.getString("ProcessingParticipantId"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("ProcessingParticipantId"), "ProcessingParticipantId Value validation");
			flag= Component.verifyEquals(rsCredit.getString("IntMessageType"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("IntMessageType"), "IntMessageType record validation");
			publishResults(flag, rsCredit.getString("IntMessageType"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("IntMessageType"), "IntMessageType Value validation ");
			flag= Component.verifyEquals(rsCredit.getString("MessageSource"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("MessageSource"), "MessageSource record validation");
			publishResults(flag, rsCredit.getString("MessageSource"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("MessageSource"), "Source Value validation ");
			flag= Component.verifyEquals(rsCredit.getString("MessageDestination"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("MessageDestination"), "MessageDestination record validation");
			publishResults(flag, rsCredit.getString("MessageDestination"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("MessageDestination"), "Destination Value validation ");
			flag= Component.verifyEquals(rsCredit.getString("RecordCounts"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("RecordCounts"), "RecordCounts record validation");
			publishResults(flag, rsCredit.getString("RecordCounts"), FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("RecordCounts"), "RecordCounts Value validation ");
			DBValidations.validateBusinessDate(FetchXMLNodeValues.creditItemHeaderSetTagWiseData.get("BusinessDate"));
			DBValidations.validateExtractId(rsCredit.getString(2));
			DBValidations.validateProcessingParticipantId(rsCredit.getString(3));
			DBValidations.validateIntMessageType(rsCredit.getString(5));
			DBValidations.validateMessageSource(rsCredit.getString(6));
			DBValidations.validateMessageDestination(rsCredit.getString(7));
			DBValidations.validateRecordCounts(rsCredit.getString(8));	
		}
		return rsCredit;
	}
	
	// Description: This method verifies the DB validations for Core Table 
	public static void assertCoreHeader(String templateFilePath, String fileName) throws java.text.ParseException, IOException{
		businessDate_NV = GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//BusinessDate", "BusinessDate");
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		date = formatter.parse(businessDate_NV[0]);
		Assert.assertTrue(DBValidations.validateBusinessDate(businessDate_NV[0]));
		ExtractId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ExtractId", "ExtractId");
		Assert.assertTrue(DBValidations.validateExtractId(ExtractId_NV[0]));
		ProcessingParticipantId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ProcessingParticipantId", "ProcessingParticipantId");
		Assert.assertTrue(DBValidations.validateProcessingParticipantId(ProcessingParticipantId_NV[0]));
		ExtMessageType_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ExtMessageType", "ExtMessageType");
		Assert.assertTrue(DBValidations.validateExtMessageType(ExtMessageType_NV[0]));
		IntMessageType_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//IntMessageType", "IntMessageType");
		Assert.assertTrue(DBValidations.validateIntMessageType(IntMessageType_NV[0]));
		MessageSource_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//MessageSource", "MessageSource");
		Assert.assertTrue(DBValidations.validateMessageSourceDataType(MessageSource_NV[0]));
		MessageDestination_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//MessageDestination", "MessageDestination");
		Assert.assertTrue(DBValidations.validateMessageDestinationDataType(MessageDestination_NV[0]));
		if (fileName.contains("<PayDecision>"))
		RecordCounts_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//RecordCounts", "RecordCounts");
		Assert.assertTrue(DBValidations.validateRecordCounts(RecordCounts_NV[0]));
	}
		
		// Description: This method verifies the DB validations for Entity Table 
		public static void assertEntityHeader(String templateFilePath, String fileName) throws java.text.ParseException
		{
		EntityType_NV = GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//EntityType", "EntityType");
		Assert.assertTrue(DBValidations.validateEntityType(EntityType_NV[0]));
		Assert.assertTrue(DBValidations.validateEntityTypeID(EntityType_NV[0]));
		EntityId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//EntityId", "EntityId");
		Assert.assertTrue(DBValidations.validateEntityId(EntityId_NV[0]));
		StateRevision_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//StateRevision", "StateRevision");
		Assert.assertTrue((StateRevision_NV[0].length()==6));
		EntityState_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//EntityState", "EntityState");
		Assert.assertTrue(DBValidations.validateEntityState(EntityState_NV[0]));
		SourceDateTime_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//SourceDateTime", "SourceDateTime");
		dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		tempDate=dateFormat.parse(SourceDateTime_NV[0]);
		Assert.assertTrue(DBValidations.validateSourceDateTime(SourceDateTime_NV[0]));
	}
		
		// Description: This method verifies the DB validations for Item Table 
		public static void assertItemHeader(String templateFilePath, String fileName)
		{
			ItemId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");
			Assert.assertTrue(DBValidations.validateItemId(ItemId_NV[0]));
			ProcessId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ProcessId", "ProcessId");
			Assert.assertTrue(DBValidations.validateProcessIdDataType(ProcessId_NV[0]));
			OperatorId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//OperatorId", "OperatorId");
			Assert.assertTrue(DBValidations.validateOperatorIdDataType(OperatorId_NV[0]));
			UpdateDateTime_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//UpdateDateTime", "UpdateDateTime");
			Assert.assertTrue(DBValidations.validateUpdateDateTime(UpdateDateTime_NV[0]));
			AuditRevision_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//AuditRevision", "AuditRevision");
			Assert.assertTrue(DBValidations.validateAuditRevision(AuditRevision_NV[0]));
			Gender_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//Gender", "Gender");
			Assert.assertTrue(DBValidations.validateGender(Gender_NV[0]));
		}
		
		// Description: This method verifies the DB validations for EntityError Table 
		public static void assertEntityError(String templateFilePath, String fileName)
		{
			ErrorCode_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ErrorCode", "ErrorCode");
			Assert.assertTrue(DBValidations.validateErrorCode(ErrorCode_NV[0]));
			ErrorDescription_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ErrorDescription", "ErrorDescription");
			Assert.assertTrue(DBValidations.validateEntityErroDesc(ErrorDescription_NV[0]));
			ErrorLocation_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ErrorLocation", "ErrorLocation");
			Assert.assertTrue(DBValidations.validateEntityErrorLocation(ErrorLocation_NV[0]));
		}
		
		// Description: This method verifies the DB validations for CodeLine Table 
		public static void assertCodeLine(String templateFilePath, String fileName)
		{
			SortCode_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//SortCode", "SortCode");
			Assert.assertTrue(DBValidations.validateOriginalSortCode(ErrorCode_NV[0]));
			Account_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//Account", "Account");
			Assert.assertTrue(DBValidations.validateAccount(ErrorDescription_NV[0]));
			Amount_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//Amount", "Amount");
			Assert.assertTrue(DBValidations.validateAmount(Amount_NV[0]));
		}
		
		// Description: This method verifies the DB validations for Code Line reference Table 
		public static void assertCodeLineReference(String templateFilePath, String fileName)
		{
			assertCodeLine("D:\\Sandeep\\TestData\\", "03MA02.xml");
			Reference_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//Reference", "Reference");
			Assert.assertTrue(DBValidations.validateOriginalSortCode(Reference_NV[0]));
			TranCode_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//TranCode", "TranCode");
			Assert.assertTrue(DBValidations.validateAccount(TranCode_NV[0]));
		}
		
		// Description: This method verifies the DB validations for Response Window Table 
		public static void assertResponseWindow(String templateFilePath, String fileName)
		{
			Day2ResponseWindowStartDateTime_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//Day2ResponseWindowStartDateTime", "Day2ResponseWindowStartDateTime");
			Assert.assertTrue(DBValidations.validateOriginalSortCode(Day2ResponseWindowStartDateTime_NV[0]));
			Day2ResponseWindowEndDateTime_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//Day2ResponseWindowEndDateTime", "Day2ResponseWindowEndDateTime");
			Assert.assertTrue(DBValidations.validateAccount(Day2ResponseWindowEndDateTime_NV[0]));
		}
		
		// Description: This method verifies the DB validations for Open Case Table 
		public static void assertOpenCaseHeader(String templateFilePath, String fileName) 
		{
		CaseId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//CaseId", "CaseId");
		Assert.assertTrue(DBValidations.validateCaseID(CaseId_NV[0]));
		CaseType_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//CaseType", "CaseType");
		Assert.assertTrue(DBValidations.validateCaseType(CaseType_NV[0]));
		EntityType_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//EntityType", "EntityType");
		Assert.assertTrue(DBValidations.validateEntityState(EntityType_NV[0]));
		EntityId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//EntityId", "EntityId");
		Assert.assertTrue(DBValidations.validateSourceDateTime(EntityId_NV[0]));
		}
		
		// Description: This method verifies the DB validations for Closed Case Table 
		public static void assertClosedCaseHeader(String templateFilePath, String fileName) 
		{
		CaseId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//CaseId", "CaseId");
		Assert.assertTrue(DBValidations.validateCaseID(CaseId_NV[0]));
		}
		
		// Description: This method verifies the DB validations for Job Table 
		public static void assertJobHeader(String templateFilePath, String fileName) 
		{
		businessDate_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//BusinessDate", "BusinessDate");
		Assert.assertTrue(DBValidations.validateCaseID(businessDate_NV[0]));
		InstallationId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//InstallationId", "InstallationId");
		Assert.assertTrue(DBValidations.validateCaseType(InstallationId_NV[0]));
		CaptureSystemId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//CaptureSystemId", "CaptureSystemId");
		Assert.assertTrue(DBValidations.validateEntityState(CaptureSystemId_NV[0]));
		StartTime_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//StartTime", "StartTime");
		Assert.assertTrue(DBValidations.validateSourceDateTime(StartTime_NV[0]));
		EndTime_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//EndTime", "EndTime");
		Assert.assertTrue(DBValidations.validateCaseID(EndTime_NV[0]));
		WorkTypeNbr_NVR =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//WorkTypeNbr", "WorkTypeNbr");
		Assert.assertTrue(DBValidations.validateCaseType(WorkTypeNbr_NVR[0]));
		SortFamily_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//SortFamily", "SortFamily");
		Assert.assertTrue(DBValidations.validateEntityState(SortFamily_NV[0]));
		SourceType_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//SourceType", "SourceType");
		Assert.assertTrue(DBValidations.validateSourceDateTime(SourceType_NV[0]));
		SourceName_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//SourceName", "SourceName");
		Assert.assertTrue(DBValidations.validateCaseID(SourceName_NV[0]));
		SourceID_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//SourceID", "SourceID");
		Assert.assertTrue(DBValidations.validateCaseType(CaseType_NV[0]));
		FinancialInstitutionID_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//FinancialInstitutionID", "FinancialInstitutionID");
		Assert.assertTrue(DBValidations.validateEntityState(EntityType_NV[0]));
		CollectionStartTime_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//CollectionStartTime", "CollectionStartTime");
		Assert.assertTrue(DBValidations.validateSourceDateTime(EntityId_NV[0]));
		CollectionEndTime_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//CollectionEndTime", "CollectionEndTime");
		Assert.assertTrue(DBValidations.validateCaseID(CaseId_NV[0]));
		
		}
			
		// Description: This method verifies the DB validations for Block Table 
		public static void assertBlockHeader(String templateFilePath, String fileName) 
		{
		BlkNbr_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//BlkNbr_NV", "BlkNbr_NV");
		Assert.assertTrue(DBValidations.validateCaseID(PayDecision_NV[0]));
		ImageType_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ImageType", "ImageType");
		Assert.assertTrue(DBValidations.validateCaseID(ImageType_NV[0]));
	}
		
		// Description: This method verifies the DB validations for Posting Batch Table 
		public static void assertPostingBatchItemsHeader(String templateFilePath, String fileName) 
		{
		ItemId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ItemId_NV", "ItemId_NV");
		Assert.assertTrue(DBValidations.validateItemId(ItemId_NV[0]));
		TriggeringState_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//TrigerringState", "TrigerringState");
		Assert.assertTrue(DBValidations.validateCaseID(TriggeringState_NV[0]));
	}
		
		// Description: This method verifies the DB validations for Pay decision Table 
		public static void assertPayDecisionHeader(String templateFilePath, String fileName) 
		{
		PayDecision_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//PayDecision", "PayDecision");
		Assert.assertTrue(DBValidations.validateCaseID(PayDecision_NV[0]));
		PayDecisionReasonCode_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//PayDecisionReasonCode", "PayDecisionReasonCode");
		Assert.assertTrue(DBValidations.validateCaseID(PayDecisionReasonCode_NV[0]));
	}
		
		// Description: This method verifies the DB validations for Fraud response Table 
		public static void assertFraudResponseHeader(String templateFilePath, String fileName)
		{
		CreationDateTime_NV = GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//CreationDateTime", "CreationDateTime");
		Assert.assertTrue(DBValidations.validateCreatedDate(CreationDateTime_NV[0]));
		NumberofEntries_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//NumberofEntries", "NumberofEntries");
		Assert.assertTrue(DBValidations.validateNumberOfEntries(NumberofEntries_NV[0]));
		FraudResponseType_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//FraudResponseType", "FraudResponseType");
		Assert.assertTrue(DBValidations.validateResponseType(FraudCheckResult_NV[0]));
		TransactionSetId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//TransactionSetId", "TransactionSetId");
		Assert.assertTrue(DBValidations.validateTransactionSetId(TransactionSetId_NV[0]));
		ItemId_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//ItemId", "ItemId");	
		Assert.assertTrue(DBValidations.validateItemId(ItemId_NV[0]));
		Assert.assertTrue(DBValidations.validateItemId(ItemId_NV[1]));
		FraudCheckResult_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//FraudCheckResult", "FraudCheckResult");
		Assert.assertTrue(DBValidations.validateFraudCheckResult(FraudCheckResult_NV[0]));
		Assert.assertTrue(DBValidations.validateFraudCheckResult(FraudCheckResult_NV[1]));
		FraudCheckReason_NV =GenericMethodUtilis.getTagValueByXPATH(templateFilePath, fileName, "//FraudCheckReason", "FraudCheckReason");	
		Assert.assertTrue(DBValidations.validateFraudCheckReaosn(FraudCheckResult_NV[0]));
		Assert.assertTrue(DBValidations.validateFraudCheckReaosn(FraudCheckResult_NV[1]));
		}
	
	// Description: This method gets the node list for all the tags from the XML 
	private static NodeList getNodeSetList(String xsdFilesPath, String xsdFileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(xsdFilesPath, xsdFileName)).getElementsByTagName(elementsSetWithTagName);		
	}	
	
}