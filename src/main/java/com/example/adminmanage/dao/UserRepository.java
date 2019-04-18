package com.example.adminmanage.dao;

import com.example.adminmanage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String name);

    @Query(value = "SELECT u.username, u.password FROM User u WHERE u.username=?1")
    User findUsernameAndPassWordByUsername(String name);

    boolean existsByUsername(String name);
}
