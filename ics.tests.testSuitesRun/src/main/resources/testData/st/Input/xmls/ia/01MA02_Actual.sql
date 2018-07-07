DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
	<Core>
      <BusinessDate>2017-01-17</BusinessDate>
      <ExtractId>ExID</ExtractId>
      <ProcessingParticipantId>3000A0</ProcessingParticipantId>
      <ExtMessageType>MSG01</ExtMessageType>
      <IntMessageType>01MA02</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>12345678</RecordCounts>                       
	</Core>
	<Entities>
		<Entity>
			<EntityType>D</EntityType>
        	<EntityId>EntID</EntityId>                                                 
        	<StateRevision>111</StateRevision>
       		<EntityState>570</EntityState>                                                   
        	<SourceDateTime>2017-01-17T00:20:59+00:00</SourceDateTime> 
		</Entity>
	</Entities>
	
	<Items>
    <Item>
        <ItemId>ITMID</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2017-01-17T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>123457</AuditRevision>
         <Gender>Db</Gender>
         <Codeline>
			<SortCode>425997</SortCode>
			<Account>78787878</Account>
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

              SEND ON CONVERSATION @h MESSAGE TYPE [01MA02Message](@XMLString)


			
