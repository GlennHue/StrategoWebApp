package be.kdg.service.api;


import be.kdg.model.Achievement;
import be.kdg.model.User;

import java.util.List;

/**
 * Created by wouter on 6/02/14.
 */
public interface UserServiceApi {
    public User getUser(String username);

    public boolean userIsValid(String username, String password);

    public void addUser(String username, String password, String email);

    public void updatePassword(String username, String password);

    public void updateEmail(String username, String email);

    public void removeUser(User user);

    public void setAuthenticationCode(String username, String uuid);

    public List<Achievement> getAchievementsByUsername(String username);

    boolean uuidIsVerified(String uuid);

    public List<User> getFriendsByUsername(String username);
}