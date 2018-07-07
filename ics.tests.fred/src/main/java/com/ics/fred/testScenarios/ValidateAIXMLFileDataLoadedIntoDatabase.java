package com.ics.fred.testScenarios;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadSQLFileUtil;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateAIXMLFileDataLoadedIntoDatabase extends ICSDBUtilis{

	
	public static  void validateAIXMLDataLoadedeIntoDB(String dbServerName,String fredAutoDb,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,
		String strAIXMLFilePath,String aixmlArchvFilePath) throws Exception,NullPointerException{
				 
	     String sqlConfigBusinessDate ="select Value from Config.BusinessConfig where [Key]='BDATE'";
	     String xPathICNBatch ="//Document/ICN/CaptureInfo/CaptureItem/BATCH/TktAn";
		 String xPathICNJob ="//JOB/BusinessDate";
		 //String xPathICNJobWorkTypeNumber ="//JOB/WorkTypeNbr";
		 String xPathICNJobWorkTypeNumber ="//ICN/CaptureInfo/CaptureItem/JOB/WorkTypeNbr";

		 String xPathICNTransactionSet ="//cf_ICSTransactionID";
		 String xPathICNGender ="//CaptureItem/Gender";
		// String xPathICNEntityState ="//Entities/Entity/EntityState";
		 String xPathICNEntityId ="//Entities/Entity/EntityId";
		 
		 String xPathAIXMLJob ="//Jobs/Job/BusinessDate";
		 String xPathAIXMLJobWorkTypeNumber ="//Jobs/Job/WorkTypeNbr";
		 String xPathAIXMLTransactionSet ="//Cf_ICSTransactionID";
		 String xPathAIXMLGender="//Gender";
		 String xPathAIXMLItemId="//Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/CustomFields/cf_ICSItemID";


	    // Test Step-7 : Verify AIXML Message Loaded Into Database successfully
	     
		String xpathAIXMLJobBusinessDate ="//Jobs/Job/BusinessDate";
		String xpathAIXMLBlkNbr ="//Jobs/Job/Blocks/Block/BlkNbr";
		String aixmlFileNameCreated;
			Map<String,String> map=new LinkedHashMap<String,String>();			
			
	    	//create unique block number
			int blockNo  = GenericMethods.generateUniqueNo(4);
			System.out.println("Block Number: " + blockNo);		
			String dateVal = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
			aixmlFileNameCreated =fredaixmlTmpltFilename.concat(String.valueOf(blockNo))+dateVal+"_Actual.xml";
			
			String aixmlfile = fredaixmlTmpltFilename+".xml";
			File filetoberenamed = new File(templateFilePath+aixmlfile);
			System.out.println("FRED AIXML Template input path :"+templateFilePath+aixmlFileNameCreated);
			boolean aixmlFileNameCreated1 = filetoberenamed.renameTo(new File(templateFilePath+aixmlFileNameCreated));
			
			System.out.println("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
			
			//update Business Date in the file
			String sqlConfigBusinessDate1 ="select Value from Config.BusinessConfig where [Key]='BDATE'";
			ResultSet rsConfigBusinessDate = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, sqlConfigBusinessDate1);
			String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(rsConfigBusinessDate);
			String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
			
			String aixmlFilePathTobeUpdated=templateFilePath+aixmlFileNameCreated;
			
			map.put(xpathAIXMLJobBusinessDate, configDateValue);
			map.put(xpathAIXMLBlkNbr, String.valueOf(blockNo));
			
			ReadSQLFileUtil.updateXMLTagVal(aixmlFilePathTobeUpdated,map);
			System.out.println("2 Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
			
			//copies file to AIXML path      
			Boolean checkStatus  = GenericMethods.CopyFileToDestinationPath(aixmlFileNameCreated,templateFilePath,strAIXMLFilePath);
			Thread.sleep(3000);
			System.out.println("Check Status"+checkStatus);
			String archv = aixmlArchvFilePath+aixmlFileNameCreated;
			
			//File filename = new File(archv).exists();
			boolean result = new File(archv).exists();
			System.out.println("File exists in Archive Folder: "+result);
			
			System.out.println("Copy file to destination completed: "+aixmlArchvFilePath+aixmlFileNameCreated);
					
			System.out.println("AIXML File has been loaded into database successfully ");		 
		 
			 
			//********* Verify Job Details from the file are loaded into database ****************//
	        
			// aixmlFileNameCreated=fileToBeCopied;
			// String aixmlFileNameCreated="74431_AIXML_OC_2502_Actual.xml";
			 System.out.println("Test Steps for Job WorkTypeNbr Validation");									
			 Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xPathAIXMLJobWorkTypeNumber);
			 System.out.println("wrktype"+aixmljobWrkTypeNodeVal);
			 List<String> litsOfNodeItemIdAIXML = getNodeICNAIXMLListValues(aixmljobWrkTypeNodeVal,"WorkTypeNbr");
			 System.out.println("AIXML Job WorkType list values returned"+litsOfNodeItemIdAIXML);
			 String xmlNodeVal = litsOfNodeItemIdAIXML.get(0);
			 if(xmlNodeVal.startsWith("0")){
					xmlNodeVal=xmlNodeVal.substring(1, xmlNodeVal.length());
				}
			 String jobWrkTypNbrQuery ="select WorkTypeNbr from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			 ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, jobWrkTypNbrQuery);
			 String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			 System.out.println("wrktype value from db"+actualjobWrkTypNbrVal);
			
			 Component.assertEquals(actualjobWrkTypNbrVal, xmlNodeVal, "AIXML Job WorkTypeNbr is present in database");
				
			//********* Verify Blocks Details from the file are loaded into database ****************//
		
			String xPathAIXMLBlockNbr="//Jobs/Job/Blocks/Block/BlkNbr";
			String xPathAIXMLBlockImgType="//Jobs/Job/Blocks/Block/ImageType";
			Map<String, String> aixmlBlockNbrVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xPathAIXMLBlockNbr);
			System.out.println("Block Number :"+aixmlBlockNbrVal);
			Map<String, String> aixmlBlockImgTypVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xPathAIXMLBlockImgType);
			System.out.println("Block ImageType :"+aixmlBlockImgTypVal);
			
			//Verify Block Number under block details
			String aixmlBlockNbrValQuery ="select BlkNbr from Base.AIXMLBlocks";
			ResultSet rsBlockNbrVal = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, aixmlBlockNbrValQuery);
			String litsOfBlkVal = getNodeICNAIXMLListValues(aixmlBlockNbrVal,"BlkNbr").get(0);
			String actualBlockNbrVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsBlockNbrVal,litsOfBlkVal);
			System.out.println("AIXML block number list values returned"+litsOfBlkVal);
			System.out.println("AIXML block list values returned from sql "+actualBlockNbrVal);
			Component.assertEquals(actualBlockNbrVal, litsOfBlkVal, "AIXML block number is present in database");
			
			//Verify Block ImageType under block details
			String aixmlBlockImgTypQuery ="select ImageType from Base.AIXMLBlocks where BlkNbr='"+actualBlockNbrVal+"'";
			ResultSet rsBlockImgType = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, aixmlBlockImgTypQuery);
			String litsOfBlkImgTypeVal = getNodeICNAIXMLListValues(aixmlBlockImgTypVal,"ImageType").get(0);
			String actualBlockImageTypeVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsBlockImgType,litsOfBlkImgTypeVal);
			System.out.println("AIXML block ImageType list values returned"+litsOfBlkImgTypeVal);
			System.out.println("AIXML block ImageType list values returned from sql "+actualBlockImageTypeVal);
			Component.assertEquals(actualBlockImageTypeVal, litsOfBlkImgTypeVal, "AIXML block ImageType is present in database");
							
			//********* Verify Batches Details from the file are loaded into database ****************//			
			
			String xPathAIXMLBatchTktAn ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchTktAn";
			String blockIDquery ="select BlockID from Base.AIXMLBlocks where BlkNbr='"+litsOfBlkVal+"'";
			String batchdetailsQuery="select BatchTktAn from Base.AIXMLBatches where BlockID=("+blockIDquery+")";
			Map<String, String> aixmljobbatchNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xPathAIXMLBatchTktAn);
			ResultSet rsBatchDetails = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, batchdetailsQuery);
			String actualBatchDetailsVal = GenericMethods.getICSRetrieveColumnValues(rsBatchDetails);
			String litsOfBatchdetailsVal = getNodeICNAIXMLListValues(aixmljobbatchNodeVal,"BatchTktAn").get(0);
			System.out.println("AIXML batch list values returned"+litsOfBatchdetailsVal);
			if(litsOfBatchdetailsVal.isEmpty()){
				litsOfBatchdetailsVal="null";
			}
			System.out.println("AIXML batch list values returned from sql "+actualBatchDetailsVal);
			Component.assertEquals(actualBatchDetailsVal, litsOfBatchdetailsVal, "AIXML batch TktAn is present in database");			
							
			//********* Verify Items Details from the file are loaded into database ****************//			
			String xPathAIXMLItemISN ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/ISN";
			String batchIDquery="select BatchID from Base.AIXMLBatches where BlockID=("+blockIDquery+")";
			String itemQuery ="select ISN from Base.AIXMLItems where BatchID=("+batchIDquery+")";
			Map<String, String> aixmljobbatchItemNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xPathAIXMLItemISN);
			ResultSet rsItemDetails = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, itemQuery);
			String actualItemDetailsVal = GenericMethods.getICSRetrieveColumnValues(rsItemDetails);
			String litsOfItemdetailsVal = getNodeICNAIXMLListValues(aixmljobbatchItemNodeVal,"ISN").get(0);
			System.out.println("AIXML Item list values returned"+litsOfItemdetailsVal);
			System.out.println("AIXML Item list values returned from sql "+actualItemDetailsVal);
			Component.assertEquals(actualItemDetailsVal, litsOfItemdetailsVal, "AIXML Item ISN is present in database");
			
			//********* Verify ItemHistory Details from the file are loaded into database ****************//
					
			String xPathAIXMLItemHistoryUserId ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/UserId";
			String xPathAIXMLItemHistoryAccNbr ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/Codeline/AN";
			Map<String,String> aixmlItemHistoryUserId= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xPathAIXMLItemHistoryUserId);
			String litsOfItemHistoryUserIdVal = getNodeICNAIXMLListValues(aixmlItemHistoryUserId,"UserId").get(0);
			String itemIDQuery="select ItemID from Base.AIXMLItems where BatchID=("+batchIDquery+"))"+" and UserID="+"'"+litsOfItemHistoryUserIdVal+"'";
			String itemHistoryAccNbrQuery="select AN from Base.AIXMLItemHistory where ItemID IN ("+itemIDQuery;
			System.out.println("ItemHistory Account Number"+itemHistoryAccNbrQuery);
			ResultSet rsItemHistoryAccNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, itemHistoryAccNbrQuery);
			String actualItemHistoryAccNbrVal = GenericMethods.getICSRetrieveColumnValues(rsItemHistoryAccNbr);
			Map<String,String> aixmlItemHistoryAccNbr= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xPathAIXMLItemHistoryAccNbr);															 
			String litsOfItemHistoryAccNbrVal = getNodeICNAIXMLListValues(aixmlItemHistoryAccNbr,"AN").get(0);
			System.out.println("AIXML ItemHistory Account Number list values returned"+litsOfItemHistoryAccNbrVal);
			System.out.println("AIXML ItemHistory Account Number list values returned from sql "+actualItemHistoryAccNbrVal);
			Component.assertEquals(actualItemHistoryAccNbrVal, litsOfItemHistoryAccNbrVal, "AIXML ItemHistory Account Number is present in database");
																
			//********* Verify AIXML File Details are loaded into database****************//
			// String jobQuery ="select JobID from Base.AIXMLJobs";
			String jobQuery ="select FileName from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			ResultSet rsjobQuery = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, jobQuery);
			String actualjobVal = GenericMethods.getICSRetrieveColumnValues(rsjobQuery);
			Component.assertEquals(actualjobVal, aixmlFileNameCreated, "AIXML FIle is present in database");
			 
			//********* Verify ErrorLog Table****************//
		//	verifyErrorLog06MF01(dbServerName,fredAutoDb,aixmlArchvFilePath,aixmlFileNameCreated,ICNFileName);
			 
			//********* Verify ICN File is generated ****************//
			//verify only one output ICN file is generated
			ResultSet ICNOutputCountResultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, "select count(*) from dbo.ICNOutput where FileName ='"+aixmlFileNameCreated+"'");
			String ICNOutputRows = GenericMethods.getICSRetrieveColumnValues(ICNOutputCountResultSet);
			System.out.println("Number of ICNOutput data rows: " + ICNOutputRows);
			Assert.assertTrue(Integer.valueOf(ICNOutputRows).equals(1), "Multiple transaction ids are bundled in to one ICN file");
					
			//retrieves ICNOutput cell value
			ResultSet ICNOutputResultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, "select ICNOutput from dbo.ICNOutput where FileName ='"+aixmlFileNameCreated+"'");
			String ICNOutput = GenericMethods.getICSRetrieveColumnValues(ICNOutputResultSet);
			writeDataOfFileStream(getFileStreamWithExtension(ICNFilepath, ICNFileName, ".xml"), ICNOutput, false);	
			String ICNOutputFile = ICNFileName+".xml";
			System.out.println("ICNOutput data saved to a file in location"+ICNOutputFile);
		
	}	

	public static  void verifyErrorLog06MF01(String serverName,String dbName,String aixmlArchvFilePath,String aixmlFileName,
			String ICNFileName) throws Exception{
		
		
		//DI_ MO1_510.xml: Error converting data type nvarchar to numeric. : in usp_Shred_AIXML_History
		
				
		// System.out.println("Verify ErrorLog Table for any error message occurs");
	
		 System.out.println("Check if any aixml file is present in ErrorLog"+aixmlFileName);
		String strBusinessDateValidationWithDBInErrLog="SELECT ErrorMessage FROM Base.ErrorLog";
		ResultSet rsErrLog = ICSProductDBConnection.getICSDBServerConnection(serverName, dbName, strBusinessDateValidationWithDBInErrLog);
		String actualErrLog = GenericMethods.getICSRetrieveMutipleColumnValues(rsErrLog,aixmlFileName);
		//String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
		System.out.println("Error Log found in Base.ErrorLog Table"+actualErrLog);
		
		
		//ErrQueue Query
		String errQueueMsg = "SELECT casted_message_body = CASE message_type_name WHEN 'X' THEN CAST(message_body AS NVARCHAR(MAX)) "+
				"ELSE message_body END FROM [FREDPIT].[Base].[//FRED/MF01SendQueue] WITH(NOLOCK)";
		
		ResultSet rsErrLoginErrQueue = ICSProductDBConnection.getICSDBServerConnection(serverName, dbName, errQueueMsg);
		String actualErrLoginErrQueue = GenericMethods.getICSRetrieveMutipleColumnValues(rsErrLoginErrQueue,aixmlFileName);		
		System.out.println("Error Log found in ErrorQueue and XSD validation Failed"+actualErrLoginErrQueue);
		
		}
	
	
	public static List<String> getNodeICNAIXMLListValues(Map<String, String> aixmlNodeVal,String tagName){
		 List<String> listOfString = new ArrayList<String>();
		 for(int i=1;i<=aixmlNodeVal.size();i++){
				System.out.println("Node size "+aixmlNodeVal.size());
				System.out.println("Node values "+aixmlNodeVal.get(tagName+i));
				System.out.println("list values "+listOfString.add(aixmlNodeVal.get(tagName+i)));
				
			}
		return listOfString;
	}
		
}
