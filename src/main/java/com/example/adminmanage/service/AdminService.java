package com.example.adminmanage.service;

import com.example.adminmanage.entity.User;
import com.example.adminmanage.global.response.CommitResponse;
import com.example.adminmanage.global.response.LoginResponse;
import com.example.adminmanage.global.response.ResponseEntity;
import com.example.adminmanage.global.response.UserManageResponse;

public interface AdminService {
    ResponseEntity getAllUsers(int start, int number);

    ResponseEntity changeUser(User user);

    ResponseEntity addUser(User user);

    ResponseEntity checkUserIfValid(String name, String pswd);

    ResponseEntity resetPassword(String name);
}
