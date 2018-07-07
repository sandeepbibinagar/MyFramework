package com.ics.fred.testScenarios;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.fred.common.GenericMethods;

public class AIXMLServiceStartValidation extends ICSDBUtilis{
//Check AIXML file start service 1
	
	public static void validateAIXMLSerice(String serverName,String dbName) throws Exception{
	
	String queryVal ="SELECT TOP (1000) [ServiceStatus] FROM [FREDPIT].[Base].[WindowsServiceStatus]";
	ResultSet resultset =getICSDBServerConnection(serverName, dbName, queryVal);
	String actualAIXMLSeriveVal = GenericMethods.getICSRetrieveColumnValues(resultset);
	System.out.println("actualAIXMLSeriveVal "+actualAIXMLSeriveVal);
	
	if (actualAIXMLSeriveVal.equals("0")){
		
		System.out.println("AIXMLService is not runnning "+actualAIXMLSeriveVal);
		//Starts the service
		//sqlFileInjectCommand = "cmd /c sqlcmd -S GBIBC-DT30-32-V\\SQL001 -d FREDPIT -i "+templateFilePath + sqlCreditTemplateFileName + "_Actual.sql ";
		String strCommand ="SC \\\\129.227.33.34 start iPSL.iCE.Fred.AIXMLLoad";
		//strCommand ="net START iPSL.iCE.Fred.AIXMLService";
		sqlCommandExecutionTest(strCommand);
		publishResults(actualAIXMLSeriveVal.equals("0"), "AIXMLService is not running...."+actualAIXMLSeriveVal, "AIXMLService is not running...."+actualAIXMLSeriveVal, "AIXMLService Status validation performed.");
		
	}
	else
	{
		//Starts the service
		System.out.println("AIXMLService is up and running...."+actualAIXMLSeriveVal);
		publishResults(!(actualAIXMLSeriveVal.equals("0")), "AIXMLService is up and running...."+actualAIXMLSeriveVal, "AIXMLService is up and running...."+actualAIXMLSeriveVal, "AIXMLService Status validation performed.");
		
	}
	}
	

}
