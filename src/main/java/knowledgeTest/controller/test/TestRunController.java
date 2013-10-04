package knowledgeTest.controller.test;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves runTest page.
 */
@Controller
@Secured("ROLE_USER")
@RequestMapping("/test/runTest")
public class TestRunController extends TestAbstractController {

    protected static Logger logger = Logger.getLogger(TestRunController.class);

    /**
     * Handles and retrieves /WEB-INF/jsp/test/runTest.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTestRunPage() {
        logger.info("runTest.jsp ");
        return new ModelAndView("runTest");
    }
}
