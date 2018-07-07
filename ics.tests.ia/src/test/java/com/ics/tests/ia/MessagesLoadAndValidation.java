/*  
<Copyright file="ImageAndDataLoad.java" company="iPSL">
Copyright © iPSL 2017 All rights are reserved.
Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
is prohibited without the prior written permission of the copyright owner.
</copyright> 
*/
 
/*
/************************** Module Header ******************************
 * Module Name : Message Loading and DB Validations
 * Date : 18/04/2017
 * Created By : Sandeep Bibinagar
 * Description : This file contains all the test scripts w.r.t Image and Data load & XSD Validations for different Message types
 * 
****************** AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 13/05/2017		
*********************************************************************** 
Description : Updating file as more work flows/test scripts are added 
***********************************************************************
******************** AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 12/06/2017		
*********************************************************************** 
Description : adding XSD validations for the messages 
***********************************************************************
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 22/06/2017		
*********************************************************************** 
Description : Updating file as more work flows/test scripts are added 
***********************************************************************
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 29/06/2017		
**********************************************************************************************************
Description : Added posting and case management related messages & DB validations
***********************************************************************************************************
******************** AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 30/08/2017		
**********************************************************************************************************
Description : Added FraudAdditional Info for Message 01MA03
***********************************************************************************************************
******************** AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
**********************************************************************************************************
 Description : added functionality to fetch test data from excel sheet
 ******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
 Description : Updating class as per java coding standards
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 11/09/2017		
 Description : Adding REMA01XSD and REMA01 message as part of the regression suite
********************************************************************************************************** 
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/10/2017		
 Description : Adding PEMA02XSD and PEMA02 message as part of the regression suite

******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 08/10/2017		
 Description : Adding 15MA01XSD and 15MA01 message as part of the regression suite
********************************************************************************************************** 
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 12/10/2017		
 Description : Adding 16MA01XSD and 16MA01 message as part of the regression suite
********************************************************************************************************** */

package com.ics.tests.ia;
import java.io.File;
import java.util.Random;
import org.apache.commons.lang.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.Test;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;
import com.microsoft.windowsazure.core.utils.Base64;
import net.sourceforge.htmlunit.corejs.javascript.UintMap;
import com.ics.ia.db.testScenarios.FetchXMLNodeValues;


/***************************************************************************************************************************************/
/* Purpose:	 This class contains all test scripts w.r.t Image and Data load & XSD Validations for different Message types  
 * All XML Files, XSD's & SQL files are placed in Project location src/main/resources/testData
 * @Author= Sandeep Bibinagar 
***************************************************************************************************************************************/

public class MessagesLoadAndValidation extends ICSAutomationCommonSetup{
	public static String server =getIAValueFromDataSheet("server");
	public static String db = getIAValueFromDataSheet("db_r2");
	public static String templateFilePath = getIADataFromPathXml();
	public static String xml01MA01 = getIAValueFromDataSheet("01MA01xml");
	public static String xml01MA02 = getIAValueFromDataSheet("01MA02xml");
	public static String xml01MA03 = getIAValueFromDataSheet("01MA03xml");
	public static String xml01MA04 = getIAValueFromDataSheet("01MA04xml");
	public static String xml01MA05 = getIAValueFromDataSheet("01MA05xml");
	public static String xml01MA06 = getIAValueFromDataSheet("01MA06xml");
	public static String xml02MA01 = getIAValueFromDataSheet("02MA01xml");
	public static String xml03MA01 = getIAValueFromDataSheet("03MA01xml");
	public static String xml03MA02 = getIAValueFromDataSheet("03MA02xml");
	public static String xml04MA01 = getIAValueFromDataSheet("04MA01xml");
	public static String xml05MA01 = getIAValueFromDataSheet("05MA01xml");
	public static String xml05MA02 = getIAValueFromDataSheet("05MA02xml");
	public static String xml05MA03 = getIAValueFromDataSheet("05MA03xml");
	public static String xml06MA01 = getIAValueFromDataSheet("06MA01xml");
	public static String xml06MA02 = getIAValueFromDataSheet("06MA02xml");
	public static String xml06MA03 = getIAValueFromDataSheet("06MA03xml");
	public static String xml06MA04 = getIAValueFromDataSheet("06MA04xml");
	public static String xml07MA01 = getIAValueFromDataSheet("07MA01xml");
	public static String xml08MA01 = getIAValueFromDataSheet("08MA01xml");
	public static String xml09MA01 = getIAValueFromDataSheet("09MA01xml");
	public static String xml11MA01 = getIAValueFromDataSheet("11MA01xml");
	public static String xml11MA02 = getIAValueFromDataSheet("11MA02xml");
	public static String xml11MA03 = getIAValueFromDataSheet("11MA03xml");
	public static String xml12MA01 = getIAValueFromDataSheet("12MA01xml");
	public static String xml13MA01 = getIAValueFromDataSheet("13MA01xml");
	public static String xml13MA02 = getIAValueFromDataSheet("13MA02xml");
	public static String xml13MA04 = getIAValueFromDataSheet("13MA04xml");
	public static String xml13MA05 = getIAValueFromDataSheet("13MA05xml");
	public static String xmlPRMA01 = getIAValueFromDataSheet("PRMA01xml");
	public static String xmlPTMA01 = getIAValueFromDataSheet("PTMA01xml");
	public static String xmlPEMA01 = getIAValueFromDataSheet("PEMA01xml");
	public static String xmlPEMA02 = getIAValueFromDataSheet("PEMA02xml");
	public static String xmlCMMA01 = getIAValueFromDataSheet("CMMA01xml");
	public static String xmlCMMA02 = getIAValueFromDataSheet("CMMA02xml");
	public static String xmlREMA01 = getIAValueFromDataSheet("REMA01xml");
	public static String xml15MA01 = getIAValueFromDataSheet("15MA01xml");
	public static String xml16MA01 = getIAValueFromDataSheet("16MA01xml");
	public static String query="";
	public static String actual;
	boolean flag=true;
	
	
	/*
	 * Test Case: TC:77337
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/08/2017  
	 */
	@Test(priority=1) //01MA01
	public void testcase77337() throws Exception	
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("01MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 01MA01 message against XSD");
		}catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
	}
	
	
	/*
	 * Test Case: TC:81709
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=2) //01MA01
	public void testcase81709() throws Exception
	{	
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("01MA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml01MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml01MA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml01MA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_01MA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml01MA01);	
			FetchXMLNodeValues.getJobHeaderAttributeValues(xml01MA01);
			FetchXMLNodeValues.getBatchHeaderAttributeValues(xml01MA01);
			FetchXMLNodeValues.getCaptureItemHeaderAttributeValues(xml01MA01);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
		
	/*
	 * Test Case: TC:74605
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=3) //01MA02
	public void testcase74605() throws Exception	
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("01MA02xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 01MA02 message against XSD");
		}catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Test Case: TC:74477
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=4) //01MA02
	public void testcase74477() throws Exception
	{	
		try{
			
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("01MA02sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml01MA02);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml01MA02);
			FetchXMLNodeValues.getTagwiseDataForItemHeader(xml01MA02);
			FetchXMLNodeValues.getTagwiseDataForCodeLinemHeader(xml01MA02);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml01MA02);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_01MA02"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml01MA02);	
			FetchXMLNodeValues.getItemHeaderAttributeValues(xml01MA02);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
			
	/*
	 * Test Case: TC:74196
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */	
	@Test(priority=5) //01MA03
	public void testcase74196() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("01MA03xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 01MA03 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	
	/*
	 * Test Case: TC:74195
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 28/04/2017  
	 */
	@Test(priority=6) //01MA03
	public void testcase74195() throws Exception
	{	
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("01MA03sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml01MA03);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml01MA03);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml01MA03);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_01MA03"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml01MA03);	
			FetchXMLNodeValues.getFraudStatusAttributeValues(xml01MA03);
			FetchXMLNodeValues.getFraudResultsAttributeValues(xml01MA03);
			FetchXMLNodeValues.getFraudAdditionalInfoAttributeValues(xml01MA03);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
	/*
	 * Test Case: TC:74418
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=7) //01MA04
	public void testcase74418() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("01MA04xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 01MA04 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:74415
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 27/04/2017  
	 */
	@Test(priority=8) //01MA04  
	public void testcase74415() throws Exception
	{
		try{
			
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("01MA04sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml01MA04);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml01MA04);
			FetchXMLNodeValues.getTagwiseDataForItemHeader(xml01MA04);
			FetchXMLNodeValues.getTagwiseDataForCodeLinemHeader(xml01MA04);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml01MA04);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_01MA04"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml01MA04);	
			FetchXMLNodeValues.getItemHeaderAttributeValues(xml01MA04);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
	/*
	 * Test Case: TC:74421
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=9) //01MA05
	public void testcase74421() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("01MA05xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 01MA05 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:74420
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 27/04/2017  
	 */
	@Test(priority=10) //01MA05
	public void testcase74420() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("01MA05sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml01MA05);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml01MA05);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml01MA05);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_01MA05"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml01MA05);	
			}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
		}
	
	/*
	 * Test Case: TC:76053
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=11) //01MA06
	public void testcase76053() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("01MA06xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 01MA06 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	
	/*
	 * Test Case: TC:76054
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 28/04/2017  
	 */
	
	@Test(priority=12) //AuditReference //01MA06
	public void testcase76054() throws Exception
	{	
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("01MA06sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml01MA06);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml01MA06);
			FetchXMLNodeValues.getTagwiseDataForDocumentHeader(xml01MA06);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml01MA06);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_01MA06"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml01MA06);	
			FetchXMLNodeValues.getDocumentHeaderAttributeValues(xml01MA06);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}	
	
	/*
	 * Test Case: TC:74263
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=13) //02MA01
	public void testcase74263() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("02MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 02MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:74245
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 26/04/2017  
	 */
	@Test(priority=14) //02MA01
	public void testcase74245() throws Exception
	{	
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("02MA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);			
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml02MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml02MA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml02MA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_02MA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml02MA01);	
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
	/*
	 * Test Case: TC:119203
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=15) //03MA01 
	public void testcase119203() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("03MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 03MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:74907
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 23/04/2017  
	 */
	@Test(priority=16) //03MA01
	public void testcase74907() throws Exception
	{	
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("03MA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml03MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml03MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityErrorHeader(xml03MA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml03MA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_03MA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml03MA01);	
			FetchXMLNodeValues.getEntityErrorAttributeValue(xml03MA01);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
	
	/*
	 * Test Case: TC:74807
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=17) //03MA02
	public void testcase74807() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("03MA02xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 03MA02 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:74805
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 22/04/2017  
	 */
	@Test(priority=18) //03MA02
	public void testcase74805() throws Exception
	{	
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("03MA02sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml03MA02);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml03MA02);
			FetchXMLNodeValues.getTagwiseDataForCodeLinemHeader(xml03MA02);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml03MA02);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_03MA02"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml03MA02);	
			FetchXMLNodeValues.getItemHeaderAttributeValues(xml03MA02);
			FetchXMLNodeValues.getCodeLineAttributeValues(xml03MA02);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
	/*
	 * Test Case: TC:74796
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 */
	@Test(priority=19) //04MA01
	public void testcase74796() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("04MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 04MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:74798
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 19/04/2017  
	 */
	@Test(priority=20) //04MA01
	public void testcase74798() throws Exception
	{		
	try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("04MA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml04MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml04MA01);
			FetchXMLNodeValues.getTagwiseDataForItemHeader(xml04MA01);
			FetchXMLNodeValues.getTagwiseDataForResponseWindowHeader(xml04MA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml04MA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_04MA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml04MA01);
			FetchXMLNodeValues.getItemHeaderWithoutProcessIDAttributeValues(xml04MA01);
			FetchXMLNodeValues.getResponseWindowHeaderAttributeValues(xml04MA01);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
	/*
	 * Test Case: TC:63239
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=21) //05MA01
	public void testcase63239() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("05MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 05MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:63240
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 25/04/2017  
	 */
	@Test(priority=22) // 05MA01
	public void testcase63240() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("05MA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml05MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml05MA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml05MA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_05MA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml05MA01);			
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:63521
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=23) //05MA02
	public void testcase63521() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("05MA02xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 05MA02 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:63519
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 24/04/2017  
	 */
	@Test(priority=24) //05MA02
	public void testcase63519() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("05MA02sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml05MA02);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml05MA02);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml05MA02);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_05MA02"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml05MA02);	
			FetchXMLNodeValues.getItemHeaderAttributeValues(xml05MA02);
			FetchXMLNodeValues.getJobHeaderAttributeValues(xml05MA02);
			FetchXMLNodeValues.getBatchHeaderAttributeValues(xml05MA02);
			FetchXMLNodeValues.getCaptureItemHeaderAttributeValues(xml05MA02);
			FetchXMLNodeValues.getCodeLineAttributeValues(xml05MA02);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
	/*
	 * Test Case: TC:63281
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=25) //05MA03
	public void testcase63281() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("05MA03xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 05MA03 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:63279
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 24/04/2017  
	 */
	@Test(priority=26) //05MA03
	public void testcase63279() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("05MA03sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml05MA03);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml05MA03);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml05MA03);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_05MA03"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml05MA03);
			FetchXMLNodeValues.getFraudStatusAttributeValuesWithNoFraudReason(xml05MA03);
			FetchXMLNodeValues.getFraudResultsAttributeValuesWithNoFraudReason(xml05MA03);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
	/*
	 * Test Case: TC:58839
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=27) //06MA01
	public void testcase58839() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("06MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 06MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:58687
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 22/04/2017  
	 */
	@Test(priority=28) //06MA01
	public void testcase58687() throws Exception
	{
	try{
		FetchXMLNodeValues.errorQueue(server, db);
		FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("06MA01sql"));
		FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
		FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml06MA01);
		FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml06MA01);
		FetchXMLNodeValues.getCoreHeaderAttributeValues(xml06MA01);
		FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_06MA01"));
		FetchXMLNodeValues.getEntityHeaderAttributeValues(xml06MA01);			
		}catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Test Case: TC:71875
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=29) //06MA02
	public void testcase71875() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("06MA02xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 06MA02 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:71872
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 21/04/2017  
	 */
	@Test(priority=30) //06MA02
	public void testcase71872() throws Exception
	{
		try{
		FetchXMLNodeValues.errorQueue(server, db);
		FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("06MA02sql"));
		FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
		FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml06MA02);
		FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml06MA02);
		FetchXMLNodeValues.getTagwiseDataForItemHeader(xml06MA02);
		FetchXMLNodeValues.getCoreHeaderAttributeValues(xml06MA02);
		FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_06MA02"));
		FetchXMLNodeValues.getEntityHeaderAttributeValues(xml06MA02);	
		FetchXMLNodeValues.getItemHeaderAttributeValues(xml06MA02);
		FetchXMLNodeValues.getJobHeaderAttributeValues(xml06MA02);
		FetchXMLNodeValues.getBatchHeaderAttributeValues(xml06MA02);
		FetchXMLNodeValues.getCaptureItemHeaderAttributeValues(xml06MA02);
		FetchXMLNodeValues.getCodeLineAttributeValues(xml06MA02);
		}catch(Exception e)
		{
		System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Test Case: TC:61886
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=31) //06MA03
	public void testcase61886() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("06MA03xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 06MA03 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:60206
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 20/04/2017  
	 */
	@Test(priority=32) //06MA03
	public void testcase60206() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("06MA03sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml06MA03);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml06MA03);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml06MA03);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_06MA03"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml06MA03);	
			FetchXMLNodeValues.getFraudStatusAttributeValues(xml06MA03);
			FetchXMLNodeValues.getFraudResultsAttributeValues(xml06MA03);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
	/*
	 * Test Case: TC:58586
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=33) //06MA04
	public void testcase58586() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("06MA04xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 06MA04 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
		
	/*
	 * Test Case: TC:61771
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 17/04/2017  
	 */
	@Test(priority=34) //06MA04
	public void testcase61771() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("06MA04sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml06MA04);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml06MA04);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml06MA04);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_06MA04"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml06MA04);	
			FetchXMLNodeValues.getItemHeaderAttributeValues(xml06MA04);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:60853
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=35) //07MA01
	public void testcase60853() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("07MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 07MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
		
	/*
	 * Test Case: TC:61730
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 18/04/2017  
	 */
	@Test(priority=36)//07MA01
	public void testcase61730() throws Exception
	{
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("07MA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml07MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml07MA01);
			FetchXMLNodeValues.getTagwiseDataForDocumentHeader(xml07MA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml07MA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_07MA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml07MA01);	
			FetchXMLNodeValues.getDocumentHeaderAttributeValues(xml07MA01);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
		
	/*
	 * Test Case: TC:65858
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=37) //08MA01
	public void testcase65858() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("08MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 08MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
	
	/*
	 * Test Case: TC:65907
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 19/04/2017  
	 */
	@Test(priority=38)//08MA01
	public void testcase65907() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("08MA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml08MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml08MA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml08MA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_08MA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml08MA01);	
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:74810
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=39) //09MA01
	public void testcase74810() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("09MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 09MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	
	/*
	 * Test Case: TC:74808
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 19/04/2017  
	 */
	@Test(priority=40)//09MA01
	public void testcase74808() throws Exception
	{
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("09MA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml09MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml09MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityErrorHeader(xml09MA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml09MA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_09MA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml09MA01);	
			FetchXMLNodeValues.getEntityErrorAttributeValue(xml09MA01);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:75228
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=41) //11MA01
	public void testcase75228() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("11MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 11MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
		
	/*
	 * Test Case: TC:75330
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 16/04/2017  
	 */
	@Test(priority=42)//11MA01
	public void testcase75330() throws Exception
		{		
			try{
				FetchXMLNodeValues.errorQueue(server, db);
				FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("11MA01sql"));				FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
				FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml11MA01);
				FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml11MA01);
				FetchXMLNodeValues.getCoreHeaderAttributeValues(xml11MA01);
				FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_11MA01"));
				FetchXMLNodeValues.getEntityHeaderAttributeValues(xml11MA01);	
				}catch(Exception e)
				{
				System.out.println(e.getMessage());
				}	
	}
		
	/*
	 * Test Case: TC:75201
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 11/07/2017  
	 */
	@Test(priority=43) //11MA02
	public void testcase75201() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("11MA02xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 11MA02 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:75200
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 11/07/2017  
	 */
	
	@Test(priority=44)//11MA02
	public void testcase75200() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("11MA02sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml11MA02);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml11MA02);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml11MA02);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_11MA02"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml11MA02);			
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:74889
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=45) //11MA03
	public void testcase74889() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("11MA03xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 11MA03 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:74939
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 15/04/2017  
	 */
	
	@Test(priority=46)//11MA03
	public void testcase74939() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("11MA03sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml11MA03);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml11MA03);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml11MA03);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_11MA03"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml11MA03);			
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	

	
	/*
	 * Test Case: TC:77784
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=47) //12MA01
	public void testcase77784() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("12MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 12MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	
	/*
	 * Test Case: TC:77781
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 02/05/2017  
	 */
	@Test(priority=48) //12MA01
	public void testcase77781() throws Exception
	{
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("12MA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);;
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml12MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml12MA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml12MA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_12MA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml12MA01);	
			FetchXMLNodeValues.getItemHeaderWithoutProcessIDAttributeValues(xml12MA01);
			FetchXMLNodeValues.getPayDecisionAttributeValues(xml12MA01);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:63217
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=49) //13MA01
	public void testcase63217() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("13MA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 13MA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:63028
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 02/05/2017  
	 */
	@Test(priority=50) //13MA01
	public void testcase63028() throws Exception
	{
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("13MA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml13MA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml13MA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml13MA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_13MA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml13MA01);	
			FetchXMLNodeValues.getItemHeaderWithBenParticipantIDAttributeValues(xml13MA01);
			FetchXMLNodeValues.getPayDecisionAttributeValues(xml13MA01);
			FetchXMLNodeValues.getCodeLineAndSwitchedCodeLineAttributeValues(xml13MA01);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:63431
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=51) //13MA02
	public void testcase63431() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("13MA02xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 13MA02 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:63433
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 03/05/2017
	 */
	@Test(priority=52) //13MA02
	public void testcase63433() throws Exception
	{
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("13MA02sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml13MA02);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml13MA02);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml13MA02);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_13MA02"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml13MA02);	
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:67835
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=53) //13MA04
	public void testcase67835() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("13MA04xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 13MA04 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
		
	/*
	 * Test Case: TC:67833
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 02/05/2017  
	 */
	@Test(priority=54)//13MA04
	public void testcase67833() throws Exception
	{
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db,  templateFilePath, getIAValueFromDataSheet("13MA04sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml13MA04);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml13MA04);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml13MA04);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_13MA04"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml13MA04);	
			FetchXMLNodeValues.getItemHeaderAttributeValues(xml13MA04);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:67867
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=55) //13MA05
	public void testcase67867() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("13MA05xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 13MA05 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	
	/*
	 * Test Case: TC:67865
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 03/05/2017
	 */
	@Test(priority=56) //13MA05
	public void testcase67865() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("13MA05sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml13MA05);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml13MA05);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xml13MA05);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_13MA05"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xml13MA05);	
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}	
		}
	
	
	/*
	 * Test Case: TC:88215
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=57) //CMMA01
	public void testcase88215() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("CMMA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the CMMA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:88219
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 03/05/2017 
	 */
	@Test(priority=58) //CMMA01
	public void testcase88219() throws Exception
	{
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("CMMA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xmlCMMA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xmlCMMA01);
			FetchXMLNodeValues.getTagwiseDataForOpenCaseHeader(xmlCMMA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xmlCMMA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_CMMA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xmlCMMA01);
			FetchXMLNodeValues.getItemHeaderAttributeValues(xmlCMMA01);
			FetchXMLNodeValues.getOpenCaseHeaderAttributeValues(xmlCMMA01);
			FetchXMLNodeValues.getClosedCaseHeaderAttributeValues(xmlCMMA01);
			//PostingExtractFile.getPostingExtractAttributeValues(xmlCMMA01);
			FetchXMLNodeValues.getPostingExtractWithoutFileIdAttributeValues(xmlCMMA01);
			FetchXMLNodeValues.getPostingItemEntriesWithoutNPAAttributeValues(xmlCMMA01);
			//PostingExtractFile.getPostingItemEntriesAttributeValues(xmlCMMA01);
			FetchXMLNodeValues.getPostingDebitAttributeValues(xmlCMMA01);
			FetchXMLNodeValues.getPostingCreditAttributeValues(xmlCMMA01);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}	
	}
	
	/*
	 * Test Case: TC:88217
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=59) //CMMA02
	public void testcase88217() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("CMMA02xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the CMMA02 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}
		
	/*
	 * Test Case: TC:88221
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 04/05/2017
	 */
	@Test(priority=60) //CMMA02
	public void testcase88221() throws Exception
	{
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("CMMA02sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xmlCMMA02);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xmlCMMA02);
			FetchXMLNodeValues.getTagwiseDataForClosedCaseHeader(xmlCMMA02);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xmlCMMA02);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_CMMA02"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xmlCMMA02);
			FetchXMLNodeValues.getClosedCaseHeaderAttributeValues(xmlCMMA02);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}	
	}

	
	/*
	 * Test Case: TC:69486
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=61) //PRMA01
	public void testcase69486() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("PRMA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the PRMA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:69493
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns. 
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	
	@Test(priority=62) //PRMA01
	public void testcase69493() throws Exception
	{
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("PRMA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xmlPRMA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xmlPRMA01);
			FetchXMLNodeValues.getTagwiseDataForPostingUpdateHeader(xmlPRMA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xmlPRMA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_PRMA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xmlPRMA01);
			FetchXMLNodeValues.getPostingUpdate_PostingResponseAttributeValues(xmlPRMA01);
			FetchXMLNodeValues.getPostingUpdate_PostingResponseRecordAttributeValues(xmlPRMA01);
			FetchXMLNodeValues.getPostingUpdate_ResponseDetailAttributeValues(xmlPRMA01);	
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}	
	}
	
	/*
	 * Test Case: TC:61890
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=63) //PTMA01
	public void testcase61890() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("PTMA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the PTMA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
		
	/*
	 * Test Case: TC:61888
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=64) //PTMA01
	public void testcase61888() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("PTMA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xmlPTMA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xmlPTMA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_PTMA01"));
			FetchXMLNodeValues.getPostingBatchAttributeValues(xmlPTMA01);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}	
	}

		
	
	/*
	 * Test Case: TC:72578
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=65) //PEMA01
	public void testcase72578() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("PEMA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the PEMA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:72580
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=66) //PEMA01
	public void testcase72580() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("PEMA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);			
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xmlPEMA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xmlPEMA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xmlPEMA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_PEMA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xmlPEMA01);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}	
	}
	
	
	/*
	 * Test Case: TC:141960
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 06/10/2017  
	 */
	@Test(priority=67) //PEMA02
	public void testcase141960() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("PEMA02xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the PEMA02 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:141966
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 06/10/2017
	 */
	@Test(priority=68) //PEMA02
	public void testcase141966() throws Exception
	{		
		try{
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("PEMA02sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xmlPEMA02);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xmlPEMA02);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xmlPEMA02);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_PEMA02"));
			FetchXMLNodeValues.getPostingExtractAttributeValues(xmlPEMA02);
			FetchXMLNodeValues.getPostingItemEntriesAttributeValues(xmlPEMA02);
			FetchXMLNodeValues.getPostingDebitAttributeValues(xmlPEMA02);
			FetchXMLNodeValues.getPostingCreditAttributeValues(xmlPEMA02);
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}	
	}
	
	
	/*
	 * Test Case: TC:72578
	 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
	 * Expected:  XSD validation & Message load should be successful 
	 * Author: Sandeep Bibinagar
	 * Date: 29/04/2017  
	 */
	@Test(priority=69) //REMA01
	public void testcase134405() throws Exception
	{	
		try{
			FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("REMA01xsd"));
			publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the REMA01 message against XSD");
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
	}
	
	/*
	 * Test Case: TC:72580
	 * Description: After executing the message, the values should be updated in the respective Tables/Columns
	 * Expected:   All values from the message should be updated in the respective Tables/Columns.
	 * Author: Sandeep Bibinagar
	 * Date: 05/05/2017
	 */
	@Test(priority=70) //REMA01
	public void testcase134406() throws Exception
	{		
		try{
			
			FetchXMLNodeValues.errorQueue(server, db);
			FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("REMA01sql"));
			FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);		
			FetchXMLNodeValues.getTagwiseDataForCoreHeader(xmlREMA01);
			FetchXMLNodeValues.getTagwiseDataForEntityHeader(xmlREMA01);
			FetchXMLNodeValues.getCoreHeaderAttributeValues(xmlREMA01);
			FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_REMA01"));
			FetchXMLNodeValues.getEntityHeaderAttributeValues(xmlREMA01);
			FetchXMLNodeValues.getItemHeaderWithoutGenderAttributeValues(xmlREMA01);
		
			}catch(Exception e)
			{
			System.out.println(e.getMessage());
			}
		}	
		
		/*
		 * Test Case: TC:142226
		 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
		 * Expected:  XSD validation & Message load should be successful 
		 * Author: Sandeep Bibinagar
		 * Date: 09/10/2017  
		 */
		@Test(priority=71) //15MA01
		public void testcase142226() throws Exception
		{	
			try{
				FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("15MA01xsd"));
				publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 15MA01 message against XSD");
				}catch(Exception e)
				{
				System.out.println(e.getMessage());
				}
		}
		
		/*
		 * Test Case: TC:142230
		 * Description: After executing the message, the values should be updated in the respective Tables/Columns
		 * Expected:   All values from the message should be updated in the respective Tables/Columns.
		 * Author: Sandeep Bibinagar
		 * Date: 10/10/2017
		 */
		@Test(priority=72) //15MA01
		public void testcase142230() throws Exception
		{		
			try{
				FetchXMLNodeValues.errorQueue(server, db);
				FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("15MA01sql"));
				FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);
				FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml15MA01);
				FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml15MA01);
				FetchXMLNodeValues.getCoreHeaderAttributeValues(xml15MA01);
				FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_15MA01"));
				FetchXMLNodeValues.getEntityHeaderAttributeValues(xml15MA01);
				}catch(Exception e)
				{
				System.out.println(e.getMessage());
				}	
			}
	
		/*
		 * Test Case: TC: 142169
		 * Description: The message should be validated against the XSD & must be loaded into the archive DB.
		 * Expected:  XSD validation & Message load should be successful 
		 * Author: Sandeep Bibinagar
		 * Date: 11/10/2017  
		 */
		@Test(priority=73) //16MA01
		public void testcase142169() throws Exception
		{	
			try{
				FetchXMLNodeValues.validateXSD(server, db, templateFilePath, getIAValueFromDataSheet("16MA01xsd"));
				publishResults(flag,"XSD Validation passed.." ,"XSD Validation should be successful", "Validating the 16MA01 message against XSD");
				}catch(Exception e)
				{
				System.out.println(e.getMessage());
				}
		}
		
		/*
		 * Test Case: TC:142170
		 * Description: After executing the message, the values should be updated in the respective Tables/Columns
		 * Expected:   All values from the message should be updated in the respective Tables/Columns.
		 * Author: Sandeep Bibinagar
		 * Date: 12/10/2017
		 */
		@Test(priority=74) //16MA01
		public void testcase142170() throws Exception
		{		
			try{
				FetchXMLNodeValues.errorQueue(server, db);
				FetchXMLNodeValues.validateMessageLoadIntoIADB(server, db, templateFilePath, getIAValueFromDataSheet("16MA01sql"));
				FetchXMLNodeValues.validateErrorQueueAfterLoad(server,db);				
				FetchXMLNodeValues.getTagwiseDataForCoreHeader(xml16MA01);
				FetchXMLNodeValues.getTagwiseDataForEntityHeader(xml16MA01);
				FetchXMLNodeValues.getCoreHeaderAttributeValues(xml16MA01);
				FetchXMLNodeValues.getExtMessageTypeAttributeValue(getIAValueFromDataSheet("MessageId_16MA01"));
				FetchXMLNodeValues.getEntityHeaderAttributeValues(xml16MA01);
				FetchXMLNodeValues.getFraudAttributeValues(xml16MA01);
				}catch(Exception e)
				{
				System.out.println(e.getMessage());
				}	
			}
	
}
	