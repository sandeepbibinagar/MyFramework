DECLARE @A AS XML(BASE.[03MA02])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">

<ICN>
 <Core>
     <BusinessDate>2017-10-18</BusinessDate>
     <ExtractId>1058658187383052YSEP220259</ExtractId>
     <ProcessingParticipantId>ABC001</ProcessingParticipantId>
     <ExtMessageType>MSG03</ExtMessageType>
     <IntMessageType>03MA02</IntMessageType>
     <MessageSource>MO</MessageSource>
     <MessageDestination>IA</MessageDestination>
     <RecordCounts>1</RecordCounts>
 </Core>
 <Entities>
	<Entity>
	<EntityType>T</EntityType>
        <EntityId>4282464956264786MQGAP64</EntityId>                                                 
        <StateRevision>100118</StateRevision>
       	<EntityState>130</EntityState>                                                   
        <SourceDateTime>2016-06-17T00:20:59+00:00</SourceDateTime>  
	</Entity>
	<Entity>
	<EntityType>I</EntityType>
        <EntityId>DEBIIT162004331RAJESHDEBT</EntityId>                                                 
        <StateRevision>100118</StateRevision>
       	<EntityState>130</EntityState>                                                   
        <SourceDateTime>2016-06-17T00:20:59+00:00</SourceDateTime>  
	</Entity>
 </Entities>  
  <Items>
    <Item>
         <ItemId>6983314900824310CNQLC1072</ItemId>
         <ProcessId>123456789012345PROCS12345</ProcessId>
         <OperatorId>123456789012345PROCS</OperatorId>
         <UpdateDateTime>2016-11-07T07:58:36+00:00</UpdateDateTime>
         <AuditRevision>123457</AuditRevision>
         <Gender>Db</Gender>		    
         <Codeline>
            <Reference>4545454545454545</Reference>
  			<SortCode>676767</SortCode> 
            <Account>12345678</Account>
			<TranCode>20</TranCode>
			<Amount>918712349.01</Amount>           
		</Codeline>
    </Item>
  </Items>
</ICN>
</Document>'