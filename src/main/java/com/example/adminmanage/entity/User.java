package com.example.adminmanage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class User {

    public User() { }

    public User(String username) {
        this.username = username;
        this.accountStatus = true;
        this.passwordStatus = false;
        this.createTime = new Date();
        this.resetPassword();
    }

    @Id
    @Column(name = "username")
    private String username;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "userType", nullable = false)
    private UserType userType;

    @Column(name = "accountStatus", nullable = false)
    private boolean accountStatus;

    @Column(name = "passwordStatus", nullable = false)
    private boolean passwordStatus;

    @Column(name = "remarksInfo")
    private String remarksInfo;

    @Column(name = "createTime", nullable = false, updatable = false)
    private Date createTime;

    public void resetPassword() {
        this.password = "123456";//TODO passwordEncoder
    }
}
