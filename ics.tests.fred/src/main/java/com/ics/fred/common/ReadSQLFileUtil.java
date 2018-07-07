package com.ics.fred.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadSQLFileUtil {


		public static String getSQLFileTagValues(String templateFilePath,String fileName,String startTag,String endTag){
			
			String s=new String();
			StringBuffer sbf = new StringBuffer();
			String tagValue = null;
			
			String filePath = templateFilePath+fileName;
				try{
				FileReader fr=new FileReader(new File(filePath));
				BufferedReader br = new BufferedReader(fr);
				
				while((s = br.readLine())!= null){
					sbf.append(s);
					}
				br.close();
				String content =sbf.toString();
			
				int indexStartTag=content.indexOf(startTag);
				int length = startTag.length();
				int indexEndTag=content.indexOf(endTag);
				tagValue = content.substring(indexStartTag+length, indexEndTag);
				System.out.println("Tag Value is :"+tagValue);
							
				}
			catch(Exception e){
			
		}
		return tagValue;
		}
		
		public static void updateXMLTagVal(String filepath,Map<String,String> map) throws Exception
		{
			
			DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
			Document doc= docBuilder.parse(filepath);
			
			
			for(Entry<String, String> entry:map.entrySet()){
				XPath xPath=XPathFactory.newInstance().newXPath();
				Node node=(Node)xPath.evaluate(entry.getKey(), doc,XPathConstants.NODE);
				node.setTextContent(entry.getValue());
			}
			
			Transformer transformer =TransformerFactory.newInstance().newTransformer();
			transformer.transform(new DOMSource(doc),new StreamResult(new File(filepath)));
				
		}
		
		public static void updateMutipleTagValues(String filepath,String xpathExpression,String newValue) throws Exception
		{
			
			DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
			Document doc= docBuilder.parse(filepath);
			
				XPath xPath=XPathFactory.newInstance().newXPath();
				NodeList node=(NodeList)xPath.evaluate(xpathExpression, doc,XPathConstants.NODESET);
				System.out.println("newValue before loop"+newValue);
				for(int i=0,len = node.getLength();i<len;i++){			
					Node nodes =node.item(i);		
					System.out.println("nodes :"+nodes);
					nodes.setTextContent(newValue);
					//newValue=newValue+i;
				System.out.println("newValue "+newValue);
				
				}
			//}
			
			Transformer transformer =TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			
			StreamResult result =new StreamResult(new FileWriter(filepath));
			transformer.transform(new DOMSource(doc),result);
				
			result.getWriter().flush();
			result=null;
		}
		
		public static void updateXMLTagValExcel(String filepath,Map<String,String> map,String xpathExpression) throws Exception
		{
			
			DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
			Document doc= docBuilder.parse(filepath);
			
			
			for(Entry<String, String> entry:map.entrySet()){
				XPath xPath=XPathFactory.newInstance().newXPath();
				NodeList nodelist=(NodeList)xPath.evaluate(xpathExpression, doc,XPathConstants.NODESET);				
				Node node=(Node)xPath.evaluate(entry.getKey(), doc,XPathConstants.NODE);
				for(int i=0,len = nodelist.getLength();i<len;i++){	
					Node nodeXpath = nodelist.item(i);
					System.out.println("nodelist.item "+i+" :"+nodelist.item(i));
					String newV =entry.getValue();
					nodeXpath.setTextContent(newV);
					
				/*if(node.hasChildNodes()){
					node.getNextSibling().setTextContent(newV);
					
				}*/
				
					
				}
			}
			
			Transformer transformer =TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			
			StreamResult result =new StreamResult(new FileWriter(filepath));
			transformer.transform(new DOMSource(doc),result);
				
			result.getWriter().flush();
			result=null;			
		}
		
		public static void updateMutipleTagValuesExcel(String filepath,String xpathExpression,String newValue) throws Exception
		{
			
			DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
			Document doc= docBuilder.parse(filepath);
			
				XPath xPath=XPathFactory.newInstance().newXPath();
				NodeList node=(NodeList)xPath.evaluate(xpathExpression, doc,XPathConstants.NODESET);
				System.out.println("newValue before loop"+newValue);
				for(int i=0,len = node.getLength();i<len;i++){			
					Node nodes =node.item(i);		
					System.out.println("nodes :"+nodes);
					nodes.setTextContent(newValue);
					newValue=newValue+i;
					System.out.println("newValue "+newValue);
					/*if(node.hasChildNodes()){
						node.getNextSibling().setTextContent(newV);
					}*/
				
				}
			//}
			
			Transformer transformer =TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			
			StreamResult result =new StreamResult(new FileWriter(filepath));
			transformer.transform(new DOMSource(doc),result);
				
			result.getWriter().flush();
			result=null;
		}
		
		
}