package com.company.platform.userservice.repository;

import com.company.platform.userservice.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByNameContainingIgnoreCase(String name, Pageable page);
    Optional<User> findByEmail(String email);

}