package com.example.adminmanage.global.response;

import com.example.adminmanage.entity.User;
import com.example.adminmanage.global.config.StatusCode;
import lombok.Getter;

import java.util.Date;

@Getter
//@Setter
public class LoginResponse {
    private String userName;
    private String token;
    private String userType;
    private Date createTime;
    private Date loginTime;
    private String code;
    private String message;
    private String commonMenu;

    LoginResponse(User user) {
        this.userName = user.getUserName();
        this.userType = user.getUserType();
        this.loginTime = new Date();
        this.createTime = user.getCreateTime();
        this.code = "0";
        this.message = StatusCode.LOGIN_SUCCESS;

        //TODO token 和 commonMenu 未完成
        this.token = "123123";
        this.commonMenu = "321321";
    }

    LoginResponse(String status) {
        this.code = "1";
        this.message = status;
        this.loginTime = new Date();
    }
}
