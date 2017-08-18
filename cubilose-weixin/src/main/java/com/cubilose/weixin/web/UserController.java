package com.cubilose.weixin.web;

import com.cubilose.weixin.entity.User;
import com.cubilose.weixin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by jianxin.wang on 2017/8/15.
 */

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private final static Logger logger
            = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 用户信息列表页
     *
     * @return
     */
    @RequestMapping("/list")
    ResponseEntity list(@RequestParam int start, @RequestParam int size) {
        List<User> users = userService.list(start, size);
        users.forEach(user -> {
            logger.info(user.toString());
        });

        return success(users);
    }

    /**
     * 根据主键ID查询
     *
     * @param id    主键ID
     * @return
     */
    @RequestMapping("/query/{id}")
    ResponseEntity queryById(@PathVariable Long id) {
        logger.info("queryById：" + id);
        User user = userService.queryById(id);

        return success(user);
    }

    /**
     * 根据用户微信昵称ID查询
     *
     * @param wName   用户微信昵称
     * @return
     */
    @RequestMapping("/qw/{id}")
    ResponseEntity queryByWName(@PathVariable String wName) {
        logger.info("queryByWName：" + wName);
        List<User> users = userService.queryByName(wName);
        users.forEach(user -> {
            logger.info(user.toString());
        });

        return success(users);
    }

    /**
     * 根据快递单号查询
     *
     * @param logisticsNumber   快递单号
     * @return
     */
    @RequestMapping("/qn/{logisticsNumber}")
    ResponseEntity queryByLogisticsNumber(@PathVariable String logisticsNumber) {
        logger.info("queryByLogisticsNumber：" + logisticsNumber);
        List<User> users = userService.queryByLogisticsNumber(logisticsNumber);
        users.forEach(user -> {
            logger.info(user.toString());
        });

        return success(users);
    }


}
