package dao;

import entities.Kotik;
import entities.Owner;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class OwnerDao extends mainDao{
    public Owner findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Owner.class, id);
    }

    public Kotik findKotikById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Kotik.class, id);
    }

    public List<Owner> findAll() {
        List<Owner> owners = (List<Owner>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Owner").list();
        return owners;
    }
}
