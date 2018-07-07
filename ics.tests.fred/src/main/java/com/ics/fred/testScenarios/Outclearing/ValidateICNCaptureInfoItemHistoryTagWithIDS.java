package com.ics.fred.testScenarios.Outclearing;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
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

import com.ics.fred.testScenarios.Tags1;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateICNCaptureInfoItemHistoryTagWithIDS  extends GenericMethodUtilis{

	static String icnCaptureInfoXpath ="//CaptureInfo";
	static String icnCaptureItemXpath ="//CaptureInfo/CaptureItem";
	static String icnCaptureItemItmHistory ="//CaptureInfo/CaptureItem/ItemHistory";
	static String icnCaptureItemItemHistoryAuditRevision=	"//CaptureInfo/CaptureItem/ItemHistory/AuditRevision";
	static String icnCaptureItemItemHistoryUserId=	"//CaptureInfo/CaptureItem/ItemHistory/UserId";
	static String icnCaptureItemItemHistoryTime=	"//CaptureInfo/CaptureItem/ItemHistory/Time";
	static String icnCaptureItemItemHistoryProcess=	"//CaptureInfo/CaptureItem/ItemHistory/Process";
	static String icnCaptureItemItemHistoryIsDeleted=	"//CaptureInfo/CaptureItem/ItemHistory/IsDeleted";
	static String icnCaptureItemItemHistoryGender=	"//CaptureInfo/CaptureItem/ItemHistory/Gender";
	static String icnCaptureItemItemHistoryReference=	"//CaptureInfo/CaptureItem/ItemHistory/Reference";
	static String icnCaptureItemItemHistoryAccount=	"//CaptureInfo/CaptureItem/ItemHistory/Account";
	static String icnCaptureItemItemHistorySortCode=	"//CaptureInfo/CaptureItem/ItemHistory/SortCode";
	static String icnCaptureItemItemHistoryTranCode=	"//CaptureInfo/CaptureItem/ItemHistory/TranCode";
	static String icnCaptureItemItemHistoryAmount=	"//CaptureInfo/CaptureItem/ItemHistory/Amount";

	static String captureInfoTagName="CaptureInfo";
	static String captureItemTagName="CaptureItem";
	static String itemHistoryTagName="ItemHistory";
	static String auditRevisionTagName="AuditRevision";
	static String userIdTagName="UserId";
	static String timeTagName="Time";
	static String processTagName="Process";
	static String isDeletedTagName="IsDeleted";
	static String genderTagName="Gender";
	static String referenceTagName="Reference";
	static String accountTagName="Account";
	static String sortcodeTagName="SortCode";
	static String trancodeTagName="TranCode";
	static String amountTagName="Amount";


	static String auditRevisionNodeVal,userIdNodeVal,timeNodeVal,processNodeVal,isDeletedNodeVal,genderNodeVal,referenceNodeVal;
	static String accountNodeVal,sortcodeNodeVal,trancodeNodeVal,amountNodeVal;
	
	
	public static void validateICNItemHistoryTagWithIDS(String excelFilePath,String icnFilepath,String icnFileName) throws Exception{
		
		FileInputStream inputStream = new FileInputStream(excelFilePath);
		
		Workbook wrkBk = new XSSFWorkbook(inputStream);
		int index = wrkBk.getSheetIndex("CaptureInfoCFItemHistory");
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
						System.out.println("RootTag CaptureInfo val :"+cell.getRichStringCellValue().toString());
						count++;
						break;

					case 2:
						tag.setCaptureInfoChildNode(cell.getRichStringCellValue().toString());
						System.out.println("CaptureInfoChildNode CaptureItem Tag val :"+cell.getRichStringCellValue().toString());
						count++;
						break;
						
					case 3:
						tag.setCaptureInfoChildNode1(cell.getRichStringCellValue().toString());
						System.out.println("CaptureInfoChildNode1 val :"+cell.getRichStringCellValue().toString()); //ItemHistory
						count++;
						break;
					case 4:
						tag.setCaptureInfoChildNode1(cell.getRichStringCellValue().toString());
						System.out.println("CaptureInfoChildNode2 val :"+cell.getRichStringCellValue().toString()); //AuditRevision, ,, so on
						count++;
						break;
					
					case 5:
						tag.setOccurs(cell.getRichStringCellValue().toString());
						count++;
						break;
					case 6:
						tag.setFixedVar(cell.getRichStringCellValue().toString());
						count++;
						break;
					case 7:
						tag.setType(cell.getRichStringCellValue().toString());
					//	int type = (int)cell.getNumericCellValue();
					//	tag.setType(Integer.toString(type));
						count++;
						break;
					case 8:
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

		String grpHdrNodeVal;
		String coreChildNode,rootNode,captureChildNote1,captureChildNote2,captureChildNote3,captureChildNote4;
		
		String occurs,captureChildNote;
		String type,fixedVar,length;
		int i=0;
		for(Tags1 tags:listOfTag){
			System.out.println("listOfTag :"+listOfTag);
			//coreChildNode = tags.getCoreChildNode();
			rootNode =tags.getRootNode();
			
			captureChildNote = tags.getCaptureInfoChildNode(); //CaptureInfoChildNode  CaptureItem
			captureChildNote1 =tags.getCaptureInfoChildNode1(); //CaptureInfoChildNode1 JOB, BTACH,BLOCK,APGIN....
			captureChildNote2=tags.getCaptureInfoChildNode2();
		//	captureChildNote3=tags.getCaptureInfoChildNode3();
		//	captureChildNote4=tags.getCaptureInfoChildNode4();
			
			occurs=tags.getOccurs();
			type=tags.getType();
			length=tags.getLength();
			fixedVar=tags.getFixedVar();
			if(null!=rootNode){
				getRootNodeValidation(rootNode,icnFilepath,icnFileName); //TxSetSubmissn
				if(null!=captureChildNote){
					System.out.println("captureChildNote "+captureChildNote);
						getCaptureInfoChildNodeValidation(captureChildNote,icnFilepath,icnFileName); //TxSet
						if(null!=captureChildNote1){
							System.out.println("captureChildNote "+captureChildNote);
							getCaptureInfoChildNode1Validation(captureChildNote1,icnFilepath,icnFileName); //DbtItm
							if(null!=captureChildNote2){
								System.out.println("captureChildNote "+captureChildNote);
								
								
								
							switch(captureChildNote2){
							//case "TxSetSubmissn":
							
							case "AuditRevision":
								auditRevisionNodeVal=getCaptureItemHistoryAuditRevisionNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistoryAuditRevisionTagOccurence(captureChildNote2,occurs,auditRevisionNodeVal);
								validateItemHistoryAuditRevisionTagLength(captureChildNote2,length,auditRevisionNodeVal);
								break;
							case "UserId":
								userIdNodeVal=getCaptureItemHistoryUserIdNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistoryUserIdTagOccurence(captureChildNote2,occurs,userIdNodeVal);
								validateItemHistoryUserIdTagLength(captureChildNote2,length,userIdNodeVal);
								break;
							case "Time":
								timeNodeVal=getCaptureItemHistoryTimeNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistoryTimeTagOccurence(captureChildNote2,occurs,timeNodeVal);
								validateItemHistoryTimeTagLength(captureChildNote2,length,timeNodeVal);
								break;
							
							case "Process":
								processNodeVal=getCaptureItemHistoryProcessNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistoryProcessTagOccurence(captureChildNote2,occurs,processNodeVal);
								validateItemHistoryProcessTagLength(captureChildNote2,length,processNodeVal);
								break;
							case "IsDeleted":
								isDeletedNodeVal=getCaptureItemHistoryIsDeletedNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistoryIsDeletedTagOccurence(captureChildNote2,occurs,isDeletedNodeVal);
								validateItemHistoryIsDeletedTagLength(captureChildNote2,length,isDeletedNodeVal);
								break;
							case "Gender":
								genderNodeVal=getCaptureItemHistoryGenderNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistoryGenderTagOccurence(captureChildNote2,occurs,genderNodeVal);
								validateItemHistoryGenderTagLength(captureChildNote2,length,genderNodeVal);
								break;
							case "Reference":
								referenceNodeVal=getCaptureItemHistoryReferenceNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistoryReferenceTagOccurence(captureChildNote2,occurs,referenceNodeVal);
								validateItemHistoryReferenceTagLength(captureChildNote2,length,referenceNodeVal);
								break;
							case "Account":
								accountNodeVal=getCaptureItemHistoryAccountNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistoryAccountTagOccurence(captureChildNote2,occurs,accountNodeVal);
								validateItemHistoryAccountTagLength(captureChildNote2,length,accountNodeVal);
								break;
							case "SortCode":
								sortcodeNodeVal=getCaptureItemHistorySortCodeNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistorySortCodeTagOccurence(captureChildNote2,occurs,sortcodeNodeVal);
								validateItemHistorySortCodeTagLength(captureChildNote2,length,sortcodeNodeVal);
								break;	
							case "TranCode":
								trancodeNodeVal=getCaptureItemHistoryTrancodeNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistoryTranCodeTagOccurence(captureChildNote2,occurs,trancodeNodeVal);
								validateItemHistoryTranCodeTagLength(captureChildNote2,length,trancodeNodeVal);
								break;	
							case "Amount":
								amountNodeVal=getCaptureItemHistoryAmountNodeVal(captureChildNote2,icnFilepath,icnFileName);
								validateItemHistoryAmountTagOccurence(captureChildNote2,occurs,amountNodeVal);
								validateItemHistoryAmountTagLength(captureChildNote2,length,amountNodeVal);
								break;	
								
							
			
		} // switch end
									
							}
							else{
								System.out.println("TxSet Tags are not present");
							}
		}
						else{
							System.out.println("ISO TransactionSetSubmission  tag is not present");
						}
		}
				}
				else
				{
					System.out.println("ISO TransactionSetSubmission tag is not present");
				}
		
		}
		
		

	}

	

	private static void validateItemHistoryAuditRevisionTagOccurence(String captureChildNote2, String occurs,
			String auditRevisionNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryAuditRevisionTagLength(String captureChildNote2, String length,
			String auditRevisionNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryUserIdTagOccurence(String captureChildNote2, String occurs,
			String userIdNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryUserIdTagLength(String captureChildNote2, String length,
			String userIdNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryTimeTagOccurence(String captureChildNote2, String occurs,
			String timeNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryTimeTagLength(String captureChildNote2, String length, String timeNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryProcessTagOccurence(String captureChildNote2, String occurs,
			String processNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryProcessTagLength(String captureChildNote2, String length,
			String processNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryIsDeletedTagOccurence(String captureChildNote2, String occurs,
			String isDeletedNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryIsDeletedTagLength(String captureChildNote2, String length,
			String isDeletedNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryGenderTagOccurence(String captureChildNote2, String occurs,
			String genderNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryGenderTagLength(String captureChildNote2, String length,
			String genderNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryReferenceTagOccurence(String captureChildNote2, String occurs,
			String referenceNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryReferenceTagLength(String captureChildNote2, String length,
			String referenceNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryAccountTagOccurence(String captureChildNote2, String occurs,
			String accountNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryAccountTagLength(String captureChildNote2, String length,
			String accountNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistorySortCodeTagOccurence(String captureChildNote2, String occurs,
			String sortcodeNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistorySortCodeTagLength(String captureChildNote2, String length,
			String sortcodeNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryTranCodeTagOccurence(String captureChildNote2, String occurs,
			String trancodeNodeVal2) {
		// TODO Auto-generated method stub
		
	}



	private static void validateItemHistoryTranCodeTagLength(String coreChildNode, String length,	String bdateNodeVal) {

		 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
		 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
		 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

		
	}



	private static void validateItemHistoryAmountTagOccurence(String coreChildNode, String length,	String bdateNodeVal) {

		 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
		 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
		 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

		
	}



	private static void validateItemHistoryAmountTagLength(String coreChildNode, String length,	String bdateNodeVal) {

		 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
		 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
		 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

		
	}



	private static String getCaptureItemHistoryAmountNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static String getCaptureItemHistoryTrancodeNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static String getCaptureItemHistorySortCodeNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static String getCaptureItemHistoryAccountNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static String getCaptureItemHistoryReferenceNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static String getCaptureItemHistoryGenderNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static String getCaptureItemHistoryIsDeletedNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static String getCaptureItemHistoryProcessNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static String getCaptureItemHistoryTimeNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static String getCaptureItemHistoryUserIdNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static String getCaptureItemHistoryAuditRevisionNodeVal(String coreChildNode, String icnFilepath,
			String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		return bdateNodeVal;
	}



	private static void getCaptureInfoChildNode1Validation(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItmHistory);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,itemHistoryTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
	
		
	}

	private static void getCaptureInfoChildNodeValidation(String coreChildNode, String icnFilepath,String icnFileName) throws Exception {
		System.out.println("****************************************************************");
		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemXpath);
		String dateKey =getMapKey(dateKeyVal);
		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemXpath);
		String bdateNodeVal = getNodeListValues(dateKeyVal1,captureItemTagName).get(0);
		System.out.println("bdateNodeVal :"+bdateNodeVal);
		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
			
		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		
		
	}

	private static void getRootNodeValidation(String rootNode, String icnFilepath, String icnFileName) throws Exception {
		System.out.println("For loop RootNode Core :"+rootNode);
		System.out.println("****************************************************************");
		System.out.println("Validating CaptureInfo is present in ICN as per IDS version 8 :"+rootNode);
		System.out.println("****************************************************************");
		
		validationStepInformation("****************************************************************");
		validationStepInformation("Validating CaptureInfo ChilNode is present in ICN as per IDS version 8 :"+rootNode);
		validationStepInformation("****************************************************************");
		validationStepInformation("icnCaptureInfoXpath "+icnCaptureInfoXpath);
		Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureInfoXpath);
		String nodeKey = getMapKey(coreNodeVal);
		//coreName= coreNodeVal.tostatic String();
		//for(int i=0;i<coreNodeVal.size();i++){
	//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
		System.out.println("coreNodeVal :"+nodeKey);
		boolean flag =Component.verifyEquals(nodeKey, rootNode, "Root tagname is as per IDSv8");			
		System.out.println("Validation is performed for RootTag :"+flag);
		validationStepInformation("Validation is performed for RootTag :"+flag);
		publishResults(flag,nodeKey, rootNode, "CaptureInfo tagname is as per IDSv8");
		
	}
	
	public static Map<String,String> getXMLNodeNameByXPATH(String filePath,String fileName,String xPath) throws Exception{

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
}
