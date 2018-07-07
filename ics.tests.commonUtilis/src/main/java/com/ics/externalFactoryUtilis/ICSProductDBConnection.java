package com.ics.externalFactoryUtilis;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/********************************************************************************************************************/
/* Created By: 	Umamahesh.Mulugu@ipsl.co.uk																			*/
/* Purpose:	 DB connection String methods   
 * Updated By : Himanshu Malhotra
 * Change Made : Changed to TYPE_SCROLL_INSENSITIVE
 * Reason for Change: It reflects the current updated result set in DB. 
 *                               								    	*/
/********************************************************************************************************************/
public class ICSProductDBConnection {	
	protected static Statement dbStatementInstance ; 
	protected static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	protected static ResultSet resultSet;
	protected static String tempResultValue;

	public static ResultSet getICSDBServerConnection(String serverName, String dbName, String dbQuery) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{		
		Statement dbStatementConnect  = dbConnect(serverName,dbName);
		if(dbStatementConnect.execute(dbQuery))
		{
			resultSet = dbStatementConnect.executeQuery(dbQuery);	
		}
		return resultSet;
	}
	
	public static ResultSet getICSDBServerConnectionCluster(String serverName, String dbName, String dbQuery) throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
	{		
		Statement dbStatementConnect  = dbConnectCluster(serverName,dbName);
		if(dbStatementConnect.execute(dbQuery))
		{
			resultSet = dbStatementConnect.executeQuery(dbQuery);	
		}
		return resultSet;
	}
	
	protected static Statement dbConnect(String serverName , String dBName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(driver).newInstance();
		String URL = "jdbc:sqlserver://"+serverName+";databaseName = "+dBName+";integratedSecurity = true";
		return DriverManager.getConnection(URL).createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);		
	}
	
	protected static Statement dbConnectCluster(String serverName , String dBName) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException
	{
		Class.forName(driver).newInstance();
		String URL = "jdbc:sqlserver://"+serverName+";databaseName = "+dBName+";integratedSecurity = true;selectmethod = direct"; 
		return DriverManager.getConnection(URL).createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);		
	}

}
