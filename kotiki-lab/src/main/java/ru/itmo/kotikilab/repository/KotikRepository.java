package ru.itmo.kotikilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotikilab.entities.Color;
import ru.itmo.kotikilab.entities.Kotik;

import java.util.List;

@Repository
public interface KotikRepository extends JpaRepository<Kotik, Integer> {
    List<Kotik> findByColor(Color color);
}
