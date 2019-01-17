package com.bgp.mgr.common.response;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

public class ResponseBodyResult<T> implements Serializable {

    private static int SUCCESS_CODE = 0;
    private static int ERROR_CODE = -1;
    private static int NO_AUTH_CODE = -2;

    private static String SUCCESS_MESSAGE = "操作成功！";
    private static String ERROR_MESSAGE = "操作失败！";
    private static String NO_AUTH_MESSAGE = "您没有操作权限，请联系系统管理员！";

    private int code;
    private String message;
    private T data;

    private ResponseBodyResult(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    private ResponseBodyResult(int code, String message, T data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static String success() {
        return JSON.toJSONString(new ResponseBodyResult(ResponseBodyResult.SUCCESS_CODE, ResponseBodyResult.SUCCESS_MESSAGE));
    }

    public static <T> String success(T data) {
        return JSON.toJSONString(new ResponseBodyResult(ResponseBodyResult.SUCCESS_CODE, ResponseBodyResult.SUCCESS_MESSAGE, data));
    }

    public static String error() {
        return JSON.toJSONString(new ResponseBodyResult(ResponseBodyResult.ERROR_CODE, ResponseBodyResult.ERROR_MESSAGE));
    }

    public static String error(String message) {
        return JSON.toJSONString(new ResponseBodyResult(ResponseBodyResult.ERROR_CODE, message));
    }

    public static String notAuth() {
        return JSON.toJSONString(new ResponseBodyResult(ResponseBodyResult.NO_AUTH_CODE, ResponseBodyResult.NO_AUTH_MESSAGE));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}