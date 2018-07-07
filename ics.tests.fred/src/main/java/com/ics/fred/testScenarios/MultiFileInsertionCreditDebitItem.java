package com.ics.fred.testScenarios;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class MultiFileInsertionCreditDebitItem extends ICSDBUtilis  {

	public static void generateAndVerifyX9FileNameMultiItem(String dbServerName, String fredAutoDb, String sqlConfigBusinessDate, 
			String templateCrFileType, String templateDbFileType, String crTemplateFileName, 
			String drTemplateFileName, String templateFilePath,	String templateExtractIDToBeReplaced, 
			String crX9FilePath, String dbX9FilePath, String templateBusinessDateToBeReplaced) throws Exception{
		
		 String sqlFileMultipleInjectCommand = "cmd /c sqlcmd -S "+dbServerName+" -d "+fredAutoDb+" -i "+
					templateFilePath + crTemplateFileName + "_Actual.sql "+
							templateFilePath + drTemplateFileName + "_Actual.sql";
			
		//create unique Extract id for Credit and Debit files
		String crExtractID =  generateExtractId(templateCrFileType);
		String dbExtractID =  generateExtractId(templateDbFileType);
		System.out.println("Extract Id for Credit item: " + crExtractID);
		System.out.println("Extract Id for Debit item: " + dbExtractID);
		
		//Update Extract Id in the credit and debit template .sql file
		
		createFileFromTemplate(templateFilePath, crTemplateFileName, ".sql", templateExtractIDToBeReplaced, crExtractID);
		createFileFromTemplate(templateFilePath, drTemplateFileName, ".sql", templateExtractIDToBeReplaced, dbExtractID);
		
		String crX9fileName = crExtractID+"-CR.icl";
		String dbX9fileName = dbExtractID+"-DR.icl";
		
		//Update Business Date in the credit and debit template .sql files
		ResultSet resultSetConfigDateValue = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, sqlConfigBusinessDate);
		String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(resultSetConfigDateValue);
		String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
		
		createFileFromTemplate(templateFilePath, crTemplateFileName+"_Actual", ".sql", templateBusinessDateToBeReplaced, configDateValue);
		createFileFromTemplate(templateFilePath, drTemplateFileName+"_Actual", ".sql", templateBusinessDateToBeReplaced, configDateValue);
		
		//Inject sql file in to DB through Command line
		sqlCommandExecution(sqlFileMultipleInjectCommand);
		System.out.println("SQL command line execution performed");
		Thread.sleep(20000);
		
		//Retrieves file Name
		String crActualfileName=getRequiredFileName(crX9FilePath, crX9fileName);
		System.out.println("X9 file for credit item" + crActualfileName);
	
		Component.assertEquals(crActualfileName, crX9fileName, "X9 File check performed for credit item");
		
		String dbActualfileName = getRequiredFileName(dbX9FilePath, dbX9fileName);
		System.out.println("X9 file for debit item" + dbActualfileName);
		Component.assertEquals(dbActualfileName, dbX9fileName, "X9 File check performed for debit item");
		
		//delete the unique input files created
		GenericMethodUtilis.DeleteFile(templateFilePath, crTemplateFileName+"_Actual", ".sql");
		GenericMethodUtilis.DeleteFile(templateFilePath, drTemplateFileName+"_Actual", ".sql");
	
	}
}
