package be.kdg.persistence.impl;

import be.kdg.model.User;
import be.kdg.persistence.HibernateUtil;
import be.kdg.persistence.api.UserDAOApi;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Glenn on 6/02/14.
 */
@Repository("userDAO")
public class UserDAOImpl implements UserDAOApi {

    private Session session;
    private Transaction tx;

    @Override
    public User getUserById(int id) {
        openSessionAndTransaction();
        String queryString = "from User u where u.id = :id";
        Query query = session.createQuery(queryString).setInteger("id", id);
        User user = (User) query.uniqueResult();
        close();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public void insertNewUser(User user) {
        openSessionAndTransaction();
        if(!userExists(user.getUsername())) {
            session.saveOrUpdate(user);
            closeAndCommit();
        }
    }

    @Override
    public List getAchievementsByUsername(String username) {
        openSessionAndTransaction();
        String queryString = "select a from User u join u.achievements a where u.username = :username";
        List achievements = session.createQuery(queryString).setString("username", username).list();
        session.close();
        return achievements;
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void removeUser(User user) {
        openSessionAndTransaction();
        session.delete(user);
        closeAndCommit();
    }

    @Override
    public void updateEmail(String username, String email) {

    }

    @Override
    public void updatePassword(String username, String password) {

    }

    @Override
    public boolean checkLogin(String username, String password) {
        User user = getUserByUsername(username);
        return user != null && user.getPassword().equals(password) && user.isVerified();
    }

    @Override
    public User getUserByUsername(String username) {
        openSessionAndTransaction();

        String querystring = "from User u where u.username = :username";
        Query query = session.createQuery(querystring).setString("username", username);
        User user = (User) query.uniqueResult();
        close();
        return user;
    }

    @Override
    public boolean userExists(String username) {
        openSessionAndTransaction();

        String queryString = "from User u where u.username = :username";
        Query query = session.createQuery(queryString).setString("username", username);
        User user = (User) query.uniqueResult();
        close();
        return user != null;
    }

    @Override
    public void setUserAuthenticationCode(String username,String uuid) {
        openSessionAndTransaction();
        Query query = session.createQuery("update User u set u.uuid = :uuid" +
                " where u.username = :username");
        query.setString("uuid",uuid);
        query.setString("username",username);
        query.executeUpdate();
        closeAndCommit();
    }

    @Override
    public boolean uuidIsVerified(String uuid) {
        openSessionAndTransaction();

        Query query = session.createQuery("from User u where u.uuid = :uuid");
        query.setString("uuid",uuid);
        User user = (User)query.uniqueResult();
        if (user!=null)
        {
            user.setVerified(true);
            session.saveOrUpdate(user);
            closeAndCommit();
            return true;
        }
        return false;
    }

    @Override
    public void addUser(User user) {
        openSessionAndTransaction();
        session.saveOrUpdate(user);
        closeAndCommit();
    }

    private void openSessionAndTransaction(){
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void close() {
        session.close();
    }

    private void closeAndCommit() {
        tx.commit();
        session.close();
    }
}
