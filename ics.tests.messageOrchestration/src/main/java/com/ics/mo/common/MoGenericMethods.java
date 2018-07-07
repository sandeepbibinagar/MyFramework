package com.ics.mo.common;

import java.io.File;
import java.io.IOException;
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


public class MoGenericMethods extends GenericMethodUtilis {
	protected static String moDbServer, moDbName ;
	protected static String moInputExcelSheetName = "Common" ;
	protected static String moInputSheetTestCaseAsCommon = "Common" ;
	protected static String moInputSheetKeyAsServerName = "MOServerName", document08SM01Status, msg05KM01FileName="" ;
	protected static String moInputExcelSheetKeyForDBName = "MODBName" ;
	protected static String scheduleId;
	protected static String columnICNContent = "ICNContent", columnPayLoad = "PayLoad", columnScheduleID = "ScheduleID", dbPrefix;
	protected static String activityId, jobTemplateId,  tempISOSourceID , tempICNSourceID, columnSourceID = "SourceID";
	protected static boolean tempResult;
	protected static String actualICNContentMsgFM01InReceiveStaging, actualRowIDMsgFM01;;
	protected static String actualIntraDayScheduleStatus, expectedIntraDayScheduleStatus, actualJobExecutionLogStatus, expectedJobExecutionLogStatus;
	protected static String actualScheduleWorkQueueStatus, expectedScheduleWorkQueueStatus, actualSourceTrackerStatus, expectedSourceTrackerStatus;
	protected static String actualPayerDirtyFlag , expectedDirtyFlag, actualPayerBatchedFlag, expectedBatchedFlag, actualBeneficiaryDirtyFlag, actualBeneficiaryBatchedFlag;
	protected static String actualPostingDirtyFlag, actualPostingBatchedFlag;
	protected static String keyDataAsIntraDayScheduleStatus = "IntradaySchedule Status", keyDataAsJobExecutionLogStatus = "JobExecutionLog Status"; 
	protected static String keyDataAsScheduleQueue = "ScheduleWorkQueue Status", keyDataAsSourceTracker = "SourceTracker Status";
	protected static String columnISOContent = "ISOContent", columnSourceSKID = "SourceSKID", columnMsgId = "Msg_Id", columnStateDivision = "SourceStateRevision";
	protected static String sourceID, sourceSKID, actualSourceStateDivision;
	protected static String keyDataAsPayerItemDirtyFlag = "ItemState PayerItemDirtyFlag", keyDataAsPayerBatchedFlag = "ItemState PayerItemBatchedFlag";
	protected static String keyDataAsSourceBatchedFlag = "SourceBatchedFlag", keyDataAsSourceDirtyFlag = "SourceDirtyFlag";
	protected static HashMap<String, String> initialStateFromFred = new LinkedHashMap<String, String>();
	protected static HashMap<String, String> fraudResultFromKappa = new LinkedHashMap<String, String>();
	protected static HashMap<String, Float> thresholdValueForItems = new LinkedHashMap<String, Float>();
	protected static HashMap<String, String> msg07MS01ValuesForItems = new LinkedHashMap<String, String>();
	protected static HashMap<String, HashMap<String, String>> itemAndFREDCodeLineDetails = new LinkedHashMap<String, HashMap<String, String>>();
	protected static HashMap<String, String> fredSortCodeDetails = new LinkedHashMap<String, String>(); 
	protected static HashMap<String, HashMap<String, String>> itemAndFREDCaptureInfoDetails = new LinkedHashMap<String, HashMap<String, String>>();
	protected static HashMap<String, String> fredCaptureInfoDetails = new LinkedHashMap<String, String>(); 
	protected static String kappaResultAsFraud = "Fraudulent", kappaResultAsSuspect = "Suspect", kappaResultAsOK = "OK", kappaResultAsNotProcessed = "Not Processed";
	protected static boolean fredState510Flag, fredState515Flag,fredState20Flag, fredState21Flag ,codelineCorrectedFlag;
	protected static String switchIntialStateAs500 = "500", fredIntialStateAs510 = "510", fredInitialStateAs515 = "515", fredIntialStateAs20 = "20", fredInitialStateAs21 = "21";
	protected static String derivedEntityStateAs520 = "520", derivedEntityStateAs525 = "525" , switchIntialStateAs10 = "10", derivedEntityStateAs50 = "50", derivedEntityStateAs51 = "51";
	protected static boolean fraudulentFlag , notProcessedFlag, suspectFlag, okFlag;
	protected static boolean thresholdFlag99KOrLess, thresholdFlag100KOrLess, thresholdFlag100KOrMore;
	protected static String expectedDerivedEntityState , payLoadCreatedTime , tempISOContent, tempICNContent ;
	protected static String actualSourceBatchedFlag, actualSourceDirtyFlag,	expectedSourceDirtyFlag, expectedSourceBatchedFlag;
	protected static HashMap<String, String> entityStateMapForDebitItem = new LinkedHashMap<String, String>();
	protected static HashMap<String, String> entityStateMapForCreditItem = new LinkedHashMap<String, String>();
	protected static HashMap<String, String> entityStateMapForCreditItemForBeneficiary = new LinkedHashMap<String, String>();
	protected static HashMap<Integer, String> createdJobParams = new LinkedHashMap<Integer, String>();
	protected static boolean msgFlowFlag = true;
	protected static String actualBeneficiaryItemStatus, keyDataAsBeneficiaryItemStateForDebit = "BeneficiaryItemStateForDebit";
	protected static String expectedBeneficiaryItemStateStatusForDebit, expectedBeneficiaryItemStateStatusForCredit;
	protected static String keyDataAsBeneficiaryItemStateForCredit, expectedBeneficiaryTsetState;
	protected static String actualBeneficiaryTsetState, keyDataAsBeneficiaryTsetState = "BeneficiaryTsetState";
	protected static HashMap<String, Boolean> codeLineCorrectedChangeFlag = new LinkedHashMap<String, Boolean>(), codeLineSerialChangeFlag = new LinkedHashMap<String, Boolean>();
	protected static HashMap<String, Boolean> codeLineSortCodeChangeFlag = new LinkedHashMap<String, Boolean>(), codeLineAccountNoChangeFlag = new LinkedHashMap<String, Boolean>();
	protected static HashMap<String, Boolean> codeLineTxCodeChangeFlag =  new LinkedHashMap<String, Boolean>();
	protected static String serialNo = "SerialNo", sortCode = "SortCode", accountNo = "Account" , transactionCode = "TranCode", amount = "Amount";
	protected static String actualBeneficiaryItemDirtyFlag , expectedBeneficiaryItemDirtyFlag, actualBeneficiaryBatchedDirtyFlag, expectedBeneficiaryBatchedDirtyFlag;
	protected static String keyDataAsBeneficiaryItemDirtyFlag = "ItemState BeneficiaryItemDirtyFlag", keyDataAsBeneficiaryBatchedFlag = "ItemState BeneficiaryItemBatchedFlag";
	protected static List<String> itemIdWithNoExtracts ;
	public static String keyColumnDataAsXmlFileName = "xmlFileName";
	public static List<String> itemSKIDs = new ArrayList<String>();
	public static String postingSourceID = "",keyColumnData = "", rowId = "", msg08SM01FileName = "", sourceIdMsg07MS01="", msg06SM01FileName="",msg04SM01FileName="", msg13SM01FileName="",
			msg09SM01FileName="",msg05SM01FileName="", msg06KM01FileName="", msg12SM01FileName="", msg11SM01FileName="";
	public static String permInputFileXmlContent;
	public static String prrmInputFileXmlContent;
	public static HashMap<String, String> permFileEntityStates = new LinkedHashMap<String, String>();
	public static HashMap<String, String> prrmFileEntityStates = new LinkedHashMap<String, String>();
	public static List<String> derivedState520ItemIds = new ArrayList<String>();	
	protected static final Logger moGenericMethodsLog = Logger.getLogger(MoGenericMethods.class.getSimpleName()) ;

	/**
	 * This method is to Set required MO - DB Server details to connect
	 * @param inputExcelName
	 */
	public static void setMoDetails(String inputExcelName) {
		moDbServer = getRequiredStTestData(inputExcelName, moInputExcelSheetName, moInputSheetTestCaseAsCommon,
				moInputSheetKeyAsServerName);
		moDbName = getRequiredStTestData(inputExcelName, moInputExcelSheetName, moInputSheetTestCaseAsCommon, 
				moInputExcelSheetKeyForDBName);		
		setDBPrefix(moDbName.substring(moDbName.indexOf("MORepository") + "MORepository".length()));
		defaultEntityStateMapForDebitItems();
		defaultEntityStateMapForCreditItems();
		defaultEntityStateMapForCreditItemsForBeneficiary();
		moGenericMethodsLog.info("MODB instance : " + moDbServer + " connected");
	}	

	//	/*
	//	 * 1. Get MO DB details
	//	 */
	//	public static void setMoDetails(String inputExcelName) {
	//		moDbServer = getRequiredStTestData(inputExcelName, moInputExcelSheetName, moInputSheetTestCaseAsCommon,
	//				moInputSheetKeyAsServerName);
	//		moDbName = getRequiredStTestData(inputExcelName, moInputExcelSheetName, moInputSheetTestCaseAsCommon, 
	//				moInputExcelSheetKeyForDBName);		
	//		setDBPrefix(moDbName.substring(0,moDbName.indexOf("MORepository")));
	//		defaultEntityStateMapForDebitItems();
	//		defaultEntityStateMapForCreditItems();
	//		defaultEntityStateMapForCreditItemsForBeneficiary();
	//		moGenericMethodsLog.info("MODB instance : " + moDbServer + " connected");
	//	}
	/**
	 * This method is to create JobParamIds for required ScheduleParams
	 * @throws Exception
	 */
	public static void setupJobParamIds() throws Exception
	{
		for(int paramId = 1 ; paramId <= 9 ; paramId++)
		{
			String additionalParams = ICSPropertiesConfig.getMOResourceFile().getString("jobscheduleParams_" + paramId);
			String query = "INSERT INTO " + getMoSchedulerTable() + ".[Config].[JobScheduleParams] ([Key],[Value],[CreatedDateTime],[CreatedBYUser],[UpdatedDateTime],[UpdatedByUser]) "
					+ "VALUES ('Parameters','" + additionalParams + "' ,GETDATE(),USER_NAME(),GETDATE(),USER_NAME())";
			insertRecordInIcsDB(getMoDbServerDetails(), getMoDbNameDetails(), query );	
			query = "SELECT JobParamId FROM " + getMoSchedulerTable() + ".[Config].[JobScheduleParams] "
					+ "where value = '"+ additionalParams + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				createdJobParams.put(paramId, resultSet.getString("JobParamId"));
			}			
		}
		moGenericMethodsLog.info("JobParamIds records created in JobScheduleParams table");
	}

	/**
	 * This method is to created JobTemplates for All the Message workflow
	 * @throws Exception
	 */
	public static void createJobTemplateForWorkFlows() throws Exception
	{
		String firstRunStartTime = new GenericMethodUtilis().dateFormat_2.format(new Date()) ;
		String template = " Template";
		switch(workFlow04SM01)
		{
		case "04SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow04SM01, workFlow04SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));
		case "04MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow04MA01, workFlow04MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));
		case "06SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow06SM01, workFlow06SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));
		case "06MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow06MA01, workFlow06MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "06MF01":
			createJobTemplateIdForRequiredWorkFlow(workFlow06MF01, workFlow06MF01 + template,  firstRunStartTime , "2359", createdJobParams.get(2));
		case "06FM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow06FM01, workFlow06FM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));
		case "06MA02":
			createJobTemplateIdForRequiredWorkFlow(workFlow06MA02, workFlow06MA02 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "06MK01":
			createJobTemplateIdForRequiredWorkFlow(workFlow06MK01, workFlow06MK01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "06KM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow06KM01, workFlow06KM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));		
		case "06MA03":
			createJobTemplateIdForRequiredWorkFlow(workFlow06MA03, workFlow06MA03 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "06MD01":
			createJobTemplateIdForRequiredWorkFlow(workFlow06MD01, workFlow06MD01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "11SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow11SM01, workFlow11SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "11MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow11MA01, workFlow11MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "12SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow12SM01, workFlow12SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "12MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow12MA01, workFlow12MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "PTMA01":
			createJobTemplateIdForRequiredWorkFlow(workFlowPTMA01, workFlowPTMA01 + template,  firstRunStartTime, "2359", createdJobParams.get(8));	
		case "PTMR01":
			createJobTemplateIdForRequiredWorkFlow(workFlowPTMR01, workFlowPTMR01 + template,  firstRunStartTime, "2359", createdJobParams.get(5));
		case "PERM01":
			createJobTemplateIdForRequiredWorkFlow(workFlowPERM01, workFlowPERM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));
		case "PEMA01":
			createJobTemplateIdForRequiredWorkFlow(workFlowPEMA01, workFlowPEMA01 + template,  firstRunStartTime, "2359", createdJobParams.get(5));	
		case "PRRM01":
			createJobTemplateIdForRequiredWorkFlow(workFlowPRRM01, workFlowPRRM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));
		case "PRMA01":
			createJobTemplateIdForRequiredWorkFlow(workFlowPRMA01, workFlowPRMA01 + template,  firstRunStartTime, "2359", createdJobParams.get(5));	
		case "PRMD01":
			createJobTemplateIdForRequiredWorkFlow(workFlowPRMD01, workFlowPRMD01 + template,  firstRunStartTime, "2359", createdJobParams.get(5));
		case "06DM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow06DM01, workFlow06DM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));		
		case "06MA04":
			createJobTemplateIdForRequiredWorkFlow(workFlow06MA04, workFlow06MA04 + template,  firstRunStartTime, "2359", createdJobParams.get(2));//4
		case "07MS01":
			createJobTemplateIdForRequiredWorkFlow(workFlow07MS01, workFlow07MS01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	//
		case "07MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow07MA01, workFlow07MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));		
		case "08SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow08SM01, workFlow08SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));			
		case "08MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow08MA01, workFlow08MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "09SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow09SM01, workFlow09SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(9));	
		case "09MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow09MA01, workFlow09MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(9));	
		case "05SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow05SM01, workFlow05SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));	
		case "05MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow05MA01, workFlow05MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));			
		case "05MF01":
			createJobTemplateIdForRequiredWorkFlow(workFlow05MF01, workFlow05MF01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));			
		case "05FM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow05FM01, workFlow05FM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));	
		case "05MA02":
			createJobTemplateIdForRequiredWorkFlow(workFlow05MA02, workFlow05MA02 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "05MK01":
			createJobTemplateIdForRequiredWorkFlow(workFlow05MK01, workFlow05MK01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "05KM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow05KM01, workFlow05KM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));		
		case "05MA03":
			createJobTemplateIdForRequiredWorkFlow(workFlow05MA03, workFlow05MA03 + template,  firstRunStartTime, "2359", createdJobParams.get(2));		
		case "13SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow13SM01, workFlow13SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(3));			
		case "13MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow13MA01, workFlow13MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(4));
		case "13MA02":
			createJobTemplateIdForRequiredWorkFlow(workFlow13MA02, workFlow13MA02 + template,  firstRunStartTime, "2359", createdJobParams.get(4));	
		case "13MD01":
			createJobTemplateIdForRequiredWorkFlow(workFlow13MD01, workFlow13MD01 + template,  firstRunStartTime, "2359", createdJobParams.get(4));
		case "13DM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow13DM01, workFlow13DM01 + template,  firstRunStartTime, "2359", createdJobParams.get(3));
		case "13MD02":
			createJobTemplateIdForRequiredWorkFlow(workFlow13MD02, workFlow13MD02 + template,  firstRunStartTime, "2359", createdJobParams.get(4));	
		case "13DM02":
			createJobTemplateIdForRequiredWorkFlow(workFlow13DM02, workFlow13DM02 + template,  firstRunStartTime, "2359", createdJobParams.get(3));	
		case "13MM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow13MM01, workFlow13MM01 + template,  firstRunStartTime, "2359", createdJobParams.get(4));
		case "13MA04":
			createJobTemplateIdForRequiredWorkFlow(workFlow13MA04, workFlow13MA04 + template,  firstRunStartTime, "2359", createdJobParams.get(4));		
		case "13MA99":
			createJobTemplateIdForRequiredWorkFlow(workFlow13MA99, workFlow13MA99 + template,  firstRunStartTime, "2359", createdJobParams.get(4));		
		case "12MA02":
			createJobTemplateIdForRequiredWorkFlow(workFlow12MA02, workFlow12MA02 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "11MA02":
			createJobTemplateIdForRequiredWorkFlow(workFlow11MA02, workFlow11MA02 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "01FM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow01FM01, workFlow01FM01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));	
		case "01MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MA01, workFlow01MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(1));	
		case "01MD01":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MD01, workFlow01MD01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "01MK01":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MK01, workFlow01MK01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "01KM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow01KM01, workFlow01KM01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "01MA02":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MA02, workFlow01MA02 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "01MA03":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MA03, workFlow01MA03 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "01MM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MM01, workFlow01MM01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "01MA04":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MA04, workFlow01MA04 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "01MA05":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MA05, workFlow01MA05 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "01MS01":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MS01, workFlow01MS01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "01MA06":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MA06, workFlow01MA06 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "02SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow02SM01, workFlow02SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "01DM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow01DM01, workFlow01DM01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "02MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow02MA01, workFlow02MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "15SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow15SM01, workFlow15SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "15MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow15MA01, workFlow15MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));	
		case "01MD02":
			createJobTemplateIdForRequiredWorkFlow(workFlow01MD02, workFlow01MD02 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "01DM02":
			createJobTemplateIdForRequiredWorkFlow(workFlow01DM02, workFlow01DM02 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "03SM01":
			createJobTemplateIdForRequiredWorkFlow(workFlow03SM01, workFlow03SM01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "03MA01":
			createJobTemplateIdForRequiredWorkFlow(workFlow03MA01, workFlow03MA01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "REXM01":
			createJobTemplateIdForRequiredWorkFlow(workFlowREXM01, workFlowREXM01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		case "REMA01":
			createJobTemplateIdForRequiredWorkFlow(workFlowREMA01, workFlowREMA01 + template,  firstRunStartTime, "2359", createdJobParams.get(2));
		}		
		moGenericMethodsLog.info("JobTemplates created for Message 4,5,6,7,8,9,11,12,13 and Postings");
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
	private static void createJobTemplateIdForRequiredWorkFlow(String workFlowName, String templateName , String firstRunStartTime, String lastRunStartTime, String jobParamId) throws Exception
	{
		String query = "INSERT INTO " + getMoSchedulerTable() + ".[Config].[JobTemplate] VALUES ('" + workFlowName + "' ,'" + templateName + "' , 'HSBC' , 1 ,'" + firstRunStartTime + "' ,"
				+ "'pd0' , '" + jobParamId + "' , 0 ,'" + "2359" + "' ,'pd0' , '" + jobParamId + "' , 0 , '" + jobParamId + "' , 1 ,'Demo' , GETDATE() , System_User ,GETDATE() , System_User"
				+ ", 1)"; 
		try{
			insertRecordInIcsDB(getMoDbServerDetails(), getMoDbNameDetails(), query );
		}catch(Exception e)
		{
			validationErrorInformation(e.getMessage());
			moGenericMethodsLog.error(e.getMessage());
			Component.assertTrue(false, "");
		}
		moGenericMethodsLog.info("JobTemplate created for workFlow "+ workFlowName);
	}	

	/**
	 * This method to trigger DTSX package for ScheduleId to create
	 * @throws Exception
	 */
	public static void packageRunForSODScheduleIds() throws Exception
	{
		boolean flag = false;
		moGenericMethodsLog.info("Running iPSL.ICS.MO.SOD.JobBuilder.dtsx package for JobTemplates of all the workflows");
		try{
			sqlCommandExecution(ICSPropertiesConfig.getMOResourceFile().getString("packageRun_withParameters"));
			flag = true;
			Thread.sleep(20000);
			moGenericMethodsLog.info("Successfully ran iPSL.ICS.MO.SOD.JobBuilder.dtsx package for JobTemplates of all the workflows");
		}catch(Exception e)
		{
			moGenericMethodsLog.error("Failed in iPSL.ICS.MO.SOD.JobBuilder.dtsx package run for JobTemplates of all the workflows");
			publishResults(flag, "Failed in iPSL.ICS.MO.SOD.JobBuilder.dtsx package run for JobTemplates of all the workflows" , "Should run iPSL.ICS.MO.SOD.JobBuilder.dtsx package for JobTemplates of all the workflows",
					"iPSL.ICS.MO.SOD.JobBuilder.dtsx package run for JobTemplates of all the workflows");
			Component.assertTrue(flag, "Failed in iPSL.ICS.MO.SOD.JobBuilder.dtsx run");
		}			
	}

	/**
	 * This method is to get specific tag data of required XML data of required Switch file
	 * @param testCaseName
	 * @param workFlow
	 * @param keyName
	 * @param regression
	 * @throws Exception
	 */
	public static void getInputXmlDataOfSwitchFile(String testCaseName, String workFlow, String keyName, String regression) throws Exception
	{
		expectedTxVersion = new ArrayList<String>();
		expectedTsetId = new ArrayList<String>();
		expectedDebitItemId = new ArrayList<String>();
		expectedSourceId = new ArrayList<String>();
		dayOneResponseWindow = new ArrayList<String>();
		dayTwoResponseWindow = new ArrayList<String>();
		getXMLFilePathOfRegressionCyle1(getInputSheetOfMoRegression(regression), testCaseName, workFlow, keyName );		
		expectedSourceId = setOutputFileData(regression,getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//MsgId", "MsgId");
		moGenericMethodsLog.info("Source-Id of " + workFlow + " input file : " + expectedSourceId);
		expectedTsetId = setOutputFileData(regression, getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//TxSetId", "TxSetId");
		moGenericMethodsLog.info("TxSetIds of " + workFlow + " input file : " + expectedTsetId);
		expectedTxVersion = setOutputFileData(regression, getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//TxSetVrsn", "TxSetVrsn");
		expectedCreditItemId = setOutputFileData(regression,getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//CdtItmId", "CdtItmId");
		moGenericMethodsLog.info("CreditItems of " + workFlow + " input file : " + expectedCreditItemId);
		expectedDebitItemId = setOutputFileData(regression,getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//DbtItmId", "DbtItmId");
		moGenericMethodsLog.info("DebitItems of " + workFlow + " input file : " + expectedDebitItemId);
		try{
			dayOneResponseWindow = setOutputFileData(regression,getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//DayOneRspnWndwStartDtTm", "DayOneRspnWndwStartDtTm");
			moGenericMethodsLog.info("DayOneResponseWindow of " + workFlow + " input file : " + dayOneResponseWindow);
		}catch(Exception e)
		{
			moGenericMethodsLog.info("DayOneResponseWindow of " + workFlow + " input file : " + null);
		}
		try{
			dayTwoResponseWindow = setOutputFileData(regression,getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//DayTwoRspnWndwStartDtTm", "DayTwoRspnWndwStartDtTm");
			moGenericMethodsLog.info("DayTwoResponseWindow of " + workFlow + " input file : " + dayTwoResponseWindow);
		}catch(Exception e)
		{
			moGenericMethodsLog.info("DayTwoResponseWindow of " + workFlow + " input file : " + null);
		}
	}

	/**
	 * This method is to get the specific Tag data of XML content of required FRED file
	 * @param testCaseName
	 * @param workFlow
	 * @param keyName
	 * @param regression
	 * @throws Exception
	 */
	public static void getInputXmlDataOfMoToFredFile(String testCaseName, String workFlow, String keyName, String regression) throws Exception
	{
		extractId  = new ArrayList<String>();
		getXMLFilePathOfRegressionCyle1(getInputSheetOfMoRegression(regression), testCaseName, workFlow, keyName);
		extractId = setOutputFileData(regression, getOuputSheetOfMoRegressionMsg06(), testCaseName, workFlow, keyColumnData, keyColumnDataAsXmlFileName, "//ExtractId", "ExtractId");
		if(workFlow.equals(workFlow01FM01))
		{

		}
		moGenericMethodsLog.info("ExtractId of " + workFlow + " file : " + extractId);
	}

	/**
	 * This method is to get created ScheduleId for given workflow
	 * @param workFlowName
	 * @return
	 * @throws Exception
	 */
	public static String getCreatedScheduleId(String workFlowName) throws Exception
	{
		String jobExecutionLogQuery = "Select ScheduleID , ActivityId, PayLoad from " + getMoSchedulerTable() + ".[Scheduler].[JobExecutionLog] where WorkFlowname = '" + workFlowName + "'";		
		String payLoadValue = "";
		scheduleId = "";
		int checkCountFlag = 0;
		boolean payLoadFlag = false;
		do{
			checkCountFlag++;
			ResultSet jobExecutionLogResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), jobExecutionLogQuery);
			while(jobExecutionLogResultSet.next())
			{
				activityId = jobExecutionLogResultSet.getString("ActivityId");
				payLoadValue = jobExecutionLogResultSet.getString(columnPayLoad);
				if((payLoadValue.indexOf(getMsg06SM01FileName()) > 0) || (payLoadValue.indexOf(expectedDebitItemId.get(0)) > 0)  ||(payLoadValue.indexOf(expectedCreditItemId.get(0)) > 0) ||
						(payLoadValue.indexOf(expectedDebitItemId.get(1)) > 1)   ||(payLoadValue.indexOf(expectedDebitItemId.get(2)) > 1)||(payLoadValue.indexOf(getMsg06KM01FileName()) > 0)|| (payLoadValue.indexOf(rowId) > 0) ||
						(payLoadValue.indexOf(postingSourceID) > 0)||  (payLoadValue.indexOf(getMsg12SM01FileName()) > 0) || ((payLoadValue.indexOf(getMsg11SM01FileName()) > 0)) ||
						(payLoadValue.indexOf(getMsg08SM01FileName()) > 0) || (payLoadValue.indexOf(sourceIdMsg07MS01) > 0) || (payLoadValue.indexOf(getMsg05SM01FileName()) > 0) ||
						(payLoadValue.indexOf(getMsg04SM01FileName()) > 0) || (payLoadValue.indexOf(getMsg05KM01FileName()) > 0) || (payLoadValue.indexOf(getMsg13SM01FileName()) > 0))
				{
					payLoadFlag = true;
					scheduleId = jobExecutionLogResultSet.getString(columnScheduleID);
				}else if((payLoadValue.indexOf(getMsg08SM01FileName()) > 0))
				{
					payLoadFlag = true;
					scheduleId = jobExecutionLogResultSet.getString(columnScheduleID);
				}else if((workFlowName.equals(workFlowPTMA01)) && (itemSKIDs.size() > 0))
				{
					boolean flag = false;
					for(String eachItemSKID : itemSKIDs)
					{
						if((payLoadValue.indexOf(eachItemSKID) > 0))
						{
							flag = true;
							continue;
						}
					}
					if(flag)
					{
						payLoadFlag = true;
						scheduleId = jobExecutionLogResultSet.getString(columnScheduleID);
					}
				}else if((payLoadValue.indexOf(expectedCreditItemId.get(0)) > 0)|| (payLoadValue.indexOf(expectedCreditItemId.get(1)) > 0)|| (payLoadValue.indexOf(expectedCreditItemId.get(2)) > 0))
				{
					payLoadFlag = true;
					scheduleId = jobExecutionLogResultSet.getString(columnScheduleID);
				}
			}
			if((scheduleId.equals("")) && (checkCountFlag < 50))
			{
				Thread.sleep(5000);
				moGenericMethodsLog.info("Waiting for the PayLoad to create for workflow "+ workFlowName + " in JobExecutionLog table");

			}else if(!(scheduleId.equals(""))){
				moGenericMethodsLog.info("PayLoad created for workFlow "+ workFlowName + " with ScheduleId "+ scheduleId+ " in JobExecutionLog table");
				validationStepInformation("PayLoad created for workFlow "+ workFlowName + " with ScheduleId "+ scheduleId+ " in JobExecutionLog table");
				break;
			}else {	
				moGenericMethodsLog.error("PayLoad not created for workFlow "+ workFlowName+ " in JobExecutionLog table");
				validationErrorInformation("PayLoad not created for workFlow "+ workFlowName+ " in JobExecutionLog table");
				Component.assertTrue(false, "PayLoad not created for workFlow "+ workFlowName+ " in JobExecutionLog table");
				break;
			}
		}while(!(payLoadFlag));
		return scheduleId;
	}

	public static String scheduleIdWithFileName(String workFlowName, String fileName) throws Exception
	{
		String query = "Select ScheduleID , ActivityId, PayLoad from " + getMoSchedulerTable() + ".[Scheduler].[JobExecutionLog] where WorkFlowname = '" + workFlowName + "'";		
		String payLoadValue = new String();
		scheduleId = "";
		int checkCountFlag = 0;
		boolean payLoadFlag = false;
		do
		{
			checkCountFlag++;
			ResultSet jobExecutionResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(jobExecutionResultSet.next())
			{
				activityId = jobExecutionResultSet.getString("ActivityId");
				payLoadValue = jobExecutionResultSet.getString(columnPayLoad);
				if(payLoadValue.indexOf(fileName) > 0)
				{
					payLoadFlag = true;
					scheduleId = jobExecutionResultSet.getString(columnScheduleID);
				}						
			}		
			if((scheduleId.equals("")) && (checkCountFlag < 50))
			{
				Thread.sleep(5000);
				moGenericMethodsLog.info("Waiting for Payload creation for workflow "+ workFlowName + " in JobExecutionLog table");

			}else if(!(scheduleId.equals(""))){
				moGenericMethodsLog.info("PayLoad created for workFlow "+ workFlowName + " with ScheduleId "+ scheduleId);
				validationStepInformation("PayLoad created for workFlow "+ workFlowName + " with ScheduleId "+ scheduleId);
				break;
			}else {	
				moGenericMethodsLog.error("PayLoad not created for workFlow "+ workFlowName);
				validationErrorInformation("PayLoad not created for workFlow "+ workFlowName);
				Component.assertTrue(false, "PayLoad not created for workFlow "+ workFlowName);
				break;
			}
		}while(!(payLoadFlag));
		return scheduleId;
	}

	public static String scheduleIdWithItemIdsList(String workFlowName, List<String> itemIds) throws Exception
	{
		String query = "Select ScheduleID , ActivityId, PayLoad from " + getMoSchedulerTable() + ".[Scheduler].[JobExecutionLog] where WorkFlowname = '" + workFlowName + "'";		
		String payLoadValue = new String();
		scheduleId = "";
		int checkCountFlag = 0;
		boolean payLoadFlag = false;
		do
		{
			checkCountFlag++;
			ResultSet jobExecutionResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(jobExecutionResultSet.next())
			{
				activityId = jobExecutionResultSet.getString("ActivityId");
				payLoadValue = jobExecutionResultSet.getString(columnPayLoad);
				InnerLoop:	for(String eachItemId : itemIds)
				{
					if(payLoadValue.indexOf(eachItemId) > 0)
					{
						payLoadFlag = true;
						scheduleId = jobExecutionResultSet.getString(columnScheduleID);
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
				moGenericMethodsLog.info("Waiting for Payload creation for workflow "+ workFlowName + " in JobExecutionLog table");

			}else if(!(scheduleId.equals(""))){
				moGenericMethodsLog.info("PayLoad created for workFlow "+ workFlowName + " with ScheduleId "+ scheduleId);
				validationStepInformation("PayLoad created for workFlow "+ workFlowName + " with ScheduleId "+ scheduleId);
				break;
			}else {	
				moGenericMethodsLog.error("PayLoad not created for workFlow "+ workFlowName);
				validationErrorInformation("PayLoad not created for workFlow "+ workFlowName);
				Component.assertTrue(false, "PayLoad not created for workFlow "+ workFlowName);
				break;
			}
		}while(!(payLoadFlag));
		return scheduleId;
	}

	public static String scheduleIdWithRowIndex(String workFlowName) throws Exception
	{
		String query = "Select ScheduleID , ActivityId, PayLoad from " + getMoSchedulerTable() + ".[Scheduler].[JobExecutionLog] where WorkFlowname = '" + workFlowName + "'";		
		String payLoadValue = new String();
		scheduleId = "";
		int checkCountFlag = 0;
		boolean payLoadFlag = false;
		do
		{
			checkCountFlag++;
			ResultSet jobExecutionResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(jobExecutionResultSet.next())
			{
				activityId = jobExecutionResultSet.getString("ActivityId");
				payLoadValue = jobExecutionResultSet.getString(columnPayLoad);
				if(payLoadValue.indexOf(rowId) > 0)
				{
					payLoadFlag = true;
					scheduleId = jobExecutionResultSet.getString(columnScheduleID);
				}						
			}		
			if((scheduleId.equals("")) && (checkCountFlag < 50))
			{
				Thread.sleep(5000);
				moGenericMethodsLog.info("Waiting for Payload creation for workflow "+ workFlowName + " in JobExecutionLog table");

			}else if(!(scheduleId.equals(""))){
				moGenericMethodsLog.info("PayLoad created for workFlow "+ workFlowName + " with ScheduleId "+ scheduleId);
				validationStepInformation("PayLoad created for workFlow "+ workFlowName + " with ScheduleId "+ scheduleId);
				break;
			}else {	
				moGenericMethodsLog.error("PayLoad not created for workFlow "+ workFlowName);
				validationErrorInformation("PayLoad not created for workFlow "+ workFlowName);
				Component.assertTrue(false, "PayLoad not created for workFlow "+ workFlowName);
				break;
			}
		}while(!(payLoadFlag));
		return scheduleId;
	}

	/**
	 * This method is to get EAV location for given workflow
	 * @param workFlowName
	 * @return
	 * @throws Exception
	 */
	public static String getEAVLocation(String workFlowName) throws Exception
	{
		String query = "select value from " + getMoConfigTable() + ".[Config].[MOInstanceConfigEAV] "
				+ "where Entity  = 'FileLocation' and Attribute = '" + workFlowName + "'" ;  
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	public static void test3() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		String query = "Update " + getMoRepositoryTable() +".[Base].[ItemState] SET PayerItemDirtyFlag = 30 Where PayerItemDirtyFlag IN(27,25)";
		getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
	}

	/**
	 * This method is to check create scheduleId status
	 * @param workFlowName
	 * @param scheduleID
	 * @param flagWorkFlow
	 * @param itemIds
	 * @return
	 * @throws Exception
	 */
	public static String checkScheduleIdStatus(String workFlowName, String scheduleID, String flagWorkFlow, List<String> itemIds) throws Exception
	{
		boolean dirtyAndBatchedFlag = false;
		String intradayScheduleQuery = "SELECT Status, JobTemplateId from " + getMoSchedulerTable() + ".[Scheduler].[IntradaySchedule] "
				+ "Where WorkFlowname = '" + workFlowName + "' "
				+ "and ScheduleID = '" + scheduleID + "' order by 1 desc"  ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), intradayScheduleQuery); 
		String status = "";
		while(resultSet.next())
		{
			status = resultSet.getString("Status");
			jobTemplateId = resultSet.getString("JobTemplateId");
			While1:	while(!(status.equals("170")))
			{
				if(status.equals("35"))
				{
					moGenericMethodsLog.info("Schedule-Id of IntradaySchedule " + scheduleID + " status is in 35 : PayLoad Created");
					validationStepInformation("Schedule-Id of IntradaySchedule " + scheduleID + " status is in 35 : PayLoad Created");
					String jobExecutionLogQuery = "SELECT Status, ActivityId from " + getMoSchedulerTable() + ".[Scheduler].[JobExecutionLog] "
							+ "Where WorkFlowname = '" + workFlowName + "' "
							+ "and ScheduleID = '" + scheduleID + "' order by 1 desc"  ;
					ResultSet jobExecutionLogResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), jobExecutionLogQuery); 
					while(jobExecutionLogResultSet.next())
					{
						String jobExecutionLogStatus = jobExecutionLogResultSet.getString("Status")	;
						if(!(jobExecutionLogStatus.equals("")))
						{
							if(jobExecutionLogStatus.equals("60"))
							{							
								moGenericMethodsLog.info("Schedule-Id of JobExecutionLog " + scheduleID + " status in 60 : Created by Job Executor");
								validationStepInformation("Schedule-Id of JobExecutionLog " + scheduleID + " status in 60 : Created by Job Executor");
								String scheduleWorkQueueQuery = "SELECT State FROM " + getMoSchedulerTable() + ".[Scheduler].[ScheduledWorkQueue] "
										+"where ActivityId = '" + jobExecutionLogResultSet.getString("ActivityId") + "'";
								ResultSet scheduleWorkQueueResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), scheduleWorkQueueQuery); 
								while(scheduleWorkQueueResultSet.next())
								{
									String state = scheduleWorkQueueResultSet.getString("State");
									if(state.equals("70"))
									{
										moGenericMethodsLog.info("ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") + " is in 70 : Created by Task Builder");
										validationStepInformation("ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") + " is in 70 : Created by Task Builder");
									}
								}
							}else if(jobExecutionLogStatus.equals("80")) {								
								moGenericMethodsLog.info("Schedule-Id of JobExecutionLog " + scheduleID + " status in 80 : Processed by Agent");
								validationStepInformation("Schedule-Id of JobExecutionLog " + scheduleID + " status in 80 : Processed by Agent");
								String scheduleWorkQueueQuery = "SELECT State , ActivityId FROM " + getMoSchedulerTable() + ".[Scheduler].[ScheduledWorkQueue] "
										+"where ActivityId = '" + jobExecutionLogResultSet.getString("ActivityId") + "'";
								ResultSet scheduleWorkQueueResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), scheduleWorkQueueQuery); 
								while(scheduleWorkQueueResultSet.next())
								{
									String state = scheduleWorkQueueResultSet.getString("State");
									if(state.equals("90"))
									{
										moGenericMethodsLog.info("ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") + " is 90 : Processed by Agent");
										validationStepInformation("ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") + " is 90 : Processed by Agent");
									}
								}
							}else if((jobExecutionLogStatus.equals("130"))) {	
								dirtyAndBatchedFlag = true;
								moGenericMethodsLog.info("Schedule-Id of JobExecutionLog " + scheduleID + " status in 130 : Success by Agent");
								validationStepInformation("Schedule-Id of JobExecutionLog " + scheduleID + " status in 130 : Success by Agent");
								String scheduleWorkQueueQuery = "SELECT State, ActivityId FROM " + getMoSchedulerTable() + ".[Scheduler].[ScheduledWorkQueue] "
										+"where ActivityId = '" + jobExecutionLogResultSet.getString("ActivityId") + "'";
								ResultSet scheduleWorkQueueResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), scheduleWorkQueueQuery); 
								while(scheduleWorkQueueResultSet.next())
								{
									String state = scheduleWorkQueueResultSet.getString("State");
									if(state.equals("150"))
									{
										moGenericMethodsLog.info("ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") + " is 150 : Success by Agent");
										publishResults(true, "150" , "150", "Check ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") );
									}
								}								
								publishResults(true, "130" , "130",	"Check Schedule-Id of JobExecutionLog for scheduleID "+ scheduleID );
								break While1;
							}else if(jobExecutionLogStatus.equals("140")){
								moGenericMethodsLog.error("Schedule-Id of JobExecutionLog " + scheduleID + " status in 140 : Failed by Agent");
								validationErrorInformation("Schedule-Id of JobExecutionLog " + scheduleID + " status in 140 : Success by Agent");
								activityId = jobExecutionLogResultSet.getString("ActivityId");								
								String exceptionLogQuery = "SELECT Source, StackTrace, Id, ActivityId, Message FROM  " + getMoRepositoryTable() + ".[Logging].[ExceptionLog] "
										+ "where ActivityId = '" + activityId + "' and StackTrace IS NOT NULL";
								ResultSet exceptionLogResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), exceptionLogQuery); 
								while(exceptionLogResultSet.next())
								{
									moGenericMethodsLog.error("Exception record created for ActivityId " + exceptionLogResultSet.getString("ActivityId") + " with  Id  "+ exceptionLogResultSet.getString("Id") + 
											"\n . And Error Message as : "+ exceptionLogResultSet.getString("Message"));
									validationErrorInformation("Exception record created for ActivityId " + exceptionLogResultSet.getString("ActivityId") + " with  Id  "+ exceptionLogResultSet.getString("Id") + 
											"\n . And Error Message as : "+ exceptionLogResultSet.getString("Message"));
								}								
								String scheduleWorkQueueQuery = "SELECT State, ActivityId FROM " + getMoSchedulerTable() + ".[Scheduler].[ScheduledWorkQueue] "
										+"where ActivityId = '" + jobExecutionLogResultSet.getString("ActivityId") + "'";
								ResultSet scheduleWorkQueueResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), scheduleWorkQueueQuery); 
								while(scheduleWorkQueueResultSet.next())
								{
									String state = scheduleWorkQueueResultSet.getString("State");
									if(state.equals("160"))
									{
										moGenericMethodsLog.error("ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") + " is 160 : Failure by Agent");
										publishResults(true, "160" , "160", 	"Check ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") );
									}
								}							
								Component.assertTrue(false, "Schedule-Id of JobExecutionLog " + scheduleID + " status in 140 : Failed by Agent");
							}else if(jobExecutionLogStatus.equals("141")){
								moGenericMethodsLog.error("Schedule-Id of JobExecutionLog " + scheduleID + " status in 141 : Failure by Agent with HttpStatusCode:401. Unauthorized error issue . No Role for workflow " 
										+ workFlowName + "or Role User record available in Role/RoleUsers table");
								validationErrorInformation("Schedule-Id of JobExecutionLog " + scheduleID + " status in 141 : Failure by Agent with HttpStatusCode:401. Unauthorized error issue . No Role for workflow " 
										+ workFlowName + "or Role User record available in Role/RoleUsers table");
								activityId = jobExecutionLogResultSet.getString("ActivityId");								
								String scheduleWorkQueueQuery = "SELECT State, ActivityId FROM " + getMoSchedulerTable() + ".[Scheduler].[ScheduledWorkQueue] "
										+"where ActivityId = '" + jobExecutionLogResultSet.getString("ActivityId") + "'";
								ResultSet scheduleWorkQueueResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), scheduleWorkQueueQuery); 
								while(scheduleWorkQueueResultSet.next())
								{
									String state = scheduleWorkQueueResultSet.getString("State");
									if(state.equals("160"))
									{
										moGenericMethodsLog.error("ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") + " is 160 : Failure by Agent");
										publishResults(true, "160" , "160", 	"Check ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") );
									}
								}	
								String exceptionLogQuery = "SELECT Source, StackTrace, Id, ActivityId, Message FROM  " + getMoRepositoryTable() + ".[Logging].[ExceptionLog] "
										+ "where ActivityId = '" + activityId + "' and StackTrace IS NOT NULL";
								ResultSet exceptionLogResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), exceptionLogQuery); 
								while(exceptionLogResultSet.next())
								{
									moGenericMethodsLog.error("Exception record created for ActivityId " + exceptionLogResultSet.getString("ActivityId") + " with  Id  "+ exceptionLogResultSet.getString("Id") + 
											"\n . And Error Message as : "+ exceptionLogResultSet.getString("Message"));
									validationErrorInformation("Exception record created for ActivityId " + exceptionLogResultSet.getString("ActivityId") + " with  Id  "+ exceptionLogResultSet.getString("Id") + 
											"\n . And Error Message as : "+ exceptionLogResultSet.getString("Message"));
								}	
								Component.assertTrue(false, "Schedule-Id of JobExecutionLog " + scheduleID + " status in 141 : Failure by Agent with HttpStatusCode:401. Unauthorized error issue . No Role/Role User available");
							}else if(jobExecutionLogStatus.equals("142")){
								moGenericMethodsLog.error("Schedule-Id of JobExecutionLog " + scheduleID + " status in 142 : Failure by Agent with HttpStatusCode:417. Inbound validation error in file " + workFlowName);
								validationErrorInformation("Schedule-Id of JobExecutionLog " + scheduleID + " status in 142 : Failure by Agent with HttpStatusCode:417. Inbound validation error in file " + workFlowName);
								String scheduleWorkQueueQuery = "SELECT State, ActivityId FROM " + getMoSchedulerTable() + ".[Scheduler].[ScheduledWorkQueue] "
										+"where ActivityId = '" + activityId+ "'";
								ResultSet scheduleWorkQueueResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), scheduleWorkQueueQuery); 
								while(scheduleWorkQueueResultSet.next())
								{
									String state = scheduleWorkQueueResultSet.getString("State");
									if(state.equals("160"))
									{
										moGenericMethodsLog.error("ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") + " is 160 : Failure by Agent");
										publishResults(true, "160" , "160", 	"Check ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") );
									}
								}	
								String exceptionLogQuery = "SELECT Source, StackTrace, Id, ActivityId, Message FROM  " + getMoRepositoryTable() + ".[Logging].[ExceptionLog] "
										+ "where ActivityId = '" + activityId + "' and StackTrace IS NOT NULL";
								ResultSet exceptionLogResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), exceptionLogQuery); 
								while(exceptionLogResultSet.next())
								{
									moGenericMethodsLog.error("Exception record created for ActivityId " + exceptionLogResultSet.getString("ActivityId") + " with  Id  "+ exceptionLogResultSet.getString("Id") + 
											"\n . And Error Message as : "+ exceptionLogResultSet.getString("Message"));
									validationErrorInformation("Exception record created for ActivityId " + exceptionLogResultSet.getString("ActivityId") + " with  Id  "+ exceptionLogResultSet.getString("Id") + 
											"\n . And Error Message as : "+ exceptionLogResultSet.getString("Message"));
								}								
							}else if(jobExecutionLogStatus.equals("143")){
								moGenericMethodsLog.error("Schedule-Id of JobExecutionLog " + scheduleID + " status in 143 : Failure by Agent with HttpStatusCode:413. File Size of workFlow "+ workFlowName+  " more than threshold size in EAV table");
								validationErrorInformation("Schedule-Id of JobExecutionLog " + scheduleID + " status in 143 : Failure by Agent with HttpStatusCode:413. File Size of workFlow "+ workFlowName+  " more than threshold size in EAV table");
								String scheduleWorkQueueQuery = "SELECT State, ActivityId FROM " + getMoSchedulerTable() + ".[Scheduler].[ScheduledWorkQueue] "
										+"where ActivityId = '" + activityId + "'";
								ResultSet scheduleWorkQueueResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), scheduleWorkQueueQuery); 
								while(scheduleWorkQueueResultSet.next())
								{
									String state = scheduleWorkQueueResultSet.getString("State");
									if(state.equals("160"))
									{
										moGenericMethodsLog.error("ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") + " is 160 : Failure by Agent");
										publishResults(true, "160" , "160", "Check ScheduleWorkQueue State for activityId "+ jobExecutionLogResultSet.getString("ActivityId") );
									}
								}	
								String exceptionLogQuery = "SELECT Source, StackTrace, Id, ActivityId, Message FROM  " + getMoRepositoryTable() + ".[Logging].[ExceptionLog] "
										+ "where ActivityId = '" + activityId + "' and StackTrace IS NOT NULL";
								ResultSet exceptionLogResultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), exceptionLogQuery); 
								while(exceptionLogResultSet.next())
								{
									moGenericMethodsLog.error("Exception record created for ActivityId " + exceptionLogResultSet.getString("ActivityId") + " with  Id  "+ exceptionLogResultSet.getString("Id") + 
											"\n . And Error Message as : "+ exceptionLogResultSet.getString("Message"));
									validationErrorInformation("Exception record created for ActivityId " + exceptionLogResultSet.getString("ActivityId") + " with  Id  "+ exceptionLogResultSet.getString("Id") + 
											"\n . And Error Message as : "+ exceptionLogResultSet.getString("Message"));
								}
								Component.assertTrue(false, "Schedule-Id of JobExecutionLog " + scheduleID + " status in 143 : Failure by Agent with HttpStatusCode:413. File Size of workFlow "+ workFlowName+  " more than threshold size in EAV table");
							}						
						}
						Thread.sleep(8000);
					}
				}
				resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), intradayScheduleQuery); 
				status = getICSRetrieveColumnValue(resultSet);
			}
		}		
		intradayScheduleQuery = "SELECT Status, JobTemplateId from " + getMoSchedulerTable() + ".[Scheduler].[IntradaySchedule] "
				+ "Where WorkFlowname = '" + workFlowName + "' and ScheduleID = '" + scheduleID + "' order by 1 desc"  ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), intradayScheduleQuery); 
		while(resultSet.next())
		{
			status = resultSet.getString("Status");
			if(status.equals("170"))
			{
				moGenericMethodsLog.info("Schedule-Id of IntradaySchedule " + scheduleID + " status in 170 : Completed By Supervisor");
				publishResults(true, "170" , "170", "Check Schedule-Id of IntradaySchedule for scheduleID "+  scheduleID);
			}else {
				moGenericMethodsLog.info("Schedule-Id of IntradaySchedule " + scheduleID + " status still in " + status);
				Thread.sleep(8000);
				intradayScheduleQuery = "SELECT Status, JobTemplateId from " + getMoSchedulerTable() + ".[Scheduler].[IntradaySchedule] "
						+ "Where WorkFlowname = '" + workFlowName + "' and ScheduleID = '" + scheduleID + "' order by 1 desc"  ;
				resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), intradayScheduleQuery); 				
			}			
		}
		if(dirtyAndBatchedFlag)
		{
			getExpectedFlagsCondition(flagWorkFlow);
			if(!(flagWorkFlow.equals("")))
			{
				getExpectedFlagsCondition(flagWorkFlow);
				for(String eachItemId : itemIds)
				{
					Thread.sleep(5000);
					getActualFlagsConditionOfItemState(eachItemId);
					if(((flagWorkFlow.indexOf("5") > 0)) || ((flagWorkFlow.indexOf("13") > 0)))
					{
						if(!(expectedDirtyFlag==null))
						{
							if(expectedDirtyFlag.equals(actualBeneficiaryDirtyFlag))
							{
								publishResults(true, actualBeneficiaryDirtyFlag, expectedDirtyFlag, "Check DirtyFlag Condition flag for Beneficiary workFlow " + flagWorkFlow + " to triger");
								moGenericMethodsLog.info("ActualBeneficiaryDirtyFlag condition is "+ actualBeneficiaryDirtyFlag + " is same as expected " + expectedDirtyFlag + " for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}
							else {
								//								publishResults(false, actualBeneficiaryDirtyFlag, expectedDirtyFlag, "Check DirtyFlag Condition flag for Beneficiary workFlow " + flagWorkFlow + " to triger");
								//								moGenericMethodsLog.error("ActualBeneficiaryDirtyFlag condition is "+ actualBeneficiaryDirtyFlag + " is not same as expected " + expectedDirtyFlag + "  for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}	
						}											
						if(!(expectedBatchedFlag==null))
						{
							if(expectedBatchedFlag.equals(actualBeneficiaryBatchedFlag))
							{
								publishResults(true, actualBeneficiaryBatchedFlag, expectedBatchedFlag, "Check BatchedFlag Condition flag for Beneficiary workFlow " + flagWorkFlow + " to triger");
								moGenericMethodsLog.info("ActualBeneficiaryBatchedFlag condition is "+ actualBeneficiaryBatchedFlag + " is same as expected " + expectedBatchedFlag + " for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}
							else {
								//								publishResults(false, actualBeneficiaryBatchedFlag, expectedBatchedFlag, "Check BatchedFlag Condition flag for Beneficiary workFlow " + flagWorkFlow + " to triger");
								//								moGenericMethodsLog.error("ActualBeneficiaryBatchedFlag condition is "+ actualBeneficiaryBatchedFlag + " is not same as expected " + expectedBatchedFlag + " for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}
						}											
					}else if(flagWorkFlow.indexOf("P") > 0)
					{
						if(!(expectedDirtyFlag==null))
						{
							if(expectedDirtyFlag.equals(actualPostingDirtyFlag))
							{
								publishResults(true, actualPostingDirtyFlag, expectedDirtyFlag, "Check Posting DirtyFlag Condition flag for itemId " + eachItemId + " with Posting workFlow " + flagWorkFlow + " to triger");
								moGenericMethodsLog.info("ActualPostingDirtyFlag condition is "+ actualPostingDirtyFlag + " is same as expected for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}
							else {
								//								publishResults(false, actualPostingDirtyFlag, expectedDirtyFlag, "Check Posting DirtyFlag Condition flag for itemId " + eachItemId + " with Posting workFlow " + flagWorkFlow + " to triger");
								//								moGenericMethodsLog.error("ActualPostingDirtyFlag condition is "+ actualPostingDirtyFlag + " is not same as expected " + expectedDirtyFlag + " for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}	
						}											
						if(!(expectedBatchedFlag==null))
						{
							if(expectedBatchedFlag.equals(actualPostingBatchedFlag))
							{
								publishResults(true, actualPostingBatchedFlag, expectedBatchedFlag, "Check Posting BatchedFlag Condition flag for itemId " + eachItemId + " with Posting workFlow " + flagWorkFlow + " to triger");
								moGenericMethodsLog.info("ActualPostingBatchedFlag condition is "+ actualPostingBatchedFlag + " is same as expected for itemId " + eachItemId + " with  workFlow "+ flagWorkFlow + " to trigger");
							}
							else {
								//								publishResults(false, actualPostingBatchedFlag, expectedBatchedFlag, "Check Posting BatchedFlag Condition flag for itemId " + eachItemId + " with Posting workFlow " + flagWorkFlow + " to triger");
								//								moGenericMethodsLog.error("ActualPostingBatchedFlag condition is "+ actualPostingBatchedFlag + " is not same as expected " + expectedBatchedFlag + " for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}
						}	
					}else {
						if(!(expectedDirtyFlag==null))
						{
							if(actualPayerDirtyFlag.equals("20") || actualPayerDirtyFlag.equals("27"))
							{
								actualPayerDirtyFlag = "10";
							}
							if(expectedDirtyFlag.equals(actualPayerDirtyFlag))
							{
								publishResults(true, actualPayerDirtyFlag, expectedDirtyFlag, "Check DirtyFlag Condition flag for Payer workFlow " + flagWorkFlow + " to triger");
								moGenericMethodsLog.info("ActualPayerDirtyFlag condition is "+ actualPayerDirtyFlag + " is same as expected for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}
							else {
								//								publishResults(false, actualPayerDirtyFlag, expectedDirtyFlag, "Check DirtyFlag Condition flag for Payer workFlow " + flagWorkFlow + " to triger");
								//								moGenericMethodsLog.error("ActualPayerDirtyFlag condition is "+ actualPayerDirtyFlag + " is not same as expected " + expectedDirtyFlag + "  for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}
						}

						if(!(expectedBatchedFlag==null))
						{
							if(actualPayerBatchedFlag.equals("20") || actualPayerBatchedFlag.equals("27"))
							{
								actualPayerBatchedFlag = "10";
							}
							if(expectedBatchedFlag.equals(actualPayerBatchedFlag))
							{
								publishResults(true, actualPayerBatchedFlag, expectedBatchedFlag, "Check BatchedFlag Condition flag for Payer workFlow " + flagWorkFlow + " to triger");
								moGenericMethodsLog.info("ActualPayerBatchedFlag condition is "+ actualPayerBatchedFlag + " is same as expected for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}
							else {
								//								publishResults(false, actualPayerBatchedFlag, expectedBatchedFlag, "Check BatchedFlag Condition flag for Payer workFlow " + flagWorkFlow + " to triger");
								//								moGenericMethodsLog.error("ActualPayerBatchedFlag condition is "+ actualPayerBatchedFlag + " is not same as expected " + expectedBatchedFlag + " for itemId " + eachItemId + " with workFlow "+ flagWorkFlow + " to trigger");
							}
						}										
					}
				}
			}
		}			
		return status;
	}	

	/**
	 * This method is to get expected flag conditions from ItemState table
	 * @param workFlow
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	public static void getExpectedFlagsCondition(String workFlow) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		String query = "SELECT DirtyFlagCondition , BatchedFlagCondition FROM " + getMoConfigTable() + ".[Config].[MessageParameters] "
				+ "where InternalMessageType = '" + workFlow + "'";
		ResultSet  result = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		while(result.next())
		{
			expectedDirtyFlag = result.getString("DirtyFlagCondition");
			expectedBatchedFlag = result.getString("BatchedFlagCondition");
		}
	}

	/**
	 * This method is to get actual flag conditions from ItemState table 
	 * @param itemId
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	public static void getActualFlagsConditionOfItemState(String itemId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		String query = "SELECT PayerItemDirtyFlag , PayerItemBatchedFlag, BeneficiaryItemBatchedFlag , BeneficiaryItemDirtyFlag FROM " + getMoRepositoryTable() + ".[Base].[ItemState] "
				+ "where ItemID = '" + itemId  + "'";
		ResultSet  result = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		while(result.next())
		{
			actualPayerDirtyFlag = result.getString("PayerItemDirtyFlag");
			actualPayerBatchedFlag = result.getString("PayerItemBatchedFlag");
			actualBeneficiaryDirtyFlag = result.getString("BeneficiaryItemDirtyFlag");
			actualBeneficiaryBatchedFlag = result.getString("BeneficiaryItemBatchedFlag");
		}
	}

	/**
	 * This method is to get actual flag conditions from PostingEntry table
	 * @param itemId
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 */
	public static void getActualFlagsConditionOfPostingEntryState(String itemId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		String query = "SELECT DirtyFlag , BatchedFlag FROM " + getMoRepositoryTable() + ".[Base].[PostingEntryState] "
				+ "where ItemID = '" + itemId  + "'";
		ResultSet  result = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		while(result.next())
		{
			actualPostingDirtyFlag = result.getString("DirtyFlag");
			actualPostingBatchedFlag = result.getString("BatchedFlag");
		}
	}	

	/**
	 * This method is to check required TxSetIds of switch file in TransactionSet table
	 * @param workFlow
	 * @throws Exception
	 */
	public static void dbCheckTxSetIdInTransactionSetTable(String workFlow) throws Exception
	{
		moGenericMethodsLog.info("Validating TxSetIds insert in TransactionSet Table");
		for(String eachTransactionId : expectedTsetId)
		{			
			checkTxSetIdInsertinTransactionTable(eachTransactionId , 
					"Check the TxSetId column in the Item table for TransactionSetId  = " + eachTransactionId , workFlow);
		}
	}	

	/**
	 * This method is to check each TxsetId and also duplicate TxsetId check in TransactionSet table
	 * @param transactionSetId
	 * @param validationHeader
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */
	private static boolean checkTxSetIdInsertinTransactionTable(String transactionSetId, String validationHeader, String workFlow) throws Exception
	{
		String query = "Select TsetID from " + getMoRepositoryTable() + ".[Base].[TransactionSet] "
				+ "Where TsetID like '%" + transactionSetId + "%' and InternalMessageType = '"+ workFlow + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		if(resultSet.next())
		{
			tempResult = Component.verifyTrue(true, "TsetId not inserted in Transaction table");
			publishResults(tempResult, "TsetId with " +  transactionSetId + " inserted in Transaction table for workFlow "+ workFlow, "Should see TsetId with " + transactionSetId + " insert in Transaction table" , validationHeader);
			moGenericMethodsLog.info("TsetId with " +  transactionSetId + " inserted in Transaction table for workFlow "+ workFlow);	
		}else
		{
			tempResult = Component.verifyTrue(false, "TsetId not inserted in Transaction table");
			publishResults(tempResult, "TsetId with " +  transactionSetId + " not inserted in Transaction table", "Should see TsetId with " + transactionSetId + " insert in Transaction table" , validationHeader);
		}
		int duplicateCount = 0 ; 
		while(resultSet.next())
		{
			duplicateCount++;
			if(duplicateCount > 2)
			{
				validationErrorInformation("Duplicate TSetId loaded in Transaction table and TsetId is "+ transactionSetId);
				moGenericMethodsLog.error("Duplicate TSetId loaded in Transaction table and TsetId is "+ transactionSetId);
				Component.assertTrue(false, "Duplicate TSetId loaded in Transaction table and TsetId is "+ transactionSetId);
			}
		}
		return tempResult;
	}

	/**
	 * This method is to check required required TxSetIds of switch file in TransactionSetState table
	 * @throws Exception
	 */
	public static void dbCheckTxSetIdFromTransactionSetStateTable() throws Exception
	{
		moGenericMethodsLog.info("Validating TxSetIds insert in TransactionSetState Table");
		for(String eachTransactionId : expectedTsetId)
		{
			checkTxSetIdInsertinTransactionSetStateTable(eachTransactionId , 
					"Check the TxSetId column in the Transaction table for TransactionSetId = " + eachTransactionId);
		}
	}

	/**
	 * This method is to check each TxsetId insert in TransactionSetState table
	 * @param transactionSetId
	 * @param validationHeader
	 * @param workFlow
	 * @return
	 * @throws Exception
	 */
	private static boolean checkTxSetIdInsertinTransactionSetStateTable(String transactionSetId, String validationHeader) throws Exception
	{
		String query = "Select TsetID from " + getMoRepositoryTable() + ".[Base].[TransactionSetState] "
				+ "Where TsetID like '%" + transactionSetId + "%'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		if(resultSet.next())
		{
			tempResult = Component.verifyTrue(true, "TsetId not inserted in TransactionSetState table");
			publishResults(tempResult, "TsetId with " +  transactionSetId + " inserted in TransactionSetState table", "Should see TsetId with " + transactionSetId + " insert in TransactionSetState table" , validationHeader);
			moGenericMethodsLog.info("TsetId with " +  transactionSetId + " inserted in TransactionSetState table");
		}else
		{
			tempResult = Component.verifyTrue(false, "TsetId not inserted in TransactionSetState table");
			publishResults(tempResult, "TsetId with " +  transactionSetId + " not inserted in TransactionSetState table", "Should see TsetId with " + transactionSetId + " insert in TransactionSetState table" , validationHeader);
			moGenericMethodsLog.error("TsetId with " +  transactionSetId + " not inserted in TransactionSetState table");
		}
		return tempResult;
	}

	/**
	 * This method is to check SourceId record insert in Source Table for given workflow
	 * @param sourceId
	 * @param workFlow
	 * @throws Exception
	 */
	public static void dbCheckSourceIdFromSourceTable(String sourceId,  String workFlow) throws Exception
	{
		moGenericMethodsLog.info("Validating SourceId record insert for " + workFlow + " in Source Table");
		boolean flag = false;
		if(workFlow.equals(workFlow05SM01))
		{
			flag = checkSourceIdInsertInSourceTable("MSG05."+sourceId, "Check the SourceID column in the Source table");
		}else if(workFlow.equals(workFlow06SM01))
		{
			flag = checkSourceIdInsertInSourceTable("MSG06."+sourceId, "Check the SourceID column in the Source table");
		}

		if(flag)
		{
			moGenericMethodsLog.info("SourceID " + "MSG05." +sourceId + " record  inserted in Source table");
		}else
		{
			moGenericMethodsLog.error("SourceID " + "MSG06." +sourceId + " record not inserted in Source table");
		}
	}

	/**
	 * This method is to check SourceId record Insert and display the validation results in report
	 * @param sourceId
	 * @param validationHeader
	 * @return
	 * @throws Exception
	 */
	private static boolean checkSourceIdInsertInSourceTable(String sourceId, String validationHeader) throws Exception
	{
		String query = "Select SourceID from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "Where SourceID = '" + sourceId + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		if(resultSet.next())
		{
			tempResult = Component.verifyTrue(true, "SourceId not inserted in Source table");
			publishResults(tempResult, "Msg-Id with " +  sourceId + " inserted in Source table", "Should see Msg-Id with " + sourceId + " insert in source table" , validationHeader);
		}else
		{
			tempResult = Component.verifyTrue(false, "SourceId not inserted in Source table");
			publishResults(tempResult, "Msg-Id with " +  sourceId + " not inserted in Source table", "Should see Msg-Id with " + sourceId + " insert in source table" , validationHeader);
		}
		return tempResult;
	}

	/**
	 * This method is to Get SourceID of ISO content from Source table with Debit Item for given Workflow 
	 * @param workFlow
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void checkSourceIDOfISOContentWithDebitItem(String workFlow) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "Select SourceID , ISOContent = CAST(ISOContent AS XML) from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "Where MessageType = '" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		tempISOContent = "";
		while(resultSet.next())
		{
			tempISOContent = resultSet.getString("ISOContent");
			if(tempISOContent.indexOf(expectedDebitItemId.get(0)) > 0)
			{
				tempISOSourceID = resultSet.getString(columnSourceID);
				publishResults(true, "WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempISOSourceID,
						"Should see workflow "+ workFlow + " to load in source table",
						"Check workflow " + workFlow + " record insert in source table");
				moGenericMethodsLog.info("WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempISOSourceID);
				break;
			}
			moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in TargetQueue and in source table ");
			publishResults(false, "WorkFlow "+ workFlow + " not loaded in TargetQueue and in source table ",
					"Should see workflow "+ workFlow + " to load in TargetQueue and in source table ",
					"Check workflow " + workFlow + " record insert in TargetQueue and in source table ");
			Component.assertTrue(false, "WorkFlow "+ workFlow + " not loaded in TargetQueue and in source table ");			
		}
	}

	/**
	 * This method is to Get SourceID of ICN content from Source table with Debit Item for given workflow 
	 * @param workFlow
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void checkSourceIDOfICNContentWithDebitItem(String workFlow) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "Select SourceID , ICNContent = CAST(ICNContent AS XML) from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "Where MessageType = '" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		tempICNContent = "";
		boolean tempFlag = false;
		while(resultSet.next())
		{
			tempICNContent = resultSet.getString("ICNContent");
			if(tempICNContent.indexOf(expectedDebitItemId.get(0)) > 0)
			{
				tempFlag = true;
				tempICNSourceID=resultSet.getString(columnSourceID);
				publishResults(true, "WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID,
						"Should see workflow "+ workFlow + " to load in source table",
						"Check workflow " + workFlow + " record insert in source table");
				moGenericMethodsLog.info("WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID);
			}
		}
		if(!tempFlag)
		{
			publishResults(tempFlag, "WorkFlow "+ workFlow + " not loaded in source table ",
					"Should see workflow "+ workFlow + " to load in source table",
					"Check workflow " + workFlow + " record insert in source table");
			moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in source table ");
			Component.assertTrue(tempFlag, "WorkFlow "+ workFlow + " not loaded in source table");
		}
	}

	/**
	 * This method is to Get SourceID of ICN content from Source table with Debit Item for given workflow and DebitItms 
	 * @param workFlow
	 * @param items
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void checkSourceIDOfICNContentRequiredDebit(String workFlow, List<String> items) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "Select SourceID , ICNContent = CAST(ICNContent AS XML) from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "Where MessageType = '" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		tempICNContent = "";
		boolean tempFlag = false;
		while(resultSet.next())
		{
			tempICNContent = resultSet.getString("ICNContent");
			if(tempICNContent.indexOf(items.get(0)) > 0)
			{
				tempFlag = true;
				tempICNSourceID=resultSet.getString(columnSourceID);
				publishResults(true, "WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID,
						"Should see workflow "+ workFlow + " to load in source table",
						"Check workflow " + workFlow + " record insert in source table");
				moGenericMethodsLog.info("WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID);
			}
		}
		if(!tempFlag)
		{
			publishResults(tempFlag, "WorkFlow "+ workFlow + " not loaded in source table ",
					"Should see workflow "+ workFlow + " to load in source table",
					"Check workflow " + workFlow + " record insert in source table");
			moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in source table ");
			Component.assertTrue(tempFlag, "WorkFlow "+ workFlow + " not loaded in source table"); 
		}
	}

	/**
	 * This method is to Get SourceID of ICN content of PTMR01 from Source table with given workflow and extractId 
	 * @param workFlow
	 * @param extractId
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public static void checkSourceIDOfICNContentOfPTRM01(String workFlow, String extractId) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "Select SourceID , ICNContent = CAST(ICNContent AS XML) from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "Where MessageType = '" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		tempICNContent = "";
		boolean tempFlag = false;
		while(resultSet.next())
		{
			tempICNContent = resultSet.getString("ICNContent");
			if(tempICNContent.indexOf(extractId) > 0)
			{
				tempFlag = true;
				tempICNSourceID=resultSet.getString(columnSourceID);
				publishResults(true, "WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID,
						"Should see workflow "+ workFlow + " to load in source table",
						"Check workflow " + workFlow + " record insert in source table");
				moGenericMethodsLog.info("WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID);
			}
		}
		if(!tempFlag)
		{
			publishResults(tempFlag, "WorkFlow "+ workFlow + " not loaded in source table ",
					"Should see workflow "+ workFlow + " to load in source table",
					"Check workflow " + workFlow + " record insert in source table");
			moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in source table ");
			/*Component.assertTrue(tempFlag, "WorkFlow "+ workFlow + " not loaded in source table");*/
		}
	}

	/**
	 * This method is to check State from ItemTabe for given workflow
	 * @param workFlow
	 * @param itemIds
	 * @throws Exception
	 */
	public static void dbCheckStateColumnFromItemTable(String workFlow , List<String> itemIds) throws Exception
	{
		for(String eachItemId : itemIds)
		{
			switch(workFlow)
			{
			case "06SM01" :
				validateStateColumn(eachItemId, workFlow, entityStateMapForDebitItem);
				break;
			case "06MA01" :
				validateStateColumn(eachItemId, workFlow, entityStateMapForDebitItem);
				break;
			case "06MF01" :
				validateStateColumn(eachItemId, workFlow, entityStateMapForDebitItem);	
				break;
			case "06FM01" :
				checkInitialStateFromFredInItemTable(eachItemId, workFlow06FM01, initialStateFromFred);
				break;
			case "06MA02" :
				validateStateColumn(eachItemId, workFlow, initialStateFromFred);	
				break;
			case "06MK01" :
				validateStateColumn(eachItemId, workFlow, initialStateFromFred);	
				break;
			case "06KM01" :
				initialiseVariables();
				checkDerivedEntityStateInItemTable(eachItemId, workFlow06KM01);
				break;
			case "06MA03" :
				validateStateColumn(eachItemId, workFlow, entityStateMapForDebitItem);
				break;
			case "06MD01" :
				validateStateColumn(eachItemId, workFlow, entityStateMapForDebitItem);	
				break;
			case "06MA04" :
				validateStateColumn(eachItemId, workFlow,  initialStateFromFred);
				break;
			case "07MS01" :	
				checkStateFromItemTableFor07MS01(itemIds, workFlow);
				checkPayerStateFromItemStateFor07MS01(itemIds, workFlow);
				break;
			case "07MA01" :
				checkStateFromItemTableFor07MS01(itemIds, workFlow);
				checkPayerStateFromItemStateFor07MS01(itemIds, workFlow);
				break;
			case "08MA01" :
				validateStateColumn(eachItemId, workFlow, entityStateMapForDebitItem);
				break;
			case "05SM01" :
				validateStateColumn(eachItemId, workFlow, entityStateMapForCreditItemForBeneficiary);
				break;
			case "05MA01" :
				validateStateColumn(eachItemId, workFlow, entityStateMapForCreditItemForBeneficiary);
				break;	
			case "05MF01" :
				validateStateColumn(eachItemId, workFlow, entityStateMapForCreditItemForBeneficiary);
				break;
			case "05FM01" :
				checkInitialStateFromFredInItemTable(eachItemId, workFlow, initialStateFromFred);
				break;
			case "05MA02" :
				validateStateColumn(eachItemId, workFlow, initialStateFromFred);	
				break;	
			case "05MK01" :
				validateStateColumn(eachItemId, workFlow, initialStateFromFred);	
				break;	
			case "05KM01" :
				initialiseVariables();
				checkDerivedEntityStateInItemTable(eachItemId, workFlow05KM01);	
				break;	
			case "05MA03" :
				validateStateColumn(eachItemId, workFlow, entityStateMapForDebitItem);	
				break;		
			case "PTMR01" :
				checkPostingStateForPTMR01(eachItemId, workFlow, "945");
				break;
			case "12SM01M" :
				validatePayerItemFor12SM01Matched(eachItemId,"12SM01", "640");
				break;
			case "12SM01" :
				validatePayerItemFor12SM01Matched(eachItemId,"12SM01", "641");
				break;	
			case "12MA01" :
				validatePayerItemFor12SM01Matched(eachItemId,"12MA01", "640");
				break;	
			case "12MA02" :
				validatePayerItemFor12SM01Matched(eachItemId,"12MA01", "641");
				break;	
			case "11SM01M" :
				validatePayerItemFor12SM01Matched(eachItemId,"11SM01", "630");
				break;
			case "11SM01" :
				validatePayerItemFor12SM01Matched(eachItemId,"11SM01", "631");
				break;	
			case "11MA01" :
				validatePayerItemFor12SM01Matched(eachItemId,"11MA01", "630");
				break;	
			case "11MA02" :
				validatePayerItemFor12SM01Matched(eachItemId,"11MA02", "631");
				break;				
			}
		}		
	}

	/*
	 * 23. Validate ItemState Column for BeneficiaryNotification
	 */
	public static void checkItemTableForBeneficiaryItems(String itemId, String expectedItemState, String workFlow) throws Exception
	{
		String query = "SELECT State FROM " + getMoRepositoryTable() + ".[Base].[Item] where ItemID = '" + itemId + "' and InternalMessageType = '" + workFlow + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			String actual = resultSet.getString("State");
			if((actual.trim()).equals(expectedItemState.trim()))
			{
				moGenericMethodsLog.info("Actual State column from Item Table as "+ actual + " Matches with Expected " + expectedItemState + " for itemId " + itemId  + " for workFlow "+ workFlow);
				publishResults(true, actual, expectedItemState , "Check State column from Item Table for itemId "+ itemId + " for workFlow "+ workFlow);
			}else {
				moGenericMethodsLog.error("Actual State column from Item Table as "+ actual + " not Matches with Expected " + expectedItemState + " for itemId " +  itemId + " for workFlow "+ workFlow);
				publishResults(false, actual, expectedItemState , "Check State column from Item Table for itemId "+ itemId + " for workFlow "+ workFlow);
			}
		}
	}

	/*
	 * 23. Update Window2Business Date of Required Items
	 */
	public static void updateWindow2BusinessDate(List<String> itemIds , String window2Date) throws Exception
	{
		for(String eachItemId : itemIds)
		{
			String query = "Update " + getMoRepositoryTable() + ".Base.ItemState Set Window2BusinessDate = '" + window2Date + "' where ItemID = '" + eachItemId + "'";
			getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		}		
	}

	/*
	 * 23. Update Window1Business Date of Required Items
	 */
	public static void updateWindow1BusinessDate(List<String> itemIds , String window1Date) throws Exception
	{
		for(String eachItemId : itemIds)
		{
			String query = "Update " + getMoRepositoryTable() + ".Base.ItemState Set Window1BusinessDate = '" + window1Date + "' where ItemID = '" + eachItemId + "'";
			getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		}		
	}	

	/*
	 * 23.1 Validate State Column for Given WorkFlow and ItemId
	 */
	private static void validateStateColumn(String itemId, String workFlow, HashMap<String, String> mapValues) throws Exception
	{
		String actualStateValue = getStateValueFromItemTable(itemId, workFlow);
		String expectedStateValue = mapValues.get(itemId);
		boolean tempFlag =expectedStateValue.equals(actualStateValue);
		checkStateFromItemTable(tempFlag, actualStateValue, expectedStateValue, itemId, workFlow);		
	}

	/*
	 * 23.2 Check Initial State From FRED 
	 */
	public static void checkInitialStateFromFredInItemTable(String itemId, String workFlow, HashMap<String, String> initialStateFromFred) throws Exception
	{		
		String expectedFredState = initialStateFromFred.get(itemId).trim();
		String actualFredState = getStateValueFromItemTable(itemId, workFlow).trim();		
		boolean tempFlag = expectedFredState.equals(actualFredState);
		checkStateFromItemTable(tempFlag, actualFredState, expectedFredState, itemId, workFlow);		
	}

	/*
	 * 23.3 Check Derived Entity State for Fraud Result From KAPPA
	 */
	public static void checkDerivedEntityStateInItemTable(String itemId, String workFlow) throws Exception
	{
		checkInitialStateFromFred(initialStateFromFred.get(itemId).trim());
		try
		{
			checkFraudResultFromKappa(fraudResultFromKappa.get(itemId).trim());


			if(fredState510Flag && fraudulentFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs520;
			}else if(fredState510Flag && notProcessedFlag)
			{
				expectedDerivedEntityState = derivedEntityStateAs520;
			}else if(fredState510Flag && suspectFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs520;
			}else if(fredState510Flag && okFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs520;
			}else if(fredState510Flag && fraudulentFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs520;	
			}else if(fredState510Flag && notProcessedFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs520;
			}else if(fredState510Flag && suspectFlag)
			{
				expectedDerivedEntityState = derivedEntityStateAs520;
			}else if(fredState510Flag && okFlag)
			{
				expectedDerivedEntityState = derivedEntityStateAs520;
			}else if(fredState515Flag && fraudulentFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs525;	
			}else if(fredState515Flag && notProcessedFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs525;
			}else if(fredState515Flag && suspectFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs525;
			}else if(fredState515Flag && okFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs525;
			}else if(fredState515Flag && fraudulentFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs525;
			}else if(fredState515Flag && notProcessedFlag )
			{
				expectedDerivedEntityState = derivedEntityStateAs525;
			}else if(fredState515Flag && suspectFlag)
			{
				expectedDerivedEntityState = derivedEntityStateAs525;
			}else if(fredState515Flag && okFlag)
			{
				expectedDerivedEntityState = derivedEntityStateAs525;
			}else if((fredState20Flag && okFlag) || (fredState20Flag && notProcessedFlag))
			{
				expectedDerivedEntityState = derivedEntityStateAs50;
			}else if((fredState20Flag && fraudulentFlag) || (fredState20Flag && suspectFlag))
			{
				expectedDerivedEntityState = derivedEntityStateAs51;
			}else if(fredState21Flag )
			{
				expectedDerivedEntityState = derivedEntityStateAs51;
			}
			entityStateMapForDebitItem.put(itemId, expectedDerivedEntityState);
			String actualDerivedEntityState = getStateValueFromItemTable(itemId, workFlow);
			boolean tempFlag = expectedDerivedEntityState.equals(actualDerivedEntityState);
			checkStateFromItemTable(tempFlag, actualDerivedEntityState, expectedDerivedEntityState, itemId, workFlow);
		}catch(Exception e)
		{

		}
	}

	/*
	 * 23.4 Check Flag for Initial State 
	 */
	private static void checkInitialStateFromFred(String initialStateFromFred)
	{
		if(initialStateFromFred.equals(fredIntialStateAs510))
		{
			fredState510Flag = true;
		}else if(initialStateFromFred.equals(fredInitialStateAs515))
		{
			fredState515Flag = true;
		}else if(initialStateFromFred.equals(fredIntialStateAs20))
		{
			fredState20Flag = true;
		}else if(initialStateFromFred.equals(fredInitialStateAs21))
		{
			fredState21Flag = true;
		}
	}

	/*
	 * 23.5 Check Fraud Result From KAPPA
	 */
	private static void checkFraudResultFromKappa(String fraudResult)
	{
		if(fraudResult.equals(kappaResultAsFraud))
		{
			fraudulentFlag = true;
		}else if(fraudResult.equals(kappaResultAsSuspect))
		{
			suspectFlag = true;
		}else if(fraudResult.equals(kappaResultAsOK))
		{
			okFlag = true;
		}else if(fraudResult.equals(kappaResultAsNotProcessed))
		{
			notProcessedFlag = true;
		}
	}

	/*
	 * 23.6 Get State value from Item Table
	 */
	private static String getStateValueFromItemTable(String itemId, String workFlowName) throws Exception
	{
		String query = "Select State from " + getMoRepositoryTable() + ".[Base].[Item] "
				+ "where ItemID = '" + itemId + "' and InternalMessageType = '" + workFlowName + "'";	
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 			
		return getICSRetrieveColumnValue(resultSet);
	}

	/*
	 * 23.7 Publish Results for State Check From Item Table
	 */
	private static void checkStateFromItemTable(boolean resultFlag , String actual , String expected, String itemId , String workFlow)
	{
		if(resultFlag)
		{
			publishResults(resultFlag, actual , expected, "Check State from Item table for workflow "+ workFlow + " with itemId "+ itemId );
			moGenericMethodsLog.info("Actual State value "+ actual + " matches with Expected "+ expected +" for workFlow "+ workFlow + " with itemId "+ itemId );
		}else {
			publishResults(resultFlag, actual , expected, "Check State from Item table for workflow "+ workFlow + " with itemId "+ itemId );
			moGenericMethodsLog.error("Actual State value "+ actual + " not matches with Expected "+ expected +" for workFlow "+ workFlow + " with itemId "+ itemId );
		}
	}

	/*
	 * 23.8 Initialise variables
	 */
	private static void initialiseVariables()
	{
		fredState510Flag = false; fredState515Flag = false; fredState20Flag = false; fredState21Flag = false;
		fraudulentFlag = false; notProcessedFlag = false; suspectFlag = false; okFlag = false;
		thresholdFlag99KOrLess = false; thresholdFlag100KOrLess = false; thresholdFlag100KOrMore = false;
		expectedDerivedEntityState = ""; /*entityStateMapForItem = new LinkedHashMap<String, String>();*/		
	}

	/*
	 * 23.9 Default Entity State for System generated files
	 */
	private static void defaultEntityStateMapForDebitItems()
	{		
		for(String eachItemId : expectedDebitItemId)
		{
			entityStateMapForDebitItem.put(eachItemId, switchIntialStateAs500);		
		}
	}

	/*
	 * 23.10 Default Entity State for System generated files
	 */
	private static void defaultEntityStateMapForCreditItems()
	{		
		for(String eachItemId : expectedCreditItemId)
		{
			entityStateMapForCreditItem.put(eachItemId, switchIntialStateAs500);		
		}
	}

	/*
	 * 23.11 Default Entity State for System generated files
	 */
	private static void defaultEntityStateMapForCreditItemsForBeneficiary()
	{		
		for(String eachItemId : expectedCreditItemId)
		{
			entityStateMapForCreditItemForBeneficiary.put(eachItemId, switchIntialStateAs10);		
		}
	}

	/*
	 * 23.12 Check Derived State for Beneficiary
	 */

	/*
	 * 24. Method to check PayerItemState for 12SM01 Matched scenario
	 */
	public static void validatePayerItemFor12SM01Matched(String eachItemId, String workFlow, String expectedPostingState) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "SELECT PayerItemState FROM " + getMoRepositoryTable() + ".[Base].[ItemState] where ItemID = '" + eachItemId + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			String actual = resultSet.getString("PayerItemState");
			if((actual.trim()).equals(expectedPostingState.trim()))
			{
				moGenericMethodsLog.info("Actual PayerItemState "+ actual + " Matches with Expected for itemId " + eachItemId  + " for workFlow "+ workFlow);
				publishResults(true, actual, expectedPostingState , "Check PayerItemState State for itemId "+ eachItemId + " for workFlow "+ workFlow);
			}else {
				moGenericMethodsLog.error("Actual PayerItemState "+ actual + " not Matches with Expected for itemId " +  eachItemId + " for workFlow "+ workFlow);
				publishResults(false, actual, expectedPostingState , "Check PayerItemState State for itemId "+ eachItemId + " for workFlow "+ workFlow);
			}
		}
	}

	/*
	 * 25. Get Item SKIDs created in Item Table for Postings
	 */
	public static void getItemSKID(List<String> itemIds, String workFlow) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		for(String eachItemId : itemIds)
		{
			String query = "SELECT ItemSKID FROM " + getMoRepositoryTable() + ".[Base].[Item] where ItemID = '" + eachItemId + "' and InternalMessageType = '" + workFlow + "'"
					+ " and State = '520'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{				
				itemSKIDs.add(resultSet.getString("ItemSKID"));
				if(!(resultSet.getString("ItemSKID") == null))
				{
					derivedState520ItemIds.add(eachItemId);
				}				
			}
		}
	}

	/*
	 * 26. Check Posting state for PTMA01
	 */
	public static void checkPostingStateForPTMA01(List<String> itemIds, String workFlow, String expectedPostingState) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		List<String> testDebits = itemIds;
		for(int eachItemIndex = 0 ; eachItemIndex < itemIds.size() ; eachItemIndex++)
		{
			String tempItemId = testDebits.get(eachItemIndex);
			String query = "SELECT PostingState FROM " + getMoRepositoryTable() + ".[Base].[ItemState] where ItemID = '" + tempItemId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				String actual = resultSet.getString("PostingState");
				if((actual.trim()).equals(expectedPostingState.trim()))
				{
					moGenericMethodsLog.info("Actual PostingState "+ actual + " Matches with Expected for itemId " + tempItemId  + " for workFlow "+ workFlow);
					publishResults(true, actual, expectedPostingState , "Check Posting State for itemId "+ tempItemId + " for workFlow "+ workFlow);
				}else {
					moGenericMethodsLog.error("Actual PostingState "+ actual + " not Matches with Expected for itemId " +  tempItemId + " for workFlow "+ workFlow);
					publishResults(false, actual, expectedPostingState , "Check Posting State for itemId "+ tempItemId + " for workFlow "+ workFlow);
				}
			}
		}
	}

	/*
	 * 27. Check Posting State for PTMR01
	 */
	public static void checkPostingStateForPTMR01(String eachItemId, String workFlow, String expectedPostingState) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "SELECT PostingState FROM " + getMoRepositoryTable() + ".[Base].[ItemState] where ItemID = '" + eachItemId + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			String actual = resultSet.getString("PostingState");
			if((actual.trim()).equals(expectedPostingState.trim()))
			{
				moGenericMethodsLog.info("Actual PostingState "+ actual + " Matches with Expected for itemId " + eachItemId  + " for workFlow "+ workFlow);
				publishResults(true, actual, expectedPostingState , "Check Posting State for itemId "+ eachItemId + " for workFlow "+ workFlow);
			}else {
				moGenericMethodsLog.error("Actual PostingState "+ actual + " not Matches with Expected for itemId " +  eachItemId + " for workFlow "+ workFlow);
				publishResults(false, actual, expectedPostingState , "Check Posting State for itemId "+ eachItemId + " for workFlow "+ workFlow);
			}
		}
	}

	/*
	 * 28. Check Posting State for PERM01
	 */
	public static void checkPostingStateForPERM01(String workFlow, String postingContent) throws Exception
	{
		itemIdWithNoExtracts = new ArrayList<String>();
		String entitiesSection = postingContent.substring(postingContent.indexOf("<Entities>"), postingContent.indexOf("</Entities>"));
		do 
		{
			entitiesSection = entitiesSection.substring(entitiesSection.indexOf("<EntityType>I</EntityType>") + "<EntityType>I</EntityType>".length());
			String entityId = entitiesSection.substring(entitiesSection.indexOf("<EntityId>"), entitiesSection.indexOf("</EntityId>")+ "</EntityId>".length());
			String entityState = entitiesSection.substring(entitiesSection.indexOf("<EntityState>"), entitiesSection.indexOf("</EntityState>") + "</EntityState>".length());
			String tempEntityId = entityId.substring(entityId.indexOf("<EntityId>") + "<EntityId>".length(), entityId.indexOf("</EntityId>"));
			String tempEntityState = entityState.substring(entityState.indexOf("<EntityState>") + "<EntityState>".length(), entityState.indexOf("</EntityState>")); 
			if(tempEntityState.equals("975"))
			{
				itemIdWithNoExtracts.add(tempEntityId);
				moGenericMethodsLog.info("No Extract record in " + workFlow + " file " + " with EntityType- E for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
				validationStepInformation("No Extract record in " + workFlow + " file " + " with EntityType- E for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
			}else {
				moGenericMethodsLog.info("Extract record available in " + workFlow + " file " + " for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
				validationStepInformation("Extract record available in " + workFlow + " file " + " for itemId " + tempEntityId + " with Posting state as " + tempEntityState);	
			}
			permFileEntityStates.put(tempEntityId, tempEntityState);
		}while(entitiesSection.indexOf("<EntityType>I</EntityType>") > 0); 
		moGenericMethodsLog.info("PERM Input Test-Data file Entity States are :"+ permFileEntityStates);
		for(Entry<String, String> eachPERMEntityStates : permFileEntityStates.entrySet())
		{
			String tempEntityId =  eachPERMEntityStates.getKey();
			String tempEntityState = eachPERMEntityStates.getValue();

			String query = "SELECT PostingState FROM " + getMoRepositoryTable() + ".[Base].[ItemState] where ItemID = '" + tempEntityId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				String actual = resultSet.getString("PostingState");
				if((actual.trim()).equals(tempEntityState.trim()))
				{
					moGenericMethodsLog.info("Actual PostingState "+ actual + " Matches with Expected for itemId " + tempEntityId  + " for workFlow "+ workFlow);
					publishResults(true, actual, tempEntityState.trim() , "Check Posting State for itemId "+ tempEntityId + " for workFlow "+ workFlow);
				}else {
					moGenericMethodsLog.error("Actual PostingState "+ actual + " not Matches with Expected for itemId " +  tempEntityId + " for workFlow "+ workFlow);
					publishResults(false, actual, tempEntityState.trim() , "Check Posting State for itemId "+ tempEntityId + " for workFlow "+ workFlow);
				}
			}			
		}
	}

	/*
	 * 29. Check Posting State for PEMA01 and PRMA01
	 */
	public static void checkPostingStateForPEMA01(HashMap<String, String> itemIdAndPostingState , String workFlow) throws Exception
	{
		for(Entry<String, String> eachItemAndItsPostingState : itemIdAndPostingState.entrySet() )
		{
			String tempItemId = eachItemAndItsPostingState.getKey();
			String tempItemPostingState = eachItemAndItsPostingState.getValue();
			String query = "SELECT PostingState FROM " + getMoRepositoryTable() + ".[Base].[ItemState] where ItemID = '" + tempItemId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				String actual = resultSet.getString("PostingState");
				if(actual.trim().equals(tempItemPostingState))
				{
					moGenericMethodsLog.info("Actual PostingState "+ actual + " Matches with Expected for itemId " + tempItemId  + " for workFlow "+ workFlow);
					publishResults(true, actual, tempItemPostingState.trim() , "Check Posting State for itemId "+ tempItemId + " for workFlow "+ workFlow);
				}else {
					moGenericMethodsLog.error("Actual PostingState "+ actual + " not Matches with Expected for itemId " +  tempItemId + " for workFlow "+ workFlow);
					publishResults(false, actual, tempItemPostingState.trim() , "Check Posting State for itemId "+ tempItemId + " for workFlow "+ workFlow);
				}
			}				
		}
	}

	/*
	 * 30. Check Posting State for PRRM01
	 */
	public static void checkPostingStateForPRRM01(String workFlow, String postingContent) throws Exception
	{
		String entitiesSection = postingContent.substring(postingContent.indexOf("<Entities>"), postingContent.indexOf("</Entities>"));
		do 
		{
			entitiesSection = entitiesSection.substring(entitiesSection.indexOf("<EntityType>I</EntityType>") + "<EntityType>I</EntityType>".length());
			String entityId = entitiesSection.substring(entitiesSection.indexOf("<EntityId>"), entitiesSection.indexOf("</EntityId>")+ "</EntityId>".length());
			String entityState = entitiesSection.substring(entitiesSection.indexOf("<EntityState>"), entitiesSection.indexOf("</EntityState>") + "</EntityState>".length());
			String tempEntityId = entityId.substring(entityId.indexOf("<EntityId>") + "<EntityId>".length(), entityId.indexOf("</EntityId>"));
			String tempEntityState = entityState.substring(entityState.indexOf("<EntityState>") + "<EntityState>".length(), entityState.indexOf("</EntityState>")); 
			if(tempEntityState.equals("965"))
			{
				moGenericMethodsLog.info("Pending Response record present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
				validationStepInformation("Pending Response record present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
			}else if(tempEntityState.equals("970"))
			{
				moGenericMethodsLog.info("Success Response record present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
				validationStepInformation("Success Response record present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
			}else if(tempEntityState.equals("960"))
			{
				moGenericMethodsLog.info("Failure Response record present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
				validationStepInformation("Failure Response rrecord present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);

			}else if(tempEntityState.equals("958"))
			{
				moGenericMethodsLog.info("Exception Flagged by Client bank , meaning no final outcome will be sent record present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
				validationStepInformation("Exception Flagged by Client bank , meaning no final outcome will be sent record present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);	
			}else if(tempEntityState.equals("959"))
			{
				moGenericMethodsLog.info("Failure Aggregated Response, final outcome record present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
				validationStepInformation("Failure Aggregated Response, final outcome record present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);	
			}else if(tempEntityState.equals("963"))
			{
				moGenericMethodsLog.info("Posting record with Entity Type as I present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);
				validationStepInformation("Posting record with Entity Type as I present in " + workFlow + " file for itemId " + tempEntityId + " with Posting state as " + tempEntityState);	
			}
			prrmFileEntityStates.put(tempEntityId, tempEntityState);
		}while(entitiesSection.indexOf("<EntityType>I</EntityType>") > 0); 
		moGenericMethodsLog.info("PRRM Input Test-Data file Entity States are :"+ prrmFileEntityStates);
		for(Entry<String, String> eachPRRMEntityStates : prrmFileEntityStates.entrySet())
		{
			String tempEntityId =  eachPRRMEntityStates.getKey();
			String tempEntityState = eachPRRMEntityStates.getValue();
			String query = "SELECT PostingState FROM " + getMoRepositoryTable() + ".[Base].[ItemState] where ItemID = '" + tempEntityId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				String actual = resultSet.getString("PostingState");
				if((actual.trim()).equals(tempEntityState.trim()))
				{
					moGenericMethodsLog.info("Actual PostingState "+ actual + " Matches with Expected for itemId " + tempEntityId  + " for workFlow "+ workFlow);
					publishResults(true, actual, tempEntityState.trim() , "Check Posting State for itemId "+ tempEntityId + " for workFlow "+ workFlow);
				}else {
					moGenericMethodsLog.error("Actual PostingState "+ actual + " not Matches with Expected for itemId " +  tempEntityId + " for workFlow "+ workFlow);
					publishResults(false, actual, tempEntityState.trim() , "Check Posting State for itemId "+ tempEntityId + " for workFlow "+ workFlow);
				}
			}			
		}		
	}

	/*
	 * 31. Update Payer Item State to Derived States
	 */
	public static void updatePayerItemToDerivedStates() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		for(Entry<String, String> eachDebitItem : entityStateMapForDebitItem.entrySet())
		{
			String itemId = eachDebitItem.getKey();
			String query = "Update " + getMoRepositoryTable() + ".[Base].[ItemState] Set PayerItemState = '" + eachDebitItem.getValue() + "' "
					+ "Where ItemID = '" + itemId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);

			query = "Update " + getMoRepositoryTable() + ".[Base].[ItemState] Set PayerItemDirtyFlag = '10' "
					+ "Where ItemID = '" + itemId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);

			query = "Update " + getMoRepositoryTable() + ".[Base].[ItemState] Set PayerItemBatchedFlag = '10' "
					+ "Where ItemID = '" + itemId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			moGenericMethodsLog.info("Msg12 checked. PayerItemState , PayerItemDirtyFlag and PayerItemBatchedFlag of itemId " + itemId+ " updated to Derived Entity States ");
			validationStepInformation("Msg12 checked. PayerItemState , PayerItemDirtyFlag and PayerItemBatchedFlag of itemId " + itemId+ " updated to Derived Entity States ");
		}
	}

	/*
	 * 32. Update Payer Item State to Submitted to ICS CI
	 */
	public static void updatePayerItemStateAsSubmitToCI(String itemId) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		String query = "Update " + getMoRepositoryTable() + ".[Base].[ItemState] Set PayerItemState = '570' "
				+ "Where ItemID = '" + itemId + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);			
		moGenericMethodsLog.info("Msg11 checked. PayerItemState of itemId " + itemId+ " updated to 570 Entity States ");

	}

	/*
	 * 33. Update Window2 Date to Day1 Date
	 */
	public static void updateWindows2DateToDay1Date(List<String> itemIds) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InterruptedException
	{
		for(String eachItemId : itemIds)
		{
			String query = "Update " + getMoRepositoryTable() + ".[Base].[ItemState] Set Window2BusinessDate = '" + dayOneResponseWindow.get(0) + "' "
					+ "Where ItemID = '" + eachItemId + "'";
			getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		}		
		/*moGenericMethodsLog.info("Updated Window2BusinessDate in ItemState table for ItemIds  to Window1 Date for MSG11 to process");
		validationStepInformation("Updated Window2BusinessDate in ItemState table for ItemIds  to Window1 Date for MSG11 to process");		*/
	}

	/*
	 * 34. Update Window 2 Date to Day2 Date
	 */
	public static void updateWindows2DateToDayTwoDate(List<String> itemIds) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InterruptedException
	{
		for(String eachItemId : itemIds)
		{
			String query = "Update " + getMoRepositoryTable() + ".[Base].[ItemState] Set Window2BusinessDate = '" + dayTwoResponseWindow.get(0) + "' "
					+ "Where ItemID = '" + eachItemId + "'";
			getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		}
	}

	/*
	 * 35. Check Posting state for 06DM01 work-flow
	 */
	public static void checkPostingStateFor06DM01(String workFlow, String postingContent) throws Exception
	{
		initialStateFromFred = new HashMap<String, String>();
		String entitiesSection = postingContent.substring(postingContent.indexOf("<Entities>"), postingContent.indexOf("</Entities>"));
		do 
		{
			entitiesSection = entitiesSection.substring(entitiesSection.indexOf("<EntityType>I</EntityType>") + "<EntityType>I</EntityType>".length());
			String entityId = entitiesSection.substring(entitiesSection.indexOf("<EntityId>"), entitiesSection.indexOf("</EntityId>")+ "</EntityId>".length());
			String entityState = entitiesSection.substring(entitiesSection.indexOf("<EntityState>"), entitiesSection.indexOf("</EntityState>") + "</EntityState>".length());
			String tempEntityId = entityId.substring(entityId.indexOf("<EntityId>") + "<EntityId>".length(), entityId.indexOf("</EntityId>"));
			String tempEntityState = entityState.substring(entityState.indexOf("<EntityState>") + "<EntityState>".length(), entityState.indexOf("</EntityState>")); 
			initialStateFromFred.put(tempEntityId, tempEntityState);
			if(tempEntityState.equals("550"))
			{
				try
				{
					if(prrmFileEntityStates.get(tempEntityId).equals("970"))
					{
						moGenericMethodsLog.info("DEW - Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting Re-trigger not needed");
						validationStepInformation("DEW - Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting Re-trigger not needed");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("960"))
					{
						moGenericMethodsLog.info("DEW - Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank NO Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank NO Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("958"))
					{
						moGenericMethodsLog.info("DEW - Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Exeption Flagged with no final outcome response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not  matches with bank Exeption Flagged with no final outcome response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("959"))
					{
						moGenericMethodsLog.info("DEW - Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank failure Aggregated Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						moGenericMethodsLog.info("DEW - Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank failure Aggregated Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("963"))
					{
						moGenericMethodsLog.info("DEW - Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						moGenericMethodsLog.info("DEW - Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("965"))
					{
						moGenericMethodsLog.info("DEW - Pending Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And waiting for the Posting response");
						validationStepInformation("DEW - Pending Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And waiting for the Posting response");
					}	
				}catch(Exception e)
				{

				}					    	
			}		    
			else if(tempEntityState.equals("551"))
			{		       	
				try 
				{
					if(prrmFileEntityStates.get(tempEntityId).equals("970"))
					{
						moGenericMethodsLog.info("DEW - Default Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting Re-trigger not needed");
						validationStepInformation("DEW - Default Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting Re-trigger not needed");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("960"))
					{
						moGenericMethodsLog.info("DEW - Default Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank NO Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Default Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank NO Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("958"))
					{
						moGenericMethodsLog.info("DEW - Default Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Exeption Flagged with no final outcome response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Default Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not  matches with bank Exeption Flagged with no final outcome response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("959"))
					{
						moGenericMethodsLog.info("DEW - Default Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank failure Aggregated Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Default Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank failure Aggregated Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("963"))
					{
						moGenericMethodsLog.info("DEW - Default Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Default Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}	
				}catch(Exception e)
				{

				}				
			}
			else if(tempEntityState.equals("560"))
			{
				try
				{
					if(prrmFileEntityStates.get(tempEntityId).equals("970"))
					{
						moGenericMethodsLog.info("DEW - Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}
					else if(prrmFileEntityStates.get(tempEntityId).equals("960"))
					{
						moGenericMethodsLog.info("DEW - Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank NO Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting Re-trigger not needed");
						validationStepInformation("DEW - Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank NO Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting Re-trigger not needed");
					}
					else if(prrmFileEntityStates.get(tempEntityId).equals("958"))
					{
						moGenericMethodsLog.info("DEW - Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Exeption Flagged with no final outcome response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not  matches with bank Exeption Flagged with no final outcome response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("959"))
					{
						moGenericMethodsLog.info("DEW - Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Failure Aggregated Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Failure Aggregated Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}
					else if(prrmFileEntityStates.get(tempEntityId).equals("963"))
					{
						moGenericMethodsLog.info("DEW - Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}
				}catch(Exception e)
				{

				}				
			}
			else if(tempEntityState.equals("561"))
			{
				try
				{
					if(prrmFileEntityStates.get(tempEntityId).equals("970"))
					{
						moGenericMethodsLog.info("DEW - Default Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Default Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}
					else if(prrmFileEntityStates.get(tempEntityId).equals("960"))
					{
						moGenericMethodsLog.info("DEW - Default Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank NO Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting Re-trigger not needed");
						validationStepInformation("DEW - Default Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank NO Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting Re-trigger not needed");
					}
					else if(prrmFileEntityStates.get(tempEntityId).equals("958"))
					{
						moGenericMethodsLog.info("DEW - Default Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Exeption Flagged with no final outcome response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Default Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not  matches with bank Exeption Flagged with no final outcome response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("959"))
					{
						moGenericMethodsLog.info("DEW - Default Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Failure Aggregated Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Default Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Failure Aggregated Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}
					else if(prrmFileEntityStates.get(tempEntityId).equals("963"))
					{
						moGenericMethodsLog.info("DEW - Default Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Default Not Paid Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}
				}catch(Exception e)
				{

				}				
			}
			else if(tempEntityState.equals("562"))
			{
				try{
					if(prrmFileEntityStates.get(tempEntityId).equals("970"))

					{
						moGenericMethodsLog.info("DEW - Not Paid Always No Pay Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Not Paid Always No Pay Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}
					else if(prrmFileEntityStates.get(tempEntityId).equals("960"))
					{
						moGenericMethodsLog.info("DEW - Not Paid Always No Pay Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank NO Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting Re-trigger not needed");
						validationStepInformation("DEW - Not Paid Always No Pay Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " matches with bank NO Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting Re-trigger not needed");
					}
					else if(prrmFileEntityStates.get(tempEntityId).equals("958"))
					{
						moGenericMethodsLog.info("DEW - Not Paid Always No Pay Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Exeption Flagged with no final outcome response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Not Paid Always No Pay Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not  matches with bank Exeption Flagged with no final outcome response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}else if(prrmFileEntityStates.get(tempEntityId).equals("959"))
					{
						moGenericMethodsLog.info("DEW - Not Paid Always No Pay Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Failure Aggregated Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Not Paid Always No Pay Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Failure Aggregated Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}
					else if(prrmFileEntityStates.get(tempEntityId).equals("963"))
					{
						moGenericMethodsLog.info("DEW - Not Paid Always No Pay Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW - Not Paid Always No Pay Response record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Response as " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}
				}catch(Exception e)
				{

				}
			}
			else if(tempEntityState.equals("541"))
			{
				try {
					if((prrmFileEntityStates.get(tempEntityId).equals("970")) || (prrmFileEntityStates.get(tempEntityId).equals("960")) ||
							(prrmFileEntityStates.get(tempEntityId).equals("958")) || (prrmFileEntityStates.get(tempEntityId).equals("959")) ||
							(prrmFileEntityStates.get(tempEntityId).equals("963")))
					{
						moGenericMethodsLog.info("DEW Reposting Request-Override record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
						validationStepInformation("DEW Reposting Request-Override " + tempEntityState + " record present in " + workFlow + " file for itemId " + tempEntityId + " with DEW state as " + tempEntityState + " not matches with bank Pay response " + prrmFileEntityStates.get(tempEntityId) + " . And Posting will Re-trigger");
					}
				}catch(Exception e)
				{

				}					    	
			}
		}while(entitiesSection.indexOf("<EntityType>I</EntityType>") > 0); 
		moGenericMethodsLog.info(workFlow + " Input Test-Data file Entity States are :"+ initialStateFromFred);
		dewToMoRecordForNoPRRMResponse(workFlow);
		for(Entry<String, String> eachEntityStates : initialStateFromFred.entrySet())
		{
			String tempEntityId =  eachEntityStates.getKey();
			String tempEntityState = eachEntityStates.getValue();
			String query = "SELECT PayerItemState FROM " + getMoRepositoryTable() + ".[Base].[ItemState] where ItemID = '" + tempEntityId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				String actual = resultSet.getString("PayerItemState");
				if((actual.trim()).equals(tempEntityState.trim()))
				{
					moGenericMethodsLog.info("Actual PayerState "+ actual + " Matches with Expected for itemId " + tempEntityId  + " for workFlow "+ workFlow);
					publishResults(true, actual, tempEntityState.trim() , "Check Posting State for itemId "+ tempEntityId + " for workFlow "+ workFlow);
				}else {
					moGenericMethodsLog.error("Actual PayerState "+ actual + " not Matches with Expected for itemId " +  tempEntityId + " for workFlow "+ workFlow);
					publishResults(false, actual, tempEntityState.trim() , "Check Posting State for itemId "+ tempEntityId + " for workFlow "+ workFlow);
				}
			}			
		}			
	}

	/*
	 * 35.1 DEW State record for PRRM with Nil Response and PERM with No Extract Details for given ItemIds
	 */
	public static void dewToMoRecordForNoPRRMResponse(String workFlow)
	{
		try{
			if(itemIdWithNoExtracts.size() > 0)
			{
				for(String eachItemId : itemIdWithNoExtracts)
				{
					String dewPostingState = initialStateFromFred.get(eachItemId);
					if((initialStateFromFred.get(eachItemId)).equals("541"))
					{
						moGenericMethodsLog.info("DEW Reposting Request-Override record present in " + workFlow + " file for itemId " + eachItemId + " with DEW state " + dewPostingState + " not matches with No bank Pay response. Hence its the DEB Override condition and Postings to be Re-triggered for itemId " + eachItemId);
						validationStepInformation("DEW Reposting Request-Override record present in " + workFlow + " file for itemId " + eachItemId + " with DEW state " + dewPostingState + " not matches with No bank Pay response. Hence its the DEB Override condition and Postings to be Re-triggered for itemId " + eachItemId);
					}else if(initialStateFromFred.get(eachItemId).equals("561"))
					{
						moGenericMethodsLog.info("DEW Default Not Paid Decision record present in " + workFlow + " file for itemId " + eachItemId + " with DEW state " + dewPostingState + " for No bank Pay response. Hence Not Pay as final Decision and No Posting Re-trigger needed");
						validationStepInformation("DEW Default Not Paid Decision record present in " + workFlow + " file for itemId " + eachItemId + " with DEW state " + dewPostingState + " for No bank Pay response. Hence Not Pay as final Decision and No Posting Re-trigger needed");
					}else if(initialStateFromFred.get(eachItemId).equals("560"))
					{
						moGenericMethodsLog.info("DEW Not Paid Decision record present in " + workFlow + " file for itemId " + eachItemId + " with DEW state " + dewPostingState + " for No bank Pay response. Hence Not Pay as final Decision and No Posting Re-trigger needed");
						validationStepInformation("DEW Not Paid Decision record present in " + workFlow + " file for itemId " + eachItemId + " with DEW state " + dewPostingState + " for No bank Pay response. Hence Not Pay as final Decision and No Posting Re-trigger needed");
					}else if(initialStateFromFred.get(eachItemId).equals("550"))
					{
						moGenericMethodsLog.info("DEW Paid Decision record present in " + workFlow + " file for itemId " + eachItemId + " with DEW state " + dewPostingState + " for No bank Pay response. Hence its the DEB Override condition and Postings to be Re-triggered for itemId " + eachItemId);
						validationStepInformation("DEW Paid Decision record present in " + workFlow + " file for itemId " + eachItemId + " with DEW state " + dewPostingState + " for No bank Pay response. Hence its the DEB Override condition and Postings to be Re-triggered for itemId " + eachItemId);
					}else if(initialStateFromFred.get(eachItemId).equals("551"))
					{
						moGenericMethodsLog.info("DEW Default Paid Decision record present in " + workFlow + " file for itemId " + eachItemId + " with DEW state " + dewPostingState + " for No bank Pay response. Hence its the DEB Override condition and Postings to be Re-triggered for itemId " + eachItemId);
						validationStepInformation("DEW Default Paid Decision record present in " + workFlow + " file for itemId " + eachItemId + " with DEW state " + dewPostingState + " for No bank Pay response. Hence its the DEB Override condition and Postings to be Re-triggered for itemId " + eachItemId);
					}
				}
			}		
		}catch(Exception e)
		{

		}

	}

	/*
	 * 36. Validate BeneficiaryItemState for given ItemId and WorkFlow
	 */
	public static void validateBeneficiaryItemState(List<String> itemIds, String workFlow) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		for(String itemId : itemIds)
		{
			String query = "SELECT BeneficiaryItemState FROM " + getMoRepositoryTable() + ".[Base].[ItemState] where ItemID = '" + itemId + "'";	
			getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				String actual = resultSet.getString("BeneficiaryItemState");
				if(actual.equals("211"))
				{
					moGenericMethodsLog.info("BeneficiaryItemState of workFlow "+workFlow + " for itemId " + itemId +" : "+ actual + " matches as expected"); 
				}
			}
		}
	}

	/*
	 * 37. Check State from Item Table for 07MS01
	 */
	public static void checkStateFromItemTableFor07MS01(List<String> itemIds , String workFlow ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		for(String itemId : itemIds)
		{
			String query = "SELECT State FROM " + getMoRepositoryTable() + ".[Base].[Item] where InternalMessageType = '07MS01' and "
					+ "ItemID = '" + itemId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				String actual = resultSet.getString("State");
				if((actual.trim()).equals("570"))
				{
					moGenericMethodsLog.info("Item Table actual State column value "+ actual + " Matches with Expected for itemId " + itemId  + " for workFlow "+ workFlow);
					publishResults(true, actual, "570" , "Check State from Item Table for itemId "+ itemId + " for workFlow "+ workFlow);
				}else {
					moGenericMethodsLog.error("Item Table actual State column value "+ actual + " not Matches with Expected for itemId " +  itemId + " for workFlow "+ workFlow);
					publishResults(false, actual, "570" , "Check State from Item Table for itemId "+ itemId + " for workFlow "+ workFlow);
				}
			}
		}
	}

	/*
	 * 38. Check PayerState from ItemState Table for 07MS01
	 */
	public static void checkPayerStateFromItemStateFor07MS01(List<String> itemIds  , String workFlow ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		for(String itemId : itemIds)
		{
			String query = "SELECT PayerItemState FROM " + getMoRepositoryTable() + ".[Base].[ItemState] where ItemID = '" + itemId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			while(resultSet.next())
			{
				String actual = resultSet.getString("PayerItemState");
				if((actual.trim()).equals("570"))
				{
					moGenericMethodsLog.info("ItemState Table actual PayerItemState column value "+ actual + " Matches with Expected for itemId " + itemId  + " for workFlow "+ workFlow);
					publishResults(true, actual, "570" , "Check PayerItemState from ItemState Table for itemId "+ itemId + " for workFlow "+ workFlow);
				}/*else {
					moGenericMethodsLog.error("ItemState Table actual PayerItemState column value "+ actual + " not Matches with Expected for itemId " +  itemId + " for workFlow "+ workFlow);
					publishResults(false, actual, "570" , "Check State from ItemState Table for itemId "+ itemId + " for workFlow "+ workFlow);
				}*/
			}
		}
	}

	/*
	 * 39. Check Source State for 07MS01
	 */
	public static void checkSourceStateFor07MS01(String sourceId, String workFlow) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		String query = "SELECT SourceState FROM " + getMoRepositoryTable() + ".[Base].[Source] where SourceID = '"  + sourceId + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			String actual = resultSet.getString("SourceState");
			if((actual.trim()).equals("568"))
			{
				moGenericMethodsLog.info("Source Table actual SourceState column value "+ actual + " Matches with Expected for SourceID " + sourceId  + " for workFlow "+ workFlow);
				publishResults(true, actual, "568" , "Check SourceState column in Source Table for SourceID " + sourceId + " for workFlow "+ workFlow);
			}else {
				moGenericMethodsLog.error("Source Table actual SourceState column value "+ actual + " not Matches with Expected for SourceID " + sourceId  + " for workFlow "+ workFlow);
				publishResults(false, actual, "568" , "Check SourceState column in Source Table for SourceID " + sourceId + " for workFlow "+ workFlow);
			}
		}
	}

	/*
	 * 40. Update Source State to 570 Status for 07MS01
	 */
	public static void updateSourceStateTo570(String sourceId, String workFlow) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		String query = "Update "+ getMoRepositoryTable() + ".[Base].[Source] Set SourceState = 570 where SourceID = '"+  sourceId +"'";
		getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		moGenericMethodsLog.info("Updated SourceState from 568 to 570");
	}

	/*
	 * 41. DB Check Window1BusinessDate Column
	 */
	public static void dbCheckWindow1ColumnFromItemStateTable(List<String> itemIds, String workFlow, String sourceId, String windowDate) throws Exception
	{
		moGenericMethodsLog.info("Validating Window1BusinessDate column in ItemState table for itemIds "+ itemIds);
		for(String itemId : itemIds)
		{
			String query = "SELECT Window1BusinessDate FROM " + getMoRepositoryTable() + ".[Base].[ItemState] "
					+ "Where ItemID = '"+ itemId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			String actualWindow1BusinessDate = getICSRetrieveColumnValue(resultSet);
			String expectedWindow1BusinessDate = "";

			try{
				expectedWindow1BusinessDate = windowDate.substring(0, windowDate.indexOf("T"));	
			}catch(Exception e)
			{
				expectedWindow1BusinessDate = 	windowDate;
			}


			if(actualWindow1BusinessDate==null)
			{
				if(expectedWindow1BusinessDate == null)
				{
					moGenericMethodsLog.error("Window1BusinessDate column value is " + actualWindow1BusinessDate+ " for itemId "+ itemId + " with workFlow "
							+ workFlow + " same as in workFlow DayOneRspnWndwDate content as " + expectedWindow1BusinessDate + 
							" in source table for sourceId "+ sourceId);
					validationErrorInformation("Window1BusinessDate column value is " + actualWindow1BusinessDate+ " for itemId "+ itemId + " with workFlow "
							+ workFlow + " same as in workFlow DayOneRspnWndwDate content as " + expectedWindow1BusinessDate + 
							" in source table for sourceId "+ sourceId);
					Component.assertTrue(false,"Window1BusinessDate column value is " + actualWindow1BusinessDate+ " for itemId "+ itemId + " with workFlow "
							+ workFlow + " same as in workFlow DayOneRspnWndw content as " + expectedWindow1BusinessDate + 
							" in source table for sourceId "+ sourceId);
				}else {
					//					moGenericMethodsLog.error("Window1BusinessDate column value is " + actualWindow1BusinessDate+ " for itemId "+ itemId + " with workFlow "
					//							+ workFlow + " not same as in workFlow DayOneRspnWndwDate content as " + expectedWindow1BusinessDate + 
					//							" in source table for sourceId "+ sourceId);
					//					validationErrorInformation("Window1BusinessDate column value is " + actualWindow1BusinessDate+ " for itemId "+ itemId + " with workFlow "
					//							+ workFlow + " not same as in workFlow DayOneRspnWndwDate content as " + expectedWindow1BusinessDate + 
					//							" in source table for sourceId "+ sourceId);
					//					Component.assertEquals(actualWindow1BusinessDate, expectedWindow1BusinessDate, "Window1BusinessDate column value is NULL for itemId "+ itemId + " for workFlow "
					//							+ workFlow + " not same as in workFlow DayOneRspnWndw content as " + expectedWindow1BusinessDate + 
					//							" in source table for sourceId "+ sourceId);					
				}
			}else if(actualWindow1BusinessDate.equals(expectedWindow1BusinessDate)) {
				moGenericMethodsLog.info("Window1BusinessDate column value is " + actualWindow1BusinessDate+ " for itemId "+ itemId + " with workFlow "
						+ workFlow + " same as in workFlow DayOneRspnWndwDate content as " + expectedWindow1BusinessDate + 
						" in source table for sourceId "+ sourceId);	
				publishResults(true,"Window1BusinessDate column value is " + actualWindow1BusinessDate+ " for itemId "+ itemId + " with workFlow " + workFlow, 
						"DayOneRspnWndwDate content as " + expectedWindow1BusinessDate + " in source table for sourceId "+ sourceId ,
						"Check values of DayOneRspnWndwStartDtTm to be populated in window1 column in the ItemState table");
			}else {
				moGenericMethodsLog.error("Window1BusinessDate column value is " + actualWindow1BusinessDate+ " for itemId "+ itemId + " with workFlow "
						+ workFlow + " not same as in workFlow DayOneRspnWndwDate content as " + expectedWindow1BusinessDate + 
						" in source table for sourceId "+ sourceId);	
				publishResults(false,"Window1BusinessDate column value is " + actualWindow1BusinessDate+ " for itemId "+ itemId + " with workFlow " + workFlow, 
						"DayOneRspnWndwDate content as " + expectedWindow1BusinessDate + " in source table for sourceId "+ sourceId ,
						"Check values of DayOneRspnWndwStartDtTm to be populated in window1 column in the ItemState table");
			}
		}
	}

	/*
	 * 42. DB Check Window2BusinessDate
	 */
	public static void dbCheckWindow2ColumnFromItemStateTable(List<String> itemIds, String workFlow, String sourceId, String windowDate) throws Exception
	{
		moGenericMethodsLog.info("Validating Window2BusinessDate column in ItemState table for itemIds "+ itemIds);
		for(String itemId : itemIds)
		{
			String query = "SELECT Window2BusinessDate FROM " + getMoRepositoryTable() + ".[Base].[ItemState] "
					+ "Where ItemID = '"+ itemId + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			String actualWindow2BusinessDate = getICSRetrieveColumnValue(resultSet);
			String expectedWindow2BusinessDate = "";
			try{
				expectedWindow2BusinessDate = windowDate.substring(0, windowDate.indexOf("T"));	
			}catch(Exception e)
			{
				expectedWindow2BusinessDate = windowDate;
			}
			if(actualWindow2BusinessDate==null)
			{
				if(expectedWindow2BusinessDate == null )
				{
					moGenericMethodsLog.info("Window2BusinessDate column value is " + actualWindow2BusinessDate+ " for itemId "+ itemId + " with workFlow "
							+ workFlow + " same as in workFlow DayTwoRspnWndwDate content as " + expectedWindow2BusinessDate + 
							" in source table for sourceId "+ sourceId);
					validationStepInformation("Window2BusinessDate column value is " + actualWindow2BusinessDate+ " for itemId "+ itemId + " with workFlow "
							+ workFlow + " same as in workFlow DayTwoRspnWndwDate content as " + expectedWindow2BusinessDate + 
							" in source table for sourceId "+ sourceId);
				}
				//				else {
				//					moGenericMethodsLog.error("Window2BusinessDate column value is " + actualWindow2BusinessDate+ " for itemId "+ itemId + " with workFlow "
				//							+ workFlow + " not same as in workFlow DayTwoRspnWndwDate content as " + expectedWindow2BusinessDate + 
				//							" in source table for sourceId "+ sourceId);
				//					validationStepInformation("Window2BusinessDate column value is " + actualWindow2BusinessDate+ " for itemId "+ itemId + " with workFlow "
				//							+ workFlow + " not same as in workFlow DayTwoRspnWndwDate content as " + expectedWindow2BusinessDate + 
				//							" in source table for sourceId "+ sourceId);
				//					Component.assertEquals(actualWindow2BusinessDate, expectedWindow2BusinessDate, "Window2BusinessDate column value is NULL for itemId "+ itemId + " for workFlow "
				//							+ workFlow + " not same as in workFlow DayTwoRspnWndwDate content as " + expectedWindow2BusinessDate + 
				//							" in source table for sourceId "+ sourceId);					
				//				}
			}else if(actualWindow2BusinessDate.equals(expectedWindow2BusinessDate)) {
				moGenericMethodsLog.info("Window2BusinessDate column value is " + actualWindow2BusinessDate+ " for itemId "+ itemId + " with workFlow "
						+ workFlow + " same as in workFlow DayTwoRspnWndwDate content as " + expectedWindow2BusinessDate + 
						" in source table for sourceId "+ sourceId);	
				publishResults(true,"Window2BusinessDate column value is " + actualWindow2BusinessDate+ " for itemId "+ itemId + " with workFlow " + workFlow, 
						"DayTwoRspnWndwDate content as " + expectedWindow2BusinessDate + " in source table for sourceId "+ sourceId ,
						"Check values of DayTwoRspnWndwDate to be populated in window1 column in the ItemState table");
			}else {
				moGenericMethodsLog.error("Window2BusinessDate column value is " + actualWindow2BusinessDate+ " for itemId "+ itemId + " with workFlow "
						+ workFlow + " same as in workFlow DayTwoRspnWndwDate content as " + expectedWindow2BusinessDate + 
						" in source table for sourceId "+ sourceId);	
				publishResults(false,"Window2BusinessDate column value is " + actualWindow2BusinessDate+ " for itemId "+ itemId + " with workFlow " + workFlow, 
						"DayTwoRspnWndwDate content as " + expectedWindow2BusinessDate + " in source table for sourceId "+ sourceId ,
						"Check values of DayTwoRspnWndwDate to be populated in window1 column in the ItemState table");
				/*Component.assertEquals(actualWindow2BusinessDate, expectedWindow2BusinessDate, "Window2BusinessDate column value is " + actualWindow2BusinessDate+ " for itemId "+ itemId + " with workFlow "
						+ workFlow + " not same as in workFlow DayTwoRspnWndwDate content as " + expectedWindow2BusinessDate + 
						" in source table for sourceId "+ sourceId);*/
			}
		}
	}

	/*
	 * 43. DB Check ActivityId
	 */
	public static void dbCheckActivityIdColumn() throws Exception
	{
		moGenericMethodsLog.info("Validating ActivityId created in JobExecutionLog and Logging table");
		checkAndValidateActivityLogGenerated(getActivityLogGenerates(scheduleId), scheduleId, 
				"Check the ActivityID column in the Logging, JobExecutionLog and ScheduleWorkQueue");
	}

	/*
	 * 43.1 Check And Validate Activity Log Generated
	 */
	private static void checkAndValidateActivityLogGenerated(String activityId, String scheduleId, String validationHeader) throws Exception
	{
		int tempIndex = activityId.indexOf("_");
		String tempString = activityId.substring(0, tempIndex);
		if(tempString.equals(scheduleId))
		{
			String uniqueLateral = activityId.substring(++tempIndex);
			String query = "Select ScheduleID FROM " + getMoSchedulerTable() + ".[Scheduler].[JobExecutionLog] "
					+ "Where ActivityId like '%" + uniqueLateral + "%'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			int rowRecordNo = 0;
			while(resultSet.next())
			{	
				rowRecordNo++;
			}
			if(rowRecordNo > 1)
			{
				tempResult = Component.verifyTrue(false, "Activity Id generated is not a unique lateral");
				publishResults(tempResult, "Activity Id " + activityId + " generated is not a unique lateral in JobExecutionLog and ScheduleWorkQueue tables",
						"Should generate unique ActiviyId in JobExecutionLog and ScheduleWorkQueue tables" , validationHeader);
				moGenericMethodsLog.info("Activity Id " + activityId + " generated is not a unique lateral in JobExecutionLog and ScheduleWorkQueue tables");
			}else {
				tempResult = Component.verifyTrue(true, "Activity Id generated is not a unique lateral");
				publishResults(tempResult, "Unique Activity Id " + activityId + " generated in JobExecutionLog and ScheduleWorkQueue tables",
						"Should generate unique ActiviyId in JobExecutionLog and ScheduleWorkQueue tables" , validationHeader);
				moGenericMethodsLog.info("Unique Activity Id " + activityId + " generated in JobExecutionLog and ScheduleWorkQueue tables");
			}
		}else {
			tempResult = Component.verifyTrue(false, "Activity Id generated is not a unique lateral");
			publishResults(tempResult, "Activity Id " + activityId + " generated is not a unique lateral in JobExecutionLog and ScheduleWorkQueue tables",
					"Should generate unique ActiviyId in JobExecutionLog and ScheduleWorkQueue tables" , validationHeader);
			moGenericMethodsLog.info("Activity Id " + activityId + " generated is not a unique lateral in JobExecutionLog and ScheduleWorkQueue tables");
		}
	}

	/*
	 * 43.2 Get ActivityLog Generated
	 */
	private static String getActivityLogGenerates(String scheduleID) throws Exception
	{
		String query = "SELECT J.ActivityId FROM " + getMoSchedulerTable() + ".[Scheduler].[JobExecutionLog] J "
				+ "JOIN " + getMoSchedulerTable() + ".[Scheduler].[ScheduledWorkQueue] S ON J.ActivityId = S.ActivityId "
				+ "Where ScheduleID = "+ scheduleID;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		activityId = getICSRetrieveColumnValue(resultSet);
		return activityId;
	}

	/*
	 * 44. Get SourceID of ISO Content
	 */
	public static String getISOSourceID()
	{
		return tempISOSourceID;
	}

	/*
	 * 45. Get SourceID of ISO Content
	 */
	public static String getISOSourceContent()
	{
		return tempISOContent;
	}

	/*
	 * 46. Get ICN Content From Source Table
	 */
	public static String getICNSourceID()
	{
		return tempICNSourceID;
	}

	/*
	 * 47. Get ICN Content From Source Table
	 */
	public static String getICNSourceContent()
	{
		return tempICNContent;
	}

	/*
	 * 48. TargetQueue Check
	 */
	private static void checkXmlInTargetQueue(String requiredTargetQueue, String workflowName, String entractId,  String validationHeader ) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		//		String query = "select conversation_group_id, casted_message_body = CASE message_type_name WHEN 'X' THEN CAST(message_body AS xml) "
		//				+ "ELSE message_body END FROM " + getMoRepositoryTable() + ".[Base].[//MO/"+ requiredTargetQueue +"]  WITH(NOLOCK) "
		//				+ "where message_type_name = '" + workflowName + "Message' order by message_enqueue_time desc";
		//		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		//		String columnNameAsCastedMessageBody = "casted_message_body";
		//		String columnNameAsConversationGroupId = "conversation_group_id";
		//		String tempValueConversationGroupId = "";
		//		boolean checkQueueFlag = false;
		//		while(resultSet.next())
		//		{			
		//			String tempValueCastedMessageBody= resultSet.getString(columnNameAsCastedMessageBody);	
		//			if((tempValueCastedMessageBody.indexOf(entractId)) > 0)
		//			{
		//				checkQueueFlag = true;
		//				tempValueConversationGroupId = resultSet.getString(columnNameAsConversationGroupId);
		//			}
		//		}
		//		if(checkQueueFlag)
		//		{
		//			publishResults(checkQueueFlag, workflowName + " payload file loaded in " +requiredTargetQueue + " with columnNameAsConversationGroupId as " + tempValueConversationGroupId,
		//					"Should see PayLoad created for Message-" + workflowName +" in MO-" + requiredTargetQueue, validationHeader	);
		//			moGenericMethodsLog.info(workflowName + " payload  file loaded in " +requiredTargetQueue + "  with columnNameAsConversationGroupId as " + tempValueConversationGroupId);
		//			/*	Component.assertTrue(checkQueueFlag, workflowName + " payload  file loaded in requiredTargetQueue");*/
		//		}else {
		//			publishResults(checkQueueFlag, workflowName + " payload file not loaded in " +requiredTargetQueue ,
		//					"Should see PayLoad created for Message-" + workflowName +" in MO-" + requiredTargetQueue, validationHeader	);
		//			moGenericMethodsLog.error(workflowName + " payload file not loaded in " +requiredTargetQueue);
		//			/*	Component.assertTrue(checkQueueFlag, workflowName + " payload file not loaded in requiredTargetQueue");*/
		//		}
	}

	/*
	 * 48.1 IA TargetQueue Check
	 */
	public static void checkXmlInIaTargetQueue(String workflowName, String extractId, String validationHeader) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InterruptedException
	{
		Thread.sleep(10000);
		checkXmlInTargetQueue("IATargetQueue", workflowName, extractId, validationHeader );
	}

	/*
	 * 48.2 FRED TargetQueue Check
	 */
	public static void checkXmlInFredTargetQueue(String workflowName, String extractId, String validationHeader) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InterruptedException
	{
		Thread.sleep(10000);
		checkXmlInTargetQueue("FREDTargetQueue", workflowName, extractId, validationHeader );
	}

	/*
	 * 48.3 DEW TargetQueue Check
	 */
	public static void checkXmlInDewTargetQueue(String workflowName, String extractId, String validationHeader) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, InterruptedException
	{
		Thread.sleep(10000);
		checkXmlInTargetQueue("DEWTargetQueue", workflowName, extractId, validationHeader );
	}

	/*
	 * 49. Check MessageFlow Insert in Receive Staging table
	 */
	public static void checkSourceIdInsertInReceiveStagingTable(String workFlow) throws Exception
	{
		Thread.sleep(10000);
		String query = "SELECT Msg = CAST(Msg AS XML), Msg_Id FROM " + getMoRepositoryTable() + ".[Base].[ReceiveStaging] "
				+ "where MessageType='" + workFlow + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		actualICNContentMsgFM01InReceiveStaging = "";
		actualRowIDMsgFM01 = "";
		while(resultSet.next())
		{
			actualICNContentMsgFM01InReceiveStaging = resultSet.getString("Msg");
			if((actualICNContentMsgFM01InReceiveStaging.indexOf(extractId.get(0)) > 0) || (actualICNContentMsgFM01InReceiveStaging.indexOf(expectedDebitItemId.get(0))>0))
			{
				actualRowIDMsgFM01 = resultSet.getString(columnMsgId);
				rowId = actualRowIDMsgFM01;
			}
		}
	}

	/*
	 * 50. Method to update 08SM01 file MsgId with 07MA01
	 */
	public static void update08SM01with07MA01(String msg08SM01InputFilePath, String msg08SM01InputFileName, String msg07MA01value) throws IOException, InterruptedException
	{
		String strExpectedISOData = FileUtils.readFileToString(new File(msg08SM01InputFilePath + msg08SM01InputFileName));
		String tempString1 = strExpectedISOData.substring(strExpectedISOData.lastIndexOf("<MsgId>") + "<MsgId>".length(), strExpectedISOData.lastIndexOf("</MsgId>"));
		msg07MA01value = msg07MA01value.substring(msg07MA01value.indexOf("MSG07.") + "MSG07.".length());
		strExpectedISOData = strExpectedISOData.replaceFirst(tempString1, msg07MA01value);
		writeToFile(msg08SM01InputFilePath+ msg08SM01InputFileName, strExpectedISOData);	
		if(strExpectedISOData.indexOf("<ErrLctn>") > 0)
		{
			String tempErrStatus = strExpectedISOData.substring(strExpectedISOData.indexOf("<ErrLctn>") + "<ErrLctn>".length() , strExpectedISOData.indexOf("</ErrLctn>"));
			if(tempErrStatus.equals("Success"))
			{
				document08SM01Status = "580";	
			}else if(tempErrStatus.equals("Failure"))
			{
				document08SM01Status = "590";	
			}
		}
		Thread.sleep(2000);
		String target = (msg08SM01InputFilePath+ msg08SM01InputFileName).replaceAll(".txt", ".xml");
		Thread.sleep(2000);
		File targetFileStream = new File(target);
		new File(msg08SM01InputFilePath+ msg08SM01InputFileName).renameTo(targetFileStream);
	}

	/*
	 * 51. Method to check SourceID created for 08SM01 message flow.
	 */
	public static void checkSourceIDOfMsg08SM01(String workFlow, String sourceIDMsg07MS01) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "Select SourceID , ISOContent = CAST(ISOContent AS XML) from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "Where MessageType = '" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		boolean tempFlag = false;
		while(resultSet.next())
		{
			tempISOContent = resultSet.getString("ISOContent");
			sourceIdMsg07MS01 = sourceIDMsg07MS01.substring(sourceIDMsg07MS01.indexOf("MSG07.") + "MSG07.".length());
			try{
				if(tempISOContent.indexOf(sourceIdMsg07MS01) > 0)
				{
					tempISOSourceID = resultSet.getString(columnSourceID);
					tempFlag = true;
					publishResults(true, "WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempISOSourceID,
							"Should see workflow "+ workFlow + " to load in source table",
							"Check workflow " + workFlow + " record insert in source table");
					moGenericMethodsLog.info("WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempISOSourceID);
				}
			}catch(Exception e)
			{
				moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in TargetQueue and in source table ");
				publishResults(false, "WorkFlow "+ workFlow + " not loaded in TargetQueue and in source table ",
						"Should see workflow "+ workFlow + " to load in TargetQueue and in source table ",
						"Check workflow " + workFlow + " record insert in TargetQueue and in source table ");
				Component.assertTrue(false, "WorkFlow "+ workFlow + " not loaded in TargetQueue and in source table ");
			}

		}
		if(!tempFlag)
		{
			publishResults(true, "WorkFlow "+ workFlow + " not loaded in source table ",
					"Should see workflow "+ workFlow + " to load in source table",
					"Check workflow " + workFlow + " record insert in source table");
			moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in source table ");
		}
	}

	/*
	 * 52. Method to check Source Batched Flag from Source Table
	 */
	public static void dbCheckSourceBatchedFlagFromSourceTable(String regressionCycle, String testCaseName, String workFlow, String sourceId) throws Exception
	{
		moGenericMethodsLog.info("Validating SourceBatchedFlagStatus in Source Table for workflow "+ workFlow);
		actualSourceBatchedFlag = getSourceBatchedFlagFromSourceTable(sourceId);
		String tempValue = getRequiredStTestData(regressionCycle, testCaseName, workFlow, keyDataAsSourceBatchedFlag);
		try{
			expectedSourceBatchedFlag = tempValue.substring(0, tempValue.indexOf("."));	
		}catch(Exception e)
		{

		}
		boolean flag = actualSourceBatchedFlag.equals(expectedSourceBatchedFlag);
		if(flag)
		{
			publishResults(flag, "SourceBatchedFlag status for workflow "+ workFlow + " is "+ actualSourceBatchedFlag,
					"Should see SourceBatchedFlag status for workflow "+ workFlow + " as "+ expectedSourceBatchedFlag,
					"Check SourceBatchedFlag status for workflow "+ workFlow);	
			moGenericMethodsLog.info("Actual SourceBatchedFlag status for workflow "+ workFlow + " is "+ actualSourceBatchedFlag);
		}else {
			publishResults(flag, "SourceBatchedFlag status for workflow "+ workFlow + " is "+ actualSourceBatchedFlag,
					"Should see SourceBatchedFlag status for workflow "+ workFlow + " as "+ expectedSourceBatchedFlag,
					"Check SourceBatchedFlag status for workflow "+ workFlow);	
			moGenericMethodsLog.error("Actual SourceBatchedFlag status for workflow "+ workFlow + " is "+ actualSourceBatchedFlag);
			Component.assertEquals(actualSourceBatchedFlag, expectedSourceBatchedFlag, "Actual SourceBatchedFlag status not matches with Expected SourceBatchedFlag status "+ expectedSourceBatchedFlag);
		}		
	}

	/*
	 * 52.1 Method to Get Source Batched Flag from Source Table
	 */
	private static String getSourceBatchedFlagFromSourceTable(String sourceId) throws Exception
	{
		String query = "Select [SourceBatchedFlag] "
				+ "from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "where SourceID = '" + sourceId + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/*
	 * 53. Method to check Source Dirty Flag from Source Table
	 */
	public static void dbCheckSourceDirtyFlagFromSourceTable(String regressionCycle, String testCaseName, String workFlow, String sourceId) throws Exception
	{
		moGenericMethodsLog.info("Validating SourceDirtyFlag Status in Source Table for workflow "+ workFlow);
		actualSourceDirtyFlag = getSourceDirtyFlagFromSourceTable(sourceId);
		String tempValue = getRequiredStTestData(regressionCycle, testCaseName, workFlow, keyDataAsSourceDirtyFlag);	
		try{
			expectedSourceDirtyFlag = tempValue.substring(0, tempValue.indexOf("."));	
		}catch(Exception e)
		{

		}
		boolean flag = actualSourceDirtyFlag.equals(expectedSourceDirtyFlag);
		if(flag)
		{
			publishResults(flag, "SourceDirtyFlag status for workflow "+ workFlow + " is "+ actualSourceDirtyFlag,
					"Should see SourceDirtyFlag status for workflow "+ workFlow + " as "+ expectedSourceDirtyFlag,
					"Check SourceDirtyFlag status for workflow "+ workFlow);	
			moGenericMethodsLog.info("Actual SourceDirtyFlag status for workflow "+ workFlow + " is "+ actualSourceDirtyFlag);
		}else {
			publishResults(flag, "SourceDirtyFlag status for workflow "+ workFlow + " is "+ actualSourceDirtyFlag,
					"Should see SourceDirtyFlag status for workflow "+ workFlow + " as "+ expectedSourceDirtyFlag,
					"Check SourceDirtyFlag status for workflow "+ workFlow);	
			moGenericMethodsLog.error("Actual SourceDirtyFlag status for workflow "+ workFlow + " is "+ actualSourceDirtyFlag);
			Component.assertEquals(actualSourceDirtyFlag, expectedSourceDirtyFlag, "Actual SourceDirtyFlag status not matches with Expected SourceDirtyFlag status  "+ expectedSourceDirtyFlag);
		}		
	}

	/*
	 * 53.1 Method to Get Source Dirty Flag from Source Table
	 */
	private static String getSourceDirtyFlagFromSourceTable(String sourceId) throws Exception
	{		
		String query = "Select [SourceDirtyFlag] "
				+ "from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "where SourceID = '" + sourceId + "'";	
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/*
	 * 54. Method to Check SourceStateColumnFromSourceTable
	 */
	public static void dbCheckSourceStateColumnFromSourceTable(String workFlow , String sourceId) throws Exception
	{
		moGenericMethodsLog.info("Validating SourceState Status in Source Table for workflow "+ workFlow);
		String actualSourceState = getSourceStateFromSourceTable(sourceId);
		boolean flag = actualSourceState.equals(document08SM01Status);
		if(flag)
		{
			publishResults(flag, "SourceState status for workflow "+ workFlow + " is "+ actualSourceState,
					"Should see SourceState status for workflow "+ workFlow + " as "+ document08SM01Status,
					"Check SourceState status for workflow "+ workFlow);	
			moGenericMethodsLog.info("Actual SourceState status for workflow "+ workFlow + " is "+ actualSourceState);
		}else {
			publishResults(flag, "SourceState status for workflow "+ workFlow + " is "+ actualSourceState,
					"Should see SourceState status for workflow "+ workFlow + " as "+ document08SM01Status,
					"Check SourceState status for workflow "+ workFlow);	
			moGenericMethodsLog.error("Actual SourceState status for workflow "+ workFlow + " is "+ actualSourceState);
		}		
	}	

	/*
	 * 54.1 Method to Get SourceStateFromSourceTable
	 */
	private static String getSourceStateFromSourceTable(String sourceId) throws Exception
	{
		String query = "Select [SourceState] "
				+ "from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "where SourceID = '" + sourceId + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/*
	 * 55. Method to Check SourceID of Msg08MA01
	 */
	public static void checkSourceIDOfMsg08MA01(String workFlow, String sourceIDMsg07MS01) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "Select SourceID , ICNContent = CAST(ICNContent AS XML) from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "Where MessageType = '" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		boolean tempFlag = false;
		while(resultSet.next())
		{
			tempICNContent = resultSet.getString("ICNContent");
			try{
				if(tempICNContent.indexOf(sourceIdMsg07MS01) > 0)
				{
					tempICNSourceID = resultSet.getString(columnSourceID);
					tempFlag = true;
					publishResults(true, "WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID,
							"Should see workflow "+ workFlow + " to load in source table",
							"Check workflow " + workFlow + " record insert in source table");
					moGenericMethodsLog.info("WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID);
				}
			}catch(Exception e)
			{
				moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in TargetQueue and in source table ");
				publishResults(false, "WorkFlow "+ workFlow + " not loaded in TargetQueue and in source table ",
						"Should see workflow "+ workFlow + " to load in TargetQueue and in source table ",
						"Check workflow " + workFlow + " record insert in TargetQueue and in source table ");
				Component.assertTrue(false, "WorkFlow "+ workFlow + " not loaded in TargetQueue and in source table ");
			}
		}
		if(!tempFlag)
		{
			publishResults(true, "WorkFlow "+ workFlow + " not loaded in source table ",
					"Should see workflow "+ workFlow + " to load in source table",
					"Check workflow " + workFlow + " record insert in source table");
			moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in source table ");
		}
	}

	/*
	 * 56. Method to Check XSD Validation For Given WorkFlow and Source/TargetQueue Content
	 */
	public static void xsdValidationForRequiredWorkFlow(String workFlow, String xsdFilePath, String messageContent) throws Exception
	{
		//		messageContent = messageContent.substring(messageContent.indexOf("/XMLSchema\">") + "/XMLSchema\">".length());
		//		String xsdContent = FileUtils.readFileToString(new File(xsdFilePath + workFlow + ".sql"));	
		//		int xsdContentIndex = xsdContent.indexOf("/XMLSchema\">") + "/XMLSchema\">".length();
		//		xsdContent = xsdContent.substring(0, xsdContentIndex);
		//		xsdContent = xsdContent + messageContent + "'";
		//		String tempDBTableName = xsdContent.substring(xsdContent.indexOf("USE ")+ "USE ".length(), xsdContent.indexOf("DECLARE"));
		//		xsdContent = xsdContent.replaceAll(tempDBTableName, moDbName + " " );
		//		File file = new File(xsdFilePath + workFlow + ".sql");
		//		if(file.delete())
		//		{
		//			String actualContentPath = xsdFilePath + workFlow  + ".txt";
		//			writeToFile(actualContentPath, xsdContent);
		//			File file2  = new File(actualContentPath);
		//			File file3 = new File(xsdFilePath + workFlow + ".sql");
		//			boolean success = file2.renameTo(file3);
		//			if(success)
		//			{
		//				xsdContent = FileUtils.readFileToString(new File(xsdFilePath + workFlow + ".sql"));	
		//				try{
		//					getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), xsdContent); 
		//					moGenericMethodsLog.info("XSD validation passed for workFlow " + workFlow );
		//					validationStepInformation("XSD validation passed for workFlow " + workFlow );
		//				}catch(Exception SQLServerException)
		//				{
		//					moGenericMethodsLog.error("XSD validation Failed for workFlow " + workFlow + " due to below reason");
		//					validationErrorInformation("XSD validation Failed for workFlow " + workFlow + " due to below reason <br> " +
		//							SQLServerException.getMessage()	);
		//
		//					moGenericMethodsLog.error(SQLServerException.getMessage());					
		//					Component.assertTrue(false, "XSD validation Failed for workFlow " + workFlow);
		//				}
		//			}
		//		}		
	}

	/*
	 * 57. Method to Check SourceID of ISOContent using Credit Item for given workFlow
	 */
	public static void checkSourceIDOfISOContentWithCreditItem(String workFlow) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "Select SourceID , ISOContent = CAST(ISOContent AS XML) from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "Where MessageType = '" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		tempISOContent = "";
		boolean tempFlag = false;
		while(resultSet.next())
		{
			tempISOContent = resultSet.getString("ISOContent");
			if(tempISOContent.indexOf(expectedCreditItemId.get(0)) > 0)
			{
				tempISOSourceID = resultSet.getString(columnSourceID);
				tempFlag = true;
				publishResults(true, "WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempISOSourceID,
						"Should see workflow "+ workFlow + " to load in source table",
						"Check workflow " + workFlow + " record insert in source table");
				moGenericMethodsLog.info("WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempISOSourceID);
			}
		}
		if(!tempFlag)
		{
			publishResults(true, "WorkFlow "+ workFlow + " not loaded in source table ",
					"Should see workflow "+ workFlow + " to load in source table",
					"Check workflow " + workFlow + " record insert in source table");
			moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in source table ");
		}
	}

	/*
	 * 58. Method to Check SourceID of ICNContent using Credit Item for given workFlow
	 */
	public static void checkSourceIDOfICNContentWithCreditItem(String workFlow) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "Select SourceID , ICNContent = CAST(ICNContent AS XML) from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "Where MessageType = '" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		boolean tempFlag = false;
		while(resultSet.next())
		{
			tempICNContent = resultSet.getString("ICNContent");
			if(tempICNContent.indexOf(expectedCreditItemId.get(0)) > 0)
			{
				tempFlag = true;
				tempICNSourceID=resultSet.getString(columnSourceID);
				publishResults(true, "WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID,
						"Should see workflow "+ workFlow + " to load in source table",
						"Check workflow " + workFlow + " record insert in source table");
				moGenericMethodsLog.info("WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID);
			}
		}
		if(!tempFlag)
		{
			publishResults(tempFlag, "WorkFlow "+ workFlow + " not loaded in source table ",
					"Should see workflow "+ workFlow + " to load in source table",
					"Check workflow " + workFlow + " record insert in source table");
			moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in source table ");
			Component.assertTrue(tempFlag, "WorkFlow "+ workFlow + " not loaded in source table");
		}
	}

	/*
	 * 59. Method to Check BeneficiaryItemState from ItemState table 
	 */	
	public static void checkBeneficiaryItemStateForItemId(String itemId, String workFlow, String expectedBeneficiaryItemStatus) throws Exception
	{
		actualBeneficiaryItemStatus = getBeneficiaryItemState(itemId);
		if(expectedBeneficiaryItemStatus.equals(actualBeneficiaryItemStatus))
		{
			moGenericMethodsLog.info("Actual BeneficiaryItemState value for itemId " + itemId + " is " + actualBeneficiaryItemStatus + " matched with expected");
			publishResults(true, actualBeneficiaryItemStatus, expectedBeneficiaryItemStatus, "Check BeneficiaryItemState value for itemId " + itemId);
		}else {
			moGenericMethodsLog.error("Actual BeneficiaryItemState value for itemId " + itemId + " is " + actualBeneficiaryItemStatus + " not matched with expected " + expectedBeneficiaryItemStatus);
			publishResults(false, actualBeneficiaryItemStatus, expectedBeneficiaryItemStatus, "Check BeneficiaryItemState value for itemId " + itemId);
		}		
	}

	/*
	 * 60. Method to check BeneficiaryTsetState from TransactionSetState table
	 */
	public static void checkBeneficiaryTsetState(String tsetId, String workFlow, String expectedBeneficiaryTsetStateStatus) throws Exception
	{
		actualBeneficiaryTsetState = getBeneficiaryTsetStateFromTransactionSetState(tsetId);
		if(expectedBeneficiaryTsetStateStatus.equals(actualBeneficiaryTsetState))
		{
			moGenericMethodsLog.info("Actual BeneficiaryTsetState value for tsetId " + tsetId + " is " + actualBeneficiaryTsetState + " matched with expected");
			publishResults(true, actualBeneficiaryTsetState, expectedBeneficiaryTsetStateStatus, "Check BeneficiaryTsetState value for tsetId " + tsetId);
		}else {
			moGenericMethodsLog.error("Actual BeneficiaryTsetState value for tsetId " + tsetId + " is " + actualBeneficiaryTsetState + " matched with expected" + expectedBeneficiaryTsetStateStatus);
			publishResults(false, actualBeneficiaryTsetState, expectedBeneficiaryTsetStateStatus, "Check BeneficiaryTsetState value for tsetId " + tsetId);
		}
	}	

	/*
	 * 61. Method to Check Items insert in Item Table
	 */	
	public static void checkItemsInsertinItemTable(List<String> itemIds, String workFlow) throws Exception
	{
		for(String eachItemId : itemIds)
		{
			String query = "Select ItemID from " + getMoRepositoryTable() + ".[Base].[Item] "
					+ "Where ItemID = '" + eachItemId + "' and InternalMessageType = '"+ workFlow + "'" ;
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			if(resultSet.next())
			{
				moGenericMethodsLog.info("ItemId with " +  eachItemId + " inserted in Item table for workflow "+workFlow);
				publishResults(true, "ItemId with " +  eachItemId + " inserted in Item table for workflow "+workFlow , "Should see ItemId with " + eachItemId + " insert in Item table" + workFlow , "Check ItemId "+eachItemId + " insert in Item table for workFlow "+ workFlow);
			}else
			{
				moGenericMethodsLog.error("ItemId with " +  eachItemId + " not inserted in Item table for workfow "+workFlow);
				publishResults(false, "ItemId with " +  eachItemId + " not inserted in Item table for workfow "+workFlow, "Should see ItemId with " + eachItemId + " insert in Item table  for workfow "+workFlow , "Check ItemId "+eachItemId + " insert in Item table for workFlow "+ workFlow);
			}
		}		
	}

	/*
	 * 62. Method to Check ItemId Insert in ItemState Table
	 */
	public static void checkItemIdInsertInItemStateTable(List<String> itemIds) throws Exception
	{
		for(String eachItemId : itemIds)
		{
			String query = "Select ItemID from " + getMoRepositoryTable() + ".[Base].[ItemState] "
					+ "Where ItemID = '" + eachItemId + "'" ;
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
			if(resultSet.next())
			{
				moGenericMethodsLog.info("ItemId with " +  eachItemId + " inserted in ItemState table");
				publishResults(true, "ItemId with " +  eachItemId + " inserted in ItemState table", "Should see ItemId with " + eachItemId + " insert in ItemState table" , "Check ItemId "+eachItemId + " insert in ItemState table" );
			}else
			{
				moGenericMethodsLog.error("ItemId with " +  eachItemId + " not inserted in ItemState table");	
				publishResults(false, "ItemId with " +  eachItemId + " not inserted in ItemState table", "Should see ItemId with " + eachItemId + " insert in ItemState table" , "Check ItemId "+eachItemId + " insert in ItemState table");
			}
		}		
	}

	/*
	 * 63. Method to Check Beneficiary State for Msg 13
	 */	
	public static void checkBeneficiaryStateForMsg13(String itemId, String state, String workFlow) throws Exception
	{
		String query = "Select BeneficiaryItemState from " + getMoRepositoryTable() + ".[Base].[ItemState] "
				+ "Where ItemID = '" + itemId + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			String actualItemState = resultSet.getString("BeneficiaryItemState");
			if(actualItemState.equals(actualItemState))
			{
				moGenericMethodsLog.info("BeneficiaryItemState " + actualItemState + " for itemId "+ itemId + " matches with expected for workFlow "+ workFlow );
				publishResults(true, actualItemState, actualItemState, "Check BeneficiaryItemState for itemId "+ itemId + " for workFlow "+ workFlow);
			}else {
				moGenericMethodsLog.error("BeneficiaryItemState " + actualItemState + " for itemId "+ itemId + " matches with expected for workFlow "+ workFlow );
				publishResults(false, actualItemState, actualItemState, "Check BeneficiaryItemState for itemId "+ itemId + " for workFlow "+ workFlow);
			}
		}
	}

	/*
	 * 64. Method to Check  SourceID of ICN Content with Extract ID
	 */
	public static void checkSourceIDOfICNContentWithExtractID(String workFlow, String extractId) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException
	{
		String query = "Select SourceID , ICNContent = CAST(ICNContent AS XML) from " + getMoRepositoryTable() + ".[Base].[Source] "
				+ "Where MessageType = '" + workFlow + "'" ;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		String tempICNContent = "";
		boolean tempFlag = false;
		while(resultSet.next())
		{
			tempICNContent = resultSet.getString("ICNContent");
			if(tempICNContent.indexOf(extractId) > 0)
			{
				tempFlag = true;
				tempICNSourceID=resultSet.getString(columnSourceID);
				publishResults(true, "WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID,
						"Should see workflow "+ workFlow + " to load in source table",
						"Check workflow " + workFlow + " record insert in source table");
				moGenericMethodsLog.info("WorkFlow "+ workFlow + " loaded in source table with sourceID "+ tempICNSourceID);
			}
		}
		if(!tempFlag)
		{
			publishResults(tempFlag, "WorkFlow "+ workFlow + " not loaded in source table ",
					"Should see workflow "+ workFlow + " to load in source table",
					"Check workflow " + workFlow + " record insert in source table");
			moGenericMethodsLog.error("WorkFlow "+ workFlow + " not loaded in source table ");
			Component.assertTrue(tempFlag, "WorkFlow "+ workFlow + " not loaded in source table");
		}
	}

	/*
	 * 65. Method to Update PERM with PTMR Extract-Id
	 */
	public static void updatePERMwithPTMRid(String procedureFilePath, String procedureFileName, String ptma01Value) throws IOException, InterruptedException
	{
		String strExpectedISOData = FileUtils.readFileToString(new File(procedureFilePath + procedureFileName));
		String tempString1 = strExpectedISOData.substring(strExpectedISOData.indexOf("<EntityType>D</EntityType>")+"<EntityType>D</EntityType>".length());
		String tempString2 = tempString1.substring(tempString1.indexOf("<EntityId>")+"<EntityId>".length() , tempString1.indexOf("</EntityId>") );
		tempString1 = tempString1.replaceFirst(tempString2, ptma01Value);
		strExpectedISOData = strExpectedISOData.substring(0, strExpectedISOData.indexOf("<EntityType>D</EntityType>") + "<EntityType>D</EntityType>".length()) + tempString1.trim();
		permInputFileXmlContent = strExpectedISOData;
		writeToFile(procedureFilePath+ procedureFileName, strExpectedISOData);	
		Thread.sleep(2000);
		String target = (procedureFilePath+ procedureFileName).replaceAll(".txt", ".sql");
		Thread.sleep(2000);
		File targetFileStream = new File(target);
		new File(procedureFilePath+ procedureFileName).renameTo(targetFileStream);
	}

	/*
	 * 66. Method to Validate Beneficiary State for given Item Id , workflow and given Expected State
	 */
	public static void validateBeneficiaryState(String itemId, String state, String workFlow) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{
		String query = "SELECT BeneficiaryItemState FROM [MORepository].[Base].[ItemState] where ItemID = '" + itemId + "'"	;
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			String actual = resultSet.getString("BeneficiaryItemState");
			if(actual.equals(state))
			{
				moGenericMethodsLog.info("Actual BeneficiaryItemState "+ actual + " matches with expected");
				publishResults(true, actual, state, "Check BeneficiaryItemState for workFlow "+ workFlow);
			}
		}
	}

	/*
	 * 67. Method to get PRRMICN Content Loaded in Source/Receive Staging table
	 */
	public static void getPRRMICNContent(String filePath , String fileName) throws IOException
	{
		prrmInputFileXmlContent = FileUtils.readFileToString(new File(filePath + fileName));
	}

	/*
	 * 68. Get SourceId for Required WorkFlow
	 */
	public static String getSourceIDForRequiredWorkFlow(String workFlow, String itemId) throws Exception
	{
		String tempSourceID = "";
		try{
			String query = "SELECT SourceID , ICNContent = CAST(ICNContent As xml) FROM " + getMoRepositoryTable() + ".[Base].[Source] "
					+ "WHERE MessageType = '"+ workFlow + "'";
			resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 

			while(resultSet.next())
			{
				String tempICNContent = resultSet.getString(columnICNContent);
				if(tempICNContent.indexOf(itemId) > 0)
				{
					tempSourceID = resultSet.getString(columnSourceID);		   
				}
			}
		}catch(Exception e)
		{

		}
		return tempSourceID;
	}

	/*
	 * 69. Check file Saved in EAV table location.
	 */
	public static void checkSavedFileInEAVLocation(String workFlow, List<String> itemIds, String validationHeader) throws Exception
	{
		if(workFlow.equals(workFlow07MS01))
		{
			msg07MS01ValuesForItems = new HashMap<String, String>();
			for(String eachItemId : itemIds)
			{
				msg07MS01ValuesForItems.put(eachItemId, "570");
			}
		}	
		String eavLocation = getEAVLocation(workFlow);
		String query = "SELECT FilenameMsgId FROM " +  getMoRepositoryTable() + ".[Base].[SourceTracker] "
				+ "where ActivityId = '" + activityId + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		String fileName = "";
		while(resultSet.next())
		{
			fileName = resultSet.getString("FilenameMsgId");
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
			publishResults(flag, "File name " + strFile + " for workflow " + workFlow + " saved in EAV Location " + eavLocation + " with proper format"  , 
					"Should see " + workFlow + " file saved in its EAV config location " + eavLocation, validationHeader);	
			moGenericMethodsLog.info("File name " + strFile + " for workflow " + workFlow + " saved in EAV Location " + eavLocation + " with proper format");
		}else {
			publishResults(flag, "File with "+ strFile + " not available in EAV Location " + eavLocation + " with proper format"  , 
					"Should see " + workFlow + " file saved in its EAV config location " + eavLocation, validationHeader);		
			moGenericMethodsLog.error("File with "+ strFile + " not available in EAV Location " + eavLocation + " with proper format" );
		}	
	}		

	/*
	 * 70. Method to get Beneficiary Item State for given ItemId
	 */
	public static String getBeneficiaryItemState(String itemId) throws Exception
	{		
		String query = "Select [BeneficiaryItemState] "
				+ "from " + getMoRepositoryTable() + ".[Base].[ItemState] "
				+ "where ItemID = '" + itemId + "'";	
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/*
	 * 71. Method to get BeneficiaryDirtyFlag from ItemState table for given ItemId
	 */
	public static String getBeneficiaryItemDirtyFlag(String itemId) throws Exception
	{		
		String query = "Select [BeneficiaryItemDirtyFlag] "
				+ "from " + getMoRepositoryTable() + ".[Base].[ItemState] "
				+ "where ItemID = '" + itemId + "'";	
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/*
	 * 72. Method to get BeneficiaryBatchedFlag from ItemState table for given ItemId
	 */
	public static String getBeneficiaryItemBatchedFlag(String itemId) throws Exception
	{
		String query = "Select [BeneficiaryItemBatchedFlag] "
				+ "from " + getMoRepositoryTable() + ".[Base].[ItemState] "
				+ "where ItemID = '" + itemId + "'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/*
	 * 73. Method to get BeneficiaryTsetState From TransactionSet table for given TsetId
	 */
	public static String getBeneficiaryTsetStateFromTransactionSetState(String transactionSetId) throws Exception
	{
		String query = "SELECT BeneficiaryTsetState FROM " + getMoRepositoryTable() + ".[Base].[TransactionSetState] "
				+ "where TsetID like '%" + transactionSetId + "%'";
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query); 
		return getICSRetrieveColumnValue(resultSet);
	}

	/*
	 * 74. Method to get created ScheduleId
	 */
	public static String getScheduleId()
	{
		return scheduleId;
	}

	public static String getColumnKeyAsXmlFileName()
	{
		return keyColumnDataAsXmlFileName;
	}

	/*
	 * 75. Method to set fileName for 06SM01
	 */
	public static void setMsg06SM01FileName(String fileName)
	{
		msg06SM01FileName = fileName;
	}

	/*
	 * 76. Method to get fileName for 04SM01
	 */
	public static String getMsg04SM01FileName()
	{
		return msg04SM01FileName;
	}

	/*
	 * 77. Method to set fileName for 04SM01
	 */
	public static void setMsg04SM01FileName(String fileName)
	{
		msg04SM01FileName = fileName;
	}

	/*
	 * 78. Method to get fileName for 13SM01
	 */
	public static String getMsg13SM01FileName()
	{
		return msg13SM01FileName;
	}

	/*
	 * 79. Method to set fileName for 13SM01
	 */
	public static void setMsg13SM01FileName(String fileName)
	{
		msg13SM01FileName = fileName;
	}

	/*
	 * 80. Method to get fileName for 09SM01
	 */
	public static String getMsg09SM01FileName()
	{
		return msg09SM01FileName;
	}

	/*
	 * 81. Method to set fileName for 09SM01
	 */
	public static void setMsg09SM01FileName(String fileName)
	{
		msg09SM01FileName = fileName;
	}

	/*
	 * 82. Method to get fileName for 05SM01
	 */
	public static String getMsg05SM01FileName()
	{
		return msg05SM01FileName;
	}

	/*
	 * 83. Method to set fileName for 05SM01
	 */
	public static void setMsg05SM01FileName(String fileName)
	{
		msg05SM01FileName = fileName;
	}

	/*
	 * 84. Method to get fileName for 05KM01
	 */
	public static String getMsg05KM01FileName()
	{
		return msg05KM01FileName;
	}

	/*
	 * 85. Method to set fileName for 05KM01
	 */
	public static void setMsg05KM01FileName(String fileName)
	{
		msg05KM01FileName = fileName;
	}

	/*
	 * 86. Method to get fileName for 06SM01
	 */
	public static String getMsg06SM01FileName()
	{
		return msg06SM01FileName;
	}

	/*
	 * 87. Method to set fileName for 12SM01
	 */
	public static void setMsg12SM01FileName(String fileName)
	{
		msg12SM01FileName = fileName;
	}

	/*
	 * 88. Method to get fileName for 12SM01
	 */
	public static String getMsg12SM01FileName()
	{
		return msg12SM01FileName;
	}

	/*
	 * 89. Method to set fileName for 11SM01
	 */
	public static void setMsg11SM01FileName(String fileName)
	{
		msg11SM01FileName = fileName;
	}

	/*
	 * 90. Method to get fileName for 11SM01
	 */
	public static String getMsg11SM01FileName()
	{
		return msg11SM01FileName;
	}

	/*
	 * 91. Method to set fileName for 06KM01
	 */
	public static void setMsg06KM01FileName(String fileName)
	{
		msg06KM01FileName = fileName;
	}

	/*
	 * 92. Method to get fileName for 06KM01
	 */
	public static String getMsg06KM01FileName()
	{
		return msg06KM01FileName;
	}

	/*
	 * 93. Method to set fileName for 08SM01
	 */
	public static void setMsg08SM01FileName(String fileName)
	{
		msg08SM01FileName = fileName;
	}

	/*
	 * 94. Method to get fileName for 08SM01
	 */
	public static String getMsg08SM01FileName()
	{
		return msg08SM01FileName;
	}

	/*
	 * 95. Method to get created RowId of ReceiveStaging table
	 */
	public static String getRowId()
	{
		return rowId;
	}

	/*
	 * 96. Method to get Payload created time
	 */
	public static String getPayLoadCreatedTime() throws Exception
	{
		String query = "Select * from " + getMoSchedulerTable() + ".[Scheduler].[JobExecutionLog] where ScheduleID = '" + scheduleId + "'";	
		resultSet = getICSDBServerConnection(getMoDbServerDetails(), getMoDbNameDetails(), query);
		while(resultSet.next())
		{
			payLoadCreatedTime = resultSet.getString("StartDateTime");
		}
		return payLoadCreatedTime;
	}

	/*
	 * 97. Method to get required MoRepository Instance
	 */
	public static String getMoRepositoryTable()
	{
		return "[MORepository" + getDBPrefix() + "]";
	}

	/*
	 * 98. Method to get required MoConfig Instance
	 */
	public static String getMoConfigTable()
	{
		return "[MOConfigDB" + getDBPrefix() + "]";
	}

	/*
	 * 99. Method to get required MoScheduler Instance
	 */
	public static String getMoSchedulerTable()
	{
		return "[MOScheduler" + getDBPrefix() + "]";
	}

	//	/*
	//	 * 97. Method to get required MoRepository Instance
	//	 */
	//	public static String getMoRepositoryTable()
	//	{
	//		return "[" + getDBPrefix() + "MORepository]";
	//	}
	//
	//	/*
	//	 * 98. Method to get required MoConfig Instance
	//	 */
	//	public static String getMoConfigTable()
	//	{
	//		return "[" + getDBPrefix() + "MOConfigDB]";
	//	}
	//
	//	/*
	//	 * 99. Method to get required MoScheduler Instance
	//	 */
	//	public static String getMoSchedulerTable()
	//	{
	//		return "[" + getDBPrefix() + "MOScheduler]";
	//	}
	/*
	 * 99.1 Method to set DB Instance to work
	 */
	public static void setDBPrefix(String db)
	{
		dbPrefix = db;
	}

	/*
	 * 99.2 Method to get DB Instance to work
	 */
	public static String getDBPrefix()
	{
		return dbPrefix;
	}

	/*
	 * 100.1 Method to get MoDbServerDetails
	 */
	public static String getMoDbServerDetails()
	{
		return moDbServer;
	}

	/*
	 * 100.2 Method to set MoDbNameDetails
	 */
	public static String getMoDbNameDetails()
	{
		return moDbName;
	}
}
