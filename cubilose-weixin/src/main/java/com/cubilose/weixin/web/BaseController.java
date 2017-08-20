package com.cubilose.weixin.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Created by jianxin.wang on 2017/8/17.
 */

public class BaseController {

    ResponseEntity success() {
        return success("");
    }

    @SuppressWarnings("unchecked")
    ResponseEntity success(Object data) {
        Result result = new Result(data);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    ResponseEntity error() {
        return error(-1);
    }

    ResponseEntity error(int code) {
        return error(code, "");
    }

    @SuppressWarnings("unchecked")
    ResponseEntity error(int code, Object data) {
        Result result = new Result(code, data);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
