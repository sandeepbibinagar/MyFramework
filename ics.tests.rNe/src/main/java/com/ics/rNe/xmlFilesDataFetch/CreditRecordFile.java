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
public class CreditRecordFile extends GenericMethodUtilis{
	
	public static String creditIdTagName = "CreditId";
	public static String sortCodeTagName = "SortCode";
	public static String accNumTagName = "AccNum";
	public static String refTagName = "Ref";
	public static String tranCodeTagName = "TranCode";
	public static String beneficiaryTagName = "Beneficiary";
	
	public static String creditRecordFilesPath = "D:/Test XML/";
	public static String creditRecordFileName = "sample4.xml";
	
	public static String HeaderTagName = "CreditRecord";
	
	public static HashMap<String, String> getCreditRecordFileTagwiseData() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(creditRecordFilesPath, creditRecordFileName, HeaderTagName);
		HashMap<String, String> creditRecordTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				creditRecordTagWiseData.put(creditIdTagName, eachElement.getElementsByTagName(creditIdTagName).item(0).getTextContent());	
				creditRecordTagWiseData.put(sortCodeTagName, eachElement.getElementsByTagName(sortCodeTagName).item(0).getTextContent());	
				creditRecordTagWiseData.put(accNumTagName, eachElement.getElementsByTagName(accNumTagName).item(0).getTextContent());					
				creditRecordTagWiseData.put(refTagName, eachElement.getElementsByTagName(refTagName).item(0).getTextContent());	
				creditRecordTagWiseData.put(tranCodeTagName, eachElement.getElementsByTagName(tranCodeTagName).item(0).getTextContent());	
				creditRecordTagWiseData.put(beneficiaryTagName, eachElement.getElementsByTagName(beneficiaryTagName).item(0).getTextContent());	
			}
		}
		return creditRecordTagWiseData;		
	}
	
	private static NodeList getNodeSetList(String creditRecordFilesPath, String creditRecordFileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(creditRecordFilesPath, creditRecordFileName)).getElementsByTagName(elementsSetWithTagName);		
	}	
	

}
