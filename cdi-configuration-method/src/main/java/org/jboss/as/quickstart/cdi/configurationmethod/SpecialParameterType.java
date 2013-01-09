package org.jboss.as.quickstart.cdi.configurationmethod;

public class SpecialParameterType {

	private final String name;

	public SpecialParameterType() {
		this.name = "default";
	}

	public SpecialParameterType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	};

}
