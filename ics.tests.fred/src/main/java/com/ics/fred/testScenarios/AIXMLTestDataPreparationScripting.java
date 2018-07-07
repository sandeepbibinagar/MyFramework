package com.ics.fred.testScenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadSQLFileUtil;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;


public class AIXMLTestDataPreparationScripting extends ICSDBUtilis {

	//read excel file 
	
		public static Map<String,String> readExcelAIXML(String filepath,String fredaixmlTmpltFilename,String templateFilePath,String dbServerName,String fredDBName,String aixmlCopyTempFldrPath) throws Exception{
		FileInputStream inputStream = new FileInputStream(filepath);
		
		Workbook wrkBk = new XSSFWorkbook(inputStream);
		int index = wrkBk.getSheetIndex("AIXMLTestDataSingleItem");
		
		Map<String,String> xpathAndNewValueMap = new HashMap<>();
		Sheet fsheet = wrkBk.getSheetAt(index);
		Iterator<Row> iterator = fsheet.iterator();
		List<String> listTag = new ArrayList<String>();
		String a = null;
		int rowIndex=0;
		 String s =generateAIXMLFile(fredaixmlTmpltFilename,templateFilePath,dbServerName,fredDBName,aixmlCopyTempFldrPath);
		while(iterator.hasNext()){
			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			
			String xpath = null;
			String newValue = null;
			 rowIndex = nextRow.getRowNum();
			
			System.out.println("rowIndex :"+rowIndex);
			int count=1;
			if(rowIndex>0){
				
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				
					
					switch(cell.getCellType()){
					case Cell.CELL_TYPE_STRING :
						if(count%2!=0){
							xpath=cell.getStringCellValue();
							System.out.println("cell CELL_TYPE_STRING xpath "+xpath);
						}
						else{
							newValue =cell.getStringCellValue();
							System.out.println("cell CELL_TYPE_STRING newValue "+newValue);
						}
						System.out.println("cell CELL_TYPE_STRING xpath newValue value"+cell.getStringCellValue());
						//update newvalue in xml
						
						count++;
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if(count%2!=0){
							xpath=String.valueOf(cell.getNumericCellValue());
							System.out.println("cell CELL_TYPE_NUMERIC xpath "+xpath);
						}
						else{
							newValue =String.valueOf(cell.getNumericCellValue());
							System.out.println("cell CELL_TYPE_NUMERIC newValue "+newValue);
						}
						System.out.println("cell CELL_TYPE_STRING xpath newValue value"+cell.getNumericCellValue());
						count++;
						break;
					}
					xpathAndNewValueMap.put(xpath, newValue);
					System.out.println("cell index value"+xpathAndNewValueMap.put(xpath, newValue));
					System.out.println("cell index value"+xpathAndNewValueMap.get(rowIndex));
					
			}
			}
			}
	
			ReadSQLFileUtil.updateXMLTagVal(s,xpathAndNewValueMap);		
			updateExistingFile(xpathAndNewValueMap,dbServerName,fredDBName,s,aixmlCopyTempFldrPath);
		return xpathAndNewValueMap;
	}


	public static String generateAIXMLFile(String fredaixmlTmpltFilename,String templateFilePath,String dbServerName,String fredDBName,String aixmlCopyTempFldrPath) throws Exception{
		int blockNo  = GenericMethods.generateUniqueNo(4);
		//System.out.println("Block Number: " + blockNo);		
		String dateVal = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		//Random No. generation
		Random randomObj = new Random();
		int high=111;
		int low=10;
		int rndm = randomObj.nextInt(high-low)+low;
		System.out.println("rndm "+rndm);
		int rndmNbr = blockNo+rndm;

		GenericMethods.copyFileFromOneLocationToAnother(templateFilePath,aixmlCopyTempFldrPath,fredaixmlTmpltFilename+".xml");
		
		String aixmlFileNameCreated =fredaixmlTmpltFilename.concat(String.valueOf(rndm))+dateVal+"_Actual.xml";
		
		String aixmlfile = fredaixmlTmpltFilename+".xml";
		File filetoberenamed = new File(aixmlCopyTempFldrPath+aixmlfile);
		//System.out.println("Fred Template input path :"+aixmlCopyTempFldrPath+aixmlFileNameCreated);
		boolean aixmlFileNameCreated1 = filetoberenamed.renameTo(new File(aixmlCopyTempFldrPath+aixmlFileNameCreated));
		
		System.out.println("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
		
		//update Business Date in the file
		
		String aixmlFilePathTobeUpdated=aixmlCopyTempFldrPath+aixmlFileNameCreated;

		System.out.println("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
		validationStepInformation("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
	
		//return aixmlFileNameCreated;
		return aixmlFilePathTobeUpdated;
	}

	public static void updateExistingFile(Map<String,String> mapV,String dbServerName,String fredDBName,String aixmlFilePathtemp,String aixmlCopyTempFldrPath) throws Exception{
		String xpathAIXMLJobBusinessDate ="//Jobs/Job/BusinessDate";
		String xpathAIXMLBlkNbr ="//Jobs/Job/Blocks/Block/BlkNbr";
		String xpathAIXMLCustomFieldsItemID ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/CustomFields/cf_ICSItemID";
		Map<String,String> map=new LinkedHashMap<String,String>();	
		int blockNo  = GenericMethods.generateUniqueNo(4);
		String sqlConfigBusinessDate ="select Value from Config.BusinessConfig where [Key]='BDATE'";
		ResultSet rsConfigBusinessDate = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, sqlConfigBusinessDate);
		String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(rsConfigBusinessDate);
		String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
		map.put(xpathAIXMLJobBusinessDate, configDateValue);
		map.put(xpathAIXMLBlkNbr, String.valueOf(blockNo));
	
		ReadSQLFileUtil.updateXMLTagVal(aixmlFilePathtemp,map);
		
	}
	
	
	

}