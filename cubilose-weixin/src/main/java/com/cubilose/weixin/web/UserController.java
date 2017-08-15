package com.cubilose.weixin.web;

import com.cubilose.weixin.entity.User;
import com.cubilose.weixin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/15.
 */

@RestController
@RequestMapping("/user")
public class UserController {

    private final static Logger logger
            = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    ResponseEntity list() {

        List<User> users = userService.list();
        users.forEach(user -> {
            logger.info(user.toString());
        });

        return new ResponseEntity("aaaa", HttpStatus.OK);
    }
}
