package ru.itmo.kotikilab.services;

import ru.itmo.kotikilab.entities.Friend;
import ru.itmo.kotikilab.entities.Kotik;
import ru.itmo.kotikilab.entities.Owner;

import java.util.List;

public interface MainService {
    Owner findOwnerById(int id);
    Owner saveOwner(Owner owner);
    void deleteOwner(Owner owner);
    List<Owner> findAllOwners();

    Kotik findKotikById(int id);
    Kotik saveKotik(Kotik kotik);
    void deleteKotik(Kotik kotik);
    List<Kotik> findAllKotiks();

    Friend findFriendById(int id);
    Friend saveFriend(Friend friend);
    void deleteFriend(Friend friend);
    List<Friend> findAllFriends();

    void addOwnerToKotik(int idOwner, int idKotik);
    Friend addFriends(int idKotik, int idFriend);
}
