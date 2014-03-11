package be.kdg.persistence.impl;

import be.kdg.model.Game;
import be.kdg.model.Player;
import be.kdg.model.StartPosition;
import be.kdg.persistence.HibernateUtil;
import be.kdg.persistence.api.GameDAOApi;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Glenn on 5-3-14.
 */
@Repository("gameDao")
public class GameDAOImpl implements GameDAOApi {

    private Session session;
    private Transaction tx;

    @Override
    public Game getGame(int gameId) {
        openSessionAndTransaction();
        Game game = getGameOpenSession(gameId);
        close();
        return game;
    }

    private Game getGameOpenSession(int gameId) {
        String queryString = "from Game g where g.id = :id";
        Query query = session.createQuery(queryString).setInteger("id", gameId);
        return (Game) query.uniqueResult();
    }

    @Override
    public String getPlayerColor(int playerId) {
        //todo: implement this
        return "blue";
    }

    @Override
    public List<Player> getPlayersByGameId(int gameId) {
        openSessionAndTransaction();
        Game game = getGameOpenSession(gameId);
        List<Player> result = game.getPlayers();
        close();
        return result;
    }

    @Override
    public void addStartPosition(int gameId, StartPosition sp) {
        openSessionAndTransaction();
        Game game = getGameOpenSession(gameId);
        sp.setGame(game);
        session.saveOrUpdate(sp);
        closeAndCommit();
    }

    @Override
    public List<StartPosition> getStartingPositions(int gameId) {
        openSessionAndTransaction();
        Game game = getGameOpenSession(gameId);
        List<StartPosition> result = game.getStartPositions();
        close();
        return result;
    }

    private void openSessionAndTransaction() {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();
    }

    private void close() {
        session.close();
    }

    private void closeAndCommit() {
        tx.commit();
        session.close();
    }
}
