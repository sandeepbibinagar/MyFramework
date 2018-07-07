package com.ics.mo.xmlFiles.icnContent;

import java.io.File;
import org.apache.commons.io.FileUtils;
import com.ics.mo.common.MoGenericMethods;

public class Msg01KM01 extends MoGenericMethods 
{
	public static void check01KM01ICNdataInsertInSource(String inputFilePath, String inputFileName, String outputFilePath, String workFlow) throws Exception
	{
		String expected01KM01Data = FileUtils.readFileToString(new File(inputFilePath + inputFileName));  
		int msf06TsetListOcccurence = getStringOccurence(expected01KM01Data, "<MSF06TsetFrdChkRspList>");
		String tempExpected01KM01Data = expected01KM01Data;
		while(msf06TsetListOcccurence>0)
		{
			msf06TsetListOcccurence--;
		}
	}
}
