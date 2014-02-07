package be.kdg.service.api;


import be.kdg.model.User;

/**
 * Created by wouter on 6/02/14.
 */
public interface UserService {
    public User getUser(String username);
    public boolean userIsValid(String username,String password);
    public void addUser(String username,String password,String email);
    public void updatePassword(String username,String password);
    public void updateEmail(String username,String email);
    public void removeUser(User user);

}