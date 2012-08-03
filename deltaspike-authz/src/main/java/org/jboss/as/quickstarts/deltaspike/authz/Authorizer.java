package org.jboss.as.quickstarts.deltaspike.authz;

import javax.interceptor.InvocationContext;

import org.apache.deltaspike.security.api.authorization.annotation.Secures;

public class Authorizer {
    
    @Secures
    @Restricted
    public boolean authorize(InvocationContext ctx, @LoggedIn String user) {

        // Hardcode a user to be the admin
        Role userRole = user == "admin" ? Role.ADMIN : Role.USER;
        
        // Get the required role
        Role requiredRole = getRequiredRole(ctx);
        
        return userRole.equals(requiredRole);
    }
    
    private Role getRequiredRole(InvocationContext ctx) {
        // Annotation could be on the method
        if (ctx.getMethod().isAnnotationPresent(Restricted.class))
            return ctx.getMethod().getAnnotation(Restricted.class).value();
        // Annotation could be on the class
        else if (ctx.getMethod().getDeclaringClass().isAnnotationPresent(Restricted.class))
            return ctx.getMethod().getDeclaringClass().getAnnotation(Restricted.class).value();
        // Deal with the case that we can't find the @Restricted annotation (shouldn't happen!)
        else
            throw new IllegalStateException("Unable to locate @Restricted"); 
    }

}
