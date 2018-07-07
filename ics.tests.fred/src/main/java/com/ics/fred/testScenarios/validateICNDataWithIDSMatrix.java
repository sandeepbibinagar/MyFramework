package com.ics.fred.testScenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ics.fred.common.FREDXMLValidation;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class validateICNDataWithIDSMatrix extends GenericMethodUtilis{

	String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\";
	String ICNFileName="ICNOutput.xml";
	String icnCoreBusinessDate ="//Core/BusinessDate";
	Map<String, String> icnCoreBusinessDateNodeVal,icnCoreExtractId,icnCorePostingExtractId,icnCoreProcessingParticipantId,icnCoreExtMessageType,icnCoreIntMessageType;
	Map<String, String> icnCoreMessageSource,icnCoreMessageDestination,icnCoreRecordCounts;
	
	//XPATH Declarations
	String icnCoreExtractIdXPATH="//Core/ExtractId";
	String icnCorePostingExtractIdXPATH="//Core/PostingExtractId";
	String icnCoreProcessingParticipantIdXPATH="//Core/ProcessingParticipantId";
	String icnCoreExtMessageTypeXPATH="//Core/ExtMessageType";
	String icnCoreIntMessageTypeXPATH="//Core/IntMessageType";
	String icnCoreMessageSourceXPATH="//Core/MessageSource";
	String icnCoreMessageDestinationXPATH="//Core/MessageDestination";
	String icnCoreRecordCountsXPATH="//Core/RecordCounts";
	
	//Read excel IDS file
	@Test
	public void validateIDSFormat() throws Exception{
	String excelFile= "\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
	InputStream excelFileTobeRead = new FileInputStream(excelFile);
	XSSFWorkbook wb = new XSSFWorkbook(excelFileTobeRead);
	XSSFWorkbook test =new XSSFWorkbook();
	XSSFSheet sheet = wb.getSheetAt(1);
	XSSFRow row;
	XSSFCell cell;
	Iterator rows = sheet.rowIterator();
	while(rows.hasNext())
	{
		row=(XSSFRow)rows.next();
		Iterator cells =row.cellIterator();
		while(cells.hasNext())
		{
			cell=(XSSFCell)cells.next();
			//Iterator cells =((XSSFRow) rows).cellIterator();
			while(cells.hasNext())
			{
				cell=(XSSFCell) cells.next();
				if(cell.getCellType()==XSSFCell.CELL_TYPE_STRING)
				{
					System.out.println(cell.getStringCellValue()+" ");
					icnCoreBusinessDateNodeVal = getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreBusinessDate);
					System.out.println("icnCoreBusinessDateNodeVal :"+icnCoreBusinessDateNodeVal);
					List<String> litsOfNodeBusinessDateAIXML = getNodeListValues(icnCoreBusinessDateNodeVal,"BusinessDate");
					System.out.println("ICN Job BusinessDate list values returned: "+litsOfNodeBusinessDateAIXML);
					String icnBusinessDateNodeVal = litsOfNodeBusinessDateAIXML.get(0);
					System.out.println("ICN Job BusinessDate values returned: "+icnBusinessDateNodeVal);
					boolean flag =FREDXMLValidation.validateBusinessDateDatabaseFormat(icnBusinessDateNodeVal);
					if(flag)
						System.out.println("BusinessDate datatype and format validation done :");
					else
						System.out.println("BusinessDate format is not as per IDS version 8 ");
					
				//	publishResults(flag,icnBusinessDateNodeVal,cell.getStringCellValue(),"Validation of BusinessDate tagName as per IDS version 8.0 ");
					
					//ExtractID Lenght Validation 
										
					icnCoreExtractId = getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreExtractIdXPATH);
					List<String> extractIdNodeVallist = getNodeListValues(icnCoreExtractId,"ExtractId");
					String extractId =extractIdNodeVallist.get(0);
					boolean flagExtractId =FREDXMLValidation.validateExtractId(extractId);
					if(flagExtractId)
						System.out.println("ExtractID datatype and length validation done successfully");
					else
						System.out.println("ExtractID datatype and lenght validation is not as per IDS version 8 ");
					
					//PostingExtractId
					icnCorePostingExtractId = getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCorePostingExtractIdXPATH);
					List<String> icnCorePostingExtractIdlist = getNodeListValues(icnCorePostingExtractId,"PostingExtractId");
					String postingExtractId =icnCorePostingExtractIdlist.get(0);
					boolean flagPostingExtractId =FREDXMLValidation.validatePostingExtractId(postingExtractId);
					if(flagPostingExtractId)
						System.out.println("PostingExtractId datatype and length validation done ");
					else
						System.out.println("PostingExtractId format is not as per IDS version 8 ");
					
					//ProcessingParticipantId
					icnCoreProcessingParticipantId = getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreProcessingParticipantIdXPATH);
					List<String> icnCoreProcessingParticipantIdList = getNodeListValues(icnCoreProcessingParticipantId,"ProcessingParticipantId");
					String processingParticipantId =icnCoreProcessingParticipantIdList.get(0);
					boolean flagProcessingParticipantId =FREDXMLValidation.validatePostingExtractId(processingParticipantId);
					if(flagProcessingParticipantId)
						System.out.println("flagProcessingParticipantId datatype and format validation done ");
					else
						System.out.println("flagProcessingParticipantId format is not as per IDS version 8 ");
					
					//ExtMessageType
					icnCoreExtMessageType = getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreExtMessageTypeXPATH);
					List<String> icnCoreExtMessageTypelist = getNodeListValues(icnCoreExtMessageType,"ExtMessageType");
					String extMessageType =icnCoreExtMessageTypelist.get(0);
					boolean flagExtMessageType =FREDXMLValidation.validateExtMessageType(extMessageType);
					if(flagProcessingParticipantId)
						System.out.println("flagExtMessageType datatype and format validation done ");
					else
						System.out.println("flagExtMessageType format is not as per IDS version 8 ");
					
					//IntMessageType
					icnCoreIntMessageType = getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreIntMessageTypeXPATH);
					List<String> intMessageTypeList = getNodeListValues(icnCoreIntMessageType,"IntMessageType");
					String intMessageType =intMessageTypeList.get(0);
					boolean flagIntMessageType =FREDXMLValidation.validateMessageSource(intMessageType);
					if(flagIntMessageType)
						System.out.println("flagIntMessageType datatype and format validation done ");
					else
						System.out.println("flagIntMessageType format is not as per IDS version 8 ");
					
					//MessageSource
					icnCoreMessageSource = getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreMessageSourceXPATH);
					List<String> messageSourceList = getNodeListValues(icnCoreMessageSource,"MessageSource");
					String messageSource =messageSourceList.get(0);
					boolean flagMsgSrc =FREDXMLValidation.validateMessageSource(messageSource);
					if(flagMsgSrc)
						System.out.println("flagMsgSrc datatype and format validation done ");
					else
						System.out.println("flagMsgSrc format is not as per IDS version 8 ");
					
					//MessageDestination
					icnCoreMessageDestination = getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreMessageDestinationXPATH);
					List<String> messageDestinationList = getNodeListValues(icnCoreMessageDestination,"MessageDestination");
					String messageDestination =messageDestinationList.get(0);
					boolean flagMsgDest =FREDXMLValidation.validateMessageSource(messageDestination);
					if(flagMsgDest)
						System.out.println("flagMsgDest datatype and format validation done ");
					else
						System.out.println("flagMsgDest format is not as per IDS version 8 ");
					
				//	public static void validateRecordCountsDataTypeVal(){
					//RecordCounts
					icnCoreRecordCounts = getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreRecordCountsXPATH);
					List<String> recordCountsList = getNodeListValues(icnCoreRecordCounts,"RecordCounts");
					String recordCounts =recordCountsList.get(0);
					boolean flagRecordCnts =FREDXMLValidation.validateMessageSource(recordCounts);
					if(flagRecordCnts)
						System.out.println("flagRecordCnts datatype and format validation done ");
					else
						System.out.println("flagRecordCnts format is not as per IDS version 8 ");
					//}
					
				}
				/*else if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC)
				{
					System.out.println(cell.getNumericCellValue()+" ");
				}
				else if(cell.getCellType()==XSSFCell.CELL_TYPE_BOOLEAN)
				{
					System.out.println(cell.getBooleanCellValue());
				}
			*/	else
				{
					System.out.println("No cell value");
				}
			}
			System.out.println();
		}
	}
	}
	
	
	
	
	public Map<String, String> getXMLNodeNameByXPATH(String filePath, String fileName, String xPath) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{

		//System.out.println("Hello World : "+ filePath+fileName);
		Document doc = getParsedXMLData(new File(filePath+fileName));

		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(xPath);
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		Map<String, String> nodeTag = new LinkedHashMap<String, String>();
		for (int i=0;i<nodes.getLength();i++){
			
			System.out.println("Node Item Content"+nodes.item(i).getTextContent());
			nodeTag.put(nodes.item(i).getNodeName(), nodes.item(i).getTextContent());
			
		}
		System.out.println("Node Item get(0)"+nodeTag.keySet());
		System.out.println("Node Item get(1)"+nodeTag.values());
		return nodeTag;
	 
	}
		
	protected static HSSFSheet getSheet(String filePath, String sheetName) throws FileNotFoundException, IOException{
		return getWorkBook(filePath).getSheet(sheetName);
	}
	
	/*
	 * Generic Method to get the access to required excel 
	 */
	private static HSSFWorkbook getWorkBook(String filePath) throws FileNotFoundException, IOException {
		return new HSSFWorkbook(new FileInputStream(new File(filePath)));
	}
}
