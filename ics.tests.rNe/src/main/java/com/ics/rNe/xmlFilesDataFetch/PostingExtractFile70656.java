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

public class PostingExtractFile70656 extends GenericMethodUtilis {
	
	//PostingHeader Attributes
	public static String schemaTagName = "Schema";
	public static String procDateTagName = "ProcDate";
	public static String participantTagName = "Participant";
	public static String sequenceTagName = "Sequence";
	public static String versionTagName = "Version";
	public static String fileDateTagName = "FileDate";
	public static String weekdayTagName = "WeekDay";
	public static String typeTagName = "Type";
	public static String environmentTagName = "Environment";
	
	//Record Body Attributes
	public static String sequenceRBTagName = "Sequence";
	public static String postTypeTagName = "PostType";
	public static String subTypeTagName = "SubType";
	public static String sourceMsgTagName = "SourceMsg";
	public static String channelTagName = "Channel";
	public static String entryTypeTagName = "EntryType";
	public static String postSrcTagName = "PostSrc";
	public static String responseIDSourceTagName = "ResponseIDSource";
	public static String postDayTagName = "PostDay";
	public static String clearDateTagName = "ClearDate";
	public static String settDateTagName = "SettDate";
	public static String captDateTagName = "CaptDate";
	public static String amountTagName = "Amount";
	public static String tranSetIDTagName = "TranSetID";
	public static String reasonTagName = "Reason";
	public static String overrideTagName = "Override";
	public static String nPASortTagName = "NPASort";
	public static String collPartTagName = "CollPart";
	public static String collLocnTagName = "CollLocn";
	
	//DebitRecord Attributes
	public static String debitTransactionIdTagName = "DebitTransactionID";
	public static String sortCodeDRTagName = "SortCode";
	public static String accNumDRTagName = "AccNum";
	public static String serNumDRTagName = "SerNum";
	public static String tranCodeDRTagName = "TranCode";
	public static String debitFullAmountDRTagName = "FullAmt";
	public static String switchSortDRTagName = "SwitchSort";
	public static String switchAccDRTagName = "SwitchAcc";

	//CreditRecord Attributes
	public static String creditIdCRTagName = "CreditId";
	public static String sortCodeCRTagName = "SortCode";
	public static String accNumCRTagName = "AccNum";
	public static String refCRTagName = "Ref";
	public static String tranCodeCRTagName = "TranCode";
	public static String origAmtCRTagName = "OrigAmt";
	public static String exceptionTagName = "Exception";
	public static String beneficiaryCRTagName = "Beneficiary";
	
	//Headers
	public static String PostingResponseHeader = "PostingHeader";
	public static String PostingResponseRecord ="RecordBody";
	public static String PostingResponseDebit = "DebitRecord";
	public static String PostingResponseCredit = "CreditRecord";
	
	//Hashmaps
	public static HashMap<String, String> extractPostingHeaderSetTagWiseData;
	public static HashMap<String, String> extractRecordBodySetTagWiseData;
	public static HashMap<String, String> extractDebitRecordSetTagWiseData;
	public static HashMap<String, String> extractCreditRecordSetTagWiseData;
	
	//File Path and Name for different .sql and XML file types
	public static String filePath = GenericMethodUtilis.getrNeResourceFile().getString("filePath");
	public static String fileName = GenericMethodUtilis.getrNeResourceFile().getString("fileName");
	
	public static void getTagwiseDataForPostingExtract() throws SAXException, IOException, ParserConfigurationException
	{	

		NodeList nodeSetList = getNodeSetList(filePath,fileName,PostingResponseHeader);

		extractPostingHeaderSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength(); eachNodeSetEntry++)
		{

			Node eachNode = nodeSetList.item(eachNodeSetEntry);

			if(nodeSetList.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				
				Element eachElement=(Element) (nodeSetList.item(eachNodeSetEntry));

				extractPostingHeaderSetTagWiseData.put(schemaTagName, eachElement.getElementsByTagName(schemaTagName).item(0).getTextContent());	
				
				extractPostingHeaderSetTagWiseData.put(procDateTagName, eachElement.getElementsByTagName(procDateTagName).item(0).getTextContent());	
				
				extractPostingHeaderSetTagWiseData.put(participantTagName, eachElement.getElementsByTagName(participantTagName).item(0).getTextContent());					
				
				extractPostingHeaderSetTagWiseData.put(sequenceTagName, eachElement.getElementsByTagName(sequenceTagName).item(0).getTextContent());	
				
				extractPostingHeaderSetTagWiseData.put(versionTagName, eachElement.getElementsByTagName(versionTagName).item(0).getTextContent());		
				
				extractPostingHeaderSetTagWiseData.put(fileDateTagName, eachElement.getElementsByTagName(fileDateTagName).item(0).getTextContent());	
				
				extractPostingHeaderSetTagWiseData.put(environmentTagName, eachElement.getElementsByTagName(environmentTagName).item(0).getTextContent());				
				
				extractPostingHeaderSetTagWiseData.put(weekdayTagName, eachElement.getElementsByTagName(weekdayTagName).item(0).getTextContent());									
				
				extractPostingHeaderSetTagWiseData.put(typeTagName, eachElement.getElementsByTagName(typeTagName).item(0).getTextContent());

			}
		}

		NodeList nodeSetList1 = getNodeSetList(filePath,fileName,PostingResponseRecord);
		extractRecordBodySetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList1.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList1.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList1.item(eachNodeSetEntry);
				extractRecordBodySetTagWiseData.put(sequenceRBTagName, eachElement.getElementsByTagName(sequenceRBTagName).item(0).getTextContent());
				extractRecordBodySetTagWiseData.put(postTypeTagName, eachElement.getElementsByTagName(postTypeTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(subTypeTagName, eachElement.getElementsByTagName(subTypeTagName).item(0).getTextContent());					
				extractRecordBodySetTagWiseData.put(sourceMsgTagName, eachElement.getElementsByTagName(sourceMsgTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(channelTagName, eachElement.getElementsByTagName(channelTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(entryTypeTagName, eachElement.getElementsByTagName(entryTypeTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(postSrcTagName, eachElement.getElementsByTagName(postSrcTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(responseIDSourceTagName, eachElement.getElementsByTagName(responseIDSourceTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(postDayTagName, eachElement.getElementsByTagName(postDayTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(clearDateTagName, eachElement.getElementsByTagName(clearDateTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(settDateTagName, eachElement.getElementsByTagName(settDateTagName).item(0).getTextContent());					
				extractRecordBodySetTagWiseData.put(captDateTagName, eachElement.getElementsByTagName(captDateTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(amountTagName, eachElement.getElementsByTagName(amountTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(tranSetIDTagName, eachElement.getElementsByTagName(tranSetIDTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(reasonTagName, eachElement.getElementsByTagName(reasonTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(overrideTagName, eachElement.getElementsByTagName(overrideTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(nPASortTagName, eachElement.getElementsByTagName(nPASortTagName).item(0).getTextContent());						
				extractRecordBodySetTagWiseData.put(collPartTagName, eachElement.getElementsByTagName(collPartTagName).item(0).getTextContent());	
				extractRecordBodySetTagWiseData.put(collLocnTagName, eachElement.getElementsByTagName(collLocnTagName).item(0).getTextContent());	
				System.out.println("Test : "+ extractRecordBodySetTagWiseData.get("CollLocn"));
			}
		}	

		NodeList nodeSetList2 = getNodeSetList(filePath,fileName, PostingResponseDebit);
		extractDebitRecordSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList2.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList2.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList2.item(eachNodeSetEntry);
				extractDebitRecordSetTagWiseData.put(debitTransactionIdTagName, eachElement.getElementsByTagName(debitTransactionIdTagName).item(0).getTextContent());	
				extractDebitRecordSetTagWiseData.put(sortCodeDRTagName, eachElement.getElementsByTagName(sortCodeDRTagName).item(0).getTextContent());	
				extractDebitRecordSetTagWiseData.put(accNumDRTagName, eachElement.getElementsByTagName(accNumDRTagName).item(0).getTextContent());					
				extractDebitRecordSetTagWiseData.put(serNumDRTagName, eachElement.getElementsByTagName(serNumDRTagName).item(0).getTextContent());	
				extractDebitRecordSetTagWiseData.put(tranCodeDRTagName, eachElement.getElementsByTagName(tranCodeDRTagName).item(0).getTextContent());
				extractDebitRecordSetTagWiseData.put(switchSortDRTagName, eachElement.getElementsByTagName(switchSortDRTagName).item(0).getTextContent());
				extractDebitRecordSetTagWiseData.put(switchAccDRTagName, eachElement.getElementsByTagName(switchAccDRTagName).item(0).getTextContent());
				extractDebitRecordSetTagWiseData.put(debitFullAmountDRTagName, eachElement.getElementsByTagName(debitFullAmountDRTagName).item(0).getTextContent());	
				
			}
		}

		NodeList nodeSetList3 = getNodeSetList(filePath,fileName, PostingResponseCredit);
		extractCreditRecordSetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList3.getLength() ; eachNodeSetEntry++)
		{

			if(nodeSetList3.item(eachNodeSetEntry).getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) nodeSetList3.item(eachNodeSetEntry);
				extractCreditRecordSetTagWiseData.put(creditIdCRTagName, eachElement.getElementsByTagName(creditIdCRTagName).item(0).getTextContent());	
				extractCreditRecordSetTagWiseData.put(sortCodeCRTagName, eachElement.getElementsByTagName(sortCodeCRTagName).item(0).getTextContent());	
				extractCreditRecordSetTagWiseData.put(accNumCRTagName, eachElement.getElementsByTagName(accNumCRTagName).item(0).getTextContent());					
				extractCreditRecordSetTagWiseData.put(refCRTagName, eachElement.getElementsByTagName(refCRTagName).item(0).getTextContent());	
				extractCreditRecordSetTagWiseData.put(tranCodeCRTagName, eachElement.getElementsByTagName(tranCodeCRTagName).item(0).getTextContent());	
				extractCreditRecordSetTagWiseData.put(origAmtCRTagName, eachElement.getElementsByTagName(origAmtCRTagName).item(0).getTextContent());
				extractCreditRecordSetTagWiseData.put(beneficiaryCRTagName, eachElement.getElementsByTagName(beneficiaryCRTagName).item(0).getTextContent());
				extractCreditRecordSetTagWiseData.put(exceptionTagName, eachElement.getElementsByTagName(exceptionTagName).item(0).getTextContent());
			}
		}
	}
	
	private static NodeList getNodeSetList(String FilePath, String FileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(FilePath, FileName)).getElementsByTagName(elementsSetWithTagName);		
	}	
}
