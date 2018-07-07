package com.ics.fred.testScenarios;

import com.ics.seleniumCoreUtilis.GenericMethodUtilis;


public class ValidateICNWithIDS extends GenericMethodUtilis {
	private static String sheetName = getExcelSheetName(new Object(){});
	private static String testCaseName = getRequiredXmlTestCaseNameFRED(new Object(){}) ;
	
	
	public static void validateICNFile(String fredInputData){
		String fredIDSv8TagName = getRequiredStTestData(fredInputData,sheetName,testCaseName,"BusinessDate");
		System.out.println("BusinessDate Value is :"+fredIDSv8TagName);
	}
	
	protected static String getRequiredXmlTestCaseNameFRED(Object object)
	{
		String tempSheetName = getExcelSheetName(object);
		return tempSheetName.substring(tempSheetName.lastIndexOf("Reg"), tempSheetName.indexOf("$") );
	}
	
	protected static String getFREDRegressionInputSheet()
	{
		return getInputXlsTestDataLocationPath() + "FRED_Regression_Input_Data" ;
	}
}