package knowledgeTest.logic.DAO.impl;

import knowledgeTest.logic.DAO.TaskDAO;
import knowledgeTest.logic.DAO.support.CustomHibernateDaoSupport;
import knowledgeTest.model.Task;
import org.springframework.stereotype.Repository;

/**
 * Class handel's Task type Data Access Object functionality
 */
@Repository("TaskDAOImpl")
public class TaskDAOImpl extends CustomHibernateDaoSupport<Task> implements TaskDAO{

    public TaskDAOImpl() {
        super(Task.class);
    }
}
