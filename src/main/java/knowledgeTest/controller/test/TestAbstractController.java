package knowledgeTest.controller.test;

import knowledgeTest.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

/**
 * Abstract controller for test pages common functionality
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
public abstract class TestAbstractController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
