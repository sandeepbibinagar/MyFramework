DECLARE @A AS XML(BASE.[06MA04])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
<ICN>
  <Core>
      <BusinessDate>2016-10-07</BusinessDate>
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
           <EntityId>UCTAPQ162784331RAAJESHSRE</EntityId>
           <StateRevision>101113</StateRevision>
           <EntityState>540</EntityState>
           <SourceDateTime>2016-10-07T07:58:36+00:00</SourceDateTime>
       </Entity>
  </Entities>
  <Items>
   <Item>
      <ItemId>UCTAPQ162784331RAAJESHSRE</ItemId>
      <ProcessId>00156process1</ProcessId>
      <OperatorId>123456opsid</OperatorId>
      <UpdateDateTime>2016-07-07T07:58:36+00:00</UpdateDateTime>
      <AuditRevision>100115</AuditRevision>
	  <Gender>Db</Gender>
      <PayDecision>
          <PayDecision>0</PayDecision>
          <PayDecisionReasonCode>11</PayDecisionReasonCode>         
      </PayDecision>
    </Item>
  </Items> 
</ICN>


</Document>'

