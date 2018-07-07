package com.ics.seleniumCoreUtilis;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.security.Key;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.Scanner;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenericMethodUtilis {
	private static String icsEnvironment = "environment/runTime/ics_environment" ;
	public static String inputXlsTestDataBasePath = getICSEnvironment(icsEnvironment).getString("inputXlsTestDataBasePath") ;
	private static String outputXlsTestDataBasePath = getICSEnvironment(icsEnvironment).getString("outputXlsTestDataBasePath") ;
	public static String inputXmlTestDataBasePath = getICSEnvironment(icsEnvironment).getString("inputXmlsTestDataBasePath") ;
	public static String outputXmlTestDataBasePath = getICSEnvironment(icsEnvironment).getString("outputXmlsTestDataBasePath") ;
	private static String sqlFileTestDataBasePath = getICSEnvironment(icsEnvironment).getString("sqlFileTestDataBasePath") ;
	private static String xsdFileTestDataBasePath = getICSEnvironment(icsEnvironment).getString("xsdFileTestDataBasePath") ;
	private static String moInputRegTestDataFileName = "mo_regresion_input_data.xls";
	private static String moOutputTestDataFileName = "mo_regression_output_data.xls";
	public static String agencyInputTestDataFileName = "\\agency_testData.xls";
	private static String rneInputTestDataFileName = "\\rne_testData.xls";
	private static String iaInputTestDataFileName = "\\TestData_IA.xls";
	private static String referenceDataInputTestDataFileName = "\\ReferenceData_Properties.xls";
	public static String fileDropPathFredToMo = "\\\\129.227.32.253\\d$\\User Data\\Surendra\\Simulator\\FM01\\Simulater\\FileDrop\\" ;
	public static String simulatorPathFredToMo = "\\\\129.227.32.253\\d$\\User Data\\Surendra\\Simulator\\FM01\\Simulater\\";
	public static String simulatorNameFredToMo = "DropMessageToQueue.exe";
	public static String outputTestReportsBasePath = getICSEnvironment(icsEnvironment).getString("inputXlsTestDataBasePath");
	public static String agencyPropertiesFile = "agency";
	public static String refDataPropertiesFile = "referenceData";
	public static String dewPropertiesFile = "dew";
	public static String iaPropertiesFile = "ia";
	public static String referencedataPropertiesFile = "referencedata";
	public static String fredPropertiesFile = "fred";
	public static String cmPropertiesFile = "casemanagement";	
	public static String moPropertiesFile = "mo";
	public static String rNePropertiesFile="rNe";
	public static ExtentReports EXTENT ;
	public static ExtentTest EXTENTLOG ;
	public static String actualISOContent, actualICNContent;
	public static boolean finalTestScriptResultFailFlag ;
	public static String directoryPathSeperationSymbol = "\\";
	private static String inputDataSheetColumnAsTestCase = "Test Case" ;
	private static String inputDataSheetColumnAsKey = "Key" ;
	private static String inputDataSheetColumnAsValue = "Value" ;
	private static String inputDataSheetColumnAsValueClient = "ValueLBG" ;
	private static int cellIndexForColumnNameAsTestCase ;
	private static int cellIndexForColumnNameAsKey ;
	private static int cellIndexForColumnNameAsValue ;
	private static int cellIndexForColumnNameAsValueLBG ;
	private static int lastRowNumber ;	
	public static String userDirectory;
	public static ResultSet resultSet ;
	public static String resultSetValue ;
	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver" ;
	private static String sqlFileInjectCommand ;
	public DateFormat dateFormat_1 = new SimpleDateFormat("yyyy.MM.dd_HH:mm:ss");
	public DateFormat dateFormat_2 = new SimpleDateFormat("HHmm");
	public DateFormat dateFormat_3 = new SimpleDateFormat("yyyy-MM-dd");
	public DateFormat dateFormat_4 = new SimpleDateFormat("kk:mm");
	public DateFormat dateFormat_5 = new SimpleDateFormat("HHmm");
	public DateFormat dateFormat_6 = new SimpleDateFormat("HHss");
	public DateFormat dateFormat_7 = new SimpleDateFormat("yyyyMMddHHmmss");
	public DateFormat dateFormat_8 = new SimpleDateFormat("yyyy-MM-dd kk:mm");
	public DateFormat dateFormat_9 = new SimpleDateFormat("yyyyHHmmss");
	public DateFormat dateFormat_10 = new SimpleDateFormat("yyyyMMddHHmmss");
	public static String workFlow13SM01 = "13SM01", workFlow13MM01 = "13MM01", workFlow13MA04 = "13MA04", workFlow13MA01 = "13MA01", workFlow13MA02 = "13MA02";
	public static String workFlow13MD01 = "13MD01", workFlow13MD02 = "13MD02", workFlow13DM02 = "13DM02", workFlow13DM01 = "13DM01", workFlow05SM01 = "05SM01";
	public static String workFlow05MA01 = "05MA01", workFlow05MF01 = "05MF01", workFlow05FM01 = "05FM01", workFlow05MA02 = "05MA02", workFlow05MK01 = "05MK01";
	public static String workFlow05KM01 = "05KM01", workFlow05MA03 = "05MA03", workFlow06SM01 = "06SM01", workFlow06MA01 = "06MA01", workFlow06MF01 = "06MF01";
	public static String workFlow06FM01 = "06FM01", workFlow06MA02 = "06MA02",workFlow06MK01 = "06MK01", workFlow06KM01 = "06KM01", workFlowPTMA01 = "PTMA01";
	public static String workFlowPTMR01 = "PTMR01", workFlowPERM01 = "PERM01", workFlowPEMA01 = "PEMA01", workFlowPRRM01 = "PRRM01", workFlowPRMA01 = "PRMA01";
	public static String workFlowPRMD01 = "PRMD01", workFlow06MA03 = "06MA03", workFlow06MD01 = "06MD01", workFlow06DM01 = "06DM01", workFlow06MA04 = "06MA04";
	public static String workFlow07MS01 = "07MS01", workFlow07MA01 = "07MA01", workFlow08SM01 = "08SM01", workFlow08MA01 = "08MA01", workFlowXsd = "xsd";
	public static String workFlow04SM01 = "04SM01" , workFlow04MA01 = "04MA01", workFlow11SM01 = "11SM01", workFlow11MA01 = "11MA01", workFlow12SM01 = "12SM01"; 
	public static String	workFlow09SM01 = "09SM01", workFlow09MA01 = "09MA01", workFlow12MA01 = "12MA01", workFlow11MA02 = "11MA02", workFlow12MA02 = "12MA02";
	public static String workFlow13MA99 = "13MA99", workFlow01FM01 = "01FM01" , workFlow01MA01 = "01MA01", workFlow01MD01 = "01MD01", workFlow01DM01 = "01DM01" ;
	public static String workFlow01MK01 = "01MK01" , workFlow01KM01 = "01KM01" , workFlow01MA02 = "01MA02" , workFlow01MA03 = "01MA03", workFlow01MM01 = "01MM01",
			workFlow01MA04 = "01MA04", workFlow01MA05 = "01MA05", workFlow01MS01 = "01MS01", workFlow01MA06 = "01MA06", workFlow02SM01 = "02SM01", workFlow02MA01 = "02MA01", workFlow15SM01 = "15SM01", workFlow15MA01 = "15MA01", 
					workFlow01MD02 = "01MD02", workFlow01DM02 = "01DM02", workFlow03SM01 = "03SM01", workFlow03MA01 = "03MA01", workFlowREXM01 = "REXM01", workFlowREMA01 = "REMA01";
	public static String sql06FM01 = "06FM01.sql", sql01FM01 = "01FM01.sql";
	public static String sql13DM01 = "13DM01.sql";
	public static String sql13DM02 = "13DM02.sql";
	public static String sqlPERM01 = "PERM01.sql";
	public static String txtPERM01 = "PERM01.txt";
	public static String sqlPRRM01 = "PRRM01.sql";
	public static String sql06DM01 = "06DM01.sql";
	public static String sql05FM01 = "05FM01.sql";
	public static ArrayList<String> expectedTsetId = new ArrayList<String>();
	public static ArrayList<String> expectedTxVersion = new ArrayList<String>();
	public static ArrayList<String> dayOneResponseWindow = new ArrayList<String>();
	public static ArrayList<String> dayTwoResponseWindow = new ArrayList<String>();
	public static ArrayList<String> expectedCreditItemId = new ArrayList<String>();
	public static ArrayList<String> expectedDebitItemId = new ArrayList<String>();
	public static ArrayList<String> expectedSourceId = new ArrayList<String>();
	public static ArrayList<String> extractId = new ArrayList<String>();
	private static final Logger genericMethodUtilsLog = Logger.getLogger(GenericMethodUtilis.class.getSimpleName()) ;
	public static final String comparisionReportStatement = "Please find the differences of above comparison as follows: <br/> <br/>";
	public static String exceptionMessage = "Exception Message : <br/> ";
	public static String exceptionTrace = "Exception Trace :  <br/>";
	public static final String forwardSlash = "\\" , newLine = "\n";
	public static List<String> workFlowList = new ArrayList<String>();

	protected static WebDriver startDriver(){
		return new InternetExplorerInitializer().initialize();
	}

	/*
	 * Modified By: Abir Pramanik, 16-Feb-2017
	 * Change Details: Added to initiate driver with a new url.
	 */
	public static WebDriver startDriverWithUrl(WebDriver driver){
		//driver.close();
		startDriver();
		return driver;
	}

	public static String getSystemLoginUserName()
	{
		return System.getProperty("user.name");
	}
	public static SimpleDateFormat getRequiredDateFormat(String dateFormat)
	{
		return new SimpleDateFormat(dateFormat);
	}

	public static String getSystemDateWithRequiredFormat(String dateFormat){			
		return  getRequiredDateFormat(dateFormat).format(new Date());	
	}

	public static Timestamp getTimeStamp(long timeStamp)
	{
		return new Timestamp(timeStamp);
	}

	public static File getFileStreamWithoutExtension(String filePath, String fileName)
	{
		return new File(filePath + fileName);
	}

	public static File getFileStreamWithExtension(String filePath, String fileName, String extension)
	{
		return new File(filePath + fileName + extension);
	}

	public static String readDataOfFileStream(File fileStream) throws IOException
	{
		return FileUtils.readFileToString(fileStream);
	}

	public static void writeDataOfFileStream(File fileStream, String data, boolean overWrite) throws IOException
	{
		FileUtils.writeStringToFile(fileStream, data, overWrite);
	}

	/*
	 * Set the String of UserDirectory-The Base Path of TestSuiteRun project where entire Test-Data and Environment data need to fetch
	 */
	public static void setUserDirectory(String requiredUserDirectory)
	{
		userDirectory = requiredUserDirectory + directoryPathSeperationSymbol ;
	}

	public static String getUserDirectory()
	{
		return userDirectory;
	}

	/* 
	 * Method Name: getRequiredFileName
	 * Author: Nisha Tripathi
	 * Created Date: 15/02/2017
	 * Description: Verify file is available in the path
	 * Last Modified By: Dhanabal Natarajan
	 * Modification made: Logic to loop and locate the file was introduced
	 * Last Modified Date: 16/02/2017
	 */
	public static String getRequiredFileName(String filePath , String fileName)
	{		
		File folder = new File(filePath);
		File[] listofFile = folder.listFiles();
		boolean flag = false;
		Object strFile = null;
		for(int i=0;i<listofFile.length;i++){ 
			strFile = listofFile[i].getName();
			if (fileName.equals(strFile)){
				flag = true;
				break;
			}	
		}
		if(!flag){
			return null;
		}else{
			return strFile.toString();
		}
	}

	/* 
	 * Method Name: decrypt
	 * Author: Rajwinder Kaur
	 * Created Date: 22/09/2017
	 * Description: Decrypts the encrypted password
	 * **Parameters
	 */	
	public static String decrypt(String val) throws Exception
	{
		String key = "Bar12345Bar12345";
		Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, aesKey);
		Base64.Decoder decoder = Base64.getDecoder();
		cipher.init(Cipher.DECRYPT_MODE, aesKey);
		String decrypted = new String(cipher.doFinal(decoder.decode(val)));
		return decrypted;
	}

	/* 
	 * Method Name: ConvertDateInToRequiredFormat
	 * Author: Dhanabal Natarajan
	 * Created Date: 22/02/2017
	 * Description: Converts date in to required format
	 * Last Modified By: Dhanabal Natarajan
	 * Last Modified Date: 22/02/2017
	 * **Parameters
	 * InputFormat - format of the input date for ex: yyyyMMdd
	 * OutputFormat - format of the input date for ex: yyyy-MM-dd
	 * InputDate - Date to be converted for ex:20170222
	 */
	public static String ConvertDateInToRequiredFormat(String InputFormat, String OutputFormat, String InputDate) throws ParseException{
		Date moddate = new SimpleDateFormat(InputFormat).parse(InputDate);
		return (new SimpleDateFormat(OutputFormat).format(moddate));
	}

	public static Document getParsedXMLData(File fileStream) throws SAXException, IOException, ParserConfigurationException
	{
		DocumentBuilderFactory dbFactory=DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder=dbFactory.newDocumentBuilder();
		return dBuilder.parse(fileStream);		
	}

	/* 
	 * Method Name: getXMLNodeValue
	 * Author: Dhanabal Natarajan
	 * Created Date: 06/03/2017
	 * Description: Retrieves Node(s) value of the XML based on the XPATH definition. Returns Hash Map string in a key value pair.
	 * Last Modified By: Dhanabal Natarajan
	 * Last Modified Date: 06/03/2017
	 * **Parameters
	 * filePath - Path of the File ex: //129.227.33.35/f$/AutomationTestData/output/
	 * fileName - Name of the file along with extension ex: ICNTest.xml
	 * xPath - XPATH expression example1://Entity; example2://DbtItmId[text()='abc']/following-sibling::DbtItmTp
	 */
	public static Map<String, String> getXMLNodeValueByXPATH(String filePath, String fileName, String xPath) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException{
		Document doc = getParsedXMLData(new File(filePath+fileName));
		XPath xpath = XPathFactory.newInstance().newXPath();
		XPathExpression expr = xpath.compile(xPath);
		Object result = expr.evaluate(doc, XPathConstants.NODESET);
		NodeList nodes = (NodeList) result;
		Map<String, String> nodeValue = new LinkedHashMap<String, String>();

		int key=1;
		for (int i=0;i<nodes.getLength();i++){
			nodeValue.put(nodes.item(i).getNodeName()+key, nodes.item(i).getTextContent());
			key+=1;
		}
		return nodeValue;
	}

	/*
	 * Method to get required PIT test-data with filter to specific key selection of sheet name of specific Input Test-Data Excel 
	 * 
	 */
	public static String getRequiredStTestData(String filePath, String sheetName, String testCase, String keyData)
	{
		String tempValueName = null;
		try
		{
			HSSFSheet tempSheet = getSheet(filePath, sheetName);
			lastRowNumber = tempSheet.getPhysicalNumberOfRows();
			stTestDataSheetColumnNames(filePath, sheetName);		
			for(int eachRow = tempSheet.getFirstRowNum() + 1 ; eachRow < lastRowNumber ; eachRow++)
			{
				String tempTestCaseName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsTestCase).toString().trim();
				String tempKeyName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsKey).toString().trim();
				if((tempTestCaseName.equals(testCase.trim())) && (tempKeyName.equals(keyData.trim())))
				{
					tempValueName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsValue).toString().trim();
					break;
				}
			}
		}
		catch(Exception e)
		{
			validationErrorInformation(e.getMessage());
		}
		return tempValueName;
	}	

	/*
	 * Method to get required test-data with filter to specific key selection of sheet name of specific Input Test-Data Excel
	 * without testcase field 
	 * 
	 */
	public static String getRequiredTestData(String filePath, String sheetName,String keyData)
	{
		String tempValueName = null;
		try
		{
			HSSFSheet tempSheet = getSheet(filePath, sheetName);
			lastRowNumber = tempSheet.getPhysicalNumberOfRows();
			stTestDataSheetColumnNames(filePath, sheetName);		
			for(int eachRow = tempSheet.getFirstRowNum() + 1 ; eachRow < lastRowNumber ; eachRow++)
			{
				String tempKeyName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsKey).toString().trim();
				if((tempKeyName.equals(keyData.trim())))
				{
					Cell cellToWatch = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsValue);
					if(cellToWatch.getCellType() == Cell.CELL_TYPE_FORMULA)
					{
						tempValueName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsValue).getRichStringCellValue().toString().trim();
						break;
					}
					else
					{
						tempValueName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsValue).toString().trim();
						break;
					}
				}
			}
		}
		catch(Exception e)
		{

			validationErrorInformation(e.getMessage());
		}
		return tempValueName;
	}	

	/*
	 * Method to get required test-data with filter to specific key selection of sheet name of specific Input Test-Data Excel
	 * without testcase field 
	 * 
	 */
	public static String getRequiredTestDataAgency(String filePath, String sheetName,String keyData,String value)
	{
		String tempValueName = null;
		try
		{
			HSSFSheet tempSheet = getSheet(filePath, sheetName);
			lastRowNumber = tempSheet.getPhysicalNumberOfRows();
			stTestDataSheetColumnNames(filePath, sheetName);
			if(value.equalsIgnoreCase("HSBC")){
				for(int eachRow = tempSheet.getFirstRowNum() + 1 ; eachRow < lastRowNumber ; eachRow++)
				{
					String tempKeyName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsKey).toString().trim();
					if((tempKeyName.equals(keyData.trim())))
					{
						tempValueName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsValue).toString().trim();
						break;
					}
				}
			}
			else if (value.equalsIgnoreCase("LBG")){
				for(int eachRow = tempSheet.getFirstRowNum() + 1 ; eachRow < lastRowNumber ; eachRow++)
				{
					String tempKeyName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsKey).toString().trim();
					if((tempKeyName.equals(keyData.trim())))
					{
						tempValueName = tempSheet.getRow(eachRow).getCell(2).toString().trim();
						break;
					}
				}
			}
		}
		catch(Exception e)
		{
			validationErrorInformation(e.getMessage());
		}
		return tempValueName;
	}	

	public static String getWorkingDirectoryPath(){
		File f = new File(System.getProperty("user.dir"));
		return(f.getPath());
	}

	/*
	 * Method to get required agency specific property file details
	 * 
	 */
	public static String getAgencyValueFromDataSheet(String keyData)
	{	
		String data=null;
		String value = getBrandingValueFromDataSheet("clientBranding");
		String dirPath = getWorkingDirectoryPath();
		String path = dirPath + directoryPathSeperationSymbol +inputXlsTestDataBasePath+agencyPropertiesFile+agencyInputTestDataFileName;
		if(value.equalsIgnoreCase("HSBC")){
			data = getRequiredTestDataAgency(path,agencyPropertiesFile,keyData,value);
		}
		else if (value.equalsIgnoreCase("LBG")){
			data = getRequiredTestDataAgency(path,agencyPropertiesFile,keyData,value);
		}
		return data;
	}

	/*
	 * Method to fetch the client branding value from datasheet
	 * 
	 */
	public static String getBrandingValueFromDataSheet(String keyData)
	{	
		String dirPath = getWorkingDirectoryPath();
		String path = dirPath + directoryPathSeperationSymbol +inputXlsTestDataBasePath+agencyPropertiesFile+agencyInputTestDataFileName;
		return getRequiredTestData(path,agencyPropertiesFile,keyData);
	}

	/*
	 * Method to get required RnE specific property file details
	 * 
	 */
	public static String getRnEValueFromDataSheet(String keyData)
	{	
		String dirPath = getWorkingDirectoryPath();
		String path = dirPath + directoryPathSeperationSymbol +inputXlsTestDataBasePath+rNePropertiesFile+rneInputTestDataFileName;
		return getRequiredTestData(path,rNePropertiesFile,keyData);
	}


	/*
	 * Method to get required IA specific property file details_excel sheet to fetch key value pair
	 * 
	 */
	public static String getIAValueFromDataSheet(String keyData)
	{	
		String dirPath = getWorkingDirectoryPath();
		String path = dirPath + directoryPathSeperationSymbol +inputXlsTestDataBasePath+iaPropertiesFile+iaInputTestDataFileName;
		return getRequiredTestData(path,iaPropertiesFile,keyData);
	}

	/*
	 * Method to get the path for IA value_sql and xml data loading files
	 * 
	 */
	public static String getIADataFromPathXml()
	{	
		String dirPath = getWorkingDirectoryPath();
		String path = dirPath + directoryPathSeperationSymbol +inputXmlTestDataBasePath+iaPropertiesFile +directoryPathSeperationSymbol;
		return path;
	}

	/*
	 * Method to get the path for IA value_sql and xml data loading files
	 * 
	 */
	public static String getIADataFromPathXmlForNode()
	{	
		String dirPath = getWorkingDirectoryPath();
		String path = dirPath + directoryPathSeperationSymbol +inputXmlTestDataBasePath+iaPropertiesFile + directoryPathSeperationSymbol;
		return path;
	}

	/*
	 * Method to get the path for IA value
	 * 
	 */
	public static String getIADataFromPathSql()
	{	
		String dirPath = getWorkingDirectoryPath();
		String path = dirPath + directoryPathSeperationSymbol +sqlFileTestDataBasePath+iaPropertiesFile;
		return path;
	}

	/*
	 * Method to get the path for agency value
	 * 
	 */
	public static String getAgencyDataFromPath(String value)
	{	
		String dirPath = getWorkingDirectoryPath();
		String path = dirPath + directoryPathSeperationSymbol +inputXmlTestDataBasePath+agencyPropertiesFile+directoryPathSeperationSymbol+value;
		return path;
	}


	/* 
	 * Method Name: getRefDataValueFromDataSheet
	 * Author: Hemapriya Shanmugam
	 * Created Date: 24/08/2017
	 * Description: To Get All Property file values for Reference Data  
	 */
	public static String getRefDataValueFromDataSheet(String keyData)
	{	
		String dirPath = getWorkingDirectoryPath();
		String path = dirPath + directoryPathSeperationSymbol +inputXlsTestDataBasePath+refDataPropertiesFile+referenceDataInputTestDataFileName;
		return getRequiredTestData(path,refDataPropertiesFile,keyData);
	}
	/* 
	 * Method Name: Get List of Tag values
	 * Author: Nisha Tripathi
	 * Created Date: 06/03/2017
	 * Description: Return List of Tag values
	 * **Parameters
	 * strNodeVal - Object returned by Map getXMLNodeValueByXPATH method
	 * tagName - TagName
	 * 
	 */

	public static List<String> getNodeListValues(Map<String, String> strNodeVal, String tagName){
		List<String> listOfString = new ArrayList<String>();
		for(int i=1;i<=strNodeVal.size();i++){
			listOfString.add(strNodeVal.get(tagName+i));
		}
		return listOfString;
	}

	/* 
	 * Method Name: DeleteFile
	 * Author: Dhanabal Natarajan
	 * Created Date: 22/02/2017
	 * Description: Deletes specific file in the path
	 * Last Modified By: Dhanabal Natarajan
	 * Last Modified Date: 22/02/2017
	 * **Parameters
	 * filePath - Folder path of the file
	 * fileName - Name of the file
	 * fileExtension - Extension of the file Ex: .xml
	 */
	public static void DeleteFile(String filePath, String fileName, String fileExtension){
		File f = new File(filePath + fileName + fileExtension);
		if(f.exists()){
			f.delete();
		}
	}

	/* 
	 * Method Name: CopyFileToDestinationPath
	 * Author: Dhanabal Natarajan
	 * Created Date: 21/02/2017
	 * Description: Copies file from source path to destination path
	 * Last Modified By: Dhanabal
	 * Last Modified Date: 21/02/2017
	 * **Parameters
	 * fileName - Name of the file to be copied
	 * srcFilePath - Source path where the file was located
	 * destFilePath - Destination path where the file to be moved
	 */

	public static Boolean CopyFileToDestinationPath(String fileName, String srcFilePath, String destFilePath) throws IOException, InterruptedException
	{
		File srcFile = new File(srcFilePath+fileName);
		File destFile = new File (destFilePath+fileName);
		FileUtils.copyFile(srcFile, destFile);

		//wait for file to be consumed by the system
		long act_time=2000;
		while (destFile.exists()){
			if (act_time>240000){
				break;
			}
			Thread.sleep(2000);
			act_time = (act_time+2000);
			System.out.println("Act Time"+act_time);
		}

		return (!destFile.exists());
	}

	public static String getXMLFilePathOfRegressionCyle1(String regressionCycle, String sheet, String testScriptName, String keyName)
	{
		String tempPath = null;
		tempPath = getRequiredStTestData(regressionCycle, sheet.replaceAll("Test", "test"), testScriptName, 
				keyName);
		return tempPath;
	}

	public static Map<String, String> getXmlFileData(String regresionCycle, String xpath,String sheetName, String testCase, String xmlFileName, String key)
			throws Exception {
		getXMLFileName(regresionCycle, sheetName,testCase, xmlFileName);
		Map<String, String> xmlFileData = getXMLNodeValueByXPATH(userDirectory + inputXmlTestDataBasePath + "\\" + regresionCycle +   "\\" + sheetName.replaceAll("Test", "test") + "\\" + testCase + "\\", getXMLFileName(regresionCycle, sheetName,testCase, xmlFileName), xpath);
		return xmlFileData;
	}

	public static String getXMLFileName(String regresionCycle , String sheet,String testCaseID, String xmlFileNameKey)
	{
		String tempFileName =  getRequiredStTestData(getInputSheetOfMoRegression(regresionCycle), sheet, testCaseID, xmlFileNameKey);
		return tempFileName;		
	}

	public static ArrayList<String> setOutputFileData(String regresionCycle, String filePath, String sheetName, String testCase, String key, String xmlFileName, String xPath,
			String tagName) throws Exception {
		ArrayList<String> NodeValuesList = (ArrayList<String>) getNodeListValues(getXmlFileData(regresionCycle, xPath, sheetName, testCase, xmlFileName, key), tagName);
		//		setStOutputTestData(filePath, sheetName, sheetName, key, NodeValuesList);
		return NodeValuesList;
	}

	/*
	 * Author: Darpan Yashlaha
	 * Date Modified: 10-Mar-2017
	 * Change Details: Methods for writing multiple data to excel sheet.
	 */

	public static void setStOutputTestData(String filePath, String sheetName, String testCase, String key, ArrayList<String> value) throws FileNotFoundException, IOException
	{
		String output;
		File file = new File(filePath);
		file.setWritable(true, false);
		FileInputStream fis = new FileInputStream(filePath);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet tempSheet = wb.getSheet(sheetName);
		lastRowNumber = tempSheet.getPhysicalNumberOfRows();
		stTestDataSheetColumnNames(filePath, sheetName);
		for(int eachRow = tempSheet.getFirstRowNum() + 1 ; eachRow < lastRowNumber ; eachRow++)
		{
			String tempTestCaseName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsTestCase).toString().trim();
			String tempScenarioName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsKey).toString().trim();
			if((tempTestCaseName.equals(testCase.trim())) && (tempScenarioName.equals(key.trim())))
			{
				HSSFRow row = tempSheet.getRow(eachRow);
				for(int i = 0; i<value.size(); i++)
				{
					output = value.get(i);
					HSSFCell cell = row.createCell(cellIndexForColumnNameAsValue + i);
					cell.setCellValue(output);
				}
				break;
			}
		}
		fis.close();

		FileOutputStream fos = new FileOutputStream(filePath);
		wb.write(fos);
		wb.close();
		fos.close();
		file.setReadable(true, false);
	}

	/*
	 * Method to get the File Name of - MO Input Test Data
	 */
	public static String getInputSheetOfMoRegression(String regressionCyle)
	{
		return getInputXlsTestDataLocationPath() + regressionCyle + "\\" + moInputRegTestDataFileName ;
	}

	public static void fileCopy(String sourcePath, String targetPath, String tempFileNAme) throws IOException
	{
		File sourePath1 = new File(sourcePath);
		File targetPath1 = new File("\\" + targetPath + "\\" +tempFileNAme);
		FileUtils.copyFile(sourePath1, targetPath1);
	}

	/*
	 * Method to get the File Name of - MO Output Test Data
	 */
	public static String getOuputSheetOfMoRegressionMsg06()
	{
		return getOutputXlsTestDataLocationPath() + moOutputTestDataFileName ;
	}

	/******************************************************************************************************************
	/* Method Name: getExcelSheetName																		      
	/* Author: Keerthiga Sankara Pandian																			  
	/* Created Date:  14-Mar-2017																					  
	/* Description: Added a method to fetch the sheet name from which the output data should be retrieved	  
	/* Parameters:
	/* object: fetches the current object
	/******************************************************************************************************************/
	public static String getExcelSheetName(Object object){
		String packageName = object.getClass().getName();
		return packageName.substring(packageName.indexOf("tests"));				
	}

	public static ResourceBundle getAgencyResourceFile(){
		return getBundle(agencyPropertiesFile);
	}

	public static ResourceBundle getIAResourceFile(){
		return getBundle(iaPropertiesFile);
	}

	public static ResourceBundle getDEWResourceFile(){
		return getBundle(dewPropertiesFile);
	}

	/*
	 * Modified By: Darpan Yashlaha, 14-Feb-2017
	 * Change Details: Added method for reading reference data properties file.
	 */
	public static ResourceBundle getReferenceDataResourceFile()
	{
		return getBundle(referencedataPropertiesFile);
	}

	/*
	 * Modified By: Dhanabal Natarajan, 21-Feb-2017
	 * Change Details: Created a method for FRED.
	 */
	public static ResourceBundle getFredResourceFile(){
		return getBundle(fredPropertiesFile);
	}

	/*
	 * Modified By: Keerthiga Sankara Pandian, 16-Feb-2017
	 * Change Details: Method for reading reference data properties file.
	 */
	public static ResourceBundle getCMResourceFile(){
		return getBundle(cmPropertiesFile);
	}

	/*
	 * Modified By: Rohini Reddy Veeram, 16-Feb-2017
	 * Change Details: Method for reading reference data properties file.
	 */
	public static ResourceBundle getMOResourceFile()
	{
		return getBundle(moPropertiesFile);
	}

	public static ResourceBundle getBundle(String fileName)
	{
		return ResourceBundle.getBundle(fileName);
	}	

	/*
	 * Modified By: Himanshu Malhotra, 23-Feb-2017
	 * Change Details: Method for reading rNe properties file.
	 */
	public static ResourceBundle getrNeResourceFile()
	{
		return getBundle(rNePropertiesFile);
	}

	public static String getICSRetrieveColumnValue(ResultSet resultSet) throws Exception
	{
		resultSetValue = "";
		while(resultSet.next())
		{
			resultSetValue  = resultSet.getString(1);
			break;
		}	
		return resultSetValue;
	}

	public static void validationStepInformation(String validationHeader)
	{
		EXTENTLOG.log(LogStatus.INFO, validationHeader);
	}

	public static void validationErrorInformation(Object object)
	{
		finalTestScriptResultFailFlag = true;
		EXTENTLOG.log(LogStatus.ERROR, object.toString());
	}

	public static void publishResults(boolean result, String actual, String expected, String message)
	{
		if(!result)
		{
			finalTestScriptResultFailFlag = true;
			EXTENTLOG.log(LogStatus.FAIL, "Step : "+message + "<br/>Expected: " + expected + "<br/>Actual: " + actual);
		}
		else
		{
			EXTENTLOG.log(LogStatus.PASS, "Step : "+message + "<br/>Expected: " + expected + "<br/>Actual: " + actual);		
		}
	}

	/*
	 * Input xls Directory - Base Path
	 */
	public static String getInputXlsTestDataLocationPath()
	{
		return userDirectory + inputXlsTestDataBasePath ;
	}

	/*
	 * Output xls Directory - Base Path
	 */
	public static String getOutputXlsTestDataLocationPath()
	{
		return userDirectory + outputXlsTestDataBasePath ;
	}

	/*
	 * Input xls Directory - Base Path
	 */
	public static String getSqlsTestDataLocationPath()
	{
		return userDirectory + sqlFileTestDataBasePath ;
	}

	/*
	 * Input xls Directory - Base Path
	 */
	public static String getXsdTestDataLocationPath()
	{
		return userDirectory + xsdFileTestDataBasePath ;
	}

	/*
	 * Path of ICSEnvironment
	 */
	private static ResourceBundle getICSEnvironment(String icsEnvironment)
	{
		return getBundle(icsEnvironment);
	}

	/*
	 * Method to get the ResultSet for db query of required DBServerName and its DBName
	 */
	public static ResultSet getICSDBServerConnection(String serverName, String dbName, String dbQuery) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{		
		Statement dbStatementConnect  = dbConnect(serverName,dbName);
		if(dbStatementConnect.execute(dbQuery))
		{
			resultSet = dbStatementConnect.executeQuery(dbQuery);			
		}
		return resultSet;
	}

	public static int insertRecordInIcsDB(String serverName, String dbName, String dbQuery) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Statement dbStatementConnect  = dbConnect(serverName,dbName);
		return dbStatementConnect.executeUpdate(dbQuery);
	}

	/*
	 * Method to get required PIT test-data with filter on sheetName selection of specific Input Test-Data Excel 
	 */
	private static void stTestDataSheetColumnNames(String filePath, String sheetName) throws FileNotFoundException, IOException
	{
		Iterator<Cell> cells = getSheet(filePath, sheetName).getRow(getSheet(filePath, sheetName).getFirstRowNum()).cellIterator();
		int tempCellCount = 0 ;
		while(cells.hasNext())
		{
			String tempCellValue = cells.next().toString().trim();
			if((tempCellValue).equals(inputDataSheetColumnAsTestCase.trim()))
			{
				cellIndexForColumnNameAsTestCase = tempCellCount; 
			}
			else if((tempCellValue).equals(inputDataSheetColumnAsKey.trim()))
			{
				cellIndexForColumnNameAsKey = tempCellCount;
			}
			else if((tempCellValue).equals(inputDataSheetColumnAsValue.trim()))
			{
				cellIndexForColumnNameAsValue = tempCellCount;
			}
			/*else if((tempCellValue).equals(inputDataSheetColumnAsValueClient.trim()))
			{
				cellIndexForColumnNameAsValue = tempCellCount;
			}*/
			tempCellCount++;
		}
	}

	public static void runStoredProcedureCall(String sqlServerName, String sqlDbName, String procedureFilePath, String procedureFileName)
	{
		sqlFileInjectCommand = "cmd /c sqlcmd -S " + sqlServerName + " -d " + sqlDbName + " -i " + procedureFilePath + procedureFileName  + ".sql" ;
		sqlCommandExecution(sqlFileInjectCommand);
	}

	public static void runStoredProcedureCallTest(String sqlServerName, String sqlDbName, String procedureFilePath, String procedureFileName) throws IOException
	{
		sqlFileInjectCommand = "cmd /c sqlcmd -S " + sqlServerName + " -d " + sqlDbName + " -i " + procedureFilePath + procedureFileName  + "_Actual.sql" ;
		sqlCommandExecutionTest(sqlFileInjectCommand);
	}

	public static void executionStoredProcedureCall(String sqlServerName, String sqlDbName, String procedureFilePath, String procedureFileName)
	{
		sqlFileInjectCommand = "cmd /c sqlcmd -S " + sqlServerName + " -i " + procedureFilePath + procedureFileName ;
		sqlCommandExecution(sqlFileInjectCommand);
	}

	public static String getTestDataFileName(String filePath)
	{
		File folder = new File(filePath);
		File[] listofFile = folder.listFiles();
		return listofFile[0].getName();
	}

	/*
	 * Author: Darpan Yashlaha
	 * Date Modified: 10-Mar-2017
	 * Change Details: Methods for writing data to excel sheet.
	 */
	public static void setStOutputTestData(String filePath, String sheetName, String testCase, String key, String value) throws FileNotFoundException, IOException
	{
		File file = new File(filePath);
		file.setWritable(true, false);
		FileInputStream fis = new FileInputStream(filePath);
		HSSFWorkbook wb = new HSSFWorkbook(fis);
		HSSFSheet tempSheet = wb.getSheet(sheetName);
		lastRowNumber = tempSheet.getPhysicalNumberOfRows();
		stTestDataSheetColumnNames(filePath, sheetName);
		for(int eachRow = tempSheet.getFirstRowNum() + 1 ; eachRow < lastRowNumber ; eachRow++)
		{
			String tempTestCaseName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsTestCase).toString().trim();
			String tempScenarioName = tempSheet.getRow(eachRow).getCell(cellIndexForColumnNameAsKey).toString().trim();
			if((tempTestCaseName.equals(testCase.trim())) && (tempScenarioName.equals(key.trim())))
			{
				HSSFRow row = tempSheet.getRow(eachRow);
				HSSFCell cell = row.createCell(cellIndexForColumnNameAsValue);
				cell.setCellValue(value);
				break;
			}
		}
		fis.close();
		FileOutputStream fos = new FileOutputStream(filePath);
		wb.write(fos);
		wb.close();
		fos.close();
		file.setReadable(true, false);
	}

	public static String getStringDataOfXmlFile(String xmlFilePath) throws IOException
	{
		File xmlFile = new File(xmlFilePath);
		Reader fileReader = new FileReader(xmlFile);
		BufferedReader bufReader = new BufferedReader(fileReader);
		StringBuilder sb = new StringBuilder();
		String line = bufReader.readLine();
		while(line!=null)
		{
			sb.append(line);
			line = bufReader.readLine();
		}
		String temp = sb.toString().substring(sb.toString().indexOf("<Document"));
		return temp.replace(" ", "");
	}


	/* 
	 * Method Name: sqlCommandExecution
	 * Author: Nisha Tripathi
	 * Created Date: 15/02/2017
	 * Description: Inject SQL file in to the DB through commandline execution
	 * Last Modified By: Nisha Tripathi
	 * Last Modified Date: 16/02/2017
	 */
	public static String sqlCommandExecution(String strCommand){
		StringBuffer stringBufferStream= new StringBuffer();

		try {
			final Process strSqlCommand = Runtime.getRuntime().exec(strCommand);
			final InputStream in = strSqlCommand.getInputStream();			
			int eachCharacterIndex;
			while ((eachCharacterIndex=in.read())!=-1){
				stringBufferStream.append((char)eachCharacterIndex);
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		} 
		return stringBufferStream.toString();	
	}	

	private static BufferedReader BufferedReader(InputStreamReader inputStreamReader) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void runExeFromCommandPrompt(String filePath, String fileName) throws IOException, InterruptedException
	{
		ProcessBuilder p = new ProcessBuilder();
		p.command(filePath + fileName);
		p.start();		
		Thread.sleep(5000);
	}

	/*
	 * Method to get the File Name of - Web-Loader Input Data
	 */
	public static String getTestReportsBaseLocationPath()
	{
		return userDirectory + outputTestReportsBasePath + "\\extentReportFile.html" ;
	}

	public static String getInputXmlBasePath(String regresionCyle, String testCaseName, String workFlowName)
	{
		return userDirectory  +  inputXmlTestDataBasePath + regresionCyle + "\\" + testCaseName.replaceFirst("Test", "test") + "\\" + workFlowName + "\\" ;
	}

	public static String getOutputXmlBasePath(String regresionCyle, String testCaseName, String workFlowName)
	{
		return userDirectory  +  outputXmlTestDataBasePath + regresionCyle + "\\" + testCaseName.replaceFirst("Test", "test") + "\\" + workFlowName + "\\" ;
	}

	/*
	 * Generic Method to get the access to specific sheet name in excel 
	 */
	private static HSSFSheet getSheet(String filePath, String sheetName) throws FileNotFoundException, IOException{
		return getWorkBook(filePath).getSheet(sheetName);
	}

	/*
	 * Generic Method to get the access to required excel 
	 */
	private static HSSFWorkbook getWorkBook(String filePath) throws FileNotFoundException, IOException {
		return new HSSFWorkbook(new FileInputStream(new File(filePath)));
	}

	/*
	 * Generic Method for Database connect for required ServerName and DBName
	 */
	private static Statement dbConnect(String serverName , String dBName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(driver).newInstance();
		String URL = "jdbc:sqlserver://" + serverName + ";databaseName = " + dBName+ ";integratedSecurity = true";
		return DriverManager.getConnection(URL).createStatement();		
	}

	/*
	 * Generic Method To Sort data in Ascending Order
	 */

	public static boolean verifySortAscending(List<WebElement> elementList ){

		try {
			ArrayList<String> obtainedList = new ArrayList<>();
			for(WebElement rowEle:elementList){			
				obtainedList.add(rowEle.getText());
			}
			ArrayList<String> sortedList = new ArrayList<>();
			for(String s:obtainedList){
				sortedList.add(s);
			}
			Collections.sort(sortedList);
			Assert.assertTrue(sortedList.equals(obtainedList), "Not Sorted in Ascending order");
			return true;
		} catch (AssertionError e) {
			return false;
		}
	}

	/*
	 * Generic Method To Sort data in Descending Order
	 */

	public static boolean verifySortDescending(List<WebElement> elementList ){

		try {
			ArrayList<String> obtainedList = new ArrayList<>();
			for(WebElement rowEle:elementList){			
				obtainedList.add(rowEle.getText());
			}
			ArrayList<String> sortedList = new ArrayList<>();
			for(String s:obtainedList){
				sortedList.add(s);			
			}

			Collections.sort(sortedList);
			Collections.reverse(sortedList);
			System.out.println(obtainedList);
			System.out.println(sortedList);
			Assert.assertTrue(sortedList.equals(obtainedList), "Not Sorted in Descending order");
			return true;
		} catch (AssertionError e) {
			return false;
		}	
	}

	/*
	 * Generic Method to Compare files of different Extension
	 */
	public static boolean CompareFile(String outputFilePath,String inputFilePath,String fileType)
	{
		boolean Compare = true;
		String in_Line,out_Line;
		try
		{
			Scanner sc_input = new Scanner(new FileReader(inputFilePath));
			Scanner sc_output = new Scanner(new FileReader(outputFilePath));
			while(sc_input.hasNextLine() && sc_output.hasNextLine())
			{
				in_Line = sc_input.nextLine();
				out_Line = sc_output.nextLine();
				if(in_Line.equals(out_Line))
					Compare = true;
				else 
					Compare = false; 
				break;
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Compare;
	}

	public static boolean ValidateXMLWithCSV(Scanner sc_Op,String[] oldSCAC, String[] newSC, String[] newAC, int noOfRecords)
	{
		boolean isMatching = false;
		boolean result_cass_old = false;
		boolean result_cass_newSC = false;
		boolean result_cass_newAC = false;
		String scannedLine;
		String tempString;
		int noOfOutputLines=0;
		/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date Date = new Date();
		Date today;
		try {
			today = dateFormat.parse(dateFormat.format(Date));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/

		try
		{
			while(sc_Op.hasNextLine())
			{
				for(int b=0; b < noOfRecords; b++)
				{
					/*	if (swtchDate[b].after(today) && redrctnDate[b].before(today))
				{*/
					scannedLine = sc_Op.nextLine();
					tempString = scannedLine.substring(0, 14);
					result_cass_old = oldSCAC[b].equals(tempString);
					tempString = scannedLine.substring(15, 21);
					result_cass_newSC = newSC[b].equals(tempString);
					tempString = scannedLine.substring(22, 30);
					result_cass_newAC = newAC[b].equals(tempString);

					noOfOutputLines ++;
					if (result_cass_old && result_cass_newSC && result_cass_newAC && noOfOutputLines == noOfRecords)
					{
						isMatching = true;
					}
					else
					{
						isMatching = false;
					}
					/*}
				else
				{
					isMatching = false;
					String actualResult = "The switch date and redirecton date of the records are not inappropriate with Swtich Date: " + swtchDate[b] + " & Redirection Date: " + redrctnDate[b] + " for the Old (SortCode +Account Number): " + oldSCAC[b]; 
					String expectedResult = "The switch date should > current date and redirection date should be < current date for the output to be generated properly";
					publishResults(isMatching, actualResult, expectedResult, "*******CASS******");
				}*/
				}
			}	
		}
		catch (NoSuchElementException ns)
		{
			System.out.println("Output File contains lesser number of records than the number of records inputted.");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return isMatching;
	}

	public static boolean ReferenceData_OutputFileValidation(String input, String query, String fileType, int expectedStatus)
	{
		String output, fileName,remarks="No Fetched Remarks",msg_ER,actualResult,expectedResult;
		int fileStatus=0;
		boolean isProcessed = false;
		//Processing 1 erroneous file
		try
		{
			output = ICSDBUtilis.sqlCommandExecution(ICSPropertiesConfig.getReferenceDataResourceFile().getString(input));

			//Fetching status of the erroneous file
			//Verification: if the error is logged in Audit Table

			if(((!(output.toLowerCase().contains("failed")||output.toLowerCase().contains("not valid"))) && expectedStatus 
					== Integer.parseInt(ICSPropertiesConfig.getReferenceDataFileStatusSuccess()))
					|| ((output.toLowerCase().contains("failed")||output.toLowerCase().contains("not valid")) && 
							expectedStatus == Integer.parseInt(ICSPropertiesConfig.getReferenceDataFileStatusRepeat())))
			{
				try{

					ResultSet rs = 
							ICSProductDBConnection.getICSDBServerConnection(ICSPropertiesConfig.getReferenceDataServer(), ICSPropertiesConfig.getReferenceDataFileDatabase(), ICSPropertiesConfig.getReferenceDataResourceFile().getString(query));
					while(rs.next())
					{						
						fileName = rs.getString(1);
						fileStatus = rs.getInt(2);
						remarks = rs.getString(3);
					}
					isProcessed = fileStatus==expectedStatus;
					if (isProcessed)
					{
						expectedResult = "The File should get processed with a Status of " + expectedStatus;
						actualResult= "The File got processed with a status of " + fileStatus + " & Remarks as '" + remarks + "'";
						msg_ER = "******** " + fileType + " ********";
					}
					else
					{
						expectedResult = "The File should get processed with a Status of " + expectedStatus;
						actualResult= "The File got processed with a status of " + fileStatus + " & Remarks as '" + remarks + "'";
						msg_ER = "******** " + fileType + " ********";

					}
					publishResults(isProcessed,actualResult,expectedResult,msg_ER);
					//Assert.assertTrue(isProcessed);

				}

				catch(Exception e)
				{
					fileName = null;
				}

			}
			else
			{
				isProcessed = false;
				expectedResult = "SSIS Package should process the file " + expectedStatus;
				msg_ER = "******** " + fileType + " ********";
				actualResult = "SSIS Package did not process the file successfully with these command line errors: '" + output.substring(output.indexOf(':')+11,output.indexOf(']')+8) +"'";
				publishResults(isProcessed,actualResult,expectedResult,msg_ER);
				//Assert.assertTrue(isProcessed);

			}
		}

		catch(Exception e)
		{
			fileName = null;
			//System.out.println("ERROR IN OUTER CATCH");
		}
		return isProcessed;		

	}

	public static File deleteFilesInFilePath(String filePath)
	{
		File file = new File(filePath);
		file.delete();
		return file;
	}

	public static void scriptStartLog(Logger logger, String information)
	{
		logger.info("\n \n" + information);
	}

	public static void scriptCompletionLog(Logger logger, String information)
	{
		if(finalTestScriptResultFailFlag)
		{
			logger.error(information + " failed \n \n");

		}else {
			logger.info(information + " passed \n \n");
		}

	}

	/* 
	 * Method Name: getTagValueByXPATH
	 * Author: Hemapriya Shanmugam 
	 * Created Date: 15/05/2017
	 * Description: Gets String Array TAG VALUE using XPATH from XMLs
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 15/05/2017
	 */	

	public static String[] getTagValueByXPATH(String filePath,String fileName, String xPath, String tagName)
	{	Map<String, String> nodeValue;
	try 
	{
		nodeValue =  getXMLNodeValueByXPATH(filePath, fileName, xPath);
		List<String> tagValueList=  new ArrayList<String>();
		tagValueList = getNodeListValues(nodeValue, tagName);
		String[] tagValue = new String[tagValueList.size()];
		for (int i = 0; i<tagValueList.size();i++)
		{
			tagValue[i]=tagValueList.get(i);
		}
		return tagValue;

	}
	catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}		
	}

	public static void writeToFile(String filePath, String dataToWrite) throws IOException, InterruptedException
	{
		FileWriter fw = new FileWriter(filePath);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(dataToWrite);
		bw.close();
		Thread.sleep(2000);
	}	

	/*public static void xmlUnitSetup()
		{
			XMLUnit.setIgnoreWhitespace(true);
			XMLUnit.setIgnoreAttributeOrder(true);			
			XMLUnit.setIgnoreDiffBetweenTextAndCDATA(true);
			XMLUnit.setIgnoreComments(true);
			XMLUnit.setNormalizeWhitespace(true);
		}*/

	/* 
	 * Method Name: sqlCommandExecution
	 * Author: Nisha Tripathi
	 * Created Date: 15/02/2017
	 * Description: Inject SQL file in to the DB through commandline execution
	 * Last Modified By: Nisha Tripathi
	 * Last Modified Date: 16/02/2017
	 * Method Copy Updated Date: 26/05/2017
	 */
	public static String sqlCommandExecutionTest(String strCommand) throws IOException{
		StringBuffer stringBufferStream= new StringBuffer();
		final Process strSqlCommand = Runtime.getRuntime().exec(strCommand);
		InputStreamReader inputS = new InputStreamReader(strSqlCommand.getInputStream());
		OutputStreamWriter oSw = new OutputStreamWriter(strSqlCommand.getOutputStream());
		BufferedReader stdErr = new BufferedReader(new InputStreamReader(strSqlCommand.getErrorStream()));
		final InputStream in = strSqlCommand.getInputStream();			
		int eachCharacterIndex;
		String s=null;
		while ((eachCharacterIndex=in.read())!=-1){
			stringBufferStream.append((char)eachCharacterIndex);
		}
		System.out.println(stringBufferStream.append((char)eachCharacterIndex));
		//oSw.write(eachCharacterIndex);
		//System.out.println("read msg"+oSw.append((char)eachCharacterIndex));
		while(stdErr.readLine()!=null){
			System.out.println("Incorrect Commandline execution performed "+stdErr.readLine());
		}
		return stringBufferStream.toString();	
	}	

	public static String getRenamedFileExtenstion(String data, String actualExtension, String expectedExtension)
	{
		String target = data.replaceAll(actualExtension, expectedExtension);
		File targetFileStream = new File(target);
		new File(data).renameTo(targetFileStream);
		return target;
	}
	
	public static int getStringOccurence(String requiredContent, String findStr)
	{
		int lastIndex = 1;
		int count = 0;		
		while(lastIndex!=-1)
		{
			lastIndex = requiredContent.indexOf(findStr, lastIndex);
			if(lastIndex!=-1)
			{
				count++;
				lastIndex += findStr.length();
			}
		}
		return count;
	}

	/* 
	 * Method Name: removeUnusedSpaceFromArray
	 * Author: Hemapriya Shanmugam 
	 * Created Date: 01/06/2017
	 * Description: Frees Unused Space From String Array - Helps in speeding up execution while using in loops or while accessing the array
	 * 				Argument - Single Dimension String Array and Returns - the same String Array without empty/null cells
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 01/06/2017
	 */	
	public static String[] removeUnusedSpaceFromArray(String[] array)
	{
		List<String> list = new ArrayList<String>();
		for(String text:array)
		{
			if(!(text==null || text.isEmpty()))
				list.add(text);
		}
		array = list.toArray(new String[0]);
		return array;
	}

	public static void workFlowLoadFinalResultStatement(String workFlow )
	{
		if(!(finalTestScriptResultFailFlag))
		{
			validationStepInformation("Successfully loaded the message " + workFlow );
			genericMethodUtilsLog.info("Successfully loaded the message " + workFlow);
		}else {
			validationErrorInformation("Tag Validation error for message load for workflow " + workFlow );
			genericMethodUtilsLog.error("Tag Validation error for message load for workflow " + workFlow );
		}
	}
}
