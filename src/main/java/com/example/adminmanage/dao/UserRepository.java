package com.example.adminmanage.dao;

import com.example.adminmanage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserName(String name);

    User findUserByUserName(String name);

    boolean existsByUserName(String name);
}
