package knowledgeTest.logic.model;

import knowledgeTest.model.Rating;
import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * JUnit test for the {@link knowledgeTest.model} class.
 * Testing DAO query for Rating type object.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class ModelTest extends AbstractJUnit4SpringContextTests {

    private Long id = (long) 1;
    private String name = "TestUser";
    private String pass = "password";
    private Integer access = 0;
    private Integer score = 5;
    private Integer status = 1;

    /**
     * Testing User model
     */
    @Test
    public void test_user() {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        // creating Rating
        Rating rating = new Rating(id, timestamp, score);

        // creating User
        User user = new User(id, name, pass, access, status, rating);

        // asserting
        assertNotNull("Fail - user object can't be null", user);
        assertEquals("Fail - id must be same,", id, user.getUserId());
        assertEquals("Fail - name must be same,", name, user.getUserName());
        assertEquals("Fail - password must be same,", pass, user.getPassword());
        assertEquals("Fail - access must be same,", access, user.getAccess());
        assertEquals("Fail - status must be same,", status, user.getStatus());
        assertEquals("Fail - rating must be same", rating, user.getRating());
    }

    /**
     * Testing Task model
     */
    @Test
    public void test_task() {

        Long id = (long) 1;
        String question = "Question text blu blu blu";
        String answer1 = "answer1", answer2 = "answer2", answer3 = "answer3", answer4 = "answer4";
        Integer correct = 1;

        // creating Task
        Task task = new Task(id, question, answer1, answer2, answer3, answer4, correct);

        // asserting
        assertNotNull("Fail - Task object can't be null", task);
        assertEquals("Fail - id must be same,", id, task.getTaskId());
        assertEquals("Fail - question must be same,", question, task.getQuestion());
        assertEquals("Fail - answer1 must be same,", answer1, task.getAnswer1());
        assertEquals("Fail - answer2 must be same,", answer2, task.getAnswer2());
        assertEquals("Fail - answer3 must be same,", answer3, task.getAnswer3());
        assertEquals("Fail - answer4 must be same,", answer4, task.getAnswer4());
        assertEquals("Fail - correct must be same,", correct, task.getCorrect());
    }

    /**
     * Testing Rating model
     */
    @Test
    public void test_rating() {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        // creating Rating
        Rating rating = new Rating(id, timestamp, score);

        // creating User
        User user = new User(id, name, pass, access, status, rating);

        // asserting
        assertNotNull("Fail - user object can't be null", user);
        assertNotNull("Fail - rating object can't be null", rating);
        assertEquals("Fail - id must be same,", id, rating.getRatingId());
        assertEquals("Fail - timestamp must be same,", timestamp, rating.getRatingDate());
        assertEquals("Fail - score must be same,", score, rating.getScore());
    }
}
