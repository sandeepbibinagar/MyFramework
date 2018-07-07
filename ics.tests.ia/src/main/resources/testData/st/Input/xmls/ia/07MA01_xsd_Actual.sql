DECLARE @A AS XML(BASE.[07MA01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
<ICN>
 <Core>
    <BusinessDate>2017-05-04</BusinessDate>
    <ExtractId>3000002016082207MA01000001</ExtractId>
    <ProcessingParticipantId>3A0C00</ProcessingParticipantId>
    <ExtMessageType>MSG07</ExtMessageType>
    <IntMessageType>07MA01</IntMessageType>
    <MessageSource>MO</MessageSource>
    <MessageDestination>IA</MessageDestination>
    <RecordCounts>1</RecordCounts>                       
 </Core>
 <Entities>
	<Entity>
		<EntityType>D</EntityType>
        <EntityId>99999916278C:5762897407</EntityId>                                                 
        <StateRevision>100116</StateRevision>
       	<EntityState>570</EntityState>                                                   
        <SourceDateTime>2016-06-22T00:20:59+01:00</SourceDateTime> 
	</Entity>
 </Entities>  
 <DocumentTransactionItems>
	<DocumentId>99999916278C:5762897407</DocumentId>
	<CreationDateTime>2016-06-21T00:20:59+01:00</CreationDateTime>
	<NumberofEntries>2</NumberofEntries>
	<SenderId>BC1234</SenderId>
	<ChargingParticipant>CD1234</ChargingParticipant>
	<TestDocument>true</TestDocument>	
	<ItemId>UCTAPQ162784331RAAJESHSRE</ItemId>	
 </DocumentTransactionItems>
</ICN>

</Document>'

