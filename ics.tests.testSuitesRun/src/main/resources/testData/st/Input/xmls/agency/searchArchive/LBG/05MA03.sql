DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML

SET @XMLString = '  <Document xmlns="urn:xsd:ipsl.ics">

  <ICN>
 <Core>
   <BusinessDate>2017-05-31</BusinessDate>
   <ExtractId>0000042017071705MA03000004</ExtractId>
   <ProcessingParticipantId>999991</ProcessingParticipantId>
   <ExtMessageType>MSG05</ExtMessageType>
   <IntMessageType>05MA03</IntMessageType>
   <MessageSource>MO</MessageSource>
   <MessageDestination>IA</MessageDestination>
   <RecordCounts>1</RecordCounts>                       
</Core>  

<Entities>
	        
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>AA87ME1704421222803037300</EntityId>
        <StateRevision>200124</StateRevision>
        <EntityState>50</EntityState>
        <SourceDateTime>2017-07-17T00:20:59+01:00</SourceDateTime>
      </Entity> 
    
    </Entities>
	
<FraudResponse>
   <CreationDateTime>2017-07-17T00:20:59+01:00</CreationDateTime> 
   <NumberofEntries>1234567890</NumberofEntries> 
   <FraudResponseType>MSF06</FraudResponseType> 
 <FraudStatusTransactionSets>
    <TransactionSetId>49PZSR163099211645611343</TransactionSetId> 
    <FraudItemResults>
      <ItemId>AA87ME1704421222803037300</ItemId> 
      <FraudCheckResult>Suspect</FraudCheckResult> 
    </FraudItemResults>
 </FraudStatusTransactionSets>
</FraudResponse>  
                
</ICN>
</Document>' 
       
              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [05MA03Message](@XMLString)