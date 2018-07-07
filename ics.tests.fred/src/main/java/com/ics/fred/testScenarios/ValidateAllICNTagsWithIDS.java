package com.ics.fred.testScenarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;

public class ValidateAllICNTagsWithIDS extends ICSDBUtilis{

	
	public static List<String> getTags(String excelFilePath) throws Exception{
		
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
		Workbook wrkBk = new XSSFWorkbook(inputStream);
		int index = wrkBk.getSheetIndex("TagsICN05MF01History");
		
		
		Sheet fsheet = wrkBk.getSheetAt(index);
		Iterator<Row> iterator = fsheet.iterator();
		List<String> listTag = new ArrayList<String>();
		String a = null;
		
		while(iterator.hasNext()){
			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			int rowIndex = nextRow.getRowNum();
			System.out.println("rowIndex :"+rowIndex);
			//int count=1;
				if(rowIndex>=0){
					while(cellIterator.hasNext()){
						Cell cell = cellIterator.next();
						listTag.add(cell.getStringCellValue());
					}
				}
		}
		wrkBk.close();
		inputStream.close();
		return listTag;
	}
	
public static List<String> getTagsNoHistory(String excelFilePath) throws Exception{
		
		FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
		
		Workbook wrkBk = new XSSFWorkbook(inputStream);
		int index = wrkBk.getSheetIndex("TagsICN05MF01NOHistory");
		
		
		Sheet fsheet = wrkBk.getSheetAt(index);
		Iterator<Row> iterator = fsheet.iterator();
		List<String> listTag = new ArrayList<String>();
		String a = null;
		
		while(iterator.hasNext()){
			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			int rowIndex = nextRow.getRowNum();
			System.out.println("rowIndex :"+rowIndex);
			//int count=1;
				if(rowIndex>=0){
					while(cellIterator.hasNext()){
						Cell cell = cellIterator.next();
						listTag.add(cell.getStringCellValue());
					}
				}
		}
		wrkBk.close();
		inputStream.close();
		return listTag;
	}
	
	
public static List<String> getOutClearingTags(String excelFilePath) throws Exception{
	
	FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
	
	Workbook wrkBk = new XSSFWorkbook(inputStream);
	int index = wrkBk.getSheetIndex("TagsISO01MF01");
	
	
	Sheet fsheet = wrkBk.getSheetAt(index);
	Iterator<Row> iterator = fsheet.iterator();
	List<String> listTag = new ArrayList<String>();
	String a = null;
	
	while(iterator.hasNext()){
		
		Row nextRow = iterator.next();
		Iterator<Cell> cellIterator = nextRow.cellIterator();
		int rowIndex = nextRow.getRowNum();
		System.out.println("rowIndex :"+rowIndex);
		//int count=1;
			if(rowIndex>=0){
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					listTag.add(cell.getStringCellValue());
				}
			}
	}
	wrkBk.close();
	inputStream.close();
	return listTag;
}




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
	
	
	
	public static void validatICNAllTags(String excelFilePath,String icnXMLPath,String icnFileName) throws Exception{//{ main(String[] args)
		String  xmlFilePath =icnXMLPath+icnFileName;
		validationStepInformation("Validate All ICN Tags "+xmlFilePath);
		boolean flag;
		//ValidateAllICNTagsWithIDS valObj =new ValidateAllICNTagsWithIDS();
		List<String> list =getTags(excelFilePath);
		for(String val:list){
			
			flag=getTagValue(xmlFilePath, "<"+val+">", "<"+val+"/>");
			publishResults(flag,val,val,val+" ICN Tagname is as per IDSv8."+flag);
		}
		
	}
		public static void validatICNAllTagsNoHistory(String excelFilePath,String icnFilePath,String icnFileName) throws Exception{//{ main(String[] args)
			String xmlFilePath=icnFilePath+icnFileName;
			validationStepInformation("Validate All ICN Tags NoHistory "+xmlFilePath);
			boolean flag;
			//ValidateAllICNTagsWithIDS valObj =new ValidateAllICNTagsWithIDS();
			List<String> list =getTagsNoHistory(excelFilePath);
			for(String val:list){
				
				flag=getTagValue(xmlFilePath, "<"+val+">", "<"+val+"/>");
				publishResults(flag,val,val,val+" ICN Tagname is as per IDSv8."+flag);
			}
	
	}
	
		public static void validatOutClearingICNAllTags(String excelFilePath,String icnFilePath,String icnFileName) throws Exception{//{ main(String[] args)
				String xmlFilePath=icnFilePath+icnFileName;
				validationStepInformation("Validate All OC ICN Tags "+xmlFilePath);
				boolean flag;
				//ValidateAllICNTagsWithIDS valObj =new ValidateAllICNTagsWithIDS();
				List<String> list =getOutClearingTags(excelFilePath);
				for(String val:list){
					
					flag=getTagValue(xmlFilePath, "<"+val+">", "<"+val+"/>");
					if(flag)
						publishResults(flag,val,val,val+" ICN Tagname is as per IDSv8."+flag);
					else
						publishResults(flag,val+" Tag Not Found ",val,val+" ICN Tagname is as per IDSv8."+flag);
				}
		
		}
		
	/*	public static String getICNOutputFile65862FromDatabase(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			//verify only one output ICN file is generated
			int blockNo  = GenericMethods.generateUniqueNo(4);
			ICNFileName=ICNFileName.concat(String.valueOf(blockNo));
			System.out.println("ICNFileName :"+ICNFileName);
			
			ResultSet ICNOutputCountResultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, "select count(*) from dbo.ICNOutput where FileName ='"+aixmlFileNameCreated+"'");
			String ICNOutputRows = GenericMethods.getICSRetrieveColumnValues(ICNOutputCountResultSet);
			//retrieves ICNOutput cell value
			ResultSet icnOutputResultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, "select ICNOutput from dbo.ICNOutput where FileName ='"+aixmlFileNameCreated+"'");
			String icnOutput = GenericMethods.getICSRetrieveColumnValues(icnOutputResultSet);
			writeDataOfFileStream(getFileStreamWithExtension(ICNFilepath, ICNFileName, ".xml"), icnOutput, false);
			
			String icnOutputFileTC58270 = ICNFilepath+ICNFileName+".xml";
			System.out.println("ICNOutput data saved to a file in location :"+icnOutputFileTC58270);
		//	ICSGenericUtils.setPITOutputTestData(fredOutputFilePath,sheetName,sheetName,"fredICNOutputTC58720",icnOutputFileTC58270);
			return ICNFileName;
		}
		
		public static String getICNOutputFile65863FromDatabase(String dbServerName,String fredDBName,String templateFilePath,String ICNFilepath,String fredaixmlTmpltFilename,String ICNFileName,String fredAIXMLFilePath,String aixmlArchvFilePath) throws Exception{
			//verify only one output ICN file is generated
			int blockNo  = GenericMethods.generateUniqueNo(4);
			ICNFileName=ICNFileName.concat(String.valueOf(blockNo));
			System.out.println("ICNFileName :"+ICNFileName);
			
			ResultSet ICNOutputCountResultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, "select count(*) from dbo.ICNOutput where FileName ='"+aixmlFileNameCreated+"'");
			String ICNOutputRows = GenericMethods.getICSRetrieveColumnValues(ICNOutputCountResultSet);
			//retrieves ICNOutput cell value
			ResultSet icnOutputResultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, "select ICNOutput from dbo.ICNOutput where FileName ='"+aixmlFileNameCreated+"'");
			String icnOutput = GenericMethods.getICSRetrieveColumnValues(icnOutputResultSet);
			writeDataOfFileStream(getFileStreamWithExtension(ICNFilepath, ICNFileName, ".xml"), icnOutput, false);
			
			String icnOutputFileTC58270 = ICNFilepath+ICNFileName+".xml";
			System.out.println("ICNOutput data saved to a file in location :"+icnOutputFileTC58270);
		//	ICSGenericUtils.setPITOutputTestData(fredOutputFilePath,sheetName,sheetName,"fredICNOutputTC58720",icnOutputFileTC58270);
			return ICNOutputRows;
		}
*/
	
}
