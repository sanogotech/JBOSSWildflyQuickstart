package org.jboss.as.quickstart.cdi.configurationmethod;

import javax.enterprise.util.AnnotationLiteral;

public class FlowDefinitionParameterQualifierLiteral extends AnnotationLiteral<FlowDefinitionParameterQualifier> implements FlowDefinitionParameterQualifier {

	public static final FlowDefinitionParameterQualifier INSTANCE = new FlowDefinitionParameterQualifierLiteral();
	
	private FlowDefinitionParameterQualifierLiteral () {}
	
}
