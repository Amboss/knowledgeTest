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
@RequestMapping("/index")
public class IndexController {

    protected static Logger logger = Logger.getLogger(IndexController.class);

    /**
     * Handles and retrieves /WEB-INF/jsp/index.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getLoginPage() {
        logger.info("index.jsp ");
        return new ModelAndView("index");
    }

}
