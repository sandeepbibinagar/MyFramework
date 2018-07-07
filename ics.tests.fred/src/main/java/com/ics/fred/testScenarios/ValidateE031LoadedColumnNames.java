package com.ics.fred.testScenarios;

import java.io.IOException;

import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ics.fred.common.FREDXMLValidation;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;


public class ValidateE031LoadedColumnNames {



	
	public static String businessDateTagName = "BusinessDate";
	public static String extractIdTagName = "ExtractId";
	public static String intMessageTypeTagName = "IntMessageType";
	public static String processingParticipantIdTagName = "ProcessingParticipantId";
	public static String extMessageTypeTagName = "ExtMessageType";
	public static String messageSourceTagName = "MessageSource";	
	public static String recordCountsTagName="RecordCounts";
	public static String messageDestinationTagName="MessageDestination";
	public static String xsdFileCoreNodeTagName="Core";
	
	public static HashMap<String, String> verifyE031ColumnPresentInDatabase(String xsdFilesPath,String xsdFileName) throws SAXException, IOException, ParserConfigurationException
		{
			NodeList nodeSetList = getNodeSetList(xsdFilesPath, xsdFileName, xsdFileCoreNodeTagName);
			HashMap<String, String> coreNodeSetTagWiseData = new LinkedHashMap<String, String>();
			boolean assertFlagCheck;
			for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
			{
				Node eachNode = nodeSetList.item(eachNodeSetEntry);
				if(eachNode.getNodeType()==Node.ELEMENT_NODE)
				{
					Element eachElement=(Element) eachNode;
					coreNodeSetTagWiseData.put(businessDateTagName, eachElement.getElementsByTagName(businessDateTagName).item(0).getTextContent());
					System.out.println("CoreNodeValue is :"+coreNodeSetTagWiseData.get(businessDateTagName));
					 assertFlagCheck = FREDXMLValidation.validateBusinessDateWithFile(coreNodeSetTagWiseData.get(businessDateTagName));
					System.out.println("AssertValue fro Busineesdate "+assertFlagCheck);				
					Component.assertTrue(assertFlagCheck, "BusinessDate Tag as expected");
				
					coreNodeSetTagWiseData.put(extractIdTagName, eachElement.getElementsByTagName(extractIdTagName).item(0).getTextContent());	
					System.out.println("CoreNodeValue of ExtractID is :"+coreNodeSetTagWiseData.get(extractIdTagName));
					assertFlagCheck = FREDXMLValidation.validateExtractId(coreNodeSetTagWiseData.get(extractIdTagName));
					System.out.println("AssertValue fro ExtractID "+assertFlagCheck);				
					Component.assertTrue(assertFlagCheck, "ExtractID as expected");
									
					coreNodeSetTagWiseData.put(intMessageTypeTagName, eachElement.getElementsByTagName(intMessageTypeTagName).item(0).getTextContent());					
					System.out.println("CoreNodeValue of IntMessageType is :"+coreNodeSetTagWiseData.get(intMessageTypeTagName));
					assertFlagCheck = FREDXMLValidation.validateIntMessageType(coreNodeSetTagWiseData.get(intMessageTypeTagName));
					System.out.println("AssertValue for IntMessageType "+assertFlagCheck);				
					Component.assertTrue(assertFlagCheck, "IntMessageType Tag as expected");
					
					
					coreNodeSetTagWiseData.put(processingParticipantIdTagName, eachElement.getElementsByTagName(processingParticipantIdTagName).item(0).getTextContent());	
					System.out.println("CoreNodeValue of ProcessingParticipantId is :"+coreNodeSetTagWiseData.get(processingParticipantIdTagName));
					assertFlagCheck = FREDXMLValidation.validateProcessingParticipantId(coreNodeSetTagWiseData.get(processingParticipantIdTagName));
					Component.assertTrue(assertFlagCheck, "ProcessingParticipantIdTagName Tag not as expected");
					
					coreNodeSetTagWiseData.put(extMessageTypeTagName, eachElement.getElementsByTagName(extMessageTypeTagName).item(0).getTextContent());	
					System.out.println("CoreNodeValue of is ExtMessageType:"+coreNodeSetTagWiseData.get(extMessageTypeTagName));
					assertFlagCheck = FREDXMLValidation.validateExtMessageType((coreNodeSetTagWiseData.get(extMessageTypeTagName)).toString());
					System.out.println("AssertValue for MessageDestination "+assertFlagCheck);				
									
					coreNodeSetTagWiseData.put(messageSourceTagName, eachElement.getElementsByTagName(messageSourceTagName).item(0).getTextContent());	
					System.out.println("CoreNodeValue of MessageSourceis :"+coreNodeSetTagWiseData.get(messageSourceTagName));
					assertFlagCheck = FREDXMLValidation.validateMessageSource(coreNodeSetTagWiseData.get(messageSourceTagName));
					Component.assertTrue(assertFlagCheck, "MessageSource Tag as expected");
					System.out.println("AssertValue for MessageSource "+assertFlagCheck);				
									
					coreNodeSetTagWiseData.put(recordCountsTagName, eachElement.getElementsByTagName(recordCountsTagName).item(0).getTextContent());	
					System.out.println("CoreNodeValue of RecordCounts is :"+coreNodeSetTagWiseData.get(recordCountsTagName));
					assertFlagCheck = FREDXMLValidation.validateRecordCounts((coreNodeSetTagWiseData.get(recordCountsTagName)).toString());
					Component.assertTrue(assertFlagCheck, "RecordCounts tag as expected");
					System.out.println("AssertValue for RecordCounts "+assertFlagCheck);
									
					coreNodeSetTagWiseData.put(messageDestinationTagName, eachElement.getElementsByTagName(messageDestinationTagName).item(0).getTextContent());						
					System.out.println("CoreNodeValue of MessageDestination is :"+coreNodeSetTagWiseData.get(messageDestinationTagName));
					assertFlagCheck = FREDXMLValidation.validateMessageDestination(coreNodeSetTagWiseData.get(messageDestinationTagName));
					Component.assertTrue(assertFlagCheck, "MessageDestination Tag as expected");
					System.out.println("AssertValue for MessageDestination "+assertFlagCheck);	
				}
			}
			return coreNodeSetTagWiseData;		
		}
		
		private static NodeList getNodeSetList(String xsdFilesPath, String xsdFileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
		{
			return GenericMethodUtilis.getParsedXMLData(GenericMethodUtilis.getFileStreamWithoutExtension(xsdFilesPath, xsdFileName)).getElementsByTagName(elementsSetWithTagName);		
		}	

		
	}
