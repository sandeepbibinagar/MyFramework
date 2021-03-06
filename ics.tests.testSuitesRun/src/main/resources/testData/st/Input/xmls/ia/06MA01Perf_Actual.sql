DECLARE @inc INT = 1
DECLARE @NoOfMsgs INT =300

DECLARE @MsgIdInc1 INT = 19990000

DECLARE @CrdtMsgId VARCHAR(25)
DECLARE @CrdtMsgId1 VARCHAR(25)
SET @CrdtMsgId1 = 'CREDI00001625DD98DCR'

DECLARE @CrdtAmt1 DECIMAL(14,2)
SET @CrdtAmt1 = 99.00 

DECLARE @CrdtSrtCd1 INT
SET @CrdtSrtCd1 = 380000

DECLARE @CrdtAcctNbr1 INT
SET @CrdtAcctNbr1 = 39000000

DECLARE @CrdtReference1 BIGINT
SET @CrdtReference1 = 370000011115555367

DECLARE @DbtMsgId VARCHAR(25)
DECLARE @DbtMsgId1 VARCHAR(25)
SET @DbtMsgId1 = 'DEBIT000016250998DDR'

DECLARE @DbtAmt1 DECIMAL(14,2)
SET @DbtAmt1 = 98.00 

DECLARE @DbtSrtCd1 INT
SET @DbtSrtCd1 = 360000

DECLARE @DbtAcctNbr1 INT
SET @DbtAcctNbr1 = 36000000

DECLARE @DbtReference1 BIGINT
SET @DbtReference1 = 370000


WHILE @inc <= @NoOfMsgs
BEGIN
DECLARE @h UNIQUEIDENTIFIER	
DECLARE @XMLString VARCHAR(MAX)

DECLARE @CrdtAmt DECIMAL(14,2)
DECLARE @CrdtSrtCd INT
DECLARE @CrdtAcctNbr INT
DECLARE @CrdtReference BIGINT
DECLARE @MsgIdInc INT
DECLARE @DbtAmt DECIMAL(14,2)
DECLARE @DbtSrtCd INT
DECLARE @DbtAcctNbr INT
DECLARE @DbtReference BIGINT


SET @MsgIdInc = @MsgIdInc1 + @inc
SET @CrdtMsgId = CONCAT(@CrdtMsgId1,CAST(@MsgIdInc AS VARCHAR(8)))
SET @CrdtAmt = @CrdtAmt1 + @inc * 9.98
SET @CrdtSrtCd  = @CrdtSrtCd1 + @inc
SET @CrdtAcctNbr = @CrdtAcctNbr1 + @inc
SET @CrdtReference = @CrdtReference1 + @inc
SET @MsgIdInc = @MsgIdInc1 + @inc
SET @DbtMsgId = CONCAT(@DbtMsgId1,CAST(@MsgIdInc AS VARCHAR(8)))
SET @DbtAmt = @DbtAmt1 + @inc * 9.98
SET @DbtSrtCd = @DbtSrtCd1 + @inc
SET @DbtAcctNbr = @DbtAcctNbr1 + @inc
SET @DbtReference = @DbtReference1 + @inc

if @CrdtSrtCd > 999999
set @CrdtSrtCd = 100000

if @DbtSrtCd > 999999
set @CrdtSrtCd = 100000

if @DbtReference > 999999
set @DbtReference = 100000

SET @XMLString = '<Document xmlns="urn:xsd:ipsl.ics">
  <ReqToPay>
    <GrpHdr>
      <MsgId>99999916266C:2478372449</MsgId>
      <CreDtTm>2016-09-22T16:23:27+01:00</CreDtTm>
      <NbOfTxs>1</NbOfTxs>
      <RcvrId>300000</RcvrId>
      <TstInd>true</TstInd>
      <Sgntr>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</Sgntr>
    </GrpHdr>
    <TxSet>
      <TxSetId>GMR29W1626615616927137</TxSetId>
      <TxSetVrsn>01</TxSetVrsn>
      <ColltngPtcptId>300001</ColltngPtcptId>
      <CaptrdDtTm>2016-09-22T16:23:27+01:00</CaptrdDtTm>
      <TxSetSubDtTm>2016-09-22T16:21:27+01:00</TxSetSubDtTm>
      <Src>1111</Src>
      <ColltngBrnchLctn>TownCntS</ColltngBrnchLctn>
      <ColltngLctn>Norrthmptn</ColltngLctn>
      <ChanlRskTp>None</ChanlRskTp>
      <ChanlDesc>Channel Description</ChanlDesc>
      <ColltnPt>MILTON KEYNES</ColltnPt>
      <ColltngBrnchRef>Collecting Branch Reference</ColltngBrnchRef>
      <NbOfItms>2</NbOfItms>
      <EndPtId>300001</EndPtId>
      <CrdtItm>
        <CdtItmId>CRDTMSGID</CdtItmId>
        <CdtItmTp>CRDI</CdtItmTp>
        <Amt Ccy="GBP">
          CRDTAMT
        </Amt>
        <BkCd>CRDTSRTCD</BkCd>
        <AcctNb>CRDTACCTNBR</AcctNb>
        <RefNb>CRDTREFERENCE</RefNb>
        <CdtItmFrdData>
          <BnfcryNm>Richi richards</BnfcryNm>
          <VrtlCdtInd>true</VrtlCdtInd>
          <RefData>Reference Data</RefData>
        </CdtItmFrdData>
        <RprdItm>
          <BkCdRprdInd>true</BkCdRprdInd>
          <AcctNbRprdInd>false</AcctNbRprdInd>
          <AmtRprdInd>false</AmtRprdInd>
          <SrlNbRprdInd>true</SrlNbRprdInd>
          <RefNbRprdInd>false</RefNbRprdInd>
        </RprdItm>
        <DfltdItm>
          <BkCdDfltdInd>false</BkCdDfltdInd>
          <AcctNbDfltdInd>true</AcctNbDfltdInd>
          <RefNbDfltdInd>true</RefNbDfltdInd>
        </DfltdItm>
        <SwtchdItm>
          <BkCd>301020</BkCd>
          <AcctNb>23452136</AcctNb>
        </SwtchdItm>
      </CrdtItm>
      <DbtItm>
        <DbtItmId>DBTMSGID</DbtItmId>
        <DbtItmTp>RTPI</DbtItmTp>
        <DbtItmTxCd>TP</DbtItmTxCd>
        <RpresntdItmInd>true</RpresntdItmInd>
        <Amt Ccy="GBP">CRDTAMT</Amt>
        <BkCd>DBTSRTCD</BkCd>
        <AcctNb>DBTACCTNBR</AcctNb>
        <SrlNb>DBTREFERENCE</SrlNb>
        <HghValItm>true</HghValItm>
        <DayOneRspnWndwStartDtTm>2016-06-03T09:20:36.027+01:00</DayOneRspnWndwStartDtTm>
        <DayOneRspnWndwEndDtTm>2016-06-04T09:20:36.027+01:00</DayOneRspnWndwEndDtTm>
        <DayTwoRspnWndwStartDtTm>2016-06-05T09:20:36.027+01:00</DayTwoRspnWndwStartDtTm>
        <DayTwoRspnWndwEndDtTm>2016-06-06T09:20:36.027+01:00</DayTwoRspnWndwEndDtTm>
        <XtrnlDataRef>XtrnlDataRef</XtrnlDataRef>
        <ItmImgData>
          <Img>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</Img>
          <FrntImgQltyIndctnInd>true</FrntImgQltyIndctnInd>
          <BckImgQltyIndctnInd>true</BckImgQltyIndctnInd>
        </ItmImgData>
        <DbtItmFrdData>
          <ChqAtRskInd>false</ChqAtRskInd>
          <DtOfFrstChq>2016-09-15</DtOfFrstChq>
          <DtOfLstChq>2016-09-15</DtOfLstChq>
          <NbOfCtrPtys>354</NbOfCtrPtys>
          <NbOfVldChqs>2</NbOfVldChqs>
          <NbOfFrdChqs>2</NbOfFrdChqs>
          <HghstAmt Ccy="GBP">100.00</HghstAmt>
          <RskInd>0011</RskInd>
        </DbtItmFrdData>
        <RprdItm>
          <BkCdRprdInd>true</BkCdRprdInd>
          <AcctNbRprdInd>false</AcctNbRprdInd>
          <AmtRprdInd>true</AmtRprdInd>
          <SrlNbRprdInd>false</SrlNbRprdInd>
          <RefNbRprdInd>false</RefNbRprdInd>
        </RprdItm>
        <DfltdItm>
          <BkCdDfltdInd>true</BkCdDfltdInd>
          <AcctNbDfltdInd>false</AcctNbDfltdInd>
          <SrlNbDfltdInd>true</SrlNbDfltdInd>
        </DfltdItm>
        <DbtDplctItm>
          <DplctItmId>999999162661956ORL3UPTNIN</DplctItmId>
          <DbtDplctSts>FULL</DbtDplctSts>
          <DtFirstPresntd>2016-09-15</DtFirstPresntd>
          <MmbId>JGCTNN</MmbId>
          <OrgnlCaptrDt>2016-09-14</OrgnlCaptrDt>
          <OrgnlSrc>0010</OrgnlSrc>
        </DbtDplctItm>
        <DbtStopdItm>
          <StopdDt>2016-09-15</StopdDt>
          <StopdSts>PARA</StopdSts>
          <Amt Ccy="GBP">100.00</Amt>
          <BnfcryNm>Richards</BnfcryNm>
          <StopItmStartRg>135792</StopItmStartRg>
          <StopItmEndRg>000509</StopItmEndRg>
        </DbtStopdItm>
      </DbtItm>
    </TxSet>	
  </ReqToPay>
  <ICN>
    <Core>
		<BusinessDate>2016-04-30</BusinessDate>
		<ExtractId>22222222222233343434343434</ExtractId>
		<ProcessingParticipantId>909838</ProcessingParticipantId>
		<ExtMessageType>MSG06</ExtMessageType>
		<IntMessageType>06MA01</IntMessageType>
		<MessageSource>MO</MessageSource>
		<MessageDestination>IA</MessageDestination>
		<RecordCounts>1</RecordCounts>		
    </Core>    
	<Entities>
		<Entity>
			<EntityType>I</EntityType>
			<EntityId>DBTMSGID</EntityId>
			<StateRevision>100000</StateRevision>
			<EntityState>261</EntityState>
			<SourceDateTime>2016-06-20T16:49:30+01:00</SourceDateTime>			
		</Entity>
		<Entity>
			<EntityType>I</EntityType>
			<EntityId>CRDTMSGID</EntityId>
			<StateRevision>100000</StateRevision>
			<EntityState>560</EntityState>
			<SourceDateTime>2016-06-20T16:49:30+01:00</SourceDateTime>			
		</Entity>		
	</Entities>
  </ICN>
</Document>'


SET @XMLString = REPLACE(@XMLString,'CRDTMSGID',@CrdtMsgId)
SET @XMLString = REPLACE(@XMLString,'CRDTAMT',@CrdtAmt)
SET @XMLString = REPLACE(@XMLString,'CRDTSRTCD',@CrdtSrtCd)
SET @XMLString = REPLACE(@XMLString,'CRDTACCTNBR',@CrdtAcctNbr)
SET @XMLString = REPLACE(@XMLString,'CRDTREFERENCE',@CrdtReference)

SET @XMLString = REPLACE(@XMLString,'DBTMSGID',@DbtMsgId)
SET @XMLString = REPLACE(@XMLString,'DBTAMT',@DbtAmt)
SET @XMLString = REPLACE(@XMLString,'DBTSRTCD',@DbtSrtCd)
SET @XMLString = REPLACE(@XMLString,'DBTACCTNBR',@DbtAcctNbr)
SET @XMLString = REPLACE(@XMLString,'DBTREFERENCE',@DbtReference)


DECLARE @XMLString1 XML

SET @XMLString1 = CAST(@XMLString AS XML)

BEGIN DIALOG CONVERSATION @h
		FROM SERVICE [//ImageArchive/SendService]
		TO SERVICE '//ImageArchive/ReceiveService'
		ON CONTRACT [//IA/MO/Contract]
		WITH ENCRYPTION=OFF;

		SEND ON CONVERSATION @h MESSAGE TYPE [06MA01Message](@XMLString1)

SET @inc += 1

--waitfor delay '00:00:01'
print @inc

END