package org.jboss.as.quickstarts.deltaspike.authz;
/**
 * JBoss, Home of Professional Open Source
 * Copyright 2012, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
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



import static org.jboss.as.quickstarts.deltaspike.authz.Role.ADMIN;
import static org.jboss.as.quickstarts.deltaspike.authz.Role.USER;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 * A simple REST service which is able to say hello to either an Admin or a User
 *
 * @author bsutter@redhat.com
 * @author Pete Muir
 */

@Path("/hello")
@RequestScoped
public class HelloService {

    @POST
    @Path("/{name}")
    @Produces("application/json")
    @Restricted(USER)
    public String getHelloWorld(@PathParam("name") String name) {
        System.out.println("name: " + name);
        return "{\"result\":\" Hello " + name + "\"}";
    }
    
    @POST
    @Path("/admin/{name}")
    @Produces("application/json")
    @Restricted(ADMIN)
    public String getHelloWorldAdmin(@PathParam("name") String name) {
        System.out.println("name: " + name);
        return "{\"result\":\" Hello " + name + " from admin!\"}";
    }

    

}
