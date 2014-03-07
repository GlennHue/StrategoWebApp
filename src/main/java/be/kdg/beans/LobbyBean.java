package be.kdg.beans;

import be.kdg.model.Game;
import be.kdg.model.Player;
import be.kdg.model.User;
import org.springframework.stereotype.Component;

import javax.faces.bean.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thomas on 25/02/14.
 */

@Component("lobbyBean")
@ApplicationScoped
public class LobbyBean {
    private List<User> users = new ArrayList<User>();
 
    /*
    private void findGame(User u1, User u2){
        Player p1 = new Player(u1.getUsername());
        Player p2 = new Player(u2.getUsername());
 
        Game game = new Game(p1,p2);
    }
    */

    public void addUser(User user){
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void removeUser(User user) {
        while(users.contains(user))
            users.remove(user);
    }

    public void checkGames() {
         if(users.size()>=2){
            User u1 = users.get(0);
            User u2 = users.get(1);

            Player p1 = new Player( u1.getUsername(), u1.getId());
            Player p2 = new Player( u2.getUsername(), u2.getId());
            Game game = new Game(p1,p2);

             removeUser(u1);
             removeUser(u2);
        }
    }
}