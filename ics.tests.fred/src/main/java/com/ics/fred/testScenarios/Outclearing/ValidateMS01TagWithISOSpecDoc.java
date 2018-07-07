package com.ics.fred.testScenarios.Outclearing;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ics.fred.common.FREDXMLValidation;
import com.ics.fred.testScenarios.Tags1;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateMS01TagWithISOSpecDoc extends GenericMethodUtilis{

	List<String> listOfMandatory = new ArrayList<String>();
	
	//XPATH Declarations
	static String isoTxSetSubmissionXpath ="//TxSetSubmissn";
	static String isoTxSetSubmissionGrpHdrXpath ="//TxSetSubmissn/GrpHdr";
	static String isoTxSetSubmissnGrpHdrMsgIdXpath ="//TxSetSubmissn/GrpHdr/MsgId";
	
	static String isoTxSetSubmissnGrpHdrCreDtTmXpath ="//TxSetSubmissn/GrpHdr/CreDtTm";
	static String isoTxSetSubmissnGrpHdrNbOfTxsXpath ="//TxSetSubmissn/GrpHdr/NbOfTxs";
	static String isoTxSetSubmissnGrpHdrSndrIdXpath ="//TxSetSubmissn/GrpHdr/SndrId";
	static String isoTxSetSubmissnGrpHdrDrctPtcptXpath ="//TxSetSubmissn/GrpHdr/DrctPtcpt";
	static String isoTxSetSubmissnGrpHdrTstIndXpath ="//TxSetSubmissn/GrpHdr/TstInd";
	static String isoTxSetSubmissnGrpHdrSgntrXpath ="//TxSetSubmissn/GrpHdr/Sgntr";
	
	static String isoTxSetXpath ="//TxSetSubmissn/TxSet";
	static String isoTxSetIdXpath ="//TxSetSubmissn/TxSet/TxSetId";
	static String isoTxSetVersionXpath ="//TxSetSubmissn/TxSet/TxSetVrsn";
	static String isoTxSetEndPtIdXpath ="//TxSetSubmissn/TxSet/EndPtId";
	static String isoTxSetNbOfItmsXpath ="//TxSetSubmissn/TxSet/NbOfItms";
	static String isoTxSetFrdChckOnlyIndXpath ="//TxSetSubmissn/TxSet/FrdChckOnlyInd";
	static String isoTxSetColltngPtcptIdXpath ="//TxSetSubmissn/TxSet/ColltngPtcptId";
	static String isoTxSetCaptrdDtTmXpath ="//TxSetSubmissn/TxSet/CaptrdDtTm";
	static String isoTxSetSrcXpath ="//TxSetSubmissn/TxSet/Src";
	static String isoTxSetChanlRskTpXpath ="//TxSetSubmissn/TxSet/ChanlRskTp";
	static String isoTxSetCrdtItmXpath="//TxSetSubmissn/TxSet/CrdtItm";
	static String isoTxSetDbtItmXpath="//TxSetSubmissn/TxSet/DbtItm";
	
	static String isoTxSetDbtItmSrNbRprdIndXpath="//TxSetSubmissn/TxSet/DbtItm/RprdItm/SrlNbRprdInd";
	static String isoTxSetDbtItmBkCdRprdIndXpath="//TxSetSubmissn/TxSet/DbtItm/RprdItm/BkCdRprdInd";
	static String isoTxSetDbtItmAcctNbRprdIndXpath="//TxSetSubmissn/TxSet/DbtItm/RprdItm/AcctNbRprdInd";
	static String isoTxSetDbtItmAmtRprdIndXpath="//TxSetSubmissn/TxSet/DbtItm/RprdItm/AmtRprdInd";
	static String isoTxSetDbtItmDtOfFrstChqXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmFrdData/DtOfFrstChq";
	static String isoTxSetDbtItmDtOfLstChqXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmFrdData/DtOfLstChq";
	static String isoTxSetDbtItmNbOfCtrPtysXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmFrdData/NbOfCtrPtys";
	static String isoTxSetDbtItmNbOfVldChqsXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmFrdData/NbOfVldChqs";
	static String isoTxSetDbtItmNbOfFrdChqsXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmFrdData/NbOfFrdChqs";
	static String isoTxSetDbtItmHghstAmtXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmFrdData/HghstAmt";
	static String isoTxSetDbtItmRskIndXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmFrdData/RskInd";

	static String isoTxSetDbtItmImgDataXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmFrdData";
	static String isoTxSetDbtItmImgCaptrIdXpath="//TxSetSubmissn/TxSet/DbtItm/ItmImgMetaData/ImgCaptrId";
	static String isoTxSetDbtItmImgCaptrDvcIdXpath="//TxSetSubmissn/TxSet/DbtItm/ItmImgMetaData/ImgCaptrDvcId";
	static String isoTxSetDbtItmImgCaptrLctnXpath="//TxSetSubmissn/TxSet/DbtItm/ItmImgMetaData/ImgCaptrLctn";
	static String isoTxSetDbtItmImgCaptrDtTmXpath="//TxSetSubmissn/TxSet/DbtItm/ItmImgMetaData/ImgCaptrDtTm";
	static String isoTxSetDbtItmImgMetaDataXpath="//TxSetSubmissn/TxSet/DbtItm/ItmImgMetaData";
	static String isoTxSetDbtItmImgDataImgHashXpath="//TxSetSubmissn/TxSet/DbtItm/ItmImgData/ImgHash";
	static String isoTxSetDbtItmImgDataImgXpath="//TxSetSubmissn/TxSet/DbtItm/ItmImgData/Img";
	
	static String isoTxSetCrdItmIdXpath="//TxSetSubmissn/TxSet/CrdtItm/CdtItmId";
	static String isoTxSetCrdItmTpXpath="//TxSetSubmissn/TxSet/CrdtItm/CdtItmTp";
	static String isoTxSetCrdtItmTxCdXpath="//TxSetSubmissn/TxSet/CrdtItm/CdtItmTxCd";
	static String isoTxSetCrdtItmOnusItmIdXpath="//TxSetSubmissn/TxSet/CrdtItm/OnUsItmInd";
	static String isoTxSetCrdtItmAmtXpath="//TxSetSubmissn/TxSet/CrdtItm/Amt";
	static String isoTxSetCrdtItmBkCdXpath="//TxSetSubmissn/TxSet/CrdtItm/BkCd";
	static String isoTxSetCrdItmAcctNbXpath="//TxSetSubmissn/TxSet/CrdtItm/AcctNb";
	static String isoTxSetCrdItmRefNbXpath="//TxSetSubmissn/TxSet/CrdtItm/RefNb";
	static String isoTxSetCrdItmXtrnlDataRefXpath="//TxSetSubmissn/TxSet/CrdtItm/XtrnlDataRef";
	
	static String isoTxSetDbtItmIdXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmId";
	static String isoTxSetDbtItmTpXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmTp";
	static String isoTxSetDbtItmTxCdXpath="//TxSetSubmissn/TxSet/DbtItm/DbtItmTxCd";
	static String isoTxSetDbtItmRepresentItmIndXpath="//TxSetSubmissn/TxSet/DbtItm/RpresntdItmInd";
	static String isoTxSetDbtItmAmtXpath="//TxSetSubmissn/TxSet/DbtItm/Amt";
	static String isoTxSetDbtItmOnUsXpath="//TxSetSubmissn/TxSet/DbtItm/OnUsItmInd";
	static String isoTxSetDbtItmBkCdXpath="//TxSetSubmissn/TxSet/DbtItm/BkCd";
	static String isoTxSetDbtItmAcctNbXpath="//TxSetSubmissn/TxSet/DbtItm/AcctNb";
	static String isoTxSetDbtItmSrlNbXpath="//TxSetSubmissn/TxSet/DbtItm/SrlNb";
	static String isoTxSetDbtItmXtrnlDataRefXpath="//TxSetSubmissn/TxSet/DbtItm/XtrnlDataRef";
	
	 static String txSetSubmissionNodeVal,grpHdrNodeVal,msgIdNodeVal,creDtTmNodeVal,nbOfTxsNodeVal;
	 static String sndrIdNodeVal,drctPtcptNodeVal,tstIndNodeVal,creditItmRefNbDfltdIndNodeVal,creditItmAcctNbDfltdIndNodeVal,creditItmBkCdDfltdIndNodeVal;
	 static String grpHdrMsgIdNodeVal,grpHdrDrctPtcptNodeVal,grpHdrTstIndNodeVal,grpHdrSgntrNodeVal,grpHdrCreDtTmNodeVal,grpHdrNbOfTxsNodeVal,grpHdrSndrIdNodeVal;
	 static String txSetNodeVal,txSetIdNodeVal,txSetVersionNodeVal,txSetEndPtIdNodeVal,txSetNbOfItmsNodeVal,txSetFrdChckOnlyIndNodeVal,txSetChanlRskTpNodeVal,txSetSrcNodeVal,txSetCaptrdDtTmNodeVal,txSetColltngPtcptIdNodeVal,txSetCrdtItmNodeVal,txSetDbtItmNodeVal;
	 static String dbtItmSrlNbDfltdIndNodeVal,dfltdItmNodeVal,dbtItmBkCdDfltdIndNodeVal,dbtItmAcctNbDfltdIndNodeVal;
	 static String dbtItmRprdItmNodeVal,dbtItmBkCdRprdIndNodeVal,dbtItmAcctNbRprdIndNodeVal,dbtItmAmtRprdIndNodeVal,dbtItmSrlNbRprdIndNodeVal;
	 static String dbtItmImgDataNodeVal,dbtItmImgNodeVal,dbtItmImgMetaDataNodeVal,dbtItmImgCaptrIdNodeVal,dbtItmImgCaptrDvcIdNodeVal;
	 static String dbtItmImgCaptrLctnNodeVal,dbtItmImgCaptrDtTmNodeVal,dbtItmFrdDataNodeVal,dbtItmFrdDataDtOfFrstChqNodeVal,dbtItmFrdDataHghstAmtNodeVal,dbtItmFrdDataRskIndNodeVal;
	 static String dbtItmImgHashNodeVal,dbtItmFrdDataDtOfLstChqNodeVal,dbtItmFrdDataNbOfCtrPtysNodeVal,dbtItmFrdDataNbOfVldChqsNodeVal,dbtItmFrdDataNbOfFrdChqsNodeVal;
	 static String crdtItmSrlNbDfltdIndNodeVal,creditDfltdItmNodeVal,crdtItmBkCdDfltdIndNodeVal,crdtItmAcctNbDfltdIndNodeVal;
	 static String crdtItmRprdItmNodeVal,crdtItmBkCdRprdIndNodeVal,crdtItmAcctNbRprdIndNodeVal,crdtItmAmtRprdIndNodeVal,crdtItmRefNbRprdIndNodeVal;
	 static String crdtItmImgDataNodeVal,crdtItmImgNodeVal,crdtItmImgMetaDataNodeVal,crdtItmImgCaptrIdNodeVal,crdtItmImgCaptrDvcIdNodeVal;
	 static String crdtItmImgCaptrLctnNodeVal,crdtItmImgCaptrDtTmNodeVal,crdtItmFrdDataNodeVal,crdtItmFrdChqAtRskIndNodeVal,crdtItmFrdDataFnddAmtNodeVal,crdtItmFrdDataNbOfCdtsOrDbtsNodeVal;
	 static String crdtItmImgHashNodeVal,crdtItmFrdDataBnfcryNmNodeVal,crdtItmFrdDataVrtlCdtIndNodeVal,crdtItmFrdDataRefDataNodeVal,crdtItmFrdDataCshAmtNodeVal,crdtItmFrdDataNonFunddAmtNodeVal;	
	 static String dbtItmXtrnlDataRefNodeVal,dbtItmRefNbNodeVal,dbtItmIdNodeVal,dbtItmTpNodeVal,dbtItmTxCdNodeVal,dbtItmRpresntdItmIndNodeVal,dbtItmOnUsItmIndNodeVal,dbtItmAmtNodeVal,dbtItmBkCdNodeVal,dbtItmAcctNbNodeVal,dbtItmSrlNbNodeVal;
	 static String crdtItmXtrnlDataRefNodeVal,CrdtItmRefNbNodeVal,crdtItmIdNodeVal,crdtItmTpNodeVal,crdtItmTxCdNodeVal,crdtItmRpresntdItmIndNodeVal,crdtItmOnUsItmIndNodeVal,crdtItmAmtNodeVal,crdtItmBkCdNodeVal,crdtItmAcctNbNodeVal,crdtItmRefNbNodeVal;
	 
	 
	 public static void validateISOTxSetSubmissionTags(String excelFilePath,String icnFilepath,String icnFileName) throws Exception {

				//icnCoreBusinessDate;
				FileInputStream inputStream = new FileInputStream(excelFilePath);
				
				Workbook wrkBk = new XSSFWorkbook(inputStream);
				int index = wrkBk.getSheetIndex("MSG01_GrpHdr");
				System.out.println(index);
				
				Sheet fsheet = wrkBk.getSheetAt(index);
				Iterator<Row> iterator = fsheet.iterator();
				List<String> listTag = new ArrayList<String>();
				
				List<Tags1> listOfTag = new ArrayList<>();
				
				while(iterator.hasNext()){
					Tags1 tag = new Tags1();
					
					Row nextRow = iterator.next();
					Iterator<Cell> cellIterator = nextRow.cellIterator();
					int rowIndex = nextRow.getRowNum();
					int count=1;
				
					if(rowIndex>0){
						while(cellIterator.hasNext()){
							Cell cell = cellIterator.next();
							System.out.println("cell :"+cell);
							switch(count){
							case 1:
								tag.setRootNode(cell.getRichStringCellValue().toString());
								System.out.println("RootTag CaptureInfo val :"+cell.getRichStringCellValue().toString());
								count++;
								break;

							case 2:
								tag.setCaptureInfoChildNode(cell.getRichStringCellValue().toString());
								System.out.println("CaptureInfoChildNode TxSetSubmission Tag val :"+cell.getRichStringCellValue().toString());
								count++;
								break;
								
							case 3:
								tag.setCaptureInfoChildNode1(cell.getRichStringCellValue().toString());
								System.out.println("CaptureInfoChildNode1 val :"+cell.getRichStringCellValue().toString());
								count++;
								break;

							
							case 4:
								tag.setOccurs(cell.getRichStringCellValue().toString());
								count++;
								break;
							case 5:
								tag.setFixedVar(cell.getRichStringCellValue().toString());
								count++;
								break;
							case 6:
								tag.setType(cell.getRichStringCellValue().toString());
							//	int type = (int)cell.getNumericCellValue();
							//	tag.setType(Integer.toString(type));
								count++;
								break;
							case 7:
								int len = (int)cell.getNumericCellValue();
								tag.setLength(Integer.toString(len));
							
								/*String len = cell.getRichStringCellValue().toString();
								tag.setLength(len);
								*/
								count++;
								break;
							}
							
						}
						listOfTag.add(tag);
						System.out.println("listOfTag :"+listOfTag+"  listOfTag.add(tag)"+listOfTag.add(tag));
					}
				}
				wrkBk.close();
				inputStream.close();
			
				String grpHdrNodeVal;
				String coreChildNode,rootNode,captureChildNote1,captureChildNote2,captureChildNote3,captureChildNote4;
				
				String occurs,captureChildNote;
				String type,fixedVar,length;
				int i=0;
				for(Tags1 tags:listOfTag){
					System.out.println("listOfTag :"+listOfTag);
					//coreChildNode = tags.getCoreChildNode();
					rootNode =tags.getRootNode();     //TxSetSubmissn
					
					captureChildNote = tags.getCaptureInfoChildNode(); //CaptureInfoChildNode   GrpHdr
					captureChildNote1 =tags.getCaptureInfoChildNode1(); //CaptureInfoChildNode1 MsgId, CreDtTm,BLOCK,APGIN....
					System.out.println("captureChildNote1 :"+captureChildNote1);
				//	captureChildNote2=tags.getCaptureInfoChildNode2();
				//	captureChildNote3=tags.getCaptureInfoChildNode3();
				//	captureChildNote4=tags.getCaptureInfoChildNode4();
					
					occurs=tags.getOccurs();
					System.out.println("occurs :"+occurs);
					type=tags.getType();
					length=tags.getLength();
					fixedVar=tags.getFixedVar();
					if(null!=rootNode){
						boolean rflag =getRootNodeValidation(rootNode,icnFilepath,icnFileName);
						if(rflag){
						if(null!=captureChildNote){
							System.out.println("captureChildNote "+captureChildNote);
								boolean grpflag = getCaptureInfoChildNodeValidation(captureChildNote,icnFilepath,icnFileName); //grphdr validation
								boolean txsetflag = getCaptureInfoChildNodeTxSetValidation(captureChildNote,icnFilepath,icnFileName); //txSet validation
								if(grpflag || txsetflag){
									if(null!=captureChildNote1){
									switch(captureChildNote){
									//case "TxSetSubmissn":
									
									
										case "GrpHdr":
										grpHdrNodeVal=validateISOTXSetSubmmsnGrpHdrTag(captureChildNote,icnFilepath,icnFileName);
										//getJobSubTagsOccuranceValidation(captureChildNote1,occurs,businessDateNodeVal);
										System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr DataType in ICN is :"+type);
										System.out.println("Fixed or Variable cell is not required for GrpHdr in IDS v8 "+fixedVar);
										System.out.println("Lenght validation is not required for GrpHdr in IDS v8 "+length);
									//	getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal);
								//		if(null!=captureChildNote1){
										
										
													switch(captureChildNote1){
													case "MsgId":
														grpHdrMsgIdNodeVal=getISOTxSetSubmmsnGrpHdrMsgIdNodeVal(captureChildNote1,icnFilepath,icnFileName);
														getGrpHdrMsgIdTagOccuranceValidation(captureChildNote1,occurs,grpHdrMsgIdNodeVal);
														validateFixedValLength(captureChildNote1,length,grpHdrMsgIdNodeVal);
														break;
													case "CreDtTm":
														grpHdrCreDtTmNodeVal=getISOTxSetSubmmsnGrpHdrCreDtTmNodeVal(captureChildNote1,icnFilepath,icnFileName);
														getGrpHdrCreDtTmTagOccuranceValidation(captureChildNote1,occurs,grpHdrCreDtTmNodeVal);
														validateVariableLength(captureChildNote1,length,grpHdrMsgIdNodeVal);
														
														break;
														
													case "NbOfTxs":
														grpHdrNbOfTxsNodeVal=getISOTxSetSubmmsnGrpHdrNbOfTxsNodeVal(captureChildNote1,icnFilepath,icnFileName);
														getGrpHdrNbOfTxsTagOccuranceValidation(captureChildNote1,occurs,grpHdrNbOfTxsNodeVal);
														validateVariableLength(captureChildNote1,length,grpHdrNbOfTxsNodeVal);
														break;
														
													case "SndrId":
														grpHdrSndrIdNodeVal=getISOTxSetSubmmsnGrpHdrSndrIdNodeVal(captureChildNote1,icnFilepath,icnFileName);
														getSndrIdTagOccuranceValidation(captureChildNote1,occurs,grpHdrSndrIdNodeVal);
														validateFixedValLength(captureChildNote1,length,grpHdrSndrIdNodeVal);
														break;
														
													case "DrctPtcpt":
														grpHdrDrctPtcptNodeVal=getISOTxSetSubmmsnGrpHdrDrctPtcptNodeVal(captureChildNote1,icnFilepath,icnFileName);
														getDrctPtcptTagOccuranceValidation(captureChildNote1,occurs,grpHdrDrctPtcptNodeVal);
														validateFixedValLength(captureChildNote1,length,grpHdrDrctPtcptNodeVal);
														break;
														
													case "TstInd":
														grpHdrTstIndNodeVal=getISOTxSetSubmmsnGrpHdrTstIndNodeVal(captureChildNote1,icnFilepath,icnFileName);
														getTstIndTagOccuranceValidation(captureChildNote1,occurs,grpHdrTstIndNodeVal);
														validateVariableLength(captureChildNote1,length,grpHdrTstIndNodeVal);
														break;
														
													case "Sgntr":
														grpHdrSgntrNodeVal=getISOTxSetSubmmsnGrpHdrSgntrNodeVal(captureChildNote1,icnFilepath,icnFileName);
														getSgntrTagOccuranceValidation(captureChildNote1,occurs,grpHdrSgntrNodeVal);
														validateVariableLength(captureChildNote1,length,grpHdrSgntrNodeVal);
														break;
														
												}
										/*}
										else {
											System.out.println("Subtags are not present");
										}*/
										break;
										
										case "TxSet":
											
											txSetNodeVal=validateISOTXSetTag(captureChildNote,icnFilepath,icnFileName);
											System.out.println("TxSet tag"+txSetNodeVal);
										System.out.println("TxSet childNode:"+captureChildNote1);
										switch(captureChildNote1){
										case "TxSetId":
												txSetIdNodeVal=getISOTxSetSubmmsnTransactionSetIdNodeVal(captureChildNote1,icnFilepath,icnFileName);
												getTxSetIdTagOccuranceValidation(captureChildNote1,occurs,txSetIdNodeVal);
												validateVariableLength(captureChildNote1,length,txSetIdNodeVal);
												break;
										case "TxSetVrsn":
												txSetVersionNodeVal=getISOTxSetSubmmsnTxSetVrsnNodeVal(captureChildNote1,icnFilepath,icnFileName);
												getTxSetVrsnTagOccuranceValidation(captureChildNote1,occurs,txSetVersionNodeVal);
												validateVariableLength(captureChildNote1,length,txSetVersionNodeVal);
												break;
												
										case "ColltngPtcptId":
											txSetColltngPtcptIdNodeVal=getISOTxSetSubmmsnTxSetColltngPtcptIdNodeVal(captureChildNote1,icnFilepath,icnFileName);
											getColltngPtcptIdTagOccuranceValidation(captureChildNote1,occurs,txSetColltngPtcptIdNodeVal);
											validateVariableLength(captureChildNote1,length,txSetColltngPtcptIdNodeVal);
											break;
										case "CaptrdDtTm":
											txSetCaptrdDtTmNodeVal=getISOTxSetSubmmsnTxSetCaptrdDtTmNodeVal(captureChildNote1,icnFilepath,icnFileName);
											getCaptrdDtTmTagOccuranceValidation(captureChildNote1,occurs,txSetCaptrdDtTmNodeVal);
											validateVariableLength(captureChildNote1,length,txSetCaptrdDtTmNodeVal);
											break;
										case "Src":
											txSetSrcNodeVal=getISOTxSetSubmmsnTxSetSrcNodeVal(captureChildNote1,icnFilepath,icnFileName);
											gettxSetSrcTagOccuranceValidation(captureChildNote1,occurs,txSetSrcNodeVal);
											validateVariableLength(captureChildNote1,length,txSetSrcNodeVal);
											break;
										case "ChanlRskTp":
											txSetChanlRskTpNodeVal=getISOTxSetSubmmsnTxSetChanlRskTpNodeVal(captureChildNote1,icnFilepath,icnFileName);
											getChanlRskTpTagOccuranceValidation(captureChildNote1,occurs,txSetChanlRskTpNodeVal);
											validateVariableLength(captureChildNote1,length,txSetChanlRskTpNodeVal);
											//getBlockSubTagTypeValidation(captureChildNote1,length,businessDateNodeVal);
											break;
										case "FrdChckOnlyInd":
											txSetFrdChckOnlyIndNodeVal=getISOTxSetSubmmsnTxSetFrdChckOnlyIndNodeVal(captureChildNote1,icnFilepath,icnFileName);
											getFrdChckOnlyIndTagOccuranceValidation(captureChildNote1,occurs,txSetFrdChckOnlyIndNodeVal);
											validateVariableLength(captureChildNote1,length,txSetFrdChckOnlyIndNodeVal);
											//getBlockSubTagTypeValidation(captureChildNote1,length,businessDateNodeVal);
											break;
										case "NbOfItms":
											txSetNbOfItmsNodeVal=getISOTxSetSubmmsnTxSetNbOfItmsNodeVal(captureChildNote1,icnFilepath,icnFileName);
											getNbOfItmsTagOccuranceValidation(captureChildNote1,occurs,txSetNbOfItmsNodeVal);
											validateVariableLength(captureChildNote1,length,txSetNbOfItmsNodeVal);
											//getBlockSubTagTypeValidation(captureChildNote1,length,businessDateNodeVal);
											break;
											
										case "EndPtId":
											txSetEndPtIdNodeVal=getISOTxSetSubmmsnTxSetEndPtIdNodeVal(captureChildNote1,icnFilepath,icnFileName);
											getEndPtIdTagOccuranceValidation(captureChildNote1,occurs,txSetEndPtIdNodeVal);
											validateVariableLength(captureChildNote1,length,txSetEndPtIdNodeVal);
											//getBlockSubTagTypeValidation(captureChildNote1,length,businessDateNodeVal);
											break;
											
										case "CrdtItm":
											txSetCrdtItmNodeVal=getISOTxSetSubmmsnTxSetCrdtItmNodeVal(captureChildNote1,icnFilepath,icnFileName);
											getCrdtItmTagOccuranceValidation(captureChildNote1,occurs,txSetCrdtItmNodeVal);
											validateVariableLength(captureChildNote1,length,txSetCrdtItmNodeVal);
											//getBlockSubTagTypeValidation(captureChildNote1,length,businessDateNodeVal);
											break; 
										case "DbtItm":
											txSetDbtItmNodeVal=getISOTxSetSubmmsnTxSetDbtItmNodeVal(captureChildNote1,icnFilepath,icnFileName);
											getDbtItmTagOccuranceValidation(captureChildNote1,occurs,txSetDbtItmNodeVal);
											validateVariableLength(captureChildNote1,length,txSetDbtItmNodeVal);
											//getBlockSubTagTypeValidation(captureChildNote1,length,businessDateNodeVal);
											break;
											
										
											}
											
									break;							
										
									
					
				} // switch end
											
									}
									else{
										System.out.println("TxSet Tags are not present");
									}
				}
								else{
									System.out.println("ISO TransactionSetSubmission  tag is not present");
								}
				}
						}
						else
						{
							System.out.println("ISO TransactionSetSubmission tag is not present");
						}
				
				}	
				}
			
			}
			
			private static void validateFixedValLength(String coreChildNode, String length, String grpHdrMsgIdNodeVal2) {
				System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 publishResults(((grpHdrMsgIdNodeVal2.length())<=Integer.parseInt(length)),((grpHdrMsgIdNodeVal2.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+grpHdrMsgIdNodeVal2.length()+" in ICN":coreChildNode+" length "+grpHdrMsgIdNodeVal2.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+grpHdrMsgIdNodeVal2.length()+" in ICN",coreChildNode+" Length validation");
		
			}
			
			private static void validateVariableLength(String coreChildNode, String length, String grpHdrMsgIdNodeVal2) {
				System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 publishResults(((grpHdrMsgIdNodeVal2.length())<=Integer.parseInt(length)),((grpHdrMsgIdNodeVal2.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+grpHdrMsgIdNodeVal2.length()+" in ICN":coreChildNode+" length "+grpHdrMsgIdNodeVal2.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+grpHdrMsgIdNodeVal2.length()+" in ICN",coreChildNode+" Length validation");
		
			}

			public static Map<String,String> getXMLNodeNameByXPATH(String filePath,String fileName,String xPath) throws Exception{

				System.out.println("ICN : "+ filePath+fileName);
				Document doc = getParsedXMLData(new File(filePath+fileName));

				XPath xpath = XPathFactory.newInstance().newXPath();
				XPathExpression expr = xpath.compile(xPath);
				Object result = expr.evaluate(doc, XPathConstants.NODESET);
				NodeList nodes = (NodeList) result;
				Map<String, String> nodeValue = new LinkedHashMap<String, String>();
				Map<String, String> nodeName = null;
				Set<String> nodeKey = null;
				String nodeName1=null;

				for (int i=0;i<nodes.getLength();i++){
					//System.out.println(nodes.item(i).getTextContent());
					nodeValue.put(nodes.item(i).getNodeName(), nodes.item(i).getTextContent());
					// nodeName =nodes.item(i).getNodeName();
				//nodeName = nodeValue.entrySet().iterator().next().getKey();
					//nodeValue
				//	nodeKey = nodeValue.keySet();
				//	nodeKey.iterator().next().
				//	System.out.println("nodeKey :"+nodeKey);
				}
				return nodeValue;
			}
			
			
			public static String getMapKey(Map<String,String> strNodeVal){
				String obj = null;
				for(Map.Entry<String,String> map:strNodeVal.entrySet()){
				 obj = map.getKey();
			}
			return obj;
			}
			
			public static boolean getRootNodeValidation(String rootNode,String icnFilepath,String icnFileName) throws Exception{
				
				System.out.println("For loop RootNode Core :"+rootNode);
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				System.out.println("****************************************************************");
				System.out.println("Validating ISO TransactionSetSubmission is present in ICN as per IDS version 8 :"+rootNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("****************************************************************");
				validationStepInformation("Validating ISO TransactionSetSubmission is present in ICN as per IDS version 8 :"+rootNode);
				validationStepInformation("****************************************************************");
				validationStepInformation("ISO TxSetSubmission "+isoTxSetSubmissionXpath);
				Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSubmissionXpath);
				String nodeKey = getMapKey(coreNodeVal);
				//coreName= coreNodeVal.tostatic String();
				//for(int i=0;i<coreNodeVal.size();i++){
			//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
				System.out.println("coreNodeVal :"+nodeKey);
				boolean flag =Component.verifyEquals(nodeKey, rootNode, "Root tagname is as per IDSv8");			
				System.out.println("Validation is performed for RootTag :"+flag);
				validationStepInformation("Validation is performed for RootTag :"+flag);
				publishResults(flag,nodeKey, rootNode, "CaptureInfo tagname is as per IDSv8");
				return flag;
			}
			
			public static boolean getCaptureInfoChildNodeValidation(String captureInfoChilNode,String icnFilepath,String icnFileName) throws Exception{
				
				System.out.println("****************************************************************");
				System.out.println("Validating TxSetSubmission is present in ICN as per IDS version 8 :"+captureInfoChilNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("****************************************************************");
				validationStepInformation("Validating TxSetSubmission ChilNode is present in ICN as per IDS version 8 :"+captureInfoChilNode);
				validationStepInformation("****************************************************************");
				
				Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSubmissionGrpHdrXpath);
				String nodeKey = getMapKey(coreNodeVal);
				//coreName= coreNodeVal.tostatic String();
				//for(int i=0;i<coreNodeVal.size();i++){
			//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
				System.out.println("TxSetSubmission :"+nodeKey);
				boolean flag =Component.verifyEquals(nodeKey, captureInfoChilNode, "TxSetSubmission tagname is as per IDSv8");			
				System.out.println("Validation is performed for TxSetSubmission :"+flag);
				validationStepInformation("Validation is performed for TxSetSubmission :"+flag);
				publishResults(flag,nodeKey, captureInfoChilNode, "TxSetSubmission tagname is as per IDSv8");
				return flag;
			}
			//TxSet 
			
				public static boolean getCaptureInfoChildNodeTxSetValidation(String captureInfoChilNode,String icnFilepath,String icnFileName) throws Exception{
				
				System.out.println("****************************************************************");
				System.out.println("Validating TxSetSubmission TxSet is present in ICN as per IDS version 8 :"+captureInfoChilNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("****************************************************************");
				validationStepInformation("Validating TxSetSubmission TxSet is present in ICN as per IDS version 8 :"+captureInfoChilNode);
				validationStepInformation("****************************************************************");
				
				Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetXpath);
				String nodeKey = getMapKey(coreNodeVal);
				//coreName= coreNodeVal.tostatic String();
				//for(int i=0;i<coreNodeVal.size();i++){
			//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
				System.out.println("TxSet :"+nodeKey);
				boolean flag =Component.verifyEquals(nodeKey, captureInfoChilNode, "TxSet tagname is as per IDSv8");			
				System.out.println("Validation is performed for TxSet Tag :"+flag);
				validationStepInformation("Validation is performed for TxSet :"+flag);
				publishResults(flag,nodeKey, captureInfoChilNode, "TxSet tagname is as per IDSv8");
				return flag;
			}
			public static String validateISOTXSetSubmmsnGrpHdrTag(String captureInfoChilNode,String icnFilepath,String icnFileName) throws Exception{
				
				System.out.println("****************************************************************");
				System.out.println("Validating Transaction Set Submission is present in ICN as per IDS version 8 :"+captureInfoChilNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("****************************************************************");
				validationStepInformation("Validating Transaction Set Submission is present in ICN as per IDS version 8 :"+captureInfoChilNode);
				validationStepInformation("****************************************************************");
				
				Map<String, String> TxSetSubmissionJobNodeVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSubmissionGrpHdrXpath);
				String nodeKey = getMapKey(TxSetSubmissionJobNodeVal);
				//coreName= coreNodeVal.tostatic String();
				//for(int i=0;i<coreNodeVal.size();i++){
			//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
				System.out.println("JobNodeKey :"+nodeKey);
				boolean flag =Component.verifyEquals(nodeKey, captureInfoChilNode, "TxSetSubmission JOB tagname is as per IDSv8");			
				System.out.println("Validation is performed for TxSetSubmission JOB :"+flag);
				publishResults(flag,nodeKey, captureInfoChilNode, "TxSetSubmission GrpHdr tagname is as per IDSv8");
				
				return nodeKey;			
			}
			
			
			
			public static String getISOTxSetSubmmsnGrpHdrMsgIdNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				 String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating Transaction Set Submission is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission GrpHdr MsgId is populating under JOB in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrMsgIdXpath);
				String dateKey =getMapKey(dateKeyVal);
				System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrMsgIdXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"MsgId").get(0);
				System.out.println("bdateNodeVal :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "GrpHdr MsgId tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("GrpHdr MsgId Tag is present under GrpHdr Tag in MSG01+ICN file as per IDS v8 :");
				else
					System.out.println("GrpHdr MsgId Tag is not present under GrpHdr Tag in MSG01+ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "GrpHdr MsgId tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnGrpHdrCreDtTmNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating InstallationId is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission InstallationId is populating under JOB in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrCreDtTmXpath);
				String dateKey =getMapKey(dateKeyVal);
				System.out.println("InstallationId Key:"+dateKey+"::InstallationId KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrCreDtTmXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"CreDtTm").get(0);
				System.out.println("InstallationId :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "InstallationId tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("InstallationId Tag is present under JOB Tag in ICN file as per IDS v8 :");
				else
					System.out.println("InstallationId Tag is not present under JOB Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "CreDtTm tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			//BLOCK Validation
			
			public static String getISOTxSetSubmmsnGrpHdrNbOfTxsNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BLOCK Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BLOCK is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrNbOfTxsXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Block Tag Key:"+dateKey+"::Block Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrNbOfTxsXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"NbOfTxs").get(0);
				System.out.println("Block :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "Block tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("NbOfTxs Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("NbOfTxs Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "NbOfTxs tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnGrpHdrSndrIdNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				 String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BlkNbr is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BlkNbr is populating under JOB in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrSndrIdXpath);
				String dateKey =getMapKey(dateKeyVal);
				System.out.println("InstallationId Key:"+dateKey+"::InstallationId KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrSndrIdXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"SndrId").get(0);
				System.out.println("BlkNbr :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "BlkNbr tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("SndrId Nbr Tag is present under Block Tag in ICN file as per IDS v8 :");
				else
					System.out.println("SndrId Nbr Tag is not present under Block Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "SndrId tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnGrpHdrDrctPtcptNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BLOCK ImageType is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BLOCK ImageType is populating under BLOCK in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrDrctPtcptXpath);
				String dateKey =getMapKey(dateKeyVal);
				System.out.println("Block ImageType Key:"+dateKey+"::Block ImageType KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrDrctPtcptXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"DrctPtcpt").get(0);
				System.out.println("ImageType :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DrctPtcpt tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("DrctPtcpt Tag is present under BLOCK Tag in ICN file as per IDS v8 :");
				else
					System.out.println("DrctPtcpt Tag is not present under BLOCK Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "DrctPtcpt tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			//BATCH Validation
			
			public static String getISOTxSetSubmmsnGrpHdrTstIndNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BATCH Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BATCH is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrTstIndXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrTstIndXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"TstInd").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "Batch tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("TstInd Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("TstInd Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "TstInd tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			
			public static String getISOTxSetSubmmsnGrpHdrSgntrNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BATCH Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BATCH is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrSgntrXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetSubmissnGrpHdrSgntrXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"Sgntr").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "Batch tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("Sgntr Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("Sgntr Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "Sgntr tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			//TxSet NodeValue
			public static String validateISOTXSetTag(String captureInfoChilNode,String icnFilepath,String icnFileName) throws Exception{
				
				System.out.println("****************************************************************");
				System.out.println("Validating Transaction Set  is present in ICN as per IDS version 8 :"+captureInfoChilNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("****************************************************************");
				validationStepInformation("Validating Transaction Set is present in ICN as per IDS version 8 :"+captureInfoChilNode);
				validationStepInformation("****************************************************************");
				
				Map<String, String> TxSetSubmissionJobNodeVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetXpath);
				String nodeKey = getMapKey(TxSetSubmissionJobNodeVal);
				//coreName= coreNodeVal.tostatic String();
				//for(int i=0;i<coreNodeVal.size();i++){
			//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
				System.out.println("isoTxSetXpath :"+nodeKey);
				boolean flag =Component.verifyEquals(nodeKey, captureInfoChilNode, "TxSetSubmission TxSet tagname is as per IDSv8");			
				System.out.println("Validation is performed for TxSetSubmission TxSetVrsn :"+flag);
				publishResults(flag,nodeKey, captureInfoChilNode, "TxSet tagname is as per IDSv8");
				
				return nodeKey;			
			}
			public static String getISOTxSetSubmmsnTransactionSetIdNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BATCH Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BATCH is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetIdXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetIdXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"TxSetId").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "Batch tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("TxSetId Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("TxSetId Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "TxSetId tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnTxSetVrsnNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BATCH Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BATCH is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetVersionXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetVersionXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"TxSetVrsn").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "Batch tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("TxSetVrsn Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("TxSetVrsn Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "TxSetVrsn tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnTxSetColltngPtcptIdNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BATCH Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BATCH is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetColltngPtcptIdXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetColltngPtcptIdXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"ColltngPtcptId").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "ColltngPtcptId tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("ColltngPtcptId Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("ColltngPtcptId Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "ColltngPtcptId tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnTxSetCaptrdDtTmNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BATCH Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BATCH is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCaptrdDtTmXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("CaptrdDtTm Tag Key:"+dateKey+"::CaptrdDtTm Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCaptrdDtTmXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"CaptrdDtTm").get(0);
				System.out.println("CaptrdDtTm :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "CaptrdDtTm tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("CaptrdDtTm Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("CaptrdDtTm Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "CaptrdDtTm tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnTxSetSrcNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BATCH Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BATCH is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetSrcXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetSrcXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"Src").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "Src tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("Src Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("Src Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "Src tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnTxSetChanlRskTpNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BATCH Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BATCH is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetChanlRskTpXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetChanlRskTpXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"ChanlRskTp").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "Batch tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("ChanlRskTp Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("ChanlRskTp Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "ChanlRskTp tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnTxSetFrdChckOnlyIndNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating BATCH Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission BATCH is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetFrdChckOnlyIndXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetFrdChckOnlyIndXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"FrdChckOnlyInd").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "Batch tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("FrdChckOnlyInd Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("FrdChckOnlyInd Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "FrdChckOnlyInd tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnTxSetNbOfItmsNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating NbOfItms Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission NbOfItms is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetNbOfItmsXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetNbOfItmsXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"NbOfItms").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "NbOfItms tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("NbOfItms Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("NbOfItms Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "NbOfItms tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnTxSetEndPtIdNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating EndPtId Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission EndPtId is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetEndPtIdXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetEndPtIdXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"EndPtId").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "EndPtId tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("EndPtId Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("EndPtId Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "EndPtId tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			
			
			public static String getISOTxSetSubmmsnTxSetCrdtItmNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating EndPtId Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission EndPtId is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCrdtItmXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCrdtItmXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"CrdtItm").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "CrdtItm tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("CrdtItm Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("CrdtItm Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "CrdtItm tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			public static String getISOTxSetSubmmsnTxSetDbtItmNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
				// TODO Auto-generated method stub
			//	excelReader();
				String bdateNodeVal=null;
				System.out.println("****************************************************************");
				System.out.println("Validating DbtItm Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission DbtItm is populating in ICN as per IDS version 8 ");
				validationStepInformation("*********************************************************************************************************************");
				
				//now read xml and compare here
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
				//BusinessDate Validation 
				Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmXpath);
				 String dateKey =getMapKey(dateKeyVal);
				System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmXpath);
				bdateNodeVal =getNodeListValues(dateKeyVal1,"DbtItm").get(0);
				System.out.println("Batch :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DbtItm tagname is as per IDSv8");
				if(flagb==true)
					System.out.println("DbtItm Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
				else
					System.out.println("DbtItm Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
				
				publishResults(flagb,dateKey, coreChildNode, "DbtItm tagname is as per IDSv8");
				return bdateNodeVal;
			}
			
			//Occurence Validation
			
				
			
			public static void getGrpHdrMsgIdTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"MsgId tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			public static void getGrpHdrCreDtTmTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission CreDtTm Occurance for CreationDateTime in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"CreDtTm tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			public static void getGrpHdrNbOfTxsTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission NbOfTxs Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"NbOfTxs tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			public static void getSndrIdTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission SndrId Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"SndrId tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			
			public static void getDrctPtcptTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission DrctPtcpt Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"DrctPtcpt tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			
			public static void getTstIndTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission TstInd Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission TstInd Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+coreChildNode+" NodeValue "+batchNodeVal+" is present under "+coreChildNode+" tag in ICN":"TstInd tag is null","TxSetSubmission "+coreChildNode+" NodeValue "+batchNodeVal+" is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			public static void getSgntrTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission GrpHdr Sgntr Occurance in ISO file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ISO file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr Sgntr Occurance for Signature in MSG01+ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"Sgntr tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			public static void getTxSetIdTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
						
							System.out.println("*****TxSetSubmission TxSetId Occurance in ISO file validation starts here*********");
							validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ISO file validation starts here************");
							System.out.println("*****As per IDS version 8, TxSetSubmission TxSetId Occurance for Signature in MSG01+ICN is :"+occurs);
							validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
							publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"Sgntr tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
						}
			
			public static void getTxSetVrsnTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission Version Occurance in ISO file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ISO file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSet Version Occurance for Signature in MSG01+ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"Sgntr tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			public static void getColltngPtcptIdTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission NbOfTxs Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"NbOfTxs tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			public static void getCaptrdDtTmTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission SndrId Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"SndrId tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			
			public static void gettxSetSrcTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission DrctPtcpt Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"DrctPtcpt tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			
			public static void getChanlRskTpTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission TstInd Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission TstInd Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+coreChildNode+" NodeValue "+batchNodeVal+" is present under "+coreChildNode+" tag in ICN":"TstInd tag is null","TxSetSubmission "+coreChildNode+" NodeValue "+batchNodeVal+" is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			public static void getFrdChckOnlyIndTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission GrpHdr Sgntr Occurance in ISO file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ISO file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr Sgntr Occurance for Signature in MSG01+ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"Sgntr tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			public static void getNbOfItmsTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
						
							System.out.println("*****TxSetSubmission TxSetId Occurance in ISO file validation starts here*********");
							validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ISO file validation starts here************");
							System.out.println("*****As per IDS version 8, TxSetSubmission TxSetId Occurance for Signature in MSG01+ICN is :"+occurs);
							validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
							publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"Sgntr tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
						}
			
			public static void getEndPtIdTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission Version Occurance in ISO file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ISO file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSet Version Occurance for Signature in MSG01+ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"Sgntr tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}
			
			public static void getDbtItmTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
				System.out.println("*****TxSetSubmission TxSetId Occurance in ISO file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ISO file validation starts here************");
				System.out.println("*****As per IDS version 8, TxSetSubmission TxSetId Occurance for Signature in MSG01+ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"Sgntr tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
			}

public static void getCrdtItmTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****TxSetSubmission Version Occurance in ISO file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ISO file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSet Version Occurance for Signature in MSG01+ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"Sgntr tag is null","TxSetSubmission "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
}
			
			


public static void validateISOTxSetCreditSubmissionTags(String excelFilePath,String icnFilepath,String icnFileName) throws Exception {

	//icnCoreBusinessDate;
	FileInputStream inputStream = new FileInputStream(excelFilePath);
	
	Workbook wrkBk = new XSSFWorkbook(inputStream);
	int index = wrkBk.getSheetIndex("TransactionSetCreditItem");
	System.out.println(index);
	
	Sheet fsheet = wrkBk.getSheetAt(index);
	Iterator<Row> iterator = fsheet.iterator();
	List<String> listTag = new ArrayList<String>();
	
	List<Tags1> listOfTag = new ArrayList<>();
	
	while(iterator.hasNext()){
		Tags1 tag = new Tags1();
		
		Row nextRow = iterator.next();
		Iterator<Cell> cellIterator = nextRow.cellIterator();
		int rowIndex = nextRow.getRowNum();
		int count=1;
	
		if(rowIndex>0){
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				System.out.println("cell :"+cell);
				switch(count){
				case 1:
					tag.setRootNode(cell.getRichStringCellValue().toString());
					System.out.println("RootTag CaptureInfo val :"+cell.getRichStringCellValue().toString());
					count++;
					break;

				case 2:
					tag.setCaptureInfoChildNode(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode TxSetSubmission Tag val :"+cell.getRichStringCellValue().toString());
					count++;
					break;
					
				case 3:
					tag.setCaptureInfoChildNode1(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode1 val :"+cell.getRichStringCellValue().toString());
					count++;
					break;

				
				case 4:
					tag.setOccurs(cell.getRichStringCellValue().toString());
					count++;
					break;
				case 5:
					tag.setFixedVar(cell.getRichStringCellValue().toString());
					count++;
					break;
				case 6:
					tag.setType(cell.getRichStringCellValue().toString());
				//	int type = (int)cell.getNumericCellValue();
				//	tag.setType(Integer.toString(type));
					count++;
					break;
				case 7:
					int len = (int)cell.getNumericCellValue();
					tag.setLength(Integer.toString(len));
				
					/*String len = cell.getRichStringCellValue().toString();
					tag.setLength(len);
					*/
					count++;
					break;
				}
				
			}
			listOfTag.add(tag);
			System.out.println("listOfTag :"+listOfTag+"  listOfTag.add(tag)"+listOfTag.add(tag));
		}
	}
	wrkBk.close();
	inputStream.close();

	String grpHdrNodeVal;
	String coreChildNode,rootNode,captureChildNote1,captureChildNote2,captureChildNote3,captureChildNote4;
	
	String occurs,captureChildNote;
	String type,fixedVar,length;
	int i=0;
	for(Tags1 tags:listOfTag){
		System.out.println("listOfTag :"+listOfTag);
		//coreChildNode = tags.getCoreChildNode();
		rootNode =tags.getRootNode();
		
		captureChildNote = tags.getCaptureInfoChildNode(); //CaptureInfoChildNode  CaptureItem
		captureChildNote1 =tags.getCaptureInfoChildNode1(); //CaptureInfoChildNode1 JOB, BTACH,BLOCK,APGIN....
		captureChildNote2=tags.getCaptureInfoChildNode2();
	//	captureChildNote3=tags.getCaptureInfoChildNode3();
	//	captureChildNote4=tags.getCaptureInfoChildNode4();
		
		occurs=tags.getOccurs();
		type=tags.getType();
		length=tags.getLength();
		fixedVar=tags.getFixedVar();
		if(null!=rootNode){
			getRootNodeValidation(rootNode,icnFilepath,icnFileName); //TxSetSubmissn
			if(null!=captureChildNote){
				System.out.println("captureChildNote "+captureChildNote);
					getCaptureInfoChildNodeValidation(captureChildNote,icnFilepath,icnFileName); //TxSet
					if(null!=captureChildNote1){
						System.out.println("captureChildNote "+captureChildNote);
						getCaptureInfoChildNodeValidation(captureChildNote1,icnFilepath,icnFileName); //DbtItm
						if(null!=captureChildNote2){
							System.out.println("captureChildNote "+captureChildNote);
							getCaptureInfoChildNodeValidation(captureChildNote1,icnFilepath,icnFileName); //DbtItm
							
							
						switch(captureChildNote2){
						//case "TxSetSubmissn":
						
						case "CdtItmId":
							crdtItmIdNodeVal=getTxSetCrdtItmIdNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateCrdtItmIdTagOccurence(captureChildNote2,occurs,crdtItmIdNodeVal);
							validateFixedValLength(captureChildNote2,length,crdtItmIdNodeVal);
							break;
						case "CdtItmTp":
							crdtItmTpNodeVal=getTxSetCrdtItmTpNodeVal(captureChildNote2,icnFilepath,icnFileName);
							//validateCrdtItmCrdtItmTpTagOccurence(captureChildNote2,occurs,crdtItmTpNodeVal);
							validateCrdtItmIdTagOccurence(captureChildNote2,occurs,crdtItmTpNodeVal);
							validateVariableLength(captureChildNote2,length,crdtItmTpNodeVal);
							break;
						case "CdtItmTxCd":
							crdtItmTxCdNodeVal=getTxSetCrdtItmTxCdNodeVal(captureChildNote2,icnFilepath,icnFileName);
							//validateCrdtItmCrdtItmTxCdTagOccurence(captureChildNote2,occurs,crdtItmTxCdNodeVal);
							validateCrdtItmIdTagOccurence(captureChildNote2,occurs,crdtItmTxCdNodeVal);
							validateFixedValLength(captureChildNote2,length,crdtItmTxCdNodeVal);
							break;
						
						case "OnUsItmInd":
							crdtItmOnUsItmIndNodeVal=getTxSetCrdtItmOnUsItmIndNodeVal(captureChildNote2,icnFilepath,icnFileName);
							//validateCrdtItmOnUsItmIndTagOccurence(captureChildNote2,occurs,crdtItmOnUsItmIndNodeVal);
							validateCrdtItmIdTagOccurence(captureChildNote2,occurs,crdtItmOnUsItmIndNodeVal);
							validateVariableLength(captureChildNote2,length,crdtItmOnUsItmIndNodeVal);
							break;
						case "Amt":
							crdtItmAmtNodeVal=getTxSetCrdtItmAmtNodeVal(captureChildNote2,icnFilepath,icnFileName);
							//validateCrdtItmAmtTagOccurence(captureChildNote2,occurs,crdtItmAmtNodeVal);
							validateCrdtItmIdTagOccurence(captureChildNote2,occurs,crdtItmAmtNodeVal);
							validateVariableLength(captureChildNote2,length,crdtItmAmtNodeVal);
							break;
						case "BkCd":
							crdtItmBkCdNodeVal=getTxSetCrdtItmBkCdNodeVal(captureChildNote2,icnFilepath,icnFileName);
							//validateCrdtItmBkCdTagOccurence(captureChildNote2,occurs,crdtItmBkCdNodeVal);
							validateCrdtItmIdTagOccurence(captureChildNote2,occurs,crdtItmBkCdNodeVal);
							validateFixedValLength(captureChildNote2,length,crdtItmBkCdNodeVal);
							break;
						case "AcctNb":
							crdtItmAcctNbNodeVal=getTxSetCrdtItmAcctNbNodeVal(captureChildNote2,icnFilepath,icnFileName);
							//validateCrdtItmAcctNbTagOccurence(captureChildNote2,occurs,crdtItmAcctNbNodeVal);
							validateCrdtItmIdTagOccurence(captureChildNote2,occurs,crdtItmAcctNbNodeVal);
							validateFixedValLength(captureChildNote2,length,crdtItmAcctNbNodeVal);
							break;
						case "RefNb":
							crdtItmRefNbNodeVal=getTxSetCrdtItmRefNbNodeVal(captureChildNote2,icnFilepath,icnFileName);
							//validateCrdtItmRefNbTagOccurence(captureChildNote2,occurs,crdtItmRefNbNodeVal);
							validateCrdtItmIdTagOccurence(captureChildNote2,occurs,crdtItmRefNbNodeVal);
							validateVariableLength(captureChildNote2,length,crdtItmRefNbNodeVal);
							break;
						case "XtrnlDataRef":
							crdtItmXtrnlDataRefNodeVal=getTxSetCrdtItmXtrnlDataRefNodeVal(captureChildNote2,icnFilepath,icnFileName);
							//validateCrdtItmXtrnlDataRefTagOccurence(captureChildNote2,occurs,crdtItmXtrnlDataRefNodeVal);
							validateCrdtItmIdTagOccurence(captureChildNote2,occurs,crdtItmXtrnlDataRefNodeVal);
							validateVariableLength(captureChildNote2,length,crdtItmXtrnlDataRefNodeVal);
							break;						
							
						
		
	} // switch end
								
						}
						else{
							System.out.println("TxSet Tags are not present");
						}
	}
					else{
						System.out.println("ISO TransactionSetSubmission  tag is not present");
					}
	}
			}
			else
			{
				System.out.println("ISO TransactionSetSubmission tag is not present");
			}
	
	}	
	}

private static void validateCrdtItmCrdtItmTpTagOccurence(String captureChildNote2, String occurs,
		String crdtItmTpNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmCrdtItmTxCdTagOccurence(String captureChildNote2, String occurs,
		String crdtItmTxCdNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmOnUsItmIndTagOccurence(String captureChildNote2, String occurs,
		String crdtItmOnUsItmIndNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmAmtTagOccurence(String captureChildNote2, String occurs, String crdtItmAmtNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmBkCdTagOccurence(String captureChildNote2, String occurs,
		String crdtItmBkCdNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmAcctNbTagOccurence(String captureChildNote2, String occurs,
		String crdtItmAcctNbNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmRefNbTagOccurence(String captureChildNote2, String occurs,
		String crdtItmRefNbNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmXtrnlDataRefTagOccurence(String captureChildNote2, String occurs,
		String crdtItmXtrnlDataRefNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static String getTxSetCrdtItmXtrnlDataRefNodeVal(String coreChildNode, String icnFilepath,
		String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating NbOfItms Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission NbOfItms is populating in ICN as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
	
	//now read xml and compare here
	//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
	//BusinessDate Validation 
	Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCrdItmXtrnlDataRefXpath);
	 String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCrdItmXtrnlDataRefXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"XtrnlDataRef").get(0);
	System.out.println("Batch :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "NbOfItms tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("NbOfItms Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
	else
		System.out.println("NbOfItms Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;

}

private static String getTxSetCrdtItmRefNbNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCrdItmRefNbXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCrdItmRefNbXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"RefNb").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}


private static String getTxSetCrdtItmAcctNbNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCrdItmAcctNbXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCrdItmAcctNbXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"AcctNb").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}


private static String getTxSetCrdtItmBkCdNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCrdtItmBkCdXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCrdtItmBkCdXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"BkCd").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}


private static String getTxSetCrdtItmAmtNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCrdtItmAmtXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCrdtItmAmtXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"Amt").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static String getTxSetCrdtItmOnUsItmIndNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCrdtItmOnusItmIdXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCrdtItmOnusItmIdXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"OnUsItmInd").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static String getTxSetCrdtItmTxCdNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCrdtItmTxCdXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCrdtItmTxCdXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"CdtItmTxCd").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static String getTxSetCrdtItmTpNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCrdItmTpXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCrdItmTpXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"CdtItmTp").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static String getTxSetCrdtItmIdNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
	
	//now read xml and compare here
	//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
	//BusinessDate Validation 
	Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetCrdItmIdXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetCrdItmIdXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"CdtItmId").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;

}

private static void validateCrdtItmIdTagOccurence(String coreChildNode, String occurs, String batchNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission CreditItmId Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission TstInd Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+coreChildNode+" NodeValue "+batchNodeVal+" is present under "+coreChildNode+" tag in ICN":"TstInd tag is null","TxSetSubmission "+coreChildNode+" NodeValue "+batchNodeVal+" is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");

	
}


/*
private static void validateCrdtItmCrdtItmTpTagLength(String captureChildNote2, String length,
		String crdtItmTpNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmCrdtItmTxCdTagLength(String captureChildNote2, String length,
		String crdtItmTxCdNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmOnUsItmIndTagLength(String captureChildNote2, String length,
		String crdtItmOnUsItmIndNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmAmtTagLength(String captureChildNote2, String length, String crdtItmAmtNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmBkCdTagLength(String captureChildNote2, String length, String crdtItmBkCdNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmImgAcctNbTagLength(String captureChildNote2, String length,
		String crdtItmAcctNbNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmRefNbTagLength(String captureChildNote2, String length,
		String crdtItmRefNbNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCrdtItmXtrnlDataRefTagLength(String captureChildNote2, String length,
		String crdtItmXtrnlDataRefNodeVal2) {
	// TODO Auto-generated method stub
	
}
*/



public static void validateISOTxSetDebitSubmissionTags(String excelFilePath,String icnFilepath,String icnFileName) throws Exception {

	//icnCoreBusinessDate;
	FileInputStream inputStream = new FileInputStream(excelFilePath);
	
	Workbook wrkBk = new XSSFWorkbook(inputStream);
	int index = wrkBk.getSheetIndex("TransactionSetIdDebitItem");
	System.out.println(index);
	
	Sheet fsheet = wrkBk.getSheetAt(index);
	Iterator<Row> iterator = fsheet.iterator();
	List<String> listTag = new ArrayList<String>();
	
	List<Tags1> listOfTag = new ArrayList<>();
	
	while(iterator.hasNext()){
		Tags1 tag = new Tags1();
		
		Row nextRow = iterator.next();
		Iterator<Cell> cellIterator = nextRow.cellIterator();
		int rowIndex = nextRow.getRowNum();
		int count=1;
	
		if(rowIndex>0){
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				System.out.println("cell :"+cell);
				switch(count){
				case 1:
					tag.setRootNode(cell.getRichStringCellValue().toString());
					System.out.println("RootTag CaptureInfo val :"+cell.getRichStringCellValue().toString());
					count++;
					break;

				case 2:
					tag.setCaptureInfoChildNode(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode CaptureItem Tag val :"+cell.getRichStringCellValue().toString());
					count++;
					break;
					
				case 3:
					tag.setCaptureInfoChildNode1(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode1 val :"+cell.getRichStringCellValue().toString());
					count++;
					break;

				case 4:
					//listTag.add(cell.getRichstatic StringCellValue.tostatic String());
					tag.setCaptureInfoChildNode2(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode2 ChildNode Tag val 1:"+cell.getRichStringCellValue().toString());
					count++;
					break;
				case 5:
					//listTag.add(cell.getRichstatic StringCellValue.tostatic String());
					tag.setCaptureInfoChildNode3(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode3 ChildNode Tag val 1:"+cell.getRichStringCellValue().toString());
					count++;
					break;
				case 6:
					tag.setOccurs(cell.getRichStringCellValue().toString());
					count++;
					break;
				case 7:
					tag.setFixedVar(cell.getRichStringCellValue().toString());
					count++;
					break;
				case 8:
					tag.setType(cell.getRichStringCellValue().toString());
				//	int type = (int)cell.getNumericCellValue();
				//	tag.setType(Integer.toString(type));
					count++;
					break;
				case 9:
					int len = (int)cell.getNumericCellValue();
					tag.setLength(Integer.toString(len));
				
					/*String len = cell.getRichStringCellValue().toString();
					tag.setLength(len);
					*/
					count++;
					break;
				}
				
			}
			listOfTag.add(tag);
			System.out.println("listOfTag :"+listOfTag+"  listOfTag.add(tag)"+listOfTag.add(tag));
		}
	}
	wrkBk.close();
	inputStream.close();

	String jobNodeVal;
	String coreChildNode,rootNode,captureChildNote1,captureChildNote2,captureChildNote3,captureChildNote4;
	
	String occurs,captureChildNote;
	String type,fixedVar,length;
	int i=0;
	for(Tags1 tags:listOfTag){
		System.out.println("listOfTag :"+listOfTag);
		//coreChildNode = tags.getCoreChildNode();
		rootNode =tags.getRootNode();
		
		captureChildNote = tags.getCaptureInfoChildNode(); //CaptureInfoChildNode  CaptureItem
		captureChildNote1 =tags.getCaptureInfoChildNode1(); //CaptureInfoChildNode1 JOB, BTACH,BLOCK,APGIN....
		captureChildNote2=tags.getCaptureInfoChildNode2();
	//	captureChildNote3=tags.getCaptureInfoChildNode3();
	//	captureChildNote4=tags.getCaptureInfoChildNode4();
		
		occurs=tags.getOccurs();
		type=tags.getType();
		length=tags.getLength();
		fixedVar=tags.getFixedVar();
		if(null!=rootNode){
			getRootNodeValidation(rootNode,icnFilepath,icnFileName); //TxSetSubmissn
			if(null!=captureChildNote){
				System.out.println("captureChildNote "+captureChildNote);
					getCaptureInfoChildNodeValidation(captureChildNote,icnFilepath,icnFileName); //TxSet
					if(null!=captureChildNote1){
						System.out.println("captureChildNote "+captureChildNote);
						getCaptureInfoChildNodeValidation(captureChildNote1,icnFilepath,icnFileName); //DbtItm
						if(null!=captureChildNote2){
							System.out.println("captureChildNote "+captureChildNote);
							getCaptureInfoChildNodeValidation(captureChildNote1,icnFilepath,icnFileName); //DbtItm
							
							
						switch(captureChildNote2){
							
						case "DbtItmId":
							dbtItmIdNodeVal=getTxSetDbtItmIdNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateDbtItmIdTagOccurence(captureChildNote2,occurs,dbtItmIdNodeVal);
							validateFixedValLength(captureChildNote2,length,dbtItmIdNodeVal);
							break;
						case "DbtItmTp":
							dbtItmTpNodeVal=getTxSetDbtItmTpNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateDbtItmDbtItmTpTagOccurence(captureChildNote2,occurs,dbtItmTpNodeVal);
							validateVariableLength(captureChildNote2,length,dbtItmTpNodeVal);
							break;
						case "DbtItmTxCd":
							dbtItmTxCdNodeVal=getTxSetDbtItmTxCdNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateDbtItmDbtItmTxCdTagOccurence(captureChildNote2,occurs,dbtItmTxCdNodeVal);
							validateFixedValLength(captureChildNote2,length,dbtItmTxCdNodeVal);
							break;
						case "RpresntdItmInd":
							dbtItmRpresntdItmIndNodeVal=getTxSetDbtItmRpresntdItmIndNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateDbtItmRpresntdItmIndTagOccurence(captureChildNote2,occurs,dbtItmRpresntdItmIndNodeVal);
							validateVariableLength(captureChildNote2,length,dbtItmRpresntdItmIndNodeVal);
							break;
						case "OnUsItmInd":
							dbtItmOnUsItmIndNodeVal=getTxSetDbtItmOnUsItmIndNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateDbtItmOnUsItmIndTagOccurence(captureChildNote2,occurs,dbtItmOnUsItmIndNodeVal);
							validateVariableLength(captureChildNote2,length,dbtItmOnUsItmIndNodeVal);
							break;
						case "Amt":
							dbtItmAmtNodeVal=getTxSetDbtItmAmtNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateDbtItmOnUsItmIndTagOccurence(captureChildNote2,occurs,dbtItmAmtNodeVal);
							validateVariableLength(captureChildNote2,length,dbtItmAmtNodeVal);
							break;
						case "BkCd":
							dbtItmBkCdNodeVal=getTxSetDbtItmBkCdNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateDbtItmOnUsItmIndTagOccurence(captureChildNote2,occurs,dbtItmBkCdNodeVal);
							validateFixedValLength(captureChildNote2,length,dbtItmBkCdNodeVal);
							break;
						case "AcctNb":
							dbtItmAcctNbNodeVal=getTxSetDbtItmAcctNbNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateDbtItmOnUsItmIndTagOccurence(captureChildNote2,occurs,dbtItmAcctNbNodeVal);
							validateFixedValLength(captureChildNote2,length,dbtItmAcctNbNodeVal);
							break;
						case "SrlNb":
							dbtItmSrlNbNodeVal=getTxSetDbtItmSrlNbNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateDbtItmOnUsItmIndTagOccurence(captureChildNote2,occurs,dbtItmSrlNbNodeVal);
							validateVariableLength(captureChildNote2,length,dbtItmSrlNbNodeVal);
							break;
						case "XtrnlDataRef":
							dbtItmXtrnlDataRefNodeVal=getTxSetDbtItmXtrnlDataRefNodeVal(captureChildNote2,icnFilepath,icnFileName);
							validateDbtItmOnUsItmIndTagOccurence(captureChildNote2,occurs,dbtItmXtrnlDataRefNodeVal);
							validateVariableLength(captureChildNote2,length,dbtItmXtrnlDataRefNodeVal);
							break;
								
						}
						}		
					}
			}	
		}


	}
}
			
			
private static String getTxSetDbtItmIdNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmIdXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmIdXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"DbtItmId").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static void validateDbtItmIdTagLength(String captureChildNote2, String length, String dbtItmIdNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static String getTxSetDbtItmTpNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmTpXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmTpXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"DbtItmTp").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static void validateDbtItmDbtItmTpTagLength(String captureChildNote2, String length, String dbtItmTpNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static String getTxSetDbtItmTxCdNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmTxCdXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmTxCdXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"DbtItmTxCd").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static String getTxSetDbtItmRpresntdItmIndNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmRepresentItmIndXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmRepresentItmIndXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"RpresntdItmInd").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;

}

private static String getTxSetDbtItmOnUsItmIndNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmOnUsXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmOnUsXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"OnUsItmInd").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static String getTxSetDbtItmAmtNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmAmtXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmAmtXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"Amt").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static String getTxSetDbtItmBkCdNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmBkCdXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmBkCdXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"BkCd").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static String getTxSetDbtItmAcctNbNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmAcctNbXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmAcctNbXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"AcctNb").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static String getTxSetDbtItmSrlNbNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmSrlNbXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmSrlNbXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"SrlNb").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static String getTxSetDbtItmXtrnlDataRefNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" is populating under TxSetSubmission in ISO as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmXtrnlDataRefXpath);
	String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetDbtItmXtrnlDataRefXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"XtrnlDataRef").get(0);
	System.out.println("DebitAmount :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
	else
		System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, dateKey+" tagname is as per IDSv8");
	return bdateNodeVal;
}

private static void validateDbtItmDbtItmTxCdTagLength(String captureChildNote2, String length,
		String dbtItmTxCdNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmRpresntdItmIndTagLength(String captureChildNote2, String length,
		String dbtItmRpresntdItmIndNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmOnUsItmIndTagLength(String captureChildNote2, String length,
		String dbtItmOnUsItmIndNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmAmtTagLength(String captureChildNote2, String length, String dbtItmAmtNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmBkCdTagLength(String captureChildNote2, String length, String dbtItmBkCdNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmImgAcctNbTagLength(String captureChildNote2, String length,
		String dbtItmAcctNbNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmSrlNbTagLength(String captureChildNote2, String length, String dbtItmSrlNbNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmXtrnlDataRefTagLength(String captureChildNote2, String length,
		String dbtItmXtrnlDataRefNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmXtrnlDataRefTagOccurence(String captureChildNote2, String occurs,
		String dbtItmXtrnlDataRefNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmSrlNbTagOccurence(String captureChildNote2, String occurs,
		String dbtItmSrlNbNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmAcctNbTagOccurence(String captureChildNote2, String occurs,
		String dbtItmAcctNbNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmBkCdTagOccurence(String captureChildNote2, String occurs, String dbtItmBkCdNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmAmtTagOccurence(String captureChildNote2, String occurs, String dbtItmAmtNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmOnUsItmIndTagOccurence(String captureChildNote2, String occurs,
		String dbtItmOnUsItmIndNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmRpresntdItmIndTagOccurence(String captureChildNote2, String occurs,
		String dbtItmRpresntdItmIndNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmDbtItmTxCdTagOccurence(String captureChildNote2, String occurs,
		String dbtItmTxCdNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmDbtItmTpTagOccurence(String captureChildNote2, String occurs,
		String dbtItmTpNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateDbtItmIdTagOccurence(String coreChildNode, String occurs, String batchNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission CreditItmId Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission TstInd Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"TxSetSubmission "+coreChildNode+" NodeValue "+batchNodeVal+" is present under "+coreChildNode+" tag in ICN":"TstInd tag is null","TxSetSubmission "+coreChildNode+" NodeValue "+batchNodeVal+" is present under "+coreChildNode+" tag in ICN","TxSetSubmission "+coreChildNode+" tag occurence validation performed");
	
}

public static void validateISOTxSetCrdtItmTags(String excelFilePath,String icnFilepath,String icnFileName) throws Exception {

	//icnCoreBusinessDate;
	FileInputStream inputStream = new FileInputStream(excelFilePath);
	
	Workbook wrkBk = new XSSFWorkbook(inputStream);
	int index = wrkBk.getSheetIndex("CrdtItm");
	System.out.println(index);
	
	Sheet fsheet = wrkBk.getSheetAt(index);
	Iterator<Row> iterator = fsheet.iterator();
	List<String> listTag = new ArrayList<String>();
	
	List<Tags1> listOfTag = new ArrayList<>();
	
	while(iterator.hasNext()){
		Tags1 tag = new Tags1();
		
		Row nextRow = iterator.next();
		Iterator<Cell> cellIterator = nextRow.cellIterator();
		int rowIndex = nextRow.getRowNum();
		int count=1;
	
		if(rowIndex>0){
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				System.out.println("cell :"+cell);
				switch(count){
				case 1:
					tag.setRootNode(cell.getRichStringCellValue().toString());
					System.out.println("RootTag CaptureInfo val :"+cell.getRichStringCellValue().toString());
					count++;
					break;

				case 2:
					tag.setCaptureInfoChildNode(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode CaptureItem Tag val :"+cell.getRichStringCellValue().toString());
					count++;
					break;
					
				case 3:
					tag.setCaptureInfoChildNode1(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode1 val :"+cell.getRichStringCellValue().toString());
					count++;
					break;

				case 4:
					//listTag.add(cell.getRichstatic StringCellValue.tostatic String());
					tag.setCaptureInfoChildNode2(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode2 ChildNode Tag val 1:"+cell.getRichStringCellValue().toString());
					count++;
					break;
				case 5:
					//listTag.add(cell.getRichstatic StringCellValue.tostatic String());
					tag.setCaptureInfoChildNode3(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode3 ChildNode Tag val 1:"+cell.getRichStringCellValue().toString());
					count++;
					break;
				case 6:
					tag.setOccurs(cell.getRichStringCellValue().toString());
					count++;
					break;
				case 7:
					tag.setFixedVar(cell.getRichStringCellValue().toString());
					count++;
					break;
				case 8:
					tag.setType(cell.getRichStringCellValue().toString());
				//	int type = (int)cell.getNumericCellValue();
				//	tag.setType(Integer.toString(type));
					count++;
					break;
				case 9:
					int len = (int)cell.getNumericCellValue();
					tag.setLength(Integer.toString(len));
				
					/*String len = cell.getRichStringCellValue().toString();
					tag.setLength(len);
					*/
					count++;
					break;
				}
				
			}
			listOfTag.add(tag);
			System.out.println("listOfTag :"+listOfTag+"  listOfTag.add(tag)"+listOfTag.add(tag));
		}
	}
	wrkBk.close();
	inputStream.close();

	String jobNodeVal;
	String coreChildNode,rootNode,captureChildNote1,captureChildNote2,captureChildNote3,captureChildNote4;
	
	String occurs,captureChildNote;
	String type,fixedVar,length;
	int i=0;
	for(Tags1 tags:listOfTag){
		System.out.println("listOfTag :"+listOfTag);
		//coreChildNode = tags.getCoreChildNode();
		rootNode =tags.getRootNode();
		
		captureChildNote = tags.getCaptureInfoChildNode(); //CaptureInfoChildNode  CaptureItem
		captureChildNote1 =tags.getCaptureInfoChildNode1(); //CaptureInfoChildNode1 JOB, BTACH,BLOCK,APGIN....
		captureChildNote2=tags.getCaptureInfoChildNode2();
		captureChildNote3=tags.getCaptureInfoChildNode3();
	//	captureChildNote4=tags.getCaptureInfoChildNode4();
		
		occurs=tags.getOccurs();
		type=tags.getType();
		length=tags.getLength();
		fixedVar=tags.getFixedVar();
		if(null!=rootNode){
			getRootNodeValidation(rootNode,icnFilepath,icnFileName); //TxSetSubmissn
			if(null!=captureChildNote){
				System.out.println("captureChildNote "+captureChildNote);
					getCaptureInfoChildNodeValidation(captureChildNote,icnFilepath,icnFileName); //TxSet
					if(null!=captureChildNote1){
						System.out.println("captureChildNote "+captureChildNote);
						getCaptureInfoChildNodeValidation(captureChildNote1,icnFilepath,icnFileName); //crdtItm
						if(null!=captureChildNote2){
							System.out.println("captureChildNote "+captureChildNote);
							getCaptureInfoChildNodeValidation(captureChildNote1,icnFilepath,icnFileName); //crdtItm
							
			
						switch(captureChildNote2){
							case "ItmImgData":
								crdtItmImgDataNodeVal=getTxSetCrdtItmImgDataNodeVal(captureChildNote2,icnFilepath,icnFileName);
							//getJobSubTagsOccuranceValidation(captureChildNote1,occurs,businessDateNodeVal);
							System.out.println("*****As per IDS version 8, ChildCoreNode BusinessDate DataType in ICN is :"+type);
							System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);
							System.out.println("Lenght validation is not required for BusinessDate in IDS v8 "+length);
						//	getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal);
							switch(captureChildNote3){
								case "Img":
									crdtItmImgNodeVal=getTxSetCrdtItmImgNodeVal(captureChildNote3,icnFilepath,icnFileName);
									validateCrdtItmImgTagOccurence(captureChildNote3,occurs,crdtItmImgNodeVal);
									validateCrdtItmImgTagLength(captureChildNote3,length,crdtItmImgNodeVal);
									break;
								case "ImgHash":
									crdtItmImgHashNodeVal=getTxSetCrdtItmImgHashNodeVal(captureChildNote3,icnFilepath,icnFileName);
									validateCrdtItmImgHashTagOccurence(captureChildNote3,occurs,crdtItmImgHashNodeVal);
									validateCrdtItmImgHashTagLength(captureChildNote3,length,crdtItmImgHashNodeVal);
									break;
							
							}
							break;
							
								case "ItmImgMetaData":
									crdtItmImgMetaDataNodeVal=getTxSetCrdtItmMetaDataNodeVal(captureChildNote2,icnFilepath,icnFileName);
									validateCrdtItmItmImgMetaDataTagOccurence(captureChildNote2,occurs,crdtItmImgMetaDataNodeVal);
									//getJobInstallationIdLenghtValidation(captureChildNote1,length,installationIdNodeVal);
									switch(captureChildNote3){
									case "ImgCaptrId":
										crdtItmImgCaptrIdNodeVal=getTxSetCrdtItmCaptrIdNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCrdtItmImgCaptrIdTagOccurence(captureChildNote3,occurs,crdtItmImgCaptrIdNodeVal);
										validateCrdtItmImgCaptrIdTagLength(captureChildNote3,length,crdtItmImgCaptrIdNodeVal);
										break;
									case "ImgCaptrDvcId":
										crdtItmImgCaptrDvcIdNodeVal=getTxSetCrdtItmCaptrCaptrDvcIdNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCrdtItmImgCaptrDvcIdTagOccurence(captureChildNote3,occurs,crdtItmImgCaptrDvcIdNodeVal);
										validateCrdtItmImgCaptrDvcIdTagLength(captureChildNote3,length,crdtItmImgCaptrDvcIdNodeVal);
										break;
									case "ImgCaptrLctn":
										crdtItmImgCaptrLctnNodeVal=getTxSetCrdtItmCaptrCaptrLctnNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCrdtItmImgCaptrLctnTagOccurence(captureChildNote3,occurs,crdtItmImgCaptrLctnNodeVal);
										validateCrdtItmImgCaptrLctnTagLength(captureChildNote3,length,crdtItmImgCaptrLctnNodeVal);
										break;
									case "ImgCaptrDtTm":
										crdtItmImgCaptrDtTmNodeVal=getTxSetCrdtItmCaptrCaptrDtTmNodeVal(captureChildNote2,icnFilepath,icnFileName);
										validateCrdtItmImgCaptrDtTmTagOccurence(captureChildNote3,occurs,crdtItmImgCaptrDtTmNodeVal);
										validateCrdtItmImgCaptrDtTmTagLength(captureChildNote3,length,crdtItmImgCaptrDtTmNodeVal);
										break;
								
								}
								
									break;
									
								case "CdtItmFrdData":
									crdtItmFrdDataNodeVal=getTxSetCrdtItmFrdDataNodeVal(captureChildNote2,icnFilepath,icnFileName);
									validateTxSetCrdtItmFrdDataTgOccurence(captureChildNote2,occurs,crdtItmFrdDataNodeVal);
									//getJobInstallationIdLenghtValidation(captureChildNote1,length,installationIdNodeVal);
									switch(captureChildNote3){
									case "ChqAtRskInd":
										crdtItmFrdChqAtRskIndNodeVal=getTxSetCrdtItmFrdDataChqAtRskIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCrdtItmFrdDataChqAtRskIndTagOccurence(captureChildNote3,occurs,crdtItmFrdChqAtRskIndNodeVal);
										validateCrdtItmFrdDataChqAtRskIndTagLength(captureChildNote3,length,crdtItmFrdChqAtRskIndNodeVal);
										break;
									case "BnfcryNm":
										crdtItmFrdDataBnfcryNmNodeVal=getTxSetCrdtItmFrdDataBnfcryNmNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCrdtItmFrdDataBnfcryNmTagOccurence(captureChildNote3,occurs,crdtItmFrdDataBnfcryNmNodeVal);
										validateCrdtItmFrdDataBnfcryNmTagLength(captureChildNote3,length,crdtItmFrdDataBnfcryNmNodeVal);
										break;
									case "VrtlCdtInd":
										crdtItmFrdDataVrtlCdtIndNodeVal=getTxSetCrdtItmFrdDataVrtlCdtIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCrdtItmFrdDataVrtlCdtIndTagOccurence(captureChildNote3,occurs,crdtItmFrdDataVrtlCdtIndNodeVal);
										validateCrdtItmFrdDataVrtlCdtIndTagLength(captureChildNote3,length,crdtItmFrdDataVrtlCdtIndNodeVal);
										break;
									case "RefData":
										crdtItmFrdDataRefDataNodeVal=getTxSetCrdtItmFrdDataRefDataNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCrdtItmFrdDataRefDataTagOccurence(captureChildNote3,occurs,crdtItmFrdDataRefDataNodeVal);
										validateCrdtItmFrdDataRefDataTagLength(captureChildNote3,length,crdtItmFrdDataRefDataNodeVal);
									break;
									case "CshAmt":
										crdtItmFrdDataCshAmtNodeVal=getTxSetCrdtItmFrdDataCshAmtNodeVal(captureChildNote2,icnFilepath,icnFileName);
										validateCrdtItmFrdDataCshAmtTagOccurence(captureChildNote3,occurs,crdtItmFrdDataCshAmtNodeVal);
										validateCrdtItmFrdDataCshAmtTagLength(captureChildNote3,length,crdtItmFrdDataCshAmtNodeVal);
										break;
									case "FnddAmt":
										crdtItmFrdDataFnddAmtNodeVal=getTxSetCrdtItmFrdDataFndAmtNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCrdtItmFrdDataFndAmtTagOccurence(captureChildNote3,occurs,crdtItmFrdDataFnddAmtNodeVal);
										validateCrdtItmFrdDataFndAmtTagLength(captureChildNote3,length,crdtItmFrdDataFnddAmtNodeVal);
										break;
									case "NonFunddAmt":
										crdtItmFrdDataNonFunddAmtNodeVal=getTxSetCrdtItmNonFunddAmtNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCrdtItmFrdDataNonFunddAmtTagOccurence(captureChildNote3,occurs,crdtItmFrdDataNonFunddAmtNodeVal);
										validateCrdtItmFrdDataNonFunddAmtTagLength(captureChildNote3,length,crdtItmFrdDataNonFunddAmtNodeVal);
										break;
									case "NbOfCdtsOrDbts":
										crdtItmFrdDataNbOfCdtsOrDbtsNodeVal=getTxSetCrdtItmNbOfCdtsOrDbtsNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCrdtItmFrdDataNbOfCdtsOrDbtsTagOccurence(captureChildNote3,occurs,crdtItmFrdDataNbOfCdtsOrDbtsNodeVal);
										validateCrdtItmFrdDataNbOfCdtsOrDbtsTagLength(captureChildNote3,length,crdtItmFrdDataNbOfCdtsOrDbtsNodeVal);
										break;
									}
									break;
								case "RprdItm":
									dbtItmRprdItmNodeVal=getTxSetCrdtItmRprdItmNodeVal(captureChildNote2,icnFilepath,icnFileName);
								//	validateJobWorkTypeNbrTagOccurance(captureChildNote3,occurs,workTypeNbrNodeVal);
									
									switch(captureChildNote3){
									case "BkCdRprdInd":
										crdtItmBkCdRprdIndNodeVal=getTxSetCrdtItmBkCdRprdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateBkCdRprdIndTagOccurence(captureChildNote3,occurs,crdtItmBkCdRprdIndNodeVal);
										validateBkCdRprdIndTagLength(captureChildNote3,length,crdtItmBkCdRprdIndNodeVal);
										break;
										
									case "AcctNbRprdInd":
										crdtItmAcctNbRprdIndNodeVal=getTxSetCrdtItmAcctNbRprdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateAcctNbRprdIndTagOccurence(captureChildNote3,occurs,dbtItmAcctNbRprdIndNodeVal);
										validateAcctNbRprdIndTagLength(captureChildNote3,length,dbtItmAcctNbRprdIndNodeVal);
										break;
										
									case "AmtRprdInd":
										crdtItmAmtRprdIndNodeVal=getTxSetCrdtItmAmtRprdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateAmtRprdIndTagOccurance(captureChildNote3,occurs,crdtItmAmtRprdIndNodeVal);
										validateAmtRprdIndTagLength(captureChildNote3,length,crdtItmAmtRprdIndNodeVal);
										break;
										
									case "RefNbRprdInd":
										crdtItmRefNbRprdIndNodeVal=getTxSetCrdtItmRefNbRprdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateCreditItmRefNbRprdIndTagOccurance(captureChildNote3,occurs,crdtItmRefNbRprdIndNodeVal);
										validateCreditItmRefNbRprdIndTagLength(captureChildNote3,length,crdtItmRefNbRprdIndNodeVal);
										break;
									}
									
									break;
								case "DfltdItm":
									creditDfltdItmNodeVal=getTxSetCrdtItmDfltdItmNodeVal(captureChildNote2,icnFilepath,icnFileName);
									
									switch(captureChildNote3){
									
									case "BkCdDfltdInd":
										creditItmBkCdDfltdIndNodeVal=getTxSetCrdtItmBkCdDfltdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateTxSetCrdtItmBkCdDfltdIndTagOccurance(captureChildNote3,occurs,dbtItmBkCdDfltdIndNodeVal);
										validateTxSetCrdtItmBkCdDfltdIndTagLength(captureChildNote3,length,dbtItmBkCdDfltdIndNodeVal);	
										break;
										
									case "AcctNbDfltdInd":
										creditItmAcctNbDfltdIndNodeVal=getTxSetCrdtItmAcctNbDfltdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateTxSetCrdtItmAcctNbDfltdIndTagOccurance(captureChildNote3,occurs,dbtItmAcctNbDfltdIndNodeVal);
										validateTxSetCrdtItmAcctNbDfltdIndTagLength(captureChildNote3,length,dbtItmAcctNbDfltdIndNodeVal);	
										break;
										
									case "RefNbDfltdInd":
										creditItmRefNbDfltdIndNodeVal=getTxSetCrdtItmRefNbDfltdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateTxSetCrdtItmRefNbDfltdIndTagOccurance(captureChildNote3,occurs,creditItmRefNbDfltdIndNodeVal);
										validateTxSetCrdtItmRefNbDfltdIndTagLength(captureChildNote3,length,creditItmRefNbDfltdIndNodeVal);
										break;
								
									}
									
									break;
							
							
							
					
		}
	}		
	}
	}	
	}


}

}
			
private static void validateCreditItmRefNbRprdIndTagLength(String captureChildNote3, String length,
		String crdtItmRefNbRprdIndNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateCreditItmRefNbRprdIndTagOccurance(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateCrdtItmFrdDataNonFunddAmtTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateCrdtItmFrdDataNonFunddAmtTagLength(String captureChildNote3, String length,
		String creditItemNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((creditItemNodeVal.length())<=Integer.parseInt(length),((creditItemNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+creditItemNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+creditItemNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

	
}

private static String getTxSetCrdtItmNonFunddAmtNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmDfltdItmNodeVal(String captureChildNote2, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static void validateTxSetCrdtItmBkCdDfltdIndTagLength(String captureChildNote3, String length,
		String crdtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****Credit "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Credit DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((crdtItmNodeVal.length())<=Integer.parseInt(length),((crdtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateTxSetCrdtItmBkCdDfltdIndTagOccurance(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static String getTxSetCrdtItmBkCdDfltdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static void validateTxSetCrdtItmRefNbDfltdIndTagLength(String captureChildNote3, String length,
		String dbtItmSrlNbDfltdIndNodeVal2) {
	// TODO Auto-generated method stub
	
}

private static void validateTxSetCrdtItmRefNbDfltdIndTagOccurance(String captureChildNote3, String occurs,	String crdtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static String getTxSetCrdtItmRefNbDfltdIndNodeVal(String captureChildNote3, String icnFilepath,	String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmAcctNbDfltdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static void validateTxSetCrdtItmAcctNbDfltdIndTagOccurance(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateTxSetCrdtItmAcctNbDfltdIndTagLength(String captureChildNote3, String length,
		String crdtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit DfltdItm AccDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****Credit "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit DfltdItm AccDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Credit DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((crdtItmNodeVal.length())<=Integer.parseInt(length),((crdtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmImgHashTagLength(String captureChildNote3, String length,
		String crdtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit DfltdItm AccDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****Credit "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit DfltdItm AccDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Credit DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((crdtItmNodeVal.length())==Integer.parseInt(length),((crdtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmImgHashTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateCrdtItmImgTagLength(String captureChildNote3, String length, String crdtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit DfltdItm AccDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****Credit "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit DfltdItm AccDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Credit DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((crdtItmNodeVal.length())!=0,((crdtItmNodeVal.length())!=0)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmImgTagOccurence(String captureChildNote3, String occurs, String crdtItmImgNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmImgNodeVal2!=null,(crdtItmImgNodeVal2!=null)?"TxSetSubmission "+crdtItmImgNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmImgNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static String getTxSetCrdtItmRefNbRprdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmAmtRprdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmAcctNbRprdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmBkCdRprdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmRprdItmNodeVal(String captureChildNote2, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmFrdDataVrtlCdtIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmFrdDataRefDataNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmFrdDataCshAmtNodeVal(String captureChildNote2, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmFrdDataFndAmtNodeVal(String captureChildNote3, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmNbOfCdtsOrDbtsNodeVal(String captureChildNote3, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static void validateCrdtItmFrdDataNbOfCdtsOrDbtsTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmFrdDataNbOfCdtsOrDbtsTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateCrdtItmFrdDataFndAmtTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())<=Integer.parseInt(length),((dbtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmFrdDataFndAmtTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateCrdtItmFrdDataCshAmtTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())<=Integer.parseInt(length),((dbtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmFrdDataCshAmtTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateCrdtItmFrdDataRefDataTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())<=Integer.parseInt(length),((dbtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmFrdDataRefDataTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateCrdtItmFrdDataVrtlCdtIndTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())<=Integer.parseInt(length),((dbtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmFrdDataVrtlCdtIndTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateCrdtItmFrdDataBnfcryNmTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())<=Integer.parseInt(length),((dbtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmFrdDataBnfcryNmTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static String getTxSetCrdtItmFrdDataBnfcryNmNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static void validateCrdtItmFrdDataChqAtRskIndTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())<=Integer.parseInt(length),((dbtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmFrdDataChqAtRskIndTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static String getTxSetCrdtItmFrdDataChqAtRskIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static void validateTxSetCrdtItmFrdDataTgOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}
private static String getTxSetCrdtItmFrdDataNodeVal(String captureChildNote2, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static void validateCrdtItmImgCaptrDtTmTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())!=0,((dbtItmNodeVal.length())!=0)?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmImgCaptrDtTmTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateCrdtItmImgCaptrLctnTagLength(String captureChildNote3, String length, String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())<=Integer.parseInt(length),((dbtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmImgCaptrLctnTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateCrdtItmImgCaptrDvcIdTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())<=Integer.parseInt(length),((dbtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateCrdtItmImgCaptrDvcIdTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateCrdtItmImgCaptrIdTagLength(String captureChildNote3, String length,	String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())<=Integer.parseInt(length),((dbtItmNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

	
}

private static void validateCrdtItmImgCaptrIdTagOccurence(String captureChildNote3, String occurs,
String crdtItmNodeVal) {
System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");


}

private static String getTxSetCrdtItmMetaDataNodeVal(String captureChildNote2, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static void validateCrdtItmItmImgMetaDataTagOccurence(String captureChildNote3, String occurs,
		String crdtItmNodeVal) {
	System.out.println("*****TxSetSubmission Credit Img Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission Credit Img Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(crdtItmNodeVal!=null,(crdtItmNodeVal!=null)?"TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag is null","TxSetSubmission "+crdtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static String getTxSetCrdtItmCaptrCaptrDtTmNodeVal(String captureChildNote2, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmCaptrCaptrLctnNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmCaptrCaptrDvcIdNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmCaptrIdNodeVal(String captureChildNote3, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmImgHashNodeVal(String captureChildNote3, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmImgDataNodeVal(String captureChildNote2, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetCrdtItmImgNodeVal(String captureChildNote3, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

public static void validateISOTxSetDebitItmTags(String excelFilePath,String icnFilepath,String icnFileName) throws Exception {


	//icnCoreBusinessDate;
	FileInputStream inputStream = new FileInputStream(excelFilePath);
	
	Workbook wrkBk = new XSSFWorkbook(inputStream);
	int index = wrkBk.getSheetIndex("DbtItm");
	System.out.println(index);
	
	Sheet fsheet = wrkBk.getSheetAt(index);
	Iterator<Row> iterator = fsheet.iterator();
	List<String> listTag = new ArrayList<String>();
	
	List<Tags1> listOfTag = new ArrayList<>();
	
	while(iterator.hasNext()){
		Tags1 tag = new Tags1();
		
		Row nextRow = iterator.next();
		Iterator<Cell> cellIterator = nextRow.cellIterator();
		int rowIndex = nextRow.getRowNum();
		int count=1;
	
		if(rowIndex>0){
			while(cellIterator.hasNext()){
				Cell cell = cellIterator.next();
				System.out.println("cell :"+cell);
				switch(count){
				case 1:
					tag.setRootNode(cell.getRichStringCellValue().toString());
					System.out.println("RootTag CaptureInfo val :"+cell.getRichStringCellValue().toString());
					count++;
					break;

				case 2:
					tag.setCaptureInfoChildNode(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode CaptureItem Tag val :"+cell.getRichStringCellValue().toString());
					count++;
					break;
					
				case 3:
					tag.setCaptureInfoChildNode1(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode1 val :"+cell.getRichStringCellValue().toString());
					count++;
					break;

				case 4:
					//listTag.add(cell.getRichstatic StringCellValue.tostatic String());
					tag.setCaptureInfoChildNode2(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode2 ChildNode Tag val 1:"+cell.getRichStringCellValue().toString());
					count++;
					break;
				case 5:
					//listTag.add(cell.getRichstatic StringCellValue.tostatic String());
					tag.setCaptureInfoChildNode3(cell.getRichStringCellValue().toString());
					System.out.println("CaptureInfoChildNode3 ChildNode Tag val 1:"+cell.getRichStringCellValue().toString());
					count++;
					break;
				case 6:
					tag.setOccurs(cell.getRichStringCellValue().toString());
					count++;
					break;
				case 7:
					tag.setFixedVar(cell.getRichStringCellValue().toString());
					count++;
					break;
				case 8:
					tag.setType(cell.getRichStringCellValue().toString());
				//	int type = (int)cell.getNumericCellValue();
				//	tag.setType(Integer.toString(type));
					count++;
					break;
				case 9:
					int len = (int)cell.getNumericCellValue();
					tag.setLength(Integer.toString(len));
				
					/*String len = cell.getRichStringCellValue().toString();
					tag.setLength(len);
					*/
					count++;
					break;
				}
				
			}
			listOfTag.add(tag);
			System.out.println("listOfTag :"+listOfTag+"  listOfTag.add(tag)"+listOfTag.add(tag));
		}
	}
	wrkBk.close();
	inputStream.close();

	String jobNodeVal;
	String coreChildNode,rootNode,captureChildNote1,captureChildNote2,captureChildNote3,captureChildNote4;
	
	String occurs,captureChildNote;
	String type,fixedVar,length;
	int i=0;
	for(Tags1 tags:listOfTag){
		System.out.println("listOfTag :"+listOfTag);
		//coreChildNode = tags.getCoreChildNode();
		rootNode =tags.getRootNode();
		
		captureChildNote = tags.getCaptureInfoChildNode(); //CaptureInfoChildNode  CaptureItem
		captureChildNote1 =tags.getCaptureInfoChildNode1(); //CaptureInfoChildNode1 JOB, BTACH,BLOCK,APGIN....
		captureChildNote2=tags.getCaptureInfoChildNode2();
		captureChildNote3=tags.getCaptureInfoChildNode3();
	//	captureChildNote4=tags.getCaptureInfoChildNode4();
		
		occurs=tags.getOccurs();
		type=tags.getType();
		length=tags.getLength();
		fixedVar=tags.getFixedVar();
		if(null!=rootNode){
			getRootNodeValidation(rootNode,icnFilepath,icnFileName); //TxSetSubmissn
			if(null!=captureChildNote){
				System.out.println("captureChildNote "+captureChildNote);
					getCaptureInfoChildNodeValidation(captureChildNote,icnFilepath,icnFileName); //TxSet
					if(null!=captureChildNote1){
						System.out.println("captureChildNote "+captureChildNote);
						getCaptureInfoChildNodeValidation(captureChildNote1,icnFilepath,icnFileName); //DbtItm
						if(null!=captureChildNote2){
							System.out.println("captureChildNote "+captureChildNote);
							getCaptureInfoChildNodeValidation(captureChildNote1,icnFilepath,icnFileName); //DbtItm
							
			
						switch(captureChildNote2){
							case "ItmImgData":
								dbtItmImgDataNodeVal=getTxSetDbtItmImgDataNodeVal(captureChildNote2,icnFilepath,icnFileName);
							//getJobSubTagsOccuranceValidation(captureChildNote1,occurs,businessDateNodeVal);
							System.out.println("*****As per IDS version 8, ChildCoreNode BusinessDate DataType in ICN is :"+type);
							System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);
							System.out.println("Lenght validation is not required for BusinessDate in IDS v8 "+length);
						//	getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal);
							switch(captureChildNote3){
								case "Img":
									dbtItmImgNodeVal=getTxSetDbtItmImgNodeVal(captureChildNote3,icnFilepath,icnFileName);
									validateDbtItmImgTagOccurence(captureChildNote3,occurs,dbtItmImgNodeVal);
									validateDbtItmImgTagLength(captureChildNote3,length,dbtItmImgNodeVal);
									break;
								case "ImgHash":
									dbtItmImgHashNodeVal=getTxSetDbtItmImgHashNodeVal(captureChildNote3,icnFilepath,icnFileName);
									validateDbtItmImgHashTagOccurence(captureChildNote3,occurs,dbtItmImgHashNodeVal);
									validateDbtItmImgHashTagLength(captureChildNote3,length,dbtItmImgHashNodeVal);
									break;
							
							}
							break;
							
								case "ItmImgMetaData":
									dbtItmImgMetaDataNodeVal=getTxSetDbtItmMetaDataNodeVal(captureChildNote2,icnFilepath,icnFileName);
									getTxSetDbtItmFrdDataNodeVal(captureChildNote2,occurs,dbtItmImgMetaDataNodeVal);
									//getJobInstallationIdLenghtValidation(captureChildNote1,length,installationIdNodeVal);
									switch(captureChildNote3){
									case "ImgCaptrId":
										dbtItmImgCaptrIdNodeVal=getTxSetDbtItmCaptrIdNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateDbtItmImgCaptrIdTagOccurence(captureChildNote3,occurs,dbtItmImgCaptrIdNodeVal);
										validateDbtItmImgCaptrIdTagLength(captureChildNote3,length,dbtItmImgCaptrIdNodeVal);
										break;
									case "ImgCaptrDvcId":
										dbtItmImgCaptrDvcIdNodeVal=getTxSetDbtItmCaptrCaptrDvcIdNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateDbtItmImgCaptrDvcIdTagOccurence(captureChildNote3,occurs,dbtItmImgCaptrDvcIdNodeVal);
										validateDbtItmImgCaptrDvcIdTagLength(captureChildNote3,length,dbtItmImgCaptrDvcIdNodeVal);
										break;
									case "ImgCaptrLctn":
										dbtItmImgCaptrLctnNodeVal=getTxSetDbtItmCaptrCaptrLctnNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateDbtItmImgCaptrLctnTagOccurence(captureChildNote3,occurs,dbtItmImgCaptrLctnNodeVal);
										validateDbtItmImgCaptrLctnTagLength(captureChildNote3,length,dbtItmImgCaptrLctnNodeVal);
										break;
									case "ImgCaptrDtTm":
										dbtItmImgCaptrDtTmNodeVal=getTxSetDbtItmCaptrCaptrDtTmNodeVal(captureChildNote2,icnFilepath,icnFileName);
										validateDbtItmImgCaptrDtTmTagOccurence(captureChildNote3,occurs,dbtItmImgCaptrDtTmNodeVal);
										validateDbtItmImgCaptrDtTmTagLength(captureChildNote3,length,dbtItmImgCaptrDtTmNodeVal);
										break;
								
								}
								
									break;
									
								case "DbtItmFrdData":
									dbtItmFrdDataNodeVal=getTxSetDbtItmFrdDataNodeVal(captureChildNote2,icnFilepath,icnFileName);
									validateTxSetDbtItmFrdDataTgOccurence(captureChildNote2,occurs,dbtItmFrdDataNodeVal);
									//getJobInstallationIdLenghtValidation(captureChildNote1,length,installationIdNodeVal);
									switch(captureChildNote3){
									case "DtOfFrstChq":
										dbtItmFrdDataDtOfFrstChqNodeVal=getTxSetDbtItmDtOfFrstChqNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateDbtItmFrdDataDtOfFrstChqTagOccurence(captureChildNote3,occurs,dbtItmFrdDataDtOfFrstChqNodeVal);
										validateDbtItmFrdDataDtOfFrstChqTagLength(captureChildNote3,length,dbtItmFrdDataDtOfFrstChqNodeVal);
										break;
									case "DtOfLstChq":
										dbtItmFrdDataDtOfLstChqNodeVal=getTxSetDbtItmDtOfLstChqNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateDbtItmFrdDataDtOfLstChqTagOccurence(captureChildNote3,occurs,dbtItmFrdDataDtOfLstChqNodeVal);
										validateDbtItmFrdDataDtOfLstChqTagLength(captureChildNote3,length,dbtItmFrdDataDtOfLstChqNodeVal);
										break;
									case "NbOfCtrPtys":
										dbtItmFrdDataNbOfCtrPtysNodeVal=getTxSetDbtItmNbOfCtrPtysNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateDbtItmFrdDataNbOfCtrPtysTagOccurence(captureChildNote3,occurs,dbtItmFrdDataNbOfCtrPtysNodeVal);
										validateDbtItmFrdDataNbOfCtrPtysTagLength(captureChildNote3,length,dbtItmFrdDataNbOfCtrPtysNodeVal);
										break;
									case "NbOfVldChqs":
										dbtItmFrdDataNbOfVldChqsNodeVal=getTxSetDbtItmNbOfVldChqsNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateDbtItmFrdDataNbOfVldChqsTagOccurence(captureChildNote3,occurs,dbtItmFrdDataNbOfVldChqsNodeVal);
										validateDbtItmFrdDataNbOfVldChqsTagLength(captureChildNote3,length,dbtItmFrdDataNbOfVldChqsNodeVal);
									break;
									case "NbOfFrdChqs":
										dbtItmFrdDataNbOfFrdChqsNodeVal=getTxSetDbtItmNbOfFrdChqsNodeVal(captureChildNote2,icnFilepath,icnFileName);
										validateDbtItmFrdDataNbOfFrdChqsTagOccurence(captureChildNote3,occurs,dbtItmFrdDataNbOfFrdChqsNodeVal);
										validateDbtItmFrdDataNbOfFrdChqsTagLength(captureChildNote3,length,dbtItmFrdDataNbOfFrdChqsNodeVal);
										break;
									case "HghstAmt":
										dbtItmFrdDataHghstAmtNodeVal=getTxSetDbtItmHghstAmtNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateDbtItmFrdDataHghstAmtTagOccurence(captureChildNote3,occurs,dbtItmFrdDataHghstAmtNodeVal);
										validateDbtItmFrdDataHghstAmtTagLength(captureChildNote3,length,dbtItmFrdDataHghstAmtNodeVal);
										break;
									case "RskInd":
										dbtItmFrdDataRskIndNodeVal=getTxSetDbtItmRskIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateDbtItmFrdDataRskIndTagOccurence(captureChildNote3,occurs,dbtItmFrdDataRskIndNodeVal);
										validateDbtItmFrdDataRskIndTagLength(captureChildNote3,length,dbtItmFrdDataRskIndNodeVal);
										break;
									}
									break;
								case "RprdItm":
									dbtItmRprdItmNodeVal=getTxSetDbtItmRprdItmNodeVal(captureChildNote2,icnFilepath,icnFileName);
								//	validateJobWorkTypeNbrTagOccurance(captureChildNote3,occurs,workTypeNbrNodeVal);
									
									switch(captureChildNote3){
									case "BkCdRprdInd":
										dbtItmBkCdRprdIndNodeVal=getTxSetDbtItmBkCdRprdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateBkCdRprdIndTagOccurence(captureChildNote3,occurs,dbtItmBkCdRprdIndNodeVal);
										validateBkCdRprdIndTagLength(captureChildNote3,length,dbtItmBkCdRprdIndNodeVal);
										break;
										
									case "AcctNbRprdInd":
										dbtItmAcctNbRprdIndNodeVal=getTxSetDbtItmAcctNbRprdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateAcctNbRprdIndTagOccurence(captureChildNote3,occurs,dbtItmAcctNbRprdIndNodeVal);
										validateAcctNbRprdIndTagLength(captureChildNote3,length,dbtItmAcctNbRprdIndNodeVal);
										break;
										
									case "AmtRprdInd":
										dbtItmAmtRprdIndNodeVal=getTxSetDbtItmAmtRprdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateAmtRprdIndTagOccurance(captureChildNote3,occurs,dbtItmAmtRprdIndNodeVal);
										validateAmtRprdIndTagLength(captureChildNote3,length,dbtItmAmtRprdIndNodeVal);
										break;
										
									case "SrlNbRprdInd":
										dbtItmSrlNbRprdIndNodeVal=getTxSetDbtItmSrlNbRprdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateDebitSrlNbRprdIndTagOccurance(captureChildNote3,occurs,dbtItmSrlNbRprdIndNodeVal);
										validateDebitSrlNbRprdIndTagLength(captureChildNote3,length,dbtItmSrlNbRprdIndNodeVal);
										break;
									}
									
									break;
								case "DfltdItm":
									dfltdItmNodeVal=getTxSetDbtItmDfltdItmNodeVal(captureChildNote2,icnFilepath,icnFileName);
									
									switch(captureChildNote3){
									
									case "BkCdDfltdInd":
										dbtItmBkCdDfltdIndNodeVal=getTxSetDbtItmBkCdDfltdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateTxSetDbtItmBkCdDfltdIndTagOccurance(captureChildNote3,occurs,dbtItmBkCdDfltdIndNodeVal);
										validateTxSetDbtItmBkCdDfltdIndTagLength(captureChildNote3,length,dbtItmBkCdDfltdIndNodeVal);	
										break;
										
									case "AcctNbDfltdInd":
										dbtItmAcctNbDfltdIndNodeVal=getTxSetDbtItmAcctNbDfltdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateTxSetDbtItmAcctNbDfltdIndTagOccurance(captureChildNote3,occurs,dbtItmAcctNbDfltdIndNodeVal);
										validateTxSetDbtItmAcctNbDfltdIndTagLength(captureChildNote3,length,dbtItmAcctNbDfltdIndNodeVal);	
										break;
										
									case "SrlNbDfltdInd":
										dbtItmSrlNbDfltdIndNodeVal=getTxSetDbtItmSrlNbDfltdIndNodeVal(captureChildNote3,icnFilepath,icnFileName);
										validateTxSetDbtItmSrlNbDfltdIndTagOccurance(captureChildNote3,occurs,dbtItmSrlNbDfltdIndNodeVal);
										validateTxSetDbtItmSrlNbDfltdIndTagLength(captureChildNote3,length,dbtItmSrlNbDfltdIndNodeVal);
										break;
								
									}
									
									break;
							
							
							
					
		}
	}		
	}
	}	
	}


}
			
}

private static void validateTxSetDbtItmFrdDataTgOccurence(String captureChildNote2, String occurs, String dbtItmFrdDataNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote2+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote2+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataNodeVal2!=null,(dbtItmFrdDataNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataNodeVal2+" tag is present under "+captureChildNote2+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataNodeVal2+" tag is present under "+captureChildNote2+" tag in ICN","TxSetSubmission "+captureChildNote2+" tag occurence validation performed");

}

private static String getTxSetDbtItmFrdDataNodeVal(String captureChildNote2, String occurs,String dbtItmImgMetaDataNodeVal2) {
	return dbtItmImgMetaDataNodeVal2;
	
}

private static void validateDbtItmImgCaptrLctnTagLength(String captureChildNote3, String length,String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmImgCaptrLctnTagOccurence(String captureChildNote3, String occurs,String dbtItmImgCaptrLctnNodeVal2) {
	
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmImgCaptrLctnNodeVal2!=null,(dbtItmImgCaptrLctnNodeVal2!=null)?"TxSetSubmission "+dbtItmImgCaptrLctnNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmImgCaptrLctnNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmImgCaptrDtTmTagLength(String captureChildNote3, String length,String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmImgCaptrDtTmTagOccurence(String captureChildNote3, String occurs,String dbtItmImgCaptrDtTmNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmImgCaptrDtTmNodeVal2!=null,(dbtItmImgCaptrDtTmNodeVal2!=null)?"TxSetSubmission "+dbtItmImgCaptrDtTmNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmImgCaptrDtTmNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmImgCaptrDvcIdTagLength(String captureChildNote3, String length,String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmImgCaptrDvcIdTagOccurence(String captureChildNote3, String occurs,String dbtItmFrdDataDtOfFrstChqNodeVal2) {

	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataDtOfFrstChqNodeVal2!=null,(dbtItmFrdDataDtOfFrstChqNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataDtOfFrstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataDtOfFrstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmImgCaptrIdTagLength(String captureChildNote3, String length, String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmImgCaptrIdTagOccurence(String captureChildNote3, String occurs, String dbtItmFrdDataDtOfFrstChqNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataDtOfFrstChqNodeVal2!=null,(dbtItmFrdDataDtOfFrstChqNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataDtOfFrstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataDtOfFrstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmImgHashTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmImgHashTagOccurence(String captureChildNote3, String occurs,
		String dbtItmFrdDataDtOfFrstChqNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataDtOfFrstChqNodeVal2!=null,(dbtItmFrdDataDtOfFrstChqNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataDtOfFrstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataDtOfFrstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmImgTagLength(String captureChildNote3, String length, String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())!=0,((dbtItmNodeVal.length())!=0)?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmImgTagOccurence(String captureChildNote3, String occurs,
		String dbtItmFrdDataDtOfFrstChqNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataDtOfFrstChqNodeVal2!=null,(dbtItmFrdDataDtOfFrstChqNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataDtOfFrstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataDtOfFrstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmFrdDataRskIndTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmFrdDataRskIndTagOccurence(String captureChildNote3, String occurs,
		String dbtItmFrdDataRskIndNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataRskIndNodeVal2!=null,(dbtItmFrdDataRskIndNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataRskIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataRskIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmFrdDataHghstAmtTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmFrdDataHghstAmtTagOccurence(String captureChildNote3, String occurs,
		String dbtItmFrdDataHghstAmtNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataHghstAmtNodeVal2!=null,(dbtItmFrdDataHghstAmtNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataHghstAmtNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataHghstAmtNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmFrdDataNbOfFrdChqsTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmFrdDataNbOfFrdChqsTagOccurence(String captureChildNote3, String occurs,
		String dbtItmFrdDataNbOfFrdChqsNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataNbOfFrdChqsNodeVal2!=null,(dbtItmFrdDataNbOfFrdChqsNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataNbOfFrdChqsNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataNbOfFrdChqsNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmFrdDataNbOfVldChqsTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmFrdDataNbOfVldChqsTagOccurence(String captureChildNote3, String occurs,
		String dbtItmFrdDataNbOfVldChqsNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataNbOfVldChqsNodeVal2!=null,(dbtItmFrdDataNbOfVldChqsNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataNbOfVldChqsNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataNbOfVldChqsNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmFrdDataNbOfCtrPtysTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmFrdDataNbOfCtrPtysTagOccurence(String captureChildNote3, String occurs,
		String dbtItmFrdDataNbOfCtrPtysNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataNbOfCtrPtysNodeVal2!=null,(dbtItmFrdDataNbOfCtrPtysNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataNbOfCtrPtysNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataNbOfCtrPtysNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmFrdDataDtOfLstChqTagLength(String captureChildNote3, String length,
		String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmFrdDataDtOfLstChqTagOccurence(String captureChildNote3, String occurs,
		String dbtItmFrdDataDtOfLstChqNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataDtOfLstChqNodeVal2!=null,(dbtItmFrdDataDtOfLstChqNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataDtOfLstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataDtOfLstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateDbtItmFrdDataDtOfFrstChqTagLength(String captureChildNote3, String length, String dbtItmNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((dbtItmNodeVal.length())==Integer.parseInt(length),((dbtItmNodeVal.length())==Integer.parseInt(length))?"TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+dbtItmNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateDbtItmFrdDataDtOfFrstChqTagOccurence(String captureChildNote3, String occurs,	String dbtItmFrdDataDtOfFrstChqNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(dbtItmFrdDataDtOfFrstChqNodeVal2!=null,(dbtItmFrdDataDtOfFrstChqNodeVal2!=null)?"TxSetSubmission "+dbtItmFrdDataDtOfFrstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+dbtItmFrdDataDtOfFrstChqNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateTxSetDbtItmSrlNbDfltdIndTagLength(String captureChildNote3, String length,	String srlNbDfltdIndNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((srlNbDfltdIndNodeVal2.length())<=Integer.parseInt(length),((srlNbDfltdIndNodeVal2.length())<=Integer.parseInt(length))?"TxSetSubmission "+srlNbDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+srlNbDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

	
}

private static void validateTxSetDbtItmSrlNbDfltdIndTagOccurance(String captureChildNote3, String occurs, String srlNbDfltdIndNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(srlNbDfltdIndNodeVal2!=null,(srlNbDfltdIndNodeVal2!=null)?"TxSetSubmission "+srlNbDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+srlNbDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

	
}

private static void validateTxSetDbtItmAcctNbDfltdIndTagLength(String captureChildNote3, String length,	String acctNbDfltdIndNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((acctNbDfltdIndNodeVal2.length())<=Integer.parseInt(length),((acctNbDfltdIndNodeVal2.length())<=Integer.parseInt(length))?"TxSetSubmission "+acctNbDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+acctNbDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");
	
}

private static void validateTxSetDbtItmAcctNbDfltdIndTagOccurance(String captureChildNote3, String occurs, String acctNbDfltdIndNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(acctNbDfltdIndNodeVal2!=null,(acctNbDfltdIndNodeVal2!=null)?"TxSetSubmission "+acctNbDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+acctNbDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");
	
}

private static void validateTxSetDbtItmBkCdDfltdIndTagLength(String captureChildNote3, String length,	String bkCdDfltdIndNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((bkCdDfltdIndNodeVal2.length())<=Integer.parseInt(length),((bkCdDfltdIndNodeVal2.length())<=Integer.parseInt(length))?"TxSetSubmission "+bkCdDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+bkCdDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateTxSetDbtItmBkCdDfltdIndTagOccurance(String captureChildNote3, String occurs,
		String bkCdDfltdIndNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(bkCdDfltdIndNodeVal2!=null,(bkCdDfltdIndNodeVal2!=null)?"TxSetSubmission "+bkCdDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+bkCdDfltdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");
	
}

private static void validateDebitSrlNbRprdIndTagLength(String captureChildNote3, String length,	String debitItemNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((debitItemNodeVal.length())<=Integer.parseInt(length),((debitItemNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+debitItemNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+debitItemNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

	
}

private static void validateDebitSrlNbRprdIndTagOccurance(String captureChildNote3, String occurs,String debitItemNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(debitItemNodeVal!=null,(debitItemNodeVal!=null)?"TxSetSubmission "+debitItemNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+debitItemNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateAmtRprdIndTagOccurance(String captureChildNote3, String occurs, String debitItemNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(debitItemNodeVal!=null,(debitItemNodeVal!=null)?"TxSetSubmission "+debitItemNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+debitItemNodeVal+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateAmtRprdIndTagLength(String captureChildNote3, String length, String debitItemNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((debitItemNodeVal.length())<=Integer.parseInt(length),((debitItemNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+debitItemNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+debitItemNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateAcctNbRprdIndTagLength(String captureChildNote3, String length, String debitItemNodeVal) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((debitItemNodeVal.length())<=Integer.parseInt(length),((debitItemNodeVal.length())<=Integer.parseInt(length))?"TxSetSubmission "+debitItemNodeVal+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+debitItemNodeVal+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateAcctNbRprdIndTagOccurence(String captureChildNote3, String occurs, String acctNbRprdIndNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(acctNbRprdIndNodeVal2!=null,(acctNbRprdIndNodeVal2!=null)?"TxSetSubmission "+acctNbRprdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+acctNbRprdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}

private static void validateBkCdRprdIndTagLength(String captureChildNote3, String length, String bkCdRprdIndNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Length in MSG01 file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission DfltdItm BkCdDfltdInd Length in MSG01 is :"+length);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission DfltdItm Validation for "+captureChildNote3+" in ICN is :"+length);
	publishResults((bkCdRprdIndNodeVal2.length())<=Integer.parseInt(length),((bkCdRprdIndNodeVal2.length())<=Integer.parseInt(length))?"TxSetSubmission "+bkCdRprdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":captureChildNote3+" tag value is unexpected","TxSetSubmission "+bkCdRprdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in MSG01","TxSetSubmission "+captureChildNote3+" tag length validation performed");

}

private static void validateBkCdRprdIndTagOccurence(String captureChildNote3, String occurs, String bkCdRprdIndNodeVal2) {
	// TODO Auto-generated method stub
	System.out.println("*****TxSetSubmission GrpHdr Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+captureChildNote3+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, TxSetSubmission GrpHdr MsgId Occurance for BusinessDate in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, TxSetSubmission Occurence Validation for "+captureChildNote3+" in ICN is :"+occurs);
	publishResults(bkCdRprdIndNodeVal2!=null,(bkCdRprdIndNodeVal2!=null)?"TxSetSubmission "+bkCdRprdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN":"MsgId tag is null","TxSetSubmission "+bkCdRprdIndNodeVal2+" tag is present under "+captureChildNote3+" tag in ICN","TxSetSubmission "+captureChildNote3+" tag occurence validation performed");

}


private static String getTxSetDbtItmImgNodeVal(String captureChildNote3, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmImgHashNodeVal(String captureChildNote3, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmMetaDataNodeVal(String captureChildNote2, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmCaptrIdNodeVal(String captureChildNote2, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmCaptrCaptrDvcIdNodeVal(String captureChildNote2, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmCaptrCaptrLctnNodeVal(String captureChildNote2, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmCaptrCaptrDtTmNodeVal(String captureChildNote2, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}



private static String getTxSetDbtItmDtOfFrstChqNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmDtOfLstChqNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmNbOfCtrPtysNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmNbOfVldChqsNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmNbOfFrdChqsNodeVal(String captureChildNote2, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmHghstAmtNodeVal(String captureChildNote3, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmRskIndNodeVal(String captureChildNote3, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmRprdItmNodeVal(String captureChildNote2, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmBkCdRprdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmAcctNbRprdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmAmtRprdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmSrlNbRprdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmDfltdItmNodeVal(String captureChildNote2, String icnFilepath, String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmBkCdDfltdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmAcctNbDfltdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmSrlNbDfltdIndNodeVal(String captureChildNote3, String icnFilepath,
		String icnFileName) {
	// TODO Auto-generated method stub
	return null;
}

private static String getTxSetDbtItmImgDataNodeVal(String coreChildNode, String icnFilepath,String icnFileName) throws Exception {
	// TODO Auto-generated method stub
	String bdateNodeVal=null;
	System.out.println("****************************************************************");
	System.out.println("Validating NbOfItms Tag is populating under TxSetSubmission in ICN as per IDS version 8 :"+coreChildNode);
	System.out.println("****************************************************************");
	
	validationStepInformation("*********************************************************************************************************************");
	validationStepInformation("Validating "+ coreChildNode +" TxSetSubmission NbOfItms is populating in ICN as per IDS version 8 ");
	validationStepInformation("*********************************************************************************************************************");
	
	//now read xml and compare here
	//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
	//BusinessDate Validation 
	Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,isoTxSetNbOfItmsXpath);
	 String dateKey =getMapKey(dateKeyVal);
	System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
	Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,isoTxSetNbOfItmsXpath);
	bdateNodeVal =getNodeListValues(dateKeyVal1,"ItmImgData").get(0);
	System.out.println("Batch :"+bdateNodeVal);
	boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "NbOfItms tagname is as per IDSv8");
	if(flagb==true)
		System.out.println("NbOfItms Tag is present under TxSetSubmission Tag in ICN file as per IDS v8 :");
	else
		System.out.println("NbOfItms Tag is not present under TxSetSubmission  Tag in ICN file as per IDS v8 :");
	
	publishResults(flagb,dateKey, coreChildNode, "ItmImgData tagname is as per IDSv8");
	return bdateNodeVal;
}



}