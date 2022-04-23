package ru.itmo.kotikilab.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itmo.kotikilab.entities.*;
import ru.itmo.kotikilab.repository.*;
import ru.itmo.kotikilab.tools.KotikiException;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MainServiceImpl implements MainService {
    private final KotikRepository kotikRepo;
    private final OwnerRepository ownerRepo;
    private final FriendRepository friendRepo;
    private final RoleRepository roleRepo;

    @Override
    public void createOwner(String name, String date, String username, String password, String role){
        log.info("Create owner");
        Owner owner = new Owner(name, LocalDate.parse(date), username, password, role);
        ownerRepo.save(owner);
    }

    @Override
    public Owner findOwnerById(int id) {
        log.info("Finding owner by id");
        return ownerRepo.getById(id);
    }

    @Override
    public Owner findOwnerByUsername(String username) {
        log.info("Finding owner by username");
        return ownerRepo.findByUsername(username);
    }

    @Override
    public Owner saveOwner(Owner owner) {
        log.info("Saving new owner");
        return ownerRepo.save(owner);
    }

    @Override
    public void deleteOwner(Owner owner) {
        log.info("Delete owner");
        ownerRepo.delete(owner);
    }

    @Override
    public List<Owner> findAllOwners() {
        log.info("Fetching all owners");
        return ownerRepo.findAll();
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
    public Role saveRole(Role role) {
        log.info("Saving new role");
        return roleRepo.save(role);
    }

    @Override
    public Kotik createKotik(String name, String date, String breed, int color, int ownerId) throws Exception {
        log.info("Create kotik");
        Color colorEnum = getColor(color);
        Owner owner = findOwnerById(ownerId);
        Kotik kotik = new Kotik(name, LocalDate.parse(date), breed, colorEnum, owner);
        ownerRepo.save(owner);
        kotikRepo.save(kotik);
        return kotik;
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

    @Override
    public void deleteKotik(Kotik kotik) {
        log.info("Delete kotik");
        kotikRepo.delete(kotik);
    }

    @Override
    public List<Kotik> findAllKotiks() {
        log.info("Fetching all kotiks");
        return kotikRepo.findAll();
    }

    @Override
    public List<Kotik> findKotikByColor(String color) {
        log.info("Find kotik by color");
        Color colorEnum = Color.getTypeByName(color);
        List<Kotik> kotiki = kotikRepo.findByColor(colorEnum);
        return kotiki;
    }

    @Override
    public Friend findFriendById(int id) {
        log.info("Finding friend by id");
        return friendRepo.getById(id);
    }

    @Override
    public Friend saveFriend(Friend friend) {
        log.info("Saving new friend");
        return friendRepo.save(friend);
    }

    @Override
    public void deleteFriend(Friend friend) {
        log.info("Delete friend");
        friendRepo.delete(friend);
    }

    @Override
    public List<Friend> findAllFriends() {
        log.info("Fetching all friends");
        return friendRepo.findAll();
    }

    @Override
    public void addOwnerToKotik(int idOwner, int idKotik) {
        log.info("Adding owner to kotik");
        Owner owner = findOwnerById(idOwner);
        Kotik kotik = findKotikById(idKotik);
        owner.addKotik(kotik);
        kotik.setOwnerId(owner);
        ownerRepo.save(owner);
        kotikRepo.save(kotik);
    }

    @Override
    public Friend addFriends(int idKotik, int idFriend) {
        Kotik kotik = findKotikById(idKotik);
        Kotik kotikFriend = findKotikById(idFriend);
        Friend newFriend = kotik.addFriend(kotikFriend);
        friendRepo.save(newFriend);
        return newFriend;
    }
}
