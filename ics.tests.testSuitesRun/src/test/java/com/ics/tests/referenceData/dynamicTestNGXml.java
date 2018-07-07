package com.ics.tests.referenceData;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;


public class dynamicTestNGXml extends ICSAutomationCommonSetup {
	
	public static int noOfTestCases=180;
	public static String dirPath = getWorkingDirectoryPath();
	private static String referenceDataInputTestCaseSelectionFileName = "\\ReferenceData_TCSelect.xls";
	private static String referenceDataTestNGXmlCopy = "\\ReferenceData_TestNG.xml";
	public static String filePath = dirPath + directoryPathSeperationSymbol +inputXlsTestDataBasePath+refDataPropertiesFile+referenceDataInputTestCaseSelectionFileName; //excel that can input the TCs to be executed
	public static String testNGCopy = dirPath + directoryPathSeperationSymbol +inputXmlTestDataBasePath+refDataPropertiesFile+referenceDataTestNGXmlCopy; //xml copy of the TestNG xml created after the selectMethod
	
	// MAIN METHOD
	public static void main(String[] args) throws Exception 
	{
		new dynamicTestNGXml().selectMethods_FromXl();
	}
	
	/*
	 * Method Name: selectMethods_FromXl
 	 * Author: Hemapriya Shanmugam
 	 * Date: 15-Jul-2017
 	 * Method Description: Method To select cases that are marked "Y" for execution in Excel -> Run Solution From Here
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 05-Sep-2017
	 */	
	public void selectMethods_FromXl() throws Exception
	{
		int j=0;
		List<XmlInclude> methodName = new ArrayList<XmlInclude>(noOfTestCases);
		List<XmlTest> listTest=new ArrayList<>();
		List<XmlSuite> list = new ArrayList<XmlSuite>();
		
		XmlSuite suite=new XmlSuite();
		suite.setName("Suite");
		XmlTest test=new XmlTest(suite);
		test.setName("Test");
		XmlClass className=new XmlClass();
		className.setName("com.ics.referenceData.Regression");	
		ArrayList<XmlClass> classes=new ArrayList<XmlClass>();
		FileInputStream file = new FileInputStream(new File(filePath));
		Workbook workbook = WorkbookFactory.create(file);
		Sheet sheet = workbook.getSheetAt(0);
		noOfTestCases = sheet.getPhysicalNumberOfRows();

		for(int i=1; i<noOfTestCases; i++)
		{
			if(sheet.getRow(i).getCell(1).getStringCellValue().equals("Y"))
			{
				methodName.add(j,(new XmlInclude(sheet.getRow(i).getCell(0).getStringCellValue())));
				j++;
			}
		}
		workbook.close();
		file.close();
		System.out.println("File Closed");
		className.setIncludedMethods(methodName);
		classes.add(className);
		test.setXmlClasses(classes);
		listTest.add(test);
		suite.setTests(listTest);
		list.add(suite);
		
		//File Name and Path to paste the TestNG xml ONLY for reference -- NOT USED BY TC execution
		@SuppressWarnings("resource")
		FileWriter writer=new FileWriter(new File(testNGCopy)); 
		writer.write(suite.toXml());
		writer.flush();
		TestNG tng = new TestNG();
		tng.setXmlSuites(list);
		tng.run();
		
	}
	

	/*
	 * Method Name: resultPassToXl
 	 * Author: Hemapriya Shanmugam
 	 * Date: 15-Jul-2017
 	 * Method Description: Method To write back onto Excel on the Final status of TC -- To be called by each method or class as per excel row -> Call From Each Test Case
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 05-Sep-2017
	 */	
	
	public void resultPassToXl(String testCaseName,String result) throws Exception
	{
			int j = 0;
			System.out.println("Test Result for " + testCaseName + ": " + result);

			System.out.println("RESULT PUBLISHING... May take several seconds.. Please Wait..");
		
			FileInputStream file = new FileInputStream(new File(filePath));
			Workbook workbook = WorkbookFactory.create(file);//new XSSFWorkbook(file);
			Sheet sheet = workbook.getSheetAt(0);
			Row r;
			Cell c;
			FileOutputStream out = new FileOutputStream(new File(filePath));
			try{
			for(int i=1; i<=noOfTestCases; i++)
			{
				if(sheet.getRow(i).getCell(0).getStringCellValue().equals(testCaseName))
				{
					System.out.println("Row#: " + i + ": " + testCaseName);
					r = sheet.getRow(i);
					c = r.createCell(2);// --.getCell(2);
					c.setCellValue(result);
					sheet.getWorkbook().write(out);
					//workbook.write(out);
					//.close();
					workbook.close();
					out.flush();
					file.close();
					i=noOfTestCases+1;
					System.out.println("RESULT PUSHED TO EXCEL");	
					j=1;
				}			
			}	
			if(j==0)
			{
				workbook.close();
				out.flush();
				file.close();
				System.out.println("ISSUE IN UPDATING EXCEL For " + testCaseName);	
			}
		}
		catch(Exception e)
		{	
			e.printStackTrace();
			workbook.close();
			out.flush();
			file.close();
		}	
	}
	
	
	

	
}
