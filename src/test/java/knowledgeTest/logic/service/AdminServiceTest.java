package knowledgeTest.logic.service;

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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit test for the {@link knowledgeTest.logic.service.impl.AdminServiceImpl} class.
 * Testing service functions for ADMIN_ROLE.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class AdminServiceTest extends AbstractJUnit4SpringContextTests {

    private static ShaPasswordEncoder passEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @BeforeClass
    public static void initiate() {
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

    /**
     * Testing createUser() method
     */
    @Test
    public void test_createUser() {
        // creating and saving User object
        adminService.createUser(getUserObject());

        // asserting
        User user = userService.findUserByUserName("TestUser");
        assertNotNull("Fail - user mast contain target object", user);
        assertEquals("Fail - password must be same", getUserObject().getPassword(), user.getPassword());
        assertEquals("Fail - access must be same", getUserObject().getAccess(), user.getAccess());

        // cleaning DB
        adminService.deleteUser(user.getUserId());
    }

    /**
     * Testing updateUser() method   TODO
     */
    @Test
    public void test_updateUser() {
        // creating and saving User object
        adminService.createUser(getUserObject());

        // updating user in DB
        User userBefore = userService.findUserByUserName("TestUser");
        String newPass = passEncoder.encodePassword("newPass", null);
        userBefore.setUserName("newTestName");
        userBefore.setPassword(newPass);
        userBefore.setAccess(1);
        adminService.updateUser(userBefore);

        // asserting
        User userAfter = userService.findUserById(userBefore.getUserId());
        assertNotNull("Fail - user mast contain target object", userAfter);
        assertEquals("Fail - userName must be same", userBefore.getUserName(), userAfter.getUserName());
        assertEquals("Fail - password must be same", userBefore.getPassword(), userAfter.getPassword());
        assertEquals("Fail - access must be same", userBefore.getAccess(), userAfter.getAccess());

        // cleaning DB
        adminService.deleteUser(userAfter.getUserId());
    }

    /**
     * Testing deleteUser() method
     */
    @Test
    public void test_deleteUser() {
        // creating and saving User object
        adminService.createUser(getUserObject());

        // deleting user
        User user = userService.findUserByUserName("TestUser");
        adminService.deleteUser(user.getUserId());

        // asserting
        boolean thrown = false;
        try {
            userService.findUserById(user.getUserId());
        } catch (IndexOutOfBoundsException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    /**
     * Testing getAllUsers() method   TODO
     */
    @Test
    public void test_getAllUsers() {
        // creating few users
        ArrayList<String> userNameList = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            adminService.createUser(new User(null, Integer.toString(i),
                    passEncoder.encodePassword(Integer.toString(i), null), 0, null));
            userNameList.add(Integer.toString(i));
        }

        // asserting
        List<User> userList = adminService.getAllUsers();
        assertNotNull("Fail - userList mast contain target objects", userList);
        assertEquals("Fail - userList mast contain " + userNameList.size() + ",",
                userNameList.size(), userList.size());
        for (int j = 0; j <= userNameList.size() - 1; j++) {
            assertEquals("Fail - name not same", userNameList.get(j), userList.get(j).getUserName());
        }

        // cleaning DB
        for (User user : userList) {
            adminService.deleteUser(user.getUserId());
        }
    }

    /**
     * Testing createTask() method
     */
    @Test
    public void test_createTask() {
        // creating new Task object
        adminService.createTask(getTaskObject());

        // asserting
        List<Task> list = adminService.getAllTasks();
        for (Task foo : list) {
            if (foo.getQuestion().equals(getTaskObject().getQuestion())) {
                assertEquals("FAIL - task answer1 must be the same,",
                        getTaskObject().getAnswer1(), foo.getAnswer1());
                assertEquals("FAIL - task answer2 must be the same,",
                        getTaskObject().getAnswer2(), foo.getAnswer2());
                assertEquals("FAIL - task answer3 must be the same,",
                        getTaskObject().getAnswer3(), foo.getAnswer3());
                assertEquals("FAIL - task answer4 must be the same,",
                        getTaskObject().getAnswer4(), foo.getAnswer4());
                assertEquals("FAIL - task correct must be the same,",
                        getTaskObject().getCorrect(), foo.getCorrect());
            }

            // cleaning DB
            adminService.deleteTask(foo.getTaskId());
        }
    }

    /**
     * Testing updateTask() method
     */
    @Test
    public void test_updateTask() {
        // creating new Task object
        adminService.createTask(getTaskObject());

        // updating
        List<Task> list1 = adminService.getAllTasks();
        assert list1 != null : "Fail - List can't be null!";
        Task taskBefore = new Task();
        for (Task foo : list1) {
            if (foo.getQuestion().equals(getTaskObject().getQuestion())) {
                foo.setQuestion("newQuestion");
                foo.setAnswer1("newAnswer1");
                foo.setAnswer2("newAnswer2");
                foo.setAnswer3("newAnswer3");
                foo.setAnswer4("newAnswer4");
                foo.setCorrect(4);
                taskBefore = foo;
                adminService.updateTask(foo);
            }
        }

        // asserting
        List<Task> list2 = adminService.getAllTasks();
        for (Task foo : list2) {
            if (foo.getQuestion().equals(taskBefore.getQuestion())) {
                assertEquals("FAIL - task answer1 must be the same,",
                        taskBefore.getAnswer1(), foo.getAnswer1());
                assertEquals("FAIL - task answer2 must be the same,",
                        taskBefore.getAnswer2(), foo.getAnswer2());
                assertEquals("FAIL - task answer3 must be the same,",
                        taskBefore.getAnswer3(), foo.getAnswer3());
                assertEquals("FAIL - task answer4 must be the same,",
                        taskBefore.getAnswer4(), foo.getAnswer4());
                assertEquals("FAIL - task correct must be the same,",
                        taskBefore.getCorrect(), foo.getCorrect());
            }

            // cleaning DB
            adminService.deleteTask(foo.getTaskId());
        }
    }

    /**
     * Testing deleteTask() method
     */
    @Test
    public void test_deleteTask() {
        // creating new Task object
        adminService.createTask(getTaskObject());

        // deleting task
        List<Task> listBefore = adminService.getAllTasks();
        for (Task foo : listBefore) {
            if (foo.getQuestion().equals(getTaskObject().getQuestion())) {
                adminService.deleteTask(foo.getTaskId());
            }
        }

        List<Task> listAfter = adminService.getAllTasks();
        for (Task foo : listAfter) {
            assertNull(foo);
        }
    }

    /**
     * Testing getAllTasks() method
     */
    @Test
    public void test_getAllTasks() {
        // creating few tasks
        List<String> questionList = new ArrayList<>();
        for (int i = 0; i <= 5; i++) {
            String str = "Question" + Integer.toString(i);
            adminService.createTask(new Task(null, str, "answer1", "answer2", "answer3", "answer4", 1));
            questionList.add(str);
        }

        // asserting
        List<Task> taskList = adminService.getAllTasks();
        assertNotNull("FAIL - task list can't be null", taskList);
        assertEquals("Fail - taskList must contain " + questionList.size(),
                questionList.size(), taskList.size());
        for (int j = 0; j <= questionList.size() - 1; j++) {
            assertEquals("Fail - question must be same", questionList.get(j), taskList.get(j).getQuestion());
        }

        // cleaning DB
        for(Task foo : taskList) {
            adminService.deleteTask(foo.getTaskId());
        }
    }
}
