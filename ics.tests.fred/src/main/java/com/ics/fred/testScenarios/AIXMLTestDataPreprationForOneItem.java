
	package com.ics.fred.testScenarios;

	import java.io.File;
	import java.io.FileInputStream;
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

	import com.ics.externalFactoryUtilis.ICSDBUtilis;
	import com.ics.externalFactoryUtilis.ICSProductDBConnection;
	import com.ics.fred.common.GenericMethods;
	import com.ics.fred.common.ReadSQLFileUtil;
	import com.ics.seleniumCoreUtilis.Component;
	import com.ics.seleniumCoreUtilis.GenericMethodUtilis;


	public class AIXMLTestDataPreprationForOneItem extends ICSDBUtilis {

		//read excel file 
		//	getRequiredStTestData(filePath, sheetName, testCase, keyData);
		public static String filepath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\XMLConvertedToExcel.xlsx";
		//	String jobWorkTypeData = getRequiredStTestData(filepath, sheetName, testCase, keyData);
		//static String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
		//static String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\";
//		static String ICNFileName="ICNOutput.xml";
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
		

		
//		public static List<String> readExcelAIXML() throws IOException{
		public static Map<String,String> readExcelAIXML(String fredaixmlTmpltFilename,String templateFilePath,String dbServerName,String fredDBName,String aixmlCopyTempFldrPath) throws Exception{
		FileInputStream inputStream = new FileInputStream(filepath);
		
		Workbook wrkBk = new XSSFWorkbook(inputStream);
		int index = wrkBk.getSheetIndex("AIXMLTestData");
		
		Map<String,String> xpathAndNewValueMap = new HashMap<>();
		Sheet fsheet = wrkBk.getSheetAt(index);
		Iterator<Row> iterator = fsheet.iterator();
		List<String> listTag = new ArrayList<String>();
		String a = null;
		int rowIndex=0;
		String s =generateAIXMLFile(fredaixmlTmpltFilename,templateFilePath,dbServerName,fredDBName,aixmlCopyTempFldrPath,1);
		while(iterator.hasNext()){
			
			Row nextRow = iterator.next();
			Iterator<Cell> cellIterator = nextRow.cellIterator();
			
			String xpath = null;
			String newValue = null;
			 rowIndex = nextRow.getRowNum();
			System.out.println("rowIndex :"+rowIndex);
			
			int count=1;
			if(rowIndex>0){
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
					
					switch(cell.getCellType()){
					case Cell.CELL_TYPE_BLANK :
						System.out.println("Break");
						break;
					case Cell.CELL_TYPE_STRING :
						if(count%2!=0){
							xpath=cell.getStringCellValue();
							System.out.println("cell CELL_TYPE_STRING xpath "+xpath);
						}
						else{
							newValue =cell.getStringCellValue();
							System.out.println("cell CELL_TYPE_STRING newValue "+newValue);
						}
						System.out.println("cell CELL_TYPE_STRING xpath newValue value"+cell.getStringCellValue());
						//update newvalue in xml
					//	ReadSQLFileUtil.updateMutipleTagValues(s,xpath,newValue);
						count++;
						break;
					case Cell.CELL_TYPE_NUMERIC:
						if(count%2!=0){
							xpath=String.valueOf(cell.getNumericCellValue());
							System.out.println("cell CELL_TYPE_NUMERIC xpath "+xpath);
						}
						else{
							newValue =String.valueOf(cell.getNumericCellValue());
							System.out.println("cell CELL_TYPE_NUMERIC newValue "+newValue);
						}
						System.out.println("cell CELL_TYPE_STRING xpath newValue value"+cell.getNumericCellValue());
					//	ReadSQLFileUtil.updateMutipleTagValues(s,xpath,newValue);
						count++;
						break;
					}
					xpathAndNewValueMap.put(xpath, newValue);
					System.out.println("cell index value"+xpathAndNewValueMap.put(xpath, newValue));
					System.out.println("cell index rowIndex value"+rowIndex+ " "+xpathAndNewValueMap.get(rowIndex));
					//ReadSQLFileUtil.updateMutipleTagValues(s,xpath,newValue);
					ReadSQLFileUtil.updateXMLTagValExcel(s,xpathAndNewValueMap,xpath);
					
			}
			}
			//String s =generateAIXMLFile(fredaixmlTmpltFilename,templateFilePath,dbServerName,fredDBName,aixmlCopyTempFldrPath,rowIndex);
		//	ReadSQLFileUtil.updateXMLTagVal(s,xpathAndNewValueMap);
			
			
			//listTag.add(a);
		}
//		return listTag;
		
		return xpathAndNewValueMap;
	}

		/*public static void compareExcelvalwithICNTag() throws Exception{
		List<String> b=	readExcelAIXML();
		for(int i=1;i<b.size();i++){
			System.out.println("Excel val: "+b.get(i));
			String c =b.get(i);
		
		}
			
		
		}
		
		public static void validateItem(String c) throws Exception{
			List<String> b=	readExcelAIXML();
			Map<String, String> icnItemIdKeyVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemId);
			String itemIdKey =getMapKey(icnItemIdKeyVal);
			System.out.println("ItemId Key:"+itemIdKey+"::ItemId KeyValue Pair:"+icnItemIdKeyVal);
		boolean flag=Component.verifyEquals(itemIdKey, b.get(0), "ItemID Validation");
		System.out.println("itemIdKey"+itemIdKey);
		System.out.println("b get 0"+b.get(0));
		System.out.println("ItemID"+flag);
		}
		
		public static void validateItemSer(String c) throws Exception{
			Map<String, String> icnItemIdKeyVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnItemCodelineXpath);
			String itemIdKey =getMapKey(icnItemIdKeyVal);
			System.out.println("ItemId Key:"+itemIdKey+"::ItemId KeyValue Pair:"+icnItemIdKeyVal);
		boolean flag=Component.verifyEquals(itemIdKey, c, "ItemID Validation");
		
		System.out.println(flag);
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
	*/
		public static void generate3000ItemsAixml(String fredaixmlTmpltFilename,String templateFilePath,String dbServerName,String fredDBName,String aixmlCopyTempFldrPath) throws Exception{
			for(int i=1;i<=2;i++){
				Map<String,String> xpathAndNewValueMap = readExcelAIXML(fredaixmlTmpltFilename,templateFilePath,dbServerName,fredDBName,aixmlCopyTempFldrPath);
				String s =generateAIXMLFile(fredaixmlTmpltFilename,templateFilePath,dbServerName,fredDBName,aixmlCopyTempFldrPath,i);
			
				System.out.println("generate3000ItemsAixml xpathAndNewValueMap "+xpathAndNewValueMap+" "+s);
				ReadSQLFileUtil.updateXMLTagVal(s,xpathAndNewValueMap);
				
			//	Thread.sleep(2000);
			}
			
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
			String xpathAIXMLCustomFieldsItemID ="//AlogentPaymentsGateway/Jobs/Job/Blocks/Block/Batches/Batch/Items/Item/CustomFields/cf_ICSItemID";
			Map<String,String> map=new LinkedHashMap<String,String>();	
		//	Map<String,String> mapExcelData= readExcelAIXML();
			int blockNo  = GenericMethods.generateUniqueNo(4);
					
		//	System.out.println("Block Number: " + blockNo);		
			String sqlConfigBusinessDate ="select Value from Config.BusinessConfig where [Key]='BDATE'";
			ResultSet rsConfigBusinessDate = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredDBName, sqlConfigBusinessDate);
			String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(rsConfigBusinessDate);
			String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
			
			//ItemId generation
		
			long milliSec = System.currentTimeMillis();
			
		//	System.out.println("MilliSec :"+milliSec);
			String strMilliSec = String.valueOf(milliSec);
			strMilliSec = strMilliSec.substring(strMilliSec.length()-8);
			int itemId1 = GenericMethods.generateUniqueNo(2);
			String itemId = String.valueOf(itemId1);
			
			
			
		/*	map.put(xpathAIXMLJobBusinessDate, configDateValue);
			map.put(xpathAIXMLBlkNbr, String.valueOf(blockNo));
		*/	
			/*for(int i=0;i<=2;i++){
				String cfsItemId =  generateItemId(itemId,strMilliSec,String.valueOf(counter));
				map.put(xpathAIXMLCustomFieldsItemID, cfsItemId);
			}*/
		
		//	ReadSQLFileUtil.updateXMLTagVal(aixmlFilePathtemp,mapExcelData);
		/*	Map<String,String> cfsItmd = getXMLNodeValueByXPATH(aixmlCopyTempFldrPath,aixmlFileNameCreated, xpathAIXMLCustomFieldsItemID);
				String cfsItemId =  generateItemId(itemId,strMilliSec,String.valueOf(counter));
				map.put(xpathAIXMLCustomFieldsItemID, cfsItemId);
		*/
		//	ReadSQLFileUtil.updateMutipleTagValues(aixmlFilePathtemp,xpathAIXMLCustomFieldsItemID,cfsItemId);
			//return aixmlFilePathTobeUpdated;
		}
		
		public static String generateItemId(String fileType,String strMilliSec,String counter){	
			String dateTimeFormat = "yyyy.MM.dd.HH.mm.ss";
			String firstpart = getRequiredDateFormat(dateTimeFormat)
					.format(getTimeStamp(System.currentTimeMillis())).replace(".","");
			//firstpart = firstpart.substring(firstpart.length()); //10
			String lastpart = firstpart.substring(firstpart.length()-4);		 //4
			//System.out.println("CFSItemID "+firstpart + fileType+ "IN" +lastpart);
			System.out.println("before counter :"+strMilliSec);
			String returnVal =firstpart +"CrOC"+strMilliSec+counter;
			//counter=counter+1;
			//System.out.println("strMilliSec :"+returnVal);
			return  returnVal;  //14+3+4+4 //10+2+4+4
		}
		

	}
