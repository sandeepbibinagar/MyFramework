package com.ics.dew.pitDatabase;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class LoadMSG06MoToDew extends GenericMethodUtilis{
	
	
	
	/*public static String txSetIdTagName = "TxSetId";
	
	
	public static String duplicatesFromAPGFilesPath = "D:/DEW XML/";
	public static String duplicatesFromAPGFileName = "DuplicatesfromAPG.xml";
	
	public static String duplicatesFromAPGHeaderTagName = "TxSet";
	
	public static HashMap<String, String> getXSDFileTagwiseDataForResponseHeader() throws SAXException, IOException, ParserConfigurationException
	{
		NodeList nodeSetList = getNodeSetList(duplicatesFromAPGFilesPath, duplicatesFromAPGFileName, duplicatesFromAPGHeaderTagName);
		HashMap<String, String> SetTagWiseData = new LinkedHashMap<String, String>();
		for(int eachNodeSetEntry=0 ; eachNodeSetEntry<nodeSetList.getLength() ; eachNodeSetEntry++)
		{
			Node eachNode = nodeSetList.item(eachNodeSetEntry);
			if(eachNode.getNodeType()==Node.ELEMENT_NODE)
			{
				Element eachElement=(Element) eachNode;
				SetTagWiseData.put(txSetIdTagName, eachElement.getElementsByTagName(txSetIdTagName).item(0).getTextContent());	
				}
		}
		return SetTagWiseData;		
	}
	
	private static NodeList getNodeSetList(String duplicatesFromAPGFilesPath, String duplicatesFromAPGFileName, String elementsSetWithTagName) throws SAXException, IOException, ParserConfigurationException
	{
		return getParsedXMLData(getFileStreamWithoutExtension(duplicatesFromAPGFilesPath, duplicatesFromAPGFileName)).getElementsByTagName(elementsSetWithTagName);		
	}	
*/	
	/*public void scriptMSG06Loading() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException 
	
	{
			String sqlCommand = "cmd /c sqlcmd -S GBIBC-DT30-33-V\\SQL001 -d DEW -i //129.227.32.216/w$/DEW/06MD01 New/Load MSg06.sql";
			ICSDBUtilis.sqlCommandExecution(sqlCommand);	
	}*/
		
	
	public boolean query1() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException
		
	{
			boolean flag = true;
			String query = "select * from Base.EventLog";
			
			ResultSet rs = ICSProductDBConnection.getICSDBServerConnection(ICSPropertiesConfig.getDewServername(), ICSPropertiesConfig.getDewDatabaseName(), query);
			
			System.out.println(rs.getString(2));
			rs.next();
			
			System.out.println();
			
			while(rs.next())
			{
				
				if(!rs.getString(2).equals("XML Document Closed"))
				{
					System.out.println("3");
					flag = false;
					break;
				}
			}
			return flag;
	}
			
		
	}

	