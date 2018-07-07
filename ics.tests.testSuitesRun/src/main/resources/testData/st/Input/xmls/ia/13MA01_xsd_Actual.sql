DECLARE @A AS XML(BASE.[13MA01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
<ICN>
		<Core>
			<BusinessDate>2017-11-12</BusinessDate>
			<ExtractId>0000042317051213MA02000001</ExtractId>
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
				<EntityId>000004173651000DEBIT00001</EntityId>
				<StateRevision>100028</StateRevision>
				<EntityState>250</EntityState>
				<SourceDateTime>2017-05-12T07:58:36+00:00</SourceDateTime>
			</Entity>	
					
		</Entities>
		<Items>
			<Item>
				<ItemId>000004173651000DEBIT00001</ItemId>
				<AuditRevision>100028</AuditRevision>
				<BeneficiaryParticipantId>123456</BeneficiaryParticipantId>
				<Gender>Db</Gender>
				<Codeline>
					<SortCode>943210</SortCode>
					<Account>12345678</Account>
				</Codeline>
				<SwitchedCodeline>
					<SwitchedSortCode>123456</SwitchedSortCode>
					<SwitchedAccount>12345678</SwitchedAccount>
				</SwitchedCodeline>
				<PayDecision>
					<PayDecision>true</PayDecision>
					<PayDecisionReasonCode>P221</PayDecisionReasonCode>
				</PayDecision>
			</Item>
		</Items>
		
</ICN>

</Document>'

