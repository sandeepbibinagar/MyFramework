package com.ics.fred.testScenarios;

import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateOutclearingICNHistory extends GenericMethodUtilis{

	
	
	public static void validateOutClearingICNWithIDS(String excelFilePath,String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempfile,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
	 try{
		boolean flag = ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileMovedToArchieveFldr(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempfile, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
		String icnFileNameHistory2 = ValidateAIXMLExtractFileLoadedIntoDatabase.getICNOutputFileFromDatabase(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempfile, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
		System.out.println("validateOutclearingICNHistoryDetail icnFileName :"+icnFileNameHistory2);
		icnFileNameHistory2=icnFileNameHistory2+".xml";
		//String icnFileNameHistory2="ICNOutput4558.xml";
	//	validationStepInformation("validate OC ICN icnFileName :"+icnFileNameHistory2);
		ICNEntitiesValidation.validateICNEntitiesWithIDS(excelFilePath,strICNFilePath,icnFileNameHistory2);
	//	CaptureInfoJobBlockValidationWithIDS.validateCaptureInfoTags();
		//ValidateAllICNTagsWithIDS.validatICNAllTags();
		ValidateAllICNTagsWithIDS.validatOutClearingICNAllTags(excelFilePath,strICNFilePath,icnFileNameHistory2);
	 }
	 catch(Exception e){
		 System.out.println(e.getMessage());
	 }
	
	
	}
	
	public static void validateOutClearingISOWithIDS(String excelFilePath,String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempfile,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
	
		//Validate OC ISO tag
		
	
	}
	
	
}