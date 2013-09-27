package knowledgeTest.logic.DAO.impl;

import knowledgeTest.logic.DAO.RatingDAO;
import knowledgeTest.logic.DAO.support.CustomHibernateDaoSupport;
import knowledgeTest.model.Rating;
import org.springframework.stereotype.Repository;

/**
 * Class handel's Rating type Data Access Object functionality
 */
@Repository("RatingDAOImpl")
public class RatingDAOImpl extends CustomHibernateDaoSupport<Rating> implements RatingDAO {

    public RatingDAOImpl() {
        super(Rating.class);
    }
}
