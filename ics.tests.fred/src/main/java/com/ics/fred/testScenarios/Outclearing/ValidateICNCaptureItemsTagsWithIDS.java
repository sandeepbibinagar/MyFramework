
	package com.ics.fred.testScenarios.Outclearing;


	import java.io.File;
	import java.io.FileInputStream;
	import java.util.ArrayList;
	import java.util.Iterator;
	import java.util.LinkedHashMap;
	import java.util.List;
	import java.util.Map;
	import java.util.Set;

	import javax.xml.xpath.XPath;
	import javax.xml.xpath.XPathConstants;
	import javax.xml.xpath.XPathExpression;
	import javax.xml.xpath.XPathFactory;

	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.testng.annotations.Test;
	import org.w3c.dom.Document;
	import org.w3c.dom.NodeList;
	import com.ics.externalFactoryUtilis.ICSDBUtilis;
	import com.ics.fred.common.FREDXMLValidation;
import com.ics.fred.testScenarios.Tags1;
import com.ics.seleniumCoreUtilis.Component;
	import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class ValidateICNCaptureItemsTagsWithIDS  extends GenericMethodUtilis{

		List<String> listOfMandatory = new ArrayList<String>();
		static String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
		static String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\";
		static String ICNFileName="ICNOutput.xml";
		
		//XPATH Declarations
		//CaptureInfoChildNode1
		
		static String icnCaptureInfoXpath ="//CaptureInfo";
		static String icnCaptureItemXpath ="//CaptureInfo/CaptureItem";
		static String icnCaptureItemJobXpath ="//CaptureInfo/CaptureItem/JOB";
		static String icnCaptureItemBLOCKXpath ="//CaptureInfo/CaptureItem/BLOCK";
		static String icnCaptureItemBATCHXpath ="//CaptureInfo/CaptureItem/BATCH";
	//	static String icnCaptureItemJobXpath ="//CaptureInfo/CaptureItem/JOB";
		static String icnCaptureItemAPGDIN ="//CaptureInfo/CaptureItem/APGDIN";
		static String icnCaptureItemAPGBusinessDate ="//CaptureInfo/CaptureItem/APGBusinessDate";
		static String icnCaptureItemGender ="//CaptureInfo/CaptureItem/Gender";
		static String icnCaptureItemTransactionNumber ="//CaptureInfo/CaptureItem/TransactionNumber";
		static String icnCaptureItemIsElectronic ="//CaptureInfo/CaptureItem/IsElectronic";
		static String icnCaptureItemIsOnUs ="//CaptureInfo/CaptureItem/IsOnUs";
		static String icnCaptureItemIsDeleted ="//CaptureInfo/CaptureItem/IsDeleted";
		static String icnCaptureItemIsCorrected ="//CaptureInfo/CaptureItem/IsCorrected";
		static String icnCaptureItemIsAmountCorrected ="//CaptureInfo/CaptureItem/IsAmountCorrected";
		static String icnCaptureItemOriginalAmount ="//CaptureInfo/CaptureItem/OriginalAmount";
		static String icnCaptureItemIsTCCorrected ="//CaptureInfo/CaptureItem/IsTCCorrected";
		static String icnCaptureItemIsANCorrected ="//CaptureInfo/CaptureItem/IsANCorrected";
		static String icnCaptureItemIsSortCodeCorrected ="//CaptureInfo/CaptureItem/IsSortCodeCorrected";
		static String icnCaptureItemIsSerialCorrected ="//CaptureInfo/CaptureItem/IsSerialCorrected";
		static String icnCaptureItemIsReject ="//CaptureInfo/CaptureItem/IsReject";
		static String icnCaptureItemRejectReason ="//CaptureInfo/CaptureItem/RejectReason";
		static String icnCaptureItemSpSelector ="//CaptureInfo/CaptureItem/SpSelector";
		static String icnCaptureItemCurrency ="//CaptureInfo/CaptureItem/Currency";
		static String icnCaptureItemAdjustmentReason ="//CaptureInfo/CaptureItem/AdjustmentReason";
		static String icnCaptureItemNarrative ="//CaptureInfo/CaptureItem/Narrative";
		static String icnCaptureItemOperatorId ="//CaptureInfo/CaptureItem/OperatorId";
		
		static String icnCaptureItemProcessId ="//CaptureInfo/CaptureItem/ProcessId";
		static String icnCaptureItemItemId ="//CaptureInfo/CaptureItem/ItemId";
		static String icnCaptureItemOriginalIsn ="//CaptureInfo/CaptureItem/OriginalIsn";
		static String icnCaptureItemAeStatus ="//CaptureInfo/CaptureItem/AeStatus";
		static String icnCaptureItemIcStatus ="//CaptureInfo/CaptureItem/IcStatus";
		static String icnCaptureItemIqvStatus ="//CaptureInfo/CaptureItem/IqvStatus";
		static String icnCaptureItemCarSetId ="//CaptureInfo/CaptureItem/CarSetId";
		static String icnCaptureItemCarResult ="//CaptureInfo/CaptureItem/CarResult";
		

		static String icnCaptureItemIaStatus="//CaptureInfo/CaptureItem/IaStatus";
		static String icnCaptureItemIaResult="//CaptureInfo/CaptureItem/IaResult";
		static String icnCaptureItemPNVRevwSts="//CaptureInfo/CaptureItem/PNVReviewStatus";
		static String icnCaptureItemDuplicateSts="//CaptureInfo/CaptureItem/DuplicateStatus";
		static String icnCaptureItemRetrnRsn="//CaptureInfo/CaptureItem/ReturnReason";
		static String icnCaptureItemCheqIssuedDate="//CaptureInfo/CaptureItem/ChequeIssuedDate";
		static String icnCaptureItemImgHsh="//CaptureInfo/CaptureItem/ImageHash";
		static String icnCaptureItemSrcData="//CaptureInfo/CaptureItem/ItemSourceData";
		

		static String icnCaptureItemCustomFields ="//CaptureInfo/CaptureItem/CustomFields";
		static String icnCaptureItemItmHistory ="//CaptureInfo/CaptureItem/ItemHistory";
		


		static String icnCaptureItemItemSourceDataBranchSelector=	"//CaptureInfo/CaptureItem/ItemSourceData/BranchSelector";
		static String icnCaptureItemItemSourceDataBranchItemDesc=	"//CaptureInfo/CaptureItem/ItemSourceData/BranchItemDesc";
		static String icnCaptureItemItemSourceDataTellerId=	"//CaptureInfo/CaptureItem/ItemSourceData/TellerId";
		static String icnCaptureItemItemSourceDataProdType=	"//CaptureInfo/CaptureItem/ItemSourceData/ProdType";
		static String icnCaptureItemItemSourceDataTransactionType=	"//CaptureInfo/CaptureItem/ItemSourceData/TransactionType";
		static String icnCaptureItemItemSourceDataReceiptNumber=	"//CaptureInfo/CaptureItem/ItemSourceData/ReceiptNumber";
		static String icnCaptureItemItemSourceDataCarSetId=	"//CaptureInfo/CaptureItem/ItemSourceData/CarSetId";
		static String icnCaptureItemItemSourceDataSentToCar=	"//CaptureInfo/CaptureItem/ItemSourceData/SentToCar";
		static String icnCaptureItemItemSourceDataPassedCar=	"//CaptureInfo/CaptureItem/ItemSourceData/PassedCar";
		static String icnCaptureItemItemSourceDataCarConfidence=	"//CaptureInfo/CaptureItem/ItemSourceData/CarConfidence";
		static String icnCaptureItemItemSourceDataSentToIcr=	"//CaptureInfo/CaptureItem/ItemSourceData/SentToIcr";
		static String icnCaptureItemItemSourceDataAutoCorrected=	"//CaptureInfo/CaptureItem/ItemSourceData/AutoCorrected";
		static String icnCaptureItemItemSourceDataOperatorCorrected=	"//CaptureInfo/CaptureItem/ItemSourceData/OperatorCorrected";
		static String icnCaptureItemItemSourceDataIaStatus=	"//CaptureInfo/CaptureItem/ItemSourceData/IaStatus";
		static String icnCaptureItemItemSourceDataIaResult=	"//CaptureInfo/CaptureItem/ItemSourceData/IaResult";
				
				
		static String icnCaptureItemCustomFieldscf_AccountType=	"//CaptureInfo/CaptureItem/CustomFields/cf_AccountType";
		static String icnCaptureItemCustomFieldscf_ANDefaulted=	"//CaptureInfo/CaptureItem/CustomFields/cf_ANDefaulted";
		static String icnCaptureItemCustomFieldscf_Batch_SourceID=	"//CaptureInfo/CaptureItem/CustomFields/cf_Batch_SourceID";
		static String icnCaptureItemCustomFieldscf_BrandID=	"//CaptureInfo/CaptureItem/CustomFields/cf_BrandID";
		static String icnCaptureItemCustomFieldscf_CustomerName1=	"//CaptureInfo/CaptureItem/CustomFields/cf_CustomerName1";
		static String icnCaptureItemCustomFieldscf_CustomerName2=	"//CaptureInfo/CaptureItem/CustomFields/cf_CustomerName2";
		static String icnCaptureItemCustomFieldscf_CustomerName3=	"//CaptureInfo/CaptureItem/CustomFields/cf_CustomerName3";
		static String icnCaptureItemCustomFieldscf_CustomerName4=	"//CaptureInfo/CaptureItem/CustomFields/cf_CustomerName4";
		static String icnCaptureItemCustomFieldscf_CustomerName5=	"//CaptureInfo/CaptureItem/CustomFields/cf_CustomerName5";
		static String icnCaptureItemCustomFieldscf_Date=	"//CaptureInfo/CaptureItem/CustomFields/cf_Date";
		static String icnCaptureItemCustomFieldscf_DeferredPosting=	"//CaptureInfo/CaptureItem/CustomFields/cf_DeferredPosting";
		static String icnCaptureItemCustomFieldscf_DeviceID=	"//CaptureInfo/CaptureItem/CustomFields/cf_DeviceID";
		static String icnCaptureItemCustomFieldscf_fCashItem=	"//CaptureInfo/CaptureItem/CustomFields/cf_fCashItem";
		static String icnCaptureItemCustomFieldscf_ICSTransactionID=	"//CaptureInfo/CaptureItem/CustomFields/cf_ICSTransactionID";
		static String icnCaptureItemCustomFieldscf_ImageDateTime=	"//CaptureInfo/CaptureItem/CustomFields/cf_ImageDateTime";
		static String icnCaptureItemCustomFieldscf_IQVRejectReason=	"//CaptureInfo/CaptureItem/CustomFields/cf_IQVRejectReason";
		static String icnCaptureItemCustomFieldscf_LAR=	"//CaptureInfo/CaptureItem/CustomFields/cf_LAR";
		static String icnCaptureItemCustomFieldscf_LocationID=	"//CaptureInfo/CaptureItem/CustomFields/cf_LocationID";
		static String icnCaptureItemCustomFieldscf_LockRollID=	"//CaptureInfo/CaptureItem/CustomFields/cf_LockRollID";
		static String icnCaptureItemCustomFieldscf_MarketSector=	"//CaptureInfo/CaptureItem/CustomFields/cf_MarketSector";
		static String icnCaptureItemCustomFieldscf_MSOUGroupID=	"//CaptureInfo/CaptureItem/CustomFields/cf_MSOUGroupID";
		static String icnCaptureItemCustomFieldscf_MSOUGroupName=	"//CaptureInfo/CaptureItem/CustomFields/cf_MSOUGroupName";
		static String icnCaptureItemCustomFieldscf_NoPaySuspectRsn=	"//CaptureInfo/CaptureItem/CustomFields/cf_NoPaySuspectRsn";
		static String icnCaptureItemCustomFieldscf_NPASortCode=	"//CaptureInfo/CaptureItem/CustomFields/cf_NPASortCode";
		static String icnCaptureItemCustomFieldscf_ParticipantID=	"//CaptureInfo/CaptureItem/CustomFields/cf_ParticipantID";
		static String icnCaptureItemCustomFieldscf_PersonID=	"//CaptureInfo/CaptureItem/CustomFields/cf_PersonID";
		static String icnCaptureItemCustomFieldscf_PlaceHolder=	"//CaptureInfo/CaptureItem/CustomFields/cf_PlaceHolder";
		static String icnCaptureItemCustomFieldscf_SCDefaulted=	"//CaptureInfo/CaptureItem/CustomFields/cf_SCDefaulted";
		static String icnCaptureItemCustomFieldscf_SERDefaulted=	"//CaptureInfo/CaptureItem/CustomFields/cf_SERDefaulted";
		static String icnCaptureItemCustomFieldscf_SourceID=	"//CaptureInfo/CaptureItem/CustomFields/cf_SourceID";
		static String icnCaptureItemCustomFieldscf_SuspectReason=	"//CaptureInfo/CaptureItem/CustomFields/cf_SuspectReason";
		static String icnCaptureItemCustomFieldscf_SwitchedOut=	"//CaptureInfo/CaptureItem/CustomFields/cf_SwitchedOut";
		static String icnCaptureItemCustomFieldscf_AgencySC=	"//CaptureInfo/CaptureItem/CustomFields/cf_AgencySC";
		static String icnCaptureItemCustomFieldscf_DoDateCheck=	"//CaptureInfo/CaptureItem/CustomFields/cf_DoDateCheck";
		static String icnCaptureItemCustomFieldscf_DoCARvLAR=	"//CaptureInfo/CaptureItem/CustomFields/cf_DoCARvLAR";
		static String icnCaptureItemCustomFieldscf_DoCARvData=	"//CaptureInfo/CaptureItem/CustomFields/cf_DoCARvData";
		static String icnCaptureItemCustomFieldscf_CPMError=	"//CaptureInfo/CaptureItem/CustomFields/cf_CPMError";
		
		
		//CaptureInfoChildNode3
		static String icnCaptrItmBtchSrcDataAcquisitionStartTime=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/AcquisitionStartTime";
		static String icnCaptrItmBtchSrcDataAcquisitionEndTime=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/AcquisitionEndTime";
		static String icnCaptrItmBtchSrcDataCollctStartTime=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/CollectionStartTime";
		static String icnCaptrItmBtchSrcDataCollctEndTime=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/CollectionEndTime";
		static String icnCaptrItmBtchSrcDataATMData=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/ATMData";
		static String icnCaptrItmBtchSrcDataBrnchDta=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/BranchData";
		static String icnCaptrItmBtchSrcDataRmyeDpst =	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/RemoteDeposit";
		
		//CaptureInfoChildNode4
		static String icnCaptrItmBtchSrcDataATMDataRegnId=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/ATMData/RegionId";
		static String icnCaptrItmBtchSrcDataATMDataATMId=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/ATMData/ATMId";
		static String icnCaptrItmBtchSrcDataATMDataATMNme=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/ATMData/ATMName";
		static String icnCaptrItmBtchSrcDataBrnchDtaRegnId=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/BranchData/RegionId";
		static String icnCaptrItmBtchSrcDataBrnchDtaBrnchId=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/BranchData/BranchId";
		static String icnCaptrItmBtchSrcDataBrnchDtaBrnchName=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/BranchData/BranchName";
		static String icnCaptrItmBtchSrcDataRmyeDpstCorporateId =	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/RemoteDeposit/CorporateId";
		static String icnCaptrItmBtchSrcDataRmyeDpstCorporateUsrId =	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData/BatchSourceData/RemoteDeposit/CorporateUserId";
		
		static String jobBusinessDateNodeVal,captureSystemIdNodeVal,startTimeNodeVal,isOnUsNodeVal,isDeletedNodeVal,isCorrectedNodeVal,isAmountCorrectedNodeVal,originalAmountNodeVal;
		static String isTCCorrectedNodeVal,isANCorrectedNodeVal,isSortCodeCorrectedNodeVal,isSerialCorrectedNodeVal;
		static String isRejectNodeVal,rejectRsnNodeVal,spSelectorNodeVal;
		static String tktAuxNodeVal,operatorIdNodeVal,adjstmntRsnNodeVal,batchDebitAmountNodeVal,batchCreditAmountNodeVal,batchDebitCountNodeVal,batchCreditCountNodeVal;

	//	public static static String businessDateTagName	InstallationIdTagName	CaptureSystemIdTagName	StartTimeTagName	EndTimeTagName	WorkTypeNbrTagName	SortFamilyTagName	SourceTypeTagName	SourceNameTagName	SourceIDTagName	FinancialInstitutionIDTagName	CollectionStartTimeTagName	CollectionEndTimeTagName

	//CatupreItemNodeVal declaration
		static String jobNodeVal,apgdinNodeVal,	apgbusinessdateNodeVal,	genderNodeVal,	transactionnumberNodeVal;
		static String iselectronicNodeVal,	isonusNodeVal,	isdeletedNodeVal,	iscorrectedNodeVal,	isamountcorrectedNodeVal;
		static String originalamountNodeVal,	istccorrectedNodeVal,	isancorrectedNodeVal,	issortcodecorrectedNodeVal;
		static String isserialcorrectedNodeVal,	isrejectNodeVal,	rejectreasonNodeVal,	spselectorNodeVal,	currencyNodeVal;
		static String adjustmentreasonNodeVal,	narrativeNodeVal,	operatoridNodeVal,	processidNodeVal,	itemidNodeVal;
		static String originalISNNodeVal,	aestatusNodeVal,	icstatusNodeVal,	iqvstatusNodeVal,	carsetidNodeVal,carresultNodeVal;
		static String iastatusNodeVal,	iaresultNodeVal,	pnvreviewstatusNodeVal,	duplicatestatusNodeVal,	returnreasonNodeVal,	chequeissueddateNodeVal;
		static String imagehashNodeVal,	itemsourcedataNodeVal,	customfieldsNodeVal,	itemhistoryNodeVal;
		static String isElectronicNodeVal,transactionNumberNodeVal;

		 static String apgDINTagName = "APGDIN";
		 static String apgBusinessDateTagName = "APGBusinessDate";
		 static String genderTagName = "Gender";
		 static String transactionNumberTagName = "TransactionNumber";
		 static String isElectronicTagName = "IsElectronic";
		 static String isOnUsTagName = "IsOnUs";	
		 static String isDeletedTagName="IsDeleted";
		 static String isCorrectedTagName="IsCorrected";
		 static String isAmountCorrectedTagName="IsAmountCorrected";
		 static String originalAmountTagName="OriginalAmount";
		 static String isTcCorrectedTagName="IsTCCorrected";
		 static String isANCorrectedTagName="IsANCorrected";
		 static String isSortCodeCorrectedTagName="IsSortCodeCorrected";
		 static String isSerialcorrectedTagName="IsSerialCorrected";
		 static String isRejectTagName="IsReject";
		 static String rejectReasonTagName="RejectReason";
		 static String spSelectorTagName="SpSelector";
		 static String currencyTagName="Currency";
		 static String adjustmentReasonTagName="AdjustmentReason";
		 static String narrativeTagName="Narrative";
		 static String operatorIdTagName="OperatorId";
		 static String processIdTagName="ProcessId";
		 static String itemIdTagName="ItemId";
		 static String originalISNTagName="OriginalIsn";
		 static String aeStatusTagName="AeStatus";
		 static String icStatusTagName="IcStatus";
		 static String iqvStatusTagName="IqvStatus";
		 static String carSetIdTagName="CarSetId";
		 static String carResultTagName="CarResult";
		 static String iaStatusTagName="IaStatus";
		 static String iaResultTagName="IaResult";
		 static String pnvreviewstatusTagName="PNVReviewStatus";
		 static String duplicateStatusTagName="DuplicateStatus";
		 static String returnReasonTagName="ReturnReason";
		 static String chequeIssuedDateTagName="ChequeIssuedDate";
		 static String imagehashTagName="ImageHash";
		 static String itemsourcedataTagName="ItemSourceData";
		 static String customfieldsTagName="CustomFields";
		 static String itemhistoryTagName="ItemHistory";
	
	
		public static void validateCaptureInfoTags(String excelFilePath,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub

			//icnCoreBusinessDate;
			FileInputStream inputStream = new FileInputStream(excelFilePath);
			
			Workbook wrkBk = new XSSFWorkbook(inputStream);
			int index = wrkBk.getSheetIndex("CaptureInfoItemsClearing");
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
						
						/*	String len = cell.getRichStringCellValue().toString();
							tag.setLength(len);*/
							
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
			String coreChildNode,rootNode,captureChildNote1;
			
			String occurs,captureChildNote;
			String type,fixedVar,length;
			int i=0;
			for(Tags1 tags:listOfTag){
				System.out.println("listOfTag :"+listOfTag);
				//coreChildNode = tags.getCoreChildNode();
				rootNode =tags.getRootNode();
				
				captureChildNote = tags.getCaptureInfoChildNode(); //CaptureInfoChildNode  CaptureItem
				captureChildNote1 =tags.getCaptureInfoChildNode1(); //CaptureInfoChildNode1 APGIN....
				
				occurs=tags.getOccurs();
				type=tags.getType();
				length=tags.getLength();
				fixedVar=tags.getFixedVar();
				
				if(null!=rootNode){
					getRootNodeValidation(rootNode,icnFilepath,icnFileName);
					if(null!=captureChildNote){
						System.out.println("captureChildNote "+captureChildNote);
							getCaptureInfoChildNodeValidation(captureChildNote,icnFilepath,icnFileName);
							if(null!=captureChildNote1){
								switch(captureChildNote1){
									case "APGDIN":
										apgdinNodeVal=getAPGDINValidationNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateAPGDINTagOccurance(captureChildNote1,occurs,apgdinNodeVal);
										validateAPGDINTagLength(captureChildNote1,length,apgdinNodeVal);
										
									System.out.println("*****As per IDS version 8, ChildCoreNode BusinessDate DataType in ICN is :"+type);
									System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);
									System.out.println("Lenght validation is not required for BusinessDate in IDS v8 "+length);
								//	getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal);
									break;
										case "APGBusinessDate":
											jobBusinessDateNodeVal=getAPGBuisnessDateValidationTag(captureChildNote1,icnFilepath,icnFileName);
											validateAPGBusinessDateTagOccurance(captureChildNote1,occurs,jobBusinessDateNodeVal);
											validateAPGBusinessDateTagLength(captureChildNote1,length,jobBusinessDateNodeVal);
											break;
										case "Gender":
											genderNodeVal=getCaptureItmGenderTagNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateCaptureItemGenderTagOccurance(captureChildNote1,occurs,genderNodeVal);
											validateCaptureItemGenderTagLength(captureChildNote1,length,genderNodeVal);
											break;
										case "TransactionNumber":
											transactionNumberNodeVal=getTransactionNumberValidationTag(captureChildNote1,icnFilepath,icnFileName);
											validateTransactionNbrTagOccurance(captureChildNote1,occurs,transactionNumberNodeVal);
											validateTransactionNbrTagLength(captureChildNote1,length,transactionNumberNodeVal);
											break;
										case "IsElectronic":
											isElectronicNodeVal=getIsElectronicNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateIsElectronicTagOccurance(captureChildNote1,occurs,isElectronicNodeVal);
											validateIsElectronicValLength(captureChildNote1,length,isElectronicNodeVal);
											break;
										case "IsOnUs":
											isOnUsNodeVal=getCaptureIsOnUsCorrectedNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateIsOnUsTagOccurance(captureChildNote1,occurs,isOnUsNodeVal);
											validateIsOnUsTagLength(captureChildNote1,length,isOnUsNodeVal);
											break;
										case "IsDeleted":
											isDeletedNodeVal=getCaptureIsDeletedCorrectedNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateIsDeletedTagOccurance(captureChildNote1,occurs,isDeletedNodeVal);
											validateIsDeletedTagLength(captureChildNote1,length,isDeletedNodeVal);
											break;
										case "IsCorrected":
											isCorrectedNodeVal=getCaptureIsCorrectedNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateCaptureItemIsCorrectedTagOccurance(captureChildNote1,occurs,isCorrectedNodeVal);
											validateCaptureItemIsCorrectedLength(captureChildNote1,length,isCorrectedNodeVal);
											break;
										case "IsAmountCorrected":
											isAmountCorrectedNodeVal=getCaptureIsAmountCorrectedNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateIsAmountCorrectedTagOccurance(captureChildNote1,occurs,isAmountCorrectedNodeVal);
											validateIsAmountCorrectedTagLength(captureChildNote1,length,isAmountCorrectedNodeVal);
											break;
										case "OriginalAmount":
											originalAmountNodeVal=getCaptureOriginalAmountNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateOriginalAmountTagOccurance(captureChildNote1,occurs,originalAmountNodeVal);
											validateOriginalAmountTagLength(captureChildNote1,length,originalAmountNodeVal);
											break;
										case "IsTCCorrected":
											isTCCorrectedNodeVal=getCaptureIsTCCorrectedNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateIsTCCorrectedTagOccurance(captureChildNote1,occurs,isTCCorrectedNodeVal);
											validateIsTCCorrectedTagLength(captureChildNote1,length,isTCCorrectedNodeVal);
											break;
										case "IsANCorrected":
											isANCorrectedNodeVal=getCaptureIsANCorrectedNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateCaptureItemIsANCorrectedTagOccurance(captureChildNote1,occurs,isANCorrectedNodeVal);
											validateCaptureItemIsANCorrectedTagLength(captureChildNote1,length,isANCorrectedNodeVal);
											break;
										case "IsSortCodeCorrected":
											isSortCodeCorrectedNodeVal=getCaptureIsSortCodeCorrectedNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateIsSortCodeCorrectedTagOccurance(captureChildNote1,occurs,isSortCodeCorrectedNodeVal);
											validateIsSortCodeCorrectedTagLength(captureChildNote1,length,isSortCodeCorrectedNodeVal);
											break;
										case "IsSerialCorrected":
											isSerialCorrectedNodeVal=getCaptureIsSerialCorrectedNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateCaptureItemIsSerCorrectedTagOccurance(captureChildNote1,occurs,isSerialCorrectedNodeVal);
											validateCaptureItemIsSerCorrectedTagLength(captureChildNote1,length,isSerialCorrectedNodeVal);											
											break;
									
									case "IsReject":
										isRejectNodeVal=getCaptureItemIsRejectTagValidation(captureChildNote1,icnFilepath,icnFileName);
										validateCaptureItemIsRejectOccurence(captureChildNote1,occurs,isRejectNodeVal);
										validateCaptureItemIsRejectLength(captureChildNote1,length,isRejectNodeVal);
										break;
										//getBlockSubTagLenghtValidation(captureChildNote1,length,businessDateNodeVal);
									case "RejectReason":
											rejectRsnNodeVal=getCaptireItemRejectRsnNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateCaptureItemRjectRsnTagOccurence(captureChildNote1,occurs,rejectRsnNodeVal);
											validateCaptureItemRjectRsnTagLength(captureChildNote1,length,rejectRsnNodeVal);
											break;
									case "SpSelector":
											spSelectorNodeVal=getCaptureItemSpSelectorNodeVal(captureChildNote1,icnFilepath,icnFileName);
											validateCaptureItemSpSelectorTagOccurance(captureChildNote1,occurs,spSelectorNodeVal);
											validateCaptureItemSpSelectorTagLength(captureChildNote1,length,spSelectorNodeVal);
											//getBlockSubTagTypeValidation(captureChildNote1,length,businessDateNodeVal);
											break;
										
									case "Currency":
										currencyNodeVal=getICNCaptureItemCurrencyTagVal(captureChildNote1,icnFilepath,icnFileName);
										validateCaptureItemCurrencyTagOccurance(captureChildNote1,occurs,currencyNodeVal);
										validateCaptureItemCurrencyTagLength(captureChildNote1,length,currencyNodeVal);
									break;
									case "AdjustmentReason":
										adjstmntRsnNodeVal=getICNCaptureItemAdjstmntRsnTagVal(captureChildNote1,icnFilepath,icnFileName);
										validateAdjstmntRsnTagOccurance(captureChildNote1,occurs,adjstmntRsnNodeVal);
										validateAdjstmntRsnTagLength(captureChildNote1,length,adjstmntRsnNodeVal);
										break;
									case "Narrative": //Optional
										narrativeNodeVal=getNarrativeNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateNarrativeTagOccurance(captureChildNote1,occurs,narrativeNodeVal);
										validateNarrativeTagLength(captureChildNote1,length,narrativeNodeVal);
										break;
									case "OperatorId":
										operatorIdNodeVal=getCaptureItemOperatorIdNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateOperatorIdTagOccurance(captureChildNote1,occurs,operatorIdNodeVal);
										validateOperatorIdTagLength(captureChildNote1,length,operatorIdNodeVal);
										
										break;
									case "ProcessId":
										processidNodeVal=getCaptureItemProcessIdNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateProcessIdTagOccurance(captureChildNote1,occurs,processidNodeVal);
										validateProcessIdTagLength(captureChildNote1,length,processidNodeVal);
									
										break;
									case "ItemId":
										itemidNodeVal=getCaptureItemItemIdNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateItemIdTagOccurance(captureChildNote1,occurs,itemidNodeVal);
										validateItemIdTagLength(captureChildNote1,length,itemidNodeVal);
										break;
									case "OriginalIsn":
										originalISNNodeVal=getCaptureItemOriginalISNNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateOriginalISNTagOccurance(captureChildNote1,occurs,originalISNNodeVal);
										validateOriginalISNTagLength(captureChildNote1,length,originalISNNodeVal);
										break;
									case "AeStatus":
										aestatusNodeVal=getCaptureItemAeStatusNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateAeStatusTagOccurance(captureChildNote1,occurs,aestatusNodeVal);
										validateAeStatusTagLength(captureChildNote1,length,aestatusNodeVal);
										break;
									case "IcStatus":
										icstatusNodeVal=getCaptureItemIcStatusNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateIcStatusTagOccurance(captureChildNote1,occurs,icstatusNodeVal);
										validateIcStatusTagLength(captureChildNote1,length,icstatusNodeVal);
										break;
									case "IqvStatus":
										iqvstatusNodeVal=getCaptureItemIqvStatusNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateIqvStatusTagOccurance(captureChildNote1,occurs,iqvstatusNodeVal);
										validateIqvStatusTagLength(captureChildNote1,length,iqvstatusNodeVal);
										break;
									case "CarSetId":
										carsetidNodeVal=getCaptureItemCarSetIdNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateCarSetIdTagOccurance(captureChildNote1,occurs,carsetidNodeVal);
										validateCarSetIdTagLength(captureChildNote1,length,carsetidNodeVal);
										break;
									case "CarResult":
										carresultNodeVal=getCaptureItemCarResultNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateCarResultTagOccurance(captureChildNote1,occurs,carresultNodeVal);
										validateCarResultTagLength(captureChildNote1,length,carresultNodeVal);
										break;
									case "IaStatus":
										iastatusNodeVal=getCaptureItemIaStatusNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateIaStatusTagOccurance(captureChildNote1,occurs,iastatusNodeVal);
										validateIaStatusTagLength(captureChildNote1,length,iastatusNodeVal);
										break;
									case "IaResult":
										iaresultNodeVal=getCaptureItemIaResultNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateIaResultTagOccurance(captureChildNote1,occurs,iaresultNodeVal);
										validateIaResultTagLength(captureChildNote1,length,iaresultNodeVal);
										break;
									case "PNVReviewStatus":
										pnvreviewstatusNodeVal=getCaptureItemPNVReviewStatusStatusNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validatePNVReviewStatusTagOccurance(captureChildNote1,occurs,pnvreviewstatusNodeVal);
										validatePNVReviewStatusTagLength(captureChildNote1,length,pnvreviewstatusNodeVal);
										break;
									case "DuplicateStatus":
										duplicatestatusNodeVal=getCaptureItemDuplicateStatusNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateDuplicateStatusTagOccurance(captureChildNote1,occurs,duplicatestatusNodeVal);
										validateDuplicateStatusTagLength(captureChildNote1,length,duplicatestatusNodeVal);
										break;
									case "ReturnReason":
										returnreasonNodeVal=getCaptureItemReturnReasonNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateReturnReasonTagOccurance(captureChildNote1,occurs,returnreasonNodeVal);
										validateReturnReasonTagLength(captureChildNote1,length,returnreasonNodeVal);
										break;
									case "ChequeIssuedDate":
										chequeissueddateNodeVal=getCaptureItemChequeIssuedDateNodeVal(captureChildNote1,icnFilepath,icnFileName);
										validateChequeIssuedDateTagOccurance(captureChildNote1,occurs,chequeissueddateNodeVal);
										validateChequeIssuedDateTagLength(captureChildNote1,occurs,chequeissueddateNodeVal);
										break;
									
				}
			}		
			}
			}	
			}
		
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
		
		public static void getRootNodeValidation(String rootNode,String icnFilepath,String icnFileName) throws Exception{
			
			System.out.println("For loop RootNode Core :"+rootNode);
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			System.out.println("****************************************************************");
			System.out.println("Validating CaptureInfo is present in ICN as per IDS version 8 :"+rootNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("****************************************************************");
			validationStepInformation("Validating CaptureInfo ChilNode is present in ICN as per IDS version 8 :"+rootNode);
			validationStepInformation("****************************************************************");
			validationStepInformation("icnCaptureInfoXpath "+icnCaptureInfoXpath);
			Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureInfoXpath);
			String nodeKey = getMapKey(coreNodeVal);
			//coreName= coreNodeVal.tostatic String();
			//for(int i=0;i<coreNodeVal.size();i++){
		//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
			System.out.println("coreNodeVal :"+nodeKey);
			boolean flag =Component.verifyEquals(nodeKey, rootNode, "Root tagname is as per IDSv8");			
			System.out.println("Validation is performed for RootTag :"+flag);
			validationStepInformation("Validation is performed for RootTag :"+flag);
			publishResults(flag,nodeKey, rootNode, "CaptureInfo tagname is as per IDSv8");
		}
		
		public static void getCaptureInfoChildNodeValidation(String captureInfoChilNode,String icnFilepath,String icnFileName) throws Exception{
			
			System.out.println("****************************************************************");
			System.out.println("Validating CaptureItem is present in ICN as per IDS version 8 :"+captureInfoChilNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("****************************************************************");
			validationStepInformation("Validating CaptureItem ChilNode is present in ICN as per IDS version 8 :"+captureInfoChilNode);
			validationStepInformation("****************************************************************");
			
			Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemXpath);
			String nodeKey = getMapKey(coreNodeVal);
			//coreName= coreNodeVal.tostatic String();
			//for(int i=0;i<coreNodeVal.size();i++){
		//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
			System.out.println("CaptureItem :"+nodeKey);
			boolean flag =Component.verifyEquals(nodeKey, captureInfoChilNode, "CaptureItem tagname is as per IDSv8");			
			System.out.println("Validation is performed for CaptureItem :"+flag);
			validationStepInformation("Validation is performed for CaptureItem :"+flag);
			publishResults(flag,nodeKey, captureInfoChilNode, "CaptureItem tagname is as per IDSv8");
		}
		
		public static String validateICNCaptureItemJOBTag(String captureInfoChilNode,String icnFilepath,String icnFileName) throws Exception{
			
			System.out.println("****************************************************************");
			System.out.println("Validating CaptureItem JOB is present in ICN as per IDS version 8 :"+captureInfoChilNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("****************************************************************");
			validationStepInformation("Validating CaptureItem JOB ChilNode is present in ICN as per IDS version 8 :"+captureInfoChilNode);
			validationStepInformation("****************************************************************");
			
			Map<String, String> captureItemJobNodeVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJobXpath);
			String nodeKey = getMapKey(captureItemJobNodeVal);
			//coreName= coreNodeVal.tostatic String();
			//for(int i=0;i<coreNodeVal.size();i++){
		//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
			System.out.println("JobNodeKey :"+nodeKey);
			boolean flag =Component.verifyEquals(nodeKey, captureInfoChilNode, "CaptureItem JOB tagname is as per IDSv8");			
			System.out.println("Validation is performed for CaptureItem JOB :"+flag);
			publishResults(flag,nodeKey, captureInfoChilNode, "CaptureItem JOB tagname is as per IDSv8");
			
			return nodeKey;			
		}
		
		
		public static String getAPGDINValidationNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemAPGDIN);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemAPGDIN);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,apgDINTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureIsSerialCorrectedNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIsSerialCorrected);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIsSerialCorrected);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,isSerialcorrectedTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		//BLOCK Validation
		
		public static String getCaptureItemIsRejectTagValidation(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIsReject);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIsReject);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,isRejectTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptireItemRejectRsnNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemRejectReason);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemRejectReason);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,rejectReasonTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemSpSelectorNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemSpSelector);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemSpSelector);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,spSelectorTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
				
		public static String getICNCaptureItemCurrencyTagVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCurrency);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCurrency);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,currencyTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getICNCaptureItemAdjstmntRsnTagVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemAdjustmentReason);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemAdjustmentReason);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,adjustmentReasonTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getNarrativeNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemNarrative);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemNarrative);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,narrativeTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemOperatorIdNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemOperatorId);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemOperatorId);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,operatorIdTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemProcessIdNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemProcessId);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemProcessId);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,processIdTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemItemIdNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemItemId);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemItemId);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,itemIdTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getIsElectronicNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			
				System.out.println("****************************************************************");
				System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
				System.out.println("****************************************************************");
				
				validationStepInformation("*********************************************************************************************************************");
				validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
				validationStepInformation("*********************************************************************************************************************");
				Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIsElectronic);
				String dateKey =getMapKey(dateKeyVal);
				System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
				Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIsElectronic);
				String bdateNodeVal = getNodeListValues(dateKeyVal1,isElectronicTagName).get(0);
				System.out.println("bdateNodeVal :"+bdateNodeVal);
				boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
					
				publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
				return bdateNodeVal;
		}
		
		public static String getCaptureItemAeStatusNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemAeStatus);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemAeStatus);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,aeStatusTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemCarResultNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCarResult);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCarResult);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,carResultTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemIcStatusNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIcStatus);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIcStatus);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,icStatusTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemCarSetIdNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCarSetId);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCarSetId);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,carSetIdTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemIqvStatusNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIqvStatus);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIqvStatus);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,iqvStatusTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItmGenderTagNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemGender);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemGender);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,genderTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemPNVReviewStatusStatusNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIaResult);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIaResult);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,iaResultTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemIaResultNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemGender);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemGender);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,genderTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemIaStatusNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIaStatus);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIaStatus);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,iaStatusTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemChequeIssuedDateNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			String bdateNodeVal =null;
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			try{
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCheqIssuedDate);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCheqIssuedDate);
			 bdateNodeVal = getNodeListValues(dateKeyVal1,chequeIssuedDateTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			return bdateNodeVal;
		}
		
		public static String getCaptureItemReturnReasonNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemRetrnRsn);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemRetrnRsn);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,returnReasonTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemDuplicateStatusNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemDuplicateSts);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemDuplicateSts);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,duplicateStatusTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		public static String getCaptureIsANCorrectedNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIsANCorrected);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIsANCorrected);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,isANCorrectedTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureIsSortCodeCorrectedNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIsSortCodeCorrected);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIsSortCodeCorrected);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,isSortCodeCorrectedTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureIsCorrectedNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIsCorrected);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIsCorrected);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,isCorrectedTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureIsDeletedCorrectedNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIsDeleted);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIsDeleted);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,isDeletedTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		
		public static String getCaptureIsOnUsCorrectedNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIsOnUs);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIsOnUs);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,isOnUsTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		
		public static String getCaptureIsAmountCorrectedNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIsAmountCorrected);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIsAmountCorrected);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,isAmountCorrectedTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureOriginalAmountNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			String bdateNodeVal=null;
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			try{
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemOriginalAmount);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemOriginalAmount);
			 bdateNodeVal = getNodeListValues(dateKeyVal1,originalAmountTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			return bdateNodeVal;
		}
		
		public static String getCaptureIsTCCorrectedNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemIsTCCorrected);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemIsTCCorrected);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,isTcCorrectedTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getCaptureItemOriginalISNNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemOriginalIsn);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemOriginalIsn);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,originalISNTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getAPGBuisnessDateValidationTag(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemAPGBusinessDate);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemAPGBusinessDate);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,apgBusinessDateTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getTransactionNumberValidationTag(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			System.out.println("****************************************************************");
			System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
			validationStepInformation("*********************************************************************************************************************");
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemTransactionNumber);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemTransactionNumber);
			String bdateNodeVal = getNodeListValues(dateKeyVal1,transactionNumberTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
				
			publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		
		
		//Occurence Validation
		public static void validateCaptureItemCurrencyTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BusinessDate in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BusinessDate tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void getJobInstallationTagOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal) throws Exception {
				System.out.println("*****CaptureItem JOB "+bdateNodeVal+" Occurance in ICN file validation starts here*********");
				validationStepInformation("*****CaptureItem Job "+bdateNodeVal+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(genderNodeVal!=null,(genderNodeVal!=null)?"InstallationId Node value "+genderNodeVal+" validation performed":"InstallationId tag is null","InstallationId Node value "+genderNodeVal+" validation performed","InstallationId Node value "+genderNodeVal+" validation performed");
		}
			
	  public static void validateCaptureItemIsRejectOccurence(String coreChildNode,String occurs,String bdateNodeVal){
		//ExtractID occurrence validation
		  System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for Block Nbr in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag1 = Component.verifyTrue(isRejectNodeVal!=null, "Block tag containing node and is present under CaptureItem");
			System.out.println("Block tag is containing nodes :"+flag1);
			publishResults(flag1,(isRejectNodeVal!=null)?"BLOCK tag is containing node and is present under captureItem ":"BLOCK tag does not contain any node","BLOCK tag is containing node and is present under captureItem ","BLOCK Occurence validation");
			
	  }
	 
		public static void validateCaptureItemRjectRsnTagOccurence(String coreChildNode,String occurs,String bdateNodeVal){
		//ExtractID occurrence validation
			System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for Block Nbr in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag1 = Component.verifyTrue(rejectRsnNodeVal!=null, "Block tag containing node and is present under CaptureItem");
			System.out.println("BlockNbr tag is containing value :"+flag1);
			publishResults(flag1,(rejectRsnNodeVal!=null)?"BLOCK tag is containing node and is present under captureItem ":"BLOCK tag does not contain any node","BLOCK tag is containing node and is present under captureItem ","BLOCK Occurence validation");
		}
		
		public static void validateCaptureItemSpSelectorTagOccurance(String coreChildNode,String occurs,String imageTypeNodeVal){
			//ProcessingParticipantID
			System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for Block Nbr in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag1 = Component.verifyTrue(imageTypeNodeVal!=null, "Block tag containing node and is present under CaptureItem");
			System.out.println("Block ImageType tag is containing value :"+flag1);
			publishResults(flag1,(imageTypeNodeVal!=null)?"Block ImageType tag is containing node and is present under captureItem ":"BLOCK ImageType tag does not contain any node","Block ImageType tag is containing node and is present under captureItem ","BLOCK Occurence validation");
		}
		
		//BATCH Occurence Validation
		public static void getJobBusinessDateTagOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal) throws Exception {
			//	getCoreSubTagsOccuranceValidation(occurs,businessDateNodeVal,extractIDNodeVal,processingParticipantIdNodeVal,intMessageTypeNodeVal,
				//extMessageTypeNodeVal,messageSourceNodeVal,recordCountsNodeVal,messageDestinationNodeVal);
				
				System.out.println("*****CaptureItem JOB BusinessDate Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				//Element element = null;
				//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation(ICNFilepath,ICNFileName,element);
			if(bdateNodeVal!=null){
			    System.out.println("*****As per IDS version 8, CaptureItem JOB BusinessDate Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, CaptureItem JOB BusinessDate Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				 boolean assertFlagCheck = FREDXMLValidation.validateBusinessDateDatabaseFormat(bdateNodeVal);
				 boolean flag = Component.verifyTrue(assertFlagCheck, "JOB BusinessDate date type and data format validation performed.");
				 System.out.println("JOB BusinessDate date type and data format"+flag);
				}
			else
				{
				System.out.println("JOB BusinessDate should not be null!!");
				}
			publishResults(bdateNodeVal!=null,(bdateNodeVal!=null)?"BusinessDate date type and data format "+bdateNodeVal+" validation performed":"BusinessDate tag is null","BusinessDate date type and data format "+bdateNodeVal+" validation performed","BusinessDate date type and data format "+bdateNodeVal+" validation performed");
			}
		
		//CaptureItem Tag Occurence
		public static void validateOriginalISNTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

		}
		public static void validateItemIdTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

		}
		public static void validateProcessIdTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
			System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

		}
	public static void validateOperatorIdTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateCaptureItemIsCorrectedTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateIsAmountCorrectedTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateOriginalAmountTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateIsTCCorrectedTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateCaptureItemIsANCorrectedTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateIsSortCodeCorrectedTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateCaptureItemIsSerCorrectedTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {

	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
		
		public static void validateAdjstmntRsnTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void validateIsDeletedTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void validateIsOnUsTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchTktSC in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void validateTransactionNbrTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchTktSC in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void validateCaptureItemGenderTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchCreditCount in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchCreditCount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		
		public static void validateAPGBusinessDateTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitCount in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitCount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		
		public static void validateIsElectronicTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchCreditAmount in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchCreditAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void validateNarrativeTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
public static void validateIaStatusTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
public static void validateCarResultTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}

public static void validateCarSetIdTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}

public static void validateIqvStatusTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}

public static void validateIcStatusTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}

public static void validateAeStatusTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}
	
public static void validateDuplicateStatusTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}
public static void validateReturnReasonTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}
public static void validateChequeIssuedDateTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}
	
public static void validateAPGDINTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}

public static void validatePNVReviewStatusTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}


public static void validateIaResultTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
}
		// CaptureItem Length Validation
		
			public static void validateIcStatusTagLength(String coreChildNode,String length,String bdateNodeVal){
				 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

			}
		
			public static void validateIqvStatusTagLength(String coreChildNode,String length,String bdateNodeVal){
				 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

			}
			
			public static void validateCarSetIdTagLength(String coreChildNode,String length,String bdateNodeVal){
				 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

			}
			
			public static void validateCarResultTagLength(String coreChildNode,String length,String bdateNodeVal){
				 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

			}
			
			public static void validateIaStatusTagLength(String coreChildNode,String length,String bdateNodeVal){
				 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
				 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

			}

public static void validateIaResultTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validatePNVReviewStatusTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())==Integer.parseInt(length)),((bdateNodeVal.length())==Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}
public static void validateAdjstmntRsnTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateCaptureItemSpSelectorTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateCaptureItemRjectRsnTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateCaptureItemIsANCorrectedTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateIsAmountCorrectedTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateCaptureItemIsCorrectedLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateReturnReasonTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateDuplicateStatusTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())==Integer.parseInt(length)),((bdateNodeVal.length())==Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}
public static void validateAPGDINTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateOriginalAmountTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 try{
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");
	 }
	 catch(Exception e){
		 System.out.println(e.getMessage());
	 }
}


public static void validateIsTCCorrectedTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateIsOnUsTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateIsDeletedTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateOperatorIdTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateProcessIdTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateItemIdTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())==Integer.parseInt(length)),((bdateNodeVal.length())==Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateOriginalISNTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}
public static void validateAeStatusTagLength(String coreChildNode,String length,String bdateNodeVal){

	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateNarrativeTagLength(String coreChildNode,String length,String bdateNodeVal){

	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateCaptureItemCurrencyTagLength(String coreChildNode,String length,String bdateNodeVal){

	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateIsElectronicValLength(String coreChildNode,String length,String bdateNodeVal){

	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateCaptureItemGenderTagLength(String coreChildNode,String length,String bdateNodeVal){

	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateTransactionNbrTagLength(String coreChildNode,String length,String bdateNodeVal){

	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}


public static void validateIsSortCodeCorrectedTagLength(String coreChildNode,String length,String bdateNodeVal){

	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateCaptureItemIsSerCorrectedTagLength(String coreChildNode,String length,String bdateNodeVal){

	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}

public static void validateCaptureItemIsRejectLength(String coreChildNode,String length,String bdateNodeVal){

	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

}


public static void validateChequeIssuedDateTagLength(String coreChildNode,String length,String bdateNodeVal){
  // Check dateTimeformat
	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	try{
	 boolean flag= FREDXMLValidation.validateBusinessDateDatabaseFormat(bdateNodeVal);
	if(flag)
	publishResults(flag,coreChildNode+" format is "+bdateNodeVal+" in ICN",coreChildNode+" format is "+bdateNodeVal+" in ICN",coreChildNode+" Length validation");
	else
	publishResults(flag,coreChildNode+" format is "+bdateNodeVal+" in ICN not as per IDS v8",coreChildNode+" format is "+bdateNodeVal+" in ICN",coreChildNode+" Length validation");
	}
	catch(Exception e){
		System.out.println(e.getMessage());
	}
}

public static void validateAPGBusinessDateTagLength(String coreChildNode,String length,String bdateNodeVal){

	 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
	boolean flag= FREDXMLValidation.validateBusinessDateDatabaseFormat(bdateNodeVal);
	if(flag)
	publishResults(flag,coreChildNode+" format is "+bdateNodeVal+" in ICN",coreChildNode+" format is "+bdateNodeVal+" in ICN",coreChildNode+" Length validation");
	else
	publishResults(flag,coreChildNode+" format is "+bdateNodeVal+" in ICN not as per IDS v8",coreChildNode+" format is "+bdateNodeVal+" in ICN",coreChildNode+" Length validation");

}


}


