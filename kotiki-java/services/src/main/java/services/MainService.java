package services;

import dao.FriendDao;
import dao.KotikDao;
import entities.Friend;
import entities.Kotik;
import entities.Owner;
import dao.OwnerDao;

import java.util.List;

public class MainService {
    private OwnerDao ownersDao = new OwnerDao();
    private KotikDao kotikiDao = new KotikDao();
    private FriendDao friendsDao = new FriendDao();

    public Owner findOwner(int id) {
        return ownersDao.findById(id);
    }

    public List<Owner> findAllOwners() {
        return ownersDao.findAll();
    }

    public void saveOwner(Owner owner) {
        ownersDao.save(owner);
    }

    public void deleteOwner(Owner owner) {
        ownersDao.delete(owner);
    }

    public void updateOwner(Owner owner) {
        ownersDao.update(owner);
    }

    public Kotik findKotikById(int id) {
        return ownersDao.findKotikById(id);
    }


    public Kotik findKotik(int id) {
        return kotikiDao.findById(id);
    }

    public void saveKotik(Kotik kotik) {
        kotikiDao.save(kotik);
    }

    public void deleteKotik(Kotik kotik) {
        kotikiDao.delete(kotik);
    }

    public void updateKotik(Kotik kotik) {
        kotikiDao.update(kotik);
    }

    public List<Kotik> findAllKOtiki() {
        return kotikiDao.findAll();
    }

    public Owner findOwnerById(int id) {
        return kotikiDao.findOwnerById(id);
    }


    public Friend findFriend(int id) {
        return friendsDao.findById(id);
    }

    public void saveFriend(Friend friend) {
        friendsDao.save(friend);
    }

    public void deleteFriend(Friend friend) {
        friendsDao.delete(friend);
    }

    public void updateFriend(Friend friend) {
        friendsDao.update(friend);
    }

    public List<Friend> findAllFriends() {
        return friendsDao.findAll();
    }

    public Kotik findKotikByIdInFriends(int id) {
        return friendsDao.findKotikById(id);
    }

}
