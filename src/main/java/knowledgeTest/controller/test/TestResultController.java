package knowledgeTest.controller.test;

import knowledgeTest.logic.service.UserService;
import knowledgeTest.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves result page after end of test.
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@RequestMapping("/test/result")
public class TestResultController extends TestAbstractController {

    protected static Logger logger = Logger.getLogger(TestResultController.class);

    @Autowired
    private UserService userService;

    /**
     * Handles and retrieves /WEB-INF/jsp/test/result.jsp
     *
     * @return ModelAndView object
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ModelAndView getTestResultPage(@PathVariable("id") Long id,
                                          BindingResult errors) {
        logger.info("result.jsp ");

        if (id != null) {
            User user = userService.findUserById(id);
            ModelAndView model = new ModelAndView("result");
            model.addObject("user", user);
            model.addObject("rating", user.getRating());
            return model;
        } else {
            return new ModelAndView("redirect:/test/authorisation");
        }
    }
}