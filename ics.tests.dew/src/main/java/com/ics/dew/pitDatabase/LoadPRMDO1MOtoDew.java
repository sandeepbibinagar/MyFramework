package com.ics.dew.pitDatabase;

import com.ics.externalFactoryUtilis.ICSDBUtilis;

public class LoadPRMDO1MOtoDew {

	public void scriptPRMD01Loading() throws ClassNotFoundException, InstantiationException, IllegalAccessException  
	
	{
			String sqlCommand = "cmd /c sqlcmd -S GBIBC-DT30-33-V\\SQL001 -d DEW -i D:/DEW/Load MSg06.sql";
			ICSDBUtilis.sqlCommandExecution(sqlCommand);	
	}
	
	
}
