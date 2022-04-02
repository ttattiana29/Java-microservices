package dao;

import entities.Kotik;
import entities.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class KotikDao extends mainDao{

    public Kotik findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Kotik.class, id);
    }

    public Owner findOwnerById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Owner.class, id);
    }

    public List<Kotik> findAll() {
        List<Kotik> kotiki = (List<Kotik>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Kotik").list();
        return kotiki;
    }
}
