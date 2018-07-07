DECLARE @h UNIQUEIDENTIFIER
DECLARE @XMLString XML

SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
 <Core>
    <BusinessDate>2017-06-01</BusinessDate>
    <ExtractId>55545678909876543212345678</ExtractId>
    <ProcessingParticipantId>300000</ProcessingParticipantId>
    <ExtMessageType>MSG13</ExtMessageType>
    <IntMessageType>13MA04</IntMessageType>
    <MessageSource>MO</MessageSource>
    <MessageDestination>IA</MessageDestination>
    <RecordCounts>09809</RecordCounts>                       
 </Core>
 <Entities>
	<Entity>
		<EntityType>I</EntityType>                                  
		<EntityId>AA87ME1704421222803037300</EntityId>
		<StateRevision>400127</StateRevision>
		<EntityState>301</EntityState>
		<SourceDateTime>2017-03-03T07:58:36+00:00</SourceDateTime>
	</Entity>
	<Entity>
		<EntityType>I</EntityType>                                  
		<EntityId>AC87ME1704421222803037302</EntityId>
		<StateRevision>400128</StateRevision>
		<EntityState>260</EntityState>
		<SourceDateTime>2017-03-03T07:58:36+00:00</SourceDateTime>
	</Entity>
	<Entity>
		<EntityType>I</EntityType>                                  
		<EntityId>AD87ME1704421222803037303</EntityId>
		<StateRevision>400129</StateRevision>
		<EntityState>260</EntityState>
		<SourceDateTime>2017-03-03T07:58:36+00:00</SourceDateTime>
	</Entity>
			
	</Entities>  
 <Items>
    <Item>
         <ItemId>AA87ME1704421222803037300</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2017-03-03T07:58:36+00:00</UpdateDateTime>
		 <AuditRevision>001600</AuditRevision>		
		 <Gender>Cr</Gender>
		 <Codeline>
			<Narrative>Checkfornarrative123456789</Narrative>
		 </Codeline>
		<AlternateCodeline>
			<AlternateSortCode>123456</AlternateSortCode>
			<AlternateAccount>87654321</AlternateAccount>
	    </AlternateCodeline>
    </Item>
	<Item>
         <ItemId>AC87ME1704421222803037302</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2017-03-03T07:58:36+00:00</UpdateDateTime>
		 <AuditRevision>001600</AuditRevision>		
		 <Gender>Db</Gender>
		 <Codeline>
			<Narrative>Checkfornarrative123456789</Narrative>
		 </Codeline>
		<AlternateCodeline>
			<AlternateSortCode>123456</AlternateSortCode>
			<AlternateAccount>87654321</AlternateAccount>
	    </AlternateCodeline>
    </Item>
	<Item>
         <ItemId>AD87ME1704421222803037303</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2017-03-03T07:58:36+00:00</UpdateDateTime>
		 <AuditRevision>001600</AuditRevision>		
		 <Gender>Db</Gender>
		 <Codeline>
			<Narrative>Checkfornarrative123456789</Narrative>
		 </Codeline>
		<AlternateCodeline>
			<AlternateSortCode>123456</AlternateSortCode>
			<AlternateAccount>87654321</AlternateAccount>
	    </AlternateCodeline>
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

SEND ON CONVERSATION @h MESSAGE TYPE [13MA04Message](@XMLString)
