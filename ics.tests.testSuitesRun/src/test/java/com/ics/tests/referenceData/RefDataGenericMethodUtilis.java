package com.ics.tests.referenceData;

import java.io.File;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;
import com.google.common.base.Strings;
import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;

	public class RefDataGenericMethodUtilis extends ICSAutomationCommonSetup{
	public static Map<String, String> addressNameN,postalNameN,addressLine1N,addressLine2N,cityTownN,areaCountryN,CountryN,postCodeOutCodeN,postCodeInCodeN,zipCodeN= new LinkedHashMap<String, String>();
	public static List<String> addressName,postalName,addressLine1,addressLine2,cityTown,areaCountry,country,postOutCode,postInCode,zipCode = new ArrayList<String>();
	public static int matchCounter=0,notMatchCounter=0;
	public static boolean isEISCDProcessed = false, isEISCD = false;
	public static boolean aL2DBNull=false,areaDBNull=false,zCodeDBNull=false,aL2DBChk=false,areaDBChk=false,zCodeDBChk=false;
	public static boolean addressee,po,aL1,aL2,cT,area,poI,poO,zC,c;
	public static String[] errDB=new String[16];
	public static String[] errIP =new String[16], errCol=new String[16];
	public static int[] errIterate=new int[16];
	public static Map<String, String> oldSortCodeAcNumber,newSortCodeAcNumber = new LinkedHashMap<String, String>();
	public static List<String> switchDateFromXML, redirectionDateFromXML,odSCACFromXML, newSCACFromXML = new ArrayList<String>();
	public static boolean isPass; 
	public static List<String> tagValueList=  new ArrayList<String>();
	public static int returnStringArraySize;
	public static String actualResult_fail="";
		
	
	/* 
	 * Method Name: ValidateXMLWithCSV
	 * Author: Hemapriya Shanmugam
	 * Created Date: 25-Apr-2017
	 * Description: For CASS File, the tag value from Input XML is validated against the Output CSV file
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam
	 * Last Modified Date: 25-Apr-2017
	 */
public static boolean ValidateXMLWithCSV(Scanner sc_Op,String[] oldSCAC, String[] newSC, String[] newAC, int noOfRecords)
{
	boolean isMatching = false;
	boolean result_cass_old = false;
	boolean result_cass_newSC = false;
	boolean result_cass_newAC = false;
	String scannedLine;
	String tempString;
	int noOfOutputLines=0;

	try
	{
	while(sc_Op.hasNextLine())
	{
		for(int b=0; b < noOfRecords; b++)
		{
		/*	if (swtchDate[b].after(today) && redrctnDate[b].before(today))
			{*/
			scannedLine = sc_Op.nextLine();
			tempString = scannedLine.substring(0, 14);
			result_cass_old = oldSCAC[b].equals(tempString);
			tempString = scannedLine.substring(15, 21);
			result_cass_newSC = newSC[b].equals(tempString);
			tempString = scannedLine.substring(22, 30);
			result_cass_newAC = newAC[b].equals(tempString);

			noOfOutputLines ++;
			if (result_cass_old && result_cass_newSC && result_cass_newAC && noOfOutputLines == noOfRecords)
			{
				isMatching = true;
			}
			else
			{
				isMatching = false;
			}
		/*}
			else
			{
				isMatching = false;
				String actualResult = "The switch date and redirecton date of the records are not inappropriate with Swtich Date: " + swtchDate[b] + " & Redirection Date: " + redrctnDate[b] + " for the Old (SortCode +Account Number): " + oldSCAC[b]; 
				String expectedResult = "The switch date should > current date and redirection date should be < current date for the output to be generated properly";
				publishResults(isMatching, actualResult, expectedResult, "*******CASS******");
			}*/
		}
		}	
		}
		catch (NoSuchElementException ns)
		{
			System.out.println("Output File contains lesser number of records than the number of records inputted.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	return isMatching;
	}
	/* 
	 * Method Name: referenceData_processingInputFile
	 * Author: Hemapriya Shanmugam
	 * Created Date: 25-Apr-2017
	 * Description: For All FileTypes, command line SSIS Package Processing is triggered and validated according to expectedStatus passed.
	 * 				For expectedStatus: 0 -> SSIS Pass is expected & DB Audit Status 0 are validated
	 * 				For expectedStatus: 2 -> SSIS Fail is expected & DB Audit Status 2 are validated
	 * 				For Outbound creation -> SSIS Execution
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam
	 * Last Modified Date: 25-Apr-2017
	 */
	public static boolean referenceData_processingInputFile(String input, String query, String fileType, int expectedStatus, boolean inbound, String outboundFilePathName)
	{
		String output, remarks="No Fetched Remarks",msg_ER = "******** " + fileType + " ********",actualResult,expectedResult;
		int fileStatus=0;
		boolean isProcessed = false;
		//Processing 1 erroneous file
		try
		{
		output = ICSDBUtilis.sqlCommandExecution(getRefDataValueFromDataSheet(input));
		//Fetching status of the erroneous file
		//Verification: if the error is logged in Audit Table
		
		if(((!(output.toLowerCase().contains("failed")||output.toLowerCase().contains("not valid"))) && expectedStatus 
					== Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1)))
						|| ((output.toLowerCase().contains("failed")||output.toLowerCase().contains("not valid")) && 
								expectedStatus == Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1))))
			{
			try
			
				{
					if(inbound)
					{
						ResultSet rs = 
							ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet(query));
						while(rs.next())
						{						
							fileStatus = rs.getInt(2);
							remarks = rs.getString(3);
						}
						isProcessed = fileStatus==expectedStatus;
					
					
						if (isProcessed)
						{
						 expectedResult = "The File should get processed with a Status of " + expectedStatus;
						 actualResult= "The File got processed with a status of " + fileStatus + " & Remarks as '" + remarks + "'";
						}
						else
						{
							expectedResult = "The File should get processed with a Status of " + expectedStatus;
							 actualResult= "The File got processed with a status of " + fileStatus + " & Remarks as '" + remarks + "'";					
						}
					}
					else
					{
						//Outbound generation validation
						File file = new File(getRefDataValueFromDataSheet(outboundFilePathName));
						isProcessed = file.exists();

							if(isProcessed)
							{
							expectedResult = "The Outbound SSIS Package should run successfully and Outbound should be generated";
							actualResult= "The Outbound SSIS Package should run successfully";
							}
							else
							{
							expectedResult = "The Outbound SSIS Package should run successfully and Outbound should be generated";
							actualResult= "The Outbound SSIS Package ran successfully but outbound did not get generated";				
							}
					}
					
				publishResults(isProcessed,actualResult,expectedResult,msg_ER);
			}
			
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
			else
			{
				isProcessed = false;
				expectedResult = "SSIS Package should get processed successfully";
				actualResult = "SSIS Package did not process the file successfully with these command line errors: '" + output.substring(output.indexOf(':')+11,output.indexOf(']')+8) +"'";
				publishResults(isProcessed,actualResult,expectedResult,msg_ER);
			}
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isProcessed;		

	}
	/* 
	 * Method Name: deleteFilesInFilePath
	 * Author: Hemapriya Shanmugam
	 * Created Date: 25/04/2017
	 * Description: Irrespective of Product, file in the filePath (Format: "\\filePath\\fileName.ext" in argument) are deleted at File level
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam
	 * Last Modified Date: 25-Apr-2017
	 */
	public static File deleteFilesInFilePath(String filePath)
	{
		File file = new File(filePath);
		while(file.exists())
		{
			file.delete();
		}
		return file;
	}
	
	/* 
	 * Method Name: getTagValueByXPATH
	 * Author: Hemapriya Shanmugam 
	 * Created Date: 15-May-2017
	 * Description: Gets String Array TAG VALUE using XPATH from XMLs CALL 2 METHODS TOGETHER
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */	
	
	public static void getTagValueByXPATHFetchList(String filePath,String fileName, String xPath, String tagName) throws Exception		
	{
		Map<String, String> nodeValue;
		nodeValue =  getXMLNodeValueByXPATH(getRefDataValueFromDataSheet(filePath),getRefDataValueFromDataSheet(fileName),xPath);		
		tagValueList = getNodeListValues(nodeValue, tagName);
		returnStringArraySize = tagValueList.size();
	}
	
	/* 
	 * Method Name: getTagValueByXPATHReturnStringArray
	 * Author: Hemapriya Shanmugam 
	 * Created Date: 15-May-2017
	 * Description: Returns the tag values List from XPATH to String Array
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */	
	
	public static String[] getTagValueByXPATHReturnStringArray() throws Exception
	{		
		String[] tagValue = new String[returnStringArraySize];
		for (int i = 0; i<returnStringArraySize;i++)
			{
				tagValue[i]=tagValueList.get(i);
			}
		return tagValue;			
	}

	/*
	 * Method Name: validateDBWhenSSISFails
	 * Author: Hemapriya Shanmugam
	 * Created Date: 14-May-2017
	 * Method Description: When file processing fails at SSIS Package Level there should not be any DB Entry
	 * Reviewed By: Venkatasainath Devisetty
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */
	public static boolean validateDBWhenSSISFails(String fileType,String input, String query) throws Exception
	{
		String output,actualresult,expectedresult;
		boolean result=false;
		int CountBeforeCommand=0,CountAfterCommand=0;
		
		ResultSet rs_before = 
				ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet(query));
				
				
		while(rs_before.next())
		{			
			try
			{
				CountBeforeCommand = rs_before.getInt(1);
				//Processing from Input location that doesn't have input file
				output = ICSDBUtilis.sqlCommandExecution(getRefDataValueFromDataSheet(input));
				//Getting Count of After Processing Audit Table rows
				ResultSet rs_after = 
						ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet(query));
				while(rs_after.next())
				{	
					CountAfterCommand = rs_after.getInt(1);		
				}		
				
			}
			catch(Exception e)
			{
				output = null;
			}
			result = (((output.toLowerCase().contains("failed") || output.toLowerCase().contains("not valid"))&& CountBeforeCommand == CountAfterCommand) || ((output.toLowerCase().contains("failed") || output.toLowerCase().contains("not valid"))&& (fileType == "EISCD-Null Bank Code" || fileType == "EISCD-Null Abbreviated Bank Name" || fileType == "EISCD-Null Bank Name")));

			if (result)
			{ 
			 	 isPass = result;
				 expectedresult = "Command Line with Input location that doesn't have the valid file should fail";
				 if(output.toLowerCase().contains("not valid"))
					 actualresult = "Command Line with Input location that doesn't have the valid file fails with the output:- '" + output;
				 else
				 actualresult= "Command Line with Input location that doesn't have the valid file fails with the output:- '" + output.substring(output.indexOf(':')+11,output.indexOf(']')+8) + "'";
									
				publishResults(result,actualresult,expectedresult,fileType + " - Failed output at command prompt when Valid File Not available");
			}
			else
			{
				actualresult = "Loading is unexpectedly Successful with output:- '" + output.substring(output.indexOf(':')+11,output.indexOf(']')+8) + "'" ;
				publishResults(result,actualresult,"Loading of File should NOT be successful","Invalid Command line should fail loading at the Command Prompt");
				result = CountBeforeCommand == CountAfterCommand;
				publishResults(result,"DB has been updated with the file unexpected","File should not reach DB as it is expected that the command line for loading itself fails",fileType + " - Invalid Command line should should not load into Database and not update Database");

			}
	}	
		return result;

	}
	
	/*
	 * Method Name:  validateAuditStatusFailMsg
	 * Author: Hemapriya Shanmugam
	 * Created Date: 14-May-2017
	 * Method Description: When file processing fails at SSIS Package Level there should an error message in the Audit Table
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */
	public static boolean validateAuditStatusFailMsg(String fileType,String input, String fileName, String expectedString,int recordCount, String[] expectedStrings) throws Exception
	{	
		String output,query,status = "", expectedResult="Audit Status should be 1 and the error should be logged", msg=fileType + " Audit Status & Error Validation";
		boolean isValid = false;
		String expectedStatus = getRefDataValueFromDataSheet("file_status_fail").substring(0,1);
		String[] errMsg = new String[50];
		int a=0,isValidCounter=0;
		
		output = ICSDBUtilis.sqlCommandExecution(getRefDataValueFromDataSheet(input));
		if((output.toLowerCase().contains("failed"))||(output.toLowerCase().contains("not valid")))
		{
			query = getRefDataValueFromDataSheet("query_AuditFail") + " '%" + fileName + "%'" ;
			ResultSet rs = 
					ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
			
			while(rs.next())
			{
				if(rs.getString(1) == null)
					errMsg[a] ="";
				else
					errMsg[a] = rs.getString(1);
				if(rs.getString(2) != null)
					status = rs.getString(2);
				a++;
			}
			errMsg=removeUnusedSpaceFromArray(errMsg);
			
			if(status.equals(expectedStatus))
				{
				
					if(recordCount == 1)
					{
						isValid=validateFailScenarioDBErrorToExpected(errMsg,expectedString);
						publishResults(isValid,actualResult_fail,expectedResult,msg);
					}
					else
					{
						for (int i=0;i<recordCount;i++)
						{
							expectedString = expectedStrings[i];
							boolean isMatch=validateFailScenarioDBErrorToExpected(errMsg,expectedString);
							publishResults(isMatch,actualResult_fail,expectedResult,msg);
							if(isMatch)
								isValidCounter++;

						}
						isValid = (isValidCounter == recordCount);
					}
				}
		
			else
			{
				actualResult_fail = "File did not fail with status '" + expectedStatus +"' . The file processed status is: " + status + " .";
				publishResults(isValid,actualResult_fail,expectedResult,msg);
			}
		}
		else
		{
			actualResult_fail = "File Processing did NOT Fail at SSIS Package level";
			publishResults(isValid,actualResult_fail,expectedResult,msg);

		}
				return isValid;
	}
	
	/*
	 * Method Name:  validateAuditStatusFailMsg
	 * Author: Hemapriya Shanmugam
	 * Created Date: 14-May-2017
	 * Method Description: When EISCD File Pass, certain DB Tables get inserted with data and certain do not which is validated
	 * 						Validation is done using the COUNT(ROWS IN EACH TABLE) as the input file has 16 records by default
	 * 						Pre-requisite: Delete all rows in which COUNT(ROWS IN EACH TABLE) are to be fetched 
	 * 														--> SHOULD COVER prerequisite using EISCD_DBCleanup()
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */
	public static boolean validateFailScenarioDBErrorToExpected(String[] errMsg,String expectedString)
	{
		int matchCounter=0;
		for(int i=0;i<errMsg.length;i++)
		{
	
		if (errMsg[i] == "")
			actualResult_fail = "No Error Log Details are logged";
		else
			{
				if(errMsg[i].contains(expectedString))
					{	
						matchCounter++;
						actualResult_fail = "Audit Status is '1' and Error Message is as expected which is => " + errMsg[i];
					}	
			}
		}
		if (matchCounter ==0)
			actualResult_fail = "Status is '1' but Error Message is as NOT having the expected error message => " + expectedString;
		
		return (matchCounter!=0);

	}
	
	/*
	 * Method Name:  validateAuditStatusFailMsg
	 * Author: Hemapriya Shanmugam
	 * Created Date: 14-May-2017
	 * Method Description: When EISCD File Pass, certain DB Tables get inserted with data and certain do not which is validated
	 * 						Validation is done using the COUNT(ROWS IN EACH TABLE) as the input file has 16 records by default
	 * 						Pre-requisite: Delete all rows in which COUNT(ROWS IN EACH TABLE) are to be fetched 
	 * 														--> SHOULD COVER prerequisite using EISCD_DBCleanup()
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */
	public static boolean validateEISCDTablesForPass(int bank,int bankOff,int bankOffAdd,int bankOffTele,int eiscdCnCCC, String tableTobeNull) throws Exception
	{
		boolean dbbank= false,dbbankOff= false,dbbankOffAdd= false,dbbankOffTele= false,dbeiscdCnCCC = false,isValid=false; 
		String actualResult, expectedResult=tableTobeNull + " should have records accordingly", msg = "EISCD XML Null Records DB Table Validation";
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_EISCDBank"));
		ResultSet rs_bo =ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_EISCDBankOffice"));
		ResultSet rs_add = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_EISCDBOACount"));
		ResultSet rs_tele = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_EISCDBankTele"));
		ResultSet rs_cc = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_EISCDCNCCC"));
		
		while(rs.next() && rs_bo.next() && rs_add.next()&& rs_tele.next() && rs_cc.next())
		{
			dbbank=(bank==rs.getInt(1));
			dbbankOff=(bankOff==rs_bo.getInt(1));
			dbbankOffAdd = (bankOffAdd == rs_add.getInt(1));
			dbbankOffTele=(bankOffTele==rs_tele.getInt(1));
			dbeiscdCnCCC=(eiscdCnCCC==rs_cc.getInt(1));
		}
		isValid = (dbbank && dbbankOff && dbbankOffAdd && dbbankOffTele && dbeiscdCnCCC);
		if(isValid)
		{
			isValid = true;
			actualResult = "All the records are updated as per expectation - Fields for " + tableTobeNull + " contains records as per requirement";
		}
		else
		{
			actualResult = "Number of Records associated with " + tableTobeNull + " is NOT appropriate";
		}
		publishResults(isValid,actualResult,expectedResult,msg);
		return isValid;
	}
	/*
	 * Method Name: EISCD_DBCleanup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 14-May-2017
 	 * Method Description: EISCD Files Only - Cleanup in DB Tables Deleting contents of basic EISCD Tables that store data from XML
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */
	public static void EISCD_DBCleanup() throws Exception
	{		
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("cleanup_67653"));	
	}
	
	/*
	 * Method Name: SpecialInstruction_DBCleanup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 25-Sep-2017
 	 * Method Description: EISCD Files Only - Cleanup in DB Tables Deleting contents of basic EISCD Tables that store data from XML
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 25-Sep-2017
	 */
	public static void SpecialInstruction_DBCleanup() throws Exception
	{		
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("cleanup_SI"));	
	}
	
	/*
	 * Method Name: ERDMSC_DBCleanup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 14-May-2017
 	 * Method Description: ERDMS Commercial Files Only - Cleanup in DB Tables Deleting contents of basic ERDMS Tables that store data from XML
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */
	public static void ERDMSC_DBCleanup() throws Exception
	{		
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("cleanup_erdmsC"));	
	}
	/*
	 * Method Name: ERDMSA_DBCleanup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 14-May-2017
 	 * Method Description: ERDMS Agency Files Only - Cleanup in DB Tables Deleting contents of basic ERDMS Tables that store data from XML
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */
	public static void ERDMSA_DBCleanup() throws Exception
	{		
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("cleanup_erdmsA"));	
	}
	/*
	 * Method Name: ERDMSR_DBCleanup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 14-May-2017
 	 * Method Description: ERDMS Retail Files Only - Cleanup in DB Tables Deleting contents of basic ERDMS Tables that store data from XML
	 * Comments:
	 */
	public static void ERDMSR_DBCleanup() throws Exception
	{		
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("cleanup_erdmsR"));	
	}
	/*
	 * Method Name: ERDMSI_DBCleanup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 14-Jul-2017
 	 * Method Description: ERDMS Internal Files Only - Cleanup in DB Tables Deleting contents of basic ERDMS Tables that store data from XML
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 21-Jul-2017
	 */
	public static void ERDMSI_DBCleanup() throws Exception
	{		
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("cleanup_erdmsI"));	
	}
	/*
	 * Method Name: Stops_DBCleanup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 11-Sep-2017
 	 * Method Description: Stops Files Only - Cleanup in DB Tables Deleting contents of Stop Tables that store data from File
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 11-Sep-2017
	 */
	public static void Stops_DBCleanup() throws Exception
	{		
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("cleanup_stops"));	
	}
	/*
	 * Method Name: ICREX_DBCleanup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 01-Aug-2017
 	 * Method Description: HSBC Sort Codes ICREX Files Only - Cleanup in DB Tables Deleting contents of basic ICREX Tables that store data from XML
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 05-Aug-2017
	 */
	public static void ICREX_DBCleanup() throws Exception
	{		
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("cleanup_icrex"));	
	}
	/*
	 * Method Name: IDREX_DBCleanup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 01-Aug-2017
 	 * Method Description: HSBC Sort Codes IDREX Files Only - Cleanup in DB Tables Deleting contents of basic IDREX Tables that store data from XML
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 05-Aug-2017
	 */
	public static void IDREX_DBCleanup() throws Exception
	{		
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("cleanup_idrex"));	
	}
	/*
	 * Method Name: ICREX_DBCleanup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 01-Aug-2017
 	 * Method Description: HSBC Sort Codes IDEEX Files Only - Cleanup in DB Tables Deleting contents of basic IDEEX Tables that store data from XML
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 05-Aug-2017
	 */
	public static void IDEEX_DBCleanup() throws Exception
	{		
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("cleanup_ideex"));	
	}
	/*
	 * Method Name: validateERDMSCommTables
 	 * Author: Hemapriya Shanmugam
 	 * Date: 18-May-2017
 	 * Method Description: Validation of Records in ERDMS Commercial Transaction Tables 
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 25-May-2017
	 */
	public static boolean validateERDMSCommTables(int comm, int sI, int sIAddress,int tPI, int hOCA, int customer) throws Exception
	{
		boolean dbComm= false,dbSI= false,dbSIAddress= false,dbTPI= false,dbHOCA = false,dbCust = false, isValid = false; 
		String actualResult, expectedResult="All tables should have records accordingly", msg = "ERDMS Commercial DB Table Validation";
		ResultSet rs_C =ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsC_Comm"));
		ResultSet rs_SI = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsC_SI"));
		ResultSet rs_SIAddress = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsC_SIAddress"));
		ResultSet rs_TPI = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsC_TPI"));
		ResultSet rs_HOCA = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsC_HOCA"));
		ResultSet rs_Cust = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsC_Customer"));

		while(rs_C.next() && rs_SI.next() && rs_SIAddress.next()&& rs_TPI.next() && rs_HOCA.next() && rs_Cust.next())
		{
			dbComm=(comm==rs_C.getInt(1));
			dbSI=(sI==rs_SI.getInt(1));
			dbSIAddress = (sIAddress == rs_SIAddress.getInt(1));
			dbTPI=(tPI==rs_TPI.getInt(1));
			dbHOCA=(hOCA==rs_HOCA.getInt(1));
			dbCust = (customer == rs_Cust.getInt(1));
		}
		isValid = (dbComm && dbSI && dbSIAddress && dbTPI && dbHOCA && dbCust);
		if(isValid)
		{
			actualResult = "All the records are updated as per expectation";
		}
		else
		{
			actualResult = "ERDMS Commercial DB table update/insert is NOT appropriate";
		}
		publishResults(isValid,actualResult,expectedResult,msg);
		return isValid;
	}
	/*
	 * Method Name: validateERDMSAgencyTables
 	 * Author: Hemapriya Shanmugam
 	 * Date: 28-May-2017
 	 * Method Description: Validation of Records in ERDMS Agency Transaction Tables 
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 30-May-2017
	 */	
	public static boolean validateERDMSAgencyTables(int source, int agency, int agencybank,int settlementbank, int chargingbank, int hoca) throws Exception
	{
		boolean dbS= false,dbA= false,dbAb= false,dbSb= false,dbHOCA = false,dbC = false, isValid = false; 
		String actualResult, expectedResult="All tables should have records accordingly", msg = "ERDMS Agency DB Table Validation";
		ResultSet rs_S =ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsA_Source"));
		ResultSet rs_A = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsA_Agency"));
		ResultSet rs_Ab = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsA_AgencyBranch"));
		ResultSet rs_Sb = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsA_SettlementBank"));
		ResultSet rs_C = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsA_ChargingBank"));
		ResultSet rs_H = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsA_HOCA"));

		while(rs_S.next() && rs_A.next() && rs_Ab.next()&& rs_Sb.next() && rs_C.next() && rs_H.next())
		{
			dbS=(source==rs_S.getInt(1));
			dbA=(agency==rs_A.getInt(1));
			dbAb = (agencybank == rs_Ab.getInt(1));
			dbSb=(settlementbank==rs_Sb.getInt(1));
			dbC=(chargingbank==rs_C.getInt(1));
			dbHOCA = (hoca == rs_H.getInt(1));
		}
		isValid = (dbS && dbA && dbAb && dbSb && dbC && dbHOCA);
		if(isValid)
		{
			actualResult = "All the records are updated as per expectation";
		}
		else
		{
			actualResult = "ERDMS Agency DB table update/insert is NOT appropriate";
		}
		publishResults(isValid,actualResult,expectedResult,msg);
		return isValid;
	}
	
	/*
	 * Method Name: validateERDMSRetailTables
 	 * Author: Hemapriya Shanmugam
 	 * Date: 28-Jun-2017
 	 * Method Description: Validation of Records in ERDMS Retail Bank Transaction Tables 
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Aug-2017
	 */	
	public static boolean validateERDMSRetailTables(int source, int retail, int customer,int tpi) throws Exception
	{
		boolean dbS= false,dbR= false,dbT = false, isValid = false; //,dbC= true
		String actualResult, expectedResult="All tables should have records accordingly", msg = "ERDMS Retail DB Table Validation";
		ResultSet rs_S =ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsR_Source"));
		ResultSet rs_R = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsR_Retail"));
		//ResultSet rs_C = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsR_Customer"));
		ResultSet rs_T = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsR_TPI"));

		while(rs_S.next() && rs_R.next() && rs_T.next())//&& rs_C.next()
		{
			dbS=(source==rs_S.getInt(1));
			dbR=(retail==rs_R.getInt(1));
			//dbC = (customer == rs_C.getInt(1));
			dbT=(tpi==rs_T.getInt(1));
		}
		isValid = (dbS && dbR && dbT);//&& dbC 
		if(isValid)
		{
			actualResult = "All the records are updated as per expectation";
		}
		else
		{
			actualResult = "ERDMS Retail DB table update/insert is NOT appropriate";
		}
		publishResults(isValid,actualResult,expectedResult,msg);
		return isValid;
	}

	/*
	 * Method Name: validateERDMSInternalTables
 	 * Author: Hemapriya Shanmugam
 	 * Date: 28-Jul-2017
 	 * Method Description: Validation of Records in ERDMS Internal Bank Transaction Tables 
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Aug-2017
	 */	
	public static boolean validateERDMSInternalTables(int sInt, int intDep, int hoca) throws Exception
	{
		boolean dbsInt= false,dbintDep= false,dbHOCA = false, isValid = false; 
		String actualResult, expectedResult="All tables should have records accordingly", msg = "ERDMS Internal DB Table Validation";
		ResultSet rs_sInt =ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsI_Source"));
		ResultSet rs_intDep = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsI_IntDep"));
		ResultSet rs_hoca = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_erdmsI_HOCA"));
		
		while(rs_sInt.next() && rs_intDep.next() && rs_hoca.next())
		{
			dbsInt=(sInt==rs_sInt.getInt(1));
			dbintDep=(intDep==rs_intDep.getInt(1));
			dbHOCA = (hoca == rs_hoca.getInt(1));
		}
		isValid = (dbsInt && dbintDep && dbHOCA );
		if(isValid)
		{
			actualResult = "All the records are updated as per expectation";
		}
		else
		{
			actualResult = "ERDMS Internal DB table update/insert is NOT appropriate";
		}
		publishResults(isValid,actualResult,expectedResult,msg);
		return isValid;
	}
	
	/*
	 * Method Name: validateERDMSInternalTables
 	 * Author: Hemapriya Shanmugam
 	 * Date: 28-Jul-2017
 	 * Method Description: Validation of Records in ERDMS Internal Bank Transaction Tables 
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Aug-2017
	 */	
	public static boolean validateStopsTables(int sH, int sD) throws Exception
	{
		boolean dbsH= false,dbsD= false,isValid = false; 
		String actualResult, expectedResult="All tables should have records accordingly", msg = "STOPS DB Table Validation";
		ResultSet rs_sH =ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_countStopsHeader"));
		ResultSet rs_sD = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_countStopsDetail"));
		
		while(rs_sH.next() && rs_sD.next())
		{
			dbsH=(sH == rs_sH.getInt(1));
			dbsD=(sD == rs_sD.getInt(1));
		}
		isValid = (dbsH && dbsD );
		if(isValid)
		{
			actualResult = "All the records are updated as per expectation";
		}
		else
		{
			actualResult = "Stops DB table update/insert is NOT appropriate";
		}
		publishResults(isValid,actualResult,expectedResult,msg);
		return isValid;
	}
	
	
	/*
	 * Method Name: validateColumnInTable
 	 * Author: Hemapriya Shanmugam
 	 * Date: 15-Jul-2017
 	 * Method Description: Validation the presence of a column in a particular Table
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Aug-2017
	 */	
	public static boolean validateColumnInTable(String columnName, String tableName) throws Exception
	{
		String query = "SELECT " + columnName + " FROM " + tableName + " ";
		String expectedResult = "The " + tableName + "is expected to have a column by name " + columnName , msg = "Presence of " + columnName + " in " + tableName,actualResult=columnName + " is present in the table: " + tableName;
		int presenceCounter = 0;
		boolean isColumnPresent=false;
		try
		{
			ResultSet rs = 	ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
			while(rs.next())
			{
				presenceCounter++;
			}
			if(presenceCounter > 0)
				isColumnPresent = true;
			publishResults(isColumnPresent, actualResult, expectedResult, msg);

		}
		catch(SQLException e)
		{
			int code =  e.getErrorCode();
			if(code == 207)// ("com.microsoft.sqlserver.jdbc.SQLServerException: Invalid column name 'ParticipantI'."))
				publishResults(isColumnPresent, columnName + " is not present in the table: "+ tableName, expectedResult, msg);
			else
				e.printStackTrace();
		}
		return isColumnPresent;	
	}	
	/*
	 * Method Name: validateHSBCSortCodes
 	 * Author: Hemapriya Shanmugam
 	 * Date: 12-Jun-2017
 	 * Method Description: HSBC Sort Code Validation With File Data and DB
 	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 18-Jun-2017
	 */
	public static boolean validateHSBCSortCodes(String[] fileHeaderContents, String[] fileDataContents, String hsbcSortCodeType) throws Exception
	{	
		String headerTable, dataTable,itemLimit,referenceValue="CreditReferenceValue", referenceLength="CreditReferenceLength";
		String expectedResult = "The " + hsbcSortCodeType + " filewith record Length = 34/36 is expected to have 34 records saved in Tables", msg = hsbcSortCodeType + " DB Validation",actualResult= "The " + hsbcSortCodeType + " file with record Length = 34/36 has the first 34 records saved in Tables";
		boolean isHeader = false, isData = false;
		if(hsbcSortCodeType.equals("ICREX"))
			{
			headerTable = "Base.HSBCICREX1Header";
			dataTable = "Base.HSBCICREX1Detail";
			itemLimit = "CreditItemLimit";
			}
		else
		{
			if(hsbcSortCodeType.toUpperCase().equals("IDREX"))
			{
				headerTable = "Base.HSBCIDREX1Header";
				dataTable = "Base.HSBCIDREX1Detail";
				itemLimit = "DebitItemLimit";
				referenceValue="DebitReferenceValue";
				referenceLength="DebitReferenceLength";
			}
			else
			{
				headerTable = "Base.HSBCIDEEX1Header";
				dataTable = "Base.HSBCIDEEX1Detail";
				itemLimit = "OutclearingItemLimit";

			}
		}
		int counterData = 0;
		String query_header = getRefDataValueFromDataSheet("query_HSBCHeader") + " " + headerTable;
		String query_data = "SELECT RecordCode, ArgumentValue, SortCode, BranchType, " + itemLimit + ", ArgumentResult1, " + referenceValue + ", " + referenceLength + ", ArgumentResult2 FROM " + dataTable;
		ResultSet rs_header = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query_header);
		ResultSet rs_data = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query_data);
		String headerLine, dataLine;
		while(rs_header.next())
		{
			headerLine = rs_header.getString(1) + rs_header.getString(2) + rs_header.getString(3) + rs_header.getString(4) + rs_header.getString(5) + Strings.padStart(rs_header.getString(6),3,'0') + Strings.padStart(rs_header.getString(7),2,'0') + rs_header.getString(8) + Strings.padStart(rs_header.getString(9),3,'0') + Strings.padStart(rs_header.getString(10), 2, '0') + rs_header.getString(11) ;
			isHeader = fileHeaderContents[0].substring(0,34).equals(headerLine);	
			if (!isHeader)
			{
				actualResult = "The " + hsbcSortCodeType + " file with Header Record Length = 34/36 does not have first 34 records in DB for(/from) the record line: " + headerLine;
				break;
			}
		}
		publishResults(isHeader,actualResult,expectedResult,msg);
		
		while(rs_data.next())
		{
			dataLine = rs_data.getString(1) + rs_data.getString(2) + rs_data.getString(3) + rs_data.getString(4) + rs_data.getString(5) + rs_data.getString(6) + rs_data.getString(7) + rs_data.getString(8) + Strings.padEnd(rs_data.getString(9), 4, '0') ;
			isData = fileDataContents[counterData].substring(0, 34).equals(dataLine);
			counterData++;
			if (!isData)
			{
				actualResult = "The " + hsbcSortCodeType + " file with Data Record Length = 34/36 does not have first 34 records in DB for(/from) the record line: " + dataLine;
				break;
			}
		}
		isData = isData && (counterData == fileDataContents.length);
		publishResults(isData,actualResult,expectedResult,msg);
		return (isData && isHeader && counterData == fileDataContents.length);
	}
	
	/*
	 * Method Name: EISCD_DBBackup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 14-May-2017
 	 * Method Description: EISCD Files Only - Backup in DB Tables Deleting contents of basic EISCD Tables that store data from XML 
 	 * 							- provide appropriate suffix(like Base.EISCDBank_backup with backup being suffix) for your backup table in  the argument
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */
	public static void EISCD_DBBackup() throws Exception
	{
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_EISCDBackUp"));
	}
	/*
	 * Method Name: EISCD_ValidateCountComparingWithBackup
 	 * Author: Hemapriya Shanmugam
 	 * Date: 23-May-2017
 	 * Method Description: EISCD Files Only - DELETE SCENARIO Validate if the backup db records = actual db records
 	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 30-May-2017
	 */
	public static boolean EISCD_ValidateCountComparingWithBackup() throws Exception
	{
		int count_b=0,count_bO=0,count_bOA=0,count_bT=0,count_cnccc=0;
		boolean isValid = false;
		String expectedResult = "First File's records should not be deleted after processing second file that is invalid",msg = "EISCD Delete Scenario",actualResult = "The first file's records have been purged";
		String bank = getRefDataValueFromDataSheet("query_EISCDCompareBank");
 
		String bO =	getRefDataValueFromDataSheet("query_EISCDCompareBankOffice");

		String bOA = getRefDataValueFromDataSheet("query_EISCDCompareBankOfficeAdd");

		String bT =	getRefDataValueFromDataSheet("query_EISCDCompareTelephone");

		String cnccc =	getRefDataValueFromDataSheet("query_EISCDCompareCNCCC");
				 
		ResultSet rs_b= ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), bank);
		ResultSet rs_bO= ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), bO);
		ResultSet rs_bOA= ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), bOA);
		ResultSet rs_bT= ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), bT);
		ResultSet rs_cnccc= ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), cnccc);
		
		while(rs_b.next() && rs_bO.next() && rs_bOA.next() && rs_bT.next() && rs_cnccc.next())
		{
			count_b=rs_b.getInt(1);
			count_bO=rs_bO.getInt(1);
			count_bOA=rs_bOA.getInt(1);
			count_bT=rs_bT.getInt(1);
			count_cnccc=rs_cnccc.getInt(1);	
		}
		if(count_b==5 && count_bO==10 && count_bOA==10 && count_bT==10 && count_cnccc==10)
		{
			isValid = true;
			actualResult = "First File's records are NOT deleted and the second file's(which is invalid) records are NOT inserted";
		}
		publishResults(isValid,actualResult,expectedResult,msg);
		return isValid;
	}

	/*
	 * Method Name:  validateAddressTagValuesForEISCD
	 * Author: Hemapriya Shanmugam
	 * Date: 22-May-2017
	 * Method Description: EISCD Files Only - Address Tag from Input XML is validated against DB Table Value - Base.BankOfficeAddress
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 29-May-2017
	 */
	public static boolean validateAddressTagValuesForEISCD(boolean isEISCDProcessed,boolean isEISCDProcessed_OneInAll,boolean isEISCDOutput_OneInAll) throws Exception
	
	{
		boolean isValid=false;
		int size=0;
		if(isEISCDProcessed || isEISCDProcessed_OneInAll)
		{
		
				addressNameN = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//AddresseeName");			
				postalNameN = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//PostalName");
				addressLine1N = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//AddressLine1");
				addressLine2N = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//AddressLine2");
				cityTownN = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//CityOrTown");
				areaCountryN = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//AreaOrCounty");
				CountryN = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//Country");
				postCodeOutCodeN = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//PostCodeOutcode");
				postCodeInCodeN = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//PostCodeIncode");
				zipCodeN = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//ZipCode");
				
				addressName = getNodeListValues(addressNameN,"AddresseeName");
				postalName = getNodeListValues(postalNameN,"PostalName");
				addressLine1 = getNodeListValues(addressLine1N,"AddressLine1");
				addressLine2 = getNodeListValues(addressLine2N,"AddressLine2");
				cityTown = getNodeListValues(cityTownN,"CityOrTown");
				areaCountry = getNodeListValues(areaCountryN,"AreaOrCounty");
				country = getNodeListValues(CountryN,"Country");
				postOutCode = getNodeListValues(postCodeOutCodeN,"PostCodeOutcode");
				postInCode = getNodeListValues(postCodeInCodeN,"PostCodeIncode");
				zipCode = getNodeListValues(zipCodeN,"ZipCode");
		
				size = addressNameN.size();
		}
		

	String[] addresseeDB = new String[size], pONameDB = new String[size],aL1DB = new String[size],aL2DB = new String[size],cTDB = new String[size],areaDB = new String[size],countryDB = new String[size],poOutCodeDB = new String[size],poInCodeDB = new String[size],zCodeDB = new String[size];
	String tempAL2 = null,tempZCode=null,tempArea = null;
	int j =0;
	ResultSet rs;
	
		rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_67658"));
	
	
	while(rs.next())
	{
		if(rs.getString(1) == null)
			addresseeDB[j] ="";
		else
		addresseeDB[j] = rs.getString(1).toString();
		if(rs.getString(2) == null)
			pONameDB[j] ="";
		else
		pONameDB[j] = rs.getString(2).toString();
		if(rs.getString(3) == null)
			aL1DB[j] ="";
		else
		aL1DB[j] = rs.getString(3).toString();

		if(rs.getString(4) == null)
			aL2DB[j] =tempAL2;
		else
		{
			tempAL2=rs.getString(4).toString();
			aL2DB[j] = tempAL2;
		}
		if(rs.getString(5) == null)
			cTDB[j] ="";
		else
			cTDB[j] = rs.getString(5).toString();
		
		if(rs.getString(6) == null)
			areaDB[j] = tempArea;
		else
		{
			tempArea = rs.getString(6).toString();
			areaDB[j] = tempArea;
		}
		if(rs.getString(7) == null)
			countryDB[j] ="";
		else
		countryDB[j] = rs.getString(7).toString();
		if(rs.getString(8) == null)
			poOutCodeDB[j] ="";
		else
		poOutCodeDB[j] = rs.getString(8).toString();
		if(rs.getString(9) == null)
			poInCodeDB[j] ="";
		else
		poInCodeDB[j] = rs.getString(9).toString();
		
		if(rs.getString(10) == null)
			zCodeDB[j] = tempZCode;
		else
		{
			tempZCode = rs.getString(10).toString();
			zCodeDB[j] = tempZCode;
		}		
		
		tempAL2=tempArea=tempZCode=null;
		j++;
	}
		
		for(int i=0;i<addressNameN.size();i++)
			{
			 aL2DBNull = ((aL2DB[i] == null) && (addressLine2.get(i)== ""));
			 areaDBNull = ((areaDB[i] == null) && (areaCountry.get(i)==""));
			 zCodeDBNull = ((zCodeDB[i] == null) && (zipCode.get(i)==""));
			 
			 if(!aL2DBNull)
			 { aL2DBChk = (aL2DB[i].equals(addressLine2.get(i).toString()));}
			 if(!areaDBNull)
				{areaDBChk = (areaDB[i].equals(areaCountry.get(i).toString()));}
			 if(!zCodeDBNull)
				{zCodeDBChk=(zCodeDB[i].equals(zipCode.get(i).toString()));}
			 addressee=addresseeDB[i].equals(addressName.get(i));
			 po =pONameDB[i].equals(postalName.get(i)); 
			 aL1=aL1DB[i].equals(addressLine1.get(i));
			 cT=cTDB[i].equals(cityTown.get(i));
			 poO= poOutCodeDB[i].equals(postOutCode.get(i));
			 c=countryDB[i].equals(country.get(i));
			 poI=poInCodeDB[i].equals(postInCode.get(i));
			 aL2= (aL2DBNull || aL2DBChk);
			 area= (areaDBNull || areaDBChk);
			 zC=(zCodeDBNull || zCodeDBChk) ;
							
				if(addressee && po && aL1 && aL2 && cT && poO && c && poI && aL2 && area && zC)
					matchCounter++;
			 	else
			 	{
				 	if(!addressee){errCol[notMatchCounter]="Addressee Name";errDB[notMatchCounter]=addresseeDB[i];errIP[notMatchCounter]=addressName.get(i).toString();errIterate[notMatchCounter]=notMatchCounter;}
					if(!po){errCol[notMatchCounter]="Postal Name";errDB[notMatchCounter]=pONameDB[i];errIP[notMatchCounter]=postalName.get(i).toString();errIterate[notMatchCounter]=notMatchCounter;}
					if(!aL1){errCol[notMatchCounter]="Address Line 1";errDB[notMatchCounter]=aL1DB[i];errIP[notMatchCounter]=addressLine1.get(i).toString();errIterate[notMatchCounter]=notMatchCounter;}
					if(!aL2){
						errCol[notMatchCounter]="Address Line 2";
						if(aL2DB[i]== "") errDB[notMatchCounter]=""; else errDB[notMatchCounter]=aL2DB[i];
						if(addressLine2.get(i)== "") errIP[notMatchCounter]=""; else errIP[notMatchCounter]=addressLine2.get(i).toString();
						errIterate[notMatchCounter]=notMatchCounter;}
					if(!cT) {errCol[notMatchCounter]="City/Town";errDB[notMatchCounter]=cTDB[i];errIP[notMatchCounter]=cityTown.get(i).toString();errIterate[notMatchCounter]=notMatchCounter;}
					if(!area){errCol[notMatchCounter]="Area/County";
						if(areaDB[i]=="") errDB[notMatchCounter]="";else errDB[notMatchCounter]=areaDB[i];
						if(areaCountry.get(i) == "") errIP[notMatchCounter]=""; else errIP[notMatchCounter]=areaCountry.get(i).toString();
						errIterate[notMatchCounter]=notMatchCounter;}
					if(!c){errCol[notMatchCounter]="Country";errDB[notMatchCounter]=countryDB[i];errIP[notMatchCounter]=country.get(i).toString();errIterate[notMatchCounter]=notMatchCounter;}
					if(!poO){errCol[notMatchCounter]="Post Out Code";errDB[notMatchCounter]=poOutCodeDB[i];errIP[notMatchCounter]=postOutCode.get(i).toString();errIterate[notMatchCounter]=notMatchCounter;}
					if(!poI){errCol[notMatchCounter]="Post In Code";errDB[notMatchCounter]=poInCodeDB[i];errIP[notMatchCounter]=postInCode.get(i).toString();errIterate[notMatchCounter]=notMatchCounter;}
					if(!zC){errCol[notMatchCounter]="Zip Code";
						if(zCodeDB[i] == "") errDB[notMatchCounter]="";else errDB[notMatchCounter]=zCodeDB[i];
						if(zipCode.get(i) == "") errIP[notMatchCounter]=""; else errIP[notMatchCounter]=zipCode.get(i).toString();
						errIterate[notMatchCounter]=notMatchCounter;}						
					
				notMatchCounter++;
			 	}	
			 			
				aL2DBNull=areaDBNull=zCodeDBNull=aL2DBChk=areaDBChk=zCodeDBChk=false;
			}
	
	
		if(matchCounter == addressNameN.size())
		{
			isValid=true;
			publishResults(isValid,"Input file records for Address matches with DB records ", "Input File Records for Address should match with DB records","EISCD MultiAgency File Processing");
		}
		else
		{
		
			String actualResult = null; 
			for(int k=0; k<notMatchCounter; k++)
			{
			 actualResult = "Unmatched Record for Field: " + errCol[k] +" Input Value: " + errIP[k] + " DB Value: " + errDB[k];
				publishResults(isValid,actualResult, "Input File Records should match with DB records","EISCD MultiAgency File Processing");
			}
		}
		return isValid;
}
	/*
	 * Method Name:  validateCommonFiles
	 * Author: Hemapriya Shanmugam
	 * Date: 23-May-2017
	 * Method Description: All Reference Data File Types processing for particular Expected Status &
	 * 					Output File Compare Validation for matching input & output xmls
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 30-May-2017
	 */
	public static boolean validateInboundCommonFiles(String input, String query, String fileType,int expectedStatus,boolean outputValidationRequired,String outputFilePath, String inputFilePath,boolean isFileProcessedFromPreviousForReprocess)
	
	{
		String expectedResult,actualResult,msg;
		boolean isFileProcessed = isFileProcessedFromPreviousForReprocess,isFile = false,isValid = false;
		if(expectedStatus == Integer.parseInt(getRefDataValueFromDataSheet("file_status_success").substring(0,1))&& (!isFileProcessedFromPreviousForReprocess))
		{
			isFileProcessed = referenceData_processingInputFile(input,query,fileType,expectedStatus,true,"");
			isFile = isFileProcessed;
			isValid = isFile;
		}
		if ( outputValidationRequired && isFileProcessed)
		{
			isFile=isFileProcessed;
			expectedResult = fileType + " Output File should be having matching fields as in input file";
			msg= fileType + " Output File Validation";

			isValid = CompareFile(getRefDataValueFromDataSheet(outputFilePath),getRefDataValueFromDataSheet(inputFilePath),"A");
			if(isValid)
				{
				actualResult = fileType + " Output File is matching with INPUT FILE";
				}
			else{
				actualResult = fileType + " Output File is not appropriate";
				}
			publishResults(isValid,actualResult,expectedResult,msg);

		}

		
		if(expectedStatus== Integer.parseInt(getRefDataValueFromDataSheet("file_status_repeat").substring(0,1)))
		{// IMPORTANT: DO NOT ASSIGN THIS LOOP's RETURN VALUE TO EISCDProcessed_ONEINALL VARIABLE
			boolean isFileReprocessed;
			isFileProcessed = isFileProcessedFromPreviousForReprocess;
			expectedResult = "File reprocessing should not generate an output file";
			msg = fileType + " Reprocessed - Output File Existence"; 
			if(isFileProcessed)
			{
				File file = deleteFilesInFilePath(getRefDataValueFromDataSheet(outputFilePath));
				if(!file.exists())			
					{
						isFileReprocessed = referenceData_processingInputFile(input,query,fileType + " REPROCESS",expectedStatus,true,"");
						if(isFileReprocessed)
						{
							if (!file.exists())
							{
								publishResults(isFileReprocessed,"File has been processed twice and output is not generated the second time",expectedResult,msg);
								isFile = isFileReprocessed;
								isValid = isFileReprocessed;
							}
							else
							{
								publishResults(false,"File has been processed twice and output has been generated the second time",expectedResult,msg);
							}
						}
						else{
							publishResults(isFileReprocessed,"File has not been re-processed",expectedResult,msg);
							isFile = isFileReprocessed;
						}

					}
				else
				{
					publishResults(false,"Error in deleting OUTPUTFile",expectedResult,msg);
					isValid = false;
				}

			}
			else
			{
				isFile = isFileProcessed;
				publishResults(isFileProcessed,"File did not get processed even once",expectedResult,msg);
			}
		}
			
		isPass = isValid;
		return isFile;
		
	}
	/*
	 * Method Name:  validateAddressTagValuesForEISCD
	 * Author: Hemapriya Shanmugam
	 * Date: 23-May-2017
	 * Method Description: EISCD Files Only - Telephone Tag from Input XML is validated against DB Table Value - Base.BankOfficeTelephone
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 30-May-2017
	 */
	public static boolean validateTelephoneTagValuesForEISCD(boolean isEISCDProcessed_OneInAll) throws Exception
	{
		Map<String, String> telephoneNode,faxNode;
		List<String> telephoneNumber, faxNumber;
		int matchCounter=0,supervisorCounter=0;
		boolean isValid = false;
	
		if(isEISCDProcessed_OneInAll)
		{
			telephoneNode = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//Telephone");
			faxNode = getXMLNodeValueByXPATH(getRefDataValueFromDataSheet("EISCD_filePath_67653"),getRefDataValueFromDataSheet("EISCD_fileName_67653"),"//SecondTelephoneNumber");
			telephoneNumber = getNodeListValues(telephoneNode,"Telephone");
			faxNumber = getNodeListValues(faxNode,"SecondTelephoneNumber");			
			
			String[] stdDB = new String[telephoneNode.size()], telephoneNoDB = new String[telephoneNode.size()],stdFaxDB = new String[faxNode.size()],faxNoDB = new String[faxNode.size()];
			int j =0;
			ResultSet rs = 
					ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_67653_1"));
			
			while(rs.next())
			{
				stdDB[j] = rs.getString(1).toString();
				telephoneNoDB[j] = rs.getString(2).toString();
				stdFaxDB[j] = rs.getString(3).toString();
				faxNoDB[j] = rs.getString(4).toString();
				j++;
			}
			
			
			String[] std  = new String[telephoneNode.size()];
			String[] telephoneNo = new String[telephoneNode.size()];
			String[] stdFax = new String[telephoneNode.size()];
			String[] faxNo = new String[telephoneNode.size()];
			//String[] supervisor = new String[telephoneNode.size()];
			
			
			for(int i=0;i<telephoneNode.size();i++)
			{						
				std[i] =  telephoneNumber.get(i).toString().replaceAll("\\s", "").substring(0, 3);		
				telephoneNo[i] = telephoneNumber.get(i).toString().replaceAll("\\s", "").substring(3, 11);
				stdFax[i] =  faxNumber.get(i).toString().replaceAll("\\s", "").substring(0, 3);		
				faxNo[i] = faxNumber.get(i).toString().replaceAll("\\s", "").substring(3, 11);	
				
			
				//if(((supervisor[i].equalsIgnoreCase("A")) || (supervisor[i].equalsIgnoreCase("B")) || (supervisor[i].equalsIgnoreCase("C")) || (supervisor[i].equalsIgnoreCase("D"))))
				//	{
						if( (stdDB[i].equals(std[i])) && (telephoneNoDB[i].equals(telephoneNo[i])) && (stdFaxDB[i].equals(stdFax[i])) && (faxNoDB[i].equals(faxNo[i])))
						matchCounter++;
				//	}
				//else  
				//	supervisorCounter++;
			}
			
			if(matchCounter == telephoneNode.size() && supervisorCounter==0)
				{
					isValid = true;
					publishResults(isValid,"Input file records for Telephone and Fax matches with DB records ", "Input File Records for Telephone and Fax should match with DB records","EISCD MultiAgency File Processing");
				}
			else
				{
					publishResults(isValid,"Input file records DO NOT match with DB records", "Input File Records should match with DB records","EISCD MultiAgency File Processing");
				}
	
		}
		return isValid;
	}
/*
 * Method Name:  validateAddressTagValuesForEISCD
 * Author: Hemapriya Shanmugam
 * Date: 24-Jun-2017
 * Method Description: All File - For Fail status, validation that the Output File is NOT generated at output File Path is required
 * 						outPutFile - "\\filePath\\fileName.ext"
 * Reviewed By: Venkatasainath Devisetty
 * Last Modified By: Hemapriya Shanmugam 
 * Last Modified Date: 30-Jun-2017
 */
	public static boolean validateOutputLocationForFail(File outputFile, String fileType)
	{	
		boolean isOutputEmpty=false;
		String expectedResult="File should not be generated at the Output Location", msg = " Invalid File Outbound generation",actualResult;
	
		if(!outputFile.exists())
		{ 	
			isOutputEmpty = true;
			actualResult = "File is not generated in Output Location";
		}
		else
			actualResult = "File is generated at the Output Location";
			publishResults(isOutputEmpty,actualResult,expectedResult,msg)	; 
		return isOutputEmpty;
		
	}
	/*
	 * Method Name:  bcalOutboundValidation
	 * Author: Hemapriya Shanmugam
	 * Date: 15-Jun-2017
	 * Method Description: Outbound Validation for Business Calendar
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Jun-2017
	 */
	public static boolean bcalOutboundValidation() 
	{
		String actualResult="Outbound file of Business Calendar does not contain all the holiday records present in the DB",expectedResult="Outbound file of Business Calendar should contain all the holiday records present in the Database",msg="Business Calendar Outbound Validation";
		boolean isBCal=false;
		//fetch the dates from the xml
		try
		{
			getTagValueByXPATHFetchList("bcal_outputFilePath", "bcal_outputFileName", "//date", "date");
		}
		catch(Exception e)
		{
			e.getMessage().equalsIgnoreCase("Premature end of file");
			actualResult = "Outbound file of Business Calendar is empty. This might be because Business Calendar table is empty";
			publishResults(isBCal, actualResult, expectedResult, msg);

		}
		try
		{
		String[] datesString = new String[returnStringArraySize];
		datesString=getTagValueByXPATHReturnStringArray();

		//fetch dates from db
		ResultSet rs = 
				ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_bcal"));
		int dBCounter=0;
		while(rs.next())
			{
				if(!(datesString[dBCounter].equalsIgnoreCase(rs.getString(1).toString())))
				{
					break;
				}
				
				dBCounter++;
			}
		//compare them and publish results
		if(dBCounter==returnStringArraySize)
		{
			 isBCal = true;
			 actualResult = "Outbound file of Business Calendar contains all the holiday records present in the DB";
		}
		publishResults(isBCal, actualResult, expectedResult, msg);
		return isBCal;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return isBCal;
		}
	}
	/*
	 * Method Name:  getStringFromDelimitedFile
	 * Author: Hemapriya Shanmugam
	 * Date: 20-May-2017
	 * Method Description: From Any Delimited File (Pipe / Comma / etc), each of the lines are stacked as string in a string array
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 22-May-2017
	 */
	public static String[] getStringFromDelimitedFile(String filePath,String RecordId, String delimitor) throws Exception
	{
		List<String> tempSplitList = new ArrayList<String>();
		String[] tempSplit = null;
		String startChar= "";
		if(RecordId.toUpperCase() != "ALL")
			startChar=RecordId.toUpperCase()+ delimitor;	
		@SuppressWarnings("resource")
		Scanner infile = new Scanner(new File(getRefDataValueFromDataSheet(filePath)));
		while(infile.hasNext())
		{
			String s = infile.nextLine();
			if (delimitor.equals(""))
			{
				if((startChar != "") && (s.startsWith(startChar)))
				{
					tempSplitList.add(s);
				}
			}
			else
			{
				if((startChar != "") && (s.startsWith(startChar)))
				{
					tempSplit = s.split(Pattern.quote(delimitor));
				}
				if(startChar == "")
				{
					tempSplit = s.split(Pattern.quote(delimitor));
	
					for (int i = 0; i < tempSplit.length; i++)
					{
						tempSplitList.add(tempSplit[i]);
					}
				}
			}
		}
		if(RecordId.toUpperCase() == "ALL" || delimitor.equals(""))
		{
			tempSplit = tempSplitList.parallelStream().toArray(String[]::new);
		}
			 return tempSplit;
	}
	/*
	 * Method Name:  validateERDMSComm_SIWithDB
	 * Author: Hemapriya Shanmugam
	 * Date: 14-Jun-2017
	 * Method Description: Validate the Special Instruction table field by field for ERDMS Commercial
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Jun-2017
	 */
	public static boolean validateERDMSComm_SIWithDB(String[] inputFilefields, String type, boolean expectedResultOnSelect) throws Exception
	{
		boolean isSIID=false,isSIName=false,isAlternatReturnsAccNumber=false,isAlternateReturnsSortCode=false,isLottedReturn =false,
				isIsDetailedAdviceRequired=false,isRepresentmentCode=false,isSpecialInstructionCreatedDate=false,isPointOfContact=false,isAddressLine1=false,isAddressLine2=false,
				isAddressLine3=false,isAddressPostCode=false,isContactNumber=false,isAlternateContactNumber=false, isTableEntry = false;
		String actualResult = "The Special Instruction values are not appropriately updated in Special Instruction(&Address) table",
				expectedResult = "The Special Instruction values should be inserted in Special Instruction(&Address) table",
				msg = "ERDMS Commercial - Special Instruction DB Validation";
		String query = getRefDataValueFromDataSheet("query_SpecialInstruction");
		int inputFileFieldsCounter = 6;
		if(!(type.toString().toLowerCase().equalsIgnoreCase("Commercial")))
		{
			inputFileFieldsCounter = 0;
			msg = "Special Instruction DB Validation for " + type;			
		}
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		
		while(rs.next())
		{
			isSIID = (rs.getString(1).equals(inputFilefields[inputFileFieldsCounter]));
			isSIName = (rs.getString(2).equals(inputFilefields[inputFileFieldsCounter+1]));
			isAlternatReturnsAccNumber = (rs.getString(3).equals(inputFilefields[inputFileFieldsCounter+2]));
			isAlternateReturnsSortCode = (rs.getString(4).equals( inputFilefields[inputFileFieldsCounter+3]));
			isLottedReturn = (rs.getString(5).equals(inputFilefields[inputFileFieldsCounter+4]));
			isIsDetailedAdviceRequired = (rs.getString(6).equals(inputFilefields[inputFileFieldsCounter+5]));
			isRepresentmentCode = (rs.getString(7).equals(inputFilefields[inputFileFieldsCounter+6]));
			isSpecialInstructionCreatedDate = (rs.getString(8).toString().replaceAll("-", "").equals(inputFilefields[inputFileFieldsCounter+7]));
			isPointOfContact = (rs.getString(9).equals(inputFilefields[inputFileFieldsCounter+8]));
			isAddressLine1 = (rs.getString(10).equals(inputFilefields[inputFileFieldsCounter+9]));
			isAddressLine2 = (rs.getString(11).equals(inputFilefields[inputFileFieldsCounter+10]));
			isAddressLine3 = (rs.getString(12).equals(inputFilefields[inputFileFieldsCounter+11]));
			isAddressPostCode = (rs.getString(13).equals(inputFilefields[inputFileFieldsCounter+12]));
			isContactNumber = (rs.getString(14).equals(inputFilefields[inputFileFieldsCounter+13]));
			isAlternateContactNumber = (rs.getString(15).equals(inputFilefields[inputFileFieldsCounter+14]));		
		}
		isTableEntry = (isSIID && isSIName && isAlternatReturnsAccNumber && isAlternateReturnsSortCode && isLottedReturn  && 
				isIsDetailedAdviceRequired && isRepresentmentCode && isSpecialInstructionCreatedDate && isPointOfContact && isAddressLine1 && isAddressLine2 && 
				isAddressLine3 && isAddressPostCode && isContactNumber && isAlternateContactNumber);
		if ( isTableEntry )
			{
				actualResult = "The Special Instruction values are appropriately updated in Special Instruction(&Address) table";
				publishResults((isTableEntry && expectedResultOnSelect), actualResult, expectedResult, msg);
				return true;
			}
		else
		{
			
			if(!expectedResultOnSelect)
			{
				actualResult = "The Special Instruction values are not shown in Special Instruction(&Address) table";
				expectedResult = "The Special Instruction values should not be shown in Special Instruction(&Address) table";
			}
			publishResults((!expectedResultOnSelect), actualResult, expectedResult, msg);

			return (!expectedResultOnSelect);
	
		}
	}

	/*
	 * Method Name:  validateERDMSAgency_SettlementACWithDB
	 * Author: Hemapriya Shanmugam
	 * Date: 14-Jun-2017
	 * Method Description: Validate the Settlement Bank table field by field for ERDMS Commercial
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Jun-2017
	 */
	public static boolean validateERDMSAgency_SettlementACWithDB(String[] inputFilefields) throws Exception
	{
		boolean isSSC=false,isSAC=false,isIsAggReqd=false,isAgencyId=false;
		String actualResult = "The Settlement Account values are not appropriately updated in SettlementAccount table",
				expectedResult = "The Settlement Account values should be inserted in SettlementAccount table",
				msg = "ERDMS Agency - Settlement Account DB Validation";
		String query;
		query = getRefDataValueFromDataSheet("query_SettlementAc");
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		
		while(rs.next())
		{
			isSSC = (rs.getString(1).equals(inputFilefields[13]));
			isSAC = (rs.getString(2).equals(inputFilefields[14]));
			isIsAggReqd = (rs.getString(3).equals(inputFilefields[15]));
			isAgencyId = (rs.getString(4).equals( inputFilefields[2]));
		}
		boolean isValid = ( isSSC && isSAC && isIsAggReqd && isAgencyId );
		if (isValid)
			{
				actualResult = "The Settlement Account values are appropriately updated in SettlementAccount table";
				publishResults(isValid, actualResult, expectedResult, msg);
				return isValid;
			}
		else
		{
			publishResults(isValid, actualResult, expectedResult, msg);
			return isValid;	
		}
	}

	/*
	 * Method Name:  validateERDMSComm_TPIWithDB
	 * Author: Hemapriya Shanmugam
	 * Date: 14-Jun-2017
	 * Method Description: Validate the TPI table field by field for ERDMS Commercial
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Jun-2017
	 */
	public static boolean validateERDMSComm_TPIWithDB(String[] inputFilefields) throws Exception
	{
		boolean  isTPI_ID=false, isTPI_Name=false, isTPI_Type=false, isTPI_OpenDate=false, isAlternateName1=false, isAlternateName2=false, 
				isAlternateName3=false, isAlternateName4=false, isAlternateName5=false, isAlternateName6=false, isAlternateName7=false, 
				isAlternateName8=false, isAlternateName9=false, isAlternateName10=false, isTPIClosureDate=false, 
				isTPIClosureReasonDescription=false, isTPIBusinessLineOwner =false;
		String actualResult = "The TPI values are not appropriately updated in TPI table",
				expectedResult = "The TPI values should be inserted in TPI table",
				msg = "ERDMS Commercial - TPI DB Validation";
		String query = getRefDataValueFromDataSheet("query_TPIComm");
		
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		
		while(rs.next())
		{
			isTPI_ID = (rs.getString(1).equals(inputFilefields[21]));
			isTPI_Name = (rs.getString(2).equals(inputFilefields[22]));
			isTPI_Type = (rs.getString(3).equals(inputFilefields[23]));
			isTPI_OpenDate = (rs.getString(4).toString().replaceAll("-", "").equals( inputFilefields[24]));
			isAlternateName1 = (rs.getString(5).equals(inputFilefields[25]));
			isAlternateName2 = (rs.getString(6).equals(inputFilefields[26]));
			isAlternateName3 = (rs.getString(7).equals(inputFilefields[27]));
			isAlternateName4 = (rs.getString(8).equals(inputFilefields[28]));
			isAlternateName5 = (rs.getString(9).equals(inputFilefields[29]));
			isAlternateName6 = (rs.getString(10).equals(inputFilefields[30]));
			isAlternateName7 = (rs.getString(11).equals(inputFilefields[31]));
			isAlternateName8 = (rs.getString(12).equals(inputFilefields[32]));
			isAlternateName9 = (rs.getString(13).equals(inputFilefields[33]));
			isAlternateName10 = (rs.getString(14).equals(inputFilefields[34]));
			isTPIClosureDate = (rs.getString(15).toString().replaceAll("-", "").equals(inputFilefields[35]));
			isTPIClosureReasonDescription = (rs.getString(16).equals(inputFilefields[36]));
			isTPIBusinessLineOwner = (rs.getString(17).equals(inputFilefields[37]));
		
		}
		
		if ( isTPI_ID && isTPI_Name && isTPI_Type && isTPI_OpenDate && isAlternateName1 && isAlternateName2 && isAlternateName3 && isAlternateName4 && isAlternateName5 && isAlternateName6 && isAlternateName7 && isAlternateName8 && isAlternateName9 && isAlternateName10 && isTPIClosureDate && isTPIClosureReasonDescription && isTPIBusinessLineOwner )
			{
			
				actualResult = "The TPI values are appropriately updated in TPI table";
				publishResults(true, actualResult, expectedResult, msg);
				return true;
			}
		else
		{
			publishResults(false, actualResult, expectedResult, msg);
			return false;	
		}
	}
	
	/*
	 * Method Name:  validateERDMSComm_CustomerWithDB
	 * Author: Hemapriya Shanmugam
	 * Date: 14-Jun-2017
	 * Method Description: Validate the Customer table field by field for ERDMS Commercial
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Jun-2017
	 */
	public static boolean validateERDMSComm_CustomerWithDB(String[] inputFilefields) throws Exception
	{
		boolean  isCustID=false, isCustName=false, isCustNumber=false, isCustSC=false;
		String actualResult = "The Customer values are not appropriately updated in Customer table",
				expectedResult = "The Customer values should be inserted in Customer table",
				msg = "ERDMS Commercial - Customer DB Validation";
		String query = getRefDataValueFromDataSheet("query_CustomerComm");
		
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		
		while(rs.next())
		{
			isCustID = (rs.getString(1).equals(inputFilefields[2]));
			isCustName = (rs.getString(2).equals(inputFilefields[3]));
			isCustNumber = (rs.getString(3).equals(inputFilefields[4]));
			isCustSC = (rs.getString(4).equals(inputFilefields[5]));
		}
		
		if ( isCustID && isCustName && isCustNumber && isCustSC )
			{
				actualResult = "The Customer values are appropriately updated in Customer table";
				publishResults(true, actualResult, expectedResult, msg);
				return true;
			}
		else
		{
			publishResults(false, actualResult, expectedResult, msg);
			return false;	
		}
	}
	
	/*
	 * Method Name:  validateERDMSCommon_HOCAWithDB
	 * Author: Hemapriya Shanmugam
	 * Date: 14-Jun-2017
	 * Method Description: Validate the HOCA table field by field for ALL ERDMS 
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Jun-2017
	 */
	public static boolean validateERDMSCommon_HOCAWithDB(String[] inputFilefields, String fileTypeERDMS) throws Exception
	{
		boolean isHSC=false,isHAC=false,isHEffDate=false,isBusOwner=false,isCrExType =false,
				isCreatedDate=false,isLastUpdatedDate=false, isSetSC = false, isSetAC = false,isModDate=false,isModBy=false;
		String actualResult = "The HOCA values are not appropriately updated in HOCA table",
				expectedResult = "The HOCA values should be inserted in HOCA table",
				msg = "ERDMS" + fileTypeERDMS + " - HOCA DB Validation";
		String query;
		if(fileTypeERDMS.equalsIgnoreCase("Commercial")) query = getRefDataValueFromDataSheet("query_HOCAComm");
		else query = getRefDataValueFromDataSheet("query_HOCAOthers");
		int fieldNumber = 18;
		if(fileTypeERDMS.equalsIgnoreCase("Commercial")) fieldNumber = 40;
		if(fileTypeERDMS.equalsIgnoreCase("Internal")) fieldNumber = 8;
		String TodayDate = LocalDateTime.now().toString().substring(0, 10);
		String userName = System.getenv().get("USERDOMAIN") + "\\" + System.getProperty("user.name");
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		
		while(rs.next())
		{
			isHSC = (rs.getString(1).equals(inputFilefields[fieldNumber]));
			isHAC = (rs.getString(2).equals(inputFilefields[fieldNumber+1]));
			isHEffDate = (rs.getString(3).toString().replaceAll("-", "").equals(inputFilefields[fieldNumber+2]));
			isBusOwner = (rs.getString(4).equals( inputFilefields[fieldNumber+3]));
			isCrExType = (rs.getString(5).equals(inputFilefields[fieldNumber+4]));
			if(fileTypeERDMS.equalsIgnoreCase("Commercial"))
				{	
					isCreatedDate = (rs.getString(6).toString().replaceAll("-", "").equals(inputFilefields[fieldNumber+5]));
					isLastUpdatedDate = (rs.getString(7).toString().replaceAll("-", "").equals(inputFilefields[fieldNumber+6]));
				}
			else 
			{
				isSetSC = (rs.getString(6).equals(inputFilefields[fieldNumber+5]));
				isSetAC = (rs.getString(7).equals(inputFilefields[fieldNumber+6]));

			}
			isModDate = (rs.getString(8).contains(TodayDate));
			isModBy = (rs.getString(9).equalsIgnoreCase(userName));
		}
		boolean isValid = isHSC && isHAC && isHEffDate && isBusOwner && isCrExType  && ((isCreatedDate && isLastUpdatedDate) || (isSetSC && isSetAC)) && isModDate && isModBy;
		if (isValid)
			{
				actualResult = "The HOCA values are appropriately updated in HOCA table";
				publishResults(isValid, actualResult, expectedResult, msg);
				return isValid;
			}
		else
		{
			publishResults(isValid, actualResult, expectedResult, msg);
			return isValid;	
		}
	}

	
	/*
	 * Method Name:  validateERDMSAgency_ChargingWithDB
	 * Author: Hemapriya Shanmugam
	 * Date: 29-Jun-2017
	 * Method Description: Validate the Charging Bank table field by field for ERDMS Agency
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 30-Jun-2017
	 */
	public static boolean validateERDMSAgency_ChargingWithDB(String[] inputFilefields) throws Exception
	{
		boolean isCSC=false,isCAC=false;
		String actualResult = "The ChargingAccount values are not appropriately updated in ChargingAccount table",
				expectedResult = "The ChargingAccount values should be inserted in ChargingAccount table",
				msg = "ERDMS Agency - Charging Account DB Validation";
		String query;
		query = getRefDataValueFromDataSheet("query_ChargingComm");
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		
		while(rs.next())
		{
			isCSC = (rs.getString(1).equals(inputFilefields[16]));
			isCAC = (rs.getString(2).equals(inputFilefields[17]));
			}
		boolean isValid = ( isCSC && isCAC );
		if (isValid)
			{
				actualResult = "The Charging Account values are appropriately updated in ChargingAccount table";
				publishResults(isValid, actualResult, expectedResult, msg);
				return isValid;
			}
		else
		{
			publishResults(isValid, actualResult, expectedResult, msg);
			return isValid;	
		}
	}
	/*
	 * Method Name:  validateERDMSAgencyBranchWithDB
	 * Author: Hemapriya Shanmugam
	 * Date: 14-Jun-2017
	 * Method Description: Validate the Agency Bank table field by field for ERDMS Agency
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 14-Jun-2017
	 */
	public static boolean validateERDMSAgencyBranchWithDB(String[] inputFilefields) throws Exception
	{
		boolean  isAB=false, isABSC=false, isABP=false, isABScheme=false, isABPayScheme=false, isABPartSC=false;
		String actualResult = "The Agency Branch values are not appropriately updated in AgencyBranch table",
				expectedResult = "The Agency Branch values should be inserted in AgencyBranch table",
				msg = "ERDMS Agency - AgencyBranch DB Validation";
		String query = getRefDataValueFromDataSheet("query_AgencyBranch");
		
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		
		while(rs.next())
		{
			isAB = (rs.getString(1).equals(inputFilefields[7]));
			isABSC = (rs.getString(2).equals(inputFilefields[8]));
			isABP = (rs.getString(3).equals(inputFilefields[9]));
			isABScheme = (rs.getString(4).equals(inputFilefields[10]));
			isABPayScheme = (rs.getString(5).equals(inputFilefields[11]));
			isABPartSC = (rs.getString(6).equals(inputFilefields[12]));
		}
		
		if ( isAB && isABSC && isABP && isABScheme && isABPayScheme && isABPartSC )
			{
				actualResult = "The Agency Branch values are appropriately updated in AgencyBranch table";
				publishResults(true, actualResult, expectedResult, msg);
				return true;
			}
		else
		{
			publishResults(false, actualResult, expectedResult, msg);
			return false;	
		}
	}

	/*
	 * Method Name:  validateERDMSInternal_HeaderAndFooterWithDB
	 * Author: Hemapriya Shanmugam
	 * Date: 05-Jul-2017
	 * Method Description: Validate the Source table Header And Footer field by field for ERDMS Internal
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 05-Jul-2017
	 */
	public static boolean validateERDMSInternal_HeaderAndFooterWithDB(String[] HeaderFilefields,String[] FooterFilefields) throws Exception
	{
		boolean isHDate=false,isHLastDate=false,isTRecId=false,isTLastDate=false;
		String actualResult = "The Header & Footer Record values are not appropriately updated in SourceInternal table",
				expectedResult = "The Header & Footer Record values should be inserted in SourceInternal table",
				msg = "ERDMS Internal - SourceInternal Table Validation For Header&Footer";
		String query_Header,query_Trailer;
		query_Header = getRefDataValueFromDataSheet("query_IntHeader");
		query_Trailer = getRefDataValueFromDataSheet("query_IntTrDB");
		ResultSet rs_H = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query_Header);
		ResultSet rs_T = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query_Trailer);
		String TodayDate = LocalDateTime.now().toString().substring(0, 10);
		
		while(rs_H.next())
		{
			isHDate = (rs_H.getString(1).replaceAll("[^a-zA-Z0-9]", "").substring(0, 14).equals(HeaderFilefields[2]));
			isHLastDate = (rs_H.getString(2) == null);

		}
		while(rs_T.next())
		{
			isTRecId = (rs_T.getString(1).equals(FooterFilefields[1]));
			isTLastDate = (rs_T.getString(2) == null);

		}
		boolean isValid = ( isHDate && isHLastDate && isTRecId && isTLastDate );
		if (isValid)
			{
				actualResult = "The Header & Footer Record values are appropriately updated in SourceInternal table";
				publishResults(isValid, actualResult, expectedResult, msg);
				return isValid;
			}
		else
		{
			publishResults(isValid, actualResult, expectedResult, msg);
			return isValid;	
		}
	}
	
	/*
	 * Method Name:  validateERDMSInternal_DataSourceWithDB
	 * Author: Hemapriya Shanmugam
	 * Date: 05-Jul-2017
	 * Method Description: Validate the Source table Data record field by field for ERDMS Internal
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 05-Jul-2017
	 */
	public static boolean validateERDMSInternal_DataSourceWithDB(String[] dataContents) throws Exception
	{
		boolean isRecId= false, isCatId = false, isIntDep = false, isDepCatType = false, isDepName = false, isDepSC = false, isDepSetSC = false, isDepSetAc = false, isHOCASC = false, isHOCAAc = false, isHOCAEffDate = false, isBusLineOwner = false, isCredEx = false, isHOCASetSC = false, isHOCASetAC = false, isCredDate = false, isLastUpdated = false, isModDate = false, isModBy = false;
		String actualResult = "The Data Record values are not appropriately updated in SourceInternal table",
				expectedResult = "The Data Record values should be inserted in SourceInternal table",
				msg = "ERDMS Internal - SourceInternal Table Validation For Data Record";
		String query;
		query = getRefDataValueFromDataSheet("query_IntDataDB");
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		String TodayDate = LocalDateTime.now().toString().substring(0, 10);
		String userName = System.getenv().get("USERDOMAIN") + "\\" + System.getProperty("user.name");
		while(rs.next())
		{
			isRecId = (rs.getString(1).equals(dataContents[0]));
			isCatId = (rs.getString(2).equals(dataContents[1]));
			isIntDep = (rs.getString(3).equals(dataContents[2]));
			isDepCatType = (rs.getString(4).equals(dataContents[3]));
			isDepName = (rs.getString(5).equals(dataContents[4]));
			isDepSC = (rs.getString(6).equals(dataContents[5]));
			isDepSetSC = (rs.getString(7).equals(dataContents[6]));
			isDepSetAc = (rs.getString(8).equals(dataContents[7]));
			isHOCASC = (rs.getString(9).equals(dataContents[8]));
			isHOCAAc = (rs.getString(10).equals(dataContents[9]));
			isHOCAEffDate = (rs.getString(11).replaceAll("[^a-zA-Z0-9]", "").substring(0, 8).equals(dataContents[10]));
			isBusLineOwner = (rs.getString(12).equals(dataContents[11]));
			isCredEx = (rs.getString(13).equals(dataContents[12]));
			isHOCASetSC = (rs.getString(14).equals(dataContents[13]));
			isHOCASetAC = (rs.getString(15).equals(dataContents[14]));
			isCredDate = (rs.getString(16).replaceAll("[^a-zA-Z0-9]", "").substring(0, 8).equals(dataContents[15]));
			isLastUpdated = (rs.getString(17).replaceAll("[^a-zA-Z0-9]", "").substring(0,8).equals(dataContents[16]));
			isModDate = (rs.getString(18).contains(TodayDate));
			isModBy = (rs.getString(19).toString().toLowerCase().equals(userName.toLowerCase()));

		}
		
		boolean isValid = ( isRecId && isCatId && isIntDep && isDepCatType && isDepName && isDepSC && isDepSetSC && isDepSetAc && isHOCASC && isHOCAAc && isHOCAEffDate && isBusLineOwner && isCredEx && isHOCASetSC && isHOCASetAC && isCredDate && isLastUpdated && isModDate && isModBy  );
		if (isValid)
			{
				actualResult = "The Data Record values are appropriately updated in SourceInternal table";
				publishResults(isValid, actualResult, expectedResult, msg);
				return isValid;
			}
		else
		{
			publishResults(isValid, actualResult, expectedResult, msg);
			return isValid;	
		}
	}

	/*
	 * Method Name:  validateERDMSInternal_DataIntDepWithDB
	 * Author: Hemapriya Shanmugam
	 * Date: 05-Jul-2017
	 * Method Description: Validate the Internal Department table Data record field by field for ERDMS Internal
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-May-2017
	 */
	public static boolean validateERDMSInternal_DataIntDepWithDB(String[] dataContents) throws Exception
	{
		boolean  isCatId = false, isIntDep = false, isDepCatType = false, isDepName = false, isDepSC = false, isDepSetSC = false, isDepSetAc = false, isModDate = false, isModBy = false;
		String actualResult = "The Data Record values are not appropriately updated in InternalDepartment table",
				expectedResult = "The Data Record values should be inserted in InternalDepartment table",
				msg = "ERDMS Internal - InternalDepartment Table Validation For Data Record";
		String query;
		query = getRefDataValueFromDataSheet("query_IntDepDB");
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		String TodayDate = LocalDateTime.now().toString().substring(0, 10);
		String userName = System.getenv().get("USERDOMAIN") + "\\" + System.getProperty("user.name");
		while(rs.next())
		{
			isCatId = (rs.getString(2).equals(dataContents[1]));
			isIntDep = (rs.getString(3).equals(dataContents[2]));
			isDepCatType = (rs.getString(4).equals(dataContents[3]));
			isDepName = (rs.getString(5).equals(dataContents[4]));
			isDepSC = (rs.getString(6).equals(dataContents[5]));
			isDepSetSC = (rs.getString(7).equals(dataContents[6]));
			isDepSetAc = (rs.getString(8).equals(dataContents[7]));
			isModDate = (rs.getString(9).contains(TodayDate));
			isModBy = (rs.getString(10).toString().toLowerCase().equals(userName.toLowerCase()));

		}
		
		boolean isValid = ( isCatId && isIntDep && isDepCatType && isDepName && isDepSC && isDepSetSC && isDepSetAc && isModDate && isModBy  );
		if (isValid)
			{
				actualResult = "The Data Record values are appropriately updated in InternalDepartment table";
				publishResults(isValid, actualResult, expectedResult, msg);
				return isValid;
			}
		else
		{
			publishResults(isValid, actualResult, expectedResult, msg);
			return isValid;	
		}
	}
	/*
	 * Method Name: sp_RefOutSpeInstructionCol
 	 * Author: Hemapriya Shanmugam
 	 * Date: 26-Sep-2017
 	 * Method Description: Special Instruction Output Stored Procedures' outputs are verified for correct Column Names 	
 	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 26-Sep-2017
	 */
	public static boolean sp_RefOutColValidation(String spName, String expectedColumnsFile) throws Exception
	{
		spName = getRefDataValueFromDataSheet(spName);
		String actualResult = "The " + spName + " does not contain any invalid ColumnNames",
				expectedResult = "The " + spName + " should not contain any invalid ColumnNames",
				msg = "The " + spName + " Verification for Column Names";
		System.out.println("Executing: " + spName);
		String query = "EXEC " + spName ;
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		ResultSetMetaData rs_m = rs.getMetaData();
		int totalColumns = rs_m.getColumnCount();
		String columnName[] = new String[totalColumns];
		String expectedColumnNames[] =  getStringFromDelimitedFile(expectedColumnsFile, "ALL", ",");;
		
		List<String> expNotPresentColList = new ArrayList<String>();
		if(totalColumns == expectedColumnNames.length)
		{
			for (int i = 0; i < totalColumns; i++)
			{
				columnName[i]=rs_m.getColumnLabel(i+1);
			}
			for(int j = 0; j < totalColumns; j++)
			{ 
				if (!(columnName[j].toString().equals(expectedColumnNames[j].toString())))
				{
					expNotPresentColList.add(expectedColumnNames[j].toString());
				}
			}
		}
		if (expNotPresentColList.size() != 0)
		{
			actualResult = "The " + spName + " does not contain valid Columns which is/are " + expNotPresentColList;
			publishResults(false, actualResult, expectedResult, msg);
			return (false);
		}
		else
		{
			publishResults(true, actualResult, expectedResult, msg);
			return (true);	
		}
	}
	/*
	 * Method Name: sp_OutboundValidation 
 	 * Author: Hemapriya Shanmugam
 	 * Date: 14-Aug-2017
 	 * Method Description: All Output Stored Procedures' outputs are verified for duplicate Columns [DataValidation With Outbound  vs StoredProcedure]	
 	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Aug-2017
	 */
	public static boolean sp_OutboundValidation(String[] dataContents, String type) throws Exception
	{
		String actualResult = "The " + type + " Stored Procedure and corresponding outbound have same records",
				expectedResult = "The " + type + " Stored Procedure and corresponding outbound should contain same records",
				msg = "The " + type + " Stored Procedure and Outbound Validation";
		boolean isMatch=false;
		
		List<String> spContentsList = new ArrayList<String>();
		String spName,queryForSP,queryForCount ;
		if(type == "LBG")
		{
			queryForCount = "query_LBGSortCode";
			spName = getRefDataValueFromDataSheet("lbg_SpName");
		}
		else
		{
			queryForCount = "query_TSBSortCode";
			spName = getRefDataValueFromDataSheet("tsb_SpName");
			
		}
		queryForSP = "EXEC " + spName;

		String TodayDate = LocalDateTime.now().toString().substring(0, 10).replaceAll("[^a-zA-Z0-9]", "");
		System.out.println("Count of DataContents: " + dataContents.length);

		String output = ICSDBUtilis.sqlCommandExecution(getRefDataValueFromDataSheet(queryForCount));
		int count = Integer.parseInt(output.substring(output.indexOf('-')+13, output.indexOf('(')).replaceAll("\\s", "").replaceAll("\\n", "").replaceAll("\\r", ""));
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), queryForSP);		
		String totalCount = Integer.toString(count);
		while(rs.next())
		{
			if(rs.getString(1) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(1).toString());
			if(rs.getString(2) == null)
				spContentsList.add("");
			if((rs.getString(2) != null) && (rs.getString(1).toString().equals("2")))
				spContentsList.add(TodayDate);
			if ((rs.getString(2) != null) && (! (rs.getString(1).toString().equals("2"))))
			spContentsList.add(rs.getString(2).toString());
			if((rs.getString(3) == null))
				spContentsList.add("");
			if((rs.getString(3) != null) && (rs.getString(1).toString().equals("2")))
				spContentsList.add(totalCount);
			if (rs.getString(3) != null)
				spContentsList.add(rs.getString(3).toString());
			if(rs.getString(6) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(6).toString());
			if(rs.getString(4) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(4).toString());
			if(rs.getString(5) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(5).toString());
			if(rs.getString(7) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(7).toString());
			if(rs.getString(8) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(8).toString());
		}
		System.out.println("Count Of Records in SP:"+ spContentsList.get(dataContents.length-1).toString());
		if(spContentsList.get(dataContents.length).toString().equals(totalCount))
			{
				System.out.println("sp  " + spContentsList);
				for (int i =0; i<dataContents.length; i++)
				{
					isMatch = (spContentsList.get(i).toString().equals(dataContents[i]));
					if(!isMatch)
					{
						System.out.println("File: " + dataContents[i] + "  DB/SP: " + spContentsList.get(i).toString());
						break;	
					}
				}
				if(!(isMatch))
				actualResult = "The " + spName + " and outbound does not have matching records";
			}
		else
		{
			actualResult = "The " + spName + " and outbound have different number of records as Expected count of records is " + totalCount + " And Actual Count is " + spContentsList.get(dataContents.length).toString() ;
		}
		publishResults(isMatch, actualResult, expectedResult, msg);
		return isMatch;
	}

	/*
	 * Method Name: sp_OutboundValidation 
 	 * Author: Hemapriya Shanmugam
 	 * Date: 14-Aug-2017
 	 * Method Description: All Output Stored Procedures' outputs are verified for duplicate Columns [DataValidation With Outbound  vs StoredProcedure]	
 	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Aug-2017
	 */
	/*public static boolean hsbcSp_OutboundValidation() throws Exception
	{
		String actualResult = "The HSBCSortCodesToAPG Stored Procedure have appropriate values",
				expectedResult = "The HSBCSortCodesToAPG Stored Procedure should contain values according to On-Us Logic and HSBC SortCode input files",
				msg = "The HSBCSortCodesToAPG Stored Procedure Validation";
		boolean isMatch=false;
		
		List<String> spContentsList = new ArrayList<String>();
		String queryForSP = "EXEC " + getRefDataValueFromDataSheet("hsbc_SpName") ,queryForCount = getRefDataValueFromDataSheet("query_OnUsCount");
		String output = ICSDBUtilis.sqlCommandExecution(getRefDataValueFromDataSheet(queryForCount));
		int count = Integer.parseInt(output.substring(output.indexOf('-')+13, output.indexOf('(')).replaceAll("\\s", "").replaceAll("\\n", "").replaceAll("\\r", ""));
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), queryForSP);		
		String totalCount = Integer.toString(count);
		ResultSet rs_OnUs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet("query_OnUsForHsbc"));

		while(rs.next())
		{
			if(rs.getString(1) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(1).toString());
			if(rs.getString(2) == null)
				spContentsList.add("");
			if((rs.getString(2) != null) && (rs.getString(1).toString().equals("2")))
				spContentsList.add(TodayDate);
			if ((rs.getString(2) != null) && (! (rs.getString(1).toString().equals("2"))))
			spContentsList.add(rs.getString(2).toString());
			if((rs.getString(3) == null))
				spContentsList.add("");
			if((rs.getString(3) != null) && (rs.getString(1).toString().equals("2")))
				spContentsList.add(totalCount);
			if (rs.getString(3) != null)
				spContentsList.add(rs.getString(3).toString());
			if(rs.getString(6) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(6).toString());
			if(rs.getString(4) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(4).toString());
			if(rs.getString(5) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(5).toString());
			if(rs.getString(7) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(7).toString());
			if(rs.getString(8) == null)
				spContentsList.add("");
			else
			spContentsList.add(rs.getString(8).toString());
		}
		System.out.println("count:"+ spContentsList.get(dataContents.length-1).toString());
		if(spContentsList.get(dataContents.length).toString().equals(totalCount))
			{
				System.out.println("sp  " + spContentsList);
				for (int i =0; i<dataContents.length; i++)
				{
					isMatch = (spContentsList.get(i).toString().equals(dataContents[i]));
					if(!isMatch)
					{
						System.out.println("File: " + dataContents[i] + "  DB/SP: " + spContentsList.get(i).toString());
						break;	
					}
				}
				if(!(isMatch))
				actualResult = "The " + spName + " and outbound does not have matching records";
			}
		else
		{
			actualResult = "The " + spName + " and outbound have different number of records as Expected count of records is " + totalCount + " And Actual Count is " + spContentsList.get(dataContents.length).toString() ;
		System.out.println(actualResult);
		}
		publishResults(isMatch, actualResult, expectedResult, msg);
		return isMatch;
	}*/
	
	
	/* 
	 * Method Name: setUserDBRoleByRoleName
	 * Author: Hemapriya Shanmugam
	 * Created Date: 18/08/2017
	 * Description: For Row Level Security, the user role access can be changed according to the Client
	 * Last Modified By: Hemapriya Shanmugam
	 * Last Modified Date: 18/08/2017
	 */
	public static boolean setUserDBRoleByRoleName(String roleName) throws Exception
	{
		String query = "query_AllAccess";
		boolean isSet = false;
		if (roleName.toUpperCase().equals("LLOYDS"))
			query = "query_LloydsAccess";
		if (roleName.toUpperCase() == "HSBC")
			query = "query_HsbcAccess";
	
		String output = ICSDBUtilis.sqlCommandExecution(getRefDataValueFromDataSheet(query));
	
			if(output.contains("Command(s) completed successfully.") || output.contains("because it does not exist ") || output.contains("Changed database context to 'iPSL.RefData636'"))
				isSet = true;
	
		return isSet;
	
	}	
	
	/*
	 * Method Name: executeInsertQuery
 	 * Author: Hemapriya Shanmugam
 	 * Date: 25-Sep-2017
 	 * Method Description: Any Insert Query will be executed	
 	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 25-Sep-2017
	 */
	
	public static void executeInsertQuery(String queryKeyWord, String type) throws Exception
	{
		boolean result = true;
		String msg = "Insert Records For " + type , actualResult = "Execution of Insert Query is successful",expectedResult = "Execution of Insert Query should be successful"; 
		ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet(queryKeyWord));
		publishResults(result, actualResult, expectedResult, msg);
	}
	
	/*
	 * Method Name: sp_RefOutDupCol
 	 * Author: Hemapriya Shanmugam
 	 * Date: 14-Aug-2017
 	 * Method Description: All Output Stored Procedures' outputs are verified for duplicate Columns 	
 	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Aug-2017
	 */
	public static boolean sp_RefOutDupCol(String spName) throws Exception
	{
		String actualResult = "The " + spName + " does not contain any duplicate Columns",
				expectedResult = "The " + spName + " should not contain any duplicate Columns",
				msg = "********" + spName + " Verification for Duplicate Columns";
		System.out.println("Executing: " + spName);
		String query = "EXEC " + spName ;
		boolean isDuplicate = false;
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query);
		ResultSetMetaData rs_m = rs.getMetaData();
		int totalColumns = rs_m.getColumnCount();
		String columnName[] = new String[totalColumns];
		String dupCol[] = new String[totalColumns];
		List<String> dupColList = new ArrayList<String>();
		Set<String> dupColHs = new HashSet<>();
		int colIter = 0, reIter = 0;
		for (int i = 0; i < totalColumns; i++)
		{
			columnName[i]=rs_m.getColumnLabel(i+1);
		}
		for(int j = 0; j < totalColumns; j++)
		{ 
			for (int k = 0; k < totalColumns; k++) 
			{
				if ((columnName[j].toString().equals(columnName[k].toString())) && (j != k)) 
				{
						isDuplicate = true;
						for (int l=0; l < colIter; l++)
						{
							if(dupCol[l] == columnName[j])
								reIter++;
						}
						if (reIter == 0)
						{
							dupCol[colIter] = columnName[j];
							colIter++;
						}
				}
			}
		}
		for(String s : dupCol)
		{
			if(s != null)
				dupColList.add(s);
		}
		dupColHs.addAll(dupColList);
		dupColList.clear();
		dupColList.addAll(dupColHs);
		if (isDuplicate)
		{
			actualResult = "The " + spName + " contains duplicate Columns which is/are " + dupColList;
			publishResults((!isDuplicate), actualResult, expectedResult, msg);
			return (!isDuplicate);
		}
		else
		{
			publishResults((!isDuplicate), actualResult, expectedResult, msg);
			return (!isDuplicate);	
		}
	}
	
	/*
	 * Method Name: sp_RefOutSpeInstructionCol
 	 * Author: Hemapriya Shanmugam
 	 * Date: 27-Sep-2017
 	 * Method Description: Special Instruction Output Stored Procedures' outputs are verified 	
 	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 27-Sep-2017
	 */
	public static boolean sp_RefOutSpeInstructionValidation(String product, String expectedInstructionType, boolean validateAddressRequired, boolean verifyIfNotPresent) throws Exception
	{
		expectedInstructionType = getRefDataValueFromDataSheet(expectedInstructionType);
		String spSIName = getRefDataValueFromDataSheet("spName_GetSI");
		String actualResult = "The Special Instruction(&Address) stored procedure output does not have the Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected",
				expectedResult = "The Special Instruction(&Address) stored procedure output should have the Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected",
				msg = product + " - The Special Instruction(&Address) Stored Procedure Output Verification for Instruction Type " + expectedInstructionType;
		if(verifyIfNotPresent)
		{
			actualResult = "The Special Instruction(&Address) stored procedure output have the Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected";
			expectedResult = "The Special Instruction(&Address) stored procedure output should NOT have the Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected";
			msg = product + " - The Special Instruction(&Address) Stored Procedure Output Verification for Expected Absence of Instruction Type " + expectedInstructionType;
			
		}
		
		boolean siSeqPartial = true, siAddSeqPartial = true, siAddSiSeqPartial = true, isSISeqPartialFinal = false,isRefOutSI;
		List<String> sIListFromRefout = new ArrayList<String>();
		List<String> sIListFrVerification = new ArrayList<String>();

		String querySI = "EXEC " + spSIName ;
		ResultSet rs_SI = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), querySI);
		String query_verifySIOut = getRefDataValueFromDataSheet("query_verifySIOut");
		int colForInsType = 7, colCountForVerify = 11, isPresentCounter = 0;
		String queryForSequenceDate = getRefDataValueFromDataSheet("query_busDateForSISequence");
		ResultSet rs_SequenceDate = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), queryForSequenceDate);
		String sequenceDate = "", invalidSISeqNumberInSI = "", invalidSISeqNumberInSIAdd = "", invalidSIAddSeqNumberInSIAdd = "";
		
		while(rs_SequenceDate.next())
		{
			sequenceDate = rs_SequenceDate.getString(1);
		}
		if(validateAddressRequired)
		{
			query_verifySIOut = getRefDataValueFromDataSheet("query_verifySIAddOut");
			colCountForVerify = 18;
			String spSIAddName = getRefDataValueFromDataSheet("spName_GetSIA");
			String querySIAdd = "EXEC " + spSIAddName ;
			ResultSet rs_SIAdd = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), querySIAdd);

			while(rs_SI.next() && rs_SIAdd.next())
			{	
				if(rs_SI.getString(colForInsType).toString().equals(expectedInstructionType.substring(0,1).toString()))
				{
					isPresentCounter++;
					if(verifyIfNotPresent)
						break;
					else
					{
						siSeqPartial = rs_SI.getString(1).toString().substring(0, 6).equals(sequenceDate);
						if(!siSeqPartial)
						{
							invalidSISeqNumberInSI = rs_SI.getString(1).toString();
							break;
						}
						for(int siCol = 2; siCol <= 13; siCol++)
						{
							if(rs_SI.getString(siCol) == null)
								sIListFromRefout.add("");
							else
								sIListFromRefout.add(rs_SI.getString(siCol).toString());
						}
						siAddSiSeqPartial = rs_SIAdd.getString(1).toString().substring(0, 6).equals(sequenceDate);
						siAddSeqPartial = rs_SIAdd.getString(2).toString().substring(0, 6).equals(sequenceDate);
						if(!(siAddSeqPartial && siAddSiSeqPartial))
						{
							invalidSISeqNumberInSIAdd = rs_SIAdd.getString(1).toString();
							invalidSIAddSeqNumberInSIAdd = rs_SIAdd.getString(2).toString();
							break;
						}
						for(int siAddCol = 3; siAddCol <= 10; siAddCol++)
						{
							if(rs_SIAdd.getString(siAddCol) == null)
								sIListFromRefout.add("");
							else
								sIListFromRefout.add(rs_SIAdd.getString(siAddCol).toString());
						}
						isSISeqPartialFinal = (siSeqPartial && siAddSeqPartial && siAddSiSeqPartial );

					}
				}
			}
		}
		else
		{
			while(rs_SI.next())
			{
				if(rs_SI.getString(colForInsType).toString().equals(expectedInstructionType.substring(0,1).toString()))
				{
					isPresentCounter++;
					if(verifyIfNotPresent)
						break;
					else
					{				
						siSeqPartial = rs_SI.getString(1).toString().substring(0, 8).equals(sequenceDate);
						if(!siSeqPartial)
						{
							break;
						}
						for(int siCol = 2; siCol <= 13; siCol++)
						{
							if(rs_SI.getString(siCol) == null)
								sIListFromRefout.add("");
							else
								sIListFromRefout.add(rs_SI.getString(siCol).toString());
						}
					}
				}
			}
			isSISeqPartialFinal = (siSeqPartial);
		}
		if(verifyIfNotPresent)
		{
			if(isPresentCounter == 0)
				actualResult = "The Special Instruction(&Address) stored procedure output does NOT have the Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected";
			isRefOutSI = (isPresentCounter == 0);
		}
		else
		{
			if(isSISeqPartialFinal)
		{
			ResultSet rs_VerifySI = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query_verifySIOut);
			while(rs_VerifySI.next())
			{
				for(int verifyCounter = 1; verifyCounter <= 5; verifyCounter++)
				{
					if(rs_VerifySI.getString(verifyCounter) == null)
						sIListFrVerification.add("");
					else
						sIListFrVerification.add(rs_VerifySI.getString(verifyCounter).toString());
				}
				sIListFrVerification.add(expectedInstructionType.substring(0,1).toString());
				for(int verifyCounter = 6; verifyCounter <= colCountForVerify; verifyCounter++)
				{
					if(rs_VerifySI.getString(verifyCounter) == null)
						sIListFrVerification.add("");
					else
						sIListFrVerification.add(rs_VerifySI.getString(verifyCounter).toString());
				}

			}
			isRefOutSI = sIListFrVerification.equals(sIListFromRefout);
			if(isRefOutSI)
			{
				actualResult = "The Special Instruction(&Address) stored procedure output have record for Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected";
						
			}
		}
		
		
		else
		{
			expectedResult = "Output Stored Procedure should have valid business date at the beginning of Sequence Number as " + sequenceDate;
			actualResult = "Output Stored Procedure does not have valid business date at the beginning of Sequence Number - Sequence Number in SI: " + invalidSISeqNumberInSI + " | Sequence Number in SI Address: " + invalidSISeqNumberInSIAdd + " | Address -Sequence Number in SI Address: " + invalidSIAddSeqNumberInSIAdd;
			isRefOutSI = isSISeqPartialFinal;
		}
	}
		publishResults(isRefOutSI, actualResult, expectedResult, msg);
		
		return isRefOutSI;
	}
	
	/*
	 * Method Name: sp_RefOutSpeInstructionCol
 	 * Author: Hemapriya Shanmugam
 	 * Date: 27-Sep-2017
 	 * Method Description: Special Instruction Output Stored Procedures' outputs are verified 	
 	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 27-Sep-2017
	 */
	public static boolean sp_RefOutSpeInstructionValidationR2(String product, String expectedInstructionType, boolean validateAddressRequired, boolean verifyIfNotPresent) throws Exception
	{
		expectedInstructionType = getRefDataValueFromDataSheet(expectedInstructionType);
		String spSIName = getRefDataValueFromDataSheet("spName_GetSI");
		String actualResult = "The Special Instruction(&Address) stored procedure output does not have the Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected",
				expectedResult = "The Special Instruction(&Address) stored procedure output should have the Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected",
				msg = product + " - The Special Instruction(&Address) Stored Procedure Output Verification for Instruction Type " + expectedInstructionType;
		if(verifyIfNotPresent)
		{
			actualResult = "The Special Instruction(&Address) stored procedure output have the Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected";
			expectedResult = "The Special Instruction(&Address) stored procedure output should NOT have the Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected";
			msg = product + " - The Special Instruction(&Address) Stored Procedure Output Verification for Expected Absence of Instruction Type " + expectedInstructionType;
			
		}
		
		boolean siSeqPartial = true, siAddSeqPartial = true, siAddSiSeqPartial = true, isSISeqPartialFinal = false,isRefOutSI;
		List<String> sIListFromRefout = new ArrayList<String>();
		List<String> sIListFrVerification = new ArrayList<String>();

		String querySI = "EXEC " + spSIName ;
		ResultSet rs_SI = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), querySI);
		String query_verifySIOut = getRefDataValueFromDataSheet("query_verifySIOut");
		int colForInsType = 7, colCountForVerify = 13, isPresentCounter = 0;
		String queryForSequenceDate = getRefDataValueFromDataSheet("query_busDateForSISequence");
		ResultSet rs_SequenceDate = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), queryForSequenceDate);
		String sequenceDate = "", invalidSISeqNumberInSI = "", invalidSISeqNumberInSIAdd = "", invalidSIAddSeqNumberInSIAdd = "";
		
		while(rs_SequenceDate.next())
		{
			sequenceDate = rs_SequenceDate.getString(1);
		}
		if(validateAddressRequired)
		{
			query_verifySIOut = getRefDataValueFromDataSheet("query_verifySIAddOut");
			colCountForVerify = 18;
			String spSIAddName = getRefDataValueFromDataSheet("spName_GetSIA");
			String querySIAdd = "EXEC " + spSIAddName ;
			ResultSet rs_SIAdd = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), querySIAdd);

			while(rs_SI.next() && rs_SIAdd.next())
			{	
				if(rs_SI.getString(colForInsType).toString().equals(expectedInstructionType.substring(0,1).toString()))
				{
					isPresentCounter++;
					if(verifyIfNotPresent)
						break;
					else
					{
						siSeqPartial = rs_SI.getString(1).toString().substring(0, 6).equals(sequenceDate);
						if(!siSeqPartial)
						{
							invalidSISeqNumberInSI = rs_SI.getString(1).toString();
							break;
						}
						for(int siCol = 2; siCol <= 13; siCol++)
						{
							if(rs_SI.getString(siCol) == null)
								sIListFromRefout.add("");
							else
								sIListFromRefout.add(rs_SI.getString(siCol).toString());
						}
						siAddSiSeqPartial = rs_SIAdd.getString(1).toString().substring(0, 6).equals(sequenceDate);
						siAddSeqPartial = rs_SIAdd.getString(2).toString().substring(0, 6).equals(sequenceDate);
						if(!(siAddSeqPartial && siAddSiSeqPartial))
						{
							invalidSISeqNumberInSIAdd = rs_SIAdd.getString(1).toString();
							invalidSIAddSeqNumberInSIAdd = rs_SIAdd.getString(2).toString();
							break;
						}
						for(int siAddCol = 3; siAddCol <= 10; siAddCol++)
						{
							if(rs_SIAdd.getString(siAddCol) == null)
								sIListFromRefout.add("");
							else
								sIListFromRefout.add(rs_SIAdd.getString(siAddCol).toString());
						}
						isSISeqPartialFinal = (siSeqPartial && siAddSeqPartial && siAddSiSeqPartial );

					}
				}
			}
		}
		else
		{			
				while(rs_SI.next())
			{
				if(rs_SI.getString(colForInsType).toString().equals(expectedInstructionType.substring(0,1).toString()))
				{
					isPresentCounter++;
					if(verifyIfNotPresent)
						break;
					else
					{				
						siSeqPartial = rs_SI.getString(1).toString().substring(0, 6).equals(sequenceDate);
						if(!siSeqPartial)
						{
							break;
						}
						for(int siCol = 2; siCol <= 13; siCol++)
						{
							if(rs_SI.getString(siCol) == null)
								sIListFromRefout.add("");
							else
								sIListFromRefout.add(rs_SI.getString(siCol).toString());
						}
					}
				}
			}
			isSISeqPartialFinal = (siSeqPartial);
		}
		if(verifyIfNotPresent)
		{
			if(isPresentCounter == 0)
				actualResult = "The Special Instruction(&Address) stored procedure output does NOT have the Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected";
			isRefOutSI = (isPresentCounter == 0);
		}
		else
		{
			if(isSISeqPartialFinal)
			{
			int col1ForInstructionType = Integer.parseInt(getRefDataValueFromDataSheet("specialInstructionColNumber1ForInstructionType"+expectedInstructionType.substring(0,1).toString()));
			int col2ForInstructionType = Integer.parseInt(getRefDataValueFromDataSheet("specialInstructionColNumber2ForInstructionType"+expectedInstructionType.substring(0,1).toString()));

			ResultSet rs_VerifySI = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), query_verifySIOut);
			while(rs_VerifySI.next())
			{
				for(int verifyCounter = 1; verifyCounter <= 5; verifyCounter++)
				{
					if(rs_VerifySI.getString(verifyCounter) == null)
						sIListFrVerification.add("");
					else
						sIListFrVerification.add(rs_VerifySI.getString(verifyCounter).toString());
				}
				sIListFrVerification.add(expectedInstructionType.substring(0,1).toString());
				for(int verifyCounter = 6; verifyCounter <= colCountForVerify; verifyCounter++)
				{
					if(rs_VerifySI.getString(verifyCounter) == null && (verifyCounter != col1ForInstructionType) && (verifyCounter != col2ForInstructionType))
						sIListFrVerification.add("");
					else
						sIListFrVerification.add(rs_VerifySI.getString(verifyCounter).toString());
				}

			}
			isRefOutSI = sIListFrVerification.equals(sIListFromRefout);
			if(isRefOutSI)
			{
				actualResult = "The Special Instruction(&Address) stored procedure output have record for Instruction Type: " + expectedInstructionType.substring(0,1).toString() + " as expected";
						
			}
		}
		
		
		else
		{
			expectedResult = "Output Stored Procedure should have valid business date at the beginning of Sequence Number as " + sequenceDate;
			actualResult = "Output Stored Procedure does not have valid business date at the beginning of Sequence Number - Sequence Number in SI: " + invalidSISeqNumberInSI + " | Sequence Number in SI Address: " + invalidSISeqNumberInSIAdd + " | Address -Sequence Number in SI Address: " + invalidSIAddSeqNumberInSIAdd;
			isRefOutSI = isSISeqPartialFinal;
		}
	}
		publishResults(isRefOutSI, actualResult, expectedResult, msg);
		
		return isRefOutSI;
	}
	
	/*
	 * Method Name: sp_RefOutSCAttributesValidation
 	 * Author: Hemapriya Shanmugam
 	 * Date: 13-Oct-2017
 	 * Method Description: SortCode Attributes Output Stored Procedures' outputs are verified for all records row by row	
 	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15-Oct-2017
	 */
	public static boolean sp_RefOutSCAttributesValidation(String client) throws Exception
	{
		String actualResult = "SortCode Attributes SP contains expected output",
				expectedResult = "SortCode Attributes SP should contain the expected output from EISCD and Agency Details",
				msg = client + "SortCode Attributes SP Validation";
		
		
		boolean isSC = false,isAbbreviatedBankName = false, isBankName = false, isBankCode = false, isSupervisoryBody = false, 
				isSortCode = false, isBicBank = false, isBicBranch = false, isSuffix = false, isBankOfficeTitle = false, 
				isNCBCountryCode = false, isDeletedDate = false, isBankOfficeDateLastChanged = false, isPrintIndicator = false, 
				isStatus = false, isCNCCDateLastChanged = false, isDateClosed = false, isSettlementBank = false, isDebitAgencySortCode = false,
				isReturnIndicator = false, isGBNIIndicator = false, isBankOfficeType = false, isMainBankOfficeSortCode = false, 
				isMajorLocation = false, isMinorLocation = false, isOfficeOrPlaceName = false, isSecondEntryIndicator = false, 
				isOfficeOrPlaceNameSecond = false, isOfficeTitle = false, isAddressName = false, isPostalName = false, 
				isAddressLine1 = false, isAddressLine2 = false, isCity_Town = false, isArea_Country = false, isPostCodeOutCode = false, 
				isPostCodeIncode = false, isZipCode = false, isCountry = false, isPhoneInternationalPrefix = false, isPhoneStd = false, 
				isPhoneNumber = false, isFAXInternationalPrefix = false, isFAXStd = false, isFAXNumber = false, isParticipantId = false, 
				isSettlementParticipantID = false, isSettlementBankName = false, isAgencyId = false, isExternalAgencyId = false, 
				isOnUsFlag = false, isBrandId = false, isAgencyAccountExists = false;

		List<String> sAListFromRefout = new ArrayList<String>();
		List<String> sAListFrVerification = new ArrayList<String>();

		String querySA = "query_fetchSortCodeAttributeSorted", queryCreateInsertSA= "query_createTempAndInsertForSortCodeAttributes", 
				queryForVerification =  "query_SortCodeAttributes", queryToDropTempTable = "query_fetchSortCodeAttributeSorted_DROP";
		ResultSet rs_SACreateInsertSA = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet(queryCreateInsertSA));

		ResultSet rs_SA = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet(querySA));
		ResultSet rs_SAVerify = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet(queryForVerification));
		while(rs_SA.next())
		{
			for(int sACol = 1; sACol <= 52; sACol++)
			{
				if(rs_SA.getString(sACol) == null)
					sAListFromRefout.add("");
				else
					sAListFromRefout.add(rs_SA.getString(sACol).toString());
			}
		}
		ResultSet rs_SATableDrop = ICSProductDBConnection.getICSDBServerConnection(getRefDataValueFromDataSheet("db_server"), getRefDataValueFromDataSheet("database"), getRefDataValueFromDataSheet(queryToDropTempTable));
		while(rs_SAVerify.next())
		{
			for(int sACol = 1; sACol <= 52; sACol++)
			{
				if(rs_SAVerify.getString(sACol) == null)
					sAListFrVerification.add("");
				else
					sAListFrVerification.add(rs_SAVerify.getString(sACol).toString());
			}
		}
		int verifyCounter = 0;
		String erroneousString="",sortCodeExpected = "";
		while (verifyCounter < sAListFromRefout.size() && verifyCounter <  sAListFrVerification.size())
		{
			isAbbreviatedBankName = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			String AE = sAListFrVerification.get(verifyCounter).toString(),AR = sAListFromRefout.get(verifyCounter).toString();
			
			if(!isAbbreviatedBankName)
			{
				
				erroneousString = "AbbreviatedBankName: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;

			isBankName = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isBankName)
			{
				erroneousString = "BankName: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isBankCode = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());

			if(!isBankCode)
			{
				erroneousString = "BankCode: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;

			isSupervisoryBody = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());

			if(!isSupervisoryBody)
			{
				erroneousString = "SupervisoryBody: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isSortCode = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			sortCodeExpected = sAListFrVerification.get(verifyCounter).toString();
			if(!isSortCode)
			{
				erroneousString = "SortCode: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isBicBank = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());

			if(!isBicBank)
			{
				erroneousString = "BicBank: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isBicBranch = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isBicBranch)
			{
				erroneousString = "BicBranch: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isSuffix = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isSuffix)
			{
				erroneousString = "Suffix: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isBankOfficeTitle = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isBankOfficeTitle)
			{
				erroneousString = "BankOfficeTitle: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isNCBCountryCode = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isNCBCountryCode)
			{
				erroneousString = "NCBCountryCode: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isDeletedDate = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isDeletedDate)
			{
				erroneousString = "DeletedDate: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isBankOfficeDateLastChanged = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isBankOfficeDateLastChanged)
			{
				erroneousString = "BankOfficeDateLastChanged: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isPrintIndicator = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isPrintIndicator)
			{
				erroneousString = "PrintIndicator: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isStatus = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isStatus)
			{
				erroneousString = "Status: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isCNCCDateLastChanged = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isCNCCDateLastChanged)
			{
				erroneousString = "CNCCDateLastChanged: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isDateClosed = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());

			if(!isDateClosed)
			{
				erroneousString = "DateClosed: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isSettlementBank = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isSettlementBank)
			{
				erroneousString = "SettlementBank: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isDebitAgencySortCode = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isDebitAgencySortCode)
			{
				erroneousString = "DebitAgencySortCode: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isReturnIndicator = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isReturnIndicator)
			{
				erroneousString = "ReturnIndicator: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isGBNIIndicator = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isGBNIIndicator)
			{
				erroneousString = "GBNIIndicator: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isBankOfficeType = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isBankOfficeType)
			{
				erroneousString = "BankOfficeType: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isMainBankOfficeSortCode = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isMainBankOfficeSortCode)
			{
				erroneousString = "MainBankOfficeSortCode: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isMajorLocation = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isMajorLocation)
			{
				erroneousString = "MajorLocation: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isMinorLocation = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isMinorLocation)
			{
				erroneousString = "MinorLocation: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isOfficeOrPlaceName = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isOfficeOrPlaceName)
			{
				erroneousString = "OfficeOrPlaceName: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isSecondEntryIndicator = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isSecondEntryIndicator)
			{
				erroneousString = "SecondEntryIndicator: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isOfficeOrPlaceNameSecond = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isOfficeOrPlaceNameSecond)
			{
				erroneousString = "OfficeOrPlaceNameSecond: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isOfficeTitle = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isOfficeTitle)
			{
				erroneousString = "OfficeTitle: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isAddressName = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isAddressName)
			{
				erroneousString = "AddressName: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isPostalName = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isPostalName)
			{
				erroneousString = "PostalName: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isAddressLine1 = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isAddressLine1)
			{
				erroneousString = "AddressLine1: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isAddressLine2 = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isAddressLine2)
			{
				erroneousString = "AddressLine2: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isCity_Town = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isCity_Town)
			{
				erroneousString = "City_Town: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isArea_Country = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isArea_Country)
			{
				erroneousString = "Area_Country: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isPostCodeOutCode = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isPostCodeOutCode)
			{
				erroneousString = "PostCodeOutCode: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isPostCodeIncode = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isPostCodeIncode)
			{
				erroneousString = "PostCodeIncode: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isZipCode = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isZipCode)
			{
				erroneousString = "ZipCode: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isCountry = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isCountry)
			{
				erroneousString = "Country: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isPhoneInternationalPrefix = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isPhoneInternationalPrefix)
			{
				erroneousString = "PhoneInternationalPrefix: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isPhoneStd = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isPhoneStd)
			{
				erroneousString = "PhoneStd: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isPhoneNumber = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isPhoneNumber)
			{
				erroneousString = "PhoneNumber: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isFAXInternationalPrefix = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isFAXInternationalPrefix)
			{
				erroneousString = "FAXInternationalPrefix: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isFAXStd = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isFAXStd)
			{
				erroneousString = "FAXStd: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isFAXNumber = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isFAXNumber)
			{
				erroneousString = "FAXNumber: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isParticipantId = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isParticipantId)
			{
				erroneousString = "ParticipantId: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isSettlementParticipantID = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isSettlementParticipantID)
			{
				erroneousString = "SettlementParticipantID: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isSettlementBankName = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isSettlementBankName)
			{
				erroneousString = "SettlementBankName: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isAgencyId = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isAgencyId)
			{
				erroneousString = "AgencyId: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isExternalAgencyId = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isExternalAgencyId)
			{
				erroneousString = "ExternalAgencyId: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isOnUsFlag = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isOnUsFlag)
			{
				erroneousString = "OnUsFlag: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isBrandId = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isBrandId)
			{
				erroneousString = "BrandId: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
			isAgencyAccountExists = sAListFromRefout.get(verifyCounter).toString().equals(sAListFrVerification.get(verifyCounter).toString());
			if(!isAgencyAccountExists)
			{
				erroneousString = "AgencyAccountExists: "+ sAListFromRefout.get(verifyCounter) + " And Expected Value being: " + sAListFrVerification.get(verifyCounter);
				break;
			}
			verifyCounter++;
		
		}
		isSC = (isAbbreviatedBankName && isBankName && isBankCode && isSupervisoryBody && isSortCode && isBicBank && isBicBranch && isSuffix && 
				isBankOfficeTitle && isNCBCountryCode && isDeletedDate && isBankOfficeDateLastChanged && isPrintIndicator && isStatus && 
				isCNCCDateLastChanged && isDateClosed && isSettlementBank && isDebitAgencySortCode && isReturnIndicator && isGBNIIndicator && 
				isBankOfficeType && isMainBankOfficeSortCode && isMajorLocation && isMinorLocation && isOfficeOrPlaceName && 
				isSecondEntryIndicator && isOfficeOrPlaceNameSecond && isOfficeTitle && isAddressName && isPostalName && isAddressLine1 && 
				isAddressLine2 && isCity_Town && isArea_Country && isPostCodeOutCode && isPostCodeIncode && isZipCode && isCountry && 
				isPhoneInternationalPrefix && isPhoneStd && isPhoneNumber && isFAXInternationalPrefix && isFAXStd && isFAXNumber && 
				isParticipantId && isSettlementParticipantID && isSettlementBankName && isAgencyId && isExternalAgencyId && isOnUsFlag && 
				isBrandId && isAgencyAccountExists);
		if(!isSC)
		actualResult = "SortCode Attributes SP does not contain the expected output Where the " + erroneousString + "with concerning SortCode being: " + sortCodeExpected;

		publishResults((isSC), actualResult, expectedResult, msg);
		return (isSC);
	}
}
