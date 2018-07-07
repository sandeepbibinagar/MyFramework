DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
 
  <ColltngFrdNtfctn>
    <GrpHdr>
      <MsgId>99999917250C:0503938157</MsgId>
      <CreDtTm>2017-10-05T16:31:43+01:00</CreDtTm>
      <NbOfTxs>1</NbOfTxs>
      <RcvrId>000004</RcvrId>
      <TstInd>true</TstInd>
      <Sgntr>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</Sgntr>
    </GrpHdr>
    <TxSet>
      <TxSetId>SBZT001700817890000001</TxSetId>
      <TxSetVrsn>01</TxSetVrsn>
      <ColltngPtcptId>000004</ColltngPtcptId>
      <CaptrdDtTm>2017-10-05T16:31:43+01:00</CaptrdDtTm>
      <TxSetSubDtTm>2017-10-05T16:31:43+01:00</TxSetSubDtTm>
      <Src>1856</Src>
      <NbOfItms>1</NbOfItms>
      <DbtItm>
        <DbtItmId>SBZD011700856789000000001</DbtItmId>
        <EndPtId>UOTGGY</EndPtId>
        <DbtItmTp>IODI</DbtItmTp>
        <DbtItmTxCd>02</DbtItmTxCd>
        <Amt Ccy="GBP">100.12</Amt>
        <BkCd>977289</BkCd>
        <AcctNb>82891713</AcctNb>
        <SrlNb>083052</SrlNb>
        <FrdStsCd>FRDL</FrdStsCd>
        <FrdRsnCd>F180</FrdRsnCd>
        <RprdItm>
          <BkCdRprdInd>true</BkCdRprdInd>
          <AcctNbRprdInd>false</AcctNbRprdInd>
          <AmtRprdInd>false</AmtRprdInd>
          <SrlNbRprdInd>true</SrlNbRprdInd>
          <RefNbRprdInd>true</RefNbRprdInd>
        </RprdItm>
      </DbtItm>
    </TxSet>
  </ColltngFrdNtfctn>
  <ICN>
    <Core>
      <BusinessDate>2017-10-06</BusinessDate>
      <ExtractId>ExID</ExtractId>
      <ProcessingParticipantId>000004</ProcessingParticipantId>
      <ExtMessageType>MSG16</ExtMessageType>
      <IntMessageType>16MA01</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>1</RecordCounts>
    </Core>
    <Entities>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>EntID</EntityId>
        <StateRevision>279003</StateRevision>
        <EntityState>180</EntityState>
      	<SourceDateTime>2016-11-07T07:58:36+00:00</SourceDateTime>
      
</Entity>
    </Entities>
    <FraudResponse>
      <CreationDateTime>2017-10-05T16:31:43+00:00</CreationDateTime>
      <NumberofEntries>1</NumberofEntries>
      <FraudStatusTransactionSets>
        <TransactionSetId>SBZT00170081789000000101</TransactionSetId>
        <FraudItemResults>
          <ItemId>ITMID</ItemId>
          <FraudCheckResult>FRDL</FraudCheckResult>
          <FraudCheckReason>F180</FraudCheckReason>
        </FraudItemResults>
      </FraudStatusTransactionSets>
    </FraudResponse>
  </ICN>
</Document>
'
    		  BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [16MA01Message](@XMLString)

