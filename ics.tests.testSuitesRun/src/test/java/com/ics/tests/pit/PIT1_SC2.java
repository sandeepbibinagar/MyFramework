package com.ics.tests.pit;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;
import com.ics.tests.rNe.Sprint2_1;

/**
 * @author MuluguUm
 * Sample case where we should add/create PIT1_SC2 cases
 *
 */
public class PIT1_SC2 {
	
	@Test(priority=1)
	public void mm() throws SAXException, IOException, ParserConfigurationException
	{
		new Sprint2_1().test_case_58821();
	}
	
	
}