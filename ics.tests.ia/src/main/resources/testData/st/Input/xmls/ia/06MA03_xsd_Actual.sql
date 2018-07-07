DECLARE @A AS XML(BASE.[06MA03])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
 
	<ICN>
		<Core>
			<BusinessDate>2016-11-04</BusinessDate>
			<ExtractId>1849986423955623NMPJ723786</ExtractId>
			<ProcessingParticipantId>AB1234</ProcessingParticipantId>
			<ExtMessageType>MSG06</ExtMessageType>
			<IntMessageType>06MA03</IntMessageType>
			<MessageSource>MO</MessageSource>
			<MessageDestination>IA</MessageDestination>			
			<RecordCounts>1</RecordCounts>
		</Core>
		<Entities>
			
			<Entity>
				<EntityType>I</EntityType>
				<EntityId>8228914889701911OPPQC55</EntityId>
				<StateRevision>11</StateRevision>
				<EntityState>525</EntityState>
				<SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
			</Entity>
		</Entities>
		<FraudResponse>
			<CreationDateTime>2016-06-22T00:20:59+01:00</CreationDateTime>
			<NumberofEntries>1</NumberofEntries>
			<FraudResponseType>MSF06</FraudResponseType>
			<FraudStatusTransactionSets>
				<TransactionSetId>ASHOGY162782814981023800</TransactionSetId>
				<FraudItemResults>
					<ItemId>DSHOPQ162784331AAAAAAAA01</ItemId>
					<FraudCheckResult>OK</FraudCheckResult>
					<FraudCheckReason>A1G</FraudCheckReason>
				</FraudItemResults>
				<FraudItemResults>
					<ItemId>DSHOPQ162784331AAAAAAAA02</ItemId>
					<FraudCheckResult>Not Processed</FraudCheckResult>
					<FraudCheckReason>A1G</FraudCheckReason>
				</FraudItemResults>
			</FraudStatusTransactionSets>
		</FraudResponse>
	</ICN>

  

</Document>'

