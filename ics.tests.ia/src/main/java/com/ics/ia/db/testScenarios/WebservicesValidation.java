package com.ics.ia.db.testScenarios;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.ResultSet;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

import org.testng.*;

public class WebservicesValidation extends GenericMethodUtilis{

	public static String query="";
	public static boolean flag;
	public static String server =getIAValueFromDataSheet("server");
	public static String db = getIAValueFromDataSheet("db_r2");
	
	
	public static void runWebLoader_ItemSearchQuery_UII(String apiType, String serviceName) throws Exception
	{
		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
					}
					public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
					}
				}
		};
		try
		{
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			String input = "{\"iPSLHeader\": {\"BankID\": \"123123\",\"BusinessDate\": \"20160808\",\"FinancialInstitutionID\": \"1\",\"MessageID\": \"060505121212\",\"ProcessID\": \"WebU\",\"ProductID\": \"WebU\",\"RequestingUserID\": \"1\",\"SourceLocation\": \"1\",\"SourceSubLocation\": \"1\",}," + "\"iPSLRequestBody\":{\"UniqueItemIdentifier\": \"" +(ICSPropertiesConfig.InputUII_api())+"\"}}";
			URL url = new URL("https://gbibc-dt03-03-v.devipsl.eu.uis.unisys.com/" + apiType + "/" + serviceName);
			HttpURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			//con.setRequestProperty("User-Agent", "Fiddler");
			//con.setRequestProperty("Accept", "*");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			//con.setRequestProperty("Authorization", "Basic " + new Base64Encoder().encode(("Svc-FWDalUser1:r3vi3W123*").getBytes(StandardCharsets.UTF_8)));			
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(input);
			osw.close();
			int i = 0;
			System.out.println("HTTP Status Code : "+con.getResponseCode());
			System.out.println("Status Message : "+con.getResponseMessage());
			while((con.getResponseCode() != HttpURLConnection.HTTP_OK) && i<=10)
			{
				System.out.println(con.getResponseCode());
				Thread.sleep(2000);
				i++;
				if(con.getResponseCode() != HttpURLConnection.HTTP_OK){
				Assert.assertEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK);
				}
			//	throw new Exception("Failed with error code: " + con.getResponseCode());
			}
			/*if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
			{*/
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String output;
				StringBuffer op = new StringBuffer();
				while((output = br.readLine()) != null)
				{
					System.out.println("Response Output : "+output);
					String businessDate=output.substring(output.indexOf("ProcessingDate")+16).substring(1,9).trim();
					System.out.println("Business date : "+businessDate);
					String uii=output.substring(output.indexOf("UniqueItemIdentifier")+22).substring(1,26).trim();
					System.out.println("UII : "+uii);
					String serial=output.substring(output.indexOf("SerialReference")+17).substring(1,7).trim();
					System.out.println("Serial Reference : "+serial);
					String sortcode=output.substring(output.indexOf("SortCode")+10).substring(1,7).trim();
					System.out.println("SortCode : "+sortcode);
					String accnumber=output.substring(output.indexOf("Account")+9).substring(1,9).trim();
					System.out.println("Account : "+accnumber);
					String collPId=output.substring(output.indexOf("CollectingParticipantIdentifier")+33).substring(1,7).trim();
					System.out.println("Collecting Participant Identifier : "+collPId);
					String trancode=output.substring(output.indexOf("TransactionCode")+17).substring(1,3).trim();
					System.out.println("Transaction Code : "+trancode);
					String tsetid=output.substring(output.indexOf("TransactionSetIdentifier")+26).substring(1,25).trim();
					System.out.println("Transaction SetId: "+tsetid);
					String channeltype=output.substring(output.indexOf("ChannelRiskType")+17).substring(1,5).trim();
					System.out.println("Channel Risk Type: "+channeltype);
					String collbranchref=output.substring(output.indexOf("CollectingBranchReference")+27).substring(1,256).trim();
					System.out.println("CollectingBranch Reference: "+collbranchref);
					String collbranchloc=output.substring(output.indexOf("CollectingBranchLocation")+26).substring(1,9).trim();
					System.out.println("CollectingBranch Location: "+collbranchloc);
					String collloc=output.substring(output.indexOf("CollectingLocation")+20).substring(1,11).trim();
					System.out.println("Collecting Location: "+collloc);
					query ="select * from base.FinalDebit d join base.vw_TXSet t on d.InternalTxId=t.InternalTxId"
							+ " where DebitId='"+ICSPropertiesConfig.InputUII_api()+"'";	
					System.out.println(query );
					ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnectionCluster(server, db, query);
					int count =0;
					while(rsJob.next())
					{
						flag= Component.verifyEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK, "HTTP Status code validation");
						publishResults(flag, Integer.toString(con.getResponseCode()), Integer.toString(HttpURLConnection.HTTP_OK), "HTTP Status code validation");
						flag= Component.verifyEquals(rsJob.getString("DebitId"), uii, "UII validation");
						publishResults(flag, rsJob.getString("DebitId"), uii, "UII value validation");
						flag= Component.verifyEquals(rsJob.getString("ItemId").substring(0,8), businessDate, "Business Date validation");
						publishResults(flag, rsJob.getString("ItemId").substring(0,8), businessDate, "Business Date validation");
						flag= Component.verifyEquals(rsJob.getString("Serial"), serial, "Serial validation");
						publishResults(flag, rsJob.getString("Serial"), serial, "Serial validation");
						flag= Component.verifyEquals(rsJob.getString("Sortcode"), sortcode, "Sort Code validation");
						publishResults(flag, rsJob.getString("Sortcode"), sortcode, "Sort Code validation");
						flag= Component.verifyEquals(rsJob.getString("AccountNumber"), accnumber, "Account Number validation");
						publishResults(flag, rsJob.getString("AccountNumber"), accnumber, "AccountNumber validation");
						flag= Component.verifyEquals(rsJob.getString("CollectingParticipantId"), collPId, "Collecting Participant Identifier validation");
						publishResults(flag, rsJob.getString("CollectingParticipantId"), collPId, "Collecting Participant Identifier validation");
						flag= Component.verifyEquals(rsJob.getString("TranCode"), trancode, "TransactionCode validation");
						publishResults(flag, rsJob.getString("TranCode"), trancode, "TransactionCode validation");
						flag= Component.verifyEquals(rsJob.getString("TransactionSetIdWithVersion"), tsetid, "TransactionSetIdentifier validation");
						publishResults(flag, rsJob.getString("TransactionSetIdWithVersion"), tsetid, "TransactionSetIdentifier validation");
						flag= Component.verifyEquals(rsJob.getString("ChannelRiskType"), channeltype, "ChannelRiskType validation");
						publishResults(flag, rsJob.getString("ChannelRiskType"), channeltype, "ChannelRiskType validation");
						flag= Component.verifyEquals(rsJob.getString("CollectionBranchRef"), collbranchref, "CollectingBranchReference validation");
						publishResults(flag, rsJob.getString("CollectionBranchRef"), collbranchref, "CollectingBranchReference validation");
						flag= Component.verifyEquals(rsJob.getString("CollectingBranchLocation"), collbranchloc, "CollectingBranchLocation validation");
						publishResults(flag, rsJob.getString("CollectingBranchLocation"), collbranchloc, "CollectingBranchLocation validation");
						flag= Component.verifyEquals(rsJob.getString("CollectingLocation"), collloc, "CollectingLocation validation");
						publishResults(flag, rsJob.getString("CollectingLocation"), collloc, "CollectingLocation validation");
					}
				}
				br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	
	public static void runWebLoader_ItemSearchQuery_BusinessDateAccount(String apiType, String serviceName) throws Exception
	{
		TrustManager[] trustAllCerts = new TrustManager[]{
				new X509TrustManager() {
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}
					public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
					}
					public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
					}
				}
		};
		try
		{
			SSLContext sc = SSLContext.getInstance("SSL");
			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
			String input = "{\"iPSLHeader\": {\"BankID\": \"123123\",\"BusinessDate\": \"20160808\",\"FinancialInstitutionID\": \"1\",\"MessageID\": \"060505121212\",\"ProcessID\": \"WebU\",\"ProductID\": \"WebU\",\"RequestingUserID\": \"1\",\"SourceLocation\": \"1\",\"SourceSubLocation\": \"1\",}," + "\"iPSLRequestBody\":{\"ProcessingDate\": \""+"20170911"+"\",\"Account\": \""+"12345678"+"\"}}";
			URL url = new URL("https://gbibc-dt03-03-v.devipsl.eu.uis.unisys.com/" + apiType + "/" + serviceName);
			HttpURLConnection con = (HttpsURLConnection) url.openConnection();
			con.setDoInput(true);
			con.setDoOutput(true);
			con.setRequestMethod("POST");
			//con.setRequestProperty("User-Agent", "Fiddler");
			//con.setRequestProperty("Accept", "*");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
			//con.setRequestProperty("Authorization", "Basic " + new Base64Encoder().encode(("Svc-FWDalUser1:r3vi3W123*").getBytes(StandardCharsets.UTF_8)));			
			OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
			osw.write(input);
			osw.close();
			int i = 0;
			System.out.println("HTTP Status Code : "+con.getResponseCode());
			System.out.println("Status Message : "+con.getResponseMessage());
			while((con.getResponseCode() != HttpURLConnection.HTTP_OK) && i<=10)
			{
				System.out.println(con.getResponseCode());
				Thread.sleep(2000);
				i++;
			//	throw new Exception("Failed with error code: " + con.getResponseCode());
			}
			/*if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
			{*/
				BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
				String output;
				StringBuffer op = new StringBuffer();
				while((output = br.readLine()) != null)
				{
					System.out.println("Response Output : "+output);
					String bdate=output.substring(output.indexOf("BankID")+8).substring(1,7).trim();
					System.out.println("Bank ID : "+bdate);
					String mid=output.substring(output.indexOf("MessageID")+11).substring(1,13).trim();
					System.out.println("Message ID : "+mid);
					String CountOfItemsIncluded=output.substring(output.indexOf("CountOfItemsIncluded")+21).substring(1,2).trim();
					System.out.println("Count of Items : "+CountOfItemsIncluded);
					String businessDate=output.substring(output.indexOf("ProcessingDate")+16).substring(1,9).trim();
					System.out.println("Business date : "+businessDate);
					String uii=output.substring(output.indexOf("UniqueItemIdentifier")+22).substring(1,26).trim();
					System.out.println("UII : "+uii);
					String serial=output.substring(output.indexOf("SerialReference")+17).substring(1,7).trim();
					System.out.println("Serial Reference : "+serial);
					String sortcode=output.substring(output.indexOf("SortCode")+10).substring(1,7).trim();
					System.out.println("SortCode : "+sortcode);
					String accnumber=output.substring(output.indexOf("Account")+9).substring(1,9).trim();
					System.out.println("Account : "+accnumber);
					String collPId=output.substring(output.indexOf("CollectingParticipantIdentifier")+33).substring(1,7).trim();
					System.out.println("Collecting Participant Identifier : "+collPId);
					query ="select * from base.FinalDebit "
							+ " where AccountNumber='"+ICSPropertiesConfig.InputAccount_api()+"' and ItemId like '20170911%'";	
					System.out.println(query );
					ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnectionCluster(server, db, query);
					//int count =0;
					while(rsJob.next())
					{
						
						flag= Component.verifyEquals(rsJob.getString("DebitId"), uii, "UII validation");
						publishResults(flag, rsJob.getString("DebitId"), uii, "UII value validation");
						flag= Component.verifyEquals(rsJob.getString("ItemId").substring(0,8), businessDate, "Business Date validation");
						publishResults(flag, rsJob.getString("ItemId").substring(0,8), businessDate, "Business Date validation");
						flag= Component.verifyEquals(rsJob.getString("Serial"), serial, "Serial validation");
						publishResults(flag, rsJob.getString("Serial"), serial, "Serial validation");
						flag= Component.verifyEquals(rsJob.getString("Sortcode"), sortcode, "Sort Code validation");
						publishResults(flag, rsJob.getString("Sortcode"), sortcode, "Sort Code validation");
						flag= Component.verifyEquals(rsJob.getString("AccountNumber"), accnumber, "Account Number validation");
						publishResults(flag, rsJob.getString("AccountNumber"), accnumber, "AccountNumber validation");
						flag= Component.verifyEquals(rsJob.getString("CollectingParticipantId"), collPId, "Collecting Participant Identifier validation");
						publishResults(flag, rsJob.getString("CollectingParticipantId"), collPId, "Collecting Participant Identifier validation");
					}
				}
				
				br.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public static void runWebLoader_GetImageQuery(String apiType, String serviceName) throws Exception
	{	TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
			}
	};
	try
	{
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		String input = "{\"iPSLHeader\": {\"BankID\": \"123123\",\"BusinessDate\": \"20160808\",\"FinancialInstitutionID\": \"1\",\"MessageID\": \"060505121212\",\"ProcessID\": \"WebU\",\"ProductID\": \"WebU\",\"RequestingUserID\": \"1\",\"SourceLocation\": \"1\",\"SourceSubLocation\": \"1\",}," + "\"iPSLRequestBody\":{\"UniqueItemIdentifier\": \"" +(ICSPropertiesConfig.InputUII_api())+"\"}}";
		URL url = new URL("https://gbibc-dt03-03-v.devipsl.eu.uis.unisys.com/" + apiType + "/" + serviceName);
		HttpURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", "Fiddler");
		//con.setRequestProperty("Accept", "*");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		//con.setRequestProperty("Authorization", "Basic " + new Base64Encoder().encode(("Svc-FWDalUser1:r3vi3W123*").getBytes(StandardCharsets.UTF_8)));			
		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(input);
		osw.close();
		int i = 0;
		System.out.println("HTTP Status Code : "+con.getResponseCode());
		System.out.println("Status Message : "+con.getResponseMessage());
		while((con.getResponseCode() != HttpURLConnection.HTTP_OK) && i<=10)
		{
			System.out.println(con.getResponseCode());
			Thread.sleep(2000);
			i++;
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK){
			Assert.assertEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK);
			}
		//	throw new Exception("Failed with error code: " + con.getResponseCode());
		}
		/*if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
		{*/
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer op = new StringBuffer();
			while((output = br.readLine()) != null)
			{
				System.out.println("Response Output : "+output);
				String uii=output.substring(output.indexOf("UniqueItemIdentifier")+22).substring(1,26).trim();
				System.out.println("UII : "+uii);
				String imageType=output.substring(output.indexOf("ImageType")+11).substring(1,6).trim();
				System.out.println("Image Type : "+imageType);
				String image=output.substring(output.indexOf("Images")+8).substring(1,6740).trim();
				System.out.println("Image : "+image);
				query ="select * from base.FinalDebit d join base.Image i on d.ItemId=i.ItemId"
						+ " where DebitId='"+ICSPropertiesConfig.InputUII_api()+"'";	
				System.out.println(query );
				ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnectionCluster(server, db, query);
				//int count =0;
				while(rsJob.next())
				{
					flag= Component.verifyEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK, "HTTP Status code validation");
					publishResults(flag, Integer.toString(con.getResponseCode()), Integer.toString(HttpURLConnection.HTTP_OK), "HTTP Status code validation");
					flag= Component.verifyEquals(rsJob.getString("DebitId"), uii, "UII validation");
					publishResults(flag, rsJob.getString("DebitId"), uii, "UII value validation");
					flag= Component.verifyEquals(rsJob.getString("Image"), image, "Image validation");
					publishResults(flag, rsJob.getString("Image"), image, "Image validation");
					flag= Component.verifyEquals(rsJob.getString("ImageType"), imageType, "ImageType validation");
					publishResults(flag, rsJob.getString("ImageType"), imageType, "ImageType validation");
					
				}
			}
			
			br.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	
	public static void runWebLoader_GetRepairedItemDetailsQuery(String apiType, String serviceName) throws Exception
	{	TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
			}
	};
	try
	{
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		String input = "{\"iPSLHeader\": {\"BankID\": \"123123\",\"BusinessDate\": \"20160808\",\"FinancialInstitutionID\": \"1\",\"MessageID\": \"060505121212\",\"ProcessID\": \"WebU\",\"ProductID\": \"WebU\",\"RequestingUserID\": \"1\",\"SourceLocation\": \"1\",\"SourceSubLocation\": \"1\",}," + "\"iPSLRequestBody\":{\"UniqueItemIdentifier\": \"" +(ICSPropertiesConfig.InputUII_api())+"\"}}";
		URL url = new URL("https://gbibc-dt03-03-v.devipsl.eu.uis.unisys.com/" + apiType + "/" + serviceName);
		HttpURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", "Fiddler");
		//con.setRequestProperty("Accept", "*");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		//con.setRequestProperty("Authorization", "Basic " + new Base64Encoder().encode(("Svc-FWDalUser1:r3vi3W123*").getBytes(StandardCharsets.UTF_8)));			
		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(input);
		osw.close();
		int i = 0;
		System.out.println("HTTP Status Code : "+con.getResponseCode());
		System.out.println("Status Message : "+con.getResponseMessage());
		while((con.getResponseCode() != HttpURLConnection.HTTP_OK) && i<=10)
		{
			System.out.println(con.getResponseCode());
			Thread.sleep(2000);
			i++;
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK){
			Assert.assertEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK);
			}
		//	throw new Exception("Failed with error code: " + con.getResponseCode());
		}
		/*if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
		{*/
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer op = new StringBuffer();
			while((output = br.readLine()) != null)
			{
				System.out.println("Response Output : "+output);
				String uii=output.substring(output.indexOf("UniqueItemIdentifier")+22).substring(1,26).trim();
				System.out.println("UII : "+uii);
				String accRepaired=output.substring(output.indexOf("AccountNumberRepaired")+22).substring(1,2).trim();
				System.out.println("AccountNumber Repaired : "+accRepaired);
				String amtRepaired=output.substring(output.indexOf("AmountRepaired")+15).substring(1,2).trim();
				System.out.println("Amount Repaired : "+amtRepaired);
				String serialRepaired=output.substring(output.indexOf("SerialReferenceRepaired")+25).substring(1,2).trim();
				System.out.println("SerialReference Repaired : "+serialRepaired);
				String sortcodeRepaired=output.substring(output.indexOf("SortCodeRepaired")+17).substring(1,2).trim();
				System.out.println("SortCode Repaired : "+sortcodeRepaired);
				query ="select * from base.FinalDebit"
						+ " where DebitId='"+ICSPropertiesConfig.InputUII_api()+"'";	
				System.out.println(query );
				ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnectionCluster(server, db, query);
				//int count =0;
				while(rsJob.next())
				{
					flag= Component.verifyEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK, "HTTP Status code validation");
					publishResults(flag, Integer.toString(con.getResponseCode()), Integer.toString(HttpURLConnection.HTTP_OK), "HTTP Status code validation");
					flag= Component.verifyEquals(rsJob.getString("DebitId"), uii, "UII validation");
					publishResults(flag, rsJob.getString("DebitId"), uii, "UII value validation");
					flag= Component.verifyEquals(rsJob.getString("RepairedSortcode"), sortcodeRepaired, "RepairedSortcode validation");
					publishResults(flag, rsJob.getString("RepairedSortcode"), sortcodeRepaired, "RepairedSortcode validation");
					flag= Component.verifyEquals(rsJob.getString("RepairedSerial"), serialRepaired, "RepairedSerial validation");
					publishResults(flag, rsJob.getString("RepairedSerial"), serialRepaired, "RepairedSerial validation");
					flag= Component.verifyEquals(rsJob.getString("RepairedAccount"), accRepaired, "RepairedAccount validation");
					publishResults(flag, rsJob.getString("RepairedAccount"), accRepaired, "RepairedAccount validation");
					flag= Component.verifyEquals(rsJob.getString("RepairedAmount"), amtRepaired, "AmountRepaired validation");
					publishResults(flag, rsJob.getString("RepairedAmount"), amtRepaired, "AmountRepaired validation");
				}
			}
			
			br.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	

	public static void runWebLoader_GetDefaultedItemDetailsQuery(String apiType, String serviceName) throws Exception
	{	TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
			}
	};
	try
	{
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		String input = "{\"iPSLHeader\": {\"BankID\": \"123123\",\"BusinessDate\": \"20160808\",\"FinancialInstitutionID\": \"1\",\"MessageID\": \"060505121212\",\"ProcessID\": \"WebU\",\"ProductID\": \"WebU\",\"RequestingUserID\": \"1\",\"SourceLocation\": \"1\",\"SourceSubLocation\": \"1\",}," + "\"iPSLRequestBody\":{\"UniqueItemIdentifier\": \"" +(ICSPropertiesConfig.InputUII_api())+"\"}}";
		URL url = new URL("https://gbibc-dt03-03-v.devipsl.eu.uis.unisys.com/" + apiType + "/" + serviceName);
		HttpURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", "Fiddler");
		//con.setRequestProperty("Accept", "*");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		//con.setRequestProperty("Authorization", "Basic " + new Base64Encoder().encode(("Svc-FWDalUser1:r3vi3W123*").getBytes(StandardCharsets.UTF_8)));			
		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(input);
		osw.close();
		int i = 0;
		System.out.println("HTTP Status Code : "+con.getResponseCode());
		System.out.println("Status Message : "+con.getResponseMessage());
		while((con.getResponseCode() != HttpURLConnection.HTTP_OK) && i<=10)
		{
			System.out.println(con.getResponseCode());
			Thread.sleep(2000);
			i++;
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK){
			Assert.assertEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK);
			}
		//	throw new Exception("Failed with error code: " + con.getResponseCode());
		}
		/*if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
		{*/
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer op = new StringBuffer();
			while((output = br.readLine()) != null)
			{
				System.out.println("Response Output : "+output);
				String uii=output.substring(output.indexOf("UniqueItemIdentifier")+22).substring(1,26).trim();
				System.out.println("UII : "+uii);
				String accDefaulted=output.substring(output.indexOf("AccountNumberDefaulted")+23).substring(1,2).trim();
				System.out.println("AccountNumber Defaulted : "+accDefaulted);
				String serialDefaulted=output.substring(output.indexOf("SerialReferenceDefaulted")+25).substring(1,2).trim();
				System.out.println("SerialReference Defaulted : "+serialDefaulted);
				String sortDefaulted=output.substring(output.indexOf("SortCodeDefaulted")+18).substring(1,2).trim();
				System.out.println("SortCode Defaulted : "+sortDefaulted);
				query ="select * from base.FinalDebit"
						+ " where DebitId='"+ICSPropertiesConfig.InputUII_api()+"'";	
				System.out.println(query );
				ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnectionCluster(server, db, query);
			//	int count =0;
				while(rsJob.next())
				{
					flag= Component.verifyEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK, "HTTP Status code validation");
					publishResults(flag, Integer.toString(con.getResponseCode()), Integer.toString(HttpURLConnection.HTTP_OK), "HTTP Status code validation");
					flag= Component.verifyEquals(rsJob.getString("DebitId"), uii, "UII validation");
					publishResults(flag, rsJob.getString("DebitId"), uii, "UII value validation");
					flag= Component.verifyEquals(rsJob.getString("DefaultedSortcode"), sortDefaulted, "DefaultedSortcode validation");
					publishResults(flag, rsJob.getString("DefaultedSortcode"), sortDefaulted, "DefaultedSortcode validation");
					flag= Component.verifyEquals(rsJob.getString("DefaultedSerialNumber"), serialDefaulted, "DefaultedSerialNumber validation");
					publishResults(flag, rsJob.getString("DefaultedSerialNumber"), serialDefaulted, "DefaultedSerialNumber validation");
					flag= Component.verifyEquals(rsJob.getString("DefaultedAccount"), accDefaulted, "DefaultedAccount validation");
					publishResults(flag, rsJob.getString("DefaultedAccount"), accDefaulted, "DefaultedAccount validation");
					}
			}
			
			br.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	
	
	
	public static void runWebLoader_GetSwitchedItemDetailsQuery(String apiType, String serviceName) throws Exception
	{	TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
			}
	};
	try
	{
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		String input = "{\"iPSLHeader\": {\"BankID\": \"123123\",\"BusinessDate\": \"20160808\",\"FinancialInstitutionID\": \"1\",\"MessageID\": \"060505121212\",\"ProcessID\": \"WebU\",\"ProductID\": \"WebU\",\"RequestingUserID\": \"1\",\"SourceLocation\": \"1\",\"SourceSubLocation\": \"1\",}," + "\"iPSLRequestBody\":{\"UniqueItemIdentifier\": \"" +(ICSPropertiesConfig.InputUII_api())+"\"}}";
		URL url = new URL("https://gbibc-dt03-03-v.devipsl.eu.uis.unisys.com/" + apiType + "/" + serviceName);
		HttpURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", "Fiddler");
		//con.setRequestProperty("Accept", "*");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		//con.setRequestProperty("Authorization", "Basic " + new Base64Encoder().encode(("Svc-FWDalUser1:r3vi3W123*").getBytes(StandardCharsets.UTF_8)));			
		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(input);
		osw.close();
		int i = 0;
		System.out.println("HTTP Status Code : "+con.getResponseCode());
		System.out.println("Status Message : "+con.getResponseMessage());
		while((con.getResponseCode() != HttpURLConnection.HTTP_OK) && i<=10)
		{
			System.out.println(con.getResponseCode());
			Thread.sleep(2000);
			i++;
			Thread.sleep(5000);
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
			{
			Assert.assertEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK);
			}
			
		//	throw new Exception("Failed with error code: " + con.getResponseCode());
		}
		/*if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
		{*/
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer op = new StringBuffer();
			while((output = br.readLine()) != null)
			{
				System.out.println("Response Output : "+output);
				String uii=output.substring(output.indexOf("UniqueItemIdentifier")+22).substring(1,26).trim();
				System.out.println("UII : "+uii);
				String accDefaulted=output.substring(output.indexOf("AccountNumberDefaulted")+23).substring(1,2).trim();
				System.out.println("AccountNumber Defaulted : "+accDefaulted);
				String serialDefaulted=output.substring(output.indexOf("SerialReferenceDefaulted")+25).substring(1,2).trim();
				System.out.println("SerialReference Defaulted : "+serialDefaulted);
				String sortDefaulted=output.substring(output.indexOf("SortCodeDefaulted")+18).substring(1,2).trim();
				System.out.println("SortCode Defaulted : "+sortDefaulted);
				query ="select * from base.FinalDebit"
						+ " where DebitId='"+ICSPropertiesConfig.InputUII_api()+"'";	
				System.out.println(query );
				ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnectionCluster(server, db, query);
			//	int count =0;
				while(rsJob.next())
				{
					flag= Component.verifyEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK, "HTTP Status code validation");
					publishResults(flag, Integer.toString(con.getResponseCode()), Integer.toString(HttpURLConnection.HTTP_OK), "HTTP Status code validation");
					flag= Component.verifyEquals(rsJob.getString("DebitId"), uii, "UII validation");
					publishResults(flag, rsJob.getString("DebitId"), uii, "UII value validation");
					flag= Component.verifyEquals(rsJob.getString("DefaultedSortcode"), sortDefaulted, "DefaultedSortcode validation");
					publishResults(flag, rsJob.getString("DefaultedSortcode"), sortDefaulted, "DefaultedSortcode validation");
					flag= Component.verifyEquals(rsJob.getString("DefaultedSerialNumber"), serialDefaulted, "DefaultedSerialNumber validation");
					publishResults(flag, rsJob.getString("DefaultedSerialNumber"), serialDefaulted, "DefaultedSerialNumber validation");
					flag= Component.verifyEquals(rsJob.getString("DefaultedAccount"), accDefaulted, "DefaultedAccount validation");
					publishResults(flag, rsJob.getString("DefaultedAccount"), accDefaulted, "DefaultedAccount validation");
					}
				
			}
			
			br.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	
	
	public static void runWebLoader_CreditFraudItemDetailsQuery(String apiType, String serviceName) throws Exception
	{	TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
			}
	};
	try
	{
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		String input = "{\"iPSLHeader\": {\"BankID\": \"123123\",\"BusinessDate\": \"20160808\",\"FinancialInstitutionID\": \"1\",\"MessageID\": \"060505121212\",\"ProcessID\": \"WebU\",\"ProductID\": \"WebU\",\"RequestingUserID\": \"1\",\"SourceLocation\": \"1\",\"SourceSubLocation\": \"1\",}," + "\"iPSLRequestBody\":{\"UniqueItemIdentifier\": \"" +(ICSPropertiesConfig.InputUII_api())+"\"}}";
		URL url = new URL("https://gbibc-dt03-03-v.devipsl.eu.uis.unisys.com/" + apiType + "/" + serviceName);
		HttpURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", "Fiddler");
		//con.setRequestProperty("Accept", "*");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		//con.setRequestProperty("Authorization", "Basic " + new Base64Encoder().encode(("Svc-FWDalUser1:r3vi3W123*").getBytes(StandardCharsets.UTF_8)));			
		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(input);
		osw.close();
		int i = 0;
		System.out.println("HTTP Status Code : "+con.getResponseCode());
		System.out.println("Status Message : "+con.getResponseMessage());
		while((con.getResponseCode() != HttpURLConnection.HTTP_OK) && i<=10)
		{
			System.out.println(con.getResponseCode());
			Thread.sleep(2000);
			i++;
			Thread.sleep(5000);
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
			{
			Assert.assertEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK);
			}
			
		//	throw new Exception("Failed with error code: " + con.getResponseCode());
		}
		/*if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
		{*/
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer op = new StringBuffer();
			while((output = br.readLine()) != null)
			{
				System.out.println("Response Output : "+output);
				String uii=output.substring(output.indexOf("UniqueItemIdentifier")+22).substring(1,26).trim();
				System.out.println("UII : "+uii);
				String accDefaulted=output.substring(output.indexOf("AccountNumberDefaulted")+23).substring(1,2).trim();
				System.out.println("AccountNumber Defaulted : "+accDefaulted);
				String serialDefaulted=output.substring(output.indexOf("SerialReferenceDefaulted")+25).substring(1,2).trim();
				System.out.println("SerialReference Defaulted : "+serialDefaulted);
				String sortDefaulted=output.substring(output.indexOf("SortCodeDefaulted")+18).substring(1,2).trim();
				System.out.println("SortCode Defaulted : "+sortDefaulted);
				query ="select * from base.FinalDebit"
						+ " where DebitId='"+ICSPropertiesConfig.InputUII_api()+"'";	
				System.out.println(query );
				ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnectionCluster(server, db, query);
			//	int count =0;
				while(rsJob.next())
				{
					flag= Component.verifyEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK, "HTTP Status code validation");
					publishResults(flag, Integer.toString(con.getResponseCode()), Integer.toString(HttpURLConnection.HTTP_OK), "HTTP Status code validation");
					flag= Component.verifyEquals(rsJob.getString("DebitId"), uii, "UII validation");
					publishResults(flag, rsJob.getString("DebitId"), uii, "UII value validation");
					flag= Component.verifyEquals(rsJob.getString("DefaultedSortcode"), sortDefaulted, "DefaultedSortcode validation");
					publishResults(flag, rsJob.getString("DefaultedSortcode"), sortDefaulted, "DefaultedSortcode validation");
					flag= Component.verifyEquals(rsJob.getString("DefaultedSerialNumber"), serialDefaulted, "DefaultedSerialNumber validation");
					publishResults(flag, rsJob.getString("DefaultedSerialNumber"), serialDefaulted, "DefaultedSerialNumber validation");
					flag= Component.verifyEquals(rsJob.getString("DefaultedAccount"), accDefaulted, "DefaultedAccount validation");
					publishResults(flag, rsJob.getString("DefaultedAccount"), accDefaulted, "DefaultedAccount validation");
					}
				
			}
			
			br.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	
	
	public static void runWebLoader_DebitFraudItemDetailsQuery(String apiType, String serviceName) throws Exception
	{	TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
			}
	};
	try
	{
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		String input = "{\"iPSLHeader\": {\"BankID\": \"123123\",\"BusinessDate\": \"20160808\",\"FinancialInstitutionID\": \"1\",\"MessageID\": \"060505121212\",\"ProcessID\": \"WebU\",\"ProductID\": \"WebU\",\"RequestingUserID\": \"1\",\"SourceLocation\": \"1\",\"SourceSubLocation\": \"1\",}," + "\"iPSLRequestBody\":{\"UniqueItemIdentifier\": \"" +(ICSPropertiesConfig.InputUII_api())+"\"}}";
		URL url = new URL("https://gbibc-dt03-03-v.devipsl.eu.uis.unisys.com/" + apiType + "/" + serviceName);
		HttpURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", "Fiddler");
		//con.setRequestProperty("Accept", "*");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		//con.setRequestProperty("Authorization", "Basic " + new Base64Encoder().encode(("Svc-FWDalUser1:r3vi3W123*").getBytes(StandardCharsets.UTF_8)));			
		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(input);
		osw.close();
		int i = 0;
		System.out.println("HTTP Status Code : "+con.getResponseCode());
		System.out.println("Status Message : "+con.getResponseMessage());
		while((con.getResponseCode() != HttpURLConnection.HTTP_OK) && i<=10)
		{
			System.out.println(con.getResponseCode());
			Thread.sleep(2000);
			i++;
			Thread.sleep(5000);
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
			{
			Assert.assertEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK);
			}
			
		//	throw new Exception("Failed with error code: " + con.getResponseCode());
		}
		/*if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
		{*/
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer op = new StringBuffer();
			while((output = br.readLine()) != null)
			{
				System.out.println("Response Output : "+output);
				String uii=output.substring(output.indexOf("UniqueItemIdentifier")+22).substring(1,26).trim();
				System.out.println("UII : "+uii);
				String dateoffirstcheque=output.substring(output.indexOf("DateOfFirstCheque")+19).substring(1,9).trim();
				System.out.println("Date Of First Cheque : "+dateoffirstcheque);
				String dateoflastcheque=output.substring(output.indexOf("DateOfLastCheque")+18).substring(1,9).trim();
				System.out.println("Date Of Last Cheque : "+dateoflastcheque);
				String largestamt=output.substring(output.indexOf("LargestAmount")+15).substring(1,8).trim();
				System.out.println("LargestAmount : "+largestamt);
				String noofcounterparties=output.substring(output.indexOf("NumberOfCounterParties")+23).substring(1,2).trim();
				System.out.println("NumberOfCounterParties : "+noofcounterparties);
				String nooffraudcheques=output.substring(output.indexOf("NumberOfFraudCheques")+21).substring(1,2).trim();
				System.out.println("NumberOfFraudCheques : "+nooffraudcheques);
				String noofgoodcheques=output.substring(output.indexOf("NumberOfGoodCheques")+20).substring(1,2).trim();
				System.out.println("NumberOfGoodCheques : "+noofgoodcheques);
				String riskind=output.substring(output.indexOf("RiskIndicator")+14).substring(1,5).trim();
				System.out.println("RiskIndicator : "+riskind);
				String suspitem=output.substring(output.indexOf("SuspiciousItemIndicator")+24).substring(1,2).trim();
				System.out.println("SuspiciousItemIndicator : "+suspitem);
				query ="SELECT F.*,D.ReasonCode,NULL AS Fraudstatuscode FROM base.FinalDebit D INNER JOIN Base.DebitFraudData F ON D.ItemId = F.ItemId"
						+ " where DebitId='"+ICSPropertiesConfig.InputUII_api()+"'";	
				System.out.println(query );
				ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnectionCluster(server, db, query);
			//	int count =0;
				while(rsJob.next())
				{
					flag= Component.verifyEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK, "HTTP Status code validation");
					publishResults(flag, Integer.toString(con.getResponseCode()), Integer.toString(HttpURLConnection.HTTP_OK), "HTTP Status code validation");
					flag= Component.verifyEquals(ICSPropertiesConfig.InputUII_api(), uii, "UII validation");
					publishResults(flag, ICSPropertiesConfig.InputUII_api(), uii, "UII value validation");
					flag= Component.verifyEquals(rsJob.getString("DateOfFirstCheque").replaceAll("[.-]", ""), dateoffirstcheque, "DateOfFirstCheque");
					publishResults(flag, rsJob.getString("DateOfFirstCheque").replaceAll("[.-]", ""), dateoffirstcheque, "DateOfFirstCheque value validation");
					flag= Component.verifyEquals(rsJob.getString("DateOfLastCheque").replaceAll("[.-]", ""), dateoflastcheque, "DateOfLastCheque validation");
					publishResults(flag, rsJob.getString("DateOfLastCheque").replaceAll("[.-]", ""), dateoflastcheque, "DateOfLastCheque value validation");
					flag= Component.verifyEquals(rsJob.getString("LargestAmount").replaceAll("[.£]", ""), largestamt, "LargestAmount validation");
					publishResults(flag, rsJob.getString("LargestAmount").replaceAll("[.£]", ""), largestamt, "LargestAmount value validation");
					flag= Component.verifyEquals(rsJob.getString("NumberOfCounterParties"), noofcounterparties, "NumberOfCounterParties validation");
					publishResults(flag, rsJob.getString("NumberOfCounterParties"), noofcounterparties, "NumberOfCounterParties value validation");
					flag= Component.verifyEquals(rsJob.getString("NumberOfFraudCheques"), nooffraudcheques, "NumberOfFraudCheques validation");
					publishResults(flag, rsJob.getString("NumberOfFraudCheques"), nooffraudcheques, "NumberOfFraudCheques value validation");
					flag= Component.verifyEquals(rsJob.getString("NumberOfGoodCheques"), noofgoodcheques, "NumberOfGoodCheques validation");
					publishResults(flag, rsJob.getString("NumberOfGoodCheques"), noofgoodcheques, "NumberOfGoodCheques value validation");
					flag= Component.verifyEquals(rsJob.getString("RiskIndicator"), riskind, "NumberORiskIndicatorfGoodCheques validation");
					publishResults(flag, rsJob.getString("RiskIndicator"), riskind, "RiskIndicator value validation");
					flag= Component.verifyEquals(rsJob.getString("SuspiciousCheque"), suspitem, "SuspiciousCheque validation");
					publishResults(flag, rsJob.getString("SuspiciousCheque"), suspitem, "SuspiciousCheque value validation");
				}
				
			}
			
			br.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	
	
	
	public static void runWebLoader_ItemCaptureHistoryQuery(String apiType, String serviceName) throws Exception
	{	TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
			}
	};
	try
	{
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		String input = "{\"iPSLHeader\": {\"BankID\": \"123123\",\"BusinessDate\": \"20160808\",\"FinancialInstitutionID\": \"1\",\"MessageID\": \"060505121212\",\"ProcessID\": \"WebU\",\"ProductID\": \"WebU\",\"RequestingUserID\": \"1\",\"SourceLocation\": \"1\",\"SourceSubLocation\": \"1\",}," + "\"iPSLRequestBody\":{\"UniqueItemIdentifier\": \"" +(ICSPropertiesConfig.InputUII_api())+"\"}}";
		URL url = new URL("https://gbibc-dt03-03-v.devipsl.eu.uis.unisys.com/" + apiType + "/" + serviceName);
		HttpURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", "Fiddler");
		//con.setRequestProperty("Accept", "*");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		//con.setRequestProperty("Authorization", "Basic " + new Base64Encoder().encode(("Svc-FWDalUser1:r3vi3W123*").getBytes(StandardCharsets.UTF_8)));			
		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(input);
		osw.close();
		int i = 0;
		System.out.println("HTTP Status Code : "+con.getResponseCode());
		System.out.println("Status Message : "+con.getResponseMessage());
		while((con.getResponseCode() != HttpURLConnection.HTTP_OK) && i<=10)
		{
			System.out.println(con.getResponseCode());
			Thread.sleep(2000);
			i++;
			Thread.sleep(5000);
			if(con.getResponseCode() != HttpURLConnection.HTTP_OK)
			{
			Assert.assertEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK);
			}
			
		//	throw new Exception("Failed with error code: " + con.getResponseCode());
		}
		/*if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
		{*/
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer op = new StringBuffer();
			while((output = br.readLine()) != null)
			{
				System.out.println("Response Output : "+output);
				String uii=output.substring(output.indexOf("UniqueItemIdentifier")+22).substring(1,26).trim();
				System.out.println("UII : "+uii);
				String acct=output.substring(output.indexOf("Account")+19).substring(1,9).trim();
				System.out.println("Acccount : "+acct);
				String amount=output.substring(output.indexOf("Amount")+18).substring(1,9).trim();
				System.out.println("Amount : "+amount);
				String auditRev=output.substring(output.indexOf("AuditRevision")+15).substring(1,8).trim();
				System.out.println("AuditRevision: "+auditRev);
				String serial=output.substring(output.indexOf("Serial")+23).substring(1,2).trim();
				System.out.println("Serial : "+serial);
				String sortcode=output.substring(output.indexOf("SortCode")+21).substring(1,2).trim();
				System.out.println("SortCode : "+sortcode);
				String trancode=output.substring(output.indexOf("TranCode")+20).substring(1,2).trim();
				System.out.println("TranCode : "+trancode);
				query ="SELECT F.*,D.ReasonCode,NULL AS Fraudstatuscode FROM base.FinalDebit D INNER JOIN Base.DebitFraudData F ON D.ItemId = F.ItemId"
						+ " where DebitId='"+ICSPropertiesConfig.InputUII_api()+"'";	
				System.out.println(query );
				ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnectionCluster(server, db, query);
			//	int count =0;
				while(rsJob.next())
				{
					flag= Component.verifyEquals(con.getResponseCode(), HttpURLConnection.HTTP_OK, "HTTP Status code validation");
					publishResults(flag, Integer.toString(con.getResponseCode()), Integer.toString(HttpURLConnection.HTTP_OK), "HTTP Status code validation");
					flag= Component.verifyEquals(ICSPropertiesConfig.InputUII_api(), uii, "UII validation");
					publishResults(flag, ICSPropertiesConfig.InputUII_api(), uii, "UII value validation");
					flag= Component.verifyEquals(rsJob.getString("SortCode").replaceAll("[.-]", ""), sortcode, "sortcode");
					publishResults(flag, rsJob.getString("SortCode").replaceAll("[.-]", ""), sortcode, "sortcode value validation");
					flag= Component.verifyEquals(rsJob.getString("Serial").replaceAll("[.-]", ""), serial, "Serial validation");
					publishResults(flag, rsJob.getString("Serial").replaceAll("[.-]", ""), serial, "Serial value validation");
					flag= Component.verifyEquals(rsJob.getString("TranCode").replaceAll("[.£]", ""), trancode, "TranCode validation");
					publishResults(flag, rsJob.getString("TranCode").replaceAll("[.£]", ""), trancode, "TranCode value validation");
					flag= Component.verifyEquals(rsJob.getString("AuditRevision"), auditRev, "AuditRevision validation");
					publishResults(flag, rsJob.getString("AuditRevision"), auditRev, "AuditRevision value validation");
				}
				
			}
			
			br.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
	
	public static void runWebLoader_GetImagesQuery(String apiType, String serviceName) throws Exception
	{	TrustManager[] trustAllCerts = new TrustManager[]{
			new X509TrustManager() {
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
				public void checkClientTrusted(X509Certificate[] certs, String authType) throws CertificateException {
				}
			}
	};
	try
	{
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, new java.security.SecureRandom());
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		String input = "{\"iPSLHeader\": {\"BankID\": \"123123\",\"BusinessDate\": \"20160808\",\"FinancialInstitutionID\": \"1\",\"MessageID\": \"060505121212\",\"ProcessID\": \"WebU\",\"ProductID\": \"WebU\",\"RequestingUserID\": \"1\",\"SourceLocation\": \"1\",\"SourceSubLocation\": \"1\",}," + "\"iPSLRequestBody\":{\"UniqueItemIdentifier\": \"" +(ICSPropertiesConfig.InputUII_api())+"\"}}";
		URL url = new URL("https://gbibc-dt03-03-v.devipsl.eu.uis.unisys.com/" + apiType + "/" + serviceName);
		HttpURLConnection con = (HttpsURLConnection) url.openConnection();
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setRequestMethod("POST");
		//con.setRequestProperty("User-Agent", "Fiddler");
		//con.setRequestProperty("Accept", "*");
		con.setRequestProperty("Accept", "application/json");
		con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
		//con.setRequestProperty("Authorization", "Basic " + new Base64Encoder().encode(("Svc-FWDalUser1:r3vi3W123*").getBytes(StandardCharsets.UTF_8)));			
		OutputStreamWriter osw = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
		osw.write(input);
		osw.close();
		int i = 0;
		System.out.println("HTTP Status Code : "+con.getResponseCode());
		System.out.println("Status Message : "+con.getResponseMessage());
		while((con.getResponseCode() != HttpURLConnection.HTTP_OK) && i<=10)
		{
			System.out.println(con.getResponseCode());
			Thread.sleep(2000);
			i++;
		//	throw new Exception("Failed with error code: " + con.getResponseCode());
		}
		/*if(con.getResponseCode() == HttpURLConnection.HTTP_OK)
		{*/
			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String output;
			StringBuffer op = new StringBuffer();
			while((output = br.readLine()) != null)
			{
				System.out.println("Response Output : "+output);
				String bdate=output.substring(output.indexOf("BankID")+8).substring(1,7).trim();
				System.out.println("Bank ID : "+bdate);
				String mid=output.substring(output.indexOf("MessageID")+11).substring(1,13).trim();
				System.out.println("Message ID : "+mid);
				String uii=output.substring(output.indexOf("UniqueItemIdentifier")+22).substring(1,26).trim();
				System.out.println("UII : "+uii);
				String imageType=output.substring(output.indexOf("ImageType")+11).substring(1,6).trim();
				System.out.println("Image Type : "+imageType);
				String image=output.substring(output.indexOf("Images")+8).substring(1,6740).trim();
				System.out.println("Image : "+image);
				query ="select * from base.FinalDebit d join base.Image i on d.ItemId=i.ItemId"
						+ " where DebitId='"+ICSPropertiesConfig.InputUII_api()+"'";	
				System.out.println(query );
				ResultSet rsJob = ICSProductDBConnection.getICSDBServerConnectionCluster(server, db, query);
				//int count =0;
				while(rsJob.next())
				{
					flag= Component.verifyEquals(rsJob.getString("DebitId"), uii, "UII validation");
					publishResults(flag, rsJob.getString("DebitId"), uii, "UII value validation");
					flag= Component.verifyEquals(rsJob.getString("Image"), image, "Image validation");
					publishResults(flag, rsJob.getString("Image"), image, "Image validation");
				}
			}
			
			br.close();
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
}
}
