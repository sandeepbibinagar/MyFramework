DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
  <ICN>
    <Core>
      <BusinessDate>2016-11-04</BusinessDate>
      <ExtractId>ExID</ExtractId>
      <ProcessingParticipantId>300000</ProcessingParticipantId>
      <ExtMessageType>MSG12</ExtMessageType>
      <IntMessageType>12MA01</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>30000000</RecordCounts>
    </Core>
  <Entities>
   <Entity>
        <EntityType>I</EntityType>
        <EntityId>EntID</EntityId>
        <StateRevision>444</StateRevision>
        <EntityState>661</EntityState>
        <SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
      </Entity>
	  
    </Entities>
  <Items>
   
 
	<Item>
        <ItemId>ITMID</ItemId>
        <AuditRevision>153417</AuditRevision>
        <Gender>Db</Gender>
	<PayDecision>
          <PayDecision>true</PayDecision>
          <PayDecisionReasonCode>pdrc</PayDecisionReasonCode>         
      </PayDecision>
	</Item>
  </Items>
  </ICN>
</Document>
'
       
              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [12MA01Message](@XMLString)
