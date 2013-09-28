package knowledgeTest.logic.service.impl;

import knowledgeTest.logic.DAO.RatingDAO;
import knowledgeTest.logic.DAO.TaskDAO;
import knowledgeTest.logic.DAO.UserDAO;
import knowledgeTest.logic.service.AdminService;
import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class handel's ADMIN_ROLE functionality
 */
@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {

    protected static Logger logger = Logger.getLogger(AdminServiceImpl.class);

    private ShaPasswordEncoder passEncoder = new ShaPasswordEncoder(256);

    private UserDAO userDAO;

    private TaskDAO taskDAO;

    private RatingDAO ratingDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Autowired
    public void setTaskDAO(TaskDAO taskDAO) {
        this.taskDAO = taskDAO;
    }

    @Autowired
    public void setRatingDAO(RatingDAO ratingDAO) {
        this.ratingDAO = ratingDAO;
    }

    /**
     * Create user
     *
     * @param user - must contain userName and password to pass assertion
     */
    @Override
    public void createUser(User user) {

        logger.debug("initiating method createUser()");

        assert user.getUserName() != null || user.getPassword() != null :
                "Service Error: unable to save user, userName or password is missing!";
        userDAO.save(user);
    }

    /**
     * Update user
     *
     * @param user - object User must contain userName parameter to pass assertion
     * @throws RuntimeException
     */
    @Override
    public void updateUser(User user) {

        logger.debug("initiating method updateUser()");

        assert user.getUserName() != null : "Service Error: unable to update user, userName is missing!";

        List<User> list = userDAO.findAllByParam("userName", user.getUserName());
        if (list != null) {
            for (User foo : list) {
                userDAO.update(foo);
            }
        } else {
            throw new RuntimeException("Service Error: unable to update user, can't find user: "
                    + user.getUserName());
        }
    }

    /**
     * Delete user
     *
     * @param userId - determine user id
     * @throws RuntimeException
     */
    @Override
    public void deleteUser(Long userId) {
        logger.debug("initiating method deleteUser()");

        assert userId != null : "Service Error: unable to delete user, specified Id is empty!";

        List<User> list = userDAO.findAllByParam("userId", userId);
        if (list != null) {
            for (User foo : list) {
                userDAO.delete(foo.getUserId());
            }
        } else {
            throw new RuntimeException("Service Error: unable to delete user, " +
                    "can't find user with id: " + userId);
        }
    }

    /**
     * Get all users
     *
     * @return list of User objects
     * @throws RuntimeException
     */
    @Override
    public List<User> getAllUsers() {
        logger.debug("initiating method getAllUsers()");

        List<User> list = userDAO.findAll();

        if (list != null) {
            return list;
        } else {
            throw new RuntimeException("Service Error: unable to get all users, " +
                    "the Data Base is empty!");
        }
    }

    /**
     * Create task
     *
     * @param task - must contain all parameters to pass asserting
     * @throws RuntimeException
     */
    @Override
    public void createTask(Task task) {
        logger.debug("initiating method createTask()");

        List list = (List) task;
        for (Object aList : list) {
            if (aList == null) {
                throw new RuntimeException("Service Error: unable to initiate method createTask()," +
                        "one of parameters are empty");
            } else {
                taskDAO.save(task);
            }
        }
    }

    /**
     * Update task
     *
     * @param task - must contain id parameter to pass asserting
     * @throws RuntimeException
     */
    @Override
    public void updateTask(Task task) {
        logger.debug("initiating method updateTask()");

        assert task.getTaskId() != null : "Service Error: unable to initiate method updateTask()," +
                "task id is empty!";
        List<Task> list = taskDAO.findAllByParam("taskId", task.getTaskId());
        if (list != null) {
            for (Task foo : list) {
                taskDAO.update(foo);
            }
        } else {
            throw new RuntimeException("Service Error: unable to update task, can't find task " +
                    "with ID: " + task.getTaskId());
        }

    }

    /**
     * Delete task
     *
     * @param taskId - determine Task id
     * @throws RuntimeException
     */
    @Override
    public void deleteTask(Long taskId) {
        logger.debug("initiating method deleteTask()");

        assert taskId != null : "Service Error: unable to delete task, task id is empty!";

        List<Task> list = taskDAO.findAllByParam("taskId", taskId);
        if (list != null) {
            taskDAO.delete(taskId);
        } else {
            throw new RuntimeException("Service Error: unable to delete task, " +
                    "can't find task with id: " + taskId);
        }
    }

    /**
     * Get all tasks
     *
     * @return list of Task objects
     * @throws RuntimeException
     */
    @Override
    public List<Task> getAllTasks() {
        logger.debug("initiating method getAllTasks()");

        List<Task> list = taskDAO.findAll();

        if (list != null) {
            return list;
        } else {
            throw new RuntimeException("Service Error: unable to get all tasks, " +
                    "the Data Base is empty!");
        }
    }
}
