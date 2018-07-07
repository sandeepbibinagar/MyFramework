package com.ics.fred.testScenarios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import com.ics.externalFactoryUtilis.ICSDBUtilis;

public class ExcelReaderSampleFile extends ICSDBUtilis{

	List<String> listOfMandatory = new ArrayList<String>();
	String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
	String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\";
	String ICNFileName="ICNOutput.xml";
	String icnCoreBusinessDate ="//Core/BusinessDate";
	 String businessDateTagName = "BusinessDate";
	 String extractIdTagName = "ExtractId";
	String intMessageTypeTagName = "IntMessageType";
	String processingParticipantIdTagName = "ProcessingParticipantId";
	 String extMessageTypeTagName = "ExtMessageType";
	String messageSourceTagName = "MessageSource";	
	 String recordCountsTagName="RecordCounts";
	 String messageDestinationTagName="MessageDestination";
 String xsdFileCoreNodeTagName="Core";
	

	
	//private static final String businessDateTagName = null;

	//public static void main(String[] args) throws Exception {
	@Test
	public void excelReader() throws Exception {
		// TODO Auto-generated method stub

		//icnCoreBusinessDate;
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		
		Workbook wrkBk = new XSSFWorkbook(inputStream);
		int index = wrkBk.getSheetIndex("Core");
		System.out.println(index);
		
		Sheet fsheet = wrkBk.getSheetAt(index);
		Iterator<Row> iterator = fsheet.iterator();
		List<String> listTag = new ArrayList<String>();
		
		List<Tags> listOfTag = new ArrayList<>();
		
		while(iterator.hasNext()){
			Tags tag = new Tags();
			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			int rowIndex = nextRow.getRowNum();
			int count=1;
			if(rowIndex>0){
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					switch(count){
					case 1:
						//listTag.add(cell.getRichStringCellValue.toString());
						tag.setCoreChildNode(cell.getRichStringCellValue().toString());
						count++;
						break;
					case 2:
						tag.setOccurs(cell.getRichStringCellValue().toString());
						count++;
						break;
					case 3:
						tag.setFixedVar(cell.getRichStringCellValue().toString());
						count++;
						break;
					case 4:
						tag.setType(cell.getRichStringCellValue().toString());
						count++;
						break;
					case 5:
						int len = (int)cell.getNumericCellValue();
						tag.setLength(Integer.toString(len));
						count++;
						break;
					}
					
				}
				listOfTag.add(tag);
				
			}
		}
		wrkBk.close();
		inputStream.close();
		
		String coreChildNode,occurs,type,fixedVar,length;
		for(Tags tags:listOfTag){
			coreChildNode = tags.getCoreChildNode();
			if(null!=coreChildNode){
				System.out.println(coreChildNode);
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				
			//	icnCoreBusinessDateNodeVal = getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreBusinessDate);
				//System.out.println("icnCoreBusinessDateNodeVal :"+icnCoreBusinessDateNodeVal);
			//	List<String> litsOfNodeBusinessDateAIXML = getNodeListValues(icnCoreBusinessDateNodeVal,"BusinessDate");
		//		System.out.println("ICN Job BusinessDate list values returned: "+litsOfNodeBusinessDateAIXML);
			//	String icnBusinessDateNodeVal = litsOfNodeBusinessDateAIXML.get(0);
			//	System.out.println("ICN Job BusinessDate values returned: "+icnBusinessDateNodeVal);
				
				
				String bdateNodeVal =getNodeListValues(getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreBusinessDate),businessDateTagName).get(0);
				System.out.println("bdateNodeVal :"+bdateNodeVal);
			}
		}
		
	}
		
		
		
}
