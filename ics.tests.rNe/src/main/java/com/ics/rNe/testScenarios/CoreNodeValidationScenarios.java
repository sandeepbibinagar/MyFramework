package com.ics.rNe.testScenarios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 * Comments: This class file has all the Core node attribute validations.
 *
 */
public class CoreNodeValidationScenarios {
	
	private static boolean resultFlag ;
	
	public static boolean validateBusinessDate(String businessDateTagActualValue)
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
	
	public static boolean validateExtractId(String extractIdTagActualValue)
	{
		String TagExpectedPatternMatch = extractIdTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=26 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateIntMessageType(String intMessageTypeTagActualValue){
		String TagExpectedPatternMatch = intMessageTypeTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("PTMR01"))
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
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(processingParticipantIdTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateExtMessageType(String extMessageTypeTagActualValue){
		String TagExpectedPatternMatch = extMessageTypeTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("PSTNG"))
		{
			resultFlag = true;
		}else {
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
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(recordCountsTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	

}
