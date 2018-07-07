package com.ics.rNe.testScenarios;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class DebitDetailValidationScenarios {
	
	private static boolean resultFlag ;
	
	public static boolean validateDefSort(String defSortTagActualValue){
		String TagExpectedPatternMatch = defSortTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateDefAcc(String defAccTagActualValue){
		String TagExpectedPatternMatch = defAccTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateRepresent(String representTagActualValue){
		String TagExpectedPatternMatch = representTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateFrontQual(String frontQualTagActualValue){
		String TagExpectedPatternMatch = frontQualTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateRearQual(String rearQualTagActualValue){
		String TagExpectedPatternMatch = rearQualTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateFraudCode(String fraudCodeTagActualValue){
		String TagExpectedPatternMatch = fraudCodeTagActualValue;
		if(TagExpectedPatternMatch.equals("OK") || TagExpectedPatternMatch.equals("NotProcessed")|| TagExpectedPatternMatch.equals("Suspect")|| TagExpectedPatternMatch.equals("Fraudulent"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateFraudReason(String fraudReasonTagActualValue){
		String TagExpectedPatternMatch = fraudReasonTagActualValue;
		if(TagExpectedPatternMatch.length()==3)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateFraudName(String fraudNameTagActualValue){
		String TagExpectedPatternMatch = fraudNameTagActualValue;
		if(TagExpectedPatternMatch.equals("OK"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateDuplicate(String duplicateTagActualValue){
		String TagExpectedPatternMatch = duplicateTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateDupId(String dupIdTagActualValue){
		String TagExpectedPatternMatch = dupIdTagActualValue;
		if(TagExpectedPatternMatch.length()==25)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateDupStatus(String dupStatusTagActualValue){
		String TagExpectedPatternMatch = dupStatusTagActualValue;
		if(TagExpectedPatternMatch.equals("FULL") || TagExpectedPatternMatch.equals("PART"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateDupSeen(String dupSeenTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(dupSeenTagActualValue);
			System.out.println(dateFormat.format(tempDateValue));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);
			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));
			
			if(dupSeenTagActualValue.equalsIgnoreCase(strTotal))
				resultFlag = true;
			else
				resultFlag=false;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultFlag=false;
		}
		
		return resultFlag;		
	}
	
	public static boolean validateDupCollect(String dupCollectTagActualValue){
		String TagExpectedPatternMatch = dupCollectTagActualValue;
		if(TagExpectedPatternMatch.length()==6)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateDupCapture(String dupCaptureTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(dupCaptureTagActualValue);
			System.out.println(dateFormat.format(tempDateValue));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);
			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));
			
			if(dupCaptureTagActualValue.equalsIgnoreCase(strTotal))
				resultFlag = true;
			else
				resultFlag=false;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultFlag=false;
		}
		
		return resultFlag;		
	}
	
	public static boolean validateDupSource(String dupSourceTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(dupSourceTagActualValue);
		if(tempDoubleValue >= 1000 && tempDoubleValue <= 9999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateStopped(String stoppedTagActualValue){
		String TagExpectedPatternMatch = stoppedTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateStopDate(String stopDateTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(stopDateTagActualValue);
			System.out.println(dateFormat.format(tempDateValue));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);
			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));
			
			if(stopDateTagActualValue.equalsIgnoreCase(strTotal))
				resultFlag = true;
			else
				resultFlag=false;
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultFlag=false;
		}
		
		return resultFlag;		
	}
	
	public static boolean validateStopStatus(String stopStatusTagActualValue){
		String TagExpectedPatternMatch = stopStatusTagActualValue;
		if(TagExpectedPatternMatch.equals("FULL") || TagExpectedPatternMatch.equals("PARA")|| TagExpectedPatternMatch.equals("PARS"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateStopAmt(String stopAmtTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(stopAmtTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 999999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateStopName(String stopNameTagActualValue){
		String TagExpectedPatternMatch = stopNameTagActualValue;
		if(TagExpectedPatternMatch.length()==50)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateStopStart(String stopStartTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(stopStartTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateStopEnd(String stopEndTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(stopEndTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateRepSort(String repSortTagActualValue){
		String TagExpectedPatternMatch = repSortTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateRepAcc(String repAccTagActualValue){
		String TagExpectedPatternMatch = repAccTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateRepAmt(String repAmtTagActualValue){
		String TagExpectedPatternMatch = repAmtTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateRepSer(String repSerTagActualValue){
		String TagExpectedPatternMatch = repSerTagActualValue;
		if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateException(String exceptionTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(exceptionTagActualValue);
		if(tempDoubleValue >= 10 && tempDoubleValue <= 99){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}

}
