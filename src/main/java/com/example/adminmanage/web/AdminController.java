package com.example.adminmanage.web;

import com.example.adminmanage.entity.User;
import com.example.adminmanage.global.config.StatusCode;
import com.example.adminmanage.global.response.*;
import com.example.adminmanage.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ResponseEntity changeUser(
            @RequestBody User user) {

        return adminService.changeUser(user);
    }

    @PutMapping("/manage")
    public ResponseEntity addUser(
            @RequestBody User user) {

        return adminService.addUser(user);
    }

    @GetMapping("/manage")
    public ResponseEntity getAllUsers(
            @RequestParam(name = "pageNum", defaultValue = "1") int start,
            @RequestParam(name = "pageSize", defaultValue = "10") int size) {

        if (start < 1 || size < 1)
            return ResponseFactory.userManageResponse(new ArrayList<>());
        else
            return adminService.getAllUsers(start, size);
    }

    @PostMapping("/manage/pswdreset")
    public ResponseEntity resetPassword(
            @RequestBody Map<String, String> info) {

        String name = info.get("userName");

        if (name == null || name.isEmpty())
            return ResponseFactory.commitResponse(StatusCode.COMMIT_WRONG_ARGS);
        else
            return adminService.resetPassword(name);
    }

    @PutMapping("/login")
    public ResponseEntity login(
            @RequestBody Map<String, String> info) {

        String name = info.get("username");
        String pswd = info.get("password");

        if (name == null || name.isEmpty())
            return ResponseFactory.failLoginResponse(StatusCode.LOGIN_WRONG_USERNAME);
        else if (pswd == null || pswd.isEmpty())
            return ResponseFactory.failLoginResponse(StatusCode.LOGIN_WRONG_PASSWORD);
        else
            return adminService.checkUserIfValid(name, pswd);
    }
}
