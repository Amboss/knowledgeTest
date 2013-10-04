package knowledgeTest.controller.test;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves result page after end of test.
 */
@Controller
@Secured("ROLE_USER")
@RequestMapping("/test/result")
public class TestResultController extends TestAbstractController {

    protected static Logger logger = Logger.getLogger(TestResultController.class);

    /**
     * Handles and retrieves /WEB-INF/jsp/test/result.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTestResultPage() {
        logger.info("result.jsp ");
        return new ModelAndView("result");
    }
}