package knowledgeTest.controller.testPages;

import knowledgeTest.model.JsonRequestTask;
import knowledgeTest.model.JsonTaskModel;
import knowledgeTest.logic.service.UserService;
import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles and retrieves runTest page.
 *
 * TODO - save state in session;
 * TODO - remove logic from controller;
 */
@Controller
@Secured("IS_AUTHENTICATED_ANONYMOUSLY")
@RequestMapping("/test/runTest")
public class TestRunController extends TestAbstractController {

    protected static Logger logger = Logger.getLogger(TestRunController.class);

    // list contain task for current user
    private List<Task> taskArrayList = new ArrayList<>();

    private JsonTaskModel taskModel = new JsonTaskModel();

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
     * - returning next JsonTaskModel if it's number less or equals taskArrayList size;
     * @param requestTask - model to process JSON request from client
     * Retrieves /WEB-INF/jsp/content/test/runTest.jsp
     *
     * @return JsonTaskModel
     */
    @RequestMapping(value = "/taskModel", method = RequestMethod.POST)
    public @ResponseBody
    JsonTaskModel getNextQuestion(@RequestBody JsonRequestTask requestTask) {
        logger.info("runTest.jsp ");

        if (requestTask.getUserId() != 0) {

            User user = userService.findUserById(requestTask.getUserId());

            /**
             * if taskNum is null then sending first task
             * if not null then sending next task and saving score if it is positive
             * if taskNum value bigger then taskArrayList then redirecting to result page
             */
            if (requestTask.getTaskNum().equals(null)) {
                // sending first task
                return userService.setJsonTaskModel(taskArrayList.get(0), requestTask.getUserId(), 0, null);

                // sending next task
            } else {
                if (taskModel.getTaskNum() < taskArrayList.size() - 1) {

                    int taskNum = requestTask.getTaskNum();
                    int foo = user.getRating().getScore();

                    // saving score if it is positive
                    if (!requestTask.getAnswerNum().equals(null)) {
                        if (requestTask.getAnswerNum().equals(taskArrayList.get(taskNum).getCorrect())) {

                            userService.updateUserRating(requestTask.getUserId(), foo + 1);
                        }
                    }
                    // sending task with index 1, 2, 3, 4
                    return userService.setJsonTaskModel(taskArrayList.get(taskNum + 1),
                            requestTask.getUserId(), taskNum + 1, null);
                } else {
                    // redirecting to result page
                    return userService.setJsonTaskModel(null, null, null, "/test/result/" + requestTask.getUserId());
                }
            }
        } else {
            // redirecting to authorisation page
            return userService.setJsonTaskModel(null, null, null, "/test/authorisation");
        }
    }
}
