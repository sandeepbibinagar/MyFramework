package com.ics.tests.mo;

import org.apache.log4j.Logger;

public class TestBeneficiaryFlow extends BeneficiaryTestCases  {
	private static final Logger testBeneficiaryFlow = Logger.getLogger(TestBeneficiaryFlow.class.getSimpleName());

	TestBeneficiaryFlow() {
		super(getRequiredXmlTestCaseName(new Object(){}) , testBeneficiaryFlow);
	}
}
