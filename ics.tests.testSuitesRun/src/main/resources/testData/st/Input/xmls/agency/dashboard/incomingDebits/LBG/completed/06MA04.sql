DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
  <ICN>
    <Core>
      <BusinessDate>2017-06-01</BusinessDate>
      <ExtractId>HOXZBR2017051906MA04000001</ExtractId>
      <ProcessingParticipantId>000632</ProcessingParticipantId>
      <ExtMessageType>MSG06</ExtMessageType>
      <IntMessageType>06MA04</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>5</RecordCounts>
    </Core>
    <Entities>
      <Entity>
	  <EntityType>I</EntityType>
        <EntityId>GHI1DE231067890f1GHi2JkLm</EntityId>
        <StateRevision>139006</StateRevision>
        <EntityState>560</EntityState>
        <SourceDateTime>2017-05-23T16:20:59+01:00</SourceDateTime>
      </Entity>
      <Entity>
	 <EntityType>I</EntityType>
        <EntityId>GHI1DE231067890f1GHi2JkLa</EntityId>
        <StateRevision>139007</StateRevision>
        <EntityState>550</EntityState>
        <SourceDateTime>2017-05-23T16:20:59+01:00</SourceDateTime>
      </Entity>
    </Entities>
    <Items>
      <Item>
        <ItemId>GHI1DE231067890f1GHi2JkLm</ItemId>
        <ProcessId>VUXBZHYAAYRSBIWUWXZZJBJII</ProcessId>
        <OperatorId>YWSYOPYESIXGZIEAKWPH</OperatorId>
        <UpdateDateTime>2017-05-23T16:20:59+01:00</UpdateDateTime>
        <AuditRevision>261575</AuditRevision>
        <Gender>Db</Gender>
        <PayDecision>
          <PayDecision>false</PayDecision>
          <PayDecisionReasonCode>OYTL</PayDecisionReasonCode>
        </PayDecision>
      </Item>
	  <Item>
        <ItemId>GHI1DE231067890f1GHi2JkLa</ItemId>
        <ProcessId>VUXBZHYAAYRSBIWUWXZZJBJII</ProcessId>
        <OperatorId>YWSYOPYESIXGZIEAKWPH</OperatorId>
        <UpdateDateTime>2017-05-23T16:20:59+01:00</UpdateDateTime>
        <AuditRevision>261575</AuditRevision>
        <Gender>Db</Gender>
        <PayDecision>
          <PayDecision>true</PayDecision>
          <PayDecisionReasonCode>OYTL</PayDecisionReasonCode>
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