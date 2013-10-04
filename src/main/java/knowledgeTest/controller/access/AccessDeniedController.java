package knowledgeTest.controller.access;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles access denied page.
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@RequestMapping("/access/accessDenied")
public class AccessDeniedController {

    protected static Logger logger = Logger.getLogger(AccessDeniedController.class);

    /**
     * Handles and retrieves /access/accessDenied.jsp
     * shown whenever a access denied.
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getDeniedPage() {
        logger.debug("Received request to show denied page");
        return new ModelAndView("accessDenied");



    }
}
