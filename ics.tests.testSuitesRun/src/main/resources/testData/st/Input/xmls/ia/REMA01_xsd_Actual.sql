DECLARE @A AS XML(BASE.[REMA01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
 
  <ICN>     
   <Core>
     <BusinessDate>2017-07-10</BusinessDate>
     <ExtractId>SANGEESRE09876543212345699</ExtractId>
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
        <EntityId>CRSANT162004331RAJESHCR99</EntityId>
        <StateRevision>200001</StateRevision>
        <EntityState>20</EntityState>
        <SourceDateTime>2016-11-07T07:58:36+00:00</SourceDateTime>
       
</Entity>
  </Entities>
  <Items>
    <Item>
         <ItemId>CRSANT162004331RAJESHCR9D</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2016-11-07T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>123457</AuditRevision>
    </Item>
  </Items>
  </ICN>
</Document>'