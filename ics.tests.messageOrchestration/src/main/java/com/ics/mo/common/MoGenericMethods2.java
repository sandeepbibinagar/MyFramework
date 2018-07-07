package com.ics.mo.common;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

/**
 * <h1>MoGenericMethods</h1>
 * This class file contains all MO related Common Methods to fetch data from Required DB , Monitoring/Validate required 
 * message flow data with Expected.
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-04-15
 */
public class MoGenericMethods2 extends MoDbAndEntityNames
{ 	
	protected HashMap<Integer, String> createdJobParams = new LinkedHashMap<Integer, String>();
	protected String scheduleId = new String(), activityId = new String(), jobTemplateId = new String(), status = new String();
	protected String actualPayerDirtyFlag = new String(), actualPayerBatchedFlag = new String(), actualBeneficiaryDirtyFlag = new String(), actualBeneficiaryBatchedFlag = new String();
	protected String keyColumnData = new String(), msg04SM01FileName = new String(), msg05SM01FileName = new String(), msg06SM01FileName = new String(), msg06KM01FileName = new String(), msg01KM01FileName = new String();
	protected static String keyColumnDataAsXmlFileName = "xmlFileName", keyColumnTsetDataValue = "tsetsDataValue", keyColumnMsgIdValue = "msgIdValue", keyColumnFMextractId = "extractId06FM01", keyColumnDMextractId = "extractId06DM01";
	protected String sourceID = new String(), sourceSKID = new String(), rowId = new String(), newReplacedMsgIdData = new String();
	protected String tempISOContent = new String(), tempISOSourceSKID = new String(), tempISOSourceID = new String(), tempICNContent = new String(), tempICNSourceID = new String(), tempICNSourceSKID = new String();
	protected String actualContentInReceiveStaging = new String(), newReplacedTestData = new String(), newExtractId06FM01 = new String(), newExtractId06DM01 = new String();
	protected static final String  serialNo = "SerialNo", sortCode = "SortCode", accountNo = "Account" , transactionCode = "TranCode", amount = "Amount";
	protected ArrayList<String> expectedTsetId = new ArrayList<String>(), expectedTxVersion = new ArrayList<String>(), dayOneResponseWindow = new ArrayList<String>() ;
	protected ArrayList<String> dayTwoResponseWindow = new ArrayList<String>(), expectedCreditItemId = new ArrayList<String>(), expectedDebitItemId = new ArrayList<String>();
	protected ArrayList<String> expectedSourceId = new ArrayList<String>(), extractId = new ArrayList<String>();
	protected ExtentReports extent ;
	protected ExtentTest extentLog ;
	protected final Logger moGenericMethodsLog = Logger.getLogger(MoGenericMethods2.class.getSimpleName());

	public MoGenericMethods2(ExtentReports extent, ExtentTest extentLog)
	{
		this.extent = extent;
		this.extentLog = extentLog;
	}

	/**
	 * This method is to create JobParamIds for required ScheduleParams
	 * @throws Exception
	 */
	public MoGenericMethods2 setupJobParamIds() throws Exception
	{
		for(int paramId = 1 ; paramId <= 9 ; paramId++)
		{
			String additionalParams = ICSPropertiesConfig.getMOResourceFile().getString("jobscheduleParams_" + paramId);
			String query = "INSERT INTO " + schedulerSchema + jobScheduleParamsTable + " ([" + keyColumn + "],[" + valueColumn 
					+ "],[" + createdDateTimeColumn + "],[" + createdByUserColumn + "],[" + updatedDateTimeColumn + "],[" + updatedByUserColumn + "]) "
					+ "VALUES ('Parameters','" + additionalParams + "' ,GETDATE(),USER_NAME(),GETDATE(),USER_NAME())";
			try{
	    		insertRecordInIcsDB(moServerDetails, schedulerSchema, query);
			}catch(Exception e)
			{
				validationErrorInformation(jobParamsException + additionalParams);
				moGenericMethodsLog.error(jobParamsException + additionalParams);
				Component.assertTrue(false, jobParamsException + additionalParams);
			}	
			query = "SELECT " + jobParamIdColumn + " FROM " + schedulerSchema + jobScheduleParamsTable
					+ " where " + valueColumn + " = '"+ additionalParams + "'";
			resultSet = getICSDBServerConnection(moServerDetails, schedulerSchema, query);
			while(resultSet.next())
			{
				createdJobParams.put(paramId, resultSet.getString(jobParamIdColumn));
			}			
		}
		moGenericMethodsLog.info(jobParamCreation);
		return this;
	}

	/**
	 * This method is to created JobTemplates for All the Message workFlow
	 * @throws Exception
	 */
	@SuppressWarnings("incomplete-switch")
	public MoGenericMethods2 createJobTemplateForWorkFlows() throws Exception
	{
		String firstRunStartTime = new GenericMethodUtilis().dateFormat_2.format(new Date()) ;
		String template = " Template";
		switch(WorkFlowNames.MSG04SM01)
		{
		case MSG04SM01:
			createJobTemplate(workFlow04SM01, workFlow04SM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));
		case MSG04MM01:
			createJobTemplate(workFlow04MM01, workFlow04MM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));
		case MSG04MA01:
			createJobTemplate(workFlow04MA01, workFlow04MA01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));
		case MSG06SM01:
			createJobTemplate(workFlow06SM01, workFlow06SM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));
		case MSG05SM01:
			createJobTemplate(workFlow05SM01, workFlow05SM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));
		case MSG06MA01:
			createJobTemplate(workFlow06MA01, workFlow06MA01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG06MF01:
			createJobTemplate(workFlow06MF01, workFlow06MF01 + template, firstRunStartTime ,jobTemplateEndTime, createdJobParams.get(2));
		case MSG06FM01:
			createJobTemplate(workFlow06FM01, workFlow06FM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));
		case MSG06MA02:
			createJobTemplate(workFlow06MA02, workFlow06MA02 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG06MK01:
			createJobTemplate(workFlow06MK01, workFlow06MK01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG06KM01:
			createJobTemplate(workFlow06KM01, workFlow06KM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));		
		case MSG06MA03:
			createJobTemplate(workFlow06MA03, workFlow06MA03 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG06MD01:
			createJobTemplate(workFlow06MD01, workFlow06MD01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG11SM01:
			createJobTemplate(workFlow11SM01, workFlow11SM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG11MA01:
			createJobTemplate(workFlow11MA01, workFlow11MA01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG12SM01:
			createJobTemplate(workFlow12SM01, workFlow12SM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG12MA01:
			createJobTemplate(workFlow12MA01, workFlow12MA01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSGPTMA01:
			createJobTemplate(workFlowPTMA01, workFlowPTMA01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(8));	
		case MSGPTMR01:
			createJobTemplate(workFlowPTMR01, workFlowPTMR01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(5));
		case MSGPERM01:
			createJobTemplate(workFlowPERM01, workFlowPERM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));
		case MSGPEMA01:
			createJobTemplate(workFlowPEMA01, workFlowPEMA01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(5));	
		case MSGPRRM01:
			createJobTemplate(workFlowPRRM01, workFlowPRRM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));
		case MSGPRMA01:
			createJobTemplate(workFlowPRMA01, workFlowPRMA01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(5));	
		case MSGPRMD01:
			createJobTemplate(workFlowPRMD01, workFlowPRMD01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(5));
		case MSG06DM01:
			createJobTemplate(workFlow06DM01, workFlow06DM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));		
		case MSG06MA04:
			createJobTemplate(workFlow06MA04, workFlow06MA04 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG07MS01:
			createJobTemplate(workFlow07MS01, workFlow07MS01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG07MA01:
			createJobTemplate(workFlow07MA01, workFlow07MA01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));		
		case MSG08SM01:
			createJobTemplate(workFlow08SM01, workFlow08SM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));			
		case MSG08MA01:
			createJobTemplate(workFlow08MA01, workFlow08MA01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG09SM01:
			createJobTemplate(workFlow09SM01, workFlow09SM01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(9));	
		case MSG09MA01:
			createJobTemplate(workFlow09MA01, workFlow09MA01 + template, firstRunStartTime, jobTemplateEndTime, createdJobParams.get(9));	
		case MSG01FM01:
			createJobTemplate(workFlow01FM01, workFlow01FM01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));	
		case MSG01MA01:
			createJobTemplate(workFlow01MA01, workFlow01MA01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(1));	
		case MSG01MD01:
			createJobTemplate(workFlow01MD01, workFlow01MD01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG01MK01:
			createJobTemplate(workFlow01MK01, workFlow01MK01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG01KM01:
			createJobTemplate(workFlow01KM01, workFlow01KM01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG01MA02:
			createJobTemplate(workFlow01MA02, workFlow01MA02 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG01MA03:
			createJobTemplate(workFlow01MA03, workFlow01MA03 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG01MM01:
			createJobTemplate(workFlow01MM01, workFlow01MM01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG01MA04:
			createJobTemplate(workFlow01MA04, workFlow01MA04 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG01MA05:
			createJobTemplate(workFlow01MA05, workFlow01MA05 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG01MS01:
			createJobTemplate(workFlow01MS01, workFlow01MS01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG01MA06:
			createJobTemplate(workFlow01MA06, workFlow01MA06 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG02SM01:
			createJobTemplate(workFlow02SM01, workFlow02SM01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG01DM01:
			createJobTemplate(workFlow01DM01, workFlow01DM01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG02MA01:
			createJobTemplate(workFlow02MA01, workFlow02MA01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG15SM01:
			createJobTemplate(workFlow15SM01, workFlow15SM01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG15MA01:
			createJobTemplate(workFlow15MA01, workFlow15MA01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));	
		case MSG01MD02:
			createJobTemplate(workFlow01MD02, workFlow01MD02 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG01DM02:
			createJobTemplate(workFlow01DM02, workFlow01DM02 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG03SM01:
			createJobTemplate(workFlow03SM01, workFlow03SM01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSG03MA01:
			createJobTemplate(workFlow03MA01, workFlow03MA01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSGREXM01:
			createJobTemplate(workFlowREXM01, workFlowREXM01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		case MSGREMA01:
			createJobTemplate(workFlowREMA01, workFlowREMA01 + template,  firstRunStartTime, jobTemplateEndTime, createdJobParams.get(2));
		}		
		moGenericMethodsLog.info(jobTemplateCreation);
		return this;
	}

	/**
	 * This method to trigger DTSX package for IntradaySchedules to create
	 * @throws Exception
	 */
	public MoGenericMethods2 packageRunForSODScheduleIds() throws Exception
	{
		boolean flag = false;
		moGenericMethodsLog.info(dtsxPackageRun);
		try{
			sqlCommandExecution(dtsxPath);
			flag = true;
			Thread.sleep(15000);
			moGenericMethodsLog.info(dtsxSuccessRun);
		}catch(Exception e)
		{
			validationErrorInformation(dtsxFailureRun);
			moGenericMethodsLog.error(dtsxFailureRun);
			Component.assertTrue(flag,dtsxFailureRun);
		}	
		return this;
	}

	/**
	 * This method is to get specific tag data of required XML data of required Switch file
	 * @param testCaseName
	 * @param workFlow
	 * @param keyName
	 * @param regression
	 * @throws Exception
	 */
	public MoGenericMethods2 getInputXmlDataOfSwitchFile(String testCaseName, String workFlow, String keyName, String regression) throws Exception
	{
		expectedTxVersion = new ArrayList<String>();
		expectedTsetId = new ArrayList<String>();
		expectedDebitItemId = new ArrayList<String>();
		expectedSourceId = new ArrayList<String>();
		dayOneResponseWindow = new ArrayList<String>();
		dayTwoResponseWindow = new ArrayList<String>();
		getXMLFilePathOfRegressionCyle1(getInputSheetOfMoRegression(regression), testCaseName, workFlow, keyName );		
		expectedSourceId = setOutputFileData(regression,getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//MsgId", "MsgId");
		moGenericMethodsLog.info(String.format(sourceIdData, workFlow) + expectedSourceId);
		expectedTsetId = setOutputFileData(regression, getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//TxSetId", "TxSetId");
		moGenericMethodsLog.info(String.format(txsetIdsData, workFlow) + expectedTsetId);
		expectedTxVersion = setOutputFileData(regression, getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//TxSetVrsn", "TxSetVrsn");
		expectedCreditItemId = setOutputFileData(regression,getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//CdtItmId", "CdtItmId");
		moGenericMethodsLog.info(String.format(creditIdsData, workFlow) + expectedCreditItemId);
		expectedDebitItemId = setOutputFileData(regression,getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//DbtItmId", "DbtItmId");
		moGenericMethodsLog.info(String.format(debitIdsData, workFlow) + expectedDebitItemId);
		try{
			dayOneResponseWindow = setOutputFileData(regression,getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//DayOneRspnWndwStartDtTm", "DayOneRspnWndwStartDtTm");
			moGenericMethodsLog.info(String.format(dayOneWindowData, workFlow) + dayOneResponseWindow);
		}catch(Exception e)
		{
			moGenericMethodsLog.info(String.format(dayOneWindowData, workFlow) + null);
		}
		try{
			dayTwoResponseWindow = setOutputFileData(regression,getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//DayTwoRspnWndwStartDtTm", "DayTwoRspnWndwStartDtTm");
			moGenericMethodsLog.info(String.format(dayTwoWindowData, workFlow) + dayTwoResponseWindow);
		}catch(Exception e)
		{
			moGenericMethodsLog.info(String.format(dayTwoWindowData, workFlow) + null);
		}
		return this;
	}
	
	/**
	 * This method is to get BD date
	 * @return
	 * @throws Exception
	 */
	public String getBusinessDate() throws Exception
	{
		String query = "SELECT " + valueColumn + " FROM " + configSchema + eavTable
				+ " Where " + entityColumn + "= '" + controlM + "' and " + attributeColumn + "= '" + businessDate + "'";
		resultSet = getICSDBServerConnection(moServerDetails, configSchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}
	
	/**
	 * This method is to get BD+1 date
	 * @return
	 * @throws Exception
	 */
	public String getBusinessDatePlusOne() throws Exception
	{
		String query = "SELECT " + valueColumn + " FROM " + configSchema + eavTable
				+ " Where " + entityColumn + "= '" + controlM + "' and " + attributeColumn + "= '" + businessDateP1 + "'";
		resultSet = getICSDBServerConnection(moServerDetails, configSchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}
	
	/**
	 * This method is to Create TestData for SwitchFile
	 * @param inputPath
	 * @param inputFileName
	 * @param msgIdToReplace
	 * @param dataToReplace
	 * @return
	 * @throws Exception
	 */
	public MoGenericMethods2 createTestDataForSwitch(String inputPath, String inputFileName, String msgIdToReplace, String dataToReplace) throws Exception
	{
		String switchData = FileUtils.readFileToString(new File(inputPath+inputFileName));	
		newReplacedMsgIdData = new GenericMethodUtilis().dateFormat_11.format(new Date());
	    Thread.sleep(5000);
		newReplacedTestData = new GenericMethodUtilis().dateFormat_11.format(new Date());
		switchData = switchData.replaceAll(msgIdToReplace.substring(0, msgIdToReplace.indexOf(".")), newReplacedMsgIdData);	
		switchData = switchData.replaceAll(dataToReplace.substring(0, dataToReplace.indexOf(".")) + "", newReplacedTestData);
		try{
			String dayTwo = switchData.substring(switchData.indexOf("<DayTwoRspnWndwStartDtTm>")+"<DayTwoRspnWndwStartDtTm>".length(), switchData.indexOf("</DayTwoRspnWndwStartDtTm>"));
			String dayTwoToReplace = dayTwo.substring(0, dayTwo.indexOf("T"));
			switchData = switchData.replaceAll(dayTwoToReplace, getBusinessDatePlusOne());
		}catch(Exception e)
		{
			validationStepInformation("No DayOne DayTwo Window Dates for given Switch File");
		}
		String createdDateTime = switchData.substring(switchData.indexOf("<CreDtTm>")+"<CreDtTm>".length(), switchData.indexOf("</CreDtTm>"));
		String credDtTmToReplace = createdDateTime.substring(0, createdDateTime.indexOf("T"));
		switchData = switchData.replaceAll(credDtTmToReplace, getBusinessDate());
		new File(inputPath+inputFileName).delete();
		inputFileName = inputFileName.replaceAll(".xml", ".txt");
		writeToFile(inputPath+inputFileName, switchData);		
		getRenamedFileExtenstion(inputPath+inputFileName, ".txt", ".xml");
		Thread.sleep(5000);
		inputFileName = inputFileName.replaceAll(".txt", ".xml");
		String tempInputFileName = inputFileName.substring(inputFileName.indexOf("MSG"));
		tempInputFileName = tempInputFileName.substring(tempInputFileName.indexOf("_")+"_".length());
		tempInputFileName = tempInputFileName.substring(0, tempInputFileName.indexOf("_"));
		getRenamedFileExtenstion(inputPath+inputFileName, tempInputFileName, new GenericMethodUtilis().dateFormat_13.format(new Date()));
		return this;
	}
	
	/**
	 * This method is to create TestData for FM01
	 * @param xmlInputFile
	 * @param extractIdToReplace
	 * @param dataToReplace
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */
	public MoGenericMethods2 createdTestDataForFM(String xmlInputFile, String sqlInputFile, String extractIdToReplace, String dataToReplace, String workFlow) throws Exception
	{
		String fredDataXml = FileUtils.readFileToString(new File(xmlInputFile));	
		fredDataXml = fredDataXml.replaceAll(dataToReplace.substring(0, dataToReplace.indexOf(".")) + "", newReplacedTestData);
		newExtractId06FM01 = new GenericMethodUtilis().dateFormat_12.format(new Date()) + workFlow + new GenericMethodUtilis().dateFormat_11.format(new Date());
		fredDataXml = fredDataXml.replaceAll(extractIdToReplace, newExtractId06FM01);
		String businessDateToReplace = fredDataXml.substring(fredDataXml.indexOf("<BusinessDate>") + "<BusinessDate>".length(), fredDataXml.indexOf("</BusinessDate>"));
		fredDataXml = fredDataXml.replaceAll(businessDateToReplace, getBusinessDate());
		getRenamedFileExtenstion(xmlInputFile, ".xml", ".txt");
		writeToFile(xmlInputFile, fredDataXml);		
		getRenamedFileExtenstion(xmlInputFile, ".txt", ".xml");
		String fredDataSql = FileUtils.readFileToString(new File(sqlInputFile+workFlow+".sql"));	
		String tempSqlData = fredDataSql.substring(fredDataSql.indexOf("<Document"), fredDataSql.indexOf("</Document>")+ "</Document>".length());
		tempSqlData = tempSqlData.replaceAll(dataToReplace.substring(0, dataToReplace.indexOf(".")) + "", newReplacedTestData);
	    String moRepositoryToReplace = fredDataSql.substring(fredDataSql.indexOf("[") +"[".length(), fredDataSql.indexOf("]"));
		fredDataSql = fredDataSql.replaceAll(moRepositoryToReplace, repositorySchema);
		String tempPreFixData = fredDataSql.substring(0, fredDataSql.indexOf("'<Document"));
		String tempPostFixData = fredDataSql.substring(fredDataSql.indexOf("</Document>") + "</Document>".length());
		fredDataSql = tempPreFixData + tempSqlData + tempPostFixData ;	
		fredDataSql = fredDataSql.replaceAll(businessDateToReplace, getBusinessDate());
		new File(sqlInputFile+workFlow+".sql").delete();
		writeToFile(sqlInputFile+workFlow+".txt", fredDataSql);		
		getRenamedFileExtenstion(sqlInputFile+workFlow+".txt", ".txt", ".sql");	
		Thread.sleep(3000);
		return this;
	}
	
	/**
	 * This method is to create TestData for DM01
	 * @param inputFile
	 * @param extractIdToReplace
	 * @param dataToReplace
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */
	public MoGenericMethods2 createdTestDataForDM(String xmlInputFile, String sqlInputFile, String extractIdToReplace, String dataToReplace, String workFlow) throws Exception
	{
		String dewDataXml = FileUtils.readFileToString(new File(xmlInputFile));	
		dewDataXml = dewDataXml.replaceAll(dataToReplace.substring(0, dataToReplace.indexOf(".")) + "", newReplacedTestData);
		newExtractId06DM01 = new GenericMethodUtilis().dateFormat_12.format(new Date()) + workFlow + new GenericMethodUtilis().dateFormat_11.format(new Date());
		dewDataXml = dewDataXml.replaceAll(extractIdToReplace, newExtractId06DM01);
		String businessDateToReplace = dewDataXml.substring(dewDataXml.indexOf("<BusinessDate>") + "<BusinessDate>".length(), dewDataXml.indexOf("</BusinessDate>"));
		dewDataXml = dewDataXml.replaceAll(businessDateToReplace, getBusinessDate());
		getRenamedFileExtenstion(xmlInputFile, ".xml", ".txt");
		writeToFile(xmlInputFile, dewDataXml);		
		getRenamedFileExtenstion(xmlInputFile, ".txt", ".xml");
		String dewDataSql = FileUtils.readFileToString(new File(sqlInputFile+workFlow+".sql"));	
		String tempSqlData = dewDataSql.substring(dewDataSql.indexOf("<Document"), dewDataSql.indexOf("</Document>")+ "</Document>".length());
		tempSqlData = tempSqlData.replaceAll(dataToReplace.substring(0, dataToReplace.indexOf(".")) + "", newReplacedTestData);
		
		String moRepositoryToReplace = dewDataSql.substring(dewDataSql.indexOf("[") +"[".length(), dewDataSql.indexOf("]"));
		dewDataSql = dewDataSql.replaceAll(moRepositoryToReplace, repositorySchema);
		String tempPreFixData = dewDataSql.substring(0, dewDataSql.indexOf("'<Document"));
		String tempPostFixData = dewDataSql.substring(dewDataSql.indexOf("</Document>'") + "</Document>'".length());
		dewDataSql = tempPreFixData + tempSqlData + tempPostFixData ;		
		dewDataSql = dewDataSql.replaceAll(businessDateToReplace, getBusinessDate());
		new File(sqlInputFile+workFlow+".sql").delete();
		writeToFile(sqlInputFile+workFlow+".txt", dewDataSql);		
		getRenamedFileExtenstion(sqlInputFile+workFlow+".txt", ".txt", ".sql");	
		Thread.sleep(3000);
		return this;
	}
	
	/**
	 * This method is to create TestData for KAPPA
	 * @param inputPath
	 * @param inputFileName
	 * @param msgIdToReplace
	 * @param dataToReplace
	 * @return
	 * @throws Exception
	 */
	public MoGenericMethods2 createTestDataForKAPPA(String inputPath, String inputFileName, String msgIdToReplace, String dataToReplace) throws Exception
	{
		String kappaData = FileUtils.readFileToString(new File(inputPath+inputFileName));	
	    Thread.sleep(5000);
		newReplacedTestData = new GenericMethodUtilis().dateFormat_11.format(new Date());
		kappaData = kappaData.replaceAll(dataToReplace.substring(0, dataToReplace.indexOf(".")) + "", newReplacedTestData);
		String createdDateTime = kappaData.substring(kappaData.indexOf("<CreDtTm>")+"<CreDtTm>".length(), kappaData.indexOf("</CreDtTm>"));
		String credDtTmToReplace = createdDateTime.substring(0, createdDateTime.indexOf("T"));
		kappaData = kappaData.replaceAll(credDtTmToReplace, getBusinessDate());
		new File(inputPath+inputFileName).delete();
		inputFileName = inputFileName.replaceAll(".xml.READY", ".txt");
		writeToFile(inputPath+inputFileName, kappaData);		
		getRenamedFileExtenstion(inputPath+inputFileName, ".txt", ".xml.READY");
		String tempInputFileName = inputFileName.substring(inputFileName.indexOf("MSG"));
		tempInputFileName = tempInputFileName.substring(tempInputFileName.indexOf("_")+"_".length());
		tempInputFileName = tempInputFileName.substring(0, tempInputFileName.indexOf("."));
		getRenamedFileExtenstion(inputPath+inputFileName, tempInputFileName, new GenericMethodUtilis().dateFormat_12.format(new Date()) 
				+ "_0"+new GenericMethodUtilis().dateFormat_11.format(new Date()));
		return this;
	}
	
	public MoGenericMethods2 copyToSqlDataSectonAndExtChange(String fileName, String srcFilePath, String destFilePath) throws Exception
	{
		CopyFileToDestinationPath(fileName, srcFilePath, destFilePath);
		getRenamedFileExtenstion(destFilePath+"\\"+fileName, ".xml", ".sql");
		return this;
	}
	
	/**
	 * This method is to get the specific Tag data of XML content of required FRED file
	 * @param testCaseName
	 * @param workFlow
	 * @param keyName
	 * @param regression
	 * @throws Exception
	 */
	public MoGenericMethods2 getInputXmlDataOfMoToFredFile(String testCaseName, String workFlow, String keyName, String regression) throws Exception
	{
		extractId = new ArrayList<String>();
		getXMLFilePathOfRegressionCyle1(getInputSheetOfMoRegression(regression), testCaseName, workFlow, keyName);
		extractId = setOutputFileData(regression, getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//ExtractId", "ExtractId");
		if(workFlow.equals(workFlow01FM01))
		{
			getInputXmlDataOfSwitchFile(testCaseName, workFlow, keyName, regression);
		}
		moGenericMethodsLog.info(String.format(extractIdData, workFlow)+ extractId);
		return this;
	}

	/**
	 * This method is to get EAV location for given workflow
	 * @param workFlowName
	 * @return
	 * @throws Exception
	 */
	public String getEAVLocation(String workFlowName) throws Exception
	{
		String query = "select " + valueColumn + " from " + configSchema + eavTable
				+ " Where " + entityColumn + "='" + fileLocation + "' and " + attributeColumn + "='" + workFlowName + "'" ;  
		resultSet = getICSDBServerConnection(moServerDetails, configSchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/**
	 * This method is to get the ScheduleId W.R.T given file name
	 * @param workFlowName
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public MoGenericMethods2 scheduleIdWithFileName(String workFlowName, String fileName) throws Exception
	{
		String query = "Select " + scheduleIDColumn + "," + activityIdColumn + "," + payLoadColumn + " from " + schedulerSchema + jobExecutionLogTable + " where WorkFlowname = '" + workFlowName + "'";		
		String payLoadValue = new String();
		scheduleId = new String();
		int checkCountFlag = 0;
		boolean payLoadFlag = false;
		do
		{
			checkCountFlag++;
			ResultSet jobExecutionResultSet = getICSDBServerConnection(moServerDetails, schedulerSchema, query);
			while(jobExecutionResultSet.next())
			{
				activityId = jobExecutionResultSet.getString(activityIdColumn);
				payLoadValue = jobExecutionResultSet.getString(payLoadColumn);
				if(payLoadValue.indexOf(fileName) > 0)
				{
					payLoadFlag = true;
					scheduleId = jobExecutionResultSet.getString(scheduleIDColumn);
				}						
			}		
			if((scheduleId.equals("")) && (checkCountFlag < 50))
			{
				Thread.sleep(5000);
				moGenericMethodsLog.info(String.format(paylodCreationWait, workFlowName));
				validationStepInformation(String.format(paylodCreationWait, workFlowName));
			}else if(!(scheduleId.equals(""))){
				moGenericMethodsLog.info(String.format(payloadCreated, workFlowName) + scheduleId);
				validationStepInformation(String.format(payloadCreated, workFlowName)+ scheduleId);
				break;
			}else {	
				moGenericMethodsLog.error(payLoadNotCreated + workFlowName);
				validationErrorInformation(payLoadNotCreated + workFlowName);
				Component.assertTrue(false, payLoadNotCreated + workFlowName);
				break;
			}
		}while(!(payLoadFlag));
		return this;
	}
	
	/**
	 * This method is to get the ScheduleId W.R.T given file name
	 * @param workFlowName
	 * @param rowId
	 * @return
	 * @throws Exception
	 */
	public MoGenericMethods2 scheduleIdWithRowId(String workFlowName, String rowId) throws Exception
	{
		String query = "Select " + scheduleIDColumn + "," + activityIdColumn + "," + payLoadColumn + " from " + schedulerSchema + jobExecutionLogTable + " where WorkFlowname = '" + workFlowName + "'";		
		String payLoadValue = new String();
		scheduleId = new String();
		int checkCountFlag = 0;
		boolean payLoadFlag = false;
		do
		{
			checkCountFlag++;
			ResultSet jobExecutionResultSet = getICSDBServerConnection(moServerDetails, schedulerSchema, query);
			while(jobExecutionResultSet.next())
			{
				activityId = jobExecutionResultSet.getString(activityIdColumn);
				payLoadValue = jobExecutionResultSet.getString(payLoadColumn);
				if(payLoadValue.indexOf("<RowId>" + rowId + "</RowId>") > 0)
				{
					payLoadFlag = true;
					scheduleId = jobExecutionResultSet.getString(scheduleIDColumn);
				}						
			}		
			if((scheduleId.equals("")) && (checkCountFlag < 50))
			{
				Thread.sleep(5000);
				moGenericMethodsLog.info(String.format(paylodCreationWait, workFlowName));
				validationStepInformation(String.format(paylodCreationWait, workFlowName));
			}else if(!(scheduleId.equals(""))){
				moGenericMethodsLog.info(String.format(payloadCreated, workFlowName) + scheduleId);
				validationStepInformation(String.format(payloadCreated, workFlowName)+ scheduleId);
				break;
			}else {	
				moGenericMethodsLog.error(payLoadNotCreated + workFlowName);
				validationErrorInformation(payLoadNotCreated + workFlowName);
				Component.assertTrue(false, payLoadNotCreated + workFlowName);
				break;
			}
		}while(!(payLoadFlag));
		return this;
	}

	/**
	 * This method is to get the ScheduleId W.R.T given itemIds
	 * @param workFlowName
	 * @param itemIds
	 * @return
	 * @throws Exception
	 */
	public MoGenericMethods2 scheduleIdWithIdsList(String workFlowName, List<String> itemIds) throws Exception
	{
		String query = "Select " + scheduleIDColumn + "," + activityIdColumn + "," + payLoadColumn + " from " + schedulerSchema + jobExecutionLogTable + " where WorkFlowname = '" + workFlowName + "'";		
		String payLoadValue = new String();
		scheduleId = new String();
		int checkCountFlag = 0;
		boolean payLoadFlag = false;
		do
		{
			checkCountFlag++;
			ResultSet jobExecutionResultSet = getICSDBServerConnection(moServerDetails, schedulerSchema, query);
			while(jobExecutionResultSet.next())
			{
				activityId = jobExecutionResultSet.getString(activityIdColumn);
				payLoadValue = jobExecutionResultSet.getString(payLoadColumn);
				InnerLoop:	for(String eachItemId : itemIds)
				{
					if(payLoadValue.indexOf(eachItemId) > 0)
					{
						payLoadFlag = true;
						scheduleId = jobExecutionResultSet.getString(scheduleIDColumn);
						if(!(scheduleId.equals("")))
						{
							break InnerLoop;
						}
					}						
				}						
			}		
			if((scheduleId.equals("")) && (checkCountFlag < 50))
			{
				Thread.sleep(5000);
				moGenericMethodsLog.info(String.format(paylodCreationWait, workFlowName));
				validationStepInformation(String.format(paylodCreationWait, workFlowName));
			}else if(!(scheduleId.equals(""))){
				moGenericMethodsLog.info(String.format(payloadCreated, workFlowName) + scheduleId);
				validationStepInformation(String.format(payloadCreated, workFlowName)+ scheduleId);
				break;
			}else {	
				moGenericMethodsLog.error(payLoadNotCreated+ workFlowName);
				validationErrorInformation(payLoadNotCreated+ workFlowName);
				Component.assertTrue(false, payLoadNotCreated+ workFlowName);
				break;
			}
		}while(!(payLoadFlag));
		return this;
	}

	/**
	 * This method is to check the status of created ScheduleId
	 * @param workFlowName
	 * @param scheduleID
	 * @param flagWorkFlow
	 * @param itemIds
	 * @return
	 * @throws Exception
	 */
	public MoGenericMethods2 checkScheduleIdStatus(String workFlowName, String scheduleID, String flagWorkFlow,
			List<String> itemIds) throws Exception
	{
		resultSet = getIntradayScheduleResultSet(workFlowName, scheduleID); 
		status = new String();
		jobTemplateId = new String();
		while(resultSet.next())
		{
			status = resultSet.getString(statusColumn);
			jobTemplateId = resultSet.getString(jobTemplateIdColumn);
			While1:		while(!(status.equals(completedBySupervisor)))
			{
				if(status.equals(payLoadCreated))
				{
					moGenericMethodsLog.info(String.format(payLoadCreatedStatus, scheduleID, payLoadCreated));
					validationStepInformation(String.format(payLoadCreatedStatus, scheduleID, payLoadCreated));
					String jobExecutionLogQuery = "SELECT " + statusColumn + "," + activityIdColumn + " from " + schedulerSchema + jobExecutionLogTable
							+ " Where " + workFlowNameColumn + " = '" + workFlowName + "'"
							+ " and " + scheduleIDColumn + " = '" + scheduleID + "' order by " + createdDateTimeColumn + " desc";
					ResultSet jobExecutionLogResultSet = getICSDBServerConnection(moServerDetails, schedulerSchema, jobExecutionLogQuery); 
					while(jobExecutionLogResultSet.next())
					{
						String jobExecutionLogStatus = jobExecutionLogResultSet.getString(statusColumn);
						if(!(jobExecutionLogStatus.equals("")))
						{
							if(jobExecutionLogStatus.equals(createdByTaskBuilder))
							{
								ResultSet resultSet = getScheduleWorkQueueResultSet(jobExecutionLogResultSet);
								while(resultSet.next())
								{
									String state = resultSet.getString(stateColumn);
									if(state.equals(createdByTaskBuilderWorkQueue))
									{
										moGenericMethodsLog.info(String.format(createByTaskBuilder, 
												jobExecutionLogResultSet.getString(activityIdColumn), createdByTaskBuilderWorkQueue));
										validationStepInformation(String.format(createByTaskBuilder,
												jobExecutionLogResultSet.getString(activityIdColumn), createdByTaskBuilderWorkQueue));
									}
								}
							}else if(jobExecutionLogStatus.equals(processedByAgent))
							{
								moGenericMethodsLog.info(String.format(createdAgentProcessed, scheduleID, processedByAgent));
								validationStepInformation(String.format(createdAgentProcessed, scheduleID, processedByAgent));
								ResultSet resultSet = getScheduleWorkQueueResultSet(jobExecutionLogResultSet);
								while(resultSet.next())
								{
									String state = resultSet.getString(stateColumn);
									if(state.equals(processingByAgent))
									{
										moGenericMethodsLog.info(String.format(createdAgentProcessed, 
												jobExecutionLogResultSet.getString(activityIdColumn), processingByAgent));
										validationStepInformation(String.format(createdAgentProcessed,
												jobExecutionLogResultSet.getString(activityIdColumn), processingByAgent));
									}
								}
							}else if(jobExecutionLogStatus.equals(successByAgent))
							{
								moGenericMethodsLog.info(String.format(successByAgentLog, scheduleID, successByAgent));
								validationStepInformation(String.format(successByAgentLog, scheduleID, successByAgent));
								ResultSet resultSet = getScheduleWorkQueueResultSet(jobExecutionLogResultSet);
								while(resultSet.next())
								{
									String state = resultSet.getString(stateColumn);
									if(state.equals(successByAgentWorkQueue))
									{
										moGenericMethodsLog.info(String.format(successByAgentWorkQueueLog, 
												jobExecutionLogResultSet.getString(activityIdColumn), successByAgentWorkQueue));
										validationStepInformation(String.format(successByAgentWorkQueueLog, 
												jobExecutionLogResultSet.getString(activityIdColumn), successByAgentWorkQueue));
									}
								}
								break While1;
							}else if(jobExecutionLogStatus.equals(failureByAgentWithHttp417))
							{
								moGenericMethodsLog.error(String.format(failedByAgentHttpCode417Log, scheduleID, failureByAgentWithHttp417) + workFlowName);
								validationErrorInformation(String.format(failedByAgentHttpCode417Log, scheduleID, failureByAgentWithHttp417) + workFlowName);
								ResultSet resultSet = getScheduleWorkQueueResultSet(jobExecutionLogResultSet);
								while(resultSet.next())
								{
									String state = resultSet.getString(stateColumn);
									if(state.equals(failureByAgentWorkQueue))
									{
										moGenericMethodsLog.error(String.format(failedByAgentWorkQueue,
												jobExecutionLogResultSet.getString(activityIdColumn), failureByAgentWorkQueue));
										validationErrorInformation(String.format(failedByAgentWorkQueue,
												jobExecutionLogResultSet.getString(activityIdColumn), failureByAgentWorkQueue));
									}
								}
								if(getExceptionLogResultSet().next())
								{
									moGenericMethodsLog.error(String.format(exceptionLog, getExceptionLogResultSet().getString(activityIdColumn), 
											getExceptionLogResultSet().getString(idColumn)) + getExceptionLogResultSet().getString(messageColumn));
									validationErrorInformation(String.format(exceptionLog, getExceptionLogResultSet().getString(activityIdColumn), 
											getExceptionLogResultSet().getString(idColumn)) + getExceptionLogResultSet().getString(messageColumn));
								}
							}
						}
						Thread.sleep(8000);
					}					
				}
				resultSet = getIntradayScheduleResultSet(workFlowName, scheduleID); 
				status = getICSRetrieveColumnValue(resultSet);
			}
		}
//		resultSet = getIntradayScheduleResultSet(workFlowName, scheduleID); 
//		while(resultSet.next())
//		{
//			status = resultSet.getString(statusColumn);
//			if(status.equals(completedBySupervisor))
//			{
//				moGenericMethodsLog.info(String.format(completedBySupervisorLog, scheduleID, completedBySupervisor));
//				validationStepInformation(String.format(completedBySupervisorLog, scheduleID, completedBySupervisor));
//			}else {
//				moGenericMethodsLog.info(String.format(supervisorPendingCompletion, scheduleID) + status);
//				Thread.sleep(8000);
//				resultSet = getIntradayScheduleResultSet(workFlowName, scheduleID);		
//			}			
//		}
	   return this;
	}

	/**
	 * This method is to check Batched and Dirty Flag condition for required WorkFlow
	 * @param itemIds
	 * @param workFlowName
	 * @throws Exception
	 */
	public MoGenericMethods2 checkBatchedAndDirtyFlags(List<String> itemIds, String workFlowName) throws Exception
	{
		for(String eachItemId : itemIds)
		{
			getActualFlagsFromItemState(eachItemId);
		}
		return this;
	}

	/**
	 * This method is to get SourceID of ISO content from Source table W.R.T given Items for given Workflow 
	 * @param workFlow
	 * @throws Exception
	 */
	public MoGenericMethods2 checkSourceIDOfISOcontent(String workFlow, List<String> ids) throws Exception
	{
    	String query = "Select " + sourceIDColumn + "," + sourceSKIDColumn + "," + isoContentXMLColumn + " FROM " + repositorySchema + sourceTable
				+ " Where " + messageTypeColumn + "='" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query);
		tempISOContent = new String();
		tempISOSourceSKID = new String(); 
		boolean flag = false;
		While : while(resultSet.next())
		{
			tempISOContent = resultSet.getString(isoContent);
			for(String eachItemId : ids)
			{
				if(tempISOContent.indexOf(eachItemId) > 0)
				{
					tempISOSourceID = resultSet.getString(sourceIDColumn);
					tempISOSourceSKID = resultSet.getString(sourceSKIDColumn);
					publishResults(true, String.format(sourceTableLogActual, workFlow)+ tempISOSourceID + " and SourceSKID : " + tempISOSourceSKID,
							String.format(sourceTableLogExpected, workFlow) + ids,
							String.format(sourceTableLogCheck, workFlow)+ ids);
					moGenericMethodsLog.info(String.format(sourceTableLogActual, workFlow)+ tempISOSourceID + " and SourceSKID : " + tempISOSourceSKID);
					flag = true;
					break While;
				}	
			}						
		}
		if(!flag)
		{
			moGenericMethodsLog.error(String.format(sourceTableLogError, workFlow));
			publishResults(false, String.format(sourceTableLogError, workFlow),
					String.format(sourceTableLogError, workFlow) + ids,
					String.format(sourceTableLogCheck, workFlow)+ ids);
			Component.assertTrue(false, String.format(sourceTableLogError, workFlow));			
		}
		return this;
	}

	/**
	 * This method is to get SourceID of ISO content from Source table W.R.T given Items for given WorkFlow 
	 * @param workFlow
	 * @throws Exception
	 */
	public MoGenericMethods2 checkSourceIDOfICNcontent(String workFlow, List<String> itemIds) throws Exception
	{
		String query = "Select " + sourceIDColumn + "," + sourceSKIDColumn + "," + icnContentXMLColumn + " FROM " + repositorySchema + sourceTable
				+ " Where " + messageTypeColumn + "='" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query);
		tempICNContent = new String();
		tempICNSourceSKID = new String();
		boolean flag = false;
	While:	while(resultSet.next())
		{
			tempICNContent = resultSet.getString(icnContent);
			tempICNSourceSKID = resultSet.getString(sourceSKIDColumn);
			for(String eachItemId : itemIds)
			{
				if(tempICNContent.indexOf(eachItemId) > 0)
				{
					tempICNSourceID = resultSet.getString(sourceIDColumn);
					publishResults(true, String.format(sourceTableLogActual, workFlow)+ tempICNSourceID + " and SourceSKID : " + tempISOSourceSKID,
							String.format(sourceTableLogError, workFlow) + itemIds,
							String.format(sourceTableLogCheck, workFlow)+ itemIds);
					moGenericMethodsLog.info(String.format(sourceTableLogActual, workFlow)+ tempICNSourceID + " and SourceSKID : " + tempISOSourceSKID);
					flag = true;
					break While;
				}	
			}					
		}
		if(!flag)
		{
			moGenericMethodsLog.error(String.format(sourceTableLogError, workFlow));
			publishResults(false, String.format(sourceTableLogError, workFlow),
					String.format(sourceTableLogError, workFlow) + itemIds,
					String.format(sourceTableLogCheck, workFlow)+ itemIds);
			Component.assertTrue(false, String.format(sourceTableLogError, workFlow));			
		}
		return this;
	}

	/**
	 * This method is to check the required Item State and Flag status of Item table and ItemState table
	 * @param workFlow
	 * @param itemIds
	 * @param processFlow
	 * @throws Exception
	 */
	public MoGenericMethods2 checkStateFromItemAndItemStateTable(String workFlow, HashMap<String,String> itemIdAndState, String processFlow) throws Exception
	{
		validateItemTable(workFlow, itemIdAndState);
		validateItemStateTable(workFlow, itemIdAndState, processFlow);
		return this;
	}
	
	/**
	 * This method is to Check Gender Column In ItemState Table 
	 * @param itemIds
	 * @param transactionType
	 * @return
	 */
	public MoGenericMethods2 checkGenderInItemState(List<String> itemIds, String transactionType)
	{
		return this;
	}
	
	/**
	 * This method is to check RoleFlag status for Payer/Beneficiary/Collector process in ItemState table
	 * @param itemIds
	 * @param transactionType
	 * @return
	 */
	public MoGenericMethods2 checkRoleFlagInItemState(List<String> itemIds, String transactionType)
	{
		return this;
	}
	
	/**
	 * This method is to check RoleFlag status for Payer/Beneficiary/Collector process in TransactionSetState table
	 * @param tsetIds
	 * @param transactionType
	 * @return
	 */
	public MoGenericMethods2 checkRoleFlagInTransactionSetState(List<String> tsetIds, String transactionType)
	{
		return this;
	}

	/**
	 * This method is to check the required TsetId State and Flag status of TxSet table and TxSetState table
	 * @param workFlow
	 * @param tsetIds
	 * @param processFlow
	 */
	public MoGenericMethods2 checkStateTxSetAndTxSetStateTable(String workFlow, HashMap<String,String> tsetIdAndState, String processFlow) throws Exception
	{
		validateTxSetTable(workFlow, tsetIdAndState);
		validateTxSetStateTable(workFlow, tsetIdAndState, processFlow);
		return this;
	}

	/**
	 * This method is to check MessageFlow Insert in Receive Staging table
	 * @param workFlow
	 * @throws Exception
	 */
	public MoGenericMethods2 checkMessageFlowInsertInReceiveStaging(String workFlow, List<String> extractId) throws Exception
	{
		Thread.sleep(10000);
		String query = "SELECT " + msgColumnXMLColumn + "," + msgIdColumn + " FROM " + repositorySchema + receiveStagingTable
				+ " Where " + messageTypeColumn + "='" + workFlow + "'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query);
		actualContentInReceiveStaging = new String();
		rowId = new String();
		while(resultSet.next())
		{
			actualContentInReceiveStaging = resultSet.getString(msgColumn);
			if((actualContentInReceiveStaging.indexOf(extractId.get(0)) > 0))
			{
				rowId = resultSet.getString(msgIdColumn);
				break;
			}
		}
		return this;
	}
	
	/**
	 * This method is to check MO to KAPPA file save in respective EAV location 
	 * @param workFlow
	 * @param itemIds
	 * @param activityId
	 * @return
	 * @throws Exception
	 */
	public MoGenericMethods2 checkSavedFileInEAVLocation(String workFlow, String activityId) throws Exception
	{
		String eavLocation = getEAVLocation(workFlow);
		String query = "SELECT " + fileNameMsgIdColumn + " FROM " +  repositorySchema + sourceTrackerTable
				+ " Where " + activityIdColumn + "= '" + activityId + "'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query);
		String fileName = new String();
		while(resultSet.next())
		{
			fileName = resultSet.getString(fileNameMsgIdColumn);
		}
		File folder = new File(getEAVLocation(workFlow));
		File[] listofFile = folder.listFiles();
		String strFile = null;
		boolean flag = false;
		for(int i=0;i<listofFile.length;i++)
		{
			strFile = listofFile[i].getName();
			if((strFile.trim()).equals(fileName))
			{
				flag = true;
				break;
			}
		}
		if(flag)
		{
			moGenericMethodsLog.info("File name " + strFile + " for workflow " + workFlow + " saved in EAV Location " + eavLocation + " with proper format");
		    validationStepInformation("File name " + strFile + " for workflow " + workFlow + " saved in EAV Location " + eavLocation + " with proper format");
		}else {
			moGenericMethodsLog.error("File with "+ strFile + " not available in EAV Location " + eavLocation + " with proper format");
		    validationErrorInformation("File with "+ strFile + " not available in EAV Location " + eavLocation + " with proper format");
		}	
		return this;
	}
	
	/**
	 * This method is to get 
	 */
	public synchronized static String getColumnKeyAsXmlFileName()
	{
		return keyColumnDataAsXmlFileName;
	}
	
	public static String getColumnkeyAsTsetData()
	{
		return keyColumnTsetDataValue;
	}
	
	public static String getColumnkeyAsMsgIdValue()
	{
		return keyColumnMsgIdValue;
	}
	
	public static String getColumnkeyAsFMextractId()
	{
		return keyColumnFMextractId;
	}
	
	public static String getColumnKeyAsDMextractId()
	{
		return keyColumnDMextractId;
	}
	
	public String getNewReplacedMsgIdData()
	{
		return newReplacedMsgIdData;
	}
	
	public String getNewReplacedTestData()
	{
		return newReplacedTestData;
	}
	
	public String getNewExtractId06FM01()
	{
		return newExtractId06FM01;
	}
	
	public String getNewExtractId06DM01()
	{
		return newExtractId06DM01;
	}
	
	/**
	 * This method is to set fileName for 04SM01
	 * @param fileName
	 */
	public MoGenericMethods2 setMsg04SM01FileName(String fileName)
	{
		msg04SM01FileName = fileName;
		return this;
	}

	/**
	 * This method is to get fileName for 04SM01
	 * @return
	 */
	public String getMsg04SM01FileName()
	{
		return msg04SM01FileName;
	}
	
	/**
	 * This method is to set fileName for 05SM01
	 * @param fileName
	 */
	public MoGenericMethods2 setMsg05SM01FileName(String fileName)
	{
		msg05SM01FileName = fileName;
		return this;
	}

	/**
	 * This method is to get fileName for 06SM01
	 * @return
	 */
	public String getMsg05SM01FileName()
	{
		return msg05SM01FileName;
	}

	/**
	 * This method is to set fileName for 06SM01
	 * @param fileName
	 */
	public MoGenericMethods2 setMsg06SM01FileName(String fileName)
	{
		msg06SM01FileName = fileName;
		return this;
	}

	/**
	 * This method is to get fileName for 06SM01
	 * @return
	 */
	public String getMsg06SM01FileName()
	{
		return msg06SM01FileName;
	}
	
	/**
	 * This method is to set fileName for 06KM01
	 * @param fileName
	 */
	public MoGenericMethods2 setMsg06KM01FileName(String fileName)
	{
		msg06KM01FileName = fileName;
		return this;
	}

	/**
	 * This method is to get fileName for 01KM01
	 * @return
	 */
	public String getMsg01KM01FileName()
	{
		return msg06KM01FileName;
	}
	
	/**
	 * This method is to set fileName for 01KM01
	 * @param fileName
	 */
	public MoGenericMethods2 setMsg01KM01FileName(String fileName)
	{
		msg06KM01FileName = fileName;
		return this;
	}

	/**
	 * This method is to get fileName for 06KM01
	 * @return
	 */
	public String getMsg06KM01FileName()
	{
		return msg06KM01FileName;
	}

	/**
	 * This method to get created ScheduleId
	 * @return
	 */
	public String getScheduleId()
	{
		return scheduleId;
	}

	/**
	 * This method to get created RowId
	 * @return
	 */
	public String getRowId()
	{
		return rowId;
	}
	
	
	/**
	 * This method to get created ExtractId
	 * @return
	 */
	public ArrayList<String> getExtractId()
	{
		return extractId;
	}
	
	/**
	 * This method is to get DebitItemIds
	 * @return
	 */
	public ArrayList<String> getDebitItems()
	{
		return expectedDebitItemId;
	}	
	
	/**
	 * This method is to get TsetIds
	 * @return
	 */
	public ArrayList<String> getTsetIds()
	{
		return expectedTsetId;
	}
	
	/**
	 * This method is to get SourceID of ICN Content created
	 * @return
	 */
	public String getICNSourceID()
	{
		return tempICNSourceID;
	}
	
	/**
	 * This method is to get SourceID of ISO Content created
	 * @return
	 */
	public String getISOSourceID()
	{
		return tempISOSourceID;
	}

	/**
	 * This method is to get ICN Content from Sourct table
	 */
	public String getICNContent()
	{
		return tempICNContent;
	}
	
	/**
	 * This method is to get ActivityId created for the Payload
	 * @return
	 */
	public String getActivityId()
	{
		return activityId;
	}

	/**
	 * This method is to Validate State from Item table for required ItemId and given WorkFlow
	 * @param workFlow
	 * @param itemIdAndState
	 * @throws Exception
	 */
	private MoGenericMethods2 validateItemTable(String workFlow , HashMap<String,String> itemIdAndState) throws Exception
	{
		for(Entry<String, String> eachItemDetails : itemIdAndState.entrySet())
		{
			String itemId = eachItemDetails.getKey();
			String entityState = eachItemDetails.getValue();
			String actualStateValue = getStateValueFromItemTable(itemId, workFlow);	
			boolean resultFlag = entityState.equals(actualStateValue);
			if(resultFlag)
			{
				publishResults(resultFlag, actualStateValue , entityState, String.format(itemTableStateCheck, workFlow) + itemId);
				moGenericMethodsLog.info(String.format(itemTableStateLogResult, actualStateValue, entityState, workFlow) + itemId);
			}
		}
		return this;
	}

	/**
	 * This method is to Validate State from ItemState table for required ItemId and given WorkFlow and ProcessFlow
	 * @param workFlow
	 * @param itemIdAndState
	 * @param processFlow
	 * @throws Exception
	 */
	private MoGenericMethods2 validateItemStateTable(String workFlow , HashMap<String,String> itemIdAndState, String processFlow) throws Exception
	{
		for(Entry<String, String> eachItemDetails : itemIdAndState.entrySet())
		{
			String itemId = eachItemDetails.getKey();
			String entityState = eachItemDetails.getValue();
			if(processFlow.equals(payer))
			{
				String actualStateValue = getPayerStateFromItemStateTable(itemId);
				boolean resultFlag = entityState.equals(actualStateValue);
				if(resultFlag)
				{
					publishResults(resultFlag, actualStateValue , entityState, String.format(itemStateTableStateCheck, workFlow) + itemId );
					moGenericMethodsLog.info(String.format(itemStateTableStateLogResult, actualStateValue, entityState, workFlow) + itemId );
				}
			}else if(processFlow.equals(beneficiary))
			{
				String actualStateValue = getBeneficiaryStateFromItemStateTable(itemId);
				boolean resultFlag = entityState.equals(actualStateValue);
				if(resultFlag)
				{
					publishResults(resultFlag, actualStateValue , entityState, String.format(itemStateTableStateCheck, workFlow) + itemId );
					moGenericMethodsLog.info(String.format(itemStateTableStateLogResult, actualStateValue, entityState, workFlow) + itemId );
				}
			}else if(processFlow.equals(collector))
			{
				String actualStateValue = getCollectorStateFromItemStateTable(itemId);
				boolean resultFlag = entityState.equals(actualStateValue);
				if(resultFlag)
				{
					publishResults(resultFlag, actualStateValue , entityState, String.format(itemStateTableStateCheck, workFlow) + itemId );
					moGenericMethodsLog.info(String.format(itemStateTableStateLogResult, actualStateValue, entityState, workFlow) + itemId );
				}
			}
		}
		return this;
	}

	/**
	 * This method is to Validate State from TransactionSet table for required TsetId and given WorkFlow
	 * @param workFlow
	 * @param tsetIdAndState
	 */
	private MoGenericMethods2 validateTxSetTable(String workFlow , HashMap<String,String> tsetIdAndState) throws Exception
	{
		for(Entry<String, String> eachTsetDetails : tsetIdAndState.entrySet())
		{
			String tsetId = eachTsetDetails.getKey();
			String tsetIdState = eachTsetDetails.getValue();
			String actualStateValue = getStateValueFromTransactionSetTable(tsetId, workFlow);	
			boolean resultFlag = tsetIdState.equals(actualStateValue);
			if(resultFlag)
			{
				publishResults(resultFlag, actualStateValue , tsetIdState, String.format(transactionSetTableStateCheck, workFlow) + tsetId);
				moGenericMethodsLog.info(String.format(transactionSetTableStateLogResult, actualStateValue, tsetIdState, workFlow) + tsetId);
			}
		}
		return this;
	}

	/**
	 * This method is to Validate State from TransactionSetState table for required TsetId and given WorkFlow
	 * @param workFlow
	 * @param tsetIdAndState
	 */
	private MoGenericMethods2 validateTxSetStateTable(String workFlow , HashMap<String,String> tsetIdAndState, String processFlow) throws Exception
	{	
		for(Entry<String, String> eachTsetDetails : tsetIdAndState.entrySet())
		{
			String tsetId = eachTsetDetails.getKey();
			String tsetIdState = eachTsetDetails.getValue();
			if(processFlow.equals(payer))
			{
				String actualStateValue = getPayerStateFromTransactionSetStateTable(tsetId);
				boolean resultFlag = tsetIdState.equals(actualStateValue);
				if(resultFlag)
				{
					publishResults(resultFlag, actualStateValue , tsetIdState, String.format(itemStateTableStateCheck, workFlow) + tsetId );
					moGenericMethodsLog.info(String.format(itemStateTableStateLogResult, actualStateValue, tsetIdState, workFlow) + tsetId );
				}
			}else if(processFlow.equals(beneficiary))
			{
				String actualStateValue = getBeneficiaryStateFromTransactionSetState(tsetId);
				boolean resultFlag = tsetIdState.equals(actualStateValue);
				if(resultFlag)
				{
					publishResults(resultFlag, actualStateValue , tsetIdState, String.format(transactionSetStateTableStateCheck, workFlow) + tsetId );
					moGenericMethodsLog.info(String.format(transactionSetStateTableStateLogResult, actualStateValue, tsetIdState, workFlow) + tsetId );
				}
			}else if(processFlow.equals(collector))
			{
				String actualStateValue = getCollectorStateFromTransactionSetState(tsetId);
				boolean resultFlag = tsetIdState.equals(actualStateValue);
				if(resultFlag)
				{
					publishResults(resultFlag, actualStateValue , tsetIdState, String.format(transactionSetStateTableStateCheck, workFlow) + tsetId );
					moGenericMethodsLog.info(String.format(transactionSetStateTableStateLogResult, actualStateValue, tsetIdState, workFlow) + tsetId );
				}
			}			
		}
		return this;
	}

	/**
	 * This method is to get the value of State Column from Item table for given ItemId and WorkFlow
	 * @param itemId
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */
	private String getStateValueFromItemTable(String itemId, String workFlow) throws Exception
	{
		String query = "Select " + stateColumn + " FROM " + repositorySchema + itemTable
				+ " Where " + itemIdColumn + "='" + itemId + "' and " + internalMessageTypeColumn + "= '" + workFlow + "'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/**
	 * This method is to get the value of State column from TransactionSet table for given TsetId and WorkFlow
	 * @param tsetId
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */
	private String getStateValueFromTransactionSetTable(String tsetId, String workFlow) throws Exception
	{
		String query = "Select " + stateColumn + " FROM " + repositorySchema + transactionSetTable
				+ " Where " + tsetIDColumn + " Like '" + tsetId + "%' and " + internalMessageTypeColumn + "= '" + workFlow + "'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/**
	 * This method is to get the value of PayerTsetState column from TransactionSetState table for given TsetId
	 * @param tsetId
	 * @return
	 * @throws Exception
	 */
	private String getPayerStateFromTransactionSetStateTable(String tsetId) throws Exception
	{
		String query = "Select " + payerTsetStateColumn + " FROM " + repositorySchema + transactionSetStateTable
				+ " Where " + tsetIDColumn + " Like '%" + tsetId + "%'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/**
	 * This method is to get the value of BeneficiaryTsetState column from TransactionSetState table for given TsetId
	 * @param tsetId
	 * @return
	 * @throws Exception
	 */
	private String getBeneficiaryStateFromTransactionSetState(String tsetId) throws Exception
	{
		String query = "Select " + beneficiaryTsetStateColumn + " FROM " + repositorySchema + transactionSetStateTable
				+ " Where " + tsetIDColumn + " Like '" + tsetId + "%'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}
	
	/**
	 * This method is to get the value of CollectorTsetState column from TransactionSetState table for given TsetId
	 * @param tsetId
	 * @return
	 * @throws Exception
	 */
	private String getCollectorStateFromTransactionSetState(String tsetId) throws Exception
	{
		String query = "Select " + collectorTsetStateColumn + " FROM " + repositorySchema + transactionSetStateTable
				+ " Where " + tsetIDColumn + " Like '" + tsetId + "%'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/**
	 * This method is to get the value of PayerItemState column from ItemState table for given ItemId
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	private String getPayerStateFromItemStateTable(String itemId) throws Exception
	{
		String query = "Select " + payerItemStateColumn + " FROM " + repositorySchema + itemStateTable
				+ " Where " + itemIdColumn + "='" + itemId + "'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/**
	 * This method is to get the value of BeneficiaryItemState column from ItemState table for given ItemId
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	private String getBeneficiaryStateFromItemStateTable(String itemId) throws Exception
	{
		String query = "Select " + beneficiaryItemStateColumn + " FROM " + repositorySchema + itemStateTable
				+ " Where " + itemIdColumn + "='" + itemId + "'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}
	
	/**
	 * This method is to get the value of CollectorItemState column from ItemState table for given ItemId
	 * @param itemId
	 * @return
	 * @throws Exception
	 */
	private String getCollectorStateFromItemStateTable(String itemId) throws Exception
	{
		String query = "Select " + collectorItemStateColumn + " FROM " + repositorySchema + itemStateTable
				+ " Where " + itemIdColumn + "='" + itemId + "'";
		resultSet = getICSDBServerConnection(moServerDetails, repositorySchema, query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/**
	 * This method is to get actual flag conditions from ItemState table 
	 * @param itemId
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	private MoGenericMethods2 getActualFlagsFromItemState(String itemId) throws Exception
	{
		String query = "SELECT " + payerItemDirtyFlagColumn + "," + payerItemBatchedFlagColumn + "," + beneficiaryItemBatchedFlagColumn + "," +
				beneficiaryItemDirtyFlagColumn + "," + " FROM " + repositorySchema + exceptionLogTable 
				+ " Where " + itemIdColumn + "='" + itemId + "'";
		ResultSet result = getICSDBServerConnection(moServerDetails, repositorySchema, query); 
		if(result.next())
		{
			actualPayerDirtyFlag = result.getString(payerItemDirtyFlagColumn);
			actualPayerBatchedFlag = result.getString(payerItemBatchedFlagColumn);
			actualBeneficiaryDirtyFlag = result.getString(beneficiaryItemDirtyFlagColumn);
			actualBeneficiaryBatchedFlag = result.getString(beneficiaryItemBatchedFlagColumn);
		}
		return this;
	}

	/**
	 * This method is to get the required ResultSet of IntradaySchedule
	 * @param workFlowName
	 * @param scheduleID
	 * @return
	 * @throws Exception
	 */
	private ResultSet getIntradayScheduleResultSet(String workFlowName , String scheduleID) throws Exception
	{
		String query = "SELECT " + statusColumn + "," + jobTemplateIdColumn + " from " + schedulerSchema + intradayScheduleTable
				+ " Where " + workFlowNameColumn + " = '" + workFlowName + "'"
				+ " and " + scheduleIDColumn + " = '" + scheduleID + "' order by " + updatedDateTimeColumn + " desc";
		return getICSDBServerConnection(moServerDetails, schedulerSchema, query); 
	}

	/**
	 * This method is to get the required ResultSet of ScheduleWorkQueue
	 * @param resultSet
	 * @return
	 * @throws Exception
	 */
	private ResultSet getScheduleWorkQueueResultSet(ResultSet resultSet) throws Exception
	{
		String query = "SELECT " + stateColumn + "," + activityIdColumn + " FROM " + schedulerSchema + scheduledWorkQueueTable
				+ " Where " + activityIdColumn + "= '" + resultSet.getString(activityIdColumn) + "'";
		return getICSDBServerConnection(moServerDetails, schedulerSchema, query); 
	}

	/**
	 * This method is to get the required ResultSet of ExceptionLog table
	 * @param resultSet
	 * @return
	 * @throws Exception
	 */
	private ResultSet getExceptionLogResultSet() throws Exception
	{
		String query = "SELECT " + sourceColumn + "," + stackTraceColumn + "," + idColumn + "," + activityIdColumn + "," + messageColumn + " FROM " 
				+ repositorySchema + exceptionLogTable	+ " Where " + activityIdColumn + "='" + activityId + "' and " + stackTraceColumn + " IS NOT NULL";
		return getICSDBServerConnection(moServerDetails, repositorySchema, query); 
	}

	/**
	 * This ENUM class is to get the WorkFlow names
	 * @author MuluguUm
	 *
	 */
	private enum WorkFlowNames {
		MSG06SM01, MSG06MA01, MSG06MF01, MSG06FM01, MSG06MA02, MSG06MK01, MSG06KM01, MSG06MA03, MSG06MD01, MSGPTMA01, MSGPTMR01,
		MSGPERM01, MSGPEMA01, MSGPRRM01, MSGPRMA01, MSGPRMD01, MSG06DM01, MSG06MA04, MSG07MS01, MSG07MA01, MSG08SM01, MSG08MA01,
		MSG11SM01, MSG11MA01, MSG11MA02, MSG12SM01, MSG12MA01, MSG12MA02, MSG05SM01, MSG05MA01, MSG05MF01, MSG05FM01, MSG05MA02,
		MSG05MK01, MSG05KM01, MSG05MA03, MSG13SM01, MSG13MM01, MSG13MA01, MSG13MA04, MSG13MD01, MSG13MD02, MSG13DM01, MSG13DM02, 
		MSG13MA02, MSG04SM01, MSG04MM01, MSG04MA01, MSG09SM01, MSG09MA01, MSG01FM01, MSG01MA02, MSG01MK01, MSG01MA03, MSG01MD01, MSG01DM01,
		MSG01MA04, MSG01MA05, MSG01MS01, MSG01MA06, MSG01MA01, MSG01KM01, MSG01MM01, MSG02SM01, MSG02MA01, MSG15SM01, MSG15MA01,
		MSG01MD02, MSG01DM02, MSG03SM01, MSG03MA01, MSGREXM01, MSGREMA01;
	}

	/**
	 * This method call is to create JobTemplate for required Message flow
	 * @param workFlowName
	 * @param templateName
	 * @param firstRunStartTime
	 * @param lastRunStartTime
	 * @param jobParamId
	 * @throws Exception
	 */
	private MoGenericMethods2 createJobTemplate(String workFlowName, String templateName , 
			String firstRunStartTime, String lastRunStartTime, String jobParamId) throws Exception
	{
		String query = "INSERT INTO " + schedulerSchema + jobTemplateTable + " VALUES ('" + workFlowName + "' ,'" + templateName + "' , 'HSBC' , 1 ,'" + firstRunStartTime + "' ,"
				+ "'pd0' , '" + jobParamId + "' , 0 ,'" + jobTemplateEndTime + "' ,'pd0' , '" + jobParamId + "' , 0 , '" + jobParamId + "' , 1 ,'Demo' , GETDATE() , System_User ,GETDATE() , System_User"
				+ ", 1)"; 
		try{
			insertRecordInIcsDB(moServerDetails, schedulerSchema, query);
		}catch(Exception e)
		{
			validationErrorInformation(jobTemplatesException + workFlowName);
			moGenericMethodsLog.error(jobTemplatesException + workFlowName);
			Component.assertTrue(false, jobTemplatesException + workFlowName);
		}
		moGenericMethodsLog.info(jobTemplateEachWorkFlow + workFlowName);
		return this;
	}	
}
