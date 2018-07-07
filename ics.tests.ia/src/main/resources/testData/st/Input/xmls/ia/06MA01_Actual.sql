DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML

SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
 <ReqToPay>
    <GrpHdr>
      <MsgId>00000416278C:5762897408</MsgId>
      <CreDtTm>2017-05-24T14:28:45+01:00</CreDtTm>
      <NbOfTxs>100</NbOfTxs>
      <RcvrId>000004</RcvrId>
      <TstInd>true</TstInd>
      <Sgntr>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</Sgntr>
    </GrpHdr>
    <TxSet>
      <TxSetId>0000041736510009000001</TxSetId>
      <TxSetVrsn>01</TxSetVrsn>
      <ColltngPtcptId>000004</ColltngPtcptId>
      <CaptrdDtTm>2017-05-24T08:28:46+01:00</CaptrdDtTm>
      <TxSetSubDtTm>2017-05-24T14:28:46+01:00</TxSetSubDtTm>
      <Src>1000</Src>
      <ColltngBrnchLctn>1</ColltngBrnchLctn>
      <ColltngLctn>000004</ColltngLctn>
      <ChanlRskTp>RSKK</ChanlRskTp>
      <ChanlDesc>ChanlDesc</ChanlDesc>
      <ColltnPt>ColltnPt</ColltnPt>
      <ColltngBrnchRef>ColltngBrnchRef</ColltngBrnchRef>
      <NbOfItms>100</NbOfItms>
      <EndPtId>000004</EndPtId>
     
	  <CrdtItm>
        <CdtItmId>000004173651000CREDIT0001</CdtItmId>
        <CdtItmTp>CRDI</CdtItmTp>		
        <Amt Ccy="GBP">100.89</Amt>
        <BkCd>400005</BkCd>
        <AcctNb>40000005</AcctNb>
        <RefNb>12345678</RefNb>
        <CdtItmFrdData>
          <BnfcryNm>Lewis</BnfcryNm>
          <VrtlCdtInd>false</VrtlCdtInd>
          <RefData>REFDATA</RefData>
        </CdtItmFrdData>
        <RprdItm>
          <BkCdRprdInd>true</BkCdRprdInd>
          <AcctNbRprdInd>false</AcctNbRprdInd>
          <AmtRprdInd>false</AmtRprdInd>
          <SrlNbRprdInd>false</SrlNbRprdInd>
          <RefNbRprdInd>false</RefNbRprdInd>
        </RprdItm>		
        <DfltdItm>
          <BkCdDfltdInd>true</BkCdDfltdInd>
          <AcctNbDfltdInd>false</AcctNbDfltdInd>
          <RefNbDfltdInd>false</RefNbDfltdInd>
        </DfltdItm>
        <SwtchdItm>
          <BkCd>312345</BkCd>
          <AcctNb>98765432</AcctNb>
        </SwtchdItm>
      </CrdtItm>
	 
	 
	  
      <DbtItm>
        <DbtItmId>000004173651000DEBIT00001</DbtItmId>
        <DbtItmTp>RTPI</DbtItmTp>
        <DbtItmTxCd>26</DbtItmTxCd>
        <RpresntdItmInd>false</RpresntdItmInd>
        <Amt Ccy="GBP">7234.43</Amt>
        <BkCd>070012</BkCd>
        <AcctNb>10000001</AcctNb>
        <SrlNb>881230</SrlNb>
        <HghValItm>false</HghValItm>
        <DayOneRspnWndwStartDtTm>2017-05-24T11:15:43+01:00</DayOneRspnWndwStartDtTm>
        <DayOneRspnWndwEndDtTm>2017-05-24T11:15:43+01:00</DayOneRspnWndwEndDtTm>
        <DayTwoRspnWndwStartDtTm>2017-05-24T11:15:43+01:00</DayTwoRspnWndwStartDtTm>
        <DayTwoRspnWndwEndDtTm>2017-05-24T11:15:43+01:00</DayTwoRspnWndwEndDtTm>
        <XtrnlDataRef>XtrnlDataRef</XtrnlDataRef>
        <ItmImgData>
          <Img>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</Img>
          <FrntImgQltyIndctnInd>true</FrntImgQltyIndctnInd>
          <BckImgQltyIndctnInd>false</BckImgQltyIndctnInd>
        </ItmImgData>
        <DbtItmFrdData>
          <ChqAtRskInd>false</ChqAtRskInd>
          <DtOfFrstChq>2017-05-24</DtOfFrstChq>
          <DtOfLstChq>2017-05-24</DtOfLstChq>
          <NbOfCtrPtys>1</NbOfCtrPtys>
          <NbOfVldChqs>1</NbOfVldChqs>
          <NbOfFrdChqs>2</NbOfFrdChqs>
          <HghstAmt Ccy="GBP">999999999.34</HghstAmt>
          <RskInd>1234</RskInd>
        </DbtItmFrdData>
        <RprdItm>
          <BkCdRprdInd>false</BkCdRprdInd>
          <AcctNbRprdInd>true</AcctNbRprdInd>
          <AmtRprdInd>false</AmtRprdInd>
          <SrlNbRprdInd>false</SrlNbRprdInd>
          <RefNbRprdInd>false</RefNbRprdInd>
        </RprdItm>
        <DfltdItm>
          <BkCdDfltdInd>true</BkCdDfltdInd>
          <AcctNbDfltdInd>false</AcctNbDfltdInd>
          <SrlNbDfltdInd>false</SrlNbDfltdInd>
        </DfltdItm>
		<SwtchdItm>
          <BkCd>300008</BkCd>
          <AcctNb>30000008</AcctNb>
        </SwtchdItm>
		<DbtDplctItm>
		   <DplctItmId>000004173651000DEBIT00001</DplctItmId>
		   <DbtDplctSts>FULL</DbtDplctSts>
		   <DtFirstPresntd>2017-05-24</DtFirstPresntd>
		   <MmbId>AA1234</MmbId>
		   <OrgnlCaptrDt>2017-03-08</OrgnlCaptrDt>
		   <OrgnlSrc>2016</OrgnlSrc>
		</DbtDplctItm>
        <DbtStopdItm>
          <StopdDt>2017-03-08</StopdDt>
          <StopdSts>PARA</StopdSts>
		  <Amt Ccy="GBP">10000.49</Amt>
		  <BnfcryNm>Lewis John123 </BnfcryNm>
		  <StopItmStartRg>123456</StopItmStartRg>
		  <StopItmEndRg>100000</StopItmEndRg>
        </DbtStopdItm>
      </DbtItm>
    </TxSet>
  </ReqToPay>
<ICN>
 <Core>
     <BusinessDate>2017-05-24</BusinessDate>
     <ExtractId>ExID</ExtractId>
     <ProcessingParticipantId>000004</ProcessingParticipantId>
     <ExtMessageType>MSG06</ExtMessageType>
     <IntMessageType>06MA01</IntMessageType>
     <MessageSource>MO</MessageSource>
     <MessageDestination>IA</MessageDestination>
     <RecordCounts>2</RecordCounts>                       
</Core>
<Entities>
	
	<Entity>
		<EntityType>I</EntityType>
        <EntityId>EntID</EntityId>                                                 
        <StateRevision>100001</StateRevision>
       	<EntityState>500</EntityState>                                                   
        <SourceDateTime>2017-05-24T00:20:59+00:00</SourceDateTime> 
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
