DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML


SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
		<ReqToPay>
    <GrpHdr>
      <MsgId>99999916278C:5762897407</MsgId>
      <CreDtTm>2017-05-02T14:28:45+01:00</CreDtTm>
      <NbOfTxs>1</NbOfTxs>
      <RcvrId>888881</RcvrId>
      <TstInd>false</TstInd>
      <Sgntr>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</Sgntr>
    </GrpHdr>
	<TxSet>
		<TxSetId>49PZSR1630992116456113</TxSetId>
		<TxSetVrsn>42</TxSetVrsn>
		<ColltngPtcptId>888881</ColltngPtcptId>
		<CaptrdDtTm>2017-06-01T13:00:00+01:00</CaptrdDtTm>
		<TxSetSubDtTm>2017-06-01T13:00:00+01:00</TxSetSubDtTm>
		<Src>1111</Src>
		<ColltngBrnchLctn>BrLocton</ColltngBrnchLctn>
		<ColltngLctn>CollectLOC</ColltngLctn>
		<ChanlRskTp>CHRK</ChanlRskTp>
		<ChanlDesc>Channel Desc</ChanlDesc>
		<ColltnPt>Collecting RefrPoint</ColltnPt>
		<ColltngBrnchRef>Coll Branch Ref</ColltngBrnchRef>
		<NbOfItms>2</NbOfItms>
		<EndPtId>888881</EndPtId>
	 <CrdtItm>
        <CdtItmId>AL87ME1704421222803037300</CdtItmId>
        <CdtItmTp>IOCI</CdtItmTp>
        <Amt Ccy="GBP">1231.89</Amt>
        <BkCd>403504</BkCd>
        <AcctNb>89078907</AcctNb>
        <RefNb>RefNb</RefNb>
        <CdtItmFrdData>
          <BnfcryNm>BnfcryNm</BnfcryNm>
          <VrtlCdtInd>true</VrtlCdtInd>
          <RefData>RefData</RefData>
        </CdtItmFrdData>
        <RprdItm>
          <BkCdRprdInd>true</BkCdRprdInd>
          <AcctNbRprdInd>true</AcctNbRprdInd>
          <AmtRprdInd>true</AmtRprdInd>
          <SrlNbRprdInd>true</SrlNbRprdInd>
          <RefNbRprdInd>true</RefNbRprdInd>
        </RprdItm>		
        <DfltdItm>
          <BkCdDfltdInd>true</BkCdDfltdInd>
          <AcctNbDfltdInd>true</AcctNbDfltdInd>
          <RefNbDfltdInd>true</RefNbDfltdInd>
        </DfltdItm>
      </CrdtItm>
	  <DbtItm>
			<DbtItmId>AL87ME1704421222803037301</DbtItmId>
			<DbtItmTp>RTPI</DbtItmTp>
			<DbtItmTxCd>02</DbtItmTxCd>
			<RpresntdItmInd>0</RpresntdItmInd>
			<Amt Ccy="GBP">7.11</Amt>
			<BkCd>403504</BkCd>
			<AcctNb>32000001</AcctNb>
			<SrlNb>111121</SrlNb>
			<HghValItm>0</HghValItm>
			<DayOneRspnWndwStartDtTm>2017-06-01T13:00:00+01:00</DayOneRspnWndwStartDtTm>
			<DayOneRspnWndwEndDtTm>2017-06-01T23:51:00+01:00</DayOneRspnWndwEndDtTm>
			<DayTwoRspnWndwStartDtTm>2017-06-01T07:00:00+01:00</DayTwoRspnWndwStartDtTm>
			<DayTwoRspnWndwEndDtTm>2017-06-01T14:30:00+01:00</DayTwoRspnWndwEndDtTm>
			<XtrnlDataRef>xtrnalRefData</XtrnlDataRef>
			<RprdItm>
				<BkCdRprdInd>0</BkCdRprdInd>
				<AcctNbRprdInd>0</AcctNbRprdInd>
				<AmtRprdInd>0</AmtRprdInd>
				<SrlNbRprdInd>0</SrlNbRprdInd>
				<RefNbRprdInd>0</RefNbRprdInd>
			</RprdItm>
			<DfltdItm>
				<BkCdDfltdInd>0</BkCdDfltdInd>
				<AcctNbDfltdInd>0</AcctNbDfltdInd>
				<SrlNbDfltdInd>0</SrlNbDfltdInd>
			</DfltdItm>
		</DbtItm>

	</TxSet>
</ReqToPay>

<ICN>
<Core>
	<BusinessDate>2017-06-01</BusinessDate>
	<ExtractId>3000002016110206MA01000021</ExtractId>
	<ProcessingParticipantId>888881</ProcessingParticipantId>
	<ExtMessageType>MSG06</ExtMessageType>
	<IntMessageType>06MA01</IntMessageType>
	<MessageSource>MO</MessageSource>
	<MessageDestination>IA</MessageDestination>
	<RecordCounts>2</RecordCounts>
</Core>

<Entities>

		<Entity>
			<EntityType>I</EntityType>
			<EntityId>AL87ME1704421222803037300</EntityId>
			<StateRevision>888881</StateRevision>
			<EntityState>250</EntityState>
			<SourceDateTime>2017-04-06T13:00:00+01:00</SourceDateTime>
		</Entity>
		<Entity>
			<EntityType>I</EntityType>
			<EntityId>AL87ME1704421222803037301</EntityId>
			<StateRevision>300003</StateRevision>
			<EntityState>240</EntityState>
			<SourceDateTime>2017-04-06T13:00:00+01:00</SourceDateTime>
		</Entity>


</Entities>
</ICN>
</Document>
'
              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [06MA01Message](@XMLString)
