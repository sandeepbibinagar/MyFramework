//<copyright  file="TestNGCreator.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>
package com.ics.tests.agency;
/******************MODULE HEADER*****************************************
 * Module Name -  TestNGCreator.java
 ************************************************************************ 
 * Date -  26/07/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - Dynamic TestNGCreator to run scenarios from excel sheet
 ***********************************************************************/
 
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.testng.TestNG;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

import org.testng.annotations.Test;
public class TestNGCreator extends GenericMethodUtilis
{
	int priority = 0;
	XmlSuite suite=new XmlSuite();
	List<XmlTest> listTest=new ArrayList<>();
	List<XmlSuite> list=new ArrayList<>();
	
	@Test
	public void createTestNG() throws Exception
	{
		suite.setName("Suite");
		HSSFWorkbook wb = null;
		
		try
		{
			InputStream is= new FileInputStream(new File(getWorkingDirectoryPath()+"\\"+inputXlsTestDataBasePath+agencyPropertiesFile+agencyInputTestDataFileName));
			wb= new HSSFWorkbook(is);
			HSSFSheet sheet=wb.getSheet("testCase");
			for(int i=1;i<=sheet.getLastRowNum();i++)
			{
				if(sheet.getRow(i).getCell(3).getStringCellValue().equalsIgnoreCase("y"))
				{
					ArrayList<XmlClass> classes = new ArrayList<XmlClass>();
					XmlTest test = new XmlTest(suite);
					test.setName("Test" + i);
					XmlClass className = new XmlClass();
					String method = sheet.getRow(i).getCell(2).getStringCellValue();
					String classN=sheet.getRow(i).getCell(1).getStringCellValue();
					List<XmlInclude> methodName = new ArrayList<XmlInclude>();
										
					methodName.add(new XmlInclude(method));
					className.setName("com.ics.tests.agency." + classN);						
					
					className.setIncludedMethods(methodName);
					classes.add(className);
					test.setXmlClasses(classes);
					listTest.add(test);
				}
			}
			
			createSuite();
			testNGRun();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			wb.close();
		}
	}

	public void createSuite()
	{
		try
		{
			suite.setTests(listTest);
			list.add(suite);
			File f = new File(getWorkingDirectoryPath()+"\\testng.xml");
			f.setWritable(true, false);
			PrintWriter writer = new PrintWriter(f); 
			writer.write(suite.toXml());
			writer.flush();
			writer.close();
			f.setReadable(true, false);
		}
		catch(Exception e)
		{
		e.printStackTrace();
		}
	}
	
	public void testNGRun()
	{
		TestNG tng = new TestNG();
		tng.setXmlSuites(list);
		tng.run();
	}
	
}