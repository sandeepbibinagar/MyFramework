DECLARE @A AS XML(BASE.[13MA04])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">


	
<ICN>
  <Core>
      <BusinessDate>2016-11-04</BusinessDate>
      <ExtractId>9460761092447811XNVD796872</ExtractId>
      <ProcessingParticipantId>300000</ProcessingParticipantId>
      <ExtMessageType>MSG13</ExtMessageType>
      <IntMessageType>13MA04</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>1</RecordCounts>
  </Core>
	<Entities>
		
			<Entity>
				<EntityType>I</EntityType>
				<EntityId>1348539509566587VQRUM21</EntityId>
				<StateRevision>112</StateRevision>
				<EntityState>560</EntityState>
				<SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
			</Entity>
	</Entities>
  <Items>
  	

	  	<Item>
      <ItemId>4403588293927582KJHGN3899</ItemId>
      <ProcessId>00156process1</ProcessId>
      <OperatorId>123456opsid</OperatorId>
      <UpdateDateTime>2016-07-07T07:58:36+00:00</UpdateDateTime>
      <AuditRevision>210000</AuditRevision>
	  <Gender>Db</Gender>
      
    </Item>
  </Items> 
</ICN>


</Document>'

