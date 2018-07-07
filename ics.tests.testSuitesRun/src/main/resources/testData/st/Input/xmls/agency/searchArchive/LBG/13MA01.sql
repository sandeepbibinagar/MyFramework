
DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML

SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
    <Core>
      <BusinessDate>2017-06-01</BusinessDate>
      <ExtractId>RAJESHSRE09876543212345678</ExtractId>
      <ProcessingParticipantId>300001</ProcessingParticipantId>
      <ExtMessageType>MSG13</ExtMessageType>
      <IntMessageType>13MA01</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>3</RecordCounts>
    </Core>
    <Entities>
      <Entity>
        <EntityType>I</EntityType>
        <EntityId>AC87ME1704421222803037302</EntityId>
        <StateRevision>300127</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2017-04-10T11:41:50+01:00</SourceDateTime>
      </Entity>
	  <Entity>
        <EntityType>I</EntityType>
        <EntityId>AD87ME1704421222803037303</EntityId>
        <StateRevision>300128</StateRevision>
        <EntityState>240</EntityState>
        <SourceDateTime>2017-04-10T11:41:50+01:00</SourceDateTime>
      </Entity>
    </Entities>
    <Items>
      <Item>
        <ItemId>AC87ME1704421222803037302</ItemId>
        <AuditRevision>416439</AuditRevision>
        <BeneficiaryParticipantId>300001</BeneficiaryParticipantId>
        <Gender>Db</Gender>
        <Codeline>
          <SortCode>309991</SortCode>
          <Account>18628513</Account>
        </Codeline>
       <SwitchedCodeline>
          <SwitchedSortCode>276118</SwitchedSortCode>
          <SwitchedAccount>41242393</SwitchedAccount>
        </SwitchedCodeline> 
		    <PayDecision>
          <PayDecision>false</PayDecision>
		 <PayDecisionReasonCode>1508</PayDecisionReasonCode>
        </PayDecision>
      </Item>
  
	  <Item>
        <ItemId>AD87ME1704421222803037303</ItemId>
        <AuditRevision>601091</AuditRevision>
        <BeneficiaryParticipantId>300001</BeneficiaryParticipantId>
        <Gender>Db</Gender>
        <Codeline>
          <SortCode>309991</SortCode>
          <Account>99439856</Account>
        </Codeline>
        <SwitchedCodeline>
          <SwitchedSortCode>042214</SwitchedSortCode>
          <SwitchedAccount>13737898</SwitchedAccount>
        </SwitchedCodeline>
        <PayDecision>
          <PayDecision>false</PayDecision>
		 <PayDecisionReasonCode>1580</PayDecisionReasonCode>
        </PayDecision>
      </Item>
     </Items>
  </ICN>
</Document>'



              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [13MA01Message](@XMLString)
