package ru.itmo.kotikilab.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itmo.kotikilab.entities.Friend;
import ru.itmo.kotikilab.entities.Kotik;
import ru.itmo.kotikilab.entities.Owner;
import ru.itmo.kotikilab.repository.FriendRepository;
import ru.itmo.kotikilab.repository.KotikRepository;
import ru.itmo.kotikilab.repository.OwnerRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MainServiceImpl implements MainService {
    private final KotikRepository kotikRepo;
    private final OwnerRepository ownerRepo;
    private final FriendRepository friendRepo;

    @Override
    public Owner findOwnerById(int id) {
        log.info("Finding owner by id");
        return ownerRepo.getById(id);
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
    }

    @Override
    public Friend addFriends(int idKotik, int idFriend) {
        Kotik kotik = findKotikById(idKotik);
        Kotik kotikFriend = findKotikById(idFriend);
        Friend newFriend = kotik.addFriend(kotikFriend);
        return newFriend;
    }
}
