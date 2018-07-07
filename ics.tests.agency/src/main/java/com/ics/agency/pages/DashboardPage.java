package com.ics.agency.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.thoughtworks.selenium.webdriven.commands.IsElementPresent;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

public class DashboardPage extends ICSPageUtilis {

	@FindBy(id = "FrmAgency")
	private static WebElement agencyFrame;

	@FindBy(xpath = "//a[contains(@href,Dashboard)]")
	private static WebElement Dashboard;
	
	@FindBy(id = "divDashboardItems")
	private static WebElement DashboardItems;
	
	@FindBy(id = "btnRefresh")
	private static WebElement btnRefresh;
		
	@FindBy(id = "overridelink")
	private static WebElement securityOverrideLink;
	
	@FindBy(xpath = "//*[@class='header']/div/h4")
	private static WebElement sessionExpired;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[5]/div[1]")
	private static WebElement dashboardIncomingDebitTotal;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[5]/div[2]")
	private static WebElement dashboardIncomingDebitItemCount;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[5]/div[3]")
	private static WebElement dashboardIncomingDebitTotalValue;
		
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[3]/div[1]")
	private static WebElement dashboardIncomingDebitCompleted;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[3]/div[2]")
	private static WebElement dashboardIncomingDebitCompletedItemCount;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[3]/div[3]")
	private static WebElement dashboardIncomingDebitCompletedItemValue;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[1]/div[1]")
	private static WebElement dashboardIncomingDebitPayDecision;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[1]/div[2]")
	private static WebElement dashboardIncomingDebitPayDecisionItemCount;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[1]/div[3]")
	private static WebElement dashboardIncomingDebitPayDecisionItemValue;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[2]/div[1]")
	private static WebElement dashboardIncomingDebitKappaDecision;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[2]/div[2]")
	private static WebElement dashboardIncomingDebitKappaDecisionItemCount;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[2]/div[2]/div[2]/div[3]")
	private static WebElement dashboardIncomingDebitKappaDecisionItemValue;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[3]/div[2]/div[1]/div[1]")
	private static WebElement dashboardIncomingCreditCollectedElsewhere;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[3]/div[2]/div[1]/div[2]")
	private static WebElement dashboardIncomingCreditCollectedElsewhereItemCount;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[3]/div[2]/div[1]/div[3]")
	private static WebElement dashboardIncomingCreditCollectedElsewhereItemValue;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[3]/div[2]/div[2]/div[1]")
	private static WebElement dashboardIncomingCreditCollectedByMe;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[3]/div[2]/div[2]/div[2]")
	private static WebElement dashboardIncomingCreditCollectedByMeItemCount;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[3]/div[2]/div[2]/div[3]")
	private static WebElement dashboardIncomingCreditCollectedByMeItemValue;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[3]/div[2]/div[4]/div[1]")
	private static WebElement dashboardIncomingCreditTotal;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[3]/div[2]/div[4]/div[2]")
	private static WebElement dashboardIncomingCreditTotalItemCount;
	
	@FindBy(xpath="//*[@id='divDashboardItems']/div/div/div[3]/div[2]/div[4]/div[3]")
	private static WebElement dashboardIncomingCreditTotalItemValue;
		
	protected static ExtentTest EXTENTLOG;
	protected static boolean flag=false;
	protected static boolean flagBanner = false;
	public static String server = getAgencyValueFromDataSheet("serverName3046");
	public static String db = getAgencyValueFromDataSheet("agencyDatabase");
	public static String IA_db = getAgencyValueFromDataSheet("imageArchiveDatabase");
	public static String path_delete_iadata=getAgencyDataFromPath("dashboard")+"\\";
	public static String deleteIaTables=getAgencyValueFromDataSheet("deleteIATables");
	
	public DashboardPage(WebDriver driver) {
		super(driver);		
	}

	public DashboardPage(WebDriver driver, String url) {
		super(driver, url);		
	}

	public static boolean returnResultFlag()
	{	
		return BankLandingPage.flag;
	}
	/*************************************************************************************
	 * Method Name: dataLoad_IA_deleteScript
	 * Purpose:Method for deleting data in IA database
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  18-Aug-2017
	/*************************************************************************************/
	public DashboardPage dataLoad_IA_deleteScript(){
		runStoredProcedureCall(server,IA_db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,IA_db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,IA_db,path_delete_iadata,deleteIaTables);
		runStoredProcedureCall(server,IA_db,path_delete_iadata,deleteIaTables);
		return this;
	}
	
	/*************************************************************************************
	 * Method Name: verifyLandingPageDashboardCompleted
	 * Purpose:Method for validating completed items for incoming debits in dashboard
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  18-Aug-2017
	/*************************************************************************************/	
	public DashboardPage verifyLandingPageDashboardCompleted() throws Exception 
	{		
		String itemValue=getDashboardValueFromDb("Debits");
		String itemCount=getDashboardCountFromDb("Debits");
		String finalValue = dashboardIncomingDebitCompletedItemValue.getText().replaceAll(",", "");
		String finalTotalValue = dashboardIncomingDebitTotalValue.getText().replaceAll(",", "");
		assertTrue(isElementDisplayed(DashboardItems),"Dashboard screen displayed as expected");
		if (dashboardIncomingDebitCompleted.getText().contains("Complete") && dashboardIncomingDebitCompletedItemCount.getText().contains(itemCount)
				&& finalValue.contains(itemValue) && finalTotalValue.contains(itemValue))
		{
			flag=true;
			publishResults(flag, "Incoming debits completed items value is displayed", "Incoming debits completed items value should be displayed", "verify Incoming debits completed items value is displayed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming debits completed items value is not displayed", "Incoming debits completed items value should be displayed", "verify Incoming debits completed items value is displayed");
		}
		return new DashboardPage(driver);
	}
	/*************************************************************************************
	 * Method Name: verifyLandingPageDashboardPayDecision
	 * Purpose:Method for validating pay decsion items for incoming debits in dashboard
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  20-Aug-2017
	/*************************************************************************************/	
	public DashboardPage verifyLandingPageDashboardPayDecision() throws Exception 
	{		
		String itemValue=getDashboardValueFromDb("Debits");
		String itemCount=getDashboardCountFromDb("Debits");
		String finalValue = dashboardIncomingDebitPayDecisionItemValue.getText().replaceAll(",", "");
		String finalTotalValue = dashboardIncomingDebitTotalValue.getText().replaceAll(",", "");
		assertTrue(isElementDisplayed(DashboardItems),"Dashboard screen displayed as expected");
		if (dashboardIncomingDebitPayDecision.getText().contains("Payment Decisions") && dashboardIncomingDebitPayDecisionItemCount.getText().equals(itemCount)
				&& finalValue.contains(itemValue) && finalTotalValue.contains(itemValue))
		{
			flag=true;
			publishResults(flag, "Incoming debits pay decision value is displayed", "Incoming debits pay decision value should be displayed", "verify Incoming debits pay decision value is displayed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming debits pay decision value is not displayed", "Incoming debits pay decision value should be displayed", "verify Incoming debits pay decision value is displayed");
		}
		return new DashboardPage(driver);
	}
	/*************************************************************************************
	 * Method Name: verifyLandingPageDashboardKappaDecision
	 * Purpose:Method for validating kappa decsion items for incoming debits in dashboard
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  20-Aug-2017
	/*************************************************************************************/		
	public DashboardPage verifyLandingPageDashboardKappaDecision() throws Exception 
	{		
		String itemValue=getDashboardValueFromDb("Debits");
		String itemCount=getDashboardCountFromDb("Debits");
		String finalValue = dashboardIncomingDebitKappaDecisionItemValue.getText().replaceAll(",", "");
		String finalTotalValue = dashboardIncomingDebitTotalValue.getText().replaceAll(",", "");
		assertTrue(isElementDisplayed(DashboardItems),"Dashboard screen displayed as expected");
		if (dashboardIncomingDebitKappaDecision.getText().contains("Kappa Decisions") && dashboardIncomingDebitKappaDecisionItemCount.getText().equals(itemCount)
				&& finalValue.contains(itemValue) && finalTotalValue.contains(itemValue))
		{
			flag=true;
			publishResults(flag, "Incoming debits kappa decision value is displayed", "Incoming debits kappa decision value should be displayed", "verify Incoming debits kappa decision value is displayed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming debits kappa decision value is not displayed", "Incoming debits kappa decision value should be displayed", "verify Incoming debits kappa decision value is displayed");
		}
		return new DashboardPage(driver);
	}
	/*************************************************************************************
	 * Method Name: verifyLandingPageDashboardTotal
	 * Purpose:Method for validating total items for incoming debits in dashboard
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  21-Aug-2017
	/*************************************************************************************/		
	public DashboardPage verifyLandingPageDashboardTotal() throws Exception 
	{	
		String itemValue=getDashboardValueFromDb("Debits");
		String itemCount=getDashboardCountFromDb("Debits");
		
		String finalTotalValue = dashboardIncomingDebitTotalValue.getText().replaceAll(",", "");
		assertTrue(isElementDisplayed(DashboardItems),"Dashboard screen displayed as expected");
		if (dashboardIncomingDebitTotal.getText().contains("Totals:") && dashboardIncomingDebitItemCount.getText().equals(itemCount)
			&& finalTotalValue.contains(itemValue) )
		{
			flag=true;
			publishResults(flag, "Incoming debits total value is displayed", "Incoming debits total value should be displayed", "verify Incoming debits total value is displayed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming debits total value is not displayed", "Incoming debits total value should be displayed", "verify Incoming debits total value is displayed");
		}
		return new DashboardPage(driver);
	}
	/*************************************************************************************
	 * Method Name: getDashboardValueFromDb
	 * Purpose:Method for getting fetching amount value from database
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  21-Aug-2017
	/*************************************************************************************/		
	public String getDashboardValueFromDb(String value) throws Exception 
	{
		String itemValue = null;
		String query = "";
		if (value=="Debits"){
			query = getAgencyValueFromDataSheet("IATotalDebitValue");
		}
		else if(value=="Credits"){
			query = getAgencyValueFromDataSheet("IATotalCreditValue");	
		}
		ResultSet rs1 = ICSProductDBConnection.getICSDBServerConnectionCluster(server, IA_db, query);
		while(rs1.next()){
			itemValue = rs1.getString(1);	
		}
		return itemValue;
	}
	/*************************************************************************************
	 * Method Name: getDashboardCountFromDb
	 * Purpose:Method for getting fetching amount count from database
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  22-Aug-2017
	/*************************************************************************************/			
	public String getDashboardCountFromDb(String value) throws Exception 
	{
		String itemCount = null;
		String query="";
		if (value=="Debits"){
			query = getAgencyValueFromDataSheet("IATotalDebitItemCount");
		}
		else if(value=="Credits"){
			query = getAgencyValueFromDataSheet("IATotalCreditItemCount");	
		}
		ResultSet rs2 = ICSProductDBConnection.getICSDBServerConnectionCluster(server, IA_db, query);
		while(rs2.next()){
			itemCount = rs2.getString(1);	
		}
		return itemCount;
	}
	/*************************************************************************************
	 * Method Name: verifyDashboardCollectedElsewhere
	 * Purpose:Method for validating amount for collected elsewhere items in dashboard
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  23-Aug-2017
	/*************************************************************************************/				
	public DashboardPage verifyDashboardCollectedElsewhere() throws Exception 
	{	
		String itemValue=getDashboardValueFromDb("Debits");
		String itemCount=getDashboardCountFromDb("Debits");
		
		pause(1000);
		String finalValue = dashboardIncomingCreditCollectedElsewhereItemValue.getText().replaceAll(",", "");
		//String finalTotalValue = dashboardIncomingCreditTotalItemValue.getText().replaceAll(",", "");
		assertTrue(isElementDisplayed(DashboardItems),"Dashboard screen displayed as expected");
		if (dashboardIncomingCreditCollectedElsewhereItemCount.getText().equals(itemCount)
				&& finalValue.contains(itemValue) /*&& finalTotalValue.contains(itemValue)*/)
		{
			flag=true;
			publishResults(flag, "Incoming credits collected elsewhere items value is displayed", "Incoming credits collected elsewhere items value should be displayed", "verify Incoming credits collected elsewhere items value is displayed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming credits collected elsewhere items value is not displayed", "Incoming credits collected elsewhere items value should be displayed", "verify Incoming credits collected elsewhere items value is displayed");
		}
		return new DashboardPage(driver);
	}
	/*************************************************************************************
	 * Method Name: verifyDashboardCollectedByMe
	 * Purpose:Method for validating amount for collected by me items in dashboard
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  23-Aug-2017
	/*************************************************************************************/				
	public DashboardPage verifyDashboardCollectedByMe() throws Exception 
	{	
		String itemValue=getDashboardValueFromDb("Credits");
		String itemCount=getDashboardCountFromDb("Credits");
		pause(1000);
		String finalValue = dashboardIncomingCreditCollectedByMeItemValue.getText().replaceAll(",", "");
		String finalTotalValue = dashboardIncomingCreditTotalItemValue.getText().replaceAll(",", "");
		assertTrue(isElementDisplayed(DashboardItems),"Dashboard screen displayed as expected");
		if (dashboardIncomingCreditCollectedByMeItemCount.getText().equals(itemCount)
				&& finalValue.contains(itemValue) && finalTotalValue.contains(itemValue))
		{
			flag=true;
			publishResults(flag, "Incoming credits CollectedByMe items value is displayed", "Incoming credits CollectedByMe items value should be displayed", "verify Incoming credits CollectedByMe items value is displayed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming credits CollectedByMe items value is not displayed", "Incoming credits CollectedByMe items value should be displayed", "verify Incoming credits CollectedByMe items value is displayed");
		}
		return new DashboardPage(driver);
	}
	/*************************************************************************************
	 * Method Name: verifyIncomingCreditsDashboardTotal
	 * Purpose:Method for validating total amount for incoming credit items in dashboard
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  23-Aug-2017
	/*************************************************************************************/	
	public DashboardPage verifyIncomingCreditsDashboardTotal() throws Exception 
	{	
		String itemValue=getDashboardValueFromDb("Credits");
		String itemCount=getDashboardCountFromDb("Credits");
		
		String finalTotalValue = dashboardIncomingCreditTotalItemValue.getText().replaceAll(",", "");
		assertTrue(isElementDisplayed(DashboardItems),"Dashboard screen displayed as expected");
		if (dashboardIncomingDebitTotal.getText().contains("Totals:") && dashboardIncomingCreditTotalItemCount.getText().equals(itemCount)
			&& finalTotalValue.equals(itemValue) )
		{
			flag=true;
			publishResults(flag, "Incoming credits total value is displayed", "Incoming credits total value should be displayed", "verify Incoming credits total value is displayed");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming credits total value is not displayed", "Incoming credits total value should be displayed", "verify Incoming credits total value is displayed");
		}
		return new DashboardPage(driver);
	}
	
	/*************************************************************************************
	 * Method Name: verifyIncomingCreditsConfigTime
	 * Purpose:Method for validating data is displayed after the config time passes
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  29-Aug-2017
	/*************************************************************************************/	
	public DashboardPage verifyIncomingCreditsConfigTime() throws Exception 
	{		
		assertTrue(isElementDisplayed(DashboardItems),"Dashboard screen displayed as expected");
		if (/*dashboardIncomingCreditTotalItemCount.getText().equals("£0.00")
			&& dashboardIncomingCreditCollectedByMeItemValue.getText().equals("£0.00") && */dashboardIncomingCreditCollectedElsewhereItemValue.getText().equals("£0.00") )
		{
			flag=true;
			publishResults(flag, "Incoming credits dashboard values not updated", "Incoming credits dashboard values should not be updated", "verify Incoming credits dashboard values not updated within the configured time");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming credits dashboard values is updated", "Incoming credits dashboard values should not be updated", "verify Incoming credits dashboard values not updated within the configured time");
		}
		pause(30000);
		clickElement(btnRefresh);
		if (/*dashboardIncomingCreditTotalItemCount.getText().equals("£0.00")
				&& dashboardIncomingCreditCollectedByMeItemValue.getText().equals("£0.00") &&*/ dashboardIncomingCreditCollectedElsewhereItemValue.getText().equals("£0.00") )
			{
				flag=true;
				publishResults(flag, "Incoming credits dashboard values not updated", "Incoming credits dashboard values should not be updated", "verify Incoming credits dashboard values not updated within the configured time");
			}else
			{	flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Incoming credits dashboard values is updated", "Incoming credits dashboard values should not be updated", "verify Incoming credits dashboard values not updated within the configured time");
			}
		pause(30000);
		clickElement(btnRefresh);
		//verifyIncomingCreditsDashboardTotal();
		verifyDashboardCollectedElsewhere();
		return new DashboardPage(driver);
	}
	
	/*************************************************************************************
	 * Method Name: verifyIncomingDebitsConfigTime
	 * Purpose:Method for validating data is displayed after the config time passes
	 * Author: Deepa Ramu
	 * Reviewed by : venkatasainath devisetty
	 * Created Date:  29-Aug-2017
	/*************************************************************************************/	
	public DashboardPage verifyIncomingDebitsConfigTime() throws Exception 
	{		
		assertTrue(isElementDisplayed(DashboardItems),"Dashboard screen displayed as expected");
		if (dashboardIncomingDebitTotalValue.getText().equals("£0.00")
			&& dashboardIncomingDebitPayDecisionItemValue.getText().equals("£0.00") )
		{
			flag=true;
			publishResults(flag, "Incoming debits dashboard values not updated", "Incoming debits dashboard values should not be updated", "verify Incoming debits dashboard values not updated within the configured time");
		}else
		{	flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag, "Incoming debits dashboard values is updated", "Incoming debits dashboard values should not be updated", "verify Incoming debits dashboard values not updated within the configured time");
		}
		pause(30000);
		clickElement(btnRefresh);
		if (dashboardIncomingDebitTotalValue.getText().equals("£0.00")
				&& dashboardIncomingDebitPayDecisionItemValue.getText().equals("£0.00") )
			{
				flag=true;
				publishResults(flag, "Incoming debits dashboard values not updated", "Incoming debits dashboard values should not be updated", "verify Incoming debits dashboard values not updated within the configured time");
			}else
			{	flag=false;
				finalTestScriptResultFailFlag = true;
				publishResults(flag, "Incoming debits dashboard values is updated", "Incoming debits dashboard values should not be updated", "verify Incoming debits dashboard values not updated within the configured time");
			}
		pause(30000);
		clickElement(btnRefresh);
		verifyLandingPageDashboardPayDecision();
		return new DashboardPage(driver);
	}
	
}
