/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

package be.kdg.persistence.impl;

import be.kdg.model.Piece;
import be.kdg.model.Player;
import be.kdg.persistence.HibernateUtil;
import be.kdg.persistence.api.PlayerDAOApi;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository("playerDAO")
public class PlayerDAOImpl implements PlayerDAOApi{

    private Session session;
    private Transaction tx;

    @Override
    public void insertNewPlayer(Player player) {
        openSessionAndTransaction();
        session.saveOrUpdate(player);
        closeAndCommit();
    }

    @Override
    public List<Piece> getArmyByPlayerId(int playerId) {
        openSessionAndTransaction();
        List<Piece> result;
        String queryString = "from Player p where p.id = :id";
        Query query = session.createQuery(queryString).setInteger("id", playerId);
        result = ((Player)query.uniqueResult()).getArmy();
        close();
        return result;
    }

    @Override
    public List<Piece> getGraveyardByPlayerId(int playerId) {
        openSessionAndTransaction();
        List<Piece> result;
        String queryString = "from Player p where p.id = :id";
        Query query = session.createQuery(queryString).setInteger("id", playerId);
        result = ((Player)query.uniqueResult()).getGraveyard();
        close();
        return result;
    }

    @Override
    public boolean getReady(int playerId) {
        openSessionAndTransaction();
        String queryString = "from Player p where p.id = :id";
        Query query = session.createQuery(queryString).setInteger("id", playerId);
        boolean result = ((Player)query.uniqueResult()).getReady();
        close();
        return result;
    }


    @Override
    public void setReady(int playerId) {
        openSessionAndTransaction();
        Player player = getPlayerOpenSession(playerId);
        player.setReady(true);
        session.saveOrUpdate(player);
        closeAndCommit();
    }

    @Override
    public void savePlayer(Player p1) {
        openSessionAndTransaction();
        session.saveOrUpdate(p1);
        closeAndCommit();
    }

    @Override
    public Player getPlayerById(int playerId) {
        openSessionAndTransaction();
        String queryString = "from Player p where p.id = :id";
        Query query = session.createQuery(queryString).setInteger("id", playerId);
        Player player = (Player) query.uniqueResult();
        close();
        return player;
    }

    private Player getPlayerOpenSession(int playerId) {
        String queryString = "select p from Player p where p.id = :id";
        Query query = session.createQuery(queryString).setInteger("id", playerId);
        return (Player) query.uniqueResult();
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
