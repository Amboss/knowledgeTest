package knowledgeTest.controller.test;

import knowledgeTest.bean.JsonTaskModel;
import knowledgeTest.logic.service.UserService;
import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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

    // numberInList / taskId
    private List<Task> taskArrayList = new ArrayList<>();

    @Autowired
    private UserService userService;

    /**
     * - initiating list of random tasks
     * - returning first task;
     * <p/>
     * Retrieves /WEB-INF/jsp/content/test/runTest.jsp
     *
     * @return ModelAndView object with list of tasks
     */
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView initTest(@PathVariable("userId") Long userId) {
        logger.info("runTest.jsp ");

        // initiating list of random tasks
        taskArrayList = userService.getRandomListOfTasks(5);

        // returning first task
        return new ModelAndView("runTest", "jsonModel", setJsonTaskModel(taskArrayList.get(0), userId, 1));
    }

    /**
     * - saving user score if it positive;
     * - returning next task if it's number less or equals taskArrayList size;
     * <p/>
     * Retrieves /WEB-INF/jsp/content/test/runTest.jsp
     *
     * @return ModelAndView object with list of tasks
     */
    @RequestMapping(value = "/{userName}/{taskNum}/{answer}", method = RequestMethod.GET)
    public
    @ResponseBody
    ModelAndView getNextQuestion(@PathVariable("userId") Long userId,
                                 @PathVariable("taskNum") Integer taskNum,
                                 @PathVariable("answer") Integer answer) {
        logger.info("runTest.jsp ");

        if (taskNum != null && answer != null && userId!= null) {

            // if task number is less or equals taskArrayList size
            if (taskNum <= taskArrayList.size() - 1) {

                // saving user score
                if (answer.equals(taskArrayList.get(taskNum).getCorrect())) {
                    User user = userService.findUserById(userId);
                    userService.updateUserRating(userId, user.getRating().getScore() + answer);
                }

                // returning next task
                return new ModelAndView("runTest", "jsonModel", setJsonTaskModel(taskArrayList.get(++taskNum), userId, taskNum));
            } else {
                // redirecting to result page
                return new ModelAndView("redirect:/test/result/" + userId);
            }
        } else {
            return new ModelAndView("redirect:/test/authorisation");
        }
    }

    /**
     * returning next question of quiz
     * <p/>
     * Handles /WEB-INF/jsp/content/test/runTest.jsp
     *
     * @return ModelAndView object
     */
    @RequestMapping(value = "/{userName}/{score}", method = RequestMethod.POST)
    public ModelAndView onRunPageSubmit(@ModelAttribute("user") User user,
                                        @PathVariable("score") Integer score,
                                        BindingResult errors) {
        logger.info("runTest.jsp");

        if (user != null && score != null) {
            userService.updateUserRating(user.getUserId(), score);
            return new ModelAndView("redirect:/test/result/" + user.getUserId());
        } else {
            return new ModelAndView("redirect:/");
        }
    }

    /**
     * Method setting JsonTaskModel bean for JSON object
     * @param task - current task to be work on;
     * @param userId - current used Id;
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
}
