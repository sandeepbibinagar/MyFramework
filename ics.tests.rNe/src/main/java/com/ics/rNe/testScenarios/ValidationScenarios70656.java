package com.ics.rNe.testScenarios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidationScenarios70656 extends GenericMethodUtilis{
	
	private static boolean resultFlag ;
	
	
	
	public static boolean validateSchema(String schemaTagActualValue)
	{
		String TagExpectedPatternMatch = schemaTagActualValue;
		if(TagExpectedPatternMatch.length()<=10 )
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be of 10 Char length", "schema validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be of 10 char length", "schema validation");
		}		
		return resultFlag;
	}
	
	public static boolean validateParticipant(String participantTagActualValue){
		String TagExpectedPatternMatch = participantTagActualValue;
		if(TagExpectedPatternMatch.length()==6)
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be of 6 Char length", "Participant validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be of 6 Char length", "Participant validation");
		}		
		return resultFlag;
	}
	
	public static boolean validateProcDate(String procDateTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(procDateTagActualValue);
			System.out.println(dateFormat.format(tempDateValue));			
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));			
			if(procDateTagActualValue.equalsIgnoreCase(strTotal))
			{
				resultFlag = true;
			publishResults(resultFlag, procDateTagActualValue, "Should be Date Format", "procDate Validation");
			}
			else
			{
				resultFlag=false;
				publishResults(resultFlag, procDateTagActualValue, "Should be Date Format", "procDate Validation");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return resultFlag;
		
	}	
	
	public static boolean validateSequence(String extractSequenceTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(extractSequenceTagActualValue);
		if((tempDoubleValue >= 1000 && tempDoubleValue <= 9999) && tempDoubleValue != 0000){
			resultFlag = true;
			publishResults(resultFlag, tempDoubleValue.toString(), "4 digit int", "Sequence validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, tempDoubleValue.toString(), "4 digit int", "Sequence validation");
		}
		return resultFlag;
	}
	
	public static boolean validateVersion(String versionTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(versionTagActualValue);
		if((tempDoubleValue >= 10 && tempDoubleValue <= 99) && tempDoubleValue != 00){
			resultFlag = true;
			publishResults(resultFlag, tempDoubleValue.toString(), "2 digit int", "Sequence validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, tempDoubleValue.toString(), "2 digit int", "Sequence validation");
		}
		return resultFlag;
	}
	
	public static boolean validateFileDate(String fileDateTagActualValue){
		
		try
		{
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date tempDate=dateFormat.parse(fileDateTagActualValue);
			if(tempDate instanceof Date)
			{
				resultFlag = true;
				publishResults(resultFlag, tempDate.toString(), "Should be DateTime Format", "FileDate Validation");
			}
			else
			{
				resultFlag = false;
				publishResults(resultFlag, tempDate.toString(), "Should be DateTime Format", "FileDate Validation");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultFlag = false;
		}
		
		return resultFlag;
	}	
	
	public static boolean validateWeekday(String weekdayTagActualValue){
		String TagExpectedPatternMatch = weekdayTagActualValue;
		if(TagExpectedPatternMatch.length()<=2 &&( TagExpectedPatternMatch.equalsIgnoreCase("MO") || TagExpectedPatternMatch.equalsIgnoreCase("TU")|| TagExpectedPatternMatch.equalsIgnoreCase("WED")|| TagExpectedPatternMatch.equalsIgnoreCase("TH")|| TagExpectedPatternMatch.equalsIgnoreCase("FR")))
		{
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "2 char String", "Weekday validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "2 char String", "Weekday validation");
		}		
		return resultFlag;
	}
	
	public static boolean validateType(String TypeTagActualValue){
		String TagExpectedPatternMatch = TypeTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("N") || TagExpectedPatternMatch.equalsIgnoreCase("S")|| TagExpectedPatternMatch.equalsIgnoreCase("F"))
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "N|S|F", "Type validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "N|S|F" , "Type validation");
		}		
		return resultFlag;
	}
	
	public static boolean validateEnvironment(String environmentTagActualValue){
		String TagExpectedPatternMatch = environmentTagActualValue;
		String Pattern="([A-Z][0-9])";
		if((TagExpectedPatternMatch.equalsIgnoreCase("L")|| TagExpectedPatternMatch.equalsIgnoreCase("T")) && TagExpectedPatternMatch.matches(Pattern)){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "A-Z,0-1", "Environment validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "A-Z,0-1", "Environment validation");
		}		
		return resultFlag;
	}
	
	public static boolean validateSequenceRB(String SequenceTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(SequenceTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "Sequence validation");
			resultFlag = true;
		}else {
			resultFlag = false;
			publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "Sequence validation");
		}
		return resultFlag;
	}
	
	public static boolean validatePostType(String postTypeTagActualValue){
		String TagExpectedPatternMatch = postTypeTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("LDC"))
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "LDC", "PostType validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "LDC" , "PostType validation");
		}		
		return resultFlag;
	}
	
	public static boolean validateSubType(String subTypeTagActualValue){
		String TagExpectedPatternMatch = subTypeTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("OutClearing"))
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "OutClearing", "subType validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "OutClearing" , "subType validation");
		}		
		return resultFlag;
	}
	
	public static boolean validateSourceMsg(String sourceMsgTagActualValue){
		String TagExpectedPatternMatch = sourceMsgTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("MSG13"))
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "MSG13", "SourceMsg validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "MSG13" , "SourceMsg validation");
		}		
		return resultFlag;
	}
	
	public static boolean validateChannel(String channelTagActualValue){
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(channelTagActualValue);
		if((tempDoubleValue >= 1000 && tempDoubleValue <= 9999) && tempDoubleValue != 0000){
			resultFlag = true;
			publishResults(resultFlag, tempDoubleValue.toString(), " 4 digit int", "Channel validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, tempDoubleValue.toString(), " 4 digit int", "Channel validation");
		}
		return resultFlag;
	}
	
	public static boolean validateEntryType(String entryTypeTagActualValue){
		String TagExpectedPatternMatch = entryTypeTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("CREDIT"))
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "CREDIT", "EntryType validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "CREDIT" , "EntryType validation");
		}		
		return resultFlag;
	}
	
	public static boolean validatePostSrc(String postSourceTagActualValue){
		String TagExpectedPatternMatch = postSourceTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("CREDIT"))
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "CREDIT", "PostSource validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "CREDIT" , "PostSource validation");
		}		
		return resultFlag;
	}
	
	public static boolean validateResponseIDSource(String responseIDSourceTagActualValue){
		String TagExpectedPatternMatch = responseIDSourceTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("DEBIT"))
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "DEBIT", "ResponseIDSource validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "DEBIT" , "ResponseIDSource validation");
		}		
		return resultFlag;
	}
	
	public static boolean validatePostDay(String postDayTagActualValue){
		String TagExpectedPatternMatch = postDayTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("1"))
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "1", "PostDay validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "1" , "PostDay validation");
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
			{
				resultFlag = true;
			publishResults(resultFlag, clearDateTagActualValue, "Should be Date Format", "ClearDate Validation");
			}
			else
			{
				resultFlag=false;
				publishResults(resultFlag, clearDateTagActualValue, "Should be Date Format", "ClearDate Validation");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return resultFlag;
		
	}	
	
	public static boolean validateSettDate(String settDateTagActualValue)
	{
		Date tempDateValue;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try
		{
			tempDateValue = dateFormat.parse(settDateTagActualValue);
			System.out.println(dateFormat.format(tempDateValue));			
			Calendar cal = Calendar.getInstance();
			cal.setTime(tempDateValue);			
			String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));			
			if(settDateTagActualValue.equalsIgnoreCase(strTotal))
			{
				resultFlag = true;
			publishResults(resultFlag, settDateTagActualValue, "Should be Date Format", "SettDate Validation");
			}
			else
			{
				resultFlag=false;
				publishResults(resultFlag, settDateTagActualValue, "Should be Date Format", "SettDate Validation");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}		
		return resultFlag;
		
	}	
	
public static boolean validateCaptDate(String captDateTagActualValue){
		
		try
		{
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date tempDate=dateFormat.parse(captDateTagActualValue);
			if(tempDate instanceof Date)
			{
				resultFlag = true;
				publishResults(resultFlag, tempDate.toString(), "Should be DateTime Format", "CaptDate Validation");
			}
			else
			{
				resultFlag = false;
				publishResults(resultFlag, tempDate.toString(), "Should be DateTime Format", "CaptDate Validation");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			resultFlag = false;
		}
		
		return resultFlag;
	}

public static boolean validateAmount(String amountTagActualValue){
	String TagExpectedPatternMatch = amountTagActualValue;
	String pattern="([0-9]{1,15}+[.][0-9]{1,2})";
	if(TagExpectedPatternMatch.length()<=18 && TagExpectedPatternMatch.matches(pattern)){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "15,2 decimal", "Amount validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "15,2 decimal", "Amount validation");
	}		
	return resultFlag;
}

public static boolean validateTranSetID(String tranSetIDTagActualValue){
	String TagExpectedPatternMatch = tranSetIDTagActualValue;
	if(TagExpectedPatternMatch.length()==22){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "22 char", "TranSetID validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "22 char", "TranSetID validation");
	}		
	return resultFlag;
}

public static boolean validateReason(String reasonTagActualValue){
	String TagExpectedPatternMatch = reasonTagActualValue;
	if(TagExpectedPatternMatch.length()==6){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "6 char", "Reason validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "6 char", "Reason validation");
	}		
	return resultFlag;
}

public static boolean validateOverride(String overrideTagActualValue){
	String TagExpectedPatternMatch = overrideTagActualValue;
	if(TagExpectedPatternMatch.equals("N")){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "N", "Override validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "N", "Override validation");
	}		
	return resultFlag;
}

public static boolean validateNPASort(String nPASortTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(nPASortTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
		publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "NPASort validation");
	}else {
		resultFlag = false;
		publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "NPASort validation");
	}
	return resultFlag;
}

public static boolean validateCollPart(String collPartTagActualValue){
	String TagExpectedPatternMatch = collPartTagActualValue;
	if(TagExpectedPatternMatch.length()==6){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "6 char", "CollPart validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "6 char", "CollPart validation");
	}		
	return resultFlag;
}

public static boolean validateCollLocn(String collLocnTagActualValue){
	String TagExpectedPatternMatch = collLocnTagActualValue;
	if(TagExpectedPatternMatch.length()==10){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "10 char", "CollLocn validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "10 char", "CollLocn validation");
	}		
	return resultFlag;
}	
	
public static boolean validateDebitTransactionID(String DebitTransactionIDTagActualValue){
	String TagExpectedPatternMatch = DebitTransactionIDTagActualValue;
	if(TagExpectedPatternMatch.length()==25){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "25 char string", "DebitTransactionID validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "25 char string", "DebitTransactionID validation");
		
	}		
	return resultFlag;
}


public static boolean validateSortCodeDR(String sortCodeTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(sortCodeTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
		publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "SortCodeDR validation");
	}else {
		resultFlag = false;
		publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "SortCodeDR validation");
	}
	return resultFlag;
}

public static boolean validateAccNumDR(String accountNumTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(accountNumTagActualValue);
	if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
		resultFlag = true;
		publishResults(resultFlag, tempDoubleValue.toString(), "8 digit int", "AccNumDR validation");
	}else {
		resultFlag = false;
		publishResults(resultFlag, tempDoubleValue.toString(), "8 digit int", "AccNumDR validation");
	}
	return resultFlag;
}

public static boolean validateSerNumDR(String SerNumDRTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(SerNumDRTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
		publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "SerNumDR validation");
	}else {
		resultFlag = false;
		publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "SerNumDR validation");
	}
	return resultFlag;
}


public static boolean validateTranCodeDR(String tranCodeTagActualValue)
{
	String TagExpectedPatternMatch = tranCodeTagActualValue;
	if(TagExpectedPatternMatch.length()==2){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "2 char string", "TranCodeDR validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "2 char string", "TranCodeDR validation");
		
	}		
	return resultFlag;
}

public static boolean validateSwitchSort(String switchedSortCodeTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(switchedSortCodeTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
		publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "SwitchSort validation");
	}else {
		resultFlag = false;
		publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "SwitchSort validation");
	}
	return resultFlag;
}

public static boolean validateSwitchAcc(String switchAccTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(switchAccTagActualValue);
	if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
		resultFlag = true;
		publishResults(resultFlag, tempDoubleValue.toString(), "8 digit int", "SwitchAcc validation");
	}else {
		resultFlag = false;
		publishResults(resultFlag, tempDoubleValue.toString(), "8 digit int", "SwitchAcc validation");
	}
	return resultFlag;
}

public static boolean validateFullAmt(String debitFullAmountTagActualValue){
	String TagExpectedPatternMatch = debitFullAmountTagActualValue;
	String pattern="([0-9]{1,9}+[.][0-9]{1,2})";
	if(TagExpectedPatternMatch.length()<=12 && TagExpectedPatternMatch.matches(pattern)){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "9,2 decimal", "FullAmt validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "9,2 decimal", "FullAmt validation");
	}		
	return resultFlag;
}


public static boolean validateCreditIdCR(String CreditIdCRTagActualValue){
	String TagExpectedPatternMatch = CreditIdCRTagActualValue;
	if(TagExpectedPatternMatch.length()==25){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "25 char string", "CreditIdCR validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "25 char string", "CreditIdCR validation");
	}		
	return resultFlag;
}

public static boolean validateSortCodeCR(String sortCodeCRTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(sortCodeCRTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
		publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "SortCode validation");
	}else {
		resultFlag = false;
		publishResults(resultFlag, tempDoubleValue.toString(), "6 digit int", "SortCode validation");
	}
	return resultFlag;
}

public static boolean validateAccNumCR(String accountCRTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(accountCRTagActualValue);
	if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
		resultFlag = true;
		publishResults(resultFlag, tempDoubleValue.toString(), "8 digit int", "AccNum validation");
	}else {
		resultFlag = false;		
		publishResults(resultFlag, tempDoubleValue.toString(), "8 digit int", "AccNum validation");
	}
	return resultFlag;
}

public static boolean validateRefCR(String referenceCRTagActualValue){
	String TagExpectedPatternMatch = referenceCRTagActualValue;
	if(TagExpectedPatternMatch.length()==18){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "18 char string", "RefCR validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "18 char string", "RefCR validation");
	}		
	return resultFlag;
}

public static boolean validateTranCodeCR(String tranCodeCRTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(tranCodeCRTagActualValue);
	if(tempDoubleValue >= 10 && tempDoubleValue <= 99){
		resultFlag = true;
		publishResults(resultFlag, tempDoubleValue.toString(), "2 digit int", "TranCode validation");
	}else {
		resultFlag = false;
		publishResults(resultFlag, tempDoubleValue.toString(), "2 digit int", "TranCode validation");
	}
	return resultFlag;
}

public static boolean validateOrigAmt(String originalAmountTagActualValue)
{
	String TagExpectedPatternMatch = originalAmountTagActualValue;
	String pattern="([0-9]{1,9}+[.][0-9]{1,2})";
	if(TagExpectedPatternMatch.length()<=12 && TagExpectedPatternMatch.matches(pattern)){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "9,2 decimal", "FullAmt validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "9,2 decimal", "FullAmt validation");
	}		
	return resultFlag;
}

public static boolean validateException(String creditExceptionCodeTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(creditExceptionCodeTagActualValue);
	if(tempDoubleValue >= 100 && tempDoubleValue <= 999){
		resultFlag = true;
		publishResults(resultFlag, tempDoubleValue.toString(), "3 digit int", "Exception validation");
	}else {
		resultFlag = false;
		publishResults(resultFlag, tempDoubleValue.toString(), "3 digit int", "Exception validation");
	}
	return resultFlag;
}

public static boolean validateBeneficiary(String beneficiaryNameTagActualValue){
	String TagExpectedPatternMatch = beneficiaryNameTagActualValue;
	if(TagExpectedPatternMatch.length()<=50){
	resultFlag = true;
	publishResults(resultFlag, TagExpectedPatternMatch, "upto 50 char string", "Beneficiary validation");
	}
	else {
		resultFlag = false;
		publishResults(resultFlag, TagExpectedPatternMatch, "upto 50 char string", "Beneficiary validation");
	}		
	return resultFlag;
}


	
}
