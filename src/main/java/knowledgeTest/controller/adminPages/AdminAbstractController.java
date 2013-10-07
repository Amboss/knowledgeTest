package knowledgeTest.controller.adminPages;

import knowledgeTest.logic.service.AdminService;
import knowledgeTest.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

/**
 * Abstract controller for Admin role pages common functionality
 */
@Controller
@Secured("ROLE_ADMIN")
public abstract class AdminAbstractController {

    private AdminService adminService;

    private UserService userService;

    @Autowired
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
