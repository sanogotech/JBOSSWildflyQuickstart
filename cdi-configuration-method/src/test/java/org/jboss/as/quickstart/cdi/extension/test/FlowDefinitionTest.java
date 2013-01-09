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

package org.jboss.as.quickstart.cdi.extension.test;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.spi.Extension;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.as.quickstart.cdi.configurationmethod.FlowConfigurationManager;
import org.jboss.as.quickstart.cdi.configurationmethod.FlowDefinition;
import org.jboss.as.quickstart.cdi.configurationmethod.FlowDefintionExtension;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.DependencyResolvers;
import org.jboss.shrinkwrap.resolver.api.maven.MavenDependencyResolver;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Verification test.
 */
@RunWith(Arquillian.class)
public class FlowDefinitionTest {
    @Deployment
    public static Archive<?> getDeployment() {
        
        MavenDependencyResolver resolver = DependencyResolvers.use(MavenDependencyResolver.class).loadMetadataFromPom("pom.xml");
        
        Archive<?> archive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackages(true, FlowDefintionExtension.class.getPackage())
                .addClass(MyFlowDefinition.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsServiceProvider(Extension.class, FlowDefintionExtension.class)
                .addAsLibraries(resolver.artifacts(
                        "org.apache.deltaspike.core:deltaspike-core-api", 
                        "org.apache.deltaspike.core:deltaspike-core-impl").resolveAsFiles());
        return archive;
    }

    @Inject
    private FlowConfigurationManager manager;

    @Test
    public void assertFlowsRegistered() {
        List<FlowDefinition> flows = manager.getFlowDefinitions();
        List<String> flowNames = new ArrayList<String>();
        for (FlowDefinition def : flows) {
        	flowNames.add(def.getName());
        	if (def.getName().equals("three")) {
        		Assert.assertEquals("special", def.getSpecial().getName());
        	}
        }
        Assert.assertEquals(flowNames.size(), 3);
        Assert.assertTrue(flowNames.contains("one"));
        Assert.assertTrue(flowNames.contains("two"));
        Assert.assertTrue(flowNames.contains("three"));
    }
}
