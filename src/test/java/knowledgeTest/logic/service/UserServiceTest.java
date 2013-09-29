package knowledgeTest.logic.service;

import knowledgeTest.model.Rating;
import knowledgeTest.model.User;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

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

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    private static Timestamp timestamp;

    @BeforeClass
    public static void initiate() {
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /*
     * Creating User Object with params for current test
     * (id, pass, access, rating)
     */
    private User getUserObject() {
        return new User(null, "TestUser", "testPass", 0, null);
    }

    /*
    * Creating User Object with params for current test
    * (id, ratingDate, score, taskList)
    */
    private Rating getRatingObject() {
        return new Rating(null, timestamp, 20, null);
    }

    /**
     * Testing findUserByUserName() method
     */
    @Test
    public void test_findUserByUserName() {
        // creating user

    }

    /**
     * Testing findUserById() method
     */
    @Test
    public void test_findUserById() {

    }

    /**
     * Testing getRandomListOfTasks() method
     */
    @Test
    public void test_getRandomListOfTasks() {

    }

    /**
     * Testing findTaskById() method
     */
    @Test
    public void test_findTaskById() {

    }

    /**
     * Testing saveUserRating() method
     */
    @Test
    public void test_saveUserRating() {

    }

    /**
     * Testing getUserRatingById() method
     */
    @Test
    public void test_getUserRatingById() {

    }

    /**
     * Testing setUserRatingDate() method
     */
    @Test
    public void test_setUserRatingDate() {

    }
}
