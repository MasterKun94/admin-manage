package com.example.adminmanage.dao;

import com.example.adminmanage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserName(String name);

    @Query(value = "SELECT u.userName, u.passWord FROM User u WHERE u.userName=?1")
    User findUsernameAndPassWordByUsername(String name);

    boolean existsByUserName(String name);
}
