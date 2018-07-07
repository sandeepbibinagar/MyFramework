DECLARE @A AS XML(BASE.[PRMA01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">

	<ICN>
		<Core>
			<BusinessDate>2017-11-04</BusinessDate>
			<ExtractId>20161104090909090909090909</ExtractId>
			<ProcessingParticipantId>000001</ProcessingParticipantId>
			<ExtMessageType>PSTNG</ExtMessageType>
			<IntMessageType>PRMA01</IntMessageType>
			<MessageSource>MO</MessageSource>
			<MessageDestination>IA</MessageDestination>
			<RecordCounts>1</RecordCounts>
		</Core>
		<Entities>
			
			<Entity>
				<EntityType>T</EntityType>
				<EntityId>UCTAPQ162784331AAAAAAAAAA</EntityId>
				<StateRevision>888999</StateRevision>
				<EntityState>589</EntityState>
				<SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
			</Entity>
		</Entities>
		<PostingUpdate>
			<PostingResponse>
				<FileId>123456789012345678901234567890ASD</FileId>
				<FileSequenceNumber>1234</FileSequenceNumber>
				<Source>1234</Source>
				<FileDateTime>1234567890ASDFG1234</FileDateTime>
				<ExtractItemCount>1234567</ExtractItemCount>
				<PostingResponseRecord>
					<ResponseSequence>1234567</ResponseSequence>
					<ItemId>123456789012345ITMID12555</ItemId>
					<PostingType>AS1</PostingType>
					<Account>12345678</Account>
					<SortCode>123456</SortCode>
					<NPAAccount>12345678</NPAAccount>
					<NPASortCode>123456</NPASortCode>	
					<Amount>12345678901.12</Amount>
					<RedirectionInd>A</RedirectionInd>
					<AccountingSystem>ASDF</AccountingSystem>
					<ResponseStatus>A</ResponseStatus>
					<ResponseSubType>A</ResponseSubType>
					<ResponseStatusCount>123</ResponseStatusCount>
					<AggregationCount>9999</AggregationCount>
					<ResponseDetail>
						<RicherDataRef>qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890qwertyuiop1234567890</RicherDataRef>
						<FraudStatusCode>AS12</FraudStatusCode>
						<FraudReasonCode>AS39</FraudReasonCode>
						<CreditReference>1234567890qwert123</CreditReference>
						<Serial>123456</Serial>
					</ResponseDetail>
					<StatusRecords>
						<StatusSequence>123</StatusSequence>
						<ReasonCode>ABCVFD</ReasonCode>
						<ReasonText>qwertyuiop1234567890qwertyuiop1234567890</ReasonText>
					</StatusRecords>
	   </PostingResponseRecord>
   </PostingResponse>
</PostingUpdate>
                    
</ICN>

</Document>'