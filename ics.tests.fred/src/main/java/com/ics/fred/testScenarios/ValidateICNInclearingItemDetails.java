package com.ics.fred.testScenarios;

import com.ics.fred.testScenarios.ValidateAIXMLExtractFileLoadedIntoDatabase;
import com.ics.fred.testScenarios.ValidateAllICNTagsWithIDS;
import com.ics.fred.testScenarios.Outclearing.ValidateICNCaptureItemJobBatchBlockTagWithIDS;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;


public class ValidateICNInclearingItemDetails extends GenericMethodUtilis{

	
	public static void validate65862ICNHistorydetails(String excelFilePath,String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempfile,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
	
	ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileMovedToArchieveFldr(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempfile, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
	String icnFileName = ValidateAIXMLExtractFileLoadedIntoDatabase.getICNOutputFileFromDatabase(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempfile, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
	System.out.println("65862 validateICNHistorydetails icnFileName :"+icnFileName);
	validationStepInformation("Before validateICNHistorydetails icnFileName :"+icnFileName);
	icnFileName=icnFileName+".xml";
	validationStepInformation("65862 validateICNHistorydetails icnFileName :"+icnFileName);
	CoreICNValidation.validateICNWithIDSSpecification(excelFilePath,strICNFilePath,icnFileName);
	ICNEntitiesValidation.validateICNEntitiesWithIDS(excelFilePath,strICNFilePath,icnFileName);
	//ICNItemsValidation.validateICNDebitClearingItemWithIDS();
	ValidateICNCaptureInfoItemNode.validateCaptureInfoItemsWithNoHistory(excelFilePath,strICNFilePath,icnFileName);
	ValidateAllICNTagsWithIDS.validatICNAllTags(excelFilePath,strICNFilePath,icnFileName);
		
	}
	
	public static void validate65863ICNNoHistorydetails(String excelFilePath,String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlFileWithNoHistory,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
	
	ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileMovedToArchieveFldr(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlFileWithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
	String icnFileNameNoHistory = ValidateAIXMLExtractFileLoadedIntoDatabase.getICNOutputFileFromDatabase(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlFileWithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
	System.out.println("validateICNNoHistorydetails icnFileName :"+icnFileNameNoHistory);
	icnFileNameNoHistory=icnFileNameNoHistory+".xml";
	validationStepInformation("65863 validateICNNoHistorydetails icnFileName :"+icnFileNameNoHistory);
	CoreICNValidation.validateICNWithIDSSpecification(excelFilePath,strICNFilePath,icnFileNameNoHistory);
	ICNEntitiesValidation.validateICNEntitiesWithIDS(excelFilePath,strICNFilePath,icnFileNameNoHistory);
	//ICNItemsValidation.validateICNDebitClearingItemWithIDS();	
	ValidateICNCaptureInfoItemNode.validateCaptureInfoItemsWithNoHistory(excelFilePath,strICNFilePath,icnFileNameNoHistory);
	ValidateAllICNTagsWithIDS.validatICNAllTagsNoHistory(excelFilePath,strICNFilePath,icnFileNameNoHistory);
	
	}
	
	public static void validate80848ICNNoHistoryDetail(String excelFilePath,String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlFile80848WithNoHistory,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
		
//		CoreICNValidation.validateICNWithIDSSpecification(excelFilePath,strICNFilePath,ICNFileName);
		//ICNItemsValidation.validateICNDebitClearingItemWithIDS();
		ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileMovedToArchieveFldr(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlFile80848WithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
		
		String icnFileNameNoHistory1 = ValidateAIXMLExtractFileLoadedIntoDatabase.getICNOutputFileFromDatabase(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlFile80848WithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
		System.out.println("validate80848ICNHistoryDetail icnFileName :"+icnFileNameNoHistory1);
		icnFileNameNoHistory1=icnFileNameNoHistory1+".xml";
		
		validationStepInformation("TC-80848 - Validate ICN file "+icnFileNameNoHistory1+" for No History details :");
		
		//	ICNItemsValidation_FM_Clearing.validateICNClearingItemWithIDS();
		ValidateICNCaptureInfoItemNode.validateCaptureInfoItemsWithNoHistory(excelFilePath,strICNFilePath,icnFileNameNoHistory1);
		//CaptureInfoValidationWithIDS.validateCaptureInfoTags();
		//CoreICNValidation.validateICNWithIDSSpecification(excelFilePath,strICNFilePath,ICNFileName);
	//	ICNEntitiesValidation.validateICNEntitiesWithIDS();
		ICNEntitiesValidation.validateICNEntitiesWithIDS(excelFilePath,strICNFilePath,icnFileNameNoHistory1);
		//CaptureInfoJobBlockValidationWithIDS.validateCaptureInfoTags();
		ValidateICNCaptureItemJobBatchBlockTagWithIDS.validateCaptureInfoTags(excelFilePath,strICNFilePath,icnFileNameNoHistory1);
		ValidateAllICNTagsWithIDS.validatICNAllTagsNoHistory(excelFilePath,strICNFilePath,icnFileNameNoHistory1);
	
	}
	
	public static void validate80849ICNHistoryDetail(String excelFilePath,String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlFile80849WithNoHistory,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
		
		ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileMovedToArchieveFldr(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlFile80849WithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
		String icnFileNameHistory2 = ValidateAIXMLExtractFileLoadedIntoDatabase.getICNOutputFileFromDatabase(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlFile80849WithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
		System.out.println("validate80849ICNHistoryDetail icnFileName :"+icnFileNameHistory2);
		icnFileNameHistory2=icnFileNameHistory2+".xml";
		
		validationStepInformation("TC-80849 - Validate ICN file "+icnFileNameHistory2+" for History details :");
		ValidateICNCaptureInfoItemNode.validateCaptureInfoItemsWithNoHistory(excelFilePath,strICNFilePath,icnFileNameHistory2);
	//	ICNItemsValidation_FM_Clearing.validateICNClearingItemWithIDS();
		CoreICNValidation.validateICNWithIDSSpecification(excelFilePath,strICNFilePath,icnFileNameHistory2);
		ICNItemValidationMF01.validateICNDebitInClearingItemFredToMOWithIDS(excelFilePath,strICNFilePath,icnFileNameHistory2);
		
		
		ICNEntitiesValidation.validateICNEntitiesWithIDS(excelFilePath,strICNFilePath,icnFileNameHistory2);
		//CaptureInfoValidationWithIDS.validateCaptureInfoTags();
		//CoreICNValidation.validateICNWithIDSSpecification(excelFilePath,strICNFilePath,ICNFileName);
	//	ICNEntitiesValidation.validateICNEntitiesWithIDS();
		ValidateICNCaptureItemJobBatchBlockTagWithIDS.validateCaptureInfoTags(excelFilePath,strICNFilePath,icnFileNameHistory2);
		//ValidateAllICNTagsWithIDS.validatICNAllTags();
		ValidateAllICNTagsWithIDS.validatICNAllTags(excelFilePath,strICNFilePath,icnFileNameHistory2);
		
		
		
		
	}
	
		
}
