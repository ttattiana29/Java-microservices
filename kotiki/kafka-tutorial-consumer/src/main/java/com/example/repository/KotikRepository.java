package com.example.repository;

import com.example.entities.Color;
import com.example.entities.Kotik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KotikRepository extends JpaRepository<Kotik, Integer> {
    List<Kotik> findByColor(Color color);
}
