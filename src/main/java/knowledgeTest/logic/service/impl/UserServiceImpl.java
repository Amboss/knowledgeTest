package knowledgeTest.logic.service.impl;

import knowledgeTest.logic.service.UserService;
import knowledgeTest.model.Rating;
import knowledgeTest.model.Task;
import knowledgeTest.model.User;

import java.util.List;

/**
 * Class handel's USER_ROLE functionality
 */
public class UserServiceImpl implements UserService {

    /**
     * Find user by userName
     *
     * @return User Object
     */
    @Override
    public User findUserByUserName(String userName) {
        return null;
    }

    /**
     * Find user by id
     *
     * @return User Object
     */
    @Override
    public User findUserById(Long userId) {
        return null;
    }

    /**
     * Get list of tasks
     *
     * @param amount - describes how many tasks have to be in list
     * @return List of Task objects
     */
    @Override
    public List<Task> getRandomListOfTasks(Integer amount) {
        return null;
    }

    /**
     * Get task by id
     *
     * @return Task Object
     */
    @Override
    public Task findTaskById(Long taskId) {
        return null;
    }

    /**
     * Save rating of the user
     */
    @Override
    public void saveUserRating(Long userId) {

    }

    /**
     * Get rating of user by id
     *
     * @return Rating Object
     */
    @Override
    public Rating getUserRatingById(Long userId) {
        return null;
    }

    /**
     * Change rating date
     */
    @Override
    public void setUserRatingDate(Long userId) {

    }
}
