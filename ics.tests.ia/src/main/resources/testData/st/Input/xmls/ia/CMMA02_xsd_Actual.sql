DECLARE @A AS XML(BASE.[CMMA02])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
		
	<ICN>
		<Core>
			<BusinessDate>2016-10-07</BusinessDate>
			<ExtractId>1596337652946518YLKP210036</ExtractId>
			<ProcessingParticipantId>300000</ProcessingParticipantId>
			<ExtMessageType>CSMGT</ExtMessageType>
			<IntMessageType>CMMA02</IntMessageType>
			<MessageSource>MO</MessageSource>  
			<MessageDestination>IA</MessageDestination>
			<RecordCounts>2</RecordCounts>
		</Core>
		<Entities>
			<Entity>
				<EntityType>I</EntityType>                  
				<EntityId>2975417803742798JVKCK40</EntityId>
				<StateRevision>111</StateRevision>
				<EntityState>250</EntityState>
				<SourceDateTime>2016-10-07T07:58:36+00:00</SourceDateTime>
			</Entity>
		</Entities>
		<CaseManagement>
			<CloseCase>
				<CaseId>12345678901234</CaseId>
			</CloseCase>
		</CaseManagement>
	</ICN>





</Document>'

