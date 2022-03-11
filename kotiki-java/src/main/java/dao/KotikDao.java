package dao;

import entities.Kotik;
import entities.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class KotikDao {

    public Kotik findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Kotik.class, id);
    }

    public void save(Kotik kotik) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(kotik);
        tx1.commit();
        session.close();
    }

    public void update(Kotik kotik) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(kotik);
        tx1.commit();
        session.close();
    }

    public void delete(Kotik kotik) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(kotik);
        tx1.commit();
        session.close();
    }

    public Owner findOwnerById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Owner.class, id);
    }

    public List<Kotik> findAll() {
        List<Kotik> kotiki = (List<Kotik>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Kotik").list();
        return kotiki;
    }
}
