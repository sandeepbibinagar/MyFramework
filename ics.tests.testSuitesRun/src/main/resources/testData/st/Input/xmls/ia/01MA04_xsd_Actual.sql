DECLARE @A AS XML(BASE.[01MA04])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">

<ICN>
  <Core>
      <BusinessDate>2016-11-04</BusinessDate>
      <ExtractId>5760774693958887MTKJ080549</ExtractId>
      <ProcessingParticipantId>300000</ProcessingParticipantId>
      <ExtMessageType>MSG01</ExtMessageType>
      <IntMessageType>01MA04</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>1</RecordCounts>
  </Core>
	<Entities>
		
			<Entity>
				<EntityType>I</EntityType>
				<EntityId>4949450645071390WBWNA03</EntityId>
				<StateRevision>112</StateRevision>
				<EntityState>560</EntityState>
				<SourceDateTime>2016-06-22T00:20:59+00:00</SourceDateTime>
			</Entity>
	</Entities>
  <Items>
  	

	  	<Item>
      <ItemId>4429645097492243RJTYH3091</ItemId>
      <ProcessId>00156process1</ProcessId>
      <OperatorId>123456opsid</OperatorId>
      <UpdateDateTime>2016-07-07T07:58:36+00:00</UpdateDateTime>
      <AuditRevision>210000</AuditRevision>
      <Gender>Db</Gender>
     		 <Codeline>
			<SortCode>425689</SortCode>
			<Account>78787878</Account>
            		<Amount>918712349.01</Amount>
		 </Codeline>
    </Item>
  </Items> 
</ICN>

</Document>'