/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.service.api;


import be.kdg.model.Achievement;
import be.kdg.model.Game;
import be.kdg.model.User;

import java.util.List;

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

    public void userLogout(String username);

    public User insertFriend(String username,String friendname);

    public Boolean userAndFriendAreFriends(String username, String friendname);

    public void addFbUser(String fn, String x, String x1);

    public void updateUser(User user);

    public int getRank(User user);

    public int getMaxRank();

    public List<Game> getGamesByUsername(String username);
}