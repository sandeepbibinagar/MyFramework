package com.ics.tests.fred;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.fred.testScenarios.AIXMLFileNameUniqueValidation;
import com.ics.fred.testScenarios.E022MessageLoadedIntoFREDDatabase;
import com.ics.fred.testScenarios.FREDCIMF01BusinessDateValidation;
import com.ics.fred.testScenarios.FREDCIX9ExtractIDValidation;
import com.ics.fred.testScenarios.FREDDIMF01BusinessDateValidation;
import com.ics.fred.testScenarios.FREDDIX9ExtractIDValidation;
import com.ics.fred.testScenarios.ImageDataValidationWithDatabase;
import com.ics.fred.testScenarios.MultiFileInsertionCreditDebitItem;
import com.ics.fred.testScenarios.SingleFileInsertionCreditItem;
import com.ics.fred.testScenarios.ValidateAIXMLFileDataLoadedIntoDatabase;
import com.ics.fred.testScenarios.ValidateE031ICNCoreSubTags;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

	public class Sprint3_2 extends GenericMethodUtilis{
		private static String sqlCreditFileType = ICSPropertiesConfig.getSqlFileTypeAsCredit();
		private static String sqlDebitFileType = ICSPropertiesConfig.getSqlFileTypeAsDebit();
		private static String templateFilePath = ICSPropertiesConfig.getsqlTemplateFilePath();
		private static String creditX9FilePath = ICSPropertiesConfig.getcreditX9FilePath();
		private static String debitX9FilePath = ICSPropertiesConfig.getdebitX9FilePath();
		private static String dbConfigBusinessDateSQL = ICSPropertiesConfig.getDBConfigBusinessDateSQL();
		private static String dbServerName = ICSPropertiesConfig.getFREDDBServerName();
		private static String fredDatabaseName = ICSPropertiesConfig.getFREDDatabaseName();
		private static String fredBuisnessDateDataType = ICSPropertiesConfig.getFREDBuisnessDateDataType();
		private static String fredBusinessDateValidationWithBatchMF01 = ICSPropertiesConfig.getFREDBusinessDateValidationWithBatchMF01();
		private static String fredBusinessDateValidationWithDBInErrLog = ICSPropertiesConfig.getFREDBusinessDateValidationWithDBInErrLog();
		private static String sqlE022ExtractDataFromDB = ICSPropertiesConfig.getFREDE022MessageDataExtractionFromDB();
		//private static String strBatchDetailsID = ICSPropertiesConfig.getFREDE022MessageDataExtractionFromDBForBatchDetailsID();
		//private static String strBusinessDate = ICSPropertiesConfig.getFREDE022MessageDataExtractionFromDBForBusinessDate();
		//private static String strExtractID = ICSPropertiesConfig.getFREDE022MessageDataExtractionFromDBForExtractID();
		//private static String strProcessingParticipantId = ICSPropertiesConfig.getFREDE022MessageDataExtractionFromDBForParticipantId();
		//private static String strExtMessageType = ICSPropertiesConfig.getFREDE022MessageDataExtractionFromDBforExtMessageType();
		//private static String strIntMessageType = ICSPropertiesConfig.getFREDE022MessageDataExtractionFromDBForIntMessgaeType();
		private static String strImageDataTypeValidation = ICSPropertiesConfig.getFREDImageDataTypeValidation();
		private static String strImageDataExtractionFromDB = ICSPropertiesConfig.getFREDImageDataExtractionFromDB();
		private static String fredExtractIDValidationWithBatchMF01 = ICSPropertiesConfig.getSQLExtractIDValueFromBatchMF01Key();
		private static String strX9FileCreatedQueryValidationFromDatabase= ICSPropertiesConfig.getX9FileCreatedQueryValidationKey();
		private static String strICNFilePath =ICSPropertiesConfig.getICNFilePathKey();
		//private static String strAIXMLRejectFldrPathKey =ICSPropertiesConfig.getAIXMLRejectFldrPathKey();
		//private static String strAIXMLArchiveFldrPathKey =ICSPropertiesConfig.getAIXMLArchiveFldrPathKey();
		private static String aixmlTemplateFileName = "74431_AIXML_OC";
		private static String aixmlTempFileName = "7443_AIXML_OC";
		private static String strAIXMLRejectFldrPathKey =ICSPropertiesConfig.getAIXMLRejectFldrPathKey();
		private static String strAIXMLArchiveFldrPathKey =ICSPropertiesConfig.getAIXMLArchiveFldrPathKey();
		private static String strAIXMLFilePath =ICSPropertiesConfig.getaixmlFilePthKey();
		private static String mf01SendQueueQuery =ICSPropertiesConfig.getMF01SendQueueQueryKey();
		
		
		private static String sqlCreditTemplateFileName = "TC_MSG05_CR";
		private static String sqlDebitTemplateFileName = "TC_62948_DR";
		private static String sqlTemplateExtractIDToBeReplaced = "STRINGTOBEREPLACEDININPUTFILE";
		private static String sqlTemplateBusinessDateToBeReplaced = "BUSINESSDATETOBEREPLACE";
		
			
		//*********** TC-78180 and TC-77830 Variable declaration ************//
		
		private static String sqlBusinessDateCreditTemplateFileName = "TC_78180_05MF01_BuisnessDateValidation";
		private static String sqlBusinessDateDebitTemplateFileName = "TC_77830_06MF01_BuisnessDateValidation";
		private static String sqlBusinessDateSecondDebitTemplateFileName = "TC_77830_06MF01_BuisnessDateValidationIncorrect";
		private static String sqlBusinessDateSecondCreditTemplateFileName = "TC_77830_05MF01_BuisnessDateValidationIncorrect";
		
			
		//********* TC-78183 ExtarctID validation Variable Declaration *******//
		
		private static String sqlCreditExtractIDSameValueTemplateFileName = "TC_05MF01_ExtractID";
		private static String sqlCreditExtractIDSecSameValueTemplateFileName = "TC_05MF01_SameExtractIDValues";
		private static String sqlDebitExtractIDSameValueTemplateFileName = "TC_06MF01_ExtractID";
		private static String sqlDebitExtractIDSecSameValueTemplateFileName = "TC_06MF01_SameExtractIDValues";		
		private static String strICNXMLFileName="ICNOutput.xml";
		
		//************* SQL Command Execution ****************//
		
		private static String sqlFileInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-32-V\\SQL001 -d FREDPIT -i "+
				templateFilePath + sqlCreditTemplateFileName + "_Actual.sql ";
		
		private static String sqlFileMultipleInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-32-V\\SQL001 -d FREDPIT -i "+
				templateFilePath + sqlCreditTemplateFileName + "_Actual.sql "+
						templateFilePath + sqlDebitTemplateFileName + "_Actual.sql";		
		
		
		//String a = getFredResourceFile().getString("sqlFileTypeAsCredit");
		
	   //********** TestCase  : TC_62948_Single -Inject single sql file and verify X9 file is created ***********/
		   //********** TestCase  : TC_62948_Single -Inject single sql file and verify X9 file is created ***********/
				@Test(priority=1)
				public void test_case_62948_Single() throws Exception
				{
					SingleFileInsertionCreditItem.generateAndVerifyX9FileName(dbServerName, fredDatabaseName, dbConfigBusinessDateSQL, sqlCreditFileType, sqlCreditTemplateFileName, templateFilePath,
							sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced,creditX9FilePath );
					
				}
				
				@Test(priority=1)
				public void test_case_62948_SingleDebit() throws Exception
				{
					SingleFileInsertionCreditItem.generateAndVerifyX9FileName(dbServerName, fredDatabaseName, dbConfigBusinessDateSQL, sqlDebitFileType, sqlDebitTemplateFileName, templateFilePath,
							sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced, debitX9FilePath );
					
				}
			
	
		
		@Test // (priority=6)
		public void test_case_744312() throws Exception
		{
			ValidateAIXMLFileDataLoadedIntoDatabase.validateAIXMLDataLoadedeIntoDB(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
		}
		
		//********** TestCase 3 : TC_77830 -DI BusinessDate Validation Testing ***********//
				
			
				
				//********** TestCase 4 : TC_78180 -CI BusinessDate Validation Testing ***********//
				@Test (priority=8)
				public void test_case_78180() throws Exception
				{
				    FREDCIMF01BusinessDateValidation.validAndInvalidCIBusinessDateValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
							fredBusinessDateValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlCreditFileType,
							sqlBusinessDateCreditTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
							sqlTemplateBusinessDateToBeReplaced,sqlBusinessDateSecondCreditTemplateFileName);
				}
				
				//********** TestCase 5 : TC_58566 -LoadedData Extraction from DB ***********//		
			   @Test (priority=9)			
				public void test_case_58566() throws Exception
					{
					E022MessageLoadedIntoFREDDatabase.validateE022MessageLoadedIntoFredDB(dbServerName,fredDatabaseName,sqlE022ExtractDataFromDB,sqlCreditFileType,
							templateFilePath,sqlCreditTemplateFileName);
					}
				
					
				@Test(priority=10)
				public void test_case_78183() throws Exception
				{
					 
					String sqlCreditExtractIDLenIncorrectValTemplateFileName = null;
					String sqlCreditExtractIDLenIncorrectValTemplateFileName1=null;
					FREDCIX9ExtractIDValidation.validAndInvalidCIExtractIDValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
							fredExtractIDValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlCreditFileType,
							sqlCreditTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
							sqlTemplateBusinessDateToBeReplaced,sqlCreditExtractIDSameValueTemplateFileName,strX9FileCreatedQueryValidationFromDatabase,mf01SendQueueQuery,
							sqlCreditExtractIDLenIncorrectValTemplateFileName,sqlCreditExtractIDLenIncorrectValTemplateFileName1);
				   				
				}
				
				//********** TestCase 8 : TC_77326-FRED-DI-ExtractID Length validation with DB ***********//
				
				@Test(priority=11)		
				public void test_case_77326() throws Exception
				{
					 
					FREDDIX9ExtractIDValidation.validAndInvalidDIExtractIDValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
						fredExtractIDValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlDebitFileType,
						sqlDebitTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
						sqlTemplateBusinessDateToBeReplaced,sqlDebitExtractIDSameValueTemplateFileName,strX9FileCreatedQueryValidationFromDatabase);   				
				}
				
}