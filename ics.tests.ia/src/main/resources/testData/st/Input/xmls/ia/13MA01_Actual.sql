DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
		<Core>
			<BusinessDate>2017-05-12</BusinessDate>
			<ExtractId>ExID</ExtractId>
			<ProcessingParticipantId>000004</ProcessingParticipantId>
			<ExtMessageType>MSG13</ExtMessageType>
			<IntMessageType>13MA01</IntMessageType>
			<MessageSource>MO</MessageSource>                                 
			<MessageDestination>IA</MessageDestination>
			<RecordCounts>1</RecordCounts>
		</Core>

		<Entities>
			<Entity>
				<EntityType>I</EntityType>                                   
				<EntityId>EntID</EntityId>
				<StateRevision>100028</StateRevision>
				<EntityState>250</EntityState>
				<SourceDateTime>2017-05-12T07:58:36+00:00</SourceDateTime>
			</Entity>	
					
		</Entities>
		<Items>
			<Item>
				<ItemId>ITMID</ItemId>
				<AuditRevision>100028</AuditRevision>
				<BeneficiaryParticipantId>123456</BeneficiaryParticipantId>
				<Gender>Db</Gender>
				<Codeline>
					<SortCode>943210</SortCode>
					<Account>12345678</Account>
				</Codeline>
				<SwitchedCodeline>
					<SwitchedSortCode>123456</SwitchedSortCode>
					<SwitchedAccount>SwAcct</SwitchedAccount>
				</SwitchedCodeline>
				<PayDecision>
					<PayDecision>true</PayDecision>
					<PayDecisionReasonCode>pdrc</PayDecisionReasonCode>
				</PayDecision>
			</Item>
		</Items>
		
</ICN>
</Document>
'
 
BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [13MA01Message](@XMLString)