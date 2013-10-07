package knowledgeTest.controller.test;

import knowledgeTest.logic.service.UserService;
import knowledgeTest.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves runTest page.
 */
@Controller
@Secured("ROLE_USER")
@RequestMapping("/test/runTest/{userName}")
public class TestRunController extends TestAbstractController {

    protected static Logger logger = Logger.getLogger(TestRunController.class);

    @Autowired
    private UserService userService;

    /**
     * Retrieves /WEB-INF/jsp/content/test/runTest.jsp
     *
     * @return ModelAndView object with list of tasks
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTestRunPage(@PathVariable("userName") String userName,
                                       BindingResult result) {
        logger.info("runTest.jsp ");

        if (userName != null) {
            ModelAndView model = new ModelAndView("runTest");
            model.addObject("tasks", userService.getRandomListOfTasks(5));
            User user = userService.findUserByUserName(userName);
            model.addObject("user", user);
            return model;
        } else {
            return new ModelAndView("redirect:/test/authorisation");
        }

    }

    /**
     * Handles /WEB-INF/jsp/content/test/runTest.jsp
     *
     * @return ModelAndView object
     */
    @RequestMapping(value = "{score}", method = RequestMethod.POST)
    public ModelAndView onRunPageSubmit(@ModelAttribute("user")User user,
                                        @PathVariable("score")Integer score,
                                        BindingResult errors) {
        logger.info("runTest.jsp ");

        if (user != null && score != null) {
            userService.updateUserRating(user.getUserId(), score);
            return new ModelAndView("redirect:/test/result/" + user.getUserId());
        } else {
            return new ModelAndView("redirect:/");
        }
    }
}
