package knowledgeTest.logic.DAO;

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

/**
 * JUnit test for the {@link knowledgeTest.logic.DAO.impl.RatingDAOImpl} class.
 * Testing DAO query for Rating type object.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class RatingDAOTest extends AbstractJUnit4SpringContextTests {

    private RatingDAO ratingDAO;

    private static Timestamp timestamp;

    @Autowired
    public void setRatingDAO(RatingDAO ratingDAO) {
        this.ratingDAO = ratingDAO;
    }

    @BeforeClass
    public static void initiate() {
        Date date = new Date();
        timestamp = new Timestamp(date.getTime());
    }

    /**
     * termination of Rating from Data Base
     */
    @After
    public void test_delete() {

    }

    @Test
    public void test_save() {

    }

    @Test
    public void test_update() {

    }

    @Test
    public void test_find() {

    }

    @Test
    public void test_findAll() {

    }

    @Test
    public void test_findAllByParam() {

    }
}
