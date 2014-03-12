package be.kdg.service.api;

import be.kdg.model.Game;
import be.kdg.model.StartPosition;

import java.util.List;

/**
 * Created by Glenn on 5-3-14.
 */
public interface GameServiceApi {

    public void setStartPosition(int gameId, String pieces);

    public void movePiece(int gameId, int x, int y);

    public String getPlayerColor(int playerId);

    public boolean getReady(int gameId);

    public void addStartPosition (int gameId, String pieces);

    public List<StartPosition> getStartingPositions(int gameId);

    public int fight(String piecePlayer, String pieceEnemy);

    public void addMove(int gameId, int oldIndex, int newIndex);

    public Game reconstructGame(int gameId);
    
    public Game getGame(int gameId);

    public void saveGame(Game game);
}
