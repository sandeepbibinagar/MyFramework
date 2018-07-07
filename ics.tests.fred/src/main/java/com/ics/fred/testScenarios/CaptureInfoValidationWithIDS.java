	package com.ics.fred.testScenarios;

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
	import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

	public class CaptureInfoValidationWithIDS extends GenericMethodUtilis{

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
		
		//CaptureInfoChildNode2
		static String icnCaptureItemJOBBusinessDate=	"//CaptureInfo/CaptureItem/JOB/BusinessDate";
		static String icnCaptureItemJOBInstallationId=	"//CaptureInfo/CaptureItem/JOB/InstallationId";
		static String icnCaptureItemJOBCaptureSystemId=	"//CaptureInfo/CaptureItem/JOB/CaptureSystemId";
		static String icnCaptureItemJOBStartTime=	"//CaptureInfo/CaptureItem/JOB/StartTime";
		static String icnCaptureItemJOBEndTime=	"//CaptureInfo/CaptureItem/JOB/EndTime";
		static String icnCaptureItemJOBWorkTypeNbr=	"//CaptureInfo/CaptureItem/JOB/WorkTypeNbr";
		static String icnCaptureItemJOBSortFamily=	"//CaptureInfo/CaptureItem/JOB/SortFamily";
		static String icnCaptureItemJOBSourceType=	"//CaptureInfo/CaptureItem/JOB/SourceType";
		static String icnCaptureItemJOBSourceName=	"//CaptureInfo/CaptureItem/JOB/SourceName";
		static String icnCaptureItemJOBSourceID=	"//CaptureInfo/CaptureItem/JOB/SourceID";
		static String icnCaptureItemJOBFinancialInstitutionID=	"//CaptureInfo/CaptureItem/JOB/FinancialInstitutionID";
		static String icnCaptureItemJOBCollectionStartTime=	"//CaptureInfo/CaptureItem/JOB/CollectionStartTime";
		static String icnCaptureItemJOBCollectionEndTime=	"//CaptureInfo/CaptureItem/JOB/CollectionEndTime";
	
		static String icnCaptureItemBLOCKBlkNbr=	"//CaptureInfo/CaptureItem/BLOCK/BlkNbr";
		static String icnCaptureItemBLOCKImageType=	"//CaptureInfo/CaptureItem/BLOCK/ImageType";
		
		static String icnCaptureItemBATCHTktTc=	"//CaptureInfo/CaptureItem/BATCH/TktTc";
		static String icnCaptureItemBATCHTktAn=	"//CaptureInfo/CaptureItem/BATCH/TktAn";
		static String icnCaptureItemBATCHTktSC=	"//CaptureInfo/CaptureItem/BATCH/TktSC";
		static String icnCaptureItemBATCHTktAux=	"//CaptureInfo/CaptureItem/BATCH/TktAux";
		static String icnCaptureItemBATCHBatchCreditCount=	"//CaptureInfo/CaptureItem/BATCH/BatchCreditCount";
		static String icnCaptureItemBATCHBatchDebitCount=	"//CaptureInfo/CaptureItem/BATCH/BatchDebitCount";
		static String icnCaptureItemBATCHBatchCreditAmount=	"//CaptureInfo/CaptureItem/BATCH/BatchCreditAmount";
		static String icnCaptureItemBATCHBatchDebitAmount=	"//CaptureInfo/CaptureItem/BATCH/BatchDebitAmount";
		static String icnCaptureItemBATCHBatchSourceData=	"//CaptureInfo/CaptureItem/BATCH/BatchSourceData";
		
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
		
		static String icnCaptureItemItemHistoryAuditRevision=	"//CaptureInfo/CaptureItem/ItemHistory/AuditRevision";
		static String icnCaptureItemItemHistoryUserId=	"//CaptureInfo/CaptureItem/ItemHistory/UserId";
		static String icnCaptureItemItemHistoryTime=	"//CaptureInfo/CaptureItem/ItemHistory/Time";
		static String icnCaptureItemItemHistoryProcess=	"//CaptureInfo/CaptureItem/ItemHistory/Process";
		static String icnCaptureItemItemHistoryIsDeleted=	"//CaptureInfo/CaptureItem/ItemHistory/IsDeleted";
		static String icnCaptureItemItemHistoryGender=	"//CaptureInfo/CaptureItem/ItemHistory/Gender";
		static String icnCaptureItemItemHistoryReference=	"//CaptureInfo/CaptureItem/ItemHistory/Reference";
		static String icnCaptureItemItemHistoryAccount=	"//CaptureInfo/CaptureItem/ItemHistory/Account";
		static String icnCaptureItemItemHistorySortCode=	"//CaptureInfo/CaptureItem/ItemHistory/SortCode";
		static String icnCaptureItemItemHistoryTranCode=	"//CaptureInfo/CaptureItem/ItemHistory/TranCode";
		static String icnCaptureItemItemHistoryAmount=	"//CaptureInfo/CaptureItem/ItemHistory/Amount";

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
		
		static String jobBusinessDateNodeVal,installationIdNodeVal,captureSystemIdNodeVal,startTimeNodeVal,endTimeNodeVal,workTypeNbrNodeVal,sortFamilyNodeVal,sourceTypeNodeVal,sourceNameNodeVal;
		static String sourceIDNodeVal,financialInstitutionIDNodeVal,collectionStartTimeNodeVal,collectionEndTimeNodeVal;
		static String blockNodeVal,blkNbrNodeVal,imageTypeNodeVal;
	

	//	public static static String businessDateTagName	InstallationIdTagName	CaptureSystemIdTagName	StartTimeTagName	EndTimeTagName	WorkTypeNbrTagName	SortFamilyTagName	SourceTypeTagName	SourceNameTagName	SourceIDTagName	FinancialInstitutionIDTagName	CollectionStartTimeTagName	CollectionEndTimeTagName

		
		 static String businessDateTagName = "BusinessDate";
		 static String extractIdTagName = "ExtractId";
		 static String intMessageTypeTagName = "IntMessageType";
		 static String processingParticipantIdTagName = "ProcessingParticipantId";
		 static String extMessageTypeTagName = "ExtMessageType";
		 static String messageSourceTagName = "MessageSource";	
		 static String recordCountsTagName="RecordCounts";
		 static String messageDestinationTagName="MessageDestination";
		 static String xsdFileCoreNodeTagName="Core";
		static String instlltionIdNodeVal,intMessageTypeNodeVal,processingParticipantIdNodeVal,extMessageTypeNodeVal,messageSourceNodeVal;
		 static String extractIDNodeVal,recordCountsNodeVal,messageDestinationNodeVal;

		
	 //
	 	
		//private static final static String businessDateTagName = null;

		//public static void main(static String[] args) throws Exception {
		@Test
		public static void validateCaptureInfoTags() throws Exception {
			// TODO Auto-generated method stub

			//icnCoreBusinessDate;
			FileInputStream inputStream = new FileInputStream(excelFilePath);
			
			Workbook wrkBk = new XSSFWorkbook(inputStream);
			int index = wrkBk.getSheetIndex("CaptureInfo");
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
							//listTag.add(cell.getRichstatic StringCellValue.tostatic String());
							tag.setCaptureInfoChildNode4(cell.getRichStringCellValue().toString());
							System.out.println("CaptureInfoChildNode4 ChildNode Tag val 1:"+cell.getRichStringCellValue().toString());
							count++;
							break;
						case 7:
							tag.setOccurs(cell.getRichStringCellValue().toString());
							count++;
							break;
						case 8:
							tag.setFixedVar(cell.getRichStringCellValue().toString());
							count++;
							break;
						case 9:
							tag.setType(cell.getRichStringCellValue().toString());
						//	int type = (int)cell.getNumericCellValue();
						//	tag.setType(Integer.toString(type));
							count++;
							break;
						case 10:
							int len = (int)cell.getNumericCellValue();
							tag.setLength(Integer.toString(len));
						
						//	String len = cell.getRichStringCellValue().toString();
							//tag.setLength(len);
							
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
				coreChildNode = tags.getCoreChildNode();
				rootNode =tags.getRootNode();
				
				captureChildNote = tags.getCaptureInfoChildNode(); //CaptureInfoChildNode  CaptureItem
				captureChildNote1 =tags.getCaptureInfoChildNode1(); //CaptureInfoChildNode1 JOB, BTACH,BLOCK,APGIN....
				captureChildNote2=tags.getCaptureInfoChildNode2();
				captureChildNote3=tags.getCaptureInfoChildNode3();
				captureChildNote4=tags.getCaptureInfoChildNode4();
				
				occurs=tags.getOccurs();
				type=tags.getType();
				length=tags.getLength();
				fixedVar=tags.getFixedVar();
				if(null!=rootNode){
					getRootNodeValidation(rootNode);
					if(null!=captureChildNote){
							getCaptureInfoChildNodeValidation(captureChildNote);
							if(null!=captureChildNote1){
								switch(captureChildNote1){
									case "JOB":
									jobNodeVal=validateICNCaptureItemJOBTag(captureChildNote1);
									//getJobSubTagsOccuranceValidation(captureChildNote1,occurs,businessDateNodeVal);
									System.out.println("*****As per IDS version 8, ChildCoreNode BusinessDate DataType in ICN is :"+type);
									System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);
									System.out.println("Lenght validation is not required for BusinessDate in IDS v8 "+length);
								//	getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal);
									switch(captureChildNote2){
										case "BusinessDate":
											jobBusinessDateNodeVal=getJOBBuisnessDateValidationTag(captureChildNote2);
											getJobBusinessDateTagOccuranceValidation(captureChildNote2,occurs,jobBusinessDateNodeVal);
											
											break;
										case "InstallationId":
											installationIdNodeVal=getJOBInstallationIdValidationTag(captureChildNote2);
											getJobInstallationTagOccuranceValidation(captureChildNote2,occurs,instlltionIdNodeVal);
											//getJobInstallationIdLenghtValidation(captureChildNote1,length,installationIdNodeVal);
											break;
											
									}
									
									break;
									case "BLOCK":
										blockNodeVal=getCaptureItemBlockTagValidation(captureChildNote1);
										getCaptureItemBlockOccuranceValidation(captureChildNote1,occurs,blockNodeVal);
										//getBlockSubTagLenghtValidation(captureChildNote1,length,businessDateNodeVal);
										switch(captureChildNote2){
											case "BlkNbr":
													blkNbrNodeVal=validateCaptureItemBlockNbr(captureChildNote2);
													getCaptureItemBlockNbrOccuranceValidation(captureChildNote2,occurs,blkNbrNodeVal);
													//getBlockSubTagLenghtValidation(captureChildNote1,length,businessDateNodeVal);
													break;
											case "ImageType":
													imageTypeNodeVal=validateCaptureItemBlockImageType(captureChildNote2);
													getCaptureItemBlockImageTypeOccuranceValidation(captureChildNote2,occurs,imageTypeNodeVal);
													//getBlockSubTagLenghtValidation(captureChildNote1,length,businessDateNodeVal);
													//getBlockSubTagTypeValidation(captureChildNote1,length,businessDateNodeVal);
													break;
											
										}
						
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
		
		public static void getRootNodeValidation(String rootNode) throws Exception{
			
			System.out.println("For loop RootNode Core :"+rootNode);
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			System.out.println("****************************************************************");
			System.out.println("Validating CaptureInfo is present in ICN as per IDS version 8 :"+rootNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("****************************************************************");
			validationStepInformation("Validating CaptureInfo ChilNode is present in ICN as per IDS version 8 :"+rootNode);
			validationStepInformation("****************************************************************");

			Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCaptureInfoXpath);
			String nodeKey = getMapKey(coreNodeVal);
			//coreName= coreNodeVal.tostatic String();
			//for(int i=0;i<coreNodeVal.size();i++){
		//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
			System.out.println("coreNodeVal :"+nodeKey);
			boolean flag =Component.verifyEquals(nodeKey, rootNode, "Core tagname is as per IDSv8");			
			System.out.println("Validation is performed for CoreTag :"+flag);
			publishResults(flag,nodeKey, rootNode, "CaptureInfo tagname is as per IDSv8");
		}
		
		public static void getCaptureInfoChildNodeValidation(String captureInfoChilNode) throws Exception{
			
			System.out.println("****************************************************************");
			System.out.println("Validating CaptureItem is present in ICN as per IDS version 8 :"+captureInfoChilNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("****************************************************************");
			validationStepInformation("Validating CaptureItem ChilNode is present in ICN as per IDS version 8 :"+captureInfoChilNode);
			validationStepInformation("****************************************************************");
			
			Map<String, String> coreNodeVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCaptureItemXpath);
			String nodeKey = getMapKey(coreNodeVal);
			//coreName= coreNodeVal.tostatic String();
			//for(int i=0;i<coreNodeVal.size();i++){
		//	coreName = coreNodeVal.entrySet().iterator().next().getKey();
			System.out.println("coreNodeVal :"+nodeKey);
			boolean flag =Component.verifyEquals(nodeKey, captureInfoChilNode, "CaptureInfo tagname is as per IDSv8");			
			System.out.println("Validation is performed for CaptureInfo :"+flag);
			publishResults(flag,nodeKey, captureInfoChilNode, "CaptureInfo tagname is as per IDSv8");
		}
		
		public static String validateICNCaptureItemJOBTag(String captureInfoChilNode) throws Exception{
			
			System.out.println("****************************************************************");
			System.out.println("Validating CaptureItem JOB is present in ICN as per IDS version 8 :"+captureInfoChilNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("****************************************************************");
			validationStepInformation("Validating CaptureItem JOB ChilNode is present in ICN as per IDS version 8 :"+captureInfoChilNode);
			validationStepInformation("****************************************************************");
			
			Map<String, String> captureItemJobNodeVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCaptureItemJobXpath);
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
		
		
		public static String getJOBBuisnessDateValidationTag(String coreChildNode) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			 String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating CaptureItem BusinessDate is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BusinessDate is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCaptureItemJOBBusinessDate);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCaptureItemJOBBusinessDate);
			bdateNodeVal =getNodeListValues(dateKeyVal1,businessDateTagName).get(0);
			System.out.println("bdateNodeVal :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "JOB BusinessDate tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("JOB BusinessDate Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("JOB BusinessDate Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "JOB BusinessDate tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBInstallationIdValidationTag(String coreChildNode) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating InstallationId is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem InstallationId is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCaptureItemJOBInstallationId);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::InstallationId KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCaptureItemJOBInstallationId);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"InstallationId").get(0);
			System.out.println("InstallationId :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "InstallationId tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("InstallationId Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("InstallationId Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "InstallationId tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		
		public static String getCaptureItemBlockTagValidation(String coreChildNode) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BLOCK Tag is populating under CaptureItem in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BLOCK is populating in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCaptureItemBLOCKXpath);
			 String dateKey =getMapKey(dateKeyVal);
			System.out.println("Block Tag Key:"+dateKey+"::Block Tag KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCaptureItemBLOCKXpath);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"BLOCK").get(0);
			System.out.println("Block :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "Block tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("Block Tag is present under CaptureItem Tag in ICN file as per IDS v8 :");
			else
				System.out.println("Block Tag is not present under CaptureItem  Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "Block tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String validateCaptureItemBlockNbr(String coreChildNode) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			 String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BlkNbr is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BlkNbr is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCaptureItemBLOCKBlkNbr);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::InstallationId KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCaptureItemBLOCKBlkNbr);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"BlkNbr").get(0);
			System.out.println("BlkNbr :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "BlkNbr tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("Block Nbr Tag is present under Block Tag in ICN file as per IDS v8 :");
			else
				System.out.println("Block Nbr Tag is not present under Block Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "BlkNbr tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String validateCaptureItemBlockImageType(String coreChildNode) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BLOCK ImageType is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BLOCK ImageType is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(ICNFilepath,ICNFileName,icnCaptureItemBLOCKImageType);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("Block ImageType Key:"+dateKey+"::Block ImageType KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(ICNFilepath,ICNFileName,icnCaptureItemBLOCKImageType);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"ImageType").get(0);
			System.out.println("ImageType :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "ImageType tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("ImageType Tag is present under BLOCK Tag in ICN file as per IDS v8 :");
			else
				System.out.println("ImageType Tag is not present under BLOCK Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "ImageType tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		
		//Occurence Validation
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
		
		public static void getJobInstallationTagOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal) throws Exception {
				System.out.println("*****CaptureItem JOB "+bdateNodeVal+" Occurance in ICN file validation starts here*********");
				validationStepInformation("*****CaptureItem Job "+bdateNodeVal+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for BusinessDate in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(installationIdNodeVal!=null,(installationIdNodeVal!=null)?"InstallationId Node value "+installationIdNodeVal+" validation performed":"InstallationId tag is null","InstallationId Node value "+installationIdNodeVal+" validation performed","InstallationId Node value "+installationIdNodeVal+" validation performed");
		}
			
	  public static void getCaptureItemBlockOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
		//ExtractID occurrence validation
		  System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for Block Nbr in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag1 = Component.verifyTrue(blockNodeVal!=null, "Block tag containing node and is present under CaptureItem");
			System.out.println("Block tag is containing nodes :"+flag1);
			publishResults(flag1,(blockNodeVal!=null)?"BLOCK tag is containing node and is present under captureItem ":"BLOCK tag does not contain any node","BLOCK tag is containing node and is present under captureItem ","BLOCK Occurence validation");
			
	  }
	 
		public static void getCaptureItemBlockNbrOccuranceValidation(String coreChildNode,String occurs,String bdateNodeVal){
		//ExtractID occurrence validation
			System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for Block Nbr in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag1 = Component.verifyTrue(blkNbrNodeVal!=null, "Block tag containing node and is present under CaptureItem");
			System.out.println("BlockNbr tag is containing value :"+flag1);
			publishResults(flag1,(blkNbrNodeVal!=null)?"BLOCK tag is containing node and is present under captureItem ":"BLOCK tag does not contain any node","BLOCK tag is containing node and is present under captureItem ","BLOCK Occurence validation");
		}
		
		public static void getCaptureItemBlockImageTypeOccuranceValidation(String coreChildNode,String occurs,String imageTypeNodeVal){
			//ProcessingParticipantID
			System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for Block Nbr in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag1 = Component.verifyTrue(imageTypeNodeVal!=null, "Block tag containing node and is present under CaptureItem");
			System.out.println("Block ImageType tag is containing value :"+flag1);
			publishResults(flag1,(imageTypeNodeVal!=null)?"Block ImageType tag is containing node and is present under captureItem ":"BLOCK ImageType tag does not contain any node","Block ImageType tag is containing node and is present under captureItem ","BLOCK Occurence validation");
		}
		/*
		public void getCoreSubTagsExtMsgTypeOccuranceValidation(static String coreChildNode,static String occurs,static String bdateNodeVal){
		//ExtMsgType
			System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag4 = Component.verifyTrue(extMessageTypeNodeVal!=null, "extMessageTypeNodeVal tag containing value");
			System.out.println("extMessageTypeNodeVal tag is containing value :"+flag4);
			publishResults(Component.verifyTrue(extMessageTypeNodeVal!=null, "extMessageTypeNodeVal tag containing value"),(extMessageTypeNodeVal!=null)?"ExtMessageType tag is containing value":"ExtMessageType tag is null","ExtMessageType tag is containing value","ExtMessageType Occurence validation");
		}
		
		//IntMsgType
		public void getCoreSubTagsIntMsgTypeOccuranceValidation(static String coreChildNode,static String occurs,static String bdateNodeVal){
			System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			boolean flag3 = Component.verifyTrue(intMessageTypeNodeVal!=null, "intMessageTypeNodeVal tag containing value");
			System.out.println("intMessageTypeNodeVal tag is containing value :"+flag3);
			publishResults(Component.verifyTrue(intMessageTypeNodeVal!=null, "intMessageTypeNodeVal tag containing value"),(intMessageTypeNodeVal!=null)?"IntMessageType tag is containing value":"IntMessageType tag is null","IntMessageType tag is containing value","IntMessageType Occurence validation");
		}
		
		//MsgSource
			public void getCoreSubTagsMsgSrcOccuranceValidation(static String coreChildNode,static String occurs,static String bdateNodeVal){
				System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				boolean flag5 = Component.verifyTrue(messageSourceNodeVal!=null, "messageSourceNodeVal tag containing value");
			System.out.println("messageSourceNodeVal tag is containing value :"+flag5);
		publishResults(Component.verifyTrue(messageSourceNodeVal!=null, "messageSourceNodeVal tag containing value"),(messageSourceNodeVal!=null)?"MessageSource tag is containing value":"MessageSource tag is null","MessageSource tag is containing value","MessageSource Occurence validation");
			}
		
		//MsgDestimation
			public void getCoreSubTagsMsgDestOccuranceValidation(static String coreChildNode,static String occurs,static String bdateNodeVal){
				System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				boolean flag6 = Component.verifyTrue(messageDestinationNodeVal!=null, "messageDestinationNodeVal tag containing value");
			System.out.println("messageDestinationNodeVal tag is containing value :"+flag6);
			publishResults(Component.verifyTrue(messageDestinationNodeVal!=null, "messageDestinationNodeVal tag containing value"),(messageDestinationNodeVal!=null)?"messageDestination tag is containing value":"messageDestination tag is null","messageDestination tag is containing value","messageDestination Occurence validation");
			}
			
		//Record Counts
			public void getCoreSubTagsRecordCntsOccuranceValidation(static String coreChildNode,static String occurs,static String bdateNodeVal){
				System.out.println("*****As per IDS version 8, ChildCoreNodes Occurance for "+coreChildNode+" in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, ChildCoreNodes Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				boolean flag7 = Component.verifyTrue(recordCountsNodeVal!=null, "recordCountsNodeVal tag containing value");
			System.out.println("recordCountsNodeVal tag is containing value :"+flag7);
			publishResults(Component.verifyTrue(recordCountsNodeVal!=null, "recordCountsNodeVal tag containing value"),(recordCountsNodeVal!=null)?"recordCounts tag is containing value":"recordCounts tag is null","recordCounts tag is containing value","recordCounts Occurence validation");
			}
		
		
		
		public void getCoreSubTagExtractIdLenghtValidation(static String coreChildNode,static String length,static String businessDateNodeVal){
			System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);	
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
			int extractIdLength=Integer.parseInt(length);
			boolean assertFlagCheck = FREDXMLValidation.validateExtractIdWithIDS(extractIDNodeVal,extractIdLength);
				 boolean bflag = Component.verifyTrue(assertFlagCheck, "ExtractId length validation performed.");
				 if(bflag){
					 System.out.println("ExtractId length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("ExtractId value lenght is not as per IDS v8. ");
				 }
				 publishResults(bflag,(extractIdLength==26)?"ExtractID length "+extractIdLength+" in ICN is as per IDS v8":"ExtractID length "+extractIdLength+" in ICN validation got failed as per IDS v8","ExtractID length "+extractIdLength+" in ICN is as per IDS v8","ExtractID Occurence validation");
		}
			
				 //PP
		public void getCoreSubTagPPartcipantIdLenghtValidation(static String coreChildNode,static String length,static String businessDateNodeVal){
			System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
			boolean ppflag = Component.verifyTrue(FREDXMLValidation.validateProcessingParticipantId(processingParticipantIdNodeVal), "ProcessingParticipantId length validation performed.");
				 if(ppflag){
					 System.out.println("ProcessingParticipantId length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("ProcessingParticipantId value lenght is not as per IDS v8. ");
				 }
				 publishResults(ppflag,(processingParticipantIdNodeVal.length()==Integer.parseInt(length.trim()))?"ProcessingParticipantId value length "+processingParticipantIdNodeVal.length()+" in ICN is as per IDS v8":"ProcessingParticipantId length "+processingParticipantIdNodeVal.length()+" in ICN validation got failed as per IDS v8","ProcessingParticipantId length "+processingParticipantIdNodeVal.length()+" in ICN is as per IDS v8","ProcessingParticipantId Length validation");
				 
		}	 
				 //ExtMsgType
		public void getCoreSubTagExtMsgTypeLenghtValidation(static String coreChildNode,static String length,static String businessDateNodeVal){
			System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
			validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
			boolean flagExtMsgType = Component.verifyTrue(FREDXMLValidation.validateExtMessageType(extMessageTypeNodeVal), "ExtMessageType length validation performed.");
				 if(flagExtMsgType){
					 System.out.println("ExtMessageType length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("ExtMessageType value lenght is not as per IDS v8. ");
				 }
				 publishResults(flagExtMsgType,((extMessageTypeNodeVal.length())<=Integer.parseInt(length))?"ExtMessageTypeNodeVal value length "+extMessageTypeNodeVal.length()+" in ICN is as per IDS v8":"extMessageTypeNodeVal length "+extMessageTypeNodeVal.length()+" in ICN validation got failed as per IDS v8","ExtMessageTypeNodeVal length "+extMessageTypeNodeVal.length()+" in ICN is as per IDS v8","ExtMessageTypeNodeVal Length validation");
		}
				 //IntMsgType
				 public void getCoreSubTagIntMsgTypeLenghtValidation(static String coreChildNode,static String length,static String businessDateNodeVal){
					 System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
					 validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
					 boolean flagIntMsgType = Component.verifyTrue(FREDXMLValidation.validateIntMessageType(intMessageTypeNodeVal), "IntMessageType length validation performed.");
				 if(flagIntMsgType){
					 System.out.println("IntMessageType length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("IntMessageType value lenght is not as per IDS v8. ");
				 }
				 publishResults(flagIntMsgType,((intMessageTypeNodeVal.length())<=Integer.parseInt(length))?"IntMessageTypeNodeVal value length "+intMessageTypeNodeVal.length()+" in ICN is as per IDS v8":"intMessageTypeNodeVal length "+messageSourceNodeVal.length()+" in ICN validation got failed as per IDS v8","IntMessageTypeNodeVal length "+intMessageTypeNodeVal.length()+" in ICN is as per IDS v8","IntMessageTypeNodeVal Length validation");
				 }
				 //MsgSrc
				 public void getCoreSubTagMsgSrcLenghtValidation(static String coreChildNode,static String length,static String businessDateNodeVal){
					 System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
					 validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
					 boolean flagMsgSrc = Component.verifyTrue(FREDXMLValidation.validateMessageSource(messageSourceNodeVal), "MessageSource length validation performed.");
				 if(flagMsgSrc){
					 System.out.println("MsgSource Validation length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("MsgSource value lenght is not as per IDS v8. ");
				 }
				 publishResults(flagMsgSrc,((messageSourceNodeVal.length())<=Integer.parseInt(length))?"MessageSource value length "+messageSourceNodeVal.length()+" in ICN is as per IDS v8":"MessageSource length "+messageSourceNodeVal.length()+" in ICN validation got failed as per IDS v8","MessageSource length "+messageSourceNodeVal.length()+" in ICN is as per IDS v8","MessageSource Length validation");
				 }
				 
				 //msgDest
				 public void getCoreSubTagMsgDestLenghtValidation(static String coreChildNode,static String length,static String businessDateNodeVal){
					 System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
					 validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
					 boolean flagMsgDest = Component.verifyTrue(FREDXMLValidation.validateMessageDestination(messageDestinationNodeVal), "MessageDestination length validation performed.");
				 if(flagMsgDest){
					 System.out.println("MsgDestination length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("MsgDestination value lenght is not as per IDS v8. ");
				 }
				 publishResults(flagMsgDest,((messageDestinationNodeVal.length())<=Integer.parseInt(length))?"MessageDestination value length "+messageDestinationNodeVal.length()+" in ICN is as per IDS v8":"MessageDestination length "+messageDestinationNodeVal.length()+" in ICN validation got failed as per IDS v8","MessageDestination length "+messageDestinationNodeVal.length()+" in ICN is as per IDS v8","MessageDestination Length validation");
				 }
				
				 //RecordCounts
				 public void getCoreSubTagRecordCntsLenghtValidation(static String coreChildNode,static String length,static String businessDateNodeVal){
					 System.out.println("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
					 validationStepInformation("*****As per IDS version 8, ChildCoreNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
					 boolean rcLength =FREDXMLValidation.validateRecordCountsLength(recordCountsNodeVal);
				 boolean flagRC =Component.verifyTrue(rcLength,"RecordCounts length validation as per IDS v8"); 
						// Component.verifyTrue(FREDXMLValidation.validateRecordCountsLength(recordCountsNodeVal), "RecordCounts length validation performed.");
				// publishResults(flagRC,static String.valueOf(rcLength),length,"RecordCounts length validation as per IDS v8");
				 if(flagRC){
					 System.out.println("RecordCounts length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("RecordCounts value lenght is not as per IDS v8.");
				 }
				 publishResults(flagRC,(recordCountsNodeVal!=null)?"RecordCounts Length in ICN file is"+length+"as per IDS v8":"RecordCounts length should not be 0 and gretaer than 8","RecordCounts Length in ICN file is"+length+"as per IDS v8","RecordCounts length validation as per IDS v8"); 
				}
				
*/	
		
		

}

