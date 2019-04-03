package com.example.adminmanage.service;

import com.example.adminmanage.dao.UserRepository;
import com.example.adminmanage.entity.User;
import com.example.adminmanage.global.config.StatusCode;
import com.example.adminmanage.global.response.CommitResponse;
import com.example.adminmanage.global.response.LoginResponse;
import com.example.adminmanage.global.response.ResponseFactory;
import com.example.adminmanage.global.response.UserManageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {
    private UserRepository userRepository;

    @Autowired
    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserManageResponse getAllUsers(int start, int size) {
        Page<User> page = userRepository.findAll(PageRequest.of(start, size));
        return ResponseFactory.userManageResponse(page.getContent());
    }

    @Override
    @Transactional
    public CommitResponse changeUser(User user) {
        User user1 = userRepository.findByUserName(user.getUserName())
                .orElse(new User(user.getUserName()));
        user1.setUserType(user.getUserType());
        user1.setAccountStatus(user.isAccountStatus());
        user1.setRemarksInfo(user.getRemarksInfo());

        return ResponseFactory.commitResponse(StatusCode.COMMIT_SUCCESS);
    }

    @Override
    public LoginResponse checkUserIfValid(String name, String pswd) {
        User user1 = userRepository.findUserByUserName(name);

        if (user1 == null)
            return ResponseFactory.failLoginResponse(StatusCode.LOGIN_WRONG_USERNAME);

        if (!user1.isAccountStatus())
            return ResponseFactory.failLoginResponse(StatusCode.LOGIN_NEGATIVE_STATUS);

        if (!user1.getPassWord().equals(pswd))
            return ResponseFactory.failLoginResponse(StatusCode.LOGIN_WRONG_PASSWORD);

        return ResponseFactory.successLoginResponse(user1);
    }

    @Override
    @Transactional
    public CommitResponse resetPassword(String name) {
        User user1 = userRepository.findUserByUserName(name);

        if (user1 == null)
            return ResponseFactory.commitResponse(StatusCode.COMMIT_FAIL);
        else
            user1.setPassWord("123456");
            return ResponseFactory.commitResponse(StatusCode.COMMIT_SUCCESS);

    }
}
