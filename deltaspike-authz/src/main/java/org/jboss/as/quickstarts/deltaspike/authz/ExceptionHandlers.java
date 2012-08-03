package org.jboss.as.quickstarts.deltaspike.authz;

import org.apache.deltaspike.core.api.exception.control.annotation.BeforeHandles;
import org.apache.deltaspike.core.api.exception.control.annotation.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;
import org.apache.deltaspike.security.api.authorization.AccessDeniedException;

/**
 * Any exception handlers we want to register.
 * 
 * @author Pete Muir
 *
 */
@ExceptionHandler
public class ExceptionHandlers {

    /**
     * Log any AccessDeniedExceptions thrown by the DeltaSpike authorization system
     * @param evt
     * @param user
     */
    public void logUnauthorizedSayHelloFromAdmin(@BeforeHandles ExceptionEvent<AccessDeniedException> evt, @LoggedIn String user) {
        System.out.println("Access denied to restricted resource. user: " + user);
    }

}