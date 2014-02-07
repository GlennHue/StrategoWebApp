package be.kdg.beans;


import be.kdg.model.User;
import be.kdg.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by wouter on 6/02/14.
 */
@Component
@Scope("session")
public class UserBean {
    private String username;
    private String password;
    private User user;
    @Autowired
    private UserService userService;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String checkCredentials()
    {
        if(userService.userIsValid(username,password))
        {
            return "succes";
        }
        return null;
    }
}