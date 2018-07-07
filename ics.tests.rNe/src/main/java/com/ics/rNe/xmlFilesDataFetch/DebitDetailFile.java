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
public class DebitDetailFile extends GenericMethodUtilis {
	
	public static String defSortTagName = "DefSort";
	public static String defAccTagName = "DefAcc";
	public static String representTagName = "Represent";
	public static String frontQualTagName = "FrontQual";
	public static String rearQualTagName = "RearQual";
	public static String fraudCodeTagName = "FraudCode";
	public static String fraudReasonTagName = "FraudReason";
	public static String fraudNameTagName = "FraudName";
	public static String duplicateTagName = "Duplicate";
	public static String dupIdTagName = "DupId";
	public static String dupStatusTagName = "DupStatus";
	public static String dupSeenTagName = "DupSeen";
	public static String dupCollectTagName = "DupCollect";
	public static String dupCaptureTagName = "DupCapture";
	public static String dupSourceTagName = "DupSource";
	public static String stoppedTagName = "Stopped";
	public static String stopDateTagName = "StopDate";
	public static String stopStatusTagName = "StopStatus";
	
	public static String stopAmtTagName = "StopAmt";
	public static String stopNameTagName = "StopName";
	public static String stopStartTagName = "StopStart";
	public static String stopEndTagName = "StopEnd";
	public static String repSortTagName = "RepSort";
	public static String repAccTagName = "RepAcc";
	public static String repAmtTagName = "RepAmt";
	public static String repSerTagName = "RepSer";
	public static String exceptionTagName = "Exception";
	
	
	public static String debitDetailsFilesPath = "D:/Test XML/";
	public static String debitDetailsFileName = "sample4.xml";
	
	public static String HeaderTagName = "DebitDetail";
	
	
	public static HashMap<String, String> getDebitDetailFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(debitDetailsFilesPath, debitDetailsFileName, HeaderTagName);
		HashMap<String, String> debitDetailSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				debitDetailSetTagWiseData.put(defSortTagName, eachElement.getElementsByTagName(defSortTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(defAccTagName, eachElement.getElementsByTagName(defAccTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(representTagName, eachElement.getElementsByTagName(representTagName).item(0).getTextContent());					
				debitDetailSetTagWiseData.put(frontQualTagName, eachElement.getElementsByTagName(frontQualTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(rearQualTagName, eachElement.getElementsByTagName(rearQualTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(fraudCodeTagName, eachElement.getElementsByTagName(fraudCodeTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(fraudReasonTagName, eachElement.getElementsByTagName(fraudReasonTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(fraudNameTagName, eachElement.getElementsByTagName(fraudNameTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(duplicateTagName, eachElement.getElementsByTagName(duplicateTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(dupIdTagName, eachElement.getElementsByTagName(dupIdTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(dupStatusTagName, eachElement.getElementsByTagName(dupStatusTagName).item(0).getTextContent());					
				debitDetailSetTagWiseData.put(dupSeenTagName, eachElement.getElementsByTagName(dupSeenTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(dupCollectTagName, eachElement.getElementsByTagName(dupCollectTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(dupCaptureTagName, eachElement.getElementsByTagName(dupCaptureTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(dupSourceTagName, eachElement.getElementsByTagName(dupSourceTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(stoppedTagName, eachElement.getElementsByTagName(stoppedTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(stopDateTagName, eachElement.getElementsByTagName(stopDateTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(stopStatusTagName, eachElement.getElementsByTagName(stopStatusTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(stopAmtTagName, eachElement.getElementsByTagName(stopAmtTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(stopNameTagName, eachElement.getElementsByTagName(stopNameTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(stopStartTagName, eachElement.getElementsByTagName(stopStartTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(stopEndTagName, eachElement.getElementsByTagName(stopEndTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(repSortTagName, eachElement.getElementsByTagName(repSortTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(repAccTagName, eachElement.getElementsByTagName(repAccTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(repAmtTagName, eachElement.getElementsByTagName(repAmtTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(repSerTagName, eachElement.getElementsByTagName(repSerTagName).item(0).getTextContent());	
				debitDetailSetTagWiseData.put(exceptionTagName, eachElement.getElementsByTagName(exceptionTagName).item(0).getTextContent());		
				
			}
		}
		return debitDetailSetTagWiseData;		
	}
	
	private static NodeList getNodeSetList(String debitDetailsFilesPath, String debitDetailsFileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(debitDetailsFilesPath, debitDetailsFileName)).getElementsByTagName(elementsSetWithTagName);		
	}

}
