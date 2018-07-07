package com.ics.dew.pages;
import java.util.*;
import java.io.IOException;
import java.sql.ResultSet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;
import com.ics.dew.db.DewDbQueries;
import com.ics.externalFactoryUtilis.*;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DewSearchItemsPage extends ICSPageUtilis {	


	static String searchPageResult2;
	static String searchPageResult1;


	@FindBy(xpath = "//span[@expanded-text='Search Items']")
	private static WebElement linkSearchItemsMenu;

	
	
	@FindBy(xpath = ".//*[@id='SearchResultID']/tfoot/tr/td/a[3]")
	private static WebElement nextPage;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[6]/div[1]/label[2]")
	private static WebElement uniqueIdentifier;

	@FindBy(id = "cmbSiSearchType")
	private static WebElement textBoxSearchType;

	@FindBy(id = "SearchResultID")
	private static WebElement searchResultPageTable;
	
	@FindBy(id = "dtpSiFromDatePicker")
	private  WebElement FromDate;

	@FindBy(id = "dtpSiToDatePicker")
	private static WebElement ToDate;

	@FindBy(id = "searchSubmit")
	private static WebElement buttonSearch;

	@FindBy(id = "searchClear")
	private static WebElement buttonClear;

	@FindBy(id = "nav-title")
	private static WebElement searchItemResultPageHeader;

	@FindBy(xpath = ".//*[@id='SearchResultID']/tbody/tr[2]/td/table/tbody/tr/td[1]/div[18]/div[2]")
	private static WebElement finalDecisionValueOfItem;

	@FindBy(id = "cmbSiWorkGroup")
	private static WebElement dropDownWorkGroup;

	@FindBy(id = "cmbSiWorkStream")
	private static WebElement dropDownWorkStream;	

	@FindBy(id = "cmbSiSubWorkStream")
	private static WebElement dropDownSubWorkStream;	

	@FindBy(id = "cmbSiProcesses")
	private static WebElement dropDownProcess;	

	@FindBy(id = "cmbSiProcesses//option")
	private static WebElement dropDownProcessOptions;	

	@FindBy(id = "cmbSiFinalPaymentsDecision")
	private static WebElement dropDownFinalPaymentDecision;

	@FindBy(id = "chkEndOfDayDefaulted")
	private static WebElement checkBoxDefaultDecisionApplied;	

	@FindBy(id = "txtSiAmount")
	private static WebElement textAmount;	

	@FindBy(id = "txtSiAccountNumber")
	private static WebElement textAccountNumber;	

	@FindBy(id = "txtSiSerial")
	private static WebElement textSerial;	

	@FindBy(id = "txtSiAccountSortCode")
	private static WebElement textSortCode;	

	@FindBy(id = "txtSiUserName")
	private static WebElement textUserName;	

	@FindBy(id = "txtSiUniqueIdentifier")
	private static WebElement textUniqueIdentifier;

	@FindBy(id = "messageInfo")
	private static WebElement textMessage;

	@FindBy(xpath="//div[@class='datepicker-days']/table/thead/tr/th[@class='prev']")
	private static WebElement datepickerPreviousButton;

	@FindBy(xpath="//div[@class='datepicker-days']/table/thead/tr/th[@class='datepicker-switch']")
	private static WebElement datepickerDisplayedMonthYear;	

	@FindBy(id="SearchResultID")
	private static WebElement searchResultTable;

	@FindBy(id="btnTakeOwnership")
	private static WebElement btnTakeOwnership;

	@FindBy(xpath = ".//*[@id='main-content']/form/div/div[1]/h4/label")
	private static WebElement searchPageHeader;

	@FindBy(xpath = ".//*[@id='SearchResultID']/tbody/tr/td[1]")
	private static WebElement weblinkExpand;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[1]")
	private static WebElement headerColExpandAll;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[3]")
	private static WebElement headerColSortcode;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[4]")
	private static WebElement headerColAccNumber;

	@FindBy(xpath = ".//*[@id='SearchResultID']/tbody/tr/td[5]")
	private static WebElement searchResultSerialNumber;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[5]")
	private static WebElement headerColSerialNumber;

	
	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[6]")
	private static WebElement headerColAmount;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[7]")
	private static WebElement headerColWorkGroup;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[8]")
	private static WebElement headerColWorkStream;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[9]")
	private static WebElement headerColProcess;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[10]")
	private static WebElement headerColOwner;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[11]")
	private static WebElement headerColFinal;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[12]")
	private static WebElement headerColDefaultApplied;

	@FindBy(xpath = ".//*[@id='SearchResultID']/thead/tr/th[13]")
	private static WebElement headerColSerialTimeStamp;


	@FindBy(xpath = ".//*[@id='SearchResultID']/tbody/tr/td[2]")
	private static WebElement weblinkView;

	@FindBy(xpath = ".//*[@id='SearchResultID']/tbody/tr/td[11]")
	private static WebElement checkBoxFinal;



	public DewSearchItemsPage isExpAllColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColExpandAll.isDisplayed(), "ExpAll Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "ExpAll Column is present ", "ExpAll Column should be present", "ExpAll Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "ExpAll Column is not present ", "ExpAll Column should be present", "ExpAll Column Validation in header");
		}

		return this;		
	}

	public DewSearchItemsPage isSortCodeColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColSortcode.isDisplayed(), "Sortcode Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Sortcode Column is present ", "Sortcode Column should be present", "Sortcode Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Sortcode Column is not present ", "Sortcode Column should be present", "Sortcode Column Validation in header");
		}

		return this;		
	}


	public DewSearchItemsPage isSerialNumberColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColSerialNumber.isDisplayed(), "Serial Number Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Serial Number Column is present ", "Serial Number Column should be present", "Serial Number Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Serial Number Column is not present ", "Serial Number Column should be present", "Serial Number Column Validation in header");
		}

		return this;		
	}
	
	
	
	public DewSearchItemsPage capturingSearchPageResult1()
	{
		searchPageResult1 = searchResultSerialNumber.getText();
		System.out.println("111111" + searchPageResult1);
		
		return this;
	}

	public DewSearchItemsPage capturingSearchPageResult2()
	{
		
		searchPageResult2 = searchResultSerialNumber.getText();
		System.out.println("22222" + searchPageResult2);

		
		return this;
	}

	public DewSearchItemsPage validateOnClickBackPreviousSearchresultPage()
	{
		
		verifyTrue(searchPageResult1.equals(searchPageResult2), "Search Result page is not same as the previous Search result Page");
		return this;
	}
	
	
	public DewSearchItemsPage isAccNumberColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColAccNumber.isDisplayed(), "Account Number Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Account Number Column is present ", "Account Number Column should be present", "Account Number Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Account Number is not present ", "Account Number Column should be present", "Account Number Column Validation in header");
		}

		return this;		
	}


	public DewSearchItemsPage isAmountColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColAmount.isDisplayed(), "Amount Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Amount Column is present ", "Amount Column should be present", "Amount Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Amount Column is not present ", "Amount Column should be present", "Amount Column Validation in header");
		}

		return this;		
	}

	public DewSearchItemsPage isWorkGroupColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColWorkGroup.isDisplayed(), "Work Group Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Work Group Column is present ", "Work Group Column should be present", "Work Group Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Work Group Column is not present ", "Work Group Column should be present", "Work Group Column Validation in header");
		}

		return this;		
	}


	public DewSearchItemsPage isWorkStreamColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColWorkStream.isDisplayed(), "Work Stream Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Work Stream Column is present ", "Work Stream Column should be present", "Work Stream Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Work Stream Column is not present ", "Work Stream Column should be present", "Work Stream Column Validation in header");
		}

		return this;		
	}

	public DewSearchItemsPage isProcessColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColProcess.isDisplayed(), "Process Number Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Process Column is present ", "Process Column should be present", "Process Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Process Column is not present ", "Process Column should be present", "Process Column Validation in header");
		}

		return this;		
	}	

	public DewSearchItemsPage isOwnerColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColOwner.isDisplayed(), "Owner Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Owner Column is present ", "Owner Column should be present", "Owner Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Owner Column is not present ", "Owner Column should be present", "Owner Column Validation in header");
		}

		return this;		
	}

	public DewSearchItemsPage isFinalColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColFinal.isDisplayed(), "Final Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Final Column is present ", "Final Column should be present", "Final Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Final Column is not present ", "Final Column should be present", "Final Column Validation in header");
		}

		return this;		
	}


	public DewSearchItemsPage isDefaultAppliedColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColDefaultApplied.isDisplayed(), "Default Applied Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Default Applied Column is present ", "Default Applied Column should be present", "Default Applied Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Default Applied Column is not present ", "Default Applied Column should be present", "Default Applied Column Validation in header");
		}

		return this;		
	}

	public DewSearchItemsPage isTimestampColPresentInHeader() {

		boolean tempResult = verifyTrue(headerColSerialTimeStamp.isDisplayed(), "Time Stamp Column is not present");

		if (tempResult == true)
		{


			publishResults(tempResult, "Time Stamp Column is present ", "Time Stamp Column should be present", "Time Stamp Column Validation in header");

		}
		else
		{

			publishResults(tempResult, "Time Stamp Column is not present ", "Time Stamp Column should be present", "Time Stamp Column Validation in header");
		}

		return this;		
	}




	public DewSearchItemsPage valuetextAccountSortCode() {
		fillTextBox(textSortCode, ICSPropertiesConfig.getAccountSortCode());
		return this;		
	}

	public DewSearchItemsPage valuetextAccountNumber() {
		fillTextBox(textAccountNumber, ICSPropertiesConfig.getAccountNumber());
		return this;
	}

	public DewSearchItemsPage valuetextSerial() {
		fillTextBox(textSerial, ICSPropertiesConfig.getSerial());
		return this;		
	}

	public DewSearchItemsPage valuetextAmount() {
		fillTextBox(textAmount, ICSPropertiesConfig.getAmount());
		return this;
	}

	public DewSearchItemsPage valuetextUserName() {
		fillTextBox(textUserName, ICSPropertiesConfig.getSearchScreenuserName());
		return this;
	}

	public DewSearchItemsPage valuetextUniqueIdentifier() {
		fillTextBox(textUniqueIdentifier, ICSPropertiesConfig.getUniqueIdentifier());
		return this;		
	}

	public DewSearchItemsPage textUniqueIdentifierOfItem() {
		fillTextBox(textUniqueIdentifier, DewPendingPaymentsPage.uniqueId1);
		return this;		
	}
	

	public DewSearchItemsPage(WebDriver driver) {
		super(driver);
	}

	public DewSearchItemsPage(WebDriver driver, String url) {
		super(driver, url);
	}

	public DewSearchItemsPage clearWorkGroup() {	
		dropDownWorkGroup.clear();
		return this;
	}

	public DewSearchItemsPage clearWorkStream() {	
		dropDownWorkStream.clear();		 
		return this;
	}

	public DewSearchItemsPage pageTitle() {	
		getTitle(driver);	 
		return this;
	}

	public DewSearchItemsPage passEodUrl(String Url) {
		getUrl(Url);
		return this;
	}

	public DewSearchItemsPage acceptEodAlert() {
		switchToAlert().accept();
		return this;
	} 

	public DewSearchItemsPage validateEodAssertMessage() {		
		assertTrue(switchToAlert().getText().equals("End of Day WARNING"),"true");
		return this;
	} 

	public DewSearchItemsPage pageTitleValidation() {			

		verifyTrue(getTextSearchPageHeader().equals(ICSPropertiesConfig.getsearchItemPageTitle()), "Page title is not correct");
		pause(5000);

		return this;
	}

	public String searchItemdefaultValue() {
		return (textBoxSearchType.getAttribute("value"));
	}

	public DewSearchItemsPage searchItemdefaultValueValidation() {			

		boolean result = verifyTrue(searchItemdefaultValue().equals(ICSPropertiesConfig.getsearchItemDefaultValue()), "Default option  is not as expected");

		return this;
	}

	public String fromDateDefaultValue() {
		return (FromDate.getAttribute("value"));
	}

	public DewSearchItemsPage fromDateDefaultValueValidation() {	

		verifyTrue(fromDateDefaultValue().equals(getSystemDateWithRequiredFormat("dd/MM/YYYY")), "Default Value is not as expected");
		return this;
	}

	public String toDateDefaultValue() {	
		return (ToDate.getAttribute("value"));
	}

	public DewSearchItemsPage toDateDefaultValueValidation() {	
		verifyTrue(toDateDefaultValue().equals(getSystemDateWithRequiredFormat("dd/MM/YYYY")), "Default Value is not as expected");
		return this;
	}	

	public DewSearchItemsPage clickSearchButton() {	
		actionMouseClickEvent(buttonSearch);		
		pause(10000);
		return this;
	}

	public String textMessageonClick() {	
		return (textMessage.getText());		

	}

	public String getTextSearchPageHeader() {	
		return (searchPageHeader.getText());		

	}




	public DewSearchItemsPage searchCriteriamessageValidation() {	

		verifyTrue(textMessageonClick().equals(ICSPropertiesConfig.getDewsearchCriteriaMessage()), "Message is not as expected");	
		return this;

	}

	public DewSearchItemsPage refineSearchMessageValidation() {	

		verifyTrue(textMessageonClick().equals(ICSPropertiesConfig.getDewRefineSearchCriteriaMessage()), "Refine Search Message is not as expected");		
		return this;

	}

	public DewSearchItemsPage clickClearButton() {	
		actionMouseClickEvent(buttonClear);		
		pause(5000);
		return this;
	}

	public DewSearchItemsPage clickViewLink() {	
		actionMouseClickEvent(weblinkView);		
		pause(5000);
		return this;
	}

	public DewSearchItemsPage isViewLinkPresent() {


		boolean tempResult=verifyTrue(weblinkView.isDisplayed(),"View Link for item is not present ");

		publishResults(tempResult, (weblinkView.isDisplayed())?"View Link for item is present":"View Link for an item is not present","CheckBox Final should be selected","View Link Validation");

		return this;
	}



	public DewSearchItemsPage selectWorkgroup() {	
		selectDropdownValue(driver, dropDownWorkGroup, ICSPropertiesConfig.getDewWorkgroupValue());		
		pause(10000);
		return this;
	}

	public DewSearchItemsPage selectWorkstream() {	
		selectDropdownValue(driver, dropDownWorkStream, ICSPropertiesConfig.getDewWorkStreamValue());
		pause(30000);
		return this;
	}

	public DewSearchItemsPage selectSubWorkstream() {	
		selectDropdownValue(driver, dropDownSubWorkStream, ICSPropertiesConfig.getDewSubWorkStreamValue());
		pause(10000);
		return this;
	}

	public DewSearchItemsPage selectFinalPaymentDecision() {
		selectDropdownValue(driver, dropDownFinalPaymentDecision, ICSPropertiesConfig.getDewFinalPaymentDecision());
		pause(10000);
		return this;
	}

	public DewSearchItemsPage isWorkstreamEnabled()
	{	
		assertTrue(dropDownWorkStream.isEnabled(), "Workstream is not  enabled");
		return this;
	}

	public DewSearchItemsPage isWorkstreamDisabled()
	{	
		verifyFalse(dropDownWorkStream.isEnabled(), "Workstream is not greyed out");
		return this;
	}

	public DewSearchItemsPage isSubWorkstreamDisabled()
	{
		verifyFalse(dropDownSubWorkStream.isEnabled(), "Sub Workstream is not disabled");
		return this;
	}

	public DewSearchItemsPage isSubWorkstreamEnabled()
	{
		verifyFalse(dropDownSubWorkStream.isEnabled(), "Sub Workstream field is not disabled");
		return this;
	}

	public DewSearchItemsPage isProcessEnabled()
	{
		verifyFalse(dropDownProcess.isEnabled(), "Process  field is not disabled");
		return this;
	}

	public DewSearchItemsPage processValidation()
	{
		int size;
		Select se = new Select(dropDownProcess);
		java.util.List<WebElement> l = se.getOptions(); 

		size=l.size();
		if (size == 2)
		{

			verifyTrue((l.get(1).getText()).equals("System"), "Process drop down has one value only and it is System ");

		}

		else

		{
			System.out.println("Process dropdown has more than 1 value");

		}

		return this;
	}


	public DewSearchItemsPage selectProcess() {

		selectDropdownValue(driver, dropDownProcess, ICSPropertiesConfig.getDewSelectProcessValue());
		return this;
	}

	public boolean getstateDefaultDecisionApplied() {
		return(checkBoxDefaultDecisionApplied.isSelected());
	}

	public DewSearchItemsPage selectFromDate() throws InterruptedException {
		setToFromDate(FromDate, ICSPropertiesConfig.getDewfromDate(), ICSPropertiesConfig.getDewfromDateMonthYear());
		return this;	
	}

	public DewSearchItemsPage selectToDate() throws InterruptedException {
		setToFromDate(ToDate, ICSPropertiesConfig.getDewtoDate(), ICSPropertiesConfig.getDewtoDateMonthYear());
		return this;
	}

	public DewSearchItemsPage setToFromDate(WebElement element,String Date, String monthandyear) throws InterruptedException {	
		actionMouseClickEvent(element);
		Thread.sleep(5000);
		String displayedmonthandyear=getText(datepickerDisplayedMonthYear);
		while(!(monthandyear.trim().equalsIgnoreCase(displayedmonthandyear.trim())))
		{    	
			actionMouseClickEvent(datepickerPreviousButton);
			displayedmonthandyear=getText(datepickerDisplayedMonthYear);

		}		
		actionMouseClickEvent(driver.findElement(By.xpath("//div[@class='datepicker-days']/table/tbody/tr/td[text()='"+Date+"' and @class='day']")));     
		return this;
	}

	public DewSearchItemsPage clickSearchItemsMenu() {	
		actionMouseClickEvent(linkSearchItemsMenu);		
		pause(20000);	
		return this;		
	}


	public DewSearchItemsPage clickEXPLink() {	
		actionMouseClickEvent(weblinkExpand);		
		pause(10000);
		return this;
	}

	public DewSearchItemsPage validateFinalPaymentStatus() {
		//create xpath for it and add code
		return this;
	}

	public DewSearchItemsPage validateFinalPaymentStatusNotPaidBySystem() {
		//create xpath for it and add code
		return this;
	}
	public DewSearchItemsPage validateFinalPaymentStatusPaidBySystem() {
		//create xpath for it and add code
		return this;
	}

	public DewSearchItemsPage validateFinalCheckbox() {


		boolean tempResult=verifyTrue(checkBoxFinal.isSelected(),"Check Box Final is not selected");

		publishResults(tempResult, (checkBoxFinal.isSelected())?"Checkbox Final is selected":"Checkbox Final is not selected","CheckBox Final should be selected","CheckBox Final Validation");


		return this;
	}


	public DewSearchItemsPage validateFinalPaymentStatusPaidByPosting() {

		//boolean tempResult=false;	
		verifyTrue(finalDecisionValueOfItem.getText().equals("Paid by Posting File"),"Payment status is not Paid by Posting File");
		publishResults(finalDecisionValueOfItem.getText().equals("Paid by Posting File"), (finalDecisionValueOfItem.getText().equals("Paid by Posting File"))?"Payment status is Paid by Posting File":"Payment status is not Paid by Posting File","Payment status should be Paid by Posting File","Payment status Validation");

		/*tempResult = verifyTrue(finalDecisionValueOfItem.getText().equals("Paid by Posting File"),"Payment status is not Paid by Posting File");

		if (tempResult == false)
		{
			System.out.println("falseeeeeeeeeeeeeee");

			publishResults(tempResult, "Payment status is other than <Paid by Posting File> ", "Payment status should be <Paid by Posting File>", "Payment status Validation");

		}
		else
		{

			System.out.println("trueeeeeeeeeeee");


			publishResults(tempResult, "Payment status is <Paid by Posting File> ", "Payment status should be <Paid by Posting File>", "Payment status Validation");
		}
		 */		
		return this;
	}

	public DewSearchItemsPage validateFinalPaymentStatusNotPaidByPosting() {


		boolean tempResult=	verifyTrue(finalDecisionValueOfItem.getText().equals("Not Paid by System"),"Payment status is other than Not Paid by System");
		publishResults(tempResult, (finalDecisionValueOfItem.getText().equals("Not Paid by System"))?"Payment status is Not Paid by System":"Payment status is other than Not Paid by System","Payment status should be Not Paid by System","Payment status Validation");

		/*tempResult = verifyTrue(finalDecisionValueOfItem.getText().equals("Paid by Posting File"),"Payment status is not Paid by Posting File");

		if (tempResult == false)
		{
			System.out.println("falseeeeeeeeeeeeeee");

			publishResults(tempResult, "Payment status is other than <Paid by Posting File> ", "Payment status should be <Paid by Posting File>", "Payment status Validation");

		}
		else
		{

			System.out.println("trueeeeeeeeeeee");


			publishResults(tempResult, "Payment status is <Paid by Posting File> ", "Payment status should be <Paid by Posting File>", "Payment status Validation");
		}
		 */		
		return this;
	}



	public DewSearchItemsPage executeLoadMsg06(String command) throws IOException {


		String dewServerName= ICSPropertiesConfig.getDewServername();
		String dewDBName = ICSPropertiesConfig.getDewDatabaseName();
		String filePath1= "\\\\129.227.32.216\\w$\\DEW\\06MD01 New\\All scripts\\";
		String fileName1 = "Load MSg06.sql";


		command =  "cmd /c sqlcmd -m 1 -S "+dewServerName+" -d "+dewDBName+" -i "+
				filePath1 + fileName1;

		ICSDBUtilis.sqlCommandExecution(command);

		return this;

	}

	public DewSearchItemsPage executePostingResponse(String command) throws IOException {


		String dewServerName= ICSPropertiesConfig.getDewServername();
		String dewDBName = ICSPropertiesConfig.getDewDatabaseName();
		String filePath1= "\\\\129.227.32.216\\w$\\DEW\\06MD01 New\\All scripts\\";
		String fileName1 = "Posting Response.sql";


		command =  "cmd /c sqlcmd -m 1 -S "+dewServerName+" -d "+dewDBName+" -i "+
				filePath1 + fileName1;

		ICSDBUtilis.sqlCommandExecution(command);

		return this;

	}



	public DewSearchItemsPage validateTotalNoOfItems() {

		/*
		 * 	Data required
		 */
		return this;	
	}

	public DewPendingPaymentsPage switchToPendingPaymentPage() {	
		
		return new DewPendingPaymentsPage(driver);		
	}	

	
	
	
	public DewSearchItemsPage validateFinalDecisionOfItem() {

		/*
		 * 	Data required
		 */
		return this;	
	}

	public DewSearchItemsPage validatePaymentPendingButtons() {

		/*
		 * 	Data required
		 */
		return this;	
	}

	public DewSearchItemsPage validateMessagesOnClickSearch() throws InterruptedException {

		try{
			if(searchPageHeader.isDisplayed())

			{

				refineSearchMessageValidation();
				clickClearButton();
			}
		}
		catch(Exception e)



		{

			clickSearchItemsMenu();

		}	

		return this;	
	}


	public DewSearchItemsPage isTakeOwnershipButtonDisplayed() throws InterruptedException {


		boolean tempResult = verifyFalse(btnTakeOwnership.isDisplayed(),"Take Ownership button is displayed");

		if (tempResult == false)
		{

			publishResults(tempResult, "Take Ownership button is not displayed", "Take Ownership button should not be disabled", "Take Ownership button Validation");

		}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(tempResult, "Take Ownership button is displayed", "Take Ownership button should not be disabled", "Take Ownership button Validation");

		}

		return this;	

	}


	public DewSearchItemsPage validateItemCountOnSearchPageResult() throws Exception {

		String itemCountDB = null;
		ResultSet rs1 = DewDbQueries.itemCountOnWorkgroupWorkstream();

		while(rs1.next())	
		{

			itemCountDB =rs1.getString(1);
		}

		int itemFinalCountWeb= getWebElementsCount(".//*[@id='SearchResultID']/tbody/tr/td[2]/a");

		try{
			if ( driver.findElement(By.xpath(".//*[@id='SearchResultID']/tfoot")).isDisplayed())

			{
				int footSize = getWebElementsCount(".//*[@id='SearchResultID']/tfoot/tr/td[1]/a");
				System.out.println("first page size " + itemFinalCountWeb );
				for(int j=1;j<=footSize;j++)
				{

					String t=driver.findElement(By.xpath(".//*[@id='SearchResultID']/tfoot/tr/td/a["+j+"]")).getAttribute("title");
					if(t.equalsIgnoreCase("Go to next page."))
					{
						driver.findElement(By.xpath(".//*[@id='SearchResultID']/tfoot/tr/td/a["+j+"]")).click();
						pause(30000);
						itemFinalCountWeb+=getWebElementsCount(".//*[@id='SearchResultID']/tbody/tr/td[2]/a");
						System.out.println(itemFinalCountWeb+"total count in loop");
					}
				}


			}

			String itemFinalCountWebString = String.valueOf(itemFinalCountWeb);

			boolean tempResult= verifyEquals(itemFinalCountWeb,itemCountDB , "Item Count on search Result Page is matching DB");

			publishResults(tempResult,itemFinalCountWebString,itemCountDB, "Item Count Validation on Search Result Page "  );


		}

		catch(Exception e)

		{

			e.getStackTrace();

		}	

		return this;


	}

	public DewSearchItemsPage validateWorkstream(){

		String actualWorkstream = null;
		String expectedWorkstream = ICSPropertiesConfig.getDewWorkStreamSDCname();

		try{

			ResultSet rs1 = DewDbQueries.itemsOnTimestamp();

			while(rs1.next())	
			{
				actualWorkstream=rs1.getString(2);
				boolean tempResult=false;
				tempResult = verifyNotEquals(actualWorkstream,expectedWorkstream, "Workstream is Standard Debit Clearing" );
				publishResults(tempResult, actualWorkstream, expectedWorkstream, "Workstream Validation (Should not be Standard Debit Clearing)");

			}
		}

		catch(Exception e)

		{
			e.getStackTrace();
		}	

		return this;
	}


}
