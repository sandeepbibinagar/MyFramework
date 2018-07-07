package com.ics.tests.mo;

import org.apache.log4j.Logger;

public class TestPayerFlow extends PayerCoreTestCases {
	private static final Logger testRequestToPay = Logger.getLogger(TestPayerFlow.class.getSimpleName());

	TestPayerFlow() {
		super(getRequiredXmlTestCaseName(new Object(){}) , testRequestToPay);
	}
}
