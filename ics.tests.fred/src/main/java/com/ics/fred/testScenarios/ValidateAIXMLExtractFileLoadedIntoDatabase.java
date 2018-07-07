package com.ics.fred.testScenarios;

import java.io.*;
import java.math.BigDecimal;

/******************************************************************************************************************************************************************
/* Class Name: ValidateAIXMLDataLoadedIntoFredDatabase
/* Author: Nisha Tripathi
/* Created Date:  18-Mar-2017
/* Description: Validate AIXML Data loaded into Database and returns ICNOutput file to the fred_Output excel file.
/******************************************************************************************************************************************************************/

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadSQLFileUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateAIXMLExtractFileLoadedIntoDatabase extends ICSDBUtilis{

		/*******************************************************************************************************************************************
		/* Method Name: validateAIXMLFileMovedToArchieveFldr
		/* Author: Nisha Tripathi
		/* Created Date:  10-Mar-2017
		/* Description: Test Step-6 : Verify AIXML Message Loaded Into Database successfully
		/*******************************************************************************************************************************************/
		public static String aixmlFileNameCreated; //="AIXMLExtract_OC_Nisha12.xml";
		public static String itemIDQuery;
		public static String blockIDquery;
		public static String batchIDquery;
		public static String actualBlockNbrVal;
		public static String litsOfBlkNbrVal;
		static String itemQuery;
	
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
		 * Method Name: validateAIXMLJobWorkTypeDetailsWithDb
		 * Author: Nisha Tripathi
		 * Created Date:  10-Mar-2017
		 * Description: TestStep 7: AIXML Job WorkTypeNbr is present in Job table
		 /********************************************************************************************************************************************/
		
		public static void validateAIXMLJobWorkTypeDetailsWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/WorkTypeNbr";
			String xmlNodeVal;
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> litsOfNodeWrkTypeNbrAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"WorkTypeNbr");
			System.out.println("AIXML Job WorkType list values returned: "+litsOfNodeWrkTypeNbrAIXML);
			
			xmlNodeVal = litsOfNodeWrkTypeNbrAIXML.get(0);
			if(xmlNodeVal.isEmpty()){
				System.out.println("xmlNodeVal "+xmlNodeVal+"validate database for eror log");
			}
			else
			{
				if(xmlNodeVal.startsWith("0")){
					xmlNodeVal=xmlNodeVal.substring(1, xmlNodeVal.length());
				}
			}
			String jobWrkTypNbrQuery ="select WorkTypeNbr from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("JobWorkType Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("Wrktype value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(xmlNodeVal);
			System.out.println("worktype return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, xmlNodeVal, "Job WorkType Number of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, xmlNodeVal, "Job WorkType Number of the aixml file is loaded into database.");
		}
		
		public static void validateAIXMLJobInstallationIdWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/InstallationId";
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> litsOfNodeWrkTypeNbrAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"InstallationId");
			System.out.println("AIXML Job InstallationId list values returned: "+litsOfNodeWrkTypeNbrAIXML);
			String xmlNodeVal = litsOfNodeWrkTypeNbrAIXML.get(0);
			String jobWrkTypNbrQuery ="select InstallationId from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("Job InstallationId Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("InstallationId value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(xmlNodeVal);
			System.out.println("InstallationId return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, xmlNodeVal, "Job InstallationId of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, xmlNodeVal, "Job InstallationId of the aixml file is loaded into database.");
		}

		public static void validateAIXMLJobCaptureSystemIdWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/CaptureSystemId";
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> litsOfNodeWrkTypeNbrAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"CaptureSystemId");
			System.out.println("AIXML Job CaptureSystemId list values returned: "+litsOfNodeWrkTypeNbrAIXML);
			String xmlNodeVal = litsOfNodeWrkTypeNbrAIXML.get(0);
			String jobWrkTypNbrQuery ="select CaptureSystemId from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("Job CaptureSystemId Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("CaptureSystemId value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(xmlNodeVal);
			System.out.println("CaptureSystemId return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, xmlNodeVal, "Job CaptureSystemId Number of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, xmlNodeVal, "Job CaptureSystemId Number of the aixml file is loaded into database.");
		}

		public static void validateAIXMLJobStartTimeWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/StartTime";
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> jobStartTimeAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"StartTime");
			System.out.println("AIXML Job StartTime list values returned: "+jobStartTimeAIXML);
						
			//2016-09-22T01:30:55+00:00
			//2016-09-22 01:30:55.0000000
			String xmlNodeVal = jobStartTimeAIXML.get(0);
			//xmlNodeVal=xmlNodeVal.replace("T", " ");
			int dateLength = xmlNodeVal.length();
			int index = xmlNodeVal.indexOf("T");
			String datePart =xmlNodeVal.substring(0,index);
			String dateSecondPart =xmlNodeVal.substring(index,dateLength);
			String dateThirdPart =dateSecondPart.substring(1,index-1);
		//	int index1 = xmlNodeVal.indexOf("+");
						System.out.println(" second part datePart :"+datePart+" date second part:"+dateSecondPart+" third datePart:"+dateThirdPart);
			String expectedStartTime = datePart+" "+dateThirdPart+".0000000";
			System.out.println("finalDate "+expectedStartTime);
			
			
			String jobWrkTypNbrQuery ="select StartTime from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("Job StartTime Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("StartTime value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(expectedStartTime);
			System.out.println("StartTime return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, expectedStartTime, "Job StartTime Number of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, expectedStartTime, "Job StartTime Number of the aixml file is loaded into database.");
		}

		public static void validateAIXMLJobEndTimeWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/EndTime";
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> jobEndTimeAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"EndTime");
			System.out.println("AIXML Job EndTime list values returned: "+jobEndTimeAIXML);
			
			//2016-09-22T01:30:55+00:00
			//2016-09-22 01:30:55.0000000
			String xmlNodeVal = jobEndTimeAIXML.get(0);
			//xmlNodeVal=xmlNodeVal.replace("T", " ");
			int dateLength = xmlNodeVal.length();
			int index = xmlNodeVal.indexOf("T");
			String datePart =xmlNodeVal.substring(0,index);
			String dateSecondPart =xmlNodeVal.substring(index,dateLength);
			String dateThirdPart =dateSecondPart.substring(1,index-1);
		//	int index1 = xmlNodeVal.indexOf("+");
			System.out.println(" second part datePart :"+datePart+" date second part:"+dateSecondPart+" third datePart:"+dateThirdPart);
			String expectedEndTime = datePart+" "+dateThirdPart+".0000000";
			System.out.println("finalDate "+expectedEndTime);
			
			String jobWrkTypNbrQuery ="select EndTime from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("Job EndTime Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("EndTime value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(xmlNodeVal);
			System.out.println("EndTime return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, expectedEndTime, "Job EndTime Number of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, expectedEndTime, "Job EndTime Number of the aixml file is loaded into database.");
		}

		public static void validateAIXMLJobSortFamilyWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/SortFamily";
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> litsOfNodeWrkTypeNbrAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"SortFamily");
			System.out.println("AIXML Job SortFamily list values returned: "+litsOfNodeWrkTypeNbrAIXML);
			String xmlNodeVal = litsOfNodeWrkTypeNbrAIXML.get(0);
			if(xmlNodeVal.startsWith("0")){
				xmlNodeVal=xmlNodeVal.substring(1, xmlNodeVal.length());
			}
			String jobWrkTypNbrQuery ="select WorkTypeNbr from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("JobWorkType Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("Wrktype value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(xmlNodeVal);
			System.out.println("worktype return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, xmlNodeVal, "Job WorkType Number of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, xmlNodeVal, "Job WorkType Number of the aixml file is loaded into database.");
		}

		public static void validateAIXMLJobSourceTypeWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/SourceType";
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> litsOfNodeWrkTypeNbrAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"SourceType");
			System.out.println("AIXML Job SourceType list values returned: "+litsOfNodeWrkTypeNbrAIXML);
			String xmlNodeVal = litsOfNodeWrkTypeNbrAIXML.get(0);
			if(xmlNodeVal.startsWith("0")){
				xmlNodeVal=xmlNodeVal.substring(1, xmlNodeVal.length());
			}
			String jobWrkTypNbrQuery ="select SourceType from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("JobSourceType Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("SourceType value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(xmlNodeVal);
			System.out.println("SourceType return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, xmlNodeVal, "Job SourceType of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, xmlNodeVal, "Job SourceType of the aixml file is loaded into database.");
		}
		public static void validateAIXMLJobSourceNameWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/SourceName";
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> litsOfNodeWrkTypeNbrAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"SourceName");
			System.out.println("AIXML Job SourceName list values returned: "+litsOfNodeWrkTypeNbrAIXML);
			String xmlNodeVal = litsOfNodeWrkTypeNbrAIXML.get(0);
			if(xmlNodeVal.startsWith("0")){
				xmlNodeVal=xmlNodeVal.substring(1, xmlNodeVal.length());
			}
			String jobWrkTypNbrQuery ="select SourceName from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("Job SourceName Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("SourceName value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(xmlNodeVal);
			System.out.println("SourceName return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, xmlNodeVal, "Job SourceName of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, xmlNodeVal, "Job SourceName of the aixml file is loaded into database.");
		}
		public static void validateAIXMLJobSourceIdWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/SourceId";
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> litsOfNodeWrkTypeNbrAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"SourceId");
			System.out.println("AIXML Job SourceId list values returned: "+litsOfNodeWrkTypeNbrAIXML);
			String xmlNodeVal = litsOfNodeWrkTypeNbrAIXML.get(0);
			String jobWrkTypNbrQuery ="select SourceId from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("Job SourceId Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("SourceId value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(xmlNodeVal);
			System.out.println("SourceId return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, xmlNodeVal, "Job SourceId Number of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, xmlNodeVal, "Job SourceId Number of the aixml file is loaded into database.");
		}
		public static void validateAIXMLJobFinancialInstitutionIdWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/FinancialInstitutionId";
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> litsOfNodeWrkTypeNbrAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"FinancialInstitutionId");
			System.out.println("AIXML Job FinancialInstitutionId list values returned: "+litsOfNodeWrkTypeNbrAIXML);
			String xmlNodeVal = litsOfNodeWrkTypeNbrAIXML.get(0);
			
			String jobWrkTypNbrQuery ="select FinancialInstitutionId from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("JobWorkType Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("FinancialInstitutionId value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(xmlNodeVal);
			System.out.println("FinancialInstitutionId return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, xmlNodeVal, "Job FinancialInstitutionId Number of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, xmlNodeVal, "Job FinancialInstitutionId Number of the aixml file is loaded into database.");
		}
		public static void validateAIXMLJobCollectionStartTimeWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobCollectionStartTime ="//Jobs/Job/CollectionStartTime";
			Map<String, String> aixmljobCollectionStrtTimeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobCollectionStartTime);
			List<String> jobCollectionStartTimeAIXML = getNodeListValues(aixmljobCollectionStrtTimeNodeVal,"CollectionStartTime");
			System.out.println("AIXML Job CollectionStartTime list values returned: "+jobCollectionStartTimeAIXML);
			//2016-09-22T01:30:55+00:00
			//2016-09-22 01:30:55.0000000
			String xmlNodeVal = jobCollectionStartTimeAIXML.get(0);
			//xmlNodeVal=xmlNodeVal.replace("T", " ");
			int dateLength = xmlNodeVal.length();
			int index = xmlNodeVal.indexOf("T");
			String datePart =xmlNodeVal.substring(0,index);
			String dateSecondPart =xmlNodeVal.substring(index,dateLength);
			String dateThirdPart =dateSecondPart.substring(1,index-1);
		//	int index1 = xmlNodeVal.indexOf("+");
						System.out.println(" second part datePart :"+datePart+" date second part:"+dateSecondPart+" third datePart:"+dateThirdPart);
			String expectedCollectStartTime = datePart+" "+dateThirdPart+".0000000";
			System.out.println("finalDate "+expectedCollectStartTime);
			//expectedCollectStartTime
			
			String jobCollectStartTimeQuery ="select CollectionStartTime from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("Job CollectionStartTime Query : "+jobCollectStartTimeQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobCollectStartTimeQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("Job CollectionStartTime value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(expectedCollectStartTime);
			System.out.println("CollectionStartTime return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, expectedCollectStartTime, "Job CollectionStartTime of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, expectedCollectStartTime, "Job CollectionStartTime of the aixml file is loaded into database.");
		}
		public static void validateAIXMLJobCollectionEndTimeWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobWorkTypeNumber ="//Jobs/Job/CollectionEndTime";
			Map<String, String> aixmljobWrkTypeNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobWorkTypeNumber);
			List<String> jobCollectionEndTimeAIXML = getNodeListValues(aixmljobWrkTypeNodeVal,"CollectionEndTime");
			System.out.println("AIXML Job CollectionEndTime list values returned: "+jobCollectionEndTimeAIXML);
			//2016-09-22T01:30:55+00:00
			//2016-09-22 01:30:55.0000000
			String xmlNodeVal = jobCollectionEndTimeAIXML.get(0);
			//xmlNodeVal=xmlNodeVal.replace("T", " ");
			int dateLength = xmlNodeVal.length();
			int index = xmlNodeVal.indexOf("T");
			String datePart =xmlNodeVal.substring(0,index);
			String dateSecondPart =xmlNodeVal.substring(index,dateLength);
			String dateThirdPart =dateSecondPart.substring(1,index-1);
		//	int index1 = xmlNodeVal.indexOf("+");
			System.out.println(" second part datePart :"+datePart+" date second part:"+dateSecondPart+" third datePart:"+dateThirdPart);
			String expectedCollectEndTime = datePart+" "+dateThirdPart+".0000000";
			System.out.println("finalDate "+expectedCollectEndTime);
			
			String jobWrkTypNbrQuery ="select CollectionEndTime from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			System.out.println("CollectionEndTime Query : "+jobWrkTypNbrQuery);
			ResultSet rsjobWrkTypNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobWrkTypNbrQuery);
			String  actualjobWrkTypNbrVal = GenericMethods.getICSRetrieveColumnValues(rsjobWrkTypNbr);
			System.out.println("CollectionEndTime value from db: "+actualjobWrkTypNbrVal);          
			returnVal.add(actualjobWrkTypNbrVal);
			returnVal.add(expectedCollectEndTime);
			System.out.println("CollectionEndTime return values :"+returnVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobWrkTypNbrVal, expectedCollectEndTime, "Job CollectionEndTime of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualjobWrkTypNbrVal, expectedCollectEndTime, "Job CollectionEndTime the aixml file is loaded into database.");
		}


		/*******************************************************************************************************************************************
		/* Method Name: validateAIXMLJobBusinessDateValWithDb
		/* Author: Nisha Tripathi
		/* Created Date:  10-Mar-2017
		/* Description: Test step 7: Validate aixml Job BusinessDate is loaded into Job table
		/*******************************************************************************************************************************************/
		public static void validateAIXMLJobBusinessDateValWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLJobBusinessDate ="//Jobs/Job/BusinessDate";
			System.out.println("BusinessDate starts");
			//publishResults(true, "Nisha", "Nisha", "Job BusinessDate of the aixml file is loaded into database.");
			Map<String, String> aixmljobBusinessDateNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLJobBusinessDate);
			List<String> litsOfNodeBusinessDateAIXML = getNodeListValues(aixmljobBusinessDateNodeVal,"BusinessDate");
			System.out.println("AIXML Job BusinessDate list values returned: "+litsOfNodeBusinessDateAIXML);
			String xmlBusinessDateNodeVal = litsOfNodeBusinessDateAIXML.get(0);
			String jobBusinessDateQuery ="select BusinessDate from Base.AIXMLJobs where FileName='"+aixmlFileNameCreated+"'";
			ResultSet rsjobBusinessDate = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, jobBusinessDateQuery);
			String  actualjobBusinessDateVal = GenericMethods.getICSRetrieveColumnValues(rsjobBusinessDate);
			System.out.println("BusinessDate value from db: "+actualjobBusinessDateVal);
			//returnVal.add(actualjobBusinessDateVal);
			//returnVal.add(xmlBusinessDateNodeVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualjobBusinessDateVal, xmlBusinessDateNodeVal, "AIXMLExtract File Job BusinessDate.");
			publishResults(returnExpectedReslt, actualjobBusinessDateVal, xmlBusinessDateNodeVal, "Job BusinessDate of the aixml file is loaded into database.");
			//return returnVal;
		
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

		/*******************************************************************************************************************************************
		/* Method Name: validateAIXMLBlockImageTypeDetailWithDb
		/* Author: Nisha Tripathi
		/* Created Date:  10-Mar-2017
		/* Description: Test step 8 : Validate AIXML block ImageType is present in Block table
		/*******************************************************************************************************************************************/

		public static void validateAIXMLBlockImageTypeDetailWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{	
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLBlockImgType="//Jobs/Job/Blocks/Block/ImageType";
			Map<String, String> aixmlBlockImgTypVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBlockImgType);
			System.out.println("Block ImageType :"+aixmlBlockImgTypVal);
			System.out.println("Block Number Value in Image type methods :"+actualBlockNbrVal);
			String aixmlBlockImgTypQuery ="select ImageType from Base.AIXMLBlocks where BlkNbr='"+actualBlockNbrVal+"'";
			ResultSet rsBlockImgType = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, aixmlBlockImgTypQuery);
			String litsOfBlkImgTypeVal = getNodeListValues(aixmlBlockImgTypVal,"ImageType").get(0);
			String actualBlockImageTypeVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsBlockImgType,litsOfBlkImgTypeVal);
			System.out.println("AIXML block ImageType list values returned"+litsOfBlkImgTypeVal);
			System.out.println("AIXML block ImageType list values returned from sql "+actualBlockImageTypeVal);
			//returnVal.add(actualBlockImageTypeVal);
			//returnVal.add(litsOfBlkImgTypeVal);
			boolean returnExpectedReslt = Component.verifyEquals(actualBlockImageTypeVal, litsOfBlkImgTypeVal, "Block ImageType of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualBlockImageTypeVal, litsOfBlkImgTypeVal, "Block ImageType of the aixml file is loaded into database.");
	
		}

		/*******************************************************************************************************************************************
		/* Method Name: validateAIXMLBatchDetailsWithDb
		/* Author: Nisha Tripathi
		/* Created Date:  10-Mar-2017
		/* Description: Test Step 9: Verify Batches Details from the file are loaded into Batch table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateAIXMLBatchDetailsTktANWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLBatchTktAn ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/BatchTktAn";
			blockIDquery ="select BlockID from Base.AIXMLBlocks where BlkNbr='"+litsOfBlkNbrVal+"'";
			System.out.println("Block Number value in BatchDetails ID"+litsOfBlkNbrVal);
			String batchdetailsQuery="select BatchTktAn from Base.AIXMLBatches where BlockID=("+blockIDquery+")";
			ResultSet rsBatchDetails = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, batchdetailsQuery);
			String actualBatchDetailsVal = GenericMethods.getICSRetrieveColumnValues(rsBatchDetails);
			Map<String, String> aixmljobbatchNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLBatchTktAn);
			String litsOfBatchdetailsVal = getNodeListValues(aixmljobbatchNodeVal,"BatchTktAn").get(0);
			System.out.println("AIXML batch list values returned"+litsOfBatchdetailsVal);
			System.out.println("AIXML batch list values returned from sql "+actualBatchDetailsVal);
			returnVal.add(actualBatchDetailsVal);
			returnVal.add(litsOfBatchdetailsVal);
			return returnVal;

		}

		/*******************************************************************************************************************************************
		/* Method Name: validateAIXMLItemDetailsWithDb
		/* Author: Nisha Tripathi
		/* Created Date:  10-Mar-2017
		/* Description: Test Step 10 : Verify Items ISN from the file are loaded into Item table
		/*******************************************************************************************************************************************/

		public static void validateAIXMLItemDetailsWithDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String litsOfItemdetailsVal =null;
			String actualItemDetailsVal =null;
			String xpathAIXMLItemISN ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/ISN";
			batchIDquery="select BatchID from Base.AIXMLBatches where BlockID=("+blockIDquery+")";
			itemQuery ="select ISN from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
			System.out.println("itemQuery "+itemQuery);
			ResultSet rsItemDetails = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemQuery);
			Map<String, String> aixmljobbatchItemNodeVal = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemISN);
			for(int i=0;i<aixmljobbatchItemNodeVal.size();i++){
				litsOfItemdetailsVal = getNodeListValues(aixmljobbatchItemNodeVal,"ISN").get(i);
				actualItemDetailsVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemDetails,litsOfItemdetailsVal);
				System.out.println("AIXML Item list values returned"+litsOfItemdetailsVal);
				System.out.println("AIXML Item list values returned from sql "+actualItemDetailsVal);
				returnVal.add(actualItemDetailsVal);
				returnVal.add(litsOfItemdetailsVal);
			
			//return returnVal;
				System.out.println("Validate ISN");
			boolean returnExpectedReslt = Component.verifyEquals(actualItemDetailsVal, litsOfItemdetailsVal, "Item ISN of the aixml file is not loaded into database.");
			System.out.println("Validate ISN "+returnExpectedReslt);
			GenericMethodUtilis.validationStepInformation("Validate ItemHistory ISN Value");
			System.out.println("Result:  ");
			GenericMethodUtilis.publishResults(returnExpectedReslt, actualItemDetailsVal, actualItemDetailsVal, "Item ISN of the aixml file is loaded into database.");
			}

		}

		/*******************************************************************************************************************************************
		/* Method Name: validateAIXMLItemHistoryDetailsWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  12-Mar-2017
		/* Description: Test Step-11 : Verify ItemHistory Account Details from the file are loaded into ItemHistory table
		/*******************************************************************************************************************************************/

		public static void validateAIXMLItemHistoryDetailsWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemHistoryUserId ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/UserId";
			String xpathAIXMLItemHistoryAccNbr ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/Codeline/AN";
			Map<String,String> aixmlItemHistoryUserId= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemHistoryUserId);
			Map<String,String> aixmlItemHistoryAccNbr= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemHistoryAccNbr);															 
			for(int i=0;i<aixmlItemHistoryAccNbr.size();i++){
				String litsOfItemHistoryUserIdVal = getNodeListValues(aixmlItemHistoryUserId,"UserId").get(i);
				String litsOfItemHistoryAccNbrVal = getNodeListValues(aixmlItemHistoryAccNbr,"AN").get(i);
				System.out.println("AIXML ItemHistory Account Number list values returned"+litsOfItemHistoryAccNbrVal);
				System.out.println("Block id qury :"+blockIDquery);
				System.out.println("Batch id qury :"+batchIDquery);
				itemIDQuery="select ItemID from Base.AIXMLItems where BatchID IN ("+batchIDquery+"))";
				System.out.println("ItemID Query in Account method:"+itemIDQuery);
				String itemUserIDQuery=itemIDQuery+" and UserID="+"'"+litsOfItemHistoryUserIdVal+"'";
				String itemHistoryAccNbrQuery="select AN from Base.AIXMLItemHistory where ItemID IN ("+itemUserIDQuery;
				System.out.println("ItemHistory Account Number"+itemHistoryAccNbrQuery);
				ResultSet rsItemHistoryAccNbr = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemHistoryAccNbrQuery);
				String actualItemHistoryAccNbrVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemHistoryAccNbr,litsOfItemHistoryAccNbrVal);		
				System.out.println("AIXML ItemHistory Account Number list values returned from sql "+actualItemHistoryAccNbrVal);
			//	returnVal.add(actualItemHistoryAccNbrVal);
			//	returnVal.add(litsOfItemHistoryAccNbrVal);
				boolean returnExpectedReslt = Component.verifyEquals(actualItemHistoryAccNbrVal, litsOfItemHistoryAccNbrVal, "ItemHistory Acount of the aixml file is not loaded into database.");
				publishResults(returnExpectedReslt, actualItemHistoryAccNbrVal, litsOfItemHistoryAccNbrVal, "ItemHistory Account of the aixml file is loaded into database.");
				
			}
			//return returnVal;
		}

		/*******************************************************************************************************************************************
		/* Method Name: verifyErrorLog06MF01()
		/* Author: Nisha Tripathi
		/* Created Date:  12-Mar-2017
		/* Description: Test Step 12: Verify ErrorLog Table
		/*******************************************************************************************************************************************/

		public static void verifyErrorLog06MF01(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			System.out.println("Verify ErrorLog Table for any error occurs");
			System.out.println("Check if processed "+aixmlFileNameCreated+" AIXML file is present in ErrorLog Table");
		//	LocalDate date = LocalDate.now();
			SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.sssssss");
			Date now = new Date();
			String strDate = sdfDate.format(now);
			System.out.println("strDate " +strDate);
					
			String errorLogQuery="SELECT ErrorMessage FROM Base.ErrorLog where OccuredDateTime='"+strDate+"'";
			System.out.println(errorLogQuery);
			ResultSet rsErrLog = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, errorLogQuery);
			ArrayList<String> actualErrLog = GenericMethods.getICSRetrieveErrColumnValues(rsErrLog,aixmlFileNameCreated);	
			System.out.println("Error Log found for "+aixmlFileNameCreated+" in ErrorLog Table :"+actualErrLog);
		//	ICSGenericUtils.setPITOutputTestData(fredOutputFilePath,sheetName,sheetName,"fredAIXMLErrLog",actualErrLog);
			boolean returnExpectedReslt = false;
			if(!(actualErrLog.size()==0))
			{
				returnExpectedReslt = Component.verifyEquals(actualErrLog.get(0), actualErrLog.get(1), "ErrorLog is present in database.");
				publishResults(returnExpectedReslt, actualErrLog.get(0), actualErrLog.get(1), actualErrLog+" ErrorLog is present for "+aixmlFileNameCreated+" aixml file loaded into database");
			}
			else 
				{
				returnExpectedReslt = Component.verifyTrue(actualErrLog.size()==0, "ErrorLog is not present in database.");
				publishResults(returnExpectedReslt, (actualErrLog.size()==0)?"ErrorLog is not present in database.":"Issue Found!!","ErrorLog is not present in database.","ErrorLog is not present in database.");
			}
			
			
		}

		/*******************************************************************************************************************************************
		/* Method Name: getICNOutputFileFromDatabase
		/* Author: Nisha Tripathi
		/* Created Date:  12-Mar-2017
		/* Description: Test Step 13: Verify ICNoutput File is generated in ICNOutput table
		/*******************************************************************************************************************************************/

		public static String getICNOutputFileFromDatabase(String dbServerName,String fredDBName,String templateFilePath,String icnFilepath,String fredaixmlTmpltFilename,String icnFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			//verify only one output ICN file is generated
			int blockNo  = GenericMethods.generateUniqueNo(4);
			icnFileName=icnFileName.concat(String.valueOf(blockNo));
			System.out.println("ICNFileName :"+icnFileName);
			
			ResultSet ICNOutputCountResultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, "select count(*) from Base.ICNOutput where FileName ='"+aixmlFileNameCreated+"'");
			String ICNOutputRows = GenericMethods.getICSRetrieveColumnValues(ICNOutputCountResultSet);
			//retrieves ICNOutput cell value
			ResultSet icnOutputResultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, "select ICNOutput from Base.ICNOutput where FileName ='"+aixmlFileNameCreated+"'");
			String icnOutput = GenericMethods.getICSRetrieveColumnValues(icnOutputResultSet);
			writeDataOfFileStream(getFileStreamWithExtension(icnFilepath, icnFileName, ".xml"), icnOutput, false);
			
			String icnOutputFileTC58270 = icnFilepath+icnFileName+".xml";
			System.out.println("ICNOutput data saved to a file in location :"+icnOutputFileTC58270);
		//	ICSGenericUtils.setPITOutputTestData(fredOutputFilePath,sheetName,sheetName,"fredICNOutputTC58720",icnOutputFileTC58270);
			
			//String returnExpctdVal=null;
		//	returnExpctdVal=getICNOutputFileFromDatabase(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			validationStepInformation("ICNOutput File:"+icnFileName);
			publishResults(Integer.valueOf(ICNOutputRows).equals(1), (Integer.valueOf(ICNOutputRows).equals(1))?"One ICNOutput File is Present":"More Than one file is present", "ICNOutput File Got Generated in FRED database", "ICNOutput File Count Verifying");
			
			
			return icnFileName;
		}

		/*******************************************************************************************************************************************
		/* Method Name: getEntityStateICNOutputFileFromDatabase
		/* Author: Nisha Tripathi
		/* Created Date:  12-Mar-2017
		/* Description: Get entity state from ICNOutput.xml file
		/*******************************************************************************************************************************************/

		public static String getEntityStateICNOutputFileFromDatabase(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			String startTag="<EntityState>";
			String endTag="</EntityState>";
			String icnOutputFile = getICNOutputFileFromDatabase(dbServerName,fredDBName,templateFilePath,ICNFilepath,fredaixmlTmpltFilename,ICNFileName,fredAIXMLFilePath,aixmlArchvFilePath);
			System.out.println("ICNOutputFilename :"+ICNFilepath+icnOutputFile);
			String entityStateVal = ReadSQLFileUtil.getSQLFileTagValues(ICNFilepath,icnOutputFile+".xml",startTag,endTag);
			System.out.println("ICNOutputFilename EnityState Value :"+entityStateVal);
			
			return entityStateVal;            
		}	

		/*******************************************************************************************************************************************
		/* Method Name: validateAIXMLCustomFieldsDetailsWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  14-Mar-2017
		/* Description: Test Step 13: Verify CustomFields ItemID Details from the file are loaded into CustomFields Table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateAIXMLCustomFieldsDetailsWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			
			String litsOfCustomFieldsItemID = null;
			String cfICSItemIDQuery = null;
			String actualCustomFieldsItemID =null;
			String xpathAIXMLCustomFieldsItemID ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/CustomFields/cf_ICSItemID";
			Map<String,String> aixmlItemCustomFieldItemID= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLCustomFieldsItemID);
			System.out.println("Custom Fields ItemID Aixml: "+aixmlItemCustomFieldItemID.size());
			for(int i=0;i<aixmlItemCustomFieldItemID.size();i++){			
				litsOfCustomFieldsItemID = getNodeListValues(aixmlItemCustomFieldItemID,"cf_ICSItemID").get(i);
				cfICSItemIDQuery ="select cf_ICSItemID from Base.AIXMLCustomFields where cf_ICSItemID='"+litsOfCustomFieldsItemID+"'";		
				//String cfICSItemIDQuery ="select cf_ICSItemID from Base.AIXMLCustomFields where ItemID IN ("+itemIDQuery;
				System.out.println("Custom Fields ItemID query: "+cfICSItemIDQuery);
				ResultSet rsCustomFldsItemID = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, cfICSItemIDQuery);
				actualCustomFieldsItemID= GenericMethods.getICSRetrieveColumnValues(rsCustomFldsItemID);
				System.out.println("AIXML CustomFields ItemID list values returned"+litsOfCustomFieldsItemID);
				System.out.println("AIXML CustomFields ItemID list values returned from sql "+actualCustomFieldsItemID);		
				returnVal.add(actualCustomFieldsItemID);
				returnVal.add(litsOfCustomFieldsItemID);
						
			}	
			System.out.println("Custom ReturnValue :"+returnVal);
			return returnVal;
		}	

		/*******************************************************************************************************************************************
		/* Method Name: getNodeListValues
		/* Author: Nisha Tripathi
		/* Created Date:  10-Mar-2017
		/* Description: Get NodeList values from XML
		/*******************************************************************************************************************************************/

		public static List<String> getNodeListValues(Map<String, String> aixmlNodeVal,String tagName){
			List<String> listOfString = new ArrayList<String>();
			for(int i=1;i<=aixmlNodeVal.size();i++){
			//	System.out.println("Node size "+aixmlNodeVal.size());
			//	System.out.println("Node values "+aixmlNodeVal.get(tagName+i));
				listOfString.add(aixmlNodeVal.get(tagName+i));
			}
			return listOfString;
		}
		
		
		public static ArrayList<String> getEntityStateICNOutputFileFromDb(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String listofEntitySt =null;
			String xpathICNEntitySt = "//ICN/Core/Entities/Entity/EntityState";
			String xpathICNEntityID = "//ICN/Core/Entities/Entity/EntityId";
			Map<String,String> icnEntityState= getXMLNodeValueByXPATH(ICNFilepath, ICNFileName,xpathICNEntitySt);															 
			for(int i=0;i<icnEntityState.size();i++){
				listofEntitySt = getNodeListValues(icnEntityState,"EntityState").get(i);
				System.out.println("List of Entity State: "+i+" "+listofEntitySt);
				returnVal.add(listofEntitySt);
			}
			return returnVal;
		}	


		/*******************************************************************************************************************************************
		/* Method Name: validateItemHistoryAmountWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: AIXML Item Amount is present in Item table
		/*******************************************************************************************************************************************/

		public static void validateRepairedAIXMLItemAmountWithDBValOld(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			System.out.println("*********** Validate AIXML Item Amount is present in Item table **********");
			double actualItemHistoryAmountVal=0;
			double litsOfItemHistoryAmtVal=0;
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemHistoryAmtNbr ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/Codeline/AM";
			Map<String,String> aixmlItemHistoryAmt= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemHistoryAmtNbr);															 
			for(int i=0;i<aixmlItemHistoryAmt.size();i++){
				litsOfItemHistoryAmtVal = Double.valueOf(getNodeListValues(aixmlItemHistoryAmt,"AM").get(i));
				System.out.println("ItemID Query in itemHistory Amount method: "+itemIDQuery);
				String itemHistoryAmtQuery="select AM from Base.AIXMLItemHistory where ItemID IN ("+itemIDQuery;
				System.out.println("ItemHistory Amount Number"+itemHistoryAmtQuery);
				ResultSet rsItemHistoryAmt = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemHistoryAmtQuery);
				while(rsItemHistoryAmt.next())
				{
					actualItemHistoryAmountVal  = rsItemHistoryAmt.getDouble(1);
					if(actualItemHistoryAmountVal == litsOfItemHistoryAmtVal)
						break;
				}

				System.out.println("AIXML ItemHistory Amount Number list values returned"+litsOfItemHistoryAmtVal);
				System.out.println("AIXML ItemHistory Amount Number list values returned from sql "+actualItemHistoryAmountVal);
				
			//	returnVal.add(String.valueOf(actualItemHistoryAmountVal));
			//	returnVal.add(String.valueOf(litsOfItemHistoryAmtVal));
			
			//return returnVal;
			
			boolean returnExpectedReslt = Component.verifyEquals(String.valueOf(actualItemHistoryAmountVal), String.valueOf(litsOfItemHistoryAmtVal), "ItemHistory Amount fields of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, String.valueOf(actualItemHistoryAmountVal), String.valueOf(litsOfItemHistoryAmtVal), "ItemID of the aixml file is loaded into database.");
			}
	}
		
		public static void validateRepairedAIXMLItemAmountWithDBVal(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			System.out.println("*********** Validate AIXML Item Amount is present in Item table **********");
			String actualItemHistoryAmountVal = null;
			String litsOfItemHistoryAmtVal;
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemHistoryAmtNbr ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/Codeline/AM";
			Map<String,String> aixmlItemHistoryAmt= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemHistoryAmtNbr);															 
			for(int i=0;i<aixmlItemHistoryAmt.size();i++){
				litsOfItemHistoryAmtVal = getNodeListValues(aixmlItemHistoryAmt,"AM").get(i);
				System.out.println("ItemID Query in itemHistory Amount method: "+itemIDQuery);
				String itemHistoryAmtQuery="select AM from Base.AIXMLItemHistory where ItemID IN ("+itemIDQuery;
				System.out.println("ItemHistory Amount Number"+itemHistoryAmtQuery);
				ResultSet rsItemHistoryAmt = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemHistoryAmtQuery);
				actualItemHistoryAmountVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemHistoryAmt,litsOfItemHistoryAmtVal);
				
				System.out.println("AIXML ItemHistory Amount Number list values returned"+litsOfItemHistoryAmtVal);
				System.out.println("AIXML ItemHistory Amount Number list values returned from sql "+actualItemHistoryAmountVal);
				
			//	returnVal.add(String.valueOf(actualItemHistoryAmountVal));
			//	returnVal.add(String.valueOf(litsOfItemHistoryAmtVal));
			
			//return returnVal;
			
			boolean returnExpectedReslt = Component.verifyEquals(actualItemHistoryAmountVal, litsOfItemHistoryAmtVal, "ItemHistory Amount fields of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, actualItemHistoryAmountVal, litsOfItemHistoryAmtVal, "ItemHistory Amount fields of the aixml file is loaded into database.");
			}
	}



		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemAccountWithDBVal
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: AIXML Item Account is present in Item table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemAccountWithDBVal(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{	
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemAN ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/AN";
			Map<String,String> aixmlItemAN= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemAN);
			for(int i=0;i<aixmlItemAN.size();i++){
				String listOfItemANVal = getNodeListValues(aixmlItemAN,"AN").get(i);
				String accountQuery ="select Account from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsItemAccount = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, accountQuery);
				String actualItemAccountVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemAccount,listOfItemANVal);
				System.out.println("AIXML Item Account Number list values returned"+listOfItemANVal);
				System.out.println("AIXML Item Account Number list values returned from sql "+actualItemAccountVal);
				
				returnVal.add(actualItemAccountVal);
				returnVal.add(listOfItemANVal);
			}
			return returnVal;
		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemSCWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item Sortcode is present in Item table
		/*******************************************************************************************************************************************/

		public static void validateRepairedAIXMLItemSCWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemSC ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/SC";
			Map<String,String> aixmlItemSC= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemSC);
			for(int i=0;i<aixmlItemSC.size();i++){
				String listOfItemSCVal = getNodeListValues(aixmlItemSC,"SC").get(i);
				String sortCodeQuery ="select SortCode from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsItemSortCode = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, sortCodeQuery);
				String actualItemSortCode = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemSortCode,listOfItemSCVal);
				System.out.println("AIXML Item SortCode  list values returned"+listOfItemSCVal);
				System.out.println("AIXML Item SortCode  list values returned from sql "+actualItemSortCode);
				
				returnVal.add(actualItemSortCode);
				returnVal.add(listOfItemSCVal);
				boolean scFlag =Component.verifyEquals(actualItemSortCode, listOfItemSCVal, "SortCode value is present in AIXMLItem Table");
				publishResults(scFlag,actualItemSortCode, listOfItemSCVal, "SortCode value is present in AIXMLItem Table");
			}
			//return returnVal;	
		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemSERWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item Serial is present in Item table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemSERWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemSER ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/SER";
			Map<String,String> aixmlItemSER= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemSER);
			for(int i=0;i<aixmlItemSER.size();i++){
				String listOfItemSERVal = getNodeListValues(aixmlItemSER,"SER").get(i);
				String serialQuery ="select Serial from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsItemSER = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, serialQuery);
				String actualItemSERVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemSER,listOfItemSERVal);
				System.out.println("AIXML Item SERIAL Number Number list values returned"+listOfItemSERVal);
				System.out.println("AIXML Item SERIAL Number Number list values returned from sql "+actualItemSERVal);
						
				returnVal.add(actualItemSERVal);
				returnVal.add(listOfItemSERVal);
			}
			return returnVal;
		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemTrancodeWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item Trancode is present in Item table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemTrancodeWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemTC ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/TC";
			Map<String,String> aixmlItemTC= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemTC);
			for(int i=0;i<aixmlItemTC.size();i++){
				String listOfItemTCVal = getNodeListValues(aixmlItemTC,"TC").get(i);
				String trancodeQuery ="select Trancode from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsItemTrancode = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, trancodeQuery);
				String actualItemTrancodeVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemTrancode,listOfItemTCVal);
				System.out.println("AIXML Item TC list values returned"+listOfItemTCVal);
				System.out.println("AIXML Item Trancode list values returned from sql "+actualItemTrancodeVal);
				
				returnVal.add(actualItemTrancodeVal);
				returnVal.add(listOfItemTCVal);
			}
			return returnVal;
		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemIsCorrectedWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item IsCorrect is present in Item table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemIsCorrectedWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLIsCorrectedTag = "//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/IsCorrected";
			Map<String,String> aixmlItemIsCorrected= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLIsCorrectedTag);
			for(int i=0;i<aixmlItemIsCorrected.size();i++){
				String listOfIsCorrectedVal = getNodeListValues(aixmlItemIsCorrected,"IsCorrected").get(i);
				String isCorrectedQuery ="select IsCorrected from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsItemisCorrected = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, isCorrectedQuery);
				String actualItemIsCorrectedVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemisCorrected,listOfIsCorrectedVal);		
				System.out.println("AIXML Item isCorrected list values returned"+listOfIsCorrectedVal);
				System.out.println("AIXML Item isCorrected list values returned from sql "+actualItemIsCorrectedVal);
				
				returnVal.add(actualItemIsCorrectedVal);
				returnVal.add(listOfIsCorrectedVal);
			}
			return returnVal;	

		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemIsAmtCorrectedWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item IsAmount is present in ItemHistory table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemIsAmtCorrectedWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLIsAmountCorrectedTag ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/IsAmountCorrected";
			Map<String,String> aixmlItemIsAmountCorrected= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLIsAmountCorrectedTag);
			for(int i=0;i<aixmlItemIsAmountCorrected.size();i++){
				String listOfIsAmtCorrectedVal = getNodeListValues(aixmlItemIsAmountCorrected,"IsAmountCorrected").get(i);
				String isAmountCorrectedQuery ="select IsAmountCorrected from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsItemisAmountCorrected = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, isAmountCorrectedQuery);
				String actualItemisAmountCorrectedVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemisAmountCorrected,listOfIsAmtCorrectedVal);
				System.out.println("AIXML Item isAmountCorrected list values returned"+listOfIsAmtCorrectedVal);
				System.out.println("AIXML Item isAmountCorrected  list values returned from sql "+actualItemisAmountCorrectedVal);
			
				returnVal.add(actualItemisAmountCorrectedVal);
				returnVal.add(listOfIsAmtCorrectedVal);
			}
			return returnVal;	
		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemIsTCCorrectedWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item IsCorrected is present in ItemHistory table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemIsTCCorrectedWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLIsTCCorrectedTag ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/IsTCCorrected";
			Map<String,String> aixmlIsTCCorrected= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLIsTCCorrectedTag);
			for(int i=0;i<aixmlIsTCCorrected.size();i++){
				String listOfIsTCCorrectedVal = getNodeListValues(aixmlIsTCCorrected,"IsTCCorrected").get(i);
				String isTCCorrectedQuery ="select IsTCCorrected from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsTCCorrected = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, isTCCorrectedQuery);
				String actualTCCorrectedVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsTCCorrected,listOfIsTCCorrectedVal);
				System.out.println("AIXML Item TCCorrected list values returned"+listOfIsTCCorrectedVal);
				System.out.println("AIXML Item TCCorrected list values returned from sql "+actualTCCorrectedVal);
			
				returnVal.add(actualTCCorrectedVal);
				returnVal.add(listOfIsTCCorrectedVal);
			}
			return returnVal;	

		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemIsANCorrectedWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item IsANCorrected is present in Item table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemIsANCorrectedWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLIsANCorrectedTag ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/IsANCorrected";
			Map<String,String> aixmlItemIsANCorrected= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLIsANCorrectedTag);
			for(int i=0;i<aixmlItemIsANCorrected.size();i++){
				String listOfItemIsANCorrectedVal = getNodeListValues(aixmlItemIsANCorrected,"IsANCorrected").get(i);
				String isANCorrectedQuery ="select IsANCorrected from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsisANCorrected = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, isANCorrectedQuery);
				String actualIsANCorrected = GenericMethods.getICSRetrieveMutipleColumnValues(rsisANCorrected,listOfItemIsANCorrectedVal);
				System.out.println("AIXML Item isANCorrected list values returned"+listOfItemIsANCorrectedVal);
				System.out.println("AIXML Item isANCorrected list values returned from sql "+actualIsANCorrected);
				
				returnVal.add(actualIsANCorrected);
				returnVal.add(listOfItemIsANCorrectedVal);
			}
			return returnVal;
		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemIsSCCorrectedWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item IsSCCorrected is present in Item table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemIsSCCorrectedWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLIsSortCodeCorrectedTag ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/IsSortCodeCorrected";
			Map<String,String> aixmlItemIsSCCorrected= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLIsSortCodeCorrectedTag);
			for(int i=0;i<aixmlItemIsSCCorrected.size();i++){
				String listOfItemIsSCCorrectedVal = getNodeListValues(aixmlItemIsSCCorrected,"IsSortCodeCorrected").get(i);
				String isSortcodeCorrectedQuery ="select IsSortcodeCorrected from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsIsSortcodeCorrected = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, isSortcodeCorrectedQuery);
				String actualIsSortcodeCorrectedVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsIsSortcodeCorrected,listOfItemIsSCCorrectedVal);
				System.out.println("AIXML Item isSortcodeCorrected list values returned"+listOfItemIsSCCorrectedVal);
				System.out.println("AIXML Item isSortcodeCorrected list values returned from sql "+actualIsSortcodeCorrectedVal);
				
				returnVal.add(actualIsSortcodeCorrectedVal);
				returnVal.add(listOfItemIsSCCorrectedVal);
			}
			return returnVal;	

		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemIsSERCorrectedWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item IsSERCorrected is present in Item table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemIsSERCorrectedWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLIsSerialCorrectedTag ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/IsSerialCorrected";
			Map<String,String> aixmlItemIsSerialCorrected= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLIsSerialCorrectedTag);
			for(int i=0;i<aixmlItemIsSerialCorrected.size();i++){
				String listOfIsSRCorrectedVal = getNodeListValues(aixmlItemIsSerialCorrected,"IsSerialCorrected").get(i);
				String isSerialCorrectedQuery ="select IsSerialCorrected from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsIsSerialCorrected = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, isSerialCorrectedQuery);
				String actualIsSerialCorrected = GenericMethods.getICSRetrieveMutipleColumnValues(rsIsSerialCorrected,listOfIsSRCorrectedVal);
				System.out.println("AIXML Item isSerialCorrected list values returned"+listOfIsSRCorrectedVal);
				System.out.println("AIXML Item isSerialCorrected list values returned from sql "+actualIsSerialCorrected);
			
				returnVal.add(actualIsSerialCorrected);
				returnVal.add(listOfIsSRCorrectedVal);
			}
			return returnVal;	
		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemAdjustmentReasonCodeWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item Adjustment and ReasonCode is present in Item table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemAdjustmentReasonCodeWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLAdjustmentReasonTag ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/AdjustmentReason";
			Map<String,String> aixmlItemAdjustmentReason = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLAdjustmentReasonTag);
			for(int i=0;i<aixmlItemAdjustmentReason.size();i++){
				String listOfItemAdjustRsnVal = getNodeListValues(aixmlItemAdjustmentReason,"AdjustmentReason").get(i);	
				String adjustmentReasonQuery ="select AdjustmentReason from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
				ResultSet rsAdjustmentReason = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, adjustmentReasonQuery);
				String actualAdjustmentReasonVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsAdjustmentReason,listOfItemAdjustRsnVal);
				System.out.println("AIXML Item Adjustment ReasonCode list values returned"+listOfItemAdjustRsnVal);
				System.out.println("AIXML Item Adjustment ReasonCode list values returned from sql "+actualAdjustmentReasonVal);
				
				returnVal.add(actualAdjustmentReasonVal);
				returnVal.add(listOfItemAdjustRsnVal);
			}
			return returnVal;	

		}

		/*******************************************************************************************************************************************
		/* Method Name: validateRepairedAIXMLItemProcessWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML Item Process is present in Item table
		/*******************************************************************************************************************************************/

		public static ArrayList<String> validateRepairedAIXMLItemProcessWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemProcess ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/Process";
			String processQuery ="select Process from Base.AIXMLItems where BatchID IN ("+batchIDquery+")";
			Map<String,String> aixmlItemProcess = getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemProcess);
			for(int i=0;i<aixmlItemProcess.size();i++){
				String listOfItemProcessVal = getNodeListValues(aixmlItemProcess,"Process").get(i);		
				ResultSet rsProcess = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, processQuery);
				String actualItemProcessVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsProcess,listOfItemProcessVal);
				System.out.println("AIXML Item Process list values returned"+listOfItemProcessVal);
				System.out.println("AIXML Item Process list values returned from sql "+actualItemProcessVal);
				
				returnVal.add(actualItemProcessVal);
				returnVal.add(listOfItemProcessVal);
			}
			return returnVal;

		}

		/*******************************************************************************************************************************************
		/* Method Name: validateItemHistoryAmountWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML ItemHistory Amount is present in ItemHistory table
		/*******************************************************************************************************************************************/

		/*public static ArrayList<String> validateItemHistoryAmountWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String actualItemHistAmountIndex =null;
			String xpathAIXMLItemHistoryAmtNbr ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/Codeline/AM";
			Map<String,String> aixmlItemHistoryAmt= getXMLNodeValueByXPATH(fredAIXMLArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemHistoryAmtNbr);															 
			for(int i=0;i<aixmlItemHistoryAmt.size();i++){
				String litsOfItemHistoryAmtVal = getNodeListValues(aixmlItemHistoryAmt,"AM").get(i);
				String itemHistoryAmtQuery="select AM from Base.AIXMLItemHistory where ItemID IN ("+itemIDQuery;
				System.out.println("ItemHistory Amount Number"+itemHistoryAmtQuery);
				ResultSet rsItemHistoryAmt = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemHistoryAmtQuery);
				String actualItemHistoryAmountVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemHistoryAmt,litsOfItemHistoryAmtVal);
				while(rsItemHistoryAmt.next())
				{
					actualItemHistoryAmountVal  = rsItemHistoryAmt.getString(1);
					int index=actualItemHistoryAmountVal.indexOf(".");
					actualItemHistAmountIndex = actualItemHistoryAmountVal.substring(0, index);
					if(actualItemHistAmountIndex.equals(litsOfItemHistoryAmtVal))
						break;
				}

				System.out.println("AIXML ItemHistory Amount Number list values returned"+litsOfItemHistoryAmtVal);
				System.out.println("AIXML ItemHistory Amount Number list values returned from sql "+actualItemHistAmountIndex);
				setOutputFileData(fredOutputFilePath,sheetName,sheetName,"repairedItemHistoryAmount",actualItemHistAmountIndex);
				returnVal.add(actualItemHistAmountIndex);
				returnVal.add(litsOfItemHistoryAmtVal);
			}
			return returnVal;
		}
	*/
		/*******************************************************************************************************************************************
		/* Method Name: validateItemHistoryTrancodeWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML ItemHistory Trancode is present in ItemHistory table
		/*******************************************************************************************************************************************/

		public static void validateItemHistoryTrancodeWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemHistoryTC ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/Codeline/TC";				
			Map<String,String> aixmlItemHistoryTC= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemHistoryTC);	
			for(int i=0;i<aixmlItemHistoryTC.size();i++){
				String litsOfItemHistoryTC = getNodeListValues(aixmlItemHistoryTC,"TC").get(i);
				System.out.println("ItemID Query in Trancode:"+itemIDQuery);
				String itemHistoryTCQuery="select TC from Base.AIXMLItemHistory where ItemID IN ("+itemIDQuery;
				System.out.println("ItemHistory Transcode Number"+itemHistoryTCQuery);
				ResultSet rsItemHistoryTC = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemHistoryTCQuery);
				String actualItemHistoryTrancode = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemHistoryTC,litsOfItemHistoryTC);
				System.out.println("AIXML ItemHistory TC list values returned"+litsOfItemHistoryTC);
				System.out.println("AIXML ItemHistory Trancode list values returned from sql "+actualItemHistoryTrancode);
				
			//	returnVal.add(actualItemHistoryTrancode);
				//returnVal.add(litsOfItemHistoryTC);
				boolean flag=Component.verifyEquals(actualItemHistoryTrancode, litsOfItemHistoryTC, "ItemHistory Trancode is present in AIXML.ItemHistory Table");
				publishResults(flag,actualItemHistoryTrancode, litsOfItemHistoryTC, "ItemHistory Trancode is present in AIXML.ItemHistory Table");
		
			}
			//return returnVal;
		}

		/*******************************************************************************************************************************************
		/* Method Name: validateItemHistorySortcodeWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML ItemHistory SortCode is present in ItemHistory table
		/*******************************************************************************************************************************************/

		public static void validateItemHistorySortcodeWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemHistorySC ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/Codeline/SC";
			Map<String,String> aixmlItemHistorySC= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemHistorySC);	
			for(int i=0;i<aixmlItemHistorySC.size();i++){
				String litsOfItemHistorySortcode = getNodeListValues(aixmlItemHistorySC,"SC").get(i);
				String itemHistorySCQuery="select SC from Base.AIXMLItemHistory where ItemID IN ("+itemIDQuery;
				System.out.println("ItemHistory SC Number"+itemHistorySCQuery);
				ResultSet rsItemHistorySC = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemHistorySCQuery);
				String actualItemHistorySortcode = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemHistorySC,litsOfItemHistorySortcode);		
				System.out.println("AIXML ItemHistory SC list values returned"+litsOfItemHistorySortcode);
				System.out.println("AIXML ItemHistory SC list values returned from sql "+actualItemHistorySortcode);
				
				//returnVal.add(actualItemHistorySortcode);
				//returnVal.add(litsOfItemHistorySortcode);
				boolean flag=Component.verifyEquals(actualItemHistorySortcode, litsOfItemHistorySortcode, "ItemHistory SortCode is present in AIXML.ItemHistory Table");
				publishResults(flag,actualItemHistorySortcode, litsOfItemHistorySortcode, "ItemHistory SortCode is present in AIXML.ItemHistory Table");
			}
			//return returnVal;
		}

		/*******************************************************************************************************************************************
		/* Method Name: validateItemHistorySerialWithDB
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: TestStep : AIXML ItemHistory Serial is present in ItemHistory table
		/*******************************************************************************************************************************************/

		public static void validateItemHistorySerialWithDB(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String xpathAIXMLItemHistorySER ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/Codeline/SER";
			Map<String,String> aixmlItemHistorySerial= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemHistorySER);															 
			System.out.println("ItemHistory Serial Value unedr CodeLine: "+aixmlItemHistorySerial.size());
			for(int i=0;i<aixmlItemHistorySerial.size();i++){
				String itemHistorySerialQuery="select Serial from Base.AIXMLItemHistory where ItemID IN ("+itemIDQuery;
				System.out.println("ItemHistory Serial Number"+itemHistorySerialQuery);
				ResultSet rsItemHistorySerial = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemHistorySerialQuery);
				String litsOfItemHistorySerialVal = getNodeListValues(aixmlItemHistorySerial,"SER").get(i);
				String actualItemHistorySerialVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemHistorySerial,litsOfItemHistorySerialVal);
				System.out.println("AIXML ItemHistory Serial Number list values returned from AIXML: "+litsOfItemHistorySerialVal);
				System.out.println("AIXML ItemHistory Serial Number list values returned from Database: "+actualItemHistorySerialVal);
				
			//	returnVal.add(actualItemHistorySerialVal);
				//returnVal.add(litsOfItemHistorySerialVal);
				boolean flag=Component.verifyEquals(actualItemHistorySerialVal, litsOfItemHistorySerialVal, "ItemHistory Serial is present in AIXML.ItemHistory Table");
				publishResults(flag,actualItemHistorySerialVal, litsOfItemHistorySerialVal, "ItemHistory Serial is present in AIXML.ItemHistory Table");
		
			}
			System.out.println("Item History Serial returned value: "+returnVal);
			//return returnVal;

		}

		/*******************************************************************************************************************************************
		/* Method Name: setOutputFileData
		/* Author: Nisha Tripathi
		/* Created Date:  16-Mar-2017
		/* Description: Return output in OutputExcel file by passing required parameters
		/*******************************************************************************************************************************************/

		
		public ArrayList<String> updateAIXMLFile(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			
			String xmlVal="100.20";
			String st=null;
			double actualItemHistoryAmountVal=0;
			
			System.out.println("Amount values"+st);
			
			ArrayList<String> returnVal = new ArrayList<>();
			//String actualItemHistAmountIndex =null;
			String xpathAIXMLItemHistoryAmtNbr ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/History/Change/Codeline/AM";
			Map<String,String> aixmlItemHistoryAmt= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLItemHistoryAmtNbr);															 
			for(int i=0;i<aixmlItemHistoryAmt.size();i++){
				double litsOfItemHistoryAmtVal = Double.valueOf(getNodeListValues(aixmlItemHistoryAmt,"AM").get(i));
				String itemHistoryAmtQuery="select AM from Base.AIXMLItemHistory where ItemID IN ("+itemIDQuery;
				System.out.println("ItemHistory Amount Number"+itemHistoryAmtQuery);
				ResultSet rsItemHistoryAmt = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemHistoryAmtQuery);
//				String actualItemHistoryAmountVal = GenericMethods.getICSRetrieveMutipleColumnValues(rsItemHistoryAmt,litsOfItemHistoryAmtVal);
				while(rsItemHistoryAmt.next())
				{
					actualItemHistoryAmountVal  = rsItemHistoryAmt.getDouble(1);
//					int index = actualItemHistoryAmountVal.indexOf(".");
					/*actualItemHistAmountIndex = actualItemHistoryAmountVal.substring(0, index);
					formatVal=Double.parseDouble(actualItemHistoryAmountVal);
					st =String.format("%.2f",new BigDecimal(formatVal));*/
					
					if(actualItemHistoryAmountVal == litsOfItemHistoryAmtVal)
						break;
				}

				System.out.println("AIXML ItemHistory Amount Number list values returned"+litsOfItemHistoryAmtVal);
				System.out.println("AIXML ItemHistory Amount Number list values returned from sql "+st);
			
				returnVal.add(String.valueOf(actualItemHistoryAmountVal));
				returnVal.add(String.valueOf(litsOfItemHistoryAmtVal));
			}
			return returnVal;
			
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
		
		public static void getEntityStateValFromICN(String dbServerName,String fredDBName,String templateFilePath,String icnFilepath,String fredaixmlTmpltFilename,String icnFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			
			String xpathAIXMLNoPaySuspentRsn="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/CustomFields/cf_NoPaySuspectRsn";
			String xpathICNEntitySt ="//Document/ICN/Entities/Entity/EntityState";
			System.out.println("EntityState aixmlArchvFilePath :"+aixmlArchvFilePath+aixmlFileNameCreated);
			Map<String,String> aixmlCFNodeVal= getXMLNodeValueByXPATH(aixmlArchvFilePath,aixmlFileNameCreated,xpathAIXMLNoPaySuspentRsn);															 
			System.out.println("Custom field NoPaySuspect Reason Tag value : "+aixmlCFNodeVal.size());
			icnFileName =getICNOutputFileFromDatabase(dbServerName,fredDBName,templateFilePath,icnFilepath,fredaixmlTmpltFilename,icnFileName,fredAIXMLFilePath,aixmlArchvFilePath);
			icnFileName=icnFileName+".xml";
			for(int i=0;i<aixmlCFNodeVal.size();i++){
				//String itemHistorySerialQuery="select Serial from Base.AIXMLItemHistory where ItemID IN ("+itemIDQuery;
				//System.out.println("ItemHistory Serial Number"+itemHistorySerialQuery);
				//ResultSet rsItemHistorySerial = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, itemHistorySerialQuery);
				String listofcustomfieldsNodeVal = getNodeListValues(aixmlCFNodeVal,"cf_NoPaySuspectRsn").get(i);
				System.out.println("listofcustomfieldsNodeVal  "+listofcustomfieldsNodeVal);
				String listofentityState=null;
				//if(listofcustomfieldsNodeVal.isEmpty()){
				if(listofcustomfieldsNodeVal==null){
					listofcustomfieldsNodeVal=null;
					System.out.println("Entity state value if listofcustomfieldsNodeVal is null "+listofcustomfieldsNodeVal);
					publishResults((listofcustomfieldsNodeVal==null), (listofcustomfieldsNodeVal==null)?"EntityState value is:510":"Issue found for !!"+listofcustomfieldsNodeVal+"value is :510", "EntityState value is:510", "EntityState value for empty cf_NoPaySuspectRsn");
				}
				else{
					Map<String,String> icnEntityState= getXMLNodeValueByXPATH(icnFilepath,icnFileName,xpathICNEntitySt);	
					System.out.println(icnFilepath+icnFileName+xpathICNEntitySt);
					System.out.println("ICN EntityState value is : "+icnEntityState);
					listofentityState = getNodeListValues(icnEntityState,"EntityState").get(i);
					System.out.println("listofentityState  "+listofentityState);
					publishResults((listofcustomfieldsNodeVal!=null), (listofcustomfieldsNodeVal!=null)?"EntityState value for cf_NoPaySuspectRsn "+listofcustomfieldsNodeVal+" is:"+listofentityState:"EntityState value for cf_NoPaySuspectRsn "+listofcustomfieldsNodeVal+"value is :510", "EntityState value for cf_NoPaySuspectRsn "+listofcustomfieldsNodeVal+" is:"+listofentityState, "EntityState value for cf_NoPaySuspectRsn");
				}
			}
			}
	
	
		public static void validateAIXMLUniqueFileNameInDatabase(String templateAIXMLFileName,String templateFilePath,String aixmlRejectFldrfilePath,
				String aixmlArchieveFldrfilePath,String strAIXMLFilePath,String dbServerName,String fredDBName) throws Exception{
			
			String aixmlFileNameCreatedUnq = "DI_MO_92548214110214_Actual.xml";
				System.out.println("Copy file to destination completed");	
					
					String archvfilename = aixmlArchieveFldrfilePath+aixmlFileNameCreatedUnq;
					String rjctfilename = aixmlRejectFldrfilePath+aixmlFileNameCreatedUnq;
					String inputFileName = templateFilePath+aixmlFileNameCreatedUnq;
					String aixmlUniqueFileName =aixmlFileNameCreatedUnq;
					
							
				/*	String archvfilename = aixmlArchieveFldrfilePath+templateAIXMLFileName;
					String rjctfilename = aixmlRejectFldrfilePath+templateAIXMLFileName;
					String inputFileName = templateFilePath+templateAIXMLFileName;
		*/
					File srcFile = new File(inputFileName);
					System.out.println("Template Input File SOURCE Path :"+srcFile);
					File destFile = new File(aixmlArchieveFldrfilePath);
					File rejectDestFile = new File(aixmlRejectFldrfilePath);

					File arcvfolder = new File(aixmlArchieveFldrfilePath);
					File[] listofFilesInArchv = arcvfolder.listFiles();
					boolean checkInutputFile = new File(inputFileName).exists();
				
					File rjctfolder = new File(aixmlRejectFldrfilePath);
					File[] listofFileInRejctFldr = rjctfolder.listFiles();
				    //boolean checkRjctFile = new File(rjctfilename).exists();
				
			       //boolean checkArchieveFile = new File(rjctfilename).exists();
					Boolean checkStatus;
					for(int i=0;i<listofFilesInArchv.length;i++){ 
					//	System.out.println("File present in Archiev fldr :"+listofFilesInArchv[i].getName());
						if(listofFilesInArchv[i].getName().equals(aixmlUniqueFileName))
						{
							FileUtils.moveFileToDirectory(srcFile,rejectDestFile,true);
							System.out.println("No. of File(s) present in Archiev fldr :"+listofFilesInArchv.length);
							System.out.println("File present in Reject fldr :"+listofFileInRejctFldr[i].getName());
							if(listofFileInRejctFldr[i].getName().equals(rjctfilename))
							{
								boolean checkRjctFile = new File(rjctfilename).exists();
								System.out.println("File moved to reject folder :"+checkRjctFile);
								System.out.println("File present in Reject fldr :"+listofFileInRejctFldr[i].getName());
							}
						}
						else
						{
							//copies file to AIXML path
							
							checkStatus  = GenericMethods.CopyFileToDestinationPath(aixmlFileNameCreatedUnq,templateFilePath,strAIXMLFilePath);
							Thread.sleep(3000);
							Component.assertTrue(checkStatus, "Verify file was consumed by the system? - step Completed");
							System.out.println("AIXML file processed to AIXML Folder:"+aixmlArchieveFldrfilePath);
							System.out.println("AIXML file processed to AIXML File:"+archvfilename);
							boolean result = new File(aixmlArchieveFldrfilePath).exists();
							System.out.println("File exists in Archive Folder: "+result);
							
						}
					}
								
				}
		
		 public static void validateISOItemId(String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempFileName,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
			 
			 //validate itemid in icn if deleted item flag true
			 
			 //validate itemid in iso if deleted item flag true
			 boolean flag =ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileMovedToArchieveFldr(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
				if(flag){
					try{
						
						String startAIXMLItemIdTag="<cf_ICSItemID>";
						String endTagAIXMLItemIdTag="</cf_ICSItemID>";
						 String srtIsDeletedtag ="<IsDeleted>";
						 String endIsDeletedtag ="</IsDeleted>";
						 String startItemIdTag="<ItemId>";
							String endItemIdTag="</ItemId>";
							String itemIdAIXMLVal,itemIdVal,crItemIdVal ;
							String startISOItemIdTag="<CdtItmId>";
							String endTagISOItemIdTag="</CdtItmId>";
							
							ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileLoadedIntoDabatase(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
							String icnOutputFile = ValidateAIXMLExtractFileLoadedIntoDatabase.getICNOutputFileFromDatabase(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
							System.out.println("ICNOutputFilename :"+strICNFilePath+icnOutputFile);
							String deletedAIXMLVal = ReadSQLFileUtil.getSQLFileTagValues(fredAIXMLTempFolderPath,aixmlFileNameCreated,srtIsDeletedtag,endIsDeletedtag);
							System.out.println("deletedAIXMLVal "+deletedAIXMLVal);
							if(!(deletedAIXMLVal.equals("false"))){//.equals("true") && deletedAIXMLVal.equals("0") ){
								 itemIdAIXMLVal = ReadSQLFileUtil.getSQLFileTagValues(fredAIXMLTempFolderPath,aixmlFileNameCreated,startAIXMLItemIdTag,endTagAIXMLItemIdTag);
								 itemIdVal = ReadSQLFileUtil.getSQLFileTagValues(strICNFilePath,icnOutputFile+".xml",startItemIdTag,endItemIdTag);
									System.out.println("ICNOutputFilename ItemId Value under ICN tag:"+itemIdVal);
									validationStepInformation("ICNOutputFilename ItemId Value under ICN tag:"+itemIdVal);
									 crItemIdVal = ReadSQLFileUtil.getSQLFileTagValues(strICNFilePath,icnOutputFile+".xml",startISOItemIdTag,endTagISOItemIdTag);
									 System.out.println("ICNOutputFile  ItemId Value under ISO tag :"+crItemIdVal);
									 validationStepInformation("ICNOutputFilename ItemId Value under ICN tag:"+itemIdVal);
							          if(itemIdVal.equals(crItemIdVal)){
							        	  System.out.println("Issue found!!");
							        	  validationStepInformation("ItemId validation in MSG01 and ICN message");
							        	  publishResults(false, crItemIdVal, itemIdVal, "ItemId is present in MSG01+ICN message file");
							          }
							          else
							          {
							        	  System.out.println(" Item Id is not present in MSG01 message file");
							        	  validationStepInformation("ItemId validation in MSG01 and ICN message");
							        	  publishResults(true, itemIdVal, itemIdVal, "Item Id is not present in MSG01 message file");
							          }
							}
							else
							{
								System.out.println("No need to validate icn");
								publishResults(true, srtIsDeletedtag, srtIsDeletedtag, "IsDeleted tag should be set to true....");
							}
							
							 
							
					}
					catch(Exception e){
							System.out.println(e.getMessage());
							validationErrorInformation(e.getMessage());
						}
					}
				else 
				{
					System.out.println("File not moved into archive");
					//check errror log
					ValidateAIXMLExtractFileLoadedIntoDatabase.verifyErrorLog06MF01(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
					
				}
		 }
		
		public static void validateAIXMLValuesWithDB(String dbServerName,String fredDatabaseName,String templateFilePath,String strICNFilePath,String aixmlTempFileName,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey){
			
			ArrayList<String> returnActExpctdVal=new ArrayList<>();
			boolean returnExpectedReslt;
			try{
			
			validateAIXMLFileMovedToArchieveFldr(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			validateAIXMLFileLoadedIntoDabatase(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			validateAIXMLJobBusinessDateValWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			validateAIXMLJobWorkTypeDetailsWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			validateAIXMLJobCaptureSystemIdWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			//validateAIXMLJobCollectionStartTimeWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			validateAIXMLBlockNumberDetailsWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			validateAIXMLBlockImageTypeDetailWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			returnActExpctdVal=validateAIXMLBatchDetailsWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			returnExpectedReslt = Component.verifyEquals(returnActExpctdVal.get(0), returnActExpctdVal.get(1), "BatchDetails of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, returnActExpctdVal.get(0), returnActExpctdVal.get(1), "BatchDetails of the aixml file is loaded into database.");

			validateAIXMLItemDetailsWithDb(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			returnActExpctdVal=validateAIXMLCustomFieldsDetailsWithDB(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			returnExpectedReslt = Component.verifyEquals(returnActExpctdVal.get(0), returnActExpctdVal.get(1), "ItemID fields of the aixml file is not loaded into database.");
			publishResults(returnExpectedReslt, returnActExpctdVal.get(0), returnActExpctdVal.get(1), "ItemID of the aixml file is loaded into database.");

			getICNOutputFileFromDatabase(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			String returnExpctdVal=null;
			returnExpctdVal=getICNOutputFileFromDatabase(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			publishResults(Integer.valueOf(returnExpctdVal).equals(1), (Integer.valueOf(returnExpctdVal).equals(1))?"One ICNOutput File is Present":"More Than one file is present", "ICNOutput File Got Generated in FRED database", "ICNOutput File Count Verifying");
			
			System.out.println("ICNOutput FileName ="+strICNXMLFileName);
			String icnEntityState =getEntityStateICNOutputFileFromDatabase(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			publishResults(icnEntityState!=null,(icnEntityState!=null)?"Entity state value is present":"Entity state is null","Entity state value is present","Verifying entity state");
			
			validateAIXMLItemHistoryDetailsWithDB(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			validateRepairedAIXMLItemAmountWithDBVal(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			validateItemHistoryTrancodeWithDB(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			validateItemHistorySortcodeWithDB(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			validateItemHistorySerialWithDB(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			
			verifyErrorLog06MF01(dbServerName,fredDatabaseName,templateFilePath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			/*if(!(returnActExpctdVal.size()==0))
			{
				returnExpectedReslt = Component.verifyEquals(returnActExpctdVal.get(0), returnActExpctdVal.get(1), "ErrorLog is not present in database.");
				publishResults(returnExpectedReslt, returnActExpctdVal.get(0), returnActExpctdVal.get(1), "ErrorLog is present for aixml file loaded into database");
			}
			else 
				System.out.println("No error found in ErrorLog table");		
			 */
			}
			
		catch(Exception e)
		{
			//publish result with null pointer exception
			System.out.println(e.getMessage());
		}
		finally {
			//publish result with expected method pass or fail value
		}


		}


}


