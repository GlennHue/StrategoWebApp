package be.kdg.service.impl;

import be.kdg.model.Achievement;
import be.kdg.model.User;
import be.kdg.persistence.api.UserDAOApi;
import be.kdg.service.api.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wouter on 6/02/14.
 */
@Service("userService")
public class UserServiceImpl implements UserServiceApi {
    @Autowired
    private UserDAOApi userDao;
    @Override
    public User getUser(String username) {
        return userDao.getUserByUsername(username);
    }

    @Override
    public boolean userIsValid(String username, String password) {
        return userDao.checkLogin(username, password);
    }

    @Override
    public void addUser(String username, String password, String email) {
        userDao.insertNewUser(new User(username,password,email));
    }

    @Override
    public void updatePassword(String username, String password) {
        userDao.updatePassword(username,password);
    }

    @Override
    public void updateEmail(String username, String email) {
        userDao.updateEmail(username,email);
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
    public void setAuthenticationCode(String username,String uuid) {
        userDao.setUserAuthenticationCode(username,uuid);
    }

    @Override
    public boolean uuidIsVerified(String uuid) {
        return userDao.uuidIsVerified(uuid);
    }

    @Override
    public List<User> getFriendsByUsername(String username) {
        return userDao.getFriendsByUsername(username);
    }

}