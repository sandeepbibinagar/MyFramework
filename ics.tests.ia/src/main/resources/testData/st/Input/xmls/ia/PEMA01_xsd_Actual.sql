DECLARE @A AS XML(BASE.[PEMA01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
<ICN>
	<Core>
      <BusinessDate>2016-08-12</BusinessDate>
      <ExtractId>1234567890987654321test123</ExtractId>
      <ProcessingParticipantId>300000</ProcessingParticipantId>
      <ExtMessageType>PSTNG</ExtMessageType>
      <IntMessageType>PEMA01</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>12345678</RecordCounts>                       
	</Core>
	<Entities>
		<Entity>
			<EntityType>I</EntityType>
			<EntityId>DSHOPQ162784331AAAAAAAA01</EntityId>
			<StateRevision>111001</StateRevision>
			<EntityState>520</EntityState>
			<SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
		</Entity>
	</Entities>
</ICN>
</Document>'