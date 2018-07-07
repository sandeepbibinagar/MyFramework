package com.ics.fred.common;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import org.apache.commons.io.FileUtils;
import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

/******************************************************************************************************************************************************************
/* Class Name: GenericMethods
/* Author: Nisha Tripathi
/* Created Date:  10-Mar-2017
/* Description: Common Methods to fetch value from database
/******************************************************************************************************************************************************************/

//import com.ics.seleniumCoreUtils.GenericMethodUtilis;
//import com.ics.seleniumCoreUtils.ICSDBUtilis;

public class GenericMethods extends GenericMethodUtilis{
	
	public static String resultSetValue;
	private static String dateTimeFormat = "yyyy.MM.dd.HH.mm.ss";
	
	/****************************************************************************************************************************************************************** 
	/* Method Name: Retrieve Single value from Database
	/* Author: Nisha Tripathi
	/* Created Date: 10-Mar-2017
	/* Description: Retrieve single value from database
	/******************************************************************************************************************************************************/
	
	public static String getICSRetrieveColumnValues(ResultSet resultSet) throws Exception
	{
		System.out.println("Resultset Size:"+resultSet.getFetchSize());
		while(resultSet.next()){
			resultSetValue  = resultSet.getString(1).trim();
		//	System.out.println("Returning Value in while loop:"+resultSetValue);
			
		}	
	//	System.out.println("Returning Value:"+resultSetValue);
		return resultSetValue;
	}
	
	/****************************************************************************************************************************************************************** 
	/* Method Name: Retrieve Single value from Database
	/* Author: Nisha Tripathi
	/* Created Date: 10-Mar-2017
	/* Description: Retrieve  column value and finds expected value from database
	/******************************************************************************************************************************************************/
	
	public static String getICSRetrieveMutipleColumnValues(ResultSet resultSet, String expectedValue) throws Exception
	{
		String resultSetValue=null;
		while(resultSet.next())
		{
			resultSetValue  = resultSet.getString(1);
			if(resultSetValue.equals(expectedValue))
				System.out.println("Database returned value is :"+resultSetValue);
				break;
		}
		System.out.println("Database returned value is after while loop:"+resultSetValue);
		return resultSetValue;
	}
	
	/****************************************************************************************************************************************************************** 
	/* Method Name: Retrieve Single value from Database
	/* Author: Nisha Tripathi
	/* Created Date: 10-Mar-2017
	/* Description: Retrieve multiple required column values from database
	/******************************************************************************************************************************************************/
	
	public static String getICSRetrieveAllColumnValues(ResultSet resultSet) throws Exception
	{
		 StringBuffer sbf = new StringBuffer();
			String strBusinessDate,strExtractID,strProcessingParticipantId,strExtMessageType,strIntMessageType,strMessageSource,strMessageDestination;
		
			while(resultSet.next()){
				strBusinessDate  = resultSet.getString("BusinessDate");
				strExtractID  = resultSet.getString("ExtractID");
				strProcessingParticipantId  = resultSet.getString("ProcessingParticipantId");
				strExtMessageType  = resultSet.getString("ExtMessageType");
				strIntMessageType  = resultSet.getString("IntMessageType");
				strMessageSource  = resultSet.getString("MessageSource").trim();
			    strMessageDestination  = resultSet.getString("MessageDestination").trim();
			    strMessageDestination  = resultSet.getString("RecordCounts").trim();
			    strMessageDestination  = resultSet.getString("ImportDateTime").trim();
			    strMessageDestination  = resultSet.getString("ItemType").trim();
			 	sbf.append(strExtractID).append(strBusinessDate).append(strProcessingParticipantId).append(strExtMessageType).append(strIntMessageType).append(strMessageSource).append(strMessageDestination);
				
				break;
			}
			return sbf.toString();
	}
	
	/****************************************************************************************************************************************************************** 
	/* Method Name: Retrieve Single value from Database
	/* Author: Nisha Tripathi
	/* Created Date: 10-Mar-2017
	/* Description: Retrieve multiple required column values from database
	/******************************************************************************************************************************************************/
	
	public static Integer generateUniqueNo(int noOfDigits){		
		String firstpart = getRequiredDateFormat(dateTimeFormat)
				.format(getTimeStamp(System.currentTimeMillis())).replace(".","");
		//System.out.println("first part of block number"+firstpart);
		String lastpart = firstpart.substring(firstpart.length()-noOfDigits);
		//System.out.println("Last part of block number"+lastpart);
		return  Integer.parseInt(lastpart);
	}
	
	/****************************************************************************************************************************** 
	/* Method Name: createUniqueFileFromTemplate
	/* Author: Dhanabal Natarajan
	/* Created Date: 10-Mar-2017
	/* Description: Create new unique file using the template and replaces the hardcoded string in the file
	/* **Parameters
	/* filePath - Path of the file 
	/* fileName - Template file Name without file extension
	/* fileExtension - Extension of the file
	/* extractIDStringToBeReplace - Hard coded string in the file
	/* extractIDStringToReplace - Value to replace the hard coded string
	/* uniqueFileName - Unique file name without extension
	/********************************************************************************************************************************/
	
	public static void createUniqueFileFromTemplate(String filePath, String fileName, String fileExtension, 
			String extractIDStringToBeReplace, String extractIDStringToReplace, String uniqueFileName) throws IOException
	{		
		String content = readDataOfFileStream(ICSDBUtilis.getFileStreamWithExtension(filePath, fileName, fileExtension));
		String con = content.replaceAll(extractIDStringToBeReplace, extractIDStringToReplace);
		if(!uniqueFileName.contains("_Actual")){
			fileExtension = "_Actual"+fileExtension;
		}
		writeDataOfFileStream(ICSDBUtilis.getFileStreamWithExtension(filePath, uniqueFileName, fileExtension), con, false);
	}
	
	/********************************************************************************************************************************* 
	/* Method Name: CopyFileToDestinationPath
	/* Author: Dhanabal Natarajan
	/* Created Date: 10-Mar-2017
	/* Description: Copies file from source path to destination path
	/* **Parameters
	/* fileName - Name of the file to be copied
	/* srcFilePath - Source path where the file was located
	/* destFilePath - Destination path where the file to be moved
	/*************************************************************************************************************************************/
	
	public static Boolean CopyFileToDestinationPath(String fileName, String srcFilePath, String destFilePath) throws IOException, InterruptedException
	{
		File srcFile = new File(srcFilePath+fileName);
		File destFile = new File (destFilePath+fileName);
		FileUtils.copyFile(srcFile, destFile);
		
		//wait for file to be consumed by the system
		long act_time=2000;
		while (destFile.exists()){
			if (act_time>240000){
				break;
			}
			Thread.sleep(2000);
			act_time = (act_time+2000);
		}
		
		return (!destFile.exists());
	}
	
	/************************************************************************************************************************************* 
	/* Method Name: ConvertDateInToRequiredFormat
	/* Author: Dhanabal Natarajan
	/* Created Date: 10-Mar-2017
	/* Description: Converts date in to required format
	/* **Parameters
	/* InputFormat - format of the input date for ex: yyyyMMdd
	/* OutputFormat - format of the input date for ex: yyyy-MM-dd
	/* InputDate - Date to be converted for ex:20170222
	/
	 * @throws ParseException **************************************************************************************************************************************/
	public static String ConvertDateInToRequiredFormat(String InputFormat, String OutputFormat, String InputDate) throws ParseException{
		Date moddate = new SimpleDateFormat(InputFormat).parse(InputDate);
		return (new SimpleDateFormat(OutputFormat).format(moddate));
	}

	/****************************************************************************************************************************************************************** 
	/* Method Name: Retrieve Single value from Database
	/* Author: Nisha Tripathi
	/* Created Date: 10-Mar-2017
	/* Description: Retrieve 2 column values from database
	/******************************************************************************************************************************************************/
	
	public Map<String,String> getMessageItemIdAndImageValue(ResultSet resultSet) throws Exception
	{
	
		String strMessageItemID,strImage;
		Map<String,String> messageItemAndImageValue = new HashMap<String,String>();
			while(resultSet.next()){
			strMessageItemID  = resultSet.getString("MessageItemID");
			strImage = resultSet.getString("Image");
			messageItemAndImageValue.put(strMessageItemID, strImage);
		}
		return messageItemAndImageValue;
	}

	/************************************************************************************************************************************ 
	/* Method Name: Retrieve two column values from Database
	/* Author: Nisha Tripathi
	/* Created Date: 10-Mar-2017
	/* Description: Retrieve two column values from database
	/***************************************************************************************************************************************/
	public static ArrayList<String> getICSRetrieveErrColumnValues(ResultSet resultSet,String filename) throws Exception
	{
	
		String resultSetValue=null;
		String businessDateFaileMsg=filename+":"+" "+filename+" :Businessdate validation failed.";
		String blockNumberAlreadyPrcsdErr=filename+" : This block number in the file is already processed.";
		String errorMessageBusinessdateValidationFailedFileName =filename+" :Businessdate validation failed.";
		String errorMessageDateTimeCnvFailed ="Conversion failed when converting date and/or time from character string.";
		String errorMessageDateTimeCnvFailedWithFileName =filename+": "+errorMessageDateTimeCnvFailed;
		String  strCnvFailed="Conversion failed when converting date and/or time from character string.";
		 
		ArrayList<String> returnVal= new ArrayList<>();
	
		while(resultSet.next())
		{	
			resultSetValue  = resultSet.getString(1);
			if(resultSetValue.contains(businessDateFaileMsg))
			{
				returnVal.add(resultSetValue);
				returnVal.add(businessDateFaileMsg);
			}
			else if(resultSetValue.contains(strCnvFailed))
			{ 
				returnVal.add(resultSetValue);
				returnVal.add(strCnvFailed);
			}
			else if(resultSetValue.contains(errorMessageBusinessdateValidationFailedFileName))
			{ 
				returnVal.add(resultSetValue);
				returnVal.add(errorMessageBusinessdateValidationFailedFileName);
			}
			else if(resultSetValue.contains(errorMessageDateTimeCnvFailed))
			{ 
				returnVal.add(resultSetValue);			
				returnVal.add(errorMessageDateTimeCnvFailed);
				
			}
			else if(resultSetValue.contains(errorMessageDateTimeCnvFailedWithFileName))
			{ 
				returnVal.add(resultSetValue);
				returnVal.add(errorMessageDateTimeCnvFailedWithFileName);
				
			}
			else if(resultSetValue.contains(blockNumberAlreadyPrcsdErr))
				 { 
					returnVal.add(resultSetValue);
					returnVal.add(blockNumberAlreadyPrcsdErr);
				 }
				else 
					{
					System.out.println("No Error found for "+filename+" in Batch Error Log Table");
					}
		
		}

		return returnVal;
	}
	
	/****************************************************************************************************************************************************************** 
	/* Method Name: Retrieve Single value from Database
	/* Author: Nisha Tripathi
	/* Created Date: 10-Mar-2017
	/* Description: Retrieve  list of column values and find expected value from database 
	/******************************************************************************************************************************************************/
	
	public static ArrayList<String> getMutipleColumnValues(ResultSet resultSet, ArrayList<String> expectedValue) throws Exception
	{
		String resultSetValue=null;
		ArrayList<String> returnVal= new ArrayList<>();
		
		while(resultSet.next())
		{
			resultSetValue  = resultSet.getString(1);			
			if(resultSetValue.equals(expectedValue))
				break;
		}
		returnVal.add(resultSetValue);
		return returnVal;
	}
	
	public static ArrayList<String> getMutipleColumnsValues(ResultSet resultSet) throws Exception
	{
		ArrayList<String> returnVal= new ArrayList<>();
		
		while(resultSet.next())
		{
			returnVal.add(resultSet.getString(1).trim());
			returnVal.add(resultSet.getString(2));
			returnVal.add(resultSet.getString(3));
			returnVal.add(resultSet.getString(4));
			returnVal.add(resultSet.getString(5));
			returnVal.add(resultSet.getString(6));
			returnVal.add(resultSet.getString(7));
			returnVal.add(resultSet.getString(8));
			
			/*strBusinessDate  = resultSet.getString("BusinessDate");
			strExtractID  = resultSet.getString("ExtractID");
			strProcessingParticipantId  = resultSet.getString("ProcessingParticipantId");
			strExtMessageType  = resultSet.getString("ExtMessageType");
			strIntMessageType  = resultSet.getString("IntMessageType");
			strMessageSource  = resultSet.getString("MessageSource").trim();
		    strMessageDestination  = resultSet.getString("MessageDestination").trim();
		    strMessageDestination  = resultSet.getString("RecordCounts").trim();
		    strMessageDestination  = resultSet.getString("ImportDateTime").trim();
		    strMessageDestination  = resultSet.getString("ItemType").trim();*/
		 
			
		}
		System.out.println("returnVal :"+returnVal);
		return returnVal;
	}

	/****************************************************************************************************************************************************************** 
	/* Method Name: Retrieve Single value from Database
	/* Author: Nisha Tripathi
	/* Created Date: 10-Mar-2017
	/* Description: Retrieve  column value and finds expected value from database
	/******************************************************************************************************************************************************/
	
	public static String getICSRetrieveErrLog(ResultSet resultSet, String expectedValue) throws Exception
	{
		String resultSetValue=null;
		while(resultSet.next())
		{
			resultSetValue  = resultSet.getString(1);
			if(resultSetValue.contains(expectedValue))
				System.out.println("MF01SendQueue Table returned value is :"+resultSetValue);
			
		}
		System.out.println("MF01SendQueue returned value is after while loop:"+resultSetValue);
		return resultSetValue;
	}

	/****************************************************************************************************************************************************************** 
	/* Method Name: copyFileFromOneLocationToAnother
	/* Author: Nisha Tripathi
	/* Created Date: 13-May-2017
	/* Description: Copy file from one location to another
	/******************************************************************************************************************************************************/
	

	public static void copyFileFromOneLocationToAnother(String srcFilePath,String destFilePath,String fileName) throws Exception{
	File srcFile = new File(srcFilePath+fileName);
	File destFile = new File (destFilePath+fileName);
	FileUtils.copyFile(srcFile, destFile);
	}
	
	/****************************************************************************************************************************************************************** 
	/* Method Name: copyFileFromOneLocationToAnother
	/* Author: Nisha Tripathi
	/* Created Date: 26-Jun-2017
	/* Description: retrieve businessDate from database
	/******************************************************************************************************************************************************/
	
	public static String getActualConfigDate(String dbServerName,String fredAutoDb,String sqlConfigBusinessDate) throws Exception{
		ResultSet rsConfigBusinessDate = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, sqlConfigBusinessDate);
		String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(rsConfigBusinessDate);
		String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
		return configDateValue;
	}
	
	/****************************************************************************************************************************************************************** 
	/* Method Name: errActualVal
	/* Author: Nisha Tripathi
	/* Created Date: 26-Jun-2017
	/* Description: Get specific error message
	/******************************************************************************************************************************************************/
	
	public static String errActualVal(String actualExtractIDIncorrectValue){
		String startTag ="<Description>";
		String endTag ="</Description>";
			
		int indexStartTag=actualExtractIDIncorrectValue.indexOf(startTag);
		int length = startTag.length();
		int indexEndTag=actualExtractIDIncorrectValue.indexOf(endTag);
		String tagValue = actualExtractIDIncorrectValue.substring(indexStartTag+length, indexEndTag);
		System.out.println("Expected Substring Value is :"+tagValue);
		
		int indexStart=tagValue.indexOf("This");
		String tagValue1 = tagValue.substring(0, indexStart);
		System.out.println("Expected err Substring Value is :"+tagValue1);
		return tagValue1;
	}
}