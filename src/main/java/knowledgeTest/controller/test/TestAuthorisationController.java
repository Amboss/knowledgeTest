package knowledgeTest.controller.test;

import knowledgeTest.logic.service.UserService;
import knowledgeTest.model.User;
import knowledgeTest.web.validation.UserValidation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves authorisation page.
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@RequestMapping("/test/authorisation")
public class TestAuthorisationController extends TestAbstractController {

    protected static Logger logger = Logger.getLogger(TestAuthorisationController.class);

    private UserService userService;

    private UserValidation validation;

    @Autowired
    public void setValidation(UserValidation validation) {
        this.validation = validation;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves /WEB-INF/jsp/test/authorisation.jsp
     *
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAuthorisationPage() {
        logger.info("loading authorisation.jsp ");
        return new ModelAndView("authorisation", "user", new User());
    }

    /**
     * Handles /WEB-INF/jsp/test/authorisation.jsp
     *
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onAuthorisationSubmit(@ModelAttribute("user") User user,
                                              BindingResult errors, Authentication auth) {
        logger.info("processing authorisation.jsp ");

        validation.validate(user, errors);

        if (!errors.hasErrors()) {
            // creating
            userService.createUserForTest(user.getUserName());

//            return new UsernamePasswordAuthenticationToken(
//                    auth.getName(),
//                    auth.getCredentials(),
//                    getAuthorities(user.getAccess()));
            return new ModelAndView("redirect:/test/runTest");
        } else {
            return new ModelAndView("authorisation", "user", user);
        }
    }
}