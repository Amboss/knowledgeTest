package knowledgeTest.logic.DAO.impl;

import knowledgeTest.logic.DAO.UserDAO;
import knowledgeTest.logic.DAO.support.CustomHibernateDaoSupport;
import knowledgeTest.model.User;
import org.springframework.stereotype.Repository;

/**
 * Class handel's User type Data Access Object functionality
 */
@Repository("UserDAO")
public class UserDAOImpl extends CustomHibernateDaoSupport<User> implements UserDAO {

    public UserDAOImpl() {
        super(User.class);
    }
}
