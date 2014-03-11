package be.kdg.beans;

import be.kdg.model.Game;
import be.kdg.model.Player;
import be.kdg.model.User;
import be.kdg.service.api.GameServiceApi;
import be.kdg.service.api.PlayerServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    GameServiceApi gameService;
    @Autowired
    PlayerServiceApi playerService;
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

    public int[] checkGames(User user) {
        if(users.size()>=2){
            User u1 = users.get(0);
            if(u1.equals(user)){
                u1 = users.get(1);
            }

            Game game = new Game();
            int gameId = gameService.saveGame(game);
            Player p1 = new Player(user,game);
            Player p2 = new Player(u1,game);
            playerService.savePlayer(p1);
            playerService.savePlayer(p2);
            int playerId = p1.getId();
            removeUser(user);
            removeUser(u1);

            int[]idArray = new int[]{gameId,playerId};
            return idArray;
        }
        return null;
    }
}