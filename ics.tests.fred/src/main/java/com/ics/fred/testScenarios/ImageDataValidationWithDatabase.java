package com.ics.fred.testScenarios;

import java.io.IOException;
import java.sql.ResultSet;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.fred.common.ReadImageValueFromSQLFile;
import com.ics.seleniumCoreUtilis.Component;

//import org.testng.annotations.Test;

/*import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.fred.common.GenericMethods;
import com.ics.seleniumCoreUtilis.Component;
*/
public class ImageDataValidationWithDatabase extends ICSDBUtilis {

	/* Image Data */
//	private static String  COLUMN_TYPE_AS_IMAGE_DT_EXPECTED = "varchar";
		
		
		public static void verifyImageDataFromDatabase(String serverName,String dbName,String dbItemImageDataTypeQuery,String dbImageFetchQuery2,
				String templateFilePath,String templateFileName,String templateFileType) throws Exception{	
		
			/*** Image DataType****/
			
			 String dbSQLFileInjectCommand = "cmd /c sqlcmd -S "+serverName+" -d "+dbName+" -i "+templateFilePath + templateFileName + "_Actual.sql ";		
			 String extractID =  ICSDBUtilis.generateExtractId(templateFileType);		
						 
			 ICSDBUtilis.createFileFromTemplate(templateFilePath, templateFileName,".sql", "STRINGTOBEREPLACEDININPUTFILE", extractID);
			 ICSDBUtilis.sqlCommandExecution(dbSQLFileInjectCommand);
			 System.out.println("(13MF01) SQL File has been loaded into database successfully");
			 String fileName=templateFileName+"_Actual.sql";

			 ReadImageValueFromSQLFile readSQLFile = new ReadImageValueFromSQLFile();
			 Map<String,String> mapTagValuesFromFile =readSQLFile.getTagValue(templateFilePath, fileName);
			 GenericMethods genericObj = new GenericMethods();
			 			 
			 String dbDataLoadedIntoDBQuery = dbImageFetchQuery2+" where ExtractID ='"+extractID+"'";
			 System.out.println("Item Image Column Values of E022 Message is :"+dbDataLoadedIntoDBQuery);
			 ResultSet resultSetColumnValue = getICSDBServerConnection(serverName, dbName, dbDataLoadedIntoDBQuery);

			 Map<String,String> mapTagValuesFromResultSet =genericObj.getMessageItemIdAndImageValue(resultSetColumnValue);
			 
			 for(Map.Entry<String,String> entry:mapTagValuesFromFile.entrySet()){
				 String fileItemId = entry.getKey();
				 String fileImageValue =entry.getValue();
				 String getImageValueFromResultSetByFileItemId = mapTagValuesFromResultSet.get(fileItemId);				 
				// Component.assertEquals(fileImageValue, getImageValueFromResultSetByFileItemId, "Actual and Expected Loaded Image values (05MF01 or 06MF01)of the messages are same.");
				boolean flagImage=Component.verifyEquals(fileImageValue, getImageValueFromResultSetByFileItemId, "Actual and Expected Loaded Image values (05MF01 or 06MF01)of the messages are same.");
				 publishResults(flagImage,fileImageValue,getImageValueFromResultSetByFileItemId,"Actual and expected Image Validation");
			 }
		
		}
}