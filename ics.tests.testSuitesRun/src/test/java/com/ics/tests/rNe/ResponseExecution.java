package com.ics.tests.rNe;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.ics.externalFactoryUtilis.ICSDBUtilis;
import com.ics.rNe.testScenarios.CoreNodeRecordValidationScenarios;
import com.ics.rNe.testScenarios.CoreNodeValidationScenarios;
import com.ics.rNe.testScenarios.CreditRecordValidationScenarios;
import com.ics.rNe.testScenarios.DEBValidationScenarios;
import com.ics.rNe.testScenarios.DebitDetailValidationScenarios;
import com.ics.rNe.testScenarios.PostingExtractFileValidationScenarios;
import com.ics.rNe.testScenarios.PostingHeaderValidationsScenarios;
import com.ics.rNe.testScenarios.PostingRecordValidationScenarios;
import com.ics.rNe.testScenarios.PostingResponseHeaderValidationScenarios;
import com.ics.rNe.testScenarios.RecordBodyValidationScenarios;
import com.ics.rNe.testScenarios.ValidationScenarios70656;
import com.ics.rNe.xmlFilesDataFetch.CoreNodeRecordFile;
import com.ics.rNe.xmlFilesDataFetch.CreditRecordFile;
import com.ics.rNe.xmlFilesDataFetch.DEBFile;
import com.ics.rNe.xmlFilesDataFetch.DebitDetailFile;
import com.ics.rNe.xmlFilesDataFetch.ExtractXML;
import com.ics.rNe.xmlFilesDataFetch.PERM01File;
import com.ics.rNe.xmlFilesDataFetch.PostingExtractFile;
import com.ics.rNe.xmlFilesDataFetch.PostingExtractFile70656;
import com.ics.rNe.xmlFilesDataFetch.PostingHeaderFile;
import com.ics.rNe.xmlFilesDataFetch.PostingRecordFile;
import com.ics.rNe.xmlFilesDataFetch.RecordBodyFile;
import com.ics.rNe.xmlFilesDataFetch.ResponseFile1CR1DR;
import com.ics.rNe.xmlFilesDataFetch.XSDFile;
import com.ics.seleniumCoreUtilis.Component;
import com.ics.testNgSeleniumSetup.ICSAutomationCommonSetup;

public class ResponseExecution extends ICSAutomationCommonSetup{
	
	@Test(priority = 1)
	public void ResponseValidation1DR1CR() throws SAXException, IOException, ParserConfigurationException{
	
		try{
		//ResponseFile1CR1DR.getPRRM01XML();
		//ResponseFile1CR1DR.readData();
		//	ResponseFile1CR1DR.readDataFromPRRM01();
			ResponseFile1CR1DR.readData();
			//ResponseFile1CR1DR.fetchDataFromRNEPostingResponse("000004173651000DEBIT00001");
		}
		catch(Exception e){
			
		}
		
	
	

}
}
