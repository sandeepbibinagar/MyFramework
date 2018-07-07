package com.ics.fred.common;

import java.io.InputStream;
import java.util.Map;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.ics.seleniumCoreUtilis.Component;

public class ReadBatchJobTxIdValueFromICN {

	public Map<String, String> getICNTagValue(String templateFilePath, String fileName){
		
		//Read ICN batch tag value
		return null;
		
		
	}
	
	
	public Map<String, String> getBatchIDValuesFromAIXMLFile1(String templateFilePath, String fileName){
		return null;
		//Read AIXML batch tag value
		
		
	}
	

	public void getBatchIDValuesFromAIXMLFile(String templateFilePath,String fileName){
		ReadBatchJobTxIdValueFromICN readIAixmlICNValues = new ReadBatchJobTxIdValueFromICN();
		 Map<String,String> mapTagValuesFromICNFile =readIAixmlICNValues.getICNTagValue(templateFilePath,fileName);
		 
		 Map<String,String> mapTagValuesFromAIXML =readIAixmlICNValues.getBatchIDValuesFromAIXMLFile1(templateFilePath,fileName);
		 
		 for(Map.Entry<String,String> entry:mapTagValuesFromICNFile.entrySet()){
			 String fileItemId = entry.getKey();
			 String fileImageValue =entry.getValue();
			 String getImageValueFromResultSetByFileItemId = mapTagValuesFromAIXML.get(fileItemId);				 
			 Component.assertEquals(fileImageValue, getImageValueFromResultSetByFileItemId, "Actual and Expected Loaded Image values (05MF01 or 06MF01)of the messages are same.");
		
	}
		
			
		}
	

public  void getTagValFromXMLFile() throws XPathExpressionException{
	
	InputStream is = this.getClass().getResourceAsStream("/NishaICN/ICNTest.xml");
	InputSource inputSrc = new InputSource(is);
//	InputStream fileContents = inputSrc.getByteStream();
	XPath xpath = XPathFactory.newInstance().newXPath();
	String expression = "Document/TxSetSubmissn/GrpHdr/MsgId";
	NodeList nodelist = (NodeList) xpath.evaluate(expression, inputSrc, XPathConstants.NODE);
	for(int i=0;i<nodelist.getLength();i++){
		System.out.println(nodelist.item(i).getNodeValue());
	}
}
	public static void main(String[] args) throws XPathExpressionException{
		System.out.println("Element found");
		ReadBatchJobTxIdValueFromICN icnVal = new ReadBatchJobTxIdValueFromICN();
		icnVal.getTagValFromXMLFile();
	}
}
