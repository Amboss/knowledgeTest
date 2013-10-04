package knowledgeTest.controller;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves index page.
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@RequestMapping("/")
public class IndexController {

    protected static Logger logger = Logger.getLogger(IndexController.class);

    /**
     * Retrieves /WEB-INF/jsp/content/index.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        logger.info("loading index.jsp ");
        return new ModelAndView("index");
    }

    /**
     * Handles /WEB-INF/jsp/content/index.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onLoginSubmit() {
        logger.info("redirecting to authorisation.jsp ");
        return new ModelAndView("redirect:/test/authorisation");
    }
}
