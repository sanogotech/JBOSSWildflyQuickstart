package org.jboss.as.quickstarts.deltaspike.authz;

import org.apache.deltaspike.core.api.exception.control.annotation.BeforeHandles;
import org.apache.deltaspike.core.api.exception.control.annotation.ExceptionHandler;
import org.apache.deltaspike.core.api.exception.control.event.ExceptionEvent;
import org.apache.deltaspike.security.api.authorization.AccessDeniedException;

@ExceptionHandler
public class ExceptionHandlers {

    public void logUnauthorizedSayHelloFromAdmin(@BeforeHandles ExceptionEvent<AccessDeniedException> evt, @LoggedIn String user) {
        System.out.println("Access denied to restricted resource. user: " + user);
        evt.handled();
    }

}