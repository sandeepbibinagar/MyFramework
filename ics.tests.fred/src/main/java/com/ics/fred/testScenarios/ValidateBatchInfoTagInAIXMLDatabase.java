package com.ics.fred.testScenarios;

import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadSQLFileUtil;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateBatchInfoTagInAIXMLDatabase extends GenericMethodUtilis{
	public static String aixmlFileNameCreated; // ="AIXMLExtract_DEBITIN_BatchInfo1417181417_Actual.xml";
	
	public static String itemIDQuery;
	public static String blockIDquery;
	public static String batchIDquery;
	public static String actualBlockNbrVal;
	public static String litsOfBlkNbrVal;
	static String itemQuery;
	
	
	public void validateBatchInfoDetailsInAIXML(){
		
		
	}
	
	
	public static boolean validateAIXMLFileMovedToArchieveFldr(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception,NullPointerException{
		
		String xpathAIXMLJobBusinessDate ="//Jobs/Job/BusinessDate";
		String xpathAIXMLBlkNbr ="//Jobs/Job/Blocks/Block/BlkNbr";
	
		Map<String,String> map=new LinkedHashMap<String,String>();			
		
    	//create unique block number
		int blockNo  = GenericMethods.generateUniqueNo(4);
		System.out.println("Block Number: " + blockNo);		
		String dateVal = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		aixmlFileNameCreated =fredaixmlTmpltFilename.concat(String.valueOf(blockNo))+dateVal+"_Actual.xml";
		
		String aixmlfile = fredaixmlTmpltFilename+".xml";
		File filetoberenamed = new File(templateFilePath+aixmlfile);
		System.out.println("Fred Template input path :"+templateFilePath+aixmlFileNameCreated);
		boolean aixmlFileNameCreated1 = filetoberenamed.renameTo(new File(templateFilePath+aixmlFileNameCreated));
		
		System.out.println("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
		
		//update Business Date in the file
		String sqlConfigBusinessDate ="select Value from Config.BusinessConfig where [Key]='BDATE'";
		ResultSet rsConfigBusinessDate = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, sqlConfigBusinessDate);
		String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(rsConfigBusinessDate);
		String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
		
		String aixmlFilePathTobeUpdated=templateFilePath+aixmlFileNameCreated;
		
		map.put(xpathAIXMLJobBusinessDate, configDateValue);
		map.put(xpathAIXMLBlkNbr, String.valueOf(blockNo));
		
		ReadSQLFileUtil.updateXMLTagVal(aixmlFilePathTobeUpdated,map);
		System.out.println("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
		validationStepInformation("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
		//copies file to AIXML path      
		Boolean checkStatus  = GenericMethods.CopyFileToDestinationPath(aixmlFileNameCreated,templateFilePath,fredAIXMLFilePath);
		Thread.sleep(3000);
		System.out.println("Check Status"+checkStatus);
		String archv = aixmlArchvFilePath+aixmlFileNameCreated;
		
		//File filename = new File(archv).exists();
		boolean result = new File(archv).exists();
		System.out.println("File exists in Archive Folder: "+result);
		validationStepInformation("File exists in Archive Folder: "+result);
		
		System.out.println("Copy file to destination completed: "+aixmlArchvFilePath+aixmlFileNameCreated);
	//	validationStepInformation("Copy file to destination completed: "+aixmlArchvFilePath+aixmlFileNameCreated);
		
		System.out.println("AIXML File has been loaded into database successfully ");
		validationStepInformation("AIXML File has been loaded into database successfully ");
		
		publishResults(result, result?"AIXMLExtract File Consumed":"AIXMLExtract File not Consumed", "AIXMLExtract File Consumed", "AIXMLExtract File Consuming");
		return result;
	
	}  

	/*******************************************************************************************************************************************
	 * Method Name: validateAIXMLFileLoadedIntoDabatase
	 * Author: Nisha Tripathi
	 * Created Date:  12-Mar-2017
	 * Description: Test Step 6 :Verify AIX	ML File Details are loaded into Job table
	 /*******************************************************************************************************************************************/
	
	public static void validateAIXMLFileLoadedIntoDabatase(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
		ArrayList<String> returnVal = new ArrayList<>();
		
		String jobFileNameQuery ="select FileName from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
		System.out.println("AIXML FileName Query:"+jobFileNameQuery);
		System.out.println("AIXML FileName:"+aixmlFileNameCreated);
		ResultSet rsjobQuery = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobFileNameQuery);
		String actualjobVal = GenericMethods.getICSRetrieveColumnValues(rsjobQuery);
		returnVal.add(actualjobVal);
		returnVal.add(aixmlFileNameCreated);
		boolean returnExpectedReslt = Component.verifyEquals(actualjobVal, aixmlFileNameCreated, "AIXMLExtract File not loaded into database.");
		System.out.println("Expected Result Val : "+returnExpectedReslt);
		System.out.println("Actual Val : "+actualjobVal);
		System.out.println("Expected Val : "+aixmlFileNameCreated);
		publishResults(returnExpectedReslt, actualjobVal, aixmlFileNameCreated, "AIXMLExtract File Loaded InTo Database.");
		if(returnExpectedReslt)
		{
			publishResults(returnExpectedReslt, actualjobVal, aixmlFileNameCreated, "AIXMLExtract File Loaded InTo Database.");
		}
		else
		{
			//go and check errorLog
			ValidateErrLogScenariosDebit.getErrorLogMsg(templateFilePath,aixmlFileNameCreated,dbServerName,fredDBName); 
			publishResults(returnExpectedReslt, actualjobVal, aixmlFileNameCreated, "AIXMLExtract File Loaded InTo Database.");
		}
		System.out.println("AIXML File loaded validaton done");
	}
	
	
	/*******************************************************************************************************************************************
	/* Method Name: validateAIXMLBatchDetailsWithDb
	/* Author: Nisha Tripathi
	/* Created Date:  10-Mar-2017
	/* Description: Test Step 9: Verify Batches Details from the file are loaded into Batch table
	/*******************************************************************************************************************************************/

	public static ArrayList<String> validateAIXMLBatchDetailsWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
		ArrayList<String> returnVal = new ArrayList<>();
		String xpathAIXMLBatchTktAn ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchTktAn";
		String xpathAIXMLBatchItemDebitCount="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchItemDebitCount";

		blockIDquery ="select BlockID from Base.AIXMLBlocks where BlkNbr='"+litsOfBlkNbrVal+"'";
		System.out.println("Block Number value in BatchDetails ID"+litsOfBlkNbrVal);
		String batchdetailsQuery="select BatchTktAn,BatchItemDebitCount from Base.AIXMLBatches where BlockID=("+blockIDquery+")";
		System.out.println("Batchdetails Query:  "+batchdetailsQuery);
		ResultSet rsBatchDetails = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, batchdetailsQuery);
		List<String> actualBatchDetailsVal = GenericMethods.getMutipleColumnsValues(rsBatchDetails);
		Map<String, String> aixmljobbatchNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchTktAn);
		String litsOfBatchdetailsVal = aixmljobbatchNodeVal.get("BatchTktAn1");
					
		Map<String, String> aixmljobbatchNodeValfordebit = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchItemDebitCount);
		String aixmljobbatchNodedebitVal = aixmljobbatchNodeValfordebit.get("BatchItemDebitCount1");
		if(aixmljobbatchNodedebitVal.startsWith("0")){
			aixmljobbatchNodedebitVal=aixmljobbatchNodedebitVal.substring(2, aixmljobbatchNodedebitVal.length());
		}
		System.out.println("litsOfBatchdetailsVal:  "+aixmljobbatchNodedebitVal);
		if(litsOfBatchdetailsVal.isEmpty()){
			litsOfBatchdetailsVal="null";
		}
		
		returnVal.add("BatchTktAn:"+actualBatchDetailsVal.get(0)+" BatchItemDebitCount:"+actualBatchDetailsVal.get(1));
		returnVal.add("BatchTktAn:"+litsOfBatchdetailsVal+" BatchItemDebitCount:"+aixmljobbatchNodedebitVal);
		System.out.println(returnVal);
		return returnVal;

	}
	
	public static ArrayList<String> validateAIXMLBatchDetailsTktWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
		ArrayList<String> returnVal = new ArrayList<>();
		String xpathAIXMLBatchTktTc  ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchTktTc";
		String xpathAIXMLBatchTktAn ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchTktAn";
		String xpathAIXMLBatchTktSc  ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchTktSc";
		String xpathAIXMLBatchTktSerial  ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchTktSerial";
		String xpathAIXMLBatchItemCreditCount  ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchItemCreditCount";
		String xpathAIXMLBatchItemDebitCount="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchItemDebitCount";
		String xpathAIXMLBatchItemCreditAmt  ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchItemCreditAmount";
		String xpathAIXMLBatchItemDebitAmt  ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchItemDebitAmount";
		
		
		blockIDquery ="select BlockID from Base.AIXMLBlocks where BlkNbr='"+litsOfBlkNbrVal+"'";
		System.out.println("Block Number value in BatchDetails ID"+litsOfBlkNbrVal);
		
		String batchdetailsQuery="select BatchTktTc,BatchTktAn,BatchTktSc,BatchTktSerial,BatchItemCreditCount,BatchItemDebitCount,BatchItemCreditAmount,BatchItemDebitAmount from Base.AIXMLBatches where BlockID=("+blockIDquery+")";
		System.out.println("Batchdetails Query:  "+batchdetailsQuery);
		ResultSet rsBatchDetails = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, batchdetailsQuery);
		List<String> actualBatchDetailsVal = GenericMethods.getMutipleColumnsValues(rsBatchDetails);
		
		Map<String, String> aixmljobbatchNodeTktTc = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchTktTc);
		String litsOfBatchTktTc = aixmljobbatchNodeTktTc.get("BatchTktTc");
		System.out.println("BatchTktTc1 "+litsOfBatchTktTc);		
		
		Map<String, String> aixmljobbatchNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchTktAn);
		String litsOfBatchdetailsVal = aixmljobbatchNodeVal.get("BatchTktAn1");
		System.out.println("BatchTktAn1 "+litsOfBatchdetailsVal);
		
		Map<String, String> aixmljobbatchNodeValfordebit = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchItemDebitCount);
		String aixmljobbatchNodedebitVal = aixmljobbatchNodeValfordebit.get("BatchItemDebitCount1");
		System.out.println("BatchItemDebitCount1 "+aixmljobbatchNodedebitVal);		
		
					
		Map<String, String> aixmljobbatchNodeValTktSerial = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchTktSerial);
		String aixmljobbatchNodeTktSerial = aixmljobbatchNodeValTktSerial.get("BatchTktSerial1");
		
		Map<String, String> aixmljobbatchNodeTktSCVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchTktSc);
		String litsOfBatchTktSc = aixmljobbatchNodeTktSCVal.get("BatchTktSc1");
					
		Map<String, String> aixmljobbatchNodeValforCreditCnt = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchItemCreditCount);
		String aixmljobbatchNodecreditCnt = aixmljobbatchNodeValforCreditCnt.get("BatchItemCreditCount1");
		
		Map<String, String> aixmljobbatchNodeCreditAmtVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchItemCreditAmt);
		String litsOfBatchdetailsCreditAmtVal = aixmljobbatchNodeCreditAmtVal.get("BatchItemCreditAmount1");
					
		Map<String, String> aixmljobbatchNodeValfordebitAmt = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchItemDebitAmt);
		String aixmljobbatchNodedebitAmtVal = aixmljobbatchNodeValfordebitAmt.get("BatchItemDebitAmount1");
	
	 System.out.println(" Database details BatchTktTc :"+actualBatchDetailsVal.get(0)+" BatchTktAn :"+actualBatchDetailsVal.get(1)+
		" BatchTktSc : "+actualBatchDetailsVal.get(2)+" BatchTktSerial : "+actualBatchDetailsVal.get(3)+
		" BatchItemCreditCount :"+actualBatchDetailsVal.get(4)+" BatchItemDebitCount :"+actualBatchDetailsVal.get(5)+
		" BatchItemCreditAmount:"+actualBatchDetailsVal.get(6)+" BatchItemDebitAmount :"+actualBatchDetailsVal.get(7));
		
	 System.out.println("Return val : "+returnVal.add("BatchTktTc :"+actualBatchDetailsVal.get(0)+" BatchTktAn :"+actualBatchDetailsVal.get(1)+
		" BatchTktSc : "+actualBatchDetailsVal.get(2)+" BatchTktSerial : "+actualBatchDetailsVal.get(3)+
		" BatchItemCreditCount :"+actualBatchDetailsVal.get(4)+" BatchItemDebitCount :"+actualBatchDetailsVal.get(5)+
		" BatchItemCreditAmount:"+actualBatchDetailsVal.get(6)+" BatchItemDebitAmount :"+actualBatchDetailsVal.get(7)));
	 
	 returnVal.add("BatchTktTc :"+actualBatchDetailsVal.get(0)+" BatchTktAn :"+actualBatchDetailsVal.get(1)+
		" BatchTktSc : "+actualBatchDetailsVal.get(2)+" BatchTktSerial : "+actualBatchDetailsVal.get(3)+
		" BatchItemCreditCount :"+actualBatchDetailsVal.get(4)+" BatchItemDebitCount :"+actualBatchDetailsVal.get(5)+
		" BatchItemCreditAmount:"+actualBatchDetailsVal.get(6)+" BatchItemDebitAmount :"+actualBatchDetailsVal.get(7));

		//returnVal.add("BatchTktAn:"+litsOfBatchdetailsVal+" BatchItemDebitCount:"+aixmljobbatchNodedebitVal);
	 System.out.println(" AIXML file details "+"BatchTktTc :"+litsOfBatchTktTc+" BatchTktAn :"+litsOfBatchdetailsVal+
				" BatchTktSc : "+litsOfBatchTktSc+" BatchTktSerial : "+aixmljobbatchNodeTktSerial+
				" BatchItemCreditCount :"+aixmljobbatchNodecreditCnt+" BatchItemDebitCount :"+aixmljobbatchNodedebitVal+
				" BatchItemCreditAmount:"+litsOfBatchdetailsCreditAmtVal+" BatchItemDebitAmount :"+aixmljobbatchNodedebitAmtVal);
	 
		returnVal.add("BatchTktTc :"+litsOfBatchTktTc+" BatchTktAn :"+litsOfBatchdetailsVal+
				" BatchTktSc : "+litsOfBatchTktSc+" BatchTktSerial : "+aixmljobbatchNodeTktSerial+
				" BatchItemCreditCount :"+aixmljobbatchNodecreditCnt+" BatchItemDebitCount :"+aixmljobbatchNodedebitVal+
				" BatchItemCreditAmount:"+litsOfBatchdetailsCreditAmtVal+" BatchItemDebitAmount :"+aixmljobbatchNodedebitAmtVal);
		
		System.out.println(returnVal);
		return returnVal;

	}
	
	
	/*******************************************************************************************************************************************
	/* Method Name: validateAIXMLBlockNumberDetailsWithDb
	/* Author: Nisha Tripathi
	/* Created Date:  10-Mar-2017
	/* Description: Test Step 8: Validate AIXML block number is present in Block table
	/*******************************************************************************************************************************************/
	public static void validateAIXMLBlockNumberDetailsWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{	
		String xpathAIXMLBlockNbr="//Jobs/Job/Blocks/Block/BlkNbr";
		//String aixmlBlockNbrValQuery ="select BlkNbr from Base.AIXMLBlocks where FileName='"+aixmlFileNameCreated+"'";
		String aixmlBlockNbrValQuery ="select BlkNbr from Base.AIXMLBlocks where JobID ="+"("+"select JobID from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"')";
		
		Map<String, String> aixmlBlockNbrVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBlockNbr);
		ResultSet rsBlockNbrVal = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, aixmlBlockNbrValQuery);
		litsOfBlkNbrVal = getNodeListValues(aixmlBlockNbrVal,"BlkNbr").get(0);
		actualBlockNbrVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsBlockNbrVal,litsOfBlkNbrVal);
		System.out.println("AIXML block number list values returned: "+litsOfBlkNbrVal);
		System.out.println("AIXML block list values returned from Database: "+actualBlockNbrVal);		
					
		boolean returnExpectedReslt = Component.verifyEquals(actualBlockNbrVal, litsOfBlkNbrVal, "Block Number of the aixml file is not loaded into database.");
		publishResults(returnExpectedReslt, actualBlockNbrVal, litsOfBlkNbrVal, "Block Number of the aixml file is loaded into database.");

	}
	
	
	public static void validateAIXMLBatchInfoValuesWithDB(String dbServerName,String fredDatabaseName,String templateFilePath,String strICNFilePath,String aixmlTempFileName,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception {	
		ArrayList<String> returnActExpctdVal=new ArrayList<>();
		boolean returnExpectedReslt;


		
		validateAIXMLFileMovedToArchieveFldr(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
		
		validateAIXMLFileLoadedIntoDabatase(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
		validateAIXMLBlockNumberDetailsWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
		
		
		
			returnActExpctdVal=validateAIXMLBatchDetailsTktWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			returnExpectedReslt = Component.verifyEquals(returnActExpctdVal.get(0), returnActExpctdVal.get(1), "BatchDetails of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, returnActExpctdVal.get(0), returnActExpctdVal.get(1), "BatchDetails of the aixml file is loaded into database.");
	
	}
	
	/*returnActExpctdVal=validateAIXMLBatchDetailsWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
	returnExpectedReslt = Component.verifyEquals(returnActExpctdVal.get(0), returnActExpctdVal.get(1), "BatchDetails of the aixml file is not loaded into database.");
	publishResults(returnExpectedReslt, returnActExpctdVal.get(0), returnActExpctdVal.get(1), "BatchDetails of the aixml file is loaded into database.");
*/
	
}
