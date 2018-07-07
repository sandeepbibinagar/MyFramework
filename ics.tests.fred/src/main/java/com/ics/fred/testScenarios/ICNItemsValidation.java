

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
	import org.testng.annotations.Test;
	import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;
	import com.ics.externalFactoryUtilis.ICSDBUtilis;
	import com.ics.fred.common.FREDXMLValidation;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;



	public class ICNItemsValidation extends GenericMethodUtilis{

		List<String> listOfMandatory = new ArrayList<String>();
		static String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
		//static String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\";
//		static String ICNFileName="ICNOutput.xml";
		static String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\MF01InputFolder\\";
		static String ICNFileName="TC_62948_DR_Actual.xml";
		
		//XPATH Declarations
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
		
		
		static String itemIdTagName = "ItemId";
		static String codelineTagName = "Codeline";
		static String serialTagName = "Serial";
		static String referenceTagName = "Reference";
		static String sortCodeTagName = "SortCode";
		static String accountTagName = "Account";
		static String trancodeTagName = "TranCode";	
		static String amountTagName="Amount";
		static String currencyTagName="Currency";
		static String imageTagName="Image";
		 static String itemIdNodeVal,codeLineNodeVal,currencyNodeVal,referenceNodeVal,trancodeNodeVal,serialNodeVal,sortCodeNodeVal,accountNodeVal;
			static String imageNodeVal,amountNodeVal;
		
		//private static final String businessDateTagName = null;

		//public static void main(String[] args) throws Exception {
		@Test
		public static void validateICNDebitClearingItemWithIDS() throws Exception {
			// TODO Auto-generated method stub

			//icnItemsBusinessDate;
			FileInputStream inputStream = new FileInputStream(excelFilePath);
			
			Workbook wrkBk = new XSSFWorkbook(inputStream);
			int index = wrkBk.getSheetIndex("Items05MF01");
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
				int count=0;
			
				if(rowIndex>0){
					while(cellIterator.hasNext()){
						Cell cell = cellIterator.next();
						System.out.println("cell :"+cell);
						switch(count){
						case 0:
							tag.setRootNode(cell.getRichStringCellValue().toString());
							System.out.println("RootTag Item val :"+cell.getRichStringCellValue().toString());
							count++;
							break;

						case 1:
							//listTag.add(cell.getRichStringCellValue.toString());
							tag.setItemChildNode(cell.getRichStringCellValue().toString());
							System.out.println("Item ChildNode Tag val :"+cell.getRichStringCellValue().toString());
							count++;
							break;
							
						case 2:
							//listTag.add(cell.getRichStringCellValue.toString());
							tag.setItemChildNode1(cell.getRichStringCellValue().toString());
							System.out.println("ItemChildNode1 :ItemID  Codeline Image ChildNode Tag val 1:"+cell.getRichStringCellValue().toString());
							count++;
							break;
						case 3:
							//listTag.add(cell.getRichStringCellValue.toString());
							tag.setItemChildNode2(cell.getRichStringCellValue().toString());
							System.out.println("ItemChildNode2 : Codeline data ChildNode Tag val 2:"+cell.getRichStringCellValue().toString());
							count++;
							break;
						
						case 4:
							tag.setOccurs(cell.getRichStringCellValue().toString());
							System.out.println("Occurs Column : Tag val :"+cell.getRichStringCellValue().toString());
							count++;
							break;
						case 5:
							tag.setFixedVar(cell.getRichStringCellValue().toString());
							System.out.println("Fixed/Var Column : Tag val :"+cell.getRichStringCellValue().toString());
							count++;
							break;
						case 6:
						//	System.out.println("Type Case 7 :Type " +cell.getNumericCellValue());
							//int typeLen = (int)cell.getNumericCellValue();
							//tag.setType(Integer.toString(typeLen));
										
							tag.setType(cell.getRichStringCellValue().toString());
							System.out.println("Case 7 :" +cell.getRichStringCellValue());
							count++;
							break;
						case 7:
							//System.out.println("Length" +cell.getRichStringCellValue());
							System.out.println("Length" +cell.getNumericCellValue());
							int len = (int)cell.getNumericCellValue();
							tag.setLength(Integer.toString(len));
							//String len = cell.getRichStringCellValue().toString();
							//tag.setLength(len);
							
							count++;
							break;
						}
						
					}
					listOfTag.add(tag);
					System.out.println("Item listOfTag :"+listOfTag+"  Item listOfTag.add(tag)"+listOfTag.add(tag));
				}
			}
			wrkBk.close();
			inputStream.close();
		
			
			String itemChildNode,rootNode,itmChildNode1,itmChildNode2;
			String itemName;
			String occurs;
			String type,fixedVar,length;
			int i=0;
			for(Tags1 tags:listOfTag){
				
				System.out.println("Get listOfTag :"+listOfTag);
				//Get Tag row
				itemChildNode = tags.getItemChildNode();
				System.out.println("itemChildNode :"+itemChildNode);
				itmChildNode1 =tags.getItemChildNode1();
				System.out.println("itemChildNode1 :"+itmChildNode1);
				itmChildNode2 =tags.getItemChildNode2();
				System.out.println("itemChildNode2 :"+itmChildNode2);
				rootNode =tags.getRootNode();
				System.out.println("rootNode :"+rootNode);
				occurs=tags.getOccurs();
				System.out.println("occurs :"+occurs);
				type=tags.getType();
				System.out.println("type :"+type);
				length=tags.getLength();
				System.out.println("length :"+length);
				fixedVar=tags.getFixedVar();
				System.out.println("fixedVar :"+fixedVar);
				
				if(null!=rootNode){
					getRootNodeItemsValidation(rootNode);
					if(null!=itemChildNode){
						getItemChildNodeValidation(itemChildNode);
						if(null!=itmChildNode1){
							switch(itmChildNode1){
								case "ItemId":
										itemIdNodeVal=getItemIdChildNodeValidation(itmChildNode1);
										getItemIdSubTagsOccuranceValidation(itemChildNode,occurs,itemIdNodeVal);
										System.out.println("*****As per IDS version 8, ChildItemsNode ItemId DataType in ICN is :"+type);
										System.out.println("Fixed or Variable cell is required for ItemId in IDS v8 "+fixedVar);
										System.out.println("Lenght validation is not required for ItemId in IDS v8 "+length);
										getItemIdLenghtValidation(itmChildNode1,length,itemIdNodeVal);
								
										break;
						
								case "Codeline":
										codeLineNodeVal = getItemsChildNodeCodelineValidation(itmChildNode1);
										System.out.println("*****As per IDS version 8, ChildItems Codeline Node tagName is "+itmChildNode1);
										System.out.println("Validation for Fixed/Variable, dataType and length. is not required.");
										System.out.println("Occurence validation for Codeline in IDS v8 "+occurs);
										getItemsSubTagsCodelineOccuranceValidation(itemChildNode,occurs,codeLineNodeVal);
																				
										if(null!=itmChildNode2){
											switch(itmChildNode2){
											
											case "Serial":
												serialNodeVal = getItemsCodelineSerialValidation(itmChildNode2);
												getItemsSubTagsSerialOccuranceValidation(itemChildNode,occurs,serialNodeVal);
												getItemsSubTagSerialLenghtValidation(itemChildNode,length,serialNodeVal);
												break;
											/* Reference Not required
											case "Reference":
												referenceNodeVal = getItemsCodelineReferenceValidation(itmChildNode2);
												getItemsSubTagsReferenceOccuranceValidation(itemChildNode,occurs,referenceNodeVal);
												getItemsSubTagReferenceLenghtValidation(itemChildNode,length,referenceNodeVal);
												break;
											*/
											case "SortCode":
												sortCodeNodeVal = getItemsCodelineSortCodeValidation(itmChildNode2);
												getItemsSubTagsSortCodeOccuranceValidation(itemChildNode,occurs,sortCodeNodeVal);
												getItemsSubTagSortCodeLenghtValidation(itemChildNode,length,sortCodeNodeVal);
												break;
												
											case "Account":
												accountNodeVal = getItemsCodelineAccountValidation(itmChildNode2);
												getItemsSubTagsAccountOccuranceValidation(itemChildNode,occurs,accountNodeVal);
												getItemsSubTagAccountLenghtValidation(itemChildNode,length,accountNodeVal);
												break;
												
											case "TranCode":
												trancodeNodeVal = getItemsCodelineTrancodeValidation(itmChildNode2);
												getItemsSubTagsTranCodeOccuranceValidation(itemChildNode,occurs,trancodeNodeVal);
												getItemsSubTagTrancodeLenghtValidation(itemChildNode,length,trancodeNodeVal);
												break;
												
											case "Amount":
												amountNodeVal = getItemsCodelineAmountValidation(itmChildNode2);
												getItemsSubTagsAmountOccuranceValidation(itemChildNode,occurs,amountNodeVal);
												getItemsSubTagAmountLenghtValidation(itemChildNode,length,amountNodeVal);
												break;
												
											case "Currency":
												currencyNodeVal = getItemsCodelineCurrencyValidation(itmChildNode2);
												getItemsSubTagsCurrencyOccuranceValidation(itmChildNode2,occurs,currencyNodeVal);
												getItemsSubTagCurrencyLenghtValidation(itmChildNode2,length,currencyNodeVal);
												break;
													
											}
										}
								
										break;
								case "Image":
									imageNodeVal= getItemsChildNodeImageValidation(itmChildNode1,occurs);
									System.out.println("imageNodeVal "+imageNodeVal);
									
							
									break;
							
							}
						}
					}
				}
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
		
		//RootNode Item validation
		public static void getRootNodeItemsValidation(String rootNode) throws Exception{
			
			System.out.println("For loop RootNode Items :"+rootNode);
			//now read xml and compare here
			//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
			System.out.println("****************************************************************");
			System.out.println("Validating RootNode Items is present in ICN as per IDS version 8 :"+rootNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("****************************************************************");
			validationStepInformation("Validating Items is present in ICN as per IDS version 8 :"+rootNode);
			validationStepInformation("****************************************************************");
			
			Map<String, String> ItemsNodeVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,"//Items");
			String nodeKey = getMapKey(ItemsNodeVal);
			//ItemsName= ItemsNodeVal.toString();
			//for(int i=0;i<ItemsNodeVal.size();i++){
		//	ItemsName = ItemsNodeVal.entrySet().iterator().next().getKey();
			System.out.println("ItemsNodeVal :"+nodeKey);
			boolean flag =Component.verifyEquals(nodeKey, rootNode, "Items tagname is as per IDSv8");			
			System.out.println("Validation is performed for ItemsTag :"+flag);
			validationStepInformation("Validation is performed for ItemsTag :"+flag);
			publishResults(flag,nodeKey,rootNode,"Items tagName is as per IDS version 8 :");
		}
		
		//ChildNode Item Validation
		
		public static void getItemChildNodeValidation(String itemChildNode) throws Exception{
			
			System.out.println("For loop RootNode Items :"+itemChildNode);
			//now read xml and compare here
			//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
			System.out.println("****************************************************************");
			System.out.println("Validating ChildNode Item is present under Items in ICN as per IDS version 8 :"+itemChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("****************************************************************");
			validationStepInformation("Validating ChildNode Item is present under Items in ICN as per IDS version 8 :"+itemChildNode);
			validationStepInformation("****************************************************************");
			
			Map<String, String> itemsNodeVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItems);
			String nodeKey = getMapKey(itemsNodeVal);
			System.out.println("Item NodeVal :"+nodeKey);
			boolean flag =Component.verifyEquals(nodeKey, itemChildNode, "Item tagname is as per IDSv8");			
			System.out.println("Validation is performed for ItemTag :"+flag);
			validationStepInformation("Validation is performed for ItemsTag :"+flag);
			publishResults(flag,nodeKey,itemChildNode,"Item tagName is present under Item Tag is as per IDS version 8 :");
		}
		
		public static String getItemIdChildNodeValidation(String itemsChildNode) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String itemIdNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating ItemId is populating under Item Tag in ICN as per IDS version 8 :"+itemsChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ itemsChildNode +" ChildItemNodes is populating under Item Tag in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemId);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("ItemId Key:"+dateKey+"::ItemId KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnItemId);
			for(int i=0;i<dateKeyVal1.size();i++){
			itemIdNodeVal =getNodeListValues(dateKeyVal1,itemIdTagName).get(i);
			System.out.println("itemIdNodeVal :"+itemIdNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, itemsChildNode, "ItemId tagname is present under Item is as per IDSv8");
			if(flagb==true)
				System.out.println("ItemId Tag is present under Item Tag in ICN file as per IDS v8 :");
			else
				System.out.println("ItemId Tag is not present under Item Tag in ICN file as per IDS v8 :");
			
			//for(int i=0;i<ItemsNodeVal.size();i++){
			//String bdName = bdateNode.entrySet().iterator().next().getKey();
			
			//ExtractID Validation
			//getItemsChildNodeExtractIdValidation(String ItemsChildNode);
			publishResults(flagb,dateKey, itemsChildNode, "ItemId tagname "+dateKey+" for ItemID "+itemIdNodeVal+" is as per IDSv8");
			}
			return itemIdNodeVal;
		}
		
		public static String getItemsChildNodeCodelineValidation(String itemsChildNode) throws Exception{
			
			System.out.println("****************************************************************");
			System.out.println("Validating Codeline ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 :"+itemsChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ itemsChildNode +" ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			String codelineNodeVal;
			//now read xml and compare here
			//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
			Map<String, String> codelineKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemCodelineXpath);
			Map<String, String> codelineKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnItemCodelineXpath);
			String codelineKey =getMapKey(codelineKeyValue);
			System.out.println("Codeline Key:"+codelineKey+"::Codeline KeyValue Pair:"+codelineKeyValue);
			
			codelineNodeVal =getNodeListValues(codelineKeyValue1,codelineTagName).get(0);
			System.out.println("codelineNodeVal :"+codelineNodeVal);
			boolean flagb =Component.verifyEquals(codelineKey, itemsChildNode, "Codeline tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("Codeline Tag is present under ItemsTag in ICN file as per IDS v8 :");
			else
				System.out.println("Codeline Tag is not present under ItemsTag in ICN file as per IDS v8 :");
			
			//for(int i=0;i<ItemsNodeVal.size();i++){
			//String bdName = bdateNode.entrySet().iterator().next().getKey();
			publishResults(flagb,codelineKey, itemsChildNode, "Codeline tagname is as per IDSv8");
			return codelineNodeVal;
			
		}
		
		//Codeline subtags validation
		public static String getItemsCodelineTrancodeValidation(String itemsChildNode) throws Exception{
			
			System.out.println("****************************************************************");
			System.out.println("Validating Codeline Serial ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 :"+itemsChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ itemsChildNode +" ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			String codelineNodeVal;
			//now read xml and compare here
			//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
			Map<String, String> codelineKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemsTranCodeXPATH);
			Map<String, String> codelineKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnItemsTranCodeXPATH);
			String codelineKey =getMapKey(codelineKeyValue);
			System.out.println("Trancode Key:"+codelineKey+"::Trancode KeyValue Pair:"+codelineKeyValue);
			
			codelineNodeVal =getNodeListValues(codelineKeyValue1,trancodeTagName).get(0);
			System.out.println("TrancodeNodeVal :"+codelineNodeVal);
			boolean flagb =Component.verifyEquals(codelineKey, itemsChildNode, "Codeline Trancode tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("Trancode Tag is present under ItemsTag in ICN file as per IDS v8 :");
			else
				System.out.println("Trancode Tag is not present under ItemsTag in ICN file as per IDS v8 :");
			
			//for(int i=0;i<ItemsNodeVal.size();i++){
			//String bdName = bdateNode.entrySet().iterator().next().getKey();
			publishResults(flagb,codelineKey, itemsChildNode, "Codeline Trancode tagname is as per IDSv8");
			return codelineNodeVal;
			
		}
		
	public static String getItemsCodelineReferenceValidation(String itemsChildNode) throws Exception{
			
			System.out.println("****************************************************************");
			System.out.println("Validating Codeline Serial ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 :"+itemsChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ itemsChildNode +" ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			String codelineNodeVal;
			//now read xml and compare here
			//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
			Map<String, String> codelineKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemsReferenceXPATH);
			Map<String, String> codelineKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnItemsReferenceXPATH);
			String codelineKey =getMapKey(codelineKeyValue);
			System.out.println("Reference Key:"+codelineKey+"::Reference KeyValue Pair:"+codelineKeyValue);
			
			codelineNodeVal =getNodeListValues(codelineKeyValue1,referenceTagName).get(0);
			System.out.println("ReferenceKeyNode :"+codelineNodeVal);
			boolean flagb =Component.verifyEquals(codelineKey, itemsChildNode, "Codeline Reference tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("Reference Tag is present under ItemsTag in ICN file as per IDS v8 :");
			else
				System.out.println("Reference Tag is not present under ItemsTag in ICN file as per IDS v8 :");
			
			//for(int i=0;i<ItemsNodeVal.size();i++){
			//String bdName = bdateNode.entrySet().iterator().next().getKey();
			publishResults(flagb,codelineKey, itemsChildNode, "Codeline Reference tagname is as per IDSv8");
			return codelineNodeVal;
			
		}
	public static String getItemsCodelineAccountValidation(String itemsChildNode) throws Exception{

	System.out.println("****************************************************************");
	System.out.println("Validating Codeline Serial ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 :"+itemsChildNode);
	System.out.println("****************************************************************");

	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ itemsChildNode +" ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");

	String codelineNodeVal;
	//now read xml and compare here
	//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
	Map<String, String> codelineKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemsAccountXPATH);
	Map<String, String> codelineKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnItemsAccountXPATH);
	String codelineKey =getMapKey(codelineKeyValue);
	System.out.println("Account Key:"+codelineKey+"::Account KeyValue Pair:"+codelineKeyValue);

	codelineNodeVal =getNodeListValues(codelineKeyValue1,accountTagName).get(0);
	System.out.println("AccountNodeVal :"+codelineNodeVal);
	boolean flagb =Component.verifyEquals(codelineKey, itemsChildNode, "Codeline Account tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("Account Tag is present under ItemsTag in ICN file as per IDS v8 :");
	else
		System.out.println("Account Tag is not present under ItemsTag in ICN file as per IDS v8 :");

	//for(int i=0;i<ItemsNodeVal.size();i++){
	//String bdName = bdateNode.entrySet().iterator().next().getKey();
	publishResults(flagb,codelineKey, itemsChildNode, "Codeline Account tagname is as per IDSv8");
	return codelineNodeVal;

	}
	public static String getItemsCodelineAmountValidation(String itemsChildNode) throws Exception{

	System.out.println("****************************************************************");
	System.out.println("Validating Codeline Serial ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 :"+itemsChildNode);
	System.out.println("****************************************************************");

	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ itemsChildNode +" ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");

	String codelineNodeVal;
	//now read xml and compare here
	//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
	Map<String, String> codelineKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemsAmountXPATH);
	Map<String, String> codelineKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnItemsAmountXPATH);
	String codelineKey =getMapKey(codelineKeyValue);
	System.out.println("Amount Key:"+codelineKey+"::Amount KeyValue Pair:"+codelineKeyValue);

	codelineNodeVal =getNodeListValues(codelineKeyValue1,amountTagName).get(0);
	System.out.println("AmountNodeVal :"+codelineNodeVal);
	boolean flagb =Component.verifyEquals(codelineKey, itemsChildNode, "Codeline Amount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("Amount Tag is present under ItemsTag in ICN file as per IDS v8 :");
	else
		System.out.println("Amount Tag is not present under ItemsTag in ICN file as per IDS v8 :");

	//for(int i=0;i<ItemsNodeVal.size();i++){
	//String bdName = bdateNode.entrySet().iterator().next().getKey();
	publishResults(flagb,codelineKey, itemsChildNode, "Codeline Amount tagname is as per IDSv8");
	return codelineNodeVal;

	}
	public static String getItemsCodelineSortCodeValidation(String itemsChildNode) throws Exception{

	System.out.println("****************************************************************");
	System.out.println("Validating Codeline SortCode ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 :"+itemsChildNode);
	System.out.println("****************************************************************");

	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ itemsChildNode +" ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");

	String codelineNodeVal;
	//now read xml and compare here
	//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
	Map<String, String> codelineKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemsSortCodeXPATH);
	Map<String, String> codelineKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnItemsSortCodeXPATH);
	String codelineKey =getMapKey(codelineKeyValue);
	System.out.println("SortCode Key:"+codelineKey+"::SortCode KeyValue Pair:"+codelineKeyValue);

	codelineNodeVal =getNodeListValues(codelineKeyValue1,sortCodeTagName).get(0);
	System.out.println("SortCodeNodeVal :"+codelineNodeVal);
	boolean flagb =Component.verifyEquals(codelineKey, itemsChildNode, "Codeline SortCode tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("SortCode Tag is present under ItemsTag in ICN file as per IDS v8 :");
	else
		System.out.println("SortCode Tag is not present under ItemsTag in ICN file as per IDS v8 :");

	//for(int i=0;i<ItemsNodeVal.size();i++){
	//String bdName = bdateNode.entrySet().iterator().next().getKey();
	publishResults(flagb,codelineKey, itemsChildNode, "Codeline SortCode tagname is as per IDSv8");
	return codelineNodeVal;

	}
	public static String getItemsCodelineSerialValidation(String itemsChildNode) throws Exception{

	System.out.println("****************************************************************");
	System.out.println("Validating Codeline Serial ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 :"+itemsChildNode);
	System.out.println("****************************************************************");

	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ itemsChildNode +" ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");

	String codelineNodeVal;
	//now read xml and compare here
	//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
	Map<String, String> codelineKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemsSerialXPATH);
	Map<String, String> codelineKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnItemsSerialXPATH);
	String codelineKey =getMapKey(codelineKeyValue);
	System.out.println("Serial Key:"+codelineKey+"::Serial KeyValue Pair:"+codelineKeyValue);

	codelineNodeVal =getNodeListValues(codelineKeyValue1,serialTagName).get(0);
	System.out.println("SerialNodeVal :"+codelineNodeVal);
	boolean flagb =Component.verifyEquals(codelineKey, itemsChildNode, "Codeline serial tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("Serial Tag is present under ItemsTag in ICN file as per IDS v8 :");
	else
		System.out.println("Serial Tag is not present under ItemsTag in ICN file as per IDS v8 :");

	//for(int i=0;i<ItemsNodeVal.size();i++){
	//String bdName = bdateNode.entrySet().iterator().next().getKey();
	publishResults(flagb,codelineKey, itemsChildNode, "Codeline Serial tagname is as per IDSv8");
	return codelineNodeVal;

	}
	public static String getItemsCodelineCurrencyValidation(String itemsChildNode) throws Exception{

	System.out.println("****************************************************************");
	System.out.println("Validating Codeline Serial ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 :"+itemsChildNode);
	System.out.println("****************************************************************");

	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ itemsChildNode +" ChildItemsNodes is populating under Item Tag in ICN as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");

	String codelineNodeVal;
	//now read xml and compare here
	//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
	Map<String, String> codelineKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemsCurrencyXpath);
	Map<String, String> codelineKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnItemsCurrencyXpath);
	String codelineKey =getMapKey(codelineKeyValue);
	System.out.println("Currency Key:"+codelineKey+"::Currency KeyValue Pair:"+codelineKeyValue);

	codelineNodeVal =getNodeListValues(codelineKeyValue1,currencyTagName).get(0);
	System.out.println("CurrencyNodeVal :"+codelineNodeVal);
	boolean flagb =Component.verifyEquals(codelineKey, itemsChildNode, "Codeline Currency tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("Currency Tag is present under ItemsTag in ICN file as per IDS v8 :");
	else
		System.out.println("Currency Tag is not present under ItemsTag in ICN file as per IDS v8 :");

	//for(int i=0;i<ItemsNodeVal.size();i++){
	//String bdName = bdateNode.entrySet().iterator().next().getKey();
	publishResults(flagb,codelineKey, itemsChildNode, "Codeline Currency tagname is as per IDSv8");
	return codelineNodeVal;

	}
		
		
	public static String getItemsChildNodeImageValidation(String itemsChildNode,String occurs) throws Exception{
			
			System.out.println("****************************************************************");
			System.out.println("Validating Image ChildItemsNodes is populating under ItemsTag in ICN as per IDS version 8 :"+itemsChildNode);
			System.out.println("****************************************************************");
			String imageNodeVal;
			//now read xml and compare here
			//ValidateE031ICNItemsSubTags.getBusinessDateICNValidation();
			Map<String, String> imageKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemsImageXPATH);
			String imageKey =getMapKey(imageKeyValue);
			System.out.println("Image Key:"+imageKey+"::Image KeyValue Pair:"+imageKeyValue);
			Map<String, String> processingParticpntIdKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnItemsImageXPATH);
			imageNodeVal =getNodeListValues(processingParticpntIdKeyValue1,imageTagName).get(0);
			System.out.println("imageNodeVal "+imageNodeVal);
			if(imageNodeVal.isEmpty())
			{
				System.out.println("No Validation is required.");
				publishResults(imageNodeVal.isEmpty(),(imageNodeVal.isEmpty())?"ImageNode is not present in ICN file":"Issue Found!!","ImageNode is not present in ICN file","ImageNode Validation");
			}
			else if(imageNodeVal!=null)
			{
				System.out.println("Image :"+imageNodeVal);
				boolean flagb =Component.verifyEquals(imageKey, itemsChildNode, "Image tagname is as per IDSv8");
				if(flagb)
				{
					System.out.println("Image Tag is present under ItemsTag in ICN file as per IDS v8 :");
				}
				else
				{
					System.out.println("Image Tag is not present under ItemsTag in ICN file as per IDS v8 :");
				}
				getItemsSubTagsImageOccuranceValidation(itemsChildNode,occurs,imageNodeVal);
				
				//for(int i=0;i<ItemsNodeVal.size();i++){
				//String bdName = bdateNode.entrySet().iterator().next().getKey();
				publishResults(flagb,imageKey, itemsChildNode, "Image tagname is as per IDSv8");
			}
			else
			{
				System.out.println("Issue found!!");
				publishResults(imageNodeVal==null,imageKey, itemsChildNode, "Image tagname is as per IDSv8");
			}
			
			return imageNodeVal;
			
		}




	//Occurence validation starts here
			public static void getItemIdSubTagsOccuranceValidation(String itemsChildNode,String occurs,String itemIdNodeVal) throws Exception {
			
			System.out.println("*****ChildItems ItemId Nodes Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildItems ItemId Nodes Occurance in ICN file validation starts here************");
			
			if(itemIdNodeVal!=null){
				
				System.out.println("*****As per IDS version 8, ChildItemsNodes Occurance for ItemId in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildItemsNodes Occurence Validation for "+itemsChildNode+" in ICN is :"+occurs);
				 boolean assertFlagCheck = FREDXMLValidation.validateItemIdLengthValidation(itemIdNodeVal);
				 boolean flag = Component.verifyTrue(assertFlagCheck, "ItemId data type and occurence validation performed.");
				 System.out.println("ItemId data type and occurence validation performed"+flag);
				 
			}
			else
			{
				System.out.println("ItemId should not be null!!");
			}
			publishResults(itemIdNodeVal!=null,(itemIdNodeVal!=null)?"Validation of ItemId data type and occurence for ItemId: "+itemIdNodeVal+" is performed.":"ItemId tag is null","Validation of ItemId data type and occurence for ItemId: "+itemIdNodeVal+" is performed.","ItemId data type and data format "+itemIdNodeVal+" validation performed");
		}
		
		public static void getItemsSubTagsCodelineOccuranceValidation(String itemsChildNode,String occurs,String codeLineNodeVal){
		//ExtractID occurrence validation
		System.out.println("*****As per IDS version 8, ChildItemsNodes Codeline Occurance for ExtractID in ICN is :"+occurs);
		validationStepInformation("*****As per IDS version 8, ChildItemsNodes Codeline Occurence Validation for "+itemsChildNode+" in ICN is :"+occurs);
			boolean flag1 = Component.verifyTrue(codeLineNodeVal!=null, "Codeline tag containing value");
			System.out.println("Codeline tag is containing value :"+flag1);
			publishResults(flag1,(codeLineNodeVal!=null)?"Codeline tag is containing value":"Codeline tag is null","Codeline tag is containing value","Codeline Occurence validation");
		}
		
		//ImageOcuurence
		public static void getItemsSubTagsImageOccuranceValidation(String itemsChildNode,String occurs,String imageNodeVal){
			
			System.out.println("*****As per IDS version 8, ChildItemsNodes Occurance for "+itemsChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildItemsNodes Occurence Validation for "+itemsChildNode+" in ICN is :"+occurs);
			
			boolean flag2 = Component.verifyTrue(imageNodeVal!=null, "Image tag containing value");
			System.out.println("Image tag is containing value :"+flag2);
			publishResults(Component.verifyTrue(imageNodeVal!=null, "Image tag containing value"),(imageNodeVal!=null)?"Image tag is containing value":"Image tag is null","Image tag is containing value","Image Occurence validation");
		}
		
		//Serial Occurence validation
		public static void getItemsSubTagsSerialOccuranceValidation(String itemsChildNode,String occurs,String serialNodeVal){
			//ExtractID occurrence validation
			System.out.println("*****As per IDS version 8, ChildItemsNodes Codeline Occurance for ExtractID in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildItemsNodes Codeline Occurence Validation for "+itemsChildNode+" in ICN is :"+occurs);
				boolean flag1 = Component.verifyTrue(serialNodeVal!=null, "Codeline tag containing value");
				System.out.println("Codeline Serial tag is containing value :"+flag1);
				publishResults(flag1,(serialNodeVal!=null)?"Codeline Serial tag is containing value":"Codeline Serial tag is null","Codeline tag is containing value","Codeline Occurence validation");
			}
			
			public static void getItemsSubTagsSortCodeOccuranceValidation(String itemsChildNode,String occurs,String sortCodeNodeVal){
				//ProcessingParticipantID
				System.out.println("*****As per IDS version 8, ChildItemsNodes Occurance for "+itemsChildNode+" in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildItemsNodes Occurence Validation for "+itemsChildNode+" in ICN is :"+occurs);
				boolean flag2 = Component.verifyTrue(sortCodeNodeVal!=null, "sortCodeNodeVal tag containing value");
				System.out.println("SortCode tag is containing value :"+flag2);
				publishResults(Component.verifyTrue(sortCodeNodeVal!=null, "sortCodeNodeVal tag containing value"),(sortCodeNodeVal!=null)?"SortCode tag is containing value":"SortCode tag is null","SortCode tag is containing value","SortCode Occurence validation");
			}
		
		public static void getItemsSubTagsReferenceOccuranceValidation(String itemsChildNode,String occurs,String referenceNodeVal){
		//Reference
			System.out.println("*****As per IDS version 8, ChildItemsNodes Occurance for "+itemsChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildItemsNodes Occurence Validation for "+itemsChildNode+" in ICN is :"+occurs);
			boolean flag4 = Component.verifyTrue(referenceNodeVal!=null, "Reference tag containing value");
			System.out.println("referenceNodeVal tag is containing value :"+flag4);
			publishResults(Component.verifyTrue(referenceNodeVal!=null, "Reference tag containing value"),(referenceNodeVal!=null)?"Reference tag is containing value":"Reference tag is null","Reference tag is containing value","Reference Occurence validation");
		}
		
		//Account
		public static void getItemsSubTagsAccountOccuranceValidation(String ItemsChildNode,String occurs,String accountNodeVal){
			System.out.println("*****As per IDS version 8, ChildItemsNodes Occurance for "+ItemsChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildItemsNodes Occurence Validation for "+ItemsChildNode+" in ICN is :"+occurs);
			boolean flag3 = Component.verifyTrue(accountNodeVal!=null, "accountNodeVal tag containing value");
			System.out.println("accountNodeVal tag is containing value :"+flag3);
			publishResults(Component.verifyTrue(accountNodeVal!=null, "accountNodeVal tag containing value"),(accountNodeVal!=null)?"accountNodeVal tag is containing value":"accountNodeVal tag is null","accountNodeVal tag is containing value","accountNodeVal Occurence validation");
		}
		
		//Amount
			public static void getItemsSubTagsAmountOccuranceValidation(String ItemsChildNode,String occurs,String amountNodeVal){
				System.out.println("*****As per IDS version 8, ChildItemsNodes Occurance for "+ItemsChildNode+" in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildItemsNodes Occurence Validation for "+ItemsChildNode+" in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildItemsNodes Occurence Validation for "+ItemsChildNode+" in ICN is :"+occurs);
				boolean flag5 = Component.verifyTrue(amountNodeVal!=null, "amountNodeVal tag containing value");
			System.out.println("messageSourceNodeVal tag is containing value :"+flag5);
		publishResults(Component.verifyTrue(amountNodeVal!=null, "amountNodeVal tag containing value"),(amountNodeVal!=null)?"Amount tag is containing value":"Amount tag is null","Amount tag is containing value","Amount Occurence validation");
			}
		
		//Trancode
			public static void getItemsSubTagsTranCodeOccuranceValidation(String itemsChildNode,String occurs,String trancodeNodeVal){
				System.out.println("*****As per IDS version 8, ChildItemsNodes Occurance for "+itemsChildNode+" in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildItemsNodes Occurence Validation for "+itemsChildNode+" in ICN is :"+occurs);
				boolean flag6 = Component.verifyTrue(trancodeNodeVal!=null, "Trancode tag containing value");
			System.out.println("Trancode tag is containing value :"+flag6);
			publishResults(Component.verifyTrue(trancodeNodeVal!=null, "Trancode tag containing value"),(trancodeNodeVal!=null)?"Trancode tag is containing value":"Trancode tag is null","Trancode tag is containing value","Trancode Occurence validation");
			}
			
		//Currency
			public static void getItemsSubTagsCurrencyOccuranceValidation(String itemsChildNode,String occurs,String currencyNodeVal){
				System.out.println("*****As per IDS version 8, ChildItemsNodes Occurance for "+itemsChildNode+" in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildItemsNodes Occurence Validation for "+itemsChildNode+" in ICN is :"+occurs);
				boolean flag7 = Component.verifyTrue(currencyNodeVal!=null, "Currency tag containing value");
			System.out.println("Currency tag is containing value :"+flag7);
			publishResults(Component.verifyTrue(currencyNodeVal!=null, "Currency tag containing value"),(currencyNodeVal!=null)?"Currency tag is containing value":"Currency tag is null","Currency tag is containing value","Currency Occurence validation");
			}
		
		
			//Length vaidation
			
			public static void getItemIdLenghtValidation(String itemsChildNode,String length,String itemIdNodeVal){
				System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+itemsChildNode+" in ICN is :"+length);	
				validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+itemsChildNode+" in ICN is :"+length);
				int itemIdLength=Integer.parseInt(length);
				boolean assertFlagCheck = FREDXMLValidation.validateItemIdLengthValidationWithIDS(itemIdNodeVal,length);
					 boolean bflag = Component.verifyTrue(assertFlagCheck, "ItemId length validation performed.");
					 if(bflag){
						 System.out.println("ItemId length correct as per IDS v8 :");
					 }
					 else
					 {
						 System.out.println("ItemId value lenght is not as per IDS v8. ");
					 }
					 publishResults((itemIdLength==25),(itemIdLength==25)?"ItemId length "+itemIdLength+" in ICN is as per IDS v8":"ItemId length "+itemIdLength+" in ICN validation got failed as per IDS v8","ItemId length "+itemIdLength+" in ICN is as per IDS v8","ItemId Length validation");
			}
				
		
		//SortCode Length validation
		public static void getItemsSubTagSortCodeLenghtValidation(String itemsChildNode,String length,String sortCodeNodeVal){
			System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+itemsChildNode+" in ICN is :"+length);	
			validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+itemsChildNode+" in ICN is :"+length);
			int sortcodeLength=Integer.parseInt(length);
			boolean assertFlagCheck = FREDXMLValidation.validateSortCodeWithIDS(sortCodeNodeVal,length);
				 boolean bflag = Component.verifyTrue(assertFlagCheck, "SortCode length validation performed.");
				 if(bflag){
					 System.out.println("SortCode length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("SortCode value lenght is not as per IDS v8. ");
				 }
				 publishResults((sortcodeLength<=6),(sortcodeLength<=6)?"SortCode length "+sortcodeLength+" in ICN is as per IDS v8":"SortCode length "+sortcodeLength+" in ICN validation got failed as per IDS v8","SortCode length "+sortcodeLength+" in ICN is as per IDS v8","SortCode Length validation");
		}
			
				 //Serial
		public static void getItemsSubTagSerialLenghtValidation(String ItemsChildNode,String length,String serialNodeVal){
			System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+ItemsChildNode+" in ICN is :"+length);
			validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+ItemsChildNode+" in ICN is :"+length);
			boolean ppflag = Component.verifyTrue(FREDXMLValidation.validateSerialWithIDS(serialNodeVal, length), "Serial length validation performed.");
				 if(ppflag){
					 System.out.println("Serial length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("Serial value lenght is not as per IDS v8. ");
				 }
				 publishResults((serialNodeVal.length()==Integer.parseInt(length.trim())),(serialNodeVal.length()==Integer.parseInt(length.trim()))?"Serial value length "+serialNodeVal.length()+" in ICN is as per IDS v8":"Serial length "+serialNodeVal.length()+" in ICN validation got failed as per IDS v8","Serial value length "+serialNodeVal.length()+" in ICN is as per IDS v8","Serial Length validation");
				 
		}	 
				 //Account
		public static void getItemsSubTagAccountLenghtValidation(String ItemsChildNode,String length,String accountNodeVal){
			System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+ItemsChildNode+" in ICN is :"+length);
			validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+ItemsChildNode+" in ICN is :"+length);
			boolean flagExtMsgType = Component.verifyTrue(FREDXMLValidation.validateAccountWithIDS(accountNodeVal, length), "Account length validation performed.");
				 if(flagExtMsgType){
					 System.out.println("Account length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("Account value lenght is not as per IDS v8. ");
				 }
				 publishResults(((accountNodeVal.length())<=Integer.parseInt(length)),((accountNodeVal.length())<=Integer.parseInt(length))?"Account value length "+accountNodeVal.length()+" in ICN is as per IDS v8":"Account length "+accountNodeVal.length()+" in ICN validation got failed as per IDS v8","Account value length "+accountNodeVal.length()+" in ICN is as per IDS v8","Account Length validation");
		}
				 //Amount
				 public static void getItemsSubTagAmountLenghtValidation(String ItemsChildNode,String length,String amountNodeVal){
					 System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+ItemsChildNode+" in ICN is :"+length);
					 validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+ItemsChildNode+" in ICN is :"+length);
					 boolean flagIntMsgType = Component.verifyTrue(FREDXMLValidation.validateAmountWithIDS(amountNodeVal,length), "Amount length validation performed.");
				 if(flagIntMsgType){
					 System.out.println("Amount length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("Amount value lenght is not as per IDS v8. ");
				 }
				 publishResults(((amountNodeVal.length())<=Integer.parseInt(length)),((amountNodeVal.length())<=Integer.parseInt(length))?"Amount value length "+amountNodeVal.length()+" in ICN is as per IDS v8":"amountNodeVal length "+amountNodeVal.length()+" in ICN validation got failed as per IDS v8","Amount value length "+amountNodeVal.length()+" in ICN is as per IDS v8","amountNodeVal Length validation");
				 }
				 
				 //Reference
				 public static void getItemsSubTagReferenceLenghtValidation(String itemsChildNode,String length,String businessDateNodeVal){
					 System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+itemsChildNode+" in ICN is :"+length);
					 validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+itemsChildNode+" in ICN is :"+length);
					 boolean flagMsgSrc = Component.verifyTrue(FREDXMLValidation.validateReferenceWithIDS(referenceNodeVal,length),"Reference length validation performed.");
				 if(flagMsgSrc){
					 System.out.println("Reference Validation length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("Reference value lenght is not as per IDS v8. ");
				 }
				 publishResults(((referenceNodeVal.length())<=Integer.parseInt(length)),((referenceNodeVal.length())<=Integer.parseInt(length))?"Reference value length "+referenceNodeVal.length()+" in ICN is as per IDS v8":"Reference length "+referenceNodeVal.length()+" in ICN validation got failed as per IDS v8","Reference length "+referenceNodeVal.length()+" in ICN is as per IDS v8","Reference Length validation");
				 }
				 
				 //Currency
				 public static void getItemsSubTagCurrencyLenghtValidation(String ItemsChildNode,String length,String currencyNodeVal){
					 System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+ItemsChildNode+" in ICN is :"+length);
					 validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+ItemsChildNode+" in ICN is :"+length);
					 boolean flagMsgDest = Component.verifyTrue(FREDXMLValidation.validateCurrencyWithIDS(currencyNodeVal, length), "Currency length validation performed.");
				 if(flagMsgDest){
					 System.out.println("Currency length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("Currency value lenght is not as per IDS v8. ");
				 }
				 publishResults(((currencyNodeVal.length())<=3),((currencyNodeVal.length())<=3)?"Currency value length "+currencyNodeVal.length()+" in ICN is as per IDS v8":"Currency length "+currencyNodeVal.length()+" in ICN validation got failed as per IDS v8","Currency value length "+currencyNodeVal.length()+" in ICN is as per IDS v8","Currency Length validation");
				 }
				
				 //Trancode
				 public static void getItemsSubTagTrancodeLenghtValidation(String itemsChildNode,String length,String trancodeNodeVal){
					 System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+itemsChildNode+" in ICN is :"+length);
					 validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+itemsChildNode+" in ICN is :"+length);
					 boolean rcLength =FREDXMLValidation.validateTrancodeWithIDS(trancodeNodeVal,length);
					 boolean flagRC =Component.verifyTrue(rcLength,"Trancode length validation as per IDS v8"); 
					 // Component.verifyTrue(FREDXMLValidation.validateRecordCountsLength(recordCountsNodeVal), "RecordCounts length validation performed.");
					 // publishResults(flagRC,String.valueOf(rcLength),length,"RecordCounts length validation as per IDS v8");
				 if(flagRC){
					 System.out.println("Trancode length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("Trancode value lenght is not as per IDS v8.");
				 }
				 publishResults((trancodeNodeVal.length()<=2),(trancodeNodeVal.length()<=2)?"Trancode Length in ICN file is"+length+"as per IDS v8":"Trancode length should not be greater than 2","Trancode Length in ICN file is"+length+"as per IDS v8","Trancode length validation as per IDS v8"); 
				}
				
	}
