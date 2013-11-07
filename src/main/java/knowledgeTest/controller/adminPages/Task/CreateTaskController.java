package knowledgeTest.controller.adminPages.task;

import knowledgeTest.controller.adminPages.AdminAbstractController;
import knowledgeTest.logic.service.AdminService;
import knowledgeTest.logic.service.UserService;
import knowledgeTest.model.Task;
import knowledgeTest.components.validation.TaskValidation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Handles and retrieves admin page responsible for edit/ creation of Task
 * User must be authenticated as Admin to see this page
 */
@Controller
@Secured("ROLE_ADMIN")
@RequestMapping("/admin/create_task")
public class CreateTaskController extends AdminAbstractController {

    protected static Logger logger = Logger.getLogger(CreateTaskController.class);

    private TaskValidation validation;

    @Autowired
    public void setValidation(TaskValidation validation) {
        this.validation = validation;
    }

    @Autowired
    private AdminService adminService;

    @Autowired
    private UserService userService;

    /**
     * Handles and retrieves /WEB-INF/jsp/content/admin/task/create_task.jsp
     *
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getCreateTaskPage() {
        logger.info("createTask.jsp");

        return new ModelAndView("create_task", "task", new Task());
    }

    /**
     * Handles and retrieves /WEB-INF/jsp/content/admin/task/create_task.jsp
     *
     * @return ModelAndView object
     */
    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView onTaskEditsubmit(@ModelAttribute("task")Task task, BindingResult result,
                                         SessionStatus status, HttpServletRequest request) {

        logger.info("adminHome.jsp");

        validation.validate(task, result);

        if (!result.hasErrors()) {
            adminService.createTask(task);
            return new ModelAndView("redirect:/admin/taskManager");
        } else {
            return new ModelAndView("create_task", "task", task);
        }
    }
}
