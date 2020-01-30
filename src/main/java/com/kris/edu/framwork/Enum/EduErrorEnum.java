package com.kris.edu.framwork.Enum;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum EduErrorEnum {

    NEW_USER_ADD_FAILED(-3000, "新用户增加失败"),
    DUPLIACTE_USER_ADD_FAILED(-3001,"新增用户名已存在");

    private int code;
    private String msg;

}
