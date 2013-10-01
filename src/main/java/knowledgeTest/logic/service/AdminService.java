package knowledgeTest.logic.service;

import knowledgeTest.model.Task;
import knowledgeTest.model.User;
import org.springframework.security.access.annotation.Secured;

import java.util.List;

/**
 * Interface describes ADMIN_ROLE functionality
 */
@Secured("ROLE_ADMIN")
public interface AdminService {

    /**
     * Create user
     */
    public void createUser(User user);

    /**
     * Update user
     */
    public void updateUser(User user);

    /**
     *  Delete user
     */
    public void deleteUser(Long userId);

    /**
     * Get all users
     * @return list of User objects
     */
    public List<User> getAllUsers();

    /**
     * Create task
     */
    public void createTask(Task task);

    /**
     * Update task
     */
    public void updateTask(Task task);

    /**
     * Delete task
     */
    public void deleteTask(Long taskId);

    /**
     * Get all tasks
     * @return list of Task objects
     */
    public List<Task> getAllTasks();
}
