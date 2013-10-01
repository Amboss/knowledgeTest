package knowledgeTest.logic.service;

import knowledgeTest.model.Rating;
import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * JUnit test for the {@link knowledgeTest.logic.service.impl.UserServiceImpl} class.
 * Testing service functions for USER_ROLE.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    private static ShaPasswordEncoder passEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    private static Timestamp timestamp;

    @BeforeClass
    public static void initiate() {
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
        passEncoder = new ShaPasswordEncoder(256);
    }

    /*
     * Creating User Object with params for current test
     * (id, pass, access, rating)
     */
    private User getUserObject() {
        return new User(null, "TestUser", passEncoder.encodePassword("testPass", null), 0, null);
    }

    /*
     * Creating Task Object with params for current test
     * (taskId, question, answer1, answer2, answer3, answer4, correct)
     */
    private Task getTaskObject() {
        return new Task(null, "Question text blu blu blu", "answer1", "answer2", "answer3", "answer4", 1);
    }

    /*
     * Creating User Object with params for current test
     * (id, ratingDate, score, taskList)
     */
    private Rating getRatingObject() {
        return new Rating(null, timestamp, 20);
    }

    /**
     * Testing findUserByUserName() method
     */
    @Test
    public void test_findUserByUserName() {
        // creating user
        adminService.createUser(getUserObject());

        // asserting
        User user = userService.findUserByUserName(getUserObject().getUserName());
        assertNotNull("FAIL - user must contain object", user);
        assertEquals("FAIL - userName must be same", getUserObject().getUserName(), user.getUserName());
        assertEquals("FAIL - password must be same", getUserObject().getPassword(), user.getPassword());
        assertEquals("FAIL - access must be same", getUserObject().getAccess(), user.getAccess());

        // cleaning DB
        adminService.deleteUser(user.getUserId());
    }

    /**
     * Testing findUserById() method
     */
    @Test
    public void test_findUserById() {
        // creating user
        adminService.createUser(getUserObject());
        User userFoo = userService.findUserByUserName(getUserObject().getUserName());

        if (userFoo != null) {
            // asserting
            User user = userService.findUserById(userFoo.getUserId());
            assertNotNull("FAIL - user must contain object", user);
            assertEquals("FAIL - userName must be same", getUserObject().getUserName(), user.getUserName());
            assertEquals("FAIL - password must be same", getUserObject().getPassword(), user.getPassword());
            assertEquals("FAIL - access must be same", getUserObject().getAccess(), user.getAccess());

            // cleaning DB
            adminService.deleteUser(user.getUserId());
        }
    }

    /**
     * Testing getRandomListOfTasks() method
     */
    @Test
    public void test_getRandomListOfTasks() {
        // creating few tasks
        List<String> questionList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            String str = "Question" + Integer.toString(i);
            adminService.createTask(new Task(null, str, "answer1", "answer2", "answer3", "answer4", 1));
            questionList.add(str);
        }

        // asserting
        Integer amount = 5;
        List<Task> taskList = userService.getRandomListOfTasks(amount);
        assertEquals("FAIL - taskSet must contain 5 objects", amount, (Object)taskList.size());

        // cleaning DB
        List<Task> listToClean = adminService.getAllTasks();
        for (Task foo : listToClean) {
            adminService.deleteTask(foo.getTaskId());
        }
    }

    /**
     * Testing findTaskById() method
     */
    @Test
    public void test_findTaskById() {
        //creating task
        adminService.createTask(getTaskObject());
        Task task = adminService.getAllTasks().get(0);

        // asserting
        if (task != null) {
            Task targetTask = userService.findTaskById(task.getTaskId());

            assertNotNull("FAIL - targetTask can't be null");
            assertEquals("FAIL - task answer1 must be the same,",
                    getTaskObject().getAnswer1(), targetTask.getAnswer1());
            assertEquals("FAIL - task answer2 must be the same,",
                    getTaskObject().getAnswer2(), targetTask.getAnswer2());
            assertEquals("FAIL - task answer3 must be the same,",
                    getTaskObject().getAnswer3(), targetTask.getAnswer3());
            assertEquals("FAIL - task answer4 must be the same,",
                    getTaskObject().getAnswer4(), targetTask.getAnswer4());
            assertEquals("FAIL - task correct must be the same,",
                    getTaskObject().getCorrect(), targetTask.getCorrect());

            // cleaning DB
            adminService.deleteTask(task.getTaskId());
        }
    }

    /**
     * Testing updateUserRating() method       TODO
     */
    @Test
    public void test_updateUserRating() {
        // creating user
        adminService.createUser(getUserObject());

        // creating few tasks
        for (int i = 1; i <= 10; i++) {
            String str = "Question" + Integer.toString(i);
            adminService.createTask(new Task(null, str, "answer1", "answer2", "answer3", "answer4", 1));
        }

        User user = userService.findUserByUserName(getUserObject().getUserName());
        if (user != null) {

            // saving rating of supported user
            Integer score = 5;
            userService.updateUserRating(user.getUserId(), score);

            // asserting
            User targetUser = userService.findUserById(user.getUserId());
            assertNotNull("FAIL - targetUsr must contain object!", targetUser);
            assertEquals("FAIL - userName must be same,", user.getUserName(), targetUser.getUserName());
            assertNotNull("FAIL - Date can't be null,", targetUser.getRating().getRatingDate());
            assertEquals("FAIL - score must be same,", score, targetUser.getRating().getScore());

            // cleaning DB
            List<Task> listToClean = adminService.getAllTasks();
            for (Task foo : listToClean) {
                adminService.deleteTask(foo.getTaskId());
            }

            adminService.deleteUser(user.getUserId());
        }
    }
}
