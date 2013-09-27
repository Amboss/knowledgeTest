package knowledgeTest.logic.DAO;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JUnit test for the {@link knowledgeTest.logic.DAO.impl.UserDAOImpl} class.
 * Testing DAO query for User type object.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class UserDAOTest extends AbstractJUnit4SpringContextTests {

    private UserDAO userDAO;

    private static ShaPasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @BeforeClass
    public static void initiate() {
        passwordEncoder = new ShaPasswordEncoder(256);
    }

    /**
     * termination of User from Data Base
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
