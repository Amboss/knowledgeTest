package knowledgeTest.logic.DAO;

import knowledgeTest.model.Rating;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit test for the {@link knowledgeTest.logic.DAO.impl.RatingDAOImpl} class.
 * Testing DAO query for Rating type object.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class RatingDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RatingDAO ratingDAO;

    private static Timestamp timestamp;

    @BeforeClass
    public static void initiate() {
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /*
     * Creating User Object with params for current test
     * (id = null, ratingDate = timestamp, score = 20, taskList = null)
     */
    private Rating getRatingObject() {
        return new Rating(null, timestamp, 20, null);
    }

    /**
     * Testing Rating delete() method
     */
    @After
    public void test_delete() {
        //searching and deleting tested object
        Rating rating = new Rating();
        rating.setScore(20);
        List<Rating> before = ratingDAO.findAllByParam("score", rating.getScore());
        for (Rating foo : before) {
            if (foo.getScore().equals(getRatingObject().getScore())) {
                ratingDAO.delete(foo.getRatingId());
            }
        }

        // asserting
        List<Rating> after = ratingDAO.findAllByParam("score", rating.getScore());
        for (Rating foo : after) {
            assertNull("FAIL - rating must be deleted", foo);
        }
    }

    /**
     * Testing Rating delete() method
     */
    @Test
    public void test_save() {
        // creating new User object
        ratingDAO.save(getRatingObject());

        // asserting
        List<Rating> list = ratingDAO.findAllByParam("score", getRatingObject().getScore());
        assertNotNull("FAIL - rating must be not null", list);
    }

    /**
     * Testing Rating delete() method
     */
    @Test
    public void test_findAllByParam() {
        // creating new User object
        ratingDAO.save(getRatingObject());

        // asserting
        List<Rating> list = ratingDAO.findAllByParam("score", getRatingObject().getScore());
        assertNotNull("FAIL - rating must be not null", list);
    }

    /**
     * Testing Rating delete() method
     */
    @Test
    public void test_find() {
        // creating new User object
        ratingDAO.save(getRatingObject());

        // asserting
        List<Rating> list = ratingDAO.findAllByParam("score", getRatingObject().getScore());
        for (Rating foo : list) {
            if (foo.getScore().equals(getRatingObject().getScore())) {

                // asserting
                Rating newRating = ratingDAO.find(foo.getRatingId());
                assertEquals("FAIL - score must be the same,", getRatingObject().getScore(), newRating.getScore());
            }
        }
    }

    /**
     * Testing Rating delete() method
     */
    @Test
    public void test_update() {
        // creating new User object
        ratingDAO.save(getRatingObject());

        // asserting
        List<Rating> before = ratingDAO.findAllByParam("score", getRatingObject().getScore());
        for (Rating foo : before) {
            if (foo.getScore().equals(getRatingObject().getScore())) {
                foo.setScore(33);
                ratingDAO.update(foo);

                // asserting
                Rating newRating = ratingDAO.find(foo.getRatingId());
                assertEquals("FAIL - score must be the same,", (Object) 33, newRating.getScore());
            }
        }
    }

    /**
     * Testing Rating delete() method
     */
    @Test
    public void test_findAll() {
        // creating few user objects
        int size = 5;
        for (int i = 0; i < size; i++) {
            ratingDAO.save(new Rating(null, timestamp, i, null));
        }

        // asserting
        List<Rating> list = ratingDAO.findAll();
        assertSame("FAIL - list must contain " + size + " objects", size, list.size());

        // clearing DB from test Objects
        for (Rating foo : list) {
            ratingDAO.delete(foo.getRatingId());
        }
    }


}
