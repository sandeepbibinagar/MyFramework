DECLARE @A AS XML(BASE.[PTMA01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
<ICN>
	<Core>
      <BusinessDate>2016-08-22</BusinessDate>
      <ExtractId>1234567890987654321test123</ExtractId>
      <ProcessingParticipantId>300000</ProcessingParticipantId>
      <ExtMessageType>PSTNG</ExtMessageType>
      <IntMessageType>PTMA01</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>12345678</RecordCounts>                       
	</Core>
	
	<PostingBatch>
		<PostingBatchItems>
			<ItemId>1234567890123456789012345</ItemId>
			<TriggeringState>212</TriggeringState>
		</PostingBatchItems>
	</PostingBatch>
</ICN>
</Document>'