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
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin/userManager")
public class UserManagerController extends AdminAbstractController {

    protected static Logger logger = Logger.getLogger(UserManagerController.class);

    @Autowired
    private AdminService adminService;

    /**
     * Handles and retrieves /WEB-INF/jsp/admin/adminHome.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getAdminHomePage() {
        logger.info("userManager.jsp ");
        return new ModelAndView("adminHome","userList", adminService.getAllUsers());
    }
}
