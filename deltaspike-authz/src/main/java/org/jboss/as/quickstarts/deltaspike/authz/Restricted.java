package org.jboss.as.quickstarts.deltaspike.authz;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import static org.jboss.as.quickstarts.deltaspike.authz.Role.USER;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;

import org.apache.deltaspike.security.api.authorization.annotation.SecurityBindingType;

/**
 * A security binding which takes a role
 * @author pmuir
 *
 */
@Retention(RUNTIME)
@Target({METHOD, TYPE})
@SecurityBindingType
@Documented
public @interface Restricted {

    @Nonbinding
    public Role value() default USER;
    
}
