package ru.itmo.kotikilab.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.itmo.kotikilab.entities.Friend;
import ru.itmo.kotikilab.entities.Kotik;
import ru.itmo.kotikilab.entities.Owner;
import ru.itmo.kotikilab.services.MainService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {
    private final MainService kotikiService;

    @GetMapping("/owners")
    public ResponseEntity<List<Owner>>getOwners(){
        return ResponseEntity.ok().body(kotikiService.findAllOwners());
    };

    @PostMapping("/owner/save")
    public ResponseEntity<Owner>saveOwner(@RequestBody Owner owner){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/owner/save").toUriString());
        return ResponseEntity.created(uri).body(kotikiService.saveOwner(owner));
    }

    @GetMapping("/kotiks")
    public ResponseEntity<List<Kotik>>getKotiks(){
        return ResponseEntity.ok().body(kotikiService.findAllKotiks());
    };

    @PostMapping("/kotik/save")
    public ResponseEntity<Kotik>saveKotik(@RequestBody Kotik kotik){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/kotik/save").toUriString());
        return ResponseEntity.created(uri).body(kotikiService.saveKotik(kotik));
    }

    @GetMapping("/friends")
    public ResponseEntity<List<Friend>>getFriends(){
        return ResponseEntity.ok().body(kotikiService.findAllFriends());
    };

    @PostMapping("/friend/save")
    public ResponseEntity<Friend>saveFriend(@RequestBody Friend friend){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/friend/save").toUriString());
        return ResponseEntity.created(uri).body(kotikiService.saveFriend(friend));
    }

    @PostMapping("/owner/addtokotik")
    public ResponseEntity<Owner>addOwnerToKotik(@RequestBody int idOwner, @RequestBody int idKotik) {
        kotikiService.addOwnerToKotik(idOwner, idKotik);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/friend/addfriend")
    public ResponseEntity<Friend>addFriend(@RequestBody int idKotik, @RequestBody int idFriend) {
        kotikiService.addFriends(idKotik, idFriend);
        return ResponseEntity.ok().build();
    }
}
