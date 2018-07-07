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
public class PostingHeaderFile extends GenericMethodUtilis {
	

	public static String sequenceTagName = "Sequence";
	public static String postTypeTagName = "PostType";
	public static String subTypeTagName = "SubType";
	public static String sourceMsgTagName = "SourceMsg";
	public static String channelTagName = "Channel";
	public static String entryTypeTagName = "EntryType";
	public static String postingSourceTagName = "PostingSource";
	public static String respIDSourceTagName = "RespIDSource";
	public static String postDayTagName = "PostDay";
	public static String clearDateTagName = "clearDate";
	public static String settDateTypeTagName = "SettDateType";
	public static String captDateTypeTagName = "CaptDateType";
	public static String amountTagName = "Amount";
	public static String tranSetIDTypeTagName = "TranSetIDType";
	public static String reasonTagName = "Reason";
	public static String overrideTagName = "Override";
	public static String collPartTagName = "CollPart";
	public static String collLocnTagName = "CollLocn";
	
	public static String debitIdTagName = "DebitId";
	public static String sortCodeTagName = "SortCode";
	public static String accNumTagName = "AccNum";
	public static String serNumTagName = "SerNum";
	public static String tranCodeTagName = "TranCode";
	public static String switchSortTagName = "SwitchSort";
	public static String switchAccTagName = "SwitchAcc";
	public static String tranCountTagName = "TranCount";
	
	public static String postingHeaderFilesPath = "D:/Test XML/";
	public static String postingHeaderFileName = "sample4.xml";
	
	public static String HeaderTagName = "PostingHeader";
	public static String DebitRecordHeaderTagName = "PostingHeader";
	
	
	public static HashMap<String, String> getPostingHeaderFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(postingHeaderFilesPath, postingHeaderFileName, HeaderTagName);
		HashMap<String, String> postingResponseSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				postingResponseSetTagWiseData.put(sequenceTagName, eachElement.getElementsByTagName(sequenceTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(postTypeTagName, eachElement.getElementsByTagName(postTypeTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(subTypeTagName, eachElement.getElementsByTagName(subTypeTagName).item(0).getTextContent());					
				postingResponseSetTagWiseData.put(sourceMsgTagName, eachElement.getElementsByTagName(sourceMsgTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(channelTagName, eachElement.getElementsByTagName(channelTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(entryTypeTagName, eachElement.getElementsByTagName(entryTypeTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(postingSourceTagName, eachElement.getElementsByTagName(postingSourceTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(respIDSourceTagName, eachElement.getElementsByTagName(respIDSourceTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(postDayTagName, eachElement.getElementsByTagName(postDayTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(clearDateTagName, eachElement.getElementsByTagName(clearDateTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(settDateTypeTagName, eachElement.getElementsByTagName(settDateTypeTagName).item(0).getTextContent());					
				postingResponseSetTagWiseData.put(captDateTypeTagName, eachElement.getElementsByTagName(captDateTypeTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(amountTagName, eachElement.getElementsByTagName(amountTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(tranSetIDTypeTagName, eachElement.getElementsByTagName(tranSetIDTypeTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(reasonTagName, eachElement.getElementsByTagName(reasonTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(overrideTagName, eachElement.getElementsByTagName(overrideTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(collPartTagName, eachElement.getElementsByTagName(collPartTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(collLocnTagName, eachElement.getElementsByTagName(collLocnTagName).item(0).getTextContent());	
				
				
			}
		}
		return postingResponseSetTagWiseData;		
	}
	
	
	public static HashMap<String, String> getDebitHeaderFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(postingHeaderFilesPath, postingHeaderFileName, DebitRecordHeaderTagName);
		HashMap<String, String> postingResponseSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				postingResponseSetTagWiseData.put(debitIdTagName, eachElement.getElementsByTagName(debitIdTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(sortCodeTagName, eachElement.getElementsByTagName(sortCodeTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(accNumTagName, eachElement.getElementsByTagName(accNumTagName).item(0).getTextContent());					
				postingResponseSetTagWiseData.put(serNumTagName, eachElement.getElementsByTagName(serNumTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(tranCodeTagName, eachElement.getElementsByTagName(tranCodeTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(switchSortTagName, eachElement.getElementsByTagName(switchSortTagName).item(0).getTextContent());	
				postingResponseSetTagWiseData.put(switchAccTagName, eachElement.getElementsByTagName(switchAccTagName).item(0).getTextContent());	
			}
		}
		return postingResponseSetTagWiseData;		
	}
	
	public static HashMap<String, String> getPostingTrailerFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(postingHeaderFilesPath, postingHeaderFileName, DebitRecordHeaderTagName);
		HashMap<String, String> postingResponseSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				postingResponseSetTagWiseData.put(tranCountTagName, eachElement.getElementsByTagName(tranCountTagName).item(0).getTextContent());
			}
		}
		return postingResponseSetTagWiseData;		
	}
	
	
	private static NodeList getNodeSetList(String postingHeaderFilesPath, String postingHeaderFileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(postingHeaderFilesPath, postingHeaderFileName)).getElementsByTagName(elementsSetWithTagName);		
	}	
	

}
