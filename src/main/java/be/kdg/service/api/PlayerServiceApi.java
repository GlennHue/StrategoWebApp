package be.kdg.service.api;

import be.kdg.model.Piece;
import be.kdg.model.Player;
import be.kdg.model.User;

import java.util.List;

/**
 * Created by Glenn on 5-3-14.
 */
public interface PlayerServiceApi {

    public Player createPlayer(User user);

    public void insertNewPlayer(Player player);

    public Player getPlayerById(int playerId);

    public List<Piece> getArmyByPlayerId(int playerId);

    public List<Piece> getGraveyardByPlayerId(int playerId);

    public void setReady(int playerId);

    public void savePlayer(Player p1);

    public Player getEnemy(int playerId);
}
