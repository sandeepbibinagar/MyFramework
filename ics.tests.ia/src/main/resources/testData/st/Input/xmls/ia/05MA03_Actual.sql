DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
	<ICN>
		<Core>
			<BusinessDate>2016-10-07</BusinessDate>
			<ExtractId>ExID</ExtractId>
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
				<EntityId>EntID</EntityId>
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
					<ItemId>ITMID1</ItemId>
					<FraudCheckResult>OK</FraudCheckResult>
					<!--	<FraudCheckReason>A1G</FraudCheckReason> -->
				</FraudItemResults>
				<FraudItemResults>
					<ItemId>ITMID2</ItemId>
					<FraudCheckResult>Not Processed</FraudCheckResult>
					<!-- <FraudCheckReason>A1G</FraudCheckReason> -->
				</FraudItemResults>
			</FraudStatusTransactionSets>
		</FraudResponse>
		</ICN>
</Document>
'
              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [05MA03Message](@XMLString)
