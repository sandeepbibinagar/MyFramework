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
public class XSDFile extends GenericMethodUtilis {
	public static String schemaTagName = "Schema";
	public static String procDateTagName = "ProcDate";
	public static String participantTagName = "Participant";
	public static String sequenceTagName = "Sequence";
	public static String versionTagName = "Version";
	public static String fileDateTagName = "FileDate";
	public static String sourceTagName = "Source";
	public static String environmentTagName = "Environment";
	
	public static String weekDayTagName = "WeekDay";
	public static String typeTagName = "Type";
	public static String ccyTagName = "Ccy";
	
	public static String sequenceRTagName="Sequence";
	public static String tranIDTagName="TranID";
	public static String postTypeTagName="PostType";
	public static String accNumTagName="AccNum";
	public static String sortCodeTagName="SortCode";
	public static String nPAAccTagName="NPAAcc";
	public static String nPASortTagName="NPASort";
	public static String amountTagName="Amount";
	public static String redirIndTagName="RedirInd";
	public static String accSystemTagName="AccSystem";
	public static String respStatusTagName="RespStatus";
	public static String respSubTypeTagName="RespSubType";
	public static String statusCntTagName="StatusCnt";
	
	public static String statusSeqtTagName="StatusSeq";
	public static String reasonCodeTagName="ReasonCode";
	public static String reasonTextTagName="ReasonText";
	
	public static String richerDataTagName="RicherData";
	public static String fraudStatusTagName="FraudStatus";
	public static String fraudReasonTagName="FraudReason";
	
	//public static String sequenceTagName = "Sequence";
	//public static String postTypeTagName = "PostType";
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
	//public static String amountTagName = "Amount";
	public static String tranSetIDTypeTagName = "TranSetIDType";
	public static String reasonTagName = "Reason";
	public static String overrideTagName = "Override";
	public static String collPartTagName = "CollPart";
	public static String collLocnTagName = "CollLocn";
	
	
	
	
	public static String tranCountTagName="TranCount";	
	
	public static String xsdFilesPath = "D:/RnE_Data/TestXML/";
	public static String xsdFileName = "sample2.xml";
	
	public static String xsdFileResponseHeaderTagName = "ResponseHeader";
	
	public static String xsdFileResponseTagName = "Response";
	
	public static String xsdFileStatusTagName = "Status";
	
	public static String xsdFileRespDetailTagName = "RespDetail";
	
	public static String xsdFileTrailerTagName = "Trailer";

	public static HashMap<String, String> getXSDFileTagwiseDataForResponseHeader() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(xsdFilesPath, xsdFileName, xsdFileResponseHeaderTagName);
		HashMap<String, String> responseHeaderSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				responseHeaderSetTagWiseData.put(schemaTagName, eachElement.getElementsByTagName(schemaTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(procDateTagName, eachElement.getElementsByTagName(procDateTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(participantTagName, eachElement.getElementsByTagName(participantTagName).item(0).getTextContent());					
				responseHeaderSetTagWiseData.put(sequenceTagName, eachElement.getElementsByTagName(sequenceTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(versionTagName, eachElement.getElementsByTagName(versionTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(sourceTagName, eachElement.getElementsByTagName(sourceTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(fileDateTagName, eachElement.getElementsByTagName(fileDateTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(environmentTagName, eachElement.getElementsByTagName(environmentTagName).item(0).getTextContent());						
			}
		}
		return responseHeaderSetTagWiseData;		
	}
	
	public static HashMap<String, String> getXSDFileTagwiseDataForPostingHeader() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(xsdFilesPath, xsdFileName, xsdFileResponseHeaderTagName);
		HashMap<String, String> responseHeaderSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				responseHeaderSetTagWiseData.put(schemaTagName, eachElement.getElementsByTagName(schemaTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(procDateTagName, eachElement.getElementsByTagName(procDateTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(participantTagName, eachElement.getElementsByTagName(participantTagName).item(0).getTextContent());					
				responseHeaderSetTagWiseData.put(sequenceTagName, eachElement.getElementsByTagName(sequenceTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(versionTagName, eachElement.getElementsByTagName(versionTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(sourceTagName, eachElement.getElementsByTagName(sourceTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(fileDateTagName, eachElement.getElementsByTagName(fileDateTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(environmentTagName, eachElement.getElementsByTagName(environmentTagName).item(0).getTextContent());
				responseHeaderSetTagWiseData.put(weekDayTagName, eachElement.getElementsByTagName(weekDayTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(typeTagName, eachElement.getElementsByTagName(typeTagName).item(0).getTextContent());	
				responseHeaderSetTagWiseData.put(ccyTagName, eachElement.getElementsByTagName(ccyTagName).item(0).getTextContent());
				
			}
		}
		return responseHeaderSetTagWiseData;		
	}
	
	public static HashMap<String, String> getXSDFileTagwiseDataForResponse() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(xsdFilesPath, xsdFileName, xsdFileResponseTagName);
		HashMap<String, String> responseSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				responseSetTagWiseData.put(sequenceRTagName, eachElement.getElementsByTagName(sequenceRTagName).item(0).getTextContent());	
				responseSetTagWiseData.put(tranIDTagName, eachElement.getElementsByTagName(tranIDTagName).item(0).getTextContent());	
				responseSetTagWiseData.put(postTypeTagName, eachElement.getElementsByTagName(postTypeTagName).item(0).getTextContent());					
				responseSetTagWiseData.put(accNumTagName, eachElement.getElementsByTagName(accNumTagName).item(0).getTextContent());	
				responseSetTagWiseData.put(sortCodeTagName, eachElement.getElementsByTagName(sortCodeTagName).item(0).getTextContent());	
				responseSetTagWiseData.put(nPAAccTagName, eachElement.getElementsByTagName(nPAAccTagName).item(0).getTextContent());	
				responseSetTagWiseData.put(nPASortTagName, eachElement.getElementsByTagName(nPASortTagName).item(0).getTextContent());	
				responseSetTagWiseData.put(amountTagName, eachElement.getElementsByTagName(amountTagName).item(0).getTextContent());
				responseSetTagWiseData.put(redirIndTagName, eachElement.getElementsByTagName(redirIndTagName).item(0).getTextContent());	
				responseSetTagWiseData.put(accSystemTagName, eachElement.getElementsByTagName(accSystemTagName).item(0).getTextContent());	
				responseSetTagWiseData.put(respStatusTagName, eachElement.getElementsByTagName(respStatusTagName).item(0).getTextContent());	
				responseSetTagWiseData.put(respSubTypeTagName, eachElement.getElementsByTagName(respSubTypeTagName).item(0).getTextContent());	
				responseSetTagWiseData.put(statusCntTagName, eachElement.getElementsByTagName(statusCntTagName).item(0).getTextContent());
			}
		}
		return responseSetTagWiseData;		
	}
	
	public static HashMap<String, String> getXSDFileTagwiseDataForStatus() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(xsdFilesPath, xsdFileName, xsdFileStatusTagName);
		HashMap<String, String> statusSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				statusSetTagWiseData.put(statusSeqtTagName, eachElement.getElementsByTagName(statusSeqtTagName).item(0).getTextContent());	
				statusSetTagWiseData.put(reasonCodeTagName, eachElement.getElementsByTagName(reasonCodeTagName).item(0).getTextContent());	
				statusSetTagWiseData.put(reasonTextTagName, eachElement.getElementsByTagName(reasonTextTagName).item(0).getTextContent());					
				
			}
		}
		return statusSetTagWiseData;		
	}
	
	public static HashMap<String, String> getXSDFileTagwiseDataForRespDetail() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(xsdFilesPath, xsdFileName, xsdFileRespDetailTagName);
		HashMap<String, String> respDetailSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				respDetailSetTagWiseData.put(richerDataTagName, eachElement.getElementsByTagName(richerDataTagName).item(0).getTextContent());	
				respDetailSetTagWiseData.put(fraudStatusTagName, eachElement.getElementsByTagName(fraudStatusTagName).item(0).getTextContent());	
				respDetailSetTagWiseData.put(fraudReasonTagName, eachElement.getElementsByTagName(fraudReasonTagName).item(0).getTextContent());					
				
			}
		}
		return respDetailSetTagWiseData;		
	}
	
	public static HashMap<String, String> getXSDFileTagwiseDataForTrailer() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(xsdFilesPath, xsdFileName, xsdFileTrailerTagName);
		HashMap<String, String> trailerSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				trailerSetTagWiseData.put(tranCountTagName, eachElement.getElementsByTagName(tranCountTagName).item(0).getTextContent());	
			}
		}
		return trailerSetTagWiseData;		
	}

	private static NodeList getNodeSetList(String xsdFilesPath, String xsdFileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(xsdFilesPath, xsdFileName)).getElementsByTagName(elementsSetWithTagName);		
	}	
}
