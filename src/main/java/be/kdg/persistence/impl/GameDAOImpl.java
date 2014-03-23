/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.persistence.impl;

import be.kdg.model.*;
import be.kdg.persistence.HibernateUtil;
import be.kdg.persistence.api.GameDAOApi;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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
    public void refresh(){
        openSessionAndTransaction();
        String queryString = "from Achievement a where a.id = :id";
        Query query = session.createQuery(queryString).setInteger("id", 1);
        Achievement achievement = (Achievement) query.uniqueResult();
        close();
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

    @Override
    public void saveGame(Game game) {
        openSessionAndTransaction();
        session.saveOrUpdate(game);
        closeAndCommit();
    }

    @Override
    public void addMove(Move move) {
        openSessionAndTransaction();
        session.saveOrUpdate(move);
        closeAndCommit();
    }

    @Override
    public int getLatestMoveNr(int gameId) {
        openSessionAndTransaction();
        Game game = getGameOpenSession(gameId);
        int result = game.getMoves().size();
        close();
        return result;
    }

    @Override
    public Move getLastMove(int gameId) {
        openSessionAndTransaction();
        String queryString = "from Move m where m.game.id = :gameId order by m.id desc ";
        Query query = session.createQuery(queryString).setInteger("gameId", gameId).setMaxResults(1);
        Move move = (Move)query.uniqueResult();
        close();
        return move;
    }

    private void openSessionAndTransaction() {
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.getTransaction();
        tx.begin();
    }

    private void close() {
        if(session.isOpen()){
        session.close();}
    }

    private void closeAndCommit() {
        try{
            if (!tx.wasCommitted())
                tx.commit();
        }
        catch (Exception e){
            tx.rollback();
        }
        if(session.isOpen()){
            session.close();}
    }
}
