package com.ytking.hyperheuristicscheduling.Enum;
import lombok.Data;

/**
 * @version V1.0
 * @Package com.ss.jwt.R
 * @author: Liu
 * @Date: 10:36
 */
//枚举类
public enum Renum {
    //这里是可以自己定义的，方便与前端交互即可
    UNKNOWN_ERROR(300,"未知错误"),
    SUCCESS(200,"成功"),
    USER_NOT_EXIST(401,"数据不存在"),
    USER_IS_EXISTS(402,"数据已存在"),
    DATA_IS_NULL(403,"数据为空"),
    ;
    private Integer code;
    private String msg;

    Renum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
