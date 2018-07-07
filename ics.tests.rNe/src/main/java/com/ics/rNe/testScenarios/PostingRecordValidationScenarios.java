package com.ics.rNe.testScenarios;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class PostingRecordValidationScenarios {
	
	private static boolean resultFlag ;
	
	public static boolean validateEntityType(String entityTypeTagActualValue){
		String TagExpectedPatternMatch = entityTypeTagActualValue;
		if(TagExpectedPatternMatch.equals("D") || TagExpectedPatternMatch.equals("T")|| TagExpectedPatternMatch.equals("I")|| TagExpectedPatternMatch.equals("B")|| TagExpectedPatternMatch.equals("E"))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
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
	
	public static boolean validateEntityId(String entityIdTagActualValue){
		String TagExpectedPatternMatch = entityIdTagActualValue;
		if(TagExpectedPatternMatch.length()<=28)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
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

public static boolean validatePostingExtractId(String postingExtractIdTagActualValue){
	String TagExpectedPatternMatch = postingExtractIdTagActualValue;
	if(TagExpectedPatternMatch.length()<=28)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}


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

public static boolean validateField(String fieldTagActualValue){
	String TagExpectedPatternMatch = fieldTagActualValue;
	if(TagExpectedPatternMatch.equals("ICSPST"))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateWeekday(String weekdayTagActualValue){
	String TagExpectedPatternMatch = weekdayTagActualValue;
	if(TagExpectedPatternMatch.length()<=2)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateFileType(String fileTypeTagActualValue){
	String TagExpectedPatternMatch = fileTypeTagActualValue;
	if(TagExpectedPatternMatch.equals("N"))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateCurrency(String currencyTagActualValue){
	String TagExpectedPatternMatch = currencyTagActualValue;
	if(TagExpectedPatternMatch.equals("GBP"))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}


public static boolean validateEnvironment(String environmentTagActualValue){
	String TagExpectedPatternMatch = environmentTagActualValue;
	if(TagExpectedPatternMatch.length()<=20)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}


public static boolean validateExtractStartDateTime(String extractStartDateTimeTagActualValue){
	
	try
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date tempDate=dateFormat.parse(extractStartDateTimeTagActualValue);
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

public static boolean validateExtractEndDateTime(String extractEndDateTimeTagActualValue){
	
	try
	{
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date tempDate=dateFormat.parse(extractEndDateTimeTagActualValue);
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

public static boolean validateExtractItem(String extractItemTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(extractItemTagActualValue);
	if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateItemId(String itemIdTagActualValue){
	String TagExpectedPatternMatch = itemIdTagActualValue;
	if(TagExpectedPatternMatch.length()<=25)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validatePostingAttempt(String postingAttemptTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(postingAttemptTagActualValue);
	if(tempDoubleValue >= 100 && tempDoubleValue <= 999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validatePostingSequence(String postingSequenceTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(postingSequenceTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validatePostingType(String postingTypeTagActualValue){
	String TagExpectedPatternMatch = postingTypeTagActualValue;
	if(TagExpectedPatternMatch.length()<=3)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validatePostingSubType(String postingSubTypeTagActualValue){
	String TagExpectedPatternMatch = postingSubTypeTagActualValue;
	if(TagExpectedPatternMatch.length()<=11)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateChannel(String channelTagActualValue){
	String TagExpectedPatternMatch = channelTagActualValue;
	if(TagExpectedPatternMatch.length()<=4)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validatePostingDrCrEntry(String postingDrCrEntryTagActualValue){
	String TagExpectedPatternMatch = postingDrCrEntryTagActualValue;
	if(TagExpectedPatternMatch.equals("Debit") || TagExpectedPatternMatch.equals("Credit"))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validatePostingSource(String postingSourceTagActualValue){
	String TagExpectedPatternMatch = postingSourceTagActualValue;
	if(TagExpectedPatternMatch.equals("Debit") || TagExpectedPatternMatch.equals("Credit"))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateResponseIdSource(String responseIdSourceTagActualValue){
	String TagExpectedPatternMatch = responseIdSourceTagActualValue;
	if(TagExpectedPatternMatch.equals("Debit") || TagExpectedPatternMatch.equals("Credit"))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validatePostingDay(String postingDayTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(postingDayTagActualValue);
	if(tempDoubleValue >= 1 && tempDoubleValue <= 9){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateClearingDate(String clearingDateTagActualValue)
{
	Date tempDateValue;
	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	try
	{
		tempDateValue = dateFormat.parse(clearingDateTagActualValue);
		System.out.println(dateFormat.format(tempDateValue));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(tempDateValue);
		
		String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));
		
		if(clearingDateTagActualValue.equalsIgnoreCase(strTotal))
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

public static boolean validateSettlementDate(String settlementDateTagActualValue)
{
	Date tempDateValue;
	SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
	try
	{
		tempDateValue = dateFormat.parse(settlementDateTagActualValue);
		System.out.println(dateFormat.format(tempDateValue));
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(tempDateValue);
		
		String strTotal = String.valueOf(cal.get(Calendar.YEAR) + "-" + String.format("%02d", (cal.get(Calendar.MONTH)+1)) + "-" + String.format("%02d", cal.get(Calendar.DATE)));
		
		if(settlementDateTagActualValue.equalsIgnoreCase(strTotal))
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

public static boolean validatePostedAmount(String postedAmountTagActualValue){
	String TagExpectedPatternMatch = postedAmountTagActualValue;
	if(TagExpectedPatternMatch.length()<=15)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validatePostingDecisionReasonCode(String postingDecisionRevisionCodeTagActualValue){
	String TagExpectedPatternMatch = postingDecisionRevisionCodeTagActualValue;
	if(TagExpectedPatternMatch.length()<=4)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateItemIdDR(String ItemIdDRTagActualValue){
	String TagExpectedPatternMatch = ItemIdDRTagActualValue;
	if(TagExpectedPatternMatch.length()<=25)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}


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

public static boolean validateReferenceDR(String referenceTagActualValue){
	String TagExpectedPatternMatch = referenceTagActualValue;
	if(TagExpectedPatternMatch.length()<=18)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateTranCodeDR(String tranCodeTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(tranCodeTagActualValue);
	if(tempDoubleValue >= 10 && tempDoubleValue <= 99){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateSwitchedSortCode(String switchedSortCodeTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(switchedSortCodeTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateSwitchedAccount(String switchedAccountTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(switchedAccountTagActualValue);
	if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateDebitFullAmount(String debitFullAmountTagActualValue){
	String TagExpectedPatternMatch = debitFullAmountTagActualValue;
	if(TagExpectedPatternMatch.length()<=15)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateItemIdCR(String itemIdCRTagActualValue){
	String TagExpectedPatternMatch = itemIdCRTagActualValue;
	if(TagExpectedPatternMatch.length()<=25)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateSortCodeCR(String sortCodeCRTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(sortCodeCRTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateAccountCR(String accountCRTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(accountCRTagActualValue);
	if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateReferenceCR(String referenceCRTagActualValue){
	String TagExpectedPatternMatch = referenceCRTagActualValue;
	if(TagExpectedPatternMatch.length()<=18)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateTranCodeCR(String tranCodeCRTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(tranCodeCRTagActualValue);
	if(tempDoubleValue >= 10 && tempDoubleValue <= 99){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateOriginalAmount(String originalAmountTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(originalAmountTagActualValue);
	if(tempDoubleValue >= 100000000 && tempDoubleValue <= 999999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

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

public static boolean validateOriginalAccount(String originalAccountTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(originalAccountTagActualValue);
	if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateCreditExceptionCode(String creditExceptionCodeTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(creditExceptionCodeTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateBeneficiaryName(String beneficiaryNameTagActualValue){
	String TagExpectedPatternMatch = beneficiaryNameTagActualValue;
	if(TagExpectedPatternMatch.length()<=18)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

}
