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

public class ValidateICNCaptureItemJobBatchBlockTagWithIDS  extends GenericMethodUtilis{

		List<String> listOfMandatory = new ArrayList<String>();
		/*static String excelFilePath ="\\\\129.227.33.35\\f$\\AutomationTestData\\Input\\FRED_Regression_Input_Data.xlsx";
		static String ICNFilepath="\\\\129.227.33.35\\f$\\AutomationTestData\\output\\";
		static String ICNFileName="ICNOutput.xml";
		*/
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
		static String batchNodeVal,tktAuxNodeVal,tktAnNodeVal,tktSCNodeVal,tktTcNodeVal,batchDebitAmountNodeVal,batchCreditAmountNodeVal,batchDebitCountNodeVal,batchCreditCountNodeVal;

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
	
		public static void validateCaptureInfoTags(String excelFilePath,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub

			//icnCoreBusinessDate;
			FileInputStream inputStream = new FileInputStream(excelFilePath);
			
			Workbook wrkBk = new XSSFWorkbook(inputStream);
			int index = wrkBk.getSheetIndex("CaptureInfoJobBlockBatch");
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
							tag.setOccurs(cell.getRichStringCellValue().toString());
							count++;
							break;
						case 6:
							tag.setFixedVar(cell.getRichStringCellValue().toString());
							count++;
							break;
						case 7:
							tag.setType(cell.getRichStringCellValue().toString());
						//	int type = (int)cell.getNumericCellValue();
						//	tag.setType(Integer.toString(type));
							count++;
							break;
						case 8:
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
					getRootNodeValidation(rootNode,icnFilepath,icnFileName); 
					if(null!=captureChildNote){
						System.out.println("captureChildNote "+captureChildNote);
							getCaptureInfoChildNodeValidation(captureChildNote,icnFilepath,icnFileName);
							if(null!=captureChildNote1){
								switch(captureChildNote1){
									case "JOB":
									jobNodeVal=validateICNCaptureItemJOBTag(captureChildNote1,icnFilepath,icnFileName);
									//getJobSubTagsOccuranceValidation(captureChildNote1,occurs,businessDateNodeVal);
									System.out.println("*****As per IDS version 8, ChildCoreNode BusinessDate DataType in ICN is :"+type);
									System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);
									System.out.println("Lenght validation is not required for BusinessDate in IDS v8 "+length);
								//	getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal);
									switch(captureChildNote2){
										case "BusinessDate":
											jobBusinessDateNodeVal=getJOBBuisnessDateValidationTag(captureChildNote2,icnFilepath,icnFileName);
											getJobBusinessDateTagOccuranceValidation(captureChildNote2,occurs,jobBusinessDateNodeVal);
											
										//	validateJobInstallationIdTagLength(captureChildNote2,length,jobBusinessDateNodeVal);
											break;
										case "InstallationId":
											installationIdNodeVal=getJOBInstallationIdValidationTag(captureChildNote2,icnFilepath,icnFileName);
											getJobInstallationTagOccuranceValidation(captureChildNote2,occurs,instlltionIdNodeVal);
										//	validationStepInformation(installationIdNodeVal);
											validateJobInstallationIdTagLength(captureChildNote2,length,installationIdNodeVal);
											break;
										case "CaptureSystemId":
											captureSystemIdNodeVal=getJOBCaptureSystemIdValidationTag(captureChildNote2,icnFilepath,icnFileName);
											validateJobCaptureSystemIdTagOccurance(captureChildNote2,occurs,captureSystemIdNodeVal);
											validateJobCaptureSystemIdTagLength(captureChildNote2,length,captureSystemIdNodeVal);
											break;
										case "StartTime":
											startTimeNodeVal=getJOBStartTimeTagVal(captureChildNote2,icnFilepath,icnFileName);
											validateJobStartTimeTagOccurance(captureChildNote2,occurs,startTimeNodeVal);
											//getJobInstallationIdLenghtValidation(captureChildNote1,length,installationIdNodeVal);
											break;
										case "EndTime":
											endTimeNodeVal=getJOBEndTimeNodeVal(captureChildNote2,icnFilepath,icnFileName);
											validateJobEndTimeTagOccurance(captureChildNote2,occurs,endTimeNodeVal);
											//getJobInstallationIdLenghtValidation(captureChildNote1,length,installationIdNodeVal);
											break;
										case "WorkTypeNbr":
											workTypeNbrNodeVal=getJOBWrkTypeNodeVal(captureChildNote2,icnFilepath,icnFileName);
											validateJobWorkTypeNbrTagOccurance(captureChildNote2,occurs,workTypeNbrNodeVal);
											validateJobWorkTypeNbrTagLength(captureChildNote2,length,workTypeNbrNodeVal);
											break;
										case "SortFamily":
											sortFamilyNodeVal=getJOBSortFamilyNodeVal(captureChildNote2,icnFilepath,icnFileName);
											validateJobSortFamilyTagOccurance(captureChildNote2,occurs,sortFamilyNodeVal);
											validateJobSortFamilyTagLength(captureChildNote2,length,sortFamilyNodeVal);
											break;
										case "SourceType":
											sourceTypeNodeVal=getJOBSrcTypeNodeVal(captureChildNote2,icnFilepath,icnFileName);
											validateJobSourceTypeTagOccurance(captureChildNote2,occurs,sourceTypeNodeVal);
											validateJobSourceTypeTagLength(captureChildNote2,length,sourceTypeNodeVal);
											break;
										case "SourceName":
											sourceNameNodeVal=getJOBSrcNameNodeVal(captureChildNote2,icnFilepath,icnFileName);
											validateJobSourceNameTagOccurance(captureChildNote2,occurs,sourceNameNodeVal);
											validateJobSourceNameTagLength(captureChildNote2,length,sourceNameNodeVal);
											break;
										case "SourceID":
											sourceIDNodeVal=getJOBSrcIdNodeVal(captureChildNote2,icnFilepath,icnFileName);
											validateJobSrcIdTagOccurance(captureChildNote2,occurs,sourceIDNodeVal);
											validateJobSrcIdTagLength(captureChildNote2,length,sourceIDNodeVal);
											break;
										case "FinancialInstitutionID":
											financialInstitutionIDNodeVal=getJOBFinancialInstitutionId(captureChildNote2,icnFilepath,icnFileName);
											validateJobFinancialInstitutionIDTagOccurance(captureChildNote2,occurs,financialInstitutionIDNodeVal);
											validateJobFinancialInstitutionIDTagLength(captureChildNote2,length,financialInstitutionIDNodeVal);
											break;
										case "CollectionStartTime":
											collectionStartTimeNodeVal=getJOBCollectionStartTimeNodeVal(captureChildNote2,icnFilepath,icnFileName);
											validateJobCollectionStartTimeTagOccurance(captureChildNote2,occurs,collectionStartTimeNodeVal);
											break;
										case "CollectionEndTime":
											collectionEndTimeNodeVal=getJOBCollectionEndTimeNodeVal(captureChildNote2,icnFilepath,icnFileName);
											validateJobCollectionEndTimeTagOccurance(captureChildNote2,occurs,collectionEndTimeNodeVal);
											break;
										
									}
									
									break;
									case "BLOCK":
										blockNodeVal=getCaptureItemBlockTagValidation(captureChildNote1,icnFilepath,icnFileName);
										getCaptureItemBlockOccuranceValidation(captureChildNote1,occurs,blockNodeVal);
										//getBlockSubTagLenghtValidation(captureChildNote1,length,businessDateNodeVal);
										switch(captureChildNote2){
											case "BlkNbr":
													blkNbrNodeVal=validateCaptureItemBlockNbr(captureChildNote2,icnFilepath,icnFileName);
													getCaptureItemBlockNbrOccuranceValidation(captureChildNote2,occurs,blkNbrNodeVal);
													validateBlockNbrTagLength(captureChildNote2,length,blkNbrNodeVal);
													break;
											case "ImageType":
													imageTypeNodeVal=validateCaptureItemBlockImageType(captureChildNote2,icnFilepath,icnFileName);
													getCaptureItemBlockImageTypeOccuranceValidation(captureChildNote2,occurs,imageTypeNodeVal);
													validateBlockImageTypeTagLength(captureChildNote2,length,imageTypeNodeVal);
													//getBlockSubTagTypeValidation(captureChildNote1,length,businessDateNodeVal);
													break;
											
										}
						
								break;
										
									case "BATCH":
										batchNodeVal=validateICNCaptureItemBATCHTag(captureChildNote1,icnFilepath,icnFileName);
										getBatchSubTagsOccuranceValidation(captureChildNote1,occurs,batchNodeVal);
										System.out.println("*****As per IDS version 8, ChildCoreNode BusinessDate DataType in ICN is :"+type);
										System.out.println("Fixed or Variable cell is not required for BusinessDate in IDS v8 "+fixedVar);
										System.out.println("Lenght validation is not required for BusinessDate in IDS v8 "+length);
									//	getCoreSubTagLenghtValidation(coreChildNode,length,businessDateNodeVal);
										switch(captureChildNote2){
											case "TktTc":
												tktTcNodeVal=validateBatchTktTCTagInICN(captureChildNote2,icnFilepath,icnFileName);
												getBatchTktTCTagOccuranceValidation(captureChildNote2,occurs,tktTcNodeVal);
												validateBatchTktTcTagLength(captureChildNote2,length,tktTcNodeVal);
												break;
											case "TktAn":
												tktAnNodeVal=validateBatchTktAnTagInICN(captureChildNote2,icnFilepath,icnFileName);
												getBatchTktAnTagOccuranceValidation(captureChildNote2,occurs,tktAnNodeVal);
												validateBatchTktAnTagLength(captureChildNote2,length,tktAnNodeVal);
												break;
											case "TktSC":
												tktSCNodeVal=validateBatchTktSCTagInICN(captureChildNote2,icnFilepath,icnFileName);
												getBatchTktSCTagOccuranceValidation(captureChildNote2,occurs,tktSCNodeVal);
												validateBatchTktSCTagLength(captureChildNote2,length,tktSCNodeVal);
												break;
											case "TktAux":
												tktAuxNodeVal=validateBatchTktAuxTagInICN(captureChildNote2,icnFilepath,icnFileName);
												getBatchTktAuxTagOccuranceValidation(captureChildNote2,occurs,tktAuxNodeVal);
												validateBatchTktAuxTagLength(captureChildNote2,length,tktAuxNodeVal);
												break;
											case "BatchCreditCount":
												batchCreditCountNodeVal=validateBatchCreditCntTagInICN(captureChildNote2,icnFilepath,icnFileName);
												getBatchCreditCntTagOccuranceValidation(captureChildNote2,occurs,batchCreditCountNodeVal);
												validateBatchCreditCntTagLength(captureChildNote2,length,batchCreditCountNodeVal);
												break;
											case "BatchDebitCount":
												batchDebitCountNodeVal=validateBatchDebitCntTagInICN(captureChildNote2,icnFilepath,icnFileName);
												getBatchDebitCntTagOccuranceValidation(captureChildNote2,occurs,batchDebitCountNodeVal);
												validateBatchDebitCntTagLength(captureChildNote2,length,batchDebitCountNodeVal);
												break;
											case "BatchCreditAmount":
												batchCreditAmountNodeVal=validateBatchCreditAmtTagInICN(captureChildNote2,icnFilepath,icnFileName);
												getBatchCreditAmtTagOccuranceValidation(captureChildNote2,occurs,batchCreditAmountNodeVal);
												validateBatchCreditAmtTagLength(captureChildNote2,length,batchCreditAmountNodeVal);
												break;
											case "BatchDebitAmount":
												batchDebitAmountNodeVal=validateBatchDbtAmtTagInICN(captureChildNote2,icnFilepath,icnFileName);
												getBatchDbtAmtTagOccuranceValidation(captureChildNote2,occurs,batchDebitAmountNodeVal);
												validateBatchDebitAmtTagLength(captureChildNote2,length,batchDebitAmountNodeVal);
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
		
		
		public static String getJOBBuisnessDateValidationTag(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
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
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBBusinessDate);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBBusinessDate);
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
		
		public static String getJOBInstallationIdValidationTag(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
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
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBInstallationId);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::InstallationId KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBInstallationId);
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
		
		public static String getJOBCaptureSystemIdValidationTag(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating InstallationId is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBCaptureSystemId);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::InstallationId KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBCaptureSystemId);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"CaptureSystemId").get(0);
			System.out.println("InstallationId :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "InstallationId tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("CaptureSystemId Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("CaptureSystemId Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "CaptureSystemId tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBStartTimeTagVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating InstallationId is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBStartTime);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::InstallationId KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBStartTime);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"StartTime").get(0);
			System.out.println("InstallationId :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "InstallationId tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("StartTime Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("StartTime Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "StartTime tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBEndTimeNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating InstallationId is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBEndTime);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::EndTime KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBEndTime);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"EndTime").get(0);
			System.out.println("InstallationId :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "EndTime tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("EndTime Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("EndTime Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "EndTime tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBWrkTypeNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating WorkTypeNbr is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBWorkTypeNbr);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::EndTime KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBWorkTypeNbr);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"WorkTypeNbr").get(0);
			System.out.println("WorkTypeNbr :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "EndTime tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("WorkTypeNbr Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("WorkTypeNbr Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "WorkTypeNbr tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBSrcTypeNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating WorkTypeNbr is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBSourceType);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::EndTime KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBSourceType);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"SourceType").get(0);
			System.out.println("WorkTypeNbr :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "EndTime tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("WorkTypeNbr Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("WorkTypeNbr Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "SourceType tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBSortFamilyNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating WorkTypeNbr is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBSortFamily);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::EndTime KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBSortFamily);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"SortFamily").get(0);
			System.out.println("WorkTypeNbr :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "EndTime tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("WorkTypeNbr Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("WorkTypeNbr Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "SortFamily tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBSrcNameNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating WorkTypeNbr is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBSourceName);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::EndTime KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBSourceName);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"SourceName").get(0);
			System.out.println("SourceName :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "EndTime tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("WorkTypeNbr Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("WorkTypeNbr Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "SourceName tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBSrcIdNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating WorkTypeNbr is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBSourceID);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::EndTime KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBSourceID);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"SourceID").get(0);
			System.out.println("WorkTypeNbr :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "EndTime tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("WorkTypeNbr Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("WorkTypeNbr Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "SourceId tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBFinancialInstitutionId(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating WorkTypeNbr is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBFinancialInstitutionID);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::EndTime KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBFinancialInstitutionID);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"FinancialInstitutionID").get(0);
			System.out.println("WorkTypeNbr :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "EndTime tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("WorkTypeNbr Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("WorkTypeNbr Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "FinancialInstitutionId tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBCollectionStartTimeNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating WorkTypeNbr is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBCollectionStartTime);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::EndTime KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBCollectionStartTime);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"CollectionStartTime").get(0);
			System.out.println("WorkTypeNbr :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "EndTime tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("CollectionStartTime Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("CollectionStartTime Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "CollectionStartTime tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String getJOBCollectionEndTimeNodeVal(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating CollectionEndTime is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem is populating under JOB in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBCollectionEndTime);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::EndTime KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemJOBCollectionEndTime);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"CollectionEndTime").get(0);
			System.out.println("WorkTypeNbr :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "CollectionEndTime tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("CollectionEndTime Tag is present under JOB Tag in ICN file as per IDS v8 :");
			else
				System.out.println("CollectionEndTime Tag is not present under JOB Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "CollectionEndTime tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		//BLOCK Validation
		
		public static String getCaptureItemBlockTagValidation(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
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
			Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBLOCKXpath);
			 String dateKey =getMapKey(dateKeyVal);
			System.out.println("Block Tag Key:"+dateKey+"::Block Tag KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBLOCKXpath);
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
		
		public static String validateCaptureItemBlockNbr(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
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
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBLOCKBlkNbr);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("InstallationId Key:"+dateKey+"::InstallationId KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBLOCKBlkNbr);
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
		
		public static String validateCaptureItemBlockImageType(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BLOCK ImageType is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BLOCK ImageType is populating under BLOCK in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBLOCKImageType);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("Block ImageType Key:"+dateKey+"::Block ImageType KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBLOCKImageType);
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
		
		//BATCH Validation
		
		public static String validateICNCaptureItemBATCHTag(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BATCH Tag is populating under CaptureItem in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BATCH is populating in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String,String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHXpath);
			 String dateKey =getMapKey(dateKeyVal);
			System.out.println("Batch Tag Key:"+dateKey+"::Batch Tag KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHXpath);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"BATCH").get(0);
			System.out.println("Batch :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "Batch tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("Batch Tag is present under CaptureItem Tag in ICN file as per IDS v8 :");
			else
				System.out.println("Batch Tag is not present under CaptureItem  Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "Batch tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String validateBatchTktTCTagInICN(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BATCH TktTC is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BATCH TktTC is populating under BATCH in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHTktTc);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("Batch TktTc Key:"+dateKey+"::Batch TktTc KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHTktTc);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"TktTc").get(0);
			System.out.println("TktTc :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "TktTc tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("TktTc Tag is present under BATCH Tag in ICN file as per IDS v8 :");
			else
				System.out.println("TktTc Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "TktTc tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String validateBatchTktAnTagInICN(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BATCH TktAn is populating in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BATCH TktAn is populating under BATCH in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHTktAn);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("Batch TktAn Key:"+dateKey+"::Batch TktAn KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHTktAn);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"TktAn").get(0);
			System.out.println("TktAn :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "TktAn tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("TktAn Tag is present under BATCH Tag in ICN file as per IDS v8 :");
			else
				System.out.println("TktAn Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "TktAn tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String validateBatchTktSCTagInICN(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BATCH TktSC is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BATCH TktSC is populating under BATCH in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHTktSC);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("Batch TktSC Key:"+dateKey+"::Batch TktSC KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHTktSC);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"TktSC").get(0);
			System.out.println("TktSC :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "TktSC tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("TktSC Tag is present under BATCH Tag in ICN file as per IDS v8 :");
			else
				System.out.println("TktSC Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "TktTc tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String validateBatchTktAuxTagInICN(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BATCH TktAux is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BATCH TktAux is populating under BATCH in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHTktAux);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("Batch TktAux Key:"+dateKey+"::Batch TktTc KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHTktAux);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"TktAux").get(0);
			System.out.println("TktAux :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "TktAux tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("TktAux Tag is present under BATCH Tag in ICN file as per IDS v8 :");
			else
				System.out.println("TktAux Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "TktAux tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String validateBatchCreditCntTagInICN(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BatchCreditCount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BatchCreditCount is populating under BATCH in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHBatchCreditCount);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("BatchCreditCount Key:"+dateKey+"::BatchCreditCount KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHBatchCreditCount);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"BatchCreditCount").get(0);
			System.out.println("BatchCreditCount :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "BatchCreditCount tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("BatchCreditCount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
			else
				System.out.println("BatchCreditCount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "BatchCreditCount tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String validateBatchDebitCntTagInICN(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BatchDebitCount is populating under JOB in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BatchDebitCount is populating under BATCH in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHBatchDebitCount);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("BatchDebitCount Key:"+dateKey+"::BatchDebitCount KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHBatchDebitCount);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"BatchDebitCount").get(0);
			System.out.println("BatchDebitCount :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "BatchDebitCount tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("BatchDebitCount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
			else
				System.out.println("BatchDebitCount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "BatchDebitCount tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String validateBatchCreditAmtTagInICN(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BatchCreditAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BatchCreditAmount is populating under BATCH in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHBatchCreditAmount);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("BatchCreditAmount Key:"+dateKey+"::BatchCreditAmount KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHBatchCreditAmount);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"BatchCreditAmount").get(0);
			System.out.println("BatchCreditAmount :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "BatchCreditAmount tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("BatchCreditAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
			else
				System.out.println("BatchCreditAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "BatchCreditAmount tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		public static String validateBatchDbtAmtTagInICN(String coreChildNode,String icnFilepath,String icnFileName) throws Exception {
			// TODO Auto-generated method stub
		//	excelReader();
			String bdateNodeVal=null;
			System.out.println("****************************************************************");
			System.out.println("Validating BatchDebitAmount is populating under BATCH in ICN as per IDS version 8 :"+coreChildNode);
			System.out.println("****************************************************************");
			
			validationStepInformation("*********************************************************************************************************************");
			validationStepInformation("Validating "+ coreChildNode +" CaptureItem BATCH DebitAmount is populating under BATCH in ICN as per IDS version 8 ");
			validationStepInformation("*********************************************************************************************************************");
			
			//now read xml and compare here
			//ValidateE031ICNCoreSubTags.getBusinessDateICNValidation();
			//BusinessDate Validation 
			Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHBatchDebitAmount);
			String dateKey =getMapKey(dateKeyVal);
			System.out.println("Batch DebitAmount Key:"+dateKey+"::Batch DebitAmount KeyValue Pair:"+dateKeyVal);
			Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemBATCHBatchDebitAmount);
			bdateNodeVal =getNodeListValues(dateKeyVal1,"BatchDebitAmount").get(0);
			System.out.println("DebitAmount :"+bdateNodeVal);
			boolean flagb =Component.verifyEquals(dateKey, coreChildNode, "DebitAmount tagname is as per IDSv8");
			if(flagb==true)
				System.out.println("DebitAmount Tag is present under BATCH Tag in ICN file as per IDS v8 :");
			else
				System.out.println("DebitAmount Tag is not present under BATCH Tag in ICN file as per IDS v8 :");
			
			publishResults(flagb,dateKey, coreChildNode, "BatchDebitAmount tagname is as per IDSv8");
			return bdateNodeVal;
		}
		
		//Occurence Validation
		public static void getBatchSubTagsOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
				
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
		
		//Job
		public static void validateJobCaptureSystemIdTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

		}
		public static void validateJobStartTimeTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

		}
		public static void validateJobEndTimeTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
			System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

		}
	public static void validateJobWorkTypeNbrTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateJobSortFamilyTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateJobSourceTypeTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateJobSourceNameTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateJobSrcIdTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateJobFinancialInstitutionIDTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateJobCollectionStartTimeTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
	
	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
public static void validateJobCollectionEndTimeTagOccurance(String coreChildNode,String occurs,String batchNodeVal) throws Exception {

	System.out.println("*****CaptureItem JOB Occurance in ICN file validation starts here*********");
	validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
	System.out.println("*****As per IDS version 8, CaptureItem JOB Occurance for "+coreChildNode+" in ICN is :"+occurs);
	validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
	publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");

}
		
		public static void getBatchTktTCTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void getBatchTktAnTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for "+coreChildNode+" in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void getBatchTktSCTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchTktSC in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void getBatchTktAuxTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchTktSC in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":coreChildNode+" tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void getBatchCreditCntTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchCreditCount in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchCreditCount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		
		public static void getBatchDebitCntTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitCount in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitCount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		
		public static void getBatchCreditAmtTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildCoreNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchCreditAmount in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchCreditAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
		public static void getBatchDbtAmtTagOccuranceValidation(String coreChildNode,String occurs,String batchNodeVal) throws Exception {
			
			System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
			validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
			System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
			validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
			publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
		}
		
			
		// BatchDebitAmount
			public static void validateBatchDebitAmtTagLength(String coreChildNode,String length,String bdateNodeVal){
				 System.out.println("*****As per IDS version 8, Batch ChildNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, Batch ChildNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
				 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"BatchDebitAmount length validation performed.");
				 if(flag){
					 System.out.println("BatchDebitAmount Validation length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("BatchDebitAmount value lenght is not as per IDS v8. ");
				 }
				 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"BatchDebitAmount value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"BatchDebitAmount length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","BatchDebitAmount length "+bdateNodeVal.length()+" in ICN is as per IDS v8","BatchDebitAmount Length validation");
			}
		
			public static void validateBatchCreditAmtTagLength(String coreChildNode,String length,String bdateNodeVal){
				 System.out.println("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
				 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"BatchCreditAmount length validation performed.");
				 if(flag){
					 System.out.println("BatchCreditAmount Validation length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("BatchCreditAmount value lenght is not as per IDS v8. ");
				 }
				 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"BatchCreditAmount value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"BatchCreditAmount length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","BatchCreditAmount length "+bdateNodeVal.length()+" in ICN is as per IDS v8","BatchCreditAmount Length validation");
			}
			
			public static void validateBatchDebitCntTagLength(String coreChildNode,String length,String bdateNodeVal){
				 System.out.println("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
				 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"BatchDebitCount length validation performed.");
				 if(flag){
					 System.out.println("BatchDebitCount Validation length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("BatchDebitCount value lenght is not as per IDS v8. ");
				 }
				 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"BatchDebitCount value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"BatchDebitCount length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","BatchDebitCount length "+bdateNodeVal.length()+" in ICN is as per IDS v8","BatchDebitCount Length validation");
			}
			
			public static void validateBatchCreditCntTagLength(String coreChildNode,String length,String bdateNodeVal){
				 System.out.println("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
				 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"BatchCreditCount length validation performed.");
				 if(flag){
					 System.out.println("BatchCreditCount Validation length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("BatchCreditCount value lenght is not as per IDS v8. ");
				 }
				 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"BatchCreditCount value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"BatchCreditCount length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","BatchCreditCount length "+bdateNodeVal.length()+" in ICN is as per IDS v8","BatchCreditCount Length validation");
			}
			
			public static void validateBatchTktAuxTagLength(String coreChildNode,String length,String bdateNodeVal){
				 System.out.println("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
				 validationStepInformation("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
				 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"BatchTktAux length validation performed.");
				 if(flag){
					 System.out.println("TktAux Validation length correct as per IDS v8 :");
				 }
				 else
				 {
					 System.out.println("TktAux value lenght is not as per IDS v8. ");
				 }
				 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"BatchTktAux value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"BatchTktAux length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","BatchTktAux length "+bdateNodeVal.length()+" in ICN is as per IDS v8","BatchTktAux Length validation");
			}

public static void validateBatchTktSCTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"TktAux length validation performed.");
	 if(flag){
		 System.out.println("TktSC Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("TktSC value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"BatchTktSC value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"BatchTktSC length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","BatchTktSC length "+bdateNodeVal.length()+" in ICN is as per IDS v8","BatchTktSC Length validation");
}

public static void validateBatchTktAnTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"BatchCreditAmount length validation performed.");
	 if(flag){
		 System.out.println("TktAn Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("TktAn value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"BatchTktAn value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"BatchTktAn length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","BatchTktAn length "+bdateNodeVal.length()+" in ICN is as per IDS v8","BatchTktAn Length validation");
}

public static void validateBatchTktTcTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, Batch Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"TktTc length validation performed.");
	 if(flag){
		 System.out.println("TktTc Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("TktTc value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"BatchTktTc value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"BatchTktTc length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","BatchTktTc length "+bdateNodeVal.length()+" in ICN is as per IDS v8","BatchTktTc Length validation");
}

public static void validateBlockImageTypeTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"ImageType length validation performed.");
	 if(flag){
		 System.out.println("ImageType Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("ImageType value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"ImageType value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"BatchDebitAmount length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","BatchCreditCount length "+bdateNodeVal.length()+" in ICN is as per IDS v8","BatchCreditCount Length validation");
}

public static void validateBlockNbrTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"BlkNbr length validation performed.");
	 if(flag){
		 System.out.println("BlkNbr Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("BlkNbr value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"BlkNbr value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"BatchDebitAmount length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","BatchCreditCount length "+bdateNodeVal.length()+" in ICN is as per IDS v8","BatchCreditCount Length validation");
}

public static void validateJobFinancialInstitutionIDTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, ChildItemsNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, ChildItemsNodes Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"FinancialInstitutionID length validation performed.");
	 if(flag){
		 System.out.println("FinancialInstitutionID Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("FinancialInstitutionID value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"FinancialInstitutionID value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"FinancialInstitutionID length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","FinancialInstitutionID length "+bdateNodeVal.length()+" in ICN is as per IDS v8","FinancialInstitutionID Length validation");
}

public static void validateJobSourceTypeTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"SourceType length validation performed.");
	 if(flag){
		 System.out.println("SourceType Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("SourceType value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"Job SourceType value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"Job SourceType length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","Job SourceType length "+bdateNodeVal.length()+" in ICN is as per IDS v8","Job SourceType Length validation");
}

public static void validateJobSortFamilyTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"SortFamily length validation performed.");
	 if(flag){
		 System.out.println("SortFamily Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("SortFamily value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"Job SortFamily value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"Job SortFamily length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","Job SortFamily length "+bdateNodeVal.length()+" in ICN is as per IDS v8","Job SortFamily Length validation");
}

public static void validateJobWorkTypeNbrTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"WorkTypeNbr length validation performed.");
	 if(flag){
		 System.out.println("WorkTypeNbr Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("WorkTypeNbr value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"Job WorkTypeNbr value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"Job WorkTypeNbr length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","Job WorkTypeNbr length "+bdateNodeVal.length()+" in ICN is as per IDS v8","Job WorkTypeNbr Length validation");
}

public static void validateJobCaptureSystemIdTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateJobCaptureSystemIdWithIDS(bdateNodeVal,Integer.parseInt(length)),"CaptureSystemId length validation performed.");
	 if(flag){
		 System.out.println("CaptureSystemId Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("CaptureSystemId value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"JobCaptureSystemId value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"JobCaptureSystemId length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","JobCaptureSystemId length "+bdateNodeVal.length()+" in ICN is as per IDS v8","JobCaptureSystemId Length validation");
}

public static void validateJobInstallationIdTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, JOB Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, JOB Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"Job InstallationId length validation performed.");
	 if(flag){
		 System.out.println("InstallationId Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("InstallationId value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"Job InstallationId value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"Job InstallationId length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","Job InstallationId length "+bdateNodeVal.length()+" in ICN is as per IDS v8","Job InstallationId Length validation");
}

public static void validateJobSourceNameTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"SourceName length validation performed.");
	 if(flag){
		 System.out.println("SourceName Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("SourceName value lenght is not as per IDS v8. ");
	 }
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"SourceName value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"SourceName length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","SourceName length "+bdateNodeVal.length()+" in ICN is as per IDS v8","SourceName Length validation");
}


public static void validateJobSrcIdTagLength(String coreChildNode,String length,String bdateNodeVal){
	 System.out.println("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	 validationStepInformation("*****As per IDS version 8, Job Length Validation for "+coreChildNode+" in ICN is :"+length);
	/* boolean flag = Component.verifyTrue(FREDXMLValidation.validateBatchDebitAmtWithIDS(bdateNodeVal,Integer.parseInt(length)),"SourceId length validation performed.");
	 if(flag){
		 System.out.println("SourceId Validation length correct as per IDS v8 :");
	 }
	 else
	 {
		 System.out.println("SourceId value lenght is not as per IDS v8. ");
	 }*/
	 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?"SourceId value length "+bdateNodeVal.length()+" in ICN is as per IDS v8":"SourceId length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8","SourceId length "+bdateNodeVal.length()+" in ICN is as per IDS v8","SourceId Length validation");
}
}

