package ru.itmo.kotikilab.services;

import ru.itmo.kotikilab.entities.*;

import java.time.LocalDate;
import java.util.List;

public interface MainService {
    void createOwner(String name, String date);
    Owner findOwnerById(int id);
    Owner saveOwner(Owner owner);
    void deleteOwner(Owner owner);
    List<Owner> findAllOwners();

    Color getColor(int color) throws Exception;
    Kotik createKotik(String name, String date, String breed, int color, int ownerId) throws Exception;
    Kotik findKotikById(int id);
    Kotik saveKotik(Kotik kotik);
    void deleteKotik(Kotik kotik);
    List<Kotik> findAllKotiks();
    List<Kotik> findKotikByColor(String color);

    Friend findFriendById(int id);
    Friend saveFriend(Friend friend);
    void deleteFriend(Friend friend);
    List<Friend> findAllFriends();

    void addOwnerToKotik(int idOwner, int idKotik);
    Friend addFriends(int idKotik, int idFriend);
}
