DECLARE @h UNIQUEIDENTIFIER 
DECLARE @XMLString XML



SET @XMLString = '
<Document xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns="urn:xsd:ipsl.ics">
  <ReqToPay>
     <TxSet>
      <TxSetId>ASHOGY1600128149810238</TxSetId>
      <TxSetVrsn>01</TxSetVrsn>
      <ColltngPtcptId>COLPTT</ColltngPtcptId>
      <CaptrdDtTm>2016-12-09T14:28:46+01:00</CaptrdDtTm>
      <TxSetSubDtTm>2016-12-09T14:28:46+00:00</TxSetSubDtTm>
      <Src>1111</Src>
      <ColltngBrnchLctn>1</ColltngBrnchLctn>
      <ColltngLctn>ENG</ColltngLctn>
      <ChanlRskTp>RSKK</ChanlRskTp>
      <ChanlDesc>ChanlDesc</ChanlDesc>
      <ColltnPt>ColltnPt</ColltnPt>
      <ColltngBrnchRef>ColltngBrnchRef</ColltngBrnchRef>
      <NbOfItms>2</NbOfItms>
      <EndPtId>300000</EndPtId>
  

    <CrdtItm>
        <CdtItmId>CSHOA6162787138AAAAAAAAAA</CdtItmId>
        <CdtItmTp>IOCI</CdtItmTp>
        <Amt Ccy="GBP">1231.89</Amt>
        <BkCd>232323</BkCd>
        <AcctNb>89078907</AcctNb>
        <RefNb>RefNb</RefNb>
    </CrdtItm>
    

    <DbtItm>
        <DbtItmId>DSHOPQ162784331AAAAAAAA02</DbtItmId>
        <DbtItmTp>RTPI</DbtItmTp>
        <DbtItmTxCd>TP</DbtItmTxCd>
        <RpresntdItmInd>true</RpresntdItmInd>
        <Amt Ccy="GBP">123.43</Amt>
        <BkCd>145466</BkCd>
    </DbtItm>
    
</TxSet>
  </ReqToPay>
<ICN>
 <Core>
     <BusinessDate>20161104</BusinessDate>
     <ExtractId>20161104090909090909090909</ExtractId>
     <ProcessingParticipantId>300000</ProcessingParticipantId>
     <ExtMessageType>MSG06</ExtMessageType>
     <IntMessageType>06MA01</IntMessageType>
     <MessageSource>MO</MessageSource>
     <MessageDestination>IA</MessageDestination>
     <RecordCounts>1</RecordCounts>                       
</Core>
<Entities>
	<Entity>
		    <EntityType>I</EntityType>
        	<EntityId>DSHOPQ162784331AAAAAAAA01</EntityId>                                                 
        	<StateRevision>000001</StateRevision>
       		<EntityState>500</EntityState>                                                   
        	<SourceDateTime>2016-12-09T14:28:46+00:00</SourceDateTime> 
	</Entity>
	<Entity>
		    <EntityType>I</EntityType>
        	<EntityId>DSHOPQ162784331AAAAAAAA02</EntityId>                                                 
        	<StateRevision>000001</StateRevision>
       		<EntityState>500</EntityState>                                                   
        	<SourceDateTime>2016-12-09T14:28:46+00:00</SourceDateTime> 
	</Entity>
	<Entity>
		<EntityType>I</EntityType>
        	<EntityId>CSHOA6162787138AAAAAAAAAA</EntityId>                                                 
        	<StateRevision>000002</StateRevision>
       		<EntityState>500</EntityState>                                                   
        	<SourceDateTime>2016-12-09T14:28:46+00:00</SourceDateTime> 
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
