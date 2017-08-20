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
    ResponseEntity list(@RequestParam int pageNum, @RequestParam int pageSize) {
        List<User> users = userService.list(pageNum, pageSize);
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
     * @param keyword   查询关键字，可以是微信昵称，微信ID
     * @return
     */
    @RequestMapping("/query")
    ResponseEntity query(@RequestParam String keyword,
                         @RequestParam int pageNum,
                         @RequestParam int pageSize) {
        logger.info("query：" + keyword);
        List<User> users = userService.query(keyword, pageNum, pageSize);
        users.forEach(user -> {
            logger.info(user.toString());
        });

        return success(users);
    }

    /**
     * 拉取最新微信用户列表
     *
     * @return
     */
    @RequestMapping("/pull_user_list")
    ResponseEntity pullUserLit() {
       userService.pullUserList();

       return success();
    }

}
