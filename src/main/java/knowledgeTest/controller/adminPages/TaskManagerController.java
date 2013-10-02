package knowledgeTest.controller.adminPages;

import org.apache.log4j.Logger;
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

    /**
     * Handles and retrieves /WEB-INF/jsp/admin/taskManager.jsp
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getTaskManagerPage() {
        logger.info("taskManager.jsp ");
        return new ModelAndView("/admin/taskManager.jsp");
    }
}