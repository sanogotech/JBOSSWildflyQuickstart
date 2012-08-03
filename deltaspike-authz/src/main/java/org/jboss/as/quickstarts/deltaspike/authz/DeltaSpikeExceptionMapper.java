package org.jboss.as.quickstarts.deltaspike.authz;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.deltaspike.core.api.exception.control.event.ExceptionToCatchEvent;

/**
 * Integration between JAX-RS's {@link ExceptionMapper} and DeltaSpike exception handling.
 * 
 * @author pmuir
 * 
 */
@Provider
public class DeltaSpikeExceptionMapper implements ExceptionMapper<Throwable> {

    @Inject
    private Event<ExceptionToCatchEvent> event;

    /**
     * Handle any exceptions throw by JAX-RS.
     */
    @Override
    public Response toResponse(Throwable exception) {
        // If it's already a JAX-RS exception, then we use it
        // We could make this more complex and allow people to rehandle these exceptions...
        if (exception instanceof WebApplicationException) {
            return ((WebApplicationException) exception).getResponse();
        } else {
            // otherwise, route it to any exception handlers
            ExceptionToCatchEvent evt = new ExceptionToCatchEvent(exception);
            try {
                event.fire(evt);
            } catch (Throwable t) {
                // If the exception handlers throw an error, then we send it onward
                return Response.status(Response.Status.BAD_REQUEST).entity(t).build();
            }
            // If the exception handlers swallowed the error, then then return an empty request
            // TODO is this the right thing to do?!?
            return Response.ok().build();
        }
    }

}