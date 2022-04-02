package ru.itmo.kotikilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotikilab.entities.Kotik;

@Repository
public interface KotikRepository extends JpaRepository<Kotik, Integer> {
}
