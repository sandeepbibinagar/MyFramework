DECLARE @A AS XML(BASE.[CMMA01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics">
	
	
	<ICN>
		<Core>
			<BusinessDate>2017-05-06</BusinessDate>
			<ExtractId>3359424302101967YFBQ535112</ExtractId>
			<ProcessingParticipantId>300000</ProcessingParticipantId>
			<ExtMessageType>CSMGT</ExtMessageType>
			<IntMessageType>CMMA01</IntMessageType>
			<MessageSource>MO</MessageSource>  
			<MessageDestination>IA</MessageDestination>
			<RecordCounts>2</RecordCounts>
		</Core>

   	<Entities> 
		 <Entity>
         		<EntityType>I</EntityType>
         		<EntityId>8393774581906565LVTGQ7641</EntityId>
         		<StateRevision>200001</StateRevision>
         		<EntityState>20</EntityState>
        		<SourceDateTime>2016-11-07T07:58:36+00:00</SourceDateTime>
		 </Entity>
     </Entities>
	
	<Items>
      		<Item>		
		        <ItemId>CMMAPQ162784331AAAAAAAA19</ItemId>
				<ProcessId>123456789012345PROCS12345</ProcessId>
				<OperatorId>123456789012345PROCS</OperatorId>
				<UpdateDateTime>2016-11-07T07:58:36+00:00</UpdateDateTime>
				<AuditRevision>123457</AuditRevision>
				<Gender>Cr</Gender>
	   		  </Item>
  </Items>


	<CaseManagement>
		<OpenCase>
			<CaseId>1414709325</CaseId>
			<CaseType>1234567890</CaseType>
			<EntityType>I</EntityType>
			<EntityId>8393774581906565LVTGQ7649</EntityId>
		</OpenCase>
		<CloseCase>
			 <CaseId>1414709315</CaseId>
		</CloseCase>
	</CaseManagement>

	<PostingUpdate>
		<PostingInformation>
			 		<ExtractSequence>1286</ExtractSequence>
					<ExtractRevision>123</ExtractRevision>
					<Weekday>TU</Weekday>
					<FileType>1</FileType>
					<Currency>GBR</Currency>
					<Environment>1</Environment>
					<ExtractStartDateTime>2016-11-07T07:58:36+00:00</ExtractStartDateTime>
					<ExtractEndDateTime>2016-11-09T07:58:36+00:00</ExtractEndDateTime>
					<ExtractItemCount>00000001</ExtractItemCount>
					<ExtractItemAmount>1000000000000.50</ExtractItemAmount>
					<PostingItemEntry>
							<ItemId>CMMAPQ162784331AAAAAAAA10</ItemId>
							<PostingSequence>999999</PostingSequence>
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
							<PostingOverrideFlag>A</PostingOverrideFlag>
							<SupportingInfo>12345678901234567890QWERTYUIOP1234567890</SupportingInfo>
							<DebitRecord>
									<ItemId>CMMAPQ162784331AAAAAAAA12</ItemId>
									<SortCode>123456</SortCode>
									<Account>12345678</Account>
									<Serial>123456</Serial>
							</DebitRecord>
							<CreditRecord>
									<ItemId>CMMAPQ162784331AAAAAAAA11</ItemId>
									<SortCode>998877</SortCode>
									<Account>99886543</Account>
									<Reference>998899998899998899</Reference>
							</CreditRecord>
				    </PostingItemEntry>
			</PostingInformation>
		</PostingUpdate>
	</ICN>
</Document>'

