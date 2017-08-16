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

    ResponseEntity success(Object value) {
        Result result = new Result(value);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
