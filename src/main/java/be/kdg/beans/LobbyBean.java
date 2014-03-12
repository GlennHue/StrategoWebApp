package be.kdg.beans;

import be.kdg.model.Color;
import be.kdg.model.Game;
import be.kdg.model.Player;
import be.kdg.model.User;
import be.kdg.service.api.GameServiceApi;
import be.kdg.service.api.PlayerServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ApplicationScoped;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public List<User> getUsers() {
        return users;
    }

    public void removeUser(User user) {
        while(users.contains(user))
            users.remove(user);
    }

    public Player checkGames(User user) {
        if(users.size()>=2){
            User u1 = users.get(0);
            if(u1.equals(user)){
                u1 = users.get(1);
            }

            Game game = new Game();
            gameService.saveGame(game);
            be.kdg.model.Color[]colors = new be.kdg.model.Color[]{Color.BLUE,Color.RED};
            Random randomColor = new Random();
            Color color = colors[randomColor.nextInt(2)];
            Player p1 = new Player(user,game,color);
            Player p2;
            if (color == Color.BLUE) {
                p2 = new Player(u1,game,Color.RED);
            } else p2 = new Player(u1,game,Color.BLUE);

            playerService.savePlayer(p1);
            playerService.savePlayer(p2);
            removeUser(user);
            removeUser(u1);
            game.setPlayers(new ArrayList<Player>(Arrays.asList(p1, p2)));
            return p1;
        }
        return null;
    }
}