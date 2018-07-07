package com.ics.fred.testScenarios;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.w3c.dom.NodeList;
import com.ics.fred.common.FREDXMLValidation;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class CoreICNValidation extends GenericMethodUtilis{

	List<String> listOfMandatory = new ArrayList<String>();
	
	//XPATH Declarations
	static String icnCoreBusinessDate ="//Core/BusinessDate";
	static String icnExtractId ="//Core/ExtractId";
	static String icnCorePostingExtractIdXPATH="//Core/PostingExtractId";
	static String icnCoreProcessingParticipantIdXPATH="//Core/ProcessingParticipantId";
	static String icnCoreExtMessageTypeXPATH="//Core/ExtMessageType";
	static String icnCoreIntMessageTypeXPATH="//Core/IntMessageType";
	static String icnCoreMessageSourceXPATH="//Core/MessageSource";
	static String icnCoreMessageDestinationXPATH="//Core/MessageDestination";
	static String icnCoreRecordCountsXPATH="//Core/RecordCounts";
	
	 static String businessDateTagName = "BusinessDate";
	 static String extractIdTagName = "ExtractId";
	 static String intMessageTypeTagName = "IntMessageType";
	 static String processingParticipantIdTagName = "ProcessingParticipantId";
	 static String extMessageTypeTagName = "ExtMessageType";
	 static String messageSourceTagName = "MessageSource";	
	 static String recordCountsTagName="RecordCounts";
	 static String messageDestinationTagName="MessageDestination";
	 String xsdFileCoreNodeTagName="Core";
	 static String businessDateNodeVal,intMessageTypeNodeVal,processingParticipantIdNodeVal,extMessageTypeNodeVal,messageSourceNodeVal;
	 static String extractIDNodeVal,recordCountsNodeVal,messageDestinationNodeVal;
	// public static String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
	//	public static String ICNFileName="ICNOutput.xml";
	//	String ICNFilepath ="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\ICNOutput.xml";
		
	
 //
 	
	//private static final String businessDateTagName = null;

	//public static void main(String[] args) throws Exception {
	//@Test
	public static void validateICNWithIDSSpecification(String excelFilePath,String ICNFilepath,String ICNFileName) throws Exception {
		// TODO Auto-generated method stub

		//icnCoreBusinessDate;
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		
		Workbook wrkBk = new XSSFWorkbook(inputStream);
		int index = wrkBk.getSheetIndex("Core");
		System.out.println(index);
		
		Sheet fsheet = wrkBk.getSheetAt(index);
		Iterator<Row> iterator = fsheet.iterator();
		List<String> listTag = new ArrayList<String>();
		
		List<Tags1> listOfTag = new ArrayList<>();
		
		while(iterator.hasNext()){
			Tags1 tag = new Tags1();
			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			int rowIndex = nextRow.getRowNum();
			int count=1;
		
			if(rowIndex>0){
				while(cellIterator.hasNext()){
					Cell cell = cellIterator.next();
					System.out.println("cell :"+cell);
					switch(count){
					case 1:
						tag.setRootNode(cell.getRichStringCellValue().toString());
						System.out.println("RootTag val :"+cell.getRichStringCellValue().toString());
						count++;
						break;

					case 2:
						//listTag.add(cell.getRichStringCellValue.toString());
						tag.setCoreChildNode(cell.getRichStringCellValue().toString());
						System.out.println("ChildNode Tag val 1:"+cell.getRichStringCellValue().toString());
						count++;
						break;
					case 3:
						tag.setOccurs(cell.getRichStringCellValue().toString());
						count++;
						break;
					case 4:
						tag.setFixedVar(cell.getRichStringCellValue().toString());
						count++;
						break;
					case 5:
						tag.setType(cell.getRichStringCellValue().toString());
						count++;
						break;
					case 6:
						int len = (int)cell.getNumericCellValue();
						tag.setLength(Integer.toString(len));
						/*String len = cell.getRichStringCellValue().toString();
						tag.setLength(len);
						*/
						count++;
						break;
					}
					
				}
				listOfTag.add(tag);
				System.out.println("listOfTag :"+listOfTag+"  listOfTag.add(tag)"+listOfTag.add(tag));
			}
		}
		wrkBk.close();
		inputStream.close();
	
		
		String coreChildNode,rootNode;
		String occurs;
		String type,fixedVar,length;
		int i=0;
		
		for(Tags1 tags:listOfTag){
			System.out.println("listOfTag :"+listOfTag);
			coreChildNode = tags.getCoreChildNode();
			rootNode =tags.getRootNode();
			String coreName;
			occurs=tags.getOccurs();
			type=tags.getType();
			length=tags.getLength();
			fixedVar=tags.getFixedVar();
			if(null!=rootNode){
				getRootNodeCoreValidation(rootNode,ICNFilepath,ICNFileName);
				if(null!=coreChildNode){
					switch(coreChildNode){
						case "BusinessDate":
								businessDateNodeVal=getCoreChildNodeBuisnessDateValidation(coreChildNode,ICNFilepath,ICNFileName);
								getCoreSubTagsOccuranceValidation(coreChildNode,occurs,businessDateNodeVal);
								System.out.println("*****As per IDS version 8, ChildCoreNode BusinessDate DataType in ICN is :"+type);
								System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);
								System.out.println("Lenght validation is not required for BusinessDate in IDS v8 "+length);
							//	getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal);
						
								break;
				
						case "ExtractId":
								extractIDNodeVal = getCoreChildNodeExtractIdValidation(coreChildNode,ICNFilepath,ICNFileName);
								getCoreSubTagsExtractIdOccuranceValidation(coreChildNode,occurs,extractIDNodeVal);
								getCoreSubTagExtractIdLenghtValidation(coreChildNode,length,extractIDNodeVal);
						
								break;
						case "ProcessingParticipantId":
							processingParticipantIdNodeVal = getCoreChildNodeProcessingParticipantIdValidation(coreChildNode,ICNFilepath,ICNFileName);
							getCoreSubTagsProcessingParticipantIDOccuranceValidation(coreChildNode,occurs,processingParticipantIdNodeVal);
							getCoreSubTagPPartcipantIdLenghtValidation(coreChildNode,length,processingParticipantIdNodeVal);
					
							break;
						case "ExtMessageType":
							try{
							extMessageTypeNodeVal = getCoreChildNodeExtMsgTypeValidation(coreChildNode,ICNFilepath,ICNFileName);
							getCoreSubTagsExtMsgTypeOccuranceValidation(coreChildNode,occurs,extMessageTypeNodeVal);
							getCoreSubTagExtMsgTypeLenghtValidation(coreChildNode,length,extMessageTypeNodeVal);
							}
							catch(Exception e){
								e.getMessage();
								validationErrorInformation(coreChildNode+" Tag is not present in ICN file which should be present as per IDSv8"+e.getMessage());
							}
							break;
						case "IntMessageType":
							try{
							intMessageTypeNodeVal = getCoreChildNodeIntMsgTypeValidation(coreChildNode,ICNFilepath,ICNFileName);
							getCoreSubTagsIntMsgTypeOccuranceValidation(coreChildNode,occurs,intMessageTypeNodeVal);
							getCoreSubTagIntMsgTypeLenghtValidation(coreChildNode,length,intMessageTypeNodeVal);
							}
							catch(Exception e){
								e.getMessage();
								validationStepInformation(e.getMessage());
								validationErrorInformation(coreChildNode+" Tag is not present in ICN file which should be present as per IDSv8"+e.getMessage());
							}
							
							break;
						case "MessageSource":
							messageSourceNodeVal = getCoreChildNodeMsgSrcValidation(coreChildNode,ICNFilepath,ICNFileName);
							getCoreSubTagsMsgSrcOccuranceValidation(coreChildNode,occurs,messageSourceNodeVal);
							getCoreSubTagMsgSrcLenghtValidation(coreChildNode,length,messageSourceNodeVal);
					
							break;
						case "MessageDestination":
							messageDestinationNodeVal = getCoreChildNodeMsgDestValidation(coreChildNode,ICNFilepath,ICNFileName);
							getCoreSubTagsMsgDestOccuranceValidation(coreChildNode,occurs,messageDestinationNodeVal);
							getCoreSubTagMsgDestLenghtValidation(coreChildNode,length,messageDestinationNodeVal);
					
							break;
						case "RecordCounts":
							recordCountsNodeVal = getCoreChildNodeRecrdCntsValidation(coreChildNode,ICNFilepath,ICNFileName);
							getCoreSubTagsRecordCntsOccuranceValidation(coreChildNode,occurs,recordCountsNodeVal);
							getCoreSubTagRecordCntsLenghtValidation(coreChildNode,length,recordCountsNodeVal);
					
							break;
								
			}
				
			
				}
				
			/*	businessDateNodeVal=getCoreChildNodeBuisnessDateValidation(coreChildNode);
				extractIDNodeVal = getCoreChildNodeExtractIdValidation(coreChildNode);
				processingParticipantIdNodeVal = getCoreChildNodeProcessingParticipantIdValidation(coreChildNode);
				extMessageTypeNodeVal = getCoreChildNodeExtMsgTypeValidation(coreChildNode);
				intMessageTypeNodeVal = getCoreChildNodeIntMsgTypeValidation(coreChildNode);
				messageSourceNodeVal = getCoreChildNodeMsgSrcValidation(coreChildNode);
				messageDestinationNodeVal = getCoreChildNodeMsgDestValidation(coreChildNode);
				recordCountsNodeVal = getCoreChildNodeRecrdCntsValidation(coreChildNode);
				
					getCoreSubTagsOccuranceValidation(coreChildNode,occurs,businessDateNodeVal,extractIDNodeVal,processingParticipantIdNodeVal,intMessageTypeNodeVal,
						extMessageTypeNodeVal,messageSourceNodeVal,recordCountsNodeVal,messageDestinationNodeVal);
				System.out.println("*****As per IDS version 8, ChildCoreNode BusinessDate DataType in ICN is :"+type);
				System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);
				getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal,extractIDNodeVal,processingParticipantIdNodeVal,intMessageTypeNodeVal,
						extMessageTypeNodeVal,messageSourceNodeVal,recordCountsNodeVal,messageDestinationNodeVal);
			*/
		
				}		
			
/*				occurs=tags.getOccurs();
				if(null!=occurs){
					getCoreSubTagsOccuranceValidation(coreChildNode,occurs,businessDateNodeVal,extractIDNodeVal,processingParticipantIdNodeVal,intMessageTypeNodeVal,
							extMessageTypeNodeVal,messageSourceNodeVal,recordCountsNodeVal,messageDestinationNodeVal);
				
				}
				
				type=tags.getType();
				if(null!=type){
				System.out.println("*****As per IDS version 8, ChildCoreNode BusinessDate DataType in ICN is :"+type);
				}
				
				fixedVar=tags.getFixedVar();
				if(null!=fixedVar){
					System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);


				
				}
				length=tags.getLength();
				if(null!=length){
					System.out.println("Length validation is not required for BuisnessDate as per IDS v8."+length);
					System.out.println("Length validation is required for ExtractID as per IDS v8."+length);
					getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal,extractIDNodeVal,processingParticipantIdNodeVal,intMessageTypeNodeVal,
							extMessageTypeNodeVal,messageSourceNodeVal,recordCountsNodeVal,messageDestinationNodeVal);
				
					
				}*/
		
		}
		
		
	}
	
	public static Map<String, String> getXMLNodeNameByXPATH(String filePath, String fileName, String xPath) throws Exception{

		System.out.println("ICN : "+ filePath+fileName);
		Document doc = getParsedXMLData(new File(filePath+fileName));

		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(xPath);
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		Map<String, String> nodeValue = new LinkedHashMap<String, String>();
		Map<String, String> nodeName = null;
		Set<String> nodeKey = null;
		String nodeName1=null;

		for (int i=0;i<nodes.getLength();i++){
			//System.out.println(nodes.item(i).getTextContent());
			nodeValue.put(nodes.item(i).getNodeName(), nodes.item(i).getTextContent());
			// nodeName =nodes.item(i).getNodeName();
		//nodeName = nodeValue.entrySet().iterator().next().getKey();
			//nodeValue
		//	nodeKey = nodeValue.keySet();
		//	nodeKey.iterator().next().
		//	System.out.println("nodeKey :"+nodeKey);
		}
		return nodeValue;
	}
	
	
	public static String getMapKey(Map<String,String> strNodeVal){
		String obj = null;
		for(Map.Entry<String,String> map:strNodeVal.entrySet()){
		 obj = map.getKey();
	}
	return obj;
	}
	
	
	/*Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,"//Entities/Entity/EntityId");
	//for(int i=0;i<coreNodeVal.size();i++){
	coreName = coreNodeVal.entrySet().iterator().next().getKey();
	System.out.println("coreNodeVal :"+coreName);
	boolean flag =Component.verifyEquals(coreName, "EntityId", "Core tagname is as per IDSv8");
//	System.out.println("Flag :"+i+flag);
//	}
	
	List<String> rNodeList =getNodeListName(coreNodeVal,"Core");
	System.out.println("rNodeList :"+rNodeList);
	
	
	//boolean flag =Component.verifyEquals(coreName, "EntityId", "Core tagname is as per IDSv8");
	System.out.println("Flag :"+flag);
*/
	public static void getRootNodeCoreValidation(String rootNode,String ICNFilepath,String ICNFileName) throws Exception{
		
		System.out.println("For loop RootNode Core :"+rootNode);
		//now read xml and compare here
		//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
		System.out.println("****************************************************************");
		System.out.println("Validating RootNode Core is present in ICN as per IDS version 8 :"+rootNode);
		System.out.println("****************************************************************");
		Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,"//Core");
		String nodeKey = getMapKey(coreNodeVal);
		//coreName= coreNodeVal.toString();
		//for(int i=0;i<coreNodeVal.size();i++){
	//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
		System.out.println("coreNodeVal :"+nodeKey);
		boolean flag =Component.verifyEquals(nodeKey, rootNode, "Core tagname is as per IDSv8");			
		System.out.println("Validation is performed for CoreTag :"+flag);
		
	}
	
	public static String getCoreChildNodeBuisnessDateValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception {
		// TODO Auto-generated method stub
	//	excelReader();
		String bdateNodeVal=null;
		System.out.println("****************************************************************");
		System.out.println("Validating ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 :"+coreChildNode);
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+ coreChildNode +" ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 ");
		validationStepInformation("*********************************************************************************************************************");
		
		//now read xml and compare here
		//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
		//BusinessDate Validation 
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCoreBusinessDate);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreBusinessDate);
		bdateNodeVal =getNodeListValues(dateKeyVal1,businessDateTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "BusinessDate tagname is as per IDSv8");
		if(flagb==true)
			System.out.println("BusinessDate Tag is present under CoreTag in ICN file as per IDS v8 :");
		else
			System.out.println("BusinessDate Tag is not present under CoreTag in ICN file as per IDS v8 :");
		
		//for(int i=0;i<coreNodeVal.size();i++){
		//String bdName = bdateNode.entrySet().iterator().next().getKey();
		
		//ExtractID Validation
		//getCoreChildNodeExtractIdValidation(String coreChildNode);
		publishResults(flagb,dateKey, coreChildNode, "BusinessDate tagname is as per IDSv8");
		return bdateNodeVal;
	}
	
	public static String getCoreChildNodeExtractIdValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception{
		
		System.out.println("****************************************************************");
		System.out.println("Validating Extract ID ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 :"+coreChildNode);
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+ coreChildNode +" ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 ");
		validationStepInformation("*********************************************************************************************************************");
		
		String extractIdNodeVal;
		//now read xml and compare here
		//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
		Map<String, String> extractIdKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnExtractId);
		Map<String, String> extractIdKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnExtractId);
		String extractIdKey =getMapKey(extractIdKeyValue);
		System.out.println("ExtractId Key:"+extractIdKey+"::ExtractId KeyValue Pair:"+extractIdKeyValue);
		
		extractIdNodeVal =getNodeListValues(extractIdKeyValue1,extractIdTagName).get(0);
		System.out.println("extractIdNodeVal :"+extractIdNodeVal);
		boolean flagb =Component.verifyEquals(extractIdKey, coreChildNode, "ExtractId tagname is as per IDSv8");
		if(flagb==true)
			System.out.println("ExtractId Tag is present under CoreTag in ICN file as per IDS v8 :");
		else
			System.out.println("ExtractId Tag is not present under CoreTag in ICN file as per IDS v8 :");
		
		//for(int i=0;i<coreNodeVal.size();i++){
		//String bdName = bdateNode.entrySet().iterator().next().getKey();
		publishResults(flagb,extractIdKey, coreChildNode, "ExtractId tagname is as per IDSv8");
		return extractIdNodeVal;
		
	}
	
public static String getCoreChildNodeProcessingParticipantIdValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception{
		
		System.out.println("****************************************************************");
		System.out.println("Validating ProcessingParticipantId ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 :"+coreChildNode);
		System.out.println("****************************************************************");
		String processingParticpntIdNodeVal;
		//now read xml and compare here
		//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
		Map<String, String> processingParticpntIdKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCoreProcessingParticipantIdXPATH);
		String processingParticpntIdKey =getMapKey(processingParticpntIdKeyValue);
		System.out.println("ProcessingParticipantId Key:"+processingParticpntIdKey+"::ProcessingParticipantId KeyValue Pair:"+processingParticpntIdKeyValue);
		Map<String, String> processingParticpntIdKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreProcessingParticipantIdXPATH);
		processingParticpntIdNodeVal =getNodeListValues(processingParticpntIdKeyValue1,processingParticipantIdTagName).get(0);
		System.out.println("processingParticpntIdNodeVal :"+processingParticpntIdNodeVal);
		boolean flagb =Component.verifyEquals(processingParticpntIdKey, coreChildNode, "ProcessingParticipantId tagname is as per IDSv8");
		if(flagb==true)
			System.out.println("ProcessingParticipantId Tag is present under CoreTag in ICN file as per IDS v8 :");
		else
			System.out.println("ProcessingParticipantId Tag is not present under CoreTag in ICN file as per IDS v8 :");
		
		//for(int i=0;i<coreNodeVal.size();i++){
		//String bdName = bdateNode.entrySet().iterator().next().getKey();
		publishResults(flagb,processingParticpntIdKey, coreChildNode, "ProcessingParticipantId tagname is as per IDSv8");
		return processingParticpntIdNodeVal;
		
	}

public static String getCoreChildNodeIntMsgTypeValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception{
	
	System.out.println("****************************************************************");
	System.out.println("Validating IntMessageType ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
	
	String intMsgTypeNodeVal;
	//now read xml and compare here
	//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
	Map<String, String> intMsgTypeKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCoreIntMessageTypeXPATH);
	String intMsgTypeKey =getMapKey(intMsgTypeKeyValue);
	System.out.println("IntMessageType Key:"+intMsgTypeKey+"::IntMessageType KeyValue Pair:"+intMsgTypeKeyValue);
	Map<String, String> intMsgTypeKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreIntMessageTypeXPATH);
	intMsgTypeNodeVal =getNodeListValues(intMsgTypeKeyValue1,intMessageTypeTagName).get(0);
	System.out.println("intMsgTypeNodeVal :"+intMsgTypeNodeVal);
	boolean flagb =Component.verifyEquals(intMsgTypeKey, coreChildNode, "IntMessageType tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("IntMessageType Tag is present under CoreTag in ICN file as per IDS v8 :");
	else
		System.out.println("IntMessageType Tag is not present under CoreTag in ICN file as per IDS v8 :");
	
	//for(int i=0;i<coreNodeVal.size();i++){
	//String bdName = bdateNode.entrySet().iterator().next().getKey();
	publishResults(flagb,intMsgTypeKey, coreChildNode, "IntMessageType tagname is as per IDSv8");
	return intMsgTypeNodeVal;	
}

public static String getCoreChildNodeExtMsgTypeValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception{
	
	System.out.println("****************************************************************");
	System.out.println("Validating ExtMessageType ID ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
	
	
	String extMsgTypeNodeVal;
	//try{
	//now read xml and compare here
	//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
	Map<String, String> extMsgTypeKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCoreExtMessageTypeXPATH);
	Map<String, String> extMsgTypeKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreExtMessageTypeXPATH);
	String extMsgTypeKey =getMapKey(extMsgTypeKeyValue);
	/*if(extMsgTypeKey.isEmpty()){
		extMsgTypeKey=null;
		publishResults(extMsgTypeKey.isEmpty(),(extMsgTypeKey.isEmpty())?coreChildNode+" Tag is not present in ICN with tagName "+extMsgTypeKey+" which is mandatory field as per IDS version 8":"IssueFound!!",coreChildNode+" Tag is not present in ICN with tagName "+extMsgTypeKey+" which is mandatory field as per IDS version 8","ExtMessageType tagname in ICN file is as per IDSv8");
	}*/
	System.out.println("ExtMessageType Key:"+extMsgTypeKey+"::ExtMessageType KeyValue Pair:"+extMsgTypeKeyValue);
	
	extMsgTypeNodeVal =getNodeListValues(extMsgTypeKeyValue1,extMessageTypeTagName).get(0);

	System.out.println("extMsgTypeNodeVal :"+extMsgTypeNodeVal);
	boolean flagb =Component.verifyEquals(extMsgTypeKey, coreChildNode, "ExtMessageType tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("ExtMessageType Tag is present under CoreTag in ICN file as per IDS v8 :");
	else
		System.out.println("ExtMessageType Tag is not present under CoreTag in ICN file as per IDS v8 :");
	
	//for(int i=0;i<coreNodeVal.size();i++){
	//String bdName = bdateNode.entrySet().iterator().next().getKey();
	publishResults(flagb,extMsgTypeKey, coreChildNode, "ExtMessageType tagname in ICN file is as per IDSv8");
	
	return extMsgTypeNodeVal;
	
}

public static String getCoreChildNodeMsgSrcValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception{
	
	System.out.println("****************************************************************");
	System.out.println("Validating "+ coreChildNode +" ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8");
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
	
	String msgSrcNodeVal;
	//now read xml and compare here
	//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
	Map<String, String> msgSrcKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCoreMessageSourceXPATH);
	Map<String, String> msgSrcKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreMessageSourceXPATH);
	String msgSrcKey =getMapKey(msgSrcKeyValue);
	System.out.println("MessageSource Key:"+msgSrcKey+"::MessageSource KeyValue Pair:"+msgSrcKeyValue);
	msgSrcNodeVal =getNodeListValues(msgSrcKeyValue1,messageSourceTagName).get(0);
	System.out.println("msgSrcNodeVal :"+msgSrcNodeVal);
	boolean flagb =Component.verifyEquals(msgSrcKey, coreChildNode, "MessageSource tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("MessageSource Tag is present under CoreTag in ICN file as per IDS v8 :");
	else
		System.out.println("MessageSource Tag is not present under CoreTag in ICN file as per IDS v8 :");
	
	//for(int i=0;i<coreNodeVal.size();i++){
	//String bdName = bdateNode.entrySet().iterator().next().getKey();
	publishResults(flagb,msgSrcKey, coreChildNode, "MessageSource tagname is as per IDSv8");
	return msgSrcNodeVal;
	
}

public static String getCoreChildNodeMsgDestValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception{
	
	System.out.println("*********************************************************************************************************************");
	System.out.println("Validating MessageDestination ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("*********************************************************************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating MessageDestination ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 :"+coreChildNode);
	validationStepInformation("*********************************************************************************************************************");
	
	String msgDestNodeVal;
	//now read xml and compare here
	//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
	Map<String, String> msgDestKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCoreMessageDestinationXPATH);
	Map<String, String> msgDestKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreMessageDestinationXPATH);
	String msgDestKey =getMapKey(msgDestKeyValue);
	System.out.println("MessageDestination Key:"+msgDestKey+"::MessageDestination KeyValue Pair:"+msgDestKeyValue);	
	msgDestNodeVal =getNodeListValues(msgDestKeyValue1,messageDestinationTagName).get(0);
	System.out.println("msgDestNodeVal :"+msgDestNodeVal);
	boolean flagb =Component.verifyEquals(msgDestKey, coreChildNode, "MessageDestination tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("MessageDestination Tag is present under CoreTag in ICN file as per IDS v8 :");
	else
		System.out.println("MessageDestination Tag is not present under CoreTag in ICN file as per IDS v8 :");
	
	//for(int i=0;i<coreNodeVal.size();i++){
	//String bdName = bdateNode.entrySet().iterator().next().getKey();
	publishResults(flagb,msgDestKey, coreChildNode, "MessageDestination tagname is as per IDSv8");
	return msgDestNodeVal;
}


public static String getCoreChildNodeRecrdCntsValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception{
	
	System.out.println("****************************************************************");
	System.out.println("Validating RecordCounts ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("****************************************************************");
	validationStepInformation("Validating RecordCounts ChildCoreNodes is populating under CoreTag in ICN as per IDS version 8 :"+coreChildNode);
	validationStepInformation("****************************************************************");
	
	String rcNodeVal;
	//now read xml and compare here
	//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
	Map<String, String> rcKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCoreRecordCountsXPATH);
	Map<String, String> rcKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCoreRecordCountsXPATH);
	String recordCntsKey =getMapKey(rcKeyValue);
	System.out.println("RecordCounts Key:"+recordCntsKey+"::RecordCounts KeyValue Pair:"+rcKeyValue);
	rcNodeVal =getNodeListValues(rcKeyValue1,recordCountsTagName).get(0);
	System.out.println("extractIdNodeVal :"+rcNodeVal);
	System.out.println("RecordCOunt childNode"+coreChildNode);
	boolean flagb =Component.verifyEquals(recordCntsKey, coreChildNode, "RecordCounts tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("RecordCounts Tag is present under CoreTag in ICN file as per IDS v8 :");
	else
		System.out.println("RecordCounts Tag is not present under CoreTag in ICN file as per IDS v8 :");
	
	//for(int i=0;i<coreNodeVal.size();i++){
	//String bdName = bdateNode.entrySet().iterator().next().getKey();
	publishResults(flagb,recordCntsKey, coreChildNode, "RecordCounts tagname is as per IDSv8");
	return rcNodeVal;
	
}


	public static void getCoreSubTagsOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal) throws Exception {
	//	getCoreSubTagsOccuranceValidation(occurs,businessDateNodeVal,extractIDNodeVal,processingParticipantIdNodeVal,intMessageTypeNodeVal,
		//extMessageTypeNodeVal,messageSourceNodeVal,recordCountsNodeVal,messageDestinationNodeVal);
		
		System.out.println("*****ChildCoreNodes Occurance in ICN file validation starts here*********");
		validationStepInformation("*****ChildCoreNodes Occurance in ICN file validation starts here************");
	/*	Element element = null;
		ValidateE031ICNCoreSubTags.getBusinessDateICNValidation(ICNFilepath,ICNFileName,element);
	}*/if(bdateNodeVal!=null){
		System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for BusinessDate in ICN is :"+occurs);
		validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
		 boolean assertFlagCheck = FREDXMLValidation.validateBusinessDateDatabaseFormat(bdateNodeVal);
		 boolean flag = Component.verifyTrue(assertFlagCheck, "BusinessDate date type and data format validation performed.");
		 System.out.println("BusinessDate date type and data format"+flag);
		}
	else
		{
		System.out.println("BusinessDate should not be null!!");
		}
	publishResults(bdateNodeVal!=null,(bdateNodeVal!=null)?"BusinessDate date type and data format "+bdateNodeVal+" validation performed":"BusinessDate tag is null","BusinessDate date type and data format "+bdateNodeVal+" validation performed","BusinessDate date type and data format "+bdateNodeVal+" validation performed");
	}
	public static void getCoreSubTagsExtractIdOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
	//ExtractID occurrence validation
	System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for ExtractID in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
		boolean flag1 = Component.verifyTrue(extractIDNodeVal!=null, "ExtractID tag containing value");
		System.out.println("ExtractID tag is containing value :"+flag1);
		publishResults(extractIDNodeVal!=null,(extractIDNodeVal!=null)?"ExtractID tag is containing value":"ExtractID tag is null","ExtractID tag is containing value","ExtractID Occurence validation");
	}
	
	public static void getCoreSubTagsProcessingParticipantIDOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
		//ProcessingParticipantID
		System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
		validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
		boolean flag2 = Component.verifyTrue(processingParticipantIdNodeVal!=null, "processingParticipantIdNodeVal tag containing value");
		System.out.println("processingParticipantIdNodeVal tag is containing value :"+flag2);
		//publishResults(Component.verifyTrue(processingParticipantIdNodeVal!=null, "processingParticipantIdNodeVal tag containing value"),(processingParticipantIdNodeVal!=null)?"processingParticipantIdNodeVal tag is containing value":"ExtractID tag is null","ExtractID tag is containing value","ExtractID Occurence validation");
		publishResults(processingParticipantIdNodeVal!=null,(processingParticipantIdNodeVal!=null)?"processingParticipantIdNodeVal tag is containing value":"ProcessingParticipantId tag is null","processingParticipantIdNodeVal tag is containing value","processingParticipantIdNodeVal Occurence validation");
	}
	
	public static void getCoreSubTagsExtMsgTypeOccuranceValidation(String coreChildNode,String occurs,String extMessageTypeNodeVal){
	//ExtMsgType
		System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
		validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
		boolean flag4 = Component.verifyTrue(extMessageTypeNodeVal!=null, "extMessageTypeNodeVal tag containing value");
		System.out.println("extMessageTypeNodeVal tag is containing value :"+flag4);
		//publishResults(Component.verifyTrue(extMessageTypeNodeVal!=null, "extMessageTypeNodeVal tag containing value"),(extMessageTypeNodeVal!=null)?"ExtMessageType tag is containing value":"ExtMessageType tag is null","ExtMessageType tag is containing value","ExtMessageType Occurence validation");
		publishResults(extMessageTypeNodeVal!=null,(extMessageTypeNodeVal!=null)?"ExtMessageType tag is containing value":"ExtMessageType tag is null","ExtMessageType tag is containing value","ExtMessageType Occurence validation");
	}
	
	//IntMsgType
	public static void getCoreSubTagsIntMsgTypeOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
		System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
		validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
		boolean flag3 = Component.verifyTrue(intMessageTypeNodeVal!=null, "intMessageTypeNodeVal tag containing value");
		System.out.println("intMessageTypeNodeVal tag is containing value :"+flag3);
		publishResults(Component.verifyTrue(intMessageTypeNodeVal!=null, "intMessageTypeNodeVal tag containing value"),(intMessageTypeNodeVal!=null)?"IntMessageType tag is containing value":"IntMessageType tag is null","IntMessageType tag is containing value","IntMessageType Occurence validation");
	}
	
	//MsgSource
		public static void getCoreSubTagsMsgSrcOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
			System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag5 = Component.verifyTrue(messageSourceNodeVal!=null, "messageSourceNodeVal tag containing value");
		System.out.println("messageSourceNodeVal tag is containing value :"+flag5);
	publishResults(Component.verifyTrue(messageSourceNodeVal!=null, "messageSourceNodeVal tag containing value"),(messageSourceNodeVal!=null)?"MessageSource tag is containing value":"MessageSource tag is null","MessageSource tag is containing value","MessageSource Occurence validation");
		}
	
	//MsgDestimation
		public static void getCoreSubTagsMsgDestOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
			System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag6 = Component.verifyTrue(messageDestinationNodeVal!=null, "messageDestinationNodeVal tag containing value");
		System.out.println("messageDestinationNodeVal tag is containing value :"+flag6);
		publishResults(Component.verifyTrue(messageDestinationNodeVal!=null, "messageDestinationNodeVal tag containing value"),(messageDestinationNodeVal!=null)?"messageDestination tag is containing value":"messageDestination tag is null","messageDestination tag is containing value","messageDestination Occurence validation");
		}
		
	//Record Counts
		public static void getCoreSubTagsRecordCntsOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
			System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag7 = Component.verifyTrue(recordCountsNodeVal!=null, "recordCountsNodeVal tag containing value");
		System.out.println("recordCountsNodeVal tag is containing value :"+flag7);
		publishResults(Component.verifyTrue(recordCountsNodeVal!=null, "recordCountsNodeVal tag containing value"),(recordCountsNodeVal!=null)?"recordCounts tag is containing value":"recordCounts tag is null","recordCounts tag is containing value","recordCounts Occurence validation");
		}
	
	
	
	public static void getCoreSubTagExtractIdLenghtValidation(String coreChildNode,String length,String businessDateNodeVal){
		System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);	
		validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
		int extractIdLength=Integer.parseInt(length);
		boolean assertFlagCheck = FREDXMLValidation.validateExtractIdWithIDS(extractIDNodeVal,extractIdLength);
			 boolean bflag = Component.verifyTrue(assertFlagCheck, "ExtractId length validation performed.");
			 if(bflag){
				 System.out.println("ExtractId length correct as per IDS v8 :");
			 }
			 else
			 {
				 System.out.println("ExtractId value lenght is not as per IDS v8. ");
			 }
			 publishResults(bflag,(extractIdLength==26)?"ExtractID length "+extractIdLength+" in ICN is as per IDS v8":"ExtractID length "+extractIdLength+" in ICN validation got failed as per IDS v8","ExtractID length "+extractIdLength+" in ICN is as per IDS v8","ExtractID Occurence validation");
	}
		
			 //PP
	public static void getCoreSubTagPPartcipantIdLenghtValidation(String coreChildNode,String length,String businessDateNodeVal){
		System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
		validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
		boolean ppflag = Component.verifyTrue(FREDXMLValidation.validateProcessingParticipantId(processingParticipantIdNodeVal), "ProcessingParticipantId length validation performed.");
			 if(ppflag){
				 System.out.println("ProcessingParticipantId length correct as per IDS v8 :");
			 }
			 else
			 {
				 System.out.println("ProcessingParticipantId value lenght is not as per IDS v8. ");
			 }
			 publishResults(ppflag,(processingParticipantIdNodeVal.length()==Integer.parseInt(length.trim()))?"ProcessingParticipantId length "+processingParticipantIdNodeVal.length()+" in ICN is as per IDS v8":"ProcessingParticipantId length "+processingParticipantIdNodeVal.length()+" in ICN validation got failed as per IDS v8","ProcessingParticipantId length "+processingParticipantIdNodeVal.length()+" in ICN is as per IDS v8","ProcessingParticipantId Length validation");
			 
	}	 
			 //ExtMsgType
	public static void getCoreSubTagExtMsgTypeLenghtValidation(String coreChildNode,String length,String extMessageTypeNodeVal){
		System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
		validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
		System.out.println(Integer.parseInt(length)+"Lenth string conversion");
		boolean flagExtMsgType = Component.verifyTrue(FREDXMLValidation.validateExtMessageType(extMessageTypeNodeVal), "ExtMessageType length validation performed.");
			 if(flagExtMsgType){
				 System.out.println("ExtMessageType length correct as per IDS v8 :");
			 }
			 else
			 {
				 System.out.println("ExtMessageType value lenght is not as per IDS v8. ");
			 }
			 publishResults(((extMessageTypeNodeVal.length())==Integer.parseInt(length)),((extMessageTypeNodeVal.length())==Integer.parseInt(length))?"ExtMessageTypeNodeVal value length "+extMessageTypeNodeVal.length()+" in ICN is as per IDS v8":"extMessageTypeNodeVal length "+extMessageTypeNodeVal.length()+" in ICN validation got failed as per IDS v8","ExtMessageTypeNodeVal value length "+extMessageTypeNodeVal.length()+" in ICN is as per IDS v8","ExtMessageTypeNodeVal Length validation");
	}
			 //IntMsgType
			 public static void getCoreSubTagIntMsgTypeLenghtValidation(String coreChildNode,String length,String businessDateNodeVal){
				 System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
				 boolean flagIntMsgType = Component.verifyTrue(FREDXMLValidation.validateIntMessageType(intMessageTypeNodeVal), "IntMessageType length validation performed.");
			 if(flagIntMsgType){
				 System.out.println("IntMessageType length correct as per IDS v8 :");
			 }
			 else
			 {
				 System.out.println("IntMessageType value lenght is not as per IDS v8. ");
			 }
			 publishResults(((intMessageTypeNodeVal.length())<=Integer.parseInt(length)),((intMessageTypeNodeVal.length())<=Integer.parseInt(length))?"IntMessageTypeNodeVal value length "+intMessageTypeNodeVal.length()+" in ICN is as per IDS v8":"intMessageTypeNodeVal length "+messageSourceNodeVal.length()+" in ICN validation got failed as per IDS v8","IntMessageTypeNodeVal value length "+intMessageTypeNodeVal.length()+" in ICN is as per IDS v8","IntMessageTypeNodeVal Length validation");
			 }
			 //MsgSrc
			 public static void getCoreSubTagMsgSrcLenghtValidation(String coreChildNode,String length,String businessDateNodeVal){
				 System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
				 boolean flagMsgSrc = Component.verifyTrue(FREDXMLValidation.validateMessageSource(messageSourceNodeVal), "MessageSource length validation performed.");
			 if(flagMsgSrc){
				 System.out.println("MsgSource Validation length correct as per IDS v8 :");
			 }
			 else
			 {
				 System.out.println("MsgSource value lenght is not as per IDS v8. ");
			 }
			 publishResults(flagMsgSrc,((messageSourceNodeVal.length())<=Integer.parseInt(length))?"MessageSource value length "+messageSourceNodeVal.length()+" in ICN is as per IDS v8":"MessageSource length "+messageSourceNodeVal.length()+" in ICN validation got failed as per IDS v8","MessageSource value length "+messageSourceNodeVal.length()+" in ICN is as per IDS v8","MessageSource Length validation");
			 }
			 
			 //msgDest
			 public static void getCoreSubTagMsgDestLenghtValidation(String coreChildNode,String length,String businessDateNodeVal){
				 System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
				 boolean flagMsgDest = Component.verifyTrue(FREDXMLValidation.validateMessageDestination(messageDestinationNodeVal), "MessageDestination length validation performed.");
			 if(flagMsgDest){
				 System.out.println("MsgDestination length correct as per IDS v8 :");
			 }
			 else
			 {
				 System.out.println("MsgDestination value lenght is not as per IDS v8. ");
			 }
			 publishResults((messageDestinationNodeVal.length())<=Integer.parseInt(length),((messageDestinationNodeVal.length())<=Integer.parseInt(length))?"MessageDestination value length "+messageDestinationNodeVal.length()+" in ICN is as per IDS v8":"MessageDestination length "+messageDestinationNodeVal.length()+" in ICN validation got failed as per IDS v8","MessageDestination value length "+messageDestinationNodeVal.length()+" in ICN is as per IDS v8","MessageDestination Length validation");
			 }
			
			 //RecordCounts
			 public static void getCoreSubTagRecordCntsLenghtValidation(String coreChildNode,String length,String businessDateNodeVal){
				 System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
				 boolean rcLength =FREDXMLValidation.validateRecordCountsLength(recordCountsNodeVal);
			 boolean flagRC =Component.verifyTrue(rcLength,"RecordCounts length validation as per IDS v8"); 
					// Component.verifyTrue(FREDXMLValidation.validateRecordCountsLength(recordCountsNodeVal), "RecordCounts length validation performed.");
			// publishResults(flagRC,String.valueOf(rcLength),length,"RecordCounts length validation as per IDS v8");
			 if(flagRC){
				 System.out.println("RecordCounts length correct as per IDS v8 :");
			 }
			 else
			 {
				 System.out.println("RecordCounts value lenght is not as per IDS v8.");
			 }
			 publishResults(recordCountsNodeVal!=null,(recordCountsNodeVal!=null)?"RecordCounts Length in ICN file is"+length+"as per IDS v8":"RecordCounts length should not be 0 and gretaer than 8","RecordCounts Length in ICN file is"+length+"as per IDS v8","RecordCounts length validation as per IDS v8"); 
			}
			
}
