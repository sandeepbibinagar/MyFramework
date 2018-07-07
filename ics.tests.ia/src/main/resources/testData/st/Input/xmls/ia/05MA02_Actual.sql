DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">

   <ICN>     
   <Core>
     <BusinessDate>2017-04-06</BusinessDate>
     <ExtractId>ExID</ExtractId>
     <ProcessingParticipantId>000005</ProcessingParticipantId>
     <ExtMessageType>MSG05</ExtMessageType>
     <IntMessageType>05MA02</IntMessageType>
     <MessageSource>MO</MessageSource>
     <MessageDestination>IA</MessageDestination>
     <RecordCounts>1</RecordCounts>
   </Core>

   <Entities>      
   	 <Entity>
		<EntityType>I</EntityType>                                  
		<EntityId>EntID</EntityId>
		<StateRevision>100029</StateRevision>
		<EntityState>20</EntityState>
		<SourceDateTime>2017-04-06T07:58:36+00:00</SourceDateTime>
	 </Entity>
  </Entities> 
   
  <Items>    
	    <Item>
        <ItemId>ITMID1</ItemId>
         <ProcessId>1234567890123456789012345</ProcessId>
         <OperatorId>12345678901234567890</OperatorId>
         <UpdateDateTime>2017-04-06T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>100027</AuditRevision>
         <Gender>Cr</Gender>
		 <AdjustmentReason>99</AdjustmentReason>
		 <NoPayReason>NoPayReason</NoPayReason> 
         <Codeline>
            <Reference>123456</Reference>
	        <SortCode>923456</SortCode> 
            <Account>92345678</Account>
	        <TranCode>tranCd</TranCode>
	        <Amount>123456789.12</Amount>
            <Currency>0</Currency>
	      </Codeline>
    </Item>		
	
		
  </Items>
  <CaptureInfo> 
  
<CaptureItem>
    <JOB>
            <BusinessDate>2017-04-06</BusinessDate>
            <InstallationId>InstID</InstallationId>
            <CaptureSystemId>A</CaptureSystemId>
            <StartTime>2017-04-06T09:58:36+00:00</StartTime>
            <EndTime>2017-04-06T10:58:36+00:00</EndTime>
            <WorkTypeNbr>11</WorkTypeNbr>
            <SortFamily>123</SortFamily>
            <SourceType>ATM SOURCE TYPE</SourceType>
            <SourceName>ATM NAME OF THE SOURCE</SourceName>
            <SourceID>12</SourceID> 
            <FinancialInstitutionID>Insta1234</FinancialInstitutionID>
            <CollectionStartTime>2017-04-06T09:59:36+00:00</CollectionStartTime>
            <CollectionEndTime>2017-04-06T09:59:56+00:00</CollectionEndTime>
    </JOB>
    <BLOCK>
            <BlkNbr>BlockNum</BlkNbr> 
            <ImageType>CCITTJP</ImageType>
    </BLOCK>
    <BATCH>
            <TktTc>BA</TktTc>
            <TktAn>AAAAAAAAAABBB</TktAn> 
            <TktSC>123456</TktSC>
            <TktAux>AAAAAAAAAABBBBBBBBBB</TktAux>
            <BatchCreditCount>12345</BatchCreditCount> 
            <BatchDebitCount>12340</BatchDebitCount>
            <BatchCreditAmount>1234567890.11</BatchCreditAmount>
            <BatchDebitAmount>1234567890.22</BatchDebitAmount>
     </BATCH>
      
            <APGDIN>APGDIN</APGDIN>
			<APGBusinessDate>2017-04-06T12:03:45+00:00</APGBusinessDate>
            <Gender>Cr</Gender> 
            <TransactionNumber>TrNum</TransactionNumber> 
            <IsElectronic>1</IsElectronic> 
            <IsOnUs>0</IsOnUs> 
            <IsDeleted>1</IsDeleted> 
            <IsCorrected>0</IsCorrected>
            <IsAmountCorrected>1</IsAmountCorrected>
			<OriginalAmount>100.12</OriginalAmount>
            <IsTCCorrected>0</IsTCCorrected>
            <IsANCorrected>0</IsANCorrected> 
            <IsSortCodeCorrected>true</IsSortCodeCorrected>
            <IsSerialCorrected>0</IsSerialCorrected> 
            <IsReject>0</IsReject> 
            <RejectReason>999</RejectReason> 
            <SpSelector>ABCD</SpSelector> 
            <Currency>GBP</Currency> 
            <AdjustmentReason>99</AdjustmentReason> 
            <Narrative>Narrative Comments</Narrative>
			<OperatorId>OperatorId</OperatorId>
			<ProcessId>Process</ProcessId>
			<ItemId>ITMID2</ItemId> 
            <OriginalIsn>AAAAAAAAAABB</OriginalIsn> 
            <AeStatus>AAAAAAAAAABBBBBBBBBBCCC</AeStatus> 
            <IcStatus>AAAAAAAAAABBBBBBBBBBCCC</IcStatus>
            <IqvStatus>AAAAAAAAAABBBB</IqvStatus> 
            <CarSetId>123</CarSetId>
            <CarResult>AAAAAAAAAAB</CarResult> 
            <IaStatus>AAAAAAAAAAB</IaStatus> 
            <IaResult>AAAAAAAAAAB</IaResult> 
            <PNVReviewStatus>1</PNVReviewStatus> 
            <DuplicateStatus>9</DuplicateStatus>
            <ReturnReason>12</ReturnReason> 
            <ChequeIssuedDate>2017-04-06T07:58:36+00:00</ChequeIssuedDate>
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

              SEND ON CONVERSATION @h MESSAGE TYPE [05MA02Message](@XMLString)
