package com.kris.edu.framwork.BaseResult;

import com.kris.edu.framwork.Enum.EduErrorEnum;
import com.kris.edu.framwork.Enum.EduSuccessEnum;
import com.kris.edu.framwork.exception.EduException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResult<T> {

    private int code;
    private String msg;
    private T date;


    public static BaseResult operateSuccess(EduSuccessEnum successEnum){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(successEnum.getCode());
        baseResult.setMsg(successEnum.getMsg());
        baseResult.setDate(simpleDateFormat.format(new Date()));
        return baseResult;
    }


    public static BaseResult operateFailed(EduErrorEnum eduErrorEnum){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(eduErrorEnum.getCode());
        baseResult.setMsg(eduErrorEnum.getMsg());
        baseResult.setDate(simpleDateFormat.format(new Date()));
        return baseResult;
    }

    public static <T extends EduException> BaseResult<Void> fail(T exception) {
        return fail(exception.getCode(), exception.getMessage());
    }

    public static BaseResult<Void> fail(int code, String message) {
        BaseResult<Void> apiResult = new BaseResult<>();
        apiResult.setCode(code);
        apiResult.setMsg(message);
        return apiResult;
    }


}
