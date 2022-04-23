package ru.itmo.kotikilab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itmo.kotikilab.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findById(int id);
}
