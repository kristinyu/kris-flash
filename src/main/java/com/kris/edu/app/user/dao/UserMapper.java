package com.kris.edu.app.user.dao;

import com.kris.edu.app.user.bean.UserBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    int selectUserCount();

    List<UserBean> selectUserList();

    int addNewUser(@Param("username") String username,
                   @Param("password") String password,
                   @Param("nickname") String nickname,
                   @Param("phone") String phone);

    List<UserBean> validateUser(@Param("usernameEnCode") String usernameEnCode);
}
