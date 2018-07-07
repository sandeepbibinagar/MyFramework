package com.ics.rNe.xmlFilesDataFetch;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */

public class CoreNodeFile extends GenericMethodUtilis{
	
	public static String businessDateTagName = "BusinessDate";
	public static String extractIdTagName = "ExtractId";
	public static String intMessageTypeTagName = "IntMessageType";
	//public static String sequenceTagName = "Sequence";
	public static String postingExtractIdTagName = "PostingExtractId";
	public static String processingParticipantIdTagName = "ProcessingParticipantId";
	public static String extMessageTypeTagName = "ExtMessageType";
	public static String messageSourceTagName = "MessageSource";	
	public static String recordCountsTagName="RecordCounts";
	public static String messageDestinationTagName="MessageDestination";
	
	public static String xsdFilesPath = "D:/Test XML/";
	public static String xsdFileName = "sample3.xml";	
	public static String xsdFileCoreNodeTagName = "Core";
		
	public static HashMap<String, String> getCoreFileTagwiseDataForCoreNode() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(xsdFilesPath, xsdFileName, xsdFileCoreNodeTagName);
		HashMap<String, String> coreNodeSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				coreNodeSetTagWiseData.put(businessDateTagName, eachElement.getElementsByTagName(businessDateTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(extractIdTagName, eachElement.getElementsByTagName(extractIdTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(intMessageTypeTagName, eachElement.getElementsByTagName(intMessageTypeTagName).item(0).getTextContent());					
				//coreNodeSetTagWiseData.put(sequenceTagName, eachElement.getElementsByTagName(sequenceTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(postingExtractIdTagName, eachElement.getElementsByTagName(postingExtractIdTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(processingParticipantIdTagName, eachElement.getElementsByTagName(processingParticipantIdTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(extMessageTypeTagName, eachElement.getElementsByTagName(extMessageTypeTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(messageSourceTagName, eachElement.getElementsByTagName(messageSourceTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(recordCountsTagName, eachElement.getElementsByTagName(recordCountsTagName).item(0).getTextContent());	
				coreNodeSetTagWiseData.put(messageDestinationTagName, eachElement.getElementsByTagName(messageDestinationTagName).item(0).getTextContent());						
			}
		}
		return coreNodeSetTagWiseData;		
	}
	
	private static NodeList getNodeSetList(String xsdFilesPath, String xsdFileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(xsdFilesPath, xsdFileName)).getElementsByTagName(elementsSetWithTagName);		
	}	
	
	

}
