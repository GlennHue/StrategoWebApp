package be.kdg.persistence.api;

import be.kdg.model.Piece;
import be.kdg.model.Player;
import be.kdg.model.User;

import java.util.List;

/**
 * Created by Glenn on 5-3-14.
 */
public interface PlayerDAOApi {

    public void insertNewPlayer(Player player);

    public List<Piece> getArmyByPlayerId(int playerId);

    public List<Piece> getGraveyardByPlayerId(int playerId);

    public boolean getReady(int playerId);

}