DECLARE @A AS XML(BASE.[04MA01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">

<ICN>
    <Core>
      <BusinessDate>2017-01-27</BusinessDate>
      <ExtractId>0839295256153007YBKZ091748</ExtractId>
      <ProcessingParticipantId>OOHVII</ProcessingParticipantId>
      <ExtMessageType>MSG04</ExtMessageType>
      <IntMessageType>04MA01</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>3</RecordCounts>
    </Core>
    <Entities>      
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>4840980968390419AQMEI4633</EntityId>
        <StateRevision>27001</StateRevision>
        <EntityState>21</EntityState>
        <SourceDateTime>2016-11-04T14:11:42+00:00</SourceDateTime>
      </Entity>
    </Entities>
	<Items>
     <Item>
         <ItemId>0323456704526503LTOTW9264</ItemId>
         <AuditRevision>123457</AuditRevision>
         <Gender>Db</Gender>		    
		<ResponseWindow>
			<Day2ResponseWindowStartDateTime>2016-11-08T07:58:36+00:00</Day2ResponseWindowStartDateTime>
			<Day2ResponseWindowEndDateTime>2016-11-08T07:58:38+00:00</Day2ResponseWindowEndDateTime>
		</ResponseWindow>
     </Item>
   </Items>
  </ICN>

             
</Document>'