package com.ics.fred.testScenarios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadSQLFileUtil;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;


public class AIXMLTestDataCreditDebitItemsInclearing extends ICSDBUtilis {

	//read excel file 
	//	getRequiredStTestData(filePath, sheetName, testCase, keyData);
	public static String filepath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\XMLConvertedToExcel.xlsx";
	//	String jobWorkTypeData = getRequiredStTestData(filepath, sheetName, testCase, keyData);
	//static String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
	//static String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\";
//	static String ICNFileName="ICNOutput.xml";
	static String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\MF01InputFolder\\";
	static String ICNFileName="TC_62948_DR_Actual.xml";
	static String icnItems ="//Items/Item";
	static String icnItemId ="//Items/Item/ItemId";
	
	static String icnItemCodelineXpath="//Items/Item/Codeline";
	static String icnItemsSerialXPATH="//Items/Item/Codeline/Serial";
	static String icnItemsReferenceXPATH="//Items/Item/Codeline/Reference";
	static String icnItemsSortCodeXPATH="//Items/Item/Codeline/SortCode";
	static String icnItemsAccountXPATH="//Items/Item/Codeline/Account";
	static String icnItemsTranCodeXPATH="//Items/Item/Codeline/TranCode";
	static String icnItemsAmountXPATH="//Items/Item/Codeline/Amount";
	static String icnItemsCurrencyXpath="//Items/Item/Codeline/Currency";
	
	static String icnItemsImageXPATH="//Items/Item/Image";
	

	
	

	public static void generate3000ItemsAixml(String fredaixmlTmpltFilename,String templateFilePath,String dbServerName,String fredDBName,String aixmlCopyTempFldrPath,int count) throws Exception{
	//	for(int i=1;i<=1;i++){
			String s =generateAIXMLFile(fredaixmlTmpltFilename,templateFilePath,dbServerName,fredDBName,aixmlCopyTempFldrPath,count);
			System.out.println("generate3000ItemsAixml  "+s);
			publishResults(s!=null, (s!=null)?"AIXML3000Items File generated :"+s:"AIXML 3000 Items file not generated..", "AIXML3000Items File generated :"+s, "AIXML 3000 Items File generated for performance testing");
	//		System.out.println("generate3000ItemsAixml xpathAndNewValueMap "+xpathAndNewValueMap+" "+s);
		//	ReadSQLFileUtil.updateXMLTagVal(s,xpathAndNewValueMap);
			
		//	Thread.sleep(2000);
		//}
		
	}
	

	public static String generateAIXMLFile(String fredaixmlTmpltFilename,String templateFilePath,String dbServerName,String fredDBName,String aixmlCopyTempFldrPath,int counter) throws Exception{
		int blockNo  = GenericMethods.generateUniqueNo(4);
		//System.out.println("Block Number: " + blockNo);		
		String dateVal = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());
		//Random No. generation
		Random randomObj = new Random();
		int high=111;
		int low=10;
		int rndm = randomObj.nextInt(high-low)+low;
		System.out.println("rndm "+rndm);
		int rndmNbr = blockNo+rndm;

		GenericMethods.copyFileFromOneLocationToAnother(templateFilePath,aixmlCopyTempFldrPath,fredaixmlTmpltFilename+".xml");
		
		String aixmlFileNameCreated =fredaixmlTmpltFilename.concat(String.valueOf(rndm))+dateVal+counter+"_Actual.xml";
		
		String aixmlfile = fredaixmlTmpltFilename+".xml";
		File filetoberenamed = new File(aixmlCopyTempFldrPath+aixmlfile);
		//System.out.println("Fred Template input path :"+aixmlCopyTempFldrPath+aixmlFileNameCreated);
		boolean aixmlFileNameCreated1 = filetoberenamed.renameTo(new File(aixmlCopyTempFldrPath+aixmlFileNameCreated));
		
		System.out.println("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
		
		//update Business Date in the file
		
		String aixmlFilePathTobeUpdated=aixmlCopyTempFldrPath+aixmlFileNameCreated;
		updateExistingFile(dbServerName,fredDBName,aixmlFilePathTobeUpdated,counter,aixmlCopyTempFldrPath,aixmlFileNameCreated);
		System.out.println("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
		validationStepInformation("Unique AIXML file Name: "+aixmlFileNameCreated+" File Renames Return :"+aixmlFileNameCreated1);
	
		//return aixmlFileNameCreated;
		return aixmlFilePathTobeUpdated;
	}

	public static void updateExistingFile(String dbServerName,String fredDBName,String aixmlFilePathtemp,int counter,String aixmlCopyTempFldrPath,String aixmlFileNameCreated) throws Exception{
		String xpathAIXMLJobBusinessDate ="//Jobs/Job/BusinessDate";
		String xpathAIXMLBlkNbr ="//Jobs/Job/Blocks/Block/BlkNbr";
		String xpathAIXMLGender ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/Gender";
		String xpathAIXMLCustomFieldsItemID ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/CustomFields/cf_ICSItemID";
		String xpathAIXMLCustomFieldsTransactionID ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/CustomFields/Cf_ICSTransactionID";
		
		Map<String,String> map=new LinkedHashMap<String,String>();	
		Map<String,String> cfsItmd = getXMLNodeValueByXPATH(aixmlCopyTempFldrPath,aixmlFileNameCreated, xpathAIXMLCustomFieldsItemID);
		int blockNo  = GenericMethods.generateUniqueNo(4);

		String sqlConfigBusinessDate ="select Value from Config.BusinessConfig where [Key]='BDATE'";
		ResultSet rsConfigBusinessDate = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, sqlConfigBusinessDate);
		String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(rsConfigBusinessDate);
		String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
		//GenericMethods.ConvertDateInToRequiredFormat("yyyyMMdd","yyyy-MM-dd",date.toString());
		//ItemId generation
	
		/*long milliSec = System.currentTimeMillis(); //13 len
		
	//	System.out.println("MilliSec :"+milliSec);
		String strMilliSec = String.valueOf(milliSec);
		strMilliSec = strMilliSec.substring(strMilliSec.length()-4);
		int itemId1 = GenericMethods.generateUniqueNo(2);
		String itemId = String.valueOf(itemId1);
		System.out.println("itemId "+itemId+" strMilliSec "+String.valueOf(milliSec));*/
		
		//transaction generation
		
		
		
		map.put(xpathAIXMLJobBusinessDate, configDateValue);
		map.put(xpathAIXMLBlkNbr, String.valueOf(blockNo));
		
		// Update file for block and businessdate
		//ReadSQLFileUtil.updateXMLTagVal(aixmlFilePathtemp,map);
		
		/*for(int i=0;i<=2;i++){
			String cfsItemId =  generateItemId(itemId,strMilliSec,String.valueOf(counter));
			map.put(xpathAIXMLCustomFieldsItemID, cfsItemId);
		}*/
	
		
		
		/*	String cfsItemId =  generateItemId(itemId,strMilliSec,String.valueOf(counter));
			map.put(xpathAIXMLCustomFieldsItemID, cfsItemId);
			
			//TransactionId : 6234567890123456781003:03
			String cfsTransactionId=generateTransactionID(blockNo,itemId,actualConfigDateValue);
			map.put(xpathAIXMLCustomFieldsTransactionID, cfsTransactionId);
			
			//Update file for mutiple itemid and transaction id
		updateMutipleTagValues(aixmlFilePathtemp,xpathAIXMLCustomFieldsItemID,cfsItemId,itemId,strMilliSec,String.valueOf(counter));
		updateMutipleTagValuesTransaction(aixmlFilePathtemp,xpathAIXMLCustomFieldsTransactionID,cfsTransactionId,blockNo,itemId,actualConfigDateValue);
		*/
	/*	if file having gender credit  and gender debit  then
		  check file having already existing transaction id for item id 
		     //
		  else 
			  update transaction T1 for itemd I1 for gender Cr
			  use same transaction T1 for itemd I2 for gender Dr
		 */
		ReadSQLFileUtil.updateXMLTagVal(aixmlFilePathtemp,map);		
		getItemISNDetails(aixmlFilePathtemp,blockNo,actualConfigDateValue,String.valueOf(counter));
		getCustomFieldsDetails(aixmlFilePathtemp,blockNo,actualConfigDateValue,String.valueOf(counter));
		//updateTransactionItemTagInXML(aixmlFilePathtemp,blockNo,itemId,"20170912",strMilliSec,String.valueOf(counter)); //commented for inclearing
		
	//	validateTransactionLogicGender(aixmlFilePathtemp,blockNo,itemId,actualConfigDateValue,strMilliSec,String.valueOf(counter));
	//	validateTransactionLogic(aixmlFilePathtemp,xpathAIXMLGender,blockNo,itemId,actualConfigDateValue,strMilliSec,String.valueOf(counter));
		//getTagVal(aixmlFilePathtemp,xpathAIXMLGender);  
		//return aixmlFilePathTobeUpdated;
	//	updateMutipleTagValues(aixmlFilePathtemp,xpathAIXMLCustomFieldsItemID,itemId,strMilliSec,String.valueOf(counter));
	//	updateMutipleTagValuesTransaction(aixmlFilePathtemp,xpathAIXMLCustomFieldsTransactionID,blockNo,itemId,actualConfigDateValue,strMilliSec,String.valueOf(counter));
		
		
	}
	
	public static String generateItemId(String counter) throws InterruptedException{	
		long milliSec = System.currentTimeMillis(); //13 len
		
		//	System.out.println("MilliSec :"+milliSec);
			String strMilliSec = String.valueOf(milliSec);
			strMilliSec = strMilliSec.substring(strMilliSec.length()-4);
			int itemId1 = GenericMethods.generateUniqueNo(2);
			String itemId = String.valueOf(itemId1);
			System.out.println("itemId "+itemId+" strMilliSec "+String.valueOf(milliSec));
		
		Random randomObj = new Random();
		int high=111;
		int low=10;
		int rndm = randomObj.nextInt(high-low)+low;
		System.out.println("Item id rndm "+rndm);
	//	Thread.sleep(10);
		int rndmNbr=rndm+GenericMethods.generateUniqueNo(4);
		int rndmNbr1=rndm+GenericMethods.generateUniqueNo(3);
		String dateTimeFormat = "yyyy.MM.dd.HH.mm.ss";
		String firstpart = getRequiredDateFormat(dateTimeFormat)
				.format(getTimeStamp(System.currentTimeMillis())).replace(".","");
		System.out.println("before counter :"+strMilliSec);
		
		//String returnVal =firstpart +"IOC"+strMilliSec+rndmNbr;
		String returnVal =firstpart +itemId+strMilliSec+itemId+counter;
		System.out.println("ItemID value :"+returnVal);
		return  returnVal;  //20170621152335IOC32672367 14+3+4+4 20170621182938-993-8457-2993
		
	}
	
	public static String generateItemISN(String counter) throws InterruptedException{	
		long milliSec = System.currentTimeMillis(); //13 len
		
		//	System.out.println("MilliSec :"+milliSec);
			String strMilliSec = String.valueOf(milliSec);
			strMilliSec = strMilliSec.substring(strMilliSec.length()-4);
			int itemId1 = GenericMethods.generateUniqueNo(2);
			String itemId = String.valueOf(itemId1);
			System.out.println("itemId "+itemId+" strMilliSec "+String.valueOf(milliSec));
		Random randomObj = new Random();
		int high=111;
		int low=10;
		int rndm = randomObj.nextInt(high-low)+low;
		System.out.println("Item id rndm "+rndm);
		int rndmNbr=rndm+GenericMethods.generateUniqueNo(4);
	//	Thread.sleep(10);
		String dateTimeFormat = "yyyy.MM.dd.HH.mm.ss";
		String firstpart = getRequiredDateFormat(dateTimeFormat)
				.format(getTimeStamp(System.currentTimeMillis())).replace(".","");
		System.out.println("before counter :"+strMilliSec);
		String returnVal ="10"+strMilliSec+rndmNbr;
		System.out.println("ISN value :"+returnVal);
		return  returnVal;  // 2+4+4
		
	}
	
	
	public static void updateMutipleTagValues(String filepath,String xpathExpression,String fileType,String strMilliSec,String counter) throws Exception
	{
		
		DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
		Document doc= docBuilder.parse(filepath);
		
			XPath xPath=XPathFactory.newInstance().newXPath();
			NodeList node=(NodeList)xPath.evaluate(xpathExpression, doc,XPathConstants.NODESET);
			//System.out.println("newValue before loop"+newValue);
			for(int i=0,len = node.getLength();i<len;i++){			
				Node nodes =node.item(i);
				String cfsItemIdVal =generateItemId(counter);
				System.out.println("nodes :"+nodes);
				nodes.setTextContent(cfsItemIdVal);
				//newValue=newValue+i;
			System.out.println("newValue "+cfsItemIdVal);
			
			
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
	
	public static void updateMutipleTagValuesTransaction(String filepath,String xpathExpression,int blockNo,String itemId1,String actualConfigDateValue,String strMilliSec,String counter) throws Exception
	{
		
		DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
		Document doc= docBuilder.parse(filepath);
		
			XPath xPath=XPathFactory.newInstance().newXPath();
			NodeList node=(NodeList)xPath.evaluate(xpathExpression, doc,XPathConstants.NODESET);
			//System.out.println("newValue before loop"+newValue);
			for(int i=0,len = node.getLength();i<len;i++){			
				Node nodes =node.item(i);
				String cfsTransactionVal =generateTransactionID(blockNo,itemId1,actualConfigDateValue);
				String cfsItemIdVal =generateItemId(counter);
				System.out.println("nodes :"+nodes+" nodes.getNodeName() "+nodes.getNodeName());
			//	nodes.setTextContent(cfsTransactionVal);
				//newValue=newValue+i;
			System.out.println("newValue "+cfsTransactionVal);
			switch (nodes.getNodeName()){
			case "cf_ICSItemID":
				nodes.setTextContent(cfsItemIdVal);
				System.out.println("Update cf_ItemID"+cfsItemIdVal);
				break;
			case "Cf_ICSTransactionID":
				nodes.setTextContent(cfsTransactionVal);
				System.out.println("Update Cf_ICSTransactionID"+cfsTransactionVal);
				break;
			}
				
			/*if(("Cf_ICSTransactionID").equals("Cf_ICSTransactionID")){
				System.out.println("Update Cf_ICSTransactionID if cond"+cfsTransactionVal);
				nodes.setTextContent(cfsTransactionVal);
			}
			System.out.println("Update Cf_ICSTransactionID"+cfsTransactionVal);*/
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
	
	public static String  generateTransactionIDOld(int blockNo,String itemId1,String actualConfigDateValue){
		
		long milliTSec = System.currentTimeMillis();
		String strMilliTSec = String.valueOf(milliTSec).substring(String.valueOf(milliTSec).length()-9);
		String transactionId = strMilliTSec+String.valueOf(blockNo)+actualConfigDateValue;
		System.out.println("transactionId "+transactionId);
		String cfsTransactionId =transactionId+":"+itemId1;
		System.out.println("transactionID "+cfsTransactionId);
		return cfsTransactionId;
		}
	
	
	public static String  generateTransactionID(int blockNo,String itemId1,String actualConfigDateValue){
		Random randomObj = new Random();
		int high=111;
		int low=10;
		int rndm = randomObj.nextInt(high-low)+low;
		System.out.println("rndm "+rndm);
		int rndmNbr = blockNo+rndm;
		long milliTSec = System.currentTimeMillis();
		String strMilliTSec = String.valueOf(milliTSec).substring(String.valueOf(milliTSec).length()-10);
		String transactionId = rndmNbr+strMilliTSec+actualConfigDateValue;
		System.out.println("transactionId "+transactionId);
		String cfsTransactionId =transactionId+":"+itemId1;
		System.out.println("transactionID "+cfsTransactionId);
		return cfsTransactionId;
		}	
	
	
	public static void validateTransactionLogic(String filepath,String xpathExpression,int blockNo,String itemId1,String actualConfigDateValue,String strMilliSec,String counter) throws Exception
	{
		/*	if file having gender credit  and gender debit  then
		  check file having already existing transaction id for item id 
		     //
		  else 
			  update transaction T1 for itemd I1 for gender Cr
			  use same transaction T1 for itemd I2 for gender Dr
		 */
		DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
		Document doc= docBuilder.parse(filepath);
		
			XPath xPath=XPathFactory.newInstance().newXPath();
			NodeList node=(NodeList)xPath.evaluate(xpathExpression, doc,XPathConstants.NODESET);
		
			for(int i=0,len = node.getLength();i<len;i++){			
				Node nodes =node.item(i);
			
				String cfsItemIdVal =generateItemId(counter);
				switch (nodes.getNodeName()){
				case "cf_ICSItemID":
					nodes.setTextContent(cfsItemIdVal);
					System.out.println("Update cf_ItemID"+cfsItemIdVal);
					break;
				case "Cf_ICSTransactionID":
					String cfsTransactionVal =generateTransactionID(blockNo,itemId1,actualConfigDateValue);
					nodes.setTextContent(cfsTransactionVal);
					System.out.println("Update Cf_ICSTransactionID"+cfsTransactionVal);
					break;
				
				case "Gender":
					String genderCredit = nodes.getTextContent();
					System.out.println("genderCredit "+genderCredit);
					//check itemid value and transaction id value for credit and debit
					if(genderCredit.equals("Cr")){
						System.out.println("Crdeit");
					}
					else
						if(genderCredit.equals("Dr")){
							System.out.println("Crdeit");
						}
					break;
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


public static void validateTransactionLogicGender(String filepath,int blockNo,String itemId1,String actualConfigDateValue,String strMilliSec,String counter) throws Exception
{
	/*	if file having gender credit  and gender debit  then
	  check file having already existing transaction id for item id 
	     //
	  else 
		  update transaction T1 for itemd I1 for gender Cr
		  use same transaction T1 for itemd I2 for gender Dr
	 */
	DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	Document doc= docBuilder.parse(filepath);
	String cftransactionidDebit = null;
	String xpathAIXMLGender ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/Gender";
	String xpathAIXMLItem ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item";
		
	XPath xPath=XPathFactory.newInstance().newXPath();
		NodeList node=(NodeList)xPath.evaluate(xpathAIXMLGender, doc,XPathConstants.NODESET);
		NodeList nodeItem=(NodeList)xPath.evaluate(xpathAIXMLItem, doc,XPathConstants.NODESET);
			NodeList cfChildNodes = doc.getElementsByTagName("Item");
	for(int i=0,lenI = cfChildNodes.getLength();i<lenI;i++){
		Node child =cfChildNodes.item(i);
			NodeList childList =child.getChildNodes();
			System.out.println("Item nodes getContext "+child.getTextContent());
	
				
						if(child.getTextContent().equals("Cr")){
							for(int k=0;k<=childList.getLength();k++){
								Node nodesChild =childList.item(k);
								NodeList nodesChildList =nodesChild.getChildNodes();
							System.out.println("nodes getContext "+nodesChild.getTextContent());
							System.out.println("nodes NodeName "+nodesChild.getNodeName());
							for(int j=0;j<nodesChildList.getLength();j++){
								Node nodesChildCf =nodesChildList.item(j);
								System.out.println("nodes NodeName "+nodesChildCf.getNodeName());
									if(nodesChildCf.getNodeName().equals("cf_ICSItemID")){
							
										System.out.println("nodesChildCf getTextCotent"+nodesChildCf.getTextContent());							
										nodesChildCf.setTextContent("cfsItemIdVal");
						
									}
							}
					}
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
				
				
				
		/*	for(int m=0,len = node.getLength();m<len;m++){			
				Node nodes =node.item(m);
			  	System.out.println("nodes.getNodeName Gender "+nodes.getNodeName());
			  	*/
			  	
			  	
			//	switch (nodes.getNodeName()){
								
				/*case "Gender":
					String genderCredit = nodes.getTextContent();
					System.out.println("genderCredit "+genderCredit);
					String cfsTransactionVal1 = null;*/
					
					//check itemid value and transaction id value for credit and debit
					/*switch(genderCredit){
					case "Cr":
						System.out.println("Crdeit");
						
						String cfsItemIdVal =generateItemId(strMilliSec,counter);
						cfsTransactionVal1 =generateTransactionID(blockNo,itemId1,actualConfigDateValue);
						cftransactionidDebit=getCustomFieldsDetails(filepath,blockNo,itemId1,actualConfigDateValue,strMilliSec,counter,cfsItemIdVal);
				
						break;
					case "Dr":
						System.out.println("Debit");
						String cfsItemIdVal1 =generateItemId(strMilliSec,counter);
					//	String cftransactionidDebit =getCustomFieldsDetails(filepath,cfsTransactionVal1,blockNo,itemId1,actualConfigDateValue,strMilliSec,counter,cfsItemIdVal1);
						getCustomFieldsDetailsDebit(filepath,blockNo,itemId1,actualConfigDateValue,strMilliSec,counter,cfsItemIdVal1);
				
						break;
						
					}*/
					//if(genderCredit.equals("Cr")){
			/*		if(nodesItem.getTextContent().contains("Cr")){
						if(nodesItem.getNodeName().equals("CustomFields")){
							if(nodesItem.getNodeName().equals("cf_ICSItemID")){
								System.out.println("cf_ICSItemID new method");
								nodesItem.setTextContent("Nisha");
							}
							else
								if(nodesItem.getNodeName().equals("Cf_ICSTransactionID")){
									System.out.println("cf_ICSItemID new method");
									nodesItem.setTextContent("NishaT");
								}
						}
							System.out.println("Crdeit");
						
						String cfsItemIdVal =generateItemId(strMilliSec,counter);
						//cfsTransactionVal1 =generateTransactionID(blockNo,itemId1,actualConfigDateValue);
				//		getCustomFieldsDetails(filepath,blockNo,itemId1,actualConfigDateValue,strMilliSec,counter,cfsItemIdVal);
												
					}
					else
						//if(genderCredit.equals("Dr")){
						if(nodesItem.getTextContent().contains("Dr")){
							
							if(nodesItem.getNodeName().equals("CustomFields")){
								if(nodesItem.getNodeName().equals("cf_ICSItemID")){
									System.out.println("cf_ICSItemID new method");
									nodesItem.setTextContent("Nisha Debit");
								}
								else
									if(nodesItem.getNodeName().equals("Cf_ICSTransactionID")){
										System.out.println("cf_ICSItemID new method");
										nodesItem.setTextContent("NishaT Debit");
									}
							}
							System.out.println("Debit");
							String cfsItemIdVal1 =generateItemId(strMilliSec,counter);
						//	String cftransactionidDebit =getCustomFieldsDetails(filepath,cfsTransactionVal1,blockNo,itemId1,actualConfigDateValue,strMilliSec,counter,cfsItemIdVal1);
					//		getCustomFieldsDetailsDebit(filepath,blockNo,itemId1,actualConfigDateValue,strMilliSec,counter,cfsItemIdVal1);
						
						}*/
				/*	break;
				}
		
			}*/
			
			
			
	
		
		
	
	/*Transformer transformer =TransformerFactory.newInstance().newTransformer();
	transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
	
	StreamResult result =new StreamResult(new FileWriter(filepath));
	transformer.transform(new DOMSource(doc),result);
		
	result.getWriter().flush();
	result=null;*/


public static String getCustomFieldsDetails(String filepath,int blockNo,String actualConfigDateValue,String counter) throws Exception{
	DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	Document doc= docBuilder.parse(filepath);
	String cfsTransactionVal1 = null;
		//getItemIdvalue
	/*NodeList cfChildNodesGender = doc.getElementsByTagName("Gender");
	for(int i=0;i<cfChildNodesGender.getLength();i++){
	
	Node nodesChildGender =cfChildNodesGender.item(i);
	if(nodesChildGender.getNodeName().equals("Cr")){
	*/	 
	
			NodeList cfChildNodes = doc.getElementsByTagName("CustomFields");
			for(int j=0;j<cfChildNodes.getLength();j++){
				NodeList childList = cfChildNodes.item(j).getChildNodes();
				for(int k=0;k<childList.getLength();k++){
					Node nodesChild =childList.item(k);
					System.out.println("childNode "+childList+" cfChildNodes"+cfChildNodes+" cfChildNodes name"+childList.item(k));
					if(nodesChild.getNodeName().equals("cf_ICSItemID")){
						//String cfsItemIdVal =generateItemId(counter);
						UUID uuid=UUID.randomUUID();
						String randomItemVal = uuid.toString().substring(0,25);
						System.out.println("nodesChild getTextCotent"+nodesChild.getTextContent());		
						randomItemVal=randomItemVal.replace("-", "");
						
						long milliSec = System.currentTimeMillis(); //13 len
						
						//	System.out.println("MilliSec :"+milliSec);
							String strMilliSec = String.valueOf(milliSec);
							strMilliSec = strMilliSec.substring(strMilliSec.length()-4);
							randomItemVal=randomItemVal+strMilliSec;
						nodesChild.setTextContent(randomItemVal);
					
						System.out.println("Update "+j+" childNode "+k+" cf_ItemID"+randomItemVal);
					}
					
					/*
					else 
						if(nodesChild.getNodeName().equals("Cf_ICSTransactionID")){
							
							cfsTransactionVal1 =generateTransactionID(blockNo,itemId1,actualConfigDateValue);
							System.out.println("nodesChild Credit getTextCotent"+nodesChild.getTextContent());
							nodesChild.setTextContent(cfsTransactionVal1);
							System.out.println("Update childNode Credit cf_ItemID"+cfsTransactionVal1);
						}
						else 
						{
							System.out.println("No child item Credit");
						}*/
				}
				
		}
	//}
	//}
		Transformer transformer =TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		StreamResult result =new StreamResult(new FileWriter(filepath));
		transformer.transform(new DOMSource(doc),result);
			
		result.getWriter().flush();
		result=null;
		return cfsTransactionVal1;
}

public static void getCustomFieldsDetailsDebit(String filepath,int blockNo,String itemId1,String actualConfigDateValue,String strMilliSec,String counter,String cfsItemIdVal) throws Exception{
	DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	Document doc= docBuilder.parse(filepath);
	//String cfsTransactionVal1;
		//getItemIdvalue
	/*NodeList cfChildNodesGender = doc.getElementsByTagName("Gender");
	for(int i=0;i<cfChildNodesGender.getLength();i++){
	
	Node nodesChildGender =cfChildNodesGender.item(i);
	if(nodesChildGender.getNodeName().equals("Cr")){
	*/	 
	
			NodeList cfChildNodes = doc.getElementsByTagName("CustomFields");
			for(int j=0;j<cfChildNodes.getLength();j++){
				NodeList childList = cfChildNodes.item(j).getChildNodes();
				for(int k=0;k<childList.getLength();k++){
					Node nodesChild =childList.item(k);
					System.out.println("childNode "+childList+" cfChildNodes"+cfChildNodes+" cfChildNodes name"+childList.item(k));
					if(nodesChild.getNodeName().equals("cf_ICSItemID")){
						cfsItemIdVal =generateItemId(counter);
						System.out.println("nodesChild Debit getTextCotent"+nodesChild.getTextContent());
						nodesChild.setTextContent(cfsItemIdVal);
						System.out.println("Update childNode Debit cf_ItemID"+cfsItemIdVal);
					}
					else 
						if(nodesChild.getNodeName().equals("Cf_ICSTransactionID")){
						//	cfsTransactionVal1 =generateTransactionID(blockNo,itemId1,actualConfigDateValue);
							System.out.println("nodesChild Debit getTextCotent"+nodesChild.getTextContent());
							String cfsTransactionVal1 =getCustomFieldsDetails(filepath,blockNo,actualConfigDateValue,counter);
							nodesChild.setTextContent(cfsTransactionVal1);
							System.out.println("Update childNode Debit cf_ItemID"+cfsTransactionVal1);
						}
						else 
						{
							System.out.println("No child item Debit");
						}
				}
				
		}
	//}
	//}
		Transformer transformer =TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		StreamResult result =new StreamResult(new FileWriter(filepath));
		transformer.transform(new DOMSource(doc),result);
			
		result.getWriter().flush();
		result=null;
}

public static void updateTransactionItemTagInXML(String filepath,int blockNo,String itemId1,String actualConfigDateValue,String strMilliSec,String counter) throws Exception{
	DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	System.out.println(filepath);
	Document doc= docBuilder.parse(filepath);
 
	NodeList itemListNodes = doc.getElementsByTagName("Item");
			NodeList cfChildNodes = doc.getElementsByTagName("CustomFields");
			boolean flag=false;
			
			for(int j=0;j<=itemListNodes.getLength()/2;j++){
				NodeList childItemList = itemListNodes.item(j).getChildNodes();
				NodeList nextItemNode = itemListNodes.item(++j).getChildNodes();
				
				for(int k=0;k<childItemList.getLength();k++){
					Node node =childItemList.item(k);
					Node nextNode =nextItemNode.item(k);
					
					//System.out.println("childNode "+childList+" cfChildNodes"+cfChildNodes+" cfChildNodes name"+childList.item(k));
					if("Gender".equals(node.getNodeName()) && "Gender".equals(nextNode.getNodeName())){
						//cfsItemIdVal =generateItemId(strMilliSec,counter);
					//	System.out.println("nodesChild Debit getTextCotent"+nodesChild.getTextContent());
						if("Cr".equals(node.getTextContent()) &&  "Dr".equals(nextNode.getTextContent())){
							flag=true;
							//update ISN value
							
						}
					}
					System.out.println(" CustomFields"+node.getNodeName());
					if("CustomFields".equals(node.getNodeName()) && "CustomFields".equals(nextNode.getNodeName())){
						if(flag){
							NodeList pnode;
							NodeList pnextNode;
							Node nodeN = null;
							Node nodeNextN = null;
							for(int p=0;p<cfChildNodes.getLength();p++){
								pnode=cfChildNodes.item(p).getChildNodes();
								pnextNode=cfChildNodes.item(++p).getChildNodes();
								
								for(int n=0;n<pnode.getLength();n++){
									nodeN = pnode.item(n);
									nodeNextN=pnextNode.item(n);
									
									System.out.println(nodeN.getNodeName());
									
									
									
									if("Cf_ICSTransactionID".equals(nodeN.getNodeName()) && "Cf_ICSTransactionID".equals(nodeNextN.getNodeName())){
										//generate transactionid
									
										String cfsTransactionVal1 =generateTransactionID(blockNo, itemId1, actualConfigDateValue);
										UUID uuid=UUID.randomUUID();
									//	String uniqueId =uuid.toString().substring(0, 25);
										System.out.println("Update childNode Debit Cf_ICSTransactionID"+cfsTransactionVal1);
										nodeN.setTextContent(cfsTransactionVal1);
										nodeNextN.setTextContent(cfsTransactionVal1);
									}
									
								}
								
																	
							}
							
							
						}
					
				}
					
				}
				Transformer transformer =TransformerFactory.newInstance().newTransformer();
				//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			//	transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				DOMSource source = new DOMSource(doc);
				StreamResult result =new StreamResult(new File(filepath));
				transformer.transform(source,result);
					
				
		
}
			
			

}

public static String getItemISNDetails(String filepath,int blockNo,String actualConfigDateValue,String counter) throws Exception{
	DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	Document doc= docBuilder.parse(filepath);
	String cfsTransactionVal1 = null;
		
			NodeList cfChildNodes = doc.getElementsByTagName("Item");
			for(int j=0;j<cfChildNodes.getLength();j++){
				NodeList childList = cfChildNodes.item(j).getChildNodes();
				for(int k=0;k<childList.getLength();k++){
					Node nodesChild =childList.item(k);
					System.out.println("childNode "+childList+" cfChildNodes"+cfChildNodes+" cfChildNodes name"+childList.item(k));
					if(nodesChild.getNodeName().equals("ISN")){
						String itemISNVal =generateItemISN(counter);
						UUID uuid=UUID.randomUUID();
						String randomISNVal = uuid.toString().substring(0,10);
						randomISNVal=randomISNVal.replace("-", "");
						randomISNVal=randomISNVal+counter;
						System.out.println("nodesChild getTextCotent"+nodesChild.getTextContent());							
						nodesChild.setTextContent(randomISNVal);
						
						System.out.println("Update "+j+" ITEM "+k+" ISN :"+randomISNVal);
					}
					
					
				}
				
		}
	//}
	//}
		Transformer transformer =TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		StreamResult result =new StreamResult(new FileWriter(filepath));
		transformer.transform(new DOMSource(doc),result);
			
		result.getWriter().flush();
		result=null;
		return cfsTransactionVal1;
}

}