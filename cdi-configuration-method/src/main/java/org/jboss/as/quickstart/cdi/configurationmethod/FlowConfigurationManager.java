package org.jboss.as.quickstart.cdi.configurationmethod;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Producer;
import javax.inject.Inject;


public class FlowConfigurationManager {

	@Inject
	private FlowDefintionExtension flowDefintionExtension;
	
	@Inject
	private BeanManager bm;
	
	public List<FlowDefinition> getFlowDefinitions() {
		List<FlowDefinition> defs = new ArrayList<FlowDefinition>();
		for (Producer<FlowDefinition> p : flowDefintionExtension.getFlowDefinitions().values()) {
			FlowDefinition def = p.produce(bm.<FlowDefinition>createCreationalContext(null));
			defs.add(def);
		}
		return defs;
	}
	
}
