package be.kdg.persistence.api;

import be.kdg.model.Game;
import be.kdg.model.Player;
import be.kdg.model.StartPosition;

import java.util.List;

/**
 * Created by Glenn on 5-3-14.
 */
public interface GameDAOApi {

    public Game getGame(int gameId);

    public String getPlayerColor(int playerId);

    public List<Player> getPlayersByGameId(int gameId);

    public void addStartPosition(int gameId, StartPosition sp);

    public List<StartPosition> getStartingPositions(int gameId);
}
