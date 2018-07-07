package com.ics.agency.entities;

import com.ics.agency.pages.BankLandingPage;
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.seleniumCoreUtilis.Scenario;

public class AgencyBankingLandingPageScenario implements Scenario<OpenAgencyPortalPage, BankLandingPage> {
	@Override
	public BankLandingPage run(OpenAgencyPortalPage entry) {		
		return entry
				.clickOpenAgencyPortalButton()
				.switchToAgencyFrame()
				.clickOnIncomingCredits();
	}
}
