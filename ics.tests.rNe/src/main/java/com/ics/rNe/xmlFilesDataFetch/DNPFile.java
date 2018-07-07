package com.ics.rNe.xmlFilesDataFetch;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.jetty.html.Input;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.gargoylesoftware.htmlunit.javascript.host.file.FileReader;
import com.gargoylesoftware.htmlunit.util.StringUtils;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;

/**
 * @author Himanshu.Malhotra@ipsl.co.uk
 *
 */
public class DNPFile extends GenericMethodUtilis{
	
	//ArrayList For Row Count
//	static ArrayList<Integer> rc=new ArrayList<Integer>();
	public static int initialSize = 0;
	static int finalSize = 0;
	static String fileNameFromDB="ICS_POST_20170623_0019_01_T";

	static ArrayList<String> recordList=new ArrayList<>();

	//Posting Header Attributes
	public static String schemaTagName = "Schema";
	public static String procDateTagName = "ProcDate";
	public static String participantTagName = "Participant";
	public static String sequenceTagName = "Sequence";
	public static String versionTagName = "Version";
	public static String fileDateTagName = "FileDate";
	public static String environmentTagName = "Environment";
	public static String currencyTagName = "Currency";
	public static String weekdayTagName = "Weekday";
	
	//File Path and Name for different .sql and XML file types
		public static String filePathDEB = GenericMethodUtilis.getrNeResourceFile().getString("filePathDEB");		

		public static String filePTMR01Path = GenericMethodUtilis.getrNeResourceFile().getString("filePTMR01Path");
		public static String filePTMR01Name = GenericMethodUtilis.getrNeResourceFile().getString("filePTMR01Name");
		
		public static String filePERM01Path = GenericMethodUtilis.getrNeResourceFile().getString("filePERM01Path");
		public static String filePERM01Name = GenericMethodUtilis.getrNeResourceFile().getString("filePERM01Name");		

		private static String filePathDEB1 = GenericMethodUtilis.getrNeResourceFile().getString("filePathDEB1");
		private static String fileNameDEB1=  GenericMethodUtilis.getrNeResourceFile().getString("fileNameDEB1");

		private static String filePathDEB2 = GenericMethodUtilis.getrNeResourceFile().getString("filePathDEB2");
		private static String fileNameDEB2=  GenericMethodUtilis.getrNeResourceFile().getString("fileNameDEB2");

		private static String filePathDEB3 = GenericMethodUtilis.getrNeResourceFile().getString("filePathDEB3");
		private static String fileNameDEB3=  GenericMethodUtilis.getrNeResourceFile().getString("fileNameDEB3");

		private static String filePathDEB4 = GenericMethodUtilis.getrNeResourceFile().getString("filePathDEB4");
		private static String fileNameDEB4=  GenericMethodUtilis.getrNeResourceFile().getString("fileNameDEB4");

		private static String filePathDEB5 = GenericMethodUtilis.getrNeResourceFile().getString("filePathDEB5");
		private static String fileNameDEB5=  GenericMethodUtilis.getrNeResourceFile().getString("fileNameDEB5");
		
		private static String filePathDEB6 = GenericMethodUtilis.getrNeResourceFile().getString("filePathDEB6");
		private static String fileNameDEB6=  GenericMethodUtilis.getrNeResourceFile().getString("fileNameDEB6");
		
		private static String filePathDEB7 = GenericMethodUtilis.getrNeResourceFile().getString("filePathDEB7");
		private static String fileNameDEB7=  GenericMethodUtilis.getrNeResourceFile().getString("fileNameDEB7");
		
		private static String dbArchiveServerName = GenericMethodUtilis.getrNeResourceFile().getString("dbArchiveServerName");
		private static String rNeArchiveDatabaseName = GenericMethodUtilis.getrNeResourceFile().getString("rNeArchiveDatabaseName");

		
		public static String firstMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePathDEB1 + fileNameDEB1 + "_Actual.sql ";

		public static String secondMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePathDEB2 + fileNameDEB2 + "_Actual.sql ";

		public static String thirdMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePathDEB3 + fileNameDEB3 + "_Actual.sql ";

		public static String fourthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePathDEB4 + fileNameDEB4 + "_Actual.sql ";

		public static String fifthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePathDEB5 + fileNameDEB5 + "_Actual.sql ";
		
		public static String sixthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePathDEB6 + fileNameDEB6 + "_Actual.sql ";
		
		public static String seventhMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePathDEB7 + fileNameDEB7 + "_Actual.sql ";
		
		
		public static String PostingResponseHeader = "Header";
		
		public static HashMap<String, String> responseHeaderSetTagWiseData;
		
		public static void getTagwiseDataForPostingExtract() throws SAXException, IOException, ParserConfigurationException
		{	

			NodeList nodeSetList = getNodeSetList(filePathDEB,fileNameFromDB+".xml",PostingResponseHeader);

			responseHeaderSetTagWiseData = new LinkedHashMap<String, String>();
			for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
			{

				Node eachNode = nodeSetList.item(eachNodeSetEntry);

				if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
				{
					System.out.println("in if");
					Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));

					responseHeaderSetTagWiseData.put(schemaTagName, eachElement.getElementsByTagName(schemaTagName).item(0).getTextContent());	
					System.out.println("Test : "+ responseHeaderSetTagWiseData.get("Schema"));
					responseHeaderSetTagWiseData.put(procDateTagName, eachElement.getElementsByTagName(procDateTagName).item(0).getTextContent());	
					System.out.println("Test : "+ responseHeaderSetTagWiseData.get("ProcDate"));
					responseHeaderSetTagWiseData.put(participantTagName, eachElement.getElementsByTagName(participantTagName).item(0).getTextContent());					
					System.out.println("Test : "+ responseHeaderSetTagWiseData.get("Participant"));
					responseHeaderSetTagWiseData.put(sequenceTagName, eachElement.getElementsByTagName(sequenceTagName).item(0).getTextContent());	
					System.out.println("Test : "+ responseHeaderSetTagWiseData.get("Sequence"));
					responseHeaderSetTagWiseData.put(versionTagName, eachElement.getElementsByTagName(versionTagName).item(0).getTextContent());		
					System.out.println("Test : "+ responseHeaderSetTagWiseData.get("Version"));
					responseHeaderSetTagWiseData.put(fileDateTagName, eachElement.getElementsByTagName(fileDateTagName).item(0).getTextContent());	
					System.out.println("Test : "+ responseHeaderSetTagWiseData.get("FileDate"));
					responseHeaderSetTagWiseData.put(environmentTagName, eachElement.getElementsByTagName(environmentTagName).item(0).getTextContent());
					System.out.println("Test : "+ responseHeaderSetTagWiseData.get("Environment"));
					responseHeaderSetTagWiseData.put(currencyTagName, eachElement.getElementsByTagName(currencyTagName).item(0).getTextContent());	
					System.out.println("Test : "+ responseHeaderSetTagWiseData.get("Currency"));
					responseHeaderSetTagWiseData.put(weekdayTagName, eachElement.getElementsByTagName(weekdayTagName).item(0).getTextContent());									
					System.out.println("Test : "+ responseHeaderSetTagWiseData.get("Weekday"));

				}
			}
		}
		
		private static NodeList getNodeSetList(String filePathDEB, String fileNameDEB, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
		{
			return getParsedXMLData(getFileStreamWithoutExtension(filePathDEB, fileNameDEB)).getElementsByTagName(elementsSetWithTagName);		
		}	
		
		
		public static void getAndValidatefileNameDEBFromRootDirectory() throws Exception
		{
			boolean flag=false;
			String extractfileNameDEB=null;
			String pattern="(ICSPST[LT][0-9]{8}[0-9]{4}[0-9]{2}.xml)";
			try{

				File folder=new File(GenericMethodUtilis.getrNeResourceFile().getString("localPathForExtractFile"));
				File[] listOfFiles=folder.listFiles();
				for (int i=0;i<listOfFiles.length;i++){
					if(listOfFiles[i].isFile()){
						System.out.println("File"+listOfFiles[i].getName());
						extractfileNameDEB=listOfFiles[i].getName();

						if(extractfileNameDEB.matches(pattern)){

							flag=true;
							publishResults(flag,extractfileNameDEB,"fileNameDEB is Correct","Extract fileNameDEB Validation");
						}
						else{
							publishResults(flag,extractfileNameDEB,"fileNameDEB Should be as per Pattern","Extract fileNameDEB Validation");
						}


					}
					else if(listOfFiles[i].isDirectory()){
						System.out.println("Dicrectory"+listOfFiles[i].getName());
					}
				}


			}

			catch(Exception e){
				System.out.println("Unhandled Exception");
			}
		}
		
		public static boolean validatePTMR01Status() throws Exception
		{
			boolean flag = false;
			String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
			String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");		

			String query = " select top 1 status from Posting.RNEMOQueueDetails "
					+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
					+ " and PostingId in ('" + readDataFromPTMR01().get(1)+"')"
					+ " order by 1 desc";
			System.out.println(query + " -------------------------------query");
			ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(server, database, query);

			int count = 0;
			while(rs.next())
			{
				count++;
			}
			System.out.println("count=" + count);

			if(count == 0)
			{
				System.out.println("No records found");
				publishResults(flag," 0 records Found","Record Should be present","Record Count Validation for PTMR01 status ");
			}
			else{
				rs.beforeFirst();

				while(rs.next())
				{
					if(rs.getString(1).equalsIgnoreCase("C")){
						flag= true;
						System.out.println("Status is C");
						publishResults(flag,rs.getString(1),"Status Should be C"," PTMR01 Status Validation");
					}
					else{
						flag=false;
						System.out.println("Status is not C");
						publishResults(flag,rs.getString(1),"Status Should be C"," PTMR01 Status Validation");
					}
				}
			}
			return flag;
		}
		
		public static void copyExtractFileFromSharedToLocalFolder() throws Exception
		{
			String[] children =  new File(GenericMethodUtilis.getrNeResourceFile().getString("configPathForExtractFile")).list();
			String fileIDFromTable=null;
			boolean flag=false;
			
			String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
			String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");
			String query = " select top 1 * from Posting.RNEMOQueueDetails "
					+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
					+ " and PostingId in ('" + readDataFromPTMR01().get(1)+"')"
					+ " order by 1 desc";

			System.out.println(query);

			ResultSet rs =  ICSProductDBConnection.getICSDBServerConnection(server, database, query);

			while(rs.next()){

				fileIDFromTable=rs.getString(20)+".xml";
				for(int i = 0 ; i <children.length; i++)
				{
					String fileNameDEB = GenericMethodUtilis.getrNeResourceFile().getString("configPathForDEBFile") + children[i];
					if(children[i].equalsIgnoreCase(fileIDFromTable))
					{
						flag=true;
						FileUtils.copyFileToDirectory(new File(fileNameDEB), new File(GenericMethodUtilis.getrNeResourceFile().getString("localPathForDEBFile")));
						publishResults(flag,"File COPIED to local","DEBFile found and copied to local","DEB File fetch and copy");

					}
					else{
						flag=false;
						publishResults(flag,"File NOT Copied to local","DEBFile found and copied to local","DEB File fetch and copy");
					}
					
				}		

			
			}
		}
		
		public static int getRowCountFromBaseCoreTable1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
		{
			int size = 0;
			String server = GenericMethodUtilis.getrNeResourceFile().getString("dbArchiveServerName");
			String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeArchiveDatabaseName");
			String query = " select count(*) from Base.Core";


			ResultSet rs =  ICSProductDBConnection.getICSDBServerConnection(server, database, query);

			try{

				while(rs.next())
				{
					size = rs.getInt(1);
				}

				System.out.println("Method1 initial size"+size);
			}

			catch(Exception e)
			{
				System.out.println("Error in getting row cout");
			}

			return size;		
		}

		public static boolean compareRowCount1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
		{
			boolean flag=false;
			finalSize=getRowCountFromBaseCoreTable1();	

			try{

				int expectedSize=initialSize +1;

				if(finalSize>initialSize){

					flag=true;
					System.out.println("Data Loaded SuccessFully");

					publishResults(flag,Integer.toString(finalSize),Integer.toString(expectedSize), "Row Count Comparison");
				}
				else{
					System.out.println("Incorrect Data");
					publishResults(flag,Integer.toString(finalSize),Integer.toString(expectedSize), "Row Count Comparison");
				}

			}

			catch(Exception e)
			{
				System.out.println("Error in getting row cout");

			}

			return flag;		
		}
		
		public static ArrayList<String> readDataFromPTMR01() throws SAXException, IOException, ParserConfigurationException
		{	
			ArrayList<String> ar=new ArrayList<String>();
			String extractIdValue=null;
			String postingExtractIdvalue=null;
			FileInputStream inputStream= new FileInputStream(GenericMethodUtilis.getrNeResourceFile().getString("DEBptmr01Path"));
			try {
				String all=IOUtils.toString(inputStream);

				int i=all.indexOf("<ExtractId>");
				int j=all.indexOf("<PostingExtractId>");
				int k=all.indexOf("</ExtractId>");
				int m=all.indexOf("</PostingExtractId>");
				String extract1=(String) all.subSequence(i+11,k);
				String extract2=(String) all.subSequence(j+18,m);
				extractIdValue=extract1;
				postingExtractIdvalue=extract2;			
				String extractId=(String) all.subSequence(i+11, i+25+12);
				String postingExtractId=(String) all.subSequence(j+18,j+25+12+7);			
				DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
				InputSource src= new InputSource();
				src.setCharacterStream(new StringReader(all));
				ar.add(extractIdValue);
				ar.add(postingExtractIdvalue);

				//Document doc =builder.parse(src);

			}
			finally{
				inputStream.close();
			}

			return ar;

		}
		
		public static ArrayList<String> getRecordsFromExtractXML() throws SAXException, IOException, ParserConfigurationException, Exception
		{
			ArrayList<String> ar=new ArrayList<String>();
			
			String sequence1=null;

			String sequence2=null;
			String postType1=null;
			String subType1=null;
			String sourceMsg1=null;
			String channel1=null;
			String entryType1=null;
			String postingSource1=null;
			String respIDSource1=null;
			String postDay1=null;
			String clearDate1=null;
			String settDate1=null;
			String captDate1=null;
			String amount1=null;
			String tranSetID1=null;
			String override1=null;
			String nPASort1=null;
			String collLocn1=null;
			String collPart1=null;

			String transIDDR1=null;
			String sortCodeDR1=null;
			String accNumDR1=null;
			String serNumDR1=null;
			String tranCodeDR1=null;
			String switchSortDR1=null;
			String switchAccDR1=null;

			String transIDCR1=null;
			String sortCodeCR1=null;
			String accNumCR1=null;
			String refCR1=null;
			String beneficiaryCR1=null;

			String sequence3=null;
			String postType2=null;
			String subType2=null;
			String sourceMsg2=null;
			String channel2=null;
			String entryType2=null;
			String postingSource2=null;
			String respIDSource2=null;
			String postDay2=null;
			String clearDate2=null;
			String settDate2=null;
			String captDate2=null;
			String amount2=null;
			String tranSetID2=null;
			String override2=null;
			String nPASort2=null;
			String collLocn2=null;
			String collPart2=null;

			String transIDDR2=null;
			String sortCodeDR2=null;
			String accNumDR2=null;
			String serNumDR2=null;
			String tranCodeDR2=null;
			String switchSortDR2=null;
			String switchAccDR2=null;

			String transIDCR2=null;
			String sortCodeCR2=null;
			String refCR2=null;
			String accNumCR2=null;
			String beneficiaryCR2=null;

			ArrayList<String> recordSet1= new ArrayList<String>();		
			recordSet1= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//Sequence"),"Sequence");
			sequence1=recordSet1.get(1);
			sequence2=recordSet1.get(2);
			//sequence3=recordSet1.get(2);

			ArrayList<String> recordSet2= new ArrayList<String>();		
			recordSet2= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//PostType"),"PostType");
			postType1=recordSet2.get(0);
			postType2=recordSet2.get(1);

			ArrayList<String> recordSet3= new ArrayList<String>();		
			recordSet3= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//SubType"),"SubType");
			subType1=recordSet3.get(0);
			subType2=recordSet3.get(1);

			ArrayList<String> recordSet4= new ArrayList<String>();		
			recordSet4= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//SourceMsg"),"SourceMsg");
			sourceMsg1=recordSet4.get(0);
			sourceMsg2=recordSet4.get(1);

			ArrayList<String> recordSet5= new ArrayList<String>();		
			recordSet5= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//Channel"),"Channel");
			channel1=recordSet5.get(0);
			channel2=recordSet5.get(1);

			ArrayList<String> recordSet6= new ArrayList<String>();		
			recordSet6= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//EntryType"),"EntryType");
			entryType1=recordSet6.get(0);
			entryType2=recordSet6.get(1);

			ArrayList<String> recordSet7= new ArrayList<String>();		
			recordSet7= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//PostingSource"),"PostingSource");
			postingSource1=recordSet7.get(0);
			postingSource2=recordSet7.get(1);

			ArrayList<String> recordSet8= new ArrayList<String>();		
			recordSet8= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//RespIDSource"),"RespIDSource");
			respIDSource1=recordSet8.get(0);
			respIDSource2=recordSet8.get(1);

			ArrayList<String> recordSet9= new ArrayList<String>();		
			recordSet9= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//ClearDate"),"ClearDate");
			clearDate1=recordSet9.get(0);
			clearDate2=recordSet9.get(1);

			ArrayList<String> recordSet10= new ArrayList<String>();		
			recordSet10= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//SettDate"),"SettDate");
			settDate1=recordSet10.get(0);
			settDate2=recordSet10.get(1);

			ArrayList<String> recordSet11= new ArrayList<String>();		
			recordSet11= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//CaptDate"),"CaptDate");
			captDate1=recordSet11.get(0);
			captDate2=recordSet11.get(1);

			ArrayList<String> recordSet12= new ArrayList<String>();		
			recordSet12= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//Amount"),"Amount");
			amount1=recordSet12.get(0);
			amount2=recordSet12.get(1);

			ArrayList<String> recordSet13= new ArrayList<String>();		
			recordSet13= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//TranSetID"),"TranSetID");
			tranSetID1=recordSet13.get(0);
			tranSetID2=recordSet13.get(1);
			
			ArrayList<String> recordSet14= new ArrayList<String>();		
			recordSet14= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//PostDay"),"PostDay");
			postDay1=recordSet14.get(0);
			postDay2=recordSet14.get(1);
			
			

			ArrayList<String> recordSet15= new ArrayList<String>();		
			recordSet15= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//Override"),"Override");
			override1=recordSet15.get(0);
			override2=recordSet15.get(1);

			ArrayList<String> recordSet16= new ArrayList<String>();		
			recordSet16= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//CollPart"),"CollPart");
			collPart1=recordSet16.get(0);
			collPart2=recordSet16.get(1);


			ArrayList<String> recordSet17= new ArrayList<String>();		
			recordSet17= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//CollLocn"),"CollLocn");
			collLocn1=recordSet17.get(0);
			collLocn2=recordSet17.get(1);


			ArrayList<String> recordSet18= new ArrayList<String>();		
			recordSet18= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//TransID"),"TransID");
			transIDDR1=recordSet18.get(0);
			transIDCR1=recordSet18.get(1);
			transIDDR2=recordSet18.get(2);
			transIDCR2=recordSet18.get(3);

			ArrayList<String> recordSet19= new ArrayList<String>();		
			recordSet19= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//SortCode"),"SortCode");
			sortCodeDR1=recordSet19.get(0);
			sortCodeCR1=recordSet19.get(1);
			sortCodeDR2=recordSet19.get(2);
			sortCodeCR2=recordSet19.get(3);

			ArrayList<String> recordSet20= new ArrayList<String>();		
			recordSet20= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//AccNum"),"AccNum");
			accNumDR1=recordSet20.get(0);
			accNumCR1=recordSet20.get(1);
			accNumDR2=recordSet20.get(0);
			accNumCR2=recordSet20.get(1);

			ArrayList<String> recordSet21= new ArrayList<String>();		
			recordSet21= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//SerNum"),"SerNum");
			serNumDR1=recordSet21.get(0);
			serNumDR2=recordSet21.get(1);

			ArrayList<String> recordSet22= new ArrayList<String>();		
			recordSet22= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//TranCode"),"TranCode");
			tranCodeDR1=recordSet22.get(0);
			tranCodeDR2=recordSet22.get(1);
			

			ArrayList<String> recordSet23= new ArrayList<String>();		
			recordSet23= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//SwitchSort"),"SwitchSort");
			switchSortDR1=recordSet23.get(0);
			switchSortDR2=recordSet23.get(1);

			ArrayList<String> recordSet24= new ArrayList<String>();		
			recordSet24= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//SwitchAcc"),"SwitchAcc");
			switchAccDR1=recordSet24.get(0);
			switchAccDR2=recordSet24.get(1);			

			ArrayList<String> recordSet25= new ArrayList<String>();		
			recordSet25= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//Ref"),"Ref");
			refCR1=recordSet25.get(0);
			refCR2=recordSet25.get(1);

//			ArrayList<String> recordSet27= new ArrayList<String>();		
//			recordSet27= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePathDEB,fileNameFromDB+".xml","//Beneficiary"),"Beneficiary");
//			beneficiaryCR1=recordSet27.get(0);
//			beneficiaryCR1=recordSet27.get(1);	
			
				

			ar.add(sequence1);//0
			ar.add(sequence2);//1
			ar.add(postType1);//2
			ar.add(postType2);//3
			ar.add(subType1);//4
			ar.add(subType2);//5
			ar.add(sourceMsg1);//6
			ar.add(sourceMsg2);//7
			ar.add(channel1);//8
			ar.add(channel2);//9
			ar.add(entryType1);//10
			ar.add(entryType2);//11
			ar.add(postingSource1);//12
			ar.add(postingSource2);//13
			ar.add(respIDSource1);//14
			ar.add(respIDSource2);//15
			ar.add(postDay1);//16
			ar.add(postDay2);//17
			ar.add(clearDate1);//18
			ar.add(clearDate2);//19
			ar.add(settDate1);//20
			ar.add(settDate2);//21
			ar.add(captDate1);//22
			ar.add(captDate2);//23
			ar.add(amount1);//24
			ar.add(amount2);//25
			ar.add(tranSetID1);//26
			ar.add(tranSetID2);//27	
			ar.add(override1);//28
			ar.add(override2);//29
			ar.add(collPart1);//30
			ar.add(collPart2);//31
			ar.add(collLocn1);//32
			ar.add(collLocn2);//33
		
			
			ar.add(transIDDR1);//34
			ar.add(transIDDR2);//35
			ar.add(transIDCR1);//36
			ar.add(transIDCR2);//37			
			
			ar.add(sortCodeDR1);//38
			ar.add(sortCodeDR2);//39
			ar.add(sortCodeCR1);//40
			ar.add(sortCodeCR2);//41
			
			ar.add(accNumDR1);//42			
			ar.add(accNumDR2);//43
			ar.add(accNumCR1);//44
			ar.add(accNumCR2);//45
			
			ar.add(serNumDR1);//46
			ar.add(serNumDR2);//47
			
			ar.add(tranCodeDR1);//48
			ar.add(tranCodeDR2);//49
			
			ar.add(switchSortDR1);//50
			ar.add(switchSortDR2);//51
			
			ar.add(switchAccDR1);//52
			ar.add(switchAccDR2);	//53		
					
			ar.add(refCR1);//54
			ar.add(refCR2);//55
			
//			ar.add(beneficiaryCR1);//56	
//			ar.add(beneficiaryCR2);//57
			
			

			System.out.println(ar);

			return ar;

		}
		
		public static void getAndValidateFileNameFromRootDirectory() throws Exception
		{
			boolean flag=false;
			String extractFileName=null;
			String pattern="(ICSPST[LT][0-9]{8}[0-9]{4}[0-9]{2}.xml)";
			try{

				File folder=new File(GenericMethodUtilis.getrNeResourceFile().getString("localPathForDEBFile"));
				File[] listOfFiles=folder.listFiles();
				for (int i=0;i<listOfFiles.length;i++){
					if(listOfFiles[i].isFile()){
						System.out.println("File"+listOfFiles[i].getName());
						extractFileName=listOfFiles[i].getName();

						if(extractFileName.matches(pattern)){

							flag=true;
							publishResults(flag,extractFileName,"FileName is Correct","DEB FileName Validation");
						}
						else{
							publishResults(flag,extractFileName,"FileName Should be as per Pattern","DEB FileName Validation");
						}


					}
					else if(listOfFiles[i].isDirectory()){
						System.out.println("Dicrectory"+listOfFiles[i].getName());
					}
				}


			}

			catch(Exception e){
				System.out.println("Unhandled Exception");
			}
		}
		
		public static String validateRnEMOQueueDetailsTableFileID() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, SAXException, IOException, ParserConfigurationException
		{
			boolean flag=false;
		
			String pattern="(ICSPST[LT][0-9]{8}[0-9]{4}[0-9]{2})";
			String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
			String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");
			String query = " select top 1 * from Posting.RNEMOQueueDetails "
					+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
					+ " and PostingId in ('" + readDataFromPTMR01().get(1)+"')"
					+ " order by 1 desc";

			System.out.println(query);

			ResultSet rs =  ICSProductDBConnection.getICSDBServerConnection(server, database, query);
			

			while(rs.next()){

				if (rs.getString(20)==null){
					publishResults(flag,"Empty Sets","Should not be null", "FileID validation from Table");
				}		
				else if(rs.getString(20).matches(pattern)){
					flag=true;
					fileNameFromDB=rs.getString(20);
					publishResults(flag,rs.getString(20),"FileName is as expected", "FileID Validation from Table");
					
				}
			}
			
			return fileNameFromDB;

		}
		
		public static ResultSet validatePostingResponseHeaderXMLAttributes() throws Exception
		{
			String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
			String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");			
			String query = " select top 1 * from Posting.RNEPostingExtract "
					
					+ " order by 1 desc";

			ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(server, database, query);		
			return rs;
		}
		
		public static ArrayList<String> getEntitySetsFromPERM01() throws SAXException, IOException, ParserConfigurationException, Exception
		{
			ArrayList<String> ar=new ArrayList<String>();

			String entityType1=null;
			String entityId1=null;
			String entityState1=null;

			String entityType2=null;
			String entityId2=null;
			String entityState2=null;

			String entityType3=null;
			String entityId3=null;
			String entityState3=null;

			String entityType4=null;
			String entityId4=null;
			String entityState4=null;
			
			String entityType5=null;
			String entityId5=null;
			String entityState5=null;
			
			String entityType6=null;
			String entityId6=null;
			String entityState6=null;

			ArrayList<String> entitySet1= new ArrayList<String>();		
			entitySet1= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name,"//EntityType"),"EntityType");
			System.out.println(entitySet1);	
			entityType1=entitySet1.get(0);
			entityType2=entitySet1.get(1);
			entityType3=entitySet1.get(2);
			entityType4=entitySet1.get(3);
			entityType5=entitySet1.get(4);
			entityType6=entitySet1.get(5);
			

			ArrayList<String> entitySet2= new ArrayList<String>();		
			entitySet2= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name,"//EntityId"),"EntityId");
			System.out.println(entitySet2);	
			entityId1=entitySet2.get(0);
			entityId2=entitySet2.get(1);
			entityId3=entitySet2.get(2);
			entityId4=entitySet2.get(3);
			entityId5=entitySet2.get(4);
			entityId6=entitySet2.get(5);

			ArrayList<String> entitySet3= new ArrayList<String>();		
			entitySet3= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name,"//EntityState"),"EntityState");
			System.out.println(entitySet3);	
			entityState1=entitySet3.get(0);
			entityState2=entitySet3.get(1);
			entityState3=entitySet3.get(2);
			entityState4=entitySet3.get(3);
			entityState5=entitySet3.get(4);
			entityState6=entitySet3.get(5);

			ar.add(entityType1);
			ar.add(entityId1);
			ar.add(entityState1);

			ar.add(entityType2);
			ar.add(entityId2);
			ar.add(entityState2);

			ar.add(entityType3);
			ar.add(entityId3);
			ar.add(entityState3);

			ar.add(entityType4);
			ar.add(entityId4);
			ar.add(entityState4);
			
			ar.add(entityType5);
			ar.add(entityId5);
			ar.add(entityState5);
			
			ar.add(entityType6);
			ar.add(entityId6);
			ar.add(entityState6);
			
			

			return ar;

		}
		
		public static void getPERM01XML() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException, SAXException, IOException, ParserConfigurationException
		{
			String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
			String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");
			String query = " select top 1 * from Posting.RNEMOQueueDetails "
					+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
					+ " order by 1 desc";
			String data = "";


			ResultSet rs =  ICSProductDBConnection.getICSDBServerConnection(server, database, query);

			while(rs.next())
			{
				data = rs.getString(15);
			}


			try(PrintWriter pw = new PrintWriter(new File("D:\\RnE_Data\\PERM01\\PERM01.xml")))
			{
				pw.write(data);
				System.out.println("Data written");
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}

		public static boolean validatePTMR01Refresh() throws Exception
		{
			boolean flag = false;
			String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
			String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");		

			String query = " select * from Posting.RNEMOQueueDetails "
					+ "where ExtractId in ('" + readDataFromPTMR01().get(0)+"')"
					+ " and PostingId in ('" + readDataFromPTMR01().get(1)+"')"
					+ " order by 1 desc";
			System.out.println(query + " -------------------------------query");
			ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(server, database, query);
			
			return flag;
		}



}
