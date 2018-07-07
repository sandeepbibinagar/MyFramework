package com.ics.tests.mo;

import org.apache.log4j.Logger;

public class TestCollectorFlow extends CollectorTestCases {
	private static final Logger testCollectorFlow = Logger.getLogger(TestPayerFlow.class.getSimpleName());
	
	TestCollectorFlow() {
		super(getRequiredXmlTestCaseName(new Object(){}) , testCollectorFlow);
	}
}
