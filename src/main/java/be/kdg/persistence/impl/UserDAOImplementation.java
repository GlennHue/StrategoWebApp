package be.kdg.persistence.impl;

import be.kdg.model.User;
import be.kdg.persistence.HibernateUtil;
import be.kdg.persistence.api.UserDAOApi;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by Glenn on 6/02/14.
 */
public class UserDAOImplementation implements UserDAOApi {


    @Override
    public User getUserById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        String queryString = "from User u where u.id = :id";
        Query query = session.createQuery(queryString).setInteger("id", id);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        return null;
    }

    @Override
    public void insertNewUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        if(!userExists(user.getUsername())) {
            session.saveOrUpdate(user);
            tx.commit();
        }
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public void removeUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
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
        return user != null && user.getPassword().equals(password);
    }

    @Override
    public User getUserByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String querystring = "from User u where u.username = :username";
        Query query = session.createQuery(querystring).setString("username", username);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    @Override
    public boolean userExists(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        String queryString = "from User u where u.username = :username";
        Query query = session.createQuery(queryString).setString("username", username);
        User user = (User) query.uniqueResult();
        return user != null;
    }
}
