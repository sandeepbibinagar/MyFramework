package com.ics.rNe.testScenarios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */

public class PostingHeaderValidationsScenarios {
	
	private static boolean resultFlag ;
	
	public static boolean validateSequence(String sequenceTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(sequenceTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validatePostType(String postTypeTagActualValue){
		String TagExpectedPatternMatch = postTypeTagActualValue;
		if(TagExpectedPatternMatch.length()==3 && TagExpectedPatternMatch.equals("DEB"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateSubType(String subTypeTagActualValue){
		String TagExpectedPatternMatch = subTypeTagActualValue;
		if(TagExpectedPatternMatch.equals("InClearing"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateSourceMsg(String sourceMsgTagActualValue){
		String TagExpectedPatternMatch = sourceMsgTagActualValue;
		if(TagExpectedPatternMatch.equals("MSG06"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateChannel(String channelTagActualValue){
		String TagExpectedPatternMatch = channelTagActualValue;
		if(TagExpectedPatternMatch.equals("9000"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateEntryType(String entryTypeTagActualValue){
		String TagExpectedPatternMatch = entryTypeTagActualValue;
		if(TagExpectedPatternMatch.equals("Debit"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validatePostingSource(String postingSourceTagActualValue){
		String TagExpectedPatternMatch = postingSourceTagActualValue;
		if(TagExpectedPatternMatch.equals("Debit"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateRespIDSource(String respIDSourceSourceTagActualValue){
		String TagExpectedPatternMatch = respIDSourceSourceTagActualValue;
		if(TagExpectedPatternMatch.equals("Debit"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validatePostDay(String postDayTagActualValue){
		String TagExpectedPatternMatch = postDayTagActualValue;
		if(TagExpectedPatternMatch.equals("ES:520=2") || TagExpectedPatternMatch.equals("ES:540/2=1")||TagExpectedPatternMatch.equals("ES:541/3=1")||TagExpectedPatternMatch.equals("ES:550/1=1"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateClearDate(String clearDateTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(clearDateTagActualValue);
			System.out.println(dateFormat.format(tempDateValue));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);
			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));
			
			if(clearDateTagActualValue.equalsIgnoreCase(strTotal))
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
	
	public static boolean validateSettDateType(String settDateTypeTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(settDateTypeTagActualValue);
			System.out.println(dateFormat.format(tempDateValue));
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);
			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));
			
			if(settDateTypeTagActualValue.equalsIgnoreCase(strTotal))
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
	
public static boolean validateCaptDateType(String captDateTypeTagActualValue){
		
		try
		{
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date tempDate=dateFormat.parse(captDateTypeTagActualValue);
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

public static boolean validateAmount(String amountTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(amountTagActualValue);
	if(tempDoubleValue >=1 && tempDoubleValue <= 999999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}


public static boolean validateTranSetIDType(String tranSetIDTypeTagActualValue){
	String TagExpectedPatternMatch = tranSetIDTypeTagActualValue;
	if(TagExpectedPatternMatch.length()<=22)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

	

public static boolean validateReason(String reasonTagActualValue){
	String TagExpectedPatternMatch = reasonTagActualValue;
	if(TagExpectedPatternMatch.length()<=4)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}
	
public static boolean validateOverride(String overrideTagActualValue){
	String TagExpectedPatternMatch = overrideTagActualValue;
	if(TagExpectedPatternMatch.equals("ES:520=N") || TagExpectedPatternMatch.equals("ES:540/2=N")||TagExpectedPatternMatch.equals("ES:541/3=Y")||TagExpectedPatternMatch.equals("ES:550/1=Y"))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateCollPart(String collPartTagActualValue){
	String TagExpectedPatternMatch = collPartTagActualValue;
	if(TagExpectedPatternMatch.length()<=6)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateCollLocn(String collLocnTagActualValue){
	String TagExpectedPatternMatch = collLocnTagActualValue;
	if(TagExpectedPatternMatch.length()<=10)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}










public static boolean validateDebitId(String DebitIdTagActualValue){
	String TagExpectedPatternMatch = DebitIdTagActualValue;
	if(TagExpectedPatternMatch.length()<=10)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateSortCode(String sortCodeTagActualValue)
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

public static boolean validateAccNum(String sortCodeTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(sortCodeTagActualValue);
	if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateSerNum(String serNumTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(serNumTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateTranCode(String tranCodeTagActualValue){
	String TagExpectedPatternMatch = tranCodeTagActualValue;
	if(TagExpectedPatternMatch.length()<=2)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateSwitchSort(String switchSortTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(switchSortTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateSwitchAcc(String switchAccTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(switchAccTagActualValue);
	if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateTranCount(String tranCountTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(tranCountTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

}
