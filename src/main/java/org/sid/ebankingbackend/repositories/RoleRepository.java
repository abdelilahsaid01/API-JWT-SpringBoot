package org.sid.ebankingbackend.repositories;

import org.sid.ebankingbackend.entities.Role;
import org.sid.ebankingbackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByNameIn(List<String> names);
}
