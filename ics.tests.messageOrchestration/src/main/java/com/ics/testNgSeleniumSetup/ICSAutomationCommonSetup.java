package com.ics.testNgSeleniumSetup;

import java.io.File;
import java.lang.reflect.Method;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
import com.ics.seleniumCoreUtilis.InternetExplorerInitializer;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ICSAutomationCommonSetup extends GenericMethodUtilis {

	protected static WebDriver DRIVER;
	private static String testSheetNameAsRegression = "mo";
	protected static String validationHeader = "Running validation script for workflow ";
	protected static File file;

	public static String getWorkingDirectoryPath(){
		File f = new File(System.getProperty("user.dir"));
		return(f.getPath());
	}


	@BeforeSuite
	public void initializeExtentReportSetup()
	{
		setUserDirectory(getWorkingDirectoryPath());
		file = new File(getTestReportsBaseLocationPath());

		try
		{
			file.setWritable(true, false);
			EXTENT = new ExtentReports(System.getProperty("user.dir") + "\\extentReportFile.html", true);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}	
	}

	@BeforeMethod(alwaysRun = true)
	public void beforeMethodRun(Method method)
	{
		finalTestScriptResultFailFlag = false;
		EXTENTLOG = EXTENT.startTest((this.getClass().getSimpleName() + " :: " + method.getName()), method.getName());
	}


	@AfterSuite(alwaysRun = true)
	public void afterSuiteRun()
	{
		EXTENT.endTest(EXTENTLOG);
		EXTENT.flush();
	}

	protected static String getSheetNameAsRegression()
	{
		return testSheetNameAsRegression;
	}

	protected static String getRequiredRegressionCycleName(Object object)
	{
		String tempSheetName = getExcelSheetName(object);
		tempSheetName = tempSheetName.substring(tempSheetName.indexOf(testSheetNameAsRegression), tempSheetName.lastIndexOf("."));
		return tempSheetName;
	}

	protected static String getRequiredXmlTestCaseName(Object object)
	{
		String tempSheetName = getExcelSheetName(object);
		return tempSheetName.substring(tempSheetName.lastIndexOf("Test"), tempSheetName.indexOf("$") );
	}

	protected static WebDriver startDriver(){
		return new InternetExplorerInitializer().initialize();
	}

	public static String getWorkingDirectory(){
		File f = new File(System.getProperty("user.dir"));
		return(f.getPath());
	}

	@AfterMethod(alwaysRun=true)
	public void catchPassAndExceptionsScreenshots(ITestResult result) {
		String methodName = result.getName();
		if(finalTestScriptResultFailFlag)
		{
			EXTENTLOG.log(LogStatus.FAIL, methodName + " Failed.");
		}
		else
		{
			EXTENTLOG.log(LogStatus.PASS, methodName + " Passed.");
		}
	}		
}
