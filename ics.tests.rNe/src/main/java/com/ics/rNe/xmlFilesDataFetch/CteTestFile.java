package com.ics.rNe.xmlFilesDataFetch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.openqa.jetty.html.Input;
import org.w3c.css.sac.InputSource;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.javascript.host.dom.Document;
import com.gargoylesoftware.htmlunit.javascript.host.file.FileReader;
import com.gargoylesoftware.htmlunit.util.StringUtils;
import com.ics.externalFactoryUtilis.ICSProductDBConnection;
import com.ics.externalFactoryUtilis.ICSPropertiesConfig;
import com.ics.seleniumCoreUtilis.GenericMethodUtilis;
import com.ics.seleniumCoreUtilis.ICSPageUtilis;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;

import com.ics.seleniumCoreUtilis.GenericMethodUtilis;

public class CteTestFile extends GenericMethodUtilis  {
	
	//File Path and Name for different .sql and XML file types
		public static String filePath = GenericMethodUtilis.getrNeResourceFile().getString("filePath");
		public static String fileName = GenericMethodUtilis.getrNeResourceFile().getString("fileName");

		public static String filePTMR01Path = GenericMethodUtilis.getrNeResourceFile().getString("filePTMR01Path");
		public static String filePTMR01Name = GenericMethodUtilis.getrNeResourceFile().getString("filePTMR01Name");

		private static String filePath1 = GenericMethodUtilis.getrNeResourceFile().getString("filePath1");
		private static String fileName1=  GenericMethodUtilis.getrNeResourceFile().getString("fileName1");

		private static String filePath2 = GenericMethodUtilis.getrNeResourceFile().getString("filePath2");
		private static String fileName2=  GenericMethodUtilis.getrNeResourceFile().getString("fileName2");

		private static String filePath3 = GenericMethodUtilis.getrNeResourceFile().getString("filePath3");
		private static String fileName3=  GenericMethodUtilis.getrNeResourceFile().getString("fileName3");

		private static String filePath4 = GenericMethodUtilis.getrNeResourceFile().getString("filePath4");
		private static String fileName4=  GenericMethodUtilis.getrNeResourceFile().getString("fileName4");

		private static String filePath5 = GenericMethodUtilis.getrNeResourceFile().getString("filePath5");
		private static String fileName5=  GenericMethodUtilis.getrNeResourceFile().getString("fileName5");

		private static String filePath6 = GenericMethodUtilis.getrNeResourceFile().getString("filePath6");
		private static String fileName6=  GenericMethodUtilis.getrNeResourceFile().getString("fileName6");

		private static String filePath7 = GenericMethodUtilis.getrNeResourceFile().getString("filePath7");
		private static String fileName7=  GenericMethodUtilis.getrNeResourceFile().getString("fileName7");
		
		private static String filePath8 = GenericMethodUtilis.getrNeResourceFile().getString("filePath8");
		private static String fileName8=  GenericMethodUtilis.getrNeResourceFile().getString("fileName8");
		
		private static String filePath9 = GenericMethodUtilis.getrNeResourceFile().getString("filePath9");
		private static String fileName9=  GenericMethodUtilis.getrNeResourceFile().getString("fileName9");
		
		private static String filePath10 = GenericMethodUtilis.getrNeResourceFile().getString("filePath10");
		private static String fileName10=  GenericMethodUtilis.getrNeResourceFile().getString("fileName10");
		
		private static String filePath11 = GenericMethodUtilis.getrNeResourceFile().getString("filePath11");
		private static String fileName11=  GenericMethodUtilis.getrNeResourceFile().getString("fileName11");
		
		private static String filePath12 = GenericMethodUtilis.getrNeResourceFile().getString("filePath12");
		private static String fileName12=  GenericMethodUtilis.getrNeResourceFile().getString("fileName12");
		
		private static String filePath13 = GenericMethodUtilis.getrNeResourceFile().getString("filePath13");
		private static String fileName13=  GenericMethodUtilis.getrNeResourceFile().getString("fileName13");

		private static String dbArchiveServerName = GenericMethodUtilis.getrNeResourceFile().getString("dbArchiveServerName");
		private static String rNeArchiveDatabaseName = GenericMethodUtilis.getrNeResourceFile().getString("rNeArchiveDatabaseName");

		public static String firstMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath1 + fileName1 + "_Actual.sql ";

		public static String secondMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath2 + fileName2 + "_Actual.sql ";

		public static String thirdMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath3 + fileName3 + "_Actual.sql ";

		public static String fourthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath4 + fileName4 + "_Actual.sql ";

		public static String fifthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath5 + fileName5 + "_Actual.sql ";

		public static String sixthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath6 + fileName6 + "_Actual.sql ";

		public static String seventhMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath7 + fileName7 + "_Actual.sql ";
		
		public static String eighthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath8 + fileName8 + "_Actual.sql ";
		
		public static String ninthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath9 + fileName9 + "_Actual.sql ";
		
		public static String tenthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath10 + fileName10 + "_Actual.sql ";
		
		public static String eleventhMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath11 + fileName11 + "_Actual.sql ";
		
		public static String twelvethMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath12 + fileName12 + "_Actual.sql ";
		
		public static String thirteenthMessageExecution = "cmd /c sqlcmd -m 1 -S "+dbArchiveServerName+" -d "+rNeArchiveDatabaseName+" -i "+
				filePath13 + fileName13 + "_Actual.sql ";


}
