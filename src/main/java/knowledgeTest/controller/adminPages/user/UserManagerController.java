package knowledgeTest.controller.adminPages.user;

import knowledgeTest.controller.adminPages.AdminAbstractController;
import knowledgeTest.logic.service.AdminService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves admin home page.
 * User must be authenticated as Admin to see this page
 * 8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin/userManager")
public class UserManagerController extends AdminAbstractController {

    protected static Logger logger = Logger.getLogger(UserManagerController.class);

    @Autowired
    private AdminService adminService;

    /**
     * Handles and retrieves /WEB-INF/jsp/content/admin/user/userManager.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAdminHomePage() {
        logger.info("userManager.jsp ");
        return new ModelAndView("userManager","userList", adminService.getAllUsers());
    }
}
