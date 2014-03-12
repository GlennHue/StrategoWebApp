package be.kdg.beans;


import be.kdg.model.User;
import be.kdg.service.api.UserServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by wouter on 6/02/14.
 */
@Component("userBean")
@Scope("session")
public class UserBean implements Serializable {
    private String username;
    private String password;
    private User user;
    @Autowired
    private UserServiceApi userService;
    @Inject
    private LobbyBean lobby;
    private List<User> users;
    private String fbUsername = "";

    @PostConstruct
    public void initLobby(){
        users=lobby.getUsers();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String checkCredentials()
    {
        if(userService.userIsValid(username,password))
        {
            user = userService.getUser(username);
            return "index.xhtml";
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void logout() {
        if(user != null){
            lobby.removeUser(user);
            this.user = null;
        }
    }

    public void findUsers(){

        FacesContext fc = FacesContext.getCurrentInstance();

        String un =  getUserParam(fc);
        User us = userService.getUser(un);
        if(!users.contains(us)){
            lobby.addUser(us);
            lobby.checkGames(us);
         }
    }

    public String getUserParam(FacesContext fc){
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        return params.get("action");
    }

    public String addTempUser(){
        String fn =   fbUsername;
        String uuid = UUID.randomUUID().toString();
        if (userService.getUser(fn) == null){
        userService.addFbUser(fn, uuid, "x");
        }
        user = userService.getUser(username);
        return null;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getFbUsername() {
        return fbUsername;
    }

    public void setFbUsername(String fbUsername) {
        this.fbUsername = fbUsername;
    }
}

