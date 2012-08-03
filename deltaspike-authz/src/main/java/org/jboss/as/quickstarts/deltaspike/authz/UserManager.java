package org.jboss.as.quickstarts.deltaspike.authz;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

/**
 * A really simple user manager.
 * 
 * @author Pete Muir
 *
 */
@SuppressWarnings("serial")
@SessionScoped
public class UserManager implements Serializable {
    
    @Produces @LoggedIn
    private String loggedInUser;
    
    public String getLoggedInUser() {
        return loggedInUser;
    }
    
    public void setLoggedInUser(String loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

}
