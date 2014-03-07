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

/**
 * Created by Glenn on 5-3-14.
 */
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
