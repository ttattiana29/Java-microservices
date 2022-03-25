package dao;

import entities.Friend;
import entities.Kotik;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class FriendDao extends mainDao{

    public Friend findById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Friend.class, id);
    }

    public Kotik findKotikById(int id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Kotik.class, id);
    }

    public List<Friend> findAll() {
        List<Friend> friends = (List<Friend>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Friend").list();
        return friends;
    }
}

