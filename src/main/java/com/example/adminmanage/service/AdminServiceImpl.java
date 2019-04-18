package com.example.adminmanage.service;

import com.example.adminmanage.dao.UserRepository;
import com.example.adminmanage.entity.User;
import com.example.adminmanage.entity.UserType;
import com.example.adminmanage.global.config.StatusCode;
import com.example.adminmanage.global.response.*;
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
    public ResponseEntity getAllUsers(int start, int size) {

        Page<User> page = userRepository.findAll(PageRequest.of(start - 1, size));
        return ResponseFactory.userManageResponse(page.getContent());

    }

    @Override
    @Transactional
    public ResponseEntity changeUser(User user) {
        User user1 = userRepository.findUserByUsername(user.getUsername());
        if (user1 == null)
            return ResponseFactory.commitResponse(StatusCode.COMMIT_FAIL);

        return ResponseFactory.commitResponse(checkAndFlush(user1, user));

    }

    @Override
    @Transactional
    public ResponseEntity addUser(User user) {

        if (userRepository.existsByUsername(user.getUsername()))
            return ResponseFactory.commitResponse((StatusCode.COMMIT_USER_EXIST));

        User user1 = new User(user.getUsername());
        return ResponseFactory.commitResponse(checkAndFlush(user1, user));

    }

    @Override
    public ResponseEntity checkUserIfValid(String name, String pswd) {
        User user1 = userRepository.findUserByUsername(name);

        if (user1 == null)
            return ResponseFactory.failLoginResponse(StatusCode.LOGIN_WRONG_USERNAME);

        if (!user1.isAccountStatus())
            return ResponseFactory.failLoginResponse(StatusCode.LOGIN_NEGATIVE_STATUS);

        if (!user1.getPassword().equals(pswd))
            return ResponseFactory.failLoginResponse(StatusCode.LOGIN_WRONG_PASSWORD);

        return ResponseFactory.successLoginResponse(user1);
    }

    @Override
    @Transactional
    public ResponseEntity resetPassword(String name) {
        User user1 = userRepository.findUsernameAndPassWordByUsername(name);

        if (user1 == null)
            return ResponseFactory.commitResponse(StatusCode.COMMIT_FAIL);

        user1.resetPassword();
        user1.setPasswordStatus(false);
        userRepository.save(user1);
        return ResponseFactory.commitResponse(StatusCode.COMMIT_SUCCESS);

    }

    private String checkAndFlush(User originUser, User newUser) {
        UserType type = newUser.getUserType();
        if (
                type.equals(UserType.Admin) ||
                type.equals(UserType.DataAnalyser) ||
                type.equals(UserType.DataManager))
        {
            originUser.setUserType(newUser.getUserType());
            originUser.setAccountStatus(newUser.isAccountStatus());
            originUser.setRemarksInfo(newUser.getRemarksInfo());
            userRepository.save(originUser);
            return StatusCode.COMMIT_SUCCESS;
        } else {
            return StatusCode.COMMIT_WRONG_ARGS;
        }
    }
}
