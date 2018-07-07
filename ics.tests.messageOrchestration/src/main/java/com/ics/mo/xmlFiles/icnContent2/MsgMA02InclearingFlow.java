package com.ics.mo.xmlFiles.icnContent2;

import org.apache.log4j.Logger;
import com.ics.mo.common.MoGenericMethods2;
import com.ics.seleniumCoreUtilis.Component;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class MsgMA02InclearingFlow extends MoGenericMethods2
{

	public MsgMA02InclearingFlow(ExtentReports extent, ExtentTest extentLog) {
		super(extent, extentLog);
		// TODO Auto-generated constructor stub
	}

	private final Logger msgMA02InclearingFlowLog = Logger.getLogger(MsgMA02InclearingFlow.class.getSimpleName()) ;
	private String entitesDataMsgFM01Inclearing;
	private String entitiesDataMsgMA02Inclearing;
	private String itemsSectionMsgFM01Inclearing;
	private String itemsSectionMsgMA02Inclearing;
	private String captureInfoMsgFM01Inclearing;
	private String captureInfoMsgMA02Inclearing;
	private String tempICNAcutalData;
	private String tempICNExpectedData;	
	private String tempICNActualEntityId;
	private String tempICNExpectedEntityId;
	private String tempICNActualEntitySourceDate;
	private String tempICNExpectedEntitySourceDate;
	private String tempICNActualItemId;
	private String tempICNExpectedItemId;
	private boolean checkFlag ;
	
	/**
	 * This Method to validate ICN Content loaded in Source Table for MSgMA02 with FRED Data
	 * @param msgFM01SourceContent
	 * @param msgMA02SourceContent
	 * @param actualWorkFlow
	 * @param expectedWorkFlow
	 */
	public void validateICNContentInSourceForMsgMA02(String msgFM01SourceContent, String msgMA02SourceContent, String actualWorkFlow,
			String expectedWorkFlow)
	{
		entitesDataMsgFM01Inclearing = msgFM01SourceContent.substring(msgFM01SourceContent.indexOf("<Entities>") + "<Entities>".length(), 
				msgFM01SourceContent.indexOf("</Entities>"));
		entitiesDataMsgMA02Inclearing = msgMA02SourceContent.substring(msgMA02SourceContent.indexOf("<Entities>") + "<Entities>".length(), 
				msgMA02SourceContent.indexOf("</Entities>"));		

		itemsSectionMsgFM01Inclearing = msgFM01SourceContent.substring(msgFM01SourceContent.indexOf("<Items>") + "<Items>".length(), 
				msgFM01SourceContent.indexOf("</Items>"));
		itemsSectionMsgMA02Inclearing = msgMA02SourceContent.substring(msgMA02SourceContent.indexOf("<Items>") + "<Items>".length(), 
				msgMA02SourceContent.indexOf("</Items>"));	
		
		captureInfoMsgFM01Inclearing = msgFM01SourceContent.substring(msgFM01SourceContent.indexOf("<CaptureInfo>") + "<CaptureInfo>".length(), 
				msgFM01SourceContent.indexOf("</CaptureInfo>"));
		captureInfoMsgMA02Inclearing = msgMA02SourceContent.substring(msgMA02SourceContent.indexOf("<CaptureInfo>") + "<CaptureInfo>".length(), 
				msgMA02SourceContent.indexOf("</CaptureInfo>"));	
		
		checkEntitiesSectionOfMsg06MA02(actualWorkFlow, expectedWorkFlow);
		if(!checkFlag)
		{
			msgMA02InclearingFlowLog.info("ICN Content-Entities Section of "+ expectedWorkFlow + " loaded for ICN Content-Entities Section of "+ actualWorkFlow);
	    publishResults(true, "ICN Content-Entities Section of "+ expectedWorkFlow + " loaded for ICN Content-Entities Section of "+ actualWorkFlow, 
	    		"Should see ICN Content-Entities Section of "+ expectedWorkFlow + " loaded for "+ actualWorkFlow,
	    		"Check ICN Content-Entities Section of "+ actualWorkFlow);
		}else {
			msgMA02InclearingFlowLog.error("ICN Content-Entities Section of "+ expectedWorkFlow + " not loaded properly for ICN Content-Entities Section of "+ actualWorkFlow);
	    publishResults(false, "ICN Content-Entities Section of "+ expectedWorkFlow + " not loaded properly for ICN Content-Entities Section of "+ actualWorkFlow, 
	    		"Should see ICN Content-Entities Section of "+ expectedWorkFlow + " to load for "+ actualWorkFlow,
	    		"Check ICN Content-Entities Section of "+ actualWorkFlow);
		}
		
		checkItemsSectionOfMsg06MA02(actualWorkFlow, expectedWorkFlow);	
		if(!checkFlag)
		{
			msgMA02InclearingFlowLog.info("ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow + " loaded for ICN Content-Item Section with CodeLine details of "+ actualWorkFlow);
			publishResults(true, "ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow + " loaded for ICN Content-Item Section with CodeLine details of "+ actualWorkFlow, 
	    		"Should see ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow + " to load for "+ actualWorkFlow,
	    		"Check ICN Content-Item Section with CodeLine details of "+ actualWorkFlow);
		}else {
			msgMA02InclearingFlowLog.error("ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow + " not loaded properly for ICN Content-Item Section with CodeLine details of "+ actualWorkFlow);
			publishResults(false, "ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow + " not loaded properly for ICN Content-Item Section with CodeLine details of "+ actualWorkFlow, 
	    		"Should see ICN Content-Item Section with CodeLine details of "+ expectedWorkFlow + " to load for "+ actualWorkFlow,
	    		"Check ICN Content-Item Section with CodeLine details of "+ actualWorkFlow);
		}
		
		checkCaptureInfoOfMsg06MA02(actualWorkFlow, expectedWorkFlow);
		if(!checkFlag)
		{
			msgMA02InclearingFlowLog.info("ICN Content-Capture Information details of "+ expectedWorkFlow + " loaded for ICN Content-Capture Information details of "+ actualWorkFlow);
			publishResults(true, "ICN Content-Capture Information details of "+ expectedWorkFlow + " loaded for ICN Content-Capture Information details of "+ actualWorkFlow, 
	    		"Should see ICN Content-Capture Information details of "+ expectedWorkFlow + " to load for "+ actualWorkFlow,
	    		"Check ICN Content-Capture Information details of "+ actualWorkFlow);
		}
		
		if(checkFlag)
		{
			Component.assertTrue(false, "Required ICN Content from "+ expectedWorkFlow + " not properly loaded in ICN content of "+ actualWorkFlow);
			msgMA02InclearingFlowLog.error("Required ICN Content from "+ expectedWorkFlow + " not properly loaded in ICN content of "+ actualWorkFlow);
		}else{
			msgMA02InclearingFlowLog.info("Required ICN Content from "+ expectedWorkFlow + " properly loaded in ICN content of "+ actualWorkFlow);
		}
	}

	/**
	 * This method to validate ICN Content Entities details loaded in Source Table for MSgMA02 with FRED Data
	 * @param actualWorkFlow
	 * @param expectedWorkFlow
	 */
	private void checkEntitiesSectionOfMsg06MA02(String actualWorkFlow, String expectedWorkFlow)
	{
		do
		{
			tempICNExpectedData = entitesDataMsgFM01Inclearing.substring(entitesDataMsgFM01Inclearing.indexOf("<Entity>")+ "<Entity>".length(), entitesDataMsgFM01Inclearing.indexOf("</Entity>"));
			tempICNAcutalData = entitiesDataMsgMA02Inclearing.substring(entitiesDataMsgMA02Inclearing.indexOf("<Entity>")+ "<Entity>".length(), entitiesDataMsgMA02Inclearing.indexOf("</Entity>"));

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

			tempICNAcutalData = tempICNAcutalData.substring(0, tempICNAcutalData.indexOf("<SourceDateTime>")).trim();			
			tempICNExpectedData = tempICNExpectedData.substring(0, tempICNExpectedData.indexOf("<SourceDateTime>")).trim();			
			
			if(tempICNActualEntityId.equals(tempICNExpectedEntityId))
			{
				String actualStateDivision = tempICNAcutalData.substring(tempICNAcutalData.indexOf("<StateRevision>") + "<StateRevision>".length(), tempICNAcutalData.indexOf("</StateRevision>")).trim();
				int tempStateDivisionIndex = actualStateDivision.indexOf(">");
				actualStateDivision = actualStateDivision.substring(++tempStateDivisionIndex);
							
				tempICNAcutalData = tempICNAcutalData.substring(0, tempICNAcutalData.indexOf("<StateRevision>")).trim();			
				if(tempICNActualEntitySourceDate.equals(tempICNExpectedEntitySourceDate))
				{
					checkFlag = true;
					validationErrorInformation("Error in SourceId Section of EntityId "+ tempICNActualEntityId + " SourceDateTime of " + actualWorkFlow + " should not match with "+ expectedWorkFlow);
					msgMA02InclearingFlowLog.error("Error in SourceId Section of EntityId "+ tempICNActualEntityId + " SourceDateTime of " + actualWorkFlow + " should not match with "+ expectedWorkFlow);					
				}
			}else {
				checkFlag = true;
				validationErrorInformation("Entity Section for EntityId "+ tempICNActualEntityId + " not available in "+ actualWorkFlow);
				msgMA02InclearingFlowLog.error("Entity Section for EntityId "+ tempICNActualEntityId + " not available in "+ actualWorkFlow);
			}
			entitesDataMsgFM01Inclearing = entitesDataMsgFM01Inclearing.substring(entitesDataMsgFM01Inclearing.indexOf("</Entity>") + "</Entity>".length());
			entitiesDataMsgMA02Inclearing = entitiesDataMsgMA02Inclearing.substring(entitiesDataMsgMA02Inclearing.indexOf("</Entity>") + "</Entity>".length());
		}while(entitesDataMsgFM01Inclearing.indexOf("<Entity>") > 0);
	}
	
	/**
	 * This method is to validate ICN Content ItemsSection details loaded in Source Table for MSgMA02 with FRED Data
	 * @param actualWorkFlow
	 * @param expectedWorkFlow
	 */
	private void checkItemsSectionOfMsg06MA02(String actualWorkFlow, String expectedWorkFlow)
	{
		checkFlag = false;
		do
		{
			tempICNExpectedData = itemsSectionMsgFM01Inclearing.substring(itemsSectionMsgFM01Inclearing.indexOf("<Item>")+ "<Item>".length(), itemsSectionMsgFM01Inclearing.indexOf("</Item>"));
			tempICNAcutalData = itemsSectionMsgMA02Inclearing.substring(itemsSectionMsgMA02Inclearing.indexOf("<Item>")+ "<Item>".length(), itemsSectionMsgMA02Inclearing.indexOf("</Item>"));	

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
					msgMA02InclearingFlowLog.error("CodeLine Section of " + actualWorkFlow + " for ItemId "+ tempICNActualItemId + " not matches with "+ expectedWorkFlow);
				}
			}
			itemsSectionMsgFM01Inclearing = itemsSectionMsgFM01Inclearing.substring(itemsSectionMsgFM01Inclearing.indexOf("</Item>") + "</Item>".length());
			itemsSectionMsgMA02Inclearing = itemsSectionMsgMA02Inclearing.substring(itemsSectionMsgMA02Inclearing.indexOf("</Item>") + "</Item>".length());
		}while(itemsSectionMsgFM01Inclearing.indexOf("<Item>") > 0);
	}
	
	/**
	 * This method is to validate ICN Content CaptureInfo loaded in Source Table for MSgMA02 with FRED Data
	 * @param actualWorkFlow
	 * @param expectedWorkFlow
	 */
	private void checkCaptureInfoOfMsg06MA02(String actualWorkFlow, String expectedWorkFlow)
	{
		checkFlag = false;
		do
		{
			tempICNExpectedData = captureInfoMsgFM01Inclearing.substring(captureInfoMsgFM01Inclearing.indexOf("<CaptureItem>")+ "<CaptureItem>".length(), captureInfoMsgFM01Inclearing.indexOf("</CaptureItem>"));
			tempICNAcutalData = captureInfoMsgMA02Inclearing.substring(captureInfoMsgMA02Inclearing.indexOf("<CaptureItem>")+ "<CaptureItem>".length(), captureInfoMsgMA02Inclearing.indexOf("</CaptureItem>"));	
	
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
				msgMA02InclearingFlowLog.error("CaptureInfo-CaptureItem Section of " + actualWorkFlow + " for ItemId "+ tempICNActualItemId + " not matches with "+ expectedWorkFlow);
			}		
		}while(captureInfoMsgFM01Inclearing.indexOf("<CaptureItem>") > 0);			
	}
}
