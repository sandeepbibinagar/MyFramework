DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
  <ICN>     
   <Core>
     <BusinessDate>2017-05-31</BusinessDate>
     <ExtractId>12345678909876543212345677</ExtractId>
     <ProcessingParticipantId>888881</ProcessingParticipantId>
     <ExtMessageType>MSG06</ExtMessageType>
     <IntMessageType>06MA02</IntMessageType>
     <MessageSource>MO</MessageSource>
     <MessageDestination>IA</MessageDestination>
     <RecordCounts>2</RecordCounts>
   </Core>
   <Entities>     
	 <Entity>
        <EntityType>I</EntityType>
        <EntityId>AL87ME1704421222803037301</EntityId>
        <StateRevision>300006</StateRevision>
        <EntityState>530</EntityState>
        <SourceDateTime>2017-04-06T07:58:36+00:00</SourceDateTime>
     </Entity>
  </Entities>
  <Items>
	<Item>
        <ItemId>AL87ME1704421222803037301</ItemId>
         <ProcessId>34</ProcessId>
         <OperatorId>23456789009876543215</OperatorId>
         <UpdateDateTime>2017-04-06T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>300011</AuditRevision>         
         <Gender>Db</Gender>
         <NoPayReason>123</NoPayReason>         
         <Codeline>
            <Serial>111121</Serial>
		    <SortCode>309991</SortCode> 
            <Account>12344678</Account>
			<TranCode>19</TranCode>
			<Amount>7.11</Amount>
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
            <StartTime>2017-04-06T09:58:36+00:00</StartTime>
            <EndTime>2017-04-06T10:58:36+00:00</EndTime>
            <WorkTypeNbr>11</WorkTypeNbr>
            <SortFamily>123</SortFamily>
            <SourceType>atm SOURCE</SourceType>
            <SourceName>ATM</SourceName>
            <SourceID>12</SourceID> 
            <FinancialInstitutionID>Insta123</FinancialInstitutionID>
            <CollectionStartTime>2017-04-06T09:59:36+00:00</CollectionStartTime>
            <CollectionEndTime>2017-04-06T09:59:56+00:00</CollectionEndTime>
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
			<APGBusinessDate>2017-06-01T09:59:36+00:00</APGBusinessDate>
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
            <ItemId>AL87ME1704421222803037301</ItemId>           
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
            <ChequeIssuedDate>2017-04-06T09:58:36+00:00</ChequeIssuedDate>
            <ItemHistory>
                 <AuditRevision>300013</AuditRevision> 
                 <UserId>User name</UserId> 
                 <Time>2017-04-06T09:58:36+00:00</Time>
                 <Process>process 12</Process>
                 <IsDeleted>1</IsDeleted>
                 <Gender>Db</Gender> 
                 <Reference>reference12</Reference>
                 <Account>123456is</Account> 
                 <SortCode>403504</SortCode>  
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
