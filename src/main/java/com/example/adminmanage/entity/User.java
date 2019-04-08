package com.example.adminmanage.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@DynamicUpdate
public class User {

    public User() { }

    public User(String userName) {
        this.userName = userName;
        this.accountStatus = true;
        this.passWordStatus = false;
        this.createTime = new Date();
        this.passWord = "123456";
    }

    @Id
    @Column(name = "username")
    private String userName;

    @JsonIgnore
    @Column(name = "password", nullable = false)
    private String passWord;

    @Column(name = "userType", nullable = false)
    private String userType;

    @Column(name = "accountStatus", nullable = false)
    private boolean accountStatus;

    @Column(name = "passWordStatus", nullable = false)
    private boolean passWordStatus;

    @Column(name = "remarksInfo")
    private String remarksInfo;

    @Column(name = "createTime", nullable = false, updatable = false)
    private Date createTime;
}
