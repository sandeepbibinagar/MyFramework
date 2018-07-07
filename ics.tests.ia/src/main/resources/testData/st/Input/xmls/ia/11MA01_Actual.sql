DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
	<ICN>
		<Core>
			<BusinessDate>2017-03-01</BusinessDate>
			<ExtractId>ExID</ExtractId>
			<ProcessingParticipantId>300000</ProcessingParticipantId>
			<ExtMessageType>MSG11</ExtMessageType>
			<IntMessageType>11MA01</IntMessageType>
			<MessageSource>MO</MessageSource>  
			<MessageDestination>IA</MessageDestination>
			<RecordCounts>19809</RecordCounts>
		</Core>
		<Entities>
			<Entity>
				<EntityType>I</EntityType>                                  
				 <EntityId>EntID</EntityId>
				<StateRevision>100003</StateRevision>
				<EntityState>250</EntityState>
				<SourceDateTime>2017-03-03T07:58:36+00:00</SourceDateTime>
			</Entity>
			
			
		</Entities>
	</ICN>
</Document>
'
              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [11MA01Message](@XMLString)
