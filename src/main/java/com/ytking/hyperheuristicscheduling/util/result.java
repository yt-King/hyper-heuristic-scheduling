package com.ytking.hyperheuristicscheduling.util;



import com.sun.org.apache.bcel.internal.generic.NEW;
import com.ytking.hyperheuristicscheduling.Enum.Renum;
import com.ytking.hyperheuristicscheduling.dao.R;

/**
 * @version V1.0
 * @Package com.ss.jwt.utils
 * @author: Liu
 * @Date: 10:39
 */
public class result {
    /*成功，且返回体有数据*/
    public static R success(Object object) {
        R r = new R();
        r.setCode(Renum.SUCCESS.getCode());
        r.setMsg(Renum.SUCCESS.getMsg());
        r.setData(object);
        return r;
    }
    //成功，但返回体没数据
    public static  R success(){
        return success(null);
    }
    //失败返回信息
    public static R Err(Integer code,String msg){
        R r = new R();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }
}
