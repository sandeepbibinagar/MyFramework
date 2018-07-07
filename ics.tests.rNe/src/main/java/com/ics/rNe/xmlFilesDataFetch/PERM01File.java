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

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class PERM01File  extends GenericMethodUtilis{
	
	public static String filePERM01Path = GenericMethodUtilis.getrNeResourceFile().getString("filePERM01Path");
	public static String filePERM01Name = GenericMethodUtilis.getrNeResourceFile().getString("filePERM01Name");		

	
	public static ArrayList<String> getPERM01Details() throws SAXException, IOException, ParserConfigurationException, Exception
	{
		ArrayList<String> ar=new ArrayList<String>();
		
		String itemId1=null;
		String postingAttempt1=null;
		String postingSequence1=null;
		String postingType1=null;
		String postingSubType1=null;
		String channel1=null;
		String postingCrDrEntry1=null;
		String postingSource1=null;
		String responseIdSource1=null;
		String postingDay1=null;
		String clearingDate1=null;
		String settlementDate1=null;
		String postedAmount1=null;
		String postingOverrideFlag1=null;
		String nPASortCode1=null;
		String chequeCount1=null;
		String collectingParticipantId1=null;
		String collectingLocation1=null;
		String aggregated1=null;
		
		String itemIdDR1=null;
		String sortCodeDR1=null;
		String accountDR1=null;
		String serial1=null;
		String tranCode1=null;
		String switchedSortCode1=null;
		String switchedAccount1=null;
		String debitFullAmount1=null;
		
		String itemIdCR1=null;
		String sortCodeCR1=null;
		String accountCR1=null;
		String reference1=null;
		String originalAmount1=null;
		String originalSortCode1=null;
		String originalAccount1=null;
		String beneficiaryName1=null;
		
		String itemId2=null;
		String postingAttempt2=null;
		String postingSequence2=null;
		String postingType2=null;
		String postingSubType2=null;
		String channel2=null;
		String postingCrDrEntry2=null;
		String postingSource2=null;
		String responseIdSource2=null;
		String postingDay2=null;
		String clearingDate2=null;
		String settlementDate2=null;
		String postedAmount2=null;
		String postingOverrideFlag2=null;
		String nPASortCode2=null;
		String chequeCount2=null;
		String collectingParticipantId2=null;
		String collectingLocation2=null;
		String aggregated2=null;
		
		String itemIdDR2=null;
		String sortCodeDR2=null;
		String accountDR2=null;
		String serial2=null;
		String tranCode2=null;
		String switchedSortCode2=null;
		String switchedAccount2=null;
		String debitFullAmount2=null;
		
		String itemIdCR2=null;
		String sortCodeCR2=null;
		String accountCR2=null;
		String reference2=null;
		String originalAmount2=null;
		String originalSortCode2=null;
		String originalAccount2=null;
		String beneficiaryName2=null;		

		ArrayList<String> entitySet1= new ArrayList<String>();		
		entitySet1= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//ItemId"),"ItemId");
		System.out.println(entitySet1);	
		itemId1=entitySet1.get(0);
		itemIdDR1=entitySet1.get(1);
		itemIdCR1=entitySet1.get(2);
		itemId2=entitySet1.get(3);
		itemIdDR2=entitySet1.get(4);
		itemIdCR2=entitySet1.get(5);
		
		ArrayList<String> entitySet2= new ArrayList<String>();		
		entitySet2= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingAttempt"),"PostingAttempt");
		System.out.println(entitySet2);	
		postingAttempt1=entitySet2.get(0);
		postingAttempt2=entitySet2.get(1);
		
		ArrayList<String> entitySet3= new ArrayList<String>();		
		entitySet3= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingSequence"),"PostingSequence");
		System.out.println(entitySet3);	
		postingSequence1=entitySet3.get(0);
		postingSequence2=entitySet3.get(1);
		
		ArrayList<String> entitySet4= new ArrayList<String>();		
		entitySet4= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingType"),"PostingType");
		System.out.println(entitySet4);	
		postingType1=entitySet4.get(0);
		postingType2=entitySet4.get(1);
		
		ArrayList<String> entitySet5= new ArrayList<String>();		
		entitySet5= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingSubType"),"PostingSubType");
		System.out.println(entitySet5);	
		postingSubType1=entitySet5.get(0);
		postingSubType2=entitySet5.get(1);
		
		ArrayList<String> entitySet6= new ArrayList<String>();		
		entitySet6= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//Channel"),"Channel");
		System.out.println(entitySet6);	
		channel1=entitySet6.get(0);
		channel2=entitySet6.get(1);
		
		ArrayList<String> entitySet7= new ArrayList<String>();		
		entitySet7= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingDrCrEntry"),"PostingDrCrEntry");
		System.out.println(entitySet7);	
		postingCrDrEntry1=entitySet7.get(0);
		postingCrDrEntry2=entitySet7.get(1);
		
		ArrayList<String> entitySet8= new ArrayList<String>();		
		entitySet8= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingSource"),"PostingSource");
		System.out.println(entitySet8);	
		postingSource1=entitySet8.get(0);
		postingSource2=entitySet8.get(1);
		
		ArrayList<String> entitySet9= new ArrayList<String>();		
		entitySet9= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//ResponseIdSource"),"ResponseIdSource");
		System.out.println(entitySet9);	
		responseIdSource1=entitySet9.get(0);
		responseIdSource2=entitySet9.get(1);
		
		ArrayList<String> entitySet10= new ArrayList<String>();		
		entitySet10= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingDay"),"PostingDay");
		System.out.println(entitySet10);	
		postingDay1=entitySet10.get(0);
		postingDay2=entitySet10.get(1);
		
		ArrayList<String> entitySet11= new ArrayList<String>();		
		entitySet11= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//ClearingDate"),"ClearingDate");
		System.out.println(entitySet11);	
		clearingDate1=entitySet11.get(0);
		clearingDate2=entitySet11.get(1);
		
		ArrayList<String> entitySet12= new ArrayList<String>();		
		entitySet12= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//SettlementDate"),"SettlementDate");
		System.out.println(entitySet12);	
		settlementDate1=entitySet12.get(0);
		settlementDate2=entitySet12.get(1);
		
		ArrayList<String> entitySet13= new ArrayList<String>();		
		entitySet13= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostedAmount"),"PostedAmount");
		System.out.println(entitySet13);	
		postedAmount1=entitySet13.get(0);
		postedAmount2=entitySet13.get(1);
		
		ArrayList<String> entitySet14= new ArrayList<String>();		
		entitySet14= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingOverrideFlag"),"PostingOverrideFlag");
		System.out.println(entitySet14);	
		postingOverrideFlag1=entitySet14.get(0);
		postingOverrideFlag2=entitySet14.get(1);
		
		ArrayList<String> entitySet15= new ArrayList<String>();		
		entitySet15= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//NPASortCode"),"NPASortCode");
		System.out.println(entitySet15);	
		nPASortCode1=entitySet15.get(0);
		nPASortCode2=entitySet15.get(1);
		
		ArrayList<String> entitySet16= new ArrayList<String>();		
		entitySet16= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//ChequeCount"),"ChequeCount");
		System.out.println(entitySet16);	
		chequeCount1=entitySet16.get(0);
		chequeCount2=entitySet16.get(1);
		
		ArrayList<String> entitySet17= new ArrayList<String>();		
		entitySet17= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//CollectingParticipantId"),"CollectingParticipantId");
		System.out.println(entitySet17);	
		collectingParticipantId1=entitySet17.get(0);
		collectingParticipantId2=entitySet17.get(1);
		
		ArrayList<String> entitySet18= new ArrayList<String>();		
		entitySet18= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//CollectingLocation"),"CollectingLocation");
		System.out.println(entitySet18);	
		collectingLocation1=entitySet18.get(0);
		collectingLocation2=entitySet18.get(1);
		
		ArrayList<String> entitySet19= new ArrayList<String>();		
		entitySet19= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//Aggregated"),"Aggregated");
		System.out.println(entitySet19);	
		aggregated1=entitySet19.get(0);
		aggregated2=entitySet19.get(1);
		
		ArrayList<String> entitySet20= new ArrayList<String>();		
		entitySet20= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//SortCode"),"SortCode");
		System.out.println(entitySet20);	
		sortCodeDR1=entitySet20.get(0);
		sortCodeCR1=entitySet20.get(1);
		sortCodeDR2=entitySet20.get(2);
		sortCodeCR2=entitySet20.get(3);		

		ArrayList<String> entitySet21= new ArrayList<String>();		
		entitySet21= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//Account"),"Account");
		System.out.println(entitySet21);	
		accountDR1=entitySet21.get(0);
		accountCR1=entitySet21.get(1);
		accountDR2=entitySet21.get(2);
		accountCR2=entitySet21.get(3);
		
		ArrayList<String> entitySet22= new ArrayList<String>();		
		entitySet22= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//Serial"),"Serial");
		System.out.println(entitySet22);	
		serial1=entitySet22.get(0);
		serial2=entitySet22.get(1);
		
		ArrayList<String> entitySet23= new ArrayList<String>();		
		entitySet23= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//TranCode"),"TranCode");
		System.out.println(entitySet23);	
		tranCode1=entitySet23.get(0);
		tranCode2=entitySet23.get(1);
		
		ArrayList<String> entitySet24= new ArrayList<String>();		
		entitySet24= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//SwitchedSortCode"),"SwitchedSortCode");
		System.out.println(entitySet24);	
		switchedSortCode1=entitySet24.get(0);
		switchedSortCode2=entitySet24.get(1);
		
		ArrayList<String> entitySet25= new ArrayList<String>();		
		entitySet25= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//SwitchedAccount"),"SwitchedAccount");
		System.out.println(entitySet25);	
		switchedAccount1=entitySet25.get(0);
		switchedAccount2=entitySet25.get(1);
		
		ArrayList<String> entitySet26= new ArrayList<String>();		
		entitySet26= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//DebitFullAmount"),"DebitFullAmount");
		System.out.println(entitySet26);	
		debitFullAmount1=entitySet26.get(0);
		debitFullAmount2=entitySet26.get(1);
		
		ArrayList<String> entitySet27= new ArrayList<String>();		
		entitySet27= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//Reference"),"Reference");
		System.out.println(entitySet27);	
		reference1=entitySet27.get(0);
		reference2=entitySet27.get(1);
		
		ArrayList<String> entitySet28= new ArrayList<String>();		
		entitySet28= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//OriginalAmount"),"OriginalAmount");
		System.out.println(entitySet28);	
		originalAmount1=entitySet28.get(0);
		originalAmount2=entitySet28.get(1);
		
		ArrayList<String> entitySet29= new ArrayList<String>();		
		entitySet29= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//OriginalSortCode"),"OriginalSortCode");
		System.out.println(entitySet29);	
		originalSortCode1=entitySet29.get(0);
		originalSortCode2=entitySet29.get(1);
		
		ArrayList<String> entitySet30= new ArrayList<String>();		
		entitySet30= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//OriginalAccount"),"OriginalAccount");
		System.out.println(entitySet30);	
		originalAccount1=entitySet30.get(0);
		originalAccount2=entitySet30.get(1);
		
		ArrayList<String> entitySet31= new ArrayList<String>();		
		entitySet31= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//BeneficiaryName"),"BeneficiaryName");
		System.out.println(entitySet31);	
		beneficiaryName1=entitySet31.get(0);
		beneficiaryName2=entitySet31.get(1);		

		ar.add(itemId1);//0
		ar.add(itemIdDR1);//1
		ar.add(itemIdCR1);//2
		ar.add(itemId2);//3
		ar.add(itemIdDR2);//4
		ar.add(itemIdCR2);//5
		ar.add(postingAttempt1);//6
		ar.add(postingAttempt2);//
		ar.add(postingSequence1);//7
		ar.add(postingSequence2);//8
		ar.add(postingType1);//9
		ar.add(postingType2);//10
		ar.add(postingSubType1);//11
		ar.add(postingSubType2);//12
		ar.add(channel1);//13
		ar.add(channel2);//14
		ar.add(postingCrDrEntry1);//15
		ar.add(postingCrDrEntry2);//16
		ar.add(postingSource1);//17
		ar.add(postingSource2);//18
		ar.add(responseIdSource1);//19
		ar.add(responseIdSource2);//20
		ar.add(postingDay1);//21
		ar.add(postingDay2);//22
		ar.add(clearingDate1);//23
		ar.add(clearingDate2);//24
		ar.add(settlementDate1);//25
		ar.add(settlementDate2);//26
		ar.add(postedAmount1);//27
		ar.add(postedAmount2);//28
		ar.add(postingOverrideFlag1);//29
		ar.add(postingOverrideFlag2);//30
		ar.add(nPASortCode1);//31
		ar.add(nPASortCode2);//32
		ar.add(chequeCount1);//33
		ar.add(chequeCount2);//34
		ar.add(collectingParticipantId1);//35
		ar.add(collectingParticipantId2);//36
		ar.add(collectingLocation1);//37
		ar.add(collectingLocation2);//38
		ar.add(aggregated1);//39
		ar.add(aggregated2);//40
		ar.add(sortCodeDR1);//41
		ar.add(sortCodeCR1);//42
		ar.add(sortCodeDR2);//43
		ar.add(sortCodeCR2);//44
		ar.add(accountDR1);//45
		ar.add(accountCR1);//46
		ar.add(accountDR2);//47
		ar.add(accountCR2);//48
		ar.add(serial1);//49
		ar.add(serial2);//50
		ar.add(tranCode1);//51
		ar.add(tranCode2);//52
		ar.add(switchedSortCode1);//53
		ar.add(switchedSortCode2);//54
		ar.add(switchedAccount1);//55
		ar.add(switchedAccount2);//56
		ar.add(debitFullAmount1);//57
		ar.add(debitFullAmount2);//58
		ar.add(reference1);//59
		ar.add(reference2);//60
		ar.add(originalAmount1);//61
		ar.add(originalAmount2);//62
		ar.add(originalSortCode1);//63
		ar.add(originalSortCode2);//64
		ar.add(originalAccount1);//65
		ar.add(originalAccount2);//66
		ar.add(beneficiaryName1);//67
		ar.add(beneficiaryName2);//68
		
		return ar;

	}
	
	
	public static ArrayList<String> getPERM01Details_1DR_1CR() throws SAXException, IOException, ParserConfigurationException, Exception
	{
		ArrayList<String> ar=new ArrayList<String>();
		
		String itemId1=null;
		String postingAttempt1=null;
		String postingSequence1=null;
		String postingType1=null;
		String postingSubType1=null;
		String channel1=null;
		String postingCrDrEntry1=null;
		String postingSource1=null;
		String responseIdSource1=null;
		String postingDay1=null;
		String clearingDate1=null;
		String settlementDate1=null;
		String postedAmount1=null;
		String postingOverrideFlag1=null;
		String nPASortCode1=null;
		String chequeCount1=null;
		String collectingParticipantId1=null;
		String collectingLocation1=null;
		String aggregated1=null;
		
		String itemIdDR1=null;
		String sortCodeDR1=null;
		String accountDR1=null;
		String serial1=null;
		String tranCode1=null;
		String switchedSortCode1=null;
		String switchedAccount1=null;
		String debitFullAmount1=null;
		
		String itemIdCR1=null;
		String sortCodeCR1=null;
		String accountCR1=null;
		String reference1=null;
		String originalAmount1=null;
		String originalSortCode1=null;
		String originalAccount1=null;
		String beneficiaryName1=null;
		
		
		ArrayList<String> entitySet1= new ArrayList<String>();		
		entitySet1= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//ItemId"),"ItemId");
		System.out.println(entitySet1);	
		itemId1=entitySet1.get(0);
		itemIdDR1=entitySet1.get(1);
		itemIdCR1=entitySet1.get(2);	
		
		ArrayList<String> entitySet2= new ArrayList<String>();		
		entitySet2= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingAttempt"),"PostingAttempt");
		System.out.println(entitySet2);	
		postingAttempt1=entitySet2.get(0);	
		
		ArrayList<String> entitySet3= new ArrayList<String>();		
		entitySet3= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingSequence"),"PostingSequence");
		System.out.println(entitySet3);	
		postingSequence1=entitySet3.get(0);	
		
		ArrayList<String> entitySet4= new ArrayList<String>();		
		entitySet4= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingType"),"PostingType");
		System.out.println(entitySet4);	
		postingType1=entitySet4.get(0);		
		
		ArrayList<String> entitySet5= new ArrayList<String>();		
		entitySet5= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingSubType"),"PostingSubType");
		System.out.println(entitySet5);	
		postingSubType1=entitySet5.get(0);		
		
		ArrayList<String> entitySet6= new ArrayList<String>();		
		entitySet6= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//Channel"),"Channel");
		System.out.println(entitySet6);	
		channel1=entitySet6.get(0);		
		
		ArrayList<String> entitySet7= new ArrayList<String>();		
		entitySet7= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingDrCrEntry"),"PostingDrCrEntry");
		System.out.println(entitySet7);	
		postingCrDrEntry1=entitySet7.get(0);		
		
		ArrayList<String> entitySet8= new ArrayList<String>();		
		entitySet8= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingSource"),"PostingSource");
		System.out.println(entitySet8);	
		postingSource1=entitySet8.get(0);	
		
		ArrayList<String> entitySet9= new ArrayList<String>();		
		entitySet9= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//ResponseIdSource"),"ResponseIdSource");
		System.out.println(entitySet9);	
		responseIdSource1=entitySet9.get(0);		
		
		ArrayList<String> entitySet10= new ArrayList<String>();		
		entitySet10= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingDay"),"PostingDay");
		System.out.println(entitySet10);	
		postingDay1=entitySet10.get(0);		
		
		ArrayList<String> entitySet11= new ArrayList<String>();		
		entitySet11= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//ClearingDate"),"ClearingDate");
		System.out.println(entitySet11);	
		clearingDate1=entitySet11.get(0);		
		
		ArrayList<String> entitySet12= new ArrayList<String>();		
		entitySet12= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//SettlementDate"),"SettlementDate");
		System.out.println(entitySet12);	
		settlementDate1=entitySet12.get(0);		
		
		ArrayList<String> entitySet13= new ArrayList<String>();		
		entitySet13= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostedAmount"),"PostedAmount");
		System.out.println(entitySet13);	
		postedAmount1=entitySet13.get(0);		
		
		ArrayList<String> entitySet14= new ArrayList<String>();		
		entitySet14= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//PostingOverrideFlag"),"PostingOverrideFlag");
		System.out.println(entitySet14);	
		postingOverrideFlag1=entitySet14.get(0);		
		
		ArrayList<String> entitySet15= new ArrayList<String>();		
		entitySet15= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//NPASortCode"),"NPASortCode");
		System.out.println(entitySet15);	
		nPASortCode1=entitySet15.get(0);		
		
		ArrayList<String> entitySet16= new ArrayList<String>();		
		entitySet16= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//ChequeCount"),"ChequeCount");
		System.out.println(entitySet16);	
		chequeCount1=entitySet16.get(0);		
		
		ArrayList<String> entitySet17= new ArrayList<String>();		
		entitySet17= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//CollectingParticipantId"),"CollectingParticipantId");
		System.out.println(entitySet17);	
		collectingParticipantId1=entitySet17.get(0);		
		
		ArrayList<String> entitySet18= new ArrayList<String>();		
		entitySet18= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//CollectingLocation"),"CollectingLocation");
		System.out.println(entitySet18);	
		collectingLocation1=entitySet18.get(0);		
		
		ArrayList<String> entitySet19= new ArrayList<String>();		
		entitySet19= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//Aggregated"),"Aggregated");
		System.out.println(entitySet19);	
		aggregated1=entitySet19.get(0);		
		
		ArrayList<String> entitySet20= new ArrayList<String>();		
		entitySet20= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//SortCode"),"SortCode");
		System.out.println(entitySet20);	
		sortCodeDR1=entitySet20.get(0);
		sortCodeCR1=entitySet20.get(1);			

		ArrayList<String> entitySet21= new ArrayList<String>();		
		entitySet21= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//Account"),"Account");
		System.out.println(entitySet21);	
		accountDR1=entitySet21.get(0);
		accountCR1=entitySet21.get(1);		
		
		ArrayList<String> entitySet22= new ArrayList<String>();		
		entitySet22= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//Serial"),"Serial");
		System.out.println(entitySet22);	
		serial1=entitySet22.get(0);		
		
		ArrayList<String> entitySet23= new ArrayList<String>();		
		entitySet23= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//TranCode"),"TranCode");
		System.out.println(entitySet23);	
		tranCode1=entitySet23.get(0);		
		
		ArrayList<String> entitySet24= new ArrayList<String>();		
		entitySet24= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//SwitchedSortCode"),"SwitchedSortCode");
		System.out.println(entitySet24);	
		switchedSortCode1=entitySet24.get(0);		
		
		ArrayList<String> entitySet25= new ArrayList<String>();		
		entitySet25= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//SwitchedAccount"),"SwitchedAccount");
		System.out.println(entitySet25);	
		switchedAccount1=entitySet25.get(0);		
		
		ArrayList<String> entitySet26= new ArrayList<String>();		
		entitySet26= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//DebitFullAmount"),"DebitFullAmount");
		System.out.println(entitySet26);	
		debitFullAmount1=entitySet26.get(0);		
		
		ArrayList<String> entitySet27= new ArrayList<String>();		
		entitySet27= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//Reference"),"Reference");
		System.out.println(entitySet27);	
		reference1=entitySet27.get(0);
		
		ArrayList<String> entitySet28= new ArrayList<String>();		
		entitySet28= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//OriginalAmount"),"OriginalAmount");
		System.out.println(entitySet28);	
		originalAmount1=entitySet28.get(0);		
		
		ArrayList<String> entitySet29= new ArrayList<String>();		
		entitySet29= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//OriginalSortCode"),"OriginalSortCode");
		System.out.println(entitySet29);	
		originalSortCode1=entitySet29.get(0);		
		
		ArrayList<String> entitySet30= new ArrayList<String>();		
		entitySet30= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//OriginalAccount"),"OriginalAccount");
		System.out.println(entitySet30);	
		originalAccount1=entitySet30.get(0);		
		
		ArrayList<String> entitySet31= new ArrayList<String>();		
		entitySet31= (ArrayList<String>) getNodeListValues(getXMLNodeValueByXPATH(filePERM01Path,filePERM01Name+".xml","//BeneficiaryName"),"BeneficiaryName");
		System.out.println(entitySet31);	
		beneficiaryName1=entitySet31.get(0);			

		ar.add(itemId1);//0
		ar.add(itemIdDR1);//1
		ar.add(itemIdCR1);//2		
		ar.add(postingAttempt1);//3		
		ar.add(postingSequence1);//4		
		ar.add(postingType1);//5		
		ar.add(postingSubType1);//6		
		ar.add(channel1);//7		
		ar.add(postingCrDrEntry1);//8		
		ar.add(postingSource1);//9		
		ar.add(responseIdSource1);//10		
		ar.add(postingDay1);//11		
		ar.add(clearingDate1);//12		
		ar.add(settlementDate1);//13		
		ar.add(postedAmount1);//14		
		ar.add(postingOverrideFlag1);//15		
		ar.add(nPASortCode1);//16		
		ar.add(chequeCount1);//17		
		ar.add(collectingParticipantId1);//18		
		ar.add(collectingLocation1);//19		
		ar.add(aggregated1);//20		
		ar.add(sortCodeDR1);//21
		ar.add(sortCodeCR1);//22		
		ar.add(accountDR1);//23
		ar.add(accountCR1);//24		
		ar.add(serial1);//25		
		ar.add(tranCode1);//26		
		ar.add(switchedSortCode1);//27		
		ar.add(switchedAccount1);//28		
		ar.add(debitFullAmount1);//29	
		ar.add(reference1);//30		
		ar.add(originalAmount1);//31		
		ar.add(originalSortCode1);//32		
		ar.add(originalAccount1);//33		
		ar.add(beneficiaryName1);//34
		
		
		return ar;

	}


}
