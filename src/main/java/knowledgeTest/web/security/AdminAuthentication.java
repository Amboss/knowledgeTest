package knowledgeTest.web.security;

import knowledgeTest.logic.DAO.UserDAO;
import knowledgeTest.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * this class responsible for the Authorized System Access
 * and first entrance of default entity
 */

@Service("AdminAuthentication")
public class AdminAuthentication extends SimpleUrlAuthenticationSuccessHandler
        implements AuthenticationManager, AuthenticationProvider, Serializable {

    protected static Logger userAccessLogger = Logger.getLogger(AdminAuthentication.class);

    private ShaPasswordEncoder passwordEncoder = new ShaPasswordEncoder(256);

    private UserDAO userDAO;

    private User user;

    private MessageSource messageSource;

    @Autowired
    public void setMessageSource(MessageSource messageSourcePar) {
        messageSource = messageSourcePar;
    }

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean supports(Class<? extends Object> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }

    public Authentication authenticate(Authentication auth) throws UsernameNotFoundException {

        /**
         * Init a database user object
         *
         */
        try {
            user = userDAO.findAllByParam("userName", auth.getName()).get(0);

        } catch (RuntimeException e) {
            throw new BadCredentialsException("User not located!");
        }

        /**
         * Checking if user account is active
         */
        if (user.getStatus() == 0) {
            throw new BadCredentialsException("Permission expired!");
        }

        /**
         * Compare passwords
         * Make sure to encode the password first before comparing
         */
        if (!passwordEncoder.isPasswordValid(user.getPassword(), (String) auth.getCredentials(), null)) {
            throw new BadCredentialsException("Wrong password!");
        }

        /**
         * main logic of Authentication manager
         * @return UsernamePasswordAuthenticationToken
         */
        userAccessLogger.debug("User is located!");
        return new UsernamePasswordAuthenticationToken(
                auth.getName(),
                auth.getCredentials(),
                getAuthorities(user.getAccess()));
    }

    /**
     * Retrieves the correct ROLE type depending on the access level
     *
     * @return list of granted authorities
     */
    public Collection<GrantedAuthority> getAuthorities(Integer access) {
        // Create a list of grants for this user
        List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(2);

        // All users are granted with ROLE_USER access by default
        userAccessLogger.debug("Grant ROLE_USER to this user");
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));

        // Check if this user has admin access
        // interpret Integer(1) as an admin user
        if (access.compareTo(1) == 0) {
            // User has admin access
            userAccessLogger.debug("Grant ROLE_ADMIN to this user");
            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return authList;
    }
}
