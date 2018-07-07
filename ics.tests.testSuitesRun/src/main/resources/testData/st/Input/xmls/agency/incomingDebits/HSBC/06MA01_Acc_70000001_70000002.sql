DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML



SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
	 <ReqToPay>
    <GrpHdr>
      <MsgId>99999916278C:5762897407</MsgId>
      <CreDtTm>2017-06-02T14:28:45+01:00</CreDtTm>
      <NbOfTxs>2</NbOfTxs>
      <RcvrId>000632</RcvrId>
      <TstInd>false</TstInd>
      <Sgntr>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</Sgntr>
    </GrpHdr>  
  <TxSet>
		<TxSetId>2A2BCD1234567896234567</TxSetId>
		<TxSetVrsn>01</TxSetVrsn>
		<ColltngPtcptId>000632</ColltngPtcptId>
		<CaptrdDtTm>2017-06-02T13:00:00+01:00</CaptrdDtTm>
		<TxSetSubDtTm>2017-06-02T13:00:00+01:00</TxSetSubDtTm>
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
        <CdtItmId>CSHOA6162787138AAAAAAAAAA</CdtItmId>
        <CdtItmTp>IOCI</CdtItmTp>
        <Amt Ccy="GBP">1231.89</Amt>
        <BkCd>405168</BkCd>
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
			<DbtItmId>GHI1DE231067890f1GHi2JkLm</DbtItmId>
			<DbtItmTp>RTPI</DbtItmTp>
			<DbtItmTxCd>02</DbtItmTxCd>
			<RpresntdItmInd>0</RpresntdItmInd>
			<Amt Ccy="GBP">90.41</Amt>
			<BkCd>405168</BkCd>
			<AcctNb>70000001</AcctNb>
			<SrlNb>112341</SrlNb>
			<HghValItm>0</HghValItm>
			<DayOneRspnWndwStartDtTm>2017-06-02T13:00:00+01:00</DayOneRspnWndwStartDtTm>
			<DayOneRspnWndwEndDtTm>2017-06-02T23:51:00+01:00</DayOneRspnWndwEndDtTm>
			<DayTwoRspnWndwStartDtTm>2017-06-02T07:00:00+01:00</DayTwoRspnWndwStartDtTm>
			<DayTwoRspnWndwEndDtTm>2017-06-02T14:30:00+01:00</DayTwoRspnWndwEndDtTm>
			<XtrnlDataRef>xtrnalRefData</XtrnlDataRef>
			<ItmImgData>
				<Img>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</Img>
		<FrntImgQltyIndctnInd>false</FrntImgQltyIndctnInd>
				<BckImgQltyIndctnInd>false</BckImgQltyIndctnInd>
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
		<DbtDplctItm>
          <DplctItmId>999999171399284CIFREI9E3R</DplctItmId>
          <DbtDplctSts>FULL</DbtDplctSts>
          <DtFirstPresntd>2016-09-15</DtFirstPresntd>
          <MmbId>000632</MmbId>
          <OrgnlCaptrDt>2016-09-14</OrgnlCaptrDt>
          <OrgnlSrc>0010</OrgnlSrc>
        </DbtDplctItm>
		</DbtItm>
	</TxSet>
<TxSet>
		<TxSetId>2B2BCD1234567896234567</TxSetId>
		<TxSetVrsn>02</TxSetVrsn>
		<ColltngPtcptId>000632</ColltngPtcptId>
		<CaptrdDtTm>2017-06-02T13:00:00+01:00</CaptrdDtTm>
		<TxSetSubDtTm>2017-06-02T13:00:00+01:00</TxSetSubDtTm>
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
        <CdtItmId>CSHOA6162787138AAAAAAAAAB</CdtItmId>
        <CdtItmTp>IOCI</CdtItmTp>
        <Amt Ccy="GBP">1231.88</Amt>
        <BkCd>405168</BkCd>
        <AcctNb>89078908</AcctNb>
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
			<DbtItmId>GHI1DE231067890f1GHi2JkLa</DbtItmId>
			<DbtItmTp>RTPI</DbtItmTp>
			<DbtItmTxCd>02</DbtItmTxCd>
			<RpresntdItmInd>0</RpresntdItmInd>
			<Amt Ccy="GBP">90.42</Amt>
			<BkCd>405168</BkCd>
			<AcctNb>70000002</AcctNb>
			<SrlNb>112342</SrlNb>
			<HghValItm>0</HghValItm>
			<DayOneRspnWndwStartDtTm>2017-06-02T13:00:00+01:00</DayOneRspnWndwStartDtTm>
			<DayOneRspnWndwEndDtTm>2017-06-02T23:51:00+01:00</DayOneRspnWndwEndDtTm>
			<DayTwoRspnWndwStartDtTm>2017-06-02T07:00:00+01:00</DayTwoRspnWndwStartDtTm>
			<DayTwoRspnWndwEndDtTm>2017-06-02T14:30:00+01:00</DayTwoRspnWndwEndDtTm>
			<XtrnlDataRef>xtrnalRefData</XtrnlDataRef>
			<ItmImgData>
				<Img>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</Img>
				<FrntImgQltyIndctnInd>false</FrntImgQltyIndctnInd>
				<BckImgQltyIndctnInd>false</BckImgQltyIndctnInd>
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
	<BusinessDate>2017-06-01</BusinessDate>
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
			<EntityType>I</EntityType>
			<EntityId>GHI1DE231067890f1GHi2JkLm</EntityId>
			<StateRevision>100111</StateRevision>
			<EntityState>750</EntityState>
			<SourceDateTime>2017-06-02T13:00:00+01:00</SourceDateTime>
		</Entity>
	<Entity>
			<EntityType>I</EntityType>
			<EntityId>GHI1DE231067890f1GHi2JkLa</EntityId>
		  <StateRevision>100120</StateRevision>
			<EntityState>500</EntityState>
			<SourceDateTime>2017-06-02T13:00:00+01:00</SourceDateTime>
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