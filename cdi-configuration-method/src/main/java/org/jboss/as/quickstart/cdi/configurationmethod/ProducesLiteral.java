package org.jboss.as.quickstart.cdi.configurationmethod;

import javax.enterprise.inject.Produces;
import javax.enterprise.util.AnnotationLiteral;

public class ProducesLiteral extends AnnotationLiteral<Produces> implements Produces {
	
	public static final Produces INSTANCE = new ProducesLiteral();
	
	private ProducesLiteral() {}

}
