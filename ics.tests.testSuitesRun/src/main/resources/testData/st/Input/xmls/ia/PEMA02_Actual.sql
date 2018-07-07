DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
<ICN>
	<Core>
       <BusinessDate>2017-08-22</BusinessDate>
      <ExtractId>ExID</ExtractId>
      <ProcessingParticipantId>300000</ProcessingParticipantId>
      <ExtMessageType>PSTNG</ExtMessageType>
      <IntMessageType>PEMA02</IntMessageType>
      <MessageSource>MO</MessageSource>
      <MessageDestination>IA</MessageDestination>
      <RecordCounts>12345678</RecordCounts>                       
	</Core>
	<PostingUpdate>
			<PostingExtract>
				<ExtractSequence>ExSeq</ExtractSequence>
				<ExtractRevision>999</ExtractRevision>
				<FileId>FId</FileId>
				<Weekday>FR</Weekday>
				<FileType>7</FileType>
				<Currency>GBP</Currency>
				<Environment>1</Environment>
				<ExtractStartDateTime>2016-11-07T07:58:36+00:00</ExtractStartDateTime>
				<ExtractEndDateTime>2016-11-07T07:58:36+00:00</ExtractEndDateTime>
				<ExtractItemCount>99999999</ExtractItemCount>
				<ExtractItemAmount>121.08</ExtractItemAmount>
				<PostingItemEntry>
					<ItemId>ITMID1</ItemId>
					<PostingAttempt>999</PostingAttempt>
					<PostingSequence>340152</PostingSequence>
					<PostingType>QW1</PostingType>
					<PostingSubType>12345678QWE</PostingSubType>
					<Channel>1223</Channel>
					<PostingDrCrEntry>123456</PostingDrCrEntry>
					<PostingSource>QWE123</PostingSource>
					<ResponseIdSource>123JHG</ResponseIdSource>
					<PostingDay>3</PostingDay>
					<ClearingDate>2016-09-09</ClearingDate>
					<SettlementDate>2016-12-09</SettlementDate>
					<PostedAmount>1234567890123.99</PostedAmount>
				    <PostingDecisionReasonCode>6544</PostingDecisionReasonCode>
					<PostingOverrideFlag>A</PostingOverrideFlag>
					<NPASortCode>123456</NPASortCode>
					<NPAAccount>12345678</NPAAccount>
					<SupportingInfo>12345678901234567890QWERTYUIOP1234567890</SupportingInfo>
					<ChequeCount>9998</ChequeCount>
					<CollectingParticipantId>999888</CollectingParticipantId>
					<CollectingLocation>123456789Q</CollectingLocation>
					
					<DebitRecord>
							<ItemId>ITMID1</ItemId>
							<SortCode>123456</SortCode>
							<Account>12345678</Account>
							<Serial>123456</Serial>
							<TranCode>tranCd</TranCode>
							<SwitchedSortCode>123456</SwitchedSortCode>
							<SwitchedAccount>SwAcct</SwitchedAccount>
					</DebitRecord>
					
					
					<CreditRecord>
							<ItemId>ITMID2</ItemId>
							<SortCode>998877</SortCode>
							<Account>99886543</Account>
							<Reference>998899998899998899</Reference>
							<TranCode>RT</TranCode>
							<OriginalAmount>12345677.98</OriginalAmount>
							<OriginalSortCode>123456</OriginalSortCode>
							<OriginalAccount>67676767</OriginalAccount>
							<CreditExceptionCode>123456</CreditExceptionCode>
							<BeneficiaryName>12345678901234567890QWERTYUIOP1234567890</BeneficiaryName>
					</CreditRecord>
				</PostingItemEntry>
			</PostingExtract>
		</PostingUpdate>
	
</ICN>
</Document>
'
      BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [PEMA02Message](@XMLString)
