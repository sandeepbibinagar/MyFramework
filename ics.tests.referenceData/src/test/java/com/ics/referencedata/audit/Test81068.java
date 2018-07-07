package com.ics.referencedata.audit;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.testng.annotations.Test;

import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;

public class Test81068
{
	/*****************************************************************************************************************************/
	/* Test Case ID:	81068
	/* Test Case Name:	AUDIT:Verify entry made into Audit table when the file is not processed by SSIS package.
	/* Author:			Darpan Yashlaha
	/* Reviewer: 		Jaspreet Singh
	/****************************************************************************************************************************/
	
	@Test
	public void test_81068() throws Exception
	{
		@SuppressWarnings("unused")
		String date, query, remarks = "";
		date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		System.out.println(date);
		
		query = "select * from SysMon.Audit order by 1 desc";
		
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(ICSPropertiesConfig.getReferenceDataServer(), ICSPropertiesConfig.getReferenceDataFileDatabase(), query);
		
		while(rs.next())
		{
			if(rs.getString(6).contains(ICSPropertiesConfig.getReferenceDataDate()))
			{
//				System.out.println(rs.getInt(1));
				remarks = rs.getString(8);
			}
			/*if(remarks.contains(ICSPropertiesConfig.getReferenceDataFileRemarks()))
				present = true;*/
		}
		
	}

}
