package com.ics.fred.testScenarios;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.ics.fred.common.GenericMethods;

public class AIXMLFileNameLengthValidation {

	
	public static void validateAIXMLFileNameLength(String templateAIXMLFileName,String templateFilePath,String destinationFilePath,String templateAIXMLFileBlockNoString) throws IOException{
	//create unique block number
		
		
	int blockNo  = GenericMethods.generateUniqueNo(3);
	System.out.println("Block Number: " + blockNo);
	String uniqueFileName = templateAIXMLFileName.replace("UNIQUENAME", String.valueOf(blockNo));
	
	//saves file with unique name and updates the block number
	GenericMethods.createUniqueFileFromTemplate(templateFilePath, templateAIXMLFileName, ".xml", templateAIXMLFileBlockNoString, String.valueOf(blockNo),uniqueFileName);	
	String fileToBeCopied = uniqueFileName + "_Actual.xml";
	String completeFilePath = templateFilePath+fileToBeCopied;
	 File srcFile = new File(completeFilePath);
	 System.out.println("Destination Path :"+srcFile);
	  File destFile = new File(destinationFilePath);
	  System.out.println("Destination Path :"+destFile);
	  System.out.println("Unique File Name "+fileToBeCopied);
	
	int countFileLength =fileToBeCopied.length();
	if(countFileLength<=20)
		{
		System.out.println("File length is :"+countFileLength);
		}
		else
		{
			//File should move to another folder if FileName length is greater than 100
			System.out.println("File length is greater than 100 character :"+countFileLength);
			FileUtils.moveFileToDirectory(srcFile, destFile,true);
			System.out.println("File has been moved to Reject Folder"+completeFilePath);
		}
	}
	
	

}
