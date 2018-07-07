package com.ics.tests.fred;

	import java.io.IOException;
	import java.sql.SQLException;
	import java.text.ParseException;

	import javax.xml.parsers.ParserConfigurationException;

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
	import com.ics.fred.testScenarios.ValidateE031ICNCoreSubTags;
	import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

	@Test
	public class Sprint3_2_New extends GenericMethodUtilis{
		 
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
			private static String strAIXMLRejectFldrPathKey =ICSPropertiesConfig.getAIXMLRejectFldrPathKey();
			private static String strAIXMLArchiveFldrPathKey =ICSPropertiesConfig.getAIXMLArchiveFldrPathKey();
			private static String strAIXMLFilePath =ICSPropertiesConfig.getaixmlFilePthKey();
		
			
			private static String sqlCreditTemplateFileName = "TC_62948CR";
			private static String sqlDebitTemplateFileName = "TC_62948DR";
			private static String aixmlTemplateFileName = "74431_AIXML_OC";
			private static String sqlTemplateExtractIDToBeReplaced = "STRINGTOBEREPLACEDININPUTFILE";
			private static String sqlTemplateBusinessDateToBeReplaced = "BUSINESSDATETOBEREPLACE";
			private static String templateAIXMLFileBlockNoString = "BLOCKNUMBERTOBEREPLACED";
			
				
			//*********** TC-78180 and TC-77830 Variable declaration ************//
			
			private static String sqlBusinessDateCreditTemplateFileName = "TC_78180_05MF01_BuisnessDateValidation";
			private static String sqlBusinessDateDebitTemplateFileName = "TC_77830_06MF01_BuisnessDateValidation";
			private static String sqlBusinessDateSecondDebitTemplateFileName = "TC_77830_06MF01_BuisnessDateValidationIncorrect";
			private static String sqlBusinessDateSecondCreditTemplateFileName = "TC_77830_05MF01_BuisnessDateValidationIncorrect";
			
				
			//********* TC-78183 ExtarctID validation Variable Declaration *******//
			
			private static String sqlCreditExtractIDSameValueTemplateFileName = "TC_05MF01_SameExtractIDValues";
			private static String sqlDebitExtractIDSameValueTemplateFileName = "TC_06MF01_SameExtractIDValues";		
			private static String strICNXMLFileName="ICNOutput.xml";
			
			//************* SQL Command Execution ****************//
			
			private static String sqlFileInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-33-V\\SQL001 -d Fred_DB_Auto -i "+
					templateFilePath + sqlCreditTemplateFileName + "_Actual.sql ";
			
			private static String sqlFileMultipleInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-33-V\\SQL001 -d Fred_DB_Auto -i "+
					templateFilePath + sqlCreditTemplateFileName + "_Actual.sql "+
							templateFilePath + sqlDebitTemplateFileName + "_Actual.sql";
			
			String a = getFredResourceFile().getString("sqlFileTypeAsCredit");			
			
		/*	@Test
			public void test_case_65881() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, Exception, ParserConfigurationException
			{
				
				AIXMLFileNameUniqueValidation.validateAIXMLUniqueFileName(aixmlTemplateFileName,templateFilePath,templateAIXMLFileBlockNoString,strAIXMLRejectFldrPathKey,strAIXMLArchiveFldrPathKey,
						sqlTemplateBusinessDateToBeReplaced,dbConfigBusinessDateSQL,strAIXMLFilePath);					      				
			}*/
			
		   //********** TestCase  : TC_62948_Single -Inject single sql file and verify X9 file is created ***********/
		/*	@Test(priority=1)
			public void test_case_62948_Single() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException
			{
				SingleFileInsertionCreditItem.generateAndVerifyX9FileName(dbServerName, fredDatabaseName, dbConfigBusinessDateSQL, sqlCreditFileType, sqlCreditTemplateFileName, templateFilePath,
						sqlTemplateExtractIDToBeReplaced, sqlTemplateBusinessDateToBeReplaced, sqlFileInjectCommand, creditX9FilePath );
				
			}
			
		*/	//********** TestCase  : TC_62948_Multiple -Inject two sql files (1 credit and 1 debit) simultaneously and verify X9 file is created ***********/
		/*	@Test(priority=2)
			public void test_case_62948_Multiple() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException
			{
				MultiFileInsertionCreditDebitItem.generateAndVerifyX9FileNameMultiItem(dbServerName, fredDatabaseName, dbConfigBusinessDateSQL, sqlCreditFileType, sqlDebitFileType, 
						sqlCreditTemplateFileName, sqlDebitTemplateFileName, templateFilePath, sqlTemplateExtractIDToBeReplaced, 
						sqlFileMultipleInjectCommand, creditX9FilePath, debitX9FilePath, sqlTemplateBusinessDateToBeReplaced);
			} 
		*/	
			
			
			//********** TestCase 3 : TC_77830 -DI BusinessDate Validation Testing ***********//
		/*	@Test (priority=3)
			public void test_case_77830() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, SAXException, ParserConfigurationException, ParseException
			{
			    FREDDIMF01BusinessDateValidation.validAndInvalidDIBusinessDateValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
						fredBusinessDateValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlDebitFileType,
						sqlBusinessDateDebitTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
						sqlTemplateBusinessDateToBeReplaced,sqlBusinessDateSecondDebitTemplateFileName);
			}
		
			
		*/	//********** TestCase 4 : TC_78180 -CI BusinessDate Validation Testing ***********//
		/*	@Test (priority=4)
			public void test_case_78180() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, SAXException, ParserConfigurationException, ParseException
			{
			    FREDCIMF01BusinessDateValidation.validAndInvalidCIBusinessDateValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
						fredBusinessDateValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlCreditFileType,
						sqlBusinessDateCreditTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
						sqlTemplateBusinessDateToBeReplaced,sqlBusinessDateSecondCreditTemplateFileName);
			}
		*/	
			//********** TestCase 5 : TC_58566 -LoadedData Extraction from DB ***********//		
		  /* @Test (priority=5)			
			public void test_case_58566() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
				{
				E022MessageLoadedIntoFREDDatabase.validateE022MessageLoadedIntoFredDB(dbServerName,fredDatabaseName,sqlE022ExtractDataFromDB,sqlDebitFileType,
						templateFilePath,sqlCreditTemplateFileName);
				}
			*/
			//********** TestCase 6 : TC_65837 -ImageData validation with DB ***********//*
			/*
			@Test (priority=6)
				public void test_case_65837() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
				{
					 
					ImageDataValidationWithDatabase.verifyImageDataFromDatabase(dbServerName,fredDatabaseName,strImageDataTypeValidation,strImageDataExtractionFromDB,
							templateFilePath,sqlDebitTemplateFileName,sqlDebitFileType);			
					
				}
			*/
			//********** TestCase 7 : TC_78183-FRED-CI-ExtractID Length validation with DB ***********//*
			/*	
			@Test (priority=7)
			public void test_case_78183() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, SAXException, ParserConfigurationException, ParseException
			{
				 
				FREDCIX9ExtractIDValidation.validAndInvalidCIExtractIDValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
					fredExtractIDValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlCreditFileType,
					sqlCreditTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
					sqlTemplateBusinessDateToBeReplaced,sqlCreditExtractIDSameValueTemplateFileName,strX9FileCreatedQueryValidationFromDatabase);
				   				
			}
			*/
			//********** TestCase 8 : TC_77326-FRED-DI-ExtractID Length validation with DB ***********//
			
			/*@Test (priority=8)		
			public void test_case_77326() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, SAXException, ParserConfigurationException, ParseException
			{
				 
				FREDDIX9ExtractIDValidation.validAndInvalidDIExtractIDValidation(dbServerName,fredDatabaseName,dbConfigBusinessDateSQL,
					fredExtractIDValidationWithBatchMF01,fredBuisnessDateDataType,fredBusinessDateValidationWithDBInErrLog,sqlDebitFileType,
					sqlDebitTemplateFileName,templateFilePath,sqlTemplateExtractIDToBeReplaced,
					sqlTemplateBusinessDateToBeReplaced,sqlDebitExtractIDSameValueTemplateFileName,strX9FileCreatedQueryValidationFromDatabase);   				
			}
			*/
		
			//********** TestCase 9 : TC_63234-ICN Core SubTags validation with DB ***********//
		
			/*@Test (priority=9)
			public void test_case_63234() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, Exception, ParserConfigurationException
			{
				 
				ValidateE031ICNCoreSubTags.getE031ICNCoreTagSubTagsValidation(strICNFilePath,strICNXMLFileName);
		      				
			}
			*/
			//********** TestCase 10 : TC_74431 -Batching changes in the ICN Logic for Multiple Credits to one debit in MSG01 file and multiple debits to one credit ***********/
				/*	private static String templateAIXMLFileName = "74431_AIXMLOutclearing_UNIQUENAME";
					private static String templateAIXMLFileBlockNoString = "BLOCKNUMBERTOBEREPLACED";
					
					private static String dbfredAuto2 = ICSPropertiesConfig.getdbfredAuto2Key();
					private static String getaixmlFilePth = ICSPropertiesConfig.getaixmlFilePthKey();
					
					@Test (priority=10)
					public void test_case_74431() throws IOException, InterruptedException, ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, ParseException
					{
						BatchingChangesInICNLogic.MultiCrSingleDrAndMultiDrSingleCr(templateFilePath, templateAIXMLFileName, sqlTemplateBusinessDateToBeReplaced, templateAIXMLFileBlockNoString, 
								dbServerName, fredDatabaseName, dbfredAuto2, dbConfigBusinessDateSQL, getaixmlFilePth);
						
					}*/
					
					
			
			
	}