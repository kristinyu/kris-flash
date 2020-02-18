package com.kris.edu.app.user.controller;


import com.kris.edu.app.user.bean.UserBean;
import com.kris.edu.app.user.service.IUserService;
import com.kris.edu.framwork.BaseResult.BaseResult;
import com.kris.edu.framwork.BaseResult.PageResult;
import com.kris.edu.framwork.Enum.EduErrorEnum;
import com.kris.edu.framwork.Enum.EduSuccessEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserContoller {

    @Autowired
    private IUserService userService;

    @GetMapping("list")
    public PageResult queryUserCount(){
        return userService.getUserCountList();
    }

    @PostMapping("add")
    public BaseResult addNewUser(@RequestParam("username")String username,
                                 @RequestParam("password")String password,
                                 @RequestParam("nickname")String nickname,
                                 @RequestParam("phone")String phone) {
        Integer result = userService.addNewUser(username, password, nickname,phone);
        switch (result) {
            case 1:
                return BaseResult.operateSuccess(EduSuccessEnum.NEW_USER_ADD_SUCCESSFULLY);
            case 2:
                return BaseResult.operateFailed(EduErrorEnum.NEW_USER_ADD_FAILED);
            case 3:
                return BaseResult.operateFailed(EduErrorEnum.DUPLIACTE_USER_ADD_FAILED);
        }
           return BaseResult.operateFailed(EduErrorEnum.NEW_USER_ADD_FAILED);
    }




}
