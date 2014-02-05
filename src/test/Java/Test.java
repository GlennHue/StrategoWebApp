/*
* Software Development
* Karel de Grote-hogeschool
* 2013-2014
*/

import be.kdg.model.User;
import be.kdg.persistence.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import static junit.framework.Assert.assertEquals;


public class Test {

    @org.junit.Test
    public void test() {
        User user = new User("username", "password", "email");
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = session.beginTransaction();

        session.saveOrUpdate(user);
        tx.commit();

        session = HibernateUtil.getSessionFactory().getCurrentSession();
        tx = session.beginTransaction();

        Query query = session.createQuery("from be.kdg.model.User");
        User testUser = (User)query.uniqueResult();
        assertEquals("users should be the same", user, testUser);
    }
}
