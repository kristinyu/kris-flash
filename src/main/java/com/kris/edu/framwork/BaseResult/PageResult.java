package com.kris.edu.framwork.BaseResult;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult<T> {
    private long totol;
    private List<T> list;
    private String date;

    public static PageResult success(long total,List list){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        PageResult pageResult = new PageResult<>();
        pageResult.setTotol(total);
        pageResult.setList(list);
        pageResult.setDate(simpleDateFormat.format(new Date()));
        return pageResult;
    }

}
