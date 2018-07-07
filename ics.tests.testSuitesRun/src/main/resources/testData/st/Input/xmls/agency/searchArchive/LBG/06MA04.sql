DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML

SET @XMLString = 
'<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
  <Core>
      <BusinessDate>2017-05-31</BusinessDate>
      <ExtractId>99999999909876543212345677</ExtractId>
      <ProcessingParticipantId>300000</ProcessingParticipantId>
      <ExtMessageType>MSG06</ExtMessageType>
      <IntMessageType>06MA04</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>1</RecordCounts>
  </Core>
	<Entities>
		<Entity>
				<EntityType>I</EntityType>
				<EntityId>AL87ME1704421222803037301</EntityId>
				<StateRevision>400111</StateRevision>
				<EntityState>550</EntityState>
				<SourceDateTime>2016-06-22T00:20:59+01:00</SourceDateTime>
			</Entity>
		
	</Entities>
  <Items>
  	<Item>
      <ItemId>AL87ME1704421222803037301</ItemId>
      <ProcessId>00156process1</ProcessId>
      <OperatorId>123456opsid</OperatorId>
      <UpdateDateTime>2016-07-07T07:58:36+00:00</UpdateDateTime>
      <AuditRevision>210000</AuditRevision>
	  <Gender>Db</Gender>
      <PayDecision>
          <PayDecision>true</PayDecision>
          <PayDecisionReasonCode>11</PayDecisionReasonCode>         
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

              SEND ON CONVERSATION @h MESSAGE TYPE [06MA04Message](@XMLString)

