DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:cccc:tech:xsd:ics.doc.001">
	<ICN>
		<Core>
			<BusinessDate>20170601</BusinessDate>
			<ExtractId>AB12342016062206MA03000001</ExtractId>
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
				<EntityId>UCTAPQ162784331RAAJESHSRE</EntityId>
				<StateRevision>100112</StateRevision>
				<EntityState>520</EntityState>
				<SourceDateTime>2016-06-22T00:20:59+01:00</SourceDateTime>
			</Entity>
			<Entity>
				<EntityType>I</EntityType>
				<EntityId>FCATA6162787138RAAJESHSRE</EntityId>
				<StateRevision>100112</StateRevision>
				<EntityState>520</EntityState>
			    <SourceDateTime>2016-11-07T07:58:36+00:00</SourceDateTime>
			</Entity>  
		</Entities>
		<FraudResponse>
			<CreationDateTime>2016-06-22T00:20:59+01:00</CreationDateTime>
			<NumberofEntries>1</NumberofEntries>
			<FraudResponseType>MSF06</FraudResponseType>
			<FraudStatusTransactionSets>
				<TransactionSetId>RAJESH162782814981023988</TransactionSetId>
				<FraudItemResults>
					<ItemId>UCTAPQ162784331RAAJESHSRE</ItemId>
					<FraudCheckResult>OK</FraudCheckResult>
				<FraudCheckReason>A$1G</FraudCheckReason>
				</FraudItemResults>
				<FraudItemResults>
					<ItemId>FCATA6162787138RAAJESHSRE</ItemId>
					<FraudCheckResult>Suspect</FraudCheckResult>
			   <FraudCheckReason>P2$H</FraudCheckReason> 
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

              SEND ON CONVERSATION @h MESSAGE TYPE [06MA03Message](@XMLString)
