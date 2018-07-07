DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
	<ICN>
		<Core>
			<BusinessDate>2016-10-07</BusinessDate>
			<ExtractId>ExID</ExtractId>
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
				<EntityId>EntID</EntityId>
				<StateRevision>111</StateRevision>
				<EntityState>250</EntityState>
				<SourceDateTime>2016-10-07T07:58:36+00:00</SourceDateTime>
			</Entity>
		</Entities>
		<CaseManagement>
			<CloseCase>
				<CaseId>ClosedCaseID</CaseId>
			</CloseCase>
		</CaseManagement>
	</ICN>
</Document>
'
              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [CMMA02Message](@XMLString)
