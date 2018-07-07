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

	public class ICNEntitiesValidation extends GenericMethodUtilis{

		List<String> listOfMandatory = new ArrayList<String>();
	/*	static String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
		static String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\";
		 static String ICNFileName="ICNOutput.xml";
	*/	
		//XPATH Declarations
		static String icnEntitiesXpath="//Entities";
		static String icnEntityXpath="//Entities/Entity";
		static String icnEntitiesTypeXpath ="//Entities/Entity/EntityType";
		static String icnEntitiesIdXpath ="//Entities/Entity/EntityId";
		static String icnEntitiesStateXpath="//Entities/Entity/EntityState";
		static String icnEntitiesSourceDateTimeXpath="//Entities/Entity/SourceDateTime";
	
		static String entityTypeTagName = "EntityType";
		static String entityStateTagName = "EntityState";
		static String sourceDateTimeTagName = "SourceDateTime";
		static String entityIdTagName = "EntityId";
		static String enitityTagName = "Entity";	
		static String xsdFileEnititiesNodeTagName="Entities";
		 static String entitiesNodeVal,entityTypeNodeVal,entityIdNodeVal,entityStateNodeVal,entityIdNodeValItem,entityIdNodeValTransaction;
		 
		
		
	 //
	 	
		//private static final String businessDateTagName = null;

		//public static void main(String[] args) throws Exception {
		@Test
		public static void validateICNEntitiesWithIDS(String excelFilePath,String ICNFilepath,String ICNFileName) throws Exception {
			// TODO Auto-generated method stub

			//icnCoreBusinessDate;
			FileInputStream inputStream = new FileInputStream(excelFilePath);
			
			Workbook wrkBk = new XSSFWorkbook(inputStream);
			int index = wrkBk.getSheetIndex("Entities");
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
							tag.setEntitiesChildNode(cell.getRichStringCellValue().toString());
							System.out.println("ChildNode Tag val :"+cell.getRichStringCellValue().toString());
							count++;
							break;
						case 3:
							//listTag.add(cell.getRichStringCellValue.toString());
							tag.setEntityChildNode(cell.getRichStringCellValue().toString());
							System.out.println("ChildNode Tag val 1:"+cell.getRichStringCellValue().toString());
							count++;
							break;
						
						case 4:
							tag.setOccurs(cell.getRichStringCellValue().toString());
							count++;
							break;
						case 5:
							tag.setFixedVar(cell.getRichStringCellValue().toString());
							count++;
							break;
						case 6:
							tag.setType(cell.getRichStringCellValue().toString());
							count++;
							break;
						case 7:
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
		
			
			String entityChildNode,rootNode,entitiesChildNode;
			String occurs;
			String type,fixedVar,length;
			int i=0;
			for(Tags1 tags:listOfTag){
				System.out.println("listOfTag :"+listOfTag);
				entityChildNode = tags.getEntityChildNode(); // entityid, entitystate,...
				entitiesChildNode=tags.getEntitiesChildNode(); //entity
				rootNode =tags.getRootNode(); //entities
				String coreName;
				occurs=tags.getOccurs();
				type=tags.getType();
				length=tags.getLength();
				fixedVar=tags.getFixedVar();
				if(null!=rootNode){
					boolean flag = getRootNodeEntityValidation(rootNode,ICNFilepath,ICNFileName);
					if(flag){
					if(null!=entitiesChildNode){
						getChildNodeEntityValidation(entitiesChildNode,ICNFilepath,ICNFileName);
					
					if(null!=entityChildNode){
						
						switch(entityChildNode){
							case "EntityType":
									entitiesNodeVal=getEntityTypeValidation(entityChildNode,ICNFilepath,ICNFileName);
									getEntityTypeOccuranceValidation(entityChildNode,occurs,entitiesNodeVal);
									System.out.println("*****As per IDS version 8, EntityChildNode EntityType DataType in ICN is :"+type);
									System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);
									System.out.println("Lenght validation is not required for BusinessDate in IDS v8 "+length);
								//	getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal);
									/*switch(entitiesNodeVal){
									case "I":
										entityIdNodeValItem = getEntityIdItemValidation(entityChildNode);
										getEntityIdItemOccuranceValidation(entityChildNode,occurs,entityIdNodeValItem);
										getEntityIdItemLengthValidation(entityChildNode,length,entityIdNodeValItem);
										break;
										
									case "T":
										entityIdNodeValTransaction = getEntityIdTransactionValidation(entityChildNode);
										getEntityIdTransactionOccuranceValidation(entityChildNode,occurs,entityIdNodeValTransaction);
										getEntityIdTransactionLengthValidation(entityChildNode,length,entityIdNodeValTransaction);
										break;
									}*/
									break;
					
							case "EntityState":
								try{
									entityStateNodeVal = getEntityStateValidation(entityChildNode,ICNFilepath,ICNFileName);
									getEntityStateOccuranceValidation(entityChildNode,occurs,entityStateNodeVal);
									getEntityStateLenghtValidation(entityChildNode,length,entityStateNodeVal);
								}
								catch(Exception e){
									System.out.println(e.getMessage());
									validationErrorInformation(entityChildNode+" Not Required"+e.getMessage());
								}
									break;
							case "SourceDateTime":
								entityTypeNodeVal = getSourceDateTimeValidation(entityChildNode,ICNFilepath,ICNFileName);
								getSourceDateTimeOccuranceValidation(entityChildNode,occurs,entityTypeNodeVal);
								System.out.println("*****As per IDS version 8, EntityChildNode SourceDateTime DataType in ICN is :"+type);
								System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);
								System.out.println("Lenght validation is not required for BusinessDate in IDS v8 "+length);
							
						
								break;
							case "EntityIdTransaction":
								
								entityIdNodeValTransaction = getEntityIdTransactionValidation(entityChildNode,ICNFilepath,ICNFileName);
								if(entityIdNodeValTransaction.equals("T")){
									getEntityIdTransactionOccuranceValidation(entityChildNode,occurs,entityIdNodeValTransaction);
									getEntityIdTransactionLengthValidation(entityChildNode,length,entityIdNodeValTransaction);
							
								}
								else{
									validationStepInformation("EntityType as Transaction value is not present in ICN");
								}
								
								break;
							case "EntityIdItem":
								
								entityIdNodeValItem = getEntityIdItemValidation(entityChildNode,ICNFilepath,ICNFileName);
								getEntityIdTransactionOccuranceValidation(entityChildNode,occurs,entityIdNodeValItem);
								getEntityIdItemLengthValidation(entityChildNode,length,entityIdNodeValItem);
						
								break;
												
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
				}
				else
				{
					validationErrorInformation("Entity tag is not present in ICN file");
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
		
		
		/*Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,"//Entities/Entity/EntityId");
		//for(int i=0;i<coreNodeVal.size();i++){
		coreName = coreNodeVal.entrySet().iterator().next().getKey();
		System.out.println("coreNodeVal :"+coreName);
		boolean flag =Component.verifyEquals(coreName, "EntityId", "Core tagname is as per IDSv8");
//		System.out.println("Flag :"+i+flag);
//		}
		
		List<String> rNodeList =getNodeListName(coreNodeVal,"Core");
		System.out.println("rNodeList :"+rNodeList);
		
		
		//boolean flag =Component.verifyEquals(coreName, "EntityId", "Core tagname is as per IDSv8");
		System.out.println("Flag :"+flag);
	*/
		public static boolean getRootNodeEntityValidation(String rootNode,String ICNFilepath,String ICNFileName) throws Exception{
			
			System.out.println("For loop RootNode Core :"+rootNode);
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			System.out.println("****************************************************************");
			System.out.println("Validating RootNode Core is present in ICN as per IDS version 8 :"+rootNode);
			System.out.println("****************************************************************");
			Map<String, String> entitiesNodeVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnEntitiesXpath);
			String nodeKey = getMapKey(entitiesNodeVal);
			//coreName= coreNodeVal.toString();
			//for(int i=0;i<coreNodeVal.size();i++){
		//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
			System.out.println("entitiesNodeVal :"+nodeKey);
			boolean flag =Component.verifyEquals(nodeKey, rootNode, "Entities tagname is as per IDSv8");			
			System.out.println("Validation is performed for Entities :"+flag);
			return flag;
		}
		
		public static void getChildNodeEntityValidation(String rootNode,String ICNFilepath,String ICNFileName) throws Exception{
			
			System.out.println("For loop RootNode Core :"+rootNode);
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			System.out.println("****************************************************************");
			System.out.println("Validating RootNode Core is present in ICN as per IDS version 8 :"+rootNode);
			System.out.println("****************************************************************");
			Map<String, String> entityNodeVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnEntityXpath);
			String nodeKey = getMapKey(entityNodeVal);
			//coreName= coreNodeVal.toString();
			//for(int i=0;i<coreNodeVal.size();i++){
		//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
			System.out.println("entityNodeVal :"+nodeKey);
			boolean flag =Component.verifyEquals(nodeKey, rootNode, "Entity tagname is as per IDSv8");			
			System.out.println("Validation is performed for Entity :"+flag);
			
		}
		
		public static String getEntityTypeValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String entityTypeNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating EntityChildNodes is populating under Entity Tag in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" EntityChildNodes is populating under Entity in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnEntitiesTypeXpath);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("entityType Key:"+dateKey+"::entityType KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnEntitiesTypeXpath);
			entityTypeNodeVal =getNodeListValues(dateKeyVal1,entityTypeTagName).get(0);
			System.out.println("entityTypeNodeVal :"+entityTypeNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "EntityType tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("EntityType Tag is present under Entities Tag in ICN file as per IDS v8 :");
			else
				System.out.println("EntityType Tag is not present under Entities tag in ICN file as per IDS v8 :");
			
			//for(int i=0;i<coreNodeVal.size();i++){
			//String bdName = bdateNode.entrySet().iterator().next().getKey();
			
			//ExtractID Validation
			//getCoreChildNodeExtractIdValidation(String coreChildNode);
			publishResults(flagb,dateKey, coreChildNode, "EntityType tagname is as per IDSv8");
			return entityTypeNodeVal;
		}
		
		public static String getEntityStateValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception{
			
			System.out.println("****************************************************************");
			System.out.println("Validating Extract ID ChildCoreNodes is populating under EntityTag in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" ChildCoreNodes is populating under EntityTag in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			String entityStateNodeVal;
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			Map<String, String> entityStateKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnEntitiesStateXpath);
			Map<String, String> entityStateKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnEntitiesStateXpath);
			String entityStateKey =getMapKey(entityStateKeyValue);
			System.out.println("EntityState Key:"+entityStateKey+"::EntityState KeyValue Pair:"+entityStateKeyValue);
			
			entityStateNodeVal =getNodeListValues(entityStateKeyValue1,entityStateTagName).get(0);
			System.out.println("entityStateNodeVal :"+entityStateNodeVal);
			boolean flagb =Component.verifyEquals(entityStateKey, coreChildNode, "ExtractId tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("EntityState Tag is present under EntityTag in ICN file as per IDS v8 :");
			else
				System.out.println("EntityState Tag is not present under EntityTag in ICN file as per IDS v8 :");
			
			//for(int i=0;i<coreNodeVal.size();i++){
			//String bdName = bdateNode.entrySet().iterator().next().getKey();
			publishResults(flagb,entityStateKey, coreChildNode, "EntityState tagname is as per IDSv8");
			return entityStateNodeVal;
			
		}
		
	

	public static String getEntityIdItemValidation(String entityChildNode,String ICNFilepath,String ICNFileName) throws Exception{
		
		System.out.println("****************************************************************");
		System.out.println("Validating IntMessageType ChildCoreNodes is populating under EntityTag in ICN as per IDS version 8 :"+entityChildNode);
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+ entityChildNode +" ChildCoreNodes is populating under EntityTag in ICN as per IDS version 8 ");
		validationStepInformation("*********************************************************************************************************************");
		
		entityChildNode = entityChildNode.substring(0,8);
		String entityIdItemNodeVal;
		//now read xml and compare here
		//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
		Map<String, String> entityIdItemKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnEntitiesIdXpath);
		String entityIdItemKey =getMapKey(entityIdItemKeyValue);
		System.out.println("EntityIdItemKey Key:"+entityIdItemKey+"::EntityIdItemKey KeyValue Pair:"+entityIdItemKeyValue);
		Map<String, String> entityIdItemKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnEntitiesIdXpath);
		entityIdItemNodeVal =getNodeListValues(entityIdItemKeyValue1,entityIdTagName).get(0);
		System.out.println("entityIdItemNodeVal :"+entityIdItemNodeVal);
		boolean flagb =Component.verifyEquals(entityIdItemKey, entityChildNode, "EntityIdItemKey tagname is as per IDSv8");
		if(flagb==true)
			System.out.println("EntityIdItemKey Tag is present under Entity Tag in ICN file as per IDS v8 :");
		else
			System.out.println("EntityIdItemKey Tag is not present under Entity Tag in ICN file as per IDS v8 :");
		
		//for(int i=0;i<coreNodeVal.size();i++){
		//String bdName = bdateNode.entrySet().iterator().next().getKey();
		publishResults(flagb,entityIdItemKey, entityChildNode, "EntityId tagname is as per IDSv8");
		return entityIdItemNodeVal;	
	}

	public static String getEntityIdTransactionValidation(String entityChildNode,String ICNFilepath,String ICNFileName) throws Exception{
		
		System.out.println("****************************************************************");
		System.out.println("Validating EntityID ID ChildEntityNodes is populating under EntityTag in ICN as per IDS version 8 :"+entityChildNode);
		System.out.println("****************************************************************");
		
		validationStepInformation("*********************************************************************************************************************");
		validationStepInformation("Validating "+ entityChildNode +" ChildCoreNodes is populating under EntityTag in ICN as per IDS version 8 ");
		validationStepInformation("*********************************************************************************************************************");
		
		entityChildNode = entityChildNode.substring(0,8);
		String entityIdTransactionNodeVal;
		//now read xml and compare here
		//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
		Map<String, String> entityIdTransactionKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnEntitiesIdXpath);
		Map<String, String> entityIdTransactionKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnEntitiesIdXpath);
		String entityIdTransactionKey =getMapKey(entityIdTransactionKeyValue);
		System.out.println("EntityID Key:"+entityIdTransactionKey+"::EntityID KeyValue Pair:"+entityIdTransactionKeyValue);
		
		entityIdTransactionNodeVal =getNodeListValues(entityIdTransactionKeyValue1,entityIdTagName).get(0);
		System.out.println("entityIdTransactionNodeVal :"+entityIdTransactionNodeVal);
		boolean flagb =Component.verifyEquals(entityIdTransactionKey, entityChildNode, "EntityID tagname is as per IDSv8");
		if(flagb==true)
			System.out.println("EntityID Tag is present under EntityTag in ICN file as per IDS v8 :");
		else
			System.out.println("EntityID Tag is not present under EntityTag in ICN file as per IDS v8 :");
		
		//for(int i=0;i<coreNodeVal.size();i++){
		//String bdName = bdateNode.entrySet().iterator().next().getKey();
		publishResults(flagb,entityIdTransactionKey, entityChildNode, "entityIdTransactionKeyValue tagname in ICN file is as per IDSv8");
		return entityIdTransactionNodeVal;
		
	}

	
	public static String getSourceDateTimeValidation(String coreChildNode,String ICNFilepath,String ICNFileName) throws Exception{
		
		System.out.println("****************************************************************");
		System.out.println("Validating SourceDateTime ChildCoreNodes is populating under EntityTag in ICN as per IDS version 8 :"+coreChildNode);
		System.out.println("****************************************************************");
		
		validationStepInformation("****************************************************************");
		validationStepInformation("Validating SourceDateTime ChildCoreNodes is populating under EntityTag in ICN as per IDS version 8 :"+coreChildNode);
		validationStepInformation("****************************************************************");
		
		String srcDateTimeNodeVal;
		//now read xml and compare here
		//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
		Map<String, String> srcDateTimeKeyValue =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnEntitiesSourceDateTimeXpath);
		Map<String, String> srcDateTimeKeyValue1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnEntitiesSourceDateTimeXpath);
		String srcDateTimeKey =getMapKey(srcDateTimeKeyValue);
		System.out.println("SourceDateTime Key:"+srcDateTimeKey+"::SourceDateTime KeyValue Pair:"+srcDateTimeKeyValue);
		srcDateTimeNodeVal =getNodeListValues(srcDateTimeKeyValue1,sourceDateTimeTagName).get(0);
		System.out.println("srcDateTimeNodeVal :"+srcDateTimeNodeVal);
		System.out.println("SourceDateTime childNode"+coreChildNode);
		boolean flagb =Component.verifyEquals(srcDateTimeKey, coreChildNode, "SourceDateTime tagname is as per IDSv8");
		if(flagb==true)
			System.out.println("SourceDateTime Tag is present under EntityTag in ICN file as per IDS v8 :");
		else
			System.out.println("SourceDateTime Tag is not present under EntityTag in ICN file as per IDS v8 :");
		
		//for(int i=0;i<coreNodeVal.size();i++){
		//String bdName = bdateNode.entrySet().iterator().next().getKey();
		publishResults(flagb,srcDateTimeKey, coreChildNode, "SourceDateTime tagname is as per IDSv8");
		return srcDateTimeNodeVal;
		
	}

	//Occurence
		public static void getSourceDateTimeOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal) throws Exception {
		//	getCoreSubTagsOccuranceValidation(occurs,businessDateNodeVal,extractIDNodeVal,processingParticipantIdNodeVal,intMessageTypeNodeVal,
			//extMessageTypeNodeVal,messageSourceNodeVal,recordCountsNodeVal,messageDestinationNodeVal);
			
			System.out.println("*****EntityChildNode Occurance in ICN file validation starts here*********");
			validationStepInformation("*****EntityChildNode Occurance in ICN file validation starts here************");
		/*	Element element = null;
			ValidateE031ICNCoreSubTags.getBusinessDateICNValidation(ICNFilepath,ICNFileName,element);
		}*/if(bdateNodeVal!=null){
			System.out.println("*****As per IDS version 8, EntityChildNode Occurance for SourceDateTime in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, EntityChildNode SourceDateTime Validation for "+coreChildNode+" in ICN is :"+occurs);
			 boolean assertFlagCheck = FREDXMLValidation.validateBusinessDateDatabaseFormat(bdateNodeVal);
			 boolean flag = Component.verifyTrue(assertFlagCheck, "SourceDateTime date type and data format validation performed.");
			 System.out.println("SourceDateTime date type and data format"+flag);
			}
		else
			{
			System.out.println("SourceDateTime should not be null!!");
			}
		publishResults(bdateNodeVal!=null,(bdateNodeVal!=null)?"BusinessDate date type and data format "+bdateNodeVal+" validation performed":"BusinessDate tag is null","BusinessDate date type and data format "+bdateNodeVal+" validation performed","BusinessDate date type and data format "+bdateNodeVal+" validation performed");
		}
		public static void getEntityStateOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
		//ExtractID occurrence validation
		System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for EntityState in ICN is :"+occurs);
		validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag1 = Component.verifyTrue(entityStateNodeVal!=null, "ExtractID tag containing value");
			System.out.println("entityStateNodeVal tag is containing value :"+flag1);
			publishResults(flag1,(entityStateNodeVal!=null)?"entityStateNodeVal tag is containing value":"entityStateNodeVal tag is null","entityStateNodeVal tag is containing value","entityStateNodeVal Occurence validation");
		}
		
		public static void getEntityTypeOccuranceValidation(String coreChildNode,String occurs,String entityTypeNodeVal){
			//SourceDateTime
			System.out.println("*****As per IDS version 8, EntityType ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, EntityType ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag2 = Component.verifyTrue(entityTypeNodeVal!=null, "EntityType tag containing value");
			System.out.println("EntityType tag is containing value :"+flag2);
			publishResults(Component.verifyTrue(entityTypeNodeVal!=null, "EntityType tag containing value"),(entityTypeNodeVal!=null)?"EntityType tag is containing value":"EntityType tag is null","EntityType tag is containing value","EntityType Occurence validation");
		}
		
		public static void getEntityIdTransactionOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
		//EntityId Transaction
			System.out.println("*****As per IDS version 8, EntityIdTransactionChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, EntityIdTransactionChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag4 = Component.verifyTrue(entityIdNodeValTransaction!=null, "EntityIdTransaction tag containing value");
			System.out.println("extMessageTypeNodeVal tag is containing value :"+flag4);
			publishResults(Component.verifyTrue(entityIdNodeValTransaction!=null, "EntityIdTransaction tag containing value"),(entityIdNodeValTransaction!=null)?"EntityIdTransaction tag is containing value":"EntityIdTransaction tag is null","EntityIdTransaction tag is containing value","EntityIdTransaction Occurence validation");
		}
		
		//EntityIdItem
		public static void getEntityIdItemOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
			System.out.println("*****As per IDS version 8, EntityIdItemChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, EntityIdItemChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag3 = Component.verifyTrue(entityIdNodeValItem!=null, "entityIdNodeValItem tag containing value");
			System.out.println("intMessageTypeNodeVal tag is containing value :"+flag3);
			publishResults(Component.verifyTrue(entityIdNodeValItem!=null, "entityIdNodeValItem tag containing value"),(entityIdNodeValItem!=null)?"entityIdNodeValItem tag is containing value":"entityIdNodeValItem tag is null","entityIdNodeValItem tag is containing value","entityIdNodeValItem Occurence validation");
		}
		
		//Length Validation
		public static void getEntityStateLenghtValidation(String coreChildNode,String length,String businessDateNodeVal){
			System.out.println("*****As per IDS version 8,EntityState ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);	
			validationStepInformation("*****As per IDS version 8, EntityState ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
			int extractIdLength=Integer.parseInt(length);
			boolean assertFlagCheck = FREDXMLValidation.validateEntityStateWithIDS(entityStateNodeVal,extractIdLength);
				 boolean bflag = Component.verifyTrue(assertFlagCheck, "EntityState length validation performed.");
				 if(bflag){
					 System.out.println("EntityState length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("EntityState value length is not as per IDS v8. ");
				 }
				 publishResults(bflag,(entityStateNodeVal.length()<=3)?"EntityState length "+entityStateNodeVal.length()+" in ICN is as per IDS v8":"EntityState length "+entityStateNodeVal.length()+" in ICN validation got failed as per IDS v8","EntityState length "+entityStateNodeVal.length()+" in ICN is as per IDS v8","EntityState Length validation");
		}
			
				 //PP
		public static void getEntityTypeLenghtValidation(String coreChildNode,String length,String businessDateNodeVal){
			System.out.println("*****As per IDS version 8, EntityType ChildCoreNodes  Length Validation for "+coreChildNode+" in ICN is :"+length);
			validationStepInformation("*****As per IDS version 8,EntityType ChildCoreNodes  Length Validation for "+coreChildNode+" in ICN is :"+length);
			boolean ppflag = Component.verifyTrue(FREDXMLValidation.validateEntityTypeLength(entityTypeNodeVal), "EntityType length validation performed.");
				 if(ppflag){
					 System.out.println("EntityType length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("EntityType value lenght is not as per IDS v8. ");
				 }
				 publishResults(ppflag,(entityTypeNodeVal.length()==Integer.parseInt(length.trim()))?"EntityType value length "+entityTypeNodeVal.length()+" in ICN is as per IDS v8":"EntityType length "+entityTypeNodeVal.length()+" in ICN validation got failed as per IDS v8","EntityType length "+entityTypeNodeVal.length()+" in ICN is as per IDS v8","EntityType Length validation");
				 
		}	 
				 //EntityIdTransaction 
		public static void getEntityIdTransactionLengthValidation(String coreChildNode,String length,String businessDateNodeVal){
			System.out.println("*****As per IDS version 8,EntityIdTransaction ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
			validationStepInformation("*****As per IDS version 8, EntityIdTransaction ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
			//boolean flagExtMsgType = Component.verifyTrue(FREDXMLValidation.validateExtMessageType(entityIdNodeValTransaction), "EntityIdTransaction length validation performed.");
				// if(entityIdNodeValTransaction.length()==Integer.parseInt(length)){
			 if(entityIdNodeValTransaction.length()==24){
					 System.out.println("EntityIdTransaction length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("EntityIdTransaction value lenght is not as per IDS v8. ");
				 }
				 publishResults((entityIdNodeValTransaction.length())==24,((entityIdNodeValTransaction.length())==24)?"EntityIdTransaction value length "+entityIdNodeValTransaction.length()+" in ICN is as per IDS v8":"EntityIdTransaction length "+entityIdNodeValTransaction.length()+" in ICN validation got failed as per IDS v8","EntityIdTransaction value length "+entityIdNodeValTransaction.length()+" in ICN is as per IDS v8","EntityIdTransaction Length validation");
		}
				 //IntMsgType
				 public static void getEntityIdItemLengthValidation(String coreChildNode,String length,String businessDateNodeVal){
					 System.out.println("*****As per IDS version 8, EntityIdItem ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
					 validationStepInformation("*****As per IDS version 8,EntityIdItem ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
					// boolean flagIntMsgType = Component.verifyTrue(FREDXMLValidation.validateIntMessageType(entityIdNodeValItem), "EntityIdItem length validation performed.");
				 if(entityIdNodeValItem.length()<=25){
					 System.out.println("EntityIdItem length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("EntityIdItem value lenght is not as per IDS v8. ");
				 }
				// publishResults(entityIdNodeValItem.length()<=Integer.parseInt(length),((entityIdNodeValItem.length())<=Integer.parseInt(length))?"EntityIdItem value length "+entityIdNodeValItem.length()+" in ICN is as per IDS v8":"EntityIdItem length "+entityIdNodeValItem.length()+" in ICN validation got failed as per IDS v8","EntityIdItem length "+entityIdNodeValItem.length()+" in ICN is as per IDS v8","EntityIdItem Length validation");
				 publishResults(entityIdNodeValItem.length()<=25,((entityIdNodeValItem.length())<=25)?"EntityIdItem value length "+entityIdNodeValItem.length()+" in ICN is as per IDS v8":"EntityIdItem length "+entityIdNodeValItem.length()+" in ICN validation got failed as per IDS v8","EntityIdItem value length "+entityIdNodeValItem.length()+" in ICN is as per IDS v8","EntityIdItem Length validation");
				 }
								
	}

