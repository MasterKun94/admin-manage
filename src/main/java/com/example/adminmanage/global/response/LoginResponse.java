package com.example.adminmanage.global.response;

import com.example.adminmanage.entity.User;
import com.example.adminmanage.entity.UserType;
import com.example.adminmanage.global.config.StatusCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
//@Setter
public class LoginResponse implements ResponseEntity {
    private String username;
    private String token;
    private UserType userType;
    private Date createTime;
    private Date loginTime;
    private String code;
    private String message;
    private List<String> commonMenu;

    LoginResponse(User user) {
        this.username = user.getUsername();
        this.userType = user.getUserType();
        this.loginTime = new Date();
        this.createTime = user.getCreateTime();
        this.code = "0";
        this.message = StatusCode.LOGIN_SUCCESS;

        //TODO
        this.token = "";
        this.commonMenu = new ArrayList<>();
    }

    LoginResponse(String status) {
        this.code = "1";
        this.message = status;
        this.loginTime = new Date();
    }
}
