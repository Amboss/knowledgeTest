package knowledgeTest.logic.service;

import knowledgeTest.model.Task;
import knowledgeTest.model.User;

import java.util.List;
import java.util.Set;

/**
 * Interface describes USER_ROLE functionality
 */
public interface UserService {

    /**
     * Find user by userName
     * @return User Object
     */
    public User findUserByUserName(String userName);

    /**
     * Find user by id
     * @return User Object
     */
    public User findUserById(Long userId);

    /**
     * Get list of tasks
     * @param amount - describes how many tasks have to be in list
     * @return List of Task objects
     */
    public List<Task> getRandomListOfTasks(Integer amount);

    /**
     * Get task by id
     * @return Task Object
     */
    public Task findTaskById(Long taskId);

    /**
     * Save rating of the user
     */
    public void saveUserRating(Long userId, Integer score, Set<Task> taskList);
}
