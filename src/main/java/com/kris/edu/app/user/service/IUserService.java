package com.kris.edu.app.user.service;

import com.kris.edu.app.user.bean.UserBean;
import com.kris.edu.framwork.BaseResult.PageResult;

import java.util.List;

public interface IUserService {

    PageResult getUserCountList(Integer pageSize,Integer pageNum);

    Integer addNewUser(String username, String password, String nickname,String phone);

}
