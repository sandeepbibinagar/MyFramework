 Use DEW62HSBC
 DECLARE @DialogHandle UNIQUEIDENTIFIER
 DECLARE @Request VARCHAR(MAX)
	
			
  DECLARE @Command NVARCHAR(500);
    DECLARE @ParmDefinition NVARCHAR(500);
    DECLARE @FileId BIGINT = 0;
    DECLARE @FileName NVARCHAR(512);
    DECLARE @Document VARCHAR(MAX);
    DECLARE @Files TABLE
        (
         [Id] BIGINT IDENTITY(1, 1),
         [subdirectory] NVARCHAR(512),
         [depth] INT,
         [isfile] BIT
        );                                          

    INSERT  INTO @Files
            EXEC [master].[sys].[xp_dirtree] 'W:\Incomingdebits_Automation\LBG\', 1, 1;

   SELECT  [subdirectory]
   FROM    @Files;

    WHILE (1=1)
    BEGIN	
        SELECT TOP 1
                @FileId = [Id],
                @FileName = [subdirectory]
        FROM    @Files
        WHERE   [Id]>@FileId
        ORDER BY [Id];



        IF @@ROWCOUNT=0
            BREAK;

        SET @Command = N'SELECT @Document = [BulkColumn] FROM OPENROWSET(BULK ''W:\Incomingdebits_Automation\LBG\' + @FileName + ''', SINGLE_CLOB) AS XmlDocument';
		SET @ParmDefinition = N'@Document VARCHAR(MAX) OUTPUT';
        EXECUTE [sys].[sp_executesql] @Command, @ParmDefinition, @Document OUT;				
            BEGIN DIALOG @DialogHandle
				FROM SERVICE [//DEW/MOReceiveService]
				TO SERVICE '//DEW/MOReceiveService'
				ON CONTRACT  [//DEW/MO/Contract]
				WITH ENCRYPTION = OFF;
				                                        
            SEND ON CONVERSATION @DialogHandle
				MESSAGE TYPE [06MD01Message]
				(@Document);
				end
		-- select *  FROM [dbo].[//DEW/MOReceiveQueue]

		-- RECEIVE TOP (1) [conversation_handle]FROM [dbo].[//DEW/MOReceiveQueue]	

		-- select *  from sys.transmission_queue order by enqueue_time desc;

		-- ALTER DATABASE [DEW] SET ENABLE_BROKER WITH ROLLBACK IMMEDIATE
	
	 -- select TOP 1 [message_body] FROM [dbo].[//DEW/MOReceiveQueue]
	 
	 
	 --RECEIVE TOP (1) [conversation_handle], [message_body], [message_type_name]
		--FROM [dbo].[//DEW/MOReceiveQueue]

		