package be.kdg.service.impl;

import be.kdg.model.Game;
import be.kdg.model.Piece;
import be.kdg.model.Player;
import be.kdg.model.User;
import be.kdg.persistence.api.PlayerDAOApi;
import be.kdg.service.api.GameServiceApi;
import be.kdg.service.api.PlayerServiceApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Glenn on 5-3-14.
 */
@Service("playerService")
public class PlayerServiceImpl implements PlayerServiceApi{

    @Autowired
    private PlayerDAOApi playerDao;

    @Autowired
    private GameServiceApi gameService;

    @Override
    public Player createPlayer(User user) {
        Player player = new Player(user);
        createArmy(player);
        return player;
    }

    @Override
    public void insertNewPlayer(Player player) {

    }

    @Override
    public Player getPlayerById(int playerId) {
        return playerDao.getPlayerById(playerId);
    }

    @Override
    public List<Piece> getArmyByPlayerId(int playerId) {
        return null;
    }

    @Override
    public List<Piece> getGraveyardByPlayerId(int playerId) {
        return null;
    }

    private void createArmy(Player player) {
        List<Piece> army = new ArrayList<Piece>();
        int[] countPieces = {1, 1, 8, 5, 4, 4, 4, 3, 2, 1, 1, 6};

        for (int i = 0; i < countPieces.length; i++) {
            createPiece(i, countPieces[i], army);
        }
        player.setArmy(army);
    }

    private void createPiece(int rank, int count, List<Piece> army) {
        for (int i = 0; i < count; i++) {
            Piece piece = new Piece(rank, "b");
            army.add(piece);
        }
    }


    @Override
    public void setReady(int playerId) {
        playerDao.setReady(playerId);
    }

    @Override
    public void savePlayer(Player p1) {
        playerDao.savePlayer(p1);
    }

    @Override
    public boolean getEnemyStatus(int playerId) {
        Player player = getPlayerById(playerId);
        Player enemy;
        Game game = gameService.getGame(player.getGame().getId());
        if (game.getPlayers().get(0).getId() == player.getId()) {
            enemy = game.getPlayers().get(1);
        } else enemy = game.getPlayers().get(0);

        return enemy.getReady();
    }

}
