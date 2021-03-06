DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
	<Core>
      <BusinessDate>2016-08-22</BusinessDate>
      <ExtractId>ExID</ExtractId>
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
			<EntityId>EntID</EntityId>
			<StateRevision>11</StateRevision>
			<EntityState>600</EntityState>
			<SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
			<EntityError>
				<ErrorCode>ZTTZ</ErrorCode>
				<ErrorDescription>DWHPWVVUZNXWWFFJCRMCUHVGKRYOMIOCUGMLNCKMUTPBQGEEQMBIHRIUQAEEXHIRYVBQTUWZGAHASUQENTXZGFVDYRUNYFPNKYCUENLWPCBGJHCVJZRDKIKSZYRBZJBBLUQUSVAZIBKEEOTRPZDYOLCBNJUGWJWGBAVOQIDNZSVJYGOLEAMAFCMZROGIBUDYEDCDSEAHVPZWQTCGUJBVXWAYNWXCBUMFOWEVDLSZAFEQDQJXMANRSHBHCDPWHYF</ErrorDescription>
				
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

              SEND ON CONVERSATION @h MESSAGE TYPE [09MA01Message](@XMLString)
