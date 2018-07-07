package com.ics.agency.pages;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

public class SettlementLimits extends ICSPageUtilis {

	@FindBy(id = "FrmAgency")
	private static WebElement agencyFrame;
	
	@FindBy(id = "ddlAgenciesList")
	private static WebElement lstAgencyList;
	
	@FindBy(id = "txtCurrWorkingDayExtdSettleCapLimit")
	private static WebElement txtExtendedSettlementCap;

	@FindBy(id = "txtNextWorkingDaySettleCapLimit")
	private static WebElement txtNextDateSettlementCap;
	
	@FindBy(id = "btnUpdate")
	private static WebElement btnUpdate;
	
	@FindBy(className = "bootbox-body")
	private static WebElement dlgErrorMessage;
	
	protected static ExtentTest EXTENTLOG;
	protected static boolean flag=false;

	public SettlementLimits(WebDriver driver) {
		super(driver);		
	}

	public SettlementLimits(WebDriver driver, String url) {
		super(driver, url);		
	}

	public static boolean returnResultFlag()
	{
		return SettlementLimits.flag;
	}
	
	public static String serverAgency = getAgencyValueFromDataSheet("serverNameAgency");
	public static String db = getAgencyValueFromDataSheet("agencyDatabase");
	
	/*************************************************************************************
	 * Method Name: verifyErrorMesssage
	 * Purpose:Method for validating the action and values displayed in activity log screen
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  05-Oct-2017
	/*************************************************************************************/
	
	public SettlementLimits verifyErrorMesssage()
	{
		String extendedSettlementCap = getAgencyValueFromDataSheet("extendedSettlementCapInvalid");
		String nextWorkingDatSettlementCap = getAgencyValueFromDataSheet("nextWorkingDatSettlementCapInvalid");
		
		validationStepInformation("Check Action and value column in Activity log page");
		fillTextBox(txtExtendedSettlementCap, extendedSettlementCap);
		clickElement (btnUpdate);
		if (dlgErrorMessage.getText().equals("Extended settlement cap limit :  Amount must be numeric 0.00 to 999999999.999") ){
			flag=true;
			publishResults(flag, "Error message displayed as expected", "Error pop up should be displayed","Error pop up should be displayed when date range is greater than 365 days");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "error message not displayed", "Error pop up should be displayed","Error pop up should be displayed when date range is greater than 365 days");
		}
		
		fillTextBox(txtExtendedSettlementCap, "");
		fillTextBox(txtNextDateSettlementCap, nextWorkingDatSettlementCap);
		clickElement (btnUpdate);
		if (dlgErrorMessage.getText().equals("Settlement cap limit :  Amount must be numeric 0.00 to 999999999.999") ){
			flag=true;
			publishResults(flag, "Error message displayed as expected", "Error pop up should be displayed","Error pop up should be displayed when date range is greater than 365 days");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "error message not displayed", "Error pop up should be displayed","Error pop up should be displayed when date range is greater than 365 days");
		}
		fillTextBox(txtNextDateSettlementCap, nextWorkingDatSettlementCap);
		return this;
	}
		
	/*
	 * Method Name: validatesettlementCapTables
 	 * Author: Deepa Ramu
 	 * Date: 31-Oct-2017
 	 * Method Description: Validation settlement cap mimitin both tables after running SOD settlement limit job  
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: 
	 * Last Modified Date: 31-Oct-2017
	 */
	public boolean validateSettlementCapTables() throws Exception
	{		
		String sod_settlementCap_packageRun=getAgencyValueFromDataSheet("sod_settlementCap_packageRun");
		String settlementLimitAgencyConfig=getAgencyValueFromDataSheet("getSettlementLimits_1");
		String settlementLimitCapConfig=getAgencyValueFromDataSheet("getSettlementLimits_2");
		String agencyConfigrowCount=getAgencyValueFromDataSheet("getCountAgencyID");
		
		List<String> AgencyConfigList = new ArrayList<String>();
		List<String> AgencySettlementCapConfigList= new ArrayList<String>();
		int count1=0 ;
		ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, getAgencyValueFromDataSheet("truncateSettlementCapTable"));
		sqlCommandExecution(sod_settlementCap_packageRun);

		ResultSet AgencyConfigRS =ICSProductDBConnection.getICSDBServerConnection(serverAgency,db,settlementLimitAgencyConfig );
		ResultSet AgencySettlementCapConfigurationRS = ICSProductDBConnection.getICSDBServerConnection(serverAgency,db,settlementLimitCapConfig);
		ResultSet AgencyIDCount =  ICSProductDBConnection.getICSDBServerConnection(serverAgency, db,agencyConfigrowCount );
		
		while(AgencyIDCount.next())
		{
			count1 = AgencyIDCount.getInt(1);		
		}
		while(AgencyConfigRS.next())
		{
			AgencyConfigList.add(AgencyConfigRS.getString(1));
			AgencyConfigList.add(AgencyConfigRS.getString(2));
		}
		while(AgencySettlementCapConfigurationRS.next())
		{
			AgencySettlementCapConfigList.add(AgencySettlementCapConfigurationRS.getString(1));
			AgencySettlementCapConfigList.add(AgencySettlementCapConfigurationRS.getString(2));
		}
					
		for(int i=0 ; i<=count1 ;i++)	{
			if ((AgencyConfigList.get(i).equals(AgencySettlementCapConfigList.get(i)))){
				flag=true;
			}
			else {
				flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag,  "Settlement details are not updated as expected / matched for the following data"+AgencyConfigList.get(i)+" , "+AgencySettlementCapConfigList.get(i), "AgencySettlementCapConfiguration table should be updated","Verify AgencySettlementCapConfiguration table is updated after SOD job");
			
			}
			if (flag){
				publishResults(flag, "AgencySettlementCapConfiguration table is updated as expected", "AgencySettlementCapConfiguration table should be updated","Verify AgencySettlementCapConfiguration table is updated after SOD job");
			}
		}
		return flag;
	}
	
	/*
	 * Method Name: validatesettlementCapTables
 	 * Author: Deepa Ramu
 	 * Date: 31-Oct-2017
 	 * Method Description: Validation of Records in ERDMS Commercial Transaction Tables 
	 * Reviewed By: Venkatasainath Devisetty
	 * Last Modified By: Hemapriya Shanmugam 
	 * Last Modified Date: 25-May-2017
	 */
	public boolean validateNextDateSettletmentCap() throws Exception
	{		

		String sod_settlementCap__packageRun=getAgencyValueFromDataSheet("sod_settlementCap_packageRun");
		List<String> AgencySettlementListDate1 = new ArrayList<String>();	
		List<String> AgencySettlementListDate2 = new ArrayList<String>();	
		
		ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, getAgencyValueFromDataSheet("setBusinessDate"));
		ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, getAgencyValueFromDataSheet("truncateSettlementCapTable"));
		sqlCommandExecution(sod_settlementCap__packageRun);
		
		ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, getAgencyValueFromDataSheet("setNextDate"));
		sqlCommandExecution(sod_settlementCap__packageRun);
		
		ResultSet AgencySettlementLimit1 =ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, getAgencyValueFromDataSheet("getSettlementLimitsDate1"));
		ResultSet AgencySettlementLimit2 = ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, getAgencyValueFromDataSheet("getSettlementLimitsDate2"));
		ResultSet SettlementLimitDate1Count =  ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, getAgencyValueFromDataSheet("getSettlementLimitsDateCount1"));
		ResultSet SettlementLimitDate2Count =  ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, getAgencyValueFromDataSheet("getSettlementLimitsDateCount2"));
		
		int count1=0;
		int count2 =0;
		while (SettlementLimitDate1Count.next() && SettlementLimitDate2Count.next()){
			count1 = SettlementLimitDate1Count.getInt(1);		
			count2 = SettlementLimitDate2Count.getInt(1);
		}
			
		while(AgencySettlementLimit1.next()) {
			AgencySettlementListDate1.add(AgencySettlementLimit1.getString(1));
			AgencySettlementListDate1.add(AgencySettlementLimit1.getString(2));
			}
		
		while(AgencySettlementLimit2.next()) {
			AgencySettlementListDate2.add(AgencySettlementLimit2.getString(1));
			AgencySettlementListDate2.add(AgencySettlementLimit2.getString(2));
		}
			
		if (count1==count2){	
			
			for (int i=1; i<=count1 ; i++ ){
			if ((AgencySettlementListDate1).get(i).equals(AgencySettlementListDate2.get(i))){
				flag=true;
			}
			else {
				flag=false;
				publishResults(flag,  "Current business date limits does not match the next working date limits", "Current business date count should match the next working date countfor the following data"+AgencySettlementListDate1.get(i)+" , "+AgencySettlementListDate2.get(i),"Verify Current business date settlement data matches the next working date settlement limits");
			}
			}
			if (flag){
				publishResults(flag,  "Current business date limits matches the next working date limits", "Current business date count should match the next working date countfor the following data","Verify Current business date settlement data matches the next working date settlement limits");
			}
		
		}
		else{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "Current business date count does not match the next working date count", "Current business date count should match the next working date count","Verify Current business date count matches the next working date count");
		}
		return flag;
	}
	
	
	/*************************************************************************************
	 * Method Name: validateCuttOffTimeError
	 * Purpose:Method for validating the error when the cut off time is passed
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  05-Nov-2017
	/*************************************************************************************/
	
	public SettlementLimits validateCuttOffTimeError() throws Exception
	{
		String extendedSettlementCap = getAgencyValueFromDataSheet("extendedSettlementCap");
	
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
		Date time = new Date();
		String time1=timeFormat.format(time);
		time1 = time1.replaceAll(":", "");
		String query = "UPDATE Config.BankConfiguration SET [Values] = '"+time1+"' WHERE Name = 'SettlementCapCutOffTime'";
		ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, query);
			
		validationStepInformation("Check Settlement time cut off limit error message is displayed");
		fillTextBox(txtExtendedSettlementCap, extendedSettlementCap);
		clickElement (btnUpdate);
		if (dlgErrorMessage.getText().equals("The system is now in lockout stage for Settlement Limit. So Settlement Limit cannot be updated.") ){
			flag=true;
			publishResults(flag, "Error message displayed as expected", "Error pop up should be displayed","Error pop up should be displayed when settlement limit cut off time is passed");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "error message not displayed", "Error pop up should be displayed","Error pop up should be displayed when settletent limit cut off time is passed");
		}
			
		query = "UPDATE Config.BankConfiguration SET [Values] = '2000' WHERE Name = 'SettlementCapCutOffTime'";
		ICSProductDBConnection.getICSDBServerConnection(serverAgency, db, query);
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: validateAmountTextBox
	 * Purpose:Method for verifying £ symbol is automatically added when user enters amount
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  05-Nov-2017
	/*************************************************************************************/
	
	public SettlementLimits validateAmountTextBox() throws Exception
	{
		String extendedSettlementCap = getAgencyValueFromDataSheet("extendedSettlementCap");
		String nextWorkingDatSettlementCap = getAgencyValueFromDataSheet("nextWorkingDatSettlementCap");
		
		validationStepInformation("Check £ symbol is automatically added when user enters amount");
		fillTextBox(txtExtendedSettlementCap, extendedSettlementCap);
		fillTextBox(txtNextDateSettlementCap, nextWorkingDatSettlementCap);
		String a = txtExtendedSettlementCap.getText();
		String b = txtNextDateSettlementCap.getText();
		
		if ((a.equals("£"+extendedSettlementCap)) && (b.equals("£"+nextWorkingDatSettlementCap)) ){
			flag=true;
			publishResults(flag, "Error message displayed as expected", "Error pop up should be displayed","Error pop up should be displayed when settlement limit cut off time is passed");
		}else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(flag,  "error message not displayed", "Error pop up should be displayed","Error pop up should be displayed when settletent limit cut off time is passed");
		}
		return this;
	}
		
}
