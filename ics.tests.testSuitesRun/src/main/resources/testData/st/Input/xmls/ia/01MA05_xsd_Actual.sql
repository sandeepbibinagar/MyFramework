DECLARE @A AS XML(BASE.[01MA05])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
<ICN>
  <Core>
      <BusinessDate>2016-01-12</BusinessDate>
      <ExtractId>2644981496640221HMAO230139</ExtractId>
      <ProcessingParticipantId>300000</ProcessingParticipantId>
      <ExtMessageType>MSG01</ExtMessageType>
      <IntMessageType>01MA05</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>1</RecordCounts>
  </Core>
	<Entities>
		
			<Entity>
				<EntityType>I</EntityType>
				<EntityId>9444162369569971SSSSX89</EntityId>
				<StateRevision>112</StateRevision>
				<EntityState>560</EntityState>
				<SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
			</Entity>
	</Entities>
  
</ICN>
</Document>'