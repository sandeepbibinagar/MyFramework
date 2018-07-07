package com.ics.fred.testScenarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateICNCaptureInfoItemNode extends GenericMethodUtilis{

	

	
	public static boolean getTagValue(String xmlFilePath,String startTag,String startTagWithoutValue){
		
		String s=new String();
		StringBuffer sbf = new StringBuffer();
		String tagValue = null;
		boolean tagFound=false;
		
			try{
			FileReader fr=new FileReader(new File(xmlFilePath));
			BufferedReader br = new BufferedReader(fr);
			
			while((s = br.readLine())!= null){
				sbf.append(s);
				}
			br.close();
			String content =sbf.toString();
		
			int indexStartTag=content.indexOf(startTag);
			int startTagWithoutVal=content.indexOf(startTagWithoutValue);
			if(indexStartTag>0 || startTagWithoutVal>0){
			//System.out.println("Tag Value is :"+tagValue);
				tagFound=true;		
			}
			}
		catch(Exception e){
		e.getMessage();
	}
	return tagFound;
	}
	
	
	
	public static void validateCaptureInfoItemsWithNoHistory(String excelFilePath,String icnFilePath,String icnFileName) throws Exception{//{ main(String[] args)
		//String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
		//String xmlFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\ICNOutput.xml";
		//String icnFileName="ICNOutput.xml";
		String xmlFilePath=icnFilePath+icnFileName;
		boolean flag;
		//ValidateAllICNTagsWithIDS valObj =new ValidateAllICNTagsWithIDS();
		
		String startItemsTag = "<Items>";
		String endItemsTag = "</Items>";
		/*List<String> list =getTags(excelFilePath);
		for(String val:list){
			
			flag=getTagValue(xmlFilePath, "<"+val+">", "<"+val+"/>");
			publishResults(flag,val,val,val+" ICN Tagname is as per IDSv8."+flag);
		}*/
		
		flag =getTagValue(xmlFilePath,startItemsTag,endItemsTag);
		if(flag){
			
			validationStepInformation("Validate that Item tag is present in ICN for AIXML file with itemhistory tag.");
			publishResults(flag,"Items","Items","Item tag is present in generated ICN "+flag);	
		}
		else{
			validationStepInformation("Validate that Item tag is not present in ICN for AIXML file without itemhistory tag.");
			publishResults(true,"Item tag is not present in generated ICN","Item tag is not present in generated ICN","Item tag is not present in generated ICN "+flag);	
		}
	
	
	}
	
	
	
	
}
