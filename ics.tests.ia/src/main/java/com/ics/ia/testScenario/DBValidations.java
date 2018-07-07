/*  
<Copyright file="DBValidations.java" company="iPSL">
Copyright © iPSL 2017 All rights are reserved.
Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
is prohibited without the prior written permission of the copyright owner.
</copyright> 
*/
 

/* ************************* Module Header ******************************
 * Module Name : Handles methods related to DB validations     
 * Date : 18/06/2017
 * Created By : Sandeep Bibinagar
 * Description : handling methods related to DB validations  
 */ 
/* ***************** AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 30/07/2017		
*********************************************************************** 
 Description : Updating file as more generic validations are added to the class
******************* AMENDMENT HISTORY *********************************** 
 Modified By : Sandeep Bibinagar	Date: 05/09/2017		
 Description : Updating class as per java coding standards
 ********************************************************************** */


package com.ics.ia.testScenario;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/********************************************************************************************************/
/* Purpose:	 This class has methods related to DataBase Validations for Image Archive 
 * @Author Sandeep Bibinagar                       */
/********************************************************************************************************/

public class DBValidations {
	
private static boolean resultFlag ;
	
	// Description: This method validates business date in the DB
	public static boolean validateBusinessDate(String businessDateTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(businessDateTagActualValue);
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));			
			if(businessDateTagActualValue.equalsIgnoreCase(strTotal))
				resultFlag = true;
			else
				resultFlag=false;			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return resultFlag;
	}
	
	// Description: This method validates cheque Issue date date in the DB
	public static boolean validateChequeIssuedDate(String businessDateTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(businessDateTagActualValue);
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));			
			if(businessDateTagActualValue.equalsIgnoreCase(strTotal))
				resultFlag = true;
			else
				resultFlag=false;			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return resultFlag;		
	}
	
	// Description: This method validates APG business date in the DB
	public static boolean validateAPGBusinessDate(String businessDateTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(businessDateTagActualValue);
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));			
			if(businessDateTagActualValue.equalsIgnoreCase(strTotal))
				resultFlag = true;
			else
				resultFlag=false;			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return resultFlag;
		}
	
	
	// Description: This method validates Extract ID in the DB
	public static boolean validateExtractId(String extractIdTagActualValue)
	{
		String TagExpectedPatternMatch = extractIdTagActualValue;
		if(TagExpectedPatternMatch.length()==26 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Open Case ID in the DB
	public static boolean validateOpenCaseId(String caseIdTagActualValue)
	{
		String TagExpectedPatternMatch = caseIdTagActualValue;
		if(TagExpectedPatternMatch.length()==25 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Processing PID in the DB
	public static boolean validateProcessingParticipantId(String processingParticipantIdTagActualValue)
	{
		String TagExpectedPatternMatch = processingParticipantIdTagActualValue;
		if(TagExpectedPatternMatch.length()==6 )
					{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	
	}
	
	// Description: This method validates Internal Message Type in the DB
	public static boolean validateIntMessageType(String intMessageTypeTagActualValue){
		String TagExpectedPatternMatch = intMessageTypeTagActualValue;
		if(TagExpectedPatternMatch.length()==6 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	
	}
	
	// Description: This method validates Account in the DB
	public static boolean validateAccount(String accountTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(accountTagActualValue);
		if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}

	// Description: This method validates External message type in the DB
	public static boolean validateExtMessageType(String extMessageTypeTagActualValue){
		String TagExpectedPatternMatch = extMessageTypeTagActualValue;
		if(TagExpectedPatternMatch.length()==5 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Message Source in the DB
	public static boolean validateMessageSource(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.equals("MO"))
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Source data type in the DB
	public static boolean validateMessageSourceDataType(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=5 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Message destination in the DB
	public static boolean validateMessageDestination(String messageDestinationTagActualValue){
		String TagExpectedPatternMatch = messageDestinationTagActualValue;
		if(TagExpectedPatternMatch.equals("IA"))
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates message destination data type in the DB
	public static boolean validateMessageDestinationDataType(String messageDestinationTagActualValue){
		String TagExpectedPatternMatch = messageDestinationTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=5 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateWorkTypeNbr(String workTypeNbrTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(workTypeNbrTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 99){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Sort code in the DB
	public static boolean validateSortFamily(String sortFamilyTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(sortFamilyTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Source ID  in the DB
	public static boolean validateSourceID(String sourceIDTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(sourceIDTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	
	// Description: This method validates Block Number in the DB
	public static boolean validateBlockNumber(String sourceIDTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(sourceIDTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 9999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Credit count in the DB
	public static boolean validateCreditCount(String sourceIDTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(sourceIDTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 99999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Debit Count in the DB
	public static boolean validateDebitCount(String sourceIDTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(sourceIDTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 99999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates record Count in the DB
	public static boolean validateRecordCounts(String recordCountsTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(recordCountsTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Return reason in the DB
	public static boolean validateReturnReason(String recordCountsTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(recordCountsTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 99){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates record count in the DB
	public static boolean validateCarSetId(String recordCountsTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(recordCountsTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates adjustment Reason in the DB
	public static boolean validateAdjustmentReason(String recordCountsTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(recordCountsTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 99){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates reject reason date in the DB
	public static boolean validateRejectReason(String recordCountsTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(recordCountsTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 9999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Transaction number in the DB
	public static boolean validateTransactionNumber(String recordCountsTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(recordCountsTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Sp Selector in the DB
	public static boolean validateSpSelector(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=4 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates original Isn in the DB
	public static boolean validateOriginalIsn(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=12)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Ae status in the DB
	public static boolean validateAeStatus(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=23)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Ia status in the DB
	public static boolean validateIaStatus(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=11)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Ia result in the DB
	public static boolean validateIaResult(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=11)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Ic status in the DB
	public static boolean validateIcStatus(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=23)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Iqv status in the DB
	public static boolean validateIqvStatus(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=14)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Item Count in the DB
	public static boolean validateItemCount(String itemCountsTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(itemCountsTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 9999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Error code in the DB
	public static boolean validateErrorCode(String errorTagActualValue)
	{
		String TagExpectedPatternMatch = errorTagActualValue;
		if(TagExpectedPatternMatch.length()==4 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates entity Type in the DB
	public static boolean validateEntityType(String entityTypeTagActualValue){

	String TagExpectedPatternMatch = entityTypeTagActualValue;
	if(TagExpectedPatternMatch.length()==1 )
	{
		resultFlag = true;
	}else {
		resultFlag = false;
	}		
	return resultFlag;
	}
	
	// Description: This method capture system id
	public static boolean validateCaptureSystemId(String captureSystemIdTagActualValue){
		String TagExpectedPatternMatch = captureSystemIdTagActualValue;
		if(TagExpectedPatternMatch.length()==1 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Orogonal sort code in the DB
	public static boolean validateOriginalSortCode(String originalSortCodeTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(originalSortCodeTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}	
	
	
	// Description: This method validates Source in the DB
	public static boolean validateSource(String sourceTagActualValue){
		String TagExpectedPatternMatch = sourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=4 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Case Type in the DB
	public static boolean validateCaseType(String caseTypeTagActualValue){
		String TagExpectedPatternMatch = caseTypeTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=10 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
		
	// Description: This method validates Account status in the DB
	public static boolean validateAccountDR(String accountTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(accountTagActualValue);
		if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Create date in the DB
	public static boolean validateCreatedDate(String createdDateTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(createdDateTagActualValue);
			System.out.println(dateFormat.format(tempDateValue));			
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));			
			if(createdDateTagActualValue.equalsIgnoreCase(strTotal))
				resultFlag = true;
			else
				resultFlag=false;			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return resultFlag;
	}
	
	// Description: This method validates APGDIN in the DB
	public static boolean validateAPGDIN(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=10)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates JG Account in the DB
	public static boolean validateJGAccount(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=11)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates BEn PID in the DB
	public static boolean validateBeneficiaryParticipantId(String errorTagActualValue)
	{
		String TagExpectedPatternMatch = errorTagActualValue;
		if(TagExpectedPatternMatch.length()==6 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates response type in the DB
	public static boolean validateResponseType(String reponseTypeTagActualvalue){
	String TagExpectedPatternMatch = reponseTypeTagActualvalue;
	if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=5 )
	{
		resultFlag = true;
	}else {
		resultFlag = false;
	}		
	return resultFlag;
	}
	
	// Description: This method validates Document id in the DB
	public static boolean validateDocumentId(String documentIdTagActualValue)
	{
		String TagExpectedPatternMatch = documentIdTagActualValue;
		if(TagExpectedPatternMatch.length()==6 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Sender ID in the DB
	public static boolean validateSenderId(String senderIdTagActualValue)
	{
		String TagExpectedPatternMatch = senderIdTagActualValue;
		if(TagExpectedPatternMatch.length()==6 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	
	// Description: This method validates charging PID in the DB
	public static boolean validateChargingParticipantId(String chargingParicipanttIdTagActualValue)
	{
		String TagExpectedPatternMatch = chargingParicipanttIdTagActualValue;
		if(TagExpectedPatternMatch.length()==6 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Fraud check result in the DB
	public static boolean validateFraudCheckResult(String fraudResultTagActualvalue){
		String TagExpectedPatternMatch = fraudResultTagActualvalue;
		if(TagExpectedPatternMatch.length()==15)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Fraud check reason in the DB
	public static boolean validateFraudCheckReaosn(String fraudReasonTagActualvalue){
	String TagExpectedPatternMatch = fraudReasonTagActualvalue;
	if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=4 )
	{
		resultFlag = true;
	}else {
		resultFlag = false;
	}		
	return resultFlag;
	}
	
	// Description: This method validates Entity type id in the DB
	public static boolean validateEntityTypeID(String entityTypeTagActualValue){
		String TagExpectedPatternMatch = entityTypeTagActualValue;
		if(TagExpectedPatternMatch.equals("D") || TagExpectedPatternMatch.equals("T")|| TagExpectedPatternMatch.equals("I")|| TagExpectedPatternMatch.equals("P")|| TagExpectedPatternMatch.equals("E"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates State revision in the DB
	public static boolean validateStateRevision(String stateRevisionTagActualValue)
	{	
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(stateRevisionTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	
	// Description: This method validates closed case ID in the DB
	public static boolean validateClosedCaseID(String caseIdTagActualValue){
		String TagExpectedPatternMatch = caseIdTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=25 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	
	// Description: This method validates case id in the DB
	public static boolean validateCaseID(String caseIDTagActualValue)
	{
		String TagExpectedPatternMatch = caseIDTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=14 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates entity state in the DB
	public static boolean validateEntityState(String entityStateTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(entityStateTagActualValue);
		if(tempDoubleValue >= 100 && tempDoubleValue <= 999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	
	// Description: This method validates entity ID in the DB
	public static boolean validateEntityId(String entityIdTagActualValue){
		String TagExpectedPatternMatch = entityIdTagActualValue;
		if(TagExpectedPatternMatch.length()<=28)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
		}
	
	// Description: This method validates Source date time in the DB
	public static boolean validateSourceDateTime(String sourceDateTimeTagActualValue){
		try
		{
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date tempDate=dateFormat.parse(sourceDateTimeTagActualValue);
			if(tempDate instanceof Date)
				resultFlag = true;
			else
				resultFlag = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultFlag = false;
		}
		return resultFlag;
	}	

	// Description: This method validates APG start time in the DB
	public static boolean validateAPGStartTime(String sourceDateTimeTagActualValue){
	try
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date tempDate=dateFormat.parse(sourceDateTimeTagActualValue);
		if(tempDate instanceof Date)
			resultFlag = true;
		else
			resultFlag = false;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		resultFlag = false;
	}	
	return resultFlag;
	}	

	// Description: This method validates APG end time in the DB
	public static boolean validateAPGEndTime(String sourceDateTimeTagActualValue){
	try
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date tempDate=dateFormat.parse(sourceDateTimeTagActualValue);
		if(tempDate instanceof Date)
			resultFlag = true;
		else
			resultFlag = false;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		resultFlag = false;
	}
	return resultFlag;
	}	
	
	// Description: This method validates Collection start time in the DB
	public static boolean validateCollectionStartTime(String sourceDateTimeTagActualValue){
	try
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date tempDate=dateFormat.parse(sourceDateTimeTagActualValue);
		if(tempDate instanceof Date)
			resultFlag = true;
		else
			resultFlag = false;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		resultFlag = false;
	}
	return resultFlag;
	}	

	// Description: This method validates Collection End time in the DB
	public static boolean validateCollectionEndTime(String sourceDateTimeTagActualValue){
	try
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date tempDate=dateFormat.parse(sourceDateTimeTagActualValue);
		if(tempDate instanceof Date)
			resultFlag = true;
		else
			resultFlag = false;
		}
	catch(Exception e)
	{
		e.printStackTrace();
		resultFlag = false;
	}
	return resultFlag;
	}	
	
	// Description: This method validates response window start time in the DB
	public static boolean validateResponseWindowStartDateTime(String startDateTimeTagActualValue){
	try
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date tempDate=dateFormat.parse(startDateTimeTagActualValue);
		if(tempDate instanceof Date)
			resultFlag = true;
		else
			resultFlag = false;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		resultFlag = false;
	}
	return resultFlag;
	}	

	// Description: This method validates response window end time in the DB
	public static boolean validateResponseWindowEndDateTime(String enedDateTimeTagActualValue){
	try
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date tempDate=dateFormat.parse(enedDateTimeTagActualValue);
		if(tempDate instanceof Date)
			resultFlag = true;
		else
			resultFlag = false;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		resultFlag = false;
	}
	return resultFlag;
	}	

	// Description: This method validates Process id in the DB
	public static boolean validateProcessIdDataType(String processIdTagActualValue){
	String TagExpectedPatternMatch = processIdTagActualValue;
	if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=25 )
	{
		resultFlag = true;
	}else {
		resultFlag = false;
	}		
	return resultFlag;
	}


	// Description: This method validates Error code in the DB
	public static boolean validateEntityErroCodeDataType(String entityerrorIdTagActualValue){
	String TagExpectedPatternMatch = entityerrorIdTagActualValue;
	if(TagExpectedPatternMatch.length()==26 )
	{
		resultFlag = true;
	}else {
		resultFlag = false;
	}		
	return resultFlag;
}

	// Description: This method validates Amount in the DB
	public static boolean validateAmount(String originalAmountTagActualValue)
	{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(originalAmountTagActualValue);
	if(tempDoubleValue <= 999999999 ){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

	// Description: This method validates Original amount in the DB
	public static boolean validateOriginalAmount(String originalAmountTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(originalAmountTagActualValue);
	if(tempDoubleValue <= 999999999 ){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
	}
	
	// Description: This method validates Entity Error description in the DB
	public static boolean validateEntityErroDesc(String entityerrordescTagActualValue){
	String TagExpectedPatternMatch = entityerrordescTagActualValue;
	if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=255 )
	{
		resultFlag = true;
	}else {
		resultFlag = false;
	}		
	return resultFlag;
	}

	// Description: This method validates Entity Error Location
	public static boolean validateEntityErrorLocation(String entityerrorlocationTagActualValue){
	String TagExpectedPatternMatch = entityerrorlocationTagActualValue;
	if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=1000 )
	{
		resultFlag = true;
	}else {
		resultFlag = false;
	}		
	return resultFlag;
	}
	
	// Description: This method validates Operator ID in the DB
	public static boolean validateOperatorIdDataType(String operatorIdTagActualValue){
		String TagExpectedPatternMatch = operatorIdTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=20 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Update Date time in the DB
	public static boolean validateUpdateDateTime(String updateDateTimeTagActualValue){
		
		try
		{
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date tempDate=dateFormat.parse(updateDateTimeTagActualValue);
			if(tempDate instanceof Date)
				resultFlag = true;
			else
				resultFlag = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultFlag = false;
		}
		return resultFlag;
	}	
	
	// Description: This method validates Audit revision in the DB
	public static boolean validateAuditRevision(String auditRevisionTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(auditRevisionTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Gender in the DB
	public static boolean validateGender(String genderTagActualValue){
		String TagExpectedPatternMatch = genderTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=3 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Posting extract in the DB
	public static boolean validatePostingExtractId(String postingExtractIdTagActualValue){
		String TagExpectedPatternMatch = postingExtractIdTagActualValue;
		if(TagExpectedPatternMatch.length()<=28)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Extract Sequence in the DB
	public static boolean validateExtractSequence(String extractSequenceTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(extractSequenceTagActualValue);
		if(tempDoubleValue >= 1000 && tempDoubleValue <= 9999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Extract revision in the DB
	public static boolean validateExtractRevision(String extractRevisionTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(extractRevisionTagActualValue);
		if(tempDoubleValue >= 100 && tempDoubleValue <= 999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Field in the DB
	public static boolean validateFileId(String fileIdTagActualValue){
		String TagExpectedPatternMatch = fileIdTagActualValue;
		if(TagExpectedPatternMatch.equals("ICSPST"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Weekday in the DB
	public static boolean validateWeekday(String weekdayTagActualValue){
		String TagExpectedPatternMatch = weekdayTagActualValue;
		if(TagExpectedPatternMatch.length()<=2)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates File Type in the DB
	public static boolean validateFileType(String fileTypeTagActualValue){
		String TagExpectedPatternMatch = fileTypeTagActualValue;
		if(TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates File Type in the DB
	public static boolean validateCurrency(String currencyTagActualValue){
		String TagExpectedPatternMatch = currencyTagActualValue;
		if(TagExpectedPatternMatch.equals("GBP"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	
	// Description: This method validates TransactionSetId in the DB
	public static boolean validateTransactionSetId(String transactionsetIdTagActualValue){
		//char f 24
		String TagExpectedPatternMatch = transactionsetIdTagActualValue;
		if(TagExpectedPatternMatch.length()==24)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	
	// Description: This method validates Credit Amount in the DB
	public static boolean validateCreditAmount(String originalAmountTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(originalAmountTagActualValue);
		if(tempDoubleValue <= 999999999 ){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Debit Amount in the DB
	public static boolean validateDebitAmount(String originalAmountTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(originalAmountTagActualValue);
		if(tempDoubleValue <= 999999999 ){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
		
	// Description: This method validates Installation ID in the DB
	public static boolean validateInstallationId(String installationIdTagActualValue){
		String TagExpectedPatternMatch = installationIdTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=15 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates PNVReviewStatus in the DB
	public static boolean validatePNVReviewStatus(String stateRevisionTagActualValue)
	{	
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(stateRevisionTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 9){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates DuplicateStatus in the DB
	public static boolean validateDuplicateStatus(String stateRevisionTagActualValue)
	{	
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(stateRevisionTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 9){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates Car results in the DB
	public static boolean validateCarResults(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=11)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates NumberOfEntries the DB
	public static boolean validateNumberOfEntries(String noOfEntriesTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(noOfEntriesTagActualValue);
		if(tempDoubleValue >= 100 && tempDoubleValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates TriggeringStatein the DB
	public static boolean validateTriggeringState(String triggeringStateTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(triggeringStateTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	
	// Description: This method validates Source Type in the DB
	public static boolean validateSourceType(String sourceTypeTagActualValue){
		String TagExpectedPatternMatch = sourceTypeTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=46 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Source Name in the DB
	public static boolean validateSourceName(String sourceNameTagActualValue){
		String TagExpectedPatternMatch = sourceNameTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=50 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Image Type in the DB
	public static boolean validateImageType(String sourceNameTagActualValue){
		String TagExpectedPatternMatch = sourceNameTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=7 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates FinancialInstitutionID in the DB
	public static boolean validateFinancialInstitutionID(String financialInstitutionIDActualValue){
		String TagExpectedPatternMatch = financialInstitutionIDActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=9 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates Item ID in the DB
	public static boolean validateItemId(String itemIdTagActualValue){
		//char f 25
		String TagExpectedPatternMatch = itemIdTagActualValue;
		if(TagExpectedPatternMatch.length()==25)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates PostingDecisionReasonCode in the DB
	public static boolean validatePostingDecisionReasonCode(String postingDecisionRevisionCodeTagActualValue){
		String TagExpectedPatternMatch = postingDecisionRevisionCodeTagActualValue;
		if(TagExpectedPatternMatch.length()==4)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates PostingOverrideFlag in the DB
	public static boolean validatePostingOverrideFlag(String postingOverrideFlagTagActualValue){
		String TagExpectedPatternMatch = postingOverrideFlagTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	// Description: This method validates NPA Sort code in the DB
	public static boolean validateNPASortCode(String nPASortCodeTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(nPASortCodeTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates SortCode DR in the DB
	public static boolean validateSortCodeDR(String sortCodeTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(sortCodeTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	// Description: This method validates NPA Account in the DB
	public static boolean validateNPAAccount(String nPAAccountTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(nPAAccountTagActualValue);
		if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}	
	

	// Description: This method validates Reason Code in the DB
		public static boolean validateReasonCode(String operatorIdTagActualValue){
			String TagExpectedPatternMatch = operatorIdTagActualValue;
			if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=6 )
			{
				resultFlag = true;
			}else {
				resultFlag = false;
			}		
			return resultFlag;
		}
	
	// Description: This method validates Reason Text in the DB
		public static boolean validateReasonText(String operatorIdTagActualValue){
			String TagExpectedPatternMatch = operatorIdTagActualValue;
			if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=40 )
			{
				resultFlag = true;
			}else {
				resultFlag = false;
			}		
			return resultFlag;
		}
		
		// Description: This method validates RicherDataRef in the DB
		public static boolean validateRicherDataRef(String operatorIdTagActualValue){
			String TagExpectedPatternMatch = operatorIdTagActualValue;
			if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=256 )
			{
				resultFlag = true;
			}else {
				resultFlag = false;
			}		
			return resultFlag;
		}
	
		
		// Description: This method validates State sequence in the DB
		public static boolean validateStateSequence(String recordCountsTagActualValue)
		{
			Double tempDoubleValue = 0d;
			tempDoubleValue = Double.parseDouble(recordCountsTagActualValue);
			if(tempDoubleValue >= 1 && tempDoubleValue <= 999){
				resultFlag = true;
			}else {
				resultFlag = false;
			}
			return resultFlag;
		}

		
		
		
		// Description: This method validates Credit Ref in the DB
				public static boolean validateCreditReference(String operatorIdTagActualValue){
					String TagExpectedPatternMatch = operatorIdTagActualValue;
					if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=18 )
					{
						resultFlag = true;
					}else {
						resultFlag = false;
					}		
					return resultFlag;
				}
		
		
				// Description: This method validates Serial in the DB
				public static boolean validateSerial(String operatorIdTagActualValue){
					String TagExpectedPatternMatch = operatorIdTagActualValue;
					if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=6 )
					{
						resultFlag = true;
					}else {
						resultFlag = false;
					}		
					return resultFlag;
				}
				
					
				// Description: This method validates Fraud Reason Code in the DB
				public static boolean validateFraudReasonCode(String operatorIdTagActualValue){
					String TagExpectedPatternMatch = operatorIdTagActualValue;
					if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=4 )
					{
						resultFlag = true;
					}else {
						resultFlag = false;
					}		
					return resultFlag;
				}
				
			
				// Description: This method validates FraudStatusCode in the DB
				public static boolean validateFraudStatusCode(String operatorIdTagActualValue){
					String TagExpectedPatternMatch = operatorIdTagActualValue;
					if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=4 )
					{
						resultFlag = true;
					}else {
						resultFlag = false;
					}		
					return resultFlag;
				}

				
		
}
