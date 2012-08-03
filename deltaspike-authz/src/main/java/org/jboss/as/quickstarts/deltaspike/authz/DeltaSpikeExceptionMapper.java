package org.jboss.as.quickstarts.deltaspike.authz;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.deltaspike.core.api.exception.control.event.ExceptionToCatchEvent;

@Provider
public class DeltaSpikeExceptionMapper implements ExceptionMapper<Throwable> {
    
    @Inject
    private Event<ExceptionToCatchEvent> event;

    @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse();
        } else {
            ExceptionToCatchEvent evt = new ExceptionToCatchEvent(exception);
            try {
                event.fire(evt);
            } catch (Throwable t) {
                return Response.status(Response.Status.BAD_REQUEST).entity(t).build();
            }
            return Response.ok().build();
        }
    }

}