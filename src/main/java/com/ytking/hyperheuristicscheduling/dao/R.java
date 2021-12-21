package com.ytking.hyperheuristicscheduling.dao;

import lombok.Data;

import java.io.Serializable;


/**
 * @version V1.0
 * @Package com.ss.jwt.R
 * @author: Liu
 * @Date: 10:21
 */
@Data
public class R<T> {
    /*返回体*/
    private  Integer code;
    private String msg;
    private T data;


}
