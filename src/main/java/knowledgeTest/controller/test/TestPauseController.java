package knowledgeTest.controller.test;

import org.apache.log4j.Logger;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves pause page.
 */
@Controller
@Secured("ROLE_USER")
@RequestMapping("/test/pause")
public class TestPauseController extends TestAbstractController {

    protected static Logger logger = Logger.getLogger(TestPauseController.class);

    /**
     * Handles and retrieves /WEB-INF/jsp/test/pause.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTestPausePage() {
        logger.info("pause.jsp ");
        return new ModelAndView("pause");
    }
}
