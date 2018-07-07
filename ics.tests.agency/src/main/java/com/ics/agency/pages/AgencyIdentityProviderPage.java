package com.ics.agency.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;


public class AgencyIdentityProviderPage extends ICSPageUtilis{
	
	public AgencyIdentityProviderPage(WebDriver driver) {
		super(driver);
	}
	public AgencyIdentityProviderPage(WebDriver driver, String url) {
		super(driver, url);
	}
	
	@FindBy(id = "txtRole")
	private static WebElement roleField;

	@FindBy(id = "btnLogin")
	private static WebElement loginField;
	
	@FindBy(xpath="//*[contains(@text()='Agency ID')]")
	private static WebElement roleLabel;

	@FindBy(id = "txtAgyID")
	private static WebElement agencyID;	

	@FindBy(id = "txtbranding")
	private static WebElement brandingID;	

	@FindBy(id = "txtuserId")
	private static WebElement userID;	
	
	@FindBy(id = "txtFName")
	private static WebElement firstName;	
	
	@FindBy(id = "txtLname")
	private static WebElement lastName;
		
	@FindBy(id = "Submit")
	private static WebElement submit;	
	
	@FindBy(id = "overridelink")
	private static WebElement securityOverrideLink;
	
	public AgencyIdentityProviderPage fillRolefield(String roleName)
	{
		fillTextBox(agencyID, getAgencyValueFromDataSheet("agencyID"));
		fillTextBox(roleField, roleName);
		fillTextBox(brandingID, getAgencyValueFromDataSheet("agencyBranding"));
		return this;
	}
	
	public AgencyIdentityProviderPage fillApproverNameID()
	{
		//ClickOnsecuritylink();
		fillRolefield(ICSPropertiesConfig.getInclearingApprover());			
		fillTextBox(firstName, getAgencyValueFromDataSheet("agencyApproverFirstName"));
		fillTextBox(lastName, getAgencyValueFromDataSheet("agencyApproverLastName"));
		fillTextBox(userID, getAgencyValueFromDataSheet("agencyApproverUserId"));
		clickLoginButton();
		return this;
	}
	
	public AgencyIdentityProviderPage fillSuperUserApproverNameID()
	{
		//ClickOnsecuritylink();
		fillRolefield(ICSPropertiesConfig.getSuperUser());			
		fillTextBox(firstName, getAgencyValueFromDataSheet("agencyApproverFirstName"));
		fillTextBox(lastName, getAgencyValueFromDataSheet("agencyApproverLastName"));
		fillTextBox(userID, getAgencyValueFromDataSheet("agencyApproverUserId"));
		clickLoginButton();
		return this;
	}
	
	public OpenAgencyPortalPage clickLoginButton()
	{
		clickElement(loginField);
		return new OpenAgencyPortalPage(driver);
	}
	
	public OpenAgencyPortalPage clickSubmitButton()
	{
		clickElement(submit);
		return new OpenAgencyPortalPage(driver);
	}

	public AgencyIdentityProviderPage assertIsLabelsPresence()
	{
		assertEquals(roleLabel.getText(), "Role(Enter only 3 characters)", "true");
		return this;
	}

	public OpenAgencyPortalPage fillAgencyIdValidations(String validations)
	{
		if(validations.contains(ICSPropertiesConfig.getAgencyAlphaNumeric()))
		{
			fillTextBox(agencyID, ICSPropertiesConfig.getAgencyAlphaNumeric());
			clickElement(loginField);
		}
		if(validations.contains(ICSPropertiesConfig.getAgencyMoreThanThirty()))
		{
			fillTextBox(agencyID, ICSPropertiesConfig.getAgencyMoreThanThirty());
			clickElement(loginField);
		}
		return new OpenAgencyPortalPage(driver);
	}
	public OpenAgencyPortalPage fillBrandingIdValidations()
	{
		fillTextBox(brandingID, ICSPropertiesConfig.getBrandingIdSingleChar());
		clickElement(loginField);
		return new OpenAgencyPortalPage(driver);
	}
	public OpenAgencyPortalPage fillBrandingIdMoreValidations()
	{
		fillTextBox(brandingID, ICSPropertiesConfig.getBrandingIdMoreChars());
		clickElement(loginField);
		return new OpenAgencyPortalPage(driver);
	}
	public OpenAgencyPortalPage fillBrandingIdNumericValidations()
	{
		fillTextBox(brandingID, ICSPropertiesConfig.getBrandingIdNumericChars());
		clickElement(loginField);
		return new OpenAgencyPortalPage(driver);
	}
	public OpenAgencyPortalPage fillBrandingIdEmptyValidations()
	{
		fillTextBox(brandingID, ICSPropertiesConfig.getBrandingIdNumericChars());
		clickElement(loginField);
		return new OpenAgencyPortalPage(driver);
	}

	public OpenAgencyPortalPage fillUserIdValidations(String validations)
	{
		if(validations.contains(ICSPropertiesConfig.getAgencyAlphaNumeric()))
		{
			fillTextBox(userID, ICSPropertiesConfig.getAgencyAlphaNumeric());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		if(validations.contains(ICSPropertiesConfig.getAgencyMoreThanThirty()))
		{
			fillTextBox(userID, ICSPropertiesConfig.getAgencyMoreThanThirty());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		if(validations.contains(ICSPropertiesConfig.getagencyIdEmptySpaces()))
		{
			fillTextBox(userID, ICSPropertiesConfig.getagencyIdEmptySpaces());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		return new OpenAgencyPortalPage(driver);
	}

	public OpenAgencyPortalPage fillFirstNameValidations(String validations)
	{
		if(validations.contains(ICSPropertiesConfig.getAgencyAlphaNumeric()))
		{
			fillTextBox(firstName, ICSPropertiesConfig.getAgencyAlphaNumeric());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		if(validations.contains(ICSPropertiesConfig.getAgencyMoreThanThirty()))
		{
			fillTextBox(firstName, ICSPropertiesConfig.getAgencyMoreThanThirty());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		if(validations.contains(ICSPropertiesConfig.getagencyIdEmptySpaces()))
		{
			fillTextBox(firstName, ICSPropertiesConfig.getagencyIdEmptySpaces());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		return new OpenAgencyPortalPage(driver);
	}
	
	public OpenAgencyPortalPage fillLastNameValidations(String validations)
	{
		if(validations.contains(ICSPropertiesConfig.getAgencyAlphaNumeric()))
		{
			fillTextBox(lastName, ICSPropertiesConfig.getAgencyAlphaNumeric());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		if(validations.contains(ICSPropertiesConfig.getAgencyMoreThanThirty()))
		{
			fillTextBox(lastName, ICSPropertiesConfig.getAgencyMoreThanThirty());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		if(validations.contains(ICSPropertiesConfig.getagencyIdEmptySpaces()))
		{
			fillTextBox(lastName, ICSPropertiesConfig.getagencyIdEmptySpaces());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		return new OpenAgencyPortalPage(driver);
	}
	public OpenAgencyPortalPage fillRoleValidations(String validations)
	{
		if(validations.contains(ICSPropertiesConfig.getAgencyAlphaNumeric()))
		{
			fillTextBox(roleField, ICSPropertiesConfig.getAgencyAlphaNumeric());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		if(validations.contains(ICSPropertiesConfig.getAgencyNumericValues()))
		{
			fillTextBox(roleField, ICSPropertiesConfig.getAgencyNumericValues());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		if(validations.contains(ICSPropertiesConfig.getroleOtherthanChars()))
		{
			fillTextBox(roleField, ICSPropertiesConfig.getroleOtherthanChars());
			clickElement(loginField);
			return new OpenAgencyPortalPage(driver);
		}
		return new OpenAgencyPortalPage(driver);
	}
	
	public AgencyIdentityProviderPage ClickOnsecuritylink() {
	//	driver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,"0"));
		clickElement(securityOverrideLink);		
		return this;
	}

}
