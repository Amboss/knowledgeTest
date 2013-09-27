package knowledgeTest.logic.DAO;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * JUnit test for the {@link knowledgeTest.logic.DAO.impl.TaskDAOImpl} class.
 * Testing DAO query for User type object.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TaskDAOTest extends AbstractJUnit4SpringContextTests {

    private TaskDAO taskDAO;

    @Autowired
    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    /**
     * termination of Task from Data Base
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
