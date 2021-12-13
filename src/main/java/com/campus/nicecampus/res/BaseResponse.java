package com.campus.nicecampus.res;

import lombok.Data;
import lombok.ToString;

/**
 * @author Henry
 */
@Data
@ToString
public class BaseResponse<T> {
    private int code;
    private String msg;
    private T data;

    public static BaseResponse succ(){
        BaseResponse baseResponse = new BaseResponse<>();
        baseResponse.setCode(1);
        baseResponse.setMsg("请求成功");
        return baseResponse;
    }
    public static BaseResponse fail(){
        BaseResponse baseResponse = new BaseResponse<>();
        baseResponse.setCode(0);
        baseResponse.setMsg("请求失败");
        return baseResponse;
    }
}
