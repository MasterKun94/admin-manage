package com.example.adminmanage.global.response;

import com.example.adminmanage.entity.User;

import java.util.List;

public class ResponseFactory {

    public static ResponseEntity successLoginResponse(User user) {
        return new LoginResponse(user);
    }

    public static ResponseEntity failLoginResponse(String status) {
        return new LoginResponse(status);
    }

    public static ResponseEntity userManageResponse(List<User> users) {
        return new UserManageResponse(users);
    }

    public static ResponseEntity commitResponse(String status) {
        return new CommitResponse(status);
    }
}
