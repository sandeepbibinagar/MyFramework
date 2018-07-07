package com.ics.fred.testScenarios;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadImageValueFromSQLFile;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateMF01DataLoadedIntoDatabase extends ICSDBUtilis{


	static String itemIDQuery;
	static String listItemIDVal;
	//static String listItemIDVal;
	static String extractID;
	static String xpathItemId="//Items/Item/ItemId";

public static void validateMF01DataLoadedIntoDB(String dbServerName,String fredDBName,String templateFileType,String filepath,String destFilePath,String fileName,String sqlTemplateExtractIDToBeReplaced,String sqlTemplateBusinessDateToBeReplaced,String dbItemImageDataTypeQuery,String dbImageFetchQuery2,String sqlConfigBusinessDate) throws Exception{
	//fileName= "TC_MSG05_CR";
	String fileTobeCreated =fileName+"_Actual";
	String fileExtension=".sql";
	String fileTobeCopied =fileName+"_Actual.sql";
	String dbSQLFileInjectCommand = "cmd /c sqlcmd -S "+dbServerName+" -d "+fredDBName+" -i "+filepath + fileName + "_Actual.sql ";		
	 extractID =  generateExtractId("CRED");	
	 
	createFileFromTemplate(filepath, fileName,".sql", sqlTemplateExtractIDToBeReplaced, extractID);
	String actualConfigDate =getActualConfigDate(dbServerName,fredDBName,sqlConfigBusinessDate);
	createFileFromTemplate(filepath, fileTobeCreated,".sql", sqlTemplateBusinessDateToBeReplaced, actualConfigDate);
	sqlCommandExecution(dbSQLFileInjectCommand);
	System.out.println("SQL File has been loaded into database ");
	validationStepInformation(" SQL File has been loaded into database ");

	copyFileFromOneLocationToAnother(filepath,destFilePath,fileTobeCopied);
	createFileFromTemplateSplit(destFilePath,fileTobeCreated,fileExtension);	
	//readAllXMLTagsFromMF01File(destFilePath,fileTobeCreated,dbServerName,fredDBName,extractID);
	validateMF01ItemCodelineReferenceWithDb(destFilePath,fileTobeCreated,dbServerName,fredDBName);
	validateCoreExtractId(destFilePath,fileTobeCreated,dbServerName,fredDBName,extractID);
	validateCoreProcessingParticipantId(destFilePath,fileTobeCreated,dbServerName,fredDBName,extractID);
	validateCoreExtMessageType(destFilePath,fileTobeCreated,dbServerName,fredDBName,extractID);
	validateCoreIntMessageType(destFilePath,fileTobeCreated,dbServerName,fredDBName,extractID);
	validateCoreMessageSource(destFilePath,fileTobeCreated,dbServerName,fredDBName,extractID);
	validateCoreMessageDestination(destFilePath,fileTobeCreated,dbServerName,fredDBName,extractID);
	validateCoreRecordCounts(destFilePath,fileTobeCreated,dbServerName,fredDBName,extractID);
	
	validateMF01ItemIDWithDb(destFilePath,fileTobeCreated,dbServerName,fredDBName);
	verifyImageDataLoadedIntoDatabase(destFilePath,fileTobeCreated,dbServerName,fredDBName,dbItemImageDataTypeQuery,dbImageFetchQuery2,extractID);
	//validateMF01ItemImageWithDb(destFilePath,fileTobeCreated,dbServerName,fredDBName,templateFileType,dbItemImageDataTypeQuery,dbImageFetchQuery2);
	
	validateMF01ItemCodelineReferenceWithDb(destFilePath,fileTobeCreated,dbServerName,fredDBName);
	validateMF01ItemCodelineTrancodeWithDb(destFilePath,fileTobeCreated,dbServerName,fredDBName);
	validateMF01ItemCodelineSerialWithDb(destFilePath,fileTobeCreated,dbServerName,fredDBName);
	validateMF01ItemCodelineSortCodeWithDb(destFilePath,fileTobeCreated,dbServerName,fredDBName);
	validateMF01ItemCodelineAccountWithDb(destFilePath,fileTobeCreated,dbServerName,fredDBName);
	validateMF01ItemCodelineAmountWithDb(destFilePath,fileTobeCreated,dbServerName,fredDBName);
	validateMF01ItemCodelineCurrencyWithDb(destFilePath,fileTobeCreated,dbServerName,fredDBName);
	
}

public static void createFileFromTemplateSplit(String destFilePath, String fileName, String fileExtension) throws Exception
{		
	
	String content = readDataOfFileStream(getFileStreamWithExtension(destFilePath, fileName, fileExtension));
	String[] splitSQLContent = content.split("'");
	for(int i=0;i<splitSQLContent.length;i++){
		System.out.println("Splitted SQL data:"+i+" "+splitSQLContent[i]);
	}
	
	writeDataOfFileStream(getFileStreamWithExtension(destFilePath, fileName, ".xml"), splitSQLContent[1], false);
	
}

public static void copyFileFromOneLocationToAnother(String srcFilePath,String destFilePath,String fileName) throws Exception{
File srcFile = new File(srcFilePath+fileName);
File destFile = new File (destFilePath+fileName);
FileUtils.copyFile(srcFile, destFile);
}

public static ArrayList<String> readAllXMLTagsFromMF01File(String filePath,String fileName,String dbServerName,String fredDBName,String extractID) throws Exception
{	
		String xpathCoreBusinessDate ="//ICN/Core/BusinessDate";		
		String ProcessingParticipantId="//Core/ProcessingParticipantId";
		String ExtMessageType="//Core/ExtMessageType";
		String IntMessageType="//Core/IntMessageType";
		String MessageSource="//Core/MessageSource";
		String MessageDestination="//Core/MessageDestination";
		String RecordCounts="//Core/RecordCounts";	
		//fileName=fileName+".xml";
		ArrayList<String> returnVal = new ArrayList<>();
		ArrayList<String> itemID =getItemIdVal(filePath,fileName,dbServerName,fredDBName);
		System.out.println("itemID :"+itemID);
		String bdateQuery;
		/*String query ="SELECT Reference FROM [Base].[MF01_Items] I JOIN [Base].[MF01_Batches] B ON I.BatchDetailsID=B.BatchDetailsID "+
				"WHERE I.MessageItemID in ('A7BN161627876060000000061','A7BN161627876060000000062') AND B.ExtractID='20170505104209CREDIN104209'";
*/
		String query ="SELECT Reference FROM [Base].[MF01_Items] I JOIN [Base].[MF01_Batches] B ON I.BatchDetailsID=B.BatchDetailsID "+
				"WHERE I.MessageItemID in ("+getStringFromArrayList(itemID)+")"+" AND B.ExtractID='"+extractID+"'";
		System.out.println("Item query New"+query);
		ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, query);
		String rsItem = null;
		while (resultSet.next()) {
			rsItem = resultSet.getString(1);
			break;
		}
		returnVal.add(rsItem);
		//returnVal.add(String.valueOf(itemID.size()));
		System.out.println("returnVal readAll :"+returnVal+" rsItem: "+rsItem);
		return returnVal;
		
		
}

	public static void validateCoreExtractId(String filePath,String fileName,String dbServerName,String fredDBName,String extractID) throws Exception{
	String coreExtractIDQuery;	
	String xpathExtractId ="//Core/ExtractId";
	String actualExtractID;
	String extractIDNodeVal;
	fileName =fileName+".xml";
	validationStepInformation("******************************************************************************");
	validationStepInformation("*********Validate MF01 Core ExtractID tag value is loaded into database.***********");
	validationStepInformation("******************************************************************************");
	
	Map<String,String> mf01XPATHExtractID= getXMLNodeValueByXPATH(filePath,fileName,xpathExtractId);
		for(int i=0;i<mf01XPATHExtractID.size();i++){
			extractIDNodeVal = getNodeListValues(mf01XPATHExtractID,"ExtractId").get(i);
			System.out.println("MF01 ExtractId :"+extractIDNodeVal);
			coreExtractIDQuery="select ExtractId from Base.MF01_Batches where ExtractID='"+extractID+"'";
			System.out.println("ExtractId Query in :"+coreExtractIDQuery);
			ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, coreExtractIDQuery);
			actualExtractID = GenericMethods.getICSRetrieveColumnValues(resultSet);//.getICSRetrieveMutipleColumnValues(rsdate,businessDate);		
			System.out.println("Actual ExtractID :"+actualExtractID);
			boolean returnExpectedReslt = Component.verifyEquals(actualExtractID, extractIDNodeVal, "ExtractID is loaded into database.");
			publishResults(returnExpectedReslt, actualExtractID, extractIDNodeVal, "Core ExtractID is loaded into database.");
		}	
}
	public static void validateCoreProcessingParticipantId(String filePath,String fileName,String dbServerName,String fredDBName,String extractID) throws Exception{
		String coreProcessingParticipantIdQuery;	
		String xpathProcessingParticipantId ="//Core/ProcessingParticipantId";
		String actualProcessingParticipantId;
		String processingParticipantIdNodeVal;
		fileName =fileName+".xml";
		
		validationStepInformation("******************************************************************************");
		validationStepInformation("*********Validate MF01 Core ProcessingParticipantId tag value is loaded into database.***********");
		validationStepInformation("******************************************************************************");
				
		Map<String,String> mf01XpathProcessingParticipantId= getXMLNodeValueByXPATH(filePath,fileName,xpathProcessingParticipantId);
			for(int i=0;i<mf01XpathProcessingParticipantId.size();i++){
				processingParticipantIdNodeVal = getNodeListValues(mf01XpathProcessingParticipantId,"ProcessingParticipantId").get(i);
				System.out.println("MF01 ProcessingParticipantId :"+processingParticipantIdNodeVal);
				coreProcessingParticipantIdQuery="select ProcessingParticipantId from Base.MF01_Batches where ExtractID='"+extractID+"'";
				System.out.println("ProcessingParticipantId Query in :"+coreProcessingParticipantIdQuery);
				ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, coreProcessingParticipantIdQuery);
				actualProcessingParticipantId = GenericMethods.getICSRetrieveColumnValues(resultSet);//.getICSRetrieveMutipleColumnValues(rsdate,businessDate);		
				System.out.println("Actual ProcessingParticipantId :"+actualProcessingParticipantId);
				boolean returnExpectedReslt = Component.verifyEquals(actualProcessingParticipantId, processingParticipantIdNodeVal, "ProcessingParticipantId is loaded into database.");
				publishResults(returnExpectedReslt, actualProcessingParticipantId, processingParticipantIdNodeVal, "Core ProcessingParticipantId is loaded into database.");
			}	
	}
	public static void validateCoreExtMessageType(String filePath,String fileName,String dbServerName,String fredDBName,String extractID) throws Exception{
		String coreExtMessageTypeQuery;	
		String xpathExtMessageType ="//Core/ExtMessageType";
		String actualExtMessageType;
		String extMessageTypeNodeVal;
		fileName =fileName+".xml";
		validationStepInformation("******************************************************************************");
		validationStepInformation("*********Validate MF01 Core ExtMessageType tag value is loaded into database.***********");
		validationStepInformation("******************************************************************************");
				
		Map<String,String> mf01XpathExtMessageType= getXMLNodeValueByXPATH(filePath,fileName,xpathExtMessageType);
			for(int i=0;i<mf01XpathExtMessageType.size();i++){
				extMessageTypeNodeVal = getNodeListValues(mf01XpathExtMessageType,"ExtMessageType").get(i);
				System.out.println("MF01 ExtMessageType :"+extMessageTypeNodeVal);
				coreExtMessageTypeQuery="select ExtMessageType from Base.MF01_Batches where ExtractID='"+extractID+"'";
				System.out.println("ExtMessageType Query in :"+coreExtMessageTypeQuery);
				ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, coreExtMessageTypeQuery);
				actualExtMessageType = GenericMethods.getICSRetrieveColumnValues(resultSet);//.getICSRetrieveMutipleColumnValues(rsdate,businessDate);		
				System.out.println("Actual ExtMessageType :"+actualExtMessageType);
				boolean returnExpectedReslt = Component.verifyEquals(actualExtMessageType, extMessageTypeNodeVal, "ExtMessageType is loaded into database.");
				publishResults(returnExpectedReslt, actualExtMessageType, extMessageTypeNodeVal, "Core ExtMessageType is loaded into database.");
			}	
	}
	
	public static void validateCoreIntMessageType(String filePath,String fileName,String dbServerName,String fredDBName,String extractID) throws Exception{
		String coreIntMessageTypeQuery;	
		String xpathIntMessageType ="//Core/IntMessageType";
		String actualIntMessageType;
		String intMessageTypeNodeVal;
		fileName =fileName+".xml";
		
		validationStepInformation("******************************************************************************");
		validationStepInformation("*********Validate MF01 Core IntMessageType tag value is loaded into database.***********");
		validationStepInformation("******************************************************************************");
		
		Map<String,String> mf01XpathIntMessageType= getXMLNodeValueByXPATH(filePath,fileName,xpathIntMessageType);
			for(int i=0;i<mf01XpathIntMessageType.size();i++){
				intMessageTypeNodeVal = getNodeListValues(mf01XpathIntMessageType,"IntMessageType").get(i);
				System.out.println("MF01 IntMessageType :"+intMessageTypeNodeVal);
				coreIntMessageTypeQuery="select IntMessageType from Base.MF01_Batches where ExtractID='"+extractID+"'";
				System.out.println("IntMessageType Query in :"+coreIntMessageTypeQuery);
				ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, coreIntMessageTypeQuery);
				actualIntMessageType = GenericMethods.getICSRetrieveColumnValues(resultSet);//.getICSRetrieveMutipleColumnValues(rsdate,businessDate);		
				System.out.println("Actual IntMessageType :"+actualIntMessageType);
				boolean returnExpectedReslt = Component.verifyEquals(actualIntMessageType, intMessageTypeNodeVal, "IntMessageType is loaded into database.");
				publishResults(returnExpectedReslt, actualIntMessageType, intMessageTypeNodeVal, "Core IntMessageType is loaded into database.");
			}	
	}
	
	public static void validateCoreMessageSource(String filePath,String fileName,String dbServerName,String fredDBName,String extractID) throws Exception{
		String coreMessageSourceQuery;	
		String xpathMessageSource ="//Core/MessageSource";
		String actualMessageSource;
		String MessageSourceNodeVal;
		fileName =fileName+".xml";
		validationStepInformation("******************************************************************************");
		validationStepInformation("*********Validate MF01 Core MessageSource tag value is loaded into database.***********");
		validationStepInformation("******************************************************************************");
		
		
		Map<String,String> mf01XPATHExtractID= getXMLNodeValueByXPATH(filePath,fileName,xpathMessageSource);
			for(int i=0;i<mf01XPATHExtractID.size();i++){
				MessageSourceNodeVal = getNodeListValues(mf01XPATHExtractID,"MessageSource").get(i);
				System.out.println("MF01 MessageSource :"+MessageSourceNodeVal);
				coreMessageSourceQuery="select MessageSource from Base.MF01_Batches where ExtractID='"+extractID+"'";
				System.out.println("MessageSource Query in :"+coreMessageSourceQuery);
				ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, coreMessageSourceQuery);
				actualMessageSource = GenericMethods.getICSRetrieveColumnValues(resultSet);//.getICSRetrieveMutipleColumnValues(rsdate,businessDate);		
				System.out.println("Actual MessageSource :"+actualMessageSource);
				boolean returnExpectedReslt = Component.verifyEquals(actualMessageSource, MessageSourceNodeVal, "MessageSource is loaded into database.");
				publishResults(returnExpectedReslt, actualMessageSource, MessageSourceNodeVal, "Core MessageSource is loaded into database.");
			}	
	}
	
	public static void validateCoreMessageDestination(String filePath,String fileName,String dbServerName,String fredDBName,String extractID) throws Exception{
		String coreMessageDestinationQuery;	
		String xpathMessageDestination ="//Core/MessageDestination";
		String actualMessageDestination;
		String messageDestinationNodeVal;
		fileName =fileName+".xml";
		validationStepInformation("******************************************************************************");
		validationStepInformation("*********Validate MF01 Core MessageDestination tag value is loaded into database.***********");
		validationStepInformation("******************************************************************************");
		
		Map<String,String> mf01XPATHExtractID= getXMLNodeValueByXPATH(filePath,fileName,xpathMessageDestination);
			for(int i=0;i<mf01XPATHExtractID.size();i++){
				messageDestinationNodeVal = getNodeListValues(mf01XPATHExtractID,"MessageDestination").get(i);
				System.out.println("MF01 MessageDestination :"+messageDestinationNodeVal);
				coreMessageDestinationQuery="select MessageDestination from Base.MF01_Batches where ExtractID='"+extractID+"'";
				System.out.println("MessageDestination Query in :"+coreMessageDestinationQuery);
				ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, coreMessageDestinationQuery);
				actualMessageDestination = GenericMethods.getICSRetrieveColumnValues(resultSet);//.getICSRetrieveMutipleColumnValues(rsdate,businessDate);		
				System.out.println("Actual MessageDestination :"+actualMessageDestination);
				boolean returnExpectedReslt = Component.verifyEquals(actualMessageDestination, messageDestinationNodeVal, "MessageDestination is loaded into database.");
				publishResults(returnExpectedReslt, actualMessageDestination, messageDestinationNodeVal, "Core MessageDestination is loaded into database.");
			}	
	}
	
	public static void validateCoreRecordCounts(String filePath,String fileName,String dbServerName,String fredDBName,String extractID) throws Exception{
		String coreRecordCountsQuery;	
		String xpathRecordCounts ="//Core/RecordCounts";
		String actualRecordCounts;
		String RecordCountsNodeVal;
		fileName =fileName+".xml";
		
		validationStepInformation("******************************************************************************");
		validationStepInformation("*********Validate MF01 Core RecordCounts tag value is loaded into database.***********");
		validationStepInformation("******************************************************************************");
		
		
		System.out.println("Checking RecordCounts in :"+fileName);
		Map<String,String> mf01XpathRecordCounts= getXMLNodeValueByXPATH(filePath,fileName,xpathRecordCounts);
			for(int i=0;i<mf01XpathRecordCounts.size();i++){
				RecordCountsNodeVal = getNodeListValues(mf01XpathRecordCounts,"RecordCounts").get(i);
				System.out.println("MF01 RecordCounts :"+RecordCountsNodeVal);
				coreRecordCountsQuery="select RecordCounts from Base.MF01_Batches where ExtractID='"+extractID+"'";
				System.out.println("ExtractId Query in :"+coreRecordCountsQuery);
				ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, coreRecordCountsQuery);
				actualRecordCounts = GenericMethods.getICSRetrieveColumnValues(resultSet);//.getICSRetrieveMutipleColumnValues(rsdate,businessDate);		
				System.out.println("Actual RecordCounts :"+actualRecordCounts);
				boolean returnExpectedReslt = Component.verifyEquals(actualRecordCounts, RecordCountsNodeVal, "RecordCounts is loaded into database.");
				publishResults(returnExpectedReslt, actualRecordCounts, RecordCountsNodeVal, "Core RecordCounts is loaded into database.");
			}	
	}
	
	
	
	
	
static String getMF01BatchID(String dbServerName,String fredDBName) throws Exception{
	
	String batchQuery = "select BatchDetailsID from base.MF01_Batches where ExtractID='"+extractID+"'";
	System.out.println("BatchID Query :"+batchQuery);
	ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, batchQuery);
	String actualBatchdetailsId = GenericMethods.getICSRetrieveColumnValues(rs);//.getICSRetrieveMutipleColumnValues(rsdate,businessDate);		
	System.out.println("Actual BatchID Details: "+actualBatchdetailsId);
	return actualBatchdetailsId;
}

public static void validateMF01ItemIDWithDb(String destFilePath,String fileName,String dbServerName,String fredDBName) throws Exception{
	String actualItemIdVal =null;
	String xpathItemId ="//Items/Item/ItemId";
	fileName =fileName+".xml";
	
	validationStepInformation("******************************************************************************");
	validationStepInformation("*********Validate MF01 Items ItemId tag value is loaded into database.***********");
	validationStepInformation("******************************************************************************");
	
	
	
	System.out.println("Checking ItemDetails in :"+fileName);
	Map<String, String> mf01ItemAccount = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);
	for(int i=0;i<mf01ItemAccount.size();i++){
		listItemIDVal = getNodeListValues(mf01ItemAccount,"ItemId").get(i);
		validationStepInformation("*********Validate MF01 Items ItemId tag value :"+listItemIDVal+" is loaded into database.***********");
		String batchInItem =getMF01BatchID(dbServerName,fredDBName);
		System.out.println("BatchItems :"+batchInItem);
		itemIDQuery ="select MessageItemId from Base.MF01_Items where BatchDetailsID='"+getMF01BatchID(dbServerName,fredDBName)+"' and MessageItemID='"+listItemIDVal+"'";
		System.out.println("itemID  Query "+itemIDQuery);		
		ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemIDQuery);		
		actualItemIdVal = GenericMethods.getICSRetrieveMutipleColumnValues(resultSet,listItemIDVal);
		System.out.println("MF01 Item ItemId list values returned "+listItemIDVal);
		System.out.println("MF01 Item ItemId list values returned from sql "+actualItemIdVal);
		boolean returnExpectedReslt = Component.verifyEquals(actualItemIdVal, listItemIDVal, "ItemId of the MF01 file is not loaded into database.");
	System.out.println("Validate ItemId: "+returnExpectedReslt);
	validationStepInformation("Validate ItemId Value");
	publishResults(returnExpectedReslt, actualItemIdVal, listItemIDVal, "ItemId of the MF01 file is loaded into database.");
	}
}



public static void validateMF01ItemImageWithDb(String destFilePath,String fileName,String dbServerName,String fredDBName,String templateFileType,String dbItemImageDataTypeQuery,String dbImageFetchQuery2) throws Exception{
	//Call Image Validation code
	ImageDataValidationWithDatabase.verifyImageDataFromDatabase(dbServerName, fredDBName, dbItemImageDataTypeQuery, dbImageFetchQuery2, destFilePath, fileName, templateFileType);
}
	
public static void validateMF01ItemCodelineReferenceWithDb(String destFilePath,String fileName,String dbServerName,String fredDBName) throws Exception{
	
	String referenceNodeVal =null;
	String actualReferenceVal =null;
	String xpathReference ="//Items/Item/Codeline/Reference";
	String itemQuery = null;
	String listItemIDValue=null;
		ArrayList<String> itemID =getItemIdVal(destFilePath,fileName,dbServerName,fredDBName);
		fileName =fileName+".xml";
		Map<String, String> mf01ItemID = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);
		
//	String itemQuery ="SELECT Reference FROM [Base].[MF01_Items] I JOIN [Base].[MF01_Batches] B ON I.BatchDetailsID=B.BatchDetailsID "+
	//				"WHERE I.MessageItemID in ("+getStringFromArrayList(itemID)+")"+" AND B.ExtractID='"+extractID+"'";
//	itemQuery ="select Reference from Base.MF01_Items where BatchDetailsID='"+getMF01BatchID(dbServerName,fredDBName)+"' and MessageItemID='"+listItemIDVal+"'";
	Map<String, String> mf01ItemReference = getXMLNodeValueByXPATH(destFilePath,fileName,xpathReference);
	for(int i=0;i<mf01ItemReference.size();i++){
		
		listItemIDValue = getNodeListValues(mf01ItemID,"ItemId").get(i);
		itemQuery ="SELECT Reference FROM [Base].[MF01_Items] I JOIN [Base].[MF01_Batches] B ON I.BatchDetailsID=B.BatchDetailsID "+
				"WHERE I.MessageItemID in ('"+listItemIDValue+"')"+" AND B.ExtractID='"+extractID+"'";
		System.out.println("itemID Reference Query "+itemQuery);
		validationStepInformation("***********Validation of Reference Tag Value for itemID  "+listItemIDValue+" is loaded into database***********");
		
		referenceNodeVal = getNodeListValues(mf01ItemReference,"Reference").get(i);
		ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemQuery);		
		actualReferenceVal = GenericMethods.getICSRetrieveMutipleColumnValues(resultSet,referenceNodeVal);
		System.out.println("MF01 Item Reference list values returned "+referenceNodeVal);
		System.out.println("MF01 Item Reference list values returned from sql "+actualReferenceVal);
		boolean returnExpectedReslt = Component.verifyEquals(actualReferenceVal, referenceNodeVal, "Reference of the MF01 file is not loaded into database.");
	System.out.println("Validate Reference: "+returnExpectedReslt);
	//validationStepInformation("Validate Reference Value");
	publishResults(returnExpectedReslt, actualReferenceVal, referenceNodeVal, "Reference of the MF01 file is loaded into database.");
	}
}

public static void validateMF01ItemCodelineTrancodeWithDb(String destFilePath,String fileName,String dbServerName,String fredDBName) throws Exception{
	ArrayList<String> returnVal = new ArrayList<>();
	String trancodeNodeVal =null;
	String actualTrancodeVal =null;
	String xpathTrancode ="//Items/Item/Codeline/Trancode";
	String itemQuery = null;
	String listItemIDValue=null;
	fileName =fileName+".xml";
	
	Map<String, String> mf01ItemID = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);	
	
	//itemQuery ="select Trancode from Base.MF01_Items where BatchDetailsID='"+getMF01BatchID(dbServerName,fredDBName)+"' and MessageItemID='"+listItemIDVal+"'";
	//System.out.println("itemID Trancode Query "+itemQuery);
	
	Map<String, String> mf01ItemTrancode = getXMLNodeValueByXPATH(destFilePath,fileName,xpathTrancode);
	for(int i=0;i<mf01ItemTrancode.size();i++){
		listItemIDValue = getNodeListValues(mf01ItemID,"ItemId").get(i);
		validationStepInformation("***********Validation of Trancode Tag Value for itemID  "+listItemIDValue+" is loaded into database***********");
		itemQuery ="SELECT Reference FROM [Base].[MF01_Items] I JOIN [Base].[MF01_Batches] B ON I.BatchDetailsID=B.BatchDetailsID "+
				"WHERE I.MessageItemID in ('"+listItemIDValue+"')"+" AND B.ExtractID='"+extractID+"'";
	
		trancodeNodeVal = getNodeListValues(mf01ItemTrancode,"Trancode").get(i);
		ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemQuery);		
		actualTrancodeVal = GenericMethods.getICSRetrieveMutipleColumnValues(resultSet,trancodeNodeVal);
		System.out.println("MF01 Item Trancode list values returned "+trancodeNodeVal);
		System.out.println("MF01 Item Trancode list values returned from sql "+actualTrancodeVal);
		boolean returnExpectedReslt = Component.verifyEquals(actualTrancodeVal, trancodeNodeVal, "Trancode of the MF01 file is not loaded into database.");
	System.out.println("Validate Trancode: "+returnExpectedReslt);
	validationStepInformation("Validate Trancode Value");
	publishResults(returnExpectedReslt, actualTrancodeVal, trancodeNodeVal, "Trancode of the MF01 file is loaded into database.");
	}
}

public static void validateMF01ItemCodelineSerialWithDb(String destFilePath,String fileName,String dbServerName,String fredDBName) throws Exception{
	ArrayList<String> returnVal = new ArrayList<>();
	String serialNodeVal =null;
	String actualSerialVal =null;
	String xpathSerial ="//Items/Item/Codeline/Serial";
	String itemQuery = null;
	fileName =fileName+".xml";
	
	String listItemIDValue=null;
	Map<String, String> mf01ItemID = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);
		
	//itemQuery ="select Serial from Base.MF01_Items where BatchDetailsID='"+getMF01BatchID(dbServerName,fredDBName)+"' and MessageItemID='"+listItemIDVal+"'";
	//System.out.println("itemID Serial Query "+itemQuery);
	validationStepInformation("***********Validation of Serial Tag Value for itemID  "+listItemIDVal+" is loaded into database***********");
	Map<String, String> mf01ItemAccount = getXMLNodeValueByXPATH(destFilePath,fileName,xpathSerial);
	for(int i=0;i<mf01ItemAccount.size();i++){
		listItemIDValue = getNodeListValues(mf01ItemID,"ItemId").get(i);
		itemQuery ="SELECT Serial FROM [Base].[MF01_Items] I JOIN [Base].[MF01_Batches] B ON I.BatchDetailsID=B.BatchDetailsID "+
				"WHERE I.MessageItemID in ('"+listItemIDValue+"')"+" AND B.ExtractID='"+extractID+"'";

		serialNodeVal = getNodeListValues(mf01ItemAccount,"Serial").get(i);
		ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemQuery);		
		actualSerialVal = GenericMethods.getICSRetrieveMutipleColumnValues(resultSet,serialNodeVal);
		System.out.println("MF01 Item Serial list values returned "+serialNodeVal);
		System.out.println("MF01 Item Serial list values returned from sql "+actualSerialVal);
		boolean returnExpectedReslt = Component.verifyEquals(actualSerialVal, serialNodeVal, "Serial of the MF01 file is not loaded into database.");
	System.out.println("Validate Serial: "+returnExpectedReslt);
	//validationStepInformation("Validate Serial Value");
	publishResults(returnExpectedReslt, actualSerialVal, serialNodeVal, "Serial of the MF01 file is loaded into database.");
	}
}

public static void validateMF01ItemCodelineSortCodeWithDb(String destFilePath,String fileName,String dbServerName,String fredDBName) throws Exception{
	ArrayList<String> returnVal = new ArrayList<>();
	String sortCodeNodeVal =null;
	String actualSortCodeVal =null;
	String xpathSortCode ="//Items/Item/Codeline/SortCode";
	String itemSCQuery = null;
	fileName =fileName+".xml";
	String listItemIDValue=null;
	Map<String, String> mf01ItemID = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);
	//itemSCQuery ="select SortCode from Base.MF01_Items where BatchDetailsID='"+getMF01BatchID(dbServerName,fredDBName)+"' and MessageItemID='"+listItemIDVal+"'";
	//System.out.println("itemID SortCode Query "+itemSCQuery);
	
	Map<String, String> mf01ItemSC = getXMLNodeValueByXPATH(destFilePath,fileName,xpathSortCode);
	for(int i=0;i<mf01ItemSC.size();i++){
		
		listItemIDValue = getNodeListValues(mf01ItemID,"ItemId").get(i);
		validationStepInformation("***********Validation of SortCode Tag Value for itemID  "+listItemIDValue+" is loaded into database***********");
		itemSCQuery ="SELECT SortCode FROM [Base].[MF01_Items] I JOIN [Base].[MF01_Batches] B ON I.BatchDetailsID=B.BatchDetailsID "+
				"WHERE I.MessageItemID in ('"+listItemIDValue+"')"+" AND B.ExtractID='"+extractID+"'";

		sortCodeNodeVal = getNodeListValues(mf01ItemSC,"SortCode").get(i);
		ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemSCQuery);		
		actualSortCodeVal = GenericMethods.getICSRetrieveMutipleColumnValues(resultSet,sortCodeNodeVal);
		System.out.println("MF01 Item SortCode list values returned "+sortCodeNodeVal);
		System.out.println("MF01 Item SortCode list values returned from sql "+actualSortCodeVal);
		boolean returnExpectedReslt = Component.verifyEquals(actualSortCodeVal, sortCodeNodeVal, "SortCode of the MF01 file is not loaded into database.");
	System.out.println("Validate SortCode: "+returnExpectedReslt);
	validationStepInformation("Validate SortCode Value");
	publishResults(returnExpectedReslt, actualSortCodeVal, sortCodeNodeVal, "SortCode of the MF01 file is loaded into database.");

	}
}

public static void validateMF01ItemCodelineAccountWithDb(String destFilePath,String fileName,String dbServerName,String fredDBName) throws Exception{
	ArrayList<String> returnVal = new ArrayList<>();
	String accountNodeVal =null;
	String actualAccountVal =null;
	String xpathAccount ="//Items/Item/Codeline/Account";
	String itemAccountQuery = null;
	fileName =fileName+".xml";
	String listItemIdValue=null;
	Map<String, String> mf01ItemID = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);
	
	/*itemAccountQuery ="select Account from Base.MF01_Items where BatchDetailsID='"+getMF01BatchID(dbServerName,fredDBName)+"' and MessageItemID='"+listItemIDVal+"'";
	System.out.println("itemID Currency Query "+itemAccountQuery);
	*/
	Map<String, String> mf01ItemAccount = getXMLNodeValueByXPATH(destFilePath,fileName,xpathAccount);
	for(int i=0;i<mf01ItemAccount.size();i++){
		
		listItemIdValue = getNodeListValues(mf01ItemID,"ItemId").get(i);
		validationStepInformation("***********Validation of Codeline Tag Value for itemID  "+listItemIdValue+" is loaded into database***********");
		itemAccountQuery ="SELECT Account FROM [Base].[MF01_Items] I JOIN [Base].[MF01_Batches] B ON I.BatchDetailsID=B.BatchDetailsID "+
				"WHERE I.MessageItemID in ('"+listItemIdValue+"')"+" AND B.ExtractID='"+extractID+"'";

		accountNodeVal = getNodeListValues(mf01ItemAccount,"Account").get(i);
		if(accountNodeVal.startsWith("0")){
			accountNodeVal=accountNodeVal.substring(1, accountNodeVal.length());
		}
		ResultSet rsItemAmt = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemAccountQuery);		
		actualAccountVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemAmt,accountNodeVal);
		System.out.println("MF01 Item Account list values returned "+accountNodeVal);
		System.out.println("MF01 Item Account list values returned from sql "+actualAccountVal);
		boolean returnExpectedReslt = Component.verifyEquals(actualAccountVal, accountNodeVal, "Account of the MF01 file is not loaded into database.");
	System.out.println("Validate Account: "+returnExpectedReslt);
	//validationStepInformation("Validate Account Value");
	publishResults(returnExpectedReslt, actualAccountVal, accountNodeVal, "Account of the MF01 file is loaded into database.");
	}
}

public static void validateMF01ItemCodelineAmountWithDb(String destFilePath,String fileName,String dbServerName,String fredDBName) throws Exception{
	ArrayList<String> returnVal = new ArrayList<>();
	String amountNodeVal =null;
	String actualAmountVal =null;
	String xpathAmount ="//Items/Item/Codeline/Amount";
	String itemAmountQuery = null;
	fileName =fileName+".xml";
	String listItemIDValue=null;
	Map<String, String> mf01ItemID = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);
	//itemAmountQuery ="select Amount from Base.MF01_Items where BatchDetailsID='"+getMF01BatchID(dbServerName,fredDBName)+"' and MessageItemID='"+listItemIDVal+"'";
	//System.out.println("itemID Currency Query "+itemAmountQuery);
	Map<String, String> mf01ItemCurrency = getXMLNodeValueByXPATH(destFilePath,fileName,xpathAmount);
	for(int i=0;i<mf01ItemCurrency.size();i++){
		
		listItemIDValue = getNodeListValues(mf01ItemID,"ItemId").get(i);
		itemAmountQuery ="SELECT Amount FROM [Base].[MF01_Items] I JOIN [Base].[MF01_Batches] B ON I.BatchDetailsID=B.BatchDetailsID "+
				"WHERE I.MessageItemID in ('"+listItemIDValue+"')"+" AND B.ExtractID='"+extractID+"'";
		System.out.println("itemID Currency Query "+itemAmountQuery);
		amountNodeVal = getNodeListValues(mf01ItemCurrency,"Amount").get(i);
		ResultSet rsItemAmt = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemAmountQuery);		
		actualAmountVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemAmt,amountNodeVal);
		System.out.println("MF01 Item Currency list values returned "+amountNodeVal);
		System.out.println("MF01 Item Currency list values returned from sql "+actualAmountVal);
		boolean returnExpectedReslt = Component.verifyEquals(actualAmountVal, amountNodeVal, "Amount of the MF01 file is not loaded into database.");
	System.out.println("Validate Amount: "+returnExpectedReslt);
	validationStepInformation("Validate Amount Value");
	publishResults(returnExpectedReslt, actualAmountVal, amountNodeVal, "Amount of the MF01 file is loaded into database.");
	}
}

public static void validateMF01ItemCodelineCurrencyWithDb(String filePath,String fileName,String dbServerName,String fredDBName) throws Exception{
	ArrayList<String> returnVal = new ArrayList<>();
	String currencyNodeVal =null;
	String actualCurrencyVal =null;
	String xpathCurrency ="//Items/Item/Codeline/Currency";
	fileName =fileName+".xml";
	String listItemIDValue=null;
	Map<String, String> mf01ItemID = getXMLNodeValueByXPATH(filePath,fileName,xpathItemId);
	String itemCurrencyQuery;
	//itemCurrencyQuery ="select Currency from Base.MF01_Items where BatchDetailsID='"+getMF01BatchID(dbServerName,fredDBName)+"' and MessageItemID='"+listItemIDVal+"'";
	
	Map<String, String> mf01ItemCurrency = getXMLNodeValueByXPATH(filePath,fileName,xpathCurrency);
	for(int i=0;i<mf01ItemCurrency.size();i++){
		listItemIDValue = getNodeListValues(mf01ItemID,"ItemId").get(i);
		validationStepInformation("**********Validation of Currency Value for itemID :"+listItemIDValue+" loaded into database******");
		itemCurrencyQuery ="SELECT Currency FROM [Base].[MF01_Items] I JOIN [Base].[MF01_Batches] B ON I.BatchDetailsID=B.BatchDetailsID "+
				"WHERE I.MessageItemID in ('"+listItemIDValue+"')"+" AND B.ExtractID='"+extractID+"'";
		System.out.println("itemID Currency Query "+itemCurrencyQuery);
		currencyNodeVal = getNodeListValues(mf01ItemCurrency,"Currency").get(i);
		ResultSet rsItemCurrency = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemCurrencyQuery);		
		actualCurrencyVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemCurrency,currencyNodeVal);
		System.out.println("MF01 Item Currency list values returned "+currencyNodeVal);
		System.out.println("MF01 Item Currency list values returned from sql "+actualCurrencyVal);
		boolean returnExpectedReslt = Component.verifyEquals(actualCurrencyVal, currencyNodeVal, "ItemId of the MF01 file is not loaded into database.");
	System.out.println("Validate Currency: "+returnExpectedReslt);
	validationStepInformation("Validate Currency Value");
	publishResults(returnExpectedReslt, actualCurrencyVal, currencyNodeVal, "Currency of the MF01 file is loaded into database.");
	}
}


/*public static File getFileStreamWithExtension(String filePath, String fileName, String extension)
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
*/

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

public static String getActualConfigDate(String dbServerName,String fredAutoDb,String sqlConfigBusinessDate) throws Exception{
	ResultSet rsConfigBusinessDate = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, sqlConfigBusinessDate);
	String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(rsConfigBusinessDate);
	String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
	return configDateValue;
}


public static void verifyImageDataLoadedIntoDatabase(String destFilePath,String fileName,String dbServerName,String fredDBName,String dbItemImageDataTypeQuery,String dbImageFetchQuery2,String extractID) throws Exception{ 	

	/*** Image DataType****/
	fileName=fileName+".xml";
	
	 ReadImageValueFromSQLFile readSQLFile = new ReadImageValueFromSQLFile();
	 Map<String,String> mapTagValuesFromFile =readSQLFile.getTagValue(destFilePath, fileName);
		 
	 GenericMethods genericObj = new GenericMethods();
	// Map<String, String> mf01ItemAccount = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);
	 String dbDataLoadedIntoDBQuery = dbImageFetchQuery2+" where ExtractID ='"+extractID+"'";
	 System.out.println("Item Image Column Values of E022 Message is :"+dbDataLoadedIntoDBQuery);
	 ResultSet resultSetColumnValue = getICSDBServerConnection(dbServerName, fredDBName, dbDataLoadedIntoDBQuery);

	 Map<String,String> mapTagValuesFromResultSet =genericObj.getMessageItemIdAndImageValue(resultSetColumnValue);
	 
	 for(Map.Entry<String,String> entry:mapTagValuesFromFile.entrySet()){
		
		 String fileItemId = entry.getKey();
		 String fileImageValue =entry.getValue();
		 
		 validationStepInformation("**********Validation of Image Value for itemID :"+fileItemId+" loaded into database******");
		 
		 String getImageValueFromResultSetByFileItemId = mapTagValuesFromResultSet.get(fileItemId);		
		 System.out.println("fileImageValue :"+fileImageValue+" getImageValueFromResultSetByFileItemId : "+getImageValueFromResultSetByFileItemId);
		// Component.assertEquals(fileImageValue, getImageValueFromResultSetByFileItemId, "Actual and Expected Loaded Image values (05MF01 or 06MF01)of the messages are same.");
		boolean flagImage=Component.verifyEquals(fileImageValue, getImageValueFromResultSetByFileItemId, "Actual and Expected Loaded Image values (05MF01 or 06MF01)of the messages are same.");
		 publishResults(flagImage,fileImageValue,getImageValueFromResultSetByFileItemId,"Actual and expected Image Validation");
		 
	 }
	}


public static ArrayList<String> getItemIdVal(String destFilePath,String fileName,String dbServerName,String fredDBName) throws Exception{
	ArrayList returnVal=new ArrayList<>();
	fileName=fileName+".xml";
	String listItemIDValue = null;
	
	Map<String, String> mf01ItemAccount = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);
	
	for(int i=0;i<mf01ItemAccount.size();i++){
		listItemIDValue = getNodeListValues(mf01ItemAccount,"ItemId").get(i);
		returnVal.add(listItemIDValue);
		System.out.println("listItemIDValue :"+listItemIDValue);
	}
	
	System.out.println("after loop :listItemIDValue :"+listItemIDValue);
	//System.out.println(returnVal);
	return returnVal;
}

public static ArrayList<String> getNodeList(Map<String, String> strNodeVal, String tagName){
	ArrayList<String> listOfString = new ArrayList<String>();
	for(int i=1;i<=strNodeVal.size();i++){
		listOfString.add(strNodeVal.get(tagName+i));
	}
	return listOfString;
}



public static StringBuilder getStringFromArrayList(ArrayList<String> InputStringArray) throws Exception
{	
  StringBuilder inputString= new StringBuilder();
  for(int i=0;i<InputStringArray.size();i++)
  {
  inputString.append("'"+InputStringArray.get(i)+"'");	 
  if(i<InputStringArray.size()-1)
	  inputString.append(",");
  }
  return inputString;

}

public static void verifyExceptionLogX9CorruptImage(String dbServerName,String fredDBName,String templateFileType,String filepath,String destFilePath,String fileName,String sqlTemplateExtractIDToBeReplaced,String sqlTemplateBusinessDateToBeReplaced,String dbItemImageDataTypeQuery,String dbImageFetchQuery2,String sqlConfigBusinessDate,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempFileName,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
	//getItemIdVal(String destFilePath,String fileName,String dbServerName,String fredDBName)
	ArrayList<String> returnVal = new ArrayList<>();
	String fileTobeCreated =fileName+"_Actual";
	String fileTobeCopied =fileName+"_Actual.sql";
	String fileExtension=".sql";
	fileName =fileName+"_Actual.xml";
	copyFileFromOneLocationToAnother(filepath,destFilePath,fileTobeCopied);
	createFileFromTemplateSplit(destFilePath,fileTobeCreated,fileExtension);	
	System.out.println(destFilePath+" filepath :"+filepath);
	System.out.println(fileName);
	String listItemIDValue=null;
	Map<String, String> mf01ItemID = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);
	System.out.println("Verify ErrorLog Table for any error occurs");
	System.out.println("Check if processed processed AIXML is present in ExceptionLog Table");

	SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sssssss");
	Date now = new Date();
	String strDate = sdfDate.format(now);
	System.out.println("strDate " +strDate);
			
	String frontImgExceptionLogQuery="select Message from base.ExceptionLog where ApplicationErrorCode='-20002'";
	validationStepInformation("Validation for X9 Tiff Corrupt Image");
	System.out.println("FrontImage Exception Log Query "+frontImgExceptionLogQuery);
	
	
	ResultSet rsErrLog = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, frontImgExceptionLogQuery);
	System.out.println("X9 item id size "+mf01ItemID.size());
	for(int i=0;i<mf01ItemID.size();i++){		
		listItemIDValue = getNodeListValues(mf01ItemID,"ItemId").get(i);
		validationStepInformation("**********Validation of Image value for itemID :"+listItemIDValue+" loaded into database******");
		System.out.println("X9 item id size "+mf01ItemID.size());
//	String actualErrLog = GenericMethods.getICSRetrieveErrLog(rsErrLog,itemId.get(0));	
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
	

	
	
}

public static void verifyExceptionLogX9SplitImage(String dbServerName,String fredDBName,String templateFileType,String filepath,String destFilePath,String fileName,String sqlTemplateExtractIDToBeReplaced,String sqlTemplateBusinessDateToBeReplaced,String dbItemImageDataTypeQuery,String dbImageFetchQuery2,String sqlConfigBusinessDate,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempFileName,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
	//getItemIdVal(String destFilePath,String fileName,String dbServerName,String fredDBName)
		ArrayList<String> returnVal = new ArrayList<>();
		String fileTobeCreated =fileName+"_Actual";
		String fileTobeCopied =fileName+"_Actual.sql";
		String fileExtension=".sql";
		fileName =fileName+"_Actual.xml";
		copyFileFromOneLocationToAnother(filepath,destFilePath,fileTobeCopied);
		createFileFromTemplateSplit(destFilePath,fileTobeCreated,fileExtension);	
		System.out.println(destFilePath+" filepath :"+filepath);
		System.out.println(fileName);
		String listItemIDValue=null;
		Map<String, String> mf01ItemID = getXMLNodeValueByXPATH(destFilePath,fileName,xpathItemId);
		System.out.println("Verify ErrorLog Table for any error occurs");
		System.out.println("Check if processed processed AIXML is present in ExceptionLog Table");

		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sssssss");
		Date now = new Date();
		String strDate = sdfDate.format(now);
		System.out.println("strDate " +strDate);
				
		String frontImgExceptionLogQuery="select Message from base.ExceptionLog where ApplicationErrorCode='-20002'";
		validationStepInformation("Validation for X9 Tiff Split Image");
		System.out.println("FrontImage Exception Log Query "+frontImgExceptionLogQuery);
		
		
		ResultSet rsErrLog = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, frontImgExceptionLogQuery);
		System.out.println("X9 item id size "+mf01ItemID.size());
		for(int i=0;i<mf01ItemID.size();i++){		
			listItemIDValue = getNodeListValues(mf01ItemID,"ItemId").get(i);
			validationStepInformation("**********Validation of Image value for itemID :"+listItemIDValue+" loaded into database******");
			System.out.println("X9 item id size "+mf01ItemID.size());
//		String actualErrLog = GenericMethods.getICSRetrieveErrLog(rsErrLog,itemId.get(0));	
			String actualErrLog = GenericMethods.getICSRetrieveColumnValues(rsErrLog);
		System.out.println("Error Log found for  in ExceptionLog Table :"+actualErrLog);
		boolean returnExpectedReslt = Component.verifyTrue(!(actualErrLog.length()==0), "ErrorLog is not present in database.");
		if(!(actualErrLog.length()==0))
		{
		
			publishResults(returnExpectedReslt, actualErrLog, actualErrLog, actualErrLog+" Exception Log is present for  aixml file loaded into database");
		}
		else 
			{
			
			publishResults(actualErrLog.length()==0, (actualErrLog.length()==0)?"Exception log is not present in database.":"Issue Found!!","ErrorLog is not present in database.","Exception log is not present in database.");
		}
		}
}
		
}

