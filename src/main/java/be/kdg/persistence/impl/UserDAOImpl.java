/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.persistence.impl;

import be.kdg.model.Achievement;
import be.kdg.model.Game;
import be.kdg.model.Player;
import be.kdg.model.User;
import be.kdg.persistence.HibernateUtil;
import be.kdg.persistence.api.UserDAOApi;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        if (!userExists(user.getUsername())) {
            openSessionAndTransaction();
            session.saveOrUpdate(user);
            closeAndCommit();
        }
    }

    @Override
    public List getAchievementsByUsername(String username) {
        openSessionAndTransaction();
        String queryString = "select a from User u join u.achievements a where u.username = :username";
        List achievements = session.createQuery(queryString).setString("username", username).list();
        close();
        return achievements;
    }

    @Override
    public List<User> getFriendsByUsername(String username) {
        openSessionAndTransaction();
        String queryString = "select friends from User u join u.friends friends where u.username = :username";
        List<User> users = session.createQuery(queryString).setString("username", username).list();
        close();
        return users;
    }

    @Override
    public void userLogout(String username) {
        User user = getUserByUsername(username);
        openSessionAndTransaction();
        user.setStatus("offline");
        session.saveOrUpdate(user);
        closeAndCommit();

    }

    @Override
    //TODO property request ofzo die friendrequest bijhoudt
    public User insertFriend(String username,String friendname) {
        openSessionAndTransaction();
        User friend = getUserByUsernameOpenSession(friendname);
        User loggedInUser = getUserByUsernameOpenSession(username);

        if (friend != null) {
            loggedInUser.addFriend(friend);
            session.saveOrUpdate(loggedInUser);
            closeAndCommit();
        }
        close();
        return friend;
    }


    private User getUserByUsernameOpenSession(String username) {
        String querystring = "from User u where u.username = :username";
        Query query = session.createQuery(querystring).setString("username", username);
        User user = (User) query.uniqueResult();
        return user;
    }

    @Override
    public Boolean userAndFriendAreFriends(String username, String friendname) {
        openSessionAndTransaction();
        String queryString = "select friends from User u join u.friends friends where u.username = :username";
        List<User> userFriends = session.createQuery(queryString).setString("username", username).list();
        String queryStringFriend = "select friends from User u join u.friends friends where u.username = :username";
        List<User> friendFriends = session.createQuery(queryStringFriend).setString("username", friendname).list();
        User user = getUserByUsernameOpenSession(username);
        User friend = getUserByUsernameOpenSession(friendname);
        close();
        if (userFriends.contains(friend) && friendFriends.contains(user)) {
            return true;
        }
        return false;
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
        if (user != null && user.getPassword().equals(password) && user.isVerified()) {
            openSessionAndTransaction();
            user.setStatus("online");
            session.saveOrUpdate(user);
            closeAndCommit();
           return true;
        }
        return false;
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
    public void setUserAuthenticationCode(String username, String uuid) {
        openSessionAndTransaction();
        Query query = session.createQuery("update User u set u.uuid = :uuid" +
                " where u.username = :username");
        query.setString("uuid", uuid);
        query.setString("username", username);
        query.executeUpdate();
        closeAndCommit();
    }

    @Override
    public boolean uuidIsVerified(String uuid) {
        openSessionAndTransaction();

        Query query = session.createQuery("from User u where u.uuid = :uuid");
        query.setString("uuid", uuid);
        User user = (User) query.uniqueResult();
        if (user != null) {
            user.setVerified(true);
            session.saveOrUpdate(user);
            closeAndCommit();
            return true;
        }
        close();
        return false;
    }

    private void openSessionAndTransaction() {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
    }

    private void close() {
        session.close();
    }

    private void closeAndCommit() {
        try{
        if(!tx.wasCommitted())
            tx.commit();
        }
        catch (Exception ex){
            tx.rollback();
        }
        session.close();
    }


    @Override
    public void addFbUser(User user) {
        if (!userExists(user.getUsername())) {
            openSessionAndTransaction();
            user.setVerified(true);
            session.saveOrUpdate(user);
            closeAndCommit();
        }
    }

    @Override
    public void updateUser(User user) {
        openSessionAndTransaction();
        session.saveOrUpdate(user);
        closeAndCommit();
    }

    @Override
    public int getRank(User user) {
        openSessionAndTransaction();
        String queryString = "from User u order by u.score desc";
        Query query = session.createQuery(queryString);
        List<User> users = query.list();
        close();
        int rank = 0;
        for(User u : users) {
            rank++;
            if(u == user) {
                break;
            }
        }
        return rank;
    }

    @Override
    public int getMaxRank() {
        openSessionAndTransaction();
        int count = ((Long)session.createQuery("select count(u.score) from User u").uniqueResult()).intValue();
        close();
        return count;


    }

    @Override
    public List<Game> getGamesByUsername(String username) {
        openSessionAndTransaction();
        User user = getUserByUsernameOpenSession(username);
        List<Game> result = new ArrayList<Game>();

        for(Player player : user.getPlayers()) {
            result.add(player.getGame());
        }
        close();
        return result;
    }
}
