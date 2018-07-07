/*  
<Copyright file="SearchItemPage.java" company="iPSL">
Copyright © iPSL 2017 All rights are reserved.
Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
is prohibited without the prior written permission of the copyright owner.
</copyright> 
*/
 

/* ************************* Module Header ******************************
 * Module Name : Handles methods related to IA Main Search Page     
 * Date : 18/06/2017
 * Created By : Sandeep Bibinagar
 * Description : handling methods related to IA Main Search Page 
/* ***************** AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 30/07/2017		
*********************************************************************** 
Description : Updating file as more work flows are added to the regression suite
***********************************************************************
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 26/08/2017		
 Description : Updating file as more work flows are added to the regression suite
 
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
 Description : Updating class as per java coding standards
 ********************************************************************** */

package com.ics.ia.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;

/********************************************************************************************************/
/* Purpose:	 This is the class for handling methods related to IA Search Page                           */
/********************************************************************************************************/

public class SearchItemPage extends ICSPageUtilis {		
	protected static boolean flag=false;
	public static boolean tempResult;
	
	public SearchItemPage(WebDriver driver) 
	{
		super(driver);
	}
	
	public SearchItemPage(WebDriver driver, String url) 
	{
		super(driver, url);
	}
	
	@FindBy(id = "nav-title")
	private static WebElement itemSearchHeaderTitle;	
	@FindBy(id = "ErrorProcessingDate")
	private static WebElement dateFormatErrorMessage;	
	@FindBy(id = "ErrorProcessingEndDate")
	private static WebElement dateFormatEndErrorMessage;
	@FindBy(id = "ClearingDin")
	private static WebElement textboxCaptureDIN;
	@FindBy(id = "IBDEBranchISN")
	private static WebElement textboxCaptureISN;
	@FindBy(id = "WorkStream")
	private static WebElement selectWorkStream;
	@FindBy(css = "ProcessingDate")
	private static WebElement textboxProcDateCSS;
	@FindBy(id = "ProcessingDate")
	private static WebElement textboxProcDate;
	@FindBy(xpath = "//input[@id='ProcessingDate']")
	private static WebElement textboxFocusProcDate;
	@FindBy(id = "ProcessingDateEnd")
	private static WebElement textboxProcDateEnd;
	@FindBy(id = "PostingDate")
	private static WebElement textboxPostingDate;
	@FindBy(id = "PostingDateEnd")
	private static WebElement textboxPostingDateEnd;
	@FindBy(id = "SortCode")
	private static WebElement textboxSC;
	@FindBy(id = "Account")
	private static WebElement textboxAccount;
	@FindBy(id = "TranCode")
	private static WebElement textboxTranCode;
	@FindBy(id = "SerialReference")
	private static WebElement textboxSerialReference;
	@FindBy(id = "SerialEnd")
	private static WebElement textboxSerialEnd;
	@FindBy(id = "Amount")
	private static WebElement textboxAmount;
	@FindBy(id = "AmountEnd")
	private static WebElement textboxAmountEnd;
	@FindBy(id = "Gnd")
	private static WebElement dropdownGnd;
	@FindBy(id = "BeneficiaryParticipant")
	private static WebElement textboxBenPID;	
	@FindBy(id = "ClearingItemReference")
	private static WebElement textboxClearingItemReference;
	@FindBy(id = "CollectingParticipant")
	private static WebElement textboxColPID;
	@FindBy(id = "UniqueItemIdentifier")
	private static WebElement textboxUII;
	@FindBy(id = "QueryTypesImageClearing")
	private static WebElement radioButtonImageClearingOnly;
	@FindBy(id = "QueryTypesPaperClearing")
	private static WebElement radioButtonPaperClearingOnly;
	@FindBy(id = "QueryTypesBoth")
	private static WebElement radioButtonQueryTypesBoth;
	@FindBy(id = "btnSubmit")
	private static WebElement buttonItemSearch;
	
	@FindBy(id = "dtMainQuery2_grid_info")
	private static WebElement searchResultsInfoUTSI;
	
	@FindBy(id = "dtMainQuery_grid_info")
	private static WebElement searchResultsInfo;
	@FindBy(id = "dtMainQuery_grid_paginate")
	private static WebElement paginationGrid;
	@FindBy(xpath  = "//div[@id='dtMainQuery_grid_paginate']/ul/li[4]/a")
	private static WebElement NextPaginationButton;	
	@FindBy(xpath  = "//li[@id='dtMainQuery_grid_previous']/a")
	private static WebElement PreviousPaginationButton;	
	@FindBy(xpath  = "//ul[@class='pagination']/li[6]/a")
	private static WebElement FourthPageButton;	
	@FindBy(xpath  = "//div[@id='dtMainQuery']/div[@id='dtMainQuery_grid_wrapper']//div[@class='dataTables_scroll']//div[@class='dataTables_scrollBody']/table[@id='dtMainQuery_grid']/tbody/tr[1]/td[1]/div/input[@class='editor-active outbox']")
	private static WebElement OutboxcheckBox;
	//@FindBy(xpath  = "//div[@id='dtMainQuery']/div[@id='dtMainQuery_grid_wrapper']//div[@class='dataTables_scroll']//div[@class='dataTables_scrollBody']/table[@id='dtMainQuery_grid']/tbody/tr[1]/td[1]/div/input[@class='editor-active']")
	@FindBy(xpath  = "//input[@class='editor-active']")
	private static WebElement OutboxcheckBoxOutboxPage;
	@FindBy(id = "btnAddToOutbox_MainQuery")
	private static WebElement AddToOutboxButton;
	@FindBy(xpath = "//th[@class='obd-currency sorting']")
	private static WebElement CurrencyColumn;
	@FindBy(xpath = "//th[@class='obd-collectingparticipantidentifier sorting']")
	private static WebElement CollectingPIDColumnName;
	@FindBy(xpath = "//th[@class='obd-narrative sorting']")
	private static WebElement NarrativeColumnName;
	@FindBy(xpath = "//th[@class='obd-account sorting']")
	private static WebElement AccountColumnName;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[1]")
	private static WebElement Record1;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[5]")
	private static WebElement Record5;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[8]")
	private static WebElement Record8;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[12]")
	private static WebElement Record12;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[15]")
	private static WebElement Record15;
	@FindBy(xpath = "//table[@id='dtMainQuery_grid']/tbody/tr[20]")
	private static WebElement Record20;
	@FindBy(xpath = "//div[@id='imageViewer']/div[2]/div[1]/div[1]/div[@id='imageViewerFront']//div[@class='viewer-container']/div[@class='viewer-canvas']/img")
	private static WebElement FrontImage;
	@FindBy(xpath = "//div[@id='imageViewer']/div[2]/div[2]/div/div[@id='imageViewerRear']//div[@class='viewer-container']/div[@class='viewer-canvas']/img")
	private static WebElement RearImage;
	@FindBy(xpath = "//div[@class='no-preview']/img")
	private static WebElement NoImage;
	
	@FindBy(xpath  = "//div[@class='modal-footer']/button[@class='btn btn-primary']")
	private static WebElement PopUpOk;
	
	@FindBy(xpath  = "//ul[@class='site-menu']//li[4]/a/span")
	private static WebElement OutboxNoOfItems;
	
	
	@FindBy(xpath  = "//ul[@class='site-menu']//li[4]/a")
	private static WebElement OutBoxMenu;	
	@FindBy(xpath  = "//ul[@class='site-menu']//li[4]")
	private static WebElement OutBoxMenuDisabled;	
	@FindBy(xpath  = "//li[@class='paginate_button active']")
	private static WebElement activePaginationButton;	
	@FindBy(xpath  = "//li[@class='paginate_button active']/a")
	private static WebElement activePaginationNthButton;	
	@FindBy(id = "ErrorSerialReference")
	private static WebElement serialreferenceError;
	@FindBy(xpath  = "//table[@id='dtMainQuery_grid']//tbody/tr/td[8]")
	private static WebElement firstRowSortCode;
	@FindBy(xpath  = "//table[@id='dtMainQuery_grid']//tbody/tr/td[18]")
	private static WebElement firstRowEntityStateDescription;
	@FindBy(xpath  = "//*[@class='fa fa-lg fa-fw fa-bars'][1]")
	private static WebElement SubQueryLink;
	@FindBy(xpath  = "//div[@class='dataTables_scrollBody']//tbody/tr/td[3]")
	private static WebElement getRowSortCodeFromOutBox;
	@FindBy(xpath  = "//div[@class='bootbox-body']/div[2]/div[6]/a")
	private static WebElement itemUpdateHistorySubQuery;
	@FindBy(xpath  = "//div[@class='bootbox-body']/div[2]/div[5]/a")
	private static WebElement itemStateHistorySubQuery;
	@FindBy(xpath  = "//div[@class='bootbox-body']/div[2]/div[2]/a")
	private static WebElement DebitFraudItemsSubQuery;
	@FindBy(xpath  = "//div[@class='bootbox-body']/div[2]/div[2]/a")
	private static WebElement CreditFraudItemsSubQuery;
	@FindBy(xpath  = "//div[@class='bootbox-body']/div[2]/div[8]/a")
	private static WebElement TsetContentsSubQuery;
	@FindBy(xpath  = "//div[@class='bootbox-body']/div[2]/div[4]/a")
	private static WebElement ItemCaptureHistorySubQuery;
	@FindBy(xpath  = "//ul[@class='nav navbar-toolbar']/li[3]/a")
	private static WebElement NavigationToolBarTitle;
	@FindBy(xpath  = "//div[@id='nav-title']")
	private static WebElement NavigationTitle;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[4]")
	private static WebElement ProcessingDateColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[7]")
	private static WebElement SerialReferenceColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[8]")
	private static WebElement SortCodeColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[9]")
	private static WebElement AccountColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[12]")
	private static WebElement AmountColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[11]")
	private static WebElement TransactionCodeColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[28]")
	private static WebElement DeletedItemIndicatorColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[40]")
	private static WebElement ClearingDinColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[41]")
	private static WebElement BranchIBDEDINColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[43]")
	private static WebElement PassNumberColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[44]")
	private static WebElement RecordTypeColumn;
	@FindBy(xpath  = "//div[@class='dataTables_scrollHead']//table/thead/tr/th[45]")
	private static WebElement ImageIndicatorColumn;
	@FindBy(xpath  = "//span[@id='spanUserName']")
	private static WebElement UserNameLink;
	@FindBy(xpath  = "//ul[@class='dropdown-menu']/li/a")
	private static WebElement LogoutLink;
	@FindBy(xpath  = "//ul[@class='site-menu']/li[5]/a")
	private static WebElement PreviousQueryMenu;
	@FindBy(xpath  = "//ul[@class='site-menu']/li[5]")
	private static WebElement PreviousQueryMenuDisabled;
	
	@FindBy(xpath  = "//div[@id='dtMainQuery']/table/tbody/tr[1]/td/div/div/a")					
	private static WebElement PreviousQueryMenuFirstItem;
	@FindBy(xpath  = "//select[@id='ItemType']")
	private static WebElement dropdownItemType;	
	@FindBy(id = "ItemStateValue")
	private static WebElement dropdownState;	
	@FindBy(id = "ChannelIdentifier")
	private static WebElement dropdownChannel;	
	@FindBy(id = "ItemType")
	private static WebElement textboxClgItemRef;	
	@FindBy(id = "PayingParticipant")
	private static WebElement textboxPayPID;	
	@FindBy(id = "CollectingBranchLocation")
	private static WebElement textboxColBchLoc;	
	@FindBy(id = "CollectingLocation")
	private static WebElement textboxColLoc;		
	@FindBy(id = "IBDEBranchISN")
	private static WebElement textboxBranchOrIBDEISN;
	@FindBy(xpath = "")
	private static WebElement textboxDisabledClearingDIN;
	@FindBy(id = "ClearingDin")
	private static WebElement textboxClearingDIN;
	@FindBy(id = "TransactionSetIdentifier")
	private static WebElement textboxUniqueTransactionSetItemIdentifier;
	@FindBy(id = "TransactionSetIdentifier")
	private static WebElement textboxTransactionSetIdentifier;
	@FindBy(id = "ItemStateValue")
	private static WebElement dropDownItemStateValue;
	@FindBy(xpath = "//select[@id='ItemStateValue']")
	private static WebElement dropDownItemType;
	@FindBy(id = "MessageType")
	private static WebElement dropDownMessageType;
	@FindBy(id = "PayingParticipant")
	private static WebElement textboxPayingParticipantIdentifier;
	@FindBy(id = "BeneficiaryParticipant")
	private static WebElement textboxBeneficiaryParticipantIdentifier;
	@FindBy(id = "CollectingParticipant")
	private static WebElement textboxCollectingParticipantIdentifier;
	@FindBy(id = "ChannelIdentifier")
	private static WebElement dropDownChannelIdentifier;
	@FindBy(id = "CollectingLocation")
	private static WebElement textboxCollectionLocation;
	@FindBy(id = "CollectingBranchLocation")
	private static WebElement textboxCollectingBranchLocation;
	@FindBy(id = "Source")
	private static WebElement textboxSource;
	@FindBy(id = "dtpSiFromDatePicker")
	private  WebElement FromDate;
	@FindBy(id = "dtpSiToDatePicker")
	private static WebElement ToDate;
	@FindBy(id = "btnSubmit")
	private static WebElement buttonSearch;
	@FindBy(id = "btnBack")
	private static WebElement buttonBack;
	@FindBy(xpath = "//button[@id='btnBack']/span")
	private static WebElement buttonBackSubQuery;
	@FindBy(xpath  = "//div[@id='nav-title']")
	private static WebElement TitleItemSearchQuery;
	@FindBy(id  = "btnRemove")
	private static WebElement Remove;
	
	
	//WebElement for Labels in SearchItem Page
	@FindBy(xpath  = "//div[@id='dvShared']/div/div[2]/div[1]/label")
	private static WebElement LabelProcDate;
	@FindBy(xpath  = "//div[@id='dvShared']/div/div[2]/div[2]/label")
	private static WebElement LabelProcDateEnd;
	@FindBy(xpath  = "//div[@id='dvShared']/div/div[2]/div[3]/label")
	private static WebElement LabelSc;
	@FindBy(xpath  = "//div[@id='dvShared']/div/div[2]/div[4]/label")
	private static WebElement LabelAccount;
	@FindBy(xpath  = "//div[@id='dvShared']/div/div[2]/div[5]/label")
	private static WebElement LabelSerialReference;
	@FindBy(xpath  = "//div[@id='dvShared']/div/div[2]/div[6]/label")
	private static WebElement LabelSerialEnd;
	@FindBy(xpath  = "//div[@id='dvShared']/div/div[2]/div[7]/label")
	private static WebElement LabelAmount;
	@FindBy(xpath  = "//div[@id='dvShared']/div/div[2]/div[8]/label")
	private static WebElement LabelAmountEnd;
	@FindBy(xpath  = "//div[@id='dvShared']/div/div[2]/div[9]/label")
	private static WebElement LabelGnd;
	@FindBy(xpath  = "//div[@id='dvImageClearing']/div/div[2]/div[1]/label")
	private static WebElement LabelUII;
	@FindBy(xpath  = "//div[@id='dvImageClearing']/div/div[2]/div[2]/label")
	private static WebElement LabelPaid;
	@FindBy(xpath  = "//div[@id='dvImageClearing']/div/div[2]/div[3]/label")
	private static WebElement LabelColLoc;
	@FindBy(xpath  = "//div[@id='dvImageClearing']/div/div[2]/div[4]/label")
	private static WebElement LabelColBchLoc;
	@FindBy(xpath  = "//div[@id='dvImageClearing']/div/div[2]/div[5]/label")
	private static WebElement LabelColPid;
	@FindBy(xpath  = "//div[@id='dvImageClearing']/div/div[2]/div[6]/label")
	private static WebElement LabelPayPid;
	@FindBy(xpath  = "//div[@id='dvImageClearing']/div/div[2]/div[7]/label")
	private static WebElement LabelBenPid;
	@FindBy(xpath  = "//div[@id='dvImageClearing']/div/div[2]/div[8]/label")
	private static WebElement LabelClgItemRef;
	@FindBy(xpath  = "//div[@id='dvImageClearing']/div/div[2]/div[9]/label")
	private static WebElement LabelChannel;
	@FindBy(xpath  = "//div[@id='dvImageClearing']/div/div[2]/div[10]/label")
	private static WebElement LabelState;
	@FindBy(xpath  = "//div[@id='dvPaperClearing']/div/div[2]/div[1]/label")
	private static WebElement LabelCaptureISN;
	@FindBy(xpath  = "//div[@id='dvPaperClearing']/div/div[2]/div[2]/label")
	private static WebElement LabelCaptureISNEnd;
	@FindBy(xpath  = "//div[@id='dvPaperClearing']/div/div[2]/div[3]/label")
	private static WebElement LabelBranch_IBDEISN;
	@FindBy(xpath  = "//div[@id='dvPaperClearing']/div/div[2]/div[4]/label")
	private static WebElement LabelWorkStream;
	@FindBy(xpath  = "//input[@id='QueryTypesImageClearing']")
	private static WebElement RadioImageClearingOnly;
	@FindBy(xpath  = "//input[@id='UniqueItemIdentifier']")
	private static WebElement TextUII;
	@FindBy(xpath  = "//button[@id='btnSubmit']")
	private static WebElement ButtonSearch;
	@FindBy(id  = "validationErrors")
	private static WebElement TextValidationErrors;
	@FindBy(xpath  = "//ul[@class='site-menu']/li[2]/a")
	private static WebElement ItemSearchQueryMenu;
	@FindBy(xpath  = "//ul[@class='site-menu']/li[3]/a")
	private static WebElement RepresentQueryMenu;
	@FindBy(xpath  = "//span[@id='spanUserName']")
	private static WebElement LogoutUserName;
	@FindBy(xpath  = "//ul[@class='dropdown-menu']/li/a")
	private static WebElement LogoutIcon;
	@FindBy(id  = "ErrorClearingDin")
	private static WebElement ClearingDinErrorText;
	@FindBy(id  = "ErrorProcessingDate")
	private static WebElement ErrorProcessingDate;
	@FindBy(id  = "ErrorProcessingDateEnd")
	private static WebElement ErrorProcessingDateEnd;
	@FindBy(id  = "ErrorPostingDate")
	private static WebElement ErrorPostingDate;
	@FindBy(id  = "ErrorPostingDateEnd")
	private static WebElement ErrorPostingDateEnd;
	@FindBy(id  = "ErrorSortCode")
	private static WebElement ErrorSortCode;
	@FindBy(id  = "ErrorAccount")
	private static WebElement ErrorAccount;
	@FindBy(id  = "ErrorTranCode")
	private static WebElement ErrorTranCode;
	@FindBy(id  = "ErrorAmount")
	private static WebElement ErrorAmount;
	@FindBy(id  = "ErrorAmountEnd")
	private static WebElement ErrorAmountEnd;
	@FindBy(id  = "ErrorUniqueItemIdentifier")
	private static WebElement ErrorUniqueItemIdentifier;
	@FindBy(id  = "ErrorTransactionSetIdentifier")
	private static WebElement ErrorTransactionSetIdentifier;
	@FindBy(id  = "ErrorPayingParticipant")
	private static WebElement ErrorPayingParticipant;
	@FindBy(id  = "ErrorBeneficiaryParticipant")
	private static WebElement ErrorBeneficiaryParticipant;
	@FindBy(id  = "ErrorCollectingParticipant")
	private static WebElement ErrorCollectingParticipant;
	@FindBy(id  = "ErrorCollectingLocation")
	private static WebElement ErrorCollectingLocation;
	@FindBy(id = "btnReset")
	private static WebElement ButtonReset;
	
	//Description : This method validates presence of Username to logout
	public WebElement VerifyLogoutUsername() {	
		return (LogoutUserName);		
	}
	
	//Description : This method performs a click on Logout
	public SearchItemPage ClickLogoutUsername() {	
		clickElement(VerifyLogoutUsername());
		return this;
	}
	
	//Description : This method validates presence of Logout icon
	public WebElement VerifyLogouticon() {	
		return (LogoutIcon);		
	}
	
	//Description : This method validates logout functionality
	public SearchItemPage ClickLogouticon() {	
		clickElement(VerifyLogouticon());
		pause(100000);
		return this;
	}
	
	
	//Description : This method handles window based alerts
	public SearchItemPage WindowsAlertPouUp(String AlertAction)
	{   pause(100000);
		WindowsAlertPopUp(AlertAction);
		pause(100000);
		return this;	
	}
	
	//Description : This method validates Item Search Title on the Search Page
	public WebElement VerifyItemSearchTitle() {	
		return (TitleItemSearchQuery);		
	}
	
	
	//Description : This method validates login scenario
	public SearchItemPage validateLoginSuccessful() {	
		
		if(isElementDisplayed(VerifyItemSearchTitle()))
		{
			flag=true;
			publishResults(flag,"Login Successful", "Login should be successful", "Validate if Login is Sucessful or not");
			}
		else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag,"Login - NOT Successful", "Login should be successful", "Verify Login is Sucessful");
		}
		
		return this;
	}
	
	
	//Description : This method performs a click on Image clearing section
	public SearchItemPage TextUII() {	
		fillTextBox(TextUII,ICSPropertiesConfig.TexboxIncorrectInputUII());
		return this;
	}
	
	//Description : This method performs a click on Submit button
	public SearchResultsPage clickSubmitButton() {			
		clickElement(buttonItemSearch);
		pause(10000);
		return new SearchResultsPage(driver);	
	}
	
	//Description : This method performs a click on Image clearing section
	public SearchItemPage clickImageClearingOnly() {	
		pause(1000);
		JavascriptExecutor exec=(JavascriptExecutor)driver;
		exec.executeScript("arguments[0].click();", radioButtonImageClearingOnly);
		pause(1000);
		return this;	
	}
	
	//Description : This method performs a click on paper clearing section
	public SearchItemPage clickPaperClearingOnly() {	
		pause(1000);
		JavascriptExecutor exec=(JavascriptExecutor)driver;
		exec.executeScript("arguments[0].click();", radioButtonPaperClearingOnly);
		pause(2000);
		return this;	
	}
	
	//Description : This method clicks shared section
	public SearchItemPage clickShared() {	
		pause(1000);
		JavascriptExecutor exec=(JavascriptExecutor)driver;
		exec.executeScript("arguments[0].click();", radioButtonQueryTypesBoth);
		pause(4000);
		return this;	
	}
	
	//Description : This method clears Processing date field
	public SearchItemPage clearProcDateField() {	
		textboxProcDate.clear();
		pause(1000);
		return this;	
	}
	
	//Description : This method performs UTSI field level validations
	public SearchItemPage validateUTSI() {	
		clickImageClearingOnly();
		fillTextBox(textboxUniqueTransactionSetItemIdentifier,ICSPropertiesConfig.TexboxIncorrectInputUTSI());
		pause(3000);
		tempResult=	verifyTrue(textboxUniqueTransactionSetItemIdentifier.getAttribute("value").equals(ICSPropertiesConfig.TexboxIncorrectInputUTSI())," 24 characters are NOT allowed for UTSI");
		publishResults(tempResult,"Max 24 characters are allowed for UTSI: "+(textboxUniqueTransactionSetItemIdentifier.getAttribute("value")).equals(ICSPropertiesConfig.TexboxIncorrectInputUTSI())+"","Max 24 characters should be allowed for UTSI","Validating if Maximum 24 character are allowed for UTSI");
		
		textboxUniqueTransactionSetItemIdentifier.clear();
		pause(4000);
		return this;
	}
	
	//Description : This method performs UII field level validations
	public SearchItemPage validateUII() 
	{
		clickImageClearingOnly();
		fillTextBox(textboxUII,ICSPropertiesConfig.TexboxIncorrectInputUII());
		assertFalse(textboxUII.getText().equals(ICSPropertiesConfig.TexboxIncorrectInputUII()),"Maximum 25 characters are allowed!!");
		fillTextBox(textboxUII,ICSPropertiesConfig.TexboxValidInputUII());
		assertTrue(textboxProcDate.isEnabled(),"Proc Date is disabled!!");
		assertTrue(textboxProcDateEnd.isEnabled(),"Proc Date End is disabled!!");
		assertTrue(textboxSC.isEnabled(),"SC is disabled!!");
		assertTrue(textboxAccount.isEnabled(),"Account is disabled!!");
		assertTrue(textboxSerialReference.isEnabled(),"Serial/Reference is disabled!!");
		assertFalse(textboxSerialEnd.isEnabled(),"Serial End is disabled!!");
		assertTrue(textboxAmount.isEnabled(),"Amount is disabled!!");
		assertTrue(textboxAmountEnd.isEnabled(),"Amount End is disabled!!");
		assertFalse(dropdownGnd.isEnabled(),"Gnd is disabled!!");
		assertFalse(dropdownItemType.isEnabled(),"ItemType is disabled");
		assertFalse(textboxColLoc.isEnabled(),"Col Loc is disabled");
		assertFalse(textboxColBchLoc.isEnabled(),"Col Bch Loc is disabled");
		assertFalse(textboxColPID.isEnabled(),"Col PID is disabled");
		assertFalse(textboxPayPID.isEnabled(),"Pay PID is disabled");
		assertFalse(textboxBenPID.isEnabled(),"Ben PID is disabled");
		assertFalse(textboxClgItemRef.isEnabled(),"Clg Item Ref is disabled");
		assertFalse(dropdownChannel.isEnabled(),"Channel is disabled");
		assertFalse(dropdownState.isEnabled(),"State is disabled");
		assertFalse(textboxCaptureDIN.isEnabled(),"Capture DIN is disabled");
		assertFalse(textboxCaptureISN.isEnabled(),"Capture ISN  is disabled");
		assertFalse(textboxBranchOrIBDEISN.isEnabled(),"Channel is disabled");
		assertFalse(selectWorkStream.isEnabled(),"WorkStream is disabled");
		tempResult=	verifyTrue(textboxUII.getAttribute("value").equals(ICSPropertiesConfig.TexboxValidInputUII())," 25 characters are NOT allowed for UII");
		publishResults(tempResult,"Max 25 characters are allowed for UII: "+(textboxUII.getAttribute("value")).equals(ICSPropertiesConfig.TexboxValidInputUII())+"","Max 25 characters should be allowed for UII","Validating if Maximum 25 character are allowed for UII");
		clickClearALL();
		return this;	
	}
	
	//Description : This method validates Shared fields
	public SearchItemPage validateSharedInputsFields() 
	{			
		tempResult=	verifyTrue(textboxProcDate.isEnabled(),"Proc Date is disabled!!");
		publishResults(tempResult,"Proc Date Enabled : "+textboxProcDate.isEnabled()+"","Proc Date should be enabled","Proc Date field Validation");
		tempResult=	verifyTrue(textboxProcDateEnd.isEnabled(),"Proc Date End is disabled!!");
		publishResults(tempResult,"Proc Date End Enabled : "+textboxProcDateEnd.isEnabled()+"","Proc Date End should be enabled","Proc Date End field Validation");
		tempResult=	verifyTrue(textboxPostingDate.isEnabled(),"Posting Date is disabled!!");
		publishResults(tempResult,"Proc Date Enabled : "+textboxPostingDate.isEnabled()+"","Posting Date should be enabled","Posting Date field Validation");
		tempResult=	verifyTrue(textboxPostingDateEnd.isEnabled(),"Posting Date End is disabled!!");
		publishResults(tempResult,"Posting Date End Enabled : "+textboxPostingDateEnd.isEnabled()+"","Posting Date End should be enabled","Posting Date End field Validation");
		tempResult=	verifyTrue(textboxSC.isEnabled(),"SC is disabled!!");
		publishResults(tempResult,"SC Enabled : "+textboxSC.isEnabled()+"","Sort Code should be enabled","Sort Code field Validation");
		tempResult=	verifyTrue(textboxAccount.isEnabled(),"Account is disabled!!");
		publishResults(tempResult,"Account Date Enabled : "+textboxAccount.isEnabled()+"","Account should be enabled","Account  field Validation");
		tempResult=	verifyTrue(textboxTranCode.isEnabled(),"TranCode is disabled!!");		
		publishResults(tempResult,"TranCode Enabled : "+textboxTranCode.isEnabled()+"","Tran Code should be enabled","Tran Code field Validation");
		tempResult=	verifyTrue(textboxSerialReference.isEnabled(),"Serial/Reference is disabled!!");
		publishResults(tempResult,"Serial/Reference Enabled : "+textboxSerialReference.isEnabled()+"","Serial/Reference should be enabled","Serial/Reference field Validation");
		tempResult=	verifyTrue(textboxAmount.isEnabled(),"Amount is disabled!!");
		publishResults(tempResult,"Amount Enabled : "+textboxAmount.isEnabled()+"","Amount should be enabled","Amount field Validation");
		tempResult=	verifyTrue(textboxAmountEnd.isEnabled(),"Amount End is disabled!!");
		publishResults(tempResult,"Amount End Enabled : "+textboxAmountEnd.isEnabled()+"","Amount End should be enabled","Amount End field Validation");
		tempResult=	verifyTrue(dropdownGnd.isEnabled(),"Gnd is disabled!!");
		publishResults(tempResult,"Gender Enabled : "+dropdownGnd.isEnabled()+"","Gender should be enabled","Gender field Validation");
		return this;	
	}

	// Description : This method validates Image clearing fields
	public SearchItemPage validateImageClearingInputsFields() {	
		tempResult=	verifyTrue(textboxUII.isEnabled(),"UniqueItemIdentifier Date is disabled!!");
		publishResults(tempResult,"UniqueItemIdentifier Date Enabled : "+textboxUII.isEnabled()+"","UniqueItemIdentifier Date should be enabled","UniqueItemIdentifier Date field Validation");
		tempResult=	verifyTrue(textboxTransactionSetIdentifier.isEnabled(),"TransactionSetIdentifier Date End is disabled!!");
		publishResults(tempResult,"TransactionSetIdentifier Date End Enabled : "+textboxTransactionSetIdentifier.isEnabled()+"","TransactionSetIdentifier Date End should be enabled","TransactionSetIdentifier Date End field Validation");
		tempResult=	verifyTrue(dropDownItemStateValue.isEnabled(),"ItemStateValue Date is disabled!!");
		publishResults(tempResult,"ItemStateValue Enabled : "+dropDownItemStateValue.isEnabled()+"","ItemStateValue should be enabled","ItemStateValue field Validation");
		tempResult=	verifyTrue(dropDownItemType.isEnabled(),"ItemType Date End is disabled!!");
		publishResults(tempResult,"ItemType Enabled : "+dropDownItemType.isEnabled()+"","ItemType should be enabled","ItemType field Validation");
		tempResult=	verifyTrue(dropDownMessageType.isEnabled(),"MessageType is disabled!!");
		publishResults(tempResult,"MessageType Enabled : "+dropDownMessageType.isEnabled()+"","MessageType should be enabled","MessageType field Validation");
		tempResult=	verifyTrue(textboxPayingParticipantIdentifier.isEnabled(),"PayingParticipantIdentifier is disabled!!");
		publishResults(tempResult,"PayingParticipantIdentifier Enabled : "+textboxPayingParticipantIdentifier.isEnabled()+"","PayingParticipantIdentifier should be enabled","PayingParticipantIdentifier field Validation");
		tempResult=	verifyTrue(textboxBeneficiaryParticipantIdentifier.isEnabled(),"BeneficiaryParticipantIdentifier is disabled!!");
		publishResults(tempResult,"BeneficiaryParticipantIdentifier Enabled : "+textboxBeneficiaryParticipantIdentifier.isEnabled()+"","BeneficiaryParticipantIdentifier should be enabled","BeneficiaryParticipantIdentifier field Validation");
		tempResult=	verifyTrue(textboxCollectingParticipantIdentifier .isEnabled(),"CollectingParticipantIdentifier is disabled!!");
		publishResults(tempResult,"CollectingParticipantIdentifier Enabled : "+textboxCollectingParticipantIdentifier.isEnabled()+"","CollectingParticipantIdentifier should be enabled","CollectingParticipantIdentifier field Validation");
		tempResult=	verifyTrue(dropDownChannelIdentifier.isEnabled(),"ChannelIdentifier End is disabled!!");
		publishResults(tempResult,"ChannelIdentifier Enabled : "+dropDownChannelIdentifier.isEnabled()+"","ChannelIdentifier should be enabled","ChannelIdentifier field Validation");
		tempResult=	verifyTrue(textboxCollectionLocation.isEnabled(),"CollectionLocation is disabled!!");
		publishResults(tempResult,"CollectionLocation Enabled : "+textboxCollectionLocation.isEnabled()+"","CollectionLocation should be enabled","CollectionLocation field Validation");
		tempResult=	verifyTrue(textboxCollectingBranchLocation.isEnabled(),"CollectingBranchLocation End is disabled!!");
		publishResults(tempResult,"CollectingBranchLocation Enabled : "+textboxCollectingBranchLocation.isEnabled()+"","CollectingBranchLocation should be enabled","CollectingBranchLocation field Validation");
		tempResult=	verifyTrue(textboxSource.isEnabled(),"Source is disabled!!");
		publishResults(tempResult,"Source Enabled : "+textboxSource.isEnabled()+"","Source should be enabled","Source field Validation");
		return this;	
	}

	// Description : This method validates error field for Processing date
	public SearchItemPage validatePaperClearingInputsFields() {	
		tempResult=	verifyTrue(textboxClearingDIN.isEnabled(),"Clearing DIN is disabled!!");
		publishResults(tempResult,"Clearing DIN  Enabled : "+textboxClearingDIN.isEnabled()+"","Clearing DIN  should be enabled","Clearing DIN  field Validation");
		tempResult=	verifyTrue(textboxBranchOrIBDEISN.isEnabled(),"Branch Item sequence number is disabled");
		publishResults(tempResult,"Branch Item sequence number Enabled : "+textboxBranchOrIBDEISN.isEnabled()+"","Branch Item sequence number should be enabled","Branch Item sequence number field Validation");
		tempResult=	verifyTrue(selectWorkStream.isEnabled(),"WorkStream is disabled");
		publishResults(tempResult,"WorkStream Enabled : "+selectWorkStream.isEnabled()+"","WorkStream should be enabled","WorkStream field Validation");
		return this;	
	}
	
	// Description : This method validates error field for Clearing DIN
	public SearchItemPage validateClearingDinErrorField() {	
		String ErrorClearingDinText="Clearing DIN allowed character length is 10 or 12.";
		textboxClearingDIN.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(2000);
		tempResult=	verifyTrue(ClearingDinErrorText.getText().equals(ErrorClearingDinText),"Error description for Clearing DIN is not correct!!");
		publishResults(tempResult,ClearingDinErrorText.getText(),ErrorClearingDinText,"Error description for Clearing DIN field Validation");
		return this;	
	}
	
	// Description : This method validates error field for Processing date
	public SearchItemPage validateProcDateErrorField() 
	{	
		String ErrorProcessingDateText="Please enter a valid date in DD/MM/YYYY or DDMMYYYY format.";
		textboxProcDate.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorProcessingDate.getText().equals(ErrorProcessingDateText),"Error description for Processing Date is not correct!!");
		publishResults(tempResult,ErrorProcessingDate.getText(),ErrorProcessingDateText,"Error description for Processing Date field Validation");
		return this;	
	}
	
	// Description : This method validates error field for Processing date end
	public SearchItemPage validateProcDateEndErrorField() 
	{	
		String ErrorProcessingDateEndText="Please enter a valid date in DD/MM/YYYY or DDMMYYYY format.";
		textboxProcDateEnd.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorProcessingDateEnd.getText().equals(ErrorProcessingDateEndText),"Error description for Processing Date End  is not correct!!");
		publishResults(tempResult,ErrorProcessingDateEnd.getText(),ErrorProcessingDateEndText,"Error description for Processing Date End field Validation");
		return this;	
	}
	
	// Description : This method validates error field for Posting Date error
	public SearchItemPage validatePostingDateErrorField() 
	{	
		String ErrorPostingDateText="Please enter a valid date in DD/MM/YYYY or DDMMYYYY format.";
		textboxPostingDate.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorPostingDate.getText().equals(ErrorPostingDateText),"Error description for Posting Date is not correct!!");
		publishResults(tempResult,ErrorPostingDate.getText(),ErrorPostingDateText,"Error description for Posting Date field Validation");
		return this;	
	}
	
	// Description : This method validates error field for PostingDate End
	public SearchItemPage validatePostingDateEndErrorField() 
	{	
		String ErrorPostingDateEndText="Please enter a valid date in DD/MM/YYYY or DDMMYYYY format.";
		textboxPostingDateEnd.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorPostingDateEnd.getText().equals(ErrorPostingDateEndText),"Error description for Posting Date End  is not correct!!");
		publishResults(tempResult,ErrorPostingDateEnd.getText(),ErrorPostingDateEndText,"Error description for Posting Date End field Validation");
		return this;	
	}
	
	
	// Description : This method validates error field for Sort Code
	public SearchItemPage validateSorCodeErrorField() 
	{	
		String ErrorSortCodeText="Sort Code allowed character length is 6 (optional wildcard ?).";
		textboxSC.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorSortCode.getText().equals(ErrorSortCodeText),"Error description for Sort Code  is not correct!!");
		publishResults(tempResult,ErrorSortCode.getText(),ErrorSortCodeText,"Error description for Sort Code field Validation");
		textboxSC.clear();
		return this;	
	}
	
	// Description : This method validates error field for Account Number
	public SearchItemPage validateAccountErrorField()
	{	
		String ErrorAccountText="Account should be 8 digit or 11 characters (optional wildcard ?).";
		textboxAccount.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorAccount.getText().equals(ErrorAccountText),"Error description for Account  is not correct!!");
		publishResults(tempResult,ErrorAccount.getText(),ErrorAccountText,"Error description for Account field Validation");
		textboxAccount.clear();
		return this;	
	}
	
	// Description : This method validates error field for Tran Code
	public SearchItemPage validateTranCodeErrorField()
	{	
		String ErrorTranCodeText="Tran Code allowed character length is 2.";
		textboxTranCode.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(2000);
		tempResult=	verifyTrue(ErrorTranCode.getText().equals(ErrorTranCodeText),"Error description for Tran Code is not correct!!");
		publishResults(tempResult,ErrorTranCode.getText(),ErrorTranCodeText,"Error description for Tran Code field Validation");
		return this;	
	}
	
	// Description : This method validates error field for Amount and AmountEnd
	public SearchItemPage validateAmountAndAmountEndErrorField() {	
		textboxTranCode.clear();
		String ErrorAmountText="Amount From should be numeric with optional decimal (9,2).";
		String Error_AmountText="Amount From should be less than or equal to Amount To.";
		textboxAmount.sendKeys(getIAValueFromDataSheet("AmountErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorAmount.getText().equals(ErrorAmountText),"Error description for Amount is not correct!!");
		publishResults(tempResult,ErrorAmount.getText(),ErrorAmountText,"Error description for Amount field Validation");
		String ErrorAmountEndText="Amount To should be numeric with optional decimal (9,2).";
		textboxAmountEnd.sendKeys(getIAValueFromDataSheet("AmountEndErrorField"));
		pause(1200);
		tempResult=	verifyTrue(ErrorAmountEnd.getText().equals(ErrorAmountEndText),"Error description for Amount End is not correct!!");
		publishResults(tempResult,ErrorAmountEnd.getText(),ErrorAmountEndText,"Error description for Amount field Validation");
		textboxAmountEnd.clear();
		textboxAmountEnd.sendKeys(getIAValueFromDataSheet("ErrorField"));
		textboxAmount.sendKeys(getIAValueFromDataSheet("AmountError"));
		tempResult=	verifyTrue(ErrorAmount.getText().equals(Error_AmountText),"Error description for Amount is not correct!!");
		publishResults(tempResult,ErrorAmount.getText(),Error_AmountText,"Error description for Amount field Validation");
		textboxAmount.clear();
		textboxAmountEnd.clear();
		return this;	
	}
	
	// Description : This method validates error field for UII
	public SearchItemPage validateUIIErrorField() 
	{	
		String ErrorUIIText="Please enter a valid UII in CCCCCCYYDDDSSSSAAAAAAAAAA or CCCCCC% or CCCCCCYYDDD% or CCCCCCYYDDDSSSS% format";
		textboxUII.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorUniqueItemIdentifier.getText().equals(ErrorUIIText),"Error description for UII is not correct!!");
		publishResults(tempResult,ErrorUniqueItemIdentifier.getText(),ErrorUIIText,"Error description for UII field Validation");
		textboxUII.clear();
		clickElement(itemSearchHeaderTitle);
		pause(1200);
		return this;
		}
		
	// Description : This method validates error field for UTSI
	public SearchItemPage validateUTSIErrorField() {	
		String ErrorUTSIText="Please enter a valid UTSI in AAAAAAAAAAAAAAAAAAAAAA or AAAAAAAAAAAAAAAAAAAAAAAA or CCYYMMDD:DCVID:TransactionNumber";
		textboxTransactionSetIdentifier.sendKeys(getIAValueFromDataSheet("UTSIErrorField"));
		pause(1200);
		tempResult=	verifyTrue(ErrorTransactionSetIdentifier.getText().equals(ErrorUTSIText),"Error description for UTSI is not correct!!");
		publishResults(tempResult,ErrorTransactionSetIdentifier.getText(),ErrorUTSIText,"Error description for UTSI field Validation");
		textboxTransactionSetIdentifier.clear();
		clickElement(itemSearchHeaderTitle);
		pause(1200);
		return this;
		}
	
	// Description : This method validates error field for Pay PID
	public SearchItemPage validatePayPIDErrorField() {	
		String ErrorPayPIDText="Paying Participant ID allowed character length is 6.";
		textboxPayingParticipantIdentifier.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorPayingParticipant.getText().equals(ErrorPayPIDText),"Error description for PayPID is not correct!!");
		publishResults(tempResult,ErrorPayingParticipant.getText(),ErrorPayPIDText,"Error description for PayPID field Validation");
		textboxPayingParticipantIdentifier.clear();
		clickElement(itemSearchHeaderTitle);
		pause(1200);
		return this;
	}
	
	
	// Description : This method validates error field for BenPID
	public SearchItemPage validateBenPIDErrorField() {	
		String ErrorBenPIDText="Beneficiary Participant ID allowed character length is 6.";
		textboxBeneficiaryParticipantIdentifier.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorBeneficiaryParticipant.getText().equals(ErrorBenPIDText),"Error description for Ben PID is not correct!!");
		publishResults(tempResult,ErrorBeneficiaryParticipant.getText(),ErrorBenPIDText,"Error description for Ben PID field Validation");
		textboxBeneficiaryParticipantIdentifier.clear();
		clickElement(itemSearchHeaderTitle);
		pause(1200);
		return this;
	}
	
	// Description : This method validates error field for collecting location PID
	public SearchItemPage validateCollPIDErrorField() {	
		String ErrorColPIDText="Collecting Participant ID allowed character length is 6.";
		textboxCollectingParticipantIdentifier.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorCollectingParticipant.getText().equals(ErrorColPIDText),"Error description for Collecting PID is not correct!!");
		publishResults(tempResult,ErrorCollectingParticipant.getText(),ErrorColPIDText,"Error description for Collecting PID field Validation");
		textboxCollectingParticipantIdentifier.clear();
		clickElement(itemSearchHeaderTitle);
		pause(1200);
		return this;	
	}
		
	// Description : This method validates error field for collecting location 
	public SearchItemPage validateCollLocErrorField() {	
		String ErrorColPIDText="Collecting Location allowed character length is 6.";
		textboxCollectionLocation.sendKeys(getIAValueFromDataSheet("ErrorField"));
		pause(900);
		tempResult=	verifyTrue(ErrorCollectingLocation.getText().equals(ErrorColPIDText),"Error description for Collecting Loc is not correct!!");
		publishResults(tempResult,ErrorCollectingLocation.getText(),ErrorColPIDText,"Error description for Collecting Loc field Validation");
		textboxCollectionLocation.clear();
		clickElement(itemSearchHeaderTitle);
		pause(1200);
		return this;	
	}
	
	// Description : This method validates whether Processing date is focused or not when on the main page 
	public SearchItemPage validateProcDateFocus() throws AWTException {	
		Robot rb=new Robot();
		rb.keyPress(KeyEvent.VK_2);
		pause(1000);
		String ProcDateVal = textboxProcDate.getAttribute("value");
		if(ProcDateVal.equals(getIAValueFromDataSheet("ProcDateVal")))
		{
			flag=true;
			publishResults(flag,"Cursor placed in 'Processing Date' Field", "Cursor should be placed in 'Processing Date' Field", "Validate Cursor placed in 'Processing Date' Field");
			}
		else
		{
			flag=false;
			finalTestScriptResultFailFlag = true;
			publishResults(flag,"Cursor is NOT placed in 'Processing Date' Field", "Cursor should be placed in 'Processing Date' Field", "Verify if Cursor is placed in 'Processing Date' Field");
		}
		assertTrue(flag, "Cursor is NOT placed in 'Processing Date' Field");
		return this;	
	}
	
	// Description : This method validates all fields for RTP indicator 
	public SearchItemPage validateRTPPaidIndicator() {
		clickImageClearingOnly();
		pause(100);
		assertFalse(getSelectClassWebElementInstance(dropdownState).isMultiple(),"Only Single value can be selected from the drop down");
		assertFalse(getSelectClassWebElementInstance(dropdownState).getFirstSelectedOption().equals(ICSPropertiesConfig.DropdownPaidDefaultValue()),"Default value is available");
		List<WebElement> options=getSelectClassWebElementInstance(dropdownState).getOptions();
		assertFalse(options.size()==7, "Seven values are available in the dropdown");
		assertEquals(options.get(0).getText(), ICSPropertiesConfig.DropdownPaidDefaultValue(), "Default value available in the dropdown");
		assertEquals(options.get(1).getText(), ICSPropertiesConfig.DropdownPaidValueIndex1(), "Completed value available in the dropdown");
		assertEquals(options.get(2).getText(), ICSPropertiesConfig.DropdownPaidValueIndex2(), "Paid available in the dropdown");
		assertEquals(options.get(3).getText(), ICSPropertiesConfig.DropdownPaidValueIndex3(), "Not Paid available in the dropdown");
		assertEquals(options.get(4).getText(), ICSPropertiesConfig.DropdownPaidValueIndex4(), "Bank Holiday paid status for RTP Cheque value available in the dropdown");	
		assertEquals(options.get(5).getText(), ICSPropertiesConfig.DropdownPaidValueIndex5(), "Hold Over available in the dropdown");
		assertEquals(options.get(6).getText(), ICSPropertiesConfig.DropdownPaidValueIndex6(), "In Process available in the dropdown");
		assertEquals(options.get(7).getText(), ICSPropertiesConfig.DropdownPaidValueIndex7(), "All Process Completed available in the dropdown");	
		tempResult=	verifyTrue(options.get(1).getText().equals(ICSPropertiesConfig.DropdownPaidValueIndex1()),"Completed Value not available in the dropdown!!");
		publishResults(tempResult,options.get(1).getText(),ICSPropertiesConfig.DropdownPaidValueIndex1(),"Completed value field Validation in the RTP dropdown");
		tempResult=	verifyTrue(options.get(2).getText().equals(ICSPropertiesConfig.DropdownPaidValueIndex2()),"Paid Value not available in the dropdown!!");
		publishResults(tempResult,options.get(2).getText(),ICSPropertiesConfig.DropdownPaidValueIndex2(),"Paid value field Validation in the RTP dropdown");
		tempResult=	verifyTrue(options.get(3).getText().equals(ICSPropertiesConfig.DropdownPaidValueIndex3()),"Not Paid Value not available in the dropdown!!");
		publishResults(tempResult,options.get(3).getText(),ICSPropertiesConfig.DropdownPaidValueIndex3(),"Not Paid value field Validation in the RTP dropdown");
		tempResult=	verifyTrue(options.get(4).getText().equals(ICSPropertiesConfig.DropdownPaidValueIndex4()),"Bank Holiday  Value not available in the dropdown!!");
		publishResults(tempResult,options.get(4).getText(),ICSPropertiesConfig.DropdownPaidValueIndex4(),"Bank Holiday  value field Validation in the RTP dropdown");
		tempResult=	verifyTrue(options.get(5).getText().equals(ICSPropertiesConfig.DropdownPaidValueIndex5()),"Hold Over Value not available in the dropdown!!");
		publishResults(tempResult,options.get(5).getText(),ICSPropertiesConfig.DropdownPaidValueIndex5(),"Hold Over value field Validation in the RTP dropdown");
		tempResult=	verifyTrue(options.get(6).getText().equals(ICSPropertiesConfig.DropdownPaidValueIndex6()),"In Process Value not available in the dropdown!!");
		publishResults(tempResult,options.get(6).getText(),ICSPropertiesConfig.DropdownPaidValueIndex6(),"In Process value field Validation in the RTP dropdown");
		tempResult=	verifyTrue(options.get(7).getText().equals(ICSPropertiesConfig.DropdownPaidValueIndex7()),"All Process Completed Value not available in the dropdown!!");
		publishResults(tempResult,options.get(7).getText(),ICSPropertiesConfig.DropdownPaidValueIndex7(),"All Process Completed field Validation in the RTP dropdown");
		clickClearALL();
		pause(2000);
		return this;	
	}	
	
	// Description : This method validates all fields should be intact when back button is clicked 
	public SearchItemPage validateHomeBackNavigation(){
		pause(2000);
		clickShared();
		pause(2000);
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDate());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElement(buttonSearch);
		String AmountEndText=ICSPropertiesConfig.getAmountEnd();
		pause(9000);
		clickElementEnter(buttonBack);
		pause(3000);
		tempResult=	verifyTrue(textboxAmountEnd.getAttribute("value").trim().equals(AmountEndText.trim()),"Amount End value is not correct!!");
		publishResults(tempResult,textboxAmountEnd.getAttribute("value").trim(),AmountEndText.trim(),"Amount End field Validation on Home Page");
		pause(5000);
		return this;
	}
	
	// Description : This method validates all fields are empty/cleared when navigated to Home page 
	public SearchItemPage validateHomeNavigation(){
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDate());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		//String AmountEndText=ICSPropertiesConfig.getAmountEnd();
		clickElement(buttonSearch);
		pause(9000);
		clickElementEnter(ItemSearchQueryMenu);
		pause(7000);
		tempResult=	verifyTrue(textboxAmountEnd.getAttribute("value").isEmpty(),"Amount End value is not Empty!!");
		publishResults(tempResult,"Amount To text box is empty : "+textboxAmountEnd.getAttribute("value").isEmpty()+"","Amount To field should be empty", "Amount To field Validation");
		tempResult=	verifyTrue(textboxAmount.getAttribute("value").isEmpty(),"Amount From value is not Empty!!");
		publishResults(tempResult,"Amount From text box is empty : "+textboxAmount.getAttribute("value").isEmpty()+"","Amount From field should be empty", "Amount From field Validation");
		return this;
	}
	
	// Description : This method validates presence of previous query menu 
	public SearchItemPage validatePreviousQueryMenu(){
		tempResult=	verifyTrue(PreviousQueryMenu.isDisplayed(),"Previous Query Menu is not Displayed!!");
		publishResults(tempResult,"Prev Query menu is present: "+PreviousQueryMenu.isDisplayed()+"","Previous Query menu should be displayed", "Previous query menu option Validation");
		return this;
	}
	
	// Description : This method validates if previous query menu is disabled 
	public SearchItemPage validatePreviousQueryMenuDisabled(){
		pause(3000);
		clickItemSearchQueryMenu();
		String isDisabled = PreviousQueryMenuDisabled.getAttribute("disabled");
		tempResult=	verifyTrue(isDisabled.equals("true"),"Previous Query Menu is Enabled!!");
		publishResults(tempResult,"Prev Query menu is disabled: "+isDisabled.equals("true")+"","Previous Query menu should be disabled", "Previous query menu option disabled Validation");
		return this;
	}
	
	// Description : This method validates if previous query menu is enabled 
	public SearchItemPage validatePreviousQueryMenuEnabled(){
		tempResult=	verifyTrue(PreviousQueryMenu.isEnabled(),"Previous Query Menu is disabled!!");
		publishResults(tempResult,"Prev Query menu is enabled: "+PreviousQueryMenu.isEnabled()+"","Previous Query menu should be enabled", "Previous query menu option enabled Validation");
		return this;
	}

	// Description : This method click previous query menu 
	public SearchItemPage clickPreviousQueryMenu(){
		clickElementEnter(PreviousQueryMenu);	
		pause(5000);
		return this;
	}
	
	// Description : This method clicks first row in the previous queries page
	public SearchItemPage clickPreviousQueryMenuFirstItem(){
		
		clickElementEnter(PreviousQueryMenuFirstItem);	
		pause(5000);
		tempResult=	verifyTrue(itemSearchHeaderTitle.isDisplayed(),"Search criteria for previous query is not populated!!");
		publishResults(tempResult,"Search criteria for previous query is populated: "+itemSearchHeaderTitle.isDisplayed()+"","Search criteria for prev query should be populated", "Search criteria for prev query Validation");
		return this;
	}
	
	// Description : This method inputs text in IBDE ISN field 
	public SearchItemPage inputTextBoxBranchOrIBDEISN(String value) {			
		fillTextBox(textboxBranchOrIBDEISN, value);
		return this;	
	}
	
	// Description : This method validates if all 3 sections are enabled when corresponding sections are selected 
	public SearchItemPage validateHomeScreenSections(){
		clickElementEnter(ItemSearchQueryMenu);	
		pause(5000);
		tempResult=	verifyTrue(textboxProcDate.isEnabled(),"Proc date is not enabled!!");
		publishResults(tempResult,"Proc Date is Enabled: "+textboxProcDate.isEnabled()+"","Proc Date should be enabled", "Proc Date isEnable Validation");
		clickImageClearingOnly();
		pause(1000);
		tempResult=	verifyTrue(textboxUII.isEnabled(),"UII is not enabled!!");
		publishResults(tempResult,"UII is Enabled: "+textboxUII.isEnabled()+"","UII should be enabled", "UII isEnable Validation");
		clickPaperClearingOnly();
		pause(1000);
		tempResult=	verifyTrue(textboxClearingDIN.isEnabled(),"ClearingDIN is not enabled!!");
		publishResults(tempResult,"ClearingDIN is Enabled: "+textboxClearingDIN.isEnabled()+"","ClearingDIN", "ClearingDIN isEnable Validation");
		return this;
	}
	
	// Description : This method validates presence of menus on main search page 
	public SearchItemPage validateHomeScreenMenus(){
		tempResult=	verifyTrue(ItemSearchQueryMenu.isDisplayed(),"Item Search Query Menu is not displayed!!");
		publishResults(tempResult,"Item Search Query Menu is Enabled: "+ItemSearchQueryMenu.isDisplayed()+"","Item Search Query Menu  should be enabled", "Item Search Query Menu  isEnable Validation");
		tempResult=	verifyTrue(PreviousQueryMenu.isDisplayed(),"Previous Query Menu is not displayed!!");
		publishResults(tempResult,"Previous Query Menu  is Enabled: "+PreviousQueryMenu.isDisplayed()+"","Previous Query Menu should be enabled", "Previous Query Menu  isEnable Validation");
		tempResult=	verifyTrue(OutBoxMenu.isDisplayed(),"OutBox Menu is not displayed!!");
		publishResults(tempResult,"OutBox Menu is Enabled: "+OutBoxMenu.isDisplayed()+"","OutBox Menu", "OutBox Menu isEnable Validation");
		return this;
	}
	
	// Description : This method validates Out box functionality 
	public SearchItemPage validateOutboxMenu(){
		String isDisabled = OutBoxMenuDisabled.getAttribute("disabled");
		tempResult=	verifyTrue(OutBoxMenu.isDisplayed(),"OutBox Menu is not displayed!!");
		publishResults(tempResult,"OutBox Menu is Displayed: "+OutBoxMenu.isDisplayed()+"","OutBox Menu should be displayed", "OutBox Menu isDisplayed Validation");
		
		tempResult=	verifyTrue((isDisabled.equals("true")),"Outbox menu is Enabled:!!");
		publishResults(tempResult,"Outbox menu is Disabled: "+isDisabled.equals("true")+"","Outbox Menu should be Disabled", "OutBox Menu is Disabled Validation");
		return this;
	}
	
	// Description : This method performs IBDE ISN field level validations 
	public SearchItemPage validateTextBoxBranchOrIBDEISN() {
		clickPaperClearingOnly();
		fillTextBox(textboxBranchOrIBDEISN, ICSPropertiesConfig.GetInvalidBranchOrIBDEISN());
		assertTrue(textboxBranchOrIBDEISN.getAttribute("value").length()==12,"Only 12 digits will be accepted");
		fillTextBox(textboxBranchOrIBDEISN, ICSPropertiesConfig.GetInvalidBranchOrIBDEISNString());
		assertFalse(textboxBranchOrIBDEISN.getAttribute("value")==null,"Only digits will be accepted");
		return this;	
	}
	
	// Description : This method performs IBDE ISN field level validations 
		public SearchItemPage validateTextBoxIBDEISN() {
			fillTextBox(textboxBranchOrIBDEISN, ICSPropertiesConfig.GetInvalidBranchOrIBDEISN());
			assertTrue(textboxBranchOrIBDEISN.getAttribute("value").length()==12,"Only 12 digits will be accepted");
			fillTextBox(textboxBranchOrIBDEISN, ICSPropertiesConfig.GetInvalidBranchOrIBDEISNString());
			assertFalse(textboxBranchOrIBDEISN.getAttribute("value")==null,"Only digits will be accepted");
			return this;	
		}
	
		// Description : This method performs Wide Search - Error Handling 	
		public SearchItemPage validateWideSearch() {
			clickItemSearchQueryMenu();
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd());
			fillTextBox(textboxSC, ICSPropertiesConfig.getSortCode());
			fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerial());
			fillTextBox(textboxAccount, ICSPropertiesConfig.getAccount());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount());
			clickElementEnter(ButtonSearch);
			pause(9000);
			String ValidationErrorText= "1. The date range entered exceeds the limit of 1 year. Please reduce the date range to 1 year or less";
			tempResult=	verifyTrue(searchResultsInfo.isDisplayed(),"Search is not successful");
			publishResults(tempResult,"Search successful with Proc date range less than a year : "+searchResultsInfo.isDisplayed()+"","Search results should be displayed", "Validation for Wide search with proc date range less than a year");
			clickElementEnter(buttonBack);
			pause(2000);
			textboxProcDateEnd.clear();
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd1Year());
			pause(2000);
			clickElementEnter(ButtonSearch);
			tempResult=	verifyTrue(TextValidationErrors.getText().equals(ValidationErrorText),"Validation error not displayed for date range more than a year");
			publishResults(tempResult,"Validation error Displayed for date range more than a year : "+TextValidationErrors.getText().equals(ValidationErrorText)+"","Error should be displayed for date range more than a year", "Validation for search with date range more than a year");
			clickClearALL();
			
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd());
			fillTextBox(textboxSC, ICSPropertiesConfig.getSortCode());
			fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerial());
			fillTextBox(textboxAccount, ICSPropertiesConfig.getAccount());
			clickElementEnter(ButtonSearch);
			pause(9000);
			tempResult=	verifyTrue(searchResultsInfo.isDisplayed(),"Search is not successful");
			publishResults(tempResult,"Search successful with Proc date range less than a year : "+searchResultsInfo.isDisplayed()+"","Search results should be displayed", "Validation for Wide search with proc date range less than a year");
			clickElementEnter(buttonBack);
			pause(2000);
			textboxProcDateEnd.clear();
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd1Year());
			pause(2000);
			clickElementEnter(ButtonSearch);
			tempResult=	verifyTrue(TextValidationErrors.getText().equals(ValidationErrorText),"Validation error not displayed for date range more than a year");
			publishResults(tempResult,"Validation error Displayed for date range more than a year : "+TextValidationErrors.getText().equals(ValidationErrorText)+"","Error should be displayed for date range more than a year", "Validation for search with date range more than a year");
			clickClearALL();
			
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd());
			fillTextBox(textboxSC, ICSPropertiesConfig.getSortCode());
			fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerial());
			fillTextBox(textboxAccount, ICSPropertiesConfig.getAccount());
			clickElementEnter(ButtonSearch);
			pause(9000);
			tempResult=	verifyTrue(searchResultsInfo.isDisplayed(),"Search is not successful");
			publishResults(tempResult,"Search successful with Proc date range less than a year : "+searchResultsInfo.isDisplayed()+"","Search results should be displayed", "Validation for Wide search with proc date range less than a year");
			clickElementEnter(buttonBack);
			pause(2000);
			textboxProcDateEnd.clear();
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd1Year());
			pause(2000);
			clickElementEnter(ButtonSearch);
			tempResult=	verifyTrue(TextValidationErrors.getText().equals(ValidationErrorText),"Validation error not displayed for date range more than a year");
			publishResults(tempResult,"Validation error Displayed for date range more than a year : "+TextValidationErrors.getText().equals(ValidationErrorText)+"","Error should be displayed for date range more than a year", "Validation for search with date range more than a year");
			clickClearALL();
			
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd());
			fillTextBox(textboxSC, ICSPropertiesConfig.getSortCode());
			fillTextBox(textboxAccount, ICSPropertiesConfig.getAccount());
			clickElementEnter(ButtonSearch);
			pause(9000);
			tempResult=	verifyTrue(searchResultsInfo.isDisplayed(),"Search is not successful");
			publishResults(tempResult,"Search successful with Proc date range less than a year : "+searchResultsInfo.isDisplayed()+"","Search results should be displayed", "Validation for Wide search with proc date range less than a year");
			clickElementEnter(buttonBack);
			pause(2000);
			textboxProcDateEnd.clear();
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd1Year());
			pause(2000);
			clickElementEnter(ButtonSearch);
			tempResult=	verifyTrue(TextValidationErrors.getText().equals(ValidationErrorText),"Validation error not displayed for date range more than a year");
			publishResults(tempResult,"Validation error Displayed for date range more than a year : "+TextValidationErrors.getText().equals(ValidationErrorText)+"","Error should be displayed for date range more than a year", "Validation for search with date range more than a year");
			clickClearALL();
			return this;	
		}
		
			
		// Description : This method validates date input functionality
		public SearchItemPage validateDateInput() {			
			String expectedDate="12/11/2016";
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerInputDate());
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerInputDate());
			fillTextBox(textboxPostingDate, ICSPropertiesConfig.getDatePickerInputDate());
			fillTextBox(textboxPostingDateEnd, ICSPropertiesConfig.getDatePickerInputDate());
			tempResult=	verifyTrue(textboxProcDate.getText().equals(expectedDate),"Validation Date format not correct for Processing date field");
			publishResults(tempResult,"Date format for Processing date is as expected : "+textboxProcDate.getText().equals(expectedDate)+"","Date format should be as expected", "Validation for date format for processing date");
			tempResult=	verifyTrue(textboxProcDateEnd.getText().equals(expectedDate),"Validation Date format not correct for Processing date End field");
			publishResults(tempResult,"Date format for Processing date End is as expected : "+textboxProcDateEnd.getText().equals(expectedDate)+"","Date format should be as expected", "Validation for date format for processing date end");
			tempResult=	verifyTrue(textboxPostingDate.getText().equals(expectedDate),"Validation Date format not correct for Posting date field");
			publishResults(tempResult,"Date format for Posting date is as expected : "+textboxPostingDate.getText().equals(expectedDate)+"","Date format should be as expected", "Validation for date format for posting date");
			tempResult=	verifyTrue(textboxPostingDateEnd.getText().equals(expectedDate),"Validation Date format not correct for Posting date end date field");
			publishResults(tempResult,"Date format for Posting date end is as expected : "+textboxProcDateEnd.getText().equals(expectedDate)+"","Date format should be as expected", "Validation for date format for Posting date end");
			clickClearALL();
			return this;	
		}
		
	
	// Description : This method performs ISN field level validations 
	public SearchItemPage validateCaptureISN() {			
		fillTextBox(textboxCaptureISN, ICSPropertiesConfig.getTextboxInvalidCaptureISN());
		assertTrue(textboxCaptureISN.getText().length()==19,"Only 19 digits will be accepted");
		fillTextBox(textboxCaptureISN, ICSPropertiesConfig.getTextboxInvalidCaptureISNString());
		assertFalse(textboxCaptureISN.getText()==null,"Only digits will be accepted");
		return this;	
	}
	
	// Description : This method perform click on Item search query menu 
	public SearchItemPage clickItemSearchQueryMenu() {			
		clickElementEnter(ItemSearchQueryMenu);
		pause(5000);
		return this;	
	}
	
	
	// Description : This method perform click on Represent query menu 
		public SearchItemPage clickRepresentQueryMenu() {			
			clickElementEnter(RepresentQueryMenu);
			pause(5000);
			return this;	
		}
		
	// Description : This method validates Date format error messages 
	public SearchItemPage validateDateFormatErrorMessage() 
	{
		String errorMsgInvalidDate="Please enter a valid date in DD/MM/YYYY or DDMMYYYY format.";
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerInvalidProcDate());
		pause(500);
		tempResult=	verifyTrue(dateFormatErrorMessage.getText().equals(errorMsgInvalidDate),"Incorrect Date Format Error message!!");
		publishResults(tempResult,dateFormatErrorMessage.getText(),errorMsgInvalidDate,"Error description for Proc Date field Validation");
		fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerInvalidProcDate());
		pause(500);
		tempResult=	verifyTrue(ErrorProcessingDateEnd.getText().equals(errorMsgInvalidDate), "Incorrect Date Format Error message!!");
		publishResults(tempResult,ErrorProcessingDateEnd.getText(),errorMsgInvalidDate,"Error description for Proc Date End field Validation");
		fillTextBox(textboxPostingDate, ICSPropertiesConfig.getDatePickerInvalidProcDate());
		pause(500);
		tempResult=	verifyTrue(ErrorPostingDate.getText().equals(errorMsgInvalidDate),"Incorrect Date Format Error message!!");
		publishResults(tempResult,ErrorPostingDate.getText(),errorMsgInvalidDate,"Error description for Posting Date field Validation");
		pause(500);
		fillTextBox(textboxPostingDateEnd, ICSPropertiesConfig.getDatePickerInvalidProcDate());
		tempResult=	verifyTrue(ErrorPostingDateEnd.getText().equals(errorMsgInvalidDate),"Incorrect Date Format Error message!!");
		publishResults(tempResult,ErrorPostingDateEnd.getText(),errorMsgInvalidDate,"Error description for Posting Date End field Validation");
		textboxPostingDate.clear();
		textboxPostingDateEnd.clear();
		return this;	
	}
	
	// Description : This method validates Future date error messages 
	public SearchItemPage validateFutureDateFormatErrorMessage() 
	{
		String futureDateErrorMsg = "A future date has been entered. Please enter a valid date.";
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerInvalidFutureProcDate());
		pause(500);
		tempResult=	verifyTrue(dateFormatErrorMessage.getText().equals(futureDateErrorMsg),"Future Date Format Error message!!");
		publishResults(tempResult,dateFormatErrorMessage.getText(),futureDateErrorMsg,"Error description for future Proc Date field Validation");
		fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerInvalidFutureProcDate());
		pause(500);
		tempResult=	verifyTrue(ErrorProcessingDateEnd.getText().equals(futureDateErrorMsg),"Future Date Format Error message!!");
		publishResults(tempResult,ErrorProcessingDateEnd.getText(),futureDateErrorMsg,"Error description for future Proc Date End field Validation");
		textboxProcDate.clear();
		textboxProcDateEnd.clear();
		clickElement(itemSearchHeaderTitle);
		pause(3000);
		return this;
		}

	// Description : This method validates Start date error messages 
	public SearchItemPage validateStartDateErrorMessage() 
	{
		clickShared();
		textboxProcDate.clear();
		textboxProcDateEnd.clear();
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDate());
		fillTextBox(textboxSerialReference,  ICSPropertiesConfig.getSerialReference());
		clickElementEnter(buttonSearch);
		pause(12000);
		tempResult=	verifyTrue(buttonBack.isDisplayed(),"Back button is not displayed for prior date!!!");
		publishResults(tempResult,"Back button is displayed.. : "+buttonBack.isDisplayed()+"","Back button should be displayed", "Singular Date search validation");
		clickElementEnter(buttonBack);
		pause(5000);
		textboxProcDate.clear();
		textboxProcDateEnd.clear();
		textboxSerialReference.clear();
		pause(2000);
		return this;
	}
	
	// Description : This method validates Minimum Search criteria 
	public SearchItemPage validateMinimumSearchCriteria() 
	{
		pause(1000);
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
		fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd());
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerialReference());
		clickElementEnter(buttonSearch);
		pause(9000);
		String searchResults= searchResultsInfo.getText();
		String noOfEntries=searchResults.substring(18,19);
		System.out.println("The number of Entries : " +noOfEntries);
		clickElementEnter(searchResultsInfo);
		pause(2000);
		int entries= Integer.parseInt(noOfEntries);
		tempResult=	verifyTrue(entries>=1,"Minimum Criteria NOT satisfied for Serial Reference");
		publishResults(tempResult,"No. of search results for searial reference : "+entries+"","Search results should be displayed for serial reference", "Minimum Criteria search for Serial Reference validation");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSerialReference.clear();
		fillTextBox(textboxSC, ICSPropertiesConfig.getSortCode());
		clickElementEnter(buttonSearch);
		pause(12000);
		clickElementEnter(searchResultsInfo);
		pause(2000);
		tempResult=	verifyTrue(entries>=1,"Minimum Criteria NOT satisfied for Sort Code");
		publishResults(tempResult,"No. of search results for sort code : "+entries+"","Search results should be displayed for sort code", "Minimum Criteria search for Sort Code validation");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSC.clear();
		fillTextBox(textboxAccount, ICSPropertiesConfig.getAccount());
		clickElementEnter(buttonSearch);
		pause(12000);
		clickElementEnter(searchResultsInfo);
		pause(2000);
		tempResult=	verifyTrue(entries>=1,"Minimum Criteria NOT satisfied for Account");
		publishResults(tempResult,"No. of search results for Account  : "+entries+"","Search results should be displayed for Account", "Minimum Criteria search for Account validation");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxAccount.clear();
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmountForMinSearch());
		clickElementEnter(buttonSearch);
		pause(12000);
		clickElementEnter(searchResultsInfo);
		pause(2000);
		tempResult=	verifyTrue(entries>=1,"Minimum Criteria NOT satisfied for Amount");
		publishResults(tempResult,"No. of search results for Amount  : "+entries+"","Search results should be displayed for Amount", "Minimum Criteria search for Amount validation");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxAmount.clear();
		textboxProcDate.clear();
		textboxProcDateEnd.clear();
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateLegacy());
		fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEndLegacy());
		clickPaperClearingOnly();
		fillTextBox(textboxClearingDIN, ICSPropertiesConfig.getClearingDIN());
		clickElementEnter(buttonSearch);
		pause(12000);
		clickElementEnter(searchResultsInfo);
		pause(2000);
		tempResult=	verifyTrue(entries>=0,"Minimum Criteria NOT satisfied for Clearing DIN");
		publishResults(tempResult,"No. of search results for Clearing DIN  : "+entries+"","Search results should be displayed for Clearing DIN", "Minimum Criteria search for Clearing DIN validation");
		clickElementEnter(buttonBack);
		pause(1000);
		textboxClearingDIN.clear();
		fillTextBox(textboxBranchOrIBDEISN, ICSPropertiesConfig.getBranchORIBDEISN());
		clickElementEnter(buttonSearch);
		pause(12000);
		clickElementEnter(searchResultsInfo);
		pause(1000);
		tempResult=	verifyTrue(entries>=0,"Minimum Criteria NOT satisfied for IBDE ISN");
		publishResults(tempResult,"No. of search results for IBDE ISN  : "+entries+"","Search results should be displayed for IBDE ISN", "Minimum Criteria search for IBDE ISN validation");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxBranchOrIBDEISN.clear();
		return this;
	}
	
	// Description : This method validates Minimum Search criteria for date
	public SearchItemPage validateMinimumSearchCriteriaForOnlyDate() {
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDate());
		fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd());
		clickElementEnter(ButtonSearch);
		tempResult=	verifyTrue(TextValidationErrors.isDisplayed(),"Minimum Criteria NOT satisfied for ONLY Date Parameters");
		publishResults(tempResult,"Validation error Displayed for ONLY Date Parameters : "+TextValidationErrors.isDisplayed()+"","Error should be displayed for ONLY Date Parameters", "Validation for Minimum Criteria search for only Date parameters ");
		textboxProcDate.clear();
		textboxProcDateEnd.clear();
	return this;	
	}
	
	// Description : This method validates Minimum Search criteria 
	public SearchItemPage validateMinimumSearchCriteriaWithNoCriteria() 
	{
		clickShared();
		clickElementEnter(ButtonSearch);
		tempResult=	verifyTrue(TextValidationErrors.isDisplayed(),"Minimum Criteria NOT satisfied with No Criteria");
		publishResults(tempResult,"Validation error Displayed with no criteria : "+TextValidationErrors.isDisplayed()+"","Error should be displayed with no criteria", "Minimum Criteria search with no criteria validation");
		pause(2000);
	return this;	
	}
	
	// Description : This method validates End values for Processing date and posting date
	public SearchItemPage validateToOrEndValueFields() 
	{
		clickImageClearingOnly();
		clickShared();
		fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd());
		clickElementEnter(ButtonSearch);
		tempResult=	verifyTrue(TextValidationErrors.isDisplayed(), "Error Message NOT displayed when only 'To Value'  for Proc Date End is Entered");
		publishResults(tempResult,"Validation error Displayed with only To or End Value for Proc Date End : "+TextValidationErrors.isDisplayed()+"","Error Message should be displayed when only 'To Value' for Proc Date End is Entered", "Error Message Validation for Proc Date End when only end value is Entered");
		pause(1000);
		textboxProcDateEnd.clear();
		fillTextBox(textboxPostingDateEnd, ICSPropertiesConfig.getDatePickerValidPostingDateEnd());
		clickElementEnter(ButtonSearch);
		tempResult=	verifyTrue(TextValidationErrors.isDisplayed(), "Error Message NOT displayed when only 'To Value'  for Posting Date End is Entered");
		publishResults(tempResult,"Error Displayed with only To or End Value for Posting Date End : "+TextValidationErrors.isDisplayed()+"","Error Message should be displayed when only 'To Value' for Proc Date End is Entered", "Error Message Validation for Posting Date End when only end value is Entered");
		pause(2000);
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElementEnter(ButtonSearch);
		tempResult=	verifyTrue(ErrorAmount.isDisplayed(), "Error Amount is not displayed when only 'To Value'  for Amount End is Entered.");
		publishResults(tempResult,"Error Displayed with only To or End Value for Amount End : "+TextValidationErrors.isDisplayed()+"","Error Message should be displayed when only 'To Value' for Amount End is Entered", "Error Message Validation for Amount End when only end value is Entered");
		textboxAmountEnd.clear();
		pause(2000);
		return this;	
	}
	
	// Description : This method validates Search criteria for Sort Code
	public SearchItemPage validateSearchSortCodeWildCardParameters() 
	{
		clickClearALL();
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDate());
		fillTextBox(textboxSC, ICSPropertiesConfig.getSortCodeWith2WildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Search Results are not Displayed for 2 wild chars for sort code");
		publishResults(tempResult,"Search Results displayed for 2 wildchars for sort code : "+searchResultsInfo.isDisplayed()+"","Search Results are should be Displayed for 2 wild chars for sort code", "Search Results Validation for 2 wild chars for sort code");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSC.clear();
		fillTextBox(textboxSC, ICSPropertiesConfig.getSortCodeWith1WildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Search Results are not Displayed for 1 wild char for sort code");
		publishResults(tempResult,"Search Results displayed for 1 wildchar for sort code : "+searchResultsInfo.isDisplayed()+"","Search Results are should be Displayed for 1 wild char for sort code", "Search Results Validation for 1 wild char for sort code");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSC.clear();
		fillTextBox(textboxSC, ICSPropertiesConfig.getSortCodeWith3WildChars());
		clickElementEnter(ButtonSearch);
		pause(1000);
		tempResult=	verifyTrue(ErrorSortCode.isDisplayed(), "SortCode Error Message Not displayed for more than 2 wild char parameters");
		publishResults(tempResult,"Search Results displayed for more than 2 wildchars for sort code : "+ErrorSortCode.isDisplayed()+"","Search Results are should be Displayed for more than  2 wild chars for sort code", "Search Results Validation for more than 2 wild chars for sort code");
		textboxProcDate.clear();
		textboxSC.clear();
		pause(300);
	return this;
	}
	 
	// Description : This method validates Search criteria for Account Number
	public SearchItemPage validateSearchAccountNumberWildCardParameters() 
	{
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDate());
		fillTextBox(textboxAccount, ICSPropertiesConfig.getAccountNumberWithFourWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Account Number Search Results are not Displayed with 4 wild char parameters");
		publishResults(tempResult,"Account Number Search Results displayed with 4 wild char parameters : "+searchResultsInfo.isDisplayed()+"","Account Number Search Results  should be Displayed with 4 wild char parameters", "Search Results Validation for 4 wild char parameters for Account Number");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxAccount.clear();
		fillTextBox(textboxAccount, ICSPropertiesConfig.getAccountNumberWithThreeWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Account Number Search Results are not Displayed with 3 wild char parameters");
		publishResults(tempResult,"Account Number Search Results displayed with 3 wild char parameters : "+searchResultsInfo.isDisplayed()+"","Account Number Search Results  should be Displayed with 3 wild char parameters", "Search Results Validation for 3 wild char parameters for Account Number");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxAccount.clear();
		fillTextBox(textboxAccount, ICSPropertiesConfig.getAccountNumberWithTwoWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Account Number Search Results are not Displayed with 2 wild char parameters");
		publishResults(tempResult,"Account Number Search Results displayed with 2 wild char parameters : "+searchResultsInfo.isDisplayed()+"","Account Number Search Results  should be Displayed with 2 wild char parameters", "Search Results Validation for 2 wild char parameters for Account Number");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxAccount.clear();
		fillTextBox(textboxAccount, ICSPropertiesConfig.getAccountNumberWithFourWildChars());
		fillTextBox(textboxSC, ICSPropertiesConfig.getSortCode());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Account Number Search Results are not Displayed with 4 wild char parameters");
		publishResults(tempResult,"Account Number Search Results displayed with 4 wild char parameters : "+searchResultsInfo.isDisplayed()+"","Account Number Search Results should be Displayed with 4 wild char parameters", "Search Results Validation for 4 wild char parameters for Account Number with sort code");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxAccount.clear();
		textboxSC.clear();
		textboxProcDate.clear();
		pause(300);
		return this;
		}
	
	// Description : This method validates Search criteria for Serial reference
	public SearchItemPage validateSearchSerialWildCardParameters() 
	{
		clickClearALL();		
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDate());
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerialWithTwoWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Serial Search Results are not Displayed with 2 wild char parameters");
		publishResults(tempResult,"Serial Search Results displayed with 2 wild char parameters : "+searchResultsInfo.isDisplayed()+"","Serial Search Results should be Displayed with 2 wild char parameters", "Search Results Validation for 2 wild char parameters for Serial");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSerialReference.clear();
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerialWithOneWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Serial Search Results are not Displayed with 1 wild char parameters");
		publishResults(tempResult,"Serial Search Results displayed with 1 wild char parameter : "+searchResultsInfo.isDisplayed()+"","Serial Search Results should be Displayed with 1 wild char parameter", "Search Results Validation for 1 wild char parameters for Serial");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSerialReference.clear();
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerialWithThreeWildChars());
		clickElementEnter(ButtonSearch);
		pause(2000);
		tempResult=	verifyTrue(serialreferenceError.getText().equals("Only 2 wildcard (?) characters are allowed for Serial"), "Serial Search Error is not Displayed with more than 2 wild char parameters");
		publishResults(tempResult,"Serial search results error should be displayed with more than 2 wild char parameters : "+serialreferenceError.isDisplayed()+"","Serial Search Results error should be Displayed with more than 2 wild char parameters", "Search Results error Validation for more than 2 wild char parameters for Serial");
		textboxSerialReference.clear();
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getAccountNumberWithTwoWildChars());
		fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEnd());
		fillTextBox(textboxSC, ICSPropertiesConfig.getSortCode());
		fillTextBox(textboxAccount, ICSPropertiesConfig.getAccount());
		clickElementEnter(ButtonSearch);
		pause(9000);
		tempResult=	verifyTrue(TextValidationErrors.getText().equals("1. Wildcards cannot be used in conjunction with this query") , "Serial Search Results are not Displayed with 2 wild char parameters with Proc End Date and Sort Code combination");
		publishResults(tempResult,"Serial Search Results error should be displayed with 2 wild char parameters along with sortcode and account : "+TextValidationErrors.isDisplayed()+"","Serial Search Results error should be Displayed with 2 wild char parameters along with sortcode and account", "Search Results Validation for 2 wild char parameters for Serial along with account and sortcode ");
		textboxProcDateEnd.clear();
		textboxSC.clear();
		textboxAccount.clear();
		textboxProcDateEnd.clear();
		return this;
		}
	
		// Description : This method validates Search criteria for Credit reference	
		public SearchItemPage validateSearchCreditReferenceWildCardParameters() 
		{
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDate());
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getCreditReferenceWithSixWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Credit Reference Search Results are not Displayed with 6 wild char parameters");
		publishResults(tempResult,"Credit Reference Results displayed with 6 wild char parameter : "+searchResultsInfo.isDisplayed()+"","Credit Reference Search Results should be Displayed with 6 wild char parameter", "Credit Reference Search Results Validation for 6 wild char parameters");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSerialReference.clear();
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getCreditReferenceWithFiveWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Credit Reference Search Results are not Displayed with 5 wild char parameters");
		publishResults(tempResult,"Credit Reference Search Results displayed with 5 wild char parameter : "+searchResultsInfo.isDisplayed()+"","Credit Reference Search Results should be Displayed with 5 wild char parameter", "Credit Reference search Results Validation for 5 wild char parameters");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSerialReference.clear();
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getCreditReferenceWithFourWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Credit Reference Search Results are not Displayed with 4 wild char parameters");
		publishResults(tempResult,"Credit Reference Search Results displayed with 4 wild char parameter : "+searchResultsInfo.isDisplayed()+"","Credit Reference Search Results should be Displayed with 4 wild char parameter", "Credit Reference Results Validation for 4 wild char parameters");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSerialReference.clear();
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getCreditReferenceWithThreeWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Credit Reference Search Results are not Displayed with 3 wild char parameters");
		publishResults(tempResult,"Credit Reference Search Results displayed with 3 wild char parameter : "+searchResultsInfo.isDisplayed()+"","Credit Reference Search Results should be Displayed with 3 wild char parameter", "Credit Reference Search Results Validation for 3 wild char parameters");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSerialReference.clear();
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getCreditReferenceWithTwoWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Credit Reference Search Results are not Displayed with 2 wild char parameters");
		publishResults(tempResult,"Credit Reference Search Results displayed with 2 wild char parameter : "+searchResultsInfo.isDisplayed()+"","Credit Reference Search Results should be Displayed with 2 wild char parameter", "Credit Reference Search Results Validation for 2 wild char parameters");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSerialReference.clear();
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getCreditReferenceWithOneWildChars());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		tempResult=	verifyTrue(searchResultsInfo.isDisplayed(), "Credit Reference Search Results are not Displayed with 1 wild char parameters");
		publishResults(tempResult,"Credit Reference Search Results displayed with 1 wild char parameter : "+searchResultsInfo.isDisplayed()+"","Credit Reference Search Results should be Displayed with 1 wild char parameter", "Credit Reference search Results Validation for 1 wild char parameter");
		clickElementEnter(buttonBack);
		pause(2000);
		textboxSerialReference.clear();
		fillTextBox(textboxSerialReference, ICSPropertiesConfig.getCreditReferenceWithSevenWildChars());
		clickElementEnter(ButtonSearch);
		tempResult=	verifyTrue(serialreferenceError.getText().equals("Only 6 wildcard (?) characters are allowed for Reference"), "Credit Reference Error not Displayed with more than 6 wild char parameters");
		publishResults(tempResult,"Credit Reference Search Results displayed error with more than 6 wild char parameters : "+serialreferenceError.isDisplayed()+"","Credit Reference Search Results should be Displayed with error message with more than 6 wild char parameters", "Credit Reference search Results Validation for more than 6 wild char parameters");
		textboxSerialReference.clear();
		textboxProcDate.clear();
		clickItemSearchQueryMenu();
		pause(1000);
		return this;
		}
	
	// Description : This method performs Collecting PID field level validations	
	public SearchItemPage validateColPID() {
		clickImageClearingOnly();
		fillTextBox(textboxColPID, ICSPropertiesConfig.getInvalidColPID());
		assertFalse(textboxColPID.getAttribute("value").length()==6,"Only 6 digits will be accepted");
		tempResult=	verifyTrue(textboxColPID.getAttribute("value").equals(ICSPropertiesConfig.getInvalidColPID()),"Only 6 digits will be accepted");
		publishResults(tempResult," Only 6 digits will be accepted: "+(textboxColPID.getAttribute("value")).equals(ICSPropertiesConfig.getInvalidColPID())+"","Only 6 digits will be accepted","Validating Coll PID");
		fillTextBox(textboxColPID, ICSPropertiesConfig.getInvalidColPIDString());
		assertFalse(textboxColPID.getAttribute("value")==null,"Only digits will be accepted");
		fillTextBox(textboxColPID, ICSPropertiesConfig.getInvalidColPIDSpecialCharacters());
		assertFalse(textboxColPID.getAttribute("value")==null,"Only digits will be accepted. Special characters will not be accepted");
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getValidColPIDLeadingZero());
		fillTextBox(textboxSC, ICSPropertiesConfig.getValidColPIDLeadingZero());
		fillTextBox(textboxAccount, ICSPropertiesConfig.getValidColPIDLeadingZero());
		fillTextBox(textboxColPID, ICSPropertiesConfig.getValidColPIDLeadingZero());
		assertFalse(textboxColPID.getAttribute("value")==null,"Only digits will be accepted. Leading zeroes will not be accepted");
		clickClearALL();
		return this;	
	}
	
	// Description : This method performs Pay PID field level validations	
	public SearchItemPage validatePayPID() {
		clickImageClearingOnly();
		fillTextBox(textboxPayPID, ICSPropertiesConfig.getTextboxInvalidPayPID());
		assertFalse(textboxPayPID.getAttribute("value").equals(ICSPropertiesConfig.getInvalidColPID()),"Only 6 digits will be accepted");
		fillTextBox(textboxPayPID, ICSPropertiesConfig.getTextboxInvalidPayPIDString());
		assertFalse(textboxPayPID.getAttribute("value")==null,"Only digits will be accepted");
		fillTextBox(textboxPayPID,ICSPropertiesConfig.getTextboxInvalidPayPIDSpecialCharacters());
		assertFalse(textboxPayPID.getAttribute("value")==null,"Only digits will be accepted. Special characters will not be accepted");
		fillTextBox(textboxPayPID, ICSPropertiesConfig.getTextboxInvalidPayPIDString());
		assertFalse(textboxPayPID.getAttribute("value")==null,"Only digits will be accepted. Leading zeroes will not be accepted");
		tempResult=	verifyTrue(textboxPayPID.getAttribute("value").equals(ICSPropertiesConfig.getTextboxInvalidPayPIDString())," Special characters are NOT allowed");
		publishResults(tempResult," Special characters will not be accepted: "+(textboxPayPID.getAttribute("value")).equals(ICSPropertiesConfig.getTextboxInvalidPayPIDString())+"","Special characters are not allowed for Pay PID","Validating Pay PID");
		clickClearALL();
		return this;	
	}
	
	// Description : This method validates Beneficiary Participant ID field	
	public SearchItemPage validateBenPID() {
		clickImageClearingOnly();
		pause(1000);
		fillTextBox(textboxBenPID, ICSPropertiesConfig.getInvalidBenPID());
		assertFalse(textboxBenPID.getAttribute("value").equals(ICSPropertiesConfig.getInvalidBenPID()),"Only 6 digits will be accepted");
		fillTextBox(textboxBenPID,ICSPropertiesConfig.getInvalidBenPIDString());
		assertFalse(textboxBenPID.getAttribute("value")==null,"Only digits will be accepted");
		tempResult=	verifyTrue(textboxBenPID.getAttribute("value").equals(ICSPropertiesConfig.getInvalidBenPIDString())," Only digits will be allowed");
		publishResults(tempResult," Only digits will be accepted: "+(textboxBenPID.getAttribute("value")).equals(ICSPropertiesConfig.getInvalidBenPIDString())+"","Only digits will be accepted","Validating BenPID");
		fillTextBox(textboxBenPID,ICSPropertiesConfig.getInvalidBenPIDSpecialCharacters());
		assertFalse(textboxBenPID.getAttribute("value")==null,"Only digits will be accepted. Special characters will not be accepted");
		fillTextBox(textboxBenPID, ICSPropertiesConfig.getTextboxInvalidPayPIDString());
		assertFalse(textboxBenPID.getAttribute("value")==null,"Only digits will be accepted. Leading zeroes will not be accepted");
		clickClearALL();	
		return this;	
	}
	
	// Description : This method validates Clearing Item field	
	public SearchItemPage validateClearingItemInterface() {
		fillTextBox(textboxClearingItemReference,ICSPropertiesConfig.getTextboxInvalidClearingItemReference());
		assertFalse(textboxClearingItemReference.getText().length()==10,"Only 10 digits will be accepted");
		fillTextBox(textboxClearingItemReference,ICSPropertiesConfig.getTextboxInvalidClearingItemReferenceString());
		assertFalse(textboxClearingItemReference.getText()==null,"Only digits will be accepted");
		fillTextBox(textboxClearingItemReference,ICSPropertiesConfig.getTextboxClearingItemReferenceSpecialCharacters());
		assertFalse(textboxClearingItemReference.getText()==null,"Only digits will be accepted. Special characters will not be accepted");
		fillTextBox(textboxClearingItemReference, ICSPropertiesConfig.getValidClearingItemReferenceLeadingZero());
		assertFalse(textboxClearingItemReference.getText()==null,"Only digits will be accepted. Leading zeroes will not be accepted");
		return this;	
	}
	
	// Description : This method validates Search performance for Web UI	
	public SearchItemPage validatePerf() {
		//Scenario 1
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxSC, ICSPropertiesConfig.getSortCodePerf());
			fillTextBox(textboxAccount, ICSPropertiesConfig.getAccountPerf());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmountPerf());
			fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerialReferencePerf());
			clickElementEnter(ButtonSearch);
			long start = System.currentTimeMillis();
			pause(3500);
			System.out.println("**** start time : "+start);
			assertTrue(searchResultsInfo.isDisplayed(), "Search Results NOT displayed");
			long end= System.currentTimeMillis();
			System.out.println("**** End time : "+end);
			long totalTime= end-start;
			System.out.println("**** Total time taken for search- Scenario 1: "+totalTime);
			//Scenario 2
			clickImageClearingOnly();
			pause(500);
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxUII, ICSPropertiesConfig.getUniqueIdentifierPerf());
			clickElementEnter(ButtonSearch);
			//WebDriver driver;
			long start2 = System.currentTimeMillis();
			assertTrue(searchResultsInfo.isDisplayed(), "Search Results NOT displayed");
			long end2= System.currentTimeMillis();
			long totalTime2= end2-start2;
			System.out.println("Total time taken for search- Scenario 2: "+totalTime2);
			//Scenario 3 - 1 year range
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEndPerf());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerialReferencePerf());
			clickElementEnter(ButtonSearch);
			pause(4500);
			long start3 = System.currentTimeMillis();
			assertTrue(searchResultsInfo.isDisplayed(), "Search Results NOT displayed");
			long end3= System.currentTimeMillis();
			long totalTime3= end3-start3;
			System.out.println("Total time taken for search- Scenario 3: "+totalTime3);
			//Scenario 4  - 14days range
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
			fillTextBox(textboxProcDateEnd, ICSPropertiesConfig.getDatePickerValidProcDateEndPerf14Days());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			fillTextBox(textboxSerialReference, ICSPropertiesConfig.getSerialReferencePerf());
			Select sel=new Select(dropdownGnd);
			sel.selectByIndex(2);
			long start4 = System.currentTimeMillis();
			long end4= System.currentTimeMillis();
			long totalTime4= end4-start4;
			System.out.println("Total time taken for search- Scenario 4: "+totalTime4);
		return this;	
	}
	
	
	
	// Description : This method validates clear all/reset functionality on Main search page	
	public SearchItemPage clickClearALL() {	
		clickElementEnter(ButtonReset);
		pause(8000);
		return this;	
	}
	
	// Description : This method validates Single page functionality on the Item search results page	
	public SearchItemPage validateSinglePageDisplay() 
	{
	fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
	fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
	fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
	clickElementEnter(ButtonSearch);
	pause(9000);
	clickElementEnter(searchResultsInfo);
	String page = null;
	if(paginationGrid.isDisplayed()){
	if(activePaginationButton.isDisplayed()){
			page=activePaginationNthButton.getText();
			System.out.println("Page value is " +page);
			if(page.equals(1)){
				flag =true;
			}
		}
	}
	tempResult=	verifyTrue(page.equals("1") , "Search Results are not Displayed in Single Page");
	publishResults(tempResult,"Search results displayed in Single Page : "+activePaginationNthButton.isDisplayed()+"","Results should be displayed in Single Page", "Validation of single page Results screen");
	clickElementEnter(buttonBack);
	pause(3000);
	return this;
}
	
	// Description : This method validates Next page functionality on the Item search results page	
	public SearchItemPage validateNextPageButton() 
	{
		clickClearALL();
		pause(2000);
		String page = null;
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		if(paginationGrid.isDisplayed()){
				pause(200);
				clickElementEnter(NextPaginationButton);
				pause(2500);
				page=activePaginationNthButton.getText();
				if(page.equals(2)){
					flag =true;
				}
		}
		tempResult=	verifyTrue(page.equals("2") , "Next Page Button in Results are not Displayed");
		publishResults(tempResult,"Next Page Button in Results are Displayed : "+activePaginationNthButton.isEnabled()+"","Next Page Button in Results should be Displayed", "Validation of Next Page Button in Search Results");
		clickElementEnter(buttonBack);
		pause(2000);
		return this;
	}
	
	// Description : This method validates previous page navigation functionality on the Item search results page	
	public SearchItemPage validatePreviousPageButton() 
	{
		clickClearALL();
		String page = null;
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		if(paginationGrid.isDisplayed()){
				pause(200);
				clickElementEnter(PreviousPaginationButton);
				page=activePaginationNthButton.getText();
				System.out.println("Page value is " +page);
				if(page.equals(1)){
					flag =true;
				}
		}
		tempResult=	verifyTrue(page.equals("1") , "Previous Page Button in Results are not Displayed");
		publishResults(tempResult,"Previous Page Button in Results are Displayed : "+activePaginationNthButton.isEnabled()+"","Previous Page Button in Results should be Displayed", "Validation of Previous Page Button in Search Results");
		clickElementEnter(buttonBack);
		pause(2000);
		return this;
	}
	
	// Description : This method validates page navigation functionality on the Item search results page	
	public SearchItemPage validatePageNavigation() 
	{
		clickClearALL();
		String page = null;
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
		if(paginationGrid.isDisplayed()){
				pause(200);
				clickElementEnter(FourthPageButton);
				pause(2500);
				page=activePaginationNthButton.getText();
				System.out.println("Page value is " +page);
				if(page.equals(4)){
					flag =true;
				}
		}
		tempResult=	verifyTrue(page.equals("4") , "Page Navigation did not work..");
		publishResults(tempResult,"Page Navigated to respective page : "+activePaginationNthButton.isEnabled()+"","Page should be Navigated", "Validation of Page navigation");
		return this;
	}
	
	// Description : This method validates Add to out box functionality on the Item search results page	
	public SearchItemPage validateSelectRowAddToOutbox() 
	{
		clickClearALL();
		pause(1000);
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElementEnter(ButtonSearch);
		pause(9000);
		clickElementEnter(searchResultsInfo);
				String SortCode = firstRowSortCode.getText();
				System.out.println("SortCode is "+SortCode);
				clickElementSpace(OutboxcheckBox);
				pause(2200);
				clickElementEnter(AddToOutboxButton);
				pause(2300);
				clickElementEnter(PopUpOk);
				pause(2200);
				clickElementEnter(OutBoxMenu);
				pause(4000);
				String SortCodefromOutBox= getRowSortCodeFromOutBox.getText();
				System.out.println("Outbox SortCode is : "+SortCodefromOutBox);
				if(SortCode.equals(SortCodefromOutBox))
				{
					System.out.println("Selected item added to Outbox");
				}
		tempResult=	verifyTrue(SortCode.equals(SortCodefromOutBox) , "Selected Item not added to Outbox");
		publishResults(tempResult,"Selected item added to Outbox : "+SortCode.equals(SortCodefromOutBox)+"","Selected Item should be added to outbox", "Validation of Outbox");
		return this;
	}
	
	// Description : This method validates sub queries functionality on the Item search results page	
	public SearchItemPage validateSubQueries() 
	{
		pause(2000);
		clickElementEnter(ItemSearchQueryMenu);
		pause(4000);
		String NavTitleText = "Item Update History - Results";
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElementEnter(ButtonSearch);
		pause(9000);
		JavascriptExecutor ex=(JavascriptExecutor)driver;
		ex.executeScript("arguments[0].click();", SubQueryLink);
		pause(1000);
		verifyTrue(itemUpdateHistorySubQuery.isDisplayed(), "itemUpdateHistorySubQuery is not displayed");
		verifyTrue(itemStateHistorySubQuery.isDisplayed(), "itemStateHistorySubQuery is not displayed ");
		verifyTrue(DebitFraudItemsSubQuery.isDisplayed(), "DebitFraudItemsSubQuery is not displayed");
		verifyTrue(CreditFraudItemsSubQuery.isDisplayed(), "CreditFraudItemsSubQuery is not displayed ");
		pause(1000);	
		JavascriptExecutor exe=(JavascriptExecutor)driver;
		exe.executeScript("arguments[0].click();", itemUpdateHistorySubQuery);
		pause(2000);
		if(NavigationTitle.getText().equals("Item Update History - Results"))
		tempResult=	verifyTrue(NavigationTitle.getText().equals(NavTitleText),"Item Update History SubQuery Results are not displayed");
		publishResults(tempResult,NavigationTitle.getText(),NavTitleText,"Item Update History SubQuery Validation");
		pause(2000);
		return this;
	}
	
	// Description : This method validates Logout scenario 	
	public SearchItemPage validateLogout() throws AWTException 
	{
		pause(3000);
		clickElementEnter(UserNameLink);
		pause(2000);
		clickElementEnter(LogoutIcon);
		pause(2000);
		tempResult=	verifyTrue(LogoutIcon.isDisplayed(), "user should be able to log out");
		publishResults(tempResult,"User able to log out : "+LogoutIcon.isDisplayed()+"","User should be able to logout", "Validaiton for logout");
		for(String currentWindow : driver.getWindowHandles()){
			driver.switchTo().window(currentWindow);
		}
		pause(3000);
		System.out.println("The title is pop up : "+driver.getTitle());
		Robot r=new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.delay(1000);
			driver.quit();
			//driver.close();
			pause(8000);
		return this;
	}
	
	// Description : This method validates Horizontal scroll on the Item search results page	
	public SearchItemPage validateHorizontalScroll() 
	{
		clickElementEnter(ItemSearchQueryMenu);
		pause(2000);
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElementEnter(ButtonSearch);
		pause(9000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", AccountColumnName);
		pause(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CurrencyColumn);
		pause(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CollectingPIDColumnName);
		pause(3000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", NarrativeColumnName);
		pause(4000);
		tempResult=	verifyTrue(NarrativeColumnName.isDisplayed(), "user should be able to perform horizontal scroll");
		publishResults(tempResult,"User able to perform  horizontal scroll: "+NarrativeColumnName.isDisplayed()+"","User should be able to perform horizontal scroll", "Validaiton for Horizontal scroll");
		return this;
	}
	
	// Description : This method validates Vertical scroll on the Item search results page	
	public SearchItemPage validateVerticalScroll() 
	{
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record1);
		pause(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record5);
		pause(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record8);
		pause(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record12);
		pause(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record15);
		pause(2000);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", Record20);
		pause(2000);
		tempResult=	verifyTrue(Record20.isDisplayed(), "user should be able to perform vertical scroll");
		publishResults(tempResult,"User able to perform  vertical scroll: "+Record20.isDisplayed()+"","User should be able to perform vertical scroll", "Validaiton for vertical scroll");
		clickElementEnter(buttonBack);
		pause(2000);
		clickClearALL();
		pause(2000);
	return this;
	}
	
	// Description : This method validates Single click or selection of item on the Item search results page	
	public SearchItemPage validateSingleClick() 
	{
		fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
		fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
		fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
		clickElementEnter(ButtonSearch);
		pause(8000);
		JavascriptExecutor ex=(JavascriptExecutor)driver;
		ex.executeScript("arguments[0].click();", firstRowSortCode);
		pause(9000);
		if(Record1.getAttribute("class").contains("selected"))
		{
			System.out.println("Record is selected");
		}else{
			System.out.println("Record is Not selected");
		}
		tempResult=	verifyTrue(Record1.getAttribute("class").contains("selected"), "User should be able to select the record");
		publishResults(tempResult,"User able to select single record: "+Record1.getAttribute("class").contains("selected")+"","User should be able to select single record", "Validaiton for selecting single record");
	return this;
	}
	
	// Description : This method validates Tool tips for all columns on the Item search results page	
	public SearchItemPage validateToolTip() 
	{
		String ProcessingDateColumnToolTip = "The date that the item was processed through clearing";
		String SerialReferenceColumnToolTip = "Credit reference or cheque serial number";
		String SortCodeColumnToolTip = "Sort code of the item";
		String AccountColumnToolTip = "Account number of the item";
		String TransactionCodeColumnToolTip = "Transaction code of the item";
		String AmountColumnToolTip = "Value amount of the item";
		String ClearingDinColumnToolTip = "The unique identifier for the paper item";
		String BranchDinColumnToolTip = "IBDE or branch item sequence number";
		String PassNumberColumnToolTip = "Pass number";
		String RecordTypeColumnToolTip = "Record type";
		tempResult=	verifyTrue(ProcessingDateColumn.getAttribute("data-original-title").equals(ProcessingDateColumnToolTip), "User should be able to capture Processign date tool tip");
		publishResults(tempResult,"User able to capture tool tip for Processing date: "+ProcessingDateColumn.getAttribute("data-original-title").equals(ProcessingDateColumnToolTip)+"","User should be able to capture Processign date tool tip", "Validaiton for capturing Processign date tool tip");
		tempResult=	verifyTrue(SerialReferenceColumn.getAttribute("data-original-title").equals(SerialReferenceColumnToolTip), "User should be able to capture SerialReference tool tip");
		publishResults(tempResult,"User able to capture tool tip for Serial reference: "+SerialReferenceColumn.getAttribute("data-original-title").equals(SerialReferenceColumnToolTip)+"","User should be able to capture Processign date tool tip", "Validaiton for capturing Processign date tool tip");
		tempResult=	verifyTrue(SortCodeColumn.getAttribute("data-original-title").equals(SortCodeColumnToolTip), "User should be able to capture Sort Code tool tip");
		publishResults(tempResult,"User able to capture tool tip for Sort Code: "+SortCodeColumn.getAttribute("data-original-title").equals(SortCodeColumnToolTip)+"","User should be able to capture Sort Code tool tip", "Validaiton for capturing Sort Code tool tip");
		tempResult=	verifyTrue(AccountColumn.getAttribute("data-original-title").equals(AccountColumnToolTip), "User should be able to capture Account tool tip");
		publishResults(tempResult,"User able to capture tool tip for Account: "+AccountColumn.getAttribute("data-original-title").equals(AccountColumnToolTip)+"","User should be able to capture Account tool tip", "Validaiton for capturing Account tool tip");
		tempResult=	verifyTrue(TransactionCodeColumn.getAttribute("data-original-title").equals(TransactionCodeColumnToolTip), "User should be able to capture TransactionCode tool tip");
		publishResults(tempResult,"User able to capture tool tip for TransactionCode: "+TransactionCodeColumn.getAttribute("data-original-title").equals(TransactionCodeColumnToolTip)+"","User should be able to capture Transaction Code tool tip", "Validaiton for capturing Transaction Code tool tip");
		tempResult=	verifyTrue(AmountColumn.getAttribute("data-original-title").equals(AmountColumnToolTip), "User should be able to capture Amount Tool Tip ");
		publishResults(tempResult,"User able to capture tool tip for Amount: "+AmountColumn.getAttribute("data-original-title").equals(AmountColumnToolTip)+"","User should be able to capture Amount tool tip", "Validaiton for capturing Amount tool tip");
		tempResult=	verifyTrue(ClearingDinColumn.getAttribute("data-original-title").equals(ClearingDinColumnToolTip), "User should be able to capture Clearing Din tool tip");
		publishResults(tempResult,"User able to capture tool tip for Clearing Din: "+ClearingDinColumn.getAttribute("data-original-title").equals(ClearingDinColumnToolTip)+"","User should be able to capture Clearing Din tool tip", "Validaiton for capturing Clearing Din tool tip");
		tempResult=	verifyTrue(BranchIBDEDINColumn.getAttribute("data-original-title").equals(BranchDinColumnToolTip), "User should be able to capture Branch IBDEDIN tool tip");
		publishResults(tempResult,"User able to capture tool tip for Branch IBDEDIN: "+BranchIBDEDINColumn.getAttribute("data-original-title").equals(BranchDinColumnToolTip)+"","User should be able to capture Branch IBDE DIN tool tip", "Validaiton for capturing Branch IBDE DIN tool tip");
		tempResult=	verifyTrue(PassNumberColumn.getAttribute("data-original-title").equals(PassNumberColumnToolTip), "User should be able to capture Pass Number tip");
		publishResults(tempResult,"User able to capture tool tip for PassNumber: "+PassNumberColumn.getAttribute("data-original-title").equals(PassNumberColumnToolTip)+"","User should be able to capture Pass Number tool tip", "Validaiton for capturing Pass Number  tool tip");
		tempResult=	verifyTrue(RecordTypeColumn.getAttribute("data-original-title").equals(RecordTypeColumnToolTip), "User should be able to capture RecordType tool tip");
		publishResults(tempResult,"User able to capture tool tip for Record Type: "+RecordTypeColumn.getAttribute("data-original-title").equals(RecordTypeColumnToolTip)+"","User should be able to capture Record Type tool tip", "Validaiton for capturing Record Type tool tip");
		clickItemSearchQueryMenu();
		pause(3000);
	return this;
	}
	
	// Description : This method validates if out box button is displayed on the Item search results page	
	public SearchItemPage validateIsOutBoxPresent() 
	{
	fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
	fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
	fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
	clickElementEnter(ButtonSearch);
	pause(9000);
	tempResult=	verifyTrue(AddToOutboxButton.isDisplayed(), "Add to Outbox should be dsplayed");
	publishResults(tempResult,"Add to Outbox is dsplayed: "+AddToOutboxButton.isDisplayed()+"","Add to Outbox should be displayed", "Validating if Add to Outbox is displayed or not");
	pause(2000);
	return this;
	}
	
	// Description : This method validates Front and Rear Image for an Item on the Item search results page	
	public SearchItemPage validateFrontAndRearImage() 
	{
	clickElement(searchResultsInfo);
	JavascriptExecutor ex=(JavascriptExecutor)driver;
	ex.executeScript("arguments[0].click();", firstRowSortCode);
	pause(8000);
	tempResult=	verifyTrue(FrontImage.isDisplayed(), "Front Image should be dsplayed");
	publishResults(tempResult,"Frount Image is dsplayed: "+FrontImage.isDisplayed()+"","Front Image should be displayed", "Validating if Front Image is displayed or not");
	tempResult=	verifyTrue(RearImage.isDisplayed(), "RearImage should be dsplayed");
	publishResults(tempResult,"RearImage  is dsplayed: "+RearImage.isDisplayed()+"","RearImage should be displayed", "Validating if RearImage is displayed or not");
	pause(2000);
	clickItemSearchQueryMenu();
	pause(5000);
	return this;
	}
	
	// Description : This method validates NO image scenario on the item search results page	
	public SearchItemPage validateNoImage() 
	{
	pause(2000);
	fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart());
	fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
	fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
	clickElementEnter(ButtonSearch);
	pause(9000);
	tempResult=	verifyTrue(NoImage.isDisplayed(), "No Image should be displayed");
	publishResults(tempResult,"No Image is displayed: "+NoImage.isDisplayed()+"","No Image should be displayed", "Validating if No Image is displayed or not");
	pause(4000);
	clickItemSearchQueryMenu();
	pause(5000);
	return this;
	}
	
	//Description : This method performs verification of transaction content sub query
	public SearchItemPage validateTransactionContentSubQuery() 
	{
	String NavTitleText = "Transaction Set Contents - Results";
	clickItemSearchQueryMenu();
	clickImageClearingOnly();
	fillTextBox(textboxUII,ICSPropertiesConfig.TexboxValidInputUII());
	clickElementEnter(ButtonSearch);
	pause(9000);
	JavascriptExecutor ex=(JavascriptExecutor)driver;
	ex.executeScript("arguments[0].click();", SubQueryLink);
	pause(2000);
	verifyTrue(TsetContentsSubQuery.isDisplayed(), "TsetContentsSubQuery is not displayed ");
	pause(2000);
	JavascriptExecutor exe=(JavascriptExecutor)driver;
	exe.executeScript("arguments[0].click();", TsetContentsSubQuery);
	pause(9000);
	if(NavigationTitle.getText().equals(NavTitleText))
	tempResult=	verifyTrue(NavigationTitle.getText().equals(NavTitleText),"Transaction Set Contents - Results are not displayed");
	publishResults(tempResult,NavigationTitle.getText(),NavTitleText,"Transaction Set Contents SubQuery Validation");
	
	return this;
	}
		
	//Description : This method performs verification of debit fraud item details sub query
		public SearchItemPage validateDebitFraudItemDetailsSubQuery() 
		{
		clickItemSearchQueryMenu();
		clickImageClearingOnly();
		fillTextBox(textboxUII,ICSPropertiesConfig.TexboxValidInputUII());
		clickElementEnter(ButtonSearch);
		pause(9000);
		String NavTitleText = "Debit Fraud Item Details - Results";
		JavascriptExecutor ex=(JavascriptExecutor)driver;
		ex.executeScript("arguments[0].click();", SubQueryLink);
		pause(1000);
		verifyTrue(DebitFraudItemsSubQuery.isDisplayed(), "Debit Fraud Items SubQuery is not displayed ");
		pause(1000);
		JavascriptExecutor exe=(JavascriptExecutor)driver;
		exe.executeScript("arguments[0].click();", DebitFraudItemsSubQuery);
		pause(8000);
		if(NavigationTitle.getText().equals(NavTitleText))
		tempResult=	verifyTrue(NavigationTitle.getText().equals(NavTitleText),"Debit Fraud Item Details - Results are not displayed");
		publishResults(tempResult,NavigationTitle.getText(),NavTitleText,"Debit Fraud Item Details - Results Validation");
		return this;
		}
		
		
		public SearchItemPage validateNoOutputUII() 
		{
		clickItemSearchQueryMenu();
		clickImageClearingOnly();
		fillTextBox(textboxUII,ICSPropertiesConfig.TexboxNoOutputUII());
		clickElementEnter(ButtonSearch);
		pause(9000);
		String searchResults= searchResultsInfo.getText();
		String noOfEntries=searchResults.substring(18,19);
		clickElementEnter(searchResultsInfo);
		pause(2000);
		int entries= Integer.parseInt(noOfEntries);
		tempResult=	verifyTrue(entries==0,"Main search for output results for UII is not satisfied");
		publishResults(tempResult,"No. of search results for UII which does not exist in DB : "+entries+"",""+entries+" search results should be displayed for UII which is not present in DB", "No Search results validation for UII");
		return this;
		}
		
		
		
		//Description : This method performs verification of item state history sub query
			public SearchItemPage validateItemStateHistorySubQuery() 
			{
			clickItemSearchQueryMenu();
			clickImageClearingOnly();
			fillTextBox(textboxUII,ICSPropertiesConfig.TexboxValidInputUII());
			clickElementEnter(ButtonSearch);
			pause(9000);
			String NavTitleText = "Debit Fraud Item Details - Results";
			JavascriptExecutor ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", SubQueryLink);
			pause(1000);
			verifyTrue(itemStateHistorySubQuery.isDisplayed(), "Item State History SubQuery is not displayed ");
			pause(1000);
			JavascriptExecutor exe=(JavascriptExecutor)driver;
			exe.executeScript("arguments[0].click();", itemStateHistorySubQuery);
			pause(8000);
			if(NavigationTitle.getText().equals(NavTitleText))
			tempResult=	verifyTrue(NavigationTitle.getText().equals(NavTitleText),"Item State History - Results are not displayed");
			publishResults(tempResult,NavigationTitle.getText(),NavTitleText,"Item State History - Results Validation");
			pause(2000);
			clickItemSearchQueryMenu();
			clickImageClearingOnly();
			pause(2000);
			return this;
			}
			
	
			//Description : This method performs verification of main search output results for UII and UTSI
			public SearchItemPage validateMainSearchOutputResults() 
			{
			fillTextBox(textboxUII,ICSPropertiesConfig.TexboxNoOutputUII());
			clickElementEnter(ButtonSearch);
			pause(9000);
			String searchResults= searchResultsInfo.getText();
			String noOfEntries=searchResults.substring(18,19);
			clickElementEnter(searchResultsInfo);
			pause(2000);
			int entries= Integer.parseInt(noOfEntries);
			tempResult=	verifyTrue(entries==0,"Main search for output results for UII is not satisfied");
			publishResults(tempResult,"No. of search results for UII which does not exist in DB : "+entries+"",""+entries+" search results should be displayed for UII which is not present in DB", "No Search results validation for UII");
			clickElementEnter(buttonBack);
			pause(2000);
			clickShared();
			clickImageClearingOnly();
			fillTextBox(textboxUniqueTransactionSetItemIdentifier,ICSPropertiesConfig.TexboxNoOutputUTSI());
			clickElementEnter(ButtonSearch);
			pause(9000);
			searchResults= searchResultsInfoUTSI.getText();
			noOfEntries=searchResults.substring(18,19);
			clickElementEnter(searchResultsInfoUTSI);
			pause(2000);
			entries= Integer.parseInt(noOfEntries);
			tempResult=	verifyTrue(entries==0,"Main search for output results for UTSI is not satisfied");
			publishResults(tempResult,"No. of search results for UTSI which does not exist in DB : "+entries+"","+entries+ search results should not be displayed for UTSI which is not present in DB", "No Search results validation for UTSI");
			clickItemSearchQueryMenu();
			pause(2000);
			return this;
			}
			
			//Description : This method performs verification of main search output results for UII and Business date
			public SearchItemPage validateSearchOutputResultsForUII() 
			{
			clickImageClearingOnly();			
			fillTextBox(textboxUII,ICSPropertiesConfig.TexboxNoOutputUII());
			fillTextBox(textboxProcDate,ICSPropertiesConfig.getDatePickerValidProcDate());
			clickElementEnter(ButtonSearch);
			pause(9000);
			String searchResults= searchResultsInfo.getText();
			String noOfEntries=searchResults.substring(18,19);
			clickElementEnter(searchResultsInfo);
			pause(2000);
			int entries= Integer.parseInt(noOfEntries);
			tempResult=	verifyTrue(entries==0,"Main search for output results for UII & Business Date is not satisfied");
			publishResults(tempResult,"No. of search results for UII and Business Date which does not exist in DB : "+entries+"",""+entries+" search results should be displayed for UII & business date which is not present in DB", "No Search results validation for UII & Business date");
			return this;
			}
			
			
			//Description : This method performs search verification for UTSI
			public SearchItemPage validateSearchValidationForUTSI() 
			{
			/*textboxUII.clear();
			textboxProcDate.clear();*/
			clickItemSearchQueryMenu();
			clickImageClearingOnly();
			fillTextBox(textboxUII,ICSPropertiesConfig.TexboxNoOutputUII());
			pause(3000);
			String isDisabledUTSI = textboxUniqueTransactionSetItemIdentifier.getAttribute("disabled");
			String isDisabledSource = textboxSource.getAttribute("disabled");
			String isDisabledColPID = textboxCollectingParticipantIdentifier.getAttribute("disabled");
			String isDisabledPPID = textboxPayingParticipantIdentifier.getAttribute("disabled");
			String isDisabledColBranchLoc = textboxCollectingBranchLocation.getAttribute("disabled");
			tempResult=	verifyTrue(isDisabledUTSI.equals("true"),"UTSI text box is Enabled!!");
			publishResults(tempResult,"UTSI text box is disabled: "+isDisabledUTSI.equals("true")+"","UTSI text box  should be disabled", "UTSI text box option disabled Validation");
			tempResult=	verifyTrue(isDisabledSource.equals("true"),"Source text box is Enabled!!");
			publishResults(tempResult,"Source text box is disabled: "+isDisabledSource.equals("true")+"","Source text box  should be disabled", "Source text box option disabled Validation");
			tempResult=	verifyTrue(isDisabledColPID.equals("true"),"Collecting PID text box is Enabled!!");
			publishResults(tempResult,"Collecting PID text box is disabled: "+isDisabledColPID.equals("true")+"","Collecting PID text box  should be disabled", "Collecting PID text box option disabled Validation");
			tempResult=	verifyTrue(isDisabledPPID.equals("true"),"Paying PID text box is Enabled!!");
			publishResults(tempResult,"Paying PID text box is disabled: "+isDisabledPPID.equals("true")+"","Paying PID text box  should be disabled", "Paying PID text box option disabled Validation");
			tempResult=	verifyTrue(isDisabledColBranchLoc.equals("true"),"Coll Branch location text box is Enabled!!");
			publishResults(tempResult,"Coll Branch location  text box is disabled: "+isDisabledColBranchLoc.equals("true")+"","Coll Branch location  text box  should be disabled", "Coll Branch location  text box option disabled Validation");
			tempResult=	verifyTrue(isDisabledColBranchLoc.equals("true"),"Clearing DIN text box is Enabled!!");
			publishResults(tempResult,"Clearing DIN text box is disabled: "+isDisabledColBranchLoc.equals("true")+"","Clearing DIN text box  should be disabled", "Clearing DIN text box option disabled Validation");
			return this;
			}
		
			
			//Description : This method performs search verification for UTSI
			public SearchItemPage validateUIIPostingDate() 
			{
			clickItemSearchQueryMenu();
			clickImageClearingOnly();
			fillTextBox(textboxUII,ICSPropertiesConfig.TexboxNoOutputUII());
			fillTextBox(textboxPostingDate,ICSPropertiesConfig.TexboxNoOutputUII());
			
				
			return this;
			}
			
			
			//Description : This method performs out box validation on all pages
			public SearchItemPage validateOutboxMenuOnAllPages() 
			{
			clickItemSearchQueryMenu();
			String isDisabledOutboxMenu = OutBoxMenuDisabled.getAttribute("disabled");
			tempResult=	verifyTrue(OutBoxMenu.isDisplayed(),"Outbox menu is not displayed!!");
			publishResults(tempResult,"Outbox menu is displayed: "+OutBoxMenu.isDisplayed()+"","Outbox menu should be displayed", "validating if outbox menu is displayed");
			tempResult=	verifyTrue(isDisabledOutboxMenu.equals("true"),"Outbox menu is is Enabled!!");
			publishResults(tempResult,"Outbox menu is disabled: "+isDisabledOutboxMenu.equals("true")+"","Outbox menu should be disabled", "Validating if outbox menu is disabled");
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			clickElementEnter(searchResultsInfo);
			clickElementSpace(OutboxcheckBox);
			pause(2200);
			clickElementEnter(AddToOutboxButton);
			pause(2300);
			clickElementEnter(PopUpOk);
			pause(2200);
			String noOfItemsAddedToOutbox="1";
			String outboxItem =OutboxNoOfItems.getAttribute("expanded-text");
			String noOfOutboxItems = outboxItem.substring(outboxItem.indexOf("(")+1, outboxItem.indexOf(")"));
			tempResult=	verifyTrue(noOfOutboxItems.equals(noOfItemsAddedToOutbox),"No. of outbox items does not match with items added to outbox!!");
			publishResults(tempResult,"No. of outbox items match with items added to outbox on search results page: "+noOfOutboxItems.equals(noOfItemsAddedToOutbox)+"","Outbox menu should be displayed with no of items added", "validating No. of outbox items match with items added to outbox on Items results page");
			JavascriptExecutor ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", SubQueryLink);
			pause(2000);
			ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", itemUpdateHistorySubQuery);
			pause(9000);
			tempResult=	verifyTrue(noOfOutboxItems.equals(noOfItemsAddedToOutbox),"No. of outbox items does not match with items added to outbox!!");
			publishResults(tempResult,"No. of outbox items match with items added to outbox on item update history page: "+noOfOutboxItems.equals(noOfItemsAddedToOutbox)+"","Outbox menu should be displayed with no of items added", "validating No. of outbox items match with items added to outbox on item update history page");
			clickItemSearchQueryMenu();
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", SubQueryLink);
			pause(2000);
			ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", itemStateHistorySubQuery);
			pause(9000);
			tempResult=	verifyTrue(noOfOutboxItems.equals(noOfItemsAddedToOutbox),"No. of outbox items does not match with items added to outbox!!");
			publishResults(tempResult,"No. of outbox items match with items added to outbox on item state history page: "+noOfOutboxItems.equals(noOfItemsAddedToOutbox)+"","Outbox menu should be displayed with no of items added on item state history page", "validating No. of outbox items match with items added to outbox on item state history page");
			clickItemSearchQueryMenu();
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", SubQueryLink);
			pause(2000);
			ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();",DebitFraudItemsSubQuery );
			pause(9000);
			tempResult=	verifyTrue(noOfOutboxItems.equals(noOfItemsAddedToOutbox),"No. of outbox items does not match with items added to outbox!!");
			publishResults(tempResult,"No. of outbox items match with items added to outbox on debit/credit fraud items page: "+noOfOutboxItems.equals(noOfItemsAddedToOutbox)+"","Outbox menu should be displayed with no of items added on debit/credit fraud items page:", "validating No. of outbox items match with items added to outbox on debit/credit fraud items page");
			clickItemSearchQueryMenu();
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", SubQueryLink);
			pause(2000);
			ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();",TsetContentsSubQuery );
			pause(9000);
			tempResult=	verifyTrue(noOfOutboxItems.equals(noOfItemsAddedToOutbox),"No. of outbox items does not match with items added to outbox!!");
			publishResults(tempResult,"No. of outbox items match with items added to outbox on Tset content page: "+noOfOutboxItems.equals(noOfItemsAddedToOutbox)+"","Outbox menu should be displayed with no of items added on Tset content page:", "validating No. of outbox items match with items added to outbox on Tset content page");
			return this;
			}
			
			
			//Description : This method validates if Out box items are decremented
			public SearchItemPage validateOutboxMenuItemsDecrement() 
			{
			pause(2000);
			clickElementEnter(OutBoxMenu);
			pause(4000);	
			clickElementSpace(OutboxcheckBoxOutboxPage);
			pause(2200);
			clickElementEnter(Remove);
			pause(2000);
			clickElementEnter(PopUpOk);
			pause(4200);
			String isDisabledOutboxMenu = OutBoxMenuDisabled.getAttribute("disabled");
			tempResult=	verifyTrue(isDisabledOutboxMenu.equals("true"),"Outbox menu items not decremented !");
			publishResults(tempResult,"Outbox menu items decremented: "+isDisabledOutboxMenu.equals("true")+"","Outbox menu items should be decremented", "Validating if outbox menu items are decremented");
			return this;
			}
			
			
			//Description : This method validates Entity State Description
			public SearchItemPage validateEntityStateDescription() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
			{
			String query="";
			String ESDB = null,ESDescDB = null;
			clickItemSearchQueryMenu();
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			String getESDescriptionOnWeb= firstRowEntityStateDescription.getText();
			String getESDesc=getESDescriptionOnWeb.substring(6, 19);
			String getES=getESDescriptionOnWeb.substring(0, 4);
			query = "select [EntityState] ,[Description] FROM [Short_Term_Archive_R2].[ReferenceData].[RTPPaidEntityStates]"
					+ " where EntityState='"+getES+"'";
			ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(getIAValueFromDataSheet("server"), getIAValueFromDataSheet("db_r2"), query);
			try {
				while(rs.next())
				{
					 ESDB = rs.getString("EntityState");
					 ESDescDB = rs.getString("Description");
				}
			tempResult=	verifyTrue(getESDesc.trim().equals(ESDescDB),"Entity Description mapping is not correct !");
			publishResults(tempResult,"Entity Description mapping is correct: "+getESDesc.trim().equals(ESDescDB)+"","Entity Description mapping in results page should be as per ReferenceData.RTPPaidEntityStates", "Validating if Entity Description mapping in results page is as per ReferenceData.RTPPaidEntityStates");
			tempResult=	verifyTrue(getES.trim().equals(ESDB),"Entity State mapping is not correct !");
			publishResults(tempResult,"Entity State mapping is correct: "+getES.trim().equals(ESDB)+"","Entity State mapping in results page should be as per ReferenceData.RTPPaidEntityStates", "Validating if Entity State mapping in results page is as per ReferenceData.RTPPaidEntityStates");
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return this;
			}
		
			
			//Description : This method validates Invalid credit fraud item details
			public SearchItemPage validateInvalidCreditFraudItemDetails()  
			{
			clickItemSearchQueryMenu();
			clickImageClearingOnly();
			fillTextBox(textboxUII,ICSPropertiesConfig.TexboxNoOutputUII());
			clickElementEnter(ButtonSearch);
			pause(9000);
			String searchResults= searchResultsInfo.getText();
			String noOfEntries=searchResults.substring(18,19);
			clickElementEnter(searchResultsInfo);
			pause(2000);
			int entries= Integer.parseInt(noOfEntries);
			tempResult=	verifyTrue(entries==0,"The search result does not return empty result set");
			publishResults(tempResult,"The search result returns empty result set : "+noOfEntries.equals(entries)+"", "search results should return empty result set when passing invalid credit fraud item details", "Validating if search result returns empty result set for invalid credit fraud item details");
			clickElementEnter(buttonBack);
			pause(2000);
			return this;
			}
			
			
			//Description : This method performs verification of credit fraud item details sub query
			public SearchItemPage validateCreditFraudItemDetailsSubQuery() 
			{
			clickItemSearchQueryMenu();
			fillTextBox(textboxProcDate, ICSPropertiesConfig.getDatePickerValidProcDateStart1());
			fillTextBox(textboxAmount, ICSPropertiesConfig.getAmount1());
			fillTextBox(textboxAmountEnd, ICSPropertiesConfig.getAmountEnd());
			clickElementEnter(ButtonSearch);
			pause(9000);
			String NavTitleText = "Credit Fraud Item Details - Results";
			JavascriptExecutor ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", SubQueryLink);
			pause(2000);
			verifyTrue(CreditFraudItemsSubQuery.isDisplayed(), "credit Fraud Items SubQuery is not displayed ");
			pause(2000);
			JavascriptExecutor exe=(JavascriptExecutor)driver;
			exe.executeScript("arguments[0].click();", CreditFraudItemsSubQuery);
			pause(8000);
			if(NavigationTitle.getText().equals(NavTitleText))
			tempResult=	verifyTrue(NavigationTitle.getText().equals(NavTitleText),"Credit Fraud Item Details - Results are not displayed");
			publishResults(tempResult,NavigationTitle.getText(),NavTitleText,"Credit Fraud Item Details - Results Validation");
			clickItemSearchQueryMenu();
			return this;
			}	
			
			//Description : This method performs verification of webui Tab details
			public SearchItemPage validateWebUiTab() 
			{
			String expectedtitle = "Lloyds Image Archive";
			clickItemSearchQueryMenu();
			tempResult=	verifyTrue(driver.getTitle().equals(expectedtitle),"Title does not display bank details");
			publishResults(tempResult,driver.getTitle(),expectedtitle,"Web UI Tab Title Validation");
			clickRepresentQueryMenu();
			tempResult=	verifyTrue(driver.getTitle().equals(expectedtitle),"Title does not display bank details");
			publishResults(tempResult,driver.getTitle(),expectedtitle,"Web UI Tab Title Validation");
			clickPreviousQueryMenu();
			tempResult=	verifyTrue(driver.getTitle().equals(expectedtitle),"Title does not display bank details");
			publishResults(tempResult,driver.getTitle(),expectedtitle,"Web UI Tab Title Validation");
			return this;
			}		
			
			//Description : This method performs verification of Item capture history sub query
			public SearchItemPage validateItemCaptureHistorySubQuery() 
			{
			clickImageClearingOnly();
			fillTextBox(textboxUII, getIAValueFromDataSheet("ValidInputUII"));
			clickElementEnter(ButtonSearch);
			pause(9000);
			String NavTitleText = "Item Capture History - Results";
			JavascriptExecutor ex=(JavascriptExecutor)driver;
			ex.executeScript("arguments[0].click();", SubQueryLink);
			pause(2000);
			verifyTrue(ItemCaptureHistorySubQuery.isDisplayed(), "Item Capture History SubQuery is not displayed ");
			pause(2000);
			JavascriptExecutor exe=(JavascriptExecutor)driver;
			exe.executeScript("arguments[0].click();", ItemCaptureHistorySubQuery);
			pause(8000);
			if(NavigationTitle.getText().equals(NavTitleText))
			tempResult=	verifyTrue(NavigationTitle.getText().equals(NavTitleText),"Item Capture History - Results are not displayed");
			publishResults(tempResult,NavigationTitle.getText(),NavTitleText,"Item Capture History - Results Validation");
			clickItemSearchQueryMenu();
			return this;
			}	
			
			
	}
