package com.ics.rNe.testScenarios;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class PostingResponseHeaderValidationScenarios {
	private static boolean resultFlag ;

	public static boolean validateParticipant(String participantIdActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(participantIdActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}

	public static boolean validateVersion(String versionTagActualValue)
	{
		int tempIntegerValue = 0;
		tempIntegerValue=Integer.parseInt(versionTagActualValue);
		if(tempIntegerValue >= 1 && tempIntegerValue <= 99){
			resultFlag = true;
		}else {
			resultFlag = false;
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

	public static boolean validateSchema(String schemaTagActualvalue){
		String schemaTagExpectedPatternMatch =schemaTagActualvalue;
		//String schemaTagExpectedPatternMatch = "([0-90-9.0-9]){0,10}";
		if(schemaTagExpectedPatternMatch.length()==4)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}

	public static boolean validateSequence(String sequenceTagActualValue){
		String sequenceTagExpectedPatternMatch = sequenceTagActualValue;
		if(sequenceTagExpectedPatternMatch.length()==4)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}

	public static boolean validateEnvironment(String environmentTagActualValue){
		String environmentTagExpectedPatternMatch = environmentTagActualValue;
		if(environmentTagExpectedPatternMatch.length()==1)
		{
			if(environmentTagExpectedPatternMatch.equals("T") || environmentTagExpectedPatternMatch.equals("L"))
				resultFlag = true;
			else
				resultFlag = false;
		}
		
			
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateEnvironment_58731(String environmentTagActualValue){
		String environmentTagExpectedPatternMatch = environmentTagActualValue;
		String patt = "([0-9A-Z])";
		if(environmentTagExpectedPatternMatch.length()==1 && environmentTagExpectedPatternMatch.matches(patt))
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	

	public static boolean validateSource(String sourceTagActualValue){
		String sourceTagExpectedPatterMatch = sourceTagActualValue;
		if(sourceTagExpectedPatterMatch.length()>=1 && sourceTagExpectedPatterMatch.length()<=4 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}

	public static boolean validateFileDate(String fileDateTagActualValue){
		
		try
		{
			String[] s1=fileDateTagActualValue.split("T");
			
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat timeFormat=new SimpleDateFormat("hh:mm:ss");
			
			String date = dateFormat.format(dateFormat.parse(s1[0]));
			String time = timeFormat.format(timeFormat.parse(s1[1]));
			
			if(s1[0].equalsIgnoreCase(date) && s1[1].equalsIgnoreCase(time))
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
	
	public static boolean validateSequenceR(String sequenceRTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(sequenceRTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateTranID(String tranIDTagActualValue){
		String TagExpectedPatternMatch = tranIDTagActualValue;
		if(TagExpectedPatternMatch.length()==25)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validatePostType(String postTypeTagActualValue){
		String TagExpectedPatternMatch = postTypeTagActualValue;
		if(TagExpectedPatternMatch.length()==3)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}

	public static boolean validateAccNum(String accNumTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(accNumTagActualValue);
		if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
			resultFlag = true;
		}else {
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
	
	public static boolean validateNPAAcc(String nPAAccTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(nPAAccTagActualValue);
		if(tempDoubleValue >= 10000000 && tempDoubleValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateNPASort(String nPASortTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(nPASortTagActualValue);
		if(tempDoubleValue >= 100000 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateAmount(String amountTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(amountTagActualValue);
		if(tempDoubleValue >= 1 && tempDoubleValue <= 999999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateRedirInd(String redirIndTagActualValue){
		String TagExpectedPatternMatch = redirIndTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("Y") || TagExpectedPatternMatch.equalsIgnoreCase("N"))
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateAccSystem(String accSystemTagActualValue){
		String TagExpectedPatternMatch = accSystemTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=4 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateRespStatus(String respStatusTagActualValue){
		String TagExpectedPatternMatch = respStatusTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("Y") || TagExpectedPatternMatch.equalsIgnoreCase("N") || TagExpectedPatternMatch.equalsIgnoreCase("P"))
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateRespSubType(String respSubTypeTagActualValue){
		String TagExpectedPatternMatch = respSubTypeTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("D") || TagExpectedPatternMatch.equalsIgnoreCase("C") || TagExpectedPatternMatch.equalsIgnoreCase("A") || TagExpectedPatternMatch.equalsIgnoreCase("T"))
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateStatusCnt(String statusCntTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(statusCntTagActualValue);
		if(tempDoubleValue >=0 && tempDoubleValue <= 999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateStatusSeq(String statusSeqTagActualValue)
	{
		Double tempDoubleValue = 0d;
		tempDoubleValue = Double.parseDouble(statusSeqTagActualValue);
		if(tempDoubleValue >=0 && tempDoubleValue <= 999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateReasonCode(String reasonCodeTagActualValue){
		String TagExpectedPatternMatch = reasonCodeTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=6 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateReasonText(String reasonTextTagActualValue){
		String TagExpectedPatternMatch = reasonTextTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=40 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateRicherData(String richerDataTagActualValue){
		String TagExpectedPatternMatch = richerDataTagActualValue;
		if(TagExpectedPatternMatch.length()>=1 && TagExpectedPatternMatch.length()<=256 )
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateFraudStatus(String fraudStatusTagActualValue){
		String TagExpectedPatternMatch = fraudStatusTagActualValue;
		if(TagExpectedPatternMatch.length()==4)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateFraudReason(String fraudReasonTagActualValue){
		String TagExpectedPatternMatch = fraudReasonTagActualValue;
		if(TagExpectedPatternMatch.length()==4)
		{
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
		if(tempDoubleValue >=0 && tempDoubleValue <= 999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateWeekDay(String weekDayTagActualValue){
		String TagExpectedPatternMatch = weekDayTagActualValue;
		if((TagExpectedPatternMatch.equalsIgnoreCase("MO") || TagExpectedPatternMatch.equalsIgnoreCase("TU") || TagExpectedPatternMatch.equalsIgnoreCase("WE") || TagExpectedPatternMatch.equalsIgnoreCase("TH")|| TagExpectedPatternMatch.equalsIgnoreCase("FR")) && TagExpectedPatternMatch.length()==2)
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateType(String typeTagActualValue){
		String TagExpectedPatternMatch = typeTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("T"))
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateCcy(String ccyTagActualValue){
		String TagExpectedPatternMatch = ccyTagActualValue;
		if(TagExpectedPatternMatch.equalsIgnoreCase("GBP"))
		{
			resultFlag = true;
		}else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	
	
}
