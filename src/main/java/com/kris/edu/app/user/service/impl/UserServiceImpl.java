package com.kris.edu.app.user.service.impl;

import com.kris.edu.app.user.bean.UserBean;
import com.kris.edu.app.user.dao.UserMapper;
import com.kris.edu.app.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserBean> getUserCountList() {
        return userMapper.selectUserList();
    }

    @Override
    public boolean addNewUser(String username, String password, String nickname) {
        return userMapper.addNewUser(username, password, nickname) > 0 ? true : false;
    }
}
