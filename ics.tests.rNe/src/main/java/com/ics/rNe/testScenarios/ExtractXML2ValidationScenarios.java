package com.ics.rNe.testScenarios;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class ExtractXML2ValidationScenarios extends GenericMethodUtilis{
	
	private static boolean resultFlag ;
	
	//TC-58731 Validations
	
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
	
	public static boolean validateCurrency(String TypeTagActualValue){
		String TagExpectedPatternMatch = TypeTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("GBP"))
		{
			resultFlag = true;
			publishResults(resultFlag, TagExpectedPatternMatch, "GBP", "Currency validation");
		}else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "GBP" , "Currency validation");
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
	
	//TC-58755 validations
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
	
	public static boolean validateDefSort(String TagActualValue){
		String TagExpectedPatternMatch = TagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("Y") || TagExpectedPatternMatch.equalsIgnoreCase("N")){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "DefSort validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "DefSort validation");
		}		
		return resultFlag;
	}

	public static boolean validateDefAcc(String TagActualValue){
		String TagExpectedPatternMatch = TagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("Y") || TagExpectedPatternMatch.equalsIgnoreCase("N")){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "DefAcc validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "DefAcc validation");
		}		
		return resultFlag;
	}

	public static boolean validateRepresent(String TagActualValue){
		String TagExpectedPatternMatch = TagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("Y") || TagExpectedPatternMatch.equalsIgnoreCase("N")){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "Represent validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "Represent validation");
		}		
		return resultFlag;
	}

	public static boolean validateFrontQual(String TagActualValue){
		String TagExpectedPatternMatch = TagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("Y") || TagExpectedPatternMatch.equalsIgnoreCase("N")){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "FrontQual validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "FrontQual validation");
		}		
		return resultFlag;
	}

	public static boolean validateRearQual(String TagActualValue){
		String TagExpectedPatternMatch = TagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("Y") || TagExpectedPatternMatch.equalsIgnoreCase("N")){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "RearQual validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "RearQual validation");
		}		
		return resultFlag;
	}

	public static boolean validateFraudCode(String TagActualValue){
		String TagExpectedPatternMatch = TagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("OK") || TagExpectedPatternMatch.equalsIgnoreCase("NotProcessed")|| TagExpectedPatternMatch.equalsIgnoreCase("Suspect")|| TagExpectedPatternMatch.equalsIgnoreCase("Fraudulent")){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "Should be OK/NotProcessed/Suspect/Fraudulent", "FraudCode validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be OK/NotProcessed/Suspect/Fraudulent", "FraudCode validation");
		}		
		return resultFlag;
	}

	public static boolean validateFraudReason(String NameTagActualValue){
		String TagExpectedPatternMatch = NameTagActualValue;
		if(TagExpectedPatternMatch.length()==3){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "should be 3 char string", "FraudReason validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "should be 3 char string", "FraudReason validation");
		}		
		return resultFlag;
	}

	public static boolean validateFraudName(String TagActualValue){
		String TagExpectedPatternMatch = TagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("OK")){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "Should be OK", "FraudName validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be OK", "FraudName validation");
		}		
		return resultFlag;
	}

	public static boolean validateDuplicate(String TagActualValue){
		String TagExpectedPatternMatch = TagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("Y") || TagExpectedPatternMatch.equalsIgnoreCase("N")){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "Duplicate validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be Y or N", "Duplicate validation");
		}		
		return resultFlag;
	}

	public static boolean validateDupId(String NameTagActualValue){
		String TagExpectedPatternMatch = NameTagActualValue;
		if(TagExpectedPatternMatch.length()==25){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "should be 25 char string", "DupId validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "should be 25 char string", "DupId validation");
		}		
		return resultFlag;
	}

	public static boolean validateDupStatus(String TagActualValue){
		String TagExpectedPatternMatch = TagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("FULL") || TagExpectedPatternMatch.equalsIgnoreCase("PART")){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "Should be FULL or PART", "DupStatus validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "Should be FULL or PART", "DupStatus validation");
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
			
			if(dupSeenTagActualValue.equalsIgnoreCase(strTotal)){
				resultFlag = true;
				publishResults(resultFlag, dupSeenTagActualValue, "Should be Date Format", "DupSeen Validation");
			}
			else{
				resultFlag=false;
				publishResults(resultFlag, dupSeenTagActualValue, "Should be Date Format", "DupSeen Validation");
			}
		
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
		if(TagExpectedPatternMatch.length()==6){
		resultFlag = true;
		publishResults(resultFlag, TagExpectedPatternMatch, "should be 6 char string", "DupCollect validation");
		}
		else {
			resultFlag = false;
			publishResults(resultFlag, TagExpectedPatternMatch, "should be 6 char string", "DupCollect validation");
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
			
			if(dupCaptureTagActualValue.equalsIgnoreCase(strTotal)){
				resultFlag = true;
				publishResults(resultFlag, dupCaptureTagActualValue, "Should be Date Format", "DupCollect Validation");
			}
			else{
				resultFlag=false;
				publishResults(resultFlag, dupCaptureTagActualValue, "Should be Date Format", "DupCollect Validation");
			}
		
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
