DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
  <ICN>     
   <Core>
     <BusinessDate>2017-06-01</BusinessDate>
     <ExtractId>12345678909876543212345677</ExtractId>
     <ProcessingParticipantId>000632</ProcessingParticipantId>
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
        <StateRevision>000003</StateRevision>
        <EntityState>510</EntityState>
        <SourceDateTime>2017-05-23T07:58:36+00:00</SourceDateTime>
     </Entity>
	 <Entity>
           <EntityType>I</EntityType>
           <EntityId>GHI1DE231067890f1GHi2JkLa</EntityId>                                                 
           <StateRevision>000004</StateRevision>
       	   <EntityState>510</EntityState>                                                   
		   <SourceDateTime>2017-05-22T00:20:59+01:00</SourceDateTime> 
		</Entity>		
  </Entities>
  <Items>
    <Item>
        <ItemId>GHI1DE231067890f1GHi2JkLm</ItemId>
         <ProcessId>34</ProcessId>
         <OperatorId>23456789009876543215</OperatorId>
         <UpdateDateTime>2017-05-23T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>000003</AuditRevision>         
         <Gender>Db</Gender>
         <NoPayReason>123</NoPayReason>         
         <Codeline>
            <Serial>500001</Serial>
		    <SortCode>405168</SortCode> 
            <Account>70000001</Account>
			<TranCode>19</TranCode>
			<Amount>5000.41</Amount>
            <Currency>0</Currency>
		</Codeline>
    </Item>
	<Item>
        <ItemId>GHI1DE231067890f1GHi2JkLa</ItemId>
         <ProcessId>35</ProcessId>
         <OperatorId>23456789009876543213</OperatorId>
         <UpdateDateTime>2017-05-22T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>000003</AuditRevision>         
         <Gender>Db</Gender>
         <NoPayReason>124</NoPayReason>         
         <Codeline>
            <Serial>500002</Serial>
		    <SortCode>405168</SortCode> 
            <Account>70000002</Account>
			<TranCode>21</TranCode>
			<Amount>5000.42</Amount>
            <Currency>0</Currency>
		</Codeline>
    </Item>
	
  </Items>
  <CaptureInfo>   
     <CaptureItem> 
		<JOB>
            <BusinessDate>2017-06-01</BusinessDate>
            <InstallationId>id123install789</InstallationId>
            <CaptureSystemId>A</CaptureSystemId>
            <StartTime>2017-05-23T09:58:36+00:00</StartTime>
            <EndTime>2017-05-23T10:58:36+00:00</EndTime>
            <WorkTypeNbr>11</WorkTypeNbr>
            <SortFamily>123</SortFamily>
            <SourceType>atm SOURCE</SourceType>
            <SourceName>ATM</SourceName>
            <SourceID>12</SourceID> 
            <FinancialInstitutionID>Insta123</FinancialInstitutionID>
            <CollectionStartTime>2017-05-23T09:59:36+00:00</CollectionStartTime>
            <CollectionEndTime>2017-05-23T09:59:56+00:00</CollectionEndTime>
		</JOB>
		<BLOCK>
            <BlkNbr>126</BlkNbr> 
            <ImageType>TIFFioi</ImageType>
	   </BLOCK>
	   <BATCH>
            <TktTc>1b</TktTc>
            <TktAn>1234567890987</TktAn> 
            <TktSC>SC1178</TktSC>
            <TktAux>AUxillarylllllllllll</TktAux>
            <BatchCreditCount>12560</BatchCreditCount> 
            <BatchDebitCount>12340</BatchDebitCount>
            <BatchCreditAmount>123456789101112.11</BatchCreditAmount>
            <BatchDebitAmount>444455555666666.22</BatchDebitAmount>
	   </BATCH>
            <APGDIN>1234567890</APGDIN>
			<APGBusinessDate>2017-05-23T09:59:36+00:00</APGBusinessDate>
            <Gender>Db</Gender> 
            <TransactionNumber>1234567890</TransactionNumber> 
            <IsElectronic>1</IsElectronic> 
            <IsOnUs>1</IsOnUs> 
            <IsDeleted>true</IsDeleted> 
            <IsCorrected>true</IsCorrected>
            <IsAmountCorrected>true</IsAmountCorrected> 
            <IsTCCorrected>1</IsTCCorrected>
            <IsANCorrected>1</IsANCorrected> 
            <IsSortCodeCorrected>true</IsSortCodeCorrected>
            <IsSerialCorrected>true</IsSerialCorrected> 
            <IsReject>true</IsReject> 
            <RejectReason>129</RejectReason> 
            <SpSelector>abcd</SpSelector> 
            <Currency>GBP</Currency> 
            <AdjustmentReason>66</AdjustmentReason> 
            <Narrative>provide comments</Narrative> 
            <OperatorId>user name1123</OperatorId>
            <ProcessId>APG process</ProcessId> 
            <ItemId>GHI1DE231067890f1GHi2JkLm</ItemId>           
            <OriginalIsn>123456789012</OriginalIsn> 
            <AeStatus>opopopopopopopopopopopp</AeStatus> 
            <IcStatus>opopopopopopopopopopopp</IcStatus>
            <IqvStatus>12345678901234</IqvStatus> 
            <CarSetId>123</CarSetId>
            <CarResult>plplplplplp</CarResult> 
            <IaStatus>plplplplplp</IaStatus> 
            <IaResult>plplplplplp</IaResult> 
            <PNVReviewStatus>1</PNVReviewStatus> 
            <DuplicateStatus>6</DuplicateStatus>
            <ReturnReason>77</ReturnReason> 
            <ChequeIssuedDate>2017-05-23T09:58:36+00:00</ChequeIssuedDate>
            <ItemHistory>
                 <AuditRevision>123210</AuditRevision> 
                 <UserId>User name</UserId> 
                 <Time>2017-05-23T09:58:36+00:00</Time>
                 <Process>process 12</Process>
                 <IsDeleted>1</IsDeleted>
                 <Gender>Db</Gender> 
                 <Reference>reference12</Reference>
                 <Account>123456is</Account> 
                 <SortCode>12345d</SortCode>  
                 <TranCode>ik</TranCode> 
                 <Amount>12345678.66</Amount>
            </ItemHistory>
         </CaptureItem> 
	     <CaptureItem> 
			<JOB>
				<BusinessDate>2017-06-01</BusinessDate>
				<InstallationId>id123install789</InstallationId>
				<CaptureSystemId>A</CaptureSystemId>
				<StartTime>2017-05-23T09:58:36+00:00</StartTime>
				<EndTime>2017-05-23T10:58:36+00:00</EndTime>
				<WorkTypeNbr>11</WorkTypeNbr>
				<SortFamily>123</SortFamily>
				<SourceType>atm SOURCE</SourceType>
				<SourceName>ATM</SourceName>
				<SourceID>12</SourceID> 
				<FinancialInstitutionID>Insta123</FinancialInstitutionID>
				<CollectionStartTime>2017-05-23T09:59:36+00:00</CollectionStartTime>
				<CollectionEndTime>2017-05-23T09:59:56+00:00</CollectionEndTime>
			</JOB>
			<BLOCK>
				<BlkNbr>126</BlkNbr> 
				<ImageType>TIFFioi</ImageType>
			</BLOCK>
			 <BATCH>
				<TktTc>1b</TktTc>
				<TktAn>1234567890987</TktAn> 
				<TktSC>SC1178</TktSC>
				<TktAux>AUxillarylllllllllll</TktAux>
				<BatchCreditCount>12560</BatchCreditCount> 
				<BatchDebitCount>12340</BatchDebitCount>
				<BatchCreditAmount>123456789101112.11</BatchCreditAmount>
				<BatchDebitAmount>444455555666666.22</BatchDebitAmount>
			 </BATCH>
				<APGDIN>1234567890</APGDIN>
				<APGBusinessDate>2017-05-23T09:59:36+00:00</APGBusinessDate>
				<Gender>Db</Gender> 
				<TransactionNumber>1234567890</TransactionNumber> 
				<IsElectronic>0</IsElectronic> 
				<IsOnUs>0</IsOnUs> 
				<IsDeleted>false</IsDeleted> 
				<IsCorrected>false</IsCorrected>
				<IsAmountCorrected>false</IsAmountCorrected> 
				<IsTCCorrected>0</IsTCCorrected>
				<IsANCorrected>0</IsANCorrected> 
				<IsSortCodeCorrected>false</IsSortCodeCorrected>
				<IsSerialCorrected>false</IsSerialCorrected> 
				<IsReject>false</IsReject> 
				<RejectReason>129</RejectReason> 
				<SpSelector>abcd</SpSelector> 
				<Currency>GBP</Currency> 
				<AdjustmentReason>66</AdjustmentReason> 
				<Narrative>provide comments</Narrative> 
				<OperatorId>user name1123</OperatorId>
				<ProcessId>APG process</ProcessId> 
				<ItemId>GHI1DE231067890f1GHi2JkLa</ItemId>           
				<OriginalIsn>123456789012</OriginalIsn> 
				<AeStatus>opopopopopopopopopopopp</AeStatus> 
				<IcStatus>opopopopopopopopopopopp</IcStatus>
				<IqvStatus>12345678901234</IqvStatus> 
				<CarSetId>123</CarSetId>
				<CarResult>plplplplplp</CarResult> 
				<IaStatus>plplplplplp</IaStatus> 
				<IaResult>plplplplplp</IaResult> 
				<PNVReviewStatus>1</PNVReviewStatus> 
				<DuplicateStatus>6</DuplicateStatus>
				<ReturnReason>77</ReturnReason> 
				<ChequeIssuedDate>2017-05-23T09:58:36+00:00</ChequeIssuedDate>
				<ItemHistory>
					 <AuditRevision>123210</AuditRevision> 
					 <UserId>User name</UserId> 
					 <Time>2017-05-23T09:58:36+00:00</Time>
					 <Process>process 12</Process>
					 <IsDeleted>1</IsDeleted>
					 <Gender>Db</Gender> 
					 <Reference>reference12</Reference>
					 <Account>123456is</Account> 
					 <SortCode>12345d</SortCode>  
					 <TranCode>ik</TranCode> 
					 <Amount>12345678.66</Amount>
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
