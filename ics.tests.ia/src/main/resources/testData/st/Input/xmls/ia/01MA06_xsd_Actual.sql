DECLARE @A AS XML(BASE.[01MA06])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">

<ICN>
 <Core>
    <BusinessDate>2016-08-22</BusinessDate>
    <ExtractId>30000020160822071101000001</ExtractId>
    <ProcessingParticipantId>3A0C00</ProcessingParticipantId>
    <ExtMessageType>MSG01</ExtMessageType>
    <IntMessageType>01MA06</IntMessageType>
    <MessageSource>MO</MessageSource>
    <MessageDestination>IA</MessageDestination>
    <RecordCounts>1</RecordCounts>                       
 </Core>
 <Entities>
	<Entity>
	<EntityType>D</EntityType>
        <EntityId>LL87ME17044212228030376</EntityId>                                                 
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
	<TransactionSetId>123456162784331231434567</TransactionSetId>
 </DocumentTransactionItems>
</ICN>
</Document>'