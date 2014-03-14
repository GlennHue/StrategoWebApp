/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.service.impl;

import be.kdg.model.Achievement;
import be.kdg.model.Game;
import be.kdg.model.User;
import be.kdg.persistence.api.UserDAOApi;
import be.kdg.service.api.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserServiceApi {
    @Autowired
    private UserDAOApi userDao;

    @Override
    public User getUser(String username) {
        return userDao.getUserByUsername(username);
    }

    //TODO: checken of de user al ingelogd is en een foutmelding tonen indien dit zo is
    @Override
    public boolean userIsValid(String username, String password) {
        return userDao.checkLogin(username, password);
    }

    @Override
    public void addUser(String username, String password, String email) {
        userDao.insertNewUser(new User(username, password, email));
    }

    @Override
    public void updatePassword(String username, String password) {
        userDao.updatePassword(username, password);
    }

    @Override
    public void updateEmail(String username, String email) {
        userDao.updateEmail(username, email);
    }

    @Override
    public void removeUser(User user) {
        userDao.removeUser(user);
    }

    @Override
    public List<Achievement> getAchievementsByUsername(String username) {
        return userDao.getAchievementsByUsername(username);
    }

    @Override
    public void setAuthenticationCode(String username, String uuid) {
        userDao.setUserAuthenticationCode(username, uuid);
    }

    @Override
    public boolean uuidIsVerified(String uuid) {
        return userDao.uuidIsVerified(uuid);
    }

    @Override
    public List<User> getFriendsByUsername(String username) {
        return userDao.getFriendsByUsername(username);
    }

    @Override
    public void userLogout(String username) {
        userDao.userLogout(username);
    }

    @Override
    public User insertFriend(String username,String friendname) {
        return userDao.insertFriend(username,friendname);
    }

    @Override
    public Boolean userAndFriendAreFriends(String username, String friendname) {
        return userDao.userAndFriendAreFriends(username,friendname);
    }

    @Override
    public void addFbUser(String fn, String x, String x1) {
        userDao.addFbUser(new User(fn, x, x1));
    }

    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    public int getRank(User user) {
        return userDao.getRank(user);
    }

    @Override
    public int getMaxRank() {
        return userDao.getMaxRank();
    }

    @Override
    public List<Game> getGamesByUsername(String username) {
        return userDao.getGamesByUsername(username);
    }
}