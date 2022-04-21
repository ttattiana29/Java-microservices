package ru.itmo.kotikilab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itmo.kotikilab.entities.Friend;
import ru.itmo.kotikilab.entities.Kotik;
import ru.itmo.kotikilab.entities.Owner;
import ru.itmo.kotikilab.services.MainService;
import ru.itmo.kotikilab.wrapper.FriendWrap;
import ru.itmo.kotikilab.wrapper.KotikWrap;
import ru.itmo.kotikilab.wrapper.OwnerWrap;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final MainService kotikiService;

    @GetMapping("/owners")
    public ResponseEntity<List<OwnerWrap>>getOwners(){
        return new ResponseEntity<>(
                kotikiService.findAllOwners().stream().
                        map((owner -> owner.getOwnerWrap())).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    };

    @PostMapping("/owner/createowner")
    public ResponseEntity<OwnerWrap>createOwner(@RequestParam String name, @RequestParam String date) {
        kotikiService.createOwner(name, date);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/owner/save")
    public ResponseEntity<OwnerWrap>saveOwner(@RequestBody Owner owner){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/owner/save").toUriString());
        OwnerWrap ownerWrap = owner.getOwnerWrap();
        return ResponseEntity.created(uri).body(ownerWrap);
    }

    @GetMapping("/kotiks")
    public ResponseEntity<List<KotikWrap>>getKotiks(){
        return new ResponseEntity<>(
                kotikiService.findAllKotiks().stream().
                        map((kotik -> kotik.getKotikWrap())).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    };

    @PostMapping("/kotik/createkotik")
    public ResponseEntity<KotikWrap>createKotik(@RequestParam String name, @RequestParam String date, @RequestParam String breed, @RequestParam int color, @RequestParam int ownerId) throws Exception {
        kotikiService.createKotik(name, date, breed, color, ownerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/kotik/save")
    public ResponseEntity<Kotik>saveKotik(@RequestBody Kotik kotik){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/kotik/save").toUriString());
        return ResponseEntity.created(uri).body(kotikiService.saveKotik(kotik));
    }

    @GetMapping("/friends")
    public ResponseEntity<List<FriendWrap>>getFriends(){
        return new ResponseEntity<>(
                kotikiService.findAllFriends().stream().
                        map((friend -> friend.getFriendWrap())).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    };

    @PostMapping("/friend/save")
    public ResponseEntity<Friend>saveFriend(@RequestBody Friend friend){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/friend/save").toUriString());
        return ResponseEntity.created(uri).body(kotikiService.saveFriend(friend));
    }

    @PostMapping("/friend/addfriend")
    public ResponseEntity<FriendWrap>addFriend(@RequestParam int idKotik, @RequestParam int idFriend) {
        kotikiService.addFriends(idKotik, idFriend);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/kotik/findByColor")
    public ResponseEntity<List<KotikWrap>>findKotikByColor(@RequestParam String color) {
        List<Kotik> kotiki = kotikiService.findKotikByColor(color);
        return new ResponseEntity<>(
                kotiki.stream().
                        map((kotik -> kotik.getKotikWrap())).
                        collect(Collectors.toList()), HttpStatus.ACCEPTED);
    }
}
