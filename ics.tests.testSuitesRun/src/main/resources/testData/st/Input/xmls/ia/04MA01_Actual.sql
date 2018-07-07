DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
 
  <ICN>
    <Core>
      <BusinessDate>2017-01-27</BusinessDate>
      <ExtractId>ExID</ExtractId>
      <ProcessingParticipantId>OOHVII</ProcessingParticipantId>
      <ExtMessageType>MSG04</ExtMessageType>
      <IntMessageType>04MA01</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>3</RecordCounts>
    </Core>
    <Entities>
	<Entity>
        <EntityType>I</EntityType>
        <EntityId>EntID</EntityId>
        <StateRevision>27001</StateRevision>
        <EntityState>20</EntityState>
        <SourceDateTime>2016-11-04T14:11:42+00:00</SourceDateTime>
      </Entity>
    </Entities>
	<Items>
     <Item>
         <ItemId>ITMID</ItemId>
        
         <AuditRevision>123457</AuditRevision>
         <Gender>Db</Gender>		    
		<ResponseWindow>
			<Day2ResponseWindowStartDateTime>2016-11-08T07:58:36+00:00</Day2ResponseWindowStartDateTime>
			<Day2ResponseWindowEndDateTime>2016-11-08T07:58:38+00:00</Day2ResponseWindowEndDateTime>
		</ResponseWindow>
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

              SEND ON CONVERSATION @h MESSAGE TYPE [04MA01Message](@XMLString)