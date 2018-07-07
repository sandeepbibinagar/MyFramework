package com.ics.tests.ia;

import org.testng.annotations.Test;

import com.ics.ia.db.testScenarios.WebservicesValidation;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

public class RegressionSuite_WebServices_IA extends ICSAutomationCommonSetup{
	
	public static String query="";
	public static String server =getIAValueFromDataSheet("server");
	public static String db = getIAValueFromDataSheet("db_r2");
	public static boolean flag;
	public static String getWebServiceType ="IA.BusinessServiceR2/api";
	public static String getItemSearchWebServiceName ="iCSItemSearchQuery";
	public static String getImageQueryWebServiceName ="ICSGetImageQuery";
	public static String getRepairedItemDetailsQueryWebServiceName ="ICSRepairedItemDetailsQuery";
	public static String getDefaultedItemDetailsQueryWebServiceName ="ICSDefaultedItemDetailsQuery";
	public static String getDebitFraudItemDetailsQueryWebServiceName ="ICSDebitFraudItemDetailsQuery";
	public static String getCreditFraudItemDetailsQueryWebServiceName ="ICSCreditFraudItemDetailsQuery";
	public static String getDebitDuplicateItemDetailsQueryWebServiceName ="ICSDebitDuplicateItemDetailsQuery";
	
	/*
	 * Test Case: 70266
	 * Description: Tests Item Search Query web service
	 * Expected:  Should give expected output as per inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 29/11/2017  
	 */
	@Test(priority=1) 
	public void testICSItemSearchQuery(){
	try {
		WebservicesValidation.runWebLoader_ItemSearchQuery_UII(getWebServiceType,getItemSearchWebServiceName);
		//runWebLoader_ItemSearchQuery_BusinessDateAccount(getWebServiceType,getItemSearchWebServiceName);
		} catch (Exception e) {
		e.printStackTrace();
		}
}
	
	/*
	 * Test Case: 70264
	 * Description: Tests Image Query web service
	 * Expected:  Should give expected output as per inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 29/11/2017  
	 */
	@Test(priority=2) 
	public void testICSGetImageQuery(){
	try {
		WebservicesValidation.runWebLoader_GetImageQuery(getWebServiceType,getImageQueryWebServiceName);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	
	/*
	 * Test Case: 702XX
	 * Description: Tests Repaired Item Details Query web service
	 * Expected:  Should give expected output as per inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 04/12/2017  
	 */
	@Test(priority=3) 
	public void testICSRepairedItemDetailsQuery(){
	try {
		WebservicesValidation.runWebLoader_GetRepairedItemDetailsQuery(getWebServiceType,getRepairedItemDetailsQueryWebServiceName);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	
	/*
	 * Test Case: 702XX
	 * Description: Tests Defaulted Item Details Query web service
	 * Expected:  Should give expected output as per inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 04/12/2017  
	 */
	@Test(priority=4) 
	public void testICSDefaultedItemDetailsQuery(){
	try {
		WebservicesValidation.runWebLoader_GetDefaultedItemDetailsQuery(getWebServiceType,getDefaultedItemDetailsQueryWebServiceName);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	
	/*
	 * Test Case: 702XX
	 * Description: Tests Defaulted Item Details Query web service
	 * Expected:  Should give expected output as per inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 04/12/2017  
	 */
	@Test(priority=5) 
	public void testICSDebitFraudItemDetailsQuery(){
	try {
		WebservicesValidation.runWebLoader_DebitFraudItemDetailsQuery(getWebServiceType,getDebitFraudItemDetailsQueryWebServiceName);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
		
	
	/*
	 * Test Case: 702XX
	 * Description: Tests Credit Fraud Item Details Query web service
	 * Expected:  Should give expected output as per inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 05/12/2017  
	 */
	//@Test(priority=6) 
	public void testICSCreditFraudItemDetailsQuery(){
	try {
		WebservicesValidation.runWebLoader_CreditFraudItemDetailsQuery(getWebServiceType,getCreditFraudItemDetailsQueryWebServiceName);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	/*
	 * Test Case: 702XX
	 * Description: Tests Item Capture History Query web service
	 * Expected:  Should give expected output as per inputs provided.
	 * Author: Sandeep Bibinagar
	 * Date: 05/12/2017  
	 */
	@Test(priority=7) 
	public void testICSItemCaptureHistoryQuery(){
	try {
		WebservicesValidation.runWebLoader_ItemCaptureHistoryQuery(getWebServiceType,getCreditFraudItemDetailsQueryWebServiceName);
		} catch (Exception e) {
		e.printStackTrace();
		}
	}
	
}
