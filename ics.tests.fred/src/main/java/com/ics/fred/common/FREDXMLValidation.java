package com.ics.fred.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FREDXMLValidation {
	
	private static boolean resultFlag ;
		
	public static boolean validateBusinessDateWithFile(String businessDateTagActualValue)
	{
		Date tempDateValue;
		String strDatevalue = null;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyyMMdd");
		try
		{
			tempDateValue = dateFormat.parse(businessDateTagActualValue);
			System.out.println(dateFormat.format(tempDateValue));
			 strDatevalue = String.valueOf(tempDateValue);
				if (strDatevalue!=null)
					resultFlag = true;
				else 
					resultFlag = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return resultFlag;
		
	}	
	
	public static boolean validateBusinessDateDatabaseFormat(String businessDateTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(businessDateTagActualValue);
			System.out.println(dateFormat.format(tempDateValue));			
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));
			
			System.out.println("Print buinessdate with format:"+strTotal);
			if(businessDateTagActualValue.equals(strTotal))
				resultFlag = true;
			else
				resultFlag= false;			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return resultFlag;
		
	}
	
	public static boolean validateExtractId(String extractIdTagActualValue)
	{
		String tagExpectedPatternMatch = extractIdTagActualValue;
		if(tagExpectedPatternMatch.length()==26 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateExtractIdWithIDS(String extractIdTagActualValue,int extractIDLen)
	{
		String tagExpectedPatternMatch = extractIdTagActualValue;
		if(tagExpectedPatternMatch.length()==extractIDLen)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateIntMessageType(String intMessageTypeTagActualValue){
		String TagExpectedPatternMatch = intMessageTypeTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("06FM01"))
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateSequence(String sequenceTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(sequenceTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validatePostingExtractId(String postingExtractIdTagActualValue){
		String TagExpectedPatternMatch = postingExtractIdTagActualValue;
		if(TagExpectedPatternMatch.length()==26)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateProcessingParticipantId(String processingParticipantIdTagActualValue)
	{
		int tempDoubleValue = 0;
		tempDoubleValue = Integer.parseInt(processingParticipantIdTagActualValue);
		if(processingParticipantIdTagActualValue.length()==6)
		{
			if(tempDoubleValue >= 1 && tempDoubleValue <= 999999)
			resultFlag = true;
			else 
			resultFlag = false;
		}
			else
			{
				System.out.println("processingParticipantId lenght is not as per IDS Matrix");
			}
			
		return resultFlag;
	}
	
	public static boolean validateEntityTypeLength(String processingParticipantIdTagActualValue)
	{
		int tempDoubleValue = 0;
		tempDoubleValue = Integer.parseInt(processingParticipantIdTagActualValue);
		if(processingParticipantIdTagActualValue.length()==1)
		{

			resultFlag = true;

			
		}
			else
			{
				resultFlag = false;
				System.out.println("processingParticipantId lenght is not as per IDS Matrix");
			}
			
		return resultFlag;
	}
	
	
	
	public static boolean validateExtMessageType(String extMessageTypeTagActualValue){
		
		String tagExpectedPatternMatch = extMessageTypeTagActualValue;
		if(tagExpectedPatternMatch.length()==5)
		if(tagExpectedPatternMatch.equalsIgnoreCase("MSG06") && tagExpectedPatternMatch.equalsIgnoreCase("MSG05") && tagExpectedPatternMatch.equalsIgnoreCase("MSG01"))
		{
			resultFlag = true;
		}
		else 
		{
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateMessageSource(String messageSourceTagActualValue){
		String TagExpectedPatternMatch = messageSourceTagActualValue;
		if(TagExpectedPatternMatch.length()<=5)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static Integer validateMessageSourceLength(String messageSourceTagActualValue){
		int msgSrcLength = messageSourceTagActualValue.length();
		System.out.println("MessageSource Length count:"+msgSrcLength);	
		if(msgSrcLength<=5){
			
		}
		return msgSrcLength;
	}
	
	public static boolean validateMessageDestination(String messageDestinationTagActualValue){
		String TagExpectedPatternMatch = messageDestinationTagActualValue;
		if(TagExpectedPatternMatch.length()<=5)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateRecordCounts(String recordCountsTagActualValue)
	{
		int tenpIntValue = 0;
		tenpIntValue = Integer.parseInt(recordCountsTagActualValue);
		if(tenpIntValue >= 1 && tenpIntValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateRecordCountsLength(String recordCountsTagActualValue)
	{
		int tenpIntValue = 0;
		tenpIntValue = Integer.parseInt(recordCountsTagActualValue);
		int count =recordCountsTagActualValue.length();
		System.out.println("RecordCounts Lenth :"+count);
		if(tenpIntValue >= 1 && tenpIntValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	
	public static boolean validateRecordCounts_DoubleDataType(String recordCountsTagActualValue)
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
	
	public static boolean validateInstallationId(String installationId)
	{
		String  expectedTagInstallationId = installationId;
		if(expectedTagInstallationId.length()<=15){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateCaptureSystemId(String captureSystemId)
	{
		String  expectedTagCaptureSystemId = captureSystemId;
		if(expectedTagCaptureSystemId.length()==1){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateStartTime(String captureSystemId)
	{
		String  expectedTagCaptureSystemId = captureSystemId;
		if(expectedTagCaptureSystemId.length()==1){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	//ItemID length vlidation
	public static boolean validateItemIdLengthValidation(String itemIdActualVal)
	{
		String tagExpectedPatternMatch = itemIdActualVal;
		if(tagExpectedPatternMatch.length()==25)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateItemIdLengthValidationWithIDS(String itemIdActualVal,String len)
	{
		String tempVal =itemIdActualVal;
		String tempLen =len;
		if(tempVal.length()==tempLen.length())
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	//SortCod
	public static boolean validateSortCodeWithIDS(String sorcodeTagActualValue,String scLen)
	{
		String tagExpectedPatternMatch = sorcodeTagActualValue;
		if(tagExpectedPatternMatch.length()==scLen.length())
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateSerialWithIDS(String serailTagActualValue,String serLen)
	{
		String tagExpectedPatternMatch = serailTagActualValue;
		if(tagExpectedPatternMatch.length()==serLen.length())
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	
	public static boolean validateAmountWithIDS(String amountTagActualValue,String serLen)
	{
		String tagExpectedPatternMatch = amountTagActualValue;
		if(tagExpectedPatternMatch.length()==serLen.length())
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateAccountWithIDS(String accountTagActualValue,String len)
	{
		String tagExpectedPatternMatch = accountTagActualValue;
		if(tagExpectedPatternMatch.length()==len.length())
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateReferenceWithIDS(String refTagActualValue,String refLen)
	{
		String tagExpectedPatternMatch = refTagActualValue;
		if(tagExpectedPatternMatch.length()==refLen.length())
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateCurrencyWithIDS(String currencyTagActualValue,String refLen)
	{
		String tagExpectedPatternMatch = currencyTagActualValue;
		if(tagExpectedPatternMatch.length()==refLen.length())
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateTrancodeWithIDS(String currencyTagActualValue,String refLen)
	{
		String tagExpectedPatternMatch = currencyTagActualValue;
		if(tagExpectedPatternMatch.length()==refLen.length())
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateInstallationIdWithIDS(String currencyTagActualValue,String refLen)
	{
		String tagExpectedPatternMatch = currencyTagActualValue;
		if(tagExpectedPatternMatch.length()==refLen.length())
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateEntityStateWithIDS(String extractIdTagActualValue,int extractIDLen)
	{
		String tagExpectedPatternMatch = extractIdTagActualValue;
		if(tagExpectedPatternMatch.length()<=extractIDLen)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	//BatchDebitAmount
	public static boolean validateBatchDebitAmtWithIDS(String extractIdTagActualValue,int extractIDLen)
	{
		String tagExpectedPatternMatch = extractIdTagActualValue;
		if(tagExpectedPatternMatch.length()<=extractIDLen)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateJobCaptureSystemIdWithIDS(String extractIdTagActualValue,int extractIDLen)
	{
		String tagExpectedPatternMatch = extractIdTagActualValue;
		if(tagExpectedPatternMatch.length()<=extractIDLen)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
}

//