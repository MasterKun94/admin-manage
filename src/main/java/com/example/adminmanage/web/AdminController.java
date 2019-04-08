package com.example.adminmanage.web;

import com.example.adminmanage.entity.User;
import com.example.adminmanage.global.config.StatusCode;
import com.example.adminmanage.global.response.CommitResponse;
import com.example.adminmanage.global.response.LoginResponse;
import com.example.adminmanage.global.response.ResponseFactory;
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
    public CommitResponse changeUser(
            @RequestBody User user) {

        return adminService.changeUser(user);
    }

    @PutMapping("/manage")
    public CommitResponse addUser(
            @RequestBody User user) {

        return adminService.addUser(user);
    }

    @GetMapping("/manage")
    public UserManageResponse getAllUsers(
            @RequestParam(name = "pageNum", defaultValue = "1") int start,
            @RequestParam(name = "pageSize", defaultValue = "10") int size) {

        return adminService.getAllUsers(start, size);
    }

    @PostMapping("/manage/pswdreset")
    public CommitResponse resetPassword(
            @RequestBody Map<String, String> info) {

        String name = info.get("userName");

        if (name == null || name.isEmpty())
            return ResponseFactory.commitResponse(StatusCode.COMMIT_WRONG_ARGS);

        return adminService.resetPassword(name);
    }

    @PutMapping("/login")
    public LoginResponse login(
            @RequestBody Map<String, String> info) {

        String name = info.get("userName");
        String pswd = info.get("passWord");

        if (name == null || name.isEmpty())
            return ResponseFactory.failLoginResponse(StatusCode.LOGIN_WRONG_USERNAME);
        else if (pswd == null || pswd.isEmpty())
            return ResponseFactory.failLoginResponse(StatusCode.LOGIN_WRONG_PASSWORD);
        else
            return adminService.checkUserIfValid(info.get("userName"), info.get("passWord"));
    }
}
