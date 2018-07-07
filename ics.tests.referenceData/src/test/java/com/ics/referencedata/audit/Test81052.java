package com.ics.referencedata.audit;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.testng.annotations.Test;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;

public class Test81052
{
	/*****************************************************************************************************************************/
	/* Test Case ID:	81052
	/* Test Case Name:	AUDIT:Verify whether entry is made into Audit table along with remarks when file is processed successfully.
	/* Author:			Darpan Yashlaha
	/* Reviewer: 		Jaspreet Singh
	/****************************************************************************************************************************/
	
	@Test
	public void test_81052() throws Exception
	{
		@SuppressWarnings("unused")
		String date, query, remarks = "";
		date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
		System.out.println(date);
		
		query = "select * from SysMon.Audit order by 1 desc";
		
		ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(ICSPropertiesConfig.getReferenceDataServer(), ICSPropertiesConfig.getReferenceDataFileDatabase(), query);
		
		while(rs.next())
		{
//			System.out.println(ICSPropertiesConfig.getReferenceDataDate());
			if(rs.getString(6).contains(ICSPropertiesConfig.getReferenceDataDate()))
			{
//				System.out.println(rs.getInt(1));
				remarks = rs.getString(8);
			}
			/*if(!remarks.isEmpty())
				present = true;*/
		}
		
	}

}
