DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
 <Core>
   <BusinessDate>2017-05-28</BusinessDate>
   <ExtractId>RAJESHSRE09876543212345678</ExtractId>
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
        <EntityId>CE87ME1704421222W803037200</EntityId>
        <StateRevision>300125</StateRevision>
        <EntityState>250</EntityState>
        <SourceDateTime>2016-11-07T07:58:36+00:00</SourceDateTime>
	 </Entity>
	 <Entity>
        <EntityType>I</EntityType>
        <EntityId>CE87ME1704421222803037201</EntityId>
        <StateRevision>301126</StateRevision>
        <EntityState>250</EntityState>
        <SourceDateTime>2016-11-07T07:58:36+00:00</SourceDateTime>
	 </Entity>
	 <Entity>
        <EntityType>I</EntityType>
        <EntityId>CE87ME1704421222803037202</EntityId>
        <StateRevision>301127</StateRevision>
        <EntityState>997</EntityState>
        <SourceDateTime>2016-11-07T07:58:36+00:00</SourceDateTime>
	 </Entity>
	 <Entity>
        <EntityType>I</EntityType>
        <EntityId>CE87ME1704421222803037203</EntityId>
        <StateRevision>301127</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2016-11-07T07:58:36+00:00</SourceDateTime>
	 </Entity>
 </Entities>
 <FraudResponse>
   <CreationDateTime>2017-03-19T00:20:59+01:00</CreationDateTime> 
   <NumberofEntries>1</NumberofEntries> 
   <FraudResponseType>MSF06</FraudResponseType> 
 <FraudStatusTransactionSets>
    <TransactionSetId>RAJESH162782814981024308</TransactionSetId> 
    <FraudItemResults>
      <ItemId>CE87ME1704421222803037200</ItemId> 
      <FraudCheckResult>Not Processed</FraudCheckResult>
	<!--  <FraudCheckReason>A$1</FraudCheckReason>   -->    
    </FraudItemResults>
    <FraudItemResults>
      <ItemId>CE87ME1704421222803037201</ItemId> 
      <FraudCheckResult>OK</FraudCheckResult>
	<!--  <FraudCheckReason>B$1</FraudCheckReason>  -->
    </FraudItemResults>	
	<FraudItemResults>
      <ItemId>CE87ME1704421222803037202</ItemId> 
      <FraudCheckResult>Suspect</FraudCheckResult>
<!--	<FraudCheckReason>B$1</FraudCheckReason>  -->
    </FraudItemResults>
	<FraudItemResults>
      <ItemId>CE87ME1704421222803037203</ItemId> 
      <FraudCheckResult>Fraudulent</FraudCheckResult>
<!--	<FraudCheckReason>B$1</FraudCheckReason>  -->
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

              SEND ON CONVERSATION @h MESSAGE TYPE [05MA03Message](@XMLString)
