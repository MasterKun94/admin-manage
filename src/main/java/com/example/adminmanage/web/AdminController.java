package com.example.adminmanage.web;

import com.example.adminmanage.entity.User;
import com.example.adminmanage.global.response.CommitResponse;
import com.example.adminmanage.global.response.LoginResponse;
import com.example.adminmanage.global.response.UserManageResponse;
import com.example.adminmanage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/manage")
    public CommitResponse updateUser(
            @RequestBody User user) {

        return adminService.changeUser(user);
    }

    @GetMapping("/manage")
    public UserManageResponse getAllUsers(
            @RequestParam(name = "pageNum", defaultValue = "0") int start,
            @RequestParam(name = "pageSize", defaultValue = "10") int size) {

        return adminService.getAllUsers(start, size);
    }

    @PostMapping("/manage/pswdreset")
    public CommitResponse resetPassword(
            @RequestBody String name) {

        return adminService.resetPassword(name);
    }

    @PutMapping("/login")
    public LoginResponse login(
            @RequestBody Map<String, String> info) {

        return adminService.checkUserIfValid(info.get("userName"), info.get("passWord"));
    }
}
