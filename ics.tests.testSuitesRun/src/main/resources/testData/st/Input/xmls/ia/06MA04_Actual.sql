DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML

SET @XMLString = 
'<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
  <Core>
      <BusinessDate>2016-11-04</BusinessDate>
      <ExtractId>ExID</ExtractId>
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
				<EntityId>EntID</EntityId>
				<StateRevision>112</StateRevision>
				<EntityState>560</EntityState>
				<SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
			</Entity>
	</Entities>
  <Items>
  	

	  	<Item>
      <ItemId>ITMID</ItemId>
      <ProcessId>00156process1</ProcessId>
      <OperatorId>123456opsid</OperatorId>
      <UpdateDateTime>2016-07-07T07:58:36+00:00</UpdateDateTime>
      <AuditRevision>210000</AuditRevision>
	  <Gender>Db</Gender>
	<PayDecision>
          <PayDecision>true</PayDecision>
          <PayDecisionReasonCode>prdc</PayDecisionReasonCode>         
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

