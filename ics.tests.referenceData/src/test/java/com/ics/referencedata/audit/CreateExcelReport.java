package com.ics.referencedata.audit;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xerces.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class CreateExcelReport
{
	static FileOutputStream fileOut = null;
//	static String inputXMLFile = "C:/Users/MuluguUm/Desktop/ICS_Automation Project/ics_master_folder/integration-tests-ics-automation/integration.tests.ics.testSuiteRun/test-output/testng-results.xml";
	static String inputXMLFile = "D:/st_workspace/workspace/ics-tests-automation/ics.tests.referenceData/src/test/resources/testng-results.xml";
//	static String outputExcelFile = "C:/Users/MuluguUm/Desktop/ICS_Automation Project/ics_master_folder/integration-tests-ics-automation/integration.tests.ics.testSuiteRun/src/main/resources/test_report.xlsx";
	static String outputExcelFile = "D:/st_workspace/workspace/ics-tests-automation/ics.tests.referenceData/src/test/resources/test_report.xlsx";
	static XSSFSheet sheet, sheet1;
	static XSSFWorkbook wb;
	static ArrayList<String> alSprint;

	public static void main(String args[]) throws Exception
	{
		try
		{
			createExcelFileData();
		}
		catch(FileNotFoundException f)
		{
			System.out.println("FileNotFoundException " + f.getMessage());
		}
	}

	public static void createExcelFileData() throws Exception
	{
		try
		{
			wb = new XSSFWorkbook();
			sheet = wb.createSheet("Final Results");

			XSSFRow row = sheet.createRow((short) 0);
			
			row.createCell(0).setCellValue("Product");
			row.createCell(1).setCellValue("Sprint");
			row.createCell(2).setCellValue("Test Case");
			row.createCell(3).setCellValue("Status");
			row.createCell(4).setCellValue("Failed Step Details");
			row.createCell(5).setCellValue("Start Time");
			row.createCell(6).setCellValue("End Time");
			row.createCell(7).setCellValue("Time Elapsed");

			DOMParser parser = new DOMParser();
			parser.parse(inputXMLFile);

			Document doc = parser.getDocument();

			NodeList nodeList = doc.getElementsByTagName("test-method");
			
			for(int i=0; i<nodeList.getLength(); i++)
			{
				row = sheet.createRow((short) i+1);
					
				Node node = nodeList.item(i);

				NamedNodeMap nnm = node.getAttributes();
				
				String signature = nnm.getNamedItem("signature").getNodeValue();
				StringTokenizer st = new StringTokenizer(signature,":.@");
				alSprint = new ArrayList<String>();
				
				while(st.hasMoreTokens())
				{
					alSprint.add(st.nextToken());
				}
				
				row.createCell(0).setCellValue(alSprint.get(5));
				
				row.createCell(1).setCellValue(alSprint.get(6));

				String name = nnm.getNamedItem("name").getNodeValue();
				row.createCell(2).setCellValue(name);

				String status = nnm.getNamedItem("status").getNodeValue();
				row.createCell(3).setCellValue(status);

				String startedTime = nnm.getNamedItem("started-at").getNodeValue();
				row.createCell(5).setCellValue(startedTime);

				String finishTime = nnm.getNamedItem("finished-at").getNodeValue();
				row.createCell(6).setCellValue(finishTime);

				String duration = nnm.getNamedItem("duration-ms").getNodeValue();
				row.createCell(7).setCellValue(duration + " ms");

				if(status.equalsIgnoreCase("fail"))
				{
					NodeList errorNode = node.getChildNodes();
					String nodeName = errorNode.item(1).getNodeName();

					if(nodeName.equalsIgnoreCase("exception"))
					{
						NodeList messageNode = errorNode.item(1).getChildNodes();
						String errorMsg = messageNode.item(1).getTextContent();
						StringTokenizer st1 = new StringTokenizer(errorMsg, "\n.");
						while(st1.hasMoreTokens())
							alSprint.add(st1.nextToken());

						row.createCell(4).setCellValue(alSprint.get(8).trim() + ".");
					}
				}
			}

			fileOut = new FileOutputStream(outputExcelFile);
			wb.write(fileOut);
			fileOut.close();
			System.out.println("Report Created Successfully.");

		}
		catch(Exception e)
		{
			System.out.println("Report Creation Failed.");
			e.printStackTrace();
		}
	}

}
