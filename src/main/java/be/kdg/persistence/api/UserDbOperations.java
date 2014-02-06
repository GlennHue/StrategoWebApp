package be.kdg.persistence.api;

import be.kdg.model.User;

import java.util.List;

/**
 * Created by Glenn on 6/02/14.
 */
public interface UserDbOperations {

    public User getUserById(int id);

    public User getUserByEmail(String email);

    public void insertNewUser (User user);

    public List<User> getAllUsers();

    public void removeUser(User user);

    public void updateEmail(String email);

    public void updatePassword(String password);

    public boolean checkLogin(String username, String password);

    public User getUserByUsername(String username);

    public boolean userExists(String username);
}
