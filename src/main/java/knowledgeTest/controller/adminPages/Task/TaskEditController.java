package knowledgeTest.controller.adminPages.task;

import knowledgeTest.controller.adminPages.AdminAbstractController;
import knowledgeTest.logic.service.AdminService;
import knowledgeTest.logic.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Handles and retrieves admin page responsible for edit/ creation of Task
 * User must be authenticated as Admin to see this page
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin/edit_task")
public class TaskEditController extends AdminAbstractController {

    protected static Logger logger = Logger.getLogger(TaskEditController.class);

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    /**
     * Handles and retrieves /WEB-INF/jsp/admin/adminHome.jsp
     *
     * @return ModelAndView object
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView getAdminHomePage(@PathVariable("id") Long id) {
        logger.info("adminHome.jsp");

        ModelAndView model = new ModelAndView("adminHome");
        model.addObject("task", userService.findTaskById(id));
        return model;
    }
}
