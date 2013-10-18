package knowledgeTest.controller.test;

import knowledgeTest.bean.JsonTaskModel;
import knowledgeTest.logic.service.UserService;
import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @Autowired
    private UserService userService;

    /**
     * Initiation of runTest JSP
     * - initiating list of random tasks
     * <p/>
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
     * retrieving task in JSON object
     * - saving user score if it positive;
     * - returning next task JsonTaskModel if it's number less or equals taskArrayList size;
     * <p/>
     * Retrieves /WEB-INF/jsp/content/test/runTest.jsp
     *
     * @return JsonTaskModel
     */
    @RequestMapping(value = "/{userId}/{taskNum}/{answer}", method = RequestMethod.GET)
    public
    @ResponseBody
    JsonTaskModel getNextQuestion(@PathVariable("userId") Long userId,
                                  @PathVariable("taskNum") Integer taskNum,
                                  @PathVariable("answer") Integer answer,
                                  HttpServletRequest request, HttpServletResponse response) {
        logger.info("runTest.jsp ");

        JsonTaskModel jsonTaskModel = new JsonTaskModel();

        if (userId != null) {

            User user = userService.findUserById(userId);

            /**
             * if taskNum is null then sending first task
             * if not null then sending next task and saving score if it is positive
             * if taskNum value bigger then taskArrayList then redirecting to result page
             */
            if (taskNum == 0 && answer == 0) {
                // sending first task
                jsonTaskModel = setJsonTaskModel(taskArrayList.get(0), userId, 1);

                // sending next task
            } else {
                if (taskNum <= taskArrayList.size() - 1) {

                    // saving score if it is positive
                    if (answer.equals(taskArrayList.get(taskNum).getCorrect())) {
                        userService.updateUserRating(userId, user.getRating().getScore() + answer);
                    }

                    jsonTaskModel = setJsonTaskModel(taskArrayList.get(++taskNum), userId, taskNum);
                } else {
                    // redirecting to result page
                    redirect(request, response, "/test/result/" + userId);
                }
            }
        } else {
            redirect(request, response, "/test/authorisation");
        }
        return jsonTaskModel;
    }

    /**
     * Method setting JsonTaskModel bean for JSON object
     *
     * @param task    - current task to be work on;
     * @param userId  - current used Id;
     * @param taskNum - number of current task in the list;
     * @return JsonTaskModel object
     */
    protected JsonTaskModel setJsonTaskModel(Task task, Long userId, Integer taskNum) {
        JsonTaskModel model = new JsonTaskModel();
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
