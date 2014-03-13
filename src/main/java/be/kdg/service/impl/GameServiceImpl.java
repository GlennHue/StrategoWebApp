package be.kdg.service.impl;

import be.kdg.model.*;
import be.kdg.persistence.api.GameDAOApi;
import be.kdg.service.api.GameServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Glenn on 5-3-14.
 */
@Service("gameService")
public class GameServiceImpl implements GameServiceApi {
    @Autowired
    private GameDAOApi gameDao;

    @Override
    public void setStartPosition(int gameId, String pieces) {
        Game game = gameDao.getGame(gameId);
        char firstChar = pieces.charAt(0);
        if (firstChar == ('b')){
            putStartPiecesBlue(pieces, game);
        }
        else{
            putStartPiecesRed(pieces, game);
        }
    }

    private void putStartPiecesRed(String pieces, Game game) {
        String[] piecesArray = pieces.split(",");
        Tile[] tiles = game.getBoard().getTiles();
        int j = 0;
        for (int i = 39; i>=0;i--){
            tiles[i].setPiece(new Piece(Integer.parseInt(piecesArray[j].substring(1)), piecesArray[j].substring(0, 1)));
            j++;
        }
    }

    private void putStartPiecesBlue(String pieces, Game game) {
        String[] pieces2 = pieces.split(",");
        Tile[] tiles = game.getBoard().getTiles();
        for (int i = 60; i<100;i++){
            tiles[i].setPiece(new Piece(Integer.parseInt(pieces2[i-60].substring(1)), pieces2[i-60].substring(0, 1)));
        }
    }

    @Override
    public void movePiece(int gameId, int oldIndex, int newIndex) {
        //todo: insert in db
        Game game = gameDao.getGame(gameId);
        Tile[] tiles = game.getBoard().getTiles();
        tiles[oldIndex].setPiece(tiles[newIndex].getPiece());
        tiles[newIndex].setPiece(null);
    }



    @Override
    public String getPlayerColor(int playerId) {
        return gameDao.getPlayerColor(playerId);
    }

    @Override
    public boolean getReady(int gameId) {
        List<Player> players = gameDao.getPlayersByGameId(gameId);
        boolean result = true;
        for(Player player : players) {
            if(!player.getReady()) {
                result = false;
            }
        }
        return result;
    }

    @Override
    public void addStartPosition(int gameId, String pieces) {
        char firstChar = pieces.charAt(0);
        StartPosition sp = null;
        if (firstChar == ('b')){
            sp = new StartPosition("BLUE", pieces);
        }
        else{
            sp = new StartPosition("RED", pieces);
        }
        gameDao.addStartPosition(gameId, sp);
    }

    @Override
    public List<StartPosition> getStartingPositions(int gameId) {
        return gameDao.getStartingPositions(gameId);
    }

    @Override
    public int fight(String piecePlayerStr, String pieceEnemyStr) {
        Piece piecePlayer = new Piece(Integer.parseInt(piecePlayerStr.substring(1)),piecePlayerStr.substring(0,1));
        Piece pieceEnemy = new Piece(Integer.parseInt(pieceEnemyStr.substring(1)),pieceEnemyStr.substring(0,1));
        return piecePlayer.compareTo(pieceEnemy);
    }

    @Override
    public int fight(int gameId,int playerIndex, int enemyIndex){
        Piece piecePlayer = gameDao.getGame(gameId).getBoard().getTile(playerIndex).getPiece();
        Piece pieceEnemy = gameDao.getGame(gameId).getBoard().getTile(enemyIndex).getPiece();
        return piecePlayer.compareTo(pieceEnemy);
    }

    @Override
    public Game getGame(int gameId) {
        return gameDao.getGame(gameId);
    }

    @Override
    public void saveGame(Game game) {
        gameDao.saveGame(game);
    }

    @Override
    public void addMove(int gameId, int oldIndex, int newIndex) {
        Move move = new Move(oldIndex, newIndex);
        Game game = gameDao.getGame(gameId);
        move.setGame(game);
        move.setNumber(gameDao.getLatestMoveNr(gameId)+1);
        gameDao.addMove(move);
    }

    @Override
    public Game reconstructGame(int gameId) {
        Game game = gameDao.getGame(gameId);
        Board board = game.getBoard();
        for(StartPosition position : game.getStartPositions()) {
            setStartPosition(gameId, position.getPiece());
        }
        for(Move move : game.getMoves()) {
            int oldIndex = -1;
            int newIndex = -1;
            if(move.getNumber() % 2 == 0) {
                oldIndex = move.getOldIndex();
                newIndex = move.getNewIndex();
            } else {
                oldIndex = 99 - move.getOldIndex();
                newIndex = 99 - move.getNewIndex();
            }
            Piece defendingPiece = board.getTiles()[newIndex].getPiece();
            Piece attackingPiece = board.getTiles()[oldIndex].getPiece();
            if(defendingPiece != null) {
                if(attackingPiece.compareTo(defendingPiece) > 0) {
                    board.getTiles()[newIndex].setPiece(board.getTiles()[oldIndex].getPiece());
                    board.getTiles()[oldIndex].setPiece(null);
                } else if(attackingPiece.compareTo(defendingPiece) == 0) {
                    board.getTiles()[newIndex].setPiece(null);
                    board.getTiles()[oldIndex].setPiece(null);
                } else {
                    board.getTiles()[oldIndex].setPiece(null);
                }
            } else {
                board.getTiles()[newIndex].setPiece(board.getTiles()[oldIndex].getPiece());
                board.getTiles()[oldIndex].setPiece(null);
            }
        }
        return game;
    }

}
