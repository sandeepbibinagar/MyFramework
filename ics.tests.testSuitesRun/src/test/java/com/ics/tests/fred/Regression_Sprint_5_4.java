package com.ics.tests.fred;
	
import org.testng.annotations.Test;
	import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
	import com.ics.fred.testScenarios.AIXMLFileNameUniqueValidation;
import com.ics.fred.testScenarios.AIXMLServiceStartValidation;
import com.ics.fred.testScenarios.CoreICNValidation;
import com.ics.fred.testScenarios.FREDCIMF01BusinessDateValidation;
	import com.ics.fred.testScenarios.FREDCIX9ExtractIDValidation;
	import com.ics.fred.testScenarios.FREDDIMF01BusinessDateValidation;
import com.ics.fred.testScenarios.ICNItemValidationMF01;
import com.ics.fred.testScenarios.SingleFileInsertionCreditItem;
//import com.ics.fred.testScenarios.Validate65862ICNHistory;
import com.ics.fred.testScenarios.ValidateAIXMLExtractFileLoadedIntoDatabase;
import com.ics.fred.testScenarios.ValidateAIXMLImageICNOutput;
import com.ics.fred.testScenarios.ValidateAIXMLMutipleItemsCreditDebitOutclearing;
import com.ics.fred.testScenarios.ValidateBatchInfoTagInAIXMLDatabase;
import com.ics.fred.testScenarios.ValidateErrLogScenariosDebit;
import com.ics.fred.testScenarios.ValidateICNInclearingItemDetails;
import com.ics.fred.testScenarios.ValidateMF01DataLoadedIntoDatabase;
import com.ics.fred.testScenarios.ValidateOutclearingICNHistory;
import com.ics.fred.testScenarios.XSDICNValidationFM01;
import com.ics.fred.testScenarios.Outclearing.ValidateICNCaptureItemsTagsWithIDS;
import com.ics.fred.testScenarios.Outclearing.ValidateISOTransactionSetTagsWithVocalinkSpecs;

import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

	public class Regression_Sprint_5_4  extends ICSAutomationCommonSetup{
					
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
			private static String sheetName = getExcelSheetName(new Object(){});
		
			static String destActualFilePath="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\MF01InputFolder\\";
			static String aixmlCopyTempFldrPath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\AIXMLTempFolder\\";
			public static String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
			public static String excelMSG01FilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\AIXMLTemplate\\MSG01ICN.xlsx";
			public static String ICNFileName="ICNOutput.xml";
			public static String ICNMF05File="TC_MSG05_CR_Actual.xml";
			public static String icnMF06File="TC_62948_DR_Actual.xml";
			private static String aixmlPerfFldrPath="\\\\129.227.33.35\\f$\\AutomationTestData\\AIXMLTemplate\\AIXMLPerf\\";
			
			private static String aixmlTemplateFileName = "UniqueFileValidation";
			private static String aixmlTempFileName = "DI_MO_92548";
			static String aixmlTempInvalidFileName="InvalidAIXMLFile";
			static String aixmlFileWithNoHistory="AIXMLExtract_OC_Mobile";
			static String aixmlImageFile ="AIXMLOriginal_Cr_Dr_ImageIncorrect";
					
			private static String sqlCreditTemplateFileName = "TC_MSG05_CR";
			private static String sqlDebitTemplateFileName = "TC_62948_DR";
			static String ICNXSDValFile ="ICNXSDValidationMsg";
			private static String sqlTemplateExtractIDToBeReplaced = "STRINGTOBEREPLACEDININPUTFILE";
			private static String sqlTemplateBusinessDateToBeReplaced = "BUSINESSDATETOBEREPLACE";
			
			
			//*********** TC-78180 and TC-77830 Variable declaration ************//
			
			private static String sqlBusinessDateCreditTemplateFileName = "TC_77830_05MF01_BuisnessDateValidation";
			private static String sqlBusinessDateDebitTemplateFileName = "TC_77830_06MF01_BuisnessDateValidation";
			private static String sqlBusinessDateSecondDebitTemplateFileName = "TC_77830_06MF01_BuisnessDateValidationIncorrect";
			private static String sqlBusinessDateSecondCreditTemplateFileName = "TC_77830_05MF01_BuisnessDateValidationIncorrect";
		
				
			//********* TC-78183 ExtarctID validation Variable Declaration *******//
			
			private static String sqlCreditExtractIDSameValueTemplateFileName = "TC_05MF01_ExtractID";
			private static String sqlCreditExtractIDSecSameValueTemplateFileName = "TC_05MF01_SameExtractIDValues";
			private static String sqlCreditExtractIDLenIncorrectValTemplateFileName = "TC_05MF01_ExtractIDLenValidation";
			public static String sqlCreditExtractIDLenIncorrectValTemplateFileName1="TC_05MF01_ExtractIDLenValidation1";
			
			private static String sqlDebitExtractIDSameValueTemplateFileName = "TC_06MF01_ExtractID";
			private static String sqlDebitExtractIDSecSameValueTemplateFileName = "TC_06MF01_SameExtractIDValues";
			private static String sqlDebitExtractIDLenIncorrectValTemplateFileName = "TC_06MF01_ExtractIDLenValidation";
			private static String strICNXMLFileName="ICNOutput";
			
			//************* SQL Command Execution ****************//
			
			private static String sqlFileInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-32-V\\SQL001 -d FREDPIT -i "+
					templateFilePath + sqlCreditTemplateFileName + "_Actual.sql ";
			
			private static String sqlFileMultipleInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-32-V\\SQL001 -d FREDPIT -i "+
					templateFilePath + sqlCreditTemplateFileName + "_Actual.sql "+
							templateFilePath + sqlDebitTemplateFileName + "_Actual.sql";		
			
			
			//String a = getFredResourceFile().getString("sqlFileTypeAsCredit");
			
		   //********** TestCase  : TC_62948_Single -Inject single sql file and verify X9 file is created ***********/
		
			@Test(priority = 1)
			public void singleFileInsertionX9FileGeneration_Credit() throws Exception
			{
				SingleFileInsertionCreditItem.generateAndVerifyX9FileName(dbServerName, fredDatabaseName, dbConfigBusinessDateSQL, sqlCreditFileType, sqlCreditTemplateFileName, templateFilePath,
						sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced,creditX9FilePath );
			}
			
			@Test(priority=2)
			public void singleFileInsertionX9FileGeneration_Debit() throws Exception
			{
				SingleFileInsertionCreditItem.generateAndVerifyX9FileName(dbServerName, fredDatabaseName, dbConfigBusinessDateSQL, sqlDebitFileType, sqlDebitTemplateFileName, templateFilePath,
						sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced, debitX9FilePath );
			}
			
			//********** TestCase 5 : TC_58566 -LoadedData Extraction from DB ***********//		
			@Test (priority=3)			
			public void tc58566_E022DataLoadedIntoDB() throws Exception
			{
				ValidateMF01DataLoadedIntoDatabase.validateMF01DataLoadedIntoDB(dbServerName,fredDatabaseName,sqlCreditFileType,templateFilePath,destActualFilePath,sqlCreditTemplateFileName,sqlTemplateExtractIDToBeReplaced,sqlTemplateBusinessDateToBeReplaced,strImageDataTypeValidation,strImageDataExtractionFromDB,dbConfigBusinessDateSQL);
			}
		
					
			@Test(priority=4)
			public void tc78183_ExtractIDValidation() throws Exception
			{
					 
				FREDCIX9ExtractIDValidation.validAndInvalidCIExtractIDValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
				fredExtractIDValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlCreditFileType,
				sqlDebitTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
				sqlTemplateBusinessDateToBeReplaced,sqlCreditExtractIDSameValueTemplateFileName,strX9FileCreatedQueryValidationFromDatabase,mf01SendQueueQuery,
				sqlCreditExtractIDLenIncorrectValTemplateFileName,sqlCreditExtractIDLenIncorrectValTemplateFileName1);
			   				
			}
							
					
			@Test(priority=5)
			public void tc62945_X9FileValidationE022() throws Exception
			{
						SingleFileInsertionCreditItem.generateAndVerifyX9FileName(dbServerName, fredDatabaseName, dbConfigBusinessDateSQL, sqlDebitFileType,sqlDebitTemplateFileName, templateFilePath,
								sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced,debitX9FilePath );
			}
				
			@Test(priority=6)
			public void tc65841_X9FileValidation05MF01() throws Exception
			{
				SingleFileInsertionCreditItem.generateAndVerifyX9FileName(dbServerName, fredDatabaseName, dbConfigBusinessDateSQL, sqlCreditFileType, sqlCreditTemplateFileName, templateFilePath,
						sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced,creditX9FilePath );
			}		
			
			@Test(priority =7)
			public void aixmlServiceStartValidation() throws Exception{
				
				AIXMLServiceStartValidation.validateAIXMLSerice(dbServerName,fredDatabaseName);			
			}
				
			@Test (priority =8)
			public void validateAIXMLFile() throws Exception
			{
				ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}		
			
			@Test(priority=9)
			public static void tc80624EntityStateValidation() throws Exception{
				ValidateAIXMLExtractFileLoadedIntoDatabase.getEntityStateValFromICN(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
				
			}
			
			@Test(priority=10)
			public static void tc80625EntityStateValidation() throws Exception{
				ValidateAIXMLExtractFileLoadedIntoDatabase.getEntityStateValFromICN(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
				
			}
			
			@Test(priority=11)
			public static void tc65874AIXMLBlockNbrValidation() throws Exception{
				
				ValidateAIXMLExtractFileLoadedIntoDatabase.verifyErrorLog06MF01(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}
			
			//************************* TestCase 11 : TC_77830 -DI BusinessDate Validation Testing ***********//
			@Test(priority=12)
			public void tc77830BusinessDateValidation_DI() throws Exception
			{
			    FREDDIMF01BusinessDateValidation.validAndInvalidDIBusinessDateValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
						fredBusinessDateValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlDebitFileType,
						sqlBusinessDateDebitTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
						sqlTemplateBusinessDateToBeReplaced,sqlBusinessDateSecondDebitTemplateFileName);
			}
			
					
			//********** TestCase 12 : TC_78180 -CI BusinessDate Validation Testing ***********//
			@Test (priority=13)
			public void tc78180BusinessDateValidation_CI() throws Exception
			{
			    FREDCIMF01BusinessDateValidation.validAndInvalidCIBusinessDateValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
						fredBusinessDateValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlCreditFileType,
						sqlBusinessDateCreditTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
						sqlTemplateBusinessDateToBeReplaced,sqlBusinessDateSecondCreditTemplateFileName);
			}
			
			
			//********** TestCase  : TC_65881 -AIXML DuplicateFileName Validation Testing ***********//
			@Test(priority=14)
			public static void tc65881AIXMLDuplicateFileNameValidation() throws Exception{
				
					AIXMLFileNameUniqueValidation.validateAIXMLUniqueFileNameInDatabase(aixmlTemplateFileName,fredAIXMLTempFolderPath,strAIXMLRejectFldrPathKey,strAIXMLArchiveFldrPathKey,strAIXMLFilePath,dbServerName,fredDatabaseName,aixmlCopyTempFldrPath);
			}
		
			@Test(priority=15)
			public static void tc65877ErrorLogScnearioMF01() throws Exception{
				
				ValidateErrLogScenariosDebit.verifyErrorLogMF01SendQueue(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
						fredExtractIDValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlCreditFileType,
						sqlCreditTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
						sqlTemplateBusinessDateToBeReplaced,sqlCreditExtractIDSameValueTemplateFileName,strX9FileCreatedQueryValidationFromDatabase,mf01SendQueueQuery,
						sqlCreditExtractIDLenIncorrectValTemplateFileName,sqlCreditExtractIDLenIncorrectValTemplateFileName1,fredBusinessDateValidationWithBatchMF01,
						sqlBusinessDateSecondCreditTemplateFileName,sqlBusinessDateCreditTemplateFileName,aixmlTempFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey,strICNFilePath,strICNXMLFileName);
			}
			
			@Test(priority=16)
			public static void tc77317ValidateCreditInclearingXSDwithICN() throws Exception{
				
				XSDICNValidationFM01.validateFM01XSDWithICN(dbServerName, fredDatabaseName, templateFilePath,strICNFilePath,strICNXMLFileName,ICNXSDValFile,sqlCreditFileType,sqlDebitFileType,dbConfigBusinessDateSQL);
			}
			
			@Test(priority=17)
			public static void tc77319ValidateDebitInclearingXSDwithICN() throws Exception{
				
				XSDICNValidationFM01.validateMF01CreditDebitXSDWithICN(dbServerName, fredDatabaseName, templateFilePath,strICNFilePath,strICNXMLFileName,ICNXSDValFile,sqlCreditFileType,sqlDebitFileType,dbConfigBusinessDateSQL);
			}
			
			@Test(priority=18)
			public static void tc81291Validate06MF01ICNWithIDS() throws Exception{
				//debit ibnlearing
				CoreICNValidation.validateICNWithIDSSpecification(excelFilePath,destActualFilePath,icnMF06File);
				ICNItemValidationMF01.validateICNDebitInClearingItemWithIDS(excelFilePath,destActualFilePath,icnMF06File);
				//ICNItemValidationMF01.validateICNClearingItemWithIDS(excelFilePath,destActualFilePath,ICNMF06File);
			}
			
			@Test(priority=19)
			public static void tc65862ValidateICNWithIDS_ItemHistoryDetails() throws Exception{
				
				String aixmlFileWithNoHistory="DI_MO_65862_ItemHistory";
				ValidateICNInclearingItemDetails.validate65862ICNHistorydetails(excelFilePath,dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, 
						strICNFilePath, aixmlFileWithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
			}
			
			@Test(priority=20)
			public static void tc65863ValidateICNWithIDS_NoItemHistoryDetails() throws Exception{
				
				String aixmlTempfile="DI_MO_65863_NoHistory";
				ValidateICNInclearingItemDetails.validate65863ICNNoHistorydetails(excelFilePath,dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlTempfile, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
				
			}
		
			@Test(priority=21)
			public static void tc80848ValidateCaptureInfoICN_NoItemDetails() throws Exception{
				
				String aixmlFile80848WithNoHistory="DI_MO_80848_NoHistory";
				ValidateICNInclearingItemDetails.validate80848ICNNoHistoryDetail(excelFilePath,dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlFile80848WithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
				
			}
			
			@Test(priority=22)
			public static void tc80849ValidateCaptureInfoICNWithItemHistoryDetails() throws Exception{
			
				String aixmlFileWithHistory="DI_MO_80849_HistoryDetails";
				ValidateICNInclearingItemDetails.validate80849ICNHistoryDetail(excelFilePath,dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, strICNFilePath, aixmlFileWithHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);	
								
			}
			
			@Test(priority=23)
			public static void tc66098ErrorLogScnearioAIXML() throws Exception{
				
				ValidateErrLogScenariosDebit.validateAIXMLInvalidFile(dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, aixmlTempInvalidFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey, strICNFilePath, strICNXMLFileName);
			
			}
			
			@Test(priority=24)
			public static void tc69331ValidateOutClearingICN_NoItemDetails() throws Exception{
				
				String aixmlFileWithNoHistory="AIXMLExtract_OC_Mobile_NoHistoryDetails";
				ValidateOutclearingICNHistory.validateOutClearingICNWithIDS(excelFilePath,dbServerName, fredDatabaseName, fredAIXMLTempFolderPath, 
						strICNFilePath, aixmlFileWithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey);
			}
			
			@Test (priority =25)
			public void tc69937validateOutclearingAIXMLFileIngestion() throws Exception
			{
				String aixmlTempFileName="AIXMLExtract_OC_Mobile";
				ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}
			
			@Test (priority =26)
			public void tc69355validateOutclearingMutipleCreditSingleDebit() throws Exception
			{
				String aixmlTempFileName="AIXML_69355_OC_MutipleCrSingleDebit";
				ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}
			
			@Test (priority =27)
			public void tc69356validateOutclearingMutipleDebitSingleCredit() throws Exception
			{
				String aixmlTempFileName="AIXML_69356_OC_MutipleDrSingleCr";
				ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}
			
			
			@Test (priority =28)
			public void tc80666validateOutclearingISOItemTypeBulkTest() throws Exception
			{
			
				String aixmlBulkFilePath="\\\\129.227.33.35\\f$\\AutomationTestData\\OnusItemType\\LBG\\AIXMLExtract_Bulk_Test.xml";
				String icnfilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\OnusItemType\\LBG\\ICNOutputFileAIXMLBulkTest.xml";
		
				ValidateAIXMLMutipleItemsCreditDebitOutclearing.getCashItemTagValFromXML(aixmlBulkFilePath,icnfilepath);
			
			}
			
			@Test (priority =29)
			public void tc80667validateOutclearingISOItemTypeBulkTest() throws Exception
			{
				ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}
			
			@Test (priority =30)
			public void tc108789validateBatchInfoDetailsDebitIN() throws Exception
			{
				String aixmlTempFileName="AIXMLExtract_DEBITIN_BatchInfo";
				
				ValidateBatchInfoTagInAIXMLDatabase.validateAIXMLBatchInfoValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
		
			}
			
			@Test (priority =31)
			public void tc109285validateBatchInfoDetailsCREDITIN() throws Exception
			{
				String aixmlTempFileName="AIXMLExtract_CREDITIN_BatchInfo";				
				ValidateBatchInfoTagInAIXMLDatabase.validateAIXMLBatchInfoValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
		
			}
			
			@Test(priority =32)
			public void tc109288validateBatchInfoDetailsOutclearing() throws Exception
			{
				String aixmlTempFileName="AIXMLExtract_OC_BatchInfo";				
				ValidateBatchInfoTagInAIXMLDatabase.validateAIXMLBatchInfoValuesWithDB(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
		
			}
			
			@Test(priority =33)
			public void tc71822validateISOTags() throws Exception{
		
			String aixmlISOTag="AIXMLExtract_OC_Mobile";
			ValidateISOTransactionSetTagsWithVocalinkSpecs.validateISOTxSetSubmissionTagsWithVocalink(excelMSG01FilePath,dbServerName, fredDatabaseName, fredAIXMLTempFolderPath,strICNFilePath, aixmlISOTag, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey,strAIXMLRejectFldrPathKey,aixmlCopyTempFldrPath);
			ValidateICNCaptureItemsTagsWithIDS.validateCaptureInfoTags(excelFilePath,strICNFilePath,"ICNOutput_Image.xml");
		}
			
			
			/*@Test(priority =34)
			public void tc71826testDataClearing() throws Exception{
			//String aixmlItemsTempFileName="BigFile_imagesactual_IOC5"; AIXML_3000_IN_Test
		//	String aixmlItemsTempFileName="AIXML_3000_IN_Test";
				String aixmlItemsTempFileName="AIXML_3000_IN_22_Jun";
				for(int i=1;i<=1;i++){
					AIXMLTestDataCreditDebitItemsInclearing.generate3000ItemsAixml(aixmlItemsTempFileName,fredAIXMLTempFolderPath,dbServerName,fredDatabaseName,aixmlPerfFldrPath,i);
					//Thread.sleep(2000);
				}
			}*/
			
			
		/*	public void validateCaptureItemDetailsWithIDS() throws Exception{
				ValidateICNCaptureItemsTagsWithIDS.validateCaptureInfoTags(excelFilePath,strICNFilePath,"ICNOutput_Image.xml");
			}
			*/
			
			@Test(priority =34)
			public void tc108788deletedItemsOCValidation() throws Exception{
				String aixmlDeletedFlag = "AIXMLOriginalDeletedFlag";
				ValidateAIXMLExtractFileLoadedIntoDatabase.validateISOItemId(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlDeletedFlag,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}
			
			@Test(priority =35)
			public void tc91413ErrLogImageConversionFailureAIXML() throws Exception{
			//	String aixmlImageFile ="AIXMLOriginal_Cr_Dr_ImageIncorrect3940113940_Actual";
				ValidateAIXMLImageICNOutput.aixmlCorruptImageValidation(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlImageFile,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}
			
			@Test(priority =36)
			public void tc91364ErrLogImageConversionFailureX9() throws Exception{
				String sqlImageFileName ="TC_MSG05_CR_Corrupt_Image";
				ValidateAIXMLImageICNOutput.x9CorruptImageValidation(dbServerName,fredDatabaseName,sqlCreditFileType,templateFilePath,destActualFilePath,sqlImageFileName,sqlTemplateExtractIDToBeReplaced,sqlTemplateBusinessDateToBeReplaced,strImageDataTypeValidation,strImageDataExtractionFromDB,dbConfigBusinessDateSQL,fredAIXMLTempFolderPath,strICNFilePath,aixmlImageFile,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}
			
			@Test(priority =37)
			public void tc98374ErrLogImageFailureX9() throws Exception{
				String sqlCreditTemplateFileName1 ="TC_MSG05_CR_Split_Image";
				ValidateAIXMLImageICNOutput.x9SplitImageValidation(dbServerName,fredDatabaseName,sqlCreditFileType,templateFilePath,destActualFilePath,sqlCreditTemplateFileName1,sqlTemplateExtractIDToBeReplaced,sqlTemplateBusinessDateToBeReplaced,strImageDataTypeValidation,strImageDataExtractionFromDB,dbConfigBusinessDateSQL,fredAIXMLTempFolderPath,strICNFilePath,aixmlImageFile,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			}
			
			
			
		}






/*package com.ics.tests.fred;

import org.testng.annotations.Test;

import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.fred.testScenarios.AIXMLServiceStartValidation;
import com.ics.fred.testScenarios.ValidateAIXMLExtractFileLoadedIntoDatabase;
import com.ics.fred.testScenarios.ValidateAIXMLImageICNOutput;
import com.ics.fred.testScenarios.ValidateMSG01DeletedItemStatus;
import com.ics.fred.testScenarios.Outclearing.ValidateISOTransactionSetTagsWithVocalinkSpecs;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

public class Regression_Sprint_5_4 extends ICSAutomationCommonSetup{
	private static String sqlCreditFileType = ICSPropertiesConfig.getSqlFileTypeAsCredit();
	private static String sqlDebitFileType = ICSPropertiesConfig.getSqlFileTypeAsDebit();
	private static String templateFilePath = ICSPropertiesConfig.getsqlTemplateFilePath();
	private static String creditX9FilePath = ICSPropertiesConfig.getcreditX9FilePath();
	private static String debitX9FilePath = ICSPropertiesConfig.getdebitX9FilePath();
	private static String dbConfigBusinessDateSQL = ICSPropertiesConfig.getDBConfigBusinessDateSQL();
	private static String dbServerName = ICSPropertiesConfig.getFREDDBServerName();
	private static String fredDatabaseName = ICSPropertiesConfig.getFREDDatabaseName();		
			static String strICNFilePath =ICSPropertiesConfig.getICNFilePathKey();
			 static String strAIXMLArchiveFldrPathKey =ICSPropertiesConfig.getAIXMLArchiveFldrPathKey();
			 static String strAIXMLFilePath =ICSPropertiesConfig.getaixmlFilePthKey();
			 static String fredAIXMLTempFolderPath =ICSPropertiesConfig.getFREDAIXMLTempFolderPathKey();
			 private static String fredBuisnessDateDataType = ICSPropertiesConfig.getFREDBuisnessDateDataType();
				private static String fredBusinessDateValidationWithBatchMF01 = ICSPropertiesConfig.getFREDBusinessDateValidationWithBatchMF01();
				private static String fredBusinessDateValidationWithDBInErrLog = ICSPropertiesConfig.getFREDBusinessDateValidationWithDBInErrLog();
				private static String sqlE022ExtractDataFromDB = ICSPropertiesConfig.getFREDE022MessageDataExtractionFromDB();
				private static String strImageDataTypeValidation = ICSPropertiesConfig.getFREDImageDataTypeValidation();
				private static String strImageDataExtractionFromDB = ICSPropertiesConfig.getFREDImageDataExtractionFromDB();
				private static String fredExtractIDValidationWithBatchMF01 = ICSPropertiesConfig.getSQLExtractIDValueFromBatchMF01Key();
				private static String strX9FileCreatedQueryValidationFromDatabase= ICSPropertiesConfig.getX9FileCreatedQueryValidationKey();
					private static String mf01SendQueueQuery =ICSPropertiesConfig.getMF01SendQueueQueryKey();
				private static String fredInputTestDataFile =ICSPropertiesConfig.getFREDTestDataInputFileKey();
				private static String sheetName = getExcelSheetName(new Object(){});
			
				public static String excelMSG01FilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\AIXMLTemplate\\MSG01ICN.xlsx";
				private static String aixmlPerfFldrPath="\\\\129.227.33.35\\f$\\AutomationTestData\\AIXMLTemplate\\AIXMLPerf\\";
				
				private static String aixmlTemplateFileName = "UniqueFileValidation";
				private static String aixmlTempFileName = "DI_MO_92548";
				static String aixmlTempInvalidFileName="InvalidAIXMLFile";
				
						
				private static String sqlCreditTemplateFileName = "TC_MSG05_CR";
				private static String sqlDebitTemplateFileName = "TC_62948_DR";
				static String ICNXSDValFile ="ICNXSDValidationMsg";
				private static String sqlTemplateExtractIDToBeReplaced = "STRINGTOBEREPLACEDININPUTFILE";
				private static String sqlTemplateBusinessDateToBeReplaced = "BUSINESSDATETOBEREPLACE";
			
			 String destActualFilePath="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\MF01InputFolder\\";
			 String aixmlCopyTempFldrPath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\AIXMLTempFolder\\";
			 static	String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
			 static	String excelFilePathMS01 ="\\\\129.227.33.35\\f$\\AutomationTestData\\AIXMLTemplate\\MSG01ICN.xlsx";
			
				private static String strAIXMLRejectFldrPathKey =ICSPropertiesConfig.getAIXMLRejectFldrPathKey();
				
				 String ICNFileName="ICNOutput.xml";
				 String ICNMF05File="TC_MSG05_CR_Actual.xml";
				 String icnMF06File="TC_62948_DR_Actual.xml";
				 static String strICNXMLFileName="ICNOutput";
				 String aixmlImageFile ="AIXMLOriginal_Cr_Dr_ImageIncorrect3940113940_Actual";
				
				static String aixmlFileWithNoHistory="AIXMLExtract_OC_Mobile";
				
				@Test
				public void getTagValISO() throws Exception{
					ValidateISOTransactionSetTagsWithVocalinkSpecs.validateISOTxSetSubmissionTagsWithVocalink(excelFilePathMS01,dbServerName, fredDatabaseName, fredAIXMLTempFolderPath,strICNFilePath, aixmlFileWithNoHistory, strICNXMLFileName, strAIXMLFilePath, strAIXMLArchiveFldrPathKey,strAIXMLRejectFldrPathKey,aixmlCopyTempFldrPath);
					
				}
				
				@Test
				public void tc108788deletedItemsOCValidation() throws Exception{
					String aixmlDeletedFlag = "AIXMLOriginalDeletedFlag";
					ValidateAIXMLExtractFileLoadedIntoDatabase.validateISOItemId(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlDeletedFlag,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
				}
				
				@Test
				public void tc91413ErrLogImageConversionFailureAIXML() throws Exception{
					
					ValidateAIXMLImageICNOutput.aixmlCorruptImageValidation(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlImageFile,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
				}
				
				public void tc91364ErrLogImageConversionFailureX9() throws Exception{
					String sqlCreditTemplateFileName ="TC_MSG05_CR_Corrupt_Image";
					ValidateAIXMLImageICNOutput.x9CorruptImageValidation(dbServerName,fredDatabaseName,sqlCreditFileType,templateFilePath,destActualFilePath,sqlCreditTemplateFileName,sqlTemplateExtractIDToBeReplaced,sqlTemplateBusinessDateToBeReplaced,strImageDataTypeValidation,strImageDataExtractionFromDB,dbConfigBusinessDateSQL,fredAIXMLTempFolderPath,strICNFilePath,aixmlImageFile,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
				}
				
				public void tc98374ErrLogAIXMLValidation(){
					
				}
				
				public void tc98967ErrLogX9ImgValidation(){
					
				}
				
				
				
			}
*/