package be.kdg.beans;


import be.kdg.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Created by wouter on 6/02/14.
 */
@Component("registerBean")
@Scope("request")
public class RegisterBean implements Serializable {
    private String username;
    private String email;
    private String password;
    private String confirmPassword;
    @Autowired
    private UserService userService;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void addNewUser(){
        if(password.equals(confirmPassword))
        {
            userService.addUser(username,password,email);
        }
    }
}