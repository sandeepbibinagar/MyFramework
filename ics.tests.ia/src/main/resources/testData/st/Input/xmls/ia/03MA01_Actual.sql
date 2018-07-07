DECLARE @h UNIQUEIDENTIFIER
DECLARE @XMLString XML

SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns= "urn:xsd:ipsl.ics">
<ICN>
		<Core>
			<BusinessDate>2016-10-07</BusinessDate>
			<ExtractId>ExID</ExtractId>
			<ProcessingParticipantId>300000</ProcessingParticipantId>
			<ExtMessageType>MSG03</ExtMessageType>
			<IntMessageType>03MA01</IntMessageType>
			<MessageSource>MO</MessageSource>  
			<MessageDestination>IA</MessageDestination>
			<RecordCounts>2</RecordCounts>
		</Core>
		<Entities>
		<Entity>
			<EntityType>T</EntityType>
			<EntityId>EntID</EntityId>
			<StateRevision>211</StateRevision>
			<EntityState>600</EntityState>
			<SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
			<EntityError>
				<ErrorCode>ZTTZ</ErrorCode>
				<ErrorDescription>CMHPWVVUZNXWWFFJCRMCUHVGKRYOMIOCUGMLNCKMUTPBQGEEQMBIHRIUQAEEXHIRYVBQTUWZGAHASUQENTXZGFVDYRUNYFPNKYCUENLWPCBGJHCVJZRDKIKSZYRBZJBBLUQUSVAZIBKEEOTRPZDYOLCBNJUGWJWGBAVOQIDNZSVJYGOLEAMAFCMZROGIBUDYEDCDSEAHVPZWQTCGUJBVXWAYNWXCBUMFOWEVDLSZAFEQDQJXMANRSHBHCDPWHYF</ErrorDescription>
				
			</EntityError>
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

SEND ON CONVERSATION @h MESSAGE TYPE [03MA01Message](@XMLString)