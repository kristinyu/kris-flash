package com.kris.edu.app.user.controller;


import com.kris.edu.app.user.bean.UserBean;
import com.kris.edu.app.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserContoller {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("list")
    public List<UserBean> queryUserCount(){
        return userService.getUserCountList();
    }

    @PostMapping("add")
    public String addNewUser(@RequestParam("username")String username,
                             @RequestParam("password")String password,
                             @RequestParam("nickname")String nickname){

        boolean result = userService.addNewUser(username,password,nickname);

        if (result){
            return "success";
        }else{
            return "failed";
        }
    }
}
