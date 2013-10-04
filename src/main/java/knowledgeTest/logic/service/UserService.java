package knowledgeTest.logic.service;

import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Interface describes USER_ROLE functionality
 */
@Service("AnonymousServiceImpl")
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
     * Update rating of the user
     */
    public void updateUserRating(Long userId, Integer score);

    /**
     * Create User for test
     */
    public void createUserForTest(String userName);
}
