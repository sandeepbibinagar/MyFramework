package com.ics.fred.testScenarios;

import java.io.File;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadSQLFileUtil;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;


public class AIXMLFileNameUniqueValidation extends ICSDBUtilis {

	/*fredAIXMLRejectFldrfilePath=\\\\129.227.33.35\\j$\\Rejected\\
			fredAIXMLArchieveFldrfilePath=\\\\129.227.33.35\\j$\\Archive\\
	*/
//	public static void validateAIXMLUniqueFileName(String templateAIXMLFileName,String templateFilePath,String destinationFilePath,String templateAIXMLFileBlockNoString) throws IOException{
	//create unique block number
	//int blockNo  = GenericMethods.generateUniqueNo(3);
	//System.out.println("Block Number: " + blockNo);
	//String uniqueFileName = templateAIXMLFileName.replace("UNIQUENAME", String.valueOf(blockNo));
	//Pass same file twice
   
	//saves file with unique name and updates the block number
	//GenericMethods.createUniqueFileFromTemplate(templateFilePath, templateAIXMLFileName, ".xml", templateAIXMLFileBlockNoString, String.valueOf(blockNo),uniqueFileName);	
//	String fileToBeCopied = uniqueFileName + "_Actual.xml";
		
//	String completeFilePath = templateFilePath+fileToBeCopied;
//	 File srcFile = new File(completeFilePath);
//	 System.out.println("Destination Path :"+srcFile);
//	  File destFile = new File(destinationFilePath);
//	  System.out.println("Destination Path :"+destFile);
	//  System.out.println("Unique File Name "+fileToBeCopied);

//	int countFileLength =fileToBeCopied.length();

//verify samefile has processed to aixml folder
//if duplicate file is processed it shud be moved to rejected folder	
//verify same file is present in archieve if present then shud move that file into reject fldr or donot move

public static void validateAIXMLUniqueFileName(String templateAIXMLFileName,String templateFilePath,String aixmlRejectFldrfilePath,String aixmlArchieveFldrfilePath,
		String businessDateToBeReplace,String configDateValue,String strAIXMLFilePath,String fredServerName,String dbName) throws Exception{

	String xpathAIXMLBlkNbr ="//Jobs/Job/Blocks/Block/BlkNbr";
	String xpathAIXMLJobBusinessDate ="//Jobs/Job/BusinessDate";
	String aixmlFileNameCreated = templateAIXMLFileName;
	Map<String,String> map=new LinkedHashMap<String,String>();
	
	//create unique block number
	int blockNo  = GenericMethods.generateUniqueNo(4);
	System.out.println("Block Number: " + blockNo);		
	String dateVal = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
	aixmlFileNameCreated =templateAIXMLFileName.concat(String.valueOf(blockNo))+dateVal+"_Actual.xml";
	
	String aixmlfile = templateAIXMLFileName+".xml";
	File filetoberenamed = new File(templateFilePath+aixmlfile);
	System.out.println("Fred Template Input path :"+templateFilePath+aixmlFileNameCreated);
	boolean aixmlFileNameCreated1 = filetoberenamed.renameTo(new File(templateFilePath+aixmlFileNameCreated));
	
	System.out.println("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
	
	//create unique block number
			//int blockNo  = GenericMethods.generateUniqueNo(4);
		//	System.out.println("Block Number: " + blockNo);
			//String uniqueFileName = templateAIXMLFileName.replace("UNIQUENAME", String.valueOf(blockNo));
			//String uniqueFileName = templateAIXMLFileName.replace("UNIQUENAME", "3019");
			
			//saves file with unique name and updates the block number
//			GenericMethods.createUniqueFileFromTemplate(templateFilePath, templateAIXMLFileName, ".xml", templateAIXMLFileBlockNumberString, String.valueOf(blockNo),uniqueFileName);
		//	GenericMethods.createUniqueFileFromTemplate(templateFilePath, templateAIXMLFileName, ".xml", templateAIXMLFileBlockNumberString, "3019",uniqueFileName);
		
			
			//update Business Date in the file
			String sqlConfigBusinessDate ="select Value from Config.BusinessConfig where [Key]='BDATE'";
			ResultSet rsConfigBusinessDate = ICSProductDBConnection.getICSDBServerConnection(fredServerName, dbName, sqlConfigBusinessDate);
			String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(rsConfigBusinessDate);
			configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
			
			String aixmlFilePathTobeUpdated=templateFilePath+aixmlFileNameCreated;
			
			map.put(xpathAIXMLJobBusinessDate, configDateValue);
			map.put(xpathAIXMLBlkNbr, String.valueOf(blockNo));
			
			//Search if file already there in aixml input folder
			ReadSQLFileUtil.updateXMLTagVal(aixmlFilePathTobeUpdated,map);
			System.out.println("2 Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
			
			String fileToBeCopiedToArchvFldr = aixmlFileNameCreated;
			System.out.println("Unique File Name to be copied to Archieve Folder: "+fileToBeCopiedToArchvFldr);

			
			//update Business Date in the file
		//	ICSDBUtilis.createFileFromTemplate(filePath, fileName, fileExtension, extractIDStringToBeReplace, extractIDStringToReplace);
			//ICSDBUtilis.createFileFromTemplate(templateFilePath, uniqueFileName + "_Actual", ".xml", businessDateToBeReplace, configDateValue);
			//System.out.println("File updated with Business Date");
			
			
			
			System.out.println("Copy file to destination completed");	
			
			String archvfilename = aixmlArchieveFldrfilePath+aixmlFileNameCreated;
			String rjctfilename = aixmlRejectFldrfilePath+aixmlFileNameCreated;
			String inputFileName = templateFilePath+aixmlFileNameCreated;
			String aixmlUniqueFileName =aixmlFileNameCreated;
			
					
		/*	String archvfilename = aixmlArchieveFldrfilePath+templateAIXMLFileName;
			String rjctfilename = aixmlRejectFldrfilePath+templateAIXMLFileName;
			String inputFileName = templateFilePath+templateAIXMLFileName;
*/
			File srcFile = new File(inputFileName);
			System.out.println("SOURCE Path :"+srcFile);
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
					
					checkStatus  = GenericMethods.CopyFileToDestinationPath(fileToBeCopiedToArchvFldr,templateFilePath,strAIXMLFilePath);
					Thread.sleep(3000);
					Component.assertTrue(checkStatus, "Verify file was consumed by the system? - step Completed");
					System.out.println("AIXML file processed to AIXML Folder:"+aixmlArchieveFldrfilePath);
					System.out.println("AIXML file processed to AIXML File:"+archvfilename);
					boolean result = new File(aixmlArchieveFldrfilePath).exists();
					System.out.println("File exists in Archive Folder: "+result);
					
				}
			}
			/*if(checkInutputFile){
				 System.out.println("File is present in Archieve");
				 //Move that file into RejectFolder
				 FileUtils.moveFileToDirectory(srcFile,rejectDestFile,true);
				 System.out.println("File moved to reject folder :"+checkInutputFile);
				 if(checkRjctFile)
					 System.out.println("Same filename should not be processed ahd hence moved into reject folder"+rjctfilename);
			}
			System.out.println("File has not been processed yet :"+checkInutputFile);
			*/
		
			/*for(int i=0;i<listofFile.length;i++){ 
				if(listofFile[i].getName().equals(filetobecopiedtoarchieve)){
					return Constants.FILE_EXISTS;
				}
			}*/
			
			
			
			
			
		}


public static void validateAIXMLUniqueFileNameInDatabase(String fredaixmlTmpltFilename,String templateFilePath,String aixmlRejectFldrfilePath,
		String aixmlArchieveFldrfilePath,String fredAIXMLFilePath,String dbServerName,String fredDBName,String aixmlFileCopyTempFldrPath) throws Exception{
	
			
	String aixmlFileNameCreated = generateAIXMLFile(fredaixmlTmpltFilename,templateFilePath,dbServerName,fredDBName,aixmlFileCopyTempFldrPath);
	//create unique block number
	//copies file to AIXML path      
	String archv = aixmlArchieveFldrfilePath+aixmlFileNameCreated;
	String rejectFldPath = aixmlRejectFldrfilePath+aixmlFileNameCreated;
	
	String archvDuplicateFileVal = aixmlArchieveFldrfilePath+aixmlFileNameCreated;
	String rejectFldPathDuplicateFileVal = aixmlRejectFldrfilePath+aixmlFileNameCreated;
	
	//Case 1.check duplicate filename present in rejected folder
	for(int i=1;i<=2;i++){
	switch(i){
	case 1:
		Boolean checkStatus  = GenericMethods.CopyFileToDestinationPath(aixmlFileNameCreated,aixmlFileCopyTempFldrPath,fredAIXMLFilePath);
		Thread.sleep(3000);
		System.out.println("Check Status"+checkStatus);
		
			boolean result = new File(archv).exists();
			boolean rejectFlag = new File(rejectFldPath).exists();
			if(result)
			{
				
				System.out.println("File exists in Archive Folder: "+result);
				validationStepInformation("File exists in Archive Folder: "+result);
				publishResults(result, result?"AIXMLExtract File Moved to Archive folder":"AIXMLExtract File not Moved to Archieve", "AIXMLExtract File Moved to Archive folder", "AIXMLExtract File Uniquesness Validation");
			}
			else if(rejectFlag)
			{
				System.out.println("File exists in Reject Folder: "+rejectFlag);
				validationStepInformation("File exists in Reject Folder: "+rejectFlag);
				publishResults(rejectFlag, rejectFlag?"AIXMLExtract File Moved to Reject folder":"AIXMLExtract File not Moved to Reject", "AIXMLExtract File Moved to Reject folder", "AIXMLExtract File Uniquesness Validation");
			}
			else
			{
				System.out.println("Issue Found!!");
				publishResults(rejectFlag, rejectFlag?"Issue Founf!!":"AIXMLExtract File stuck in AIXML folder", "Issue Founf!!", "AIXMLExtract File Uniquesness Validation");
			}
				
			break;
	case 2:
		
		String existingFilePath=aixmlFileCopyTempFldrPath+aixmlFileNameCreated;
		System.out.println(existingFilePath);
		//String updatedExistingFilePath = updateExistingFile(existingFilePath);
		updateExistingFile(dbServerName,fredDBName,existingFilePath);
		//Thread.sleep(100);
		Boolean checkDuplicateFileProcessd  = GenericMethods.CopyFileToDestinationPath(aixmlFileNameCreated,aixmlFileCopyTempFldrPath,fredAIXMLFilePath);
		Thread.sleep(3000);
		System.out.println("Check Duplicate AIXML FileName Processing Status"+checkDuplicateFileProcessd);
		System.out.println("Copy file to destination completed: "+archvDuplicateFileVal);
		validationStepInformation("Copy file to destination completed: "+archvDuplicateFileVal+" ::"+checkDuplicateFileProcessd);
			
			boolean resultDup = new File(archvDuplicateFileVal).exists();
			System.out.println("resultDup :"+resultDup);
			boolean rejectFlagDup = new File(rejectFldPathDuplicateFileVal).exists();
			System.out.println("rejectFlagDup :"+rejectFlagDup);
			//String expectedDupBlkNbrErrMsg =aixmlFileNameCreated+": "+aixmlFileNameCreated+" : This block number in the file is already processed.";
			String expectedDupFileErrMsg =aixmlFileNameCreated+": "+aixmlFileNameCreated+" :This file is already processed.";
			String dupFileErrQuery ="select ErrorMessage from Base.ErrorLog where ErrorMessage='"+expectedDupFileErrMsg+"'";
			System.out.println("dupFileErrLog Query : "+dupFileErrQuery);
			
			if(rejectFlagDup)
			{
				System.out.println("Duplicate File exists in Reject Folder: "+rejectFlagDup);
				validationStepInformation("Duplicate AIXMLFileName"+aixmlFileNameCreated+" exists in Reject Folder: "+rejectFlagDup);
			
				publishResults(resultDup, resultDup?"AIXMLExtract File Moved to Reject folder":"AIXMLExtract File not Moved to Reject", "AIXMLExtract File Moved to Reject folder", "AIXMLExtract File Uniquesness Validation");
				validationStepInformation("Validate Duplicate AIXMLFileName"+aixmlFileNameCreated+" present in ErrorLog Table: ");
				ResultSet rslt = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, dupFileErrQuery);
				String  actualErrMsg = GenericMethods.getICSRetrieveMutipleColumnValues(rslt, expectedDupFileErrMsg);
				System.out.println("Actual ErrorMsg value from db: "+actualErrMsg); 
				publishResults(rejectFlagDup,actualErrMsg,expectedDupFileErrMsg,"Rejected DuplicateFile is loaded into ErrorLog Table");
				
			}
			else if(resultDup)
			{
				System.out.println("Duplicate File exists in Archive Folder: "+resultDup);
				validationStepInformation("Duplicate AIXMLFileName exists in Archive Folder: "+resultDup);
				publishResults(resultDup, resultDup?"AIXMLExtract File Moved to Archive folder":"AIXMLExtract File not Moved to Archieve", "AIXMLExtract File Moved to Archive folder", "AIXMLExtract File Uniquesness Validation");
				
			}
						
			else
			{
				System.out.println("Issue Found!!");
				publishResults(resultDup, resultDup?"Found Issue!!!":"AIXMLExtract File stuck in AIXML folder", "Found Issue!!!", "AIXMLExtract File Uniquesness Validation");
				
			}
			
			break;
	}
	
	}
					
}


public static String generateAIXMLFile(String fredaixmlTmpltFilename,String templateFilePath,String dbServerName,String fredDBName,String aixmlCopyTempFldrPath) throws Exception{
	int blockNo  = GenericMethods.generateUniqueNo(4);
	System.out.println("Block Number: " + blockNo);		
	String dateVal = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
	
	GenericMethods.copyFileFromOneLocationToAnother(templateFilePath,aixmlCopyTempFldrPath,fredaixmlTmpltFilename+".xml");
	
	String aixmlFileNameCreated =fredaixmlTmpltFilename.concat(String.valueOf(blockNo))+dateVal+"_Actual.xml";
	
	String aixmlfile = fredaixmlTmpltFilename+".xml";
	File filetoberenamed = new File(aixmlCopyTempFldrPath+aixmlfile);
	System.out.println("Fred Template input path :"+aixmlCopyTempFldrPath+aixmlFileNameCreated);
	boolean aixmlFileNameCreated1 = filetoberenamed.renameTo(new File(aixmlCopyTempFldrPath+aixmlFileNameCreated));
	
	System.out.println("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
	
	//update Business Date in the file
	
	String aixmlFilePathTobeUpdated=aixmlCopyTempFldrPath+aixmlFileNameCreated;
	updateExistingFile(dbServerName,fredDBName,aixmlFilePathTobeUpdated);
	System.out.println("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
	validationStepInformation("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
	return aixmlFileNameCreated;
}

public static void updateExistingFile(String dbServerName,String fredDBName,String aixmlFilePathtemp) throws Exception{
	String xpathAIXMLJobBusinessDate ="//Jobs/Job/BusinessDate";
	String xpathAIXMLBlkNbr ="//Jobs/Job/Blocks/Block/BlkNbr";
	Map<String,String> map=new LinkedHashMap<String,String>();	
	
	int blockNo  = GenericMethods.generateUniqueNo(4);
	System.out.println("Block Number: " + blockNo);		
	String sqlConfigBusinessDate ="select Value from Config.BusinessConfig where [Key]='BDATE'";
	ResultSet rsConfigBusinessDate = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, sqlConfigBusinessDate);
	String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(rsConfigBusinessDate);
	String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
	map.put(xpathAIXMLJobBusinessDate, configDateValue);
	map.put(xpathAIXMLBlkNbr, String.valueOf(blockNo));
	
	ReadSQLFileUtil.updateXMLTagVal(aixmlFilePathtemp,map);
	//return aixmlFilePathTobeUpdated;
}


}
	
	


