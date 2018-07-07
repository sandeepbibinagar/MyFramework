USE [MORepository_BugFix]
DECLARE @dialog_handle UNIQUEIDENTIFIER,
        @ExpenseReport XML;
SET @ExpenseReport = '<Document xmlns="urn:xsd:ipsl.ics"  xmlns:xsd="http://www.w3.org/2001/XMLSchema">
  <ICN>
    <Core>
      <BusinessDate>2017-11-23</BusinessDate>
      <ExtractId>0000042017042812DM02004387</ExtractId>
      <ProcessingParticipantId>000004</ProcessingParticipantId>
      <ExtMessageType>MSG13</ExtMessageType>
      <IntMessageType>13DM01</IntMessageType>
      <MessageSource>DEW</MessageSource>
      <MessageDestination>MO</MessageDestination>
      <RecordCounts>1</RecordCounts>
    </Core>
    <Entities>
	 <Entity>
        <EntityType>I</EntityType>
        <EntityId>BNIPSL171008104744Crdt101</EntityId>
        <EntityState>260</EntityState>
        <SourceDateTime>2017-04-27T10:00:41+01:00</SourceDateTime>
      </Entity>
     </Entities>
</ICN>
</Document>';

BEGIN DIALOG @dialog_handle
FROM SERVICE [//MO/DEWReceiveService]
TO SERVICE '//MO/DEWReceiveService'
ON CONTRACT [//DEW/MO/Contract];

SEND ON CONVERSATION @dialog_handle
   MESSAGE TYPE [13DM01Message]
   (@ExpenseReport);