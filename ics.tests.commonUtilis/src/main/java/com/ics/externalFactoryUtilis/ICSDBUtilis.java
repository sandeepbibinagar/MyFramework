package com.ics.externalFactoryUtilis;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

/********************************************************************************************************************/
/* Created By: 	Umamahesh.Mulugu@ipsl.co.uk																			*/
/* Purpose:	 ICS - DB Reusable methods                                      								    	*/
/********************************************************************************************************************/
public class ICSDBUtilis extends GenericMethodUtilis  {	
	
	private static String dateFormat = "yyyy.MM.dd.HH.mm.ss";

	/* 
	 * Method Name: generateExtractId
	 * Author: Dhanabal Natarajan
	 * Created Date: 15/02/2017
	 * Description: Create Extract ID uniquely using Date and Time stamp
	 * Last Modified By: Dhanabal
	 * Last Modified Date: 16/02/2017
	 */
	public static String generateExtractId(String fileType){		
		String firstpart = getRequiredDateFormat(dateFormat)
				.format(getTimeStamp(System.currentTimeMillis())).replace(".","");
		String lastpart = firstpart.substring(firstpart.length()-6);		
		System.out.println("CFSItemID "+firstpart + fileType+ "IN" +lastpart);
		
		return  firstpart + fileType+ "IN" +lastpart;
	}	
	
	/* 
	 * Method Name: createFileFromTemplate
	 * Author: Dhanabal Natarajan
	 * Created Date: 15/02/2017
	 * Description: Create new .sql file using the template .sql file where New Extract ID is updated.
	 * Last Modified By: Dhanabal
	 * Last Modified Date: 16/02/2017
	 */
	public static void createFileFromTemplate(String filePath, String fileName, String fileExtension, 
			String extractIDStringToBeReplace, String extractIDStringToReplace) throws IOException
	{		
		String content = readDataOfFileStream(getFileStreamWithExtension(filePath, fileName, fileExtension));
		String con = content.replaceAll(extractIDStringToBeReplace, extractIDStringToReplace);
		if(!fileName.contains("_Actual")){
				fileExtension = "_Actual"+fileExtension;
		}
		writeDataOfFileStream(getFileStreamWithExtension(filePath, fileName, fileExtension), con, false);
	}
	
	/* 
	 * Method Name: sqlCommandExecution
	 * Author: Nisha Tripathi
	 * Created Date: 15/02/2017
	 * Description: Inject SQL file in to the DB through commandline execution
	 * Last Modified By: Nisha Tripathi
	 * Last Modified Date: 16/02/2017
	 * Updated Version Date : 09-05-2017
	 * Updared Description : Included getErrorStream() to give message if incorrect command line has been executed.
	 */
	public static String sqlCommandExecution(String strCommand){
		StringBuffer stringBufferStream= new StringBuffer();
		
		try {
			final Process strSqlCommand = Runtime.getRuntime().exec(strCommand);
			InputStreamReader inputS = new InputStreamReader(strSqlCommand.getInputStream());
			OutputStreamWriter oSw = new OutputStreamWriter(strSqlCommand.getOutputStream());
			BufferedReader stdErr = new BufferedReader(new InputStreamReader(strSqlCommand.getErrorStream()));
			final InputStream in = strSqlCommand.getInputStream();			
			int eachCharacterIndex;
			String s=null;
			
			while ((eachCharacterIndex=in.read())!=-1){
				stringBufferStream.append((char)eachCharacterIndex);
				
			}
			System.out.println(stringBufferStream.append((char)eachCharacterIndex));
			//oSw.write(eachCharacterIndex);
			//System.out.println("read msg"+oSw.append((char)eachCharacterIndex));
						
			while(stdErr.readLine()!=null){
				System.out.println("Incorrect Commandline execution performed "+stdErr.readLine());
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		return stringBufferStream.toString();	
	}	

}
