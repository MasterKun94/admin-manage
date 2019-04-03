package com.example.adminmanage.global.response;

import com.example.adminmanage.entity.User;

import java.util.List;

public class ResponseFactory {

    public static LoginResponse successLoginResponse(User user) {
        return new LoginResponse(user);
    }

    public static LoginResponse failLoginResponse(String status) {
        return new LoginResponse(status);
    }

    public static UserManageResponse userManageResponse(List<User> users) {
        return new UserManageResponse(users);
    }

    public static CommitResponse commitResponse(String status) {
        return new CommitResponse(status);
    }
}
