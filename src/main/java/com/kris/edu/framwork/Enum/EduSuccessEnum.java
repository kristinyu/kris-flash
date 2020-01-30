package com.kris.edu.framwork.Enum;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EduSuccessEnum {

    NEW_USER_ADD_SUCCESSFULLY(1, "新增用户成功");

    private int code;
    private String msg;

}
