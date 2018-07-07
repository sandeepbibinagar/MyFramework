DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
<Core>
	<BusinessDate>2017-06-01</BusinessDate>
	<ExtractId>3000002016110206MA02000021</ExtractId>
	<ProcessingParticipantId>300001</ProcessingParticipantId>
	<ExtMessageType>MSG06</ExtMessageType>
	<IntMessageType>06MA02</IntMessageType>
	<MessageSource>MO</MessageSource>
	<MessageDestination>IA</MessageDestination>
	<RecordCounts>2</RecordCounts>
	</Core>

<Entities>
		<Entity>
			<EntityType>I</EntityType>
			<EntityId>GHI1DE231067890f1GHi2JkLm</EntityId>
			<StateRevision>100112</StateRevision>
			<EntityState>510</EntityState>
			<SourceDateTime>2017-06-02T13:00:00+01:00</SourceDateTime>
		</Entity>
	<Entity>
			<EntityType>I</EntityType>
			<EntityId>GHI1DE231067890f1GHi2JkLa</EntityId>
				<StateRevision>100121</StateRevision>
			<EntityState>530</EntityState>
			<SourceDateTime>2017-06-02T13:00:00+01:00</SourceDateTime>
		</Entity>
		
</Entities>
<Items>
    <Item>
        <ItemId>GHI1DE231067890f1GHi2JkLm</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2017-06-02T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>123457</AuditRevision>
         <Gender>Db</Gender>
         <NoPayReason>nopay</NoPayReason>
         <Codeline>
            <Serial>500001</Serial>
		    <SortCode>121101</SortCode> 
            <Account>70000001</Account>
			<Amount>5000.41</Amount>
            <Currency>123</Currency>
	</Codeline>
    </Item>
	<Item>
        <ItemId>GHI1DE231067890f1GHi2JkLa</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2017-06-02T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>123457</AuditRevision>
         <Gender>Db</Gender>
         <NoPayReason>nopay</NoPayReason>
         <Codeline>
            <Serial>500002</Serial>
		    <SortCode>121101</SortCode> 
            <Account>70000002</Account>
		<!--<TranCode>20</TranCode>-->
			<Amount>5000.42</Amount>
            <Currency>123</Currency>
	</Codeline>
    </Item>
  </Items>
  <CaptureInfo>
	<CaptureItem>
		<JOB>
            <BusinessDate>2017-06-01</BusinessDate>
            <InstallationId>1234567890INSTL</InstallationId>
            <CaptureSystemId>A</CaptureSystemId>
            <StartTime>2017-06-02T09:58:36+02:00</StartTime>
            <EndTime>2017-06-02T10:58:36+02:00</EndTime>
            <WorkTypeNbr>11</WorkTypeNbr>
            <SortFamily>123</SortFamily>
            <SourceType>ATM SOURCE TYPE</SourceType>
            <SourceName> ATM NAME OF THE SOURCE</SourceName>
            <SourceID>19</SourceID> 
            <FinancialInstitutionID>Insta1234</FinancialInstitutionID>
            <CollectionStartTime>2017-06-02T09:59:36+04:00</CollectionStartTime>
            <CollectionEndTime>2017-06-02T09:59:56+04:00</CollectionEndTime>
		</JOB>
		<BLOCK>
            <BlkNbr>1261</BlkNbr> 
            <ImageType>CCITTJP</ImageType>
		</BLOCK>
		<BATCH>
            <TktTc>1b</TktTc>
            <TktAn>1234567890TKT</TktAn> 
            <TktSC>SC3396</TktSC>
            <TktAux>1234567890AUXILIARY1</TktAux>
            <BatchCreditCount>12560</BatchCreditCount> 
            <BatchDebitCount>12340</BatchDebitCount>
            <BatchCreditAmount>123456789012.11</BatchCreditAmount>
            <BatchDebitAmount>123456789012.22</BatchDebitAmount>
		</BATCH>     
        <APGDIN>12343ABCD%</APGDIN>
		<APGBusinessDate>2017-06-02T14:28:13+01:00</APGBusinessDate>
        <Gender>Db</Gender> 
        <TransactionNumber>1234567890</TransactionNumber> 
        <IsElectronic>1</IsElectronic> 
        <IsOnUs>1</IsOnUs> 
        <IsDeleted>true</IsDeleted> 
        <IsCorrected>true</IsCorrected>
        <IsAmountCorrected>true</IsAmountCorrected> 
        <IsTCCorrected>true</IsTCCorrected>
        <IsANCorrected>1</IsANCorrected> 
        <IsSortCodeCorrected>1</IsSortCodeCorrected>
        <IsSerialCorrected>true</IsSerialCorrected> 
        <IsReject>true</IsReject> 
        <RejectReason>999</RejectReason> 
        <SpSelector>ZZ23</SpSelector> 
        <Currency>GBP</Currency> 
        <AdjustmentReason>99</AdjustmentReason> 
		<Narrative>abcdefghij123456</Narrative>
		<OperatorId>randomtest</OperatorId>
		<ProcessId>sasdadasdasd</ProcessId>
		<ItemId>GHI1DE231067890f1GHi2JkLm</ItemId>
        <OriginalIsn>123456789012</OriginalIsn> 
        <AeStatus>123456789012345AESTS123</AeStatus> 
        <IcStatus>123456789012345ICSTS123</IcStatus>
        <IqvStatus>12345678901234</IqvStatus> 
        <CarSetId>123</CarSetId>
        <CarResult>1234567890C</CarResult> 
        <IaStatus>1234567890I</IaStatus> 
        <IaResult>1234567890R</IaResult> 
        <PNVReviewStatus>1</PNVReviewStatus> 
        <DuplicateStatus>3</DuplicateStatus>
        <ReturnReason>239</ReturnReason> 
        <ChequeIssuedDate>2017-06-02T09:58:36+02:00</ChequeIssuedDate>
	<!--	<CustomFields>
          <cf_AccountType>PJNUYKSFKMTE</cf_AccountType>
          <cf_ANDefaulted>J</cf_ANDefaulted>
          <cf_Batch_SourceID>Yes</cf_Batch_SourceID>
          <cf_BrandID>QUHFSDBQMMXKVSN</cf_BrandID>
          <cf_CustomerName1>TDZHMRPIVWXMMADZHWUMKWWBLCYXAXOOZSQJTXWKALMJVRPQINPNBGCWHYVKSSIYHIMTJUGKZJMYKNFTMIPNHBNTPTCVBMSIDNB</cf_CustomerName1>
          <cf_CustomerName2>LLOYDS</cf_CustomerName2>
          <cf_CustomerName3>Retail</cf_CustomerName3>
          <cf_CustomerName4>WGNHFNRTEMTSCYJDVRGVSDPSKJNRDUWLSOPBBGCNVZKHICZOIFIWVSMSRVWVAAQKQIZOIIBPOTZTTIKIKYIYMAQABWKGJGMUJAR</cf_CustomerName4>
          <cf_CustomerName5>ZCKGADTHBSTZILNLCRRYPSZMLXVFHGEJSRKFZRIUSSOHIXBUVUKCRCBHDYUCIOZVNJHRAIMKJWFWDRDOVAJNVCRPPCCAWFMETSU</cf_CustomerName5>
          <cf_Date>YQKUNIATVX</cf_Date>
          <cf_DeferredPosting>EDL</cf_DeferredPosting>
          <cf_DeviceID>UGGHFMALSRBYFLNDKADKXFYLGVFVIW</cf_DeviceID>
          <cf_fCashItem>S</cf_fCashItem>
          <cf_ICSTransactionID>LYQGQYEZGSJGYJWHWMPKXASOP</cf_ICSTransactionID>
          <cf_ImageDateTime>IVBJHECVYQZPFXEGAXSNYZCKNJIS</cf_ImageDateTime>
          <cf_IQVRejectReason>MVKGCQHCCQZNGKZSXSQOLEWDAAQJTR</cf_IQVRejectReason>
          <cf_LAR>VYWJJDGSMOQ</cf_LAR>
          <cf_LocationID>BDKXEHOZJNVSTHIYGFBA</cf_LocationID>
          <cf_LockRollID>XJLUARWAWMEFBYJAMF</cf_LockRollID>
          <cf_MarketSector>VTCKKEAFCCBFZNLOSSSPWNKNCWIPRVYHMAKIYNYGUVPUYRNQUNGRIWJIIOHNVKOHEUSYDDQETLVBXPOLJSVEOHZPRBZJGKLJGIQ</cf_MarketSector>
          <cf_MSOUGroupID>EYONRWBXWBHKSZVEDPKSXUCHBDGYJZLUPZJNVEDHMJCOKQGKLGITWPTKVGLNQDOXPMVTPIIYLDFNSNXKXVNCRRYAFZROSQQJVFZ</cf_MSOUGroupID>
          <cf_MSOUGroupName>XVMUCXJHSJGRIJAQUQLFXNUNPRUGVMWRMOAPKBKEHKFQZIIFRAVSKZCJAZELDDMGNPFTJLFTGGYNIEOHIOOVQDGMLQOLWZBDXZC</cf_MSOUGroupName>
          <cf_NoPaySuspectRsn>JCPWGUCMEBPBVKXCPPRPKISHSWUXHF</cf_NoPaySuspectRsn>
          <cf_NPASortCode>UPJCEE</cf_NPASortCode>
          <cf_ParticipantID>TSHAOR</cf_ParticipantID>
          <cf_PersonID>CKWPSAZWMOGJSBJHDJZF</cf_PersonID>
          <cf_PlaceHolder>FYHZSTGSJWHQBTGHKDEUYKSFJZJDVMOYAJLDDXXZUGNPGPIZVBWAFFHUGBQSJCXWIJPSHCENFEZRXDZTQKHTQOTVBRXBHEPRJQH</cf_PlaceHolder>
          <cf_SCDefaulted>X</cf_SCDefaulted>
          <cf_SERDefaulted>Z</cf_SERDefaulted>
          <cf_SourceID>QSTX</cf_SourceID>
          <cf_SuspectReason>IU</cf_SuspectReason>
          <cf_SwitchedOut>A</cf_SwitchedOut>
        </CustomFields>-->
		<ItemHistory>
			 <AuditRevision>123456</AuditRevision> 
			 <UserId>USER IDENTITY FOR THE IMAGE ARCHIVE TO BE STORED 1234567890</UserId> 
			 <Time>2017-06-02T09:58:36+02:00</Time>
			 <Process>PROCESS IDENTIFIER 12</Process>
			 <IsDeleted>true</IsDeleted>
			 <Gender>Db</Gender> 
			 <Reference>REFERENCE 2012</Reference>
			 <Account>ACCOUNT NMBR1</Account> 
			 <SortCode>123QWE</SortCode>  
			 <TranCode>20</TranCode> 
			 <Amount>123456789.1</Amount>
		 </ItemHistory>
         </CaptureItem>   
		<CaptureItem>
			<JOB>
            <BusinessDate>2017-06-01</BusinessDate>
            <InstallationId>1234567890INSTL</InstallationId>
            <CaptureSystemId>A</CaptureSystemId>
            <StartTime>2017-06-02T09:58:36+02:00</StartTime>
            <EndTime>2017-06-02T10:58:36+02:00</EndTime>
            <WorkTypeNbr>11</WorkTypeNbr>
            <SortFamily>123</SortFamily>
            <SourceType>ATM SOURCE TYPE</SourceType>
            <SourceName> ATM NAME OF THE SOURCE</SourceName>
            <SourceID>19</SourceID> 
            <FinancialInstitutionID>Insta1234</FinancialInstitutionID>
            <CollectionStartTime>2017-06-02T09:59:36+04:00</CollectionStartTime>
            <CollectionEndTime>2017-06-02T09:59:56+04:00</CollectionEndTime>
		</JOB>
		<BLOCK>
            <BlkNbr>1261</BlkNbr> 
            <ImageType>CCITTJP</ImageType>
		</BLOCK>
		<BATCH>
            <TktTc>1b</TktTc>
            <TktAn>1234567890TKT</TktAn> 
            <TktSC>SC3396</TktSC>
            <TktAux>1234567890AUXILIARY1</TktAux>
            <BatchCreditCount>12560</BatchCreditCount> 
            <BatchDebitCount>12340</BatchDebitCount>
            <BatchCreditAmount>123456789012.11</BatchCreditAmount>
            <BatchDebitAmount>123456789012.22</BatchDebitAmount>
		</BATCH>     
        <APGDIN>12335ABCD%</APGDIN>
		<APGBusinessDate>2017-06-02T14:28:13+01:00</APGBusinessDate>
        <Gender>Db</Gender> 
        <TransactionNumber>1234567890</TransactionNumber> 
        <IsElectronic>1</IsElectronic> 
        <IsOnUs>1</IsOnUs> 
        <IsDeleted>true</IsDeleted> 
        <IsCorrected>true</IsCorrected>
        <IsAmountCorrected>true</IsAmountCorrected> 
        <IsTCCorrected>true</IsTCCorrected>
        <IsANCorrected>1</IsANCorrected> 
        <IsSortCodeCorrected>0</IsSortCodeCorrected>
        <IsSerialCorrected>true</IsSerialCorrected> 
        <IsReject>false</IsReject> 
        <RejectReason>999</RejectReason> 
        <SpSelector>ZZ23</SpSelector> 
        <Currency>GBP</Currency> 
        <AdjustmentReason>99</AdjustmentReason> 
		<Narrative>abcdefghij123456</Narrative>
		<OperatorId>randomtest1</OperatorId>
		<ProcessId>sasdadasdasd</ProcessId>
		<ItemId>GHI1DE231067890f1GHi2JkLa</ItemId>
        <OriginalIsn>123456789012</OriginalIsn> 
        <AeStatus>123456789012345AESTS123</AeStatus> 
        <IcStatus>123456789012345ICSTS123</IcStatus>
        <IqvStatus>12345678901234</IqvStatus> 
        <CarSetId>123</CarSetId>
        <CarResult>1234567890C</CarResult> 
        <IaStatus>1234567890I</IaStatus> 
        <IaResult>1234567890R</IaResult> 
        <PNVReviewStatus>1</PNVReviewStatus> 
        <DuplicateStatus>9</DuplicateStatus>
        <ReturnReason>239</ReturnReason> 
        <ChequeIssuedDate>2017-06-02T09:58:36+02:00</ChequeIssuedDate>
	<!--<CustomFields>
          <cf_AccountType>PJNUYKSFKMTE</cf_AccountType>
          <cf_ANDefaulted>J</cf_ANDefaulted>
          <cf_Batch_SourceID>Yes</cf_Batch_SourceID>
          <cf_BrandID>QUHFSDBQMMXKVSN</cf_BrandID>
          <cf_CustomerName1>TDZHMRPIVWXMMADZHWUMKWWBLCYXAXOOZSQJTXWKALMJVRPQINPNBGCWHYVKSSIYHIMTJUGKZJMYKNFTMIPNHBNTPTCVBMSIDNB</cf_CustomerName1>
          <cf_CustomerName2>LLOYDS</cf_CustomerName2>
          <cf_CustomerName3>Retail</cf_CustomerName3>
          <cf_CustomerName4>WGNHFNRTEMTSCYJDVRGVSDPSKJNRDUWLSOPBBGCNVZKHICZOIFIWVSMSRVWVAAQKQIZOIIBPOTZTTIKIKYIYMAQABWKGJGMUJAR</cf_CustomerName4>
          <cf_CustomerName5>ZCKGADTHBSTZILNLCRRYPSZMLXVFHGEJSRKFZRIUSSOHIXBUVUKCRCBHDYUCIOZVNJHRAIMKJWFWDRDOVAJNVCRPPCCAWFMETSU</cf_CustomerName5>
          <cf_Date>YQKUNIATVX</cf_Date>
          <cf_DeferredPosting>EDL</cf_DeferredPosting>
          <cf_DeviceID>UGGHFMALSRBYFLNDKADKXFYLGVFVIW</cf_DeviceID>
          <cf_fCashItem>S</cf_fCashItem>
          <cf_ICSTransactionID>LYQGQYEZGSJGYJWHWMPKXASOP</cf_ICSTransactionID>
          <cf_ImageDateTime>IVBJHECVYQZPFXEGAXSNYZCKNJIS</cf_ImageDateTime>
          <cf_IQVRejectReason>MVKGCQHCCQZNGKZSXSQOLEWDAAQJTR</cf_IQVRejectReason>
          <cf_LAR>VYWJJDGSMOQ</cf_LAR>
          <cf_LocationID>BDKXEHOZJNVSTHIYGFBA</cf_LocationID>
          <cf_LockRollID>XJLUARWAWMEFBYJAMF</cf_LockRollID>
          <cf_MarketSector>VTCKKEAFCCBFZNLOSSSPWNKNCWIPRVYHMAKIYNYGUVPUYRNQUNGRIWJIIOHNVKOHEUSYDDQETLVBXPOLJSVEOHZPRBZJGKLJGIQ</cf_MarketSector>
          <cf_MSOUGroupID>EYONRWBXWBHKSZVEDPKSXUCHBDGYJZLUPZJNVEDHMJCOKQGKLGITWPTKVGLNQDOXPMVTPIIYLDFNSNXKXVNCRRYAFZROSQQJVFZ</cf_MSOUGroupID>
          <cf_MSOUGroupName>XVMUCXJHSJGRIJAQUQLFXNUNPRUGVMWRMOAPKBKEHKFQZIIFRAVSKZCJAZELDDMGNPFTJLFTGGYNIEOHIOOVQDGMLQOLWZBDXZC</cf_MSOUGroupName>
          <cf_NoPaySuspectRsn>JCPWGUCMEBPBVKXCPPRPKISHSWUXHF</cf_NoPaySuspectRsn>
          <cf_NPASortCode>UPJCEE</cf_NPASortCode>
          <cf_ParticipantID>TSHAOR</cf_ParticipantID>
          <cf_PersonID>CKWPSAZWMOGJSBJHDJZF</cf_PersonID>
          <cf_PlaceHolder>FYHZSTGSJWHQBTGHKDEUYKSFJZJDVMOYAJLDDXXZUGNPGPIZVBWAFFHUGBQSJCXWIJPSHCENFEZRXDZTQKHTQOTVBRXBHEPRJQH</cf_PlaceHolder>
          <cf_SCDefaulted>X</cf_SCDefaulted>
          <cf_SERDefaulted>Z</cf_SERDefaulted>
          <cf_SourceID>QSTX</cf_SourceID>
          <cf_SuspectReason>IU</cf_SuspectReason>
          <cf_SwitchedOut>A</cf_SwitchedOut>
        </CustomFields>-->
		<ItemHistory>
			 <AuditRevision>123456</AuditRevision> 
			 <UserId>USER IDENTITY FOR THE IMAGE ARCHIVE TO BE STORED 1234567890</UserId> 
			 <Time>2016-11-07T09:58:36+02:00</Time>
			 <Process>PROCESS IDENTIFIER 12</Process>
			 <IsDeleted>true</IsDeleted>
			 <Gender>Db</Gender> 
			 <Reference>REFERENCE 2012</Reference>
			 <Account>ACCOUNT NMBR1</Account> 
			 <SortCode>123QWE</SortCode>  
			 <TranCode>20</TranCode> 
			 <Amount>123456789.1</Amount>
		 </ItemHistory>
         </CaptureItem> 
      </CaptureInfo> 
</ICN>
</Document>
'
       
              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [06MA02Message](@XMLString)
