package com.ics.mo.xmlFiles.icnContent;

import org.apache.log4j.Logger;
import com.ics.seleniumCoreUtilis.Component;

//<copyright  file="MsgMA02.java" company="iPSL">
//Copyright © iPSL 2017 All rights are reserved.
//Reproduction or transmission in whole or in part, in any form or by any means, electronic, mechanical or otherwise,
//is prohibited without the prior written permission of the copyright owner.
//</copyright>

/******************MODULE HEADER*****************************************
* Module Name -  MsgMA02.java
************************************************************************ 
* Date -  26/04/2017
************************************************************************ 
* Created By -  MuluguUm
************************************************************************ 
* Description - Required Generic Functionalities to check MsgMA02
***********************************************************************/
public class MsgMA02 extends Msg06FM_Msg05FM {
	protected static final Logger msg06MA02Log = Logger.getLogger(MsgMA02.class.getSimpleName()) ;
	protected static String entitesDataMsg06FM01;
	protected static String entitiesDataMsg06MA02;
	protected static String itemsSectionMsg06FM01;
	protected static String itemsSectionMsg06MA02;
	protected static String captureInfoMsg06FM01;
	protected static String captureInfoMsg06MA02;
	protected static String tempICNAcutalData;
	protected static String tempICNExpectedData;	
	protected static String tempICNActualEntityId;
	protected static String tempICNExpectedEntityId;
	protected static String tempICNActualEntitySourceDate;
	protected static String tempICNExpectedEntitySourceDate;
	protected static String tempICNActualItemId;
	protected static String tempICNExpectedItemId;
	protected static boolean checkFlag ;

	/*
	 * 1. Method to validate ICN Content loaded in Source Table for MSgMA02 with Fred Data
	 */
	public static void validateICNContentInSourceForMsgMA02(String msg06FM01SourceContent, String msg06MA02SourceContent, String actualWorkFlow,
			String expectedWorkFlow)
	{
		entitesDataMsg06FM01 = msg06FM01SourceContent.substring(msg06FM01SourceContent.indexOf("<Entities>") + "<Entities>".length(), 
				msg06FM01SourceContent.indexOf("</Entities>"));
		entitiesDataMsg06MA02 = msg06MA02SourceContent.substring(msg06MA02SourceContent.indexOf("<Entities>") + "<Entities>".length(), 
				msg06MA02SourceContent.indexOf("</Entities>"));		

		itemsSectionMsg06FM01 = msg06FM01SourceContent.substring(msg06FM01SourceContent.indexOf("<Items>") + "<Items>".length(), 
				msg06FM01SourceContent.indexOf("</Items>"));
		itemsSectionMsg06MA02 = msg06MA02SourceContent.substring(msg06MA02SourceContent.indexOf("<Items>") + "<Items>".length(), 
				msg06MA02SourceContent.indexOf("</Items>"));	
		
		captureInfoMsg06FM01 = msg06FM01SourceContent.substring(msg06FM01SourceContent.indexOf("<CaptureInfo>") + "<CaptureInfo>".length(), 
				msg06FM01SourceContent.indexOf("</CaptureInfo>"));
		captureInfoMsg06MA02 = msg06MA02SourceContent.substring(msg06MA02SourceContent.indexOf("<CaptureInfo>") + "<CaptureInfo>".length(), 
				msg06MA02SourceContent.indexOf("</CaptureInfo>"));	
		
		try{
			int stateDivisionOfMsg06MA02 = Integer.parseInt(actualSourceStateDivision);
			stateDivisionOfMsg06MA02 = stateDivisionOfMsg06MA02 + 2;
			actualSourceStateDivision = stateDivisionOfMsg06MA02 + "";
		}catch(Exception e)
		{
			
		}		
		checkEntitiesSectionOfMsg06MA02(actualWorkFlow, expectedWorkFlow);
		if(!checkFlag)
		{
			msg06MA02Log.info("ICN Content-Entities Section of "+ expectedWorkFlow  + " loaded for ICN Content-Entities Section of  "+ actualWorkFlow);
	        publishResults(true, "ICN Content-Entities Section of "+ expectedWorkFlow  + " loaded for ICN Content-Entities Section of  "+ actualWorkFlow, 
	        		"Should see ICN Content-Entities Section of "+ expectedWorkFlow  + " loaded for "+ actualWorkFlow,
	        		"Check ICN Content-Entities Section of "+ actualWorkFlow);
		}else {
			msg06MA02Log.error("ICN Content-Entities Section of "+ expectedWorkFlow  + " not loaded properly for ICN Content-Entities Section of  "+ actualWorkFlow);
	        publishResults(false, "ICN Content-Entities Section of "+ expectedWorkFlow  + " not loaded properly for ICN Content-Entities Section of  "+ actualWorkFlow, 
	        		"Should see ICN Content-Entities Section of "+ expectedWorkFlow  + " to load for "+ actualWorkFlow,
	        		"Check ICN Content-Entities Section of "+ actualWorkFlow);
		}
		
		checkItemsSectionOfMsg06MA02(actualWorkFlow, expectedWorkFlow);	
		if(!checkFlag)
		{
			msg06MA02Log.info("ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow  + " loaded for ICN Content-Item Section with CodeLine details of  "+ actualWorkFlow);
			publishResults(true, "ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow  + " loaded for ICN Content-Item Section with CodeLine details of  "+ actualWorkFlow, 
	        		"Should see ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow  + " to load for "+ actualWorkFlow,
	        		"Check ICN Content-Item Section with CodeLine details of "+ actualWorkFlow);
		}else {
			msg06MA02Log.error("ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow  + " not loaded properly for ICN Content-Item Section with CodeLine details of  "+ actualWorkFlow);
			publishResults(false, "ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow  + " not loaded properly for ICN Content-Item Section with CodeLine details of  "+ actualWorkFlow, 
	        		"Should see ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow  + " to load for "+ actualWorkFlow,
	        		"Check ICN Content-Item Section with CodeLine details of "+ actualWorkFlow);
		}
		
		checkCaptureInfoOfMsg06MA02(actualWorkFlow, expectedWorkFlow);
		if(!checkFlag)
		{
			msg06MA02Log.info("ICN Content-Capture Information details of "+ expectedWorkFlow  + " loaded for ICN Content-Capture Information details of  "+ actualWorkFlow);
			publishResults(true, "ICN Content-Capture Information details of "+ expectedWorkFlow  + " loaded for ICN Content-Capture Information details of  "+ actualWorkFlow, 
	        		"Should see ICN Content-Capture Information details of "+ expectedWorkFlow  + " to load for "+ actualWorkFlow,
	        		"Check ICN Content-Capture Information details of "+ actualWorkFlow);
		}
		
		if(checkFlag)
		{
			Component.assertTrue(false, "Required ICN Content from "+ expectedWorkFlow  + " not properly loaded in ICN content of  "+ actualWorkFlow);
			msg06MA02Log.error("Required ICN Content from "+ expectedWorkFlow  + " not properly loaded in ICN content of  "+ actualWorkFlow);
		}else{
			msg06MA02Log.info("Required ICN Content from "+ expectedWorkFlow  + " properly loaded in ICN content of  "+ actualWorkFlow);
		}
	}

	/*
	 * 1.1 Method to validate ICN Content Entities details loaded in Source Table for MSgMA02 with Fred Data
	 */
	protected static void checkEntitiesSectionOfMsg06MA02(String actualWorkFlow, String expectedWorkFlow)
	{
		do
		{
			tempICNExpectedData = entitesDataMsg06FM01.substring(entitesDataMsg06FM01.indexOf("<Entity>")+ "<Entity>".length(), entitesDataMsg06FM01.indexOf("</Entity>"));
			tempICNAcutalData = entitiesDataMsg06MA02.substring(entitiesDataMsg06MA02.indexOf("<Entity>")+ "<Entity>".length(), entitiesDataMsg06MA02.indexOf("</Entity>"));

			tempICNActualEntitySourceDate = tempICNAcutalData.substring(tempICNAcutalData.indexOf("<SourceDateTime>") + "<SourceDateTime>".length(), tempICNAcutalData.indexOf("</SourceDateTime>"));
			int actualEntitySourceDate = tempICNActualEntitySourceDate.indexOf(">");
			tempICNActualEntitySourceDate = tempICNActualEntitySourceDate.substring(++actualEntitySourceDate);
			tempICNExpectedEntitySourceDate = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<SourceDateTime>") + "<SourceDateTime>".length(), tempICNExpectedData.indexOf("</SourceDateTime>"));
			int expectedEntitySourceDate = tempICNExpectedEntitySourceDate.indexOf(">");
			tempICNExpectedEntitySourceDate = tempICNExpectedEntitySourceDate.substring(++expectedEntitySourceDate);

			tempICNActualEntityId = tempICNAcutalData.substring(tempICNAcutalData.indexOf("<EntityId>") + "<EntityId>".length(), tempICNAcutalData.indexOf("</EntityId>"));
			int actualEntityId = tempICNActualEntityId.indexOf(">");
			tempICNActualEntityId = tempICNActualEntityId.substring(++actualEntityId);
			tempICNExpectedEntityId = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<EntityId>") + "<EntityId>".length(), tempICNExpectedData.indexOf("</EntityId>"));
			int expectedEntityId = tempICNExpectedEntityId.indexOf(">");
			tempICNExpectedEntityId = tempICNExpectedEntityId.substring(++expectedEntityId);

			tempICNAcutalData =  tempICNAcutalData.substring(0, tempICNAcutalData.indexOf("<SourceDateTime>")).trim();			
			tempICNExpectedData =  tempICNExpectedData.substring(0, tempICNExpectedData.indexOf("<SourceDateTime>")).trim();			
			
			if(tempICNActualEntityId.equals(tempICNExpectedEntityId))
			{
				String actualStateDivision = tempICNAcutalData.substring(tempICNAcutalData.indexOf("<StateRevision>") + "<StateRevision>".length(), tempICNAcutalData.indexOf("</StateRevision>")).trim();
				int tempStateDivisionIndex = actualStateDivision.indexOf(">");
				actualStateDivision = actualStateDivision.substring(++tempStateDivisionIndex);
				boolean stateDivisionFlag = actualStateDivision.equals(actualSourceStateDivision);
				if(actualStateDivision.equals(actualSourceStateDivision))
				{
					publishResults(stateDivisionFlag, actualStateDivision, actualSourceStateDivision, "Check the incremented value of SourceStateDivision for workflow "+ actualWorkFlow +" with EntityId "+ tempICNActualEntityId);
				    msg06MA02Log.info("SourceStateDivision for workflow "+ actualWorkFlow +" with EntityId "+ tempICNActualEntityId + " is "+ actualStateDivision + " matches with actual value");
				}else {
					checkFlag = true;
					publishResults(stateDivisionFlag, actualStateDivision, actualSourceStateDivision, "Check the incremented value of SourceStateDivision for workflow "+ actualWorkFlow+" for EntityId "+ tempICNActualEntityId);
				    msg06MA02Log.error("SourceStateDivision for workflow "+ actualWorkFlow +" with EntityId "+ tempICNActualEntityId + " is "+ actualStateDivision + " matches with actual value");
				}
				
				tempICNAcutalData =  tempICNAcutalData.substring(0, tempICNAcutalData.indexOf("<StateRevision>")).trim();			
				if(tempICNActualEntitySourceDate.equals(tempICNExpectedEntitySourceDate))
				{
					checkFlag = true;
					validationErrorInformation("Error in SourceId Section of EntityId "+ tempICNActualEntityId + " SourceDateTime of " + actualWorkFlow + " should not match with "+ expectedWorkFlow);
					msg06MA02Log.error("Error in SourceId Section of EntityId "+ tempICNActualEntityId + " SourceDateTime of " + actualWorkFlow + " should not match with "+ expectedWorkFlow);					
				}
			}else {
				checkFlag = true;
				validationErrorInformation("Entity Section for EntityId "+ tempICNActualEntityId + " not available in "+ actualWorkFlow);
				msg06MA02Log.error("Entity Section for EntityId "+ tempICNActualEntityId + " not available in "+ actualWorkFlow);
			}
			entitesDataMsg06FM01 = entitesDataMsg06FM01.substring(entitesDataMsg06FM01.indexOf("</Entity>") + "</Entity>".length());
			entitiesDataMsg06MA02 =  entitiesDataMsg06MA02.substring(entitiesDataMsg06MA02.indexOf("</Entity>") + "</Entity>".length());
		}while(entitesDataMsg06FM01.indexOf("<Entity>") > 0);
	}
	
	/*
	 * 1.2 Method to validate ICN Content ItemsSection details loaded in Source Table for MSgMA02 with Fred Data
	 */
	private static void checkItemsSectionOfMsg06MA02(String actualWorkFlow, String expectedWorkFlow)
	{
		checkFlag = false;
		do
		{
			tempICNExpectedData = itemsSectionMsg06FM01.substring(itemsSectionMsg06FM01.indexOf("<Item>")+ "<Item>".length(), itemsSectionMsg06FM01.indexOf("</Item>"));
			tempICNAcutalData = itemsSectionMsg06MA02.substring(itemsSectionMsg06MA02.indexOf("<Item>")+ "<Item>".length(), itemsSectionMsg06MA02.indexOf("</Item>"));	

			tempICNActualItemId = tempICNAcutalData.substring(tempICNAcutalData.indexOf("<ItemId>") + "<ItemId>".length(), tempICNAcutalData.indexOf("</ItemId>"));
			int actualItemId = tempICNActualItemId.indexOf(">");
			tempICNActualItemId = tempICNActualItemId.substring(++actualItemId);
			tempICNExpectedItemId = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<ItemId>") + "<ItemId>".length(), tempICNExpectedData.indexOf("</ItemId>"));
			int expectedItemId = tempICNExpectedItemId.indexOf(">");
			tempICNExpectedItemId = tempICNExpectedItemId.substring(++expectedItemId);

			if(tempICNActualItemId.equals(tempICNExpectedItemId))
			{
				tempICNExpectedData = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<Codeline>")+ "<Codeline>".length(), tempICNExpectedData.indexOf("</Codeline>"));
				int expectedData = tempICNExpectedData.indexOf(">");
				tempICNExpectedData = tempICNExpectedData.substring(++expectedData);
				
				tempICNAcutalData = tempICNAcutalData.substring(tempICNAcutalData.indexOf("<Codeline>")+ "<Codeline>".length(), tempICNAcutalData.indexOf("</Codeline>"));
				int actualData = tempICNAcutalData.indexOf(">");
				tempICNAcutalData = tempICNAcutalData.substring(++actualData);
				
				if(!(tempICNExpectedData.equals(tempICNAcutalData)))
				{
					checkFlag = true;
					validationErrorInformation("CodeLine Section of " + actualWorkFlow + " for ItemId "+ tempICNActualItemId + " not matches with "+ expectedWorkFlow);
					msg06MA02Log.error("CodeLine Section of " + actualWorkFlow + " for ItemId "+ tempICNActualItemId + " not matches with "+ expectedWorkFlow);
				}
			}
			itemsSectionMsg06FM01 = itemsSectionMsg06FM01.substring(itemsSectionMsg06FM01.indexOf("</Item>") + "</Item>".length());
			itemsSectionMsg06MA02 =  itemsSectionMsg06MA02.substring(itemsSectionMsg06MA02.indexOf("</Item>") + "</Item>".length());
		}while(itemsSectionMsg06FM01.indexOf("<Item>") > 0);
	}
	
	/*
	 * 1.3 Method to validate ICN Content CaptureInfo loaded in Source Table for MSgMA02 with Fred Data
	 */
	private static void checkCaptureInfoOfMsg06MA02(String actualWorkFlow, String expectedWorkFlow)
	{
		checkFlag = false;
		do
		{
			tempICNExpectedData = captureInfoMsg06FM01.substring(captureInfoMsg06FM01.indexOf("<CaptureItem>")+ "<CaptureItem>".length(), captureInfoMsg06FM01.indexOf("</CaptureItem>"));
			tempICNAcutalData = captureInfoMsg06MA02.substring(captureInfoMsg06MA02.indexOf("<CaptureItem>")+ "<CaptureItem>".length(), captureInfoMsg06MA02.indexOf("</CaptureItem>"));	
	
			tempICNActualItemId = tempICNAcutalData.substring(tempICNAcutalData.indexOf("<ItemId>") + "<ItemId>".length(), tempICNAcutalData.indexOf("</ItemId>"));
			int actualItemId = tempICNActualItemId.indexOf(">");
			tempICNActualItemId = tempICNActualItemId.substring(++actualItemId);
			tempICNExpectedItemId = tempICNExpectedData.substring(tempICNExpectedData.indexOf("<ItemId>") + "<ItemId>".length(), tempICNExpectedData.indexOf("</ItemId>"));
			int expectedItemId = tempICNExpectedItemId.indexOf(">");
			tempICNExpectedItemId = tempICNExpectedItemId.substring(++expectedItemId);
			
			if(!(tempICNExpectedData.equals(tempICNAcutalData)))
			{
				checkFlag = true;
				validationErrorInformation("CaptureInfo-CaptureItem Section of " + actualWorkFlow + " for ItemId "+ tempICNActualItemId + " not matches with "+ expectedWorkFlow);
				msg06MA02Log.error("CaptureInfo-CaptureItem Section of " + actualWorkFlow + " for ItemId "+ tempICNActualItemId + " not matches with "+ expectedWorkFlow);
			}		
		}while(captureInfoMsg06FM01.indexOf("<CaptureItem>") > 0);			
	}
}
