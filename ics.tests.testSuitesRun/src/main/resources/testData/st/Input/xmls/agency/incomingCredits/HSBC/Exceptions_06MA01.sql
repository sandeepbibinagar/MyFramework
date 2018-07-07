DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML

SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
	<ReqToPay>
    <GrpHdr>
      <MsgId>99999916278C:5762897407</MsgId>
      <CreDtTm>2017-05-10T14:28:45+01:00</CreDtTm>
      <NbOfTxs>1</NbOfTxs>
      <RcvrId>000632</RcvrId>
      <TstInd>false</TstInd>
      <Sgntr>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</Sgntr>
    </GrpHdr>  
  <TxSet>
		<TxSetId>49HZSR1630992116456112</TxSetId>
		<TxSetVrsn>83</TxSetVrsn>
		<ColltngPtcptId>000632</ColltngPtcptId>
		<CaptrdDtTm>2017-05-02T13:00:00+01:00</CaptrdDtTm>
		<TxSetSubDtTm>2017-05-02T13:00:00+01:00</TxSetSubDtTm>
		<Src>1111</Src>
		<ColltngBrnchLctn>BrLocton</ColltngBrnchLctn>
		<ColltngLctn>CollectLOC</ColltngLctn>
		<ChanlRskTp>CHRK</ChanlRskTp>
		<ChanlDesc>Channel Desc</ChanlDesc>
		<ColltnPt>Collecting RefrPoint</ColltnPt>
		<ColltngBrnchRef>Coll Branch Ref</ColltngBrnchRef>
		<NbOfItms>30</NbOfItms>
		<EndPtId>END123</EndPtId>
    <CrdtItm>
        <CdtItmId>MN87ME1704421222803037615</CdtItmId>
        <CdtItmTp>IOCI</CdtItmTp>
        <Amt Ccy="GBP">102.82</Amt>
        <BkCd>403504</BkCd>
        <AcctNb>12345678</AcctNb>
        <RefNb>1234567890efghtyui</RefNb>
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
			<DbtItmId>MN4SH6163097446iDhj4cDX8A</DbtItmId>
			<DbtItmTp>RTPI</DbtItmTp>
			<DbtItmTxCd>02</DbtItmTxCd>
			<RpresntdItmInd>0</RpresntdItmInd>
        <Amt Ccy="GBP">50.81</Amt>
        <BkCd>403504/BkCd>
        <AcctNb>99839857</AcctNb>
        <SrlNb>123457</SrlNb>
			<HghValItm>0</HghValItm>
			<DayOneRspnWndwStartDtTm>2017-05-02T13:00:00+01:00</DayOneRspnWndwStartDtTm>
			<DayOneRspnWndwEndDtTm>2017-05-02T23:51:00+01:00</DayOneRspnWndwEndDtTm>
			<DayTwoRspnWndwStartDtTm>2017-05-02T07:00:00+01:00</DayTwoRspnWndwStartDtTm>
			<DayTwoRspnWndwEndDtTm>2017-05-02T14:30:00+01:00</DayTwoRspnWndwEndDtTm>
			<XtrnlDataRef>xtrnalRefData</XtrnlDataRef>
			<ItmImgData>
				<Img>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</Img>
				<FrntImgQltyIndctnInd>1</FrntImgQltyIndctnInd>
				<BckImgQltyIndctnInd>1</BckImgQltyIndctnInd>
			</ItmImgData>
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
	<BusinessDate>2017-05-10</BusinessDate>
	<ExtractId>3000002016110206MA01000021</ExtractId>
	<ProcessingParticipantId>000632</ProcessingParticipantId>
	<ExtMessageType>MSG06</ExtMessageType>
	<IntMessageType>06MA01</IntMessageType>
	<MessageSource>MO</MessageSource>
	<MessageDestination>IA</MessageDestination>
	<RecordCounts>2</RecordCounts>
	</Core>

<Entities>
		<Entity>
			<EntityType>T</EntityType>
			<EntityId>49HZSR1630992116456112</EntityId>
			<StateRevision>100300</StateRevision>
			<EntityState>500</EntityState>
			<SourceDateTime>2017-05-02T13:00:00+01:00</SourceDateTime>
		</Entity>
	<Entity>
			<EntityType>I</EntityType>
			<EntityId>MN87ME1704421222803037615</EntityId>
		  <StateRevision>100301</StateRevision>
			<EntityState>500</EntityState>
			<SourceDateTime>2017-05-02T13:00:00+01:00</SourceDateTime>
		</Entity>
	<Entity>
			<EntityType>I</EntityType>
			<EntityId>MN4SH6163097446iDhj4cDX8A</EntityId>
		  <StateRevision>100302</StateRevision>
			<EntityState>500</EntityState>
			<SourceDateTime>2017-05-02T13:00:00+01:00</SourceDateTime>
		</Entity>
</Entities>
</ICN>
</Document>' 
       
              BEGIN DIALOG CONVERSATION @h
              FROM SERVICE [//ImageArchive/SendService]
              TO SERVICE '//ImageArchive/ReceiveService'
              ON CONTRACT [//IA/MO/Contract]
              WITH ENCRYPTION=OFF;

              SEND ON CONVERSATION @h MESSAGE TYPE [06MA01Message](@XMLString)

