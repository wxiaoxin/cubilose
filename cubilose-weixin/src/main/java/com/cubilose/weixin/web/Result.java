package com.cubilose.weixin.web;

/**
 * Created by jianxin.wang on 2017/8/17.
 */
public class Result {

    private int code;

    private Object data;

    public Result() {
        this(0);
    }

    public Result(int code) {
        this(code, "");
    }

    public Result(Object data) {
        this(0, data);
    }

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
