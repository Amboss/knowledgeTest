package knowledgeTest.controller.test;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves authorisation page.
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@RequestMapping("/test/authorisation")
public class TestAutenticationController extends TestAbstractController {

    protected static Logger logger = Logger.getLogger(TestAutenticationController.class);

    /**
     * Handles and retrieves /WEB-INF/jsp/test/authorisation.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAuthorisationPage() {
        logger.info("authorisation.jsp ");
        return new ModelAndView("test/authorisation");
    }
}
