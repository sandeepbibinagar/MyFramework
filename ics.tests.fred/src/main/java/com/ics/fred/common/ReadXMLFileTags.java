package com.ics.fred.common;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXMLFileTags {

	
	public List<String> getAllXmlTags(String filePath) throws ParserConfigurationException, SAXException, IOException{
		List<String> listOfXmlTags=new ArrayList<String>();

		File fXmlFile=new File(filePath);
		if(fXmlFile.exists())
		{
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		NodeList list = doc.getElementsByTagName("*");
		System.out.println("XML elements:");
		String tagValue;
		for(int i=0;i<list.getLength();i++)
		{
			Element element = (Element)list.item(i);
			listOfXmlTags.add(element.getNodeName());//putting all xml tags into list
		System.out.println("List of elements:"+element.getNodeName());
	//	listOfXmlTags.add(element.getNodeValue());
		System.out.println("List of elements:"+element);
		}
		}
		else{
			System.out.println("File Not found");
		}
		return listOfXmlTags;
		}

		

	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		ReadXMLFileTags readXML = new ReadXMLFileTags();
		String filePath = "\\\\129.227.33.35\\f$\\AutomationTestData\\output\\ICNTest.xml";
		readXML.getAllXmlTags(filePath);
	}

}
