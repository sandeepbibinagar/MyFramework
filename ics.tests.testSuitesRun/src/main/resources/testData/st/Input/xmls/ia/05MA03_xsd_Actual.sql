DECLARE @A AS XML(BASE.[05MA03])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">

	<ICN>
		<Core>
			<BusinessDate>2016-10-07</BusinessDate>
			<ExtractId>1043097314384265GAWS928979</ExtractId>
			<ProcessingParticipantId>300000</ProcessingParticipantId>
			<ExtMessageType>MSG05</ExtMessageType>
			<IntMessageType>05MA03</IntMessageType>
			<MessageSource>MO</MessageSource>  
			<MessageDestination>IA</MessageDestination>
			<RecordCounts>2</RecordCounts>
		</Core>
		<Entities>
			<Entity>
				<EntityType>I</EntityType>                  
				<EntityId>2067622300045979AQQKO46</EntityId>
				<StateRevision>111444</StateRevision>
				<EntityState>250</EntityState>
				<SourceDateTime>2016-10-07T07:58:36+00:00</SourceDateTime>
			</Entity>
		</Entities>
<FraudResponse>
			<CreationDateTime>2016-06-22T00:20:59+00:00</CreationDateTime>
			<NumberofEntries>1</NumberofEntries>
			<FraudResponseType>MSF06</FraudResponseType>
			<FraudStatusTransactionSets>
				<TransactionSetId>ASHOGY162782814981023819</TransactionSetId>
				<FraudItemResults>
					<ItemId>4773163233551097FWEIE6250</ItemId>
					<FraudCheckResult>OK</FraudCheckResult>
					<!-- <FraudCheckReason>A1G</FraudCheckReason> -->
				</FraudItemResults>
				<FraudItemResults>
					<ItemId>6087282593096074CHSCI2623</ItemId>
					<FraudCheckResult>Not Processed</FraudCheckResult>
					<!-- <FraudCheckReason>A1G</FraudCheckReason> -->
				</FraudItemResults>
			</FraudStatusTransactionSets>
		</FraudResponse>
		</ICN>
            
</Document>'


