DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML
SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
	<ICN>
	<Core>
      <BusinessDate>2016-08-22</BusinessDate>
      <ExtractId>ExID</ExtractId>
      <ProcessingParticipantId>300000</ProcessingParticipantId>
      <ExtMessageType>PSTNG</ExtMessageType>
      <IntMessageType>PTMA01</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>12345678</RecordCounts>                       
	</Core>
	<PostingBatch>
		<PostingBatchItems>
			<ItemId>ITMID</ItemId>
			<TriggeringState>212</TriggeringState>
		</PostingBatchItems>
	</PostingBatch>
</ICN>
</Document>
'
              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [PTMA01Message](@XMLString)
