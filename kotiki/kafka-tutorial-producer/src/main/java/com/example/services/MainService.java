package com.example.services;

import com.example.model.Message;
import com.example.wrapper.FriendWrap;
import com.example.wrapper.KotikWrap;
import com.example.wrapper.OwnerWrap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Media;
import java.util.List;

@Service
@Slf4j
public class MainService {
    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    public List<OwnerWrap> ownersWrap;
    public OwnerWrap ownerWrap;
    public List<KotikWrap> kotiksWrap;
    public KotikWrap kotikWrap;
    public List<FriendWrap> friendsWrap;
    public FriendWrap friendWrap;

    @KafkaListener(topics = "sendOwners", groupId = "owners_group_id")
    public List getUsers(List<OwnerWrap> ownersWrap) {
        this.ownersWrap = ownersWrap;
        System.out.println(ownersWrap);
        return this.ownersWrap;
    }

    @KafkaListener(topics = "sendOwner", groupId = "owner_group_id")
    public OwnerWrap getOwner(OwnerWrap ownerWrap) {
        this.ownerWrap = ownerWrap;
        System.out.println(ownerWrap);
        return this.ownerWrap;
    }

    @KafkaListener(topics = "sendKotiks", groupId = "kotiks_group_id")
    public List getKotiks(List<KotikWrap> kotiksWrap) {
        this.kotiksWrap = kotiksWrap;
        System.out.println(kotiksWrap);
        return this.kotiksWrap;
    }

    @KafkaListener(topics = "sendKotik", groupId = "kotik_group_id")
    public KotikWrap getKotik(KotikWrap kotikWrap) {
        this.kotikWrap = kotikWrap;
        System.out.println(kotikWrap);
        return this.kotikWrap;
    }

    @KafkaListener(topics = "sendFriends", groupId = "friends_group_id")
    public List getFriends(List<FriendWrap> friendsWrap) {
        this.friendsWrap = friendsWrap;
        System.out.println(friendsWrap);
        return this.kotiksWrap;
    }

    @KafkaListener(topics = "sendFriend", groupId = "friend_group_id")
    public FriendWrap getFriend(FriendWrap friendWrap) {
        this.friendWrap = friendWrap;
        System.out.println(ownerWrap);
        return this.friendWrap;
    }
}
