DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
 <Core>
     <BusinessDate>2017-10-18</BusinessDate>
     <ExtractId>ExID</ExtractId>
     <ProcessingParticipantId>ABC001</ProcessingParticipantId>
     <ExtMessageType>MSG03</ExtMessageType>
     <IntMessageType>03MA02</IntMessageType>
     <MessageSource>MO</MessageSource>
     <MessageDestination>IA</MessageDestination>
     <RecordCounts>1</RecordCounts>
 </Core>
 <Entities>
	
	<Entity>
	<EntityType>I</EntityType>
        <EntityId>EntID</EntityId>                                                 
        <StateRevision>100118</StateRevision>
       	<EntityState>130</EntityState>                                                   
        <SourceDateTime>2016-06-17T00:07:09+00:00</SourceDateTime> 
	</Entity>
 </Entities>  
  <Items>
    <Item>
         <ItemId>ITMID</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2016-11-07T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>123457</AuditRevision>
         <Gender>Cr</Gender>		    
         <Codeline>
            <Reference>4545454545454545</Reference>
  			<SortCode>676767</SortCode> 
            <Account>12345678</Account>
			<TranCode>tranCd</TranCode>
			<Amount>918712349.01</Amount>           
		</Codeline>
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

              SEND ON CONVERSATION @h MESSAGE TYPE [03MA02Message](@XMLString)
