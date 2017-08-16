package com.cubilose.weixin.web;

/**
 * Created by jianxin.wang on 2017/8/17.
 */
public class Result {

    private int code;

    private Object value;

    public Result() {
        this(0);
    }

    public Result(int code) {
        this(code, "");
    }

    public Result(Object value) {
        this(0, value);
    }

    public Result(int code, Object value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
