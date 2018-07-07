DECLARE @inc INT = 1
DECLARE @NoOfMsgs INT =75

DECLARE @MsgIdInc1 INT = 19990000

DECLARE @CrdtMsgId VARCHAR(25)
DECLARE @CrdtMsgId1 VARCHAR(25)
SET @CrdtMsgId1 ='CRE70016250CxIDCD'
				
				
DECLARE @CrdtAmt1 DECIMAL(14,2)
SET @CrdtAmt1 = 200.22

DECLARE @CrdtSrtCd1 INT
SET @CrdtSrtCd1 = 380738

DECLARE @CrdtAcctNbr1 INT
SET @CrdtAcctNbr1 = 39000738

DECLARE @CrdtReference1 BIGINT
SET @CrdtReference1 = 270000011115555367

DECLARE @DbtMsgId VARCHAR(25)
DECLARE @DbtMsgId1 VARCHAR(25)
SET @DbtMsgId1 = 'DEBT5016250DxIDDB'

DECLARE @DbtAmt1 DECIMAL(14,2)
SET @DbtAmt1 = 150.22 

DECLARE @DbtSrtCd1 INT
SET @DbtSrtCd1 = 380738

DECLARE @DbtAcctNbr1 INT
SET @DbtAcctNbr1 = 39000738

DECLARE @DbtReference1 BIGINT
SET @DbtReference1 = 370738


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

SET @XMLString = '<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
	<BnfcryPmtNtfctn>
    <GrpHdr>
      <MsgId>99999917027C:6682463861</MsgId>
      <CreDtTm>2016-11-04T13:24:38+00:00</CreDtTm>
      <NbOfTxs>1</NbOfTxs>
      <RcvrId>300000</RcvrId>
      <TstInd>true</TstInd>
      <Sgntr>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</Sgntr>
    </GrpHdr>
    <TxSet>
      <TxSetId>49PZSR1705895000000017</TxSetId>
      <TxSetVrsn>84</TxSetVrsn>
      <ColltngPtcptId>987667</ColltngPtcptId>
      <CaptrdDtTm>2016-11-04T13:24:38+00:00</CaptrdDtTm>
      <TxSetSubDtTm>2016-11-04T13:24:38+00:00</TxSetSubDtTm>
      <Src>1234</Src>
      <NbOfItms>2</NbOfItms>
      <EndPtId>300001</EndPtId>
      <CrdtItm>
        <CdtItmId>CRDTMSGID</CdtItmId>
        <CdtItmTp>CRDI</CdtItmTp>
        <CdtItmTxCd>AA</CdtItmTxCd>
        <Amt Ccy="GBP">CRDTAMT</Amt>
        <BkCd>CRDTSRTCD</BkCd>
        <AcctNb>CRDTACCTNBR</AcctNb>
        <RefNb>CRDTREFERENCE</RefNb>
        <XtrnlDataRef>xdata ref.</XtrnlDataRef>
        <ItmImgData>
          <Img>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</Img>
          <FrntImgQltyIndctnInd>true</FrntImgQltyIndctnInd>
          <BckImgQltyIndctnInd>false</BckImgQltyIndctnInd>
        </ItmImgData>
        <CdtItmFrdData>
          <ChqAtRskInd>true</ChqAtRskInd>
          <BnfcryNm>Robert</BnfcryNm>
          <VrtlCdtInd>false</VrtlCdtInd>
          <RefData>Ref.data</RefData>
          <CshAmt Ccy="GBP">10.10</CshAmt>
          <FnddAmt Ccy="GBP">20.20</FnddAmt>
          <NonFunddAmt Ccy="GBP">30.30</NonFunddAmt>
          <NbOfCdtsOrDbts>31</NbOfCdtsOrDbts>
        </CdtItmFrdData>
        <RprdItm>
          <BkCdRprdInd>false</BkCdRprdInd>
          <AcctNbRprdInd>true</AcctNbRprdInd>
          <AmtRprdInd>true</AmtRprdInd>
          <RefNbRprdInd>true</RefNbRprdInd>
        </RprdItm>
        <DfltdItm>
          <BkCdDfltdInd>false</BkCdDfltdInd>
          <AcctNbDfltdInd>true</AcctNbDfltdInd>
          <RefNbDfltdInd>true</RefNbDfltdInd>
        </DfltdItm>
        <SwtchdItm>
          <BkCd>413535</BkCd>
          <AcctNb>87654321</AcctNb>
        </SwtchdItm>
      </CrdtItm>
      <DbtItm>
        <DbtItmId>DBTMSGID</DbtItmId>
        <DbtItmTp>RTPI</DbtItmTp>
        <DbtItmTxCd>AB</DbtItmTxCd>
        <Amt Ccy="GBP">DBTAMT</Amt>
        <BkCd>DBTSRTCD</BkCd>
        <AcctNb>DBTACCTNBR</AcctNb>
        <SrlNb>DBTREFERENCE</SrlNb>
        <HghValItm>true</HghValItm>
        <DayOneRspnWndwStartDtTm>2016-10-03T09:39:51+01:00</DayOneRspnWndwStartDtTm>
        <DayOneRspnWndwEndDtTm>2016-10-03T09:39:51+01:00</DayOneRspnWndwEndDtTm>
        <DayTwoRspnWndwStartDtTm>2016-10-03T09:39:51+01:00</DayTwoRspnWndwStartDtTm>
        <DayTwoRspnWndwEndDtTm>2016-10-03T09:39:51+01:00</DayTwoRspnWndwEndDtTm>
        <PayDcsnInd>false</PayDcsnInd>
        <PayDcsnRsnCd>1580</PayDcsnRsnCd>
        <FrdRsnCd>F001</FrdRsnCd>
        <ItmImgData>
          <Img>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</Img>
        </ItmImgData>
        <DfltdItm>
          <BkCdDfltdInd>true</BkCdDfltdInd>
          <AcctNbDfltdInd>true</AcctNbDfltdInd>
          <SrlNbDfltdInd>true</SrlNbDfltdInd>
        </DfltdItm>
        <SwtchdItm>
          <BkCd>413535</BkCd>
          <AcctNb>87654321</AcctNb>
        </SwtchdItm>
      </DbtItm> 
    </TxSet>
  </BnfcryPmtNtfctn>

	<ICN>
		<Core>
			<BusinessDate>2016-01-21</BusinessDate>
			<ExtractId>ExID</ExtractId>
			<ProcessingParticipantId>300000</ProcessingParticipantId>
			<ExtMessageType>MSG13</ExtMessageType>
			<IntMessageType>13MA02</IntMessageType>
			<MessageSource>MO</MessageSource>  
			<MessageDestination>IA</MessageDestination>
			<RecordCounts>9809</RecordCounts>
		</Core>
		<Entities>
				
			<Entity>
				<EntityType>I</EntityType>                                  
				<EntityId>DBTMSGID</EntityId>
				<StateRevision>400001</StateRevision>
				<EntityState>250</EntityState>
				<SourceDateTime>2017-03-03T07:58:36+00:00</SourceDateTime>
			</Entity>

			<Entity>
				<EntityType>I</EntityType>                                  
				<EntityId>CRDTMSGID</EntityId>
				<StateRevision>400001</StateRevision>
				<EntityState>250</EntityState>
				<SourceDateTime>2017-03-03T07:58:36+00:00</SourceDateTime>
			</Entity>
		</Entities>
	</ICN>
</Document>



'SET @XMLString = REPLACE(@XMLString,'CRDTMSGID',@CrdtMsgId)
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

              SEND ON CONVERSATION @h MESSAGE TYPE [13MA02Message](@XMLString)

SET @inc += 1

--waitfor delay '00:00:01'
print @inc

END