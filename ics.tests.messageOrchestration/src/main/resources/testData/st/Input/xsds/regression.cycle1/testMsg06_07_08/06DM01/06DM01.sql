USE MORepository
DECLARE @A AS XML(BASE.[06DM01])
SET @A = '<Document xmlns="urn:xsd:ipsl.ics" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema"><ICN><Core><BusinessDate>2017-07-13</BusinessDate><ExtractId>0000052017031606DM01000034</ExtractId><ProcessingParticipantId>000005</ProcessingParticipantId><ExtMessageType>MSG06</ExtMessageType><IntMessageType>06DM01</IntMessageType><MessageSource>DEW</MessageSource><MessageDestination>MO</MessageDestination><RecordCounts>3</RecordCounts></Core><Entities><Entity><EntityType>I</EntityType><EntityId>IFWFVZ171010000051ADMukT7</EntityId><EntityState>550</EntityState><SourceDateTime>2017-03-16T10:32:19.0000000+00:00</SourceDateTime></Entity><Entity><EntityType>I</EntityType><EntityId>IFWFVZ171010000052ADMukT7</EntityId><EntityState>560</EntityState><SourceDateTime>2017-03-16T10:32:19.0000000+00:00</SourceDateTime></Entity><Entity><EntityType>I</EntityType><EntityId>IFWFVZ171010000053ADMukT7</EntityId><EntityState>551</EntityState><SourceDateTime>2017-03-16T10:32:19.0000000+00:00</SourceDateTime></Entity></Entities><Items><Item><ItemId>IFWFVZ171010000051ADMukT7</ItemId><ProcessId>CharLength-25</ProcessId><OperatorId>CharLength-20</OperatorId><UpdateDateTime>2017-03-16T10:22:19.0000000+00:00</UpdateDateTime><PayDecision><PayDecision>true</PayDecision><PayDecisionReasonCode>A123</PayDecisionReasonCode></PayDecision></Item><Item><ItemId>IFWFVZ171010000052ADMukT7</ItemId><ProcessId>CharLength-25</ProcessId><OperatorId>CharLength-20</OperatorId><UpdateDateTime>2017-03-16T10:22:19.0000000+00:00</UpdateDateTime><PayDecision><PayDecision>true</PayDecision><PayDecisionReasonCode>A123</PayDecisionReasonCode></PayDecision></Item><Item><ItemId>IFWFVZ171010000053ADMukT7</ItemId><ProcessId>CharLength-25</ProcessId><OperatorId>CharLength-20</OperatorId><UpdateDateTime>2017-03-16T10:22:19.0000000+00:00</UpdateDateTime><PayDecision><PayDecision>true</PayDecision><PayDecisionReasonCode>A123</PayDecisionReasonCode></PayDecision></Item></Items></ICN></Document>'