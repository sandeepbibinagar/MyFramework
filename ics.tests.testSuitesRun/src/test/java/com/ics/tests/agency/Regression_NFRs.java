//<copyright  file="Regression_NFRs.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>
package com.ics.tests.agency;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ics.agency.entities.OpenAgencyPageScenario;
import com.ics.agency.pages.AgencyIdentityProviderPage;
import com.ics.agency.pages.BankLandingPage;
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

/******************MODULE HEADER*****************************************
 * Module Name -  Regression_NFRs.java
 ************************************************************************ 
 * Date -  26/07/2017
 ************************************************************************ 
 * Created By -  RamuDeep
 ************************************************************************ 
 * Description - TestScripts to check the Functionality of NFRs
 ***********************************************************************/
 
/******************AMENDMENT HISTORY*********************************** 
  * Modified By - 
 ***********************************************************************
  * Description - 
 ***********************************************************************/
public class Regression_NFRs extends ICSAutomationCommonSetup{

	public static String server = getAgencyValueFromDataSheet("serverNameAgency");
	public static String db = getAgencyValueFromDataSheet("agencyDatabase");
	public static String query= "";
	public static String query1= "";
	
	@BeforeMethod
	public static void driverSetup(){
		DRIVER = startDriver();
	}
	
	@AfterMethod()
	public void quitDriver(){
		DRIVER.quit();
	}
	
	/*
	 * Test Case: 81458
	 * Author:Deepa Ramu
	 * Description: NFR: Regression: Verify session expiry 
	 * Expected: 
	 *   1. Session should expire according to the time set
	 */
	@Test(priority = 1)
	public void test_case_117047_sessionExpiry() throws Exception
	{		
		query = "UPDATE Config.BankConfiguration SET [Values] = '1' WHERE Name = 'SessionExpire'";
		ResultSet rsCore = ICSProductDBConnection.getICSDBServerConnection(server, db, query);		
		new AgencyIdentityProviderPage(DRIVER,  getAgencyValueFromDataSheet("URL"))
		.run(new OpenAgencyPageScenario(ICSPropertiesConfig.getSuperUser()));
		new OpenAgencyPortalPage(DRIVER)
		.clickOpenAgencyPortalButton()
		.switchToAgencyFrame()
		.clickOnIncomingDebits()
		.verifySessionExpiry();
		query = "UPDATE Config.BankConfiguration SET [Values] = '20' WHERE Name = 'SessionExpire'";
		rsCore = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
	}
	
	/*
	 * Test Case: 102294
	 * Author:Deepa Ramu
	 * Description: NFR: Regression: Mixed mode authentication - user role verification
	 * Expected: 
	 *   1. Invalid user role should not be able to access internal url 
	 */
	@Test(priority = 2)
	public void test_case_102294_userRoleVerification() throws Exception
	{		
		query = "UPDATE Base.ADRoleMap SET [RoleId] = '25' WHERE AdName = 'GG-DE-Onshore-Testers'";
		ResultSet rsCore = ICSProductDBConnection.getICSDBServerConnection(server, db , query);		
		new AgencyIdentityProviderPage(DRIVER, getAgencyResourceFile().getString("internalUserURL"))
		.ClickOnsecuritylink();
		new BankLandingPage(DRIVER)
		.assertIncomingDebitTabNotAvailable();	
		query = "UPDATE Base.ADRoleMap SET [RoleId] = '11' WHERE AdName = 'GG-DE-Onshore-Testers'";
	    rsCore = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
	}
	
	/*
	 * Test Case: 102297
	 * Author:Deepa Ramu
	 * Description: NFR: Regression: Mixed mode authentication - group Access verification
	 * Expected: 
	 *   1. User who does not have access to group, should not be able to access internal url 
	 */
	@Test(priority = 3)
	public void test_case_102297_groupAccessVerification() throws Exception
	{		
		query = "DELETE FROM Base.ADRoleMap WHERE AdName = 'GG-DE-Onshore-Testers'";
		ResultSet rsCore = ICSProductDBConnection.getICSDBServerConnection(server, db , query);		
		new AgencyIdentityProviderPage(DRIVER, getAgencyResourceFile().getString("internalUserURL"))
		.ClickOnsecuritylink();
		new BankLandingPage(DRIVER)
		.assertIncomingDebitTabNotAvailable();	
		query = "INSERT INTO Base.ADRoleMap VALUES (11,'GG-DE-Onshore-Testers',3,'L')";
		rsCore = ICSProductDBConnection.getICSDBServerConnection(server, db, query);
	}

	/*
	 * Test Case: 102297
	 * Author:Deepa Ramu
	 * Description: NFR: Regression: Mixed mode authentication - group Access verification
	 * Expected: 
	 *   1. User who does not have access to group, should not be able to access internal url 
	 */
	@Test(priority = 4)
	public void config_update() throws Exception
	{	
		String sheetName ="";
		String filePath = "D:\\TFS_WD\\DEEPA_TFS_WD\\TabConfig.xlsx";
		for (int i=1; i<=18 ; i++){
		query = "SELECT ItemValue from base.ControlMapping where TabId="+i;
		query1 =  "SELECT controlName from base.UserControls inner join base.ControlMapping on base.ControlMapping.UserControlsId = base.UserControls.ControlId where base.ControlMapping.TabId ="+i;		
		ResultSet rs1 = ICSProductDBConnection.getICSDBServerConnection(server, db , query);	
		ResultSet rs2 = ICSProductDBConnection.getICSDBServerConnection(server, db , query1);	
		if(i==1){
			sheetName = "Dashboard";
		}
		else if (i==2){
			sheetName = "OutgoingItems";
		}
		else if (i==3){
			sheetName = "Incomingdebits";
		}
		else if (i==4){
			sheetName = "Incomingcredits";
		}
		else if (i==5){
			sheetName = "Search";
		}
		else if (i==6){
			sheetName = "ActivityLog";
		}	
		else if (i==7){
			sheetName = "CreateQuery";
		}
		else if (i==8){
			sheetName = "UploadDownload";
		}
		else if (i==9){
			sheetName = "SystemSettings";
		}
		else if (i==10){
			sheetName = "CapManagement";
		}
		else if (i==11){
			sheetName = "Completed";
		}
		else if (i==12){
			sheetName = "ExceptionItems";
		}
		else if (i==13){
			sheetName = "CollectedByMe";
		}
		else if (i==14){
			sheetName = "CollectedElsewhere";
		}
		else if (i==15){
			sheetName = "Exceptions";
		}
		else if (i==16){
			sheetName = "Upload";
		}
		else if (i==17){
			sheetName = "Download";
		}	
		else if (i==18){
			sheetName = "CapManagement1";
		}
		setStOutputTestData(filePath,sheetName,rs1,0);
		setStOutputTestData(filePath,sheetName,rs2,1);
		}
	}
	
	public static void setStOutputTestData(String filePath,String sheetName, ResultSet rsCore, int value1) throws FileNotFoundException, IOException, SQLException
	{
		File file = new File(filePath);
		file.setWritable(true, false);
		FileInputStream fis = new FileInputStream(filePath);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet tempSheet;
		try
		{
			tempSheet = wb.getSheet(sheetName);
			if(tempSheet.equals(null))
				throw new Exception();
		}
		catch(Exception e)
		{
			tempSheet = wb.createSheet(sheetName);
		}
		int i=0;
		while(rsCore.next())
		{
			String value = rsCore.getString(1);
			XSSFRow row;
			System.out.println("first: " + tempSheet.getFirstRowNum());
			if(tempSheet.getPhysicalNumberOfRows() == 0)
				row = tempSheet.createRow(i++);
			else
				row = tempSheet.getRow(i++);
			System.out.println(row.getRowNum());
			row.createCell(value1).setCellValue(value);
		}
		fis.close();
		FileOutputStream fos = new FileOutputStream(filePath);
		wb.write(fos);
		wb.close();
		fos.close();
		file.setReadable(true, false);
	}
	
}

