DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
  <ICN>     
    <Core>
      <BusinessDate>2017-06-01</BusinessDate>
      <ExtractId>XF8QUW2017051906MA03000001</ExtractId>
      <ProcessingParticipantId>010005</ProcessingParticipantId>
      <ExtMessageType>MSG06</ExtMessageType>
      <IntMessageType>06MA03</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>4</RecordCounts>
    </Core>
    <Entities>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>GHI1DE231067890f1GHi2JkLm</EntityId>
        <StateRevision>139001</StateRevision>
        <EntityState>521</EntityState>
        <SourceDateTime>2017-05-19T16:20:50+01:00</SourceDateTime>
      </Entity>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>GHI1DE231067890f1GHi2JkLa</EntityId>
        <StateRevision>139001</StateRevision>
        <EntityState>520</EntityState>
        <SourceDateTime>2017-05-19T16:20:50+01:00</SourceDateTime>
      </Entity>
    </Entities>
    <FraudResponse>
      <CreationDateTime>2017-05-19T16:20:50+01:00</CreationDateTime>
      <NumberofEntries>9655791204</NumberofEntries>
      <FraudResponseType>MSF06</FraudResponseType>
      <FraudStatusTransactionSets>
        <TransactionSetId>2A2BCD123456789623456701</TransactionSetId>
        <FraudItemResults>
          <ItemId>GHI1DE231067890f1GHi2JkLm</ItemId>
          <FraudCheckResult>OK</FraudCheckResult>
          <FraudCheckReason>ICY</FraudCheckReason>
        </FraudItemResults>
        <FraudItemResults>
          <ItemId>GHI1DE231067890f1GHi2JkLa</ItemId>
          <FraudCheckResult>Fraudulent</FraudCheckResult>
          <FraudCheckReason>UIY</FraudCheckReason>
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

              SEND ON CONVERSATION @h MESSAGE TYPE [06MA03Message](@XMLString)