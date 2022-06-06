package com.example.services;

import com.example.entities.Color;
import com.example.entities.Friend;
import com.example.entities.Kotik;
import com.example.entities.Owner;
import com.example.wrapper.FriendWrap;
import com.example.wrapper.KotikWrap;
import com.example.wrapper.OwnerWrap;

import java.time.LocalDate;
import java.util.List;

public interface MainService {
    void createOwner(OwnerWrap ownerWrap);
    Owner findOwnerById(int id);
    Owner findOwnerByUsername(String username);
    Owner saveOwner(Owner owner);
//    void deleteOwner(Owner owner);
    void findAllOwners(List<OwnerWrap> ownerWraps);

    void consProduceOwners(List<OwnerWrap> ownerWraps);
    void consProduceOwner(OwnerWrap ownerWrap);
    void consProduceKotiks(List<KotikWrap> kotikWraps);
    void consProduceKotik(KotikWrap kotikWrap);
    void consProduceFriends(List<FriendWrap> friendWraps);
    void consProduceFriend(FriendWrap friendWrap);

    Color getColor(int color) throws Exception;
    void createKotik(KotikWrap kotikWrap) throws Exception;
    Kotik findKotikById(int id);
    Kotik saveKotik(Kotik kotik);
//    void deleteKotik(Kotik kotik);
    void findAllKotiks(List<KotikWrap> kotikWraps);
    List<Kotik> findKotikByColor(String color);
//
//    Friend findFriendById(int id);
    Friend saveFriend(Friend friend);
//    void deleteFriend(Friend friend);
    void findAllFriends(List<FriendWrap> friendWraps);
//    void addOwnerToKotik(int idOwner, int idKotik);
    void addFriends(FriendWrap friendWrap);
    void getUserKotiks(String username);
}