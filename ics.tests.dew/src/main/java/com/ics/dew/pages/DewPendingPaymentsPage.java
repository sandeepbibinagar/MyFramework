package com.ics.dew.pages;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.ics.dew.db.DewDbQueries;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;
import com.microsoft.windowsazure.management.configuration.PublishSettingsLoader;



public class DewPendingPaymentsPage extends ICSPageUtilis{


	static String uniqueId1;
	static String uniqueId2;

	@FindBy(xpath = "//span[@expanded-text='Search Items']")
	private static WebElement linkSearchItemsMenu;

	@FindBy(id="btnTakeOwnership")
	private static WebElement btnTakeOwnership;

	@FindBy(id="submitBack")
	private static WebElement buttonBack;

	


	@FindBy(xpath = "/html/body/div[3]/div/div/div/ul/li[3]/a")
	private static WebElement linkPendingPayments;

	@FindBy(id = "PPWorkGroupDD")
	private static WebElement dropDownWorkGroup;

	@FindBy(id = "decisionSuspend")
	private static WebElement buttonSuspend;

	@FindBy(id = "decisionPass")
	private static WebElement buttonPass;

	@FindBy(id = "decisionVerify")
	private static WebElement buttonVerify;

	@FindBy(id = "decisionFail")
	private static WebElement buttonFail;

	@FindBy(id = "userDecisionReferalNotification")
	private static WebElement buttonReferalNotification;

	@FindBy(id = "submitNext")
	private static WebElement buttonNext;

	@FindBy(id = "PPWorkStreamDD")
	private static WebElement dropDownWorkStream;	

	@FindBy(id = "PPSubWorkStreamDD")
	private static WebElement dropDownSubWorkStream;	

	@FindBy(id = "PPProcessesDD")
	private static WebElement dropDownProcess;	

	@FindBy(id = "PPCustomerSegmentDD")
	private static WebElement dropdownCustomerSegment;

	@FindBy(id = "userSubmit")
	private static WebElement btnStartProcessing;	

	@FindBy(id = "PPReferralName")
	private static WebElement textTeamName;

	@FindBy(id = "PPReferralEmail")
	private static WebElement textLocalEmail;		

	@FindBy(id = "PPReferralFaxNumber")
	private static WebElement textFaxNumber;	

	@FindBy(id = "PPReferralPhoneNumber")
	private static WebElement textPhoneNumber;

	@FindBy(id = "PPConfirmContact")
	private  WebElement checkBoxConfirmContact;

	@FindBy(id = "Comments")
	private static WebElement textComments;

	@FindBy(id = "FailReason")
	private static WebElement dropDownFailReason;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[6]/div[1]/label[2]")
	private static WebElement pendingPaymentUniqueIdentifier;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[2]/div[1]/label[2]")
	private static WebElement pendingPaymentSortCode;



	@FindBy(xpath = ".//*[@id='InfoList']/div/div[2]/div[2]/label[2]")
	private static WebElement pendingPaymentAccount;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[2]/div[3]/label[2]")
	private static WebElement pendingPaymentSerial;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[2]/div[4]/label[2]")
	private static WebElement pendingPaymentAmount;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[3]/div[1]/label[2]")
	private static WebElement pendingPaymentCBSortCode;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[3]/div[2]/label[2]")
	private static WebElement pendingPaymentSystemDefaultDecision;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[3]/div[3]/label[2]")
	private static WebElement pendingPaymentSwitchedAccount;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[3]/div[4]/label[2]")
	private static WebElement pendingPaymentRepresented;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[4]/div[1]/label[2]")
	private static WebElement pendingPaymentCustomerSegment;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[4]/div[2]/label[2]")
	private static WebElement pendingPaymentFailReason;

	@FindBy(xpath = ".//*[@id='InfoList']/div/div[5]/div[1]/label[2]")
	private static WebElement pendingPaymentChannelMethod;

	@FindBy(id="viewhistory")
	private static WebElement pendingPaymentButtonHistory;

	@FindBy(id="AutoFetchChkBox")
	private static WebElement autoFetchCeckBox;



	public DewPendingPaymentsPage valuetextComments() {
		fillTextBox(textComments, "Comments");
		return this;
	}	

	public DewPendingPaymentsPage selectdropdownFailReason() {	

		selectDropdownValue(driver, dropDownFailReason, ICSPropertiesConfig.getDewPPFailReaosn() );		
		pause(10000);
		return this;
	}



	public DewPendingPaymentsPage(WebDriver driver) {
		super(driver);
	}
	public DewPendingPaymentsPage(WebDriver driver, String url) {
		super(driver, url);
	}
	public DewPendingPaymentsPage clickPendingPaymentsMenu() {
		actionMouseClickEvent(linkPendingPayments);
		return this;
	}

	public DewSearchItemsPage clickSearchItemsMenu() {
		actionMouseClickEvent(linkSearchItemsMenu);
		pause(10000);
		return new DewSearchItemsPage(driver);
	}

	public DewSearchItemsPage clickBackButton() {
		actionMouseClickEvent(buttonBack);
		return new DewSearchItemsPage(driver);	
	}


	public DewPendingPaymentsPage passEodUrl(String Url) {
		getUrl(Url);
		return this;
	}
	public DewPendingPaymentsPage validateEodAssertMessage() {		
		assertTrue(switchToAlert().getText().equals("End of Day WARNING"),"true");
		return this;
	} 
	public DewPendingPaymentsPage acceptEodAlert() {
		switchToAlert().accept();
		return this;
	} 

	public DewPendingPaymentsPage selectWorkgroup() {	

		selectDropdownValue(driver,dropDownWorkGroup,ICSPropertiesConfig.getDewPPWorkgroupValue());		
		pause(10000);

		return this;

	}

	public DewPendingPaymentsPage selectWorkStream() {	

		selectDropdownValue(driver,dropDownWorkStream , ICSPropertiesConfig.getDewPPWorkStreamValue());		
		pause(5000);
		return this;
	}

	public DewPendingPaymentsPage selectSubWorkStream() {	

		selectDropdownValue(driver,dropDownSubWorkStream , ICSPropertiesConfig.getDewPPSubWorkStreamValue());		
		if (ICSPropertiesConfig.getDewPPSubWorkStreamValue().equals("Refer"))
		{
			selectcustomerSegment();	
		}
		pause(5000);
		return this;

	}

	public DewPendingPaymentsPage selectProcess() {	

		selectDropdownValue(driver,dropDownProcess , ICSPropertiesConfig.getDewPPProcessValue());		
		pause(5000);
		return this;

	}

	public DewPendingPaymentsPage selectcustomerSegment() {	

		selectDropdownValue(driver,dropdownCustomerSegment , ICSPropertiesConfig.getDewPPCustomerSegmentValue());		
		pause(10000);
		return this;

	}

	public DewPendingPaymentsPage isSuspendEnable()
	{	

		boolean tempResult=verifyTrue(buttonSuspend.isEnabled(),"Suspend button is not enabled");

		publishResults(tempResult, (buttonSuspend.isEnabled())?"Suspend button is enabled":"Suspend button is disabled","Suspend Button should be enabled","Suspend Button Validation");

		return this;

	}


	public DewPendingPaymentsPage isPassEnable()
	{	

		boolean tempResult=verifyTrue(buttonPass.isEnabled(),"Pass button is not enabled");

		publishResults(tempResult, (buttonPass.isEnabled())?"Pass button is enabled":"Pass button is disabled","Pass Button should be enabled","Pass Button Validation");

		return this;

	}

	public DewPendingPaymentsPage isVerifyEnable()
	{	

		boolean tempResult=verifyTrue(buttonVerify.isEnabled(),"Verify button is not enabled");

		publishResults(tempResult, (buttonVerify.isEnabled())?"Verify button is enabled":"Verify button is disabled","Verify Button should be enabled","Verify Button Validation");

		return this;

	}

	public DewPendingPaymentsPage isFailEnable()
	{	

		boolean tempResult=verifyTrue(buttonFail.isEnabled(),"Fail button is not enabled");

		publishResults(tempResult, (buttonFail.isEnabled())?"Fail button is enabled":"Fail button is disabled","Fail Button should be enabled","Fail Button Validation");

		return this;

	}

	public DewPendingPaymentsPage isAutoFetchSelected()
	{	

		boolean tempResult=verifyFalse(autoFetchCeckBox.isSelected(),"Autofecth Check Box button is selected");

		if (tempResult == false)
		{
			publishResults(tempResult, "Autofecth Check Box is not selected", "Autofecth Check Box  should not be selected", "Autofecth Check Box Validation");
		}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(tempResult, "Autofecth Check Box is selected", "Autofecth Check Box  should not be selected", "Autofecth Check Box Validation");
		}

		return this;

	}



	public DewPendingPaymentsPage isPassDisable()
	{	

		boolean tempResult=verifyFalse(buttonPass.isEnabled(),"Pass button is enabled");

		if (tempResult == false)
		{

			publishResults(tempResult, "Pass button is disabled", "Pass button should be disabled", "Pass button Validation");

		}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(tempResult, "Pass button is enabled", "Pass button should be disabled", "Pass button Validation");

		}


		return this;

	}

	public DewPendingPaymentsPage isVerifyDisable()
	{	

		boolean tempResult=verifyFalse(buttonVerify.isEnabled(),"Verify button is enabled");

		if (tempResult == false)
		{

			publishResults(tempResult, "Verify button is disabled", "Verify button should be disabled", "Verify button Validation");

		}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(tempResult, "Verify button is enabled", "Verify button should be disabled", "Verify button Validation");

		}

		return this;

	}

	public DewPendingPaymentsPage isFailDisable()
	{	

		boolean tempResult=verifyFalse(buttonFail.isEnabled(),"Fail button is enabled");

		if (tempResult == false)
		{

			publishResults(tempResult, "Fail button is disabled", "Fail button should be disabled", "Fail button Validation");

		}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(tempResult, "Fail button is enabled", "Fail button should be disabled", "Fail button Validation");

		}

		return this;

	}

	public DewPendingPaymentsPage isNextDisable()
	{	

		boolean tempResult=verifyFalse(buttonNext.isEnabled(),"Next button is enabled");

		if (tempResult == false)
		{

			publishResults(tempResult, "Next button is disabled", "Next button should be disabled", "Next button Validation");

		}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(tempResult, "Next button is enabled", "Next button should be disabled", "Next button Validation");

		}

		return this;

	}

	public DewPendingPaymentsPage isNextEnable()
	{	

		boolean tempResult=verifyTrue(buttonNext.isEnabled(),"Next button is not enabled");

		if (tempResult == true)
		{

			publishResults(tempResult, "Next button is enabled", "Next button should be enabled", "Next button Validation");

		}
		else
		{
			
			publishResults(tempResult, "Next button is disabled", "Next button should be enabled", "Next button Validation");

		}

		return this;

	}

	
	
	public DewPendingPaymentsPage isReferralNotificationDisable()
	{	

		boolean tempResult=verifyFalse(buttonReferalNotification.isEnabled(),"Referal Notification button is enabled");

		if (tempResult == false)
		{

			publishResults(tempResult, "Referal Notification button is disabled", "Referal Notification button should be disabled", "Referal Notification button Validation");

		}
		else
		{
			finalTestScriptResultFailFlag = true;
			publishResults(tempResult, "Referal Notification button is enabled", "Referal Notification button should be disabled", "Referal Notification button Validation");

		}

		return this;

	}

	public DewPendingPaymentsPage capturingUniqueIdentfier1()
	{
		uniqueId1 = pendingPaymentUniqueIdentifier.getText();
		
		System.out.println(uniqueId1);
		return this;
	}

	public DewPendingPaymentsPage capturingUniqueIdentfier2()
	{
		uniqueId2 = pendingPaymentUniqueIdentifier.getText();
		return this;
	}



	public DewPendingPaymentsPage valuetextTeamName() {
		fillTextBox(textTeamName, ICSPropertiesConfig.getDewPPTeamName());
		return this;
	}

	public DewPendingPaymentsPage valuetextEmail() {
		fillTextBox(textLocalEmail, ICSPropertiesConfig.getDewPPEmail());
		return this;
	}

	public DewPendingPaymentsPage valuetextFaxNumber() {
		fillTextBox(textFaxNumber, ICSPropertiesConfig.getDewPPFaxNumber());
		return this;
	}

	public DewPendingPaymentsPage valuetextPhoneNumber() {
		fillTextBox(textPhoneNumber, ICSPropertiesConfig.getDewPPPhoneNumber());
		return this;
	}

	public DewPendingPaymentsPage clickConfirmContact() {	
		actionMouseClickEvent(checkBoxConfirmContact);		
		pause(20000);	
		return this;	
	}

	public DewPendingPaymentsPage clickVerify() {	
		actionMouseClickEvent(buttonVerify);		
		pause(20000);	
		return this;	
	}

	public DewPendingPaymentsPage clickNext() {	
		actionMouseClickEvent(buttonNext);		
		pause(20000);	
		return this;	
	}


	public DewPendingPaymentsPage validateUniqueIdentifier()
	{

		boolean tempResult = verifyEquals(pendingPaymentSystemDefaultDecision.getText(), "Not Applicable", "System Default Decision is not matching with the database value");
		publishResults(tempResult,pendingPaymentSystemDefaultDecision.getText(), "Not Applicable", "Default Decision Validation"  );

		return this;
	}

	public DewPendingPaymentsPage clickAutoFetch() {	
		actionMouseClickEvent(autoFetchCeckBox);			
		return this;	
	}

	public DewPendingPaymentsPage clickStartProcessing() {
		actionMouseClickEvent(btnStartProcessing);
		pause(20000);
		return this;
	}

	public DewPendingPaymentsPage clickbtnPass() {	
		actionMouseClickEvent(buttonPass);		
		pause(20000);
		return this;

	}

	public DewPendingPaymentsPage clickbtnTakeOwnership() {	
		actionMouseClickEvent(btnTakeOwnership);		
		pause(20000);
		return this;

	}



	public static String getUniqueIDText()
	{
		return pendingPaymentUniqueIdentifier.getText();
	}

	public DewPendingPaymentsPage validateItemOwnership(){


		String userName = null;
		String userSeq = null;

		String debitinWorkstreamSeq = null;
		String expectedValueUserName = ICSPropertiesConfig.getDewitemOwnerName();
		String expectedValueUserseq =   ICSPropertiesConfig.getDewItemOwnerSeq()    ;
		try{
			ResultSet rs1 = DewDbQueries.itemOwnershipDetails();

			while(rs1.next())	
			{
				userName=rs1.getString(1);
				debitinWorkstreamSeq= rs1.getString(2);
				
			}

			boolean tempResult= verifyEquals(userName,expectedValueUserName , "Ownership is not with the correct User ");

			publishResults(tempResult,userName, expectedValueUserName, "Ownership Validation"  );

			clickSearchItemsMenu();
			pause(10000);

			ResultSet rs2 = DewDbQueries.itemOwnershipDetailsAfterNavigation(debitinWorkstreamSeq);


			while(rs2.next())	
			{
				userSeq=rs2.getString(1);

				System.out.println(userSeq);
			}

			
			boolean tempResult1= verifyNotEquals(userSeq,expectedValueUserseq, "Ownership is still with the same user ");

			publishResults(tempResult1,userSeq, "User seq should not be " +expectedValueUserseq , "Release Ownership Validation"  );


		}


		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;

	}


	public DewPendingPaymentsPage takeOwnershipOfItem() throws InterruptedException {

		if (btnTakeOwnership.isEnabled())
		{

			clickbtnTakeOwnership();

		}
		else
		{
			valuetextComments();
			clickVerify();

		}

		return this;	

	}


	public DewPendingPaymentsPage validateItemOwnershipAfterPreliminaryDecision(){


		String userName = null;
		String userSeq = null;
		String debitinWorkstreamSeq = null;
		
		String expectedValueUserName =  ICSPropertiesConfig.getDewitemOwnerName()  ;
		String expectedValueUserseq =   ICSPropertiesConfig.getDewItemOwnerSeq()    ;
		
		try{
			ResultSet rs1 = DewDbQueries.itemOwnershipDetails();

			while(rs1.next())	
			{
				userName=rs1.getString(1);
				debitinWorkstreamSeq= rs1.getString(2);
			}

			boolean tempResult= verifyEquals(userName,expectedValueUserName , "Ownership is not with the correct User ");

			publishResults(tempResult,userName, expectedValueUserName, "Ownership Validation"  );

			clickSearchItemsMenu();

			ResultSet rs2 = DewDbQueries.itemOwnershipDetailsAfterNavigation(debitinWorkstreamSeq);


			while(rs2.next())	
			{
				userSeq=rs2.getString(1);

			}

			boolean tempResult1= verifyEquals(userSeq.trim(),expectedValueUserseq.trim(), "Ownership is changed ");

			publishResults(tempResult1,"User sequence for an item " + userSeq, "User sequence should be " + expectedValueUserseq, "Release Ownership Validation"  );


		}


		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;

	}

	public DewPendingPaymentsPage validateItemOwnershipAfterDecisionClickNext(){


		String userName = null;
		String userSeq = null;

		String debitinWorkstreamSeq = null;
		String expectedValueUserName =  ICSPropertiesConfig.getDewitemOwnerName()  ;
		
		try{
			ResultSet rs1 = DewDbQueries.itemOwnershipDetails();

			while(rs1.next())	
			{
				userName=rs1.getString(1);
				debitinWorkstreamSeq= rs1.getString(2);
			}

			boolean tempResult= verifyEquals(userName,expectedValueUserName , "Ownership is not with the correct User ");

			publishResults(tempResult,userName, expectedValueUserName, "Ownership Validation"  );

			valuetextComments();
			clickbtnPass();
			clickNext();


			ResultSet rs2 = DewDbQueries.itemOwnershipDetailsAfterNavigation(debitinWorkstreamSeq);


			while(rs2.next())	
			{
				userSeq=rs2.getString(1);

			}

			boolean tempResult1= verifyEquals(userSeq,null, "Ownership is with some user");

			publishResults(tempResult1,userSeq, null, "Release Ownership Validation"  );


		}


		catch(Exception e)
		{
			e.printStackTrace();
		}
		return this;

	}


	public DewSearchItemsPage switchDriverToSearchItemPage() {	
		
		return new DewSearchItemsPage(driver);		
	}	



	public DewPendingPaymentsPage validateItemDecisionInfo(){


		String accountNumber = null;
		String sortcode = null;
		String serialNumber = null;
		String amount = null;
		String userFailReason = null;
		String systemDefaultDec = null;
		String representItemInd = null;
		String switchedAccNumber = null;
		String debitId = null;
		String collectingPid = null;
		String channelRiskType = null;
		String customerSegment = null;
		try{



			ResultSet rs1 = DewDbQueries.itemDetails();

			while(rs1.next())	
			{


				accountNumber=rs1.getString(2);
				sortcode=rs1.getString(3);
				serialNumber=rs1.getString(4);
				amount=rs1.getString(5);
				userFailReason=rs1.getString(6);
				systemDefaultDec=rs1.getString(7);
				representItemInd=rs1.getString(8);
				switchedAccNumber=rs1.getString(9);
				debitId=rs1.getString(10);
				collectingPid=rs1.getString(11);
				channelRiskType=rs1.getString(12);
				customerSegment=rs1.getString(13);
			}

			boolean tempResult=false;



			validationErrorInformation("Test Case 54623");

			tempResult = verifyTrue(pendingPaymentSortCode.getText().equals(sortcode), "Sort Code is not matching with the database value" );



			publishResults(tempResult,pendingPaymentSortCode.getText(), sortcode, "SortCode Validation"  );
			tempResult = verifyTrue(pendingPaymentSerial.getText().equals(serialNumber), "Serial Number is not matching with the database value" );
			publishResults(tempResult,pendingPaymentSerial.getText(), serialNumber, "serial number Validation"  );

			tempResult = verifyTrue(pendingPaymentAmount.getText().equals(amount), "Amount is not matching with the database value" );
			publishResults(tempResult,pendingPaymentAmount.getText(), amount, "Amount Validation"  );
			if (userFailReason == null)
			{
				tempResult= verifyEquals(pendingPaymentFailReason.getText(), "Not Applicable", "User Fail Reason is not matching with the database value");
				publishResults(tempResult,pendingPaymentFailReason.getText(), "Not Applicable", "Fail Reason Validation"  );
			}
			else
			{
				tempResult = verifyTrue(pendingPaymentFailReason.getText().equals(userFailReason), "Fail Reason is not matching with the database value" );
				publishResults(tempResult,pendingPaymentFailReason.getText(), "userFailReason", "Fail Reason Validation"  );

			}

			if (systemDefaultDec == null)
			{
				tempResult = verifyEquals(pendingPaymentSystemDefaultDecision.getText(), "Not Applicable", "System Default Decision is not matching with the database value");
				publishResults(tempResult,pendingPaymentSystemDefaultDecision.getText(), "Not Applicable", "Default Decision Validation"  );

			}
			else
			{
				tempResult = verifyTrue(pendingPaymentSystemDefaultDecision.getText().equals(systemDefaultDec), "System Default Decision is not matching with the database value" );
				publishResults(tempResult,pendingPaymentSystemDefaultDecision.getText(), systemDefaultDec, "Default Decision Validation"  );


			}

			if (representItemInd.equals("0"))
			{
				tempResult = verifyEquals(pendingPaymentRepresented.getText(), "No", "Represent Item Ind is not matching with the database value");
				publishResults(tempResult,pendingPaymentRepresented.getText(), "No", "Represent  Validation"  );


			}
			else
			{
				tempResult = verifyTrue(pendingPaymentRepresented.getText().equals(representItemInd), "Represent Item Ind is not matching with the database value" );
				publishResults(tempResult,pendingPaymentRepresented.getText(), representItemInd, "Represent  Validation"  );

			}

			if (switchedAccNumber == null)
			{
				tempResult = verifyEquals(pendingPaymentSwitchedAccount.getText(), "N", "Switched Account Number  is not matching with the database value");
				publishResults(tempResult,pendingPaymentSwitchedAccount.getText(), "N", "Switched Account Number Validation"  );


			}
			else
			{
				tempResult = verifyTrue(pendingPaymentSwitchedAccount.getText().equals(switchedAccNumber), "Switched Account Number is not matching with the database value" );
				publishResults(tempResult,pendingPaymentSwitchedAccount.getText(), switchedAccNumber, "Switched Account Number Validation" );

			}

			tempResult = verifyTrue(getUniqueIDText().equals(debitId), "DebitID is not matching with the database value" );
			publishResults(tempResult,getUniqueIDText(), debitId, "Unique Id Validation" );

			tempResult = verifyTrue(pendingPaymentCBSortCode.getText().equals(collectingPid), "Collecting bank Sort Code is not matching with the database value" );
			publishResults(tempResult,pendingPaymentCBSortCode.getText(), collectingPid, "Collecting Id Validation"  );


			tempResult = verifyTrue(pendingPaymentChannelMethod.getText().equals(channelRiskType), "Channel Method is not matching with the database value" );
			publishResults(tempResult,pendingPaymentChannelMethod.getText(), channelRiskType, "Channel Method Validation" );


			tempResult = verifyTrue(pendingPaymentCustomerSegment.getText().equals(customerSegment), "Customer Segment is not matching with the database value" );
			publishResults(tempResult,pendingPaymentCustomerSegment.getText(), customerSegment, "Customer Segment Validation");

			tempResult = verifyTrue(pendingPaymentButtonHistory.isEnabled(), "History Button is not enabled");

			if(tempResult ==false)
			{
				publishResults(tempResult,"NotEnabled", "Should be Enabled", "Switched Account Number Validation"  );
			}
			else 
			{
				publishResults(tempResult,"Enabled", "Should be Enabled", "Switched Account Number Validation"  );	
			}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

		return this;
	}





}


