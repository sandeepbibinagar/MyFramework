package com.ics.dew.db;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.ics.dew.pages.DewPendingPaymentsPage;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;

public class DewDbQueries extends ICSProductDBConnection {

	static String dewServerName= ICSPropertiesConfig.getDewServername();
	static String dewDBName = ICSPropertiesConfig.getDewDatabaseName();

	public static ResultSet itemDetails() throws Exception{

		String workstreamName = ICSPropertiesConfig.getDewPPWorkStreamValue() ; 
		String debitId = DewPendingPaymentsPage.getUniqueIDText();	

		String query = "SELECT DebitInWorkstreamSeq,DB.AccountNumber,DB.Sortcode,DB.SerialNumber,DB.Amount as amount"
				+ " ,UFR.Name As UserFailReason,SD1.Name As sysDefaultDec,DB.RepresentItemInd,DB.SwitchedAccountNumber"
				+ " ,DB.DebitId,TS.CollectingPId,TS.ChannelRiskType,CS.Name FROM [DEW].[Process].[DebitInWorkstream] DIW "
				+ " Left Join [DEW].[CFG].WorkStream WS on WS.WorkStreamSeq=DIW.WorkStreamSeq "
				+ " Left Join [DEW].[CFG].UserFailReason UFR on UFR.UserFailReasonSeq=DIW.UserFailReasonSeq"
				+ " Left join [DEW].[CFG].SystemDecision SD1 on SD1.SystemDecisionSeq=DIW.SystemDefaultDecisionSeq"
				+ " left join [DEW].Process.[06MD_Debit] DB on DB.DebitSeq=DIW.DebitSeq  "
				+ " left join [DEW].Process.[06MD_TxSet] TS on TS.TxSetSeq=(select TxSetSeq from Process.[06MD_Debit] "
				+ " where DebitSeq = (select DebitSeq from process.[06MD_Debit] where DebitId='"+debitId+"'))"
				+ " left join [DEW].CFG.[CustomerSegment] CS on CS.CustomerSegmentSeq=(select CustomerSegmentSeq from Process.[06MD_Debit_State]"
				+ " where DebitSeq =(select DebitSeq from process.[06MD_Debit] where DebitId='"+debitId+"'))"
				+ " where DIW.DebitSeq=(select DebitSeq from process.[06MD_Debit] where DebitId='"+debitId+"')"
				+ "and WS.Name='"+workstreamName+"'";

		ResultSet rs = getICSDBServerConnection(dewServerName, dewDBName, query);
		return rs;


	}


	public static ResultSet itemOwnershipDetails() throws Exception{


		String query = "select u.UserName , d.DebitInWorkstreamSeq from [DataAccess].[User] u left join process.DebitInWorkstream d on u.UserSeq=d.UserSeq where d.GroupSeq is not null";

		ResultSet rs = getICSDBServerConnection(dewServerName, dewDBName, query);
		return rs;


	}

	public static ResultSet itemOwnershipDetailsAfterNavigation(String debitinWorkstreamSeq) throws Exception{

		String query = " select userseq from Process.DebitInWorkstream where DebitInWorkstreamSeq='"+debitinWorkstreamSeq+"'";

		ResultSet resultSet = getICSDBServerConnection(dewServerName, dewDBName, query);
		return resultSet;


	}

	public static ResultSet itemCountOnWorkgroupWorkstream() throws Exception{

		String workGroupName = ICSPropertiesConfig.getDewWorkgroupValue(); 
		String workStreamName =	ICSPropertiesConfig.getDewWorkStreamValue();	

		String query = "select count(D.DebitInWorkstreamSeq) from process.DebitInWorkstream D where D.WorkstreamSeq=(select WS.WorkstreamSeq from CFG.Workstream WS where "
					+ "WS.Name='"+workStreamName+"' and WS.WorkGroupSeq=(select WG.WorkGroupSeq from CFG.WorkGroup WG where WG.Name='"+workGroupName+"'))" ;

		ResultSet resultSet = getICSDBServerConnection(dewServerName, dewDBName, query);
		return resultSet;


	}
	
	public static ResultSet itemsOnTimestamp() throws Exception{

/*		String timeStamp = ICSPropertiesConfig.getDewTimeStampValue(); */
		
/*		String query =  "select D.WorkstreamSeq,WS.Name from Process.DebitInWorkstream D  left join "
						+ "CFG.Workstream WS on (D.WorkstreamSeq = WS.WorkstreamSeq) where D.TimeStamp ='"+timeStamp+"'";
*/	
		
		
		/*Alternate Way if you do not want to pass timestamp manually*/
		
		String workGroupName = ICSPropertiesConfig.getDewWorkgroupValue(); 
		String workStreamName =	ICSPropertiesConfig.getDewWorkStreamValue();	
		String workStreamSDC = ICSPropertiesConfig.getDewWorkStreamSDCname();
		
		String query =  "select D.debitseq,WS.Name,D.WorkstreamSeq from Process.DebitInWorkstream D  left join "
				+ "	CFG.Workstream WS on (D.WorkstreamSeq = WS.WorkstreamSeq) where D.DebitSeq in "
				+ "(select debitseq from process.[06MD_Debit] where amount > ( select AmountFrom from cfg.Workstream "
				+ "where WorkGroupSeq =(select WorkGroupSeq from cfg.WorkGroup where name = '"+workGroupName+"')  and Name = "
				+ "'"+workStreamSDC+"')) and D.WorkstreamSeq = (select WorkstreamSeq from cfg.Workstream where"
				+ " name = '"+workStreamName+"' and WorkGroupSeq = ( select WorkGroupSeq from cfg.WorkGroup where Name ='"+workGroupName+"'"
				+ "	) )";
		
		
		ResultSet resultSet = getICSDBServerConnection(dewServerName, dewDBName, query);
		return resultSet;


	}

	
	
}
