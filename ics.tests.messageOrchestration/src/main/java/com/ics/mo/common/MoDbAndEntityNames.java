package com.ics.mo.common;

import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

/**
 * <h1>MoDbAndEntityNames</h1>
 * This class file to get MO DB Instances , Table and Column Names from mo.properties file 
 * <br>
 * <i>Copyright © iPSL 2017 All rights are reserved.
 * Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
 * is prohibited without the prior written permission of the copyright owner.</i>
 * @author MuluguUm
 * @version 1.0
 * @since 2017-06-23
 */
public class MoDbAndEntityNames extends GenericMethodUtilis
{
	public static final String moColumnProperties = "moDbColumns";
	public static final String moDbConnectProperties = "moDbConnect";
	public static final String moDbQueriesProperties = "moDbQueries";
	public static final String moEntityStatesProperties = "moEntityStates";
	public static final String moPostingStatesProperties = "moPostingStates";
	public static final String moLogDetailsProperties = "moLogDetails";
	public static final String moServerDetails = getMOdbConnect("moServerDetails");
	public static final String packageRunPath = getMOdbConnect("dtsxPath");
	public static final String repositorySchema = getMOdbColumnDetails("repositorySchema");
	public static final String configSchema = getMOdbColumnDetails("configSchema");
	public static final String schedulerSchema = getMOdbColumnDetails("schedulerSchema");
	public static final String eavTable = getMOdbColumnDetails("eavTable");
	public static final String entityColumn = getMOdbColumnDetails("entityColumn");
	public static final String attributeColumn = getMOdbColumnDetails("attributeColumn");
	public static final String valueColumn = getMOdbColumnDetails("valueColumn");
	public static final String category = getMOdbColumnDetails("category");
	public static final String collectingParticipantId = getMOdbColumnDetails("collectingParticipantId");
	public static final String chargingParticipant = getMOdbColumnDetails("chargingParticipant");
	public static final String controlM = getMOdbColumnDetails("controlM");
	public static final String businessDate = getMOdbColumnDetails("businessDate");
	public static final String businessDateP1 = getMOdbColumnDetails("businessDateP1");
	public static final String businessDateP2 = getMOdbColumnDetails("businessDateP2");
	public static final String businessDateM1 = getMOdbColumnDetails("businessDateM1");
	public static final String processingDate = getMOdbColumnDetails("processingDate");
	public static final String fileLocation = getMOdbColumnDetails("fileLocation");
	public static final String thresholdAmount = getMOdbColumnDetails("thresholdAmount");
	public static final String messageParametersTable = getMOdbColumnDetails("messageParametersTable");
	public static final String messageParametersSKIDColumn = getMOdbColumnDetails("messageParametersSKIDColumn");
	public static final String externalMessageIDColumn = getMOdbColumnDetails("externalMessageIDColumn");
	public static final String participantRoleColumn = getMOdbColumnDetails("participantRoleColumn");
	public static final String sourceSubSystemColumn = getMOdbColumnDetails("sourceSubSystemColumn");
	public static final String destinationSubSystemColumn = getMOdbColumnDetails("destinationSubSystemColumn");
	public static final String dirtyFlagConditionColumn = getMOdbColumnDetails("dirtyFlagConditionColumn");
	public static final String batchedFlagConditionColumn = getMOdbColumnDetails("batchedFlagConditionColumn");
	public static final String entityStateTransFromColumn = getMOdbColumnDetails("entityStateTransFromColumn");
	public static final String jobScheduleParamsTable = getMOdbColumnDetails("jobScheduleParamsTable");
	public static final String jobParamIdColumn = getMOdbColumnDetails("jobParamIdColumn");
	public static final String keyColumn = getMOdbColumnDetails("keyColumn");
	public static final String createdDateTimeColumn = getMOdbColumnDetails("createdDateTimeColumn");
	public static final String createdByUserColumn = getMOdbColumnDetails("createdByUserColumn");
	public static final String updatedDateTimeColumn = getMOdbColumnDetails("updatedDateTimeColumn");
	public static final String updatedByUserColumn = getMOdbColumnDetails("updatedByUserColumn");
	public static final String jobTemplateTable = getMOdbColumnDetails("jobTemplateTable");
	public static final String jobTemplateIdColumn = getMOdbColumnDetails("jobTemplateIdColumn");
	public static final String entityStateTable = getMOdbColumnDetails("entityStateTable");
	public static final String entityColumnIntradaySchedule = getMOdbColumnDetails("entityColumnIntradaySchedule");
	public static final String entityColumnScheduleWorkQueue = getMOdbColumnDetails("entityColumnScheduleWorkQueue");
	public static final String entityColumnJobExecutionLog = getMOdbColumnDetails("entityColumnJobExecutionLog");
	public static final String descriptionColumn = getMOdbColumnDetails("descriptionColumn");
	public static final String descriptionColumnCompletedbySupervisor = getMOdbColumnDetails("descriptionColumnCompletedbySupervisor");
	public static final String descriptionColumnPayLoadCreated = getMOdbColumnDetails("descriptionColumnPayLoadCreated");
	public static final String descriptionColumnReadByService = getMOdbColumnDetails("descriptionColumnReadByService");
	public static final String descriptionColumnCompletedBySupervisor = getMOdbColumnDetails("descriptionColumnReadByService");
	public static final String descriptionColumnCreatedByJobExecutor = getMOdbColumnDetails("descriptionColumnCreatedByJobExecutor");
	public static final String descriptionColumnCreatedByTaskBuilder = getMOdbColumnDetails("descriptionColumnCreatedByTaskBuilder");
	public static final String descriptionColumnProcessedByAgent = getMOdbColumnDetails("descriptionColumnProcessedByAgent");
	public static final String descriptionColumnSuccessByAgent = getMOdbColumnDetails("descriptionColumnSuccessByAgent");
	public static final String descriptionColumnFailureByAgent140 = getMOdbColumnDetails("descriptionColumnFailureByAgent140");
	public static final String descriptionColumnFailureByAgent142 = getMOdbColumnDetails("descriptionColumnFailureByAgent142");
	public static final String descriptionColumnFailureByAgent141 = getMOdbColumnDetails("descriptionColumnFailureByAgent141");
	public static final String descriptionColumnFailureByAgent143 = getMOdbColumnDetails("descriptionColumnFailureByAgent143");
	public static final String descriptionColumnFailureByAgent144 = getMOdbColumnDetails("descriptionColumnFailureByAgent144");
	public static final String descriptionColumnProcessedByJobWatcher = getMOdbColumnDetails("descriptionColumnProcessedByJobWatcher");
	public static final String descriptionColumnAdjustedByJobWatcher = getMOdbColumnDetails("descriptionColumnAdjustedByJobWatcher");
	public static final String descriptionColumnErrorInJobWatcher = getMOdbColumnDetails("descriptionColumnErrorInJobWatcher");
	public static final String descriptionColumnPickedUpLater = getMOdbColumnDetails("descriptionColumnPickedUpLater");
	public static final String descriptionColumnNoTaskAvailable = getMOdbColumnDetails("descriptionColumnNoTaskAvailable");
	public static final String currentValColumn = getMOdbColumnDetails("currentValColumn");
	public static final String intradayScheduleTable = getMOdbColumnDetails("intradayScheduleTable");
	public static final String scheduleIDColumn = getMOdbColumnDetails("scheduleIDColumn");
	public static final String statusColumn = getMOdbColumnDetails("statusColumn");
	public static final String plannedStartDateTimeColumn = getMOdbColumnDetails("plannedStartDateTimeColumn");
	public static final String additionalParamsColumn = getMOdbColumnDetails("additionalParamsColumn");
	public static final String workFlowNameColumn = getMOdbColumnDetails("workFlowNameColumn");
	public static final String jobExecutionLogTable = getMOdbColumnDetails("jobExecutionLogTable");
	public static final String activityIdColumn = getMOdbColumnDetails("activityIdColumn");
	public static final String payLoadColumn = getMOdbColumnDetails("payLoadColumn");
	public static final String scheduledWorkQueueTable = getMOdbColumnDetails("scheduledWorkQueueTable");
	public static final String workFlowTypeColumn = getMOdbColumnDetails("workFlowTypeColumn");
	public static final String stateColumn = getMOdbColumnDetails("stateColumn");
	public static final String exceptionLogTable = getMOdbColumnDetails("exceptionLogTable");
	public static final String sourceColumn = getMOdbColumnDetails("sourceColumn");
	public static final String messageColumn = getMOdbColumnDetails("messageColumn");
	public static final String stackTraceColumn = getMOdbColumnDetails("stackTraceColumn");
	public static final String applicationErrorCodeColumn = getMOdbColumnDetails("applicationErrorCodeColumn");
	public static final String errorSeverityColumn = getMOdbColumnDetails("errorSeverityColumn");
	public static final String receiveStagingTable = getMOdbColumnDetails("receiveStagingTable");
	public static final String msgIdColumn = getMOdbColumnDetails("msgIdColumn");
	public static final String msgSourceColumn = getMOdbColumnDetails("msgSourceColumn");
	public static final String msgDestinationColumn = getMOdbColumnDetails("msgDestinationColumn");
	public static final String messageTypeColumn = getMOdbColumnDetails("messageTypeColumn");
	public static final String msgColumn = getMOdbColumnDetails("msgColumn");
	public static final String msgColumnXMLColumn = getMOdbColumnDetails("msgColumnXMLColumn");
	public static final String queueColumn = getMOdbColumnDetails("queueColumn");
	public static final String businessDayColumn = getMOdbColumnDetails("businessDayColumn");
	public static final String sourceTable = getMOdbColumnDetails("sourceTable");
	public static final String sourceIDColumn = getMOdbColumnDetails("sourceIDColumn");
	public static final String isoContent = getMOdbColumnDetails("isoContent");
	public static final String icnContent = getMOdbColumnDetails("icnContent");
	public static final String isoContentXMLColumn = getMOdbColumnDetails("isoContentXMLColumn");
	public static final String icnContentXMLColumn = getMOdbColumnDetails("icnContentXMLColumn");
	public static final String sourceSKIDColumn = getMOdbColumnDetails("sourceSKIDColumn");
	public static final String sourceStateColumn = getMOdbColumnDetails("sourceStateColumn");
	public static final String sourceStateRevisionColumn = getMOdbColumnDetails("sourceStateRevisionColumn");
	public static final String sourceDirtyFlagColumn = getMOdbColumnDetails("sourceDirtyFlagColumn");
	public static final String sourceBatchedFlagColumn = getMOdbColumnDetails("sourceBatchedFlagColumn");
	public static final String sourceTrackerTable = getMOdbColumnDetails("sourceTrackerTable");
	public static final String idColumn = getMOdbColumnDetails("idColumn");
	public static final String sourceTrackerSKIDColumn = getMOdbColumnDetails("sourceTrackerSKIDColumn");
	public static final String fileNameMsgIdColumn = getMOdbColumnDetails("fileNameMsgIdColumn");
	public static final String transactionSetTable = getMOdbColumnDetails("transactionSetTable");
	public static final String tsetIDColumn = getMOdbColumnDetails("tsetIDColumn");
	public static final String tsetSKIDColumn = getMOdbColumnDetails("tsetSKIDColumn");
	public static final String stateDivisionColumn = getMOdbColumnDetails("stateDivisionColumn");
	public static final String internalMessageTypeColumn = getMOdbColumnDetails("internalMessageTypeColumn");
	public static final String transactionSetStateTable = getMOdbColumnDetails("transactionSetStateTable");
	public static final String payerRoleFlagColumn = getMOdbColumnDetails("payerRoleFlagColumn");
	public static final String payerTsetStateColumn = getMOdbColumnDetails("payerTsetStateColumn");
	public static final String payerTsetDirtyFlagColumn = getMOdbColumnDetails("payerTsetDirtyFlagColumn");
	public static final String payerTsetBatchedFlagColumn = getMOdbColumnDetails("payerTsetBatchedFlagColumn");
	public static final String beneficiaryRoleFlagColumn = getMOdbColumnDetails("beneficiaryRoleFlagColumn");
	public static final String beneficiaryTsetStateColumn = getMOdbColumnDetails("beneficiaryTsetStateColumn");
	public static final String collectorTsetStateColumn = getMOdbColumnDetails("collectorTsetStateColumn");
	public static final String beneficiaryTsetDirtyFlagColumn = getMOdbColumnDetails("beneficiaryTsetDirtyFlagColumn");
	public static final String beneficiaryTsetBatchedFlagColumn = getMOdbColumnDetails("beneficiaryTsetBatchedFlagColumn");
	public static final String tsetStateSKIDColumn = getMOdbColumnDetails("tsetStateSKIDColumn");
	public static final String window1BusinessDateColumn = getMOdbColumnDetails("window1BusinessDateColumn");
	public static final String window2BusinessDateColumn = getMOdbColumnDetails("window2BusinessDateColumn");
	public static final String itemTable = getMOdbColumnDetails("itemTable");
	public static final String itemIdColumn = getMOdbColumnDetails("itemIdColumn");
	public static final String itemSKIDColumn = getMOdbColumnDetails("itemSKIDColumn");
	public static final String itemStateTable = getMOdbColumnDetails("itemStateTable");
	public static final String payerItemStateColumn = getMOdbColumnDetails("payerItemStateColumn");
	public static final String beneficiaryItemStateColumn = getMOdbColumnDetails("beneficiaryItemStateColumn");
	public static final String collectorItemStateColumn = getMOdbColumnDetails("collectorItemStateColumn");
	public static final String payerItemDirtyFlagColumn = getMOdbColumnDetails("payerItemDirtyFlagColumn");
	public static final String payerItemBatchedFlagColumn = getMOdbColumnDetails("payerItemBatchedFlagColumn");
	public static final String beneficiaryItemDirtyFlagColumn = getMOdbColumnDetails("beneficiaryItemDirtyFlagColumn");
	public static final String beneficiaryItemBatchedFlagColumn = getMOdbColumnDetails("beneficiaryItemBatchedFlagColumn");
	public static final String postingStateColumn = getMOdbColumnDetails("postingStateColumn");
	public static final String genderColumn = getMOdbColumnDetails("genderColumn");
	public static final String postingEntryTable = getMOdbColumnDetails("postingEntryTable");
	public static final String postingEntrySKIDColumn = getMOdbColumnDetails("postingEntrySKIDColumn");
	public static final String postingEntryStateTable = getMOdbColumnDetails("postingEntryStateTable");
	public static final String postingEntryIDColumn = getMOdbColumnDetails("postingEntryIDColumn");
	public static final String sortCodeColumn = getMOdbColumnDetails("sortCodeColumn");
	public static final String accountNumberColumn = getMOdbColumnDetails("accountNumberColumn");
	public static final String dirtyFlagColumn = getMOdbColumnDetails("dirtyFlagColumn");
	public static final String batchedFlagColumn = getMOdbColumnDetails("batchedFlagColumn");
	public static final String fromTheICS = moEntityStates("fromTheICS");
	public static final String payerValidState = moEntityStates("payerValidState");
	public static final String payerInvalidState = moEntityStates("payerInvalidState");
	public static final String payableAfterFraud = moEntityStates("payableAfterFraud");
	public static final String notPayableAfterFraud = moEntityStates("notPayableAfterFraud");
	public static final String beforeClearing = moEntityStates("beforeClearing");
	public static final String validClearing = moEntityStates("validClearing");
	public static final String invalidClearing = moEntityStates("invalidClearing");   
	public static final String validFraud = moEntityStates("validFraud");
	public static final String invalidFraud = moEntityStates("invalidFraud");
	public static final String kappaOk =  moEntityStates("kappaOk");
	public static final String kappaNotProcessed = moEntityStates("kappaNotProcessed");
	public static final String kappaSuspect = moEntityStates("kappaSuspect");
	public static final String kappaFraudulent = moEntityStates("kappaFraudulent");
	public static final String jobParamCreation = moLogDetails("jobParamCreation");
	public static final String jobParamsException = moLogDetails("jobParamsException");
	public static final String jobTemplateCreation = moLogDetails("jobTemplateCreation");
	public static final String jobTemplateEachWorkFlow = moLogDetails("jobTemplateEachWorkFlow");
	public static final String jobTemplateEndTime = getMOdbConnect("jobTemplateEndTime");
	public static final String jobTemplatesException = moLogDetails("jobTemplatesException");
	public static final String dtsxPackageRun = moLogDetails("dtsxPackageRun");
	public static final String dtsxPath = getMOdbConnect("dtsxPath");
	public static final String dtsxSuccessRun = moLogDetails("dtsxSuccessRun");
	public static final String dtsxFailureRun = moLogDetails("dtsxFailureRun");
	public static final String sourceIdData = moLogDetails("sourceIdData");
	public static final String txsetIdsData = moLogDetails("txsetIdsData");
	public static final String creditIdsData = moLogDetails("creditIdsData");
	public static final String debitIdsData = moLogDetails("debitIdsData");	
	public static final String dayOneWindowData = moLogDetails("dayOneWindowData");
	public static final String dayTwoWindowData = moLogDetails("dayTwoWindowData");
	public static final String extractIdData = moLogDetails("extractIdData");
	public static final String paylodCreationWait = moLogDetails("paylodCreationWait");
	public static final String payloadCreated = moLogDetails("payloadCreated");
	public static final String payLoadNotCreated = moLogDetails("payLoadNotCreated");
	public static final String payLoadCreatedStatus = moLogDetails("payLoadCreatedStatus");
	public static final String createByTaskBuilder = moLogDetails("createByTaskBuilder");
	public static final String createdAgentProcessed = moLogDetails("createdAgentProcessed");
	public static final String failedByAgentHttpCode417Log = moLogDetails("failedByAgentHttpCode417Log");
	public static final String failedByAgentWorkQueue = moLogDetails("failedByAgentWorkQueue");
	public static final String completedBySupervisor = moEntityStates("completedBySupervisor");
	public static final String payLoadCreated = moEntityStates("payLoadCreated");
	public static final String createdByTaskBuilder = moEntityStates("createdByTaskBuilder");
	public static final String processedByAgent = moEntityStates("processedByAgent");
	public static final String successByAgent = moEntityStates("successByAgent");
	public static final String failureByAgent = moEntityStates("failureByAgent");
	public static final String failureByAgentWithHttp401 = moEntityStates("failureByAgentWithHttp401");
	public static final String failureByAgentWithHttp417 = moEntityStates("failureByAgentWithHttp417");
	public static final String failureByAgentWithHttp413 = moEntityStates("failureByAgentWithHttp413");
	public static final String failureByAgentWith5xxServerErr = moEntityStates("failureByAgentWith5xxServerErr");
	public static final String createdByTaskBuilderWorkQueue = moEntityStates("createdByTaskBuilderWorkQueue");
	public static final String processingByAgent = moEntityStates("processingByAgent");
	public static final String successByAgentWorkQueue = moEntityStates("successByAgentWorkQueue");
	public static final String failureByAgentWorkQueue = moEntityStates("failureByAgentWorkQueue");	
	public static final String withdrawnAfterClearing = moEntityStates("withdrawnAfterClearing");
	public static final String amendedAfterClearing = moEntityStates("amendedAfterClearing");
	public static final String withdrawnAfterFraud = moEntityStates("withdrawnAfterFraud");
	public static final String amendedAfterFraud = moEntityStates("amendedAfterFraud");
	public static final String successByAgentWorkQueueLog = moLogDetails("successByAgentWorkQueueLog");
	public static final String successByAgentLog = moLogDetails("successByAgentLog");
	public static final String exceptionLog = moLogDetails("exceptionLog");
	public static final String completedBySupervisorLog = moLogDetails("completedBySupervisorLog");
	public static final String supervisorPendingCompletion = moLogDetails("supervisorPendingCompletion");
	public static final String sourceTableLogActual = moLogDetails("sourceTableLogActual");
	public static final String sourceTableLogExpected = moLogDetails("sourceTableLogExpected");
	public static final String sourceTableLogError = moLogDetails("sourceTableLogError");
	public static final String sourceTableLogCheck = moLogDetails("sourceTableLogCheck");
	public static final String itemTableStateCheck = moLogDetails("itemTableStateCheck");
	public static final String itemStateTableStateCheck = moLogDetails("itemStateTableStateCheck");
	public static final String itemTableStateLogResult = moLogDetails("itemTableStateLogResult");
	public static final String itemStateTableStateLogResult = moLogDetails("itemStateTableStateLogResult");	
	public static final String transactionSetTableStateCheck = moLogDetails("transactionSetTableStateCheck");
	public static final String transactionSetStateTableStateCheck = moLogDetails("transactionSetStateTableStateCheck");
	public static final String transactionSetTableStateLogResult = moLogDetails("transactionSetTableStateLogResult");
	public static final String transactionSetStateTableStateLogResult = moLogDetails("transactionSetStateTableStateLogResult");
	public static final String isoContentValidation = moLogDetails("isoContentValidation");
	public static final String detailComparison = moLogDetails("detailComparison");
	public static final String dbtStopItemLogActual = moLogDetails("dbtStopItemLogActual");
	public static final String dbtStopItemLogExpected = moLogDetails("dbtStopItemLogExpected");
	public static final String dbtStopItemLogCheck = moLogDetails("dbtStopItemLogCheck");
	public static final String dbtStopItemLogError = moLogDetails("dbtStopItemLogError");
	public static final String dbtStopItemCheckHeader = moLogDetails("dbtStopItemCheckHeader");
	public static final String grpHdrCheckHeader = moLogDetails("grpHdrCheckHeader");
	public static final String detailComparisonTsets = moLogDetails("detailComparisonTsets");
	public static final String standardAmoutHeaderProcessed = moLogDetails("standardAmoutHeaderProcessed");
	public static final String standardAmoutHeaderError = moLogDetails("standardAmoutHeaderError");
	public static final String isoTagDetailsNotLoaded = moLogDetails("isoTagDetailsNotLoaded");
	public static final String icnAppendWithISOCheck = moLogDetails("icnAppendWithISOCheck");
	public static final String icnAppendWithISOActual = moLogDetails("icnAppendWithISOActual");
	public static final String icnAppendWithISOExpected = moLogDetails("icnAppendWithISOExpected");
	public static final String icnAppendWithISOError = moLogDetails("icnAppendWithISOError");
	public static final String collector = moLogDetails("collector");
	public static final String payer = moLogDetails("payer");
	public static final String beneficiary = moLogDetails("beneficiary");
	public static final String receiveStagingTableLogActual = moLogDetails("receiveStagingTableLogActual");
	public static final String receiveStagingTableLogExpected = moLogDetails("receiveStagingTableLogExpected");
	public static final String receiveStagingTableLogCheck = moLogDetails("receiveStagingTableLogCheck");
	public static final String receiveStagingTableLogError = moLogDetails("receiveStagingTableLogError");
	public static final String sourceTableFredValidateLog = moLogDetails("sourceTableFredValidateLog");
	public static final String validItemsLog = moLogDetails("validItemsLog");
	public static final String inValidItemsLog = moLogDetails("inValidItemsLog");
	public static final String validTsetsLog = moLogDetails("validTsetsLog");
	public static final String inValidTsetsLog = moLogDetails("inValidTsetsLog");
	public static final String icnDataInReceiveStageValidateLog = moLogDetails("icnDataInReceiveStageValidateLog");
	public static final String detailComparisonReceiveStageLog = moLogDetails("detailComparisonReceiveStageLog");
	public static final String checkOnCoreSectionLog = moLogDetails("checkOnCoreSectionLog");
	public static final String checkOnEntitiesSectionLog = moLogDetails("checkOnEntitiesSectionLog");
	public static final String checkOnItemsSectionLog = moLogDetails("checkOnItemsSectionLog");
	public static final String checkOnCaptureInfoSectionLog = moLogDetails("checkOnCaptureInfoSectionLog");
	public static final String codeLineChangedForItemIdLog = moLogDetails("codeLineChangedForItemIdLog");
	public static final String bankDetailsAfterAfterFMLog = moLogDetails("bankDetailsAfterAfterFMLog");
	public static final String msg04 = moLogDetails("msg04");
	public static final String msg05 = moLogDetails("msg05");
	public static final String msg06 = moLogDetails("msg06");
	public static final String debit = moLogDetails("debit");
	public static final String credit = moLogDetails("credit");
	
	/**
	 * This method is to get the value of required DbColumn 
	 * @param moKey
	 * @return
	 */
	public static String getMOdbColumnDetails(String moKey)
	{
		return getBundle(moColumnProperties).getString(moKey);
	}

	/**
	 * This method is to get the value of required DbConnect
	 * @param moKey
	 * @return
	 */
	public static String getMOdbConnect(String moKey)
	{
		return getBundle(moDbConnectProperties).getString(moKey);	
	}
	
	/**
	 * This method is to get the required MO Db Query
	 * @param moKey
	 * @return
	 */
	public static String getMOdbQueries(String moKey)
	{
		return getBundle(moDbQueriesProperties).getString(moKey);
	}

	/**
	 * This method is to get the required EntityStates
	 * @param moKey
	 * @return
	 */
	public static String moEntityStates(String moKey)
	{
		return getBundle(moEntityStatesProperties).getString(moKey);
	}

	/**
	 * This method is to get required PostingStates
	 * @param moKey
	 * @return
	 */
	public static String moPostingStates(String moKey)
	{
		return getBundle(moPostingStatesProperties).getString(moKey);
	}
	
	/**
	 * This method is to get required MO Log Details
	 * @param moKey
	 * @return
	 */
	public static String moLogDetails(String moKey)
	{
		return getBundle(moLogDetailsProperties).getString(moKey);
	}
}
