
package com.ics.fred.testScenarios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
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


public class ValidateAIXMLMutipleItemsCreditDebitOutclearing extends ICSDBUtilis {

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
	 //String icnfilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\Onus  Item type\\LBG\\ICNOutputFileAIXMLBulkTest.xml";
	static String templateFilePath="\\\\129.227.33.35\\f$\\AutomationTestData\\OnusItemType\\LBG\\";
	static String fileNameAIXML="AIXMLExtract_Bulk_Test.xml";
	static String fileNameICN="ICNOutputFileAIXMLBulkTest.xml";
	static String icnItemCodelineXpath="//Items/Item/Codeline";
	static String icnItemsSerialXPATH="//Items/Item/Codeline/Serial";
	static String icnItemsReferenceXPATH="//Items/Item/Codeline/Reference";
	static String icnItemsSortCodeXPATH="//Items/Item/Codeline/SortCode";
	static String icnItemsAccountXPATH="//Items/Item/Codeline/Account";
	static String icnItemsTranCodeXPATH="//Items/Item/Codeline/TranCode";
	static String icnItemsAmountXPATH="//Items/Item/Codeline/Amount";
	static String icnItemsCurrencyXpath="//Items/Item/Codeline/Currency";
	
	static String icnItemsImageXPATH="//Items/Item/Image";
	

	
	

	/*public static void generate3000ItemsAixml(String fredaixmlTmpltFilename,String templateFilePath,String dbServerName,String fredDBName,String aixmlCopyTempFldrPath,int count) throws Exception{
	//	for(int i=1;i<=1;i++){
			String s =generateAIXMLFile(fredaixmlTmpltFilename,templateFilePath,dbServerName,fredDBName,aixmlCopyTempFldrPath,count);
			System.out.println("generate3000ItemsAixml  "+s);
	//		System.out.println("generate3000ItemsAixml xpathAndNewValueMap "+xpathAndNewValueMap+" "+s);
		//	ReadSQLFileUtil.updateXMLTagVal(s,xpathAndNewValueMap);
			
		//	Thread.sleep(2000);
		//}
		
	}
	*/

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
	
		long milliSec = System.currentTimeMillis(); //13 len
		
	//	System.out.println("MilliSec :"+milliSec);
		String strMilliSec = String.valueOf(milliSec);
		strMilliSec = strMilliSec.substring(strMilliSec.length()-4);
		int itemId1 = GenericMethods.generateUniqueNo(2);
		String itemId = String.valueOf(itemId1);
		System.out.println("itemId "+itemId+" strMilliSec "+String.valueOf(milliSec));
		
		//transaction generation
		
		
		
		map.put(xpathAIXMLJobBusinessDate, configDateValue);
		map.put(xpathAIXMLBlkNbr, String.valueOf(blockNo));
		
	
		ReadSQLFileUtil.updateXMLTagVal(aixmlFilePathtemp,map);
		ArrayList<String> val =getDeferredItemTagValFromXML(aixmlFilePathtemp);
		for(int i=0;i<val.size();i++){
			System.out.println("val "+val.get(i));
			if(val.get(i).equals("Yes")&& val.get(i).equals("Yes"))
				System.out.println(" Debit value is IODI and credit value is IOCI");
			else
				if(val.get(i).equals("Yes")&& val.get(i).equals("No"))
					System.out.println(" Debit value is ITPI and credit value is CRDI");
				else
					if(val.get(i).equals("No")&& val.get(i).equals("Yes"))
						System.out.println(" Debit value is RTPI and credit value is IOCI");
					else
						if(val.get(i).equals("No")&& val.get(i).equals("No"))
							System.out.println(" Debit value is RTPI and credit value is CRDI");
						else
							System.out.println("There is no DeferredTag in AIXML file");
		}

	}
	
	
	



		

public static ArrayList<String> getISOItemTypeIdVal(String icnfilepath) throws Exception{
	DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	Document doc= docBuilder.parse(icnfilepath);
	boolean flag=false;
	ArrayList<String> itemTypeRetnVal = new ArrayList<>();
	String crdTItmVal = null,debitTItmVal=null;
	Node pnode;
	NodeList qnode;
	NodeList pnextNode,qnextNode;
	Node nodeN = null, nodeP = null;
	Node nodeNextN = null, nodeNextP = null;
			NodeList cfChildNodes = doc.getElementsByTagName("TxSet");
			NodeList chldNodeCreditItemType = doc.getElementsByTagName("CrdtItm");
			NodeList chldNodeDebitItemType = doc.getElementsByTagName("DbtItm");
		System.out.println(" TxSet cfChildNodes.getLength()"+cfChildNodes.getLength());
			for(int j=0;j<cfChildNodes.getLength();j++){
				NodeList childItemList = cfChildNodes.item(j).getChildNodes();
				NodeList nextItemNode = cfChildNodes.item(++j).getChildNodes();
				System.out.println(" Tset childItemList"+childItemList);
				for(int k=0;k<childItemList.getLength();k++){
					Node node =childItemList.item(k);
					Node nextNode =nextItemNode.item(k);
					
					//System.out.println("childNode "+childList+" cfChildNodes"+cfChildNodes+" cfChildNodes name"+childList.item(k));
					if("CrdtItm".equals(node.getNodeName()))
						{ 
							flag =true;
							//int p=1;
							for(int p=1;p<=chldNodeCreditItemType.getLength();p++){
								pnode=chldNodeCreditItemType.item(p).getNextSibling();
								System.out.println("chldNodeCreditItemType.item(p).getNextSibling() "+chldNodeCreditItemType.item(p).getNextSibling());
							//	pnode=chldNodeCreditItemType.item(p).getChildNodes();
						//		pnextNode=chldNodeCreditItemType.item(++p).getChildNodes();
							//	System.out.println("CrdtItm childNodelength"+pnode.getLength());
								if(flag){
								for(int m=0;m<((NodeList) pnode).getLength();m++){
									nodeN = ((NodeList) pnode).item(m);
							//		nodeNextN=pnextNode.item(m);
									System.out.println("CrdtItm childNode node 1 "+nodeN);
									System.out.println("CrdtItm childNode node 2 "+nodeNextN);
								
							//		if("CdtItmId".equals(nodeN.getNodeName()) || "CdtItmTp".equals(nodeNextN.getNodeName()))
									if("CdtItmTp".equals(nodeN.getNodeName()))
										{
											//get value of CdtItmTp -CRDI
											String crdTItmIdVal =nodeN.getTextContent();
											System.out.println("Credit ItemType ID is"+crdTItmIdVal);
											
											 crdTItmVal =nodeN.getTextContent();
											System.out.println("Credit ItemType is"+crdTItmVal);
										}
								
						}
								}
								else
								{
									System.out.println("Issue in Code!!");
								}
						}
						}
				
						if("DbtItm".equals(nextNode.getNodeName()))
							{
								for(int q=1;q<=chldNodeDebitItemType.getLength();q++){
									qnode=chldNodeDebitItemType.item(q).getChildNodes();
									qnextNode=chldNodeDebitItemType.item(++q).getChildNodes();
									
									for(int n=1;n<=qnode.getLength();n++){
										nodeP = qnode.item(n);
										nodeNextP=qnextNode.item(n);
										
										System.out.println(nodeP.getNodeName());
									
							//			if("DbtItmId".equals(nodeP.getNodeName()) && "DbtItmTp".equals(nodeNextP.getNodeName()))
										if("DbtItmTp".equals(nodeP.getNodeName()))	
										{
												//get value of CdtItmTp -CRDI
												String crdTItmIdVal =nodeP.getTextContent();
												System.out.println("Debit Item ID is"+crdTItmIdVal);
												
												debitTItmVal =nodeP.getTextContent();
												System.out.println("Debit ItemType is"+debitTItmVal);
												
										}
									
							}
						}
					}
						itemTypeRetnVal.add(crdTItmVal);
						itemTypeRetnVal.add(debitTItmVal);
				}
				/*if(crdTItmVal.equals("CRDI") && debitTItmVal.equals("ITPI")){
					System.out.println(" cashItem value is 1");
				}
				else
					if(crdTItmVal.equals("CRDI") && debitTItmVal.equals("RTPI")){
						System.out.println(" cashItem value is 0");
					}*/
				
				
					
			}
		Transformer transformer =TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
	//	StreamResult result =new StreamResult(new FileWriter(filepath));
		//transformer.transform(new DOMSource(doc),result);
			
	//	result.getWriter().flush();
	//	result=null;
		
	return itemTypeRetnVal;
}



public static ArrayList<String> getDeferredItemTagValFromXML(String filepath) throws Exception{
	DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	System.out.println(filepath);
	Document doc= docBuilder.parse(filepath);
	String creditDeferredVal = null,debitDeferredVal =null;
	String cfsCreditItemId = null;
	String cfsDebitItemId = null;
	ArrayList<String> itemDeferredVal = new ArrayList<>();
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
									
									if("cf_ICSItemID".equalsIgnoreCase(nodeN.getNodeName()) && "cf_ICSItemID".equalsIgnoreCase(nodeNextN.getNodeName())){
										
										 cfsCreditItemId =nodeN.getTextContent();
										 cfsDebitItemId =nodeNextN.getTextContent();
										System.out.println("cf_ICSItemID Credit nodeN.getNodeName()"+cfsCreditItemId);
										System.out.println("cf_ICSItemID Debit nodeN.getNodeName()"+cfsDebitItemId);
									}
									
									if("Cf_DeferredPosting".equals(nodeN.getNodeName()) && "Cf_DeferredPosting".equals(nodeNextN.getNodeName())){
									
										 creditDeferredVal = nodeN.getTextContent();
										 debitDeferredVal = nodeNextN.getTextContent();
										//getTextContent of DeferredPosting customfields for credit and debit
										System.out.println(" Cf_DeferredPosting nodeN.getNodeName()"+creditDeferredVal);
										System.out.println(" Cf_DeferredPosting nodeNextN.getNodeName()"+debitDeferredVal);
										
									System.out.println(" cf_fCashItem nodeNextN.getNodeName()"+debitDeferredVal);
											ArrayList<String> icnDebitTxPValue2 =getItemTypeDebitOutClearingOtherChannelValue(cfsCreditItemId,cfsDebitItemId,creditDeferredVal,debitDeferredVal);
											System.out.println("getItemTypeDebitOutClearingValue() credit for "+creditDeferredVal+" "+icnDebitTxPValue2.get(0));
											System.out.println("getItemTypeDebitOutClearingValue() debit for "+debitDeferredVal+" "+icnDebitTxPValue2.get(1));
									validationStepInformation("getItemTypeDebitOutClearingValue() credit for "+creditDeferredVal+" "+icnDebitTxPValue2.get(0));
									validationStepInformation("getItemTypeDebitOutClearingValue() debit for "+debitDeferredVal+" "+icnDebitTxPValue2.get(1));
								
									String actualItemOnusVal ="CashItem value in AIXML OC is "+creditDeferredVal+" and CreditItemType in MSG01 message is "+icnDebitTxPValue2.get(0);
								//	publishResults(icnDebitTxPValue2!=null, , "getItemTypeDebitOutClearingValue() credit for "+creditDeferredVal+" "+icnDebitTxPValue2.get(0), "Credit ItemType and Onus mapping value validation for cashItem value "+creditDeferredVal);
								//			publishResults(icnDebitTxPValue2!=null, "getItemTypeDebitOutClearingValue() debit for "+debitDeferredVal+" "+icnDebitTxPValue2.get(1), "getItemTypeDebitOutClearingValue() debit for "+debitDeferredVal+" "+icnDebitTxPValue2.get(1), "Debit ItemType and Onus mapping value validation for cashItem value "+debitDeferredVal);
							}
								
							
									
								}
								
																	
							}
							
							
						}
					
				}
					
				}
				
				itemDeferredVal.add(creditDeferredVal);
				itemDeferredVal.add(debitDeferredVal);
				
				Transformer transformer =TransformerFactory.newInstance().newTransformer();
				//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			//	transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				DOMSource source = new DOMSource(doc);
				StreamResult result =new StreamResult(new File(filepath));
				transformer.transform(source,result);
				
				
				
		
}
			
			return itemDeferredVal;

}

public static ArrayList<String> getCashItemTagValFromXML(String filepath,String icnfilepath) throws Exception{
	  
	DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	System.out.println(filepath);
	Document doc= docBuilder.parse(filepath);
	String creditDeferredVal = null,debitDeferredVal =null;
	
	
	String cfsCreditItemId = null;
	String cfsDebitItemId= null;
	
	ArrayList<String> itemDeferredVal = new ArrayList<>();
	NodeList itemListNodes = doc.getElementsByTagName("Item");
			NodeList cfChildNodes = doc.getElementsByTagName("CustomFields");
			boolean flag=false;
			
			for(int j=0;j<=itemListNodes.getLength()/2;j++){
				NodeList childItemList = itemListNodes.item(j).getChildNodes();
				NodeList nextItemNode = itemListNodes.item(++j).getChildNodes();
				
				for(int k=0;k<childItemList.getLength();k++){
					Node node =childItemList.item(k);
					Node nextNode =nextItemNode.item(k);
					
					if("Gender".equals(node.getNodeName()) && "Gender".equals(nextNode.getNodeName())){
						if("Cr".equals(node.getTextContent()) &&  "Dr".equals(nextNode.getTextContent())){
							flag=true;
							
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
									
									if("cf_ICSItemID".equalsIgnoreCase(nodeN.getNodeName()) && "cf_ICSItemID".equalsIgnoreCase(nodeNextN.getNodeName())){
										
										 cfsCreditItemId =nodeN.getTextContent();
										 cfsDebitItemId =nodeNextN.getTextContent();
										System.out.println("cf_ICSItemID Credit nodeN.getNodeName()"+cfsCreditItemId);
										System.out.println("cf_ICSItemID Debit nodeN.getNodeName()"+cfsDebitItemId);
									}
										
									if("cf_fCashItem".equalsIgnoreCase(nodeN.getNodeName()) && "cf_fCashItem".equalsIgnoreCase(nodeNextN.getNodeName())){
									   
										 creditDeferredVal = nodeN.getTextContent();
										 debitDeferredVal = nodeNextN.getTextContent();
											System.out.println(" cf_fCashItem nodeN.getNodeName()"+creditDeferredVal);
										System.out.println(" cf_fCashItem nodeNextN.getNodeName()"+debitDeferredVal);
											ArrayList<String> icnDebitTxPValue2 =getItemTypeDebitOutClearingValue(cfsCreditItemId,cfsDebitItemId,creditDeferredVal,debitDeferredVal);
											System.out.println("getItemTypeDebitOutClearingValue() credit for "+creditDeferredVal+" "+icnDebitTxPValue2.get(0));
											System.out.println("getItemTypeDebitOutClearingValue() debit for "+debitDeferredVal+" "+icnDebitTxPValue2.get(1));
									validationStepInformation("getItemTypeDebitOutClearingValue() credit for "+creditDeferredVal+" "+icnDebitTxPValue2.get(0));
									validationStepInformation("getItemTypeDebitOutClearingValue() debit for "+debitDeferredVal+" "+icnDebitTxPValue2.get(1));
								
									String actualItemOnusVal ="CashItem value for Credit ItemId "+cfsCreditItemId+" and Debit ItemId "+cfsDebitItemId+" in AIXML OC is "+creditDeferredVal+" and CreditItemType in MSG01 message is "+icnDebitTxPValue2.get(0)+
											"Debit ItemType in MSG01 message is "+icnDebitTxPValue2.get(1);
									
									String expectedItemOnusVal ="CashItem value for Credit ItemId "+cfsCreditItemId+" and Debit ItemId "+cfsDebitItemId+" in AIXML OC is "+creditDeferredVal+" and CreditItemType in MSG01 message is "+icnDebitTxPValue2.get(0)+
											"Debit ItemType in MSG01 message is "+icnDebitTxPValue2.get(1);
								//	publishResults(icnDebitTxPValue2!=null, , "getItemTypeDebitOutClearingValue() credit for "+creditDeferredVal+" "+icnDebitTxPValue2.get(0), "Credit ItemType and Onus mapping value validation for cashItem value "+creditDeferredVal);
								publishResults(icnDebitTxPValue2!=null, actualItemOnusVal,expectedItemOnusVal, "Debit ItemType and Onus mapping value validation for cashItem value "+debitDeferredVal);
							}
								
																	
							}
							
							
						}
					
				}
					
				}
				
				itemDeferredVal.add(creditDeferredVal);
				itemDeferredVal.add(debitDeferredVal);
				}
				Transformer transformer =TransformerFactory.newInstance().newTransformer();
				//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			//	transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				DOMSource source = new DOMSource(doc);
				StreamResult result =new StreamResult(new File(filepath));
				transformer.transform(source,result);
				
				
				}
			return itemDeferredVal;
		
	

}




public static ArrayList<String> getItemIdTagValFromAIXML(String filepath) throws Exception{
	DocumentBuilderFactory docFactory=DocumentBuilderFactory.newInstance();
	DocumentBuilder docBuilder=docFactory.newDocumentBuilder();
	System.out.println(filepath);
	Document doc= docBuilder.parse(filepath);
	String creditDeferredVal = null,debitDeferredVal =null;
	ArrayList<String> itemDeferredVal = new ArrayList<>();
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
									
									
									
									if("cf_ICSItemID".equalsIgnoreCase(nodeN.getNodeName()) && "cf_ICSItemID".equalsIgnoreCase(nodeNextN.getNodeName())){
									
										 creditDeferredVal = nodeN.getTextContent();
										 debitDeferredVal = nodeNextN.getTextContent();
										//getTextContent of DeferredPosting customfields for credit and debit
										System.out.println(" cf_ICSItemID nodeN.getNodeName()"+creditDeferredVal);
										System.out.println(" cf_ICSItemID nodeNextN.getNodeName()"+debitDeferredVal);
										
									}
									
								}
								
																	
							}
							
							
						}
					
				}
					
				}
				
				itemDeferredVal.add(creditDeferredVal);
				itemDeferredVal.add(debitDeferredVal);
				
				Transformer transformer =TransformerFactory.newInstance().newTransformer();
				//transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			//	transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
				DOMSource source = new DOMSource(doc);
				StreamResult result =new StreamResult(new File(filepath));
				transformer.transform(source,result);
				
				
				
		
}
			
			return itemDeferredVal;

}



public static Map<String,String> getISOCreditDebitItemTypeTagValue(String templateFilePath,String fileName){
	
	String startTag="<TxSetSubmissn>";
	String endTag ="</TxSetSubmissn>";
	String s=new String();
	StringBuffer sbf = new StringBuffer();
	Map<String,String> itemIdandImageMap = new HashMap<String,String>();
	String contentBetweenItems =null;
	
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
		contentBetweenItems = content.substring(indexStartTag+length, indexEndTag);
		
		String[] itemArray=contentBetweenItems.split("<TxSet>");
		String startTagCreditItemTag="<CrdtItm>";
		String endTagItemId ="</CrdtItm>";
		int indexStartTagItemId,indexStartImage,indexEndTagItemId,indexEndTagImage;
		int lengthItemdId,lengthImage;
		String imageValue,ItemIdValue;
		String creditStartTag="<CdtItmTp>";
		String creditEndTag ="</CdtItmTp>";
		
	
		String debitStartTag="<DbtItmTp>";
		String debitEndTag ="</DbtItmTp>";
		
		for(String itemArrayValues:itemArray){
			if(itemArrayValues.contains(creditStartTag)&&itemArrayValues.contains(startTagCreditItemTag))
			{
				indexStartTagItemId=itemArrayValues.indexOf(startTagCreditItemTag);
				lengthItemdId=startTagCreditItemTag.length();
				indexEndTagItemId=itemArrayValues.indexOf(endTagItemId);
				ItemIdValue=itemArrayValues.substring(indexStartTagItemId+lengthItemdId, indexEndTagItemId);
				
				indexStartImage=itemArrayValues.indexOf(creditStartTag);
				lengthImage=creditStartTag.length();
				indexEndTagImage=itemArrayValues.indexOf(creditEndTag);
				imageValue=itemArrayValues.substring(indexStartImage+lengthImage, indexEndTagImage);
				
				itemIdandImageMap.put(ItemIdValue, imageValue);
			}
		}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
      return itemIdandImageMap;
}

public static String getItemTypeOutClearingValue(){
	String fileImageValue = null;
	 Map<String,String> mapTagValuesFromFile =getISOCreditDebitItemTypeTagValue(templateFilePath, fileNameICN);
	 
 
	 for(Map.Entry<String,String> entry:mapTagValuesFromFile.entrySet()){
		 String fileItemId = entry.getKey();
		  fileImageValue =entry.getValue();
		// String getImageValueFromResultSetByFileItemId = mapTagValuesFromResultSet.get(fileItemId);		
		 
	 }
	 return fileImageValue;
}

public static Map<String,String> getISODebitItemTypeTagValue(String templateFilePath,String fileName){
	
	String startTag="<TxSetSubmissn>";
	String endTag ="</TxSetSubmissn>";
	String s=new String();
	StringBuffer sbf = new StringBuffer();
	Map<String,String> itemIdandImageMap = new HashMap<String,String>();
	String contentBetweenItems =null;
	
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
		contentBetweenItems = content.substring(indexStartTag+length, indexEndTag);
		
		String[] itemArray=contentBetweenItems.split("<TxSet>");
		String startTagCreditItemTag="<CrdtItm>";
		String endTagItemId ="</CrdtItm>";
		int indexStartTagItemId,indexStartImage,indexEndTagItemId,indexEndTagImage;
		int lengthItemdId,lengthImage;
		String imageValue,ItemIdValue;
		/*String creditStartTag="<CdtItmTp>";
		String creditEndTag ="</CdtItmTp>";
		*/
	
		String creditStartTag="<DbtItmTp>";
		String creditEndTag ="</DbtItmTp>";
		
		for(String itemArrayValues:itemArray){
			if(itemArrayValues.contains(creditStartTag)&&itemArrayValues.contains(startTagCreditItemTag))
			{
				indexStartTagItemId=itemArrayValues.indexOf(startTagCreditItemTag);
				lengthItemdId=startTagCreditItemTag.length();
				indexEndTagItemId=itemArrayValues.indexOf(endTagItemId);
				ItemIdValue=itemArrayValues.substring(indexStartTagItemId+lengthItemdId, indexEndTagItemId);
				
				indexStartImage=itemArrayValues.indexOf(creditStartTag);
				lengthImage=creditStartTag.length();
				indexEndTagImage=itemArrayValues.indexOf(creditEndTag);
				imageValue=itemArrayValues.substring(indexStartImage+lengthImage, indexEndTagImage);
				
				itemIdandImageMap.put(ItemIdValue, imageValue);
			}
		}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		
      return itemIdandImageMap;
}

public static ArrayList<String> getItemTypeDebitOutClearingValue(String aixmlCreditItemVal,String aixmlDebitItemval,String aixmlCashItemCredit,String aixmlCashItemDebit){
	String fileImageValue = null ,fileImageValueDebit= null;
	ArrayList<String> retrunItemValue=new ArrayList<>();
	 Map<String,String> mapTagValuesFromFile =getISOCreditDebitItemTypeTagValue(templateFilePath, fileNameICN);
	// Map<String,String> mapTagValuesFromResultSet 
	 Map<String,String> mapTagValuesDebitFromFile =getISODebitItemTypeTagValue(templateFilePath, fileNameICN);
	 
	
	 for(Map.Entry<String,String> entry:mapTagValuesFromFile.entrySet()){
		 String fileItemId = entry.getKey();
		 fileImageValue =entry.getValue();
		 for(Map.Entry<String,String> entrydeit:mapTagValuesDebitFromFile.entrySet()){
		 
		
		  fileImageValueDebit =entrydeit.getValue();
		  System.out.println("ISO CreditItem value :"+fileImageValue);
		  System.out.println("ISO DebitItem value :"+fileImageValueDebit);
		// String getImageValueFromResultSetByFileItemId = mapTagValuesFromResultSet.get(fileItemId);		
		/* if(fileCreditItemValue.equals(aixmlCreditItemVal) && fileImageValueDebit.equals(aixmlDebitItemval)){
			 
		 }*/
			  if(fileImageValue.equals("CRDI") && fileImageValueDebit.equals("ITPI")){
				  if(aixmlCashItemCredit.equals("1") && aixmlCashItemDebit.equals("1")){
					System.out.println(" cashItem value is 1 and credit item value is "+fileImageValue);
					System.out.println(" cashItem value is 1 and debit item value is "+fileImageValueDebit);
					System.out.println("Credit and Debit ItemType value is correct");
					retrunItemValue.add(fileImageValue);
					retrunItemValue.add(fileImageValueDebit);
				}
			  else
				  {
					  System.out.println("Issue found in ISO development!!!");
				  }
				  
				  
		  }
		  else {
			  
				  
				  if(fileImageValue.equals("CRDI") && fileImageValueDebit.equals("RTPI")){
					  if(aixmlCashItemCredit.equals("0") && aixmlCashItemDebit.equals("0")){
						System.out.println(" cashItem value is 0 and credit item value is "+fileImageValue);
						System.out.println(" cashItem value is 0 and debit item value is "+fileImageValueDebit);
						System.out.println("Credit and Debit ItemType value is correct");
						retrunItemValue.add(fileImageValue);
						retrunItemValue.add(fileImageValueDebit);
					}
				  else
				  {
					  System.out.println("Issue found in ISO development!!!");
				  }
				  
			  }
		
		  }
		  
			
	 }
		 
		 }
	
	// return fileImageValue;
	 return retrunItemValue;
}

public static ArrayList<String> getItemTypeDebitOutClearingOtherChannelValue(String aixmlCreditItemVal,String aixmlDebitItemval,String aixmlCashItemCredit,String aixmlCashItemDebit){
	String fileImageValue = null ,fileImageValueDebit= null;
	ArrayList<String> retrunItemValue=new ArrayList<>();
	 Map<String,String> mapTagValuesFromFile =getISOCreditDebitItemTypeTagValue(templateFilePath, fileNameICN);
	// Map<String,String> mapTagValuesFromResultSet 
	 Map<String,String> mapTagValuesDebitFromFile =getISODebitItemTypeTagValue(templateFilePath, fileNameICN);
	 
	
	 for(Map.Entry<String,String> entry:mapTagValuesFromFile.entrySet()){
		 String fileItemId = entry.getKey();
		 fileImageValue =entry.getValue();
		 for(Map.Entry<String,String> entrydeit:mapTagValuesDebitFromFile.entrySet()){
		 
		
		  fileImageValueDebit =entrydeit.getValue();
		  System.out.println("ISO CreditItem value :"+fileImageValue);
		  System.out.println("ISO DebitItem value :"+fileImageValueDebit);
		// String getImageValueFromResultSetByFileItemId = mapTagValuesFromResultSet.get(fileItemId);		
		/* if(fileCreditItemValue.equals(aixmlCreditItemVal) && fileImageValueDebit.equals(aixmlDebitItemval)){
			 
		 }*/
			  if(fileImageValue.equals("CRDI") && fileImageValueDebit.equals("ITPI")){
				  if(aixmlCashItemCredit.equals("1") && aixmlCashItemDebit.equals("1")){
					System.out.println(" cashItem value is 1 and credit item value is "+fileImageValue);
					System.out.println(" cashItem value is 1 and debit item value is "+fileImageValueDebit);
					System.out.println("Credit and Debit ItemType value is correct");
					retrunItemValue.add(fileImageValue);
					retrunItemValue.add(fileImageValueDebit);
				}
			  else
				  {
					  System.out.println("Issue found in ISO development!!!");
				  }
				  
				  
		  }
		  else {
			  
				  
				  if(fileImageValue.equals("CRDI") && fileImageValueDebit.equals("RTPI")){
					  if(aixmlCashItemCredit.equals("0") && aixmlCashItemDebit.equals("0")){
						System.out.println(" cashItem value is 0 and credit item value is "+fileImageValue);
						System.out.println(" cashItem value is 0 and debit item value is "+fileImageValueDebit);
						System.out.println("Credit and Debit ItemType value is correct");
						retrunItemValue.add(fileImageValue);
						retrunItemValue.add(fileImageValueDebit);
					}
				  else
				  {
					  System.out.println("Issue found in ISO development!!!");
				  }
				  
			  }
		
		  }
		  
			
	 }
		 
		 }
	
	// return fileImageValue;
	 return retrunItemValue;
}


}
