package ru.itmo.kotikilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotikilab.entities.Owner;

@Repository
public interface OwnerRepository extends JpaRepository<Owner, Integer> {
}
