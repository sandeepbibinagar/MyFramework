DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
 <Core>
    <BusinessDate>2016-08-22</BusinessDate>
    <ExtractId>ExID</ExtractId>
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
        <EntityId>EntID</EntityId>                                                 
        <StateRevision>100116</StateRevision>
       	<EntityState>570</EntityState>                                                   
        <SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime> 
	</Entity>
 </Entities>  
 <DocumentTransactionItems>
	<DocumentId>DocID</DocumentId>
	<CreationDateTime>2016-06-21T00:20:59+00:00</CreationDateTime>
	<NumberofEntries>2</NumberofEntries>
	<SenderId>BC1234</SenderId>
	<ChargingParticipant>CD1234</ChargingParticipant>
	<TestDocument>true</TestDocument>	
	<TransactionSetId>123456162784331231434567</TransactionSetId>
 </DocumentTransactionItems>
</ICN>
</Document>
'
    		  BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [01MA06Message](@XMLString)

