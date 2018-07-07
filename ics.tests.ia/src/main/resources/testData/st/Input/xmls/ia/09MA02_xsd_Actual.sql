DECLARE @A AS XML(BASE.[09MA02])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">


<ICN>
	<Core>
      <BusinessDate>2016-08-22</BusinessDate>
      <ExtractId>2094995283294654LPUT442462</ExtractId>
      <ProcessingParticipantId>300000</ProcessingParticipantId>
      <ExtMessageType>MSG09</ExtMessageType>
      <IntMessageType>09MA01</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>12345678</RecordCounts>                       
	</Core>
	<Entities>
		<Entity>
			<EntityType>I</EntityType>
			<EntityId>1732767488909303GZVWT36</EntityId>
			<StateRevision>11</StateRevision>
			<EntityState>600</EntityState>
			<SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
			
		</Entity>
	</Entities>
</ICN>

</Document>'

