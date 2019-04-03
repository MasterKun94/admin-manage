package com.example.adminmanage.service;

import com.example.adminmanage.entity.User;
import com.example.adminmanage.global.response.CommitResponse;
import com.example.adminmanage.global.response.LoginResponse;
import com.example.adminmanage.global.response.UserManageResponse;

public interface AdminService {
    UserManageResponse getAllUsers(int start, int number);

    CommitResponse changeUser(User user);

    LoginResponse checkUserIfValid(String name, String pswd);

    CommitResponse resetPassword(String name);
}
