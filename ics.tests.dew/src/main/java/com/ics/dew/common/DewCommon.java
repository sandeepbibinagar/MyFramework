package com.ics.dew.common;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import com.ics.dew.pages.DewSearchItemsPage;
import com.ics.externalFactoryUtilis.ICSDBUtilis;

public class DewCommon {

	public static String resultSetValue;
	private static String dateTimeFormat = "yyyy.MM.dd.HH.mm.ss";
	public static String getICSRetrieveColumnValues(ResultSet resultSet) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		while(resultSet.next()){
			resultSetValue  = resultSet.getString(1);
			break;
			
		}	
		System.out.println("Returning Value:"+resultSetValue);
		return resultSetValue;
	}
	
	public static String getICSRetrieveMutipleColumnValues(ResultSet resultSet, String expectedValue) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		String resultSetValue=null;
		while(resultSet.next())
		{
			resultSetValue  = resultSet.getString(1);
			if(resultSetValue.equals(expectedValue))
				break;
		}
	
		return resultSetValue;
	}
	
	public static String getICSRetrieveAllColumnValues(ResultSet resultSet) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		 StringBuffer sbf = new StringBuffer();
		String strBatchDetailsID,strBusinessDate,strExtractID,strProcessingParticipantId,strExtMessageType,strIntMessageType;
		
		while(resultSet.next()){
			strBatchDetailsID  = resultSet.getString("BatchDetailsID");
			strBusinessDate  = resultSet.getString("BusinessDate");
			strExtractID  = resultSet.getString("ExtractID");
			strProcessingParticipantId  = resultSet.getString("ProcessingParticipantId");
			strExtMessageType  = resultSet.getString("ExtMessageType");
			strIntMessageType  = resultSet.getString("IntMessageType");
		    sbf.append(strBatchDetailsID).append(strBusinessDate).append(strExtractID).append(strProcessingParticipantId).append(strExtMessageType).append(strIntMessageType);
           
			break;
		}
		return sbf.toString();
		}
	
	
	
	
	/* 
	 * Method Name: CopyFileToDestinationPath
	 * Author: Dhanabal Natarajan
	 * Created Date: 21/02/2017
	 * Description: Copies file from source path to destination path
	 * Last Modified By: Dhanabal
	 * Last Modified Date: 21/02/2017
	 * **Parameters
	 * fileName - Name of the file to be copied
	 * srcFilePath - Source path where the file was located
	 * destFilePath - Destination path where the file to be moved
	 */
	
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
			System.out.println("Act Time"+act_time);
		}
		
		return (!destFile.exists());
	}
	
	/* 
	 * Method Name: ConvertDateInToRequiredFormat
	 * Author: Prateek Arora
	 * Created Date: 23/02/2017
	 * Description: Converts date in to required format
	 * Last Modified By: Prateek Arora
	 * **Parameters
	 * InputFormat - format of the input date for ex: yyyyMMdd
	 * OutputFormat - format of the input date for ex: yyyy-MM-dd
	 * InputDate - Date to be converted for ex:20170222
	 */
	public static String ConvertDateInToRequiredFormat(String InputFormat, String OutputFormat, String InputDate) throws ParseException{
		Date dewddate = new SimpleDateFormat(InputFormat).parse(InputDate);
		return (new SimpleDateFormat(OutputFormat).format(dewddate));
	}
	
	
	public void executeEODSSISPackage(String Command) throws IOException {

		ProcessBuilder builder=new ProcessBuilder("cmd.exe","/c","notepad.exe");
		builder.redirectErrorStream(true);
		Process process=builder.start();
			
	}
	
	
}
