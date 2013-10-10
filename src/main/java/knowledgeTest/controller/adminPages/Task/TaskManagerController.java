package knowledgeTest.controller.adminPages.task;

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
 * Handles and retrieves admin task manager page.
 * User must be authenticated as Admin to see this page
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin/taskManager")
public class TaskManagerController extends AdminAbstractController {

    protected static Logger logger = Logger.getLogger(TaskManagerController.class);

    @Autowired
    private AdminService adminService;

    /**
     * Retrieves /WEB-INF/jsp/admin/taskManager.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTaskManagerPage() {
        logger.info("taskManager.jsp ");
        return new ModelAndView("taskManager", "taskList", adminService.getAllTasks());
    }
}