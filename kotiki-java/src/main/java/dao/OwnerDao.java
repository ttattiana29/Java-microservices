package dao;

import entities.Kotik;
import entities.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class OwnerDao {

    public Owner findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Owner.class, id);
    }

    public void save(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(owner);
        tx1.commit();
        session.close();
    }

    public void update(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(owner);
        tx1.commit();
        session.close();
    }

    public void delete(Owner owner) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(owner);
        tx1.commit();
        session.close();
    }

    public Kotik findKotikById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Kotik.class, id);
    }

    public List<Owner> findAll() {
        List<Owner> owners = (List<Owner>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Owner").list();
        return owners;
    }
}
