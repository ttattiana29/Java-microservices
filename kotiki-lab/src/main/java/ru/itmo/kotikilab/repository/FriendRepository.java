package ru.itmo.kotikilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotikilab.entities.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer> {
}
