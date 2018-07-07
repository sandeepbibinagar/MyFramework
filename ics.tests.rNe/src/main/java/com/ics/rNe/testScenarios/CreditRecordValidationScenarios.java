package com.ics.rNe.testScenarios;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */

public class CreditRecordValidationScenarios {
	
	private static boolean resultFlag ;
	
	public static boolean validateCreditId(String creditIdTagActualValue){
		String TagExpectedPatternMatch = creditIdTagActualValue;
		if(TagExpectedPatternMatch.length()==25)
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
		if(tempDoubleValue >=1 && tempDoubleValue <= 999999){
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
		if(tempDoubleValue >=1 && tempDoubleValue <= 99999999){
			resultFlag = true;
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
	public static boolean validateRef(String refTagActualValue){
		String TagExpectedPatternMatch = refTagActualValue;
		if(TagExpectedPatternMatch.length()<=18)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateTranCode(String tranCodeActualValue){
		String TagExpectedPatternMatch = tranCodeActualValue;
		if(TagExpectedPatternMatch.length()<=2)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	public static boolean validateBeneficiary(String beneficiaryActualValue){
		String TagExpectedPatternMatch = beneficiaryActualValue;
		if(TagExpectedPatternMatch.length()<=50)
		resultFlag = true;
		else {
			resultFlag = false;
		}		
		return resultFlag;
	}
	
	



}
