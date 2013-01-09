/*
* JBoss, Home of Professional Open Source
* Copyright 2012, Red Hat, Inc. and/or its affiliates, and individual
* contributors by the @authors tag. See the copyright.txt in the 
* distribution for a full listing of individual contributors.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
* http://www.apache.org/licenses/LICENSE-2.0
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,  
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package org.jboss.as.quickstart.cdi.configurationmethod;

import java.util.HashMap;
import java.util.Map;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedMethod;
import javax.enterprise.inject.spi.AnnotatedParameter;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.ProcessProducer;
import javax.enterprise.inject.spi.Producer;

import org.apache.deltaspike.core.util.metadata.builder.AnnotatedTypeBuilder;

/**
 * A simple CDI Portable Extension to register a producer method if {@link FlowDefiner} is present
 */
public class FlowDefintionExtension implements Extension {

	private Map<String, Producer<FlowDefinition>> flowDefinitions = new HashMap<String, Producer<FlowDefinition>>();
	
	public Map<String, Producer<FlowDefinition>> getFlowDefinitions() {
		return flowDefinitions;
	}
    
	//TODO Add @WithAnnotations when CDI 1.1 is released
    <X> void addProduces(@Observes ProcessAnnotatedType<X> pat) {
        for (AnnotatedMethod<? super X> m : pat.getAnnotatedType().getMethods()) {
           if (FlowDefinition.class.isAssignableFrom(m.getJavaMember().getReturnType()) && m.isAnnotationPresent(FlowDefiner.class)) {
        	   
        	   AnnotatedTypeBuilder<X> builder = new AnnotatedTypeBuilder<X>().readFromType(pat.getAnnotatedType());
        	   builder.addToMethod(m, ProducesLiteral.INSTANCE);
        	   
        	   
        	   for (AnnotatedParameter<? super X> p : m.getParameters()) {
        		   if (p.getTypeClosure().contains(SpecialParameterType.class)) {
        			   builder.addToParameter(p, FlowDefinitionParameterQualifierLiteral.INSTANCE);
        		   }
        	   }
        	   
        	   pat.setAnnotatedType(builder.create());
           }
        }
    }
    
    <T> void findFlowDefiners(@Observes ProcessProducer<T, FlowDefinition> pp) {
    	if (pp.getAnnotatedMember().isAnnotationPresent(FlowDefiner.class)) {
    		String definitionName = pp.getAnnotatedMember().getAnnotation(FlowDefiner.class).value();
    		flowDefinitions.put(definitionName, pp.getProducer());
    	}
    }
}
