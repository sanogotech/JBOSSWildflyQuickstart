package org.jboss.as.quickstart.cdi.configurationmethod;

import javax.enterprise.inject.Produces;

public class SpecialParameterManager {

	@Produces @FlowDefinitionParameterQualifier 
	public SpecialParameterType createSpecialParameter() {
		return new SpecialParameterType("special");
	}
	
}
