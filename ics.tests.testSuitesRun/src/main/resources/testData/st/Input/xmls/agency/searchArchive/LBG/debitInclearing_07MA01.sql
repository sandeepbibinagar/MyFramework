DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
 <Core>
    <BusinessDate>2017-06-01</BusinessDate>
    <ExtractId>3000002016082207MA01000001</ExtractId>
    <ProcessingParticipantId>999991</ProcessingParticipantId>
    <ExtMessageType>MSG07</ExtMessageType>
    <IntMessageType>07MA01</IntMessageType>
    <MessageSource>MO</MessageSource>
    <MessageDestination>IA</MessageDestination>
    <RecordCounts>1</RecordCounts>                       
 </Core>
 <Entities>
	<Entity>
		<EntityType>D</EntityType>
        <EntityId>AL87ME1704421222803037300</EntityId>                                                 
        <StateRevision>400116</StateRevision>
       	<EntityState>560</EntityState>                                                   
        <SourceDateTime>2016-06-22T00:20:59+01:00</SourceDateTime> 
	</Entity>
	<Entity>
	    <EntityType>D</EntityType>
        <EntityId>AL87ME1704421222803037301</EntityId>                                                 
        <StateRevision>400116</StateRevision>
       	<EntityState>230</EntityState>                                                   
        <SourceDateTime>2016-06-22T00:20:59+01:00</SourceDateTime> 
	</Entity>
 </Entities>  
 <DocumentTransactionItems>
	<DocumentId>99999916278C:5762897407</DocumentId>
	<CreationDateTime>2016-06-21T00:20:59+01:00</CreationDateTime>
	<NumberofEntries>997</NumberofEntries>
	<!--<ReceiverId>AB1234</ReceiverId> -->
	<SenderId>BC1234</SenderId>
	<ChargingParticipant>CD1234</ChargingParticipant>
	<TestDocument>true</TestDocument>	
	<ItemId>AL87ME1704421222803037300</ItemId>	
 </DocumentTransactionItems>

</ICN>
</Document>
'

            BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [07MA01Message](@XMLString)
