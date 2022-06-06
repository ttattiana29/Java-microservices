package com.example.services;

import com.example.entities.Color;
import com.example.entities.Friend;
import com.example.entities.Kotik;
import com.example.entities.Owner;
import com.example.repository.FriendRepository;
import com.example.repository.KotikRepository;
import com.example.repository.OwnerRepository;
import com.example.wrapper.FriendWrap;
import com.example.wrapper.KotikWrap;
import com.example.wrapper.OwnerWrap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MainServiceImpl implements MainService {
    private final KotikRepository kotikRepo;
    private final OwnerRepository ownerRepo;
    private final FriendRepository friendRepo;

    @Autowired
    private KafkaTemplate<String, List> kafkaOwnersTemplate;

    @Autowired
    private KafkaTemplate<String, OwnerWrap> kafkaOwnerTemplate;

    @Autowired
    private KafkaTemplate<String, List> kafkaKotiksTemplate;

    @Autowired
    private KafkaTemplate<String, KotikWrap> kafkaKotikTemplate;

    @Autowired
    private KafkaTemplate<String, List> kafkaFriendsTemplate;

    @Autowired
    private KafkaTemplate<String, FriendWrap> kafkaFriendTemplate;

    @Override
    @KafkaListener(topics = "createOwner", groupId = "owner_group_id")
    public void createOwner(OwnerWrap ownerWrap) {
        log.info("Create owner");
        Owner owner = new Owner(ownerWrap.getName(), ownerWrap.getBirthday(), ownerWrap.getUsername(),
                ownerWrap.getPassword(), ownerWrap.getRole());
        ownerRepo.save(owner);
        consProduceOwner(owner.getOwnerWrap());
    }

    @Override
    @KafkaListener(topics = "createKotik", groupId = "kotik_group_id")
    public void createKotik(KotikWrap kotikWrap) throws Exception {
        log.info("Create kotik");
        Color colorEnum = getColor(kotikWrap.getColorId());
        Owner owner = findOwnerById(kotikWrap.getOwner_id());
        Kotik kotik = new Kotik(kotikWrap.getName(), kotikWrap.getBirthday(), kotikWrap.getBreed(), colorEnum, owner);
        ownerRepo.save(owner);
        kotikRepo.save(kotik);
        consProduceKotik(kotik.getKotikWrap());
    }

    @Override
    public Owner findOwnerById(int id) {
        log.info("Finding owner by id");
        return ownerRepo.getById(id);
    }

    @Override
    @KafkaListener(topics = "findOwnerByUsername", groupId = "owner_group_id")
    public Owner findOwnerByUsername(String username) {
        log.info("Finding owner by username");
        Owner owner = ownerRepo.findByUsername(username);
        OwnerWrap ownerWrap = owner.getOwnerWrap();
        System.out.println("BLAAAAAAAAAAAAAAAAAAAAAAAAAAAAAT");
        consProduceOwner(ownerWrap);
        return owner;
    }

    @Override
    public Owner saveOwner(Owner owner) {
        log.info("Saving new owner");
        return ownerRepo.save(owner);
    }
//
//    @Override
//    public void deleteOwner(Owner owner) {
//        log.info("Delete owner");
//        ownerRepo.delete(owner);
//    }

    @Override
    @KafkaListener(topics = "owners", groupId = "owners_group_id")
    public void findAllOwners(List<OwnerWrap> ownerWraps) {
        log.info("Fetching all owners");
        ownerWraps = ownerRepo.findAll().stream().
                map((owner -> owner.getOwnerWrap())).
                collect(Collectors.toList());
        consProduceOwners(ownerWraps);
    }

    @Override
    public void consProduceOwners(List<OwnerWrap> ownerWraps) {
        kafkaOwnersTemplate.send("sendOwners", ownerWraps);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consProduceOwner(OwnerWrap ownerWrap) {
        kafkaOwnerTemplate.send("sendOwner", ownerWrap);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consProduceKotiks(List<KotikWrap> kotikWraps) {
        kafkaOwnersTemplate.send("sendKotiks", kotikWraps);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consProduceKotik(KotikWrap kotikWrap) {
        kafkaKotikTemplate.send("sendKotik", kotikWrap);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consProduceFriends(List<FriendWrap> friendWraps) {
        kafkaFriendsTemplate.send("sendFriends", friendWraps);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void consProduceFriend(FriendWrap friendWrap) {
        kafkaFriendTemplate.send("sendFriend", friendWrap);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Color getColor(int color) throws Exception{
        for (Color colors: Color.values()) {
            if (colors.ordinal() == color) {
                return colors;
            }
        }
        throw new Exception("Error");
    }

    @Override
    public Kotik findKotikById(int id) {
        log.info("Finding kotik by id");
        return kotikRepo.getById(id);
    }

    @Override
    public Kotik saveKotik(Kotik kotik) {
        log.info("Saving new kotik");
        return kotikRepo.save(kotik);
    }
//
//    @Override
//    public void deleteKotik(Kotik kotik) {
//        log.info("Delete kotik");
//        kotikRepo.delete(kotik);
//    }
//
    @Override
    @KafkaListener(topics = "kotiks", groupId = "kotiks_group_id")
    public void findAllKotiks(List<KotikWrap> kotikWraps) {
        log.info("Fetching all kotiks");
        kotikWraps = kotikRepo.findAll().stream().
                map((kotik -> kotik.getKotikWrap())).
                collect(Collectors.toList());
        consProduceKotiks(kotikWraps);
    }

    @Override
    @KafkaListener(topics = "findKotikByColor", groupId = "kotiks_group_id")
    public List<Kotik> findKotikByColor(String color) {
        log.info("Find kotik by color");
        Color colorEnum = Color.getTypeByName(color);
        List<Kotik> kotiki = kotikRepo.findByColor(colorEnum);
        consProduceKotiks(kotiki.stream().map(kotik -> kotik.getKotikWrap()).collect(Collectors.toList()));
        return kotiki;
    }

//    @Override
//    public Friend findFriendById(int id) {
//        log.info("Finding friend by id");
//        return friendRepo.getById(id);
//    }
//
    @Override
    public Friend saveFriend(Friend friend) {
        log.info("Saving new friend");
        return friendRepo.save(friend);
    }
//
//    @Override
//    public void deleteFriend(Friend friend) {
//        log.info("Delete friend");
//        friendRepo.delete(friend);
//    }
//
    @Override
    @KafkaListener(topics = "friends", groupId = "friends_group_id")
    public void findAllFriends(List<FriendWrap> friendWraps) {
        log.info("Fetching all friends");
        friendWraps = friendRepo.findAll().stream().
                map((friend -> friend.getFriendWrap())).
                collect(Collectors.toList());
        consProduceFriends(friendWraps);
    }
//
//    @Override
//    public void addOwnerToKotik(int idOwner, int idKotik) {
//        log.info("Adding owner to kotik");
//        Owner owner = findOwnerById(idOwner);
//        Kotik kotik = findKotikById(idKotik);
//        owner.addKotik(kotik);
//        kotik.setOwnerId(owner);
//        ownerRepo.save(owner);
//        kotikRepo.save(kotik);
//    }
//
    @Override
    @KafkaListener(topics = "createFriend", groupId = "friend_group_id")
    public void addFriends(FriendWrap friendWrap) {
        Kotik kotik = findKotikById(friendWrap.kotikId);
        Kotik kotikFriend = findKotikById(friendWrap.friendId);
        Friend newFriend = kotik.addFriend(kotikFriend);
        friendRepo.save(newFriend);
        consProduceFriend(newFriend.getFriendWrap());
    }

    @Override
    @KafkaListener(topics = "userKotiks", groupId = "kotiks_group_id")
    public void getUserKotiks(String username) {
        Owner owner = findOwnerByUsername(username);
        List<Kotik> kotiks = kotikRepo.findAll();
        List<Kotik> result = new ArrayList<>();
        for (Kotik kotik:kotiks) {
            if(kotik.getOwner().getId() == owner.getId()) {
                result.add(kotik);
            }
        }
        consProduceKotiks(result.stream().map(kotik -> kotik.getKotikWrap()).collect(Collectors.toList()));
    }
}
