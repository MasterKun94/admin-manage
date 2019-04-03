package com.example.adminmanage.global.response;

import com.example.adminmanage.entity.User;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
public class UserManageResponse {
    private int listTotal;
    private Date time;
    private List<User> data;

    UserManageResponse(List<User> users) {
        this.listTotal = users.size();
        this.time = new Date();
        this.data = users;
    }
}
