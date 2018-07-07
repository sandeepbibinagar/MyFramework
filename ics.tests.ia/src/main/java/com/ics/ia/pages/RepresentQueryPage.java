package com.ics.ia.pages;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;


public class RepresentQueryPage extends ICSPageUtilis {	
		
		protected static boolean flag=false;
		public static boolean tempResult;
		public RepresentQueryPage(WebDriver driver) 
		{
			super(driver);
		}
		
		public RepresentQueryPage(WebDriver driver, String url) 
		{
			super(driver, url);
		}
		
	@FindBy(xpath  = "//ul[@class='site-menu']/li[3]/a")
	private static WebElement RepresentQueryMenu;
	@FindBy(xpath  = "//input[@id='ProcessingDate']")
	private static WebElement ProcessingDate;	
	@FindBy(xpath  = "//input[@id='ProcessingEndDate']")
	private static WebElement ProcessingDateTo;	
	@FindBy(xpath  = "//input[@id='SortCode']")
	private static WebElement SortCode;	
	@FindBy(xpath  = "//input[@id='Account']")
	private static WebElement Account;	
	@FindBy(xpath  = "//input[@id='Serial']")
	private static WebElement Serial;	
	@FindBy(xpath  = "//input[@id='Amount']")
	private static WebElement Amount;	
	@FindBy(xpath  = "//button[@id='btnSubmit2']")
	private static WebElement SubmitBtn;	
	@FindBy(xpath  = "//button[@id='btnReset']")
	private static WebElement ClearAllBtn;	
	
	@FindBy(xpath  = "//div[@id='validationErrors']")
	private static WebElement MinimumSearchError;	
	
	

	
	// Description : This method validates all fields on RepresentQuery page 
		public RepresentQueryPage validateRepresentQueryFields(){
			clickElement(RepresentQueryMenu);
			pause(4000);
			tempResult=	verifyTrue(ProcessingDate.isDisplayed(),"ProcessingDate is not Displayed!!");
			publishResults(tempResult,"ProcessingDate is present: "+ProcessingDate.isDisplayed()+"","ProcessingDate should be displayed", "ProcessingDate field Validation");
			tempResult=	verifyTrue(ProcessingDateTo.isDisplayed(),"ProcessingDateTo is not Displayed!!");
			publishResults(tempResult,"ProcessingDateTo is present: "+ProcessingDateTo.isDisplayed()+"","ProcessingDateTo should be displayed", "ProcessingDateTo field Validation");
			tempResult=	verifyTrue(SortCode.isDisplayed(),"SortCode is not Displayed!!");
			publishResults(tempResult,"SortCode is present: "+SortCode.isDisplayed()+"","SortCode should be displayed", "SortCode field Validation");
			tempResult=	verifyTrue(Account.isDisplayed(),"Account is not Displayed!!");
			publishResults(tempResult,"Account is present: "+Account.isDisplayed()+"","Account should be displayed", "Account field Validation");
			tempResult=	verifyTrue(Serial.isDisplayed(),"Serial is not Displayed!!");
			publishResults(tempResult,"Serial is present: "+Serial.isDisplayed()+"","Serial should be displayed", "Serial field Validation");
			tempResult=	verifyTrue(Amount.isDisplayed(),"Amount is not Displayed!!");
			publishResults(tempResult,"Amount is present: "+Amount.isDisplayed()+"","Amount should be displayed", "Amount field Validation");
			tempResult=	verifyTrue(SubmitBtn.isDisplayed(),"SubmitBtn is not Displayed!!");
			publishResults(tempResult,"SubmitBtn is present: "+SubmitBtn.isDisplayed()+"","SubmitBtn should be displayed", "SubmitBtn field Validation");
			tempResult=	verifyTrue(ClearAllBtn.isDisplayed(),"ClearAllBtn is not Displayed!!");
			publishResults(tempResult,"ClearAllBtn is present: "+ClearAllBtn.isDisplayed()+"","ClearAllBtn should be displayed", "ClearAllBtn field Validation");
			return this;
		}
		
		// Description : This method validates default value in Proc date field on RepresentQuery page 
		public RepresentQueryPage validateDefaultValueProcDate(){
			clickElement(RepresentQueryMenu);
			pause(4000);
			DateFormat date = new SimpleDateFormat("dd/MM/yyyy");
			Date dt=new Date();
			String CurrentDate=date.format(dt);
			String ProcDateVal=ProcessingDate.getAttribute("value");
			tempResult=	verifyTrue(CurrentDate.equals(ProcDateVal),"Current date is not prefilled in the Proc Date field!!");
			publishResults(tempResult,"Current date is prefilled in Proc date field: "+CurrentDate.equals(ProcDateVal)+"","Current date should be prefilled in the Proc Date field", "Current date in the Proc Date field Validation");
			return this;
			}
		
		// Description : This method validates minimum search criteria on RepresentQuery page 
		public RepresentQueryPage validateMinimumSearchCriteriaRepresentQuery(){
			clickElement(RepresentQueryMenu);
			pause(4000);
			String ErrorMsg= "1. The minimum criteria have not been entered. As a minimum, please enter at least\n"+
			"- Proc Date\n"+
			"- or Proc Date + SC\n"+
			"- or Proc Date + Account\n"+
			"- or Proc Date + Serial\n"+
			"- or Proc Date + Amount\n";
			clickElementEnter(ClearAllBtn);
			pause(1000);
			clickElementEnter(SubmitBtn);
			tempResult=	verifyTrue(MinimumSearchError.getText().trim().equals(ErrorMsg.trim()),"Minimum search criteria error is not displayed!!");
			publishResults(tempResult,"Minimum Search criteria is satisfied: "+MinimumSearchError.getText().trim().equals(ErrorMsg.trim())+"","Minimum Search criteria error for represent query should be displayed ", "Minimum search criteria for represent query Validation");
			return this;
			}
		
		// Description : This method validates Max date range on RepresentQuery page 
		public RepresentQueryPage validateSearchCriteriaMaximumDaysRepresentQuery(){
			clickElement(RepresentQueryMenu);
			pause(4000);
			fillTextBox(ProcessingDate, getIAValueFromDataSheet("ProcDateRepresentQuery"));
			fillTextBox(ProcessingDateTo, getIAValueFromDataSheet("ProcDateToRepresentQuery"));
			clickElementEnter(SubmitBtn);
			String ErrorMsg= "1. A date range of more than 14 calendar days is not permitted. Please refine your date range to within 14 calendar days.";
			pause(1000);
			tempResult=	verifyTrue(MinimumSearchError.getText().trim().equals(ErrorMsg.trim()),"Date range more than 14 days should not be permitted!!");
			publishResults(tempResult,"Date range more than 14 days is not permitted: "+MinimumSearchError.getText().trim().equals(ErrorMsg.trim())+"","Date range more than 14 days should not be permitted", "Date range more than 14 days on represent query Validation");
			return this;
			}
		
		// Description : This method validates use of wild card characters on RepresentQuery page 
		public RepresentQueryPage validateWildCardOnRepresentQuery(){
			clickElement(RepresentQueryMenu);
			pause(4000);
			clickElementEnter(ClearAllBtn);
			pause(1000);
			String SortCodeWithoutWildcard = "12345";
			String SerialWithoutWildcard = "12345";
			String AccountWithoutWildcard = "1234567";
			String AmountWithoutWildcard = "123";
			fillTextBox(SortCode, getIAValueFromDataSheet("SortCode_repItem_wildcard"));
			fillTextBox(Serial, getIAValueFromDataSheet("Serial_repItem_wildcard"));
			fillTextBox(Account, getIAValueFromDataSheet("Account_repItem_wildcard"));
			fillTextBox(Amount, getIAValueFromDataSheet("Amount_repItem_wildcard"));
			tempResult=	verifyTrue(SortCodeWithoutWildcard.equals(SortCode.getAttribute("value")),"Sorcode field doesnt allow wildcard chars on represent query page!!");
			publishResults(tempResult,"Sortcode does not allow wildcard chars: "+SortCodeWithoutWildcard.equals(SortCode.getAttribute("value"))+"","Sortcode should not allow wildcard chars on represent items query page", "Sorcode field for wildcard char Validation");
			tempResult=	verifyTrue(SerialWithoutWildcard.equals(Serial.getAttribute("value")),"Serial field doesnt allow wildcard chars on represent query page!!");
			publishResults(tempResult,"Serial does not allow wildcard chars: "+SerialWithoutWildcard.equals(Serial.getAttribute("value"))+"","Sortcode should not allow wildcard chars on represent items query page", "Serial field for wildcard char Validation");
			tempResult=	verifyTrue(AccountWithoutWildcard.equals(Account.getAttribute("value")),"Account field doesnt allow wildcard chars on represent query page!!");
			publishResults(tempResult,"Account does not allow wildcard chars: "+AccountWithoutWildcard.equals(Account.getAttribute("value"))+"","Account should not allow wildcard chars on represent items query page", "Account field for wildcard char Validation");
			tempResult=	verifyTrue(AmountWithoutWildcard.equals(Amount.getAttribute("value")),"Amount field doesnt allow wildcard chars on represent query page!!");
			publishResults(tempResult,"Amount does not allow wildcard chars: "+AmountWithoutWildcard.equals(Amount.getAttribute("value"))+"","Amount should not allow wildcard chars on represent items query page", "Amount field for wildcard char Validation");
			return this;
			}
				
		
}
