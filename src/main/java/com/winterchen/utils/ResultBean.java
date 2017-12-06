package com.winterchen.utils;

import java.io.Serializable;

/**
 * 数据返回封装类
 * Created by winterchen on 2017/12/1.
 */
public class ResultBean<T> implements Serializable{

    private static final long serialVersionUID = 1L;

    private String msg;

    private int code;

    private T data;

    /**
     * 成功返回
     * @param code
     * @param data
     */
    public ResultBean(int code, T data) {
        this.code = code;
        this.data = data;
    }

    /**
     * 失败返回
     * @param msg
     * @param code
     */
    public ResultBean(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "msg='" + msg + '\'' +
                ", code=" + code +
                ", data=" + data +
                '}';
    }
}
