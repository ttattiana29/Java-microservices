package ru.itmo.kotikilab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itmo.kotikilab.entities.Friend;
import ru.itmo.kotikilab.entities.Kotik;
import ru.itmo.kotikilab.entities.Owner;
import ru.itmo.kotikilab.services.MainService;
import ru.itmo.kotikilab.wrapper.FriendWrap;
import ru.itmo.kotikilab.wrapper.KotikWrap;
import ru.itmo.kotikilab.wrapper.OwnerWrap;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final MainService kotikiService;

    @GetMapping("/")
    public String hello() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Owner owner = kotikiService.findOwnerByUsername(auth.getName());
        return "Hello " + owner.getName();
    }

    @GetMapping("/error")
    public String error() {
        return "Error";
    }

    @GetMapping("/user")
    public String user() {
        return "User";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }

    @GetMapping("/admin/owners")
    public ResponseEntity<List<OwnerWrap>>getOwners(){
        return new ResponseEntity<>(
                kotikiService.findAllOwners().stream().
                        map((owner -> owner.getOwnerWrap())).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    };

    @PostMapping("/admin/owner/createowner")
    public ResponseEntity<OwnerWrap>createOwner(@RequestParam String name, @RequestParam String date, @RequestParam String username, @RequestParam String password, @RequestParam String role) {
        kotikiService.createOwner(name, date, username, password, role);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/owner/save")
    public ResponseEntity<OwnerWrap>saveOwner(@RequestBody Owner owner){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/owner/save").toUriString());
        OwnerWrap ownerWrap = owner.getOwnerWrap();
        return ResponseEntity.created(uri).body(ownerWrap);
    }

    @GetMapping("/admin/kotiks")
    public ResponseEntity<List<KotikWrap>>getKotiks(){
        return new ResponseEntity<>(
                kotikiService.findAllKotiks().stream().
                        map((kotik -> kotik.getKotikWrap())).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    };

    @PostMapping("/admin/kotik/createkotik")
    public ResponseEntity<KotikWrap>createKotik(@RequestParam String name, @RequestParam String date, @RequestParam String breed, @RequestParam int color, @RequestParam int ownerId) throws Exception {
        kotikiService.createKotik(name, date, breed, color, ownerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/admin/kotik/save")
    public ResponseEntity<Kotik>saveKotik(@RequestBody Kotik kotik){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/kotik/save").toUriString());
        return ResponseEntity.created(uri).body(kotikiService.saveKotik(kotik));
    }

    @GetMapping("/admin/friends")
    public ResponseEntity<List<FriendWrap>>getFriends(){
        return new ResponseEntity<>(
                kotikiService.findAllFriends().stream().
                        map((friend -> friend.getFriendWrap())).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    };

    @PostMapping("/admin/friend/save")
    public ResponseEntity<Friend>saveFriend(@RequestBody Friend friend){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/friend/save").toUriString());
        return ResponseEntity.created(uri).body(kotikiService.saveFriend(friend));
    }

    @PostMapping("/admin/friend/addfriend")
    public ResponseEntity<FriendWrap>addFriend(@RequestParam int idKotik, @RequestParam int idFriend) {
        kotikiService.addFriends(idKotik, idFriend);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user/kotiks")
    public ResponseEntity<List<KotikWrap>>getUserKotiks(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Owner owner = kotikiService.findOwnerByUsername(auth.getName());
        List<KotikWrap> kotiks = kotikiService.findAllKotiks().stream().
                map((kotik -> kotik.getKotikWrap())).
                collect(Collectors.toList());
        List<KotikWrap> kotiksWrap = new ArrayList<>();
        for (KotikWrap kotik:kotiks) {
            if(kotik.getOwner_id() == owner.getId()) {
                kotiksWrap.add(kotik);
            }
        }
        return ResponseEntity.ok().body(kotiksWrap);

    };

    @PostMapping("/admin/kotik/findByColor")
    public ResponseEntity<List<KotikWrap>>findKotikByColor(@RequestParam String color) {
        List<Kotik> kotiki = kotikiService.findKotikByColor(color);
        return new ResponseEntity<>(
                kotiki.stream().
                        map((kotik -> kotik.getKotikWrap())).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }

    @PostMapping("/user/kotik/findByColor")
    public ResponseEntity<List<KotikWrap>>getUserKotiksByColor(@RequestParam String color){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Owner owner = kotikiService.findOwnerByUsername(auth.getName());
        List<KotikWrap> result  = new ArrayList<>();
        List<Kotik> kotiki = kotikiService.findKotikByColor(color);
        List<KotikWrap> kotiksWrap = new ArrayList<>();
        for (Kotik kotik:kotiki) {
            kotiksWrap.add(kotik.getKotikWrap());
        }
        for (KotikWrap kotik:kotiksWrap) {
            if(kotik.getOwner_id() == owner.getId()) {
                result.add(kotik);
            }
        }
        return ResponseEntity.ok().body(result);
    }
}
