package knowledgeTest.controller.access;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves login page.
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@RequestMapping("/access/login")
public class LoginLogoutController {

    protected static Logger logger = Logger.getLogger(LoginLogoutController.class);

    /**
     * Handles and retrieves /access/login.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        logger.debug("Received request to show denied page");
        return new ModelAndView("login");
    }


}
