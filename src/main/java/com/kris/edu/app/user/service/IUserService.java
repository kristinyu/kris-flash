package com.kris.edu.app.user.service;

import com.kris.edu.app.user.bean.UserBean;

import java.util.List;

public interface IUserService {

    List<UserBean> getUserCountList();

    boolean addNewUser(String username, String password, String nickname);

}
