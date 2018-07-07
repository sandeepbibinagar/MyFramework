package com.ics.fred.testScenarios.Outclearing;


import com.ics.fred.testScenarios.ValidateAllICNTagsWithIDS;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateISOTransactionSetTagsWithVocalinkSpecs extends GenericMethodUtilis{

	
	
	public static  void validateISOTxSetSubmissionTagsWithVocalink(String excelFilePath,String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempfile,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey,String strAIXMLRejectFldrPathKey,String aixmlCopyTempFldrPath) throws Exception{
	
		 aixmlTempfile="AIXMLOriginal3.xml";
		String  icnFileName = "ICNOutput_Image";
//	ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileMovedToArchieveFldr(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempfile, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
	//AIXMLFileNameUniqueValidation.validateAIXMLUniqueFileNameInDatabase(aixmlTempfile,fredAIXMLTempFolderPath,strAIXMLRejectFldrPathKey,strAIXMLArchiveFldrPathKey,strAIXMLFilePath,dbServerName,fredDatabaseName,aixmlCopyTempFldrPath);
		System.out.println("aixmlTempfile "+aixmlTempfile);
//	String icnFileName = ValidateAIXMLExtractFileLoadedIntoDatabase.getICNOutputFileFromDatabase(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempfile, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
//	System.out.println("Validate ISO icnFileName :"+icnFileName);
	validationStepInformation("Before ISO icnFileName :"+icnFileName);
	icnFileName=icnFileName+".xml";
	validationStepInformation("After ISO icnFileName icnFileName :"+icnFileName);
	//CoreICNValidation.validateICNWithIDSSpecification(excelFilePath,strICNFilePath,icnFileName);
	//ICNEntitiesValidation.validateICNEntitiesWithIDS(excelFilePath,strICNFilePath,icnFileName);
	//ICNItemsValidation.validateICNDebitClearingItemWithIDS();
	//ValidateICNCaptureInfoItemNode.validateCaptureInfoItemsWithNoHistory(excelFilePath,strICNFilePath,icnFileName);
	ValidateAllICNTagsWithIDS.validatOutClearingICNAllTags(excelFilePath,strICNFilePath,icnFileName);
	ValidateMS01TagWithISOSpecDoc.validateISOTxSetSubmissionTags(excelFilePath,strICNFilePath,icnFileName);
	}
	
	
	/*public static void main(String args[]) throws Exception{
		ValidateISOTransactionSetTagsWithVocalinkSpecs objISO = new ValidateISOTransactionSetTagsWithVocalinkSpecs();
		objISO.validateISOTxSetSubmissionTagsWithVocalink();
		
	}*/
	
}
