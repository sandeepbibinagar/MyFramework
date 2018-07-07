
	package com.ics.tests.fred;

	 import java.util.ArrayList;
	import org.testng.annotations.Test;
	import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
	import com.ics.fred.testScenarios.AIXMLFileNameUniqueValidation;
import com.ics.fred.testScenarios.AIXMLServiceStartValidation;
import com.ics.fred.testScenarios.AIXMLTestDataCreditDebitItems;
import com.ics.fred.testScenarios.AIXMLTestDataCreditDebitItemsInclearing;
import com.ics.fred.testScenarios.AIXMLTestDataPreparationScripting;
import com.ics.fred.testScenarios.AIXMLTestDataPreprationForOneItem;
import com.ics.fred.testScenarios.FREDCIMF01BusinessDateValidation;
	import com.ics.fred.testScenarios.FREDCIX9ExtractIDValidation;
	import com.ics.fred.testScenarios.FREDDIMF01BusinessDateValidation;
	import com.ics.fred.testScenarios.SingleFileInsertionCreditItem;
	import com.ics.fred.testScenarios.ValidateAIXMLExtractFileLoadedIntoDatabase;
import com.ics.fred.testScenarios.ValidateAIXMLMutipleItemsCreditDebitOutclearing;
import com.ics.fred.testScenarios.ValidateErrLogScenariosDebit;
	import com.ics.fred.testScenarios.ValidateMF01DataLoadedIntoDatabase;
import com.ics.fred.testScenarios.ValidateOutclearingICNHistory;
import com.ics.fred.testScenarios.Outclearing.ValidateISOCustomFieldDeferredFlag;
import com.ics.seleniumCoreUtilis.Component;
	import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

	public class Regression_Sprint5_2 extends ICSAutomationCommonSetup{
					
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
			private static String strAIXMLRejectFldrPathKey =ICSPropertiesConfig.getAIXMLRejectFldrPathKey();
			private static String strAIXMLArchiveFldrPathKey =ICSPropertiesConfig.getAIXMLArchiveFldrPathKey();
			private static String strAIXMLFilePath =ICSPropertiesConfig.getaixmlFilePthKey();
			private static String mf01SendQueueQuery =ICSPropertiesConfig.getMF01SendQueueQueryKey();
			private static String fredInputTestDataFile =ICSPropertiesConfig.getFREDTestDataInputFileKey();
			private static String fredAIXMLTempFolderPath =ICSPropertiesConfig.getFREDAIXMLTempFolderPathKey();
			private static String aixmlPerfFldrPath="\\\\129.227.33.35\\f$\\AutomationTestData\\AIXMLTemplate\\AIXMLPerf\\";
			static String destActualFilePath="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\MF01InputFolder\\";
			public static String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
			public static String ICNFileName="ICNOutput.xml";
			public static String aixmlTestDataExcelFilepath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\XMLConvertedToExcel.xlsx";
			private static String aixmlTempFileName = "DI_MO_92548";
			
			
			private static String sqlCreditTemplateFileName = "TC_MSG05_CR";
			private static String sqlDebitTemplateFileName = "TC_62948_DR";
			
			//*********** TC-78180 and TC-77830 Variable declaration ************//
			
				
			//********* TC-78183 ExtarctID validation Variable Declaration *******//
			
			public static String sqlCreditExtractIDLenIncorrectValTemplateFileName1="TC_05MF01_ExtractIDLenValidation1";
			
			private static String strICNXMLFileName="ICNOutput";
			
			//************* SQL Command Execution ****************//
			
			@Test(priority =1)
			public void validateAIXMLFile() throws Exception
			{
				ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}		
			
			@Test(priority=2)
			public void tc80624EntityStateValidation() throws Exception{
				ValidateAIXMLExtractFileLoadedIntoDatabase.getEntityStateValFromICN(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
				
			}	
			
			@Test(priority=3)
			public void aixmlServiceStartValidation() throws Exception{
			
				AIXMLServiceStartValidation.validateAIXMLSerice(dbServerName,fredDatabaseName);			
			}
			
			@Test(priority=4)
			public void aixmlTestDataFileCreated() throws Exception{
				String aixmlItemsTempFileName="DI_MO_Items";
				//AIXMLTestDataPreprationForOneItem.readExcelAIXML(aixmlItemsTempFileName,fredAIXMLTempFolderPath,dbServerName,fredDatabaseName,aixmlPerfFldrPath);
				AIXMLTestDataPreparationScripting.readExcelAIXML(aixmlTestDataExcelFilepath,aixmlItemsTempFileName,fredAIXMLTempFolderPath,dbServerName,fredDatabaseName,aixmlPerfFldrPath);
			}
			
			//String a = getFredResourceFile().getString("sqlFileTypeAsCredit");
			

			@Test(priority=23)
			public static void tc69331ValidateOutClearingICN_NoItemDetails() throws Exception{
				
				String aixmlFileWithNoHistory="AIXMLExtract_OC_Mobile";
				ValidateOutclearingICNHistory.validateOutClearingICNWithIDS(excelFilePath,dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, 
						strICNFilePath, aixmlFileWithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
			}
			
			@Test (priority =24)
			public void validateOutclearingAIXMLFile() throws Exception
			{
				ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}
			
			@Test
			public  void testDataFileCreatedInclearing() throws Exception{
			//String aixmlItemsTempFileName="BigFile_imagesactual_IOC5";
			String aixmlItemsTempFileName="AIXML_TransactionIdOneItemId";
			
			AIXMLTestDataCreditDebitItems.generate3000ItemsAixml(aixmlItemsTempFileName,fredAIXMLTempFolderPath,dbServerName,fredDatabaseName,aixmlPerfFldrPath);
			
			}
			
			
			@Test
			public void testDataFileCreatedInclearingInclearing() throws Exception{
			//String aixmlItemsTempFileName="BigFile_imagesactual_IOC5"; AIXML_3000_IN_Test
		//	String aixmlItemsTempFileName="AIXML_3000_IN_Test";
				String aixmlItemsTempFileName="AIXMLExtract_8_0008_20170612_A";
			for(int i=1;i<=1;i++){
			AIXMLTestDataCreditDebitItemsInclearing.generate3000ItemsAixml(aixmlItemsTempFileName,fredAIXMLTempFolderPath,dbServerName,fredDatabaseName,aixmlPerfFldrPath,i);
			//Thread.sleep(2000);
			}
			}
			
			@Test
			public void testDataFileCreatedInclearingImageReplace() throws Exception{
			//String aixmlItemsTempFileName="BigFile_imagesactual_IOC5";
			String aixmlItemsTempFileName="AIXML_TransactionIdOneItemId.xml";
			
			AIXMLTestDataCreditDebitItems.replaceImageValueInAIXML(aixmlItemsTempFileName,fredAIXMLTempFolderPath,dbServerName,fredDatabaseName,aixmlPerfFldrPath);
			
			}
			
			@Test
			public void testcase80666validateItemTypeValInISO() throws Exception{
			//String aixmlItemsTempFileName="BigFile_imagesactual_IOC5";
			String aixmlBulkFilePath="\\\\129.227.33.35\\f$\\AutomationTestData\\OnusItemType\\LBG\\AIXMLExtract_Bulk_Test.xml";
			String icnfilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\OnusItemType\\LBG\\ICNOutputFileAIXMLBulkTest.xml";
		//	 ValidateISOCustomFieldDeferredFlag.getCustomFieldCashItemSrcId8001(aixmlBulkFilePath);
			ValidateAIXMLMutipleItemsCreditDebitOutclearing.getCashItemTagValFromXML(aixmlBulkFilePath,icnfilepath);
			}
			
		}