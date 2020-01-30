package com.kris.edu.framwork.BaseResult;

import com.kris.edu.framwork.Enum.EduErrorEnum;
import com.kris.edu.framwork.Enum.EduSuccessEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult {
    private int code;
    private String msg;
    private String date;


    public static BaseResult operateSuccess(EduSuccessEnum successEnum){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(successEnum.getCode());
        baseResult.setMsg(successEnum.getMsg());
        baseResult.setDate(simpleDateFormat.format(new Date()));
        return baseResult;
    }


    public static BaseResult operateFailed(EduErrorEnum successEnum){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(successEnum.getCode());
        baseResult.setMsg(successEnum.getMsg());
        baseResult.setDate(simpleDateFormat.format(new Date()));
        return baseResult;
    }

}
