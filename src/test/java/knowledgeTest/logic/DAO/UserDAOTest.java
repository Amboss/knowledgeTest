package knowledgeTest.logic.DAO;

import knowledgeTest.model.User;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit test for the {@link knowledgeTest.logic.DAO.impl.UserDAOImpl} class.
 * Testing DAO query for User type object.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class UserDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private UserDAO userDAO;

    private static ShaPasswordEncoder passwordEncoder;

    /*
     * Creating User Object with params for current test
     * (id = null, pass = testPass, access = 0, rating = null)
     */
    private User getUserObject() {
        return new User(null, "TestUser", passwordEncoder.encodePassword("testPass", null), 0, null);
    }

    @BeforeClass
    public static void initiate() {
        passwordEncoder = new ShaPasswordEncoder(256);
    }

    /**
     * Testing User delete() method
     */
    @After
    public void test_delete() {

        //searching and deleting tested object
        User user = new User("TestUser");
        List<User> before = userDAO.findAllByParam("userName", user.getUserName());
        for (User foo : before) {
            if (foo.getUserName().equals(user.getUserName())) {
                userDAO.delete(foo.getUserId());
            }
        }

        // asserting
        List<User> after = userDAO.findAllByParam("userName", user.getUserName());
        for (User foo : after) {
            assertNull("FAIL - user must be deleted", foo);
        }
    }

    /**
     * Testing User delete() method
     */
    @Test
    public void test_save() {
        // creating new User object
        userDAO.save(getUserObject());

        // asserting
        List<User> list = userDAO.findAllByParam("userName", getUserObject().getUserName());
        for (User foo : list) {
            if (foo.getUserName().equals(getUserObject().getUserName())) {
                assertEquals("FAIL - password must be the same,", getUserObject().getPassword(), foo.getPassword());
                assertEquals("FAIL - access must be the same,", getUserObject().getAccess(), foo.getAccess());
            }
        }
    }

    /**
     * Testing User findAllByParam() by id method
     */
    @Test
    public void test_findAllByParam() {
        // creating new User object
        userDAO.save(getUserObject());

        // asserting
        List<User> list = userDAO.findAllByParam("password", getUserObject().getPassword());
        assertNotNull("FAIL - list have to contain object", list);
    }

    /**
     * Testing User find() by id method
     */
    @Test
    public void test_find() {
        // creating new User object
        userDAO.save(getUserObject());

        List<User> list = userDAO.findAllByParam("userName", getUserObject().getUserName());
        for (User foo : list) {
            if (foo.getUserName().equals(getUserObject().getUserName())) {

                // asserting
                User newUser = userDAO.find(foo.getUserId());
                assertEquals("FAIL - user name must be the same,",
                        getUserObject().getUserName(), newUser.getUserName());
                assertEquals("FAIL - password must be the same,",
                        getUserObject().getPassword(), newUser.getPassword());
                assertEquals("FAIL - access must be the same,",
                        getUserObject().getAccess(), newUser.getAccess());
            }
        }
    }

    /**
     * Testing User update() method
     */
    @Test
    public void test_update() {
        // creating new User object
        userDAO.save(getUserObject());

        // updating User object
        String newPass = passwordEncoder.encodePassword("newTestPass", null);
        List<User> before = userDAO.findAllByParam("userName", getUserObject().getUserName());
        for (User foo : before) {
            if (foo.getUserName().equals(getUserObject().getUserName())) {
                foo.setPassword(newPass);
                foo.setAccess(1);
                userDAO.update(foo);
            }
        }

        // asserting
        List<User> after = userDAO.findAllByParam("userName", getUserObject().getUserName());
        for (User foo : after) {
            assertEquals("FAIL - user name must be the same,", getUserObject().getUserName(), foo.getUserName());
            assertEquals("FAIL - password must be the same,", newPass, foo.getPassword());
            assertEquals("FAIL - access must be the same,", (Object) 1, foo.getAccess());
        }
    }

    /**
     * Testing User findAll() method
     */
    @Test
    public void test_findAll() {
        // creating few user objects
        int size = 5;
        for (int i = 0; i < size; i++) {
            userDAO.save(new User(null, "TestUser" + i,
                    passwordEncoder.encodePassword("testPass" + i, null), 0, null));
        }

        // asserting
        List<User> list = userDAO.findAll();
        assertSame("FAIL - list must contain " + size + " objects", size, list.size());

        // clearing DB from test Objects
        for (User foo : list) {
            userDAO.delete(foo.getUserId());
        }
    }
}
