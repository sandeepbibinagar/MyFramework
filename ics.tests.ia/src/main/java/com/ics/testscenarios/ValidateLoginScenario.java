/*  
<Copyright file="ValidateLoginScenario.java" company="iPSL">
Copyright © iPSL 2017 All rights are reserved.
Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
is prohibited without the prior written permission of the copyright owner.
</copyright> 
*/
 
/* ************************* Module Header ******************************
 * Module Name : Login method
 * Date : 28/04/2017
 * Created By : Sandeep Bibinagar
 * Description : This class contains Login Scenario
 ******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
 Description : Updating class as per java coding standards
 ********************************************************************** */


package com.ics.testscenarios;


import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.ia.pages.ImageArchiveWorkflowPage;
import com.ics.ia.pages.SearchItemPage;
import com.ics.seleniumCoreUtilis.Scenario;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

	public class ValidateLoginScenario implements Scenario<ImageArchiveWorkflowPage,SearchItemPage> 
	{
	@Override
	public SearchItemPage run(ImageArchiveWorkflowPage entry) throws Exception {		
	return entry
		  
		 .iaLoginAlertAutentication(ICSPropertiesConfig.getIAUsername(),GenericMethodUtilis.decrypt(ICSPropertiesConfig.getIAPassword()));
	}
	
	
	
}