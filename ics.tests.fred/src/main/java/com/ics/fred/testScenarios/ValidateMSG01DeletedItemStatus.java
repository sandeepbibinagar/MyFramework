package com.ics.fred.testScenarios;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.fred.common.ReadSQLFileUtil;

public class ValidateMSG01DeletedItemStatus extends ICSDBUtilis{

	 public static void validateISOItemId(String dbServerName,String fredDatabaseName,String fredAIXMLTempFolderPath,String strICNFilePath,String aixmlTempFileName,String strICNXMLFileName,String strAIXMLFilePath,String strAIXMLArchiveFldrPathKey) throws Exception{
		 
		 //validate itemid in icn if deleted item flag true
		 
		 //validate itemid in iso if deleted item flag true
		 boolean flag =ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileMovedToArchieveFldr(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
			if(flag){
				try{
					
					String startAIXMLItemIdTag="<cf_ICSItemID>";
					String endTagAIXMLItemIdTag="</cf_ICSItemID>";
					 String srtIsDeletedtag ="<IsDeleted>";
					 String endIsDeletedtag ="</IsDeleted>";
					 String startItemIdTag="<ItemId>";
						String endItemIdTag="</ItemId>";
						String itemIdAIXMLVal,itemIdVal,crItemIdVal ;
						String startISOItemIdTag="<CdtItmId>";
						String endTagISOItemIdTag="</CdtItmId>";
						
						ValidateAIXMLExtractFileLoadedIntoDatabase.validateAIXMLFileLoadedIntoDabatase(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
						String icnOutputFile = ValidateAIXMLExtractFileLoadedIntoDatabase.getICNOutputFileFromDatabase(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
						System.out.println("ICNOutputFilename :"+strICNFilePath+icnOutputFile);
						String deletedAIXMLVal = ReadSQLFileUtil.getSQLFileTagValues(fredAIXMLTempFolderPath,aixmlTempFileName+".xml",srtIsDeletedtag,endIsDeletedtag);
						System.out.println("deletedAIXMLVal "+deletedAIXMLVal);
						if(deletedAIXMLVal.equals("0") || deletedAIXMLVal.equals("true") ){
							 itemIdAIXMLVal = ReadSQLFileUtil.getSQLFileTagValues(fredAIXMLTempFolderPath,aixmlTempFileName+".xml",startAIXMLItemIdTag,endTagAIXMLItemIdTag);
							 itemIdVal = ReadSQLFileUtil.getSQLFileTagValues(strICNFilePath,icnOutputFile+".xml",startItemIdTag,endItemIdTag);
								System.out.println("ICNOutputFilename EnityState Value :"+itemIdVal);
								
								 crItemIdVal = ReadSQLFileUtil.getSQLFileTagValues(strICNFilePath,icnOutputFile+".xml",startISOItemIdTag,endTagISOItemIdTag);
								 System.out.println("ICNOutputFile  Value :"+crItemIdVal);
						          if(itemIdVal.equals(crItemIdVal)){
						        	  System.out.println("Issue found!!");
						          }
						          else
						          {
						        	  System.out.println(" Item Id is not present in MSG01 message file");
						          }
						}
						else
						{
							System.out.println("No need to validate icn");
						}
						
						 
						
				}
				catch(Exception e){
						System.out.println(e.getMessage());
						validationErrorInformation(e.getMessage());
					}
				}
			else 
			{
				System.out.println("File not moved into archive");
				//check errror log
				ValidateAIXMLExtractFileLoadedIntoDatabase.verifyErrorLog06MF01(dbServerName,fredDatabaseName,fredAIXMLTempFolderPath,strICNFilePath,aixmlTempFileName,strICNXMLFileName,strAIXMLFilePath,strAIXMLArchiveFldrPathKey);
				
			}
	 }
	 
	 
	
}
