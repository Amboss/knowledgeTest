package knowledgeTest.logic.service.impl;

import knowledgeTest.logic.DAO.RatingDAO;
import knowledgeTest.logic.DAO.TaskDAO;
import knowledgeTest.logic.DAO.UserDAO;
import knowledgeTest.logic.service.UserService;
import knowledgeTest.model.Rating;
import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import knowledgeTest.util.CustomUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

/**
 * Class handel's USER_ROLE functionality
 */
@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    protected static Logger logger = Logger.getLogger(UserServiceImpl.class);

    private Date date = new Date();

    private Timestamp timestamp = new Timestamp(date.getTime());

    private CustomUtil customUtil;

    private UserDAO userDAO;

    private TaskDAO taskDAO;

    private RatingDAO ratingDAO;

    @Autowired
    public void setCustomUtil(CustomUtil customUtil) {
        this.customUtil = customUtil;
    }

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
     * Find user by userName
     *
     * @return User Object
     * @throws RuntimeException
     */
    @Override
    public User findUserByUserName(String userName) {
        logger.debug("initiating method findUserByUserName()");

        assert userName != null : "Service Error: unable to initiate method findUserByUserName()," +
                " userName is empty!";

        List<User> list = userDAO.findAllByParam("userName", userName);
        if (list != null) {
            return list.get(0);
        } else {
            throw new RuntimeException("Service Error: unable to find user with userName: "
                    + userName);
        }
    }

    /**
     * Find user by id
     *
     * @return User Object
     * @throws RuntimeException
     */
    @Override
    public User findUserById(Long userId) {
        logger.debug("initiating method findUserById()");
        assert userId != null :
                "Service Error: unable to initiate method findUserById(), userId is empty!";

        List<User> list = userDAO.findAllByParam("userId", userId);
        if (list != null) {
            return list.get(0);
        } else {
            throw new RuntimeException("Service Error: unable to find user with userId: " + userId);
        }
    }

    /**
     * Get list of tasks
     *
     * @param amount - describes how many tasks have to be in list, can't be null or less then "1".
     * @return List of Task objects
     * @throws RuntimeException
     */
    @Override
    public List<Task> getRandomListOfTasks(Integer amount) {
        logger.debug("initiating method getRandomListOfTasks()");

        assert amount != null : "Service Error: unable to initiate method " +
                "getRandomListOfTasks(), amount of tasks not specified!";

        // retrieving all tasks fro, DB to list
        List<Task> existingList = taskDAO.findAll();

        // initiating target list for random tasks
        List<Task> targetList = new ArrayList<>();

        // creating list with specified amount of random numbers from existingList range
        ArrayList<Integer> randomNumberList =
                customUtil.getRandomNumbers(amount, existingList.size());

        // adding task to target list by specified index from existingList
        for (Integer foo : randomNumberList) {
            targetList.add(existingList.get(foo));
        }

        return targetList;
    }

    /**
     * Get task by id
     *
     * @return Task Object
     * @throws RuntimeException
     */
    @Override
    public Task findTaskById(Long taskId) {
        logger.debug("initiating method()");

        assert taskId != null : "Service Error: unable to initiate method " +
                "findTaskById(), task Id is empty!";

        Task task = taskDAO.find(taskId);
        if (task != null) {
            return task;
        } else {
            throw new RuntimeException("Service Error: unable to find task with taskId: " + taskId);
        }
    }

    /**
     * Save rating of the user
     *
     * @param userId   - id of target user for who rating must be created
     * @param score    - result of positive answers
     * @throws RuntimeException
     */
    @Override
    public void updateUserRating(Long userId, Integer score) {
        logger.debug("initiating method findTaskById()");

        assert userId != null : "Service Error: unable to initiate method updateUserRating(), " +
                "userId is empty!";
        assert score != null : "Service Error: unable to initiate method updateUserRating(), " +
                "score is empty!";

        User user = userDAO.find(userId);

        if (user != null) {

            if (user.getRating().getRatingId() != null) {
                // updating rating (id, date, score, taskList)
                Rating rating = ratingDAO.find(user.getRating().getRatingId());
                rating.setRatingDate(timestamp);
                rating.setScore(score);
                ratingDAO.update(rating);
            } else {

                // creating rating (id, date, score, taskList) and saving
                user.setRating(new Rating(null, timestamp, 0));
                userDAO.save(user);
            }
        } else {
            throw new RuntimeException("Service Error: unable to find user with userId: " + userId);
        }
    }
}
