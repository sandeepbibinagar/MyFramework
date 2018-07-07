DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML

SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
  <ICN>     
   <Core>
     <BusinessDate>2017-07-10</BusinessDate>
     <ExtractId>ExID</ExtractId>
     <ProcessingParticipantId>ABC001</ProcessingParticipantId>
     <ExtMessageType>REMA01</ExtMessageType>
     <IntMessageType>REMA01</IntMessageType>
     <MessageSource>MO</MessageSource>
     <MessageDestination>IA</MessageDestination>
     <RecordCounts>23</RecordCounts>
   </Core>
   <Entities> 
	 <Entity>
        <EntityType>I</EntityType>
        <EntityId>EntID</EntityId>
        <StateRevision>200001</StateRevision>
        <EntityState>20</EntityState>
        <SourceDateTime>2016-11-07T07:58:36+00:00</SourceDateTime>
       
</Entity>
  </Entities>
  <Items>
    <Item>
         <ItemId>ITMID</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2016-11-07T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>123457</AuditRevision>
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

              SEND ON CONVERSATION @h MESSAGE TYPE [REMA01Message](@XMLString)
              