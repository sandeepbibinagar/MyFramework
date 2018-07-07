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

	public class ValidateICNCaptureItemCustomFieldsTagWithIDS  extends GenericMethodUtilis{

		List<String> listOfMandatory = new ArrayList<String>();
	
		//XPATH Declarations
		//CaptureInfoChildNode1
		
		static String icnCaptureInfoXpath ="//CaptureInfo";
		static String icnCaptureItemXpath ="//CaptureInfo/CaptureItem";


		static String icnCaptureItemImgHsh="//CaptureInfo/CaptureItem/ImageHash";
		static String icnCaptureItemSrcData="//CaptureInfo/CaptureItem/ItemSourceData";
		

		static String icnCaptureItemCustomFields ="//CaptureInfo/CaptureItem/CustomFields";
			
		//CaptureInfoChildNode2
	
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
				
				
		static String icnCaptureItemCustomFieldscfAccountType=	"//CaptureInfo/CaptureItem/CustomFields/cf_AccountType";
		static String icnCaptureItemCustomFieldscfANDefaulted=	"//CaptureInfo/CaptureItem/CustomFields/cf_ANDefaulted";
		static String icnCaptureItemCustomFieldscfBatch_SourceID=	"//CaptureInfo/CaptureItem/CustomFields/cf_Batch_SourceID";
		static String icnCaptureItemCustomFieldscfBrandID=	"//CaptureInfo/CaptureItem/CustomFields/cf_BrandID";
		static String icnCaptureItemCustomFieldscfCustomerName1=	"//CaptureInfo/CaptureItem/CustomFields/cf_CustomerName1";
		static String icnCaptureItemCustomFieldscfCustomerName2=	"//CaptureInfo/CaptureItem/CustomFields/cf_CustomerName2";
		static String icnCaptureItemCustomFieldscfCustomerName3=	"//CaptureInfo/CaptureItem/CustomFields/cf_CustomerName3";
		static String icnCaptureItemCustomFieldscfCustomerName4=	"//CaptureInfo/CaptureItem/CustomFields/cf_CustomerName4";
		static String icnCaptureItemCustomFieldscfCustomerName5=	"//CaptureInfo/CaptureItem/CustomFields/cf_CustomerName5";
		static String icnCaptureItemCustomFieldscfDate=	"//CaptureInfo/CaptureItem/CustomFields/cf_Date";
		static String icnCaptureItemCustomFieldscfDeferredPosting=	"//CaptureInfo/CaptureItem/CustomFields/cf_DeferredPosting";
		static String icnCaptureItemCustomFieldscfDeviceID=	"//CaptureInfo/CaptureItem/CustomFields/cf_DeviceID";
		static String icnCaptureItemCustomFieldscffCashItem=	"//CaptureInfo/CaptureItem/CustomFields/cf_fCashItem";
		static String icnCaptureItemCustomFieldscfICSTransactionID=	"//CaptureInfo/CaptureItem/CustomFields/cf_ICSTransactionID";
		static String icnCaptureItemCustomFieldscfImageDateTime=	"//CaptureInfo/CaptureItem/CustomFields/cf_ImageDateTime";
		static String icnCaptureItemCustomFieldscfIQVRejectReason=	"//CaptureInfo/CaptureItem/CustomFields/cf_IQVRejectReason";
		static String icnCaptureItemCustomFieldscfLAR=	"//CaptureInfo/CaptureItem/CustomFields/cf_LAR";
		static String icnCaptureItemCustomFieldscfLocationID=	"//CaptureInfo/CaptureItem/CustomFields/cf_LocationID";
		static String icnCaptureItemCustomFieldscfLockRollID=	"//CaptureInfo/CaptureItem/CustomFields/cf_LockRollID";
		static String icnCaptureItemCustomFieldscfMarketSector=	"//CaptureInfo/CaptureItem/CustomFields/cf_MarketSector";
		static String icnCaptureItemCustomFieldscfMSOUGroupID=	"//CaptureInfo/CaptureItem/CustomFields/cf_MSOUGroupID";
		static String icnCaptureItemCustomFieldscfMSOUGroupName=	"//CaptureInfo/CaptureItem/CustomFields/cf_MSOUGroupName";
		static String icnCaptureItemCustomFieldscfNoPaySuspectRsn=	"//CaptureInfo/CaptureItem/CustomFields/cf_NoPaySuspectRsn";
		static String icnCaptureItemCustomFieldscfNPASortCode=	"//CaptureInfo/CaptureItem/CustomFields/cf_NPASortCode";
		static String icnCaptureItemCustomFieldscfParticipantID=	"//CaptureInfo/CaptureItem/CustomFields/cf_ParticipantID";
		static String icnCaptureItemCustomFieldscfPersonID=	"//CaptureInfo/CaptureItem/CustomFields/cf_PersonID";
		static String icnCaptureItemCustomFieldscfPlaceHolder=	"//CaptureInfo/CaptureItem/CustomFields/cf_PlaceHolder";
		static String icnCaptureItemCustomFieldscfSCDefaulted=	"//CaptureInfo/CaptureItem/CustomFields/cf_SCDefaulted";
		static String icnCaptureItemCustomFieldscfSERDefaulted=	"//CaptureInfo/CaptureItem/CustomFields/cf_SERDefaulted";
		static String icnCaptureItemCustomFieldscfSourceID=	"//CaptureInfo/CaptureItem/CustomFields/cf_SourceID";
		static String icnCaptureItemCustomFieldscfSuspectReason=	"//CaptureInfo/CaptureItem/CustomFields/cf_SuspectReason";
		static String icnCaptureItemCustomFieldscfSwitchedOut=	"//CaptureInfo/CaptureItem/CustomFields/cf_SwitchedOut";
		static String icnCaptureItemCustomFieldscfAgencySC=	"//CaptureInfo/CaptureItem/CustomFields/cf_AgencySC";
		static String icnCaptureItemCustomFieldscfDoDateCheck=	"//CaptureInfo/CaptureItem/CustomFields/cf_DoDateCheck";
		static String icnCaptureItemCustomFieldscfDoCARvLAR=	"//CaptureInfo/CaptureItem/CustomFields/cf_DoCARvLAR";
		static String icnCaptureItemCustomFieldscfDoCARvData=	"//CaptureInfo/CaptureItem/CustomFields/cf_DoCARvData";
		static String icnCaptureItemCustomFieldscfCPMError=	"//CaptureInfo/CaptureItem/CustomFields/cf_CPMError";
		
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


		static String customFieldsNodeVal,cfAccountTypeNodeVal,cfANDefaultedNodeVal,cfBrandIDNodeVal,cfBatchSourceIDNodeVal;
		static String cfCustomerName1NodeVal,cfCustomerName2NodeVal,cfCustomerName3NodeVal,cfCustomerName4NodeVal,cfCustomerName5NodeVal;
		static String cfSwitchedOutNodeVal,cfSuspectReasonNodeVal,cfSourceIDNodeVal,cfSERDefaultedNodeVal,cfSCDefaultedNodeVal,cfPlaceHolderNodeVal;
		static String cfDateNodeVal,cfDeferredPostingNodeVal,cfDeviceIDNodeVal, cfCashItemNodeVal, cfICSTransactionIDNodeVal, cfImageDateTimeNodeVal;
		static String cfIQVRejectReasonNodeVal, cfLARNodeVal, cfLocationIDNodeVal ,cfLockRollIDNodeVal ,cfMarketSectorNodeVal, cfMSOUGroupIDNodeVal ,cfMSOUGroupName5NodeVal , cfNoPaySuspectRsnNodeVal;
		static String cfNPASortCodeNodeVal,cfParticipantIDNodeVal,cfPersonIDNodeVal;
		
		static String cfAccountType="cf_AccountType";
		static String cfANDefaulted="cf_ANDefaulted";
		static String cfBatch_SourceID="cf_Batch_SourceID";
		static String cfBrandID="cf_BrandID";
		static String cfCustomerName1="cf_CustomerName1";
		static String cfCustomerName2="cf_CustomerName2";
		static String cfCustomerName3="cf_CustomerName3";
		static String cfCustomerName4="cf_CustomerName4";
		static String cfCustomerName5="cf_CustomerName5";
		static String cfDate="cf_Date";
		static String cfDeferredPosting="cf_DeferredPosting";
		static String cfDeviceID="cf_DeviceID";
		static String cffCashItem="cf_fCashItem";
		static String cfICSTransactionID="cf_ICSTransactionID";
		static String cfImageDateTime="cf_ImageDateTime";
		static String cfIQVRejectReason="cf_IQVRejectReason";
		static String cfLAR="cf_LAR";
		static String cfLocationID="cf_LocationID";
		static String cfLockRollID="cf_LockRollID";
		static String cfMarketSector="cf_MarketSector";
		static String cfMSOUGroupID="cf_MSOUGroupID";
		static String cfMSOUGroupName="cf_MSOUGroupName";
		static String cfNoPaySuspectRsn="cf_NoPaySuspectRsn";
		static String cfNPASortCode="cf_NPASortCode";
		static String cfParticipantID="cf_ParticipantID";
		static String cfPersonID="cf_PersonID";
		static String cfPlaceHolder="cf_PlaceHolder";
		static String cfSCDefaulted="cf_SCDefaulted";
		static String cfSERDefaulted="cf_SERDefaulted";
		static String cfSourceID="cf_SourceID";
		static String cfSuspectReason="cf_SuspectReason";
		static String cfSwitchedOut="cf_SwitchedOut";
		static String cfAgencySC="cf_AgencySC";
		static String cfDoDateCheck="cf_DoDateCheck";
		static String cfDoCARvLAR="cf_DoCARvLAR";
		static String cfDoCARvData="cf_DoCARvData";
		static String cfCPMError="cf_CPMError";



		 public static void validateICNCustomFieldsWithIDS(String excelFilePath,String icnFilepath,String icnFileName) throws Exception{
		 		
		 		FileInputStream inputStream = new FileInputStream(excelFilePath);
		 		
		 		Workbook wrkBk = new XSSFWorkbook(inputStream);
		 		int index = wrkBk.getSheetIndex("CaptureInfoCFItemsClearing");
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
		 						System.out.println("CaptureInfoChildNode1 val :"+cell.getRichStringCellValue().toString()); //ItemHistory
		 						count++;
		 						break;
		 					case 4:
		 						tag.setCaptureInfoChildNode2(cell.getRichStringCellValue().toString());
		 						System.out.println("CaptureInfoChildNode2 val :"+cell.getRichStringCellValue().toString()); //AuditRevision, ,, so on
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
		 							getCaptureInfoChildNode1Validation(captureChildNote1,icnFilepath,icnFileName); //DbtItm
		 							if(null!=captureChildNote2){
		 								System.out.println("captureChildNote "+captureChildNote);
		 									switch(captureChildNote2){
		 							//case "TxSetSubmissn":
						 							/*case "CustomFields":
						 								customFieldsNodeVal=getCaptureItemCustomFieldsNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,customFieldsNodeVal);
						 								//validateCaptureItemCustomFieldsTagLength(captureChildNote2,length,customFieldsNodeVal);
						 								break;*/
						 								
						 							case "cf_AccountType":
						 								cfAccountTypeNodeVal=getCaptureItemCustomFieldsAccountTypeNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfAccountTypeNodeVal);	
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfAccountTypeNodeVal);
						 								break;
						 							case "cf_ANDefaulted":
						 								cfANDefaultedNodeVal=getCaptureItemCustomFieldsANDefaultedNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfANDefaultedNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfANDefaultedNodeVal);
						 								break;
						 								
						 							case "cf_Batch_SourceID":
						 								cfBatchSourceIDNodeVal=getCustomFieldsBatchSourceIDNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfBatchSourceIDNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfBatchSourceIDNodeVal);
						 								break;
						 								
						 							case "cf_BrandID":
						 								cfBrandIDNodeVal=getCustomFieldsBrandIDNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfBrandIDNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfBrandIDNodeVal);
						 								break;
						 							case "cf_CustomerName1":
						 								cfCustomerName1NodeVal=getCustomFieldsCustomerName1NodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfCustomerName1NodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfCustomerName1NodeVal);
						 								break;								
						 								
						 							case "cf_CustomerName2":
						 								cfCustomerName2NodeVal=getCustomFieldsCustomerName2NodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfCustomerName2NodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfCustomerName2NodeVal);
						 								break;
						 							case "cf_CustomerName3":
						 								cfCustomerName3NodeVal=getCustomFieldsCustomerName3NodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfCustomerName3NodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfCustomerName3NodeVal);
						 								break;
						 							case "cf_CustomerName4":
						 								cfCustomerName4NodeVal=getCustomFieldsCustomerName4NodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfCustomerName4NodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfCustomerName4NodeVal);
						 								break;
						 							case "cf_CustomerName5":
						 								cfCustomerName5NodeVal=getCustomFieldsCustomerName5NodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfCustomerName5NodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfCustomerName5NodeVal);
						 								break;
						 							case "cf_Date":
						 								cfDateNodeVal=getCustomFieldsDateNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfDateNodeVal);
						 								validateCustomFieldFixedTypeTagLength(captureChildNote2,length,cfDateNodeVal);		 								
						 								break;
						 							case "cf_DeferredPosting":
						 								cfDeferredPostingNodeVal=getCustomFieldsDeferredPostingNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfDeferredPostingNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfDeferredPostingNodeVal);
						 								break;
						 							case "cf_DeviceID":
						 								cfDeviceIDNodeVal=getCustomFieldsDeviceIDNNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfDeviceIDNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfDeviceIDNodeVal);
						 								break;
						 							case "cf_fCashItem":
						 								cfCashItemNodeVal=getCustomFieldsfCashItemNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfCashItemNodeVal);
						 								validateCustomFieldFixedTypeTagLength(captureChildNote2,length,cfCashItemNodeVal);
						 								break;
						 							case "cf_ICSTransactionID":
						 								cfICSTransactionIDNodeVal=getCustomFieldsICSTransactionIDNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfICSTransactionIDNodeVal);
						 								validateCustomFieldFixedTypeTagLength(captureChildNote2,length,cfICSTransactionIDNodeVal);
						 								break;
						 							case "cf_ImageDateTime":
						 								cfImageDateTimeNodeVal=getCustomFieldsImageDateTimeNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfImageDateTimeNodeVal);
						 								validateCustomFieldFixedTypeTagLength(captureChildNote2,length,cfImageDateTimeNodeVal);
						 								break;							
						 							case "cf_IQVRejectReason":
						 								cfIQVRejectReasonNodeVal=getCustomFieldsIQVRejectReasonNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfIQVRejectReasonNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfIQVRejectReasonNodeVal);
						 								break;
						 							case "cf_LAR":
						 								cfLARNodeVal=getCustomFieldsLARNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfLARNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfLARNodeVal);
						 								break;
						 								
						 							case "cf_LocationID":
						 								cfLocationIDNodeVal=getCustomFieldsLocationIDNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfLocationIDNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfLocationIDNodeVal);
						 								break;
						 								
						 							case "cf_LockRollID":
						 								cfLockRollIDNodeVal=getCustomFieldsLockRollIDNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfLockRollIDNodeVal);
							 							validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfLockRollIDNodeVal);
						 								break;
						 								
						 							case "cf_MarketSector":
						 								cfMarketSectorNodeVal=getCustomFieldsMarketSectorNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfMarketSectorNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfMarketSectorNodeVal);
						 								break;
						 							case "cf_MSOUGroupID":
						 								cfMSOUGroupIDNodeVal=getCustomFieldsMSOUGroupIDNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfMSOUGroupIDNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfMSOUGroupIDNodeVal);
						 								break;
						 							case "cf_MSOUGroupName":
						 								cfMSOUGroupName5NodeVal=getCustomFieldsMSOUGroupnameNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfMSOUGroupName5NodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfMSOUGroupName5NodeVal);
						 								break;
						 							case "cf_NoPaySuspectRsn":
						 								cfNoPaySuspectRsnNodeVal=getCustomFieldsNoPaySuspectRsnNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfNoPaySuspectRsnNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfNoPaySuspectRsnNodeVal);
						 								break;
						 							case "cf_NPASortCode":
						 								cfNPASortCodeNodeVal=getCustomFieldsNPASortCodeNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfNPASortCodeNodeVal);
						 								validateCustomFieldFixedTypeTagLength(captureChildNote2,length,cfNPASortCodeNodeVal);
						 								break;
						 							case "cf_ParticipantID":
						 								cfParticipantIDNodeVal=getCustomFieldsParticipantIDNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfParticipantIDNodeVal);
						 								validateCustomFieldFixedTypeTagLength(captureChildNote2,length,cfParticipantIDNodeVal);
						 								break;
						 							case "cf_PersonID":
						 								cfPersonIDNodeVal=getCustomFieldsPersonIDNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfPersonIDNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfPersonIDNodeVal);
						 								break;
						 							case "cf_PlaceHolder":
						 								cfPlaceHolderNodeVal=getCustomFeildsPlaceHolderNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfPlaceHolderNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfPlaceHolderNodeVal);
						 								break;
						 								
						 							case "cf_SCDefaulted":
						 								cfSCDefaultedNodeVal=getCustomFeildsSCDefaultedNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfSCDefaultedNodeVal);
						 								validateCustomFieldFixedTypeTagLength(captureChildNote2,length,cfSCDefaultedNodeVal);
						 								break;
						 							case "cf_SERDefaulted":
						 								cfSERDefaultedNodeVal=getCustomFeildsSERDefaultedNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfSERDefaultedNodeVal);
						 								validateCustomFieldFixedTypeTagLength(captureChildNote2,length,cfSERDefaultedNodeVal);
						 								break;
						 							case "cf_SourceID":
						 								cfSourceIDNodeVal=getCustomFeildsSourceIDNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfSourceIDNodeVal);
						 								validateCustomFieldFixedTypeTagLength(captureChildNote2,length,cfSourceIDNodeVal);
						 								break;
						 								
						 							case "cf_SuspectReason":
						 								cfSuspectReasonNodeVal=getCustomFeildsSuspectReasonNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfSuspectReasonNodeVal);
						 								validateCustomFieldsVariableTypeLength(captureChildNote2,length,cfSuspectReasonNodeVal);
						 								break;
						 								
						 							case "cf_SwitchedOut":
						 								cfSwitchedOutNodeVal=getCustomFieldsSwitchedOutNodeVal(captureChildNote2,icnFilepath,icnFileName);
						 								validateCustomFieldsTagOccurence(captureChildNote2,occurs,cfSwitchedOutNodeVal);
						 								validateCustomFieldFixedTypeTagLength(captureChildNote2,length,cfSwitchedOutNodeVal);
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


		


			private static void validateCustomFieldsTagOccurence(String coreChildNode, String occurs,String customFieldsNodeVal2) {
				System.out.println("*****CaptureItem BATCH Occurance in ICN file validation starts here*********");
				validationStepInformation("*****ChildNodes "+coreChildNode+" Occurance in ICN file validation starts here************");
				System.out.println("*****As per IDS version 8, CaptureItem BATCH Occurance for BatchDebitAmount in ICN is :"+occurs);
				validationStepInformation("*****As per IDS version 8, CaptureItem Occurence Validation for "+coreChildNode+" in ICN is :"+occurs);
				publishResults(batchNodeVal!=null,(batchNodeVal!=null)?"CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN":"BatchDebitAmount tag is null","CaptureItem "+batchNodeVal+" tag is present under "+coreChildNode+" tag in ICN","CaptureItem "+batchNodeVal+" tag validation performed");
			
			
		}


			private static void validateCustomFieldsVariableTypeLength(String coreChildNode, String length,	String bdateNodeVal) {

		 		 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
		 		 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
		 		 publishResults(((bdateNodeVal.length())<=Integer.parseInt(length)),((bdateNodeVal.length())<=Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

		 		
		 	}

		 	private static void validateCustomFieldFixedTypeTagLength(String coreChildNode, String length,	String bdateNodeVal) {

		 		 System.out.println("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
		 		 validationStepInformation("*****As per IDS version 8, CaptureItem Length Validation for "+coreChildNode+" in ICN is :"+length);
		 		 publishResults(((bdateNodeVal.length())==Integer.parseInt(length)),((bdateNodeVal.length())==Integer.parseInt(length))?coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN":coreChildNode+" length "+bdateNodeVal.length()+" in ICN validation got failed as per IDS v8",coreChildNode+" value length is "+bdateNodeVal.length()+" in ICN",coreChildNode+" Length validation");

		 		
		 	}
		 	
		 	
		 	private static String getCustomFieldsSwitchedOutNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfSwitchedOut);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfSwitchedOut);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_SwitchedOut").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFeildsSuspectReasonNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfSuspectReason);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfSuspectReason);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_SuspectReason").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFeildsSourceIDNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfSourceID);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfSourceID);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_SourceID").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
			
		 	private static String getCustomFeildsSERDefaultedNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfSERDefaulted);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfSERDefaulted);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_SERDefaulted").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
			
		 	private static String getCustomFeildsSCDefaultedNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfSCDefaulted);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfSCDefaulted);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_SCDefaulted").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	private static String getCustomFeildsPlaceHolderNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfPlaceHolder);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfPlaceHolder);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_PlaceHolder").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	private static String getCustomFieldsPersonIDNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfPersonID);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfPersonID);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_PersonID").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	private static String getCustomFieldsParticipantIDNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfParticipantID);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfParticipantID);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_ParticipantID").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	private static String getCustomFieldsNPASortCodeNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfNPASortCode);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfNPASortCode);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_NPASortCode").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	private static String getCustomFieldsNoPaySuspectRsnNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfNoPaySuspectRsn);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfNoPaySuspectRsn);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_NoPaySuspectRsn").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	private static String getCustomFieldsMSOUGroupnameNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfMSOUGroupName);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfMSOUGroupName);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_MSOUGroupName").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	private static String getCustomFieldsMarketSectorNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfMarketSector);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfMarketSector);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_MarketSector").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFieldsLockRollIDNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfLockRollID);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfLockRollID);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_LockRollID").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFieldsMSOUGroupIDNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfMSOUGroupID);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfMSOUGroupID);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_MSOUGroupID").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFieldsLocationIDNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfLocationID);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfLocationID);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_LocationID").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFieldsLARNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfLAR);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfLAR);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_LAR").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFieldsIQVRejectReasonNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfIQVRejectReason);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfIQVRejectReason);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_IQVRejectReason").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFieldsImageDateTimeNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfImageDateTime);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfImageDateTime);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFieldsICSTransactionIDNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfICSTransactionID);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfICSTransactionID);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfICSTransactionID).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFieldsfCashItemNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscffCashItem);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscffCashItem);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cffCashItem).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFieldsDeviceIDNNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfDeviceID);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfDeviceID);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfDeviceID).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}
		 	
		 	private static String getCustomFieldsDeferredPostingNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfDeferredPosting);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfDeferredPosting);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfDeferredPosting).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}



		 	private static String getCustomFieldsDateNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfDate);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfDate);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfDate).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}



		 	private static String getCustomFieldsCustomerName5NodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfCustomerName5);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfCustomerName5);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfCustomerName5).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}



		 	private static String getCustomFieldsCustomerName4NodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfCustomerName5);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfCustomerName5);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfCustomerName5).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}



		 	private static String getCustomFieldsCustomerName3NodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfCustomerName5);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfCustomerName5);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfCustomerName5).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}



		 	private static String getCustomFieldsCustomerName2NodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfCustomerName5);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfCustomerName5);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfCustomerName5).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}



		 	private static String getCustomFieldsCustomerName1NodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfCustomerName5);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfCustomerName5);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfCustomerName5).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}



		 	private static String getCustomFieldsBrandIDNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfBrandID);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfBrandID);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfBrandID).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}



		 	private static String getCustomFieldsBatchSourceIDNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfBatch_SourceID);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfBatch_SourceID);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfBatch_SourceID).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}



		 	private static String getCaptureItemCustomFieldsANDefaultedNodeVal(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfANDefaulted);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfANDefaulted);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,cfANDefaulted).get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}

		
		 	private static String getCaptureItemCustomFieldsAccountTypeNodeVal(String coreChildNode, String icnFilepath,
		 			String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfAccountType);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFieldscfAccountType);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"cf_AccountType").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		return bdateNodeVal;
		 	}



		 	private static void getCaptureInfoChildNode1Validation(String coreChildNode, String icnFilepath, String icnFileName) throws Exception {
		 		
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFields);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemCustomFields);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"CustomFields").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 	
		 		
		 	}

		
		 	
		
		 	
		 	private static void getCaptureInfoChildNodeValidation(String coreChildNode, String icnFilepath,String icnFileName) throws Exception {
		 		System.out.println("****************************************************************");
		 		System.out.println("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		System.out.println("****************************************************************");
		 		
		 		validationStepInformation("*********************************************************************************************************************");
		 		validationStepInformation("Validating "+coreChildNode+" is populating under CaptureItem in ICN as per IDS version 8");
		 		validationStepInformation("*********************************************************************************************************************");
		 		Map<String, String> dateKeyVal =getXMLNodeNameByXPATH(icnFilepath,icnFileName,icnCaptureItemXpath);
		 		String dateKey =getMapKey(dateKeyVal);
		 		System.out.println("businessdate Key:"+dateKey+"::businessdate KeyValue Pair:"+dateKeyVal);
		 		Map<String, String> dateKeyVal1 =getXMLNodeValueByXPATH(icnFilepath,icnFileName,icnCaptureItemXpath);
		 		String bdateNodeVal = getNodeListValues(dateKeyVal1,"CaptureItem").get(0);
		 		System.out.println("bdateNodeVal :"+bdateNodeVal);
		 		boolean flagb =Component.verifyEquals(dateKey, coreChildNode, coreChildNode+" tagname is as per IDSv8");
		 			
		 		publishResults(flagb,dateKey, coreChildNode,  coreChildNode+" tagname is as per IDSv8");
		 		
		 		
		 	}

		 	private static void getRootNodeValidation(String rootNode, String icnFilepath, String icnFileName) throws Exception {
		 		System.out.println("For loop RootNode Core :"+rootNode);
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
		 }
