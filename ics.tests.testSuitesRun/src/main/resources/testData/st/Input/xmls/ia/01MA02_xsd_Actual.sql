DECLARE @A AS XML(BASE.[01MA02])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
 
<ICN>
	<Core>
      <BusinessDate>2017-01-17</BusinessDate>
      <ExtractId>6452781295469386MUOG412554</ExtractId>
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
        	<EntityId>4529190824050776GHFIJ73</EntityId>                                                 
        	<StateRevision>111</StateRevision>
       		<EntityState>570</EntityState>                                                   
        	<SourceDateTime>2017-01-17T00:20:59+00:00</SourceDateTime> 
		</Entity>
	</Entities>
	
	<Items>
    <Item>
        <ItemId>2585057475803316ZOPGO9837</ItemId>
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
</Document>'