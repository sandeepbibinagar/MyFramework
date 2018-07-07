package com.ics.fred.testScenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.seleniumCoreUtilis.Component;

public class ExcelReaderSampleFile1 extends ICSDBUtilis{

	List<String> listOfMandatory = new ArrayList<String>();
	String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
	String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\";
	String ICNFileName="ICNOutput.xml";
	
	//XPATH Declarations
	String icnCoreBusinessDate ="//Core/BusinessDate";
	String icnExtractId ="//Core/ExtractId";
	String icnCorePostingExtractIdXPATH="//Core/PostingExtractId";
	String icnCoreProcessingParticipantIdXPATH="//Core/ProcessingParticipantId";
	String icnCoreExtMessageTypeXPATH="//Core/ExtMessageType";
	String icnCoreIntMessageTypeXPATH="//Core/IntMessageType";
	String icnCoreMessageSourceXPATH="//Core/MessageSource";
	String icnCoreMessageDestinationXPATH="//Core/MessageDestination";
	String icnCoreRecordCountsXPATH="//Core/RecordCounts";
	
	
	 String businessDateTagName = "BusinessDate";
	 String extractIdTagName = "ExtractId";
	String intMessageTypeTagName = "IntMessageType";
	String processingParticipantIdTagName = "ProcessingParticipantId";
	 String extMessageTypeTagName = "ExtMessageType";
	String messageSourceTagName = "MessageSource";	
	 String recordCountsTagName="RecordCounts";
	 String messageDestinationTagName="MessageDestination";
 String xsdFileCoreNodeTagName="Core";
	
 //
 

	
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
		
		List<Tags1> listOfTag = new ArrayList<>();
		
		while(iterator.hasNext()){
			Tags1 tag = new Tags1();
			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			int rowIndex = nextRow.getRowNum();
			int count=1;
			int i=0;
			if(rowIndex>0){
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					System.out.println("cell :"+cell);
					switch(count){
					case 1:
						//listTag.add(cell.getRichStringCellValue.toString());
						tag.setCoreChildNode(cell.getRichStringCellValue().toString());
						System.out.println("Tag val :"+cell.getRichStringCellValue().toString());
						readTag(listOfTag,cell,nextRow);
						count++;
						i++;
						break;
					/*case 2:
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
					//	int len = (int)cell.getNumericCellValue();
						//tag.setLength(Integer.toString(len));
						String len = cell.getRichStringCellValue().toString();
						tag.setLength(len);
						
						count++;
						break;*/
					}
					
				}
				listOfTag.add(tag);
				System.out.println("listOfTag :"+listOfTag+"  listOfTag.add(tag)"+listOfTag.add(tag));
			}
		}
		wrkBk.close();
		inputStream.close();
	}
		
		public void readTag(List<Tags1> listOfTag,Cell cell,Row rw) throws Exception{
		String coreChildNode;
		String occurs,type,fixedVar,length;
		int i=0;
		for(Tags1 tags:listOfTag){
			System.out.println("listOfTag :"+listOfTag);
			coreChildNode = tags.getCoreChildNode();
			occurs=tags.getOccurs();
			System.out.println("occurs  :"+occurs);
		
			if(null!=coreChildNode){
				System.out.println("For loop coreChildNode:"+coreChildNode);
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				
			/*	String bdateNodeVal =getNodeListValues(getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreBusinessDate),businessDateTagName).get(0);
				System.out.println("bdateNodeVal :"+bdateNodeVal);
				boolean flag =Component.verifyEquals(bdateNodeVal, cell1, "BusinessDate tagname is as per IDSv8");
				System.out.println("Flag :"+flag);
			*/	
				Map<String, String> extractIdName=getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,"//Entities/Entity/EntityId");
				for(int i1=0;i1<extractIdName.size();i1++){
				String extractId =getNodeListValues(extractIdName,"EntityId").get(i1);
				String nodeName =getNodeName(extractIdName);
				System.out.println("nodeName :"+nodeName);
			System.out.println("EntityId :"+extractId);
				System.out.println("extractIdName :"+extractIdName);
				boolean flag1 =Component.verifyEquals(extractIdName, cell, "ExtractId tagname is as per IDSv8");
				System.out.println("Flag Extractid:"+flag1);
				}
				
			}
			if(null!=occurs){
				System.out.println("For loop occurs:"+occurs);
				
			}
			}
		}
			
		
		
		
		public static Map<String,String> getXMLNodeNameByXPATH(String filePath, String fileName, String xPath) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{

			System.out.println("ICN : "+ filePath+fileName);
			Document doc = getParsedXMLData(new File(filePath+fileName));

			XPath xpath = XPathFactory.newInstance().newXPath();
			XPathExpression expr = xpath.compile(xPath);
			Object result = expr.evaluate(doc, XPathConstants.NODESET);
			NodeList nodes = (NodeList) result;
			Map<String, String> nodeValue = new LinkedHashMap<String, String>();
			Map<String, String> nodeName = null;
			String nodeName1=null;

			for (int i=0;i<nodes.getLength();i++){
				System.out.println(nodes.item(i).getTextContent());
				nodeValue.put(nodes.item(i).getNodeName(), nodes.item(i).getTextContent());
				// nodeName =nodes.item(i).getNodeName();
			//nodeName = nodeValue.entrySet().iterator().next().getKey();
				
			}
			return nodeValue;
		}
			
			
		public static String getNodeName(Map<String, String> nodeValue){
			return nodeValue.entrySet().iterator().next().getKey();
		}
	}
		
		
		

