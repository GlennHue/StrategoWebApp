/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.persistence.api;

import be.kdg.model.Piece;
import be.kdg.model.Player;

import java.util.List;

public interface PlayerDAOApi {

    public void insertNewPlayer(Player player);

    public List<Piece> getArmyByPlayerId(int playerId);

    public List<Piece> getGraveyardByPlayerId(int playerId);

    public boolean getReady(int playerId);

    public void setReady(int playerId);

    public void savePlayer(Player p1);

    public Player getPlayerById(int playerId);
}
