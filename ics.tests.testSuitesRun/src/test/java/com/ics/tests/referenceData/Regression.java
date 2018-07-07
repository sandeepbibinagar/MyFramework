package com.ics.tests.referenceData;

import java.io.File;
import org.apache.commons.lang.ArrayUtils;
import org.testng.annotations.Test;


public class Regression extends RefDataGenericMethodUtilis {
	
	private boolean isEISCDProcessed_OneInAll=false;
	private boolean isEISCDOutput_OneInAll=false;
	private boolean isValidERDMSC = false;


	
		/*
		 * Test Case ID: 94191
		 * Test Description: REF - LBG Cheque and Credit Book File: Verify whether the command line for LBG Cheque & Credit Book is working properly or not
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Apr-2017
		 * Comments:
		 */
	@Test
		public void test_94191() throws Exception
		{
		boolean result;
		result = referenceData_processingInputFile("chbcr_94191","query_94191","CHBCR",Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),true,"");
		if(	result)
			new dynamicTestNGXml().resultPassToXl("test_94191","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_94191","Fail");
	
		}
	
		/*
		 * Test Case ID: 94192
		 * Test Description: REF - LBG Cheque and Credit Book File: Verify the audit status when the same file is reprocessed on the same day
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Apr-2017
		 * Comments:
		 */
			@Test
		public void test_94192() throws Exception
		
		{
				boolean isCHBCR=false,isCHBCRReprocessing=false;
				isCHBCR = referenceData_processingInputFile("chbcr_94192","query_94192","CHBCR",Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),true,"");
				isCHBCRReprocessing = referenceData_processingInputFile("chbcr_94192","query_94192","CHBCR",Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1)),true,"");
				if(	isCHBCR && isCHBCRReprocessing)
					new dynamicTestNGXml().resultPassToXl("test_94192","Pass");
				else
					new dynamicTestNGXml().resultPassToXl("test_94192","Fail");
			
		}
		

		/*
		 * Test Case ID: 94193
		 * Test Description: REF - LBG Cheque and Credit Book File: Verify if SSIS Package fails and doesn't have Audit Status when file is not present in the input location.
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_94193() throws Exception
		{
			boolean result=validateDBWhenSSISFails("CHBCR","chbcr_94193","query_CountOfAudit");
			if(	result )
				new dynamicTestNGXml().resultPassToXl("test_94193","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_94193","Fail");
		
		}

		/*
		 * Test Case ID: 94194
		 * Test Description: REF - LBG Cheque and Credit Book File: Verify whether the command line fails when the mandatory values in the command lines are not passed
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_94194() throws Exception
		{
			boolean result=validateDBWhenSSISFails("CHBCR","chbcr_94194","query_CountOfAudit");
			if(	result )
				new dynamicTestNGXml().resultPassToXl("test_94194","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_94194","Fail");
		
		}
		/*
		 * Test Case ID: 94195
		 * Test Description: REF - LBG Cheque and Credit Book File: Verify the audit status when SSIS Package is executed successfully
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Apr-2017
		 * Comments: 
		 */
		
		@Test
		public void test_94195() throws Exception
		{
			boolean result;
			result = referenceData_processingInputFile("chbcr_94195","query_94191","CHBCR-Valid File",Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),true,"");
			if(	result)
				new dynamicTestNGXml().resultPassToXl("test_94195","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_94195","Fail");
			}
	/*
	 * Test Case ID: 94362
	 * Test Description: REF - LBG Cheque and Credit Book File: Verify the audit status when the file with incorrect header data is processed
	 * Author: Hemapriya Shanmugam
	 * Date: 20-Apr-2017
	 * Comments:
	 */

	@Test
		public void test_94362() throws Exception
		{
		boolean result;
		result = referenceData_processingInputFile("chbcr_94362","query_94191","CHBCR-Incorrect Header Data",Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),true,"");
		if(	result)
			new dynamicTestNGXml().resultPassToXl("test_94362","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_94362","Fail");
		}
			
		/*
		 * Test Case ID: 94374
		 * Test Description: REF - LBG Cheque and Credit Book File: Verify the Audit Status when the file with incorrect data record is processed.
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Apr-2017
		 * Comments:
		 */

		@Test
		public void test_94374() throws Exception
		{
			boolean result;
			result = referenceData_processingInputFile("chbcr_94374","query_94191","CHBCR-Incorrect Data Record",Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),true,"");
			if(	result)
				new dynamicTestNGXml().resultPassToXl("test_94374","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_94374","Fail");
		}

		/*
		 * Test Case ID: 94375
		 * Test Description: REF - LBG Cheque and Credit Book File: Verify the Audit Status when the file with incorrect tail data is processed.
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Apr-2017
		 * Comments:
		 */

		@Test
		public void test_94375() throws Exception
		{
			boolean result;
			result = referenceData_processingInputFile("chbcr_94375","query_94191","CHBCR-Incorrect Trail Record Data",Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),true,"");
			if(	result)
				new dynamicTestNGXml().resultPassToXl("test_94375","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_94375","Fail");
				
		}
	
		/*
		 * Test Case ID: 94377
		 * Test Description: REF - LBG Cheque and Credit Book File: Verify the Audit Status when the file with incorrect date format in header data is processed.
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_94377() throws Exception
		{
			boolean result;
			result = referenceData_processingInputFile("chbcr_94377","query_94191","CHBCR-Incorrect Date in Header",Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),true,"");	
			if(	result)
				new dynamicTestNGXml().resultPassToXl("test_94377","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_94377","Fail");
		}

		/*
		 * Test Case ID: 94392
		 * Test Description: REF - LBG Cheque and Credit Book File: Verify the Audit Status when the file with invalid length is processed.
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Apr-2017
		 * Comments:
		 */

		@Test
		public void test_94392() throws Exception
		{
			boolean result;
			result = referenceData_processingInputFile("chbcr_94392","query_94191","CHBCR-Invalid Length Tag Value",Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),true,"");
			if(	result)
				new dynamicTestNGXml().resultPassToXl("test_94392","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_94392","Fail");
		}
			
		
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the chbcr file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test

		public void test_74306_chbcr() throws Exception
		{
			boolean isCHBCR;
			boolean outputValidationRequired = true;
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			
			isCHBCR = validateInboundCommonFiles("chbcr_74306","query_94191","CHBCR",expectedStatus,outputValidationRequired,"chbcr_outputFilePath","chbcr_filePath",false);
			
			if(	isCHBCR )
				new dynamicTestNGXml().resultPassToXl("test_74306_chbcr","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74306_chbcr","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the eiscd file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_eiscd() throws Exception
		{
		boolean isEISCD;//isCass //DEPRECATED
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		boolean outputValidationRequired = true;
		
		isEISCD = validateInboundCommonFiles("eiscd_74306","query_eiscd_74306","EISCD",expectedStatus,outputValidationRequired,"eiscd_outputFilePath","eiscd_filePath",false);
		if(	isEISCD )
			new dynamicTestNGXml().resultPassToXl("test_74306_eiscd","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_eiscd","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the positive pay file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_pospay() throws Exception
		{
		boolean isPosPay;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		boolean outputValidationRequired = true;
		
		isPosPay = validateInboundCommonFiles("pospay_74306","query_pospay_74306","POSITIVE PAY",expectedStatus,outputValidationRequired,"pospay_outputFilePath","pospay_filePath",false);
		
		if(	isPosPay )
			new dynamicTestNGXml().resultPassToXl("test_74306_pospay","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_pospay","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the Business Calendar file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_businessCalendar() throws Exception
		{
		boolean isBCalProcessed;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		
		isBCalProcessed = referenceData_processingInputFile("bcal_74306","query_bcal_74306","BUSINESS CALENDAR",expectedStatus,true,"");
		if(	isBCalProcessed )
			new dynamicTestNGXml().resultPassToXl("test_74306_businessCalendar","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_businessCalendar","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the BusinessDate file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_businessDate() throws Exception
		{
		boolean isBDateProcessed;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		
		isBDateProcessed = referenceData_processingInputFile("bdate_74306","query_bdate_74306","BUSINESS DATE",expectedStatus,true,"");
		if(	isBDateProcessed)
			new dynamicTestNGXml().resultPassToXl("test_74306_businessDate","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_businessDate","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the CODOS file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_codos() throws Exception
		{
		boolean isCodosProcessed;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		
		isCodosProcessed = referenceData_processingInputFile("codos_74306","query_codos_74306","CODOS",expectedStatus,true,"");
		if(	 isCodosProcessed )
			new dynamicTestNGXml().resultPassToXl("test_74306_codos","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_codos","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the ERDMS Commercial file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_erdmsCommercial() throws Exception
		{
			ERDMSC_DBCleanup();
		boolean isERDMSCProcessed;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		
		isERDMSCProcessed = referenceData_processingInputFile("erdmsC_74306","query_erdmsC_74306","ERDMS COMMERCIAL",expectedStatus,true,"");
		if(	isERDMSCProcessed )
			new dynamicTestNGXml().resultPassToXl("test_74306_erdmsCommercial","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_erdmsCommercial","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the ERDMS Agency file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_erdmsAgency() throws Exception
		{
		ERDMSA_DBCleanup();
		boolean isERDMSAProcessed;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		
		isERDMSAProcessed = referenceData_processingInputFile("erdmsA_74306","query_erdmsA_74306","ERDMS AGENCY",expectedStatus,true,"");
		if(	isERDMSAProcessed )
			new dynamicTestNGXml().resultPassToXl("test_74306_erdmsAgency","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_erdmsAgency","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the ERDMS Retail file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_erdmsRetail() throws Exception
		{
		boolean isERDMSRProcessed;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		
		isERDMSRProcessed = referenceData_processingInputFile("erdmsR_74306","query_erdmsR_74306","ERDMS RETAIL",expectedStatus,true,"");
		if(	isERDMSRProcessed )
			new dynamicTestNGXml().resultPassToXl("test_74306_erdmsRetail","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_erdmsRetail","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the ERDMS Internal file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_erdmsInternal() throws Exception
		{
		boolean isERDMSIProcessed;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		
		isERDMSIProcessed = referenceData_processingInputFile("erdmsI_74306","query_erdmsI_74306","ERDMS INTERNAL",expectedStatus,true,"");
		if(	 isERDMSIProcessed )
			new dynamicTestNGXml().resultPassToXl("test_74306_erdmsInternal","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_erdmsInternal","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the STOPS file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_stops() throws Exception
		{
		boolean isSTOPSProcessed;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		
		isSTOPSProcessed = referenceData_processingInputFile("stops_74306","query_stops_74306","STOPS",expectedStatus,true,"");
		if(	isSTOPSProcessed )
			new dynamicTestNGXml().resultPassToXl("test_74306_stops","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_stops","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the STOPS Traveller file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_stopsTraveller() throws Exception
		{
		boolean isSTOPSTProcessed;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		
		isSTOPSTProcessed = referenceData_processingInputFile("stopsT_74306","query_stopsT_74306","STOPS TRAVELLER",expectedStatus,true,"");
		if(	isSTOPSTProcessed )
			new dynamicTestNGXml().resultPassToXl("test_74306_stopsTraveller","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_stopsTraveller","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the ICREX file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_icrex() throws Exception
		{
			boolean isICREXProcessed = false, isICREXDBValidation = false;
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ICREX_DBCleanup();
			setUserDBRoleByRoleName("Hsbc");
			isICREXProcessed = referenceData_processingInputFile("sc_Icrex_74306","query_icrex_74306","SORT CODES - ICREX",expectedStatus,true,"");
			if (isICREXProcessed)
				{
					String[] dataContentsHeader=getStringFromDelimitedFile("icrexFile","01","");
					String[] dataContentsData=getStringFromDelimitedFile("icrexFile","02","");
			
					isICREXDBValidation = validateHSBCSortCodes(dataContentsHeader,dataContentsData,"ICREX");
				}
			setUserDBRoleByRoleName("all");
			if( isICREXProcessed && isICREXDBValidation )
				new dynamicTestNGXml().resultPassToXl("test_74306_icrex","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74306_icrex","Fail");
			}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the IDREX file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_idrex() throws Exception
		{
			boolean isIDREXProcessed = false, isIDREXDBValidation = false;
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			IDREX_DBCleanup();
			setUserDBRoleByRoleName("Hsbc");
		
		isIDREXProcessed = referenceData_processingInputFile("sc_Idrex_74306","query_idrex_74306","SORT CODES - IDREX",expectedStatus,true,"");
		if (isIDREXProcessed)
		{
			String[] dataContentsHeader=getStringFromDelimitedFile("idrexFile","01","");
			String[] dataContentsData=getStringFromDelimitedFile("idrexFile","02","");
	
			isIDREXDBValidation = validateHSBCSortCodes(dataContentsHeader,dataContentsData,"IDREX");
		}
		setUserDBRoleByRoleName("all");

		
		if(	 isIDREXProcessed && isIDREXDBValidation)
			new dynamicTestNGXml().resultPassToXl("test_74306_idrex","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_idrex","Fail");
		}
		/*
		 * Test Case ID: 74306
		 * Test Description: REF - Verify whether the Status is 0 when package tries to process the IDEEX file.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74306_ideex() throws Exception
		{
		boolean  isIDEEXProcessed, isIDEEXDBValidation = false;
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		IDEEX_DBCleanup();
		setUserDBRoleByRoleName("Hsbc");		
		isIDEEXProcessed = referenceData_processingInputFile("sc_Ideex_74306","query_ideex_74306","SORT CODES - IDEEX",expectedStatus,true,"");
		if (isIDEEXProcessed)
		{
			String[] dataContentsHeader=getStringFromDelimitedFile("ideexFile","01","");
			String[] dataContentsData=getStringFromDelimitedFile("ideexFile","02","");
	
			isIDEEXDBValidation = validateHSBCSortCodes(dataContentsHeader,dataContentsData,"IDEEX");
		}
		setUserDBRoleByRoleName("all");

		
		if(	 isIDEEXProcessed && isIDEEXDBValidation)
			new dynamicTestNGXml().resultPassToXl("test_74306_ideex","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_ideex","Fail");
		}
		/*@Test
		public void test_74306_cass() throws Exception
		{
		boolean isCass //DEPRECATED
		int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
		boolean outputValidationRequired = true;
		
		isCass = validateCass(); //DEPRECATED			
		if(	isCass)
			new dynamicTestNGXml().resultPassToXl("test_74306_cass","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_74306_cass","Fail");
		}*/
		
		
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the same CHBCR file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_chbcr() throws Exception
		{
			boolean isCHBCRReprocessed = false;
			boolean isCHBCRProcessed;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			boolean outputValidationRequired = false;
			
			isCHBCRProcessed = validateInboundCommonFiles("chbcr_74310","query_94191","CHBCR",expectedStatus_1,outputValidationRequired,"chbcr_outputFilePath","chbcr_filePath",false);
			isCHBCRReprocessed = validateInboundCommonFiles("chbcr_74310","query_chbcr_74310","CHBCR",expectedStatus_2,outputValidationRequired,"chbcr_outputFilePath","chbcr_filePath",isCHBCRProcessed);

			if(	isCHBCRReprocessed && isCHBCRProcessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_chbcr","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_chbcr","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the EISCD file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_eiscd() throws Exception
		{
			boolean isEISCDReprocessed=false;
			boolean isEISCDProcessed;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			boolean outputValidationRequired = false;
			

			isEISCDProcessed = validateInboundCommonFiles("eiscd_74310","query_eiscd_74310","EISCD",expectedStatus_1,outputValidationRequired,"eiscd_outputFilePath","eiscd_filePath",false);
			isEISCDReprocessed = validateInboundCommonFiles("eiscd_74310","query_eiscd_74310","EISCD",expectedStatus_2,outputValidationRequired,"eiscd_outputFilePath","eiscd_filePath",isEISCDProcessed);

			if(	 isEISCDReprocessed && isEISCDProcessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_eiscd","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_eiscd","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the Positive Pay file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_pospay() throws Exception
		{
			boolean isPosPayReprocessed=false, isPosPayProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			boolean outputValidationRequired = false;
			
			isPosPayProcessed = validateInboundCommonFiles("pospay_74310","query_pospay_74310","POSITIVE PAY",expectedStatus_1,outputValidationRequired,"pospay_outputFilePath","pospay_filePath",false);
			isPosPayReprocessed = validateInboundCommonFiles("pospay_74310","query_pospay_74310","POSITIVE",expectedStatus_2,outputValidationRequired,"pospay_outputFilePath","pospay_filePath",isPosPayProcessed);

			if(	isPosPayReprocessed && isPosPayProcessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_pospay","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_pospay","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the Business Calendar file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_businessCalendar() throws Exception
		{
			boolean isBCalReprocessed = false,isBCalProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			
			
			isBCalProcessed = referenceData_processingInputFile("bcal_74310","query_bcal_74310","BUSINESS CALENDAR",expectedStatus_1,true,"");
			if(isBCalProcessed)isBCalReprocessed = referenceData_processingInputFile("bcal_74310","query_bcal_74310","BUSINESS CALENDAR - REPROCESS",expectedStatus_2,true,"");
			
			if(	isBCalReprocessed && isBCalProcessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_businessCalendar","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_businessCalendar","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the Business Date file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_businessDate() throws Exception
		{
			boolean isBDateReprocessed=false, isBDateProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			
			isBDateProcessed = referenceData_processingInputFile("bdate_74310","query_bdate_74310","BUSINESS DATE",expectedStatus_1,true,"");
			if(isBDateProcessed)isBDateReprocessed = referenceData_processingInputFile("bdate_74310","query_bdate_74310","BUSINESS DATE - REPROCESS",expectedStatus_2,true,"");
			
			if(	 isBDateReprocessed && isBDateProcessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_businessDate","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_businessDate","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the CODOS file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_codos() throws Exception
		{
			boolean isCodosReprocessed=false, isCodosProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			
			isCodosProcessed = referenceData_processingInputFile("codos_74310","query_codos_74310","CODOS",expectedStatus_1,true,"");
			if(isCodosProcessed)isCodosReprocessed = referenceData_processingInputFile("codos_74310","query_codos_74310","CODOS - REPROCESS",expectedStatus_2,true,"");
			
			if(isCodosProcessed && isCodosReprocessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_codos","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_codos","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the ERDMS Commercial file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_erdmsCommercial() throws Exception
		{
			boolean isERDMSCReprocessed=false, isERDMSCProcessed = false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			
			isERDMSCProcessed = referenceData_processingInputFile("erdmsC_74310","query_erdmsC_74310","ERDMS COMMERCIAL",expectedStatus_1,true,"");
			if(isERDMSCProcessed)isERDMSCReprocessed = referenceData_processingInputFile("erdmsC_74310","query_erdmsC_74310","ERDMS COMMERCIAL - REPROCESS",expectedStatus_2,true,"");
			if(	isERDMSCReprocessed && isERDMSCProcessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_erdmsCommercial","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_erdmsCommercial","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the ERDMS Agency file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_erdmsAgency() throws Exception
		{
			boolean isERDMSAReprocessed=false,isERDMSAProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			
			isERDMSAProcessed = referenceData_processingInputFile("erdmsA_74310","query_erdmsA_74310","ERDMS AGENCY",expectedStatus_1,true,"");
			if(isERDMSAProcessed)isERDMSAReprocessed = referenceData_processingInputFile("erdmsA_74310","query_erdmsA_74310","ERDMS AGENCY - REPROCESS",expectedStatus_2,true,"");
			
			if(	isERDMSAProcessed && isERDMSAReprocessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_erdmsAgency","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_erdmsAgency","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the ERDMS Retail file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_erdmsRetail() throws Exception
		{
			boolean isERDMSRReprocessed=false,isERDMSRProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			
						
			isERDMSRProcessed = referenceData_processingInputFile("erdmsR_74310","query_erdmsR_74310","ERDMS RETAIL",expectedStatus_1,true,"");
			if(isERDMSRProcessed)isERDMSRReprocessed = referenceData_processingInputFile("erdmsR_74310","query_erdmsR_74310","ERDMS RETAIL - REPROCESS",expectedStatus_2,true,"");
			
			if(	isERDMSRProcessed && isERDMSRReprocessed )
				new dynamicTestNGXml().resultPassToXl("test_74310_erdmsRetail","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_erdmsRetail","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the ERDMS Internal file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_erdmsInternal() throws Exception
		{
			boolean isERDMSIReprocessed=false, isERDMSIProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
						
			isERDMSIProcessed = referenceData_processingInputFile("erdmsI_74310","query_erdmsI_74310","ERDMS INTERNAL",expectedStatus_1,true,"");
			if(isERDMSIProcessed)isERDMSIReprocessed = referenceData_processingInputFile("erdmsI_74310","query_erdmsI_74310","ERDMS INTERNAL - REPROCESS",expectedStatus_2,true,"");
			
			if(	isERDMSIProcessed&& isERDMSIReprocessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_erdmsInternal","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_erdmsInternal","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the STOPS file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_stops() throws Exception
		{
			boolean isSTOPSReprocessed=false, isSTOPSProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			
			isSTOPSProcessed = referenceData_processingInputFile("stops_74310","query_stops_74310","STOPS",expectedStatus_1,true,"");
			if(isSTOPSProcessed)isSTOPSReprocessed = referenceData_processingInputFile("stops_74310","query_stops_74310","STOPS - REPROCESS",expectedStatus_2,true,"");
			
			if(	isSTOPSProcessed && isSTOPSReprocessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_stops","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_stops","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the STOPS Traveller file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_stopsTraveller() throws Exception
		{
			boolean isSTOPSTReprocessed=false, isSTOPSTProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			
			isSTOPSTProcessed = referenceData_processingInputFile("stopsT_74310","query_stopsT_74310","STOPS TRAVELLER",expectedStatus_1,true,"");
			if(isSTOPSTProcessed)isSTOPSTReprocessed = referenceData_processingInputFile("stopsT_74310","query_stopsT_74310","STOPS TRAVELLER - REPROCESS",expectedStatus_2,true,"");
			
			if(	isSTOPSTProcessed && isSTOPSTReprocessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_stopsTraveller","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_stopsTraveller","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the ICREX file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */

		@Test
		public void test_74310_icrex() throws Exception
		{
			boolean isICREXReprocessed=false,isICREXProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			
			isICREXProcessed = referenceData_processingInputFile("sc_Icrex_74310","query_icrex_74310","SORT CODE - ICREX",expectedStatus_1,true,"");
			if(isICREXProcessed)isICREXReprocessed = referenceData_processingInputFile("sc_Icrex_74310","query_icrex_74310","SORT CODE - ICREX - REPROCESS",expectedStatus_2,true,"");
			if(	isICREXReprocessed && isICREXProcessed )
				new dynamicTestNGXml().resultPassToXl("test_74310_icrex","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_icrex","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the IDREX file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_idrex() throws Exception
		{
			boolean isIDREXReprocessed=false, isIDREXProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));
			
			isIDREXProcessed = referenceData_processingInputFile("sc_Idrex_74310","query_idrex_74310","SORT CODE - IDREX",expectedStatus_1,true,"");
			if(isIDREXProcessed)isIDREXReprocessed = referenceData_processingInputFile("sc_Idrex_74310","query_idrex_74310","SORT CODE - IDREX - REPROCESS",expectedStatus_2,true,"");
			
			if(	isIDREXProcessed && isIDREXReprocessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_idrex","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_idrex","Fail");
		}
		/*
		 * Test Case ID: 74310
		 * Test Description: REF - Verify whether the Status is 2 when package tries to process the IDEEX file which already has a status in audit table as 0.
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Apr-2017
		 * Comments:
		 */
		@Test
		public void test_74310_ideex() throws Exception
		{
			boolean isIDEEXReprocessed=false,isIDEEXProcessed=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)),expectedStatus_2=Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1));

			isIDEEXProcessed = referenceData_processingInputFile("sc_Ideex_74310","query_ideex_74310","SORT CODE - IDEEX",expectedStatus_1,true,"");
			if(isIDEEXProcessed)isIDEEXReprocessed = referenceData_processingInputFile("sc_Ideex_74310","query_ideex_74310","SORT CODE - IDEEX - REPROCESS",expectedStatus_2,true,"");
					
			if(	isIDEEXProcessed && isIDEEXReprocessed)
				new dynamicTestNGXml().resultPassToXl("test_74310_ideex","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_74310_ideex","Fail");
		}

		
		/*
		 * Test Case ID: 67653
		 * Test Description: EISCD_Inbound - Verify whether the Telephone and Fax Attributes in Inbound XML are mapped to correct attributes in the DB
		 * Author: Hemapriya Shanmugam
		 * Date: 11-May-2017
		 * Comments:
		 */
		@Test
		public void test_67653() throws Exception
		{
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			boolean isEISCD_67653=false,outPutValidationRequired = false; //since this case is not about output validation but tag validation in DB tables 
			isPass = false;
			isEISCD_67653=validateInboundCommonFiles("eiscd_67653","query_67653","EISCD Multi Agency",expectedStatus,outPutValidationRequired,"eiscd_outputFilePath_67653","eiscd_inputFilePath_67653",isEISCDProcessed_OneInAll);
			
			//Assigns Global variable
			if(!isEISCDProcessed_OneInAll)
				isEISCDProcessed_OneInAll = isEISCD_67653;
			isPass = validateTelephoneTagValuesForEISCD(isEISCDProcessed_OneInAll);
			
			if(isPass)
				new dynamicTestNGXml().resultPassToXl("test_67653","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_67653","Fail");		
		}
		/*
		 * Test Case ID: 67658
		 * Test Description: EISCD_Inbound - Verify whether the Address Attributes in Inbound XML are mapped to correct attributes in the DB
		 * Author: Hemapriya Shanmugam
		 * Date: 11-May-2017
		 * Comments:
		 */
			@Test
		public void test_67658() throws Exception
		{
			isPass = false;
			int expectedStatus=Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			boolean isEISCD_67658=true,outPutValidationRequired = false; //since this case is not about output validation but tag validation in DB tables 
			//Validation For Address
			if(!isEISCDProcessed_OneInAll)
			isEISCD_67658=validateInboundCommonFiles("eiscd_67653","query_67653","EISCD Multi Agency",expectedStatus,outPutValidationRequired,"eiscd_outputFilePath_67653","eiscd_inputFilePath_67653",isEISCDProcessed_OneInAll);
			
			//Assign Global Variable
			if(!isEISCDProcessed_OneInAll)
				isEISCDProcessed_OneInAll = isEISCD_67658;
			
			isPass=validateAddressTagValuesForEISCD(isEISCDProcessed,isEISCDProcessed_OneInAll,isEISCDOutput_OneInAll);	
			
			//Passes status to excel
			if(isPass)
				new dynamicTestNGXml().resultPassToXl("test_67658","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_67658","Fail");
	
		}	
				
		/*
		 * Test Case ID: 67660
		 * Test Description: EISCD_Inbound - Verify whether the Transaction tables are not getting updated when the file is not successfully processed.
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_67660() throws Exception
		{	
			//Validating SSIS Failure	
			boolean result = validateDBWhenSSISFails("EISCD","eiscd_67660","query_CountOfAudit");
			
			//Passes status to excel
			if(result)
				new dynamicTestNGXml().resultPassToXl("test_67660","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_67660","Fail");
	
		}
						
			/*
			 * Test Case ID: 67663
			 * Test Description: EISCD_Outbound - Verify whether the EISCD_Outbound file has all the tags that are present in the inbound XML
			 * Author: Hemapriya Shanmugam
			 * Date: 15-May-2017
			 * Comments:
			 */
			@Test
		public void test_67663() throws Exception
		{
			isPass =false;
			boolean outPutValidationRequired = true,isEISCD_67663=false;
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			
			//Validating Outbound
			isEISCD_67663=validateInboundCommonFiles("eiscd_67653","query_67653","EISCD Multi Agency",expectedStatus,outPutValidationRequired,"eiscd_outputFilePath_67653","eiscd_inputFilePath_67653",isEISCDProcessed_OneInAll);
			if(!isEISCDProcessed_OneInAll)
				isEISCDProcessed_OneInAll = isEISCD_67663;
			isPass = isEISCDProcessed_OneInAll;
			//Passes status to excel
			if(isPass)
				new dynamicTestNGXml().resultPassToXl("test_67663","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_67663","Fail");
	
		}
		
		/*
		 * Test Case ID: 104400
		 * Test Description: EISCD_Input: Null Bank Code  , Bank Code - 4 digits, Bank Code - only numerals
		 * Author: Hemapriya Shanmugam
		 * Date: 17-May-2017
		 * Comments:
		 */
		@Test
		public void test_104400() throws Exception
		{	
			boolean isEISCD=false;
			
			EISCD_DBCleanup();
			File file_null = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_104400_null"));
			File file_lt4 = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_104400_lt4"));
			File file_gt4 = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_104400_gt4"));
			File file_NN = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_104400_NN"));
		
			
			if(!(file_null.exists() && file_lt4.exists() || file_gt4.exists() || file_NN.exists()))
			{
			boolean result_null=validateDBWhenSSISFails("EISCD-Null Bank Code", "eiscd_bc", "query_CountOfAudit");
			boolean result_InvalidLengthLess =validateAuditStatusFailMsg("EISCD-Bank Code < 4 digits", "eiscd_bc_lt4","_bc_lt4","The 'BankCode' attribute is invalid ",1,null);
			boolean result_InvalidLengthMore =validateAuditStatusFailMsg("EISCD-Bank Code > 4 digits", "eiscd_bc_gt4","_bc_gt4","The 'BankCode' attribute is invalid ",1,null);
			boolean result_NonNumeric =validateAuditStatusFailMsg("EISCD-Bank Code Alphabetic values", "eiscd_bc_NN","_bc_NN","The 'BankCode' attribute is invalid ",1,null);
			if(result_null )
				isEISCD=validateOutputLocationForFail(file_null, "EISCD_BankCode(Null");
			if(result_InvalidLengthLess )
				isEISCD=validateOutputLocationForFail(file_lt4, "EISCD_BankCode(InvalidLength<4");
			if(result_InvalidLengthMore )
				isEISCD=validateOutputLocationForFail(file_gt4, "EISCD_BankCode(InvalidLength>4");
			if(result_NonNumeric )
				isEISCD=validateOutputLocationForFail(file_NN, "EISCD_BankCode(NonNumeric");

			}
			
			if(	isEISCD )
				new dynamicTestNGXml().resultPassToXl("test_104400","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_104400","Fail");
		
		}
		
		/*
		 * Test Case ID: 104694
		 * Test Description: Invalid SupervisoryBody - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_104694() throws Exception
		{	
			boolean isEISCD=false;
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_104694"));
			
			if(!file.exists())			
				validateAuditStatusFailMsg("EISCD-Invalid Supervisor Code", "eiscd_sb","_sb","'SupervisoryBody' element is invalid",1,null);
			
			isEISCD=validateOutputLocationForFail(file,"EISCD_InvalidSupervisoryBank");
			if(	isEISCD )
				new dynamicTestNGXml().resultPassToXl("test_104694","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_104694","Fail");
	
		}
		
		/*
		 * Test Case ID: 104794
		 * Test Description: EISCD_Input: Null Abbreviated Bank Name - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_104794() throws Exception
		{	
			boolean isEISCD=false;
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_104794"));
			
			if(!file.exists())			
				validateDBWhenSSISFails("EISCD-Null Abbreviated Bank Name", "eiscd_aBN", "query_CountOfAudit");
			
			isEISCD=validateOutputLocationForFail(file,"EISCD_EmptyAbbreviatedBankName");
			if(	isEISCD )
				new dynamicTestNGXml().resultPassToXl("test_104794","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_104794","Fail");
		
		}
		/*
		 * Test Case ID: 104795
		 * Test Description: EISCD_Input: Null Bank Name - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_104795() throws Exception
		{	
			boolean isEISCD=false;
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_104795"));
			
			if(!file.exists())			
				validateDBWhenSSISFails("EISCD-Null Bank Name", "eiscd_BN", "query_CountOfAudit");
			
			isEISCD=validateOutputLocationForFail(file,"EISCD_BankName");
			if(	isEISCD )
				new dynamicTestNGXml().resultPassToXl("test_104795","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_104795","Fail");
		
		}
		/*
		 * Test Case ID: 104797
		 * Test Description: EISCD_Input: Null Address - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_104797() throws Exception
		{	
			boolean isAddress = false;
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_104797"));		
			if(!file.exists())			
				isAddress = validateInboundCommonFiles("eiscd_address","query_address","EISCD-Null Address Sub Tags",expectedStatus_0,true,"eiscd_outputFilePath_104797","eiscd_input_FilePath_104797",false);
			
			if(isAddress)
			{
				isAddress=false;
				isAddress=validateEISCDTablesForPass(10,16,15,16,16, "BankOfficeAddress");
			}
			if(	isAddress )
				new dynamicTestNGXml().resultPassToXl("test_104797","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_104797","Fail");
	
		}
		
		/*
		 * Test Case ID: 104801
		 * Test Description: Null BankOffice - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_104801() throws Exception
		{	
			boolean isEISCD=false,isBO = false;
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_104801"));		
			if(!file.exists())
			{
				isBO = validateAuditStatusFailMsg("EISCD-Null Bank Office Type", "eiscd_BOempty","_BOempty","'BankOfficeType' element is invalid",1,null);
				if(isBO)isEISCD=validateOutputLocationForFail(file,"EISCD_CNCCCSettlementBank");
			}
			if(	isEISCD )
				new dynamicTestNGXml().resultPassToXl("test_104801","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_104801","Fail");
	
		}
		/*
		 * Test Case ID: 104853
		 * Test Description: EISCD_Input: EISCD Tag Value Validation - Invalid BankOffice - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_104853() throws Exception
		{	
			boolean isEISCD=false;
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_104853"));
			
			if(!file.exists())			
				validateAuditStatusFailMsg("EISCD-Invalid Bank Office", "eiscd_BO","_BO","'BankOfficeType' element is invalid",1,null);
			
			isEISCD=validateOutputLocationForFail(file,"EISCD_InvalidBankOfficeType");
			if(	isEISCD )
				new dynamicTestNGXml().resultPassToXl("test_104853","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_104853","Fail");
	
		}
		/*
		 * Test Case ID: 105051
		 * Test Description: EISCD_Input: EISCD Tag Value Validation - Null Chaps Sterling - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_105051() throws Exception
		{	
			boolean isCS = false,finalCS=false;
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105051"));		
			if(!file.exists())			
				isCS = validateInboundCommonFiles("eiscd_cS","query_cS","EISCD-Null Chaps Sterling Sub Tags",expectedStatus_0,true,"eiscd_outputFilePath_105051","eiscd_input_FilePath_105051",false);
						
			if(isCS)
			{
				finalCS=validateEISCDTablesForPass(10,16,16,16,16, "ChapsSterling");
			}
			if(	finalCS && isCS )
				new dynamicTestNGXml().resultPassToXl("test_105051","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105051","Fail");
	
		}
		
		/*
		 * Test Case ID: 105168
		 * Test Description: EISCD_Input: EISCD Tag Value Validation - Invalid CNCCC Status - Fail 
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_105168() throws Exception
		{	
			boolean isEISCD=false;
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105168"));
			
			if(!file.exists())			
				validateAuditStatusFailMsg("EISCD-Invalid CNCCC Status Code", "eiscd_ccStatus","_ccStatus","'Status' element is invalid",1,null);
			
			isEISCD=validateOutputLocationForFail(file,"EISCD_InvalidCNCCCStatus");
			if(	isEISCD  )
				new dynamicTestNGXml().resultPassToXl("test_105168","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105168","Fail");
	
		}
		

		/*
		 * Test Case ID: 105203
		 * Test Description: EISCD_Input: EISCD Tag Value Validation - Null CNCCC Return Indicator - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_105203() throws Exception
		{	
			boolean isReturn = false;
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105203"));		
			if(!file.exists())			
				isReturn = validateInboundCommonFiles("eiscd_ccReturn","query_ccReturn","EISCD-Null Return Indicator",expectedStatus_0,true,"eiscd_outputFilePath_105203","eiscd_input_FilePath_105203",false);
			
			if(isReturn)
			{
				isReturn = false;
				isReturn= validateEISCDTablesForPass(10,16,16,16,16, "CNCCC");
			}
			if(	isReturn )
				new dynamicTestNGXml().resultPassToXl("test_105203","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105203","Fail");
	
		}
		
		/*
		 * Test Case ID: 105206
		 * Test Description: EISCD_Input: EISCD Tag Value Validation - CNCCC Settlement Bank - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_105206() throws Exception
		{	
			boolean isEISCD=false;
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105206"));
			
			if(!file.exists())			
				//validateAuditStatusFailMsg("EISCD-Null Settlement Bank", "eiscd_ccSB","_ccSB","'SettlementBank' element is invalid",1,null);
				isEISCD = validateInboundCommonFiles("eiscd_ccSB","query_ccSB","EISCD-Null Settlement Bank",expectedStatus_0,true,"eiscd_outputFilePath_105206","eiscd_input_FilePath_105206",false);
			if(isEISCD)
				{
				isEISCD= false;
				isEISCD=validateEISCDTablesForPass(10,16,16,16,16, "EISCD_CNCCCSettlementBank");
				}
			
			//isEISCD=validateOutputLocationForFail(file,"EISCD_CNCCCSettlementBank");
			if(	isEISCD )
				new dynamicTestNGXml().resultPassToXl("test_105206","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105206","Fail");
	
		
		}
		
		/*
		 * Test Case ID: 105209
		 * Test Description: EISCD_Input: EISCD Tag Value Validation - NonNumeric/Invalid Length CNCCC SortCode - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_105209() throws Exception
		{	
			boolean isEISCD_nonnumeric=false,isEISCD_InvalidLessLength=false,isEISCD_InvalidMoreLength=false;
			boolean isEISCD_finalNN=false,isEISCD_finalLessLength=false,isEISCD_finalMoreLength=false;

			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105209"));
			
			if(!file.exists())			
				validateAuditStatusFailMsg("EISCD-Non-Numeric CNCCC SortCode ", "eiscd_ccDASC_NN","_ccDASC_NN","'DebitAgencySortCode' element is invalid",1,null);
			
			isEISCD_nonnumeric=validateOutputLocationForFail(file,"EISCD_InvalidCNCCCSortCode");
			if(isEISCD_nonnumeric)
				isEISCD_finalNN=validateEISCDTablesForPass(0,0,0,0,0, "All EISCD");
	
			
			EISCD_DBCleanup();
			File file_1 = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105209_1"));
			
			if(!file_1.exists())			
				validateAuditStatusFailMsg("EISCD-Invalid Length(<6) CNCCC SortCode ", "eiscd_ccDASC_lt","_ccDASC_lt","'DebitAgencySortCode' element is invalid",1,null);
			
			isEISCD_InvalidLessLength=validateOutputLocationForFail(file_1,"EISCD_InvalidCNCCCSortCode");
			if(isEISCD_InvalidLessLength)
				isEISCD_finalLessLength=validateEISCDTablesForPass(0,0,0,0,0, "All EISCD");
	
			EISCD_DBCleanup();
			File file_2 = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105209_2"));
			
			if(!file_2.exists())			
				validateAuditStatusFailMsg("EISCD-Invalid Length(>6) CNCCC SortCode ", "eiscd_ccDASC_gt","_ccDASC_gt","'DebitAgencySortCode' element is invalid",1,null);
			
			isEISCD_InvalidMoreLength=validateOutputLocationForFail(file_2,"EISCD_InvalidCNCCCSortCode");
			if(isEISCD_InvalidMoreLength)
				isEISCD_finalMoreLength=validateEISCDTablesForPass(0,0,0,0,0, "All EISCD");
			if(	isEISCD_finalNN && isEISCD_finalLessLength && isEISCD_finalMoreLength )
				new dynamicTestNGXml().resultPassToXl("test_105209","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105209","Fail");
	
	
		}
		
		
		
		/*
		 * Test Case ID: 105211
		 * Test Description: EISCD_Input: EISCD Tag Value Validation - Invalid GBNI Indicator - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-May-2017
		 * Comments:
		 */
		@Test
		public void test_105211() throws Exception
		{	
			boolean isEISCD=false;
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105211"));
			
			if(!file.exists())			
				validateAuditStatusFailMsg("EISCD-Invalid CNCCC GBNI Indicator ", "eiscd_ccGBNI","_ccGBNI","'GBNIIndicator' element is invalid",1,null);
			
			isEISCD=validateOutputLocationForFail(file,"EISCD_InvalidCNCCCGBNIIndicator");
			if(	isEISCD )
				new dynamicTestNGXml().resultPassToXl("test_105211","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105211","Fail");
	
		}

		/*
		 * Test Case ID: 105224
		 * Test Description: EISCD_Input: EISCD Tag Value Validation - Null/Non Numeric/Invalid Length Sort Code - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 22-May-2017
		 * Comments:
		 */
		@Test
		public void test_105224() throws Exception
		{	
			boolean isEISCD_nonnumeric=false,isEISCD_InvalidLessLength=false,isEISCD_InvalidMoreLength=false,isEISCD_null = false;
			boolean isEISCD_finalNN=false,isEISCD_finalLessLength=false,isEISCD_finalMoreLength=false,isEISCD_finalNull = false;

			
			EISCD_DBCleanup();
			File file_0 = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105224_0"));
			
			if(!file_0.exists())			
				validateAuditStatusFailMsg("EISCD - Null SortCode ", "eiscd_SC","_SC","The 'SortCode' attribute is invalid",1,null);
			
			isEISCD_null=validateOutputLocationForFail(file_0,"EISCD_SortCode");
			if(isEISCD_null)
				isEISCD_finalNull = validateEISCDTablesForPass(0,0,0,0,0, "All EISCD");
	
			
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105224"));
			
			if(!file.exists())			
				validateAuditStatusFailMsg("EISCD - Non-Numeric SortCode ", "eiscd_SC_NN","_SC_NN","The 'SortCode' attribute is invalid",1,null);
			
			isEISCD_nonnumeric=validateOutputLocationForFail(file,"EISCD_SortCode");
			if(isEISCD_nonnumeric)
				isEISCD_finalNN=validateEISCDTablesForPass(0,0,0,0,0, "All EISCD");
	
			
			EISCD_DBCleanup();
			File file_1 = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105224_1"));
			
			if(!file_1.exists())			
				validateAuditStatusFailMsg("EISCD-Invalid Length(<6) SortCode ", "eiscd_SC_lt","_SC_lt","The 'SortCode' attribute is invalid",1,null);
			
			isEISCD_InvalidLessLength=validateOutputLocationForFail(file_1,"EISCD_InvalidSortCode");
			if(isEISCD_InvalidLessLength)
				isEISCD_finalLessLength=validateEISCDTablesForPass(0,0,0,0,0, "All EISCD");
	
			EISCD_DBCleanup();
			File file_2 = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105224_2"));
			
			if(!file_2.exists())			
				validateAuditStatusFailMsg("EISCD-Invalid Length(>6) SortCode ", "eiscd_SC_gt","_SC_gt","The 'SortCode' attribute is invalid",1,null);
			
			isEISCD_InvalidMoreLength=validateOutputLocationForFail(file_2,"EISCD_InvalidSortCode");
			if(isEISCD_InvalidMoreLength)
				isEISCD_finalMoreLength=validateEISCDTablesForPass(0,0,0,0,0, "All EISCD");
	
		if(isEISCD_finalLessLength && isEISCD_finalMoreLength && isEISCD_finalNN && isEISCD_finalNull)
			new dynamicTestNGXml().resultPassToXl("test_105224","Pass");
		else
			new dynamicTestNGXml().resultPassToXl("test_105224","Fail");

		}
		/*
		 * Test Case ID: 105855
		 * Test Description: EISCD_Input: EISCD Tag Value Validation - Null Debit Agency Sort Code - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 23-May-2017
		 * Comments:
		 */
		@Test
		public void test_105855() throws Exception
		{
			boolean isEISCD=false,isDASC = false;
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			
			EISCD_DBCleanup();
			File file = deleteFilesInFilePath(getRefDataValueFromDataSheet("eiscd_outputFilePath_105855"));		
			if(!file.exists())			
				isDASC = validateInboundCommonFiles("eiscd_ccDASC_empty","query_DASC","EISCD-Null Debit Agency Sort Code",expectedStatus_0,true,"eiscd_outputFilePath_105855","eiscd_input_FilePath_105855",false);
			
			if(isDASC)
				isEISCD = validateEISCDTablesForPass(10,16,16,16,16, "SortCode");
			if(	isEISCD )
				new dynamicTestNGXml().resultPassToXl("test_105855","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105855","Fail");
	
		
		}
		/*
		 * Test Case ID: deleteScenario
		 * Test Description: EISCD_Input: EISCD Tag Value Validation - Insertion of records in DB Tables should not happen for invalid files
		 * Author: Hemapriya Shanmugam
		 * Date: 25-May-2017
		 * Comments:
		 */
		@Test
		public void deleteScenario() throws Exception
		{
			boolean isFile1,isEISCD=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));

			validateInboundCommonFiles("eiscd_del1","query_del1","EISCD-DELETE SCENARIO",expectedStatus_1,false,"","",false);
			isFile1=validateEISCDTablesForPass(5,10,10,10,10, "EISCD Valid");
			if(isFile1)
			{
				EISCD_DBBackup();
				validateAuditStatusFailMsg("EISCD-Invalid File", "eiscd_del2","_del2","'SupervisoryBody' element is invalid",1,null);
				isEISCD=EISCD_ValidateCountComparingWithBackup();
			}
			if(	isEISCD )
				new dynamicTestNGXml().resultPassToXl("deleteScenario","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("deleteScenario","Fail");
	
		}
		/*
		 * Test Case ID: test_88440
		 * Test Description: EISCD_Input: Business Calendar Outbound
		 * Author: Hemapriya Shanmugam
		 * Date: 30-May-2017
		 * Comments:
		 */
		@Test
		public void test_88440() throws Exception
		{
			//process cmd prompt
			boolean isBCal=false;
			if(referenceData_processingInputFile("bcal_out", "", "Business Calendar - OUTBOUND", 0, false, "bcal_outputFilePathName"))
				{
					isBCal=bcalOutboundValidation();
				
				}
			if(	isBCal )
				new dynamicTestNGXml().resultPassToXl("test_88440","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_88440","Fail");
	
			
		}
		
		/*
		 * Test Case ID: test_105267
		 * Test Description: EISCD_Input: Business Calendar Outbound
		 * Author: Hemapriya Shanmugam
		 * Date: 30-May-2017
		 * Comments:
		 */
		@Test
		public void test_105267() throws Exception
		{
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSC_DBCleanup();
			
			boolean result_Pipe=validateInboundCommonFiles("T_105267", "query_105267", "ERDMS Commercial - Pipe Delimiters", expectedStatus_success, false, "", "", false);
			ERDMSC_DBCleanup();
			boolean result_NoPipe=validateAuditStatusFailMsg("ERDMS_Commercial Without Pipe Delimiter","T_105267_1", "105267_NoPipe", "Footer is required",1,null);

			if(result_Pipe && result_NoPipe && validateERDMSCommTables(0, 0, 0, 0, 0, 0))	
				new dynamicTestNGXml().resultPassToXl("test_105267","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105267","Fail");	
		}
		/*
		 * Test Case ID: test_105268
		 * Test Description: ERDMS_Commercial: Date Format Wrong - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105268() throws Exception
		{
			String[] expectedStrings = new String[7];
			expectedStrings[0] = "Creation date : Date is invalid";
			expectedStrings[1] = "TPI date : Date is invalid";
			expectedStrings[2] = "TPI closure date : Date is invalid";
			expectedStrings[3] = "Aggregation effective date : Date is invalid";
			expectedStrings[4] = "HOCA effective date : Date is invalid";
			expectedStrings[5] = "Created date : Date is invalid";
			expectedStrings[6] = "Last updated date : Date is invalid";
			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial Invalid Date","T_105268", "105268_Date", "",expectedStrings.length,expectedStrings);

			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105268","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105268","Fail");	
		}
		
		/*
		 * Test Case ID: test_105268
		 * Test Description: ERDMS_Commercial: Date Format Wrong - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105271() throws Exception
		{
			ERDMSC_DBCleanup();
			boolean result_less=validateAuditStatusFailMsg("ERDMS_Commercial Less digits in CustomerID","T_105271", "105271_LessLengthCustomerID", "Customer ID : Length is invalid",1,null);
			boolean result_null=validateAuditStatusFailMsg("ERDMS_Commercial Null CustomerID","T_105271_2", "105271_WithoutCustomerID", "Last Updated Date : Value is required",1,null);
			if(result_less && result_null && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105271","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105271","Fail");	
		}
		
		/*
		 * Test Case ID: test_105272
		 * Test Description: ERDMS_Commercial: Invalid Calalogue ID - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105272() throws Exception
		{
			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial Invalid Catalogue ID","T_105272", "105272_CatalougeIDWrong", "Invalid Catalogue Id",1,null);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105272","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105272","Fail");	
		}
		/*
		 * Test Case ID: test_105274
		 * Test Description: ERDMS_Commercial: No header & footer - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105274() throws Exception
		{
			String expectedErrorMsg[] = new String[2];
			expectedErrorMsg[0]= "Header is required";
			expectedErrorMsg[1]= "Footer is required";

			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial without Header/Footer","T_105274", "105274_WithoutHeaderandFooter", "",expectedErrorMsg.length,expectedErrorMsg);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105274","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105274","Fail");	
		}
		
		/*
		 * Test Case ID: test_105275
		 * Test Description: ERDMS_Commercial: Invalid Header Record ID - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105275() throws Exception
		{
			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial invalid Header Record Id","T_105275", "105275-WrongHeaderRecordID", "Header is required",1,null);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105275","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105275","Fail");	
		}
		
		/*
		 * Test Case ID: test_105276
		 * Test Description: ERDMS_Commercial: No Pipe Delimiter in Header - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105276() throws Exception
		{
			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial Without Pipte delimiter in Header","T_105276", "105276-NoPipeInHeader", "Header is required",1,null);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105276","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105276","Fail");	
		}
		
		/*
		 * Test Case ID: test_105277
		 * Test Description: ERDMS_Commercial: Invalid Header Record ID - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105277() throws Exception
		{
			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial invalid Header Length","T_105277", "105277-MoreHeader", "Header is required",1,null);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105277","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105277","Fail");	
		}
		/*
		 * Test Case ID: test_105275
		 * Test Description: ERDMS_Commercial: Invalid Header Record ID - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105279() throws Exception
		{
			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial invalid Data Record Id","T_105279", "105279_DataRecordID", "Record Type invalid",1,null);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105279","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105279","Fail");	
		}
		/*
		 * Test Case ID: test_105280
		 * Test Description: ERDMS_Commercial: Invalid Header Record ID - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		
		@Test
		public void test_105280() throws Exception
		{
			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial With Only Optional Tags","T_105280", "105280_OnlyOptional", "Catalogue Id value is required",1,null);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105280","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105280","Fail");	
		}
		/*
		 * Test Case ID: test_105281
		 * Test Description: ERDMS_Commercial: Invalid Header Record ID - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105281() throws Exception
		{
			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial invalid Row Count","T_105281", "105281_wrongRecordCount", "Row Count mismatch",1,null);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105281","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105281","Fail");	
		}
		
		/*
		 * Test Case ID: test_105286
		 * Test Description: ERDMS_Commercial: Invalid Customer Account number - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105286() throws Exception
		{
			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial invalid Account Number","T_105286", "105286_NonNumericAC", "customer Account Number : Data type is invalid",1,null);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105286","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105286","Fail");	
		}
		/*
		 * Test Case ID: test_105287
		 * Test Description: ERDMS_Commercial: Invalid Customer ID - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105287() throws Exception
		{
			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial invalid Customer ID","T_105287", "105287_NonNumericIDs", "Customer ID : Data type is invalid",1,null);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105287","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105287","Fail");	
		}
		/*
		 * Test Case ID: test_105293
		 * Test Description: ERDMS_Commercial: NonNumeric Sort Code and Alternate Sort code- Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105293() throws Exception
		{
			String[] expectedStrings = new String[2];
			expectedStrings[0] = "Customer Sortcode : Data type is invalid";
			expectedStrings[1] = "Alternate Return SortCode : Data type is invalid";

			ERDMSC_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Commercial invalid Sort Code & Alternate Sort Code","T_105293", "105293_NonNumericSCandAltSC", "",2,expectedStrings);
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105293","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105293","Fail");	
		}
		/*
		 * Test Case ID: test_105284
		 * Test Description: ERDMS_Commercial: Invalid File Name in Command Line - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105284() throws Exception
		{
			boolean result=validateDBWhenSSISFails("ERDMS Commercial - Input File without extension in command line", "T_105284", "query_CountOfAudit");
			if(result && validateERDMSCommTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105284","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105284","Fail");	
		}
		/*
		 * Test Case ID: test_105269
		 * Test Description: ERDMS_Commercial: No Special Instructions ID - Pass without updating SI table
		 * Author: Hemapriya Shanmugam
		 * Date: 05-Jun-2017
		 * Comments:
		 */
		@Test
		public void test_105269_105270() throws Exception
		{
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSC_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105269", "query_105269", "ERDMS Commercial - Without Special Instructions", expectedStatus_success, false, "", "", false);
			if(result && validateERDMSCommTables(3, 0, 0, 1, 1, 1))
				new dynamicTestNGXml().resultPassToXl("test_105269_105270","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105269_105270","Fail");	
		}

		/*
		 * Test Case ID: test_105278
		 * Test Description: ERDMS_Commercial: Only Mandatory fields
		 * Author: Hemapriya Shanmugam
		 * Date: 06-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 06-Jun-2017
		 */
		@Test
		public void test_105278() throws Exception
		{
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSC_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105278", "query_105278", "ERDMS Commercial - Only Mandatory fields", expectedStatus_success, false, "", "", false);
			if(result && validateERDMSCommTables(3, 0, 0, 0, 1, 1))
				new dynamicTestNGXml().resultPassToXl("test_105278","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105278","Fail");	
		}

		/*
		 * Test Case ID: test_105273
		 * Test Description: ERDMS_Commercial: Presence of ParticipandId column in all ERDMS Commercial tables in DB
		 * Author: Hemapriya Shanmugam
		 * Date: 06-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 06-Jun-2017
		 */
		@Test
		public void test_105273() throws Exception
		{
			if(validateColumnInTable(getRefDataValueFromDataSheet("105273_SourceCol"),getRefDataValueFromDataSheet("105273_SourceTab")) && 
					validateColumnInTable(getRefDataValueFromDataSheet("105273_CommSpCol"),getRefDataValueFromDataSheet("105273_CommSpTab")) && 
					validateColumnInTable(getRefDataValueFromDataSheet("105273_CommSpACol"),getRefDataValueFromDataSheet("105273_CommSpATab")) && 
					validateColumnInTable(getRefDataValueFromDataSheet("105273_CommTpiCol"),getRefDataValueFromDataSheet("105273_CommTpiTab")) && 
					validateColumnInTable(getRefDataValueFromDataSheet("105273_CommHocaCol"),getRefDataValueFromDataSheet("105273_CommHocaTab")))
				new dynamicTestNGXml().resultPassToXl("test_105273","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105273","Fail");	
		
		}
		/*
		 * Test Case ID: test_105285
		 * Test Description: ERDMS_Commercial: Special Instruction fields DB validation
		 * Author: Hemapriya Shanmugam
		 * Date: 07-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 07-Jun-2017
		 */
		@Test
		public void test_105285() throws Exception
		{
			boolean resultDB = false;
			//delete tables & process file - pass
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSC_DBCleanup();
			isValidERDMSC=validateInboundCommonFiles("T_105285", "query_105285", "ERDMS Commercial - Valid For Special Instruction", expectedStatus_success, false, "", "", false);
			if(isValidERDMSC)
			{
				//Get Value from File
				String[] dataContents=getStringFromDelimitedFile("erdmsC_105285","D","|"); 
				
				//Get value from DB and compare
				 resultDB = validateERDMSComm_SIWithDB(dataContents, "Commercial", true);
			}
			if(resultDB)
				new dynamicTestNGXml().resultPassToXl("test_105285","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105285","Fail");						
		}
		/*
		 * Test Case ID: test_105289
		 * Test Description: ERDMS_Commercial: HOCA fields DB validation
		 * Author: Hemapriya Shanmugam
		 * Date: 07-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105289() throws Exception
		{
			boolean resultDB = false;
			//delete tables & process file - pass
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));	
			ERDMSC_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105289", "query_105289", "ERDMS Commercial - Valid For HOCA", expectedStatus_success, false, "", "", false);
			
			if(result)
			{
				//Get Value from File
				String[] dataContents=getStringFromDelimitedFile("erdmsC_105285","D","|"); 		
				//Get value from DB and compare
				resultDB = validateERDMSCommon_HOCAWithDB(dataContents,"Commercial");
			}
			if(resultDB)
				new dynamicTestNGXml().resultPassToXl("test_105289","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105289","Fail");						
		}
		
		/*
		 * Test Case ID: test_105290
		 * Test Description: ERDMS_Commercial: HOCA fields DB validation
		 * Author: Hemapriya Shanmugam
		 * Date: 07-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 07-Jun-2017
		 */
		@Test
		public void test_105290() throws Exception
		{
			boolean resultDB = false;
			//delete tables & process file - pass
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			//The Valid file will be processed by TC: 105285 and iff 105285 was not executed for testing then the same valid file gets processed for this TC
			ERDMSC_DBCleanup();
				boolean result=validateInboundCommonFiles("T_105290", "query_105290", "ERDMS Commercial - Valid For TPI", expectedStatus_success, false, "", "", false);
				if(result)
				{
					//Get Value from File
					String[] dataContents=getStringFromDelimitedFile("erdmsC_105285","D","|"); 
					
					//Get value from DB and compare
					resultDB = validateERDMSComm_TPIWithDB(dataContents);
				}
			if(resultDB)
				new dynamicTestNGXml().resultPassToXl("test_105290","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105290","Fail");						
		}
		/*
		 * Test Case ID: test_105291
		 * Test Description: ERDMS_Commercial: HOCA fields DB validation
		 * Author: Hemapriya Shanmugam
		 * Date: 08-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 08-Jun-2017
		 */
		@Test
		public void test_105291() throws Exception
		{
			boolean resultDB = false;
			//delete tables & process file - pass
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			//The Valid file will be processed by TC: 105285 and iff 105285 was not executed for testing then the same valid file gets processed for this TC

				ERDMSC_DBCleanup();
				boolean result=validateInboundCommonFiles("T_105291", "query_105291", "ERDMS Commercial - Valid For Customer", expectedStatus_success, false, "", "", false);
			
			if(result)
			{
				String[] dataContents=getStringFromDelimitedFile("erdmsC_105285","D","|"); 			
				//Get value from DB and compare
				 resultDB = validateERDMSComm_CustomerWithDB(dataContents);
			}
			
			if(resultDB)
				new dynamicTestNGXml().resultPassToXl("test_105291","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105291","Fail");						
		}
		/*
		 * Test Case ID: test_CommTrailer
		 * Test Description: ERDMS_Commercial: Trailer Count Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 21-July-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-July-2017
		 */
		@Test
		public void test_CommTrailer() throws Exception 
		{
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSC_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Commercial With Null Trailer Count","CommTrailer", "CommTrailer_TrailerCountNull", "Row Count : Value is required",1,null);
			validateERDMSCommTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSC_DBCleanup();
			boolean result_lengthLess=validateInboundCommonFiles("CommTrailer_1", "query_commTrailer", "ERDMS_Commercial With Length Of Trailer Count  < 8", expectedStatus, false, "", "", false);

			validateERDMSCommTables(3, 1, 1, 1, 1, 1); 
			
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Commercial With Length Of Trailer Count > 8","CommTrailer_2", "CommTrailer_TrailerCountMore", "row count : Length is invalid",1,null);
			validateERDMSCommTables(3, 1, 1, 1, 1, 1); 
			
			boolean result_Incorrect=validateAuditStatusFailMsg("ERDMS_Commercial With Incorrect Trailer Count ","CommTrailer_4", "CommTrailer_TrailerCountIncorrect", "Row Count mismatch",1,null);
			validateERDMSCommTables(3, 1, 1, 1, 1, 1); 
			
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Commercial With Non-Numeric Value in Trailer Count","CommTrailer_3", "CommTrailer_TrailerCountNN", "Row Count : Data type is invalid",1,null);
					
			if( result_Invalid  && result_lengthLess && result_lengthMore  && result_NN && result_Incorrect && validateERDMSCommTables(3, 1, 1, 1, 1, 1))
				new dynamicTestNGXml().resultPassToXl("test_CommTrailer","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_CommTrailer","Fail");			
		}
		
		/*
		 * Test Case ID: test_105182
		 * Test Description: ERDMS_Agency: Header Record ID Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 12-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 12-Jun-2017
		 */
		@Test
		public void test_105182() throws Exception
		{
			String expectedString = "Header is required";
			ERDMSA_DBCleanup();
			boolean result_NC=validateAuditStatusFailMsg("ERDMS_Agency With Numeric Header Record ID","T_105182", "105182_NoCharHeader", expectedString,1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			ERDMSA_DBCleanup();
			boolean result_Null=validateAuditStatusFailMsg("ERDMS_Agency With Null Header Record ID","T_105182_1", "105182_NoHeaderRecordID", expectedString,1,null);
			if(result_NC && result_Null && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105182","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105182","Fail");			
		}
		
		/*
		 * Test Case ID: test_105191
		 * Test Description: ERDMS_Agency: Date in Header - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 12-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 12-Jun-2017
		 */
		@Test
		public void test_105191() throws Exception
		{
			String expectedErrorMsg[] = new String[1];
			expectedErrorMsg[0]= "Creation Date : Date is invalid";
			//expectedErrorMsg[1]= "Footer is required";

			ERDMSA_DBCleanup();
			boolean result_invalid=validateAuditStatusFailMsg("ERDMS_Agency With Date in Header","T_105191", "105191_HDate", "Creation Date : Date is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Lesser Length in Date in Header","T_105191_1", "105191_HDateLength", "",expectedErrorMsg.length,expectedErrorMsg);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Greater Length in Date in Header","T_105191_2", "105191_HDateLengthMore", "Invalid creation date",1,null);
			
			
			if(result_invalid && result_lengthMore && result_lengthLess && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105191","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105191","Fail");			
		}
		
		/*
		 * Test Case ID: test_105193
		 * Test Description: ERDMS_Agency: Data Record Id Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 12-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 12-Jun-2017
		 */
		@Test
		public void test_105193() throws Exception
		{
			String expectedErrorMsg[] = new String[2];
			expectedErrorMsg[0]= "Record Type invalid";
			expectedErrorMsg[1]= "row count miss match";

			ERDMSA_DBCleanup();
			boolean result_Null=validateAuditStatusFailMsg("ERDMS_Agency With Invalid Data Record ID","T_105193", "105193_DataRecordIdNoChar", "",expectedErrorMsg.length,expectedErrorMsg);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Empty Data Record ID","T_105193_1", "105193_DataRecordIdNull", "",expectedErrorMsg.length,expectedErrorMsg);

			if(result_Null && result_lengthMore &&  validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105193","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105193","Fail");			
		}
		
		/*
		 * Test Case ID: test_105195
		 * Test Description: ERDMS_Agency: Catalogue Id Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 13-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 13-Jun-2017
		 */
		@Test
		public void test_105195() throws Exception
		{
			String expectedErrorMsg[] = new String[2];
			expectedErrorMsg[0]= "Catalogue ID has invalid value";
			expectedErrorMsg[1]= "catalogueID : Length is invalid";

			ERDMSA_DBCleanup();
			boolean result_Null=validateAuditStatusFailMsg("ERDMS_Agency With Numeric Cataogue ID","T_105195_3", "105195_NoCharCatalougeID", expectedErrorMsg[0],1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Catalogue ID < 2","T_105195", "105195_CatalogueIdLengthLess", "",expectedErrorMsg.length,expectedErrorMsg);

			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Catalogue ID > 2","T_105195_1", "105195_CatalogueIdLengthMore", "",expectedErrorMsg.length,expectedErrorMsg);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_InvalidEnum=validateAuditStatusFailMsg("ERDMS_Agency With Invalid Enum Values","T_105195_2", "105195_InvalidEnumCatalougeID", expectedErrorMsg[0],1,null);

			
			if(result_Null && result_lengthLess && result_lengthMore && result_InvalidEnum && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105195","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105195","Fail");			
		}
		/*
		 * Test Case ID: test_105196
		 * Test Description: ERDMS_Agency: Agency Id Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 13-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 13-Jun-2017
		 */
		@Test
		public void test_105196() throws Exception
		{
			ERDMSA_DBCleanup();
			boolean result_Null=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Agency ID","T_105196_2", "105196_NNAgencyID", "agencyID : Data type is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency ID < 2","T_105196", "105196_AgencyIDLengthLess", "agencyID : Length is invalid",1,null);

			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency ID > 2","T_105196_1", "105196_AgencyIDLengthMore", "agencyID : Length is invalid",1,null);
			
			
			if(result_Null && result_lengthLess && result_lengthMore  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105196","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105196","Fail");			
		}
		/*
		 * Test Case ID: test_105198
		 * Test Description: ERDMS_Agency: Agency Name Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 13-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 14-Jun-2017
		 */
		@Test
		public void test_105198() throws Exception 
		{	
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			
			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateInboundCommonFiles("T_105198","query_105198","ERDMS_Agency With Length Of Agency Name < 40", expectedStatus_0,false,"","",false);

			validateERDMSAgencyTables(3, 1, 1, 1, 1, 1);
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency Name > 40","T_105198_1", "105198_AgentNameLengthMore", "agencyBankName : Length is invalid",1,null);
			
			if( result_lengthLess && result_lengthMore  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105198","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105198","Fail");			
		}
		
		/*
		 * Test Case ID: test_105200
		 * Test Description: ERDMS_Agency: Aggregation Effective Date Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 14-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jun-2017
		 */
		@Test
		public void test_105200() throws Exception 
		{
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Invalid Aggregation Effective Date","T_105200", "105200_AggDateInvalid", "aggregationEffectiveDate : Date is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);		

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Aggregation Effective Date < 8","T_105200_1", "105200_AggDateLengthLess", "aggregationEffectiveDate : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Aggregation Effective Date > 8","T_105200_2", "105200_AggDateLengthMore", "aggregationEffectiveDate : Length is invalid",1,null);	
			
			if( result_Invalid && result_lengthLess && result_lengthMore  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105200","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105200","Fail");			
		}

		
		/*
		 * Test Case ID: test_105202
		 * Test Description: ERDMS_Agency: AFT Subscription Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 14-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 14-Jun-2017
		 */
		@Test
		public void test_105202() throws Exception 
		{
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of AFT Subscription > 1","T_105202_1", "105202_AFTSubLengthmore", "isaftsubscription : Length is invalid",1,null);

			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_lengthNull=validateAuditStatusFailMsg("ERDMS_Agency With Empty AFT Subscription","T_105202_2", "105202_AFTSubLengthNull", "isaftsubscription : Value is required",1,null);
			
			
			if( result_lengthMore && result_lengthNull  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105202","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105202","Fail");			
		}
		/*
		 * Test Case ID: test_105204
		 * Test Description: ERDMS_Agency: Authorization Workflow Required Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 14-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jun-2017
		 */
		@Test
		public void test_105204() throws Exception 
		{
			String[] errMsg = new String[2];
			errMsg[0] = "isAuthorisatonWorkflowRequired : Value is required";
			errMsg[1] = "isAuthorisatonWorkflowRequired : Length is invalid";
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Authorization Workflow Required > 1","T_105204_1", "105204_AuthorizationLengthMore", errMsg[1],1,null);

			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_lengthNull=validateAuditStatusFailMsg("ERDMS_Agency With Empty Authorization Workflow Required","T_105204_2", "105204_AuthorizationNull", "",errMsg.length,errMsg);
			
			
			if(  result_lengthMore && result_lengthNull  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105204","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105204","Fail");			
		}

		/*
		 * Test Case ID: test_105210
		 * Test Description: ERDMS_Agency: Agency Branch Name Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 14-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 14-Jun-2017
		 */
		@Test
		public void test_105210() throws Exception 
		{
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Agency Branch Name","T_105210", "105210_AgencyBranchNameLengthNull", "agencyBranchName : Value is required",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateInboundCommonFiles("T_105210_1","query_105210","ERDMS_Agency With Length Of Agency Branch Name < 40", expectedStatus_0,false,"","",false);

			validateERDMSAgencyTables(3, 1, 1, 1, 1, 1); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency Branch Name > 40 ","T_105210_2", "105210_AgencyBranchNameLengthMore", "agencyBranchName : Length is invalid",1,null);
			
			
			if( result_Invalid && result_lengthLess && result_lengthMore  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105210","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105210","Fail");			
		}
		
		/*
		 * Test Case ID: test_105213
		 * Test Description: ERDMS_Agency: Agency Sort Code Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 14-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 14-Jun-2017
		 */
		@Test
		public void test_105213() throws Exception 
		{
			String[] errorMsg = new String[2];
			errorMsg[0] = "agencyBranchSortCode : Length is invalid";
			errorMsg[1] = "agencyBranchSortCode : Value is required";
			
			
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Agency Sort Code","T_105213", "105213_AgencySCNull", "",2,errorMsg);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency Sort Code < 6","T_105213_1", "105213_AgencySCLengthLess", errorMsg[0],1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency Sort Code > 6 ","T_105213_2", "105213_AgencySCLengthMore", errorMsg[0],1,null);
			
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			ERDMSA_DBCleanup();
			boolean result_lengthNN=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency Sort Code > 6 ","T_105213_3", "105213_AgencySCNN", " ",1,null);
			
			
			if( result_Invalid && result_lengthLess && result_lengthMore  && result_lengthNN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105213","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105213","Fail");			
		}
		
		/*
		 * Test Case ID: test_105216
		 * Test Description: ERDMS_Agency: Agency Branch Name Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 14-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 14-Jun-2017
		 */
		@Test
		public void test_105216() throws Exception 
		{
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Agency Participation ID","T_105216", "105216_ParticipationIdNull", "agencyParticipationID : Value is required",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

		/*	ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency Participation ID < 10 ","105216_1", "105216_ParticiaptionIdLess", "agencyParticipationID : Length is invalid",1,null);

			if(result_lengthLess)validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); */
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency Participation ID > 10 ","T_105216_2", "105216_ParticipationIdMore", "agencyParticipationID : Length is invalid",1,null);
			
			
			if( result_Invalid  && result_lengthMore  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))//&& result_lengthLess
				new dynamicTestNGXml().resultPassToXl("test_105216","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105216","Fail");			
		}
		
		/*
		 * Test Case ID: test_105217
		 * Test Description: ERDMS_Agency: Agency Branch Name Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jun-2017
		 */
		@Test
		public void test_105217() throws Exception 
		{
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Agency Scheme Code","T_105217", "105217_AgSchemeCodeNull", " ",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)		
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency Scheme Code > 10 ","T_105217_2", "105217_AgSchemeCodeLengthMore", " ",1,null);
			
			
			if( result_Invalid  && result_lengthMore  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0)) //&& result_lengthLess
				new dynamicTestNGXml().resultPassToXl("test_105217","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105217","Fail");			
		}
		/*
		 * Test Case ID: test_105218
		 * Test Description: ERDMS_Agency: Agency Branch Payment Scheme Description Code Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jun-2017
		 */
		@Test
		public void test_105219() throws Exception 
		{
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Agency Branch Payment Scheme Description Code","T_105219", "105219_AgPaySchemeNULL", "agencyBranchPaymentSchemeDescription : Value is required",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateInboundCommonFiles("T_105219_1","query_105219","ERDMS_Agency With Length Of Agency Scheme Code < 40 ", expectedStatus_0,false,"","",false);

			validateERDMSAgencyTables(3, 1, 1, 1, 1, 1); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Agency Scheme Code > 40 ","T_105219_2", "105219_AgPaySchemeMore", "agencyBranchPaymentSchemeDescription : Length is invalid",1,null);
			
			
			if( result_Invalid && result_lengthLess && result_lengthMore  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105219","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105219","Fail");			
		}
		/*
		 * Test Case ID: test_105220
		 * Test Description: ERDMS_Agency: Participation Status Code Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 14-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jun-2017
		 */
		@Test
		public void test_105220() throws Exception 
		{
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Participation Status Code","T_105220", "105220_PartStatusCodeNull", "agencyParticipationStatusCode : Value is required",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Participation Status Code > 8 ","T_105220_2", "105220_PartStatusCodeMore", "agencyParticipationStatusCode : Length is invalid",1,null);
			
			
			if( result_Invalid  && result_lengthMore  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105220","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105220","Fail");			
		}

		/*
		 * Test Case ID: test_105221
		 * Test Description: ERDMS_Agency: Settlement Sort Code Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jun-2017
		 */
		@Test
		public void test_105221() throws Exception 
		{
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Settlement Sort Code","T_105221", "105221_SettleSCNull", "settlementSortCode : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Settlement Sort Code  < 6","T_105221_1", "105221_SettleSCLess", "settlementSortCode : Length is invalid",1,null);

			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Settlement Sort Code > 6","T_105221_2", "105221_SettleSCMore", "settlementSortCode : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Value in Settlement Sort Code","T_105221_3", "105221_SettleSCNN", "settlementSortCode : Data type is invalid",1,null);
			
			
			if( result_Invalid && result_lengthLess && result_lengthMore  && result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105221","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105221","Fail");			
		}
		
		/*
		 * Test Case ID: test_105222
		 * Test Description: ERDMS_Agency: Settlement Account Number Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jun-2017
		 */
		@Test
		public void test_105222() throws Exception 
		{
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Settlement Account Number","T_105222", "105222_AcNumNull", "settlementAccountNumber : Value is required",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Settlement Account Numbere  < 8","T_105222_1", "105222_AcNumLengthLess", "settlementAccountNumber : Length is invalid",1,null);

			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Settlement Account Number > 8","T_105222_2", "105222_AcNumLengthMore", "settlementAccountNumber : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Value in Settlement Account Number","T_105222_3", "105222_AcNumNN", "settlementAccountNumber : Data type is invalid",1,null);
			
			
			if( result_Invalid && result_lengthLess && result_lengthMore  && result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105222","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105222","Fail");			
		}

		/*
		 * Test Case ID: test_105226
		 * Test Description: ERDMS_Agency: Is Aggregation Required Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105225() throws Exception 
		{
			String errMsg[] = new String[2];
			errMsg[0] = "isBranchAggregationRequired : Length is invalid";
			errMsg[1] = "isBranchAggregationRequired : Value is required";
					
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Aggregation Required","T_105225", "105225_AggReqdNull", "",2,errMsg);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Aggregation Required  > 1","T_105225_1", "105225_AggReqdLengthMore", errMsg[0],1,null);

			if( result_Invalid && result_lengthMore  && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105225","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105225","Fail");			
		}
		
		/*
		 * Test Case ID: test_105226
		 * Test Description: ERDMS_Agency: Charging Account Sort Code Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105226() throws Exception 
		{
			String errMsg[] = new String[2];
			errMsg[0] = "chargingSortCode : Length is invalid";
			errMsg[1] = "chargingSortCode : Value is required";
			
			
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Charging Sort Code","T_105226", "105226_CASCNull", "",2,errMsg);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Charging Sort Code  < 6","T_105226_1", "105226_CASCLengthLess", errMsg[0],1,null);

			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Charging Sort Code > 6","T_105226_2", "105226_CASCLengthMore", errMsg[0],1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Value in Charging Sort Code","T_105226_3", "105226_CASCNN", "chargingSortCode : Data type is invalid",1,null);
			
			
			if( result_Invalid && result_lengthLess && result_lengthMore  && result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105226","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105226","Fail");			
		}
		
		/*
		 * Test Case ID: test_105222
		 * Test Description: ERDMS_Agency: Charging Account Account Number Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 15-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105227() throws Exception 
		{
			String errMsg[] = new String[2];
			errMsg[0] = "chargingAccountNumber : Length is invalid";
			errMsg[1] = "chargingAccountNumber : Value is required";
			
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Charging Account Number","T_105227", "105227_CAACNull", "",2,errMsg);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Charging Account Numbere  < 8","T_105227_1", "105227_CAACLengthLess", errMsg[0],1,null);

			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Charging Account Number > 8","T_105227_2", "105227_CAACLengthMore", "chargingAccountNumber : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Value in Charging Account Number","T_105227_3", "105227_CAACNN", "chargingAccountNumber : Data type is invalid",1,null);
			
			
			if( result_Invalid && result_lengthLess && result_lengthMore  && result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105227","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105227","Fail");			
		}
		/*
		 * Test Case ID: test_105259
		 * Test Description: ERDMS_Agency: Created Date Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 16-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105259() throws Exception 
		{
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Created Date","T_105259", "105259_CreatedDateNull", "Created Date : Value is required",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Created Date  < 8","T_105259_1", "105259_CreatedDateLess", "Created Date : Length is invalid",1,null);

			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Created Date > 8","T_105259_2", "105259_CreatedDateMore", "Created Date : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Value in Created Date","T_105259_3", "105259_CreatedDateInvalid", "Created Date : Date is invalid",1,null);
			
			
			if( result_Invalid && result_lengthLess && result_lengthMore  && result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105259","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105259","Fail");			
		}
		
		/*
		 * Test Case ID: test_105260
		 * Test Description: ERDMS_Agency: Last Updated Date Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 16-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105260() throws Exception 
		{
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Last Updated Date","T_105260", "105260_LastDateNull", "Last Updated Date : Value is required",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Last Updated Date  < 8","T_105260_1", "105260_LastDateLess", "Last Updated Date : Length is invalid",1,null);

			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Last Updated Date > 8","T_105260_2", "105260_LastDateMore", "Last Updated Date : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Value in Last Updated Date","T_105260_3", "105260_LastDateInvalid", "Last Updated Date : Date is invalid",1,null);
			
			
			if( result_Invalid && result_lengthLess && result_lengthMore  && result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105260","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105260","Fail");			
		}
		
		/*
		 * Test Case ID: test_105261
		 * Test Description: ERDMS_Agency: Trailer Record ID Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 16-Jun-2017
		 * Comments: -- NULL SCENARIO
		 */
		@Test
		public void test_105261() throws Exception 
		{
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Trailer Record ID","T_105261", "105261_TRailerRecordNull", "Record Type invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Trailer Record ID > 1","T_105261_1", "105261_TrailerRecordMore", "Record Type invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Value in Trailer Record ID","T_105261_2", "105261_TrailerRecordIEnumInvalid", "Record Type invalid",1,null);
			
			
			if( result_Invalid && result_lengthMore  && result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105261","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105261","Fail");			
		}
		
		/*
		 * Test Case ID: test_105262
		 * Test Description: ERDMS_Agency: Trailer Count Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 16-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105262() throws Exception 
		{
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSA_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Agency With Null Trailer Count","T_105262", "105262_TrailerCountNull", "recordCount : Value is required",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); //(3, 1, 0, 0, 0, 0)

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateInboundCommonFiles("T_105262_1", "query_105262_1", "ERDMS Agency With Trailer Count < 8", expectedStatus, false, "", "", false);//validateAuditStatusFailMsg("ERDMS_Agency With Length Of Trailer Count  < 8","105262_1", "105262_TrailerCountLess", "row count mismatched",1,null);

			validateERDMSAgencyTables(3, 1, 1, 1, 1, 1); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of Trailer Count > 8","T_105262_2", "105262_TrailerCountMore", "recordCount : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_Incorrect=validateAuditStatusFailMsg("ERDMS_Agency With Incorrect Trailer Count ","T_105262_4", "105262_TrailerCountIncorrect", "row count miss match",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Value in Trailer Count","T_105262_3", "105262_TrailerCountNN", "row Count : Data type is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0);
			
			if( result_Invalid  && result_lengthLess && result_lengthMore  && result_NN && result_Incorrect)//&& result_lengthLess
				new dynamicTestNGXml().resultPassToXl("test_105262","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105262","Fail");		
		}
		/*
		 * Test Case ID: test_105230
		 * Test Description: ERDMS_Agency: Trailer Count Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105230() throws Exception
		{

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of HOCA SortCode < 6","T_105230", "105230_HOCASCLess", "hocasortCode : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of HOCA SortCode> 6","T_105230_1", "105230_HOCASCMore", "hocasortCode : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Value in HOCA SortCode","T_105230_2", "105230_HOCASCNN", " ",1,null);
			
			
			if( result_lengthLess && result_lengthMore  && result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105230","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105230","Fail");			
		
		}
		/*
		 * Test Case ID: test_105233
		 * Test Description: ERDMS_Agency: HOCA Account Number Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105233() throws Exception
		{

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of HOCA AccountNumber < 6","T_105233", "105233_HOCAACLess", "hoccaccountNumber : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of HOCA AccountNumber> 6","T_105233_1", "105233_HOCAACMore", "hoccaccountNumber : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Non-Numeric Value in HOCA AccountNumber","T_105233_2", "105233_HOCAACNN", "hoccaccountNumber : Data type is invalid",1,null);
			
			
			if( result_lengthLess && result_lengthMore  && result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105233","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105233","Fail");			
		
		}
		/*
		 * Test Case ID: test_105236
		 * Test Description: ERDMS_Agency: HOCA Effective Date Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105236() throws Exception
		{

			ERDMSA_DBCleanup();
			boolean result_lengthLess=validateAuditStatusFailMsg("ERDMS_Agency With Length Of HOCA Effective Date < 6","T_105236", "105236_HOCAEDateLess", "Hoca Effective Date : Length is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Agency With Length Of HOCA Effective Date> 6","T_105236_1", "105236_HOCAEDateMore", "Hoca Effective Date : Data type is invalid",1,null);
			validateERDMSAgencyTables(0, 0, 0, 0, 0, 0); 
			
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Invalid Date Format in HOCA Effective Date","T_105236_2", "105236_HOCAEDateInvalid", "Hoca Effective Date : Date is invalid",1,null);
			
			
			if( result_lengthLess && result_lengthMore  && result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105236","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105236","Fail");			
		
		}
		
		
		/*
		 * Test Case ID: test_105237
		 * Test Description: ERDMS_Agency: Business Line Owner Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105237() throws Exception
		{
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Length of Business Line Owner Value > 2","T_105237", "105237_HOCALOMore", "businessLineOwner : Length is invalid",1,null);
			
			if(  result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105237","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105237","Fail");			
		
		}

		/*
		 * Test Case ID: test_105238
		 * Test Description: ERDMS_Agency: Credit Extract Type Invalid - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105238() throws Exception
		{
			ERDMSA_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Agency With Length of Credit Extract Type Value > 10","T_105238", "105238_HOCACETMore", "creditExtractType : Length is invalid",1,null);
			
			if(  result_NN && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105238","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105238","Fail");			
		
		}
		/*
		 * Test Case ID: test_105300
		 * Test Description: ERDMS_Agency: No Optional Tags Valid - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 20-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105300() throws Exception
		{
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSA_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105300","query_105300","ERDMS_Agency With No optional Tags", expectedStatus_0,false,"","",false); 
			
			if( result && validateERDMSAgencyTables(3, 1, 1, 1, 1, 1))
				new dynamicTestNGXml().resultPassToXl("test_105300","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105300","Fail");				
		}
		
		/*
		 * Test Case ID: test_105295
		 * Test Description: ERDMS_Agency: Null in All Mandatory Fields - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 21-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105295() throws Exception
		{
			String[] errMsg = new String[3];
			errMsg[0] = "Error at Line  : 1, Header is required";
			errMsg[1] = "Error at Line  : 2, Record Type invalid";
			errMsg[2] = "Error at Line  : 3, Record Type invalid";

			ERDMSA_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Agency With Null in All Mandatory Fields","T_105295", "105295", "",errMsg.length,errMsg);
			
			if( result && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105295","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105295","Fail");				
		}
		
		/*
		 * Test Case ID: test_105338
		 * Test Description: ERDMS_Agency: Input File Name in Command Line with no extension while executing SSIS Package - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 21-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105338() throws Exception
		{
			ERDMSA_DBCleanup();
			boolean result=validateDBWhenSSISFails("ERDMS Agency - Input File without extension in command line", "T_105338", "query_CountOfAudit");
					
			if( result && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105338","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105338","Fail");				
		}
		/*
		 * Test Case ID: test_105340
		 * Test Description: ERDMS_Agency: No Optional Tags Valid - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 21-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jun-2017
		 */
		@Test
		public void test_105340() throws Exception
		{
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSA_DBCleanup();
			validateInboundCommonFiles("T_105340","query_105340","ERDMS_Agency Valid File", expectedStatus_0,false,"","",false); 
			
			if( validateColumnInTable("ParticipantID","base.SourceAgency") && validateColumnInTable("ParticipantID","base.Agency") && validateColumnInTable("ParticipantID","base.AgencyBranch") && validateColumnInTable("ParticipantID","base.SettlementAccount") && validateColumnInTable("ParticipantID","base.ChargingAccount") && validateColumnInTable("ParticipantID","base.HOCA") )
				new dynamicTestNGXml().resultPassToXl("test_105340","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105340","Fail");				
		}
		/*
		 * Test Case ID: test_106061
		 * Test Description: ERDMS_Agency: No Optional Tags Valid - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 22-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 22-Jun-2017
		 */
		@Test
		public void test_106061() throws Exception
		{
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSA_DBCleanup();
			boolean result_pipe = validateInboundCommonFiles("T_106061_1","query_106061","ERDMS_Agency Valid File", expectedStatus_0,false,"","",false); 	
			validateERDMSAgencyTables(3, 1, 1, 1, 1, 1);
			
			boolean result=validateAuditStatusFailMsg("ERDMS Agency - No Pipe delimitation in the Valid File","T_106061", "106061.txt", "Header is required",1,null);
					
			if( result_pipe && result && validateERDMSAgencyTables(3, 1, 1, 1, 1, 1))
				new dynamicTestNGXml().resultPassToXl("test_106061","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_106061","Fail");				
		}
		/*
		 * Test Case ID: test_108878
		 * Test Description: ERDMS_Agency: Null in All Mandatory Fields - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 21-Jun-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Ju -2017
		 */
		@Test
		public void test_108878() throws Exception
		{

			ERDMSA_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Agency With HOCA Settlement SortCode and Settlement AccountNumber","T_108878", "108878", " ",1,null);
			
			if( result && validateERDMSAgencyTables(0, 0, 0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_108878","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_108878","Fail");				
		}

		/*
		 * Test Case ID: test_105331
		 * Test Description: ERDMS_Agency: HOCA fields DB validation
		 * Author: Hemapriya Shanmugam
		 * Date: 21-July-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jul-2017
		 */
		@Test
		public void test_105331() throws Exception
		{
			boolean resultDB = false;
			//delete tables & process file - pass
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));	
			ERDMSA_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105331", "query_105331", "ERDMS Agency - Valid For HOCA", expectedStatus_success, false, "", "", false);
			
			if(result)
			{
				//Get Value from File
				String[] dataContents=getStringFromDelimitedFile("erdmsA_105331","D","|"); 				
				//Get value from DB and compare
				resultDB = validateERDMSCommon_HOCAWithDB(dataContents,"Agency");
			}
			if(resultDB)
				new dynamicTestNGXml().resultPassToXl("test_105331","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105331","Fail");						
		}
		/*
		 * Test Case ID: test_105308
		 * Test Description: ERDMS_Agency: HOCA fields DB validation
		 * Author: Hemapriya Shanmugam
		 * Date: 21-July-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jul-2017
		 */
		@Test
		public void test_105308() throws Exception
		{
			boolean resultDB = false;
			//delete tables & process file - pass
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));	
			ERDMSA_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105308", "query_105308", "ERDMS Agency - Valid For SettlementBank", expectedStatus_success, false, "", "", false);
			
			if(result)
			{
				//Get Value from File
				String[] dataContents=getStringFromDelimitedFile("erdmsA_105308","D","|"); 				
				//Get value from DB and compare
				resultDB = validateERDMSAgency_SettlementACWithDB(dataContents);
			}
			if(resultDB)
				new dynamicTestNGXml().resultPassToXl("test_105308","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105308","Fail");						
		}
		/*
		 * Test Case ID: test_105310
		 * Test Description: ERDMS_Agency: HOCA fields DB validation
		 * Author: Hemapriya Shanmugam
		 * Date: 21-July-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jul-2017
		 */
		@Test
		public void test_105310() throws Exception
		{
			boolean resultDB = false;
			//delete tables & process file - pass
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));	
			ERDMSA_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105310", "query_105310", "ERDMS Agency - Valid For Charging Account", expectedStatus_success, false, "", "", false);
			
			if(result)
			{
				//Get Value from File
				String[] dataContents=getStringFromDelimitedFile("erdmsA_105310","D","|"); 				
				//Get value from DB and compare
				resultDB = validateERDMSAgency_SettlementACWithDB(dataContents);
			}
			if(resultDB)
				new dynamicTestNGXml().resultPassToXl("test_105310","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105310","Fail");						
		}
		/*
		 * Test Case ID: test_105322
		 * Test Description: ERDMS_Agency: HOCA fields DB validation
		 * Author: Hemapriya Shanmugam
		 * Date: 21-July-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Jul-2017
		 */
		@Test
		public void test_105322() throws Exception
		{
			boolean resultDB = false;
			//delete tables & process file - pass
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));	
			ERDMSA_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105322", "query_105322", "ERDMS Agency - Valid For Agency Branch", expectedStatus_success, false, "", "", false);
			
			if(result)
			{
				//Get Value from File
				String[] dataContents=getStringFromDelimitedFile("erdmsA_105322","D","|"); 				
				//Get value from DB and compare
				resultDB = validateERDMSAgencyBranchWithDB(dataContents);
			}
			if(resultDB)
				new dynamicTestNGXml().resultPassToXl("test_105322","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105322","Fail");						
		}
		
		/*
		 * Test Case ID: test_105663
		 * Test Description: ERDMS_Retail: Pipe Delimitation
		 * Author: Hemapriya Shanmugam
		 * Date: 06-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 06-Jul-2017
		 */
		@Test
		public void test_105663() throws Exception
		{
			int expectedStatus_0 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSR_DBCleanup();
			boolean result_pipe = validateInboundCommonFiles("T_105663","query_105663","ERDMS_Retail Valid File", expectedStatus_0,false,"","",false); 	
			validateERDMSRetailTables(4, 2, 0, 2);
			
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With No Pipe Delimiter","T_105663_1", "105663_NoPipe", "Footer is required",1,null);
					
			if( result_pipe && result && validateERDMSRetailTables(4, 2, 0, 2))
				new dynamicTestNGXml().resultPassToXl("test_105663","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105663","Fail");				
		}
		
		/*
		 * Test Case ID: test_105678
		 * Test Description: ERDMS_Retail
		 * Author: Hemapriya Shanmugam
		 * Date: 06-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 06-Jul-2017
		 */
		@Test
		public void test_105678() throws Exception
		{
			String[] expectedStrings = new String[2];
			expectedStrings[0]= "Header is required";
			expectedStrings[1]= "Footer is required";
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With No Header & Footer","T_105678", "105678", "",expectedStrings.length,expectedStrings);
					
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105678","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105678","Fail");				
		
		}
		/*
		 * Test Case ID: test_105683
		 * Test Description: ERDMS_Retail
		 * Author: Hemapriya Shanmugam
		 * Date: 06-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 06-Jul-2017
		 */
		@Test
		public void test_105683() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Header record id","T_105683", "105683", "Header is required",1,null);
					
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105683","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105683","Fail");				
		
		}
		
		/*
		 * Test Case ID: test_105684
		 * Test Description: ERDMS_Retail
		 * Author: Hemapriya Shanmugam
		 * Date: 06-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 06-Jul-2017
		 */
		@Test
		public void test_105684() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Header Record Not Pipe-delimited","T_105684", "105684", "Header is required",1,null);
					
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105684","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105684","Fail");				
		
		}
		/*
		 * Test Case ID: test_105685
		 * Test Description: ERDMS_Retail
		 * Author: Hemapriya Shanmugam
		 * Date: 06-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 07-Aug-2017
		 */
		@Test
		public void test_105685() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Header Record having invalid field length","T_105685", "105685", "Header is required",1,null);
					
			if(result&& validateERDMSRetailTables(0, 0, 0, 0)) 
				new dynamicTestNGXml().resultPassToXl("test_105685","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105685","Fail");				
		
		}
		/*
		 * Test Case ID: test_105695
		 * Test Description: ERDMS_Retail
		 * Author: Hemapriya Shanmugam
		 * Date: 07-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 07-Jul-2017
		 */
		@Test
		public void test_105695() throws Exception
		{
			String[] expectedStrings  = new String[5];
			expectedStrings[0] = "CreationDate : Date is invalid";
			expectedStrings[1] ="TPI Open Date : Date is invalid";
			expectedStrings[2] ="Created Date : Date is invalid";
			expectedStrings[3] ="Last Updated Date : Date is invalid";
			expectedStrings[4] ="TPI closure Date : Date is invalid";
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Date Field","T_105695", "105695_DateLess", "Date",1,null);
			ERDMSR_DBCleanup();
			boolean result_more=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Date Field","T_105695_1", "105695_DateMore", "Date",1,null);
			ERDMSR_DBCleanup();
			boolean result_invalid=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Date Field","T_105695_2", "105695_DateInvalid", "",expectedStrings.length,expectedStrings);
					
			if(result && result_more &&  result_invalid && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105695","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105695","Fail");				
		
		}
		/*
		 * Test Case ID: test_105703
		 * Test Description: ERDMS_Retail
		 * Author: Hemapriya Shanmugam
		 * Date: 07-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 07-Jul-2017
		 */
		@Test
		public void test_105703() throws Exception
		{
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSR_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105703", "query_105703", "ERDMS_Retail With Only Mandatory Fields", expectedStatus, false, "", "", false);
										
			if(result && validateERDMSRetailTables(3, 1, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105703","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105703","Fail");				
		
		}
		/*
		 * Test Case ID: test_105706
		 * Test Description: ERDMS_Retail: 
		 * Author: Hemapriya Shanmugam
		 * Date: 07-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 07-Jul-2017
		 */
		@Test
		public void test_105706() throws Exception
		{
			String[] expectedStrings = new String[7];
			expectedStrings[0]= "Catalogue ID Value is required";
			expectedStrings[1]= "Retail Business Banking ID : Value is required";
			expectedStrings[2]= "Customer Account Name : Value is required";
			expectedStrings[3]= "Customer Account Number : Value is required";
			expectedStrings[4]= "Customer Sort Code : Value is required";
			expectedStrings[5]= "File Created Date Time : Value is required";
			expectedStrings[6]= "Last Updated Date Time : Value is required";
			
			ERDMSR_DBCleanup();
			boolean result=	validateAuditStatusFailMsg("ERDMS_Retail With No Mandatory Fields","T_105706", "105706","",expectedStrings.length,expectedStrings);
					
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105706","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105706","Fail");				
		}
		/*
		 * Test Case ID: test_105712
		 * Test Description: ERDMS_Retail: Invalid Catalogue ID
		 * Author: Hemapriya Shanmugam
		 * Date: 07-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 07-Jul-2017
		 */
		@Test
		public void test_105712() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Catalogue Id","T_105712", "105712","Catalogue ID has invalid value",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105712","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105712","Fail");				
		
		}
		
		/*
		 * Test Case ID: test_105713
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Jul-2017
		 */
		@Test
		public void test_105713() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Data Record Id","T_105713", "105713","Record Type invalid",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105713","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105713","Fail");				
		
		}
		/*
		 * Test Case ID: test_105728
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Jul-2017
		 */
		@Test
		public void test_105728() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Data Record Id","T_105728", "105728_RBMore","Reatil banking ID : Length is invalid",1,null);
			ERDMSR_DBCleanup();
			boolean result_more=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Data Record Id","T_105728_1", "105728_RBInvalid","Reatil banking ID : Data type is invalid",1,null);
										
			if(result && result_more && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105728","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105728","Fail");				
		
		}
		/*
		 * Test Case ID: test_105730
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Jul-2017
		 */
		@Test
		public void test_105730() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Data Record Id","T_105730", "105730","Reatil banking ID : Length is invalid",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105730","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105730","Fail");				
		
		}
		
		/*
		 * Test Case ID: test_105745
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Jul-2017
		 */
		@Test
		public void test_105745() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Data Record Id","T_105745", "105745","Customer Account Name: Invalid Length",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105745","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105745","Fail");					
		}
	
		/*
		 * Test Case ID: test_105802
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Jul-2017
		 */
		@Test
		public void test_105802() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Customer Account Number > 8","T_105802", "105802","Customer Account Number : Length is invalid",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105802","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105802","Fail");					
		}
		
		/*
		 * Test Case ID: test_105804
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Jul-2017
		 */
		@Test
		public void test_105804() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Customer Account Number < 8","T_105804", "105804","Customer Account Number : Length is invalid",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105804","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105804","Fail");					
		}
	
		/*
		 * Test Case ID: test_105808
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Jul-2017
		 */
		@Test
		public void test_105808() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Customer Account Number in alphabets","T_105808", "105808","Customer Account Number : Data type is invalid",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105808","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105808","Fail");					
		}
		/*
		 * Test Case ID: test_105845
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Jul-2017
		 */
		@Test
		public void test_105845() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Customer SortCode < 6","T_105845", "105845","Customer Sort Code : Length is invalid",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105845","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105845","Fail");					
		}
			
		/*
		 * Test Case ID: test_105847
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Comments: -- NULL SCENARIO
		 */
		@Test
		public void test_105847() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Customer SortCode > 6","T_105847", "105847","Customer Sort Code : Length is invalid",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105847","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105847","Fail");					
		}
		/*
		 * Test Case ID: test_105850
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Comments: -- NULL SCENARIO
		 */
		@Test
		public void test_105850() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With TPI ID < 5","T_105850", "105850","TPI ID : Length is invalid",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105850","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105850","Fail");					
		}
		/*
		 * Test Case ID: test_105852
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Jul-2017
		 * Comments: -- NULL SCENARIO
		 */
		@Test
		public void test_105852() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With TPI ID > 5","T_105852", "105852","TPI ID : Length is invalid",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105852","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105852","Fail");					
		}
		/*
		 * Test Case ID: test_105857
		 * Test Description: ERDMS_Retail: Empty Record Id
		 * Author: Hemapriya Shanmugam
		 * Date: 13-Jul-2017
		 * Comments: -- NULL SCENARIO
		 */
		@Test
		public void test_105857() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With TPI Name > 50","T_105857", "105857","",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105857","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105857","Fail");					
		}
		
		/*
		 * Test Case ID: test_105892
		 * Test Description: ERDMS_Retail: Empty TPI Name
		 * Author: Hemapriya Shanmugam
		 * Date: 13-Jul-2017
		 * Comments: -- NULL SCENARIO
		 */
		@Test
		public void test_105892() throws Exception
		{
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSR_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105892", "query_105892", "ERDMS Retail - Pass For TPI Name Is Empty", expectedStatus_success, false, "", "", false);
										
			if(result && validateERDMSRetailTables(4, 2, 0, 2))
				new dynamicTestNGXml().resultPassToXl("test_105892","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105892","Fail");					
		}
		
		/*
		 * Test Case ID: test_105897
		 * Test Description: ERDMS_Retail: Invalid TPI ID
		 * Author: Hemapriya Shanmugam
		 * Date: 13-Jul-2017
		 * Comments: -- NULL SCENARIO
		 */
		@Test
		public void test_105897() throws Exception
		{	
			String[] expectedStrings = new String[2];
			expectedStrings[0] = "TPI ID : Data type is invalid";
			expectedStrings[1] = "TPI ID : Length is invalid";
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With TPI ID > 6","T_105897", "105897",expectedStrings[1],1,null);
			validateERDMSRetailTables(0, 0, 0, 0);
			
			ERDMSR_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Retail With TPI ID is Invalid with Alphabets","T_105897_1", "105897_1","",expectedStrings.length,expectedStrings);
						
			if(result && result_Invalid && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105897","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105897","Fail");					
		}
		
		/*
		 * Test Case ID: test_105900
		 * Test Description: ERDMS_Retail: Empty TPI Name
		 * Author: Hemapriya Shanmugam
		 * Date: 13-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 13-Jul-2017
		 */
		@Test
		public void test_105900() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With TPI Type > 6","T_105900", "105900","TPI Type: Invalid Length",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105900","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105900","Fail");					
		}
		
		/*
		 * Test Case ID: test_105912
		 * Test Description: ERDMS_Retail: Invalid formate TPI Date
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Comments: -- NULL SCENARIO
		 */
		@Test
		public void test_105912() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail Invalid format TPI Date","T_105912", "105912","TPI Open Date : Date is invalid",1,null);
			validateERDMSRetailTables(0, 0, 0, 0);
			
			ERDMSR_DBCleanup();
			boolean result_More=validateAuditStatusFailMsg("ERDMS_Retail With TPI OpenDAte > 8","T_105912_1", "105912_1","tpiOpenDate : Date is invalid",1,null);
			
			if(result && result_More && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105912","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105912","Fail");					
		}
		
		/*
		 * Test Case ID: test_105916
		 * Test Description: ERDMS_Retail: Alternate Name > 40
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 20-Jul-2017
		 */
		@Test
		public void test_105916() throws Exception
		{
			String[] expectedStrings = new String[10];
			expectedStrings[0] = "Alternate Name 1: Invalid Length";
			expectedStrings[1] = "Alternate Name 2: Invalid Length";
			expectedStrings[2] = "Alternate Name 3: Invalid Length";
			expectedStrings[3] = "Alternate Name 4: Invalid Length";
			expectedStrings[4] = "Alternate Name 5: Invalid Length";
			expectedStrings[5] = "Alternate Name 6: Invalid Length";
			expectedStrings[6] = "Alternate Name 7: Invalid Length";
			expectedStrings[7] = "Alternate Name 8: Invalid Length";
			expectedStrings[8] = "Alternate Name 9: Invalid Length";
			expectedStrings[9] = "Alternate Name 10: Invalid Length";
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Alternate Name > 40","T_105916", "105916","",expectedStrings.length,expectedStrings);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105916","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105916","Fail");					
		}
			
		/*
		 * Test Case ID: test_105918
		 * Test Description: ERDMS_Retail: Invalid TPI Closure Date
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 23-Jul-2017
		 */
		@Test
		public void test_105918() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid TPI Closure Date","T_105918", "105918","TPI closure Date : Date is invalid",1,null);
			validateERDMSRetailTables(0, 0, 0, 0);
			
			ERDMSR_DBCleanup();
			boolean result_More=validateAuditStatusFailMsg("ERDMS_Retail With Length TPI Closure Date > 8","T_105918_1", "105918_1","tpiClosureDate  : Date is invalid",1,null);
			
			if(result && result_More && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105918","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105918","Fail");					
		}
		
		/*
		 * Test Case ID: test_105921
		 * Test Description: ERDMS_Retail: Invalid TPI Closure Reason/Description
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 22-Jul-2017
		 */
		@Test
		public void test_105921() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid TPI Closure Reason/Description","T_105921", "105921","Closure Reason: Invalid Length",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105921","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105921","Fail");					
		}

		/*
		 * Test Case ID: test_105927
		 * Test Description: ERDMS_Retail: Invalid Business Line Owner
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 22-Jul-2017
		 */
		@Test
		public void test_105927() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Business Line Owner","T_105927", "105927","BusinessLine Owner: Invalid Length",1,null);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105927","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105927","Fail");					
		}
		
		/*
		 * Test Case ID: test_105931
		 * Test Description: ERDMS_Retail: Invalid CreatedDate
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 22-Jul-2017
		 */
		@Test
		public void test_105931() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid CreatedDate","T_105931", "105931","Created Date : Date is invalid",1,null);
			validateERDMSRetailTables(0, 0, 0, 0);
			
			ERDMSR_DBCleanup();
			boolean result_More=validateAuditStatusFailMsg("ERDMS_Retail With Length Created Date > 8","T_105931_1", "105931_1","Invalid Created Date",1,null);
										
			if(result && result_More && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105931","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105931","Fail");					
		}
		
		/*
		 * Test Case ID: test_105933
		 * Test Description: ERDMS_Retail: Invalid Last Updated Date
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 20-Jul-2017
		 */
		@Test
		public void test_105933() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Last Updated Date","T_105933", "105933","Last Updated Date : Date is invalid",1,null);
			validateERDMSRetailTables(0, 0, 0, 0);
			
			ERDMSR_DBCleanup();
			boolean result_More=validateAuditStatusFailMsg("ERDMS_Retail With Length of  Last Updated Date > 8","T_105933_1", "105933_1","Invalid Last Updated Date",1,null);

										
			if(result && result_More && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105933","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105933","Fail");					
		}
		
		/*
		 * Test Case ID: test_105935
		 * Test Description: ERDMS_Retail: No Pipe Delimitation in Trailer Record
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 21-Aug-2017
		 */
		@Test
		public void test_105935() throws Exception
		{
			String[] expectedStrings = new String [2];
			expectedStrings[0] = "Record Type invalid";
			expectedStrings[1] = "Footer is required";
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With No Pipe Delimitation in Trailer Record","T_105935", "105935","",expectedStrings.length,expectedStrings);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105935","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105935","Fail");					
		}
		
		/*
		 * Test Case ID: test_105936
		 * Test Description: ERDMS_Retail: Invalid Trailer Record ID
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 25-Jul-2017
		 */
		@Test
		public void test_105936() throws Exception
		{
			String[] expectedStrings = new String [2];
			expectedStrings[0] = "Record Type invalid";
			expectedStrings[1] = "Footer is required";
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With Invalid Trailer Record ID","T_105936", "105936","",expectedStrings.length,expectedStrings);
										
			if(result && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105936","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105936","Fail");					
		}
		
		/*
		 * Test Case ID: test_105937
		 * Test Description: ERDMS_Retail: Less Trailer Record Count
		 * Author: Hemapriya Shanmugam
		 * Date: 13-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 25-Jul-2017
		 */
		@Test
		public void test_105937() throws Exception
		{
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSR_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105937", "query_105937", "ERDMS Retail - Pass For Tailer Record < 8", expectedStatus_success, false, "", "", false);
										
			if(result && validateERDMSRetailTables(4, 2, 0, 2))
				new dynamicTestNGXml().resultPassToXl("test_105937","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105937","Fail");					
		}
		
		/*
		 * Test Case ID: test_105938
		 * Test Description: ERDMS_Retail:  Trailer Record Count > 8
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105938() throws Exception
		{
			ERDMSR_DBCleanup();
			boolean result=validateAuditStatusFailMsg("ERDMS_Retail With  Trailer Record Count > 8","T_105938", "105938","Row count : Length is invalid",1,null);
			validateERDMSRetailTables(0, 0, 0, 0);
			
			ERDMSR_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Retail With Alphabets in Trailer Record Count ","T_105938_1", "105938_1","row Count : Data type is invalid",1,null);
			validateERDMSRetailTables(0, 0, 0, 0);
			
			ERDMSR_DBCleanup();
			boolean result_Incorrect=validateAuditStatusFailMsg("ERDMS_Retail With Incorrect Trailer Record ","T_105938_3", "105938_3","row count miss match",1,null);
			validateERDMSRetailTables(0, 0, 0, 0);
			
			ERDMSR_DBCleanup();
			boolean result_Null=validateAuditStatusFailMsg("ERDMS_Retail With Null Trailer Record Count","T_105938_2", "105938_2","row Count : Value is required",1,null);
			
			if(result && result_NN &&  result_Null && result_Incorrect && validateERDMSRetailTables(0, 0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105938","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105938","Fail");					
		}
		
		/*
		 * Test Case ID: test_105214
		 * Test Description: ERDMS_Internal:  Trailer Record Count Validation
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105214_105253() throws Exception 
		{
			boolean resultDB = false;
			//delete tables & process file - pass
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));	
			ERDMSI_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105214_valid", "query_105214", "ERDMS Internal - Valid For Header&Footer", expectedStatus_success, false, "", "", false);
			
			if(result)
			{
				//Get Value from File
				String[] dataContentsHeader=getStringFromDelimitedFile("erdmsI_105214","H","|"); 		
				String[] dataContentsTrailer=getStringFromDelimitedFile("erdmsI_105214","T","|"); 				

				//Get value from DB and compare
				resultDB = validateERDMSInternal_HeaderAndFooterWithDB(dataContentsHeader,dataContentsTrailer);
			}
			if(resultDB)
				new dynamicTestNGXml().resultPassToXl("test_105214_105253","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105214_105253","Fail");						
		
		}
		
		/*
		 * Test Case ID: test_105303
		 * Test Description: ERDMS_Internal:  Only Optional Fields - Fail
		 * Author: Hemapriya Shanmugam
		 * Date: 31-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105303() throws Exception 
		{
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With NULL Mandatory Fields","T_105303", "105303_OnlyOptional", " ",1,null);
			if( result_Invalid && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105303","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105303","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_105357
		 * Test Description: ERDMS_Internal:  All fields more length
		 * Author: Hemapriya Shanmugam
		 * Date: 31-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105357_105524_105525() throws Exception 
		{
			String[] expectedStrings1 = new String[]{"Header is required","Record Type invalid","Error at Line  : 3, Record Type invalid","Footer is required"};
			String[] expectedStrings2 = new String[19];
					expectedStrings2[0] = "Reference Number: Invalid length";
					expectedStrings2[1] = "Invalid creation date";
					expectedStrings2[2] = "Catalogue ID has invalid value";
					expectedStrings2[3] = "Internal Department ID : Length is invalid";
					expectedStrings2[4] = "Department Category Type: Invalid length";
					expectedStrings2[5] = "Department Name: Invalid length";
					expectedStrings2[6] = "Department Sort Code : Length is invalid";
					expectedStrings2[7] = "Department Settlement Sort Code : Length is invalid";
					expectedStrings2[8] = "Department Settlement Account Number : Length is invalid";
					expectedStrings2[9] = "HOCA Sort Code : Length is invalid";
					expectedStrings2[10] = "HOCA Account Number : Length is invalid";
					expectedStrings2[11] = "Invalid HOCA Effective Date";
					expectedStrings2[12] = "Business Line Owner: Invalid length";
					expectedStrings2[13] = "Credit Extract Type: Invalid length";
					expectedStrings2[14] = "HOCA Settlement Sort Code : Length is invalid";
					expectedStrings2[15] = "HOCA Settlement Account Number : Length is invalid";
					expectedStrings2[16] = "Invalid Last Updated Date";
					expectedStrings2[17] = "Invalid Created Date";
					expectedStrings2[18] = "row count : Length is invalid";
			ERDMSI_DBCleanup(); 
			boolean result_Invalid1=validateAuditStatusFailMsg("ERDMS_Internal With Exceeding Agreed Field Lengths","T_105357", "105357_105524_105525_moreLength", "",expectedStrings1.length,expectedStrings1);
			validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup(); 
			boolean result_Invalid2=validateAuditStatusFailMsg("ERDMS_Internal With Exceeding Agreed Field Lengths with Valid RecordId","T_105357_1", "105357_105524_105525_moreLength2", "",expectedStrings2.length,expectedStrings2);

			if( result_Invalid1 && result_Invalid2 && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105357_105524_105525","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105357_105524_105525","Fail");		
		
		}
		/*
		 * Test Case ID: test_105526
		 * Test Description: ERDMS_Internal:  All fields Validation
		 * Author: Hemapriya Shanmugam
		 * Date: 31-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105526_105679_105541_105529() throws Exception 
		{
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			boolean resultSourceDB = false, resultIntDepDB = false, resultHOCADB = false;
		//	ERDMSI_DBCleanup();
			boolean result_Valid=true;//validateInboundCommonFiles("T_105526", "query_105526", "Valid File For Validation", expectedStatus, false, "", "", false);
			validateERDMSInternalTables(3, 1, 1); 	
			if(result_Valid)
			{
				//Get Value from File
				String[] dataContents=getStringFromDelimitedFile("erdmsI_105526_105679_105541_105529","D","|"); 		
				
				//Get value from DB and compare
				 resultSourceDB = validateERDMSInternal_DataSourceWithDB(dataContents);
				 resultIntDepDB = validateERDMSInternal_DataIntDepWithDB(dataContents);
				 resultHOCADB = validateERDMSCommon_HOCAWithDB(dataContents,"Internal");
				 
			}
			if( result_Valid && resultSourceDB && resultIntDepDB && resultHOCADB)
				new dynamicTestNGXml().resultPassToXl("test_105526_105679_105541_105529","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105526_105679_105541_105529","Fail");		
		
		}
		/*
		 * Test Case ID: test_105577
		 * Test Description: ERDMS_Internal:  All fields more length
		 * Author: Hemapriya Shanmugam
		 * Date: 31-Jul-2017
		 * Comments: -- NULL SCENARIO
		 */
		@Test
		public void test_105577() throws Exception 
		{
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With NonNumeric Internal DepartmentID","T_105577", "105577_NN", "Internal Department ID : Data type is invalid",1,null);
			 validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup();
			boolean result_Less=validateAuditStatusFailMsg("ERDMS_Internal With Length Of Internal DepartmentID < 5","T_105577_1", "105577_LessLength", "Internal Department ID : Length is invalid",1,null);
			
			
			if( result_Invalid && result_Less && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105577","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105577","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_105583
		 * Test Description: ERDMS_Internal:  All fields more length
		 * Author: Hemapriya Shanmugam
		 * Date: 01-Aug-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105583() throws Exception 
		{
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With Non Numeric Department Sort Code","T_105583", "105583_NN", "Department Sort Code : Data type is invalid",1,null);
			validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup();
			boolean result_less=validateAuditStatusFailMsg("ERDMS_Internal With Department Sort Code Length < 6","T_105583_1", "105583_LessLength", "Department Sort Code : Length is invalid",1,null);
			
			if( result_Invalid && result_less && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105583","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105583","Fail");		
		
		}
		/*
		 * Test Case ID: test_105589
		 * Test Description: ERDMS_Internal:  All fields more length
		 * Author: Hemapriya Shanmugam
		 * Date: 01-Aug-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105589() throws Exception 
		{
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With Department Setllement SortCode Length < 6","T_105589", "105589", "Department Settlement Sort Code : Length is invalid",1,null);
			validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Internal With Non Numeric Department Settlement SortCode","T_105589_1", "105589_NN", "Department Settlement Sort Code : Data type is invalid",1,null);
			
			if( result_Invalid && result_NN && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105589","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105589","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_105590
		 * Test Description: ERDMS_Internal:  All fields more length
		 * Author: Hemapriya Shanmugam
		 * Date: 01-Aug-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105590() throws Exception 
		{
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With Less Length Department Setllement AccountNumber","T_105590", "105590", "Department Settlement Account Number : Length is invalid",1,null);
			validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Internal With Non Numeric Department Settlement AccountNumber","T_105590_1", "105590_NN", "Department Settlement Account Number : Data type is invalid",1,null);
			
			if( result_Invalid && result_NN && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105590","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105590","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_105591
		 * Test Description: ERDMS_Internal:  Validation HOCA SortCode
		 * Author: Hemapriya Shanmugam
		 * Date: 01-Aug-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105591() throws Exception 
		{
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With Less Length HOCA SortCode","T_105591", "105591", "HOCA Sort Code : Length is invalid",1,null);
			validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Internal With NonNumeric HOCA SortCode","T_105591_1", "105591_NN", "HOCA Sort Code : Data type is invalid",1,null);
			
			if( result_Invalid && result_NN && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105591","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105591","Fail");		
		
		}
		/*
		 * Test Case ID: test_105592
		 * Test Description: ERDMS_Internal:  Validation HOCA Account Number
		 * Author: Hemapriya Shanmugam
		 * Date: 01-Aug-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105592() throws Exception 
		{
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With Less Length HOCA Account Number","T_105592", "105592", "HOCA Account Number : Length is invalid",1,null);
			validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Internal With Non Numeric HOCA Account Number","T_105592_1", "105592_NN", "HOCA Account Number : Data type is invalid",1,null);
			
			if( result_Invalid && result_NN && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105592","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105592","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_105614
		 * Test Description: ERDMS_Internal:  Validation HOCA Effective Date
		 * Author: Hemapriya Shanmugam
		 * Date: 01-Aug-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105614() throws Exception 
		{
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With Less Length HOCA Effective Date","T_105614", "105614", " ",1,null);
			validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Internal With Invalid HOCA Effective Date","T_105614_1", "105614_Invalid", "HOCA Effective Date : Date is invalid",1,null);
			
			if( result_Invalid && result_NN && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105614","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105614","Fail");		
		
		}

		
		/*
		 * Test Case ID: test_105658
		 * Test Description: ERDMS_Internal:  Validation HOCA Settlement Sort Code
		 * Author: Hemapriya Shanmugam
		 * Date: 01-Aug-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 03-Aug-2017
		 */
		@Test
		public void test_105658() throws Exception 
		{
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With Non Numeric HOCA Settlement Sort Code","T_105658", "105658_NN", "HOCA Settlement Sort Code : Data type is invalid",1,null);
			validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup();
			boolean result_less=validateAuditStatusFailMsg("ERDMS_Internal With Less Length HOCA Settlement Sort Code","T_105658_1", "105658_less", "HOCA Settlement Sort Code : Length is invalid",1,null);
			
			if( result_Invalid && result_less && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105658","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105658","Fail");		
		
		}
		/*
		 * Test Case ID: test_105659
		 * Test Description: ERDMS_Internal:  Validation HOCA Settlement Account Number
		 * Author: Hemapriya Shanmugam
		 * Date: 01-Aug-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105659() throws Exception 
		{
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With Non Numeric HOCA Settlement Account Number","T_105659", "105659", "HOCA Settlement Account Number : Data type is invalid",1,null);
			validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup();
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Internal With HOCA Settlement Account Number < 8","T_105659_1", "105659_less", "HOCA Settlement Account Number : Length is invalid",1,null);
			
			if( result_Invalid && result_NN && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105659","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105659","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_105660_105661
		 * Test Description: ERDMS_Internal:  Validation Created Date
		 * Author: Hemapriya Shanmugam
		 * Date: 01-Aug-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 01-Aug-2017
		 */
		@Test
		public void test_105660_105661() throws Exception 
		{
			String[] expectedStrings = new String[] {"created Date : Date is invalid","last Updated Date : Date is invalid"};
			String[] expectedStrings1 = new String[] {"Invalid Created Date","Invalid Last Updated Date"};
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With Invalid Created Date","T_105660", "105660_105661_invalid", "",expectedStrings.length,expectedStrings);
			validateERDMSInternalTables(0, 0, 0);
			ERDMSI_DBCleanup();
			boolean result_less=validateAuditStatusFailMsg("ERDMS_Internal With Less Length Created Date","T_105660_1", "105660_105661_less", "",expectedStrings1.length,expectedStrings1);
			
			if( result_Invalid && result_less && validateERDMSInternalTables(0, 0, 0))
				new dynamicTestNGXml().resultPassToXl("test_105660_105661","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105660_105661","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_105693
		 * Test Description: ERDMS_Internal:  Only Mandatory Fields - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 25-Jul-2017
		 */
		@Test
		public void test_105693() throws Exception 
		{
			int expectedStatus_success = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));	
			//delete tables & process file - pass
			ERDMSI_DBCleanup();
			boolean result=validateInboundCommonFiles("T_105693", "query_105693", "ERDMS Internal - Valid For Only Madatory Fields", expectedStatus_success, false, "", "", false);
			if( result && validateERDMSInternalTables(3, 1, 1))
				new dynamicTestNGXml().resultPassToXl("test_105693","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_105693","Fail");		
	
		}
		

		/*
		 * Test Case ID: test_IntTrailer
		 * Test Description: ERDMS_Internal:  Trailer Record Count Validation
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 25-Jul-2017
		 */
		@Test
		public void test_IntTrailer() throws Exception 
		{
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			ERDMSI_DBCleanup();
			boolean result_Invalid=validateAuditStatusFailMsg("ERDMS_Internal With Null Trailer Count","IntTrailer", "TrailerCountEmpty", "row Count : Value is required",1,null);
			validateERDMSInternalTables(0, 0, 0); 

			ERDMSI_DBCleanup();
			boolean result_lengthLess=validateInboundCommonFiles("IntTrailer_1", "query_IntTrailer", "ERDMS_Internal With Length Of Trailer Count  < 8", expectedStatus, false, "", "", false);

			validateERDMSInternalTables(3, 1, 1); 
			
			boolean result_lengthMore=validateAuditStatusFailMsg("ERDMS_Internal With Length Of Trailer Count > 8","IntTrailer_2", "TrailerCountMore", "row count : Length is invalid",1,null);
			validateERDMSInternalTables(3, 1, 1); 
			
			boolean result_Incorrect=validateAuditStatusFailMsg("ERDMS_Internal With Incorrect Trailer Count ","IntTrailer_3", "TrailerCountIncorrect", "row count mismatched",1,null);
			validateERDMSInternalTables(3, 1, 1); 
			
			boolean result_NN=validateAuditStatusFailMsg("ERDMS_Internal With Non-Numeric Value in Trailer Count","IntTrailer_4", "TrailerCountNN", "row Count : Data type is invalid",1,null);
			
			if( result_Invalid  && result_lengthLess && result_lengthMore  && result_NN && result_Incorrect && validateERDMSInternalTables(3, 1, 1))
				new dynamicTestNGXml().resultPassToXl("test_IntTrailer","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_IntTrailer","Fail");			
		}
		

		/*
		 * Test Case ID: test_105693
		 * Test Description: ERDMS_Internal:  Only Mandatory Fields - Pass
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jul-2017
		 */
		@Test
		public void test_erdms_refresh() throws Exception 
		{
			boolean isERDMSCValid = false, isERDMSCValid2 = false, isERDMSC=false,isERDMSAValid = false,isERDMSA=false, isERDMSAValid2 = false,isERDMSRValid = false,isERDMSRValid2 = false,isERDMSR=false,isERDMSIValid2 = false, isERDMSIValid = false,isERDMSI=false;
			int expectedStatus_1 = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			
			//Valid ERDMS Files
			validateInboundCommonFiles("erdmsC_del1","query_erdmsCdel1","ERDMS Commercial Valid File-DELETE SCENARIO",expectedStatus_1,false,"","",false);
			validateInboundCommonFiles("erdmsA_del1","query_erdmsAdel1","ERDMS Agency Valid File-DELETE SCENARIO",expectedStatus_1,false,"","",false);
			validateInboundCommonFiles("erdmsR_del1","query_erdmsRdel1","ERDMS Retail Banking Valid File-DELETE SCENARIO",expectedStatus_1,false,"","",false);
			validateInboundCommonFiles("erdmsI_del1","query_erdmsIdel1","ERDMS Internal Department Valid File-DELETE SCENARIO",expectedStatus_1,false,"","",false);
			
			//Validate the Table Record  Count
			isERDMSCValid = validateERDMSCommTables(3, 1 , 1 , 1, 1, 1);
			isERDMSAValid = validateERDMSAgencyTables(3, 1, 1, 1, 1, 1); 
			isERDMSRValid = validateERDMSRetailTables(4, 2, 0, 2);
			isERDMSIValid = validateERDMSInternalTables(3, 1, 1);
			
			validateInboundCommonFiles("erdmsC_del15","query_erdmsCdel15","ERDMS Commercial Valid File(5)-DELETE SCENARIO",expectedStatus_1,false,"","",false);
			validateInboundCommonFiles("erdmsA_del15","query_erdmsAdel15","ERDMS Agency Valid File(5)-DELETE SCENARIO",expectedStatus_1,false,"","",false);
			validateInboundCommonFiles("erdmsR_del15","query_erdmsRdel15","ERDMS Retail Banking Valid File(5)-DELETE SCENARIO",expectedStatus_1,false,"","",false);
			validateInboundCommonFiles("erdmsI_del15","query_erdmsIdel15","ERDMS Internal Department Valid File(5)-DELETE SCENARIO",expectedStatus_1,false,"","",false);
			
			//Validate the Table Record  Count
			isERDMSCValid2 = validateERDMSCommTables(7, 3 , 2 , 3, 5, 5);
			isERDMSAValid2 = validateERDMSAgencyTables(7, 5, 5, 5, 5, 5); 
			isERDMSRValid2 = validateERDMSRetailTables(7, 5, 0, 3);
			isERDMSIValid2 = validateERDMSInternalTables(7, 5, 5);
			
			
			
			//Invalid Files to bring in RollBack 
			if(isERDMSCValid && isERDMSCValid2)
			{
				validateInboundCommonFiles("erdmsC_del2","query_erdmsCdel2","ERDMS Commercial-Duplicate Record Containing Exhaustive File",expectedStatus_1,false,"","",false);
				isERDMSC=validateERDMSCommTables(10173, 8 , 1 , 6, 10080, 10080);
			}
			if(isERDMSAValid && isERDMSAValid2)
			{
				validateInboundCommonFiles("erdmsA_del2","query_erdmsAdel2","ERDMS Agency-Duplicate Record Containing Exhaustive File",expectedStatus_1,false,"","",false);
				isERDMSA=validateERDMSAgencyTables(10222, 10219, 10219, 10219, 10219, 10219); 
			}
			if(isERDMSRValid && isERDMSRValid2)
			{
				validateInboundCommonFiles("erdmsR_del2","query_erdmsRdel2","ERDMS Retail-Duplicate Record Containing Exhaustive File",expectedStatus_1,false,"","",false);
				isERDMSR=validateERDMSRetailTables(10155, 10152, 5, 2);
			}
			if(isERDMSIValid && isERDMSIValid2)
			{
				validateInboundCommonFiles("erdmsI_del2","query_erdmsIdel2","ERDMS Internal-Duplicate Record Containing Exhaustive File",expectedStatus_1,false,"","",false);
				isERDMSI=validateERDMSInternalTables(10163, 10160, 10160);
			}
			if(	isERDMSC && isERDMSA && isERDMSR && isERDMSI)
				new dynamicTestNGXml().resultPassToXl("test_erdms_refresh","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_erdms_refresh","Fail");
		}

		/*
		 * Test Case ID: test_RefOutDup
		 * Test Description: Stored Procedures : RefOut SPs Have No Duplicate Columns
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jul-2017
		 */
		@Test
		public void test_RefOutDup() throws Exception 
		{
			boolean isAgencyParticipant = false, isAgencySCAndAccounts  = false, isAgencyScData = false, isAPGAdjReasons = false, isAPGNoPay = false, isChannelId = false,
					isEntityState = false, isEntityStateStatus = false, isHOCAAcToApg = false, isHOCAAcToKappa = false, isHSBCSortCodeToApg = false, isInstrumentType = false,
					isKappaFraudSuspectCode = false, isLloydsScToFraud = false, isGetNoPay = false, isParticipantRefData = false, isPostingRespQualifier = false, isPostingRespStatus = false,
					isPostingRespType = false, isSCAddress = false, isSCAttribute = false, isSCToApg = false, isSI = false, isSIAdd = false,
					isStopsAcToApg = false, isStopsSingleMultiToApg = false, isTSBSc = false, isUserFail = false, isUserProcessCode = false, 
					isWhitelistData = false, isWorkStreamStates = false;
			isAgencyParticipant = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetAgParticipant"));
			isAgencySCAndAccounts = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetAgScandAc"));
			isAgencyScData = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetAgSc"));
			isAPGAdjReasons = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetAgAdj"));
			isAPGNoPay = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetAPGNoPay"));
			isChannelId = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetChanId"));
			isEntityState = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetEntState"));
			isEntityStateStatus = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetEntStateStatus"));
			isHOCAAcToApg = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetHocaAPG"));
			isHOCAAcToKappa = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetHocaKappa"));
			isHSBCSortCodeToApg = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetHsbcSC"));
			isInstrumentType = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetInsType"));
			isKappaFraudSuspectCode = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetKappaSuspectCode"));
			isLloydsScToFraud = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetLloydsSC"));
			isGetNoPay = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetNoPayRC"));
			isParticipantRefData = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetPartRD"));
			isPostingRespQualifier = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetPostRespQual"));
			isPostingRespStatus = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetPostResoStatus"));
			isPostingRespType = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetPostResoType"));
			isSCAddress = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetSCAddress"));
			isSCAttribute = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetSCAttribute"));
			isSCToApg = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetSCToAPG"));
			isSI = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetSI"));
			isSIAdd = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetSIA"));
			isStopsAcToApg = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetStops"));
			isStopsSingleMultiToApg = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetStopsSingMulti"));
			isTSBSc = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetTsb"));
			isUserFail = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetUserFailReason"));
			isUserProcessCode = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetUserProcessCode"));
			isWhitelistData = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetWhiteList"));
			isWorkStreamStates = sp_RefOutDupCol(getRefDataValueFromDataSheet("spName_GetWorkStreamSt"));
			if(	isAgencyParticipant && isAgencySCAndAccounts  && isAgencyScData && isAPGAdjReasons && isAPGNoPay && isChannelId &&
					isEntityState && isEntityStateStatus && isHOCAAcToApg && isHOCAAcToKappa && isHSBCSortCodeToApg && isInstrumentType &&
					isKappaFraudSuspectCode && isLloydsScToFraud && isGetNoPay && isParticipantRefData && isPostingRespQualifier && isPostingRespStatus &&
					isPostingRespType && isSCAddress && isSCAttribute && isSCToApg && isSI && isSIAdd &&
					isStopsAcToApg && isStopsSingleMultiToApg && isTSBSc && isUserFail && isUserProcessCode && 
					isWhitelistData && isWorkStreamStates)
				new dynamicTestNGXml().resultPassToXl("test_RefOutDup","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_RefOutDup","Fail");
		
		}
		
		/*
		 * Test Case ID: test_LBGSortCodeToAPG
		 * Test Description: Stored Procedures : LBG Sort Code To APG - SP & Outbound Validation
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jul-2017
		 */
		@Test
		public void test_LBGSortCodeToAPG() throws Exception 
		{
			boolean isLBGOutBound = false, isLBGOutboundVal = false;
			setUserDBRoleByRoleName("LLOYDS");
			deleteFilesInFilePath(getRefDataValueFromDataSheet("lBGOutboundFile"));
				
				isLBGOutBound = referenceData_processingInputFile("lbgToFraud_out","","LBG OutBound SSIS",0,false,"lBGOutboundFile");
				
			if(isLBGOutBound) 
				{
				String[] lbgOutbound = getStringFromDelimitedFile("lBGOutboundFile","ALL",",");
				isLBGOutboundVal = sp_OutboundValidation(lbgOutbound, "LBG");
				}
			setUserDBRoleByRoleName("ALL");
			

			if (isLBGOutBound && isLBGOutboundVal)
				new dynamicTestNGXml().resultPassToXl("test_LBGSortCodeToAPG","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_LBGSortCodeToAPG","Fail");
		
		}
		
		/*
		 * Test Case ID: test_TSBSortCodeToAPG
		 * Test Description: Stored Procedures : LBG Sort Code To APG - SP & Outbound Validation
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jul-2017
		 */
		@Test
		public void test_TSBSortCodeToAPG() throws Exception 
		{
			boolean isTSBOutBound = false, isTSBOutboundVal = false;
			setUserDBRoleByRoleName("LLOYDS");

			deleteFilesInFilePath(getRefDataValueFromDataSheet("tSBOutboundFile"));

			isTSBOutBound = referenceData_processingInputFile("tsbToFraud_out","","TSB OutBound SSIS",0,false,"tSBOutboundFile");

			String[] tsbOutbound = getStringFromDelimitedFile("tSBOutboundFile","ALL",",");
			isTSBOutboundVal = sp_OutboundValidation(tsbOutbound, "TSB");
			setUserDBRoleByRoleName("ALL");

			if (isTSBOutBound && isTSBOutboundVal)
				new dynamicTestNGXml().resultPassToXl("test_TSBSortCodeToAPG","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_TSBSortCodeToAPG","Fail");
		
		}
		
		/*
		 * Test Case ID: test_84529
		 * Test Description: Stored Procedures : STOPS File Validation For Invalid Header Id
		 * Author: Hemapriya Shanmugam
		 * Date: 08-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Sep-2017
		 */
		@Test
		public void test_84529() throws Exception 
		{
			String expectedStrings = "Header is required";
			boolean isNoHeader = false, isInValidHeader = false, isNumericHeader = false;
			Stops_DBCleanup();
			isNoHeader= validateAuditStatusFailMsg("STOPS File With No Header RecordID","T_84529", "LBGICSSTOPRCBS20170721000001", expectedStrings,1,null);
			validateStopsTables(0, 0);
			Stops_DBCleanup();
			isInValidHeader=validateAuditStatusFailMsg("STOPS File With No Header RecordID","T_84529_1", "LBGICSSTOPRCBS20170721000002", expectedStrings,1,null);
			validateStopsTables(0, 0);
			Stops_DBCleanup();
			isNumericHeader=validateAuditStatusFailMsg("STOPS File With No Header RecordID","T_84529_2", "LBGICSSTOPRCBS20170721000003", expectedStrings,1,null);
			validateStopsTables(0, 0);
			if (isNoHeader && isInValidHeader && isNumericHeader)
				new dynamicTestNGXml().resultPassToXl("test_84529","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_84529","Fail");		
		}
		
		/*
		 * Test Case ID: test_84553
		 * Test Description: Stored Procedures : STOPS File Validation For Invalid File Id in Header
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Sep-2017
		 */
		@Test
		public void test_84553() throws Exception 
		{
			//String[] expectedStrings = new String[]{"Header is required", "row count miss match"};
			boolean isInvalid = false, isValid = true;
			//int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			Stops_DBCleanup();
			isInvalid= validateAuditStatusFailMsg("STOPS File With Invalid File Id In Header Record","T_84553", "LBGICSSTOPRCBS20170721000004", "Invalid row length of 130",1,null);
			validateStopsTables(0 , 0);
			
			/*Stops_DBCleanup();
			isValid=validateInboundCommonFiles("T_84553_1","query_84553","STOPS File With Valid File Id With Special Characters In Header Record",expectedStatus,false,"chbcr_outputFilePath","",false);			
			validateStopsTables(0, 0);*/
			if (isInvalid && isValid)
				new dynamicTestNGXml().resultPassToXl("test_84553","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_84553","Fail");		
		}
		
		/*
		 * Test Case ID: test_84558_84829
		 * Test Description: Stored Procedures : STOPS File Validation For Invalid Date in Header
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Sep-2017
		 */
		@Test
		public void test_84558_84829() throws Exception 
		{
			boolean isDateGreater = false, isDateLess = false, isDateNN = false, isDateWrong = false, isDateFormatInvalid = false;
			Stops_DBCleanup();
			isDateGreater= validateAuditStatusFailMsg("STOPS File With File Date > 8","T_84558", "LBGICSSTOPRCBS20170721000006", "Invalid row length of 126",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isDateLess= validateAuditStatusFailMsg("STOPS File With File Date < 8","T_84558_2", "LBGICSSTOPRCBS20170721000007", "Invalid row length of 119",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isDateNN= validateAuditStatusFailMsg("STOPS File With File Date is Non Numeric","T_84558_3", "LBGICSSTOPRCBS20170721000008", "File Date : Date is invalid",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isDateWrong= validateAuditStatusFailMsg("STOPS File With File Date Contains erroneous Date Value","T_84558_4", "LBGICSSTOPRCBS20170721000009", "File Date : Date is invalid",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isDateFormatInvalid= validateAuditStatusFailMsg("STOPS File With File Date has invalid Format","T_84558_5", "LBGICSSTOPRCBS20170721000010", "File Date : Date is invalid",1,null);
			validateStopsTables(0 ,0);
			
			
			if (isDateGreater && isDateLess && isDateNN && isDateWrong && isDateFormatInvalid)
				new dynamicTestNGXml().resultPassToXl("test_84558_84829","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_84558_84829","Fail");		
		}
		
		/*
		 * Test Case ID: test_84575
		 * Test Description: Stored Procedures : STOPS File Validation For Invalid File Source in Header
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Sep-2017
		 */
		@Test
		public void test_84575() throws Exception 
		{
			boolean isNCA = false, isCBS = false, isIFCA = false, isCS = false;
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			Stops_DBCleanup();
			isNCA= validateInboundCommonFiles("T_84575","query_84575","STOPS File NCA Valid File Source",expectedStatus,false,"","",false);
			validateStopsTables(1 ,4);
			Stops_DBCleanup();
			isCBS= validateInboundCommonFiles("T_84575_1","query_84575_1","STOPS File CBS Valid File Source",expectedStatus,false,"","",false);
			validateStopsTables(1 ,4);
			Stops_DBCleanup();
			isIFCA= validateInboundCommonFiles("T_84575_2","query_84575_2","STOPS File IFCA Valid File Source",expectedStatus,false,"","",false);
			validateStopsTables(1 ,4);
			Stops_DBCleanup();
			isCS= validateInboundCommonFiles("T_84575_3","query_84575_3","STOPS File CS Valid File Source",expectedStatus,false,"","",false);
			validateStopsTables(1 ,4);
			
			if (isNCA && isCBS && isIFCA && isCS)
				new dynamicTestNGXml().resultPassToXl("test_84575","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_84575","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_84709
		 * Test Description: Stored Procedures : STOPS File Validation For Invalid File Source in Header
		 * Author: Hemapriya Shanmugam
		 * Date: 11-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 11-Sep-2017
		 */
		@Test
		public void test_84709() throws Exception 
		{
			boolean isFSInvalid = false, isFSNumeric = false, isFSSpecialCharacters = false;
			Stops_DBCleanup();
			isFSInvalid= validateAuditStatusFailMsg("STOPS File With Invalid File Source","T_84709", "LBGICSSTOPRCBS20170721000011", "Invalid row length of 121",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isFSNumeric= validateAuditStatusFailMsg("STOPS File With File Source With Numbers","T_84709_1", "LBGICSSTOPRCBS20170721000012", "File Source : Invalid value",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isFSSpecialCharacters= validateAuditStatusFailMsg("STOPS File With File Source With Special Characters","T_84709_2", "LBGICSSTOPRCBS20170721000013", "File Source : Invalid value",1,null);
			validateStopsTables(0 ,0);
			if (isFSInvalid && isFSNumeric && isFSSpecialCharacters)
				new dynamicTestNGXml().resultPassToXl("test_84709","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_84709","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_84835
		 * Test Description: Stored Procedures : STOPS File Validation For Invalid File Type in Header
		 * Author: Hemapriya Shanmugam
		 * Date: 12-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 12-Sep-2017
		 */
		@Test
		public void test_84835() throws Exception 
		{
			boolean isFTInvalid = false, isFTNumeric = false;
			String expectedString = "File Type : Invalid value";
			Stops_DBCleanup();
			isFTInvalid= validateAuditStatusFailMsg("STOPS File With Invalid File Type in Header","T_84835", "LBGICSSTOPRCBS20170721000014", expectedString,1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isFTNumeric= validateAuditStatusFailMsg("STOPS File With File Source With Numbers in Header","T_84835_1", "LBGICSSTOPRCBS20170721000015", expectedString,1,null);
			validateStopsTables(0 ,0);
			
			if (isFTInvalid && isFTNumeric )
				new dynamicTestNGXml().resultPassToXl("test_84835","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_84835","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_84838
		 * Test Description: Stored Procedures : STOPS File Validation For Invalid Fillers in Header
		 * Author: Hemapriya Shanmugam
		 * Date: 12-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 12-Sep-2017
		 */
		@Test
		public void test_84838() throws Exception 
		{
			boolean isFInvalid = false, isFNumeric = false;
			Stops_DBCleanup();
			isFInvalid= validateAuditStatusFailMsg("STOPS File With Invalid Fillers in Header(less)","T_84838", "LBGICSSTOPRCBS20170721000016", "Invalid row length of 123",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isFNumeric= validateAuditStatusFailMsg("STOPS File With Invalid Fillers in Header(more)","T_84838_1", "LBGICSSTOPRCBS20170721000017", "Invalid row length of 109",1,null);
			validateStopsTables(0 ,0);
			
			if (isFInvalid && isFNumeric )
				new dynamicTestNGXml().resultPassToXl("test_84838","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_84838","Fail");		
		
		}
	
		/*
		 * Test Case ID: test_84842
		 * Test Description: Stored Procedures : STOPS File Validation For Invalid Record Id in Data Record
		 * Author: Hemapriya Shanmugam
		 * Date: 12-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 12-Sep-2017
		 */
		@Test
		public void test_84842() throws Exception 
		{
			boolean isRecInvalid = false, isRecNumeric = false, isRecEmpty = false;
			Stops_DBCleanup();
			isRecEmpty= validateAuditStatusFailMsg("STOPS File With Empty Data Record Id","T_84842", "LBGICSSTOPRCBS20170721000018", "Invalid row length of 119",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isRecInvalid= validateAuditStatusFailMsg("STOPS File With Invalid Data Record Id","T_84842_1", "LBGICSSTOPRCBS20170721000019", "Record Type invalid",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isRecNumeric= validateAuditStatusFailMsg("STOPS File With Numeric Data Record Id","T_84842_2", "LBGICSSTOPRCBS20170721000020", "Record Type invalid",1,null);
			validateStopsTables(0 ,0);
			
			if (isRecEmpty && isRecInvalid && isRecNumeric )
				new dynamicTestNGXml().resultPassToXl("test_84842","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_84842","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_84847
		 * Test Description: Stored Procedures : STOPS File Validation For Invalid SortCode in Data Record
		 * Author: Hemapriya Shanmugam
		 * Date: 12-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 12-Sep-2017
		 */
		@Test
		public void test_84847() throws Exception 
		{
			boolean isSCless = false, isSCmore = false, isSCAlpha = false, isSCSpecial = false, isSCEmpty = false;
			Stops_DBCleanup();
			isSCless= validateAuditStatusFailMsg("STOPS File With SortCode of length < 6","T_84847", "LBGICSSTOPRCBS20170721000021", "Invalid row length of 119",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isSCmore= validateAuditStatusFailMsg("STOPS File With SortCode of length > 6","T_84847_1", "LBGICSSTOPRCBS20170721000022", "Invalid row length of 122",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isSCAlpha= validateAuditStatusFailMsg("STOPS File With SortCode containing Alphabets","T_84847_2", "LBGICSSTOPRCBS20170721000023", "Sort Code : Data type is invalid",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isSCSpecial= validateAuditStatusFailMsg("STOPS File With SortCode containing Special Characters","T_84847_3", "LBGICSSTOPRCBS20170721000024", "Sort Code : Data type is invalid",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isSCEmpty= validateAuditStatusFailMsg("STOPS File With Empty SortCode","T_84847_4", "LBGICSSTOPRCBS20170721000025", "Invalid row length of 114",1,null);
			validateStopsTables(0 ,0);
			
			if (isSCless && isSCmore && isSCAlpha && isSCSpecial && isSCEmpty )
				new dynamicTestNGXml().resultPassToXl("test_84847","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_84847","Fail");		
		
		}
	
		/*
		 * Test Case ID: test_84855
		 * Test Description: Stored Procedures : STOPS File Validation For Invalid Account Number in Data
		 * Author: Hemapriya Shanmugam
		 * Date: 12-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 12-Sep-2017
		 */
		@Test
		public void test_84855() throws Exception 
		{
			boolean isACless = false, isACmore = false, isACAlpha = false, isACSpecial = false, isACEmpty = false;
			Stops_DBCleanup();
			isACless= validateAuditStatusFailMsg("STOPS File With Account Number of length < 8","T_84855", "LBGICSSTOPRCBS20170721000026", "Invalid row length of 119",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isACmore= validateAuditStatusFailMsg("STOPS File With Account Number of length > 8","T_84855_1", "LBGICSSTOPRCBS20170721000027", "Invalid row length of 124",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isACAlpha= validateAuditStatusFailMsg("STOPS File With Account Number containing Alphabets","T_84855_2", "LBGICSSTOPRCBS20170721000028", "Account Number : Data type is invalid",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isACSpecial= validateAuditStatusFailMsg("STOPS File With Account Number containing Special Characters","T_84855_3", "LBGICSSTOPRCBS20170721000029", "Account Number : Data type is invalid",1,null);
			validateStopsTables(0 ,0);
			Stops_DBCleanup();
			isACEmpty= validateAuditStatusFailMsg("STOPS File With Empty Account Number","T_84855_4", "LBGICSSTOPRCBS20170721000030", "Invalid row length of 112",1,null);
			validateStopsTables(0 ,0);
			
			if (isACless && isACmore && isACAlpha && isACSpecial && isACEmpty )
				new dynamicTestNGXml().resultPassToXl("test_84855","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_84855","Fail");		
		
		}
		
		/*
		 * Test Case ID: test_108462
		 * Test Description: Special Instruction: Verify the Insertion of HSBC records into Base Tables
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_108462() throws Exception
		{
			boolean isSI = false, selectResultFilled = false;
			SpecialInstruction_DBCleanup();
			setUserDBRoleByRoleName("HSBC");
			
			executeInsertQuery("query_SIInsert","Special Instruction - HSBC Access");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction Address - HSBC Access");
			String[] SI = getStringFromDelimitedFile("bulkInsertSI", "ALL", "|");
			String[] SIAdd = getStringFromDelimitedFile("bulkInsertSIAdd", "ALL", "|");
			String[] SICombined = (String[])ArrayUtils.addAll(SI, SIAdd);
			
		
			isSI = validateERDMSComm_SIWithDB(SICombined,"HSBC access Insert to table",selectResultFilled);
			setUserDBRoleByRoleName("ALL");

			if (isSI )
				new dynamicTestNGXml().resultPassToXl("test_108462","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_108462","Fail");		
		}
		
		/*
		 * Test Case ID: test_108467
		 * Test Description: Special Instruction: Verify the Insertion of HSBC records into Base Tables without HSBC Access
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_108467() throws Exception
		{
			boolean isSI = false, selectResultFilled = false;
			SpecialInstruction_DBCleanup();
			setUserDBRoleByRoleName("LLOYDS");
			
			executeInsertQuery("query_SIInsert","Special Instruction - No HSBC Access");
			executeInsertQuery("query_SIAddInsert","Special Instruction Address - No HSBC Access");
			String[] SI = getStringFromDelimitedFile("bulkInsertSI", "ALL", "|");
			String[] SIAdd = getStringFromDelimitedFile("bulkInsertSIAdd", "ALL", "|");
			String[] SICombined = (String[])ArrayUtils.addAll(SI, SIAdd);

		
			isSI = validateERDMSComm_SIWithDB(SICombined,"access Insert to table without HSBC ",selectResultFilled);
			setUserDBRoleByRoleName("ALL");

			if (isSI )
				new dynamicTestNGXml().resultPassToXl("test_108467","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_108467","Fail");		
		}
		
		/*
		 * Test Case ID: test_108554
		 * Test Description: Special Instruction Address: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_108554() throws Exception
		{
			boolean isSIAdd = false;
			isSIAdd = sp_RefOutColValidation("spName_GetSIA","refoutSIAddColumnNames");
			if (isSIAdd )
				new dynamicTestNGXml().resultPassToXl("test_108554","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_108554","Fail");
		}
		
		/*
		 * Test Case ID: test_108555
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_108555() throws Exception
		{
			boolean isSI = false;
			isSI = sp_RefOutColValidation("spName_GetSI","refoutSIColumnNames");
			if (isSI )
				new dynamicTestNGXml().resultPassToXl("test_108555","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_108555","Fail");
		}
		
		/*
		 * Test Case ID: test_131058
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 28-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 28-Sep-2017
		 */
		@Test
		public void test_131058() throws Exception
		{
			boolean isSINone = false, isSIDefault = false, isSITwice = false, isSINoneNotPresent = false, isAddressValidationRequired = false, isVerifyNotPresent = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_None_131058","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");			
			setUserDBRoleByRoleName("LLOYDS");
			//Exec Refout and validate for entry of HSBC Alone
			isSINone = sp_RefOutSpeInstructionValidation("Lloyds", "InstructionType6", isAddressValidationRequired, isVerifyNotPresent);
			
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_Twice_131058","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");			
			setUserDBRoleByRoleName("LLOYDS");
			//Exec Refout and validate for entry of HSBC Alone
			isSITwice = sp_RefOutSpeInstructionValidation("Lloyds", "InstructionType6", isAddressValidationRequired, isVerifyNotPresent);
			
			isVerifyNotPresent = true;
			
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_Default_131058","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");			
			setUserDBRoleByRoleName("LLOYDS");
			//Exec Refout and validate for entry of HSBC Alone
			isSIDefault = sp_RefOutSpeInstructionValidation("Lloyds", "InstructionType6", isAddressValidationRequired, isVerifyNotPresent);
			
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_None_131058_NP","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");
			
			setUserDBRoleByRoleName("LLOYDS");
			//Exec Refout and validate for entry of HSBC Alone
			isSINoneNotPresent = sp_RefOutSpeInstructionValidation("Lloyds", "InstructionType6", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			
			
			if (isSINone && isSIDefault && isSITwice && isSINoneNotPresent)
				new dynamicTestNGXml().resultPassToXl("test_131058","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131058","Fail");
		}
		
		
		/*
		 * Test Case ID: test_131073
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 28-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 28-Sep-2017
		 */
		@Test
		public void test_131073() throws Exception
		{
			boolean isSINone = false, isSIDefault = false, isSITwice = false, isSINoneNotPresent = false, isAddressValidationRequired = false, isVerifyNotPresent = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_None_131073","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");			
			setUserDBRoleByRoleName("HSBC");
			//Exec Refout and validate for entry of HSBC Alone
			isSINone = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType6", isAddressValidationRequired, isVerifyNotPresent);
			
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_Twice_131073","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");			
			setUserDBRoleByRoleName("HSBC");
			//Exec Refout and validate for entry of HSBC Alone
			isSITwice = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType6", isAddressValidationRequired, isVerifyNotPresent);
			
			isVerifyNotPresent = true;
			
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_Default_131073","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");			
			setUserDBRoleByRoleName("HSBC");
			//Exec Refout and validate for entry of HSBC Alone
			isSIDefault = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType6", isAddressValidationRequired, isVerifyNotPresent);
			
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_None_131073_NP","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");
			
			setUserDBRoleByRoleName("HSBC");
			//Exec Refout and validate for entry of HSBC Alone
			isSINoneNotPresent = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType6", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			
			
			if (isSINone && isSIDefault && isSITwice && isSINoneNotPresent)
				new dynamicTestNGXml().resultPassToXl("test_131073","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131073","Fail");
		}
		
		
		/*
		 * Test Case ID: test_131081
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_131081() throws Exception
		{
			boolean isSI = false, isAddressValidationRequired = false, isVerifyNotPresent = false, isSI_NP = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131081","Special Instruction - HSBC Access");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction Address - HSBC Access");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer - HSBC Access");
			setUserDBRoleByRoleName("HSBC");
			//Exec Refout and validate for entry of HSBC Alone
			isSI = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType1", isAddressValidationRequired, isVerifyNotPresent);
			isVerifyNotPresent = true;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131081_NP","Special Instruction - HSBC Access");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction Address - HSBC Access");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer - HSBC Access");
			setUserDBRoleByRoleName("HSBC");
			
			isSI_NP = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType1", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			if (isSI && isSI_NP)
				new dynamicTestNGXml().resultPassToXl("test_131081","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131081","Fail");
		}
		
		/*
		 * Test Case ID: test_131060
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_131060() throws Exception
		{
			boolean isSI = false, isAddressValidationRequired = false, isVerifyNotPresent = false, isSI_NP = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131060","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");
			setUserDBRoleByRoleName("LLOYDS");
			//Exec Refout and validate for entry of HSBC Alone
			isSI = sp_RefOutSpeInstructionValidation("LLOYDS", "InstructionType2", isAddressValidationRequired, isVerifyNotPresent);
			
			isVerifyNotPresent = true;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131060_NP","Special Instruction");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");
			setUserDBRoleByRoleName("LLOYDS");
			
			isSI_NP = sp_RefOutSpeInstructionValidation("LLOYDS", "InstructionType2", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			if (isSI && isSI_NP)
				new dynamicTestNGXml().resultPassToXl("test_131060","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131060","Fail");
		}
		
		
		
		/*
		 * Test Case ID: test_131062
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_131062() throws Exception
		{
			boolean isSI = false, isAddressValidationRequired = false, isVerifyNotPresent = false, isSI_NP = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131062","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");
			setUserDBRoleByRoleName("LLOYDS");
			//Exec Refout and validate for entry of LLOYDS Alone
			isSI = sp_RefOutSpeInstructionValidation("LLOYDS", "InstructionType5", isAddressValidationRequired, isVerifyNotPresent);
			
			isVerifyNotPresent = true;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131062_NP","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");
			setUserDBRoleByRoleName("LLOYDS");
			
			isSI_NP = sp_RefOutSpeInstructionValidation("LLOYDS", "InstructionType5", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			if (isSI && isSI_NP)
				new dynamicTestNGXml().resultPassToXl("test_131062","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131062","Fail");
		}
	
		
		/*
		 * Test Case ID: test_131063
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_131063() throws Exception
		{
			boolean isSI = false, isAddressValidationRequired = false, isVerifyNotPresent = false, isSI_NP = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131063","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");
			setUserDBRoleByRoleName("LLOYDS");
			//Exec Refout and validate for entry of LLOYDS Alone
			isSI = sp_RefOutSpeInstructionValidation("LLOYDS", "InstructionType4", isAddressValidationRequired, isVerifyNotPresent);
			
			isVerifyNotPresent = true;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131063_NP","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");
			setUserDBRoleByRoleName("LLOYDS");
			
			isSI_NP = sp_RefOutSpeInstructionValidation("LLOYDS", "InstructionType4", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			if (isSI && isSI_NP)
				new dynamicTestNGXml().resultPassToXl("test_131063","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131063","Fail");
		}
	
		
		/*
		 * Test Case ID: test_131071
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_131071() throws Exception
		{
			boolean isSI = false, isAddressValidationRequired = false, isVerifyNotPresent = false, isSI_NP = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131071","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");
			setUserDBRoleByRoleName("LLOYDS");
			//Exec Refout and validate for entry of LLOYDS Alone
			isSI = sp_RefOutSpeInstructionValidation("LLOYDS", "InstructionType3", isAddressValidationRequired, isVerifyNotPresent);
			
			isVerifyNotPresent = true;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131071_NP","Special Instruction");
			executeInsertQuery("query_SIAddInsert_Lloyds","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_Lloyds","Special Instruction Customer");
			setUserDBRoleByRoleName("LLOYDS");
			
			isSI_NP = sp_RefOutSpeInstructionValidation("LLOYDS", "InstructionType3", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			if (isSI && isSI_NP)
				new dynamicTestNGXml().resultPassToXl("test_131071","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131071","Fail");
		}
		
		
		
		/*
		 * Test Case ID: test_131074
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_131074() throws Exception
		{
			boolean isSI = false, isAddressValidationRequired = false, isVerifyNotPresent = false, isSI_NP = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131074","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");
			setUserDBRoleByRoleName("HSBC");
			//Exec Refout and validate for entry of HSBC Alone
			isSI = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType5", isAddressValidationRequired, isVerifyNotPresent);
			
			isVerifyNotPresent = true;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131074_NP","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");
			setUserDBRoleByRoleName("HSBC");
			
			isSI_NP = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType5", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			if (isSI && isSI_NP)
				new dynamicTestNGXml().resultPassToXl("test_131074","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131074","Fail");
		}
	
		
		/*
		 * Test Case ID: test_131075
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_131075() throws Exception
		{
			boolean isSI = false, isAddressValidationRequired = false, isVerifyNotPresent = false, isSI_NP = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131075","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");
			setUserDBRoleByRoleName("HSBC");
			//Exec Refout and validate for entry of HSBC Alone
			isSI = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType4", isAddressValidationRequired, isVerifyNotPresent);
			
			isVerifyNotPresent = true;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131075_NP","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");
			setUserDBRoleByRoleName("HSBC");
			
			isSI_NP = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType4", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			if (isSI && isSI_NP)
				new dynamicTestNGXml().resultPassToXl("test_131075","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131075","Fail");
		}
	
		
		/*
		 * Test Case ID: test_131076
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_131076() throws Exception
		{
			boolean isSI = false, isAddressValidationRequired = false, isVerifyNotPresent = false, isSI_NP = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131076","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");
			setUserDBRoleByRoleName("HSBC");
			//Exec Refout and validate for entry of HSBC Alone
			isSI = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType3", isAddressValidationRequired, isVerifyNotPresent);
			
			isVerifyNotPresent = true;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131076_NP","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");
			setUserDBRoleByRoleName("HSBC");
			
			isSI_NP = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType3", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			if (isSI && isSI_NP)
				new dynamicTestNGXml().resultPassToXl("test_131076","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131076","Fail");
		}

		
		
		/*
		 * Test Case ID: test_131078
		 * Test Description: Special Instruction: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_131078() throws Exception
		{
			boolean isSI = false, isAddressValidationRequired = true, isVerifyNotPresent = false, isSI_NP = false;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131078","Special Instruction");
			executeInsertQuery("query_SIAddInsert_HSBC","Special Instruction Address");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");
			setUserDBRoleByRoleName("HSBC");
			//Exec Refout and validate for entry of HSBC Alone
			isSI = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType2", isAddressValidationRequired, isVerifyNotPresent);
			
			isVerifyNotPresent = true;
			setUserDBRoleByRoleName("ALL");
			SpecialInstruction_DBCleanup();
			//INSERT QUERY
			executeInsertQuery("query_SIInsert_131078_NP","Special Instruction");
			executeInsertQuery("query_SICustomerInsert_HSBC","Special Instruction Customer");
			setUserDBRoleByRoleName("HSBC");
			
			isSI_NP = sp_RefOutSpeInstructionValidation("HSBC", "InstructionType2", isAddressValidationRequired, isVerifyNotPresent);
			setUserDBRoleByRoleName("ALL");
			if (isSI && isSI_NP)
				new dynamicTestNGXml().resultPassToXl("test_131078","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_131078","Fail");
		}
		
		
		/*
		 * Test Case ID: test_108554
		 * Test Description: Special Instruction Address: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 25-Sep-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 26-Sep-2017
		 */
		@Test
		public void test_RefoutColNameCheck() throws Exception
		{
			boolean isAgParticipant = false, isAgScandAc = false, isAgSc = false, isAgAdj = false, 
					isAPGNoPay = false, spName_BranchDetails = false, spName_BrandSCDetails = false, isChanId = false, isEntState = false, isEntStateStatus = false, isHocaAPG = false, 
					isHocaKappa = false, isHsbcSC = false, isInsType = false, isKappaSuspectCode = false, isLloydsSC = false, isNoPayRC = false, isPartRD = false, isPostRespQual = false, 
					isPostResoStatus = false, isPostResoType = false, isSCAddress = false, isSCAttribute = false, isSCToAPG = false, isStops = false, isStopsSingMulti = false, 
					isTsb = false, isUserFailReason = false, isUserProcessCode = false, isWhiteList = false, isWorkStreamSt = false;
			
			isAgParticipant= sp_RefOutColValidation( "spName_GetAgParticipant","refoutAgencyParticipantDataColumnNames");
			isAgScandAc= sp_RefOutColValidation( "spName_GetAgScandAc","refoutAgencySortCodeAndAccountsColumnNames");
			isAgSc= sp_RefOutColValidation( "spName_GetAgSc","refoutAgencySortCodeDataColumnNames");
			isAgAdj= sp_RefOutColValidation( "spName_GetAgAdj","refoutAPGAdjustmentReasonsColumnNames");
			isAPGNoPay= sp_RefOutColValidation( "spName_GetAPGNoPay","refoutAPGNoPayResponseColumnNames");
			spName_BranchDetails= sp_RefOutColValidation( "spName_BranchDetails","refoutBranchDetailsToCorrespondenceColumnNames");
			spName_BrandSCDetails= sp_RefOutColValidation( "spName_BrandSCDetails","refoutBrandSortcodeDetailsColumnNames");
			isChanId= sp_RefOutColValidation( "spName_GetChanId","refoutChannelIdentificationColumnNames");
			isEntState= sp_RefOutColValidation( "spName_GetEntState","refoutEntityStateColumnNames");
			isEntStateStatus= sp_RefOutColValidation( "spName_GetEntStateStatus","refoutEntityStateStatusColumnNames");
			isHocaAPG= sp_RefOutColValidation( "spName_GetHocaAPG","refoutHocaAccountToApgColumnNames");
			isHocaKappa= sp_RefOutColValidation( "spName_GetHocaKappa","refoutHocaAccountToKappaColumnNames");
			isHsbcSC= sp_RefOutColValidation( "spName_GetHsbcSC","refoutHSBCSortCodesToAPGColumnNames");
			isInsType= sp_RefOutColValidation( "spName_GetInsType","refoutInstrumentTypeToApgColumnNames");
			isKappaSuspectCode= sp_RefOutColValidation( "spName_GetKappaSuspectCode","refoutKappaFraudSuspectCodeColumnNames");
			isLloydsSC= sp_RefOutColValidation( "spName_GetLloydsSC","refoutLloydsSortCodeOutboundToFraudColumnNames");
			isNoPayRC= sp_RefOutColValidation( "spName_GetNoPayRC","refoutNoPayReasonCodeColumnNames");
			isPartRD= sp_RefOutColValidation( "spName_GetPartRD","refoutParticipantReferenceDataColumnNames");
			isPostRespQual= sp_RefOutColValidation( "spName_GetPostRespQual","refoutPostingResponseQualifierColumnNames");
			isPostResoStatus= sp_RefOutColValidation( "spName_GetPostResoStatus","refoutPostingResponseStatusColumnNames");
			isPostResoType= sp_RefOutColValidation( "spName_GetPostResoType","refoutPostingResponseTypeColumnNames");
			isSCAddress= sp_RefOutColValidation( "spName_GetSCAddress","refoutSortCodeAddressColumnNames");
			isSCAttribute= sp_RefOutColValidation( "spName_GetSCAttribute","refoutSortcodeAttributesColumnNames");
			isSCToAPG= sp_RefOutColValidation( "spName_GetSCToAPG","refoutSortcodeToApgColumnNames");
			isStops= sp_RefOutColValidation( "spName_GetStops","refoutStopsAccountToApgColumnNames");
			isStopsSingMulti= sp_RefOutColValidation( "spName_GetStopsSingMulti","refoutStopsSingleMultiToApgColumnNames");
			isTsb= sp_RefOutColValidation( "spName_GetTsb","refoutTSBSortCodeOutboundToFraudColumnNames");
			isUserFailReason= sp_RefOutColValidation( "spName_GetUserFailReason","refoutUserFailReasonsColumnNames");
			isUserProcessCode= sp_RefOutColValidation( "spName_GetUserProcessCode","refoutUserProcessCodeColumnNames");
			isWhiteList= sp_RefOutColValidation( "spName_GetWhiteList","refoutWhitelistDataToApgColumnNames");
			isWorkStreamSt= sp_RefOutColValidation( "spName_GetWorkStreamSt","refoutWorkstreamStatesColumnNames");
			
			
			if (isAgParticipant && isAgScandAc && isAgSc && isAgAdj && isAPGNoPay && spName_BranchDetails && spName_BrandSCDetails && isChanId && 
					isEntState && isEntStateStatus && isHocaAPG && isHocaKappa && isHsbcSC && isInsType && isKappaSuspectCode && isLloydsSC && isNoPayRC && isPartRD && 
					isPostRespQual && isPostResoStatus && isPostResoType && isSCAddress && isSCAttribute && isSCToAPG && isStops && isStopsSingMulti && isTsb && 
					isUserFailReason && isUserProcessCode && isWhiteList && isWorkStreamSt )
				new dynamicTestNGXml().resultPassToXl("test_RefoutColNameCheck","Pass");
			else
				new dynamicTestNGXml().resultPassToXl("test_RefoutColNameCheck","Fail");
		}
	
		/*
		 * Test Case ID: test_108554
		 * Test Description: Special Instruction Address: Refout Column Names
		 * Author: Hemapriya Shanmugam
		 * Date: 09-Oct-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 09-Oct-2017
		 */
		@Test
		public void test_SortCodeAttributes() throws Exception
		{
			boolean isEISCD = true,isSpSCAttrA = false,isSpSCAttrL = false,isSpSCAttrH = false;
			int expectedStatus = Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1));
			boolean outputValidationRequired = false;
			//EISCD_DBCleanup();
			//Agency Data To be inserted by bulk insert statements TO be copied from PostDeployment Scripts
			//isEISCD = validateInboundCommonFiles("eiscd_SortCodeAttributes","query_eiscd_SortCodeAttributes","EISCD Valid File For SortCodeAttributes Validation",expectedStatus,outputValidationRequired,"","",false);
			if(	isEISCD )
			{
				setUserDBRoleByRoleName("ALL");
				isSpSCAttrA = sp_RefOutSCAttributesValidation("LLOYDS And HSBC");
				setUserDBRoleByRoleName("LLOYDS");
				isSpSCAttrL = sp_RefOutSCAttributesValidation("LLOYDS");
				setUserDBRoleByRoleName("HSBC");
				isSpSCAttrH = sp_RefOutSCAttributesValidation("HSBC");		
			}
			if(isSpSCAttrA && isSpSCAttrL && isSpSCAttrH)
			new dynamicTestNGXml().resultPassToXl("test_RefoutColNameCheck","Pass");
			else
			new dynamicTestNGXml().resultPassToXl("test_RefoutColNameCheck","Fail");
			
		}
		
		
		/*
		 * Test Case ID: test_TSBSortCodeToAPG
		 * Test Description: Stored Procedures : LBG Sort Code To APG - SP & Outbound Validation
		 * Author: Hemapriya Shanmugam
		 * Date: 19-Jul-2017
		 * Reviewed By: Venkatasainath Devisetty
		 * Last Modified By: Hemapriya Shanmugam 
		 * Last Modified Date: 15-Jul-2017
		 */
	/*	@Test
		public void test_HSBCSortCodeToAPG() throws Exception 
		{
			boolean isHSBCOutBound = false, isHSBCSpVal = false;

			setUserDBRoleByRoleName("HSBC");
			//SP Validation
			//Tree Type Validation Method For HSBC
			
			isHSBCSpVal= hsbcSp_OutboundValidation();
			//SP output = Outbound
			isHSBCOutBound = referenceData_processingInputFile("hsbcToApg_out","","HSBC OutBound SSIS",0,false,"hsbcOutboundFile");

			
		}*/
		
}


		