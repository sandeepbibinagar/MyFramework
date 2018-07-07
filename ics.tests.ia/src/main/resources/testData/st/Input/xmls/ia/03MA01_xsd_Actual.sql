DECLARE @A AS XML(BASE.[03MA01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
<ICN>
		<Core>
			<BusinessDate>2016-10-07</BusinessDate>
			<ExtractId>7642776277821528NUYB235277</ExtractId>
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
			<EntityId>2494372913626954JDZEG553</EntityId>
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
</Document>'