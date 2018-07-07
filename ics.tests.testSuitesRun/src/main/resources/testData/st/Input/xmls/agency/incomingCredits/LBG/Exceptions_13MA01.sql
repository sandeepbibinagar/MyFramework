DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '  <Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
	
	<ICN>
		<Core>
			<BusinessDate>2017-05-10</BusinessDate>
			<ExtractId>12345678909876543212345678</ExtractId>
			<ProcessingParticipantId>999991</ProcessingParticipantId>
			<ExtMessageType>MSG13</ExtMessageType>
			<IntMessageType>13MA01</IntMessageType>
			<MessageSource>MO</MessageSource>                                
			<MessageDestination>IA</MessageDestination>
			<RecordCounts>09809</RecordCounts>
		</Core>
		<Entities>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>MN87ME1704421222803037615</EntityId>
        <StateRevision>73000</StateRevision>
        <EntityState>211</EntityState>
        <SourceDateTime>2017-04-26T11:41:50+01:00</SourceDateTime>
      </Entity>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>MN4SH6163097446iDhj4cDX8A</EntityId>
        <StateRevision>730001</StateRevision>
        <EntityState>230</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>MA4SH6163097446iDhj4cDX8B</EntityId>
        <StateRevision>730002</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
	  <Entity>
        <EntityType>I</EntityType>
        <EntityId>MA4SH6163097446iDhj4cFX8D</EntityId>
        <StateRevision>730003</StateRevision>
        <EntityState>250</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>

	 <Entity>
        <EntityType>I</EntityType>
        <EntityId>AX87ME1704421222803037615</EntityId>
        <StateRevision>830001</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>AX4SH6163097446iDhj4cDX8A</EntityId>
        <StateRevision>830002</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>AB4SH6163097446iDhj4cDX8B</EntityId>
        <StateRevision>830003</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
	  <Entity>
        <EntityType>I</EntityType>
        <EntityId>AB4SH6163097446iDhj4cDX8C</EntityId>
        <StateRevision>830004</StateRevision>
        <EntityState>250</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
	<Entity>
        <EntityType>I</EntityType>
        <EntityId>LM87ME1704421222803037615</EntityId>
        <StateRevision>93000</StateRevision>
        <EntityState>220</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>LM4SH6163097446iDhj4cDX8A</EntityId>
        <StateRevision>93001</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>LM4SH6163097446iDhj4cDX8B</EntityId>
        <StateRevision>930002</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
	  <Entity>
        <EntityType>I</EntityType>
        <EntityId>LM4SH6163097446iDhj4cDX8C</EntityId>
        <StateRevision>930003</StateRevision>
        <EntityState>211</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
	 <Entity>
        <EntityType>I</EntityType>
        <EntityId>RS4SH6163097446iDhj4cDX8C</EntityId>
        <StateRevision>930004</StateRevision>
        <EntityState>220</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
	 <Entity>
        <EntityType>I</EntityType>
        <EntityId>QW87ME1704421222803037615</EntityId>
        <StateRevision>93000</StateRevision>
        <EntityState>230</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>QW4SH6163097446iDhj4cDX8B</EntityId>
        <StateRevision>93001</StateRevision>
        <EntityState>250</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>QA4SH6163097446iDhj4cDX8B</EntityId>
        <StateRevision>93002</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
      </Entity>
	  <Entity>
        <EntityType>I</EntityType>
        <EntityId>QA4SH6163097446iDhj4cDX8C</EntityId>
        <StateRevision>93002</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2017-05-05T11:41:50+01:00</SourceDateTime>
		 </Entity>
	</Entities>
	
	<Items>
			<Item>
				<ItemId>MN4SH6163097446iDhj4cDX8A</ItemId>
				<AuditRevision>323457</AuditRevision>
				<BeneficiaryParticipantId>999991</BeneficiaryParticipantId>
				<Gender>Db</Gender>
				<Codeline>
					<SortCode>309991</SortCode>
					<Account>12345678</Account>
				</Codeline>
				<SwitchedCodeline>
					<SwitchedSortCode>413535</SwitchedSortCode>
					<SwitchedAccount>87654321</SwitchedAccount>
				</SwitchedCodeline>
				<PayDecision>
					<PayDecision>false</PayDecision>
					<PayDecisionReasonCode>1580</PayDecisionReasonCode>
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