package be.kdg.persistence.impl;

import be.kdg.model.Achievement;
import be.kdg.persistence.HibernateUtil;
import be.kdg.persistence.api.AchievementDAOApi;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Glenn on 19/02/14.
 */
@Repository("achievementDao")
public class AchievementDAOImpl implements AchievementDAOApi {
    Session session = null;
    Transaction tx = null;

    @Override
    public void addAchievement(Achievement achievement) {
        openSessionAndTransaction();
        session.saveOrUpdate(achievement);
        commitAndClose();
    }

    @Override
    public List getAllAchievements() {
        openSessionAndTransaction();
        String queryString = "from Achievement a";
        Query query = session.createQuery(queryString);
        List result = query.list();
        session.close();
        return result;
    }

    @Override
    public Achievement getAchievementByTitle(String title) {
        openSessionAndTransaction();
        String queryString = "from Achievement a where a.title = :title";
        Achievement a = (Achievement) session.createQuery(queryString).setString("title", title).uniqueResult();
        session.close();
        return a;
    }

    @Override
    public Achievement getAchievementById(int id) {
        openSessionAndTransaction();
        String queryString = "from Achievement a where a.id = :id";
        Achievement a = (Achievement) session.createQuery(queryString).setInteger("id", id).uniqueResult();
        session.close();
        return a;
    }


    private void openSessionAndTransaction() {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void commitAndClose() {
        tx.commit();
        session.close();
    }

    private void close() {
        session.close();
    }
}
