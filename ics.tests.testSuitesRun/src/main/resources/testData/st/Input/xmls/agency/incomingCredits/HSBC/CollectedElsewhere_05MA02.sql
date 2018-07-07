DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML

SET @XMLString = '  <Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
 <ICN>     
   <Core>
     <BusinessDate>2017-05-28</BusinessDate>
     <ExtractId>12345678909876543212345677</ExtractId>
     <ProcessingParticipantId>000632</ProcessingParticipantId>
     <ExtMessageType>MSG05</ExtMessageType>
     <IntMessageType>05MA02</IntMessageType>
     <MessageSource>MO</MessageSource>
     <MessageDestination>IA</MessageDestination>
     <RecordCounts>03</RecordCounts>
   </Core>
   <Entities> 
    <Entity>
         <EntityType>T</EntityType>                                  
         <EntityId>49DSUT163099211645661363</EntityId>
         <StateRevision>200000</StateRevision>
         <EntityState>10</EntityState>
         <SourceDateTime>2017-04-27T07:58:36+00:00</SourceDateTime>
      </Entity>
       <Entity>
         <EntityType>I</EntityType>                                  
		<EntityId>AUT7ME1704421222803037800</EntityId>
		<StateRevision>200023</StateRevision>
		<EntityState>250</EntityState>
		<SourceDateTime>2016-10-07T07:58:36+00:00</SourceDateTime>
      </Entity>
  </Entities>
  <Items>
    <Item>
        <ItemId>AUT7ME1704421222803037800</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2017-04-25T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>223457</AuditRevision>
         <Gender>Cr</Gender>
         <NoPayReason>nopay</NoPayReason>
         <Codeline>
            <Reference>153457</Reference>
           <SortCode>154456</SortCode> 
            <Account>15344678</Account>
           <TranCode>20</TranCode>
           <Amount>101.63</Amount>
            <Currency>123</Currency>
     </Codeline>
    </Item>
  </Items>
  <CaptureInfo>
	<CaptureItem>
		<JOB>
            <BusinessDate>2017-05-28</BusinessDate>
            <InstallationId>1234567890INSTL</InstallationId>
            <CaptureSystemId>A</CaptureSystemId>
            <StartTime>2017-05-28T09:58:36+02:00</StartTime>
            <EndTime>2017-05-28T10:58:36+02:00</EndTime>
            <WorkTypeNbr>11</WorkTypeNbr>
            <SortFamily>123</SortFamily>
            <SourceType>ATM SOURCE TYPE</SourceType>
            <SourceName> ATM NAME OF THE SOURCE</SourceName>
            <SourceID>19</SourceID> 
            <FinancialInstitutionID>Insta1234</FinancialInstitutionID>
            <CollectionStartTime>2017-04-25T09:59:36+04:00</CollectionStartTime>
            <CollectionEndTime>2017-04-25T09:59:56+04:00</CollectionEndTime>
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
		<APGBusinessDate>2017-05-28T14:28:13+01:00</APGBusinessDate>
        <Gender>Cr</Gender> 
        <TransactionNumber>1234567890</TransactionNumber> 
        <IsElectronic>1</IsElectronic> 
        <IsOnUs>0</IsOnUs> 
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
		<OperatorId>testerag</OperatorId>
		<ProcessId>sasdadasdasd</ProcessId>
		<ItemId>AUT7ME1704421222803037800</ItemId>
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
        <ChequeIssuedDate>2017-05-28T09:58:36+02:00</ChequeIssuedDate>
		<ItemHistory>
			 <AuditRevision>123461</AuditRevision> 
			 <UserId>USER IDENTITY FOR THE IMAGE ARCHIVE TO BE STORED 1234567890</UserId> 
			 <Time>2017-04-25T09:58:36+02:00</Time>
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
</Document>' 
       
              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [05MA02Message](@XMLString)

