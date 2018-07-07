package com.ics.rNe.testScenarios;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class PostingExtractFileValidationScenarios {
	
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
		if(TagExpectedPatternMatch.equalsIgnoreCase("PERM01"))
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
		if(TagExpectedPatternMatch.equals("RnE"))
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateMessageDestination(String messageDestinationTagActualValue){
		String TagExpectedPatternMatch = messageDestinationTagActualValue;
		if(TagExpectedPatternMatch.equals("MO"))
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

public static boolean validateFileId(String fileIdTagActualValue){
	String TagExpectedPatternMatch = fileIdTagActualValue;
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
	String Pattern="([A-Z][0-9])";
	if(TagExpectedPatternMatch.length()<=20 && TagExpectedPatternMatch.matches(Pattern))
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

public static boolean validateExtractItemCount(String extractItemCountTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(extractItemCountTagActualValue);
	if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}	
	

public static boolean validateItemId(String itemIdTagActualValue){
	String TagExpectedPatternMatch = itemIdTagActualValue;
	if(TagExpectedPatternMatch.length()==25)
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
	if(TagExpectedPatternMatch.length()==3)
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
	if(TagExpectedPatternMatch.length()==4)
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
	String pattern="([0-9]+[.][0-9]{1,2})";
	if(TagExpectedPatternMatch.length()<=15 && TagExpectedPatternMatch.matches(pattern))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validatePostingDecisionReasonCode(String postingDecisionRevisionCodeTagActualValue){
	String TagExpectedPatternMatch = postingDecisionRevisionCodeTagActualValue;
	if(TagExpectedPatternMatch.length()==4)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validatePostingOverrideFlag(String postingOverrideFlagTagActualValue){
	String TagExpectedPatternMatch = postingOverrideFlagTagActualValue;
	if(TagExpectedPatternMatch.equals("Y") || TagExpectedPatternMatch.equals("N"))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

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

public static boolean validateSupportingInfo(String supportingInfoTagActualValue){
	String TagExpectedPatternMatch = supportingInfoTagActualValue;
	if(TagExpectedPatternMatch.length()==40)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateChequeCount(String chequeCountTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(chequeCountTagActualValue);
	if(tempDoubleValue >= 1000 && tempDoubleValue <= 9999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateCollectingParticipantId(String collectingParticipantIdTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(collectingParticipantIdTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
	}else {
		resultFlag = false;
	}
	return resultFlag;
}

public static boolean validateCollectingLocation(String collectingLocationTagActualValue){
	String TagExpectedPatternMatch = collectingLocationTagActualValue;
	if(TagExpectedPatternMatch.length()==10)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateAggregrated(String aggregratedTagActualValue){
	String TagExpectedPatternMatch = aggregratedTagActualValue;
	if(TagExpectedPatternMatch.equals("1")||TagExpectedPatternMatch.equals("0"))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}


public static boolean validateItemIdDR(String ItemIdDRTagActualValue){
	String TagExpectedPatternMatch = ItemIdDRTagActualValue;
	if(TagExpectedPatternMatch.length()==25)
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

public static boolean validateSerialDR(String serialDRTagActualValue)
{
	Double tempDoubleValue = 0d;
	tempDoubleValue = Double.parseDouble(serialDRTagActualValue);
	if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
		resultFlag = true;
	}else {
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
	String pattern="([0-9]+[.][0-9]{1,2})";
	if(TagExpectedPatternMatch.length()<=15 && TagExpectedPatternMatch.matches(pattern))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}


public static boolean validateItemIdCR(String itemIdCRTagActualValue){
	String TagExpectedPatternMatch = itemIdCRTagActualValue;
	if(TagExpectedPatternMatch.length()==25)
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
	if(TagExpectedPatternMatch.length()==18)
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
	String pattern = "([0-9]+[.][0-9]{1,2})";
	if(tempDoubleValue <= 999999999 ){
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
	if(TagExpectedPatternMatch.length()==50)
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}

public static boolean validateFileName(String fileId){
	String TagExpectedPatternMatch = fileId;
	String Pattern= "(ICSPST[LT][0-9]{8}[0-9]{4}[0-9]{2})";
	if(TagExpectedPatternMatch.matches(Pattern))
	resultFlag = true;
	else {
		resultFlag = false;
	}		
	return resultFlag;
}


}
