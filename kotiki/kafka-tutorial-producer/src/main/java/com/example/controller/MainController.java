package com.example.controller;

import com.example.model.Message;
import com.example.producer.ProducerService;
import com.example.services.MainService;
import com.example.wrapper.FriendWrap;
import com.example.wrapper.KotikWrap;
import com.example.wrapper.OwnerWrap;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MainController {
    @Autowired
    private MainService mainService;
    @Autowired
    private KafkaTemplate<String, List> kafkaOwnersTemplate;
    @Autowired
    private KafkaTemplate<String, String> kafkaOwnerTemplate;
    @Autowired
    private KafkaTemplate<String, OwnerWrap> kafkaOwnerWrapTemplate;
    @Autowired
    private KafkaTemplate<String, List> kafkaKotiksTemplate;
    @Autowired
    private KafkaTemplate<String, String> kafkaKotikTemplate;
    @Autowired
    private KafkaTemplate<String, KotikWrap> kafkaKotikWrapTemplate;
    @Autowired
    private KafkaTemplate<String, List> kafkaFriendsTemplate;
    @Autowired
    private KafkaTemplate<String, String> kafkaFriendTemplate;
    @Autowired
    private KafkaTemplate<String, FriendWrap> kafkaFriendWrapTemplate;

    @GetMapping("/error")
    public String error() {
        return "Error";
    }

    @GetMapping("/")
    public String hello() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Hello "+auth.getName();
    }

    @GetMapping("/user")
    public String user() {
        return "User";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

    @GetMapping("/admin/owner/owners")
    public ResponseEntity<List<OwnerWrap>> owners() throws InterruptedException {
        List<OwnerWrap> owners = new ArrayList<>();
        kafkaOwnersTemplate.send("owners", owners);
        Thread.sleep(1000);
        List<OwnerWrap> ownerWraps = mainService.ownersWrap;
        return ResponseEntity.ok().body(ownerWraps);
    }

    @GetMapping("/admin/owner/findByUsername")
    public ResponseEntity<OwnerWrap> findByUsername(@RequestParam String username) {
        kafkaOwnerTemplate.send("findOwnerByUsername", username);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OwnerWrap ownerWrap = mainService.ownerWrap;
        return ResponseEntity.ok().body(ownerWrap);
    }

    @GetMapping("/admin/kotik/kotiks")
    public ResponseEntity<List<KotikWrap>>getKotiks() throws InterruptedException {
        List<KotikWrap> kotiks = new ArrayList<>();
        kafkaKotiksTemplate.send("kotiks", kotiks);
        Thread.sleep(1000);
        List<KotikWrap> kotikWraps = mainService.kotiksWrap;
        return ResponseEntity.ok().body(kotikWraps);
    }

    @GetMapping("/admin/friend/friends")
    public ResponseEntity<List<FriendWrap>>getFriends() throws InterruptedException {
        List<FriendWrap> friends = new ArrayList<>();
        kafkaFriendsTemplate.send("friends", friends);
        Thread.sleep(1000);
        List<FriendWrap> friendWraps = mainService.friendsWrap;
        return ResponseEntity.ok().body(friendWraps);
    }

    @PostMapping("admin/owner/createOwner")
    public ResponseEntity<OwnerWrap> createOwner(@RequestParam String name, @RequestParam String date, @RequestParam String username, @RequestParam String password, @RequestParam String role) {
        List<Integer> kotiks = new ArrayList<>();
        OwnerWrap ownerWrap = new OwnerWrap(0, name, LocalDate.parse(date), username, password, role, kotiks);
        kafkaOwnerWrapTemplate.send("createOwner", ownerWrap);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OwnerWrap ownerWrap1 = mainService.ownerWrap;
        return ResponseEntity.ok().body(ownerWrap1);
    }

    @PostMapping("/admin/kotik/createkotik")
    public ResponseEntity<KotikWrap> createKotik(@RequestParam String name, @RequestParam String date, @RequestParam String breed, @RequestParam int color, @RequestParam int ownerId) throws Exception {
        KotikWrap kotikWrap = new KotikWrap(0, name, LocalDate.parse(date), breed, color, ownerId);
        kafkaKotikWrapTemplate.send("createKotik", kotikWrap);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        KotikWrap kotikWrap1 = mainService.kotikWrap;
        return ResponseEntity.ok().body(kotikWrap1);
    }

    @PostMapping("/admin/friend/createFriend")
    public ResponseEntity<FriendWrap>addFriend(@RequestParam int idKotik, @RequestParam int idFriend) {
        FriendWrap friendWrap = new FriendWrap(0,idKotik, idFriend);
        kafkaFriendWrapTemplate.send("createFriend", friendWrap);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FriendWrap friendWrap1 = mainService.friendWrap;
        return ResponseEntity.ok().body(friendWrap1);
    }

    @GetMapping("/user/kotik/kotiks")
    public ResponseEntity<List<KotikWrap>>getUserKotiks(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        kafkaOwnerTemplate.send("userKotiks", auth.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<KotikWrap> kotikWraps = mainService.kotiksWrap;
        return ResponseEntity.ok().body(kotikWraps);
    };

    @GetMapping("/admin/kotik/findByColor")
    public ResponseEntity<List<KotikWrap>>findKotikByColor(@RequestParam String color) {
        kafkaKotikTemplate.send("findKotikByColor", color);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<KotikWrap> kotikWraps = mainService.kotiksWrap;
        return ResponseEntity.ok().body(kotikWraps);
    }

    @PostMapping("/user/kotik/findByColor")
    public ResponseEntity<List<KotikWrap>>getUserKotiksByColor(@RequestParam String color){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        List<KotikWrap> result = new ArrayList<>();
        kafkaOwnerTemplate.send("findOwnerByUsername", auth.getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        OwnerWrap ownerWrap = mainService.ownerWrap;
        kafkaKotikTemplate.send("findKotikByColor", color);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<KotikWrap> kotikWraps1 = mainService.kotiksWrap;
        List<KotikWrap> kotikWraps2 = new ArrayList<>();
        kotikWraps2 = kotikWraps1;
        for(KotikWrap kotikWrap:kotikWraps2) {
            if(kotikWrap.getOwner_id() == ownerWrap.getId()) {
                result.add(kotikWrap);
            }
        }
        return ResponseEntity.ok().body(result);
    }
}
