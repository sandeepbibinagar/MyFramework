package com.ics.fred.testScenarios;

import org.testng.annotations.Test;

public class ValidateTagsCore {

	
	static boolean resultFlag;
	
	@Test
	public static void result(){
		String recordCountsTagActualValue ="12345678";
		boolean f =validateRecordCounts(recordCountsTagActualValue);
		System.out.println(f);
	}
	
	
	
	public static boolean validateRecordCounts(String recordCountsTagActualValue)
	{
		int tenpIntValue = 0;
		tenpIntValue = Integer.parseInt(recordCountsTagActualValue);
		int count =recordCountsTagActualValue.length();
		if(tenpIntValue >= 1 && tenpIntValue <= 99999999){
			resultFlag = true;
			System.out.println("tenpIntValue"+tenpIntValue+"Lenght of record count: "+count);
		}else {
			resultFlag = false;
		}
		return resultFlag;
	}
	
}
