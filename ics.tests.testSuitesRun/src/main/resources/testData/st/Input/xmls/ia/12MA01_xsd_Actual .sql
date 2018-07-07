DECLARE @A AS XML(BASE.[12MA01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">


  
  <ICN>
    <Core>
      <BusinessDate>2016-11-04</BusinessDate>
      <ExtractId>2020631876781540EHNU791187</ExtractId>
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
        <EntityId>4083814507962493XYOSC04</EntityId>
        <StateRevision>444</StateRevision>
        <EntityState>661</EntityState>
        <SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
      </Entity>
    </Entities>
  <Items>
  	<Item>
        <ItemId>5862525673936070LJLIN5890</ItemId>
        <AuditRevision>153417</AuditRevision>
        <Gender>Db</Gender>
	<PayDecision>
          <PayDecision>true</PayDecision>
          <PayDecisionReasonCode>PdRc</PayDecisionReasonCode>         
      </PayDecision>
	</Item>
  </Items>
  </ICN>


</Document>'

