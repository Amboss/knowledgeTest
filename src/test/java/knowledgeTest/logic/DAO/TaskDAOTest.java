package knowledgeTest.logic.DAO;

import knowledgeTest.model.Task;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

/**
 * JUnit test for the {@link knowledgeTest.logic.DAO.impl.TaskDAOImpl} class.
 * Testing DAO query for User type object.
 * ApplicationContext will be loaded from "classpath:/application-context.xml"
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
@TransactionConfiguration(defaultRollback = true, transactionManager = "transactionManager")
@Transactional
public class TaskDAOTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private TaskDAO taskDAO;

    /*
     * Creating Task Object with params for current test
     * (taskId = null, question, answer1, answer2, answer3, answer4, correct)
     */
    private Task getTaskObject() {
        return new Task(null, "Question text blu blu blu", "answer1", "answer2", "answer3", "answer4", 1);
    }

    /**
     * Testing Task delete() method
     */
    @After
    public void test_delete() {

        // searching and deleting tested object
        Task task = new Task();
        task.setQuestion("Question text blu blu blu");
        List<Task> befor = taskDAO.findAllByParam("question", task.getQuestion());
        for (Task foo : befor) {
            if (foo.getQuestion().equals(getTaskObject().getQuestion())) {
                taskDAO.delete(foo.getTaskId());
            }
        }

        // aserting
        List<Task> after = taskDAO.findAllByParam("question", task.getQuestion());
        for (Task foo : after) {
            assertNull("FAIL - task must be deleted", foo);
        }
    }

    /**
     * Testing Task save() method
     */
    @Test
    public void test_save() {
        // creating new Task object
        taskDAO.save(getTaskObject());

        // asserting
        List<Task> list = taskDAO.findAllByParam("question", getTaskObject().getQuestion());
        for (Task foo : list) {
            if (foo.getQuestion().equals(getTaskObject().getQuestion())) {
                assertEquals("FAIL - task answer1 must be the same,", getTaskObject().getAnswer1(), foo.getAnswer1());
                assertEquals("FAIL - task answer2 must be the same,", getTaskObject().getAnswer2(), foo.getAnswer2());
                assertEquals("FAIL - task answer3 must be the same,", getTaskObject().getAnswer3(), foo.getAnswer3());
                assertEquals("FAIL - task answer4 must be the same,", getTaskObject().getAnswer4(), foo.getAnswer4());
                assertEquals("FAIL - task correct must be the same,", getTaskObject().getCorrect(), foo.getCorrect());
            }
        }
    }

    /**
     * Testing Task findAllByParam() method
     */
    @Test
    public void test_findAllByParam() {
        // creating new Task object
        taskDAO.save(getTaskObject());

        // asserting
        List<Task> list = taskDAO.findAllByParam("question", getTaskObject().getQuestion());
        assertNotNull("FAIL - list have to contain object", list);
    }

    /**
     * Testing Task find() method
     */
    @Test
    public void test_find() {
        // creating new Task object
        taskDAO.save(getTaskObject());

        List<Task> list = taskDAO.findAllByParam("question", getTaskObject().getQuestion());
        for (Task foo : list) {
            if (foo.getQuestion().equals(getTaskObject().getQuestion())) {

                // asserting
                Task newTask = taskDAO.find(foo.getTaskId());
                assertEquals("FAIL - task answer1 must be the same,",
                        getTaskObject().getAnswer1(), newTask.getAnswer1());
                assertEquals("FAIL - task answer2 must be the same,",
                        getTaskObject().getAnswer2(), newTask.getAnswer2());
                assertEquals("FAIL - task answer3 must be the same,",
                        getTaskObject().getAnswer3(), newTask.getAnswer3());
                assertEquals("FAIL - task answer4 must be the same,",
                        getTaskObject().getAnswer4(), newTask.getAnswer4());
                assertEquals("FAIL - task correct must be the same,",
                        getTaskObject().getCorrect(), newTask.getCorrect());
            }
        }
    }

    /**
     * Testing Task update() method
     */
    @Test
    public void test_update() {
        // creating new Task object
        taskDAO.save(getTaskObject());

        // updating Task object
        String newAnswer1 = "newAnswer1", newAnswer2 = "newAnswer2", newAnswer4 = "newAnswer4";
        int newCorrect = 3;
        List<Task> before = taskDAO.findAllByParam("question", getTaskObject().getQuestion());
        for (Task foo : before) {
            if (foo.getQuestion().equals(getTaskObject().getQuestion())) {
                foo.setAnswer1(newAnswer1);
                foo.setAnswer2(newAnswer2);
                foo.setAnswer4(newAnswer4);
                foo.setCorrect(newCorrect);
                taskDAO.update(foo);
            }
        }

        // asserting
        List<Task> after = taskDAO.findAllByParam("question", getTaskObject().getQuestion());
        for (Task foo : after) {
            if (foo.getQuestion().equals(getTaskObject().getQuestion())) {

                // asserting
                Task newTask = taskDAO.find(foo.getTaskId());
                assertEquals("FAIL - task answer1 must be the same,", newAnswer1, newTask.getAnswer1());
                assertEquals("FAIL - task answer2 must be the same,", newAnswer2, newTask.getAnswer2());
                assertEquals("FAIL - task answer4 must be the same,", newAnswer4, newTask.getAnswer4());
                assertEquals("FAIL - task correct must be the same,", (Object) newCorrect, newTask.getCorrect());
            }
        }
    }

    /**
     * Testing Task findAll() method
     */
    @Test
    public void test_findAll() {
        // creating few Task objects
        int size = 20;
        for (int i = 0; i < size; i++) {
            taskDAO.save( new Task(null, "Question text blu blu blu" + i, "answer1" + i, "answer2" + i,
                    "answer3" + i, "answer4" + i, i));
        }

        // asserting
        List<Task> list = taskDAO.findAll();
        assertSame("FAIL - list must contain " + size + " objects", size, list.size());

        // clearing DB
        for (Task foo : list) {
            taskDAO.delete(foo.getTaskId());
        }
    }
}
