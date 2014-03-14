/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.persistence.api;

import be.kdg.model.Game;
import be.kdg.model.Move;
import be.kdg.model.Player;
import be.kdg.model.StartPosition;

import java.util.List;

public interface GameDAOApi {

    public Game getGame(int gameId);

    public String getPlayerColor(int playerId);

    public List<Player> getPlayersByGameId(int gameId);

    public void addStartPosition(int gameId, StartPosition sp);

    public List<StartPosition> getStartingPositions(int gameId);

    public void saveGame(Game game);

    public void addMove(Move move);

    public int getLatestMoveNr(int gameId);

    public Move getLastMove(int gameId);

    public void refresh();
}
