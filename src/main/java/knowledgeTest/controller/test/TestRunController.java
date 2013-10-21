package knowledgeTest.controller.test;

import knowledgeTest.bean.RequestTask;
import knowledgeTest.bean.TaskModel;
import knowledgeTest.logic.service.UserService;
import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles and retrieves runTest page.
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@RequestMapping("/test/runTest")
public class TestRunController extends TestAbstractController {

    protected static Logger logger = Logger.getLogger(TestRunController.class);

    // list contain task for current user
    private List<Task> taskArrayList = new ArrayList<>();

    private TaskModel taskModel = new TaskModel();

    @Autowired
    private UserService userService;

    /**
     * Initiation of runTest JSP
     * - initiating list of random tasks
     * @param userId - path variable passed by URL and specify id of current user
     * Retrieves /WEB-INF/jsp/content/test/runTest.jsp
     *
     * @return ModelAndView
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public ModelAndView initTest(@PathVariable("userId") Long userId) {
        logger.info("runTest.jsp ");

        // initiating list of random tasks
        taskArrayList = userService.getRandomListOfTasks(5);
        User user = new User();
        user.setUserId(userId);

        // initiation of runTask JSP
        return new ModelAndView("runTest", "user", user);
    }

    /**
     * this method responses to GET request
     * - saving user score if it positive;
     * - returning next TaskModel if it's number less or equals taskArrayList size;
     * @param requestTask - model to process JSON request from client
     * Retrieves /WEB-INF/jsp/content/test/runTest.jsp
     *
     * @return TaskModel
     */
    @RequestMapping(value = "/taskModel", method = RequestMethod.POST)
    public @ResponseBody TaskModel getNextQuestion(@RequestBody RequestTask requestTask,
                              HttpServletRequest request, HttpServletResponse response) {
        logger.info("runTest.jsp ");

        if (requestTask.getUserId() != 0) {

            User user = userService.findUserById(requestTask.getUserId());

            /**
             * if taskNum is null then sending first task
             * if not null then sending next task and saving score if it is positive
             * if taskNum value bigger then taskArrayList then redirecting to result page
             */
            if (requestTask.getTaskNum() == 0) {
                // sending first task
                taskModel = setJsonTaskModel(taskArrayList.get(0), requestTask.getUserId(), 1);

                // sending next task
            } else {
                if (taskModel.getTaskNum() <= taskArrayList.size()) {

                    int taskNum = requestTask.getTaskNum();
                    int foo = user.getRating().getScore();
                    int size = taskArrayList.size();
                    // saving score if it is positive
                    if (requestTask.getAnswerNum() != null) {
                        if (requestTask.getAnswerNum().equals(taskArrayList.get(taskNum - 1).getCorrect())) {

                            userService.updateUserRating(requestTask.getUserId(), foo + 1);
                        }
                    }                 // TODO  JS error: [object Object] status: error er:Index: 5, Size: 5
                    taskModel = setJsonTaskModel(taskArrayList.get(taskNum), requestTask.getUserId(), taskNum + 1);
                } else {
                    // redirecting to result page

                    redirect(request, response, "/test/result/" + requestTask.getUserId());
                    return null;
                }
            }
        } else {
            redirect(request, response, "/test/authorisation");
        }
        return taskModel;
    }

    /**
     * Method setting TaskModel bean for JSON object
     *
     * @param task    - current task to be work on;
     * @param userId  - current used Id;
     * @param taskNum - number of current task in the list;
     * @return TaskModel object
     */
    protected TaskModel setJsonTaskModel(Task task, Long userId, Integer taskNum) {
        TaskModel model = new TaskModel();
        model.setUserId(userId);
        model.setTaskNum(taskNum);
        model.setQuestion(task.getQuestion());
        model.setAnswer1(task.getAnswer1());
        model.setAnswer2(task.getAnswer2());
        model.setAnswer3(task.getAnswer3());
        model.setAnswer4(task.getAnswer4());
        return model;
    }

    /**
     * customised redirect
     */
    public void redirect(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            response.sendRedirect(request.getContextPath() + path);
        } catch (java.io.IOException e) {
            throw new BadCredentialsException("Redirect error!");
        }
    }
}
