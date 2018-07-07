package com.ics.fred.testScenarios;

import java.io.File;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadSQLFileUtil;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class SingleFileInsertionCreditItem extends ICSDBUtilis  {
	public static ArrayList<String> itemID=new ArrayList<String>();
	public static String fredMF06TmpltFilename;
	
	public static void generateAndVerifyX9FileName(String dbServerName, String fredAutoDb, String sqlConfigBusinessDate, 
			String templateFileType, String templateFileName, String templateFilePath,String templateExtractIDToBeReplaced, String templateBusinessDateToBeReplaced, String creditX9FilePath) 
					throws Exception{
		
		ArrayList<String> returnVal = new ArrayList<>();
		String sqlFileInjectCommand = "cmd /c sqlcmd -S "+dbServerName+" -d "+fredAutoDb+" -i "+templateFilePath + templateFileName + "_Actual.sql ";
		String x9fileName=null;
		//create unique Extract id
		String extractID =  generateExtractId(templateFileType);
		
		System.out.println("******** TestCase 62948 : Single Credit Message Insertion and X9 File Generation validation***********");
		
		System.out.println("Extract Id for Credit item: " + extractID);
		
		//Update Extract Id in the template .sql file
		createFileFromTemplate(templateFilePath, templateFileName, ".sql", templateExtractIDToBeReplaced, extractID);	
		if(templateFileType.equals("CRED"))
		{
			x9fileName = extractID+"-CR.icl";
		}
		else
		{
			x9fileName = extractID+"-DR.icl";
		}
		
		//Update Business Date in the template .sql file
		
		//String sqlConfigBusinessDate ="select Value from Config.BusinessConfig where [Key]='BDATE'";
		ResultSet rsConfigBusinessDate = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, sqlConfigBusinessDate);
		String actualConfigDateValue = GenericMethods.getICSRetrieveColumnValues(rsConfigBusinessDate);
		String configDateValue = GenericMethodUtilis.ConvertDateInToRequiredFormat("yyyyMMdd", "yyyy-MM-dd", actualConfigDateValue);
		createFileFromTemplate(templateFilePath, templateFileName+"_Actual", ".sql", templateBusinessDateToBeReplaced, configDateValue);
		
		//Inject sql file in to DB through Command line
		sqlCommandExecution(sqlFileInjectCommand);
		System.out.println("SQL command line execution performed "+sqlFileInjectCommand);
		Thread.sleep(10000);
		fredMF06TmpltFilename = templateFileName+"_Actual.sql";
		//getMessageItemIDFromSQL(dbServerName,fredAutoDb,templateFilePath,fredMF06TmpltFilename,creditX9FilePath);
		//String actualfileName=getRequiredFileName(creditX9FilePath,x9fileName);
		//System.out.println("X9 file for "+templateFileType+"IT item: from X9 folder" + actualfileName);
		
		validateX9File(dbServerName, fredAutoDb,templateFileName, templateFilePath,creditX9FilePath,extractID);
		//Component.assertEquals(actualfileName, x9fileName, "X9 File check performed");
		//	boolean returnExpectedReslt=Component.verifyEquals(actualfileName, x9fileName, "X9 File check performed");
		//delete the unique input file created

	//	return returnVal;
		//GenericMethodUtilis.DeleteFile(templateFilePath, templateFileName+"_Actual", ".sql");
	}
	
	
	public static void validateX9File(String dbServerName, String fredAutoDb, String templateFileName, String templateFilePath,String creditX9FilePath,String extractID)throws Exception{
		ArrayList<String> returnVal = new ArrayList<>();
		String x9FileNameDb=null;
		System.out.println("creditX9FilePath "+creditX9FilePath);
		//itemID=getMessageItemIDFromSQL(templateFilePath);
		//System.out.println("ItemID value from MF06 SQL :"+itemID);
		String query ="SELECT X9FileName FROM [Base].[MF01_Batches] where X9FileName is not null AND ExtractID='"+extractID+"'";
		/*String query = "SELECT DISTINCT X9FileName FROM [Base].[MF01_Batches] B "
				+" JOIN [Base].[MF01_Items] I"
				+" ON B.BatchDetailsID=I.BatchDetailsID"
				+" WHERE X9FileName is not null"
				+" AND I.MessageItemID in ("+getStringFromArrayList(itemID)+")";
		*/	//	+ GenericMethodUtilis.getStringFromArrayList(itemID)+")";
		/*String query = "SELECT DISTINCT X9FileName FROM [Base].[MF01_Batches] B "
				+" JOIN [Base].[MF01_Items] I"
				+" ON B.BatchDetailsID=I.BatchDetailsID"
				+" WHERE X9FileName is not null";
		*/System.out.println("Validate X9 Query"+query);
	try{
		ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, query);
		while (resultSet.next()) {
			x9FileNameDb = resultSet.getString(1);
			System.out.println("X9 FileName present in MF01Batches table :"+x9FileNameDb);
			break;
		}
		String actualfileName=getRequiredFileName(creditX9FilePath,x9FileNameDb);
		String debitX9OutputFile=creditX9FilePath+actualfileName;
		
		boolean checkX9File = new File(debitX9OutputFile).exists();
		System.out.println("checkX9File "+checkX9File);
		boolean flagX9 =Component.verifyTrue(x9FileNameDb!=null, "X9 FileName is present in FRED Database!!");
		
		if(checkX9File){
		publishResults(checkX9File, (x9FileNameDb!=null)?x9FileNameDb+" X9 File got generated at X9 output folder":" X9 File not generated at X9 output folder. and Null is present in database",x9FileNameDb+" X9 File got generated at X9 output folder","X9 File got generated at the specified output location.");
		publishResults(checkX9File, actualfileName, x9FileNameDb, "X9 File got generated at X9 output folder location");
		}
		else{
			publishResults(checkX9File, (x9FileNameDb!=null)?x9FileNameDb+" X9 File got generated at X9 output folder":" X9 File not generated at X9 output folder and Null is present in database",x9FileNameDb+" X9 File got generated at X9 output folder","X9 File got generated at the specified output location.");
			publishResults(x9FileNameDb!=null, "X9 filename is not present at output location", "X9 filename is not present at output location", "Validation for X9 File generation at X9 output folder location");
		
		}
		
		//boolean returnExpectedReslt=Component.verifyTrue(checkX9File, "X9 File is present at the X9 output folder for debit item");
		
	}
	catch(Exception e){
		e.getMessage();
	}
	}
	
		
		public static StringBuilder getStringFromArrayList(ArrayList<String> InputStringArray) throws Exception
		{	
		  StringBuilder inputString= new StringBuilder();
		  for(int i=0;i<InputStringArray.size();i++)
		  {
		  inputString.append("'"+InputStringArray.get(i)+"'");	 
		  if(i<InputStringArray.size()-1)
			  inputString.append(",");
		  }
		  return inputString;
		
		}
		
		public static List<String> getNodeListValues(Map<String, String> aixmlNodeVal,String tagName){
			List<String> listOfString = new ArrayList<String>();
			for(int i=1;i<=aixmlNodeVal.size();i++){
			//	System.out.println("Node size "+aixmlNodeVal.size());
			//	System.out.println("Node values "+aixmlNodeVal.get(tagName+i));
				listOfString.add(aixmlNodeVal.get(tagName+i));
			}
			return listOfString;
		}	

		public static ArrayList<String> getMessageItemIDFromSQL(String templateFilePath) throws Exception{
			ArrayList<String> returnVal = new ArrayList<>();
			String itemIDVal;
			String startTag="<ItemId>";
			String endTag="</ItemId>";
			System.out.println("MF06 SQL File :"+templateFilePath+fredMF06TmpltFilename);
		//	List itemIDValList =getNodeListValues(itemIDVal,startTag);
			/*String count = WordUtils.initials(startTag);
			System.out.println("No. oItemID tag in SQL File:"+count.length());
			for(int i=0;i<count.length();i++){
			itemIDVal = ReadSQLFileUtil.getSQLFileTagValues(templateFilePath,fredMF06TmpltFilename+".sql",startTag,endTag);
			System.out.println("ItemID value is :"+count.length()+" :"+itemIDVal);
			
			System.out.println("MF06 ItemId Value :"+itemIDVal);
			returnVal.add(itemIDVal);
			}		
			System.out.println("Array list of itemIDval :"+returnVal);
			*/
			File f= new File(templateFilePath+fredMF06TmpltFilename);
			try{
			/*Scanner sc=new Scanner(new FileInputStream(fredMF06TmpltFilename));
			
				int cnt=0;
			
			while(sc.hasNext()){
				sc.next();
				cnt++;
			}
			*/
			itemIDVal = ReadSQLFileUtil.getSQLFileTagValues(templateFilePath,fredMF06TmpltFilename,startTag,endTag);
		//	System.out.println("ItemID value is :"+cnt+" :"+itemIDVal);
			
			System.out.println("MF06 ItemId Value :"+itemIDVal);
			returnVal.add(itemIDVal);
			}
			catch(Exception e)
			{
				//
			}
			
			return returnVal;            
		}
		
		public static void validateMF01SendQueue(String dbServerName,String fredAutoDb,String extractID,String creditX9FilePath) throws Exception{
			
			//MF01SendQueue query validation
			ArrayList<String> returnVal = new ArrayList<>();
			String x9FileNameDb=null;
			//itemID=getMessageItemIDFromSQL(templateFilePath);
			//System.out.println("ItemID value from MF06 SQL :"+itemID);
			validationStepInformation("**********Validation of X9File created at the X9 outpout location for ExtractId :"+extractID+"******");
			String query ="SELECT isX9FileCreated FROM [Base].[MF01_Batches] where ExtractID='"+extractID+"'";
			/*String query = "SELECT DISTINCT X9FileName FROM [Base].[MF01_Batches] B "
					+" JOIN [Base].[MF01_Items] I"
					+" ON B.BatchDetailsID=I.BatchDetailsID"
					+" WHERE X9FileName is not null"
					+" AND I.MessageItemID in ("+getStringFromArrayList(itemID)+")";
			*/	//	+ GenericMethodUtilis.getStringFromArrayList(itemID)+")";
			/*String query = "SELECT DISTINCT X9FileName FROM [Base].[MF01_Batches] B "
					+" JOIN [Base].[MF01_Items] I"
					+" ON B.BatchDetailsID=I.BatchDetailsID"
					+" WHERE X9FileName is not null";
			*/
			String isX9FileCrt;
			System.out.println("Validate X9 File is created in database : "+query);
			ResultSet resultSet = ICSProductDBConnection.getICSDBServerConnection(dbServerName, fredAutoDb, query);
			while (resultSet.next()) {
				isX9FileCrt = resultSet.getString(1);
				if(isX9FileCrt!=null)
				System.out.println("X9 FileName present in MF01Batches table :"+x9FileNameDb);
				break;
			}
			
			String actualfileName=getRequiredFileName(creditX9FilePath,x9FileNameDb);
			String debitX9OutputFile=creditX9FilePath+actualfileName;
			//Component.assertTrue(x9FileNameDb!=null, "X9 FileName is present in FRED Database!!");
			boolean checkX9File = new File(debitX9OutputFile).exists();
			boolean returnExpectedReslt=Component.verifyTrue(checkX9File, "X9 File is present at the X9 output folder for debit item");
			publishResults(returnExpectedReslt, actualfileName, x9FileNameDb, "X9 File got generated at X9 output folder location");
		
		}
		

	
}
