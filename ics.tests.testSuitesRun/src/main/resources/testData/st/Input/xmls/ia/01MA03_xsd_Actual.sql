DECLARE @A AS XML(BASE.[01MA03])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
 

	<ICN>
		<Core>
			<BusinessDate>2016-10-07</BusinessDate>
			<ExtractId>5747254535178185VBJI488881</ExtractId>
			<ProcessingParticipantId>300000</ProcessingParticipantId>
			<ExtMessageType>MSF06</ExtMessageType>
			<IntMessageType>01MA03</IntMessageType>
			<MessageSource>MO</MessageSource>  
			<MessageDestination>IA</MessageDestination>
			<RecordCounts>2</RecordCounts>
		</Core>
		<Entities>
			<Entity>
				<EntityType>I</EntityType>                  
				<EntityId>4983549590777713MOBJY57</EntityId>
				<StateRevision>111</StateRevision>
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
					<ItemId>6771071366897561AUEDT0879</ItemId>
					<FraudCheckResult>OK</FraudCheckResult>
					<FraudCheckReason>A1G</FraudCheckReason>
				<FraudAdditionalInfo>
				<FirstChequeDate>2017-01-12</FirstChequeDate>
				<LastChequeDate>2017-01-12</LastChequeDate>
				<CounterpartiesCount>1</CounterpartiesCount>
				<GoodChequesCount>1111</GoodChequesCount>
				<FraudChequesCount>1111</FraudChequesCount>
				<LargestAmount>100000000.23</LargestAmount>
			   	<RiskIndicator>1234</RiskIndicator>
			</FraudAdditionalInfo>
				</FraudItemResults>
				<FraudItemResults>
					<ItemId>6942908631196503QGQXY9206</ItemId>
					<FraudCheckResult>Not Processed</FraudCheckResult>
					<FraudCheckReason>A1G</FraudCheckReason>
			<FraudAdditionalInfo>
				<FirstChequeDate>2017-01-12</FirstChequeDate>
				<LastChequeDate>2017-01-12</LastChequeDate>
				<CounterpartiesCount>1</CounterpartiesCount>
				<GoodChequesCount>1111</GoodChequesCount>
				<FraudChequesCount>1111</FraudChequesCount>
				<LargestAmount>100000000.23</LargestAmount>
			   	<RiskIndicator>1234</RiskIndicator>
			</FraudAdditionalInfo>
				</FraudItemResults>
			</FraudStatusTransactionSets>
		</FraudResponse>
	</ICN>


</Document>'