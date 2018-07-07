package com.ics.agency.entities;

import com.ics.agency.pages.AgencyIdentityProviderPage;
import com.ics.agency.pages.OpenAgencyPortalPage;
import com.ics.seleniumCoreUtilis.Scenario;

public class OpenAgencyPageScenario implements Scenario<AgencyIdentityProviderPage, OpenAgencyPortalPage> {
	
	private final String roleName;
	
	public OpenAgencyPageScenario(String roleName)
	{
		this.roleName = roleName;
	}

	@Override
	public OpenAgencyPortalPage run(AgencyIdentityProviderPage entry) {		
		return entry
				//.ClickOnsecuritylink()		
				.fillRolefield(roleName)
				.clickLoginButton();				
	}	
	
	
	
}
