DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
 
  <TxSetSubmissnRpt>
    <GrpHdr>
      <MsgId>99999917058C:0364022245</MsgId>
      <CreDtTm>2017-09-27T17:15:24Z</CreDtTm>
      <NbOfTxs>1</NbOfTxs>
      <RcvrId>000004</RcvrId>
      <TstInd>true</TstInd>
      <Sgntr>UjBsR09EbGhjZ0dTQUxNQUFBUUNBRU1tQ1p0dU1GUXhEUzhi</Sgntr>
    </GrpHdr>
    <SubmissnInf>
      <MsgId>00000417282D:0000000048</MsgId>
      <NbOfTxSetsTtl>1</NbOfTxSetsTtl>
      <NbOfTxSetsAccptd>1</NbOfTxSetsAccptd>
      <NbOfTxSetsRjctd>0</NbOfTxSetsRjctd>
    </SubmissnInf>
  </TxSetSubmissnRpt>
  <ICN>
    <Core>
      <BusinessDate>2017-10-09</BusinessDate>
      <ExtractId>ExID</ExtractId>
      <ProcessingParticipantId>000004</ProcessingParticipantId>
      <ExtMessageType>MSG15</ExtMessageType>
      <IntMessageType>15MA01</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>1</RecordCounts>
    </Core>
    <Entities>
      <Entity>
        <EntityType>D</EntityType>
        <EntityId>EntID</EntityId>
        <StateRevision>282002</StateRevision>
        <EntityState>170</EntityState>
     <SourceDateTime>2016-11-07T07:58:36+00:00</SourceDateTime>
      </Entity>
    </Entities>
  </ICN>
</Document>
'
    		  BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [15MA01Message](@XMLString)

