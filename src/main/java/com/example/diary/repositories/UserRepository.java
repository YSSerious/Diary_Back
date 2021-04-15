package com.example.diary.repositories;

import com.example.diary.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    User getById(String id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
