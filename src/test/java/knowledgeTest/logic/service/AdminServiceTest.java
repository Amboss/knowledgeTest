package knowledgeTest.logic.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * JUnit test for the {@link knowledgeTest.logic.service.impl.AdminServiceImpl} class.
 * Testing service functions for ADMIN_ROLE.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
public class AdminServiceTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private AdminService adminService;

    /**
     * Testing createUser() method
     */
    @Test
    public void test_createUser() {

    }

    /**
     * Testing updateUser() method
     */
    @Test
    public void test_updateUser() {

    }

    /**
     * Testing deleteUser() method
     */
    @Test
    public void test_deleteUser() {

    }

    /**
     * Testing getAllUsers() method
     */
    @Test
    public void test_getAllUsers() {

    }

    /**
     * Testing createTask() method
     */
    @Test
    public void test_createTask() {

    }

    /**
     * Testing updateTask() method
     */
    @Test
    public void test_updateTask() {

    }

    /**
     * Testing deleteTask() method
     */
    @Test
    public void test_deleteTask() {

    }

    /**
     * Testing getAllTasks() method
     */
    @Test
    public void test_getAllTasks() {

    }
}
