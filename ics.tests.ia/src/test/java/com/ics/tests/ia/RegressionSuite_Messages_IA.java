/*  
<Copyright file="RegressionSuite_Messages_IA.java" company="iPSL">
Copyright © iPSL 2017 All rights are reserved.
Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
is prohibited without the prior written permission of the copyright owner.
</copyright> 
*/
/* ************************* Module Header *********************************************************
 * Module Name : Messages Regression test Suite 
 * Date : 19/07/2017
 * Created By : Sandeep Bibinagar
 * Description : This file contains all the test scripts/work flows w.r.t Web UI for Image Archive
 
 ********************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
 *******************************************************************************************************
 Description : added functionality to fetch test data from excel sheet
 ******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
 Description : Updating class as per java coding standards
 ******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 11/09/2017		
 Description : Updating class as per java coding standards ****************
 *************************************************************************** */

package com.ics.tests.ia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlGroups;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlRun;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;
import junit.framework.TestCase;


public class RegressionSuite_Messages_IA extends ICSAutomationCommonSetup{
	
	public static int noOfTestCases;
	public static String dirPath = getWorkingDirectoryPath();
	private static String iaTestCaseSelectionFileName = "\\TCSelect.xls";
	private static String IATestNGXml = "\\ia_testng.xml";
	public static String filePath = dirPath + directoryPathSeperationSymbol +inputXlsTestDataBasePath+iaPropertiesFile+iaTestCaseSelectionFileName; //excel that can input the TCs to be executed
	public static String testNGCopy = dirPath + directoryPathSeperationSymbol +inputXmlTestDataBasePath+iaPropertiesFile+IATestNGXml; //xml copy of the TestNG xml created after the selectMethod
	
	public static void main(String[] args) throws Exception 
	{
		new RegressionSuite_Messages_IA().selectMethods_FromExcel();
	}
	
	// Method to select test cases that are marked "Y" for execution in Excel; test cases marked "N" are skipped
	public void selectMethods_FromExcel() throws Exception
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
		className.setName("com.ics.tests.ia.MessagesLoadAndValidation");	
		ArrayList<XmlClass> classes=new ArrayList<XmlClass>();
		List<XmlTest> methods= new ArrayList();
		FileInputStream file = new FileInputStream(new File(filePath));
		Workbook workbook = WorkbookFactory.create(file);//new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheetAt(0);
		noOfTestCases = sheet.getPhysicalNumberOfRows();
		for(int i=1; i<noOfTestCases; i++)
		{
			//System.out.println(sheet.getRow(i).getCell(1).getStringCellValue());
			if(sheet.getRow(i).getCell(1).getStringCellValue().equals("Y"))
			{
				methodName.add(j,(new XmlInclude(sheet.getRow(i).getCell(0).getStringCellValue())));
				j++;
			}
		}
		workbook.close();
		file.close();
	//	System.out.println("File Closed");
		className.setIncludedMethods(methodName);//(Arrays.asList(new XmlInclude[] {new XmlInclude(typeof)} ));
		classes.add(className);
		test.setXmlClasses(classes);
		listTest.add(test);
		suite.setTests(listTest);
		list.add(suite);
		FileWriter writer=new FileWriter(new File(testNGCopy)); 
		writer.write(suite.toXml());
		writer.flush();
		TestNG tng = new TestNG();
		tng.setXmlSuites(list);
		tng.run();
	}
}
