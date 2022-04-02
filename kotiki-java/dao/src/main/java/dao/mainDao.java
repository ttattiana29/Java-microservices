package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;

public abstract class mainDao<T> {
    public void save(T owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(owner);
        tx1.commit();
        session.close();
    }

    public void update(T owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(owner);
        tx1.commit();
        session.close();
    }

    public void delete(T owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(owner);
        tx1.commit();
        session.close();
    }
}
