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

public class ExtractXML extends GenericMethodUtilis{
	
	    //Posting Header Attributes
		public static String schemaTagName = "Schema";
		public static String participantTagName = "Participant";
		public static String procDateTagName = "ProcDate";		
		public static String sequenceTagName = "Sequence";
		public static String versionTagName = "Version";
		public static String fileDateTagName = "FileDate";
		public static String environmentTagName = "Environment";
		public static String currencyTagName = "Currency";
		public static String weekdayTagName = "Weekday";
		
		//Record Attributes
		public static String sequenceRTagName = "Sequence";
		public static String postTypeTagName = "PostType";
		public static String subTypeTagName = "SubType";
		public static String sourceMsgTagName = "SourceMsg";
		public static String channelTagName = "Channel";
		public static String entryTypeTagName = "EntryType";
		public static String postingSourceTagName = "PostingSource";
		public static String respIDSourceTagName = "RespIDSource";
		public static String postDayTagName = "PostDay";
		public static String clearDateTagName = "ClearDate";
		public static String settDateTagName = "SettDate";
		public static String captDateTagName = "CaptDate";
		public static String amountTagName = "Amount";
		public static String tranSetIDTagName = "TranSetID";
		public static String reasonTagName = "Reason";
		public static String overrideTagName = "Override";
		public static String nPASortTagName = "NPASort";
		public static String numChequesTagName = "NumCheques";
		public static String collPartTagName = "CollPart";
		public static String collLocnTagName = "CollLocn";
		
		//Debit Attributes
		public static String transIdDRTagName = "TransID";
		public static String sortCodeDRTagName = "SortCode";
		public static String accNumDRTagName = "AccNum";
		public static String serNumDRTagName = "SerNum";
		public static String tranCodeDRTagName = "TranCode";
		public static String debitFullAmountDRTagName = "DebitFullAmt";
		public static String switchSortDRTagName = "SwitchSort";
		public static String switchAccDRTagName = "SwitchAcc";
		
		//Credit Attributes
		public static String transIdCRTagName = "TransID";
		public static String sortCodeCRTagName = "SortCode";
		public static String accNumCRTagName = "AccNum";
		public static String refCRTagName = "Ref";
		public static String tranCodeCRTagName = "TranCode";
		public static String origAmtCRTagName = "OrigAmt";
		public static String beneficiaryCRTagName = "Beneficiary";
		
		//File Path and Name for different .sql and XML file types
		public static String filePath = GenericMethodUtilis.getrNeResourceFile().getString("localPathForExtractFile");
		public static String fileName = PostingExtractFile.fileNameFromDB;
		
		//Hashmap for Extract Header Attributes
		public static HashMap<String, String> responseHeaderSetTagWiseData;
		
		//Headers
		public static String PostingResponseHeader = "Header";
		
		public static void getTagwiseDataForPostingExtract() throws SAXException, IOException, ParserConfigurationException
		{	

			System.out.println(filePath + fileName);
			NodeList nodeSetList = getNodeSetList(filePath,fileName+".xml",PostingResponseHeader);

			responseHeaderSetTagWiseData = new LinkedHashMap<String, String>();
			for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
			{

				Node eachNode = nodeSetList.item(eachNodeSetEntry);

				if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
				{
					Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));

					responseHeaderSetTagWiseData.put(schemaTagName, eachElement.getElementsByTagName(schemaTagName).item(0).getTextContent());						
					responseHeaderSetTagWiseData.put(procDateTagName, eachElement.getElementsByTagName(procDateTagName).item(0).getTextContent());					
					responseHeaderSetTagWiseData.put(participantTagName, eachElement.getElementsByTagName(participantTagName).item(0).getTextContent());					
					responseHeaderSetTagWiseData.put(sequenceTagName, eachElement.getElementsByTagName(sequenceTagName).item(0).getTextContent());					
					responseHeaderSetTagWiseData.put(versionTagName, eachElement.getElementsByTagName(versionTagName).item(0).getTextContent());						
					responseHeaderSetTagWiseData.put(fileDateTagName, eachElement.getElementsByTagName(fileDateTagName).item(0).getTextContent());						
					responseHeaderSetTagWiseData.put(environmentTagName, eachElement.getElementsByTagName(environmentTagName).item(0).getTextContent());					
					responseHeaderSetTagWiseData.put(currencyTagName, eachElement.getElementsByTagName(currencyTagName).item(0).getTextContent());						
					responseHeaderSetTagWiseData.put(weekdayTagName, eachElement.getElementsByTagName(weekdayTagName).item(0).getTextContent());
				}
			}
		}
		
		public static ArrayList<String> getRecordsFromExtractXML() throws SAXException, IOException, ParserConfigurationException, Exception
		{
			ArrayList<String> ar=new ArrayList<String>();

			String sequence1=null;
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
			String reason1=null;
			String override1=null;
			String nPASort1=null;
			String numCheques1=null;
			String collPart1=null;
			String collLocn1=null;

			String transIDDR1=null;
			String sortCodeDR1=null;
			String accNumDR1=null;
			String serNumDR1=null;
			String tranCodeDR1=null;
			String switchSortDR1=null;
			String switchAccDR1=null;
			String fullAmtDR1=null;

			String transIDCR1=null;
			String sortCodeCR1=null;
			String accNumCR1=null;
			String refCR1=null;
			String tranCodeCR1=null;
			String origAmtCR1=null;
			String exceptionCR1=null;
			String beneficiaryCR1=null;

//			String sequence2=null;
//			String postType2=null;
//			String subType2=null;
//			String sourceMsg2=null;
//			String channel2=null;
//			String entryType2=null;
//			String postingSource2=null;
//			String respIDSource2=null;
//			String postDay2=null;
//			String clearDate2=null;
//			String settDate2=null;
//			String captDate2=null;
//			String amount2=null;
//			String tranSetID2=null;
//			String reason2=null;
//			String override2=null;
//			String numCheques2=null;
//			String collLocn2=null;
//
//			String transIDDR2=null;
//			String sortCodeDR2=null;
//			String accNumDR2=null;
//			String serNumDR2=null;
//			String tranCodeDR2=null;
//			String switchSortDR2=null;
//			String switchAccDR2=null;
//
//			String transIDCR2=null;
//			String sortCodeCR2=null;
//			String refCR2=null;
//			String accNumCR2=null;
//			String tranCodeCR2=null;
//			String origAmtCR2=null;
//			String beneficiaryCR2=null;




			ArrayList<String> recordSet1= new ArrayList<String>();		
			recordSet1= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//Sequence"),"Sequence");
			sequence1=recordSet1.get(1);
			//sequence2=recordSet1.get(2);

			ArrayList<String> recordSet2= new ArrayList<String>();		
			recordSet2= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//PostType"),"PostType");
			postType1=recordSet2.get(0);
			//postType2=recordSet2.get(1);

			ArrayList<String> recordSet3= new ArrayList<String>();		
			recordSet3= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//SubType"),"SubType");
			subType1=recordSet3.get(0);
			//subType2=recordSet3.get(1);

			ArrayList<String> recordSet4= new ArrayList<String>();		
			recordSet4= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//SourceMsg"),"SourceMsg");
			sourceMsg1=recordSet4.get(0);
			//sourceMsg2=recordSet4.get(1);

			ArrayList<String> recordSet5= new ArrayList<String>();		
			recordSet5= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//Channel"),"Channel");
			channel1=recordSet5.get(0);
			//channel2=recordSet5.get(1);

			ArrayList<String> recordSet6= new ArrayList<String>();		
			recordSet6= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//EntryType"),"EntryType");
			entryType1=recordSet6.get(0);
			//entryType2=recordSet6.get(1);

			ArrayList<String> recordSet7= new ArrayList<String>();		
			recordSet7= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//PostingSource"),"PostingSource");
			postingSource1=recordSet7.get(0);
			//postingSource2=recordSet7.get(1);

			ArrayList<String> recordSet8= new ArrayList<String>();		
			recordSet8= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//RespIDSource"),"RespIDSource");
			respIDSource1=recordSet8.get(0);
			//respIDSource2=recordSet8.get(1);

			ArrayList<String> recordSet9= new ArrayList<String>();		
			recordSet9= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//ClearDate"),"ClearDate");
			clearDate1=recordSet9.get(0);
			//clearDate2=recordSet9.get(1);

			ArrayList<String> recordSet10= new ArrayList<String>();		
			recordSet10= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//SettDate"),"SettDate");
			settDate1=recordSet10.get(0);
			//settDate2=recordSet10.get(1);

			ArrayList<String> recordSet11= new ArrayList<String>();		
			recordSet11= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//CaptDate"),"CaptDate");
			captDate1=recordSet11.get(0);
			//captDate2=recordSet11.get(1);

			ArrayList<String> recordSet12= new ArrayList<String>();		
			recordSet12= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//Amount"),"Amount");
			amount1=recordSet12.get(0);
			//amount2=recordSet12.get(1);

			ArrayList<String> recordSet13= new ArrayList<String>();		
			recordSet13= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//TranSetID"),"TranSetID");
			tranSetID1=recordSet13.get(0);
			//tranSetID2=recordSet13.get(1);

			ArrayList<String> recordSet14= new ArrayList<String>();		
			recordSet14= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//Reason"),"Reason");
			reason1=recordSet14.get(0);


			ArrayList<String> recordSet15= new ArrayList<String>();		
			recordSet15= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//Override"),"Override");
			override1=recordSet15.get(0);
			//override2=recordSet15.get(1);

			ArrayList<String> recordSet16= new ArrayList<String>();		
			recordSet16= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//NumCheques"),"NumCheques");
			numCheques1=recordSet16.get(0);


			ArrayList<String> recordSet17= new ArrayList<String>();		
			recordSet17= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//CollLocn"),"CollLocn");
			collLocn1=recordSet17.get(0);


			ArrayList<String> recordSet18= new ArrayList<String>();		
			recordSet18= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//TransID"),"TransID");
			transIDDR1=recordSet18.get(0);
			transIDCR1=recordSet18.get(1);
			//transIDDR2=recordSet18.get(2);
			//transIDCR2=recordSet18.get(3);

			ArrayList<String> recordSet19= new ArrayList<String>();		
			recordSet19= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//SortCode"),"SortCode");
			sortCodeDR1=recordSet19.get(0);
			sortCodeCR1=recordSet19.get(1);
			//sortCodeDR2=recordSet19.get(2);
			//sortCodeCR2=recordSet19.get(3);

			ArrayList<String> recordSet20= new ArrayList<String>();		
			recordSet20= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//AccNum"),"AccNum");
			accNumDR1=recordSet20.get(0);
			accNumCR1=recordSet20.get(1);
			//accNumDR2=recordSet20.get(0);
			//accNumCR2=recordSet20.get(1);

			ArrayList<String> recordSet21= new ArrayList<String>();		
			recordSet21= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//SerNum"),"SerNum");
			serNumDR1=recordSet21.get(0);
			//serNumDR2=recordSet21.get(1);

			ArrayList<String> recordSet22= new ArrayList<String>();		
			recordSet22= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//TranCode"),"TranCode");
			tranCodeDR1=recordSet22.get(0);
			tranCodeCR1=recordSet22.get(1);
			//tranCodeDR2=recordSet22.get(2);
			//tranCodeCR2=recordSet22.get(3);

			ArrayList<String> recordSet23= new ArrayList<String>();		
			recordSet23= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//SwitchSort"),"SwitchSort");
			switchSortDR1=recordSet23.get(0);
			//switchSortDR2=recordSet23.get(1);

			ArrayList<String> recordSet24= new ArrayList<String>();		
			recordSet24= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//SwitchAcc"),"SwitchAcc");
			switchAccDR1=recordSet24.get(0);
			//switchAccDR2=recordSet24.get(1);			

			ArrayList<String> recordSet25= new ArrayList<String>();		
			recordSet25= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//Ref"),"Ref");
			refCR1=recordSet25.get(0);
			//refCR2=recordSet25.get(1);

			ArrayList<String> recordSet26= new ArrayList<String>();		
			recordSet26= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//OrigAmt"),"OrigAmt");
			origAmtCR1=recordSet26.get(0);


			ArrayList<String> recordSet27= new ArrayList<String>();		
			recordSet27= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//Beneficiary"),"Beneficiary");
			beneficiaryCR1=recordSet27.get(0);
			
			ArrayList<String> recordSet28= new ArrayList<String>();		
			recordSet28= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//FullAmt"),"FullAmt");
			fullAmtDR1=recordSet28.get(0);
			
			ArrayList<String> recordSet29= new ArrayList<String>();		
			recordSet29= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//Exception"),"Exception");
			exceptionCR1=recordSet29.get(0);
			
			ArrayList<String> recordSet30= new ArrayList<String>();		
			recordSet30= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//NPASort"),"NPASort");
			nPASort1=recordSet30.get(0);
			
			ArrayList<String> recordSet31= new ArrayList<String>();		
			recordSet31= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePath,fileName,"//CollPart"),"CollPart");
			collPart1=recordSet31.get(0);
			

			ar.add(sequence1);
			//ar.add(sequence2);
			ar.add(postType1);
			//ar.add(postType2);
			ar.add(subType1);
			//ar.add(subType2);
			ar.add(sourceMsg1);
			//ar.add(sourceMsg2);
			ar.add(channel1);
			//ar.add(channel2);
			ar.add(entryType1);
			//ar.add(entryType2);
			ar.add(postingSource1);
			//ar.add(postingSource2);
			ar.add(respIDSource1);
			//ar.add(respIDSource2);
			ar.add(postDay1);
			ar.add(clearDate1);
			//ar.add(clearDate2);
			ar.add(settDate1);
			//ar.add(settDate2);
			ar.add(captDate1);
			//ar.add(captDate2);
			ar.add(amount1);
			//ar.add(amount2);
			ar.add(tranSetID1);
			//ar.add(tranSetID2);
			ar.add(reason1);
			ar.add(override1);
			//ar.add(override2);
			ar.add(nPASort1);
			ar.add(numCheques1);
			ar.add(collPart1);
			ar.add(collLocn1);
			
			ar.add(transIDDR1);
			//
			//ar.add(transIDDR2);
			//ar.add(transIDCR2);
			ar.add(sortCodeDR1);
			//
			//ar.add(sortCodeDR2);
			//ar.add(sortCodeCR2);
			ar.add(accNumDR1);
			//
			//ar.add(accNumDR2);
			//ar.add(accNumCR2);
			ar.add(serNumDR1);
			//ar.add(serNumDR2);
			ar.add(tranCodeDR1);
			//
			//ar.add(tranCodeDR2);
			//ar.add(tranCodeCR2);
			ar.add(switchSortDR1);
			//ar.add(switchSortDR2);
			ar.add(switchAccDR1);
			//ar.add(switchAccDR2);
			ar.add(fullAmtDR1);
			
			ar.add(transIDCR1);
			ar.add(sortCodeCR1);
			ar.add(accNumCR1);			
			ar.add(refCR1);
			ar.add(tranCodeCR1);
			//ar.add(refCR2);
			ar.add(origAmtCR1);
			ar.add(exceptionCR1);
			ar.add(beneficiaryCR1);	

			System.out.println(ar);

			return ar;

		}
		
		public static ResultSet validatePostingResponseHeaderXMLAttributes() throws Exception
		{
			String server = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBServerName");
			String database = GenericMethodUtilis.getrNeResourceFile().getString("rNeDBName");			
			String query = " select top 1 * from Posting.RNEPostingExtract "
					+ "where HeaderSequence in ('" + ExtractXML.responseHeaderSetTagWiseData.get("Sequence")+"')"
					+ " and HeaderVersion in ('" + ExtractXML.responseHeaderSetTagWiseData.get("Version")+"')"
					+ " order by 1 desc";

			ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(server, database, query);		
			return rs;
		}

		
		private static NodeList getNodeSetList(String FilePath, String FileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
		{
			return getParsedXMLData(getFileStreamWithoutExtension(FilePath, FileName)).getElementsByTagName(elementsSetWithTagName);		
		}
		

}
